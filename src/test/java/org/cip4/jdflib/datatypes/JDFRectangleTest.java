/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
/**
 * JDFRectangleRangeTest.java
 *
 * @author Elena Skobchenko
 *
 *         Copyright (c) 2001-2004 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JDFRectangleTest {
	/**
	 *
	 * @throws Exception
	 */
	@Test
	public final void testRectangle() throws Exception
	{
		final JDFRectangle r1 = new JDFRectangle("0 0 1 1");
		JDFRectangle r2 = new JDFRectangle("0 0 1 1");
		Assertions.assertTrue(r1.equals(r2));
		Assertions.assertTrue(r1.isGreaterOrEqual(r2));
		Assertions.assertTrue(r1.isLessOrEqual(r2));
		Assertions.assertFalse(r1.isGreater(r2));
		Assertions.assertFalse(r1.isLess(r2));
		r2 = new JDFRectangle("0 0 1 2");
		Assertions.assertTrue(r1.isLessOrEqual(r2));
		Assertions.assertTrue(r1.isLess(r2));
		r2 = new JDFRectangle("0 0 1.000000001 1");
		Assertions.assertTrue(r1.isLessOrEqual(r2));
		Assertions.assertTrue(r1.equals(r2));

		r2 = new JDFRectangle("2 2 3 3");
		Assertions.assertFalse(r1.equals(r2));
		Assertions.assertFalse(r1.isGreaterOrEqual(r2));
		Assertions.assertFalse(r1.isLessOrEqual(r2));
		Assertions.assertFalse(r1.isGreater(r2));
		Assertions.assertFalse(r1.isLess(r2));

	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	public final void testRectangleMm() throws Exception
	{
		final JDFRectangle r1 = new JDFRectangle();
		r1.setLlxMm(0);
		r1.setLlyMm(0);
		r1.setUrxMm(1);
		r1.setUryMm(1);

		JDFRectangle r2 = new JDFRectangle();
		r2.setLlxMm(0);
		r2.setLlyMm(0);
		r2.setUrxMm(1);
		r2.setUryMm(1);

		Assertions.assertTrue(r1.equals(r2));
		Assertions.assertTrue(r1.isGreaterOrEqual(r2));
		Assertions.assertTrue(r1.isLessOrEqual(r2));
		Assertions.assertFalse(r1.isGreater(r2));
		Assertions.assertFalse(r1.isLess(r2));

		r2 = new JDFRectangle();
		r2.setLlxMm(0);
		r2.setLlyMm(0);
		r2.setUrxMm(1);
		r2.setUryMm(2);
		Assertions.assertTrue(r1.isLessOrEqual(r2));
		Assertions.assertTrue(r1.isLess(r2));

		r2 = new JDFRectangle();
		r2.setLlxMm(0);
		r2.setLlyMm(0);
		r2.setUrxMm(1.000000001);
		r2.setUryMm(1);
		Assertions.assertTrue(r1.isLessOrEqual(r2));
		Assertions.assertTrue(r1.equals(r2));

		r2 = new JDFRectangle();
		r2.setLlxMm(2);
		r2.setLlyMm(2);
		r2.setUrxMm(3);
		r2.setUryMm(3);
		Assertions.assertFalse(r1.equals(r2));
		Assertions.assertFalse(r1.isGreaterOrEqual(r2));
		Assertions.assertFalse(r1.isLessOrEqual(r2));
		Assertions.assertFalse(r1.isGreater(r2));
		Assertions.assertFalse(r1.isLess(r2));
	}

	/**
	 *
	 */
	@Test
	void testShift()
	{
		final JDFRectangle r = JDFRectangle.createRectangle("0 0 1 1");
		r.shift(5, 10);
		final JDFRectangle r2 = JDFRectangle.createRectangle("5 10 6 11");
		Assertions.assertEquals(r, r2);
	}

	/**
	 *
	 */
	@Test
	void testGetLL()
	{
		final JDFRectangle r = JDFRectangle.createRectangle("5 10 6 11");
		Assertions.assertEquals(new JDFXYPair(5, 10), r.getLL());
	}

	/**
	 *
	 */
	@Test
	void testGetUR()
	{
		final JDFRectangle r = JDFRectangle.createRectangle("5 10 6 11");
		Assertions.assertEquals(new JDFXYPair(6, 11), r.getUR());
	}

	/**
	 *
	 */
	@Test
	void testFromXYPair()
	{
		final JDFXYPair size = new JDFXYPair(2, 3);
		final JDFRectangle r = new JDFRectangle(size);
		Assertions.assertEquals(size, r.getSize());
		Assertions.assertEquals(size, r.getUR());
		Assertions.assertEquals(new JDFXYPair(), r.getLL());
	}

	/**
	 *
	 */
	@Test
	void testGetSize()
	{
		final JDFRectangle r = JDFRectangle.createRectangle("5 10 6 12");
		Assertions.assertEquals(new JDFXYPair(1, 2), r.getSize());
	}

	/**
	 *
	 */
	@Test
	void testGetArea()
	{
		final JDFRectangle r = JDFRectangle.createRectangle("1 2 3 4");
		Assertions.assertEquals(4, r.getArea(), 0.0);
	}

	/**
	 *
	 */
	@Test
	void testGetCenter()
	{
		final JDFRectangle r = JDFRectangle.createRectangle("4 10 6 12");
		Assertions.assertEquals(new JDFXYPair(5, 11), r.getCenter());
	}

	/**
	 *
	 */
	@Test
	void testIsInside()
	{
		final JDFRectangle r = JDFRectangle.createRectangle("4 10 6 12");
		Assertions.assertTrue(r.isInside(new JDFXYPair(5, 11)));
		Assertions.assertTrue(r.isInside(new JDFXYPair(4, 12)));
		Assertions.assertTrue(r.isInside(new JDFXYPair(6, 12)));
		Assertions.assertFalse(r.isInside(new JDFXYPair(6, 12.1)));
	}

	/**
	 *
	 */
	@Test
	void testSetCenter()
	{
		final JDFRectangle r = JDFRectangle.createRectangle("4 10 6 12");
		Assertions.assertEquals(new JDFXYPair(5, 11), r.getCenter());
		final JDFRectangle r2 = r.clone();
		r2.setCenter(r2.getCenter());
		Assertions.assertEquals(r2, r);
		r2.setCenter(new JDFXYPair(15, 31));
		r.shift(10, 20);
		Assertions.assertEquals(r2, r);

	}

	/**
	 *
	 */
	@Test
	void testScaleXY()
	{
		final JDFRectangle r = JDFRectangle.createRectangle("4 10 6 12");
		final JDFRectangle r2 = JDFRectangle.createRectangle("8 40 12 48");
		r.scale(new JDFXYPair(2, 4));
		Assertions.assertEquals(r2, r);

	}

	/**
	*
	*/
	@Test
	void testShiftXY()
	{
		final JDFRectangle r = JDFRectangle.createRectangle("0 0 1 1");
		r.shift(new JDFXYPair(5, 10));
		final JDFRectangle r2 = JDFRectangle.createRectangle("5 10 6 11");
		Assertions.assertEquals(r, r2);
	}

	/**
	*
	*/
	@Test
	void testNormalize()
	{
		final JDFRectangle r = JDFRectangle.createRectangle("0 0 1 1");
		final JDFRectangle r2 = r.clone();
		Assertions.assertEquals(r2, r.normalize());
		final JDFRectangle r3 = JDFRectangle.createRectangle("1 1 0 0");
		Assertions.assertEquals(r2, r3.normalize());
	}

	/**
	*
	*/
	@Test
	void testInterSect()
	{
		final JDFRectangle r = JDFRectangle.createRectangle("10 10 100 100");
		final JDFRectangle r2 = JDFRectangle.createRectangle("2 2 5 5");
		Assertions.assertNull(r.getInterSection(r2));
		Assertions.assertNull(r2.getInterSection(r));
		Assertions.assertEquals(r2, r2.getInterSection(r2));
		final JDFRectangle r3 = JDFRectangle.createRectangle("20 20 200 2000");
		Assertions.assertEquals(JDFRectangle.createRectangle("20 20 100 100"), r3.getInterSection(r));
		Assertions.assertEquals(JDFRectangle.createRectangle("20 20 100 100"), r.getInterSection(r3));
	}

	/**
	*
	*/
	@Test
	void testBounding()
	{
		final JDFRectangle r = JDFRectangle.createRectangle("10 5 100 100");
		final JDFRectangle r2 = JDFRectangle.createRectangle("22 22 45 45");
		Assertions.assertEquals(r, r2.getBoundingRect(r));
		Assertions.assertEquals(r, r.getBoundingRect(r2));
		Assertions.assertEquals(r, r.getBoundingRect(null));
		final JDFRectangle r3 = JDFRectangle.createRectangle("20 20 200 2000");
		Assertions.assertEquals(JDFRectangle.createRectangle("10 5 200 2000"), r3.getBoundingRect(r));
		Assertions.assertEquals(JDFRectangle.createRectangle("10 5 200 2000"), r.getBoundingRect(r3));
	}

	/**
	 *
	 */
	@Test
	void testCreate()
	{
		final JDFRectangle r = JDFRectangle.createRectangle("1 2 3 4");
		final JDFRectangle r2 = new JDFRectangle();
		r2.setLlx(1);
		r2.setLly(2);
		r2.setUrx(3);
		r2.setUry(4);
		Assertions.assertEquals(r, r2);
	}

	/**
	 *
	 */
	@Test
	public final void testClone()
	{

		final JDFRectangle ab = new JDFRectangle(1.0, 2.0, 3, 4);
		final JDFRectangle ac = ab.clone();
		Assertions.assertEquals(ab, ac);
	}
}
