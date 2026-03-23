/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2026 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
/**
 * Created on Jul 5, 2006, 11:45:44 AM org.cip4.jdflib.util.MimeUtil.java Project Name: mimeutil
 */
package org.cip4.jdflib.util;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumDeviceOperationMode;
import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumWorkType;
import org.cip4.jdflib.auto.JDFAutoNotification.EnumClass;
import org.cip4.jdflib.auto.JDFAutoResourceAudit.EnumReason;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFAttributeMapArray;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFResourceQuParams;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.NodeIdentifier;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFUsageCounter;

// TODO add time related metadata

/**
 * Utility class for status JDF and JMF
 *
 * @author prosirai
 */
public class StatusCounter
{

	public enum EAmountType
	{
		PhaseAmount, PhaseWaste, StartAmount, StartWaste, TotalAmount, TotalWaste, Speed, Last
	}

	private double percentComplete = 0;
	private JDFNode m_Node;
	private boolean bCompleted = false;
	private JDFDoc docJMFPhaseTime;
	private VJDFAttributeMap m_vPartMap;
	private VString m_ignoreParts = null;
	private String m_deviceID = null;
	private VString m_moduleID = null;
	private VString m_moduleType = null;
	private final List<LinkAmount> vLinkAmount;
	private String firstRefID = null;
	private String queueEntryID;
	private EnumDeviceOperationMode operationMode = EnumDeviceOperationMode.Productive;
	private EnumWorkType workType = null;
	private final HashSet<String> setTrackWaste = new HashSet<>();
	private final HashSet<String> setCopyResInfo = new HashSet<>();
	private EnumDeviceStatus status = null;
	private String statusDetails = null;
	private JDFDate startDate;
	private NodeIdentifier nodeID = null;
	private final Vector<JDFEmployee> vEmployees = new Vector<>();
	private final List<JDFNotification> events;

	private double totalCounter = -1;
	private VString icsVersions = null;
	private boolean addPhaseTimeAmounts;

	/**
	 * @return the total counter value
	 */
	public double getTotalCounter()
	{
		return totalCounter;
	}

	/**
	 * @param _totalCounter
	 */
	public void setTotalCounter(final double _totalCounter)
	{
		totalCounter = _totalCounter;
	}

	/**
	 * @return the value of currentCounter
	 */
	public double getCurrentCounter()
	{
		return currentCounter;
	}

	/**
	 * @param _currentCounter
	 */
	public void setCurrentCounter(final double _currentCounter)
	{
		this.currentCounter = _currentCounter;
	}

	private double currentCounter = -1;

	/**
	 * @param employee
	 * @return
	 */
	public int addEmployee(final JDFEmployee employee)
	{
		if (employee != null)
		{
			final JDFEmployee eOld = (JDFEmployee) ContainerUtil.getMatch(vEmployees, employee, 0);
			if (eOld == null)
			{
				vEmployees.add(employee);
				resetPhase();
			}
		}
		return vEmployees.size();
	}

	/**
	 * @param employee
	 * @return
	 */
	public boolean removeEmployee(final JDFEmployee employee)
	{
		final JDFEmployee eOld = (JDFEmployee) ContainerUtil.getMatch(vEmployees, employee, 0);
		if (eOld != null)
		{
			final boolean b = vEmployees.remove(employee);
			if (b)
			{
				resetPhase();
			}
			return b;
		}
		return false;
	}

	/**
	 * @return the vector of currently registered employees
	 */
	public Vector<JDFEmployee> getEmpoyees()
	{
		return vEmployees;
	}

	/**
	 * @return
	 */
	public boolean clearEmployees()
	{
		final boolean b = vEmployees.size() > 0;
		vEmployees.clear();
		if (b)
		{
			resetPhase();
		}
		return b;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "[StatusCounter - counter: " + m_deviceID + "Start date: " + startDate + " " + ContainerUtil.toString(vLinkAmount) + "]";
	}

	/**
	 *
	 */
	public void writeAll()
	{
		if (m_Node != null)
		{
			getVResLink(ELinkType.Node); // write all reslinks to disk
		}
	}

	/**
	 * construct a StatusUtil for a node n
	 *
	 * @param node      the JDFNode that is being processed
	 * @param vPartMap  the map of Parts that is being processed excluding the waste partition
	 * @param vResLinks the reslinks that are tracked for amount handling
	 */
	public StatusCounter(final JDFNode node, final VJDFAttributeMap vPartMap, final VElement vResLinks)
	{
		vLinkAmount = new ArrayList<>();
		addPhaseTimeAmounts = true;
		events = new ArrayList<>();
		setActiveNode(node, vPartMap, vResLinks);
	}

	/**
	 * @param bAddAmount if true, amounts are added to phasetimes
	 */
	public void setPhaseTimeAmounts(final boolean bAddAmount)
	{
		addPhaseTimeAmounts = bAddAmount;
	}

	/**
	 * set the currently active node
	 *
	 * @param node      the JDFNode that is being processed
	 * @param vPartMap  the map of Parts that is being processed excluding the waste partition
	 * @param vResLinks the reslinks that are tracked for amount handling
	 */
	public void setActiveNode(final JDFNode node, final VJDFAttributeMap vPartMap, VElement vResLinks)
	{

		if (node == null)
		{
			setTrackWaste.clear();
		}

		bCompleted = false;
		m_Node = node;
		m_vPartMap = vPartMap;
		vLinkAmount.clear();
		firstRefID = null;
		docJMFPhaseTime = null;
		startDate = new JDFDate();
		nodeID = null;
		percentComplete = 0;

		if (node == null)
		{
			setPhase(null, null, EnumDeviceStatus.Idle, EnumDeviceStatus.Idle.getName());
		}

		if (m_vPartMap == null && m_Node != null)
		{
			m_vPartMap = m_Node.getNodeInfoPartMapVector();
		}

		nodeID = node != null ? node.getIdentifier() : null;
		if (m_vPartMap != null && nodeID != null && node != null)
		{
			nodeID.setTo(node.getJobID(true), node.getJobPartID(false), m_vPartMap);
		}

		if (ContainerUtil.isEmpty(vResLinks) && m_Node != null)
		{
			vResLinks = m_Node.getResourceLinks(null);
			if (vResLinks != null)
			{
				final int siz = vResLinks.size();
				for (int i = siz - 1; i >= 0; i--)
				{
					final JDFResourceLink rl = (JDFResourceLink) vResLinks.elementAt(i);
					if (!rl.isPhysical())
					{
						vResLinks.remove(i);
					}
				}
			}
		}

		setUpResLinks(vResLinks);
	}

	/**
	 * get the matching LinkAmount out of this
	 *
	 * @param refID the refID, name or usage of the resource of the bag - this MUST match the refID of a ResourceLink
	 * @return the LinkAmount with matching refID, null if none found or bags is null
	 */
	protected LinkAmount getLinkAmount(String refID)
	{

		if (refID == null)
		{
			refID = getFirstRefID();
		}

		for (final LinkAmount element : vLinkAmount)
		{
			if (element.linkFitsKey(refID))
			{
				return element;
			}
		}

		return null;
	}

