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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EUsage;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFLot;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoResourceLink : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoResourceLink extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[26];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTUALAMOUNT, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.AMOUNT, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.COMBINEDPROCESSINDEX, 0x3333333331l, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.COMBINEDPROCESSTYPE, 0x4444444433l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.DRAFTOK, 0x4444444333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.DURATION, 0x3333333333l, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MAXAMOUNT, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.MINAMOUNT, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.MINLATESTATUS, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, JDFResource.EnumResStatus.getEnum(0), null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.MINSTATUS, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, JDFResource.EnumResStatus.getEnum(0), null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.ORIENTATION, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumOrientation.getEnum(0), null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.PIPEPARTIDKEYS, 0x4444333333l, AttributeInfo.EnumAttributeType.enumerations, EnumPipePartIDKeys.getEnum(0), null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.PIPEPAUSE, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.PIPEPROTOCOL, 0x4444433331l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.PIPERESUME, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.PIPEURL, 0x3333333333l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.PROCESSUSAGE, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.RECOMMENDATION, 0x4444444433l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.REMOTEPIPEENDPAUSE, 0x4444433333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[19] = new AtrInfoTable(AttributeName.REMOTEPIPEENDRESUME, 0x4444433333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[20] = new AtrInfoTable(AttributeName.RREF, 0x2222222222l, AttributeInfo.EnumAttributeType.IDREF, null, null);
		atrInfoTable[21] = new AtrInfoTable(AttributeName.RSUBREF, 0x4444444433l, AttributeInfo.EnumAttributeType.IDREF, null, null);
		atrInfoTable[22] = new AtrInfoTable(AttributeName.START, 0x3333333333l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[23] = new AtrInfoTable(AttributeName.STARTOFFSET, 0x3333333333l, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[24] = new AtrInfoTable(AttributeName.USAGE, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration, JDFResourceLink.EnumUsage.getEnum(0), null);
		atrInfoTable[25] = new AtrInfoTable(AttributeName.TRANSFORMATION, 0x3333333331l, AttributeInfo.EnumAttributeType.matrix, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.AMOUNTPOOL, 0x6666666661l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.LOT, 0x4444333111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoResourceLink
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoResourceLink(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoResourceLink
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoResourceLink(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoResourceLink
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoResourceLink(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for Orientation
	 */

	public enum EOrientation
	{
		Rotate0, Rotate90, Rotate180, Rotate270, Flip0, Flip90, Flip180, Flip270;

		public static EOrientation getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EOrientation.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Orientation
	 * 
	 * @deprecated use EOrientation instead
	 */

	@Deprecated
	@SuppressWarnings("rawtypes")
	public static class EnumOrientation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumOrientation(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumOrientation getEnum(final String enumName)
		{
			return (EnumOrientation) getEnum(EnumOrientation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumOrientation getEnum(final int enumValue)
		{
			return (EnumOrientation) getEnum(EnumOrientation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumOrientation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumOrientation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumOrientation.class);
		}

		/**  */
		public static final EnumOrientation Rotate0 = new EnumOrientation("Rotate0");
		/**  */
		public static final EnumOrientation Rotate90 = new EnumOrientation("Rotate90");
		/**  */
		public static final EnumOrientation Rotate180 = new EnumOrientation("Rotate180");
		/**  */
		public static final EnumOrientation Rotate270 = new EnumOrientation("Rotate270");
		/**  */
		public static final EnumOrientation Flip0 = new EnumOrientation("Flip0");
		/**  */
		public static final EnumOrientation Flip90 = new EnumOrientation("Flip90");
		/**  */
		public static final EnumOrientation Flip180 = new EnumOrientation("Flip180");
		/**  */
		public static final EnumOrientation Flip270 = new EnumOrientation("Flip270");
	}

	/**
	 * Enumeration strings for PipePartIDKeys
	 */

	public enum EPipePartIDKeys
	{
		BinderySignatureName, BinderySignaturePaginationIndex, BlockName, BundleItemIndex, CellIndex, Condition, DeliveryUnit0, DeliveryUnit1, DeliveryUnit2, DeliveryUnit3, DeliveryUnit4, DeliveryUnit5, DeliveryUnit6, DeliveryUnit7, DeliveryUnit8, DeliveryUnit9, DocCopies, DocIndex, DocRunIndex, DocSheetIndex, DocTags, Edition, EditionVersion, FountainNumber, ItemNames, LayerIDs, Location, Metadata0, Metadata1, Metadata2, Metadata3, Metadata4, Metadata5, Metadata6, Metadata7, Metadata8, Metadata9, Option, PageNumber, PageTags, PlateLayout, PartVersion, PreflightRule, ProductPart, PreviewType, RibbonName, Run, RunIndex, RunPage, RunTags, RunSet, SectionIndex, Separation, SetCopies, SetDocIndex, SetIndex, SetRunIndex, SetSheetIndex, SetTags, SheetIndex, SheetName, Side, SignatureName, StationName, SubRun, TileID, WebName, WebProduct, WebSetup;

		public static EPipePartIDKeys getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EPipePartIDKeys.class, val, null);
		}
	}

	/**
	 * Enumeration strings for PipePartIDKeys
	 * 
	 * @deprecated use EPipePartIDKeys instead
	 */

	@Deprecated
	@SuppressWarnings("rawtypes")
	public static class EnumPipePartIDKeys extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPipePartIDKeys(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPipePartIDKeys getEnum(final String enumName)
		{
			return (EnumPipePartIDKeys) getEnum(EnumPipePartIDKeys.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPipePartIDKeys getEnum(final int enumValue)
		{
			return (EnumPipePartIDKeys) getEnum(EnumPipePartIDKeys.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPipePartIDKeys.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPipePartIDKeys.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPipePartIDKeys.class);
		}

		/**  */
		public static final EnumPipePartIDKeys BinderySignatureName = new EnumPipePartIDKeys("BinderySignatureName");
		/**  */
		public static final EnumPipePartIDKeys BinderySignaturePaginationIndex = new EnumPipePartIDKeys("BinderySignaturePaginationIndex");
		/**  */
		public static final EnumPipePartIDKeys BlockName = new EnumPipePartIDKeys("BlockName");
		/**  */
		public static final EnumPipePartIDKeys BundleItemIndex = new EnumPipePartIDKeys("BundleItemIndex");
		/**  */
		public static final EnumPipePartIDKeys CellIndex = new EnumPipePartIDKeys("CellIndex");
		/**  */
		public static final EnumPipePartIDKeys Condition = new EnumPipePartIDKeys("Condition");
		/**  */
		public static final EnumPipePartIDKeys DeliveryUnit0 = new EnumPipePartIDKeys("DeliveryUnit0");
		/**  */
		public static final EnumPipePartIDKeys DeliveryUnit1 = new EnumPipePartIDKeys("DeliveryUnit1");
		/**  */
		public static final EnumPipePartIDKeys DeliveryUnit2 = new EnumPipePartIDKeys("DeliveryUnit2");
		/**  */
		public static final EnumPipePartIDKeys DeliveryUnit3 = new EnumPipePartIDKeys("DeliveryUnit3");
		/**  */
		public static final EnumPipePartIDKeys DeliveryUnit4 = new EnumPipePartIDKeys("DeliveryUnit4");
		/**  */
		public static final EnumPipePartIDKeys DeliveryUnit5 = new EnumPipePartIDKeys("DeliveryUnit5");
		/**  */
		public static final EnumPipePartIDKeys DeliveryUnit6 = new EnumPipePartIDKeys("DeliveryUnit6");
		/**  */
		public static final EnumPipePartIDKeys DeliveryUnit7 = new EnumPipePartIDKeys("DeliveryUnit7");
		/**  */
		public static final EnumPipePartIDKeys DeliveryUnit8 = new EnumPipePartIDKeys("DeliveryUnit8");
		/**  */
		public static final EnumPipePartIDKeys DeliveryUnit9 = new EnumPipePartIDKeys("DeliveryUnit9");
		/**  */
		public static final EnumPipePartIDKeys DocCopies = new EnumPipePartIDKeys("DocCopies");
		/**  */
		public static final EnumPipePartIDKeys DocIndex = new EnumPipePartIDKeys("DocIndex");
		/**  */
		public static final EnumPipePartIDKeys DocRunIndex = new EnumPipePartIDKeys("DocRunIndex");
		/**  */
		public static final EnumPipePartIDKeys DocSheetIndex = new EnumPipePartIDKeys("DocSheetIndex");
		/**  */
		public static final EnumPipePartIDKeys DocTags = new EnumPipePartIDKeys("DocTags");
		/**  */
		public static final EnumPipePartIDKeys Edition = new EnumPipePartIDKeys("Edition");
		/**  */
		public static final EnumPipePartIDKeys EditionVersion = new EnumPipePartIDKeys("EditionVersion");
		/**  */
		public static final EnumPipePartIDKeys FountainNumber = new EnumPipePartIDKeys("FountainNumber");
		/**  */
		public static final EnumPipePartIDKeys ItemNames = new EnumPipePartIDKeys("ItemNames");
		/**  */
		public static final EnumPipePartIDKeys LayerIDs = new EnumPipePartIDKeys("LayerIDs");
		/**  */
		public static final EnumPipePartIDKeys Location = new EnumPipePartIDKeys("Location");
		/**  */
		public static final EnumPipePartIDKeys Metadata0 = new EnumPipePartIDKeys("Metadata0");
		/**  */
		public static final EnumPipePartIDKeys Metadata1 = new EnumPipePartIDKeys("Metadata1");
		/**  */
		public static final EnumPipePartIDKeys Metadata2 = new EnumPipePartIDKeys("Metadata2");
		/**  */
		public static final EnumPipePartIDKeys Metadata3 = new EnumPipePartIDKeys("Metadata3");
		/**  */
		public static final EnumPipePartIDKeys Metadata4 = new EnumPipePartIDKeys("Metadata4");
		/**  */
		public static final EnumPipePartIDKeys Metadata5 = new EnumPipePartIDKeys("Metadata5");
		/**  */
		public static final EnumPipePartIDKeys Metadata6 = new EnumPipePartIDKeys("Metadata6");
		/**  */
		public static final EnumPipePartIDKeys Metadata7 = new EnumPipePartIDKeys("Metadata7");
		/**  */
		public static final EnumPipePartIDKeys Metadata8 = new EnumPipePartIDKeys("Metadata8");
		/**  */
		public static final EnumPipePartIDKeys Metadata9 = new EnumPipePartIDKeys("Metadata9");
		/**  */
		public static final EnumPipePartIDKeys Option = new EnumPipePartIDKeys("Option");
		/**  */
		public static final EnumPipePartIDKeys PageNumber = new EnumPipePartIDKeys("PageNumber");
		/**  */
		public static final EnumPipePartIDKeys PageTags = new EnumPipePartIDKeys("PageTags");
		/**  */
		public static final EnumPipePartIDKeys PlateLayout = new EnumPipePartIDKeys("PlateLayout");
		/**  */
		public static final EnumPipePartIDKeys PartVersion = new EnumPipePartIDKeys("PartVersion");
		/**  */
		public static final EnumPipePartIDKeys PreflightRule = new EnumPipePartIDKeys("PreflightRule");
		/**  */
		public static final EnumPipePartIDKeys ProductPart = new EnumPipePartIDKeys("ProductPart");
		/**  */
		public static final EnumPipePartIDKeys PreviewType = new EnumPipePartIDKeys("PreviewType");
		/**  */
		public static final EnumPipePartIDKeys RibbonName = new EnumPipePartIDKeys("RibbonName");
		/**  */
		public static final EnumPipePartIDKeys Run = new EnumPipePartIDKeys("Run");
		/**  */
		public static final EnumPipePartIDKeys RunIndex = new EnumPipePartIDKeys("RunIndex");
		/**  */
		public static final EnumPipePartIDKeys RunPage = new EnumPipePartIDKeys("RunPage");
		/**  */
		public static final EnumPipePartIDKeys RunTags = new EnumPipePartIDKeys("RunTags");
		/**  */
		public static final EnumPipePartIDKeys RunSet = new EnumPipePartIDKeys("RunSet");
		/**  */
		public static final EnumPipePartIDKeys SectionIndex = new EnumPipePartIDKeys("SectionIndex");
		/**  */
		public static final EnumPipePartIDKeys Separation = new EnumPipePartIDKeys("Separation");
		/**  */
		public static final EnumPipePartIDKeys SetCopies = new EnumPipePartIDKeys("SetCopies");
		/**  */
		public static final EnumPipePartIDKeys SetDocIndex = new EnumPipePartIDKeys("SetDocIndex");
		/**  */
		public static final EnumPipePartIDKeys SetIndex = new EnumPipePartIDKeys("SetIndex");
		/**  */
		public static final EnumPipePartIDKeys SetRunIndex = new EnumPipePartIDKeys("SetRunIndex");
		/**  */
		public static final EnumPipePartIDKeys SetSheetIndex = new EnumPipePartIDKeys("SetSheetIndex");
		/**  */
		public static final EnumPipePartIDKeys SetTags = new EnumPipePartIDKeys("SetTags");
		/**  */
		public static final EnumPipePartIDKeys SheetIndex = new EnumPipePartIDKeys("SheetIndex");
		/**  */
		public static final EnumPipePartIDKeys SheetName = new EnumPipePartIDKeys("SheetName");
		/**  */
		public static final EnumPipePartIDKeys Side = new EnumPipePartIDKeys("Side");
		/**  */
		public static final EnumPipePartIDKeys SignatureName = new EnumPipePartIDKeys("SignatureName");
		/**  */
		public static final EnumPipePartIDKeys StationName = new EnumPipePartIDKeys("StationName");
		/**  */
		public static final EnumPipePartIDKeys SubRun = new EnumPipePartIDKeys("SubRun");
		/**  */
		public static final EnumPipePartIDKeys TileID = new EnumPipePartIDKeys("TileID");
		/**  */
		public static final EnumPipePartIDKeys WebName = new EnumPipePartIDKeys("WebName");
		/**  */
		public static final EnumPipePartIDKeys WebProduct = new EnumPipePartIDKeys("WebProduct");
		/**  */
		public static final EnumPipePartIDKeys WebSetup = new EnumPipePartIDKeys("WebSetup");
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
	public void setActualAmount(final double value)
	{
		setAttribute(AttributeName.ACTUALAMOUNT, value, null);
	}

	/**
	 * (17) get double attribute ActualAmount
	 * 
	 * @return double the value of the attribute
	 */
	public double getActualAmount()
	{
		return getRealAttribute(AttributeName.ACTUALAMOUNT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Amount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Amount
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setAmount(final double value)
	{
		setAttribute(AttributeName.AMOUNT, value, null);
	}

	/**
	 * (17) get double attribute Amount
	 * 
	 * @return double the value of the attribute
	 */
	public double getAmount()
	{
		return getRealAttribute(AttributeName.AMOUNT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CombinedProcessIndex
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CombinedProcessIndex
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setCombinedProcessIndex(final JDFIntegerList value)
	{
		setAttribute(AttributeName.COMBINEDPROCESSINDEX, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute CombinedProcessIndex
	 * 
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getCombinedProcessIndex()
	{
		final String strAttrName = getAttribute(AttributeName.COMBINEDPROCESSINDEX, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CombinedProcessType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CombinedProcessType
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setCombinedProcessType(final String value)
	{
		setAttribute(AttributeName.COMBINEDPROCESSTYPE, value, null);
	}

	/**
	 * (23) get String attribute CombinedProcessType
	 * 
	 * @return the value of the attribute
	 */
	public String getCombinedProcessType()
	{
		return getAttribute(AttributeName.COMBINEDPROCESSTYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DraftOK ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DraftOK
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setDraftOK(final boolean value)
	{
		setAttribute(AttributeName.DRAFTOK, value, null);
	}

	/**
	 * (18) get boolean attribute DraftOK
	 * 
	 * @return boolean the value of the attribute
	 */
	public boolean getDraftOK()
	{
		return getBoolAttribute(AttributeName.DRAFTOK, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Duration ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Duration
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setDuration(final JDFDuration value)
	{
		setAttribute(AttributeName.DURATION, value, null);
	}

	/**
	 * (20) get JDFDuration attribute Duration
	 * 
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getDuration()
	{
		final String strAttrName = getAttribute(AttributeName.DURATION, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxAmount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxAmount
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setMaxAmount(final double value)
	{
		setAttribute(AttributeName.MAXAMOUNT, value, null);
	}

	/**
	 * (17) get double attribute MaxAmount
	 * 
	 * @return double the value of the attribute
	 */
	public double getMaxAmount()
	{
		return getRealAttribute(AttributeName.MAXAMOUNT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinAmount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MinAmount
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setMinAmount(final double value)
	{
		setAttribute(AttributeName.MINAMOUNT, value, null);
	}

	/**
	 * (17) get double attribute MinAmount
	 * 
	 * @return double the value of the attribute
	 */
	public double getMinAmount()
	{
		return getRealAttribute(AttributeName.MINAMOUNT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinLateStatus
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MinLateStatus
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMinLateStatus(final JDFResource.EResStatus enumVar)
	{
		setAttribute(AttributeName.MINLATESTATUS, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute MinLateStatus
	 * 
	 * @return the value of the attribute
	 */
	public JDFResource.EResStatus getEMinLateStatus()
	{
		return JDFResource.EResStatus.getEnum(getAttribute(AttributeName.MINLATESTATUS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinLateStatus
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MinLateStatus
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setMinLateStatus(JDFResource.EResStatus) based on java.lang.enum instead
	 */
	@Deprecated
	public void setMinLateStatus(final JDFResource.EnumResStatus enumVar)
	{
		setAttribute(AttributeName.MINLATESTATUS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute MinLateStatus
	 * 
	 * @return the value of the attribute
	 * @deprecated use JDFResource.EResStatus getEMinLateStatus() based on java.lang.enum instead
	 */
	@Deprecated
	public JDFResource.EnumResStatus getMinLateStatus()
	{
		return JDFResource.EnumResStatus.getEnum(getAttribute(AttributeName.MINLATESTATUS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinStatus ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MinStatus
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMinStatus(final JDFResource.EResStatus enumVar)
	{
		setAttribute(AttributeName.MINSTATUS, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute MinStatus
	 * 
	 * @return the value of the attribute
	 */
	public JDFResource.EResStatus getEMinStatus()
	{
		return JDFResource.EResStatus.getEnum(getAttribute(AttributeName.MINSTATUS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinStatus ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MinStatus
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setMinStatus(JDFResource.EResStatus) based on java.lang.enum instead
	 */
	@Deprecated
	public void setMinStatus(final JDFResource.EnumResStatus enumVar)
	{
		setAttribute(AttributeName.MINSTATUS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute MinStatus
	 * 
	 * @return the value of the attribute
	 * @deprecated use JDFResource.EResStatus getEMinStatus() based on java.lang.enum instead
	 */
	@Deprecated
	public JDFResource.EnumResStatus getMinStatus()
	{
		return JDFResource.EnumResStatus.getEnum(getAttribute(AttributeName.MINSTATUS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Orientation ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Orientation
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOrientation(final EOrientation enumVar)
	{
		setAttribute(AttributeName.ORIENTATION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Orientation
	 * 
	 * @return the value of the attribute
	 */
	public EOrientation getEOrientation()
	{
		return EOrientation.getEnum(getAttribute(AttributeName.ORIENTATION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Orientation ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Orientation
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setOrientation(EOrientation) based on java.lang.enum instead
	 */
	@Deprecated
	public void setOrientation(final EnumOrientation enumVar)
	{
		setAttribute(AttributeName.ORIENTATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Orientation
	 * 
	 * @return the value of the attribute
	 * @deprecated use EOrientation getEOrientation() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumOrientation getOrientation()
	{
		return EnumOrientation.getEnum(getAttribute(AttributeName.ORIENTATION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PipePartIDKeys
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute PipePartIDKeys
	 * 
	 * @param v List of the enumeration values
	 */
	public void setEPipePartIDKeys(final List<EPipePartIDKeys> v)
	{
		setEnumsAttribute(AttributeName.PIPEPARTIDKEYS, v, null);
	}

	/**
	 * (9.2) get PipePartIDKeys attribute PipePartIDKeys
	 * 
	 * @return List of the enumerations
	 */
	public List<EPipePartIDKeys> getEnumsPipePartIDKeys()
	{
		return getEnumerationsAttribute(AttributeName.PIPEPARTIDKEYS, null, EPipePartIDKeys.class);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PipePartIDKeys
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute PipePartIDKeys
	 * 
	 * @param v List of the enumeration values
	 * @deprecated use setEPipePartIDKeys(List<EPipePartIDKeys>) based on java.lang.enum instead
	 */
	@Deprecated
	public void setPipePartIDKeys(final List<EnumPipePartIDKeys> v)
	{
		setEnumerationsAttribute(AttributeName.PIPEPARTIDKEYS, v, null);
	}

	/**
	 * (9.2) get PipePartIDKeys attribute PipePartIDKeys
	 * 
	 * @return Vector of the enumerations
	 * @deprecated use List<EPipePartIDKeys> getEnumsPipePartIDKeys() based on java.lang.enum instead
	 */
	@Deprecated
	public Vector<EnumPipePartIDKeys> getPipePartIDKeys()
	{
		return getEnumerationsAttribute(AttributeName.PIPEPARTIDKEYS, null, EnumPipePartIDKeys.getEnum(0), false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PipePause ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PipePause
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setPipePause(final double value)
	{
		setAttribute(AttributeName.PIPEPAUSE, value, null);
	}

	/**
	 * (17) get double attribute PipePause
	 * 
	 * @return double the value of the attribute
	 */
	public double getPipePause()
	{
		return getRealAttribute(AttributeName.PIPEPAUSE, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PipeProtocol
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PipeProtocol
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setPipeProtocol(final String value)
	{
		setAttribute(AttributeName.PIPEPROTOCOL, value, null);
	}

	/**
	 * (23) get String attribute PipeProtocol
	 * 
	 * @return the value of the attribute
	 */
	public String getPipeProtocol()
	{
		return getAttribute(AttributeName.PIPEPROTOCOL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PipeResume ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PipeResume
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setPipeResume(final double value)
	{
		setAttribute(AttributeName.PIPERESUME, value, null);
	}

	/**
	 * (17) get double attribute PipeResume
	 * 
	 * @return double the value of the attribute
	 */
	public double getPipeResume()
	{
		return getRealAttribute(AttributeName.PIPERESUME, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PipeURL ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PipeURL
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setPipeURL(final String value)
	{
		setAttribute(AttributeName.PIPEURL, value, null);
	}

	/**
	 * (23) get String attribute PipeURL
	 * 
	 * @return the value of the attribute
	 */
	public String getPipeURL()
	{
		return getAttribute(AttributeName.PIPEURL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProcessUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ProcessUsage
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setProcessUsage(final String value)
	{
		setAttribute(AttributeName.PROCESSUSAGE, value, null);
	}

	/**
	 * (23) get String attribute ProcessUsage
	 * 
	 * @return the value of the attribute
	 */
	public String getProcessUsage()
	{
		return getAttribute(AttributeName.PROCESSUSAGE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Recommendation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Recommendation
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setRecommendation(final boolean value)
	{
		setAttribute(AttributeName.RECOMMENDATION, value, null);
	}

	/**
	 * (18) get boolean attribute Recommendation
	 * 
	 * @return boolean the value of the attribute
	 */
	public boolean getRecommendation()
	{
		return getBoolAttribute(AttributeName.RECOMMENDATION, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RemotePipeEndPause
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RemotePipeEndPause
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setRemotePipeEndPause(final double value)
	{
		setAttribute(AttributeName.REMOTEPIPEENDPAUSE, value, null);
	}

	/**
	 * (17) get double attribute RemotePipeEndPause
	 * 
	 * @return double the value of the attribute
	 */
	public double getRemotePipeEndPause()
	{
		return getRealAttribute(AttributeName.REMOTEPIPEENDPAUSE, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RemotePipeEndResume
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RemotePipeEndResume
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setRemotePipeEndResume(final double value)
	{
		setAttribute(AttributeName.REMOTEPIPEENDRESUME, value, null);
	}

	/**
	 * (17) get double attribute RemotePipeEndResume
	 * 
	 * @return double the value of the attribute
	 */
	public double getRemotePipeEndResume()
	{
		return getRealAttribute(AttributeName.REMOTEPIPEENDRESUME, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute rRef ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute rRef
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setrRef(final String value)
	{
		setAttribute(AttributeName.RREF, value, null);
	}

	/**
	 * (23) get String attribute rRef
	 * 
	 * @return the value of the attribute
	 */
	public String getrRef()
	{
		return getAttribute(AttributeName.RREF, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute rSubRef ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute rSubRef
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setrSubRef(final String value)
	{
		setAttribute(AttributeName.RSUBREF, value, null);
	}

	/**
	 * (23) get String attribute rSubRef
	 * 
	 * @return the value of the attribute
	 */
	public String getrSubRef()
	{
		return getAttribute(AttributeName.RSUBREF, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Start ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute Start
	 * 
	 * @param value the value to set the attribute to or null
	 */
	public void setStart(final JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
			date = new JDFDate();
		setAttribute(AttributeName.START, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute Start
	 * 
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getStart()
	{
		final String str = getAttribute(AttributeName.START, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StartOffset ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StartOffset
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setStartOffset(final JDFDuration value)
	{
		setAttribute(AttributeName.STARTOFFSET, value, null);
	}

	/**
	 * (20) get JDFDuration attribute StartOffset
	 * 
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getStartOffset()
	{
		final String strAttrName = getAttribute(AttributeName.STARTOFFSET, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Usage ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Usage
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setUsage(final EUsage enumVar)
	{
		setAttribute(AttributeName.USAGE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Usage
	 * 
	 * @return the value of the attribute
	 */
	public EUsage getEUsage()
	{
		return EUsage.getEnum(getAttribute(AttributeName.USAGE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Usage ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Usage
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setUsage(EUsage) based on java.lang.enum instead
	 */
	@Deprecated
	public void setUsage(final EnumUsage enumVar)
	{
		setAttribute(AttributeName.USAGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Usage
	 * 
	 * @return the value of the attribute
	 * @deprecated use EUsage getEUsage() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumUsage getUsage()
	{
		return EnumUsage.getEnum(getAttribute(AttributeName.USAGE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Transformation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Transformation
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setTransformation(final JDFMatrix value)
	{
		setAttribute(AttributeName.TRANSFORMATION, value, null);
	}

	/**
	 * (20) get JDFMatrix attribute Transformation
	 * 
	 * @return JDFMatrix the value of the attribute, null if a the attribute value is not a valid to create a JDFMatrix
	 */
	public JDFMatrix getTransformation()
	{
		final String strAttrName = getAttribute(AttributeName.TRANSFORMATION, null, null);
		final JDFMatrix nPlaceHolder = JDFMatrix.createMatrix(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element AmountPool
	 * 
	 * @return JDFAmountPool the element
	 */
	public JDFAmountPool getAmountPool()
	{
		return (JDFAmountPool) getElement(ElementName.AMOUNTPOOL, null, 0);
	}

	/**
	 * (25) getCreateAmountPool
	 * 
	 * @return JDFAmountPool the element
	 */
	public JDFAmountPool getCreateAmountPool()
	{
		return (JDFAmountPool) getCreateElement_JDFElement(ElementName.AMOUNTPOOL, null, 0);
	}

	/**
	 * (29) append element AmountPool
	 * 
	 * @return JDFAmountPool the element @ if the element already exists
	 */
	public JDFAmountPool appendAmountPool()
	{
		return (JDFAmountPool) appendElementN(ElementName.AMOUNTPOOL, 1, null);
	}

	/**
	 * (24) const get element Lot
	 * 
	 * @return JDFLot the element
	 */
	public JDFLot getLot()
	{
		return (JDFLot) getElement(ElementName.LOT, null, 0);
	}

	/**
	 * (25) getCreateLot
	 * 
	 * @return JDFLot the element
	 */
	public JDFLot getCreateLot()
	{
		return (JDFLot) getCreateElement_JDFElement(ElementName.LOT, null, 0);
	}

	/**
	 * (26) getCreateLot
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFLot the element
	 */
	public JDFLot getCreateLot(final int iSkip)
	{
		return (JDFLot) getCreateElement_JDFElement(ElementName.LOT, null, iSkip);
	}

	/**
	 * (27) const get element Lot
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFLot the element default is getLot(0)
	 */
	public JDFLot getLot(final int iSkip)
	{
		return (JDFLot) getElement(ElementName.LOT, null, iSkip);
	}

	/**
	 * Get all Lot from the current element
	 * 
	 * @return Collection<JDFLot>, null if none are available
	 */
	public Collection<JDFLot> getAllLot()
	{
		return getChildArrayByClass(JDFLot.class, false, 0);
	}

	/**
	 * (30) append element Lot
	 * 
	 * @return JDFLot the element
	 */
	public JDFLot appendLot()
	{
		return (JDFLot) appendElement(ElementName.LOT, null);
	}

}
