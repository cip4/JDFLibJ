/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
/**
 * JDFDateTest.java
 * 
 * @author Dietrich Mucha
 *
 * Copyright (C) 2002 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.util;

import java.util.Calendar;
import java.util.zip.DataFormatException;

import junit.framework.TestCase;

public class JDFDateTest extends TestCase
{
    public void testBadDate()
    {
        JDFDate date;
        try
        {
            date = new JDFDate("1999+09-26T11:43:10+03:00");
            fail("date exception: "+date);
        }
        catch(DataFormatException dfe)
        {
//
        }
        try
        {
            date = new JDFDate("1999");
            fail("date exception: "+date);
        }
        catch(DataFormatException dfe)
        {
//
        }
        try
        {
            date = new JDFDate(null);
        }
        catch(DataFormatException dfe)
        {
            fail("date exception: ");
        }
        try
        {
            date = new JDFDate("1975-01-01T20:00:10.5");
            fail("date exception: "+date);
        }
        catch(DataFormatException dfe)
        {
//
        }
        try
        {
            date = new JDFDate("2004-11-26T11:43:10.33-03");
            fail("date exception: "+date);
        }
        catch(DataFormatException dfe)
        {
//
        }
        try
        {
            date = new JDFDate("2004-11-26T11:43:10.-0300");
            fail("date exception: "+date);
        }
        catch(DataFormatException dfe)
        {
//
        }
                
    }
    /**
     * Method testdateTimeISO.
     */
    public void testdateOnly() throws Exception
    {
        JDFDate date = new JDFDate();
        String strDate = date.getDateTimeISO();
        date = new JDFDate("2006-11-26");
        strDate = date.getDateTimeISO();
        assertEquals("Bug "+strDate,strDate,"2006-11-26T00:00:00+00:00");

    }

    /**
     * Method testdateTimeISO.
     */
    public void testdateTimeISO()
    {
        JDFDate date = new JDFDate();
        String strDate = date.getDateTimeISO();
        try
        {
	        // summer
	        date = new JDFDate("1999-09-26T11:43:10+03:00");
	        strDate = date.getDateTimeISO();
	        assertTrue("Bug "+strDate+" is not equal to 1999-09-26T11:43:10+03:00", 
	                   strDate.equals("1999-09-26T11:43:10+03:00"));
	        
	        date = new JDFDate("1999-09-26T11:43:10-03:00");
	        strDate = date.getDateTimeISO();
	        assertTrue("Bug "+strDate+" is not equal to 1999-09-26T11:43:10-03:00", 
	                   strDate.equals("1999-09-26T11:43:10-03:00"));
	
	        // winter
	        date = new JDFDate("1999-11-26T11:43:10+03:00");
	        strDate = date.getDateTimeISO();
	        assertTrue("Bug "+strDate+" is not equal to 1999-11-26T11:43:10+03:00", 
	                   strDate.equals("1999-11-26T11:43:10+03:00"));
	        
            date = new JDFDate("1999-11-26T11:43:10-03:00");
            strDate = date.getDateTimeISO();
            assertTrue("Bug "+strDate+" is not equal to 1999-11-26T11:43:10-03:00", 
                       strDate.equals("1999-11-26T11:43:10-03:00"));
            
            // note the various A,c zulu etc times below
            date = new JDFDate("1999-11-26T11:43:10a");
            strDate = date.getDateTimeISO();
            assertEquals("Bug ",strDate,"1999-11-26T11:43:10+01:00");
            
            date = new JDFDate("1999-11-26T11:43:10C");
            strDate = date.getDateTimeISO();
            assertEquals("Bug ",strDate,"1999-11-26T11:43:10+03:00");
            
            date = new JDFDate("1999-11-26T11:43:10Z");
            strDate = date.getDateTimeISO();
            assertEquals("Bug ", strDate,"1999-11-26T11:43:10+00:00");
            
            date = new JDFDate("1999-11-26T11:43:10i");
            strDate = date.getDateTimeISO();
            assertEquals("Bug ", strDate,"1999-11-26T11:43:10+09:00");

            date = new JDFDate("1999-11-26T11:43:10K");
            strDate = date.getDateTimeISO();
            assertEquals("Bug ",strDate,"1999-11-26T11:43:10+10:00");
            
            date = new JDFDate("1999-11-26T11:43:10M");
            strDate = date.getDateTimeISO();
            assertEquals("Bug ", strDate,"1999-11-26T11:43:10+12:00");
            
            date = new JDFDate("1999-11-26T11:43:10N");
            strDate = date.getDateTimeISO();
            assertEquals("Bug ", strDate,"1999-11-26T11:43:10-01:00");
            
            date = new JDFDate("1999-11-26T11:43:10V");
            strDate = date.getDateTimeISO();
            assertEquals("Bug ", strDate,"1999-11-26T11:43:10-09:00");
            
            date = new JDFDate("1999-11-26T11:43:10W");
            strDate = date.getDateTimeISO();
            assertEquals("Bug ",strDate,"1999-11-26T11:43:10-10:00");
            
            date = new JDFDate("1999-11-26T11:43:10Y");
            strDate = date.getDateTimeISO();
            assertEquals("Bug ",strDate,"1999-11-26T11:43:10-12:00");
            
            date = new JDFDate("1999-11-26T11:43:10.123Y");
            strDate = date.getDateTimeISO();
            assertEquals("Bug ",strDate,"1999-11-26T11:43:10-12:00");

            date = new JDFDate("1999-11-26T11:43:10.12345-03:00");
            strDate = date.getDateTimeISO();
            assertTrue("Bug "+strDate+" is not equal to 1999-11-26T11:43:10-03:00", 
                       strDate.equals("1999-11-26T11:43:10-03:00"));
            
            date = new JDFDate("2004-11-26T11:43:10.-03:00");
            strDate = date.getDateTimeISO();
            assertTrue("Bug "+strDate+" is not equal to 2004-11-26T11:43:10-03:00", 
                       strDate.equals("2004-11-26T11:43:10-03:00"));
            
         }
        catch(DataFormatException dfe)
        {
        	System.out.println(dfe.getMessage());
        	dfe.printStackTrace();
            assertFalse("date exception",true);
        }
    }
    
    //////////////////////////////////////////////////////////
    
    public void testEquals() throws Exception
    {
        JDFDate date1=new JDFDate();
        JDFDate date2=new JDFDate();
        assertEquals(date1, date2);
        assertTrue(date1.equals(date2));
    }    
    //////////////////////////////////////////////////////////
    
    public void testCompare2() throws Exception
    {
        JDFDate date1=new JDFDate();
        JDFDate date2=new JDFDate();
        assertEquals(date1.compareTo(date2), 0);
        date2.addOffset(0,0,0,1); // it is now later
        assertTrue(date1.compareTo(date2)<0);
        assertTrue(date2.compareTo(date1)>0);
    }    
    
   //////////////////////////////////////////////////////////
    
    public void testAddOffset() throws Exception
    {
        JDFDate date1=new JDFDate();
        JDFDate date2=new JDFDate();
        assertEquals(date1.compareTo(date2), 0);
        date2.addOffset(0,0,0,1); // it is now later
        assertEquals(date1.toString().compareTo(date2.toString()), -1);
        date1.addOffset(0,0,24,0); // it is now later
        assertEquals(date1.toString().compareTo(date2.toString()), 0);
        assertEquals(date1.compareTo(date2), 0);
        date2.addOffset(60,0,0,1); // it is now later
        date1.addOffset(0,1,24,0); // it is now later
        assertEquals(date1.compareTo(date2), 0);
        date2.addOffset(0,60,0,1); // it is now later
        date1.addOffset(0,0,25,0); // it is now later
        assertEquals(date1.compareTo(date2), 0);
        
        
        JDFDate date = new JDFDate("2007-09-26T11:43:10+03:00");
        date.addOffset(0,0,0,1); // it is now later
        assertEquals(date.getDateTimeISO(),"2007-09-27T11:43:10+03:00");
        date.addOffset(0,0,0,1); // it is now later
        assertEquals(date.getDateTimeISO(),"2007-09-28T11:43:10+03:00");
        date.addOffset(2,0,0,0); // it is now later
        assertEquals(date.getDateTimeISO(),"2007-09-28T11:43:12+03:00");
        assertEquals(date.getDateISO(),"2007-09-28");
        date.addOffset(0,0,0,4); // it is now later
        assertEquals(date.getDateISO(),"2007-10-02");
        

    }    
    //////////////////////////////////////////////////////////

    public void testMyDateLong() throws Exception
    {
        long timeMilli = 1008745211300L;        // 2001-12-19T07:00:11+00:00
        
        JDFDate date = new JDFDate(timeMilli);
        assertTrue("Bug " + date.getTimeInMillis() + " is not equal to 1008745211000L", date.getTimeInMillis() == timeMilli);

        String strDate = date.getDateTimeISO();
        JDFDate date2 = new JDFDate("2001-12-19T07:00:11.300+00:00");
        
        String strDate2 = date2.getDateTimeISO();
        assertEquals("Bug "+strDate+" is not equal to "+strDate2,date, date2);
       
        JDFDate date3 = new JDFDate("2001-12-19T07:00:11.300-00:00");        
        String strDate3 = date3.getDateTimeISO();
        assertEquals("Bug "+strDate+" is not equal to "+strDate3, date,date3);
        
        JDFDate date4 = new JDFDate("2001-12-19T07:00:11.300Z");
        String strDate4 = date4.getDateTimeISO();
        assertEquals("Bug "+strDate+" is not equal to "+strDate4, date,date4);
    }

    ///////////////////////////////////////////////////////
    
}
