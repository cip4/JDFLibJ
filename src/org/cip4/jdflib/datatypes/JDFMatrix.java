/*
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
 * JDFMatrix.java
 *
 * Last changes
 *
 * 2001-10-19   Dagmar Schlenz  Bug fixed in getAffineTransform
 *
 */
package org.cip4.jdflib.datatypes;

import java.awt.geom.AffineTransform;
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
	// **************************************** Constructors
	// ****************************************
	public final static JDFMatrix unitMatrix = new JDFMatrix(1, 0, 0, 1, 0, 0);

	/**
	 * constructs a matrix with all values set to 0.0 Double
	 */
	public JDFMatrix()
	{
		super(MAX_MATRIX_DIMENSION);
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
	 * constructs a matrix with all values set via a JDFMatrix
	 * 
	 * @param JDma the given matrix
	 */
	public JDFMatrix(final JDFMatrix ma)
	{
		super(MAX_MATRIX_DIMENSION);
		setA(ma.getA());
		setB(ma.getB());
		setC(ma.getC());
		setD(ma.getD());
		setTx(ma.getTx());
		setTy(ma.getTy());
	}

	/**
	 * constructs a rectangle with all values set via a JDFNumberList
	 * 
	 * @param nl the given number list
	 * 
	 * @throws DataFormatException - if the JDFNumberList has not a valid format
	 */
	public JDFMatrix(final JDFNumberList nl) throws DataFormatException
	{
		super(MAX_MATRIX_DIMENSION);
		if (nl.size() != MAX_MATRIX_DIMENSION)
		{
			throw new DataFormatException("JDFMatrix: can't construct JDFMatrix from this JDFNuberList value");
		}
		m_numList.set(0, nl.m_numList.get(0));
		m_numList.set(1, nl.m_numList.get(1));
		m_numList.set(2, nl.m_numList.get(2));
		m_numList.set(3, nl.m_numList.get(3));
		m_numList.set(4, nl.m_numList.get(4));
		m_numList.set(5, nl.m_numList.get(5));
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
		if (m_numList.size() != MAX_MATRIX_DIMENSION)
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
	 * getA - returns the first coordinate
	 * 
	 * @return the first coordinate
	 */
	public double getA()
	{
		return ((Double) m_numList.get(0)).doubleValue();
	}

	/**
	 * setA - sets the first coordinate
	 * 
	 * @param p_a the first coordinate
	 */
	public void setA(final double p_a)
	{
		m_numList.set(0, new Double(p_a));
	}

	/**
	 * getB - returns the second coordinate
	 * 
	 * @return double - the second coordinate
	 */
	public double getB()
	{
		return ((Double) m_numList.get(1)).doubleValue();
	}

	/**
	 * setB - sets the second coordinate
	 * 
	 * @param p_b the first coordinate
	 */
	public void setB(final double p_b)
	{
		m_numList.set(1, new Double(p_b));
	}

	/**
	 * getC - returns the third coordinate
	 * 
	 * @return double - the third coordinate
	 */
	public double getC()
	{
		return ((Double) m_numList.get(2)).doubleValue();
	}

	/**
	 * setC - sets the third coordinate
	 * 
	 * @param p_c the third coordinate
	 */
	public void setC(final double p_c)
	{
		m_numList.set(2, new Double(p_c));
	}

	/**
	 * getD - returns the fourth coordinate
	 * 
	 * @return double - the fourth coordinate
	 */
	public double getD()
	{
		return ((Double) m_numList.get(3)).doubleValue();
	}

	/**
	 * setD - sets the fourth coordinate
	 * 
	 * @param p_d the fourth coordinate
	 */
	public void setD(final double p_d)
	{
		m_numList.set(3, new Double(p_d));
	}

	/**
	 * getTx - returns the tx coordinate
	 * 
	 * @return double - the tx coordinate
	 */
	public double getTx()
	{
		return ((Double) m_numList.get(4)).doubleValue();
	}

	/**
	 * setTx - sets the tx coordinate
	 * 
	 * @param p_tx the tx coordinate
	 */
	public void setTx(final double p_tx)
	{
		m_numList.set(4, new Double(p_tx));
	}

	/**
	 * getTy - returns the ty coordinate
	 * 
	 * @return double - the ty coordinate
	 */
	public double getTy()
	{
		return ((Double) m_numList.get(5)).doubleValue();
	}

	/**
	 * setTy - sets the ty coordinate
	 * 
	 * @param p_y the ty coordinate
	 */
	public void setTy(final double p_ty)
	{
		m_numList.set(5, new Double(p_ty));
	}

	/**
	 * equals - returns true if both JDFMatrices are equal, otherwise false
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

		final JDFMatrix m = (JDFMatrix) other;

		return (Math.abs(this.getA() - m.getA()) <= EPSILON) && (Math.abs(this.getB() - m.getB()) <= EPSILON) && (Math.abs(this.getC() - m.getC()) <= EPSILON)
				&& (Math.abs(this.getD() - m.getD()) <= EPSILON) && (Math.abs(this.getTx() - m.getTx()) <= EPSILON) && (Math.abs(this.getTy() - m.getTy()) <= EPSILON);
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
	 * getAffineTransform -
	 * 
	 * @return AffineTransform
	 */
	public AffineTransform getAffineTransform()
	{
		// 2001-10-19 D.Schlenz:
		// switched getB() and getC() in constructor call of AffineTransform
		return new AffineTransform(this.getA(), this.getB(), this.getC(), this.getD(), this.getTx(), this.getTy());
	}

	/**
	 * setAffineTransform -
	 * 
	 * @param AffineTransform matrix to be stored
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
	 * shifts Tx and Ty by the amount specified
	 * 
	 * @param tX shift in x direction
	 * @param tY shift in y direction
	 */
	public void shift(final double tx, final double ty)
	{
		setTx(getTx() + tx);
		setTy(getTy() + ty);
	}

	/**
	 * rotate this by degrees degrees CouterClockwise
	 * 
	 * @param degrees the degrees to rotate by in counterclockwise direction
	 */
	public void rotate(final double degrees)
	{
		final AffineTransform at = getAffineTransform();
		at.rotate(degrees * Math.PI / 180.0);
		setAffineTransform(at);
	}

	/**
	 * concatinates this with m
	 * 
	 * @param m the matrix to concatinate
	 * @param tY shift in y direction
	 */
	public void concat(final JDFMatrix m)
	{
		final AffineTransform a = getAffineTransform();
		final AffineTransform ma = m.getAffineTransform();
		a.concatenate(ma);
		setAffineTransform(a);
	}

	/**
	 * shift this matrix by an xypair
	 * 
	 * @param point the point to shift by
	 */
	public void shift(final JDFXYPair point)
	{
		if (point == null)
		{
			return;
		}
		shift(point.getX(), point.getY());

	}
}
