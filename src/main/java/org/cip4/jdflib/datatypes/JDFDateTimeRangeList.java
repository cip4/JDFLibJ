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
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * @author Elena Skobchenko
 *
 *         JDFDateTimeRangeList.java
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.ArrayList;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;

/**
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFDateTimeRangeList extends JDFRangeList
{
	// **************************************** Attributes
	// ******************************************

	// **************************************** Constructors
	// ****************************************

	/**
	 * empty constructor
	 */
	public JDFDateTimeRangeList()
	{
		super();
	}

	/**
	 * constructs a JDFDateTimeRangeList from a given string
	 *
	 * @param s the given string
	 *
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFDateTimeRangeList(final String s) throws DataFormatException
	{
		if (s != null && !s.equals(JDFCoreConstants.EMPTYSTRING))
		{
			setString(s);
		}
	}

	/**
	 * factory for JDFDateTimeRangeList that silently returns null in case of illegal strings
	 *
	 * @param s the string to parse
	 * @return the JDFDateTimeRangeList, null if s is not compatible
	 */
	public static JDFDateTimeRangeList createDateTimeRangeList(final String s)
	{
		if (s != null && s.length() > 0)
		{
			try
			{
				return new JDFDateTimeRangeList(s);
			}
			catch (final DataFormatException x)
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
	 * constructs a JDFDateTimeRangeList from the given JDFDateTimeRangeList
	 *
	 * @param rl the given JDFDateTimeRangeList
	 */
	public JDFDateTimeRangeList(final JDFDateTimeRangeList rl)
	{
		rangeList = new ArrayList<>(rl.rangeList);
	}

	// **************************************** Methods
	// *********************************************

	/**
	 * inRange - returns true if the given JDFDate value is in one of the ranges of the range list
	 *
	 * @param x the given JDFDate (duration) value to compare
	 *
	 * @return boolean - true if in range otherwise false
	 */
	public boolean inRange(final JDFDate x)
	{
		final int sz = rangeList.size();
		for (int i = 0; i < sz; i++)
		{
			final JDFDateTimeRange r = (JDFDateTimeRange) rangeList.get(i);

			if (r.inRange(x))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * setString - parse the given string and set the duration range list
	 *
	 * @param s the given string
	 *
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public void setString(final String s) throws DataFormatException
	{
		if (s.indexOf(JDFConstants.TILDE) == 0 || s.lastIndexOf(JDFConstants.TILDE) == (s.length() - 1))
			throw new DataFormatException("JDFDateTimeRangeList::SetString: Illegal string " + s);
		final String zappedWS = StringUtil.zappTokenWS(s, "~");
		final StringArray vs = StringArray.getVString(zappedWS, " \t");
		rangeList.clear();
		for (final String str : vs)
		{
			try
			{
				final JDFDateTimeRange dr = new JDFDateTimeRange(str);
				rangeList.add(dr);
			}
			catch (final DataFormatException dfe)
			{
				throw new DataFormatException("JDFDateTimeRangeList::SetString: Illegal string " + s);
			}
		}
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
			new JDFDateTimeRangeList(s);
		}
		catch (final DataFormatException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * Add a Duration range r =rMin~rMax
	 *
	 * @param r the Duration range to append to the list
	 */
	public void append(final JDFDateTimeRange r)
	{
		rangeList.add(r);
	}

	/**
	 * Add a Duration range defined by two dates xMin~xMax
	 *
	 * @param xMin the left value of the Duration range to append to the list
	 * @param xMax the right value of the Duration range to append to the list
	 */
	public void append(final JDFDate xMin, final JDFDate xMax)
	{
		append(new JDFDateTimeRange(xMin, xMax));
	}

	/**
	 * Add an individual JDFDate element
	 *
	 * @param x the left and right value of the Duration range to append to the list
	 */
	public void append(final JDFDate x)
	{
		append(new JDFDateTimeRange(x, x));
	}

	/**
	 * isOrdered - tests if 'this' is an OrderedRangeList
	 *
	 * @return boolean - true if 'this' is an OrdneredRangeList
	 */
	@Override
	public boolean isOrdered()
	{
		final int siz = rangeList.size();
		if (siz == 0)
			return false; // attempt to operate on a null element

		final Vector<JDFDate> v = new Vector<>(); // vector of ranges
		for (int i = 0; i < siz; i++)
		{
			final JDFDateTimeRange r = (JDFDateTimeRange) rangeList.get(i);
			v.addElement(r.getLeft());
			if (!r.getLeft().equals(r.getRight()))
			{
				v.addElement(r.getRight());
			}
		}

		final int n = v.size() - 1;
		if (n == 0)
			return true; // single value

		final JDFDate first = (v.elementAt(0));
		final JDFDate last = (v.elementAt(n));

		for (int j = 0; j < n; j++)
		{
			final JDFDate value = (v.elementAt(j));
			final JDFDate nextvalue = (v.elementAt(j + 1));

			if (((first.equals(last) && value.equals(nextvalue)) || (first.isEarlier(last) && (value.isEarlier(nextvalue) || value.equals(nextvalue)))
					|| (first.isLater(last) && (value.isLater(nextvalue) || value.equals(nextvalue)))) == false)
				return false;
		}
		return true;
	}

	/**
	 * isUniqueOrdered - tests if 'this' is an UniqueOrdered RangeList
	 *
	 * @return boolean - true if 'this' is an UniqueOrdered RangeList
	 */
	@Override
	public boolean isUniqueOrdered()
	{

		final int siz = rangeList.size();
		if (siz == 0)
		{
			return false; // attempt to operate on a null element
		}

		final Vector<JDFDate> v = new Vector<>(); // vector of ranges
		for (int i = 0; i < siz; i++)
		{
			final JDFDateTimeRange r = (JDFDateTimeRange) rangeList.get(i);
			v.addElement(r.getLeft());
			if (!r.getLeft().equals(r.getRight()))
			{
				v.addElement(r.getRight());
			}
		}

		final int n = v.size() - 1;
		if (n == 0)
		{
			return true; // single value
		}
		final JDFDate first = v.elementAt(0);
		final JDFDate last = v.elementAt(n);

		if (first.equals(last))
		{
			return false;
		}
		for (int j = 0; j < n; j++)
		{
			final JDFDate value = v.elementAt(j);
			final JDFDate nextvalue = v.elementAt(j + 1);

			if (((first.isEarlier(last) && value.isEarlier(nextvalue)) || (first.isLater(last) && value.isLater(nextvalue))) == false)
				return false;
		}
		return true;
	}

}
