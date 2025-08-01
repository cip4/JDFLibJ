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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * KString.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Base64;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.ifaces.IStreamWriter;
import org.cip4.jdflib.ifaces.IURLSetter;
import org.cip4.jdflib.util.mime.BodyPartHelper;
import org.cip4.jdflib.util.mime.MimeHelper;
import org.cip4.jdflib.util.zip.ZipReader;

import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;

/**
 * collection of helper routines to convert urls
 *
 * @author prosirai
 *
 */
public class UrlUtil
{
	public static final String ZIP = "zip";

	private static final String SLASHSLASH = "//";

	public static final String FILE = "file:";

	public static final String FTP = "ftp:";

	public static final String HTTPS = "https:";

	public static final String HTTP = "http:";

	public static final int MAX_STREAM = 12345678;

	private static Log log = LogFactory.getLog(UrlUtil.class);

	/**
	 * @deprecated use the real class
	 * @author prosirai
	 *
	 */
	@Deprecated
	public static class HTTPDetails extends org.cip4.jdflib.util.net.HTTPDetails
	{
	}

	/**
	 * @deprecated use the real class
	 * @author prosirai
	 *
	 */
	@Deprecated
	public static class URLWriter extends org.cip4.jdflib.util.URLWriter
	{
		public URLWriter(final String strUrl, final IStreamWriter streamWriter, final String method, final String contentType, final org.cip4.jdflib.util.net.HTTPDetails details)
		{
			super(strUrl, streamWriter, method, contentType, details);
		}
	}

	public static String getAuthorizationHeader(final String authHeader0)
	{
		String authHeader = StringUtil.removeToken(authHeader0, 0, JDFConstants.BLANK);
		if (authHeader != null)
		{
			try
			{
				authHeader = new String(Base64.getDecoder().decode(authHeader));
			}
			catch (final Exception x)
			{
				authHeader = null;
			}
		}
		return authHeader;
	}

	/**
	 * http post method
	 */
	public static final String POST = HttpMethod.POST.name();
	/**
	 * http put method
	 */
	public static final String PUT = HttpMethod.PUT.name();
	/**
	 * http get method
	 */
	public static final String GET = HttpMethod.GET.name();
	/**
	 * http head method
	 */
	public static final String HEAD = HttpMethod.HEAD.name();
	public static final String DELETE = HttpMethod.DELETE.name();
	public static final String CONNECT = HttpMethod.CONNECT.name();
	public static final String OPTIONS = HttpMethod.OPTIONS.name();
	public static final String TRACE = HttpMethod.TRACE.name();
	public static final String PATCH = HttpMethod.PATCH.name();

	/**
	 * list of all http methods
	 *
	 *
	 */
	public enum HttpMethod
	{
		POST, PUT, GET, HEAD, DELETE, CONNECT, OPTIONS, TRACE, PATCH
	}

	/**
	 *
	 */
	public static final String KEEPALIVE = "keep-alive";

	static int DEFAULT_CONNECTION_TIMEOUT = 42000;

	/**
	 * Returns connection timeout in milliseconds as integer.
	 *
	 * @return connectionTimeout in milliseconds
	 */
	public static void setConnectionTimeout(final int timeout)
	{
		DEFAULT_CONNECTION_TIMEOUT = timeout;
	}

	/**
	 * Returns connection timeout in milliseconds as integer.
	 *
	 * @return connectionTimeout in milliseconds
	 */
	public static int getConnectionTimeout()
	{
		return DEFAULT_CONNECTION_TIMEOUT;
	}

	/**
	 *
	 */
	public static final String ACCEPT = "accept";
	public static final String CLOSE = "close";

	/**
	 *
	 */
	public static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";

	/**
	 * strings that must be escaped in urls
	 */
	public static final String m_URIEscape = "|#%?@&=+$;[]\\\"\'<>^{}~*";

	/**
	 * strings that must be escaped in uncs
	 */
	public static final String m_UNCEscape = "/\\*?<>|";
	/**
	 *
	 */
	public static final String TEXT_HTML = "text/html";
	/**
	 *
	 */
	public static final String TEXT_PLAIN = "text/plain";
	/**
	 *
	 */
	public static final String TEXT_UNKNOWN = JDFCoreConstants.MIME_TEXTUNKNOWN;
	/**
	 * the preferred value for XML!
	 */
	public static final String APPLICATION_XML = "application/xml";
	/**
	 * zip, maybe?
	 */
	public static final String APPLICATION_CFF2 = "application/cff2";
	public static final String APPLICATION_ZIP = "application/zip";
	/**
	 * zip, maybe?
	 */
	public static final String APPLICATION_XZIP = "application/x-zip-compressed";
	/**
	 * pdf, duh...
	 */
	public static final String APPLICATION_PDF = JDFCoreConstants.MIME_PDF;
	/**
	 * ps, duh...
	 */
	public static final String APPLICATION_PS = JDFCoreConstants.MIME_PS;
	/**
	 *
	 */
	public static final String TEXT_XML = JDFCoreConstants.MIME_TEXTXML;
	/**
	 *
	 */
	public static final String TEXT_CSV = "text/csv";
	public static final String TEXT_JSON = "text/json";
	public static final String APPLICATION_JSON = "application/json";
	/**
	 *
	 */
	public static final String VND_PPF = JDFCoreConstants.MIME_CIP3;
	public static final String VND_PTK = JDFCoreConstants.MIME_PTK;
	public static final String VND_PTK_J = JDFCoreConstants.MIME_PTK_JSON;
	public static final String VND_JDF = JDFCoreConstants.MIME_JDF;
	public static final String VND_JMF = JDFCoreConstants.MIME_JMF;
	public static final String VND_XJDF = JDFCoreConstants.MIME_XJDF;
	public static final String VND_XJMF = JDFCoreConstants.MIME_XJMF;
	public static final String VND_XJDF_J = JDFCoreConstants.MIME_XJDF_JSON;
	public static final String VND_XJMF_J = JDFCoreConstants.MIME_XJMF_JSON;
	/**
	 *
	 */
	public static final String CONTENT_ID = "Content-ID";
	/**
	 * more commonly used strings
	 */
	public static final String CONTENT_TYPE = "Content-Type";
	/**
	 * more commonly used strings
	 */
	public static final String CONTENT_LENGTH = "Content-Length";
	/**
	 *
	 */
	public static final String BASE64 = "base64";
	/**
	 *
	 */
	public static final String BINARY = "binary";
	public static final String CONNECTION = "Connection";
	public static final String AUTHORIZATION = "Authorization";