	protected LinkAmount getLinkAmount(final String refID, JDFAttributeMap map)
	{
		final LinkAmount la = getLinkAmount(refID);
		return (la != null && la.hasBag(map)) ? la : null;
	}

	/**
	 * get the matching LinkAmount out of this
	 *
	 * @param refID the refID, name or usage of the resource of the bag - this MUST match the refID of a ResourceLink
	 * @return the actual refID of the matching resLink, null if none found or bags is null
	 */
	public String getLinkID(String refID)
	{

		if (refID == null)
		{
			refID = getFirstRefID();
		}

		for (final LinkAmount element : vLinkAmount)
		{
			if (element.linkFitsKey(refID))
			{
				return element.rl.getrRef();
			}
		}

		return null;
	}

	/**
	 * get the matching LinkAmount out of this
	 *
	 * @param n the index of the resource of the bag - this MUST match the refID of a ResourceLink
	 * @return the LinkAmount with matching n, null if none found or bags is null
	 */
	protected LinkAmount getLinkAmount(final int n)
	{
		return ContainerUtil.get(vLinkAmount, n);
	}

	/**
	 * get the refID of the first resource, i.e. the Resource that is being tracked in status messages
	 *
	 * @return the rRef of the prime resource link
	 */
	public String getFirstRefID()
	{
		if (firstRefID != null)
		{
			return firstRefID;
		}
		if (vLinkAmount.isEmpty())
		{
			return null;
		}
		return vLinkAmount.get(0).rl.getrRef();
	}

	/**
	 * set the id of the resource to be tracked in phasetimes, i.e. THE resource that is counted
	 *
	 * @param resID
	 */
	public void setFirstRefID(final String resID)
	{
		firstRefID = resID;
	}

	/**
	 * set the partIDKeys to be ignored
	 *
	 * @param key
	 */
	public void addIgnorePart(final EnumPartIDKey key)
	{
		if (m_ignoreParts == null && key != null)
		{
			m_ignoreParts = new VString();
		}
		if (key == null)
		{
			m_ignoreParts = null;
		}
		else
		{
			m_ignoreParts.add(key.getName());
		}
	}

	/**
	 * @param resLinks
	 */
	private void setUpResLinks(final VElement resLinks)
	{
		if (!ContainerUtil.isEmpty(resLinks))
		{
			vLinkAmount.clear();
			for (final KElement rl : resLinks)
			{
				vLinkAmount.add(new LinkAmount((JDFResourceLink) rl));
			}
		}
	}

	/**
	 * clear all the amounts in the resource with id refID
	 *
	 * @param refID id of the resource
	 */
	public void clearAmounts(final String refID)
	{
		final LinkAmount la = getLinkAmount(refID);
		if (la == null)
		{
			return;
		}
		la.reset();
	}

	/**
	 * add the amount specified by amount and waste to the resource with id refID
	 *
	 * @param refID    , type or usage of the resource, if null all are updated
	 * @param amount
	 * @param waste
	 * @param sumTotal if true, also sum up the total amounts, else only phase
	 */
	public void addPhase(final String refID, final double amount, final double waste, final boolean sumTotal)
	{
		addPhase(refID, amount, waste, sumTotal, null);
	}

	public synchronized void addPhase(final String refID, final double amount, final double waste, final boolean sumTotal, JDFAttributeMap map)
	{
		final LinkAmount la = getLinkAmount(refID);
		if (la == null)
		{
			return;
		}
		la.addPhase(amount, waste, bCompleted, addPhaseTimeAmounts, map);
		if (JDFAttributeMap.isEmpty(map))
		{
			if (sumTotal)
			{
				la.updateSpeed();
			}
			if (amount >= 0)
			{
				updatePercentComplete(la);
			}
		}
	}

	/**
	 * set the phase the amount specified by amount and waste to the resource with id refID
	 *
	 * @param refID  , type or usage of the resource, if null all are updated
	 * @param amount the amount for this phase
	 * @param waste  the waste for this phase
	 */
	public synchronized void setPhase(final String refID, final double amount, final double waste)
	{
		setPhase(refID, amount, waste, null);
	}

	public synchronized void setPhase(final String refID, final double amount, final double waste, JDFAttributeMap map)
	{
		final LinkAmount la = getLinkAmount(refID);
		if (la == null)
		{
			return;
		}
		la.addPhase(amount, waste, true, false, map);
	}

	/**
	 * updates percentcomplete based on la
	 *
	 * @param la
	 */
	void updatePercentComplete(final LinkAmount la)
	{
		if (la == null || la.getAmount(EAmountType.StartAmount) <= 0 || !la.rl.getrRef().equals(getFirstRefID()))
		{
			return;
		}
		setPercentComplete(la.getAmount(EAmountType.TotalAmount) / la.getAmount(EAmountType.StartAmount) * 100.0);

	}

	/**
	 * set the total amount specified by amount and waste to the resource with id refID usually called when reading resource audits or resource signals
	 *
	 * @param refID  , type or usage of the resource, if null all are updated
	 * @param amount the amount to set
	 * @param bWaste if true, set total waste, else set total good
	 */
	public synchronized void setTotal(final String refID, final double amount, final boolean bWaste)
	{
		setTotal(refID, amount, bWaste, null);
	}

	public synchronized void setTotal(String refID, final double amount, final boolean bWaste, JDFAttributeMap map)
	{
		final LinkAmount la = getLinkAmount(refID);
		if (la == null)
		{
			return;
		}
		if (bWaste)
		{
			la.setAmount(EAmountType.TotalWaste, amount, map);
		}
		else
		{
			la.setAmount(EAmountType.TotalAmount, amount, map);
			updatePercentComplete(la);
		}
		la.updateSpeed();
	}

	public double getTotalAmount(final String refID)
	{
		return getTotalAmount(refID, null);
	}

	/**
	 * get the total the amount of the resource with id refID
	 *
	 * @param refID , type or usage of the resource,
	 * @return
	 */
	public double getTotalAmount(final String refID, JDFAttributeMap map)
	{
		final LinkAmount la = getLinkAmount(refID);
		return la == null ? 0 : la.getAmount(EAmountType.TotalAmount, map);
	}

	/**
	 * get all total amounts of all tracked resources
	 *
	 * @return
	 */
	public JDFResourceLink[] getAmountLinks()
	{
		if (ContainerUtil.isEmpty(vLinkAmount))
		{
			return null;
		}
		final JDFResourceLink[] d = new JDFResourceLink[vLinkAmount.size()];
		for (int i = 0; i < d.length; i++)
		{
			d[i] = vLinkAmount.get(i).rl;
		}
		return d;
	}

	/**
	 * @return the percent completed of the current node
	 */
	public double getPercentComplete()
	{
		return percentComplete;
	}

	/**
	 * get the total the amount of the resource with id refID
	 *
	 * @param refID , type or usage of the resource,
	 * @return
	 */
	public double getPhaseAmount(final String refID)
	{
		return getPhaseAmount(refID, null);
	}

	public double getPhaseAmount(final String refID, JDFAttributeMap map)
	{
		final LinkAmount la = getLinkAmount(refID);
		return la == null ? 0 : la.getAmount(EAmountType.PhaseAmount, map);
	}

