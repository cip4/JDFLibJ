/**
 * JDFRectangleRangeTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JDFRectangleRangeTest {

	@Test
	public final void testIsPartOfRange()
	{
		JDFRectangleRange range = new JDFRectangleRange(new JDFRectangle(0, 0,
				2, 2), new JDFRectangle(0, 0, 4, 4));

		Assertions.assertTrue(range
				.isPartOfRange(new JDFRectangleRange(new JDFRectangle(0, 0, 3,
						3), new JDFRectangle(0, 0, 3.5, 3.5))), "inRange falsch");
	}

}
