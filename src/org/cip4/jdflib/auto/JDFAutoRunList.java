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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFByteMap;
import org.cip4.jdflib.resource.process.JDFDisposition;
import org.cip4.jdflib.resource.process.JDFDynamicInput;
import org.cip4.jdflib.resource.process.JDFInsertSheet;
import org.cip4.jdflib.resource.process.JDFInterpretedPDLData;
import org.cip4.jdflib.resource.process.JDFLayoutElement;

public abstract class JDFAutoRunList extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[25];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ISPAGE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.PAGECOPIES, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, "1");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.SETCOPIES, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, "1");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.COMPONENTGRANULARITY, 0x44443311, AttributeInfo.EnumAttributeType.enumeration, EnumComponentGranularity.getEnum(0), null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.DIRECTORY, 0x33333333, AttributeInfo.EnumAttributeType.URL, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.DOCNAMES, 0x33333333, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.DOCS, 0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.ENDOFBUNDLEITEM, 0x33333311, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.ENDOFDOCUMENT, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.ENDOFSET, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.FIRSTPAGE, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.IGNORECONTEXT, 0x33331111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.LOGICALPAGE, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.NDOC, 0x44444431, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[14] = new AtrInfoTable(AttributeName.NPAGE, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[15] = new AtrInfoTable(AttributeName.NSET, 0x44444431, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[16] = new AtrInfoTable(AttributeName.PAGELISTINDEX, 0x33333311, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[17] = new AtrInfoTable(AttributeName.PAGENAMES, 0x33333333, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
        atrInfoTable[18] = new AtrInfoTable(AttributeName.PAGES, 0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[19] = new AtrInfoTable(AttributeName.RUNTAG, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[20] = new AtrInfoTable(AttributeName.SETNAMES, 0x33333331, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
        atrInfoTable[21] = new AtrInfoTable(AttributeName.SETS, 0x33333331, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[22] = new AtrInfoTable(AttributeName.SHEETSIDES, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumSheetSides.getEnum(0), null);
        atrInfoTable[23] = new AtrInfoTable(AttributeName.SKIPPAGE, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[24] = new AtrInfoTable(AttributeName.SORTED, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[7];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.BYTEMAP, 0x66666666);
        elemInfoTable[1] = new ElemInfoTable(ElementName.DYNAMICINPUT, 0x44443333);
        elemInfoTable[2] = new ElemInfoTable(ElementName.INSERTSHEET, 0x33333333);
        elemInfoTable[3] = new ElemInfoTable(ElementName.LAYOUTELEMENT, 0x66666666);
        elemInfoTable[4] = new ElemInfoTable(ElementName.INTERPRETEDPDLDATA, 0x66666611);
        elemInfoTable[5] = new ElemInfoTable(ElementName.DISPOSITION, 0x66666666);
        elemInfoTable[6] = new ElemInfoTable(ElementName.PAGELIST, 0x66666666);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoRunList
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoRunList(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoRunList
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoRunList(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoRunList
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoRunList(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoRunList[  --> " + super.toString() + " ]";
    }


    public boolean  init()
    {
        boolean bRet = super.init();
        setResourceClass(JDFResource.EnumResourceClass.Parameter);
        return bRet;
    }


    public EnumResourceClass getValidClass()
    {
        return JDFResource.EnumResourceClass.Parameter;
    }


        /**
        * Enumeration strings for ComponentGranularity
        */

        public static class EnumComponentGranularity extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumComponentGranularity(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumComponentGranularity getEnum(String enumName)
            {
                return (EnumComponentGranularity) getEnum(EnumComponentGranularity.class, enumName);
            }

            public static EnumComponentGranularity getEnum(int enumValue)
            {
                return (EnumComponentGranularity) getEnum(EnumComponentGranularity.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumComponentGranularity.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumComponentGranularity.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumComponentGranularity.class);
            }

            public static final EnumComponentGranularity Page = new EnumComponentGranularity("Page");
            public static final EnumComponentGranularity Document = new EnumComponentGranularity("Document");
            public static final EnumComponentGranularity Set = new EnumComponentGranularity("Set");
            public static final EnumComponentGranularity All = new EnumComponentGranularity("All");
            public static final EnumComponentGranularity BundleItem = new EnumComponentGranularity("BundleItem");
        }      



        /**
        * Enumeration strings for SheetSides
        */

        public static class EnumSheetSides extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumSheetSides(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumSheetSides getEnum(String enumName)
            {
                return (EnumSheetSides) getEnum(EnumSheetSides.class, enumName);
            }

            public static EnumSheetSides getEnum(int enumValue)
            {
                return (EnumSheetSides) getEnum(EnumSheetSides.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumSheetSides.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumSheetSides.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumSheetSides.class);
            }

            public static final EnumSheetSides Front = new EnumSheetSides("Front");
            public static final EnumSheetSides Back = new EnumSheetSides("Back");
            public static final EnumSheetSides FrontBack = new EnumSheetSides("FrontBack");
            public static final EnumSheetSides BackFront = new EnumSheetSides("BackFront");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute IsPage
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IsPage
          * @param value: the value to set the attribute to
          */
        public void setIsPage(boolean value)
        {
            setAttribute(AttributeName.ISPAGE, value, null);
        }

        /**
          * (18) get boolean attribute IsPage
          * @return boolean the value of the attribute
          */
        public boolean getIsPage()
        {
            return getBoolAttribute(AttributeName.ISPAGE, null, true);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PageCopies
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PageCopies
          * @param value: the value to set the attribute to
          */
        public void setPageCopies(int value)
        {
            setAttribute(AttributeName.PAGECOPIES, value, null);
        }

        /**
          * (15) get int attribute PageCopies
          * @return int the value of the attribute
          */
        public int getPageCopies()
        {
            return getIntAttribute(AttributeName.PAGECOPIES, null, 1);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SetCopies
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SetCopies
          * @param value: the value to set the attribute to
          */
        public void setSetCopies(int value)
        {
            setAttribute(AttributeName.SETCOPIES, value, null);
        }

        /**
          * (15) get int attribute SetCopies
          * @return int the value of the attribute
          */
        public int getSetCopies()
        {
            return getIntAttribute(AttributeName.SETCOPIES, null, 1);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ComponentGranularity
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute ComponentGranularity
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setComponentGranularity(EnumComponentGranularity enumVar)
        {
            setAttribute(AttributeName.COMPONENTGRANULARITY, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute ComponentGranularity
          * @return the value of the attribute
          */
        public EnumComponentGranularity getComponentGranularity()
        {
            return EnumComponentGranularity.getEnum(getAttribute(AttributeName.COMPONENTGRANULARITY, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Directory
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Directory
          * @param value: the value to set the attribute to
          */
        public void setDirectory(String value)
        {
            setAttribute(AttributeName.DIRECTORY, value, null);
        }

        /**
          * (23) get String attribute Directory
          * @return the value of the attribute
          */
        public String getDirectory()
        {
            return getAttribute(AttributeName.DIRECTORY, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DocNames
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DocNames
          * @param value: the value to set the attribute to
          */
        public void setDocNames(JDFNameRangeList value)
        {
            setAttribute(AttributeName.DOCNAMES, value, null);
        }

        /**
          * (20) get JDFNameRangeList attribute DocNames
          * @return JDFNameRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNameRangeList
          */
        public JDFNameRangeList getDocNames()
        {
            String strAttrName = "";
            JDFNameRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.DOCNAMES, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNameRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Docs
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Docs
          * @param value: the value to set the attribute to
          */
        public void setDocs(JDFIntegerRangeList value)
        {
            setAttribute(AttributeName.DOCS, value, null);
        }

        /**
          * (20) get JDFIntegerRangeList attribute Docs
          * @return JDFIntegerRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerRangeList
          */
        public JDFIntegerRangeList getDocs()
        {
            String strAttrName = "";
            JDFIntegerRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.DOCS, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFIntegerRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute EndOfBundleItem
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute EndOfBundleItem
          * @param value: the value to set the attribute to
          */
        public void setEndOfBundleItem(boolean value)
        {
            setAttribute(AttributeName.ENDOFBUNDLEITEM, value, null);
        }

        /**
          * (18) get boolean attribute EndOfBundleItem
          * @return boolean the value of the attribute
          */
        public boolean getEndOfBundleItem()
        {
            return getBoolAttribute(AttributeName.ENDOFBUNDLEITEM, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute EndOfDocument
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute EndOfDocument
          * @param value: the value to set the attribute to
          */
        public void setEndOfDocument(boolean value)
        {
            setAttribute(AttributeName.ENDOFDOCUMENT, value, null);
        }

        /**
          * (18) get boolean attribute EndOfDocument
          * @return boolean the value of the attribute
          */
        public boolean getEndOfDocument()
        {
            return getBoolAttribute(AttributeName.ENDOFDOCUMENT, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute EndOfSet
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute EndOfSet
          * @param value: the value to set the attribute to
          */
        public void setEndOfSet(boolean value)
        {
            setAttribute(AttributeName.ENDOFSET, value, null);
        }

        /**
          * (18) get boolean attribute EndOfSet
          * @return boolean the value of the attribute
          */
        public boolean getEndOfSet()
        {
            return getBoolAttribute(AttributeName.ENDOFSET, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute FirstPage
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FirstPage
          * @param value: the value to set the attribute to
          */
        public void setFirstPage(int value)
        {
            setAttribute(AttributeName.FIRSTPAGE, value, null);
        }

        /**
          * (15) get int attribute FirstPage
          * @return int the value of the attribute
          */
        public int getFirstPage()
        {
            return getIntAttribute(AttributeName.FIRSTPAGE, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreContext
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreContext
          * @param value: the value to set the attribute to
          */
        public void setIgnoreContext(VString value)
        {
            setAttribute(AttributeName.IGNORECONTEXT, value, null);
        }

        /**
          * (21) get VString attribute IgnoreContext
          * @return VString the value of the attribute
          */
        public VString getIgnoreContext()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.IGNORECONTEXT, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute LogicalPage
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LogicalPage
          * @param value: the value to set the attribute to
          */
        public void setLogicalPage(int value)
        {
            setAttribute(AttributeName.LOGICALPAGE, value, null);
        }

        /**
          * (15) get int attribute LogicalPage
          * @return int the value of the attribute
          */
        public int getLogicalPage()
        {
            return getIntAttribute(AttributeName.LOGICALPAGE, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NDoc
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NDoc
          * @param value: the value to set the attribute to
          */
        public void setNDoc(int value)
        {
            setAttribute(AttributeName.NDOC, value, null);
        }

        /**
          * (15) get int attribute NDoc
          * @return int the value of the attribute
          */
        public int getNDoc()
        {
            return getIntAttribute(AttributeName.NDOC, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NPage
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NPage
          * @param value: the value to set the attribute to
          */
        public void setNPage(int value)
        {
            setAttribute(AttributeName.NPAGE, value, null);
        }

        /**
          * (15) get int attribute NPage
          * @return int the value of the attribute
          */
        public int getNPage()
        {
            return getIntAttribute(AttributeName.NPAGE, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NSet
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NSet
          * @param value: the value to set the attribute to
          */
        public void setNSet(int value)
        {
            setAttribute(AttributeName.NSET, value, null);
        }

        /**
          * (15) get int attribute NSet
          * @return int the value of the attribute
          */
        public int getNSet()
        {
            return getIntAttribute(AttributeName.NSET, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PageListIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PageListIndex
          * @param value: the value to set the attribute to
          */
        public void setPageListIndex(JDFIntegerRangeList value)
        {
            setAttribute(AttributeName.PAGELISTINDEX, value, null);
        }

        /**
          * (20) get JDFIntegerRangeList attribute PageListIndex
          * @return JDFIntegerRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerRangeList
          */
        public JDFIntegerRangeList getPageListIndex()
        {
            String strAttrName = "";
            JDFIntegerRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.PAGELISTINDEX, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFIntegerRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PageNames
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PageNames
          * @param value: the value to set the attribute to
          */
        public void setPageNames(JDFNameRangeList value)
        {
            setAttribute(AttributeName.PAGENAMES, value, null);
        }

        /**
          * (20) get JDFNameRangeList attribute PageNames
          * @return JDFNameRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNameRangeList
          */
        public JDFNameRangeList getPageNames()
        {
            String strAttrName = "";
            JDFNameRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.PAGENAMES, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNameRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Pages
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Pages
          * @param value: the value to set the attribute to
          */
        public void setPages(JDFIntegerRangeList value)
        {
            setAttribute(AttributeName.PAGES, value, null);
        }

        /**
          * (20) get JDFIntegerRangeList attribute Pages
          * @return JDFIntegerRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerRangeList
          */
        public JDFIntegerRangeList getPages()
        {
            String strAttrName = "";
            JDFIntegerRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.PAGES, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFIntegerRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RunTag
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RunTag
          * @param value: the value to set the attribute to
          */
        public void setRunTag(String value)
        {
            setAttribute(AttributeName.RUNTAG, value, null);
        }

        /**
          * (23) get String attribute RunTag
          * @return the value of the attribute
          */
        public String getRunTag()
        {
            return getAttribute(AttributeName.RUNTAG, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SetNames
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SetNames
          * @param value: the value to set the attribute to
          */
        public void setSetNames(JDFNameRangeList value)
        {
            setAttribute(AttributeName.SETNAMES, value, null);
        }

        /**
          * (20) get JDFNameRangeList attribute SetNames
          * @return JDFNameRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNameRangeList
          */
        public JDFNameRangeList getSetNames()
        {
            String strAttrName = "";
            JDFNameRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SETNAMES, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNameRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Sets
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Sets
          * @param value: the value to set the attribute to
          */
        public void setSets(JDFIntegerRangeList value)
        {
            setAttribute(AttributeName.SETS, value, null);
        }

        /**
          * (20) get JDFIntegerRangeList attribute Sets
          * @return JDFIntegerRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerRangeList
          */
        public JDFIntegerRangeList getSets()
        {
            String strAttrName = "";
            JDFIntegerRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SETS, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFIntegerRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SheetSides
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute SheetSides
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setSheetSides(EnumSheetSides enumVar)
        {
            setAttribute(AttributeName.SHEETSIDES, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute SheetSides
          * @return the value of the attribute
          */
        public EnumSheetSides getSheetSides()
        {
            return EnumSheetSides.getEnum(getAttribute(AttributeName.SHEETSIDES, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SkipPage
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SkipPage
          * @param value: the value to set the attribute to
          */
        public void setSkipPage(int value)
        {
            setAttribute(AttributeName.SKIPPAGE, value, null);
        }

        /**
          * (15) get int attribute SkipPage
          * @return int the value of the attribute
          */
        public int getSkipPage()
        {
            return getIntAttribute(AttributeName.SKIPPAGE, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Sorted
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Sorted
          * @param value: the value to set the attribute to
          */
        public void setSorted(boolean value)
        {
            setAttribute(AttributeName.SORTED, value, null);
        }

        /**
          * (18) get boolean attribute Sorted
          * @return boolean the value of the attribute
          */
        public boolean getSorted()
        {
            return getBoolAttribute(AttributeName.SORTED, null, false);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element ByteMap
     * @return JDFByteMap the element
     */
    public JDFByteMap getByteMap()
    {
        return (JDFByteMap) getElement(ElementName.BYTEMAP, null, 0);
    }

    /** (25) getCreateByteMap
     * 
     * @return JDFByteMap the element
     */
    public JDFByteMap getCreateByteMap()
    {
        return (JDFByteMap) getCreateElement_KElement(ElementName.BYTEMAP, null, 0);
    }

    /**
     * (29) append element ByteMap
     */
    public JDFByteMap appendByteMap() throws JDFException
    {
        return (JDFByteMap) appendElementN(ElementName.BYTEMAP, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refByteMap(JDFByteMap refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateDynamicInput
     * 
     * @param iSkip number of elements to skip
     * @return JDFDynamicInput the element
     */
    public JDFDynamicInput getCreateDynamicInput(int iSkip)
    {
        return (JDFDynamicInput)getCreateElement_KElement(ElementName.DYNAMICINPUT, null, iSkip);
    }

    /**
     * (27) const get element DynamicInput
     * @param iSkip number of elements to skip
     * @return JDFDynamicInput the element
     * default is getDynamicInput(0)     */
    public JDFDynamicInput getDynamicInput(int iSkip)
    {
        return (JDFDynamicInput) getElement(ElementName.DYNAMICINPUT, null, iSkip);
    }

    /**
     * Get all DynamicInput from the current element
     * 
     * @return Collection<JDFDynamicInput>
     */
    public Collection<JDFDynamicInput> getAllDynamicInput()
    {
        Vector<JDFDynamicInput> v = new Vector<JDFDynamicInput>();

        JDFDynamicInput kElem = (JDFDynamicInput) getFirstChildElement(ElementName.DYNAMICINPUT, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFDynamicInput) kElem.getNextSiblingElement(ElementName.DYNAMICINPUT, null);
        }

        return v;
    }

    /**
     * (30) append element DynamicInput
     */
    public JDFDynamicInput appendDynamicInput() throws JDFException
    {
        return (JDFDynamicInput) appendElement(ElementName.DYNAMICINPUT, null);
    }

    /** (26) getCreateInsertSheet
     * 
     * @param iSkip number of elements to skip
     * @return JDFInsertSheet the element
     */
    public JDFInsertSheet getCreateInsertSheet(int iSkip)
    {
        return (JDFInsertSheet)getCreateElement_KElement(ElementName.INSERTSHEET, null, iSkip);
    }

    /**
     * (27) const get element InsertSheet
     * @param iSkip number of elements to skip
     * @return JDFInsertSheet the element
     * default is getInsertSheet(0)     */
    public JDFInsertSheet getInsertSheet(int iSkip)
    {
        return (JDFInsertSheet) getElement(ElementName.INSERTSHEET, null, iSkip);
    }

    /**
     * Get all InsertSheet from the current element
     * 
     * @return Collection<JDFInsertSheet>
     */
    public Collection<JDFInsertSheet> getAllInsertSheet()
    {
        Vector<JDFInsertSheet> v = new Vector<JDFInsertSheet>();

        JDFInsertSheet kElem = (JDFInsertSheet) getFirstChildElement(ElementName.INSERTSHEET, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFInsertSheet) kElem.getNextSiblingElement(ElementName.INSERTSHEET, null);
        }

        return v;
    }

    /**
     * (30) append element InsertSheet
     */
    public JDFInsertSheet appendInsertSheet() throws JDFException
    {
        return (JDFInsertSheet) appendElement(ElementName.INSERTSHEET, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refInsertSheet(JDFInsertSheet refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element LayoutElement
     * @return JDFLayoutElement the element
     */
    public JDFLayoutElement getLayoutElement()
    {
        return (JDFLayoutElement) getElement(ElementName.LAYOUTELEMENT, null, 0);
    }

    /** (25) getCreateLayoutElement
     * 
     * @return JDFLayoutElement the element
     */
    public JDFLayoutElement getCreateLayoutElement()
    {
        return (JDFLayoutElement) getCreateElement_KElement(ElementName.LAYOUTELEMENT, null, 0);
    }

    /**
     * (29) append element LayoutElement
     */
    public JDFLayoutElement appendLayoutElement() throws JDFException
    {
        return (JDFLayoutElement) appendElementN(ElementName.LAYOUTELEMENT, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refLayoutElement(JDFLayoutElement refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element InterpretedPDLData
     * @return JDFInterpretedPDLData the element
     */
    public JDFInterpretedPDLData getInterpretedPDLData()
    {
        return (JDFInterpretedPDLData) getElement(ElementName.INTERPRETEDPDLDATA, null, 0);
    }

    /** (25) getCreateInterpretedPDLData
     * 
     * @return JDFInterpretedPDLData the element
     */
    public JDFInterpretedPDLData getCreateInterpretedPDLData()
    {
        return (JDFInterpretedPDLData) getCreateElement_KElement(ElementName.INTERPRETEDPDLDATA, null, 0);
    }

    /**
     * (29) append element InterpretedPDLData
     */
    public JDFInterpretedPDLData appendInterpretedPDLData() throws JDFException
    {
        return (JDFInterpretedPDLData) appendElementN(ElementName.INTERPRETEDPDLDATA, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refInterpretedPDLData(JDFInterpretedPDLData refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element Disposition
     * @return JDFDisposition the element
     */
    public JDFDisposition getDisposition()
    {
        return (JDFDisposition) getElement(ElementName.DISPOSITION, null, 0);
    }

    /** (25) getCreateDisposition
     * 
     * @return JDFDisposition the element
     */
    public JDFDisposition getCreateDisposition()
    {
        return (JDFDisposition) getCreateElement_KElement(ElementName.DISPOSITION, null, 0);
    }

    /**
     * (29) append element Disposition
     */
    public JDFDisposition appendDisposition() throws JDFException
    {
        return (JDFDisposition) appendElementN(ElementName.DISPOSITION, 1, null);
    }

    /**
     * (24) const get element PageList
     * @return JDFPageList the element
     */
    public JDFPageList getPageList()
    {
        return (JDFPageList) getElement(ElementName.PAGELIST, null, 0);
    }

    /** (25) getCreatePageList
     * 
     * @return JDFPageList the element
     */
    public JDFPageList getCreatePageList()
    {
        return (JDFPageList) getCreateElement_KElement(ElementName.PAGELIST, null, 0);
    }

    /**
     * (29) append element PageList
     */
    public JDFPageList appendPageList() throws JDFException
    {
        return (JDFPageList) appendElementN(ElementName.PAGELIST, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refPageList(JDFPageList refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