	/**
	 * get the total the amount of the resource with id refID
	 *
	 * @param refID , type or usage of the resource,
	 * @return
	 */
	public double getTotalWaste(final String refID)
	{
		return getTotalWaste(refID, null);
	}

	public double getTotalWaste(final String refID, JDFAttributeMap map)
	{
		final LinkAmount la = getLinkAmount(refID);
		return la == null ? 0 : la.getAmount(EAmountType.TotalWaste, map);
	}

	/**
	 * get the total the amount of the resource with id refID
	 *
	 * @param refID , type or usage of the resource,
	 * @return
	 */
	public double getPhaseWaste(final String refID)
	{
		return getPhaseWaste(refID, null);
	}

	public double getPhaseWaste(final String refID, JDFAttributeMap map)
	{
		final LinkAmount la = getLinkAmount(refID);
		return la == null ? 0 : la.getAmount(EAmountType.PhaseWaste, map);
	}

	boolean resetPhase()
	{
		if (m_Node == null)
		{
			return setIdlePhase(status, statusDetails);
		}

		final JDFAuditPool ap = m_Node.getCreateAuditPool();
		final JDFPhaseTime lastPhase = ap.getLastPhase(m_vPartMap, m_moduleID == null ? null : m_moduleID.get(0));
		final EnumNodeStatus ns = lastPhase != null ? lastPhase.getStatus() : EnumNodeStatus.Waiting;
		final String nodeStatusDetails = lastPhase != null ? lastPhase.getStatusDetails() : statusDetails;
		return setPhase(ns, nodeStatusDetails, status, statusDetails);
	}

	/**
	 * set event, append the Event element and optionally the comment<br/>
	 * overwrites existing values
	 *
	 * @param eventID    Event/@EventID to set
	 * @param eventValue Event/@EventValue to set
	 * @param comment    the comment text, if null no comment is set
	 * @return
	 */
	public synchronized void setEvent(final String eventID, final String eventValue, final String comment)
	{
		final JDFNotification notif = createBaseNotification();
		notif.setEvent(eventID, eventValue, comment);
		events.add(notif);
	}

	JDFNotification createBaseNotification()
	{
		JDFNotification notif;

		if (m_Node != null)
		{
			final JDFAuditPool ap = m_Node.getCreateAuditPool();
			notif = ap.addNotification(EnumClass.Event, null, m_vPartMap);
		}
		else
		{
			notif = (JDFNotification) new JDFDoc(ElementName.NOTIFICATION).getRoot();
		}
		for (final JDFEmployee vEmployee : vEmployees)
		{
			notif.copyElement(vEmployee, null);
		}
		notif.setNode(m_Node);
		return notif;
	}

	/**
	 * Set the Status and StatusDetails of this node update the PhaseTime audit or append a new phasetime as appropriate also prepare a status JMF
	 *
	 * @param nodeStatus          the new status of the node
	 * @param nodeStatusDetails   the new statusDetails of the node
	 * @param deviceStatus        the new status of the device
	 * @param deviceStatusDetails the new statusDetails of the device
	 * @return true if the status changed
	 */
	public synchronized boolean setPhase(final EnumNodeStatus nodeStatus, final String nodeStatusDetails, final EnumDeviceStatus deviceStatus,
			final String deviceStatusDetails)
	{
		if (m_Node == null)
		{
			return setIdlePhase(deviceStatus, deviceStatusDetails);
		}
		if (deviceStatus != null)
		{
			status = deviceStatus;
		}
		if (!StringUtil.isEmpty(deviceStatusDetails))
		{
			statusDetails = deviceStatusDetails;
		}
		final JDFJMF jmfStatus = createPhaseTimeJMF();

		final LinkAmount mainLinkAmount = getLinkAmount(getFirstRefID());
		// other threads may have broken things
		if (m_Node == null)
		{
			return setIdlePhase(deviceStatus, deviceStatusDetails);
		}

		final JDFAuditPool auditPool = m_Node.getCreateAuditPool();
		// TODO rethink when to send 2 phases
		final JDFPhaseTime lastPhase = auditPool.getLastPhase(m_vPartMap, m_moduleID == null ? null : m_moduleID.get(0));
		JDFPhaseTime nextPhase = lastPhase;
		final boolean bEnd = EnumNodeStatus.Completed.equals(nodeStatus) || EnumNodeStatus.Aborted.equals(nodeStatus);
		boolean bChanged = bEnd || lastPhase == null; // no previous audit or over and out

		nextPhase = auditPool.setPhase(nodeStatus, nodeStatusDetails, m_vPartMap, new VElement(vEmployees));
		if (bEnd && !bCompleted)
		{
			writeAll();
			appendResourceAudits();
			appendProcessRun(nodeStatus, auditPool);
			bCompleted = true;
		}
		if (!bEnd) // we have been active again - need to rewrite processruns
		{
			bCompleted = false;
		}

		if (lastPhase != null && nextPhase != lastPhase) // we explicitly added
		// a new phasetime audit, thus we need to add a closing JMF for the original jobPhase
		{
			bChanged = true;
			closeJobPhase(jmfStatus, mainLinkAmount, lastPhase); // attention - resets la to 0 - all calls after this have the new amounts
			startDate = new JDFDate();
		}

		if (nextPhase != null)
		{
			if (workType != null)
			{
				nextPhase.getCreateMISDetails().setWorkType(workType);
			}
			if (m_deviceID != null)
			{
				nextPhase.getCreateDevice(0).setDeviceID(m_deviceID);
			}
			nextPhase.setModules(m_moduleID, m_moduleType);

			updateCurrentJobPhase(nodeStatus, nodeStatusDetails, deviceStatus, deviceStatusDetails, jmfStatus, mainLinkAmount, nextPhase, bEnd);
		}

		jmfStatus.eraseEmptyAttributes(true);
		return bChanged;
	}

	/**
	 * append resource audits and set output resource to available, if necessary
	 */
	void appendResourceAudits()
	{
		for (final LinkAmount linkAmount : vLinkAmount)
		{
			setResourceAudit(linkAmount, EnumReason.ProcessResult);
			linkAmount.updateNodeResource();
		}
	}

	synchronized JDFJMF createPhaseTimeJMF()
	{
		docJMFPhaseTime = createJMFDoc();
		return docJMFPhaseTime.getJMFRoot();
	}

	/**
	 * creates the base jmf
	 *
	 * @return the @see JDFDoc representing the JMF
	 */
	JDFDoc createJMFDoc()
	{
		final JDFDoc d = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = d.getJMFRoot();
		jmf.setSenderID(m_deviceID);
		if (icsVersions != null)
		{
			jmf.setICSVersions(icsVersions);
		}
		return d;
	}

