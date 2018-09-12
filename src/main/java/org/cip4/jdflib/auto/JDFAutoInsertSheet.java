/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.postpress.JDFSheet;

/**
 *****************************************************************************
 * class JDFAutoInsertSheet : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoInsertSheet extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.SHEETTYPE, 0x22222221, AttributeInfo.EnumAttributeType.enumeration, EnumSheetType.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.SHEETUSAGE, 0x22222221, AttributeInfo.EnumAttributeType.enumeration, EnumSheetUsage.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.INCLUDEINBUNDLEITEM, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumIncludeInBundleItem.getEnum(0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ISWASTE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.MARKLIST, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.SHEETFORMAT, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.USAGE, 0x44444443, AttributeInfo.EnumAttributeType.Any, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.RUNLIST, 0x66666666);
		elemInfoTable[1] = new ElemInfoTable(ElementName.LAYOUT, 0x66666111);
		elemInfoTable[2] = new ElemInfoTable(ElementName.SHEET, 0x77777666);
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
	protected JDFAutoInsertSheet(CoreDocumentImpl myOwnerDocument, String qualifiedName)
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
	protected JDFAutoInsertSheet(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
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
	protected JDFAutoInsertSheet(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoInsertSheet[  --> " + super.toString() + " ]";
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
	 * Enumeration strings for SheetType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSheetType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSheetType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSheetType getEnum(String enumName)
		{
			return (EnumSheetType) getEnum(EnumSheetType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSheetType getEnum(int enumValue)
		{
			return (EnumSheetType) getEnum(EnumSheetType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSheetType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSheetType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSheetType.class);
		}

		/**  */
		public static final EnumSheetType AccountingSheet = new EnumSheetType("AccountingSheet");
		/**  */
		public static final EnumSheetType ErrorSheet = new EnumSheetType("ErrorSheet");
		/**  */
		public static final EnumSheetType FillSheet = new EnumSheetType("FillSheet");
		/**  */
		public static final EnumSheetType InsertSheet = new EnumSheetType("InsertSheet");
		/**  */
		public static final EnumSheetType JobSheet = new EnumSheetType("JobSheet");
		/**  */
		public static final EnumSheetType SeparatorSheet = new EnumSheetType("SeparatorSheet");
	}

	/**
	 * Enumeration strings for SheetUsage
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSheetUsage extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSheetUsage(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSheetUsage getEnum(String enumName)
		{
			return (EnumSheetUsage) getEnum(EnumSheetUsage.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSheetUsage getEnum(int enumValue)
		{
			return (EnumSheetUsage) getEnum(EnumSheetUsage.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSheetUsage.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSheetUsage.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSheetUsage.class);
		}

		/**  */
		public static final EnumSheetUsage FillForceBack = new EnumSheetUsage("FillForceBack");
		/**  */
		public static final EnumSheetUsage FillForceFront = new EnumSheetUsage("FillForceFront");
		/**  */
		public static final EnumSheetUsage FillSheet = new EnumSheetUsage("FillSheet");
		/**  */
		public static final EnumSheetUsage FillSignature = new EnumSheetUsage("FillSignature");
		/**  */
		public static final EnumSheetUsage FillSurface = new EnumSheetUsage("FillSurface");
		/**  */
		public static final EnumSheetUsage Header = new EnumSheetUsage("Header");
		/**  */
		public static final EnumSheetUsage Interleaved = new EnumSheetUsage("Interleaved");
		/**  */
		public static final EnumSheetUsage InterleavedBefore = new EnumSheetUsage("InterleavedBefore");
		/**  */
		public static final EnumSheetUsage OnError = new EnumSheetUsage("OnError");
		/**  */
		public static final EnumSheetUsage Slip = new EnumSheetUsage("Slip");
		/**  */
		public static final EnumSheetUsage SlipCopy = new EnumSheetUsage("SlipCopy");
		/**  */
		public static final EnumSheetUsage Trailer = new EnumSheetUsage("Trailer");
	}

	/**
	 * Enumeration strings for IncludeInBundleItem
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumIncludeInBundleItem extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumIncludeInBundleItem(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumIncludeInBundleItem getEnum(String enumName)
		{
			return (EnumIncludeInBundleItem) getEnum(EnumIncludeInBundleItem.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumIncludeInBundleItem getEnum(int enumValue)
		{
			return (EnumIncludeInBundleItem) getEnum(EnumIncludeInBundleItem.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumIncludeInBundleItem.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumIncludeInBundleItem.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumIncludeInBundleItem.class);
		}

		/**  */
		public static final EnumIncludeInBundleItem After = new EnumIncludeInBundleItem("After");
		/**  */
		public static final EnumIncludeInBundleItem Before = new EnumIncludeInBundleItem("Before");
		/**  */
		public static final EnumIncludeInBundleItem None = new EnumIncludeInBundleItem("None");
		/**  */
		public static final EnumIncludeInBundleItem New = new EnumIncludeInBundleItem("New");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SheetType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SheetType
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSheetType(EnumSheetType enumVar)
	{
		setAttribute(AttributeName.SHEETTYPE, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute SheetUsage ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SheetUsage
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSheetUsage(EnumSheetUsage enumVar)
	{
		setAttribute(AttributeName.SHEETUSAGE, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute IncludeInBundleItem ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute IncludeInBundleItem
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setIncludeInBundleItem(EnumIncludeInBundleItem enumVar)
	{
		setAttribute(AttributeName.INCLUDEINBUNDLEITEM, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute IsWaste ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IsWaste
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setIsWaste(boolean value)
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
	 * --------------------------------------------------------------------- Methods for Attribute MarkList ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MarkList
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setMarkList(VString value)
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
	 * --------------------------------------------------------------------- Methods for Attribute SheetFormat ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SheetFormat
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setSheetFormat(String value)
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
	 * --------------------------------------------------------------------- Methods for Attribute Usage ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Usage
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setUsage(EnumSheetUsage enumVar)
	{
		setAttribute(AttributeName.USAGE, enumVar == null ? null : enumVar.getName(), null);
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
	 * *********************************************************************** Element getter / setter ***********************************************************************
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
		return (JDFRunList) getCreateElement(ElementName.RUNLIST, null, 0);
	}

	/**
	 * (29) append element RunList
	 * 
	 * @return JDFRunList the element
	 * @throws JDFException if the element already exists
	 */
	public JDFRunList appendRunList() throws JDFException
	{
		return (JDFRunList) appendElementN(ElementName.RUNLIST, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refRunList(JDFRunList refTarget)
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
		return (JDFLayout) getCreateElement(ElementName.LAYOUT, null, 0);
	}

	/**
	 * (29) append element Layout
	 * 
	 * @return JDFLayout the element
	 * @throws JDFException if the element already exists
	 */
	public JDFLayout appendLayout() throws JDFException
	{
		return (JDFLayout) appendElementN(ElementName.LAYOUT, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refLayout(JDFLayout refTarget)
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
		return (JDFSheet) getCreateElement(ElementName.SHEET, null, 0);
	}

	/**
	 * (29) append element Sheet
	 * 
	 * @return JDFSheet the element
	 * @throws JDFException if the element already exists
	 */
	public JDFSheet appendSheet() throws JDFException
	{
		return (JDFSheet) appendElementN(ElementName.SHEET, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refSheet(JDFSheet refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
