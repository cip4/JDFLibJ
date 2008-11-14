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
import java.util.Vector;                            
import org.apache.xerces.dom.CoreDocumentImpl;      
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.resource.process.*;          
import org.cip4.jdflib.resource.process.prepress.*;

public abstract class JDFAutoContentData extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[10];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CATALOGID, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.CATALOGDETAILS, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.CONTENTREFS, 0x33331111, AttributeInfo.EnumAttributeType.IDREFS, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.CONTENTTYPE, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.HASBLEEDS, 0x33333111, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.ID, 0x33331111, AttributeInfo.EnumAttributeType.ID, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.ISBLANK, 0x33333111, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.ISTRAPPED, 0x33333111, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.JOBID, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.PRODUCTID, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[5];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.CONTENTMETADATA, 0x22222111);
        elemInfoTable[1] = new ElemInfoTable(ElementName.ELEMENTCOLORPARAMS, 0x66666111);
        elemInfoTable[2] = new ElemInfoTable(ElementName.IMAGECOMPRESSIONPARAMS, 0x66666111);
        elemInfoTable[3] = new ElemInfoTable(ElementName.SCREENINGPARAMS, 0x66666111);
        elemInfoTable[4] = new ElemInfoTable(ElementName.SEPARATIONSPEC, 0x33333111);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoContentData
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoContentData(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoContentData
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoContentData(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoContentData
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoContentData(
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
        return " JDFAutoContentData[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
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
        Methods for Attribute ContentRefs
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ContentRefs
          * @param value: the value to set the attribute to
          */
        public void setContentRefs(VString value)
        {
            setAttribute(AttributeName.CONTENTREFS, value, null);
        }

        /**
          * (21) get VString attribute ContentRefs
          * @return VString the value of the attribute
          */
        public VString getContentRefs()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.CONTENTREFS, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ContentType
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ContentType
          * @param value: the value to set the attribute to
          */
        public void setContentType(String value)
        {
            setAttribute(AttributeName.CONTENTTYPE, value, null);
        }

        /**
          * (23) get String attribute ContentType
          * @return the value of the attribute
          */
        public String getContentType()
        {
            return getAttribute(AttributeName.CONTENTTYPE, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute ID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ID
          * @param value: the value to set the attribute to
          */
        public void setID(String value)
        {
            setAttribute(AttributeName.ID, value, null);
        }

        /**
          * (23) get String attribute ID
          * @return the value of the attribute
          */
        @Override
		public String getID()
        {
            return getAttribute(AttributeName.ID, null, JDFConstants.EMPTYSTRING);
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

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateContentMetaData
     * 
     * @param iSkip number of elements to skip
     * @return JDFContentMetaData the element
     */
    public JDFContentMetaData getCreateContentMetaData(int iSkip)
    {
        return (JDFContentMetaData)getCreateElement_KElement(ElementName.CONTENTMETADATA, null, iSkip);
    }

    /**
     * (27) const get element ContentMetaData
     * @param iSkip number of elements to skip
     * @return JDFContentMetaData the element
     * default is getContentMetaData(0)     */
    public JDFContentMetaData getContentMetaData(int iSkip)
    {
        return (JDFContentMetaData) getElement(ElementName.CONTENTMETADATA, null, iSkip);
    }

    /**
     * Get all ContentMetaData from the current element
     * 
     * @return Collection<JDFContentMetaData>
     */
    public Collection<JDFContentMetaData> getAllContentMetaData()
    {
        Vector<JDFContentMetaData> v = new Vector<JDFContentMetaData>();

        JDFContentMetaData kElem = (JDFContentMetaData) getFirstChildElement(ElementName.CONTENTMETADATA, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFContentMetaData) kElem.getNextSiblingElement(ElementName.CONTENTMETADATA, null);
        }

        return v;
    }

    /**
     * (30) append element ContentMetaData
     */
    public JDFContentMetaData appendContentMetaData() throws JDFException
    {
        return (JDFContentMetaData) appendElement(ElementName.CONTENTMETADATA, null);
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
