/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.util.mime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.ByteArrayIOStream.ByteArrayIOInputStream;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.StreamUtil;
import org.cip4.jdflib.util.URLReader;
import org.cip4.jdflib.util.UrlUtil;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         Jul 24, 2009
 */
public class MimeReader extends MimeHelper
{
	private final static Log log = LogFactory.getLog(MimeReader.class);

	/**
	 *
	 */
	public MimeReader()
	{
		super();
	}

	/**
	 * create a reader from an existing mutipart
	 *
	 * @param mp
	 *
	 */
	public MimeReader(final Multipart mp)
	{
		super(mp);
	}

	/**
	 * create a root multipart from an input stream
	 *
	 * @param mimeStream the input stream
	 */
	public MimeReader(final InputStream mimeStream)
	{
		super();
		theMultipart = getMultiPart(mimeStream);
	}

	/**
	 * create a root multipart from a MimeWriter
	 *
	 * @param writer the input writer that will now be read
	 */
	public MimeReader(final MimeWriter writer)
	{
		super();
		theMultipart = writer.getMultiPart();
	}

	/**
	 * create a root multipart from an input stream
	 *
	 * @param url the url or file name
	 */
	public MimeReader(final String url)
	{
		super();
		theMultipart = getMultiPart(url);
	}

	/**
	 * create a root multipart from an input stream
	 *
	 * @param url the url or file name
	 */
	public MimeReader(final File file)
	{
		super();
		theMultipart = getMultiPart(FileUtil.getBufferedInputStream(file));
	}

	/**
	 * create a root multipart from an input stream
	 *
	 * @param mimeStream the input stream
	 * @return MultiPart the Multipart that represents the root mime, null if something went wrong
	 */
	private Multipart getMultiPart(final InputStream mimeStream)
	{
		if (mimeStream == null)
		{
			return null;
		}

		try
		{
			final InputStream newStream = StreamUtil.getBufferedInputStream(mimeStream);
			final Message mimeMessage = new MimeMessage(null, newStream);

			final Multipart mp = new MimeMultipart(mimeMessage.getDataHandler().getDataSource());
			return mp;
		}
		catch (final MessagingException e)
		{
			log.info("Snafu reading mime", e);
			return null;
		}
	}

	/**
	 * helper to create a root multipart from a file
	 *
	 * @param fileName the name of the file used as input
	 * @return MultiPart the Multipart that represents the root mime, null if something went wrong
	 */
	private Multipart getMultiPart(final String fileName)
	{
		final File f = UrlUtil.urlToFile(fileName);
		try
		{
			// MUST be SharedFileInputStream, as the body part retrieving
			// methods rely on the stream remaining open!
			final ByteArrayIOStream bis = new ByteArrayIOStream(f);
			final Multipart mp = getMultiPart(bis.getInputStream());
			bis.close();
			return mp;
		}
		catch (final FileNotFoundException e)
		{
			return null;
		}
		catch (final IOException e)
		{
			return null;
		}
	}

	/**
	 * get the JDF Doc from a stream, also handle non mime streams gracefully
	 *
	 * @param stream the stream to search in
	 * @param index the index of the body part to search
	 * @return JDFDoc the parsed xml JDFDoc, null if stream does not contain xml
	 */
	public JDFDoc getJDFDoc(final InputStream stream, final int index)
	{
		final XMLDoc xmlDoc = getXMLDoc(stream, index);
		return xmlDoc == null ? null : new JDFDoc(xmlDoc);
	}

	/**
	 * get the JDF Doc from a stream, also handle non mime streams gracefully
	 *
	 * @param stream the stream to search in
	 * @param index the index of the body part to search
	 * @return JDFDoc the parsed xml JDFDoc, null if stream does not contain xml
	 */
	public XMLDoc getXMLDoc(final InputStream stream, final int index)
	{
		final ByteArrayIOInputStream bis = ByteArrayIOStream.getBufferedInputStream(stream);
		bis.mark(42);
		final Multipart mp = getMultiPart(bis);
		if (mp != null)
		{
			theMultipart = mp;
			final BodyPartHelper bph = getBodyPartHelper(index);
			if (bph != null)
			{
				return bph.getXMLDoc();
			}
		}
		// not a mime - try direct xml
		if (index == 0)
		{
			bis.seek(0);
			return XMLDoc.parseStream(bis);
		}
		else
		{
			return null;
		}
	}

	/**
	 * get the opened input stream for a given url string
	 *
	 * @param urlString
	 * @return
	 */
	public InputStream getURLInputStream(final String urlString)
	{
		BodyPartHelper bp = getPartHelperByCID(urlString);
		if (bp == null)
		{
			bp = getPartHelperByLocalName(urlString);
		}
		if (bp != null)
		{
			return bp.getInputStream();
		}
		return new URLReader(urlString).getURLInputStream();
	}

	/**
	 * @param url
	 * @return
	 */
	public String getFileName(final String url)
	{
		final BodyPartHelper bp = getPartHelperByCID(url);
		if (bp != null)
		{
			return bp.getFileName();
		}
		else
		{
			return UrlUtil.urlToFileName(url);
		}
	}
}
