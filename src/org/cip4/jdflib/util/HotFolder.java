/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.util;

import java.io.File;
import java.util.Vector;

/**
 * a very simple hotfolder watcher subdirectories are ignored
 * 
 * @author prosirai
 * 
 */
public class HotFolder implements Runnable
{
	public int stabilizeTime = 1000; // time between reads in milliseconds -
	// also minimum lenght of
	// non-modification
	private boolean interrupt = false; // if set to true, the watcher interupted
	// and the thread ends
	private static int nThread = 0;

	private File dir;
	private long lastModified = -1;
	private Vector<FileTime> lastFileTime;
	private HotFolderListener hfl;
	private String extension;
	private Thread runThread;

	/**
	 * constructor for a simple hotfolder watcher that is automagically started in its own thread
	 * 
	 * @param _dir the Directory to watch
	 * @param ext the extension filter - case is ignored and lists of extensions may be specified as a comma separated
	 *            list e.g. ".txt,.xml"
	 * @param _hfl
	 */
	public HotFolder(File _dir, String ext, HotFolderListener _hfl)
	{
		dir = _dir;
		extension = ext;
		lastFileTime = new Vector<FileTime>();
		hfl = _hfl;
		runThread = null;
		restart();
	}

	public synchronized void restart()
	{
		if (runThread != null)
			stop();
		runThread = new Thread(this, "HotFolder_" + nThread++);
		interrupt = false;
		runThread.start();
	}

	/**
	 * stop this thread;
	 * 
	 */
	public void stop()
	{
		interrupt = true;
		if (runThread != null)
		{
			synchronized (runThread)
			{
				runThread.notifyAll();

				try
				{
					runThread.join(); // kill the old thread with extreme
					// prejudice -
					// otherwise we may have multiple concurring hf watcher
					// threads
				}
				catch (InterruptedException x)
				{
					// nop
				}
			}
			runThread = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{
		while (!interrupt)
		{
			long lastMod = dir.lastModified();
			if (lastMod > lastModified || lastFileTime.size() > 0) // has the
			// directory
			// been
			// touched?
			{
				lastModified = lastMod;
				File[] files = FileUtil.listFilesWithExtension(dir, extension);
				int fileListLength = files != null ? files.length : 0;

				for (int i = lastFileTime.size() - 1; i >= 0; i--)
				{
					boolean found = false;
					for (int j = 0; j < fileListLength; j++) // loop over all
					// matching
					// files in the
					// directory
					{
						final FileTime lftAt = lastFileTime.elementAt(i);
						if (files[j] != null && files[j].equals(lftAt.f))
						{
							found = true;
							if (files[j].lastModified() == lftAt.modified)
							{
								if (files[j].exists())
								{
									hfl.hotFile(files[j]); // exists and
									// stabilized - call
									// callback
								}
								else
								{
									found = false;
								}
							}
							else
							{
								lftAt.modified = files[j].lastModified();
							}
							files[j] = null; // this file has been processed,
							// remove from list for
							// performance
						}
					}
					if (!found)
					{
						lastFileTime.remove(i); // not there anymore
					}
				}
				for (int i = 0; i < fileListLength; i++) // the file is new -
				// add to list for
				// nextr check
				{
					if (files[i] != null)
						lastFileTime.add(new FileTime(files[i]));
				}
			}
			StatusCounter.sleep(stabilizeTime);
		}
		Thread.currentThread().interrupt();

	}

	/**
	 * simple container class that retains the last known mod date of a file
	 * 
	 * @author prosirai
	 * 
	 */
	protected class FileTime
	{
		protected File f;
		protected long modified;

		protected FileTime(File _f)
		{
			f = _f;
			modified = -1;
		}
	}

}
