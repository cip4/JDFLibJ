/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
 * JDFIntegerRangeListTest.java
 *
 * @author Elena Skobchenko
 *
 * Copyright (c) 2001-2004 The International Cooperation for the Integration
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.jupiter.api.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         May 14, 2009
 */
class JDFIntegerRangeTest extends JDFTestCaseBase
{

	/**
	 * @throws Exception
	 */
	@Test
	public final void testJDFIntegerRangeString() throws Exception
	{

		JDFIntegerRange range = new JDFIntegerRange();
		range = new JDFIntegerRange(" 0 ~ 1 ");

		// rangeList is not empty
		assertFalse(range.toString().length() == 0, "Bad Constructor from a given String");
		// must be trasformed into the string "0~1"
		assertEquals(range.toString(), "0 ~ 1", "Bad Constructor from a given String");
		range = new JDFIntegerRange(" 1 ~ 1 ");
		assertEquals(range.toString(), "1", "Bad Constructor from a given String");

	}

	/**
	 * @throws Exception
	 */
	@Test
	public final void testAppend() throws Exception
	{
		final JDFIntegerRange range = new JDFIntegerRange(" 0 ~ 1 ");

		// rangeList is not empty
		assertFalse(range.toString().length() == 0, "Bad Constructor from a given String");
		// must be trasformed into the string "0~1"
		assertEquals(range.toString(), "0 ~ 1", "Bad Constructor from a given String");
		assertFalse(range.append(4));
		assertEquals(range.toString(), "0 ~ 1", "Bad Constructor from a given String");
		assertFalse(range.append(-5));
		assertEquals(range.toString(), "0 ~ 1", "Bad Constructor from a given String");
		assertTrue(range.append(2));
		assertEquals(range.toString(), "0 ~ 2", "Bad Constructor from a given String");
		assertFalse(range.append(2));
		assertEquals(range.toString(), "0 ~ 2", "Bad Constructor from a given String");
		assertFalse(range.append(1));
		assertEquals(range.toString(), "0 ~ 2", "Bad Constructor from a given String");
	}

	/**
		 *
		 */
	@Test
	public final void testCopyConstructor()
	{
		final JDFIntegerRange range = new JDFIntegerRange(4, -1, 8);
		assertTrue(range.inRange(4));
		assertFalse(range.inRange(3));
		assertTrue(range.inRange(7));
		assertFalse(range.inRange(8));
		final JDFIntegerRange range2 = new JDFIntegerRange(range);
		assertTrue(range2.inRange(4));
		assertFalse(range2.inRange(3));
		assertTrue(range2.inRange(7));
		assertFalse(range2.inRange(8));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public final void testDefaultDef() throws Exception
	{
		JDFIntegerRange range = new JDFIntegerRange("0~-1");
		assertFalse(range.inRange(4));
		JDFIntegerRange.setDefaultDef(Integer.MAX_VALUE);
		range = new JDFIntegerRange("0~-1");
		assertTrue(range.inRange(4));
		JDFIntegerRange.setDefaultDef(0);
		range = new JDFIntegerRange("0~-1");
		assertFalse(range.inRange(4));
		assertFalse(range.inRange(-4));
		assertTrue(range.inRange(0));
	}

	/**
	 *
	 */
	@Test
	void testScale()
	{
		final JDFIntegerRange range = new JDFIntegerRange(4, 5, 0);
		range.scale(2);
		assertEquals(range.getLeft(), 8);
		assertEquals(range.getRight(), 10);
	}

	/**
	 *
	 */
	@Test
	void testGetIntegerRange()
	{
		assertEquals(JDFIntegerRange.getIntegerRange("8").getLeft(), 8);
		assertEquals(JDFIntegerRange.getIntegerRange("8~22").getLeft(), 8);
		assertEquals(JDFIntegerRange.getIntegerRange("8~22").getRight(), 22);
		assertNull(JDFIntegerRange.getIntegerRange("8~22a"));
		assertNull(JDFIntegerRange.getIntegerRange(""));
		assertNull(JDFIntegerRange.getIntegerRange(null));
	}

	/**
	 *
	 */
	@Test
	void testGetString()
	{
		JDFIntegerRange integerRange = JDFIntegerRange.getIntegerRange("8~22");
		assertEquals("8 ~ 22", integerRange.getString(0));
		integerRange = JDFIntegerRange.getIntegerRange("8~77");
		assertEquals("8 ~ 77", integerRange.getString(2));
		assertEquals("8 77", integerRange.getXJDFString(2));
	}

	/**
	 *
	 */
	@Test
	void testGetCount()
	{
		JDFIntegerRange integerRange = JDFIntegerRange.getIntegerRange("8~22");
		assertEquals(15, integerRange.getElementCount());
		integerRange = JDFIntegerRange.getIntegerRange("-1 ~ -1");
		assertEquals(1, integerRange.getElementCount());
		integerRange = JDFIntegerRange.getIntegerRange("-1 ~ -4");
		assertEquals(4, integerRange.getElementCount());
		integerRange = JDFIntegerRange.getIntegerRange("8~-1");
		assertEquals(-1, integerRange.getElementCount());
		integerRange.setDef(10);
		assertEquals(2, integerRange.getElementCount());
	}

	/**
	 * @throws Exception
	 *
	 *
	 */
	@Test
	void testgetElementCountBad() throws Exception
	{
		JDFIntegerRange integerRange = JDFIntegerRange.getIntegerRange("1 ~ 4");
		integerRange.setDef(2);
		assertEquals(4, integerRange.getElementCount());

		integerRange = JDFIntegerRange.getIntegerRange("-4");
		integerRange.setDef(2);
		assertEquals(-1, integerRange.getElementCount());
	}

	/**
	 *
	 */
	@Test
	void testGetIntegerList()
	{
		JDFIntegerRange range = new JDFIntegerRange(0, -1, 0);
		JDFIntegerList il = range.getIntegerList();
		assertEquals(0, il.size());

		range = new JDFIntegerRange(0);
		il = range.getIntegerList();
		assertEquals(il.size(), 1);
		range = new JDFIntegerRange(0, -1, -1);
		il = range.getIntegerList();
		assertEquals(il.size(), 0);
		range.setDef(2000);
		il = range.getIntegerList();
		assertEquals(il.size(), 2000);
		range.setDef(20000);
		il = range.getIntegerList();
		assertEquals(il.size(), 20000);
	}

	/**
	 *
	 */
	@Test
	void testGetIntegerListNeg()
	{
		final JDFIntegerRange range = new JDFIntegerRange(-4, -1, 0);
		final JDFIntegerList il = range.getIntegerList();
		assertEquals(0, il.size());

	}
}
