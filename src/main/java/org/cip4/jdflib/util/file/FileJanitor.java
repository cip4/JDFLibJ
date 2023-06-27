/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.util.file;

import java.io.File;
import java.io.FileFilter;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.JDFDate;

/**
 * class to clean up files and directories the directories are retained longer since java knows only last modified. This is actually a good thing
 *
 * @author rainer prosi
 * @date Dec 8, 2011
 */
public class FileJanitor
{

	class AgeFilter implements FileFilter
	{
		final long t0;
		final long age;

		AgeFilter(final long age)
		{
			t0 = System.currentTimeMillis();
			this.age = age;
		}

		@Override
		public boolean accept(final File arg0)
		{
			return arg0.lastModified() + age < t0;
		}

	}

	class KillFilter implements FileFilter
	{
		private final FileFilter baseFilter;

		/**
		 *
		 * @param f
		 */
		KillFilter(final FileFilter f)
		{
			this.baseFilter = f;
		}

		/**
		 * @see java.io.FileFilter#accept(java.io.File)
		 */
		@Override
		public boolean accept(final File file)
		{
			boolean accept = baseFilter.accept(file);
			if (delEmpty && !accept && file.isDirectory() && file.list().length == 0)
			{
				accept = true;
			}

			if (accept)
			{
				if (file.isDirectory() && file.list().length > 0)
				{
					accept = false;
				}
				if (accept)
				{
					if (logSingle)
					{
						if (file.isDirectory())
							log.info("removing empty directory: " + file.getAbsolutePath() + " last touched: "
									+ new JDFDate(file.lastModified()).getFormattedDateTime(JDFDate.DATETIMEREADABLE));
						else
							log.info("removing old file: " + file.getAbsolutePath() + " last touched: "
									+ new JDFDate(file.lastModified()).getFormattedDateTime(JDFDate.DATETIMEREADABLE) + " size=" + file.length());

					}
					file.delete();
				}
			}
			return accept;
		}
	}

	private final File baseDir;
	private KillFilter filter;
	boolean logSingle;
	final private static Log log = LogFactory.getLog(FileJanitor.class);
	boolean delEmpty;

	/**
	 *
	 * if true, we log each deletion
	 *
	 * @param logSingle
	 */
	public void setLogSingle(final boolean logSingle)
	{
		this.logSingle = logSingle;
	}

	/**
	 *
	 * if true, we delete empty directories
	 *
	 * @param delEmpty
	 */
	public void setDeleteEmptyDir(final boolean delEmpty)
	{
		this.delEmpty = delEmpty;
	}

	/**
	 *
	 * @param baseDir
	 * @param filter the file filter that
	 */
	public FileJanitor(final File baseDir, final FileFilter filter)
	{
		if (filter != null)
			this.filter = new KillFilter(filter);
		this.baseDir = baseDir;
		logSingle = false;
		delEmpty = false;
	}

	/**
	 *
	 * @param baseDir
	 * @param age the minimum zapp file age in seconds
	 */
	public FileJanitor(final File baseDir, final long age)
	{
		this(baseDir, null);
		filter = new KillFilter(new AgeFilter(age * 1000));
	}

	/**
	 *
	 * process all accepted files and empty directories that have been accepted<br/>
	 * note that the filter may actually do something
	 *
	 * @return the list of files that have been processed <br/>
	 *         note these no longer exist in case of a kill filter
	 */
	public Vector<File> cleanup()
	{
		return FileUtil.listFilesInTree(baseDir, filter);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FileJanitor " + baseDir + " delEmpty=" + delEmpty;
	}
}
