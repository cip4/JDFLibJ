/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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
import org.cip4.jdflib.resource.process.JDFBarcodeProductionParams;
import org.cip4.jdflib.resource.process.JDFImageCompressionParams;
import org.cip4.jdflib.resource.process.JDFImageEnhancementParams;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFPositionObj;
import org.cip4.jdflib.resource.process.prepress.JDFColorCorrectionParams;

/**
 *****************************************************************************
 * class JDFAutoLayoutElementPart : public JDFElement
 *****************************************************************************
 *
 */

public abstract class JDFAutoLayoutElementPart extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ID, 0x33331111, AttributeInfo.EnumAttributeType.ID, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[6];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BARCODEPRODUCTIONPARAMS, 0x66666111);
		elemInfoTable[1] = new ElemInfoTable(ElementName.COLORCORRECTIONPARAMS, 0x33333111);
		elemInfoTable[2] = new ElemInfoTable(ElementName.IMAGECOMPRESSIONPARAMS, 0x33333111);
		elemInfoTable[3] = new ElemInfoTable(ElementName.IMAGEENHANCEMENTPARAMS, 0x33333111);
		elemInfoTable[4] = new ElemInfoTable(ElementName.LAYOUTELEMENT, 0x66666111);
		elemInfoTable[5] = new ElemInfoTable(ElementName.POSITIONOBJ, 0x66666111);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoLayoutElementPart
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoLayoutElementPart(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLayoutElementPart
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoLayoutElementPart(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLayoutElementPart
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoLayoutElementPart(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute ID
	--------------------------------------------------------------------- */
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

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element BarcodeProductionParams
	 *
	 * @return JDFBarcodeProductionParams the element
	 */
	public JDFBarcodeProductionParams getBarcodeProductionParams()
	{
		return (JDFBarcodeProductionParams) getElement(ElementName.BARCODEPRODUCTIONPARAMS, null, 0);
	}

	/**
	 * (25) getCreateBarcodeProductionParams
	 *
	 * @return JDFBarcodeProductionParams the element
	 */
	public JDFBarcodeProductionParams getCreateBarcodeProductionParams()
	{
		return (JDFBarcodeProductionParams) getCreateElement_JDFElement(ElementName.BARCODEPRODUCTIONPARAMS, null, 0);
	}

	/**
	 * (29) append element BarcodeProductionParams
	 *
	 * @return JDFBarcodeProductionParams the element @ if the element already exists
	 */
	public JDFBarcodeProductionParams appendBarcodeProductionParams()
	{
		return (JDFBarcodeProductionParams) appendElementN(ElementName.BARCODEPRODUCTIONPARAMS, 1, null);
	}

	/**
	 * (26) getCreateColorCorrectionParams
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFColorCorrectionParams the element
	 */
	public JDFColorCorrectionParams getCreateColorCorrectionParams(int iSkip)
	{
		return (JDFColorCorrectionParams) getCreateElement_JDFElement(ElementName.COLORCORRECTIONPARAMS, null, iSkip);
	}

	/**
	 * (27) const get element ColorCorrectionParams
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFColorCorrectionParams the element default is getColorCorrectionParams(0)
	 */
	public JDFColorCorrectionParams getColorCorrectionParams(int iSkip)
	{
		return (JDFColorCorrectionParams) getElement(ElementName.COLORCORRECTIONPARAMS, null, iSkip);
	}

	/**
	 * Get all ColorCorrectionParams from the current element
	 *
	 * @return Collection<JDFColorCorrectionParams>, null if none are available
	 */
	public Collection<JDFColorCorrectionParams> getAllColorCorrectionParams()
	{
		return getChildArrayByClass(JDFColorCorrectionParams.class, false, 0);
	}

	/**
	 * (30) append element ColorCorrectionParams
	 *
	 * @return JDFColorCorrectionParams the element
	 */
	public JDFColorCorrectionParams appendColorCorrectionParams()
	{
		return (JDFColorCorrectionParams) appendElement(ElementName.COLORCORRECTIONPARAMS, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refColorCorrectionParams(JDFColorCorrectionParams refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (26) getCreateImageCompressionParams
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFImageCompressionParams the element
	 */
	public JDFImageCompressionParams getCreateImageCompressionParams(int iSkip)
	{
		return (JDFImageCompressionParams) getCreateElement_JDFElement(ElementName.IMAGECOMPRESSIONPARAMS, null, iSkip);
	}

	/**
	 * (27) const get element ImageCompressionParams
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFImageCompressionParams the element default is getImageCompressionParams(0)
	 */
	public JDFImageCompressionParams getImageCompressionParams(int iSkip)
	{
		return (JDFImageCompressionParams) getElement(ElementName.IMAGECOMPRESSIONPARAMS, null, iSkip);
	}

	/**
	 * Get all ImageCompressionParams from the current element
	 *
	 * @return Collection<JDFImageCompressionParams>, null if none are available
	 */
	public Collection<JDFImageCompressionParams> getAllImageCompressionParams()
	{
		return getChildArrayByClass(JDFImageCompressionParams.class, false, 0);
	}

	/**
	 * (30) append element ImageCompressionParams
	 *
	 * @return JDFImageCompressionParams the element
	 */
	public JDFImageCompressionParams appendImageCompressionParams()
	{
		return (JDFImageCompressionParams) appendElement(ElementName.IMAGECOMPRESSIONPARAMS, null);
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
	 * (26) getCreateImageEnhancementParams
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFImageEnhancementParams the element
	 */
	public JDFImageEnhancementParams getCreateImageEnhancementParams(int iSkip)
	{
		return (JDFImageEnhancementParams) getCreateElement_JDFElement(ElementName.IMAGEENHANCEMENTPARAMS, null, iSkip);
	}

	/**
	 * (27) const get element ImageEnhancementParams
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFImageEnhancementParams the element default is getImageEnhancementParams(0)
	 */
	public JDFImageEnhancementParams getImageEnhancementParams(int iSkip)
	{
		return (JDFImageEnhancementParams) getElement(ElementName.IMAGEENHANCEMENTPARAMS, null, iSkip);
	}

	/**
	 * Get all ImageEnhancementParams from the current element
	 *
	 * @return Collection<JDFImageEnhancementParams>, null if none are available
	 */
	public Collection<JDFImageEnhancementParams> getAllImageEnhancementParams()
	{
		return getChildArrayByClass(JDFImageEnhancementParams.class, false, 0);
	}

	/**
	 * (30) append element ImageEnhancementParams
	 *
	 * @return JDFImageEnhancementParams the element
	 */
	public JDFImageEnhancementParams appendImageEnhancementParams()
	{
		return (JDFImageEnhancementParams) appendElement(ElementName.IMAGEENHANCEMENTPARAMS, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refImageEnhancementParams(JDFImageEnhancementParams refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element LayoutElement
	 *
	 * @return JDFLayoutElement the element
	 */
	public JDFLayoutElement getLayoutElement()
	{
		return (JDFLayoutElement) getElement(ElementName.LAYOUTELEMENT, null, 0);
	}

	/**
	 * (25) getCreateLayoutElement
	 *
	 * @return JDFLayoutElement the element
	 */
	public JDFLayoutElement getCreateLayoutElement()
	{
		return (JDFLayoutElement) getCreateElement_JDFElement(ElementName.LAYOUTELEMENT, null, 0);
	}

	/**
	 * (29) append element LayoutElement
	 *
	 * @return JDFLayoutElement the element @ if the element already exists
	 */
	public JDFLayoutElement appendLayoutElement()
	{
		return (JDFLayoutElement) appendElementN(ElementName.LAYOUTELEMENT, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refLayoutElement(JDFLayoutElement refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element PositionObj
	 *
	 * @return JDFPositionObj the element
	 */
	public JDFPositionObj getPositionObj()
	{
		return (JDFPositionObj) getElement(ElementName.POSITIONOBJ, null, 0);
	}

	/**
	 * (25) getCreatePositionObj
	 *
	 * @return JDFPositionObj the element
	 */
	public JDFPositionObj getCreatePositionObj()
	{
		return (JDFPositionObj) getCreateElement_JDFElement(ElementName.POSITIONOBJ, null, 0);
	}

	/**
	 * (29) append element PositionObj
	 *
	 * @return JDFPositionObj the element @ if the element already exists
	 */
	public JDFPositionObj appendPositionObj()
	{
		return (JDFPositionObj) appendElementN(ElementName.POSITIONOBJ, 1, null);
	}

}
