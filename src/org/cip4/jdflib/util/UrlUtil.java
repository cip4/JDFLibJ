/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2009 The International Cooperation for the Integration of 
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;

/**
 * collection of helper routines to convert urls
 * 
 * @author prosirai
 * 
 */
public class UrlUtil
{
	public static final String POST = "POST";
	public static final String GET = "GET";
	public static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";

	/**
	 * helper class to set mime details
	 * 
	 * @author prosirai
	 * 
	 */
	public static class HTTPDetails
	{
		/**
		 * size of http chunks to be written, if <=0 no chunks
		 */
		public int chunkSize = defaultChunkSize;
		public static int defaultChunkSize = -1; // don't chunk by default

		/**
		 * apply these details to the connection specified
		 * 
		 * @param urlCon
		 */
		public void applyTo(final HttpURLConnection urlCon)
		{
			if (urlCon != null)
			{
				if (chunkSize > 0)
				{
					urlCon.setChunkedStreamingMode(chunkSize);
				}
			}
		}
	}

	/**
	 * simple struct to contain the stream and type of a bodypart
	 * 
	 * @author prosirai
	 * 
	 */
	public static class UrlPart
	{
		/**
		 * @param connection
		 * @throws IOException
		 */
		public UrlPart(final URLConnection connection) throws IOException
		{
			inStream = connection.getInputStream();
			contentLength = connection.getContentLength();
			contentType = connection.getContentType();
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
		}

		public InputStream inStream;
		public String contentType;
		public int contentLength;

		/**
		 * returns an xmldoc corresponding to this part
		 * @return the doc, null if not xml
		 */
		public XMLDoc getXMLDoc()
		{
			final JDFParser p = new JDFParser();
			final XMLDoc d = p.parseStream(inStream);
			return d;
		}
	}

