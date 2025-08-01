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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.resource.JDFErrorData;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoError : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoError extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ERRORID, 0x2222222222l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.RESEND, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, EnumResend.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.RETURNCODE, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.ERRORDATA, 0x3333333111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoError
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoError(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoError
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoError(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoError
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoError(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for Resend
	 */

	public enum EResend
	{
		Required, Prohibited;

		public static EResend getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EResend.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Resend
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumResend extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumResend(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumResend getEnum(String enumName)
		{
			return (EnumResend) getEnum(EnumResend.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumResend getEnum(int enumValue)
		{
			return (EnumResend) getEnum(EnumResend.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumResend.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumResend.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumResend.class);
		}

		/**  */
		public static final EnumResend Required = new EnumResend("Required");
		/**  */
		public static final EnumResend Prohibited = new EnumResend("Prohibited");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ErrorID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ErrorID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setErrorID(String value)
	{
		setAttribute(AttributeName.ERRORID, value, null);
	}

	/**
	 * (23) get String attribute ErrorID
	 *
	 * @return the value of the attribute
	 */
	public String getErrorID()
	{
		return getAttribute(AttributeName.ERRORID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Resend ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Resend
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setResend(EResend enumVar)
	{
		setAttribute(AttributeName.RESEND, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Resend
	 *
	 * @return the value of the attribute
	 */
	public EResend getEResend()
	{
		return EResend.getEnum(getAttribute(AttributeName.RESEND, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Resend ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Resend
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setResend(EnumResend enumVar)
	{
		setAttribute(AttributeName.RESEND, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Resend
	 *
	 * @return the value of the attribute
	 */
	public EnumResend getResend()
	{
		return EnumResend.getEnum(getAttribute(AttributeName.RESEND, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ReturnCode ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ReturnCode
	 *
	 * @param value the value to set the attribute to
	 */
	public void setReturnCode(int value)
	{
		setAttribute(AttributeName.RETURNCODE, value, null);
	}

	/**
	 * (15) get int attribute ReturnCode
	 *
	 * @return int the value of the attribute
	 */
	public int getReturnCode()
	{
		return getIntAttribute(AttributeName.RETURNCODE, null, 0);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element ErrorData
	 *
	 * @return JDFErrorData the element
	 */
	public JDFErrorData getErrorData()
	{
		return (JDFErrorData) getElement(ElementName.ERRORDATA, null, 0);
	}

	/**
	 * (25) getCreateErrorData
	 * 
	 * @return JDFErrorData the element
	 */
	public JDFErrorData getCreateErrorData()
	{
		return (JDFErrorData) getCreateElement_JDFElement(ElementName.ERRORDATA, null, 0);
	}

	/**
	 * (26) getCreateErrorData
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFErrorData the element
	 */
	public JDFErrorData getCreateErrorData(int iSkip)
	{
		return (JDFErrorData) getCreateElement_JDFElement(ElementName.ERRORDATA, null, iSkip);
	}

	/**
	 * (27) const get element ErrorData
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFErrorData the element default is getErrorData(0)
	 */
	public JDFErrorData getErrorData(int iSkip)
	{
		return (JDFErrorData) getElement(ElementName.ERRORDATA, null, iSkip);
	}

	/**
	 * Get all ErrorData from the current element
	 * 
	 * @return Collection<JDFErrorData>, null if none are available
	 */
	public Collection<JDFErrorData> getAllErrorData()
	{
		return getChildArrayByClass(JDFErrorData.class, false, 0);
	}

	/**
	 * (30) append element ErrorData
	 *
	 * @return JDFErrorData the element
	 */
	public JDFErrorData appendErrorData()
	{
		return (JDFErrorData) appendElement(ElementName.ERRORDATA, null);
	}

}
