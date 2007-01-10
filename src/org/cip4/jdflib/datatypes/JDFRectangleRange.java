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
 * JDFRectangleRange.java
 *
 */

package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.util.HashUtil;




public class JDFRectangleRange extends JDFRange
{   
    //**************************************** Attributes ******************************************
    
    private JDFRectangle m_left  = null;
    private JDFRectangle m_right = null;
   
    //**************************************** Constructors ****************************************
    /**
     * constructs a xy pair range with all values set to 0.0 Double
     */
    public JDFRectangleRange()
    {
        init(null, null);
    }

    /**
     * constructs a JDFRectangleRange, both values are equal ("from x to x")
     *
     * @param x - the given JDFRectangle
     */
    public JDFRectangleRange(JDFRectangle x)
    {
        init(x, x);
    }
    
    /**
     * constructor a JDFRectangleRange with two JDFRectangle values/bounds ("from xmin to xmax")
     *
     * @param xmin the given min value
     * @param xmax the given max value
     */
    public JDFRectangleRange(JDFRectangle xmin, JDFRectangle xmax)  
    {
        init(xmin, xmax);
    }
    
    /**
     * copy constructor - constructs a JDFRectangleRange with the given JDFRectangleRange
     *
     * @param r the JDFRectangleRange to copy
     */
    public JDFRectangleRange(JDFRectangleRange r)
    {
        init(r.getLeft(), r.getRight());
    }
    
    /**
    * Initialization 
    * @param x left value
    * @param y right value
    */
    protected void init(JDFRectangle x, JDFRectangle y)
    {
        m_left = x;
        m_right = y;
    }
           
    /**
     * Constructs a JDFRectangleRange with the values of the given string
     *
     * @param s the given string representation of the range
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFRectangleRange(String s) throws DataFormatException
    {
         String[] strArray = s.split("~");
         if((strArray.length <= 0) || (strArray.length > 2))
         {
             throw new DataFormatException("JDFRectangleRange illegal string: " + s);
         }
         try
         {
             // the min and the max values are equal
             if (strArray.length == 1)
             {
                 m_left  = new JDFRectangle(strArray[0].trim());
                 m_right = new JDFRectangle(strArray[0].trim());
             }
             //two different values
             else
             {
                 m_left  = new JDFRectangle(strArray[0].trim());
                 m_right = new JDFRectangle(strArray[1].trim());
             }
         }
         catch (DataFormatException e)
         {
             throw new DataFormatException("JDFRectangleRange illegal string: " + s);
         }
    }
    
    //**************************************** Methods *********************************************
    
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
        return getLeft() + " ~ " + getRight();
    }
    
    /**
    * getString
    *
    * @return String
    * @deprecated 060414 use toString
    */
    public String getString()
    {
        return toString();
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
            new JDFRectangleRange(s);
        }
        catch (DataFormatException e)
        {
            return false;
        }
        return true;
    }
           
    /**
     * equals - returns true if both JDFRectangleRanges are equal otherwise false
     *
     *@param other the JDFRectangleRange to compare
     * @return boolean - true if equal, otherwise false
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
            
        JDFRectangleRange range = (JDFRectangleRange) other;
        
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
     * inRange - tests if the given x inside of this range 
     *
     * @param x comparison value
     *
     * @return boolean - true if x in range
     */
    public boolean inRange(JDFRectangle x)
    {
       JDFRectangle min=this.getLowerValue();
       JDFRectangle max=this.getUpperValue();
       return x.isGreaterOrEqual(min) && x.isLessOrEqual(max);
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
        JDFRectangleRange r=(JDFRectangleRange) ra;
        JDFRectangle min=this.getLowerValue();
        JDFRectangle r_min=r.getLowerValue();
        JDFRectangle max=this.getUpperValue();
        JDFRectangle r_max=r.getUpperValue();
        return r_min.isGreaterOrEqual(min) && r_max.isLessOrEqual(max);
    }
    
    
       
    /**
     * getLeft - gets the left JDFRectangle object of the range
     *
     * @return JDFRectangle - the left JDFRectangle object of the range
     */
    public JDFRectangle getLeft()
    {
        return m_left;
    }
    
    /**
    * getRight - gets the right JDFRectangle object of the range
    *
    * @return JDFRectangle - the right JDFRectangle object of the range
    */
    public JDFRectangle getRight()
    {
        return m_right;
    }
    
    /**
    * sets the left JDFRectangle object of the range
    *
    * @param x the left JDFRectangle object of the range
    */
    public void setLeft(JDFRectangle x) 
    {
        m_left = x;
    }

    /**
    * sets the right JDFRectangle object of the range
    *
    * @param JDFRectangle x - the right JDFRectangle object of the range
    */
    public void setRight(JDFRectangle x) 
    {
        m_right = x;
    }
    
    /**
     * getUpperValue - returns the upper value of the bounds
     *
     * @return JDFRectangle - the upper value of the range
     */
    public JDFRectangle getUpperValue()
    {
        return (m_left.isLessOrEqual(m_right) ? m_right : m_left);
    }

    /**
     * getLowerValue - returns the lower value of the bounds
     *
     * @return JDFRectangle - the lower value of the range
     */
    public JDFRectangle getLowerValue()
    {
        return (m_left.isLess(m_right) ? m_left : m_right);
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
       return inRange((JDFRectangle) other);
    }

}
