/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
 * 04022005 VF initial version
 */
/*
 * Created on Aug 26, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.cip4.jdflib.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.mail.BodyPart;
import javax.mail.Multipart;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams;
import org.cip4.jdflib.util.ByteArrayIOStream.ByteArrayIOInputStream;
import org.cip4.jdflib.util.UrlUtil.HTTPDetails;
import org.cip4.jdflib.util.UrlUtil.URLProtocol;
import org.junit.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 * 12.01.2009
 */
public class UrlUtilTest extends JDFTestCaseBase
{

	/**
	 * Test method for {@link org.cip4.jdflib.util.UrlUtil#getConnectionTimeout()}.
	 */
	@Test
	public void testGetConnectionTimeout()
	{
		assertEquals("ConnectionTimeout value is wrong.", UrlUtil.DEFAULT_CONNECTION_TIMEOUT, UrlUtil.getConnectionTimeout());
	}

	/**
	 *
	 */
	@Test
	public void testGetLocalURL()
	{
		assertNull(UrlUtil.getLocalURL("foo", "foo"));
		assertNull(UrlUtil.getLocalURL("foo", null));
		assertEquals(UrlUtil.getLocalURL("foo", "foo/bar"), "bar");
		assertEquals(UrlUtil.getLocalURL("/foo", "foo/bar"), "bar");
		assertEquals(UrlUtil.getLocalURL("foo", "/foo/bar"), "bar");
		assertEquals(UrlUtil.getLocalURL("foo/", "foo/bar"), "bar");
		assertEquals(UrlUtil.getLocalURL(null, "foo/bar"), "foo/bar");
		assertEquals(UrlUtil.getLocalURL("", "foo/bar"), "foo/bar");
		assertEquals(UrlUtil.getLocalURL("file://foo", "File://foo/bar/a.b"), "bar/a.b");
	}

	/**
	 *
	 */
	@Test
	public void testGetExtensionTypeFromMimeType()
	{
		assertEquals("txt", UrlUtil.getExtensionFromMimeType(UrlUtil.TEXT_UNKNOWN));
		assertEquals("jdf", UrlUtil.getExtensionFromMimeType(UrlUtil.VND_JDF));
		assertEquals("jmf", UrlUtil.getExtensionFromMimeType(UrlUtil.VND_JMF));
		assertEquals("xml", UrlUtil.getExtensionFromMimeType(UrlUtil.TEXT_XML));
		assertEquals("xml", UrlUtil.getExtensionFromMimeType(UrlUtil.APPLICATION_XML));
		assertEquals("zip", UrlUtil.getExtensionFromMimeType(UrlUtil.APPLICATION_ZIP));
		assertEquals("zip", UrlUtil.getExtensionFromMimeType(UrlUtil.APPLICATION_XZIP));
		assertEquals("pdf", UrlUtil.getExtensionFromMimeType(UrlUtil.APPLICATION_PDF));
		assertEquals("ps", UrlUtil.getExtensionFromMimeType(UrlUtil.APPLICATION_PS));
		assertEquals("mim", UrlUtil.getExtensionFromMimeType(MimeUtil.MULTIPART_RELATED));
	}

