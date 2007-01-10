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
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 * 
 * @author Elena Skobchenko
 *
 * JDFNumberRange.java
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.util.HashUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class represents a number range (JDFNumberRange). It is a whitespace separated list of 2
 * double values separated by a tilde "~", for example "1.23 ~ 1.45"
 */
public class JDFNumberRange extends JDFRange
{
    //**************************************** Attributes ******************************************
    private double m_left  = 0; // the left value of the range
    private double m_right = 0; // the right value of the range

    //**************************************** Constructors ****************************************
    /**
     * constructs an empty JDFNumberRange
     */
    public JDFNumberRange()
    {
        init(0, 0);
    }

    /**
     * constructs a JDFNumberRange both values are equal
     *
     * @param double x - the given double
     */
    public JDFNumberRange(double x)
    {
        init(x, x);
    }

    /**
     * constructor a JDFNumberRange with two double values
     *
     * @param double xmin - the given min value
     * @param double xmax - the given max value
     */
    public JDFNumberRange(double xmin, double xmax)
    {
        init(xmin, xmax);
    }

    /**
     * constructs a JDFNumberRange with the given JDFNumberRange
     *
     * @param JDFNumberRange nr
     */
    public JDFNumberRange(JDFNumberRange nr)
    {
        init(nr.getLeft(), nr.getRight());

    }
    
    /**
    * Initialization 
    */
    protected void init(double xmin, double xmax)
    {
        m_left  = xmin;
        m_right = xmax;
    }
        
    /**
    * constructs a JDFNumberRange with the values of the given string
    *
    * @param String s - the given string
    *
    * @throws DataFormatException - if the String has not a valid format
    */
    public JDFNumberRange(String s) throws DataFormatException
    {
        String[] strArray = s.split("~");
        if (strArray.length <= 0 || strArray.length > 2)
        {
            throw new DataFormatException("JDFNumberRange illegal string: " + s);
        }
        try
        {
            // the min and the max values are equal
            if (strArray.length == 1)
            {
                m_left = StringUtil.parseDouble(strArray[0],0.0);
                if(m_left==0.0 && !StringUtil.isInteger(strArray[0]))
                    throw new DataFormatException("JDFIntegerRange illegal string: " + s);
                m_right = m_left;
            }
            // two different values
            else
            {
                m_left = StringUtil.parseDouble(strArray[0],0.0);
                if(m_left==0.0 && !StringUtil.isInteger(strArray[0]))
                    throw new DataFormatException("JDFIntegerRange illegal string: " + s);
                m_right = StringUtil.parseDouble(strArray[1],0.0);
                if(m_right==0.0 && !StringUtil.isInteger(strArray[1]))
                    throw new DataFormatException("JDFIntegerRange illegal string: " + s);
            }
        }
        catch (NumberFormatException e)
        {
            throw new DataFormatException("JDFNumberRange illegal string: " + s);
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
        if (Math.abs(this.getLeft() - this.getRight()) < JDFBaseDataTypes.EPSILON)
        {
            return JDFConstants.EMPTYSTRING + getRightString();
        }
        return getLeftString() + " ~ " + getRightString();
    }

    /**
     * getString - returns the range as a String
     *
     * @return String - the range as a String
     * @deprecated 060418 use toString
     */
    public String getString()
    {
        return toString();
     }

    /**
    * isValid - validate the given String
    *
    * @param String s - the given string
    *
    * @return boolean - false if the String has not a valid format 
    */
    public boolean isValid(String s) 
    {
        try 
        {
            new JDFNumberRange(s);
        }
        catch (DataFormatException e)
        {
            return false;
        }
        return true;
    }
    
    
    /**
     * equals - returns true if both JDFNumberRange are equal otherwise false, the difference must
     * be smaller than EPSILON
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
            
        JDFNumberRange jdfNumberRange = (JDFNumberRange) other;
        
        return (Math.abs(this.getLeft()  - jdfNumberRange.getLeft())  <= EPSILON) &&
               (Math.abs(this.getRight() - jdfNumberRange.getRight()) <= EPSILON);
    }

    /**
     * hashCode complements equals() to fulfill the equals/hashCode contract
     */
    public int hashCode()
    {
        return HashUtil.hashCode(0, this.toString());
    }

    /**
     * returns the string representation of the left value of the range
     *
     * @return  the left value of the range
     */
    public String getLeftString()
    {
    	return StringUtil.formatDouble(getLeft());
    }

    /**
     * getLeft - returns the left value of the range
     *
     * @return double - the left value of the range
     */
    public double getLeft()
    {
        return m_left;
    }

    /**
     * returns the string representation of the left value of the range
     *
     * @return  the left value of the range
     */
    public String getRightString()
    {
    	return StringUtil.formatDouble(getRight());
    }
    /**
     * getRight - returns the right value of the range
     *
     * @return double - the right value of the range
     */
    public double getRight()
    {
        return m_right;
    }

    /**
     * setLeft - sets the left double object
     *
     * @param double x - the left double object
     */
    public void setLeft(double x)
    {
        m_left = x;
    }

    /**
     * setRight - sets the right double object
     *
     * @param double x - the right double object
     */
    public void setRight(double x)
    {
        m_right = x;
    }
    
    /**
     * getLowerValue - returns the lower value of the bounds for example 4.5~6.3 return 4.5,
     * 7.0~5.9 return 5.9
     *
     * @return double - the lower value of the range
     */
    public double getLowerValue()
    {
        return (getLeft() < getRight()) ? getLeft() : getRight();
    }

    /**
     * getUpperValue - return the upper value of the bounds for example 4.5~6.3 return 6.3,
     * 7.0~5.9 return 7.0
     *
     * @return double - the upper value of the range
     */
    public double getUpperValue()
    {
        return (getLeft() < getRight()) ? getRight() : getLeft();
    }

    /**
     * inRange - returns true if (lower value >= x <= upper value)
     *
     * @param double x - comparison value
     *
     * @return boolean - true if x in range
     */
    public boolean inRange(double x)
    {
        return (x >= getLowerValue()) && (x <= getUpperValue());
    }
    
    /**
     * isPartOfRange - is range 'r' within this range?
     * 
     * @param JDFNumberRange r - the range to test
     * 
     * @return boolean - true if range 'r' is within this range, else false
     */
    public boolean isPartOfRange(JDFRange ra)
    {
        JDFNumberRange r=(JDFNumberRange)ra;
        return (r.getLowerValue() >= this.getLowerValue()) && (r.getUpperValue() <= this.getUpperValue());
    }

    protected Object getRightObject()
    {
        return new Double(m_right);
    }

    protected Object getLeftObject()
    {
        return new Double(m_left);
    }
    protected boolean inObjectRange(Object other)
    {
       return inRange(((Double) other).doubleValue());
    }


}