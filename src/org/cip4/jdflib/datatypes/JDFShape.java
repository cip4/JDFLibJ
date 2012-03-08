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
 * JDFShape.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.util.HashUtil;

/**
 * This class is a representation of a JDFShape. It is a blank separated list of double values consisting of a width(x), a height(y) and a depth(z) value. this
 * spans a standard right-handed xyz coordinate system
 */
public class JDFShape extends JDFNumList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * factory for JDFShape that silently returns null in case of illegal strings
	 * @param s the string to parse - if JDFXYPair compatible, a 0  z dimension value is assumed
	 * @return the JDFShape, null if s is not compatible
	 */
	public static JDFShape createShape(String s)
	{
		try
		{
			return new JDFShape(s);
		}
		catch (DataFormatException x)
		{
			return null;
		}
	}

	/**
	 * constructor - constructs a shape with all values set to 0.0 Double
	 */
	public JDFShape()
	{
		super(MAX_SHAPE_DIMENSION);
	}

	/**
	 * constructor - constructs a shape with all values set via a Vector of Double objects
	 * 
	 * @param v the given vector
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 * @deprecated use typesafe constructors
	 */
	@Deprecated
	public JDFShape(final Vector v) throws DataFormatException
	{
		super(v);
	}

	/**
	 * constructor - constructs a shape with all values set via a String
	 * 
	 * @param s the given String
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFShape(final String s) throws DataFormatException
	{
		super(s);
	}

	/**
	 * constructor - constructs a shape with all values set via a JDFNumberList
	 * 
	 * @param nl the given number list
	 * 
	 * @throws DataFormatException - if the JDFNumberList has not a valid format
	 */
	public JDFShape(final JDFNumList nl) throws DataFormatException
	{
		super(nl);
	}

	/**
	 * constructor - constructs a shape with all values set via a JDFNumberList
	 * 
	 * @param nl the given number list
	 * 
	 * 
	 */
	public JDFShape(final JDFShape nl)
	{
		super();
		addAll(nl);
	}

	/**
	 * swap x and y coordinates - 
	 * 
	 */
	public void swapXY()
	{
		double tmp = getX();
		setX(getY());
		setY(tmp);
	}

	/**
	 * constructor - constructs a new JDFShape with the given double values
	 * 
	 * @param x the x value
	 * @param y the y value
	 * @param z the z value
	 */
	public JDFShape(final double x, final double y, final double z)
	{
		super(MAX_SHAPE_DIMENSION);
		setY(y);
		setX(x);
		setZ(z);
	}

	/**
	 * constructor - constructs a new JDFShape with the given 2 double values third is default = 0.
	 * 
	 * @param x the width
	 * @param y the height
	 */
	public JDFShape(final double x, final double y)
	{
		super(MAX_SHAPE_DIMENSION);
		setY(y);
		setX(x);
		setZ(0.0);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * isValid - true if the size of the vector is 3 and all instances are Double types
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	@Override
	public boolean isValid() throws DataFormatException
	{
		// with default Z = 0.0
		if (size() > MAX_SHAPE_DIMENSION || size() < MAX_SHAPE_DIMENSION - 2) // Shape
		{
			throw new DataFormatException("Data format exception! size=" + size());
		}

		for (Object o : this)
		{
			if (!(o instanceof Double))
			{
				throw new DataFormatException("Data format exception!");
			}
		}
		if (size() == 1)
		{
			addElement(new Double(0.0));
		}
		if (size() == 2)
		{
			addElement(new Double(0.0));
		}
		return true;
	}

	/**
	 * equals - returns true if both JDFShapes are equal, otherwise false
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

		final JDFShape shape = (JDFShape) other;

		return (Math.abs(this.getY() - shape.getY()) <= EPSILON) && (Math.abs(this.getX() - shape.getX()) <= EPSILON) && (Math.abs(this.getZ() - shape.getZ()) <= EPSILON);
	}

	/**
	 * hashCode complements equals() to fulfill the equals/hashCode contract
	 */
	@Override
	public int hashCode()
	{
		return HashUtil.hashCode(super.hashCode(), this.toString());
	}

	/**
	 * isGreaterOrEqual - equality operator >=
	 * 
	 * @param x the JDFShape object to compare to
	 * @return boolean - true if this >= x
	 */
	public boolean isGreaterOrEqual(final JDFShape x)
	{
		return (equals(x) || ((getY() >= x.getY()) && (getX() >= x.getX()) && (getZ() >= x.getZ())));
	}

	/**
	 * isLessOrEqual - equality operator <=
	 * 
	 * @param x the JDFShape object to compare to
	 * @return boolean - true if this <= x
	 */
	public boolean isLessOrEqual(final JDFShape x)
	{
		return equals(x) || ((getY() <= x.getY()) && (getX() <= x.getX()) && (getZ() <= x.getZ()));
	}

	/**
	 * isGreater - equality operator >
	 * 
	 * @param x the JDFShape object to compare to
	 * @return boolean - true if this > x
	 */
	public boolean isGreater(final JDFShape x)
	{
		return (!equals(x) && (getY() >= x.getY()) && (getX() >= x.getX()) && (getZ() >= x.getZ()));
	}

	/**
	 * isLess - equality operator <
	 * 
	 * @param x the JDFShape object to compare to
	 * @return boolean - true if this < x
	 */
	public boolean isLess(final JDFShape x)
	{
		return (!equals(x) && (getY() <= x.getY()) && (getX() <= x.getX()) && (getZ() <= x.getZ()));
	}

	/**
	 * getHeight - returns the height
	 * 
	 * @deprecated use getY - attention height and width were accidentally exchanged
	 * 
	 * @return double - the height
	 */
	@Deprecated
	public double getHeight()
	{
		return getY();
	}

	/**
	 * getY - returns the width
	 * 
	 * @return double - the width
	 * 
	 */
	public double getY()
	{
		return doubleAt(1);
	}

	/**
	 * setHeight - sets the height
	 * 
	 * @deprecated attention height and width were accidentally exchanged
	 * @param height the height
	 */
	@Deprecated
	public void setHeight(final double height)
	{
		setY(height);
	}

	/**
	 * setY - sets the height
	 * 
	 * @param y the height
	 */
	public void setY(final double y)
	{
		set(1, y);
	}

	/**
	 * getWidth - returns the width
	 * 
	 * @deprecated use getX - attention height and width were accidentally exchanged
	 * @return double - the width
	 * 
	 */
	@Deprecated
	public double getWidth()
	{
		return getX();
	}

	/**
	 * getX - returns the width
	 * 
	 * @return double - the width
	 * 
	 */
	public double getX()
	{
		return doubleAt(0);
	}

	/**
	 * setWidth - sets the x value
	 * 
	 * @param x the width
	 */
	public void setX(final double x)
	{
		set(0, x);
	}

	/**
	 * setWidth - sets the width
	 * 
	 * @deprecated attention height and width were accidentally exchanged
	 * @param width the width
	 */
	@Deprecated
	public void setWidth(final double width)
	{
		setX(width);
	}

	/**
	 * getLength - returns the length
	 * 
	 * @deprecated use getZ
	 * @return double - the length
	 */
	@Deprecated
	public double getLength()
	{
		return getZ();
	}

	/**
	 * getZ - returns the z value
	 * 
	 * @return double - the length
	 */
	public double getZ()
	{
		return doubleAt(2);
	}

	/**
	 * setLength - sets the length
	 * 
	 * @deprecated
	 * @param length the length
	 */
	@Deprecated
	public void setLength(final double length)
	{
		setZ(length);
	}

	/**
	 * set the z value
	 * 
	 * @param z
	 */
	public void setZ(final double z)
	{
		set(2, z);
	}

}
