/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of
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
 * JDFXYPairRangeListTest.java
 *
 * @author Elena Skobchenko
 *
 * Copyright (c) 2001-2004 The International Cooperation for the Integration
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumOrientation;
import org.cip4.jdflib.node.JDFNode;
import org.junit.Test;

/**
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFMatrixTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	public void testCreate()
	{
		final JDFMatrix m = new JDFMatrix(90, 20, 20);
		final JDFMatrix m2 = new JDFMatrix(EnumOrientation.Rotate90, 0, 0);
		m2.shift(20, 20);
		assertEquals(m, m2);
	}

	/**
	 *
	 */
	@Test
	public void testCreateFromRect()
	{
		final JDFRectangle r = new JDFRectangle(0, 10, 30, 40);
		final JDFMatrix m = new JDFMatrix(r);
		final JDFMatrix m2 = JDFMatrix.getUnitMatrix();
		m2.shift(0, 10);
		assertEquals(m, m2);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	public void testSetString() throws Exception
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();

		final JDFMatrix m = new JDFMatrix("1 0 0 1 0 0");
		assertEquals(m, JDFMatrix.getUnitMatrix());
		n.setAttribute("foo", m, null);
		assertEquals(n.getAttribute("foo"), m.toString());
	}

	// ////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testOrientation()
	{

		final JDFMatrix m = new JDFMatrix(EnumOrientation.Rotate0, 0, 0);
		assertEquals(m, JDFMatrix.getUnitMatrix());
	}

	/**
	 *
	 */
	@Test
	public void testRotate()
	{
		JDFMatrix m = new JDFMatrix(EnumOrientation.Rotate0, 0, 0);
		assertEquals(m, JDFMatrix.getUnitMatrix());
		m.rotate(180);
		assertEquals(m, new JDFMatrix(EnumOrientation.Rotate180, 0, 0));
		assertEquals(m.getAngle(), 180.0, 0.00001);
		m.rotate(90);
		assertEquals(m, new JDFMatrix(EnumOrientation.Rotate270, 0, 0));
		assertEquals(m.getAngle(), 270.0, 0.00001);

		m.rotate(180);
		assertEquals(m, new JDFMatrix(EnumOrientation.Rotate90, 0, 0));
		m = new JDFMatrix(EnumOrientation.Flip0, 0, 0);
		m.rotate(180);
		assertEquals(m, new JDFMatrix(EnumOrientation.Flip180, 0, 0));
		m.rotate(90);
		assertEquals(m, new JDFMatrix(EnumOrientation.Flip270, 0, 0));
		for (int i = 0; i < 1800; i += 45)
		{
			final JDFMatrix m2 = JDFMatrix.getUnitMatrix();
			m2.rotate(0.1 * i);
			assertEquals(m2.getAngle(), 0.1 * i, 0.000001);
		}
	}

	/**
	 *
	 */
	@Test
	public void testMultiRotate()
	{
		for (int i = 0; i < 4; i++)
		{
			final JDFMatrix m = JDFMatrix.getUnitMatrix();
			m.shift(i * 10, i * 20);
			final JDFMatrix m2 = new JDFMatrix(m);
			for (int ii = 0; ii < 18; ii++)
			{
				m2.rotate(20);
			}
			assertEquals(m, m2);
		}
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testShift() throws Exception
	{

		final JDFMatrix m = new JDFMatrix(EnumOrientation.Rotate0, 0, 0);
		assertEquals(m, JDFMatrix.getUnitMatrix());
		m.shift(10, 11);
		assertEquals(m, new JDFMatrix("1 0 0 1 10 11"));
		m.shift(10, 11);
		assertEquals("2nd shift adds up", m, new JDFMatrix("1 0 0 1 20 22"));
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testShiftXY() throws Exception
	{

		final JDFMatrix m = new JDFMatrix(EnumOrientation.Rotate90, 0, 0);
		m.shift(new JDFXYPair(20, 25));
		assertEquals(m, new JDFMatrix("0 1 -1 0 20 25"));
		m.shift(new JDFXYPair(20, 25));
		assertEquals(m, new JDFMatrix("0 1 -1 0 40 50"));
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testShiftMulti() throws Exception
	{

		final JDFMatrix m = new JDFMatrix(EnumOrientation.Rotate90, 0, 0);

		m.shift(new JDFXYPair(20, 25)).shift(new JDFXYPair(20, 25));
		assertEquals(m, new JDFMatrix("0 1 -1 0 40 50"));
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testSetShiftXY() throws Exception
	{

		final JDFMatrix m = new JDFMatrix(EnumOrientation.Rotate90, 0, 0);
		m.setShift(new JDFXYPair(20, 25));
		assertEquals(m, new JDFMatrix("0 1 -1 0 20 25"));
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testTransForm() throws Exception
	{
		final JDFMatrix m = new JDFMatrix(EnumOrientation.Rotate0, 0, 0);
		final JDFXYPair p = new JDFXYPair(10, 10);
		final JDFXYPair p2 = m.transform(p);
		assertEquals(p, p2);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testTransFormShift() throws Exception
	{
		final JDFMatrix m = new JDFMatrix(EnumOrientation.Rotate0, 0, 0);
		m.shift(30, 30);
		final JDFXYPair p = new JDFXYPair(10, 10);
		final JDFXYPair p2 = m.transform(p);
		assertEquals(p.shift(30, 30), p2);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testTransFormRect() throws Exception
	{
		final JDFMatrix m = new JDFMatrix(EnumOrientation.Rotate0, 0, 0);
		m.shift(30, 30);
		final JDFRectangle p = new JDFRectangle(10, 10, 20, 20);
		final JDFRectangle p2 = m.transform(p);
		p.shift(30, 30);
		assertEquals(p, p2);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testTransFormRotate() throws Exception
	{
		final JDFMatrix m = new JDFMatrix(EnumOrientation.Rotate90, 10, 10);
		final JDFMatrix m2 = new JDFMatrix(EnumOrientation.Rotate180, 10, 10);
		final JDFXYPair p = new JDFXYPair(10, 10);
		final JDFXYPair p2 = m.transform(p);
		final JDFXYPair p22 = m.transform(p2);
		final JDFXYPair p20 = m2.transform(p);
		assertEquals(p20, p22);
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testGetShiftXY() throws Exception
	{

		final JDFMatrix m = new JDFMatrix(EnumOrientation.Rotate90, 0, 0);
		assertEquals(m.getShift(), new JDFXYPair());
		m.setShift(new JDFXYPair(20, 25));
		assertEquals(m.getShift(), new JDFXYPair(20, 25));
	}

	/**
	 *
	 */
	@Test
	public void testConcat()
	{

		JDFMatrix m = new JDFMatrix(EnumOrientation.Rotate90, 0, 0);
		final JDFMatrix m2 = new JDFMatrix(EnumOrientation.Rotate270, 0, 0);
		m.concat(m2);
		assertEquals(m, JDFMatrix.getUnitMatrix());

		m = new JDFMatrix(EnumOrientation.Rotate90, 0, 0);
		m.concat(m);
		assertEquals(m, new JDFMatrix(EnumOrientation.Rotate180, 0, 0));

		m = new JDFMatrix(EnumOrientation.Flip180, 0, 0);
		m.concat(m);
		assertEquals(m, new JDFMatrix(EnumOrientation.Rotate0, 0, 0));

	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testClone() throws Exception
	{
		final JDFMatrix m = JDFMatrix.getUnitMatrix();
		m.rotate(180);
		assertNotSame(JDFMatrix.getUnitMatrix(), m);
		assertEquals(new JDFMatrix("1 0 0 1 0 0"), JDFMatrix.getUnitMatrix());
		assertEquals(new JDFMatrix(EnumOrientation.Rotate180, 0, 0), m);
	}

}
