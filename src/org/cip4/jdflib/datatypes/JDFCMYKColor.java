/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of 
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
 * JDFCMYKColor.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

/**
 * This class is a representation of a CMYK color (JDFCMYKColor). It is a blank separated list of double values consisting of C the cyan color, M the magenta
 * color, Y the yellow color and K the black color value.
 */
public class JDFCMYKColor extends JDFNumList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// **************************************** Constructors
	// ****************************************
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
	 * @deprecated use typesafe constructors
	 */
	@Deprecated
	public JDFCMYKColor(final Vector v) throws DataFormatException
	{
		super(v);
	}

	/**
	 * constructs a CMYK color with the given String
	 * 
	 * @param s - the given String
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFCMYKColor(final String s) throws DataFormatException
	{
		super(s);
	}

	/**
	 * constructs a CMYK color with a given JDFNumberList
	 * 
	 * @param nl - the given number list
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFCMYKColor(final JDFNumList nl) throws DataFormatException
	{
		super(nl);
	}

	/**
	 * constructs a new CMYK color with the given double values
	 * 
	 * @param c - the value c
	 * @param m - the value m
	 * @param y - the value y
	 * @param k - the value k
	 */
	public JDFCMYKColor(final double c, final double m, final double y, final double k)
	{
		super(MAX_CMYK_COLOR);
		set(0, new Double(c));
		set(1, new Double(m));
		set(2, new Double(y));
		set(3, new Double(k));
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * isValid - the size of the vector must be 4 and all instances are Double types
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	@Override
	public boolean isValid() throws DataFormatException
	{
		if (size() != MAX_CMYK_COLOR)
		{
			throw new DataFormatException("Data format exception!");
		}

		for (Object o : this)
		{
			if (!(o instanceof Double))
			{
				throw new DataFormatException("Data format exception!");
			}
		}
		return true;
	}

	/**
	 * getC - returns the value C of the CMYK color
	 * 
	 * @return double - the value C of the CMYK color
	 */
	public double getC()
	{
		return doubleAt(0);
	}

	/**
	 * setC - sets the value C of the CMYK color
	 * 
	 * @param c the value C of the CMYK color
	 */
	public void setC(final double c)
	{
		set(0, c);
	}

	/**
	 * getM - returns the value M of the CMYK color
	 * 
	 * @return double - the value M of the CMYK color
	 */
	public double getM()
	{
		return doubleAt(1);
	}

	/**
	 * setM - sets the value M of the CMYK color
	 * 
	 * @param m the value M of the CMYK color
	 */
	public void setM(final double m)
	{
		set(1, m);
	}

	/**
	 * getY - returns the value Y of the CMYK color
	 * 
	 * @return double - the value Y of the CMYK color
	 */
	public double getY()
	{
		return doubleAt(2);
	}

	/**
	 * setY - sets the value Y of the CMYK color
	 * 
	 * @param y the value Y of the CMYK color
	 */
	public void setY(final double y)
	{
		set(2, y);
	}

	/**
	 * getK - returns the value K of the CMYK color
	 * 
	 * @return double - the value K of the CMYK color
	 */
	public double getK()
	{
		return doubleAt(3);
	}

	/**
	 * setK - sets the value K of the CMYK color
	 * 
	 * @param k the value K of the CMYK color
	 */
	public void setK(final double k)
	{
		set(3, k);
	}

	/**
	 * @return the rgb color that roughly represents this
	 */
	public JDFRGBColor getRGB()
	{
		final JDFRGBColor rgb = new JDFRGBColor();
		final double colors = 1.0 - getK();
		rgb.setR(colors * (1.0 - getC()));
		rgb.setG(colors * (1.0 - getM()));
		rgb.setB(colors * (1.0 - getY()));
		return rgb;
	}

	/**
	 * set the cmyk value of this to a matching value
	 * @param name
	 */
	public void setNamedColor(final String name)
	{
		for (int i = 0; i < 4; i++)
		{
			set(i, 0.0);
		}
		if ("Black".equalsIgnoreCase(name))
		{
			setK(1.0);
		}
		if ("Cyan".equalsIgnoreCase(name))
		{
			setC(1.0);
		}
		if ("Magenta".equalsIgnoreCase(name))
		{
			setM(1.0);
		}
		if ("Yellow".equalsIgnoreCase(name))
		{
			setY(1.0);
		}
	}
}