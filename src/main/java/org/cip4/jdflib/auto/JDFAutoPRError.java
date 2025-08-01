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
 * class JDFAutoPRError : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPRError extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ERRORTYPE, 0x2222222211l, AttributeInfo.EnumAttributeType.enumeration, EnumErrorType.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.VALUE, 0x2222222211l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoPRError
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPRError(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPRError
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPRError(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPRError
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPRError(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for ErrorType
	 */

	public enum EErrorType
	{
		TestNotSupported, TestWrongPDL;

		public static EErrorType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EErrorType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for ErrorType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumErrorType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumErrorType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumErrorType getEnum(String enumName)
		{
			return (EnumErrorType) getEnum(EnumErrorType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumErrorType getEnum(int enumValue)
		{
			return (EnumErrorType) getEnum(EnumErrorType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumErrorType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumErrorType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumErrorType.class);
		}

		/**  */
		public static final EnumErrorType TestNotSupported = new EnumErrorType("TestNotSupported");
		/**  */
		public static final EnumErrorType TestWrongPDL = new EnumErrorType("TestWrongPDL");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ErrorType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ErrorType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setErrorType(EErrorType enumVar)
	{
		setAttribute(AttributeName.ERRORTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ErrorType
	 *
	 * @return the value of the attribute
	 */
	public EErrorType getEErrorType()
	{
		return EErrorType.getEnum(getAttribute(AttributeName.ERRORTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ErrorType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ErrorType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setErrorType(EnumErrorType enumVar)
	{
		setAttribute(AttributeName.ERRORTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ErrorType
	 *
	 * @return the value of the attribute
	 */
	public EnumErrorType getErrorType()
	{
		return EnumErrorType.getEnum(getAttribute(AttributeName.ERRORTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Value ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Value
	 *
	 * @param value the value to set the attribute to
	 */
	public void setValue(String value)
	{
		setAttribute(AttributeName.VALUE, value, null);
	}

	/**
	 * (23) get String attribute Value
	 *
	 * @return the value of the attribute
	 */
	public String getValue()
	{
		return getAttribute(AttributeName.VALUE, null, JDFCoreConstants.EMPTYSTRING);
	}

}
