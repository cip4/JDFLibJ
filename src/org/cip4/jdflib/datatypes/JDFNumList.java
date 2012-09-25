/*--------------------------------------------------------------------------------------------------
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

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.StringUtil;

/**
 * This abstract class is the representation of a number list (Integer and Double object). Intern these objects are collected in a vector and there are several
 * methods to provide an access to the data.
 */
public abstract class JDFNumList extends Vector<Object> implements JDFBaseDataTypes, Cloneable
{
	// **************************************** Constructors
	// ****************************************
	/**
	 * constructs an empty number list
	 */
	public JDFNumList()
	{
		super();// default super constructor
	}

	/**
	 * constructor - constructs a number list with the given size and sets all values set to 0.0 Double
	 * 
	 * @param size the given size
	 */
	public JDFNumList(final int size)
	{
		super(size);
		for (int i = 0; i < size; i++)
		{
			addElement(new Double(0.0));
		}
	}

	/**
	 * constructor - constructs a number list with the given vector
	 * 
	 * @param v a vector with number list objects
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 * @deprecated use typesafe constructors
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public JDFNumList(final Vector v) throws DataFormatException
	{
		super();
		addAll(v);
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
		super();
		setString(sl);
	}

	/**
	 * 
	 *  
	 * @param pos
	 * @param d
	 */
	public void set(int pos, double d)
	{
		super.set(pos, new Double(d));
	}

