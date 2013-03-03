/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of
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
 * JDFNameRangeList.java
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class is a representation of a name range list (JDFNameRangeList). It is a whitespace separated list of name
 * ranges, for example "anna~berta hans~otto"
 */
public class JDFNameRangeList extends JDFRangeList
{
	/**
	 * factory for JDFNameRangeList that silently returns null in case of illegal strings
	 * @param s the string to parse
	 * @return the JDFNameRangeList, null if s is not compatible
	 */
	public static JDFNameRangeList createNameRangeList(String s)
	{
		if (s != null && s.length() > 0)
		{
			try
			{
				return new JDFNameRangeList(s);
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
	 * constructs an empty JDFNameRangeList
	 */
	public JDFNameRangeList()
	{
		super();
	}

	/**
	 * constructs a JDFNameRangeList from a given string
	 * 
	 * @param s the given string
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFNameRangeList(String s) throws DataFormatException
	{
		if (s != null && !s.equals(JDFConstants.EMPTYSTRING))
		{
			setString(s);
		}
	}

	/**
	 * constructs a JDFNameRangeList from the given JDFNameRangeList
	 * 
	 * @param rl the given JDFNameRangeList
	 */
	public JDFNameRangeList(JDFNameRangeList rl)
	{
		rangeList = new Vector(rl.rangeList);
	}

	// **************************************** Methods
	// *********************************************

	/**
	 * setString - parse the string and separate all single ranges
	 * 
	 * @param s the given string
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public void setString(String s) throws DataFormatException
	{
		if (s.indexOf(JDFConstants.TILDE) == 0 || s.lastIndexOf(JDFConstants.TILDE) == (s.length() - 1))
			throw new DataFormatException("JDFNameRangeList::SetString: Illegal string " + s);
		String zappedWS = StringUtil.zappTokenWS(s, "~");
		VString v = StringUtil.tokenize(zappedWS, " \t", false);
		VString vs = new VString(v);
		rangeList.clear();
		for (int i = 0; i < vs.size(); i++)
		{
			String str = vs.elementAt(i);
			JDFNameRange r = new JDFNameRange(str);
			rangeList.addElement(r);
		}
	}

	/**
	 * inRange - returns true if the given string is in range with one of the ranges in the range list (<code
	 * 
	 * @param x the given string
	 * 
	 * @return boolean - true if in range, otherwise false
	 */
	public boolean inRange(String x)
	{
		for (int i = 0; i < rangeList.size(); i++)
		{
			if (((JDFNameRange) rangeList.elementAt(i)).inRange(x))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * append - appends a name range to the range list
	 * 
	 * @param r the given name range
	 */
	public void append(JDFNameRange r)
	{
		rangeList.addElement(r);
	}

	@Override
	public boolean isUniqueOrdered()
	{
		return false;
	}

	@Override
	public boolean isOrdered()
	{
		return false;
	}

}
