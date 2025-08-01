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
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.resource.process.JDFApprovalParams;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;
import org.cip4.jdflib.span.JDFIntegerSpan;
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFOptionSpan;
import org.cip4.jdflib.span.JDFSpanColorType;
import org.cip4.jdflib.span.JDFSpanImageStrategy;
import org.cip4.jdflib.span.JDFSpanProofType;
import org.cip4.jdflib.span.JDFStringSpan;

/**
 *****************************************************************************
 * class JDFAutoProofItem : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoProofItem extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CONTRACT, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.PAGEINDEX, 0x3333333331l, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.PROOFNAME, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.PROOFTARGET, 0x3333333331l, AttributeInfo.EnumAttributeType.URL, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[10];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.AMOUNT, 0x6666666661l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.BRANDNAME, 0x6666666661l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.COLORTYPE, 0x6666666661l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.HALFTONE, 0x6666666661l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.IMAGESTRATEGY, 0x6666666611l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.TECHNOLOGY, 0x6666666661l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.PROOFTYPE, 0x6666666661l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.APPROVALPARAMS, 0x6666666611l);
		elemInfoTable[8] = new ElemInfoTable(ElementName.FILESPEC, 0x6661111111l);
		elemInfoTable[9] = new ElemInfoTable(ElementName.SEPARATIONSPEC, 0x3333333331l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoProofItem
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoProofItem(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoProofItem
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoProofItem(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoProofItem
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoProofItem(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Contract ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Contract
	 *
	 * @param value the value to set the attribute to
	 */
	public void setContract(boolean value)
	{
		setAttribute(AttributeName.CONTRACT, value, null);
	}

	/**
	 * (18) get boolean attribute Contract
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getContract()
	{
		return getBoolAttribute(AttributeName.CONTRACT, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PageIndex ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PageIndex
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPageIndex(JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.PAGEINDEX, value, null);
	}

	/**
	 * (20) get JDFIntegerRangeList attribute PageIndex
	 *
	 * @return JDFIntegerRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerRangeList
	 */
	public JDFIntegerRangeList getPageIndex()
	{
		final String strAttrName = getAttribute(AttributeName.PAGEINDEX, null, null);
		final JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProofName ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ProofName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setProofName(String value)
	{
		setAttribute(AttributeName.PROOFNAME, value, null);
	}

	/**
	 * (23) get String attribute ProofName
	 *
	 * @return the value of the attribute
	 */
	public String getProofName()
	{
		return getAttribute(AttributeName.PROOFNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProofTarget ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ProofTarget
	 *
	 * @param value the value to set the attribute to
	 */
	public void setProofTarget(String value)
	{
		setAttribute(AttributeName.PROOFTARGET, value, null);
	}

	/**
	 * (23) get String attribute ProofTarget
	 *
	 * @return the value of the attribute
	 */
	public String getProofTarget()
	{
		return getAttribute(AttributeName.PROOFTARGET, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Amount
	 *
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getAmount()
	{
		return (JDFIntegerSpan) getElement(ElementName.AMOUNT, null, 0);
	}

	/**
	 * (25) getCreateAmount
	 * 
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getCreateAmount()
	{
		return (JDFIntegerSpan) getCreateElement_JDFElement(ElementName.AMOUNT, null, 0);
	}

	/**
	 * (29) append element Amount
	 *
	 * @return JDFIntegerSpan the element @ if the element already exists
	 */
	public JDFIntegerSpan appendAmount()
	{
		return (JDFIntegerSpan) appendElementN(ElementName.AMOUNT, 1, null);
	}

	/**
	 * (24) const get element BrandName
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getBrandName()
	{
		return (JDFStringSpan) getElement(ElementName.BRANDNAME, null, 0);
	}

	/**
	 * (25) getCreateBrandName
	 * 
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateBrandName()
	{
		return (JDFStringSpan) getCreateElement_JDFElement(ElementName.BRANDNAME, null, 0);
	}

	/**
	 * (29) append element BrandName
	 *
	 * @return JDFStringSpan the element @ if the element already exists
	 */
	public JDFStringSpan appendBrandName()
	{
		return (JDFStringSpan) appendElementN(ElementName.BRANDNAME, 1, null);
	}

	/**
	 * (24) const get element ColorType
	 *
	 * @return JDFSpanColorType the element
	 */
	public JDFSpanColorType getColorType()
	{
		return (JDFSpanColorType) getElement(ElementName.COLORTYPE, null, 0);
	}

	/**
	 * (25) getCreateColorType
	 * 
	 * @return JDFSpanColorType the element
	 */
	public JDFSpanColorType getCreateColorType()
	{
		return (JDFSpanColorType) getCreateElement_JDFElement(ElementName.COLORTYPE, null, 0);
	}

	/**
	 * (29) append element ColorType
	 *
	 * @return JDFSpanColorType the element @ if the element already exists
	 */
	public JDFSpanColorType appendColorType()
	{
		return (JDFSpanColorType) appendElementN(ElementName.COLORTYPE, 1, null);
	}

	/**
	 * (24) const get element HalfTone
	 *
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getHalfTone()
	{
		return (JDFOptionSpan) getElement(ElementName.HALFTONE, null, 0);
	}

	/**
	 * (25) getCreateHalfTone
	 * 
	 * @return JDFOptionSpan the element
	 */
	public JDFOptionSpan getCreateHalfTone()
	{
		return (JDFOptionSpan) getCreateElement_JDFElement(ElementName.HALFTONE, null, 0);
	}

	/**
	 * (29) append element HalfTone
	 *
	 * @return JDFOptionSpan the element @ if the element already exists
	 */
	public JDFOptionSpan appendHalfTone()
	{
		return (JDFOptionSpan) appendElementN(ElementName.HALFTONE, 1, null);
	}

	/**
	 * (24) const get element ImageStrategy
	 *
	 * @return JDFSpanImageStrategy the element
	 */
	public JDFSpanImageStrategy getImageStrategy()
	{
		return (JDFSpanImageStrategy) getElement(ElementName.IMAGESTRATEGY, null, 0);
	}

	/**
	 * (25) getCreateImageStrategy
	 * 
	 * @return JDFSpanImageStrategy the element
	 */
	public JDFSpanImageStrategy getCreateImageStrategy()
	{
		return (JDFSpanImageStrategy) getCreateElement_JDFElement(ElementName.IMAGESTRATEGY, null, 0);
	}

	/**
	 * (29) append element ImageStrategy
	 *
	 * @return JDFSpanImageStrategy the element @ if the element already exists
	 */
	public JDFSpanImageStrategy appendImageStrategy()
	{
		return (JDFSpanImageStrategy) appendElementN(ElementName.IMAGESTRATEGY, 1, null);
	}

	/**
	 * (24) const get element Technology
	 *
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getTechnology()
	{
		return (JDFNameSpan) getElement(ElementName.TECHNOLOGY, null, 0);
	}

	/**
	 * (25) getCreateTechnology
	 * 
	 * @return JDFNameSpan the element
	 */
	public JDFNameSpan getCreateTechnology()
	{
		return (JDFNameSpan) getCreateElement_JDFElement(ElementName.TECHNOLOGY, null, 0);
	}

	/**
	 * (29) append element Technology
	 *
	 * @return JDFNameSpan the element @ if the element already exists
	 */
	public JDFNameSpan appendTechnology()
	{
		return (JDFNameSpan) appendElementN(ElementName.TECHNOLOGY, 1, null);
	}

	/**
	 * (24) const get element ProofType
	 *
	 * @return JDFSpanProofType the element
	 */
	public JDFSpanProofType getProofType()
	{
		return (JDFSpanProofType) getElement(ElementName.PROOFTYPE, null, 0);
	}

	/**
	 * (25) getCreateProofType
	 * 
	 * @return JDFSpanProofType the element
	 */
	public JDFSpanProofType getCreateProofType()
	{
		return (JDFSpanProofType) getCreateElement_JDFElement(ElementName.PROOFTYPE, null, 0);
	}

	/**
	 * (29) append element ProofType
	 *
	 * @return JDFSpanProofType the element @ if the element already exists
	 */
	public JDFSpanProofType appendProofType()
	{
		return (JDFSpanProofType) appendElementN(ElementName.PROOFTYPE, 1, null);
	}

	/**
	 * (24) const get element ApprovalParams
	 *
	 * @return JDFApprovalParams the element
	 */
	public JDFApprovalParams getApprovalParams()
	{
		return (JDFApprovalParams) getElement(ElementName.APPROVALPARAMS, null, 0);
	}

	/**
	 * (25) getCreateApprovalParams
	 * 
	 * @return JDFApprovalParams the element
	 */
	public JDFApprovalParams getCreateApprovalParams()
	{
		return (JDFApprovalParams) getCreateElement_JDFElement(ElementName.APPROVALPARAMS, null, 0);
	}

	/**
	 * (29) append element ApprovalParams
	 *
	 * @return JDFApprovalParams the element @ if the element already exists
	 */
	public JDFApprovalParams appendApprovalParams()
	{
		return (JDFApprovalParams) appendElementN(ElementName.APPROVALPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refApprovalParams(JDFApprovalParams refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element FileSpec
	 *
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getFileSpec()
	{
		return (JDFFileSpec) getElement(ElementName.FILESPEC, null, 0);
	}

	/**
	 * (25) getCreateFileSpec
	 * 
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getCreateFileSpec()
	{
		return (JDFFileSpec) getCreateElement_JDFElement(ElementName.FILESPEC, null, 0);
	}

	/**
	 * (29) append element FileSpec
	 *
	 * @return JDFFileSpec the element @ if the element already exists
	 */
	public JDFFileSpec appendFileSpec()
	{
		return (JDFFileSpec) appendElementN(ElementName.FILESPEC, 1, null);
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