	/**
	 *
	 */
	@Test
	public void testGetMimeTypeFromExt()
	{
		assertEquals(UrlUtil.TEXT_UNKNOWN, UrlUtil.getMimeTypeFromURL("www.foobar.com"));
		assertEquals(UrlUtil.VND_JDF, UrlUtil.getMimeTypeFromURL(".JDF"));
		assertEquals(UrlUtil.VND_JDF, UrlUtil.getMimeTypeFromURL(".jdf"));
		assertEquals(UrlUtil.VND_JDF, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.jdf"));
		assertEquals(UrlUtil.VND_JMF, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.JMF"));
		assertEquals(UrlUtil.TEXT_XML, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.xml"));
		assertEquals(UrlUtil.TEXT_XML, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.xsl"));
		assertEquals(UrlUtil.APPLICATION_ZIP, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.zip"));
		assertEquals(UrlUtil.getMimeTypeFromURL(null), JDFConstants.MIME_TEXTUNKNOWN);
		assertEquals(UrlUtil.getMimeTypeFromURL("foo.PDF"), JDFConstants.MIME_PDF);
	}

	/**
	 *
	 */
	@Test
	public void testGetURLProtocol()
	{
		assertEquals(null, UrlUtil.getProtocol(null));
		assertEquals(URLProtocol.http, UrlUtil.getProtocol("http://foo.bar.com"));
		assertEquals(URLProtocol.cid, UrlUtil.getProtocol("cid:blah"));
		assertEquals(URLProtocol.file, UrlUtil.getProtocol("file:blah"));
	}

	/**
	 *
	 */
	@Test
	public void testExtension()
	{
		assertNull(UrlUtil.extension("foo"));
		assertEquals("", UrlUtil.extension("foo."));
		assertEquals("foo", UrlUtil.extension(".foo"));
		assertEquals("foo", UrlUtil.extension("a.b.foo"));
		assertEquals("foo", UrlUtil.extension("a.b..foo"));
	}

	/**
	 * ensure we don't accidentally introduce artifacts if we add characters for escaping
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testEscapeUTF8() throws UnsupportedEncodingException
	{
		assertEquals(UrlUtil.m_URIEscape.length(), UrlUtil.m_URIEscape.getBytes(StringUtil.UTF8).length);
		assertEquals(UrlUtil.m_UNCEscape.length(), UrlUtil.m_UNCEscape.getBytes(StringUtil.UTF8).length);
	}

	/**
	 * test the standard url escaping routines
	 */
	@Test
	public void testEscape()
	{
		assertNull(UrlUtil.escape(null, true));
		assertNull(UrlUtil.escape(null, false));
		assertEquals("%20", UrlUtil.escape(" ", false));
		assertEquals("foo%20bar", UrlUtil.escape("foo bar", true));
		assertEquals("%e2%82%ac", UrlUtil.escape("€", true));
		assertEquals("€", UrlUtil.escape("€", false));
		assertEquals("%23", UrlUtil.escape("#", false));
		assertEquals(UrlUtil.escape("世界中のあらゆる情", false), "世界中のあらゆる情");
		assertEquals("ô", UrlUtil.escape("ô", false));

	}

	/**
	 * test adding a parameter
	 */
	@Test
	public void testAddParameter()
	{
		assertEquals("a?b=c", UrlUtil.addParameter("a", "b", "c"));
		assertEquals("a", UrlUtil.addParameter("a", "", "c"));
		assertEquals("a", UrlUtil.addParameter("a", "a", null));
		assertEquals("a?b=c&bb=cc", UrlUtil.addParameter("a?b=c", "bb", "cc"));
		assertEquals("a?b=c%20d", UrlUtil.addParameter("a", "b", "c d"));
		assertEquals("a?b=http%3a%2f%2fwww.example.com", UrlUtil.addParameter("a", "b", "http://www.example.com"));
		assertEquals("ô", UrlUtil.escape("ô", false));
	}

	/**
	 * test adding a path to a url
	 */
	@Test
	public void testAddPath()
	{
		assertEquals("A/a", UrlUtil.addPath("A", "a"));
		assertEquals("A/a", UrlUtil.addPath("A/", "/a"));
		assertEquals("A/a?b=c", UrlUtil.addPath("A?b=c", "a"));
	}

	/**
	 *
	 */
	@Test
	public void testWriteToURL()
	{
		if (!isTestNetwork())
			return;
		assertNotNull(UrlUtil.writeToURL("http://www.example.com", null, UrlUtil.GET, UrlUtil.TEXT_PLAIN, null));
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	public void testStreamWriter() throws IOException
	{
		if (!isTestNetwork())
			return;
		ByteArrayIOStream byteArrayIOStream = new ByteArrayIOStream("abc".getBytes());
		ByteArrayIOInputStream inputStream = byteArrayIOStream.getInputStream();
		assertNotNull(UrlUtil.writerToURL("http://www.example.com", new UrlUtil.StreamReader(inputStream), UrlUtil.POST, UrlUtil.TEXT_PLAIN, null));
		inputStream.close();
		byteArrayIOStream.close();
	}

	/**
	 *
	 */
	@Test
	public void testWriteToURLPost()
	{
		if (!isTestNetwork())
			return;
		assertNotNull(UrlUtil.writeToURL("http://google.com", new ByteArrayInputStream("foo".getBytes()), UrlUtil.POST, "foo/bar", null));
	}

	/**
	 *
	 */
	@Test
	public void testWriteToFTP()
	{
		if (!isTestNetwork())
			return;
		assertNotNull(UrlUtil.writeToURL("ftp://ftp.mozilla.org/pub/mozilla.org", null, null, null, null));
	}

	/**
	 *
	 */
	@Test
	public void testWriteToURLClose()
	{
		if (!isTestNetwork())
			return;
		HTTPDetails det = new HTTPDetails();
		det.setbKeepAlive(false);
		assertNotNull(UrlUtil.writeToURL("http://google.com", new ByteArrayInputStream("foo".getBytes()), UrlUtil.POST, "foo/bar", det));
	}

	/**
	 *
	 */
	@Test
	public void testWriteToURLRedirect()
	{
		if (!isTestNetwork())
			return;
		assertEquals(UrlUtil.writeToURL("http://google.ch", null, UrlUtil.GET, null, null).getResponseCode(), 200);
	}

	/**
	 *
	 */
	@Test
	public void testIsRedirect()
	{
		assertTrue(UrlUtil.isRedirect(302));
		assertFalse(UrlUtil.isRedirect(42));
		assertFalse(UrlUtil.isRedirect(-301));
	}

	/**
	 *
	 */
	@Test
	public void testGetDetails()
	{
		HTTPDetails redirect = null;
		for (int i = 1; i < 666; i++)
		{
			redirect = HTTPDetails.getRedirect(redirect);
			assertEquals(redirect.getRedirect(), i);
		}
	}

	/**
	 *
	 */
	@Test
	public void testWriteToURLNull()
	{
		if (!isTestNetwork())
			return;
		assertNull(UrlUtil.writeToURL(null, null, UrlUtil.GET, UrlUtil.TEXT_PLAIN, null));
	}

	/**
	 *
	 */
	@Test
	public void testRemoveProtocol()
	{
		assertNull(UrlUtil.removeProtocol(null));
		assertNull(UrlUtil.removeProtocol("http://"));
		assertEquals("a", UrlUtil.removeProtocol("http://a"));
		assertEquals("abb:8080", UrlUtil.removeProtocol("http://abb:8080"));
		assertEquals("foo", UrlUtil.removeProtocol("cid:foo"));
		assertEquals("foo", UrlUtil.removeProtocol("CID:foo"));
	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testIsCid()
	{
		assertTrue(UrlUtil.isCID("cid:foo"));
		assertTrue(UrlUtil.isCID("<cid:foo>"));
		assertTrue(UrlUtil.isCID("<cid:"));
		assertFalse(UrlUtil.isCID(null));
		assertFalse(UrlUtil.isCID("<"));
	}

	/**
	 *
	 */
	@Test
	public void testIsNotCid()
	{
		assertFalse(UrlUtil.isNotCID("cid:foo"));
		assertFalse(UrlUtil.isNotCID("bar.foo"));
		assertTrue(UrlUtil.isNotCID("file:foo"));
		assertTrue(UrlUtil.isNotCID("http://foo"));
		assertTrue(UrlUtil.isNotCID("https://foo"));
	}

	/**
	 *
	 */
	@Test
	public void testIsHTTP()
	{
		assertTrue(UrlUtil.isHttp("http://foo.bar.com"));
		assertFalse(UrlUtil.isHttp("https://foo.bar.com"));
		assertFalse(UrlUtil.isHttp(null));
		assertFalse(UrlUtil.isHttp("foo.bar.com"));
	}

	/**
	 *
	 */
	@Test
	public void testIsHTTPS()
	{
		assertTrue(UrlUtil.isHttps("https://foo.bar.com"));
		assertFalse(UrlUtil.isHttps("http://foo.bar.com"));
		assertFalse(UrlUtil.isHttps(null));
		assertFalse(UrlUtil.isHttps("foo.bar.com"));
	}

	/**
	 *
	 */
	@Test
	public void testIsNet()
	{
		assertTrue(UrlUtil.isNet("https://foo.bar.com"));
		assertTrue(UrlUtil.isNet("http://foo.bar.com"));
		assertTrue(UrlUtil.isNet("FTP://foo.bar.com"));
		assertFalse(UrlUtil.isNet(null));
		assertFalse(UrlUtil.isNet("foo.bar.com"));
	}

	/**
	 *
	 */
	@Test
	public void testIsIRL()
	{
		assertTrue(UrlUtil.isIRL("file://blï¿½dï¿½.txt"));
		assertTrue(UrlUtil.isIRL("http://foo.com/blï¿½dï¿½.txt"));
		assertFalse("3 ///", UrlUtil.isIRL("http:///blï¿½dï¿½.txt"));
		assertFalse("blank is bad", UrlUtil.isIRL("file://a blï¿½dï¿½.txt"));
		assertTrue("blank %20 is good", UrlUtil.isIRL("file://a%20blï¿½dï¿½.txt"));
		assertTrue(UrlUtil.isIRL("file:C:/a/b.txt"));
		assertTrue("relative url", UrlUtil.isIRL("./3ï¿½.txt"));
		assertFalse("invalid char: @", UrlUtil.isIRL("http://@"));
		assertTrue(UrlUtil.isIRL("HTTP://ï¿½/ï¿½"));
		assertTrue(UrlUtil.isIRL("file:///C:/Documents%20and%20Settings/Israel/My%20Documents/Vio%20Production/Results/TIME_H8789/TIME_H8789.pdf"));
	}

	/**
	 *
	 */
	@Test
	public void testIsURL()
	{
		assertFalse(UrlUtil.isURL(null));
		assertTrue(UrlUtil.isURL("file://bl.txt"));
		assertTrue(UrlUtil.isURL("http://foo.com/bl.txt"));
		assertFalse("3 ///", UrlUtil.isURL("http:///bl.txt"));
		assertFalse("blank is bad", UrlUtil.isURL("file://a b.txt"));
		assertTrue("blank %20 is good", UrlUtil.isURL("file://a%20bl.txt"));
		assertTrue(UrlUtil.isURL("file:C:/a/b.txt"));
		assertTrue("relative url", UrlUtil.isURL("./3.txt"));
		assertFalse("invalid char: @", UrlUtil.isURL("http://@"));
		assertFalse("UNC", UrlUtil.isURL("\\\\unc\\bar\\a.txt"));
		assertTrue(UrlUtil.isURL("HTTP://a/b?c"));
	}

	/**
	*
	*/
	@Test
	public void testIsURLPerformance()
	{
		CPUTimer ct = new CPUTimer(false);
		for (int i = 0; i < 1234; i++)
		{
			ct.start();
			assertFalse(UrlUtil.isURL(null));
			assertTrue(UrlUtil.isURL("file://bl.txt"));
			assertTrue(UrlUtil.isURL("http://foo.com/bl.txt"));
			assertFalse("3 ///", UrlUtil.isURL("http:///bl.txt"));
			assertFalse("blank is bad", UrlUtil.isURL("file://a b.txt"));
			assertTrue("blank %20 is good", UrlUtil.isURL("file://a%20bl.txt"));
			assertTrue(UrlUtil.isURL("file:C:/a/b.txt"));
			assertTrue("relative url", UrlUtil.isURL("./3.txt"));
			assertFalse("invalid char: @", UrlUtil.isURL("http://@"));
			assertFalse("UNC", UrlUtil.isURL("\\\\unc\\bar\\a.txt"));
			assertTrue(UrlUtil.isURL("HTTP://a/b?c"));
			ct.stop();
		}
		System.out.println(ct.toString());
	}

	/**
	 *
	 */
	@Test
	public void testIsXMLType()
	{
		assertTrue(UrlUtil.isXMLType("text/xml"));
		assertTrue(UrlUtil.isXMLType("TEXT/XML"));
		assertTrue(UrlUtil.isXMLType("TEXT/XML; "));
		assertTrue(UrlUtil.isXMLType("application/foobar+xml "));
		assertTrue(UrlUtil.isXMLType("application/xml"));
		assertTrue(UrlUtil.isXMLType("text/foobar+xml "));
		assertFalse(UrlUtil.isXMLType("foobar+xml "));
	}

	/**
	 *
	 */
	@Test
	public void testIsZIPType()
	{
		assertFalse(UrlUtil.isZIPType("text/xml"));
		assertTrue(UrlUtil.isZIPType("application/zip"));
		assertTrue(UrlUtil.isZIPType("application/foobar+zip; "));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * @throws Exception if snafu
	 */
	@Test
	public void testStringToURL() throws Exception
	{
		if (File.separator.equals("\\"))
		{
			// test for an existing directory (a trailing slash is appended by StringToURL)
			assertTrue(UrlUtil.stringToURL("c:\\temp\\").getPath().startsWith(new URL("File:/c:/temp").getPath()));
			assertTrue(UrlUtil.stringToURL("File:/c:/temp/").getPath().startsWith(new URL("File:/c:/temp").getPath()));
			assertTrue(UrlUtil.stringToURL("c:\\temp").getPath().startsWith(new URL("File:/c:/temp").getPath()));
			assertTrue(UrlUtil.stringToURL("File:/c:/temp").getPath().startsWith(new URL("File:///c:/temp").getPath()));
			assertTrue(UrlUtil.stringToURL("File:///c:/temp").getPath().startsWith(new URL("File:///c:/temp").getPath()));

			// test for a file or a non existing object (trailing slash is removed by StringToURL)
			assertEquals(UrlUtil.stringToURL("File:/c:/blöd €.pdf"), new URL(UrlUtil.fileToUrl(new File("c:/blöd €.pdf"), true)));
			assertEquals(UrlUtil.stringToURL("c:\\xyz\\").getPath(), new URL("File:/c:/xyz").getPath());
			assertEquals(UrlUtil.stringToURL("File:/c:/xyz/").getPath(), new URL("File:/c:/xyz").getPath());
			assertEquals(UrlUtil.stringToURL("File:\\\\host\\dir\\dir2\\file.pdf").getPath(), new URL("File://host/dir/dir2/file.pdf").getPath());
			assertEquals(UrlUtil.stringToURL("File:\\\\host\\dir\\dir2/dir3/file.pdf").getPath(), new URL("File://host/dir/dir2/dir3/file.pdf").getPath());
			assertEquals(UrlUtil.stringToURL("c:\\xyz").getPath(), new URL("File:/c:/xyz").getPath());

			assertEquals(UrlUtil.stringToURL("file://foo/ .txt"), new URL("file://foo/%20.txt"));
			assertEquals(UrlUtil.stringToURL("file://foo/%.txt"), new URL("file://foo/%25.txt"));
		}
		else
		{
			assertEquals(UrlUtil.stringToURL("file:/foo/ .txt"), new URL("file:/foo/%20.txt"));
			assertEquals(UrlUtil.stringToURL("file:/foo/%.txt"), new URL("file:/foo/%25.txt"));
		}
		assertEquals(UrlUtil.stringToURL("\\\\a\\b c\\d.txt"), new URL("file://a/b%20c/d.txt"));
		assertEquals(UrlUtil.stringToURL("http://foo/%20.txt"), new URL("http://foo/%20.txt"));
		assertEquals(UrlUtil.stringToURL("http://foo"), new URL("http://foo"));
		assertEquals(UrlUtil.stringToURL("https://foo"), new URL("https://foo"));
		assertNull("empty File: should be null", UrlUtil.stringToURL("File:"));
		assertEquals(UrlUtil.stringToURL("http%3A%2F%2FDRU-CIP4HD1%3A6331"), new URL("http://DRU-CIP4HD1:6331"));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testIsEscaped()
	{
		assertTrue(UrlUtil.isEscaped("http%3A%2F%2FDRU-CIP4HD1%3A6331"));
		assertFalse(UrlUtil.isEscaped("file:http%3A%2F%2FDRU-CIP4HD1%3A6331"));
		assertFalse(UrlUtil.isEscaped("text%20a.pdf"));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 *
	 */
	@Test
	public void testGetBytesFromIP()
	{
		assertNull(UrlUtil.getBytesFromIP("foo"));
		assertNull(UrlUtil.getBytesFromIP("3.141"));
		assertNull(UrlUtil.getBytesFromIP("3.141.2.2222"));
		assertNotNull(UrlUtil.getBytesFromIP("127.0.0.1"));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetIPFromBytes()
	{
		assertNull(UrlUtil.getIPFromBytes(null));
		assertNull(UrlUtil.getIPFromBytes(new byte[] { 1, 2 }));
		assertNull(UrlUtil.getIPFromBytes(new byte[] { 1, 2 }));
		assertNotNull(UrlUtil.getIPFromBytes(new byte[] { 1, 2, 4, 5 }));
		assertNull(UrlUtil.getIPFromBytes(new byte[] { 1, 2, 4, 5, 6 }));
		assertNotNull(UrlUtil.getIPFromBytes(new byte[] { 1, 2, 4, 5, 6, 7 }));
	}

	/**
	 *
	 */
	@Test
	public void testFileToURL()
	{
		if (FileUtil.isWindows())
		{ // on windows
			final File f = new File("C:\\IO.SYS");
			String s = UrlUtil.fileToUrl(f, false);
			assertEquals(s, "file:///C:/IO.SYS");
			s = UrlUtil.fileToUrl(new File("\\\\fooBar\\4€.txt"), false);
			assertEquals(s, "file://fooBar/4€.txt");
		}
		else
		{
			String s = UrlUtil.fileToUrl(new File("/fooBar/4€.txt"), true);
			assertEquals(s, "file:/fooBar/4%e2%82%ac.txt");
			assertEquals(UrlUtil.fileToUrl(new File("/a/4%.txt"), false), "file:/a/4%25.txt");
		}
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testNonAsciiFileURL() throws Exception
	{
		for (int i = 0; i < 2; i++) // loop over escape and non-escape
		{
			final File f = new File("4€äöü.txt");
			final File f2 = FileUtil.getFileInDirectory(new File(sm_dirTestDataTemp), f);
			f2.delete();
			assertTrue(f2.createNewFile());
			assertTrue(f2.canRead());
			final String url = UrlUtil.fileToUrl(f2, i == 0);
			final XMLDoc doc = new XMLDoc("URL", null);
			final KElement root = doc.getRoot();
			root.setAttribute("url", url);
			doc.write2File(sm_dirTestDataTemp + "url.xml", 2, false);
			final XMLDoc d = XMLDoc.parseFile(sm_dirTestDataTemp + "url.xml");
			final KElement root2 = d.getRoot();
			final String urlParse = root2.getAttribute("url");
			assertEquals(url, urlParse);
			final File f3 = UrlUtil.urlToFile(urlParse);
			assertEquals(f2.getAbsolutePath(), f3.getAbsolutePath());
			assertTrue(f3.canRead());
		}
	}

	/**
	 *
	 */
	@Test
	public void testURLToFileName()
	{
		assertEquals("b.c", UrlUtil.urlToFileName("a:b.c"));
		assertEquals("b.c", UrlUtil.urlToFileName("http:/b.c?gg"));
	}

	/**
	 *
	 */
	@Test
	public void testUNCToUrl()
	{

		assertEquals("file://a/b/c.txt", UrlUtil.uncToUrl("\\\\a\\b\\c.txt", true));
		String unc = "\\\\a\\b\\c ä.txt";
		String url = UrlUtil.uncToUrl(unc, true);
		assertEquals("file://a/b/c%20%c3%a4.txt", url);
		String unc2 = unc;

		for (int i = 0; i < 10; i++)
		{
			url = UrlUtil.uncToUrl(unc, true);
			unc = UrlUtil.urlToUNC(url);
		}
		assertEquals(unc, unc2);

		assertEquals("file://a/a%23", UrlUtil.uncToUrl("\\\\a\\a#", false));
		assertEquals("file://a/b/c.d", UrlUtil.uncToUrl("\\\\a/b/c.d", false));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testURLToUNC() throws Exception
	{
		assertEquals("a%5cb", UrlUtil.urlToUNC("file:a%5cb"));
		assertEquals("a%25", UrlUtil.urlToUNC("file:a%2525"));
		assertEquals("a b", UrlUtil.urlToUNC("file:a%20b"));
		assertEquals("a b#", UrlUtil.urlToUNC("file:a%20b%23"));
		assertEquals("a%2fb\\d", UrlUtil.urlToUNC("file:a%2fb/d"));
		assertNull(UrlUtil.urlToUNC(""));
		assertEquals("\\\\host\\dir\\file", UrlUtil.urlToUNC("\\\\host\\dir\\file"));
		assertEquals("\\\\host\\dir\\file", UrlUtil.urlToUNC("//host/dir/file"));
		assertEquals("\\\\host\\dir\\file", UrlUtil.urlToUNC("file://host/dir/file"));
		assertEquals("\\\\host\\dir\\file", UrlUtil.urlToUNC("file:\\\\host\\dir\\file"));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testURLToFile() throws Exception
	{
		for (int i = 0; i < 2; i++)
		{
			File f = UrlUtil.urlToFile(".");
			assertTrue(f.isDirectory());
			File f2 = UrlUtil.urlToFile(UrlUtil.fileToUrl(f, i == 0));
			assertTrue(f2.isDirectory());
			assertEquals(f2.getCanonicalPath(), f.getCanonicalPath());

			f = new File(".\\simple.pdf");
			f2 = UrlUtil.urlToFile(UrlUtil.fileToUrl(f, i == 0));
			assertEquals("asccii", f.getCanonicalPath(), f2.getCanonicalPath());

			f = new File("blö½d .pdf");
			f2 = UrlUtil.urlToFile(UrlUtil.fileToUrl(f, i == 0));
			assertEquals("non asccii", f.getCanonicalPath(), f2.getCanonicalPath());

			f = new File("blöd .pdf");
			f2 = UrlUtil.urlToFile(UrlUtil.fileToUrl(f, i == 0));
			assertEquals("non asccii", f.getCanonicalPath(), f2.getCanonicalPath());

			f = new File("blöd ist es 10€.pdf");
			final String fileToUrl = UrlUtil.fileToUrl(f, i == 0);
			f2 = UrlUtil.urlToFile(fileToUrl);
			assertEquals("escape %20", f.getCanonicalPath(), f2.getCanonicalPath());
			assertNull(UrlUtil.urlToFile("http://foo"));
			assertNull(UrlUtil.urlToFile("https://foo"));
			assertNull(UrlUtil.urlToFile("cid:foo"));

		}

		if (PlatformUtil.isWindows())
		{ // on windows
			final String uC = "file:///C:/Documents%20and%20Settings/All%20Users/Desktop/Prinect_Imposition_Editor/Examples/x.jdf";
			assertTrue(UrlUtil.urlToFile(uC).getPath().startsWith("C:" + File.separator + "Documents and Settings"));
			final File fi1 = new File("\\\\fooBar\\4€€.txt");
			final File fi = UrlUtil.urlToFile("file://fooBar/4€%e2%82%ac.txt");
			assertEquals("escape %20", fi.getCanonicalPath(), fi1.getCanonicalPath());
			assertEquals(new File("C:\\Windows"), UrlUtil.urlToFile("file:/C:/Windows"));
			assertEquals(new File("C:\\Windows"), UrlUtil.urlToFile("file:///C:/Windows"));
			assertEquals(new File("C:\\Windows"), UrlUtil.urlToFile("file://localhost/C:/Windows"));
			assertEquals(new File("C:\\Windows"), UrlUtil.urlToFile("file:/C%3A/Windows"));
		}

	}

	/**
	 *
	 */
	@Test
	public void testisRelativeURL()
	{
		assertFalse(UrlUtil.isRelativeURL(""));
		assertFalse(UrlUtil.isRelativeURL("\\\\foo\\bar"));
		assertTrue(UrlUtil.isRelativeURL("c/d/e.f"));
		assertFalse(UrlUtil.isRelativeURL("/c/d/e.f"));
		assertFalse(UrlUtil.isRelativeURL("http://c/d/e.f"));
		assertFalse(UrlUtil.isRelativeURL("http://c:8080/c/d/e.f"));
		assertFalse(UrlUtil.isRelativeURL("c:\\foo"));
		assertFalse(UrlUtil.isRelativeURL("cid:c/d/e.f"));
	}

	/**
	 *
	 */
	@Test
	public void testisUNC()
	{
		assertTrue(UrlUtil.isUNC("\\\\foo\\bar"));
		assertFalse(UrlUtil.isUNC("c/d/e.f"));
		assertFalse(UrlUtil.isUNC("/c/d/e.f"));
	}

	/**
	 *
	 */
	@Test
	public void testGetRelativeURI()
	{
		File f = new File("./a b");
		assertEquals(StringUtil.replaceChar(UrlUtil.getRelativeURL(f, null, true), '\\', "/", 0), "a%20b");
		f = new File("../a.ß");
		assertEquals("escaped utf8", StringUtil.replaceChar(UrlUtil.getRelativeURL(f, null, true), '\\', "/", 0), "../a.%c3%9f");
		assertEquals("unescaped but utf8", StringUtil.replaceChar(UrlUtil.getRelativeURL(f, null, false), '\\', "/", 0), new String(StringUtil.getUTF8Bytes("../a.ß")));
	}

	/**
	 *
	 */
	@Test
	public void testGetParentDirectory()
	{
		assertEquals(null, UrlUtil.getParentDirectory(null));
		assertEquals(".", UrlUtil.getParentDirectory("foo.bar"));
		assertEquals("foo", UrlUtil.getParentDirectory("foo/bar"));
		assertEquals("foo", UrlUtil.getParentDirectory("foo\\bar"));
		assertEquals("\\\\foo", UrlUtil.getParentDirectory("\\\\foo\\bar"));
	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testGetRelativeURL()
	{
		File file = new File("c:\\a\\b\\c.txt");
		final File cwd = new File("c:\\a\\b1");
		assertEquals(UrlUtil.getRelativeURL(file, cwd, true), "../b/c.txt");
		file = new File("c:\\a\\b1\\c.txt");
		assertEquals(UrlUtil.getRelativeURL(file, cwd, true), "c.txt");
		file = new File("a\\..\\b\\c.txt");
		assertEquals(UrlUtil.getRelativeURL(file, null, true), "b/c.txt");
		file = cwd;
		assertEquals(UrlUtil.getRelativeURL(file, cwd, true), ".");
	}

	/**
	 *
	 */
	@Test
	public void testGetURLWithDirectory()
	{
		String url = "File://a.b";
		assertEquals("test nulls", url, UrlUtil.getURLWithDirectory(null, url));
		assertEquals("test nulls", url, UrlUtil.getURLWithDirectory("", url));
		assertEquals("test nulls", url, UrlUtil.getURLWithDirectory("File:/dir", url));

		url = "a.b";
		assertEquals("relative url", "File://dir/a.b", UrlUtil.getURLWithDirectory("File://dir/", url));
		assertEquals("relative url", "File://dir/a.b", UrlUtil.getURLWithDirectory("File://dir", url));

		url = "/a.b";
		assertEquals("absolute url no host", "File://a.b", UrlUtil.getURLWithDirectory("File://host/", url));
		assertEquals("absolute url no host", "File://a.b", UrlUtil.getURLWithDirectory("File://host", url));

		url = "/a.b:c";
		assertEquals("absolute url no host - colon", "File://a.b:c", UrlUtil.getURLWithDirectory("File://host/", url));
		assertEquals("absolute url no host - colon", "File://a.b:c", UrlUtil.getURLWithDirectory("File://host", url));

		url = "//a.b";
		assertEquals("absolute url with default host", "File://a.b", UrlUtil.getURLWithDirectory("File://dir/", url));
		assertEquals("absolute url with default host", "File://a.b", UrlUtil.getURLWithDirectory("File://dir", url));

		url = "ftp://a.b";
		assertEquals("absolute url with default host", "ftp://a.b", UrlUtil.getURLWithDirectory("File://dir/", url));
		url = "http://a.b";
		assertEquals("absolute url with default host", "http://a.b", UrlUtil.getURLWithDirectory("File://dir/", url));

		url = "//boo/a.b";
		assertEquals("absolute url with new host", "File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir/", url));
		assertEquals("absolute url with new host", "File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir", url));

		url = "//boo/./gg/../a.b";
		assertEquals("absolute url with new host and cleandots", "File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir/", url));
		assertEquals("absolute url with new host and cleandots", "File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir", url));

		assertEquals("http url", "http://dir/a.b", UrlUtil.getURLWithDirectory("http://dir", "a.b"));
	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testRemoveExtension()
	{
		assertEquals(UrlUtil.prefix("a.b"), "a");
		assertEquals(UrlUtil.prefix("a"), "a");
		assertEquals(UrlUtil.prefix("a."), "a");
		assertEquals(UrlUtil.prefix("a.b.c"), "a.b");
		assertEquals(UrlUtil.prefix("."), "");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testCleanDots()
	{
		assertEquals(UrlUtil.cleanDots("."), ".");
		assertEquals(UrlUtil.cleanDots(".."), "..");
		assertEquals(UrlUtil.cleanDots("a/.."), ".");
		assertEquals(UrlUtil.cleanDots("../../c.pdf"), "../../c.pdf");
		assertEquals(UrlUtil.cleanDots(".././../c.pdf"), "../../c.pdf");
		assertEquals(UrlUtil.cleanDots("File://a/../b"), "File://b");
		assertEquals(UrlUtil.cleanDots("File://a/.././c/../b.pdf"), "File://b.pdf");
		assertEquals(UrlUtil.cleanDots("File:///c:/a/.././c/../b.pdf"), "File:///c:/b.pdf");
		assertEquals(UrlUtil.cleanDots("/."), "/.");
	}

	/**
	 *
	 */
	@Test
	public void testCleanHttpUrl()
	{
		assertEquals(UrlUtil.cleanHttpURL("localhost"), "http://localhost");
		assertEquals(UrlUtil.cleanHttpURL("/http/localhost"), "http://localhost");
		assertEquals(UrlUtil.cleanHttpURL("http:/localhost"), "http://localhost");
		assertEquals(UrlUtil.cleanHttpURL("http:/localhost:8080//ggg"), "http://localhost:8080/ggg");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testCreateHttpUrl()
	{
		assertEquals(UrlUtil.createHttpUrl(false, "d", 0, "a"), "http://d/a");
		assertEquals(UrlUtil.createHttpUrl(false, "d", 8080, "a"), "http://d:8080/a");
		assertEquals(UrlUtil.createHttpUrl(true, "d", 8080, "a"), "https://d:8080/a");
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testMoveToDirMime() throws Exception
	{
		new MimeUtilTest().testBuildMimePackageDoc();
		final Multipart mp = MimeUtil.getMultiPart(sm_dirTestDataTemp + "testMimePackageDoc.mjm");
		final BodyPart bp = MimeUtil.getPartByCID(mp, "jdf.JDF");
		final JDFDoc d = MimeUtil.getJDFDoc(bp);
		final JDFNode n = d.getJDFRoot();
		final JDFColorSpaceConversionParams cscp = (JDFColorSpaceConversionParams) n.getMatchingResource(ElementName.COLORSPACECONVERSIONPARAMS, null, null, 0);
		assertNotNull(cscp);
		final JDFFileSpec fs = cscp.getFinalTargetDevice();
		final File newDir = new File(sm_dirTestDataTemp + "newDir");
		final File f = UrlUtil.moveToDir(fs, newDir, null, true);
		assertNotNull("error moving file to dir", f);
		for (int i = 0; i < 10; i++)
		{
			ThreadUtil.sleep(1000);
			if (fs.getURL().contains(UrlUtil.fileToUrl(newDir, false)))
			{
				break;
			}
			log.info("Waiting " + i);
		}
		long l = f.lastModified();
		final File f2 = UrlUtil.moveToDir(fs, newDir, null, false);
		assertNotNull("error moving file to dir", f2);
		ThreadUtil.sleep(1000);
		assertEquals(l, f2.lastModified(), 0);
		fs.setURL("bad:/blöd");
		assertNull("bad url:", UrlUtil.moveToDir(fs, newDir, null, true));
		fs.setURL("http://really_really_not_there.com/isnt/there?aaa");
		assertNull("bad url:", UrlUtil.moveToDir(fs, newDir, null, true));
		fs.setURL("./blub.pdf");
		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "dummy/blub.pdf"));
		assertTrue("relative:", UrlUtil.moveToDir(fs, newDir, sm_dirTestDataTemp + "dummy", true).exists());
		fs.setURL("deep/blub.pdf");
		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "dummy/deep/blub.pdf"));
		assertTrue("relative:", UrlUtil.moveToDir(fs, newDir, sm_dirTestDataTemp + "dummy", true).exists());

	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testMoveToDir() throws Exception
	{
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFRunList rl = (JDFRunList) d.getJDFRoot().addResource(ElementName.RUNLIST, EnumUsage.Input);
		JDFFileSpec fs = rl.addPDF("./content/boo.pdf", 0, -1).getLayoutElement().getFileSpec();

		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "URLIn/content/boo.pdf"));

		final File newDir = new File(sm_dirTestDataTemp + "newDir");
		fs.setURL("bad:/blöd");
		assertNull("bad url:", UrlUtil.moveToDir(fs, newDir, null, true));
		fs.setURL("http://really_really_not_there.com/isnt/there?aaa");
		assertNull("bad url:", UrlUtil.moveToDir(fs, newDir, null, true));
		fs.setURL("./blub.pdf");
		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "dummy/blub.pdf"));
		assertTrue("relative:", UrlUtil.moveToDir(fs, newDir, sm_dirTestDataTemp + "dummy", true).exists());
		fs.setURL("deep/blub.pdf");
		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "dummy/deep/blub.pdf"));
		assertTrue("relative:", UrlUtil.moveToDir(fs, newDir, sm_dirTestDataTemp + "dummy", true).exists());
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testMoveToDirDelete() throws Exception
	{
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFRunList rl = (JDFRunList) d.getJDFRoot().addResource(ElementName.RUNLIST, EnumUsage.Input);
		JDFFileSpec fs = rl.addPDF("./content/boo.pdf", 0, -1).getLayoutElement().getFileSpec();

		final File newDir = new File(sm_dirTestDataTemp + "newDir");
		fs.setURL("./blub.pdf");
		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "dummy/blub.pdf"));
		assertTrue("relative:", UrlUtil.moveToDir(fs, newDir, sm_dirTestDataTemp + "dummy", true, true).exists());
		assertFalse("relative:", new File(sm_dirTestDataTemp + "dummy/blub.pdf").exists());

		fs.setURL("deep/blub.pdf");
		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "dummy/deep/blub.pdf"));
		assertTrue("relative:", UrlUtil.moveToDir(fs, newDir, sm_dirTestDataTemp + "dummy", true, true).exists());
		assertFalse("relative:", new File(sm_dirTestDataTemp + "dummy/deep/blub.pdf").exists());
	}

	/**
	 *
	 */
	@Test
	public void testNormalize()
	{
		assertEquals(UrlUtil.normalize("a.b"), "a.b");
		assertEquals(UrlUtil.normalize("./a.b"), "a.b");
		assertEquals(UrlUtil.normalize("././a.b"), "a.b");
		assertEquals(UrlUtil.normalize("http://a/a.b"), "http://a/a.b");
		assertEquals(UrlUtil.normalize("http://a/a%20.b"), "http://a/a%20.b");
		assertEquals(UrlUtil.normalize("HTTP://a/a.b"), "http://a/a.b");
		assertEquals(UrlUtil.normalize("HTTP://a//a.b"), "http://a/a.b");
		assertEquals(UrlUtil.normalize("cid:a.b"), "cid:a.b");
		assertEquals(UrlUtil.normalize("<cid:a.b>"), "cid:a.b");
		assertEquals(UrlUtil.normalize("http://a/a.b?f=g"), "http://a/a.b?f=g");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testNormalizeFile()
	{
		assertEquals(UrlUtil.normalize("\\\\host\\dir\\a a.b"), "file://host/dir/a%20a.b");
		assertEquals(UrlUtil.normalize("\\\\host\\dir\\a ä.b"), "file://host/dir/a%20ä.b");
		assertEquals(UrlUtil.normalize("FILE://host/dir/a ä.b"), "file://host/dir/a%20ä.b");
	}

	/**
	 *
	 */
	@Test
	public void testUnEscape()
	{
		assertEquals(UrlUtil.unEscape("a.b"), "a.b");
		assertEquals(UrlUtil.unEscape("a.b"), "a.b");
		assertEquals(UrlUtil.unEscape("aaa%"), "aaa%");
		assertEquals(UrlUtil.unEscape("%2"), "%2");
		assertEquals(UrlUtil.unEscape("%20"), " ");
		assertEquals(UrlUtil.unEscape("%23"), "#");
		assertEquals(UrlUtil.unEscape("世界中のあらゆる情"), "世界中のあらゆる情");
	}

	/**
	 *
	 */
	@Test
	public void testNewExtension()
	{
		assertEquals(UrlUtil.newExtension("a.b", "c"), "a.c");
		assertEquals(UrlUtil.newExtension("a.b.c", "c"), "a.b.c");
		assertEquals(UrlUtil.newExtension("a.b", null), "a");
		assertEquals(UrlUtil.newExtension("a.b", ".c"), "a.c");
		assertEquals(UrlUtil.newExtension("a.b", ".c.d"), "a.c.d");
		assertEquals(UrlUtil.newExtension("a.b", "c.d"), "a.c.d");
		assertEquals(UrlUtil.newExtension("a.b.bb", "c.d"), "a.b.c.d");
		assertEquals(UrlUtil.newExtension(".b", ".c"), ".c");
		assertEquals(UrlUtil.newExtension("a", ".c"), "a.c");
	}
}
