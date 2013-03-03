/**
 * JDFDateTimeRangeTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import junit.framework.TestCase;

import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.junit.Assert;
import org.junit.Test;
public class JDFDateTimeRangeTest extends TestCase {
	/*
	 * Class under test for void JDFDateTimeRange(JDFDateTimeRange)
	 */
	@Test
	public final void testJDFDateTimeRangeJDFDateTimeRange() {
		try {
			JDFDateTimeRange r = new JDFDateTimeRange(new JDFDate("2004-12-01T09:30:00Z"), new JDFDate("2004-12-01T09:35:00Z"));

			JDFDateTimeRange test_r = new JDFDateTimeRange("2004-12-01T09:30:00Z ~ 2004-12-01T09:35:00Z");
			Assert.assertTrue("Bad Constructor: " + r.toString(), r.equals(test_r));

			int duration = (int) ((test_r.getRight().getTimeInMillis() - test_r.getLeft().getTimeInMillis()) / 1000);
			JDFDuration d = new JDFDuration();
			d.setDuration(duration);
			// System.out.println(duration);

			JDFDateTimeRange r2 = new JDFDateTimeRange(new JDFDate("2004-12-01T09:30:00Z"));
			JDFDateTimeRange r3 = new JDFDateTimeRange(r2);
			r3.setRight(r.getRight());

			Assert.assertTrue("Bad Constructor: " + r3.toString(), r3.equals(r));

		} catch (DataFormatException dfe) {
			System.out.println(dfe.toString());
		}
	}

	// @Test
	// public final void testInRange()
	// {
	// JDFDateTimeRangeList rangelist = new JDFDateTimeRangeList();
	// try {
	// rangelist= new JDFDateTimeRangeList("PT5M ~ PT15M PT1H5M ~ PT1H15M");
	//
	// System.out.println(rangelist.toString());
	// Assert.assertTrue("Bad setString: ", rangelist.inRange(new JDFDate("PT15M")));
	// Assert.assertTrue("Bad setString: ", rangelist.inRange(new JDFDate("PT1H15M")));
	// Assert.assertFalse("Bad setString: ", rangelist.inRange(new
	// JDFDate("PT1H25M")));
	// Assert.assertFalse("Bad setString: ", rangelist.inRange(new JDFDate("PT55S")));
	// }
	// catch (DataFormatException dfe) {
	// System.out.println(dfe.toString());
	// }
	//
	// }

	@Test
	public final void testInRange() {
		try {
			JDFDateTimeRangeList rangelist = new JDFDateTimeRangeList("2004-11-30T09:30:00Z ~ 2004-12-05T09:00:00Z");

			// System.out.println(rangelist.toString());
			Assert.assertTrue("Bad setString: ", rangelist.inRange(new JDFDate("2004-12-05T08:30:00Z")));
			Assert.assertTrue("Bad setString: ", rangelist.inRange(new JDFDate("2004-12-05T09:00:00Z")));
			Assert.assertFalse("Bad setString: ", rangelist.inRange(new JDFDate("2004-12-05T09:35:00Z")));
		} catch (DataFormatException dfe) {
			System.out.println(dfe.toString());
		}
	}

}
