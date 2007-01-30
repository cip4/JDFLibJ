/*
 * MediaColorTest.java
 * @author Dietrich Mucha
 * 
 * Copyright (C) 2004 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.resource;

import junit.framework.TestCase;

import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.resource.intent.JDFMediaIntent;
import org.cip4.jdflib.span.JDFSpanNamedColor;


public class JDFMediaColorTest extends TestCase
{
    public void testMediaColor()
    {
        // parse input string
        JDFParser p = new JDFParser();
        
        String strNode =
            "<MediaIntent xmlns=\"http://www.CIP4.org/JDFSchema_1_1\" ID=\"MI100000\" Class=\"Intent\" Status=\"Available\">" +
                "<Dimensions DataType=\"XYPairSpan\" Preferred=\"2040.945 2891.339\" Actual=\"2040.945 2891.339\"/>" +
                "<StockBrand DataType=\"StringSpan\" Preferred=\"Nopacoat Scaldia SC488961\"/>" +
                "<MediaColor DataType=\"EnumerationSpan\" Preferred=\"White\" Actual=\"White\"/>" +
                "<Thickness  DataType=\"NumberSpan\" Preferred=\"130.00\" Actual=\"130.00\"/>" +
                "<Weight     DataType=\"NumberSpan\" Preferred=\"115.00\" Actual=\"115.00\"/>" +
                "<Grade      DataType=\"IntegerSpan\" Preferred=\"1\" Actual=\"1\"/>" +
            "</MediaIntent>";

        JDFDoc jdfDoc = p.parseString(strNode);

        // no class cast exception on the following two lines means ok ...
        JDFMediaIntent mediaIntent = (JDFMediaIntent) jdfDoc.getRoot();

        // the java type of MediaColor was changed in JDFLIBJ_2.1.2BLD010 
        // from JDFSpanMediaColor to JDFSpanNamedColor, 
        // so no class cast exception should occur in getMediaColor()
        JDFSpanNamedColor nc=mediaIntent.getMediaColor();
        assertEquals(nc.getPreferred().getName(),"White");
    }
}
