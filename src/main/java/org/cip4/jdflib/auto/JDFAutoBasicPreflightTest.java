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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.process.JDFPreflightArgument;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoBasicPreflightTest : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoBasicPreflightTest extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CLASSES, 0x3333331111l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CLASSNAME, 0x3333331111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DEVNS, 0x3333333333l, AttributeInfo.EnumAttributeType.URI, null, "http://www.CIP4.org/JDFSchema_1_1");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.LISTTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumListType.getEnum(0), "SingleValue");
		atrInfoTable[4] = new AtrInfoTable(AttributeName.MAXOCCURS, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, "1");
		atrInfoTable[5] = new AtrInfoTable(AttributeName.MINOCCURS, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, "1");
		atrInfoTable[6] = new AtrInfoTable(AttributeName.NAME, 0x2222222222l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.PREFLIGHTARGUMENT, 0x6666666666l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoBasicPreflightTest
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoBasicPreflightTest(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBasicPreflightTest
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoBasicPreflightTest(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBasicPreflightTest
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoBasicPreflightTest(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for ListType
	 */

	public enum EListType
	{
		CompleteList, CompleteOrderedList, ContainedList, List, OrderedList, OrderedRangeList, Range, RangeList, SingleValue, Span, UniqueList, UniqueRangeList, UniqueOrderedList, UniqueOrderedRangeList;

		public static EListType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EListType.class, val, EListType.SingleValue);
		}
	}

	/**
	 * Enumeration strings for ListType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumListType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumListType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumListType getEnum(String enumName)
		{
			return (EnumListType) getEnum(EnumListType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumListType getEnum(int enumValue)
		{
			return (EnumListType) getEnum(EnumListType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumListType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumListType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumListType.class);
		}

		/**  */
		public static final EnumListType CompleteList = new EnumListType("CompleteList");
		/**  */
		public static final EnumListType CompleteOrderedList = new EnumListType("CompleteOrderedList");
		/**  */
		public static final EnumListType ContainedList = new EnumListType("ContainedList");
		/**  */
		public static final EnumListType List = new EnumListType("List");
		/**  */
		public static final EnumListType OrderedList = new EnumListType("OrderedList");
		/**  */
		public static final EnumListType OrderedRangeList = new EnumListType("OrderedRangeList");
		/**  */
		public static final EnumListType Range = new EnumListType("Range");
		/**  */
		public static final EnumListType RangeList = new EnumListType("RangeList");
		/**  */
		public static final EnumListType SingleValue = new EnumListType("SingleValue");
		/**  */
		public static final EnumListType Span = new EnumListType("Span");
		/**  */
		public static final EnumListType UniqueList = new EnumListType("UniqueList");
		/**  */
		public static final EnumListType UniqueRangeList = new EnumListType("UniqueRangeList");
		/**  */
		public static final EnumListType UniqueOrderedList = new EnumListType("UniqueOrderedList");
		/**  */
		public static final EnumListType UniqueOrderedRangeList = new EnumListType("UniqueOrderedRangeList");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Classes ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Classes
	 *
	 * @param value the value to set the attribute to
	 */
	public void setClasses(VString value)
	{
		setAttribute(AttributeName.CLASSES, value, null);
	}

	/**
	 * (21) get VString attribute Classes
	 *
	 * @return VString the value of the attribute
	 */
	public VString getClasses()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.CLASSES, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ClassName ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ClassName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setClassName(String value)
	{
		setAttribute(AttributeName.CLASSNAME, value, null);
	}

	/**
	 * (23) get String attribute ClassName
	 *
	 * @return the value of the attribute
	 */
	public String getClassName()
	{
		return getAttribute(AttributeName.CLASSNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DevNS ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DevNS
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDevNS(String value)
	{
		setAttribute(AttributeName.DEVNS, value, null);
	}

	/**
	 * (23) get String attribute DevNS
	 *
	 * @return the value of the attribute
	 */
	public String getDevNS()
	{
		return getAttribute(AttributeName.DEVNS, null, "http://www.CIP4.org/JDFSchema_1_1");
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ListType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ListType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setListType(EListType enumVar)
	{
		setAttribute(AttributeName.LISTTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ListType
	 *
	 * @return the value of the attribute
	 */
	public EListType getEListType()
	{
		return EListType.getEnum(getAttribute(AttributeName.LISTTYPE, null, "SingleValue"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ListType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ListType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setListType(EnumListType enumVar)
	{
		setAttribute(AttributeName.LISTTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ListType
	 *
	 * @return the value of the attribute
	 */
	public EnumListType getListType()
	{
		return EnumListType.getEnum(getAttribute(AttributeName.LISTTYPE, null, "SingleValue"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxOccurs ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxOccurs
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxOccurs(int value)
	{
		setAttribute(AttributeName.MAXOCCURS, value, null);
	}

	/**
	 * (15) get int attribute MaxOccurs
	 *
	 * @return int the value of the attribute
	 */
	public int getMaxOccurs()
	{
		return getIntAttribute(AttributeName.MAXOCCURS, null, 1);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinOccurs ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MinOccurs
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMinOccurs(int value)
	{
		setAttribute(AttributeName.MINOCCURS, value, null);
	}

	/**
	 * (15) get int attribute MinOccurs
	 *
	 * @return int the value of the attribute
	 */
	public int getMinOccurs()
	{
		return getIntAttribute(AttributeName.MINOCCURS, null, 1);
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
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element PreflightArgument
	 *
	 * @return JDFPreflightArgument the element
	 */
	public JDFPreflightArgument getPreflightArgument()
	{
		return (JDFPreflightArgument) getElement(ElementName.PREFLIGHTARGUMENT, null, 0);
	}

	/**
	 * (25) getCreatePreflightArgument
	 * 
	 * @return JDFPreflightArgument the element
	 */
	public JDFPreflightArgument getCreatePreflightArgument()
	{
		return (JDFPreflightArgument) getCreateElement_JDFElement(ElementName.PREFLIGHTARGUMENT, null, 0);
	}

	/**
	 * (29) append element PreflightArgument
	 *
	 * @return JDFPreflightArgument the element @ if the element already exists
	 */
	public JDFPreflightArgument appendPreflightArgument()
	{
		return (JDFPreflightArgument) appendElementN(ElementName.PREFLIGHTARGUMENT, 1, null);
	}

}
