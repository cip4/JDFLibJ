/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFRectangle;
    /**
    *****************************************************************************
    class JDFAutoBoxArgument : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoBoxArgument extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.BOX, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumBox.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.MIRRORMARGINS, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumMirrorMargins.getEnum(0), null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.OFFSET, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.OVERLAP, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoBoxArgument
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoBoxArgument(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoBoxArgument
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoBoxArgument(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoBoxArgument
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoBoxArgument(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    /**
     * @return  the string representation
     */
    @Override
    public String toString()
    {
        return " JDFAutoBoxArgument[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for Box
        */

        public static class EnumBox extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumBox(String name)
            {
                super(name, m_startValue++);
            }

    /**
     * @param enumName the string to convert
     * @return the enum
     */
            public static EnumBox getEnum(String enumName)
            {
                return (EnumBox) getEnum(EnumBox.class, enumName);
            }

    /**
     * @param enumValue the integer to convert
     * @return the enum
     */
            public static EnumBox getEnum(int enumValue)
            {
                return (EnumBox) getEnum(EnumBox.class, enumValue);
            }

    /**
     * @return the map of enums
     */
            public static Map getEnumMap()
            {
                return getEnumMap(EnumBox.class);
            }

    /**
     * @return the list of enums
     */
            public static List getEnumList()
            {
                return getEnumList(EnumBox.class);
            }

    /**
     * @return the iterator
     */
            public static Iterator iterator()
            {
                return iterator(EnumBox.class);
            }

            public static final EnumBox ArtBox = new EnumBox("ArtBox");
            public static final EnumBox BleedBox = new EnumBox("BleedBox");
            public static final EnumBox CropBox = new EnumBox("CropBox");
            public static final EnumBox MarginsBox = new EnumBox("MarginsBox");
            public static final EnumBox MediaBox = new EnumBox("MediaBox");
            public static final EnumBox SlugBox = new EnumBox("SlugBox");
            public static final EnumBox TrimBox = new EnumBox("TrimBox");
        }      



        /**
        * Enumeration strings for MirrorMargins
        */

        public static class EnumMirrorMargins extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumMirrorMargins(String name)
            {
                super(name, m_startValue++);
            }

    /**
     * @param enumName the string to convert
     * @return the enum
     */
            public static EnumMirrorMargins getEnum(String enumName)
            {
                return (EnumMirrorMargins) getEnum(EnumMirrorMargins.class, enumName);
            }

    /**
     * @param enumValue the integer to convert
     * @return the enum
     */
            public static EnumMirrorMargins getEnum(int enumValue)
            {
                return (EnumMirrorMargins) getEnum(EnumMirrorMargins.class, enumValue);
            }

    /**
     * @return the map of enums
     */
            public static Map getEnumMap()
            {
                return getEnumMap(EnumMirrorMargins.class);
            }

    /**
     * @return the list of enums
     */
            public static List getEnumList()
            {
                return getEnumList(EnumMirrorMargins.class);
            }

    /**
     * @return the iterator
     */
            public static Iterator iterator()
            {
                return iterator(EnumMirrorMargins.class);
            }

            public static final EnumMirrorMargins Vertical = new EnumMirrorMargins("Vertical");
            public static final EnumMirrorMargins Horizontal = new EnumMirrorMargins("Horizontal");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Box
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Box
          * @param enumVar the enumVar to set the attribute to
          */
        public void setBox(EnumBox enumVar)
        {
            setAttribute(AttributeName.BOX, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Box
          * @return the value of the attribute
          */
        public EnumBox getBox()
        {
            return EnumBox.getEnum(getAttribute(AttributeName.BOX, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MirrorMargins
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute MirrorMargins
          * @param enumVar the enumVar to set the attribute to
          */
        public void setMirrorMargins(EnumMirrorMargins enumVar)
        {
            setAttribute(AttributeName.MIRRORMARGINS, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute MirrorMargins
          * @return the value of the attribute
          */
        public EnumMirrorMargins getMirrorMargins()
        {
            return EnumMirrorMargins.getEnum(getAttribute(AttributeName.MIRRORMARGINS, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Offset
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Offset
          * @param value the value to set the attribute to
          */
        public void setOffset(JDFRectangle value)
        {
            setAttribute(AttributeName.OFFSET, value, null);
        }

        /**
          * (20) get JDFRectangle attribute Offset
          * @return JDFRectangle the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getOffset()
        {
            String strAttrName = getAttribute(AttributeName.OFFSET, null, JDFCoreConstants.EMPTYSTRING);
            JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Overlap
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Overlap
          * @param value the value to set the attribute to
          */
        public void setOverlap(boolean value)
        {
            setAttribute(AttributeName.OVERLAP, value, null);
        }

        /**
          * (18) get boolean attribute Overlap
          * @return boolean the value of the attribute
          */
        public boolean getOverlap()
        {
            return getBoolAttribute(AttributeName.OVERLAP, null, false);
        }

}// end namespace JDF
