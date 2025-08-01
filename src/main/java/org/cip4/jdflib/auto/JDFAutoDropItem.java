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
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.resource.JDFBundle;
import org.cip4.jdflib.resource.JDFPallet;
import org.cip4.jdflib.resource.JDFRegisterRibbon;
import org.cip4.jdflib.resource.JDFStrap;
import org.cip4.jdflib.resource.JDFTool;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFDigitalMedia;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRollStand;
import org.cip4.jdflib.resource.process.prepress.JDFInk;

/**
 *****************************************************************************
 * class JDFAutoDropItem : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoDropItem extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTUALAMOUNT, 0x3333333111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ACTUALTOTALAMOUNT, 0x3333333111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.AMOUNT, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.TOTALAMOUNT, 0x3333333111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.TOTALDIMENSIONS, 0x3333333111l, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.TOTALVOLUME, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.TOTALWEIGHT, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.TRACKINGID, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.UNIT, 0x4444333333l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[11];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COMPONENT, 0x2222222222l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.EXPOSEDMEDIA, 0x2222222222l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.INK, 0x2222222222l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.MEDIA, 0x2222222222l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.PALLET, 0x2222222222l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.REGISTERRIBBON, 0x2222222222l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.STRAP, 0x2222222222l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.BUNDLE, 0x2222222222l);
		elemInfoTable[8] = new ElemInfoTable(ElementName.DIGITALMEDIA, 0x2222222222l);
		elemInfoTable[9] = new ElemInfoTable(ElementName.ROLLSTAND, 0x2222222222l);
		elemInfoTable[10] = new ElemInfoTable(ElementName.TOOL, 0x2222222222l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoDropItem
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDropItem(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDropItem
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDropItem(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDropItem
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDropItem(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ActualAmount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ActualAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setActualAmount(int value)
	{
		setAttribute(AttributeName.ACTUALAMOUNT, value, null);
	}

	/**
	 * (15) get int attribute ActualAmount
	 *
	 * @return int the value of the attribute
	 */
	public int getActualAmount()
	{
		return getIntAttribute(AttributeName.ACTUALAMOUNT, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ActualTotalAmount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ActualTotalAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setActualTotalAmount(int value)
	{
		setAttribute(AttributeName.ACTUALTOTALAMOUNT, value, null);
	}

	/**
	 * (15) get int attribute ActualTotalAmount
	 *
	 * @return int the value of the attribute
	 */
	public int getActualTotalAmount()
	{
		return getIntAttribute(AttributeName.ACTUALTOTALAMOUNT, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Amount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Amount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAmount(int value)
	{
		setAttribute(AttributeName.AMOUNT, value, null);
	}

	/**
	 * (15) get int attribute Amount
	 *
	 * @return int the value of the attribute
	 */
	public int getAmount()
	{
		return getIntAttribute(AttributeName.AMOUNT, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TotalAmount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TotalAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTotalAmount(int value)
	{
		setAttribute(AttributeName.TOTALAMOUNT, value, null);
	}

	/**
	 * (15) get int attribute TotalAmount
	 *
	 * @return int the value of the attribute
	 */
	public int getTotalAmount()
	{
		return getIntAttribute(AttributeName.TOTALAMOUNT, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TotalDimensions
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TotalDimensions
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTotalDimensions(JDFShape value)
	{
		setAttribute(AttributeName.TOTALDIMENSIONS, value, null);
	}

	/**
	 * (20) get JDFShape attribute TotalDimensions
	 *
	 * @return JDFShape the value of the attribute, null if a the attribute value is not a valid to create a JDFShape
	 */
	public JDFShape getTotalDimensions()
	{
		final String strAttrName = getAttribute(AttributeName.TOTALDIMENSIONS, null, null);
		final JDFShape nPlaceHolder = JDFShape.createShape(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TotalVolume ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TotalVolume
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTotalVolume(double value)
	{
		setAttribute(AttributeName.TOTALVOLUME, value, null);
	}

	/**
	 * (17) get double attribute TotalVolume
	 *
	 * @return double the value of the attribute
	 */
	public double getTotalVolume()
	{
		return getRealAttribute(AttributeName.TOTALVOLUME, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TotalWeight ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TotalWeight
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTotalWeight(double value)
	{
		setAttribute(AttributeName.TOTALWEIGHT, value, null);
	}

	/**
	 * (17) get double attribute TotalWeight
	 *
	 * @return double the value of the attribute
	 */
	public double getTotalWeight()
	{
		return getRealAttribute(AttributeName.TOTALWEIGHT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrackingID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TrackingID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTrackingID(String value)
	{
		setAttribute(AttributeName.TRACKINGID, value, null);
	}

	/**
	 * (23) get String attribute TrackingID
	 *
	 * @return the value of the attribute
	 */
	public String getTrackingID()
	{
		return getAttribute(AttributeName.TRACKINGID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Unit ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Unit
	 *
	 * @param value the value to set the attribute to
	 */
	public void setUnit(String value)
	{
		setAttribute(AttributeName.UNIT, value, null);
	}

	/**
	 * (23) get String attribute Unit
	 *
	 * @return the value of the attribute
	 */
	public String getUnit()
	{
		return getAttribute(AttributeName.UNIT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Component
	 *
	 * @return JDFComponent the element
	 */
	public JDFComponent getComponent()
	{
		return (JDFComponent) getElement(ElementName.COMPONENT, null, 0);
	}

	/**
	 * (25) getCreateComponent
	 * 
	 * @return JDFComponent the element
	 */
	public JDFComponent getCreateComponent()
	{
		return (JDFComponent) getCreateElement_JDFElement(ElementName.COMPONENT, null, 0);
	}

	/**
	 * (26) getCreateComponent
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFComponent the element
	 */
	public JDFComponent getCreateComponent(int iSkip)
	{
		return (JDFComponent) getCreateElement_JDFElement(ElementName.COMPONENT, null, iSkip);
	}

	/**
	 * (27) const get element Component
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFComponent the element default is getComponent(0)
	 */
	public JDFComponent getComponent(int iSkip)
	{
		return (JDFComponent) getElement(ElementName.COMPONENT, null, iSkip);
	}

	/**
	 * Get all Component from the current element
	 * 
	 * @return Collection<JDFComponent>, null if none are available
	 */
	public Collection<JDFComponent> getAllComponent()
	{
		return getChildArrayByClass(JDFComponent.class, false, 0);
	}

	/**
	 * (30) append element Component
	 *
	 * @return JDFComponent the element
	 */
	public JDFComponent appendComponent()
	{
		return (JDFComponent) appendElement(ElementName.COMPONENT, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refComponent(JDFComponent refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ExposedMedia
	 *
	 * @return JDFExposedMedia the element
	 */
	public JDFExposedMedia getExposedMedia()
	{
		return (JDFExposedMedia) getElement(ElementName.EXPOSEDMEDIA, null, 0);
	}

	/**
	 * (25) getCreateExposedMedia
	 * 
	 * @return JDFExposedMedia the element
	 */
	public JDFExposedMedia getCreateExposedMedia()
	{
		return (JDFExposedMedia) getCreateElement_JDFElement(ElementName.EXPOSEDMEDIA, null, 0);
	}

	/**
	 * (26) getCreateExposedMedia
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFExposedMedia the element
	 */
	public JDFExposedMedia getCreateExposedMedia(int iSkip)
	{
		return (JDFExposedMedia) getCreateElement_JDFElement(ElementName.EXPOSEDMEDIA, null, iSkip);
	}

	/**
	 * (27) const get element ExposedMedia
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFExposedMedia the element default is getExposedMedia(0)
	 */
	public JDFExposedMedia getExposedMedia(int iSkip)
	{
		return (JDFExposedMedia) getElement(ElementName.EXPOSEDMEDIA, null, iSkip);
	}

	/**
	 * Get all ExposedMedia from the current element
	 * 
	 * @return Collection<JDFExposedMedia>, null if none are available
	 */
	public Collection<JDFExposedMedia> getAllExposedMedia()
	{
		return getChildArrayByClass(JDFExposedMedia.class, false, 0);
	}

	/**
	 * (30) append element ExposedMedia
	 *
	 * @return JDFExposedMedia the element
	 */
	public JDFExposedMedia appendExposedMedia()
	{
		return (JDFExposedMedia) appendElement(ElementName.EXPOSEDMEDIA, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refExposedMedia(JDFExposedMedia refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Ink
	 *
	 * @return JDFInk the element
	 */
	public JDFInk getInk()
	{
		return (JDFInk) getElement(ElementName.INK, null, 0);
	}

	/**
	 * (25) getCreateInk
	 * 
	 * @return JDFInk the element
	 */
	public JDFInk getCreateInk()
	{
		return (JDFInk) getCreateElement_JDFElement(ElementName.INK, null, 0);
	}

	/**
	 * (26) getCreateInk
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFInk the element
	 */
	public JDFInk getCreateInk(int iSkip)
	{
		return (JDFInk) getCreateElement_JDFElement(ElementName.INK, null, iSkip);
	}

	/**
	 * (27) const get element Ink
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFInk the element default is getInk(0)
	 */
	public JDFInk getInk(int iSkip)
	{
		return (JDFInk) getElement(ElementName.INK, null, iSkip);
	}

	/**
	 * Get all Ink from the current element
	 * 
	 * @return Collection<JDFInk>, null if none are available
	 */
	public Collection<JDFInk> getAllInk()
	{
		return getChildArrayByClass(JDFInk.class, false, 0);
	}

	/**
	 * (30) append element Ink
	 *
	 * @return JDFInk the element
	 */
	public JDFInk appendInk()
	{
		return (JDFInk) appendElement(ElementName.INK, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refInk(JDFInk refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Media
	 *
	 * @return JDFMedia the element
	 */
	public JDFMedia getMedia()
	{
		return (JDFMedia) getElement(ElementName.MEDIA, null, 0);
	}

	/**
	 * (25) getCreateMedia
	 * 
	 * @return JDFMedia the element
	 */
	public JDFMedia getCreateMedia()
	{
		return (JDFMedia) getCreateElement_JDFElement(ElementName.MEDIA, null, 0);
	}

	/**
	 * (26) getCreateMedia
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFMedia the element
	 */
	public JDFMedia getCreateMedia(int iSkip)
	{
		return (JDFMedia) getCreateElement_JDFElement(ElementName.MEDIA, null, iSkip);
	}

	/**
	 * (27) const get element Media
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFMedia the element default is getMedia(0)
	 */
	public JDFMedia getMedia(int iSkip)
	{
		return (JDFMedia) getElement(ElementName.MEDIA, null, iSkip);
	}

	/**
	 * Get all Media from the current element
	 * 
	 * @return Collection<JDFMedia>, null if none are available
	 */
	public Collection<JDFMedia> getAllMedia()
	{
		return getChildArrayByClass(JDFMedia.class, false, 0);
	}

	/**
	 * (30) append element Media
	 *
	 * @return JDFMedia the element
	 */
	public JDFMedia appendMedia()
	{
		return (JDFMedia) appendElement(ElementName.MEDIA, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refMedia(JDFMedia refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Pallet
	 *
	 * @return JDFPallet the element
	 */
	public JDFPallet getPallet()
	{
		return (JDFPallet) getElement(ElementName.PALLET, null, 0);
	}

	/**
	 * (25) getCreatePallet
	 * 
	 * @return JDFPallet the element
	 */
	public JDFPallet getCreatePallet()
	{
		return (JDFPallet) getCreateElement_JDFElement(ElementName.PALLET, null, 0);
	}

	/**
	 * (26) getCreatePallet
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPallet the element
	 */
	public JDFPallet getCreatePallet(int iSkip)
	{
		return (JDFPallet) getCreateElement_JDFElement(ElementName.PALLET, null, iSkip);
	}

	/**
	 * (27) const get element Pallet
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPallet the element default is getPallet(0)
	 */
	public JDFPallet getPallet(int iSkip)
	{
		return (JDFPallet) getElement(ElementName.PALLET, null, iSkip);
	}

	/**
	 * Get all Pallet from the current element
	 * 
	 * @return Collection<JDFPallet>, null if none are available
	 */
	public Collection<JDFPallet> getAllPallet()
	{
		return getChildArrayByClass(JDFPallet.class, false, 0);
	}

	/**
	 * (30) append element Pallet
	 *
	 * @return JDFPallet the element
	 */
	public JDFPallet appendPallet()
	{
		return (JDFPallet) appendElement(ElementName.PALLET, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refPallet(JDFPallet refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element RegisterRibbon
	 *
	 * @return JDFRegisterRibbon the element
	 */
	public JDFRegisterRibbon getRegisterRibbon()
	{
		return (JDFRegisterRibbon) getElement(ElementName.REGISTERRIBBON, null, 0);
	}

	/**
	 * (25) getCreateRegisterRibbon
	 * 
	 * @return JDFRegisterRibbon the element
	 */
	public JDFRegisterRibbon getCreateRegisterRibbon()
	{
		return (JDFRegisterRibbon) getCreateElement_JDFElement(ElementName.REGISTERRIBBON, null, 0);
	}

	/**
	 * (26) getCreateRegisterRibbon
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFRegisterRibbon the element
	 */
	public JDFRegisterRibbon getCreateRegisterRibbon(int iSkip)
	{
		return (JDFRegisterRibbon) getCreateElement_JDFElement(ElementName.REGISTERRIBBON, null, iSkip);
	}

	/**
	 * (27) const get element RegisterRibbon
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFRegisterRibbon the element default is getRegisterRibbon(0)
	 */
	public JDFRegisterRibbon getRegisterRibbon(int iSkip)
	{
		return (JDFRegisterRibbon) getElement(ElementName.REGISTERRIBBON, null, iSkip);
	}

	/**
	 * Get all RegisterRibbon from the current element
	 * 
	 * @return Collection<JDFRegisterRibbon>, null if none are available
	 */
	public Collection<JDFRegisterRibbon> getAllRegisterRibbon()
	{
		return getChildArrayByClass(JDFRegisterRibbon.class, false, 0);
	}

	/**
	 * (30) append element RegisterRibbon
	 *
	 * @return JDFRegisterRibbon the element
	 */
	public JDFRegisterRibbon appendRegisterRibbon()
	{
		return (JDFRegisterRibbon) appendElement(ElementName.REGISTERRIBBON, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refRegisterRibbon(JDFRegisterRibbon refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Strap
	 *
	 * @return JDFStrap the element
	 */
	public JDFStrap getStrap()
	{
		return (JDFStrap) getElement(ElementName.STRAP, null, 0);
	}

	/**
	 * (25) getCreateStrap
	 * 
	 * @return JDFStrap the element
	 */
	public JDFStrap getCreateStrap()
	{
		return (JDFStrap) getCreateElement_JDFElement(ElementName.STRAP, null, 0);
	}

	/**
	 * (26) getCreateStrap
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFStrap the element
	 */
	public JDFStrap getCreateStrap(int iSkip)
	{
		return (JDFStrap) getCreateElement_JDFElement(ElementName.STRAP, null, iSkip);
	}

	/**
	 * (27) const get element Strap
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFStrap the element default is getStrap(0)
	 */
	public JDFStrap getStrap(int iSkip)
	{
		return (JDFStrap) getElement(ElementName.STRAP, null, iSkip);
	}

	/**
	 * Get all Strap from the current element
	 * 
	 * @return Collection<JDFStrap>, null if none are available
	 */
	public Collection<JDFStrap> getAllStrap()
	{
		return getChildArrayByClass(JDFStrap.class, false, 0);
	}

	/**
	 * (30) append element Strap
	 *
	 * @return JDFStrap the element
	 */
	public JDFStrap appendStrap()
	{
		return (JDFStrap) appendElement(ElementName.STRAP, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refStrap(JDFStrap refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Bundle
	 *
	 * @return JDFBundle the element
	 */
	public JDFBundle getBundle()
	{
		return (JDFBundle) getElement(ElementName.BUNDLE, null, 0);
	}

	/**
	 * (25) getCreateBundle
	 * 
	 * @return JDFBundle the element
	 */
	public JDFBundle getCreateBundle()
	{
		return (JDFBundle) getCreateElement_JDFElement(ElementName.BUNDLE, null, 0);
	}

	/**
	 * (26) getCreateBundle
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFBundle the element
	 */
	public JDFBundle getCreateBundle(int iSkip)
	{
		return (JDFBundle) getCreateElement_JDFElement(ElementName.BUNDLE, null, iSkip);
	}

	/**
	 * (27) const get element Bundle
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFBundle the element default is getBundle(0)
	 */
	public JDFBundle getBundle(int iSkip)
	{
		return (JDFBundle) getElement(ElementName.BUNDLE, null, iSkip);
	}

	/**
	 * Get all Bundle from the current element
	 * 
	 * @return Collection<JDFBundle>, null if none are available
	 */
	public Collection<JDFBundle> getAllBundle()
	{
		return getChildArrayByClass(JDFBundle.class, false, 0);
	}

	/**
	 * (30) append element Bundle
	 *
	 * @return JDFBundle the element
	 */
	public JDFBundle appendBundle()
	{
		return (JDFBundle) appendElement(ElementName.BUNDLE, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refBundle(JDFBundle refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element DigitalMedia
	 *
	 * @return JDFDigitalMedia the element
	 */
	public JDFDigitalMedia getDigitalMedia()
	{
		return (JDFDigitalMedia) getElement(ElementName.DIGITALMEDIA, null, 0);
	}

	/**
	 * (25) getCreateDigitalMedia
	 * 
	 * @return JDFDigitalMedia the element
	 */
	public JDFDigitalMedia getCreateDigitalMedia()
	{
		return (JDFDigitalMedia) getCreateElement_JDFElement(ElementName.DIGITALMEDIA, null, 0);
	}

	/**
	 * (26) getCreateDigitalMedia
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDigitalMedia the element
	 */
	public JDFDigitalMedia getCreateDigitalMedia(int iSkip)
	{
		return (JDFDigitalMedia) getCreateElement_JDFElement(ElementName.DIGITALMEDIA, null, iSkip);
	}

	/**
	 * (27) const get element DigitalMedia
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDigitalMedia the element default is getDigitalMedia(0)
	 */
	public JDFDigitalMedia getDigitalMedia(int iSkip)
	{
		return (JDFDigitalMedia) getElement(ElementName.DIGITALMEDIA, null, iSkip);
	}

	/**
	 * Get all DigitalMedia from the current element
	 * 
	 * @return Collection<JDFDigitalMedia>, null if none are available
	 */
	public Collection<JDFDigitalMedia> getAllDigitalMedia()
	{
		return getChildArrayByClass(JDFDigitalMedia.class, false, 0);
	}

	/**
	 * (30) append element DigitalMedia
	 *
	 * @return JDFDigitalMedia the element
	 */
	public JDFDigitalMedia appendDigitalMedia()
	{
		return (JDFDigitalMedia) appendElement(ElementName.DIGITALMEDIA, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refDigitalMedia(JDFDigitalMedia refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element RollStand
	 *
	 * @return JDFRollStand the element
	 */
	public JDFRollStand getRollStand()
	{
		return (JDFRollStand) getElement(ElementName.ROLLSTAND, null, 0);
	}

	/**
	 * (25) getCreateRollStand
	 * 
	 * @return JDFRollStand the element
	 */
	public JDFRollStand getCreateRollStand()
	{
		return (JDFRollStand) getCreateElement_JDFElement(ElementName.ROLLSTAND, null, 0);
	}

	/**
	 * (26) getCreateRollStand
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFRollStand the element
	 */
	public JDFRollStand getCreateRollStand(int iSkip)
	{
		return (JDFRollStand) getCreateElement_JDFElement(ElementName.ROLLSTAND, null, iSkip);
	}

	/**
	 * (27) const get element RollStand
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFRollStand the element default is getRollStand(0)
	 */
	public JDFRollStand getRollStand(int iSkip)
	{
		return (JDFRollStand) getElement(ElementName.ROLLSTAND, null, iSkip);
	}

	/**
	 * Get all RollStand from the current element
	 * 
	 * @return Collection<JDFRollStand>, null if none are available
	 */
	public Collection<JDFRollStand> getAllRollStand()
	{
		return getChildArrayByClass(JDFRollStand.class, false, 0);
	}

	/**
	 * (30) append element RollStand
	 *
	 * @return JDFRollStand the element
	 */
	public JDFRollStand appendRollStand()
	{
		return (JDFRollStand) appendElement(ElementName.ROLLSTAND, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refRollStand(JDFRollStand refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Tool
	 *
	 * @return JDFTool the element
	 */
	public JDFTool getTool()
	{
		return (JDFTool) getElement(ElementName.TOOL, null, 0);
	}

	/**
	 * (25) getCreateTool
	 * 
	 * @return JDFTool the element
	 */
	public JDFTool getCreateTool()
	{
		return (JDFTool) getCreateElement_JDFElement(ElementName.TOOL, null, 0);
	}

	/**
	 * (26) getCreateTool
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFTool the element
	 */
	public JDFTool getCreateTool(int iSkip)
	{
		return (JDFTool) getCreateElement_JDFElement(ElementName.TOOL, null, iSkip);
	}

	/**
	 * (27) const get element Tool
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFTool the element default is getTool(0)
	 */
	public JDFTool getTool(int iSkip)
	{
		return (JDFTool) getElement(ElementName.TOOL, null, iSkip);
	}

	/**
	 * Get all Tool from the current element
	 * 
	 * @return Collection<JDFTool>, null if none are available
	 */
	public Collection<JDFTool> getAllTool()
	{
		return getChildArrayByClass(JDFTool.class, false, 0);
	}

	/**
	 * (30) append element Tool
	 *
	 * @return JDFTool the element
	 */
	public JDFTool appendTool()
	{
		return (JDFTool) appendElement(ElementName.TOOL, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refTool(JDFTool refTarget)
	{
		refElement(refTarget);
	}

}
