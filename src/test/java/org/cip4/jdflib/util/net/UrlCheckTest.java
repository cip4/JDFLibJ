/**
 * The CIP4 Software License, Version 1.0
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
 */
package org.cip4.jdflib.util.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.ThreadUtil;
import org.cip4.jdflib.util.UrlPart;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 * @date Nov 16, 2012
 */
public class UrlCheckTest extends JDFTestCaseBase
{

	@BeforeEach
	void beforeEach()
	{
		ProxyUtil.setUseSystemDefault(true);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testPing()
	{
		if (!isTestNetwork())
			return;
		UrlPart ping = null;
		for (int i = 0; i < 3; i++)
		{
			ping = new UrlCheck("https://www.google.com").ping(9999);
			if (ping != null)
				break;
		}

		assertNotNull(ping, debugConnection());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testHTTPDetails()
	{
		if (!isTestNetwork())
			return;
		UrlPart ping = null;
		final HTTPDetails det = new HTTPDetails();
		for (int i = 0; i < 3; i++)
		{
			final UrlCheck urlCheck = new UrlCheck("https://www.google.com");
			urlCheck.setHTTPDetails(det);
			ping = urlCheck.ping(9999);
			if (ping != null)
				break;
		}

		assertNotNull(ping, debugConnection());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testHTTPDetailsString()
	{
		final HTTPDetails det = new HTTPDetails();
		final UrlCheck urlCheck = new UrlCheck("https://www.google.com");
		urlCheck.setHTTPDetails(det);
		assertNotNull(urlCheck.toString(), debugConnection());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testBuffer()
	{
		if (!isTestNetwork())
			return;
		final UrlCheck urlCheck = new UrlCheck("https://www.google.com");
		urlCheck.setBuffer(true);
		final UrlPart ping = urlCheck.ping(5555);
		assertNotNull(ping.getResponseStream(), debugConnection());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testPingRC()
	{
		if (!isTestNetwork())
			return;
		assertEquals(200, new UrlCheck("https://www.google.com").pingRC(8888), debugConnection());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testPingRCGet()
	{
		if (!isTestNetwork())
			return;
		assertEquals(200, new UrlCheck("https://www.google.com", UrlUtil.GET).pingRC(5555), debugConnection());
	}

	/**
	 *
	 *
	 */
	@SuppressWarnings("resource")
	@Test
	public void testPingRCPost()
	{
		if (!isTestNetwork())
			return;
		final UrlCheck urlCheck = new UrlCheck("https://www.google.com", UrlUtil.POST);
		urlCheck.setStream(new ByteArrayIOStream("test".getBytes()).getInputStream());
		assertTrue(urlCheck.pingRC(8888) > 200, "Google does not accept post... ");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetPingRC()
	{
		if (!isTestNetwork())
			return;
		final UrlCheck urlCheck = new UrlCheck("https://www.google.com");
		urlCheck.startPing(5555);
		ThreadUtil.sleep(111);
		assertEquals(200, urlCheck.getPingRC(), debugConnection());
	}

	public String debugConnection()
	{
		final String url = "https://ifconfig.me/";
		try (InputStream in = new URL(url).openStream())
		{
			return "Requesting from IP: " + IOUtils.toString(in, Charset.defaultCharset());
		}
		catch (IOException e)
		{
			log.warn("nasty net exception - ignore", e);
			return "bad connection";
		}

	}
}
