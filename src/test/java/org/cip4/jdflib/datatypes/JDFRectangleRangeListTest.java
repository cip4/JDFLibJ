/**
 * JDFRectangleRangeListTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;
public class JDFRectangleRangeListTest extends TestCase
{

	/*
	 * Class under test for void append
	 */
	@Test
	public final void testAppend()
	{
		JDFRectangleRangeList rangelist = new JDFRectangleRangeList();
		try
		{
			rangelist = new JDFRectangleRangeList("0 0 2 2 ~ 0 0 4 4");
			rangelist.append(new JDFRectangleRange("0 0 4 4 ~ 0 0 6 6"));
		} catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		Assert.assertEquals("0 0 2 2 ~ 0 0 4 4 0 0 4 4 ~ 0 0 6 6", rangelist
				.toString());
		Assert.assertTrue("Bad append" + rangelist.toString(), rangelist.size() == 2);
	}

	@Test
	public final void testSetString()
	{
		JDFRectangleRangeList rangelist = new JDFRectangleRangeList();
		try
		{
			rangelist = new JDFRectangleRangeList("0 0 4 4 ~ 0 0 5 5 0 0 5 5");
		} catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		// rangeList is not empty
		Assert.assertTrue("Bad setString: " + rangelist.size(), rangelist.size() == 2);
	}

	@Test
	public final void testIsList()
	{
		JDFRectangleRangeList rangelist = null;
		try
		{
			rangelist = new JDFRectangleRangeList("0 0 4 4");
			// rangelist.append(new JDFRectangleRange(new
			// JDFRectangle("0 0 5 5"),new JDFRectangle("0 0 6 6")));
			rangelist.append(new JDFRectangle("0 0 6 6"));
			rangelist.append(new JDFRectangle("0 0 7 7"));
		} catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		if (rangelist != null)
			Assert.assertTrue("Bad isList", rangelist.isList());
	}

	@Test
	public final void testIsUnique()
	{
		JDFRectangleRangeList rangelist = null;
		try
		{
			rangelist = new JDFRectangleRangeList("0 0 4 4");
			// rangelist.append(new JDFRectangle("0 0 5 5"));
			rangelist.append(new JDFRectangleRange(new JDFRectangle("0 0 5 5"),
					new JDFRectangle("0 0 6 6")));
			// rangelist.append(new JDFRectangle("0 0 5.5 5.5"));
			rangelist.append(new JDFRectangle("0 0 7 7"));
		} catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		if (rangelist != null)
			Assert.assertTrue("Bad isUnique", rangelist.isUnique());
	}

	@Test
	public final void testIsOrdered_False()
	{
		JDFRectangleRangeList rangelist = null;
		try
		{
			rangelist = new JDFRectangleRangeList("0 0 4 4");
			rangelist.append(new JDFRectangle("0 0 5 5"));
			rangelist.append(new JDFRectangleRange(new JDFRectangle(
					"0 0 5.5 5.5"), new JDFRectangle("0 0 6 6")));
			rangelist.append(new JDFRectangle("0 0 5 5"));
			rangelist.append(new JDFRectangle("0 0 7 7"));
		} catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		if (rangelist != null)
			Assert.assertFalse("Bad isOrdered", rangelist.isOrdered());
	}

	@Test
	public final void testIsOrdered_Reverse_False()
	{
		JDFRectangleRangeList rangelist = null;
		try
		{
			rangelist = new JDFRectangleRangeList("0 0 7 7");
			rangelist.append(new JDFRectangle(0, 0, 5, 5));
			rangelist.append(new JDFRectangleRange("0 0 4 4 ~ 0 0 2 2"));
			rangelist.append(new JDFRectangle(0, 0, 5, 5));
		} catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		if (rangelist != null)
			Assert.assertFalse("Bad isOrdered", rangelist.isOrdered());
	}

	@Test
	public final void testIsUniqueOrdered_Reverse_False()
	{
		JDFRectangleRangeList rangelist = null;
		try
		{
			rangelist = new JDFRectangleRangeList("0 0 7 7");
			rangelist.append(new JDFRectangle("0 0 5 5"));
			rangelist.append(new JDFRectangleRange("0 0 4 4 ~ 0 0 2 2"));
			rangelist.append(new JDFRectangle("0 0 5 5"));
		} catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		if (rangelist != null)
			Assert.assertFalse("Bad isUniqueOrdered", rangelist.isUniqueOrdered());
	}

	@Test
	public final void testIsUniqueOrdered_True()
	{
		JDFRectangleRangeList rangelist = null;
		try
		{
			rangelist = new JDFRectangleRangeList("0 0 4 4");
			rangelist.append(new JDFRectangle("0 0 5 5"));
			rangelist.append(new JDFRectangleRange(new JDFRectangle(
					"0 0 5.5 5.5"), new JDFRectangle("0 0 6 6")));
			// rangelist.append(new JDFRectangle("0 0 5 5"));
			rangelist.append(new JDFRectangle("0 0 7 7"));
		} catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		if (rangelist != null)
			Assert.assertTrue("Bad isUniqueOrdered", rangelist.isUniqueOrdered());
	}

}
