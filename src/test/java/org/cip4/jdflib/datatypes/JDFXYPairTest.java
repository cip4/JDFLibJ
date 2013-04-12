/*
 *
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

import java.util.zip.DataFormatException;

import junit.framework.TestCase;

import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.CPUTimer;
import org.junit.Assert;
import org.junit.Test;
/**
 * 
 *  
 * @author rainer prosi
 * @date Feb 23, 2011
 */
public class JDFXYPairTest extends TestCase
{
	/**
	 * 
	 *  
	 * @throws Exception
	 */
	@Test
	public final void testSetString() throws Exception
	{
		JDFDoc doc = new JDFDoc("JDF");
		JDFNode n = doc.getJDFRoot();

		JDFXYPair xy = new JDFXYPair("1 2");
		n.setAttribute("test", xy, null);

		xy = new JDFXYPair("1.1 2.2");
		n.setAttribute("test2", xy, null);
		Assert.assertEquals("double double", n.getAttribute("test2", null, ""), "1.1 2.2");

		try
		{
			new JDFXYPair("1 2 3");
		}
		catch (DataFormatException dfe)
		{
			Assert.assertTrue("exception 123 caught", true);
		}
		try
		{
			new JDFXYPair((String) null);
		}
		catch (DataFormatException dfe)
		{
			Assert.assertTrue("exception null caught", true);
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
		Assert.assertEquals(xy.getX(), 4, 0.0);
		Assert.assertEquals(xy.getY(), 0, 0.0);

		xy = new JDFXYPair("4/1");
		Assert.assertEquals(xy.getX(), 4, 0.0);
		Assert.assertEquals(xy.getY(), 1, 0.0);
		xy = new JDFXYPair(" 4 / 1 ");
		Assert.assertEquals(xy.getX(), 4, 0.0);
		Assert.assertEquals(xy.getY(), 1, 0.0);
	}

	/**
	 * 
	 *  
	 * @throws Exception
	 */
	@Test
	public final void testIsPortrait() throws Exception
	{
		JDFXYPair xy = new JDFXYPair("4 5");
		Assert.assertTrue(xy.isPortrait());
		xy.setY(4);
		Assert.assertFalse(xy.isPortrait());
	}

	/**
	 * 
	 *  
	 * @throws Exception
	 */
	@Test
	public final void testSwapXY() throws Exception
	{
		JDFXYPair xy = new JDFXYPair("4 5");
		JDFXYPair yx = new JDFXYPair("5 4");
		Assert.assertNotSame(xy, yx);
		yx.swapXY();
		Assert.assertEquals(xy, yx);
	}

	/**
	 * 
	 * 
	 */
	@Test
	public final void testIsGreaterOrEqual()
	{
		JDFXYPair xy = new JDFXYPair();

		JDFXYPair ab = new JDFXYPair(1.0, 2.0);
		xy = new JDFXYPair(1.0 + JDFBaseDataTypes.EPSILON / 2.0, 2.0 + JDFBaseDataTypes.EPSILON / 2.0);

		Assert.assertTrue(ab.equals(xy));
		Assert.assertTrue(ab.isLessOrEqual(xy));
		Assert.assertTrue(ab.isGreaterOrEqual(xy));
	}

	/**
	 *  
	 */
	@Test
	public final void testClone()
	{

		JDFXYPair ab = new JDFXYPair(1.0, 2.0);
		JDFXYPair ac = new JDFXYPair(ab);
		ac.setX(3.0);
		Assert.assertEquals(ab.getX(), 1.0, 0.0);
	}

	/**
	 *  
	 */
	@Test
	public final void testShapeConstruct()
	{

		JDFShape ab = new JDFShape(1.0, 2.0, 3.0);
		JDFXYPair ac = new JDFXYPair(ab);
		Assert.assertEquals(ac, new JDFXYPair(1.0, 2.0));
	}

	/**
	 * 
	 *  
	 */
	@Test
	public final void testIsLessOrEqual()
	{
		JDFXYPair xy = new JDFXYPair();

		JDFXYPair ab = new JDFXYPair(1.0, 2.0);
		xy = new JDFXYPair(1.0 - JDFBaseDataTypes.EPSILON / 2.0, 2.0 - JDFBaseDataTypes.EPSILON / 2.0);

		Assert.assertTrue(ab.equals(xy));
		Assert.assertTrue(ab.isLessOrEqual(xy));
		Assert.assertTrue(ab.isGreaterOrEqual(xy));
	}

	/**
	 * 
	 *  
	 */
	@Test
	public final void testcreateXYPair()
	{
		Assert.assertNull(JDFXYPair.createXYPair(null));
		Assert.assertNull(JDFXYPair.createXYPair(""));
		Assert.assertNull(JDFXYPair.createXYPair("a"));
		Assert.assertNotNull(JDFXYPair.createXYPair("1"));
		Assert.assertNull(JDFXYPair.createXYPair("1 2 a"));
		Assert.assertNotNull(JDFXYPair.createXYPair("1 2"));
		Assert.assertNotNull(JDFXYPair.createXYPair("  1 2 "));
		Assert.assertNotNull(JDFXYPair.createXYPair("  1. 2 "));
		Assert.assertNotNull(JDFXYPair.createXYPair("  1.00 2.00 "));
	}

	/**
	* 
	*  
	*/
	@Test
	public final void testcreateXYPairPerformance()
	{
		CPUTimer t1 = new CPUTimer(true);
		for (int i = 0; i < 100000; i++)
		{
			JDFXYPair p = JDFXYPair.createXYPair("");
			Assert.assertNull(p);
		}
		t1.stop();
		System.out.println("fast: " + t1);

		CPUTimer t2 = new CPUTimer(true);
		for (int i = 0; i < 100000; i++)
		{
			try
			{
				new JDFXYPair("");
				Assert.fail("blurb");
			}
			catch (DataFormatException x)
			{
				//
			}

		}
		t2.stop();
		System.out.println("slow: " + t2);
	}
}
