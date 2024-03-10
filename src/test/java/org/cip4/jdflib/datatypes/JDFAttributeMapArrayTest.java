/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
public class JDFAttributeMapArrayTest extends JDFTestCaseBase
{
	@Test
	void testGetArray()
	{
		Assertions.assertNull(JDFAttributeMapArray.getArray(null));
		Assertions.assertNull(JDFAttributeMapArray.getArray(new JDFAttributeMap()));
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		Assertions.assertEquals(m1, JDFAttributeMapArray.getArray(m1).get(0));
	}

	/**
	 *
	 */
	@Test
	void testExtendMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.extendMap("b", new VString("b1 b2 b3", null));

		assertEquals(3, v.size());
		v.extendMap("c", new VString("c1 c2 c3", null));
		assertEquals(9, v.size());
	}

	/**
	*
	*/
	@Test
	void testExtendMapNull()
	{
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.extendMap("b", null);

		assertEquals(0, v.size());
	}

	/**
	*
	*/
	@Test
	void testExtendMapEmpty()
	{
		final JDFAttributeMapArray v = new JDFAttributeMapArray(new JDFAttributeMap());
		v.extendMap("b", new VString("", null));

		assertEquals(1, v.size());
	}

	/**
	 * tests clone
	 */
	@Test
	void testClone()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray((JDFAttributeMapArray) null);
		v.add(m1);
		v.add(m2);
		JDFAttributeMapArray v2 = new JDFAttributeMapArray(v);
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
	void testSubMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
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
	void testSubMapV()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
		v2.add(m1);
		Assertions.assertTrue(v.subMap(v2));
		Assertions.assertTrue(v.subMap(new JDFAttributeMapArray()));
		Assertions.assertTrue(v.subMap((JDFAttributeMapArray) null));
	}

	/**
	 * tests OvelapsMap for individual maps
	 */
	@Test
	void testOverlapsMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);
		Assertions.assertTrue(v.overlapsMap(m1));
		Assertions.assertFalse(v.overlapsMap(new JDFAttributeMap("a2", "v4")));
	}

	/**
	 * tests GetCommonMap for individual maps
	 */
	@Test
	void testGetCommonMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
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
	void testGetCommonMap2()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
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
	void testGetAndMaps()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a3", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);
		final JDFAttributeMapArray vAnd = v.getAndMaps(new JDFAttributeMap("a1", "v1"));
		Assertions.assertEquals(vAnd.size(), 1);
		Assertions.assertEquals(vAnd.get(0), new JDFAttributeMap("a1", "v1"));
	}

	/**
	 * tests OvelapsMap for individual maps
	 */
	@Test
	void testGetOverLapMaps()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a3", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);
		JDFAttributeMapArray vOverlap = v.getOverlapMaps(new JDFAttributeMap("a1", "v1"));
		Assertions.assertEquals(vOverlap.size(), 2);
		Assertions.assertEquals(vOverlap.get(0).size(), 2);
		vOverlap = v.getOverlapMaps(new JDFAttributeMap("a2", "v3"));
		Assertions.assertEquals(vOverlap.size(), 0);
	}

	/**
	 * tests OvelapsMap for individual maps
	 */
	@Test
	void testGetPartValues()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a3", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);
		List<String> vals = v.getPartValues("a1", true);
		Assertions.assertEquals(vals.size(), 1);
		vals = v.getPartValues("a1", false);
		Assertions.assertEquals(vals.size(), 2);
	}

	/**
	 *
	 */
	@Test
	void testgetMatchingMaps()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a3", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);
		JDFAttributeMapArray vMatch = v.getMatchingMaps("a1", "v?", false);
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
	void testHash()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a3", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
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
	void testGetOrMaps()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a3", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);
		final JDFAttributeMapArray vAnd = v.getOrMaps(new JDFAttributeMap("a1", "v1"));
		Assertions.assertEquals(vAnd.size(), 2);
		Assertions.assertEquals(vAnd, v);
	}

	/**
	 * tests OvelapsMap for individual maps
	 */
	@Test
	void testGetOrMapsV()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a3", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray(v);

		final JDFAttributeMapArray vOr = v.getOrMaps(v2);
		Assertions.assertEquals(v2, vOr);
		Assertions.assertEquals(v2, v.getOrMaps(new JDFAttributeMapArray()));
	}

	/**
	 * tests OvelapsMap for individual maps
	 */
	@Test
	void testGetOrMapsDouble()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);

		final JDFAttributeMap m3 = new JDFAttributeMap("b1", "vb1");
		m3.put("b2", "vb2");
		final JDFAttributeMap m4 = new JDFAttributeMap(m3);
		m4.put("b2", "vb3");
		final JDFAttributeMapArray vb = new JDFAttributeMapArray();
		vb.add(m3);
		vb.add(m4);

		final JDFAttributeMapArray v2 = new JDFAttributeMapArray(v);

		final JDFAttributeMapArray vOr = v.getOrMaps(vb);
		Assertions.assertEquals(4, vOr.size());
		Assertions.assertEquals(v2, v.getOrMaps(new JDFAttributeMapArray()));
	}

	/**
	 * tests OvelapsMap for vectors of maps
	 */
	@Test
	void testOverlapsMapVector()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
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
	void testOverlapMapVector()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
		final JDFAttributeMapArray clone = v.clone();
		clone.overlapMap(v2);
	}

	/**
	 * tests OvelapsMap for vectors of maps
	 */
	@Test
	void testOverlapMapVector2()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
		final JDFAttributeMapArray clone = v.clone();
		v2.add(new JDFAttributeMap("b", "c"));
		clone.overlapMap(v2);
		Assertions.assertEquals(v, clone);
	}

	/**
	 * tests OvelapsMap for vectors of maps
	 */
	@Test
	void testOverlapMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		m1.put("a3", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1.clone());
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
		final JDFAttributeMapArray clone = v.clone();
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
	void testAddAll()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMap m3 = new JDFAttributeMap(m1);
		m3.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
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
	void testShowKeys()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap();
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		m2.put("a3", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);
		Assertions.assertEquals(v.showKeys("-", " "), "[0](a2 = v2)-[1](a2 = v3) (a3 = v3)");
	}

	/**
	 * test Unify
	 */
	@Test
	void testUnifyBig()
	{
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		final long t0 = System.currentTimeMillis();
		v.ensureCapacity(10000);
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
	void testUnify()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMap m3 = new JDFAttributeMap(m1);
		m3.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
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
	void testEquals()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
		v2.add(m2);
		v2.add(m1);
		Assertions.assertEquals(v, v2, "mixed ordering");
		v2.add(m1);
		Assertions.assertFalse(v.equals(v2), "mixed ordering -other cardinality ");
	}

	/**
	 * test Equals()
	 */
	@Test
	void testIndexOf()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
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
	void testCopy()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
		v2.add(new JDFAttributeMap(m1));
		final JDFAttributeMapArray v3 = new JDFAttributeMapArray(v2);
		Assertions.assertEquals(v2, v3);
	}

	/**
	 * tests maxSize method
	 */
	@Test
	void testMaxSize()
	{
		JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
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
	void testIsEmpty()
	{
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		Assertions.assertTrue(JDFAttributeMapArray.isEmpty(null));
		Assertions.assertTrue(JDFAttributeMapArray.isEmpty(v));
		Assertions.assertTrue(JDFAttributeMapArray.isEmpty(null));
		v.add(new JDFAttributeMap());
		Assertions.assertTrue(JDFAttributeMapArray.isEmpty(v));
		v.add(new JDFAttributeMap());
		Assertions.assertFalse(JDFAttributeMapArray.isEmpty(v));
		v.clear();
		v.add(null);
		Assertions.assertTrue(JDFAttributeMapArray.isEmpty(v));

	}

	/**
	 * tests maxSize method
	 */
	@Test
	void testGetNonEmpty()
	{
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		Assertions.assertNull(JDFAttributeMapArray.getNonEmpty(v));
		Assertions.assertNull(JDFAttributeMapArray.getNonEmpty(null));
		v.add(new JDFAttributeMap());
		Assertions.assertNull(JDFAttributeMapArray.getNonEmpty(v));
		v.add(new JDFAttributeMap());
		Assertions.assertEquals(v, JDFAttributeMapArray.getNonEmpty(v));
	}

	/**
	 *
	 */
	@Test
	void testMinSize()
	{
		JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
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
	void testPut()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
		v2.add(m1);
		final JDFAttributeMapArray v3 = new JDFAttributeMapArray(v2);
		Assertions.assertEquals(v2, v3);
		v3.put("a2", "b");
		m1.put("a2", "b");
		Assertions.assertEquals(v2, v3);
		final JDFAttributeMapArray v4 = new JDFAttributeMapArray((JDFAttributeMapArray) null);
		v4.put("a1", "b1");
		Assertions.assertEquals(v4.size(), 1);
		final JDFAttributeMapArray v5 = new JDFAttributeMapArray();
		v5.put(EnumPartIDKey.Side, EnumSide.Front);
		Assertions.assertEquals(v5.size(), 1);
	}

	/**
	 * tests put method
	 */
	@Test
	void testPutMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
		v2.add(m1);
		final JDFAttributeMapArray v3 = new JDFAttributeMapArray(v2);
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
	void testReduceMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
		v2.add(new JDFAttributeMap(m1));
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
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
	void testRemoveMaps()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
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
	void testRemoveKeys()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
		v2.add(new JDFAttributeMap(m1));
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
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
	void testRemoveKey()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final JDFAttributeMapArray v2 = new JDFAttributeMapArray();
		v2.add(new JDFAttributeMap(m1));
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		m2.put("a2", "v3");
		final JDFAttributeMapArray v = new JDFAttributeMapArray();
		v.add(m1);
		v.add(m2);
		v.removeKey("a2");
		Assertions.assertEquals(v, v2);
		Assertions.assertEquals(v.size(), 1);
		v.removeKey("a1");
		Assertions.assertTrue(v.isEmpty());
	}

}
