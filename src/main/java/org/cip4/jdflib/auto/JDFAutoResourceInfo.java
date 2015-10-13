/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFCostCenter;
import org.cip4.jdflib.resource.process.JDFLot;
import org.cip4.jdflib.resource.process.JDFMISDetails;

/**
*****************************************************************************
class JDFAutoResourceInfo : public JDFElement

*****************************************************************************
*/

public abstract class JDFAutoResourceInfo extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[19];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTUALAMOUNT, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.AMOUNT, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.AVAILABLEAMOUNT, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.COMMANDRESULT, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumCommandResult.getEnum(0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.DEVICEID, 0x33311111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.LEVEL, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumLevel.getEnum(0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.LOCATION, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.LOTCONTROLLED, 0x33331111, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.MODULEID, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.MODULEINDEX, 0x33333111, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.ORIENTATION, 0x33311111, AttributeInfo.EnumAttributeType.enumeration, EnumOrientation.getEnum(0), null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.PROCESSUSAGE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.PRODUCTID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.RESOURCEID, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.RESOURCENAME, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.SCOPE, 0x33311111, AttributeInfo.EnumAttributeType.enumeration, EnumScope.getEnum(0), null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.STATUS, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, JDFResource.EnumResStatus.getEnum(0), null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.UNIT, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.USAGE, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, JDFResourceLink.EnumUsage.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[6];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.AMOUNTPOOL, 0x66666111);
		elemInfoTable[1] = new ElemInfoTable(ElementName.COSTCENTER, 0x66666666);
		elemInfoTable[2] = new ElemInfoTable(ElementName.LOT, 0x33331111);
		elemInfoTable[3] = new ElemInfoTable(ElementName.MISDETAILS, 0x66666611);
		elemInfoTable[4] = new ElemInfoTable(ElementName.PART, 0x33333311);
		elemInfoTable[5] = new ElemInfoTable(ElementName.RESOURCE, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoResourceInfo
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoResourceInfo(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoResourceInfo
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoResourceInfo(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoResourceInfo
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoResourceInfo(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return  the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoResourceInfo[  --> " + super.toString() + " ]";
	}

	/**
	* Enumeration strings for CommandResult
	*/

	@SuppressWarnings("rawtypes")
	public static class EnumCommandResult extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumCommandResult(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumCommandResult getEnum(String enumName)
		{
			return (EnumCommandResult) getEnum(EnumCommandResult.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumCommandResult getEnum(int enumValue)
		{
			return (EnumCommandResult) getEnum(EnumCommandResult.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumCommandResult.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumCommandResult.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumCommandResult.class);
		}

		/**  */
		public static final EnumCommandResult Rejected = new EnumCommandResult("Rejected");
		/**  */
		public static final EnumCommandResult Removed = new EnumCommandResult("Removed");
		/**  */
		public static final EnumCommandResult New = new EnumCommandResult("New");
		/**  */
		public static final EnumCommandResult Merged = new EnumCommandResult("Merged");
		/**  */
		public static final EnumCommandResult Replaced = new EnumCommandResult("Replaced");
	}

	/**
	* Enumeration strings for Level
	*/

	@SuppressWarnings("rawtypes")
	public static class EnumLevel extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumLevel(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumLevel getEnum(String enumName)
		{
			return (EnumLevel) getEnum(EnumLevel.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumLevel getEnum(int enumValue)
		{
			return (EnumLevel) getEnum(EnumLevel.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumLevel.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumLevel.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumLevel.class);
		}

		/**  */
		public static final EnumLevel Empty = new EnumLevel("Empty");
		/**  */
		public static final EnumLevel Low = new EnumLevel("Low");
		/**  */
		public static final EnumLevel OK = new EnumLevel("OK");
	}

	/**
	* Enumeration strings for Orientation
	*/

	@SuppressWarnings("rawtypes")
	public static class EnumOrientation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumOrientation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumOrientation getEnum(String enumName)
		{
			return (EnumOrientation) getEnum(EnumOrientation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumOrientation getEnum(int enumValue)
		{
			return (EnumOrientation) getEnum(EnumOrientation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumOrientation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumOrientation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumOrientation.class);
		}

		/**  */
		public static final EnumOrientation Up = new EnumOrientation("Up");
		/**  */
		public static final EnumOrientation Down = new EnumOrientation("Down");
		/**  */
		public static final EnumOrientation Left = new EnumOrientation("Left");
		/**  */
		public static final EnumOrientation Right = new EnumOrientation("Right");
	}

	/**
	* Enumeration strings for Scope
	*/

	@SuppressWarnings("rawtypes")
	public static class EnumScope extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumScope(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumScope getEnum(String enumName)
		{
			return (EnumScope) getEnum(EnumScope.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumScope getEnum(int enumValue)
		{
			return (EnumScope) getEnum(EnumScope.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumScope.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumScope.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumScope.class);
		}

		/**  */
		public static final EnumScope Present = new EnumScope("Present");
		/**  */
		public static final EnumScope Allowed = new EnumScope("Allowed");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute ActualAmount
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ActualAmount
	  * @param value the value to set the attribute to
	  */
	public void setActualAmount(double value)
	{
		setAttribute(AttributeName.ACTUALAMOUNT, value, null);
	}

	/**
	  * (17) get double attribute ActualAmount
	  * @return double the value of the attribute
	  */
	public double getActualAmount()
	{
		return getRealAttribute(AttributeName.ACTUALAMOUNT, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Amount
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Amount
	  * @param value the value to set the attribute to
	  */
	public void setAmount(double value)
	{
		setAttribute(AttributeName.AMOUNT, value, null);
	}

	/**
	  * (17) get double attribute Amount
	  * @return double the value of the attribute
	  */
	public double getAmount()
	{
		return getRealAttribute(AttributeName.AMOUNT, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute AvailableAmount
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute AvailableAmount
	  * @param value the value to set the attribute to
	  */
	public void setAvailableAmount(double value)
	{
		setAttribute(AttributeName.AVAILABLEAMOUNT, value, null);
	}

	/**
	  * (17) get double attribute AvailableAmount
	  * @return double the value of the attribute
	  */
	public double getAvailableAmount()
	{
		return getRealAttribute(AttributeName.AVAILABLEAMOUNT, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute CommandResult
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute CommandResult
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setCommandResult(EnumCommandResult enumVar)
	{
		setAttribute(AttributeName.COMMANDRESULT, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute CommandResult
	  * @return the value of the attribute
	  */
	public EnumCommandResult getCommandResult()
	{
		return EnumCommandResult.getEnum(getAttribute(AttributeName.COMMANDRESULT, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute DeviceID
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute DeviceID
	  * @param value the value to set the attribute to
	  */
	public void setDeviceID(String value)
	{
		setAttribute(AttributeName.DEVICEID, value, null);
	}

	/**
	  * (23) get String attribute DeviceID
	  * @return the value of the attribute
	  */
	public String getDeviceID()
	{
		return getAttribute(AttributeName.DEVICEID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Level
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute Level
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setLevel(EnumLevel enumVar)
	{
		setAttribute(AttributeName.LEVEL, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute Level
	  * @return the value of the attribute
	  */
	public EnumLevel getLevel()
	{
		return EnumLevel.getEnum(getAttribute(AttributeName.LEVEL, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Location
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Location
	  * @param value the value to set the attribute to
	  */
	public void setLocation(String value)
	{
		setAttribute(AttributeName.LOCATION, value, null);
	}

	/**
	  * (23) get String attribute Location
	  * @return the value of the attribute
	  */
	public String getLocation()
	{
		return getAttribute(AttributeName.LOCATION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute LotControlled
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute LotControlled
	  * @param value the value to set the attribute to
	  */
	public void setLotControlled(boolean value)
	{
		setAttribute(AttributeName.LOTCONTROLLED, value, null);
	}

	/**
	  * (18) get boolean attribute LotControlled
	  * @return boolean the value of the attribute
	  */
	public boolean getLotControlled()
	{
		return getBoolAttribute(AttributeName.LOTCONTROLLED, null, false);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ModuleID
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ModuleID
	  * @param value the value to set the attribute to
	  */
	public void setModuleID(String value)
	{
		setAttribute(AttributeName.MODULEID, value, null);
	}

	/**
	  * (23) get String attribute ModuleID
	  * @return the value of the attribute
	  */
	public String getModuleID()
	{
		return getAttribute(AttributeName.MODULEID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ModuleIndex
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ModuleIndex
	  * @param value the value to set the attribute to
	  */
	public void setModuleIndex(JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.MODULEINDEX, value, null);
	}

	/**
	  * (20) get JDFIntegerRangeList attribute ModuleIndex
	  * @return JDFIntegerRangeList the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFIntegerRangeList
	  */
	public JDFIntegerRangeList getModuleIndex()
	{
		String strAttrName = getAttribute(AttributeName.MODULEINDEX, null, null);
		JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Orientation
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute Orientation
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setOrientation(EnumOrientation enumVar)
	{
		setAttribute(AttributeName.ORIENTATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute Orientation
	  * @return the value of the attribute
	  */
	public EnumOrientation getOrientation()
	{
		return EnumOrientation.getEnum(getAttribute(AttributeName.ORIENTATION, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ProcessUsage
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ProcessUsage
	  * @param value the value to set the attribute to
	  */
	public void setProcessUsage(String value)
	{
		setAttribute(AttributeName.PROCESSUSAGE, value, null);
	}

	/**
	  * (23) get String attribute ProcessUsage
	  * @return the value of the attribute
	  */
	public String getProcessUsage()
	{
		return getAttribute(AttributeName.PROCESSUSAGE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ProductID
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ProductID
	  * @param value the value to set the attribute to
	  */
	public void setProductID(String value)
	{
		setAttribute(AttributeName.PRODUCTID, value, null);
	}

	/**
	  * (23) get String attribute ProductID
	  * @return the value of the attribute
	  */
	public String getProductID()
	{
		return getAttribute(AttributeName.PRODUCTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ResourceID
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ResourceID
	  * @param value the value to set the attribute to
	  */
	public void setResourceID(String value)
	{
		setAttribute(AttributeName.RESOURCEID, value, null);
	}

	/**
	  * (23) get String attribute ResourceID
	  * @return the value of the attribute
	  */
	public String getResourceID()
	{
		return getAttribute(AttributeName.RESOURCEID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ResourceName
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ResourceName
	  * @param value the value to set the attribute to
	  */
	public void setResourceName(String value)
	{
		setAttribute(AttributeName.RESOURCENAME, value, null);
	}

	/**
	  * (23) get String attribute ResourceName
	  * @return the value of the attribute
	  */
	public String getResourceName()
	{
		return getAttribute(AttributeName.RESOURCENAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Scope
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute Scope
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setScope(EnumScope enumVar)
	{
		setAttribute(AttributeName.SCOPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute Scope
	  * @return the value of the attribute
	  */
	public EnumScope getScope()
	{
		return EnumScope.getEnum(getAttribute(AttributeName.SCOPE, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Status
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute Status
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setResStatus(JDFResource.EnumResStatus enumVar)
	{
		setAttribute(AttributeName.STATUS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute Status
	  * @return the value of the attribute
	  */
	public JDFResource.EnumResStatus getResStatus()
	{
		return JDFResource.EnumResStatus.getEnum(getAttribute(AttributeName.STATUS, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Unit
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Unit
	  * @param value the value to set the attribute to
	  */
	public void setUnit(String value)
	{
		setAttribute(AttributeName.UNIT, value, null);
	}

	/**
	  * (23) get String attribute Unit
	  * @return the value of the attribute
	  */
	public String getUnit()
	{
		return getAttribute(AttributeName.UNIT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Usage
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute Usage
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setUsage(JDFResourceLink.EnumUsage enumVar)
	{
		setAttribute(AttributeName.USAGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute Usage
	  * @return the value of the attribute
	  */
	public JDFResourceLink.EnumUsage getUsage()
	{
		return JDFResourceLink.EnumUsage.getEnum(getAttribute(AttributeName.USAGE, null, null));
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element AmountPool
	 * @return JDFAmountPool the element
	 */
	public JDFAmountPool getAmountPool()
	{
		return (JDFAmountPool) getElement(ElementName.AMOUNTPOOL, null, 0);
	}

	/** (25) getCreateAmountPool
	 * 
	 * @return JDFAmountPool the element
	 */
	public JDFAmountPool getCreateAmountPool()
	{
		return (JDFAmountPool) getCreateElement_KElement(ElementName.AMOUNTPOOL, null, 0);
	}

	/**
	 * (29) append element AmountPool
	 * @return JDFAmountPool the element
	 * @throws JDFException if the element already exists
	 */
	public JDFAmountPool appendAmountPool() throws JDFException
	{
		return (JDFAmountPool) appendElementN(ElementName.AMOUNTPOOL, 1, null);
	}

	/**
	 * (24) const get element CostCenter
	 * @return JDFCostCenter the element
	 */
	public JDFCostCenter getCostCenter()
	{
		return (JDFCostCenter) getElement(ElementName.COSTCENTER, null, 0);
	}

	/** (25) getCreateCostCenter
	 * 
	 * @return JDFCostCenter the element
	 */
	public JDFCostCenter getCreateCostCenter()
	{
		return (JDFCostCenter) getCreateElement_KElement(ElementName.COSTCENTER, null, 0);
	}

	/**
	 * (29) append element CostCenter
	 * @return JDFCostCenter the element
	 * @throws JDFException if the element already exists
	 */
	public JDFCostCenter appendCostCenter() throws JDFException
	{
		return (JDFCostCenter) appendElementN(ElementName.COSTCENTER, 1, null);
	}

	/** (26) getCreateLot
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFLot the element
	 */
	public JDFLot getCreateLot(int iSkip)
	{
		return (JDFLot) getCreateElement_KElement(ElementName.LOT, null, iSkip);
	}

	/**
	 * (27) const get element Lot
	 * @param iSkip number of elements to skip
	 * @return JDFLot the element
	 * default is getLot(0)     */
	public JDFLot getLot(int iSkip)
	{
		return (JDFLot) getElement(ElementName.LOT, null, iSkip);
	}

	/**
	 * Get all Lot from the current element
	 * 
	 * @return Collection<JDFLot>, null if none are available
	 */
	public Collection<JDFLot> getAllLot()
	{
		return getChildrenByClass(JDFLot.class, false, 0);
	}

	/**
	 * (30) append element Lot
	 * @return JDFLot the element
	 */
	public JDFLot appendLot()
	{
		return (JDFLot) appendElement(ElementName.LOT, null);
	}

	/**
	 * (24) const get element MISDetails
	 * @return JDFMISDetails the element
	 */
	public JDFMISDetails getMISDetails()
	{
		return (JDFMISDetails) getElement(ElementName.MISDETAILS, null, 0);
	}

	/** (25) getCreateMISDetails
	 * 
	 * @return JDFMISDetails the element
	 */
	public JDFMISDetails getCreateMISDetails()
	{
		return (JDFMISDetails) getCreateElement_KElement(ElementName.MISDETAILS, null, 0);
	}

	/**
	 * (29) append element MISDetails
	 * @return JDFMISDetails the element
	 * @throws JDFException if the element already exists
	 */
	public JDFMISDetails appendMISDetails() throws JDFException
	{
		return (JDFMISDetails) appendElementN(ElementName.MISDETAILS, 1, null);
	}

	/** (26) getCreatePart
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPart the element
	 */
	public JDFPart getCreatePart(int iSkip)
	{
		return (JDFPart) getCreateElement_KElement(ElementName.PART, null, iSkip);
	}

	/**
	 * (27) const get element Part
	 * @param iSkip number of elements to skip
	 * @return JDFPart the element
	 * default is getPart(0)     */
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
		return getChildrenByClass(JDFPart.class, false, 0);
	}

	/**
	 * (30) append element Part
	 * @return JDFPart the element
	 */
	public JDFPart appendPart()
	{
		return (JDFPart) appendElement(ElementName.PART, null);
	}

}// end namespace JDF
