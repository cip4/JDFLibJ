/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
import java.io.IOException;
import java.util.Arrays;

import org.cip4.jdflib.util.file.FileTime;
import org.cip4.jdflib.util.thread.DelayedPersist;
import org.cip4.jdflib.util.thread.IPersistable;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 *         class to manage a directory by removing the oldest files whenever a new file is created
 * 
 *         The oldest file dies when the maximum number is reached
 * 
 *         08.12.2008
 */
public class BackupDirectory extends File implements IPersistable
{
	private final int nBackup;
	private boolean isDirectory;

	/**
	 * @param pathname the Directory
	 * @param nBackupp the number of backups to retain
	 * @throws IllegalArgumentException if file exists and is not a directory
	 */
	public BackupDirectory(final String pathname, final int nBackupp)
	{
		this(new File(pathname), nBackupp);
	}

	/**
	 * @param file the base file
	 * @param nBackupp the number of backups to retain
	 * @throws IllegalArgumentException if file exists and is not a directory
	 */
	public BackupDirectory(final File file, final int nBackupp) throws IllegalArgumentException
	{
		super(file.getPath());
		this.nBackup = nBackupp;
		if (!isDirectory())
			mkdirs();
		if (!isDirectory())
			throw new IllegalArgumentException(file.getAbsolutePath() + " is not a directory");
		isDirectory = false;
	}

	/**
	 * creates a new file in this and assures than no more than nBackup files remain
	 * 
	 * @param localFile the local file to place in this directory
	 * @return the file to write, null if an io exception occurred when creating it
	 */
	public File getNewFile(final File localFile)
	{
		final File file = FileUtil.getFileInDirectory(this, localFile);
		if (file.exists())
			FileUtil.forceDelete(file);
		else
			// we only cleanup once a minute
			DelayedPersist.getDelayedPersist().queue(this, 42000);

		final boolean ok;
		if (isDirectory)
		{
			ok = file.mkdirs();
		}
		else
		{
			try
			{
				ok = file.createNewFile();
			}
			catch (final IOException x)
			{
				return null;
			}
		}
		return ok ? file : null;
	}

	/**
	 * 
	 */
	public void clean()
	{
		File[] all;
		synchronized (this)
		{
			all = listFiles();
		}
		if (all != null && all.length >= nBackup)
		{
			final FileTime[] time = new FileTime[all.length];
			for (int i = 0; i < all.length; i++)
			{
				time[i] = new FileTime(all[i]);
			}
			Arrays.sort(time);
			for (int i = nBackup; i < all.length; i++)
			{
				FileUtil.forceDelete(time[i].getFile());
			}
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1521423479898L;

	/**
	 * @param fileName
	 * @return
	 */
	public File getNewFile(final String fileName)
	{
		return fileName == null ? null : getNewFile(new File(fileName));
	}

	/**
	 * @see java.io.File#toString()
	 */
	@Override
	public String toString()
	{
		return super.toString() + "isDir=" + isDirectory + " nBackup=" + nBackup;
	}

	/**
	 * @see org.cip4.jdflib.util.thread.IPersistable#persist()
	 */
	@Override
	public boolean persist()
	{
		clean();
		return true;
	}

	public boolean isCreateDirectory()
	{
		return isDirectory;
	}

	public void setDirectory(final boolean isDirectory)
	{
		this.isDirectory = isDirectory;
	}

}
