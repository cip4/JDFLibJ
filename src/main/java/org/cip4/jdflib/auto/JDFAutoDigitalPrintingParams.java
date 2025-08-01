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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
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
 *****************************************************************************
 * class JDFAutoDigitalPrintingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoDigitalPrintingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[16];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DIRECTPROOFAMOUNT, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, "0");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.MANUALFEED, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.COLLATE, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumCollate.getEnum(0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.FEEDSHEETLAY, 0x3111111111l, AttributeInfo.EnumAttributeType.enumeration, EnumFeedSheetLay.getEnum(0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINBOTTOM, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINLEFT, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINRIGHT, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.NONPRINTABLEMARGINTOP, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.OUTPUTBIN, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.PAGEDELIVERY, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumPageDelivery.getEnum(0), null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.PRINTPASS, 0x3333311111l, AttributeInfo.EnumAttributeType.enumeration, EnumPrintPass.getEnum(0), null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.PRINTQUALITY, 0x4444444443l, AttributeInfo.EnumAttributeType.enumeration, EnumPrintQuality.getEnum(0), null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.PRINTINGTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumPrintingType.getEnum(0), null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.SHEETLAY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumSheetLay.getEnum(0), null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.SIDES, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, EnumSides.getEnum(0), null);
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
	protected JDFAutoDigitalPrintingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
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
	protected JDFAutoDigitalPrintingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
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
	protected JDFAutoDigitalPrintingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for Collate
	 */

	public enum ECollate
	{
		None, Sheet, SheetAndSet, SheetSetAndJob, SystemSpecified;

		public static ECollate getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ECollate.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Collate
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumCollate extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumCollate(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumCollate getEnum(String enumName)
		{
			return (EnumCollate) getEnum(EnumCollate.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumCollate getEnum(int enumValue)
		{
			return (EnumCollate) getEnum(EnumCollate.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumCollate.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumCollate.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumCollate.class);
		}

		/**  */
		public static final EnumCollate None = new EnumCollate("None");
		/**  */
		public static final EnumCollate Sheet = new EnumCollate("Sheet");
		/**  */
		public static final EnumCollate SheetAndSet = new EnumCollate("SheetAndSet");
		/**  */
		public static final EnumCollate SheetSetAndJob = new EnumCollate("SheetSetAndJob");
		/**  */
		public static final EnumCollate SystemSpecified = new EnumCollate("SystemSpecified");
	}

	/**
	 * Enumeration strings for FeedSheetLay
	 */

	public enum EFeedSheetLay
	{
		Leading, Trailing;

		public static EFeedSheetLay getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EFeedSheetLay.class, val, null);
		}
	}

	/**
	 * Enumeration strings for FeedSheetLay
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumFeedSheetLay extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumFeedSheetLay(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumFeedSheetLay getEnum(String enumName)
		{
			return (EnumFeedSheetLay) getEnum(EnumFeedSheetLay.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumFeedSheetLay getEnum(int enumValue)
		{
			return (EnumFeedSheetLay) getEnum(EnumFeedSheetLay.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumFeedSheetLay.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumFeedSheetLay.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumFeedSheetLay.class);
		}

		/**  */
		public static final EnumFeedSheetLay Leading = new EnumFeedSheetLay("Leading");
		/**  */
		public static final EnumFeedSheetLay Trailing = new EnumFeedSheetLay("Trailing");
	}

	/**
	 * Enumeration strings for PageDelivery
	 */

	public enum EPageDelivery
	{
		FanFold, SameOrderFaceUp, SameOrderFaceDown, ReverseOrderFaceUp, ReverseOrderFaceDown, SystemSpecified;

		public static EPageDelivery getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EPageDelivery.class, val, null);
		}
	}

	/**
	 * Enumeration strings for PageDelivery
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPageDelivery extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPageDelivery(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPageDelivery getEnum(String enumName)
		{
			return (EnumPageDelivery) getEnum(EnumPageDelivery.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPageDelivery getEnum(int enumValue)
		{
			return (EnumPageDelivery) getEnum(EnumPageDelivery.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPageDelivery.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPageDelivery.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPageDelivery.class);
		}

		/**  */
		public static final EnumPageDelivery FanFold = new EnumPageDelivery("FanFold");
		/**  */
		public static final EnumPageDelivery SameOrderFaceUp = new EnumPageDelivery("SameOrderFaceUp");
		/**  */
		public static final EnumPageDelivery SameOrderFaceDown = new EnumPageDelivery("SameOrderFaceDown");
		/**  */
		public static final EnumPageDelivery ReverseOrderFaceUp = new EnumPageDelivery("ReverseOrderFaceUp");
		/**  */
		public static final EnumPageDelivery ReverseOrderFaceDown = new EnumPageDelivery("ReverseOrderFaceDown");
		/**  */
		public static final EnumPageDelivery SystemSpecified = new EnumPageDelivery("SystemSpecified");
	}

	/**
	 * Enumeration strings for PrintPass
	 */

	public enum EPrintPass
	{
		OneShot, MultiShot;

		public static EPrintPass getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EPrintPass.class, val, null);
		}
	}

	/**
	 * Enumeration strings for PrintPass
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPrintPass extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPrintPass(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPrintPass getEnum(String enumName)
		{
			return (EnumPrintPass) getEnum(EnumPrintPass.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPrintPass getEnum(int enumValue)
		{
			return (EnumPrintPass) getEnum(EnumPrintPass.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPrintPass.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPrintPass.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPrintPass.class);
		}

		/**  */
		public static final EnumPrintPass OneShot = new EnumPrintPass("OneShot");
		/**  */
		public static final EnumPrintPass MultiShot = new EnumPrintPass("MultiShot");
	}

	/**
	 * Enumeration strings for PrintQuality
	 */

	public enum EPrintQuality
	{
		High, Normal, Draft, SystemSpecified;

		public static EPrintQuality getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EPrintQuality.class, val, null);
		}
	}

	/**
	 * Enumeration strings for PrintQuality
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPrintQuality extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPrintQuality(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPrintQuality getEnum(String enumName)
		{
			return (EnumPrintQuality) getEnum(EnumPrintQuality.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPrintQuality getEnum(int enumValue)
		{
			return (EnumPrintQuality) getEnum(EnumPrintQuality.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPrintQuality.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPrintQuality.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPrintQuality.class);
		}

		/**  */
		public static final EnumPrintQuality High = new EnumPrintQuality("High");
		/**  */
		public static final EnumPrintQuality Normal = new EnumPrintQuality("Normal");
		/**  */
		public static final EnumPrintQuality Draft = new EnumPrintQuality("Draft");
		/**  */
		public static final EnumPrintQuality SystemSpecified = new EnumPrintQuality("SystemSpecified");
	}

	/**
	 * Enumeration strings for PrintingType
	 */

	public enum EPrintingType
	{
		SheetFed, WebFed, ContinuousFed, SystemSpecified;

		public static EPrintingType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EPrintingType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for PrintingType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPrintingType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPrintingType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPrintingType getEnum(String enumName)
		{
			return (EnumPrintingType) getEnum(EnumPrintingType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPrintingType getEnum(int enumValue)
		{
			return (EnumPrintingType) getEnum(EnumPrintingType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPrintingType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPrintingType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPrintingType.class);
		}

		/**  */
		public static final EnumPrintingType SheetFed = new EnumPrintingType("SheetFed");
		/**  */
		public static final EnumPrintingType WebFed = new EnumPrintingType("WebFed");
		/**  */
		public static final EnumPrintingType ContinuousFed = new EnumPrintingType("ContinuousFed");
		/**  */
		public static final EnumPrintingType SystemSpecified = new EnumPrintingType("SystemSpecified");
	}

	/**
	 * Enumeration strings for SheetLay
	 */

	public enum ESheetLay
	{
		Left, Right, Center, SystemSpecified;

		public static ESheetLay getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ESheetLay.class, val, null);
		}
	}

	/**
	 * Enumeration strings for SheetLay
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSheetLay extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSheetLay(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSheetLay getEnum(String enumName)
		{
			return (EnumSheetLay) getEnum(EnumSheetLay.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSheetLay getEnum(int enumValue)
		{
			return (EnumSheetLay) getEnum(EnumSheetLay.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSheetLay.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSheetLay.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSheetLay.class);
		}

		/**  */
		public static final EnumSheetLay Left = new EnumSheetLay("Left");
		/**  */
		public static final EnumSheetLay Right = new EnumSheetLay("Right");
		/**  */
		public static final EnumSheetLay Center = new EnumSheetLay("Center");
		/**  */
		public static final EnumSheetLay SystemSpecified = new EnumSheetLay("SystemSpecified");
	}

	/**
	 * Enumeration strings for Sides
	 */

	public enum ESides
	{
		OneSidedBack, OneSidedBackFlipX, OneSidedBackFlipY, OneSidedFront, TwoSided, TwoSidedFlipX, TwoSidedFlipY;

		public static ESides getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ESides.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Sides
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSides extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSides(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSides getEnum(String enumName)
		{
			return (EnumSides) getEnum(EnumSides.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSides getEnum(int enumValue)
		{
			return (EnumSides) getEnum(EnumSides.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSides.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSides.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSides.class);
		}

		/**  */
		public static final EnumSides OneSidedBack = new EnumSides("OneSidedBack");
		/**  */
		public static final EnumSides OneSidedBackFlipX = new EnumSides("OneSidedBackFlipX");
		/**  */
		public static final EnumSides OneSidedBackFlipY = new EnumSides("OneSidedBackFlipY");
		/**  */
		public static final EnumSides OneSidedFront = new EnumSides("OneSidedFront");
		/**  */
		public static final EnumSides TwoSided = new EnumSides("TwoSided");
		/**  */
		public static final EnumSides TwoSidedFlipX = new EnumSides("TwoSidedFlipX");
		/**  */
		public static final EnumSides TwoSidedFlipY = new EnumSides("TwoSidedFlipY");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DirectProofAmount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DirectProofAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDirectProofAmount(int value)
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
	 * --------------------------------------------------------------------- Methods for Attribute ManualFeed ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ManualFeed
	 *
	 * @param value the value to set the attribute to
	 */
	public void setManualFeed(boolean value)
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
	 * --------------------------------------------------------------------- Methods for Attribute Collate ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Collate
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setCollate(ECollate enumVar)
	{
		setAttribute(AttributeName.COLLATE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Collate
	 *
	 * @return the value of the attribute
	 */
	public ECollate getECollate()
	{
		return ECollate.getEnum(getAttribute(AttributeName.COLLATE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Collate ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Collate
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setCollate(EnumCollate enumVar)
	{
		setAttribute(AttributeName.COLLATE, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute FeedSheetLay
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FeedSheetLay
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFeedSheetLay(EFeedSheetLay enumVar)
	{
		setAttribute(AttributeName.FEEDSHEETLAY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute FeedSheetLay
	 *
	 * @return the value of the attribute
	 */
	public EFeedSheetLay getEFeedSheetLay()
	{
		return EFeedSheetLay.getEnum(getAttribute(AttributeName.FEEDSHEETLAY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FeedSheetLay
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FeedSheetLay
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setFeedSheetLay(EnumFeedSheetLay enumVar)
	{
		setAttribute(AttributeName.FEEDSHEETLAY, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute NonPrintableMarginBottom
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NonPrintableMarginBottom
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNonPrintableMarginBottom(double value)
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
	 * --------------------------------------------------------------------- Methods for Attribute NonPrintableMarginLeft
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NonPrintableMarginLeft
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNonPrintableMarginLeft(double value)
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
	 * --------------------------------------------------------------------- Methods for Attribute NonPrintableMarginRight
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NonPrintableMarginRight
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNonPrintableMarginRight(double value)
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
	 * --------------------------------------------------------------------- Methods for Attribute NonPrintableMarginTop
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NonPrintableMarginTop
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNonPrintableMarginTop(double value)
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
	 * --------------------------------------------------------------------- Methods for Attribute OutputBin ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OutputBin
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOutputBin(VString value)
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
	 * --------------------------------------------------------------------- Methods for Attribute PageDelivery
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PageDelivery
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPageDelivery(EPageDelivery enumVar)
	{
		setAttribute(AttributeName.PAGEDELIVERY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute PageDelivery
	 *
	 * @return the value of the attribute
	 */
	public EPageDelivery getEPageDelivery()
	{
		return EPageDelivery.getEnum(getAttribute(AttributeName.PAGEDELIVERY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PageDelivery
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PageDelivery
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setPageDelivery(EnumPageDelivery enumVar)
	{
		setAttribute(AttributeName.PAGEDELIVERY, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute PrintPass ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PrintPass
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPrintPass(EPrintPass enumVar)
	{
		setAttribute(AttributeName.PRINTPASS, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute PrintPass
	 *
	 * @return the value of the attribute
	 */
	public EPrintPass getEPrintPass()
	{
		return EPrintPass.getEnum(getAttribute(AttributeName.PRINTPASS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PrintPass ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PrintPass
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setPrintPass(EnumPrintPass enumVar)
	{
		setAttribute(AttributeName.PRINTPASS, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute PrintQuality
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PrintQuality
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPrintQuality(EPrintQuality enumVar)
	{
		setAttribute(AttributeName.PRINTQUALITY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute PrintQuality
	 *
	 * @return the value of the attribute
	 */
	public EPrintQuality getEPrintQuality()
	{
		return EPrintQuality.getEnum(getAttribute(AttributeName.PRINTQUALITY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PrintQuality
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PrintQuality
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setPrintQuality(EnumPrintQuality enumVar)
	{
		setAttribute(AttributeName.PRINTQUALITY, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute PrintingType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PrintingType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPrintingType(EPrintingType enumVar)
	{
		setAttribute(AttributeName.PRINTINGTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute PrintingType
	 *
	 * @return the value of the attribute
	 */
	public EPrintingType getEPrintingType()
	{
		return EPrintingType.getEnum(getAttribute(AttributeName.PRINTINGTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PrintingType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PrintingType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setPrintingType(EnumPrintingType enumVar)
	{
		setAttribute(AttributeName.PRINTINGTYPE, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute SheetLay ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SheetLay
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSheetLay(ESheetLay enumVar)
	{
		setAttribute(AttributeName.SHEETLAY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute SheetLay
	 *
	 * @return the value of the attribute
	 */
	public ESheetLay getESheetLay()
	{
		return ESheetLay.getEnum(getAttribute(AttributeName.SHEETLAY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SheetLay ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SheetLay
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setSheetLay(EnumSheetLay enumVar)
	{
		setAttribute(AttributeName.SHEETLAY, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute Sides ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Sides
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSides(ESides enumVar)
	{
		setAttribute(AttributeName.SIDES, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Sides
	 *
	 * @return the value of the attribute
	 */
	public ESides getESides()
	{
		return ESides.getEnum(getAttribute(AttributeName.SIDES, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Sides ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Sides
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setSides(EnumSides enumVar)
	{
		setAttribute(AttributeName.SIDES, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute StackAmount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StackAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStackAmount(int value)
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
	 * (29) append element Component
	 *
	 * @return JDFComponent the element @ if the element already exists
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
	public void refComponent(JDFComponent refTarget)
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
	 * @return JDFDisjointing the element @ if the element already exists
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
	 * @return JDFInk the element @ if the element already exists
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
	 * (29) append element Media
	 *
	 * @return JDFMedia the element @ if the element already exists
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
	public void refMedia(JDFMedia refTarget)
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
	 * @return JDFMediaSource the element @ if the element already exists
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
	public void refMediaSource(JDFMediaSource refTarget)
	{
		refElement(refTarget);
	}

}
