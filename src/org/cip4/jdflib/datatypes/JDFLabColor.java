/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFLabColor.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

/**
 * This class is a representation of a Lab color (JDFLabColor). It is a blank separated list of
 * double values consisting of L, a and b value.
 */
public class JDFLabColor extends JDFNumList
{
    //**************************************** Constructors ****************************************
    /**
     * constructs a Lab color with all values set to 0.0 Double
     */
    public JDFLabColor()
    {
        super(MAX_LAB_COLOR);
    }

    /**
     * constructs a Lab color with all values set via a Vector of Double objects
     *
     * @param v the given vector
     *
     * @throws DataFormatException - if the Vector has not a valid format
     */
    public JDFLabColor(Vector v) throws DataFormatException
    {
        super(v);
    }

    /**
     * constructs a Lab color with all values set via a String
     *
     * @param s the given String
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFLabColor(String s) throws DataFormatException
    {
        super(s);
    }

    /**
     * constructs a Lab color with all values set via a JDFLabColor
     *
     * @param lab the given Lab colors
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFLabColor(JDFLabColor lab) throws DataFormatException
    {
        this(lab.toString());
    }

    /**
     * constructs a Lab color with all values set via a JDFNumberList
     *
     * @param nl the given number list
     *
     * @throws DataFormatException - if the String does not have a valid format
     */
    public JDFLabColor(JDFNumberList nl) throws DataFormatException
    {
        this(nl.toString());
    }

    /**
     * constructs a new Lab color with the given double values
     *
     * @param l the value L
     * @param a the value a
     * @param b the value b
     */
    public JDFLabColor(double l, double a, double b)
    {
        super(MAX_LAB_COLOR);
        getnumList().set(0, new Double(l));
        getnumList().set(1, new Double(a));
        getnumList().set(2, new Double(b));
    }

    //**************************************** Methods *********************************************
    /**
     * isValid - true if the size of the vector is 3 and all objects are Double types
     *
     * @throws DataFormatException - if the Vector has not a valid format
     */
    public void isValid() throws DataFormatException
    {
        if (getnumList().size() != MAX_LAB_COLOR)
        {
            throw new DataFormatException("Data format exception!");
        }

        for (int i = 0; i < getnumList().size(); i++)
        {
            if (!(getnumList().elementAt(i) instanceof Double))
            {
                throw new DataFormatException("Data format exception!");
            }
        }
    }

    /**
     * getL - returns the value L of the Lab color
     *
     * @return double - the value L of the Lab color
     */
    public double getL()
    {
        return ((Double)getnumList().elementAt(0)).doubleValue();
    }

    /**
     * setL - sets the value L of the Lab color
     *
     * @param l the value L of the Lab color
     */
    public void setL(double l)
    {
        getnumList().set(0, new Double(l));
    }

    /**
     * getA - returns the value a of the Lab color
     *
     * @return double - the value a of the Lab color
     */
    public double getA()
    {
        return ((Double)getnumList().elementAt(1)).doubleValue();
    }

    /**
     * setA - sets the value a of the Lab color
     *
     * @param a the value a of the Lab color
     */
    public void setA(double a)
    {
        getnumList().set(1, new Double(a));
    }

    /**
     * getB - returns the value b of the Lab color
     *
     * @return double - the value b of the Lab color
     */
    public double getB()
    {
        return ((Double)getnumList().elementAt(2)).doubleValue();
    }

    /**
     * setB - sets the value b of the Lab color
     *
     * @param b the value b of the Lab color
     */
    public void setB(double b)
    {
        getnumList().set(2, new Double(b));
    }
}