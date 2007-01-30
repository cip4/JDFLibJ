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

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.process.JDFElementColorParams;
import org.cip4.jdflib.resource.process.JDFImageCompressionParams;
import org.cip4.jdflib.resource.process.JDFPageElement;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;
import org.cip4.jdflib.resource.process.prepress.JDFScreeningParams;
    /*
    *****************************************************************************
    class JDFAutoPageData : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoPageData extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[20];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ASSEMBLYID, 0x44444333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.ASSEMBLYIDS, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.CATALOGID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.CATALOGDETAILS, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.FOLDOUTPAGES, 0x33333333, AttributeInfo.EnumAttributeType.IntegerList, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.HASBLEEDS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.ISBLANK, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.ISPRINTABLE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.ISTRAPPED, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.JOBID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.PAGEFORMAT, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.PAGELABEL, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.PAGELABELPREFIX, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.PAGELABELSUFFIX, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[14] = new AtrInfoTable(AttributeName.PAGESTATUS, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[15] = new AtrInfoTable(AttributeName.PRODUCTID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[16] = new AtrInfoTable(AttributeName.SOURCEBLEEDBOX, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[17] = new AtrInfoTable(AttributeName.SOURCECLIPBOX, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[18] = new AtrInfoTable(AttributeName.SOURCETRIMBOX, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[19] = new AtrInfoTable(AttributeName.TEMPLATE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[5];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.ELEMENTCOLORPARAMS, 0x66666666);
        elemInfoTable[1] = new ElemInfoTable(ElementName.IMAGECOMPRESSIONPARAMS, 0x66666666);
        elemInfoTable[2] = new ElemInfoTable(ElementName.PAGEELEMENT, 0x33333111);
        elemInfoTable[3] = new ElemInfoTable(ElementName.SCREENINGPARAMS, 0x66666666);
        elemInfoTable[4] = new ElemInfoTable(ElementName.SEPARATIONSPEC, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoPageData
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPageData(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPageData
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPageData(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPageData
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPageData(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoPageData[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute AssemblyID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AssemblyID
          * @param value: the value to set the attribute to
          */
        public void setAssemblyID(String value)
        {
            setAttribute(AttributeName.ASSEMBLYID, value, null);
        }



        /**
          * (23) get String attribute AssemblyID
          * @return the value of the attribute
          */
        public String getAssemblyID()
        {
            return getAttribute(AttributeName.ASSEMBLYID, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute AssemblyIDs
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AssemblyIDs
          * @param value: the value to set the attribute to
          */
        public void setAssemblyIDs(VString value)
        {
            setAttribute(AttributeName.ASSEMBLYIDS, value, null);
        }



        /**
          * (21) get VString attribute AssemblyIDs
          * @return VString the value of the attribute
          */
        public VString getAssemblyIDs()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.ASSEMBLYIDS, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute CatalogID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CatalogID
          * @param value: the value to set the attribute to
          */
        public void setCatalogID(String value)
        {
            setAttribute(AttributeName.CATALOGID, value, null);
        }



        /**
          * (23) get String attribute CatalogID
          * @return the value of the attribute
          */
        public String getCatalogID()
        {
            return getAttribute(AttributeName.CATALOGID, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute CatalogDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CatalogDetails
          * @param value: the value to set the attribute to
          */
        public void setCatalogDetails(String value)
        {
            setAttribute(AttributeName.CATALOGDETAILS, value, null);
        }



        /**
          * (23) get String attribute CatalogDetails
          * @return the value of the attribute
          */
        public String getCatalogDetails()
        {
            return getAttribute(AttributeName.CATALOGDETAILS, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute FoldOutPages
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FoldOutPages
          * @param value: the value to set the attribute to
          */
        public void setFoldOutPages(JDFIntegerList value)
        {
            setAttribute(AttributeName.FOLDOUTPAGES, value, null);
        }



        /**
          * (20) get JDFIntegerList attribute FoldOutPages
          * @return JDFIntegerList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerList
          */
        public JDFIntegerList getFoldOutPages()
        {
            String strAttrName = "";
            JDFIntegerList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.FOLDOUTPAGES, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFIntegerList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
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
        Methods for Attribute JobID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute JobID
          * @param value: the value to set the attribute to
          */
        public void setJobID(String value)
        {
            setAttribute(AttributeName.JOBID, value, null);
        }



        /**
          * (23) get String attribute JobID
          * @return the value of the attribute
          */
        public String getJobID()
        {
            return getAttribute(AttributeName.JOBID, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PageFormat
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PageFormat
          * @param value: the value to set the attribute to
          */
        public void setPageFormat(String value)
        {
            setAttribute(AttributeName.PAGEFORMAT, value, null);
        }



        /**
          * (23) get String attribute PageFormat
          * @return the value of the attribute
          */
        public String getPageFormat()
        {
            return getAttribute(AttributeName.PAGEFORMAT, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PageLabel
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PageLabel
          * @param value: the value to set the attribute to
          */
        public void setPageLabel(String value)
        {
            setAttribute(AttributeName.PAGELABEL, value, null);
        }



        /**
          * (23) get String attribute PageLabel
          * @return the value of the attribute
          */
        public String getPageLabel()
        {
            return getAttribute(AttributeName.PAGELABEL, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PageLabelPrefix
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PageLabelPrefix
          * @param value: the value to set the attribute to
          */
        public void setPageLabelPrefix(String value)
        {
            setAttribute(AttributeName.PAGELABELPREFIX, value, null);
        }



        /**
          * (23) get String attribute PageLabelPrefix
          * @return the value of the attribute
          */
        public String getPageLabelPrefix()
        {
            return getAttribute(AttributeName.PAGELABELPREFIX, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PageLabelSuffix
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PageLabelSuffix
          * @param value: the value to set the attribute to
          */
        public void setPageLabelSuffix(String value)
        {
            setAttribute(AttributeName.PAGELABELSUFFIX, value, null);
        }



        /**
          * (23) get String attribute PageLabelSuffix
          * @return the value of the attribute
          */
        public String getPageLabelSuffix()
        {
            return getAttribute(AttributeName.PAGELABELSUFFIX, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PageStatus
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PageStatus
          * @param value: the value to set the attribute to
          */
        public void setPageStatus(String value)
        {
            setAttribute(AttributeName.PAGESTATUS, value, null);
        }



        /**
          * (23) get String attribute PageStatus
          * @return the value of the attribute
          */
        public String getPageStatus()
        {
            return getAttribute(AttributeName.PAGESTATUS, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ProductID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ProductID
          * @param value: the value to set the attribute to
          */
        public void setProductID(String value)
        {
            setAttribute(AttributeName.PRODUCTID, value, null);
        }



        /**
          * (23) get String attribute ProductID
          * @return the value of the attribute
          */
        public String getProductID()
        {
            return getAttribute(AttributeName.PRODUCTID, null, JDFConstants.EMPTYSTRING);
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
     * (29) append elementElementColorParams
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
     * (29) append elementImageCompressionParams
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
    /** (26) getCreatePageElement
     * 
     * @param iSkip number of elements to skip
     * @return JDFPageElement the element
     */
    public JDFPageElement getCreatePageElement(int iSkip)
    {
        return (JDFPageElement)getCreateElement_KElement(ElementName.PAGEELEMENT, null, iSkip);
    }



    /**
     * (27) const get element PageElement
     * @param iSkip number of elements to skip
     * @return JDFPageElement the element
     * default is getPageElement(0)     */
    public JDFPageElement getPageElement(int iSkip)
    {
        return (JDFPageElement) getElement(ElementName.PAGEELEMENT, null, iSkip);
    }



    public JDFPageElement appendPageElement()
    {
        return (JDFPageElement) appendElement(ElementName.PAGEELEMENT, null);
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
     * (29) append elementScreeningParams
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



    public JDFSeparationSpec appendSeparationSpec()
    {
        return (JDFSeparationSpec) appendElement(ElementName.SEPARATIONSPEC, null);
    }

}// end namespace JDF
