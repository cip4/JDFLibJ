/*--------------------------------------------------------------------------------------------------
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
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFTransferFunction.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;

/**
 * This class is a representation of a whitespace separated list of numbers representing a set of XY coordinates of a transfer function. The total number of x y
 * values must be even because of the pairs.
 */
public class JDFTransferFunction extends JDFNumList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// **************************************** Constructors
	// ****************************************
	/**
	 * constructs a xy pair with all values set to 0.0 Double
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFTransferFunction() throws DataFormatException
	{
		super(JDFConstants.EMPTYSTRING);
	}

	/**
	 * constructs a number list with the given string the number of tokens must be even
	 * 
	 * @param s the given String in number list format
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFTransferFunction(final String s) throws DataFormatException
	{
		super(s);
	}

	/**
	 * constructs a number list with the given vector the number of elements must be even
	 * 
	 * @param v the number list as a vector
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 * @deprecated use typesafe constructors
	 */
	@Deprecated
	public JDFTransferFunction(final Vector v) throws DataFormatException
	{
		super(v);
	}

	/**
	 * constructs a number list with the given number list
	 * 
	 * @param nl the given number list
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFTransferFunction(final JDFNumList nl) throws DataFormatException
	{
		super(nl.toString());
	}

	/**
	 * copy constructor<br>
	 * constructs a number list with the given transfer function
	 * 
	 * @param tf the given number list
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFTransferFunction(final JDFTransferFunction tf)
	{
		super();
		addAll(tf);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * isValid - true if the size of the vector is even and all instances are Double types
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	@Override
	public boolean isValid() throws DataFormatException
	{
		if ((size() % 2) != 0)
		{
			throw new DataFormatException("Data format exception!");
		}

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
	 * add - adds a xy coordinate to the vector
	 * 
	 * @param xy the xy coordinate to add
	 */
	public void add(final JDFXYPair xy)
	{
		add(new Double(xy.getX()));
		add(new Double(xy.getY()));
	}

	/**
	 * add - adds a x and a y coordinate to the vector
	 * 
	 * @param x the x coordinate to add
	 * @param y the y coordinate to add
	 */
	public void add(final Double x, final Double y)
	{
		add(x);
		add(y);
	}

	/**
	 * add - adds a x and a y coordinate to the vector
	 * 
	 * @param x the x coordinate to add
	 * @param y the y coordinate to add
	 */
	public void add(final double x, final double y)
	{
		add(new Double(x));
		add(new Double(y));
	}

	/**
	 * add - adds a x and a y coordinate to the vector
	 * 
	 * @param s a string with the x and y coordinate to add
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public void add(final String s) throws DataFormatException
	{
		final StringTokenizer sToken = new StringTokenizer(s, JDFConstants.BLANK);

		if ((sToken.countTokens() % 2) != 0)
		{
			throw new DataFormatException("Data format exception!");
		}

		while (sToken.hasMoreTokens())
		{
			final String t = sToken.nextToken().trim();

			try
			{
				addElement(new Double(t));
			}
			catch (final NumberFormatException e)
			{
				throw new DataFormatException("Data format exception!");
			}
		}
	}

	/**
	 * add - adds a complete transfer function to the vector
	 * 
	 * @param tf the given transfer function to add
	 */
	public void add(final JDFTransferFunction tf)
	{
		addAll(tf);
	}
}