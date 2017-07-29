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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.UrlPart;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 * 12.01.2009
 */
public class ProxyUtilTest extends JDFTestCaseBase
{
	ProxySelector defaultSel;

	/**
	 * @throws Exception if snafu
	 */
	public void testSetProxyString() throws Exception
	{
		if (isTestNetwork())
		{
			ProxyUtil.setProxy(null);
			String proxyURL = "http://proxy:8082";
			UrlPart p = null;
			int i = 0;
			p = UrlUtil.writeToURL("http://www.google.de", null, UrlUtil.GET, null, null);
			if (p != null)
				i++;
			ProxyUtil.setProxy(proxyURL);
			p = UrlUtil.writeToURL("http://www.google.de", null, UrlUtil.GET, null, null);
			if (p != null)
				i++;
			ProxyUtil.setProxy(null);
			p = UrlUtil.writeToURL("http://www.google.de", null, UrlUtil.GET, null, null);
			if (p != null)
				i++;
			assertTrue(i > 0);
		}
	}

	/**
	 * @throws Exception if snafu
	 */
	@Test
	public void testSetProxy() throws Exception
	{
		if (isTestNetwork())
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
				fail(x.toString());
			}
		}
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	public void testSystemProxy() throws Exception
	{
		if (isTestNetwork())
		{
			ProxyUtil.setUseSystemDefault(true);
			String[] v = new String[] { "http://www.yahoo.com", "https://www.google.com", "http://localhost" };
			for (String s : v)
			{
				List<Proxy> l = ProxySelector.getDefault().select(new URI(s));

				for (Proxy proxy : l)
				{
					log.info("proxy type : " + s + " " + proxy.type());
					InetSocketAddress addr = (InetSocketAddress) proxy.address();
					if (addr == null)
					{
						if (s.contains("localhost"))
						{
							log.info("No Proxy for " + s);
						}
						else
						{
							log.warn("No Proxy for external host " + s);
						}
					}
					else
					{
						log.info("proxy hostname : " + addr.getHostName());
						log.info("proxy port : " + addr.getPort());
					}
				}
			}
		}
	}

	/**
	 *
	 */
	@Test
	public void testWriteToURL()
	{
		if (!isTestNetwork())
			return;
		ProxyUtil.setUseSystemDefault(true);
		assertNotNull(UrlUtil.writeToURL("http://www.example.com", null, UrlUtil.GET, UrlUtil.TEXT_PLAIN, null));
	}

	/**
	 *
	 */
	@Test
	public void testWriteToURLSecure()
	{
		if (!isTestNetwork())
			return;
		ProxyUtil.setUseSystemDefault(true);
		UrlPart part = UrlUtil.writeToURL("https://www.google.com", null, UrlUtil.GET, UrlUtil.TEXT_PLAIN, null);
		assertNotNull(part);
		part.buffer();
	}

	@Override
	@Before
	protected void setUp() throws Exception
	{
		defaultSel = ProxySelector.getDefault();
		super.setUp();
	}

	@Override
	@After
	protected void tearDown() throws Exception
	{
		super.tearDown();
		ProxySelector.setDefault(defaultSel);
	}

}
