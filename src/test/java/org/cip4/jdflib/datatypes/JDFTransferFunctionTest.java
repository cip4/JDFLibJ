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

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JDFTransferFunctionTest extends JDFTestCaseBase
{

	@Test
	void testConstruct()
	{
		final JDFTransferFunction tf = new JDFTransferFunction();
		Assertions.assertEquals(0, tf.size());
	}

	@Test
	void testisUnit()
	{
		final JDFTransferFunction tf = new JDFTransferFunction();
		Assertions.assertFalse(tf.isUnit());
		tf.add(0, 0);
		Assertions.assertFalse(tf.isUnit());
		tf.add(1, 1);
		Assertions.assertTrue(tf.isUnit());
		tf.add(1, 1);
	}

	@Test
	void testGetUnit()
	{
		final JDFTransferFunction tf = JDFTransferFunction.getUnit();
		Assertions.assertTrue(tf.isUnit());
		tf.add(1, 2);
		Assertions.assertFalse(tf.isUnit());
		Assertions.assertTrue(JDFTransferFunction.getUnit().isUnit());
	}

	@Test
	void testAdd() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction();
		tf.add("1 2");
		Assertions.assertEquals(2, tf.size());
	}

	@Test
	void testGet()
	{
		final JDFTransferFunction tf = new JDFTransferFunction();
		Assertions.assertEquals(0, tf.getX(0), 0);
	}

	@Test
	void testSet()
	{
		final JDFTransferFunction tf = new JDFTransferFunction();
		final Vector<Double> v = new Vector<>();
		v.add(Double.valueOf(100));
		v.add(Double.valueOf(150));
		tf.set(10, 5, v);
		Assertions.assertEquals(4, tf.size());
		Assertions.assertEquals(10, tf.getX(0), 0);
		Assertions.assertEquals(100, tf.getY(0), 0);
		Assertions.assertEquals(15, tf.getX(1), 0);
		Assertions.assertEquals(150, tf.getY(1), 0);
		Assertions.assertEquals(0, tf.getY(2), 0);
	}

	@Test
	void testToString()
	{
		final JDFTransferFunction tf = new JDFTransferFunction();
		final Vector<Double> v = new Vector<>();
		v.add(Double.valueOf(100));
		v.add(Double.valueOf(150));
		tf.set(10, 5, v);
		Assertions.assertEquals("10 100 15 150", tf.toString());
	}

	@Test
	void testGetPos() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("10 0.1 20 0.2 30 0.3 100 1.0");
		Assertions.assertEquals(0, tf.getPos(0, true));
		Assertions.assertEquals(-1, tf.getPos(0, false));
		Assertions.assertEquals(0, tf.getPos(10, false));
		Assertions.assertEquals(0, tf.getPos(10, true));
		Assertions.assertEquals(0, tf.getPos(15, false));
		Assertions.assertEquals(1, tf.getPos(15, true));
		Assertions.assertEquals(2, tf.getPos(55, false));
		Assertions.assertEquals(3, tf.getPos(55, true));
		Assertions.assertEquals(3, tf.getPos(100, true));
		Assertions.assertEquals(3, tf.getPos(100, false));
		Assertions.assertEquals(-1, tf.getPos(155, false));
		Assertions.assertEquals(-1, tf.getPos(155, true));
	}

	@Test
	void testGetPosTime() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("10 0.1 20 0.2 30 0.3 100 1.0");
		final long t0 = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++)
		{
			Assertions.assertEquals(0, tf.getPos(0, true));
			Assertions.assertEquals(-1, tf.getPos(0, false));
			Assertions.assertEquals(0, tf.getPos(10, false));
			Assertions.assertEquals(0, tf.getPos(10, true));
			Assertions.assertEquals(0, tf.getPos(15, false));
			Assertions.assertEquals(1, tf.getPos(15, true));
			Assertions.assertEquals(2, tf.getPos(55, false));
			Assertions.assertEquals(3, tf.getPos(55, true));
			Assertions.assertEquals(3, tf.getPos(100, true));
			Assertions.assertEquals(3, tf.getPos(100, false));
			Assertions.assertEquals(-1, tf.getPos(155, false));
			Assertions.assertEquals(-1, tf.getPos(155, true));
		}
		log.info(" t=" + (System.currentTimeMillis() - t0));
	}

	@Test
	void testGetVal() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("0 0 10 0.1 20 0.2 30 0.3 100 1.0");
		final long t0 = System.currentTimeMillis();
		for (int j = 0; j < 10000; j++)
		{
			Assertions.assertEquals(0, tf.getValue(0), 0);
			for (int i = 0; i < 100; i++)
				Assertions.assertEquals(0.01 * i, tf.getValue(i), 0.000001);
		}
		log.info(" t=" + (System.currentTimeMillis() - t0));
	}

	@Test
	void testGetFastVal() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("0 0 10 0.05 20 0.1 30 0.15 200 1.0 460 2.3");
		tf.getFastValue(0);
		final long t0 = System.currentTimeMillis();
		for (int j = 0; j < 10000; j++)
		{
			Assertions.assertEquals(0, tf.getFastValue(0), 0);
			for (int i = 0; i < 460; i++)
				Assertions.assertEquals(0.005 * i, tf.getFastValue(i), 0.000001);
		}
		log.info(" t=" + (System.currentTimeMillis() - t0));
	}

	@Test
	void testGetFastValUnit() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("0 0 1 1");
		tf.getFastValue(0);
		final long t0 = System.currentTimeMillis();
		for (int j = 0; j < 100000; j++)
		{
			Assertions.assertEquals(0, tf.getFastValue(0), 0);
			Assertions.assertEquals(0, tf.getFastValue(-1), 0);
			Assertions.assertEquals(1, tf.getFastValue(1), 0);
			Assertions.assertEquals(1, tf.getFastValue(3), 0);
			for (int i = 0; i < 100; i++)
				Assertions.assertEquals(0.01 * i, tf.getFastValue(i * 0.01), 0.000001);
		}
		log.info(" t=" + (System.currentTimeMillis() - t0));
	}

	@Test
	void testIslUnit() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("0 0 1 1");
		final long t0 = System.currentTimeMillis();
		for (int j = 0; j < 100000; j++)
		{
			for (int i = 0; i < 100; i++)
				Assertions.assertTrue(tf.isUnit());
		}
		log.info(" t=" + (System.currentTimeMillis() - t0));
	}

	@Test
	void testGetValDesc() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("0 1.0 100 0");
		Assertions.assertEquals(1, tf.getValue(0), 0);
		for (int i = 0; i < 100; i++)
			Assertions.assertEquals(1 - 0.01 * i, tf.getValue(i), 0.000001);
	}

	@Test
	void testGetRange() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("10 1.0 100 0");
		Assertions.assertEquals(10, tf.getXRange().getX(), 0);
		Assertions.assertEquals(100, tf.getXRange().getY(), 0);
	}

	@Test
	void testGetRangeEmpty()
	{
		final JDFTransferFunction tf = new JDFTransferFunction();
		Assertions.assertEquals(0, tf.getXRange().getX(), 0);
		Assertions.assertEquals(0, tf.getXRange().getY(), 0);
	}

	@Test
	void testMultiply() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("0 0 1 2.0");
		final JDFTransferFunction tf2 = new JDFTransferFunction("0 0 0.5 0.4 1 1.0");
		tf.multiply(tf2);
		Assertions.assertEquals(3, tf.numPoints());
		Assertions.assertEquals(0.0, tf.getValue(0.0), 0);
		Assertions.assertEquals(0.8, tf.getValue(0.5), 0);
		Assertions.assertEquals(2.0, tf.getValue(1.0), 0);

	}

	@Test
	void testMultiplyStatic() throws DataFormatException
	{
		final JDFTransferFunction tf = new JDFTransferFunction("0 0 1 2.0");
		final JDFTransferFunction tf2 = new JDFTransferFunction("0 0 0.5 0.4 1 1.0");
		final JDFTransferFunction tf3 = JDFTransferFunction.multiply(tf, tf2);
		Assertions.assertEquals(3, tf3.numPoints());
		Assertions.assertEquals(0.0, tf3.getValue(0.0), 0);
		Assertions.assertEquals(0.8, tf3.getValue(0.5), 0);
		Assertions.assertEquals(2.0, tf3.getValue(1.0), 0);
		final JDFTransferFunction tf4 = JDFTransferFunction.multiply(tf, null);
		Assertions.assertEquals(tf, tf4);
		final JDFTransferFunction tf5 = JDFTransferFunction.multiply(null, tf);
		Assertions.assertEquals(tf, tf5);
	}
}
