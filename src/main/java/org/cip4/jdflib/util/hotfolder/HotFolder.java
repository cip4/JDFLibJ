/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.ThreadUtil;
import org.cip4.jdflib.util.file.FileSorter;
import org.cip4.jdflib.util.thread.MultiTaskQueue;

/**
 * a very simple hotfolder watcher subdirectories are ignored
 *
 * @author rainer prosi
 *
 */
public class HotFolder implements Runnable
{
	/**
	 * the default time time in milliseconds to wait for stabilization
	 */
	private static int defaultStabilizeTime = 2222; // time between reads in milliseconds -
	/**
	 * the time in milliseconds to wait for stabilization
	 */
	public int stabilizeTime = defaultStabilizeTime; // time between reads in milliseconds - also minimum length of non-modification
	private boolean interrupt = false; // if set to true, the watcher interupted and the thread ends
	private static int nThread = 0;

	/**
	 * @param maxConcurrent the maxConcurrent to set
	 */
	public void setMaxConcurrent(int maxConcurrent)
	{
		if (maxConcurrent > 42)
		{
			maxConcurrent = 42;
		}
		if (maxConcurrent != getMaxConcurrent())
		{
			if (maxConcurrent == 0)
			{
				if (taskQueue != null)
				{
					taskQueue.shutDown();
					taskQueue = null;
				}
			}
			else
			{
				if (taskQueue != null)
				{
					taskQueue.setMaxParallel(maxConcurrent);
				}
				else
				{
					taskQueue = MultiTaskQueue.getCreateQueue(getThreadName(false), maxConcurrent);
				}
			}
		}
	}

	/**
	 *
	 * @return
	 */
	public int getMaxConcurrent()
	{
		return taskQueue == null ? 0 : taskQueue.getMaxParallel();
	}

	private final File dir;
	private String allExtensions;

	/**
	 * @return the hot folder directory
	 */
	public File getDir()
	{
		return dir;
	}

	/**
	 * cache the extension list, null if any wildcard is accepted
	 * @return the comma separated list of extensions
	 */
	private String getAllExtensions()
	{
		if (allExtensions != null)
			return allExtensions;

		if (hfl == null)
		{
			return null;
		}
		final VString allextensions = new VString();
		for (int i = 0; i < hfl.size(); i++)
		{
			final Set<String> ext = hfl.get(i).extension;
			if (ext != null)
			{
				allextensions.addAll(ext);
			}
			else
			{
				// an extension=null exists, i.e. wildcard
				return null;
			}
		}
		allextensions.unify();
		allExtensions = allextensions.size() == 0 ? null : StringUtil.setvString(allextensions, ",", null, null);
		return allExtensions;
	}

	private long lastModified = -1;
	private final Vector<FileTime> lastFileTime;
	protected final Vector<ExtensionListener> hfl;
	private Thread runThread;
	MultiTaskQueue taskQueue;
	final Set<File> hfRunning;
	private final Log log;

	/**
	 * constructor for a simple hotfolder watcher that is automagically started in its own thread
	 *
	 * @param _dir the Directory to watch
	 * @deprecated - use the 3 parameter version
	 */
	@Deprecated
	public HotFolder(final File _dir)
	{
		this(_dir, null, null);
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
		allExtensions = null;
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
		log = LogFactory.getLog(getClass());
		taskQueue = null;
		dir = _dir;
		dir.mkdirs();
		dir.setWritable(true);

		lastFileTime = new Vector<FileTime>();
		hfl = new Vector<ExtensionListener>();
		hfRunning = new HashSet<File>();
		runThread = null;
		allExtensions = null;
		if (_hfl != null)
		{
			addListener(_hfl, ext);
		}
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
		if (!dir.canWrite())
		{
			log.error("Cannot use read only hot folder at");
		}
		String threadName = getThreadName(true);
		runThread = new Thread(this, threadName);
		runThread.setDaemon(true);
		interrupt = false;
		log.info("Starting hotfolder: " + threadName);
		lastModified = -1;
		if (taskQueue != null)
		{
			taskQueue = MultiTaskQueue.getCreateQueue(threadName, taskQueue.getMaxParallel());

		}
		runThread.start();
		hfRunning.clear();
	}

	/**
	 *
	 * @param increment
	 * @return
	 */
	String getThreadName(boolean increment)
	{
		String threadName = "HotFolder_" + nThread + "_" + dir.getAbsolutePath();
		if (increment)
		{
			nThread++;
		}
		return threadName;
	}

	/**
	 * stop this thread;
	 *
	 */
	public synchronized void stop()
	{
		interrupt = true;
		if (taskQueue != null)
		{
			taskQueue.shutDown();
		}
		if (runThread != null)
		{
			String name = runThread.getName();
			log.info("Stopping hot folder: " + name);
			ThreadUtil.notifyAll(runThread);
			log.info("Finished stopping hot folder: " + name);
			ThreadUtil.join(runThread, 10);
			runThread = null;
		}
		else
		{
			log.info("Stopping stopped hot folder: ");
		}
	}

