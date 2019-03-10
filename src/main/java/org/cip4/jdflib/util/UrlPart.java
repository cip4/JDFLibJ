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
import java.net.HttpURLConnection;
import java.net.URLConnection;

import javax.mail.BodyPart;
import javax.mail.MessagingException;

import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.core.XMLParser;
import org.cip4.jdflib.core.XMLParserFactory;
import org.cip4.jdflib.util.net.IPollDetails;

/**
 * simple struct to contain the stream and type of a bodypart
 *
 * @author rainer prosi
 *
 */
public class UrlPart implements IPollDetails
{
	/**
	 *
	 * @return the response code
	 */
	@Override
	public int getResponseCode()
	{
		return rc;
	}

	/**
	 * @param connection
	 * @throws IOException
	 */
	public UrlPart(final HttpURLConnection connection) throws IOException
	{
		this(connection, true);
	}

	/**
	 *
	 * @param connection
	 * @param isHttp
	 * @throws IOException
	 */
	public UrlPart(final URLConnection connection, final boolean isHttp) throws IOException
	{
		this.connection = connection;
		contentType = connection.getContentType();
		contentLength = connection.getContentLength();
		url = UrlUtil.urlToString(connection.getURL());
		if (isHttp)
		{
			try
			{
				inStream = connection.getInputStream();
			}
			catch (final IOException x)
			{
				inStream = null;
			}
			if (inStream == null)
				inStream = ((HttpURLConnection) connection).getErrorStream();
			rc = ((HttpURLConnection) connection).getResponseCode();
		}
		else
		{
			inStream = connection.getInputStream();
			rc = 200;
		}
		bufferStream = null;
	}

	/**
	 * @param part
	 * @throws MessagingException
	 * @throws IOException
	 */
	public UrlPart(final BodyPart part) throws MessagingException, IOException
	{
		inStream = part.getInputStream();
		contentLength = part.getSize();
		contentType = part.getContentType();
		connection = null;
		bufferStream = null;
		url = part.getFileName();
		rc = 200;
	}

	/**
	 *
	 * @param f
	 * @throws IOException
	 */
	public UrlPart(final File f) throws IOException
	{
		inStream = FileUtil.getBufferedInputStream(f);
		if (f == null)
		{
			contentLength = 0;
			contentType = null;
			url = null;
		}
		else
		{

			contentLength = f.length();
			contentType = UrlUtil.getMimeTypeFromURL(f.getName());
			url = f.getAbsolutePath();
		}

		connection = null;
		rc = f == null ? 500 : 200;
		bufferStream = null;
	}

	private final int rc;
	/**
	 * the input stream of this UrlPart
	 */
	private InputStream inStream;
	/**
	 * the content type of this UrlPart
	 */
	private final String contentType;
	private ByteArrayIOStream bufferStream;

	/**
	 * @return the contentType
	 */
	@Override
	public String getContentType()
	{
		return contentType;
	}

	/**
	 * @param inStream the inStream to set
	 */
	public void setInStream(final InputStream inStream)
	{
		this.inStream = inStream;
		bufferStream = null;
	}

	/**
	 * @see org.cip4.jdflib.util.net.IPollDetails#getResponseStream()
	 * @return the response stream
	 */
	@Override
	public InputStream getResponseStream()
	{
		return bufferStream == null ? inStream : bufferStream.getInputStream();
	}

	/**
	 * the content length of this UrlPart
	 */
	public long contentLength;
	private final URLConnection connection;
	private final String url;

	/**
	 * returns an xmldoc corresponding to this part
	 *
	 * @return the doc, null if not xml
	 */
	public XMLDoc getXMLDoc()
	{
		final XMLParser p = XMLParserFactory.getFactory().get();
		p.setInputID(url);
		final XMLDoc d = p.parseStream(getResponseStream());
		XMLParserFactory.getFactory().push(p);
		return d;
	}

	/**
	 * buffer my input stream
	 *
	 */
	public void buffer()
	{
		if (bufferStream == null)
		{
			bufferStream = new ByteArrayIOFileStream(inStream, 10000000);
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return "URLPart: " + contentType + " length=" + contentLength + " rc=" + rc + " URL=" + url + "\n" + ((bufferStream == null) ? " <not buffered>" : bufferStream);
	}

	/**
	 * @return the connection
	 */
	public URLConnection getConnection()
	{
		return connection;
	}

	/**
	 * we are polling
	 *
	 * @see org.cip4.jdflib.util.net.IPollDetails#isPush()
	 */
	@Override
	public boolean isPush()
	{
		return false;
	}

	@Override
	protected void finalize() throws Throwable
	{
		if (bufferStream != null)
			bufferStream.close();
		super.finalize();
	}

}