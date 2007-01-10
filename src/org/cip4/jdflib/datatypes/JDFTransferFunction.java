/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFTransferFunction.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;

/**
 * This class is a representation of a whitespace separated list of numbers representing a set of
 * XY coordinates of a transfer function. The total number of x y values must be even because of
 * the pairs.
 */
public class JDFTransferFunction extends JDFNumList
{
    //**************************************** Constructors ****************************************
    /**
     * constructs a xy pair with all values set to 0.0 Double
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFTransferFunction() throws DataFormatException
    {
        super(JDFConstants.EMPTYSTRING);
    }

    /**
     * constructs a number list with the given string the number of tokens must be even
     *
     * @param String s - the given String in number list format
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFTransferFunction(String s) throws DataFormatException
    {
        super(s);
    }

    /**
     * constructs a number list with the given vector the number of elements must be even
     *
     * @param Vector p_NumberList - the number list as a vector
     *
     * @throws DataFormatException - if the Vector has not a valid format
     */
    public JDFTransferFunction(Vector v) throws DataFormatException
    {
        super(v);
    }

    /**
     * constructs a number list with the given number list
     *
     * @param JDFNumList nl - the given number list
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFTransferFunction(JDFNumList nl) throws DataFormatException
    {
        super(nl.toString());
    }

    /**
     * constructs a number list with the given transfer function
     *
     * @param JDFTransferFunction tf - the given number list
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFTransferFunction(JDFTransferFunction tf) throws DataFormatException
    {
        super(tf);
    }

    //**************************************** Methods *********************************************
    /**
     * isValid - true if the size of the vector is even and all instances are Double types
     *
     * @throws DataFormatException - if the Vector has not a valid format
     */
    public void isValid() throws DataFormatException
    {
        if ((getnumList().size() % 2) != 0)
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
     * add - adds a xy coordinate to the vector
     *
     * @param JDFXYPair xy - the xy coordinate to add
     */
    public void add(JDFXYPair xy)
    {
        getnumList().add(new Double(xy.getX()));
        getnumList().add(new Double(xy.getY()));
    }

    /**
     * add - adds a x and a y coordinate to the vector
     *
     * @param Double x - the x coordinate to add
     * @param Double y - the y coordinate to add
     */
    public void add(Double x, Double y)
    {
        getnumList().add(x);
        getnumList().add(y);
    }

    /**
     * add - adds a x and a y coordinate to the vector
     *
     * @param double x - the x coordinate to add
     * @param double y - the y coordinate to add
     */
    public void add(double x, double y)
    {
        getnumList().add(new Double(x));
        getnumList().add(new Double(y));
    }

    /**
     * add - adds a x and a y coordinate to the vector
     *
     * @param String s - a string with the x and y coordinate to add
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public void add(String s) throws DataFormatException
    {
        StringTokenizer sToken = new StringTokenizer(s, JDFConstants.BLANK);

        if ((sToken.countTokens() % 2) != 0)
        {
            throw new DataFormatException("Data format exception!");
        }

        while (sToken.hasMoreTokens())
        {
            String t = sToken.nextToken().trim();

            try
            {
                getnumList().addElement(new Double(t));
            }
            catch(NumberFormatException e)
            {
                throw new DataFormatException("Data format exception!");
            }
        }
    }

    /**
     * add - adds a complete transfer function to the vector
     *
     * @param JDFTransferFunction tf - the given transfer function to add
     */
    public void add(JDFTransferFunction tf)
    {
        getnumList().addAll(tf.copyNumList());
    }
}