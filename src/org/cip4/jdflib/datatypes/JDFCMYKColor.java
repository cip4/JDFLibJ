/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFCMYKColor.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

/**
 * This class is a representation of a CMYK color (JDFCMYKColor). It is a blank separated list of
 * double values consisting of C the cyan color, M the magenta color, Y the yellow color and K the
 * black color value.
 */
public class JDFCMYKColor extends JDFNumList
{
    //**************************************** Constructors ****************************************
    /**
     * constructs a CMYK color with all values set to 0.0 Double
     */
    public JDFCMYKColor()
    {
        super(MAX_CMYK_COLOR);
    }

    /**
     * constructs a CMYK color with the given vector
     *
     * @param Vector v - the given vector
     *
     * @throws DataFormatException - if the Vector has not a valid format
     */
    public JDFCMYKColor(Vector v) throws DataFormatException
    {
        super(v);
    }

    /**
     * constructs a CMYK color with the given String
     *
     * @param String s - the given String
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFCMYKColor(String s) throws DataFormatException
    {
        super(s);
    }

    /**
     * constructs a CMYK color with a given JDFCMYKColor
     *
     * @param JDFCMYKColor cmyk - the given CMYK colors
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFCMYKColor(JDFCMYKColor cmyk) throws DataFormatException
    {
        this(cmyk.toString());
    }

    /**
     * constructs a CMYK color with a given JDFNumberList
     *
     * @param JDFNumberList nl - the given number list
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFCMYKColor(JDFNumberList nl) throws DataFormatException
    {
        this(nl.toString());
    }

    /**
     * constructs a new CMYK color with the given double values
     *
     * @param double c - the value c
     * @param double m - the value m
     * @param double y - the value y
     * @param double k - the value k
     */
    public JDFCMYKColor(double c, double m, double y, double k)
    {
        super(MAX_CMYK_COLOR);
        getnumList().set(0, new Double(c));
        getnumList().set(1, new Double(m));
        getnumList().set(2, new Double(y));
        getnumList().set(3, new Double(k));
    }

    //**************************************** Methods *********************************************
    /**
     * isValid - the size of the vector must be 4 and all instances are Double types
     *
     * @throws DataFormatException - if the Vector has not a valid format
     */
    public void isValid() throws DataFormatException
    {
        if (getnumList().size() != MAX_CMYK_COLOR)
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
     * getC - returns the value C of the CMYK color
     *
     * @return double - the value C of the CMYK color
     */
    public double getC()
    {
        return ((Double)getnumList().elementAt(0)).doubleValue();
    }

    /**
     * setC - sets the value C of the CMYK color
     *
     * @param c the value C of the CMYK color
     */
    public void setC(double c)
    {
        getnumList().set(0, new Double(c));
    }

    /**
     * getM - returns the value M of the CMYK color
     *
     * @return double - the value M of the CMYK color
     */
    public double getM()
    {
        return ((Double)getnumList().elementAt(1)).doubleValue();
    }

    /**
     * setM - sets the value M of the CMYK color
     *
     * @param m the value M of the CMYK color
     */
    public void setM(double m)
    {
        getnumList().set(1, new Double(m));
    }

    /**
     * getY - returns the value Y of the CMYK color
     *
     * @return double - the value Y of the CMYK color
     */
    public double getY()
    {
        return ((Double)getnumList().elementAt(2)).doubleValue();
    }

    /**
     * setY - sets the value Y of the CMYK color
     *
     * @param y the value Y of the CMYK color
     */
    public void setY(double y)
    {
        getnumList().set(2, new Double(y));
    }

    /**
     * getK - returns the value K of the CMYK color
     *
     * @return double - the value K of the CMYK color
     */
    public double getK()
    {
        return ((Double)getnumList().elementAt(3)).doubleValue();
    }

    /**
     * setK - sets the value K of the CMYK color
     *
     * @param k the value K of the CMYK color
     */
    public void setK(double k)
    {
        getnumList().set(3, new Double(k));
    }

}