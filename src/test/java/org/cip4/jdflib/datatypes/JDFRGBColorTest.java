/*
 *
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
 * JDFIntegerRangeListTest.java
 *
 * @author Elena Skobchenko
 *
 *         Copyright (c) 2001-2004 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import static org.junit.Assert.assertEquals;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.CPUTimer;
import org.junit.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         23.01.2009
 */
public class JDFRGBColorTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	public void testgetHTMLcolor()
	{
		JDFRGBColor c = new JDFRGBColor(0, 0, 0);
		assertEquals(c.getHTMLColor(), "#000000");
		c = new JDFRGBColor(1, 1., 1);
		assertEquals(c.getHTMLColor(), "#ffffff");
	}

	/**
	 *
	 */
	@Test
	public void testFromHTMLcolor()
	{
		final JDFRGBColor c = JDFRGBColor.createRGBColor("#f08123");
		assertEquals(0xf0, c.getR255());
		assertEquals(0x81, c.getG255());
		assertEquals(0x23, c.getB255());
	}

	/**
	 *
	 */
	@Test
	public void testgetHTMLcolorInt()
	{
		final JDFRGBColor c = new JDFRGBColor(255, 255, 255);
		assertEquals(c.getHTMLColor(), "#ffffff");
	}

	/**
	*
	*/
	@Test
	public void testgetRGB255()
	{
		final JDFRGBColor c = new JDFRGBColor(0, 0.5, 1);
		assertEquals(0, c.getR255());
		assertEquals(127, c.getG255());
		assertEquals(255, c.getB255());
	}

	/**
	*
	*/
	@Test
	public void testsetRGB255()
	{
		final JDFRGBColor c = new JDFRGBColor(0, 0, 0);
		c.setR255(0);
		for (int i = 0; i < 255; i++)
		{
			c.setR255(i);
			c.setG255(i);
			c.setB255(i);
			assertEquals(i, c.getR255());
			assertEquals(i, c.getG255());
			assertEquals(i, c.getB255());
		}

	}

	/**
	 *
	 */
	@Test
	public void testgetCMYKcolor()
	{
		JDFRGBColor co = new JDFRGBColor(0, 0, 0);
		assertEquals(co.getCMYK(), new JDFCMYKColor(0, 0, 0, 1));
		co = new JDFRGBColor(1., 1, 1);
		assertEquals(co.getCMYK(), new JDFCMYKColor(0, 0, 0, 0));
		co = new JDFRGBColor(0, 1., 1);
		assertEquals(co.getCMYK(), new JDFCMYKColor(1, 0, 0, 0));
		for (double r = 0; r <= 1; r += 0.1)
		{
			for (double g = 0; g <= 1; g += 0.1)
			{
				for (double b = 0; b <= 1; b += 0.1)
				{
					co = new JDFRGBColor(r, g, b);
					assertEquals(co.getCMYK().getRGB(), co);
				}
			}
		}
	}

	/**
	*
	*/
	@Test
	public void testgetCMYKcolorInt()
	{
		final CPUTimer ct2 = new CPUTimer(false);
		for (int r = 0; r <= 255; r++)
		{
			ct2.start();
			for (int g = 0; g <= 255; g++)
			{
				for (int b = 0; b <= 255; b++)
				{
					final double[] cmykArray = JDFRGBColor.getCMYKArray(r, g, b);
					final double[] rgbArray = JDFCMYKColor.getRGBArray(cmykArray[0], cmykArray[1], cmykArray[2], cmykArray[3]);
					assertEquals(rgbArray[0], r / 255., 0.0000001);
					assertEquals(rgbArray[1], g / 255., 0.0000001);
					assertEquals(rgbArray[2], b / 255., 0.0000001);
				}
			}
			ct2.stop();
		}
		log.info(ct2.toString());
	}

	/**
	 *
	 */
	@Test
	public void testgetCMYKcolorPerformance()
	{
		final CPUTimer ct = new CPUTimer(false);
		for (double r = 0; r <= 1; r += 0.01)
		{
			ct.start();
			for (double g = 0; g <= 1; g += 0.01)
			{
				for (double b = 0; b <= 1; b += 0.01)
				{
					final JDFRGBColor co = new JDFRGBColor(r, g, b);
					assertEquals(co.getCMYK().getRGB(), co);
				}
			}
			ct.stop();
		}
		log.info(ct.toString());
		final CPUTimer ct2 = new CPUTimer(false);
		for (double r = 0; r <= 1; r += 0.01)
		{
			ct2.start();
			for (double g = 0; g <= 1; g += 0.01)
			{
				for (double b = 0; b <= 1; b += 0.01)
				{
					final double[] cmykArray = JDFRGBColor.getCMYKArray(r, g, b);
					final double[] rgbArray = JDFCMYKColor.getRGBArray(cmykArray[0], cmykArray[1], cmykArray[2], cmykArray[3]);
					assertEquals(rgbArray[0], r, 0.0000001);
					assertEquals(rgbArray[1], g, 0.0000001);
					assertEquals(rgbArray[2], b, 0.0000001);
				}
			}
			ct2.stop();
		}
		log.info(ct2.toString());
	}
}
