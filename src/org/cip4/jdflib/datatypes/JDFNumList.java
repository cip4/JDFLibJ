/*--------------------------------------------------------------------------------------------------
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
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFNumList.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.StringUtil;

/**
 * This abstract class is the representation of a number list (Integer and Double object). Intern these objects are collected in a vector and there are several
 * methods to provide an access to the data.
 */
public abstract class JDFNumList implements JDFBaseDataTypes, Cloneable
{
	// **************************************** Constructors
	// ****************************************
	protected Vector<Object> m_numList = new Vector<Object>();

	// **************************************** Constructors
	// ****************************************
	/**
	 * constructs an empty number list
	 */
	public JDFNumList()
	{
		// default super constructor
	}

	/**
	 * constructor - constructs a number list with the given size and sets all values set to 0.0 Double
	 * 
	 * @param size the given size
	 */
	public JDFNumList(final int size)
	{
		for (int i = 0; i < size; i++)
		{
			m_numList.addElement(new Double(0.0));
		}
	}

	/**
	 * same as Vector.clear()
	 * 
	 */
	public void clear()
	{
		m_numList.clear();
	}

	/**
	 * constructor - constructs a number list with the given vector
	 * 
	 * @param v a vector with number list objects
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	public JDFNumList(final Vector v) throws DataFormatException
	{
		this.m_numList = v;
		isValid();
	}

	/**
	 * constructor - constructs a number list with the given String; if the sub class is of type JDFIntegerList all object will be Integer in all other cases
	 * the object will be a Double
	 * 
	 * @param sl the given String
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFNumList(final String sl) throws DataFormatException
	{
		final VString v = StringUtil.tokenize(sl, null, false);
		if (v != null)
		{
			final int size = v.size();
			for (int i = 0; i < size; i++)
			{
				final String s = v.stringAt(i);
				if (!StringUtil.isNumber(s))
				{
					throw new DataFormatException("JDFNumList: bad numeric value: " + s);
				}
				if (this instanceof JDFIntegerList)
				{
					m_numList.addElement(new Integer(StringUtil.parseInt(s, 0)));
				}
				else
				{
					m_numList.addElement(new Double(StringUtil.parseDouble(s, 0)));
				}
			}
		}

		isValid();
	}

	/**
	 * constructor - constructs a number list with a given JDFNumList
	 * 
	 * @param nl the given number list
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFNumList(final JDFNumList nl) throws DataFormatException
	{
		this(nl.copyNumList());
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * getString - returns all values whitespace separated in a String
	 * 
	 * @return String
	 * @deprecated 060418 - use toString
	 */
	@Deprecated
	public String getString()
	{
		return toString();
	}

	/**
	 * toString - returns the JDFNumList as a String
	 * 
	 * @return String - the JDFNumList as a String
	 */
	@Override
	public String toString()
	{
		final StringBuffer sb = new StringBuffer();

		for (int i = 0; i < m_numList.size(); i++)
		{
			if (i > 0)
			{
				sb.append(JDFConstants.BLANK);
			}

			final Object o = m_numList.elementAt(i);
			if (o instanceof Double)
			{
				sb.append(StringUtil.formatDouble(((Double) o).doubleValue()));
			}
			else if (o instanceof Integer)
			{
				sb.append(StringUtil.formatInteger(((Integer) o).intValue()));
			}
			else
			{
				sb.append(o.toString());
			}
		}

		return sb.toString();
	}

	/**
	 * getNumberList - returns the object in a JDFNumberList format
	 * 
	 * @return JDFNumberList - the object in JDFNumberList format
	 */
	protected JDFNumberList getNumberList()
	{
		JDFNumberList nl = null;

		try
		{
			nl = new JDFNumberList(m_numList);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}

		return nl;
	}

	/**
	 * equals - compares two JDFNumList elements
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

		boolean retVal = false;

		final JDFNumList jdfNumList = (JDFNumList) other;
		final int size = size();
		if (size == jdfNumList.size())
		{
			retVal = true;
			for (int i = 0; i < size && retVal == true; i++)
			{
				final double d1 = doubleAt(i);
				final double d2 = jdfNumList.doubleAt(i);
				retVal = StringUtil.isEqual(d1, d2);
			}
		}

		return retVal;
	}

	/**
	 * hashCode complements equals() to fulfill the equals/hashCode contract
	 */
	@Override
	public int hashCode()
	{
		return this.m_numList.toString().hashCode();
	}

	/**
	 * size - returns the size of the list
	 * 
	 * @return int - the size of the list
	 */
	public int size()
	{
		return m_numList.size();
	}

