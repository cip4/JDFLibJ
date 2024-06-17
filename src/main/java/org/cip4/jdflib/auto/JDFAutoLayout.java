/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFLayerList;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFPageCondition;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFSheetCondition;
import org.cip4.jdflib.resource.JDFSignature;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFInsertSheet;
import org.cip4.jdflib.resource.process.JDFLogicalStackParams;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFMediaSource;
import org.cip4.jdflib.resource.process.JDFTransferCurvePool;

/**
 *****************************************************************************
 * class JDFAutoLayout : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoLayout extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[17];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.AUTOMATED, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.LOCKORIGINS, 0x3333333111l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.MAXDOCORD, 0x4444443331l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.MAXSETORD, 0x4444443331l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ORDRESET, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration, EnumOrdReset.getEnum(0), "Continue");
		atrInfoTable[5] = new AtrInfoTable(AttributeName.SHEETCOUNTRESET, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration, EnumSheetCountReset.getEnum(0), "Continue");
		atrInfoTable[6] = new AtrInfoTable(AttributeName.NAME, 0x4444443331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.BASEORDRESET, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration, EnumBaseOrdReset.getEnum(0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.MAXCOLLECT, 0x3333331111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.MAXORD, 0x4444443333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.MINCOLLECT, 0x3333331111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.ORDSCONSUMED, 0x3333331111l, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.SHEETNAMEFORMAT, 0x3333331111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.SHEETNAMETEMPLATE, 0x3333331111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.SOURCEWORKSTYLE, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, EnumSourceWorkStyle.getEnum(0), null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.SURFACECONTENTSBOX, 0x3333333111l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.TEMPLATETYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumTemplateType.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[11];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.CONTENTOBJECT, 0x3333333333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.INSERTSHEET, 0x3333333333l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.LAYERLIST, 0x6666666661l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.LOGICALSTACKPARAMS, 0x6666661111l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.MARKOBJECT, 0x3333333333l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.MEDIA, 0x3333333331l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.MEDIASOURCE, 0x7777777776l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.PAGECONDITION, 0x3333331111l);
		elemInfoTable[8] = new ElemInfoTable(ElementName.SHEETCONDITION, 0x3333331111l);
		elemInfoTable[9] = new ElemInfoTable(ElementName.SIGNATURE, 0x4444444333l);
		elemInfoTable[10] = new ElemInfoTable(ElementName.TRANSFERCURVEPOOL, 0x6666666661l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoLayout
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoLayout(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLayout
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoLayout(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLayout
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoLayout(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for OrdReset
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumOrdReset extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumOrdReset(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumOrdReset getEnum(String enumName)
		{
			return (EnumOrdReset) getEnum(EnumOrdReset.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumOrdReset getEnum(int enumValue)
		{
			return (EnumOrdReset) getEnum(EnumOrdReset.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumOrdReset.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumOrdReset.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumOrdReset.class);
		}

		/**  */
		public static final EnumOrdReset Continue = new EnumOrdReset("Continue");
		/**  */
		public static final EnumOrdReset PagePool = new EnumOrdReset("PagePool");
		/**  */
		public static final EnumOrdReset PagePoolList = new EnumOrdReset("PagePoolList");
	}

	/**
	 * Enumeration strings for SheetCountReset
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSheetCountReset extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSheetCountReset(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSheetCountReset getEnum(String enumName)
		{
			return (EnumSheetCountReset) getEnum(EnumSheetCountReset.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSheetCountReset getEnum(int enumValue)
		{
			return (EnumSheetCountReset) getEnum(EnumSheetCountReset.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSheetCountReset.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSheetCountReset.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSheetCountReset.class);
		}

		/**  */
		public static final EnumSheetCountReset Continue = new EnumSheetCountReset("Continue");
		/**  */
		public static final EnumSheetCountReset PagePool = new EnumSheetCountReset("PagePool");
		/**  */
		public static final EnumSheetCountReset PagePoolList = new EnumSheetCountReset("PagePoolList");
	}

	/**
	 * Enumeration strings for BaseOrdReset
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumBaseOrdReset extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumBaseOrdReset(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumBaseOrdReset getEnum(String enumName)
		{
			return (EnumBaseOrdReset) getEnum(EnumBaseOrdReset.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumBaseOrdReset getEnum(int enumValue)
		{
			return (EnumBaseOrdReset) getEnum(EnumBaseOrdReset.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBaseOrdReset.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBaseOrdReset.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBaseOrdReset.class);
		}

		/**  */
		public static final EnumBaseOrdReset PagePool = new EnumBaseOrdReset("PagePool");
		/**  */
		public static final EnumBaseOrdReset PagePoolList = new EnumBaseOrdReset("PagePoolList");
	}

	/**
	 * Enumeration strings for SourceWorkStyle
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSourceWorkStyle extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSourceWorkStyle(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSourceWorkStyle getEnum(String enumName)
		{
			return (EnumSourceWorkStyle) getEnum(EnumSourceWorkStyle.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSourceWorkStyle getEnum(int enumValue)
		{
			return (EnumSourceWorkStyle) getEnum(EnumSourceWorkStyle.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSourceWorkStyle.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSourceWorkStyle.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSourceWorkStyle.class);
		}

		/**  */
		public static final EnumSourceWorkStyle Simplex = new EnumSourceWorkStyle("Simplex");
		/**  */
		public static final EnumSourceWorkStyle Perfecting = new EnumSourceWorkStyle("Perfecting");
		/**  */
		public static final EnumSourceWorkStyle WorkAndBack = new EnumSourceWorkStyle("WorkAndBack");
		/**  */
		public static final EnumSourceWorkStyle WorkAndTurn = new EnumSourceWorkStyle("WorkAndTurn");
		/**  */
		public static final EnumSourceWorkStyle WorkAndTumble = new EnumSourceWorkStyle("WorkAndTumble");
		/**  */
		public static final EnumSourceWorkStyle WorkAndTwist = new EnumSourceWorkStyle("WorkAndTwist");
	}

	/**
	 * Enumeration strings for TemplateType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumTemplateType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumTemplateType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumTemplateType getEnum(String enumName)
		{
			return (EnumTemplateType) getEnum(EnumTemplateType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumTemplateType getEnum(int enumValue)
		{
			return (EnumTemplateType) getEnum(EnumTemplateType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumTemplateType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumTemplateType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumTemplateType.class);
		}

		/**  */
		public static final EnumTemplateType Normal = new EnumTemplateType("Normal");
		/**  */
		public static final EnumTemplateType ConditionalSheets = new EnumTemplateType("ConditionalSheets");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Automated ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Automated
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAutomated(boolean value)
	{
		setAttribute(AttributeName.AUTOMATED, value, null);
	}

	/**
	 * (18) get boolean attribute Automated
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getAutomated()
	{
		return getBoolAttribute(AttributeName.AUTOMATED, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute LockOrigins ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LockOrigins
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLockOrigins(boolean value)
	{
		setAttribute(AttributeName.LOCKORIGINS, value, null);
	}

	/**
	 * (18) get boolean attribute LockOrigins
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getLockOrigins()
	{
		return getBoolAttribute(AttributeName.LOCKORIGINS, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxDocOrd ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxDocOrd
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxDocOrd(int value)
	{
		setAttribute(AttributeName.MAXDOCORD, value, null);
	}

	/**
	 * (15) get int attribute MaxDocOrd
	 *
	 * @return int the value of the attribute
	 */
	public int getMaxDocOrd()
	{
		return getIntAttribute(AttributeName.MAXDOCORD, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxSetOrd ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxSetOrd
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxSetOrd(int value)
	{
		setAttribute(AttributeName.MAXSETORD, value, null);
	}

	/**
	 * (15) get int attribute MaxSetOrd
	 *
	 * @return int the value of the attribute
	 */
	public int getMaxSetOrd()
	{
		return getIntAttribute(AttributeName.MAXSETORD, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OrdReset ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute OrdReset
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOrdReset(EnumOrdReset enumVar)
	{
		setAttribute(AttributeName.ORDRESET, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute OrdReset
	 *
	 * @return the value of the attribute
	 */
	public EnumOrdReset getOrdReset()
	{
		return EnumOrdReset.getEnum(getAttribute(AttributeName.ORDRESET, null, "Continue"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SheetCountReset
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SheetCountReset
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSheetCountReset(EnumSheetCountReset enumVar)
	{
		setAttribute(AttributeName.SHEETCOUNTRESET, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute SheetCountReset
	 *
	 * @return the value of the attribute
	 */
	public EnumSheetCountReset getSheetCountReset()
	{
		return EnumSheetCountReset.getEnum(getAttribute(AttributeName.SHEETCOUNTRESET, null, "Continue"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Name ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Name
	 *
	 * @param value the value to set the attribute to
	 */
	public void setName(String value)
	{
		setAttribute(AttributeName.NAME, value, null);
	}

	/**
	 * (23) get String attribute Name
	 *
	 * @return the value of the attribute
	 */
	public String getName()
	{
		return getAttribute(AttributeName.NAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BaseOrdReset
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BaseOrdReset
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBaseOrdReset(EnumBaseOrdReset enumVar)
	{
		setAttribute(AttributeName.BASEORDRESET, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute BaseOrdReset
	 *
	 * @return the value of the attribute
	 */
	public EnumBaseOrdReset getBaseOrdReset()
	{
		return EnumBaseOrdReset.getEnum(getAttribute(AttributeName.BASEORDRESET, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxCollect ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxCollect
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxCollect(int value)
	{
		setAttribute(AttributeName.MAXCOLLECT, value, null);
	}

	/**
	 * (15) get int attribute MaxCollect
	 *
	 * @return int the value of the attribute
	 */
	public int getMaxCollect()
	{
		return getIntAttribute(AttributeName.MAXCOLLECT, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxOrd ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxOrd
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxOrd(int value)
	{
		setAttribute(AttributeName.MAXORD, value, null);
	}

	/**
	 * (15) get int attribute MaxOrd
	 *
	 * @return int the value of the attribute
	 */
	public int getMaxOrd()
	{
		return getIntAttribute(AttributeName.MAXORD, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinCollect ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MinCollect
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMinCollect(int value)
	{
		setAttribute(AttributeName.MINCOLLECT, value, null);
	}

	/**
	 * (15) get int attribute MinCollect
	 *
	 * @return int the value of the attribute
	 */
	public int getMinCollect()
	{
		return getIntAttribute(AttributeName.MINCOLLECT, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OrdsConsumed
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OrdsConsumed
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOrdsConsumed(JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.ORDSCONSUMED, value, null);
	}

	/**
	 * (20) get JDFIntegerRangeList attribute OrdsConsumed
	 *
	 * @return JDFIntegerRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerRangeList
	 */
	public JDFIntegerRangeList getOrdsConsumed()
	{
		final String strAttrName = getAttribute(AttributeName.ORDSCONSUMED, null, null);
		final JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SheetNameFormat
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SheetNameFormat
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSheetNameFormat(String value)
	{
		setAttribute(AttributeName.SHEETNAMEFORMAT, value, null);
	}

	/**
	 * (23) get String attribute SheetNameFormat
	 *
	 * @return the value of the attribute
	 */
	public String getSheetNameFormat()
	{
		return getAttribute(AttributeName.SHEETNAMEFORMAT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SheetNameTemplate
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SheetNameTemplate
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSheetNameTemplate(String value)
	{
		setAttribute(AttributeName.SHEETNAMETEMPLATE, value, null);
	}

	/**
	 * (23) get String attribute SheetNameTemplate
	 *
	 * @return the value of the attribute
	 */
	public String getSheetNameTemplate()
	{
		return getAttribute(AttributeName.SHEETNAMETEMPLATE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SourceWorkStyle
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SourceWorkStyle
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSourceWorkStyle(EnumSourceWorkStyle enumVar)
	{
		setAttribute(AttributeName.SOURCEWORKSTYLE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute SourceWorkStyle
	 *
	 * @return the value of the attribute
	 */
	public EnumSourceWorkStyle getSourceWorkStyle()
	{
		return EnumSourceWorkStyle.getEnum(getAttribute(AttributeName.SOURCEWORKSTYLE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SurfaceContentsBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SurfaceContentsBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSurfaceContentsBox(JDFRectangle value)
	{
		setAttribute(AttributeName.SURFACECONTENTSBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute SurfaceContentsBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getSurfaceContentsBox()
	{
		final String strAttrName = getAttribute(AttributeName.SURFACECONTENTSBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TemplateType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute TemplateType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setTemplateType(EnumTemplateType enumVar)
	{
		setAttribute(AttributeName.TEMPLATETYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute TemplateType
	 *
	 * @return the value of the attribute
	 */
	public EnumTemplateType getTemplateType()
	{
		return EnumTemplateType.getEnum(getAttribute(AttributeName.TEMPLATETYPE, null, null));
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateContentObject
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFContentObject the element
	 */
	public JDFContentObject getCreateContentObject(int iSkip)
	{
		return (JDFContentObject) getCreateElement_JDFElement(ElementName.CONTENTOBJECT, null, iSkip);
	}

	/**
	 * (27) const get element ContentObject
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFContentObject the element default is getContentObject(0)
	 */
	public JDFContentObject getContentObject(int iSkip)
	{
		return (JDFContentObject) getElement(ElementName.CONTENTOBJECT, null, iSkip);
	}

	/**
	 * Get all ContentObject from the current element
	 * 
	 * @return Collection<JDFContentObject>, null if none are available
	 */
	public Collection<JDFContentObject> getAllContentObject()
	{
		return getChildArrayByClass(JDFContentObject.class, false, 0);
	}

	/**
	 * (30) append element ContentObject
	 *
	 * @return JDFContentObject the element
	 */
	public JDFContentObject appendContentObject()
	{
		return (JDFContentObject) appendElement(ElementName.CONTENTOBJECT, null);
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
	 * @return JDFInsertSheet the element default is getInsertSheet(0)
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
	 * (24) const get element LayerList
	 *
	 * @return JDFLayerList the element
	 */
	public JDFLayerList getLayerList()
	{
		return (JDFLayerList) getElement(ElementName.LAYERLIST, null, 0);
	}

	/**
	 * (25) getCreateLayerList
	 * 
	 * @return JDFLayerList the element
	 */
	public JDFLayerList getCreateLayerList()
	{
		return (JDFLayerList) getCreateElement_JDFElement(ElementName.LAYERLIST, null, 0);
	}

	/**
	 * (29) append element LayerList
	 *
	 * @return JDFLayerList the element @ if the element already exists
	 */
	public JDFLayerList appendLayerList()
	{
		return (JDFLayerList) appendElementN(ElementName.LAYERLIST, 1, null);
	}

	/**
	 * (24) const get element LogicalStackParams
	 *
	 * @return JDFLogicalStackParams the element
	 */
	public JDFLogicalStackParams getLogicalStackParams()
	{
		return (JDFLogicalStackParams) getElement(ElementName.LOGICALSTACKPARAMS, null, 0);
	}

	/**
	 * (25) getCreateLogicalStackParams
	 * 
	 * @return JDFLogicalStackParams the element
	 */
	public JDFLogicalStackParams getCreateLogicalStackParams()
	{
		return (JDFLogicalStackParams) getCreateElement_JDFElement(ElementName.LOGICALSTACKPARAMS, null, 0);
	}

	/**
	 * (29) append element LogicalStackParams
	 *
	 * @return JDFLogicalStackParams the element @ if the element already exists
	 */
	public JDFLogicalStackParams appendLogicalStackParams()
	{
		return (JDFLogicalStackParams) appendElementN(ElementName.LOGICALSTACKPARAMS, 1, null);
	}

	/**
	 * (26) getCreateMarkObject
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFMarkObject the element
	 */
	public JDFMarkObject getCreateMarkObject(int iSkip)
	{
		return (JDFMarkObject) getCreateElement_JDFElement(ElementName.MARKOBJECT, null, iSkip);
	}

	/**
	 * (27) const get element MarkObject
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFMarkObject the element default is getMarkObject(0)
	 */
	public JDFMarkObject getMarkObject(int iSkip)
	{
		return (JDFMarkObject) getElement(ElementName.MARKOBJECT, null, iSkip);
	}

	/**
	 * Get all MarkObject from the current element
	 * 
	 * @return Collection<JDFMarkObject>, null if none are available
	 */
	public Collection<JDFMarkObject> getAllMarkObject()
	{
		return getChildArrayByClass(JDFMarkObject.class, false, 0);
	}

	/**
	 * (30) append element MarkObject
	 *
	 * @return JDFMarkObject the element
	 */
	public JDFMarkObject appendMarkObject()
	{
		return (JDFMarkObject) appendElement(ElementName.MARKOBJECT, null);
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

	/**
	 * (26) getCreatePageCondition
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPageCondition the element
	 */
	public JDFPageCondition getCreatePageCondition(int iSkip)
	{
		return (JDFPageCondition) getCreateElement_JDFElement(ElementName.PAGECONDITION, null, iSkip);
	}

	/**
	 * (27) const get element PageCondition
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPageCondition the element default is getPageCondition(0)
	 */
	public JDFPageCondition getPageCondition(int iSkip)
	{
		return (JDFPageCondition) getElement(ElementName.PAGECONDITION, null, iSkip);
	}

	/**
	 * Get all PageCondition from the current element
	 * 
	 * @return Collection<JDFPageCondition>, null if none are available
	 */
	public Collection<JDFPageCondition> getAllPageCondition()
	{
		return getChildArrayByClass(JDFPageCondition.class, false, 0);
	}

	/**
	 * (30) append element PageCondition
	 *
	 * @return JDFPageCondition the element
	 */
	public JDFPageCondition appendPageCondition()
	{
		return (JDFPageCondition) appendElement(ElementName.PAGECONDITION, null);
	}

	/**
	 * (26) getCreateSheetCondition
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFSheetCondition the element
	 */
	public JDFSheetCondition getCreateSheetCondition(int iSkip)
	{
		return (JDFSheetCondition) getCreateElement_JDFElement(ElementName.SHEETCONDITION, null, iSkip);
	}

	/**
	 * (27) const get element SheetCondition
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFSheetCondition the element default is getSheetCondition(0)
	 */
	public JDFSheetCondition getSheetCondition(int iSkip)
	{
		return (JDFSheetCondition) getElement(ElementName.SHEETCONDITION, null, iSkip);
	}

	/**
	 * Get all SheetCondition from the current element
	 * 
	 * @return Collection<JDFSheetCondition>, null if none are available
	 */
	public Collection<JDFSheetCondition> getAllSheetCondition()
	{
		return getChildArrayByClass(JDFSheetCondition.class, false, 0);
	}

	/**
	 * (30) append element SheetCondition
	 *
	 * @return JDFSheetCondition the element
	 */
	public JDFSheetCondition appendSheetCondition()
	{
		return (JDFSheetCondition) appendElement(ElementName.SHEETCONDITION, null);
	}

	/**
	 * (26) getCreateSignature
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFSignature the element
	 */
	public JDFSignature getCreateSignature(int iSkip)
	{
		return (JDFSignature) getCreateElement_JDFElement(ElementName.SIGNATURE, null, iSkip);
	}

	/**
	 * (27) const get element Signature
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFSignature the element default is getSignature(0)
	 */
	public JDFSignature getSignature(int iSkip)
	{
		return (JDFSignature) getElement(ElementName.SIGNATURE, null, iSkip);
	}

	/**
	 * Get all Signature from the current element
	 * 
	 * @return Collection<JDFSignature>, null if none are available
	 */
	public Collection<JDFSignature> getAllSignature()
	{
		return getChildArrayByClass(JDFSignature.class, false, 0);
	}

	/**
	 * (30) append element Signature
	 *
	 * @return JDFSignature the element
	 */
	public JDFSignature appendSignature()
	{
		return (JDFSignature) appendElement(ElementName.SIGNATURE, null);
	}

	/**
	 * (24) const get element TransferCurvePool
	 *
	 * @return JDFTransferCurvePool the element
	 */
	public JDFTransferCurvePool getTransferCurvePool()
	{
		return (JDFTransferCurvePool) getElement(ElementName.TRANSFERCURVEPOOL, null, 0);
	}

	/**
	 * (25) getCreateTransferCurvePool
	 * 
	 * @return JDFTransferCurvePool the element
	 */
	public JDFTransferCurvePool getCreateTransferCurvePool()
	{
		return (JDFTransferCurvePool) getCreateElement_JDFElement(ElementName.TRANSFERCURVEPOOL, null, 0);
	}

	/**
	 * (29) append element TransferCurvePool
	 *
	 * @return JDFTransferCurvePool the element @ if the element already exists
	 */
	public JDFTransferCurvePool appendTransferCurvePool()
	{
		return (JDFTransferCurvePool) appendElementN(ElementName.TRANSFERCURVEPOOL, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refTransferCurvePool(JDFTransferCurvePool refTarget)
	{
		refElement(refTarget);
	}

}
