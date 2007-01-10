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
 * JDFDurationRangeTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import junit.framework.TestCase;

import org.cip4.jdflib.util.JDFDuration;


public class JDFDurationRangeTest extends TestCase
{

    /*
     * Constructors JDFDurationRange(JDFDuration) and JDFDurationRange(JDFDuration, JDFDuration)
     */
    public final void testJDFDurationRangeJDFDuration()
    {
        JDFDurationRange r = new JDFDurationRange();
        JDFDurationRange r2 = new JDFDurationRange();
        try
        {
            r  = new JDFDurationRange(new JDFDuration("PT5M"), new JDFDuration("PT15M"));
            r2 = new JDFDurationRange(new JDFDuration("PT5M"));
        }
        catch (DataFormatException dfe)
        {
            System.out.println(dfe.toString());
            fail("bad duration");
            
        }
        assertTrue("Bad Constructor ", r.toString().equals("PT5M ~ PT15M"));
        assertTrue("Bad Constructor ", r2.toString().equals("PT5M"));
    }

    /*
     * Class under test for void JDFDurationRange(JDFDurationRange)
     */
    public final void testJDFDurationRangeJDFDurationRange()
    {
        JDFDurationRange r = new JDFDurationRange();
        JDFDurationRange r2 = new JDFDurationRange();
        JDFDurationRange r3 = new JDFDurationRange();
        try
        {
            r = new JDFDurationRange(new JDFDuration("PT5M"), new JDFDuration("PT25M"));
            r2 = new JDFDurationRange(new JDFDuration("PT15M"));
            r3 = new JDFDurationRange(r2);
            r3.setRight(new JDFDuration("PT25M"));
        }
        catch (DataFormatException dfe)
        {
            System.out.println(dfe.toString());
            fail("bad duration");
        }
        assertTrue("Bad Constructor"+r.toString(), r.toString().equals("PT5M ~ PT25M"));
        assertTrue("Bad Constructor"+r2.toString(), r2.toString().equals("PT15M"));
        assertTrue("Bad CopyConstructor"+r3.toString(), r3.toString().equals("PT15M ~ PT25M"));
    }

    
    public final void testJDFDurationRangeString()
    {
        JDFDurationRange r = new JDFDurationRange();
        try
        {
            r = new JDFDurationRange(" PT5M ~ PT15M ");
        }
        catch (DataFormatException dfe)
        {
            System.out.println(dfe.toString());
            fail("bad duration");
        }
        
        assertTrue("Bad Constructor"+r.toString(), r.toString().equals("PT5M ~ PT15M"));
    }
    
    public final void testInRange()
    {
        try
        {
            JDFDurationRangeList rangelist = 
                new JDFDurationRangeList("PT5M ~ PT15M  PT1H5M ~ PT1H15M");

            assertTrue("InRange: ", rangelist.inRange(new JDFDuration("PT15M")));
            assertTrue("InRange: ", rangelist.inRange(new JDFDuration("PT1H15M")));
            assertFalse("NOT InRange: ", rangelist.inRange(new JDFDuration("PT1H25M")));
            assertFalse("NOT InRange: ", rangelist.inRange(new JDFDuration("PT55S")));
        }
        catch (DataFormatException dfe)
        {
            System.out.println(dfe.toString());
            fail("bad duration");
        }
    }

    public final void testJDFDuration()
    {
        JDFDuration d = new JDFDuration();
        JDFDuration d1 = new JDFDuration();
        JDFDuration d2 = new JDFDuration();
        JDFDuration d3 = new JDFDuration();
        
        JDFDuration p1 = new JDFDuration();
        JDFDuration p2 = new JDFDuration();
        JDFDuration p3 = new JDFDuration();
        
        try
        {
            d = new JDFDuration("PT5M");
        }
        catch (DataFormatException dfe)
        {
            System.out.println(dfe.toString());
            fail("bad duration");
        }
        
        try
        {
            d1 = new JDFDuration("PT50M");
        }
        catch (DataFormatException dfe)
        {
            System.out.println(dfe.toString());
            fail("bad duration");
        }
        
        try
        {
            d2 = new JDFDuration("P0T5M");
        }
        catch (DataFormatException dfe)
        {
            assertFalse("invalid duration String",false);
        }
        
        try
        {
            d3 = new JDFDuration("PT5MS");
        }
        catch (DataFormatException dfe)
        {
            assertFalse("invalid duration String",false);
        }
        
        try
        {
            p1 = new JDFDuration("P1Y2M3DT50M");
        }
        catch (DataFormatException dfe)
        {
            System.out.println(dfe.toString());
            fail("bad duration");
        }
        
        try
        {
            p1 = new JDFDuration("P01Y02M03DT50M");
        }
        catch (DataFormatException dfe)
        {
            System.out.println(dfe.toString());
            fail("bad duration");
        }
        
        try
        {
            p2 = new JDFDuration("P01Y02M03D");
        }
        catch (DataFormatException dfe)
        {
            System.out.println(dfe.toString());
            fail("bad duration");
        }
        
        try
        {
            p3 = new JDFDuration("P1Y2M3DT10H30M");
        }
        catch (DataFormatException dfe)
        {
            System.out.println(dfe.toString());
            fail("bad duration");
        }
       
       
        assertTrue("Bad Constructor d", d.getDuration()==300);
        assertTrue("Bad Constructor d1", d1.getDuration()==3000);
        assertTrue("Bad Constructor d2", d2.getDuration()==0);
        assertTrue("Bad Constructor d3", d3.getDuration()==0);
        assertTrue("Bad Constructor p1", p1.getDurationISO().equals("P1Y2M3DT50M"));
        assertTrue("Bad Constructor p2", p2.getDurationISO().equals("P1Y2M3D"));
        assertTrue("Bad Constructor p2", p3.getDurationISO().equals("P1Y2M3DT10H30M"));
        
    }
}
