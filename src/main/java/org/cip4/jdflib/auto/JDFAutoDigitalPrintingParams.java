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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFApprovalParams;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFDisjointing;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFMediaSource;
import org.cip4.jdflib.resource.process.prepress.JDFInk;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoDigitalPrintingParams : public JDFResource
 */

public abstract class JDFAutoDigitalPrintingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[16];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DIRECTPROOFAMOUNT, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, "0");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.MANUALFEED, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.COLLATE, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumCollate.class, 0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.FEEDSHEETLAY, 0x3111111111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumFeedSheetLay.class, 0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINBOTTOM, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINLEFT, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINRIGHT, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINTOP, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.OUTPUTBIN, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.PAGEDELIVERY, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumPageDelivery.class, 0), null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.PRINTPASS, 0x3333311111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumPrintPass.class, 0), null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.PRINTQUALITY, 0x4444444443l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumPrintQuality.class, 0), null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.PRINTINGTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumPrintingType.class, 0), null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.SHEETLAY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumSheetLay.class, 0), null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.SIDES, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumSides.class, 0), null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.STACKAMOUNT, 0x3333311111l, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[6];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COMPONENT, 0x6666666661l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.APPROVALPARAMS, 0x6666666611l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.DISJOINTING, 0x7777666661l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.INK, 0x7777776111l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.MEDIA, 0x6666666661l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.MEDIASOURCE, 0x7777777776l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoDigitalPrintingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDigitalPrintingParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDigitalPrintingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDigitalPrintingParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDigitalPrintingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDigitalPrintingParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
	 * Enumeration strings for numCollate
	 */

	public enum EnumCollate
	{
		None, Sheet, SheetAndSet, SheetSetAndJob, SystemSpecified;

		public static EnumCollate getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumCollate.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numFeedSheetLay
	 */

	public enum EnumFeedSheetLay
	{
		Leading, Trailing;

		public static EnumFeedSheetLay getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumFeedSheetLay.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numPageDelivery
	 */

	public enum EnumPageDelivery
	{
		FanFold, SameOrderFaceUp, SameOrderFaceDown, ReverseOrderFaceUp, ReverseOrderFaceDown, SystemSpecified;

		public static EnumPageDelivery getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPageDelivery.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numPrintPass
	 */

	public enum EnumPrintPass
	{
		OneShot, MultiShot;

		public static EnumPrintPass getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPrintPass.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numPrintQuality
	 */

	public enum EnumPrintQuality
	{
		High, Normal, Draft, SystemSpecified;

		public static EnumPrintQuality getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPrintQuality.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numPrintingType
	 */

	public enum EnumPrintingType
	{
		SheetFed, WebFed, ContinuousFed, SystemSpecified;

		public static EnumPrintingType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPrintingType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numSheetLay
	 */

	public enum EnumSheetLay
	{
		Left, Right, Center, SystemSpecified;

		public static EnumSheetLay getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSheetLay.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numSides
	 */

	public enum EnumSides
	{
		OneSidedBack, OneSidedBackFlipX, OneSidedBackFlipY, OneSidedFront, TwoSided, TwoSidedFlipX, TwoSidedFlipY;

		public static EnumSides getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSides.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DirectProofAmount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DirectProofAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDirectProofAmount(final int value)
	{
		setAttribute(AttributeName.DIRECTPROOFAMOUNT, value, null);
	}

	/**
	 * (15) get int attribute DirectProofAmount
	 *
	 * @return int the value of the attribute
	 */
	public int getDirectProofAmount()
	{
		return getIntAttribute(AttributeName.DIRECTPROOFAMOUNT, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ManualFeed
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ManualFeed
	 *
	 * @param value the value to set the attribute to
	 */
	public void setManualFeed(final boolean value)
	{
		setAttribute(AttributeName.MANUALFEED, value, null);
	}

	/**
	 * (18) get boolean attribute ManualFeed
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getManualFeed()
	{
		return getBoolAttribute(AttributeName.MANUALFEED, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Collate
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Collate
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setCollate(final EnumCollate enumVar)
	{
		setAttribute(AttributeName.COLLATE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Collate
	 *
	 * @return the value of the attribute
	 */
	public EnumCollate getCollate()
	{
		return EnumCollate.getEnum(getAttribute(AttributeName.COLLATE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FeedSheetLay
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FeedSheetLay
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFeedSheetLay(final EnumFeedSheetLay enumVar)
	{
		setAttribute(AttributeName.FEEDSHEETLAY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute FeedSheetLay
	 *
	 * @return the value of the attribute
	 */
	public EnumFeedSheetLay getFeedSheetLay()
	{
		return EnumFeedSheetLay.getEnum(getAttribute(AttributeName.FEEDSHEETLAY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NonPrintableMarginBottom
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NonPrintableMarginBottom
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNonPrintableMarginBottom(final double value)
	{
		setAttribute(AttributeName.NONPRINTABLEMARGINBOTTOM, value, null);
	}

	/**
	 * (17) get double attribute NonPrintableMarginBottom
	 *
	 * @return double the value of the attribute
	 */
	public double getNonPrintableMarginBottom()
	{
		return getRealAttribute(AttributeName.NONPRINTABLEMARGINBOTTOM, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NonPrintableMarginLeft
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NonPrintableMarginLeft
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNonPrintableMarginLeft(final double value)
	{
		setAttribute(AttributeName.NONPRINTABLEMARGINLEFT, value, null);
	}

	/**
	 * (17) get double attribute NonPrintableMarginLeft
	 *
	 * @return double the value of the attribute
	 */
	public double getNonPrintableMarginLeft()
	{
		return getRealAttribute(AttributeName.NONPRINTABLEMARGINLEFT, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NonPrintableMarginRight
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NonPrintableMarginRight
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNonPrintableMarginRight(final double value)
	{
		setAttribute(AttributeName.NONPRINTABLEMARGINRIGHT, value, null);
	}

	/**
	 * (17) get double attribute NonPrintableMarginRight
	 *
	 * @return double the value of the attribute
	 */
	public double getNonPrintableMarginRight()
	{
		return getRealAttribute(AttributeName.NONPRINTABLEMARGINRIGHT, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NonPrintableMarginTop
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NonPrintableMarginTop
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNonPrintableMarginTop(final double value)
	{
		setAttribute(AttributeName.NONPRINTABLEMARGINTOP, value, null);
	}

	/**
	 * (17) get double attribute NonPrintableMarginTop
	 *
	 * @return double the value of the attribute
	 */
	public double getNonPrintableMarginTop()
	{
		return getRealAttribute(AttributeName.NONPRINTABLEMARGINTOP, null, 0.0);
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
	public void setOutputBin(final VString value)
	{
		setAttribute(AttributeName.OUTPUTBIN, value, null);
	}

	/**
	 * (21) get VString attribute OutputBin
	 *
	 * @return VString the value of the attribute
	 */
	public VString getOutputBin()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.OUTPUTBIN, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
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
	 * Methods for Attribute PrintPass
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PrintPass
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPrintPass(final EnumPrintPass enumVar)
	{
		setAttribute(AttributeName.PRINTPASS, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute PrintPass
	 *
	 * @return the value of the attribute
	 */
	public EnumPrintPass getPrintPass()
	{
		return EnumPrintPass.getEnum(getAttribute(AttributeName.PRINTPASS, null, null));
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
	 * Methods for Attribute PrintingType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PrintingType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPrintingType(final EnumPrintingType enumVar)
	{
		setAttribute(AttributeName.PRINTINGTYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute PrintingType
	 *
	 * @return the value of the attribute
	 */
	public EnumPrintingType getPrintingType()
	{
		return EnumPrintingType.getEnum(getAttribute(AttributeName.PRINTINGTYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SheetLay
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SheetLay
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSheetLay(final EnumSheetLay enumVar)
	{
		setAttribute(AttributeName.SHEETLAY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute SheetLay
	 *
	 * @return the value of the attribute
	 */
	public EnumSheetLay getSheetLay()
	{
		return EnumSheetLay.getEnum(getAttribute(AttributeName.SHEETLAY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Sides
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Sides
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSides(final EnumSides enumVar)
	{
		setAttribute(AttributeName.SIDES, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Sides
	 *
	 * @return the value of the attribute
	 */
	public EnumSides getSides()
	{
		return EnumSides.getEnum(getAttribute(AttributeName.SIDES, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StackAmount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StackAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStackAmount(final int value)
	{
		setAttribute(AttributeName.STACKAMOUNT, value, null);
	}

	/**
	 * (15) get int attribute StackAmount
	 *
	 * @return int the value of the attribute
	 */
	public int getStackAmount()
	{
		return getIntAttribute(AttributeName.STACKAMOUNT, null, 0);
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
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
	 * (29) append element Component
	 *
	 * @return JDFComponent the element
	 * @ if the element already exists
	 */
	public JDFComponent appendComponent()
	{
		return (JDFComponent) appendElementN(ElementName.COMPONENT, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refComponent(final JDFComponent refTarget)
	{
		refElement(refTarget);
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
	 * @return JDFApprovalParams the element
	 * @ if the element already exists
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
	public void refApprovalParams(final JDFApprovalParams refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Disjointing
	 *
	 * @return JDFDisjointing the element
	 */
	public JDFDisjointing getDisjointing()
	{
		return (JDFDisjointing) getElement(ElementName.DISJOINTING, null, 0);
	}

	/**
	 * (25) getCreateDisjointing
	 * 
	 * @return JDFDisjointing the element
	 */
	public JDFDisjointing getCreateDisjointing()
	{
		return (JDFDisjointing) getCreateElement_JDFElement(ElementName.DISJOINTING, null, 0);
	}

	/**
	 * (29) append element Disjointing
	 *
	 * @return JDFDisjointing the element
	 * @ if the element already exists
	 */
	public JDFDisjointing appendDisjointing()
	{
		return (JDFDisjointing) appendElementN(ElementName.DISJOINTING, 1, null);
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
	 * (29) append element Ink
	 *
	 * @return JDFInk the element
	 * @ if the element already exists
	 */
	public JDFInk appendInk()
	{
		return (JDFInk) appendElementN(ElementName.INK, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refInk(final JDFInk refTarget)
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
	 * (29) append element Media
	 *
	 * @return JDFMedia the element
	 * @ if the element already exists
	 */
	public JDFMedia appendMedia()
	{
		return (JDFMedia) appendElementN(ElementName.MEDIA, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refMedia(final JDFMedia refTarget)
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
