/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of
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
 * JDFRectangleRangeTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import org.junit.Test;

import junit.framework.TestCase;

public class JDFRectangleTest extends TestCase
{
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testRectangle() throws Exception
	{
		JDFRectangle r1 = new JDFRectangle("0 0 1 1");
		JDFRectangle r2 = new JDFRectangle("0 0 1 1");
		assertTrue(r1.equals(r2));
		assertTrue(r1.isGreaterOrEqual(r2));
		assertTrue(r1.isLessOrEqual(r2));
		assertFalse(r1.isGreater(r2));
		assertFalse(r1.isLess(r2));
		r2 = new JDFRectangle("0 0 1 2");
		assertTrue(r1.isLessOrEqual(r2));
		assertTrue(r1.isLess(r2));
		r2 = new JDFRectangle("0 0 1.000000001 1");
		assertTrue(r1.isLessOrEqual(r2));
		assertTrue(r1.equals(r2));

		r2 = new JDFRectangle("2 2 3 3");
		assertFalse(r1.equals(r2));
		assertFalse(r1.isGreaterOrEqual(r2));
		assertFalse(r1.isLessOrEqual(r2));
		assertFalse(r1.isGreater(r2));
		assertFalse(r1.isLess(r2));

	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testRectangleMm() throws Exception
	{
		JDFRectangle r1 = new JDFRectangle();
		r1.setLlxMm(0);
		r1.setLlyMm(0);
		r1.setUrxMm(1);
		r1.setUryMm(1);

		JDFRectangle r2 = new JDFRectangle();
		r2.setLlxMm(0);
		r2.setLlyMm(0);
		r2.setUrxMm(1);
		r2.setUryMm(1);

		assertTrue(r1.equals(r2));
		assertTrue(r1.isGreaterOrEqual(r2));
		assertTrue(r1.isLessOrEqual(r2));
		assertFalse(r1.isGreater(r2));
		assertFalse(r1.isLess(r2));

		r2 = new JDFRectangle();
		r2.setLlxMm(0);
		r2.setLlyMm(0);
		r2.setUrxMm(1);
		r2.setUryMm(2);
		assertTrue(r1.isLessOrEqual(r2));
		assertTrue(r1.isLess(r2));

		r2 = new JDFRectangle();
		r2.setLlxMm(0);
		r2.setLlyMm(0);
		r2.setUrxMm(1.000000001);
		r2.setUryMm(1);
		assertTrue(r1.isLessOrEqual(r2));
		assertTrue(r1.equals(r2));

		r2 = new JDFRectangle();
		r2.setLlxMm(2);
		r2.setLlyMm(2);
		r2.setUrxMm(3);
		r2.setUryMm(3);
		assertFalse(r1.equals(r2));
		assertFalse(r1.isGreaterOrEqual(r2));
		assertFalse(r1.isLessOrEqual(r2));
		assertFalse(r1.isGreater(r2));
		assertFalse(r1.isLess(r2));
	}

	/**
	 * 
	 */
	@Test
	public void testShift()
	{
		JDFRectangle r = JDFRectangle.createRectangle("0 0 1 1");
		r.shift(5, 10);
		JDFRectangle r2 = JDFRectangle.createRectangle("5 10 6 11");
		assertEquals(r, r2);
	}

	/**
	 * 
	 */
	@Test
	public void testGetLL()
	{
		JDFRectangle r = JDFRectangle.createRectangle("5 10 6 11");
		assertEquals(new JDFXYPair(5, 10), r.getLL());
	}

	/**
	 * 
	 */
	@Test
	public void testGetUR()
	{
		JDFRectangle r = JDFRectangle.createRectangle("5 10 6 11");
		assertEquals(new JDFXYPair(6, 11), r.getUR());
	}

	/**
	 * 
	 */
	@Test
	public void testFromXYPair()
	{
		JDFXYPair size = new JDFXYPair(2, 3);
		JDFRectangle r = new JDFRectangle(size);
		assertEquals(size, r.getSize());
		assertEquals(size, r.getUR());
		assertEquals(new JDFXYPair(), r.getLL());
	}

	/**
	 * 
	 */
	@Test
	public void testGetSize()
	{
		JDFRectangle r = JDFRectangle.createRectangle("5 10 6 12");
		assertEquals(new JDFXYPair(1, 2), r.getSize());
	}

	/**
	* 
	*/
	@Test
	public void testShiftXY()
	{
		JDFRectangle r = JDFRectangle.createRectangle("0 0 1 1");
		r.shift(new JDFXYPair(5, 10));
		JDFRectangle r2 = JDFRectangle.createRectangle("5 10 6 11");
		assertEquals(r, r2);
	}

	/**
	 * 
	 */
	@Test
	public void testCreate()
	{
		JDFRectangle r = JDFRectangle.createRectangle("1 2 3 4");
		JDFRectangle r2 = new JDFRectangle();
		r2.setLlx(1);
		r2.setLly(2);
		r2.setUrx(3);
		r2.setUry(4);
		assertEquals(r, r2);
	}
}
