/**
 * 
 */
package org.cip4.jdflib.util.net;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URL;

import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class ProxyUtil
{
	/**
	 * get the uri of the host with protocol and port for proxy selection
	 * @param url
	 * @return
	 */
	public static URI getHostURI(URL url)
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
		catch (Exception x)
		{
			uri = null;
		}
		return uri;
	}

	/**
	 * setup the environment for proxies
	 * 
	 * @param proxy the proxy url - NO PORT!
	 * @param port the proxy port
	 * @param user the user for authentication, if null no authentication is attempted
	 * @param pw the pass word for authentication
	 */
	public static void setProxy(String proxy, int port, String user, String pw)
	{
		if (proxy == null)
		{
			URLProxySelector ups = new URLProxySelector(null);
			ProxySelector.setDefault(ups);
		}
		else
		{
			proxy = UrlUtil.removeProtocol(proxy);

			Proxy p = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(StringUtil.token(proxy, 0, ":"), port));
			URLProxySelector ups = new URLProxySelector(p);
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
			int port = StringUtil.parseInt(StringUtil.token(StringUtil.token(proxy, 1, ":"), 0, "/"), -1);
			proxy = StringUtil.token(proxy, 0, ":");
			setProxy(proxy, port, null, null);
		}
	}
}
