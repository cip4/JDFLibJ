/**
 * JDFRectangleRangeTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;
public class JDFRectangleRangeTest extends TestCase
{

	@Test
	public final void testIsPartOfRange()
	{
		JDFRectangleRange range = new JDFRectangleRange(new JDFRectangle(0, 0,
				2, 2), new JDFRectangle(0, 0, 4, 4));

		Assert.assertTrue("inRange falsch", range
				.isPartOfRange(new JDFRectangleRange(new JDFRectangle(0, 0, 3,
						3), new JDFRectangle(0, 0, 3.5, 3.5))));
	}

}
