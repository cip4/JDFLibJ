/**
 * JDFNumberRangeListTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import junit.framework.TestCase;


public class JDFNumberRangeListTest extends TestCase
{

    public final void testSetString()
    {
        JDFNumberRangeList rangeList = new JDFNumberRangeList();
        try
        {
            rangeList = new JDFNumberRangeList("0.4 1.6 ~ 2.7 3.6 ~ 6");
        }
        catch (DataFormatException dfe)
        {
            System.out.println(dfe.toString());
        }
        // rangeList is not empty
        assertFalse("Bad Constructor from a given String" , rangeList.toString().length()==0);
         
    }
    
    public final void testInRange()
    {
        JDFNumberRangeList rangeList = new JDFNumberRangeList();
        try
        {
            rangeList = new JDFNumberRangeList("0.4 1.6 ~ 2.7 3.6 ~ 6 77.5 ~ INF");
        }
        catch (DataFormatException dfe)
        {
            fail("DataFormatException");
        }
        assertFalse("Bad inRange", rangeList.inRange(2.8));
        assertTrue("Bad inRange", rangeList.inRange(0.4));
        assertTrue("Bad inRange", rangeList.inRange(12345.));
    }
    
    public final void testIsPartOfRange()
    {
        JDFNumberRangeList rangeList = new JDFNumberRangeList();
        try
        {
            rangeList = new JDFNumberRangeList("0.4 1.6 ~ 2.7 3.6 ~ 6");
        }
        catch (DataFormatException dfe)
        {
            System.out.println(dfe.toString());
        }
        assertFalse("Bad isPartOfRange", rangeList.isPartOfRange(new JDFNumberRange(2.8,3.0)));
        assertTrue("Bad isPartOfRange", rangeList.isPartOfRange(new JDFNumberRange(1.6,2.7)));
    }
    
    public final void testJDFNumberRangeList_CopyConstructor()
    {
        JDFNumberRangeList rangeList=null;
        JDFNumberRangeList copyRangeList=null;
        try {
            rangeList     = new JDFNumberRangeList("0 4");
            copyRangeList = new JDFNumberRangeList(rangeList);
            copyRangeList.append(new JDFNumberRange("8~9"));
            
            assertEquals("original rangeList wrong:", rangeList.toString(), "0 4");
            assertEquals("changed copy of rangeList wrong:", copyRangeList.toString(), "0 4 8 ~ 9");
        }
        catch (DataFormatException dfe) {
            System.out.println(dfe.toString());
        }
    }
    
    public final void testAppend()
    {
        JDFNumberRangeList rangeList=null;
        JDFNumberRangeList copyRangeList=null;
        try {
            rangeList= new JDFNumberRangeList("0 4~5");
            rangeList.append(5.7);
            rangeList.append(6,6.5);
            rangeList.append(new JDFNumberRange(6.6,7.7));
            rangeList.append(new JDFNumberRange("8~9"));
            copyRangeList = new JDFNumberRangeList("0 4~5 5.7 6~6.5 6.6~7.7 8~9");
        }
        catch (DataFormatException dfe) {
            System.out.println(dfe.toString());
        }
        
        if (rangeList != null)
        {
            assertTrue("Bad isList", rangeList.equals(copyRangeList));
        }
    }
    
    
    public final void testIsList()
    {
        JDFNumberRangeList rangeList=null;
        JDFNumberRangeList badRangeList=null;
        try {
            rangeList= new JDFNumberRangeList("0 4");
            rangeList.append(6);
            rangeList.append(7);
            badRangeList = new JDFNumberRangeList(rangeList);
            badRangeList.append(new JDFNumberRange("8~9"));
        }
        catch (DataFormatException dfe) {
            System.out.println(dfe.toString());
        }

        if (rangeList != null)
            assertTrue("Bad isList", rangeList.isList());
        if (badRangeList != null)
            assertFalse("Bad isList", badRangeList.isList());

    }

    public final void testIsUnique()
    {
        JDFNumberRangeList rangelist=null;
        try {
            rangelist= new JDFNumberRangeList("0 3~4 ");
            rangelist.append(3.5);
        }
        catch (DataFormatException dfe) {
            System.out.println(dfe.toString());
        }
        
        if (rangelist != null)
            assertFalse("Bad isUnique", rangelist.isUnique());
    }

    public final void testIsOrdered_False()
    {
        JDFNumberRangeList rangelist=null;
        try {
            rangelist= new JDFNumberRangeList("0~8");
            rangelist.append(5);
            rangelist.append(new JDFNumberRange("5.9~6.9"));
            rangelist.append(7);
        }
        catch (DataFormatException dfe) {
            System.out.println(dfe.toString());
        }
        
        if (rangelist != null)
            assertFalse("Bad isOrdered", rangelist.isOrdered());
    }
    
    public final void testIsOrdered_Reverse_True()
    {
        JDFNumberRangeList rangelist=null;
        try {
            rangelist= new JDFNumberRangeList("21.5 ~ 10.5 6");
            rangelist.append(5);
            rangelist.append(new JDFNumberRange("4~3"));
            rangelist.append(2);
        }
        catch (DataFormatException dfe) {
            System.out.println(dfe.toString());
        }
        
        if (rangelist != null)
            assertTrue("Bad isOrdered", rangelist.isOrdered());
    }

    public final void testIsUniqueOrdered_Reverse_False()
    {
        JDFNumberRangeList rangelist=null;
        try {
            rangelist= new JDFNumberRangeList("7 4.5");
            rangelist.append(10);
            rangelist.append(3.5,2.5);
        }
        catch (DataFormatException dfe) {
            System.out.println(dfe.toString());
        }
        
        if (rangelist != null)
            assertFalse("Bad isUniqueOrdered", rangelist.isUniqueOrdered());
    }
    
    public final void testIsUniqueOrdered_False()
    {
        JDFNumberRangeList rangelist=null;
        try {
            rangelist= new JDFNumberRangeList("0 2.3 5.5");
            rangelist.append(5.5);
            rangelist.append(new JDFNumberRange("6~7.8"));
            rangelist.append(9);
        }
        catch (DataFormatException dfe) {
            System.out.println(dfe.toString());
        }
       
        if (rangelist != null)
            assertFalse("Bad isUniqueOrdered", rangelist.isUniqueOrdered());
    }
    
    public final void testIsUniqueOrdered_True()
    {
        JDFNumberRangeList rangelist=null;
        try {
            rangelist= new JDFNumberRangeList("0 2.3 5.5");
            rangelist.append(6,7.8);
            rangelist.append(9);
        }
        catch (DataFormatException dfe) {
            System.out.println(dfe.toString());
        }
        
        if (rangelist != null)
            assertTrue("Bad isUniqueOrdered"+rangelist.toString(), rangelist.isUniqueOrdered());
    }

}
