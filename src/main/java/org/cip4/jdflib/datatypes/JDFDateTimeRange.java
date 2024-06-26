/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * @author Elena Skobchenko
 *
 *         JDFDateTimeRange.java
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.util.JDFDate;

/**
 *
 *
 * @author rainer prosi
 * @date Apr 12, 2013
 */
public class JDFDateTimeRange extends JDFRange
{
	// **************************************** Attributes
	// ******************************************
	/**
	 * first, left element
	 */
	private JDFDate m_left = null;

	/**
	 * second, right element
	 */
	private JDFDate m_right = null;

	// **************************************** Constructors
	// ****************************************
	/**
	 * Empty range constructor
	 */
	public JDFDateTimeRange()
	{
		init(null, null);
	}

	/**
	 * Constructor - creates a DateTime range defined by x ("from x to x")
	 *
	 * @param x boundary of the date/time range
	 */
	public JDFDateTimeRange(final JDFDate x)
	{
		init(x, x);
	}

	/**
	 * Constructor - creates a DateTime range defined by xmin and xmax
	 *
	 * @param xmin
	 * @param xmax
	 */
	public JDFDateTimeRange(final JDFDate xmin, final JDFDate xmax)
	{
		init(xmin, xmax);
	}

	/**
	 * copy constructor
	 *
	 * @param r
	 */
	public JDFDateTimeRange(final JDFDateTimeRange r)
	{
		init(r.getLeft(), r.getRight());
	}

	/**
	 * Initialization
	 *
	 * @param xmin
	 * @param xmax
	 */
	protected void init(final JDFDate xmin, final JDFDate xmax)
	{
		m_left = xmin;
		m_right = xmax;
	}

	/**
	 * factory style constructor that catches all exceptions and returns null if date is invalid
	 *
	 * @param date the formatted date string
	 * @return the JDFDate , null if date is not a valid string
	 */
	public static JDFDateTimeRange createDateTimeRange(final String date)
	{
		if (date == null || date.length() == 0)
		{
			return null;
		}
		try
		{
			return new JDFDateTimeRange(date);
		}
		catch (final DataFormatException e)
		{
			return null;
		}
	}

	/**
	 * Construct a JDFDateTimeRange from a string
	 *
	 * @param s
	 *
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFDateTimeRange(final String s) throws DataFormatException
	{
		final String[] strArray = s.split("~");
		if (strArray.length <= 0 || strArray.length > 2)
		{
			throw new DataFormatException("JDFDateTimeRange illegal string: " + s);
		}

		try
		{
			// the min and the max values are equal
			if (strArray.length == 1)
			{
				m_left = new JDFDate(strArray[0].trim());
				m_right = new JDFDate(strArray[0].trim());
			}
			// two different values
			else
			{
				m_left = new JDFDate(strArray[0].trim());
				m_right = new JDFDate(strArray[1].trim());
			}
		}
		catch (final DataFormatException e)
		{
			throw new DataFormatException("JDFDateTimeRange illegal string: " + s);
		}
	}

	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return getString(0);
	}

	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String getXJDFString(final int precision)
	{
		return m_left.getDateTimeISO() + " . " + m_right.getDateTimeISO();
	}

	/**
	 * isValid - validate the given String
	 *
	 * @param s the given string
	 *
	 * @return boolean - false if the String has not a valid format
	 */
	public boolean isValid(final String s)
	{
		try
		{
			new JDFDateTimeRange(s);
		}
		catch (final DataFormatException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * inRange - returns true if 'x' is within the range defined by 'this'
	 *
	 * @param x JDFDate that is to be compared with 'this'
	 * @return boolean - true if 'x' is within the range defined by 'this'
	 */
	public boolean inRange(final JDFDate x)
	{
		final JDFDate min = this.getLowerValue();
		final JDFDate max = this.getUpperValue();
		return ((x.isLater(min) || x.equals(min)) && (x.isEarlier(max) || x.equals(max)));
	}

	/**
	 * isPartOfRange - is range 'r' within this range?
	 *
	 * @param ra the range to test
	 *
	 * @return boolean - true if range 'r' is within this range, else false
	 */
	@Override
	public boolean isPartOfRange(final JDFRange ra)
	{
		final JDFDateTimeRange r = (JDFDateTimeRange) ra;
		final JDFDate min = this.getLowerValue();
		final JDFDate r_min = r.getLowerValue();
		final JDFDate max = this.getUpperValue();
		final JDFDate r_max = r.getUpperValue();
		return r_min.isLater(min) && r_max.isEarlier(max);
	}

	/**
	 * getLeft - get the left of the two range deliminators xmin ~ xmax
	 *
	 * @return JDFDate - the left value
	 */
	public JDFDate getLeft()
	{
		return m_left;
	}

	/**
	 * getRight - get the right of the two range deliminators xmin ~ xmax
	 *
	 * @return JDFDate - the right value
	 */
	public JDFDate getRight()
	{
		return m_right;
	}

	/**
	 * setLeft - sets the left JDFDate object of the range
	 *
	 * @param x the left JDFDate object of the range
	 */
	public void setLeft(final JDFDate x)
	{
		m_left = x;
	}

	/**
	 * setRight - sets the right JDFDate object of the range
	 *
	 * @param x the right JDFDate object of the range
	 */
	public void setRight(final JDFDate x)
	{
		m_right = x;
	}

	/**
	 * getUpperValue - returns the upper value of the bounds
	 *
	 * @return JDFDate - the upper value of the range
	 */
	public JDFDate getUpperValue()
	{
		return (m_left.isEarlier(m_right) || (m_left.equals(m_right)) ? m_right : m_left);
	}

	/**
	 * getLowerValue - returns the lower value of the bounds
	 *
	 * @return JDFDate - the lower value of the range
	 */
	public JDFDate getLowerValue()
	{
		return (m_left.isEarlier(m_right) ? m_left : m_right);
	}

	@Override
	protected JDFDate getRightObject()
	{
		return m_right;
	}

	@Override
	protected JDFDate getLeftObject()
	{
		return m_left;
	}

	@Override
	protected boolean inObjectRange(final Object other)
	{
		return inRange((JDFDate) other);
	}

	@Override
	public String getRightString(int precision)
	{
		return getRight().getDateISO();
	}

	@Override
	public String getLeftString(int precision)
	{
		return getLeft().getDateISO();
	}

}
