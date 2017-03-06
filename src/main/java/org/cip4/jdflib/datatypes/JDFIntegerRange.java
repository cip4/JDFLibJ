/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
 * JDFIntegerRange.java
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.util.HashUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class represents an integer range (JDFIntegerRange). It is a pair of 2 integer values separated by a tilde "~", for example "123 ~ 145" negative values
 * are treated differently depending on the value of m_defaultXDef @see getDefaultDef
 */
public class JDFIntegerRange extends JDFRange
{
	// **************************************** Attributes
	// ******************************************
	private int m_left = 0; // the left value of the range
	private int m_right = 0; // the right value of the range
	private int m_xDef = m_defaultXDef; // the static default when generating a list
	private static int m_defaultXDef = 0;

	/**
	 * factory for JDFIntegerRange that silently returns null in case of illegal strings
	 * @param s the string to parse
	 * @return the JDFIntegerRange, null if s is not compatible
	 */
	public static JDFIntegerRange createIntegerRange(String s)
	{
		if (s != null && s.length() > 0)
		{
			try
			{
				return new JDFIntegerRange(s);
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

	/**
	 *
	 * create a JDFIntegerRange from a string - return null if no go
	 *
	 * @param range
	 * @return
	 */
	public static JDFIntegerRange getIntegerRange(String range)
	{
		try
		{
			return new JDFIntegerRange(range);
		}
		catch (DataFormatException e)
		{
			return null;
		}
	}

	// **************************************** Constructors
	// ****************************************
	/**
	 * constructs an empty integer range
	 */
	public JDFIntegerRange()
	{
		init(0, 0, 0);
	}

	/**
	 * constructs an integer range with the given int (both values are equal)
	 *
	 * @param x the given min and max value
	 */
	public JDFIntegerRange(final int x)
	{
		init(x, x, m_defaultXDef);
	}

	/**
	 * constructs an integer range with the given int values
	 *
	 * @param xmin the given min value
	 * @param xmax the given max value
	 */
	public JDFIntegerRange(final int xmin, final int xmax)
	{
		init(xmin, xmax, m_defaultXDef);
	}

	/**
	 * constructs an integer range with the given int values
	 *
	 * @param xmin the given min value
	 * @param xmax the given max value
	 * @param xdef number of items
	 */
	public JDFIntegerRange(final int xmin, final int xmax, final int xdef)
	{
		init(xmin, xmax, xdef);
	}

	/**
	 * constructs an integer range with a given JDFIntegerRange
	 *
	 * @param ir the given JDFIntegerRange
	 */
	public JDFIntegerRange(final JDFIntegerRange ir)
	{
		init(ir.m_left, ir.m_right, ir.m_xDef);
	}

	/**
	 * Initialization
	 */
	protected void init(final int xmin, final int xmax, final int xdef)
	{
		m_left = xmin;
		m_right = xmax;
		this.setDef(xdef);
	}

	/**
	 * constructs an integer range with the given string
	 *
	 * @param s the given string
	 *
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFIntegerRange(final String s) throws DataFormatException
	{
		this(s, m_defaultXDef);
	}

	/**
	 * constructs an integer range with the given string
	 *
	 * @param s the given string
	 * @param xdef value which is used for negative numbers the value that -1 will represent in this range
	 *
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFIntegerRange(final String s, final int xdef) throws DataFormatException
	{
		if (s == null)
		{
			throw new DataFormatException("JDFIntegerRange illegal string: " + s);
		}
		final String[] strArray = s.split("~");
		if ((strArray.length <= 0) || (strArray.length > 2))
		{
			throw new DataFormatException("JDFIntegerRange illegal string: " + s);
		}
		try
		{
			if (strArray.length == 1)
			{
				// the min and the max values are equal
				m_left = StringUtil.parseInt(strArray[0], xdef);
				if (!StringUtil.isInteger(strArray[0]))
				{
					throw new DataFormatException("JDFIntegerRange illegal string: " + s);
				}
				m_right = m_left;
			}
			// two different values
			else
			{
				m_left = StringUtil.parseInt(strArray[0], xdef);
				// the min and the max values are equal
				m_left = StringUtil.parseInt(strArray[0], xdef);
				if (m_left == xdef && !StringUtil.isInteger(strArray[0]))
				{
					throw new DataFormatException("JDFIntegerRange illegal string: " + s);
				}
				m_right = StringUtil.parseInt(strArray[1], xdef);
				if (m_right == xdef && !StringUtil.isInteger(strArray[1]))
				{
					throw new DataFormatException("JDFIntegerRange illegal string: " + s);
				}
			}
		}
		catch (final NumberFormatException e)
		{
			throw new DataFormatException("JDFIntegerRange illegal string: " + s);
		}

		this.setDef(xdef); // set xDef
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		if (getLeft() == getRight())
		{
			return StringUtil.formatInteger(getRight());
		}
		return StringUtil.formatInteger(getLeft()) + " ~ " + StringUtil.formatInteger(getRight());
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
			new JDFIntegerRange(s);
		}
		catch (final DataFormatException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * equals - returns true if both JDFIntegerRange are equal otherwise false
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

		return this.toString().equals(((JDFIntegerRange) other).toString());
	}

	/**
	 * hashCode complements equals() to fulfill the equals/hashCode contract
	 */
	@Override
	public int hashCode()
	{
		return HashUtil.hashCode(0, this.toString());
	}

	/**
	 * getLeft - returns the left int object
	 *
	 * @return int - the left int object
	 */
	public int getLeft()
	{
		return (m_left < 0 && m_xDef > 0) ? m_xDef + m_left : m_left;
	}

	/**
	 * setLeft - sets the left int object
	 *
	 * @param x the left int object
	 */
	public void setLeft(final int x)
	{
		m_left = x;
	}

	/**
	 * getRight - returns the right int object
	 *
	 * @return int - the right int
	 */
	public int getRight()
	{
		return (m_right < 0 && m_xDef > 0) ? m_xDef + m_right : m_right;
	}

	/**
	 * scale - scales both values but NOT the default
	 *
	 * @param f the scaling factor
	 */
	public void scale(final int f)
	{
		m_right *= f;
		m_left *= f;
	}

	/**
	 * setRight - sets the right int object
	 *
	 * @param x the right int object
	 */
	public void setRight(final int x)
	{
		m_right = x;
	}

	/**
	 * getLowerValue - returns the lower value of the bounds for example 4~6 return 4, 7~5 return 5
	 *
	 * @return int - the lower value of the range
	 */
	public int getLowerValue()
	{
		final int left = this.getLeft();
		final int right = this.getRight();
		return (left < right) ? left : right;
	}

	/**
	 * setLowerValue - sets the lower value of the bounds
	 *
	 * @param x the new lower value of the range
	 */
	public void setLowerValue(final int x)
	{
		final int left = this.getLeft();
		final int right = this.getRight();
		if (left == right)
		{
			this.setLeft(x);
			this.setRight(x);
		}
		else if (left < right)
		{
			this.setLeft(x);
		}
		else
		{
			this.setRight(x);
		}
	}

	/**
	 * getUpperValue - returns the upper value of the bounds for example 4~6 return 6, 7~5 return 7
	 *
	 * @return int the upper value of the range
	 */
	public int getUpperValue()
	{
		final int left = this.getLeft();
		final int right = this.getRight();
		return (left < right) ? right : left;
	}

	/**
	 * setUpperValue - sets the upper value of the bounds
	 *
	 * @param x the new upper value of the range
	 */
	public void setUpperValue(final int x)
	{
		final int left = this.getLeft();
		final int right = this.getRight();
		if (left == right)
		{
			this.setLeft(x);
			this.setRight(x);
		}
		else if (left > right)
		{
			this.setLeft(x);
		}
		else
		{
			this.setRight(x);
		}
	}

	/**
	 * inRange - returns true if (lower value >= x <= upper value)
	 *
	 * @param x comparison value
	 *
	 * @return boolean - true if x in range
	 */
	public boolean inRange(final int x)
	{
		return (x >= this.getLowerValue()) && (x <= this.getUpperValue());
	}

	/**
	 * isPartOfRange - is range 'r' within this range?
	 *
	 * @param r the range to test
	 *
	 * @return boolean - true if range 'r' is within this range, else false
	 */
	@Override
	public boolean isPartOfRange(final JDFRange r)
	{
		final JDFIntegerRange ir = (JDFIntegerRange) r;
		return (ir.getLowerValue() >= this.getLowerValue()) && (ir.getUpperValue() <= this.getUpperValue());
	}

	/**
	 * getElementCount - returns the number of elements in this range, on the C++ side of the JDF library this method is called NElements if any if any range
	 * cannot be resolved due to an unknown negative value without a known default, -1 is returned
	 *
	 * @return int - the number of elements in this range, -1 if any range cannot be resolved
	 */
	public int getElementCount()
	{
		if (m_defaultXDef == 0 && (getRight() < 0 || getLeft() < 0))
		{
			return -1;
		}
		return 1 + Math.abs(this.getLeft() - this.getRight());
	}

	/**
	 * append - appends a value to this range, returns true if possible returns false if the element is not the next element in the list, it only appends on the
	 * right side of the range. For example:
	 *
	 * <pre>
	 * &quot;3&tilde;5&quot;        append(6)   -&gt; &quot;3&tilde;6&quot;
	 * &quot;5&quot;          append(6)   -&gt; &quot;5&tilde;6&quot;
	 * &quot;5&quot;          append(7)   -&gt; &quot;5 7&quot;
	 * &quot;5&tilde;9&quot;        append(6)   -&gt; &quot;5&tilde;9 6&quot;
	 * &quot;7&tilde;5&quot;        append(4)   -&gt; &quot;7&tilde;4&quot;
	 * </pre>
	 *
	 * @param x the new value
	 *
	 * @return boolean - true if successful
	 */
	public boolean append(final int x)
	{
		final int right = this.getRight();
		final int left = this.getLeft();
		if (right > left)
		{
			if ((right + 1) == x)
			{
				m_right = x;
				return true;
			}
		}
		else if (right < left)
		{
			if ((right - 1) == x)
			{
				m_right = x;
				return true;
			}
		}
		else
		{
			if ((right + 1) == x)
			{
				m_right = x;
				return true;
			}
			else if ((right - 1) == x)
			{
				m_right = x;
				return true;
			}
		}

		return false;
	}

	/**
	 * Element - value of the ith element in the range.<br>
	 * If the index is negativ the position is counted from the end of the range. For example the range is 3~7, the 2nd element is 5 and the -2nd element is 6.
	 * On the C++ side of the JDF library this method is called Element.
	 *
	 * @param i the position, if it is a negativ value start counting from the right side +1
	 *
	 * @return int the value at the ith position
	 *
	 * @throws NoSuchElementException - if the index is out of range
	 */
	public int getElement(final int i) throws NoSuchElementException
	{
		final int n = this.getElementCount();

		if ((i >= n) || (i < -n))
		{
			throw new NoSuchElementException("JDFIntegerRange::Element out of range error!");
		}

		// element(-2) is the second to last element
		if (i < 0)
		{
			return this.getElement(n + i);
		}

		// ascending range
		final int left = this.getLeft();
		final int right = this.getRight();
		if (right >= left)
		{
			return left + i;
		}

		// decending range
		return left - i;
	}

	/**
	 * setDef - sets xDef, the default value which is used for negative numbers<br>
	 * the value represents the index that is one past the end of the list<br>
	 * if xdef==0 (the default), the neg numbers themselves are used
	 *
	 * @param xdef the value that will represent negative values in this range
	 */
	public void setDef(final int xdef)
	{
		m_xDef = xdef;
	}

	/**
	 * setDefaultDef - sets the preset for xDef, which will be used when constructing an IntegerRange<br>
	 * the value represents the index that is one past the end of the list<br>
	 * if xdef==0 (the default), the neg numbers themselves are used
	 *
	 * @param xdef - (int)1 above the value that -1 will represent in this range i.e. the value that -0, were it possible to specify, would represent
	 */
	public static void setDefaultDef(final int xdef)
	{
		m_defaultXDef = xdef;
	}

	/**
	 * getDefaultDef - gets the preset for xDef, which will be used when constructing an IntegerRange<br>
	 * the value represents the index that is one past the end of the list<br>
	 * if xdef==0 (the default), the neg numbers themselves are used
	 *
	 * @return int - (int)1 above the value that -1 will represent in this range i.e. the value that -0, were it possible to specify, would represent
	 */
	public static int getDefaultDef()
	{
		return m_defaultXDef;
	}

	/**
	 * getDef - gets xDef, the default value which is used for negative numbers
	 *
	 * @return int - one above the value that -1 will represent in this range i.e. the value that -0, were it possible to specify, would represent
	 */
	public int getDef()
	{
		return m_xDef;
	}

	/**
	 * getIntegerList - returns the integer range as an integer list<br>
	 * for example an integer range of "5~9" will be returned as "5 6 7 8 9"
	 *
	 * @return JDFIntegerList - the integer list
	 */
	public JDFIntegerList getIntegerList()
	{

		final JDFIntegerList irl = new JDFIntegerList();
		final int elementCount = getElementCount();
		for (int i = 0; i < elementCount; i++)
		{
			irl.add(getElement(i));
		}
		return irl;

	}

	@Override
	protected Object getRightObject()
	{
		return Integer.valueOf(m_right);
	}

	@Override
	protected Object getLeftObject()
	{
		return Integer.valueOf(m_left);
	}

	@Override
	protected boolean inObjectRange(final Object other)
	{
		return inRange(((Integer) other).intValue());
	}

}
