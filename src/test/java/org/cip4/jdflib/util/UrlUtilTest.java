/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2022 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
 * 04022005 VF initial version
 */
/*
 * Created on Aug 26, 2004
 *
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.cip4.jdflib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import org.cip4.jdflib.util.UrlUtil.URLProtocol;
import org.cip4.jdflib.util.net.HTTPDetails;
import org.cip4.jdflib.util.zip.ZipReader;
import org.junit.jupiter.api.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         12.01.2009
 */
public class UrlUtilTest extends JDFTestCaseBase
{

	static final String FTP_SITE = null; // "ftp://speedtest.tele2.net/";

	/**
	 * Test method for {@link org.cip4.jdflib.util.UrlUtil#getConnectionTimeout()}.
	 */
	@Test
	void testGetConnectionTimeout()
	{
		assertEquals(UrlUtil.DEFAULT_CONNECTION_TIMEOUT, UrlUtil.getConnectionTimeout(), "ConnectionTimeout value is wrong.");
	}

	/**
	 *
	 */
	@Test
	void testGetLocalURL()
	{
		assertNull(UrlUtil.getLocalURL("foo", "foo"));
		assertNull(UrlUtil.getLocalURL("foo", null));
		assertEquals("bar", UrlUtil.getLocalURL("foo", "foo/bar"));
		assertEquals("bar", UrlUtil.getLocalURL("/foo", "foo/bar"));
		assertEquals("bar", UrlUtil.getLocalURL("foo", "/foo/bar"));
		assertEquals("bar", UrlUtil.getLocalURL("foo/", "foo/bar"));
		assertEquals("foo/bar", UrlUtil.getLocalURL(null, "foo/bar"));
		assertEquals("foo/bar", UrlUtil.getLocalURL("", "foo/bar"));
		assertEquals("bar/a.b", UrlUtil.getLocalURL("file://foo", "File://foo/bar/a.b"));
	}

	/**
	 *
	 */
	@Test
	void testGetExtensionTypeFromMimeType()
	{
		assertEquals("txt", UrlUtil.getExtensionFromMimeType(UrlUtil.TEXT_UNKNOWN));
		assertEquals("jdf", UrlUtil.getExtensionFromMimeType(UrlUtil.VND_JDF));
		assertEquals("xjdf", UrlUtil.getExtensionFromMimeType(UrlUtil.VND_XJDF));
		assertEquals("jmf", UrlUtil.getExtensionFromMimeType(UrlUtil.VND_JMF));
		assertEquals("xjmf", UrlUtil.getExtensionFromMimeType(UrlUtil.VND_XJMF));
		assertEquals("xml", UrlUtil.getExtensionFromMimeType(UrlUtil.TEXT_XML));
		assertEquals("xml", UrlUtil.getExtensionFromMimeType(UrlUtil.APPLICATION_XML));
		assertEquals("zip", UrlUtil.getExtensionFromMimeType(UrlUtil.APPLICATION_ZIP));
		assertEquals("zip", UrlUtil.getExtensionFromMimeType(UrlUtil.APPLICATION_XZIP));
		assertEquals("pdf", UrlUtil.getExtensionFromMimeType(UrlUtil.APPLICATION_PDF));
		assertEquals("ps", UrlUtil.getExtensionFromMimeType(UrlUtil.APPLICATION_PS));
		assertEquals("mim", UrlUtil.getExtensionFromMimeType(MimeUtil.MULTIPART_RELATED));
		assertEquals("ppf", UrlUtil.getExtensionFromMimeType(MimeUtil.VND_PPF));
	}

