/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of
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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class represents a JDF-Audit which handles individual Audit elements
 */
public class JDFAudit extends JDFElement
{

	private static final long serialVersionUID = 1L;
	final private static String m_libAgentName = "CIP4 JDF Writer Java";
	final private static String m_libAgentVersion = "1.3 BLD 52";

	// use reasonable defaults
	private static String m_strAgentName = m_libAgentName;
	private static String m_strAgentVersion = m_libAgentVersion;
	private static String m_strAuthor = software();

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.AUTHOR, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.SPAWNID, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.AGENTNAME, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.AGENTVERSION, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ID, 0x33333311, AttributeInfo.EnumAttributeType.ID, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.REFID, 0x33333311, AttributeInfo.EnumAttributeType.IDREF, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.TIMESTAMP, 0x33333222, AttributeInfo.EnumAttributeType.dateTime, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAudit
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFAudit(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAudit
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFAudit(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAudit
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFAudit(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	public static class EnumAuditType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumAuditType(String name)
		{
			super(name, m_startValue++);
		}

		public static EnumAuditType getEnum(String enumName)
		{
			return (EnumAuditType) getEnum(EnumAuditType.class, enumName);
		}

		public static EnumAuditType getEnum(int enumValue)
		{
			return (EnumAuditType) getEnum(EnumAuditType.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumAuditType.class);
		}

		public static List getEnumList()
		{
			return getEnumList(EnumAuditType.class);
		}

		public static Iterator iterator()
		{
			return iterator(EnumAuditType.class);
		}

		public static final EnumAuditType Created = new EnumAuditType("Created");
		public static final EnumAuditType Modified = new EnumAuditType("Modified");
		public static final EnumAuditType Deleted = new EnumAuditType("Deleted");
		public static final EnumAuditType Spawned = new EnumAuditType("Spawned");
		public static final EnumAuditType Merged = new EnumAuditType("Merged");
		public static final EnumAuditType Notification = new EnumAuditType("Notification");
		public static final EnumAuditType PhaseTime = new EnumAuditType("PhaseTime");
		public static final EnumAuditType ResourceAudit = new EnumAuditType("ResourceAudit");
		public static final EnumAuditType ProcessRun = new EnumAuditType("ProcessRun");
	}

	public static final class EnumSeverity extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSeverity(String name)
		{
			super(name, m_startValue++);
		}

		public static EnumSeverity getEnum(String enumName)
		{
			return (EnumSeverity) getEnum(EnumSeverity.class, enumName);
		}

		public static EnumSeverity getEnum(int enumValue)
		{
			return (EnumSeverity) getEnum(EnumSeverity.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumSeverity.class);
		}

		public static List getEnumList()
		{
			return getEnumList(EnumSeverity.class);
		}

		public static Iterator iterator()
		{
			return iterator(EnumSeverity.class);
		}

		public static final EnumSeverity Event = new EnumSeverity("Event");
		public static final EnumSeverity Information = new EnumSeverity("Information");
		public static final EnumSeverity Warning = new EnumSeverity("Warning");
		public static final EnumSeverity Error = new EnumSeverity("Error");
		public static final EnumSeverity Fatal = new EnumSeverity("Fatal");
	}

	// **************************************** Methods
	// *********************************************

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFAudit[ -->" + super.toString() + "]";
	}

	/**
	 * SetSeverity
	 * 
	 * @param EnumSeverity s
	 */
	public void setSeverity(EnumSeverity s)
	{
		setAttribute(JDFConstants.SEVERITY, s.getName(), null);
	}

	/**
	 * GetSeverity
	 * 
	 * @return EnumSeverity
	 */
	public EnumSeverity getSeverity()
	{
		return EnumSeverity.getEnum(getAttribute(JDFConstants.SEVERITY, null, null));
	}

	/**
	 * SetStatus
	 * 
	 * @param JDFElement .EnumNodeStatus s
	 */
	@Override
	public void setStatus(JDFElement.EnumNodeStatus s)
	{
		if (s == null)
			throw new JDFException("setStatus setting to null");
		setAttribute(AttributeName.STATUS, s.getName(), null);
	}

	/**
	 * SetEndStatus
	 * 
	 * @param JDFElement .EnumNodeStatus s
	 */
	public void setEndStatus(JDFElement.EnumNodeStatus s)
	{
		setAttribute(AttributeName.ENDSTATUS, s.getName(), null);
	}

	/**
	 * GetEndStatus
	 * 
	 * @return JDFElement.EnumNodeStatus
	 */
	protected JDFElement.EnumNodeStatus getEndStatus()
	{
		return JDFElement.EnumNodeStatus.getEnum(getAttribute(AttributeName.ENDSTATUS, null, null));
	}

