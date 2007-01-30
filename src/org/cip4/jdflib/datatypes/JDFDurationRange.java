/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-20064 The International Cooperation for the Integration of 
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
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 * 
 * @author Elena Skobchenko
 *
 * JDFDurationRange.java
 *
 */

package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.util.JDFDuration;


public class JDFDurationRange extends JDFRange
{
    //**************************************** Attributes ******************************************
    /**
     * first, left element
     */
    private JDFDuration m_left = null;
    
    /**
     * second, right element
     */
    private JDFDuration m_right = null;
    
    //**************************************** Constructors ****************************************
    /**
     * Empty range constructor
     */
    public JDFDurationRange()
    {
        init(new JDFDuration(0), new JDFDuration(0));
    }
    
    /**
     * Constructor - creates a Duration range defined by x
     */
    public JDFDurationRange(JDFDuration x)
    {
        init(x, x);
    }
    
    /**
     * Constructor - creates a Duration range defined by xmin to xmax
     */
    public JDFDurationRange(JDFDuration xmin, JDFDuration xmax)
    {
        init(xmin, xmax);
    }
    
    /**
     * copy constructor
     */
    public JDFDurationRange(JDFDurationRange r)
    {
        init(r.getLeft(), r.getRight());
    }
    
    /**
     * Initialization 
     */
    protected void init(JDFDuration xmin, JDFDuration xmax)
    {
        setLeft(xmin);
        setRight(xmax);
    }
    
    /** 
     * Construct a JDFDurationRange from a string
     * 
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFDurationRange(String s) throws DataFormatException
    {
        String[] strArray = s.split("~");
        if (strArray.length <= 0 || strArray.length > 2)
        {
            throw new DataFormatException("JDFDurationRange illegal string: " + s);
        }
        try
        {
            // the min and the max values are equal
            if (strArray.length == 1)
            {
                m_left = new JDFDuration(strArray[0].trim());
                m_right = new JDFDuration(strArray[0].trim());
            }
            // two different values
            else
            {
                m_left = new JDFDuration(strArray[0].trim());
                m_right = new JDFDuration(strArray[1].trim());
            }
        }
        catch (DataFormatException e)
        {
            throw new DataFormatException("JDFDurationRange illegal string: " + s);
        }
    }
    
    //**************************************** Methods *********************************************
    
     
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        if (m_left.equals(m_right)) 
        {
            return m_left.getDurationISO();
        }
        return m_left.getDurationISO() + " ~ " + m_right.getDurationISO();
    }
    
    
    /**
     * isValid - validate the given String
     *
     * @param s the given string
     *
     * @return boolean - false if the String has not a valid format 
     */
    public boolean isValid(String s)
    {
        try 
        {
            new JDFDurationRange(s);
        }
        catch (DataFormatException e)
        {
            return false;
        }
        return true;
    }
    
    
     
    /**
     * inRange - returns true if 'x' is within the range defined by 'this'
     * 
     * @param x JDFDuration that is to be compared with 'this'
     * @return boolean - true if 'x' is within the range defined by 'this'
     */
    public boolean inRange(JDFDuration x)
    {
        JDFDuration min=this.getLowerValue();
        JDFDuration max=this.getUpperValue();
        return ( ( x.isLonger(min)   || x.equals(min) )  && 
                ( x.isShorter(max) || x.equals(max) ) );
    }
    
    
    /**
     * isPartOfRange - is range 'r' within this range?
     * 
     * @param r the range to test
     * 
     * @return boolean - true if range 'r' is within this range, else false
     */
    public boolean isPartOfRange(JDFRange ra)
    {
        JDFDurationRange r=(JDFDurationRange)ra;
        JDFDuration min=this.getLowerValue();
        JDFDuration r_min=r.getLowerValue();
        JDFDuration max=this.getUpperValue();
        JDFDuration r_max=r.getUpperValue();
        return ( ( r_min.isLonger(min)   || r_min.equals(min) )  && 
                ( r_max.isShorter(max) || r_max.equals(max) ) );
    } 
    
    
    /**
     * getLeft - get the left of the two range deliminators xmin ~ xmax
     * 
     * @return JDFDuration - the left value
     */
    public JDFDuration getLeft()
    {
        return m_left;
    }
    
    /**
     * getRight - get the right of the two range deliminators xmin ~ xmax
     * 
     * @return JDFDuration - the right value
     */
    public JDFDuration getRight()
    {
        return m_right;
    }
    
    /**
     * setLeft - sets the left JDFDuration object of the range
     *
     * @param x the left JDFDuration object of the range
     */
    public void setLeft(JDFDuration x) 
    {
        m_left = x;
    }
    
    /**
     * setRight - sets the right JDFDuration object of the range
     *
     * @param x the right JDFDuration object of the range
     */
    public void setRight(JDFDuration x) 
    {
        m_right = x;
    }
    
    /**
     * getUpperValue - returns the upper value of the bounds
     *
     * @return JDFDuration - the upper value of the range
     */
    public JDFDuration getUpperValue()
    {
        return (m_left.isShorter(m_right)||(m_left.equals(m_right)) ? m_right : m_left);
    }
    
    /**
     * getLowerValue - returns the lower value of the bounds
     *
     * @return JDFDuration - the lower value of the range
     */
    public JDFDuration getLowerValue()
    {
        return (m_left.isShorter(m_right) ? m_left : m_right);
    }

    protected Object getRightObject()
    {
        return m_right;
    }

    protected Object getLeftObject()
    {
        return m_left;
    }
    protected boolean inObjectRange(Object other)
    {
       return inRange((JDFDuration) other);
    }

    
}