	/**
	 *
	 */
	@Test
	void testGetMimeTypeFromExt()
	{
		assertEquals(UrlUtil.TEXT_UNKNOWN, UrlUtil.getMimeTypeFromURL("www.foobar.com"));
		assertEquals(UrlUtil.VND_JDF, UrlUtil.getMimeTypeFromURL(".JDF"));
		assertEquals(UrlUtil.VND_JDF, UrlUtil.getMimeTypeFromURL(".jdf"));
		assertEquals(UrlUtil.VND_JDF, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.jdf"));
		assertEquals(UrlUtil.VND_XJDF, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.xjdf"));
		assertEquals(UrlUtil.VND_XJMF, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.xjmf"));
		assertEquals(UrlUtil.VND_JMF, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.JMF"));
		assertEquals(UrlUtil.VND_PPF, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.cip"));
		assertEquals(UrlUtil.VND_PPF, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.cip3"));
		assertEquals(UrlUtil.VND_PPF, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.ppf"));

		assertEquals(UrlUtil.TEXT_XML, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.xml"));
		assertEquals(UrlUtil.TEXT_XML, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.xsl"));
		assertEquals(UrlUtil.APPLICATION_CFF2, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.cf2"));
		assertEquals(UrlUtil.APPLICATION_ZIP, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.zip"));
		assertEquals(UrlUtil.APPLICATION_JSON, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.jsn"));
		assertEquals(UrlUtil.APPLICATION_JSON, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.json"));
		assertEquals(UrlUtil.getMimeTypeFromURL(null), JDFConstants.MIME_TEXTUNKNOWN);
		assertEquals(UrlUtil.getMimeTypeFromURL("foo.PDF"), JDFConstants.MIME_PDF);
	}

	/**
	 *
	 */
	@Test
	void testGetURLProtocol()
	{
		assertNull(UrlUtil.getProtocol(null));
		assertEquals(URLProtocol.http, UrlUtil.getProtocol("http://foo.bar.com"));
		assertEquals(URLProtocol.cid, UrlUtil.getProtocol("cid:blah"));
		assertEquals(URLProtocol.file, UrlUtil.getProtocol("file:blah"));
	}

	/**
	 *
	 */
	@Test
	void testExtension()
	{
		assertNull(UrlUtil.extension("foo"));
		assertEquals("", UrlUtil.extension("foo."));
		assertEquals("foo", UrlUtil.extension(".foo"));
		assertEquals("foo", UrlUtil.extension("a.b.foo"));
		assertEquals("foo", UrlUtil.extension("a.b..foo"));
		assertNull(UrlUtil.extension("/a/b.e/c"));
	}

	/**
	 * ensure we don't accidentally introduce artifacts if we add characters for escaping
	 *
	 * @throws UnsupportedEncodingException
	 */
	@Test
	void testEscapeUTF8() throws UnsupportedEncodingException
	{
		assertEquals(UrlUtil.m_URIEscape.length(), UrlUtil.m_URIEscape.getBytes(StringUtil.UTF8).length);
		assertEquals(UrlUtil.m_UNCEscape.length(), UrlUtil.m_UNCEscape.getBytes(StringUtil.UTF8).length);
	}

	/**
	 * test the standard url escaping routines
	 */
	@Test
	void testEscape()
	{
		assertNull(UrlUtil.escape(null, true, false));
		assertNull(UrlUtil.escape(null, false, false));
		assertEquals("%20", UrlUtil.escape(" ", false, false));
		assertEquals("a/b", UrlUtil.escape("a/b", false, false));
		assertEquals("a%2fb", UrlUtil.escape("a/b", false, true));
		assertEquals("foo%20bar", UrlUtil.escape("foo bar", true, false));
		assertEquals("%e2%82%ac", UrlUtil.escape("€", true, false));
		assertEquals("€", UrlUtil.escape("€", false, false));
		assertEquals("%23", UrlUtil.escape("#", false, false));
		assertEquals("ä%20ü", UrlUtil.escape("ä ü", false, false));
		assertEquals("Ä", UrlUtil.escape("Ä", false, false));
	}

	/**
	 * test adding a parameter
	 */
	@Test
	void testAddParameter()
	{
		assertEquals("a?b=c", UrlUtil.addParameter("a", "b", "c"));
		assertEquals("a", UrlUtil.addParameter("a", "", "c"));
		assertEquals("a", UrlUtil.addParameter("a", "a", null));
		assertEquals("a?b=c&bb=cc", UrlUtil.addParameter("a?b=c", "bb", "cc"));
		assertEquals("a?b=c%20d", UrlUtil.addParameter("a", "b", "c d"));
		assertEquals("a?b=http%3a%2f%2fwww.example.com", UrlUtil.addParameter("a", "b", "http://www.example.com"));
		assertEquals("Ã´", UrlUtil.escape("Ã´", false, false));
	}

	/**
	 * test adding a parameter
	 */
	@Test
	void testSetParameter()
	{
		assertEquals("", UrlUtil.setParameter("", "b", "c"));
		assertEquals("a?b=c", UrlUtil.setParameter("a", "b", "c"));
		assertEquals("a?b=c", UrlUtil.setParameter("a", "b", "c"));
		assertEquals("a", UrlUtil.setParameter("a", "", "c"));
		assertEquals("a", UrlUtil.setParameter("a", "a", null));
		assertEquals("a?b=c&bb=cc", UrlUtil.setParameter("a?b=c", "bb", "cc"));
		assertEquals("a?b=dd", UrlUtil.setParameter("a?b=c", "b", "dd"));
		assertEquals("a", UrlUtil.setParameter("a?b=c", "b", ""));
		assertEquals("a", UrlUtil.setParameter("a?b=c", "b", null));
		assertEquals("a?c=1&b=dd", UrlUtil.setParameter("a?b=c&c=1", "b", "dd"));
		assertEquals("a?b=dd&c=2", UrlUtil.setParameter("a?b=dd&c=1", "c", "2"));
		assertEquals("a?b=c%20d", UrlUtil.setParameter("a", "b", "c d"));
		assertEquals("a?b=http%3a%2f%2fwww.example.com", UrlUtil.setParameter("a", "b", "http://www.example.com"));
	}

	/**
	 * test adding a parameter
	 */
	@Test
	void testGetParameter()
	{
		assertEquals("c", UrlUtil.getParameter("a?b=c", "b"));
		assertEquals("c", UrlUtil.getParameter("a?d=e&b=c", "b"));
		assertEquals(null, UrlUtil.getParameter("a?b=c", "c"));
		assertEquals(null, UrlUtil.getParameter("a", "c"));
		assertEquals(null, UrlUtil.getParameter("a", ""));
		assertEquals(null, UrlUtil.getParameter("", "c"));
		assertEquals("http://www.example.com", UrlUtil.getParameter("a?b=http%3a%2f%2fwww.example.com", "b"));
	}

	/**
	 * test adding a path to a url
	 */
	@Test
	void testAddPath()
	{
		assertThrows(IllegalArgumentException.class, () -> UrlUtil.addPath("A", "/a"));
		assertThrows(IllegalArgumentException.class, () -> UrlUtil.addPath("A", "../a"));

		assertEquals("/A/a", UrlUtil.addPath("/A", "a"));
		assertEquals("A/a", UrlUtil.addPath("A", "a"));
		assertEquals("A/a", UrlUtil.addPath("A/", "a"));
		assertEquals("A/a..a", UrlUtil.addPath("A/", "a..a"));
		assertEquals("A/a?b=c", UrlUtil.addPath("A?b=c", "a"));
	}

	/**
	 * test adding a path to a url
	 */
	@Test
	void testGetSecurePath()
	{
		assertThrows(IllegalArgumentException.class, () -> UrlUtil.getSecurePath("A/../B", true));
		assertThrows(IllegalArgumentException.class, () -> UrlUtil.getSecurePath("A/../B", false));
		assertThrows(IllegalArgumentException.class, () -> UrlUtil.getSecurePath("A/%2E%2E/B", false));
		UrlUtil.getSecurePath("A/./B", false);
	}

	/**
	 *
	 */
	@Test
	void testWriteToURL()
	{
		if (!isTestNetwork())
			return;
		assertNotNull(UrlUtil.writeToURL("http://www.example.com", null, UrlUtil.GET, UrlUtil.TEXT_PLAIN, null));
	}

	/**
	 *
	 */
	@Test
	void testWriteToURLPost()
	{
		if (!isTestNetwork())
			return;
		final UrlPart writeToURL = UrlUtil.writeToURL("http://google.com", new ByteArrayInputStream("foo".getBytes()), UrlUtil.POST, "foo/bar", null);
		assertNotNull(writeToURL);
		writeToURL.buffer();
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testWriteToFTP() throws IOException
	{
		if (!isTestNetwork() || FTP_SITE == null)
			return;
		final UrlPart part = UrlUtil.writeToURL(FTP_SITE, null, null, null, null);
		assertEquals(part.getResponseCode(), 200);
		part.buffer();
		assertTrue(part.getResponseStream().available() > 100);
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testGetFTPInputstream() throws IOException
	{
		if (!isTestNetwork() || FTP_SITE == null)
			return;
		final InputStream is = UrlUtil.getURLInputStream(FTP_SITE);
		assertTrue(ByteArrayIOStream.getBufferedInputStream(is).available() > 100);
	}

	/**
	 *
	 */
	@Test
	void testWriteToURLClose()
	{
		if (!isTestNetwork())
			return;
		final HTTPDetails det = new HTTPDetails();
		det.setbKeepAlive(false);
		assertNotNull(UrlUtil.writeToURL("http://google.com", new ByteArrayInputStream("foo".getBytes()), UrlUtil.POST, "foo/bar", det));
	}

	/**
	 *
	 */
	@Test
	void testWriteToURLBearer()
	{
		if (!isTestNetwork())
			return;
		final HTTPDetails det = new HTTPDetails();
		det.setBearerToken("abc");
		final UrlPart writeToURL = UrlUtil.writeToURL("http://google.com", null, UrlUtil.GET, null, det);
		assertNotNull(writeToURL);
		writeToURL.buffer();
		final String s = new String(ByteArrayIOStream.getBufferedInputStream(writeToURL.getResponseStream()).getBuf());
		assertEquals(200, writeToURL.getResponseCode());
	}

	/**
	 *
	 */
	@Test
	void testWriteToURLRedirect()
	{
		if (!isTestNetwork())
			return;
		assertEquals(UrlUtil.writeToURL("http://google.ch", null, UrlUtil.GET, null, null).getResponseCode(), 200);
	}

	/**
	 *
	 */
	@Test
	void testWriteToURLSecure()
	{
		if (!isTestNetwork())
			return;
		UrlPart writeToURL = UrlUtil.writeToURL("https://google.ch", null, UrlUtil.GET, null, null);
		if (writeToURL == null)
		{
			ThreadUtil.sleep(1234);
			writeToURL = UrlUtil.writeToURL("https://google.ch", null, UrlUtil.GET, null, null);
		}
		assertEquals(writeToURL.getResponseCode(), 200);
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testWriteToURLSecureStream() throws IOException
	{
		if (!isTestNetwork())
			return;
		final UrlPart part = UrlUtil.writeToURL("https://google.ch", null, UrlUtil.GET, null, null);
		assertEquals(part.getResponseCode(), 200);
		part.buffer();

		assertTrue(part.getResponseStream().available() > 100);
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testGetHTTPSInputstream() throws IOException
	{
		if (!isTestNetwork())
			return;
		final InputStream is = UrlUtil.getURLInputStream("https://google.ch");
		assertTrue(ByteArrayIOStream.getBufferedInputStream(is).available() > 100);
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testGetBodyInputstream() throws IOException
	{
		final InputStream is = UrlUtil.getURLInputStream(UrlUtil.fileToUrl(new File(sm_dirTestData + "url1.pdf"), false), null);
		assertNotNull(is);
		is.close();
	}

	/**
	 * @throws IOException
	 *
	 *
	 */
	@Test
	void testGetZipInputstream() throws IOException
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "testZip.zip");
		final InputStream is2 = UrlUtil.getURLInputStream("content/boo.pdf", null, r);
		assertNotEquals(0, is2.read());
	}

	/**
	 *
	 */
	@Test
	void testIsRedirect()
	{
		assertTrue(UrlUtil.isRedirect(302));
		assertFalse(UrlUtil.isRedirect(42));
		assertFalse(UrlUtil.isRedirect(-301));
	}

	/**
	 *
	 */
	@Test
	void testIsReturnOK()
	{
		assertTrue(UrlUtil.isReturnCodeOK(202));
		assertTrue(UrlUtil.isReturnCodeOK(200));
		assertFalse(UrlUtil.isReturnCodeOK(420));
		assertFalse(UrlUtil.isReturnCodeOK(20));
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testIsReturnOKPart() throws IOException
	{
		final UrlPart p = new UrlPart(new File(sm_dirTestData + "url1.pdf"));
		assertTrue(UrlUtil.isReturnCodeOK(p));
		assertFalse(UrlUtil.isReturnCodeOK(new UrlPart(new File(""))));
		assertFalse(UrlUtil.isReturnCodeOK(null));
	}

	/**
	 *
	 */
	@Test
	void testGetDetails()
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
	void testWriteToURLNull()
	{
		if (!isTestNetwork())
			return;
		assertNull(UrlUtil.writeToURL(null, null, UrlUtil.GET, UrlUtil.TEXT_PLAIN, null));
	}

	/**
	 *
	 */
	@Test
	void testRemoveProtocol()
	{
		assertNull(UrlUtil.removeProtocol(null));
		assertNull(UrlUtil.removeProtocol("http://"));
		assertEquals("a", UrlUtil.removeProtocol("http://a"));
		assertEquals("abb:8080", UrlUtil.removeProtocol("http://abb:8080"));
		assertEquals("foo", UrlUtil.removeProtocol("cid:foo"));
		assertEquals("foo", UrlUtil.removeProtocol("CID:foo"));
	}

	/**
	 *
	 */
	@Test
	void testIsCid()
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
	void testIsNotCid()
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
	void testIsHTTP()
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
	void testIsHTTPS()
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
	void testIsNet()
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
	void testIsIRL()
	{
		assertTrue(UrlUtil.isIRL("file://blÃ¯Â¿Â½dÃ¯Â¿Â½.txt"));
		assertTrue(UrlUtil.isIRL("http://foo.com/blÃ¯Â¿Â½dÃ¯Â¿Â½.txt"));
		assertFalse(UrlUtil.isIRL("http:///blÃ¯Â¿Â½dÃ¯Â¿Â½.txt"), "3 ///");
		assertFalse(UrlUtil.isIRL("file://a blÃ¯Â¿Â½dÃ¯Â¿Â½.txt"), "blank is bad");
		assertTrue(UrlUtil.isIRL("file://a%20blÃ¯Â¿Â½dÃ¯Â¿Â½.txt"), "blank %20 is good");
		assertTrue(UrlUtil.isIRL("file:C:/a/b.txt"));
		assertTrue(UrlUtil.isIRL("./3Ã¯Â¿Â½.txt"), "relative url");
		assertFalse(UrlUtil.isIRL("http://@"), "invalid char: @");
		assertTrue(UrlUtil.isIRL("HTTP://Ã¯Â¿Â½/Ã¯Â¿Â½"));
		assertTrue(UrlUtil.isIRL("file:///C:/Documents%20and%20Settings/Israel/My%20Documents/Vio%20Production/Results/TIME_H8789/TIME_H8789.pdf"));
	}

	/**
	 *
	 */
	@Test
	void testIsURL()
	{
		assertFalse(UrlUtil.isURL(null));
		assertTrue(UrlUtil.isURL("file://bl.txt"));
		assertTrue(UrlUtil.isURL("http://foo.com/bl.txt"));
		assertFalse(UrlUtil.isURL("http:///bl.txt"), "3 ///");
		assertFalse(UrlUtil.isURL("file://a b.txt"), "blank is bad");
		assertTrue(UrlUtil.isURL("file://a%20bl.txt"), "blank %20 is good");
		assertTrue(UrlUtil.isURL("file:C:/a/b.txt"));
		assertTrue(UrlUtil.isURL("./3.txt"), "relative url");
		assertFalse(UrlUtil.isURL("http://@"), "invalid char: @");
		assertFalse(UrlUtil.isURL("\\\\unc\\bar\\a.txt"), "UNC");
		assertTrue(UrlUtil.isURL("HTTP://a/b?c"));
	}

	/**
	*
	*/
	@Test
	void testIsURLPerformance()
	{
		final CPUTimer ct = new CPUTimer(false);
		for (int i = 0; i < 1234; i++)
		{
			ct.start();
			assertFalse(UrlUtil.isURL(null));
			assertTrue(UrlUtil.isURL("file://bl.txt"));
			assertTrue(UrlUtil.isURL("http://foo.com/bl.txt"));
			assertFalse(UrlUtil.isURL("http:///bl.txt"), "3 ///");
			assertFalse(UrlUtil.isURL("file://a b.txt"), "blank is bad");
			assertTrue(UrlUtil.isURL("file://a%20bl.txt"), "blank %20 is good");
			assertTrue(UrlUtil.isURL("file:C:/a/b.txt"));
			assertTrue(UrlUtil.isURL("./3.txt"), "relative url");
			assertFalse(UrlUtil.isURL("http://@"), "invalid char: @");
			assertFalse(UrlUtil.isURL("\\\\unc\\bar\\a.txt"), "UNC");
			assertTrue(UrlUtil.isURL("HTTP://a/b?c"));
			ct.stop();
		}
		System.out.println(ct.toString());
	}

	/**
	 *
	 */
	@Test
	void testIsXMLType()
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
	void testIsJSONType()
	{
		assertFalse(UrlUtil.isJSONType("text/xml"));
		assertFalse(UrlUtil.isJSONType("TEXT/XML"));
		assertTrue(UrlUtil.isJSONType("TEXT/JSON; "));
		assertTrue(UrlUtil.isJSONType("application/json "));
	}

	/**
	 *
	 */
	@Test
	void testIsZIPType()
	{
		assertFalse(UrlUtil.isZIPType("text/xml"));
		assertFalse(UrlUtil.isZIPType("text/xml;"));
		assertFalse(UrlUtil.isZIPType(";"));
		assertFalse(UrlUtil.isZIPType(""));
		assertTrue(UrlUtil.isZIPType("application/zip"));
		assertTrue(UrlUtil.isZIPType("application/foobar+zip; "));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * @throws Exception if snafu
	 */
	@Test
	void testStringToURL() throws Exception
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
			assertEquals(UrlUtil.stringToURL("File:/c:/blÃ¶d €.pdf"), new URL(UrlUtil.fileToUrl(new File("c:/blÃ¶d €.pdf"), true)));
			assertEquals(UrlUtil.stringToURL("c:\\xyz\\").getPath(), new URL("File:/c:/xyz").getPath());
			assertEquals(UrlUtil.stringToURL("File:/c:/xyz/").getPath(), new URL("File:/c:/xyz").getPath());
			assertEquals(UrlUtil.stringToURL("File:\\\\host\\dir\\dir2\\file.pdf").getPath(), new URL("File://host/dir/dir2/file.pdf").getPath());
			assertEquals(UrlUtil.stringToURL("File:\\\\host\\dir\\dir2/dir3/file.pdf").getPath(), new URL("File://host/dir/dir2/dir3/file.pdf").getPath());
			assertEquals(UrlUtil.stringToURL("c:\\xyz").getPath(), new URL("File:/c:/xyz").getPath());

			assertEquals(UrlUtil.stringToURL("file://foo/a .txt"), new URL("file://foo/a%20.txt"));
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
		assertNull(UrlUtil.stringToURL("File:"), "empty File: should be null");
		assertEquals(UrlUtil.stringToURL("http%3A%2F%2FDRU-CIP4HD1%3A6331"), new URL("http://DRU-CIP4HD1:6331"));
		assertEquals(new URL("https://foo/-a1d3-7b4e52b36407/dywEqM_chouchou-düc-2019.pdf"), UrlUtil.stringToURL("https://foo/-a1d3-7b4e52b36407/dywEqM_chouchou-düc-2019.pdf"));

	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testIsEscaped()
	{
		assertTrue(UrlUtil.isEscaped("http%3A%2F%2FDRU-CIP4HD1%3A6331"));
		assertFalse(UrlUtil.isEscaped("file:http%3A%2F%2FDRU-CIP4HD1%3A6331"));
		assertFalse(UrlUtil.isEscaped("text%20a.pdf"));
		assertFalse(UrlUtil.isEscaped("-a1d3-7b4e52b36407/dywEqM_chouchou-düc-2019.pdf"));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 *
	 */
	@Test
	void testGetBytesFromIP()
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
	void testGetIPFromBytes()
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
	void testFileToURL()
	{
		if (PlatformUtil.isWindows())
		{ // on windows
			final File f = new File("C:\\IO.SYS");
			String s = UrlUtil.fileToUrl(f, false);
			assertEquals("file:///C:/IO.SYS", s);
			s = UrlUtil.fileToUrl(new File("\\\\fooBar\\4€.txt"), false);
			assertEquals("file://fooBar/4€.txt", s);
		}
		else
		{
			final String s = UrlUtil.fileToUrl(new File("/fooBar/4€.txt"), true);
			assertEquals("file:/fooBar/4%e2%82%ac.txt", s);
			assertEquals("file:/a/4%25.txt", UrlUtil.fileToUrl(new File("/a/4%.txt"), false));
		}
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testNonAsciiFileURL() throws Exception
	{
		for (int i = 0; i < 2; i++) // loop over escape and non-escape
		{
			final File f = new File("4€öÃ¶Ã¼.txt");
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
	void testURLToFileName()
	{
		assertEquals("b.c", UrlUtil.urlToFileName("a:b.c"));
		assertEquals("b.c", UrlUtil.urlToFileName("http:/b.c?gg"));
	}

	/**
	 *
	 */
	@Test
	void testUNCToUrl()
	{

		assertEquals("file://a/b/c.txt", UrlUtil.uncToUrl("\\\\a\\b\\c.txt", true));
		String unc = "\\\\a\\b\\c ö.txt";
		String url = UrlUtil.uncToUrl(unc, true);
		assertEquals("file://a/b/c%20%c3%b6.txt", url);
		final String unc2 = unc;

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
	void testURLToUNC() throws Exception
	{
		assertEquals("a%5cb", UrlUtil.urlToUNC("file:a%5cb"));
		assertEquals("a%25", UrlUtil.urlToUNC("file:a%2525"));
		assertEquals("a b", UrlUtil.urlToUNC("file:a%20b"));
		assertEquals("a b#", UrlUtil.urlToUNC("file:a%20b%23"));
		assertEquals("a%2fb\\d", UrlUtil.urlToUNC("file:a%2fb/d"));
		assertNull(UrlUtil.urlToUNC(""));
		assertNull(UrlUtil.urlToUNC("c:\\foo"));
		assertEquals("\\\\host\\dir\\file", UrlUtil.urlToUNC("\\\\host\\dir\\file"));
		assertEquals("\\\\host\\dir\\file", UrlUtil.urlToUNC("//host/dir/file"));
		assertEquals("\\\\host\\dir\\file", UrlUtil.urlToUNC("file://host/dir/file"));
		assertEquals("\\\\host\\dir\\file", UrlUtil.urlToUNC("file:\\\\host\\dir\\file"));
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testIsFileOK() throws Exception
	{
		assertFalse(UrlUtil.isFileOK(null));
		assertFalse(UrlUtil.isFileOK(new File(".")));
		final File file = new File(sm_dirTestDataTemp + "dummy.tmp");
		file.createNewFile();
		assertTrue(UrlUtil.isFileOK(file));
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testURLToFile() throws Exception
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
			assertEquals(f.getCanonicalPath(), f2.getCanonicalPath(), "asccii");

			f = new File("blÃ¶Â½d .pdf");
			f2 = UrlUtil.urlToFile(UrlUtil.fileToUrl(f, i == 0));
			assertEquals(f.getCanonicalPath(), f2.getCanonicalPath(), "non asccii");

			f = new File("blÃ¶d .pdf");
			f2 = UrlUtil.urlToFile(UrlUtil.fileToUrl(f, i == 0));
			assertEquals(f.getCanonicalPath(), f2.getCanonicalPath(), "non asccii");

			f = new File("blÃ¶d ist es 10€.pdf");
			final String fileToUrl = UrlUtil.fileToUrl(f, i == 0);
			f2 = UrlUtil.urlToFile(fileToUrl);
			assertEquals(f.getCanonicalPath(), f2.getCanonicalPath(), "escape %20");
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
			assertEquals(fi.getCanonicalPath(), fi1.getCanonicalPath(), "escape %20");
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
	void testisRelativeURL()
	{
		assertFalse(UrlUtil.isRelativeURL(""));
		assertFalse(UrlUtil.isRelativeURL("\\\\foo\\bar"));
		assertTrue(UrlUtil.isRelativeURL("c/d/e.f"));
		assertFalse(UrlUtil.isRelativeURL("/c/d/e.f"));
		assertFalse(UrlUtil.isRelativeURL("http://c/d/e.f"));
		assertFalse(UrlUtil.isRelativeURL("http://c:8080/c/d/e.f"));
		assertFalse(UrlUtil.isRelativeURL("c:\\foo"));
		assertFalse(UrlUtil.isRelativeURL("cid:c/d/e.f"));
		assertFalse(UrlUtil.isRelativeURL("?"));
		assertTrue(UrlUtil.isRelativeURL("abc?foo=http://a"));
	}

	/**
	 *
	 */
	@Test
	void testisUNC()
	{
		assertTrue(UrlUtil.isUNC("\\\\foo\\bar"));
		assertFalse(UrlUtil.isUNC("c/d/e.f"));
		assertFalse(UrlUtil.isUNC("/c/d/e.f"));
	}

	/**
	 *
	 */
	@Test
	void testGetRelativeURI()
	{
		File f = new File("./a b");
		assertEquals("a%20b", StringUtil.replaceChar(UrlUtil.getRelativeURL(f, null, true), '\\', "/", 0));
		f = new File("../a.ä");
		assertEquals("../a.%c3%a4", StringUtil.replaceChar(UrlUtil.getRelativeURL(f, null, true), '\\', "/", 0), "escaped utf8");
		assertEquals(new String(StringUtil.getUTF8Bytes("../a.ä")), StringUtil.replaceChar(UrlUtil.getRelativeURL(f, null, false), '\\', "/", 0), "unescaped but utf8");
	}

	/**
	 *
	 */
	@Test
	void testGetParentDirectory()
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
	void testGetRelativeURL()
	{
		File file = new File("c:\\a\\b\\c.txt");
		final File cwd = new File("c:\\a\\b1");
		assertEquals("../b/c.txt", UrlUtil.getRelativeURL(file, cwd, true));
		file = new File("c:\\a\\b1\\c.txt");
		assertEquals("c.txt", UrlUtil.getRelativeURL(file, cwd, true));
		file = new File("a\\..\\b\\c.txt");
		assertEquals("b/c.txt", UrlUtil.getRelativeURL(file, null, true));
		file = cwd;
		assertEquals(".", UrlUtil.getRelativeURL(file, cwd, true));
	}

	/**
	 *
	 */
	@Test
	void testGetURLWithDirectory()
	{
		String url = "File://a.b";
		assertEquals(url, UrlUtil.getURLWithDirectory("", url), "test nulls");
		assertEquals(url, UrlUtil.getURLWithDirectory(null, url), "test nulls");
		assertEquals(url, UrlUtil.getURLWithDirectory("File:/dir", url), "test nulls");

		url = "a.b";
		assertEquals("File://dir/a.b", UrlUtil.getURLWithDirectory("File://dir/", url), "relative url");
		assertEquals("File://dir/a.b", UrlUtil.getURLWithDirectory("File://dir", url), "relative url");

		url = "/a.b";
		assertEquals("File://a.b", UrlUtil.getURLWithDirectory("File://host/", url), "absolute url no host");
		assertEquals("File://a.b", UrlUtil.getURLWithDirectory("File://host", url), "absolute url no host");

		url = "/a.b:c";
		assertEquals("File://a.b:c", UrlUtil.getURLWithDirectory("File://host/", url), "absolute url no host - colon");
		assertEquals("File://a.b:c", UrlUtil.getURLWithDirectory("File://host", url), "absolute url no host - colon");

		url = "//a.b";
		assertEquals("File://a.b", UrlUtil.getURLWithDirectory("File://dir/", url), "absolute url with default host");
		assertEquals("File://a.b", UrlUtil.getURLWithDirectory("File://dir", url), "absolute url with default host");

		url = "ftp://a.b";
		assertEquals("ftp://a.b", UrlUtil.getURLWithDirectory("File://dir/", url), "absolute url with default host");
		url = "http://a.b";
		assertEquals("http://a.b", UrlUtil.getURLWithDirectory("File://dir/", url), "absolute url with default host");

		url = "//boo/a.b";
		assertEquals("File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir/", url), "absolute url with new host");
		assertEquals("File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir", url), "absolute url with new host");

		url = "//boo/./gg/../a.b";
		assertEquals("File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir/", url), "absolute url with new host and cleandots");
		assertEquals("File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir", url), "absolute url with new host and cleandots");

		assertEquals("http://dir/a.b", UrlUtil.getURLWithDirectory("http://dir", "a.b"), "http url");
	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	void testRemoveExtension()
	{
		assertEquals("a", UrlUtil.prefix("a.b"));
		assertEquals("a", UrlUtil.prefix("a"));
		assertEquals("a", UrlUtil.prefix("a."));
		assertEquals("a.b", UrlUtil.prefix("a.b.c"));
		assertEquals("", UrlUtil.prefix("."));
		assertEquals("/a/b.e/c", UrlUtil.prefix("/a/b.e/c"));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testCleanDots()
	{
		assertEquals(".", UrlUtil.cleanDots("."));
		assertEquals("..", UrlUtil.cleanDots(".."));
		assertEquals(".", UrlUtil.cleanDots("a/.."));
		assertEquals("../../c.pdf", UrlUtil.cleanDots("../../c.pdf"));
		assertEquals("../../c.pdf", UrlUtil.cleanDots(".././../c.pdf"));
		assertEquals("File://b", UrlUtil.cleanDots("File://a/../b"));
		assertEquals("File://b.pdf", UrlUtil.cleanDots("File://a/.././c/../b.pdf"));
		assertEquals("File:///c:/b.pdf", UrlUtil.cleanDots("File:///c:/a/.././c/../b.pdf"));
		assertEquals("/.", UrlUtil.cleanDots("/."));
	}

	/**
	 *
	 */
	@Test
	void testAddSecure()
	{
		assertThrows(IllegalArgumentException.class, () -> UrlUtil.addSecure(".", null));
		assertThrows(IllegalArgumentException.class, () -> UrlUtil.addSecure("a", "b"));
		assertThrows(IllegalArgumentException.class, () -> UrlUtil.addSecure("file://abc", ""));
		assertThrows(IllegalArgumentException.class, () -> UrlUtil.addSecure("file://abc/../c", "a"));
		assertThrows(IllegalArgumentException.class, () -> UrlUtil.addSecure("file://abc", ".."));
		assertThrows(IllegalArgumentException.class, () -> UrlUtil.addSecure("file://abc", "a/../c"));
		assertThrows(IllegalArgumentException.class, () -> UrlUtil.addSecure("file://abc", "a\\..\\b"));
		assertEquals("file://abc/a/b", UrlUtil.addSecure("file://abc", "a/b"));
		assertEquals("file://abc/a/b", UrlUtil.addSecure("file://abc/", "a/b"));
	}

	/**
	 *
	 */
	@Test
	void testCleanHttpUrl()
	{
		assertEquals("http://localhost", UrlUtil.cleanHttpURL("localhost"));
		assertEquals("http://localhost", UrlUtil.cleanHttpURL("/http/localhost"));
		assertEquals("http://localhost", UrlUtil.cleanHttpURL("http:/localhost"));
		assertEquals("http://localhost:8080/ggg", UrlUtil.cleanHttpURL("http:/localhost:8080//ggg"));
	}

	/**
	 *
	 *
	 */
	@Test
	void testCreateHttpUrl()
	{
		assertEquals("http://d/a", UrlUtil.createHttpUrl(false, "d", 0, "a"));
		assertEquals("http://d:8080/a", UrlUtil.createHttpUrl(false, "d", 8080, "a"));
		assertEquals("https://d:8080/a", UrlUtil.createHttpUrl(true, "d", 8080, "a"));
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	void testMoveToDirMime() throws Exception
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
		assertNotNull(f, "error moving file to dir");
		for (int i = 0; i < 100; i++)
		{
			ThreadUtil.sleep(100);
			if (fs.getURL().contains(UrlUtil.fileToUrl(newDir, false)))
			{
				break;
			}
			log.info("Waiting " + i);
		}
		final long l = f.lastModified();
		final File f2 = UrlUtil.moveToDir(fs, newDir, null, false);
		assertNotNull(f2, "error moving file to dir");
		ThreadUtil.sleep(42);
		// assertEquals(l, f2.lastModified(), 0);
		fs.setURL("bad:/blÃ¶d");
		assertNull(UrlUtil.moveToDir(fs, newDir, null, true), "bad url:");
		fs.setURL("http:localhost:2");
		assertNull(UrlUtil.moveToDir(fs, newDir, null, true), "bad url:");
		fs.setURL("./blub.pdf");
		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "dummy/blub.pdf"));
		assertTrue(UrlUtil.moveToDir(fs, newDir, sm_dirTestDataTemp + "dummy", true).exists(), "relative:");
		fs.setURL("deep/blub.pdf");
		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "dummy/deep/blub.pdf"));
		assertTrue(UrlUtil.moveToDir(fs, newDir, sm_dirTestDataTemp + "dummy", true).exists(), "relative:");

	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	void testMoveToDir() throws Exception
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFRunList rl = (JDFRunList) d.getJDFRoot().addResource(ElementName.RUNLIST, EnumUsage.Input);
		final JDFFileSpec fs = rl.addPDF("./content/boo.pdf", 0, -1).getLayoutElement().getFileSpec();

		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "URLIn/content/boo.pdf"));

		final File newDir = new File(sm_dirTestDataTemp + "newDir");
		fs.setURL("bad:/blÃ¶d");
		assertNull(UrlUtil.moveToDir(fs, newDir, null, true), "bad url:");
		// fs.setURL("http://really_really_not_there.com/isnt/there?aaa");
		fs.setURL("http://localhost:2");
		assertNull(UrlUtil.moveToDir(fs, newDir, null, true), "bad url:");
		fs.setURL("./blub.pdf");
		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "dummy/blub.pdf"));
		assertTrue(UrlUtil.moveToDir(fs, newDir, sm_dirTestDataTemp + "dummy", true).exists(), "relative:");
		fs.setURL("deep/blub.pdf");
		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "dummy/deep/blub.pdf"));
		assertTrue(UrlUtil.moveToDir(fs, newDir, sm_dirTestDataTemp + "dummy", true).exists(), "relative:");
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	void testMoveToDownDir() throws Exception
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFRunList rl = (JDFRunList) d.getJDFRoot().addResource(ElementName.RUNLIST, EnumUsage.Input);
		final JDFFileSpec fs = rl.addPDF("./content/boo.pdf", 0, -1).getLayoutElement().getFileSpec();

		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "URLIn/content/boo2.pdf"));

		final File newDir = new File(sm_dirTestDataTemp + "newDir2");
		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "blub2.pdf"));
		fs.setURL("../blub2.pdf");
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	void testMoveToDirDelete() throws Exception
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFRunList rl = (JDFRunList) d.getJDFRoot().addResource(ElementName.RUNLIST, EnumUsage.Input);
		final JDFFileSpec fs = rl.addPDF("./content/boo.pdf", 0, -1).getLayoutElement().getFileSpec();

		final File newDir = new File(sm_dirTestDataTemp + "newDir");
		fs.setURL("./blub.pdf");
		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "dummy/blub.pdf"));
		assertTrue(UrlUtil.moveToDir(fs, newDir, sm_dirTestDataTemp + "dummy", true, true).exists(), "relative:");
		assertFalse(new File(sm_dirTestDataTemp + "dummy/blub.pdf").exists(), "relative:");

		fs.setURL("deep/blub.pdf");
		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "dummy/deep/blub.pdf"));
		assertTrue(UrlUtil.moveToDir(fs, newDir, sm_dirTestDataTemp + "dummy", true, true).exists(), "relative:");
		assertFalse(new File(sm_dirTestDataTemp + "dummy/deep/blub.pdf").exists(), "relative:");
	}

	/**
	 *
	 */
	@Test
	void testNormalize()
	{
		assertEquals("a.b", UrlUtil.normalize("a.b"));
		assertEquals("a.b", UrlUtil.normalize("./a.b"));
		assertEquals("a.b", UrlUtil.normalize("././a.b"));
		assertEquals("http://a/a.b", UrlUtil.normalize("http://a/a.b"));
		assertEquals("http://a/a%20.b", UrlUtil.normalize("http://a/a%20.b"));
		assertEquals("http://a/a.b", UrlUtil.normalize("HTTP://a/a.b"));
		assertEquals("http://a/a.b", UrlUtil.normalize("HTTP://a//a.b"));
		assertEquals("cid:a.b", UrlUtil.normalize("cid:a.b"));
		assertEquals("cid:a.b", UrlUtil.normalize("<cid:a.b>"));
		assertEquals("http://a/a.b?f=g", UrlUtil.normalize("http://a/a.b?f=g"));
		assertEquals("http:/a/a.b?f=g", UrlUtil.normalize("http:/a/a.b?f=g"));
		assertNull(UrlUtil.normalize("http://a:b"));
	}

	/**
	 *
	 *
	 */
	@Test
	void testNormalizeFile()
	{
		assertEquals("file://host/dir/a%20a.b", UrlUtil.normalize("\\\\host\\dir\\a a.b"));
		assertEquals("file://host/dir/a%20ö.b", UrlUtil.normalize("\\\\host\\dir\\a ö.b"));
		assertEquals("file://host/dir/a%20ö.b", UrlUtil.normalize("FILE://host/dir/a ö.b"));
	}

	/**
	 *
	 */
	@Test
	void testUnEscape()
	{
		assertEquals("a.b", UrlUtil.unEscape("a.b"));
		assertEquals("a.b", UrlUtil.unEscape("a.b"));
		assertEquals("aaa%", UrlUtil.unEscape("aaa%"));
		assertEquals("%2", UrlUtil.unEscape("%2"));
		assertEquals(" ", UrlUtil.unEscape("%20"));
		assertEquals("#", UrlUtil.unEscape("%23"));
		assertEquals("äöü€", UrlUtil.unEscape("äöü€"));
	}

	/**
	 *
	 */
	@Test
	void testNewExtension()
	{
		assertEquals("a.c", UrlUtil.newExtension("a.b", "c"));
		assertEquals("a.b.c", UrlUtil.newExtension("a.b.c", "c"));
		assertEquals("a", UrlUtil.newExtension("a.b", null));
		assertEquals("a.c", UrlUtil.newExtension("a.b", ".c"));
		assertEquals("a.c.d", UrlUtil.newExtension("a.b", ".c.d"));
		assertEquals("a.c.d", UrlUtil.newExtension("a.b", "c.d"));
		assertEquals("a.b.c.d", UrlUtil.newExtension("a.b.bb", "c.d"));
		assertEquals(".c", UrlUtil.newExtension(".b", ".c"));
		assertEquals("a.c", UrlUtil.newExtension("a", ".c"));
		assertEquals("/a/b.e/c.txt", UrlUtil.newExtension("/a/b.e/c", ".txt"));
	}
}
