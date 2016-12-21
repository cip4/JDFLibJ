/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of 
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of 
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of 
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration 
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software 
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG 
 * copyright (c) 1999-2001, Agfa-Gevaert N.V. 
 *  
 * For more information on The International Cooperation for the 
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *  
 * 
 */
package org.cip4.jdflib.util.hotfolder;

import java.io.File;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.RollingBackupFile;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.cip4.jdflib.util.file.FileSorter;

/**
 * hotfolder listener that also moves to a done dir
 * @author rainer prosi
 * @date Feb 14, 2011
 */
class StorageHotFolderListener implements HotFolderListener
{
	private final File storage;
	private File errorStorage;
	private File okStorage;
	private final StorageHotFolder parent;
	private final Log log;
	private int nHotOK;
	private int nHotError;
	private int maxStore;

	/**
	 * @param storageDir 
	 * @param hfListener 
	 * 
	 */
	StorageHotFolderListener(File storageDir, HotFolderListener hfListener, StorageHotFolder parent)
	{
		super();
		nHotOK = nHotError = 0;
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
	 * @param ok
	 */
	void setOKStorage(File ok)
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
	 * @param error
	 */
	void setErrorStorage(File error)
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

	/**
	 * @see org.cip4.jdflib.util.hotfolder.HotFolderListener#hotFile(java.io.File)
	 */
	@Override
	public boolean hotFile(File hotFile)
	{
		log.info("processing hot file: " + hotFile);
		final File storedFile = getStoredFile(hotFile);
		if (storedFile == null)
		{
			log.warn("snafu retrieving file " + hotFile.getAbsolutePath());
			copyCompleted(hotFile, false);
			return false; // not good
		}
		boolean b = theListener.hotFile(storedFile);
		copyCompleted(storedFile, b);
		return b;
	}

	/**
	 * copy to ok or completed
	 * @param storedFile the file to move 
	 * @param bOK if true, ok else error
	 */
	void copyCompleted(final File storedFile, boolean bOK)
	{
		File auxFile = getAuxDir(storedFile);

		if (bOK)
		{
			if (okStorage != null)
			{
				File backup = FileUtil.getFileInDirectory(okStorage, new File(storedFile.getName()));
				RollingBackupFile roller = new RollingBackupFile(backup, 10);
				roller.setWantExtension(true);
				roller.getNewFile();
				File copied = FileUtil.moveFileToDir(storedFile, okStorage);
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
						File auxbackup = FileUtil.getFileInDirectory(okStorage, new File(auxFile.getName()));
						RollingBackupFile rollingBackupFile = new RollingBackupFile(auxbackup, 10);
						rollingBackupFile.setWantExtension(true);
						rollingBackupFile.getNewFile();
						FileUtil.moveFileToDir(auxFile, okStorage);
					}
				}
				cleanup(bOK);
			}
			else
			{
				boolean ok = FileUtil.forceDelete(storedFile);
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
				File backup = FileUtil.getFileInDirectory(errorStorage, new File(storedFile.getName()));
				RollingBackupFile roller = new RollingBackupFile(backup, 10);
				roller.setWantExtension(true);
				roller.getNewFile();
				File copied = FileUtil.moveFileToDir(storedFile, errorStorage);
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
						File auxbackup = FileUtil.getFileInDirectory(errorStorage, new File(auxFile.getName()));
						RollingBackupFile rollingBackupFile = new RollingBackupFile(auxbackup, 10);
						rollingBackupFile.setWantExtension(true);
						rollingBackupFile.getNewFile();
						FileUtil.moveFileToDir(auxFile, errorStorage);
					}
				}

				cleanup(bOK);
			}
			else
			{
				boolean ok = FileUtil.forceDelete(storedFile);
				if (!ok)
					log.warn("failed to delete temporary file " + storedFile.getAbsolutePath());
				FileUtil.deleteAll(auxFile);

			}
		}
	}

	protected void handleBad(final File storedFile, boolean bOK)
	{
		if (bOK)
			log.warn("could not move ok " + storedFile + " to " + okStorage.getAbsolutePath());
		else
			log.warn("could not move error " + storedFile + " to " + errorStorage.getAbsolutePath());

		File auxFile = getAuxDir(storedFile);
		FileUtil.deleteAll(auxFile);

		boolean bZapp = storedFile.delete();
		if (bZapp)
		{
			log.warn("utterly removed hot file: " + storedFile);
		}
		else
		{
			log.error("cannot process hot file: " + storedFile);
		}
	}

	/**
	 * remove old stuff from ok and error folder
	 * @param bOK if true cleanup the ok folder, else the error folder
	 */
	private void cleanup(boolean bOK)
	{
		int nHot = bOK ? nHotOK++ : nHotError++;
		if (nHot % 13 == 0)
		{
			FileSorter fs = new FileSorter(bOK ? okStorage : errorStorage);
			File[] list = fs.sortLastModified(true);
			Vector<File> vList = new Vector<File>();
			for (File f : list)
			{
				if (!f.isDirectory())
				{
					vList.add(f);
				}
			}
			for (int i = maxStore; i < vList.size(); i++)
			{
				File hotFile = vList.get(i);
				File aux = getAuxDir(hotFile);
				boolean ok = FileUtil.forceDelete(hotFile);
				if (!ok)
				{
					log.warn("failed to delete temporary file " + hotFile.getAbsolutePath());
				}
				if (aux != null)
				{
					ok = FileUtil.deleteAll(aux);
					if (!ok)
					{
						log.warn("failed to delete temporary directory " + aux.getAbsolutePath());
					}
				}
			}
		}
	}

	/**
	 * get any auxilliary directory
	 * @param hotFile
	 * @return
	 */
	File getAuxDir(File hotFile)
	{
		if (hotFile == null)
		{
			return null;
		}
		String name = hotFile.getName();
		String base = UrlUtil.newExtension(name, null);
		if (StringUtil.getNonEmpty(base) == null)
			return null;
		File parentDir = hotFile.getParentFile();
		File auxFile = FileUtil.getFileInDirectory(parentDir, new File(base));
		if (!auxFile.isDirectory())
		{
			auxFile = null;
			File[] v = FileUtil.listFilesWithExpression(parentDir, base + ".*");
			if (v != null)
			{
				for (File f : v)
				{
					if (!f.getName().equals(name) && f.isDirectory())
					{
						auxFile = f;
						break;
					}
				}
			}
		}
		return auxFile;
	}

	private File getStoredFile(File hotFile)
	{
		String hotFileName = hotFile.getName();
		File newAbsoluteFile = FileUtil.getFileInDirectory(storage, new File(hotFileName));
		boolean ok = FileUtil.moveFile(hotFile, newAbsoluteFile);
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
			File aux = getAuxDir(hotFile);
			FileUtil.moveFileToDir(aux, storage);
		}
		return ok ? newAbsoluteFile : null;
	}

	/**
	 * Setter for maxStore attribute.
	 * @param maxStore the maxStore to set
	 */
	void setMaxStore(int maxStore)
	{
		this.maxStore = maxStore;
	}

	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "StorageHotFolderListener: ok=" + okStorage + " error=" + errorStorage + " " + parent;
	}

}