	/**
	 * @param deviceStatus
	 * @param deviceStatusDetails
	 * @return true if change since last time
	 */
	synchronized boolean setIdlePhase(final EnumDeviceStatus deviceStatus, final String deviceStatusDetails)
	{
		boolean bChanged = docJMFPhaseTime == null; // first aftersetPhase
		final JDFResponse r = bChanged ? null : docJMFPhaseTime.getJMFRoot().getResponse(0);
		final JDFDeviceInfo lastDevInfo = r == null ? null : r.getDeviceInfo(-1);
		status = deviceStatus;
		statusDetails = deviceStatusDetails;

		bChanged = bChanged
				|| !ContainerUtil.equals(deviceStatusDetails, lastDevInfo == null ? null : lastDevInfo.getAttribute(AttributeName.STATUSDETAILS, null, null));
		startDate = (lastDevInfo == null || lastDevInfo.getIdleStartTime() == null || bChanged) ? new JDFDate() : lastDevInfo.getIdleStartTime();

		final JDFJMF jmf = createPhaseTimeJMF();
		final JDFResponse newResponse = jmf.appendResponse(EnumType.Status);
		newResponse.copyAttribute(AttributeName.ICSVERSIONS, jmf);
		final JDFDeviceInfo newDevInfo = newResponse.getCreateDeviceInfo(0);
		fillDeviceInfo(deviceStatus, deviceStatusDetails, newDevInfo, null);
		newDevInfo.setIdleStartTime(startDate);

		return bChanged;
	}

	/**
	 * @param deviceStatus
	 * @param deviceStatusDetails
	 * @param newDevInfo
	 * @param la
	 */
	void fillDeviceInfo(final EnumDeviceStatus deviceStatus, final String deviceStatusDetails, final JDFDeviceInfo newDevInfo, final LinkAmount la)
	{
		newDevInfo.setDeviceStatus(deviceStatus);
		newDevInfo.setStatusDetails(deviceStatusDetails);
		newDevInfo.setDeviceOperationMode(operationMode);
		newDevInfo.setDeviceID(m_deviceID);
		if (currentCounter >= 0)
		{
			newDevInfo.setProductionCounter(currentCounter);
		}
		if (totalCounter >= 0)
		{
			newDevInfo.setTotalProductionCounter(totalCounter);
		}

		if (la != null)
		{
			newDevInfo.setSpeed(la.getAmount(EAmountType.Speed, null));
		}
		for (final JDFEmployee vEmployee : vEmployees)
		{
			newDevInfo.copyElement(vEmployee, null);
		}

	}

	void updateCurrentJobPhase(final EnumNodeStatus nodeStatus, final String nodeStatusDetails, final EnumDeviceStatus deviceStatus,
			final String deviceStatusDetails, final JDFJMF jmf, final LinkAmount la, final JDFPhaseTime pt2, final boolean bEnd)
	{
		final JDFResponse respStatus = (JDFResponse) jmf.appendMessageElement(JDFMessage.EnumFamily.Response, JDFMessage.EnumType.Status);
		final JDFDeviceInfo deviceInfo = respStatus.getCreateDeviceInfo(0);
		final JDFJobPhase jp = deviceInfo.createJobPhaseFromPhaseTime(pt2);
		setJobPhaseAmounts(la, jp);
		jp.setQueueEntryID(queueEntryID);

		fillDeviceInfo(deviceStatus, deviceStatusDetails, deviceInfo, la);

		if (m_Node != null && nodeStatus != null) // may be null if idle
		{
			m_Node.setPartStatus(m_vPartMap, nodeStatus, nodeStatusDetails);
			getVResLink(ELinkType.Node);// update the nodes links

			if (bEnd)
			{
				pt2.deleteNode(); // zapp the last phasetime
			}
			else
			{
				if (addPhaseTimeAmounts)
				{
					pt2.setLinks(getVResLink(ELinkType.PhaseTime));
				}
				pt2.eraseEmptyAttributes(true);
			}
		}
	}

	JDFResponse closeJobPhase(final JDFJMF jmf, final LinkAmount la, final JDFPhaseTime pt1)
	{
		final JDFResponse respStatus = (JDFResponse) jmf.appendMessageElement(JDFMessage.EnumFamily.Response, JDFMessage.EnumType.Status);
		final JDFDeviceInfo deviceInfo = respStatus.appendDeviceInfo();
		fillDeviceInfo(status, statusDetails, deviceInfo, la);
		final JDFJobPhase jp = deviceInfo.createJobPhaseFromPhaseTime(pt1);
		jp.setJobID(m_Node.getJobID(true));
		jp.setJobPartID(m_Node.getJobPartID(false));
		jp.setQueueEntryID(queueEntryID);
		setJobPhaseAmounts(la, jp);
		if (addPhaseTimeAmounts)
		{
			pt1.setLinks(getVResLink(ELinkType.PhaseTime));
		}
		// cleanup!
		for (final LinkAmount element : vLinkAmount)
		{
			element.addPhase(0, 0, true, true);
		}

		return respStatus;
	}

	void appendProcessRun(final EnumNodeStatus nodeStatus, final JDFAuditPool ap)
	{
		final JDFProcessRun pr = (JDFProcessRun) ap.addAudit(EnumAuditType.ProcessRun, null);
		pr.setPartMapVector(m_vPartMap);
		final VElement audits = ap.getAudits(EnumAuditType.PhaseTime, null, m_vPartMap);
		for (int i = 0; i < audits.size(); i++)
		{
			pr.addPhase((JDFPhaseTime) audits.elementAt(i));
		}
		pr.setEndStatus(nodeStatus);
	}

	/**
	 * @param jmfRes
	 * @return
	 */
	public JDFDoc getDocJMFResource()
	{
		final JDFDoc jmfDoc = createJMFDoc();
		final JDFJMF jmfRes = jmfDoc.getJMFRoot();
		final VElement vResResourceInfo = getVResLink(ELinkType.ResourceInfo);
		if (ContainerUtil.isEmpty(vResResourceInfo))
		{
			return null;
		}
		final JDFSignal sig = jmfRes.appendSignal(EnumType.Resource);
		sig.copyAttribute(AttributeName.ICSVERSIONS, jmfRes);

		final JDFResourceQuParams rqp = sig.appendResourceQuParams();
		rqp.setJDF(m_Node);
		rqp.setExact(false);
		rqp.setQueueEntryID(queueEntryID);
		boolean bAllExact = true;

		if (vResResourceInfo != null)
		{
			for (final KElement el : vResResourceInfo)
			{
				final JDFResourceInfo ri = sig.appendResourceInfo();
				if (workType != null)
				{
					ri.appendMISDetails().setWorkType(workType);
				}
				final JDFResourceLink rl = (JDFResourceLink) el;
				final LinkAmount la = getLinkAmount(rl.getrRef());
				final boolean bExact = la != null && la.isCopyResInfo();
				bAllExact = bAllExact && bExact;
				rqp.setExact(bExact);
				ri.setLink(rl, rqp);
			}
		}
		rqp.setExact(bAllExact);
		jmfRes.eraseEmptyAttributes(true);
		return jmfDoc;
	}

