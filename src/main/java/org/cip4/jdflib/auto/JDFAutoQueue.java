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
import org.cip4.jdflib.jmf.JDFQueueEntry;
import org.cip4.jdflib.resource.JDFDevice;

/**
 *****************************************************************************
 * class JDFAutoQueue : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoQueue extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.STATUS, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumQueueStatus.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DEVICEID, 0x22222222, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.QUEUESIZE, 0x33333311, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.DEVICE, 0x33333333);
		elemInfoTable[1] = new ElemInfoTable(ElementName.QUEUEENTRY, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoQueue
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoQueue(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoQueue
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoQueue(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoQueue
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoQueue(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoQueue[  --> " + super.toString() + " ]";
	}

	/**
	 * Enumeration strings for QueueStatus
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumQueueStatus extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumQueueStatus(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumQueueStatus getEnum(String enumName)
		{
			return (EnumQueueStatus) getEnum(EnumQueueStatus.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumQueueStatus getEnum(int enumValue)
		{
			return (EnumQueueStatus) getEnum(EnumQueueStatus.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumQueueStatus.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumQueueStatus.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumQueueStatus.class);
		}

		/**  */
		public static final EnumQueueStatus Blocked = new EnumQueueStatus("Blocked");
		/**  */
		public static final EnumQueueStatus Closed = new EnumQueueStatus("Closed");
		/**  */
		public static final EnumQueueStatus Full = new EnumQueueStatus("Full");
		/**  */
		public static final EnumQueueStatus Running = new EnumQueueStatus("Running");
		/**  */
		public static final EnumQueueStatus Waiting = new EnumQueueStatus("Waiting");
		/**  */
		public static final EnumQueueStatus Held = new EnumQueueStatus("Held");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Status ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Status
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setQueueStatus(EnumQueueStatus enumVar)
	{
		setAttribute(AttributeName.STATUS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Status
	 * 
	 * @return the value of the attribute
	 */
	public EnumQueueStatus getQueueStatus()
	{
		return EnumQueueStatus.getEnum(getAttribute(AttributeName.STATUS, null, null));
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
	 * --------------------------------------------------------------------- Methods for Attribute QueueSize ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute QueueSize
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setQueueSize(int value)
	{
		setAttribute(AttributeName.QUEUESIZE, value, null);
	}

	/**
	 * (15) get int attribute QueueSize
	 * 
	 * @return int the value of the attribute
	 */
	public int getQueueSize()
	{
		return getIntAttribute(AttributeName.QUEUESIZE, null, 0);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateDevice
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDevice the element
	 */
	public JDFDevice getCreateDevice(int iSkip)
	{
		return (JDFDevice) getCreateElement_JDFElement(ElementName.DEVICE, null, iSkip);
	}

	/**
	 * (27) const get element Device
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDevice the element default is getDevice(0)
	 */
	public JDFDevice getDevice(int iSkip)
	{
		return (JDFDevice) getElement(ElementName.DEVICE, null, iSkip);
	}

	/**
	 * Get all Device from the current element
	 * 
	 * @return Collection<JDFDevice>, null if none are available
	 */
	public Collection<JDFDevice> getAllDevice()
	{
		return getChildrenByClass(JDFDevice.class, false, 0);
	}

	/**
	 * (30) append element Device
	 * 
	 * @return JDFDevice the element
	 */
	public JDFDevice appendDevice()
	{
		return (JDFDevice) appendElement(ElementName.DEVICE, null);
	}

	/**
	 * (26) getCreateQueueEntry
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFQueueEntry the element
	 */
	public JDFQueueEntry getCreateQueueEntry(int iSkip)
	{
		return (JDFQueueEntry) getCreateElement_JDFElement(ElementName.QUEUEENTRY, null, iSkip);
	}

	/**
	 * (27) const get element QueueEntry
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFQueueEntry the element default is getQueueEntry(0)
	 */
	public JDFQueueEntry getQueueEntry(int iSkip)
	{
		return (JDFQueueEntry) getElement(ElementName.QUEUEENTRY, null, iSkip);
	}

	/**
	 * Get all QueueEntry from the current element
	 * 
	 * @return Collection<JDFQueueEntry>, null if none are available
	 */
	public Collection<JDFQueueEntry> getAllQueueEntry()
	{
		return getChildrenByClass(JDFQueueEntry.class, false, 0);
	}

	/**
	 * (30) append element QueueEntry
	 * 
	 * @return JDFQueueEntry the element
	 */
	public JDFQueueEntry appendQueueEntry()
	{
		return (JDFQueueEntry) appendElement(ElementName.QUEUEENTRY, null);
	}

}// end namespace JDF
