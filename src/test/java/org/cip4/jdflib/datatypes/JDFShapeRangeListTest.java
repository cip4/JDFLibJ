/**
 * JDFShapeRangeListTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JDFShapeRangeListTest {
	/*
	 * Class under test for void append(JDFShapeRange)
	 */
	@Test
	public final void testAppendJDFShapeRange() {
		JDFShapeRangeList rangeList = new JDFShapeRangeList();
		try {
			rangeList = new JDFShapeRangeList("0 0 3 1 2 3~4 5 6 4 2 3~4 5 6");
			rangeList.append(new JDFShapeRange("7.5 8.5 9.5"));
		} catch (DataFormatException dfe) {
			System.out.println(dfe.toString());
		}

		Assertions.assertEquals(rangeList.toString(), "0 0 3 1 2 3 ~ 4 5 6 4 2 3 ~ 4 5 6 7.5 8.5 9.5", "original rangeList wrong:");
		Assertions.assertTrue(rangeList.size() == 4, "Bad append" + rangeList.toString());
	}

	@Test
	public final void testSetString() {
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		try {
			rangelist = new JDFShapeRangeList("1 2 3 ~ 4 5 6   1.55 3.75 5.85");
		} catch (DataFormatException dfe) {
			System.out.println(dfe.toString());
		}

		// rangeList is not empty
		Assertions.assertTrue(rangelist.size() == 2, "Bad setString: " + rangelist.size());
	}

	@Test
	public final void testInRange() {
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		try {
			rangelist = new JDFShapeRangeList("1 2 3 ~ 4 5 6  7 8 9~10 11 12");
		} catch (DataFormatException dfe) {
			System.out.println(dfe.toString());
		}
		Assertions.assertTrue(rangelist.inRange(new JDFShape(3, 4, 5)), "Bad setString: ");
		Assertions.assertTrue(rangelist.inRange(new JDFShape(4, 5, 6)), "Bad setString: ");
		Assertions.assertFalse(rangelist.inRange(new JDFShape(6, 7, 8)), "Bad setString: ");
		Assertions.assertFalse(rangelist.inRange(new JDFShape(10, 12, 12)), "Bad setString: ");
	}

	@Test
	public final void testIsPartOfRange() {
		JDFShapeRangeList rangelist = new JDFShapeRangeList();

		try {
			rangelist = new JDFShapeRangeList("1 2 3 ~ 4 5 6  7 8 9~10 11 12");
		} catch (DataFormatException dfe) {
			System.out.println(dfe.toString());
		}
		Assertions.assertTrue(rangelist.isPartOfRange(new JDFShapeRange(new JDFShape(3, 4, 5), new JDFShape(4, 5, 6))), "Bad setString: ");
		Assertions.assertTrue(rangelist.isPartOfRange(new JDFShapeRange(new JDFShape(9, 9, 9), new JDFShape(10, 10, 10))), "Bad setString: ");
		Assertions.assertFalse(rangelist.isPartOfRange(new JDFShapeRange(new JDFShape(9, 9, 9), new JDFShape(12, 12, 12))), "Bad setString: ");
		Assertions.assertFalse(rangelist.isPartOfRange(new JDFShapeRange(new JDFShape(4, 5, 6), new JDFShape(7, 8, 9))), "Bad setString: ");
	}

	@Test
	public final void testIsList_False() {
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		try {
			rangelist = new JDFShapeRangeList("0 0 4");
			rangelist.append(new JDFShapeRange(new JDFShape("0 0 5"), new JDFShape("0 0 6")));
			rangelist.append(new JDFShape("0 0 6"));
			rangelist.append(new JDFShape("0 0 7"));
		} catch (DataFormatException dfe) {
			System.out.println(dfe.toString());
		}

		Assertions.assertFalse(rangelist.isList(), "Bad isList");
	}

	@Test
	public final void testIsUnique_False() {
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		try {
			rangelist = new JDFShapeRangeList("0 0 4");
			rangelist.append(new JDFShapeRange(new JDFShape("0 0 5"), new JDFShape("0 0 6")));
			rangelist.append(new JDFShape("0 0 5.5")); // in "0 0 5 ~ 0 0 6"
			rangelist.append(new JDFShape("0 0 7"));
		} catch (DataFormatException dfe) {
			System.out.println(dfe.toString());
		}

		Assertions.assertFalse(rangelist.isUnique(), "Bad isUnique");
	}

	@Test
	public final void testIsOrdered_False() {
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		try {
			rangelist = new JDFShapeRangeList("0 0 4");
			rangelist.append(new JDFShape("0 0 5"));
			rangelist.append(new JDFShapeRange(new JDFShape("0 0 5.5"), new JDFShape("0 0 6")));
			rangelist.append(new JDFShape("0 0 5"));
			rangelist.append(new JDFShape("0 0 7"));
		} catch (DataFormatException dfe) {
			System.out.println(dfe.toString());
		}

		Assertions.assertFalse(rangelist.isOrdered(), "Bad isOrdered");
	}

	@Test
	public final void testIsOrdered_Reverse_True() {
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		try {
			rangelist = new JDFShapeRangeList("0 0 7");
			rangelist.append(new JDFShape(0, 0, 5));
			rangelist.append(new JDFShapeRange("0 0 4~0 0 2"));
		} catch (DataFormatException dfe) {
			System.out.println(dfe.toString());
		}

		Assertions.assertTrue(rangelist.isOrdered(), "Bad isOrdered");
	}

	@Test
	public final void testIsUniqueOrdered_Reverse_False() {
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		try {
			rangelist = new JDFShapeRangeList("0 0 7");
			rangelist.append(new JDFShape("0 0 5"));
			rangelist.append(new JDFShapeRange("0 0 4~0 0 2"));
			rangelist.append(new JDFShape("0 0 5"));
		} catch (DataFormatException dfe) {
			System.out.println(dfe.toString());
		}

		Assertions.assertFalse(rangelist.isUniqueOrdered(), "Bad isUniqueOrdered");
	}

	@Test
	public final void testIsUniqueOrdered_True() {
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		try {
			rangelist = new JDFShapeRangeList("0 0 4");
			rangelist.append(new JDFShape("0 0 5"));
			rangelist.append(new JDFShapeRange(new JDFShape("0 0 5.5"), new JDFShape("0 0 6")));
			// rangelist.append(new JDFShape("0 0 5"));
			rangelist.append(new JDFShape("0 0 7"));
		} catch (DataFormatException dfe) {
			System.out.println(dfe.toString());
		}

		Assertions.assertTrue(rangelist.isUniqueOrdered(), "Bad isUniqueOrdered");
	}
}
