/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-20223 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class URLWriterTest extends JDFTestCaseBase
{
	/**
	 * @throws MalformedURLException
	 *
	 */
	@Test
	public void testWriteToURL() throws MalformedURLException
	{
		if (!isTestNetwork())
			return;
		assertNotNull(new URLWriter(null, new URL("http://www.example.com"), UrlUtil.GET, UrlUtil.TEXT_PLAIN, null).writeToURL());
	}

	/**
	 * @throws MalformedURLException
	 *
	 */
	@Test
	public void testWriteToURLBad() throws MalformedURLException
	{
		if (!isTestNetwork())
			return;
		URLWriter urlWriter = new URLWriter(null, new URL("http://nosuchhost123456789"), UrlUtil.GET, UrlUtil.TEXT_PLAIN, null);
		assertNull(urlWriter.writeToURL());
		assertNotNull(urlWriter.getLastException());
	}

	/**
	 * @throws MalformedURLException
	 *
	 */
	@Test
	public void testgetAddDirect() throws MalformedURLException
	{
		URLWriter urlWriter = new URLWriter(null, new URL("http://www.example.com"), UrlUtil.GET, UrlUtil.TEXT_PLAIN, null);
		assertTrue(urlWriter.isAddDirect());
		urlWriter.setAddDirect(false);
		assertFalse(urlWriter.isAddDirect());
	}

	/**
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 *
	 */
	@Test
	public void testgetProxyList() throws MalformedURLException, URISyntaxException
	{
		URLWriter urlWriter = new URLWriter(null, new URL("http://www.example.com"), UrlUtil.GET, UrlUtil.TEXT_PLAIN, null);
		assertTrue(urlWriter.getProxies(new URI("http://www.example.com")).contains(Proxy.NO_PROXY));
		urlWriter.setAddDirect(false);
		assertFalse(urlWriter.getProxies(new URI("http://www.example.com")).isEmpty());
	}

	/**
	 * @throws MalformedURLException
	 *
	 */
	@Test
	public void testTostringt() throws MalformedURLException
	{
		URLWriter urlWriter = new URLWriter(null, new URL("http://www.example.com"), UrlUtil.GET, UrlUtil.TEXT_PLAIN, null);
		assertNotNull(urlWriter.toString());
	}

	/**
	 * @throws MalformedURLException
	 *
	 */
	@Test
	public void testWriteToURLPost() throws MalformedURLException
	{
		if (!isTestNetwork())
			return;
		final UrlPart writeToURL = new URLWriter(null, new URL("http://www.google.com"), UrlUtil.POST, UrlUtil.TEXT_PLAIN, null).writeToURL();
		assertNotNull(writeToURL);
		writeToURL.buffer();
	}

	/**
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ProtocolException
	 *
	 */
	@Test
	public void testWriteToURLPatch() throws MalformedURLException
	{
		if (!isTestNetwork())
			return;
		URLWriter w = new URLWriter(null, new URL("http://www.example.com"), UrlUtil.PATCH, UrlUtil.TEXT_PLAIN, null);
		final UrlPart writeToURL = w.writeToURL();
		assertNotNull(writeToURL);
		writeToURL.buffer();
	}

	/**
	 * @throws IOException
	 * @throws ProtocolException
	 *
	 */
	@Test
	public void testWriteToURLPatch2() throws ProtocolException, IOException
	{
		URLWriter w = new URLWriter(null, new URL("http://www.example.com"), UrlUtil.PATCH, UrlUtil.TEXT_PLAIN, null);

		HttpURLConnection c = Mockito.mock(HttpURLConnection.class);
		Mockito.doThrow(ProtocolException.class).when(c).setRequestMethod(UrlUtil.PATCH);
		w.setHttpMethod(c);

	}

	/**
	 * @throws MalformedURLException
	 *
	 */
	@Test
	public void testWriteToURLBadMethod() throws MalformedURLException
	{
		if (!isTestNetwork())
			return;

		URLWriter w = new URLWriter(null, new URL("http://www.example.com"), "bad", UrlUtil.TEXT_PLAIN, null);
		final UrlPart writeToURL = w.writeToURL();
		assertNull(writeToURL);
	}

}
