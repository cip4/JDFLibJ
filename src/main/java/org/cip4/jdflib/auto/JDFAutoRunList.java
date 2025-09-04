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

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFByteMap;
import org.cip4.jdflib.resource.process.JDFDisposition;
import org.cip4.jdflib.resource.process.JDFDynamicInput;
import org.cip4.jdflib.resource.process.JDFInsertSheet;
import org.cip4.jdflib.resource.process.JDFInterpretedPDLData;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFMetadataMap;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoRunList : public JDFResource
 */

public abstract class JDFAutoRunList extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[28];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ISPAGE, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.PAGECOPIES, 0x3333333331l, AttributeInfo.EnumAttributeType.integer, null, "1");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.SETCOPIES, 0x3333333331l, AttributeInfo.EnumAttributeType.integer, null, "1");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.AUTOMATION, 0x3333311111l, AttributeInfo.EnumAttributeType.enumeration, EnumAutomation.getEnum(0),
				null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.COMPONENTGRANULARITY, 0x4444443311l, AttributeInfo.EnumAttributeType.enumeration,
				EnumComponentGranularity.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.DIRECTORY, 0x3333333333l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.DOCNAMES, 0x3333333333l, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.DOCS, 0x3333333333l, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.DOCPAGES, 0x3331111111l, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.ENDOFBUNDLEITEM, 0x3333333311l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.ENDOFDOCUMENT, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.ENDOFSET, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.FINISHEDPAGES, 0x3333111111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.FIRSTPAGE, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.IGNORECONTEXT, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration,
				EnumIgnoreContext.getEnum(0), null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.LOGICALPAGE, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.NDOC, 0x4444444431l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.NPAGE, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.NSET, 0x4444444431l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[19] = new AtrInfoTable(AttributeName.PAGELISTINDEX, 0x3333333311l, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
		atrInfoTable[20] = new AtrInfoTable(AttributeName.PAGENAMES, 0x3333333333l, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
		atrInfoTable[21] = new AtrInfoTable(AttributeName.PAGES, 0x3333333333l, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
		atrInfoTable[22] = new AtrInfoTable(AttributeName.RUNTAG, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[23] = new AtrInfoTable(AttributeName.SETNAMES, 0x3333333331l, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
		atrInfoTable[24] = new AtrInfoTable(AttributeName.SETS, 0x3333333331l, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
		atrInfoTable[25] = new AtrInfoTable(AttributeName.SHEETSIDES, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration, EnumSheetSides.getEnum(0),
				null);
		atrInfoTable[26] = new AtrInfoTable(AttributeName.SKIPPAGE, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[27] = new AtrInfoTable(AttributeName.SORTED, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[8];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BYTEMAP, 0x6666666666l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.DYNAMICINPUT, 0x4444443333l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.METADATAMAP, 0x3333331111l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.INSERTSHEET, 0x3333333333l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.LAYOUTELEMENT, 0x6666666666l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.INTERPRETEDPDLDATA, 0x6666666611l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.DISPOSITION, 0x6666666666l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.PAGELIST, 0x6666666666l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoRunList
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoRunList(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoRunList
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoRunList(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoRunList
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoRunList(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		boolean bRet = super.init();
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
	 * Enumeration strings for Automation
	 */

	public enum EAutomation
	{
		Static, Dynamic;

		public static EAutomation getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EAutomation.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Automation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumAutomation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumAutomation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumAutomation getEnum(String enumName)
		{
			return (EnumAutomation) getEnum(EnumAutomation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumAutomation getEnum(int enumValue)
		{
			return (EnumAutomation) getEnum(EnumAutomation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumAutomation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumAutomation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumAutomation.class);
		}

		/**  */
		public static final EnumAutomation Static = new EnumAutomation("Static");
		/**  */
		public static final EnumAutomation Dynamic = new EnumAutomation("Dynamic");
	}

	/**
	 * Enumeration strings for ComponentGranularity
	 */

	public enum EComponentGranularity
	{
		Page, Document, Set, All, BundleItem;

		public static EComponentGranularity getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EComponentGranularity.class, val, null);
		}
	}

	/**
	 * Enumeration strings for ComponentGranularity
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumComponentGranularity extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumComponentGranularity(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumComponentGranularity getEnum(String enumName)
		{
			return (EnumComponentGranularity) getEnum(EnumComponentGranularity.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumComponentGranularity getEnum(int enumValue)
		{
			return (EnumComponentGranularity) getEnum(EnumComponentGranularity.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumComponentGranularity.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumComponentGranularity.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumComponentGranularity.class);
		}

		/**  */
		public static final EnumComponentGranularity Page = new EnumComponentGranularity("Page");
		/**  */
		public static final EnumComponentGranularity Document = new EnumComponentGranularity("Document");
		/**  */
		public static final EnumComponentGranularity Set = new EnumComponentGranularity("Set");
		/**  */
		public static final EnumComponentGranularity All = new EnumComponentGranularity("All");
		/**  */
		public static final EnumComponentGranularity BundleItem = new EnumComponentGranularity("BundleItem");
	}

	/**
	 * Enumeration strings for IgnoreContext
	 */

	public enum EIgnoreContext
	{
		BinderySignatureName, BinderySignaturePaginationIndex, BlockName, BundleItemIndex, CellIndex, Condition, DeliveryUnit0, DeliveryUnit1, DeliveryUnit2, DeliveryUnit3, DeliveryUnit4, DeliveryUnit5, DeliveryUnit6, DeliveryUnit7, DeliveryUnit8, DeliveryUnit9, DocCopies, DocIndex, DocRunIndex, DocSheetIndex, DocTags, Edition, EditionVersion, FountainNumber, ItemNames, LayerIDs, Location, Metadata0, Metadata1, Metadata2, Metadata3, Metadata4, Metadata5, Metadata6, Metadata7, Metadata8, Metadata9, Option, PageNumber, PageTags, PlateLayout, PartVersion, PreflightRule, ProductPart, PreviewType, RibbonName, Run, RunIndex, RunPage, RunTags, RunSet, SectionIndex, Separation, SetCopies, SetDocIndex, SetIndex, SetRunIndex, SetSheetIndex, SetTags, SheetIndex, SheetName, Side, SignatureName, StationName, SubRun, TileID, WebName, WebProduct, WebSetup;

		public static EIgnoreContext getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EIgnoreContext.class, val, null);
		}
	}

	/**
	 * Enumeration strings for IgnoreContext
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumIgnoreContext extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumIgnoreContext(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumIgnoreContext getEnum(String enumName)
		{
			return (EnumIgnoreContext) getEnum(EnumIgnoreContext.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumIgnoreContext getEnum(int enumValue)
		{
			return (EnumIgnoreContext) getEnum(EnumIgnoreContext.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumIgnoreContext.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumIgnoreContext.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumIgnoreContext.class);
		}

		/**  */
		public static final EnumIgnoreContext BinderySignatureName = new EnumIgnoreContext("BinderySignatureName");
		/**  */
		public static final EnumIgnoreContext BinderySignaturePaginationIndex = new EnumIgnoreContext("BinderySignaturePaginationIndex");
		/**  */
		public static final EnumIgnoreContext BlockName = new EnumIgnoreContext("BlockName");
		/**  */
		public static final EnumIgnoreContext BundleItemIndex = new EnumIgnoreContext("BundleItemIndex");
		/**  */
		public static final EnumIgnoreContext CellIndex = new EnumIgnoreContext("CellIndex");
		/**  */
		public static final EnumIgnoreContext Condition = new EnumIgnoreContext("Condition");
		/**  */
		public static final EnumIgnoreContext DeliveryUnit0 = new EnumIgnoreContext("DeliveryUnit0");
		/**  */
		public static final EnumIgnoreContext DeliveryUnit1 = new EnumIgnoreContext("DeliveryUnit1");
		/**  */
		public static final EnumIgnoreContext DeliveryUnit2 = new EnumIgnoreContext("DeliveryUnit2");
		/**  */
		public static final EnumIgnoreContext DeliveryUnit3 = new EnumIgnoreContext("DeliveryUnit3");
		/**  */
		public static final EnumIgnoreContext DeliveryUnit4 = new EnumIgnoreContext("DeliveryUnit4");
		/**  */
		public static final EnumIgnoreContext DeliveryUnit5 = new EnumIgnoreContext("DeliveryUnit5");
		/**  */
		public static final EnumIgnoreContext DeliveryUnit6 = new EnumIgnoreContext("DeliveryUnit6");
		/**  */
		public static final EnumIgnoreContext DeliveryUnit7 = new EnumIgnoreContext("DeliveryUnit7");
		/**  */
		public static final EnumIgnoreContext DeliveryUnit8 = new EnumIgnoreContext("DeliveryUnit8");
		/**  */
		public static final EnumIgnoreContext DeliveryUnit9 = new EnumIgnoreContext("DeliveryUnit9");
		/**  */
		public static final EnumIgnoreContext DocCopies = new EnumIgnoreContext("DocCopies");
		/**  */
		public static final EnumIgnoreContext DocIndex = new EnumIgnoreContext("DocIndex");
		/**  */
		public static final EnumIgnoreContext DocRunIndex = new EnumIgnoreContext("DocRunIndex");
		/**  */
		public static final EnumIgnoreContext DocSheetIndex = new EnumIgnoreContext("DocSheetIndex");
		/**  */
		public static final EnumIgnoreContext DocTags = new EnumIgnoreContext("DocTags");
		/**  */
		public static final EnumIgnoreContext Edition = new EnumIgnoreContext("Edition");
		/**  */
		public static final EnumIgnoreContext EditionVersion = new EnumIgnoreContext("EditionVersion");
		/**  */
		public static final EnumIgnoreContext FountainNumber = new EnumIgnoreContext("FountainNumber");
		/**  */
		public static final EnumIgnoreContext ItemNames = new EnumIgnoreContext("ItemNames");
		/**  */
		public static final EnumIgnoreContext LayerIDs = new EnumIgnoreContext("LayerIDs");
		/**  */
		public static final EnumIgnoreContext Location = new EnumIgnoreContext("Location");
		/**  */
		public static final EnumIgnoreContext Metadata0 = new EnumIgnoreContext("Metadata0");
		/**  */
		public static final EnumIgnoreContext Metadata1 = new EnumIgnoreContext("Metadata1");
		/**  */
		public static final EnumIgnoreContext Metadata2 = new EnumIgnoreContext("Metadata2");
		/**  */
		public static final EnumIgnoreContext Metadata3 = new EnumIgnoreContext("Metadata3");
		/**  */
		public static final EnumIgnoreContext Metadata4 = new EnumIgnoreContext("Metadata4");
		/**  */
		public static final EnumIgnoreContext Metadata5 = new EnumIgnoreContext("Metadata5");
		/**  */
		public static final EnumIgnoreContext Metadata6 = new EnumIgnoreContext("Metadata6");
		/**  */
		public static final EnumIgnoreContext Metadata7 = new EnumIgnoreContext("Metadata7");
		/**  */
		public static final EnumIgnoreContext Metadata8 = new EnumIgnoreContext("Metadata8");
		/**  */
		public static final EnumIgnoreContext Metadata9 = new EnumIgnoreContext("Metadata9");
		/**  */
		public static final EnumIgnoreContext Option = new EnumIgnoreContext("Option");
		/**  */
		public static final EnumIgnoreContext PageNumber = new EnumIgnoreContext("PageNumber");
		/**  */
		public static final EnumIgnoreContext PageTags = new EnumIgnoreContext("PageTags");
		/**  */
		public static final EnumIgnoreContext PlateLayout = new EnumIgnoreContext("PlateLayout");
		/**  */
		public static final EnumIgnoreContext PartVersion = new EnumIgnoreContext("PartVersion");
		/**  */
		public static final EnumIgnoreContext PreflightRule = new EnumIgnoreContext("PreflightRule");
		/**  */
		public static final EnumIgnoreContext ProductPart = new EnumIgnoreContext("ProductPart");
		/**  */
		public static final EnumIgnoreContext PreviewType = new EnumIgnoreContext("PreviewType");
		/**  */
		public static final EnumIgnoreContext RibbonName = new EnumIgnoreContext("RibbonName");
		/**  */
		public static final EnumIgnoreContext Run = new EnumIgnoreContext("Run");
		/**  */
		public static final EnumIgnoreContext RunIndex = new EnumIgnoreContext("RunIndex");
		/**  */
		public static final EnumIgnoreContext RunPage = new EnumIgnoreContext("RunPage");
		/**  */
		public static final EnumIgnoreContext RunTags = new EnumIgnoreContext("RunTags");
		/**  */
		public static final EnumIgnoreContext RunSet = new EnumIgnoreContext("RunSet");
		/**  */
		public static final EnumIgnoreContext SectionIndex = new EnumIgnoreContext("SectionIndex");
		/**  */
		public static final EnumIgnoreContext Separation = new EnumIgnoreContext("Separation");
		/**  */
		public static final EnumIgnoreContext SetCopies = new EnumIgnoreContext("SetCopies");
		/**  */
		public static final EnumIgnoreContext SetDocIndex = new EnumIgnoreContext("SetDocIndex");
		/**  */
		public static final EnumIgnoreContext SetIndex = new EnumIgnoreContext("SetIndex");
		/**  */
		public static final EnumIgnoreContext SetRunIndex = new EnumIgnoreContext("SetRunIndex");
		/**  */
		public static final EnumIgnoreContext SetSheetIndex = new EnumIgnoreContext("SetSheetIndex");
		/**  */
		public static final EnumIgnoreContext SetTags = new EnumIgnoreContext("SetTags");
		/**  */
		public static final EnumIgnoreContext SheetIndex = new EnumIgnoreContext("SheetIndex");
		/**  */
		public static final EnumIgnoreContext SheetName = new EnumIgnoreContext("SheetName");
		/**  */
		public static final EnumIgnoreContext Side = new EnumIgnoreContext("Side");
		/**  */
		public static final EnumIgnoreContext SignatureName = new EnumIgnoreContext("SignatureName");
		/**  */
		public static final EnumIgnoreContext StationName = new EnumIgnoreContext("StationName");
		/**  */
		public static final EnumIgnoreContext SubRun = new EnumIgnoreContext("SubRun");
		/**  */
		public static final EnumIgnoreContext TileID = new EnumIgnoreContext("TileID");
		/**  */
		public static final EnumIgnoreContext WebName = new EnumIgnoreContext("WebName");
		/**  */
		public static final EnumIgnoreContext WebProduct = new EnumIgnoreContext("WebProduct");
		/**  */
		public static final EnumIgnoreContext WebSetup = new EnumIgnoreContext("WebSetup");
	}

	/**
	 * Enumeration strings for SheetSides
	 */

	public enum ESheetSides
	{
		Front, Back, FrontBack, BackFront;

		public static ESheetSides getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ESheetSides.class, val, null);
		}
	}

	/**
	 * Enumeration strings for SheetSides
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSheetSides extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSheetSides(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSheetSides getEnum(String enumName)
		{
			return (EnumSheetSides) getEnum(EnumSheetSides.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSheetSides getEnum(int enumValue)
		{
			return (EnumSheetSides) getEnum(EnumSheetSides.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSheetSides.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSheetSides.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSheetSides.class);
		}

		/**  */
		public static final EnumSheetSides Front = new EnumSheetSides("Front");
		/**  */
		public static final EnumSheetSides Back = new EnumSheetSides("Back");
		/**  */
		public static final EnumSheetSides FrontBack = new EnumSheetSides("FrontBack");
		/**  */
		public static final EnumSheetSides BackFront = new EnumSheetSides("BackFront");
	}

	/*
	 * ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IsPage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IsPage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIsPage(boolean value)
	{
		setAttribute(AttributeName.ISPAGE, value, null);
	}

	/**
	 * (18) get boolean attribute IsPage
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIsPage()
	{
		return getBoolAttribute(AttributeName.ISPAGE, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PageCopies
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PageCopies
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPageCopies(int value)
	{
		setAttribute(AttributeName.PAGECOPIES, value, null);
	}

	/**
	 * (15) get int attribute PageCopies
	 *
	 * @return int the value of the attribute
	 */
	public int getPageCopies()
	{
		return getIntAttribute(AttributeName.PAGECOPIES, null, 1);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SetCopies
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SetCopies
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSetCopies(int value)
	{
		setAttribute(AttributeName.SETCOPIES, value, null);
	}

	/**
	 * (15) get int attribute SetCopies
	 *
	 * @return int the value of the attribute
	 */
	public int getSetCopies()
	{
		return getIntAttribute(AttributeName.SETCOPIES, null, 1);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Automation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Automation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setAutomation(EAutomation enumVar)
	{
		setAttribute(AttributeName.AUTOMATION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Automation
	 *
	 * @return the value of the attribute
	 */
	public EAutomation getEAutomation()
	{
		return EAutomation.getEnum(getAttribute(AttributeName.AUTOMATION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Automation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Automation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetAutomation(EAutomation) based on java.lang.enum instead
	 */
	@Deprecated
	public void setAutomation(EnumAutomation enumVar)
	{
		setAttribute(AttributeName.AUTOMATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Automation
	 *
	 * @return the value of the attribute
	 * @deprecated use EAutomation GetEAutomation() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumAutomation getAutomation()
	{
		return EnumAutomation.getEnum(getAttribute(AttributeName.AUTOMATION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ComponentGranularity
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ComponentGranularity
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setComponentGranularity(EComponentGranularity enumVar)
	{
		setAttribute(AttributeName.COMPONENTGRANULARITY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ComponentGranularity
	 *
	 * @return the value of the attribute
	 */
	public EComponentGranularity getEComponentGranularity()
	{
		return EComponentGranularity.getEnum(getAttribute(AttributeName.COMPONENTGRANULARITY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ComponentGranularity
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ComponentGranularity
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetComponentGranularity(EComponentGranularity) based on java.lang.enum instead
	 */
	@Deprecated
	public void setComponentGranularity(EnumComponentGranularity enumVar)
	{
		setAttribute(AttributeName.COMPONENTGRANULARITY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ComponentGranularity
	 *
	 * @return the value of the attribute
	 * @deprecated use EComponentGranularity GetEComponentGranularity() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumComponentGranularity getComponentGranularity()
	{
		return EnumComponentGranularity.getEnum(getAttribute(AttributeName.COMPONENTGRANULARITY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Directory
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Directory
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDirectory(String value)
	{
		setAttribute(AttributeName.DIRECTORY, value, null);
	}

	/**
	 * (23) get String attribute Directory
	 *
	 * @return the value of the attribute
	 */
	public String getDirectory()
	{
		return getAttribute(AttributeName.DIRECTORY, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DocNames
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DocNames
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDocNames(JDFNameRangeList value)
	{
		setAttribute(AttributeName.DOCNAMES, value, null);
	}

	/**
	 * (20) get JDFNameRangeList attribute DocNames
	 *
	 * @return JDFNameRangeList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFNameRangeList
	 */
	public JDFNameRangeList getDocNames()
	{
		String strAttrName = getAttribute(AttributeName.DOCNAMES, null, null);
		JDFNameRangeList nPlaceHolder = JDFNameRangeList.createNameRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Docs
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Docs
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDocs(JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.DOCS, value, null);
	}

	/**
	 * (20) get JDFIntegerRangeList attribute Docs
	 *
	 * @return JDFIntegerRangeList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFIntegerRangeList
	 */
	public JDFIntegerRangeList getDocs()
	{
		String strAttrName = getAttribute(AttributeName.DOCS, null, null);
		JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DocPages
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DocPages
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDocPages(JDFIntegerList value)
	{
		setAttribute(AttributeName.DOCPAGES, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute DocPages
	 *
	 * @return JDFIntegerList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getDocPages()
	{
		String strAttrName = getAttribute(AttributeName.DOCPAGES, null, null);
		JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute EndOfBundleItem
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EndOfBundleItem
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEndOfBundleItem(boolean value)
	{
		setAttribute(AttributeName.ENDOFBUNDLEITEM, value, null);
	}

	/**
	 * (18) get boolean attribute EndOfBundleItem
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getEndOfBundleItem()
	{
		return getBoolAttribute(AttributeName.ENDOFBUNDLEITEM, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute EndOfDocument
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EndOfDocument
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEndOfDocument(boolean value)
	{
		setAttribute(AttributeName.ENDOFDOCUMENT, value, null);
	}

	/**
	 * (18) get boolean attribute EndOfDocument
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getEndOfDocument()
	{
		return getBoolAttribute(AttributeName.ENDOFDOCUMENT, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute EndOfSet
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EndOfSet
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEndOfSet(boolean value)
	{
		setAttribute(AttributeName.ENDOFSET, value, null);
	}

	/**
	 * (18) get boolean attribute EndOfSet
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getEndOfSet()
	{
		return getBoolAttribute(AttributeName.ENDOFSET, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FinishedPages
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FinishedPages
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFinishedPages(int value)
	{
		setAttribute(AttributeName.FINISHEDPAGES, value, null);
	}

	/**
	 * (15) get int attribute FinishedPages
	 *
	 * @return int the value of the attribute
	 */
	public int getFinishedPages()
	{
		return getIntAttribute(AttributeName.FINISHEDPAGES, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FirstPage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FirstPage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFirstPage(int value)
	{
		setAttribute(AttributeName.FIRSTPAGE, value, null);
	}

	/**
	 * (15) get int attribute FirstPage
	 *
	 * @return int the value of the attribute
	 */
	public int getFirstPage()
	{
		return getIntAttribute(AttributeName.FIRSTPAGE, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreContext
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute IgnoreContext
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setIgnoreContext(EIgnoreContext enumVar)
	{
		setAttribute(AttributeName.IGNORECONTEXT, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute IgnoreContext
	 *
	 * @return the value of the attribute
	 */
	public EIgnoreContext getEIgnoreContext()
	{
		return EIgnoreContext.getEnum(getAttribute(AttributeName.IGNORECONTEXT, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreContext
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute IgnoreContext
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetIgnoreContext(EIgnoreContext) based on java.lang.enum instead
	 */
	@Deprecated
	public void setIgnoreContext(EnumIgnoreContext enumVar)
	{
		setAttribute(AttributeName.IGNORECONTEXT, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute IgnoreContext
	 *
	 * @return the value of the attribute
	 * @deprecated use EIgnoreContext GetEIgnoreContext() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumIgnoreContext getIgnoreContext()
	{
		return EnumIgnoreContext.getEnum(getAttribute(AttributeName.IGNORECONTEXT, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute LogicalPage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LogicalPage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLogicalPage(int value)
	{
		setAttribute(AttributeName.LOGICALPAGE, value, null);
	}

	/**
	 * (15) get int attribute LogicalPage
	 *
	 * @return int the value of the attribute
	 */
	public int getLogicalPage()
	{
		return getIntAttribute(AttributeName.LOGICALPAGE, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NDoc
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NDoc
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNDoc(int value)
	{
		setAttribute(AttributeName.NDOC, value, null);
	}

	/**
	 * (15) get int attribute NDoc
	 *
	 * @return int the value of the attribute
	 */
	public int getNDoc()
	{
		return getIntAttribute(AttributeName.NDOC, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NPage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NPage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNPage(int value)
	{
		setAttribute(AttributeName.NPAGE, value, null);
	}

	/**
	 * (15) get int attribute NPage
	 *
	 * @return int the value of the attribute
	 */
	public int getNPage()
	{
		return getIntAttribute(AttributeName.NPAGE, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NSet
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NSet
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNSet(int value)
	{
		setAttribute(AttributeName.NSET, value, null);
	}

	/**
	 * (15) get int attribute NSet
	 *
	 * @return int the value of the attribute
	 */
	public int getNSet()
	{
		return getIntAttribute(AttributeName.NSET, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PageListIndex
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PageListIndex
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPageListIndex(JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.PAGELISTINDEX, value, null);
	}

	/**
	 * (20) get JDFIntegerRangeList attribute PageListIndex
	 *
	 * @return JDFIntegerRangeList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFIntegerRangeList
	 */
	public JDFIntegerRangeList getPageListIndex()
	{
		String strAttrName = getAttribute(AttributeName.PAGELISTINDEX, null, null);
		JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PageNames
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PageNames
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPageNames(JDFNameRangeList value)
	{
		setAttribute(AttributeName.PAGENAMES, value, null);
	}

	/**
	 * (20) get JDFNameRangeList attribute PageNames
	 *
	 * @return JDFNameRangeList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFNameRangeList
	 */
	public JDFNameRangeList getPageNames()
	{
		String strAttrName = getAttribute(AttributeName.PAGENAMES, null, null);
		JDFNameRangeList nPlaceHolder = JDFNameRangeList.createNameRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Pages
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Pages
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPages(JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.PAGES, value, null);
	}

	/**
	 * (20) get JDFIntegerRangeList attribute Pages
	 *
	 * @return JDFIntegerRangeList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFIntegerRangeList
	 */
	public JDFIntegerRangeList getPages()
	{
		String strAttrName = getAttribute(AttributeName.PAGES, null, null);
		JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute RunTag
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RunTag
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRunTag(String value)
	{
		setAttribute(AttributeName.RUNTAG, value, null);
	}

	/**
	 * (23) get String attribute RunTag
	 *
	 * @return the value of the attribute
	 */
	public String getRunTag()
	{
		return getAttribute(AttributeName.RUNTAG, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SetNames
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SetNames
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSetNames(JDFNameRangeList value)
	{
		setAttribute(AttributeName.SETNAMES, value, null);
	}

	/**
	 * (20) get JDFNameRangeList attribute SetNames
	 *
	 * @return JDFNameRangeList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFNameRangeList
	 */
	public JDFNameRangeList getSetNames()
	{
		String strAttrName = getAttribute(AttributeName.SETNAMES, null, null);
		JDFNameRangeList nPlaceHolder = JDFNameRangeList.createNameRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Sets
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Sets
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSets(JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.SETS, value, null);
	}

	/**
	 * (20) get JDFIntegerRangeList attribute Sets
	 *
	 * @return JDFIntegerRangeList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFIntegerRangeList
	 */
	public JDFIntegerRangeList getSets()
	{
		String strAttrName = getAttribute(AttributeName.SETS, null, null);
		JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SheetSides
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SheetSides
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSheetSides(ESheetSides enumVar)
	{
		setAttribute(AttributeName.SHEETSIDES, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute SheetSides
	 *
	 * @return the value of the attribute
	 */
	public ESheetSides getESheetSides()
	{
		return ESheetSides.getEnum(getAttribute(AttributeName.SHEETSIDES, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SheetSides
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SheetSides
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetSheetSides(ESheetSides) based on java.lang.enum instead
	 */
	@Deprecated
	public void setSheetSides(EnumSheetSides enumVar)
	{
		setAttribute(AttributeName.SHEETSIDES, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute SheetSides
	 *
	 * @return the value of the attribute
	 * @deprecated use ESheetSides GetESheetSides() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumSheetSides getSheetSides()
	{
		return EnumSheetSides.getEnum(getAttribute(AttributeName.SHEETSIDES, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SkipPage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SkipPage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSkipPage(int value)
	{
		setAttribute(AttributeName.SKIPPAGE, value, null);
	}

	/**
	 * (15) get int attribute SkipPage
	 *
	 * @return int the value of the attribute
	 */
	public int getSkipPage()
	{
		return getIntAttribute(AttributeName.SKIPPAGE, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Sorted
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Sorted
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSorted(boolean value)
	{
		setAttribute(AttributeName.SORTED, value, null);
	}

	/**
	 * (18) get boolean attribute Sorted
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getSorted()
	{
		return getBoolAttribute(AttributeName.SORTED, null, false);
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element ByteMap
	 *
	 * @return JDFByteMap the element
	 */
	public JDFByteMap getByteMap()
	{
		return (JDFByteMap) getElement(ElementName.BYTEMAP, null, 0);
	}

	/**
	 * (25) getCreateByteMap
	 * 
	 * @return JDFByteMap the element
	 */
	public JDFByteMap getCreateByteMap()
	{
		return (JDFByteMap) getCreateElement_JDFElement(ElementName.BYTEMAP, null, 0);
	}

	/**
	 * (29) append element ByteMap
	 *
	 * @return JDFByteMap the element
	 * @ if the element already exists
	 */
	public JDFByteMap appendByteMap()
	{
		return (JDFByteMap) appendElementN(ElementName.BYTEMAP, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refByteMap(JDFByteMap refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element DynamicInput
	 *
	 * @return JDFDynamicInput the element
	 */
	public JDFDynamicInput getDynamicInput()
	{
		return (JDFDynamicInput) getElement(ElementName.DYNAMICINPUT, null, 0);
	}

	/**
	 * (25) getCreateDynamicInput
	 * 
	 * @return JDFDynamicInput the element
	 */
	public JDFDynamicInput getCreateDynamicInput()
	{
		return (JDFDynamicInput) getCreateElement_JDFElement(ElementName.DYNAMICINPUT, null, 0);
	}

	/**
	 * (26) getCreateDynamicInput
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDynamicInput the element
	 */
	public JDFDynamicInput getCreateDynamicInput(int iSkip)
	{
		return (JDFDynamicInput) getCreateElement_JDFElement(ElementName.DYNAMICINPUT, null, iSkip);
	}

	/**
	 * (27) const get element DynamicInput
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDynamicInput the element
	 *         default is getDynamicInput(0)
	 */
	public JDFDynamicInput getDynamicInput(int iSkip)
	{
		return (JDFDynamicInput) getElement(ElementName.DYNAMICINPUT, null, iSkip);
	}

	/**
	 * Get all DynamicInput from the current element
	 * 
	 * @return Collection<JDFDynamicInput>, null if none are available
	 */
	public Collection<JDFDynamicInput> getAllDynamicInput()
	{
		return getChildArrayByClass(JDFDynamicInput.class, false, 0);
	}

	/**
	 * (30) append element DynamicInput
	 *
	 * @return JDFDynamicInput the element
	 */
	public JDFDynamicInput appendDynamicInput()
	{
		return (JDFDynamicInput) appendElement(ElementName.DYNAMICINPUT, null);
	}

	/**
	 * (24) const get element MetadataMap
	 *
	 * @return JDFMetadataMap the element
	 */
	public JDFMetadataMap getMetadataMap()
	{
		return (JDFMetadataMap) getElement(ElementName.METADATAMAP, null, 0);
	}

	/**
	 * (25) getCreateMetadataMap
	 * 
	 * @return JDFMetadataMap the element
	 */
	public JDFMetadataMap getCreateMetadataMap()
	{
		return (JDFMetadataMap) getCreateElement_JDFElement(ElementName.METADATAMAP, null, 0);
	}

	/**
	 * (26) getCreateMetadataMap
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFMetadataMap the element
	 */
	public JDFMetadataMap getCreateMetadataMap(int iSkip)
	{
		return (JDFMetadataMap) getCreateElement_JDFElement(ElementName.METADATAMAP, null, iSkip);
	}

	/**
	 * (27) const get element MetadataMap
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFMetadataMap the element
	 *         default is getMetadataMap(0)
	 */
	public JDFMetadataMap getMetadataMap(int iSkip)
	{
		return (JDFMetadataMap) getElement(ElementName.METADATAMAP, null, iSkip);
	}

	/**
	 * Get all MetadataMap from the current element
	 * 
	 * @return Collection<JDFMetadataMap>, null if none are available
	 */
	public Collection<JDFMetadataMap> getAllMetadataMap()
	{
		return getChildArrayByClass(JDFMetadataMap.class, false, 0);
	}

	/**
	 * (30) append element MetadataMap
	 *
	 * @return JDFMetadataMap the element
	 */
	public JDFMetadataMap appendMetadataMap()
	{
		return (JDFMetadataMap) appendElement(ElementName.METADATAMAP, null);
	}

	/**
	 * (24) const get element InsertSheet
	 *
	 * @return JDFInsertSheet the element
	 */
	public JDFInsertSheet getInsertSheet()
	{
		return (JDFInsertSheet) getElement(ElementName.INSERTSHEET, null, 0);
	}

	/**
	 * (25) getCreateInsertSheet
	 * 
	 * @return JDFInsertSheet the element
	 */
	public JDFInsertSheet getCreateInsertSheet()
	{
		return (JDFInsertSheet) getCreateElement_JDFElement(ElementName.INSERTSHEET, null, 0);
	}

	/**
	 * (26) getCreateInsertSheet
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFInsertSheet the element
	 */
	public JDFInsertSheet getCreateInsertSheet(int iSkip)
	{
		return (JDFInsertSheet) getCreateElement_JDFElement(ElementName.INSERTSHEET, null, iSkip);
	}

	/**
	 * (27) const get element InsertSheet
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFInsertSheet the element
	 *         default is getInsertSheet(0)
	 */
	public JDFInsertSheet getInsertSheet(int iSkip)
	{
		return (JDFInsertSheet) getElement(ElementName.INSERTSHEET, null, iSkip);
	}

	/**
	 * Get all InsertSheet from the current element
	 * 
	 * @return Collection<JDFInsertSheet>, null if none are available
	 */
	public Collection<JDFInsertSheet> getAllInsertSheet()
	{
		return getChildArrayByClass(JDFInsertSheet.class, false, 0);
	}

	/**
	 * (30) append element InsertSheet
	 *
	 * @return JDFInsertSheet the element
	 */
	public JDFInsertSheet appendInsertSheet()
	{
		return (JDFInsertSheet) appendElement(ElementName.INSERTSHEET, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refInsertSheet(JDFInsertSheet refTarget)
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
	 * @return JDFLayoutElement the element
	 * @ if the element already exists
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
	 * (24) const get element InterpretedPDLData
	 *
	 * @return JDFInterpretedPDLData the element
	 */
	public JDFInterpretedPDLData getInterpretedPDLData()
	{
		return (JDFInterpretedPDLData) getElement(ElementName.INTERPRETEDPDLDATA, null, 0);
	}

	/**
	 * (25) getCreateInterpretedPDLData
	 * 
	 * @return JDFInterpretedPDLData the element
	 */
	public JDFInterpretedPDLData getCreateInterpretedPDLData()
	{
		return (JDFInterpretedPDLData) getCreateElement_JDFElement(ElementName.INTERPRETEDPDLDATA, null, 0);
	}

	/**
	 * (29) append element InterpretedPDLData
	 *
	 * @return JDFInterpretedPDLData the element
	 * @ if the element already exists
	 */
	public JDFInterpretedPDLData appendInterpretedPDLData()
	{
		return (JDFInterpretedPDLData) appendElementN(ElementName.INTERPRETEDPDLDATA, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refInterpretedPDLData(JDFInterpretedPDLData refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Disposition
	 *
	 * @return JDFDisposition the element
	 */
	public JDFDisposition getDisposition()
	{
		return (JDFDisposition) getElement(ElementName.DISPOSITION, null, 0);
	}

	/**
	 * (25) getCreateDisposition
	 * 
	 * @return JDFDisposition the element
	 */
	public JDFDisposition getCreateDisposition()
	{
		return (JDFDisposition) getCreateElement_JDFElement(ElementName.DISPOSITION, null, 0);
	}

	/**
	 * (29) append element Disposition
	 *
	 * @return JDFDisposition the element
	 * @ if the element already exists
	 */
	public JDFDisposition appendDisposition()
	{
		return (JDFDisposition) appendElementN(ElementName.DISPOSITION, 1, null);
	}

	/**
	 * (24) const get element PageList
	 *
	 * @return JDFPageList the element
	 */
	public JDFPageList getPageList()
	{
		return (JDFPageList) getElement(ElementName.PAGELIST, null, 0);
	}

	/**
	 * (25) getCreatePageList
	 * 
	 * @return JDFPageList the element
	 */
	public JDFPageList getCreatePageList()
	{
		return (JDFPageList) getCreateElement_JDFElement(ElementName.PAGELIST, null, 0);
	}

	/**
	 * (29) append element PageList
	 *
	 * @return JDFPageList the element
	 * @ if the element already exists
	 */
	public JDFPageList appendPageList()
	{
		return (JDFPageList) appendElementN(ElementName.PAGELIST, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refPageList(JDFPageList refTarget)
	{
		refElement(refTarget);
	}

}
