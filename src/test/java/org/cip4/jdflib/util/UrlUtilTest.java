/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of
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
import java.net.URL;
import java.nio.charset.Charset;

import javax.mail.BodyPart;
import javax.mail.Multipart;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams;
import org.cip4.jdflib.util.UrlUtil.URLProtocol;
import org.cip4.jdflib.util.net.ProxyUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 12.01.2009
 */
public class UrlUtilTest extends JDFTestCaseBase {
	// /////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testGetLocalURL() {
		Assert.assertNull(UrlUtil.getLocalURL("foo", "foo"));
		Assert.assertNull(UrlUtil.getLocalURL("foo", null));
		Assert.assertEquals(UrlUtil.getLocalURL("foo", "foo/bar"), "bar");
		Assert.assertEquals(UrlUtil.getLocalURL("/foo", "foo/bar"), "bar");
		Assert.assertEquals(UrlUtil.getLocalURL("foo", "/foo/bar"), "bar");
		Assert.assertEquals(UrlUtil.getLocalURL("foo/", "foo/bar"), "bar");
		Assert.assertEquals(UrlUtil.getLocalURL(null, "foo/bar"), "foo/bar");
		Assert.assertEquals(UrlUtil.getLocalURL("", "foo/bar"), "foo/bar");
		Assert.assertEquals(UrlUtil.getLocalURL("file://foo", "File://foo/bar/a.b"), "bar/a.b");
	}

	/**
	 * 
	 */
	@Test
	public void testGetMimeTypeFromExt() {
		Assert.assertEquals(UrlUtil.TEXT_UNKNOWN, UrlUtil.getMimeTypeFromURL("www.foobar.com"));
		Assert.assertEquals(UrlUtil.VND_JDF, UrlUtil.getMimeTypeFromURL(".JDF"));
		Assert.assertEquals(UrlUtil.VND_JDF, UrlUtil.getMimeTypeFromURL(".jdf"));
		Assert.assertEquals(UrlUtil.VND_JDF, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.jdf"));
		Assert.assertEquals(UrlUtil.VND_JMF, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.JMF"));
		Assert.assertEquals(UrlUtil.TEXT_XML, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.xml"));
		Assert.assertEquals(UrlUtil.APPLICATION_ZIP, UrlUtil.getMimeTypeFromURL("http://fobar.con/snarf.zip"));
		Assert.assertEquals(UrlUtil.getMimeTypeFromURL(null), JDFConstants.MIME_TEXTUNKNOWN);
		Assert.assertEquals(UrlUtil.getMimeTypeFromURL("foo.PDF"), JDFConstants.MIME_PDF);
	}

	/**
	 * 
	 */
	@Test
	public void testGetURLProtocol() {
		Assert.assertEquals(null, UrlUtil.getProtocol(null));
		Assert.assertEquals(URLProtocol.http, UrlUtil.getProtocol("http://foo.bar.com"));
		Assert.assertEquals(URLProtocol.cid, UrlUtil.getProtocol("cid:blah"));
		Assert.assertEquals(URLProtocol.file, UrlUtil.getProtocol("file:blah"));
	}

	/**
	 * 
	 */
	@Test
	public void testExtension() {
		Assert.assertNull(UrlUtil.extension("foo"));
		Assert.assertEquals("", UrlUtil.extension("foo."));
		Assert.assertEquals("foo", UrlUtil.extension(".foo"));
		Assert.assertEquals("foo", UrlUtil.extension("a.b.foo"));
		Assert.assertEquals("foo", UrlUtil.extension("a.b..foo"));
	}

	/**
	 * test the standard url escaping routines
	 */
	@Test
	public void testEscape() {
		Assert.assertNull(UrlUtil.escape(null, true));
		Assert.assertNull(UrlUtil.escape(null, false));
		Assert.assertEquals("%20", UrlUtil.escape(" ", false));
		Assert.assertEquals("foo%20bar", UrlUtil.escape("foo bar", true));
		Assert.assertEquals("%e2%82%ac", UrlUtil.escape("€", true));
		Assert.assertEquals("€", UrlUtil.escape("€", false));
	}

	/**
	 * test adding a parameter
	 */
	@Test
	public void testAddParameter() {
		Assert.assertEquals("a?b=c", UrlUtil.addParameter("a", "b", "c"));
		Assert.assertEquals("a?b=c&bb=cc", UrlUtil.addParameter("a?b=c", "bb", "cc"));
		Assert.assertEquals("a?b=c%20d", UrlUtil.addParameter("a", "b", "c d"));
		Assert.assertEquals("a?b=http%3a%2f%2fwww.example.com", UrlUtil.addParameter("a", "b", "http://www.example.com"));
	}

	/**
	 * test adding a path to a url
	 */
	@Test
	public void testAddPath() {
		Assert.assertEquals("A/a", UrlUtil.addPath("A", "a"));
		Assert.assertEquals("A/a", UrlUtil.addPath("A/", "/a"));
		Assert.assertEquals("A/a?b=c", UrlUtil.addPath("A?b=c", "a"));
	}

	/**
	 * 
	 */
	@Test
	public void testWriteToURL() {
		if (!isTestNetwork()) {
			log.info("skipping network test");
			return;
		}
		ProxyUtil.setProxy("proxy", 8082, null, null);
		Assert.assertNotNull(UrlUtil.writeToURL("http://www.example.com", null, UrlUtil.GET, UrlUtil.TEXT_PLAIN, null));
	}

	/**
	 * 
	 */
	@Test
	public void testWriteToURLPost() {
		if (!isTestNetwork()) {
			log.info("skipping network test");
			return;
		}
		ProxyUtil.setProxy("proxy", 8082, null, null);
		Assert.assertNotNull(UrlUtil.writeToURL("http://localhost:4021/foo/bar", new ByteArrayInputStream("foo".getBytes()), UrlUtil.POST, "foo/bar", null));
	}

	/**
	 * 
	 */
	@Test
	public void testWriteToURLNull() {
		Assert.assertNull(UrlUtil.writeToURL(null, null, UrlUtil.GET, UrlUtil.TEXT_PLAIN, null));
	}

	/**
	 * 
	 */
	@Test
	public void testRemoveProtocol() {
		Assert.assertNull(UrlUtil.removeProtocol(null));
		Assert.assertNull(UrlUtil.removeProtocol("http://"));
		Assert.assertEquals("a", UrlUtil.removeProtocol("http://a"));
		Assert.assertEquals("abb:8080", UrlUtil.removeProtocol("http://abb:8080"));
		Assert.assertEquals("foo", UrlUtil.removeProtocol("cid:foo"));
		Assert.assertEquals("foo", UrlUtil.removeProtocol("CID:foo"));
	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testIsCid() {
		Assert.assertTrue(UrlUtil.isCID("cid:foo"));
		Assert.assertTrue(UrlUtil.isCID("<cid:foo>"));
		Assert.assertTrue(UrlUtil.isCID("<cid:"));
		Assert.assertFalse(UrlUtil.isCID(null));
		Assert.assertFalse(UrlUtil.isCID("<"));
	}

	/**
	 * 
	 */
	@Test
	public void testIsNotCid() {
		Assert.assertFalse(UrlUtil.isNotCID("cid:foo"));
		Assert.assertFalse(UrlUtil.isNotCID("bar.foo"));
		Assert.assertTrue(UrlUtil.isNotCID("file:foo"));
		Assert.assertTrue(UrlUtil.isNotCID("http://foo"));
		Assert.assertTrue(UrlUtil.isNotCID("https://foo"));
	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testIsHTTP() {
		Assert.assertTrue(UrlUtil.isHttp("http://foo.bar.com"));
		Assert.assertFalse(UrlUtil.isHttp(null));
		Assert.assertFalse(UrlUtil.isHttp("foo.bar.com"));
	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testIsIRL() {
		Assert.assertTrue(UrlUtil.isIRL("file://blï¿½dï¿½.txt"));
		Assert.assertTrue(UrlUtil.isIRL("http://foo.com/blï¿½dï¿½.txt"));
		Assert.assertFalse("3 ///", UrlUtil.isIRL("http:///blï¿½dï¿½.txt"));
		Assert.assertFalse("blank is bad", UrlUtil.isIRL("file://a blï¿½dï¿½.txt"));
		Assert.assertTrue("blank %20 is good", UrlUtil.isIRL("file://a%20blï¿½dï¿½.txt"));
		Assert.assertTrue(UrlUtil.isIRL("file:C:/a/b.txt"));
		Assert.assertTrue("relative url", UrlUtil.isIRL("./3ï¿½.txt"));
		Assert.assertFalse("invalid char: @", UrlUtil.isIRL("http://@"));
		Assert.assertTrue(UrlUtil.isIRL("HTTP://ï¿½/ï¿½"));
		Assert.assertTrue(UrlUtil.isIRL("file:///C:/Documents%20and%20Settings/Israel/My%20Documents/Vio%20Production/Results/TIME_H8789/TIME_H8789.pdf"));
	}

	/**
	 * 
	 */
	@Test
	public void testIsURL() {
		Assert.assertFalse(UrlUtil.isURL(null));
		Assert.assertTrue(UrlUtil.isURL("file://bl.txt"));
		Assert.assertTrue(UrlUtil.isURL("http://foo.com/bl.txt"));
		Assert.assertFalse("3 ///", UrlUtil.isURL("http:///bl.txt"));
		Assert.assertFalse("blank is bad", UrlUtil.isURL("file://a b.txt"));
		Assert.assertTrue("blank %20 is good", UrlUtil.isURL("file://a%20bl.txt"));
		Assert.assertTrue(UrlUtil.isURL("file:C:/a/b.txt"));
		Assert.assertTrue("relative url", UrlUtil.isURL("./3.txt"));
		Assert.assertFalse("invalid char: @", UrlUtil.isURL("http://@"));
		Assert.assertFalse("UNC", UrlUtil.isURL("\\\\unc\\bar\\a.txt"));
		Assert.assertTrue(UrlUtil.isURL("HTTP://a/b?c"));
	}

	/**
	* 
	*/
	@Test
	public void testIsURLPerformance() {
		CPUTimer ct = new CPUTimer(false);
		for (int i = 0; i < 1234; i++) {
			ct.start();
			Assert.assertFalse(UrlUtil.isURL(null));
			Assert.assertTrue(UrlUtil.isURL("file://bl.txt"));
			Assert.assertTrue(UrlUtil.isURL("http://foo.com/bl.txt"));
			Assert.assertFalse("3 ///", UrlUtil.isURL("http:///bl.txt"));
			Assert.assertFalse("blank is bad", UrlUtil.isURL("file://a b.txt"));
			Assert.assertTrue("blank %20 is good", UrlUtil.isURL("file://a%20bl.txt"));
			Assert.assertTrue(UrlUtil.isURL("file:C:/a/b.txt"));
			Assert.assertTrue("relative url", UrlUtil.isURL("./3.txt"));
			Assert.assertFalse("invalid char: @", UrlUtil.isURL("http://@"));
			Assert.assertFalse("UNC", UrlUtil.isURL("\\\\unc\\bar\\a.txt"));
			Assert.assertTrue(UrlUtil.isURL("HTTP://a/b?c"));
			ct.stop();
		}
		System.out.println(ct.toString());
	}

	/**
	 * 
	 */
	@Test
	public void testIsXMLType() {
		Assert.assertTrue(UrlUtil.isXMLType("text/xml"));
		Assert.assertTrue(UrlUtil.isXMLType("TEXT/XML"));
		Assert.assertTrue(UrlUtil.isXMLType("TEXT/XML; "));
		Assert.assertTrue(UrlUtil.isXMLType("application/foobar+xml "));
		Assert.assertTrue(UrlUtil.isXMLType("application/xml"));
		Assert.assertTrue(UrlUtil.isXMLType("text/foobar+xml "));
		Assert.assertFalse(UrlUtil.isXMLType("foobar+xml "));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * @throws Exception if snafu
	 */
	@Test
	public void testStringToURL() throws Exception {
		if (File.separator.equals("\\")) {
			// test for an existing directory (a trailing slash is appended by
			// StringToURL)
			Assert.assertTrue(UrlUtil.stringToURL("c:\\temp\\").getPath().startsWith(new URL("File:/c:/temp").getPath()));
			Assert.assertTrue(UrlUtil.stringToURL("File:/c:/temp/").getPath().startsWith(new URL("File:/c:/temp").getPath()));
			Assert.assertTrue(UrlUtil.stringToURL("c:\\temp").getPath().startsWith(new URL("File:/c:/temp").getPath()));
			Assert.assertTrue(UrlUtil.stringToURL("File:/c:/temp").getPath().startsWith(new URL("File:///c:/temp").getPath()));
			Assert.assertTrue(UrlUtil.stringToURL("File:///c:/temp").getPath().startsWith(new URL("File:///c:/temp").getPath()));

			// test for a file or a non existing object (trailing slash is removed by StringToURL)
			Assert.assertEquals(UrlUtil.stringToURL("File:/c:/blöd €.pdf"), new URL(UrlUtil.fileToUrl(new File("c:/blöd €.pdf"), true)));
			Assert.assertEquals(UrlUtil.stringToURL("c:\\xyz\\").getPath(), new URL("File:/c:/xyz").getPath());
			Assert.assertEquals(UrlUtil.stringToURL("File:/c:/xyz/").getPath(), new URL("File:/c:/xyz").getPath());
			Assert.assertEquals(UrlUtil.stringToURL("c:\\xyz").getPath(), new URL("File:/c:/xyz").getPath());

			Assert.assertEquals(UrlUtil.stringToURL("file://foo/ .txt"), new URL("file://foo/%20.txt"));
			Assert.assertEquals(UrlUtil.stringToURL("file://foo/%.txt"), new URL("file://foo/%25.txt"));
		} else {
			Assert.assertEquals(UrlUtil.stringToURL("file:/foo/ .txt"), new URL("file:/foo/%20.txt"));
			Assert.assertEquals(UrlUtil.stringToURL("file:/foo/%.txt"), new URL("file:/foo/%25.txt"));
		}
		Assert.assertEquals(UrlUtil.stringToURL("\\\\a\\b c\\d.txt"), new URL("file://a/b%20c/d.txt"));
		Assert.assertEquals(UrlUtil.stringToURL("http://foo/%20.txt"), new URL("http://foo/%20.txt"));
		Assert.assertEquals(UrlUtil.stringToURL("http://foo"), new URL("http://foo"));
		Assert.assertNull("empty File: should be null", UrlUtil.stringToURL("File:"));
		Assert.assertEquals(UrlUtil.stringToURL("http%3A%2F%2FDRU-CIP4HD1%3A6331"), new URL("http://DRU-CIP4HD1:6331"));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testIsEscaped() {
		Assert.assertTrue(UrlUtil.isEscaped("http%3A%2F%2FDRU-CIP4HD1%3A6331"));
		Assert.assertFalse(UrlUtil.isEscaped("file:http%3A%2F%2FDRU-CIP4HD1%3A6331"));
		Assert.assertFalse(UrlUtil.isEscaped("text%20a.pdf"));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *  
	 * 
	 */
	@Test
	public void testGetBytesFromIP() {
		Assert.assertNull(UrlUtil.getBytesFromIP("foo"));
		Assert.assertNull(UrlUtil.getBytesFromIP("3.141"));
		Assert.assertNull(UrlUtil.getBytesFromIP("3.141.2.2222"));
		Assert.assertNotNull(UrlUtil.getBytesFromIP("127.0.0.1"));
	}

	/**
	 *  
	 * 
	 */
	@Test
	public void testGetIPFromBytes() {
		Assert.assertNull(UrlUtil.getIPFromBytes(null));
		Assert.assertNull(UrlUtil.getIPFromBytes(new byte[] { 1, 2 }));
		Assert.assertNull(UrlUtil.getIPFromBytes(new byte[] { 1, 2 }));
		Assert.assertNotNull(UrlUtil.getIPFromBytes(new byte[] { 1, 2, 4, 5 }));
		Assert.assertNull(UrlUtil.getIPFromBytes(new byte[] { 1, 2, 4, 5, 6 }));
		Assert.assertNotNull(UrlUtil.getIPFromBytes(new byte[] { 1, 2, 4, 5, 6, 7 }));
	}

	/**
	 * 
	 */
	@Test
	public void testFileToURL() {
		if (FileUtil.isWindows()) { // on windows
			final File f = new File("C:\\IO.SYS");
			String s = UrlUtil.fileToUrl(f, false);
			Assert.assertEquals(s, "file:///C:/IO.SYS");
			s = UrlUtil.fileToUrl(new File("\\\\fooBar\\4€.txt"), false);
			Assert.assertEquals(s, "file://fooBar/4€.txt");
		} else {
			String s = UrlUtil.fileToUrl(new File("/fooBar/4€.txt"), true);
			Assert.assertEquals(s, "file:/fooBar/4%e2%82%ac.txt");
			Assert.assertEquals(UrlUtil.fileToUrl(new File("/a/4%.txt"), false), "file:/a/4%25.txt");
		}
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * @throws Exception
	 */
	@Test
	public void testNonAsciiFileURL() throws Exception {
		for (int i = 0; i < 2; i++) // loop over escape and non-escape
		{
			final File f = new File("4€äöü.txt");
			final File f2 = FileUtil.getFileInDirectory(new File(sm_dirTestDataTemp), f);
			f2.delete();
			Assert.assertTrue(f2.createNewFile());
			Assert.assertTrue(f2.canRead());
			final String url = UrlUtil.fileToUrl(f2, i == 0);
			final XMLDoc doc = new XMLDoc("URL", null);
			final KElement root = doc.getRoot();
			root.setAttribute("url", url);
			doc.write2File(sm_dirTestDataTemp + "url.xml", 2, false);
			final JDFParser p = new JDFParser();
			p.bKElementOnly = true;
			final JDFDoc d = p.parseFile(sm_dirTestDataTemp + "url.xml");
			final KElement root2 = d.getRoot();
			final String urlParse = root2.getAttribute("url");
			Assert.assertEquals(url, urlParse);
			final File f3 = UrlUtil.urlToFile(urlParse);
			Assert.assertEquals(f2.getAbsolutePath(), f3.getAbsolutePath());
			Assert.assertTrue(f3.canRead());
		}

	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testURLToFileName() {
		Assert.assertEquals("b.c", UrlUtil.urlToFileName("a:b.c"));
		Assert.assertEquals("b.c", UrlUtil.urlToFileName("http:/b.c?gg"));
	}

	/**
	 * 
	 */
	@Test
	public void testUNCToUrl() {

		Assert.assertEquals("file://a/b/c.txt", UrlUtil.uncToUrl("\\\\a\\b\\c.txt", true));
		String unc = "\\\\a\\b\\c ä.txt";
		Assert.assertEquals("file://a/b/c%20%c3%a4.txt", UrlUtil.uncToUrl(unc, true));
		String unc2 = unc;
		System.out.println("ENCODING: " + System.getProperty("file.encoding"));
		System.out.println("ENCODING: " + Charset.defaultCharset());

		for (int i = 0; i < 10; i++) {
			System.out.println(unc);
			System.out.println("-----------");
			unc = UrlUtil.urlToUNC(UrlUtil.uncToUrl(unc, true));

		}
		Assert.assertEquals(unc, unc2);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testURLToUNC() throws Exception {
		Assert.assertEquals("a%5cb", UrlUtil.urlToUNC("file:a%5cb"));
		Assert.assertEquals("a%25", UrlUtil.urlToUNC("file:a%2525"));
		Assert.assertEquals("a b", UrlUtil.urlToUNC("file:a%20b"));
		Assert.assertEquals("a%2fb\\d", UrlUtil.urlToUNC("file:a%2fb/d"));
		Assert.assertEquals("\\\\host\\dir\\file", UrlUtil.urlToUNC("\\\\host\\dir\\file"));
		Assert.assertEquals("\\\\host\\dir\\file", UrlUtil.urlToUNC("//host/dir/file"));
		Assert.assertEquals("\\\\host\\dir\\file", UrlUtil.urlToUNC("file://host/dir/file"));
		Assert.assertEquals("\\\\host\\dir\\file", UrlUtil.urlToUNC("file:\\\\host\\dir\\file"));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testURLToFile() throws Exception {
		for (int i = 0; i < 2; i++) {
			File f = UrlUtil.urlToFile(".");
			Assert.assertTrue(f.isDirectory());
			File f2 = UrlUtil.urlToFile(UrlUtil.fileToUrl(f, i == 0));
			Assert.assertTrue(f2.isDirectory());
			Assert.assertEquals(f2.getCanonicalPath(), f.getCanonicalPath());

			f = new File(".\\simple.pdf");
			f2 = UrlUtil.urlToFile(UrlUtil.fileToUrl(f, i == 0));
			Assert.assertEquals("asccii", f.getCanonicalPath(), f2.getCanonicalPath());

			f = new File("blö½d .pdf");
			f2 = UrlUtil.urlToFile(UrlUtil.fileToUrl(f, i == 0));
			Assert.assertEquals("non asccii", f.getCanonicalPath(), f2.getCanonicalPath());

			f = new File("blöd .pdf");
			f2 = UrlUtil.urlToFile(UrlUtil.fileToUrl(f, i == 0));
			Assert.assertEquals("non asccii", f.getCanonicalPath(), f2.getCanonicalPath());

			f = new File("blöd ist es 10€.pdf");
			final String fileToUrl = UrlUtil.fileToUrl(f, i == 0);
			f2 = UrlUtil.urlToFile(fileToUrl);
			Assert.assertEquals("escape %20", f.getCanonicalPath(), f2.getCanonicalPath());

		}

		if (PlatformUtil.isWindows()) { // on windows
			final String uC = "file:///C:/Documents%20and%20Settings/All%20Users/Desktop/Prinect_Imposition_Editor/Examples/x.jdf";
			Assert.assertTrue(UrlUtil.urlToFile(uC).getPath().startsWith("C:" + File.separator + "Documents and Settings"));
			final File fi1 = new File("\\\\fooBar\\4€€.txt");
			final File fi = UrlUtil.urlToFile("file://fooBar/4€%e2%82%ac.txt");
			Assert.assertEquals("escape %20", fi.getCanonicalPath(), fi1.getCanonicalPath());
		}

	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testisRelativeURL() {
		Assert.assertFalse(UrlUtil.isRelativeURL("\\\\foo\\bar"));
		Assert.assertTrue(UrlUtil.isRelativeURL("c/d/e.f"));
		Assert.assertFalse(UrlUtil.isRelativeURL("/c/d/e.f"));
		Assert.assertFalse(UrlUtil.isRelativeURL("http://c/d/e.f"));
		Assert.assertFalse(UrlUtil.isRelativeURL("http://c:8080/c/d/e.f"));
		Assert.assertFalse(UrlUtil.isRelativeURL("c:\\foo"));
		Assert.assertFalse(UrlUtil.isRelativeURL("cid:c/d/e.f"));
	}

	/**
	 * 
	 */
	@Test
	public void testisUNC() {
		Assert.assertTrue(UrlUtil.isUNC("\\\\foo\\bar"));
		Assert.assertFalse(UrlUtil.isUNC("c/d/e.f"));
		Assert.assertFalse(UrlUtil.isUNC("/c/d/e.f"));
	}

	/**
	 * 
	 */
	@Test
	public void testGetRelativeURI() {
		if (File.separator.equals("\\")) { // on windows
			File f = new File("./a b");
			Assert.assertEquals(StringUtil.replaceChar(UrlUtil.getRelativeURL(f, null, true), '\\', "/", 0), "./a%20b");
			f = new File("../a.ß");
			Assert.assertEquals("escaped utf8", StringUtil.replaceChar(UrlUtil.getRelativeURL(f, null, true), '\\', "/", 0), "../a.%c3%9f");
			Assert.assertEquals("unescaped but utf8", StringUtil.replaceChar(UrlUtil.getRelativeURL(f, null, false), '\\', "/", 0), new String(StringUtil.setUTF8String("../a.ß")));

		}
	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testGetRelativeURL() {
		if (File.separator.equals("\\")) { // on windows
			File file = new File("c:\\a\\b\\c.txt");
			final File cwd = new File("c:\\a\\b1");
			Assert.assertEquals(UrlUtil.getRelativeURL(file, cwd, true), "../b/c.txt");
			file = new File("c:\\a\\b1\\c.txt");
			Assert.assertEquals(UrlUtil.getRelativeURL(file, cwd, true), "./c.txt");
			file = new File("a\\..\\b\\c.txt");
			Assert.assertEquals(UrlUtil.getRelativeURL(file, null, true), "./b/c.txt");
			file = cwd;
			Assert.assertEquals(UrlUtil.getRelativeURL(file, cwd, true), ".");
		}
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testGetURLWithDirectory() {
		String url = "File://a.b";
		Assert.assertEquals("test nulls", url, UrlUtil.getURLWithDirectory(null, url));
		Assert.assertEquals("test nulls", url, UrlUtil.getURLWithDirectory("", url));
		Assert.assertEquals("test nulls", url, UrlUtil.getURLWithDirectory("File:/dir", url));

		url = "a.b";
		Assert.assertEquals("relative url", "File://dir/a.b", UrlUtil.getURLWithDirectory("File://dir/", url));
		Assert.assertEquals("relative url", "File://dir/a.b", UrlUtil.getURLWithDirectory("File://dir", url));

		url = "/a.b";
		Assert.assertEquals("absolute url no host", "File://a.b", UrlUtil.getURLWithDirectory("File://host/", url));
		Assert.assertEquals("absolute url no host", "File://a.b", UrlUtil.getURLWithDirectory("File://host", url));

		url = "/a.b:c";
		Assert.assertEquals("absolute url no host - colon", "File://a.b:c", UrlUtil.getURLWithDirectory("File://host/", url));
		Assert.assertEquals("absolute url no host - colon", "File://a.b:c", UrlUtil.getURLWithDirectory("File://host", url));

		url = "//a.b";
		Assert.assertEquals("absolute url with default host", "File://a.b", UrlUtil.getURLWithDirectory("File://dir/", url));
		Assert.assertEquals("absolute url with default host", "File://a.b", UrlUtil.getURLWithDirectory("File://dir", url));

		url = "ftp://a.b";
		Assert.assertEquals("absolute url with default host", "ftp://a.b", UrlUtil.getURLWithDirectory("File://dir/", url));
		url = "http://a.b";
		Assert.assertEquals("absolute url with default host", "http://a.b", UrlUtil.getURLWithDirectory("File://dir/", url));

		url = "//boo/a.b";
		Assert.assertEquals("absolute url with new host", "File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir/", url));
		Assert.assertEquals("absolute url with new host", "File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir", url));

		url = "//boo/./gg/../a.b";
		Assert.assertEquals("absolute url with new host and cleandots", "File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir/", url));
		Assert.assertEquals("absolute url with new host and cleandots", "File://boo/a.b", UrlUtil.getURLWithDirectory("File://dir", url));

		Assert.assertEquals("http url", "http://dir/a.b", UrlUtil.getURLWithDirectory("http://dir", "a.b"));
	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testRemoveExtension() {
		Assert.assertEquals(UrlUtil.prefix("a.b"), "a");
		Assert.assertEquals(UrlUtil.prefix("a"), "a");
		Assert.assertEquals(UrlUtil.prefix("a."), "a");
		Assert.assertEquals(UrlUtil.prefix("a.b.c"), "a.b");
		Assert.assertEquals(UrlUtil.prefix("."), "");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testCleanDots() {
		Assert.assertEquals(UrlUtil.cleanDots("."), ".");
		Assert.assertEquals(UrlUtil.cleanDots(".."), "..");
		Assert.assertEquals(UrlUtil.cleanDots("a/.."), ".");
		Assert.assertEquals(UrlUtil.cleanDots("../../c.pdf"), "../../c.pdf");
		Assert.assertEquals(UrlUtil.cleanDots(".././../c.pdf"), "../../c.pdf");
		Assert.assertEquals(UrlUtil.cleanDots("File://a/../b"), "File://b");
		Assert.assertEquals(UrlUtil.cleanDots("File://a/.././c/../b.pdf"), "File://b.pdf");
		Assert.assertEquals(UrlUtil.cleanDots("/."), "/.");
	}

	/**
	 * 
	 * 
	 */
	@Test
	public void testCreateHttpUrl() {
		Assert.assertEquals(UrlUtil.createHttpUrl(false, "d", 0, "a"), "http://d/a");
		Assert.assertEquals(UrlUtil.createHttpUrl(false, "d", 8080, "a"), "http://d:8080/a");
		Assert.assertEquals(UrlUtil.createHttpUrl(true, "d", 8080, "a"), "https://d:8080/a");
	}

	/**
	 * @throws Exception
	 * 
	 */
	@Test
	public void testMoveToDir() throws Exception {
		new MimeUtilTest().testBuildMimePackageDoc();
		final Multipart mp = MimeUtil.getMultiPart(sm_dirTestDataTemp + "testMimePackageDoc.mjm");
		final BodyPart bp = MimeUtil.getPartByCID(mp, "jdf.JDF");
		final JDFDoc d = MimeUtil.getJDFDoc(bp);
		final JDFNode n = d.getJDFRoot();
		final JDFColorSpaceConversionParams cscp = (JDFColorSpaceConversionParams) n.getMatchingResource(ElementName.COLORSPACECONVERSIONPARAMS, null, null, 0);
		Assert.assertNotNull(cscp);
		final JDFFileSpec fs = cscp.getFinalTargetDevice();
		final File newDir = new File(sm_dirTestDataTemp + "newDir");
		final File f = UrlUtil.moveToDir(fs, newDir, null, true);
		Assert.assertNotNull("error moving file to dir", f);
		for (int i = 0; i < 10; i++) {
			ThreadUtil.sleep(1000);
			if (fs.getURL().contains(UrlUtil.fileToUrl(newDir, false))) {
				break;
			}
			System.out.println("Waiting " + i);
		}
		long l = f.lastModified();
		final File f2 = UrlUtil.moveToDir(fs, newDir, null, false);
		Assert.assertNotNull("error moving file to dir", f2);
		ThreadUtil.sleep(1000);
		Assert.assertEquals(l, f2.lastModified(), 0);
		fs.setURL("bad:/blöd");
		Assert.assertNull("bad url:", UrlUtil.moveToDir(fs, newDir, null, true));
		fs.setURL("http://really_really_not_there.com/isnt/there?aaa");
		Assert.assertNull("bad url:", UrlUtil.moveToDir(fs, newDir, null, true));
		fs.setURL("./blub.pdf");
		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "dummy/blub.pdf"));
		Assert.assertTrue("relative:", UrlUtil.moveToDir(fs, newDir, sm_dirTestDataTemp + "dummy", true).exists());
		fs.setURL("deep/blub.pdf");
		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "dummy/deep/blub.pdf"));
		Assert.assertTrue("relative:", UrlUtil.moveToDir(fs, newDir, sm_dirTestDataTemp + "dummy", true).exists());

	}

	/**
	 * 
	 */
	@Test
	public void testNormalize() {
		Assert.assertEquals(UrlUtil.normalize("a.b"), "a.b");
		Assert.assertEquals(UrlUtil.normalize("./a.b"), "a.b");
		Assert.assertEquals(UrlUtil.normalize("././a.b"), "a.b");
		Assert.assertEquals(UrlUtil.normalize("http://a/a.b"), "http://a/a.b");
		Assert.assertEquals(UrlUtil.normalize("http://a/a%20.b"), "http://a/a%20.b");
		Assert.assertEquals(UrlUtil.normalize("HTTP://a/a.b"), "http://a/a.b");
		Assert.assertEquals(UrlUtil.normalize("cid:a.b"), "cid:a.b");
	}

	/**
	 * 
	 */
	@Test
	public void testUnEscape() {
		Assert.assertEquals(UrlUtil.unEscape("a.b"), "a.b");
		Assert.assertEquals(UrlUtil.unEscape("aaa%"), "aaa%");
		Assert.assertEquals(UrlUtil.unEscape("%2"), "%2");
		Assert.assertEquals(UrlUtil.unEscape("%20"), " ");
	}

	/**
	 * 
	 */
	@Test
	public void testNewExtension() {
		Assert.assertEquals(UrlUtil.newExtension("a.b", "c"), "a.c");
		Assert.assertEquals(UrlUtil.newExtension("a.b.c", "c"), "a.b.c");
		Assert.assertEquals(UrlUtil.newExtension("a.b", null), "a");
		Assert.assertEquals(UrlUtil.newExtension("a.b", ".c"), "a.c");
		Assert.assertEquals(UrlUtil.newExtension("a.b", ".c.d"), "a.c.d");
		Assert.assertEquals(UrlUtil.newExtension("a.b", "c.d"), "a.c.d");
		Assert.assertEquals(UrlUtil.newExtension("a.b.bb", "c.d"), "a.b.c.d");
		Assert.assertEquals(UrlUtil.newExtension(".b", ".c"), ".c");
		Assert.assertEquals(UrlUtil.newExtension("a", ".c"), "a.c");
	}
}
