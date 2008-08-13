/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFRGBColor.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

/**
 * This class is a representation of a RGB color (JDFRGBColor). It is a blank separated list of double values consisting
 * of R the red color, G the green color and B the blue color value.
 */
public class JDFRGBColor extends JDFNumList
{
	// **************************************** Constructors
	// ****************************************
	/**
	 * constructs a RGB color with all values set to 0.0 Double
	 */
	public JDFRGBColor()
	{
		super(MAX_RGB_COLOR);
	}

	/**
	 * constructs a RGB color with all values set via a Vector of Double objects
	 * 
	 * @param v Vector of Double
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	public JDFRGBColor(Vector v) throws DataFormatException
	{
		super(v);
	}

	/**
	 * constructs a RGB color with all values set via a String
	 * 
	 * @param s the given String
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFRGBColor(String s) throws DataFormatException
	{
		super(s);
	}

	/**
	 * constructs a RGB color with all values set via a JDFRGBColor
	 * 
	 * @param rgb the given rgb colors
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFRGBColor(JDFRGBColor rgb) throws DataFormatException
	{
		this(rgb.toString());
	}

	/**
	 * constructs a RGB color with all values set via a JDFNumberList
	 * 
	 * @param nl the given number list
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFRGBColor(JDFNumberList nl) throws DataFormatException
	{
		this(nl.toString());
	}

	/**
	 * constructs a new RGB color with the given double values
	 * 
	 * @param r the color red
	 * @param g the color green
	 * @param b the color blue
	 */
	public JDFRGBColor(double r, double g, double b)
	{
		super(MAX_RGB_COLOR);
		m_numList.set(0, new Double(r));
		m_numList.set(1, new Double(g));
		m_numList.set(2, new Double(b));
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * isValid - true if the size of the vector is 3 and all instances are Double types
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	public void isValid() throws DataFormatException
	{
		if (m_numList.size() != MAX_RGB_COLOR)
		{
			throw new DataFormatException("Data format exception!");
		}

		for (int i = 0; i < m_numList.size(); i++)
		{
			if (!(m_numList.elementAt(i) instanceof Double))
			{
				throw new DataFormatException("Data format exception!");
			}
		}
	}

	/**
	 * getR - returns the red color
	 * 
	 * @return double - the red color
	 */
	public double getR()
	{
		return ((Double) m_numList.elementAt(0)).doubleValue();
	}

	/**
	 * setR - sets the red color
	 * 
	 * @param red the red color
	 */
	public void setR(double red)
	{
		m_numList.set(0, new Double(red));
	}

	/**
	 * getG - returns the green color
	 * 
	 * @return double - the green color
	 */
	public double getG()
	{
		return ((Double) m_numList.elementAt(1)).doubleValue();
	}

	/**
	 * setGreen - sets the green color
	 * 
	 * @param green the green color
	 */
	public void setG(double green)
	{
		m_numList.set(1, new Double(green));
	}

	/**
	 * getBlue - returns the blue color
	 * 
	 * @return double - the blue color
	 */
	public double getB()
	{
		return ((Double) m_numList.elementAt(2)).doubleValue();
	}

	/**
	 * setBlue - sets the blue color
	 * 
	 * @param blue the blue color
	 */
	public void setB(double blue)
	{
		m_numList.set(2, new Double(blue));
	}
}