/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */

/**
 * JDFXYPairRangeListTest.java
 *
 * @author Elena Skobchenko
 *
 *         Copyright (c) 2001-2004 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class VJDFAttributeMapTest extends JDFTestCaseBase
{
	/**
	 * tests clone
	 */
	@Test
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
		Assertions.assertEquals(v, v2);
		v2 = v.clone();
		Assertions.assertEquals(v, v2);
		m1.put("a3", "a4");
		Assertions.assertFalse(v.equals(v2), "modification did not migrate!");
	}

	/**
	 * tests subMap()
	 */
	@Test
	public void testSubMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		Assertions.assertTrue(v.subMap(m1));
		Assertions.assertTrue(v.subMap(m2));
		Assertions.assertTrue(v.subMap(new JDFAttributeMap()));
		Assertions.assertTrue(v.subMap((JDFAttributeMap) null));
		v.put("a3", "v4");
		final JDFAttributeMap m3 = new JDFAttributeMap(m1);
		Assertions.assertTrue(v.subMap(m3));
		m3.put("a3", "v5");
		Assertions.assertFalse(v.subMap(m3));
	}

	/**
	 * tests subMap()
	 */
	@Test
	public void testSubMapV()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		v2.add(m1);
		Assertions.assertTrue(v.subMap(v2));
		Assertions.assertTrue(v.subMap(new VJDFAttributeMap()));
		Assertions.assertTrue(v.subMap((VJDFAttributeMap) null));
	}

	/**
	 * tests OvelapsMap for individual maps
	 */
	@Test
	public void testOverlapsMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		Assertions.assertTrue(v.overlapsMap(m1));
		Assertions.assertFalse(v.overlapsMap(new JDFAttributeMap("a2", "v4")));
	}

	/**
	 * tests GetCommonMap for individual maps
	 */
	@Test
	public void testGetCommonMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		Assertions.assertNull(v.getCommonMap());
		v.add(m1);
		Assertions.assertEquals(v.getCommonMap(), m1);
		v.add(m2);
		Assertions.assertEquals(v.getCommonMap(), new JDFAttributeMap("a1", "v1"));
	}

	/**
	 * tests GetCommonMap for individual maps
	 */
	@Test
	public void testGetCommonMap2()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		final JDFAttributeMap cm = v.getCommonMap();
		Assertions.assertEquals(cm, m1);
		cm.put("aa", "bb");
		Assertions.assertNull(m1.get("aa"));
	}

	/**
	 * tests OvelapsMap for individual maps
	 */
	@Test
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
		Assertions.assertEquals(vAnd.size(), 1);
		Assertions.assertEquals(vAnd.get(0), new JDFAttributeMap("a1", "v1"));
	}

	/**
	 * tests OvelapsMap for individual maps
	 */
	@Test
	public void testGetOverLapMaps()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a3", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		VJDFAttributeMap vOverlap = v.getOverlapMaps(new JDFAttributeMap("a1", "v1"));
		Assertions.assertEquals(vOverlap.size(), 2);
		Assertions.assertEquals(vOverlap.get(0).size(), 2);
		vOverlap = v.getOverlapMaps(new JDFAttributeMap("a2", "v3"));
		Assertions.assertEquals(vOverlap.size(), 0);
	}

	/**
	 * tests OvelapsMap for individual maps
	 */
	@Test
	public void testGetPartValues()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a3", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		VString vals = v.getPartValues("a1", true);
		Assertions.assertEquals(vals.size(), 1);
		vals = v.getPartValues("a1", false);
		Assertions.assertEquals(vals.size(), 2);
	}

	/**
	 *
	 */
	@Test
	public void testgetMatchingMaps()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a3", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		VJDFAttributeMap vMatch = v.getMatchingMaps("a1", "v?", false);
		Assertions.assertEquals(vMatch.size(), 2);
		vMatch = v.getMatchingMaps("a3", "v?", false);
		Assertions.assertEquals(vMatch.size(), 1);
		Assertions.assertEquals(vMatch.get(0), m2);
		vMatch = v.getMatchingMaps("a3", "V?", true);
		Assertions.assertEquals(vMatch.size(), 1);
		Assertions.assertEquals(vMatch.get(0), m2);
		vMatch = v.getMatchingMaps("a4", "V?", true);
		Assertions.assertEquals(vMatch.size(), 0);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testHash()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a3", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		final int i = v.hashCode();
		v.add(m2);
		final int i2 = v.hashCode();
		Assertions.assertNotSame(i, i2);

	}

	/**
	 * tests OvelapsMap for individual maps
	 */
	@Test
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
		Assertions.assertEquals(vAnd.size(), 2);
		Assertions.assertEquals(vAnd, v);
	}

	/**
	 * tests OvelapsMap for individual maps
	 */
	@Test
	public void testGetOrMapsV()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a3", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		final VJDFAttributeMap v2 = new VJDFAttributeMap(v);

		final VJDFAttributeMap vOr = v.getOrMaps(v2);
		Assertions.assertEquals(v2, vOr);
		Assertions.assertEquals(v2, v.getOrMaps(new VJDFAttributeMap()));
	}

	/**
	 * tests OvelapsMap for individual maps
	 */
	@Test
	public void testGetOrMapsDouble()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);

		final JDFAttributeMap m3 = new JDFAttributeMap("b1", "vb1");
		m3.put("b2", "vb2");
		final JDFAttributeMap m4 = new JDFAttributeMap(m3);
		m4.put("b2", "vb3");
		final VJDFAttributeMap vb = new VJDFAttributeMap();
		vb.add(m3);
		vb.add(m4);

		final VJDFAttributeMap v2 = new VJDFAttributeMap(v);

		final VJDFAttributeMap vOr = v.getOrMaps(vb);
		Assertions.assertEquals(4, vOr.size());
		Assertions.assertEquals(v2, v.getOrMaps(new VJDFAttributeMap()));
	}

	/**
	 * tests OvelapsMap for vectors of maps
	 */
	@Test
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
		Assertions.assertTrue(v.overlapsMap(v2));
		v2.add(new JDFAttributeMap(m1));
		Assertions.assertTrue(v.overlapsMap(v2));
		v2.add(new JDFAttributeMap("a2", "v4"));
		Assertions.assertTrue(v.overlapsMap(v2));
		v.put("foo", "bar");
		Assertions.assertTrue(v.overlapsMap(v2));
		v2.put("foo", "notbar");
		Assertions.assertFalse(v.overlapsMap(v2));
	}

	/**
	 * tests OvelapsMap for vectors of maps
	 */
	@Test
	public void testOverlapMapVector()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		final VJDFAttributeMap clone = v.clone();
		clone.overlapMap(v2);
	}

	/**
	 * tests OvelapsMap for vectors of maps
	 */
	@Test
	public void testOverlapMapVector2()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		final VJDFAttributeMap clone = v.clone();
		v2.add(new JDFAttributeMap("b", "c"));
		clone.overlapMap(v2);
		Assertions.assertEquals(v, clone);
	}

	/**
	 * tests OvelapsMap for vectors of maps
	 */
	@Test
	public void testOverlapMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		m1.put("a3", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1.clone());
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		final VJDFAttributeMap clone = v.clone();
		clone.overlapMap(v2);
		Assertions.assertEquals(m1, clone.get(0));
		Assertions.assertEquals(v.size(), clone.size());
		v2.add(new JDFAttributeMap("a2", "v2"));
		clone.overlapMap(v2);
		Assertions.assertEquals(1, clone.size());
		Assertions.assertEquals(m1, clone.get(0));
	}

	/**
	 * tests addAll
	 */
	@Test
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
		Assertions.assertEquals(v.size(), 4);
		Assertions.assertTrue(v.contains(m1));
		Assertions.assertTrue(v.contains(m2));
		Assertions.assertTrue(v.contains(m3));

	}

	/**
	 *
	 */
	@Test
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
		Assertions.assertEquals(v.showKeys("-", " "), "[0](a2 = v2)-[1](a2 = v3) (a3 = v3)");
	}

	/**
	 * test Unify
	 */
	@Test
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
		log.info("" + (t1 - t0));
		v.unify();
		final long t2 = System.currentTimeMillis();
		log.info("unify " + (t2 - t1));
	}

	/**
	 * test Unify
	 */
	@Test
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
		Assertions.assertEquals(v.size(), 2);
		Assertions.assertTrue(v.contains(m1));
		Assertions.assertTrue(v.contains(m2));
		Assertions.assertTrue(v.contains(m3));
		v.add(m1);
		v.add(m2);
		v.add(m3);
		v.add(m1);
		v.add(m2);
		v.add(m3);
		v.unify();
		Assertions.assertEquals(v.size(), 2);
		Assertions.assertTrue(v.contains(m1));
		Assertions.assertTrue(v.contains(m2));
		Assertions.assertTrue(v.contains(m3));

		v.add(null);
		v.unify();
		Assertions.assertEquals(v.size(), 3);
		Assertions.assertTrue(v.contains(m1));
		Assertions.assertTrue(v.contains(m2));
		Assertions.assertTrue(v.contains(m3));
		Assertions.assertTrue(v.contains(null));

	}

	/**
	 * test Equals()
	 */
	@Test
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
		Assertions.assertEquals(v, v2, "mixed ordering");
		v2.add(m1);
		Assertions.assertFalse(v.equals(v2), "mixed ordering -other cardinality ");
	}

	/**
	 *
	 */
	@Test
	public void testExtendMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.extendMap("b", new VString("b1 b2 b3", null));

		Assertions.assertEquals(3, v.size());
	}

	/**
	*
	*/
	@Test
	public void testExtendMapNull()
	{
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.extendMap("b", new VString("b1 b2 b3", null));

		Assertions.assertEquals(3, v.size());
	}

	/**
	*
	*/
	@Test
	public void testExtendMapEmpty()
	{
		final VJDFAttributeMap v = new VJDFAttributeMap(new JDFAttributeMap());
		v.extendMap("b", new VString("b1 b2 b3", null));

		Assertions.assertEquals(3, v.size());
	}

	/**
	 * test Equals()
	 */
	@Test
	public void testIndexOf()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m1);
		v.add(m2);
		Assertions.assertEquals(v.indexOf(m1), 0);
		Assertions.assertEquals(v.indexOf(m2), 1);
		Assertions.assertEquals(v.indexOf(new JDFAttributeMap()), -1);
	}

	/**
	 * tests copy constructor
	 */
	@Test
	public void testCopy()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		v2.add(new JDFAttributeMap(m1));
		final VJDFAttributeMap v3 = new VJDFAttributeMap(v2);
		Assertions.assertEquals(v2, v3);
	}

	/**
	 * tests maxSize method
	 */
	@Test
	public void testMaxSize()
	{
		JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		Assertions.assertEquals(v2.maxSize(), 0);
		v2.add(m1);
		Assertions.assertEquals(v2.maxSize(), 1);
		m1 = m1.clone();
		m1.put("b", "c");
		v2.add(m1);
		Assertions.assertEquals(v2.maxSize(), 2);
		m1.put("bc", "c");
		Assertions.assertEquals(v2.maxSize(), 3);
	}

	/**
	 * tests isEmpty method
	 */
	@Test
	public void testIsEmpty()
	{
		final VJDFAttributeMap v = new VJDFAttributeMap();
		Assertions.assertTrue(VJDFAttributeMap.isEmpty(null));
		Assertions.assertTrue(VJDFAttributeMap.isEmpty(v));
		Assertions.assertTrue(VJDFAttributeMap.isEmpty(null));
		v.add(new JDFAttributeMap());
		Assertions.assertTrue(VJDFAttributeMap.isEmpty(v));
		v.add(new JDFAttributeMap());
		Assertions.assertFalse(VJDFAttributeMap.isEmpty(v));
		v.clear();
		v.add(null);
		Assertions.assertTrue(VJDFAttributeMap.isEmpty(v));

	}

	/**
	 * tests maxSize method
	 */
	@Test
	public void testGetNonEmpty()
	{
		final VJDFAttributeMap v = new VJDFAttributeMap();
		Assertions.assertNull(VJDFAttributeMap.getNonEmpty(v));
		Assertions.assertNull(VJDFAttributeMap.getNonEmpty(null));
		v.add(new JDFAttributeMap());
		Assertions.assertNull(VJDFAttributeMap.getNonEmpty(v));
		v.add(new JDFAttributeMap());
		Assertions.assertEquals(v, VJDFAttributeMap.getNonEmpty(v));
	}

	/**
	 * tests maxSize method
	 */
	@Test
	public void testGetVector()
	{
		Assertions.assertNull(VJDFAttributeMap.getVector(null));
		Assertions.assertNull(VJDFAttributeMap.getVector(new JDFAttributeMap()));
		Assertions.assertEquals(new VJDFAttributeMap(new JDFAttributeMap("a", "b")), VJDFAttributeMap.getVector(new JDFAttributeMap("a", "b")));
	}

	/**
	 *
	 */
	@Test
	public void testMinSize()
	{
		JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		Assertions.assertEquals(v2.minSize(), 0);
		v2.add(m1);
		m1.put("b", "c");
		Assertions.assertEquals(v2.minSize(), 2);
		m1 = m1.clone();
		v2.add(m1);
		Assertions.assertEquals(v2.minSize(), 2);
		m1.put("bc", "c");
		Assertions.assertEquals(v2.minSize(), 2);
	}

	/**
	 * tests put method
	 */
	@Test
	public void testPut()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		v2.add(m1);
		final VJDFAttributeMap v3 = new VJDFAttributeMap(v2);
		Assertions.assertEquals(v2, v3);
		v3.put("a2", "b");
		m1.put("a2", "b");
		Assertions.assertEquals(v2, v3);
		final VJDFAttributeMap v4 = new VJDFAttributeMap((VJDFAttributeMap) null);
		v4.put("a1", "b1");
		Assertions.assertEquals(v4.size(), 1);
		final VJDFAttributeMap v5 = new VJDFAttributeMap();
		v5.put(EnumPartIDKey.Side, EnumSide.Front);
		Assertions.assertEquals(v5.size(), 1);
	}

	/**
	 * tests put method
	 */
	@Test
	public void testPutMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final VJDFAttributeMap v2 = new VJDFAttributeMap();
		v2.add(m1);
		final VJDFAttributeMap v3 = new VJDFAttributeMap(v2);
		Assertions.assertEquals(v2, v3);
		v3.put(new JDFAttributeMap("a2", "b"));
		m1.put("a2", "b");
		Assertions.assertEquals(v2, v3);
		v3.put(new JDFAttributeMap("a3", "c"));
		m1.put("a3", "c");
		Assertions.assertEquals(v2, v3);

	}

	/**
	 * test reduceMap()
	 */
	@Test
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
		Assertions.assertEquals(v, v2);
	}

	/**
	 * test reduceMap()
	 */
	@Test
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
		Assertions.assertEquals(v2.size(), 2);
		v2.removeMaps(new JDFAttributeMap("a3", "v3"));
		Assertions.assertEquals(v2.size(), 1);
		Assertions.assertEquals(v2.get(0), m1);
		v2.add(m2);
		v2.removeMaps(new JDFAttributeMap("a1", "v1"));
		Assertions.assertEquals(v2.size(), 0);
	}

	/**
	 * test removeKeys()
	 */
	@Test
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
		Assertions.assertEquals(v, v2);
		Assertions.assertEquals(v.size(), 1);
	}

	/**
	 * test removeKeys()
	 */
	@Test
	public void testRemoveKey()
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
		v.removeKey("a2");
		Assertions.assertEquals(v, v2);
		Assertions.assertEquals(v.size(), 1);
		v.removeKey("a1");
		Assertions.assertTrue(v.isEmpty());
	}

}
