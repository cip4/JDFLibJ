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
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.postpress.JDFSheet;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoInsertSheet : public JDFResource
 */

public abstract class JDFAutoInsertSheet extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.SHEETTYPE, 0x2222222221l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumSheetType.class, 0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.SHEETUSAGE, 0x2222222221l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumSheetUsage.class, 0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.INCLUDEINBUNDLEITEM, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumIncludeInBundleItem.class, 0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ISWASTE, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.MARKLIST, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.SHEETFORMAT, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.USAGE, 0x4444444443l, AttributeInfo.EnumAttributeType.Any, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.RUNLIST, 0x6666666666l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.LAYOUT, 0x6666666111l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.SHEET, 0x7777777666l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoInsertSheet
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoInsertSheet(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoInsertSheet
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoInsertSheet(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoInsertSheet
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoInsertSheet(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
	 * Enumeration strings for numSheetType
	 */

	public enum EnumSheetType
	{
		AccountingSheet, ErrorSheet, FillSheet, InsertSheet, JobSheet, SeparatorSheet;

		public static EnumSheetType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSheetType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numSheetUsage
	 */

	public enum EnumSheetUsage
	{
		FillForceBack, FillForceFront, FillSheet, FillSignature, FillSurface, Header, Interleaved, InterleavedBefore, OnError, Slip, SlipCopy, Trailer;

		public static EnumSheetUsage getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSheetUsage.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numIncludeInBundleItem
	 */

	public enum EnumIncludeInBundleItem
	{
		After, Before, None, New;

		public static EnumIncludeInBundleItem getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumIncludeInBundleItem.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SheetType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SheetType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSheetType(final EnumSheetType enumVar)
	{
		setAttribute(AttributeName.SHEETTYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute SheetType
	 *
	 * @return the value of the attribute
	 */
	public EnumSheetType getSheetType()
	{
		return EnumSheetType.getEnum(getAttribute(AttributeName.SHEETTYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SheetUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SheetUsage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSheetUsage(final EnumSheetUsage enumVar)
	{
		setAttribute(AttributeName.SHEETUSAGE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute SheetUsage
	 *
	 * @return the value of the attribute
	 */
	public EnumSheetUsage getSheetUsage()
	{
		return EnumSheetUsage.getEnum(getAttribute(AttributeName.SHEETUSAGE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IncludeInBundleItem
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute IncludeInBundleItem
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setIncludeInBundleItem(final EnumIncludeInBundleItem enumVar)
	{
		setAttribute(AttributeName.INCLUDEINBUNDLEITEM, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute IncludeInBundleItem
	 *
	 * @return the value of the attribute
	 */
	public EnumIncludeInBundleItem getIncludeInBundleItem()
	{
		return EnumIncludeInBundleItem.getEnum(getAttribute(AttributeName.INCLUDEINBUNDLEITEM, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IsWaste
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IsWaste
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIsWaste(final boolean value)
	{
		setAttribute(AttributeName.ISWASTE, value, null);
	}

	/**
	 * (18) get boolean attribute IsWaste
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIsWaste()
	{
		return getBoolAttribute(AttributeName.ISWASTE, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MarkList
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MarkList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMarkList(final VString value)
	{
		setAttribute(AttributeName.MARKLIST, value, null);
	}

	/**
	 * (21) get VString attribute MarkList
	 *
	 * @return VString the value of the attribute
	 */
	public VString getMarkList()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.MARKLIST, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SheetFormat
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SheetFormat
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSheetFormat(final String value)
	{
		setAttribute(AttributeName.SHEETFORMAT, value, null);
	}

	/**
	 * (23) get String attribute SheetFormat
	 *
	 * @return the value of the attribute
	 */
	public String getSheetFormat()
	{
		return getAttribute(AttributeName.SHEETFORMAT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Usage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Usage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setUsage(final EnumSheetUsage enumVar)
	{
		setAttribute(AttributeName.USAGE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Usage
	 *
	 * @return the value of the attribute
	 */
	public EnumSheetUsage getUsage()
	{
		return EnumSheetUsage.getEnum(getAttribute(AttributeName.USAGE, null, null));
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element RunList
	 *
	 * @return JDFRunList the element
	 */
	public JDFRunList getRunList()
	{
		return (JDFRunList) getElement(ElementName.RUNLIST, null, 0);
	}

	/**
	 * (25) getCreateRunList
	 * 
	 * @return JDFRunList the element
	 */
	public JDFRunList getCreateRunList()
	{
		return (JDFRunList) getCreateElement_JDFElement(ElementName.RUNLIST, null, 0);
	}

	/**
	 * (29) append element RunList
	 *
	 * @return JDFRunList the element
	 * @ if the element already exists
	 */
	public JDFRunList appendRunList()
	{
		return (JDFRunList) appendElementN(ElementName.RUNLIST, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refRunList(final JDFRunList refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Layout
	 *
	 * @return JDFLayout the element
	 */
	public JDFLayout getLayout()
	{
		return (JDFLayout) getElement(ElementName.LAYOUT, null, 0);
	}

	/**
	 * (25) getCreateLayout
	 * 
	 * @return JDFLayout the element
	 */
	public JDFLayout getCreateLayout()
	{
		return (JDFLayout) getCreateElement_JDFElement(ElementName.LAYOUT, null, 0);
	}

	/**
	 * (29) append element Layout
	 *
	 * @return JDFLayout the element
	 * @ if the element already exists
	 */
	public JDFLayout appendLayout()
	{
		return (JDFLayout) appendElementN(ElementName.LAYOUT, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refLayout(final JDFLayout refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Sheet
	 *
	 * @return JDFSheet the element
	 */
	public JDFSheet getSheet()
	{
		return (JDFSheet) getElement(ElementName.SHEET, null, 0);
	}

	/**
	 * (25) getCreateSheet
	 * 
	 * @return JDFSheet the element
	 */
	public JDFSheet getCreateSheet()
	{
		return (JDFSheet) getCreateElement_JDFElement(ElementName.SHEET, null, 0);
	}

	/**
	 * (29) append element Sheet
	 *
	 * @return JDFSheet the element
	 * @ if the element already exists
	 */
	public JDFSheet appendSheet()
	{
		return (JDFSheet) appendElementN(ElementName.SHEET, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refSheet(final JDFSheet refTarget)
	{
		refElement(refTarget);
	}

}
