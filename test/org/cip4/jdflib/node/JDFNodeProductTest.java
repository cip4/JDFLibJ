/**
 * JDFNodeProductTest.java
 * 
 * @author Dietrich Mucha
 *
 * Copyright (C) 2002 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.node;

import junit.framework.TestCase;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.resource.intent.JDFLayoutIntent;

public class JDFNodeProductTest extends TestCase
{
    public void testBugBuild058()
    {
        // get the JDF document root element
        JDFDoc jdfDoc       = new JDFDoc(ElementName.JDF);
        JDFNode productNode    = jdfDoc.getJDFRoot();
        
        productNode.setType(JDFNode.EnumType.Product.getName(), false);

        // Add an intent resource
        JDFLayoutIntent layoutIntent = (JDFLayoutIntent) productNode.appendMatchingResource(
                                        "LayoutIntent", JDFNode.EnumProcessUsage.AnyInput, null);

        // set the type attribute
        JDFResourceLink rli = 
            productNode.getMatchingLink("LayoutIntent", JDFNode.EnumProcessUsage.AnyInput, 0);
            
        boolean bValid = rli.isValid(KElement.EnumValidationLevel.Complete);
        assertTrue (bValid);

        JDFLayoutIntent layoutIntent2 = (JDFLayoutIntent) rli.getTarget();
        bValid = bValid && (layoutIntent2.equals(layoutIntent));
        assertTrue (bValid);
    }

}
