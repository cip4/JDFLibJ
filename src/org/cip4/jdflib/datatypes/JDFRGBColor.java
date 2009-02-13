/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2009 The International Cooperation for the Integration of 
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
 * This class is a representation of a RGB color (JDFRGBColor). It is a blank separated list of double values consisting of R the red color, G the green color
 * and B the blue color value.
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
	 * constructs a RGB color with all values set via a JDFRGBColor
	 * 
	 * @param rgb the given rgb colors
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFRGBColor(final JDFRGBColor rgb) throws DataFormatException
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
	public JDFRGBColor(final JDFNumberList nl) throws DataFormatException
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
	public JDFRGBColor(final double r, final double g, final double b)
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
	@Override
	public boolean isValid() throws DataFormatException
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
		return true;
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
	public void setR(final double red)
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
	public void setG(final double green)
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
	public void setB(final double blue)
	{
		m_numList.set(2, new Double(blue));
	}

	/**
	 * get the html color representation of this color in the format 0xrrggbb;
	 * @return the formatted string
	 */
	public String getHTMLColor()
	{
		final int r = (int) (255.0 * getR());
		final int g = (int) (255.0 * getG());
		final int b = (int) (255.0 * getB());
		return StringUtil.sprintf("#%02x%02x%02x", r + "," + g + "," + b);
	}
}