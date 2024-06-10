/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
 * JDFXYPairRangeListTest.java
 *
 * @author Elena Skobchenko
 *
 *         Copyright (c) 2001-2004 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.CPUTimer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 *
 * @author rainer prosi
 * @date Feb 23, 2011
 */
class JDFXYPairTest extends JDFTestCaseBase
{
	/**
	 *
	 *
	 * @throws Exception
	 */
	@Test
	public final void testSetString() throws Exception
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();

		JDFXYPair xy = new JDFXYPair("1 2");
		n.setAttribute("test", xy, null);

		xy = new JDFXYPair("1.1 2.2");
		n.setAttribute("test2", xy, null);
		Assertions.assertEquals(n.getAttribute("test2", null, ""), "1.1 2.2", "double double");

		try
		{
			new JDFXYPair("1 2 3");
		}
		catch (final DataFormatException dfe)
		{
			Assertions.assertTrue(true, "exception 123 caught");
		}
		try
		{
			new JDFXYPair((String) null);
		}
		catch (final DataFormatException dfe)
		{
			Assertions.assertTrue(true, "exception null caught");
		}
	}

	/**
	 *
	 *
	 * @throws Exception
	 */
	@Test
	public final void testSingleVal() throws Exception
	{
		JDFXYPair xy = new JDFXYPair("4");
		Assertions.assertEquals(xy.getX(), 4, 0.0);
		Assertions.assertEquals(xy.getY(), 0, 0.0);

		xy = new JDFXYPair("4/1");
		Assertions.assertEquals(xy.getX(), 4, 0.0);
		Assertions.assertEquals(xy.getY(), 1, 0.0);
		xy = new JDFXYPair(" 4 / 1 ");
		Assertions.assertEquals(xy.getX(), 4, 0.0);
		Assertions.assertEquals(xy.getY(), 1, 0.0);
	}

	/**
	 *
	 *
	 * @throws Exception
	 */
	@Test
	public final void testIsPortrait() throws Exception
	{
		final JDFXYPair xy = new JDFXYPair("4 5");
		Assertions.assertTrue(xy.isPortrait());
		xy.setY(4);
		Assertions.assertFalse(xy.isPortrait());
	}

	/**
	 *
	 *
	 * @throws Exception
	 */
	@Test
	public final void testSwapXY() throws Exception
	{
		final JDFXYPair xy = new JDFXYPair("4 5");
		final JDFXYPair yx = new JDFXYPair("5 4");
		Assertions.assertNotSame(xy, yx);
		yx.swapXY();
		Assertions.assertEquals(xy, yx);
	}

	/**
	 *
	 *
	 * @throws Exception
	 */
	@Test
	public final void testShift() throws Exception
	{
		final JDFXYPair xy = new JDFXYPair("4 5");
		final JDFXYPair yx = new JDFXYPair("8 9");
		yx.shift(xy);
		Assertions.assertEquals(new JDFXYPair(12, 14), yx);
	}

	/**
	 *
	 *
	 */
	@Test
	public final void testIsGreaterOrEqual()
	{
		JDFXYPair xy = new JDFXYPair();

		final JDFXYPair ab = new JDFXYPair(1.0, 2.0);
		xy = new JDFXYPair(1.0 + JDFBaseDataTypes.EPSILON / 2.0, 2.0 + JDFBaseDataTypes.EPSILON / 2.0);

		Assertions.assertTrue(ab.equals(xy));
		Assertions.assertTrue(ab.isLessOrEqual(xy));
		Assertions.assertTrue(ab.isGreaterOrEqual(xy));
	}

	/**
	 *
	 */
	@Test
	public final void testClone()
	{

		final JDFXYPair ab = new JDFXYPair(1.0, 2.0);
		final JDFXYPair ac = new JDFXYPair(ab);
		ac.setX(3.0);
		Assertions.assertEquals(ab.getX(), 1.0, 0.0);
	}

	/**
	 *
	 */
	@Test
	public final void testClone2()
	{

		final JDFXYPair ab = new JDFXYPair(1.0, 2.0);
		final JDFXYPair ac = ab.clone();
		Assertions.assertEquals(ab, ac);
	}

	/**
	 *
	 */
	@Test
	public final void testInt()
	{

		final JDFXYPair ab = new JDFXYPair(1, 2);
		final JDFXYPair ac = ab.clone();
		Assertions.assertEquals(ab, ac);
		Assertions.assertEquals(1, ab.getX(), 0);
		Assertions.assertEquals(2, ab.getY(), 0);
	}

	/**
	 *
	 */
	@Test
	public final void testShapeConstruct()
	{

		final JDFShape ab = new JDFShape(1.0, 2.0, 3.0);
		final JDFXYPair ac = new JDFXYPair(ab);
		Assertions.assertEquals(ac, new JDFXYPair(1.0, 2.0));
	}

	/**
	 *
	 *
	 */
	@Test
	public final void testIsLessOrEqual()
	{
		JDFXYPair xy = new JDFXYPair();

		final JDFXYPair ab = new JDFXYPair(1.0, 2.0);
		xy = new JDFXYPair(1.0 - JDFBaseDataTypes.EPSILON / 2.0, 2.0 - JDFBaseDataTypes.EPSILON / 2.0);

		Assertions.assertTrue(ab.equals(xy));
		Assertions.assertTrue(ab.isLessOrEqual(xy));
		Assertions.assertTrue(ab.isGreaterOrEqual(xy));
	}

	/**
	 *
	 *
	 */
	@Test
	public final void testcreateXYPair()
	{
		Assertions.assertNull(JDFXYPair.createXYPair(null));
		Assertions.assertNull(JDFXYPair.createXYPair(""));
		Assertions.assertNull(JDFXYPair.createXYPair("a"));
		Assertions.assertNotNull(JDFXYPair.createXYPair("1"));
		Assertions.assertNull(JDFXYPair.createXYPair("1 2 a"));
		Assertions.assertNotNull(JDFXYPair.createXYPair("1 2"));
		Assertions.assertNotNull(JDFXYPair.createXYPair("  1 2 "));
		Assertions.assertNotNull(JDFXYPair.createXYPair("  1. 2 "));
		Assertions.assertNotNull(JDFXYPair.createXYPair("  1.00 2.00 "));
	}

	/**
	*
	*
	*/
	@Test
	public final void testcreateXYPairPerformance()
	{
		final CPUTimer t1 = new CPUTimer(true);
		for (int i = 0; i < 100000; i++)
		{
			final JDFXYPair p = JDFXYPair.createXYPair("");
			Assertions.assertNull(p);
		}
		t1.stop();
		log.info("fast: " + t1);

		final CPUTimer t2 = new CPUTimer(true);
		for (int i = 0; i < 100000; i++)
		{
			try
			{
				new JDFXYPair("");
				Assertions.fail("blurb");
			}
			catch (final DataFormatException x)
			{
				//
			}

		}
		t2.stop();
		System.out.println("slow: " + t2);
	}
}
