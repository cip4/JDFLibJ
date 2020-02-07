/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFMatrix.java
 *
 * Last changes
 *
 * 2001-10-19 Dagmar Schlenz Bug fixed in getAffineTransform
 *
 */
package org.cip4.jdflib.datatypes;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFElement.EnumOrientation;
import org.cip4.jdflib.util.HashUtil;

/**
 * This class represents a transformation matrix consisting of 6 transformation values a, b, c, d, tx, ty all values are double values. The matrix looks like:
 *
 * <pre>
 *  [ x']   [  a  b  tx  ] [ x ]   [ m00x + m01y + m02 ]
 *  [ y'] = [  c  d  ty  ] [ y ] = [ m10x + m11y + m12 ]
 *  [ 1 ]   [  0  0  1   ] [ 1 ]   [         1         ]
 * </pre>
 *
 */
public class JDFMatrix extends JDFNumList
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final static JDFMatrix unitMatrix = new JDFMatrix(1, 0, 0, 1, 0, 0);

	/**
	 *
	 * @return a copy of the unit matrix
	 */
	public static JDFMatrix getUnitMatrix()
	{
		return new JDFMatrix(unitMatrix);
	}

	/**
	 * constructs a matrix with all values set to 0.0 Double
	 */
	public JDFMatrix()
	{
		super(MAX_MATRIX_DIMENSION);
	}

	/**
	 * @see org.cip4.jdflib.datatypes.JDFNumList#clone()
	 */
	@Override
	public synchronized JDFMatrix clone()
	{
		return (JDFMatrix) super.clone();
	}

	/**
	 * constructs a matrix with all values set via a Vector of Double objects
	 *
	 * @param Vector v - the given Vector
	 *
	 * @throws DataFormatException - if the Vector has not a valid format
	 * @deprecated use typesafe constructors
	 */
	@Deprecated
	public JDFMatrix(final Vector v) throws DataFormatException
	{
		super(v);
	}

	/**
	 * constructs a matrix with all values set via arotation and a shift
	 *
	 * @param degrees - the rotation in degrees
	 * @param x the X shift
	 * @param y the Y shift
	 *
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	public JDFMatrix(final double degrees, final double x, final double y)
	{
		super(MAX_MATRIX_DIMENSION);
		setA(1.);
		setD(1.);
		shift(x, y);
		rotate(degrees);
	}

	/**
	 * constructs a matrix with all values set via an enumerated orientation
	 *
	 * @param orientation - the named orientation
	 * @param w the width of the unrotated object to transform
	 * @param h the height of the unrotated object to transform
	 *
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	public JDFMatrix(final EnumOrientation orientation, final double w, final double h)
	{
		super(MAX_MATRIX_DIMENSION);
		if (orientation == null || orientation.equals(EnumOrientation.Rotate0))
		{
			setA(1.);
			setD(1.);
		}
		else if (orientation.equals(EnumOrientation.Rotate90))
		{
			setB(1.);
			setC(-1.);
			setTx(h);
		}
		else if (orientation.equals(EnumOrientation.Rotate180))
		{
			setA(-1.);
			setD(-1.);
			setTx(w);
			setTy(h);
		}
		else if (orientation.equals(EnumOrientation.Rotate270))
		{
			setB(-1.);
			setC(1.);
			setTy(w);
		}
		else if (orientation.equals(EnumOrientation.Flip0))
		{
			setA(1.);
			setD(-1.);
			setTy(h);
		}
		else if (orientation.equals(EnumOrientation.Flip90))
		{
			setB(-1.);
			setC(-1.);
			setTx(h);
			setTy(w);
		}
		else if (orientation.equals(EnumOrientation.Flip180))
		{
			setA(-1.);
			setD(1.);
			setTx(w);
		}
		else if (orientation.equals(EnumOrientation.Flip270))
		{
			setB(1.);
			setC(1.);
		}
	}

	/**
	 * constructs a matrix with all values set via a String
	 *
	 * @param s the given String
	 *
	 * @throws DataFormatException - if the String has not a valid format
	 */
	public JDFMatrix(final String s) throws DataFormatException
	{
		super(s);
	}

	/**
	 * factory for JDFXYPair that silently returns null in case of illegal strings
	 *
	 * @param s the string to parse
	 * @return the JDFXYPair, null if s is not compatible
	 */
	public static JDFMatrix createMatrix(final String s)
	{
		if (s != null && s.length() >= 11) // 6 + 5 blanks
		{
			try
			{
				return new JDFMatrix(s);
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
	 * constructs a rectangle with all values set via a JDFNumberList
	 *
	 * @param nl the given number list
	 *
	 * @throws DataFormatException - if the JDFNumberList has not a valid format
	 */
	public JDFMatrix(final JDFNumList nl) throws DataFormatException
	{
		super(nl);
	}

	/**
	 * copy constructor
	 *
	 * @param matrix the given number list
	 *
	 *
	 */
	public JDFMatrix(final JDFMatrix matrix)
	{
		super();
		addAll(matrix);
	}

	/**
	 * constructs a rectangle with all values set via a JDFNumberList
	 *
	 * @param rect the given number list
	 *
	 *
	 */
	public JDFMatrix(final JDFRectangle rect)
	{
		this(unitMatrix);
		if (rect != null)
		{
			shift(rect.getLlx(), rect.getLly());
		}
	}

	/**
	 * constructs a new JDFMatrix with the given double values
	 *
	 * @param a position 01 of the transformation matrix
	 * @param b position 02 of the transformation matrix
	 * @param c position 10 of the transformation matrix
	 * @param d position 11 of the transformation matrix
	 * @param tx position 03 of the transformation matrix
	 * @param ty position 13 of the transformation matrix
	 */
	public JDFMatrix(final double a, final double b, final double c, final double d, final double tx, final double ty)
	{
		super(MAX_MATRIX_DIMENSION);
		setA(a);
		setB(b);
		setC(c);
		setD(d);
		setTx(tx);
		setTy(ty);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * isValid - true if the size of the vector is 6 and all instances are Double types
	 *
	 * @throws DataFormatException - if the Vector has not a valid format
	 */
	@Override
	public boolean isValid() throws DataFormatException
	{
		if (size() != MAX_MATRIX_DIMENSION)
		{
			throw new DataFormatException("wrong size! " + size());
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
	 * getA - returns the first coordinate
	 *
	 * @return the first coordinate
	 */
	public double getA()
	{
		return doubleAt(0);
	}

	/**
	 * setA - sets the first coordinate
	 *
	 * @param p_a the first coordinate
	 */
	public void setA(final double p_a)
	{
		set(0, p_a);
	}

	/**
	 * getB - returns the second coordinate
	 *
	 * @return double - the second coordinate
	 */
	public double getB()
	{
		return doubleAt(1);
	}

	/**
	 * setB - sets the second coordinate
	 *
	 * @param p_b the first coordinate
	 */
	public void setB(final double p_b)
	{
		set(1, p_b);
	}

	/**
	 * getC - returns the third coordinate
	 *
	 * @return double - the third coordinate
	 */
	public double getC()
	{
		return doubleAt(2);
	}

	/**
	 * setC - sets the third coordinate
	 *
	 * @param p_c the third coordinate
	 */
	public void setC(final double p_c)
	{
		set(2, p_c);
	}

	/**
	 * getD - returns the fourth coordinate
	 *
	 * @return double - the fourth coordinate
	 */
	public double getD()
	{
		return doubleAt(3);
	}

	/**
	 * setD - sets the fourth coordinate
	 *
	 * @param p_d the fourth coordinate
	 */
	public void setD(final double p_d)
	{
		set(3, p_d);
	}

	/**
	 * getTx - returns the tx coordinate
	 *
	 * @return double - the tx coordinate
	 */
	public double getTx()
	{
		return doubleAt(4);
	}

	/**
	 *
	 * @return
	 */
	public JDFXYPair getShift()
	{
		return new JDFXYPair(getTx(), getTy());
	}

	/**
	 *
	 * @return
	 */
	public EnumOrientation getOrientation()
	{
		final boolean isFlip = isFlip();
		final double angle = getAngle();
		if (Math.abs(angle) < 1)
			return isFlip ? EnumOrientation.Flip0 : EnumOrientation.Rotate0;
		if (Math.abs(angle - 90) < 1)
			return isFlip ? EnumOrientation.Flip90 : EnumOrientation.Rotate90;
		if (Math.abs(angle - 180) < 1)
			return isFlip ? EnumOrientation.Flip180 : EnumOrientation.Rotate180;
		if (Math.abs(angle - 270) < 1)
			return isFlip ? EnumOrientation.Flip270 : EnumOrientation.Rotate270;
		return null;
	}

	/**
	 *
	 * @return
	 */
	public double getAngle()
	{
		final double det = getAffineTransform().getDeterminant();
		final double aDet = Math.abs(det);
		if (aDet < 0.00001)
		{
			return 0;
		}
		final double a = getA() / aDet;
		final double b = getB() / det;
		final double angleb = Math.asin(b) * 180 / Math.PI;
		double angleA = Math.acos(a) * 180 / Math.PI;
		if (angleb < 0)
		{
			angleA += 180;
		}
		return angleA;
	}

	/**
	 *
	 * @return
	 */
	public boolean isFlip()
	{
		final double det = getAffineTransform().getDeterminant();
		return det < 0;
	}

	/**
	 * setTx - sets the tx coordinate
	 *
	 * @param p_tx the tx coordinate
	 */
	public void setTx(final double p_tx)
	{
		set(4, p_tx);
	}

	/**
	 * getTy - returns the ty coordinate
	 *
	 * @return double - the ty coordinate
	 */
	public double getTy()
	{
		return doubleAt(5);
	}

	/**
	 * setTy - sets the ty coordinate
	 *
	 * @param p_ty the ty coordinate
	 */
	public void setTy(final double p_ty)
	{
		set(5, p_ty);
	}

	/**
	 * equals - returns true if both JDFMatrices are equal, otherwise false
	 *
	 * @return boolean - true if equal otherwise false
	 */
	@Override
	public synchronized boolean equals(final Object other)
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

		final JDFMatrix m = (JDFMatrix) other;

		return (Math.abs(this.getA() - m.getA()) <= EPSILON) && (Math.abs(this.getB() - m.getB()) <= EPSILON) && (Math.abs(this.getC() - m.getC()) <= EPSILON)
				&& (Math.abs(this.getD() - m.getD()) <= EPSILON) && (Math.abs(this.getTx() - m.getTx()) <= EPSILON) && (Math.abs(this.getTy() - m.getTy()) <= EPSILON);
	}

	/**
	 * hashCode complements equals() to fulfill the equals/hashCode contract
	 */
	@Override
	public synchronized int hashCode()
	{
		return HashUtil.hashCode(super.hashCode(), this.toString());
	}

	/**
	 * getAffineTransform -
	 *
	 * @return AffineTransform
	 */
	public AffineTransform getAffineTransform()
	{
		return new AffineTransform(this.getA(), this.getB(), this.getC(), this.getD(), this.getTx(), this.getTy());
	}

	/**
	 * setAffineTransform -
	 *
	 * @param affineTrans matrix to be stored
	 */
	public void setAffineTransform(final AffineTransform affineTrans)
	{
		final double[] flatMatrix = new double[6];
		affineTrans.getMatrix(flatMatrix);
		setA(flatMatrix[0]);
		setB(flatMatrix[1]);
		setC(flatMatrix[2]);
		setD(flatMatrix[3]);
		setTx(flatMatrix[4]);
		setTy(flatMatrix[5]);
	}

	/**
	 * applies this to a point
	 *
	 * @param inCoordinate
	 * @return
	 */
	public JDFXYPair transform(final JDFXYPair inCoordinate)
	{
		if (inCoordinate == null)
			return null;
		final Point2D p = inCoordinate.getPoint2D();
		getAffineTransform().transform(p, p);
		return new JDFXYPair(p.getX(), p.getY());
	}

	/**
	 * shifts Tx and Ty by the amount specified
	 *
	 * @param tx shift in x direction
	 * @param ty shift in y direction
	 * @return
	 */
	public JDFMatrix shift(final double tx, final double ty)
	{
		setTx(getTx() + tx);
		setTy(getTy() + ty);
		return this;
	}

	/**
	 * rotate this by degrees degrees CouterClockwise
	 *
	 * @param degrees the degrees to rotate by in counterclockwise direction
	 */
	public JDFMatrix rotate(final double degrees)
	{
		final AffineTransform at = getAffineTransform();
		at.rotate(degrees * Math.PI / 180.0);
		setAffineTransform(at);
		return this;
	}

	/**
	 * concatinates this with m
	 *
	 * @param m the matrix to concatinate
	 *
	 */
	public JDFMatrix concat(final JDFMatrix m)
	{
		final AffineTransform a = getAffineTransform();
		final AffineTransform ma = m.getAffineTransform();
		a.concatenate(ma);
		setAffineTransform(a);
		return this;
	}

	/**
	 * inverts this
	 *
	 * @param m the matrix to concatinate
	 *
	 */
	public JDFMatrix invert()
	{
		final AffineTransform a = getAffineTransform();
		try
		{
			setAffineTransform(a.createInverse());
		}
		catch (final NoninvertibleTransformException e)
		{
			// nop if not invertible
		}
		return this;
	}

	/**
	 * shift this matrix by an xypair
	 *
	 * @param point the point to shift by
	 */
	public JDFMatrix shift(final JDFXYPair point)
	{
		if (point != null)
		{
			shift(point.getX(), point.getY());
		}
		return this;
	}

	/**
	 * shift this matrix by an xypair
	 *
	 * @param point the point to shift by
	 */
	public JDFMatrix setShift(final JDFXYPair point)
	{
		setTx(point == null ? 0 : point.getX());
		setTy(point == null ? 0 : point.getY());
		return this;
	}

	public JDFRectangle transform(final JDFRectangle jdfRectangle)
	{
		if (jdfRectangle == null)
			return null;
		final JDFXYPair ll = transform(jdfRectangle.getLL());
		final JDFXYPair ur = transform(jdfRectangle.getUR());
		return new JDFRectangle(ll, ur);
	}
}
