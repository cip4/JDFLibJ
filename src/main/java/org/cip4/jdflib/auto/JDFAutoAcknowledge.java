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
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoAcknowledge : public JDFMessage
 *****************************************************************************
 * 
 */

public abstract class JDFAutoAcknowledge extends JDFMessage
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.REFID, 0x2222222222l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ACKNOWLEDGETYPE, 0x3333333331l, AttributeInfo.EnumAttributeType.enumerations, EnumAcknowledgeType.getEnum(0), "Completed");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.RETURNCODE, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, "0");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.NOTIFICATION, 0x6666666666l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.EMPLOYEE, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoAcknowledge
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoAcknowledge(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAcknowledge
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoAcknowledge(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAcknowledge
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoAcknowledge(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for AcknowledgeType
	 */

	public enum EAcknowledgeType
	{
		Received, Applied, Completed;

		public static EAcknowledgeType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EAcknowledgeType.class, val, EAcknowledgeType.Completed);
		}
	}

	/**
	 * Enumeration strings for AcknowledgeType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumAcknowledgeType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumAcknowledgeType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumAcknowledgeType getEnum(String enumName)
		{
			return (EnumAcknowledgeType) getEnum(EnumAcknowledgeType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumAcknowledgeType getEnum(int enumValue)
		{
			return (EnumAcknowledgeType) getEnum(EnumAcknowledgeType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumAcknowledgeType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumAcknowledgeType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumAcknowledgeType.class);
		}

		/**  */
		public static final EnumAcknowledgeType Received = new EnumAcknowledgeType("Received");
		/**  */
		public static final EnumAcknowledgeType Applied = new EnumAcknowledgeType("Applied");
		/**  */
		public static final EnumAcknowledgeType Completed = new EnumAcknowledgeType("Completed");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute refID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute refID
	 *
	 * @param value the value to set the attribute to
	 */
	@Override
	public void setrefID(String value)
	{
		setAttribute(AttributeName.REFID, value, null);
	}

	/**
	 * (23) get String attribute refID
	 *
	 * @return the value of the attribute
	 */
	@Override
	public String getrefID()
	{
		return getAttribute(AttributeName.REFID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AcknowledgeType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute AcknowledgeType
	 *
	 * @param v List of the enumeration values
	 */
	public void setEAcknowledgeType(List<EAcknowledgeType> v)
	{
		setEnumsAttribute(AttributeName.ACKNOWLEDGETYPE, v, null);
	}

	/**
	 * (9.2) get AcknowledgeType attribute AcknowledgeType
	 *
	 * @return Vector of the enumerations
	 */
	public List<EAcknowledgeType> getEnumsAcknowledgeType()
	{
		return getEnumerationsAttribute(AttributeName.ACKNOWLEDGETYPE, null, EAcknowledgeType.class);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AcknowledgeType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute AcknowledgeType
	 *
	 * @param v List of the enumeration values
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setAcknowledgeType(List<EnumAcknowledgeType> v)
	{
		setEnumerationsAttribute(AttributeName.ACKNOWLEDGETYPE, v, null);
	}

	/**
	 * (9.2) get AcknowledgeType attribute AcknowledgeType
	 *
	 * @return Vector of the enumerations
	 */
	public Vector<EnumAcknowledgeType> getAcknowledgeType()
	{
		return getEnumerationsAttribute(AttributeName.ACKNOWLEDGETYPE, null, EnumAcknowledgeType.Completed, false);
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
	@Override
	public int getReturnCode()
	{
		return getIntAttribute(AttributeName.RETURNCODE, null, 0);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Notification
	 *
	 * @return JDFNotification the element
	 */
	public JDFNotification getNotification()
	{
		return (JDFNotification) getElement(ElementName.NOTIFICATION, null, 0);
	}

	/**
	 * (25) getCreateNotification
	 * 
	 * @return JDFNotification the element
	 */
	public JDFNotification getCreateNotification()
	{
		return (JDFNotification) getCreateElement_JDFElement(ElementName.NOTIFICATION, null, 0);
	}

	/**
	 * (29) append element Notification
	 *
	 * @return JDFNotification the element @ if the element already exists
	 */
	public JDFNotification appendNotification()
	{
		return (JDFNotification) appendElementN(ElementName.NOTIFICATION, 1, null);
	}

	/**
	 * (24) const get element Employee
	 *
	 * @return JDFEmployee the element
	 */
	public JDFEmployee getEmployee()
	{
		return (JDFEmployee) getElement(ElementName.EMPLOYEE, null, 0);
	}

	/**
	 * (25) getCreateEmployee
	 * 
	 * @return JDFEmployee the element
	 */
	public JDFEmployee getCreateEmployee()
	{
		return (JDFEmployee) getCreateElement_JDFElement(ElementName.EMPLOYEE, null, 0);
	}

	/**
	 * (26) getCreateEmployee
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFEmployee the element
	 */
	public JDFEmployee getCreateEmployee(int iSkip)
	{
		return (JDFEmployee) getCreateElement_JDFElement(ElementName.EMPLOYEE, null, iSkip);
	}

	/**
	 * (27) const get element Employee
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFEmployee the element default is getEmployee(0)
	 */
	public JDFEmployee getEmployee(int iSkip)
	{
		return (JDFEmployee) getElement(ElementName.EMPLOYEE, null, iSkip);
	}

	/**
	 * Get all Employee from the current element
	 * 
	 * @return Collection<JDFEmployee>, null if none are available
	 */
	public Collection<JDFEmployee> getAllEmployee()
	{
		return getChildArrayByClass(JDFEmployee.class, false, 0);
	}

	/**
	 * (30) append element Employee
	 *
	 * @return JDFEmployee the element
	 */
	public JDFEmployee appendEmployee()
	{
		return (JDFEmployee) appendElement(ElementName.EMPLOYEE, null);
	}

}