	/**
	 * rough classification of protocol type
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	public static enum URLProtocol
	{
		/** mime */
		cid,
		/** http or https */
		http,
		/*** file */
		file,
		/*** ftp */
		ftp
	}

	/**
	 * returns the relative URL of a file relative to the current working directory
	 *
	 * @param f the file to get the relative url for
	 * @param baseDir the file that describes cwd, if <code>null</code> cwd is calculated
	 * @param bEscape128 if true, escape > 128 (URL), else retain (IRL)
	 * @return
	 */
	public static String getRelativeURL(final File f, final File baseDir, final boolean bEscape128)
	{
		String relPath = getRelativePath(f, baseDir);
		if (relPath == null)
		{
			return fileToUrl(f, true);
		}

		relPath = StringUtil.replaceChar(relPath, '\\', "/", 0);
		final byte[] utf8 = StringUtil.getUTF8Bytes(relPath);
		relPath = new String(utf8);
		relPath = UrlUtil.escape(relPath, bEscape128, false);
		return relPath;
	}

	/**
	 * get a prinect url for this host and path
	 *
	 * @param bSecure if true, make https
	 * @param host hostname
	 * @param port the port, duh
	 * @param path may be null
	 * @return
	 */
	public static String createHttpUrl(final boolean bSecure, final String host, final int port, String path)
	{
		if (path != null && !path.startsWith("/"))
			path = "/" + path;
		try
		{
			if (port > 0)
			{
				final URL url = new URL("http" + (bSecure ? "s" : ""), host, port, path);
				return url.toExternalForm();
			}
			else
			{
				final URL url = new URL("http" + (bSecure ? "s" : ""), host, path);
				return url.toExternalForm();
			}
		}
		catch (final MalformedURLException x)
		{
			return null;
		}
	}

	/**
	 * returns the relative URL of a file relative to the current working directory<br>
	 * this includes escaping of %20 etc.
	 *
	 * @param f the file to get the relative path for
	 * @param fCWD the file that describes cwd, if <code>null</code> cwd is calculated from user.dir
	 * @return
	 */
	public static String getRelativePath(final File f, File fCWD)
	{
		if (fCWD == null)
		{
			fCWD = new File(System.getProperty("user.dir"));
		}

		String cPath = null;
		String cwd = null;
		try
		{
			cPath = f.getCanonicalPath();
			// just in case...
			cwd = fCWD.getCanonicalPath();
			if (cPath.charAt(0) != cwd.charAt(0))
			{
				return null; // incompatible abs paths
			}

		}
		catch (final IOException e)
		{
			return null;
		}
		final VString vCwd = StringUtil.tokenize(cwd, "/\\", false);
		final VString vPath = StringUtil.tokenize(cPath, "/\\", false);

		int lenPath = vPath.size();
		int siz = vCwd.size();
		if (lenPath < siz)
		{
			siz = lenPath;
		}
		for (int i = 0; i < siz; i++)
		{
			if (vCwd.get(0).equals(vPath.get(0)))
			{
				vCwd.remove(0);
				vPath.remove(0);
			}
			else
			{
				break;
			}
		}
		lenPath = vPath.size();
		siz = vCwd.size();
		String prefix = (siz == 0) ? "." : "..";

		for (int i = 1; i < siz; i++)
		{
			prefix += "/..";
		}

		String s = lenPath == 0 ? prefix : StringUtil.setvString(vPath, "/", prefix + "/", null);
		s = cleanDots(s);
		return StringUtil.replaceString(s, "/", File.separator);
	}

	/**
	 * get the file name for a url. extract it from a mime package, if appropriate
	 *
	 * @param url
	 * @param mp
	 * @return
	 */
	public static String getFileName(final String url, final Multipart mp)
	{
		if (mp != null && !isNotCID(url))
		{
			final BodyPart bp = MimeUtil.getPartByCID(mp, url);
			final String ret = MimeUtil.getFileName(bp);
			if (ret != null)
			{
				return ret;
			}
		}
		return urlToFileName(url);
	}

	/**
	 * get a readable inputstream from the CID url
	 *
	 * @param url the url to get a stream for
	 * @param multipart the multipart mime to which the cid refers
	 *
	 * @return InputStream - the readable input stream that this filespec refers to, <code>null</code> if broken or non-existent
	 *
	 */
	public static InputStream getCidURLStream(final String url, final Multipart multipart)
	{
		if (StringUtil.isEmpty(url))
		{
			return null;
		}
		final MimeHelper mimeHelper = new MimeHelper(multipart);
		BodyPartHelper bp = mimeHelper.getPartHelperByCID(url);
		if (bp == null)
			bp = mimeHelper.getPartHelperByLocalName(url);

		return bp == null ? null : bp.getInputStream();
	}

	/**
	 * get the filename extension of pathName excluding the '.' if no '.' is found, returns null if trailing . is found, returns ""
	 *
	 * @param pathName the pathName to get the extension for
	 * @return String - the filename extension
	 */
	public static String extension(final String pathName)
	{
		if (pathName == null)
		{
			return null;
		}

		final String filename = FilenameUtils.getName(pathName);
		final int index = filename.lastIndexOf(".");
		return (index == -1) ? null : filename.substring(index + 1);
	}

	/**
	 * get the path name without extension of pathName
	 *
	 * @param pathName the pathName to get the extension for
	 * @return String - the filename without extension
	 * @deprecated use prefix(pathName)
	 */
	@Deprecated
	public static String removeExtension(final String pathName)
	{
		return prefix(pathName);
	}

	/**
	 * get an array of urlparts, regardless of whether this was mime or not if the stream is mime/multipart get also extract that
	 *
	 * @param connection
	 *
	 * @return the array of body parts input stream
	 */
	public static UrlPart[] getURLParts(final HttpURLConnection connection)
	{
		if (connection == null)
		{
			return null;
		}
		final String urlContentType = connection.getContentType();
		if (!MimeUtil.MULTIPART_RELATED.equalsIgnoreCase(urlContentType))
		{
			UrlPart p;
			try
			{
				p = new UrlPart(connection);
			}
			catch (final IOException x)
			{
				return null;
			}
			return new UrlPart[] { p };
		}

		Multipart mp;
		try
		{
			mp = MimeUtil.getMultiPart(connection.getInputStream());
		}
		catch (final IOException x)
		{
			return null;
		}
		final BodyPart bp[] = MimeUtil.getBodyParts(mp);
		if (bp == null)
		{
			return null;
		}
		final UrlPart[] parts = new UrlPart[bp.length];
		for (int i = 0; i < bp.length; i++)
		{
			try
			{
				bp[i].getContentType();
				parts[i] = new UrlPart(bp[i]);
			}
			catch (final MessagingException e)
			{
				parts[i] = null;
			}
			catch (final IOException e)
			{
				parts[i] = null;
			}

		}
		return parts;
	}

	/**
	 * get the opened input stream for a given url string
	 *
	 * @param urlString
	 * @param bodyPart
	 * @param zr
	 * @return
	 */
	public static InputStream getURLInputStream(final String urlString, final BodyPart bodyPart, final ZipReader zr)
	{
		final URLReader reader = new URLReader(urlString);
		reader.setBodyPart(bodyPart);
		reader.setZipReader(zr);
		return reader.getURLInputStream();
	}

	/**
	 * get the opened input stream for a given url string
	 *
	 * @param urlString
	 * @param bodyPart
	 * @return
	 */
	public static InputStream getURLInputStream(final String urlString, final BodyPart bodyPart)
	{
		return getURLInputStream(urlString, bodyPart, null);
	}

	/**
	 * @param urlString
	 * @return
	 */
	public static InputStream getURLInputStream(final String urlString)
	{
		return getURLInputStream(urlString, null);
	}

	/**
	 * create a new directory and return null if the directory could not be created
	 *
	 * @param newDir the path or URL of the new directory
	 * @return
	 * @deprecated use FileUtil.getCreateDirectory(newDir);
	 */
	@Deprecated
	public static File getCreateDirectory(final String newDir)
	{
		return FileUtil.getCreateDirectory(newDir);
	}

	/**
	 * Convert a File to a valid file URL or IRL<br>
	 * note that some internal functions use network protocol and therefor performance may be non-optimal
	 *
	 * @param f the File to parse,
	 * @param bEscape128 if true, escape non -ascii chars (URI), if false, don't (IRI)
	 * @return the URL string
	 */
	public static String fileToUrl(final File f, final boolean bEscape128)
	{
		if (f == null)
		{
			return null;
		}
		String s = f.getAbsolutePath();
		if (PlatformUtil.isWindows())
		{
			s = StringUtil.replaceChar(s, '\\', JDFConstants.SLASH, 0);
		}
		s = UrlUtil.cleanDots(s);
		s = escape(s, bEscape128, false);
		if (s != null && s.charAt(0) != '/')
		{
			s = "///" + s;
		}

		return FILE + s;
	}

	/**
	 * @param urlString the string to parse for a file name
	 * @return the filename
	 */
	public static String urlToFileName(String urlString)
	{
		if (urlString == null)
		{
			return null;
		}
		int posSlash = urlString.lastIndexOf("/");
		posSlash = Math.max(posSlash, urlString.lastIndexOf("\\"));
		posSlash = Math.max(posSlash, urlString.lastIndexOf(":"));
		if (posSlash > 0)
		{
			urlString = urlString.substring(posSlash + 1);
		}
		final int posQ = urlString.lastIndexOf("?");
		if (posQ > 0)
		{
			urlString = urlString.substring(0, posQ);
		}
		if (urlString.length() == 0)
			return null;
		final File urlToFile = urlToFile(urlString);
		return urlToFile == null ? null : urlToFile.getName();

	}

	/**
	 * Retrieve a file for a relative or absolute file url
	 *
	 * @param urlString the file url to retrieve a file for
	 * @return the file located at url
	 */
	public static File urlToFile(String urlString)
	{
		if (urlString == null || isCID(urlString) || isNet(urlString))
		{
			return null;
		}

		if (isFile(urlString))
		{
			urlString = urlString.substring(5); // remove "file:"
		}

		if (StringUtil.getNonEmpty(urlString) == null)
		{
			return null;
		}

		final File f = new File(urlString);
		if (f.exists())
		{
			return f;
		}

		urlString = UrlUtil.unEscape(urlString);

		if (PlatformUtil.isWindows()) // on windows
		{
			if (urlString.startsWith("///") && urlString.length() > 6 && urlString.charAt(4) == ':' && urlString.charAt(5) == '/')
			{
				urlString = urlString.charAt(3) + ":" + urlString.substring(5);
			}
			else if (urlString.toLowerCase().startsWith("//localhost/") && urlString.length() > 15 && urlString.charAt(13) == ':' && urlString.charAt(14) == '/')
			{
				urlString = urlString.charAt(12) + ":" + urlString.substring(14);
			}
			else if (urlString.startsWith("/") && urlString.length() > 3 && urlString.charAt(1) != '/')
			{
				if (urlString.charAt(2) == '/')
				{
					urlString = urlString.charAt(1) + ":" + urlString.substring(2);
				}
				else if (urlString.charAt(2) == ':')
				{
					urlString = urlString.substring(1);
				}
			}
			else if (urlString.startsWith("/") && urlString.length() > 3 && urlString.charAt(2) == ':' && urlString.charAt(1) != '/')
			{
				urlString = urlString.charAt(1) + urlString.substring(2);
			}
			else if (urlString.startsWith("///"))
			{
				urlString = urlString.substring(2);
			}
		}

		return new File(urlString);
	}

	/**
	 * Retrieve a UNC path for a relative or absolute file url, any of '/'or "\\" is assumed a path separator
	 *
	 * @param urlString the file url to retrieve a UNC path for
	 * @return the UNC
	 */
	public static String urlToUNC(String urlString)
	{
		if (urlString == null || isCID(urlString) || isNet(urlString))
		{
			return null;
		}

		if (isFile(urlString))
		{
			urlString = urlString.substring(5); // remove "file:"
		}

		if (StringUtil.getNonEmpty(urlString) == null || isWindowsLocalPath(urlString))
		{
			return null;
		}
		urlString = StringUtil.replaceCharSet(urlString, "/\\", "" + (char) 0, 0);
		urlString = unEscape(urlString);
		urlString = StringUtil.escape(urlString, m_UNCEscape, "%", 16, 2, -1, -1);
		urlString = StringUtil.replaceChar(urlString, (char) 0, "\\", 0);
		return urlString;
	}

	/**
	 * @param unc
	 * @param escape128 if true escape chars>128
	 * @return
	 */
	public static String uncToUrl(final String unc, final boolean escape128)
	{
		if (!isUNC(unc))
		{
			final URL url = stringToURL(unc);
			return url == null ? null : url.toExternalForm();
		}
		String url = StringUtil.replaceCharSet(unc, "\\", "/", 0);
		url = escape(url, escape128, false);
		return FILE + url;
	}

	/**
	 * null safe url to string converter
	 *
	 * @param url
	 * @return
	 */
	public static String urlToString(final URL url)
	{
		if (url == null)
		{
			return null;
		}
		return url.toExternalForm();
	}

	/**
	 * adds a parameter to a given url using either ? or &
	 *
	 * @param baseUrl the base url - already escaped and ready to go
	 * @param key the key to add - NOT escaped
	 * @param val the value to add - NOT escaped - if null nothing is set
	 * @return the escaped new url
	 */
	public static String addParameter(final String baseUrl, String key, String val)
	{
		if (StringUtil.isEmpty(baseUrl) || StringUtil.isEmpty(key) || StringUtil.isEmpty(val))
			return baseUrl;
		final int posQMark = baseUrl.indexOf("?");
		final String flag = posQMark >= 0 ? "&" : "?";
		final StringBuilder buf = new StringBuilder(baseUrl);
		key = escape(key, true, false);
		val = escape(val, true, false);
		val = StringUtil.escape(val, ":/", "%", 16, 2, -1, -1);
		buf.append(flag).append(key).append("=").append(val);
		return buf.toString();
	}

	/**
	 * secure check of a file path
	 * 
	 * @param baseFile
	 * @param file
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static String getSecurePath(String url, final boolean allowAbsolute) throws IllegalArgumentException
	{
		url = unEscape(url);
		if (url == null || !allowAbsolute && !isRelativeURL(url))
			throw new IllegalArgumentException("URL must be relative " + url);
		if (StringUtil.hasToken(url, "..", "/\\", 0))
			throw new IllegalArgumentException("URL must not contain .. " + url);
		return cleanDots(url);
	}

	/**
	 * adds a parameter to a given url using either ? or &
	 *
	 * @param baseUrl the base url - already escaped and ready to go
	 * @param key the key to add - NOT escaped
	 * @param val the value to add - NOT escaped - if null nothing is set
	 * @return the escaped new url
	 */
	public static String setParameter(String baseUrl, final String key, final String val)
	{
		if (StringUtil.isEmpty(baseUrl) || StringUtil.isEmpty(key))
			return baseUrl;

		final String oldval = getParameter(baseUrl, key);
		if (oldval != null)
		{
			final String keyval = escape(key, true, false) + "=" + escape(oldval, true, false);
			baseUrl = baseUrl.replace(keyval, "");
			baseUrl = baseUrl.replace("?&", "?");
			baseUrl = baseUrl.replace("&&", "&");
			if (baseUrl.endsWith("?") || baseUrl.endsWith("&"))
				baseUrl = StringUtil.leftStr(baseUrl, -1);
		}
		return addParameter(baseUrl, key, val);
	}

	public static String getParameter(final String baseUrl, final String key)
	{
		if (!StringUtil.isEmpty(baseUrl) && !StringUtil.isEmpty(key))
		{
			final String keye = escape(key, true, false) + "=";
			final int pos = baseUrl.indexOf(keye);
			if (pos > 0 && "?&".contains(baseUrl.substring(pos - 1, pos)))
			{
				int posEnd = baseUrl.indexOf('&', pos);
				if (posEnd < 0)
					posEnd = baseUrl.length();
				final String val = baseUrl.substring(pos + keye.length(), posEnd);
				return unEscape(val);
			}

		}
		return null;

	}

	/**
	 * adds a path to a given url , keeping the parameters
	 *
	 * @param baseUrl the base url - already escaped and ready to go
	 * @param path the path to add
	 * @return the escaped new url
	 */
	public static String addPath(final String baseUrl, final String path) throws IllegalArgumentException
	{

		getSecurePath(baseUrl, true);
		if (path == null)
		{
			return baseUrl;
		}
		getSecurePath(path, false);
		String request = StringUtil.token(baseUrl, 0, "?");
		request = StringUtil.addToken(request, "/", path);
		final String params = StringUtil.token(baseUrl, 1, "?");
		if (params != null)
			request += "?" + params;

		return cleanDots(request);
	}

	/**
	 * standard url escaping
	 *
	 * @param toEscape the string to escape
	 * @param bEscape128 if true, also escape >128, else leave non-ascii7 as is
	 * @return the escaped string
	 *
	 */
	public static String escape(final String toEscape, final boolean bEscape128)
	{
		return escape(toEscape, bEscape128, false);
	}

	/**
	 * standard url escaping
	 *
	 * @param toEscape the string to escape
	 * @param bEscape128 if true, also escape >128, else leave non-ascii7 as is
	 * @param escapeSlash if true also escape '/'
	 * @return the escaped string
	 */
	public static String escape(String toEscape, final boolean bEscape128, final boolean escapeSlash)
	{
		if (toEscape == null)
			return null;
		final String esc = escapeSlash ? m_URIEscape + JDFConstants.SLASH : m_URIEscape;
		if (bEscape128)
		{
			final byte[] utf8Bytes = StringUtil.getUTF8Bytes(toEscape);
			toEscape = new String(utf8Bytes);
			toEscape = StringUtil.escape(toEscape, esc, "%", 16, 2, 0x21, 127);
		}
		else
		{
			toEscape = StringUtil.escape(toEscape, esc, "%", 16, 2, 0x21, 0x7fffffff);
		}
		return toEscape;
	}

	/**
	 * standard url unescaping
	 *
	 * @param toEscape the string to unescape
	 * @return the escaped string
	 */
	public static String unEscape(final String toEscape)
	{
		return StringUtil.unEscape(toEscape, "%", 16, 2);
	}

	/**
	 * Create a URL for any url string using heuristics and escaping
	 *
	 * @param urlString the file url to retrieve a file for
	 * @return
	 */
	public static URL stringToURL(String urlString)
	{

		URL url = null;

		if (StringUtil.getNonEmpty(urlString) == null)
		{
			return null;
		}
		if (isUNC(urlString))
		{
			try
			{
				return new URL(uncToUrl(urlString, true));
			}
			catch (final MalformedURLException e)
			{
				return null;
			}
		}
		if (isEscaped(urlString))
		{
			urlString = unEscape(urlString);
		}

		try
		{
			if (isCID(urlString) || isNet(urlString))
			{
				url = new URL(urlString);
			}
			else
			{
				final String fileToUrl = fileToUrl(urlToFile(urlString), true);
				url = fileToUrl == null ? null : new URL(fileToUrl);
			}
		}
		catch (final MalformedURLException x)
		{
			// nop
		}

		return url;
	}

	/**
	 * checks whether there is a remote chance that the file is useful for reading
	 *
	 * @param f - File to check
	 * @return true if the file is ok
	 */
	public static boolean isFileOK(final File f)
	{
		return f != null && f.exists() && !f.isDirectory() && f.canRead();
	}

	/**
	 * test whether a given url is escaped as utf-8
	 *
	 * @param url the url to test
	 * @return
	 */
	public static boolean isEscaped(final String url)
	{
		if (url == null)
		{
			return false;
		}
		int posColon = url.indexOf(":");
		int posEscapedColon = url.toLowerCase().indexOf("%3a");
		if (posColon < 0 && posEscapedColon < 0)
		{
			return false;
		}

		if (posColon < 0)
		{
			posColon += Integer.MAX_VALUE;
		}
		if (posEscapedColon < 0)
		{
			posEscapedColon += Integer.MAX_VALUE;
		}
		return posEscapedColon < posColon;
	}

	/**
	 * generates the correct extension for a given mime content type
	 *
	 * @param contentType
	 * @return
	 */
	public static String getExtensionFromMimeType(final String contentType)
	{
		if (contentType == null)
		{
			return null;
		}
		getMimeTypeFromURL(null);
		String extension = mimeMap.getKey(contentType);
		if (extension == null)
		{
			if (isZIPType(contentType))
			{
				extension = ZIP;
			}
			else if (isXMLType(contentType))
			{
				extension = "xml";
			}
			else if (isJSONType(contentType))
			{
				extension = "json";
			}

		}
		return extension;
	}

	/**
	 * generates the correct MIMEType for a given URL and sets it
	 *
	 * @param url
	 * @return
	 */
	public static String getMimeTypeFromURL(final String url)
	{
		if (mimeMap == null)
		{
			mimeMap = new BiHashMap<>();
			mimeMap.setUnique(false);
			mimeMap.put("pdf", JDFCoreConstants.MIME_PDF);
			mimeMap.put("ps", JDFCoreConstants.MIME_PS);

			mimeMap.put("cip", JDFCoreConstants.MIME_CIP3);
			mimeMap.put("cip3", JDFCoreConstants.MIME_CIP3);
			mimeMap.put("ppf", JDFCoreConstants.MIME_CIP3);

			mimeMap.put("ppml", JDFCoreConstants.MIME_PPML);
			mimeMap.put("ptk", JDFCoreConstants.MIME_PTK);
			mimeMap.put("xjdf", JDFCoreConstants.MIME_XJDF);
			mimeMap.put("xjmf", JDFCoreConstants.MIME_XJMF);
			mimeMap.put("jdf", JDFCoreConstants.MIME_JDF);
			mimeMap.put("jmf", JDFCoreConstants.MIME_JMF);
			mimeMap.put("jjdf", JDFCoreConstants.MIME_XJDF_JSON);
			mimeMap.put("jjmf", JDFCoreConstants.MIME_XJMF_JSON);

			mimeMap.put("xsl", TEXT_XML);
			mimeMap.put("xsd", TEXT_XML);
			mimeMap.put("xml", TEXT_XML);

			mimeMap.put("json", APPLICATION_JSON);
			mimeMap.put("jsn", APPLICATION_JSON);

			mimeMap.put("csv", TEXT_CSV);
			mimeMap.put("txt", TEXT_UNKNOWN);

			mimeMap.put("jpg", JDFCoreConstants.MIME_JPG);
			mimeMap.put("jpeg", JDFCoreConstants.MIME_JPG);
			mimeMap.put("png", JDFCoreConstants.MIME_PNG);
			mimeMap.put("tif", JDFCoreConstants.MIME_TIFF);
			mimeMap.put("tiff", JDFCoreConstants.MIME_TIFF);

			mimeMap.put("mjm", MimeUtil.MULTIPART_RELATED);
			mimeMap.put("mjd", MimeUtil.MULTIPART_RELATED);
			mimeMap.put("mim", MimeUtil.MULTIPART_RELATED);

			mimeMap.put(ZIP, APPLICATION_ZIP);

			mimeMap.put("cf2", APPLICATION_CFF2);
			mimeMap.put("cff2", APPLICATION_CFF2);
		}
		final String extension = UrlUtil.extension(url);
		final String mimeType = extension == null ? null : mimeMap.get(extension.toLowerCase());
		return mimeType == null ? JDFCoreConstants.MIME_TEXTUNKNOWN : mimeType;
	}

	/**
	 * test whether a given url is a cid (cid:)
	 *
	 * @param url the url to test
	 * @return
	 */
	public static boolean isCID(String url)
	{

		if (url == null)
		{
			return false;
		}
		if (url.startsWith("<"))
		{
			url = url.substring(1);
		}
		final String lowerURL = url.toLowerCase();
		return lowerURL.startsWith("cid:");
	}

	/**
	 * test whether a given url is NOT a cid this may be a local identifier
	 *
	 * @param url the url to test
	 * @return true if we are either a cid or a local url
	 */
	public static boolean isNotCID(final String url)
	{
		if (StringUtil.getNonEmpty(url) == null)
		{
			return true;
		}
		if (isCID(url))
		{
			return false;
		}
		if (isNet(url))
		{
			return true;
		}
		if (isFile(url))
		{
			return true;
		}
		return false;
	}

	/**
	 * is this a valid file url
	 *
	 * @param url
	 * @return
	 */
	public static boolean isFile(final String url)
	{
		if (url == null)
			return false;
		return url.toLowerCase().startsWith(FILE);
	}

	/**
	 * @param pathName
	 * @return
	 */
	public static boolean isWindowsLocalPath(final String pathName)
	{
		if (pathName == null || pathName.length() <= 1 || isUNC(pathName))
		{
			return false;
		}
		return StringUtils.isAlpha(pathName.substring(0, 1)) && pathName.substring(1, 2).equals(":")
				|| StringUtils.countMatches(pathName, "\\") > StringUtils.countMatches(pathName, "/");

	}

	// ///////////////////////////////////////////////////////////////

	/**
	 * test whether a given url is an http url (excluding https - @see isHttps)
	 *
	 * @param url the url to test
	 * @return
	 */
	public static boolean isHttp(final String url)
	{
		if (url == null)
		{
			return false;
		}
		final String lowerURL = url.toLowerCase();
		return lowerURL.startsWith(HTTP);
	}

	/**
	 * test whether a given url is an http url (excluding https - @see isHttps)
	 *
	 * @param url the url to test
	 * @return
	 */
	public static boolean isFtp(final String url)
	{
		if (url == null)
		{
			return false;
		}
		final String lowerURL = url.toLowerCase();
		return lowerURL.startsWith(FTP);
	}

	/**
	 * test whether a given url is an https url
	 *
	 * @param url the url to test
	 * @return
	 */
	public static boolean isHttps(final String url)
	{
		if (url == null)
		{
			return false;
		}
		final String lowerURL = url.toLowerCase();
		return lowerURL.startsWith(HTTPS);
	}

	/**
	 * test whether a given url is any network style url, currently either http, https or ftp
	 *
	 * @param url the url to test
	 * @return
	 */
	public static boolean isNet(final String url)
	{
		if (url == null)
		{
			return false;
		}
		final String lowerURL = url.toLowerCase();
		return lowerURL.startsWith(HTTP) || lowerURL.startsWith(HTTPS) || lowerURL.startsWith(FTP);
	}

	/**
	 * normalize a url string by grinding it through url
	 *
	 * @param urlString
	 * @return the normalized string, null if not a valid url
	 */
	public static String normalize(String urlString)
	{
		if (UrlUtil.isUNC(urlString))
		{
			return uncToUrl(urlString, false);
		}
		else if (UrlUtil.isFile(urlString))
		{
			urlString = FILE + urlString.substring(5);
			urlString = UrlUtil.unEscape(urlString);
			urlString = UrlUtil.escape(urlString, false, false);
		}
		else if (UrlUtil.isCID(urlString))
		{
			urlString = StringUtil.replaceCharSet(urlString, "<>", null, 0);
			urlString = "cid:" + urlString.substring(4);
		}
		else if (!UrlUtil.isRelativeURL(urlString))
		{
			final URL url = stringToURL(urlString);
			urlString = urlToString(url);
		}
		urlString = UrlUtil.cleanDots(urlString);

		return urlString;
	}

	/**
	 * test whether a given url is a unc path
	 *
	 * @param pathName
	 * @return
	 */
	public static boolean isUNC(final String pathName)
	{
		if (pathName == null || pathName.length() == 0)
		{
			return false;
		}
		return pathName.startsWith("\\\\");
	}

	/**
	 * check whether a file is a mime file only check extensions TODO sniff file rather than check extensions
	 *
	 * @param file the FILE to check
	 * @return true if the file is a MIME file
	 */

	public static boolean isMIME(final File file)
	{
		String packageName;
		try
		{
			packageName = file.getCanonicalPath();
		}
		catch (final IOException x)
		{
			return false;
		}
		final String lower = packageName.toLowerCase();
		return isMIMEExtenstension(lower);
	}

	/**
	 * returns the general protocol type of a url
	 *
	 * @param url
	 * @return
	 */
	public static URLProtocol getProtocol(final String url)
	{
		if (isCID(url))
			return URLProtocol.cid;
		if (isHttp(url) || isHttps(url))
			return URLProtocol.http;
		if (isFile(url))
			return URLProtocol.file;
		if (isFtp(url))
			return URLProtocol.ftp;
		return null;
	}

	/**
	 * get the ip address as a string with the left byte at pos 0
	 *
	 * @param ip
	 * @return
	 */
	public static String getIPFromBytes(final byte[] ip)
	{
		if ((ip == null) || (ip.length != 4 && ip.length != 6))
		{
			return null;
		}
		final StringBuilder b = new StringBuilder(32);
		for (int i = 0; i < ip.length; i++)
		{
			b.append(ip[i]);
			if (i > 0)
				b.append('.');
		}
		return b.toString();
	}

	/**
	 * get the ip address as a set of bytes with the left byte at pos 0
	 *
	 * @param ip
	 * @return
	 */
	public static byte[] getBytesFromIP(final String ip)
	{
		final VString v = StringUtil.tokenize(ip, ".", false);
		if (v == null || v.size() < 4)
			return null;
		final byte[] b = new byte[v.size()];
		int n = 0;
		for (final String s : v)
		{
			final int i = StringUtil.parseInt(s, -1);
			if (i > 255 || i < 0)
				return null;
			b[n++] = (byte) i;
		}
		return b;
	}

	/**
	 * check whether a file is a mime file
	 *
	 * @param lower
	 * @return
	 */
	public static boolean isMIMEExtenstension(String lower)
	{
		lower = extension(lower);
		if (lower == null)
		{
			return false;
		}

		return lower.equalsIgnoreCase("mjm") || lower.equalsIgnoreCase("mjd") || lower.equalsIgnoreCase("mim");
	}

	/**
	 * @param val
	 * @return
	 */
	public static boolean isIRL(final String val)
	{
		final char c[] = val.toCharArray();
		boolean bFix = false;
		for (int i = 0; i < c.length; i++)
		{
			if (c[i] > 127)
			{
				c[i] = 'a'; // any valid char
				bFix = true;
			}
		}

		return isURL(bFix ? new String(c) : val);
	}

	/**
	 * remove the protocol part of a url, if it is specified
	 *
	 * @param url the url string to work on
	 * @return the input string without the protocol and ":"
	 */
	public static String removeProtocol(String url)
	{
		if (url == null)
			return null;
		final int pos = url.indexOf("://");
		if (pos > -1)
			url = url.substring(pos + 3);
		else if (isCID(url))
			url = StringUtil.rightStr(url, -4);
		return StringUtil.getNonEmpty(url);
	}

	/**
	 * @param val
	 * @return
	 */
	public static boolean isURL(final String val)
	{
		if (val == null)
			return false;

		try
		{
			final URI uri = new URI(val);
			final String scheme = uri.getScheme();
			if (scheme != null && scheme.toLowerCase().startsWith("http"))
			{
				if (uri.getHost() == null)
				{
					return false;
				}
			}
			// add any other exceptions here
		}
		catch (final URISyntaxException x)
		{
			return false;
		}
		return val.length() < 4096;
	}

	/**
	 * get the local url without directory schemes in the base url are case insensitive, all others are case sensitive
	 *
	 * @param directory the url of the directory
	 * @param url the absolute url
	 * @return String - the local URL of url after removing directory
	 */
	public static String getLocalURL(final String directory, final String url)
	{
		if (directory == null || url == null || directory.length() == 0)
		{
			return url;
		}
		int len = directory.length();
		if (len > 0 && !directory.endsWith("/"))
		{
			len++;
		}
		if (url.length() <= len)
		{
			return null;
		}
		final VString vDirectory = StringUtil.tokenize(directory, "/", false);
		final VString vURL = StringUtil.tokenize(url, "/", false);
		if (vDirectory.size() >= vURL.size())
			return null;
		if (vDirectory.size() > 0 && vURL.size() > 0)
		{
			if (vDirectory.get(0).endsWith(":"))
				vDirectory.set(0, vDirectory.get(0).toLowerCase());
			if (vURL.get(0).endsWith(":"))
				vURL.set(0, vURL.get(0).toLowerCase());
		}
		for (final String dirChunk : vDirectory)
		{
			final String urlChunk = vURL.get(0);
			if (urlChunk.equals(dirChunk))
				vURL.remove(0);
			else
				return null;

		}
		return StringUtil.setvString(vURL, "/", null, null);
	}

	/**
	 * securely add a subdirectory
	 * 
	 * @param baseURL
	 * @param url
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static String addSecure(final String baseURL, final String url) throws IllegalArgumentException
	{
		if (!isRelativeURL(url))
			throw new IllegalArgumentException("url must be relative " + url);
		if (StringUtil.isEmpty(baseURL))
			throw new IllegalArgumentException("base url must not be empty");
		if (isRelativeURL(baseURL))
			throw new IllegalArgumentException("base url must not be relative " + baseURL);
		if (StringUtil.hasToken(baseURL, "..", "/\\", 0))
			throw new IllegalArgumentException("base url must not contain .. " + baseURL);
		if (StringUtil.hasToken(url, "..", "/\\", 0))
			throw new IllegalArgumentException("url must not contain .. " + url);

		return StringUtil.addToken(baseURL, JDFConstants.SLASH, url);

	}

	/**
	 * concatenate directory and url to a single path IF and only IF url is a relative url<br>
	 * relative urls MUST NOT have a scheme (e.g. file:)
	 *
	 * @param directory the url of the directory
	 * @param url the relative url of the file
	 * @return String - the concatenated URL of the directory + file
	 */
	public static String getURLWithDirectory(String directory, String url)
	{

		if (StringUtil.getNonEmpty(directory) == null)
		{
			return url;
		}

		if (url == null)
		{
			return directory;
		}

		if (url.indexOf(":") > 0 && ((url.indexOf("/") < 0) || url.indexOf("/") > url.indexOf(":")))
		{
			// scheme
			return url;
		}

		if (url.startsWith("/"))
		{
			try
			{
				final URI dirURI = new URI(directory);
				final String scheme = dirURI.getScheme();
				if (scheme != null)
				{
					directory = scheme + ":";
					if (!url.startsWith("//"))
					{
						url = "/" + url;
					}
				}
			}
			catch (final URISyntaxException x)
			{
				// nop
			}
		}

		if (!directory.endsWith("/") && !url.startsWith("/"))
		{
			directory += "/";
		}

		return cleanDots(directory + url);
	}

	/**
	 * get the parent directory of a file or url
	 *
	 * @param url
	 * @return
	 */
	public static String getParentDirectory(final String url)
	{

		if (url == null)
		{
			return null;
		}
		int pos = url.lastIndexOf('/');
		final int pos2 = url.lastIndexOf('\\');
		if (pos2 >= 0 && pos2 > pos)
			pos = pos2;

		return pos > 0 ? StringUtil.leftStr(url, pos) : ".";
	}

	/**
	 * remove any internal "../" "./" and "//" from a url
	 *
	 * @param url the url to clean
	 * @return String - the clean path
	 */
	public static String cleanDots(String url)
	{
		if (url == null)
		{
			return null;
		}
		while (url.length() > 2 && url.startsWith("./"))
			url = url.substring(2);
		final int posDouble = url.indexOf("//");
		final int posTriple = url.indexOf("///");
		final int prefixLen = posDouble == posTriple ? 3 : 2;
		String prefix = url.startsWith("/") ? "/" : "";
		if (posDouble >= 0)
		{
			prefix = url.substring(0, posDouble + prefixLen);
			url = url.substring(posDouble + prefixLen);
		}
		final VString vs = StringUtil.tokenize(url, "/", false);
		for (int i = vs.size() - 1; i > 0; i--)
		{
			if (vs.get(i).equals("") || vs.get(i).equals("."))
			{
				vs.remove(i);
			}
		}
		for (int i = vs.size() - 1; i > 0; i--)
		{
			if (vs.get(i).equals(".."))
			{
				for (int j = i - 1; j >= 0; j--)
				{
					if (!vs.get(j).equals(".."))
					{
						vs.remove(i--);
						vs.remove(j);
						break;
					}
				}
			}
		}
		return prefix + (vs.isEmpty() ? "." : StringUtil.setvString(vs, "/", null, null));
	}

	/**
	 * create a "real" url from a user input url add http://
	 *
	 * @param url the input url
	 * @return the - hopefully - usable url
	 */
	public static String cleanHttpURL(final String url)
	{
		final VString v = StringUtil.tokenize(url, "/", false);
		if (v == null)
			return null;
		String protocol = v.get(0);
		if (!protocol.startsWith("http"))
		{
			protocol = HTTP + SLASHSLASH;
		}
		else if (protocol.startsWith("https"))
		{
			protocol = HTTPS + SLASHSLASH;
			v.remove(0);
		}
		else
		{
			protocol = HTTP + SLASHSLASH;
			v.remove(0);
		}
		return StringUtil.setvString(v, "/", protocol, null);
	}

	/**
	 * write a Stream to an output URL File: and http: are currently supported Use HttpURLConnection.getInputStream() to retrieve the http response
	 *
	 * @param strUrl the URL to write to
	 * @param stream the input stream to read from
	 * @param method HEAD, GET or POST
	 * @param contentType the contenttype to set, if NULL defaults to TEXT/UNKNOWN
	 * @param det
	 * @return {@link UrlPart} the opened http connection, null in case of error
	 *
	 */
	public static UrlPart writeToURL(final String strUrl, final InputStream stream, final String method, final String contentType, final org.cip4.jdflib.util.net.HTTPDetails det)
	{
		final org.cip4.jdflib.util.URLWriter urlWriter = new org.cip4.jdflib.util.URLWriter(stream, strUrl, method, contentType, det);
		return urlWriter.writeToURL();
	}

	/**
	 * write the contents of an IStreamWriter to an output URL File: and http: are currently supported Use HttpURLConnection.getInputStream() to retrieve the http response
	 *
	 * @param strUrl the URL to write to
	 * @param streamWriter the IStreamWriter to read from
	 * @param method HEAD, GET or POST
	 * @param contentType the contenttype to set, if NULL defaults to TEXT/UNKNOWN
	 * @param det
	 * @return {@link UrlPart} the opened http connection, null in case of error
	 *
	 */
	public static UrlPart writerToURL(final String strUrl, final IStreamWriter streamWriter, final String method, final String contentType, final org.cip4.jdflib.util.net.HTTPDetails det)
	{
		final org.cip4.jdflib.util.URLWriter urlWriter = new org.cip4.jdflib.util.URLWriter(strUrl, streamWriter, method, contentType, det);
		return urlWriter.writeToURL();
	}

	/**
	 * physically store the file at the location specified in dir and also modify this to reflect the new location
	 *
	 * @param parent the parent element, trypically a filespec or preview
	 * @param dir the directory to move to. dir is created if it does not exist. If dir exists and dir is not a directory, the call fails and null is returned
	 * @param overWrite if true, zapp any old files with the same name
	 * @return the file that corresponds to the moved url reference, null if an error occurred
	 * @deprecated use moveToDir(parent, dir, null, overWrite);
	 */
	@Deprecated
	public static File moveToDir(final IURLSetter parent, final File dir, final boolean overWrite)
	{
		return moveToDir(parent, dir, null, overWrite);
	}

	/**
	 * physically store the file at the location specified in dir and also modify parent to reflect the new location
	 *
	 * @param urlSetter the parent element, typically a filespec or preview
	 * @param dir the directory to move to. dir is created if it does not exist. If dir exists and dir is not a directory, the call fails and null is returned
	 * @param cwd the current working dir for local urls
	 * @param overWrite if true, zapp any old files with the same name
	 * @return the file that corresponds to the moved url reference, null if an error occurred
	 */
	public static File moveToDir(final IURLSetter urlSetter, final File dir, final String cwd, final boolean overWrite)
	{
		return moveToDir(urlSetter, dir, cwd, overWrite, false);
	}

	/**
	 * physically store the file at the location specified in dir and also modify parent to reflect the new location
	 *
	 * @param urlSetter the parent element, typically a filespec or preview
	 * @param dir the directory to move to. dir is created if it does not exist. If dir exists and dir is not a directory, the call fails and null is returned
	 * @param cwd the current working dir for local urls
	 * @param overWrite if true, zapp any old files with the same name
	 * @throws IllegalArgumentException in case the filename contains '..'
	 * @return the file that corresponds to the moved url reference, null if an error occurred
	 */
	public static File moveToDir(final IURLSetter urlSetter, final File dir, final String cwd, final boolean overWrite, final boolean deleteFile)
	{
		if (urlSetter == null || dir == null || dir.exists() && !dir.isDirectory())
		{
			return null;
		}
		else
		{
			dir.mkdirs();
		}
		String url = urlSetter.getURL();
		if (StringUtil.getNonEmpty(url) == null)
		{
			return null;
		}

		String fileName = StringUtil.getNonEmpty(urlSetter.getUserFileName());

		if (fileName == null)
		{
			if (UrlUtil.isRelativeURL(url))
			{
				fileName = cleanDots(UrlUtil.unEscape(url));
				if (cwd != null)
				{
					url = UrlUtil.getURLWithDirectory(cwd, url);
				}
			}

			// check for nop
			final File oldFile = urlToFile(url);
			if (oldFile != null)
			{
				final File oldDir = oldFile.getParentFile();
				if (FileUtil.equals(oldDir, dir))
				{
					return oldFile;
				}
			}
		}

		final XMLDoc d = (urlSetter instanceof KElement) ? ((KElement) urlSetter).getOwnerDocument_KElement() : null;
		if (fileName == null)
		{
			final Multipart mp = d == null ? null : d.getMultiPart();
			fileName = getFileName(url, mp);
		}
		if (StringUtil.hasToken(fileName, "..", "/\\", 0))
		{
			throw new IllegalArgumentException("illegal filename " + fileName);
		}
		final File localFile = fileName == null ? null : new File(fileName);
		File out = FileUtil.getFileInDirectory(dir, localFile);
		if (out != null && out.exists())
		{
			if (overWrite)
			{
				out.delete();
			}
			else
			{
				return out;
			}
		}

		final URLReader urlReader = new URLReader(url, d);
		final File oldFile = deleteFile ? urlReader.getFile() : null;
		boolean needCopy = !deleteFile;
		if (oldFile != null)
		{
			needCopy = !FileUtil.moveFile(oldFile, out);
		}
		if (needCopy)
		{
			final InputStream inputStream = urlReader.getURLInputStream();
			if (inputStream != null)
			{
				out = FileUtil.streamToFile(inputStream, out);
			}
			else
			{
				out = null;
			}
		}
		if (out != null)
		{
			urlSetter.setURL(UrlUtil.fileToUrl(out, false));
		}
		return out;
	}

	/**
	 * if true this url is relative
	 *
	 * @param url the url string to test
	 * @return true if relative
	 */
	public static boolean isRelativeURL(String url)
	{
		url = StringUtil.token(url, 0, "?");
		if (StringUtil.isEmpty(url))
			return false;
		return url.indexOf(":/") < 0 && url.indexOf(":\\") < 0 && !url.startsWith("/") && !url.startsWith("\\") && !isCID(url);
	}

	/**
	 * check whether the mime type is a known xml dialect
	 *
	 * @param contentType
	 * @return
	 */
	public static boolean isXMLType(final String contentType)
	{
		if (contentType == null)
			return false;

		final String lower = normalizeType(contentType);

		if (TEXT_XML.equals(lower) || APPLICATION_XML.equals(lower))
			return true;
		return (lower.startsWith("application") || (lower.startsWith("text"))) && lower.endsWith("+xml");
	}

	/**
	 * check whether the mime type is a known xml dialect
	 *
	 * @param contentType
	 * @return
	 */
	public static boolean isJSONType(final String contentType)
	{
		if (contentType == null)
			return false;

		final String lower = normalizeType(contentType);
		return TEXT_JSON.equals(lower) || APPLICATION_JSON.equals(lower) || lower.endsWith("+json");
	}

	private static String normalizeType(final String contentType)
	{
		String lower = StringUtil.normalize(contentType, true);
		while (lower.endsWith(";"))
			lower = StringUtil.leftStr(lower, -1);
		return lower;
	}

	/**
	 * check whether the mime type is a known zip dialect
	 *
	 * @param contentType
	 * @return
	 */
	public static boolean isZIPType(final String contentType)
	{
		String lower = StringUtil.normalize(contentType, true);
		while (lower != null && lower.endsWith(";"))
			lower = StringUtil.leftStr(lower, -1);
		return lower != null && (APPLICATION_ZIP.equals(lower) || APPLICATION_XZIP.equals(lower) || lower.endsWith("+zip"));
	}

	/**
	 * check whether the url type is a known zip
	 *
	 * @param url
	 * @return
	 */
	public static boolean isZip(final String url)
	{
		final String ext = extension(url);
		return ZIP.equalsIgnoreCase(ext) || ZIP.equalsIgnoreCase(url);
	}

	/**
	 * replace the .extension of a file name
	 *
	 * @param strWork the file path
	 * @param newExt the new extension (works with or without the initial "."
	 * @return the strWork with a replaced extension
	 */
	public static String newExtension(final String strWork, String newExt)
	{
		if (strWork == null)
			return null;
		if (newExt == null)
		{
			return UrlUtil.prefix(strWork);
		}

		if (!newExt.startsWith("."))
		{
			newExt = "." + newExt;
		}

		return UrlUtil.prefix(strWork) + newExt;
	}

	/**
	 * inverse of extension
	 *
	 * @param strWork the string to work on
	 * @return the prefix
	 */
	public static String prefix(final String strWork)
	{
		if (strWork == null)
			return null;

		final String ext = UrlUtil.extension(strWork);
		if (ext == null)
		{
			return strWork;
		}

		return strWork.substring(0, strWork.length() - ext.length() - 1);
	}

	/**
	 * return true if the response code should redirect
	 *
	 * @param responseCode
	 * @return
	 */
	public static boolean isRedirect(final int responseCode)
	{
		return responseCode == 301 || responseCode == 302 || responseCode == 303 || responseCode == 307 || responseCode == 308;
	}

	/**
	 * return true if the response code should redirect
	 *
	 * @param responseCode
	 * @return
	 */
	public static boolean isReturnCodeOK(final int responseCode)
	{
		return responseCode / 100 == 2;
	}

	/**
	 * return true if the response code should redirect
	 *
	 * @param responseCode
	 * @return
	 */
	public static boolean isReturnCodeOK(final UrlPart p)
	{
		return p != null && isReturnCodeOK(p.getResponseCode());
	}

	private static BiHashMap<String, String> mimeMap = null;

}
