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
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoStatusQuParams : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoStatusQuParams extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DEVICEDETAILS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumDeviceDetails.getEnum(0), "None");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.EMPLOYEEINFO, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.JOBDETAILS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumJobDetails.getEnum(0), "None");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.JOBID, 0x3333333333l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.JOBPARTID, 0x3333333333l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.QUEUEENTRYID, 0x3333333311l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.QUEUEINFO, 0x4444333333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.PART, 0x3333333311l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoStatusQuParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoStatusQuParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoStatusQuParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoStatusQuParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoStatusQuParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoStatusQuParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for DeviceDetails
	 */

	public enum EDeviceDetails
	{
		None, Brief, Modules, Details, Capability, Full;

		public static EDeviceDetails getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EDeviceDetails.class, val, EDeviceDetails.None);
		}
	}

	/**
	 * Enumeration strings for DeviceDetails
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumDeviceDetails extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumDeviceDetails(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumDeviceDetails getEnum(String enumName)
		{
			return (EnumDeviceDetails) getEnum(EnumDeviceDetails.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumDeviceDetails getEnum(int enumValue)
		{
			return (EnumDeviceDetails) getEnum(EnumDeviceDetails.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDeviceDetails.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDeviceDetails.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDeviceDetails.class);
		}

		/**  */
		public static final EnumDeviceDetails None = new EnumDeviceDetails("None");
		/**  */
		public static final EnumDeviceDetails Brief = new EnumDeviceDetails("Brief");
		/**  */
		public static final EnumDeviceDetails Modules = new EnumDeviceDetails("Modules");
		/**  */
		public static final EnumDeviceDetails Details = new EnumDeviceDetails("Details");
		/**  */
		public static final EnumDeviceDetails Capability = new EnumDeviceDetails("Capability");
		/**  */
		public static final EnumDeviceDetails Full = new EnumDeviceDetails("Full");
	}

	/**
	 * Enumeration strings for JobDetails
	 */

	public enum EJobDetails
	{
		None, MIS, Brief, Full;

		public static EJobDetails getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EJobDetails.class, val, EJobDetails.None);
		}
	}

	/**
	 * Enumeration strings for JobDetails
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumJobDetails extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumJobDetails(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumJobDetails getEnum(String enumName)
		{
			return (EnumJobDetails) getEnum(EnumJobDetails.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumJobDetails getEnum(int enumValue)
		{
			return (EnumJobDetails) getEnum(EnumJobDetails.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumJobDetails.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumJobDetails.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumJobDetails.class);
		}

		/**  */
		public static final EnumJobDetails None = new EnumJobDetails("None");
		/**  */
		public static final EnumJobDetails MIS = new EnumJobDetails("MIS");
		/**  */
		public static final EnumJobDetails Brief = new EnumJobDetails("Brief");
		/**  */
		public static final EnumJobDetails Full = new EnumJobDetails("Full");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DeviceDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DeviceDetails
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDeviceDetails(EDeviceDetails enumVar)
	{
		setAttribute(AttributeName.DEVICEDETAILS, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute DeviceDetails
	 *
	 * @return the value of the attribute
	 */
	public EDeviceDetails getEDeviceDetails()
	{
		return EDeviceDetails.getEnum(getAttribute(AttributeName.DEVICEDETAILS, null, "None"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DeviceDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DeviceDetails
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setDeviceDetails(EnumDeviceDetails enumVar)
	{
		setAttribute(AttributeName.DEVICEDETAILS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute DeviceDetails
	 *
	 * @return the value of the attribute
	 */
	public EnumDeviceDetails getDeviceDetails()
	{
		return EnumDeviceDetails.getEnum(getAttribute(AttributeName.DEVICEDETAILS, null, "None"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute EmployeeInfo
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EmployeeInfo
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEmployeeInfo(boolean value)
	{
		setAttribute(AttributeName.EMPLOYEEINFO, value, null);
	}

	/**
	 * (18) get boolean attribute EmployeeInfo
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getEmployeeInfo()
	{
		return getBoolAttribute(AttributeName.EMPLOYEEINFO, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JobDetails ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute JobDetails
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setJobDetails(EJobDetails enumVar)
	{
		setAttribute(AttributeName.JOBDETAILS, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute JobDetails
	 *
	 * @return the value of the attribute
	 */
	public EJobDetails getEJobDetails()
	{
		return EJobDetails.getEnum(getAttribute(AttributeName.JOBDETAILS, null, "None"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JobDetails ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute JobDetails
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setJobDetails(EnumJobDetails enumVar)
	{
		setAttribute(AttributeName.JOBDETAILS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute JobDetails
	 *
	 * @return the value of the attribute
	 */
	public EnumJobDetails getJobDetails()
	{
		return EnumJobDetails.getEnum(getAttribute(AttributeName.JOBDETAILS, null, "None"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JobID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JobID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJobID(String value)
	{
		setAttribute(AttributeName.JOBID, value, null);
	}

	/**
	 * (23) get String attribute JobID
	 *
	 * @return the value of the attribute
	 */
	public String getJobID()
	{
		return getAttribute(AttributeName.JOBID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JobPartID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JobPartID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJobPartID(String value)
	{
		setAttribute(AttributeName.JOBPARTID, value, null);
	}

	/**
	 * (23) get String attribute JobPartID
	 *
	 * @return the value of the attribute
	 */
	public String getJobPartID()
	{
		return getAttribute(AttributeName.JOBPARTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute QueueEntryID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute QueueEntryID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setQueueEntryID(String value)
	{
		setAttribute(AttributeName.QUEUEENTRYID, value, null);
	}

	/**
	 * (23) get String attribute QueueEntryID
	 *
	 * @return the value of the attribute
	 */
	public String getQueueEntryID()
	{
		return getAttribute(AttributeName.QUEUEENTRYID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute QueueInfo ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute QueueInfo
	 *
	 * @param value the value to set the attribute to
	 */
	public void setQueueInfo(boolean value)
	{
		setAttribute(AttributeName.QUEUEINFO, value, null);
	}

	/**
	 * (18) get boolean attribute QueueInfo
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getQueueInfo()
	{
		return getBoolAttribute(AttributeName.QUEUEINFO, null, false);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Part
	 *
	 * @return JDFPart the element
	 */
	public JDFPart getPart()
	{
		return (JDFPart) getElement(ElementName.PART, null, 0);
	}

	/**
	 * (25) getCreatePart
	 * 
	 * @return JDFPart the element
	 */
	public JDFPart getCreatePart()
	{
		return (JDFPart) getCreateElement_JDFElement(ElementName.PART, null, 0);
	}

	/**
	 * (26) getCreatePart
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPart the element
	 */
	public JDFPart getCreatePart(int iSkip)
	{
		return (JDFPart) getCreateElement_JDFElement(ElementName.PART, null, iSkip);
	}

	/**
	 * (27) const get element Part
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPart the element default is getPart(0)
	 */
	public JDFPart getPart(int iSkip)
	{
		return (JDFPart) getElement(ElementName.PART, null, iSkip);
	}

	/**
	 * Get all Part from the current element
	 * 
	 * @return Collection<JDFPart>, null if none are available
	 */
	public Collection<JDFPart> getAllPart()
	{
		return getChildArrayByClass(JDFPart.class, false, 0);
	}

	/**
	 * (30) append element Part
	 *
	 * @return JDFPart the element
	 */
	public JDFPart appendPart()
	{
		return (JDFPart) appendElement(ElementName.PART, null);
	}

}
