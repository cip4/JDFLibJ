/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 * @date Jan 4, 2011
 */
class NumberFormatterTest extends JDFTestCaseBase
{
	/**
	 *
	 *
	 */
	@Test
	void testFormat()
	{
		final NumberFormatter numberFormatter = new NumberFormatter();
		numberFormatter.setZapp0(true);
		assertEquals("-3", numberFormatter.formatDouble(-2.999999999));
		assertEquals("-3", numberFormatter.formatDouble(-2.999999999, 3));
		assertEquals("-2.9999", numberFormatter.formatDouble(-2.9999, 4));
		assertEquals("-2.9999", numberFormatter.formatDouble(-2.9999, 5));
	}

	/**
	 *
	 *
	 */
	@Test
	void testFormatExp()
	{
		final NumberFormatter numberFormatter = new NumberFormatter();
		assertEquals("25000000000000000000000000000000000000000", numberFormatter.formatDouble(2.5e40));
		assertEquals(2.5e40, StringUtil.parseDouble("25000000000000000000000000000000000000000", 0), 0.001);
	}

	/**
	 *
	 *
	 */
	@Test
	void testFormatPrecision()
	{
		final NumberFormatter numberFormatter = new NumberFormatter();
		numberFormatter.setZapp0(false);
		assertEquals("3", numberFormatter.formatDouble(3.2, 0));
		assertEquals("0", numberFormatter.formatDouble(0.1, 0));
		assertEquals("0", numberFormatter.formatDouble(0.49, 0));
		assertEquals("0", numberFormatter.formatDouble(-0.49, 0));
		assertEquals("1", numberFormatter.formatDouble(0.6, 0));
		assertEquals("-1", numberFormatter.formatDouble(-0.6, 0));
		assertEquals("3.2", numberFormatter.formatDouble(3.2, 1));
		assertEquals("3.20", numberFormatter.formatDouble(3.2, 2));
		assertEquals("3.20", numberFormatter.formatDouble(3.199, 2));
		numberFormatter.setZapp0(true);
		assertEquals("3.2", numberFormatter.formatDouble(3.2, 2));
		assertEquals("3.2", numberFormatter.formatDouble(3.199, 2));
	}

	/**
	 *
	 *
	 */
	@Test
	void testFormatInt()
	{
		final NumberFormatter numberFormatter = new NumberFormatter();
		numberFormatter.setZapp0(false);
		assertEquals("3", numberFormatter.formatInt(3, 0));
		assertEquals("3", numberFormatter.formatInt(3, 1));
		assertEquals("03", numberFormatter.formatInt(3, 2));
		assertEquals("007", numberFormatter.formatInt(7, 3));
		assertEquals("-3", numberFormatter.formatInt(-3, 2));
		assertEquals("-03", numberFormatter.formatInt(-3, 3));
		assertEquals("-007", numberFormatter.formatInt(-7, 4));
		assertEquals("-3", numberFormatter.formatInt(-3, 0));
		assertEquals("-3", numberFormatter.formatInt(-3, 1));
		assertEquals("-42", numberFormatter.formatInt(-42, 2));
		assertEquals("-42", numberFormatter.formatInt(-42, 1));
		assertEquals("0", numberFormatter.formatInt(0, 0));
		assertEquals("0000", numberFormatter.formatInt(0, 4));
	}

	/**
	*
	*
	*/
	@Test
	void testFormatIntPerf()
	{
		final NumberFormatter numberFormatter = new NumberFormatter();
		long t0 = System.currentTimeMillis();
		for (int i = -1000000; i < 1000000; i++)
		{
			final String s = new NumberFormatter().formatInt(i, 0);
			assertEquals(i, StringUtil.parseInt(s, 42));
		}
		long t1 = System.currentTimeMillis();
		log.info("" + (t1 - t0));
		t0 = System.currentTimeMillis();
		for (int i = -1000000; i < 1000000; i++)
		{
			final String s = numberFormatter.formatInt(i, 0);
		}
		t1 = System.currentTimeMillis();
		log.info("" + (t1 - t0));
		for (int i = -1000000; i < 1000000; i++)
		{
			final String s = numberFormatter.formatInt(i, 0);
			assertEquals(i, StringUtil.parseInt(s, 42));
		}
		t1 = System.currentTimeMillis();
		log.info("" + (t1 - t0));
		t0 = System.currentTimeMillis();
		for (int i = -10000; i < 10000; i++)
		{
			final String s = numberFormatter.formatDouble(i, 5);
		}
		t1 = System.currentTimeMillis();
		log.info("" + (t1 - t0));
		for (int i = -10000; i < 10000; i++)
		{
			final String s = numberFormatter.formatDouble(i, 5);
			assertEquals(i, StringUtil.parseDouble(s, 42), 0.001);
		}
		t1 = System.currentTimeMillis();
		log.info("" + (t1 - t0));
		t0 = System.currentTimeMillis();
		for (int i = -10000; i < 10000; i++)
		{
			final String s = numberFormatter.formatDouble(i * Math.PI, 5);
		}
		t1 = System.currentTimeMillis();
		log.info("" + (t1 - t0));
		for (int i = -10000; i < 10000; i++)
		{
			final String s = numberFormatter.formatDouble(i * Math.PI, 5);
			assertEquals(i * Math.PI, StringUtil.parseDouble(s, 42), 0.001);
		}
		t1 = System.currentTimeMillis();
		log.info("" + (t1 - t0));

	}

	/**
	 *
	 *
	 */
	@Test
	void testFormatDouble()
	{
		final NumberFormatter numberFormatter = new NumberFormatter();
		assertEquals("3", numberFormatter.formatDouble(3.0));
		final double d = Double.NaN;
		assertEquals(null, numberFormatter.formatDouble(d));
		assertEquals("3", numberFormatter.formatDouble(Math.PI, 0));
		assertEquals("3", numberFormatter.formatDouble(Math.PI, -1));
		assertEquals("3.14", numberFormatter.formatDouble(Math.PI, 2));
	}

}
