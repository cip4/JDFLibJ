/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2021 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.file.FileSorter;

/**
 * a very simple hotfolder watcher subdirectories are ignored
 *
 * @author rainer prosi
 *
 */
public class HotFolder
{
	/**
	 * the default time time in milliseconds to wait for stabilization
	 */
	private static int defaultStabilizeTime = 6666; // time between reads in milliseconds -
	/**
	 * the time in milliseconds to wait for stabilization
	 */
	public int stabilizeTime = defaultStabilizeTime; // time between reads in milliseconds - also minimum length of non-modification

	/**
	 * @param maxConcurrent the maxConcurrent to set note will only add
	 */
	public void setMaxConcurrent(final int maxConcurrent)
	{
		HotFolderRunner.getCreateTherunner().setMaxConcurrent(maxConcurrent);
	}

	/**
	 *
	 * @param i
	 * @return
	 */
	public ExtensionListener getListener(final int i)
	{
		return ContainerUtil.get(hfl, i);
	}

	/**
	 *
	 * @return
	 */
	public int getMaxConcurrent()
	{
		final HotFolderRunner r = HotFolderRunner.getTherunner();
		return r == null ? 0 : r.getMaxConcurrent();
	}

	private final File dir;
	private String allExtensions;

	/**
	 * run the listener thread...
	 *
	 * @see java.lang.Runnable#run()
	 */
	boolean loop()
	{
		final long t0 = System.currentTimeMillis();
		final long lastMod = dir.lastModified();
		final int n = lastFileTime.size();
		if (lastMod > lastModified || n > 0 || (t0 - lastModified) < 42000)
		// has the directory been touched?
		{
			lastModified = lastMod;
			File[] files = getHotFiles();
			if ((lastFileTime.size() > 0) && (files != null))
			{
				final int fileListLength = files.length;
				for (int i = 0; i < lastFileTime.size(); i++)
				{
					boolean found = false;
					final FileTime lftAt = lastFileTime.get(i);
					for (int j = 0; j < fileListLength; j++)
					// loop over all matching files in the directory
					{
						final File fileJ = files[j];
						if (fileJ != null && fileJ.equals(lftAt.f))
						{
							final boolean processed = processSingleFile(lftAt);
							if (processed)
							{
								found = processed;
								files[j] = null;
							}
						}
					}

					if (!found)
					{
						final FileTime ft = lastFileTime.get(i);
						if (!ft.exists())// not there anymore - note the -- for undo remove
						{
							lastFileTime.remove(i--);
						}
					}
				}
			}

			if (files != null)
			{
				final List<File> vf = ContainerUtil.toArrayList(files);
				for (int i = vf.size() - 1; i >= 0; i--)
				{
					if (vf.get(i) == null)
					{
						vf.remove(i);
					}
				}
				if (!vf.isEmpty())
				{
					files = vf.toArray(new File[0]);
					files = new FileSorter(files).sortLastModified(false);

					for (final File f : files) // the file is new - add to list for next check
					{
						lastFileTime.add(new FileTime(f, false));
					}
				}
			}
		}
		return lastFileTime.size() != n;
	}

	/**
	 * @return the hot folder directory
	 */
	public File getDir()
	{
		return dir;
	}

	/**
	 * cache the extension list, null if any wildcard is accepted
	 *
	 * @return the comma separated list of extensions
	 */
	String getAllExtensions()
	{
		if (allExtensions != null)
			return allExtensions;

		if (hfl == null)
		{
			return null;
		}
		final StringArray allextensions = new StringArray();
		try
		{
			for (final ExtensionListener element : hfl)
			{
				final Set<String> ext = element.extension;
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
			allExtensions = allextensions.getString(JDFConstants.COMMA, null, null);
			return allExtensions;
		}
		catch (final Exception x)
		{
			// we had a minor issue - better just check all and try again next time
			return null;
		}
	}

	private long lastModified = -1;
	private ArrayList<FileTime> lastFileTime;

	/**
	 * @return the lastFileTime
	 */
	ArrayList<FileTime> getLastFileTime()
	{
		return lastFileTime;
	}

	/**
	 * @param lastFileTime the lastFileTime to set
	 */
	void setLastFileTime(final ArrayList<FileTime> lastFileTime)
	{
		this.lastFileTime = lastFileTime;
	}

	protected final ArrayList<ExtensionListener> hfl;
	final Set<File> hfRunning;
	static final private Log log = LogFactory.getLog(HotFolder.class);

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
		dir = _dir;
		dir.mkdirs();
		dir.setWritable(true);

		lastFileTime = new ArrayList<>();
		hfl = new ArrayList<>();
		hfRunning = new HashSet<>();
		allExtensions = null;
		HotFolderRunner.getCreateTherunner();
		if (_hfl != null)
		{
			addListener(_hfl, ext);
			restart();
		}
	}

