/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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
package org.cip4.jdflib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Base64;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 * @date Dec 3, 2010
 */
class UrlPartTest extends JDFTestCaseBase
{

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testToString() throws IOException
	{
		final UrlPart p = new UrlPart(new File(sm_dirTestData + "29694232.ptk"));
		assertNotNull(p);
		assertTrue(p.toString().contains("<not buffered>"));
		p.buffer();
		assertFalse(p.toString().contains("<not buffered>"));
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testRespString() throws IOException
	{
		final UrlPart p = new UrlPart(new File(sm_dirTestData + "29694232.ptk"));
		final String responseString = p.getResponseString(142);
		assertFalse(responseString.contains("<not buffered>"));
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testgetHeaders() throws IOException
	{
		final UrlPart p = new UrlPart(new File(sm_dirTestData + "29694232.ptk"));
		assertTrue(p.getHeaders().isEmpty());
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testgetHeadersConnect() throws IOException
	{
		final HttpURLConnection c = mock(HttpURLConnection.class);
		final ListMap<String, String> map = new ListMap<String, String>();
		map.setUnique(false);
		when(c.getHeaderFields()).thenReturn(map);
		final UrlPart p = new UrlPart(c);
		assertTrue(p.getHeaders().isEmpty());
		map.putOne("a", "b");
		assertEquals("b", p.getHeaders().get("a"));
		map.putOne("a", "b");
		assertEquals("b b", p.getHeaders().get("a"));
		assertEquals("b b", p.getHeader("a"));
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testgetAuthentication() throws IOException
	{
		final HttpURLConnection c = mock(HttpURLConnection.class);
		final ListMap<String, String> map = new ListMap<String, String>();
		map.setUnique(false);
		when(c.getHeaderFields()).thenReturn(map);
		final UrlPart p = new UrlPart(c);
		assertTrue(p.getHeaders().isEmpty());
		String val = "Basic a:b:b";
		val = Base64.getEncoder().encodeToString(val.getBytes());
		map.putOne(UrlUtil.AUTHORIZATION, val);
		assertEquals("a", p.getAuthorizationUser());
		assertEquals("b:b", p.getAuthorizationPassword());
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testgetAuthenticationBad() throws IOException
	{
		final HttpURLConnection c = mock(HttpURLConnection.class);
		final ListMap<String, String> map = new ListMap<String, String>();
		map.setUnique(false);
		when(c.getHeaderFields()).thenReturn(map);
		final UrlPart p = new UrlPart(c);
		assertTrue(p.getHeaders().isEmpty());
		final String val = "Basic a:b:b";
		// not base 64
		map.putOne(UrlUtil.AUTHORIZATION, val);
		assertEquals(null, p.getAuthorizationUser());
		assertEquals(null, p.getAuthorizationPassword());
	}

	/**
	 *
	 */
	@Test
	void testGetXMLDoc()
	{
		if (!isTestNetwork())
			return;
		final UrlPart writeToURL = UrlUtil.writeToURL("http://www.example.com", null, UrlUtil.GET, UrlUtil.TEXT_PLAIN, null);
		assertNotNull(writeToURL);
		assertNull(writeToURL.getXMLDoc());
	}

	/**
	 *
	 */
	@Test
	void testBuffer()
	{
		if (!isTestNetwork())
			return;
		final UrlPart writeToURL = UrlUtil.writeToURL("http://www.example.com", null, UrlUtil.GET, UrlUtil.TEXT_PLAIN, null);
		assertNotNull(writeToURL);
		writeToURL.buffer();
		final ByteArrayIOStream byteArrayIOStream = new ByteArrayIOStream(writeToURL.getResponseStream());
		byteArrayIOStream.close();
	}

	/**
	 *
	 */
	@Test
	void testWriteStream()
	{
		if (!isTestNetwork())
			return;
		final UrlPart writeToURL = UrlUtil.writeToURL("http://www.example.com", null, UrlUtil.GET, UrlUtil.TEXT_PLAIN, null);
		assertNotNull(writeToURL);
		final File file = new File(sm_dirTestDataTemp + "s1.html");
		FileUtil.writeFile(writeToURL, file);
		assertTrue(file.exists());
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	void testFile() throws Exception
	{
		final UrlPart p = new UrlPart(new File("Test.xml"));
		assertEquals(p.getContentType(), UrlUtil.TEXT_XML);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	void testRCOK() throws Exception
	{
		assertFalse(UrlPart.isReturnCodeOK(null));
		assertFalse(UrlPart.isReturnCodeOK(new UrlPart(new File("certainly not there"))));
		if (!isTestNetwork())
			return;
		final UrlPart writeToURL = UrlUtil.writeToURL("http://www.example.com", null, UrlUtil.GET, UrlUtil.TEXT_PLAIN, null);
		assertTrue(UrlPart.isReturnCodeOK(writeToURL));
	}

}
