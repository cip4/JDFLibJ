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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.junit.jupiter.api.Test;

/**
 * test of JDFAttributemap
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class JDFAttributeMapTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	public void testShowKeys()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		assertEquals(m1.showKeys(" "), "(a1 = v1)");
		m1.put("b1", "v2");
		assertEquals(m1.showKeys(" "), "(a1 = v1) (b1 = v2)");
		assertEquals(m1.showKeys("\n"), "(a1 = v1)\n(b1 = v2)");
		assertEquals(m1.showKeys(null), "(a1 = v1)(b1 = v2)");
	}

	/**
	 *
	 */
	@Test
	public void testClone()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		JDFAttributeMap m2 = new JDFAttributeMap(m1);
		assertEquals(m1, m2);
		m2 = m1.clone();
		assertEquals(m1, m2);
		m2.put("a2", "v3");
		assertNotSame(m1, m2);
		assertEquals(m1.get("a2"), "v2");
		assertEquals(m2.get("a2"), "v3");
	}

	/**
	*
	*/
	@Test
	public void testContainsKey()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		assertTrue(m1.containsKey("a1"));
		assertTrue(m1.containsKey("a2"));
		assertFalse(m1.containsKey("a3"));
		assertFalse(m1.containsKey(null));
	}

	/**
	*
	*/
	@Test
	public void testContainsAny()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		assertTrue(m1.containsAny(new VString("a1")));
		assertTrue(m1.containsAny(new VString("a1 b2")));
		assertFalse(m1.containsAny(new VString("c1")));
	}

	/**
	 *
	 */
	@Test
	public void testCloneNull()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap(null);
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap(m1);
		assertEquals(m1, m2);
		m2.put("a2", "v3");
		assertNotSame(m1, m2);
		assertEquals(m1.get("a2"), "v2");
		assertEquals(m2.get("a2"), "v3");
	}

	/**
	 *
	 */
	@Test
	public void testEquals()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap("a1", "v1");
		assertNotSame(m1, m2);
		m2.put("a2", "v2");
		assertEquals(m1, m2);
		m1.put("a2", (String) null);
		assertNotSame(m1, m2);
		m2.put("a2", (String) null);
		assertEquals(m1, m2);
		assertNotSame(m1, null);
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testPut()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap(EnumPartIDKey.SignatureName, "v1");
		assertEquals(m1.get("SignatureName"), "v1");
		m1.put(EnumPartIDKey.SheetName, "s1");
		assertEquals(m1.get("SheetName"), "s1");
		m1.put(EnumPartIDKey.Side, EnumSide.Front);
		assertEquals(m1.get("Side"), "Front");
		m1.put("Usage", EnumUsage.Input);
		assertEquals(m1.get("Usage"), "Input");
		m1.put("null", (String) null);
		assertEquals(m1.get("null"), null);
	}

	/**
	 *
	 */
	@Test
	public void testPutInt()
	{
		final JDFAttributeMap map = new JDFAttributeMap();
		map.put("a", 2);
		assertEquals(map.getInt("a", 4), 2);
	}

	/**
	 *
	 */
	@Test
	public void testPutDouble()
	{
		final JDFAttributeMap map = new JDFAttributeMap();
		map.put("a", 2.1);
		assertEquals(map.getDouble("a", 4), 2.1, 0.001);
	}

	/**
	 *
	 */
	@Test
	public void testPutBool()
	{
		final JDFAttributeMap map = new JDFAttributeMap();
		map.put("a", true);
		assertEquals(map.getBool("a", false), true);
	}

	/**
	 *
	 */
	@Test
	public void testPutNotNull()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap();
		assertNull(m1.putNotNull("null", null));
		m1.putNotNull(EnumPartIDKey.Side, "Front");
		assertEquals(m1.get("Side"), "Front");
	}

	/**
	 *
	 */
	@Test
	public void testGet()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap(EnumPartIDKey.SignatureName, "v1");
		assertEquals(m1.get("SignatureName"), "v1");
		m1.put(EnumPartIDKey.SheetName, "s1");
		assertEquals(m1.get(EnumPartIDKey.SignatureName), "v1");
	}

	/**
	 *
	 */
	@Test
	public void testGetIgnoreCase()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap(EnumPartIDKey.SignatureName, "v1");
		assertEquals("v1", m1.getIgnoreCase("SignatureName".toLowerCase()));
		assertEquals("v1", m1.getIgnoreCase("SignatureName"));
		assertNull(m1.getIgnoreCase("aaa"));
	}

	/**
	 *
	 */
	@Test
	public void testGetNonEmpty()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap(EnumPartIDKey.SignatureName, "");
		assertNull(m1.getNonEmpty("SignatureName"));
		assertEquals(m1.get(EnumPartIDKey.SignatureName), "");
	}

	/**
	 *
	 */
	@Test
	public void testSubMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		assertTrue(m1.subMap((JDFAttributeMap) null));
		final JDFAttributeMap m2 = new JDFAttributeMap("a1", "v1");
		final JDFAttributeMap m3 = new JDFAttributeMap("a1", "v3");
		JDFAttributeMap mStar = new JDFAttributeMap("a1", (String) null);
		assertTrue(m1.subMap(m2));
		assertTrue(m1.subMap(mStar));
		assertTrue(mStar.subMap(m3));
		m2.put("a2", "v2");
		mStar = new JDFAttributeMap("a1", "*");
		assertTrue(m1.subMap(m2));
		assertTrue(m1.subMap(mStar));
		assertTrue(mStar.subMap(m3));
		m2.put("a2", "v3");
		assertFalse(m1.subMap(m2));
		m2.put("a2", "v2");
		assertTrue(m1.subMap(m2));
		m2.put("a3", "v3");
		assertFalse(m1.subMap(m2));
		assertTrue(m1.subMap((JDFAttributeMap) null));
	}

	/**
	 *
	 */
	@Test
	public void testGetAndMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap("a1", "v1");
		assertEquals(m1.getAndMap(m2), m2);
		assertEquals(m2.getAndMap(m1), m2);
		final JDFAttributeMap m3 = new JDFAttributeMap(m2);
		m3.put("a3", "v2");
		assertEquals(m1.getAndMap(m3), m2);
		assertEquals(m3.getAndMap(m1), m2);
		m3.put("a2", "vbad");
		assertNull(m1.getAndMap(m3));
		assertNull(m3.getAndMap(m1));

	}

	/**
	 *
	 */
	@Test
	public void testAndMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap("a1", "v1");
		m1.andMap(m2);
		assertEquals(m1, m2);
		final JDFAttributeMap m3 = new JDFAttributeMap(m2);
		m3.put("a3", "v2");
		m1.andMap(m3);
		assertEquals(m1, m2);

	}

	/**
	 *
	 */
	@Test
	public void testGetOrMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap("a1", "v1");
		assertEquals(m1.getOrMap(m2), m1);
		assertEquals(m2.getOrMap(m1), m1);
		final JDFAttributeMap m3 = new JDFAttributeMap(m2);
		m3.put("a3", "v2");

		final JDFAttributeMap m4 = new JDFAttributeMap(m3);
		m4.putAll(m1);

		assertEquals(m1.getOrMap(m3), m4);
		assertEquals(m3.getOrMap(m1), m4);
		m3.put("a2", "vbad");
		assertNull(m1.getOrMap(m3));
		assertNull(m3.getOrMap(m1));

	}

	/**
	 *
	 */
	@Test
	public void testGetOrMapNull()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap("a1", (String) null);
		assertEquals(m1.getOrMap(m2), m1);
		final JDFAttributeMap m3 = new JDFAttributeMap(m2);
		m3.put("a3", "v2");

		final JDFAttributeMap m4 = new JDFAttributeMap(m3);
		m4.putAll(m1);

		assertEquals(m1.getOrMap(m3), m4);
		m3.put("a2", "vbad");
		assertNull(m1.getOrMap(m3));
		assertNull(m3.getOrMap(m1));

	}

	/**
	 *
	 */
	@Test
	public void testGetOrMapSplit()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		final JDFAttributeMap m2 = new JDFAttributeMap("a2", "v2");
		assertEquals(2, m1.getOrMap(m2).size());
		final JDFAttributeMap m3 = new JDFAttributeMap(m2);
		m3.put("a1", "v1");

		assertEquals(m1.getOrMap(m2), m3);
		assertEquals(m2.getOrMap(m1), m3);
		m2.put("a1", "vbad");
		assertNull(m1.getOrMap(m2));
		assertNull(m2.getOrMap(m1));
	}

	/**
	 *
	 */
	@Test
	public void testOverlapMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		assertTrue(m1.overlapMap((JDFAttributeMap) null));
		final JDFAttributeMap m2 = new JDFAttributeMap("a1", "v1");
		assertTrue(m1.overlapMap(m2));
		m2.put("a2", "v2");
		assertTrue(m1.overlapMap(m2));
		m2.put("a2", "v3");
		assertFalse(m1.overlapMap(m2));
		m2.put("a2", "v2");
		assertTrue(m1.overlapMap(m2));
		m2.put("a2", "*");
		assertTrue(m1.overlapMap(m2));
		m2.put("a3", "v3");
		assertTrue(m1.overlapMap(m2));
		m2.put("a4", (String) null);
		assertTrue(m1.overlapMap(m2));
		m2.put("a4", (String) null);
		assertTrue(m1.overlapMap(m2));
		m1.put("a4", (String) null);
		assertTrue(m1.overlapMap(m2));
		assertTrue(m1.overlapMap((JDFAttributeMap) null));
	}

	/**
	 * tests isEmpty method
	 */
	@Test
	public void testIsEmpty()
	{
		assertTrue(JDFAttributeMap.isEmpty(null));
		assertTrue(JDFAttributeMap.isEmpty(new JDFAttributeMap()));
		assertFalse(JDFAttributeMap.isEmpty(new JDFAttributeMap("a", "b")));

	}

	/**
	 *
	 */
	@Test
	public void testMatches()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		assertTrue(m1.matches("a1", "v?", false));
		assertTrue(m1.matches("a1", "v1", false));
		assertTrue(m1.matches("a1", "V*", true));
		assertFalse(m1.matches("a1", "v", false));
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Test
	public void testReduceMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap("a1", "v1");
		final VString keys = new VString();
		keys.add("a1");
		m1.reduceMap(keys);
		assertEquals(m1, m2);
	}

	// /////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testReduceMapSet()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap("a1", "v1");
		final HashSet<String> keys = new HashSet<>();
		keys.add("a1");
		m1.reduceMap(keys);
		assertEquals(m1, m2);
	}

	// /////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testRemoveKeys()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		final JDFAttributeMap m2 = new JDFAttributeMap("a1", "v1");
		final HashSet<String> keys = new HashSet<>();
		keys.add("a2");
		m1.removeKeys(keys);
		assertEquals(m1, m2);
		m1.put("a2", (String) null);
		assertNotSame(m1, m2);
		m1.removeKeys(keys);
		assertEquals(m1, m2);
	}

	/**
	 *
	 */
	@Test
	public void testRenameKey()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("a1", "v1");
		m1.put("a2", "v2");
		m1.put("b", "");
		assertEquals(m1.renameKey("a1", "a3"), null);
		assertEquals(m1.get("a3"), "v1");
		assertEquals(m1.renameKey("a4", "a5"), null);
		assertEquals(m1.get("a5"), null);
		assertEquals(m1.renameKey("a4", "a2"), null);
		assertEquals(m1.get("a2"), "v2");
		assertEquals(m1.renameKey("a3", "a2"), "v2");
		assertEquals(m1.get("a2"), "v1");
		assertEquals(m1.get("a3"), null);
		assertNull(m1.renameKey("b", "c"));
		assertNull(m1.get("b"));
		assertNull(m1.get("c"));
	}

}
