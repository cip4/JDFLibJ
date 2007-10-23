/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2004 The International Cooperation for the Integration of 
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
 * JDFXYPairRange.java
 *
 */

package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.util.HashUtil;


/**
 * This class represents a x y pair range (JDFXYPairRange). 
 * It is a whitespace separated list of 2 xy pairs separated 
 * by a tilde "~", for example "1.23 3.24 ~ 2.34 7.12"
 */
public class JDFXYPairRange extends JDFRange
{
    //**************************************** Attributes ******************************************
    private JDFXYPair m_left  = null; // the left value of the range
    private JDFXYPair m_right = null; // the right value of the range
    
    //**************************************** Constructors ****************************************
    /**
     * constructs a xy pair range with all values set to 0.0 Double
     */
    public JDFXYPairRange()
    {
        this(null, null);
    }
    
    /**
     * constructs a xy pair range with both values equal ("from x to x")
     *
     * @param x left/right pair
     */
    public JDFXYPairRange(JDFXYPair x)
    {
        this(x, x);
    }
    
    /**
     * constructs a xy pair range with the given left and right xy pair
     *
     * @param min the given left xy pair
     * @param max the given right xy pair
     */
    public JDFXYPairRange(JDFXYPair min, JDFXYPair max)
    {
        init(min, max);
    }
    
    /**
     * constructs a xy pair range with the given xy pair range
     *
     * @param JDFXYPairRange r - the given xy pair range
     */
    public JDFXYPairRange(JDFXYPairRange r)
    {
        init(r.getLeft(), r.getRight());
    }
    
    /**
     * Initialization
     * @param min
     * @param max 
     */
    protected void init(JDFXYPair min, JDFXYPair max)
    {
        m_left = min;
        m_right = max;
    }
    
    /**
     * constructs a xy pair range with all values set via a string
     *
     * @param s the given string
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFXYPairRange(String s) throws DataFormatException
    {
        String[] strArray = s.split("~");
        if(strArray.length > 2)
        {
            throw new DataFormatException("JDFXYPairRange illegal string: " + s);
        }
        try
        {
            // the min and the max values are equal
            if (strArray.length == 1)
            {
                m_left  = new JDFXYPair(strArray[0].trim());
                m_right = new JDFXYPair(strArray[0].trim());
            }
            //two different values
            else if (strArray.length == 2)
            {
                m_left  = new JDFXYPair(strArray[0].trim());
                m_right = new JDFXYPair(strArray[1].trim());
            }
        }
        catch (DataFormatException e)
        {
            throw new DataFormatException("JDFXYPairRange illegal string: " + s);
        }
    } 
    
    //**************************************** Methods *********************************************
    
    /**
     * toString
     *
     * @return String
     * @deprecated 060418 use toString
     */
    public String getString()
    {
        return toString();
    }
    
