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
import org.cip4.jdflib.core.VString;
    /*
    *****************************************************************************
    class JDFAutoPart : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoPart extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[57];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.SORTING, 0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.SORTAMOUNT, 0x33333333, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.BINDERYSIGNATURENAME, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.BUNDLEITEMINDEX, 0x33333311, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.CELLINDEX, 0x33333311, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.CONDITION, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.DELIVERYUNIT0, 0x33333111, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.DELIVERYUNIT1, 0x33333111, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.DELIVERYUNIT2, 0x33333111, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.DELIVERYUNIT3, 0x33333111, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.DELIVERYUNIT4, 0x33333111, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.DELIVERYUNIT5, 0x33333111, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.DELIVERYUNIT6, 0x33333111, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.DELIVERYUNIT7, 0x33333111, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[14] = new AtrInfoTable(AttributeName.DELIVERYUNIT8, 0x33333111, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[15] = new AtrInfoTable(AttributeName.DELIVERYUNIT9, 0x33333111, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[16] = new AtrInfoTable(AttributeName.DOCINDEX, 0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[17] = new AtrInfoTable(AttributeName.DOCCOPIES, 0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[18] = new AtrInfoTable(AttributeName.DOCRUNINDEX, 0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[19] = new AtrInfoTable(AttributeName.DOCSHEETINDEX, 0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[20] = new AtrInfoTable(AttributeName.FOUNTAINNUMBER, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[21] = new AtrInfoTable(AttributeName.DOCTAGS, 0x33333111, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[22] = new AtrInfoTable(AttributeName.EDITION, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[23] = new AtrInfoTable(AttributeName.EDITIONVERSION, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[24] = new AtrInfoTable(AttributeName.ITEMNAMES, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[25] = new AtrInfoTable(AttributeName.LAYERIDS, 0x33333331, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[26] = new AtrInfoTable(AttributeName.LOCATION, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[27] = new AtrInfoTable(AttributeName.OPTION, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[28] = new AtrInfoTable(AttributeName.PAGENUMBER, 0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[29] = new AtrInfoTable(AttributeName.PAGETAGS, 0x33333111, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[30] = new AtrInfoTable(AttributeName.PARTVERSION, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[31] = new AtrInfoTable(AttributeName.PLATELAYOUT, 0x33333111, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[32] = new AtrInfoTable(AttributeName.PREFLIGHTRULE, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[33] = new AtrInfoTable(AttributeName.PREVIEWTYPE, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumPreviewType.getEnum(0), null);
        atrInfoTable[34] = new AtrInfoTable(AttributeName.RIBBONNAME, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[35] = new AtrInfoTable(AttributeName.RUN, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[36] = new AtrInfoTable(AttributeName.RUNINDEX, 0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[37] = new AtrInfoTable(AttributeName.RUNPAGE, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[38] = new AtrInfoTable(AttributeName.RUNSET, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[39] = new AtrInfoTable(AttributeName.RUNTAGS, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[40] = new AtrInfoTable(AttributeName.SECTIONINDEX, 0x33333311, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[41] = new AtrInfoTable(AttributeName.SEPARATION, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[42] = new AtrInfoTable(AttributeName.SETDOCINDEX, 0x33333311, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[43] = new AtrInfoTable(AttributeName.SETRUNINDEX, 0x33333311, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[44] = new AtrInfoTable(AttributeName.SETSHEETINDEX, 0x33333311, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[45] = new AtrInfoTable(AttributeName.SETTAGS, 0x33333111, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[46] = new AtrInfoTable(AttributeName.SETINDEX, 0x33333331, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[47] = new AtrInfoTable(AttributeName.SHEETINDEX, 0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[48] = new AtrInfoTable(AttributeName.SHEETNAME, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[49] = new AtrInfoTable(AttributeName.SIDE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumSide.getEnum(0), null);
        atrInfoTable[50] = new AtrInfoTable(AttributeName.SIGNATURENAME, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[51] = new AtrInfoTable(AttributeName.STATIONNAME, 0x33333333, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[52] = new AtrInfoTable(AttributeName.SUBRUN, 0x33333111, AttributeInfo.EnumAttributeType.Any, null, null);
        atrInfoTable[53] = new AtrInfoTable(AttributeName.TILEID, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[54] = new AtrInfoTable(AttributeName.WEBNAME, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[55] = new AtrInfoTable(AttributeName.WEBPRODUCT, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[56] = new AtrInfoTable(AttributeName.WEBSETUP, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoPart
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPart(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPart
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPart(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPart
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPart(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoPart[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for PreviewType
        */

        public static class EnumPreviewType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPreviewType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPreviewType getEnum(String enumName)
            {
                return (EnumPreviewType) getEnum(EnumPreviewType.class, enumName);
            }

            public static EnumPreviewType getEnum(int enumValue)
            {
                return (EnumPreviewType) getEnum(EnumPreviewType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPreviewType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPreviewType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPreviewType.class);
            }

            public static final EnumPreviewType Separation = new EnumPreviewType("Separation");
            public static final EnumPreviewType SeparatedThumbNail = new EnumPreviewType("SeparatedThumbNail");
            public static final EnumPreviewType SeparationRaw = new EnumPreviewType("SeparationRaw");
            public static final EnumPreviewType ThumbNail = new EnumPreviewType("ThumbNail");
            public static final EnumPreviewType Viewable = new EnumPreviewType("Viewable");
        }      



        /**
        * Enumeration strings for Side
        */

        public static class EnumSide extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumSide(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumSide getEnum(String enumName)
            {
                return (EnumSide) getEnum(EnumSide.class, enumName);
            }

            public static EnumSide getEnum(int enumValue)
            {
                return (EnumSide) getEnum(EnumSide.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumSide.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumSide.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumSide.class);
            }

            public static final EnumSide Front = new EnumSide("Front");
            public static final EnumSide Back = new EnumSide("Back");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Sorting
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Sorting
          * @param value: the value to set the attribute to
          */
        public void setSorting(String value)
        {
            setAttribute(AttributeName.SORTING, value, null);
        }

        /**
          * (23) get String attribute Sorting
          * @return the value of the attribute
          */
        public String getSorting()
        {
            return getAttribute(AttributeName.SORTING, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SortAmount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SortAmount
          * @param value: the value to set the attribute to
          */
        public void setSortAmount(String value)
        {
            setAttribute(AttributeName.SORTAMOUNT, value, null);
        }

        /**
          * (23) get String attribute SortAmount
          * @return the value of the attribute
          */
        public String getSortAmount()
        {
            return getAttribute(AttributeName.SORTAMOUNT, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BinderySignatureName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BinderySignatureName
          * @param value: the value to set the attribute to
          */
        public void setBinderySignatureName(String value)
        {
            setAttribute(AttributeName.BINDERYSIGNATURENAME, value, null);
        }

        /**
          * (23) get String attribute BinderySignatureName
          * @return the value of the attribute
          */
        public String getBinderySignatureName()
        {
            return getAttribute(AttributeName.BINDERYSIGNATURENAME, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BundleItemIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BundleItemIndex
          * @param value: the value to set the attribute to
          */
        public void setBundleItemIndex(String value)
        {
            setAttribute(AttributeName.BUNDLEITEMINDEX, value, null);
        }

        /**
          * (23) get String attribute BundleItemIndex
          * @return the value of the attribute
          */
        public String getBundleItemIndex()
        {
            return getAttribute(AttributeName.BUNDLEITEMINDEX, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CellIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CellIndex
          * @param value: the value to set the attribute to
          */
        public void setCellIndex(String value)
        {
            setAttribute(AttributeName.CELLINDEX, value, null);
        }

        /**
          * (23) get String attribute CellIndex
          * @return the value of the attribute
          */
        public String getCellIndex()
        {
            return getAttribute(AttributeName.CELLINDEX, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Condition
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Condition
          * @param value: the value to set the attribute to
          */
        public void setCondition(String value)
        {
            setAttribute(AttributeName.CONDITION, value, null);
        }

        /**
          * (23) get String attribute Condition
          * @return the value of the attribute
          */
        public String getCondition()
        {
            return getAttribute(AttributeName.CONDITION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeliveryUnit0
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DeliveryUnit0
          * @param value: the value to set the attribute to
          */
        public void setDeliveryUnit0(String value)
        {
            setAttribute(AttributeName.DELIVERYUNIT0, value, null);
        }

        /**
          * (23) get String attribute DeliveryUnit0
          * @return the value of the attribute
          */
        public String getDeliveryUnit0()
        {
            return getAttribute(AttributeName.DELIVERYUNIT0, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeliveryUnit1
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DeliveryUnit1
          * @param value: the value to set the attribute to
          */
        public void setDeliveryUnit1(String value)
        {
            setAttribute(AttributeName.DELIVERYUNIT1, value, null);
        }

        /**
          * (23) get String attribute DeliveryUnit1
          * @return the value of the attribute
          */
        public String getDeliveryUnit1()
        {
            return getAttribute(AttributeName.DELIVERYUNIT1, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeliveryUnit2
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DeliveryUnit2
          * @param value: the value to set the attribute to
          */
        public void setDeliveryUnit2(String value)
        {
            setAttribute(AttributeName.DELIVERYUNIT2, value, null);
        }

        /**
          * (23) get String attribute DeliveryUnit2
          * @return the value of the attribute
          */
        public String getDeliveryUnit2()
        {
            return getAttribute(AttributeName.DELIVERYUNIT2, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeliveryUnit3
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DeliveryUnit3
          * @param value: the value to set the attribute to
          */
        public void setDeliveryUnit3(String value)
        {
            setAttribute(AttributeName.DELIVERYUNIT3, value, null);
        }

        /**
          * (23) get String attribute DeliveryUnit3
          * @return the value of the attribute
          */
        public String getDeliveryUnit3()
        {
            return getAttribute(AttributeName.DELIVERYUNIT3, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeliveryUnit4
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DeliveryUnit4
          * @param value: the value to set the attribute to
          */
        public void setDeliveryUnit4(String value)
        {
            setAttribute(AttributeName.DELIVERYUNIT4, value, null);
        }

        /**
          * (23) get String attribute DeliveryUnit4
          * @return the value of the attribute
          */
        public String getDeliveryUnit4()
        {
            return getAttribute(AttributeName.DELIVERYUNIT4, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeliveryUnit5
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DeliveryUnit5
          * @param value: the value to set the attribute to
          */
        public void setDeliveryUnit5(String value)
        {
            setAttribute(AttributeName.DELIVERYUNIT5, value, null);
        }

        /**
          * (23) get String attribute DeliveryUnit5
          * @return the value of the attribute
          */
        public String getDeliveryUnit5()
        {
            return getAttribute(AttributeName.DELIVERYUNIT5, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeliveryUnit6
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DeliveryUnit6
          * @param value: the value to set the attribute to
          */
        public void setDeliveryUnit6(String value)
        {
            setAttribute(AttributeName.DELIVERYUNIT6, value, null);
        }

        /**
          * (23) get String attribute DeliveryUnit6
          * @return the value of the attribute
          */
        public String getDeliveryUnit6()
        {
            return getAttribute(AttributeName.DELIVERYUNIT6, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeliveryUnit7
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DeliveryUnit7
          * @param value: the value to set the attribute to
          */
        public void setDeliveryUnit7(String value)
        {
            setAttribute(AttributeName.DELIVERYUNIT7, value, null);
        }

        /**
          * (23) get String attribute DeliveryUnit7
          * @return the value of the attribute
          */
        public String getDeliveryUnit7()
        {
            return getAttribute(AttributeName.DELIVERYUNIT7, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeliveryUnit8
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DeliveryUnit8
          * @param value: the value to set the attribute to
          */
        public void setDeliveryUnit8(String value)
        {
            setAttribute(AttributeName.DELIVERYUNIT8, value, null);
        }

        /**
          * (23) get String attribute DeliveryUnit8
          * @return the value of the attribute
          */
        public String getDeliveryUnit8()
        {
            return getAttribute(AttributeName.DELIVERYUNIT8, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeliveryUnit9
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DeliveryUnit9
          * @param value: the value to set the attribute to
          */
        public void setDeliveryUnit9(String value)
        {
            setAttribute(AttributeName.DELIVERYUNIT9, value, null);
        }

        /**
          * (23) get String attribute DeliveryUnit9
          * @return the value of the attribute
          */
        public String getDeliveryUnit9()
        {
            return getAttribute(AttributeName.DELIVERYUNIT9, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DocIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DocIndex
          * @param value: the value to set the attribute to
          */
        public void setDocIndex(String value)
        {
            setAttribute(AttributeName.DOCINDEX, value, null);
        }

        /**
          * (23) get String attribute DocIndex
          * @return the value of the attribute
          */
        public String getDocIndex()
        {
            return getAttribute(AttributeName.DOCINDEX, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DocCopies
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DocCopies
          * @param value: the value to set the attribute to
          */
        public void setDocCopies(String value)
        {
            setAttribute(AttributeName.DOCCOPIES, value, null);
        }

        /**
          * (23) get String attribute DocCopies
          * @return the value of the attribute
          */
        public String getDocCopies()
        {
            return getAttribute(AttributeName.DOCCOPIES, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DocRunIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DocRunIndex
          * @param value: the value to set the attribute to
          */
        public void setDocRunIndex(String value)
        {
            setAttribute(AttributeName.DOCRUNINDEX, value, null);
        }

        /**
          * (23) get String attribute DocRunIndex
          * @return the value of the attribute
          */
        public String getDocRunIndex()
        {
            return getAttribute(AttributeName.DOCRUNINDEX, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DocSheetIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DocSheetIndex
          * @param value: the value to set the attribute to
          */
        public void setDocSheetIndex(String value)
        {
            setAttribute(AttributeName.DOCSHEETINDEX, value, null);
        }

        /**
          * (23) get String attribute DocSheetIndex
          * @return the value of the attribute
          */
        public String getDocSheetIndex()
        {
            return getAttribute(AttributeName.DOCSHEETINDEX, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute FountainNumber
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FountainNumber
          * @param value: the value to set the attribute to
          */
        public void setFountainNumber(String value)
        {
            setAttribute(AttributeName.FOUNTAINNUMBER, value, null);
        }

        /**
          * (23) get String attribute FountainNumber
          * @return the value of the attribute
          */
        public String getFountainNumber()
        {
            return getAttribute(AttributeName.FOUNTAINNUMBER, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DocTags
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DocTags
          * @param value: the value to set the attribute to
          */
        public void setDocTags(String value)
        {
            setAttribute(AttributeName.DOCTAGS, value, null);
        }

        /**
          * (23) get String attribute DocTags
          * @return the value of the attribute
          */
        public String getDocTags()
        {
            return getAttribute(AttributeName.DOCTAGS, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Edition
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Edition
          * @param value: the value to set the attribute to
          */
        public void setEdition(String value)
        {
            setAttribute(AttributeName.EDITION, value, null);
        }

        /**
          * (23) get String attribute Edition
          * @return the value of the attribute
          */
        public String getEdition()
        {
            return getAttribute(AttributeName.EDITION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute EditionVersion
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute EditionVersion
          * @param value: the value to set the attribute to
          */
        public void setEditionVersion(String value)
        {
            setAttribute(AttributeName.EDITIONVERSION, value, null);
        }

        /**
          * (23) get String attribute EditionVersion
          * @return the value of the attribute
          */
        public String getEditionVersion()
        {
            return getAttribute(AttributeName.EDITIONVERSION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ItemNames
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ItemNames
          * @param value: the value to set the attribute to
          */
        public void setItemNames(String value)
        {
            setAttribute(AttributeName.ITEMNAMES, value, null);
        }

        /**
          * (23) get String attribute ItemNames
          * @return the value of the attribute
          */
        public String getItemNames()
        {
            return getAttribute(AttributeName.ITEMNAMES, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute LayerIDs
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LayerIDs
          * @param value: the value to set the attribute to
          */
        public void setLayerIDs(String value)
        {
            setAttribute(AttributeName.LAYERIDS, value, null);
        }

        /**
          * (23) get String attribute LayerIDs
          * @return the value of the attribute
          */
        public String getLayerIDs()
        {
            return getAttribute(AttributeName.LAYERIDS, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Location
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Location
          * @param value: the value to set the attribute to
          */
        public void setLocation(String value)
        {
            setAttribute(AttributeName.LOCATION, value, null);
        }

        /**
          * (23) get String attribute Location
          * @return the value of the attribute
          */
        public String getLocation()
        {
            return getAttribute(AttributeName.LOCATION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Option
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Option
          * @param value: the value to set the attribute to
          */
        public void setOption(String value)
        {
            setAttribute(AttributeName.OPTION, value, null);
        }

        /**
          * (23) get String attribute Option
          * @return the value of the attribute
          */
        public String getOption()
        {
            return getAttribute(AttributeName.OPTION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PageNumber
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PageNumber
          * @param value: the value to set the attribute to
          */
        public void setPageNumber(String value)
        {
            setAttribute(AttributeName.PAGENUMBER, value, null);
        }

        /**
          * (23) get String attribute PageNumber
          * @return the value of the attribute
          */
        public String getPageNumber()
        {
            return getAttribute(AttributeName.PAGENUMBER, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PageTags
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PageTags
          * @param value: the value to set the attribute to
          */
        public void setPageTags(String value)
        {
            setAttribute(AttributeName.PAGETAGS, value, null);
        }

        /**
          * (23) get String attribute PageTags
          * @return the value of the attribute
          */
        public String getPageTags()
        {
            return getAttribute(AttributeName.PAGETAGS, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PartVersion
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PartVersion
          * @param value: the value to set the attribute to
          */
        public void setPartVersion(VString value)
        {
            setAttribute(AttributeName.PARTVERSION, value, null);
        }

        /**
          * (21) get VString attribute PartVersion
          * @return VString the value of the attribute
          */
        public VString getPartVersion()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.PARTVERSION, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PlateLayout
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PlateLayout
          * @param value: the value to set the attribute to
          */
        public void setPlateLayout(String value)
        {
            setAttribute(AttributeName.PLATELAYOUT, value, null);
        }

        /**
          * (23) get String attribute PlateLayout
          * @return the value of the attribute
          */
        public String getPlateLayout()
        {
            return getAttribute(AttributeName.PLATELAYOUT, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PreflightRule
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PreflightRule
          * @param value: the value to set the attribute to
          */
        public void setPreflightRule(String value)
        {
            setAttribute(AttributeName.PREFLIGHTRULE, value, null);
        }

        /**
          * (23) get String attribute PreflightRule
          * @return the value of the attribute
          */
        public String getPreflightRule()
        {
            return getAttribute(AttributeName.PREFLIGHTRULE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PreviewType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute PreviewType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPreviewType(EnumPreviewType enumVar)
        {
            setAttribute(AttributeName.PREVIEWTYPE, enumVar.getName(), null);
        }

        /**
          * (9) get attribute PreviewType
          * @return the value of the attribute
          */
        public EnumPreviewType getPreviewType()
        {
            return EnumPreviewType.getEnum(getAttribute(AttributeName.PREVIEWTYPE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RibbonName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RibbonName
          * @param value: the value to set the attribute to
          */
        public void setRibbonName(String value)
        {
            setAttribute(AttributeName.RIBBONNAME, value, null);
        }

        /**
          * (23) get String attribute RibbonName
          * @return the value of the attribute
          */
        public String getRibbonName()
        {
            return getAttribute(AttributeName.RIBBONNAME, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Run
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Run
          * @param value: the value to set the attribute to
          */
        public void setRun(String value)
        {
            setAttribute(AttributeName.RUN, value, null);
        }

        /**
          * (23) get String attribute Run
          * @return the value of the attribute
          */
        public String getRun()
        {
            return getAttribute(AttributeName.RUN, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RunIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RunIndex
          * @param value: the value to set the attribute to
          */
        public void setRunIndex(String value)
        {
            setAttribute(AttributeName.RUNINDEX, value, null);
        }

        /**
          * (23) get String attribute RunIndex
          * @return the value of the attribute
          */
        public String getRunIndex()
        {
            return getAttribute(AttributeName.RUNINDEX, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RunPage
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RunPage
          * @param value: the value to set the attribute to
          */
        public void setRunPage(String value)
        {
            setAttribute(AttributeName.RUNPAGE, value, null);
        }

        /**
          * (23) get String attribute RunPage
          * @return the value of the attribute
          */
        public String getRunPage()
        {
            return getAttribute(AttributeName.RUNPAGE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RunSet
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RunSet
          * @param value: the value to set the attribute to
          */
        public void setRunSet(String value)
        {
            setAttribute(AttributeName.RUNSET, value, null);
        }

        /**
          * (23) get String attribute RunSet
          * @return the value of the attribute
          */
        public String getRunSet()
        {
            return getAttribute(AttributeName.RUNSET, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RunTags
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RunTags
          * @param value: the value to set the attribute to
          */
        public void setRunTags(String value)
        {
            setAttribute(AttributeName.RUNTAGS, value, null);
        }

        /**
          * (23) get String attribute RunTags
          * @return the value of the attribute
          */
        public String getRunTags()
        {
            return getAttribute(AttributeName.RUNTAGS, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SectionIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SectionIndex
          * @param value: the value to set the attribute to
          */
        public void setSectionIndex(String value)
        {
            setAttribute(AttributeName.SECTIONINDEX, value, null);
        }

        /**
          * (23) get String attribute SectionIndex
          * @return the value of the attribute
          */
        public String getSectionIndex()
        {
            return getAttribute(AttributeName.SECTIONINDEX, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Separation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Separation
          * @param value: the value to set the attribute to
          */
        public void setSeparation(String value)
        {
            setAttribute(AttributeName.SEPARATION, value, null);
        }

        /**
          * (23) get String attribute Separation
          * @return the value of the attribute
          */
        public String getSeparation()
        {
            return getAttribute(AttributeName.SEPARATION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SetDocIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SetDocIndex
          * @param value: the value to set the attribute to
          */
        public void setSetDocIndex(String value)
        {
            setAttribute(AttributeName.SETDOCINDEX, value, null);
        }

        /**
          * (23) get String attribute SetDocIndex
          * @return the value of the attribute
          */
        public String getSetDocIndex()
        {
            return getAttribute(AttributeName.SETDOCINDEX, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SetRunIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SetRunIndex
          * @param value: the value to set the attribute to
          */
        public void setSetRunIndex(String value)
        {
            setAttribute(AttributeName.SETRUNINDEX, value, null);
        }

        /**
          * (23) get String attribute SetRunIndex
          * @return the value of the attribute
          */
        public String getSetRunIndex()
        {
            return getAttribute(AttributeName.SETRUNINDEX, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SetSheetIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SetSheetIndex
          * @param value: the value to set the attribute to
          */
        public void setSetSheetIndex(String value)
        {
            setAttribute(AttributeName.SETSHEETINDEX, value, null);
        }

        /**
          * (23) get String attribute SetSheetIndex
          * @return the value of the attribute
          */
        public String getSetSheetIndex()
        {
            return getAttribute(AttributeName.SETSHEETINDEX, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SetTags
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SetTags
          * @param value: the value to set the attribute to
          */
        public void setSetTags(String value)
        {
            setAttribute(AttributeName.SETTAGS, value, null);
        }

        /**
          * (23) get String attribute SetTags
          * @return the value of the attribute
          */
        public String getSetTags()
        {
            return getAttribute(AttributeName.SETTAGS, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SetIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SetIndex
          * @param value: the value to set the attribute to
          */
        public void setSetIndex(String value)
        {
            setAttribute(AttributeName.SETINDEX, value, null);
        }

        /**
          * (23) get String attribute SetIndex
          * @return the value of the attribute
          */
        public String getSetIndex()
        {
            return getAttribute(AttributeName.SETINDEX, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SheetIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SheetIndex
          * @param value: the value to set the attribute to
          */
        public void setSheetIndex(String value)
        {
            setAttribute(AttributeName.SHEETINDEX, value, null);
        }

        /**
          * (23) get String attribute SheetIndex
          * @return the value of the attribute
          */
        public String getSheetIndex()
        {
            return getAttribute(AttributeName.SHEETINDEX, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SheetName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SheetName
          * @param value: the value to set the attribute to
          */
        public void setSheetName(String value)
        {
            setAttribute(AttributeName.SHEETNAME, value, null);
        }

        /**
          * (23) get String attribute SheetName
          * @return the value of the attribute
          */
        public String getSheetName()
        {
            return getAttribute(AttributeName.SHEETNAME, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Side
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Side
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setSide(EnumSide enumVar)
        {
            setAttribute(AttributeName.SIDE, enumVar.getName(), null);
        }

        /**
          * (9) get attribute Side
          * @return the value of the attribute
          */
        public EnumSide getSide()
        {
            return EnumSide.getEnum(getAttribute(AttributeName.SIDE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SignatureName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SignatureName
          * @param value: the value to set the attribute to
          */
        public void setSignatureName(String value)
        {
            setAttribute(AttributeName.SIGNATURENAME, value, null);
        }

        /**
          * (23) get String attribute SignatureName
          * @return the value of the attribute
          */
        public String getSignatureName()
        {
            return getAttribute(AttributeName.SIGNATURENAME, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StationName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StationName
          * @param value: the value to set the attribute to
          */
        public void setStationName(String value)
        {
            setAttribute(AttributeName.STATIONNAME, value, null);
        }

        /**
          * (23) get String attribute StationName
          * @return the value of the attribute
          */
        public String getStationName()
        {
            return getAttribute(AttributeName.STATIONNAME, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SubRun
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SubRun
          * @param value: the value to set the attribute to
          */
        public void setSubRun(String value)
        {
            setAttribute(AttributeName.SUBRUN, value, null);
        }

        /**
          * (23) get String attribute SubRun
          * @return the value of the attribute
          */
        public String getSubRun()
        {
            return getAttribute(AttributeName.SUBRUN, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TileID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TileID
          * @param value: the value to set the attribute to
          */
        public void setTileID(String value)
        {
            setAttribute(AttributeName.TILEID, value, null);
        }

        /**
          * (23) get String attribute TileID
          * @return the value of the attribute
          */
        public String getTileID()
        {
            return getAttribute(AttributeName.TILEID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute WebName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute WebName
          * @param value: the value to set the attribute to
          */
        public void setWebName(String value)
        {
            setAttribute(AttributeName.WEBNAME, value, null);
        }

        /**
          * (23) get String attribute WebName
          * @return the value of the attribute
          */
        public String getWebName()
        {
            return getAttribute(AttributeName.WEBNAME, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute WebProduct
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute WebProduct
          * @param value: the value to set the attribute to
          */
        public void setWebProduct(String value)
        {
            setAttribute(AttributeName.WEBPRODUCT, value, null);
        }

        /**
          * (23) get String attribute WebProduct
          * @return the value of the attribute
          */
        public String getWebProduct()
        {
            return getAttribute(AttributeName.WEBPRODUCT, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute WebSetup
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute WebSetup
          * @param value: the value to set the attribute to
          */
        public void setWebSetup(String value)
        {
            setAttribute(AttributeName.WEBSETUP, value, null);
        }

        /**
          * (23) get String attribute WebSetup
          * @return the value of the attribute
          */
        public String getWebSetup()
        {
            return getAttribute(AttributeName.WEBSETUP, null, JDFConstants.EMPTYSTRING);
        }

}// end namespace JDF