	// public static final String m_URIEscape = "%?:@&=+$,[]";
	public static final String m_URIEscape = "%?@&=+$,[]";
	public static final String TEXT_HTML = "text/html";
	public static final String TEXT_PLAIN = "text/plain";
	public static final String TEXT_UNKNOWN = JDFConstants.MIME_TEXTUNKNOWN;
	/**
	 * the preferred value!
	 */
	public static final String APPLICATION_XML = "application/xml";
	public static final String TEXT_XML = JDFConstants.MIME_TEXTXML;
	public static final String VND_JDF = JDFConstants.MIME_JDF;
	public static final String VND_JMF = JDFConstants.MIME_JMF;
	public static final String CONTENT_ID = "Content-ID";
	/**
	 * commonly used strings
	 */
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String BASE64 = "base64";
	public static final String BINARY = "binary";

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
		final byte[] utf8 = StringUtil.setUTF8String(relPath);
		relPath = new String(utf8);
		relPath = StringUtil.escape(relPath, m_URIEscape, "%", 16, 2, 0x21, bEscape128 ? 128 : -1);
		return relPath;
	}

	/**
	 * returns the relative URL of a file relative to the current working directory<br>
	 * this includes escaping of %20 etc.
	 * 
	 * @param f the file to get the relative path for
	 * @param fCWD the file that describes cwd, if <code>null</code> cwd is calculated
	 * @return
	 */
	public static String getRelativePath(final File f, final File fCWD)
	{
		File fCWDLocal = fCWD;

		if (fCWDLocal == null)
		{
			fCWDLocal = new File(System.getProperty("user.dir"));
		}

		String cPath = null;
		String cwd = null;
		try
		{
			cPath = f.getCanonicalPath();
			// just in case...
			cwd = fCWDLocal.getCanonicalPath();
			if (cPath.charAt(0) != cwd.charAt(0))
			{
				return null; // incompatible abs paths
			}

		}
		catch (final IOException e)
		{
			return null;
		}
		final VString vCwd = StringUtil.tokenize(cwd, File.separator, false);
		final VString vPath = StringUtil.tokenize(cPath, File.separator, false);

		int lenPath = vPath.size();
		int siz = vCwd.size();
		if (lenPath < siz)
		{
			siz = lenPath;
		}
		for (int i = 0; i < siz; i++)
		{
			if (vCwd.stringAt(0).equals(vPath.stringAt(0)))
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

		final String s = lenPath == 0 ? prefix : StringUtil.setvString(vPath, File.separator, prefix + File.separator, null);
		return cleanDots(s);
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
		if (url == null || url.equals(JDFConstants.EMPTYSTRING))
		{
			return null;
		}
		final BodyPart bp = MimeUtil.getPartByCID(multipart, url);
		if (bp == null)
		{
			return null;
		}

		try
		{
			return bp.getInputStream();
		}
		catch (final IOException e)
		{/* */
		}
		catch (final MessagingException e)
		{ /* */
		}
		return null; // snafu exit
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * get the filename extension of pathName
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

		final int index = pathName.lastIndexOf(".");
		return (index == -1) ? null : pathName.substring(index + 1);
	}

	/**
	 * get the filename extension of pathName
	 * 
	 * @param pathName the pathName to get the extension for
	 * @return String - the filename extension
	 */
	public static String removeExtension(final String pathName)
	{
		if (pathName == null)
		{
			return null;
		}

		final int index = pathName.lastIndexOf(".");
		return (index == -1) ? pathName : pathName.substring(0, index);
	}

	/**
	 * get an array of urlparts, regardless of whether this was mime or not if the stream is mime/multipart get also extract that
	 * 
	 * @return the array of body parts input stream
	 */
	public static UrlPart[] getURLParts(final URLConnection connection)
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
	 * @return
	 */
	public static InputStream getURLInputStream(final String urlString, final BodyPart bodyPart)
	{
		InputStream retStream = null;
		if (isCID(urlString) || bodyPart != null)
		{
			if (bodyPart != null)
			{
				final Multipart multipart = bodyPart.getParent();
				retStream = getCidURLStream(urlString, multipart);
			}
		}
		if (retStream == null && isHttp(urlString))
		{
			try
			{
				final URL url = new URL(urlString);
				final URLConnection urlConnection = url.openConnection();
				retStream = urlConnection.getInputStream();
			}
			catch (final MalformedURLException x)
			{
				//
			}
			catch (final IOException x)
			{
				//
			}
		}
		if (retStream == null) // assume file
		{
			final File f = urlToFile(urlString);
			if ((f != null) && f.canRead())
			{
				try

				{
					retStream = new FileInputStream(f);
				}
				catch (final FileNotFoundException x)
				{
					//
				}
			}
		}
		return retStream;
	}

	/**
	 * create a new directory and return null if the directory could not be created
	 * 
	 * @param newDir the path or URL of the new directory
	 */
	public static File getCreateDirectory(final String newDir)
	{
		final File f = UrlUtil.urlToFile(newDir);
		if (f == null)
		{
			return null;
		}
		if (!f.exists())
		{
			f.mkdirs();
		}
		if (!f.isDirectory())
		{
			return null;
		}
		return f;
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
		if (File.separator.equals("\\"))
		{
			s = StringUtil.replaceChar(s, '\\', "/", 0);
		}
		s = UrlUtil.cleanDots(s);
		if (bEscape128)
		{
			s = new String(StringUtil.setUTF8String(s));
			s = StringUtil.escape(s, m_URIEscape, "%", 16, 2, 0x21, 127);
		}
		else
		{
			s = StringUtil.escape(s, m_URIEscape, "%", 16, 2, 0x21, 0x7fffffff);
		}
		// if(s.length()>2 && s.charAt(1)==':' && File.separator.equals("\\"))
		// s=s.charAt(0)+s.substring(2);
		if (s.charAt(0) != '/')
		{
			s = "///" + s;
		}

		return "file:" + s;
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
		posSlash = urlString.lastIndexOf("?");
		if (posSlash > 0)
		{
			urlString = urlString.substring(0, posSlash);
		}
		return urlString.length() == 0 ? null : urlString;

	}

	/**
	 * Retrieve a file for a relative or absolute file url
	 * 
	 * @param urlString the file url to retrieve a file for
	 * @return the file located at url
	 */
	public static File urlToFile(String urlString)
	{
		if (urlString == null)
		{
			return null;
		}

		if (isCID(urlString) || isHttp(urlString))
		{
			return null;
		}

		if (urlString.toLowerCase().startsWith("file:"))
		{
			urlString = urlString.substring(5); // remove "file:"
		}

		if (StringUtil.getNonEmpty(urlString) == null)
		{
			return null;
		}

		final File f = new File(urlString);
		if (f.canRead())
		{
			return f;
		}
		if (File.separator.equals("\\")) // on windows
		{
			if (urlString.startsWith("///") && urlString.length() > 6 && urlString.charAt(4) == ':' && urlString.charAt(5) == '/')
			{
				urlString = urlString.charAt(3) + ":" + urlString.substring(5);
			}
			else if (urlString.startsWith("/") && urlString.length() > 3 && urlString.charAt(2) == '/' && urlString.charAt(1) != '/')
			{
				urlString = urlString.charAt(1) + ":" + urlString.substring(2);
			}
			else if (urlString.startsWith("///"))
			{
				urlString = urlString.substring(2);
			}
		}

		urlString = new String(StringUtil.setUTF8String(urlString)); // ensure that any non-utf8 gets encoded to utf-8
		urlString = StringUtil.unEscape(urlString, "%", 16, 2);
		urlString = StringUtil.getUTF8String(urlString.getBytes());

		return new File(urlString);
	}

	/**
	 * Create a URL for any url string using heuristics and escaping
	 * 
	 * @param urlString the file url to retrieve a file for
	 * @return
	 */
	public static URL StringToURL(final String urlString)
	{
		String urlStringLocal = urlString;

		URL url = null;

		if (urlStringLocal == null)
		{
			return url;
		}

		if (isEscaped(urlStringLocal))
		{
			urlStringLocal = StringUtil.unEscape(urlStringLocal, "%", 16, 2);
		}

		try
		{
			if (isCID(urlStringLocal) || isHttp(urlStringLocal))
			{
				url = new URL(urlStringLocal);
			}
			else
			{
				url = new URL(fileToUrl(urlToFile(urlStringLocal), true));
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
		return f != null && !f.isDirectory() && f.canRead();
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
	 * test whether a given url is a cid
	 * 
	 * @param url the url to test
	 * @return
	 */
	public static boolean isCID(final String url)
	{
		String urlLocal = url;

		if (urlLocal == null)
		{
			return false;
		}
		if (urlLocal.startsWith("<"))
		{
			urlLocal = urlLocal.substring(1);
		}
		final String lowerURL = urlLocal.toLowerCase();
		return lowerURL.startsWith("cid:");
	}

	public static boolean isWindowsLocalPath(final String pathName)
	{
		if (pathName == null || pathName.length() <= 1 || isUNC(pathName))
		{
			return false;
		}
		return StringUtils.isAlpha(pathName.substring(0, 1)) && pathName.substring(1, 2).equals(":") || StringUtils.countMatches(pathName, "\\") > StringUtils.countMatches(pathName, "/");

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
		return lowerURL.startsWith("http://");
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
		return lowerURL.startsWith("https://");
	}

	/**
	 * test whether a given url is a unc path
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
	 * check whether a file is a mime file
	 * 
	 * @param lower
	 * @return
	 */
	public static boolean isMIMEExtenstension(final String lower)
	{
		String lowerLocal = lower;

		lowerLocal = extension(lowerLocal);
		if (lowerLocal == null)
		{
			return false;
		}

		return lowerLocal.equalsIgnoreCase("mjm") || lowerLocal.equalsIgnoreCase("mjd") || lowerLocal.equalsIgnoreCase("mim");
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
	 * @param val
	 * @return
	 */
	public static boolean isURL(final String val)
	{
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
	 * concatenate directory and url to a single path IF and only IF url is a relative url<br>
	 * relative urls MUST NOT have a scheme (e.g. file:)
	 * 
	 * @param directory the url of the directory
	 * @param url the absolute url
	 * @return String - the local URL of url after removing directory
	 */
	public static String getLocalURL(final String directory, final String url)
	{
		if (directory == null || url == null)
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
		if (!url.startsWith(directory))
		{
			return null;
		}
		return url.substring(len);
	}

	/**
	 * concatenate directory and url to a single path IF and only IF url is a relative url<br>
	 * relative urls MUST NOT have a scheme (e.g. file:)
	 * 
	 * @param directory the url of the directory
	 * @param url the relative url of the file
	 * @return String - the concatenated URL of the directory + file
	 */
	public static String getURLWithDirectory(final String directory, final String url)
	{
		String directoryLocal = directory;
		String urlLocal = url;

		if (directoryLocal == null || JDFConstants.EMPTYSTRING.equals(directoryLocal))
		{
			return urlLocal;
		}

		if (urlLocal == null)
		{
			return directoryLocal;
		}

		if (urlLocal.indexOf(":") > 0 && ((urlLocal.indexOf("/") < 0) || urlLocal.indexOf("/") > urlLocal.indexOf(":")))
		{
			// scheme
			return urlLocal;
		}

		if (urlLocal.startsWith("/"))
		{
			try
			{
				final URI dirURI = new URI(directoryLocal);
				directoryLocal = dirURI.getScheme() + ":";
				if (!urlLocal.startsWith("//"))
				{
					urlLocal = "/" + urlLocal;
				}
			}
			catch (final URISyntaxException x)
			{
				// nop
			}
		}

		if (!directoryLocal.endsWith("/") && !urlLocal.startsWith("/"))
		{
			directoryLocal += "/";
		}

		return cleanDots(directoryLocal + urlLocal);
	}

	/**
	 * remove any internal "../" "./" and "//" from a url
	 * 
	 * @param url the url to clean
	 * @return String - the clean path
	 */
	public static String cleanDots(final String url)
	{
		if (url == null)
		{
			return null;
		}
		String dummy = url;
		final int posDouble = url.indexOf("//");
		String prefix = url.startsWith("/") ? "/" : "";
		if (posDouble >= 0)
		{
			prefix = url.substring(0, posDouble + 2);
			dummy = url.substring(posDouble + 2);
		}
		final VString vs = StringUtil.tokenize(dummy, "/", false);
		for (int i = vs.size() - 1; i > 0; i--)
		{
			if (vs.stringAt(i).equals("") || vs.stringAt(i).equals("."))
			{
				vs.remove(i);
			}
		}
		for (int i = vs.size() - 1; i > 0; i--)
		{
			if (vs.stringAt(i).equals(".."))
			{
				for (int j = i - 1; j >= 0; j--)
				{
					if (!vs.stringAt(j).equals(".."))
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
	 * write a Stream to an output URL File: and http: are currently supported Use HttpURLConnection.getInputStream() to retrieve the http response
	 * 
	 * @param strUrl the URL to write to
	 * @param sream the input stream to read from
	 * @param method GET or POST
	 * @param contentType the contenttype to set MUST BE SET!
	 * @return {@link UrlPart} the opened http connection, null in case of error
	 * 
	 */
	public static UrlPart writeToURL(final String strUrl, final InputStream stream, final String method, final String contentType, final HTTPDetails details)
	{
		String contentTypeLocal = contentType;

		try
		{
			final URL url = new URL(strUrl);
			final HttpURLConnection httpURLconnection = (HttpURLConnection) url.openConnection();
			httpURLconnection.setRequestMethod(method);
			httpURLconnection.setRequestProperty("Connection", "close");
			contentTypeLocal = StringUtil.token(contentTypeLocal, 0, "\r\n");
			httpURLconnection.setRequestProperty(CONTENT_TYPE, contentTypeLocal);
			httpURLconnection.setDoOutput(true);
			if (details != null)
			{
				details.applyTo(httpURLconnection);
			}

			final OutputStream out = httpURLconnection.getOutputStream();

			if (stream != null)
			{
				IOUtils.copy(stream, out);
			}

			out.flush();
			out.close();

			return new UrlPart(httpURLconnection);
		}
		catch (final Exception x)
		{
			// System.out.print(x);
		}

		return null;
	}
}