	/**
	 * update all status relevant data in jobphase also update percentComplete if we don't have amounts
	 *
	 * @param la
	 * @param jp
	 */
	void setJobPhaseAmounts(final LinkAmount la, final JDFJobPhase jp)
	{
		if (jp == null || !addPhaseTimeAmounts)
		{
			return;
		}
		jp.setPercentCompleted(percentComplete);

		if (la == null)
		{
			return;
		}

		if (la.isTrackWaste())
		{
			if (la.getAmount(EAmountType.TotalAmount) != 0)
			{
				jp.setPhaseAmount(la.getAmount(EAmountType.PhaseAmount));
				jp.setAmount(la.getAmount(EAmountType.TotalAmount));
			}
			if (la.getAmount(EAmountType.TotalWaste) != 0)
			{
				jp.setPhaseWaste(la.getAmount(EAmountType.PhaseWaste));
				jp.setWaste(la.getAmount(EAmountType.TotalWaste));
			}
		}
		else
		{
			if ((la.getAmount(EAmountType.TotalAmount) + la.getAmount(EAmountType.TotalWaste)) != 0)
			{
				jp.setPhaseAmount(la.getAmount(EAmountType.PhaseAmount) + la.getAmount(EAmountType.PhaseWaste));
				jp.setAmount(la.getAmount(EAmountType.TotalAmount) + la.getAmount(EAmountType.TotalWaste));
			}

		}
		double total = 0;

		total = la.getAmount(EAmountType.StartAmount);
		if (total != 0)
		{
			jp.setTotalAmount(total);
		}
	}

	enum ELinkType
	{
		PhaseTime, Node, ResourceInfo, ResourceAudit
	}

	/**
	 * @param n : 1=phaseTime, 2=node, 3=resourceinfo
	 * @return VElement a vector of resourcelinks
	 */
	VElement getVResLink(final ELinkType typ)
	{
		if (m_Node == null)
		{
			return null;
		}
		final VElement vRet = new VElement();
		final List<LinkAmount> vLinkAmountCopy;
		if (ELinkType.PhaseTime.equals(typ))
		{
			vLinkAmountCopy = new ArrayList<>();
			ContainerUtil.add(vLinkAmountCopy, getLinkAmount(getFirstRefID()));
		}
		else
		{
			vLinkAmountCopy = vLinkAmount;
		}
		for (final LinkAmount la : vLinkAmountCopy)
		{
			switch (typ)
			{
			case PhaseTime:
			{
				final JDFResourceLink phaseTimeLink = la.getPhaseTimeLink();
				if (phaseTimeLink != null)
				{
					vRet.add(phaseTimeLink);
				}
				break;
			}
			case Node:
			{
				vRet.add(la.updateNodeLink());
				break;
			}
			case ResourceInfo:
			{
				vRet.add(la.getResourceInfoLink());
				break;
			}
			case ResourceAudit:
			{
				vRet.add(la.getResourceAuditLink());
				break;
			}
			}
		}
		return vRet;
	}

	/**
	 * @return the docJMFPhaseTime
	 */
	public synchronized JDFDoc getDocJMFPhaseTime()
	{
		if (m_Node == null)
		{
			setIdlePhase(status, statusDetails);
		}
		return docJMFPhaseTime == null ? null : docJMFPhaseTime.clone();
	}

	/**
	 * @param bClean
	 * @return the docJMFNotification
	 */
	public synchronized JDFDoc getDocJMFNotification(final boolean bClean)
	{
		if (!events.isEmpty())
		{
			final JDFDoc jmfDoc = createJMFDoc();
			final JDFJMF jmf = jmfDoc.getJMFRoot();
			for (final JDFNotification notif : events)
			{
				final JDFSignal s = jmf.appendSignal(EnumType.Notification);
				s.copyAttribute(AttributeName.ICSVERSIONS, jmf);
				s.copyElement(notif, null);
			}
			if (bClean)
			{
				events.clear();
			}
			return jmfDoc;
		}
		else
		{
			return null;
		}
	}

	/**
	 * container class to set amounts and waste in phaseTime
	 */
	private class LinkAmount
	{
		private class AmountBag
		{
			AmountBag()
			{
				super();
				theMap = new EnumMap<>(EAmountType.class);
				for (final EAmountType e : EAmountType.values())
				{
					theMap.put(e, 0.0);
				}
			}

			protected long lastCall = 0;
			private final EnumMap<EAmountType, Double> theMap;

			/**
			 *
			 */
			void reset()
			{
				theMap.put(EAmountType.TotalAmount, 0.0);
				theMap.put(EAmountType.TotalWaste, 0.0);
				theMap.put(EAmountType.PhaseAmount, 0.0);
				theMap.put(EAmountType.PhaseWaste, 0.0);
			}

			double increment(EAmountType typ, double val)
			{
				final double d = theMap.get(typ) + val;
				theMap.put(typ, d);
				return d;
			}

			double get(EAmountType typ)
			{
				return theMap.get(typ);
			}

			void set(EAmountType typ, double d)
			{
				theMap.put(typ, d);
			}

			/**
			 * @param amount
			 * @param waste
			 * @param bNewPhase
			 * @param sumTotal  if true, also sum up the total amounts, else only phase
			 */
			void addPhase(final double amount, final double waste, final boolean bNewPhase, final boolean sumTotal)
			{
				if (sumTotal)
				{
					increment(EAmountType.TotalAmount, amount);
					increment(EAmountType.TotalWaste, waste);
				}
				if (bNewPhase)
				{
					set(EAmountType.PhaseAmount, 0.0);
					set(EAmountType.PhaseWaste, 0.0);
				}
				else
				{
					increment(EAmountType.PhaseAmount, amount);
					increment(EAmountType.PhaseWaste, waste);
				}
				final long t = System.currentTimeMillis();
				if ((get(EAmountType.TotalAmount) + get(EAmountType.TotalWaste)) <= 0) // else total takes over
				{
					if (lastCall == 0)
					{
						lastCall = t;
						set(EAmountType.PhaseWaste, 0.0);

					}
					// wait at least 30 seconds to calculate speed -else bad fluctuations...
					else if (t - lastCall < 30000 && !bNewPhase)
					{
						increment(EAmountType.Last, amount + waste);
					}
					else
					// calculate current speed
					{
						final double amountLast = theMap.get(EAmountType.Last);
						double deltaT = t - lastCall;
						deltaT /= 3600000.0; // milliseconds / hour
						theMap.put(EAmountType.Speed, deltaT == 0.0 ? 0.0 : amountLast / deltaT);
						theMap.put(EAmountType.Last, 0.0);
						lastCall = t;
					}
				}
			}

			@Override
			public String toString()
			{
				String ret = "AmountBag";
				final List<EAmountType> keys = ContainerUtil.getKeyList(theMap);
				keys.sort(null);
				for (final EAmountType key : keys)
				{
					ret += " " + key + "=" + theMap.get(key);
				}
				return ret;
			}

		} // end AmountBag

		private final JDFResourceLink rl;
		private final HashMap<JDFAttributeMap, AmountBag> lastBags;
		private final boolean bInteger;

		LinkAmount(final JDFResourceLink _rl)
		{
			final JDFNode dump = new JDFDoc("JDF").getJDFRoot();
			dump.appendResourceLinkPool().copyElement(_rl, null);
			final JDFResource target = _rl.getTarget();
			bInteger = isInteger(target);
			dump.appendResourcePool().copyElement(target, null);
			rl = (JDFResourceLink) dump.getResourceLinkPool().getElement(_rl.getNodeName(), null, 0);

			lastBags = new HashMap<>();
			prefillBags();
			fillBags();
		}

