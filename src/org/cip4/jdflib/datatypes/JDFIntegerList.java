/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of 
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
import org.cip4.jdflib.util.HashUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class is a representation of an integer list (JDFIntegerList). It is a whitespace separated
 * list of integer values.
 */
public class JDFIntegerList extends JDFNumList
{
	/**
	 * @see org.cip4.jdflib.datatypes.JDFNumList#isValidString(java.lang.String)
	 * @param s
	 * @return true if valid
	 */
	@Override
	public boolean isValidString(String st)
	{
		VString v = StringUtil.tokenize(st, null, false);
		if (v != null)
		{
			final int size = v.size();
			for (int i = 0; i < size; i++)
			{
				String s = v.stringAt(i);
				if (!StringUtil.isInteger(s))
					return false;
			}
		}
		
		return true;
	}

	/**
	 * constructs an empty range list
	 */
	public JDFIntegerList()
	{
		//default super constructor
	}

	/**
	 * constructs an integer list with all values set via a String
	 *
	 * @param s the given String
	 *
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFIntegerList(String s) throws DataFormatException
	{
		super(s);
	}

	/**
	 * constructs an integer list with all values set via a Vector of Intger objects
	 *
	 * @param v the given vector
	 *
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	public JDFIntegerList(Vector v) throws DataFormatException
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
	public JDFIntegerList(JDFIntegerList il) throws DataFormatException
	{
		this(il.m_numList);
	}

	/**
	 * constructs an integer list with all values set via an int[]
	 *
	 * @param iArray - the given integer array
	 */
	public JDFIntegerList(int[] iArray)
	{
		super();
		setIntArray(iArray);
	}

	/**
	 * constructs an integer list with all values set via an int
	 *
	 * @param i the given integer 
	 */
	public JDFIntegerList(int i)
	{
		super();
		setInt(i);
	}

	//**************************************** Methods *********************************************
	/**
	 * isValid - true if all instances are Integer types
	 *
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	@Override
	public void isValid() throws DataFormatException
	{
		for (int i = 0; i < m_numList.size(); i++)
		{
			if (!(m_numList.elementAt(i) instanceof Integer))
			{
				throw new DataFormatException("Data format exception!");
			}
		}
	}

	/**
	 * return true if at least one value in the list is d
	 * @param d the value to search
	 * @return true if this contais d
	 */
	public boolean contains(int d)
	{
		return contains(new Integer(d));
	}

	/**
	 * equals - returns true if both JDFIntegerList are equal otherwise false
	 *
	 * @return boolean - true if equal otherwise false
	 */
	@Override
	public boolean equals(Object other)
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
		return HashUtil.hashCode(super.hashCode(), this.toString());
	}

	/**
	 * addIntegerList - adds an integer list to this integer list
	 *
	 * @param il the given integer list
	 */
	public void addIntegerList(JDFIntegerList il)
	{
		final Vector getnumList = m_numList;
		final int size = il.size();
		for (int i = 0; i < size; i++)
		{
			getnumList.addElement(il.elementAt(i));
		}
	}

	/**
	 * add - add an int to the vector
	 *
	 * @param x the int value
	 */
	public void add(int x)
	{
		m_numList.add(new Integer(x));
	}

	/**
	 * add - add an Integer object to the vector
	 *
	 * @param x the Integer object
	 */
	public void add(Integer x)
	{
		m_numList.add(x);
	}

	/**
	 * add - adds a complete integer list to the vector
	 *
	 * @param il the given integer list
	 */
	public void add(JDFIntegerList il)
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
	public void add(String s) throws DataFormatException
	{
		StringTokenizer sToken = new StringTokenizer(s, JDFConstants.BLANK);
		final Vector numList = m_numList;

		while (sToken.hasMoreTokens())
		{
			int i = StringUtil.parseInt(sToken.nextToken(), 0);

			try
			{
				numList.addElement(new Integer(i));
			}
			catch (NumberFormatException e)
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
		int posLocal = pos;

		if (posLocal < 0)
			posLocal = posLocal + size();

		Integer i = (Integer) elementAt(posLocal);
		if (i == null)
			throw new JDFException("JDFIntegerList:getInt invalid index");

		return i.intValue();
	}

	/*
	 * must keep this because otherwise the object vector gets corrupted with Double objects
	 * (non-Javadoc)
	 * @see org.cip4.jdflib.datatypes.JDFNumList#scale(double)
	 */
	@Override
	public void scale(double factor)
	{
		int[] a = getIntArray();
		for (int i = 0; i < a.length; i++)
			a[i] *= factor;
		setIntArray(a);
	}

	/**
	* getIntArray - returns this integer list as an int array
	*
	* @return int[] - the int array
	*/
	public int[] getIntArray()

	{
		final Vector numList = m_numList;
		final int size = numList.size();
		int[] intArray = new int[size];

		for (int i = 0; i < size; i++)
		{
			intArray[i] = ((Integer) numList.elementAt(i)).intValue();
		}

		return intArray;
	}

	/**
	 * setIntArray - sets this integer list to an int array<br>
	 * the RangeList is emptied, then the values of iArray are added
	 *
	 * @param iArray the int array
	 */
	public void setIntArray(int[] iArray)
	{
		Vector numList = m_numList;
		numList.clear();
		for (int i = 0; i < iArray.length; i++)
			numList.add(new Integer(iArray[i]));
	}

	/**
	 * setIntArray - sets this integer list to an int<br>
	 * the RangeList is empied, then the single value i is added 
	 *
	 * @param i the value
	 */
	public void setInt(int i)
	{
		Vector numList = m_numList;
		numList.clear();
		numList.add(new Integer(i));
	}

}