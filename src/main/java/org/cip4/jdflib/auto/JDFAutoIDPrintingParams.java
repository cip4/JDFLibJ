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
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFJobSheet;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.intent.JDFMediaIntent;
import org.cip4.jdflib.resource.process.JDFCover;
import org.cip4.jdflib.resource.process.JDFIDPFinishing;
import org.cip4.jdflib.resource.process.JDFIDPLayout;
import org.cip4.jdflib.resource.process.JDFMediaSource;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoIDPrintingParams : public JDFResource
 */

public abstract class JDFAutoIDPrintingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ATTRIBUTESNATURALLANG, 0x4444444443l, AttributeInfo.EnumAttributeType.language, null, "US");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.IDPATTRIBUTEFIDELITY, 0x4444444443l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.IPPJOBPRIORITY, 0x4444444443l, AttributeInfo.EnumAttributeType.integer, null, "50");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.IPPVERSION, 0x4444444443l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.OUTPUTBIN, 0x4444444443l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.PAGEDELIVERY, 0x4444444443l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumPageDelivery.class, 0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.PRINTQUALITY, 0x4444444443l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumPrintQuality.class, 0), null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.SHEETCOLLATE, 0x4444444443l, AttributeInfo.EnumAttributeType.boolean_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[6];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COVER, 0x4444444443l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.IDPFINISHING, 0x7777777776l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.IDPLAYOUT, 0x7777777776l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.JOBSHEET, 0x4444444443l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.MEDIAINTENT, 0x7777777776l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.MEDIASOURCE, 0x7777777776l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoIDPrintingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoIDPrintingParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoIDPrintingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoIDPrintingParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoIDPrintingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoIDPrintingParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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

	/**
	 * Enumeration strings for numPageDelivery
	 */

	public enum EnumPageDelivery
	{
		SameOrderFaceUp, SameOrderFaceDown, ReverseOrderFaceUp, ReverseOrderFaceDown, SystemSpecified;

		public static EnumPageDelivery getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPageDelivery.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numPrintQuality
	 */

	public enum EnumPrintQuality
	{
		High, Normal, Draft;

		public static EnumPrintQuality getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPrintQuality.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AttributesNaturalLang
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AttributesNaturalLang
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAttributesNaturalLang(final String value)
	{
		setAttribute(AttributeName.ATTRIBUTESNATURALLANG, value, null);
	}

	/**
	 * (23) get String attribute AttributesNaturalLang
	 *
	 * @return the value of the attribute
	 */
	public String getAttributesNaturalLang()
	{
		return getAttribute(AttributeName.ATTRIBUTESNATURALLANG, null, "US");
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IDPAttributeFidelity
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IDPAttributeFidelity
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIDPAttributeFidelity(final boolean value)
	{
		setAttribute(AttributeName.IDPATTRIBUTEFIDELITY, value, null);
	}

	/**
	 * (18) get boolean attribute IDPAttributeFidelity
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIDPAttributeFidelity()
	{
		return getBoolAttribute(AttributeName.IDPATTRIBUTEFIDELITY, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IPPJobPriority
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IPPJobPriority
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIPPJobPriority(final int value)
	{
		setAttribute(AttributeName.IPPJOBPRIORITY, value, null);
	}

	/**
	 * (15) get int attribute IPPJobPriority
	 *
	 * @return int the value of the attribute
	 */
	public int getIPPJobPriority()
	{
		return getIntAttribute(AttributeName.IPPJOBPRIORITY, null, 50);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IPPVersion
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IPPVersion
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIPPVersion(final JDFXYPair value)
	{
		setAttribute(AttributeName.IPPVERSION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute IPPVersion
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getIPPVersion()
	{
		final String strAttrName = getAttribute(AttributeName.IPPVERSION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute OutputBin
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OutputBin
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOutputBin(final String value)
	{
		setAttribute(AttributeName.OUTPUTBIN, value, null);
	}

	/**
	 * (23) get String attribute OutputBin
	 *
	 * @return the value of the attribute
	 */
	public String getOutputBin()
	{
		return getAttribute(AttributeName.OUTPUTBIN, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PageDelivery
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PageDelivery
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPageDelivery(final EnumPageDelivery enumVar)
	{
		setAttribute(AttributeName.PAGEDELIVERY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute PageDelivery
	 *
	 * @return the value of the attribute
	 */
	public EnumPageDelivery getPageDelivery()
	{
		return EnumPageDelivery.getEnum(getAttribute(AttributeName.PAGEDELIVERY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PrintQuality
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PrintQuality
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPrintQuality(final EnumPrintQuality enumVar)
	{
		setAttribute(AttributeName.PRINTQUALITY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute PrintQuality
	 *
	 * @return the value of the attribute
	 */
	public EnumPrintQuality getPrintQuality()
	{
		return EnumPrintQuality.getEnum(getAttribute(AttributeName.PRINTQUALITY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SheetCollate
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SheetCollate
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSheetCollate(final boolean value)
	{
		setAttribute(AttributeName.SHEETCOLLATE, value, null);
	}

	/**
	 * (18) get boolean attribute SheetCollate
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getSheetCollate()
	{
		return getBoolAttribute(AttributeName.SHEETCOLLATE, null, false);
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element Cover
	 *
	 * @return JDFCover the element
	 */
	public JDFCover getCover()
	{
		return (JDFCover) getElement(ElementName.COVER, null, 0);
	}

	/**
	 * (25) getCreateCover
	 * 
	 * @return JDFCover the element
	 */
	public JDFCover getCreateCover()
	{
		return (JDFCover) getCreateElement_JDFElement(ElementName.COVER, null, 0);
	}

	/**
	 * (26) getCreateCover
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFCover the element
	 */
	public JDFCover getCreateCover(final int iSkip)
	{
		return (JDFCover) getCreateElement_JDFElement(ElementName.COVER, null, iSkip);
	}

	/**
	 * (27) const get element Cover
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCover the element
	 *         default is getCover(0)
	 */
	public JDFCover getCover(final int iSkip)
	{
		return (JDFCover) getElement(ElementName.COVER, null, iSkip);
	}

	/**
	 * Get all Cover from the current element
	 * 
	 * @return Collection<JDFCover>, null if none are available
	 */
	public Collection<JDFCover> getAllCover()
	{
		return getChildArrayByClass(JDFCover.class, false, 0);
	}

	/**
	 * (30) append element Cover
	 *
	 * @return JDFCover the element
	 */
	public JDFCover appendCover()
	{
		return (JDFCover) appendElement(ElementName.COVER, null);
	}

	/**
	 * (24) const get element IDPFinishing
	 *
	 * @return JDFIDPFinishing the element
	 */
	public JDFIDPFinishing getIDPFinishing()
	{
		return (JDFIDPFinishing) getElement(ElementName.IDPFINISHING, null, 0);
	}

	/**
	 * (25) getCreateIDPFinishing
	 * 
	 * @return JDFIDPFinishing the element
	 */
	public JDFIDPFinishing getCreateIDPFinishing()
	{
		return (JDFIDPFinishing) getCreateElement_JDFElement(ElementName.IDPFINISHING, null, 0);
	}

	/**
	 * (29) append element IDPFinishing
	 *
	 * @return JDFIDPFinishing the element
	 * @ if the element already exists
	 */
	public JDFIDPFinishing appendIDPFinishing()
	{
		return (JDFIDPFinishing) appendElementN(ElementName.IDPFINISHING, 1, null);
	}

	/**
	 * (24) const get element IDPLayout
	 *
	 * @return JDFIDPLayout the element
	 */
	public JDFIDPLayout getIDPLayout()
	{
		return (JDFIDPLayout) getElement(ElementName.IDPLAYOUT, null, 0);
	}

	/**
	 * (25) getCreateIDPLayout
	 * 
	 * @return JDFIDPLayout the element
	 */
	public JDFIDPLayout getCreateIDPLayout()
	{
		return (JDFIDPLayout) getCreateElement_JDFElement(ElementName.IDPLAYOUT, null, 0);
	}

	/**
	 * (29) append element IDPLayout
	 *
	 * @return JDFIDPLayout the element
	 * @ if the element already exists
	 */
	public JDFIDPLayout appendIDPLayout()
	{
		return (JDFIDPLayout) appendElementN(ElementName.IDPLAYOUT, 1, null);
	}

	/**
	 * (24) const get element JobSheet
	 *
	 * @return JDFJobSheet the element
	 */
	public JDFJobSheet getJobSheet()
	{
		return (JDFJobSheet) getElement(ElementName.JOBSHEET, null, 0);
	}

	/**
	 * (25) getCreateJobSheet
	 * 
	 * @return JDFJobSheet the element
	 */
	public JDFJobSheet getCreateJobSheet()
	{
		return (JDFJobSheet) getCreateElement_JDFElement(ElementName.JOBSHEET, null, 0);
	}

	/**
	 * (26) getCreateJobSheet
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFJobSheet the element
	 */
	public JDFJobSheet getCreateJobSheet(final int iSkip)
	{
		return (JDFJobSheet) getCreateElement_JDFElement(ElementName.JOBSHEET, null, iSkip);
	}

	/**
	 * (27) const get element JobSheet
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFJobSheet the element
	 *         default is getJobSheet(0)
	 */
	public JDFJobSheet getJobSheet(final int iSkip)
	{
		return (JDFJobSheet) getElement(ElementName.JOBSHEET, null, iSkip);
	}

	/**
	 * Get all JobSheet from the current element
	 * 
	 * @return Collection<JDFJobSheet>, null if none are available
	 */
	public Collection<JDFJobSheet> getAllJobSheet()
	{
		return getChildArrayByClass(JDFJobSheet.class, false, 0);
	}

	/**
	 * (30) append element JobSheet
	 *
	 * @return JDFJobSheet the element
	 */
	public JDFJobSheet appendJobSheet()
	{
		return (JDFJobSheet) appendElement(ElementName.JOBSHEET, null);
	}

	/**
	 * (24) const get element MediaIntent
	 *
	 * @return JDFMediaIntent the element
	 */
	public JDFMediaIntent getMediaIntent()
	{
		return (JDFMediaIntent) getElement(ElementName.MEDIAINTENT, null, 0);
	}

	/**
	 * (25) getCreateMediaIntent
	 * 
	 * @return JDFMediaIntent the element
	 */
	public JDFMediaIntent getCreateMediaIntent()
	{
		return (JDFMediaIntent) getCreateElement_JDFElement(ElementName.MEDIAINTENT, null, 0);
	}

	/**
	 * (29) append element MediaIntent
	 *
	 * @return JDFMediaIntent the element
	 * @ if the element already exists
	 */
	public JDFMediaIntent appendMediaIntent()
	{
		return (JDFMediaIntent) appendElementN(ElementName.MEDIAINTENT, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refMediaIntent(final JDFMediaIntent refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element MediaSource
	 *
	 * @return JDFMediaSource the element
	 */
	public JDFMediaSource getMediaSource()
	{
		return (JDFMediaSource) getElement(ElementName.MEDIASOURCE, null, 0);
	}

	/**
	 * (25) getCreateMediaSource
	 * 
	 * @return JDFMediaSource the element
	 */
	public JDFMediaSource getCreateMediaSource()
	{
		return (JDFMediaSource) getCreateElement_JDFElement(ElementName.MEDIASOURCE, null, 0);
	}

	/**
	 * (29) append element MediaSource
	 *
	 * @return JDFMediaSource the element
	 * @ if the element already exists
	 */
	public JDFMediaSource appendMediaSource()
	{
		return (JDFMediaSource) appendElementN(ElementName.MEDIASOURCE, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refMediaSource(final JDFMediaSource refTarget)
	{
		refElement(refTarget);
	}

}
