/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
 * JDFIntegerList.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class is a representation of an integer list (JDFIntegerList). It is a whitespace separated list of integer values.
 */
public class JDFIntegerList extends JDFNumList
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see org.cip4.jdflib.datatypes.JDFNumList#isValidString(java.lang.String)
	 * @param st
	 * @return true if valid
	 */
	@Override
	public boolean isValidString(final String st)
	{
		final VString v = StringUtil.tokenize(st, null, false);
		if (v != null)
		{
			final int size = v.size();
			for (int i = 0; i < size; i++)
			{
				final String s = v.get(i);
				if (!StringUtil.isInteger(s))
				{
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * constructs an empty range list
	 */
	public JDFIntegerList()
	{
		super();
	}

	/**
	 * constructs an integer list with all values set via a String
	 *
	 * @param s the given String
	 *
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFIntegerList(final String s) throws DataFormatException
	{
		super(s);
	}

	/**
	 *
	 * convert a string to an integerlist, and return null if the string is no good
	 *
	 * @param s the string to parse
	 * @return the integerlist, null if snafu
	 * @deprecated use createIntegerList
	 */
	@Deprecated
	public static JDFIntegerList getIntegerList(final String s)
	{
		return createIntegerList(s);
	}

	/**
	 *
	 * convert a string to an integerlist, and return null if the string is no good
	 *
	 * @param s the string to parse
	 * @return the integerlist, null if snafu
	 */
	public static JDFIntegerList createIntegerList(final String s)
	{
		if (s == null || s.length() == 0)
			return null;
		try
		{
			return new JDFIntegerList(s);
		}
		catch (final DataFormatException e)
		{
			return null;
		}
	}

	/**
	 * constructs an integer list with all values set via a JDFIntegerList
	 *
	 * @param il the given integer list
	 *
	 * @throws DataFormatException - if the JDFIntegerList has not a valid format
	 */
	public JDFIntegerList(final JDFIntegerList il) throws DataFormatException
	{
		super(il);
	}

	/**
	 * constructs an integer list with all values set via an int[]
	 *
	 * @param iArray - the given integer array
	 */
	public JDFIntegerList(final int[] iArray)
	{
		super();
		setIntArray(iArray);
	}

	/**
	 * constructs an integer list with all values set via an int
	 *
	 * @param i the given integer
	 */
	public JDFIntegerList(final int i)
	{
		super();
		setInt(i);
	}

	// **************************************** Methods *********************************************
	/**
	 * isValid - true if all instances are Integer types
	 *
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	@Override
	public boolean isValid() throws DataFormatException
	{
		for (int i = 0; i < size(); i++)
		{
			if (!(elementAt(i) instanceof Integer))
			{
				throw new DataFormatException("Data format exception!");
			}
		}
		return true;
	}

	/**
	 * return true if at least one value in the list is d
	 *
	 * @param d the value to search
	 * @return true if this contains d
	 */
	public boolean contains(final int d)
	{
		return contains(Integer.valueOf(d));
	}

	/**
	 * equals - returns true if both JDFIntegerList are equal otherwise false
	 *
	 * @return boolean - true if equal otherwise false
	 */
	@Override
	public synchronized boolean equals(final Object other)
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

		return this.toString().equals(((JDFIntegerList) other).toString());
	}

	/**
	 * hashCode complements equals() to fulfill the equals/hashCode contract
	 */
	@Override
	public synchronized int hashCode()
	{
		return toString().hashCode();
	}

	/**
	 * addIntegerList - adds an integer list to this integer list
	 *
	 * @param il the given integer list
	 * @deprecated - use addAll()
	 */
	@Deprecated
	public void addIntegerList(final JDFIntegerList il)
	{
		addAll(il);
	}

	/**
	 * add - add an int to the vector
	 *
	 * @param x the int value
	 */
	public void add(final int x)
	{
		add(Integer.valueOf(x));
	}

	/**
	 * add - add an int to the vector
	 *
	 * @param x the int value
	 */
	public JDFIntegerList addX(final int x)
	{
		add(x);
		return this;
	}

	/**
	 * add - adds a complete integer list to the vector
	 *
	 * @param il the given integer list
	 * @deprecated - usa addAll
	 */
	@Deprecated
	public void add(final JDFIntegerList il)
	{
		addAll(il);
	}

	/**
	 * add - adds a integer list string to the existing integer list
	 *
	 * @param s the given string
	 *
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public void add(final String s) throws DataFormatException
	{
		final StringTokenizer sToken = new StringTokenizer(s, JDFConstants.BLANK);
		while (sToken.hasMoreTokens())
		{
			if (StringUtil.isInteger(s))
			{
				final int i = StringUtil.parseInt(sToken.nextToken(), 0);
				add(Integer.valueOf(i));
			}
			else
			{
				throw new DataFormatException("Bad integer: " + s);
			}
		}
	}

	/**
	 * getInt - returns the integer at 'pos' from the list.<br>
	 * Note: if pos is negative, getInt returns the pos'th integer counting from the end.
	 *
	 * @param pos index of the integer to get
	 *
	 * @return int - the pos'th int
	 */
	public int getInt(int pos)
	{

		if (pos < 0)
		{
			pos = pos + size();
		}

		final Integer i = (Integer) elementAt(pos);
		if (i == null)
		{
			throw new JDFException("JDFIntegerList:getInt invalid index");
		}

		return i.intValue();
	}

	public JDFIntegerList setIntX(final int pos, final int val)
	{
		setInt(pos, val);
		return this;
	}

	/**
	 * setInt - sets the integer val at 'pos' from the list.<br>
	 * Note: if pos is negative, setInt sets the pos'th integer counting from the end.
	 *
	 * @param pos index of the integer to get
	 * @param val the value to set
	 *
	 */
	public void setInt(int pos, final int val)
	{

		if (pos < 0)
		{
			pos = pos + size();
		}

		if (pos == size())
		{
			add(val);
		}
		else if (pos > size())
		{
			throw new JDFException("JDFIntegerList:setInt invalid index:" + pos);
		}
		else
		{
			set(pos, Integer.valueOf(val));
		}
	}

	@Override
	public void sort()
	{
		final int[] a = getIntArray();
		Arrays.sort(a);
		int pos = 0;
		for (final int d : a)
		{
			setInt(pos++, d);
		}

	}

	/**
	 * must keep this because otherwise the object vector gets corrupted with Double objects
	 *
	 * @see org.cip4.jdflib.datatypes.JDFNumList#scale(double)
	 */
	@Override
	public JDFIntegerList scale(final double factor)
	{
		final int[] a = getIntArray();
		for (int i = 0; i < a.length; i++)
		{
			a[i] *= factor;
		}
		setIntArray(a);
		return this;
	}

	/**
	 * getIntArray - returns this integer list as an int array
	 *
	 * @return int[] - the int array
	 */
	@Override
	public int[] getIntArray()
	{
		final int size = size();
		final int[] intArray = new int[size];

		for (int i = 0; i < size; i++)
		{
			intArray[i] = getInt(i);
		}

		return intArray;
	}

	/**
	 * setIntArray - sets this integer list to an int array<br>
	 * the RangeList is emptied, then the values of iArray are added
	 *
	 * @param iArray the int array
	 */
	public void setIntArray(final int[] iArray)
	{
		clear();
		for (final int i : iArray)
		{
			add(i);
		}
	}

	/**
	 * setIntArray - sets this integer list to an int<br>
	 * the RangeList is emptied, then the single value i is added
	 *
	 * @param i the value
	 */
	public void setInt(final int i)
	{
		clear();
		add(i);
	}

	/**
	 * setIntArray - sets this integer list to an int<br>
	 * the RangeList is emptied, then the single value i is added
	 *
	 * @param i the value
	 * @return
	 */
	public JDFIntegerList setIntX(final int i)
	{
		clear();
		return addX(i);
	}

	/**
	 * @see org.cip4.jdflib.datatypes.JDFNumList#abs()
	 */
	@Override
	public JDFNumList abs()
	{
		final int[] a = getIntArray();
		for (int i = 0; i < a.length; i++)
		{
			a[i] = Math.abs(a[i]);
		}
		setIntArray(a);
		return this;
	}

	/**
	 * subtract l from this,
	 *
	 * @param l the list to subtract from this
	 * @throws IllegalArgumentException if sizes don't match
	 */
	@Override
	public void subtract(final JDFNumList l)
	{
		if (l != null && size() == l.size())
		{
			final int[] me = getIntArray();
			final int[] them = l.getIntArray();
			for (int i = 0; i < me.length; i++)
			{
				me[i] -= them[i];
				setElementAt(Integer.valueOf(me[i]), i);
			}
		}
	}
}