	/**
	 * restart the thread
	 */
	public synchronized void restart()
	{
		stop();
		if (dir.canWrite())
		{
			log.info("Restarting " + toString());
		}
		else
		{
			log.error("Cannot use read only hot folder at " + this);
		}
		final HotFolderRunner r = HotFolderRunner.getCreateTherunner();
		r.add(this);
		lastModified = -1;
		hfRunning.clear();
	}

	/**
	 * stop this thread;
	 *
	 */
	public synchronized void stop()
	{
		final HotFolderRunner r = HotFolderRunner.getTherunner();
		if (r != null && r.contains(this))
		{
			r.remove(this);
			log.info("stopped hot folder at: " + dir.getAbsolutePath());
		}
	}

	/**
	 *
	 * @return
	 */
	File[] getHotFiles()
	{
		final HotFolderRunner r = HotFolderRunner.getTherunner();
		if (r == null)
			return null;

		final File[] files = FileUtil.listFilesWithExtension(dir, getAllExtensions());
		int n = 0;
		if (files != null)
		{
			for (int i = 0; i < files.length; i++)
			{
				final File file = files[i];
				if (!file.canWrite())
				{
					log.warn("ignoring read only file in hot folder: " + file);
					files[i] = null;
				}
				else if (file.isDirectory() || file.isHidden())
				{
					files[i] = null;
				}
				else if (hfRunning.contains(file))
				{
					files[i] = null;
				}
				else
				{
					n++;
				}
			}
		}
		return n == 0 ? null : files;
	}

	boolean processSingleFile(final FileTime lftAt)
	{
		boolean found = true;
		if (lftAt.sameModified() && ((lftAt.modified + stabilizeTime) < System.currentTimeMillis()))
		{
			lastFileTime.remove(lftAt);
			HotFolderRunner.getTherunner().runFile(new HotFileRunner(lftAt.f));
		}
		else if (!lftAt.exists())
		{
			lastFileTime.remove(lftAt);
			found = false;
		}
		else
		{
			lftAt.updateModified();
		}

		return found;
	}

	/**
	 *
	 * @author rainer prosi
	 *
	 */
	class HotFileRunner implements Runnable
	{
		final File fileJ;

		HotFileRunner(final File fileJ)
		{
			super();
			hfRunning.add(fileJ);
			this.fileJ = fileJ;
		}

		/**
		 *
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			for (final ExtensionListener xl : hfl)
			{
				try
				{
					xl.hotFile(fileJ); // exists and stabilized - call callbacks
				}
				catch (final Throwable x)
				{
					log.error("exception processing hot file", x);
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + " " + dir + " " + lastModified;
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
	public static void setDefaultStabilizeTime(final int defaultStabilizeTime)
	{
		if (defaultStabilizeTime > 0)
		{
			HotFolder.defaultStabilizeTime = defaultStabilizeTime;
		}
	}

	/**
	 * @return the stabilizeTime
	 */
	public int getStabilizeTime()
	{
		return stabilizeTime;
	}

	/**
	 * @param stabilizeTime the stabilizeTime to set in milliseconds - we always ensure at least 2000 because some OS have 1000 file modified garnularity
	 */
	public void setStabilizeTime(final int stabilizeTime)
	{
		if (stabilizeTime >= 2000)
		{
			this.stabilizeTime = stabilizeTime;
		}
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return dir == null ? 0 : dir.hashCode();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj)
	{
		if (dir == null)
			return obj == null;
		if (!(obj instanceof HotFolder))
			return false;
		return dir.equals(((HotFolder) obj).dir);
	}

}
