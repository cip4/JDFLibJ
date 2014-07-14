/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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
 *  
 */
/*
 * Created on Aug 26, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.cip4.jdflib.util.net;

import java.io.InputStream;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlPart;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 12.01.2009
 */
public class ProxyUtilTest extends JDFTestCaseBase
{
	/**
	 * @throws Exception is snafu
	 */
	@Test
	public void testSetProxyString() throws Exception
	{
		if (!isTestNetwork())
		{
			log.info("skipping network test");
			return;
		}
		ProxyUtil.setProxy(null);
		String proxyURL = "http://proxy:8080";
		ProxyUtil.setProxy(proxyURL);
		UrlPart p = null;
		p = UrlUtil.writeToURL("http://www.google.de", null, UrlUtil.GET, null, null);
		Assert.assertNotNull(p);
		ProxyUtil.setProxy(null);
		p = UrlUtil.writeToURL("http://www.google.de", null, UrlUtil.GET, null, null);
		Assert.assertNull(p);
	}

	/**
	 * @throws Exception if snafu
	 */
	@Test
	public void testSetProxy() throws Exception
	{
		try
		{
			ProxyUtil.setProxy(null, 0, null, null);
			ProxyUtil.setProxy("dummy", 0, null, null);
			ProxyUtil.setProxy("dummy", 880, "a", null);
			ProxyUtil.setProxy("dummy", 80, "a", "b");
		}
		catch (Exception x)
		{
			Assert.fail(x.toString());
		}
	}

	/**
	 * @throws Exception if snafu
	 */
	@Test
	public void testSetProxyWrite() throws Exception
	{
		if (!isTestNetwork())
		{
			log.info("skipping network test");
			return;
		}
		String proxy = "proxy.ceu.corp.heidelberg.com";
		int proxyPort = 8080;
		//UrlUtil.writeToURL("http://" + proxy + ":" + proxyPort, null, UrlUtil.GET, null, null);
		UrlPart p = UrlUtil.writeToURL("http://localhost:8080/httpdump", null, UrlUtil.GET, null, null);
		if (p == null) // we are in the environment where the proxy is correctly set up
		{
			log.warn("no connection to proxy or no tomcat running!");
		}
		else
		{
			p = UrlUtil.writeToURL("http://localhost:8080/httpdump", null, UrlUtil.GET, null, null);
			ByteArrayIOStream ios = new ByteArrayIOStream(p.getResponseStream());
			Assert.assertEquals(p.getResponseCode(), 200, 0);
			Assert.assertNotNull(ios);
			ProxyUtil.setProxy(proxy, proxyPort, null, null);
			p = UrlUtil.writeToURL("http://localhost:8080/httpdump", null, UrlUtil.GET, null, null);
			ios = new ByteArrayIOStream(p.getResponseStream());
			Assert.assertEquals(p.getResponseCode(), 200, 0);
			p = UrlUtil.writeToURL("http://www.google.de:80", null, UrlUtil.GET, null, null);
			Assert.assertEquals(p.getResponseCode(), 200, 0);
			InputStream responseStream = p.getResponseStream();
			Assert.assertNotNull(responseStream);
			ios = new ByteArrayIOStream(p.getResponseStream());
			Assert.assertEquals(StringUtil.token(p.getContentType(), 0, ";"), UrlUtil.TEXT_HTML);

			ProxyUtil.setProxy(null, 0, null, null);
			p = UrlUtil.writeToURL("http://www.google.de", null, UrlUtil.GET, null, null);
			Assert.assertNull(p);
		}
	}

	/**
	 * 
	 */
	@Test
	public void testWriteToURL()
	{
		if (!isTestNetwork())
		{
			log.info("skipping network test");
			return;
		}
		ProxyUtil.setProxy("http://proxy", 8080, null, null);
		Assert.assertNotNull(UrlUtil.writeToURL("http://www.example.com", null, UrlUtil.GET, UrlUtil.TEXT_PLAIN, null));
	}

	/**
	 * 
	 */
	@Test
	public void testWriteToURLSystemCall()
	{
		if (!isTestNetwork())
		{
			log.info("skipping network test");
			return;
		}
		System.setProperty("http.proxyPort", "8080");
		System.setProperty("http.proxyHost", "proxy.ceu.corp.heidelberg.com");
		System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1");
		Assert.assertNotNull(UrlUtil.writeToURL("http://www.example.com", null, UrlUtil.GET, UrlUtil.TEXT_PLAIN, null));
	}

}
