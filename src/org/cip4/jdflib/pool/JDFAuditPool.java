/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of
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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFAuditPool.java
 *
 * -------------------------------------------------------------------------------------------------
 *
 * Last changes
 *
 * 2002-07-02 JG renamed JDFProcess to JDFNode
 * 2002-07-02 JG SetPhase modified first parameter to be JDFPhaseTime::EnumNodeStatus
 * 2002-07-02 JG removed IsValid 
 * 2002-07-02 JG getAudits added const mAttribute &mAttributes=mAttribute() also fixed inversion logic bug
 * 2002-07-02 JG getAudit modified 3rd parameter to const mAttribute &mAttributes=mAttribute()
 * 2002-07-02 JG remove getPoolChildName
 */
package org.cip4.jdflib.pool;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFQueueEntry;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.resource.JDFCreated;
import org.cip4.jdflib.resource.JDFDeleted;
import org.cip4.jdflib.resource.JDFMerged;
import org.cip4.jdflib.resource.JDFModified;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class represents a JDF-AuditPool
 */
public class JDFAuditPool extends JDFPool
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFAuditPool
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFAuditPool(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAuditPool
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFAuditPool(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAuditPool
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFAuditPool(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[9];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.CREATED, 0x33333333);
		elemInfoTable[1] = new ElemInfoTable(ElementName.DELETED, 0x33333333);
		elemInfoTable[2] = new ElemInfoTable(ElementName.MODIFIED, 0x33333333);
		elemInfoTable[3] = new ElemInfoTable(ElementName.NOTIFICATION, 0x33333333);
		elemInfoTable[4] = new ElemInfoTable(ElementName.RESOURCEAUDIT, 0x33333333);
		elemInfoTable[5] = new ElemInfoTable(ElementName.SPAWNED, 0x33333333);
		elemInfoTable[6] = new ElemInfoTable(ElementName.MERGED, 0x33333333);
		elemInfoTable[7] = new ElemInfoTable(ElementName.PHASETIME, 0x33333333);
		elemInfoTable[8] = new ElemInfoTable(ElementName.PROCESSRUN, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return new ElementInfo(super.getTheElementInfo(), elemInfoTable);
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
		return "JDFAuditPool[ -->" + super.toString() + "]";
	}

	/**
	 * 
	 */
	public void ensureCreated()
	{
		final JDFAudit created = getAudit(0, EnumAuditType.Created, null, null);
		if (created == null)
		{
			final JDFAudit a = getAudit(0, null, null, null);
			final JDFCreated c = addCreated(null, null);
			if (a != null)
			{
				c.setTimeStamp(a.getTimeStampDate());
				moveElement(c, a);
			}
		}
	}

	/**
	 * Add a ProcessRun Audit
	 * 
	 * @param s the node status at this time
	 * @param by the author keyword
	 * @return JDFProcessRun the newly created ProcessRun audit
	 * 
	 * default: addProcessRun(s, JDFConstants.EMPTYSTRING)
	 * @deprecated use addProcessRun(JDFElement.EnumNodeStatus s, JDFConstants.EMPTYSTRING, new VJDFAttributeMap())
	 */
	@Deprecated
	public JDFProcessRun addProcessRun(final JDFElement.EnumNodeStatus s, final String by)
	{
		return addProcessRun(s, by, null);
	}

	/**
	 * Add a ProcessRun Audit
	 * 
	 * @param s the node status at this time
	 * @param by the author keyword
	 * @return the newly created ProcessRun audit
	 * 
	 * default: AddProcessRun(s, JDFConstants.EMPTYSTRING)
	 */
	public JDFProcessRun addProcessRun(final JDFElement.EnumNodeStatus s, final String by, final VJDFAttributeMap vmParts)
	{
		final JDFProcessRun pr = (JDFProcessRun) addAudit(JDFAudit.EnumAuditType.ProcessRun, by);
		pr.setStart(null);
		pr.setEnd(null);
		pr.setEndStatus(s);
		pr.setPartMapVector(vmParts);

		return pr;
	}

	/**
	 * add an audit, called internally by the specialized functions
	 * 
	 * @param typ audit type
	 * @param by the author keyword
	 * @return JDFAudit
	 * 
	 * default: AddAudit(typ, JDFConstants.EMPTYSTRING)
	 */
	public JDFAudit addAudit(final JDFAudit.EnumAuditType typ, final String by)
	{
		final JDFAudit l = (JDFAudit) appendElement(typ.getName(), null);
		if (by != null)
		{
			if (EnumUtil.aLessThanB(getVersion(true), EnumVersion.Version_1_4))
			{
				l.setAuthor(by);
			}
			else
			{
				l.appendEmployee().setDescriptiveName(by);
			}
		}
		final JDFNode r = getJDFRoot();
		if (r.hasAttribute(AttributeName.SPAWNID))
		{
			l.setSpawnID(r.getSpawnID(false));
		}

		return l;
	}

	/**
	 * Append a Created audit element, if createdElem==null only add if it is not yet there
	 * 
	 * @param by the author keyword
	 * @param createdElem the created element
	 * @return the newly created Created audit
	 * 
	 * default: AddCreated(by, null)
	 */
	public JDFCreated addCreated(final String by, final KElement createdElem)
	{

		final JDFCreated created = (JDFCreated) addAudit(JDFAudit.EnumAuditType.Created, by);

		if (createdElem != null)
		{
			final String xpath = createdElem.buildXPath(getParentJDF().buildXPath(null, 1), 1);
			created.setXPath(xpath);
		}

		return created;
	}

	/**
	 * Append a Modified audit element
	 * 
	 * @param by the author keyword
	 * @param modifiedElem the modified element
	 * 
	 * default: AddModified(by, null)
	 */
	public JDFModified addModified(final String by, final KElement modifiedElem)
	{
		final JDFModified modified = (JDFModified) addAudit(JDFAudit.EnumAuditType.Modified, by);
		if (modifiedElem != null)
		{
			final String xpath = modifiedElem.buildXPath(getParentJDF().buildXPath(null, 1), 1);
			modified.setXPath(xpath);
		}

		return modified;
	}

	/**
	 * Append a Deleted audit element
	 * 
	 * @param by the author keyword
	 * @param modifiedElem the modified element
	 * @return JDFDeleted the newly created Modified audit
	 * 
	 * default: AddDeleted(null, null)
	 */
	public JDFDeleted addDeleted(final String by, final KElement deletedElem)
	{
		final JDFDeleted deleted = (JDFDeleted) addAudit(JDFAudit.EnumAuditType.Deleted, by);
		if (deletedElem != null)
		{
			final String xpath = deletedElem.buildXPath(getParentJDF().buildXPath(null, 1), 1);
			deleted.setXPath(xpath);
		}

		return deleted;
	}

	/**
	 * append a ResourceAudit audit element
	 * 
	 * @param by the author keyword
	 * 
	 * @return JDFResourceAudit - the newly created ResourceAudit audit, null if an error occured
	 */
	public JDFResourceAudit addResourceAudit(final String by)
	{
		return (JDFResourceAudit) addAudit(JDFAudit.EnumAuditType.ResourceAudit, by);
	}

	/**
	 * add a Notification Audit
	 * 
	 * @param by the author keyword
	 * @param s severity of the event
	 * 
	 * @return JDFAudit - the newly created Notification Audit TODO replace with addNotification
	 */
	public JDFAudit addEvent(final String by, final JDFAudit.EnumSeverity s)
	{
		final JDFAudit l = addAudit(JDFAudit.EnumAuditType.Notification, by);
		l.setSeverity(s);
		return l;
	}

	/**
	 * Append a PhaseTime audit element
	 * 
	 * @param phase the node status at this time
	 * @param by the author keyword
	 * @param vmParts defines a vector of map of parts for which the Spawned is valid
	 * @return the newly created PhaseTime audit
	 * 
	 * default: AddPhaseTime(phase, JDFConstants.EMPTYSTRING, new VJDFAttributeMap())
	 */
	public JDFPhaseTime addPhaseTime(final EnumNodeStatus phase, final String by, final VJDFAttributeMap vmParts)
	{
		final JDFPhaseTime myAudit = (JDFPhaseTime) addAudit(JDFAudit.EnumAuditType.PhaseTime, by);
		myAudit.setStatus(phase);
		myAudit.setStart(new JDFDate());
		myAudit.setEnd(new JDFDate());
		myAudit.setPartMapVector(vmParts);
		final JDFNode parentJDF = getParentJDF();
		if (parentJDF != null)
		{
			myAudit.setSpawnID(StringUtil.getNonEmpty(parentJDF.getSpawnID(true)));
		}

		return myAudit;
	}

	/**
	 * Append a Spawned audit element
	 * 
	 * @param spawned the spawned node
	 * @param rRefsRO a vector of rRefs that are spawned read-only
	 * @param rRefsRW a vector of rRefs that are spawned read-write
	 * @param by the author keyword
	 * @param vmParts
	 * 
	 * @return JDFAudit - the newly created Spawned audit
	 * 
	 * default: AddSpawned(spawned, new Vector(), new Vector(), JDFConstants.EMPTYSTRING, new VJDFAttributeMap())
	 */
	public JDFSpawned addSpawned(final JDFNode spawned, final VString rRefsRO, final VString rRefsRW, final String by, final VJDFAttributeMap vmParts)
	{
		final JDFSpawned a = (JDFSpawned) addAudit(JDFAudit.EnumAuditType.Spawned, by);
		a.setAttribute(JDFConstants.JREF, spawned.getID(), null);
		String ms = null;
		if (rRefsRO != null && !rRefsRO.isEmpty())
		{
			ms = StringUtil.setvString(rRefsRO);
			a.setAttribute(AttributeName.RREFSROCOPIED, ms, null);
		}

		if (rRefsRW != null && !rRefsRW.isEmpty())
		{
			ms = StringUtil.setvString(rRefsRW);
			a.setAttribute(AttributeName.RREFSRWCOPIED, ms, null);
		}

		a.setPartMapVector(vmParts);

		return a;
	}

	/**
	 * Append a Merged audit element
	 * 
	 * @param merged the merged node
	 * @param rRefsOverwritten a vector of rRefs that are overwritten
	 * @param by the author keyword
	 * 
	 * @return JDFMerged - the newly created Merged audit
	 * 
	 * default: AddMerged(merged, rRefsOverwritten, JDFConstants.EMPTYSTRING, null)
	 */
	public JDFMerged addMerged(final JDFNode merged, final VString rRefsOverwritten, final String by, final VJDFAttributeMap vmParts)
	{
		VString rRefsOverwrittenLocal = rRefsOverwritten;

		final JDFMerged mergedAudit = (JDFMerged) addAudit(JDFAudit.EnumAuditType.Merged, by);
		mergedAudit.setjRef(merged.getID());
		if (rRefsOverwrittenLocal != null && rRefsOverwrittenLocal.isEmpty())
		{
			rRefsOverwrittenLocal = null;
		}
		mergedAudit.setrRefsOverwritten(rRefsOverwrittenLocal);

		mergedAudit.setPartMapVector(vmParts);
		return mergedAudit;
	}

	/**
	 * Append a Notification audit element with a Class attribute of Severity
	 * 
	 * @param by the author keyword
	 * @param s the severity
	 * 
	 * @return JDFAudit - the newly created Notification audit
	 */
	public JDFNotification addNotification(final JDFNotification.EnumClass severity, final String by, final VJDFAttributeMap vmParts)
	{
		final JDFNotification l = (JDFNotification) addAudit(JDFAudit.EnumAuditType.Notification, by);
		if (l != null)
		{
			if (severity != null)
			{
				l.setClass(severity);
			}
			l.setPartMapVector(vmParts);
		}
		return l;
	}

	/**
	 * getLastPhase - get the most recent PhaseTime audit in this pool
	 * 
	 * @deprecated use getLastPhase(VJDFAttributeMap)
	 * @return JDFAudit - the last PhaseTime audit
	 */
	@Deprecated
	public JDFPhaseTime getLastPhase()
	{
		return getLastPhase(null, null);
	}

	/**
	 * getLastPhase - get the most recent PhaseTime audit in this pool
	 * 
	 * @param vPartMap the list of matching partMaps
	 * @return JDFAudit - the last PhaseTime audit
	 * @deprecated use getLastPhase(vPartMap, null)
	 */
	@Deprecated
	public JDFPhaseTime getLastPhase(final VJDFAttributeMap vPartMap)
	{
		return getLastPhase(vPartMap, null);
	}

	/**
	 * getLastPhase - get the most recent PhaseTime audit in this pool
	 * 
	 * @param vPartMap the list of matching partMaps
	 * @return JDFAudit - the last PhaseTime audit
	 */
	public JDFPhaseTime getLastPhase(final VJDFAttributeMap vPartMap, final String moduleID)
	{
		if (KElement.isWildCard(moduleID))
		{
			return (JDFPhaseTime) getAudit(-1, EnumAuditType.PhaseTime, null, vPartMap);
		}

		final VElement e = getAudits(EnumAuditType.PhaseTime, null, vPartMap);
		if (e != null)
		{
			final int size = e.size() - 1;
			for (int i = size; i >= 0; i--)
			{
				final JDFPhaseTime pt = (JDFPhaseTime) e.elementAt(i);
				if (pt.getChildWithAttribute(ElementName.MODULEPHASE, AttributeName.MODULEID, null, moduleID, 0, true) != null)
				{
					return pt;
				}
			}
		}

		return null;
	}

	/**
	 * getAudits - get all audits with attributes and partMap
	 * 
	 * @param typ type of the audit to take
	 * @param mAttributes attribute map to filter the audits
	 * 
	 * @return VElement - all elements, that matches the filter
	 * 
	 * default: getAudits(null, null)
	 * @deprecated use getAudits(null, null, null)
	 */
	@Deprecated
	public VElement getAudits(final JDFAudit.EnumAuditType typ, final JDFAttributeMap mAttributes)
	{
		return getAudits(typ, mAttributes, null);
	}

	/**
	 * } getAudits - get all audits with attributes and partMap
	 * 
	 * @param typ type of the audit to take
	 * @param mAttributes attribute map to filter the audits
	 * @param vParts the partmap vector - note that not all audits legally have parts
	 * @return VElement - all elements, that matches the filter
	 * 
	 * default: getAudits(null, null, null)
	 */
	public VElement getAudits(final JDFAudit.EnumAuditType typ, final JDFAttributeMap mAttributes, final VJDFAttributeMap vParts)
	{
		VJDFAttributeMap vPartsLocal = vParts;
		String strAuditType = null;
		if (typ != null)
		{
			strAuditType = typ.getName();
		}

		final VElement vElem = getPoolChildrenGeneric(strAuditType, mAttributes, null);
		if (vPartsLocal != null && vPartsLocal.size() == 0)
		{
			vPartsLocal = null;
		}

		for (int i = vElem.size() - 1; i >= 0; i--)
		{ // remove known comments - this would be aught in the next check but
			// we avoid the exception
			if (!(vElem.elementAt(i) instanceof JDFAudit))
			{
				vElem.removeElementAt(i);
				continue; // look at next element
			}

			final JDFAudit audit = (JDFAudit) vElem.elementAt(i);
			if (vPartsLocal != null && !vPartsLocal.equals(audit.getPartMapVector()))
			{
				vElem.removeElementAt(i);
				continue; // look at next element
			}
		}
		return vElem;
	}

	/**
	 * get the index'th audit of the given typ
	 * 
	 * @param index index of the audit negativ values are possible and will be substracted from the vector size. For example, your given Filter returns a Vector
	 * of 10 Posible Elements and your index is -7 you will get 10 - 7 = Element Number 3
	 * @param typ type of the audit to take
	 * @param mAttributes attribute map to filter the audits
	 * @return an Audit that matches the filers
	 * 
	 * default: getAudit(index, typ, null)
	 * @deprecated use 4 parameter version
	 */
	@Deprecated
	public JDFAudit getAudit(final int index, final JDFAudit.EnumAuditType typ, final JDFAttributeMap mAttributes)
	{
		return getAudit(index, typ, mAttributes, null);
	}

	/**
	 * get the index'th audit of the given typ
	 * 
	 * @param index index of the audit negativ values are possible and will be substracted from the vector size. For example,your given Filter returns a Vector
	 * of 10 Posible Elements and your index is -7 you will get 10 - 7 = Element Number 3
	 * @param typ type of the audit to take
	 * @param mAttributes attribute map to filter the audits
	 * @param vParts the partmap vector - note that not all audits legally have parts
	 * @return an Audit that matches the filers
	 * 
	 * default: getAudit(index, typ, null)
	 */
	public JDFAudit getAudit(final int index, final JDFAudit.EnumAuditType typ, final JDFAttributeMap mAttributes, final VJDFAttributeMap vParts)
	{
		int indexLocal = index;

		final VElement v = getAudits(typ, mAttributes, vParts);
		if (indexLocal < 0)
		{
			indexLocal = v.size() + indexLocal;
		}
		if (indexLocal >= v.size() || indexLocal < 0)
		{
			return null;
		}

		return (JDFAudit) v.elementAt(indexLocal);
	}

	/**
	 * finds all status messages in a jmf and fills the phaseTime with the appropriate data
	 * 
	 * @param jmf
	 * @return vector the vector of all modified phasetime elements
	 */
	public VElement setPhase(final JDFJMF jmf)
	{
		final VElement vMessages = jmf.getMessageVector(null, EnumType.Status);
		if (vMessages == null)
		{
			return null;
		}
		final VElement vRet = new VElement();
		for (int i = 0; i < vMessages.size(); i++)
		{
			final JDFMessage status = (JDFMessage) vMessages.elementAt(i);
			final VElement devInfos = status.getChildElementVector(ElementName.DEVICEINFO, null, null, true, 0, true);
			for (int j = 0; j < devInfos.size(); j++)
			{
				final JDFDeviceInfo devInfo = (JDFDeviceInfo) devInfos.elementAt(j);
				final VElement phases = devInfo.getChildElementVector(ElementName.JOBPHASE, null, null, true, 0, true);
				for (int k = 0; k < phases.size(); k++)
				{
					final JDFJobPhase phase = (JDFJobPhase) phases.elementAt(k);
					final String jobID = phase.getJobID();
					if (!jobID.equals(getParentJDF().getJobID(true)))
					{
						continue;
					}
					final String jobPartID = phase.getJobPartID();
					if (!jobPartID.equals(getParentJDF().getJobPartID(true)))
					{
						continue;
					}

					final JDFPhaseTime pt = setPhase(phase.getStatus(), phase.getStatusDetails(), phase.getPartMapVector(), devInfo.getChildElementVector(ElementName.EMPLOYEE, null));
					pt.copyElement(phase.getMISDetails(), null);
					pt.setEnd(jmf.getTimeStamp());
					pt.setStart(phase.getPhaseStartTime());
					vRet.add(pt);
				}
			}
		}
		vRet.unify();
		return vRet.size() == 0 ? null : vRet;

	}

	/**
	 * Create or modify a PhaseTime Audit and fill it If the phase is identical to the prior phase that has been set, the existing PhaseTime is modified
	 * otherwise an existing phaseTime is closed and a new phaseTime is appended Phasetime elements with different Parts are treated independently
	 * 
	 * @param status the node status at this time
	 * @param statusDetails details of this status
	 * @param vmParts defines a vector of map of parts for which the PhaseTime is valid
	 * @return JDFPhaseTime the newly created PhaseTime audit
	 * 
	 * default: SetPhase(status, null,null,null)
	 * @deprecated use the 4 parameter version
	 */
	@Deprecated
	public JDFPhaseTime setPhase(final EnumNodeStatus status, final String statusDetails, final VJDFAttributeMap vmParts)
	{
		return setPhase(status, statusDetails, vmParts, null);
	}

	/**
	 * Create or modify a PhaseTime Audit and fill it If the phase is identical to the prior phase that has been set, the existing PhaseTime is modified
	 * otherwise an existing phaseTime is closed and a new phaseTime is appended Phasetime elements with different Parts are treated independently
	 * 
	 * @param status the node status at this time
	 * @param statusDetails details of this status
	 * @param vmParts defines a vector of map of parts for which the PhaseTime is valid
	 * @param employees Vector of employees that are currently registered for this job
	 * @return JDFPhaseTime the newly created PhaseTime audit
	 * 
	 * default: SetPhase(status, null,null,null)
	 */
	public JDFPhaseTime setPhase(final EnumNodeStatus status, final String statusDetails, final VJDFAttributeMap vmParts, final VElement employees)
	{
		String statusDetailsLocal = statusDetails;

		JDFPhaseTime pt = getLastPhase(vmParts, null);
		statusDetailsLocal = StringUtil.getNonEmpty(statusDetailsLocal);
		boolean bChanged = false;
		final VElement ptEmployees = pt == null ? new VElement() : pt.getChildElementVector(ElementName.EMPLOYEE, null);
		if (pt == null)
		{
			bChanged = true;
		}
		else if (!ContainerUtil.equals(pt.getStatus(), status) || !ContainerUtil.equals(statusDetailsLocal, pt.getAttribute(AttributeName.STATUSDETAILS, null, null))
				|| !ptEmployees.isEqual(employees))
		{
			pt.setEnd(new JDFDate());
			bChanged = true;
		}
		if (bChanged)
		{
			pt = addPhaseTime(status, null, vmParts);
			pt.setStatusDetails(statusDetailsLocal);
			pt.copyElements(employees, null);
		}
		return pt;
	}

	/**
	 * get the linked resources matching some conditions
	 * 
	 * @param mResAtt map of Resource attributes to search for
	 * @param bFollowRefs true if internal references shall be followed
	 * @return VElement vector with all elements matching the conditions
	 * 
	 * default: getLinkedResources(null, true)
	 */
	public VElement getLinkedResources(final JDFAttributeMap mResAtt, final boolean bFollowRefs)
	{
		final VString refs = getHRefs(null, false, true);
		refs.unify();
		final VElement v = new VElement();

		for (int i = 0; i < refs.size(); i++)
		{
			final KElement e = getTarget(refs.elementAt(i), AttributeName.ID);
			if (e != null && e.includesAttributes(mResAtt, true))
			{
				v.addElement(e);
				if (bFollowRefs && (e instanceof JDFElement))
				{
					v.appendUnique(((JDFElement) e).getvHRefRes(bFollowRefs, true));
				}
			}
		}
		return v;
	}

	/**
	 * getLinks - get the links matching mLinkAtt out of the resource pool
	 * 
	 * @param mLinkAtt the attribute to search for
	 * 
	 * @return VElement - vector all all elements matching the condition mLinkAtt
	 * @deprecated 060216 - this seams to have accidentally been added default: getLinks(null)
	 */
	@Deprecated
	public VElement getLinks(final JDFAttributeMap mLinkAtt)
	{
		return getPoolChildrenGeneric(JDFConstants.EMPTYSTRING, mLinkAtt, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Append a new child if no identical child exists
	 * 
	 * @param p the Child to add to the element
	 */
	public void appendUnique(final JDFAudit p)
	{
		appendUniqueGeneric(p);
	}

	/**
	 * Append all children of p for which no identical child exists
	 * 
	 * @param p the Child to add to the element
	 */
	public void appendUnique(final JDFAuditPool p)
	{
		appendUniqueGeneric(p);
	}

	/**
	 * gets all children with the attribute name,mAttrib, nameSpaceURI out of the pool
	 * 
	 * @param strName name of the Child
	 * @param mAttrib an attribute to search for
	 * @return VElement: a vector with all elements in the pool matching the conditions
	 * 
	 * default: getPoolChildren(null,null)
	 */
	public VElement getPoolChildren(final String strName, final JDFAttributeMap mAttrib)
	{
		return getPoolChildrenGeneric(strName, mAttrib, JDFConstants.EMPTYSTRING);
	}

	/**
	 * @param cleanPolicy
	 * @param spawnID
	 * @deprecated use JDFMerge.cleanUpMerge
	 */
	@Deprecated
	public void cleanUpMerge(@SuppressWarnings("unused") final JDFNode.EnumCleanUpMerge cleanPolicy, @SuppressWarnings("unused") final String spawnID) throws NoSuchMethodException
	{
		throw new NoSuchMethodException("use JDFMerge.cleanUpMergeAudits");
		// JDFMerge.cleanUpMergeAudits(this, cleanPolicy, spawnID);
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * Mother of all version fixing routines
	 * 
	 * uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to high
	 * versions but potentially fail when attempting to move from higher to lower versions
	 * 
	 * @param version version that the resulting element should correspond to
	 * @return true if successful
	 */
	@Override
	public boolean fixVersion(final EnumVersion version)
	{
		if (hasAttribute(AttributeName.RREFS))
		{
			removeAttribute(AttributeName.RREFS);
		}
		return super.fixVersion(version);
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * creates a ProcessRun when this is submitted should be called by the receiving device when it initially receives and enqueues the JDF
	 * 
	 * @param qe the queuentry element to copy information from, if null parameters will be genrated on the fly
	 * @return the newly created processRun
	 */
	public JDFProcessRun createSubmitProcessRun(final JDFQueueEntry qe)
	{
		final JDFProcessRun pr = (JDFProcessRun) addAudit(EnumAuditType.ProcessRun, null);
		pr.setSubmissionTime(new JDFDate());
		if (qe != null)
		{
			pr.setPartMapVector(qe.getPartMapVector());
			pr.copyAttribute(AttributeName.QUEUEENTRYID, qe, null, null, null);
			if (qe.hasAttribute(AttributeName.SUBMISSIONTIME))
			{
				pr.copyAttribute(AttributeName.SUBMISSIONTIME, qe, null, null, null);
			}
		}
		if (!pr.hasAttribute(AttributeName.SUBMISSIONTIME))
		{
			pr.setSubmissionTime(new JDFDate());
		}
		if (!pr.hasAttribute(AttributeName.QUEUEENTRYID))
		{
			pr.setAttribute("QueueEntryID", "qe_" + JDFElement.uniqueID(0));
		}
		return pr;

	}

}
