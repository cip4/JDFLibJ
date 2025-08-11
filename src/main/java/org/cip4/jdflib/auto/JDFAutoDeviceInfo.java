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
import org.cip4.jdflib.auto.JDFAutoMISDetails.EDeviceOperationMode;
import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumDeviceOperationMode;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.node.JDFActivity;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFEvent;
import org.cip4.jdflib.resource.JDFModuleStatus;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoDeviceInfo : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoDeviceInfo extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[14];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.COUNTERUNIT, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DEVICECONDITION, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumDeviceCondition.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DEVICEID, 0x3333333111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.DEVICEOPERATIONMODE, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumDeviceOperationMode.getEnum(0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.DEVICESTATUS, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration, EnumDeviceStatus.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.ENDTIME, 0x3331111111l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.HOURCOUNTER, 0x3333333333l, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.IDLESTARTTIME, 0x3333331111l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.POWERONTIME, 0x3333333333l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.PRODUCTIONCOUNTER, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.SPEED, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.STATUSDETAILS, 0x3333333333l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.TOOLIDS, 0x3331111111l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.TOTALPRODUCTIONCOUNTER, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[6];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.ACTIVITY, 0x3333311111l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.DEVICE, 0x6666666666l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.EMPLOYEE, 0x4444433333l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.EVENT, 0x3331111111l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.JOBPHASE, 0x3333333333l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.MODULESTATUS, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoDeviceInfo
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDeviceInfo(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDeviceInfo
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDeviceInfo(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDeviceInfo
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDeviceInfo(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for DeviceCondition
	 */

	public enum EDeviceCondition
	{
		OK, NeedsAttention, Failure, OffLine;

		public static EDeviceCondition getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EDeviceCondition.class, val, null);
		}
	}

	/**
	 * Enumeration strings for DeviceCondition
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumDeviceCondition extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumDeviceCondition(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumDeviceCondition getEnum(String enumName)
		{
			return (EnumDeviceCondition) getEnum(EnumDeviceCondition.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumDeviceCondition getEnum(int enumValue)
		{
			return (EnumDeviceCondition) getEnum(EnumDeviceCondition.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDeviceCondition.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDeviceCondition.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDeviceCondition.class);
		}

		/**  */
		public static final EnumDeviceCondition OK = new EnumDeviceCondition("OK");
		/**  */
		public static final EnumDeviceCondition NeedsAttention = new EnumDeviceCondition("NeedsAttention");
		/**  */
		public static final EnumDeviceCondition Failure = new EnumDeviceCondition("Failure");
		/**  */
		public static final EnumDeviceCondition OffLine = new EnumDeviceCondition("OffLine");
	}

	/**
	 * Enumeration strings for DeviceStatus
	 */

	public enum EDeviceStatus
	{
		Unknown, Idle, Down, Setup, Running, Cleanup, Stopped;

		public static EDeviceStatus getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EDeviceStatus.class, val, null);
		}
	}

	/**
	 * Enumeration strings for DeviceStatus
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumDeviceStatus extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumDeviceStatus(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumDeviceStatus getEnum(String enumName)
		{
			return (EnumDeviceStatus) getEnum(EnumDeviceStatus.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumDeviceStatus getEnum(int enumValue)
		{
			return (EnumDeviceStatus) getEnum(EnumDeviceStatus.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDeviceStatus.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDeviceStatus.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDeviceStatus.class);
		}

		/**  */
		public static final EnumDeviceStatus Unknown = new EnumDeviceStatus("Unknown");
		/**  */
		public static final EnumDeviceStatus Idle = new EnumDeviceStatus("Idle");
		/**  */
		public static final EnumDeviceStatus Down = new EnumDeviceStatus("Down");
		/**  */
		public static final EnumDeviceStatus Setup = new EnumDeviceStatus("Setup");
		/**  */
		public static final EnumDeviceStatus Running = new EnumDeviceStatus("Running");
		/**  */
		public static final EnumDeviceStatus Cleanup = new EnumDeviceStatus("Cleanup");
		/**  */
		public static final EnumDeviceStatus Stopped = new EnumDeviceStatus("Stopped");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CounterUnit ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CounterUnit
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCounterUnit(String value)
	{
		setAttribute(AttributeName.COUNTERUNIT, value, null);
	}

	/**
	 * (23) get String attribute CounterUnit
	 *
	 * @return the value of the attribute
	 */
	public String getCounterUnit()
	{
		return getAttribute(AttributeName.COUNTERUNIT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DeviceCondition
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DeviceCondition
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDeviceCondition(EDeviceCondition enumVar)
	{
		setAttribute(AttributeName.DEVICECONDITION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute DeviceCondition
	 *
	 * @return the value of the attribute
	 */
	public EDeviceCondition getEDeviceCondition()
	{
		return EDeviceCondition.getEnum(getAttribute(AttributeName.DEVICECONDITION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DeviceCondition
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DeviceCondition
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setDeviceCondition(EDeviceCondition) based on java.lang.enum instead
	 */
	@Deprecated
	public void setDeviceCondition(EnumDeviceCondition enumVar)
	{
		setAttribute(AttributeName.DEVICECONDITION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute DeviceCondition
	 *
	 * @return the value of the attribute
	 * @deprecated use EDeviceCondition getEDeviceCondition() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumDeviceCondition getDeviceCondition()
	{
		return EnumDeviceCondition.getEnum(getAttribute(AttributeName.DEVICECONDITION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DeviceID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DeviceID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDeviceID(String value)
	{
		setAttribute(AttributeName.DEVICEID, value, null);
	}

	/**
	 * (23) get String attribute DeviceID
	 *
	 * @return the value of the attribute
	 */
	public String getDeviceID()
	{
		return getAttribute(AttributeName.DEVICEID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DeviceOperationMode
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DeviceOperationMode
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDeviceOperationMode(EDeviceOperationMode enumVar)
	{
		setAttribute(AttributeName.DEVICEOPERATIONMODE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute DeviceOperationMode
	 *
	 * @return the value of the attribute
	 */
	public EDeviceOperationMode getEDeviceOperationMode()
	{
		return EDeviceOperationMode.getEnum(getAttribute(AttributeName.DEVICEOPERATIONMODE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DeviceOperationMode
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DeviceOperationMode
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setDeviceOperationMode(EDeviceOperationMode) based on java.lang.enum instead
	 */
	@Deprecated
	public void setDeviceOperationMode(EnumDeviceOperationMode enumVar)
	{
		setAttribute(AttributeName.DEVICEOPERATIONMODE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute DeviceOperationMode
	 *
	 * @return the value of the attribute
	 * @deprecated use EDeviceOperationMode getEDeviceOperationMode() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumDeviceOperationMode getDeviceOperationMode()
	{
		return EnumDeviceOperationMode.getEnum(getAttribute(AttributeName.DEVICEOPERATIONMODE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DeviceStatus
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DeviceStatus
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDeviceStatus(EDeviceStatus enumVar)
	{
		setAttribute(AttributeName.DEVICESTATUS, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute DeviceStatus
	 *
	 * @return the value of the attribute
	 */
	public EDeviceStatus getEDeviceStatus()
	{
		return EDeviceStatus.getEnum(getAttribute(AttributeName.DEVICESTATUS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DeviceStatus
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DeviceStatus
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setDeviceStatus(EDeviceStatus) based on java.lang.enum instead
	 */
	@Deprecated
	public void setDeviceStatus(EnumDeviceStatus enumVar)
	{
		setAttribute(AttributeName.DEVICESTATUS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute DeviceStatus
	 *
	 * @return the value of the attribute
	 * @deprecated use EDeviceStatus getEDeviceStatus() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumDeviceStatus getDeviceStatus()
	{
		return EnumDeviceStatus.getEnum(getAttribute(AttributeName.DEVICESTATUS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute EndTime ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute EndTime
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setEndTime(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.ENDTIME, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute EndTime
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getEndTime()
	{
		final String str = getAttribute(AttributeName.ENDTIME, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute HourCounter ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute HourCounter
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHourCounter(JDFDuration value)
	{
		setAttribute(AttributeName.HOURCOUNTER, value, null);
	}

	/**
	 * (20) get JDFDuration attribute HourCounter
	 *
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getHourCounter()
	{
		final String strAttrName = getAttribute(AttributeName.HOURCOUNTER, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute IdleStartTime
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute IdleStartTime
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setIdleStartTime(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.IDLESTARTTIME, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute IdleStartTime
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getIdleStartTime()
	{
		final String str = getAttribute(AttributeName.IDLESTARTTIME, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PowerOnTime ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute PowerOnTime
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setPowerOnTime(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.POWERONTIME, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute PowerOnTime
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getPowerOnTime()
	{
		final String str = getAttribute(AttributeName.POWERONTIME, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProductionCounter
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ProductionCounter
	 *
	 * @param value the value to set the attribute to
	 */
	public void setProductionCounter(double value)
	{
		setAttribute(AttributeName.PRODUCTIONCOUNTER, value, null);
	}

	/**
	 * (17) get double attribute ProductionCounter
	 *
	 * @return double the value of the attribute
	 */
	public double getProductionCounter()
	{
		return getRealAttribute(AttributeName.PRODUCTIONCOUNTER, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Speed ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Speed
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSpeed(double value)
	{
		setAttribute(AttributeName.SPEED, value, null);
	}

	/**
	 * (17) get double attribute Speed
	 *
	 * @return double the value of the attribute
	 */
	public double getSpeed()
	{
		return getRealAttribute(AttributeName.SPEED, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StatusDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StatusDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStatusDetails(String value)
	{
		setAttribute(AttributeName.STATUSDETAILS, value, null);
	}

	/**
	 * (23) get String attribute StatusDetails
	 *
	 * @return the value of the attribute
	 */
	public String getStatusDetails()
	{
		return getAttribute(AttributeName.STATUSDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ToolIDs ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ToolIDs
	 *
	 * @param value the value to set the attribute to
	 */
	public void setToolIDs(VString value)
	{
		setAttribute(AttributeName.TOOLIDS, value, null);
	}

	/**
	 * (21) get VString attribute ToolIDs
	 *
	 * @return VString the value of the attribute
	 */
	public VString getToolIDs()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.TOOLIDS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TotalProductionCounter
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TotalProductionCounter
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTotalProductionCounter(double value)
	{
		setAttribute(AttributeName.TOTALPRODUCTIONCOUNTER, value, null);
	}

	/**
	 * (17) get double attribute TotalProductionCounter
	 *
	 * @return double the value of the attribute
	 */
	public double getTotalProductionCounter()
	{
		return getRealAttribute(AttributeName.TOTALPRODUCTIONCOUNTER, null, 0.0);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Activity
	 *
	 * @return JDFActivity the element
	 */
	public JDFActivity getActivity()
	{
		return (JDFActivity) getElement(ElementName.ACTIVITY, null, 0);
	}

	/**
	 * (25) getCreateActivity
	 * 
	 * @return JDFActivity the element
	 */
	public JDFActivity getCreateActivity()
	{
		return (JDFActivity) getCreateElement_JDFElement(ElementName.ACTIVITY, null, 0);
	}

	/**
	 * (26) getCreateActivity
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFActivity the element
	 */
	public JDFActivity getCreateActivity(int iSkip)
	{
		return (JDFActivity) getCreateElement_JDFElement(ElementName.ACTIVITY, null, iSkip);
	}

	/**
	 * (27) const get element Activity
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFActivity the element default is getActivity(0)
	 */
	public JDFActivity getActivity(int iSkip)
	{
		return (JDFActivity) getElement(ElementName.ACTIVITY, null, iSkip);
	}

	/**
	 * Get all Activity from the current element
	 * 
	 * @return Collection<JDFActivity>, null if none are available
	 */
	public Collection<JDFActivity> getAllActivity()
	{
		return getChildArrayByClass(JDFActivity.class, false, 0);
	}

	/**
	 * (30) append element Activity
	 *
	 * @return JDFActivity the element
	 */
	public JDFActivity appendActivity()
	{
		return (JDFActivity) appendElement(ElementName.ACTIVITY, null);
	}

	/**
	 * (24) const get element Device
	 *
	 * @return JDFDevice the element
	 */
	public JDFDevice getDevice()
	{
		return (JDFDevice) getElement(ElementName.DEVICE, null, 0);
	}

	/**
	 * (25) getCreateDevice
	 * 
	 * @return JDFDevice the element
	 */
	public JDFDevice getCreateDevice()
	{
		return (JDFDevice) getCreateElement_JDFElement(ElementName.DEVICE, null, 0);
	}

	/**
	 * (29) append element Device
	 *
	 * @return JDFDevice the element @ if the element already exists
	 */
	public JDFDevice appendDevice()
	{
		return (JDFDevice) appendElementN(ElementName.DEVICE, 1, null);
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

	/**
	 * (24) const get element Event
	 *
	 * @return JDFEvent the element
	 */
	public JDFEvent getEvent()
	{
		return (JDFEvent) getElement(ElementName.EVENT, null, 0);
	}

	/**
	 * (25) getCreateEvent
	 * 
	 * @return JDFEvent the element
	 */
	public JDFEvent getCreateEvent()
	{
		return (JDFEvent) getCreateElement_JDFElement(ElementName.EVENT, null, 0);
	}

	/**
	 * (26) getCreateEvent
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFEvent the element
	 */
	public JDFEvent getCreateEvent(int iSkip)
	{
		return (JDFEvent) getCreateElement_JDFElement(ElementName.EVENT, null, iSkip);
	}

	/**
	 * (27) const get element Event
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFEvent the element default is getEvent(0)
	 */
	public JDFEvent getEvent(int iSkip)
	{
		return (JDFEvent) getElement(ElementName.EVENT, null, iSkip);
	}

	/**
	 * Get all Event from the current element
	 * 
	 * @return Collection<JDFEvent>, null if none are available
	 */
	public Collection<JDFEvent> getAllEvent()
	{
		return getChildArrayByClass(JDFEvent.class, false, 0);
	}

	/**
	 * (30) append element Event
	 *
	 * @return JDFEvent the element
	 */
	public JDFEvent appendEvent()
	{
		return (JDFEvent) appendElement(ElementName.EVENT, null);
	}

	/**
	 * (24) const get element JobPhase
	 *
	 * @return JDFJobPhase the element
	 */
	public JDFJobPhase getJobPhase()
	{
		return (JDFJobPhase) getElement(ElementName.JOBPHASE, null, 0);
	}

	/**
	 * (25) getCreateJobPhase
	 * 
	 * @return JDFJobPhase the element
	 */
	public JDFJobPhase getCreateJobPhase()
	{
		return (JDFJobPhase) getCreateElement_JDFElement(ElementName.JOBPHASE, null, 0);
	}

	/**
	 * (26) getCreateJobPhase
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFJobPhase the element
	 */
	public JDFJobPhase getCreateJobPhase(int iSkip)
	{
		return (JDFJobPhase) getCreateElement_JDFElement(ElementName.JOBPHASE, null, iSkip);
	}

	/**
	 * (27) const get element JobPhase
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFJobPhase the element default is getJobPhase(0)
	 */
	public JDFJobPhase getJobPhase(int iSkip)
	{
		return (JDFJobPhase) getElement(ElementName.JOBPHASE, null, iSkip);
	}

	/**
	 * Get all JobPhase from the current element
	 * 
	 * @return Collection<JDFJobPhase>, null if none are available
	 */
	public Collection<JDFJobPhase> getAllJobPhase()
	{
		return getChildArrayByClass(JDFJobPhase.class, false, 0);
	}

	/**
	 * (30) append element JobPhase
	 *
	 * @return JDFJobPhase the element
	 */
	public JDFJobPhase appendJobPhase()
	{
		return (JDFJobPhase) appendElement(ElementName.JOBPHASE, null);
	}

	/**
	 * (24) const get element ModuleStatus
	 *
	 * @return JDFModuleStatus the element
	 */
	public JDFModuleStatus getModuleStatus()
	{
		return (JDFModuleStatus) getElement(ElementName.MODULESTATUS, null, 0);
	}

	/**
	 * (25) getCreateModuleStatus
	 * 
	 * @return JDFModuleStatus the element
	 */
	public JDFModuleStatus getCreateModuleStatus()
	{
		return (JDFModuleStatus) getCreateElement_JDFElement(ElementName.MODULESTATUS, null, 0);
	}

	/**
	 * (26) getCreateModuleStatus
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFModuleStatus the element
	 */
	public JDFModuleStatus getCreateModuleStatus(int iSkip)
	{
		return (JDFModuleStatus) getCreateElement_JDFElement(ElementName.MODULESTATUS, null, iSkip);
	}

	/**
	 * (27) const get element ModuleStatus
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFModuleStatus the element default is getModuleStatus(0)
	 */
	public JDFModuleStatus getModuleStatus(int iSkip)
	{
		return (JDFModuleStatus) getElement(ElementName.MODULESTATUS, null, iSkip);
	}

	/**
	 * Get all ModuleStatus from the current element
	 * 
	 * @return Collection<JDFModuleStatus>, null if none are available
	 */
	public Collection<JDFModuleStatus> getAllModuleStatus()
	{
		return getChildArrayByClass(JDFModuleStatus.class, false, 0);
	}

	/**
	 * (30) append element ModuleStatus
	 *
	 * @return JDFModuleStatus the element
	 */
	public JDFModuleStatus appendModuleStatus()
	{
		return (JDFModuleStatus) appendElement(ElementName.MODULESTATUS, null);
	}

}