		void prefillBags()
		{
			final JDFAttributeMapArray vResPartMap = rl.getPartMapArray();
			if (!ContainerUtil.isEmpty(vResPartMap))
			{
				if (!VJDFAttributeMap.isEmpty(m_vPartMap))
				{
					vResPartMap.overlapMap(m_vPartMap);
				}
				if (m_ignoreParts != null)
				{
					vResPartMap.removeKeys(m_ignoreParts.getSet());
				}
			}
			// ensure value for null
			lastBags.put(new JDFAttributeMap(), new AmountBag());
			if (!ContainerUtil.isEmpty(vResPartMap))
			{
				for (final JDFAttributeMap partMap : vResPartMap)
				{
					lastBags.put(partMap, new AmountBag());
				}
			}
		}

		void setAmount(EAmountType typ, double amount, JDFAttributeMap map)
		{
			final AmountBag bag = getCreateBag(map);
			bag.set(typ, amount);

		}

		void addAmount(EAmountType typ, double amount, JDFAttributeMap map)
		{
			final AmountBag bag = getCreateBag(map);
			bag.increment(typ, amount);

		}

		boolean hasBag(JDFAttributeMap map)
		{
			return lastBags.get(map == null ? new JDFAttributeMap() : map) != null;
		}

		void fillBags()
		{
			for (final Entry<JDFAttributeMap, AmountBag> e : lastBags.entrySet())
			{
				final JDFAttributeMap map0 = e.getKey();
				final JDFAttributeMap map = new JDFAttributeMap(map0);
				final AmountBag bag = e.getValue();
				if (isTrackWaste())
				{
					map.put(EnumPartIDKey.Condition, "Good");
					bag.set(EAmountType.StartAmount, rl.getAmountPoolDouble(AttributeName.AMOUNT, map));
					bag.increment(EAmountType.TotalAmount, rl.getAmountPoolDouble(AttributeName.ACTUALAMOUNT, map));

					map.put(EnumPartIDKey.Condition, "Waste");
					double maxAmount = rl.getAmountPoolDouble(AttributeName.MAXAMOUNT, map);
					if (maxAmount <= 0.0)
					{
						maxAmount = rl.getAmountPoolDouble(AttributeName.AMOUNT, map);
					}
					bag.set(EAmountType.StartWaste, maxAmount);
					bag.increment(EAmountType.TotalWaste, rl.getAmountPoolDouble(AttributeName.ACTUALAMOUNT, map));
				}
				else
				{
					bag.set(EAmountType.StartAmount, rl.getAmountPoolDouble(AttributeName.AMOUNT, map));
					bag.increment(EAmountType.TotalAmount, rl.getAmountPoolDouble(AttributeName.ACTUALAMOUNT, map));
				}
			}
		}

		void reset()
		{
			for (final AmountBag ab : lastBags.values())
			{
				ab.reset();
			}

		}

		/**
		 * should the resource be displayed as an integer?
		 *
		 * @param target
		 * @return true if the resource is an integer type
		 */
		boolean isInteger(final JDFResource target)
		{
			return (target instanceof JDFUsageCounter) || (target instanceof JDFMedia) || (target instanceof JDFExposedMedia)
					|| (target instanceof JDFComponent);
		}

