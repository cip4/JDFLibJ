/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.util.StringUtil;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
class VStringTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	void testGetAllString()
	{
		final VString v = new VString();
		v.appendUnique("a");
		v.appendUnique("b");
		v.appendUnique("c");
		v.appendUnique("c");
		assertEquals(StringUtil.setvString(v, " ", null, null), "a b c", "a b c");

	}

	/**
	 *
	 */
	@Test
	void testGet()
	{
		final VString v = new VString();
		v.add("a");
		v.add("b");
		v.add("c");
		v.add("c");
		assertEquals("a", v.get(0));
		assertEquals("c", v.get(3));
		assertEquals("c", v.get(-2));
		assertEquals("b", v.get(-3));
		assertNull(v.get(-6), "b");
		assertNull(v.get(7), "b");
	}

	/**
	 *
	 */
	@Test
	void testElementAt()
	{
		final VString v = new VString();
		v.add("a");
		v.add("b");
		v.add("c");
		v.add("c");
		assertEquals("a", v.elementAt(0));
		assertEquals("c", v.elementAt(3));
		assertEquals("c", v.elementAt(-2));
		assertEquals("b", v.elementAt(-3));
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	void testRemove() throws Exception
	{
		final VString v = new VString("a b c", null);
		assertEquals(v.remove(-1), "c");
		assertEquals(v.remove(-1), "b");
		assertEquals(v.size(), 1);
	}

	/**
	 *
	 */
	@Test
	void testRemoveStrings()
	{
		final VString v = new VString();
		v.add("a");
		v.add("b");
		v.add("c");
		v.add("c");
		VString v2 = new VString(v);
		v2.removeStrings((String) null, 2);
		assertEquals(v2, v);
		v2 = new VString(v);
		v2.removeStrings("c", 1);
		assertEquals(v2.size(), 3);
		assertTrue(v2.contains("c"));
		v2 = new VString(v);
		v2.removeStrings("c", 0);
		assertFalse(v2.contains("c"));
	}

	/**
	 *
	 */
	@Test
	void testRemoveStringsV()
	{
		final VString v = new VString();
		v.add("a");
		v.add("b");
		v.add("c");
		v.add("c");
		VString v2 = new VString(v);
		v2.removeStrings((VString) null, 2);
		assertEquals(v2, v);
		v2 = new VString(v);
		v2.removeStrings(new VString("a c", null), 1);
		assertEquals(v2.size(), 3);
		assertTrue(v2.contains("c"));
		v2 = new VString(v);
		v2.removeStrings(new VString("a c", null), 0);
		assertFalse(v2.contains("c"));
		v2 = new VString(v);
		v2.removeStrings(new VString("a b c", null), 0);
		assertEquals(v2.size(), 0);
	}

	/**
	 *
	 */
	@Test
	void testContainsAny()
	{
		final VString v = new VString();
		v.appendUnique("a");
		v.appendUnique("b");
		v.appendUnique("c");
		v.add("c");
		assertTrue(v.containsAny(null));
		assertFalse(v.containsAny(new VString("d e", " ")));
		assertTrue(v.containsAny(new VString("b e", " ")));
		assertTrue(v.containsAny(new VString("e b", " ")));
		assertTrue(v.containsAny(new VString("g c h", " ")));
		assertTrue(v.containsAny(v));

	}

	/**
	 *
	 */
	@Test
	void testIsEmpty()
	{
		assertTrue(VString.isEmpty(null));
		final VString v = new VString();
		assertTrue(VString.isEmpty(v));
		v.appendUnique("");
		assertTrue(VString.isEmpty(v));
		v.set(0, "b");
		assertFalse(VString.isEmpty(v));
	}

	/**
	 *
	 */
	@Test
	void testGetOverlapping()
	{
		final VString v = new VString();
		v.appendUnique("a");
		v.appendUnique("b");
		v.appendUnique("c");
		v.add("c");
		assertNull(v.getOverlapping(null));
		assertNull(v.getOverlapping(new VString("d e", " ")));
		assertEquals(new VString("b", null), v.getOverlapping(new VString("b e", " ")));
		assertEquals(new VString("c c", null), v.getOverlapping(new VString("c", " ")));
		assertEquals(new VString("b c c", null), v.getOverlapping(new VString("b c c c", " ")));
		assertEquals(v, v.getOverlapping(v));
	}

	/**
	 *
	 */
	@Test
	void testSort()
	{
		final VString v = new VString();
		v.add("a");
		v.add("c");
		v.add("b");
		v.sort();
		assertEquals(StringUtil.setvString(v, " ", null, null), "a b c", "a b c");
	}

	/**
	*
	*/
	@Test
	void testPerformance()
	{
		final long t0 = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++)
		{
			final VString v = new VString();
			for (int j = 0; j < 1000; j++)
			{
				v.add("" + j);
			}
		}
		final long t1 = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++)
		{
			final ArrayList<String> v = new ArrayList<>();
			for (int j = 0; j < 1000; j++)
			{
				v.add("" + j);
			}
		}
		final long t2 = System.currentTimeMillis();
		final Vector<String> v = new Vector<>();
		for (int i = 0; i < 1000; i++)
		{
			v.add("" + i);
		}
		for (int i = 0; i < 10000; i++)
		{
			for (int j = 0; j < 1000; j++)
			{
				final String s = v.get(j);
			}
		}
		final long t3 = System.currentTimeMillis();
		final ArrayList<String> a = new ArrayList<>();
		for (int i = 0; i < 1000; i++)
		{
			a.add("" + i);
		}
		for (int i = 0; i < 10000; i++)
		{
			for (int j = 0; j < 1000; j++)
			{
				final String s = a.get(j);
			}
		}
		final long t4 = System.currentTimeMillis();
		log.info(("v " + (t1 - t0)));
		log.info(("a " + (t2 - t1)));
		log.info(("vg " + (t3 - t2)));
		log.info(("ag " + (t4 - t3)));
	}

	/**
	 *
	 */
	@Test
	void testSetNull()
	{
		final VString v = new VString();
		v.add((String) null);
		v.add("b");
		v.add("c");
		assertEquals(StringUtil.setvString(v, " ", null, null), "b c", "b c");
	}

	/**
	 *
	 */
	@Test
	void testGetSet()
	{
		final VString v = new VString();
		v.add("a");
		v.add("c");
		v.add("b");
		final Set<?> s = v.getSet();
		assertEquals(v.size(), s.size());
		assertTrue(s.contains("c"));

	}

	// /////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testUnify()
	{
		final VString v = new VString();
		v.add("a");
		v.add("b");
		v.add("c");
		v.add("c");
		final VString w = new VString();
		w.add("c");
		w.add("b");
		w.add("a");
		w.add("a");
		w.add("d");

		v.unify();
		assertEquals(StringUtil.setvString(v, " ", null, null), "a b c", "a b c");
		v.appendUnique(w);
		assertEquals(StringUtil.setvString(v, " ", null, null), "a b c d", "a b c d");

	}

	/**
	 *
	 */
	@Test
	void testGetString()
	{
		final VString v = new VString();
		v.add("a");
		v.add("b");
		v.add("c");
		v.add("c");
		assertEquals("a b c c", v.getString());
	}

	/**
	 *
	 */
	@Test
	void testGetStringABC()
	{
		final VString v = new VString();
		v.add("a");
		v.add("b");
		v.add("c");
		v.add("c");
		assertEquals("abcc", v.getString(null, null, null));
	}

	/**
	 *
	 */
	@Test
	void testAddAll()
	{
		final VString v = new VString();
		v.add("a");
		v.add("b");
		v.add("c");
		v.add("c");
		final HashSet<String> h = new HashSet<>();
		h.add("c");
		h.add("b");
		h.add("a");
		h.add("d");

		v.unify();
		assertEquals(StringUtil.setvString(v, " ", null, null), "a b c", "a b c");
		v.addAll(h);
		v.unify();
		assertEquals(StringUtil.setvString(v, " ", null, null), "a b c d", "a b c d");

	}

	/**
	 *
	 */
	@Test
	void testAddNonEmpty()
	{
		final VString v = new VString();
		v.addNonEmpty("");
		assertEquals(0, v.size());
		v.addNonEmpty(null);
		assertEquals(0, v.size());
		v.addNonEmpty(" ");
		assertEquals(1, v.size());
	}

	/**
	*
	*/
	@Test
	void testAppendUnique()
	{
		final VString v = new VString();
		v.appendUnique((String) null);
		assertEquals(0, v.size());
		v.appendUnique("a");
		assertEquals(1, v.size());
		v.appendUnique("b");
		assertEquals(2, v.size());
		v.appendUnique("c");
		assertEquals(3, v.size());
		v.appendUnique("c");
		assertEquals(3, v.size());
	}

	/**
	*
	*/
	@Test
	void testAppendUniqueV()
	{
		final VString v = new VString();
		v.appendUnique((String) null);
		assertEquals(0, v.size());
		v.appendUnique("a");
		assertEquals(1, v.size());
		v.appendUnique(v);
		assertEquals(1, v.size());
		final VString v2 = new VString("b c");
		v.appendUnique(v2);
		assertEquals(3, v.size());
	}

	/**
	*
	*/
	@Test
	void testAppendUniqueV2()
	{
		final VString v = new VString();
		v.appendUnique((String) null);
		assertEquals(0, v.size());
		v.appendUnique("a");
		assertEquals(1, v.size());
		v.appendUnique(v);
		assertEquals(1, v.size());
		final List<String> v2 = new StringArray("b c");
		v.appendUnique(v2);
		assertEquals(3, v.size());
	}

	/**
	 *
	 */
	@Test
	void testConstructEmpty()
	{
		VString v = new VString("", ",");
		assertEquals(v.size(), 0);
		v = new VString((String) null, null);
		assertEquals(v.size(), 0);
		v = new VString((String[]) null);
		assertEquals(v.size(), 0);
		v = new VString((new String[] {}));
		assertEquals(v.size(), 0);
		v = new VString((VString) null);
		assertEquals(v.size(), 0);
		v = new VString(new VString());
		assertEquals(v.size(), 0);
	}

	/**
	 *
	 */
	@Test
	void testConstructDouble()
	{
		VString v = new VString("a,b,c, ,", ",");
		assertEquals(v.get(-1), " ");
		v = new VString("a,b,c,,", ",");
		assertEquals(v.get(-1), "c", "double tokens are ignored");
	}

	/**
	 *
	 */
	@Test
	void testFactory()
	{
		assertNull(VString.getVString(null, null));
		assertNull(VString.getVString("", null));
		assertEquals(VString.getVString("a", null).get(0), "a");
	}

	/**
	 *
	 */
	@Test
	void testadd()
	{
		final VString v = new VString();
		v.add(EnumType.AdhesiveBinding);
		assertEquals(StringUtil.setvString(v, " ", null, null), EnumType.AdhesiveBinding.getName());
	}

	/**
	 *
	 */
	@Test
	void testSetElementAt()
	{
		final VString v = new VString();
		v.add("a");
		v.add("b");
		v.add("c");
		v.add("c");
		v.add("e");
		v.setElementAt("d", 3);
		assertEquals("a b c d e", StringUtil.setvString(v, " ", null, null));

	}

}
