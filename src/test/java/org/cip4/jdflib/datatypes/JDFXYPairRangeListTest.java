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

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.resource.devicecapability.JDFTerm.EnumTerm;
import org.cip4.jdflib.resource.devicecapability.JDFTest;
import org.cip4.jdflib.resource.devicecapability.JDFXYPairEvaluation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JDFXYPairRangeListTest {
	@Test
	public final void testSetString()
	{
		JDFXYPairRangeList rangeList = new JDFXYPairRangeList();
		try
		{
			rangeList = new JDFXYPairRangeList(
					"0.4 1.9~1.4 2.9 0.4 1.9~2.4 3.8 0.4 1.6");
		} catch (DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}

		Assertions.assertEquals(rangeList.toString(), "0.4 1.9 ~ 1.4 2.9 0.4 1.9 ~ 2.4 3.8 0.4 1.6");
		// rangeList is not empty
		Assertions.assertFalse(rangeList.toString()
				.length() == 0, "Bad Constructor from a given String");
	}

	@Test
	public final void testConstructXYPairRange()
	{
		JDFXYPairRangeList rl = new JDFXYPairRangeList(new JDFXYPairRange(
				new JDFXYPair(2, 4), new JDFXYPair(6, 8)));
		Assertions.assertEquals(rl.toString(), "2 4 ~ 6 8");
	}

	@Test
	public final void testFitsTolerance()
	{
		try
		{
			new JDFXYPairRangeList("0.4 1.9~1.4 2.9 0.4 1.9~2.4 3.8 0.4 1.6");

			JDFDoc d = new JDFDoc(ElementName.TEST);
			JDFTest t = (JDFTest) d.getRoot();
			t.init();
			JDFXYPairEvaluation xyPairEvaluation = (JDFXYPairEvaluation) t
					.appendTerm(EnumTerm.XYPairEvaluation);
			xyPairEvaluation.setTolerance(new JDFXYPair("0 0"));
			Assertions.assertTrue(t.isValid(EnumValidationLevel.Complete));

			Assertions.assertEquals(xyPairEvaluation.getTolerance(), new JDFXYPair("0 0"));
			xyPairEvaluation.setTolerance(new JDFXYPair("0.1 0.1"));
		} catch (DataFormatException dfe)
		{
			Assertions.fail(dfe.toString());
		}
	}
}
