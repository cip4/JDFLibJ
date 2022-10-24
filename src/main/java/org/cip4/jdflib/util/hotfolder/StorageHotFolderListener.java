/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2022 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
package org.cip4.jdflib.util.hotfolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.MyPair;
import org.cip4.jdflib.util.RollingBackupFile;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.ThreadUtil;
import org.cip4.jdflib.util.file.FileSorter;

/**
 * hotfolder listener that also moves to a done dir
 *
 * @author rainer prosi
 * @date Feb 14, 2011
 */
class StorageHotFolderListener implements HotFolderListener
{
	private final File storage;
	private File errorStorage;
	private File okStorage;
	final StorageHotFolder parent;
	protected final Log log;
	private final AtomicInteger nHotOK;
	private final AtomicInteger nHotError;
	private final AtomicInteger nQueued;
	private int maxStore;

	/**
	 * @param storageDir
	 * @param hfListener
	 *
	 */
	StorageHotFolderListener(final File storageDir, final HotFolderListener hfListener, final StorageHotFolder parent)
	{
		super();
		nHotOK = new AtomicInteger();
		nHotError = new AtomicInteger();
		nQueued = new AtomicInteger();
		setMaxStore(42);
		errorStorage = null;
		okStorage = null;
		log = LogFactory.getLog(getClass());
		if (hfListener == null)
		{
			log.error("hfListner MUST NOT be null");
			throw new IllegalArgumentException("hfListner MUST NOT be null");
		}
		theListener = hfListener;
		storage = storageDir;
		this.parent = parent;
	}

	/**
	 *
	 * set the directory for successful done
	 *
	 * @param ok
	 */
	void setOKStorage(final File ok)
	{
		okStorage = ok;
		if (ok != null)
		{
			okStorage.mkdirs();
			okStorage.setWritable(true);
			if (!okStorage.isDirectory())
			{
				log.error("OK Directory is not a directory: " + okStorage.getAbsolutePath());
			}
		}
	}

	/**
	 *
	 * set the directory for error done
	 *
	 * @param error
	 */
	void setErrorStorage(final File error)
	{
		errorStorage = error;
		if (error != null)
		{
			errorStorage.mkdirs();
			errorStorage.setWritable(true);
			if (!errorStorage.isDirectory())
			{
				log.error("Error Directory is not a directory: " + errorStorage.getAbsolutePath());
			}
		}
	}

	HotFolderListener theListener;
	private int maxAux;

	/**
	 * @see org.cip4.jdflib.util.hotfolder.HotFolderListener#hotFile(java.io.File)
	 */
	@Override
	public boolean hotFile(final File hotFile)
	{
		log.info("processing hot file: " + hotFile);
		final MyPair<File, File> storedFiles = getStoredFile(hotFile);
		if (storedFiles == null)
		{
			log.warn("snafu retrieving file " + hotFile.getAbsolutePath());
			copyCompleted(hotFile, false);
			return false; // not good
		}
		boolean b = false;
		try
		{
			b = theListener.hotFile(storedFiles.getA());
		}
		catch (final Throwable t)
		{
			log.error("Could not process " + hotFile, t);
		}
		copyCompleted(storedFiles.getA(), b);
		log.info("deleting tmp file: " + storedFiles.getB());
		final boolean deleted = FileUtil.deleteAll(storedFiles.getB());
		if (!deleted)
		{
			log.warn("Problems deleting: " + storedFiles.getB());

		}
		return b;
	}

