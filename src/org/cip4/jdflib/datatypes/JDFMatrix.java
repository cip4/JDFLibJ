/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of
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

import org.cip4.jdflib.util.HashUtil;

/**
 * This class represents a transformation matrix consisting of 6 transformation values
 * a, b, c, d, tx, ty all values are double values. The matrix looks like:
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
    //**************************************** Constructors ****************************************
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
     */
    public JDFMatrix(Vector v) throws DataFormatException
    {
        super(v);
    }

    /**
     * constructs a matrix with all values set via a String
     *
     * @param s the given String
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFMatrix(String s) throws DataFormatException
    {
        super(s);
    }

    /**
     * constructs a matrix with all values set via a JDFMatrix
     *
     * @param JDma the given matrix
     */
    public JDFMatrix(JDFMatrix ma)
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
    public JDFMatrix(JDFNumberList nl) throws DataFormatException
    {
        super(MAX_MATRIX_DIMENSION);
        if (nl.size()!=MAX_MATRIX_DIMENSION)
            throw new DataFormatException("JDFMatrix: can't construct JDFMatrix from this JDFNuberList value");
        getnumList().set(0, nl.getnumList().get(0));
        getnumList().set(1, nl.getnumList().get(1));
        getnumList().set(2, nl.getnumList().get(2));
        getnumList().set(3, nl.getnumList().get(3));
        getnumList().set(4, nl.getnumList().get(4));
        getnumList().set(5, nl.getnumList().get(5));
    }

    /**
     * constructs a new JDFMatrix with the given double values
     *
     * @param a  position 01 of the transformation matrix
     * @param b  position 02 of the transformation matrix
     * @param c  position 10 of the transformation matrix
     * @param d  position 11 of the transformation matrix
     * @param tx position 03 of the transformation matrix
     * @param ty position 13 of the transformation matrix
     */
    public JDFMatrix(double a, double b, double c, double d, double tx, double ty)
    {
        super(MAX_MATRIX_DIMENSION);
        setA(a);
        setB(b);
        setC(c);
        setD(d);
        setTx(tx);
        setTy(ty);
    }

    //**************************************** Methods *********************************************
    /**
     * isValid - true if the size of the vector is 6 and all instances are Double types
     *
     * @throws DataFormatException - if the Vector has not a valid format
     */
    public void isValid() throws DataFormatException
    {
        if (getnumList().size() != MAX_MATRIX_DIMENSION)
        {
            throw new DataFormatException("Data format exception!");
        }

        for (int i = 0; i < getnumList().size(); i++)
        {
            if (!(getnumList().elementAt(i) instanceof Double))
            {
                throw new DataFormatException("Data format exception!");
            }
        }
    }

    /**
     * getA - returns the first coordinate
     *
     * @return the first coordinate
     */
    public double getA()
    {
        return ((Double)getnumList().get(0)).doubleValue();
    }

    /**
     * setA - sets the first coordinate
     *
     * @param p_a the first coordinate
     */
    public void setA(double p_a)
    {
        getnumList().set(0, new Double(p_a));
    }

    /**
     * getB - returns the second coordinate
     *
     * @return double - the second coordinate
     */
    public double getB()
    {
        return ((Double)getnumList().get(1)).doubleValue();
    }

    /**
     * setB - sets the second coordinate
     *
     * @param p_b the first coordinate
     */
    public void setB(double p_b)
    {
        getnumList().set(1, new Double(p_b));
    }

    /**
     * getC - returns the third coordinate
     *
     * @return double - the third coordinate
     */
    public double getC()
    {
        return ((Double)getnumList().get(2)).doubleValue();
    }

    /**
     * setC - sets the third coordinate
     *
     * @param p_c the third coordinate
     */
    public void setC(double p_c)
    {
        getnumList().set(2, new Double(p_c));
    }

    /**
     * getD - returns the fourth coordinate
     *
     * @return double - the fourth coordinate
     */
    public double getD()
    {
        return ((Double)getnumList().get(3)).doubleValue();
    }

    /**
     * setD - sets the fourth coordinate
     *
     * @param p_d the fourth coordinate
     */
    public void setD(double p_d)
    {
        getnumList().set(3, new Double(p_d));
    }

    /**
     * getTx - returns the tx coordinate
     *
     * @return double - the tx coordinate
     */
    public double getTx()
    {
        return ((Double)getnumList().get(4)).doubleValue();
    }

    /**
     * setTx - sets the tx coordinate
     *
     * @param p_tx the tx coordinate
     */
    public void setTx(double p_tx)
    {
        getnumList().set(4, new Double(p_tx));
    }

    /**
     * getTy - returns the ty coordinate
     *
     * @return double - the ty coordinate
     */
    public double getTy()
    {
        return ((Double)getnumList().get(5)).doubleValue();
    }

    /**
     * setTy - sets the ty coordinate
     *
     * @param p_y the ty coordinate
     */
    public void setTy(double p_ty)
    {
        getnumList().set(5, new Double(p_ty));
    }

    /**
     * equals - returns true if both JDFMatrices are equal, otherwise false
     *
     * @return boolean - true if equal otherwise false
     */
    public boolean equals(Object other)
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
            
        JDFMatrix m = (JDFMatrix) other;
        
        return  (Math.abs(this.getA() - m.getA())  <= EPSILON) &&
                (Math.abs(this.getB() - m.getB())  <= EPSILON) &&
                (Math.abs(this.getC() - m.getC())  <= EPSILON) &&
                (Math.abs(this.getD() - m.getD())  <= EPSILON) &&
                (Math.abs(this.getTx()- m.getTx()) <= EPSILON) &&
                (Math.abs(this.getTy()- m.getTy()) <= EPSILON) ;
    }
    
    /**
     * hashCode complements equals() to fulfill the equals/hashCode contract
     */
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
        return new AffineTransform(this.getA(),  this.getB(),
                                   this.getC(),  this.getD(),
                                   this.getTx(), this.getTy());
    }


    /**
     * setAffineTransform -
     *
     * @param AffineTransform matrix to be stored
     */
    public void setAffineTransform (AffineTransform affineTrans)
    {
        double [] flatMatrix = new double[6];
        affineTrans.getMatrix (flatMatrix);
        setA (flatMatrix[0]);
        setB (flatMatrix[1]);
        setC (flatMatrix[2]);
        setD (flatMatrix[3]);
        setTx(flatMatrix[4]);
        setTy(flatMatrix[5]);
    }

    /**
     * shifts Tx and Ty by the amount specified
     * @param tX shift in x direction
     * @param tY shift in y direction
     */
    public void shift(double tx, double ty)
    {
        setTx(getTx()+tx);
        setTy(getTy()+ty);        
    }
}
