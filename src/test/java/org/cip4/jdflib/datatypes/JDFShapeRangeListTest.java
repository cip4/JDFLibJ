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

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
public class JDFShapeRangeListTest extends TestCase {
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

		Assert.assertEquals("original rangeList wrong:", rangeList.toString(), "0 0 3 1 2 3 ~ 4 5 6 4 2 3 ~ 4 5 6 7.5 8.5 9.5");
		Assert.assertTrue("Bad append" + rangeList.toString(), rangeList.size() == 4);
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
		Assert.assertTrue("Bad setString: " + rangelist.size(), rangelist.size() == 2);
	}

	@Test
	public final void testInRange() {
		JDFShapeRangeList rangelist = new JDFShapeRangeList();
		try {
			rangelist = new JDFShapeRangeList("1 2 3 ~ 4 5 6  7 8 9~10 11 12");
		} catch (DataFormatException dfe) {
			System.out.println(dfe.toString());
		}
		Assert.assertTrue("Bad setString: ", rangelist.inRange(new JDFShape(3, 4, 5)));
		Assert.assertTrue("Bad setString: ", rangelist.inRange(new JDFShape(4, 5, 6)));
		Assert.assertFalse("Bad setString: ", rangelist.inRange(new JDFShape(6, 7, 8)));
		Assert.assertFalse("Bad setString: ", rangelist.inRange(new JDFShape(10, 12, 12)));
	}

	@Test
	public final void testIsPartOfRange() {
		JDFShapeRangeList rangelist = new JDFShapeRangeList();

		try {
			rangelist = new JDFShapeRangeList("1 2 3 ~ 4 5 6  7 8 9~10 11 12");
		} catch (DataFormatException dfe) {
			System.out.println(dfe.toString());
		}
		Assert.assertTrue("Bad setString: ", rangelist.isPartOfRange(new JDFShapeRange(new JDFShape(3, 4, 5), new JDFShape(4, 5, 6))));
		Assert.assertTrue("Bad setString: ", rangelist.isPartOfRange(new JDFShapeRange(new JDFShape(9, 9, 9), new JDFShape(10, 10, 10))));
		Assert.assertFalse("Bad setString: ", rangelist.isPartOfRange(new JDFShapeRange(new JDFShape(9, 9, 9), new JDFShape(12, 12, 12))));
		Assert.assertFalse("Bad setString: ", rangelist.isPartOfRange(new JDFShapeRange(new JDFShape(4, 5, 6), new JDFShape(7, 8, 9))));
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

		Assert.assertFalse("Bad isList", rangelist.isList());
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

		Assert.assertFalse("Bad isUnique", rangelist.isUnique());
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

		Assert.assertFalse("Bad isOrdered", rangelist.isOrdered());
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

		Assert.assertTrue("Bad isOrdered", rangelist.isOrdered());
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

		Assert.assertFalse("Bad isUniqueOrdered", rangelist.isUniqueOrdered());
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

		Assert.assertTrue("Bad isUniqueOrdered", rangelist.isUniqueOrdered());
	}
}
