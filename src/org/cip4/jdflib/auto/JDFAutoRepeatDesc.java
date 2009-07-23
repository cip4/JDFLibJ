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
import org.apache.commons.lang.enums.ValuedEnum;    
import org.apache.xerces.dom.CoreDocumentImpl;      
import org.cip4.jdflib.core.*;

public abstract class JDFAutoRepeatDesc extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ALLOWEDROTATE, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumAllowedRotate.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.GUTTERX, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.GUTTERX2, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.GUTTERY, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.GUTTERY2, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.LAYOUTSTYLE, 0x33331111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.ORDERQUANTITY, 0x33331111, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.USEBLEEDS, 0x33331111, AttributeInfo.EnumAttributeType.boolean_, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoRepeatDesc
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoRepeatDesc(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoRepeatDesc
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoRepeatDesc(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoRepeatDesc
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoRepeatDesc(
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
        return " JDFAutoRepeatDesc[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for AllowedRotate
        */

        public static class EnumAllowedRotate extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumAllowedRotate(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumAllowedRotate getEnum(String enumName)
            {
                return (EnumAllowedRotate) getEnum(EnumAllowedRotate.class, enumName);
            }

            public static EnumAllowedRotate getEnum(int enumValue)
            {
                return (EnumAllowedRotate) getEnum(EnumAllowedRotate.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumAllowedRotate.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumAllowedRotate.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumAllowedRotate.class);
            }

            public static final EnumAllowedRotate None = new EnumAllowedRotate("None");
            public static final EnumAllowedRotate Grain = new EnumAllowedRotate("Grain");
            public static final EnumAllowedRotate MinorGrain = new EnumAllowedRotate("MinorGrain");
            public static final EnumAllowedRotate CrossGrain = new EnumAllowedRotate("CrossGrain");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute AllowedRotate
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute AllowedRotate
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setAllowedRotate(EnumAllowedRotate enumVar)
        {
            setAttribute(AttributeName.ALLOWEDROTATE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute AllowedRotate
          * @return the value of the attribute
          */
        public EnumAllowedRotate getAllowedRotate()
        {
            return EnumAllowedRotate.getEnum(getAttribute(AttributeName.ALLOWEDROTATE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute GutterX
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute GutterX
          * @param value: the value to set the attribute to
          */
        public void setGutterX(double value)
        {
            setAttribute(AttributeName.GUTTERX, value, null);
        }

        /**
          * (17) get double attribute GutterX
          * @return double the value of the attribute
          */
        public double getGutterX()
        {
            return getRealAttribute(AttributeName.GUTTERX, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute GutterX2
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute GutterX2
          * @param value: the value to set the attribute to
          */
        public void setGutterX2(double value)
        {
            setAttribute(AttributeName.GUTTERX2, value, null);
        }

        /**
          * (17) get double attribute GutterX2
          * @return double the value of the attribute
          */
        public double getGutterX2()
        {
            return getRealAttribute(AttributeName.GUTTERX2, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute GutterY
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute GutterY
          * @param value: the value to set the attribute to
          */
        public void setGutterY(double value)
        {
            setAttribute(AttributeName.GUTTERY, value, null);
        }

        /**
          * (17) get double attribute GutterY
          * @return double the value of the attribute
          */
        public double getGutterY()
        {
            return getRealAttribute(AttributeName.GUTTERY, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute GutterY2
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute GutterY2
          * @param value: the value to set the attribute to
          */
        public void setGutterY2(double value)
        {
            setAttribute(AttributeName.GUTTERY2, value, null);
        }

        /**
          * (17) get double attribute GutterY2
          * @return double the value of the attribute
          */
        public double getGutterY2()
        {
            return getRealAttribute(AttributeName.GUTTERY2, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute LayoutStyle
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LayoutStyle
          * @param value: the value to set the attribute to
          */
        public void setLayoutStyle(VString value)
        {
            setAttribute(AttributeName.LAYOUTSTYLE, value, null);
        }

        /**
          * (21) get VString attribute LayoutStyle
          * @return VString the value of the attribute
          */
        public VString getLayoutStyle()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.LAYOUTSTYLE, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute OrderQuantity
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OrderQuantity
          * @param value: the value to set the attribute to
          */
        public void setOrderQuantity(int value)
        {
            setAttribute(AttributeName.ORDERQUANTITY, value, null);
        }

        /**
          * (15) get int attribute OrderQuantity
          * @return int the value of the attribute
          */
        public int getOrderQuantity()
        {
            return getIntAttribute(AttributeName.ORDERQUANTITY, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute UseBleeds
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute UseBleeds
          * @param value: the value to set the attribute to
          */
        public void setUseBleeds(boolean value)
        {
            setAttribute(AttributeName.USEBLEEDS, value, null);
        }

        /**
          * (18) get boolean attribute UseBleeds
          * @return boolean the value of the attribute
          */
        public boolean getUseBleeds()
        {
            return getBoolAttribute(AttributeName.USEBLEEDS, null, false);
        }

}// end namespace JDF
