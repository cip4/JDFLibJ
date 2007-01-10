/*
 * @author muchadie
 */
package org.cip4.jdflib.util;

import junit.framework.TestCase;

import org.cip4.jdflib.datatypes.JDFIntegerRange;


public class HashUtilTest extends TestCase
{
    public void testEqualsAndHashCode()
    {
        JDFIntegerRange range1 = new JDFIntegerRange(100,200);
        JDFIntegerRange range2 = new JDFIntegerRange(100,200);
        // different objects with same content should be equal
        assertEquals(range1, range2);
        assertEquals(range2, range1);

        int range1Hash = range1.hashCode();
        int range2Hash = range2.hashCode();
        // the hashCode of equal objects should be equal
        assertEquals(range1Hash, range2Hash);
        
        String str3  = "100 ~ 200";
        int str3Hash = str3.hashCode();
        // different objects can have the same hashCode
        assertEquals(range1Hash, str3Hash);
        
        // objects of different type should be different
        assertNotSame(str3, range1);
        assertNotSame(range1, str3);
    }
}