    /**
     * getString - returns the range as a String
     *
     * @return String - the range as a String
     */
    public String toString()
    {
        if (m_left.equals(m_right)) 
        {
            return JDFConstants.EMPTYSTRING + getLeft();
        }
        return getLeft().toString() + " ~ " + getRight().toString();
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
            new JDFXYPairRange(s);
        }
        catch (DataFormatException e)
        {
            return false;
        }
        return true;
    }
    
    /**
     * equals - returns true if both JDFXYPaiRanges are equal otherwise false
     *
     * @return boolean - true if equal otherwise false
     */
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (other == null)
        {
            return false;
        }
        if (!other.getClass().equals(getClass()))
        {
            return false;
        }
        
        JDFXYPairRange range = (JDFXYPairRange) other;
        
        return  this.getLeft().equals(range.getLeft()) &&
        this.getRight().equals(range.getRight()); 
    }
    
    /**
     * hashCode complements equals() to fulfill the equals/hashCode contract
     * @return int
     */
    public int hashCode()
    {
        return HashUtil.hashCode(0, this.toString());
    }
    
    /**
     * getLeft - returns the left JDFXYPair object
     *
     * @return JDFXYPair - the left JDFXYPair object
     */
    public JDFXYPair getLeft()
    {
        return m_left;
    }
    
    /**
     * getRight - returns the right JDFXYPair object
     *
     * @return JDFXYPair - the right JDFXYPair object
     */
    public JDFXYPair getRight()
    {
        return m_right;
    }
    
    /**
     * sets the left JDFXYPair object of the range
     *
     * @param xy the left JDFXYPair object of the range
     */
    public void setLeft(JDFXYPair xy) 
    {
        m_left = xy;
    }
    
    /**
     * sets the right JDFXYPair object of the range
     *
     * @param xy the right JDFXYPair object of the range
     */
    public void setRight(JDFXYPair xy) 
    {
        m_right = xy;
    }
    
    /**
     * getLowerXValue - returns the lower x value of the bounds
     * for example 2.9 4.5~6.3 7.9 return 2.9
     *
     * @return double - the lower x value of the range
     */
    public double getLowerXValue()
    {
        return (getLeft().getX() < getRight().getX()) ? getLeft().getX() : getRight().getX();
    }
    
    /**
     * getUpperXValue - return the upper x value of the bounds
     * for example 2.9 4.5~6.3 7.9 return 6.3
     *
     * @return double - the upper x value of the range
     */
    public double getUpperXValue()
    {
        return (getLeft().getX() < getRight().getX()) ? getRight().getX() : getLeft().getX();
    }
    
    /**
     * getLowerYValue - returns the lower y value of the bounds
     * for example 2.9 4.5~6.3 7.9 return 4.5
     *
     * @return double - the lower y value of the range
     */
    public double getLowerYValue()
    {
        return (getLeft().getY() < getRight().getY()) ? getLeft().getY() : getRight().getY();
    }
    
    /**
     * getUpperYValue - return the upper y value of the bounds
     * for example 2.9 4.5~6.3 7.9 return 7.9
     *
     * @return double - the upper y value of the range
     */
    public double getUpperYValue()
    {
        return (getLeft().getY() < getRight().getY()) ? getRight().getY() : getLeft().getY();
    }
    
    
    /**
     * getUpperValue - returns the upper value of the bounds
     *
     * @return JDFXYPair - the upper value of the range
     */
    public JDFXYPair getUpperValue()
    {
        return (m_left.isLessOrEqual(m_right) ? m_right : m_left);
    }
    
    /**
     * getLowerValue - returns the lower value of the bounds
     *
     * @return JDFXYPair - the lower value of the range
     */
    public JDFXYPair getLowerValue()
    {
        return (m_left.isLess(m_right) ? m_left : m_right);
    }
    
    
    /**
     * isEqual - boolean equivalence
     * 
     * @return boolean - true if the ranges are equivalent
     */
    public boolean isEqual(JDFXYPairRange g)
    {
        return (m_left.equals(g.m_left)) && (m_right.equals(g.m_right));
    }
    
    /**
     * inRange - returns true if <code>this</code> contains <code>xypair</code>
     *
     * @param xypair comparison pair
     *
     * @return boolean - true if xy in range
     */
    public boolean inRange(JDFXYPair xypair)
    {
        JDFXYPair min=this.getLowerValue();
        JDFXYPair max=this.getUpperValue();
        return xypair.isGreaterOrEqual(min) && xypair.isLessOrEqual(max);
    }
    
    /**
     * isPartOfRange - is range 'r' within this range?
     * 
     * @param ra the range to test
     * 
     * @return boolean - true if range 'r' is within this range, else false
     */
    public boolean isPartOfRange(JDFRange ra)
    {
        JDFXYPairRange r=(JDFXYPairRange) ra;
        
        JDFXYPair min=this.getLowerValue();
        JDFXYPair r_min=r.getLowerValue();
        JDFXYPair max=this.getUpperValue();
        JDFXYPair r_max=r.getUpperValue();
        return r_min.isGreaterOrEqual(min) && r_max.isLessOrEqual(max);
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
       return inRange((JDFXYPair) other);
    }

    
}