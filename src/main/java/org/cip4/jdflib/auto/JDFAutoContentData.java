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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.process.JDFContentMetadata;
import org.cip4.jdflib.resource.process.JDFElementColorParams;
import org.cip4.jdflib.resource.process.JDFImageCompressionParams;
import org.cip4.jdflib.resource.process.JDFOCGControl;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;
import org.cip4.jdflib.resource.process.prepress.JDFScreeningParams;

/**
 *****************************************************************************
 * class JDFAutoContentData : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoContentData extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[10];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CATALOGID, 0x4444333111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CATALOGDETAILS, 0x4444333111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.CONTENTREFS, 0x3333331111l, AttributeInfo.EnumAttributeType.IDREFS, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.CONTENTTYPE, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.HASBLEEDS, 0x3333333111l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.ID, 0x3333331111l, AttributeInfo.EnumAttributeType.ID, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.ISBLANK, 0x3333333111l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.ISTRAPPED, 0x3333333111l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.JOBID, 0x3333333111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.PRODUCTID, 0x3333333111l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[6];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.CONTENTMETADATA, 0x6666666111l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.ELEMENTCOLORPARAMS, 0x6666666111l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.IMAGECOMPRESSIONPARAMS, 0x6666666111l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.OCGCONTROL, 0x3333333111l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.SCREENINGPARAMS, 0x6666666111l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.SEPARATIONSPEC, 0x3333333111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoContentData
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoContentData(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoContentData
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoContentData(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoContentData
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoContentData(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CatalogID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CatalogID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCatalogID(String value)
	{
		setAttribute(AttributeName.CATALOGID, value, null);
	}

	/**
	 * (23) get String attribute CatalogID
	 *
	 * @return the value of the attribute
	 */
	public String getCatalogID()
	{
		return getAttribute(AttributeName.CATALOGID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CatalogDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CatalogDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCatalogDetails(String value)
	{
		setAttribute(AttributeName.CATALOGDETAILS, value, null);
	}

	/**
	 * (23) get String attribute CatalogDetails
	 *
	 * @return the value of the attribute
	 */
	public String getCatalogDetails()
	{
		return getAttribute(AttributeName.CATALOGDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ContentRefs ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ContentRefs
	 *
	 * @param value the value to set the attribute to
	 */
	public void setContentRefs(VString value)
	{
		setAttribute(AttributeName.CONTENTREFS, value, null);
	}

	/**
	 * (21) get VString attribute ContentRefs
	 *
	 * @return VString the value of the attribute
	 */
	public VString getContentRefs()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.CONTENTREFS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ContentType ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ContentType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setContentType(String value)
	{
		setAttribute(AttributeName.CONTENTTYPE, value, null);
	}

	/**
	 * (23) get String attribute ContentType
	 *
	 * @return the value of the attribute
	 */
	public String getContentType()
	{
		return getAttribute(AttributeName.CONTENTTYPE, null, JDFCoreConstants.EMPTYSTRING);
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
	 * --------------------------------------------------------------------- Methods for Attribute ID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ID
	 *
	 * @param value the value to set the attribute to
	 */
	@Override
	public void setID(String value)
	{
		setAttribute(AttributeName.ID, value, null);
	}

	/**
	 * (23) get String attribute ID
	 *
	 * @return the value of the attribute
	 */
	@Override
	public String getID()
	{
		return getAttribute(AttributeName.ID, null, JDFCoreConstants.EMPTYSTRING);
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
	 * --------------------------------------------------------------------- Methods for Attribute ProductID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ProductID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setProductID(String value)
	{
		setAttribute(AttributeName.PRODUCTID, value, null);
	}

	/**
	 * (23) get String attribute ProductID
	 *
	 * @return the value of the attribute
	 */
	public String getProductID()
	{
		return getAttribute(AttributeName.PRODUCTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element ContentMetadata
	 *
	 * @return JDFContentMetadata the element
	 */
	public JDFContentMetadata getContentMetadata()
	{
		return (JDFContentMetadata) getElement(ElementName.CONTENTMETADATA, null, 0);
	}

	/**
	 * (25) getCreateContentMetadata
	 * 
	 * @return JDFContentMetadata the element
	 */
	public JDFContentMetadata getCreateContentMetadata()
	{
		return (JDFContentMetadata) getCreateElement_JDFElement(ElementName.CONTENTMETADATA, null, 0);
	}

	/**
	 * (29) append element ContentMetadata
	 *
	 * @return JDFContentMetadata the element @ if the element already exists
	 */
	public JDFContentMetadata appendContentMetadata()
	{
		return (JDFContentMetadata) appendElementN(ElementName.CONTENTMETADATA, 1, null);
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
	 * (24) const get element OCGControl
	 *
	 * @return JDFOCGControl the element
	 */
	public JDFOCGControl getOCGControl()
	{
		return (JDFOCGControl) getElement(ElementName.OCGCONTROL, null, 0);
	}

	/**
	 * (25) getCreateOCGControl
	 * 
	 * @return JDFOCGControl the element
	 */
	public JDFOCGControl getCreateOCGControl()
	{
		return (JDFOCGControl) getCreateElement_JDFElement(ElementName.OCGCONTROL, null, 0);
	}

	/**
	 * (26) getCreateOCGControl
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFOCGControl the element
	 */
	public JDFOCGControl getCreateOCGControl(int iSkip)
	{
		return (JDFOCGControl) getCreateElement_JDFElement(ElementName.OCGCONTROL, null, iSkip);
	}

	/**
	 * (27) const get element OCGControl
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFOCGControl the element default is getOCGControl(0)
	 */
	public JDFOCGControl getOCGControl(int iSkip)
	{
		return (JDFOCGControl) getElement(ElementName.OCGCONTROL, null, iSkip);
	}

	/**
	 * Get all OCGControl from the current element
	 * 
	 * @return Collection<JDFOCGControl>, null if none are available
	 */
	public Collection<JDFOCGControl> getAllOCGControl()
	{
		return getChildArrayByClass(JDFOCGControl.class, false, 0);
	}

	/**
	 * (30) append element OCGControl
	 *
	 * @return JDFOCGControl the element
	 */
	public JDFOCGControl appendOCGControl()
	{
		return (JDFOCGControl) appendElement(ElementName.OCGCONTROL, null);
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

}
