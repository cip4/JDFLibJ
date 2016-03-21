/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of
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
 * JDFRectangle.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.util.ScaleUtil;

/**
 * This class represents a rectangle JDFRectangle) consisting of a lower left x value (llx), a lower left y value (lly), an upper right x value (urx) and an
 * upper right y value (ury) all values are Double types.
 */
public class JDFRectangle extends JDFNumList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// **************************************** Constructors
	// ****************************************
	/**
	 * constructs a rectangle with all 4 values set to 0.0 Double
	 */
	public JDFRectangle()
	{
		super(MAX_RECTANGLE_DIMENSION);
	}

	/**
	 * constructs a rectangle with all values set via a Vector of Double objects
	 * 
	 * @param v the given Vector with MAX_RECTANGLE_DIMENSION objects of type Double
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 * @deprecated use typesafe constructors
	 */
	@Deprecated
	public JDFRectangle(final Vector v) throws DataFormatException
	{
		super(v);
	}

	/**
	 * constructs a rectangle with all values set via a String
	 * 
	 * @param s the given String, blank separated numbers
	 * 
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFRectangle(final String s) throws DataFormatException
	{
		super(s);
	}

	/**
	 * factory for JDFRectangle that silently returns null in case of illegal strings
	 * @param s the string to parse
	 * @return the JDFRectangle, null if s is not compatible
	 */
	public static JDFRectangle createRectangle(String s)
	{
		if (s != null && s.length() >= 7)
		{
			try
			{
				return new JDFRectangle(s);
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
	 * constructs a rectangle with all values set via a JDFRectangle
	 * 
	 * @param rec the given rectangle
	 */
	public JDFRectangle(final JDFRectangle rec)
	{
		super(MAX_RECTANGLE_DIMENSION);
		setLlx(rec.getLlx());
		setLly(rec.getLly());
		setUrx(rec.getUrx());
		setUry(rec.getUry());
	}

	/**
	 * constructs a rectangle with all values set via a JDFRectangle
	 * 
	 * @param rec the given rectangle
	 */
	public JDFRectangle(final JDFXYPair size)
	{
		super(MAX_RECTANGLE_DIMENSION);
		setLlx(0);
		setLly(0);
		setUrx(size.getX());
		setUry(size.getY());
	}

	/**
	 * constructs a rectangle with all values set via a JDFNumberList
	 * 
	 * @param nl the given number list
	 * 
	 * @throws DataFormatException - if the JDFNumberList has not a valid format
	 */
	public JDFRectangle(final JDFNumList nl) throws DataFormatException
	{
		super(nl);
	}

	/**
	 * constructs a new JDFRectangle with the given double values
	 * 
	 * @param llx lower left x coordinate
	 * @param lly lower left y coordinate
	 * @param urx lower left x coordinate
	 * @param ury lower left y coordinate
	 */
	public JDFRectangle(final double llx, final double lly, final double urx, final double ury)
	{
		super(MAX_RECTANGLE_DIMENSION);
		setLlx(llx);
		setLly(lly);
		setUrx(urx);
		setUry(ury);
	}

	/**
	 * isValid - true if the size of the vector is 4 and all instances are Double types
	 * 
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	@Override
	public boolean isValid() throws DataFormatException
	{
		if (size() != MAX_RECTANGLE_DIMENSION)
		{
			throw new DataFormatException("Data format exception! wrong size: " + size());
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
	 * getLlx - returns the lower left x coordinate
	 * 
	 * @return double - the lower left x coordinate
	 */
	public double getLlx()
	{
		return doubleAt(0);
	}

	/**
	 * setLlx - sets the lower left x coordinate
	 * 
	 * @param x the lower left x coordinate
	 */
	public void setLlx(final double x)
	{
		set(0, x);
	}

	/**
	 * setLlXMm - sets the lower left x coordinate in millimeter
	 * 
	 * @param mmX the lower left x coordinate in millimeter
	 * @author Stefan Meissner
	 */
	public void setLlxMm(final double mmX)
	{
		setLlx(ScaleUtil.mm2Dtp(mmX));
	}

	/**
	 * getLly - returns the lower left y coordinate
	 * 
	 * @return double - the lower left y coordinate
	 */
	public double getLly()
	{
		return doubleAt(1);
	}

	/**
	 * setLly - sets the lower left y coordinate
	 * 
	 * @param y the lower left y coordinate
	 */
	public void setLly(final double y)
	{
		set(1, y);
	}

	/**
	 * setLlyMm - sets the lower left y coordinate in millimeter
	 * 
	 * @param mmY the lower left y coordinate in millimeter
	 * @author Stefan Meissner
	 */
	public void setLlyMm(final double mmY)
	{
		setLly(ScaleUtil.mm2Dtp(mmY));
	}

	/**
	 * getUrx - returns the upper right x coordinate
	 * 
	 * @return double - the upper right x coordinate
	 */
	public double getUrx()
	{
		return doubleAt(2);
	}

	/**
	 * setUrx - sets the upper right x coordinate
	 * 
	 * @param x the upper right x coordinate
	 */
	public void setUrx(final double x)
	{
		set(2, x);
	}

	/**
	 * setUrxMm - sets the upper right x coordinate in millimeter
	 * 
	 * @param mmX the upper right x coordinate in millimeter
	 * @author Stefan Meissner
	 */
	public void setUrxMm(final double mmX)
	{
		setUrx(ScaleUtil.mm2Dtp(mmX));
	}

	/**
	 * getUry - returns the upper right y coordinate
	 * 
	 * @return double - the upper right y coordinate
	 */
	public double getUry()
	{
		return doubleAt(3);
	}

	/**
	 * setUry - sets the upper right y coordinate
	 * 
	 * @param y the upper right y coordinate
	 */
	public void setUry(final double y)
	{
		set(3, y);
	}

	/**
	 * setUryMm - sets the upper right y coordinate in millimeter
	 * 
	 * @param mmY the upper right y coordinate in millimeter
	 * @author Stefan Meissner
	 */
	public void setUryMm(final double mmY)
	{
		setUry(ScaleUtil.mm2Dtp(mmY));
	}

	/**
	 * getWidth - returns the width of the rectangle, the difference between upper right x value and lower left x value as an absolute value
	 * 
	 * @return double - the width of the rectangle
	 */
	public double getWidth()
	{
		return Math.abs(getUrx() - getLlx());
	}

	/**
	 * getHeight - returns the height of the rectangle, the difference between upper right y value and lower left y value as an absolute value
	 * 
	 * @return double - the height of the rectangle
	 */
	public double getHeight()
	{
		return Math.abs(getUry() - getLly());
	}

	/**
	 * isGreater - equality operator >
	 * 
	 * @param r the JDFRectangle object to compare to
	 * @return boolean - true if <code>this</this> > r
	 */
	public boolean isGreater(final JDFRectangle r)
	{
		return (!equals(r) && (getLlx() <= r.getLlx()) && (getLly() <= r.getLly()) && (getUrx() >= r.getUrx()) && (getUry() >= r.getUry()));
	}

	/**
	 * shifts this by the amount specified
	 * 
	 * @param tx shift in x direction
	 * @param ty shift in y direction
	 */
	public void shift(final double tx, final double ty)
	{
		setLlx(getLlx() + tx);
		setLly(getLly() + ty);
		setUrx(getUrx() + tx);
		setUry(getUry() + ty);
	}

	/**
	 * shifts this by the amount specified
	 * 
	 * @param shift in x and y direction
	 */
	public void shift(final JDFXYPair shift)
	{
		if (shift == null)
		{
			return;
		}
		shift(shift.getX(), shift.getY());
	}

	/**
	 * isGreaterOrEqual - equality operator >=
	 * 
	 * @param r the JDFRectangle object to compare to
	 * @return boolean - true if <code>this</this> >= r
	 */
	public boolean isGreaterOrEqual(final JDFRectangle r)
	{
		return (getLlx() <= r.getLlx()) && (getLly() <= r.getLly()) && (getUrx() >= r.getUrx()) && (getUry() >= r.getUry());
	}

	/**
	 * isLess - equality operator <
	 * 
	 * @param r the JDFRectangle object to compare to
	 * @return boolean - true if <code>this</this> < r
	 */
	public boolean isLess(final JDFRectangle r)
	{
		return (!equals(r) && (getLlx() >= r.getLlx()) && (getLly() >= r.getLly()) && (getUrx() <= r.getUrx()) && (getUry() <= r.getUry()));
	}

	/**
	 * isLessOrEqual - equality operator <=
	 * 
	 * @param r the JDFRectangle object to compare to
	 * @return boolean - true if <code>this</this> <= r
	 */
	public boolean isLessOrEqual(final JDFRectangle r)
	{
		return (getLlx() >= r.getLlx()) && (getLly() >= r.getLly()) && (getUrx() <= r.getUrx()) && (getUry() <= r.getUry());
	}

	/**
	 * 
	 * @return the lower left pout
	 */
	public JDFXYPair getLL()
	{
		return new JDFXYPair(getLlx(), getLly());
	}

	/**
	* 
	* @return the upper right point
	*/
	public JDFXYPair getUR()
	{
		return new JDFXYPair(getUrx(), getUry());
	}

	/**
	* 
	* @return the width and height
	*/
	public JDFXYPair getSize()
	{
		return new JDFXYPair(getWidth(), getHeight());
	}
}