	/**
	 * getElementAt - returns the element at the ith position
	 * 
	 * @param i the index
	 * @return Object - the range object at the given position, null if i is out of range
	 */
	public Object elementAt(int i)
	{
		if (i < 0)
		{
			i = m_numList.size() + i;
		}

		if (i < 0 || i >= m_numList.size())
		{
			return null;
		}
		return m_numList.elementAt(i);
	}

	/**
	 * getElementAt - returns the element at the ith position
	 * 
	 * @param i the index
	 * @return double - the double value given position, 0.0 if out of range
	 */
	public double doubleAt(final int i)
	{
		final Object o = elementAt(i);
		if (o instanceof Integer)
		{
			return ((Integer) o).doubleValue();
		}
		if (o instanceof Double)
		{
			return ((Double) o).doubleValue();
		}
		return 0;
	}

	/**
	 * copyNumList - returns a clone of the numList vector
	 * 
	 * @return Vector - the clone of the numList vector
	 */
	public Vector copyNumList()
	{
		return (Vector) m_numList.clone();
	}

	/**
	 * clone - Returns a clone of this instance
	 * 
	 * @return Object - the clone
	 * @throws CloneNotSupportedException
	 */
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		final JDFNumList num = (JDFNumList) super.clone();
		num.m_numList = ((Vector) (m_numList.clone()));
		return num;
	}

	/**
	 * removeElementAt - removes the element at the given position
	 * 
	 * @param i the position from where to remove the element
	 * 
	 * @return boolean - true if successfull otherwise false
	 */
	public boolean removeElementAt(final int i)
	{
		int iLocal = i;

		if (iLocal < 0)
		{
			iLocal = iLocal + size();
		}

		if ((iLocal < size()) && (iLocal >= 0))
		{
			m_numList.removeElementAt(iLocal);

			return true;
		}

		return false;
	}

	/**
	 * replaceElementAt - replaces the element at the given position with the given object
	 * 
	 * @param obj the object
	 * @param i the given position
	 * @return boolean - true if successfull otherwise false
	 */
	public boolean replaceElementAt(final Object obj, final int i)
	{
		int iLocal = i;

		if (iLocal < 0)
		{
			iLocal = iLocal + size();
		}

		if ((iLocal < m_numList.size()) && (iLocal > -1))
		{
			m_numList.removeElementAt(iLocal);
			m_numList.insertElementAt(obj, iLocal);

			return true;
		}

		return false;
	}

	/**
	 * isValid - true if all instances are Double or Integer types
	 * 
	 * @return boolean - true if all instances are Double or Integer types
	 * @throws DataFormatException
	 */
	public abstract boolean isValid() throws DataFormatException;

	/**
	 * isValidString - true if all instances are Double or Integer types
	 * @param st the string to check
	 * 
	 * @return boolean - true if all instances are Double or Integer types
	 */
	public boolean isValidString(final String st)
	{
		final VString v = StringUtil.tokenize(st, null, false);
		if (v != null)
		{
			final int size = v.size();
			for (int i = 0; i < size; i++)
			{
				final String s = v.stringAt(i);
				if (!StringUtil.isNumber(s))
				{
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * scale all values of this to points from millimeters
	 * 
	 */
	public void scaleFromMM()
	{
		scale(72 / 25.4);
	}

	/**
	 * scale all values of this to points from centimeters
	 * 
	 */
	public void scaleFromCM()
	{
		scale(72 / 2.54);
	}

	/**
	 * scale all values of this to points from millimeters
	 * 
	 */
	public void scaleToMM()
	{
		scale(25.4 / 72);
	}

	/**
	 * scale all values of this to points from centimeters
	 * 
	 */
	public void scaleToCM()
	{
		scale(2.54 / 72);
	}

	/**
	 * scale all values of this by factor
	 * 
	 * @param factor
	 */
	public void scale(final double factor)
	{
		final int size = m_numList.size();
		for (int i = 0; i < size; i++)
		{
			final double number = doubleAt(i) * factor;
			m_numList.setElementAt(new Double(number), i);
		}
	}

	/**
	 * return true if this contains the Double or Integer object o
	 * 
	 * @param o
	 * @return
	 */
	public boolean contains(final Object o)
	{
		return m_numList.contains(o);
	}

	/**
	 * return true if this contains the Double or Integer object o
	 * 
	 * @param l
	 * @return
	 */
	public boolean contains(final JDFNumList l)
	{
		if (l == null)
		{
			return false;
		}
		for (int i = 0; i < l.size(); i++)
		{
			if (contains(l.elementAt(i)))
			{
				return true;
			}
		}
		return false;
	}

}
