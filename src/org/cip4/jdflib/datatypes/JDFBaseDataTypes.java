/*
*
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
 * JDFBaseDataTypes.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.core.JDFConstants;



/**
 * all constants of the JDF library
 */
public interface JDFBaseDataTypes
{
    /**
     * max number of Lab color
     */
    public static final int MAX_LAB_COLOR = 3;

    /**
     * max number of RGB color
     */
    public static final int MAX_RGB_COLOR = 3;

    /**
     * max number of CMYK color
     */
    public static final int MAX_CMYK_COLOR = 4;

    /**
     * max dimensions of a shape width, height, lenght
     */
    public static final int MAX_SHAPE_DIMENSION = 3;

    /**
     * max positions of rectangle coordinates lower left x, lower left y,
     * upper right x, upper right y
     */
    public static final int MAX_RECTANGLE_DIMENSION = 4;

    /**
     * max dimensions of a matrix a, b, c, d tx, ty
     */
    public static final int MAX_MATRIX_DIMENSION = 6;

    /**
     * max dimensions of a xy pair
     */
    public static final int MAX_XY_DIMENSION = 2;

    /**
     * max difference between two double values to be equal
     */
    public static final double EPSILON = 0.0000000001;

    
    /**
     * Enumeration for FitsValue method, 
     * switches between Allowed and Present testlists 
     */
    public static class EnumFitsValue extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;

        private EnumFitsValue(String name)
        {
            super(name, m_startValue++);
        }

        /**
         * @param   enumName the name of the enum object to return    
         * @return  the enum object if enumName is valid. Otherwise null
         */
        public static EnumFitsValue getEnum(String enumName)
        {
            return (EnumFitsValue) getEnum(EnumFitsValue.class, enumName);
        }

        /**
         * @param  enumValue the value of the enum object to return
         * @return the enum object if enumName is valid. Otherwise null
         */
        public static EnumFitsValue getEnum(int enumValue)
        {
            return (EnumFitsValue) getEnum(EnumFitsValue.class, enumValue);
        }

        /**
         * @return a map of all orientation enums
         */
        public static Map getEnumMap()
        {
            return getEnumMap(EnumFitsValue.class);
        }

        /**
         * @return a list of all orientation enums
         */
        public static List getEnumList()
        {
            return getEnumList(EnumFitsValue.class);
        }

        /**
         * @return an iterator over the enum objects
         */
        public static Iterator iterator()
        {
            return iterator(EnumFitsValue.class);
        }

        /**
         * @return a Vector with all String representatives of the enums
         * @deprecated
         */
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumFitsValue.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }

            return namesVector;
        }
         
        public static final EnumFitsValue Present     = new EnumFitsValue(JDFConstants.FITSVALUE_PRESENT);
        public static final EnumFitsValue Allowed     = new EnumFitsValue(JDFConstants.FITSVALUE_ALLOWED);
         
    }
    
    
         
    /** @link dependency */
    /*#JDFNumList lnkJDFNumList;*/
}