/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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
 */
package org.cip4.jdflib.datatypes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.Test;

public class JDFTransferFunctionTest extends JDFTestCaseBase
{

	@Test
	public void testConstruct()
	{
		final JDFTransferFunction tf = new JDFTransferFunction();
		assertEquals(0, tf.size());
	}

	@Test
	public void testisUnit()
	{
		final JDFTransferFunction tf = new JDFTransferFunction();
		assertFalse(tf.isUnit());
		tf.add(0, 0);
		assertFalse(tf.isUnit());
		tf.add(1, 1);
		assertTrue(tf.isUnit());
		tf.add(1, 1);
	}

	@Test
	public void testGetUnit()
	{
		final JDFTransferFunction tf = JDFTransferFunction.getUnit();
		assertTrue(tf.isUnit());
		tf.add(1, 2);
		assertFalse(tf.isUnit());
		assertTrue(JDFTransferFunction.getUnit().isUnit());
	}

	@Test
	public void testAdd() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction();
		tf.add("1 2");
		assertEquals(2, tf.size());
	}

	@Test
	public void testGet()
	{
		final JDFTransferFunction tf = new JDFTransferFunction();
		assertEquals(0, tf.getX(0), 0);
	}

	@Test
	public void testSet()
	{
		final JDFTransferFunction tf = new JDFTransferFunction();
		final Vector<Double> v = new Vector<>();
		v.add(Double.valueOf(100));
		v.add(Double.valueOf(150));
		tf.set(10, 5, v);
		assertEquals(4, tf.size());
		assertEquals(10, tf.getX(0), 0);
		assertEquals(100, tf.getY(0), 0);
		assertEquals(15, tf.getX(1), 0);
		assertEquals(150, tf.getY(1), 0);
		assertEquals(0, tf.getY(2), 0);
	}

	@Test
	public void testToString()
	{
		final JDFTransferFunction tf = new JDFTransferFunction();
		final Vector<Double> v = new Vector<>();
		v.add(Double.valueOf(100));
		v.add(Double.valueOf(150));
		tf.set(10, 5, v);
		assertEquals("10 100 15 150", tf.toString());
	}

	@Test
	public void testGetPos() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("10 0.1 20 0.2 30 0.3 100 1.0");
		assertEquals(0, tf.getPos(0, true));
		assertEquals(-1, tf.getPos(0, false));
		assertEquals(0, tf.getPos(10, false));
		assertEquals(0, tf.getPos(10, true));
		assertEquals(0, tf.getPos(15, false));
		assertEquals(1, tf.getPos(15, true));
		assertEquals(2, tf.getPos(55, false));
		assertEquals(3, tf.getPos(55, true));
		assertEquals(3, tf.getPos(100, true));
		assertEquals(3, tf.getPos(100, false));
		assertEquals(-1, tf.getPos(155, false));
		assertEquals(-1, tf.getPos(155, true));
	}

	@Test
	public void testGetPosTime() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("10 0.1 20 0.2 30 0.3 100 1.0");
		final long t0 = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++)
		{
			assertEquals(0, tf.getPos(0, true));
			assertEquals(-1, tf.getPos(0, false));
			assertEquals(0, tf.getPos(10, false));
			assertEquals(0, tf.getPos(10, true));
			assertEquals(0, tf.getPos(15, false));
			assertEquals(1, tf.getPos(15, true));
			assertEquals(2, tf.getPos(55, false));
			assertEquals(3, tf.getPos(55, true));
			assertEquals(3, tf.getPos(100, true));
			assertEquals(3, tf.getPos(100, false));
			assertEquals(-1, tf.getPos(155, false));
			assertEquals(-1, tf.getPos(155, true));
		}
		log.info(" t=" + (System.currentTimeMillis() - t0));
	}

	@Test
	public void testGetVal() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("0 0 10 0.1 20 0.2 30 0.3 100 1.0");
		final long t0 = System.currentTimeMillis();
		for (int j = 0; j < 10000; j++)
		{
			assertEquals(0, tf.getValue(0), 0);
			for (int i = 0; i < 100; i++)
				assertEquals(0.01 * i, tf.getValue(i), 0.000001);
		}
		log.info(" t=" + (System.currentTimeMillis() - t0));
	}

	@Test
	public void testGetFastVal() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("0 0 10 0.05 20 0.1 30 0.15 200 1.0 460 2.3");
		tf.getFastValue(0);
		final long t0 = System.currentTimeMillis();
		for (int j = 0; j < 10000; j++)
		{
			assertEquals(0, tf.getFastValue(0), 0);
			for (int i = 0; i < 460; i++)
				assertEquals(0.005 * i, tf.getFastValue(i), 0.000001);
		}
		log.info(" t=" + (System.currentTimeMillis() - t0));
	}

	@Test
	public void testGetFastValUnit() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("0 0 1 1");
		tf.getFastValue(0);
		final long t0 = System.currentTimeMillis();
		for (int j = 0; j < 100000; j++)
		{
			assertEquals(0, tf.getFastValue(0), 0);
			assertEquals(0, tf.getFastValue(-1), 0);
			assertEquals(1, tf.getFastValue(1), 0);
			assertEquals(1, tf.getFastValue(3), 0);
			for (int i = 0; i < 100; i++)
				assertEquals(0.01 * i, tf.getFastValue(i * 0.01), 0.000001);
		}
		log.info(" t=" + (System.currentTimeMillis() - t0));
	}

	@Test
	public void testIslUnit() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("0 0 1 1");
		final long t0 = System.currentTimeMillis();
		for (int j = 0; j < 100000; j++)
		{
			for (int i = 0; i < 100; i++)
				assertTrue(tf.isUnit());
		}
		log.info(" t=" + (System.currentTimeMillis() - t0));
	}

	@Test
	public void testGetValDesc() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("0 1.0 100 0");
		assertEquals(1, tf.getValue(0), 0);
		for (int i = 0; i < 100; i++)
			assertEquals(1 - 0.01 * i, tf.getValue(i), 0.000001);
	}

	@Test
	public void testGetRange() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("10 1.0 100 0");
		assertEquals(10, tf.getXRange().getX(), 0);
		assertEquals(100, tf.getXRange().getY(), 0);
	}

	@Test
	public void testGetRangeEmpty()
	{
		final JDFTransferFunction tf = new JDFTransferFunction();
		assertEquals(0, tf.getXRange().getX(), 0);
		assertEquals(0, tf.getXRange().getY(), 0);
	}

	@Test
	public void testMultiply() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("0 0 1 2.0");
		final JDFTransferFunction tf2 = new JDFTransferFunction("0 0 0.5 0.4 1 1.0");
		tf.multiply(tf2);
		assertEquals(3, tf.numPoints());
		assertEquals(0.0, tf.getValue(0.0), 0);
		assertEquals(0.8, tf.getValue(0.5), 0);
		assertEquals(2.0, tf.getValue(1.0), 0);

	}

	@Test
	public void testMultiplyStatic() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("0 0 1 2.0");
		final JDFTransferFunction tf2 = new JDFTransferFunction("0 0 0.5 0.4 1 1.0");
		final JDFTransferFunction tf3 = JDFTransferFunction.multiply(tf, tf2);
		assertEquals(3, tf3.numPoints());
		assertEquals(0.0, tf3.getValue(0.0), 0);
		assertEquals(0.8, tf3.getValue(0.5), 0);
		assertEquals(2.0, tf3.getValue(1.0), 0);
		final JDFTransferFunction tf4 = JDFTransferFunction.multiply(tf, null);
		assertEquals(tf, tf4);
		final JDFTransferFunction tf5 = JDFTransferFunction.multiply(null, tf);
		assertEquals(tf, tf5);
	}
}
