/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFNameRange.java
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.util.HashUtil;

/**
 * This class represents a name range (JDFNameRange). It is a whitespace separated list of 2
 * names separated by a tilde "~", for example "jack ~ john"
 */
public class JDFNameRange extends JDFRange
{
    //**************************************** Attributes ******************************************
    
    private String m_left  = JDFConstants.EMPTYSTRING; // the left value of the range
    private String m_right = JDFConstants.EMPTYSTRING; // the right value of the range

    //**************************************** Constructors ****************************************
    /**
     * constructor
     */
    public JDFNameRange()
    {
        this(JDFConstants.EMPTYSTRING, JDFConstants.EMPTYSTRING);
    }

    /**
     * constructs a JDFNameRange with the given string
     *
     * @param s the given string
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFNameRange(String s)
    {
        isValid(s);
    }

    /**
     * constructs a JDFNameRange with two given strings the left and the right name
     *
     * @param p_left  the given left string
     * @param p_right the given right string
     */
    public JDFNameRange(String p_left, String p_right)
    {
        setLeft(p_left);
        setRight(p_right);
    }

    /**
     * constructs a JDFNameRange with a give JDFNameRange
     *
     * @param JDFNameRange nr
     */
    public JDFNameRange(JDFNameRange nr)
    {
        setLeft(nr.getLeft());
        setRight(nr.getRight());
    }

    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        if (getLeft().equals(getRight()))
        {
            return getRight();
        }
        return getLeft() + " ~ " + getRight();
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
     * inRange - returns true if (left string >= x <= right string), it is a lexicographical compare
     *
     * @param x comparison string
     *
     * @return boolean - true if x in range otherwise false
     */
    public boolean inRange(String x)
    {
        return ((x.compareTo(getLeft()) >= 0) && (x.compareTo(getRight()) <= 0));
    }

    /**
     * equals - returns true if both JDFNameRange are equal otherwise false
     *
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
            
        return this.toString().equals(((JDFNameRange) other).toString());
    }

    /**
     * hashCode complements equals() to fulfill the equals/hashCode contract
     */
    public int hashCode()
    {
        return HashUtil.hashCode(0, this.toString());
    }

    /**
     * getLowerValue - returns the lower value of the range
     *
     * @return String - the lower value of the range
     */
    public String getLowerValue()
    {
        return (getLeft().compareTo(getRight()) >= 0) ? getLeft() : getRight();
    }

    /**
     * getUpperValue - return the upper value of the range
     *
     * @return String - the upper value of the range
     */
    public String getUpperValue()
    {
        return (getLeft().compareTo(getRight()) >= 0) ? getRight() : getLeft();
    }

    /**
     * isValid - validates the given String
     *
     * @param s the given string
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    protected void isValid(String s)
    {
        Vector vs = new Vector();
        StringTokenizer sToken = new StringTokenizer(s, "~");

        while (sToken.hasMoreElements())
        {
            String str = (String) sToken.nextElement();
            vs.addElement(str.trim());
        }

        setLeft((String) vs.elementAt(0));

        if (vs.size() == 2)
        {
            setRight((String) vs.elementAt(1));
        }
        else
        {
            setRight((String) vs.elementAt(0));
        }
    }

    private void setLeft(String left)
    {
        this.m_left = left;
    }

    public String getLeft()
    {
        return m_left;
    }

    private void setRight(String right)
    {
        this.m_right = right;
    }

    public String getRight()
    {
        return m_right;
    }

    public boolean isPartOfRange(JDFRange ra)
    {
        return this.equals(ra);
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
       return inRange((String) other);
    }


}
