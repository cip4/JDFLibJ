/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of 
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
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * a hotfolder that handles storage of "hotfolded" data
 * 
 * @author prosirai
 * 
 */
public class StorageHotFolder
{
	protected final HotFolder hf; // the active hot folder
	private final File storageDir;
	private int nInc;
	protected final Log log;

	/**
	 * @return the hotfolder directory
	 */
	public File getHfDirectory()
	{
		return hf.getDir();
	}

	private final Vector<StorageHotFolderListener> listenerImpl;

	/**
	 * 
	 * constructor for a simple queue based hotfolder watcher that is automagically started in its own thread
	 * 
	 * @param _hotFolderDir the hot folder directory to watch
	 * @param storageDir the storage directory where hot files are moved to
	 * @param ext the file extensions that are moved - if null no filtering
	 * @param hfListener callback that receives the generated JMF - the location of the stored file will be found in the standard command parameters
	 */
	public StorageHotFolder(final File _hotFolderDir, final File storageDir, final String ext, final HotFolderListener hfListener)
	{
		super();
		log = LogFactory.getLog(getClass());
		this.storageDir = storageDir;
		storageDir.mkdirs(); // just in case
		if (!storageDir.isDirectory())
		{
			log.error("Storage Directory is not a directory: " + storageDir.getAbsolutePath());
		}
		else
		{
			moveFromTemp(_hotFolderDir);
		}
		listenerImpl = new Vector<StorageHotFolderListener>();
		listenerImpl.add(new StorageHotFolderListener(storageDir, hfListener, this));
		hf = new HotFolder(_hotFolderDir, ext, listenerImpl.get(0));
		nInc = 0;
	}

	/**
	 * TODO Please insert comment!
	 * @param _hotFolderDir
	 */
	private void moveFromTemp(File _hotFolderDir)
	{
		File[] junk = storageDir.listFiles();
		if (junk != null && junk.length > 0)
		{
			log.warn("moving " + junk.length + " legacy temp files from: " + storageDir.getPath());
			for (File f : junk)
			{
				File fMoved = FileUtil.moveFileToDir(f, _hotFolderDir);
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
	 * restart this hot folder, create s a new listner thread and stops the old one
	 * 
	 */
	public void restart()
	{
		hf.restart();
	}

	/**
	 * 
	 * add a listener that also stores
	 * @param _hfl
	 * @param ext
	 * @return the newly added listener
	 */
	public StorageHotFolderListener addListener(HotFolderListener _hfl, String ext)
	{
		StorageHotFolderListener storageListener = new StorageHotFolderListener(storageDir, _hfl, this);
		hf.addListener(storageListener, ext);
		listenerImpl.add(storageListener);
		return storageListener;
	}

	/**
	 *  
	 * @return the file name increment in 6 digits
	 */
	public synchronized String getFileNameIncrement()
	{
		return StringUtil.sprintf("%06i", "" + nInc++);
	}

	/**
	 * 
	 * set the directory for successful done
	 * @param ok the local directory for ok files in the input hot folder
	 */
	public void setOKStorage(File ok)
	{
		for (StorageHotFolderListener shfl : listenerImpl)
			shfl.setOKStorage(FileUtil.getFileInDirectory(getHfDirectory(), ok));
	}

	/**
	 * 
	 *copy any files to the ok or error files as set by the folder properties
	 * @param storedFile 
	 * @param ok
	 */
	public void copyCompleted(final File storedFile, boolean ok)
	{
		listenerImpl.get(0).copyCompleted(storedFile, ok);
	}

	/**
	 * 
	 * set the directory for error done
	 * @param error the local directory for error files in the input hot folder
	 */
	public void setErrorStorage(File error)
	{
		for (StorageHotFolderListener shfl : listenerImpl)
			shfl.setErrorStorage(FileUtil.getFileInDirectory(getHfDirectory(), error));
	}

	/**
	 * Setter for maxStore attribute.
	 * @param maxStore the maxStore to set
	 */
	public void setMaxStore(int maxStore)
	{
		for (StorageHotFolderListener shfl : listenerImpl)
			shfl.setMaxStore(maxStore);
	}

}
