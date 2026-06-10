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
		atrInfoTable[3] = new AtrInfoTable(AttributeName.AUTOMATION, 0x3333311111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumAutomation.class, 0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.COMPONENTGRANULARITY, 0x4444443311l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumComponentGranularity.class, 0), null);
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
				JavaEnumUtil.getEnum(EnumIgnoreContext.class, 0), null);
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
		atrInfoTable[25] = new AtrInfoTable(AttributeName.SHEETSIDES, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumSheetSides.class, 0), null);
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
	protected JDFAutoRunList(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
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
	protected JDFAutoRunList(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
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
	protected JDFAutoRunList(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
	 * Enumeration strings for numAutomation
	 */

	public enum EnumAutomation
	{
		Static, Dynamic;

		public static EnumAutomation getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumAutomation.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numComponentGranularity
	 */

	public enum EnumComponentGranularity
	{
		Page, Document, Set, All, BundleItem;

		public static EnumComponentGranularity getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumComponentGranularity.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numIgnoreContext
	 */

	public enum EnumIgnoreContext
	{
		BinderySignatureName, BinderySignaturePaginationIndex, BlockName, BundleItemIndex, CellIndex, Condition, DeliveryUnit0, DeliveryUnit1, DeliveryUnit2, DeliveryUnit3, DeliveryUnit4, DeliveryUnit5, DeliveryUnit6, DeliveryUnit7, DeliveryUnit8, DeliveryUnit9, DocCopies, DocIndex, DocRunIndex, DocSheetIndex, DocTags, Edition, EditionVersion, FountainNumber, ItemNames, LayerIDs, Location, Metadata0, Metadata1, Metadata2, Metadata3, Metadata4, Metadata5, Metadata6, Metadata7, Metadata8, Metadata9, Option, PageNumber, PageTags, PlateLayout, PartVersion, PreflightRule, ProductPart, PreviewType, RibbonName, Run, RunIndex, RunPage, RunTags, RunSet, SectionIndex, Separation, SetCopies, SetDocIndex, SetIndex, SetRunIndex, SetSheetIndex, SetTags, SheetIndex, SheetName, Side, SignatureName, StationName, SubRun, TileID, WebName, WebProduct, WebSetup;

		public static EnumIgnoreContext getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumIgnoreContext.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numSheetSides
	 */

	public enum EnumSheetSides
	{
		Front, Back, FrontBack, BackFront;

		public static EnumSheetSides getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSheetSides.class, val, null);
		}
	}/*
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
	public void setIsPage(final boolean value)
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
	public void setPageCopies(final int value)
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
	public void setSetCopies(final int value)
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
	public void setAutomation(final EnumAutomation enumVar)
	{
		setAttribute(AttributeName.AUTOMATION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Automation
	 *
	 * @return the value of the attribute
	 */
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
	public void setComponentGranularity(final EnumComponentGranularity enumVar)
	{
		setAttribute(AttributeName.COMPONENTGRANULARITY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute ComponentGranularity
	 *
	 * @return the value of the attribute
	 */
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
	public void setDirectory(final String value)
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
	public void setDocNames(final JDFNameRangeList value)
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
		final String strAttrName = getAttribute(AttributeName.DOCNAMES, null, null);
		final JDFNameRangeList nPlaceHolder = JDFNameRangeList.createNameRangeList(strAttrName);
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
	public void setDocs(final JDFIntegerRangeList value)
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
		final String strAttrName = getAttribute(AttributeName.DOCS, null, null);
		final JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
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
	public void setDocPages(final JDFIntegerList value)
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
		final String strAttrName = getAttribute(AttributeName.DOCPAGES, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
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
	public void setEndOfBundleItem(final boolean value)
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
	public void setEndOfDocument(final boolean value)
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
	public void setEndOfSet(final boolean value)
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
	public void setFinishedPages(final int value)
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
	public void setFirstPage(final int value)
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
	public void setIgnoreContext(final EnumIgnoreContext enumVar)
	{
		setAttribute(AttributeName.IGNORECONTEXT, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute IgnoreContext
	 *
	 * @return the value of the attribute
	 */
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
	public void setLogicalPage(final int value)
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
	public void setNDoc(final int value)
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
	public void setNPage(final int value)
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
	public void setNSet(final int value)
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
	public void setPageListIndex(final JDFIntegerRangeList value)
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
		final String strAttrName = getAttribute(AttributeName.PAGELISTINDEX, null, null);
		final JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
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
	public void setPageNames(final JDFNameRangeList value)
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
		final String strAttrName = getAttribute(AttributeName.PAGENAMES, null, null);
		final JDFNameRangeList nPlaceHolder = JDFNameRangeList.createNameRangeList(strAttrName);
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
	public void setPages(final JDFIntegerRangeList value)
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
		final String strAttrName = getAttribute(AttributeName.PAGES, null, null);
		final JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
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
	public void setRunTag(final String value)
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
	public void setSetNames(final JDFNameRangeList value)
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
		final String strAttrName = getAttribute(AttributeName.SETNAMES, null, null);
		final JDFNameRangeList nPlaceHolder = JDFNameRangeList.createNameRangeList(strAttrName);
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
	public void setSets(final JDFIntegerRangeList value)
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
		final String strAttrName = getAttribute(AttributeName.SETS, null, null);
		final JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
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
	public void setSheetSides(final EnumSheetSides enumVar)
	{
		setAttribute(AttributeName.SHEETSIDES, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute SheetSides
	 *
	 * @return the value of the attribute
	 */
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
	public void setSkipPage(final int value)
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
	public void setSorted(final boolean value)
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
	public void refByteMap(final JDFByteMap refTarget)
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
	public JDFDynamicInput getCreateDynamicInput(final int iSkip)
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
	public JDFDynamicInput getDynamicInput(final int iSkip)
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
	public JDFMetadataMap getCreateMetadataMap(final int iSkip)
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
	public JDFMetadataMap getMetadataMap(final int iSkip)
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
	public JDFInsertSheet getCreateInsertSheet(final int iSkip)
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
	public JDFInsertSheet getInsertSheet(final int iSkip)
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
	public void refInsertSheet(final JDFInsertSheet refTarget)
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
	public void refLayoutElement(final JDFLayoutElement refTarget)
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
	public void refInterpretedPDLData(final JDFInterpretedPDLData refTarget)
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
	public void refPageList(final JDFPageList refTarget)
	{
		refElement(refTarget);
	}

}
