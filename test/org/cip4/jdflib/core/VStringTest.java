/**
 * KElementTest.java
 * 
 * @author Dietrich Mucha
 *
 * Copyright (C) 2002 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.core;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.util.StringUtil;

public class VStringTest extends TestCase
{
	public void testGetAllString()
	{
		VString v = new VString();
		v.appendUnique("a");
		v.appendUnique("b");
		v.appendUnique("c");
		v.appendUnique("c");
		assertEquals("a b c", StringUtil.setvString(v, " ", null, null),
				"a b c");

	}

	public void testContainsAny()
	{
		VString v = new VString();
		v.appendUnique("a");
		v.appendUnique("b");
		v.appendUnique("c");
		v.appendUnique("c");
		assertFalse(v.containsAny(null));
		assertFalse(v.containsAny(new VString("d e", " ")));
		assertTrue(v.containsAny(new VString("b e", " ")));
		assertTrue(v.containsAny(new VString("e b", " ")));
		assertTrue(v.containsAny(new VString("g c h", " ")));
		assertTrue(v.containsAny(v));

	}

	public void testSort()
	{
		VString v = new VString();
		v.add("a");
		v.add("c");
		v.add("b");
		v.sort();
		assertEquals("a b c", StringUtil.setvString(v, " ", null, null),
				"a b c");

	}

	public void testGetSet()
	{
		VString v = new VString();
		v.add("a");
		v.add("c");
		v.add("b");
		Set s = v.getSet();
		assertEquals(v.size(), s.size());
		assertTrue(s.contains("c"));

	}

	// /////////////////////////////////////////////////

	public void testUnify()
	{
		VString v = new VString();
		v.add("a");
		v.add("b");
		v.add("c");
		v.add("c");
		VString w = new VString();
		w.add("c");
		w.add("b");
		w.add("a");
		w.add("a");
		w.add("d");

		v.unify();
		assertEquals("a b c", StringUtil.setvString(v, " ", null, null),
				"a b c");
		v.appendUnique(w);
		assertEquals("a b c d", StringUtil.setvString(v, " ", null, null),
				"a b c d");

	}

	public void testAddAll()
	{
		VString v = new VString();
		v.add("a");
		v.add("b");
		v.add("c");
		v.add("c");
		HashSet h = new HashSet();
		h.add("c");
		h.add("b");
		h.add("a");
		h.add("d");

		v.unify();
		assertEquals("a b c", StringUtil.setvString(v, " ", null, null),
				"a b c");
		v.addAll(h);
		v.unify();
		assertEquals("a b c d", StringUtil.setvString(v, " ", null, null),
				"a b c d");

	}

	public void testadd()
	{
		VString v = new VString();
		v.add(EnumType.AdhesiveBinding);
		assertEquals(StringUtil.setvString(v, " ", null, null),
				EnumType.AdhesiveBinding.getName());

	}

	public void testSetElementAt()
	{
		VString v = new VString();
		v.add("a");
		v.add("b");
		v.add("c");
		v.add("c");
		v.add("e");
		v.setElementAt("d", 3);
		assertEquals("a b c d e", StringUtil.setvString(v, " ", null, null));

	}

}
