/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * 07082002 KM moved JDFElement.EnumNodeStatus GetStatus to JDFElement
 * 05092002 KM deleted GetStart() and GetEnd() if you need them use JDFProcessRun methods
 */
package org.cip4.jdflib.core;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class represents a JDF-Audit which handles individual Audit elements
 */
public class JDFAudit extends JDFElement implements Comparator<JDFAudit>
{

	private static final long serialVersionUID = 1L;
	final private static String m_libAgentName = "CIP4 JDF Writer Java";
	final private static String m_libAgentVersion = "1.4a BLD 68";

	// use reasonable defaults
	private static String m_strAgentName = m_libAgentName;
	private static String m_strAgentVersion = m_libAgentVersion;
	private static String m_strAuthor = null;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.AUTHOR, 0x44443333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.SPAWNID, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.AGENTNAME, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.AGENTVERSION, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ID, 0x33333311, AttributeInfo.EnumAttributeType.ID, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.REFID, 0x33333311, AttributeInfo.EnumAttributeType.IDREF, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.TIMESTAMP, 0x33333222, AttributeInfo.EnumAttributeType.dateTime, null, null);
		// 1.4
		atrInfoTable[7] = new AtrInfoTable(AttributeName.QUEUEENTRYID, 0x33331111, AttributeInfo.EnumAttributeType.shortString, null, null);

	}

	/**
	 * @see org.cip4.jdflib.core.JDFElement#getTheAttributeInfo()
	 */
	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.EMPLOYEE, 0x33331111);
	}

	/**
	 * @see org.cip4.jdflib.core.JDFElement#getTheElementInfo()
	 */
	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAudit
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFAudit(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAudit
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFAudit(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFAudit(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * before June 3, 2009
	 */
	@SuppressWarnings("unchecked")
	public static class EnumAuditType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumAuditType(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName
		 * @return
		 */
		public static EnumAuditType getEnum(final String enumName)
		{
			return (EnumAuditType) getEnum(EnumAuditType.class, enumName);
		}

		/**
		 * @param enumValue
		 * @return
		 */
		public static EnumAuditType getEnum(final int enumValue)
		{
			return (EnumAuditType) getEnum(EnumAuditType.class, enumValue);
		}

		/**
		 * @return
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumAuditType.class);
		}

		/**
		 * @return
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumAuditType.class);
		}

		/**
		 * @return
		 */
		public static Iterator iterator()
		{
			return iterator(EnumAuditType.class);
		}

		/**
		 * 
		 */
		public static final EnumAuditType Created = new EnumAuditType("Created");
		/**
		 * 
		 */
		public static final EnumAuditType Modified = new EnumAuditType("Modified");
		/**
		 * 
		 */
		public static final EnumAuditType Deleted = new EnumAuditType("Deleted");
		/**
		 * 
		 */
		public static final EnumAuditType Spawned = new EnumAuditType("Spawned");
		/**
		 * 
		 */
		public static final EnumAuditType Merged = new EnumAuditType("Merged");
		/**
		 * 
		 */
		public static final EnumAuditType Notification = new EnumAuditType("Notification");
		/**
		 * 
		 */
		public static final EnumAuditType PhaseTime = new EnumAuditType("PhaseTime");
		/**
		 * 
		 */
		public static final EnumAuditType ResourceAudit = new EnumAuditType("ResourceAudit");
		/**
		 * 
		 */
		public static final EnumAuditType ProcessRun = new EnumAuditType("ProcessRun");
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * July 20, 2009
	 */
	@SuppressWarnings("unchecked")
	public static final class EnumSeverity extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSeverity(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName
		 * @return
		 */
		public static EnumSeverity getEnum(final String enumName)
		{
			return (EnumSeverity) getEnum(EnumSeverity.class, enumName);
		}

		/**
		 * @param enumValue
		 * @return
		 */
		public static EnumSeverity getEnum(final int enumValue)
		{
			return (EnumSeverity) getEnum(EnumSeverity.class, enumValue);
		}

		/**
		 * @return
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSeverity.class);
		}

		/**
		 * @return
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSeverity.class);
		}

		/**
		 * @return
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSeverity.class);
		}

		/**
		 * 
		 */
		public static final EnumSeverity Event = new EnumSeverity("Event");
		/**
		 * 
		 */
		public static final EnumSeverity Information = new EnumSeverity("Information");
		/**
		 * 
		 */
		public static final EnumSeverity Warning = new EnumSeverity("Warning");
		/**
		 * 
		 */
		public static final EnumSeverity Error = new EnumSeverity("Error");
		/**
		 * 
		 */
		public static final EnumSeverity Fatal = new EnumSeverity("Fatal");
	}

	// **************************************** Methods
	// *********************************************

	/**
	 * toString
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFAudit[ -->" + super.toString() + "]";
	}

	/**
	 * sort by timestamp
	 * @param a1 an audit
	 * @param a2 another audit
	 * @return @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(final JDFAudit a1, final JDFAudit a2)
	{
		final JDFDate d1 = a1.getTimeStampDate();
		final JDFDate d2 = a2.getTimeStampDate();
		return ContainerUtil.compare(d1, d2);
	}

	/**
	 * SetSeverity
	 * @param s
	 */
	public void setSeverity(final EnumSeverity s)
	{
		setAttribute(JDFConstants.SEVERITY, s.getName(), null);
	}

	/**
	 * GetSeverity
	 * @return EnumSeverity
	 */
	public EnumSeverity getSeverity()
	{
		return EnumSeverity.getEnum(getAttribute(JDFConstants.SEVERITY, null, null));
	}

	/**
	 * SetStatus
	 * @param s
	 */
	@Override
	public void setStatus(final JDFElement.EnumNodeStatus s)
	{
		if (s == null)
		{
			throw new JDFException("setStatus setting to null");
		}
		setAttribute(AttributeName.STATUS, s.getName(), null);
	}

	/**
	 * SetEndStatus
	 * @param s
	 */
	public void setEndStatus(final JDFElement.EnumNodeStatus s)
	{
		setAttribute(AttributeName.ENDSTATUS, s.getName(), null);
	}

	/**
	 * GetEndStatus
	 * @return JDFElement.EnumNodeStatus
	 */
	protected JDFElement.EnumNodeStatus getEndStatus()
	{
		return JDFElement.EnumNodeStatus.getEnum(getAttribute(AttributeName.ENDSTATUS, null, null));
	}

	/**
	 * GetAuditType
	 * @return EnumAuditType
	 */
	public EnumAuditType getAuditType()
	{
		final String nam = getLocalName();
		return EnumAuditType.getEnum(nam);
	}

	/**
	 * GetPhase
	 * @deprecated use JDFPhaseTime.getStatus()
	 * @return JDFElement.EnumNodeStatus
	 */
	@Deprecated
	public JDFElement.EnumNodeStatus getPhase()
	{
		if (!getNodeName().equals(ElementName.PHASETIME))
		{
			return null;
		}

		return super.getStatus();
	}

	/**
	 * SetPart
	 * @param m
	 * @deprecated 2005-10-20 - use setPartMap() in the various subclasses instead
	 */
	@Deprecated
	public void setPart(final JDFAttributeMap m)
	{
		super.setPartMap(m);
	}

	/**
	 * get part map vector
	 * @return VJDFAttributeMap: vector of mAttribute, one for each part
	 */
	@Override
	public VJDFAttributeMap getPartMapVector()
	{
		return super.getPartMapVector();
	}

	/**
	 * Set attribute SpawnID
	 * @param value
	 */
	public void setSpawnID(final String value)
	{
		setAttribute(AttributeName.SPAWNID, value, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Set attribute refID
	 * @param value
	 */
	public void setrefID(final String value)
	{
		setAttribute(AttributeName.REFID, value, null);
	}

	/**
	 * Set attribute refID to the ID of previous
	 * @param previous the previous audit that is referenced
	 */
	public void setRef(final JDFAudit previous)
	{
		if (previous != null)
		{
			final String id = previous.appendAnchor(null); // ensure that previous has an id
			setrefID(id);
		}
	}

	/**
	 * SetAuthor
	 * @param author
	 */
	public void setAuthor(final String author)
	{
		setAttribute(AttributeName.AUTHOR, author, null);
	}

	/**
	 * SetBy
	 * @param by
	 * @deprecated 2005-09-01 use setAuthor()
	 */
	@Deprecated
	public void setBy(final String by)
	{
		if (by == null || by.equals(JDFConstants.EMPTYSTRING))
		{
			return;
		}

		setAttribute(AttributeName.AUTHOR, by, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Software
	 * @return String
	 */
	public static String software()
	{
		return m_libAgentName + JDFConstants.BLANK + m_libAgentVersion;
	}

	/**
	 * init
	 * @return boolean
	 */
	@Override
	public boolean init()
	{
		final EnumVersion auditVersion = getVersion(true);
		setAttributeNameTimeStamp(AttributeName.TIMESTAMP, null);
		if (auditVersion == null || auditVersion.getValue() >= EnumVersion.Version_1_2.getValue())
		{
			setAgentName(m_strAgentName);
			setAgentVersion(m_strAgentVersion);
		}
		if (m_strAuthor != null)
		{
			if (EnumUtil.aLessThanB(getVersion(true), EnumVersion.Version_1_4))
			{
				setAuthor(m_strAuthor);
			}
			else
			{
				appendEmployee().setDescriptiveName(m_strAuthor);
			}
		}

		if (auditVersion == null || auditVersion.getValue() >= EnumVersion.Version_1_3.getValue())
		{
			appendAnchor(null);
		}
		return super.init();
	}

	/**
	 * @see org.cip4.jdflib.core.JDFElement#getIDPrefix()
	 */
	@Override
	public String getIDPrefix()
	{
		return "a";
	}

	/**
	 * SetTimeStamp
	 * @deprecated 2005-12-02 use setTimeStamp(null)
	 */
	@Deprecated
	public void setTimeStamp()
	{
		setTimeStamp(null);
	}

	/**
	 * GetTimeStamp
	 * @deprecated use getTimeStampDate as the typed version
	 * @return the String value of TimeStamp
	 */
	@Deprecated
	public String getTimeStamp()
	{
		return getAttribute(AttributeName.TIMESTAMP, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * (12) get JDFDate attribute TimeStamp
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getTimeStampDate()
	{
		final String str = getAttribute(AttributeName.TIMESTAMP, null, null);
		if (str == null)
		{
			return null;
		}
		try
		{
			return new JDFDate(str);
		}
		catch (final DataFormatException dfe)
		{
			throw new JDFException("not a valid date string. Malformed JDF");
		}
	}

	/**
	 * Get string attribute SpawnID
	 * @return
	 */
	public String getSpawnID()
	{
		return getAttribute(AttributeName.SPAWNID, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Get string attribute refID
	 * @return
	 */
	public String getrefID()
	{
		return getAttribute(AttributeName.REFID, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Get string attribute Author
	 * @return
	 */
	public String getAuthor()
	{
		return getAttribute(AttributeName.AUTHOR);
	}

	/**
	 * Set attribute AgentName
	 * @param value
	 */
	public void setAgentName(final String value)
	{
		setAttribute(AttributeName.AGENTNAME, value);
	}

	/**
	 * Get string attribute QueueEntryID
	 * @return
	 */
	public String getQueueEntryID()
	{
		return getAttribute(AttributeName.QUEUEENTRYID);
	}

	/**
	 * Set attribute QueueEntryID
	 * @param value
	 */
	public void setQueueEntryID(final String value)
	{
		setAttribute(AttributeName.QUEUEENTRYID, value);
	}

	/**
	 * Get string attribute AgentName
	 * @return
	 */
	public String getAgentName()
	{
		return getAttribute(AttributeName.AGENTNAME);
	}

	/**
	 * Set attribute AgentVersion
	 * @param value
	 */
	public void setAgentVersion(final String value)
	{
		setAttribute(AttributeName.AGENTVERSION, value);
	}

	/**
	 * Get string attribute AgentVersion
	 * @return
	 */
	public String getAgentVersion()
	{
		return getAttribute(AttributeName.AGENTVERSION);
	}

	/**
	 * Set attribute TimeStamp
	 * @param value
	 */
	public void setTimeStamp(final JDFDate value)
	{
		setAttributeNameTimeStamp(AttributeName.TIMESTAMP, value);
	}

	/**
	 * @return the parent audit pool
	 */
	public JDFAuditPool getAuditPool()
	{
		return (JDFAuditPool) getDeepParent(ElementName.AUDITPOOL, 0);
	}

	/**
	 * create an update audit for this
	 * @return a new audit that updates this
	 */
	public JDFAudit createUpdateAudit()
	{
		final JDFAuditPool pool = getAuditPool();
		if (pool == null)
		{
			return null;
		}
		final JDFAudit copy = (JDFAudit) pool.copyElement(this, null);
		copy.removeAttribute(AttributeName.ID);
		copy.removeAttribute(AttributeName.AGENTNAME);
		copy.removeAttribute(AttributeName.AGENTVERSION);
		copy.init();
		copy.setRef(this);
		return copy;
	}

	/**
	 * get the previous updated audit that was updated by this
	 * @return the audit that was replaced by this
	 */
	public JDFAudit getUpdatedPreviousAudit()
	{
		final String refID = getrefID();
		if (StringUtil.getNonEmpty(refID) == null)
		{
			return null;
		}
		final JDFAuditPool pool = getAuditPool();
		if (pool == null)
		{
			throw new JDFException("Updating audit that is not in an audit pool");
		}
		return (JDFAudit) pool.getChildWithAttribute(null, "ID", null, refID, 0, true);
	}

	/**
	 * Gets the default static AgentName that is used to preset @AgentName when generating a new Audit
	 * @return Returns the m_strAgentName.
	 */
	public static synchronized String getStaticAgentName()
	{
		return m_strAgentName;
	}

	/**
	 * sets the default static AgentName that is used to preset @AgentName when generating a new Audit
	 * @param agentName The m_strAgentName to set.
	 */
	public static synchronized void setStaticAgentName(final String agentName)
	{
		m_strAgentName = agentName;
	}

	/**
	 * Gets the default static Author that is used to preset @AgentName when generating a new Audit
	 * @return Returns the m_Author.
	 */
	public static synchronized String getStaticAuthor()
	{
		return m_strAuthor;
	}

	/**
	 * sets the default static Author that is used to preset @Author when generating a new Audit
	 * @param author The m_strAuthor to set.
	 */
	public static synchronized void setStaticAuthor(final String author)
	{
		m_strAuthor = author;
	}

	/**
	 * gets the default static AgentVersion that is used to preset @AgentName when generating a new Audit
	 * @return sTRING the m_strAgentVersion.
	 */
	public static synchronized String getStaticAgentVersion()
	{
		return m_strAgentVersion;
	}

	/**
	 * Sets the default static AgentVersion that is used to preset @AgentName when generating a new Audit
	 * @param agentVersion The m_strAgentVersion to set.
	 */
	public static synchronized void setStaticAgentVersion(final String agentVersion)
	{
		m_strAgentVersion = agentVersion;
	}

	/**
	 * (26) getCreateEmployee
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFEmployee the element
	 */
	public JDFEmployee getCreateEmployee(final int iSkip)
	{
		return (JDFEmployee) getCreateElement_KElement(ElementName.EMPLOYEE, null, iSkip);
	}

	/**
	 * (27) const get element Employee
	 * @param iSkip number of elements to skip
	 * @return JDFEmployee the element default is getEmployee(0)
	 */
	public JDFEmployee getEmployee(final int iSkip)
	{
		return (JDFEmployee) getElement(ElementName.EMPLOYEE, null, iSkip);
	}

	/**
	 * Get all Employee from the current element
	 * 
	 * @return Collection<JDFEmployee>
	 */
	public Collection<JDFEmployee> getAllEmployee()
	{
		final Vector<JDFEmployee> v = new Vector<JDFEmployee>();
		JDFEmployee kElem = (JDFEmployee) getFirstChildElement(ElementName.EMPLOYEE, null);

		while (kElem != null)
		{
			v.add(kElem);
			kElem = (JDFEmployee) kElem.getNextSiblingElement(ElementName.EMPLOYEE, null);
		}
		return v;
	}

	/**
	 * (30) append element Employee
	 * @return
	 * @throws JDFException
	 */
	public JDFEmployee appendEmployee() throws JDFException
	{
		return (JDFEmployee) appendElement(ElementName.EMPLOYEE, null);
	}
}
