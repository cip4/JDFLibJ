/**
 * FactoryTest.java
 * 
 * @author Dietrich Mucha
 *
 * Copyright (C) 2002 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.core;

import junit.framework.TestCase;

public class FactoryTest extends TestCase
{
    static final String fileSeparator      = System.getProperty("file.separator");
    static final String sm_dirTestData     = "test"+fileSeparator+"data"+fileSeparator;
    

    public void testFactory()
    {
        String strFile = "bookintent.jdf";
        
        JDFParser p = new JDFParser();
        JDFDoc jdfDoc = p.parseFile(sm_dirTestData+strFile);
        
        assertTrue("", jdfDoc != null);
    }

    /*
     * Test for void RemoveAttribute(String, String) - PR-AKMP-000001
     */
    public void testRemoveAttributeStringString()
    {
        JDFParser p = new JDFParser();
        JDFDoc jdfDoc = p.parseFile(sm_dirTestData+"emptyAuthorAttribute.jdf");
        
        JDFElement root = jdfDoc.getJDFRoot();
        KElement kElem  = root.getChildByTagName("Created", "", 0, null, false, true);

        boolean before = kElem.hasAttribute("Author", "", false);
        assertTrue("The Attribute 'Author' does not exist", before);

        if (before)
        {
            kElem.removeAttribute("Author", "");
            boolean after = kElem.hasAttribute("Author", "", false);

            assertFalse("The Attribute 'Author' was not removed", after);
        }
    }
}