	/**
	 * run the listener thread...
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		log.info("starting hot folder at: " + dir.getAbsolutePath());
		while (!interrupt)
		{
			final long lastMod = dir.lastModified();
			if (lastMod > lastModified || lastFileTime.size() > 0)
			// has the directory been touched?
			{
				lastModified = lastMod;
				File[] files = getHotFiles();
				if (files != null)
				{
					final int fileListLength = files.length;
					for (int i = 0; i < lastFileTime.size(); i++)
					{
						boolean found = false;
						final FileTime lftAt = lastFileTime.elementAt(i);
						for (int j = 0; !interrupt && j < fileListLength; j++)
						// loop over all matching files in the directory
						{
							final File fileJ = files[j];
							if (fileJ != null && fileJ.equals(lftAt.f))
							{
								found = found || processSingleFile(files, lftAt, j, fileJ);
							}
						}

						if (!found)
						{
							lastFileTime.remove(i--); // not there anymore - note the -- for und remove
						}
					}
					Vector<File> vf = ContainerUtil.toVector(files);
					for (int i = vf.size() - 1; i >= 0; i--)
					{
						if (vf.get(i) == null)
						{
							vf.remove(i);
						}
					}
					files = vf.toArray(new File[0]);
					files = new FileSorter(files).sortLastModified(false);

					for (File f : files) // the file is new - add to list for next check
					{
						lastFileTime.add(new FileTime(f));
					}
				}
			}

			if (!ThreadUtil.wait(runThread, stabilizeTime))
				break;
		}

		if (runThread != null)
		{
			runThread.interrupt();
		}
		log.info("completed hot folder at: " + dir.getAbsolutePath());
	}

	/**
	 *
	 * @return
	 */
	private File[] getHotFiles()
	{
		if (interrupt)
			return null;

		File[] files = FileUtil.listFilesWithExtension(dir, getAllExtensions());
		if (files != null)
		{
			for (int i = 0; i < files.length; i++)
			{
				if (!files[i].canWrite())
				{
					log.warn("ignoring read only file in hot folder: " + files[i]);
					files[i] = null;
				}
				else if (files[i].isDirectory())
				{
					files[i] = null;
				}
				else if (hfRunning.contains(files[i]))
				{
					files[i] = null;
				}
			}
		}
		return files;
	}

	private boolean processSingleFile(final File[] files, final FileTime lftAt, int j, final File fileJ)
	{
		boolean found = true;
		if (fileJ.lastModified() == lftAt.modified)
		{
			if (fileJ.exists())
			{
				HotFileRunner runner = new HotFileRunner(fileJ);
				if (taskQueue == null)
				{
					runner.run();
				}
				else
				{
					boolean queued = taskQueue.queue(runner);
					if (!queued)
					{
						taskQueue = MultiTaskQueue.getCreateQueue(getThreadName(false), taskQueue.getMaxParallel());
						taskQueue.queue(runner);
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

		files[j] = null; // this file has been processed, remove from list for performance
		return found;
	}

	/**
	 *
	 * @author rainer prosi
	 *
	 */
	class HotFileRunner implements Runnable
	{
		File fileJ;

		HotFileRunner(File fileJ)
		{
			super();
			if (hfRunning != null)
			{
				hfRunning.add(fileJ);
			}
			this.fileJ = fileJ;
		}

		/**
		 *
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			for (ExtensionListener xl : hfl)
			{
				try
				{
					xl.hotFile(fileJ); // exists and stabilized - call callbacks
				}
				catch (final Throwable x)
				{
					log.error("exception processing hot files", x);
				}
			}
			if (fileJ != null)
			{
				hfRunning.remove(fileJ);
			}
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return "HotFileRunner [fileJ=" + fileJ + "]";
		}
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

		@Override
		public String toString()
		{
			return f + " " + modified;
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
		final protected HotFolderListener fl;
		final protected Set<String> extension;

		protected ExtensionListener(final HotFolderListener _hfl, String ext)
		{
			fl = _hfl;
			ext = StringUtil.getNonEmpty(ext);
			if (ext != null)
			{
				extension = new HashSet<String>();
				VString vs = StringUtil.tokenize(ext, ",", false);
				for (String s : vs)
				{
					if (s.startsWith("."))
					{
						s = s.substring(1);
					}
					s = s.toLowerCase();
					extension.add(s);
				}
			}
			else
			{
				extension = null;
			}
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
			if (extension != null)
			{
				String fileExt = FileUtil.getExtension(file);
				if (fileExt != null)
				{
					fileExt = fileExt.toLowerCase();
					if (!extension.contains(fileExt))
					{
						// wrong extension
						return;
					}
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
		return getClass().getSimpleName() + " " + dir + " " + lastModified + " maxConcurrent=" + getMaxConcurrent();
	}

	/**
	 * @return the defaultStabilizeTime
	 */
	public static int getDefaultStabilizeTime()
	{
		return defaultStabilizeTime;
	}

	/**
	 * @param defaultStabilizeTime the defaultStabilizeTime to set
	 */
	public static void setDefaultStabilizeTime(int defaultStabilizeTime)
	{
		HotFolder.defaultStabilizeTime = defaultStabilizeTime;
	}

	/**
	 * @return the stabilizeTime
	 */
	public int getStabilizeTime()
	{
		return stabilizeTime;
	}

	/**
	 * @param stabilizeTime the stabilizeTime to set
	 */
	public void setStabilizeTime(int stabilizeTime)
	{
		this.stabilizeTime = stabilizeTime;
	}
}
