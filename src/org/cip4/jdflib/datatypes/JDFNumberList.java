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

/**
 * This class is a representation of a number list (JDFNumberList). It is a whitespace separated list of double values.
 */
public class JDFNumberList extends JDFNumList
{
	// **************************************** Constructors
	// ****************************************
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
	 * constructs a number list with the given vector
	 * 
	 * @param v the given vector
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
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
	 * add - add a double value to the vector
	 * 
	 * @param x the double value
	 */
	public void add(final double x)
	{
		m_numList.add(new Double(x));
	}

	/**
	 * add - adds a Double object to the vector
	 * 
	 * @param x the Double object
	 */
	public void add(final Double x)
	{
		m_numList.add(x);
	}

	/**
	 * add - adds a complete number list to the vector
	 * 
	 * @param nl the given JDFNumberList
	 */
	public void add(final JDFNumberList nl)
	{
		m_numList.addAll(nl.copyNumList());
	}

	/**
	 * add - adds a number list to the already existing number list
	 * 
	 * @param s the given string
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public void add(final String s)
	{
		final StringTokenizer sToken = new StringTokenizer(s, JDFConstants.BLANK);

		while (sToken.hasMoreTokens())
		{
			final String t = sToken.nextToken().trim();
			m_numList.addElement(new Double(t));
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
		return contains(new Double(d));
	}

}
