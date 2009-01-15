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
import org.cip4.jdflib.core.JDFElement;

public abstract class JDFAutoBoxToBoxDifference extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.FROMBOX, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumFromBox.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.TOBOX, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumToBox.getEnum(0), null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoBoxToBoxDifference
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoBoxToBoxDifference(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoBoxToBoxDifference
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoBoxToBoxDifference(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoBoxToBoxDifference
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoBoxToBoxDifference(
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
        return " JDFAutoBoxToBoxDifference[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for FromBox
        */

        public static class EnumFromBox extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumFromBox(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumFromBox getEnum(String enumName)
            {
                return (EnumFromBox) getEnum(EnumFromBox.class, enumName);
            }

            public static EnumFromBox getEnum(int enumValue)
            {
                return (EnumFromBox) getEnum(EnumFromBox.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumFromBox.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumFromBox.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumFromBox.class);
            }

            public static final EnumFromBox ArtBox = new EnumFromBox("ArtBox");
            public static final EnumFromBox BleedBox = new EnumFromBox("BleedBox");
            public static final EnumFromBox CropBox = new EnumFromBox("CropBox");
            public static final EnumFromBox MarginsBox = new EnumFromBox("MarginsBox");
            public static final EnumFromBox MediaBox = new EnumFromBox("MediaBox");
            public static final EnumFromBox SlugBox = new EnumFromBox("SlugBox");
            public static final EnumFromBox TrimBox = new EnumFromBox("TrimBox");
        }      



        /**
        * Enumeration strings for ToBox
        */

        public static class EnumToBox extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumToBox(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumToBox getEnum(String enumName)
            {
                return (EnumToBox) getEnum(EnumToBox.class, enumName);
            }

            public static EnumToBox getEnum(int enumValue)
            {
                return (EnumToBox) getEnum(EnumToBox.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumToBox.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumToBox.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumToBox.class);
            }

            public static final EnumToBox ArtBox = new EnumToBox("ArtBox");
            public static final EnumToBox BleedBox = new EnumToBox("BleedBox");
            public static final EnumToBox CropBox = new EnumToBox("CropBox");
            public static final EnumToBox MarginsBox = new EnumToBox("MarginsBox");
            public static final EnumToBox MediaBox = new EnumToBox("MediaBox");
            public static final EnumToBox SlugBox = new EnumToBox("SlugBox");
            public static final EnumToBox TrimBox = new EnumToBox("TrimBox");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute FromBox
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute FromBox
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setFromBox(EnumFromBox enumVar)
        {
            setAttribute(AttributeName.FROMBOX, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute FromBox
          * @return the value of the attribute
          */
        public EnumFromBox getFromBox()
        {
            return EnumFromBox.getEnum(getAttribute(AttributeName.FROMBOX, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ToBox
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute ToBox
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setToBox(EnumToBox enumVar)
        {
            setAttribute(AttributeName.TOBOX, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute ToBox
          * @return the value of the attribute
          */
        public EnumToBox getToBox()
        {
            return EnumToBox.getEnum(getAttribute(AttributeName.TOBOX, null, null));
        }

}// end namespace JDF
