/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2005 The International Cooperation for the Integration of
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

package org.cip4.jdflib.auto;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFXYPair;

public abstract class JDFAutoImageShift extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.POSITIONX, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumPositionX.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.POSITIONY, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumPositionY.getEnum(0), null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.SHIFTBACK, 0x33333331, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.SHIFTFRONT, 0x33333331, AttributeInfo.EnumAttributeType.XYPair, null, "0 0");
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoImageShift
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoImageShift(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoImageShift
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoImageShift(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoImageShift
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoImageShift(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    @Override
	public String toString()
    {
        return " JDFAutoImageShift[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for PositionX
        */

        public static class EnumPositionX extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPositionX(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPositionX getEnum(String enumName)
            {
                return (EnumPositionX) getEnum(EnumPositionX.class, enumName);
            }

            public static EnumPositionX getEnum(int enumValue)
            {
                return (EnumPositionX) getEnum(EnumPositionX.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPositionX.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPositionX.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPositionX.class);
            }

            public static final EnumPositionX Left = new EnumPositionX("Left");
            public static final EnumPositionX Right = new EnumPositionX("Right");
            public static final EnumPositionX Center = new EnumPositionX("Center");
            public static final EnumPositionX Spine = new EnumPositionX("Spine");
            public static final EnumPositionX None = new EnumPositionX("None");
        }      



        /**
        * Enumeration strings for PositionY
        */

        public static class EnumPositionY extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPositionY(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPositionY getEnum(String enumName)
            {
                return (EnumPositionY) getEnum(EnumPositionY.class, enumName);
            }

            public static EnumPositionY getEnum(int enumValue)
            {
                return (EnumPositionY) getEnum(EnumPositionY.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPositionY.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPositionY.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPositionY.class);
            }

            public static final EnumPositionY Bottom = new EnumPositionY("Bottom");
            public static final EnumPositionY Top = new EnumPositionY("Top");
            public static final EnumPositionY Center = new EnumPositionY("Center");
            public static final EnumPositionY Spine = new EnumPositionY("Spine");
            public static final EnumPositionY None = new EnumPositionY("None");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute PositionX
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute PositionX
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPositionX(EnumPositionX enumVar)
        {
            setAttribute(AttributeName.POSITIONX, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute PositionX
          * @return the value of the attribute
          */
        public EnumPositionX getPositionX()
        {
            return EnumPositionX.getEnum(getAttribute(AttributeName.POSITIONX, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PositionY
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute PositionY
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPositionY(EnumPositionY enumVar)
        {
            setAttribute(AttributeName.POSITIONY, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute PositionY
          * @return the value of the attribute
          */
        public EnumPositionY getPositionY()
        {
            return EnumPositionY.getEnum(getAttribute(AttributeName.POSITIONY, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ShiftBack
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ShiftBack
          * @param value: the value to set the attribute to
          */
        public void setShiftBack(JDFXYPair value)
        {
            setAttribute(AttributeName.SHIFTBACK, value, null);
        }

        /**
          * (20) get JDFXYPair attribute ShiftBack
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getShiftBack()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SHIFTBACK, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFXYPair(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ShiftFront
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ShiftFront
          * @param value: the value to set the attribute to
          */
        public void setShiftFront(JDFXYPair value)
        {
            setAttribute(AttributeName.SHIFTFRONT, value, null);
        }

        /**
          * (20) get JDFXYPair attribute ShiftFront
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getShiftFront()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SHIFTFRONT, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFXYPair(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

}// end namespace JDF
