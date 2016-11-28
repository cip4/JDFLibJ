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
 * JDFNumberList.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class is a representation of a number list (JDFNumberList). It is a whitespace separated list of double values.
 */
public class JDFNumberList extends JDFNumList
{
	public JDFNumberList()
	{
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * constructs a number list with the given string
	 * 
	 * @param s the given String
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFNumberList(final String s) throws DataFormatException
	{
		super(s);
	}

	/**
	 * factory for JDFNumberList that silently returns null in case of illegal strings
	 * @param s the string to parse
	 * @return the JDFNumberList, null if s is not compatible
	 */
	public static JDFNumberList createNumberList(String s)
	{
		if (s != null && s.length() > 0)
		{
			try
			{
				return new JDFNumberList(s);
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
	 * constructs a number list with the given vector
	 * 
	 * @param v the given vector
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 * @deprecated use typesafe constructors
	 */
	@Deprecated
	public JDFNumberList(final Vector v) throws DataFormatException
	{
		super(v);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * isValid - true if all instances are Double types
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	@Override
	public boolean isValid() throws DataFormatException
	{
		for (Object o : this)
		{
			if (!(o instanceof Double))
			{
				throw new DataFormatException("Data format exception!");
			}
		}
		return true;
	}

	/**
	 * add - add a double value to the vector
	 * 
	 * @param x the double value
	 */
	public void add(final double x)
	{
		add(Double.valueOf(x));
	}

	/**
	 * add - adds a number list to the already existing number list
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
			final String t = sToken.nextToken();
			if (StringUtil.isNumber(t))
				add(StringUtil.parseDouble(t, 0.0));
			else
				throw new DataFormatException("illegal double: " + t);
		}
	}

	/**
	 * return true if at least one value in the list is d
	 * 
	 * @param d the value to search
	 * @return
	 */
	public boolean contains(final double d)
	{
		return contains(Double.valueOf(d));
	}

}
