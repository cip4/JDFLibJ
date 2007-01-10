/*
 *
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


public class JDFIntegerRangeTest extends JDFTestCaseBase
{
    
    public final void testJDFIntegerRangeString() throws Exception
    {
        
        JDFIntegerRange range = new JDFIntegerRange();
        range = new JDFIntegerRange(" 0 ~ 1 ");
         
        // rangeList is not empty
        assertFalse("Bad Constructor from a given String", range.toString().length()==0);
        // must be trasformed into the string "0~1"
        assertEquals("Bad Constructor from a given String",range.toString(),"0 ~ 1"); 
        range = new JDFIntegerRange(" 1 ~ 1 ");
        assertEquals("Bad Constructor from a given String",range.toString(),"1"); 
               
    }  

    ///////////////////////////////////////////////////////////////////
    public final void testAppend()
    {        
        JDFIntegerRange range = new JDFIntegerRange();
        try
        {
            range = new JDFIntegerRange(" 0 ~ 1 ");
        }
        catch (DataFormatException dfe)
        {
            fail("DataFormatException");
        }
        
        // rangeList is not empty
        assertFalse("Bad Constructor from a given String", range.toString().length()==0);
        // must be trasformed into the string "0~1"
        assertEquals("Bad Constructor from a given String",range.toString(),"0 ~ 1"); 
        assertFalse(range.append(4));
        assertEquals("Bad Constructor from a given String",range.toString(),"0 ~ 1"); 
        assertFalse(range.append(-5));
        assertEquals("Bad Constructor from a given String",range.toString(),"0 ~ 1"); 
        assertTrue(range.append(2));
        assertEquals("Bad Constructor from a given String",range.toString(),"0 ~ 2"); 
        assertFalse(range.append(2));
        assertEquals("Bad Constructor from a given String",range.toString(),"0 ~ 2"); 
        assertFalse(range.append(1));
        assertEquals("Bad Constructor from a given String",range.toString(),"0 ~ 2");         
}
    
    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////
    
    public final void testCopyConstructor()
    {        
        JDFIntegerRange range = new JDFIntegerRange(4,-1,8);
        assertTrue(range.inRange(4));
        assertFalse(range.inRange(3));
        assertTrue(range.inRange(7));
        assertFalse(range.inRange(8));
        JDFIntegerRange range2 = new JDFIntegerRange(range);
        assertTrue(range2.inRange(4));
        assertFalse(range2.inRange(3));
        assertTrue(range2.inRange(7));
        assertFalse(range2.inRange(8));
    }
    ///////////////////////////////////////////////////////////////////
    
    public final void testDefaultDef()
    {        
        JDFIntegerRange range;
        try
        {
            range = new JDFIntegerRange("0~-1");
            assertFalse(range.inRange(4));
            JDFIntegerRange.setDefaultDef(Integer.MAX_VALUE);
            range = new JDFIntegerRange("0~-1");
            assertTrue(range.inRange(4));
            JDFIntegerRange.setDefaultDef(0);
            range = new JDFIntegerRange("0~-1");
            assertFalse(range.inRange(4));
         }
        catch (DataFormatException e)
        {
            fail("constructor");
        }
     }
     
}
