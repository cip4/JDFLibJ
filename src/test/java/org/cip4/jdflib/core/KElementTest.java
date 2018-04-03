/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of
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
 * KElementTest.java
 *
 * @author Dietrich Mucha
 *
 * Copyright (C) 2002 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement.SimpleNodeComparator;
import org.cip4.jdflib.core.KElement.SingleXPathComparator;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.CPUTimer;
import org.cip4.jdflib.util.StringUtil;
import org.junit.Test;
import org.w3c.dom.Element;

/**
 * test class for KElement
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class KElementTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	public void testIsEqual()
	{
		final KElement e1 = KElement.createRoot("a", null);
		final KElement e2 = e1.clone();

		assertTrue(e1.isEqual(e2));
		e1.setAttribute("a1", "v1");
		e1.setAttribute("a2", "v2");
		e2.setAttribute("a2", "v2");
		assertFalse(e1.isEqual(e2));
		e2.setAttribute("a1", "v1");
		assertTrue(e1.isEqual(e2));
	}

	/**
	 *
	 */
	@Test
	public void testEquals()
	{
		final KElement e1 = KElement.createRoot("a", null);
		final KElement e2 = e1.clone();

		assertTrue(e1.isEqual(e2));
		e1.setAttribute("a1", "v1");
		e1.setAttribute("a2", "v2");
		e2.setAttribute("a2", "v2");
		assertFalse(e1.equals(e2));
		e2.setAttribute("a1", "v1");
		assertFalse(e1.equals(e2));
	}

	/**
	 *
	 */
	@Test
	public void testIsEqualBig()
	{
		final JDFDoc d = JDFDoc.parseFile(sm_dirTestData + "matsch.jdf");
		final KElement root = d.getRoot();
		final KElement e = root.clone();
		assertTrue(root.isEqual(e));
		e.setXPathAttribute("JDF/JDF/ResourcePool/@Foo", "bar");
		assertFalse(root.isEqual(e));
	}

	/**
	 * really weird, eh?
	 */
	@Test
	public void testBadElementNames()
	{
		final JDFDoc doc = new JDFDoc("a");
		final KElement e = doc.getRoot();
		e.appendElement("I a=\"c\"");

		final String s = doc.write2String(2);
		// final JDFDoc d2 =
		assertNotNull(new JDFParser().parseString(s));
	}

	/**
	 *
	 */
	@Test
	public void testAncestorDistance()
	{
		final KElement e = new JDFDoc("a").getRoot();
		assertEquals(e.ancestorDistance(e), 0);
		final KElement e1 = e.appendElement("b");
		assertEquals(e.ancestorDistance(e1), 1);
		final KElement e11 = e1.appendElement("b");
		assertEquals(e.ancestorDistance(e11), 2);
		final KElement e2 = e.appendElement("b");
		assertEquals(e1.ancestorDistance(e2), -1);
	}

	/**
	 * check for memory leaks
	 */
	@Test
	public void testDeleteNode()
	{
		final KElement k = new XMLDoc("root", null).getRoot();
		for (int i = 0; i < 100000; i++)
		{
			k.appendElement("DOA").deleteNode();
		}
		final long currentMem = getCurrentMem();
		if (currentMem > mem)
		{
			assertEquals(currentMem, mem, 6660000);
		}
	}

	/**
	 * check for memory leaks
	 */
	@Test
	public void testImportNode()
	{
		final KElement k = new XMLDoc("root", null).getRoot();
		for (int i = 0; i < 50000; i++)
		{
			final KElement d2 = new XMLDoc("mama", null).getRoot();

			for (int j = 0; j < 100; j++)
			{
				d2.appendElement("kid");
			}
			k.moveElement(d2.appendElement("kid"), null);
		}
		final long currentMem = getCurrentMem();
		if (currentMem > mem)
			assertEquals(currentMem, mem, 142 * 50000); // allow 142 per element
	}

	/**
	 *
	 */
	@Test
	public void testEnumValid()
	{
		EnumValidationLevel level = EnumValidationLevel.RecursiveComplete;
		assertEquals(EnumValidationLevel.NoWarnComplete, EnumValidationLevel.setNoWarning(level, true));
		assertEquals(EnumValidationLevel.RecursiveComplete, EnumValidationLevel.setNoWarning(level, false));
		level = EnumValidationLevel.RecursiveIncomplete;
		assertEquals(EnumValidationLevel.NoWarnIncomplete, EnumValidationLevel.setNoWarning(level, true));
		assertEquals(EnumValidationLevel.RecursiveIncomplete, EnumValidationLevel.setNoWarning(level, false));
	}

	/**
	 * Test for void RemoveAttribute(String, String) - PR-AKMP-000001
	 */
	@Test
	public void testRemoveAttributeStringString()
	{
		final JDFParser p = new JDFParser();
		final JDFDoc jdfDoc = p.parseFile(sm_dirTestData + "emptyAuthorAttribute.jdf");

		final JDFNode root = (JDFNode) jdfDoc.getRoot();
		final KElement kElem = root.getChildByTagName("Created", null, 0, null, false, true);

		final boolean before = kElem.hasAttribute("Author", null, false);
		assertTrue("The Attribute 'Author' does not exist", before);

		if (before)
		{
			kElem.removeAttribute("Author", "");
			final boolean after = kElem.hasAttribute("Author", "", false);

			assertFalse("The Attribute 'Author' was not removed", after);
		}
	}

	/**
	 *
	 */
	@Test
	public void testRenameElement()
	{
		final XMLDoc d = new XMLDoc("root", "www.root.com");
		final KElement root = d.getRoot();
		final String nsUri = root.getNamespaceURI();
		root.renameElement("foo", null);
		assertEquals(nsUri, root.getNamespaceURI());
		assertEquals(root.getNodeName(), "foo");
		root.renameElement("bar", "www.bar.com");
		assertEquals("www.bar.com", root.getNamespaceURI());
		final String s = d.write2String(2);
		assertTrue(s.indexOf("www.root.com") < 0);
		assertTrue(s.indexOf("www.bar.com") > 0);
	}

	/**
	 *
	 */
	@Test
	public void testRenameAttribute()
	{
		final XMLDoc d = new XMLDoc("root", null);
		final KElement root = d.getRoot();
		root.setAttribute("a", "aa");
		root.renameAttribute("a", "b");
		assertEquals("aa", root.getAttribute("b"));
	}

	/**
	 *
	 */
	@Test
	public void testRemoveChildren()
	{
		final XMLDoc d = new XMLDoc("root", null);
		final KElement root = d.getRoot();
		for (int i = 0; i < 5; i++)
		{
			root.appendElement("a");
		}
		assertNotNull(root.getElement("a"));
		root.removeChildren("a", null, null);
		assertNull(root.getElement("a"));
	}

	// /////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetElementsWithMultipleID()
	{
		final XMLDoc d = new XMLDoc("e1", null);
		final KElement e1 = d.getRoot();
		assertNull(e1.getMultipleIDs("ID"));
		e1.setXPathAttribute("./e2[2]/e3/@ID", "i1");
		e1.setXPathAttribute("./e2[3]/e3/@ID", "i2");
		assertNull(e1.getMultipleIDs("ID"));
		e1.setXPathAttribute("./e2[4]/e3/@ID", "i1");
		assertEquals(e1.getMultipleIDs("ID").get(0), "i1");
		assertEquals(e1.getMultipleIDs("ID").size(), 1);
		e1.setAttribute("ID", "i2");
		assertEquals(e1.getMultipleIDs("ID").size(), 2);
		assertTrue(e1.getMultipleIDs("ID").contains("i1"));
		assertTrue(e1.getMultipleIDs("ID").contains("i2"));

	}

	// /////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetFirstChildElement()
	{
		final XMLDoc d = new XMLDoc("e1", null);
		final KElement e1 = d.getRoot();
		final KElement a = e1.appendElement("a");
		final KElement b = e1.appendElement("b");
		final KElement c = e1.appendElement("c:c", "cc");
		assertEquals(e1.getFirstChildElement("a", null), a);
		assertEquals(e1.getFirstChildElement("b", null), b);
		assertNull(e1.getFirstChildElement("d", null));
		assertEquals(e1.getFirstChildElement("c", "cc"), c);
	}

	// /////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testGetNextSibling()
	{
		final XMLDoc d = new XMLDoc("e1", null);
		final KElement e1 = d.getRoot();
		final KElement a = e1.appendElement("a");
		final KElement b = e1.appendElement("b");
		final KElement c = e1.appendElement("c:c", "cc");
		assertEquals(a.getNextSiblingElement("c", "cc"), c);
		assertEquals(b.getNextSiblingElement("c", "cc"), c);
		assertEquals(a.getNextSiblingElement("b", null), b);
		assertNull(a.getNextSiblingElement("a", null));
	}

	// /////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testNumChildrenByClass()
	{
		final JDFNode root = new JDFDoc("JDF").getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) root.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		assertEquals(root.numChildrenByClass(xm.getClass(), false), 0);
		assertEquals(root.numChildrenByClass(xm.getClass(), true), 1);
		assertEquals(root.numChildrenByClass(JDFResourcePool.class, false), 1);
		xm.addPartition(EnumPartIDKey.SignatureName, "sig1").addPartition(EnumPartIDKey.SheetName, "Sh1");
		assertEquals(root.numChildrenByClass(xm.getClass(), true), 3);
	}

	/**
	 *
	 */
	@Test
	public void testGetElementByClass()
	{
		final JDFDoc d = creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		final JDFAuditPool ap = n.getElementByClass(JDFAuditPool.class, 0, false);
		assertNotNull(ap);
		assertNull(n.getElementByClass(JDFExposedMedia.class, 0, false));
		assertNull(n.getElementByClass(JDFExposedMedia.class, 99999, true));
		assertNull(n.getElementByClass(JDFExposedMedia.class, -99999, true));
		assertNotNull(n.getElementByClass(JDFExposedMedia.class, 0, true));
		assertNotNull(n.getElementByClass(JDFExposedMedia.class, -1, true));
		assertNotSame(n.getElementByClass(JDFExposedMedia.class, 0, true), n.getElementByClass(JDFExposedMedia.class, -1, true));
	}

	/**
	 *
	 */
	@Test
	public void testGetElementById()
	{
		final String xmlString = "<JDF ID=\"Link20704459_000351\">" + "<ELEM2 ID=\"Link20704459_000352\">" + "<ELEM3 ID=\"Link20704459_000353\">" + "<Comment/>" + "</ELEM3>"
				+ "</ELEM2>" + "</JDF>";

		for (int i = 0; i < 2; i++)
		{
			final JDFParser parser = new JDFParser();
			if (i == 1)
			{
				parser.m_SchemaLocation = new File(sm_dirTestSchema + "JDF.xsd").toURI().toString();
			}
			final JDFDoc jdfDoc = parser.parseString(xmlString);
			final KElement root = jdfDoc.getRoot();

			KElement kElem, kElem2;

			// alt funktioniert
			kElem2 = root.getTarget("Link20704459_000351", AttributeName.ID);
			assertNotNull(kElem2);

			// neu funktioniert nicht -
			// http://mail-archives.apache.org/mod_mbox/
			// xerces-j-users/200410.mbox/%3c4178694B.40708@metalab.unc.edu%3e
			// http://www.stylusstudio.com/xmldev/200012/post80000.html
			kElem = (KElement) jdfDoc.getElementById("Link20704459_000351");
			assertNotNull(kElem);

			// alt funktioniert
			kElem2 = root.getTarget("Link20704459_000352", AttributeName.ID);
			assertNotNull(kElem2);

			// neu funktioniert nicht
			kElem = (KElement) jdfDoc.getElementById("Link20704459_000352");
			assertNotNull(kElem);

			// alt funktioniert
			kElem2 = root.getTarget("Link20704459_000353", AttributeName.ID);
			assertNotNull(kElem2);

			// neu funktioniert nicht
			kElem = (KElement) jdfDoc.getElementById("Link20704459_000353");
			assertNotNull(kElem);
		}
	}

	/**
	 *
	 */
	@Test
	public void testReplaceElementRoot()
	{
		final XMLDoc d = new XMLDoc("root", "www.root.com");
		final XMLDoc d2 = new XMLDoc("root2", "www.root2.com");
		final KElement e = d.getRoot();
		e.appendElement("c1");

		final KElement e2 = d2.getRoot();
		e2.replaceElement(e);
		assertEquals("copied root", d2.getRoot(), e2);
		assertTrue("same contents", e2.isEqualNode(e));
	}

	/**
	 * test memory leaks in replaceElement
	 */
	@Test
	public void testReplaceElementMem()
	{
		final XMLDoc d = new XMLDoc("root", "www.root.com");
		final KElement e = d.getRoot();
		final KElement ec1 = e.appendElement("c1");
		final long l = d.getDocMemoryUsed();
		for (int i = 0; i < 100000; i++)
		{
			ec1.replaceElement(d.clone().getRoot().getFirstChildElement());
		}
		System.gc();
		final long l2 = d.getDocMemoryUsed();
		assertEquals("memory nice and low", l2, l, 1420000);
	}

	/**
	 *
	 */
	@Test
	public void testReplaceElement()
	{
		final XMLDoc d = new XMLDoc("root", "www.root.com");
		final XMLDoc d2 = new XMLDoc("root2", "www.root2.com");
		final KElement e = d.getRoot();
		KElement ec1 = e.appendElement("c1");
		final KElement ec2 = e.appendElement("c2");
		ec2.setAttribute("foo", "ec2");
		final KElement ec4 = e.appendElement("c4");

		final KElement ec3 = ec1.replaceElement(ec2);
		assertEquals("c1=c2", ec3, ec2);
		assertEquals("c4 is next", ec3, ec4.getPreviousSibling());
		assertEquals("c4 is next", ec3.getNextSibling(), ec4);
		assertNull("no sibling", ec1.getNextSibling());
		assertNull("no sibling", ec1.getPreviousSibling());
		assertEquals("parent ok", ec2.getParentNode_KElement(), e);
		assertNull("ec1 no parent", ec1.getParentNode());

		final KElement ec33 = ec3.replaceElement(ec3);
		assertEquals("replace of this is a nop", ec3, ec33);

		// now cross document
		final KElement e2 = d2.getRoot();
		e2.appendElement("e22");

		ec1 = ec3.replaceElement(e2);
		assertNull("ec3 no parent", ec3.getParentNode());
		assertEquals("parent ok", ec1.getParentNode_KElement(), e);
		assertEquals("c4 is next", ec1, ec4.getPreviousSibling());
		assertEquals("c4 is next", ec1.getNextSibling(), ec4);
		assertEquals("root", ec1.getParentNode(), e);

		final KElement eNew = e.replaceElement(e2);
		assertTrue(eNew.isEqual(e2));
		assertTrue(e.isEqual(e2));

	}

	// //////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testSortChildren()
	{
		final XMLDoc d = new JDFDoc("parent");
		final KElement e = d.getRoot();
		final KElement b = e.appendElement("b");
		final KElement a = e.appendElement("a");
		a.setAttribute("ID", "a1");
		final KElement c = e.appendElement("c");
		e.sortChildren();
		assertEquals(e.getFirstChildElement(), a);
		assertEquals(a.getNextSiblingElement(), b);
		assertEquals(b.getNextSiblingElement(), c);
		final KElement a3 = e.appendElement("a");
		a3.setAttribute("ID", "z1");
		final KElement a2 = e.appendElement("a");
		a2.setAttribute("ID", "a2");
		e.sortChildren();
		assertEquals(e.getFirstChildElement(), a);
		assertEquals(a.getNextSiblingElement(), a2);
		assertEquals(a2.getNextSiblingElement(), a3);
		assertEquals(a3.getNextSiblingElement(), b);
		assertEquals(b.getNextSiblingElement(), c);

	}

	/**
	 *
	 */
	@Test
	public void testSortChild()
	{
		final XMLDoc d = new JDFDoc("parent");
		final KElement e = d.getRoot();
		final KElement b = e.appendElement("b");
		final KElement a = e.appendElement("a2");
		a.setAttribute("ID", "a1");
		final KElement c = e.appendElement("c");
		e.sortChild(b);
		assertEquals(e.getFirstChildElement(), a);
		assertEquals(a.getNextSiblingElement(), b);
		assertEquals(b.getNextSiblingElement(), c);
		final KElement a3 = e.appendElement("a2");
		a3.setAttribute("ID", "z1");
		final KElement a2 = e.appendElement("a2");
		a2.setAttribute("ID", "a2");
		final KElement a4 = e.appendElement("zz");
		a2.setAttribute("ID", "a2");
		e.sortChild(a2);
		e.sortChild(a3);
		e.sortChild(a4);
		assertEquals(e.getFirstChildElement(), a);
		assertEquals(a.getNextSiblingElement(), a2);
		assertEquals(a2.getNextSiblingElement(), a3);
		assertEquals(a3.getNextSiblingElement(), b);
		assertEquals(b.getNextSiblingElement(), c);
		assertEquals(c.getNextSiblingElement(), a4);
		final KElement a0 = e.appendElement("a0");
		e.sortChild(a0);
		assertEquals(e.getFirstChildElement(), a0);

	}

	/**
	 *
	 */
	@Test
	public void testSortChildrenAttribute()
	{
		final XMLDoc d = new JDFDoc("parent");
		final KElement e = d.getRoot();
		final KElement b = e.appendElement("b");
		final KElement a = e.appendElement("a");
		a.setAttribute("at", "a1");
		b.setAttribute("at", "a2");
		final KElement c = e.appendElement("c");
		c.setAttribute("at", "a3");
		// sort forward
		e.sortChildren(new KElement.SingleAttributeComparator("at", false));
		assertEquals(e.getFirstChildElement(), a);
		assertEquals(a.getNextSiblingElement(), b);
		assertEquals(b.getNextSiblingElement(), c);
		// now invert
		e.sortChildren(new KElement.SingleAttributeComparator("at", true));
		assertEquals(e.getFirstChildElement(), c);
		assertEquals(c.getNextSiblingElement(), b);
		assertEquals(b.getNextSiblingElement(), a);

	}

	/**
	 *
	 */
	@Test
	public void testSortChildrenXPath()
	{
		final XMLDoc d = new JDFDoc("parent");
		final KElement e = d.getRoot();
		final KElement b = e.appendElement("b");
		final KElement a = e.appendElement("a");
		a.setXPathAttribute("x/@at", "a1");
		b.setXPathAttribute("x/@at", "a2");
		final KElement c = e.appendElement("c");
		c.setXPathAttribute("x/@at", "a3");
		// sort forward
		e.sortChildren(new SingleXPathComparator("x/@at", false));
		assertEquals(e.getFirstChildElement(), a);
		assertEquals(a.getNextSiblingElement(), b);
		assertEquals(b.getNextSiblingElement(), c);
		// now invert
		e.sortChildren(new SingleXPathComparator("x/@at", true));
		assertEquals(e.getFirstChildElement(), c);
		assertEquals(c.getNextSiblingElement(), b);
		assertEquals(b.getNextSiblingElement(), a);

	}

	// //////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testSortChildrenCompPerformance()
	{
		XMLDoc d = new JDFDoc("parent");
		KElement e = d.getRoot();

		final CPUTimer t = new CPUTimer(false);
		for (int i = 0; i < 10000; i++)
		{
			final KElement ee = e.appendElement("b" + (int) (Math.random() * 100));
			for (int j = 0; j < 7; j++)
			{
				ee.setAttribute("J" + j, (i + j) % 17, null);
			}
		}
		t.start();
		e.sortChildren(new SimpleNodeComparator());
		log.info(t);
		t.stop();
		d = new JDFDoc("parent");
		e = d.getRoot();
		for (int i = 0; i < 10000; i++)
		{
			final KElement ee = e.appendElement("b" + (int) (Math.random() * 100));
			for (int j = 0; j < 7; j++)
			{
				ee.setAttribute("J" + j, (i + j) % 17, null);
			}
		}
		t.start();
		e.sortChildren();
		log.info(t);
		t.stop();
		t.start();
		e.sortChildren();
		log.info(t);
		t.stop();

	}

	/**
	 *
	 */
	@Test
	public void testSortChildrenComp()
	{

		final XMLDoc d = new JDFDoc("parent");
		final KElement e = d.getRoot();
		final KElement b = e.appendElement("b");
		final KElement a = e.appendElement("a");
		a.setAttribute("ID", "a1");

		final KElement c = e.appendElement("c");
		e.sortChildren(new SimpleNodeComparator());
		assertEquals(e.getFirstChildElement(), a);
		assertEquals(a.getNextSiblingElement(), b);
		assertEquals(b.getNextSiblingElement(), c);
		final KElement a3 = e.appendElement("a");
		a3.setAttribute("ID", "z1");
		final KElement a2 = e.appendElement("a");
		a2.setAttribute("ID", "a2");
		e.sortChildren();
		assertEquals(e.getFirstChildElement(), a);
		assertEquals(a.getNextSiblingElement(), a2);
		assertEquals(a2.getNextSiblingElement(), a3);
		assertEquals(a3.getNextSiblingElement(), b);
		assertEquals(b.getNextSiblingElement(), c);

	}

	// //////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testRemoveFromAttribute()
	{
		final XMLDoc d = new JDFDoc("Foo");
		final KElement e = d.getRoot();
		e.setAttribute("a", "1 b 2");
		e.removeFromAttribute("a", "b", null, " ", 333);
		assertEquals(e.getAttribute("a"), "1 2");
		e.setAttribute("a", "c");
		e.removeFromAttribute("a", "c", null, " ", 333);
		assertNull(e.getAttribute("a", null, null));
		e.removeFromAttribute("a", "c", null, " ", 333);
		assertNull(e.getAttribute("a", null, null));
		e.setAttribute("a", "abc ab abc");
		e.removeFromAttribute("a", "ab", null, " ", 333);
		assertEquals(e.getAttribute("a"), "abc abc");
		e.setAttribute("a", "abc1 abc2 abc");
		e.removeFromAttribute("a", "ab", null, " ", 333);
		assertEquals(e.getAttribute("a"), "abc1 abc2 abc");
		e.removeFromAttribute("a", "abc", null, " ", 333);
		assertEquals(e.getAttribute("a"), "abc1 abc2");
		e.removeFromAttribute("a", "", null, " ", 333);
		assertEquals(e.getAttribute("a"), "abc1 abc2");
		e.setAttribute("a", " abc1 abc2 abc ");
		e.removeFromAttribute("a", "abc1", null, " ", 333);
		assertEquals(e.getAttribute("a"), "abc2 abc");
		e.removeFromAttribute("a", "abc", null, " ", 333);
		assertEquals(e.getAttribute("a"), "abc2");
		e.setAttribute("a", " abc1 abc2 abc ");
		e.removeFromAttribute("a", "abc1", null, null, 333);
	}

	// //////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testRemoveExtensions()
	{
		final KElement e = new XMLDoc("e", "a.com").getRoot();
		final KElement b = e.appendElement("b", "b.com");
		b.setAttribute("c:at", "cc", "c.com");
		// KElement c=
		e.appendElement("c", "c.com");
		assertNotNull(b.getAttribute("at", "c.com", null));
		assertNotNull(e.getElement("c", "c.com", 0));
		e.removeExtensions("c.com");
		assertNull(b.getAttribute("at", "c.com", null));
		assertNull(e.getElement("c", "c.com", 0));

	}

	// //////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testRemoveEmptyAttributes()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final KElement e = d.getJDFRoot();

		e.setAttribute("foo", "bar", null);
		e.setAttribute("foo2", "", null);

		assertTrue("has foo", e.hasAttribute("foo"));
		assertTrue("has foo2", e.hasAttribute("foo2"));

		final KElement e2 = e.appendElement("e2");
		e2.setAttribute("foo", "bar", null);
		e2.setAttribute("foo2", "", null);

		e.eraseEmptyAttributes(false);
		assertTrue("has foo", e.hasAttribute("foo"));
		assertFalse("has foo2", e.hasAttribute("foo2"));
		assertTrue("has foo", e2.hasAttribute("foo"));
		assertTrue("has foo2", e2.hasAttribute("foo2"));

		e.eraseEmptyAttributes(true);
		assertTrue("has foo", e.hasAttribute("foo"));
		assertFalse("has foo2", e.hasAttribute("foo2"));
		assertTrue("has foo", e2.hasAttribute("foo"));
		assertFalse("has foo2", e2.hasAttribute("foo2"));
	}

	/**
	 *
	 */
	@Test
	public void testRemoveAttribute()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final KElement e = d.getJDFRoot();
		e.setAttribute("foo", "bar", null);
		assertTrue("has foo", e.hasAttribute("foo"));
		assertTrue("has foo", e.hasAttribute("foo", null, false));
		e.removeAttribute("foo", null);
		assertFalse("has foo", e.hasAttribute("foo"));
		assertFalse("has foo", e.hasAttribute("foo", null, false));
		e.removeAttribute("foo", null);
		e.removeAttribute("foo"); // make sure we have no npe for removing non
		// existing attrbutes
		e.removeAttribute(""); // make sure we have no npe for removing non
		// existing attrbutes

		e.setAttribute("foo", "bar", "");
		assertTrue("has foo", e.hasAttribute("foo"));
		assertTrue("has foo", e.hasAttribute("foo", "", false));
		e.removeAttribute("foo", "");
		assertFalse("has foo", e.hasAttribute("foo"));
		assertFalse("has foo", e.hasAttribute("foo", "", false));

		e.setAttribute("foo", "bar", JDFConstants.JDFNAMESPACE);
		assertTrue("has foo", e.hasAttribute("foo"));
		assertTrue("has foo", e.hasAttribute("foo", JDFConstants.JDFNAMESPACE, false));
		e.removeAttribute("foo", JDFConstants.JDFNAMESPACE);
		assertFalse("has foo", e.hasAttribute("foo"));
		assertFalse("has foo", e.hasAttribute("foo", JDFConstants.JDFNAMESPACE, false));

		e.setAttribute("JDF:foo", "bar", JDFConstants.JDFNAMESPACE);
		assertTrue("has foo", e.hasAttribute("JDF:foo"));
		assertTrue("has foo", e.hasAttribute("foo", JDFConstants.JDFNAMESPACE, false));
		e.removeAttribute("foo", JDFConstants.JDFNAMESPACE);
		assertFalse("has foo", e.hasAttribute("JDF:foo"));
		assertFalse("has foo", e.hasAttribute("foo", JDFConstants.JDFNAMESPACE, false));

	}

	/**
	 *
	 */
	@Test
	public void testMatchesPath()
	{
		final XMLDoc doc = new XMLDoc("Test", "www.test.com");
		final KElement root = doc.getRoot();
		final KElement a = root.appendElement("a");
		root.appendElement("b");
		final KElement a2 = root.appendElement("a");
		final KElement a3 = root.appendElement("a");
		final KElement c = root.appendElement("ns:c", "www.c.com");
		c.setAttribute("att", "41");
		a.setAttribute("att", "42");
		assertTrue(a.matchesPath("//a", false));
		assertTrue(a.matchesPath("/Test/a", false));
		assertTrue(a.matchesPath("/Test/a[1]", false));
		assertTrue(a.matchesPath("/Test/a[@att=\"42\"]", false));
		assertTrue(a2.matchesPath("/Test/a[2]", false));
		assertTrue(a3.matchesPath("/Test/a[3]", false));
		assertFalse(a3.matchesPath("/Test/a[@att=\"*\"]", false));
		assertTrue(a.matchesPath("/Test/a[@att=\"*\"]", false));
		assertTrue(a.matchesPath("/Test/a[@att=\"42\"]", false));
		assertFalse(a.matchesPath("/Test/a[@att=\"43\"]", false));
		assertTrue(c.matchesPath("/Test/ns:c", false));
		assertTrue(c.matchesPath("/Test/ns:c[1]", false));
		assertTrue(c.matchesPath("/Test/ns:c[@att=\"*\"]", false));
		assertTrue(c.matchesPath("/Test/ns:c[@att=\"41\"]", false));
	}

	/**
	 *
	 */
	@Test
	public void testMergeElement()
	{
		final XMLDoc doc = new XMLDoc("Test", "www.test.com");
		final KElement root = doc.getRoot();
		final KElement t2 = root.appendElement("foo");
		t2.setAttribute("a", "1");
		t2.setAttribute("b", "1");

		final XMLDoc doc2 = new XMLDoc("Test", "www.test.com");
		final KElement root2 = doc2.getRoot();
		final KElement t3 = root2.appendElement("foo");
		t3.setAttribute("a", "2");
		t3.setAttribute("c", "2");

		t2.mergeElement(t3, false);
		assertEquals(t2.getAttribute("a"), "2");
		assertEquals(t2.getAttribute("b"), "1");
		assertEquals(t2.getAttribute("c"), "2");
	}

	/**
	 *
	 */
	@Test
	public void testCopyInto()
	{
		final XMLDoc doc = new XMLDoc("Test", "www.test.com");
		final KElement root = doc.getRoot();
		final KElement t = root.appendElement("foo");
		t.setAttribute("a", "1");
		t.setAttribute("b", "1");
		t.setAttribute("c:d", "1", "a.com");

		final XMLDoc doc2 = new XMLDoc("Test", "www.test.com");
		final KElement root2 = doc2.getRoot();

		root2.copyInto(root, true);
		final KElement t2 = root2.getElement("foo");
		assertEquals(t2.getAttribute("a"), "1");
		assertEquals(t2.getAttribute("b"), "1");
		assertEquals(t2.getAttribute("c:d"), "1");
		assertTrue(root2.isEqual(root));
		final XMLDoc doc3 = new XMLDoc("Test", "www.test.com");
		final KElement root3 = doc3.getRoot();

		root3.copyInto(root, true);
		final KElement t3 = root2.getElement("foo");
		assertEquals(t3.getAttribute("a"), "1");
		assertEquals(t3.getAttribute("b"), "1");
		assertEquals(t3.getAttribute("c:d"), "1");
		assertTrue(root3.isEqual(root));
	}

	/**
	 *
	 */
	@Test
	public void testMergeElementElements()
	{
		final XMLDoc doc = new XMLDoc("Test", "www.test.com");
		final KElement root = doc.getRoot();
		final KElement t2 = root.appendElement("foo");
		final KElement t22 = t2.appendElement("bar");
		t22.setAttribute("a", "1");
		t22.setAttribute("b", "1");

		final XMLDoc doc2 = new XMLDoc("Test", "www.test.com");
		final KElement root2 = doc2.getRoot();
		final KElement t3 = root2.appendElement("foo");
		final KElement t32 = t3.appendElement("bar");
		t32.setAttribute("a", "2");
		t32.setAttribute("c", "2");

		t2.mergeElement(t3, false);
		assertEquals(t2.getElement("bar", null, 0), t22);
		assertEquals(t22.getAttribute("a"), "2");
		assertEquals(t22.getAttribute("b"), "1");
		assertEquals(t22.getAttribute("c"), "2");

	}

	/**
	 *
	 */
	@Test
	public void testMergeElementMultiElements()
	{
		final XMLDoc doc = new XMLDoc("Test", "www.test.com");
		final KElement root = doc.getRoot();
		final KElement t2 = root.appendElement("foo");
		final KElement t22 = t2.appendElement("bar");
		t22.setAttribute("a", "1");
		t22.setAttribute("b", "1");

		final XMLDoc doc2 = new XMLDoc("Test", "www.test.com");
		final KElement root2 = doc2.getRoot();
		final KElement t3 = root2.appendElement("foo");
		final KElement t32 = t3.appendElement("bar");
		t32.setAttribute("a", "2");
		t32.setAttribute("c", "2");
		final KElement t33 = t3.appendElement("bar");
		t33.setAttribute("a", "4");
		t33.setAttribute("b", "4");

		t2.mergeElement(t3, false);
		assertEquals(t2.getElement("bar", null, 0), t22);
		assertEquals(t22.getAttribute("a"), "1");
		assertEquals(t22.getAttribute("b"), "1");
		assertEquals(t22.getAttribute("c"), "");
		assertEquals(t2.getElement("bar", null, 1).toXML(), t32.toXML());
		assertEquals(t2.getElement("bar", null, 2).toXML(), t33.toXML());

	}

	/**
	 *
	 */
	@Test
	public void testMoveMe()
	{
		final XMLDoc doc = new XMLDoc("Test", "www.test.com");
		final KElement root = doc.getRoot();
		final KElement a = root.appendElement("a");
		KElement b = root.appendElement("b");
		final KElement c = b.appendElement("c");
		assertNull(root.moveMe(a));
		assertNull(b.moveMe(c));
		assertEquals(b.getPreviousSibling(), a);
		b = b.moveMe(a);
		assertEquals(b.getNextSibling(), a);
		b = b.moveMe(b);
		assertEquals(b.getNextSibling(), a);
		b = b.moveMe(null);
		assertEquals(a.getNextSibling(), b);
	}

	/**
	 *
	 */
	@Test
	public void testNumChildElements()
	{
		final KElement root = KElement.createRoot("Test", "www.test.com");
		root.appendElement("a");
		root.appendElement("a");
		root.appendElement("test:a", "www.test1.com");
		root.appendElement("c");
		assertEquals(root.numChildElements(null, null), 4);
		assertEquals(root.numChildElements("a", null), 3);
		assertEquals(root.numChildElements("a", "www.test.com"), 2);
		assertEquals(root.numChildElements("a", "www.test1.com"), 1);
	}

	/**
	 *
	 */
	@Test
	public void testNumSiblingElements()
	{
		final KElement root = KElement.createRoot("Test", "www.test.com");
		final KElement a = root.appendElement("a");
		root.appendElement("a");
		root.appendElement("a");
		root.appendElement("test:a", "www.test1.com");
		a.appendElement("c");
		assertEquals(a.numSiblingElements(null, null), 3);
		assertEquals(a.numSiblingElements("a", null), 3);
		assertEquals(root.numSiblingElements("a", null), 0);
		assertEquals(a.numSiblingElements("a", "www.test.com"), 2);
		assertEquals(a.numSiblingElements("a", "www.test1.com"), 1);
	}

	/**
	 *
	 */
	@Test
	public void testMoveElement()
	{
		final XMLDoc doc = new XMLDoc("Test", null);
		final KElement root = doc.getRoot();
		final KElement a = root.appendElement("a");
		assertEquals(root.moveElement(a, a), a);
		final KElement b = root.appendElement("b");
		final KElement c = b.appendElement("c");
		final KElement c2 = b.removeChild("c", null, 0);
		assertEquals(c, c2);
		final KElement c3 = a.moveElement(c2, null);
		assertEquals(c, c3);
		assertEquals(a.getElement("c"), c3);
	}

	/**
	 *
	 */
	@Test
	public void testCopyElementMem()
	{
		final KElement k = new XMLDoc("root", null).getRoot();
		for (int i = 0; i < 100000; i++)
		{
			final KElement d2 = new XMLDoc("mama", null).getRoot();

			for (int j = 0; j < 100; j++)
			{
				d2.appendElement("kid").appendElement("grandKid");
			}
			k.copyElement(d2.getElement("kid"), null);
		}
		k.removeChildren("kid", null, null);

		final long currentMem = getCurrentMem();

		System.out.println("testCopyElementMem:" + Long.toString(currentMem - mem));
		assertEquals(currentMem, mem, 2500000); // allow 2500 per element
	}

	/**
	 *
	 */
	@Test
	public void testCloneElementMem()
	{
		final XMLDoc doc = new XMLDoc("root", null);
		// KElement k =
		doc.getRoot();
		for (int i = 0; i < 50000; i++)
		{
			final KElement d2 = new XMLDoc("mama", null).getRoot();
			d2.cloneNode(true);
			doc.cloneNode(true);
		}
		final long currentMem = getCurrentMem();
		if (currentMem > mem)
		{
			assertEquals("delta: " + (currentMem - mem), currentMem, mem, 10000000);
		}
	}

	/**
	 * @throws CloneNotSupportedException
	 *
	 */
	@Test
	public void testClone() throws CloneNotSupportedException
	{
		final XMLDoc doc = new XMLDoc("root", null);
		final KElement e = doc.getRoot();
		final KElement d = e.getCreateXPathElement("a/b/c/d[3]");
		final KElement c_clone = d.getParentNode_KElement().clone();
		assertNotNull(c_clone);
		assertTrue(c_clone.isEqual(c_clone.clone()));
		assertNotSame(c_clone, c_clone.clone());
		assertEquals(c_clone.getOwnerDocument(), c_clone.clone().getOwnerDocument());

		final KElement rootClone = e.clone();
		assertTrue(rootClone.isEqual(e.clone()));
		assertNotSame(rootClone, e.clone());
		assertEquals(rootClone.getOwnerDocument(), e.clone().getOwnerDocument());
		assertTrue(rootClone.isEqual(e));
		assertNotSame(rootClone, e);
		assertEquals(rootClone.getOwnerDocument(), e.getOwnerDocument());
	}

	/**
	 * @throws CloneNotSupportedException
	 *
	 */
	@Test
	public void testCloneNewDoc() throws CloneNotSupportedException
	{
		final XMLDoc doc = new XMLDoc("root", null);
		final KElement e = doc.getRoot();
		final KElement d = e.getCreateXPathElement("a/b/c/d[3]");
		final KElement c_clone = d.getParentNode_KElement().cloneNewDoc();
		assertNotNull(c_clone);
		assertTrue(c_clone.isEqual(c_clone.cloneNewDoc()));
		assertNotSame(c_clone, c_clone.cloneNewDoc());
		assertNotSame(c_clone.getOwnerDocument(), c_clone.cloneNewDoc().getOwnerDocument());

		final KElement rootClone = e.cloneNewDoc();
		assertTrue(rootClone.isEqual(e.cloneNewDoc()));
		assertNotSame(rootClone, e.cloneNewDoc());
		assertNotSame(rootClone.getOwnerDocument(), e.cloneNewDoc().getOwnerDocument());
		assertTrue(rootClone.isEqual(e));
		assertNotSame(rootClone, e);
		assertNotSame(rootClone.getOwnerDocument(), e.getOwnerDocument());
	}

	/**
	 *
	 */
	@Test
	public void testCloneRoot()
	{
		final XMLDoc doc = new XMLDoc("root", null);
		final KElement k = doc.getRoot();
		k.appendElement("foo");
		final KElement root = doc.getRoot();
		final KElement k2 = root.cloneRoot(doc.clone());
		assertTrue(root.isEqual(k));
		assertNotSame(k2.getOwnerDocument(), doc.getMemberDocument());
	}

	/**
	 *
	 */
	@Test
	public void testMoveAttributeNS()
	{
		final XMLDoc doc = new XMLDoc("Test", "www.test.com");
		final KElement root = doc.getRoot();
		final KElement a = root.appendElement("a");
		a.setAttribute("foo:a1", "aa", "foo.ns");
		a.moveAttribute("bar:a1", a, "foo:a1", "bar.ns", "foo.ns");
		assertNull(a.getAttribute("foo:a1", "foo.ns", null));
		assertEquals(a.getAttribute("a1", "bar.ns", null), "aa");
	}

	/**
	 *
	 */
	@Test
	public void testMoveAttribute()
	{
		final XMLDoc doc = new XMLDoc("Test", "www.test.com");
		final KElement root = doc.getRoot();
		final KElement a = root.appendElement("a");
		final KElement b = root.appendElement("b");
		a.setAttribute("att", "42");
		b.moveAttribute("att", a, null, null, null);
		assertEquals(b.getAttribute("att"), "42");
		assertNull(a.getAttribute("att", null, null));
		b.moveAttribute("noThere", a, null, null, null);
		assertNull(b.getAttribute("noThere", null, null));
		assertNull(a.getAttribute("noThere", null, null));
		a.setAttribute("foo", "a");
		b.moveAttribute("bar", a, "foo", null, null);
		assertEquals(b.getAttribute("bar"), "a");
		assertNull(a.getAttribute("bar", null, null));
		assertNull(b.getAttribute("foo", null, null));
		assertNull(a.getAttribute("foo", null, null));
		b.moveAttribute("bar", b, "bar", null, null);
		assertEquals(b.getAttribute("bar"), "a");
		b.moveAttribute("bar", null, "bar", null, null);
		assertEquals(b.getAttribute("bar"), "a");
		b.moveAttribute("bar", null, null, null, null);
		assertEquals(b.getAttribute("bar"), "a");
		b.moveAttribute("bar2", null, "bar", null, null);
		assertEquals(b.getAttribute("bar2"), "a");
		assertNull(b.getAttribute("bar", null, null));
	}

	/**
	 * test for copyElement
	 */
	@Test
	public void testCopyElement()
	{
		final XMLDoc d = new XMLDoc("d1", null);
		final KElement e = d.getRoot();
		final XMLDoc d2 = new XMLDoc("d2", null);
		final KElement e2 = d2.getRoot();
		final KElement e3 = e.copyElement(e2, null);
		assertNull(e3.getNamespaceURI());
		assertFalse(d.toString().indexOf("xmlns=\"\"") >= 0);
	}

	/**
	 * test for copyElement after clone check for spurious npes here
	 */
	@Test
	public void testCopyElementClone()
	{
		final XMLDoc d = new XMLDoc("d1", null);
		final KElement e = d.getRoot();
		final XMLDoc d2 = new XMLDoc("d2", null);
		for (int i = 0; i < 10000; i++)
		{
			final KElement e3 = e.copyElement(d2.clone().getRoot(), null);
			assertNull(e3.getNamespaceURI());
		}

		log.info("mem new:   " + getCurrentMem() + " " + mem);

		final long l = getCurrentMem() - mem;
		assertTrue(l < 1500000);

	}

	/**
	 * test for copyXPathValue
	 */
	@Test
	public void testCopyXPathValue()
	{
		final KElement e1 = new XMLDoc("e1", null).getRoot();
		final KElement e1a = new XMLDoc("e1", null).getRoot();
		e1.setXPathValue("e2/e3", "txt");
		e1.copyXPathValue("e2/e4", null, "e2/e3");
		assertEquals(e1.getXPathAttribute("e2/e4", null), "txt");
		e1.setXPathValue("e2/e3/@fnarf", "att");
		e1.copyXPathValue("e2/e4/@fnarf", null, "e2/e3/@fnarf");
		assertEquals(e1.getXPathAttribute("e2/e4/@fnarf", null), "att");
		e1a.copyXPathValue("e2/e3", e1, null);
		assertEquals(e1a.getXPathAttribute("e2/e3", null), "txt");
	}

	/**
	 * test
	 */
	@Test
	public void testCopyElements()
	{
		final XMLDoc d = new XMLDoc("d1", null);
		final KElement e = d.getRoot();

		final XMLDoc d2 = new XMLDoc("d2", null);
		final KElement e2 = d2.getRoot();
		final VElement v = new VElement();
		v.add(e2.appendElement("a"));
		v.add(e2.appendElement("b"));
		e.copyElements(v, null);
		assertEquals(e.getLastChild().getLocalName(), "b");
		assertEquals(e.getFirstChild().getLocalName(), "a");
		final KElement last = e.appendElement("last");
		e.copyElements(v, last);
		assertEquals(e.getLastChild().getLocalName(), "last");
		assertEquals(e.getLastChild().getPreviousSibling().getLocalName(), "b");
		assertEquals(e.numChildElements(null, null), 5);

	}

	/**
	 * test
	 */
	@Test
	public void testCopyElementNS()
	{
		final XMLDoc d = new XMLDoc("d1", null);
		final KElement e = d.getRoot();
		final XMLDoc d2 = new XMLDoc("d2", null);
		final KElement e2 = d2.getRoot();
		e2.addNameSpace("foo", "www.foo.com");
		e2.setAttribute("foo:bar", "blub");
		final KElement e3 = e.copyElement(e2, null);
		assertNull(e3.getNamespaceURI());
		assertTrue(d.toString().indexOf("xmlns:foo=\"www.foo.com\"") > 0);
	}

	/**
	 *
	 */
	@Test
	public void testCopyAttribute()
	{
		final XMLDoc doc = new XMLDoc("Test", "www.test.com");
		final KElement root = doc.getRoot();
		final KElement a = root.appendElement("a");
		final KElement b = root.appendElement("b");
		a.setAttribute("att", "42");
		b.copyAttribute("att", a, null, null, null);
		assertEquals(a.getAttribute("att"), "42");
		assertEquals(b.getAttribute("att"), "42");
		b.copyAttribute("noThere", a, null, null, null);
		assertNull(b.getAttribute("noThere", null, null));
		assertNull(a.getAttribute("noThere", null, null));
		b.setAttribute("noThereA", "b");
		b.copyAttribute("noThereA", a, null, null, null);
		assertNull("the existing attribute was removed ", b.getAttribute("noThereA", null, null));
		assertNull(a.getAttribute("noThereA", null, null));

		a.setAttribute("foo", "a");
		b.copyAttribute("bar", a, "foo", null, null);
		assertEquals(b.getAttribute("bar"), "a");
		assertEquals(a.getAttribute("foo"), "a");
		assertNull(a.getAttribute("bar", null, null));
		assertNull(b.getAttribute("foo", null, null));

		// ns copy tests
		a.setAttribute("ns:aa", "A", "www.ns.com");
		a.setAttribute("ns:bb", "B", "www.ns.com");
		a.setAttribute("cc", "C", null);
		b.copyAttribute("ns:aa", a, null, null, "www.ns.com");
		assertEquals(b.getAttribute("ns:aa"), "A");
		assertEquals(b.getAttribute("aa", "www.ns.com", null), "A");
		b.copyAttribute("bb", a, null, null, "www.ns.com");
		assertEquals(b.getAttribute("ns:bb"), "B");
		assertEquals(b.getAttribute("bb", "www.ns.com", null), "B");
		b.copyAttribute("ns:cc", a, "cc", "www.ns.com", null);
		assertEquals(b.getAttribute("ns:cc"), "C");
		assertEquals(b.getAttribute("cc", "www.ns.com", null), "C");

		assertNull(b.copyAttribute("a", null));
	}

	/**
	 *
	 */
	@Test
	public void testCopyAttributeNS()
	{
		final KElement a = new XMLDoc("a", null).getRoot();
		a.setAttributeNS("www.foo.com", "foo:test", "bar");

		final KElement b = new XMLDoc("b", null).getRoot();
		b.copyAttribute("test", a, "test", null, "www.foo.com");
		assertEquals(b.getAttribute("foo:test"), "bar");

		assertNull(b.copyAttribute("a", null));
	}

	/**
	 *
	 */
	@Test
	public void testNameSpace()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final KElement root = doc.getRoot();

		root.setAttribute("xmlns", "http://www.CIP4.org/JDFSchema_1_1");

		final String docNS = "http://www.cip4.org/test/";
		final String myPrefix = "MyPrefix";

		// add the namespace, this is mandatory for java xerces (contrary to c++
		// xerces )
		root.addNameSpace(myPrefix, docNS);

		// add an element with a (predefined) prefix and no namespace
		final KElement kElem9 = root.appendElement(myPrefix + JDFConstants.COLON + "MyElementLevel_2", "");
		assertTrue(kElem9.getNamespaceURI().equals(docNS));
		assertTrue(kElem9.getPrefix().equals(myPrefix));

		final KElement kElem1 = root.appendElement("MyElementLevel_1", docNS);
		assertTrue(kElem1.getNamespaceURI().equals(docNS));

		// kElem1.setAttributeNS(docNS, myPrefix+":att", "attval");
		kElem1.setAttributeNS(docNS, "att1", "attval1");

		// add an element in a namespace
		final KElement kElem = root.appendElement(myPrefix + JDFConstants.COLON + "MyElement", docNS);
		assertTrue(kElem.getNamespaceURI().equals(docNS));
		assertTrue(kElem.getPrefix().equals(myPrefix));

		// add an attribute and its value in a namespace
		kElem.setAttributeNS(docNS, myPrefix + JDFConstants.COLON + "MyAttribute", "MyValue");

		// How to get the element, Version 1
		final KElement kElem2 = root.getElement_KElement("MyElement", docNS, 0);

		String attr = kElem2.getAttribute_KElement("MyAttribute", docNS, "MyDefault");
		assertTrue(attr.equals("MyValue"));

		// this is pretty invalid but the ns url takes precedence
		attr = kElem2.getAttribute_KElement(myPrefix + JDFConstants.COLON + "MyAttribute", docNS, "MyDefault");
		assertTrue(attr.equals("MyValue"));

		// this is even more invalid but the ns url takes precedence
		attr = kElem2.getAttribute_KElement("fnarf" + JDFConstants.COLON + "MyAttribute", docNS, "MyDefault");
		assertTrue(attr.equals("MyValue"));

		// How to get the element, Version 2
		final KElement kElem3 = root.getElement_KElement(myPrefix + JDFConstants.COLON + "MyElement", docNS, 0);

		attr = kElem3.getAttribute_KElement("MyAttribute", docNS, "MyDefault");
		assertTrue(attr.equals("MyValue"));

		attr = kElem3.getAttribute_KElement(myPrefix + JDFConstants.COLON + "MyAttribute", docNS, "MyDefault");
		assertTrue(attr.equals("MyValue"));

		final DocumentJDFImpl doc0 = (DocumentJDFImpl) root.getOwnerDocument();

		final Element newChild = doc0.createElementNS(docNS, myPrefix + JDFConstants.COLON + ElementName.RESOURCELINKPOOL);
		root.appendChild(newChild);

		doc.write2File(sm_dirTestDataTemp + "NameSpace.jdf", 0, true);
	}

	/**
	 *
	 */
	@Test
	public void testNameSpaceInElements()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final KElement root = doc.getRoot();

		final String cip4NameSpaceURI = root.getNamespaceURI(); // "http://www.CIP4.org/JDFSchema_1_1"
		// ;
		assertEquals(cip4NameSpaceURI, JDFConstants.JDFNAMESPACE);

		// adding cip4NameSpaceURI a second time as default namespace is ignored
		// (using addNameSpace or setAttribute)
		root.addNameSpace(JDFConstants.EMPTYSTRING, cip4NameSpaceURI);
		root.setAttribute(JDFConstants.XMLNS, cip4NameSpaceURI);

		// adding cip4NameSpaceURI with different prefixes using addNameSpace is
		// ignored
		final String cip4Prefix1 = "JDF";
		final String cip4Prefix2 = "jdf";
		final String cip4Prefix3 = "JDFS";
		root.addNameSpace(cip4Prefix1, cip4NameSpaceURI);
		root.addNameSpace(cip4Prefix2, cip4NameSpaceURI);
		root.addNameSpace(cip4Prefix3, cip4NameSpaceURI);

		// adding cip4NameSpaceURI with different prefixes using setAttribute is
		// allowed
		root.setAttribute(JDFConstants.XMLNS + JDFConstants.COLON + cip4Prefix1, cip4NameSpaceURI);
		root.setAttribute(JDFConstants.XMLNS + JDFConstants.COLON + cip4Prefix2, cip4NameSpaceURI);
		root.setAttribute(JDFConstants.XMLNS + JDFConstants.COLON + cip4Prefix3, cip4NameSpaceURI);

		// append an element without prefix with null NameSpaceURI or
		// cip4NameSpaceURI
		final KElement kElement0 = root.appendElement("kElement0", null);
		assertTrue(kElement0.getNamespaceURI().equals(cip4NameSpaceURI));
		assertNull(kElement0.getPrefix());

		final KElement kElement1 = root.appendElement("kElement1", cip4NameSpaceURI);
		assertTrue(kElement1.getNamespaceURI().equals(cip4NameSpaceURI));
		assertNull(kElement1.getPrefix());

		// append an element with prefix with null NameSpaceURI or
		// cip4NameSpaceURI
		final KElement kElement2 = root.appendElement(cip4Prefix1 + JDFConstants.COLON + "kElement2", null);
		assertTrue(kElement2.getNamespaceURI().equals(cip4NameSpaceURI));
		assertTrue(kElement2.getPrefix().equals(cip4Prefix1));

		final KElement kElement3 = root.appendElement(cip4Prefix1 + JDFConstants.COLON + "kElement3", cip4NameSpaceURI);
		assertTrue(kElement3.getNamespaceURI().equals(cip4NameSpaceURI));
		assertTrue(kElement3.getPrefix().equals(cip4Prefix1));

		final String jdfDocString = "<JDF ID=\"n051221_021145422_000005\" Version=\"1.3\" " + "xmlns=\"http://www.CIP4.org/JDFSchema_1_1\" "
				+ "xmlns:JDF=\"http://www.CIP4.org/JDFSchema_1_1\" " + "xmlns:JDFS=\"http://www.CIP4.org/JDFSchema_1_1\" " + "xmlns:jdf=\"http://www.CIP4.org/JDFSchema_1_1\">"
				+ "<kElement0/>" + "<JDF:kElement1/>" + "<JDFS:kElement2/>" + "<jdf:kElement3/>" + "</JDF>";

		final JDFParser p = new JDFParser();
		final JDFDoc jdfDoc = p.parseString(jdfDocString);
		final KElement root1 = jdfDoc.getRoot();

		// How to get the element, uri = null or cip4NameSpaceURI

		// empty prefix is ok
		final KElement kElemGet1 = root1.getElement("kElement1", null, 0);
		final KElement kElemGet2 = root1.getElement("kElement1", cip4NameSpaceURI, 0);
		assertEquals(kElemGet1, kElemGet2);

		// correct prefix is ok
		final KElement kElemGet3 = root1.getElement(cip4Prefix1 + JDFConstants.COLON + "kElement1", null, 0);
		final KElement kElemGet4 = root1.getElement(cip4Prefix1 + JDFConstants.COLON + "kElement1", cip4NameSpaceURI, 0);
		assertEquals(kElemGet3, kElemGet4);
		assertEquals(kElemGet2, kElemGet4);

		// wrong prefix
		final KElement kElemGet5 = root1.getElement(cip4Prefix2 + JDFConstants.COLON + "kElement1", null, 0);
		final KElement kElemGet6 = root1.getElement(cip4Prefix2 + JDFConstants.COLON + "kElement1", cip4NameSpaceURI, 0);
		assertNull(kElemGet5);
		assertNull(kElemGet6);
	}

	/**
	 *
	 */
	@Test
	public void testNameSpace1()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFElement root = (JDFElement) doc.getRoot();

		final String docNS1 = "www1";
		final String docNS2 = "www2";
		final String myPrefix = "HDM";

		try
		{
			// add an element in a namespace
			final KElement kElem1 = root.appendElement(myPrefix + JDFConstants.COLON + "Foo_1", docNS1);
			assertTrue(kElem1.getNamespaceURI().equals(docNS1));
			assertTrue(kElem1.getPrefix().equals(myPrefix));

			kElem1.setAttribute(myPrefix + JDFConstants.COLON + "Foo_1", "attval1", docNS1);
			kElem1.setAttribute(myPrefix + JDFConstants.COLON + "Foo_2", "attval2", docNS2);
			fail("Called KElement.setAttribute with same prefix but different namespaces ?!");
		}
		catch (final JDFException expected)
		{
			final String partOfErrorMessage = "KElement.setAttribute:";
			assertTrue("Exception message doesn't even mention '" + partOfErrorMessage + "'?!", expected.getMessage().indexOf(partOfErrorMessage) >= 0);
		}
	}

	/**
	 *
	 */
	@Test
	public void testNameSpaceInAttributes()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFElement root = (JDFElement) doc.getRoot();
		root.addNameSpace("foo", "www.foo.com");
		assertEquals("ns", root.getAttribute("xmlns:foo"), "www.foo.com");
		final KElement child = root.appendElement("abc");
		child.addNameSpace("foo", "www.foo.com");
		assertFalse("ns 2", child.hasAttribute("xmlns:foo"));

		child.setAttribute("foo:bar", "a1");
		assertEquals("dom1", child.getAttribute("foo:bar"), "a1");
		child.setAttribute("foo:bar", "a2", "www.foo.com");
		child.setAttribute("foo:barNs", "ns", "www.foo.com");
		assertEquals("dom1", child.getAttribute("foo:bar"), "a2");
		assertEquals("dom2", child.getAttribute("bar", "www.foo.com", null), "a2");
		child.setAttribute("foo:bar", "a3");
		assertEquals("dom1", child.getAttribute("foo:bar"), "a3");
		assertEquals("dom2", child.getAttribute("bar", "www.foo.com", null), "a3");
		child.setAttribute("bar:bar", "b3", "www.bar.com");
		assertEquals("dom1", child.getAttribute("bar:bar"), "b3");
		assertEquals("dom2", child.getAttribute("bar", "www.bar.com", null), "b3");
		child.setAttribute("bar:bar", "b2");
		assertEquals("dom1", child.getAttribute("bar:bar"), "b2");
		assertEquals("dom2", child.getAttribute("bar", "www.bar.com", null), "b2");
		child.setAttribute("bar:bar", "b4", "www.bar.com");
		assertEquals("dom1", child.getAttribute("bar:bar"), "b4");
		assertEquals("dom2", child.getAttribute("bar", "www.bar.com", null), "b4");
	}

	// /////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetPrefix()
	{
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode myRoot = (JDFNode) jdfDoc.getRoot();
		myRoot.addNameSpace("foo", "www.foo.com");
		final KElement e = myRoot.appendElement("foo:bar", null);
		assertEquals(e.getPrefix(), "foo");
		myRoot.removeAttribute("xmlns:foo");
		assertEquals(e.getPrefix(), "foo");
		myRoot.setAttribute("xmlns:blub", "diewupp");
		final KElement blub = myRoot.appendElement("blub:diewupp");
		assertEquals(blub.getPrefix(), "blub");

	}

	/**
	 *
	 *
	 */
	public void testGetLength()
	{
		final KElement e = new XMLDoc("e", null).getRoot();
		e.appendElement("c");
		assertTrue(e.getLength() > 0);
	}

	/**
	 *
	 */
	@Test
	public void testGetLocalName()
	{
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode myRoot = (JDFNode) jdfDoc.getRoot();
		final String docNS = "http://www.cip4.org/test/";
		final String myPrefix = "MyPrefix";

		// append an element with a different default namespace
		myRoot.appendElement("Foo", docNS);
		// get your element back
		KElement k = myRoot.getElement_JDFElement("Foo", "", 0);
		// test your method on this element
		String s = k.getLocalName();
		// gotcha First test ready
		assertEquals("LocalName 'Foo' is not equal the original written name", s, "Foo");

		// add the namespace, this is mandatory for java xerces (contrary to c++
		// xerces )
		myRoot.addNameSpace(myPrefix, docNS);

		// append another element with a prefix, namespace is equal to default
		// namespace
		myRoot.appendElement(myPrefix + ":Faa", null);
		// get your element back
		k = myRoot.getElement_JDFElement("Faa", null, 0);
		// no null pointer handling...this is a test. The element HAS TO be
		// there...otherwise test failed
		s = k.getLocalName();
		//
		assertEquals("LocalName 'Faa' is not equal the original written name", s, "Faa");

		jdfDoc.write2File(sm_dirTestDataTemp + "GetLocalNameStatic.jdf", 0, true);
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * tests whether the correct virtual call hierarch is followed in getCreateElement
	 */
	@Test
	public void testGetCreateElement()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(JDFNode.EnumType.Imposition.getName(), false);
		final JDFRunList rl = (JDFRunList) root.appendMatchingResource(ElementName.RUNLIST, JDFNode.EnumProcessUsage.Document, null);
		rl.appendLayoutElement();
		final JDFRunList leaf = (JDFRunList) rl.getCreatePartition(JDFResource.EnumPartIDKey.Run, "Run1", new VString(JDFResource.EnumPartIDKey.Run.getName(), " "));

		final KElement el1 = rl.getCreateElement_KElement(ElementName.LAYOUTELEMENT, null, 0);
		final KElement el2 = leaf.getCreateElement_KElement(ElementName.LAYOUTELEMENT, null, 0);
		assertNotSame(el1, el2);
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetMatchesPath()
	{
		final XMLDoc d = new XMLDoc("a", null);
		final KElement r = d.getRoot();
		final KElement b = r.getCreateXPathElement("b/c/d");
		assertTrue(b.matchesPath("d", true));
		assertTrue(b.matchesPath("c/d", true));
		assertTrue(b.matchesPath("/a/b/c/d", true));
		assertTrue(b.matchesPath("a/b/c/d", true));
		assertFalse(b.matchesPath("a/a/b/c/d", true));
	}

	/**
	 *
	 */
	@Test
	public void testGetDefaultAttributeMap()
	{
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();
		final JDFAttributeMap defs = root.getDefaultAttributeMap();
		assertEquals("Template is defaulted", defs.get("Template"), "false");
		assertNull("ID is not defaulted", defs.get("ID"));
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testEraseDefaultAttributeMap()
	{
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();
		root.setTemplate(false);
		assertTrue(root.hasAttribute(AttributeName.TEMPLATE));
		root.eraseDefaultAttributes(true);
		assertFalse("Template is defaulted", root.hasAttribute(AttributeName.TEMPLATE));
		assertTrue("ID is not defaulted", root.hasAttribute(AttributeName.ID));
	}

	// /////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetAttributeMap()
	{
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();
		assertFalse("Template is defaulted", root.getAttributeMap().containsKey("Template"));
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testEraseEmptyNodes()
	{
		final JDFParser p = new JDFParser();
		final String inFile = sm_dirTestData + File.separator + "bigWhite.jdf";
		final JDFDoc jdfDoc = p.parseFile(inFile);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();
		root.eraseEmptyNodes(true);
		final String outFile = sm_dirTestDataTemp + File.separator + "SmallWhite.jdf";
		jdfDoc.write2File(outFile, 0, false);
		final File f = new File(inFile);
		final File f2 = new File(outFile);
		assertTrue(f.length() > 1.1 * f2.length());
	}

	/**
	 *
	 */
	@Test
	public void testRemoveXPathElement()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		root.setXPathAttribute("a/b[2]/@att", "foo");
		assertEquals(root.getXPathAttribute("a/b[2]/@att", null), "foo");
		root.removeXPathElement("a/b[2]");
		assertNull(root.getXPathElement("a/b[2]"));
		assertNotNull(root.getXPathElement("a/b[1]"));
	}

	/**
	 *
	 */
	@Test
	public void testRemoveXPathValue()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		root.setXPathValue("a/b[2]/att", "foo");
		assertEquals(root.getXPathAttribute("a/b[2]/att", null), "foo");
		root.removeXPathAttribute("a/b[2]/att");
		assertNotNull(root.getXPathElement("a/b[2]/att"));
		assertNull(root.getXPathAttribute("a/b[2]/att", null));
	}

	/**
	 *
	 */
	@Test
	public void testRemoveXPathAttribute2()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		root.setXPathAttribute("a/b[2]/@att", "foo");
		assertEquals(root.getXPathAttribute("a/b[2]/@att", null), "foo");
		assertEquals(root.getXPathAttribute("a/b[@att=\"foo\"]/@att", null), "foo");
		assertTrue(root.hasXPathNode("a/b[@att=\"foo\"]/@att"));
		assertTrue(root.hasXPathNode("a/b[@att=\"foo\"]"));
		root.removeXPathAttribute("a/b[2]/@att");
		assertFalse(root.hasXPathNode("a/b[@att=\"foo\"]/@att"));
		assertFalse(root.hasXPathNode("a/b[@att=\"foo\"]"));
		assertTrue(root.hasXPathNode("a/b[2]"));
		root.setXPathAttribute("a/b[2]/@att", "foo");
		root.removeXPathAttribute("a/b[@att=\"foo\"]/@att");
		assertFalse(root.hasXPathNode("a/b[@att=\"foo\"]/@att"));
		assertFalse(root.hasXPathNode("a/b[@att=\"foo\"]"));
		assertTrue(root.hasXPathNode("a/b[2]"));
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetXPathAttributeMap()
	{
		final XMLDoc jdfDoc = new XMLDoc("a", null);
		final KElement root = jdfDoc.getRoot();
		root.setXPathAttribute("b/c[3]/d/@foo", "bar3");
		root.setXPathAttribute("b/c[5]/d/@foo", "bar5");
		Map<String, String> m = root.getXPathAttributeMap("//*/@foo");
		assertEquals(m.size(), 2);
		m = root.getXPathAttributeMap("//@foo");
		assertEquals(m.size(), 2);
		assertEquals(root.getXPathAttribute("b/c[3]/d/@foo", null), "bar3");
		assertEquals(root.getXPathAttribute("b/c[5]/d/@foo", null), "bar5");
	}

	/**
	 *
	 */
	@Test
	public void testGetXPathAttributeMapNull()
	{
		final XMLDoc jdfDoc = new XMLDoc("a", null);
		final KElement root = jdfDoc.getRoot();
		root.setXPathAttribute("b/c[3]/d/@foo", "bar3");
		root.setXPathAttribute("b/c[5]/d/@foo", "bar5");
		root.setAttribute("test", "it");
		final Map<String, String> m = root.getXPathAttributeMap(null);
		assertEquals(m.size(), 3);
		assertEquals(root.getXPathAttribute("@test", null), "it");
		assertEquals(root.getXPathAttribute("b/c[3]/d/@foo", null), "bar3");
		assertEquals(root.getXPathAttribute("b/c[5]/d/@foo", null), "bar5");
	}

	/**
	 *
	 */
	@Test
	public void testGetXPathValueMapText()
	{
		final XMLDoc jdfDoc = new XMLDoc("a", null);
		final KElement root = jdfDoc.getRoot();
		root.setText("foo");
		final Map<String, String> m = root.getXPathValueMap();
		assertEquals(m.get("/a"), "foo");
	}

	/**
	 *
	 */
	@Test
	public void testGetXPathValueMapPerformance()
	{
		final CPUTimer ct = new CPUTimer(false);
		final JDFDoc d = new JDFParser().parseFile(sm_dirTestData + "bigWhite.jdf");

		for (int i = 0; i < 5; i++)
		{
			ct.start();
			d.getRoot().getXPathValueMap();
			log.info(i + " " + ct.getSingleSummary());
			ct.stop();
		}
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetXPathElementVector()
	{
		final XMLDoc jdfDoc = new XMLDoc("a", null);
		final KElement root = jdfDoc.getRoot();
		final VElement va = new VElement();
		va.add(root);
		assertEquals(va, root.getXPathElementVector("//a", 0));
		assertEquals(va, root.getXPathElementVector("/a", 0));
		assertEquals(va, root.getXPathElementVector(".", 0));
		va.clear();
		va.add(root.appendElement("b"));
		va.add(root.appendElement("b"));
		assertEquals(va, root.getXPathElementVector("b", 0));
		assertEquals(va, root.getXPathElementVector("//b", 0));
		va.clear();
		va.add(root.getCreateXPathElement("./b/c"));
		va.add(root.getCreateXPathElement("./c"));
		root.getCreateXPathElement("./c/d");
		assertEquals(va, root.getXPathElementVector("//c", 0));
		root.getCreateXPathElement("./c/d");
		assertEquals(va, root.getXPathElementVector("//c", 0));
		assertEquals(1, root.getXPathElementVector("//d", 0).size());
		assertEquals(root.getXPathElementVector("//d", 0), root.getXPathElementVector("//c/d", 0));
		assertTrue(root.getXPathElementVector("//*", 0).contains(va.elementAt(0)));
		assertTrue(root.getXPathElementVector("//*", 0).contains(root));
		root.getCreateXPathElement("./c/d[2]");
		assertEquals(2, root.getXPathElementVector("//d", 0).size());
		assertEquals(2, root.getXPathElementVector("/a/c/d", 0).size());
		assertEquals("d", root.getXPathElementVector("/a/c/d", 0).elementAt(0).getNodeName());
		assertEquals(2, root.getXPathElementVector("/*/c/d", 0).size());
		assertEquals("d", root.getXPathElementVector("/*/c/d", 0).elementAt(0).getNodeName());
	}

	/**
	 *
	 */
	@Test
	public void testGetXPathElementNS()
	{
		final XMLDoc d = new XMLDoc("a:A", "www.a.com");
		final KElement root = d.getRoot();
		root.setXPathAttribute("a:B/a:C/@a:Att", "val");
		root.appendElement("b:BB", "www.b.com");
		assertEquals(root.getXPathElement("B/C"), root.getXPathElement("a:B/a:C"));
		assertEquals(root.getXPathElement("b:BB"), root.getXPathElement("BB"));
		assertEquals("val", root.getXPathAttribute("B/C/@a:Att", null));
	}

	/**
	 *
	 */
	@Test
	public void testGetXPathElement()
	{
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();

		root.setXPathAttribute("/JDF/a[2]/@foo", "v2");
		root.setXPathAttribute("/JDF/a[3]/@foo", "v3");
		assertEquals(root.getXPathElement("/JDF/a[2]"), root.getXPathElement("/JDF/a[@foo=\"v2\"]"));
		assertEquals(root.getXPathElement("/JDF/a[3]"), root.getXPathElement("/JDF/a[@foo=\"v3\"]"));

		String nodeName = "Created";
		KElement kElem = root.getXPathElement("AuditPool/" + nodeName);
		assertEquals(kElem.getNodeName(), nodeName);
		assertTrue(kElem.matchesPath("Created", false));
		assertTrue(kElem.matchesPath("/JDF/AuditPool/Created", false));
		assertTrue(kElem.matchesPath("JDF/AuditPool/Created", false));
		assertFalse(kElem.matchesPath("/Created", false));

		nodeName = "notFound";
		kElem = root.getXPathElement("AuditPool/" + nodeName);
		assertNull(kElem);
		final XMLDoc d2 = new XMLDoc("doc", null);
		final KElement root2 = d2.getRoot();
		for (int i = 0; i < 10; i++)
		{
			final KElement e = root2.appendElement("e");
			assertEquals(root2.getXPathElement("e[" + (i + 1) + "]"), e);
			assertEquals(root2, e.getXPathElement("../"));
			assertEquals(root2, e.getXPathElement(".."));
			assertEquals(root2, e.getXPathElement(".././."));
		}
		final KElement e = root2.getCreateElement("foo.bar");
		assertEquals(e.getNodeName(), "foo.bar");
		assertEquals(root2.getXPathElement("foo.bar"), e);
		assertEquals(root2.getCreateXPathElement("foo.bar"), e);
		root.setXPathAttribute("/JDF/ee[2]/@a", "2");
		root.setXPathAttribute("/JDF/ee[1]/@a", "2");
		root.setXPathAttribute("/JDF/ee[2]/ff/@b", "3");
		assertEquals(root.getXPathAttribute("/JDF/ee/ff/@b", null), "3");
		assertEquals(root.getXPathAttribute("/JDF/ee[@a=\"2\"]/ff/@b", null), "3");
		assertEquals(root.getXPathAttribute("//ee[@a=\"2\"]/ff/@b", null), "3");
		assertNull(root.getXPathAttribute("/JDF/ee[1]/ff/@b", null));

	}

	/**
	 *
	 */
	@Test
	public void testGetCreateXPathElement()
	{
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();

		String nodeName = "Created";
		KElement kElem = root.getCreateXPathElement("AuditPool/" + nodeName);
		assertTrue("", kElem != null && kElem.getNodeName().equals(nodeName));

		nodeName = "newElement";
		kElem = root.getCreateXPathElement("AuditPool/" + nodeName);
		assertTrue("", kElem != null && kElem.getNodeName().equals(nodeName));

		final KElement e = root.getCreateXPathElement("./foo/bar[2]/fnarf[3]");
		assertNotNull("e", e);
		assertEquals("", e, root.getCreateXPathElement("./foo/bar[2]/fnarf[3]"));
		assertEquals("", e, root.getXPathElement("./foo/bar[2]/fnarf[3]"));
		root.setXPathAttribute("./foo/bar[2]@blub", "b1");

		assertEquals("1 foo", root.numChildElements("foo", null), 1);
		assertNotNull("get", root.getXPathElement("./foo/bar[2]/fnarf[3]"));
		assertEquals("", root.getElement("foo").numChildElements("bar", null), 2);
		assertEquals("", root.getElement("foo").getElement("bar").numChildElements("fnarf", null), 0);
		assertEquals("", root.getElement("foo").getElement("bar").getNextSiblingElement("bar", null).numChildElements("fnarf", null), 3);
		assertEquals("", root.getCreateXPathElement("."), root);

		assertEquals("", e, root.getXPathElement("./foo/bar[@blub=\"b1\"]/fnarf[3]"));
		assertEquals("", e, root.getCreateXPathElement("./foo/bar[@blub=\"b1\"]/fnarf[3]"));
		assertNotSame("", e, root.getCreateXPathElement("./foo/bar[@blub=\"b1\"]/fnarf[5]"));
		assertEquals("", root.getElement("foo").getElement("bar").numChildElements("fnarf", null), 0);
		assertEquals("", root.getElement("foo").getElement("bar").getNextSiblingElement("bar", null).numChildElements("fnarf", null), 5);
		assertNotNull("create by attribute value now implemented", root.getCreateXPathElement("./foo/bar[@blub=\"b1\"]/fnarf[@a=\"b\"]"));
		assertEquals("getCreate actually sets the attribute", "blahblah", root.getCreateXPathElement("./foo/bar[@blub=\"blahblah\"]").getAttribute("blub"));
		assertEquals("getCreate actually sets the attribute", "blahblah", root.getCreateXPathElement("./foo/bar[gaga/@blub=\"blahblah\"]").getXPathAttribute("gaga/@blub", null));
	}

	/**
	 *
	 */
	@Test
	public void testBuildXPath()
	{
		final XMLDoc d = new XMLDoc("d", null);
		final KElement root = d.getRoot();
		assertEquals(root.buildXPath(null, 1), "/d");
		assertEquals(root.buildXPath("/d", 1), ".");
		assertEquals(root.buildXPath(null, 0), "/d");
		assertEquals(root.buildXPath("/d", 0), ".");
		root.appendElement("e");
		final KElement e = root.appendElement("e");
		e.setAttribute("ID", "i");
		root.setAttribute("ID", "r");
		assertEquals(e.buildXPath(null, 1), "/d/e[2]");
		assertEquals(e.buildXPath(null, 3), "/d[@ID=\"r\"]/e[@ID=\"i\"]");
		assertEquals(e.buildXPath("/d", 3), "./e[@ID=\"i\"]");
		assertEquals(e.buildXPath(null, 0), "/d/e");
		assertEquals(e.buildXPath("/d", 1), "./e[2]");
		assertEquals(e.buildXPath("/d", 1), e.buildXPath("/d", 2));
		assertEquals(e.buildXPath("/d", 0), "./e");

	}

	/**
	 *
	 */
	@Test
	public void testBuildRelativeXPath()
	{
		final XMLDoc d = new XMLDoc("d", null);
		final KElement root = d.getRoot();
		assertEquals(root.buildRelativeXPath(root, 1), ".");
		assertEquals(root.buildRelativeXPath(null, 0), "/d");
		root.appendElement("e");
		final KElement e = root.appendElement("e");
		e.setAttribute("ID", "i");
		root.setAttribute("ID", "r");
		assertEquals(e.buildRelativeXPath(null, 1), "/d/e[2]");
		assertEquals(e.buildRelativeXPath(null, 3), "/d[@ID=\"r\"]/e[@ID=\"i\"]");
		assertEquals(e.buildRelativeXPath(root, 3), "./e[@ID=\"i\"]");
		assertEquals(e.buildRelativeXPath(null, 0), "/d/e");
		assertEquals(e.buildRelativeXPath(root, 1), "./e[2]");
		assertEquals(e.buildRelativeXPath(root, 1), e.buildRelativeXPath(root, 2));
		assertEquals(e.buildRelativeXPath(root, 0), "./e");

	}

	/**
	 *
	 */
	@Test
	public void testGetReal()
	{
		final KElement e = new XMLParser().parseString("<foo bar=\".42\"/>").getRoot();
		assertEquals(e.getRealAttribute("bar", null, 0.0), 0.42, 0.1);
	}

	/**
	 * make sure we also get all valid deep elements
	 */
	@Test
	public void testGetTree()
	{
		final KElement e = new XMLParser().parseString("<a/>").getRoot();
		final KElement a1 = e.appendElement("a");
		final KElement aa = a1.appendElement("a");
		final KElement b = a1.appendElement("b");
		final KElement ab = b.appendElement("a");
		aa.setAttribute("b", "c");
		ab.setAttribute("b", "c");
		final VElement tree = e.getTree("a", null, new JDFAttributeMap("b", "c"), false, false);
		assertTrue(tree.contains(aa));
		assertFalse(tree.contains(a1));
		assertFalse(tree.contains(ab));
	}

	/**
	 *
	 */
	@Test
	public void testGetXPathAttribute()
	{
		JDFAudit.setStaticAgentName("foo Agent");
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();
		root.setID("rootID");
		assertEquals(root.getXPathAttribute("@ID", null), "rootID");

		final String nodeName = "Created";
		String attribute = "AgentName";
		String attValue = root.getXPathAttribute("AuditPool/" + nodeName + "@" + attribute, "dummydefault");
		assertEquals(attValue, JDFAudit.getStaticAgentName());

		attribute = "notExistingAttribute";
		attValue = root.getXPathAttribute("AuditPool/" + nodeName + "@" + attribute, "dummydefault");
		assertEquals(attValue, "dummydefault");
		assertNull(root.getXPathAttribute("AuditPool/" + nodeName + "@" + attribute, null));
		root.setXPathAttribute("foo/bar[2]/@a", "b2");
		root.setXPathAttribute("foo/bar[2]/sub/@c", "d2");
		root.setXPathValue("b/c[5]/d", "txt");
		assertEquals(root.getXPathAttribute("foo/bar[@a=\"b2\"]/sub/@c", null), "d2");
		assertEquals(root.getXPathAttribute("b/c[5]/d", null), "txt");
		try
		{
			root.getXPathAttribute("foo/bar[0]/sub/@c", null);
			fail("index must be >0");
		}
		catch (final IllegalArgumentException x)
		{
			// nop
		}
	}

	/**
	 *
	 */
	@Test
	public void testGetXPathAttributeText()
	{
		JDFAudit.setStaticAgentName("foo Agent");
		final KElement e = KElement.createRoot("foo", null);
		e.appendElement("a");
		assertNull(e.getXPathAttribute("a", null));
		assertNull(e.getXPathAttribute("b", null));
		assertEquals("", e.getXPathAttribute("a", ""));
		assertEquals("", e.getXPathAttribute("b", ""));
	}

	/**
	 *
	 */
	@Test
	public void testGetXPathAttributeWithPath()
	{
		JDFAudit.setStaticAgentName("foo Agent");
		final KElement root = new XMLDoc("a", null).getRoot();
		root.getCreateXPathElement("b[1]/c");
		root.getCreateXPathElement("b[5]/c");
		final KElement c = root.getCreateXPathElement("b[3]/c");
		root.setXPathAttribute("b[3]/part/@foo", "bar");
		assertEquals(root.getXPathElement("b[part/@foo=\"bar\"]/c"), c);
		assertEquals(root.getXPathElement("b[./part/@foo=\"bar\"]"), c.getParentNode_KElement());
		assertEquals(root.getXPathElement("b[part/@foo=\"bar\"]"), c.getParentNode_KElement());
	}

	/**
	 *
	 */
	@Test
	public void testGetDOMAttr()
	{
		final XMLDoc xd = new XMLDoc("a", null);
		final KElement root = xd.getRoot();
		root.setAttribute("at", "b");
		assertEquals("", root.getAttribute("at"), root.getDOMAttr("at", null, false).getNodeValue());
		final KElement child = root.appendElement("child");
		assertNull("", child.getDOMAttr("at", null, false));
		assertNull("", child.getDOMAttr("at_notther", null, true));
		assertNotNull("", child.getDOMAttr("at", null, true));
	}

	/**
	 *
	 */
	@Test
	public void testGetAttribute()
	{
		final XMLDoc xd = new XMLDoc("a", null);
		final KElement root = xd.getRoot();
		root.setAttribute("at", "b");
		assertEquals("b", root.getAttribute("at"));
		assertEquals("", root.getAttribute("at2"));
	}

	/**
	 *
	 */
	@Test
	public void testGetNonEmpty()
	{
		final XMLDoc xd = new XMLDoc("a", null);
		final KElement root = xd.getRoot();
		root.setAttribute("at", "b");
		assertEquals("b", root.getNonEmpty("at"));
		assertNull(root.getNonEmpty("at2"));
	}

	/**
	 *
	 */
	@Test
	public void testGetNonEmpty_KElement()
	{
		final XMLDoc xd = new XMLDoc("a", null);
		final KElement root = xd.getRoot();
		root.setAttribute("at", "b");
		assertEquals("b", root.getNonEmpty_KElement("at"));
		assertNull(root.getNonEmpty_KElement("at2"));
	}

	/**
	 *
	 */
	@Test
	public void testHasNonEmpty()
	{
		final XMLDoc xd = new XMLDoc("a", null);
		final KElement root = xd.getRoot();
		root.setAttribute("at", "b");
		assertTrue(root.hasNonEmpty("at"));
		assertFalse(root.hasNonEmpty("at2"));
	}

	/**
	*
	*/
	@Test
	public void testHasNonEmpty_KElement()
	{
		final XMLDoc xd = new XMLDoc("a", null);
		final KElement root = xd.getRoot();
		root.setAttribute("at", "b");
		assertTrue(root.hasNonEmpty_KElement("at"));
		assertFalse(root.hasNonEmpty_KElement("at2"));
	}

	/**
	 *
	 */
	@Test
	public void testRemoveXPathAttribute()
	{
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();

		final String nodeName = "Created";
		String attribute = "Author";
		root.removeXPathAttribute("AuditPool/" + nodeName + "@" + attribute);
		String attValue = root.getXPathAttribute("AuditPool/" + nodeName + "@" + attribute, null);
		assertNull("", attValue);

		attribute = "notExistingAttribute";
		root.removeXPathAttribute("AuditPool/" + nodeName + "@" + attribute);
		attValue = root.getXPathAttribute("AuditPool/" + nodeName + "@" + attribute, "dummydefault");
		assertTrue("", attValue.equals("dummydefault"));
	}

	/**
	 *
	 */
	@Test
	public void testSetXPathAttributeReplace()
	{
		final KElement e = new XMLDoc("foo", null).getRoot();
		e.setXPathAttribute("a[@a=\"b\"]/c[@d=\"e\"]/@f", "g");
		final String s1 = e.toXML();
		e.setXPathAttribute("a[@a=\"b\"]/c[@d=\"e\"]/@f", "g");
		e.setXPathAttribute("a[@a=\"b\"]/c[@d=\"e\"]/@f", "h");
		e.setXPathAttribute("a[@a=\"b\"]/c[@d=\"e\"]/@f", "g");
		final String s2 = e.toXML();
		assertEquals(s1, s2);

	}

	/**
	 *
	 */
	@Test
	public void testSetXPathAttribute()
	{
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();

		final String nodeName = "Created";
		final String attribute = "Author";
		root.setXPathAttribute("AuditPool/" + nodeName + "@" + attribute, "newAttributeValue");
		root.setXPathAttribute("new/@foo", "bar");
		assertEquals("", root.getXPathAttribute("new/@foo", null), "bar");
		assertEquals("", root.getXPathAttribute("new@foo", null), "bar");
		root.setXPathAttribute("new@foo2", "bar2");
		assertEquals("", root.getXPathAttribute("new/@foo2", null), "bar2");
		assertEquals("", root.getXPathAttribute("new@foo2", null), "bar2");
		final String attValue = root.getXPathAttribute("AuditPool/" + nodeName + "@" + attribute, "dummydefault");
		assertEquals("", attValue, "newAttributeValue");

		for (int i = 0; i < 3; i++)
		{
			root.appendElement("Foo3");
		}
		root.setXPathAttribute("Foo3/@bar2", "bb");
		root.setXPathAttribute("Foo3[1]/@bar3", "cc");
		for (int i = 0; i < 3; i++)
		{
			assertEquals(root.getElement("Foo3", null, i).getAttribute("bar2"), "bb");
			if (i == 0)
			{
				assertEquals(root.getElement("Foo3", null, i).getAttribute("bar3"), "cc");
			}
			else
			{
				assertEquals(root.getElement("Foo3", null, i).getAttribute("bar3"), "");
			}
		}
		root.setXPathAttribute("Foo3/@bar2", "bb");

		root.setXPathAttribute("ParameterSet[@Name=\"foooo\"]/Parameter/foooo/@bar", "fnarf");
		final KElement fooo = root.getChildByTagName("foooo", null, 0, null, false, false);
		assertEquals("fnarf", fooo.getAttribute("bar"));
	}

	/**
	 *
	 */
	@Test
	public void testSetXPathValue()
	{
		final XMLDoc doc = new XMLDoc("root", null);
		final KElement root = doc.getRoot();
		root.setXPathValue("foo/bar", "snafu");
		assertEquals(root.getXPathElement("foo/bar").getText(), "snafu");
		root.setXPathValue("foo/bar/@c", "d");
		assertEquals(root.getXPathAttribute("foo/bar/@c", null), "d");
	}

	/**
	 *
	 */
	@Test
	public void testSetXPathValueText()
	{
		final XMLDoc doc = new XMLDoc("root", null);
		final KElement root = doc.getRoot();
		root.setXPathValue("foo/bar/@aa", "a");
		assertEquals(root.getXPathAttribute("foo/bar/@aa", null), "a");
		root.setXPathValue("foo[@ID=\"1\"]/bar", "snafu");
		assertEquals(root.getXPathElement("foo[@ID=\"1\"]/bar").getText(), "snafu");
		root.setXPathValue("foo[@ID=\"2\"]/bar", "snafu2");
		assertEquals(root.getXPathElement("foo[@ID=\"2\"]/bar").getText(), "snafu2");
		root.setXPathValue("foo[@ID=\"2\"]/@bar", "bb");
		assertEquals(root.getXPathAttribute("foo[@ID=\"2\"]/@bar", null), "bb");
		root.setXPathValue("foo/bar/@c", "d");
		assertEquals(root.getXPathAttribute("foo/bar/@c", null), "d");
	}

	/**
	 *
	 */
	@Test
	public void testSetXPathValues()
	{
		final XMLDoc doc = new XMLDoc("root", null);
		final KElement root = doc.getRoot();
		final JDFAttributeMap map = new JDFAttributeMap();
		map.put("foo[@ID=\"1\"]/bar", "snafu");
		map.put("foo[@ID=\"2\"]/bar", "snafu2");
		map.put("foo[@ID=\"2\"]/@bar", "bb");
		map.put("foo/bar2/@c", "d");
		root.setXPathValues(map);
		assertEquals(root.getXPathAttribute("foo/bar2/@c", null), "d");
		assertEquals(root.getXPathElement("foo[@ID=\"1\"]/bar").getText(), "snafu");
		assertEquals(root.getXPathElement("foo[@ID=\"2\"]/bar").getText(), "snafu2");
		assertEquals(root.getXPathAttribute("foo[@ID=\"2\"]/@bar", null), "bb");
	}

	/**
	 * Method testGetDeepParentChild.
	 *
	 */
	@Test
	public void testGetDeepParent()
	{
		final XMLDoc jdfDoc = new XMLDoc("Test", "www.test.com");
		final KElement e = jdfDoc.getRoot();
		final KElement foo = e.appendElement("foo");
		final KElement bar = foo.appendElement("bar");
		assertNull(bar.getDeepParent("fnarf", 2));
		assertNull(bar.getDeepParent("fnarf", 0));
		assertEquals(bar.getDeepParent((String) null, 0), bar);
		assertEquals(bar.getDeepParent((String) null, 1), foo);
		assertEquals(bar.getDeepParent((String) null, 2), e);
		assertEquals(bar.getDeepParent((String) null, 46), e);
		assertEquals(bar.getDeepParent("foo", 0), foo);
		assertEquals(bar.getDeepParent("foo", 33), foo);
		assertEquals(bar.getDeepParent("Test", 33), e);
	}

	/**
	 * Method testGetDeepParentChild.
	 *
	 */
	@Test
	public void testGetDeepParentChild()
	{
		final XMLDoc jdfDoc = new XMLDoc("Test", "www.test.com");
		final KElement e = jdfDoc.getRoot();
		final KElement foo = e.appendElement("foo");
		final KElement bar = foo.appendElement("bar");
		assertNull(bar.getDeepParentChild("fnarf"));
		assertEquals(bar.getDeepParentChild("Test"), foo);
		assertEquals(bar.getDeepParentChild("foo"), bar);
		final KElement foo2 = e.appendElement("foo:foo", "www.foo.com");
		final KElement bar2 = foo2.appendElement("bar:bar", "www.bar.com");
		assertEquals(bar2.getDeepParentChild("foo:foo"), bar2);
		final KElement bar3 = bar2.appendElement("bar:fnarf", "www.bar.com");
		assertEquals(bar3.getDeepParentChild("foo:foo"), bar2);
	}

	/**
	 * Method testGetDeepParentNotName.
	 *
	 */
	@Test
	public void testGetDeepParentNotName()
	{
		final XMLDoc jdfDoc = new XMLDoc("Test", "www.test.com");
		final KElement e = jdfDoc.getRoot();
		final KElement foo = e.appendElement("foo");
		final KElement bar = foo.appendElement("bar");
		assertEquals(bar.getDeepParentNotName("bar"), foo);
		final KElement bar2 = bar.appendElement("bar", "www.bar.com");
		assertEquals(bar2.getDeepParentNotName("bar"), foo);
		final KElement bar3 = (KElement) jdfDoc.createElement("bar");
		assertNull(bar3.getDeepParentNotName("bar"));
		final KElement bar4 = (KElement) jdfDoc.createElementNS("www.bar.com", "bar");
		assertNull(bar4.getDeepParentNotName("bar"));
	}

	/**
	 *
	 */
	@Test
	public void testInsertBefore()
	{
		final XMLDoc jdfDoc = new XMLDoc("Test", "www.test.com");
		final KElement e = jdfDoc.getRoot();
		final KElement k1 = (KElement) jdfDoc.createElement("second");
		final KElement k2 = (KElement) jdfDoc.createElement("first");
		final KElement k01 = (KElement) e.insertBefore(k1, null);
		final KElement k02 = (KElement) e.insertBefore(k2, k1);
		assertEquals(k1, k01);
		assertEquals(k2, k02);
		assertEquals(k2.getNextSiblingElement(), k1);

	}

	/**
	 *
	 */
	@Test
	public void testIncludesAttributes()
	{
		final XMLDoc jdfDoc = new XMLDoc("Test", "www.test.com");
		final KElement e = jdfDoc.getRoot();
		e.setAttribute("a", "a1");
		e.setAttribute("b", "b1");
		e.setAttribute("c", "c1");
		final JDFAttributeMap m = new JDFAttributeMap("a", "a1");
		m.put("b", "b1");
		assertTrue(e.includesAttributes(m, false));
		assertTrue(e.includesAttributes(m, true));
		m.put("d", "d1");
		assertTrue(e.includesAttributes(m, false));
		assertFalse(e.includesAttributes(m, true));

	}

	/**
	 *
	 */
	@Test
	public void testHasAttributeNS()
	{
		final XMLDoc jdfDoc = new XMLDoc("a:Test", "www.a.com");
		final KElement e = jdfDoc.getRoot();
		e.setAttribute("a:foo", "bar");
		e.setAttribute("bar", "bar2");
		assertTrue(e.hasAttribute("foo"));
		assertTrue("", e.hasAttribute("a:foo"));
		assertTrue(e.hasAttribute("bar"));
		assertTrue("", e.hasAttribute("a:bar"));
	}

	/**
	 *
	 */
	@Test
	public void testInfinity()
	{
		final XMLDoc jdfDoc = new XMLDoc("Test", "www.test.com");
		final KElement e = jdfDoc.getRoot();
		e.setAttribute("inf", Integer.MAX_VALUE, null);
		e.setAttribute("minf", Integer.MIN_VALUE, null);
		assertEquals("inf", e.getAttribute("inf", null, null), JDFConstants.POSINF);
		assertEquals("minf", e.getAttribute("minf", null, null), JDFConstants.NEGINF);
		assertEquals("inf", e.getIntAttribute("inf", null, 0), Integer.MAX_VALUE);
		assertEquals("minf", e.getIntAttribute("minf", null, 0), Integer.MIN_VALUE);
		// now double
		e.setAttribute("inf", Double.MAX_VALUE, null);
		e.setAttribute("minf", -Double.MAX_VALUE, null);
		assertEquals("inf", e.getAttribute("inf", null, null), JDFConstants.POSINF);
		assertEquals("minf", e.getAttribute("minf", null, null), JDFConstants.NEGINF);
		assertEquals("inf", e.getRealAttribute("inf", null, 0), Double.MAX_VALUE, 0.0);
		assertEquals("minf", e.getRealAttribute("minf", null, 0), -Double.MAX_VALUE, 0.0);
	}

	/**
	 *
	 */
	@Test
	public void testSetAttribute_LongAttValue()
	{
		JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		JDFNode root = (JDFNode) jdfDoc.getRoot();
		String longString = "";
		for (int i = 0; i < 13; i++)
		{
			longString += longString + "0 123456789abcdefghijklmnopqrstuvwxyz";
		}

		root.setAttribute("long", longString);
		assertEquals(root.getAttribute("long"), longString);
		jdfDoc.write2File(sm_dirTestDataTemp + "longAtt.jdf", 2, false);
		jdfDoc = new JDFDoc();
		jdfDoc = JDFDoc.parseFile(sm_dirTestDataTemp + "longAtt.jdf");
		root = jdfDoc.getJDFRoot();
		assertEquals(root.getAttribute("long"), longString);
	}

	/**
	 *
	 */
	@Test
	public void testSetAttribute_SpecialChar()
	{
		final KElement root = new XMLDoc("a", null).getRoot();

		final String string = "a\n\"b";
		root.setAttribute("cr", string);
		final String s = root.toXML();
		final ByteArrayIOStream byteArrayIOStream = new ByteArrayIOStream(s.getBytes());
		final XMLDoc doc = XMLDoc.parseStream(byteArrayIOStream.getInputStream());
		byteArrayIOStream.close();
		assertEquals(doc.getRoot().getAttribute("cr"), string);
		assertNotNull(doc);
	}

	// //////////////////////////////////////////////////////////////////////////
	// //

	/**
	 *
	 */
	@Test
	public void testSetAttributes()
	{
		final XMLDoc jdfDoc = new XMLDoc("Foo", null);
		final KElement root = jdfDoc.getRoot();
		final KElement a = root.appendElement("a");
		a.setAttribute("a", "1", null);
		a.setAttribute("b:b", "2", "www.b.com");
		final XMLDoc jdfDoc2 = new XMLDoc("Foo", null);
		final KElement root2 = jdfDoc2.getRoot();
		final KElement a2 = root2.appendElement("a");
		a2.setAttributes(a);
		assertEquals(a2.getAttribute("a"), "1");
		assertEquals(a2.getAttribute("b", "www.b.com", null), "2");
		assertEquals(a.getAttributeMap(), a2.getAttributeMap());
	}

	/**
	 *
	 */
	@Test
	public void testSetAttributesRaw()
	{
		final XMLDoc jdfDoc = new XMLDoc("Foo", null);
		final KElement root = jdfDoc.getRoot();
		final KElement a = root.appendElement("a");
		a.setAttribute("a", "1", null);
		a.setAttribute("b:b", "2", "www.b.com");
		final XMLDoc jdfDoc2 = new XMLDoc("Foo", null);
		final KElement root2 = jdfDoc2.getRoot();
		final KElement a2 = root2.appendElement("a");
		a2.setAttributesRaw(a);
		assertEquals(a2.getAttribute("a"), "1");
		assertEquals(a2.getAttribute("b", "www.b.com", null), "2");
		assertEquals(a.getAttributeMap(), a2.getAttributeMap());
		final String s = jdfDoc2.write2String(2);
		assertTrue(s.indexOf("xmlns:b") > 0);
	}

	/**
	 *
	 */
	@Test
	public void testSetAttributeRaw()
	{
		final XMLDoc jdfDoc = new XMLDoc("Foo", null);
		final KElement root = jdfDoc.getRoot();
		final KElement a = root.appendElement("a");
		a.setAttribute("a", "1", null);
		a.setAttribute("b:b", "2", "www.b.com");
		final XMLDoc jdfDoc2 = new XMLDoc("Foo", null);
		final KElement root2 = jdfDoc2.getRoot();
		final KElement a2 = root2.appendElement("a");
		final JDFAttributeMap map = a.getAttributeMap();
		final Iterator<String> it = map.getKeyIterator();
		while (it.hasNext())
		{
			final String next = it.next();
			a2.setAttributeRaw(next, map.get(next));
		}
		assertEquals(a2.getAttribute("a"), "1");
		assertEquals(a2.getAttribute("b:b"), "2");
		assertEquals(a.getAttributeMap(), a2.getAttributeMap());
		final String s = jdfDoc2.write2String(2);
		assertNull("incorrectly serialized namespace", new JDFParser().parseString(s));
		assertFalse("no magic name space...", s.indexOf("xmlns:b") > 0);
	}

	/**
	 *
	 */
	@Test
	public void testSetNonEmpty()
	{
		final XMLDoc jdfDoc = new XMLDoc("Foo", null);
		final KElement root = jdfDoc.getRoot();
		root.setNonEmpty("b", "c");
		assertEquals(root.getAttribute("b"), "c");
		root.setNonEmpty("b", "");
		assertNull(root.getAttribute("b", null, null));
	}

	/**
	 *
	 */
	@Test
	public void testSetAttributeIterator()
	{
		final XMLDoc jdfDoc = new XMLDoc("Foo", null);
		final KElement root = jdfDoc.getRoot();
		final KElement a = root.appendElement("a");
		a.setAttribute("a", "1", null);
		a.setAttribute("b:b", "2", "www.b.com");
		final XMLDoc jdfDoc2 = new XMLDoc("Foo", null);
		final KElement root2 = jdfDoc2.getRoot();
		final KElement a2 = root2.appendElement("a");
		final JDFAttributeMap map = a.getAttributeMap();
		final Iterator<String> it = map.getKeyIterator();
		while (it.hasNext())
		{
			final String next = it.next();
			a2.setAttribute(next, map.get(next));
		}
		assertEquals(a2.getAttribute("a"), "1");
		assertEquals(a2.getAttribute("b:b"), "2");
		assertEquals(a.getAttributeMap(), a2.getAttributeMap());
		final String s = jdfDoc2.write2String(2);
		assertNull("incorrectly serialized namespace", new JDFParser().parseString(s));
		assertFalse("no magic name space...", s.indexOf("xmlns:b") > 0);
	}

	/**
	 *
	 */
	@Test
	public void testSetAttributesMap()
	{
		final XMLDoc jdfDoc = new XMLDoc("Foo", null);
		final KElement a = jdfDoc.getRoot();
		a.setAttribute("a1", "b1");
		a.setAttribute("a2", "b2");

		final JDFAttributeMap map = new JDFAttributeMap("a3", "b3");
		map.put("a2", (String) null);

		a.setAttributes(map);

		final JDFAttributeMap map2 = new JDFAttributeMap("a3", "b3");
		map2.put("a1", "b1");

		assertEquals(map2, a.getAttributeMap());

	}

	// //////////////////////////////////////////////////////////////////////////
	// /////////

	/**
	 *
	 */
	@Test
	public void testSetAttributesResource()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia x = (JDFExposedMedia) n.addResource("ExposedMedia", EnumUsage.Input);
		x.setAgentName("a1");
		final JDFExposedMedia x2 = (JDFExposedMedia) x.addPartition(EnumPartIDKey.SignatureName, "S1");
		final KElement e2 = n.appendElement("foo");
		e2.setAttributes(x2);
		assertEquals("root resource attributes not copied", e2.getAttribute("AgentName"), "a1");
		assertEquals("leaf resource attributes not copied", e2.getAttribute("SignatureName"), "S1");

	}

	/**
	 *
	 */
	@Test
	public void testSetAttribute()
	{
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();

		final String nodeName = "Created";
		final KElement kElem = root.getXPathElement("AuditPool/" + nodeName);
		assertTrue("", kElem.getNodeName().equals(nodeName));

		// does setAttribute really set an empty value?
		kElem.setAttribute("Author", "");
		assertTrue("", kElem.getAttribute("Author", null, null).equals(""));

		assertTrue("", kElem.hasAttribute("Author", "", false));
		assertFalse("", kElem.hasAttribute("NewAttribute", "", false));

		kElem.setAttribute("Author", "", AttributeName.XMLNSURI);
		kElem.setAttribute("NewAttribute", "");
		assertTrue("", kElem.getAttribute("NewAttribute", null, null).equals(""));
		kElem.setAttribute("foo", "���\"\'");
		assertEquals("special characters", kElem.getAttribute("foo", null, null), "���\"\'");
	}

	/**
	 *
	 */
	@Test
	public void testSetAttributeNS()
	{
		final XMLDoc doc = new XMLDoc("a", null);
		final KElement root = doc.getRoot();
		root.setAttribute("n:b", "1", "www.n.com");
		assertEquals(root.getAttribute("n:b"), "1");
		assertEquals(root.getAttribute("n:b", "www.n.com", null), "1");
		assertEquals(root.getAttribute("b", "www.n.com", null), "1");
		root.setAttribute("n:b", (String) null, "www.n.com");
		assertNull(root.getAttribute("n:b", null, null));
		assertNull(root.getAttribute("n:b", "www.n.com", null));
		assertNull(root.getAttribute("b", "www.n.com", null));
	}

	/**
	 * test sequence of getting / setting if attributes in default and explicit ns exist
	 */
	@Test
	public void testSetAttributeNSMix()
	{
		final XMLDoc doc = new XMLDoc("a", null);
		final KElement root = doc.getRoot();
		root.setAttribute("n:b", "1", "www.n.com");
		assertEquals(root.getAttribute("n:b"), "1");
		assertEquals(root.getAttribute("n:b", "www.n.com", null), "1");
		assertEquals(root.getAttribute("b", "www.n.com", null), "1");
		root.setAttribute("b", "2");
		assertEquals(root.getAttribute("n:b"), "1");
		assertEquals(root.getAttribute("n:b", "www.n.com", null), "1");
		assertEquals(root.getAttribute("b", "www.n.com", null), "1");
		assertEquals(root.getAttribute("b"), "2");

		root.setAttribute("c", "3");
		assertEquals(root.getAttribute("n:c"), "");
		assertEquals(root.getAttribute("n:c", "www.n.com", null), null);
		assertEquals(root.getAttribute("c", "www.n.com", null), null);
		assertEquals(root.getAttribute("c"), "3");
		root.setAttribute("n:c", "4", "www.n.com");
		assertEquals(root.getAttribute("n:c"), "4");
		assertEquals(root.getAttribute("n:c", "www.n.com", null), "4");
		assertEquals(root.getAttribute("c", "www.n.com", null), "4");
		assertEquals(root.getAttribute("c"), "3");

	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetElementHashMap()
	{
		final XMLDoc d = new XMLDoc("root", null);
		final KElement root = d.getRoot();
		for (int i = 0; i < 1000; i++)
		{
			KElement c = root.appendElement("child1");
			c.setAttribute("ID", "id1_" + String.valueOf(i));
			c.setAttribute("II", "abc");
			c = root.appendElement("ns:child2", "myns");
			c.setAttribute("ID", "id2_" + KElement.uniqueID(0));
		}
		assertEquals("", root.getElementHashMap(null, null, "ID").size(), 2000);
		assertEquals("", root.getElementHashMap(null, "myns", "ID").size(), 1000);
		assertEquals("", root.getElementHashMap(null, null, "ID").get("id1_50"), root.getChildByTagName("child1", null, 0, new JDFAttributeMap("ID", "id1_50"), true, true));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetElement_KElement()
	{
		final XMLDoc d = new XMLDoc("JDF", null);
		final KElement root = d.getRoot();
		final KElement c1 = root.appendElement("c");
		final KElement c2 = root.appendElement("c");
		final KElement b1 = root.appendElement("b");
		final KElement c3 = root.appendElement("c");
		c3.setAttribute("ID", "i1");
		final KElement ref = root.appendElement("dRef");

		assertEquals(root.getElement("c"), c1);
		assertEquals(root.getElement(null), root.getFirstChild());
		assertEquals(root.getElement("dRef"), ref);

		assertNull(root.getElement("d"));
		assertEquals(root.getElement("b"), b1);
		assertEquals(root.getElement_KElement("c", null, 0), c1);
		assertEquals(root.getElement_KElement("b", null, -1), b1);
		assertEquals(root.getElement_KElement("c", null, -3), c1);
		assertEquals(root.getElement_KElement("c", null, -1), c3);
		assertEquals(root.getElement_KElement("c", null, 1), c2);
		assertEquals(root.getElement_KElement("c", null, -2), c2);
		assertEquals(root.getElement_KElement(null, null, -5), c1);
		assertEquals(root.getElement_KElement(null, null, 3), c3);
		assertNull(root.getElement_KElement("c", null, -4));
		assertNull(root.getElement_KElement("c", null, 3));
	}

	/**
	 *
	 */
	@Test
	public void testGetElementsByTagName_KElement()
	{
		final JDFDoc d = creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		assertNotNull(n.getElementsByTagName_KElement("*", null));
		assertNotNull(n.getElementsByTagName_KElement("", null));
		assertNotNull(n.getElementsByTagName_KElement(null, null));
	}

	// //////////////////////////////////////////////////////////////////////////
	// ///

	/**
	 *
	 */
	@Test
	public void testGetChildrenByTagNameWildCard()
	{
		final KElement root = new XMLDoc("root", null).getRoot();
		final KElement e1 = root.appendElement("foo");
		e1.setAttribute("a", "1");
		final KElement e2 = root.appendElement("foo");
		e2.setAttribute("a", "2");
		final KElement e3 = root.appendElement("foo");
		e3.setAttribute("b", "3");
		final KElement e4 = e3.appendElement("foo");
		e4.setAttribute("a", "3");
		final VElement v = root.getChildrenByTagName("foo", null, new JDFAttributeMap("a", "*"), false, false, 0);
		assertEquals(v.size(), 3);
		assertTrue(v.contains(e1));
		assertTrue(v.contains(e2));
		assertTrue(v.contains(e4));
	}

	/**
	 *
	 */
	@Test
	public void testGetChildrenByTagName()
	{
		final String xmlFile = "getChildrenByTagNameTest.jdf";

		final JDFParser p = new JDFParser();
		final JDFDoc jdfDoc = p.parseFile(sm_dirTestData + xmlFile);

		final JDFNode jdfRoot = (JDFNode) jdfDoc.getRoot();
		final JDFResourcePool jdfPool = jdfRoot.getResourcePool();
		VElement v = jdfPool.getChildrenByTagName("RunList", null, null, false, true, 0);
		assertEquals("Wrong number of child elements found", v.size(), 10);
		v = jdfPool.getChildrenByTagName("RunList", null, null, false, true, -1);
		assertEquals("Wrong number of child elements found", v.size(), 10);
		v = jdfPool.getChildrenByTagName("RunList", null, null, false, true, 5);
		assertEquals("Wrong number of child elements found", v.size(), 5);
	}

	/**
	 *
	 */
	@Test
	public void testGetChildrenFromList()
	{
		final XMLDoc doc = new XMLDoc("Foo", null);
		final KElement root = doc.getRoot();
		final KElement a = root.appendElement("a");
		final KElement b = a.appendElement("b");
		final KElement b2 = a.appendElement("b:b", "s");
		assertTrue(root.getChildrenFromList(new VString("b", " "), null, false, null).contains(b));
		assertTrue(root.getChildrenFromList(new VString("b", " "), null, false, null).contains(b2));
		assertTrue(root.getChildrenFromList(new VString("b:b", " "), null, false, null).contains(b2));
		assertFalse(root.getChildrenFromList(new VString("b:b", " "), null, false, null).contains(b));
	}

	/**
	 *
	 */
	@Test
	public void testGetChildrenIgnoreList()
	{
		final XMLDoc doc = new XMLDoc("Foo", null);
		final KElement root = doc.getRoot();
		final KElement a = root.appendElement("a");
		final KElement b = root.appendElement("b");
		final KElement b2 = root.appendElement("b:b", "s");
		assertTrue(root.getChildrenIgnoreList(new VString("b", " "), true, null).contains(a));
		assertFalse(root.getChildrenIgnoreList(new VString("b", " "), true, null).contains(b));
		assertFalse(root.getChildrenIgnoreList(new VString("b", " "), true, null).contains(b2));
		assertFalse(root.getChildrenIgnoreList(new VString("b:b", " "), true, null).contains(b2));
		assertTrue(root.getChildrenIgnoreList(new VString("a", " "), true, null).contains(b));
	}

	/**
	 *
	 */
	@Test
	public void testGetChildByTagName()
	{
		final XMLDoc doc = new XMLDoc("Foo", null);
		final KElement root = doc.getRoot();
		assertEquals(root.getChildElementArray().length, 0);
		root.appendElement("bar:bar", "www.bar.com");
		final KElement bar2 = root.appendElement("bar2");
		bar2.setAttribute("foo", "1");
		bar2.setAttribute("ID", "id2");
		final KElement bar3 = bar2.appendElement("bar3");
		bar3.setAttribute("foo", "1");
		bar3.setAttribute("foo2", "2");
		bar3.setAttribute("ID", "id3");
		assertEquals(root.getChildByTagName("bar3", null, 0, null, false, true), bar3);
		assertEquals(root.getChildByTagName("bar3", null, -1, null, false, true), bar3);

	}

	/**
	 *
	 */
	@Test
	public void testGetChildWithAttribute()
	{
		final XMLDoc doc = new XMLDoc("Foo", null);
		final KElement root = doc.getRoot();
		assertEquals(root.getChildElementArray().length, 0);
		root.appendElement("bar:bar", "www.bar.com");
		final KElement bar2 = root.appendElement("bar2");
		bar2.setAttribute("foo", "1");
		bar2.setAttribute("ID", "id2");
		final KElement bar3 = bar2.appendElement("bar3");
		bar3.setAttribute("foo", "1");
		bar3.setAttribute("foo2", "2");
		bar3.setAttribute("ID", "id3");
		assertEquals(root.getChildWithAttribute(null, "foo2", null, null, 0, false), bar3);
		assertEquals(root.getChildWithAttribute(null, "foo", null, null, 0, false), bar2);
		assertEquals(root.getChildWithAttribute(null, "foo", null, null, 1, false), bar3);
		assertEquals(root.getChildWithAttribute(null, "foo", null, null, -1, false), bar3);
		assertEquals(root.getChildWithAttribute(null, "foo", null, null, 0, true), bar2);
		assertEquals(root.getChildWithAttribute(null, "foo", null, "1", 0, true), bar2);
		assertEquals(root.getChildWithAttribute(null, "ID", null, "id2", 0, true), bar2);
		assertNull(root.getChildWithAttribute(null, "ID", null, "id3", 0, true));

		final XMLDoc doc2 = new XMLDoc("Foo", null);
		final KElement root2 = doc2.getRoot();
		final KElement bar22 = root2.appendElement("bar2");
		bar22.setAttribute("ID", "id22");
		assertEquals(root2.getChildWithAttribute(null, "ID", null, "id22", 0, true), bar22);
		assertNull(root.getChildWithAttribute(null, "ID", null, "id22", 0, true));
		bar3.moveElement(bar22, null);
		assertNull(root2.getChildWithAttribute(null, "ID", null, "id22", 0, true));
	}

	/**
	 *
	 */
	@Test
	public void testGetCreateChildWithAttribute()
	{
		final XMLDoc doc = new XMLDoc("Foo", null);
		final KElement root = doc.getRoot();
		assertEquals(root.getChildElementArray().length, 0);
		root.appendElement("bar:bar", "www.bar.com");
		KElement bar2 = root.getChildWithAttribute("bar2", "foo", null, "1", 0, true);
		assertNull(bar2);
		bar2 = root.getCreateChildWithAttribute("bar2", "foo", null, "1", 0);
		assertNotNull(bar2);
		final KElement bar3 = root.getCreateChildWithAttribute("bar2", "foo", null, "1", 0);
		assertEquals(bar2, bar3);
	}

	/**
	 *
	 */
	@Test
	public void testGetChildElementVector_KElement()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		final JDFResource r = n.addResource(ElementName.EXPOSEDMEDIA, null, null, null, null, null, null);
		final JDFResource rp = r.addPartition(EnumPartIDKey.Side, EnumSide.Front.getName());
		VElement v = r.getChildElementVector_KElement(ElementName.EXPOSEDMEDIA, null, null, true, 0);
		assertEquals(v.elementAt(0), rp);
		assertEquals(v.size(), 1);
		final JDFResource r2 = rp.addPartition(EnumPartIDKey.SheetName, "s2");
		v = r.getChildElementVector_KElement(ElementName.EXPOSEDMEDIA, null, null, true, 0);
		assertEquals(v.elementAt(0), rp);
		assertEquals(v.size(), 1);
		final JDFResource r3 = rp.addPartition(EnumPartIDKey.SheetName, "s3");
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.SHEETNAME, "s2");
		v = rp.getChildElementVector_KElement(ElementName.EXPOSEDMEDIA, null, map, true, 0);
		assertTrue(v.contains(r2));
		assertFalse(v.contains(r3));
	}

	/**
	 *
	 */
	@Test
	public void testGetChildElementArray()
	{
		final XMLDoc doc = new XMLDoc("Foo", null);
		final KElement root = doc.getRoot();
		assertEquals(root.getChildElementArray().length, 0);
		root.appendElement("bar:bar", "www.bar.com");
		root.appendElement("bar2");
		assertEquals(root.getChildElementArray().length, 2);
		assertEquals(root.getChildElementArray()[0], root.getElement("bar:bar"));
		assertEquals(root.getChildElementArray()[1], root.getElement("bar2"));
	}

	// //////////////////////////////////////////////////////////////////////////
	// ///

	/**
	 *
	 */
	@Test
	public void testAttributeInfo()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final VString s = n.getNamesVector("Status");
		assertTrue("Status enum", s.contains("InProgress"));
	}

	/**
	 *
	 */
	@Test
	public void testPushUp()
	{
		{// defines a logical test block
				// pushup from 4 to 1
			final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
			final JDFNode root = (JDFNode) jdfDoc.getRoot();
			KElement e = root;
			for (int i = 0; i < 5; i++)
			{
				e = e.appendElement("Test" + i, null);
			}
			e.pushUp("Test1");
			final KElement k = root.getXPathElement("Test0/Test1");
			final VElement v = k.getChildElementVector("Test4", null, new JDFAttributeMap(), true, 99999, false);
			assertTrue("pushUp does not work", v.size() == 1);
		}

		{// defines a logical test block
				// pushup with emptystring
			final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
			final JDFNode root = (JDFNode) jdfDoc.getRoot();
			KElement e = root;
			for (int i = 0; i < 5; i++)
			{
				e = e.appendElement("Test" + i, null);
			}
			e.pushUp("");
			final KElement k = root.getXPathElement("Test0/Test1/Test2");
			final VElement v = k.getChildElementVector("Test4", null, new JDFAttributeMap(), true, 99999, false);
			assertTrue("pushUp does not work", v.size() == 1);
		}

		{// defines a logical test block
				// pushup and force parentNode == null
			final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
			final JDFNode root = (JDFNode) jdfDoc.getRoot();
			KElement e = root;
			for (int i = 0; i < 5; i++)
			{
				e = e.appendElement("Test" + i, null);
			}
			final KElement k = e.pushUp("Foo");
			assertTrue(k == null);
		}
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testSetAttribute_DoubleAtt() throws Exception
	{
		final JDFParser p = new JDFParser();
		{
			final XMLDoc doc = new XMLDoc("d", null);
			final KElement root = doc.getRoot();
			root.setAttribute("a:b", "a1");
			String s2 = doc.write2String(0);
			XMLDoc doc2 = p.parseString(s2);
			assertNull("invalid ns stuff", doc2);
			root.setAttributeNS("www.a.com", "a:b", "a2");
			s2 = doc.write2String(0);
			doc2 = p.parseString(s2);
			assertNotNull("invalid ns stuff", doc2);
			final KElement root2 = doc2.getRoot();
			root2.setAttribute("a:b", "a2", "www.a.com");
			root.setAttribute("a:b", "a2", "www.a.com");

			final String s = doc.write2String(0);
			p.parseString(s);
			s2 = doc.write2String(0);
			p.parseString(s2);
		}
		{
			XMLDoc doc = new XMLDoc("d", null);
			KElement root = doc.getRoot();
			root.setAttribute("a:b", "a2", "www.a.com");
			root.setAttribute("a:b", "a1");
			String s = doc.write2String(0);
			doc = p.parseString(s);
			root = doc.getRoot();
			root.setAttribute("a:b", "a3");
			s = doc.write2String(0);
			doc = p.parseString(s);

		}

	}

	/**
	 *
	 */
	@Test
	public void testSetAttribute_NullCreate()
	{
		final XMLDoc d = new XMLDoc();
		d.createElement("blub").setAttribute("xmlns:foo", "bar:fnarf");
	}

	/**
	 * test to emulate the creation of spurious NS1 prefixes
	 */
	@Test
	public void testNS1Raw()
	{
		final XMLDoc d = new XMLDoc("a", "www.b.com");
		final KElement root = d.getRoot();
		root.setAttributeNSRaw("www.a1.com", "a:b", "val1");
		root.setAttributeNSRaw("www.a2.com", "a:c", "val2");
		assertTrue(root.toString().indexOf("NS1") > 0);
	}

	/**
	 *
	 */
	@Test
	public void testSetAttribute_NameSpaceHandling()
	{
		for (int dd = 0; dd < 2; dd++)
		{
			final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
			JDFNode root = (JDFNode) jdfDoc.getRoot();
			final KElement e = root;

			root.setAttribute("xmlns:Kai", "foo");
			final KElement appended = e.appendElement("Kai:Test1", "foo");
			appended.setAttribute("Kai:Test1", "1", "foo");
			// no try to change the namespace and append
			e.setAttribute("xmlns:Kai", "faa");
			appended.setAttribute("Kai:Test1", "2", null);
			root.setAttribute("aa", "bb");
			root.setAttribute("aa", "bb", null);
			assertTrue(root.hasAttribute("aa"));

			final KElement c = root.appendElement("Comment", null);
			assertTrue("ns append ok", c.getNamespaceURI().equals(root.getNamespaceURI()));
			final KElement f = root.insertBefore("fnarf", c, null);
			assertTrue("ns insert ok", f.getNamespaceURI().equals(root.getNamespaceURI()));
			assertTrue("ns  ok", f.getNamespaceURI() != null);
			assertTrue("ns  ok", f.getNamespaceURI().equals(JDFConstants.JDFNAMESPACE));
			final KElement f2 = root.insertBefore("fnarf:fnarf", c, "www.fnarf");
			assertTrue("ns  ok", f2.getNamespaceURI().equals("www.fnarf"));

			// try to add an element into an unspecified namespace MUST FAIL
			try
			{
				e.appendElement("Kai:Test1");
				// assume that the namespace will be added later fail("snafu");
			}
			catch (final JDFException jdfe)
			{
				// do nothing
			}

			final String testIt = jdfDoc.write2String(0);
			final JDFParser p = new JDFParser();
			final JDFDoc d2 = p.parseString(testIt);
			root = (JDFNode) d2.getRoot();
			// root.setAttribute("aa","cc");
			root.setAttribute("aa", "nsns", root.getNamespaceURI());
			assertFalse("no ns1", root.hasAttribute("ns1:aa"));
			assertEquals("no ns1", root.getAttribute("aa", root.getNamespaceURI(), null), "nsns");
			root.setAttribute("bb:aa", "nsnt", root.getNamespaceURI());
			assertTrue("ns1 default", root.hasAttribute("aa"));
			assertTrue("no ns1", root.hasAttribute("bb:aa"));
			assertEquals("no ns1", root.getAttribute("aa", root.getNamespaceURI(), null), "nsnt");
			assertEquals("no ns1", root.getAttribute("aa", null, null), "nsnt");
		}
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRemoveNS1()
	{
		final XMLDoc d = new XMLDoc("foo", null);
		final KElement root = d.getRoot();
		root.setAttribute("NS1:foo", "bar", "www.ns1.com");
		root.setAttribute("xxx:foo2", "bar", "www.yyy.com");
		root.renameAttribute("NS1:foo", "xxx:foo", null, null);
		root.removeAttribute("xmlns:NS1");
		assertTrue(d.toXML().indexOf("NS1") < 0);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testSetMixedLevelNS()
	{
		final XMLDoc xmlDoc = new XMLDoc("d", null);
		final KElement e = xmlDoc.getRoot();
		e.setAttribute("a:a", "a1", "www.a.com");
		e.setAttributeRaw("a:a", "a2");
		e.setAttributeRaw("b:b", "b1");
		e.setAttributeNSRaw("www.b.com", "b:b", "b2");
		e.setAttributeRaw("xmlns:c", "www.c.com");
		e.setAttributeRaw("c:c", "c1");
		e.setAttributeNSRaw("www.c.com", "c:c", "c2");
		final String s = xmlDoc.write2String(2);
		assertTrue(s.indexOf("a1") < 0);
		assertFalse("calling ns and raw is still broken", s.indexOf("b1") < 0);
		assertFalse("calling ns and raw is still broken", s.indexOf("c1") < 0);
	}

	/**
	 *
	 */
	@Test
	public void testXMLNameSpace()
	{
		assertNull("no ns", KElement.xmlnsPrefix("abc"));
		assertNull("no ns", KElement.xmlnsPrefix(":abc"));
		assertEquals("ns", "ns", KElement.xmlnsPrefix("ns:abc"));
		assertEquals("abc", "abc", KElement.xmlnsLocalName("ns:abc"));
		assertNull("no local name", KElement.xmlnsLocalName("abc:"));
		assertNull("no local name", KElement.xmlnsLocalName(null));
	}

	/**
	 *
	 */
	@Test
	public void testAppendChild()
	{
		final XMLDoc d = new XMLDoc("e", null);
		final KElement e = d.getRoot();
		e.setAttribute("xmlns:foo", "www.foo.com");
		final KElement e2 = (KElement) d.createElement("e2");
		e.appendChild(e2);
		assertEquals(e.getFirstChild(), e2);
		final KElement e3 = (KElement) d.createElement("foo:e3");
		assertNull(e3.getNamespaceURI());
		e.appendChild(e3);
		assertEquals(e2.getNextSibling(), e3);
		assertEquals(e3.getNamespaceURI(), "www.foo.com");
	}

	/**
	 *
	 */
	@Test
	public void testAppendCData()
	{
		final XMLDoc d = new XMLDoc("e", null);
		final KElement e = d.getRoot();
		final XMLDoc d2 = new XMLDoc("e2", null);
		final KElement e2 = d2.getRoot();
		e.appendCData(e2);
		assertTrue(e.toString().indexOf("<e2") > 0);
		e.appendCData(e);
		final JDFParser p = new JDFParser();
		final JDFDoc d3 = p.parseString(d.write2String(2));
		assertNotNull(d3);

	}

	/**
	 *
	 */
	@Test
	public void testParseAppendChild()
	{
		final String s = "<e xmlns=\"a\" xmlns:foo=\"www.foo.com\"><e2/></e>";
		final XMLParser p = new XMLParser();
		p.ignoreNSDefault = true;

		final XMLDoc d = p.parseString(s);
		final KElement e = d.getRoot();
		final KElement e3 = (KElement) d.createElement("foo:e3");
		assertNull(e3.getNamespaceURI());
		e.appendChild(e3);
		final KElement e2 = (KElement) e.getFirstChild();
		assertEquals(e2.getNextSibling(), e3);
		// assertNull(e3.getNamespaceURI());
		final KElement e4 = (KElement) d.createElement("foo:e3");
		assertNull(e4.getNamespaceURI());
		e.appendChild(e4);
		// assertNull(e4.getNamespaceURI());
	}

	/**
	 *
	 */
	@Test
	public void testSetXMLComment()
	{
		final XMLDoc d = new XMLDoc("e", null);
		final KElement root = d.getRoot();
		root.setXMLComment("foo");
		assertEquals(d.getDocumentElement().getParentNode().getFirstChild().getNodeValue(), "foo");
		root.setXMLComment("bar");
		assertEquals(d.getDocumentElement().getParentNode().getFirstChild().getNodeValue(), "bar");
		final KElement e2 = root.appendElement("e2");
		e2.setXMLComment("foobar");
		assertEquals(root.getFirstChild().getNodeValue(), "foobar");
		e2.setXMLComment("foobar2");
		assertEquals(root.getFirstChild().getNodeValue(), "foobar2");
		assertEquals(root.getFirstChild().getNextSibling(), e2);
		assertNull(root.getFirstChild().getNextSibling().getNextSibling());

	}

	/**
	 *
	 */
	@Test
	public void testAppendElementRaw()
	{
		final long t = System.currentTimeMillis();
		// final JDFDoc d = JDFDoc.parseFile(sm_dirTestData + "bigwhite.jdf");
		final JDFDoc d = JDFDoc.parseFile(sm_dirTestData + "pdyv5.jdf");
		final long t2 = System.currentTimeMillis();
		final JDFDoc d2 = new JDFDoc("JDF");
		copyElement(d2.getRoot(), d.getRoot());
		DocumentJDFImpl.setStaticStrictNSCheck(false);
		final long t3 = System.currentTimeMillis();
		final JDFDoc d3 = new JDFDoc("JDF");
		copyElementRaw(d3.getRoot(), d.getRoot());
		assertTrue(d3.getRoot().getXPathElement("ResourcePool") instanceof JDFResourcePool);
		final long t4 = System.currentTimeMillis();
		final JDFDoc d4 = new JDFDoc("JDF");
		copyElementRaw(d4.getRoot(), d3.getRoot());
		assertTrue(d4.getRoot().getXPathElement("ResourcePool") instanceof JDFResourcePool);
		final long t5 = System.currentTimeMillis();
		final JDFParser p = new JDFParser();
		final String s = d4.write2String(2);
		final JDFDoc d5 = p.parseString(s);
		assertNotNull(d5);
		final long t6 = System.currentTimeMillis();
		System.out.println("parse    " + (t2 - t));
		System.out.println("complete " + (t3 - t2));
		System.out.println("raw      " + (t4 - t3));
		System.out.println("raw 2    " + (t5 - t4));
		System.out.println("parse 2  " + (t6 - t5));
	}

	private void copyElement(final KElement dst, final KElement src)
	{
		KElement c = src.getFirstChildElement();
		dst.setAttributes(src);
		while (c != null)
		{
			final KElement newDst = dst.appendElement(c.getNodeName());
			copyElement(newDst, c);
			c = c.getNextSiblingElement();
		}
	}

	private void copyElementRaw(final KElement dst, final KElement src)
	{
		KElement c = src.getFirstChildElement();
		dst.setAttributesRaw(src);
		while (c != null)
		{
			final KElement newDst = dst.appendElementRaw(c.getNodeName(), null);
			copyElementRaw(newDst, c);
			c = c.getNextSiblingElement();
		}
	}

	/**
	 *
	 */
	@Test
	public void testAppendElement()
	{

		final XMLDoc d = new XMLDoc("e", null);
		final KElement e = d.getRoot();
		assertNull(e.getNamespaceURI());
		final KElement foo = e.appendElement("pt:foo", "www.pt.com");
		assertEquals(foo.getNamespaceURI(), "www.pt.com");
		final KElement bar = foo.appendElement("bar");
		assertNull(bar.getNamespaceURI());
		bar.setAttribute("xmlns", "www.bar.com");

		final KElement bar2 = bar.appendElement("bar");
		assertEquals(bar2.getNamespaceURI(), "www.bar.com");

		final KElement foo2 = bar.appendElement("pt:foo", "www.pt.com");
		assertEquals(foo2.getNamespaceURI(), "www.pt.com");

		final KElement bar3 = bar.appendElement("bar");
		assertEquals(bar3.getNamespaceURI(), "www.bar.com");
		final KElement bar4 = foo2.appendElement("bar");
		assertEquals(bar4.getNamespaceURI(), "www.bar.com");
	}

	/**
	 *
	 */
	@Test
	public void testAppendElement_NSAtt()
	{
		final XMLDoc d = new XMLDoc("e", null);
		final KElement e = d.getRoot();
		assertNull(e.getNamespaceURI());
		e.setAttribute("xmlns:pt", "www.pt.com");
		final KElement foo = e.appendElement("foo", null);
		assertNull(foo.getNamespaceURI());
		final KElement bar = foo.appendElement("bar");
		assertNull(bar.getNamespaceURI());
		final KElement bar2 = foo.appendElement("pt:bar");
		assertEquals(bar2.getNamespaceURI(), "www.pt.com");
	}

	/**
	 *
	 */
	@Test
	public void testAppendElement_NSAttJDFDoc()
	{
		JDFDoc d = new JDFDoc("e");
		final String url = JDFElement.getSchemaURL();
		{
			final KElement e = d.getRoot();
			assertEquals(e.getNamespaceURI(), url);
			e.setAttribute("xmlns:pt", "www.pt.com");
			final KElement foo = e.appendElement("foo", null);
			assertEquals(foo.getNamespaceURI(), url);
			final KElement bar = foo.appendElement("bar");
			assertEquals(bar.getNamespaceURI(), url);
			final KElement bar2 = foo.appendElement("pt:bar");
			assertEquals(bar2.getNamespaceURI(), "www.pt.com");
		}
		String s = d.write2String(0);

		// now check for parsed document with no default xmlns declaration
		final JDFParser p = new JDFParser();
		final int pos = s.indexOf(url);
		s = s.substring(0, pos - 7) + s.substring(pos + url.length() + 1); // +/-
		// for xmlns
		// =
		// " and "
		d = p.parseString(s);
		{
			final KElement e = d.getRoot();
			assertNull(e.getNamespaceURI());
			final KElement foo = e.appendElement("foo", null);
			assertNull(foo.getNamespaceURI());
			final KElement bar = foo.appendElement("bar");
			assertNull(bar.getNamespaceURI());
			final KElement bar2 = foo.appendElement("pt:bar");
			assertEquals(bar2.getNamespaceURI(), "www.pt.com");
		}
	}

	/**
	 *
	 */
	@Test
	public void testAppendElement_AttRaw()
	{
		JDFDoc d = new JDFDoc("e");
		final String url = JDFElement.getSchemaURL();
		{
			final KElement e = d.getRoot();
			assertEquals(e.getNamespaceURI(), url);
			e.setAttributeRaw("pt:fnarf", "blubber");
			e.setAttributeRaw("xmlns:pt", "www.pt.com");
			final KElement foo = e.appendElement("foo", null);
			assertEquals(foo.getNamespaceURI(), url);
			final KElement bar = foo.appendElement("bar");
			assertEquals(bar.getNamespaceURI(), url);
			final KElement bar2 = foo.appendElement("pt:bar");
			assertEquals(bar2.getNamespaceURI(), "www.pt.com");
		}
		String s = d.write2String(0);

		// now check for parsed document with no default xmlns declaration
		final JDFParser p = new JDFParser();
		final int pos = s.indexOf(url);
		s = s.substring(0, pos - 7) + s.substring(pos + url.length() + 1); // +/-

		d = p.parseString(s);
		{
			final KElement e = d.getRoot();
			assertNull(e.getNamespaceURI());
			final KElement foo = e.appendElement("foo", null);
			assertNull(foo.getNamespaceURI());
			final KElement bar = foo.appendElement("bar");
			assertNull(bar.getNamespaceURI());
			final KElement bar2 = foo.appendElement("pt:bar");
			assertEquals(bar2.getNamespaceURI(), "www.pt.com");
		}
	}

	/**
	 *
	 */
	@Test
	public void testAppendElement_SingleNS()
	{
		for (int i = 0; i < 2; i++)
		{
			final String wwwECom = "www.e.com";
			final XMLDoc d = i == 0 ? new XMLDoc() : new JDFDoc();
			d.setRoot("e", wwwECom);
			final KElement e = d.getRoot();
			e.addNameSpace(null, wwwECom);
			assertEquals(e.getNamespaceURI(), wwwECom);
			KElement foo = e.appendElement("f", null);
			assertEquals(foo.getNamespaceURI(), wwwECom);
			foo = e.appendElement("f", "");
			assertEquals(foo.getNamespaceURI(), wwwECom);
		}

	}

	/**
	 *
	 */
	@Test
	public void testCreateElement_NoNS()
	{
		for (int i = 0; i < 2; i++)
		{
			final String wwwECom = "www.e.com";
			final XMLDoc d = i == 0 ? new XMLDoc() : new JDFDoc();
			d.setRoot("e", wwwECom);
			final KElement e = d.getRoot();
			assertEquals(e.getNamespaceURI(), wwwECom);
			Element eFoo = d.createElement("f");
			e.appendChild(eFoo);
			final Element eBar = d.createElement("b");
			eFoo.appendChild(eBar);
			assertEquals(eBar.getNamespaceURI(), wwwECom);
			assertEquals(eFoo.getNamespaceURI(), wwwECom);
			eFoo = d.createElementNS(wwwECom, "f");
			e.appendChild(eFoo);
			assertEquals(eFoo.getNamespaceURI(), wwwECom);
		}
	}

	/**
	 *
	 */
	@Test
	public void testParse_SingleNS()
	{
		final String wwwECom = "www.e.com";
		final XMLDoc d = new XMLDoc("e", wwwECom);
		final KElement e = d.getRoot();
		assertEquals(e.getNamespaceURI(), wwwECom);
		final KElement foo = e.appendElement("f", null);
		assertEquals(foo.getNamespaceURI(), wwwECom);
		final String s = d.write2String(2);
		final JDFParser p = new JDFParser();
		final JDFDoc d2 = p.parseString(s);
		final KElement e2 = d2.getRoot();
		assertEquals(e2.getNamespaceURI(), wwwECom);
		final KElement foo2 = e.appendElement("f", null);
		assertEquals(foo2.getNamespaceURI(), wwwECom);
		assertEquals(-1, d2.write2String(2).indexOf("jdf"));
	}

	/**
	 *
	 */
	@Test
	public void testAppendXMLComment()
	{
		final XMLDoc d = new XMLDoc("e", null);
		final KElement e = d.getRoot();
		final VString v = new VString("a . - . -- . -->.<!--", ".");
		for (int i = 0; i < v.size(); i++)
		{
			String s = v.get(i);
			e.appendXMLComment(s, null);
			d.write2File(sm_dirTestDataTemp + "xmlComment.jdf", 2, false);
			final XMLDoc d2 = new JDFParser().parseFile(sm_dirTestDataTemp + "xmlComment.jdf");
			final KElement e2 = d2.getRoot();
			s = StringUtil.replaceString(s, "--", "__");
			assertEquals(e.getXMLComment(i), s);
			assertEquals(e2.getXMLComment(i), s);
		}
	}

	/**
	 *
	 *
	 */
	@Test
	public void testUniqueID()
	{
		String regExp = "_??????_?????????_??????";
		regExp = StringUtil.simpleRegExptoRegExp(regExp);
		for (int i = 0; i < 1000; i++)
		{
			assertTrue(StringUtil.matches(KElement.uniqueID(0), regExp));
		}
	}

	/**
	 *
	 */
	@Test
	public void testAppendAttribute()
	{
		final XMLDoc d = new XMLDoc("e", null);
		final KElement e = d.getRoot();
		e.appendAttribute("at", "a", null, " ", false);
		e.appendAttribute("at", "b", null, " ", false);
		e.appendAttribute("at", "c", null, " ", false);
		assertEquals("a b c", e.getAttribute("at"), "a b c");
		e.appendAttribute("at", "c", null, " ", true);
		assertEquals("a b c", e.getAttribute("at"), "a b c");
		e.appendAttribute("at", "c", null, " ", false);
		assertEquals("a b c", e.getAttribute("at"), "a b c c");
		e.appendAttribute("at", "a a b c", null, null, true);
		assertEquals("a b c", e.getAttribute("at"), "a b c c a a b c");
		e.appendAttribute("ns:key", "na", "www.ns.com", " ", true);
		assertEquals("ns a", e.getAttribute("key", "www.ns.com", ""), "na");
		e.appendAttribute("ns:key", "nb", null, " ", true);
		assertEquals("ns a", e.getAttribute("ns:key"), "na nb");
		e.appendAttribute("ns:key", "nc", "www.ns.com", " ", true);
		assertEquals("ns a", e.getAttribute("key", "www.ns.com", ""), "na nb nc");
		assertEquals("ns a", e.getAttribute("ns:key"), "na nb nc");

		assertEquals("new", e.appendAttribute("fnarf", "new", null, null, true));
		assertEquals("new", e.appendAttribute("fnarf", "new", null, null, true));
		assertEquals("new moo", e.appendAttribute("fnarf", "moo", null, null, true));
		assertEquals("new moo", e.appendAttribute("fnarf", "moo", null, null, true));
		assertEquals("new moo moo", e.appendAttribute("fnarf", "moo", null, null, false));
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	public void testToString()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		assertTrue(root.toString().trim().endsWith("<doc/>"));
	}

	/**
	 *
	 */
	@Test
	public void testToXML()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		assertTrue(root.toXML().contains("<doc/>"));
		root.setAttribute("test", "\"");
		root.setAttribute("test2", "&");
		assertTrue(root.toXML().contains("&amp;"));
	}

	/**
	 *
	 */
	@Test
	public void testToXMLParse()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		final String s = root.toXML();
		final JDFParser p = new JDFParser();
		assertNotNull(p.parseString(s));
	}

	/**
	 *
	 */
	@Test
	public void testToXMLParseNS()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		root.addNameSpace("foo1", "www.foo1.com");
		final KElement ns = root.appendElement("foo1:bar");
		root.setXSIType("typ");
		ns.setAttribute(JDFCoreConstants.XSITYPE, "foobar");
		final String s = ns.toXML();
		final JDFParser p = new JDFParser();
		assertNotNull(p.parseString(s));
	}

	// //////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testSetComment()
	{
		final KElement root = new XMLDoc("root", null).getRoot();
		root.setXMLComment("foo");
		assertNotNull(root.getPreviousSibling());
	}

	/**
	 *
	 */
	@Test
	public void testTextMethods()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		final KElement e1 = root.appendElement("e1");
		e1.setAttribute("a", "b");

		e1.setText("foo");
		assertEquals("foo", e1.getText());
		assertTrue(e1.hasChildText());

		e1.setText("bar");
		assertEquals("bar", e1.getText());
		assertTrue(e1.hasChildText());

		e1.removeAllText();
		assertNull(e1.getText());
		assertFalse(e1.hasChildText());

		e1.appendText("foo");
		assertEquals("foo", e1.getText());
		assertTrue(e1.hasChildText());

		e1.appendText("bar");
		assertEquals("foobar", e1.getText());
		assertTrue(e1.hasChildText());

		assertEquals(e1.getNumChildText(), 2);
		assertEquals("foo", e1.getText(0));
		assertEquals("bar", e1.getText(1));
		assertTrue(e1.hasChildText());

		e1.removeChildText(1);
		assertEquals(e1.getNumChildText(), 1);
		assertEquals("foo", e1.getText(0));
		assertTrue(e1.hasChildText());

		e1.removeChildText(0);
		assertEquals(e1.getNumChildText(), 0);
		assertEquals(null, e1.getText(0)); // getText(i) can return null
		assertNull(e1.getText()); // getText() can return null !!!
		assertFalse(e1.hasChildText());

		e1.removeAllText();
		assertFalse(e1.hasChildText());

		final KElement e2 = root.appendTextElement("e2", "text");
		assertEquals(e2.getNumChildText(), 1);
		assertEquals("text", e2.getText(0));
	}

	/**
	 *
	 */
	@Test
	public void testFillHashSet()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		final KElement e1 = root.appendElement("e1");
		e1.setAttribute("a", "b");
		e1.setAttribute("a2", "b");
		root.setAttribute("a", "aa");

		e1.setXPathAttribute("./e2/e3@a", "c");
		e1.setXPathAttribute("./e3/e4@a", "d");
		HashSet<String> h = root.fillHashSet("a", null);

		assertTrue("", h.contains("aa"));
		assertTrue("", h.contains("b"));
		assertTrue("", h.contains("c"));
		assertTrue("", h.contains("d"));
		assertFalse("", h.contains("a2"));

		h = e1.fillHashSet("a", null);

		assertFalse("", h.contains("aa"));
		assertTrue("", h.contains("b"));
		assertTrue("", h.contains("c"));
		assertTrue("", h.contains("d"));
		assertFalse("", h.contains("a2"));
	}

	/**
	 *
	 * test the flush method also for xml comments, cdata and similar crap
	 */
	@Test
	public void testFlush()
	{
		final XMLDoc doc = new XMLDoc("doc", null);
		final KElement root = doc.getRoot();
		root.setXPathAttribute("foo/bar/murks/@a", "bbbb");
		root.appendXMLComment("aaabbbccc", null);
		root.setAttribute("fff", "ccc");
		root.appendCData("asb");
		root.flush();
		String xml = root.toXML();
		final int pos = xml.indexOf("<d");
		xml = xml.substring(pos).trim();
		assertEquals(xml, "<doc/>");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testWrite2File()
	{
		final KElement e = KElement.createRoot("foo", null);
		final KElement e2 = e.appendElement("bar");
		e.write2File(sm_dirTestDataTemp + "dummy.xml");
		final KElement e3 = XMLDoc.parseFile(sm_dirTestDataTemp + "dummy.xml").getRoot();
		assertTrue(e.isEqual(e3));
		e2.write2File(sm_dirTestDataTemp + "dummy2.xml");
		final KElement e4 = XMLDoc.parseFile(sm_dirTestDataTemp + "dummy2.xml").getRoot();
		assertTrue(e2.isEqual(e4));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testWrite2Stream()
	{
		final KElement e = KElement.createRoot("foo", null);
		final KElement e2 = e.appendElement("bar");
		e2.setAttribute("utf8", "äöü€");
		final ByteArrayIOStream stream = new ByteArrayIOStream();
		e.write2Stream(stream);

		final KElement e3 = XMLDoc.parseStream(stream.getInputStream()).getRoot();
		assertTrue(e.isEqual(e3));
	}
}
