/*
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
 * 
 */
package org.cip4.jdflib.util;

import java.io.File;
import java.util.HashMap;

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

	/**
	 * @param pathname the base filename
	 * @param nBackupp the number of backups to retain
	 */
	public RollingBackupFile(final String pathname, final int nBackupp)
	{
		super(pathname);
		this.nBackup = nBackupp;
	}

	/**
	 * @param file the base file
	 * @param nBackupp the number of backups to retain
	 */
	public RollingBackupFile(final File file, final int nBackupp)
	{
		super(file.getPath());
		this.nBackup = nBackupp;
	}

	/**
	 * @return the file to write, i.e. this
	 */
	public File getNewFile()
	{
		final String extension = FileUtil.getExtension(this);
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
		final String pathname = getPath();
		final String extension = FileUtil.getExtension(this);
		File f = new File(getPathFor(pathname, extension, i));
		if (!f.canRead())
			f = null;

		return f;
	}

	/**
	 * remove all files, including main file
	 */
	public void clearAll()
	{
		final String pathname = getPath();
		final String extension = FileUtil.getExtension(this);
		for (int i = nBackup; i >= 0; i--)
		{
			final String sBak = getPathFor(pathname, extension, i);
			final File lastFile = new File(sBak);
			if (lastFile.exists())
			{
				lastFile.delete();
			}
			delete();
		}
	}

	/**
	 * the big simple rolling method
	 */
	private synchronized File init(String extension)
	{
		String pathname = getPath();
		pathname = UrlUtil.newExtension(pathname, extension);
		HashMap<Integer, String> map = getExtensionMap();
		for (int i = nBackup; i > 0; i--)
		{
			String ext = map.get(new Integer(i - 1));
			String ext2 = map.get(new Integer(i));
			if (ext != null)
			{
				final String sBak = getPathFor(pathname, ext, i);
				final String sNewBak = (i == 1) ? UrlUtil.newExtension(pathname, ext) : getPathFor(pathname, ext, i - 1);
				final String sBak2 = UrlUtil.newExtension(sBak, ext2);
				final File lastFile2 = new File(sBak2);
				if (lastFile2.exists())
				{
					lastFile2.delete();
				}
				final File lastFile = new File(sBak);
				new File(sNewBak).renameTo(lastFile);
			}
		}
		return new File(pathname);
	}

	/**
	 * TODO Please insert comment!
	 * @return
	 */
	private HashMap<Integer, String> getExtensionMap()
	{
		File[] oldFiles = FileUtil.listFilesWithExpression(getParentFile(), UrlUtil.newExtension(getName(), null) + "*");
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		if (oldFiles != null)
		{
			for (File file : oldFiles)
			{
				String name = file.getName();
				String ext = UrlUtil.extension(name);
				if (ext != null)
				{
					String delta = StringUtil.token(name, -2, ".");
					int i = StringUtil.parseInt(delta, -1);
					if (i >= 0)
						map.put(new Integer(i), ext);
					else if (delta != null && delta.equals(UrlUtil.prefix(name)))
						map.put(new Integer(0), ext);
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
	private String getPathFor(final String pathname, final String extension, int i)
	{
		final String newExtension = i + ((extension == null) ? "" : "." + extension);
		final String sBak = UrlUtil.newExtension(pathname, newExtension);
		return sBak;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1521423479897L;

}
