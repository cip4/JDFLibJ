/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of 
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
 * JDFCMYKColor.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

/**
 * This class is a representation of a CMYK color (JDFCMYKColor). It is a blank separated list of
 * double values consisting of C the cyan color, M the magenta color, Y the yellow color and K the
 * black color value.
 */
public class JDFCMYKColor extends JDFNumList
{
    //**************************************** Constructors ****************************************
    /**
     * constructs a CMYK color with all values set to 0.0 Double
     */
    public JDFCMYKColor()
    {
        super(MAX_CMYK_COLOR);
    }

    /**
     * constructs a CMYK color with the given vector
     *
     * @param Vector v - the given vector
     *
     * @throws DataFormatException - if the Vector has not a valid format
     */
    public JDFCMYKColor(Vector v) throws DataFormatException
    {
        super(v);
    }

    /**
     * constructs a CMYK color with the given String
     *
     * @param String s - the given String
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFCMYKColor(String s) throws DataFormatException
    {
        super(s);
    }

    /**
     * constructs a CMYK color with a given JDFCMYKColor
     *
     * @param JDFCMYKColor cmyk - the given CMYK colors
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFCMYKColor(JDFCMYKColor cmyk) throws DataFormatException
    {
        this(cmyk.toString());
    }

    /**
     * constructs a CMYK color with a given JDFNumberList
     *
     * @param JDFNumberList nl - the given number list
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFCMYKColor(JDFNumberList nl) throws DataFormatException
    {
        super(nl);
    }

    /**
     * constructs a new CMYK color with the given double values
     *
     * @param double c - the value c
     * @param double m - the value m
     * @param double y - the value y
     * @param double k - the value k
     */
    public JDFCMYKColor(double c, double m, double y, double k)
    {
        super(MAX_CMYK_COLOR);
        m_numList.set(0, new Double(c));
        m_numList.set(1, new Double(m));
        m_numList.set(2, new Double(y));
        m_numList.set(3, new Double(k));
    }

    //**************************************** Methods *********************************************
    /**
     * isValid - the size of the vector must be 4 and all instances are Double types
     *
     * @throws DataFormatException - if the Vector has not a valid format
     */
    public void isValid() throws DataFormatException
    {
        if (m_numList.size() != MAX_CMYK_COLOR)
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
    }

    /**
     * getC - returns the value C of the CMYK color
     *
     * @return double - the value C of the CMYK color
     */
    public double getC()
    {
        return ((Double)m_numList.elementAt(0)).doubleValue();
    }

    /**
     * setC - sets the value C of the CMYK color
     *
     * @param c the value C of the CMYK color
     */
    public void setC(double c)
    {
        m_numList.set(0, new Double(c));
    }

    /**
     * getM - returns the value M of the CMYK color
     *
     * @return double - the value M of the CMYK color
     */
    public double getM()
    {
        return ((Double)m_numList.elementAt(1)).doubleValue();
    }

    /**
     * setM - sets the value M of the CMYK color
     *
     * @param m the value M of the CMYK color
     */
    public void setM(double m)
    {
        m_numList.set(1, new Double(m));
    }

    /**
     * getY - returns the value Y of the CMYK color
     *
     * @return double - the value Y of the CMYK color
     */
    public double getY()
    {
        return ((Double)m_numList.elementAt(2)).doubleValue();
    }

    /**
     * setY - sets the value Y of the CMYK color
     *
     * @param y the value Y of the CMYK color
     */
    public void setY(double y)
    {
        m_numList.set(2, new Double(y));
    }

    /**
     * getK - returns the value K of the CMYK color
     *
     * @return double - the value K of the CMYK color
     */
    public double getK()
    {
        return ((Double)m_numList.elementAt(3)).doubleValue();
    }

    /**
     * setK - sets the value K of the CMYK color
     *
     * @param k the value K of the CMYK color
     */
    public void setK(double k)
    {
        m_numList.set(3, new Double(k));
    }

}