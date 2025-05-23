/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * KString.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.util.hotfolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.FileUtil;

/**
 * a hotfolder that handles storage of "hotfolded" data
 *
 * @author rainer prosi
 *
 */
public class StorageHotFolder
{
	final HotFolder hf; // the active hot folder
	final File storageDir;
	final Log log;
	int retry;
	boolean synchronous;
	boolean processAux;

	public boolean isProcessAux()
	{
		return processAux;
	}

	public void setProcessAux(final boolean processAux)
	{
		this.processAux = processAux;
	}

	/**
	 * @return the hotfolder directory
	 */
	public File getHfDirectory()
	{
		return hf.getDir();
	}

	final List<StorageHotFolderListener> listenerImpl;

	/**
	 *
	 * constructor for a simple queue based hotfolder watcher that is automagically started in its own thread
	 *
	 * @param _hotFolderDir the hot folder directory to watch
	 * @param storageDir the storage directory where hot files are moved to
	 * @param ext the comma separated list of file extensions that are moved - if null no filtering
	 * @param hfListener callback that receives the generated JMF - the location of the stored file will be found in the standard command parameters
	 */
	public StorageHotFolder(final File _hotFolderDir, final File storageDir, final String ext, final HotFolderListener hfListener)
	{
		super();
		synchronous = false;
		processAux = true;
		retry = 1;
		log = LogFactory.getLog(getClass());
		this.storageDir = storageDir;
		storageDir.mkdirs(); // just in case
		storageDir.setWritable(true);
		if (!storageDir.isDirectory())
		{
			log.error("Storage Directory is not a directory: " + storageDir.getAbsolutePath());
		}
		else
		{
			moveFromTemp(_hotFolderDir);
		}
		listenerImpl = new ArrayList<>();
		hf = new HotFolder(_hotFolderDir, null, null);
		if (hfListener != null)
		{
			addListener(hfListener, ext);
			restart();
		}
	}

	/**
	 *
	 * @param _hotFolderDir
	 */
	private void moveFromTemp(final File _hotFolderDir)
	{
		final File[] junk = storageDir.listFiles();
		if (junk != null && junk.length > 0)
		{
			log.warn("moving " + junk.length + " legacy temp files from: " + storageDir.getPath());
			for (final File f : junk)
			{
				final File fMoved = FileUtil.moveFileToDir(f, _hotFolderDir);
				if (fMoved == null)
				{
					log.warn("cannot move " + f.getName() + " from temp dir");
				}
				else
				{
					log.info("moving " + f.getName() + " from temp dir to main hot folder");
				}
			}
		}
	}

	/**
	 * stop this hot folder
	 *
	 */
	public void stop()
	{
		hf.stop();
	}

	/**
	 * restart this hot folder, creates a new listener thread and stops the old one
	 *
	 */
	public void restart()
	{
		hf.restart();
	}

	/**
	 *
	 * add a listener that also stores
	 *
	 * @param _hfl
	 * @param ext
	 * @return the newly added listener
	 */
	public StorageHotFolderListener addListener(final HotFolderListener _hfl, final String ext)
	{
		final StorageHotFolderListener storageListener = new StorageHotFolderListener(storageDir, _hfl, this);
		hf.addListener(storageListener, ext);
		listenerImpl.add(storageListener);
		return storageListener;
	}

	/**
	 *
	 * @param i
	 * @return
	 */
	public StorageHotFolderListener getListener(final int i)
	{
		return listenerImpl.get(i);
	}

	/**
	 *
	 * set the directory for successful done
	 *
	 * @param ok the local directory for ok files in the input hot folder
	 */
	public void setOKStorage(final File ok)
	{
		for (final StorageHotFolderListener shfl : listenerImpl)
			shfl.setOKStorage(FileUtil.getFileInDirectory(getHfDirectory(), ok));
	}

	/**
	 *
	 * copy any files to the ok or error files as set by the folder properties
	 *
	 * @param storedFile
	 * @param ok
	 */
	public void copyCompleted(final File storedFile, final boolean ok)
	{
		copyCompleted(storedFile, ok, null);
	}

	/**
	 *
	 * copy any files to the ok or error files as set by the folder properties
	 *
	 * @param storedFile
	 * @param ok
	 */
	public void copyCompleted(final File storedFile, final boolean ok, final Throwable t)
	{
		listenerImpl.get(0).copyCompleted(storedFile, ok, t);
	}

	/**
	 *
	 * set the directory for error done
	 *
	 * @param error the local directory for error files in the input hot folder
	 */
	public void setErrorStorage(final File error)
	{
		for (final StorageHotFolderListener shfl : listenerImpl)
			shfl.setErrorStorage(FileUtil.getFileInDirectory(getHfDirectory(), error));
	}

	/**
	 * Setter for maxStore attribute.
	 *
	 * @param maxStore the maxStore to set
	 */
	public void setMaxStore(final int maxStore)
	{
		for (final StorageHotFolderListener shfl : listenerImpl)
			shfl.setMaxStore(maxStore);
	}

	/**
	 * Setter for maxStore attribute.
	 *
	 * @param maxStore the setMaxAux to set
	 */
	public void setMaxAux(final int maxStore)
	{
		for (final StorageHotFolderListener shfl : listenerImpl)
			shfl.setMaxAux(maxStore);
	}

	/**
	 * @param maxConcurrent
	 * @see org.cip4.jdflib.util.hotfolder.HotFolder#setMaxConcurrent(int)
	 */
	public void setMaxConcurrent(final int maxConcurrent)
	{
		hf.setMaxConcurrent(maxConcurrent);
	}

	/**
	 * @param stabilizeTime
	 * @see org.cip4.jdflib.util.hotfolder.HotFolder#setStabilizeTime(int)
	 */
	public void setStabilizeTime(final int stabilizeTime)
	{
		hf.setStabilizeTime(stabilizeTime);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + " [hf=" + hf + ", storageDir=" + storageDir + " retry=" + retry + " synch=" + synchronous + " aux=" + processAux;
	}

	/**
	 * @return the retry
	 */
	protected int getRetry()
	{
		return retry;
	}

	/**
	 * @param retry the retry to set 1= no retry
	 */
	public void setRetry(int retry)
	{
		if (retry < 1)
			retry = 1;
		this.retry = retry;
	}

	/**
	 * @return
	 * @see org.cip4.jdflib.util.hotfolder.HotFolder#getMaxConcurrent()
	 */
	public int getMaxConcurrent()
	{
		return hf.getMaxConcurrent();
	}

	/**
	 * @return
	 * @see org.cip4.jdflib.util.hotfolder.HotFolder#getStabilizeTime()
	 */
	public int getStabilizeTime()
	{
		return hf.getStabilizeTime();
	}

	public boolean isSynchronous()
	{
		return synchronous;
	}

	public void setSynchronous(final boolean synchronous)
	{
		this.synchronous = synchronous;
	}

	public void setMaxCheck(final int maxCheck)
	{
		hf.setMaxCheck(maxCheck);
	}

}
