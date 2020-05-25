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
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFLot;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;

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
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTUALAMOUNT, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.AMOUNT, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.COMBINEDPROCESSINDEX, 0x33333331, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.COMBINEDPROCESSTYPE, 0x44444433, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.DRAFTOK, 0x44444433, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.DURATION, 0x33333333, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MAXAMOUNT, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.MINAMOUNT, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.MINLATESTATUS, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, JDFResource.EnumResStatus.getEnum(0), null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.MINSTATUS, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, JDFResource.EnumResStatus.getEnum(0), null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.ORIENTATION, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumOrientation.getEnum(0), null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.PIPEPARTIDKEYS, 0x44333333, AttributeInfo.EnumAttributeType.enumerations, EnumPipePartIDKeys.getEnum(0), null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.PIPEPAUSE, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.PIPEPROTOCOL, 0x44433331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.PIPERESUME, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.PIPEURL, 0x33333333, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.PROCESSUSAGE, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.RECOMMENDATION, 0x44444433, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.REMOTEPIPEENDPAUSE, 0x44433333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[19] = new AtrInfoTable(AttributeName.REMOTEPIPEENDRESUME, 0x44433333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[20] = new AtrInfoTable(AttributeName.RREF, 0x22222222, AttributeInfo.EnumAttributeType.IDREF, null, null);
		atrInfoTable[21] = new AtrInfoTable(AttributeName.RSUBREF, 0x44444433, AttributeInfo.EnumAttributeType.IDREF, null, null);
		atrInfoTable[22] = new AtrInfoTable(AttributeName.START, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[23] = new AtrInfoTable(AttributeName.STARTOFFSET, 0x33333333, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[24] = new AtrInfoTable(AttributeName.USAGE, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, JDFResourceLink.EnumUsage.getEnum(0), null);
		atrInfoTable[25] = new AtrInfoTable(AttributeName.TRANSFORMATION, 0x33333331, AttributeInfo.EnumAttributeType.matrix, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.AMOUNTPOOL, 0x66666661);
		elemInfoTable[1] = new ElemInfoTable(ElementName.LOT, 0x44333111);
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
	protected JDFAutoResourceLink(CoreDocumentImpl myOwnerDocument, String qualifiedName)
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
	protected JDFAutoResourceLink(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
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
	protected JDFAutoResourceLink(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for MinLateStatus
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumMinLateStatus extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumMinLateStatus(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumMinLateStatus getEnum(String enumName)
		{
			return (EnumMinLateStatus) getEnum(EnumMinLateStatus.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumMinLateStatus getEnum(int enumValue)
		{
			return (EnumMinLateStatus) getEnum(EnumMinLateStatus.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumMinLateStatus.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumMinLateStatus.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumMinLateStatus.class);
		}

		/**  */
		public static final EnumMinLateStatus Incomplete = new EnumMinLateStatus("Incomplete");
		/**  */
		public static final EnumMinLateStatus Rejected = new EnumMinLateStatus("Rejected");
		/**  */
		public static final EnumMinLateStatus Unavailable = new EnumMinLateStatus("Unavailable");
		/**  */
		public static final EnumMinLateStatus InUse = new EnumMinLateStatus("InUse");
		/**  */
		public static final EnumMinLateStatus Draft = new EnumMinLateStatus("Draft");
		/**  */
		public static final EnumMinLateStatus Complete = new EnumMinLateStatus("Complete");
		/**  */
		public static final EnumMinLateStatus Available = new EnumMinLateStatus("Available");
	}

	/**
	 * Enumeration strings for MinStatus
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumMinStatus extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumMinStatus(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumMinStatus getEnum(String enumName)
		{
			return (EnumMinStatus) getEnum(EnumMinStatus.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumMinStatus getEnum(int enumValue)
		{
			return (EnumMinStatus) getEnum(EnumMinStatus.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumMinStatus.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumMinStatus.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumMinStatus.class);
		}

		/**  */
		public static final EnumMinStatus Incomplete = new EnumMinStatus("Incomplete");
		/**  */
		public static final EnumMinStatus Rejected = new EnumMinStatus("Rejected");
		/**  */
		public static final EnumMinStatus Unavailable = new EnumMinStatus("Unavailable");
		/**  */
		public static final EnumMinStatus InUse = new EnumMinStatus("InUse");
		/**  */
		public static final EnumMinStatus Draft = new EnumMinStatus("Draft");
		/**  */
		public static final EnumMinStatus Complete = new EnumMinStatus("Complete");
		/**  */
		public static final EnumMinStatus Available = new EnumMinStatus("Available");
	}

	/**
	 * Enumeration strings for Orientation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumOrientation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumOrientation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumOrientation getEnum(String enumName)
		{
			return (EnumOrientation) getEnum(EnumOrientation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumOrientation getEnum(int enumValue)
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

	@SuppressWarnings("rawtypes")
	public static class EnumPipePartIDKeys extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumPipePartIDKeys(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPipePartIDKeys getEnum(String enumName)
		{
			return (EnumPipePartIDKeys) getEnum(EnumPipePartIDKeys.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPipePartIDKeys getEnum(int enumValue)
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

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute ActualAmount
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute ActualAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setActualAmount(double value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute Amount
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Amount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAmount(double value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute CombinedProcessIndex
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute CombinedProcessIndex
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCombinedProcessIndex(JDFIntegerList value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute CombinedProcessType
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute CombinedProcessType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCombinedProcessType(String value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute DraftOK
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute DraftOK
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDraftOK(boolean value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute Duration
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Duration
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDuration(JDFDuration value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute MaxAmount
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute MaxAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxAmount(double value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute MinAmount
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute MinAmount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMinAmount(double value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute MinLateStatus
	--------------------------------------------------------------------- */
	/**
	 * (5) set attribute MinLateStatus
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMinLateStatus(JDFResource.EnumResStatus enumVar)
	{
		setAttribute(AttributeName.MINLATESTATUS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute MinLateStatus
	 *
	 * @return the value of the attribute
	 */
	public JDFResource.EnumResStatus getMinLateStatus()
	{
		return JDFResource.EnumResStatus.getEnum(getAttribute(AttributeName.MINLATESTATUS, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute MinStatus
	--------------------------------------------------------------------- */
	/**
	 * (5) set attribute MinStatus
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMinStatus(JDFResource.EnumResStatus enumVar)
	{
		setAttribute(AttributeName.MINSTATUS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute MinStatus
	 *
	 * @return the value of the attribute
	 */
	public JDFResource.EnumResStatus getMinStatus()
	{
		return JDFResource.EnumResStatus.getEnum(getAttribute(AttributeName.MINSTATUS, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Orientation
	--------------------------------------------------------------------- */
	/**
	 * (5) set attribute Orientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOrientation(EnumOrientation enumVar)
	{
		setAttribute(AttributeName.ORIENTATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Orientation
	 *
	 * @return the value of the attribute
	 */
	public EnumOrientation getOrientation()
	{
		return EnumOrientation.getEnum(getAttribute(AttributeName.ORIENTATION, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute PipePartIDKeys
	--------------------------------------------------------------------- */
	/**
	 * (5.2) set attribute PipePartIDKeys
	 *
	 * @param v vector of the enumeration values
	 */
	public void setPipePartIDKeys(Vector<? extends ValuedEnum> v)
	{
		setEnumerationsAttribute(AttributeName.PIPEPARTIDKEYS, v, null);
	}

	/**
	 * (9.2) get PipePartIDKeys attribute PipePartIDKeys
	 *
	 * @return Vector of the enumerations
	 */
	public Vector<? extends ValuedEnum> getPipePartIDKeys()
	{
		return getEnumerationsAttribute(AttributeName.PIPEPARTIDKEYS, null, EnumPipePartIDKeys.getEnum(0), false);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute PipePause
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute PipePause
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPipePause(double value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute PipeProtocol
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute PipeProtocol
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPipeProtocol(String value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute PipeResume
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute PipeResume
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPipeResume(double value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute PipeURL
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute PipeURL
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPipeURL(String value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute ProcessUsage
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute ProcessUsage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setProcessUsage(String value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute Recommendation
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Recommendation
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRecommendation(boolean value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute RemotePipeEndPause
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute RemotePipeEndPause
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRemotePipeEndPause(double value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute RemotePipeEndResume
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute RemotePipeEndResume
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRemotePipeEndResume(double value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute rRef
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute rRef
	 *
	 * @param value the value to set the attribute to
	 */
	public void setrRef(String value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute rSubRef
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute rSubRef
	 *
	 * @param value the value to set the attribute to
	 */
	public void setrSubRef(String value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute Start
	--------------------------------------------------------------------- */
	/**
	 * (11) set attribute Start
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setStart(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
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

	/* ---------------------------------------------------------------------
	Methods for Attribute StartOffset
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute StartOffset
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStartOffset(JDFDuration value)
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

	/* ---------------------------------------------------------------------
	Methods for Attribute Usage
	--------------------------------------------------------------------- */
	/**
	 * (5) set attribute Usage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setUsage(EnumUsage enumVar)
	{
		setAttribute(AttributeName.USAGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Usage
	 *
	 * @return the value of the attribute
	 */
	public EnumUsage getUsage()
	{
		return EnumUsage.getEnum(getAttribute(AttributeName.USAGE, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Transformation
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Transformation
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTransformation(JDFMatrix value)
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

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
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
	 * (26) getCreateLot
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFLot the element
	 */
	public JDFLot getCreateLot(int iSkip)
	{
		return (JDFLot) getCreateElement_JDFElement(ElementName.LOT, null, iSkip);
	}

	/**
	 * (27) const get element Lot
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFLot the element default is getLot(0)
	 */
	public JDFLot getLot(int iSkip)
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
