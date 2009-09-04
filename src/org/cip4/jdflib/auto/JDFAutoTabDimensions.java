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
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;

public abstract class JDFAutoTabDimensions extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.TABEDGE, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumTabEdge.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.TABEXTENSIONDISTANCE, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.TABOFFSET, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.TABSPERBANK, 0x33333111, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.TABSETCOLLATIONORDER, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.TABWIDTH, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoTabDimensions
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoTabDimensions(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoTabDimensions
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoTabDimensions(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoTabDimensions
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoTabDimensions(
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
        return " JDFAutoTabDimensions[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for TabEdge
        */

        public static class EnumTabEdge extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumTabEdge(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumTabEdge getEnum(String enumName)
            {
                return (EnumTabEdge) getEnum(EnumTabEdge.class, enumName);
            }

            public static EnumTabEdge getEnum(int enumValue)
            {
                return (EnumTabEdge) getEnum(EnumTabEdge.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumTabEdge.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumTabEdge.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumTabEdge.class);
            }

            public static final EnumTabEdge Left = new EnumTabEdge("Left");
            public static final EnumTabEdge Rigth = new EnumTabEdge("Rigth");
            public static final EnumTabEdge Top = new EnumTabEdge("Top");
            public static final EnumTabEdge Bottom = new EnumTabEdge("Bottom");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute TabEdge
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute TabEdge
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setTabEdge(EnumTabEdge enumVar)
        {
            setAttribute(AttributeName.TABEDGE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute TabEdge
          * @return the value of the attribute
          */
        public EnumTabEdge getTabEdge()
        {
            return EnumTabEdge.getEnum(getAttribute(AttributeName.TABEDGE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TabExtensionDistance
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TabExtensionDistance
          * @param value: the value to set the attribute to
          */
        public void setTabExtensionDistance(double value)
        {
            setAttribute(AttributeName.TABEXTENSIONDISTANCE, value, null);
        }

        /**
          * (17) get double attribute TabExtensionDistance
          * @return double the value of the attribute
          */
        public double getTabExtensionDistance()
        {
            return getRealAttribute(AttributeName.TABEXTENSIONDISTANCE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TabOffset
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TabOffset
          * @param value: the value to set the attribute to
          */
        public void setTabOffset(double value)
        {
            setAttribute(AttributeName.TABOFFSET, value, null);
        }

        /**
          * (17) get double attribute TabOffset
          * @return double the value of the attribute
          */
        public double getTabOffset()
        {
            return getRealAttribute(AttributeName.TABOFFSET, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TabsPerBank
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TabsPerBank
          * @param value: the value to set the attribute to
          */
        public void setTabsPerBank(int value)
        {
            setAttribute(AttributeName.TABSPERBANK, value, null);
        }

        /**
          * (15) get int attribute TabsPerBank
          * @return int the value of the attribute
          */
        public int getTabsPerBank()
        {
            return getIntAttribute(AttributeName.TABSPERBANK, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TabSetCollationOrder
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TabSetCollationOrder
          * @param value: the value to set the attribute to
          */
        public void setTabSetCollationOrder(String value)
        {
            setAttribute(AttributeName.TABSETCOLLATIONORDER, value, null);
        }

        /**
          * (23) get String attribute TabSetCollationOrder
          * @return the value of the attribute
          */
        public String getTabSetCollationOrder()
        {
            return getAttribute(AttributeName.TABSETCOLLATIONORDER, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TabWidth
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TabWidth
          * @param value: the value to set the attribute to
          */
        public void setTabWidth(double value)
        {
            setAttribute(AttributeName.TABWIDTH, value, null);
        }

        /**
          * (17) get double attribute TabWidth
          * @return double the value of the attribute
          */
        public double getTabWidth()
        {
            return getRealAttribute(AttributeName.TABWIDTH, null, 0.0);
        }

}// end namespace JDF
