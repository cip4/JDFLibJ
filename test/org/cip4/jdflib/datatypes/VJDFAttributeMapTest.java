/*
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */

/**
 * JDFXYPairRangeListTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class VJDFAttributeMapTest extends JDFTestCaseBase
{
	/**
	 * tests clone
	 */
	public void testClone()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap((VJDFAttributeMap) null);
		v.add(m1);
		v.add(m2);
		VJDFAttributeMap v2 = new VJDFAttributeMap(v);
		assertEquals(v, v2);
		v2 = v.clone();
		assertEquals(v, v2);
		m1.put("a3", "a4");
		assertFalse("modification did not migrate!", v.equals(v2));
	}

	/**
	 * tests subMap()
	 */
	public void testSubMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		assertTrue(v.subMap(m1));
		assertTrue(v.subMap(m2));
		v.put("a3", "v4");
		final JDFAttributeMap m3 = new JDFAttributeMap(m1);
		assertTrue(v.subMap(m3));
		m3.put("a3", "v5");
		assertFalse(v.subMap(m3));
	}

	/**
	 * tests OvelapsMap for individual maps
	 */
	public void testOverlapsMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		assertTrue(v.overlapsMap(m1));
		assertFalse(v.overlapsMap(new JDFAttributeMap("a2", "v4")));
	}

	/**
	 * tests OvelapsMap for individual maps
	 */
	public void testGetAndMaps()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a3", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		final VJDFAttributeMap vAnd = v.getAndMaps(new JDFAttributeMap("a1", "v1"));
		assertEquals(vAnd.size(), 1);
		assertEquals(vAnd.get(0), new JDFAttributeMap("a1", "v1"));
	}

	/**
	 * 
	 *  
	 */
	public void testHash()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a3", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		int i = v.hashCode();
		v.add(m2);
		int i2 = v.hashCode();
		assertNotSame(i, i2);

	}

	/**
	 * tests OvelapsMap for individual maps
	 */
	public void testGetOrMaps()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a3", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		final VJDFAttributeMap vAnd = v.getOrMaps(new JDFAttributeMap("a1", "v1"));
		assertEquals(vAnd.size(), 2);
		assertEquals(vAnd, v);
	}

	/**
	 * tests OvelapsMap for vectors of maps
	 */
	public void testOverlapsMapVector()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		assertTrue(v.overlapsMap(v2));
		v2.add(new JDFAttributeMap(m1));
		assertTrue(v.overlapsMap(v2));
		v2.add(new JDFAttributeMap("a2", "v4"));
		assertTrue(v.overlapsMap(v2));
		v.put("foo", "bar");
		assertTrue(v.overlapsMap(v2));
		v2.put("foo", "notbar");
		assertFalse(v.overlapsMap(v2));
	}

	/**
	 * tests addAll
	 */
	public void testAddAll()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMap m3 = new JDFAttributeMap(m1);
		m3.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		v2.add(m2);
		v2.add(m3);
		v.addAll(v2);
		assertEquals(v.size(), 4);
		assertTrue(v.contains(m1));
		assertTrue(v.contains(m2));
		assertTrue(v.contains(m3));

	}

	/**
	 * 
	 */
	public void testShowKeys()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap();
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		m2.put("a3", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		assertEquals(v.showKeys("-", " "), "[0](a2 = v2)-[1](a2 = v3) (a3 = v3)");
	}

	/**
	 * test Unify
	 */
	public void testUnifyBig()
	{
		final VJDFAttributeMap v = new VJDFAttributeMap();
		final long t0 = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++)
		{
			for (int j = 0; j < 100; j++)
			{
				final JDFAttributeMap map = new JDFAttributeMap();
				for (int k = 0; k < 10; k++)
				{
					map.put("A" + k, "k" + j + " " + k);
				}
				v.add(map);
			}
		}
		final long t1 = System.currentTimeMillis();
		System.out.println(t1 - t0);
		v.unify();
		final long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);
	}

	/**
	 * test Unify
	 */
	public void testUnify()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMap m3 = new JDFAttributeMap(m1);
		m3.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		v.add(m3);
		v.unify();
		assertEquals(v.size(), 2);
		assertTrue(v.contains(m1));
		assertTrue(v.contains(m2));
		assertTrue(v.contains(m3));
		v.add(m1);
		v.add(m2);
		v.add(m3);
		v.add(m1);
		v.add(m2);
		v.add(m3);
		v.unify();
		assertEquals(v.size(), 2);
		assertTrue(v.contains(m1));
		assertTrue(v.contains(m2));
		assertTrue(v.contains(m3));

		v.add(null);
		v.unify();
		assertEquals(v.size(), 3);
		assertTrue(v.contains(m1));
		assertTrue(v.contains(m2));
		assertTrue(v.contains(m3));
		assertTrue(v.contains(null));

	}

	/**
	 * test Equals()
	 */
	public void testEquals()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		v2.add(m2);
		v2.add(m1);
		assertEquals("mixed ordering", v, v2);
		v2.add(m1);
		assertFalse("mixed ordering -other cardinality ", v.equals(v2));
	}

	/**
	 * test Equals()
	 */
	public void testIndexOf()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		assertEquals(v.indexOf(m1), 0);
		assertEquals(v.indexOf(m2), 1);
		assertEquals(v.indexOf(new JDFAttributeMap()), -1);
	}

	/**
	 * tests copy constructor
	 */
	public void testCopy()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		v2.add(new JDFAttributeMap(m1));
		final VJDFAttributeMap v3 = new VJDFAttributeMap(v2);
		assertEquals(v2, v3);
	}

	/**
	 * tests maxSize method
	 */
	public void testMaxSize()
	{
		JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		assertEquals(v2.maxSize(), 0);
		v2.add(m1);
		assertEquals(v2.maxSize(), 1);
		m1 = m1.clone();
		m1.put("b", "c");
		v2.add(m1);
		assertEquals(v2.maxSize(), 2);
		m1.put("bc", "c");
		assertEquals(v2.maxSize(), 3);
	}

	/**
	 * 
	 */
	public void testMinSize()
	{
		JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		assertEquals(v2.minSize(), 0);
		v2.add(m1);
		m1.put("b", "c");
		assertEquals(v2.minSize(), 2);
		m1 = m1.clone();
		v2.add(m1);
		assertEquals(v2.minSize(), 2);
		m1.put("bc", "c");
		assertEquals(v2.minSize(), 2);
	}

	/**
	 * tests put method
	 */
	public void testPut()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		v2.add(m1);
		final VJDFAttributeMap v3 = new VJDFAttributeMap(v2);
		assertEquals(v2, v3);
		v3.put("a2", "b");
		m1.put("a2", "b");
		assertEquals(v2, v3);
		final VJDFAttributeMap v4 = new VJDFAttributeMap((VJDFAttributeMap) null);
		v4.put("a1", "b1");
		assertEquals(v4.size(), 1);
		final VJDFAttributeMap v5 = new VJDFAttributeMap();
		v5.put(EnumPartIDKey.Side, EnumSide.Front);
		assertEquals(v5.size(), 1);
	}

	// /////////////////////////////////////////////////////////////
	/**
	 * test reduceMap()
	 */
	public void testReduceMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		v2.add(new JDFAttributeMap(m1));
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		final VString vs = new VString("a1", " ");
		v.reduceMap(vs.getSet());
		assertEquals(v, v2);
	}

	/**
	 * test reduceMap()
	 */
	public void testRemoveMaps()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		v2.add(m1);
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		m2.put("a3", "v3");
		v2.add(m2);
		v2.removeMaps(new JDFAttributeMap("a3", "v43"));
		assertEquals(v2.size(), 2);
		v2.removeMaps(new JDFAttributeMap("a3", "v3"));
		assertEquals(v2.size(), 1);
		assertEquals(v2.get(0), m1);
		v2.add(m2);
		v2.removeMaps(new JDFAttributeMap("a1", "v1"));
		assertEquals(v2.size(), 0);
	}

	// /////////////////////////////////////////////////////////////
	/**
	 * test removeKeys()
	 */
	public void testRemoveKeys()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		v2.add(new JDFAttributeMap(m1));
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		final VString vs = new VString("a2", " ");
		v.removeKeys(vs.getSet());
		assertEquals(v, v2);
		assertEquals(v.size(), 1);
	}
	// /////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////

}
