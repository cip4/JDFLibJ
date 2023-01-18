/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.util.net;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;

/**
 * internal proxy handler
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class ProxyUtil
{
	private static final String USE_SYSTEM_PROXIES = "java.net.useSystemProxies";

	/**
	 * get the uri of the host with protocol and port for proxy selection
	 *
	 * @param url
	 * @return
	 */
	public static URI getHostURI(final URL url)
	{
		if (url == null)
			return null;
		URI uri;
		try
		{
			final int port = url.getPort();
			String str = url.getProtocol() + "://" + url.getHost();
			if (port > 0)
			{
				str += ":" + port;
			}
			uri = new URI(str);
		}
		catch (final Exception x)
		{
			uri = null;
		}
		return uri;
	}

	/**
	 *
	 * @param use, if true use the system proxy properties
	 */
	public static void setUseSystemDefault(final boolean use)
	{
		System.setProperty(USE_SYSTEM_PROXIES, "" + use);
	}

	/**
	 *
	 * @param use, the default if not set
	 * @return if true use the system proxy properties
	 */
	public static boolean isUseSystemDefault(final boolean use)
	{
		final String def = System.getProperty(USE_SYSTEM_PROXIES);
		return StringUtil.parseBoolean(def, use);
	}

	/**
	 * same as ProxySelector.getDefault() but always ensure that local is first in the list
	 *
	 * @param uri
	 * @return
	 */
	public static List<Proxy> getProxiesWithLocal(final URI uri)
	{
		final ProxySelector selector = ProxySelector.getDefault();
		final List<Proxy> list = selector.select(uri);
		// make sure local is first in list - this is certainly faster
		if (!list.contains(Proxy.NO_PROXY))
		{
			final List<Proxy> list2 = new ArrayList<>();
			list2.add(Proxy.NO_PROXY);
			list2.addAll(list);
			return list2;
		}
		else
		{
			return list;
		}
	}

	/**
	 * setup the environment for proxies
	 *
	 * @param proxy the proxy url - NO PORT!
	 * @param port the proxy port
	 * @param user the user for authentication, if null no authentication is attempted
	 * @param pw the pass word for authentication
	 */
	public static void setProxy(String proxy, final int port, final String user, final String pw)
	{
		proxy = UrlUtil.removeProtocol(proxy);
		if (StringUtil.getNonEmpty(proxy) == null)
		{
			final URLProxySelector ups = new URLProxySelector(null);
			ProxySelector.setDefault(ups);
		}
		else
		{
			final Proxy p = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(StringUtil.token(proxy, 0, ":"), port));
			final URLProxySelector ups = new URLProxySelector(p);
			ProxySelector.setDefault(ups);
			if (user != null)
			{
				Authenticator.setDefault(new URLAuthenticator(user, pw));
			}
		}
	}

	/**
	 *
	 * @param proxy
	 */
	public static void setProxy(String proxy)
	{
		if (StringUtil.getNonEmpty(proxy) == null)
		{
			setProxy(null, 0, null, null);
		}
		else
		{
			proxy = UrlUtil.removeProtocol(proxy);
			final int port = StringUtil.parseInt(StringUtil.token(StringUtil.token(proxy, 1, ":"), 0, "/"), -1);
			proxy = StringUtil.token(proxy, 0, ":");
			setProxy(proxy, port, null, null);
		}
	}

	public static Proxy getProxy(String url)
	{
		try
		{
			final ProxySelector selector = ProxySelector.getDefault();
			final List<Proxy> list = selector.select(new URI(url));
			return ContainerUtil.get(list, 0);
		}
		catch (final Exception e)
		{
			return null;
		}
	}
}
