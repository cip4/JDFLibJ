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

import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.BackupDirectory;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;

/**
 * backup directory that creates countable file names with varying extensions
 * 
 * @author rainer prosi
 * @date July 10, 2012
 */
public class RollingBackupDirectory extends BackupDirectory
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int ordBackup;
	private final String baseName;
	private final String baseExt;

	/**
	 * @param file
	 * @param nBackupp
	 * @param base the file base name including its extension
	 * @throws IllegalArgumentException
	 */
	public RollingBackupDirectory(final File file, final int nBackupp, final String base) throws IllegalArgumentException
	{
		super(file, nBackupp);
		baseName = UrlUtil.prefix(base);
		baseExt = UrlUtil.extension(base);
		ordBackup = calcOrdBackup();
	}

	/**
	 * 
	 * @return
	 */
	private int calcOrdBackup()
	{
		final String ext = baseExt == null ? ".*" : ".*." + baseExt;
		final File[] files = FileUtil.listFilesWithExpression(this, baseName + ext);
		int nMax = 0;
		if (files == null)
			return nMax;
		for (final File file : files)
		{
			final int n = calcN(file);
			if (n > nMax)
			{
				nMax = n;
			}
		}
		return nMax;
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	private int calcN(final File file)
	{
		if (file == null)
			return 0;
		final String name = file.getName();
		final VString v = StringUtil.tokenize(name.substring(baseName.length()), ".", false);
		for (final String s : v)
		{
			if (StringUtil.isInteger(s))
			{
				return StringUtil.parseInt(s, -1);
			}
		}
		return 0;
	}

	/**
	 *
	 * 
	 * @return
	 */
	public File getNewFile()
	{
		return getNewFileWithExt(null);
	}

	/**
	 *
	 * 
	 * @param ext
	 * @return
	 */
	public synchronized File getNewFileWithExt(String ext)
	{
		final String newExt = baseExt == null ? "" : "." + baseExt;
		if (StringUtil.getNonEmpty(ext) == null)
			ext = "";
		else if (!ext.startsWith("."))
			ext = "." + ext;
		final String newFile = baseName + "." + (++ordBackup) + ext + newExt;
		return getNewFile(newFile);
	}

	/**
	 * @see java.io.File#toString()
	 */
	@Override
	public String toString()
	{
		return super.toString() + " baseName=" + baseName + " baseExt=" + baseExt + " ord=" + ordBackup;
	}

}
