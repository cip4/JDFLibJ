/*
 *
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
 * 
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFLabColor.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

/**
 * This class is a representation of a Lab color (JDFLabColor). It is a blank separated list of double values consisting of L, a and b value.
 */
public class JDFLabColor extends JDFNumList
{
	// **************************************** Constructors
	// ****************************************
	/**
	 * constructs a Lab color with all values set to 0.0 Double
	 */
	public JDFLabColor()
	{
		super(MAX_LAB_COLOR);
	}

	/**
	 * constructs a Lab color with all values set via a Vector of Double objects
	 * 
	 * @param v the given vector
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 * @deprecated use typesafe constructors
	 */
	@Deprecated
	public JDFLabColor(final Vector v) throws DataFormatException
	{
		super(v);
	}

	/**
	 * constructs a Lab color with all values set via a String
	 * 
	 * @param s the given String
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFLabColor(final String s) throws DataFormatException
	{
		super(s);
	}

	/**
	 * constructs a Lab color with all values set via a JDFLabColor
	 * 
	 * @param lab the given Lab colors
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFLabColor(final JDFLabColor lab) throws DataFormatException
	{
		this(lab.toString());
	}

	/**
	 * constructs a Lab color with all values set via a JDFNumberList
	 * 
	 * @param nl the given number list
	 * 
	 * @throws DataFormatException - if the String does not have a valid format
	 */
	public JDFLabColor(final JDFNumberList nl) throws DataFormatException
	{
		this(nl.toString());
	}

	/**
	 * constructs a new Lab color with the given double values
	 * 
	 * @param l the value L
	 * @param a the value a
	 * @param b the value b
	 */
	public JDFLabColor(final double l, final double a, final double b)
	{
		super(MAX_LAB_COLOR);
		m_numList.set(0, new Double(l));
		m_numList.set(1, new Double(a));
		m_numList.set(2, new Double(b));
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * isValid - true if the size of the vector is 3 and all objects are Double types
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	@Override
	public boolean isValid() throws DataFormatException
	{
		if (m_numList.size() != MAX_LAB_COLOR)
		{
			throw new DataFormatException("Data format exception!");
		}

		for (int i = 0; i < m_numList.size(); i++)
		{
			if (!(m_numList.elementAt(i) instanceof Double))
			{
				throw new DataFormatException("Data format exception!");
			}
		}
		return true;
	}

	/**
	 * getL - returns the value L of the Lab color
	 * 
	 * @return double - the value L of the Lab color
	 */
	public double getL()
	{
		return ((Double) m_numList.elementAt(0)).doubleValue();
	}

	/**
	 * setL - sets the value L of the Lab color
	 * 
	 * @param l the value L of the Lab color
	 */
	public void setL(final double l)
	{
		m_numList.set(0, new Double(l));
	}

	/**
	 * getA - returns the value a of the Lab color
	 * 
	 * @return double - the value a of the Lab color
	 */
	public double getA()
	{
		return ((Double) m_numList.elementAt(1)).doubleValue();
	}

	/**
	 * setA - sets the value a of the Lab color
	 * 
	 * @param a the value a of the Lab color
	 */
	public void setA(final double a)
	{
		m_numList.set(1, new Double(a));
	}

	/**
	 * getB - returns the value b of the Lab color
	 * 
	 * @return double - the value b of the Lab color
	 */
	public double getB()
	{
		return ((Double) m_numList.elementAt(2)).doubleValue();
	}

	/**
	 * setB - sets the value b of the Lab color
	 * 
	 * @param b the value b of the Lab color
	 */
	public void setB(final double b)
	{
		m_numList.set(2, new Double(b));
	}
}