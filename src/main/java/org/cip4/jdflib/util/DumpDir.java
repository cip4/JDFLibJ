/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author rainer
 *
 *         very trivial temp file dump
 *
 */
public class DumpDir
{

	private File baseDir = null;
	private static HashMap<File, AtomicInteger> listMap = new HashMap<>();
	private final int maxKeep = 666;
	final protected Log log;
	/**
	 * if true, no printouts
	 */
	public boolean quiet = true;

	/**
	 *
	 * @return
	 */
	int increment()
	{
		final AtomicInteger i = listMap.get(baseDir);
		return i.incrementAndGet();
	}

	/**
	 * create a dumpdir with dir as the root
	 *
	 * @param dir
	 */
	public DumpDir(final File dir)
	{
		log = LogFactory.getLog(getClass());
		baseDir = dir;
		baseDir.mkdirs();
		synchronized (listMap)
		{
			AtomicInteger index = listMap.get(baseDir);
			if (index == null)
			{
				index = new AtomicInteger(0);
				listMap.put(baseDir, index);
			}
			final String[] names = baseDir.list();
			int max = 0;
			if (names != null)
			{
				int l;
				for (int i = 0; i < names.length; i++)
				{
					if (names[i].length() > 9)
					{
						l = StringUtil.parseInt(names[i].substring(1, 9), 0);
					}
					else
					{
						l = 0;
					}
					if (l >= max)
					{
						max = l + 1;
					}
				}
			}
			index.set(max);
		}
	}

	/**
	 * returns the base directory as a File
	 *
	 * @return
	 */
	public File getDir()
	{
		return baseDir;
	}

	/**
	 * create a new File in this dump
	 *
	 * @param header the header to print prior to the xml
	 * @return
	 * @deprecated - use 2 parameter version; default= newFile(header,null);
	 */
	@Deprecated
	public File newFile(final String header)
	{
		return newFile(header, null);
	}

	/**
	 * create a new File in this dump
	 *
	 * @param header the header to print prior to the xml
	 * @param ext the additional extension to add prior to .tmp
	 * @return
	 *
	 */
	public File newFile(final String header, final String ext)
	{
		final int inc = increment();
		if (!quiet && (inc % 1000 == 0))
		{
			log.info("jmf dump service " + baseDir + " - " + inc + " " + new JDFDate().getDateTime());
		}

		final String s = ext == null ? StringUtil.sprintf("m%08i.tmp", "" + inc) : StringUtil.sprintf("m%08i.%s.tmp", "" + inc + "," + ext);
		final File f = FileUtil.getFileInDirectory(baseDir, new File(s));
		if (header != null)
		{
			newHeader(header, f, true);
		}

		cleanup(inc);
		return f;
	}

	/**
	 * create a new File in this dump and fill it from is
	 *
	 * @param header the header to print prior to the stream
	 * @param is the input stream to fill
	 * @return the new file
	 * @deprecated use the 3 parameter version
	 */
	@Deprecated
	public File newFileFromStream(final String header, final InputStream is)
	{
		return newFileFromStream(header, is, null);
	}

	/**
	 * create a new File in this dump and fill it from is
	 *
	 * @param header the header to print prior to the stream
	 * @param is the input stream to fill
	 * @param ext the additional extension
	 * @return the new file
	 *
	 */
	public File newFileFromStream(final String header, InputStream is, final String ext)
	{
		final File dump = newFile(null, ext);
		is = ByteArrayIOStream.getBufferedInputStream(is);

		final OutputStream fs = newHeader(header, dump, false);
		if (fs != null)
		{
			try
			{
				if (is != null)
				{
					IOUtils.copy(is, fs);
				}
				fs.flush();
				fs.close();
				if (is != null && is.markSupported())
				{
					is.reset();
				}
			}
			catch (final IOException x)
			{
				log.error("error dumping dump stream", x);
			}
		}

		return dump;
	}

	/**
	 * @param header
	 * @param f
	 * @param bClose
	 * @return
	 */
	private OutputStream newHeader(final String header, final File f, final boolean bClose)
	{
		final OutputStream fs = FileUtil.getBufferedOutputStream(f);
		if (header != null && fs != null)
		{
			try
			{
				fs.write(header.getBytes());
				fs.write("\n------ end of header ----!\n".getBytes());
				if (bClose)
				{
					fs.flush();
					fs.close();
				}
			}
			catch (final IOException e)
			{
				// nop
			}
		}
		return fs;
	}

	/**
	 * @param inc
	 */
	void cleanup(final int inc)
	{
		if ((inc % 42) == 0)
		{
			synchronized (listMap.get(baseDir))
			{
				final File[] names = FileUtil.listFilesWithExtension(baseDir, "tmp");
				if (names != null && names.length > maxKeep)
				{
					Arrays.sort(names);
					for (int i = 0; i < names.length - maxKeep; i++)
					{
						final File f = names[i];
						cleanupSingle(f);
					}
				}
			}
		}
	}

	/**
	 *
	 * clean up single file including any unpacked directories
	 *
	 * @param f
	 */
	void cleanupSingle(final File f)
	{
		if (f != null)
		{
			f.delete();
			String name = f.getName();
			name = UrlUtil.newExtension(name, "*");
			final File[] dirnames = FileUtil.listFilesWithExpression(baseDir, name);
			if (dirnames != null)
			{
				for (final File dir : dirnames)
				{
					FileUtil.deleteAll(dir);
				}
			}
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DumpDir " + baseDir + " i=" + listMap.get(baseDir).get();
	}

}
