/**
 * 
 */
package org.cip4.jdflib.elementwalker;

import junit.framework.TestCase;

import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.node.JDFNode;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class RemovePrivateTest extends TestCase
{
	/**
	 * 
	 */
	public void testRemove()
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		n.setAttribute("foo:bar", "www.foo.com", "blub");
		n.appendElement("blah:e", "www.blah.com");
		n.getCreateAuditPool().setAttribute("foo:bar", "www.foo.com", "blub");
		n.getCreateAuditPool().appendElement("blah:e", "www.blah.com");
		RemovePrivate rp = new RemovePrivate();
		rp.walkTree(n, null);
		assertNull(n.getAttribute("foo:bar", "www.foo.com", null));
		assertNull(n.getElement("e", "www.blah.com", 0));
		assertNotNull(n.getAuditPool());

	}
}
