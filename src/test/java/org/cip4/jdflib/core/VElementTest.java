/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */

package org.cip4.jdflib.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.junit.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         05.12.2008
 */
public class VElementTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	public void testAddAll()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement e = d.getRoot();
		final VElement v = new VElement();
		v.addAll((VElement) null);
		assertEquals(v.size(), 0);
		v.add(e);
		assertEquals(v.size(), 1);
		v.addAll(v);
		assertEquals(v.size(), 2);
		v.addAll(v);
		assertEquals(v.size(), 4);
	}

	/**
	 *
	 */
	@Test
	public void testAppendUnique()
	{
		final KElement e = KElement.createRoot("doc", null);
		final VElement v = new VElement();
		for (int i = 0; i < 4; i++)
			v.appendUnique(e.appendElement("a"));
		assertEquals(" identical but different elements are appended!", v.size(), 4);

	}

	/**
	 *
	 */
	@Test
	public void testAddAllArray()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement e = d.getRoot();
		final VElement v = new VElement();
		v.addAll((VElement) null);
		assertEquals(v.size(), 0);
		v.add(e);
		assertEquals(v.size(), 1);
		final KElement[] k = new KElement[4];
		k[0] = e;
		k[1] = e.appendElement("b");
		k[2] = e.appendElement("c");

		v.addAll((JDFJMF[]) null);
		assertEquals(v.size(), 1);
		v.addAll(k);
		assertEquals("null 4the element is ignored...", v.size(), 4);
	}

	/**
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testCastVector()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final VElement v = new VElement();
		v.add(n);
		final List<JDFNode> ll = (Vector) v;
		ll.get(0).addResource("foo", EnumUsage.Input);

	}

	/**
	 *
	 */
	@Test
	public void testGet()
	{
		final VElement v = new VElement();
		final XMLDoc d = new XMLDoc("e", null);
		final KElement r = d.getRoot();
		final KElement e1 = r.appendElement("e1");
		v.add(r);
		v.add(e1);
		assertEquals(v.get(0), r);
		assertEquals(v.get(-1), e1);
		assertEquals(v.get(-2), r);
		assertNull(v.get(2));
	}

	/**
	 *
	 */
	@Test
	public void testItem()
	{
		final VElement v = new VElement();
		final XMLDoc d = new XMLDoc("e", null);
		final KElement r = d.getRoot();
		final KElement e1 = r.appendElement("e1");
		v.add(r);
		v.add(e1);
		assertEquals(v.item(-1), e1);
		assertEquals(v.item(-2), r);
		assertNull(v.item(2));
	}

	/**
	 *
	 */
	@Test
	public void testElementAt()
	{
		final VElement v = new VElement();
		final XMLDoc d = new XMLDoc("e", null);
		final KElement r = d.getRoot();
		final KElement e1 = r.appendElement("e1");
		v.add(r);
		v.add(e1);
		assertEquals(v.elementAt(-1), e1);
		assertEquals(v.elementAt(-2), r);
		assertNull(v.elementAt(2));
	}

	/**
	*
	*/
	@Test
	public void testRemoveElements()
	{
		final VElement v = new VElement();
		final VElement v2 = new VElement();
		final XMLDoc d = new XMLDoc("e", null);
		final KElement r = d.getRoot();
		for (int i = 0; i < 2222; i++)
		{
			for (int j = 1; j < 4; j++)
			{
				final KElement e1 = r.appendElement("e" + j);
				v.add(e1);
			}
			final KElement e2 = r.appendElement("e2");
			v2.add(e2);
		}
		v.removeElements(null);
		assertEquals(v.size(), 6666);
		v.removeElements(v2);
		assertEquals(v.size(), 4444);
	}

	/**
	*
	*/
	@Test
	public void testRemoveElement()
	{
		final VElement v = new VElement();
		final XMLDoc d = new XMLDoc("e", null);
		final KElement r = d.getRoot();
		for (int i = 0; i < 2222; i++)
		{
			for (int j = 1; j < 4; j++)
			{
				final KElement e1 = r.appendElement("e" + j);
				v.add(e1);
			}
		}
		final KElement e2 = r.appendElement("e2");

		v.removeElements(null, 100);
		assertEquals(v.size(), 6666);
		v.removeElements(e2, 100);
		assertEquals(v.size(), 6566);
		v.removeElements(e2, 10000);
		assertEquals(v.size(), 4444);
	}

	/**
	 *
	 */
	@Test
	public void testContainsElement()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement e = d.getRoot();
		KElement e1 = e.appendElement("e1");
		e1.setAttribute("a", "b");
		final VElement v = new VElement();
		v.appendUnique(e1);
		e1 = e.appendElement("e1");
		e1.setAttribute("a", "b");
		assertTrue("containsElement", v.containsElement(e1));
		assertFalse("contains", v.contains(e1));
		e1.setText("foo");
		assertFalse("containsElement", v.containsElement(e1));
		v.appendUnique(e1);
		assertEquals("size", v.size(), 2);
		e1 = e.appendElement("e1");
		e1.setAttribute("a", "b");
		e1.setText("foo");
		assertTrue("containsElement", v.containsElement(e1));
		e1.setText("bar");
		assertFalse("containsElement", v.containsElement(e1));

	}

	/**
	 *
	 */
	@Test
	public void testgetNodeNames()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement e = d.getRoot();
		e.appendElement("a1");
		e.appendElement("b:a2", "b");
		final VElement v = e.getChildElementVector(null, null, null, true, 0, true);
		VString s = v.getElementNameVector(false);
		assertEquals(s, new VString("a1 b:a2", " "));
		s = v.getElementNameVector(true);
		assertEquals(s, new VString("a1 a2", " "));
	}

	// ///////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testUnify()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement e = d.getRoot();
		KElement e1 = e.appendElement("e1");
		e1.setAttribute("a", "b");
		final VElement v = new VElement();
		v.add(e1);
		v.add(e1);
		e1 = e.appendElement("e1");
		e1.setAttribute("a", "b");
		v.add(e1);
		assertEquals(v.size(), 3);
		v.unify();
		assertEquals(v.size(), 2);
		v.unifyElement();
		assertEquals(v.size(), 1);
	}

	/**
	 *
	 */
	@Test
	public void testUnifyElement()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement e = d.getRoot();
		final VElement v = new VElement();
		for (int i = 0; i < 100; i++)
		{
			final KElement e1 = e.appendElement("e1");
			e1.setAttribute("a", "b");
			v.add(e1);
			final KElement e2 = e.appendElement("e1");
			e1.setAttribute("a", "c");
			v.add(e2);
			final KElement e3 = e.appendElement("e3");
			e1.setAttribute("a", "c");
			v.add(e3);
		}
		v.unify();
		assertEquals(v.size(), 300);
		v.unifyElement();
		assertEquals(v.size(), 3);
	}

	/**
	 *
	 */
	@Test
	public void testUnifyElementPerformance()
	{
		final JDFDoc dBDoc = creatXMDoc();

		final XMLDoc d = new XMLDoc("doc", null);
		final KElement e = d.getRoot();
		final VElement v = new VElement();
		final long t00 = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++)
		{
			final KElement newRoot = dBDoc.clone().getRoot();
			e.copyElement(newRoot, null);
			v.add(newRoot);
		}
		System.out.println("t00: " + (System.currentTimeMillis() - t00));
		v.unify();
		assertEquals(v.size(), 1000);
		final long t0 = System.currentTimeMillis();
		v.unifyElement();
		assertEquals(v.size(), 1);
		System.out.println("t: " + (System.currentTimeMillis() - t0));
	}

	// ///////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testSort()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement e = d.getRoot();
		final KElement e1 = e.appendElement("e1");
		e1.setAttribute("a", "z");
		final KElement e2 = e.appendElement("e1");
		e2.setAttribute("a", "c");
		final VElement v = new VElement();
		v.add(e1);
		v.add(e2);
		v.sort();
		assertEquals(v.get(0), e2);
	}

	// ///////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testIsEqual()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement e = d.getRoot();
		final KElement e1 = e.appendElement("e1");
		e1.setAttribute("a", "b");
		final KElement e2 = e.appendElement("e1");
		e2.setAttribute("a", "c");
		final KElement e3 = e.appendElement("e1");
		e3.setAttribute("a", "c");
		final KElement e4 = e.appendElement("e1");
		e4.setAttribute("a", "d");
		final VElement v = new VElement();
		v.add(e1);
		v.add(e2);
		final VElement v2 = new VElement(v);
		assertTrue(v.isEqual(v2));
		v2.set(1, e3);
		assertTrue(v.isEqual(v2));
		v2.set(1, e4);
		assertFalse(v.isEqual(v2));
	}

	/**
	 *
	 */
	@Test
	public void testIndex()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement e = d.getRoot();
		final KElement e1 = e.appendElement("e1");
		e1.setAttribute("a", "b");
		final KElement e2 = e.appendElement("e1");
		e2.setAttribute("a", "c");
		final KElement e3 = e.appendElement("e1");
		e3.setAttribute("ID", "id3");
		e3.setAttribute("a", "a1");
		final KElement e4 = e.appendElement("e1");
		e4.setAttribute("ID", "id3");
		e4.setAttribute("a", "a2");
		final VElement v = new VElement();
		v.add(e1);
		v.add(e2);
		assertEquals(v.index(e1), 0);
		assertEquals(v.index(e4), -1);
		v.add(e3);
		assertEquals("test for equivalent ID aqttribute", v.index(e4), 2);
	}

	/**
	 *
	 */
	@Test
	public void testNameIndex()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement e = d.getRoot();
		final KElement e1 = e.appendElement("e1");
		final KElement e2 = e.appendElement("e2");

		final VElement v = new VElement();
		v.add(e1);
		v.add(e2);
		v.add(e1);
		v.add(e1);
		assertEquals(0, v.nameIndex("e1", 0));
		assertEquals(2, v.nameIndex("e1", 1));
		assertEquals(3, v.nameIndex("e1", 2));
	}

}
