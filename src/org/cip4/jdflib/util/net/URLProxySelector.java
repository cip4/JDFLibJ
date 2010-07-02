/**
 * 
 */
package org.cip4.jdflib.util.net;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;
import java.util.Vector;

class URLProxySelector extends ProxySelector
{

	/**
	 * @param p a proxy to add 
	 * 
	 */
	protected URLProxySelector(Proxy p)
	{
		super();
		myproxies = new Vector<Proxy>();
		if (p != null)
		{
			myproxies.add(p);
			noproxies = new Vector<Proxy>();
			noproxies.add(Proxy.NO_PROXY);
		}
		else
		{
			noproxies = myproxies;
		}
		myproxies.add(Proxy.NO_PROXY);
	}

	Vector<Proxy> myproxies;
	Vector<Proxy> noproxies;

	/**
	 * @see java.net.ProxySelector#connectFailed(java.net.URI, java.net.SocketAddress, java.io.IOException)
	 * @param uri
	 * @param sa
	 * @param ioe
	*/
	@Override
	public void connectFailed(URI uri, SocketAddress sa, IOException ioe)
	{
		//nop			
	}

	/**
	 * @see java.net.ProxySelector#select(java.net.URI)
	 * @param uri
	 * @return
	*/
	@Override
	public List<Proxy> select(URI uri)
	{
		String host = uri == null ? null : uri.getHost().toLowerCase();
		if (host == null || "localhost".equals(host) || "127.0.0.1".equals(host))
			return noproxies;

		return myproxies;
	}

}