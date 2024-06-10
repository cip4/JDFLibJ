/**
 * JDFShapeRangeListTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.zip.DataFormatException;

import org.junit.jupiter.api.Test;

class JDFShapeRangeListTest
{
	/*
	 * Class under test for void append(JDFShapeRange)
	 */
	@Test
	public final void testAppendJDFShapeRange() throws DataFormatException
	{
		JDFShapeRangeList rangeList = new JDFShapeRangeList();
		rangeList = new JDFShapeRangeList("0 0 3 1 2 3~4 5 6 4 2 3~4 5 6");
		rangeList.append(new JDFShapeRange("7.5 8.5 9.5"));

		assertEquals(rangeList.toString(), "0 0 3 1 2 3 ~ 4 5 6 4 2 3 ~ 4 5 6 7.5 8.5 9.5", "original rangeList wrong:");
		assertTrue(rangeList.size() == 4, "Bad append" + rangeList.toString());

	}

	@Test
	public final void testSetString() throws DataFormatException
	{
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		rangelist = new JDFShapeRangeList("1 2 3 ~ 4 5 6   1.55 3.75 5.85");

		// rangeList is not empty
		assertTrue(rangelist.size() == 2, "Bad setString: " + rangelist.size());
		assertEquals("7.56 8.56 9.56 7.56 8.56 9.56", new JDFShapeRange("7.555 8.555 9.555").getXJDFString(2));
	}

	@Test
	public final void testInRange() throws DataFormatException
	{
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		rangelist = new JDFShapeRangeList("1 2 3 ~ 4 5 6  7 8 9~10 11 12");
		assertTrue(rangelist.inRange(new JDFShape(3, 4, 5)), "Bad setString: ");
		assertTrue(rangelist.inRange(new JDFShape(4, 5, 6)), "Bad setString: ");
		assertFalse(rangelist.inRange(new JDFShape(6, 7, 8)), "Bad setString: ");
		assertFalse(rangelist.inRange(new JDFShape(10, 12, 12)), "Bad setString: ");
	}

	@Test
	public final void testIsPartOfRange() throws DataFormatException
	{
		JDFShapeRangeList rangelist = new JDFShapeRangeList();

		rangelist = new JDFShapeRangeList("1 2 3 ~ 4 5 6  7 8 9~10 11 12");
		assertTrue(rangelist.isPartOfRange(new JDFShapeRange(new JDFShape(3, 4, 5), new JDFShape(4, 5, 6))), "Bad setString: ");
		assertTrue(rangelist.isPartOfRange(new JDFShapeRange(new JDFShape(9, 9, 9), new JDFShape(10, 10, 10))), "Bad setString: ");
		assertFalse(rangelist.isPartOfRange(new JDFShapeRange(new JDFShape(9, 9, 9), new JDFShape(12, 12, 12))), "Bad setString: ");
		assertFalse(rangelist.isPartOfRange(new JDFShapeRange(new JDFShape(4, 5, 6), new JDFShape(7, 8, 9))), "Bad setString: ");
	}

	@Test
	public final void testIsList_False() throws DataFormatException
	{
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		rangelist = new JDFShapeRangeList("0 0 4");
		rangelist.append(new JDFShapeRange(new JDFShape("0 0 5"), new JDFShape("0 0 6")));
		rangelist.append(new JDFShape("0 0 6"));
		rangelist.append(new JDFShape("0 0 7"));
		assertFalse(rangelist.isList(), "Bad isList");
	}

	@Test
	public final void testIsUnique_False() throws DataFormatException
	{
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		rangelist = new JDFShapeRangeList("0 0 4");
		rangelist.append(new JDFShapeRange(new JDFShape("0 0 5"), new JDFShape("0 0 6")));
		rangelist.append(new JDFShape("0 0 5.5")); // in "0 0 5 ~ 0 0 6"
		rangelist.append(new JDFShape("0 0 7"));

		assertFalse(rangelist.isUnique(), "Bad isUnique");
	}

	@Test
	public final void testIsOrdered_False() throws DataFormatException
	{
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		rangelist = new JDFShapeRangeList("0 0 4");
		rangelist.append(new JDFShape("0 0 5"));
		rangelist.append(new JDFShapeRange(new JDFShape("0 0 5.5"), new JDFShape("0 0 6")));
		rangelist.append(new JDFShape("0 0 5"));
		rangelist.append(new JDFShape("0 0 7"));

		assertFalse(rangelist.isOrdered(), "Bad isOrdered");
	}

	@Test
	public final void testIsOrdered_Reverse_True() throws DataFormatException
	{
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		rangelist = new JDFShapeRangeList("0 0 7");
		rangelist.append(new JDFShape(0, 0, 5));
		rangelist.append(new JDFShapeRange("0 0 4~0 0 2"));

		assertTrue(rangelist.isOrdered(), "Bad isOrdered");
	}

	@Test
	public final void testIsUniqueOrdered_Reverse_False() throws DataFormatException
	{
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		rangelist = new JDFShapeRangeList("0 0 7");
		rangelist.append(new JDFShape("0 0 5"));
		rangelist.append(new JDFShapeRange("0 0 4~0 0 2"));
		rangelist.append(new JDFShape("0 0 5"));

		assertFalse(rangelist.isUniqueOrdered(), "Bad isUniqueOrdered");
	}

	@Test
	public final void testIsUniqueOrdered_True() throws DataFormatException
	{
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		rangelist = new JDFShapeRangeList("0 0 4");
		rangelist.append(new JDFShape("0 0 5"));
		rangelist.append(new JDFShapeRange(new JDFShape("0 0 5.5"), new JDFShape("0 0 6")));
		// rangelist.append(new JDFShape("0 0 5"));
		rangelist.append(new JDFShape("0 0 7"));

		assertTrue(rangelist.isUniqueOrdered(), "Bad isUniqueOrdered");
	}
}
