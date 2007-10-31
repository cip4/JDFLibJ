/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFNameRangeList.java
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class is a representation of a name range list (JDFNameRangeList). It is a
 * whitespace separated list of name ranges, for example "anna~berta hans~otto"
 */
public class JDFNameRangeList extends JDFRangeList
{
    
     
    /**
    * constructs an empty JDFNameRangeList
    */
    public JDFNameRangeList()
    {
        super();
    }

    /**
     * constructs a JDFNameRangeList from a given string
     *
     * @param s the given string
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFNameRangeList(String s) throws DataFormatException
    {
        if(s != null && !s.equals(JDFConstants.EMPTYSTRING))
        {
            setString(s);
        }
    }

    /**
     * constructs a JDFNameRangeList from the given JDFNameRangeList 
     *
     * @param rl the given JDFNameRangeList
     */
    public JDFNameRangeList(JDFNameRangeList rl)
    {
        rangeList = new Vector(rl.rangeList);
    }
    

    //**************************************** Methods *********************************************
    
     
    /**
     * setString - parse the string and separate all single ranges
     *
     * @param s the given string
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public void setString(String s) throws DataFormatException
    {
        if (s.indexOf(JDFConstants.TILDE) == 0 || s.lastIndexOf(JDFConstants.TILDE) == (s.length() - 1))
            throw new DataFormatException("JDFNameRangeList::SetString: Illegal string " + s);
        String zappedWS = StringUtil.zappTokenWS(s, "~");
        VString v = StringUtil.tokenize(zappedWS, " \t", false);
        VString vs = new VString(v);
        rangeList.clear();
        for (int i = 0; i < vs.size(); i++)
        {
            String str = vs.elementAt(i);
            JDFNameRange r = new JDFNameRange(str);
            rangeList.addElement(r);
        }
    }

    /**
     * inRange - returns true if the given string is in range with one of the ranges in the range
     * list (<code
     *
     * @param x the given string
     *
     * @return boolean - true if in range, otherwise false
     */
    public boolean inRange(String x)
    {
        for (int i = 0; i < rangeList.size(); i++)
        {
            if (((JDFNameRange)rangeList.elementAt(i)).inRange(x))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * append - appends a name range to the range list
     *
     * @param r the given name range
     */
    public void append(JDFNameRange r)
    {
        rangeList.addElement(r);
    }

    @Override
	public boolean isUniqueOrdered()
    {
         return false;
    }

    @Override
	public boolean isOrdered()
    {
        return false;
    }
    
 

    
}
