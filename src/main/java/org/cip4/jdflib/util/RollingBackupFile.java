/*
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
 *
 */
package org.cip4.jdflib.util;

import java.io.File;
import java.util.HashMap;

import org.cip4.jdflib.core.VString;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 * class to generate rolling backup files using a simple <FileName>.n naming algorithm.
 *
 * The oldest file dies when the maximum number is reached
 *
 * 08.12.2008
 */
public class RollingBackupFile extends File
{
	private final int nBackup;
	boolean wantExtension;

	/**
	 * @param wantExtension the wantExtension to set
	 */
	public void setWantExtension(boolean wantExtension)
	{
		this.wantExtension = wantExtension;
	}

	/**
	 * @param pathname the base filename
	 * @param nBackupp the number of backups to retain
	 */
	public RollingBackupFile(final String pathname, final int nBackupp)
	{
		super(pathname);
		this.nBackup = nBackupp;
		wantExtension = false;
	}

	/**
	 * @param file the base file
	 * @param nBackupp the number of backups to retain
	 */
	public RollingBackupFile(final File file, final int nBackupp)
	{
		this(file.getPath(), nBackupp);
	}

	/**
	 * @return the file to write, i.e. this
	 */
	public File getNewFile()
	{
		final String extension = getExtension(this);
		return getNewFile(extension);
	}

	/**
	 *
	 * get a new file for a given extension
	 * @param extension
	 * @return
	 */
	public File getNewFile(final String extension)
	{
		return init(extension);
	}

	/**
	 * @param i the index of the file to read
	 * @return the file to read, with backup
	 */
	public File getOldFile(int i)
	{
		if (i == 0)
			return this;
		if (i < 0 || i >= nBackup)
			return null;
		File[] oldFiles = FileUtil.listFilesWithExpression(getParentFile(), UrlUtil.newExtension(getName(), null) + "." + i + ".*");
		if (oldFiles == null || oldFiles.length == 0)
			return null;

		return oldFiles[0];
	}

	private String getExtension(File f)
	{
		VString v = StringUtil.tokenize(f.getName(), ".", false);
		v.remove(0);
		if (v.size() == 0)
			return null;
		if (StringUtil.isInteger(v.get(0)))
			v.remove(0);
		return StringUtil.setvString(v, ".", null, null);
	}

	/**
	 * remove all files, including main file
	 */
	public void clearAll()
	{
		File[] oldFiles = FileUtil.listFilesWithExpression(getParentFile(), UrlUtil.newExtension(getName(), null) + "*");
		if (oldFiles != null)
		{
			for (File lastFile : oldFiles)
			{
				lastFile.delete();
			}
		}
	}

	/**
	 * the big simple rolling method
	 */
	private synchronized File init(String extension)
	{
		String pathname = getPath();
		pathname = UrlUtil.newExtension(pathname, extension);
		HashMap<Integer, File> map = getNameMap();
		if (!map.isEmpty())
		{
			for (int i = nBackup; i > 0; i--)
			{
				File newFile = map.get(Integer.valueOf(i - 1));
				if (newFile != null)
				{
					File oldFile = map.get(Integer.valueOf(i));
					if (oldFile != null && oldFile.exists())
					{
						oldFile.delete();
					}
					File newFileRenamed = new File(getPathFor(newFile.getAbsolutePath(), i));
					newFile.renameTo(newFileRenamed);
				}
			}
		}
		return new File(pathname);
	}

	/**
	 *
	 * @return
	 */
	private HashMap<Integer, File> getNameMap()
	{
		String myExt = FileUtil.getExtension(this);
		String expression = UrlUtil.newExtension(getName(), null) + "*";
		if (wantExtension && StringUtil.getNonEmpty(myExt) != null)
		{
			expression += "." + myExt;
		}
		File[] oldFiles = FileUtil.listFilesWithExpression(getParentFile(), expression);
		HashMap<Integer, File> map = new HashMap<Integer, File>();
		if (oldFiles != null)
		{
			for (File file : oldFiles)
			{
				String name = file.getName();
				String ext = getExtension(file);
				int nDot = StringUtil.numSubstrings(ext, ".");
				if (ext != null)
				{
					String delta = StringUtil.token(name, -2 - nDot, ".");
					int i = StringUtil.parseInt(delta, -1);
					if (i >= 0)
					{
						map.put(Integer.valueOf(i), file);
					}
					else if (delta != null && delta.equals(UrlUtil.prefix(getName())))
					{
						map.put(Integer.valueOf(0), file);
					}
				}
			}
		}
		return map;
	}

	/**
	 * @param pathname
	 * @param extension
	 * @param i
	 * @return
	 */
	private String getPathFor(final String pathname, int i)
	{
		String base = i == 0 ? "" : "." + i;
		String oldbase = i <= 1 ? "" : "." + (i - 1);
		String extension = getExtension(new File(pathname));
		final String oldExtension = oldbase + ((extension == null) ? "" : "." + extension);
		final String newExtension = base + ((extension == null) ? "" : "." + extension);
		return StringUtil.replaceString(pathname, oldExtension, newExtension);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1521423479897L;

}
