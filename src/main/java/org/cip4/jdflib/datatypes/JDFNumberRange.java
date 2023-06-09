/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of
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
 * @author Elena Skobchenko
 *
 * JDFNumberRange.java
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.util.HashUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class represents a number range (JDFNumberRange). It is a whitespace separated list of 2 double values separated by a tilde "~", for example "1.23 ~ 1.45"
 */
public class JDFNumberRange extends JDFRange
{
	/**
	 * factory for JDFNumberRange that silently returns null in case of illegal strings
	 * 
	 * @param s the string to parse
	 * @return the JDFNumberRange, null if s is not compatible
	 */
	public static JDFNumberRange createNumberRange(String s)
	{
		if (s != null && s.length() > 0)
		{
			try
			{
				return new JDFNumberRange(s);
			}
			catch (DataFormatException x)
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	// **************************************** Attributes
	// ******************************************
	private double m_left = 0; // the left value of the range
	private double m_right = 0; // the right value of the range

	// **************************************** Constructors
	// ****************************************
	/**
	 * constructs an empty JDFNumberRange
	 */
	public JDFNumberRange()
	{
		init(0, 0);
	}

	/**
	 * constructs a JDFNumberRange, both values are equal ("from x to x")
	 * 
	 * @param x the given double
	 */
	public JDFNumberRange(final double x)
	{
		init(x, x);
	}

	/**
	 * constructor, creates a JDFNumberRange bounded by two double values ("from xmin to xmax")
	 * 
	 * @param xmin the given min value
	 * @param xmax the given max value
	 */
	public JDFNumberRange(final double xmin, final double xmax)
	{
		init(xmin, xmax);
	}

	/**
	 * copy constructor, creates a JDFNumberRange with the given JDFNumberRange
	 * 
	 * @param nr
	 */
	public JDFNumberRange(final JDFNumberRange nr)
	{
		init(nr.getLeft(), nr.getRight());

	}

	/**
	 * Initialization
	 * 
	 * @param xmin left value
	 * @param xmax right value
	 */
	protected void init(final double xmin, final double xmax)
	{
		m_left = xmin;
		m_right = xmax;
	}

	/**
	 * constructs a JDFNumberRange with the values of the given string
	 * 
	 * @param s the given string
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFNumberRange(final String s) throws DataFormatException
	{
		final String[] strArray = s.split("~");
		if (strArray.length <= 0 || strArray.length > 2)
		{
			throw new DataFormatException("JDFNumberRange illegal string: " + s);
		}
		try
		{
			// the min and the max values are equal
			if (strArray.length == 1)
			{
				m_left = StringUtil.parseDouble(strArray[0], 0.0);
				if (m_left == 0.0 && !StringUtil.isNumber(strArray[0]))
				{
					throw new DataFormatException("JDFIntegerRange illegal string: " + s);
				}
				m_right = m_left;
			}
			// two different values
			else
			{
				m_left = StringUtil.parseDouble(strArray[0], 0.0);
				if (m_left == 0.0 && !StringUtil.isNumber(strArray[0]))
				{
					throw new DataFormatException("JDFIntegerRange illegal string: " + s);
				}
				m_right = StringUtil.parseDouble(strArray[1], 0.0);
				if (m_right == 0.0 && !StringUtil.isNumber(strArray[1]))
				{
					throw new DataFormatException("JDFIntegerRange illegal string: " + s);
				}
			}
		}
		catch (final NumberFormatException e)
		{
			throw new DataFormatException("JDFNumberRange illegal string: " + s);
		}
	}

	/**
	 * isValid - validate the given String
	 * 
	 * @param s the given string to validate
	 * 
	 * @return boolean - false if the String has not a valid format
	 */
	public boolean isValid(final String s)
	{
		try
		{
			new JDFNumberRange(s);
		}
		catch (final DataFormatException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * equals - returns true if both JDFNumberRange are equal otherwise false, the difference must be smaller than EPSILON
	 * 
	 * @param other the object to compare with <code>this</code>
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

		final JDFNumberRange jdfNumberRange = (JDFNumberRange) other;

		return (Math.abs(this.getLeft() - jdfNumberRange.getLeft()) <= EPSILON) && (Math.abs(this.getRight() - jdfNumberRange.getRight()) <= EPSILON);
	}

	/**
	 * hashCode complements equals() to fulfill the equals/hashCode contract
	 * 
	 * @return int - hash code of <code>this</this>
	 */
	@Override
	public int hashCode()
	{
		return HashUtil.hashCode(0, this.toString());
	}

	/**
	 * returns the string representation of the left value of the range
	 * 
	 * @return the left value of the range
	 */
	public String getLeftString()
	{
		return StringUtil.formatDouble(getLeft());
	}

	/**
	 * getLeft - returns the left value of the range
	 * 
	 * @return double - the left value of the range
	 */
	public double getLeft()
	{
		return m_left;
	}

	/**
	 * returns the string representation of the left value of the range
	 * 
	 * @param precision
	 * @return
	 */
	@Override
	public String getLeftString(int precision)
	{
		return StringUtil.formatDouble(getLeft(), precision);
	}

	/**
	 * returns the string representation of the left value of the range
	 * 
	 * @param precision
	 * @return
	 */
	@Override
	public String getRightString(int precision)
	{
		return StringUtil.formatDouble(getRight(), precision);
	}

	/**
	 * returns the string representation of the left value of the range
	 * 
	 * @return the left value of the range
	 */
	public String getRightString()
	{
		return StringUtil.formatDouble(getRight());
	}

	/**
	 * getRight - returns the right value of the range
	 * 
	 * @return double - the right value of the range
	 */
	public double getRight()
	{
		return m_right;
	}

	/**
	 * setLeft - sets the left double object
	 * 
	 * @param x the left double object
	 */
	public void setLeft(final double x)
	{
		m_left = x;
	}

	/**
	 * setRight - sets the right double object
	 * 
	 * @param x the right double object
	 */
	public void setRight(final double x)
	{
		m_right = x;
	}

	/**
	 * scale - scales both values
	 * 
	 * @param f the scaling factor
	 */
	public void scale(final double f)
	{
		m_right *= f;
		m_left *= f;
	}

	/**
	 * getLowerValue - returns the lower value of the bounds<br>
	 * for example 4.5~6.3 returns 4.5, 7.0~5.9 returns 5.9
	 * 
	 * @return double - the lower value of the range
	 */
	public double getLowerValue()
	{
		return (getLeft() < getRight()) ? getLeft() : getRight();
	}

	/**
	 * getUpperValue - return the upper value of the bounds<br>
	 * for example 4.5~6.3 returns 6.3, 7.0~5.9 returns 7.0
	 * 
	 * @return double - the upper value of the range
	 */
	public double getUpperValue()
	{
		return (getLeft() < getRight()) ? getRight() : getLeft();
	}

	/**
	 * inRange - returns true if (lower value >= x <= upper value)
	 * 
	 * @param x comparison value
	 * 
	 * @return boolean - true if x in range
	 */
	public boolean inRange(final double x)
	{
		return (x >= getLowerValue()) && (x <= getUpperValue());
	}

	/**
	 * isPartOfRange - is range 'r' within this range?
	 * 
	 * @param r the range to test
	 * 
	 * @return boolean - true if range 'r' is within this range, else false
	 */
	@Override
	public boolean isPartOfRange(final JDFRange ra)
	{
		final JDFNumberRange r = (JDFNumberRange) ra;
		return (r.getLowerValue() >= this.getLowerValue()) && (r.getUpperValue() <= this.getUpperValue());
	}

	@Override
	protected Object getRightObject()
	{
		return Double.valueOf(m_right);
	}

	@Override
	protected Object getLeftObject()
	{
		return Double.valueOf(m_left);
	}

	@Override
	protected boolean inObjectRange(final Object other)
	{
		return inRange(((Double) other).doubleValue());
	}

	/**
	 * @see org.cip4.jdflib.datatypes.JDFRange#getString(int)
	 */
	@Override
	public String getString(int precision)
	{
		if (Math.abs(this.getLeft() - this.getRight()) < JDFBaseDataTypes.EPSILON)
		{
			return JDFConstants.EMPTYSTRING + getRightString(precision);
		}
		return getLeftString(precision) + " ~ " + getRightString(precision);

	}

}