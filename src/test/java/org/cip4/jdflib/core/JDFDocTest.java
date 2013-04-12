/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of 
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
 */
/**
 * JDFDocTest.java
 * 
 * @author Kai Mattern
 *
 * Copyright (C) 2002 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.core;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.apache.xerces.parsers.DOMParser;
import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.XMLDocUserData.EnumDirtyPolicy;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFCreated;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 14.01.2009
 */
public class JDFDocTest extends JDFTestCaseBase
{

	/**
	 * 
	 */
	@Test
	public void testCreateElementNoNS()
	{
		final XMLDoc d = new JDFDoc("FOO:TEST");
		KElement root = d.getRoot();
		root.setAttribute("xmlns:FOO", "www.foo.com");
		final KElement e = (KElement) d.createElement("FOO:bar");
		Assert.assertNull(e.getNamespaceURI());
	}

	/**
	 * 
	 */
	@Test
	public void testIsDirty()
	{
		final JDFDoc doc = new JDFDoc("test");
		final KElement e = doc.getRoot();
		Assert.assertFalse(e.isDirty());
		Assert.assertFalse(doc.isDirty(null));
		doc.getCreateXMLDocUserData().setDirtyPolicy(EnumDirtyPolicy.Doc);
		Assert.assertFalse(e.isDirty());
		Assert.assertFalse(doc.isDirty(null));
		e.setAttribute("foo", "bar");
		Assert.assertTrue(e.isDirty());
		Assert.assertTrue(doc.isDirty(null));
		doc.clearDirtyIDs();
		Assert.assertFalse(doc.isDirty(null));
		Assert.assertFalse(e.isDirty());
		KElement e2 = e.appendElement("foobar");
		Assert.assertTrue(e.isDirty());
		Assert.assertTrue(doc.isDirty(null));
		Assert.assertTrue(e2.isDirty());
		doc.getCreateXMLDocUserData().setDirtyPolicy(EnumDirtyPolicy.XPath);
		doc.clearDirtyIDs();
		Assert.assertFalse(doc.isDirty(null));
		Assert.assertFalse(e.isDirty());
		e2 = e.appendElement("foobar");
		Assert.assertTrue(doc.isDirty(null));
		Assert.assertTrue(e.isDirty());
		Assert.assertTrue(e2.isDirty());
	}

	/**
	 * just a minor test. It only checks the precessgroup count and also the class casts in GetProcessGroups
	 */
	@Test
	public void testRoots()
	{
		final String xmlFile = "job.jdf";

		final JDFParser p = new JDFParser();
		final JDFDoc jdfDoc = p.parseFile(sm_dirTestData + xmlFile);

		Assert.assertTrue(xmlFile + ": Parse Error", jdfDoc != null);
		if (jdfDoc == null)
		{
			return; // soothe findbugs ;)
		}

		Assert.assertNotNull("jdf root", jdfDoc.getJDFRoot());
		Assert.assertNull("no jmf root", jdfDoc.getJMFRoot());
	}

	/**
	 * just a minor test. It only checks the precessgroup count and also the class casts in GetProcessGroups
	 */
	@Test
	public void testManyNew()
	{
		for (int i = 0; i < 10000; i++)
		{
			JDFDoc doc = new JDFDoc("JDF");
			Assert.assertNotNull(doc);
		}
	}

	/**
	 * just a minor test. It only checks that cloned docs are actually different
	 */
	@Test
	public void testClone()
	{
		final JDFDoc d = new JDFDoc("JDF");

		final JDFDoc d2 = d.clone();
		final KElement e1 = d.getRoot();
		final KElement e2 = d2.getRoot();
		Assert.assertNotSame(e1, e2);
		Assert.assertEquals(e2.getOwnerDocument(), d2.getMemberDocument());
		e1.appendElement("foo");
		Assert.assertNull(e2.getElement("foo"));
	}

	/**
	 * 
	 */
	@Test
	public void testCloneTwice()
	{
		final JDFDoc d1 = new JDFDoc("JDF");
		final JDFNode node = d1.getJDFRoot();
		node.appendAncestorPool();
		final JDFNode root = node.getOwnerDocument_JDFElement().clone().getJDFRoot();
		Assert.assertNotNull(root);
		final JDFNode root2 = root.getOwnerDocument_JDFElement().clone().getJDFRoot();
		Assert.assertNotNull(root2);
		Assert.assertNotSame(root, root2);
	}

