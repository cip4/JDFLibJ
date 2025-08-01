/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFContentList;
import org.cip4.jdflib.resource.process.JDFElementColorParams;
import org.cip4.jdflib.resource.process.JDFImageCompressionParams;
import org.cip4.jdflib.resource.process.JDFPageData;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;
import org.cip4.jdflib.resource.process.prepress.JDFScreeningParams;

/**
 *****************************************************************************
 * class JDFAutoPageList : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPageList extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[13];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.TEMPLATE, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ASSEMBLYID, 0x4444444311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ASSEMBLYIDS, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.HASBLEEDS, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ISBLANK, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.ISPRINTABLE, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.ISTRAPPED, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.JOBID, 0x3333333311l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.PAGELABELPREFIX, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.PAGELABELSUFFIX, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.SOURCEBLEEDBOX, 0x3333333311l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.SOURCECLIPBOX, 0x3333333311l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.SOURCETRIMBOX, 0x3333333311l, AttributeInfo.EnumAttributeType.rectangle, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[8];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.ASSEMBLY, 0x6666666611l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.COLORPOOL, 0x6666666611l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.CONTENTLIST, 0x6666666611l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.IMAGECOMPRESSIONPARAMS, 0x6666666611l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.PAGEDATA, 0x3333333311l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.SCREENINGPARAMS, 0x6666666611l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.SEPARATIONSPEC, 0x3333333311l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.ELEMENTCOLORPARAMS, 0x6666666611l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPageList
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPageList(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPageList
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPageList(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPageList
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPageList(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		final boolean bRet = super.init();
		setResourceClass(JDFResource.EnumResourceClass.Parameter);
		return bRet;
	}

	/**
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Parameter;
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Template ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Template
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTemplate(boolean value)
	{
		setAttribute(AttributeName.TEMPLATE, value, null);
	}

	/**
	 * (18) get boolean attribute Template
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getTemplate()
	{
		return getBoolAttribute(AttributeName.TEMPLATE, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AssemblyID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AssemblyID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAssemblyID(String value)
	{
		setAttribute(AttributeName.ASSEMBLYID, value, null);
	}

	/**
	 * (23) get String attribute AssemblyID
	 *
	 * @return the value of the attribute
	 */
	public String getAssemblyID()
	{
		return getAttribute(AttributeName.ASSEMBLYID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AssemblyIDs ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AssemblyIDs
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAssemblyIDs(VString value)
	{
		setAttribute(AttributeName.ASSEMBLYIDS, value, null);
	}

	/**
	 * (21) get VString attribute AssemblyIDs
	 *
	 * @return VString the value of the attribute
	 */
	public VString getAssemblyIDs()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.ASSEMBLYIDS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute HasBleeds ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute HasBleeds
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHasBleeds(boolean value)
	{
		setAttribute(AttributeName.HASBLEEDS, value, null);
	}

	/**
	 * (18) get boolean attribute HasBleeds
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getHasBleeds()
	{
		return getBoolAttribute(AttributeName.HASBLEEDS, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute IsBlank ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IsBlank
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIsBlank(boolean value)
	{
		setAttribute(AttributeName.ISBLANK, value, null);
	}

	/**
	 * (18) get boolean attribute IsBlank
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIsBlank()
	{
		return getBoolAttribute(AttributeName.ISBLANK, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute IsPrintable ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IsPrintable
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIsPrintable(boolean value)
	{
		setAttribute(AttributeName.ISPRINTABLE, value, null);
	}

	/**
	 * (18) get boolean attribute IsPrintable
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIsPrintable()
	{
		return getBoolAttribute(AttributeName.ISPRINTABLE, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute IsTrapped ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IsTrapped
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIsTrapped(boolean value)
	{
		setAttribute(AttributeName.ISTRAPPED, value, null);
	}

	/**
	 * (18) get boolean attribute IsTrapped
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIsTrapped()
	{
		return getBoolAttribute(AttributeName.ISTRAPPED, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JobID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JobID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJobID(String value)
	{
		setAttribute(AttributeName.JOBID, value, null);
	}

	/**
	 * (23) get String attribute JobID
	 *
	 * @return the value of the attribute
	 */
	public String getJobID()
	{
		return getAttribute(AttributeName.JOBID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PageLabelPrefix
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PageLabelPrefix
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPageLabelPrefix(String value)
	{
		setAttribute(AttributeName.PAGELABELPREFIX, value, null);
	}

	/**
	 * (23) get String attribute PageLabelPrefix
	 *
	 * @return the value of the attribute
	 */
	public String getPageLabelPrefix()
	{
		return getAttribute(AttributeName.PAGELABELPREFIX, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PageLabelSuffix
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PageLabelSuffix
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPageLabelSuffix(String value)
	{
		setAttribute(AttributeName.PAGELABELSUFFIX, value, null);
	}

	/**
	 * (23) get String attribute PageLabelSuffix
	 *
	 * @return the value of the attribute
	 */
	public String getPageLabelSuffix()
	{
		return getAttribute(AttributeName.PAGELABELSUFFIX, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SourceBleedBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SourceBleedBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSourceBleedBox(JDFRectangle value)
	{
		setAttribute(AttributeName.SOURCEBLEEDBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute SourceBleedBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getSourceBleedBox()
	{
		final String strAttrName = getAttribute(AttributeName.SOURCEBLEEDBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SourceClipBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SourceClipBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSourceClipBox(JDFRectangle value)
	{
		setAttribute(AttributeName.SOURCECLIPBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute SourceClipBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getSourceClipBox()
	{
		final String strAttrName = getAttribute(AttributeName.SOURCECLIPBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SourceTrimBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SourceTrimBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSourceTrimBox(JDFRectangle value)
	{
		setAttribute(AttributeName.SOURCETRIMBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute SourceTrimBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getSourceTrimBox()
	{
		final String strAttrName = getAttribute(AttributeName.SOURCETRIMBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Assembly
	 *
	 * @return JDFAssembly the element
	 */
	public JDFAssembly getAssembly()
	{
		return (JDFAssembly) getElement(ElementName.ASSEMBLY, null, 0);
	}

	/**
	 * (25) getCreateAssembly
	 * 
	 * @return JDFAssembly the element
	 */
	public JDFAssembly getCreateAssembly()
	{
		return (JDFAssembly) getCreateElement_JDFElement(ElementName.ASSEMBLY, null, 0);
	}

	/**
	 * (29) append element Assembly
	 *
	 * @return JDFAssembly the element @ if the element already exists
	 */
	public JDFAssembly appendAssembly()
	{
		return (JDFAssembly) appendElementN(ElementName.ASSEMBLY, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refAssembly(JDFAssembly refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ColorPool
	 *
	 * @return JDFColorPool the element
	 */
	public JDFColorPool getColorPool()
	{
		return (JDFColorPool) getElement(ElementName.COLORPOOL, null, 0);
	}

	/**
	 * (25) getCreateColorPool
	 * 
	 * @return JDFColorPool the element
	 */
	public JDFColorPool getCreateColorPool()
	{
		return (JDFColorPool) getCreateElement_JDFElement(ElementName.COLORPOOL, null, 0);
	}

	/**
	 * (29) append element ColorPool
	 *
	 * @return JDFColorPool the element @ if the element already exists
	 */
	public JDFColorPool appendColorPool()
	{
		return (JDFColorPool) appendElementN(ElementName.COLORPOOL, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refColorPool(JDFColorPool refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ContentList
	 *
	 * @return JDFContentList the element
	 */
	public JDFContentList getContentList()
	{
		return (JDFContentList) getElement(ElementName.CONTENTLIST, null, 0);
	}

	/**
	 * (25) getCreateContentList
	 * 
	 * @return JDFContentList the element
	 */
	public JDFContentList getCreateContentList()
	{
		return (JDFContentList) getCreateElement_JDFElement(ElementName.CONTENTLIST, null, 0);
	}

	/**
	 * (29) append element ContentList
	 *
	 * @return JDFContentList the element @ if the element already exists
	 */
	public JDFContentList appendContentList()
	{
		return (JDFContentList) appendElementN(ElementName.CONTENTLIST, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refContentList(JDFContentList refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ImageCompressionParams
	 *
	 * @return JDFImageCompressionParams the element
	 */
	public JDFImageCompressionParams getImageCompressionParams()
	{
		return (JDFImageCompressionParams) getElement(ElementName.IMAGECOMPRESSIONPARAMS, null, 0);
	}

	/**
	 * (25) getCreateImageCompressionParams
	 * 
	 * @return JDFImageCompressionParams the element
	 */
	public JDFImageCompressionParams getCreateImageCompressionParams()
	{
		return (JDFImageCompressionParams) getCreateElement_JDFElement(ElementName.IMAGECOMPRESSIONPARAMS, null, 0);
	}

	/**
	 * (29) append element ImageCompressionParams
	 *
	 * @return JDFImageCompressionParams the element @ if the element already exists
	 */
	public JDFImageCompressionParams appendImageCompressionParams()
	{
		return (JDFImageCompressionParams) appendElementN(ElementName.IMAGECOMPRESSIONPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refImageCompressionParams(JDFImageCompressionParams refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element PageData
	 *
	 * @return JDFPageData the element
	 */
	public JDFPageData getPageData()
	{
		return (JDFPageData) getElement(ElementName.PAGEDATA, null, 0);
	}

	/**
	 * (25) getCreatePageData
	 * 
	 * @return JDFPageData the element
	 */
	public JDFPageData getCreatePageData()
	{
		return (JDFPageData) getCreateElement_JDFElement(ElementName.PAGEDATA, null, 0);
	}

	/**
	 * (26) getCreatePageData
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPageData the element
	 */
	public JDFPageData getCreatePageData(int iSkip)
	{
		return (JDFPageData) getCreateElement_JDFElement(ElementName.PAGEDATA, null, iSkip);
	}

	/**
	 * (27) const get element PageData
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPageData the element default is getPageData(0)
	 */
	public JDFPageData getPageData(int iSkip)
	{
		return (JDFPageData) getElement(ElementName.PAGEDATA, null, iSkip);
	}

	/**
	 * Get all PageData from the current element
	 * 
	 * @return Collection<JDFPageData>, null if none are available
	 */
	public Collection<JDFPageData> getAllPageData()
	{
		return getChildArrayByClass(JDFPageData.class, false, 0);
	}

	/**
	 * (30) append element PageData
	 *
	 * @return JDFPageData the element
	 */
	public JDFPageData appendPageData()
	{
		return (JDFPageData) appendElement(ElementName.PAGEDATA, null);
	}

	/**
	 * (24) const get element ScreeningParams
	 *
	 * @return JDFScreeningParams the element
	 */
	public JDFScreeningParams getScreeningParams()
	{
		return (JDFScreeningParams) getElement(ElementName.SCREENINGPARAMS, null, 0);
	}

	/**
	 * (25) getCreateScreeningParams
	 * 
	 * @return JDFScreeningParams the element
	 */
	public JDFScreeningParams getCreateScreeningParams()
	{
		return (JDFScreeningParams) getCreateElement_JDFElement(ElementName.SCREENINGPARAMS, null, 0);
	}

	/**
	 * (29) append element ScreeningParams
	 *
	 * @return JDFScreeningParams the element @ if the element already exists
	 */
	public JDFScreeningParams appendScreeningParams()
	{
		return (JDFScreeningParams) appendElementN(ElementName.SCREENINGPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refScreeningParams(JDFScreeningParams refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element SeparationSpec
	 *
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec getSeparationSpec()
	{
		return (JDFSeparationSpec) getElement(ElementName.SEPARATIONSPEC, null, 0);
	}

	/**
	 * (25) getCreateSeparationSpec
	 * 
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec getCreateSeparationSpec()
	{
		return (JDFSeparationSpec) getCreateElement_JDFElement(ElementName.SEPARATIONSPEC, null, 0);
	}

	/**
	 * (26) getCreateSeparationSpec
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec getCreateSeparationSpec(int iSkip)
	{
		return (JDFSeparationSpec) getCreateElement_JDFElement(ElementName.SEPARATIONSPEC, null, iSkip);
	}

	/**
	 * (27) const get element SeparationSpec
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFSeparationSpec the element default is getSeparationSpec(0)
	 */
	public JDFSeparationSpec getSeparationSpec(int iSkip)
	{
		return (JDFSeparationSpec) getElement(ElementName.SEPARATIONSPEC, null, iSkip);
	}

	/**
	 * Get all SeparationSpec from the current element
	 * 
	 * @return Collection<JDFSeparationSpec>, null if none are available
	 */
	public Collection<JDFSeparationSpec> getAllSeparationSpec()
	{
		return getChildArrayByClass(JDFSeparationSpec.class, false, 0);
	}

	/**
	 * (30) append element SeparationSpec
	 *
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec appendSeparationSpec()
	{
		return (JDFSeparationSpec) appendElement(ElementName.SEPARATIONSPEC, null);
	}

	/**
	 * (24) const get element ElementColorParams
	 *
	 * @return JDFElementColorParams the element
	 */
	public JDFElementColorParams getElementColorParams()
	{
		return (JDFElementColorParams) getElement(ElementName.ELEMENTCOLORPARAMS, null, 0);
	}

	/**
	 * (25) getCreateElementColorParams
	 * 
	 * @return JDFElementColorParams the element
	 */
	public JDFElementColorParams getCreateElementColorParams()
	{
		return (JDFElementColorParams) getCreateElement_JDFElement(ElementName.ELEMENTCOLORPARAMS, null, 0);
	}

	/**
	 * (29) append element ElementColorParams
	 *
	 * @return JDFElementColorParams the element @ if the element already exists
	 */
	public JDFElementColorParams appendElementColorParams()
	{
		return (JDFElementColorParams) appendElementN(ElementName.ELEMENTCOLORPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refElementColorParams(JDFElementColorParams refTarget)
	{
		refElement(refTarget);
	}

}
