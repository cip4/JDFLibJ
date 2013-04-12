/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of 
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
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 * 
 * @author Elena Skobchenko
 *
 * JDFXYPairRangeList.java
 *
 */

package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class is a representation of a xy pair range list (JDFXYPairRangeList). It is a whitespace separated list of xy
 * pair ranges, for example "1 2~5 8 10.25 10.25~44.55 55.34"
 */
public class JDFXYPairRangeList extends JDFRangeList
{
	/**
	 * factory for JDFXYPairRangeList that silently returns null in case of illegal strings
	 * @param s the string to parse
	 * @return the JDFXYPairRangeList, null if s is not compatible
	 */
	public static JDFXYPairRangeList createXYPairRangeList(String s)
	{
		if (s != null && s.length() > 0)
		{
			try
			{
				return new JDFXYPairRangeList(s);
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
	 * constructs an empty range list
	 */
	public JDFXYPairRangeList()
	{
		// default super constructor
	}

	/**
	 * copy constructor<br>
	 * constructs a JDFXYPairRangeList with the given JDFXYPairRangeList
	 * 
	 * @param rl the JDFXYPairRangeList to copy
	 */
	public JDFXYPairRangeList(JDFXYPairRangeList rl)
	{
		rangeList = new Vector(rl.rangeList);
	}

	/**
	 * constructs a JDFXYPairRangeList with the given JDFXYPairRange
	 * 
	 * @param r the given JDFXYPairRange
	 */
	public JDFXYPairRangeList(JDFXYPairRange r)
	{
		super();
		append(r);
	}

	/**
	 * constructs a JDFXYPairRangeList with values from a given string
	 * 
	 * @param s the given string
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFXYPairRangeList(String s) throws DataFormatException
	{
		if (s != null && !s.equals(JDFConstants.EMPTYSTRING))
		{
			setString(s);
		}
	}

	// **************************************** Methods
	// *********************************************

	/**
	 * inRange - returns true if the given JDFXYPair value is in one of the ranges of this range list
	 * 
	 * @param x the given double value to compare
	 * 
	 * @return boolean - true if in range otherwise false
	 */
	public boolean inRange(JDFXYPair x)
	{
		int sz = rangeList.size();
		for (int i = 0; i < sz; i++)
		{
			JDFXYPairRange r = (JDFXYPairRange) rangeList.elementAt(i);

			if (r.inRange(x))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * setString<br>
	 * Parse the string and set the single ranges or pairs and put them into a vector.<br>
	 * The first and the last positions in the vector are special, because they contain only a half range: the first can
	 * start with a pair and the last can end with a pair. The elements in the middle (position 2 - (n-1)) start and end
	 * with a half range, but can have pairs in the middle.
	 *<p>
	 * For example, if the string looks like this: * "1 2 ~ 4 5 6 7 ~ 8 9 10 11 ~ 1 1" <br>
	 * it is the representation of 3 ranges:<br>
	 * range 1: "1 2 ~ 4 5", range 2: "6 7 ~ 8 9" and range 3: "10 11 ~ 1 1"
	 * 
	 * @param s the given string to cut in seperate xy pair ranges
	 * 
	 * @throws DataFormatException
	 */
	public void setString(String s) throws DataFormatException
	{
		if (s.indexOf(JDFConstants.TILDE) == 0 || s.lastIndexOf(JDFConstants.TILDE) == (s.length() - 1))
			throw new DataFormatException("JDFXYPairRangeList::SetString: Illegal string " + s);
		String zappedWS = StringUtil.zappTokenWS(s, JDFConstants.TILDE);
		VString vs = new VString(zappedWS, JDFConstants.BLANK);
		rangeList.clear();
		for (int i = 0, size = vs.size(); i < size; i++)
		{
			if (size - i < MAX_XY_DIMENSION) // the last xypair is incomplete
				throw new DataFormatException("JDFXYPairRangeList::SetString: Illegal string " + s);
			String tok1 = vs.elementAt(i);
			String tok2 = vs.elementAt(++i);
			String str = tok1 + JDFConstants.BLANK + tok2;
			if (tok2.indexOf(JDFConstants.TILDE) != -1)
			{
				str = tok1 + JDFConstants.BLANK + tok2 + JDFConstants.BLANK + vs.elementAt(++i);
			}
			try
			{
				JDFXYPairRange r = new JDFXYPairRange(str);
				rangeList.addElement(r);
			}
			catch (DataFormatException dfe)
			{
				throw new DataFormatException("JDFXYPairRangeList::SetString: Illegal string " + s);
			}
		}
	}

	/**
	 * setString Parse the string and set the single ranges or pairs and put them into a vector. The first and the last
	 * positions in the vector are special because they contain only a half range, the first can start with a pair and
	 * the last can end with a pair, the elements in the middle (Position 2 - (n-1)) starts and ends with a half range
	 * but can have pairs in the middle.
	 * 
	 * For example, if the string looks like
	 * 
	 * "1 2 ~ 4 5 6 7 ~ 8 9 10 11 ~ 1 1" it is the representation of 3 ranges range 1: "1 2 ~ 4 5" range 2: "6 7 ~ 8 9"
	 * and range 3: "10 11 ~ 1 1"
	 * 
	 * @param String s - the given string to cut in seperate xy pair ranges
	 * 
	 * @return boolean - true if the string is has the right format otherwise false
	 */
	// public boolean setString(String s)
	// {
	// /**
	// * lets see what happens to the following example string
	// "1 2 3 4 ~ 5 6 7 8 9 1~2 3 4 5"
	// *
	// * this is a representation of 3 ranges,
	// * range 1: "1 2", range2: "3 4~5 6", range 3: "7 8", range 4: "9 1~2 3"
	// and range 5: "4 5"
	// *
	// * first we cut at the tilde "~" position and put the pieces into a vector
	// */
	// String str = JDFConstants.EMPTYSTRING;
	// Vector vs = new Vector();
	// StringTokenizer tildeTokens = new StringTokenizer(s, "~");
	//  
	// while (tildeTokens.hasMoreTokens())
	// {
	// str = tildeTokens.nextToken();
	// str = str.trim();
	// vs.addElement(str);
	// }
	//  
	// /**
	// * now we have a vector that looks like
	// * 1.element "1 2 3 4"
	// * 2.element "5 6 78 91"
	// * 3.element "2 3 4 5"
	// *
	// * the first and the last positions are special because they contain only
	// a half range, the
	// * first can start with a pair and the last can end with a pair, the
	// elements in the middle
	// * (Position 1 - (n-1)) starts and ends with a half range but can have
	// pairs in the middle
	// */
	// StringBuffer aRange = new StringBuffer(8);
	//  
	// for (int vpos = 0; vpos < vs.size(); vpos++) // vpos is the position in
	// the vector
	// {
	// StringTokenizer blankTokens = new
	// StringTokenizer((String)vs.elementAt(vpos), JDFConstants.BLANK);
	// int nTokens = blankTokens.countTokens();
	// int tpos = 0; // tpos is the tokenizer position in the string
	//  
	// if ((nTokens % 2) != 0)
	// {
	// return false;
	// }
	//  
	// while (blankTokens.hasMoreTokens())
	// {
	// str = blankTokens.nextToken();
	// str = str.trim();
	//  
	// /**
	// * all elements in the middle of the vector position 1 - (n-1)
	// */
	// if ((vpos > 0) && (vpos < (vs.size()-1)))
	// {
	// /**
	// * the second x element of the first range,
	// * the first element in the string
	// */
	// if (tpos == 0)
	// {
	// aRange.append(str).append(JDFConstants.BLANK);
	// }
	// /**
	// * the second y element of the first range,
	// * the second element in the string
	// */
	// else if (tpos == 1)
	// {
	// aRange.append(str);
	//  
	// try
	// {
	// rangeList.addElement(new JDFXYPairRange(aRange.toString().trim()));
	// }
	// catch (DataFormatException de)
	// {
	// return false;
	// }
	// }
	// /**
	// * the first x element of the last range,
	// * the last but one element in the string
	// */
	// else if (tpos == (nTokens-2))
	// {
	// aRange = new StringBuffer(str).append(JDFConstants.BLANK);
	// }
	// /**
	// * the first y element of the last range,
	// * the last element in the string
	// */
	// else if (tpos == (nTokens-1))
	// {
	// aRange.append(str).append("~");
	// }
	// /**
	// * the first element of a pair,
	// * position 2, 4, 6... in the string
	// */
	// else if ((tpos % 2) == 0)
	// {
	// aRange = new StringBuffer(str).append(JDFConstants.BLANK);
	// }
	// /**
	// * the second element of a pair,
	// * position 3, 5, 7... in the string,
	// * now instanciate a JDFXYPair
	// */
	// else
	// {
	// aRange.append(str);
	//  
	// try
	// {
	// rangeList.addElement(new JDFXYPair(aRange.toString().trim()));
	// }
	// catch (DataFormatException de)
	// {
	// return false;
	// }
	// }
	// }
	// /**
	// * the last position in the vector begins with the second half of a range,
	// * but can end with pairs
	// */
	// else if ((vpos == (vs.size() - 1)) && (vs.size() > 1))
	// {
	// /**
	// * the second x element of the last range,
	// * the first element in the string
	// */
	// if (tpos == 0)
	// {
	// aRange.append(str).append(JDFConstants.BLANK);
	// }
	// /**
	// * the second y element of the last range,
	// * the second element in the string
	// */
	// else if (tpos == 1)
	// {
	// aRange.append(str);
	//  
	// try
	// {
	// rangeList.addElement(new JDFXYPairRange(aRange.toString().trim()));
	// }
	// catch (DataFormatException de)
	// {
	// return false;
	// }
	// }
	// /**
	// * the first element of a pair,
	// * position 2, 4, 6... in the string
	// */
	// else if ((tpos % 2) == 0)
	// {
	// aRange = new StringBuffer(str).append(JDFConstants.BLANK);
	// }
	// /**
	// * the second element of a pair,
	// * position 3, 5, 7... in the string,
	// * now instanciate a JDFXYPair
	// */
	// else
	// {
	// aRange.append(str);
	//  
	// try
	// {
	// rangeList.addElement(new JDFXYPair(aRange.toString().trim()));
	// }
	// catch (DataFormatException de)
	// {
	// return false;
	// }
	// }
	// }
	// /**
	// * the first position in the vector ends with the first half of a range,
	// * but it can start with pairs
	// */
	// else
	// {
	// /**
	// * the first x element of the first range,
	// * the last but one element in the string
	// */
	// if (tpos == (nTokens-2))
	// {
	// aRange = new StringBuffer(str).append(JDFConstants.BLANK);
	// }
	// /**
	// * the first y element of the first range,
	// * the last element in the string
	// */
	// else if ((tpos == (nTokens-1)) && (vs.size() > 1))
	// {
	// aRange.append(str).append("~");
	// }
	// /**
	// * the first element of a pair,
	// * position 0, 2, 4, 6... in the string
	// */
	// else if ((tpos == 0) || ((tpos % 2) == 0))
	// {
	// aRange = new StringBuffer(str).append(JDFConstants.BLANK);
	// }
	// /**
	// * the second element of a pair,
	// * position 1, 3, 5... in the string,
	// * now instanciate a JDFXYPair
	// */
	// else
	// {
	// aRange.append(str);
	//  
	// try
	// {
	// rangeList.addElement(new JDFXYPair(aRange.toString().trim()));
	// }
	// catch (DataFormatException de)
	// {
	// return false;
	// }
	// }//else
	// }//else
	//  
	// tpos++;
	// }//while
	// }//for
	//  
	// return true;
	// }
	/**
	 * isValid - validate the given String
	 * 
	 * @param s the given string
	 * 
	 * @return boolean - false if the String has not a valid format
	 */
	public boolean isValid(String s)
	{
		try
		{
			new JDFXYPairRangeList(s);
		}
		catch (DataFormatException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * append - appends a JDFXYPairRange to this number range
	 * 
	 * @param r the JDFXYPairRange range to append
	 */
	public void append(JDFXYPairRange r)
	{
		rangeList.addElement(r);
	}

	/**
	 * append - appends a new range to the range list
	 * 
	 * @param xMin the min value of the new range
	 * @param xMax the max value of the new range
	 */
	public void append(JDFXYPair xMin, JDFXYPair xMax)
	{
		this.append(new JDFXYPairRange(xMin, xMax));
	}

	/**
	 * append - appends a new range to the range list, based on a single JDFXYPair
	 * 
	 * @param x both the min and the max value of the new range
	 */
	public void append(JDFXYPair x)
	{
		this.append(new JDFXYPairRange(x, x));
	}

	/**
	 * isOrdered - tests if 'this' is OrderedRangeList
	 * 
	 * @return boolean - true if 'this' is a OrdneredRangeList
	 */
	@Override
	public boolean isOrdered()
	{
		int siz = rangeList.size();
		if (siz == 0)
			return false; // attempt to operate on a null element

		Vector<JDFXYPair> v = new Vector<JDFXYPair>(); // vector of ranges
		for (int i = 0; i < siz; i++)
		{
			JDFXYPairRange r = (JDFXYPairRange) rangeList.elementAt(i);
			v.addElement(r.getLeft());
			if (!r.getLeft().equals(r.getRight()))
			{
				v.addElement(r.getRight());
			}
		}

		int n = v.size() - 1;
		if (n == 0)
			return true; // single value

		JDFXYPair first = (v.elementAt(0));
		JDFXYPair last = (v.elementAt(n));

		for (int j = 0; j < n; j++)
		{
			JDFXYPair value = (v.elementAt(j));
			JDFXYPair nextvalue = (v.elementAt(j + 1));

			if (((first.equals(last) && value.equals(nextvalue)) || (first.isLess(last) && value.isLessOrEqual(nextvalue)) || (first.isGreater(last) && value.isGreaterOrEqual(nextvalue))) == false)
				return false;
		}
		return true;
	}

	/**
	 * isUniqueOrdered - tests if 'this' is UniqueOrdered RangeList
	 * 
	 * @return boolean - true if 'this' is UniqueOrdered RangeList
	 */
	@Override
	public boolean isUniqueOrdered()
	{

		int siz = rangeList.size();
		if (siz == 0)
		{
			return false; // attempt to operate on a null element
		}

		Vector<JDFXYPair> v = new Vector<JDFXYPair>(); // vector of ranges
		for (int i = 0; i < siz; i++)
		{
			JDFXYPairRange r = (JDFXYPairRange) rangeList.elementAt(i);
			v.addElement(r.getLeft());
			if (!r.getLeft().equals(r.getRight()))
			{
				v.addElement(r.getRight());
			}
		}

		int n = v.size() - 1;
		if (n == 0)
		{
			return true; // single value
		}
		JDFXYPair first = v.elementAt(0);
		JDFXYPair last = v.elementAt(n);

		if (first.equals(last))
		{
			return false;
		}
		for (int j = 0; j < n; j++)
		{
			JDFXYPair value = v.elementAt(j);
			JDFXYPair nextvalue = v.elementAt(j + 1);

			if (((first.isLess(last) && value.isLess(nextvalue)) || (first.isGreater(last) && value.isGreater(nextvalue))) == false)
				return false;
		}
		return true;
	}

}