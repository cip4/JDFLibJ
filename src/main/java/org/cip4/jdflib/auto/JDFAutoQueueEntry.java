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
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.node.JDFNode.EActivation;
import org.cip4.jdflib.node.JDFNode.EnumActivation;
import org.cip4.jdflib.resource.JDFGangSource;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoQueueEntry : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoQueueEntry extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[15];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTIVATION, 0x3333311111l, AttributeInfo.EnumAttributeType.enumeration, EnumActivation.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DEVICEID, 0x3333333311l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ENDTIME, 0x3333333311l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.GANGNAME, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.GANGPOLICY, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, EnumGangPolicy.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.JOBID, 0x3333333333l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.JOBPARTID, 0x3333333333l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.PRIORITY, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, "1");
		atrInfoTable[8] = new AtrInfoTable(AttributeName.QUEUEENTRYID, 0x2222222222l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.RELATEDJOBID, 0x3331111111l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.RELATEDJOBPARTID, 0x3331111111l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.STATUS, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration, EnumQueueEntryStatus.getEnum(0), null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.STARTTIME, 0x3333333331l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.STATUSDETAILS, 0x3333311111l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.SUBMISSIONTIME, 0x3333333333l, AttributeInfo.EnumAttributeType.dateTime, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.JOBPHASE, 0x3333333311l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.GANGSOURCE, 0x3333111111l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.PART, 0x3333333311l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoQueueEntry
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoQueueEntry(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoQueueEntry
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoQueueEntry(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoQueueEntry
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoQueueEntry(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for GangPolicy
	 */

	public enum EGangPolicy
	{
		Gang, GangAndForce, NoGang;

		public static EGangPolicy getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EGangPolicy.class, val, null);
		}
	}

	/**
	 * Enumeration strings for GangPolicy
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumGangPolicy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumGangPolicy(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumGangPolicy getEnum(String enumName)
		{
			return (EnumGangPolicy) getEnum(EnumGangPolicy.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumGangPolicy getEnum(int enumValue)
		{
			return (EnumGangPolicy) getEnum(EnumGangPolicy.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumGangPolicy.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumGangPolicy.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumGangPolicy.class);
		}

		/**  */
		public static final EnumGangPolicy Gang = new EnumGangPolicy("Gang");
		/**  */
		public static final EnumGangPolicy GangAndForce = new EnumGangPolicy("GangAndForce");
		/**  */
		public static final EnumGangPolicy NoGang = new EnumGangPolicy("NoGang");
	}

	/**
	 * Enumeration strings for QueueEntryStatus
	 */

	public enum EQueueEntryStatus
	{
		Running, Waiting, Held, Removed, Suspended, PendingReturn, Completed, Aborted;

		public static EQueueEntryStatus getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EQueueEntryStatus.class, val, null);
		}
	}

	/**
	 * Enumeration strings for QueueEntryStatus
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumQueueEntryStatus extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumQueueEntryStatus(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumQueueEntryStatus getEnum(String enumName)
		{
			return (EnumQueueEntryStatus) getEnum(EnumQueueEntryStatus.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumQueueEntryStatus getEnum(int enumValue)
		{
			return (EnumQueueEntryStatus) getEnum(EnumQueueEntryStatus.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumQueueEntryStatus.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumQueueEntryStatus.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumQueueEntryStatus.class);
		}

		/**  */
		public static final EnumQueueEntryStatus Running = new EnumQueueEntryStatus("Running");
		/**  */
		public static final EnumQueueEntryStatus Waiting = new EnumQueueEntryStatus("Waiting");
		/**  */
		public static final EnumQueueEntryStatus Held = new EnumQueueEntryStatus("Held");
		/**  */
		public static final EnumQueueEntryStatus Removed = new EnumQueueEntryStatus("Removed");
		/**  */
		public static final EnumQueueEntryStatus Suspended = new EnumQueueEntryStatus("Suspended");
		/**  */
		public static final EnumQueueEntryStatus PendingReturn = new EnumQueueEntryStatus("PendingReturn");
		/**  */
		public static final EnumQueueEntryStatus Completed = new EnumQueueEntryStatus("Completed");
		/**  */
		public static final EnumQueueEntryStatus Aborted = new EnumQueueEntryStatus("Aborted");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Activation ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Activation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setActivation(EActivation enumVar)
	{
		setAttribute(AttributeName.ACTIVATION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Activation
	 *
	 * @return the value of the attribute
	 */
	public EActivation getEActivation()
	{
		return EActivation.getEnum(getAttribute(AttributeName.ACTIVATION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Activation ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Activation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setActivation(EnumActivation enumVar)
	{
		setAttribute(AttributeName.ACTIVATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Activation
	 *
	 * @return the value of the attribute
	 */
	public EnumActivation getActivation()
	{
		return EnumActivation.getEnum(getAttribute(AttributeName.ACTIVATION, null, null));
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
	 * --------------------------------------------------------------------- Methods for Attribute GangName ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute GangName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setGangName(String value)
	{
		setAttribute(AttributeName.GANGNAME, value, null);
	}

	/**
	 * (23) get String attribute GangName
	 *
	 * @return the value of the attribute
	 */
	public String getGangName()
	{
		return getAttribute(AttributeName.GANGNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute GangPolicy ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute GangPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setGangPolicy(EGangPolicy enumVar)
	{
		setAttribute(AttributeName.GANGPOLICY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute GangPolicy
	 *
	 * @return the value of the attribute
	 */
	public EGangPolicy getEGangPolicy()
	{
		return EGangPolicy.getEnum(getAttribute(AttributeName.GANGPOLICY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute GangPolicy ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute GangPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setGangPolicy(EnumGangPolicy enumVar)
	{
		setAttribute(AttributeName.GANGPOLICY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute GangPolicy
	 *
	 * @return the value of the attribute
	 */
	public EnumGangPolicy getGangPolicy()
	{
		return EnumGangPolicy.getEnum(getAttribute(AttributeName.GANGPOLICY, null, null));
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
	 * --------------------------------------------------------------------- Methods for Attribute Priority ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Priority
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPriority(int value)
	{
		setAttribute(AttributeName.PRIORITY, value, null);
	}

	/**
	 * (15) get int attribute Priority
	 *
	 * @return int the value of the attribute
	 */
	public int getPriority()
	{
		return getIntAttribute(AttributeName.PRIORITY, null, 1);
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
	 * --------------------------------------------------------------------- Methods for Attribute RelatedJobID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RelatedJobID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRelatedJobID(String value)
	{
		setAttribute(AttributeName.RELATEDJOBID, value, null);
	}

	/**
	 * (23) get String attribute RelatedJobID
	 *
	 * @return the value of the attribute
	 */
	public String getRelatedJobID()
	{
		return getAttribute(AttributeName.RELATEDJOBID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RelatedJobPartID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RelatedJobPartID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRelatedJobPartID(String value)
	{
		setAttribute(AttributeName.RELATEDJOBPARTID, value, null);
	}

	/**
	 * (23) get String attribute RelatedJobPartID
	 *
	 * @return the value of the attribute
	 */
	public String getRelatedJobPartID()
	{
		return getAttribute(AttributeName.RELATEDJOBPARTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Status ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Status
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setQueueEntryStatus(EQueueEntryStatus enumVar)
	{
		setAttribute(AttributeName.STATUS, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Status
	 *
	 * @return the value of the attribute
	 */
	public EQueueEntryStatus getEQueueEntryStatus()
	{
		return EQueueEntryStatus.getEnum(getAttribute(AttributeName.STATUS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Status ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Status
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setQueueEntryStatus(EnumQueueEntryStatus enumVar)
	{
		setAttribute(AttributeName.STATUS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Status
	 *
	 * @return the value of the attribute
	 */
	public EnumQueueEntryStatus getQueueEntryStatus()
	{
		return EnumQueueEntryStatus.getEnum(getAttribute(AttributeName.STATUS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StartTime ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute StartTime
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setStartTime(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.STARTTIME, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute StartTime
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getStartTime()
	{
		final String str = getAttribute(AttributeName.STARTTIME, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
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
	 * --------------------------------------------------------------------- Methods for Attribute SubmissionTime
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute SubmissionTime
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setSubmissionTime(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.SUBMISSIONTIME, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute SubmissionTime
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getSubmissionTime()
	{
		final String str = getAttribute(AttributeName.SUBMISSIONTIME, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

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
	 * (24) const get element GangSource
	 *
	 * @return JDFGangSource the element
	 */
	public JDFGangSource getGangSource()
	{
		return (JDFGangSource) getElement(ElementName.GANGSOURCE, null, 0);
	}

	/**
	 * (25) getCreateGangSource
	 * 
	 * @return JDFGangSource the element
	 */
	public JDFGangSource getCreateGangSource()
	{
		return (JDFGangSource) getCreateElement_JDFElement(ElementName.GANGSOURCE, null, 0);
	}

	/**
	 * (26) getCreateGangSource
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFGangSource the element
	 */
	public JDFGangSource getCreateGangSource(int iSkip)
	{
		return (JDFGangSource) getCreateElement_JDFElement(ElementName.GANGSOURCE, null, iSkip);
	}

	/**
	 * (27) const get element GangSource
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFGangSource the element default is getGangSource(0)
	 */
	public JDFGangSource getGangSource(int iSkip)
	{
		return (JDFGangSource) getElement(ElementName.GANGSOURCE, null, iSkip);
	}

	/**
	 * Get all GangSource from the current element
	 * 
	 * @return Collection<JDFGangSource>, null if none are available
	 */
	public Collection<JDFGangSource> getAllGangSource()
	{
		return getChildArrayByClass(JDFGangSource.class, false, 0);
	}

	/**
	 * (30) append element GangSource
	 *
	 * @return JDFGangSource the element
	 */
	public JDFGangSource appendGangSource()
	{
		return (JDFGangSource) appendElement(ElementName.GANGSOURCE, null);
	}

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