	/**
	 * copy to ok or completed
	 *
	 * @param storedFile the file to move
	 * @param bOK if true, ok else error
	 * @param bZappTmp
	 */
	void copyCompleted(final File storedFile, final boolean bOK)
	{
		final File auxFile = FileUtil.getAuxDir(storedFile);

		if (bOK)
		{
			if (okStorage != null)
			{
				final File okFile = FileUtil.getFileInDirectory(okStorage, new File(storedFile.getName()));
				final RollingBackupFile roller = new RollingBackupFile(okFile, 10);
				roller.setWantExtension(true);
				roller.getNewFile();
				final File copied = FileUtil.moveFileToDir(storedFile, okStorage);
				if (copied == null)
				{
					handleBad(storedFile, true);
				}
				else
				{
					log.info("Copied good file: " + storedFile.getName() + " to " + okStorage);
					copied.setLastModified(System.currentTimeMillis());
					if (auxFile != null)
					{
						final File auxbackup = FileUtil.getFileInDirectory(okStorage, new File(auxFile.getName()));
						final RollingBackupFile rollingBackupFile = new RollingBackupFile(auxbackup, 10);
						rollingBackupFile.setWantExtension(true);
						rollingBackupFile.getNewFile();
						final File movedAux = FileUtil.moveFileToDir(auxFile, okStorage);
						if (movedAux != null)
						{
							log.info("Copied good aux dir: " + auxFile.getName() + " to " + movedAux);
						}
						else
						{
							log.warn("Could not copy good aux dir: " + auxFile.getName() + " to " + okStorage);
						}
					}
				}
				cleanup(bOK);
			}
			else
			{
				final boolean ok = FileUtil.forceDelete(storedFile);
				if (!ok)
				{
					log.warn("failed to delete temporary file " + storedFile.getAbsolutePath());
				}
				FileUtil.deleteAll(auxFile);
			}
		}
		else
		{
			if (errorStorage != null)
			{
				final File backup = FileUtil.getFileInDirectory(errorStorage, new File(storedFile.getName()));
				final RollingBackupFile roller = new RollingBackupFile(backup, 10);
				roller.setWantExtension(true);
				roller.getNewFile();
				final File copied = FileUtil.moveFileToDir(storedFile, errorStorage);
				if (copied == null)
				{
					handleBad(storedFile, false);
				}
				else
				{
					log.warn("Copied error file: " + storedFile.getName() + " to " + errorStorage);
					copied.setLastModified(System.currentTimeMillis());
					if (auxFile != null)
					{
						final File auxbackup = FileUtil.getFileInDirectory(errorStorage, new File(auxFile.getName()));
						final RollingBackupFile rollingBackupFile = new RollingBackupFile(auxbackup, 10);
						rollingBackupFile.setWantExtension(true);
						rollingBackupFile.getNewFile();
						final File movedAux = FileUtil.moveFileToDir(auxFile, errorStorage);
						if (movedAux != null)
						{
							log.info("Copied error aux dir: " + auxFile.getName() + " to " + movedAux);
						}
						else
						{
							log.warn("Could not copy error aux dir: " + auxFile.getName() + " to " + okStorage);
						}
					}
				}

				cleanup(bOK);
			}
			else
			{
				final boolean ok = FileUtil.forceDelete(storedFile);
				if (!ok)
					log.warn("failed to delete temporary file " + storedFile.getAbsolutePath());
				FileUtil.deleteAll(auxFile);
			}
		}
		final File tmp = storedFile.getParentFile();
	}

	protected boolean handleBad(final File storedFile, final boolean bOK)
	{
		if (bOK)
		{
			log.warn("could not move ok " + storedFile + " to " + okStorage.getAbsolutePath());
		}
		else
		{
			log.warn("could not move error " + storedFile + " to " + errorStorage.getAbsolutePath());
		}
		final File auxFile = FileUtil.getAuxDir(storedFile);
		FileUtil.deleteAll(auxFile);

		final boolean bZapp = storedFile.delete();
		if (bZapp)
		{
			log.warn("utterly removed hot file: " + storedFile);
		}
		else
		{
			log.error("cannot delete hot file: " + storedFile);
		}
		return bZapp;
	}

	/**
	 * remove old stuff from ok and error folder
	 *
	 * @param bOK if true cleanup the ok folder, else the error folder
	 */
	void cleanup(final boolean bOK)
	{
		final int nHot = bOK ? nHotOK.incrementAndGet() : nHotError.incrementAndGet();
		final int check = Math.max(1, maxAux / 4);
		if ((nHot % check == 0) || (Math.random() < 0.1))
		{
			final FileSorter fs = new FileSorter(bOK ? okStorage : errorStorage);
			final File[] list = fs.sortLastModified(true);
			final List<File> vList = new ArrayList<>();
			for (final File f : list)
			{
				if (!f.isDirectory())
				{
					vList.add(f);
				}
			}
			int i = 0;
			for (final File hotFile : vList)
			{
				cleanupSingle(i++, hotFile);
			}
		}
	}

