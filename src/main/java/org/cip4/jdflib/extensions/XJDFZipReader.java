/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */

package org.cip4.jdflib.extensions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.ifaces.IStreamWriter;
import org.cip4.jdflib.ifaces.IURLSetter;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.cip4.jdflib.util.zip.ZipReader;

/**
 * class to unpack multi-xjdf zip files and convert the package to a single zip file
 * @author rainer prosi
 *
 */
public class XJDFZipReader implements IStreamWriter
{
	private String path;
	private XJDFToJDFConverter converter;

	/**
	 * 
	 */
	public XJDFZipReader(InputStream inStream)
	{
		this(ZipReader.getZipReader(inStream));
	}

	/**
	 * 
	 */
	public XJDFZipReader(ZipReader zipReader)
	{
		super();
		theReader = zipReader;
		if (theReader != null)
		{
			theReader.setCaseSensitive(false);
		}
		newDoc = null;
		newJMF = null;
		path = null;
		converter = null;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path)
	{
		this.path = UrlUtil.cleanDots(path);
	}

	/**
	 * 
	 */
	public XJDFZipReader(File inFile)
	{
		this(FileUtil.getBufferedInputStream(inFile));
	}

	final ZipReader theReader;
	private JDFDoc newDoc;
	private JDFDoc newJMF;

	/**
	 * 
	 */
	@Override
	public void writeStream(OutputStream os) throws IOException
	{
		if (newDoc == null)
		{
			convert();
		}
		if (newDoc != null)
		{
			newDoc.write2Stream(os, 2, false);
		}
	}

	/**
	 * 
	 */
	public void convert()
	{
		convertXJMF();
		convertXJDF();
	}

	/**
	 * 
	 */
	public void convertXJDF()
	{
		newDoc = null;
		if (theReader != null)
		{
			String pathExpression = UrlUtil.getURLWithDirectory(path, "*.xjdf");
			for (int i = 0; true; i++)
			{
				ZipEntry ze = theReader.getMatchingEntry(pathExpression, i);
				if (ze == null)
				{
					break;
				}
				XMLDoc xdoc = theReader.getXMLDoc();
				if (xdoc != null)
				{
					newDoc = getConverter().convert(xdoc.getRoot());
				}
			}
		}
	}

	/**
	 * 
	 */
	void convertXJMF()
	{
		newJMF = null;
		if (theReader != null)
		{
			XJDFToJDFConverter c = getConverter();
			ZipEntry ze = theReader.getMatchingEntry("*.xjmf", 0);
			XMLDoc xdoc = ze == null ? null : theReader.getXMLDoc();
			if (xdoc != null)
			{
				newJMF = c.convert(xdoc.getRoot());
				JDFJMF jmf = newJMF == null ? null : newJMF.getJMFRoot();
				JDFCommand command = jmf == null ? null : jmf.getCommand(0);
				if (command != null)
				{
					VString validParams = new VString(new String[] { ElementName.QUEUESUBMISSIONPARAMS, ElementName.RESUBMISSIONPARAMS, ElementName.RETURNQUEUEENTRYPARAMS });
					IURLSetter params = (IURLSetter) command.getChildFromList(validParams, 0, null, true);
					String url = params.getURL();
					if (UrlUtil.isRelativeURL(url))
					{
						setPath(url);
					}
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
		return "XJDFZipReader [path=" + path + ", newDoc=" + newDoc + "]";
	}

	/**
	 * 
	 * @return
	 */
	public JDFNode getJDFRoot()
	{
		return newDoc == null ? null : newDoc.getJDFRoot();
	}

	/**
	 * 
	 * @return
	 */
	public JDFJMF getJMFRoot()
	{
		return newJMF == null ? null : newJMF.getJMFRoot();
	}

	public XJDFToJDFConverter getConverter()
	{
		if (converter == null)
			converter = new XJDFToJDFConverter(null);
		return converter;
	}

	public void setConverter(XJDFToJDFConverter converter)
	{
		this.converter = converter;
	}

	/**
	 * @param newJMF the newJMF to set
	 */
	protected void setNewJMF(JDFDoc newJMF)
	{
		this.newJMF = newJMF;
	}
}
