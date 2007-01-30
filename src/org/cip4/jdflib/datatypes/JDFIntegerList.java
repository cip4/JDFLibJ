/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFIntegerList.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.util.HashUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class is a representation of an integer list (JDFIntegerList). It is a whitespace separated
 * list of integer values.
 */
public class JDFIntegerList extends JDFNumList
{
    /**
     * constructs an empty range list
     */
    public JDFIntegerList()
    {
        //default super constructor
    }
    
    
    /**
     * constructs an integer list with all values set via a String
     *
     * @param s the given String
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFIntegerList(String s) throws DataFormatException
    {
        super(s);
    }

    /**
     * constructs an integer list with all values set via a Vector of Intger objects
     *
     * @param v the given vector
     *
     * @throws DataFormatException - if the Vector has not a valid format
     */
    public JDFIntegerList(Vector v) throws DataFormatException
    {
        super(v);
    }

    /**
     * constructs an integer list with all values set via a JDFIntegerList
     *
     * @param il the given integer list
     *
     * @throws DataFormatException - if the JDFIntegerList has not a valid format
     */
    public JDFIntegerList(JDFIntegerList il) throws DataFormatException
    {
        this(il.getnumList());
    }
    
    /**
     * constructs an integer list with all values set via an int[]
     *
     * @param iArray - the given integer array
     */
    public JDFIntegerList(int[] iArray)
    {
       super();
       setIntArray(iArray);
    }
    /**
     * constructs an integer list with all values set via an int
     *
     * @param i the given integer 
     */
    public JDFIntegerList(int i)
    {
       super();
       setInt(i);
    }

    //**************************************** Methods *********************************************
    /**
     * isValid - true if all instances are Integer types
     *
     * @throws DataFormatException - if the Vector has not a valid format
     */
    public void isValid() throws DataFormatException
    {
        for (int i = 0; i < getnumList().size(); i++)
        {
            if (!(getnumList().elementAt(i) instanceof Integer))
            {
                throw new DataFormatException("Data format exception!");
            }
        }
    }

    /**
     * equals - returns true if both JDFIntegerList are equal otherwise false
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
            
        return this.toString().equals(((JDFIntegerList) other).toString());
    }
    
    /**
     * hashCode complements equals() to fulfill the equals/hashCode contract
     */
    public int hashCode()
    {
        return HashUtil.hashCode(super.hashCode(), this.toString());
    }
    
    /**
     * addIntegerList - adds an integer list to this integer list
     *
     * @param il the given integer list
     */
    public void addIntegerList(JDFIntegerList il)
    {
        final Vector getnumList = getnumList();
        final int size = il.size();
        for (int i = 0; i < size; i++)
        {
            getnumList.addElement(il.elementAt(i));
        }
    }

    /**
     * add - add an int to the vector
     *
     * @param x the int value
     */
    public void add(int x)
    {
        getnumList().add(new Integer(x));
    }

    /**
     * add - add an Integer object to the vector
     *
     * @param x the Integer object
     */
    public void add(Integer x)
    {
        getnumList().add(x);
    }

    /**
     * add - adds a complete integer list to the vector
     *
     * @param il the given integer list
     */
    public void add(JDFIntegerList il)
    {
        getnumList().addAll(il.copyNumList());
    }

    /**
     * add - adds a integer list string to the existing integer list
     *
     * @param s the given string
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public void add(String s) throws DataFormatException
    {
        StringTokenizer sToken = new StringTokenizer(s, JDFConstants.BLANK);
        final Vector numList = getnumList();

        while (sToken.hasMoreTokens())
        {
            int i=StringUtil.parseInt(sToken.nextToken(),0);

            try
            {
                 numList.addElement(new Integer(i));
            }
            catch (NumberFormatException e)
            {
                throw new DataFormatException("Data format exception!");
            }
        }
    }

    /**
     * getInt - returns the integer at 'pos' from the list.<br>
     * Note: if pos is negative, getInt returns the pos'th integer counting from the end.
     *
     * @param pos index of the integer to get
     *
     * @return int - the pos'th int
     */
    public int getInt(int pos)
    {
        if(pos<0)
            pos=pos+size();
        Integer i=(Integer) elementAt(pos);
        if(i==null)
            throw new JDFException("JDFIntegerList:getInt invalid index");
        return i.intValue();
    }
    
    /**
     * getIntArray - returns this integer list as an int array
     *
     * @return int[] - the int array
     */
    public int[] getIntArray()
    
    {
        final Vector numList = getnumList();
        final int size = numList.size();
        int[] intArray = new int[size];
        
        for (int i = 0; i < size; i++)
        {
            intArray[i] = ((Integer)numList.elementAt(i)).intValue();
        }
        
        return intArray;
    }

    /**
     * setIntArray - sets this integer list to an int array<br>
     * the RangeList is emptied, then the values of iArray are added
     *
     * @param iArray the int array
     */
    public void setIntArray(int[] iArray)
    {
        Vector numList = getnumList();
        numList.clear();
        for(int i=0;i<iArray.length;i++)
            numList.add(new Integer(iArray[i]));
    }

    /**
     * setIntArray - sets this integer list to an int<br>
     * the RangeList is empied, then the single value i is added 
     *
     * @param i the value
     */
    public void setInt(int i)
    {
        Vector numList = getnumList();
        numList.clear();
        numList.add(new Integer(i));
    }
    
}