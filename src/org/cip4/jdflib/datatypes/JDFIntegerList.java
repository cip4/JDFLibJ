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
 * JDFIntegerList.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.StringTokenizer;
import java.util.Vector;
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
				final String s = v.stringAt(i);
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
		// default super constructor
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
	 * constructs an integer list with all values set via a Vector of Intger objects
	 * 
	 * @param v the given vector
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 * @deprecated use typesafe constructors
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public JDFIntegerList(final Vector v) throws DataFormatException
	{
		super(v);
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
		this(il.m_numList);
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
		for (int i = 0; i < m_numList.size(); i++)
		{
			if (!(m_numList.elementAt(i) instanceof Integer))
			{
				throw new DataFormatException("Data format exception!");
			}
		}
		return true;
	}

	/**
	 * return true if at least one value in the list is d
	 * @param d the value to search
	 * @return true if this contais d
	 */
	public boolean contains(final int d)
	{
		return contains(new Integer(d));
	}

	/**
	 * equals - returns true if both JDFIntegerList are equal otherwise false
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

		return this.toString().equals(((JDFIntegerList) other).toString());
	}

	/**
	 * hashCode complements equals() to fulfill the equals/hashCode contract
	 */
	@Override
	public int hashCode()
	{
		return this.toString().hashCode();
	}

	/**
	 * addIntegerList - adds an integer list to this integer list
	 * 
	 * @param il the given integer list
	 */
	public void addIntegerList(final JDFIntegerList il)
	{
		final int size = il.size();
		for (int i = 0; i < size; i++)
		{
			m_numList.addElement(il.elementAt(i));
		}
	}

	/**
	 * add - add an int to the vector
	 * 
	 * @param x the int value
	 */
	public void add(final int x)
	{
		m_numList.add(new Integer(x));
	}

	/**
	 * add - add an Integer object to the vector
	 * 
	 * @param x the Integer object
	 */
	public void add(final Integer x)
	{
		m_numList.add(x);
	}

	/**
	 * add - adds a complete integer list to the vector
	 * 
	 * @param il the given integer list
	 */
	public void add(final JDFIntegerList il)
	{
		m_numList.addAll(il.copyNumList());
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
		final Vector<Object> numList = m_numList;

		while (sToken.hasMoreTokens())
		{
			final int i = StringUtil.parseInt(sToken.nextToken(), 0);

			try
			{
				numList.addElement(new Integer(i));
			}
			catch (final NumberFormatException e)
			{
				throw new DataFormatException("Data format exception!");
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
			m_numList.set(pos, new Integer(val));
		}

	}

	/**
	 * must keep this because otherwise the object vector gets corrupted with Double objects (non-Javadoc)
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
	public int[] getIntArray()
	{
		final int size = m_numList.size();
		final int[] intArray = new int[size];

		for (int i = 0; i < size; i++)
		{
			intArray[i] = ((Integer) m_numList.elementAt(i)).intValue();
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
		m_numList.clear();
		for (int i = 0; i < iArray.length; i++)
		{
			m_numList.add(new Integer(iArray[i]));
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
		m_numList.clear();
		m_numList.add(new Integer(i));
	}

}