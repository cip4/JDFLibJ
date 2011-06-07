/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of 
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
 * JDFXYPair.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.HashUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class represents a x y pair (JDFXYPair). It is a whitespace separated list of 2 numbers.
 */
public class JDFXYPair extends JDFNumList
{
	// **************************************** Constructors
	// ****************************************
	/**
	 * constructs a xy pair with all values set to 0.0 Double
	 */
	public JDFXYPair()
	{
		super(MAX_XY_DIMENSION);
	}

	/**
	 * constructs a xy pair with all values set via a Vector of Double objects
	 * 
	 * @param v Vector of Double
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 * @deprecated use typesafe constructors
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	public JDFXYPair(final Vector v) throws DataFormatException
	{
		super(v);
	}

	/**
	 * constructs a xy pair with all values set via a String
	 * if a single numerical value is specified, s is padded with " 0"
	 * 
	 * @param s the given String
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFXYPair(final String s) throws DataFormatException
	{
		super(s);
	}

	/**
	 * constructs a xy pair with all values set via a JDFXYPair
	 * 
	 * @param xy the given xy pair
	 * 
	 */
	public JDFXYPair(final JDFXYPair xy)
	{
		super(MAX_XY_DIMENSION);
		setX(xy.getX());
		setY(xy.getY());
	}

	/**
	 * constructs a xy pair with all values set via a JDFNumberList
	 * 
	 * @param nl the given number list
	 * 
	 * @throws DataFormatException - if the JDFNumberList has not a valid format
	 */
	public JDFXYPair(final JDFNumberList nl) throws DataFormatException
	{
		super(MAX_XY_DIMENSION);
		if (nl.size() != MAX_XY_DIMENSION)
		{
			throw new DataFormatException("JDFXYPair: can't construct JDFXYPair from this JDFNuberList value");
		}
		m_numList.set(0, nl.m_numList.get(0));
		m_numList.set(1, nl.m_numList.get(1));
	}

	/**
	 * constructs a new JDFXYPair with the given double values
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public JDFXYPair(final double x, final double y)
	{
		super(MAX_XY_DIMENSION);
		m_numList.set(0, new Double(x));
		m_numList.set(1, new Double(y));
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * isValid - valid if the size of the vector is 2 and all instances are Double types
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	@Override
	public boolean isValid() throws DataFormatException
	{
		if (m_numList.size() != MAX_XY_DIMENSION)
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
	 * getX - returns the x coordinate
	 * 
	 * @return double - the x coordinate
	 */
	public double getX()
	{
		return ((Double) m_numList.get(0)).doubleValue();
	}

	/**
	 * setX - sets the x coordinate
	 * 
	 * @param x the x coordinate
	 */
	public void setX(final double x)
	{
		m_numList.set(0, new Double(x));
	}

	/**
	 * getY - returns the y coordinate
	 * 
	 * @return double - the y coordinate
	 */
	public double getY()
	{
		return ((Double) m_numList.get(1)).doubleValue();
	}

	/**
	 * setY - sets the y coordinate
	 * 
	 * @param y the y coordinate
	 */
	public void setY(final double y)
	{
		m_numList.set(1, new Double(y));
	}

	/**
	 * equals - returns true if both JDFShapes are equal, otherwise false
	 * 
	 * @return boolean - true if equal otherwise false
	 */
	@Override
	public boolean equals(final Object other)
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

		final JDFXYPair xyPair = (JDFXYPair) other;

		return (Math.abs(this.getX() - xyPair.getX()) <= EPSILON) && (Math.abs(this.getY() - xyPair.getY()) <= EPSILON);
	}

	/**
	 * hashCode complements equals() to fulfill the equals/hashCode contract
	 */
	@Override
	public int hashCode()
	{
		return HashUtil.hashCode(super.hashCode(), this.toString());
	}

	/**
	 * isGreaterOrEqual - equality operator >=
	 * 
	 * @param x the JDFXYPair object to compare to
	 * @return boolean - true if this >= x
	 */
	public boolean isGreaterOrEqual(final JDFXYPair x)
	{
		return (equals(x) || ((getX() >= x.getX()) && (getY() >= x.getY())));
	}

	/**
	 * isLessOrEqual - equality operator <=
	 * 
	 * @param x the JDFXYPair object to compare to
	 * @return boolean - true if this <= x
	 */
	public boolean isLessOrEqual(final JDFXYPair x)
	{
		return (equals(x) || ((getX() <= x.getX()) && (getY() <= x.getY())));
	}

	/**
	 * isGreater - equality operator >
	 * 
	 * @param x the JDFXYPair object to compare to
	 * @return boolean - true if this > x
	 */
	public boolean isGreater(final JDFXYPair x)
	{
		return (!isLessOrEqual(x));
	}

	/**
	 * isLess - equality operator <
	 * 
	 * @param x the JDFXYPair object to compare to
	 * @return boolean - true if this < x
	 */
	public boolean isLess(final JDFXYPair x)
	{
		return (!isGreaterOrEqual(x));
	}

	/**
	 * setString with some heuristics to allow for a single numerical or values like "i/j"
	 * @see org.cip4.jdflib.datatypes.JDFNumList#setString(java.lang.String)
	 */
	@Override
	public void setString(String string) throws DataFormatException
	{
		try
		{
			super.setString(string);
		}
		catch (DataFormatException x)
		{
			Double d = StringUtil.parseDouble(string, Double.NaN);
			if (!Double.isNaN(d))
			{
				super.setString(string + " 0");
			}
			else
			{
				VString v = StringUtil.tokenize(string, "/", false);
				if (v.size() == 2)
				{
					super.setString(StringUtil.replaceChar(string, '/', " ", 0));
				}
				else
				{
					throw x;
				}
			}
		}
	}
}