		/**
		 * @return the updated reslink
		 */
		JDFResourceLink updateNodeLink()
		{
			final JDFResourceLink nodeLink = m_Node.getLink(0, null, new JDFAttributeMap(AttributeName.RREF, rl.getrRef()), null);
			if (nodeLink != null)
			{

				for (final Entry<JDFAttributeMap, AmountBag> e : lastBags.entrySet())
				{
					final JDFAttributeMap map0 = new JDFAttributeMap(e.getKey());
					final JDFAttributeMap map = new JDFAttributeMap(e.getKey());
					final VJDFAttributeMap vMap = new VJDFAttributeMap(map);
					if (isTrackWaste())
					{
						map.put(EnumPartIDKey.Condition, "Good");
						nodeLink.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(getAmount(EAmountType.TotalAmount, map0)), null, vMap);
						vMap.put(EnumPartIDKey.Condition, "Waste");
						nodeLink.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(getAmount(EAmountType.TotalWaste, map0)), null, vMap);
					}
					else
					{
						final double amount = getAmount(EAmountType.TotalAmount, map0) + getAmount(EAmountType.TotalWaste, map0);
						nodeLink.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(amount), null, vMap);
					}
					// update output status
					if (getAmount(EAmountType.TotalAmount, map0) > 0)
					{
						final JDFResource r = nodeLink.getLinkRoot();
						final JDFResource rp = r.getPartition(map0, null);
						if (rp != null)
						{
							rp.setResStatus(EnumResStatus.Available, false);
						}
					}
				}
			}
			return nodeLink;

		}

		/**
		 * update the output resource to be available
		 */
		void updateNodeResource()
		{
			final JDFResourceLink nodeLink = m_Node.getLink(0, null, new JDFAttributeMap(AttributeName.RREF, rl.getrRef()), null);
			if (nodeLink != null && EnumUsage.Output.equals(nodeLink.getUsage()))
			{

				final VElement vRes = nodeLink.getTargetVector(-1);
				if (vRes != null)
				{
					final int size = vRes.size();
					for (int i = 0; i < size; i++)
					{
						final JDFResource r = (JDFResource) vRes.get(i);
						final List<JDFResource> leaves = r.getLeafArray(false);
						for (final JDFResource rr : leaves)
						{
							final VJDFAttributeMap vMap = rr.getPartMapVector(false);
							if (m_vPartMap == null || m_vPartMap.overlapsMap(vMap))
							{
								rr.setResStatus(EnumResStatus.Available, true);
							}
						}
					}
					// update intermediate resources
					for (int i = 0; i < size; i++)
					{
						final JDFResource r = (JDFResource) vRes.get(i);
						final VElement leaves = r.getLeaves(true);
						for (final Object element : leaves)
						{
							final JDFResource rr = (JDFResource) element;
							if (rr.isLeaf())
							{
								continue;
							}
							if (EnumResStatus.Available.equals(rr.getStatusFromLeaves(false)))
							{
								rr.setResStatus(EnumResStatus.Available, false);
							}
						}
					}

				}
			}
		}

		/**
		 * update the speed based on new amounts
		 */
		void updateSpeed()
		{
			final long t = System.currentTimeMillis();
			final AmountBag ab = getBag(null);

			if (ab.lastCall == 0)
			{
				ab.set(EAmountType.Speed, 0);
				ab.lastCall = t;
				ab.set(EAmountType.Last, 0);
			}
			else
			{
				double dt = t - ab.lastCall;
				if (dt > 30000)
				{
					dt /= 3600000.;
					final double currentN = ab.get(EAmountType.TotalAmount) + ab.get(EAmountType.TotalWaste);
					final double deltaN = currentN - ab.get(EAmountType.Last);
					ab.set(EAmountType.Speed, deltaN / dt);
					ab.set(EAmountType.Last, deltaN);
					ab.lastCall = t;
				}
			}
		}

		/**
		 * @return get a link to dump into a phaseTime audit
		 */
		JDFResourceLink getPhaseTimeLink()
		{
			cleanAmounts();
			return setPhaseAmounts();
		}

		/**
		 * @return JDFResourceLink the resource link for a resource audit
		 */
		JDFResourceLink getResourceAuditLink()
		{
			return getResourceInfoLink();
		}

		/**
		 * @return JDFResourceLink the resource link for a resourceInfo
		 */
		JDFResourceLink getResourceInfoLink()
		{
			cleanAmounts();
			return setTotalAmounts();
		}

		JDFResourceLink setPhaseAmounts()
		{
			boolean bEmpty = true;
			for (final Entry<JDFAttributeMap, AmountBag> e : lastBags.entrySet())
			{
				final JDFAttributeMap map = new JDFAttributeMap(e.getKey());
				final VJDFAttributeMap vMap = new VJDFAttributeMap(map);
				final double startAmount = getAmount(EAmountType.StartAmount, map);
				final double startWaste = getAmount(EAmountType.StartWaste, map);
				final double phaseAmount = getAmount(EAmountType.PhaseAmount, map);
				final double phaseWaste = getAmount(EAmountType.PhaseWaste, map);
				final double totalAmount = getAmount(EAmountType.TotalAmount, map);
				final double totalWaste = getAmount(EAmountType.TotalWaste, map);
				if (isTrackWaste())
				{
					vMap.put(EnumPartIDKey.Condition, "Good");
					if (totalAmount != 0 || startAmount > 0)
					{
						rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(phaseAmount), null, vMap);
						bEmpty = false;
					}
					if (startAmount != 0)
					{
						rl.setAmountPoolAttribute(AttributeName.AMOUNT, formatAmount(startAmount), null, vMap);
						bEmpty = false;
					}
					vMap.put(EnumPartIDKey.Condition, "Waste");
					if (getAmount(EAmountType.TotalWaste, map) != 0 || startWaste > 0)
					{
						rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(phaseWaste), null, vMap);
						bEmpty = false;
					}
					if (startWaste != 0)
					{
						rl.setAmountPoolAttribute(AttributeName.AMOUNT, formatAmount(startWaste), null, vMap);
						bEmpty = false;
					}

				}
				else if (totalAmount + totalWaste != 0 || startAmount + startWaste > 0)
				{
					rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(phaseAmount + phaseWaste), null, vMap);
					bEmpty = false;
				}
				if (startAmount + startWaste != 0)
				{
					rl.setAmountPoolAttribute(AttributeName.AMOUNT, formatAmount(startAmount + startWaste), null, vMap);
					bEmpty = false;
				}
			}
			return bEmpty ? null : rl;
		}

		JDFResourceLink setTotalAmounts()
		{
			for (final Entry<JDFAttributeMap, AmountBag> e : lastBags.entrySet())
			{
				final JDFAttributeMap map = new JDFAttributeMap(e.getKey());
				final VJDFAttributeMap vMap = new VJDFAttributeMap(map);
				final double startAmount = getAmount(EAmountType.StartAmount, map);
				final double startWaste = getAmount(EAmountType.StartWaste, map);
				final double totalAmount = getAmount(EAmountType.TotalAmount, map);
				final double totalWaste = getAmount(EAmountType.TotalWaste, map);
				if (isTrackWaste())
				{
					vMap.put(EnumPartIDKey.Condition, "Good");
					if (totalAmount != 0)
					{
						rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(totalAmount), null, vMap);
					}
					if (startAmount != 0)
					{
						rl.setAmountPoolAttribute(AttributeName.AMOUNT, formatAmount(startAmount), null, vMap);
					}
					vMap.put(EnumPartIDKey.Condition, "Waste");
					if (totalWaste != 0)
					{
						rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(totalWaste), null, vMap);
					}
					if (startWaste != 0)
					{
						rl.setAmountPoolAttribute(AttributeName.AMOUNT, formatAmount(startWaste), null, vMap);
					}
				}
				else
				{
					if (totalAmount + totalWaste != 0)
					{
						rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(totalAmount + totalWaste), null, vMap);
					}
					if (startAmount + startWaste != 0)
					{
						rl.setAmountPoolAttribute(AttributeName.AMOUNT, formatAmount(startAmount + startWaste), null, vMap);
					}
				}
			}
			return rl;
		}

		/**
		 * change the value to integer, if required
		 *
		 * @param amount
		 * @return the formatted amount, either as integer or double
		 */
		double getAmount(final double amount)
		{
			return bInteger ? ((int) amount) : amount;
		}

		/**
		 * change the value to integer, if required
		 *
		 * @param amount
		 * @return the formatted amount, either as integer or double
		 */
		double getAmount(EAmountType typ)
		{
			double d = 0;
			for (final AmountBag ab : lastBags.values())
			{
				d += ab.get(typ);
			}
			return getAmount(d);
		}

		/**
		 * change the value to integer, if required
		 *
		 * @param amount
		 * @return the formatted amount, either as integer or double
		 */
		double getAmount(EAmountType typ, JDFAttributeMap map)
		{
			final AmountBag ab = getBag(map);
			return getAmount(ab == null ? 0 : ab.get(typ));
		}

		AmountBag getBag(JDFAttributeMap map)
		{
			if (map == null)
			{
				map = new JDFAttributeMap();
			}
			return lastBags.get(map);
		}

		AmountBag getCreateBag(JDFAttributeMap map)
		{
			AmountBag ab = getBag(map);
			if (ab == null)
			{
				ab = new AmountBag();
				lastBags.put(map, ab);
			}
			return ab;
		}

		/**
		 * @param amount
		 * @return the formatted amount, either as integer or double
		 */
		String formatAmount(final double amount)
		{
			return bInteger ? StringUtil.formatInteger((int) amount) : StringUtil.formatDouble(amount);
		}

		/**
		 *
		 */
		void cleanAmounts()
		{

			rl.removeAttribute(AttributeName.AMOUNT);
			rl.removeAttribute(AttributeName.ACTUALAMOUNT);
			rl.removeChild(ElementName.AMOUNTPOOL, null, 0);
		}

		/**
		 * @param amount
		 * @param waste
		 * @param bNewPhase
		 * @param sumTotal  if true, also sum up the total amounts, else only phase
		 */
		void addPhase(final double amount, final double waste, final boolean bNewPhase, final boolean sumTotal)
		{
			addPhase(amount, waste, bNewPhase, sumTotal, null);
		}

		void addPhase(final double amount, final double waste, final boolean bNewPhase, final boolean sumTotal, JDFAttributeMap map)
		{
			getCreateBag(map).addPhase(amount, waste, bNewPhase, sumTotal);
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			final StringBuilder sb = new StringBuilder();
			sb.append("LinkAmount: " + rl.shortString());
			final JDFAttributeMapArray a = new JDFAttributeMapArray();
			a.addAll(lastBags.keySet());
			sb.append(" bags: " + lastBags.size() + " " + a.getKeys());

			return sb.toString();

		}

		boolean linkFitsKey(final String key)
		{
			if (key == null)
			{
				return true;
			}

			return rl.matchesString(key);
		}

		boolean linkFitsKey(final Set<String> keys)
		{
			if (keys == null)
			{
				return false;
			}

			return keys.contains("*") || keys.contains(rl.getNamedProcessUsage()) || keys.contains(rl.getLinkedResourceName()) || keys.contains(rl.getrRef())
					|| keys.contains(rl.getAttribute(AttributeName.USAGE));
		}

		/**
		 * @return the bCopyResInfo
		 */
		boolean isCopyResInfo()
		{
			return linkFitsKey(setCopyResInfo);
		}

		/**
		 * @return the bTrackWaste
		 */
		boolean isTrackWaste()
		{
			return linkFitsKey(setTrackWaste);
		}

	}

	/**
	 * @return the m_deviceID
	 */
	public String getDeviceID()
	{
		return m_deviceID;
	}

	/**
	 * @return the m_moduleID
	 */
	public VString getModuleeID()
	{
		return m_moduleID;
	}

	/**
	 * @param deviceid the m_deviceID to set
	 */
	public void setDeviceID(final String deviceid)
	{
		m_deviceID = deviceid;
	}

	/**
	 * @param moduleID
	 * @param moduleType
	 */
	public void addModule(final String moduleID, final String moduleType)
	{
		if (m_moduleID == null)
		{
			m_moduleID = new VString();
		}
		if (m_moduleType == null)
		{
			m_moduleType = new VString();
		}
		m_moduleID.add(moduleID);
		m_moduleType.add(moduleType);
	}

	/**
	 * set waste tracking on or off for the resourcelink rl
	 *
	 * @param resID the resource id to the resource to track
	 * @param b     tracking on or off
	 */
	public void setTrackWaste(final String resID, final boolean b)
	{
		if (b)
		{
			setTrackWaste.add(resID);
		}
		else
		{
			setTrackWaste.remove(resID);
		}
	}

	/**
	 * set copying the resource into resourceInfo on or off for the resourcelink rl
	 *
	 * @param _refID the refid of the resourcelink to the resource to copy
	 * @param b      tracking on or off
	 */
	public void setCopyResInResInfo(final String _refID, final boolean b)
	{
		if (b)
		{
			setCopyResInfo.add(_refID);
		}
		else
		{
			setCopyResInfo.remove(_refID);
		}

	}

	/**
	 * @Deprecated
	 * @param res
	 * @param reason
	 * @return
	 */
	@Deprecated
	public synchronized JDFResourceAudit setResourceAudit(String res, final EnumReason reason)
	{
		final LinkAmount la = getLinkAmount(res);
		return setResourceAudit(la, reason);
	}

	/**
	 * @param resID  the resource ID to set/track reason for the audit
	 * @param reason
	 * @return JDFResourceAudit the generated audit
	 */
	synchronized JDFResourceAudit setResourceAudit(LinkAmount la, final EnumReason reason)
	{
		final JDFAuditPool ap = m_Node.getCreateAuditPool();

		final JDFResourceAudit resourceAudit = ap.addResourceAudit(null);
		resourceAudit.setContentsModified(false);
		resourceAudit.setReason(reason);

		final KElement newLink = resourceAudit.copyElement(la.getResourceAuditLink(), null);
		newLink.removeAttributeFromChildren(AttributeName.AMOUNT, null);
		resourceAudit.setPartMapVector(m_vPartMap);

		return resourceAudit;
	}

	/**
	 * @param endStatus
	 * @return
	 */
	public JDFProcessRun setProcessResult(final EnumNodeStatus endStatus)
	{
		setPhase(EnumNodeStatus.Completed, null, EnumDeviceStatus.Idle, null);
		final JDFAuditPool ap = m_Node.getCreateAuditPool();
		return (JDFProcessRun) ap.getAudit(-1, EnumAuditType.ProcessRun, null, m_vPartMap);
	}

	/**
	 * @param _queueEntryID
	 */
	public void setQueueEntryID(final String _queueEntryID)
	{
		queueEntryID = _queueEntryID;
	}

	/**
	 * @return queueEntryID
	 */
	public String getQueueEntryID()
	{
		return queueEntryID;
	}

	/**
	 * sets the MISDetails/@WorkType for default audis, resource audits and phaseTime elements
	 *
	 * @param _workType the worktype to set, if null no MISDetails and no Worktype are added. closes all ongoing phases and starts a new phase
	 */
	public void setWorkType(final EnumWorkType _workType)
	{
		if (ContainerUtil.equals(_workType, workType))
		{
			return; // nop
		}

		workType = _workType;
	}

	/**
	 * @return
	 */
	public EnumDeviceStatus getStatus()
	{
		return status;
	}

	/**
	 * @return
	 */
	public String getStatusDetails()
	{
		return statusDetails;
	}

	/**
	 * @return
	 */
	public JDFDate getStartDate()
	{
		return startDate;
	}

	/**
	 * @param _operationMode
	 */
	public void setOperationMode(final EnumDeviceOperationMode _operationMode)
	{
		operationMode = _operationMode;
	}

	/**
	 * @param refID the resource ID or name
	 * @return the planned amount for the resource
	 */
	public double getPlannedAmount(final String refID)
	{
		final LinkAmount la = getLinkAmount(refID);
		return la == null ? 0 : la.getAmount(la.getAmount(EAmountType.StartAmount));
	}

	/**
	 * @param refID the resource ID or name
	 * @return the planned waste for the resource
	 */
	public double getPlannedWaste(final String refID)
	{
		final LinkAmount la = getLinkAmount(refID);
		return la == null ? 0 : la.getAmount(la.getAmount(EAmountType.StartWaste));
	}

	/**
	 * @return the nodeID
	 */
	public NodeIdentifier getNodeIDentifier()
	{
		return nodeID;
	}

	/**
	 * set percentComplete to percent
	 *
	 * @param percent the percentage to set
	 */
	public void setPercentComplete(final double percent)
	{
		percentComplete = Math.max(0, Math.min(100, percent));
	}

	/**
	 * update percentComplete by percent
	 *
	 * @param percent the percentage to increment
	 */
	public void updatePercentComplete(final double percent)
	{
		setPercentComplete(percentComplete + percent);
	}

	/**
	 * @param icsVersions the icsVersions to set
	 */
	public void setIcsVersions(VString icsVersions)
	{
		if (icsVersions != null && icsVersions.size() == 0)
		{
			icsVersions = null;
		}
		this.icsVersions = icsVersions;
	}

	/**
	 * @return the icsVersions
	 */
	public VString getIcsVersions()
	{
		return icsVersions;
	}

	/**
	 * replace all employees in this - update phases if necessary
	 *
	 * @param employees
	 */
	public void replaceEmployees(final Vector<JDFEmployee> employees)
	{
		if (employees == null || employees.size() == 0)
		{
			if (vEmployees.size() > 0)
			{
				clearEmployees();
				resetPhase();
			}
		}
		else if (!new VElement(employees).isEqual(new VElement(vEmployees)))
		{
			vEmployees.clear();
			vEmployees.addAll(employees);
			resetPhase();
		}

	}

	public String shortString()
	{
		return getClass().getSimpleName() + " JobID=" + getJobID() + " DeviceID=" + getDeviceID();
	}

	public String getJobID()
	{
		return m_Node == null ? "null" : m_Node.getJobID(true);
	}

}