/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of 
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
import org.cip4.jdflib.util.StringUtil;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Element;

/**
 * test class for KElement
 * 
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class KElementTest extends JDFTestCaseBase {
	/**
	 * 
	 */
	@Test
	public void testIsEqual() {
		final KElement e1 = KElement.createRoot("a", null);
		final KElement e2 = e1.clone();

		Assert.assertTrue(e1.isEqual(e2));
		e1.setAttribute("a1", "v1");
		e1.setAttribute("a2", "v2");
		e2.setAttribute("a2", "v2");
		Assert.assertFalse(e1.isEqual(e2));
		e2.setAttribute("a1", "v1");
		Assert.assertTrue(e1.isEqual(e2));
	}

	/**
	 * 
	 */
	@Test
	public void testEquals() {
		final KElement e1 = KElement.createRoot("a", null);
		final KElement e2 = e1.clone();

		Assert.assertTrue(e1.isEqual(e2));
		e1.setAttribute("a1", "v1");
		e1.setAttribute("a2", "v2");
		e2.setAttribute("a2", "v2");
		Assert.assertFalse(e1.equals(e2));
		e2.setAttribute("a1", "v1");
		Assert.assertFalse(e1.equals(e2));
	}

	/**
	 * 
	 */
	@Test
	public void testIsEqualBig() {
		JDFDoc d = JDFDoc.parseFile(sm_dirTestData + "matsch.jdf");
		KElement root = d.getRoot();
		KElement e = root.clone();
		Assert.assertTrue(root.isEqual(e));
		e.setXPathAttribute("JDF/JDF/ResourcePool/@Foo", "bar");
		Assert.assertFalse(root.isEqual(e));
	}

	/**
	 * really weird, eh?
	 */
	@Test
	public void testBadElementNames() {
		final JDFDoc doc = new JDFDoc("a");
		final KElement e = doc.getRoot();
		e.appendElement("I a=\"c\"");

		final String s = doc.write2String(2);
		// final JDFDoc d2 =
		Assert.assertNotNull(new JDFParser().parseString(s));
	}

	/**
	 * 
	 */
	@Test
	public void testAncestorDistance() {
		final KElement e = new JDFDoc("a").getRoot();
		Assert.assertEquals(e.ancestorDistance(e), 0);
		final KElement e1 = e.appendElement("b");
		Assert.assertEquals(e.ancestorDistance(e1), 1);
		final KElement e11 = e1.appendElement("b");
		Assert.assertEquals(e.ancestorDistance(e11), 2);
		final KElement e2 = e.appendElement("b");
		Assert.assertEquals(e1.ancestorDistance(e2), -1);
	}

	/**
	 * check for memory leaks
	 */
	@Test
	public void testDeleteNode() {
		final KElement k = new XMLDoc("root", null).getRoot();
		for (int i = 0; i < 100000; i++) {
			k.appendElement("DOA").deleteNode();
		}
		Assert.assertEquals(getCurrentMem(), mem, 200000);
	}

	/**
	 * check for memory leaks
	 */
	@Test
	public void testImportNode() {
		final KElement k = new XMLDoc("root", null).getRoot();
		for (int i = 0; i < 50000; i++) {
			final KElement d2 = new XMLDoc("mama", null).getRoot();

			for (int j = 0; j < 100; j++) {
				d2.appendElement("kid");
			}
			k.moveElement(d2.appendElement("kid"), null);
		}
		Assert.assertEquals(getCurrentMem(), mem, 100 * 50000); // allow 100 per element
	}

	/**
	 * 
	 */
	@Test
	public void testEnumValid() {
		EnumValidationLevel level = EnumValidationLevel.RecursiveComplete;
		Assert.assertEquals(EnumValidationLevel.NoWarnComplete, EnumValidationLevel.setNoWarning(level, true));
		Assert.assertEquals(EnumValidationLevel.RecursiveComplete, EnumValidationLevel.setNoWarning(level, false));
		level = EnumValidationLevel.RecursiveIncomplete;
		Assert.assertEquals(EnumValidationLevel.NoWarnIncomplete, EnumValidationLevel.setNoWarning(level, true));
		Assert.assertEquals(EnumValidationLevel.RecursiveIncomplete, EnumValidationLevel.setNoWarning(level, false));
	}

	/**
	 * Test for void RemoveAttribute(String, String) - PR-AKMP-000001
	 */
	@Test
	public void testRemoveAttributeStringString() {
		final JDFParser p = new JDFParser();
		final JDFDoc jdfDoc = p.parseFile(sm_dirTestData + "emptyAuthorAttribute.jdf");

		final JDFNode root = (JDFNode) jdfDoc.getRoot();
		final KElement kElem = root.getChildByTagName("Created", null, 0, null, false, true);

		final boolean before = kElem.hasAttribute("Author", null, false);
		Assert.assertTrue("The Attribute 'Author' does not exist", before);

		if (before) {
			kElem.removeAttribute("Author", "");
			final boolean after = kElem.hasAttribute("Author", "", false);

			Assert.assertFalse("The Attribute 'Author' was not removed", after);
		}
	}

	/**
	 * 
	 */
	@Test
	public void testRenameElement() {
		final XMLDoc d = new XMLDoc("root", "www.root.com");
		final KElement root = d.getRoot();
		final String nsUri = root.getNamespaceURI();
		root.renameElement("foo", null);
		Assert.assertEquals(nsUri, root.getNamespaceURI());
		Assert.assertEquals(root.getNodeName(), "foo");
		root.renameElement("bar", "www.bar.com");
		Assert.assertEquals("www.bar.com", root.getNamespaceURI());
		final String s = d.write2String(2);
		Assert.assertTrue(s.indexOf("www.root.com") < 0);
		Assert.assertTrue(s.indexOf("www.bar.com") > 0);
	}

	// /////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testGetElementsWithMultipleID() {
		final XMLDoc d = new XMLDoc("e1", null);
		final KElement e1 = d.getRoot();
		Assert.assertNull(e1.getMultipleIDs("ID"));
		e1.setXPathAttribute("./e2[2]/e3/@ID", "i1");
		e1.setXPathAttribute("./e2[3]/e3/@ID", "i2");
		Assert.assertNull(e1.getMultipleIDs("ID"));
		e1.setXPathAttribute("./e2[4]/e3/@ID", "i1");
		Assert.assertEquals(e1.getMultipleIDs("ID").stringAt(0), "i1");
		Assert.assertEquals(e1.getMultipleIDs("ID").size(), 1);
		e1.setAttribute("ID", "i2");
		Assert.assertEquals(e1.getMultipleIDs("ID").size(), 2);
		Assert.assertTrue(e1.getMultipleIDs("ID").contains("i1"));
		Assert.assertTrue(e1.getMultipleIDs("ID").contains("i2"));

	}

	// /////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testGetFirstChildElement() {
		final XMLDoc d = new XMLDoc("e1", null);
		final KElement e1 = d.getRoot();
		final KElement a = e1.appendElement("a");
		final KElement b = e1.appendElement("b");
		final KElement c = e1.appendElement("c:c", "cc");
		Assert.assertEquals(e1.getFirstChildElement("a", null), a);
		Assert.assertEquals(e1.getFirstChildElement("b", null), b);
		Assert.assertNull(e1.getFirstChildElement("d", null));
		Assert.assertEquals(e1.getFirstChildElement("c", "cc"), c);
	}

	// /////////////////////////////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testGetNextSibling() {
		final XMLDoc d = new XMLDoc("e1", null);
		final KElement e1 = d.getRoot();
		final KElement a = e1.appendElement("a");
		final KElement b = e1.appendElement("b");
		final KElement c = e1.appendElement("c:c", "cc");
		Assert.assertEquals(a.getNextSiblingElement("c", "cc"), c);
		Assert.assertEquals(b.getNextSiblingElement("c", "cc"), c);
		Assert.assertEquals(a.getNextSiblingElement("b", null), b);
		Assert.assertNull(a.getNextSiblingElement("a", null));
	}

	// /////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testGetElementByClass() {
		final JDFDoc d = creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		JDFAuditPool ap = n.getElementByClass(JDFAuditPool.class, 0, false);
		Assert.assertNotNull(ap);
		Assert.assertNull(n.getElementByClass(JDFExposedMedia.class, 0, false));
		Assert.assertNull(n.getElementByClass(JDFExposedMedia.class, 99999, true));
		Assert.assertNull(n.getElementByClass(JDFExposedMedia.class, -99999, true));
		Assert.assertNotNull(n.getElementByClass(JDFExposedMedia.class, 0, true));
		Assert.assertNotNull(n.getElementByClass(JDFExposedMedia.class, -1, true));
		Assert.assertNotSame(n.getElementByClass(JDFExposedMedia.class, 0, true), n.getElementByClass(JDFExposedMedia.class, -1, true));
	}

	/**
	 * 
	 */
	@Test
	public void testGetElementById() {
		final String xmlString = "<JDF ID=\"Link20704459_000351\">" + "<ELEM2 ID=\"Link20704459_000352\">" + "<ELEM3 ID=\"Link20704459_000353\">" + "<Comment/>" + "</ELEM3>" + "</ELEM2>" + "</JDF>";

		for (int i = 0; i < 2; i++) {
			final JDFParser parser = new JDFParser();
			if (i == 1) {
				parser.m_SchemaLocation = new File(sm_dirTestSchema + "JDF.xsd").toURI().toString();
			}
			final JDFDoc jdfDoc = parser.parseString(xmlString);
			final KElement root = jdfDoc.getRoot();

			KElement kElem, kElem2;

			// alt funktioniert
			kElem2 = root.getTarget("Link20704459_000351", AttributeName.ID);
			Assert.assertNotNull(kElem2);

			// neu funktioniert nicht -
			// http://mail-archives.apache.org/mod_mbox/
			// xerces-j-users/200410.mbox/%3c4178694B.40708@metalab.unc.edu%3e
			// http://www.stylusstudio.com/xmldev/200012/post80000.html
			kElem = (KElement) jdfDoc.getElementById("Link20704459_000351");
			Assert.assertNotNull(kElem);

			// alt funktioniert
			kElem2 = root.getTarget("Link20704459_000352", AttributeName.ID);
			Assert.assertNotNull(kElem2);

			// neu funktioniert nicht
			kElem = (KElement) jdfDoc.getElementById("Link20704459_000352");
			Assert.assertNotNull(kElem);

			// alt funktioniert
			kElem2 = root.getTarget("Link20704459_000353", AttributeName.ID);
			Assert.assertNotNull(kElem2);

			// neu funktioniert nicht
			kElem = (KElement) jdfDoc.getElementById("Link20704459_000353");
			Assert.assertNotNull(kElem);
		}
	}

	/**
	 * 
	 */
	@Test
	public void testReplaceElementRoot() {
		final XMLDoc d = new XMLDoc("root", "www.root.com");
		final XMLDoc d2 = new XMLDoc("root2", "www.root2.com");
		final KElement e = d.getRoot();
		e.appendElement("c1");

		final KElement e2 = d2.getRoot();
		e2.replaceElement(e);
		Assert.assertEquals("copied root", d2.getRoot(), e2);
		Assert.assertTrue("same contents", e2.isEqualNode(e));
	}

	/**
	 * test memory leaks in replaceElement
	 */
	@Test
	public void testReplaceElementMem() {
		final XMLDoc d = new XMLDoc("root", "www.root.com");
		final KElement e = d.getRoot();
		final KElement ec1 = e.appendElement("c1");
		final long l = d.getDocMemoryUsed();
		for (int i = 0; i < 100000; i++) {
			ec1.replaceElement(d.clone().getRoot().getFirstChildElement());
		}
		System.gc();
		final long l2 = d.getDocMemoryUsed();
		Assert.assertEquals("memory nice and low", l2, l, 100000);
	}

	/**
	 * 
	 */
	@Test
	public void testReplaceElement() {
		final XMLDoc d = new XMLDoc("root", "www.root.com");
		final XMLDoc d2 = new XMLDoc("root2", "www.root2.com");
		final KElement e = d.getRoot();
		KElement ec1 = e.appendElement("c1");
		final KElement ec2 = e.appendElement("c2");
		ec2.setAttribute("foo", "ec2");
		final KElement ec4 = e.appendElement("c4");

		final KElement ec3 = ec1.replaceElement(ec2);
		Assert.assertEquals("c1=c2", ec3, ec2);
		Assert.assertEquals("c4 is next", ec3, ec4.getPreviousSibling());
		Assert.assertEquals("c4 is next", ec3.getNextSibling(), ec4);
		Assert.assertNull("no sibling", ec1.getNextSibling());
		Assert.assertNull("no sibling", ec1.getPreviousSibling());
		Assert.assertEquals("parent ok", ec2.getParentNode_KElement(), e);
		Assert.assertNull("ec1 no parent", ec1.getParentNode());

		final KElement ec33 = ec3.replaceElement(ec3);
		Assert.assertEquals("replace of this is a nop", ec3, ec33);

		// now cross document
		final KElement e2 = d2.getRoot();
		e2.appendElement("e22");

		ec1 = ec3.replaceElement(e2);
		Assert.assertNull("ec3 no parent", ec3.getParentNode());
		Assert.assertEquals("parent ok", ec1.getParentNode_KElement(), e);
		Assert.assertEquals("c4 is next", ec1, ec4.getPreviousSibling());
		Assert.assertEquals("c4 is next", ec1.getNextSibling(), ec4);
		Assert.assertEquals("root", ec1.getParentNode(), e);

		final KElement eNew = e.replaceElement(e2);
		Assert.assertTrue(eNew.isEqual(e2));
		Assert.assertTrue(e.isEqual(e2));

	}

	// //////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testSortChildren() {
		final XMLDoc d = new JDFDoc("parent");
		final KElement e = d.getRoot();
		final KElement b = e.appendElement("b");
		final KElement a = e.appendElement("a");
		a.setAttribute("ID", "a1");
		final KElement c = e.appendElement("c");
		e.sortChildren();
		Assert.assertEquals(e.getFirstChildElement(), a);
		Assert.assertEquals(a.getNextSiblingElement(), b);
		Assert.assertEquals(b.getNextSiblingElement(), c);
		final KElement a3 = e.appendElement("a");
		a3.setAttribute("ID", "z1");
		final KElement a2 = e.appendElement("a");
		a2.setAttribute("ID", "a2");
		e.sortChildren();
		Assert.assertEquals(e.getFirstChildElement(), a);
		Assert.assertEquals(a.getNextSiblingElement(), a2);
		Assert.assertEquals(a2.getNextSiblingElement(), a3);
		Assert.assertEquals(a3.getNextSiblingElement(), b);
		Assert.assertEquals(b.getNextSiblingElement(), c);

	}

	/**
	 * 
	 */
	@Test
	public void testSortChild() {
		final XMLDoc d = new JDFDoc("parent");
		final KElement e = d.getRoot();
		final KElement b = e.appendElement("b");
		final KElement a = e.appendElement("a2");
		a.setAttribute("ID", "a1");
		final KElement c = e.appendElement("c");
		e.sortChild(b);
		Assert.assertEquals(e.getFirstChildElement(), a);
		Assert.assertEquals(a.getNextSiblingElement(), b);
		Assert.assertEquals(b.getNextSiblingElement(), c);
		final KElement a3 = e.appendElement("a2");
		a3.setAttribute("ID", "z1");
		final KElement a2 = e.appendElement("a2");
		a2.setAttribute("ID", "a2");
		final KElement a4 = e.appendElement("zz");
		a2.setAttribute("ID", "a2");
		e.sortChild(a2);
		e.sortChild(a3);
		e.sortChild(a4);
		Assert.assertEquals(e.getFirstChildElement(), a);
		Assert.assertEquals(a.getNextSiblingElement(), a2);
		Assert.assertEquals(a2.getNextSiblingElement(), a3);
		Assert.assertEquals(a3.getNextSiblingElement(), b);
		Assert.assertEquals(b.getNextSiblingElement(), c);
		Assert.assertEquals(c.getNextSiblingElement(), a4);
		final KElement a0 = e.appendElement("a0");
		e.sortChild(a0);
		Assert.assertEquals(e.getFirstChildElement(), a0);

	}

	/**
	 * 
	 */
	@Test
	public void testSortChildrenAttribute() {
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
		Assert.assertEquals(e.getFirstChildElement(), a);
		Assert.assertEquals(a.getNextSiblingElement(), b);
		Assert.assertEquals(b.getNextSiblingElement(), c);
		// now invert
		e.sortChildren(new KElement.SingleAttributeComparator("at", true));
		Assert.assertEquals(e.getFirstChildElement(), c);
		Assert.assertEquals(c.getNextSiblingElement(), b);
		Assert.assertEquals(b.getNextSiblingElement(), a);

	}

	/**
	 * 
	 */
	@Test
	public void testSortChildrenXPath() {
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
		Assert.assertEquals(e.getFirstChildElement(), a);
		Assert.assertEquals(a.getNextSiblingElement(), b);
		Assert.assertEquals(b.getNextSiblingElement(), c);
		// now invert
		e.sortChildren(new SingleXPathComparator("x/@at", true));
		Assert.assertEquals(e.getFirstChildElement(), c);
		Assert.assertEquals(c.getNextSiblingElement(), b);
		Assert.assertEquals(b.getNextSiblingElement(), a);

	}

	// //////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testSortChildrenCompPerformance() {
		XMLDoc d = new JDFDoc("parent");
		KElement e = d.getRoot();

		for (int i = 0; i < 10000; i++) {
			e.appendElement("b" + (int) (Math.random() * 100));
		}
		System.out.println(System.currentTimeMillis());
		e.sortChildren(new SimpleNodeComparator());
		System.out.println(System.currentTimeMillis());
		d = new JDFDoc("parent");
		e = d.getRoot();
		for (int i = 0; i < 10000; i++) {
			e.appendElement("b" + (int) (Math.random() * 100));
		}
		System.out.println(System.currentTimeMillis());
		e.sortChildren();
		System.out.println(System.currentTimeMillis());
		e.sortChildren();
		System.out.println(System.currentTimeMillis());

	}

	/**
	 * 
	 */
	@Test
	public void testSortChildrenComp() {

		final XMLDoc d = new JDFDoc("parent");
		final KElement e = d.getRoot();
		final KElement b = e.appendElement("b");
		final KElement a = e.appendElement("a");
		a.setAttribute("ID", "a1");

		final KElement c = e.appendElement("c");
		e.sortChildren(new SimpleNodeComparator());
		Assert.assertEquals(e.getFirstChildElement(), a);
		Assert.assertEquals(a.getNextSiblingElement(), b);
		Assert.assertEquals(b.getNextSiblingElement(), c);
		final KElement a3 = e.appendElement("a");
		a3.setAttribute("ID", "z1");
		final KElement a2 = e.appendElement("a");
		a2.setAttribute("ID", "a2");
		e.sortChildren();
		Assert.assertEquals(e.getFirstChildElement(), a);
		Assert.assertEquals(a.getNextSiblingElement(), a2);
		Assert.assertEquals(a2.getNextSiblingElement(), a3);
		Assert.assertEquals(a3.getNextSiblingElement(), b);
		Assert.assertEquals(b.getNextSiblingElement(), c);

	}

	// //////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testRemoveFromAttribute() {
		final XMLDoc d = new JDFDoc("Foo");
		final KElement e = d.getRoot();
		e.setAttribute("a", "1 b 2");
		e.removeFromAttribute("a", "b", null, " ", 333);
		Assert.assertEquals(e.getAttribute("a"), "1 2");
		e.setAttribute("a", "c");
		e.removeFromAttribute("a", "c", null, " ", 333);
		Assert.assertNull(e.getAttribute("a", null, null));
		e.removeFromAttribute("a", "c", null, " ", 333);
		Assert.assertNull(e.getAttribute("a", null, null));
		e.setAttribute("a", "abc ab abc");
		e.removeFromAttribute("a", "ab", null, " ", 333);
		Assert.assertEquals(e.getAttribute("a"), "abc abc");
		e.setAttribute("a", "abc1 abc2 abc");
		e.removeFromAttribute("a", "ab", null, " ", 333);
		Assert.assertEquals(e.getAttribute("a"), "abc1 abc2 abc");
		e.removeFromAttribute("a", "abc", null, " ", 333);
		Assert.assertEquals(e.getAttribute("a"), "abc1 abc2");
		e.removeFromAttribute("a", "", null, " ", 333);
		Assert.assertEquals(e.getAttribute("a"), "abc1 abc2");
		e.setAttribute("a", " abc1 abc2 abc ");
		e.removeFromAttribute("a", "abc1", null, " ", 333);
		Assert.assertEquals(e.getAttribute("a"), "abc2 abc");
		e.removeFromAttribute("a", "abc", null, " ", 333);
		Assert.assertEquals(e.getAttribute("a"), "abc2");
		e.setAttribute("a", " abc1 abc2 abc ");
		e.removeFromAttribute("a", "abc1", null, null, 333);
	}

	// //////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testRemoveExtensions() {
		final KElement e = new XMLDoc("e", "a.com").getRoot();
		final KElement b = e.appendElement("b", "b.com");
		b.setAttribute("c:at", "cc", "c.com");
		// KElement c=
		e.appendElement("c", "c.com");
		Assert.assertNotNull(b.getAttribute("at", "c.com", null));
		Assert.assertNotNull(e.getElement("c", "c.com", 0));
		e.removeExtensions("c.com");
		Assert.assertNull(b.getAttribute("at", "c.com", null));
		Assert.assertNull(e.getElement("c", "c.com", 0));

	}

	// //////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testRemoveEmptyAttributes() {
		final JDFDoc d = new JDFDoc("JDF");
		final KElement e = d.getJDFRoot();

		e.setAttribute("foo", "bar", null);
		e.setAttribute("foo2", "", null);

		Assert.assertTrue("has foo", e.hasAttribute("foo"));
		Assert.assertTrue("has foo2", e.hasAttribute("foo2"));

		final KElement e2 = e.appendElement("e2");
		e2.setAttribute("foo", "bar", null);
		e2.setAttribute("foo2", "", null);

		e.eraseEmptyAttributes(false);
		Assert.assertTrue("has foo", e.hasAttribute("foo"));
		Assert.assertFalse("has foo2", e.hasAttribute("foo2"));
		Assert.assertTrue("has foo", e2.hasAttribute("foo"));
		Assert.assertTrue("has foo2", e2.hasAttribute("foo2"));

		e.eraseEmptyAttributes(true);
		Assert.assertTrue("has foo", e.hasAttribute("foo"));
		Assert.assertFalse("has foo2", e.hasAttribute("foo2"));
		Assert.assertTrue("has foo", e2.hasAttribute("foo"));
		Assert.assertFalse("has foo2", e2.hasAttribute("foo2"));
	}

	/**
	 * 
	 */
	@Test
	public void testRemoveAttribute() {
		final JDFDoc d = new JDFDoc("JDF");
		final KElement e = d.getJDFRoot();
		e.setAttribute("foo", "bar", null);
		Assert.assertTrue("has foo", e.hasAttribute("foo"));
		Assert.assertTrue("has foo", e.hasAttribute("foo", null, false));
		e.removeAttribute("foo", null);
		Assert.assertFalse("has foo", e.hasAttribute("foo"));
		Assert.assertFalse("has foo", e.hasAttribute("foo", null, false));
		e.removeAttribute("foo", null);
		e.removeAttribute("foo"); // make sure we have no npe for removing non
		// existing attrbutes
		e.removeAttribute(""); // make sure we have no npe for removing non
		// existing attrbutes

		e.setAttribute("foo", "bar", "");
		Assert.assertTrue("has foo", e.hasAttribute("foo"));
		Assert.assertTrue("has foo", e.hasAttribute("foo", "", false));
		e.removeAttribute("foo", "");
		Assert.assertFalse("has foo", e.hasAttribute("foo"));
		Assert.assertFalse("has foo", e.hasAttribute("foo", "", false));

		e.setAttribute("foo", "bar", JDFConstants.JDFNAMESPACE);
		Assert.assertTrue("has foo", e.hasAttribute("foo"));
		Assert.assertTrue("has foo", e.hasAttribute("foo", JDFConstants.JDFNAMESPACE, false));
		e.removeAttribute("foo", JDFConstants.JDFNAMESPACE);
		Assert.assertFalse("has foo", e.hasAttribute("foo"));
		Assert.assertFalse("has foo", e.hasAttribute("foo", JDFConstants.JDFNAMESPACE, false));

		e.setAttribute("JDF:foo", "bar", JDFConstants.JDFNAMESPACE);
		Assert.assertTrue("has foo", e.hasAttribute("JDF:foo"));
		Assert.assertTrue("has foo", e.hasAttribute("foo", JDFConstants.JDFNAMESPACE, false));
		e.removeAttribute("foo", JDFConstants.JDFNAMESPACE);
		Assert.assertFalse("has foo", e.hasAttribute("JDF:foo"));
		Assert.assertFalse("has foo", e.hasAttribute("foo", JDFConstants.JDFNAMESPACE, false));

	}

	/**
	 * 
	 */
	@Test
	public void testMatchesPath() {
		final XMLDoc doc = new XMLDoc("Test", "www.test.com");
		final KElement root = doc.getRoot();
		final KElement a = root.appendElement("a");
		root.appendElement("b");
		final KElement a2 = root.appendElement("a");
		final KElement a3 = root.appendElement("a");
		final KElement c = root.appendElement("ns:c", "www.c.com");
		c.setAttribute("att", "41");
		a.setAttribute("att", "42");
		Assert.assertTrue(a.matchesPath("//a", false));
		Assert.assertTrue(a.matchesPath("/Test/a", false));
		Assert.assertTrue(a.matchesPath("/Test/a[1]", false));
		Assert.assertTrue(a.matchesPath("/Test/a[@att=\"42\"]", false));
		Assert.assertTrue(a2.matchesPath("/Test/a[2]", false));
		Assert.assertTrue(a3.matchesPath("/Test/a[3]", false));
		Assert.assertFalse(a3.matchesPath("/Test/a[@att=\"*\"]", false));
		Assert.assertTrue(a.matchesPath("/Test/a[@att=\"*\"]", false));
		Assert.assertTrue(a.matchesPath("/Test/a[@att=\"42\"]", false));
		Assert.assertFalse(a.matchesPath("/Test/a[@att=\"43\"]", false));
		Assert.assertTrue(c.matchesPath("/Test/ns:c", false));
		Assert.assertTrue(c.matchesPath("/Test/ns:c[1]", false));
		Assert.assertTrue(c.matchesPath("/Test/ns:c[@att=\"*\"]", false));
		Assert.assertTrue(c.matchesPath("/Test/ns:c[@att=\"41\"]", false));
	}

	/**
	 * 
	 */
	@Test
	public void testMergeElement() {
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
		Assert.assertEquals(t2.getAttribute("a"), "2");
		Assert.assertEquals(t2.getAttribute("b"), "1");
		Assert.assertEquals(t2.getAttribute("c"), "2");
	}

	/**
	 * 
	 */
	@Test
	public void testCopyInto() {
		final XMLDoc doc = new XMLDoc("Test", "www.test.com");
		final KElement root = doc.getRoot();
		final KElement t = root.appendElement("foo");
		t.setAttribute("a", "1");
		t.setAttribute("b", "1");
		t.setAttribute("c:d", "1", "a.com");

		final XMLDoc doc2 = new XMLDoc("Test", "www.test.com");
		final KElement root2 = doc2.getRoot();

		root2.copyInto(root, true);
		KElement t2 = root2.getElement("foo");
		Assert.assertEquals(t2.getAttribute("a"), "1");
		Assert.assertEquals(t2.getAttribute("b"), "1");
		Assert.assertEquals(t2.getAttribute("c:d"), "1");
		Assert.assertTrue(root2.isEqual(root));
		final XMLDoc doc3 = new XMLDoc("Test", "www.test.com");
		final KElement root3 = doc3.getRoot();

		root3.copyInto(root, true);
		KElement t3 = root2.getElement("foo");
		Assert.assertEquals(t3.getAttribute("a"), "1");
		Assert.assertEquals(t3.getAttribute("b"), "1");
		Assert.assertEquals(t3.getAttribute("c:d"), "1");
		Assert.assertTrue(root3.isEqual(root));
	}

	/**
	 * 
	 */
	@Test
	public void testMergeElementElements() {
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
		Assert.assertEquals(t2.getElement("bar", null, 0), t22);
		Assert.assertEquals(t22.getAttribute("a"), "2");
		Assert.assertEquals(t22.getAttribute("b"), "1");
		Assert.assertEquals(t22.getAttribute("c"), "2");

	}

	/**
	 * 
	 */
	@Test
	public void testMoveMe() {
		final XMLDoc doc = new XMLDoc("Test", "www.test.com");
		final KElement root = doc.getRoot();
		final KElement a = root.appendElement("a");
		KElement b = root.appendElement("b");
		final KElement c = b.appendElement("c");
		Assert.assertNull(root.moveMe(a));
		Assert.assertNull(b.moveMe(c));
		Assert.assertEquals(b.getPreviousSibling(), a);
		b = b.moveMe(a);
		Assert.assertEquals(b.getNextSibling(), a);
		b = b.moveMe(b);
		Assert.assertEquals(b.getNextSibling(), a);
		b = b.moveMe(null);
		Assert.assertEquals(a.getNextSibling(), b);
	}

	/**
	 * 
	 */
	@Test
	public void testNumChildElements() {
		final KElement root = KElement.createRoot("Test", "www.test.com");
		root.appendElement("a");
		root.appendElement("a");
		root.appendElement("test:a", "www.test1.com");
		root.appendElement("c");
		Assert.assertEquals(root.numChildElements(null, null), 4);
		Assert.assertEquals(root.numChildElements("a", null), 3);
		Assert.assertEquals(root.numChildElements("a", "www.test.com"), 2);
		Assert.assertEquals(root.numChildElements("a", "www.test1.com"), 1);
	}

	/**
	 * 
	 */
	@Test
	public void testNumSiblingElements() {
		final KElement root = KElement.createRoot("Test", "www.test.com");
		KElement a = root.appendElement("a");
		root.appendElement("a");
		root.appendElement("a");
		root.appendElement("test:a", "www.test1.com");
		a.appendElement("c");
		Assert.assertEquals(a.numSiblingElements(null, null), 3);
		Assert.assertEquals(a.numSiblingElements("a", null), 3);
		Assert.assertEquals(root.numSiblingElements("a", null), 0);
		Assert.assertEquals(a.numSiblingElements("a", "www.test.com"), 2);
		Assert.assertEquals(a.numSiblingElements("a", "www.test1.com"), 1);
	}

	/**
	 * 
	 */
	@Test
	public void testMoveElement() {
		final XMLDoc doc = new XMLDoc("Test", null);
		final KElement root = doc.getRoot();
		final KElement a = root.appendElement("a");
		final KElement b = root.appendElement("b");
		final KElement c = b.appendElement("c");
		final KElement c2 = b.removeChild("c", null, 0);
		Assert.assertEquals(c, c2);
		final KElement c3 = a.moveElement(c2, null);
		Assert.assertEquals(c, c3);
		Assert.assertEquals(a.getElement("c"), c3);
	}

	/**
	 * 
	 */
	@Test
	public void testCopyElementMem() {
		final KElement k = new XMLDoc("root", null).getRoot();
		for (int i = 0; i < 100000; i++) {
			final KElement d2 = new XMLDoc("mama", null).getRoot();

			for (int j = 0; j < 100; j++) {
				d2.appendElement("kid").appendElement("grandKid");
			}
			k.copyElement(d2.getElement("kid"), null);
		}
		k.removeChildren("kid", null, null);

		long currentMem = getCurrentMem();

		System.out.println("testCopyElementMem:" + Long.toString(currentMem - mem));
		Assert.assertEquals(currentMem, mem, 2500000); // allow 2500 per element
	}

	/**
	 * 
	 */
	@Test
	public void testCloneElementMem() {
		final XMLDoc doc = new XMLDoc("root", null);
		// KElement k =
		doc.getRoot();
		for (int i = 0; i < 50000; i++) {
			final KElement d2 = new XMLDoc("mama", null).getRoot();
			d2.cloneNode(true);
			doc.cloneNode(true);
		}
		Assert.assertEquals(getCurrentMem(), mem, 500000);
	}

	/**
	 * @throws CloneNotSupportedException
	 * 
	 */
	@Test
	public void testClone() throws CloneNotSupportedException {
		final XMLDoc doc = new XMLDoc("root", null);
		KElement e = doc.getRoot();
		KElement d = e.getCreateXPathElement("a/b/c/d[3]");
		KElement c_clone = d.getParentNode_KElement().clone();
		Assert.assertNotNull(c_clone);
		Assert.assertTrue(c_clone.isEqual(c_clone.clone()));
		Assert.assertNotSame(c_clone, c_clone.clone());
		Assert.assertEquals(c_clone.getOwnerDocument(), c_clone.clone().getOwnerDocument());

		KElement rootClone = e.clone();
		Assert.assertTrue(rootClone.isEqual(e.clone()));
		Assert.assertNotSame(rootClone, e.clone());
		Assert.assertEquals(rootClone.getOwnerDocument(), e.clone().getOwnerDocument());
		Assert.assertTrue(rootClone.isEqual(e));
		Assert.assertNotSame(rootClone, e);
		Assert.assertEquals(rootClone.getOwnerDocument(), e.getOwnerDocument());
	}

	/**
	 * @throws CloneNotSupportedException
	 * 
	 */
	@Test
	public void testCloneNewDoc() throws CloneNotSupportedException {
		final XMLDoc doc = new XMLDoc("root", null);
		KElement e = doc.getRoot();
		KElement d = e.getCreateXPathElement("a/b/c/d[3]");
		KElement c_clone = d.getParentNode_KElement().cloneNewDoc();
		Assert.assertNotNull(c_clone);
		Assert.assertTrue(c_clone.isEqual(c_clone.cloneNewDoc()));
		Assert.assertNotSame(c_clone, c_clone.cloneNewDoc());
		Assert.assertNotSame(c_clone.getOwnerDocument(), c_clone.cloneNewDoc().getOwnerDocument());

		KElement rootClone = e.cloneNewDoc();
		Assert.assertTrue(rootClone.isEqual(e.cloneNewDoc()));
		Assert.assertNotSame(rootClone, e.cloneNewDoc());
		Assert.assertNotSame(rootClone.getOwnerDocument(), e.cloneNewDoc().getOwnerDocument());
		Assert.assertTrue(rootClone.isEqual(e));
		Assert.assertNotSame(rootClone, e);
		Assert.assertNotSame(rootClone.getOwnerDocument(), e.getOwnerDocument());
	}

	/**
	 * 
	 */
	@Test
	public void testCloneRoot() {
		final XMLDoc doc = new XMLDoc("root", null);
		final KElement k = doc.getRoot();
		k.appendElement("foo");
		final KElement root = doc.getRoot();
		final KElement k2 = root.cloneRoot(doc.clone());
		Assert.assertTrue(root.isEqual(k));
		Assert.assertNotSame(k2.getOwnerDocument(), doc.getMemberDocument());
	}

	/**
	 * 
	 */
	@Test
	public void testMoveAttribute() {
		final XMLDoc doc = new XMLDoc("Test", "www.test.com");
		final KElement root = doc.getRoot();
		final KElement a = root.appendElement("a");
		final KElement b = root.appendElement("b");
		a.setAttribute("att", "42");
		b.moveAttribute("att", a, null, null, null);
		Assert.assertEquals(b.getAttribute("att"), "42");
		Assert.assertNull(a.getAttribute("att", null, null));
		b.moveAttribute("noThere", a, null, null, null);
		Assert.assertNull(b.getAttribute("noThere", null, null));
		Assert.assertNull(a.getAttribute("noThere", null, null));
		a.setAttribute("foo", "a");
		b.moveAttribute("bar", a, "foo", null, null);
		Assert.assertEquals(b.getAttribute("bar"), "a");
		Assert.assertNull(a.getAttribute("bar", null, null));
		Assert.assertNull(b.getAttribute("foo", null, null));
		Assert.assertNull(a.getAttribute("foo", null, null));
		b.moveAttribute("bar", b, "bar", null, null);
		Assert.assertEquals(b.getAttribute("bar"), "a");
		b.moveAttribute("bar", null, "bar", null, null);
		Assert.assertEquals(b.getAttribute("bar"), "a");
		b.moveAttribute("bar", null, null, null, null);
		Assert.assertEquals(b.getAttribute("bar"), "a");
		b.moveAttribute("bar2", null, "bar", null, null);
		Assert.assertEquals(b.getAttribute("bar2"), "a");
		Assert.assertNull(b.getAttribute("bar", null, null));
	}

	/**
	 * test for copyElement
	 */
	@Test
	public void testCopyElement() {
		final XMLDoc d = new XMLDoc("d1", null);
		final KElement e = d.getRoot();
		final XMLDoc d2 = new XMLDoc("d2", null);
		final KElement e2 = d2.getRoot();
		final KElement e3 = e.copyElement(e2, null);
		Assert.assertNull(e3.getNamespaceURI());
		Assert.assertFalse(d.toString().indexOf("xmlns=\"\"") >= 0);
	}

	/**
	 * test for copyElement after clone check for spurious npes here
	 */
	@Test
	public void testCopyElementClone() {
		final XMLDoc d = new XMLDoc("d1", null);
		final KElement e = d.getRoot();
		final XMLDoc d2 = new XMLDoc("d2", null);
		for (int i = 0; i < 10000; i++) {
			final KElement e3 = e.copyElement(d2.clone().getRoot(), null);
			Assert.assertNull(e3.getNamespaceURI());
		}

		System.out.println("mem new:   " + getCurrentMem() + " " + mem);

		long l = getCurrentMem() - mem;
		Assert.assertTrue(l < 1500000);

	}

	/**
	 * test for copyXPathValue
	 */
	@Test
	public void testCopyXPathValue() {
		KElement e1 = new XMLDoc("e1", null).getRoot();
		KElement e1a = new XMLDoc("e1", null).getRoot();
		e1.setXPathValue("e2/e3", "txt");
		e1.copyXPathValue("e2/e4", null, "e2/e3");
		Assert.assertEquals(e1.getXPathAttribute("e2/e4", null), "txt");
		e1.setXPathValue("e2/e3/@fnarf", "att");
		e1.copyXPathValue("e2/e4/@fnarf", null, "e2/e3/@fnarf");
		Assert.assertEquals(e1.getXPathAttribute("e2/e4/@fnarf", null), "att");
		e1a.copyXPathValue("e2/e3", e1, null);
		Assert.assertEquals(e1a.getXPathAttribute("e2/e3", null), "txt");
	}

	/**
	 * test
	 */
	@Test
	public void testCopyElements() {
		final XMLDoc d = new XMLDoc("d1", null);
		final KElement e = d.getRoot();

		final XMLDoc d2 = new XMLDoc("d2", null);
		final KElement e2 = d2.getRoot();
		final VElement v = new VElement();
		v.add(e2.appendElement("a"));
		v.add(e2.appendElement("b"));
		e.copyElements(v, null);
		Assert.assertEquals(e.getLastChild().getLocalName(), "b");
		Assert.assertEquals(e.getFirstChild().getLocalName(), "a");
		final KElement last = e.appendElement("last");
		e.copyElements(v, last);
		Assert.assertEquals(e.getLastChild().getLocalName(), "last");
		Assert.assertEquals(e.getLastChild().getPreviousSibling().getLocalName(), "b");
		Assert.assertEquals(e.numChildElements(null, null), 5);

	}

	/**
	 * test
	 */
	@Test
	public void testCopyElementNS() {
		final XMLDoc d = new XMLDoc("d1", null);
		final KElement e = d.getRoot();
		final XMLDoc d2 = new XMLDoc("d2", null);
		final KElement e2 = d2.getRoot();
		e2.addNameSpace("foo", "www.foo.com");
		e2.setAttribute("foo:bar", "blub");
		final KElement e3 = e.copyElement(e2, null);
		Assert.assertNull(e3.getNamespaceURI());
		Assert.assertTrue(d.toString().indexOf("xmlns:foo=\"www.foo.com\"") > 0);
	}

	/**
	 * 
	 */
	@Test
	public void testCopyAttribute() {
		final XMLDoc doc = new XMLDoc("Test", "www.test.com");
		final KElement root = doc.getRoot();
		final KElement a = root.appendElement("a");
		final KElement b = root.appendElement("b");
		a.setAttribute("att", "42");
		b.copyAttribute("att", a, null, null, null);
		Assert.assertEquals(a.getAttribute("att"), "42");
		Assert.assertEquals(b.getAttribute("att"), "42");
		b.copyAttribute("noThere", a, null, null, null);
		Assert.assertNull(b.getAttribute("noThere", null, null));
		Assert.assertNull(a.getAttribute("noThere", null, null));
		b.setAttribute("noThereA", "b");
		b.copyAttribute("noThereA", a, null, null, null);
		Assert.assertNull("the existing attribute was removed ", b.getAttribute("noThereA", null, null));
		Assert.assertNull(a.getAttribute("noThereA", null, null));

		a.setAttribute("foo", "a");
		b.copyAttribute("bar", a, "foo", null, null);
		Assert.assertEquals(b.getAttribute("bar"), "a");
		Assert.assertEquals(a.getAttribute("foo"), "a");
		Assert.assertNull(a.getAttribute("bar", null, null));
		Assert.assertNull(b.getAttribute("foo", null, null));

		// ns copy tests
		a.setAttribute("ns:aa", "A", "www.ns.com");
		a.setAttribute("ns:bb", "B", "www.ns.com");
		a.setAttribute("cc", "C", null);
		b.copyAttribute("ns:aa", a, null, null, "www.ns.com");
		Assert.assertEquals(b.getAttribute("ns:aa"), "A");
		Assert.assertEquals(b.getAttribute("aa", "www.ns.com", null), "A");
		b.copyAttribute("bb", a, null, null, "www.ns.com");
		Assert.assertEquals(b.getAttribute("ns:bb"), "B");
		Assert.assertEquals(b.getAttribute("bb", "www.ns.com", null), "B");
		b.copyAttribute("ns:cc", a, "cc", "www.ns.com", null);
		Assert.assertEquals(b.getAttribute("ns:cc"), "C");
		Assert.assertEquals(b.getAttribute("cc", "www.ns.com", null), "C");
	}

	/**
	 * 
	 */
	@Test
	public void testCopyAttributeNS() {
		KElement a = new XMLDoc("a", null).getRoot();
		a.setAttributeNS("www.foo.com", "foo:test", "bar");

		KElement b = new XMLDoc("b", null).getRoot();
		b.copyAttribute("test", a, "test", null, "www.foo.com");
		Assert.assertEquals(b.getAttribute("foo:test"), "bar");
	}

	/**
	 * 
	 */
	@Test
	public void testNameSpace() {
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
		Assert.assertTrue(kElem9.getNamespaceURI().equals(docNS));
		Assert.assertTrue(kElem9.getPrefix().equals(myPrefix));

		final KElement kElem1 = root.appendElement("MyElementLevel_1", docNS);
		Assert.assertTrue(kElem1.getNamespaceURI().equals(docNS));

		// kElem1.setAttributeNS(docNS, myPrefix+":att", "attval");
		kElem1.setAttributeNS(docNS, "att1", "attval1");

		// add an element in a namespace
		final KElement kElem = root.appendElement(myPrefix + JDFConstants.COLON + "MyElement", docNS);
		Assert.assertTrue(kElem.getNamespaceURI().equals(docNS));
		Assert.assertTrue(kElem.getPrefix().equals(myPrefix));

		// add an attribute and its value in a namespace
		kElem.setAttributeNS(docNS, myPrefix + JDFConstants.COLON + "MyAttribute", "MyValue");

		// How to get the element, Version 1
		final KElement kElem2 = root.getElement_KElement("MyElement", docNS, 0);

		String attr = kElem2.getAttribute_KElement("MyAttribute", docNS, "MyDefault");
		Assert.assertTrue(attr.equals("MyValue"));

		// this is pretty invalid but the ns url takes precedence
		attr = kElem2.getAttribute_KElement(myPrefix + JDFConstants.COLON + "MyAttribute", docNS, "MyDefault");
		Assert.assertTrue(attr.equals("MyValue"));

		// this is even more invalid but the ns url takes precedence
		attr = kElem2.getAttribute_KElement("fnarf" + JDFConstants.COLON + "MyAttribute", docNS, "MyDefault");
		Assert.assertTrue(attr.equals("MyValue"));

		// How to get the element, Version 2
		final KElement kElem3 = root.getElement_KElement(myPrefix + JDFConstants.COLON + "MyElement", docNS, 0);

		attr = kElem3.getAttribute_KElement("MyAttribute", docNS, "MyDefault");
		Assert.assertTrue(attr.equals("MyValue"));

		attr = kElem3.getAttribute_KElement(myPrefix + JDFConstants.COLON + "MyAttribute", docNS, "MyDefault");
		Assert.assertTrue(attr.equals("MyValue"));

		final DocumentJDFImpl doc0 = (DocumentJDFImpl) root.getOwnerDocument();

		final Element newChild = doc0.createElementNS(docNS, myPrefix + JDFConstants.COLON + ElementName.RESOURCELINKPOOL);
		root.appendChild(newChild);

		doc.write2File(sm_dirTestDataTemp + "NameSpace.jdf", 0, true);
	}

	/**
	 * 
	 */
	@Test
	public void testNameSpaceInElements() {
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final KElement root = doc.getRoot();

		final String cip4NameSpaceURI = root.getNamespaceURI(); // "http://www.CIP4.org/JDFSchema_1_1"
		// ;
		Assert.assertEquals(cip4NameSpaceURI, JDFConstants.JDFNAMESPACE);

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
		Assert.assertTrue(kElement0.getNamespaceURI().equals(cip4NameSpaceURI));
		Assert.assertNull(kElement0.getPrefix());

		final KElement kElement1 = root.appendElement("kElement1", cip4NameSpaceURI);
		Assert.assertTrue(kElement1.getNamespaceURI().equals(cip4NameSpaceURI));
		Assert.assertNull(kElement1.getPrefix());

		// append an element with prefix with null NameSpaceURI or
		// cip4NameSpaceURI
		final KElement kElement2 = root.appendElement(cip4Prefix1 + JDFConstants.COLON + "kElement2", null);
		Assert.assertTrue(kElement2.getNamespaceURI().equals(cip4NameSpaceURI));
		Assert.assertTrue(kElement2.getPrefix().equals(cip4Prefix1));

		final KElement kElement3 = root.appendElement(cip4Prefix1 + JDFConstants.COLON + "kElement3", cip4NameSpaceURI);
		Assert.assertTrue(kElement3.getNamespaceURI().equals(cip4NameSpaceURI));
		Assert.assertTrue(kElement3.getPrefix().equals(cip4Prefix1));

		final String jdfDocString = "<JDF ID=\"n051221_021145422_000005\" Version=\"1.3\" " + "xmlns=\"http://www.CIP4.org/JDFSchema_1_1\" " + "xmlns:JDF=\"http://www.CIP4.org/JDFSchema_1_1\" "
				+ "xmlns:JDFS=\"http://www.CIP4.org/JDFSchema_1_1\" " + "xmlns:jdf=\"http://www.CIP4.org/JDFSchema_1_1\">" + "<kElement0/>" + "<JDF:kElement1/>" + "<JDFS:kElement2/>"
				+ "<jdf:kElement3/>" + "</JDF>";

		final JDFParser p = new JDFParser();
		final JDFDoc jdfDoc = p.parseString(jdfDocString);
		final KElement root1 = jdfDoc.getRoot();

		// How to get the element, uri = null or cip4NameSpaceURI

		// empty prefix is ok
		final KElement kElemGet1 = root1.getElement("kElement1", null, 0);
		final KElement kElemGet2 = root1.getElement("kElement1", cip4NameSpaceURI, 0);
		Assert.assertEquals(kElemGet1, kElemGet2);

		// correct prefix is ok
		final KElement kElemGet3 = root1.getElement(cip4Prefix1 + JDFConstants.COLON + "kElement1", null, 0);
		final KElement kElemGet4 = root1.getElement(cip4Prefix1 + JDFConstants.COLON + "kElement1", cip4NameSpaceURI, 0);
		Assert.assertEquals(kElemGet3, kElemGet4);
		Assert.assertEquals(kElemGet2, kElemGet4);

		// wrong prefix
		final KElement kElemGet5 = root1.getElement(cip4Prefix2 + JDFConstants.COLON + "kElement1", null, 0);
		final KElement kElemGet6 = root1.getElement(cip4Prefix2 + JDFConstants.COLON + "kElement1", cip4NameSpaceURI, 0);
		Assert.assertNull(kElemGet5);
		Assert.assertNull(kElemGet6);
	}

	/**
	 * 
	 */
	@Test
	public void testNameSpace1() {
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFElement root = (JDFElement) doc.getRoot();

		final String docNS1 = "www1";
		final String docNS2 = "www2";
		final String myPrefix = "HDM";

		try {
			// add an element in a namespace
			final KElement kElem1 = root.appendElement(myPrefix + JDFConstants.COLON + "Foo_1", docNS1);
			Assert.assertTrue(kElem1.getNamespaceURI().equals(docNS1));
			Assert.assertTrue(kElem1.getPrefix().equals(myPrefix));

			kElem1.setAttribute(myPrefix + JDFConstants.COLON + "Foo_1", "attval1", docNS1);
			kElem1.setAttribute(myPrefix + JDFConstants.COLON + "Foo_2", "attval2", docNS2);
			Assert.fail("Called KElement.setAttribute with same prefix but different namespaces ?!");
		} catch (final JDFException expected) {
			final String partOfErrorMessage = "KElement.setAttribute:";
			Assert.assertTrue("Exception message doesn't even mention '" + partOfErrorMessage + "'?!", expected.getMessage().indexOf(partOfErrorMessage) >= 0);
		}
	}

	/**
	 * 
	 */
	@Test
	public void testNameSpaceInAttributes() {
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFElement root = (JDFElement) doc.getRoot();
		root.addNameSpace("foo", "www.foo.com");
		Assert.assertEquals("ns", root.getAttribute("xmlns:foo"), "www.foo.com");
		final KElement child = root.appendElement("abc");
		child.addNameSpace("foo", "www.foo.com");
		Assert.assertFalse("ns 2", child.hasAttribute("xmlns:foo"));

		child.setAttribute("foo:bar", "a1");
		Assert.assertEquals("dom1", child.getAttribute("foo:bar"), "a1");
		child.setAttribute("foo:bar", "a2", "www.foo.com");
		child.setAttribute("foo:barNs", "ns", "www.foo.com");
		Assert.assertEquals("dom1", child.getAttribute("foo:bar"), "a2");
		Assert.assertEquals("dom2", child.getAttribute("bar", "www.foo.com", null), "a2");
		child.setAttribute("foo:bar", "a3");
		Assert.assertEquals("dom1", child.getAttribute("foo:bar"), "a3");
		Assert.assertEquals("dom2", child.getAttribute("bar", "www.foo.com", null), "a3");
		child.setAttribute("bar:bar", "b3", "www.bar.com");
		Assert.assertEquals("dom1", child.getAttribute("bar:bar"), "b3");
		Assert.assertEquals("dom2", child.getAttribute("bar", "www.bar.com", null), "b3");
		child.setAttribute("bar:bar", "b2");
		Assert.assertEquals("dom1", child.getAttribute("bar:bar"), "b2");
		Assert.assertEquals("dom2", child.getAttribute("bar", "www.bar.com", null), "b2");
		child.setAttribute("bar:bar", "b4", "www.bar.com");
		Assert.assertEquals("dom1", child.getAttribute("bar:bar"), "b4");
		Assert.assertEquals("dom2", child.getAttribute("bar", "www.bar.com", null), "b4");
	}

	// /////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testGetPrefix() {
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode myRoot = (JDFNode) jdfDoc.getRoot();
		myRoot.addNameSpace("foo", "www.foo.com");
		final KElement e = myRoot.appendElement("foo:bar", null);
		Assert.assertEquals(e.getPrefix(), "foo");
		myRoot.removeAttribute("xmlns:foo");
		Assert.assertEquals(e.getPrefix(), "foo");
		myRoot.setAttribute("xmlns:blub", "diewupp");
		KElement blub = myRoot.appendElement("blub:diewupp");
		Assert.assertEquals(blub.getPrefix(), "blub");

	}

	// /////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testGetLocalName() {
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
		Assert.assertEquals("LocalName 'Foo' is not equal the original written name", s, "Foo");

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
		Assert.assertEquals("LocalName 'Faa' is not equal the original written name", s, "Faa");

		jdfDoc.write2File(sm_dirTestDataTemp + "GetLocalNameStatic.jdf", 0, true);
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * tests whether the correct virtual call hierarch is followed in getCreateElement
	 */
	@Test
	public void testGetCreateElement() {
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(JDFNode.EnumType.Imposition.getName(), false);
		final JDFRunList rl = (JDFRunList) root.appendMatchingResource(ElementName.RUNLIST, JDFNode.EnumProcessUsage.Document, null);
		rl.appendLayoutElement();
		final JDFRunList leaf = (JDFRunList) rl.getCreatePartition(JDFResource.EnumPartIDKey.Run, "Run1", new VString(JDFResource.EnumPartIDKey.Run.getName(), " "));

		final KElement el1 = rl.getCreateElement_KElement(ElementName.LAYOUTELEMENT, null, 0);
		final KElement el2 = leaf.getCreateElement_KElement(ElementName.LAYOUTELEMENT, null, 0);
		Assert.assertNotSame(el1, el2);
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testGetMatchesPath() {
		final XMLDoc d = new XMLDoc("a", null);
		final KElement r = d.getRoot();
		final KElement b = r.getCreateXPathElement("b/c/d");
		Assert.assertTrue(b.matchesPath("d", true));
		Assert.assertTrue(b.matchesPath("c/d", true));
		Assert.assertTrue(b.matchesPath("/a/b/c/d", true));
		Assert.assertTrue(b.matchesPath("a/b/c/d", true));
		Assert.assertFalse(b.matchesPath("a/a/b/c/d", true));
	}

	/**
	 * 
	 */
	@Test
	public void testGetDefaultAttributeMap() {
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();
		final JDFAttributeMap defs = root.getDefaultAttributeMap();
		Assert.assertEquals("Template is defaulted", defs.get("Template"), "false");
		Assert.assertNull("ID is not defaulted", defs.get("ID"));
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testEraseDefaultAttributeMap() {
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();
		root.setTemplate(false);
		Assert.assertTrue(root.hasAttribute(AttributeName.TEMPLATE));
		root.eraseDefaultAttributes(true);
		Assert.assertFalse("Template is defaulted", root.hasAttribute(AttributeName.TEMPLATE));
		Assert.assertTrue("ID is not defaulted", root.hasAttribute(AttributeName.ID));
	}

	// /////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testGetAttributeMap() {
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();
		Assert.assertFalse("Template is defaulted", root.getAttributeMap().containsKey("Template"));
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testEraseEmptyNodes() {
		final JDFParser p = new JDFParser();
		final String inFile = sm_dirTestData + File.separator + "bigWhite.jdf";
		final JDFDoc jdfDoc = p.parseFile(inFile);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();
		root.eraseEmptyNodes(true);
		final String outFile = sm_dirTestDataTemp + File.separator + "SmallWhite.jdf";
		jdfDoc.write2File(outFile, 0, false);
		final File f = new File(inFile);
		final File f2 = new File(outFile);
		Assert.assertTrue(f.length() > 1.1 * f2.length());
	}

	/**
	 * 
	 */
	@Test
	public void testRemoveXPathElement() {
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		root.setXPathAttribute("a/b[2]/@att", "foo");
		Assert.assertEquals(root.getXPathAttribute("a/b[2]/@att", null), "foo");
		root.removeXPathElement("a/b[2]");
		Assert.assertNull(root.getXPathElement("a/b[2]"));
		Assert.assertNotNull(root.getXPathElement("a/b[1]"));
	}

	/**
	 * 
	 */
	@Test
	public void testRemoveXPathValue() {
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		root.setXPathValue("a/b[2]/att", "foo");
		Assert.assertEquals(root.getXPathAttribute("a/b[2]/att", null), "foo");
		root.removeXPathAttribute("a/b[2]/att");
		Assert.assertNotNull(root.getXPathElement("a/b[2]/att"));
		Assert.assertNull(root.getXPathAttribute("a/b[2]/att", null));
	}

	/**
	 * 
	 */
	@Test
	public void testRemoveXPathAttribute2() {
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		root.setXPathAttribute("a/b[2]/@att", "foo");
		Assert.assertEquals(root.getXPathAttribute("a/b[2]/@att", null), "foo");
		Assert.assertEquals(root.getXPathAttribute("a/b[@att=\"foo\"]/@att", null), "foo");
		Assert.assertTrue(root.hasXPathNode("a/b[@att=\"foo\"]/@att"));
		Assert.assertTrue(root.hasXPathNode("a/b[@att=\"foo\"]"));
		root.removeXPathAttribute("a/b[2]/@att");
		Assert.assertFalse(root.hasXPathNode("a/b[@att=\"foo\"]/@att"));
		Assert.assertFalse(root.hasXPathNode("a/b[@att=\"foo\"]"));
		Assert.assertTrue(root.hasXPathNode("a/b[2]"));
		root.setXPathAttribute("a/b[2]/@att", "foo");
		root.removeXPathAttribute("a/b[@att=\"foo\"]/@att");
		Assert.assertFalse(root.hasXPathNode("a/b[@att=\"foo\"]/@att"));
		Assert.assertFalse(root.hasXPathNode("a/b[@att=\"foo\"]"));
		Assert.assertTrue(root.hasXPathNode("a/b[2]"));
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testGetXPathAttributeMap() {
		final XMLDoc jdfDoc = new XMLDoc("a", null);
		final KElement root = jdfDoc.getRoot();
		root.setXPathAttribute("b/c[3]/d/@foo", "bar3");
		root.setXPathAttribute("b/c[5]/d/@foo", "bar5");
		Map<String, String> m = root.getXPathAttributeMap("//*/@foo");
		Assert.assertEquals(m.size(), 2);
		m = root.getXPathAttributeMap("//@foo");
		Assert.assertEquals(m.size(), 2);
		Assert.assertEquals(root.getXPathAttribute("b/c[3]/d/@foo", null), "bar3");
		Assert.assertEquals(root.getXPathAttribute("b/c[5]/d/@foo", null), "bar5");
	}

	/**
	 * 
	 */
	@Test
	public void testGetXPathAttributeMapNull() {
		final XMLDoc jdfDoc = new XMLDoc("a", null);
		final KElement root = jdfDoc.getRoot();
		root.setXPathAttribute("b/c[3]/d/@foo", "bar3");
		root.setXPathAttribute("b/c[5]/d/@foo", "bar5");
		root.setAttribute("test", "it");
		Map<String, String> m = root.getXPathAttributeMap(null);
		Assert.assertEquals(m.size(), 3);
		Assert.assertEquals(root.getXPathAttribute("@test", null), "it");
		Assert.assertEquals(root.getXPathAttribute("b/c[3]/d/@foo", null), "bar3");
		Assert.assertEquals(root.getXPathAttribute("b/c[5]/d/@foo", null), "bar5");
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testGetXPathElementVector() {
		final XMLDoc jdfDoc = new XMLDoc("a", null);
		final KElement root = jdfDoc.getRoot();
		final VElement va = new VElement();
		va.add(root);
		Assert.assertEquals(va, root.getXPathElementVector("//a", 0));
		Assert.assertEquals(va, root.getXPathElementVector("/a", 0));
		Assert.assertEquals(va, root.getXPathElementVector(".", 0));
		va.clear();
		va.add(root.appendElement("b"));
		va.add(root.appendElement("b"));
		Assert.assertEquals(va, root.getXPathElementVector("b", 0));
		Assert.assertEquals(va, root.getXPathElementVector("//b", 0));
		va.clear();
		va.add(root.getCreateXPathElement("./b/c"));
		va.add(root.getCreateXPathElement("./c"));
		root.getCreateXPathElement("./c/d");
		Assert.assertEquals(va, root.getXPathElementVector("//c", 0));
		root.getCreateXPathElement("./c/d");
		Assert.assertEquals(va, root.getXPathElementVector("//c", 0));
		Assert.assertEquals(1, root.getXPathElementVector("//d", 0).size());
		Assert.assertEquals(root.getXPathElementVector("//d", 0), root.getXPathElementVector("//c/d", 0));
		Assert.assertTrue(root.getXPathElementVector("//*", 0).contains(va.elementAt(0)));
		Assert.assertTrue(root.getXPathElementVector("//*", 0).contains(root));
		root.getCreateXPathElement("./c/d[2]");
		Assert.assertEquals(2, root.getXPathElementVector("//d", 0).size());
		Assert.assertEquals(2, root.getXPathElementVector("/a/c/d", 0).size());
		Assert.assertEquals("d", root.getXPathElementVector("/a/c/d", 0).elementAt(0).getNodeName());
	}

	/**
	 * 
	 */
	@Test
	public void testGetXPathElementNS() {
		XMLDoc d = new XMLDoc("a:A", "www.a.com");
		KElement root = d.getRoot();
		root.setXPathAttribute("a:B/a:C/@a:Att", "val");
		root.appendElement("b:BB", "www.b.com");
		Assert.assertEquals(root.getXPathElement("B/C"), root.getXPathElement("a:B/a:C"));
		Assert.assertEquals(root.getXPathElement("b:BB"), root.getXPathElement("BB"));
		Assert.assertEquals("val", root.getXPathAttribute("B/C/@a:Att", null));
	}

	/**
	 * 
	 */
	@Test
	public void testGetXPathElement() {
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();

		root.setXPathAttribute("/JDF/a[2]/@foo", "v2");
		root.setXPathAttribute("/JDF/a[3]/@foo", "v3");
		Assert.assertEquals(root.getXPathElement("/JDF/a[2]"), root.getXPathElement("/JDF/a[@foo=\"v2\"]"));
		Assert.assertEquals(root.getXPathElement("/JDF/a[3]"), root.getXPathElement("/JDF/a[@foo=\"v3\"]"));

		String nodeName = "Created";
		KElement kElem = root.getXPathElement("AuditPool/" + nodeName);
		Assert.assertEquals(kElem.getNodeName(), nodeName);
		Assert.assertTrue(kElem.matchesPath("Created", false));
		Assert.assertTrue(kElem.matchesPath("/JDF/AuditPool/Created", false));
		Assert.assertTrue(kElem.matchesPath("JDF/AuditPool/Created", false));
		Assert.assertFalse(kElem.matchesPath("/Created", false));

		nodeName = "notFound";
		kElem = root.getXPathElement("AuditPool/" + nodeName);
		Assert.assertNull(kElem);
		final XMLDoc d2 = new XMLDoc("doc", null);
		final KElement root2 = d2.getRoot();
		for (int i = 0; i < 10; i++) {
			final KElement e = root2.appendElement("e");
			Assert.assertEquals(root2.getXPathElement("e[" + (i + 1) + "]"), e);
			Assert.assertEquals(root2, e.getXPathElement("../"));
			Assert.assertEquals(root2, e.getXPathElement(".."));
			Assert.assertEquals(root2, e.getXPathElement(".././."));
		}
		final KElement e = root2.getCreateElement("foo.bar");
		Assert.assertEquals(e.getNodeName(), "foo.bar");
		Assert.assertEquals(root2.getXPathElement("foo.bar"), e);
		Assert.assertEquals(root2.getCreateXPathElement("foo.bar"), e);
		root.setXPathAttribute("/JDF/ee[2]/@a", "2");
		root.setXPathAttribute("/JDF/ee[1]/@a", "2");
		root.setXPathAttribute("/JDF/ee[2]/ff/@b", "3");
		Assert.assertEquals(root.getXPathAttribute("/JDF/ee/ff/@b", null), "3");
		Assert.assertEquals(root.getXPathAttribute("/JDF/ee[@a=\"2\"]/ff/@b", null), "3");
		Assert.assertEquals(root.getXPathAttribute("//ee[@a=\"2\"]/ff/@b", null), "3");
		Assert.assertNull(root.getXPathAttribute("/JDF/ee[1]/ff/@b", null));

	}

	/**
	 * 
	 */
	@Test
	public void testGetCreateXPathElement() {
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();

		String nodeName = "Created";
		KElement kElem = root.getCreateXPathElement("AuditPool/" + nodeName);
		Assert.assertTrue("", kElem != null && kElem.getNodeName().equals(nodeName));

		nodeName = "newElement";
		kElem = root.getCreateXPathElement("AuditPool/" + nodeName);
		Assert.assertTrue("", kElem != null && kElem.getNodeName().equals(nodeName));

		final KElement e = root.getCreateXPathElement("./foo/bar[2]/fnarf[3]");
		Assert.assertNotNull("e", e);
		Assert.assertEquals("", e, root.getCreateXPathElement("./foo/bar[2]/fnarf[3]"));
		Assert.assertEquals("", e, root.getXPathElement("./foo/bar[2]/fnarf[3]"));
		root.setXPathAttribute("./foo/bar[2]@blub", "b1");

		Assert.assertEquals("1 foo", root.numChildElements("foo", null), 1);
		Assert.assertNotNull("get", root.getXPathElement("./foo/bar[2]/fnarf[3]"));
		Assert.assertEquals("", root.getElement("foo").numChildElements("bar", null), 2);
		Assert.assertEquals("", root.getElement("foo").getElement("bar").numChildElements("fnarf", null), 0);
		Assert.assertEquals("", root.getElement("foo").getElement("bar").getNextSiblingElement("bar", null).numChildElements("fnarf", null), 3);
		Assert.assertEquals("", root.getCreateXPathElement("."), root);

		Assert.assertEquals("", e, root.getXPathElement("./foo/bar[@blub=\"b1\"]/fnarf[3]"));
		Assert.assertEquals("", e, root.getCreateXPathElement("./foo/bar[@blub=\"b1\"]/fnarf[3]"));
		Assert.assertNotSame("", e, root.getCreateXPathElement("./foo/bar[@blub=\"b1\"]/fnarf[5]"));
		Assert.assertEquals("", root.getElement("foo").getElement("bar").numChildElements("fnarf", null), 0);
		Assert.assertEquals("", root.getElement("foo").getElement("bar").getNextSiblingElement("bar", null).numChildElements("fnarf", null), 5);
		Assert.assertNotNull("create by attribute value now implemented", root.getCreateXPathElement("./foo/bar[@blub=\"b1\"]/fnarf[@a=\"b\"]"));
		Assert.assertEquals("getCreate actually sets the attribute", "blahblah", root.getCreateXPathElement("./foo/bar[@blub=\"blahblah\"]").getAttribute("blub"));
		Assert.assertEquals("getCreate actually sets the attribute", "blahblah", root.getCreateXPathElement("./foo/bar[gaga/@blub=\"blahblah\"]").getXPathAttribute("gaga/@blub", null));
	}

	/**
	 * 
	 */
	@Test
	public void testBuildXPath() {
		final XMLDoc d = new XMLDoc("d", null);
		final KElement root = d.getRoot();
		Assert.assertEquals(root.buildXPath(null, 1), "/d");
		Assert.assertEquals(root.buildXPath("/d", 1), ".");
		Assert.assertEquals(root.buildXPath(null, 0), "/d");
		Assert.assertEquals(root.buildXPath("/d", 0), ".");
		root.appendElement("e");
		final KElement e = root.appendElement("e");
		e.setAttribute("ID", "i");
		root.setAttribute("ID", "r");
		Assert.assertEquals(e.buildXPath(null, 1), "/d/e[2]");
		Assert.assertEquals(e.buildXPath(null, 3), "/d[@ID=\"r\"]/e[@ID=\"i\"]");
		Assert.assertEquals(e.buildXPath("/d", 3), "./e[@ID=\"i\"]");
		Assert.assertEquals(e.buildXPath(null, 0), "/d/e");
		Assert.assertEquals(e.buildXPath("/d", 1), "./e[2]");
		Assert.assertEquals(e.buildXPath("/d", 1), e.buildXPath("/d", 2));
		Assert.assertEquals(e.buildXPath("/d", 0), "./e");

	}

	// /////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testGetReal() {
		KElement e = new XMLParser().parseString("<foo bar=\".42\"/>").getRoot();
		Assert.assertEquals(e.getRealAttribute("bar", null, 0.0), 0.42, 0.1);
	}

	/**
	 * make sure we also get all valid deep elements
	 */
	@Test
	public void testGetTree() {
		KElement e = new XMLParser().parseString("<a/>").getRoot();
		KElement a1 = e.appendElement("a");
		KElement aa = a1.appendElement("a");
		KElement b = a1.appendElement("b");
		KElement ab = b.appendElement("a");
		aa.setAttribute("b", "c");
		ab.setAttribute("b", "c");
		VElement tree = e.getTree("a", null, new JDFAttributeMap("b", "c"), false, false);
		Assert.assertTrue(tree.contains(aa));
		Assert.assertFalse(tree.contains(a1));
		Assert.assertFalse(tree.contains(ab));
	}

	/**
	 * 
	 */
	@Test
	public void testGetXPathAttribute() {
		JDFAudit.setStaticAgentName("foo Agent");
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();
		root.setID("rootID");
		Assert.assertEquals(root.getXPathAttribute("@ID", null), "rootID");

		final String nodeName = "Created";
		String attribute = "AgentName";
		String attValue = root.getXPathAttribute("AuditPool/" + nodeName + "@" + attribute, "dummydefault");
		Assert.assertEquals(attValue, JDFAudit.getStaticAgentName());

		attribute = "notExistingAttribute";
		attValue = root.getXPathAttribute("AuditPool/" + nodeName + "@" + attribute, "dummydefault");
		Assert.assertEquals(attValue, "dummydefault");
		Assert.assertNull(root.getXPathAttribute("AuditPool/" + nodeName + "@" + attribute, null));
		root.setXPathAttribute("foo/bar[2]/@a", "b2");
		root.setXPathAttribute("foo/bar[2]/sub/@c", "d2");
		root.setXPathValue("b/c[5]/d", "txt");
		Assert.assertEquals(root.getXPathAttribute("foo/bar[@a=\"b2\"]/sub/@c", null), "d2");
		Assert.assertEquals(root.getXPathAttribute("b/c[5]/d", null), "txt");
		try {
			root.getXPathAttribute("foo/bar[0]/sub/@c", null);
			Assert.fail("index must be >0");
		} catch (final IllegalArgumentException x) {
			// nop
		}
	}

	/**
	 * 
	 */
	@Test
	public void testGetXPathAttributeText() {
		JDFAudit.setStaticAgentName("foo Agent");
		KElement e = KElement.createRoot("foo", null);
		e.appendElement("a");
		Assert.assertNull(e.getXPathAttribute("a", null));
		Assert.assertNull(e.getXPathAttribute("b", null));
		Assert.assertEquals("", e.getXPathAttribute("a", ""));
		Assert.assertEquals("", e.getXPathAttribute("b", ""));
	}

	/**
	 * 
	 */
	@Test
	public void testGetXPathAttributeWithPath() {
		JDFAudit.setStaticAgentName("foo Agent");
		KElement root = new XMLDoc("a", null).getRoot();
		root.getCreateXPathElement("b[1]/c");
		root.getCreateXPathElement("b[5]/c");
		KElement c = root.getCreateXPathElement("b[3]/c");
		root.setXPathAttribute("b[3]/part/@foo", "bar");
		Assert.assertEquals(root.getXPathElement("b[part/@foo=\"bar\"]/c"), c);
		Assert.assertEquals(root.getXPathElement("b[./part/@foo=\"bar\"]"), c.getParentNode_KElement());
		Assert.assertEquals(root.getXPathElement("b[part/@foo=\"bar\"]"), c.getParentNode_KElement());
	}

	/**
	 * 
	 */
	@Test
	public void testGetDOMAttr() {
		final XMLDoc xd = new XMLDoc("a", null);
		final KElement root = xd.getRoot();
		root.setAttribute("at", "b");
		Assert.assertEquals("", root.getAttribute("at"), root.getDOMAttr("at", null, false).getNodeValue());
		final KElement child = root.appendElement("child");
		Assert.assertNull("", child.getDOMAttr("at", null, false));
		Assert.assertNull("", child.getDOMAttr("at_notther", null, true));
		Assert.assertNotNull("", child.getDOMAttr("at", null, true));
	}

	/**
	 * 
	 */
	@Test
	public void testRemoveXPathAttribute() {
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();

		final String nodeName = "Created";
		String attribute = "Author";
		root.removeXPathAttribute("AuditPool/" + nodeName + "@" + attribute);
		String attValue = root.getXPathAttribute("AuditPool/" + nodeName + "@" + attribute, null);
		Assert.assertNull("", attValue);

		attribute = "notExistingAttribute";
		root.removeXPathAttribute("AuditPool/" + nodeName + "@" + attribute);
		attValue = root.getXPathAttribute("AuditPool/" + nodeName + "@" + attribute, "dummydefault");
		Assert.assertTrue("", attValue.equals("dummydefault"));
	}

	/**
	 * 
	 */
	@Test
	public void testSetXPathAttributeReplace() {
		KElement e = new XMLDoc("foo", null).getRoot();
		e.setXPathAttribute("a[@a=\"b\"]/c[@d=\"e\"]/@f", "g");
		String s1 = e.toXML();
		e.setXPathAttribute("a[@a=\"b\"]/c[@d=\"e\"]/@f", "g");
		e.setXPathAttribute("a[@a=\"b\"]/c[@d=\"e\"]/@f", "h");
		e.setXPathAttribute("a[@a=\"b\"]/c[@d=\"e\"]/@f", "g");
		String s2 = e.toXML();
		Assert.assertEquals(s1, s2);

	}

	/**
	 * 
	 */
	@Test
	public void testSetXPathAttribute() {
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();

		final String nodeName = "Created";
		final String attribute = "Author";
		root.setXPathAttribute("AuditPool/" + nodeName + "@" + attribute, "newAttributeValue");
		root.setXPathAttribute("new/@foo", "bar");
		Assert.assertEquals("", root.getXPathAttribute("new/@foo", null), "bar");
		Assert.assertEquals("", root.getXPathAttribute("new@foo", null), "bar");
		root.setXPathAttribute("new@foo2", "bar2");
		Assert.assertEquals("", root.getXPathAttribute("new/@foo2", null), "bar2");
		Assert.assertEquals("", root.getXPathAttribute("new@foo2", null), "bar2");
		final String attValue = root.getXPathAttribute("AuditPool/" + nodeName + "@" + attribute, "dummydefault");
		Assert.assertEquals("", attValue, "newAttributeValue");

		for (int i = 0; i < 3; i++) {
			root.appendElement("Foo3");
		}
		root.setXPathAttribute("Foo3/@bar2", "bb");
		root.setXPathAttribute("Foo3[1]/@bar3", "cc");
		for (int i = 0; i < 3; i++) {
			Assert.assertEquals(root.getElement("Foo3", null, i).getAttribute("bar2"), "bb");
			if (i == 0) {
				Assert.assertEquals(root.getElement("Foo3", null, i).getAttribute("bar3"), "cc");
			} else {
				Assert.assertEquals(root.getElement("Foo3", null, i).getAttribute("bar3"), "");
			}
		}
		root.setXPathAttribute("Foo3/@bar2", "bb");

		root.setXPathAttribute("ParameterSet[@Name=\"foooo\"]/Parameter/foooo/@bar", "fnarf");
		KElement fooo = root.getChildByTagName("foooo", null, 0, null, false, false);
		Assert.assertEquals("fnarf", fooo.getAttribute("bar"));
	}

	/**
	 * 
	 */
	@Test
	public void testSetXPathValue() {
		final XMLDoc doc = new XMLDoc("root", null);
		final KElement root = doc.getRoot();
		root.setXPathValue("foo/bar", "snafu");
		Assert.assertEquals(root.getXPathElement("foo/bar").getText(), "snafu");
		root.setXPathValue("foo/bar/@c", "d");
		Assert.assertEquals(root.getXPathAttribute("foo/bar/@c", null), "d");
	}

	/**
	 * 
	 */
	@Test
	public void testSetXPathValueText() {
		final XMLDoc doc = new XMLDoc("root", null);
		final KElement root = doc.getRoot();
		root.setXPathValue("foo/bar/@aa", "a");
		Assert.assertEquals(root.getXPathAttribute("foo/bar/@aa", null), "a");
		root.setXPathValue("foo[@ID=\"1\"]/bar", "snafu");
		Assert.assertEquals(root.getXPathElement("foo[@ID=\"1\"]/bar").getText(), "snafu");
		root.setXPathValue("foo[@ID=\"2\"]/bar", "snafu2");
		Assert.assertEquals(root.getXPathElement("foo[@ID=\"2\"]/bar").getText(), "snafu2");
		root.setXPathValue("foo[@ID=\"2\"]/@bar", "bb");
		Assert.assertEquals(root.getXPathAttribute("foo[@ID=\"2\"]/@bar", null), "bb");
		root.setXPathValue("foo/bar/@c", "d");
		Assert.assertEquals(root.getXPathAttribute("foo/bar/@c", null), "d");
	}

	/**
	 * 
	 */
	@Test
	public void testSetXPathValues() {
		final XMLDoc doc = new XMLDoc("root", null);
		final KElement root = doc.getRoot();
		JDFAttributeMap map = new JDFAttributeMap();
		map.put("foo[@ID=\"1\"]/bar", "snafu");
		map.put("foo[@ID=\"2\"]/bar", "snafu2");
		map.put("foo[@ID=\"2\"]/@bar", "bb");
		map.put("foo/bar2/@c", "d");
		root.setXPathValues(map);
		Assert.assertEquals(root.getXPathAttribute("foo/bar2/@c", null), "d");
		Assert.assertEquals(root.getXPathElement("foo[@ID=\"1\"]/bar").getText(), "snafu");
		Assert.assertEquals(root.getXPathElement("foo[@ID=\"2\"]/bar").getText(), "snafu2");
		Assert.assertEquals(root.getXPathAttribute("foo[@ID=\"2\"]/@bar", null), "bb");
	}

	/**
	 * Method testGetDeepParentChild.
	 * 
	 */
	@Test
	public void testGetDeepParent() {
		final XMLDoc jdfDoc = new XMLDoc("Test", "www.test.com");
		final KElement e = jdfDoc.getRoot();
		final KElement foo = e.appendElement("foo");
		final KElement bar = foo.appendElement("bar");
		Assert.assertNull(bar.getDeepParent("fnarf", 2));
		Assert.assertNull(bar.getDeepParent("fnarf", 0));
		Assert.assertEquals(bar.getDeepParent((String) null, 0), bar);
		Assert.assertEquals(bar.getDeepParent((String) null, 1), foo);
		Assert.assertEquals(bar.getDeepParent((String) null, 2), e);
		Assert.assertEquals(bar.getDeepParent((String) null, 46), e);
		Assert.assertEquals(bar.getDeepParent("foo", 0), foo);
		Assert.assertEquals(bar.getDeepParent("foo", 33), foo);
		Assert.assertEquals(bar.getDeepParent("Test", 33), e);
	}

	/**
	 * Method testGetDeepParentChild.
	 * 
	 */
	@Test
	public void testGetDeepParentChild() {
		final XMLDoc jdfDoc = new XMLDoc("Test", "www.test.com");
		final KElement e = jdfDoc.getRoot();
		final KElement foo = e.appendElement("foo");
		final KElement bar = foo.appendElement("bar");
		Assert.assertNull(bar.getDeepParentChild("fnarf"));
		Assert.assertEquals(bar.getDeepParentChild("Test"), foo);
		Assert.assertEquals(bar.getDeepParentChild("foo"), bar);
		final KElement foo2 = e.appendElement("foo:foo", "www.foo.com");
		final KElement bar2 = foo2.appendElement("bar:bar", "www.bar.com");
		Assert.assertEquals(bar2.getDeepParentChild("foo:foo"), bar2);
		final KElement bar3 = bar2.appendElement("bar:fnarf", "www.bar.com");
		Assert.assertEquals(bar3.getDeepParentChild("foo:foo"), bar2);
	}

	/**
	 * Method testGetDeepParentNotName.
	 * 
	 */
	@Test
	public void testGetDeepParentNotName() {
		final XMLDoc jdfDoc = new XMLDoc("Test", "www.test.com");
		final KElement e = jdfDoc.getRoot();
		final KElement foo = e.appendElement("foo");
		final KElement bar = foo.appendElement("bar");
		Assert.assertEquals(bar.getDeepParentNotName("bar"), foo);
		final KElement bar2 = bar.appendElement("bar", "www.bar.com");
		Assert.assertEquals(bar2.getDeepParentNotName("bar"), foo);
		final KElement bar3 = (KElement) jdfDoc.createElement("bar");
		Assert.assertNull(bar3.getDeepParentNotName("bar"));
		final KElement bar4 = (KElement) jdfDoc.createElementNS("www.bar.com", "bar");
		Assert.assertNull(bar4.getDeepParentNotName("bar"));
	}

	/**
	 * 
	 */
	@Test
	public void testInsertBefore() {
		final XMLDoc jdfDoc = new XMLDoc("Test", "www.test.com");
		final KElement e = jdfDoc.getRoot();
		final KElement k1 = (KElement) jdfDoc.createElement("second");
		final KElement k2 = (KElement) jdfDoc.createElement("first");
		final KElement k01 = (KElement) e.insertBefore(k1, null);
		final KElement k02 = (KElement) e.insertBefore(k2, k1);
		Assert.assertEquals(k1, k01);
		Assert.assertEquals(k2, k02);
		Assert.assertEquals(k2.getNextSiblingElement(), k1);

	}

	/**
	 * 
	 */
	@Test
	public void testIncludesAttributes() {
		final XMLDoc jdfDoc = new XMLDoc("Test", "www.test.com");
		final KElement e = jdfDoc.getRoot();
		e.setAttribute("a", "a1");
		e.setAttribute("b", "b1");
		e.setAttribute("c", "c1");
		final JDFAttributeMap m = new JDFAttributeMap("a", "a1");
		m.put("b", "b1");
		Assert.assertTrue(e.includesAttributes(m, false));
		Assert.assertTrue(e.includesAttributes(m, true));
		m.put("d", "d1");
		Assert.assertTrue(e.includesAttributes(m, false));
		Assert.assertFalse(e.includesAttributes(m, true));

	}

	/**
	 * 
	 */
	@Test
	public void testHasAttributeNS() {
		final XMLDoc jdfDoc = new XMLDoc("a:Test", "www.a.com");
		final KElement e = jdfDoc.getRoot();
		e.setAttribute("a:foo", "bar");
		e.setAttribute("bar", "bar2");
		Assert.assertTrue(e.hasAttribute("foo"));
		Assert.assertTrue("", e.hasAttribute("a:foo"));
		Assert.assertTrue(e.hasAttribute("bar"));
		Assert.assertTrue("", e.hasAttribute("a:bar"));
	}

	/**
	 * 
	 */
	@Test
	public void testInfinity() {
		final XMLDoc jdfDoc = new XMLDoc("Test", "www.test.com");
		final KElement e = jdfDoc.getRoot();
		e.setAttribute("inf", Integer.MAX_VALUE, null);
		e.setAttribute("minf", Integer.MIN_VALUE, null);
		Assert.assertEquals("inf", e.getAttribute("inf", null, null), JDFConstants.POSINF);
		Assert.assertEquals("minf", e.getAttribute("minf", null, null), JDFConstants.NEGINF);
		Assert.assertEquals("inf", e.getIntAttribute("inf", null, 0), Integer.MAX_VALUE);
		Assert.assertEquals("minf", e.getIntAttribute("minf", null, 0), Integer.MIN_VALUE);
		// now double
		e.setAttribute("inf", Double.MAX_VALUE, null);
		e.setAttribute("minf", -Double.MAX_VALUE, null);
		Assert.assertEquals("inf", e.getAttribute("inf", null, null), JDFConstants.POSINF);
		Assert.assertEquals("minf", e.getAttribute("minf", null, null), JDFConstants.NEGINF);
		Assert.assertEquals("inf", e.getRealAttribute("inf", null, 0), Double.MAX_VALUE, 0.0);
		Assert.assertEquals("minf", e.getRealAttribute("minf", null, 0), -Double.MAX_VALUE, 0.0);
	}

	/**
	 * 
	 */
	@Test
	public void testSetAttribute_LongAttValue() {
		JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		JDFNode root = (JDFNode) jdfDoc.getRoot();
		String longString = "";
		for (int i = 0; i < 13; i++) {
			longString += longString + "0 123456789abcdefghijklmnopqrstuvwxyz";
		}

		root.setAttribute("long", longString);
		Assert.assertEquals(root.getAttribute("long"), longString);
		jdfDoc.write2File(sm_dirTestDataTemp + "longAtt.jdf", 2, false);
		jdfDoc = new JDFDoc();
		jdfDoc = JDFDoc.parseFile(sm_dirTestDataTemp + "longAtt.jdf");
		root = jdfDoc.getJDFRoot();
		Assert.assertEquals(root.getAttribute("long"), longString);
	}

	/**
	 * 
	 */
	@Test
	public void testSetAttribute_SpecialChar() {
		KElement root = new XMLDoc("a", null).getRoot();

		String string = "a\n\"b";
		root.setAttribute("cr", string);
		String s = root.toXML();
		XMLDoc doc = XMLDoc.parseStream(new ByteArrayIOStream(s.getBytes()).getInputStream());
		Assert.assertEquals(doc.getRoot().getAttribute("cr"), string);
		Assert.assertNotNull(doc);
	}

	// //////////////////////////////////////////////////////////////////////////
	// //

	/**
	 * 
	 */
	@Test
	public void testSetAttributes() {
		final XMLDoc jdfDoc = new XMLDoc("Foo", null);
		final KElement root = jdfDoc.getRoot();
		final KElement a = root.appendElement("a");
		a.setAttribute("a", "1", null);
		a.setAttribute("b:b", "2", "www.b.com");
		final XMLDoc jdfDoc2 = new XMLDoc("Foo", null);
		final KElement root2 = jdfDoc2.getRoot();
		final KElement a2 = root2.appendElement("a");
		a2.setAttributes(a);
		Assert.assertEquals(a2.getAttribute("a"), "1");
		Assert.assertEquals(a2.getAttribute("b", "www.b.com", null), "2");
		Assert.assertEquals(a.getAttributeMap(), a2.getAttributeMap());
	}

	/**
	 * 
	 */
	@Test
	public void testSetAttributesRaw() {
		final XMLDoc jdfDoc = new XMLDoc("Foo", null);
		final KElement root = jdfDoc.getRoot();
		final KElement a = root.appendElement("a");
		a.setAttribute("a", "1", null);
		a.setAttribute("b:b", "2", "www.b.com");
		final XMLDoc jdfDoc2 = new XMLDoc("Foo", null);
		final KElement root2 = jdfDoc2.getRoot();
		final KElement a2 = root2.appendElement("a");
		a2.setAttributesRaw(a);
		Assert.assertEquals(a2.getAttribute("a"), "1");
		Assert.assertEquals(a2.getAttribute("b", "www.b.com", null), "2");
		Assert.assertEquals(a.getAttributeMap(), a2.getAttributeMap());
		String s = jdfDoc2.write2String(2);
		Assert.assertTrue(s.indexOf("xmlns:b") > 0);
	}

	/**
	 * 
	 */
	@Test
	public void testSetAttributeRaw() {
		final XMLDoc jdfDoc = new XMLDoc("Foo", null);
		final KElement root = jdfDoc.getRoot();
		final KElement a = root.appendElement("a");
		a.setAttribute("a", "1", null);
		a.setAttribute("b:b", "2", "www.b.com");
		final XMLDoc jdfDoc2 = new XMLDoc("Foo", null);
		final KElement root2 = jdfDoc2.getRoot();
		final KElement a2 = root2.appendElement("a");
		JDFAttributeMap map = a.getAttributeMap();
		Iterator<String> it = map.getKeyIterator();
		while (it.hasNext()) {
			String next = it.next();
			a2.setAttributeRaw(next, map.get(next));
		}
		Assert.assertEquals(a2.getAttribute("a"), "1");
		Assert.assertEquals(a2.getAttribute("b:b"), "2");
		Assert.assertEquals(a.getAttributeMap(), a2.getAttributeMap());
		String s = jdfDoc2.write2String(2);
		Assert.assertNull("incorrectly serialized namespace", new JDFParser().parseString(s));
		Assert.assertFalse("no magic name space...", s.indexOf("xmlns:b") > 0);
	}

	/**
	 * 
	 */
	@Test
	public void testSetAttributeIterator() {
		final XMLDoc jdfDoc = new XMLDoc("Foo", null);
		final KElement root = jdfDoc.getRoot();
		final KElement a = root.appendElement("a");
		a.setAttribute("a", "1", null);
		a.setAttribute("b:b", "2", "www.b.com");
		final XMLDoc jdfDoc2 = new XMLDoc("Foo", null);
		final KElement root2 = jdfDoc2.getRoot();
		final KElement a2 = root2.appendElement("a");
		JDFAttributeMap map = a.getAttributeMap();
		Iterator<String> it = map.getKeyIterator();
		while (it.hasNext()) {
			String next = it.next();
			a2.setAttribute(next, map.get(next));
		}
		Assert.assertEquals(a2.getAttribute("a"), "1");
		Assert.assertEquals(a2.getAttribute("b:b"), "2");
		Assert.assertEquals(a.getAttributeMap(), a2.getAttributeMap());
		String s = jdfDoc2.write2String(2);
		Assert.assertNull("incorrectly serialized namespace", new JDFParser().parseString(s));
		Assert.assertFalse("no magic name space...", s.indexOf("xmlns:b") > 0);
	}

	/**
	 * 
	 */
	@Test
	public void testSetAttributesMap() {
		final XMLDoc jdfDoc = new XMLDoc("Foo", null);
		final KElement a = jdfDoc.getRoot();
		a.setAttribute("a1", "b1");
		a.setAttribute("a2", "b2");

		final JDFAttributeMap map = new JDFAttributeMap("a3", "b3");
		map.put("a2", (String) null);

		a.setAttributes(map);

		final JDFAttributeMap map2 = new JDFAttributeMap("a3", "b3");
		map2.put("a1", "b1");

		Assert.assertEquals(map2, a.getAttributeMap());

	}

	// //////////////////////////////////////////////////////////////////////////
	// /////////

	/**
	 * 
	 */
	@Test
	public void testSetAttributesResource() {
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia x = (JDFExposedMedia) n.addResource("ExposedMedia", EnumUsage.Input);
		x.setAgentName("a1");
		final JDFExposedMedia x2 = (JDFExposedMedia) x.addPartition(EnumPartIDKey.SignatureName, "S1");
		final KElement e2 = n.appendElement("foo");
		e2.setAttributes(x2);
		Assert.assertEquals("root resource attributes not copied", e2.getAttribute("AgentName"), "a1");
		Assert.assertEquals("leaf resource attributes not copied", e2.getAttribute("SignatureName"), "S1");

	}

	/**
	 * 
	 */
	@Test
	public void testSetAttribute() {
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();

		final String nodeName = "Created";
		final KElement kElem = root.getXPathElement("AuditPool/" + nodeName);
		Assert.assertTrue("", kElem.getNodeName().equals(nodeName));

		// does setAttribute really set an empty value?
		kElem.setAttribute("Author", "");
		Assert.assertTrue("", kElem.getAttribute("Author", null, null).equals(""));

		Assert.assertTrue("", kElem.hasAttribute("Author", "", false));
		Assert.assertFalse("", kElem.hasAttribute("NewAttribute", "", false));

		kElem.setAttribute("Author", "", AttributeName.XMLNSURI);
		kElem.setAttribute("NewAttribute", "");
		Assert.assertTrue("", kElem.getAttribute("NewAttribute", null, null).equals(""));
		kElem.setAttribute("foo", "���\"\'");
		Assert.assertEquals("special characters", kElem.getAttribute("foo", null, null), "���\"\'");
	}

	/**
	 * 
	 */
	@Test
	public void testSetAttributeNS() {
		final XMLDoc doc = new XMLDoc("a", null);
		final KElement root = doc.getRoot();
		root.setAttribute("n:b", "1", "www.n.com");
		Assert.assertEquals(root.getAttribute("n:b"), "1");
		Assert.assertEquals(root.getAttribute("n:b", "www.n.com", null), "1");
		Assert.assertEquals(root.getAttribute("b", "www.n.com", null), "1");
		root.setAttribute("n:b", (String) null, "www.n.com");
		Assert.assertNull(root.getAttribute("n:b", null, null));
		Assert.assertNull(root.getAttribute("n:b", "www.n.com", null));
		Assert.assertNull(root.getAttribute("b", "www.n.com", null));
	}

	/**
	 * test sequence of getting / setting if attributes in default and explicit ns exist
	 */
	@Test
	public void testSetAttributeNSMix() {
		final XMLDoc doc = new XMLDoc("a", null);
		final KElement root = doc.getRoot();
		root.setAttribute("n:b", "1", "www.n.com");
		Assert.assertEquals(root.getAttribute("n:b"), "1");
		Assert.assertEquals(root.getAttribute("n:b", "www.n.com", null), "1");
		Assert.assertEquals(root.getAttribute("b", "www.n.com", null), "1");
		root.setAttribute("b", "2");
		Assert.assertEquals(root.getAttribute("n:b"), "1");
		Assert.assertEquals(root.getAttribute("n:b", "www.n.com", null), "1");
		Assert.assertEquals(root.getAttribute("b", "www.n.com", null), "1");
		Assert.assertEquals(root.getAttribute("b"), "2");

		root.setAttribute("c", "3");
		Assert.assertEquals(root.getAttribute("n:c"), "");
		Assert.assertEquals(root.getAttribute("n:c", "www.n.com", null), null);
		Assert.assertEquals(root.getAttribute("c", "www.n.com", null), null);
		Assert.assertEquals(root.getAttribute("c"), "3");
		root.setAttribute("n:c", "4", "www.n.com");
		Assert.assertEquals(root.getAttribute("n:c"), "4");
		Assert.assertEquals(root.getAttribute("n:c", "www.n.com", null), "4");
		Assert.assertEquals(root.getAttribute("c", "www.n.com", null), "4");
		Assert.assertEquals(root.getAttribute("c"), "3");

	}

	/**
	 * 
	 *
	 */
	@Test
	public void testGetElementHashMap() {
		final XMLDoc d = new XMLDoc("root", null);
		final KElement root = d.getRoot();
		for (int i = 0; i < 1000; i++) {
			KElement c = root.appendElement("child1");
			c.setAttribute("ID", "id1_" + String.valueOf(i));
			c.setAttribute("II", "abc");
			c = root.appendElement("ns:child2", "myns");
			c.setAttribute("ID", "id2_" + KElement.uniqueID(0));
		}
		Assert.assertEquals("", root.getElementHashMap(null, null, "ID").size(), 2000);
		Assert.assertEquals("", root.getElementHashMap(null, "myns", "ID").size(), 1000);
		Assert.assertEquals("", root.getElementHashMap(null, null, "ID").get("id1_50"), root.getChildByTagName("child1", null, 0, new JDFAttributeMap("ID", "id1_50"), true, true));
	}

	/**
	 * 
	 *
	 */
	@Test
	public void testGetElement_KElement() {
		final XMLDoc d = new XMLDoc("JDF", null);
		final KElement root = d.getRoot();
		final KElement c1 = root.appendElement("c");
		final KElement c2 = root.appendElement("c");
		final KElement b1 = root.appendElement("b");
		final KElement c3 = root.appendElement("c");
		c3.setAttribute("ID", "i1");
		final KElement ref = root.appendElement("dRef");

		Assert.assertEquals(root.getElement("c"), c1);
		Assert.assertEquals(root.getElement(null), root.getFirstChild());
		Assert.assertEquals(root.getElement("dRef"), ref);

		Assert.assertNull(root.getElement("d"));
		Assert.assertEquals(root.getElement("b"), b1);
		Assert.assertEquals(root.getElement_KElement("c", null, 0), c1);
		Assert.assertEquals(root.getElement_KElement("b", null, -1), b1);
		Assert.assertEquals(root.getElement_KElement("c", null, -3), c1);
		Assert.assertEquals(root.getElement_KElement("c", null, -1), c3);
		Assert.assertEquals(root.getElement_KElement("c", null, 1), c2);
		Assert.assertEquals(root.getElement_KElement("c", null, -2), c2);
		Assert.assertEquals(root.getElement_KElement(null, null, -5), c1);
		Assert.assertEquals(root.getElement_KElement(null, null, 3), c3);
		Assert.assertNull(root.getElement_KElement("c", null, -4));
		Assert.assertNull(root.getElement_KElement("c", null, 3));
	}

	/**
	 * 
	 */
	@Test
	public void testGetElementsByTagName_KElement() {
		final JDFDoc d = creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		Assert.assertNotNull(n.getElementsByTagName_KElement("*", null));
		Assert.assertNotNull(n.getElementsByTagName_KElement("", null));
		Assert.assertNotNull(n.getElementsByTagName_KElement(null, null));
	}

	// //////////////////////////////////////////////////////////////////////////
	// ///

	/**
	 * 
	 */
	@Test
	public void testGetChildrenByTagNameWildCard() {
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
		Assert.assertEquals(v.size(), 3);
		Assert.assertTrue(v.contains(e1));
		Assert.assertTrue(v.contains(e2));
		Assert.assertTrue(v.contains(e4));
	}

	/**
	 * 
	 */
	@Test
	public void testGetChildrenByTagName() {
		final String xmlFile = "getChildrenByTagNameTest.jdf";

		final JDFParser p = new JDFParser();
		final JDFDoc jdfDoc = p.parseFile(sm_dirTestData + xmlFile);

		final JDFNode jdfRoot = (JDFNode) jdfDoc.getRoot();
		final JDFResourcePool jdfPool = jdfRoot.getResourcePool();
		VElement v = jdfPool.getChildrenByTagName("RunList", null, null, false, true, 0);
		Assert.assertEquals("Wrong number of child elements found", v.size(), 10);
		v = jdfPool.getChildrenByTagName("RunList", null, null, false, true, -1);
		Assert.assertEquals("Wrong number of child elements found", v.size(), 10);
		v = jdfPool.getChildrenByTagName("RunList", null, null, false, true, 5);
		Assert.assertEquals("Wrong number of child elements found", v.size(), 5);
	}

	// //////////////////////////////////////////////////////////////////////////
	// ///

	/**
	 * 
	 */
	@Test
	public void testGetChildrenFromList() {
		final XMLDoc doc = new XMLDoc("Foo", null);
		final KElement root = doc.getRoot();
		final KElement a = root.appendElement("a");
		final KElement b = a.appendElement("b");
		final KElement b2 = a.appendElement("b:b", "s");
		Assert.assertTrue(root.getChildrenFromList(new VString("b", " "), null, false, null).contains(b));
		Assert.assertTrue(root.getChildrenFromList(new VString("b", " "), null, false, null).contains(b2));
		Assert.assertTrue(root.getChildrenFromList(new VString("b:b", " "), null, false, null).contains(b2));
		Assert.assertFalse(root.getChildrenFromList(new VString("b:b", " "), null, false, null).contains(b));
	}

	// //////////////////////////////////////////////////////////////////////////
	// ///

	/**
	 * 
	 */
	@Test
	public void testGetChildWithAttribute() {
		final XMLDoc doc = new XMLDoc("Foo", null);
		final KElement root = doc.getRoot();
		Assert.assertEquals(root.getChildElementArray().length, 0);
		root.appendElement("bar:bar", "www.bar.com");
		final KElement bar2 = root.appendElement("bar2");
		bar2.setAttribute("foo", "1");
		bar2.setAttribute("ID", "id2");
		final KElement bar3 = bar2.appendElement("bar3");
		bar3.setAttribute("foo", "1");
		bar3.setAttribute("foo2", "2");
		bar3.setAttribute("ID", "id3");
		Assert.assertEquals(root.getChildWithAttribute(null, "foo2", null, null, 0, false), bar3);
		Assert.assertEquals(root.getChildWithAttribute(null, "foo", null, null, 0, false), bar2);
		Assert.assertEquals(root.getChildWithAttribute(null, "foo", null, null, 1, false), bar3);
		Assert.assertEquals(root.getChildWithAttribute(null, "foo", null, null, 0, true), bar2);
		Assert.assertEquals(root.getChildWithAttribute(null, "foo", null, "1", 0, true), bar2);
		Assert.assertEquals(root.getChildWithAttribute(null, "ID", null, "id2", 0, true), bar2);
		Assert.assertNull(root.getChildWithAttribute(null, "ID", null, "id3", 0, true));

		final XMLDoc doc2 = new XMLDoc("Foo", null);
		final KElement root2 = doc2.getRoot();
		final KElement bar22 = root2.appendElement("bar2");
		bar22.setAttribute("ID", "id22");
		Assert.assertEquals(root2.getChildWithAttribute(null, "ID", null, "id22", 0, true), bar22);
		Assert.assertNull(root.getChildWithAttribute(null, "ID", null, "id22", 0, true));
		bar3.moveElement(bar22, null);
		Assert.assertNull(root2.getChildWithAttribute(null, "ID", null, "id22", 0, true));

	}

	/**
	 * 
	 */
	@Test
	public void testGetChildElementVector_KElement() {
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		final JDFResource r = n.addResource(ElementName.EXPOSEDMEDIA, null, null, null, null, null, null);
		final JDFResource rp = r.addPartition(EnumPartIDKey.Side, EnumSide.Front.getName());
		VElement v = r.getChildElementVector_KElement(ElementName.EXPOSEDMEDIA, null, null, true, 0);
		Assert.assertEquals(v.elementAt(0), rp);
		Assert.assertEquals(v.size(), 1);
		final JDFResource r2 = rp.addPartition(EnumPartIDKey.SheetName, "s2");
		v = r.getChildElementVector_KElement(ElementName.EXPOSEDMEDIA, null, null, true, 0);
		Assert.assertEquals(v.elementAt(0), rp);
		Assert.assertEquals(v.size(), 1);
		final JDFResource r3 = rp.addPartition(EnumPartIDKey.SheetName, "s3");
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.SHEETNAME, "s2");
		v = rp.getChildElementVector_KElement(ElementName.EXPOSEDMEDIA, null, map, true, 0);
		Assert.assertTrue(v.contains(r2));
		Assert.assertFalse(v.contains(r3));
	}

	/**
	 * 
	 */
	@Test
	public void testGetChildElementArray() {
		final XMLDoc doc = new XMLDoc("Foo", null);
		final KElement root = doc.getRoot();
		Assert.assertEquals(root.getChildElementArray().length, 0);
		root.appendElement("bar:bar", "www.bar.com");
		root.appendElement("bar2");
		Assert.assertEquals(root.getChildElementArray().length, 2);
		Assert.assertEquals(root.getChildElementArray()[0], root.getElement("bar:bar"));
		Assert.assertEquals(root.getChildElementArray()[1], root.getElement("bar2"));
	}

	// //////////////////////////////////////////////////////////////////////////
	// ///

	/**
	 * 
	 */
	@Test
	public void testAttributeInfo() {
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final VString s = n.getNamesVector("Status");
		Assert.assertTrue("Status enum", s.contains("InProgress"));
	}

	/**
	 * 
	 */
	@Test
	public void testPushUp() {
		{// defines a logical test block
			// pushup from 4 to 1
			final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
			final JDFNode root = (JDFNode) jdfDoc.getRoot();
			KElement e = root;
			for (int i = 0; i < 5; i++) {
				e = e.appendElement("Test" + i, null);
			}
			e.pushUp("Test1");
			final KElement k = root.getXPathElement("Test0/Test1");
			final VElement v = k.getChildElementVector("Test4", null, new JDFAttributeMap(), true, 99999, false);
			Assert.assertTrue("pushUp does not work", v.size() == 1);
		}

		{// defines a logical test block
			// pushup with emptystring
			final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
			final JDFNode root = (JDFNode) jdfDoc.getRoot();
			KElement e = root;
			for (int i = 0; i < 5; i++) {
				e = e.appendElement("Test" + i, null);
			}
			e.pushUp("");
			final KElement k = root.getXPathElement("Test0/Test1/Test2");
			final VElement v = k.getChildElementVector("Test4", null, new JDFAttributeMap(), true, 99999, false);
			Assert.assertTrue("pushUp does not work", v.size() == 1);
		}

		{// defines a logical test block
			// pushup and force parentNode == null
			final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
			final JDFNode root = (JDFNode) jdfDoc.getRoot();
			KElement e = root;
			for (int i = 0; i < 5; i++) {
				e = e.appendElement("Test" + i, null);
			}
			final KElement k = e.pushUp("Foo");
			Assert.assertTrue(k == null);
		}
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testSetAttribute_DoubleAtt() throws Exception {
		final JDFParser p = new JDFParser();
		{
			final XMLDoc doc = new XMLDoc("d", null);
			final KElement root = doc.getRoot();
			root.setAttribute("a:b", "a1");
			String s2 = doc.write2String(0);
			XMLDoc doc2 = p.parseString(s2);
			Assert.assertNull("invalid ns stuff", doc2);
			root.setAttributeNS("www.a.com", "a:b", "a2");
			s2 = doc.write2String(0);
			doc2 = p.parseString(s2);
			Assert.assertNotNull("invalid ns stuff", doc2);
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
	public void testSetAttribute_NullCreate() {
		XMLDoc d = new XMLDoc();
		d.createElement("blub").setAttribute("xmlns:foo", "bar:fnarf");
	}

	/**
	 * test to emulate the creation of spurious NS1 prefixes
	 */
	@Test
	public void testNS1Raw() {
		XMLDoc d = new XMLDoc("a", "www.b.com");
		KElement root = d.getRoot();
		root.setAttributeNSRaw("www.a1.com", "a:b", "val1");
		root.setAttributeNSRaw("www.a2.com", "a:c", "val2");
		Assert.assertTrue(root.toString().indexOf("NS1") > 0);
	}

	/**
	 * 
	 */
	@Test
	public void testSetAttribute_NameSpaceHandling() {
		for (int dd = 0; dd < 2; dd++) {
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
			Assert.assertTrue(root.hasAttribute("aa"));

			final KElement c = root.appendElement("Comment", null);
			Assert.assertTrue("ns append ok", c.getNamespaceURI().equals(root.getNamespaceURI()));
			final KElement f = root.insertBefore("fnarf", c, null);
			Assert.assertTrue("ns insert ok", f.getNamespaceURI().equals(root.getNamespaceURI()));
			Assert.assertTrue("ns  ok", f.getNamespaceURI() != null);
			Assert.assertTrue("ns  ok", f.getNamespaceURI().equals(JDFConstants.JDFNAMESPACE));
			final KElement f2 = root.insertBefore("fnarf:fnarf", c, "www.fnarf");
			Assert.assertTrue("ns  ok", f2.getNamespaceURI().equals("www.fnarf"));

			// try to add an element into an unspecified namespace MUST FAIL
			try {
				e.appendElement("Kai:Test1");
				// assume that the namespace will be added later fail("snafu");
			} catch (final JDFException jdfe) {
				// do nothing
			}

			final String testIt = jdfDoc.write2String(0);
			final JDFParser p = new JDFParser();
			final JDFDoc d2 = p.parseString(testIt);
			root = (JDFNode) d2.getRoot();
			// root.setAttribute("aa","cc");
			root.setAttribute("aa", "nsns", root.getNamespaceURI());
			Assert.assertFalse("no ns1", root.hasAttribute("ns1:aa"));
			Assert.assertEquals("no ns1", root.getAttribute("aa", root.getNamespaceURI(), null), "nsns");
			root.setAttribute("bb:aa", "nsnt", root.getNamespaceURI());
			Assert.assertTrue("ns1 default", root.hasAttribute("aa"));
			Assert.assertTrue("no ns1", root.hasAttribute("bb:aa"));
			Assert.assertEquals("no ns1", root.getAttribute("aa", root.getNamespaceURI(), null), "nsnt");
			Assert.assertEquals("no ns1", root.getAttribute("aa", null, null), "nsnt");
		}
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testRemoveNS1() {
		XMLDoc d = new XMLDoc("foo", null);
		KElement root = d.getRoot();
		root.setAttribute("NS1:foo", "bar", "www.ns1.com");
		root.setAttribute("xxx:foo2", "bar", "www.yyy.com");
		root.renameAttribute("NS1:foo", "xxx:foo", null, null);
		root.removeAttribute("xmlns:NS1");
		Assert.assertTrue(d.toXML().indexOf("NS1") < 0);
	}

	/**
	 * 
	 *
	 */
	@Test
	public void testSetMixedLevelNS() {
		XMLDoc xmlDoc = new XMLDoc("d", null);
		KElement e = xmlDoc.getRoot();
		e.setAttribute("a:a", "a1", "www.a.com");
		e.setAttributeRaw("a:a", "a2");
		e.setAttributeRaw("b:b", "b1");
		e.setAttributeNSRaw("www.b.com", "b:b", "b2");
		e.setAttributeRaw("xmlns:c", "www.c.com");
		e.setAttributeRaw("c:c", "c1");
		e.setAttributeNSRaw("www.c.com", "c:c", "c2");
		String s = xmlDoc.write2String(2);
		Assert.assertTrue(s.indexOf("a1") < 0);
		Assert.assertFalse("calling ns and raw is still broken", s.indexOf("b1") < 0);
		Assert.assertFalse("calling ns and raw is still broken", s.indexOf("c1") < 0);
	}

	/**
	 * 
	 */
	@Test
	public void testXMLNameSpace() {
		Assert.assertNull("no ns", KElement.xmlnsPrefix("abc"));
		Assert.assertNull("no ns", KElement.xmlnsPrefix(":abc"));
		Assert.assertEquals("ns", "ns", KElement.xmlnsPrefix("ns:abc"));
		Assert.assertEquals("abc", "abc", KElement.xmlnsLocalName("ns:abc"));
		Assert.assertNull("no local name", KElement.xmlnsLocalName("abc:"));
		Assert.assertNull("no local name", KElement.xmlnsLocalName(null));
	}

	/**
	 * 
	 */
	@Test
	public void testAppendChild() {
		final XMLDoc d = new XMLDoc("e", null);
		final KElement e = d.getRoot();
		e.setAttribute("xmlns:foo", "www.foo.com");
		final KElement e2 = (KElement) d.createElement("e2");
		e.appendChild(e2);
		Assert.assertEquals(e.getFirstChild(), e2);
		final KElement e3 = (KElement) d.createElement("foo:e3");
		Assert.assertNull(e3.getNamespaceURI());
		e.appendChild(e3);
		Assert.assertEquals(e2.getNextSibling(), e3);
		Assert.assertEquals(e3.getNamespaceURI(), "www.foo.com");
	}

	/**
	 * 
	 */
	@Test
	public void testAppendCData() {
		final XMLDoc d = new XMLDoc("e", null);
		final KElement e = d.getRoot();
		final XMLDoc d2 = new XMLDoc("e2", null);
		final KElement e2 = d2.getRoot();
		e.appendCData(e2);
		Assert.assertTrue(e.toString().indexOf("<e2") > 0);
		e.appendCData(e);
		final JDFParser p = new JDFParser();
		final JDFDoc d3 = p.parseString(d.write2String(2));
		Assert.assertNotNull(d3);

	}

	/**
	 * 
	 */
	@Test
	public void testParseAppendChild() {
		final String s = "<e xmlns=\"a\" xmlns:foo=\"www.foo.com\"><e2/></e>";
		final JDFParser p = new JDFParser();
		p.bKElementOnly = true;
		p.ignoreNSDefault = true;

		final XMLDoc d = p.parseString(s);
		final KElement e = d.getRoot();
		final KElement e3 = (KElement) d.createElement("foo:e3");
		Assert.assertNull(e3.getNamespaceURI());
		e.appendChild(e3);
		final KElement e2 = (KElement) e.getFirstChild();
		Assert.assertEquals(e2.getNextSibling(), e3);
		// assertNull(e3.getNamespaceURI());
		final KElement e4 = (KElement) d.createElement("foo:e3");
		Assert.assertNull(e4.getNamespaceURI());
		e.appendChild(e4);
		// assertNull(e4.getNamespaceURI());
	}

	/**
	 * 
	 */
	@Test
	public void testSetXMLComment() {
		final XMLDoc d = new XMLDoc("e", null);
		final KElement root = d.getRoot();
		root.setXMLComment("foo");
		Assert.assertEquals(d.getDocumentElement().getParentNode().getFirstChild().getNodeValue(), "foo");
		root.setXMLComment("bar");
		Assert.assertEquals(d.getDocumentElement().getParentNode().getFirstChild().getNodeValue(), "bar");
		final KElement e2 = root.appendElement("e2");
		e2.setXMLComment("foobar");
		Assert.assertEquals(root.getFirstChild().getNodeValue(), "foobar");
		e2.setXMLComment("foobar2");
		Assert.assertEquals(root.getFirstChild().getNodeValue(), "foobar2");
		Assert.assertEquals(root.getFirstChild().getNextSibling(), e2);
		Assert.assertNull(root.getFirstChild().getNextSibling().getNextSibling());

	}

	/**
	 * 
	 */
	@Test
	public void testAppendElementRaw() {
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
		Assert.assertTrue(d3.getRoot().getXPathElement("ResourcePool") instanceof JDFResourcePool);
		final long t4 = System.currentTimeMillis();
		final JDFDoc d4 = new JDFDoc("JDF");
		copyElementRaw(d4.getRoot(), d3.getRoot());
		Assert.assertTrue(d4.getRoot().getXPathElement("ResourcePool") instanceof JDFResourcePool);
		final long t5 = System.currentTimeMillis();
		final JDFParser p = new JDFParser();
		final String s = d4.write2String(2);
		final JDFDoc d5 = p.parseString(s);
		Assert.assertNotNull(d5);
		final long t6 = System.currentTimeMillis();
		System.out.println("parse    " + (t2 - t));
		System.out.println("complete " + (t3 - t2));
		System.out.println("raw      " + (t4 - t3));
		System.out.println("raw 2    " + (t5 - t4));
		System.out.println("parse 2  " + (t6 - t5));
	}

	private void copyElement(final KElement dst, final KElement src) {
		KElement c = src.getFirstChildElement();
		dst.setAttributes(src);
		while (c != null) {
			final KElement newDst = dst.appendElement(c.getNodeName());
			copyElement(newDst, c);
			c = c.getNextSiblingElement();
		}
	}

	private void copyElementRaw(final KElement dst, final KElement src) {
		KElement c = src.getFirstChildElement();
		dst.setAttributesRaw(src);
		while (c != null) {
			final KElement newDst = dst.appendElementRaw(c.getNodeName(), null);
			copyElementRaw(newDst, c);
			c = c.getNextSiblingElement();
		}
	}

	/**
	 * 
	 */
	@Test
	public void testAppendElement() {

		final XMLDoc d = new XMLDoc("e", null);
		final KElement e = d.getRoot();
		Assert.assertNull(e.getNamespaceURI());
		final KElement foo = e.appendElement("pt:foo", "www.pt.com");
		Assert.assertEquals(foo.getNamespaceURI(), "www.pt.com");
		final KElement bar = foo.appendElement("bar");
		Assert.assertNull(bar.getNamespaceURI());
		bar.setAttribute("xmlns", "www.bar.com");

		final KElement bar2 = bar.appendElement("bar");
		Assert.assertEquals(bar2.getNamespaceURI(), "www.bar.com");

		final KElement foo2 = bar.appendElement("pt:foo", "www.pt.com");
		Assert.assertEquals(foo2.getNamespaceURI(), "www.pt.com");

		final KElement bar3 = bar.appendElement("bar");
		Assert.assertEquals(bar3.getNamespaceURI(), "www.bar.com");
		final KElement bar4 = foo2.appendElement("bar");
		Assert.assertEquals(bar4.getNamespaceURI(), "www.bar.com");
	}

	/**
	 * 
	 */
	@Test
	public void testAppendElement_NSAtt() {
		final XMLDoc d = new XMLDoc("e", null);
		final KElement e = d.getRoot();
		Assert.assertNull(e.getNamespaceURI());
		e.setAttribute("xmlns:pt", "www.pt.com");
		final KElement foo = e.appendElement("foo", null);
		Assert.assertNull(foo.getNamespaceURI());
		final KElement bar = foo.appendElement("bar");
		Assert.assertNull(bar.getNamespaceURI());
		final KElement bar2 = foo.appendElement("pt:bar");
		Assert.assertEquals(bar2.getNamespaceURI(), "www.pt.com");
	}

	/**
	 * 
	 */
	@Test
	public void testAppendElement_NSAttJDFDoc() {
		JDFDoc d = new JDFDoc("e");
		final String url = JDFElement.getSchemaURL();
		{
			final KElement e = d.getRoot();
			Assert.assertEquals(e.getNamespaceURI(), url);
			e.setAttribute("xmlns:pt", "www.pt.com");
			final KElement foo = e.appendElement("foo", null);
			Assert.assertEquals(foo.getNamespaceURI(), url);
			final KElement bar = foo.appendElement("bar");
			Assert.assertEquals(bar.getNamespaceURI(), url);
			final KElement bar2 = foo.appendElement("pt:bar");
			Assert.assertEquals(bar2.getNamespaceURI(), "www.pt.com");
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
			Assert.assertNull(e.getNamespaceURI());
			final KElement foo = e.appendElement("foo", null);
			Assert.assertNull(foo.getNamespaceURI());
			final KElement bar = foo.appendElement("bar");
			Assert.assertNull(bar.getNamespaceURI());
			final KElement bar2 = foo.appendElement("pt:bar");
			Assert.assertEquals(bar2.getNamespaceURI(), "www.pt.com");
		}
	}

	/**
	 * 
	 */
	@Test
	public void testAppendElement_AttRaw() {
		JDFDoc d = new JDFDoc("e");
		final String url = JDFElement.getSchemaURL();
		{
			final KElement e = d.getRoot();
			Assert.assertEquals(e.getNamespaceURI(), url);
			e.setAttributeRaw("pt:fnarf", "blubber");
			e.setAttributeRaw("xmlns:pt", "www.pt.com");
			final KElement foo = e.appendElement("foo", null);
			Assert.assertEquals(foo.getNamespaceURI(), url);
			final KElement bar = foo.appendElement("bar");
			Assert.assertEquals(bar.getNamespaceURI(), url);
			final KElement bar2 = foo.appendElement("pt:bar");
			Assert.assertEquals(bar2.getNamespaceURI(), "www.pt.com");
		}
		String s = d.write2String(0);

		// now check for parsed document with no default xmlns declaration
		final JDFParser p = new JDFParser();
		final int pos = s.indexOf(url);
		s = s.substring(0, pos - 7) + s.substring(pos + url.length() + 1); // +/-

		d = p.parseString(s);
		{
			final KElement e = d.getRoot();
			Assert.assertNull(e.getNamespaceURI());
			final KElement foo = e.appendElement("foo", null);
			Assert.assertNull(foo.getNamespaceURI());
			final KElement bar = foo.appendElement("bar");
			Assert.assertNull(bar.getNamespaceURI());
			final KElement bar2 = foo.appendElement("pt:bar");
			Assert.assertEquals(bar2.getNamespaceURI(), "www.pt.com");
		}
	}

	/**
	 * 
	 */
	@Test
	public void testAppendElement_SingleNS() {
		for (int i = 0; i < 2; i++) {
			final String wwwECom = "www.e.com";
			final XMLDoc d = i == 0 ? new XMLDoc() : new JDFDoc();
			d.setRoot("e", wwwECom);
			final KElement e = d.getRoot();
			e.addNameSpace(null, wwwECom);
			Assert.assertEquals(e.getNamespaceURI(), wwwECom);
			KElement foo = e.appendElement("f", null);
			Assert.assertEquals(foo.getNamespaceURI(), wwwECom);
			foo = e.appendElement("f", "");
			Assert.assertEquals(foo.getNamespaceURI(), wwwECom);
		}

	}

	/**
	 * 
	 */
	@Test
	public void testCreateElement_NoNS() {
		for (int i = 0; i < 2; i++) {
			final String wwwECom = "www.e.com";
			final XMLDoc d = i == 0 ? new XMLDoc() : new JDFDoc();
			d.setRoot("e", wwwECom);
			final KElement e = d.getRoot();
			Assert.assertEquals(e.getNamespaceURI(), wwwECom);
			Element eFoo = d.createElement("f");
			e.appendChild(eFoo);
			final Element eBar = d.createElement("b");
			eFoo.appendChild(eBar);
			Assert.assertEquals(eBar.getNamespaceURI(), wwwECom);
			Assert.assertEquals(eFoo.getNamespaceURI(), wwwECom);
			eFoo = d.createElementNS(wwwECom, "f");
			e.appendChild(eFoo);
			Assert.assertEquals(eFoo.getNamespaceURI(), wwwECom);
		}
	}

	/**
	 * 
	 */
	@Test
	public void testParse_SingleNS() {
		final String wwwECom = "www.e.com";
		final XMLDoc d = new XMLDoc("e", wwwECom);
		final KElement e = d.getRoot();
		Assert.assertEquals(e.getNamespaceURI(), wwwECom);
		final KElement foo = e.appendElement("f", null);
		Assert.assertEquals(foo.getNamespaceURI(), wwwECom);
		final String s = d.write2String(2);
		final JDFParser p = new JDFParser();
		final JDFDoc d2 = p.parseString(s);
		final KElement e2 = d2.getRoot();
		Assert.assertEquals(e2.getNamespaceURI(), wwwECom);
		final KElement foo2 = e.appendElement("f", null);
		Assert.assertEquals(foo2.getNamespaceURI(), wwwECom);
		Assert.assertEquals(-1, d2.write2String(2).indexOf("jdf"));
	}

	/**
	 * 
	 */
	@Test
	public void testAppendXMLComment() {
		final XMLDoc d = new XMLDoc("e", null);
		final KElement e = d.getRoot();
		final VString v = new VString("a . - . -- . -->.<!--", ".");
		for (int i = 0; i < v.size(); i++) {
			String s = v.stringAt(i);
			e.appendXMLComment(s, null);
			d.write2File(sm_dirTestDataTemp + "xmlComment.jdf", 2, false);
			final XMLDoc d2 = new JDFParser().parseFile(sm_dirTestDataTemp + "xmlComment.jdf");
			final KElement e2 = d2.getRoot();
			s = StringUtil.replaceString(s, "--", "__");
			Assert.assertEquals(e.getXMLComment(i), s);
			Assert.assertEquals(e2.getXMLComment(i), s);
		}
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testUniqueID() {
		String regExp = "_??????_?????????_??????";
		regExp = StringUtil.simpleRegExptoRegExp(regExp);
		for (int i = 0; i < 1000; i++) {
			Assert.assertTrue(StringUtil.matches(KElement.uniqueID(0), regExp));
		}
	}

	/**
	 * 
	 */
	@Test
	public void testAppendAttribute() {
		final XMLDoc d = new XMLDoc("e", null);
		final KElement e = d.getRoot();
		e.appendAttribute("at", "a", null, " ", false);
		e.appendAttribute("at", "b", null, " ", false);
		e.appendAttribute("at", "c", null, " ", false);
		Assert.assertEquals("a b c", e.getAttribute("at"), "a b c");
		e.appendAttribute("at", "c", null, " ", true);
		Assert.assertEquals("a b c", e.getAttribute("at"), "a b c");
		e.appendAttribute("at", "c", null, " ", false);
		Assert.assertEquals("a b c", e.getAttribute("at"), "a b c c");
		e.appendAttribute("at", "a a b c", null, null, true);
		Assert.assertEquals("a b c", e.getAttribute("at"), "a b c c a a b c");
		e.appendAttribute("ns:key", "na", "www.ns.com", " ", true);
		Assert.assertEquals("ns a", e.getAttribute("key", "www.ns.com", ""), "na");
		e.appendAttribute("ns:key", "nb", null, " ", true);
		Assert.assertEquals("ns a", e.getAttribute("ns:key"), "na nb");
		e.appendAttribute("ns:key", "nc", "www.ns.com", " ", true);
		Assert.assertEquals("ns a", e.getAttribute("key", "www.ns.com", ""), "na nb nc");
		Assert.assertEquals("ns a", e.getAttribute("ns:key"), "na nb nc");

		Assert.assertEquals("new", e.appendAttribute("fnarf", "new", null, null, true));
		Assert.assertEquals("new", e.appendAttribute("fnarf", "new", null, null, true));
		Assert.assertEquals("new moo", e.appendAttribute("fnarf", "moo", null, null, true));
		Assert.assertEquals("new moo", e.appendAttribute("fnarf", "moo", null, null, true));
		Assert.assertEquals("new moo moo", e.appendAttribute("fnarf", "moo", null, null, false));
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 * 
	 */
	@Test
	public void testToString() {
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		Assert.assertTrue(root.toString().trim().endsWith("<doc/>"));
	}

	/**
	 * 
	 */
	@Test
	public void testToXML() {
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		Assert.assertTrue(root.toXML().contains("<doc/>"));
		root.setAttribute("test", "\"");
		root.setAttribute("test2", "&");
		Assert.assertTrue(root.toXML().contains("&amp;"));
	}

	/**
	 * 
	 */
	@Test
	public void testToXMLParse() {
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		final String s = root.toXML();
		final JDFParser p = new JDFParser();
		Assert.assertNotNull(p.parseString(s));
	}

	/**
	 * 
	 */
	@Test
	public void testToXMLParseNS() {
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		root.addNameSpace("foo1", "www.foo1.com");
		KElement ns = root.appendElement("foo1:bar");
		root.setXSIType("typ");
		ns.setAttribute(JDFCoreConstants.XSITYPE, "foobar");
		final String s = ns.toXML();
		final JDFParser p = new JDFParser();
		Assert.assertNotNull(p.parseString(s));
	}

	// //////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testSetComment() {
		KElement root = new XMLDoc("root", null).getRoot();
		root.setXMLComment("foo");
		Assert.assertNotNull(root.getPreviousSibling());
	}

	/**
	 * 
	 */
	@Test
	public void testTextMethods() {
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		final KElement e1 = root.appendElement("e1");
		e1.setAttribute("a", "b");

		e1.setText("foo");
		Assert.assertEquals("foo", e1.getText());
		Assert.assertTrue(e1.hasChildText());

		e1.setText("bar");
		Assert.assertEquals("bar", e1.getText());
		Assert.assertTrue(e1.hasChildText());

		e1.removeAllText();
		Assert.assertNull(e1.getText());
		Assert.assertFalse(e1.hasChildText());

		e1.appendText("foo");
		Assert.assertEquals("foo", e1.getText());
		Assert.assertTrue(e1.hasChildText());

		e1.appendText("bar");
		Assert.assertEquals("foobar", e1.getText());
		Assert.assertTrue(e1.hasChildText());

		Assert.assertEquals(e1.getNumChildText(), 2);
		Assert.assertEquals("foo", e1.getText(0));
		Assert.assertEquals("bar", e1.getText(1));
		Assert.assertTrue(e1.hasChildText());

		e1.removeChildText(1);
		Assert.assertEquals(e1.getNumChildText(), 1);
		Assert.assertEquals("foo", e1.getText(0));
		Assert.assertTrue(e1.hasChildText());

		e1.removeChildText(0);
		Assert.assertEquals(e1.getNumChildText(), 0);
		Assert.assertEquals(null, e1.getText(0)); // getText(i) can return null
		Assert.assertNull(e1.getText()); // getText() can return null !!!
		Assert.assertFalse(e1.hasChildText());

		e1.removeAllText();
		Assert.assertFalse(e1.hasChildText());

		final KElement e2 = root.appendTextElement("e2", "text");
		Assert.assertEquals(e2.getNumChildText(), 1);
		Assert.assertEquals("text", e2.getText(0));
	}

	/**
	 * 
	 */
	@Test
	public void testFillHashSet() {
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		final KElement e1 = root.appendElement("e1");
		e1.setAttribute("a", "b");
		e1.setAttribute("a2", "b");
		root.setAttribute("a", "aa");

		e1.setXPathAttribute("./e2/e3@a", "c");
		e1.setXPathAttribute("./e3/e4@a", "d");
		HashSet<String> h = root.fillHashSet("a", null);

		Assert.assertTrue("", h.contains("aa"));
		Assert.assertTrue("", h.contains("b"));
		Assert.assertTrue("", h.contains("c"));
		Assert.assertTrue("", h.contains("d"));
		Assert.assertFalse("", h.contains("a2"));

		h = e1.fillHashSet("a", null);

		Assert.assertFalse("", h.contains("aa"));
		Assert.assertTrue("", h.contains("b"));
		Assert.assertTrue("", h.contains("c"));
		Assert.assertTrue("", h.contains("d"));
		Assert.assertFalse("", h.contains("a2"));
	}

	/**
	 * 
	 * test the flush method also for xml comments, cdata and similar crap
	 */
	@Test
	public void testFlush() {
		final XMLDoc doc = new XMLDoc("doc", null);
		KElement root = doc.getRoot();
		root.setXPathAttribute("foo/bar/murks/@a", "bbbb");
		root.appendXMLComment("aaabbbccc", null);
		root.setAttribute("fff", "ccc");
		root.appendCData("asb");
		root.flush();
		String xml = root.toXML();
		int pos = xml.indexOf("<d");
		xml = xml.substring(pos).trim();
		Assert.assertEquals(xml, "<doc/>");
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testWrite2File() {
		KElement e = KElement.createRoot("foo", null);
		KElement e2 = e.appendElement("bar");
		e.write2File(sm_dirTestDataTemp + "dummy.xml");
		KElement e3 = XMLDoc.parseFile(sm_dirTestDataTemp + "dummy.xml").getRoot();
		Assert.assertTrue(e.isEqual(e3));
		e2.write2File(sm_dirTestDataTemp + "dummy2.xml");
		KElement e4 = XMLDoc.parseFile(sm_dirTestDataTemp + "dummy2.xml").getRoot();
		Assert.assertTrue(e2.isEqual(e4));
	}
}
