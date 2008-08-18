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
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFDependencies;
import org.cip4.jdflib.resource.process.JDFElementColorParams;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFImageCompressionParams;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;
import org.cip4.jdflib.resource.process.prepress.JDFScreeningParams;

public abstract class JDFAutoLayoutElement extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[13];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.IGNOREPDLCOPIES, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.IGNOREPDLIMPOSITION, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.CLIPPATH, 0x33333333, AttributeInfo.EnumAttributeType.PDFPath, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.ELEMENTTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumElementType.getEnum(0), null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.HASBLEEDS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.ISBLANK, 0x33333311, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.ISPRINTABLE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.ISTRAPPED, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.PAGELISTINDEX, 0x33333311, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.SOURCEBLEEDBOX, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.SOURCECLIPBOX, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.SOURCETRIMBOX, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.TEMPLATE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[8];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COLORPOOL, 0x66666611);
        elemInfoTable[1] = new ElemInfoTable(ElementName.DEPENDENCIES, 0x66666611);
        elemInfoTable[2] = new ElemInfoTable(ElementName.ELEMENTCOLORPARAMS, 0x66666611);
        elemInfoTable[3] = new ElemInfoTable(ElementName.FILESPEC, 0x66666666);
        elemInfoTable[4] = new ElemInfoTable(ElementName.IMAGECOMPRESSIONPARAMS, 0x66666611);
        elemInfoTable[5] = new ElemInfoTable(ElementName.PAGELIST, 0x66666611);
        elemInfoTable[6] = new ElemInfoTable(ElementName.SCREENINGPARAMS, 0x66666611);
        elemInfoTable[7] = new ElemInfoTable(ElementName.SEPARATIONSPEC, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoLayoutElement
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoLayoutElement(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoLayoutElement
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoLayoutElement(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoLayoutElement
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoLayoutElement(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoLayoutElement[  --> " + super.toString() + " ]";
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
        * Enumeration strings for ElementType
        */

        public static class EnumElementType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumElementType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumElementType getEnum(String enumName)
            {
                return (EnumElementType) getEnum(EnumElementType.class, enumName);
            }

            public static EnumElementType getEnum(int enumValue)
            {
                return (EnumElementType) getEnum(EnumElementType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumElementType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumElementType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumElementType.class);
            }

            public static final EnumElementType Auxiliary = new EnumElementType("Auxiliary");
            public static final EnumElementType Barcode = new EnumElementType("Barcode");
            public static final EnumElementType Composed = new EnumElementType("Composed");
            public static final EnumElementType Document = new EnumElementType("Document");
            public static final EnumElementType Graphic = new EnumElementType("Graphic");
            public static final EnumElementType IdentificationField = new EnumElementType("IdentificationField");
            public static final EnumElementType Image = new EnumElementType("Image");
            public static final EnumElementType MultiDocument = new EnumElementType("MultiDocument");
            public static final EnumElementType MultiSet = new EnumElementType("MultiSet");
            public static final EnumElementType Page = new EnumElementType("Page");
            public static final EnumElementType Reservation = new EnumElementType("Reservation");
            public static final EnumElementType Surface = new EnumElementType("Surface");
            public static final EnumElementType Text = new EnumElementType("Text");
            public static final EnumElementType Tile = new EnumElementType("Tile");
            public static final EnumElementType Unknown = new EnumElementType("Unknown");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnorePDLCopies
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnorePDLCopies
          * @param value: the value to set the attribute to
          */
        public void setIgnorePDLCopies(boolean value)
        {
            setAttribute(AttributeName.IGNOREPDLCOPIES, value, null);
        }

        /**
          * (18) get boolean attribute IgnorePDLCopies
          * @return boolean the value of the attribute
          */
        public boolean getIgnorePDLCopies()
        {
            return getBoolAttribute(AttributeName.IGNOREPDLCOPIES, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnorePDLImposition
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnorePDLImposition
          * @param value: the value to set the attribute to
          */
        public void setIgnorePDLImposition(boolean value)
        {
            setAttribute(AttributeName.IGNOREPDLIMPOSITION, value, null);
        }

        /**
          * (18) get boolean attribute IgnorePDLImposition
          * @return boolean the value of the attribute
          */
        public boolean getIgnorePDLImposition()
        {
            return getBoolAttribute(AttributeName.IGNOREPDLIMPOSITION, null, true);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ClipPath
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ClipPath
          * @param value: the value to set the attribute to
          */
        public void setClipPath(String value)
        {
            setAttribute(AttributeName.CLIPPATH, value, null);
        }

        /**
          * (23) get String attribute ClipPath
          * @return the value of the attribute
          */
        public String getClipPath()
        {
            return getAttribute(AttributeName.CLIPPATH, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ElementType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute ElementType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setElementType(EnumElementType enumVar)
        {
            setAttribute(AttributeName.ELEMENTTYPE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute ElementType
          * @return the value of the attribute
          */
        public EnumElementType getElementType()
        {
            return EnumElementType.getEnum(getAttribute(AttributeName.ELEMENTTYPE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute HasBleeds
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HasBleeds
          * @param value: the value to set the attribute to
          */
        public void setHasBleeds(boolean value)
        {
            setAttribute(AttributeName.HASBLEEDS, value, null);
        }

        /**
          * (18) get boolean attribute HasBleeds
          * @return boolean the value of the attribute
          */
        public boolean getHasBleeds()
        {
            return getBoolAttribute(AttributeName.HASBLEEDS, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IsBlank
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IsBlank
          * @param value: the value to set the attribute to
          */
        public void setIsBlank(boolean value)
        {
            setAttribute(AttributeName.ISBLANK, value, null);
        }

        /**
          * (18) get boolean attribute IsBlank
          * @return boolean the value of the attribute
          */
        public boolean getIsBlank()
        {
            return getBoolAttribute(AttributeName.ISBLANK, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IsPrintable
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IsPrintable
          * @param value: the value to set the attribute to
          */
        public void setIsPrintable(boolean value)
        {
            setAttribute(AttributeName.ISPRINTABLE, value, null);
        }

        /**
          * (18) get boolean attribute IsPrintable
          * @return boolean the value of the attribute
          */
        public boolean getIsPrintable()
        {
            return getBoolAttribute(AttributeName.ISPRINTABLE, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IsTrapped
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IsTrapped
          * @param value: the value to set the attribute to
          */
        public void setIsTrapped(boolean value)
        {
            setAttribute(AttributeName.ISTRAPPED, value, null);
        }

        /**
          * (18) get boolean attribute IsTrapped
          * @return boolean the value of the attribute
          */
        public boolean getIsTrapped()
        {
            return getBoolAttribute(AttributeName.ISTRAPPED, null, false);
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
        Methods for Attribute SourceBleedBox
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SourceBleedBox
          * @param value: the value to set the attribute to
          */
        public void setSourceBleedBox(JDFRectangle value)
        {
            setAttribute(AttributeName.SOURCEBLEEDBOX, value, null);
        }

        /**
          * (20) get JDFRectangle attribute SourceBleedBox
          * @return JDFRectangle the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getSourceBleedBox()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SOURCEBLEEDBOX, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFRectangle(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SourceClipBox
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SourceClipBox
          * @param value: the value to set the attribute to
          */
        public void setSourceClipBox(JDFRectangle value)
        {
            setAttribute(AttributeName.SOURCECLIPBOX, value, null);
        }

        /**
          * (20) get JDFRectangle attribute SourceClipBox
          * @return JDFRectangle the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getSourceClipBox()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SOURCECLIPBOX, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFRectangle(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SourceTrimBox
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SourceTrimBox
          * @param value: the value to set the attribute to
          */
        public void setSourceTrimBox(JDFRectangle value)
        {
            setAttribute(AttributeName.SOURCETRIMBOX, value, null);
        }

        /**
          * (20) get JDFRectangle attribute SourceTrimBox
          * @return JDFRectangle the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getSourceTrimBox()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SOURCETRIMBOX, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFRectangle(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Template
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Template
          * @param value: the value to set the attribute to
          */
        public void setTemplate(boolean value)
        {
            setAttribute(AttributeName.TEMPLATE, value, null);
        }

        /**
          * (18) get boolean attribute Template
          * @return boolean the value of the attribute
          */
        public boolean getTemplate()
        {
            return getBoolAttribute(AttributeName.TEMPLATE, null, false);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element ColorPool
     * @return JDFColorPool the element
     */
    public JDFColorPool getColorPool()
    {
        return (JDFColorPool) getElement(ElementName.COLORPOOL, null, 0);
    }

    /** (25) getCreateColorPool
     * 
     * @return JDFColorPool the element
     */
    public JDFColorPool getCreateColorPool()
    {
        return (JDFColorPool) getCreateElement_KElement(ElementName.COLORPOOL, null, 0);
    }

    /**
     * (29) append element ColorPool
     */
    public JDFColorPool appendColorPool() throws JDFException
    {
        return (JDFColorPool) appendElementN(ElementName.COLORPOOL, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refColorPool(JDFColorPool refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element Dependencies
     * @return JDFDependencies the element
     */
    public JDFDependencies getDependencies()
    {
        return (JDFDependencies) getElement(ElementName.DEPENDENCIES, null, 0);
    }

    /** (25) getCreateDependencies
     * 
     * @return JDFDependencies the element
     */
    public JDFDependencies getCreateDependencies()
    {
        return (JDFDependencies) getCreateElement_KElement(ElementName.DEPENDENCIES, null, 0);
    }

    /**
     * (29) append element Dependencies
     */
    public JDFDependencies appendDependencies() throws JDFException
    {
        return (JDFDependencies) appendElementN(ElementName.DEPENDENCIES, 1, null);
    }

    /**
     * (24) const get element ElementColorParams
     * @return JDFElementColorParams the element
     */
    public JDFElementColorParams getElementColorParams()
    {
        return (JDFElementColorParams) getElement(ElementName.ELEMENTCOLORPARAMS, null, 0);
    }

    /** (25) getCreateElementColorParams
     * 
     * @return JDFElementColorParams the element
     */
    public JDFElementColorParams getCreateElementColorParams()
    {
        return (JDFElementColorParams) getCreateElement_KElement(ElementName.ELEMENTCOLORPARAMS, null, 0);
    }

    /**
     * (29) append element ElementColorParams
     */
    public JDFElementColorParams appendElementColorParams() throws JDFException
    {
        return (JDFElementColorParams) appendElementN(ElementName.ELEMENTCOLORPARAMS, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refElementColorParams(JDFElementColorParams refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element FileSpec
     * @return JDFFileSpec the element
     */
    public JDFFileSpec getFileSpec()
    {
        return (JDFFileSpec) getElement(ElementName.FILESPEC, null, 0);
    }

    /** (25) getCreateFileSpec
     * 
     * @return JDFFileSpec the element
     */
    public JDFFileSpec getCreateFileSpec()
    {
        return (JDFFileSpec) getCreateElement_KElement(ElementName.FILESPEC, null, 0);
    }

    /**
     * (29) append element FileSpec
     */
    public JDFFileSpec appendFileSpec() throws JDFException
    {
        return (JDFFileSpec) appendElementN(ElementName.FILESPEC, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refFileSpec(JDFFileSpec refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element ImageCompressionParams
     * @return JDFImageCompressionParams the element
     */
    public JDFImageCompressionParams getImageCompressionParams()
    {
        return (JDFImageCompressionParams) getElement(ElementName.IMAGECOMPRESSIONPARAMS, null, 0);
    }

    /** (25) getCreateImageCompressionParams
     * 
     * @return JDFImageCompressionParams the element
     */
    public JDFImageCompressionParams getCreateImageCompressionParams()
    {
        return (JDFImageCompressionParams) getCreateElement_KElement(ElementName.IMAGECOMPRESSIONPARAMS, null, 0);
    }

    /**
     * (29) append element ImageCompressionParams
     */
    public JDFImageCompressionParams appendImageCompressionParams() throws JDFException
    {
        return (JDFImageCompressionParams) appendElementN(ElementName.IMAGECOMPRESSIONPARAMS, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refImageCompressionParams(JDFImageCompressionParams refTarget)
    {
        refElement(refTarget);
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

    /**
     * (24) const get element ScreeningParams
     * @return JDFScreeningParams the element
     */
    public JDFScreeningParams getScreeningParams()
    {
        return (JDFScreeningParams) getElement(ElementName.SCREENINGPARAMS, null, 0);
    }

    /** (25) getCreateScreeningParams
     * 
     * @return JDFScreeningParams the element
     */
    public JDFScreeningParams getCreateScreeningParams()
    {
        return (JDFScreeningParams) getCreateElement_KElement(ElementName.SCREENINGPARAMS, null, 0);
    }

    /**
     * (29) append element ScreeningParams
     */
    public JDFScreeningParams appendScreeningParams() throws JDFException
    {
        return (JDFScreeningParams) appendElementN(ElementName.SCREENINGPARAMS, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refScreeningParams(JDFScreeningParams refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateSeparationSpec
     * 
     * @param iSkip number of elements to skip
     * @return JDFSeparationSpec the element
     */
    public JDFSeparationSpec getCreateSeparationSpec(int iSkip)
    {
        return (JDFSeparationSpec)getCreateElement_KElement(ElementName.SEPARATIONSPEC, null, iSkip);
    }

    /**
     * (27) const get element SeparationSpec
     * @param iSkip number of elements to skip
     * @return JDFSeparationSpec the element
     * default is getSeparationSpec(0)     */
    public JDFSeparationSpec getSeparationSpec(int iSkip)
    {
        return (JDFSeparationSpec) getElement(ElementName.SEPARATIONSPEC, null, iSkip);
    }

    /**
     * Get all SeparationSpec from the current element
     * 
     * @return Collection<JDFSeparationSpec>
     */
    public Collection<JDFSeparationSpec> getAllSeparationSpec()
    {
        Vector<JDFSeparationSpec> v = new Vector<JDFSeparationSpec>();

        JDFSeparationSpec kElem = (JDFSeparationSpec) getFirstChildElement(ElementName.SEPARATIONSPEC, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFSeparationSpec) kElem.getNextSiblingElement(ElementName.SEPARATIONSPEC, null);
        }

        return v;
    }

    /**
     * (30) append element SeparationSpec
     */
    public JDFSeparationSpec appendSeparationSpec() throws JDFException
    {
        return (JDFSeparationSpec) appendElement(ElementName.SEPARATIONSPEC, null);
    }

}// end namespace JDF
