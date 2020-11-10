/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.ifaces.IStreamWriter;
import org.cip4.jdflib.util.ByteArrayIOStream.ByteArrayIOInputStream;
import org.cip4.jdflib.util.net.HTTPDetails;
import org.cip4.jdflib.util.net.ProxyUtil;

class URLWriter
{

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UrlWriter: " + method + " / " + contentType + " --> " + url;
	}

	static final Log log = LogFactory.getLog(URLWriter.class);
	private final URL url;
	private final ByteArrayIOStream stream;
	private final String method;
	private final String contentType;
	private final HTTPDetails details;
	private final IStreamWriter writer;
	private static int nLogged = 0;

	/**
	 * @param strUrl the URL to write to
	 * @param stream the input stream to read from
	 * @param streamWriter
	 * @param method HEAD, GET or POST
	 * @param contentType the contenttype to set, if NULL defaults to TEXT/UNKNOWN
	 * @param details
	 */
	public URLWriter(final URL url, final IStreamWriter streamWriter, final String method, String contentType, final HTTPDetails details)
	{
		this.url = url;
		this.method = method;
		if (contentType == null)
			contentType = UrlUtil.TEXT_UNKNOWN;
		this.contentType = StringUtil.token(contentType, 0, "\r\n");
		this.details = details;
		this.stream = getStream(streamWriter);
		this.writer = stream != null ? null : streamWriter;
	}

	/**
	 * @param strUrl the URL to write to
	 * @param stream the input stream to read from
	 * @param streamWriter
	 * @param method HEAD, GET or POST
	 * @param contentType the contenttype to set, if NULL defaults to TEXT/UNKNOWN
	 * @param details
	 */
	public URLWriter(final String strUrl, final IStreamWriter streamWriter, final String method, final String contentType, final HTTPDetails details)
	{
		this(UrlUtil.stringToURL(strUrl), streamWriter, method, contentType, details);
	}

	/**
	 * @param strUrl the URL to write to
	 * @param stream the input stream to read from
	 * @param streamWriter
	 * @param method HEAD, GET or POST
	 * @param contentType the contenttype to set, if NULL defaults to TEXT/UNKNOWN
	 * @param details
	 */
	public URLWriter(final InputStream is, final String strUrl, final String method, final String contentType, final HTTPDetails details)
	{
		this(is, UrlUtil.stringToURL(strUrl), method, contentType, details);
	}

	/**
	 * @param strUrl the URL to write to
	 * @param stream the input stream to read from
	 * @param streamWriter
	 * @param method HEAD, GET or POST
	 * @param contentType the contenttype to set, if NULL defaults to TEXT/UNKNOWN
	 * @param details
	 */
	public URLWriter(final InputStream is, final URL url, final String method, String contentType, final HTTPDetails details)
	{
		this.url = url;
		this.method = method;
		if (contentType == null)
			contentType = UrlUtil.TEXT_UNKNOWN;
		this.contentType = StringUtil.token(contentType, 0, "\r\n");
		this.details = details;
		this.stream = (is == null) ? null : new ByteArrayIOFileStream(is, UrlUtil.MAX_STREAM);
		this.writer = null;
	}

	private ByteArrayIOStream getStream(final IStreamWriter inWriter)
	{
		// we can retain the original - we never need to buffer
		if (inWriter == null || UrlUtil.isFile(UrlUtil.urlToString(url)))
			return null;

		final ByteArrayIOStream bufStream = new ByteArrayIOFileStream(UrlUtil.MAX_STREAM);
		try
		{
			inWriter.writeStream(bufStream);
		}
		catch (final IOException e)
		{
			return null;
		}
		return bufStream;
	}

	/**
	 * write a Stream to an output URL File: and http: are currently supported Use HttpURLConnection.getInputStream() to retrieve the http response
	 *
	 * @return {@link UrlPart} the opened http connection, null in case of error
	 *
	 */
	public UrlPart writeToURL()
	{
		UrlPart urlPart = null;
		UrlPart fallBack = null;

		if (UrlUtil.isFile(UrlUtil.urlToString(url)))
		{
			urlPart = writeFile();
		}
		else
		{
			final URI uri = ProxyUtil.getHostURI(url);
			if (uri == null) // redundant but makes compiler happy
				return null;

			final List<Proxy> list = ProxyUtil.getProxiesWithLocal(uri);

			for (final Proxy proxy : list)
			{

				final boolean bWantLog = list.size() == 1 || !proxy.equals(Proxy.NO_PROXY);
				urlPart = callProxy(proxy, bWantLog);
				if (urlPart != null)
				{
					final int responseCode = urlPart.getResponseCode();
					if (UrlUtil.isReturnCodeOK(responseCode))
					{
						return urlPart;
					}
					else if (UrlUtil.isRedirect(responseCode) && (details == null || details.getRedirect() < 42))
					{
						String newLocation = urlPart.getConnection().getHeaderField("Location");
						if (StringUtil.isEmpty(newLocation) && UrlUtil.isHttp(UrlUtil.urlToString(url)))
						{
							newLocation = StringUtil.replaceToken(newLocation, 0, JDFConstants.COLON, "https");
						}
						if (newLocation != null)
						{
							fallBack = urlPart;

							final ByteArrayIOInputStream newInput = stream == null ? null : stream.getInputStream();
							urlPart = new URLWriter(newInput, newLocation, method, contentType, HTTPDetails.getRedirect(details)).writeToURL();

							if (urlPart == null)
							{
								urlPart = fallBack;
							}
							else if (UrlUtil.isReturnCodeOK(urlPart.getResponseCode()))
							{
								return urlPart;
							}
						}
					}
					else
					{
						fallBack = urlPart;
					}
				}
			}
		}
		return urlPart == null ? fallBack : urlPart;

	}

	/**
	 * @return
	 */
	private UrlPart writeFile()
	{
		File f = UrlUtil.urlToFile(UrlUtil.urlToString(url));
		if (writer != null)
		{
			f = FileUtil.writeFile(writer, f);
		}
		else if (stream != null)
		{
			FileUtil.streamToFile(stream.getInputStream(), f);
		}
		try
		{
			return new UrlPart(f);
		}
		catch (final IOException x)
		{
			return null;
		}
	}

	/**
	 * @param proxy
	 * @param bWantLog
	 * @return
	 */
	private UrlPart callProxy(final Proxy proxy, final boolean bWantLog)
	{

		try
		{
			final URLConnection urlConnection = url.openConnection(proxy);
			urlConnection.setConnectTimeout(UrlUtil.getConnectionTimeout());
			urlConnection.setRequestProperty("Connection", UrlUtil.KEEPALIVE);
			urlConnection.setRequestProperty(UrlUtil.CONTENT_TYPE, contentType);
			if (urlConnection instanceof HttpURLConnection)
			{
				final HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
				httpUrlConnection.setRequestMethod(method);
				if (details != null)
				{
					details.applyTo(httpUrlConnection);
				}
				output(httpUrlConnection);
				return new UrlPart(httpUrlConnection);
			}
			else if (UrlUtil.isFtp(UrlUtil.urlToString(url)))
			{
				return new UrlPart(urlConnection, false);
			}
		}
		catch (final Throwable x)
		{
			if (bWantLog && (nLogged++ < 10 || nLogged % 100 == 0))
			{
				log.warn(x.getClass().getCanonicalName() + " snafu #" + nLogged + " writing to url: " + url + " " + x.getMessage());
			}
		}
		return null;
	}

	/**
	 * @param httpURLconnection
	 * @throws IOException
	 */
	private void output(final HttpURLConnection httpURLconnection) throws IOException
	{
		final boolean doOutput = writer != null || stream != null;
		httpURLconnection.setDoOutput(doOutput);
		if (doOutput)
		{
			final OutputStream out = StreamUtil.getBufferedOutputStream(httpURLconnection.getOutputStream());
			if (writer != null)
				writer.writeStream(out);
			else
				IOUtils.copy(stream.getInputStream(), out);
			out.flush();
			out.close();
		}
	}
}