	/**
	 * GetAuditType
	 * 
	 * @return EnumAuditType
	 */
	public EnumAuditType getAuditType()
	{
		final String nam = getLocalName();
		return EnumAuditType.getEnum(nam);
	}

	/**
	 * GetPhase
	 * 
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
	 * 
	 * @param JDFAttributeMap m
	 * @deprecated 2005-10-20 - use setPartMap() in the various subclasses instead
	 */
	@Deprecated
	public void setPart(JDFAttributeMap m)
	{
		super.setPartMap(m);
	}

	/**
	 * get part map vector
	 * 
	 * @return VJDFAttributeMap: vector of mAttribute, one for each part
	 */
	@Override
	public VJDFAttributeMap getPartMapVector()
	{
		return super.getPartMapVector();
	}

	/**
	 * Set attribute SpawnID
	 */
	public void setSpawnID(String value)
	{
		setAttribute(AttributeName.SPAWNID, value, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Set attribute refID
	 */
	public void setrefID(String value)
	{
		setAttribute(AttributeName.REFID, value, null);
	}

	/**
	 * Set attribute refID to the ID of previous
	 */
	public void setRef(JDFAudit previous)
	{
		if (previous != null)
		{
			String id = previous.appendAnchor(null); // ensure that previos has
			// an id
			setrefID(id);
		}
	}

	/**
	 * SetAuthor
	 * 
	 * @param String author
	 */
	public void setAuthor(String author)
	{
		setAttribute(AttributeName.AUTHOR, author, null);
	}

	/**
	 * SetID
	 * 
	 * @param String ID
	 */
	public void setID(String id)
	{
		setAttribute(AttributeName.ID, id, null);
	}

	/**
	 * SetBy
	 * 
	 * @param String by
	 * @deprecated 2005-09-01 use setAuthor()
	 */
	@Deprecated
	public void setBy(String by)
	{
		if (by == null || by.equals(JDFConstants.EMPTYSTRING))
		{
			return;
		}

		setAttribute(AttributeName.AUTHOR, by, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Software
	 * 
	 * @return String
	 */
	public static String software()
	{
		return m_libAgentName + JDFConstants.BLANK + m_libAgentVersion;
	}

	/**
	 * init
	 * 
	 * @return boolean
	 */
	@Override
	public boolean init()
	{
		EnumVersion auditVersion = getVersion(true);
		setAttributeNameTimeStamp(AttributeName.TIMESTAMP, null);
		if (auditVersion == null || auditVersion.getValue() >= EnumVersion.Version_1_2.getValue())
		{
			setAgentName(m_strAgentName);
			setAgentVersion(m_strAgentVersion);
		}
		if (m_strAuthor != null)
			setAuthor(m_strAuthor);

		if (auditVersion == null || auditVersion.getValue() >= EnumVersion.Version_1_3.getValue())
		{
			appendAnchor(null);
		}
		return super.init();
	}

	/**
	 * version fixing routine for JDF
	 * 
	 * uses heuristics to modify this element and its children to be compatible with a given version in general, it will
	 * be able to move from low to high versions but potentially fail when attempting to move from higher to lower
	 * versions
	 * 
	 * @param version : version that the resulting element should correspond to
	 * @return true if successful
	 */
	@Override
	public boolean fixVersion(EnumVersion version)
	{
		if (version != null)
		{
			final int value = version.getValue();
			if (value >= EnumVersion.Version_1_3.getValue())
			{
				appendAnchor(null);
			}
			else
			{
				removeAttribute(AttributeName.ID);
				// TODO fix agentname and agentversion
			}
			String author = getAuthor();
			if (value <= EnumVersion.Version_1_1.getValue())
			{
				String tmp = getAgentName();
				boolean b = false;
				if (tmp.length() != 0)
				{
					author += "_|_" + tmp;
					b = true;
				}
				tmp = getAgentVersion();
				if (tmp.length() != 0)
				{
					if (!b)
						author += "_|_ ";

					author += "_|_" + tmp;
					b = true;
				}
				removeAttribute(AttributeName.AGENTNAME);
				removeAttribute(AttributeName.AGENTVERSION);
				if (b)
					setAuthor(author);
			}
			else if (author.length() > 0) // version>=1.2 and has author
			{
				VString tokens = StringUtil.tokenize(author, "_|_", false);
				if (tokens.size() == 3)
				{ // it was previously fixed
					String tmp = tokens.stringAt(0);
					if (!tmp.equals(JDFConstants.EMPTYSTRING) && !tmp.equals(JDFConstants.BLANK))
						setAuthor(tmp);
					tmp = tokens.stringAt(1);
					if (!tmp.equals(JDFConstants.EMPTYSTRING) && !tmp.equals(JDFConstants.BLANK))
						setAgentName(tmp);
					tmp = tokens.stringAt(2);
					if (!tmp.equals(JDFConstants.EMPTYSTRING) && !tmp.equals(JDFConstants.BLANK))
						setAgentVersion(tmp);
				}
			}
		}
		return super.fixVersion(version);
	}

	@Override
	public String getIDPrefix()
	{
		return "a";
	}

	/**
	 * SetTimeStamp
	 * 
	 * @deprecated 2005-12-02 use setTimeStamp(null)
	 */
	@Deprecated
	public void setTimeStamp()
	{
		setTimeStamp(null);
	}

	/**
	 * GetTimeStamp
	 * 
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
	 * 
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getTimeStampDate()
	{
		final String str = getAttribute(AttributeName.TIMESTAMP, null, null);
		if (str == null)
			return null;
		try
		{
			return new JDFDate(str);
		}
		catch (DataFormatException dfe)
		{
			throw new JDFException("not a valid date string. Malformed JDF");
		}
	}

	/**
	 * Get string attribute SpawnID
	 */
	public String getSpawnID()
	{
		return getAttribute(AttributeName.SPAWNID, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Get string attribute refID
	 */
	public String getrefID()
	{
		return getAttribute(AttributeName.REFID, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Get string attribute Author
	 */
	public String getAuthor()
	{
		return getAttribute(AttributeName.AUTHOR);
	}

	/**
	 * Get string attribute ID
	 */
	@Override
	public String getID()
	{
		return getAttribute(AttributeName.ID);
	}

	/**
	 * Set attribute AgentName
	 */
	public void setAgentName(String value)
	{
		setAttribute(AttributeName.AGENTNAME, value);
	}

	/**
	 * Get string attribute AgentName
	 */
	public String getAgentName()
	{
		return getAttribute(AttributeName.AGENTNAME);
	}

	/**
	 * Set attribute AgentVersion
	 */
	public void setAgentVersion(String value)
	{
		setAttribute(AttributeName.AGENTVERSION, value);
	}

	/**
	 * Get string attribute AgentVersion
	 */
	public String getAgentVersion()
	{
		return getAttribute(AttributeName.AGENTVERSION);
	}

	/**
	 * Set attribute TimeStamp
	 */
	public void setTimeStamp(JDFDate value)
	{
		setAttributeNameTimeStamp(AttributeName.TIMESTAMP, value);
	}

	public JDFAuditPool getAuditPool()
	{
		return (JDFAuditPool) getDeepParent(ElementName.AUDITPOOL, 0);
	}

	/**
	 * create an update audit for this
	 * 
	 * @return
	 */
	public JDFAudit createUpdateAudit()
	{
		JDFAuditPool pool = getAuditPool();
		if (pool == null)
			return null;
		JDFAudit copy = (JDFAudit) pool.copyElement(this, null);
		copy.removeAttribute(AttributeName.ID);
		copy.removeAttribute(AttributeName.AGENTNAME);
		copy.removeAttribute(AttributeName.AGENTVERSION);
		copy.init();
		copy.setRef(this);
		return copy;
	}

	/**
	 * Gets the default static AgentName that is used to preset @AgentName when generating a new Audit
	 * 
	 * @return Returns the m_strAgentName.
	 */
	public static synchronized String getStaticAgentName()
	{
		return m_strAgentName;
	}

	/**
	 * sets the default static AgentName that is used to preset @AgentName when generating a new Audit
	 * 
	 * @param agentName The m_strAgentName to set.
	 */
	public static synchronized void setStaticAgentName(String agentName)
	{
		m_strAgentName = agentName;
	}

	/**
	 * Gets the default static Author that is used to preset @AgentName when generating a new Audit
	 * 
	 * @return Returns the m_Author.
	 */
	public static synchronized String getStaticAuthor()
	{
		return m_strAuthor;
	}

	/**
	 * sets the default static AgentName that is used to preset @AgentName when generating a new Audit
	 * 
	 * @param agentName The m_strAgentName to set.
	 */
	public static synchronized void setStaticAuthor(String author)
	{
		m_strAuthor = author;
	}

	/**
	 * gets the default static AgentVersion that is used to preset @AgentName when generating a new Audit
	 * 
	 * @return sTRING the m_strAgentVersion.
	 */
	public static synchronized String getStaticAgentVersion()
	{
		return m_strAgentVersion;
	}

	/**
	 * Sets the default static AgentVersion that is used to preset @AgentName when generating a new Audit
	 * 
	 * @param agentVersion The m_strAgentVersion to set.
	 */
	public static synchronized void setStaticAgentVersion(String agentVersion)
	{
		m_strAgentVersion = agentVersion;
	}
}
