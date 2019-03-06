/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.cip4.jdflib.util.UrlUtil;
import org.junit.Test;

public class HTTPDetailsTest
{

	private final class TestConnection extends HttpURLConnection
	{
		private TestConnection(final URL arg0)
		{
			super(arg0);
		}

		@Override
		public void connect() throws IOException
		{
			// TODO Auto-generated method stub

		}

		@Override
		public boolean usingProxy()
		{
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void disconnect()
		{
			// TODO Auto-generated method stub

		}
	}

	/**
	 *
	 */
	@Test
	public void testRedirect()
	{
		final HTTPDetails redirect = HTTPDetails.getRedirect(null);
		assertNotNull(redirect);
		assertEquals(1, redirect.getRedirect());
		assertEquals(2, HTTPDetails.getRedirect(redirect).getRedirect());
	}

	/**
	 *
	 */
	@Test
	public void testTimeOut()
	{
		final HTTPDetails redirect = new HTTPDetails();
		assertEquals(UrlUtil.getConnectionTimeout(), redirect.getConnectionTimeout());
		redirect.setConnectionTimeout(122);
		assertEquals(122, redirect.getConnectionTimeout());
	}

	/**
	 * @throws MalformedURLException
	 *
	 */
	@Test
	public void testBearer() throws MalformedURLException
	{
		final HttpURLConnection uc = new TestConnection(new URL("http://foo.com"));
		final HTTPDetails d = new HTTPDetails();
		d.setBearerToken("abc");
		d.applyTo(uc);
		assertEquals("Bearer abc", uc.getRequestProperty(UrlUtil.AUTHORIZATION));
	}

	/**
	 * @throws MalformedURLException
	 *
	 */
	@Test
	public void testSetHeader() throws MalformedURLException
	{
		final HttpURLConnection uc = new TestConnection(new URL("http://foo.com"));
		final HTTPDetails d = new HTTPDetails();
		d.setHeader("abc", "def");
		d.applyTo(uc);
		assertEquals("def", uc.getRequestProperty("abc"));
	}

}
