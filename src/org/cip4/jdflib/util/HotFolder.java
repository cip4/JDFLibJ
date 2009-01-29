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

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;

/**
 * a very simple hotfolder watcher subdirectories are ignored
 * 
 * @author prosirai
 * 
 */
public class HotFolder implements Runnable
{
	/**
	 * the default time time in milliseconds to wait for stabilization
	 */
	public static int defaultStabilizeTime = 2222; // time between reads in milliseconds -
	/**
	 * the time in milliseconds to wait for stabilization
	 */
	public int stabilizeTime = defaultStabilizeTime; // time between reads in milliseconds -
	// also minimum length of non-modification
	private boolean interrupt = false; // if set to true, the watcher interupted
	// and the thread ends
	private static int nThread = 0;

	private final File dir;

	/**
	 * @return the hot folder directory
	 */
	public File getDir()
	{
		return dir;
	}

	private String getAllExtensions()
	{
		if (hfl == null)
		{
			return null;
		}
		final VString allextensions = new VString();
		for (int i = 0; i < hfl.size(); i++)
		{
			final String ext = hfl.get(i).extension;
			if (ext != null)
			{
				allextensions.add(ext);
			}

		}
		allextensions.unify();
		return allextensions.size() == 0 ? null : StringUtil.setvString(allextensions, ",", null, null);
	}

	private long lastModified = -1;
	private final Vector<FileTime> lastFileTime;
	protected final Vector<ExtensionListener> hfl;
	private Thread runThread;

	/**
	 * constructor for a simple hotfolder watcher that is automagically started in its own thread
	 * 
	 * @param _dir the Directory to watch
	 * @param ext the extension filter - case is ignored and lists of extensions may be specified as a comma separated list e.g. ".txt,.xml"
	 * @param _hfl the listener callback
	 */
	public HotFolder(final File _dir)
	{
		dir = _dir;
		dir.mkdirs();
		lastFileTime = new Vector<FileTime>();
		hfl = new Vector<ExtensionListener>();
		runThread = null;
		restart();
	}

	/**
	 * @param _hfl
	 * @param ext
	 */
	public synchronized void addListener(final HotFolderListener _hfl, final String ext)
	{
		if (hfl != null)
		{
			hfl.add(new ExtensionListener(_hfl, ext));
		}
		lastModified = 0;
	}

	/**
	 * constructor for a simple hotfolder watcher that is automagically started in its own thread
	 * 
	 * @param _dir the Directory to watch
	 * @param ext the extension filter - case is ignored and lists of extensions may be specified as a comma separated list e.g. ".txt,.xml"
	 * @param _hfl the listener callback
	 */
	public HotFolder(final File _dir, final String ext, final HotFolderListener _hfl)
	{
		dir = _dir;
		dir.mkdirs();
		lastFileTime = new Vector<FileTime>();
		hfl = new Vector<ExtensionListener>();
		addListener(_hfl, ext);
		runThread = null;
		restart();
	}

	/**
	 * restart the thread
	 */
	public synchronized void restart()
	{
		if (runThread != null)
		{
			stop();
		}
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
					// kill the old thread with extreme prejudice -otherwise we may have multiple concurring hf watcher threads
					runThread.join();
				}
				catch (final InterruptedException x)
				{
					// nop
				}
			}
			runThread = null;
		}
	}

	/**
	 * run the listener thread...
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{

		while (!interrupt)
		{
			final long lastMod = dir.lastModified();
			if (lastMod > lastModified || lastFileTime.size() > 0)
			// has the directory been touched?
			{
				lastModified = lastMod;
				final File[] files = FileUtil.listFilesWithExtension(dir, getAllExtensions());
				final int fileListLength = files != null ? files.length : 0;

				for (int i = lastFileTime.size() - 1; i >= 0; i--)
				{
					boolean found = false;
					for (int j = 0; j < fileListLength; j++)
					// loop over all matching files in the directory
					{
						if (files != null)
						{
							final FileTime lftAt = lastFileTime.elementAt(i);
							final File fileJ = files[j];
							if (fileJ != null && fileJ.equals(lftAt.f))
							{
								found = true;
								if (fileJ.lastModified() == lftAt.modified)
								{
									if (fileJ.exists())
									{
										for (int k = 0; k < hfl.size(); k++)
										{
											try
											{
												hfl.get(k).hotFile(fileJ); // exists and stabilized - call callbacks
											}
											catch (final Exception x)
											{

											}
										}
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
								// remove from list for performance
							}
						}
					}

					if (!found)
					{
						lastFileTime.remove(i); // not there anymore
					}
				}

				if (files != null)
				{
					for (int i = 0; i < fileListLength; i++) // the file is new -
					// add to list for next check
					{
						if (files[i] != null)
						{
							lastFileTime.add(new FileTime(files[i]));
						}
					}
				}
			}

			ThreadUtil.wait(runThread, stabilizeTime);
		}

		runThread.interrupt();
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

		protected FileTime(final File _f)
		{
			f = _f;
			modified = -1;
		}
	}

	/**
	 * simple container class that retains the last known mod date of a file
	 * 
	 * @author prosirai
	 * 
	 */
	protected class ExtensionListener
	{
		protected HotFolderListener fl;
		protected String extension;

		protected ExtensionListener(final HotFolderListener _hfl, String ext)
		{
			fl = _hfl;
			ext = StringUtil.getNonEmpty(ext);
			if (ext != null)
			{
				if (ext.startsWith("."))
				{
					ext = ext.substring(1);
				}
				ext = ext.toLowerCase();
			}
			extension = ext;
		}

		/**
		 * @param file
		 */
		public void hotFile(final File file)
		{
			if (file == null)
			{
				return;
			}
			if (!KElement.isWildCard(extension))
			{
				final String fileExt = FileUtil.getExtension(file);
				if (!extension.equalsIgnoreCase(fileExt))
				{
					return;
				}
			}
			fl.hotFile(file);

		}

	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "HotFolder: " + dir + " " + lastModified;
	}

}
