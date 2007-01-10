/*
 * DocumentJDFImplTest.java 
 * 
 * @author Dietrich Mucha
 *
 * Copyright (C) 2004 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.core;

import junit.framework.TestCase;

import org.cip4.jdflib.resource.JDFShapeElement;
import org.cip4.jdflib.resource.process.JDFSurface;
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFSpanHoleType;
import org.cip4.jdflib.span.JDFSpanMethod;
import org.cip4.jdflib.span.JDFSpanShape;
import org.cip4.jdflib.span.JDFSpanSurface;
import org.cip4.jdflib.span.JDFStringSpan;


public class DocumentJDFImplTest extends TestCase
{
    public void testContextSensitiveElementNames()
    {   // HoleType, Method, Shape and Surface are context sensitive elements
        // The type casts below should all succeed
        JDFDoc doc = new JDFDoc(ElementName.JDF);
        KElement rootNode = doc.getRoot();

        JDFStringSpan kelem21 = (JDFStringSpan) rootNode.appendElement("HoleType", null);
        kelem21.setAttribute("Type", "org.cip4.jdflib.span.JDFStringSpan");
        KElement kelem22 = rootNode.appendElement("RingBinding", null);
        JDFSpanHoleType kelem23 = (JDFSpanHoleType) kelem22.appendElement("HoleType", null);
        kelem23.setAttribute("Type", "org.cip4.jdflib.span.JDFSpanHoleType");
        
        JDFNameSpan kelem11 = (JDFNameSpan) rootNode.appendElement("Method", null);
        kelem11.setAttribute("Type", "org.cip4.jdflib.span.JDFNameSpan");
        KElement kelem12 = rootNode.appendElement("InsertingIntent", null);
        JDFSpanMethod kelem13 = (JDFSpanMethod) kelem12.appendElement("Method", null);
        kelem13.setAttribute("Type", "org.cip4.jdflib.span.JDFSpanMethod");
        
        JDFShapeElement kelem4 = (JDFShapeElement) rootNode.appendElement("Shape", null);
        kelem4.setAttribute("Type", "org.cip4.jdflib.resource.JDFShapeElement");
        KElement kelem5 = rootNode.appendElement("BookCase", null);
        JDFSpanShape kelem6 = (JDFSpanShape) kelem5.appendElement("Shape", null);
        kelem6.setAttribute("Type", "org.cip4.jdflib.span.JDFSpanShape");
        
        JDFSurface kelem7 = (JDFSurface) rootNode.appendElement("Surface", null);
        kelem7.setAttribute("Type", "org.cip4.jdflib.resource.process.JDFSurface");
        KElement kelem8 = rootNode.appendElement("LaminatingIntent", null);
        JDFSpanSurface kelem9 = (JDFSpanSurface) kelem8.appendElement("Surface", null);
        kelem9.setAttribute("Type", "org.cip4.jdflib.span.JDFSpanSurface");
        
    }
}

