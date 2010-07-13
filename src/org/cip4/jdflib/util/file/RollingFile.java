/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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
package org.cip4.jdflib.util.file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * class to generate rolling backup files using a simple <FileName>.n naming algorithm.
 * 
 * The oldest file dies when the maximum number is reached
 * 
 * 08.12.2008
 */
public class RollingFile extends File
{
	private int pos;
	private final String base;
	private final String ext;
	private int digits;

	/**
	 * @param pathname the base filename
	 * @param baseName the name of the base file
	 */
	public RollingFile(final String pathname, final String baseName)
	{
		super(pathname);
		ext = UrlUtil.extension(baseName);
		base = UrlUtil.prefix(baseName);
		digits = 6;
		calcPos();
	}

	/**
	 * 
	 */
	private void calcPos()
	{
		File[] list = readAll();
		pos = 0;
		if (list == null)
			return;
		for (File f : list)
		{
			String name = f.getName();
			name = UrlUtil.newExtension(name, null);
			if (base != null)
				name = name.substring(base.length());
			int n = StringUtil.parseInt(name, -1);
			if (n > pos)
				pos = n;
		}
	}

	/**
	 * read all matching files from this
	 * @return array of all matching files
	 */
	public File[] readAll()
	{
		String expression = getFileExpression();
		File[] list = FileUtil.listFilesWithExpression(this, expression);
		if (list != null)
			Arrays.sort(list);
		return list;
	}

	/**
	 * @return
	 */
	private String getFileExpression()
	{
		String expression = "(.)*";
		if (base != null)
			expression = base + expression;
		if (ext != null)
			expression = expression + "." + ext;
		return expression;
	}

	/**
	 * @return the file to write, null if error occurred
	 */
	public File getNewFile()
	{
		File file = FileUtil.getFileInDirectory(this, new File(getNewFileName()));
		try
		{
			file.createNewFile();
		}
		catch (IOException x)
		{
			file = null;
		}
		return file;
	}

	/**
	 * @return the file to write, i.e. this
	 */
	private String getNewFileName()
	{
		String exp = getFileExpression();
		exp = StringUtil.replaceString(exp, "(.)*", "%0" + digits + "i");
		exp = StringUtil.sprintf(exp, "" + ++pos);
		return exp;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1521423479897L;

	/**
	 * @param digits the digits to set
	 */
	public void setDigits(int digits)
	{
		this.digits = digits;
	}

}
