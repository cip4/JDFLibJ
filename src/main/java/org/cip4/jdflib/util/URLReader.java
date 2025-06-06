/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of
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
 */
package org.cip4.jdflib.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

import jakarta.mail.BodyPart;
import jakarta.mail.Multipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.util.zip.ZipReader;

/**
 *
 *
 * @author rainerprosi
 * @date Feb 1, 2012
 */
public class URLReader
{
	private final String urlString;
	private BodyPart bodypart;
	private ZipReader zip;
	private final List<File> localRoots;
	private final static Log log = LogFactory.getLog(URLReader.class);
	private File notRelative;

	/**
	 * @param urlString
	 *
	 */
	public URLReader(final String urlString)
	{
		this.urlString = urlString;
		localRoots = new ArrayList<>();
		notRelative = null;
	}

	/**
	 * @param urlString
	 * @param doc
	 *
	 */
	public URLReader(final String urlString, final XMLDoc doc)
	{
		this(urlString);
		bodypart = doc.getBodyPart();
		zip = doc.getZipReader();
		final String orig = doc.getOriginalFileName();
		if (orig != null)
		{
			final File f = new File(orig);
			final File parent = f.getParentFile();
			if (parent != null)
			{
				addLocalRoot(parent);
			}
		}
	}

	/**
	 *
	 * @param bodyPart
	 */
	public void setBodyPart(final BodyPart bodyPart)
	{
		this.bodypart = bodyPart;
	}

	/**
	 * add a root for local files
	 *
	 * @param root
	 */
	public void addLocalRoot(final File root)
	{
		if (root == null)
		{
			log.error("cannot add null root");
		}
		else
		{
			localRoots.add(root);
			ContainerUtil.unify(localRoots);
		}
	}

	/**
	 *
	 * @param zip
	 */
	public void setZipReader(final ZipReader zip)
	{
		this.zip = zip;
	}

	/**
	 * get the opened input stream for a given url string
	 *
	 *
	 * @return
	 */
	InputStream getBodyPartInputStream()
	{
		InputStream retStream = null;
		if (bodypart != null)
		{
			final Multipart multipart = bodypart.getParent();
			retStream = UrlUtil.getCidURLStream(urlString, multipart);
		}
		return retStream;
	}

	/**
	 * get the opened input stream for a given url string
	 *
	 *
	 * @return
	 */
	InputStream getZipInputStream()
	{
		InputStream retStream = null;
		if (zip != null)
		{
			final ZipEntry e = zip.getEntry(urlString);
			retStream = e == null ? null : zip.getInputStream();
		}
		return retStream;
	}

	/**
	 *
	 * @return
	 */
	public InputStream getURLInputStream()
	{
		InputStream retStream = getNetInputStream();
		if (retStream == null)
		{
			retStream = getBodyPartInputStream();
			if (retStream == null)
			{
				retStream = getZipInputStream();
				if (retStream == null)
				{
					final File file = getFile();
					if (file != null)
					{
						retStream = FileUtil.getBufferedInputStream(file);
					}
				}
			}
		}
		return retStream;
	}

	public File getFile()
	{
		File file = getAbsoluteFile();
		if (file == null)
		{
			file = getRelativeFile();
		}
		return file;
	}

	/**
	 *
	 * @return
	 */
	public XMLDoc getXMLDoc()
	{
		final InputStream retStream = getURLInputStream();
		final XMLDoc doc = XMLDoc.parseStream(retStream);
		applyDoc(doc);
		return doc;
	}

	/**
	 *
	 * @return
	 */
	public JDFDoc getJDFDoc()
	{
		final InputStream retStream = getURLInputStream();
		final JDFDoc doc = JDFDoc.parseStream(retStream);
		applyDoc(doc);
		return doc;
	}

	/**
	 *
	 *
	 * @param doc
	 */
	private void applyDoc(final XMLDoc doc)
	{
		if (doc != null)
		{
			doc.setBodyPart(bodypart);
			doc.setZipReader(zip);
			final String url = notRelative == null ? urlString : notRelative.getAbsolutePath();
			final File f = UrlUtil.urlToFile(url);
			final String filename = f == null ? null : f.getAbsolutePath();
			doc.setOriginalFileName(filename);
		}
	}

	/**
	 *
	 * @return
	 */
	InputStream getNetInputStream()
	{
		if (UrlUtil.isNet(urlString))
		{
			final UrlPart part = UrlUtil.writeToURL(urlString, null, UrlUtil.GET, null, null);
			if (UrlUtil.isReturnCodeOK(part))
				return part.getResponseStream();
		}
		return null;
	}

	/**
	 *
	 * @return
	 */
	File getAbsoluteFile()
	{
		final File f = !UrlUtil.isNet(urlString) && !UrlUtil.isCID(urlString) ? UrlUtil.urlToFile(urlString) : null;
		if ((f != null) && f.canRead())
		{
			return f;
		}
		return null;
	}

	/**
	 *
	 * @return
	 */
	File getRelativeFile()
	{
		if (UrlUtil.isRelativeURL(urlString))
		{
			final File fLocal = UrlUtil.urlToFile(urlString);
			for (final File root : localRoots)
			{
				final File f = FileUtil.getFileInDirectory(root, fLocal);
				if ((f != null) && f.canRead())
				{
					notRelative = f;
					return notRelative;
				}
			}
		}
		return null;
	}

	/**
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "URLReader: " + urlString + "\n" + localRoots + (zip != null ? " zip " : "") + (bodypart != null ? " mime " : "");
	}
}