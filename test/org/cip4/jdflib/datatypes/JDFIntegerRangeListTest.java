/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of 
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of 
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of 
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration 
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software 
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG 
 * copyright (c) 1999-2001, Agfa-Gevaert N.V. 
 *  
 * For more information on The International Cooperation for the 
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *  
 * 
 */
/**
 * JDFIntegerRangeListTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;


public class JDFIntegerRangeListTest extends JDFTestCaseBase
{
    private int defaultDef;

    public final void testJDFIntegerRangeListString() throws Exception
    {

        JDFIntegerRangeList rangeList = new JDFIntegerRangeList();
        rangeList = new JDFIntegerRangeList(" 0 1 ~ 2 3 ~ 6 INF ");
        assertEquals(new JDFIntegerRangeList("   1   "), new JDFIntegerRangeList("1~1"));
        // rangeList is not empty
        assertEquals("Bad Constructor from a given String", rangeList.size(),4);
        // must be transformed into the string "0 1~2 3~6"
        assertEquals("Bad Constructor from a given String",rangeList.toString(),"0 1 ~ 2 3 ~ 6 INF"); 

    }

    public final void testJDFIntegerRangeListXDef() throws Exception
    {
        JDFIntegerRange r = new JDFIntegerRange(1,2);
        JDFIntegerRange r2 = new JDFIntegerRange(3,-1,16); // 16 elements element(-1) = 15, range = 3~15

        assertTrue("Bad construction of ranges: Range:"+ r.toString(),
                r.getElementCount()==2 );
        assertTrue("Bad construction of ranges with setDef: Range:"+ r.toString(),
                r2.getElementCount()==13 );

        JDFIntegerRangeList r3 = new JDFIntegerRangeList(" 1 ~ 2 3 ~ -1 ", 16);
        assertTrue("Bad construction of ranges with setDef: Range:"+ r.toString(),
                r3.getElementCount()==15 );
    }

    /////////////////////////////////////////////////////////////////////////

    public final void testConstruct()
    {
        JDFIntegerRangeList rangeList;
        try
        {
            rangeList = new JDFIntegerRangeList((String) null);
            assertEquals( rangeList.getElementCount(),0);
            rangeList = new JDFIntegerRangeList("");
            assertEquals( rangeList.getElementCount(),0);
        }
        catch (DataFormatException e)
        {
            fail("snafu");
        }
    }
    /////////////////////////////////////////////////////////////////////////

    public final void testNormalize() throws Exception
    {
        JDFIntegerRangeList rangeList = new JDFIntegerRangeList("1 3 5 2 4 6 ~ 22");
        rangeList.normalize(true);
        assertEquals(rangeList.toString(),"1 ~ 22");
        rangeList = new JDFIntegerRangeList("1 3 2 1 4 5 6 ~ 22");
        rangeList.normalize(false);
        assertEquals(rangeList.toString(),"1 3 ~ 1 4 ~ 22");
    }
    /////////////////////////////////////////////////////////////////////////

    public final void testDef()
    {
        JDFIntegerRangeList rangeList = null;
        try
        {
            rangeList = new JDFIntegerRangeList("0 ~-1");
            rangeList.setDef(20);
            assertEquals(rangeList.getElementCount(),20);
        }
        catch (DataFormatException dfe)
        {
            fail("DataFormatException");
        }

        try
        {
            rangeList = new JDFIntegerRangeList("2 ~ -6 -3 -2 -1 ");
            rangeList.setDef(10);

            assertEquals(rangeList.toString(),"2 ~ 4 7 8 9");
            rangeList.normalize(false);
            assertEquals(rangeList.toString(),"2 ~ 4 7 ~ 9");
            rangeList = new JDFIntegerRangeList("1 3 5 2 4 6 ~ 22");
            rangeList.normalize(true);
            assertEquals(rangeList.toString(),"1 ~ 22");
        }
        catch (DataFormatException dfe)
        {
            fail("DataFormatException");
        }
    }
    /////////////////////////////////////////////////////////////////////////

    public final void testGetIntegerList()
    {

        try
        {
            JDFIntegerRangeList rangeList = new JDFIntegerRangeList("0 1~2 3~6 8 ~ 7");
            JDFIntegerList list = rangeList.getIntegerList();
            // list must be equal the string "0 1 2 3 4 5 6"
            assertEquals("Bad getIntegerList: " + list,list.toString(),"0 1 2 3 4 5 6 8 7");

            // now some performance
            for(int i=0;i<1000;i++)
                rangeList.append(i*10, i*10+5);

            int n=0;
            for(int i=0;i<rangeList.getElementCount();i++){
                int j=rangeList.getElement(i);
                n+=j;
            }

            list = rangeList.getIntegerList();
            int m=0;
            for(int i=0;i<list.size();i++){
                int j=((Integer)list.elementAt(i)).intValue();
                m+=j;
            }

            assertEquals(n,m);
        }
        catch (DataFormatException dfe)
        {
            fail("DataFormatException");
        }
    }

    ///////////////////////////////////////////////////////////////////////

    public final void testJDFIntegerRangeList_CopyConstructor()
    {
        JDFIntegerRangeList rangeList = null;
        JDFIntegerRangeList copyRangeList = null;
        try
        {
            rangeList     = new JDFIntegerRangeList("0 4");
            copyRangeList = new JDFIntegerRangeList(rangeList);
            copyRangeList.append(new JDFIntegerRange("8~9"));

            assertEquals("original rangeList wrong:", rangeList.toString(), "0 4");
            assertEquals("changed copy of rangeList wrong:", copyRangeList.toString(), "0 4 8 ~ 9");
        }
        catch (DataFormatException dfe)
        {
            fail("DataFormatException");
        }
    }

    /////////////////////////////////////////////////////////////////////////

    public final void testAppend()
    {
        JDFIntegerRangeList rangeList=null;
        JDFIntegerRangeList copyRangeList=null;
        try {
            rangeList= new JDFIntegerRangeList("0 4~5");
            rangeList.append(6,7);
            rangeList.append(new JDFIntegerRange(7,8));
            rangeList.append(new JDFIntegerRange("8~9"));
            copyRangeList = new JDFIntegerRangeList("0 4~5 6~7 7~8 8~9");
        }
        catch (DataFormatException dfe) {
            fail("DataFormatException");
        }

        if (rangeList != null)
            assertEquals(rangeList,copyRangeList);
    }

    /////////////////////////////////////////////////////////////////////////

    public final void testIsList()
    {
        JDFIntegerRangeList goodRangeList=null;
        JDFIntegerRangeList badRangeList=null;
        try {
            goodRangeList= new JDFIntegerRangeList("0 4");
            goodRangeList.append(6, 6);
            goodRangeList.append(7 ,7);

            assertTrue("Bad isList "+goodRangeList.toString(), goodRangeList.isList());

            badRangeList = new JDFIntegerRangeList(goodRangeList);
            badRangeList.append(new JDFIntegerRange("8~9"));

            assertFalse("Bad isList" + badRangeList.toString(), badRangeList.isList());
        }
        catch (DataFormatException dfe) {
            fail("DataFormatException");
        }

    }

    ////////////////////////////////////////////////////////////////////

    public final void testIsUnique() throws Exception
    {
        JDFIntegerRangeList rangelist= new JDFIntegerRangeList("0 3~5");
        rangelist.append(4);
        assertFalse("Bad isUnique", rangelist.isUnique());
    }

    ///////////////////////////////////////////////////////////////////

    public final void testIsOrdered_False() throws Exception
    {
        JDFIntegerRangeList rangelist = new JDFIntegerRangeList("0~8");
        rangelist.append(5);
        rangelist.append(new JDFIntegerRange("6~7"));
        rangelist.append(9);

        assertFalse("Bad isOrdered", rangelist.isOrdered());
    }

    ///////////////////////////////////////////////////////////////////


    public final void testIsOrdered_Reverse_True() throws Exception
    {
        JDFIntegerRangeList rangelist=new JDFIntegerRangeList("21 ~ 10 6");
        rangelist.append(5);
        rangelist.append(4,3);

        assertTrue("Bad isOrdered", rangelist.isOrdered());
    }

    public final void testIsUniqueOrdered_Reverse_False() throws Exception
    {
        JDFIntegerRangeList rangelist=new JDFIntegerRangeList("7 4");
        rangelist.append(10);
        rangelist.append(3,1);

        assertFalse("Bad isUniqueOrdered", rangelist.isUniqueOrdered());
    }

    public final void testIsUniqueOrdered_False() throws Exception
    {
        JDFIntegerRangeList rangelist= new JDFIntegerRangeList("0 2 4");
        rangelist.append(6);
        rangelist.append(new JDFIntegerRange("6~8"));
        rangelist.append(10);

        assertFalse("Bad isUniqueOrdered", rangelist.isUniqueOrdered());
    }

    public final void testIsUniqueOrdered_True() throws Exception
    {
        JDFIntegerRangeList rangelist= new JDFIntegerRangeList("0 2 5");
        rangelist.append(6,8);
        rangelist.append(10);

        assertTrue("Bad isUniqueOrdered" + rangelist.toString(), rangelist.isUniqueOrdered());
    }

    public void testJDFIntegerRangeList1()
    {
        final JDFIntegerRangeList integerRangeList = new JDFIntegerRangeList();
        for (int i = 0; i <= 10; i++)
        {
            integerRangeList.append(i);
        }

        assertEquals("0 ~ 10", integerRangeList.toString());
    }

    public void testJDFIntegerRangeList2()
    {
        final JDFIntegerRangeList integerRangeList = new JDFIntegerRangeList();
        for (int i = 0; i <= 10; i++)
        {
            if(i != 4 && i != 8)
            {
                integerRangeList.append(i);
            }
        }

        assertEquals("0 ~ 3 5 ~ 7 9 ~ 10", integerRangeList.toString());
    }

    ///////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////

    public final void testDefaultDef() throws Exception
    {        
        JDFIntegerRangeList irl=new JDFIntegerRangeList("-INF ~ 0 5 ~ -1");

        assertTrue("inRange", irl.inRange(-99));
        assertFalse("inRange", irl.inRange(99));
        assertTrue("inRange", irl.inRange(2));

        JDFIntegerRange.setDefaultDef(Integer.MAX_VALUE);
        irl=new JDFIntegerRangeList("-INF ~ 0 5 ~ -1");

        assertFalse("inRange", irl.inRange(-99));
        assertTrue("inRange", irl.inRange(99));
        assertFalse("inRange", irl.inRange(2));            
    }

    public void testInRange() throws Exception
    {
        JDFIntegerRangeList irl=new JDFIntegerRangeList("-INF ~ 0 5 ~ INF");

        assertTrue("inRange", irl.inRange(-99));
        assertTrue("inRange", irl.inRange(99));
        assertFalse("inRange", irl.inRange(2));
    }


    public void testInfiniteList() throws Exception
    {
        final JDFIntegerRangeList integerRangeList = new JDFIntegerRangeList("0~-1",Integer.MAX_VALUE);
        for(int i = 0; i <= 10; i++)
        {
            assertTrue("RangeList 0~-1 should contain "+i, integerRangeList.inRange(i));
        }
    }


    ///////////////////////////////////////////////////////////////////

    public void testgetElementCount() throws Exception
    {
        JDFIntegerRangeList irl=new JDFIntegerRangeList("0 ~ 5");
        assertEquals(6,irl.getElementCount());    
        irl=new JDFIntegerRangeList("0 ~ -1");
        assertEquals(irl.getElementCount(),-1);            
        irl=new JDFIntegerRangeList("0 ~ INF");
        assertTrue(irl.getElementCount()<0);            
        irl=new JDFIntegerRangeList("1 ~ 2 0 ~ INF");
        assertTrue(irl.getElementCount()<0);            
        irl=new JDFIntegerRangeList("1 ~ 2 5");
        assertEquals(irl.getElementCount(),3);  
        
        irl=new JDFIntegerRangeList("1 ~ 2 -2");
        irl.setDef(4);
        assertEquals(irl.getElementCount(),3);            
        irl.setDef(1);
        assertEquals(irl.getElementCount(),-1);            
    }


    ///////////////////////////////////////////////////////////////////

    public void testgetElement() throws Exception
    {
        JDFIntegerRangeList irl=new JDFIntegerRangeList("5 ~ 8");
        assertEquals(6,irl.getElement(1));        
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
     */
    protected void setUp() throws Exception
    {
        // TODO Auto-generated method stub
        super.setUp();
        defaultDef=JDFIntegerRange.getDefaultDef();
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        // TODO Auto-generated method stub
        super.tearDown();
        JDFIntegerRange.setDefaultDef(defaultDef);
    }

    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

}
