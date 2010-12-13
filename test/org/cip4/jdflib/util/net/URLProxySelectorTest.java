/**
 * 
 */
package org.cip4.jdflib.util.net;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlPart;
import org.cip4.jdflib.util.UrlUtil;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class URLProxySelectorTest extends JDFTestCaseBase
{
	/**
	 * 
	 * @throws URISyntaxException
	 */
	public void testSelect() throws URISyntaxException
	{
		for (int i = 0; i < 2; i++)
		{
			if (i == 1)
			{
				UrlPart p = UrlUtil.writeToURL("http://proxy:8080", null, UrlUtil.GET, null, null);
				if (p == null)
					break;
				ProxyUtil.setProxy("proxy", 8080, null, null);
			}
			Proxy p = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(StringUtil.token("proxy", 0, ":"), 8080));
			URLProxySelector ups = new URLProxySelector(p);
			URI uri = UrlUtil.stringToURL("http://localhost").toURI();
			List<Proxy> l = ups.select(uri);
			assertEquals(1, l.size());
			uri = UrlUtil.stringToURL("http://www.google.com").toURI();
			l = ups.select(uri);
			assertEquals(2, l.size());
			uri = UrlUtil.stringToURL("http://68.123.4.5").toURI();
			l = ups.select(uri);
			assertEquals(2, l.size());
			uri = UrlUtil.stringToURL("http://kie-wf19prdy").toURI();
			l = ups.select(uri);
			assertEquals(1, l.size());
		}
	}
}