	/**
	 * 
	 */
	@Test
	public void testCopyXMLDoc()
	{
		final XMLDoc d1 = new XMLDoc("JDF", null);
		KElement root = d1.getRoot();
		Assert.assertFalse(root instanceof JDFNode);
		JDFDoc d = new JDFDoc(d1);
		JDFNode n = d.getJDFRoot();
		Assert.assertNotNull(n);

		XMLDoc d2 = new XMLDoc(d);
		root = d2.getRoot();
		Assert.assertTrue(root instanceof JDFNode);
	}

	/**
	 * just a minor test. It only checks that cloned docs are actually different
	 */
	@Test
	public void testCloneNav()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode root = d.getJDFRoot();
		final JDFResource r = root.addResource("Media", EnumUsage.Input);
		Assert.assertNotNull(r);
		final JDFDoc d2 = d.clone();
		final KElement e1 = d.getRoot();
		final KElement e2 = d2.getRoot();
		Assert.assertNotSame(e1, e2);
		e1.appendElement("foo");
		Assert.assertNull(e2.getElement("foo"));
		Assert.assertNotNull(e2.getChildByTagName("Media", null, 0, null, false, false));
		final NodeList nl = d.getElementsByTagName("Media");
		final NodeList nl2 = d2.getElementsByTagName("Media");
		Assert.assertEquals(nl.getLength(), 1);
		Assert.assertEquals(nl2.getLength(), 1);
	}

	/**
	 * 
	 */
	@Test
	public void testForeignRoot()
	{
		final XMLDoc doc = new XMLDoc("Foo", "fooNS");
		final KElement r = doc.getRoot();
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		r.copyElement(n, null);
		final String s = doc.write2String(0);
		final JDFParser p = new JDFParser();
		final JDFDoc d = p.parseString(s);
		Assert.assertNotNull(d.getJDFRoot());
		Assert.assertNotNull(d.getRoot());
		Assert.assertNotSame(d.getRoot(), d.getJDFRoot());

	}

	// ///////////////////////////////////////////////////

	/**
	 * just a minor test. It only checks the precessgroup count and also the class casts in GetProcessGroups
	 */
	@Test
	public void testNullParser()
	{
		JDFDoc doc = null;
		final String foo = "wehflkh";
		final JDFParser p = new JDFParser();

		doc = p.parseString(foo);
		Assert.assertNull(doc);
		doc = new JDFDoc("JDF");
		Assert.assertNotNull(doc.getNodeName());
	}

	/**
	 * just a minor test. It only checks the precessgroup count and also the class casts in GetProcessGroups
	 */
	@Test
	public void testRegisterCustomClass()
	{
		JDFDoc.registerCustomClass("Foo123", JDFRunList.class.getName());
		JDFResourcePool resourcePool = new JDFDoc("JDF").getJDFRoot().getCreateResourcePool();
		KElement e = resourcePool.appendElement("Foo123");
		Assert.assertTrue(e instanceof JDFRunList);
		KElement e2 = resourcePool.appendElement("WWW:Foo123", "WWW.com");
		Assert.assertFalse(e2 instanceof JDFRunList);
	}

	/**
	 * 
	 * test graceful null handling
	 */
	@Test
	public void testNull()
	{
		new JDFDoc((XMLDoc) null);
		new JDFDoc(new XMLDoc());
	}

	// ///////////////////////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testGetContentType()
	{
		final JDFDoc d = new JDFDoc("JDF");
		Assert.assertEquals(d.getContentType(), "application/vnd.cip4-jdf+xml");
		final JDFDoc dm = new JDFDoc("JMF");
		Assert.assertEquals(dm.getContentType(), "application/vnd.cip4-jmf+xml");
		final JDFDoc db = new JDFDoc("JMF_");
		Assert.assertEquals(db.getContentType(), "text/xml");
	}

	/**
	 * 
	 */
	@Test
	public void testSchemaDefault()
	{
		for (int i = 0; i < 3; i++)
		{
			final JDFDoc doc = new JDFDoc("JDF");
			final JDFNode n = (JDFNode) doc.getRoot();
			Assert.assertFalse("no schema - no default", n.hasAttribute(AttributeName.TEMPLATE));
			final String s = doc.write2String(2);
			final JDFParser parser = new JDFParser();
			final JDFDoc docNoSchema = parser.parseString(s);
			final JDFNode as2 = (JDFNode) docNoSchema.getRoot();
			Assert.assertFalse("no schema - no default", as2.hasAttribute(AttributeName.TEMPLATE));
			parser.setJDFSchemaLocation(UrlUtil.urlToFile(sm_dirTestSchema + File.separator + "JDF.xsd"));
			final JDFDoc docSchema = parser.parseString(s);
			final JDFNode as3 = (JDFNode) docSchema.getRoot();
			Assert.assertTrue("schema parse - default is set", as3.hasAttribute(AttributeName.TEMPLATE));
			Assert.assertFalse("schema parse - default is set", as3.getTemplate());
		}
	}

	/**
	 * 
	 */
	@Test
	public void testNS()
	{
		final JDFDoc doc = new JDFDoc("foo:bar");
		final String s = doc.write2String(2);
		Assert.assertTrue(s.indexOf(JDFConstants.JDFNAMESPACE) > 0);
		final XMLDoc doc2 = new XMLDoc("abc", null);
		String s2 = doc2.write2String(2);
		Assert.assertTrue(s2.indexOf(JDFConstants.JDFNAMESPACE) < 0);
		doc2.getRoot().copyElement(doc.getRoot(), null);
		s2 = doc2.write2String(2);
		Assert.assertTrue(s2.indexOf(JDFConstants.JDFNAMESPACE) > 0);

	}

	/**
	 * tests the correct handling of the namespaceuri when a namespace attribute is set
	 */
	@Test
	public void testCreateElementNS()
	{
		JDFDoc doc = new JDFDoc("foo:bar");
		String s = doc.write2String(2);
		Assert.assertTrue(s.indexOf(JDFConstants.JDFNAMESPACE) > 0);
		KElement root = doc.getRoot();
		root.setAttribute("xmlns:foo", "www.foo.com");
		Assert.assertEquals(root.getNamespaceURI(), "www.foo.com");
		doc = new JDFDoc("bar");
		s = doc.write2String(2);
		Assert.assertTrue(s.indexOf(JDFConstants.JDFNAMESPACE) > 0);
		root = doc.getRoot();
		root.setAttribute("xmlns", "www.foo.com");
		Assert.assertEquals(root.getNamespaceURI(), "www.foo.com");
	}

	/**
	 * 
	 */
	@Test
	public void testSetInitOnCreate()
	{
		JDFDoc doc = new JDFDoc();
		doc.setInitOnCreate(false);
		doc.setRoot("JDF", null);
		JDFNode n = doc.getJDFRoot();
		Assert.assertNull(n.getAuditPool());
	}

	/**
	 * @throws IOException 
	 * @throws SAXException 
	 * 
	 */
	@Test
	public void testParseDOM() throws SAXException, IOException
	{
		DOMParser domParser = new DOMParser();
		domParser.parse(new InputSource(new StringReader("<JDF ID=\"1\"><AuditPool><Created ID=\"1\"/></AuditPool></JDF>")));
		Assert.assertNotNull(domParser.getDocument());
		JDFDoc d = new JDFDoc(domParser.getDocument());
		Assert.assertNotNull(d);
		Assert.assertNotNull(d.getRoot());
		Assert.assertEquals(d.getRoot().getAttribute("ID"), "1");
		Assert.assertTrue(d.getRoot().getXPathElement("/JDF/AuditPool/Created") instanceof JDFCreated);
	}

	/**
	 * 
	 */
	@Test
	public void testPerformance()
	{
		{
			final JDFDoc doc = new JDFDoc("JDF");
			final KElement root = doc.getRoot();
			long l = System.currentTimeMillis();
			for (int i = 0; i < 10000; i++)
			{
				root.appendElement("Elem00");
			}
			System.out.println("Append With factory: " + (System.currentTimeMillis() - l));
			l = System.currentTimeMillis();
			final String s = doc.write2String(0);
			System.out.println("Write With factory: " + (System.currentTimeMillis() - l));
			l = System.currentTimeMillis();
			final JDFParser p = new JDFParser();
			System.out.println("Parser With factory: " + (System.currentTimeMillis() - l));
			l = System.currentTimeMillis();
			p.parseString(s);
			System.out.println("Parse With factory: " + (System.currentTimeMillis() - l));
		}
		{
			final JDFDoc doc = new JDFDoc("JDF");
			final KElement root = doc.getRoot();
			((DocumentJDFImpl) root.getOwnerDocument()).bKElementOnly = true;
			long l = System.currentTimeMillis();
			for (int i = 0; i < 10000; i++)
			{
				root.appendElement("Elem00");
			}
			System.out.println("Append Without factory: " + (System.currentTimeMillis() - l));
			l = System.currentTimeMillis();
			final String s = doc.write2String(0);
			System.out.println("Write Without factory: " + (System.currentTimeMillis() - l) + " " + s.length());
			l = System.currentTimeMillis();
			final JDFParser p = new JDFParser();
			System.out.println("Parser Without factory: " + (System.currentTimeMillis() - l));
			l = System.currentTimeMillis();
			p.parseString(s);
			System.out.println("Parse Without factory: " + (System.currentTimeMillis() - l));
		}

		{
			final JDFDoc doc = new JDFDoc("JDF");
			final KElement root = doc.getRoot();
			((DocumentJDFImpl) root.getOwnerDocument()).bKElementOnly = true;
			long l = System.currentTimeMillis();
			for (int i = 0; i < 10000; i++)
			{
				root.appendElement("Elem00");
			}
			System.out.println("Append00 Without factory: " + (System.currentTimeMillis() - l));
			l = System.currentTimeMillis();
			final String s = doc.write2String(0);
			System.out.println("Write00 Without factory: " + (System.currentTimeMillis() - l));
			l = System.currentTimeMillis();
			final JDFParser p = new JDFParser();
			System.out.println("Parser00 Without factory: " + (System.currentTimeMillis() - l));
			l = System.currentTimeMillis();
			p.parseString(s);
			System.out.println("Parse00 Without factory: " + (System.currentTimeMillis() - l));
		}

		{
			final JDFDoc doc = new JDFDoc("JDF");
			final KElement root = doc.getRoot();
			long l = System.currentTimeMillis();
			for (int i = 0; i < 10000; i++)
			{
				root.appendElement("Elem00");
			}
			System.out.println("Append With factory: " + (System.currentTimeMillis() - l));
			l = System.currentTimeMillis();
			final String s = doc.write2String(0);
			System.out.println("Write With factory: " + (System.currentTimeMillis() - l));
			l = System.currentTimeMillis();
			final JDFParser p = new JDFParser();
			System.out.println("Parser With factory: " + (System.currentTimeMillis() - l));
			l = System.currentTimeMillis();
			p.parseString(s);
			System.out.println("Parse With factory: " + (System.currentTimeMillis() - l));
		}

	}

	/**
	 * make sure that corrupt files always return a null document
	 * 
	 */
	@Test
	public void testCorrupt()
	{
		JDFDoc doc = null;
		String foo = "wehflkh";
		final JDFParser p = new JDFParser();
		doc = p.parseString(foo);
		Assert.assertNull(doc);
		foo = "<xxx><yyy><zzz></yyy></xxx>";
		doc = p.parseString(foo);
		Assert.assertNull(doc);

		doc = p.parseFile(sm_dirTestData + "corrupt.jdf");
		Assert.assertNull(doc);
		doc = new JDFDoc("JDF");
		Assert.assertNotNull(doc.getNodeName());
	}

	/**
	 * 
	 *
	 */
	@Test
	public void testEmptyString()
	{
		final JDFDoc inMessageDoc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmfIn = inMessageDoc.getJMFRoot();

		jmfIn.appendMessageElement(JDFMessage.EnumFamily.Response, null);
		final String s = inMessageDoc.write2String(0);
		Assert.assertNotNull(s);
	}

}
