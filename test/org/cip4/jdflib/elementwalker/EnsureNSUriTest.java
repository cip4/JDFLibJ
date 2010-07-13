/**
 * 
 */
package org.cip4.jdflib.elementwalker;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.util.CPUTimer;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class EnsureNSUriTest extends JDFTestCaseBase
{

	/**
	 * 
	 */
	public void testEnsureNS()
	{
		XMLDoc d = new XMLDoc("foo", "bar");
		KElement root = d.getRoot();
		root.addNameSpace("n1", "n6");
		root.setAttribute("n1:gg", "gg");
		KElement e = root.appendElement("n1:bar", "n66");
		e.appendElement("n1:gg").setAttribute("n1:test", "123");

		EnsureNSUri ensure = new EnsureNSUri();
		ensure.addNS("n1", "www.n1.com");
		ensure.walk(root);

		assertTrue(root.toXML().indexOf("n6") < 0);

	}

	/**
	 * 
	 */
	public void testBigEnsureNS()
	{
		CPUTimer ct = new CPUTimer(true);
		XMLDoc d = XMLDoc.parseFile(sm_dirTestData + "evilParts.jdf");
		KElement root = d.getRoot();
		assertNotNull(root);
		EnsureNSUri ensure = new EnsureNSUri();
		ensure.addNS("HDM", "www.hdm.com");
		ensure.walk(root);
		ct.stop();
		System.out.println(ct);

		assertTrue(root.toXML().indexOf("schema/HDM") < 0);

	}
}
