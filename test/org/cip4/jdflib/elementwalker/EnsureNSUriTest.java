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
	public void testEnsureNSNull()
	{
		XMLDoc d = new XMLDoc("foo", null);
		KElement root = d.getRoot();
		KElement e = root.appendElement("bar", "n66");
		e.appendElement("gg", "n77").setAttribute("ntest", "123");
		e.appendElement("blub:bb", "www.blub.com").setAttribute("blub:ntest", "123");

		EnsureNSUri ensure = new EnsureNSUri();
		ensure.addNS(null, "www.n1.com");
		ensure.addAlias("blub", null);
		ensure.walk(root);

		assertTrue(root.toXML().indexOf("n6") < 0);
		assertTrue(root.toXML().indexOf("n7") < 0);
		assertTrue(root.toXML().indexOf("blub") < 0);
	}

	/**
	 * 
	 */
	public void testEnsureNSAlias()
	{
		XMLDoc d = new XMLDoc("foo", "bar");
		KElement root = d.getRoot();
		root.addNameSpace("n1", "n6");
		root.addNameSpace("n2", "n6");
		root.setAttribute("n1:gg", "gg");
		root.setAttribute("n3:gg", "other", "www.n3.com");
		KElement e = root.appendElement("n2:bar", "n6");
		e.appendElement("n1:gg").setAttribute("n1:test", "123");
		e.appendElement("n2:gg").setAttribute("n2:test", "123");
		e.appendElement("n3:next").setAttribute("n3:test", "456");

		EnsureNSUri ensure = new EnsureNSUri();
		ensure.addNS("n1", "www.n1.com");
		ensure.addAlias("n2", "n1");
		ensure.walk(root);

		assertTrue(root.toXML().indexOf("n2") < 0);
		assertTrue("undeclared n3 namespace is retained", root.toXML().indexOf("n3:test") > 0);
		assertTrue(root.toXML().indexOf("<n3:next") > 0);

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
