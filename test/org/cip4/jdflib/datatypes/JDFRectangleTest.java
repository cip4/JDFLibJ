/**
 * JDFRectangleRangeTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import junit.framework.TestCase;


public class JDFRectangleTest extends TestCase {

    public final void testRectangle() throws Exception
    {
        JDFRectangle r1=new JDFRectangle("0 0 1 1");
        JDFRectangle r2=new JDFRectangle("0 0 1 1");
        assertTrue(r1.equals(r2));
        assertTrue(r1.isGreaterOrEqual(r2));
        assertTrue(r1.isLessOrEqual(r2));
        assertFalse(r1.isGreater(r2));
        assertFalse(r1.isLess(r2));
        r2=new JDFRectangle("0 0 1 2");
        assertTrue(r1.isLessOrEqual(r2));
        assertTrue(r1.isLess(r2));
        r2=new JDFRectangle("0 0 1.000000001 1");
        assertTrue(r1.isLessOrEqual(r2));
        assertTrue(r1.equals(r2));

        r2=new JDFRectangle("2 2 3 3");
        assertFalse(r1.equals(r2));
        assertFalse(r1.isGreaterOrEqual(r2));
        assertFalse(r1.isLessOrEqual(r2));
        assertFalse(r1.isGreater(r2));
        assertFalse(r1.isLess(r2));

    }
}