	void cleanupSingle(final int i, final File hotFile)
	{
		if (i > maxStore)
		{
			final boolean ok = FileUtil.forceDelete(hotFile);
			if (ok)
			{
				log.debug("deleted temporary file " + hotFile.getAbsolutePath());
			}
			else
			{
				log.warn("failed to delete temporary file " + hotFile.getAbsolutePath());
			}
		}
		final File aux = i > maxAux ? FileUtil.getAuxDir(hotFile) : null;
		if (aux != null)
		{
			final boolean ok = FileUtil.deleteAll(aux);
			if (ok)
			{
				log.info("deleted temporary aux directory " + aux.getAbsolutePath());
			}
			else
			{
				log.warn("failed to delete temporary aux directory " + aux.getAbsolutePath());
			}
		}
	}

	MyPair<File, File> getStoredFile(final File hotFile)
	{
		final String name = hotFile == null ? null : hotFile.getName();
		if (StringUtil.isEmpty(name))
		{
			log.error("invalid hot file: " + hotFile);
			return null;
		}
		final File tmpDir = getTmpDir();
		final File newAbsoluteFile = FileUtil.getFileInDirectory(tmpDir, new File(name));
		boolean ok = false;
		for (int i = 0; !ok && (i < parent.getRetry()); i++)
		{
			ok = FileUtil.moveFile(hotFile, newAbsoluteFile);
			if (!ok)
			{
				log.warn("retry " + i + " moving file from: " + hotFile.getAbsolutePath() + " to " + newAbsoluteFile.getAbsolutePath());
				if (!hotFile.exists() || !hotFile.canRead())
				{
					log.error("file disappeared while waiting: " + hotFile.getAbsolutePath());
					return null;
				}
				if (!ThreadUtil.sleep((i + 2) * parent.getStabilizeTime()))
				{
					log.error("Interrupted while waiting to move file from: " + hotFile.getAbsolutePath() + " to " + newAbsoluteFile.getAbsolutePath());
					return null;
				}
			}
		}
		if (ok)
		{
			log.info("moving file from: " + hotFile.getAbsolutePath() + " to " + newAbsoluteFile.getAbsolutePath());
		}
		else
		{
			log.error("cannot move file from: " + hotFile.getAbsolutePath() + " to " + newAbsoluteFile.getAbsolutePath());
		}
		if (ok)
		{
			processAux(hotFile, tmpDir);
		}
		return ok ? new MyPair<>(newAbsoluteFile, tmpDir) : null;
	}

	void processAux(final File hotFile, final File tmpDir)
	{
		final File aux = FileUtil.getAuxDir(hotFile);
		if (aux != null)
		{
			final File newaux = FileUtil.getFileInDirectory(tmpDir, new File(aux.getName()));
			FileUtil.moveFile(aux, newaux);
			log.info("moving aux file " + aux + " to " + tmpDir);
			for (int i = 1; true; i++)
			{
				final File moved = FileUtil.moveFileToDir(newaux, tmpDir);
				if (moved != null)
				{
					log.info("moved aux dir " + aux + " to " + moved);
					break;
				}
				else
				{
					log.warn("could not move aux dir " + aux + " to " + tmpDir + " #" + i);
					if (i == 3 || !ThreadUtil.sleep(4242 * i))
						break;
				}
			}
		}
	}

	private synchronized File getTmpDir()
	{
		return FileUtil.getFileInDirectory(storage, new File("tmp." + nQueued.incrementAndGet()));
	}

	/**
	 * Setter for maxStore attribute. also sets maxAux which may be overridden later
	 *
	 * @param maxStore the maxStore to set
	 */
	void setMaxStore(final int maxStore)
	{
		this.maxStore = maxStore;
		this.maxAux = maxStore;
	}

	/**
	 * @param maxAuxDirs the auxFactor to set if >1 then aux files get zapped earlier
	 */
	public void setMaxAux(final int maxAuxDirs)
	{
		this.maxAux = maxAuxDirs;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "StorageHotFolderListener [" + (storage != null ? "storage=" + storage + ", " : "") + (errorStorage != null ? "errorStorage=" + errorStorage + ", " : "")
				+ (okStorage != null ? "okStorage=" + okStorage + ", " : "") + (parent != null ? "parent=" + parent + ", " : "") + "nHotOK=" + nHotOK + ", nHotError=" + nHotError
				+ ", nQueued=" + nQueued + ", maxStore=" + maxStore + ", maxAux=" + maxAux + "]";
	}

}
