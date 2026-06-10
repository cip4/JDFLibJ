/**
 *
 */
package org.cip4.jdflib.util.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Vector;

class URLProxySelector extends ProxySelector
{

	/**
	 * @param p a proxy to add
	 */
	protected URLProxySelector(final Proxy p)
	{
		super();
		myproxies = new Vector<>();
		if (p != null)
		{
			myproxies.add(p);
			noproxies = new Vector<>();
			noproxies.add(Proxy.NO_PROXY);
		}
		else
		{
			noproxies = myproxies;
		}
		myproxies.add(Proxy.NO_PROXY);
	}

	private final Vector<Proxy> myproxies;
	private Vector<Proxy> noproxies;

	/**
	 * @see java.net.ProxySelector#connectFailed(java.net.URI, java.net.SocketAddress, java.io.IOException)
	 * @param uri
	 * @param sa
	 * @param ioe
	 */
	@Override
	public void connectFailed(final URI uri, final SocketAddress sa, final IOException ioe)
	{
		// nop
	}

	/**
	 * @see java.net.ProxySelector#select(java.net.URI)
	 * @param uri
	 * @return
	 */
	@Override
	public List<Proxy> select(final URI uri)
	{
		String host = uri == null ? null : uri.getHost();
		if (host != null)
		{
			host = host.toLowerCase();
		}

		boolean bLocal = false;
		try
		{
			final InetAddress a = InetAddress.getByName(host);
			bLocal = a.isSiteLocalAddress();
		}
		catch (final UnknownHostException x)
		{
			// nop
		}

		if (host == null || bLocal || "localhost".equals(host) || "127.0.0.1".equals(host))
		{
			return noproxies;
		}

		return myproxies;
	}

}