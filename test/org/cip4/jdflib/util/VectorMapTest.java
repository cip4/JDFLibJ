/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of
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

import java.util.Map;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen tests for the VectorMap class
 */
public class VectorMapTest extends JDFTestCaseBase
{
	private VectorMap<String, String> m;

	/**
	 * 
	 */
	public void testSize()
	{
		assertEquals(m.size("a"), 2);
	}

	/**
	 * test for getOne
	 */
	public void testGetOne()
	{
		assertEquals(m.getOne("a", 0), "b");
	}

	/**
	 * test for getOne
	 */
	public void testGetAllValues()
	{
		final Vector<String> allValues = m.getAllValues();
		assertEquals(allValues.size(), 3);
		assertTrue(allValues.contains("b"));
		assertTrue(allValues.contains("c"));
	}

	/**
	 * test for getIndex
	 */
	public void testGetIndex()
	{
		assertEquals(m.getIndex("a", "b"), 0);
		assertEquals(m.getIndex("a", "c"), 1);
		assertEquals(m.getIndex("a", "d"), -1);
		assertEquals(m.getIndex("c", "c"), -2);
	}

	/**
	* test for getInverted
	*/
	public void testGetInverted()
	{
		m.putOne("e", "f");
		Map<String, String> inv = m.getInvertedMap();
		assertNotNull(inv);
		assertEquals(inv.get("b"), "a");
		assertEquals(inv.get("f"), "e");
	}

	/**
	 * 
	 */
	public void testPutOne()
	{
		assertEquals(m.size("a"), 2);
		m.putOne("a", "b");
		assertEquals(m.getOne("a", 0), "b");
	}

	/**
	 * 
	 */
	public void testAppendUniqueKey()
	{
		assertEquals(m.size("a"), 2);
		Vector<String> v2 = new Vector<String>();
		v2.add("v1");
		v2.add("v2");
		m.appendUnique("a", v2);
		assertEquals(m.size("a"), 4);
		m.appendUnique("a", v2);
		assertEquals(m.size("a"), 4);
	}

	/**
	 * 
	 */
	public void testAppendUnique()
	{
		assertEquals(m.size("a"), 2);
		@SuppressWarnings("unchecked")
		VectorMap<String, String> clone = (VectorMap<String, String>) m.clone();
		m.appendUnique(clone);
		assertEquals(m, clone);
		clone.putOne("d", "d1");
		m.appendUnique(clone);
		assertEquals(m, clone);
	}

	/**
	 * 
	 */
	public void testremoveOne()
	{
		m.removeOne("a", "b");
		assertEquals(m.getOne("a", 0), "c");
		assertEquals(-1, m.getIndex("a", "b"));
		assertEquals(m.size("a"), 1);
		m.removeOne("a", "c");
		assertEquals(-2, m.getIndex("a", "b"));
		assertNull(m.get("a"));
		m.removeOne("a", "c");
		assertEquals(-2, m.getIndex("a", "b"));
		assertNull(m.get("a"));
	}

	/**
	 * 
	 */
	public void testSetOne()
	{
		m.setOne("a", "b1", "b");
		assertEquals(m.getOne("a", 0), "b1");
		assertEquals(m.size("a"), 2);
		m.setOne("a", "b2", "b4");
		assertEquals(m.getOne("a", 2), "b2");
		assertEquals(m.size("a"), 3);
		m.setOne("aaa", "bb", "b4");
		assertEquals(m.getOne("aaa", 0), "bb");
		assertEquals(m.size("aaa"), 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		m = new VectorMap<String, String>();
		m.putOne("a", "b");
		m.putOne("a", "c");
		m.putOne("a2", "c");
	}
}
