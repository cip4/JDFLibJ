/*
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
 * @author Elena Skobchenko
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 
 *  
 * @author rainerprosi
 * @date Sep 12, 2012
 */
class JDFNumberRangeListTest {

	/**
	 * 
	 *  
	 */
	@Test
	public final void testSetString()
	{
		JDFNumberRangeList rangeList = new JDFNumberRangeList();
		try
		{
			rangeList = new JDFNumberRangeList("0.4 1.6 ~ 2.7 3.6 ~ 6");
		}
		catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}
		// rangeList is not empty
		Assertions.assertFalse(rangeList.toString().length() == 0, "Bad Constructor from a given String");

	}

	/**
	 * 
	 *  
	 */
	@Test
	public final void testInRange()
	{
		JDFNumberRangeList rangeList = new JDFNumberRangeList();
		try
		{
			rangeList = new JDFNumberRangeList("0.4 1.6 ~ 2.7 3.6 ~ 6 77.5 ~ INF");
		}
		catch (DataFormatException dfe)
		{
			Assertions.fail("DataFormatException");
		}
		Assertions.assertFalse(rangeList.inRange(2.8), "Bad inRange");
		Assertions.assertTrue(rangeList.inRange(0.4), "Bad inRange");
		Assertions.assertTrue(rangeList.inRange(12345.), "Bad inRange");
	}

	/**
	 * test getstring with precision
	 *  
	 */
	@Test
	public final void testGetString()
	{
		JDFNumberRangeList rangeList = new JDFNumberRangeList();
		try
		{
			rangeList = new JDFNumberRangeList("0.44444444 1.666666 ~ 2.77777777 3.66666666 ~ 6 77.555555 ~ INF");
		}
		catch (DataFormatException dfe)
		{
			Assertions.fail("DataFormatException");
		}
		Assertions.assertEquals(rangeList.getString(1), "0.4 1.7 ~ 2.8 3.7 ~ 6 77.6 ~ INF");
		Assertions.assertEquals(rangeList.getString(2), "0.44 1.67 ~ 2.78 3.67 ~ 6 77.56 ~ INF");
	}

	/**
	 * @throws Exception 
	 * 
	 *  
	 */
	@Test
	public final void testInitRange() throws Exception
	{
		JDFNumberRange range = new JDFNumberRange("0.0");
		Assertions.assertEquals(range.getLeft(), 0.0, 0.0);
	}

	/**
	 * 
	 *  
	 */
	@Test
	public final void testIsPartOfRange()
	{
		JDFNumberRangeList rangeList = new JDFNumberRangeList();
		try
		{
			rangeList = new JDFNumberRangeList("0.4 1.6 ~ 2.7 3.6 ~ 6");
		}
		catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}
		Assertions.assertFalse(rangeList.isPartOfRange(new JDFNumberRange(2.8, 3.0)), "Bad isPartOfRange");
		Assertions.assertTrue(rangeList.isPartOfRange(new JDFNumberRange(1.6, 2.7)), "Bad isPartOfRange");
	}

	/**
	 * 
	 *  
	 */
	@Test
	public final void testJDFNumberRangeList_CopyConstructor()
	{
		JDFNumberRangeList rangeList = null;
		JDFNumberRangeList copyRangeList = null;
		try
		{
			rangeList = new JDFNumberRangeList("0 4");
			copyRangeList = new JDFNumberRangeList(rangeList);
			copyRangeList.append(new JDFNumberRange("8~9"));

			Assertions.assertEquals(rangeList.toString(), "0 4", "original rangeList wrong:");
			Assertions.assertEquals(copyRangeList.toString(), "0 4 8 ~ 9", "changed copy of rangeList wrong:");
		}
		catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}
	}

	/**
	 * 
	 *  
	 */
	@Test
	public final void testAppend()
	{
		JDFNumberRangeList rangeList = null;
		JDFNumberRangeList copyRangeList = null;
		try
		{
			rangeList = new JDFNumberRangeList("0 4~5");
			rangeList.append(5.7);
			rangeList.append(6, 6.5);
			rangeList.append(new JDFNumberRange(6.6, 7.7));
			rangeList.append(new JDFNumberRange("8~9"));
			copyRangeList = new JDFNumberRangeList("0 4~5 5.7 6~6.5 6.6~7.7 8~9");
		}
		catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		if (rangeList != null)
		{
			Assertions.assertTrue(rangeList.equals(copyRangeList), "Bad isList");
		}
	}

	/**
	 * 
	 *  
	 */
	@Test
	public final void testIsList()
	{
		JDFNumberRangeList rangeList = null;
		JDFNumberRangeList badRangeList = null;
		try
		{
			rangeList = new JDFNumberRangeList("0 4");
			rangeList.append(6);
			rangeList.append(7);
			badRangeList = new JDFNumberRangeList(rangeList);
			badRangeList.append(new JDFNumberRange("8~9"));
		}
		catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		if (rangeList != null)
			Assertions.assertTrue(rangeList.isList(), "Bad isList");
		if (badRangeList != null)
			Assertions.assertFalse(badRangeList.isList(), "Bad isList");

	}

	/**
	 * 
	 *  
	 */
	@Test
	public final void testIsUnique()
	{
		JDFNumberRangeList rangelist = null;
		try
		{
			rangelist = new JDFNumberRangeList("0 3~4 ");
			rangelist.append(3.5);
		}
		catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		if (rangelist != null)
			Assertions.assertFalse(rangelist.isUnique(), "Bad isUnique");
	}

	/**
	 * 
	 *  
	 */
	@Test
	public final void testIsOrdered_False()
	{
		JDFNumberRangeList rangelist = null;
		try
		{
			rangelist = new JDFNumberRangeList("0~8");
			rangelist.append(5);
			rangelist.append(new JDFNumberRange("5.9~6.9"));
			rangelist.append(7);
		}
		catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		if (rangelist != null)
			Assertions.assertFalse(rangelist.isOrdered(), "Bad isOrdered");
	}

	/**
	 * 
	 *  
	 */
	@Test
	public final void testIsOrdered_Reverse_True()
	{
		JDFNumberRangeList rangelist = null;
		try
		{
			rangelist = new JDFNumberRangeList("21.5 ~ 10.5 6");
			rangelist.append(5);
			rangelist.append(new JDFNumberRange("4~3"));
			rangelist.append(2);
		}
		catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		if (rangelist != null)
			Assertions.assertTrue(rangelist.isOrdered(), "Bad isOrdered");
	}

	/**
	 * 
	 *  
	 */
	@Test
	public final void testIsUniqueOrdered_Reverse_False()
	{
		JDFNumberRangeList rangelist = null;
		try
		{
			rangelist = new JDFNumberRangeList("7 4.5");
			rangelist.append(10);
			rangelist.append(3.5, 2.5);
		}
		catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		if (rangelist != null)
			Assertions.assertFalse(rangelist.isUniqueOrdered(), "Bad isUniqueOrdered");
	}

	/**
	 * 
	 *  
	 */
	@Test
	public final void testIsUniqueOrdered_False()
	{
		JDFNumberRangeList rangelist = null;
		try
		{
			rangelist = new JDFNumberRangeList("0 2.3 5.5");
			rangelist.append(5.5);
			rangelist.append(new JDFNumberRange("6~7.8"));
			rangelist.append(9);
		}
		catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		if (rangelist != null)
			Assertions.assertFalse(rangelist.isUniqueOrdered(), "Bad isUniqueOrdered");
	}

	/**
	 * 
	 *  
	 */
	@Test
	public final void testIsUniqueOrdered_True()
	{
		JDFNumberRangeList rangelist = null;
		try
		{
			rangelist = new JDFNumberRangeList("0 2.3 5.5");
			rangelist.append(6, 7.8);
			rangelist.append(9);
		}
		catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		if (rangelist != null)
			Assertions.assertTrue(rangelist.isUniqueOrdered(), "Bad isUniqueOrdered" + rangelist.toString());
	}

}
