/*
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
/*
 * @author muchadie
 */
package org.cip4.jdflib.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen tests for the VectorMap class
 */
class VectorMapTest extends JDFTestCaseBase
{
	private VectorMap<String, String> m;

	/**
	 *
	 */
	@Test
	void testSize()
	{
		Assertions.assertEquals(m.size("a"), 2);
	}

	/**
	 * test for getOne
	 */
	@Test
	void testGetOne()
	{
		Assertions.assertEquals(m.getOne("a", 0), "b");
	}

	/**
	 * test for getOne
	 */
	@Test
	void testGetAllValues()
	{
		final Vector<String> allValues = m.getAllValues();
		Assertions.assertEquals(allValues.size(), 3);
		Assertions.assertTrue(allValues.contains("b"));
		Assertions.assertTrue(allValues.contains("c"));
		Assertions.assertEquals(m.getAllValues().size(), 3, "we didn't modify m by calling getAllValues");
		Assertions.assertEquals(m.getAllValues().size(), 3);
	}

	/**
	 * test for getIndex
	 */
	@Test
	void testGetIndex()
	{
		Assertions.assertEquals(m.getIndex("a", "b"), 0);
		Assertions.assertEquals(m.getIndex("a", "c"), 1);
		Assertions.assertEquals(m.getIndex("a", "d"), -1);
		Assertions.assertEquals(m.getIndex("c", "c"), -2);
	}

	/**
	 *
	 */
	@Test
	void testFillInvertedMap()
	{
		VectorMap<String, Integer> vm = new VectorMap<String, Integer>();
		Map<Integer, String> mis = new HashMap<Integer, String>();
		for (int i = 0; i < 100; i++)
		{
			mis.put(Integer.valueOf(i), "" + (i % 10));
		}
		vm.fillInvertedMap(mis);
		Assertions.assertEquals(vm.size(), 10);
		for (int i = 0; i < 10; i++)
		{
			Vector<Integer> vector = vm.get("" + i);
			Assertions.assertEquals(vector.size(), 10);
			for (int j = 0; j < 10; j++)
			{
				Assertions.assertEquals(i + j * 10, vector.get(j).intValue());
			}
		}
	}

	/**
	* test for getInverted
	*/
	@Test
	void testGetInverted()
	{
		m.putOne("e", "f");
		Map<String, String> inv = m.getInvertedMap();
		Assertions.assertNotNull(inv);
		Assertions.assertEquals(inv.get("b"), "a");
		Assertions.assertEquals(inv.get("f"), "e");
	}

	/**
	 *
	 */
	@Test
	void testPutOne()
	{
		Assertions.assertEquals(m.size("a"), 2);
		m.putOne("a", "b");
		Assertions.assertEquals(m.getOne("a", 0), "b");
		m.setUnique(false);
		for (int i = 0; i < 7; i++)
			m.putOne("cc", "d");
		for (int i = 0; i < 7; i++)
			Assertions.assertEquals(m.getOne("cc", i), "d");
	}

	/**
	 *
	 */
	@Test
	void testAppendUniqueKey()
	{
		Assertions.assertEquals(m.size("a"), 2);
		Vector<String> v2 = new Vector<String>();
		v2.add("v1");
		v2.add("v2");
		m.appendUnique("a", v2);
		Assertions.assertEquals(m.size("a"), 4);
		m.appendUnique("a", v2);
		Assertions.assertEquals(m.size("a"), 4);
	}

	/**
	 *
	 */
	@Test
	void testAppendUnique()
	{
		Assertions.assertEquals(m.size("a"), 2);
		@SuppressWarnings("unchecked")
		VectorMap<String, String> clone = (VectorMap<String, String>) m.clone();
		m.appendUnique(clone);
		Assertions.assertEquals(m, clone);
		clone.putOne("d", "d1");
		m.appendUnique(clone);
		Assertions.assertEquals(m, clone);
	}

	/**
	 *
	 */
	@Test
	void testremoveOne()
	{
		m.removeOne("a", "b");
		Assertions.assertEquals(m.getOne("a", 0), "c");
		Assertions.assertEquals(-1, m.getIndex("a", "b"));
		Assertions.assertEquals(m.size("a"), 1);
		m.removeOne("a", "c");
		Assertions.assertEquals(-2, m.getIndex("a", "b"));
		Assertions.assertNull(m.get("a"));
		m.removeOne("a", "c");
		Assertions.assertEquals(-2, m.getIndex("a", "b"));
		Assertions.assertNull(m.get("a"));
	}

	/**
	 *
	 */
	@Test
	void testSetOne()
	{
		m.setOne("a", "b1", "b");
		Assertions.assertEquals(m.getOne("a", 0), "b1");
		Assertions.assertEquals(m.size("a"), 2);
		m.setOne("a", "b2", "b4");
		Assertions.assertEquals(m.getOne("a", 2), "b2");
		Assertions.assertEquals(m.size("a"), 3);
		m.setOne("aaa", "bb", "b4");
		Assertions.assertEquals(m.getOne("aaa", 0), "bb");
		Assertions.assertEquals(m.size("aaa"), 1);
	}

	/**
	 *
	 */
	@Test
	void testSetOneInt()
	{
		m = new VectorMap<String, String>();
		m.setOne("a", "b1", 3);
		Assertions.assertEquals(m.getOne("a", 3), "b1");
		Assertions.assertNull(m.getOne("a", 2));
		Assertions.assertEquals(m.size("a"), 4);
		m.setOne("a", "b2", -2);
		Assertions.assertEquals(m.size("a"), 4);
		Assertions.assertNull(m.getOne("a", 1));
		Assertions.assertEquals(m.getOne("a", 3), "b1");
		Assertions.assertEquals(m.getOne("a", 2), "b2");
		Assertions.assertEquals(m.getOne("a", -2), "b2");
		try
		{
			m.setOne("a", "v", -20);
			Assertions.fail("bad negative");
		}
		catch (IllegalArgumentException x)
		{
			// nop
		}
	}

	/**
	 *
	 *
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		m = new VectorMap<String, String>();
		m.putOne("a", "b");
		m.putOne("a", "c");
		m.putOne("a2", "c");
	}
}
