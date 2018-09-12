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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.resource.process.JDFExpr;

/**
 *****************************************************************************
 * class JDFAutoMetadataMap : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoMetadataMap extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CONTEXT, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumContext.getEnum(0), "PagePool");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DATATYPE, 0x22221111, AttributeInfo.EnumAttributeType.enumeration, EnumDataType.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.NAME, 0x22221111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.VALUEFORMAT, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.VALUETEMPLATE, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.EXPR, 0x33331111);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoMetadataMap
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoMetadataMap(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoMetadataMap
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoMetadataMap(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoMetadataMap
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoMetadataMap(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoMetadataMap[  --> " + super.toString() + " ]";
	}

	/**
	 * Enumeration strings for Context
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumContext extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumContext(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumContext getEnum(String enumName)
		{
			return (EnumContext) getEnum(EnumContext.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumContext getEnum(int enumValue)
		{
			return (EnumContext) getEnum(EnumContext.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumContext.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumContext.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumContext.class);
		}

		/**  */
		public static final EnumContext Set = new EnumContext("Set");
		/**  */
		public static final EnumContext Document = new EnumContext("Document");
		/**  */
		public static final EnumContext SubDoc0 = new EnumContext("SubDoc0");
		/**  */
		public static final EnumContext SubDoc1 = new EnumContext("SubDoc1");
		/**  */
		public static final EnumContext SubDoc2 = new EnumContext("SubDoc2");
		/**  */
		public static final EnumContext SubDoc3 = new EnumContext("SubDoc3");
		/**  */
		public static final EnumContext SubDoc4 = new EnumContext("SubDoc4");
		/**  */
		public static final EnumContext SubDoc5 = new EnumContext("SubDoc5");
		/**  */
		public static final EnumContext SubDoc6 = new EnumContext("SubDoc6");
		/**  */
		public static final EnumContext SubDoc7 = new EnumContext("SubDoc7");
		/**  */
		public static final EnumContext SubDoc8 = new EnumContext("SubDoc8");
		/**  */
		public static final EnumContext SubDoc9 = new EnumContext("SubDoc9");
		/**  */
		public static final EnumContext PagePool = new EnumContext("PagePool");
		/**  */
		public static final EnumContext Page = new EnumContext("Page");
		/**  */
		public static final EnumContext Object = new EnumContext("Object");
	}

	/**
	 * Enumeration strings for DataType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumDataType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumDataType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumDataType getEnum(String enumName)
		{
			return (EnumDataType) getEnum(EnumDataType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumDataType getEnum(int enumValue)
		{
			return (EnumDataType) getEnum(EnumDataType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDataType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDataType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDataType.class);
		}

		/**  */
		public static final EnumDataType string = new EnumDataType("string");
		/**  */
		public static final EnumDataType integer = new EnumDataType("integer");
		/**  */
		public static final EnumDataType double_ = new EnumDataType("double");
		/**  */
		public static final EnumDataType NMTOKEN = new EnumDataType("NMTOKEN");
		/**  */
		public static final EnumDataType boolean_ = new EnumDataType("boolean");
		/**  */
		public static final EnumDataType dateTime = new EnumDataType("dateTime");
		/**  */
		public static final EnumDataType duration = new EnumDataType("duration");
		/**  */
		public static final EnumDataType PartIDKeys = new EnumDataType("PartIDKeys");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Context ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Context
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setContext(EnumContext enumVar)
	{
		setAttribute(AttributeName.CONTEXT, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Context
	 * 
	 * @return the value of the attribute
	 */
	public EnumContext getContext()
	{
		return EnumContext.getEnum(getAttribute(AttributeName.CONTEXT, null, "PagePool"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DataType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DataType
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDataType(EnumDataType enumVar)
	{
		setAttribute(AttributeName.DATATYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute DataType
	 * 
	 * @return the value of the attribute
	 */
	public EnumDataType getDataType()
	{
		return EnumDataType.getEnum(getAttribute(AttributeName.DATATYPE, null, null));
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
	 * --------------------------------------------------------------------- Methods for Attribute ValueFormat ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ValueFormat
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setValueFormat(String value)
	{
		setAttribute(AttributeName.VALUEFORMAT, value, null);
	}

	/**
	 * (23) get String attribute ValueFormat
	 * 
	 * @return the value of the attribute
	 */
	public String getValueFormat()
	{
		return getAttribute(AttributeName.VALUEFORMAT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ValueTemplate ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ValueTemplate
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setValueTemplate(String value)
	{
		setAttribute(AttributeName.VALUETEMPLATE, value, null);
	}

	/**
	 * (23) get String attribute ValueTemplate
	 * 
	 * @return the value of the attribute
	 */
	public String getValueTemplate()
	{
		return getAttribute(AttributeName.VALUETEMPLATE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateExpr
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFExpr the element
	 */
	public JDFExpr getCreateExpr(int iSkip)
	{
		return (JDFExpr) getCreateElement_JDFElement(ElementName.EXPR, null, iSkip);
	}

	/**
	 * (27) const get element Expr
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFExpr the element default is getExpr(0)
	 */
	public JDFExpr getExpr(int iSkip)
	{
		return (JDFExpr) getElement(ElementName.EXPR, null, iSkip);
	}

	/**
	 * Get all Expr from the current element
	 * 
	 * @return Collection<JDFExpr>, null if none are available
	 */
	public Collection<JDFExpr> getAllExpr()
	{
		return getChildrenByClass(JDFExpr.class, false, 0);
	}

	/**
	 * (30) append element Expr
	 * 
	 * @return JDFExpr the element
	 */
	public JDFExpr appendExpr()
	{
		return (JDFExpr) appendElement(ElementName.EXPR, null);
	}

}// end namespace JDF