	/**
	 * 
	 * sets this to the value specified in string
	 * @param string
	 * @throws DataFormatException
	 */
	public void setString(final String string) throws DataFormatException
	{
		clear();
		if (string != null && string.length() > 0)
		{
			final VString v = StringUtil.tokenize(string, null, false);
			if (v != null)
			{
				final int size = v.size();
				boolean bInteger = this instanceof JDFIntegerList;
				int minValue = Integer.MIN_VALUE + 42; // a bit off but rare...
				for (int i = 0; i < size; i++)
				{
					final String s = v.stringAt(i);
					if (bInteger)
					{
						int theInt = StringUtil.parseInt(s, minValue);
						if (theInt == minValue)
							throw new DataFormatException("JDFNumList: bad numeric value: " + s);
						addElement(new Integer(theInt));
					}
					else
					{
						double theDouble = StringUtil.parseDouble(s, Double.NaN);
						if (Double.isNaN(theDouble))
							throw new DataFormatException("JDFNumList: bad numeric value: " + s);
						addElement(new Double(theDouble));
					}
				}
			}
			isValid();
		}
		else
		{
			throw new DataFormatException("JDFNumList: bad string value: " + string);
		}
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
		super();
		addAll(nl);
		isValid();
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
	 * 
	 * get the list of values as doubles
	 * @return
	 */
	public double[] getDoubleList()
	{
		double[] list = new double[size()];
		for (int i = 0; i < size(); i++)
			list[i] = doubleAt(i);
		return list;
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

		for (int i = 0; i < size(); i++)
		{
			if (i > 0)
			{
				sb.append(JDFConstants.BLANK);
			}

			final Object o = elementAt(i);
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
	 * getString - returns the JDFNumList as a String
	 * @param precision # of digits to print
	 * @return String - the JDFNumList as a String
	 */
	public String getString(int precision)
	{
		final StringBuffer sb = new StringBuffer();

		for (int i = 0; i < size(); i++)
		{
			if (i > 0)
			{
				sb.append(JDFConstants.BLANK);
			}

			final Object o = elementAt(i);
			if (o instanceof Double)
			{
				sb.append(StringUtil.formatDouble(((Double) o).doubleValue(), precision));
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
		return toString().hashCode();
	}

	/**
	 * getElementAt - returns the element at the ith position
	 * 
	 * @param i the index
	 * @return Object - the range object at the given position, null if i is out of range
	 */
	@Override
	public Object elementAt(int i)
	{
		if (i < 0)
		{
			i = size() + i;
		}

		if (i < 0 || i >= size())
		{
			return null;
		}
		return super.elementAt(i);
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
	 * @deprecated use clone()
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	public Vector<Object> copyNumList()
	{
		return clone();
	}

	/**
	 * removeElementAt - removes the element at the given position
	 * 
	 * @param i the position from where to remove the element
	 * 
	 */
	@Override
	public void removeElementAt(int i)
	{
		if (i < 0)
		{
			i += size();
		}
		if ((i < size()) && (i >= 0))
		{
			super.removeElementAt(i);
		}
	}

	/**
	 * replaceElementAt - replaces the element at the given position with the given object
	 * 
	 * @param obj the object
	 * @param i the given position
	 * @return boolean - true if successful otherwise false
	 */
	public boolean replaceElementAt(final Object obj, int i)
	{
		if (i < 0)
		{
			i += size();
		}

		if ((i < size()) && (i >= 0))
		{
			set(i, obj);
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
	 * @return 
	 * 
	 */
	public JDFNumList scaleFromMM()
	{
		return scale(72 / 25.4);
	}

	/**
	 * scale all values of this to points from centimeters
	 * @return 
	 * 
	 */
	public JDFNumList scaleFromCM()
	{
		return scale(72 / 2.54);
	}

	/**
	 * scale all values of this to points from millimeters
	 * @return 
	 * 
	 */
	public JDFNumList scaleToMM()
	{
		return scale(25.4 / 72);
	}

	/**
	 * scale all values of this to points from centimeters
	 * @return 
	 * 
	 */
	public JDFNumList scaleToCM()
	{
		return scale(2.54 / 72);
	}

	/**
	 * scale all values of this by factor
	 * 
	 * @param factor
	 * @return 
	 */
	public JDFNumList scale(final double factor)
	{
		final int size = size();
		for (int i = 0; i < size; i++)
		{
			final double number = doubleAt(i) * factor;
			setElementAt(new Double(number), i);
		}
		return this;
	}

	/**
	 * modify numlist to absolute values
	 * @see Math#abs
	 * @return 
	 */
	public JDFNumList abs()
	{
		final int size = size();
		for (int i = 0; i < size; i++)
		{
			final double number = Math.abs(doubleAt(i));
			setElementAt(new Double(number), i);
		}
		return this;
	}

	/**
	 * 
	 * @see java.util.Vector#clone()
	 */
	@Override
	public JDFNumList clone()
	{
		return (JDFNumList) super.clone();
	}

	/**
	 * are all values within +/- delta?
	 * @param other 
	 * @param delta 
	 * @see Math#abs
	 * @return 
	 */
	public boolean matches(JDFNumList other, double delta)
	{
		if (other == null)
			return false;
		if (size() != other.size())
			return false;
		JDFNumList dif;
		dif = clone();
		dif.subtract(other);
		dif.abs();
		for (int i = 0; i < size(); i++)
		{
			if (dif.doubleAt(i) > delta)
				return false;
		}
		return true;
	}

	/**
	 * subtract l from this, 
	 * @param l the list to subtract from this
	 * @throws IllegalArgumentException if sizes don't match
	 */
	public void subtract(JDFNumList l)
	{
		if (l == null || size() != l.size())
			return;

		double[] me = getDoubleList();
		double[] them = l.getDoubleList();
		for (int i = 0; i < me.length; i++)
		{
			me[i] -= them[i];
			setElementAt(new Double(me[i]), i);
		}
	}

	/**
	 * ensure that each instance exists only once
	 *  
	 *  
	 */
	public void unify()
	{
		Set<Object> set = new HashSet<Object>();
		int j = 0;
		for (int i = 0; i < size(); i++)
		{
			if (set.contains(elementAt(j)))
			{
				remove(j);
			}
			else
			{
				set.add(elementAt(j));
				j++;
			}
		}
	}

	/**
	 * getIntArray - returns this integer list as an int array
	 * 
	 * @return int[] - the int array
	 */
	public int[] getIntArray()
	{
		final int size = size();
		final int[] intArray = new int[size];

		for (int i = 0; i < size; i++)
		{
			intArray[i] = ((Double) elementAt(i)).intValue();
		}

		return intArray;
	}

	/**
	 * return true if this contains at least one element from l
	 * 
	 * @param l the list to check for 
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

	/**
	 * return true if this contains all elements from l
	 * 
	 * @param l the list to check for 
	 * @return 
	 */
	public boolean containsAll(final JDFNumList l)
	{
		if (l == null)
		{
			return true;
		}
		for (int i = 0; i < l.size(); i++)
		{
			if (!contains(l.elementAt(i)))
			{
				return false;
			}
		}
		return true;
	}

}
