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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoPreflightAction : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPreflightAction extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.SETSPLITBY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumSetSplitBy.getEnum(0), "RunList");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.SETREF, 0x3333333333l, AttributeInfo.EnumAttributeType.IDREF, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoPreflightAction
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPreflightAction(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPreflightAction
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPreflightAction(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPreflightAction
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPreflightAction(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for SetSplitBy
	 */

	public enum ESetSplitBy
	{
		Page, Document, RunList;

		public static ESetSplitBy getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ESetSplitBy.class, val, ESetSplitBy.RunList);
		}
	}

	/**
	 * Enumeration strings for SetSplitBy
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSetSplitBy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSetSplitBy(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSetSplitBy getEnum(String enumName)
		{
			return (EnumSetSplitBy) getEnum(EnumSetSplitBy.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSetSplitBy getEnum(int enumValue)
		{
			return (EnumSetSplitBy) getEnum(EnumSetSplitBy.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSetSplitBy.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSetSplitBy.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSetSplitBy.class);
		}

		/**  */
		public static final EnumSetSplitBy Page = new EnumSetSplitBy("Page");
		/**  */
		public static final EnumSetSplitBy Document = new EnumSetSplitBy("Document");
		/**  */
		public static final EnumSetSplitBy RunList = new EnumSetSplitBy("RunList");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SetSplitBy ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SetSplitBy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSetSplitBy(ESetSplitBy enumVar)
	{
		setAttribute(AttributeName.SETSPLITBY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute SetSplitBy
	 *
	 * @return the value of the attribute
	 */
	public ESetSplitBy getESetSplitBy()
	{
		return ESetSplitBy.getEnum(getAttribute(AttributeName.SETSPLITBY, null, "RunList"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SetSplitBy ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SetSplitBy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setSetSplitBy(EnumSetSplitBy enumVar)
	{
		setAttribute(AttributeName.SETSPLITBY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute SetSplitBy
	 *
	 * @return the value of the attribute
	 */
	public EnumSetSplitBy getSetSplitBy()
	{
		return EnumSetSplitBy.getEnum(getAttribute(AttributeName.SETSPLITBY, null, "RunList"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SetRef ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SetRef
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSetRef(String value)
	{
		setAttribute(AttributeName.SETREF, value, null);
	}

	/**
	 * (23) get String attribute SetRef
	 *
	 * @return the value of the attribute
	 */
	public String getSetRef()
	{
		return getAttribute(AttributeName.SETREF, null, JDFCoreConstants.EMPTYSTRING);
	}

}
