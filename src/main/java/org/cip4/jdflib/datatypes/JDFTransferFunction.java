/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of
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

import java.util.Collection;
import java.util.Vector;
import java.util.zip.DataFormatException;

/**
 * This class is a representation of a whitespace separated list of numbers representing a set of XY coordinates of a transfer function. The total number of x y values must be even because of the
 * pairs.
 */
public class JDFTransferFunction extends JDFNumList
{
	/**
	 * factory for JDFTransferFunction that silently returns null in case of illegal strings
	 *
	 * @param s the string to parse
	 * @return the JDFTransferFunction, null if s is not compatible
	 */
	public static JDFTransferFunction createTransferFunction(final String s)
	{
		if (s != null && s.length() > 0)
		{
			try
			{
				return new JDFTransferFunction(s);
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
	 *
	 */
	private static final long serialVersionUID = 1L;
	private double[] cache;

	/**
	 * constructs a xy pair with all values set to 0.0 Double
	 *
	 */
	public JDFTransferFunction()
	{
		super();
		cache = null;
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
		cache = null;
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
		cache = null;
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
		cache = null;
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
		cache = tf.cache;
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

		for (final Object o : this)
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
		add(Double.valueOf(xy.getX()));
		add(Double.valueOf(xy.getY()));
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
		cache = null;
		add(Double.valueOf(x));
		add(Double.valueOf(y));
	}

	/**
	 * sets a vector of y coordinates with a common distance between points
	 *
	 * @param x0
	 * @param dx
	 * @param v
	 */
	public void set(final double x0, final double dx, final Vector<Double> v)
	{
		clear();
		if (v != null)
		{
			double x = x0;
			for (final Double d : v)
			{
				add(x);
				add(d);
				x += dx;
			}
		}
	}

	/**
	 * get the x value at index note that each index consumes 2 elements (the x and y value)
	 *
	 * @param index
	 * @return
	 */
	public double getX(final int index)
	{
		return doubleAt(2 * index);
	}

	/**
	 * get the min and max value of X
	 *
	 * @param index
	 * @return
	 */
	public JDFXYPair getXRange()
	{
		return new JDFXYPair(getX(0), getX(numPoints() - 1));
	}

	/**
	 * get the Y value at index i
	 *
	 * @param index
	 * @return
	 */
	public double getY(final int index)
	{
		return doubleAt(2 * index + 1);
	}

	/**
	 * get the Y value at x-value x - we interpolate linearly from a cache
	 *
	 * @param index
	 * @return
	 */
	public double getFastValue(final double x)
	{
		final JDFXYPair r = getXRange();
		final double x0 = r.getX();
		final double d = r.getY() - x0;
		if (cache == null)
		{
			final double d1 = d * 0.001;
			cache = new double[1001];
			for (int i = 0; i <= 1000; i++)
			{
				cache[i] = getValue(x0 + i * d1);
			}
		}
		final double dX = (x - x0) / (d * 0.001);
		final int iX = (int) dX;
		if (iX < 0)
			return cache[0];
		if (iX >= 1000)
			return cache[1000];
		final double mx = dX - iX;
		if (mx == 0)
			return cache[iX];
		return cache[iX] + (cache[iX + 1] - cache[iX]) * 0.001 * mx;
	}

	/**
	 * get the Y value at x-value x - we interpolate linearly
	 *
	 * @param index
	 * @return
	 */
	public double getValue(final double x)
	{
		final int indexX0 = getPos(x, false);
		final int indexX1 = getPos(x, true);
		if (indexX0 == indexX1)
		{
			return getY(indexX0);
		}
		else
		{
			final double range = getX(indexX1) - getX(indexX0);
			if (range <= 0)
			{
				return getY(indexX0);
			}
			else
			{
				final double rx = x - getX(indexX0);
				final double y0 = getY(indexX0);
				final double y1 = getY(indexX1);
				return y0 + (y1 - y0) * (rx / range);
			}
		}
	}

	/**
	 *
	 * @return the number of points
	 */
	public int numPoints()
	{
		return size() / 2;
	}

	/**
	 *
	 * @param x
	 * @param upper
	 * @return
	 */
	int getPos(final double x, final boolean upper)
	{
		final int numPoints = numPoints();
		for (int i = 0; i < numPoints; i++)
		{
			final double x2 = getX(i);
			if (x2 > x)
			{
				return upper ? i : i - 1;
			}
			else if (x2 == x)
			{
				return i;
			}
		}
		return -1;
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
		final JDFXYPair xyp = JDFXYPair.createXYPair(s);
		if (xyp != null)
		{
			add(xyp);
		}
		else
		{
			throw new DataFormatException("Invalid string: " + s);
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
		cache = null;
	}

	/**
	 * @see java.util.Vector#add(java.lang.Object)
	 */
	@Override
	public synchronized boolean add(final Object e)
	{
		cache = null;
		return super.add(e);
	}

	/**
	 * @see java.util.Vector#addAll(java.util.Collection)
	 */
	@Override
	public synchronized boolean addAll(final Collection<? extends Object> c)
	{
		cache = null;
		return super.addAll(c);
	}

	/**
	 * @see java.util.Vector#clear()
	 */
	@Override
	public void clear()
	{
		cache = null;
		super.clear();
	}

	public void resetCache()
	{
		cache = null;
	}
}