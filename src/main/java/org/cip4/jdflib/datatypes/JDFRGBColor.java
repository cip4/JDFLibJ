/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
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

import org.cip4.jdflib.util.StringUtil;

/**
 * This class is a representation of a RGB color (JDFRGBColor). It is a blank separated list of double values consisting of R the red color, G the green color and B the blue color value.
 */
public class JDFRGBColor extends JDFNumList
{
	private static final double BIT_255 = 255.;
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final Object RGB_BLACK = new JDFRGBColor(0, 0, 0);

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
	 * @deprecated use typesafe constructors
	 */
	@Deprecated
	public JDFRGBColor(final Vector v) throws DataFormatException
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
	public JDFRGBColor(final String s) throws DataFormatException
	{
		super(s);
	}

	/**
	 * factory for JDFXYPair that silently returns null in case of illegal strings
	 *
	 * @param s the string to parse
	 * @return the JDFXYPair, null if s is not compatible
	 */
	public static JDFRGBColor createRGBColor(final String s)
	{
		if (s != null && s.length() >= 5)
		{
			try
			{
				return new JDFRGBColor(s);
			}
			catch (final DataFormatException x)
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * constructs a RGB color with all values set via a JDFNumberList
	 *
	 * @param nl the given number list
	 *
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFRGBColor(final JDFRGBColor nl)
	{
		super();
		addAll(nl);
	}

	/**
	 * constructs a new RGB color with the given double values
	 *
	 * @param r the color red
	 * @param g the color green
	 * @param b the color blue
	 */
	public JDFRGBColor(final double r, final double g, final double b)
	{
		super(MAX_RGB_COLOR);
		set(0, r);
		set(1, g);
		set(2, b);
	}

	/**
	 * constructs a new RGB color with the given double values
	 *
	 * @param r the color red
	 * @param g the color green
	 * @param b the color blue
	 */
	public JDFRGBColor(final int r, final int g, final int b)
	{
		super(MAX_RGB_COLOR);
		set(0, r / BIT_255);
		set(1, g / BIT_255);
		set(2, b / BIT_255);
	}

	/**
	 * @param rgbArray 0-1 r,g,b
	 */
	public JDFRGBColor(final double[] rgbArray)
	{
		this(rgbArray[0], rgbArray[1], rgbArray[2]);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * isValid - true if the size of the vector is 3 and all instances are Double types
	 *
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	@Override
	public boolean isValid() throws DataFormatException
	{
		if (size() != MAX_RGB_COLOR)
		{
			throw new DataFormatException("wrong size:" + size());
		}

		for (final Object o : this)
		{
			if (!(o instanceof Double))
			{
				throw new DataFormatException("Data format exception!");
			}
		}
		return true;
	}

	/**
	 * getR - returns the red color
	 *
	 * @return double - the red color
	 */
	public double getR()
	{
		return doubleAt(0);
	}

	/**
	 * setR - sets the red color
	 *
	 * @param red the red color
	 */
	public void setR(final double red)
	{
		set(0, red);
	}

	/**
	 * setR - sets the red color
	 *
	 * @param red the red color
	 */
	public void setR255(final int red)
	{
		set(0, red / BIT_255);
	}

	/**
	 * getG - returns the green color
	 *
	 * @return double - the green color
	 */
	public double getG()
	{
		return doubleAt(1);
	}

	/**
	 * setGreen - sets the green color
	 *
	 * @param green the green color
	 */
	public void setG(final double green)
	{
		set(1, green);
	}

	/**
	 * setGreen - sets the green color
	 *
	 * @param green the green color
	 */
	public void setG255(final int green)
	{
		set(1, green / BIT_255);
	}

	/**
	 * getBlue - returns the blue color
	 *
	 * @return double - the blue color
	 */
	public double getB()
	{
		return doubleAt(2);
	}

	/**
	 * getBlue - returns the red color
	 *
	 * @return int - the red color in 0-255
	 */
	public int getR255()
	{
		return (int) (BIT_255 * doubleAt(0));
	}

	/**
	 * getBlue - returns the green color
	 *
	 * @return int - the green color in 0-255
	 */
	public int getG255()
	{
		return (int) (BIT_255 * doubleAt(1));
	}

	/**
	 * getBlue - returns the blue color
	 *
	 * @return int - the blue color in 0-255
	 */
	public int getB255()
	{
		return (int) (BIT_255 * doubleAt(2));
	}

	/**
	 * setBlue - sets the blue color
	 *
	 * @param blue the blue color
	 */
	public void setB(final double blue)
	{
		set(2, blue);
	}

	/**
	 * setBlue - sets the blue color
	 *
	 * @param blue the blue color
	 */
	public void setB255(final int blue)
	{
		set(2, blue / BIT_255);
	}

	/**
	 * get the html color representation of this color in the format 0xrrggbb;
	 *
	 * @return the formatted string
	 */
	public String getHTMLColor()
	{
		final int r = (int) (255.0 * getR());
		final int g = (int) (255.0 * getG());
		final int b = (int) (255.0 * getB());
		return StringUtil.sprintf("#%02x%02x%02x", r + "," + g + "," + b);
	}

	/**
	 * @return the cmyk color that roughly represents this
	 * @See {@link JDFCMYKColor#getRGB()} for the inverse
	 */
	public JDFCMYKColor getCMYK()
	{
		return new JDFCMYKColor(getCMYKArray(getR(), getG(), getB()));
	}

	/**
	 * @param r
	 * @param g
	 * @param b
	 * @return the cmyk color that roughly represents this
	 * @See {@link JDFCMYKColor#getRGB()} for the inverse
	 */
	public static double[] getCMYKArray(final double r, final double g, final double b)
	{
		double k0 = r;
		if (b > k0)
			k0 = b;
		if (g > k0)
			k0 = g;
		final double[] ret = new double[4];
		ret[3] = 1.0 - k0;
		if (k0 > 0)
		{
			ret[0] = (k0 - r) / k0;
			ret[1] = (k0 - g) / k0;
			ret[2] = (k0 - b) / k0;
		}
		else
		{
			ret[0] = ret[1] = ret[2] = 0;
		}
		return ret;
	}

	/**
	 * @param r
	 * @param g
	 * @param b
	 * @return the cmyk color that roughly represents this
	 * @See {@link JDFCMYKColor#getRGB()} for the inverse
	 */
	public static double[] getCMYKArray(final int r, final int g, final int b)
	{
		return getCMYKArray(r / BIT_255, g / BIT_255, b / BIT_255);
	}

}