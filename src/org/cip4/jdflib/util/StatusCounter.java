/*
 *
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
 * Created on Jul 5, 2006, 11:45:44 AM
 * org.cip4.jdflib.util.MimeUtil.java
 * Project Name: mimeutil
 */
package org.cip4.jdflib.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceOperationMode;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumWorkType;
import org.cip4.jdflib.auto.JDFAutoNotification.EnumClass;
import org.cip4.jdflib.auto.JDFAutoResourceAudit.EnumReason;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFResourceQuParams;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.NodeIdentifier;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFUsageCounter;

//TODO add time related metadata

/**
 * Utility class for status JDF and JMF
 * 
 * @author prosirai
 * 
 */
public class StatusCounter
{

	private double percentComplete = 0;
	protected JDFNode m_Node;
	private boolean bCompleted = false;
	private JDFDoc docJMFPhaseTime;
	private JDFDoc docJMFResource;
	private JDFDoc docJMFNotification;
	protected VJDFAttributeMap m_vPartMap;
	protected VString m_ignoreParts = null;
	private String m_deviceID = null;
	private VString m_moduleID = null;
	private VString m_moduleType = null;
	private LinkAmount[] vLinkAmount = null;
	private String firstRefID = null;
	private String queueEntryID;
	private EnumDeviceOperationMode operationMode = EnumDeviceOperationMode.Productive;
	private EnumWorkType workType = null;
	protected HashSet<String> setTrackWaste = new HashSet<String>();
	protected HashSet<String> setCopyResInfo = new HashSet<String>();
	private EnumDeviceStatus status = null;
	private String statusDetails = null;
	private JDFDate startDate;
	private NodeIdentifier nodeID = null;
	private final Vector<JDFEmployee> vEmployees = new Vector<JDFEmployee>();

	private double totalCounter = -1;
	private VString icsVersions = null;

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
		final JDFEmployee eOld = (JDFEmployee) ContainerUtil.getMatch(vEmployees, employee, 0);
		if (eOld == null)
		{
			vEmployees.add(employee);
			resetPhase();
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
		return "[StatusCounter - counter: " + m_deviceID + "Start date: " + startDate + " " + vLinkAmount + "]";
	}

	/**
	 * 
	 */
	public void writeAll()
	{
		if (m_Node != null)
		{
			getVResLink(2); // write all reslinks to disk
		}
	}

	/**
	 * construct a StatusUtil for a node n
	 * 
	 * @param node the JDFNode that is being processed
	 * @param vPartMap the map of Parts that is being processed excluding the waste partition
	 * @param vResLinks the reslinks that are tracked for amount handling
	 */
	public StatusCounter(final JDFNode node, final VJDFAttributeMap vPartMap, final VElement vResLinks)
	{
		setActiveNode(node, vPartMap, vResLinks);
	}

	/**
	 * set the currently active node
	 * 
	 * @param node the JDFNode that is being processed
	 * @param vPartMap the map of Parts that is being processed excluding the waste partition
	 * @param vResLinks the reslinks that are tracked for amount handling
	 */
	public void setActiveNode(final JDFNode node, final VJDFAttributeMap vPartMap, final VElement vResLinks)
	{
		VElement vResLinksLocal = vResLinks;

		if (node == null)
		{
			setTrackWaste.clear();
		}

		bCompleted = false;
		m_Node = node;
		m_vPartMap = vPartMap;
		vLinkAmount = null;
		firstRefID = null;
		docJMFResource = null;
		docJMFPhaseTime = null;
		startDate = new JDFDate();
		nodeID = null;
		percentComplete = 0;

		if (node == null)
		{
			setPhase(null, null, EnumDeviceStatus.Idle, null);
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

		if (vResLinksLocal == null && m_Node != null)
		{
			vResLinksLocal = m_Node.getResourceLinks(null);
			if (vResLinksLocal != null)
			{
				final int siz = vResLinksLocal.size();
				for (int i = siz - 1; i >= 0; i--)
				{
					final JDFResourceLink rl = (JDFResourceLink) vResLinksLocal.elementAt(i);
					if (!rl.isPhysical())
					{
						vResLinksLocal.remove(i);
					}
				}
			}
		}

		setUpResLinks(vResLinksLocal);
	}

	/**
	 * simple sleep wrapper that catches its exception
	 * 
	 * @param millis
	 * @deprecated use {@link ThreadUtil}.sleep()
	 */
	@Deprecated
	public static void sleep(final int millis)
	{
		ThreadUtil.sleep(millis);
	}

	/**
	 * get the matching LinkAmount out of this
	 * 
	 * @param refID the refID, name or usage of the resource of the bag - this MUST match the refID of a ResourceLink
	 * @return the LinkAmount with matching refID, null if none found or bags is null
	 */
	protected LinkAmount getLinkAmount(final String refID)
	{
		String refIDLocal = refID;

		if (vLinkAmount == null || vLinkAmount.length == 0)
		{
			return null;
		}

		if (refIDLocal == null)
		{
			refIDLocal = getFirstRefID();
		}

		for (int i = 0; i < vLinkAmount.length; i++)
		{
			if (vLinkAmount[i].linkFitsKey(refIDLocal))
			{
				return vLinkAmount[i];
			}
		}

		return null;
	}

	/**
	 * get the matching LinkAmount out of this
	 * 
	 * @param refID the refID, name or usage of the resource of the bag - this MUST match the refID of a ResourceLink
	 * @return the actual refID of the matching resLink, null if none found or bags is null
	 */
	public String getLinkID(final String refID)
	{
		String refIDLocal = refID;

		if (vLinkAmount == null || vLinkAmount.length == 0)
		{
			return null;
		}

		if (refIDLocal == null)
		{
			refIDLocal = getFirstRefID();
		}

		for (int i = 0; i < vLinkAmount.length; i++)
		{
			if (vLinkAmount[i].linkFitsKey(refIDLocal))
			{
				return vLinkAmount[i].rl.getrRef();
			}
		}

		return null;
	}

	/**
	 * get the matching LinkAmount out of this
	 * 
	 * @param refID the refID, name or usage of the resource of the bag - this MUST match the refID of a ResourceLink
	 * @return the LinkAmount with matching refID, null if none found or bags is null
	 */
	protected LinkAmount getLinkAmount(final int n)
	{
		if (vLinkAmount == null || vLinkAmount.length <= n)
		{
			return null;
		}
		return vLinkAmount[n];
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
		if (vLinkAmount == null || vLinkAmount.length == 0)
		{
			return null;
		}
		return vLinkAmount[0].rl.getrRef();
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
		if (resLinks == null || resLinks.size() == 0)
		{
			return;
		}
		vLinkAmount = new LinkAmount[resLinks.size()];
		for (int i = 0; i < vLinkAmount.length; i++)
		{
			vLinkAmount[i] = new LinkAmount((JDFResourceLink) resLinks.elementAt(i));
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
		la.lastBag.reset();
	}

	/**
	 * set the phase the amount specified by amount and waste to the resource with id refID
	 * 
	 * @param refID , type or usage of the resource, if null all are updated
	 * @param amount the amount for this phase
	 * @param waste the waste for this phase
	 */
	public synchronized void setPhase(final String refID, final double amount, final double waste)
	{
		final LinkAmount la = getLinkAmount(refID);
		if (la == null)
		{
			return;
		}
		la.addPhase(amount, waste, true, false);
		if (amount >= 0)
		{
			updatePercentComplete(la);
		}
	}

	/**
	 * add the amount specified by amount and waste to the resource with id refID
	 * 
	 * @param refID , type or usage of the resource, if null all are updated
	 * @param amount
	 * @param waste
	 * @param sumTotal if true, also sum up the total amounts, else only phase
	 */
	public synchronized void addPhase(final String refID, final double amount, final double waste, final boolean sumTotal)
	{
		final LinkAmount la = getLinkAmount(refID);
		if (la == null)
		{
			return;
		}
		la.addPhase(amount, waste, false, sumTotal);
		if (sumTotal)
		{
			la.updateSpeed();
		}
		if (amount >= 0)
		{
			updatePercentComplete(la);
		}
	}

	/**
	 * updates percentcomplete based on la
	 * @param la
	 */
	private void updatePercentComplete(final LinkAmount la)
	{
		if (la == null || la.startAmount <= 0 || !la.rl.getrRef().equals(getFirstRefID()))
		{
			return;
		}
		percentComplete = la.lastBag.totalAmount / la.startAmount * 100.0;
	}

	/**
	 * set the total amount specified by amount and waste to the resource with id refID usually called when reading resource audits or resource signals
	 * 
	 * @param refID , type or usage of the resource, if null all are updated
	 * @param amount the amount to set
	 * @param bWaste if true, set total waste, else set total good
	 */
	public synchronized void setTotal(final String refID, final double amount, final boolean bWaste)
	{
		final LinkAmount la = getLinkAmount(refID);
		if (la == null)
		{
			return;
		}
		if (bWaste)
		{
			la.lastBag.totalWaste = amount;
		}
		else
		{
			la.lastBag.totalAmount = amount;
			updatePercentComplete(la);
		}
		la.updateSpeed();
	}

	/**
	 * get the total the amount of the resource with id refID
	 * 
	 * @param refID , type or usage of the resource,
	 */
	public double getTotalAmount(final String refID)
	{
		final LinkAmount la = getLinkAmount(refID);
		return la == null ? 0 : la.getAmount(la.lastBag.totalAmount);
	}

	/**
	 * get all total amounts of all tracked resources
	 * 
	 * @param i
	 */
	public double[] getTotalAmounts()
	{
		if (vLinkAmount == null || vLinkAmount.length == 0)
		{
			return null;
		}
		final double[] d = new double[vLinkAmount.length];
		for (int i = 0; i < d.length; i++)
		{
			d[i] = vLinkAmount[i].getAmount(vLinkAmount[i].lastBag.totalAmount);
		}
		return d;
	}

	/**
	 * get all total amounts of all tracked resources
	 * 
	 * @param i
	 */
	public JDFResourceLink[] getAmountLinks()
	{
		if (vLinkAmount == null || vLinkAmount.length == 0)
		{
			return null;
		}
		final JDFResourceLink[] d = new JDFResourceLink[vLinkAmount.length];
		for (int i = 0; i < d.length; i++)
		{
			d[i] = vLinkAmount[i].rl;
		}
		return d;
	}

	/**
	 * get all phaseamounts of all tracked resources
	 * 
	 * @param i
	 */
	public double[] getPhaseAmounts()
	{
		if (vLinkAmount == null || vLinkAmount.length == 0)
		{
			return null;
		}
		final double[] d = new double[vLinkAmount.length];
		for (int i = 0; i < d.length; i++)
		{
			d[i] = vLinkAmount[i].getAmount(vLinkAmount[i].lastBag.phaseAmount);
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
	 */
	public double getPhaseAmount(final String refID)
	{
		final LinkAmount la = getLinkAmount(refID);
		return la == null ? 0 : la.getAmount(la.lastBag.phaseAmount);
	}

	/**
	 * get the total the amount of the resource with id refID
	 * 
	 * @param refID , type or usage of the resource,
	 */
	public double getTotalWaste(final String refID)
	{
		final LinkAmount la = getLinkAmount(refID);
		return la == null ? 0 : la.getAmount(la.lastBag.totalWaste);
	}

	/**
	 * get all total amounts of all tracked resources
	 * 
	 * @param i
	 */
	public double[] getTotalWastes()
	{
		if (vLinkAmount == null || vLinkAmount.length == 0)
		{
			return null;
		}
		final double[] d = new double[vLinkAmount.length];
		for (int i = 0; i < d.length; i++)
		{
			d[i] = vLinkAmount[i].getAmount(vLinkAmount[i].lastBag.totalWaste);
		}
		return d;
	}

	/**
	 * get all phase waste amounts of all tracked resources
	 * 
	 * @param i
	 */
	public double[] getPhaseWastes()
	{
		if (vLinkAmount == null || vLinkAmount.length == 0)
		{
			return null;
		}
		final double[] d = new double[vLinkAmount.length];
		for (int i = 0; i < d.length; i++)
		{
			d[i] = vLinkAmount[i].getAmount(vLinkAmount[i].lastBag.phaseWaste);
		}
		return d;
	}

	/**
	 * get the total the amount of the resource with id refID
	 * 
	 * @param refID , type or usage of the resource,
	 */
	public double getPhaseWaste(final String refID)
	{
		final LinkAmount la = getLinkAmount(refID);
		return la == null ? 0 : la.getAmount(la.lastBag.phaseWaste);
	}

	private boolean resetPhase()
	{
		if (m_Node == null)
		{
			return setIdlePhase(status, statusDetails);
		}

		final JDFAuditPool ap = m_Node.getCreateAuditPool();
		final JDFPhaseTime lastPhase = ap.getLastPhase(m_vPartMap, m_moduleID == null ? null : m_moduleID.stringAt(0));
		final EnumNodeStatus ns = lastPhase != null ? lastPhase.getStatus() : EnumNodeStatus.Waiting;
		final String nodeStatusDetails = lastPhase != null ? lastPhase.getStatusDetails() : statusDetails;
		return setPhase(ns, nodeStatusDetails, status, statusDetails);
	}

	/**
	 * set event, append the Event element and optionally the comment<br/>
	 * overwrites existing values
	 * 
	 * @param eventID Event/@EventID to set
	 * @param eventValue Event/@EventValue to set
	 * @param comment the comment text, if null no comment is set
	 */
	public synchronized JDFNotification setEvent(final String eventID, final String eventValue, final String comment)
	{
		final JDFNotification notif = createBaseNotification();
		final JDFJMF jmf = createNotificationJMF();
		final JDFSignal s = jmf.appendSignal(EnumType.Notification);
		s.copyAttribute(AttributeName.ICSVERSIONS, jmf);

		notif.setEvent(eventID, eventValue, comment);
		s.copyElement(notif, null);
		return notif;
	}

	private JDFNotification createBaseNotification()
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
		for (int i = 0; i < vEmployees.size(); i++)
		{
			notif.copyElement(vEmployees.get(i), null);
		}
		notif.setNode(m_Node);
		return notif;
	}

	/**
	 * @return JDFJMF the newly created Notification JMF
	 */
	private synchronized JDFJMF createNotificationJMF()
	{
		if (docJMFNotification == null)
		{
			docJMFNotification = createJMFDoc();
		}
		return docJMFNotification.getJMFRoot();
	}

	/**
	 * Set the Status and StatusDetails of this node update the PhaseTime audit or append a new phasetime as appropriate also prepare a status JMF
	 * 
	 * @param nodeStatus the new status of the node
	 * @param nodeStatusDetails the new statusDetails of the node
	 * @param deviceStatus the new status of the device
	 * @param deviceStatusDetails the new statusDetails of the device
	 * @return true if the status changed
	 */
	public synchronized boolean setPhase(final EnumNodeStatus nodeStatus, final String nodeStatusDetails, final EnumDeviceStatus deviceStatus, final String deviceStatusDetails)
	{
		if (m_Node == null)
		{
			return setIdlePhase(deviceStatus, deviceStatusDetails);
		}

		status = deviceStatus;
		statusDetails = deviceStatusDetails;
		final JDFJMF jmfStatus = createPhaseTimeJMF();
		final JDFJMF jmfRes = createResourceJMF();

		final LinkAmount mainLinkAmount = getLinkAmount(getFirstRefID());

		final JDFAuditPool auditPool = m_Node.getCreateAuditPool();
		// TODO rethink when to send 2 phases
		final JDFPhaseTime lastPhase = auditPool.getLastPhase(m_vPartMap, m_moduleID == null ? null : m_moduleID.stringAt(0));
		JDFPhaseTime nextPhase = lastPhase;
		final boolean bEnd = EnumNodeStatus.Completed.equals(nodeStatus) || EnumNodeStatus.Aborted.equals(nodeStatus);
		boolean bChanged = bEnd || lastPhase == null; // no previous audit or
		// over and out

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

		if (nextPhase != null)
		{
			generateResourceResponse(jmfRes);
		}

		if (lastPhase != null && nextPhase != lastPhase) // we explicitly added
		// a new phasetime audit, thus we need to add a closing JMF for the original jobPhase
		{
			bChanged = true;
			closeJobPhase(jmfStatus, mainLinkAmount, lastPhase, nextPhase); // attention
			// -
			// resets la to 0 - all calls after this have the new amounts
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
		jmfRes.eraseEmptyAttributes(true);
		return bChanged;
	}

	/**
	 * append resource audits and set output resource to available, if necessary
	 */
	private void appendResourceAudits()
	{
		if (vLinkAmount != null)
		{
			for (int i = 0; i < vLinkAmount.length; i++)
			{
				setResourceAudit(vLinkAmount[i].refID, EnumReason.ProcessResult);
				vLinkAmount[i].updateNodeResource();
			}
		}
	}

	private synchronized JDFJMF createResourceJMF()
	{
		docJMFResource = createJMFDoc();
		return docJMFResource.getJMFRoot();
	}

	private synchronized JDFJMF createPhaseTimeJMF()
	{
		docJMFPhaseTime = createJMFDoc();
		return docJMFPhaseTime.getJMFRoot();
	}

	/**
	 * creates the base jmf
	 * @return the @see JDFDoc representing the JMF
	 */
	private JDFDoc createJMFDoc()
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
	private boolean setIdlePhase(final EnumDeviceStatus deviceStatus, final String deviceStatusDetails)
	{
		boolean bChanged = docJMFPhaseTime == null; // first aftersetPhase
		final JDFResponse r = bChanged ? null : docJMFPhaseTime.getJMFRoot().getResponse(0);
		final JDFDeviceInfo lastDevInfo = r == null ? null : r.getDeviceInfo(-1);
		status = deviceStatus;
		statusDetails = deviceStatusDetails;

		bChanged = bChanged || !ContainerUtil.equals(deviceStatusDetails, lastDevInfo == null ? null : lastDevInfo.getAttribute(AttributeName.STATUSDETAILS, null, null));
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
	 */
	private void fillDeviceInfo(final EnumDeviceStatus deviceStatus, final String deviceStatusDetails, final JDFDeviceInfo newDevInfo, final LinkAmount la)
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
			newDevInfo.setSpeed(la.getAmount(la.lastBag.speed));
		}
		for (int i = 0; i < vEmployees.size(); i++)
		{
			newDevInfo.copyElement(vEmployees.get(i), null);
		}

	}

	private void updateCurrentJobPhase(final EnumNodeStatus nodeStatus, final String nodeStatusDetails, final EnumDeviceStatus deviceStatus, final String deviceStatusDetails, final JDFJMF jmf,
			final LinkAmount la, final JDFPhaseTime pt2, final boolean bEnd)
	{
		final JDFResponse respStatus = (JDFResponse) jmf.appendMessageElement(JDFMessage.EnumFamily.Response, JDFMessage.EnumType.Status);
		final JDFDeviceInfo deviceInfo = respStatus.getCreateDeviceInfo(0);
		// if(!bEnd) // don't write a jobphase for an idle device
		// {
		final JDFJobPhase jp = deviceInfo.createJobPhaseFromPhaseTime(pt2);
		setJobPhaseAmounts(la, jp);
		jp.setQueueEntryID(queueEntryID);
		// }

		fillDeviceInfo(deviceStatus, deviceStatusDetails, deviceInfo, la);

		if (m_Node != null && nodeStatus != null) // may be null if idle
		{
			m_Node.setPartStatus(m_vPartMap, nodeStatus, nodeStatusDetails);
			getVResLink(2);// update the nodes links

			if (bEnd)
			{
				pt2.deleteNode(); // zapp the last phasetime
			}
			else
			{
				pt2.setLinks(getVResLink(1));
				pt2.eraseEmptyAttributes(true);
			}
		}
	}

	private JDFResponse closeJobPhase(final JDFJMF jmf, final LinkAmount la, final JDFPhaseTime pt1, @SuppressWarnings("unused") final JDFPhaseTime pt2)
	{
		final JDFResponse respStatus = (JDFResponse) jmf.appendMessageElement(JDFMessage.EnumFamily.Response, JDFMessage.EnumType.Status);
		final JDFDeviceInfo deviceInfo = respStatus.appendDeviceInfo();
		fillDeviceInfo(status, statusDetails, deviceInfo, la);
		final JDFJobPhase jp = deviceInfo.createJobPhaseFromPhaseTime(pt1);
		jp.setJobID(m_Node.getJobID(true));
		jp.setJobPartID(m_Node.getJobPartID(false));
		jp.setQueueEntryID(queueEntryID);
		setJobPhaseAmounts(la, jp);
		pt1.setLinks(getVResLink(1));

		// cleanup!
		if (vLinkAmount != null)
		{
			for (int i = 0; i < vLinkAmount.length; i++)
			{
				vLinkAmount[i].addPhase(0, 0, true, true);
			}
		}
		return respStatus;
	}

	private void appendProcessRun(final EnumNodeStatus nodeStatus, final JDFAuditPool ap)
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
	 * @param amounts
	 * @param jmfRes
	 */
	private void generateResourceResponse(final JDFJMF jmfRes)
	{
		final VElement vResResourceInfo = getVResLink(3);
		final JDFSignal sig = jmfRes.appendSignal(EnumType.Resource);
		sig.copyAttribute(AttributeName.ICSVERSIONS, jmfRes);

		final JDFResourceQuParams rqp = sig.appendResourceQuParams();
		rqp.setJDF(m_Node);
		rqp.setExact(false);
		rqp.setQueueEntryID(queueEntryID);
		boolean bAllExact = true;

		if (vResResourceInfo != null)
		{
			final Iterator<KElement> vResResourceInfoIterator = vResResourceInfo.iterator();
			while (vResResourceInfoIterator.hasNext())
			{
				final JDFResourceInfo ri = sig.appendResourceInfo();
				if (workType != null)
				{
					ri.appendMISDetails().setWorkType(workType);
				}
				final JDFResourceLink rl = (JDFResourceLink) vResResourceInfoIterator.next();
				final LinkAmount la = getLinkAmount(rl.getrRef());
				final boolean bExact = la != null && la.isCopyResInfo();
				bAllExact = bAllExact && bExact;
				rqp.setExact(bExact);
				ri.setLink(rl, rqp);
			}
		}
		rqp.setExact(bAllExact);
	}

	/**
	 * update all status relevant data in jobphase also update percentComplete if we don't have amounts
	 * 
	 * @param la
	 * @param jp
	 */
	private void setJobPhaseAmounts(final LinkAmount la, final JDFJobPhase jp)
	{
		if (jp == null)
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
			if (la.getAmount(la.lastBag.totalAmount) != 0)
			{
				jp.setPhaseAmount(la.getAmount(la.lastBag.phaseAmount));
				jp.setAmount(la.getAmount(la.lastBag.totalAmount));
			}
			if (la.getAmount(la.lastBag.totalWaste) != 0)
			{
				jp.setPhaseWaste(la.getAmount(la.lastBag.phaseWaste));
				jp.setWaste(la.getAmount(la.lastBag.totalWaste));
			}
		}
		else
		{
			if ((la.getAmount(la.lastBag.totalAmount) + la.getAmount(la.lastBag.totalWaste)) != 0)
			{
				jp.setPhaseAmount(la.getAmount(la.lastBag.phaseAmount) + la.getAmount(la.lastBag.phaseWaste));
				jp.setAmount(la.getAmount(la.lastBag.totalAmount) + la.getAmount(la.lastBag.totalWaste));
			}

		}
		double total = 0;

		total = la.startAmount;
		if (total != 0)
		{
			jp.setTotalAmount(total);
		}
	}

	/**
	 * @param resLink
	 * @param n : 1=phaseTime, 2=node, 3=resourceinfo
	 * @return VElement a vector of resourcelinks
	 */
	private VElement getVResLink(final int n)
	{
		if (vLinkAmount == null || m_Node == null)
		{
			return null;
		}
		final VElement vRet = new VElement();
		for (int i = 0; i < vLinkAmount.length; i++)
		{
			final LinkAmount la = vLinkAmount[i];
			if (n == 1)
			{
				vRet.add(la.getPhaseTimeLink());
			}
			if (n == 2)
			{
				vRet.add(la.updateNodeLink());
			}
			if (n == 3)
			{
				vRet.add(la.getResourceInfoLink());
			}
			if (n == 4)
			{
				vRet.add(la.getResourceAuditLink());
			}
		}
		return vRet;
	}

	/**
	 * @return the docJMFPhaseTime
	 */
	public synchronized JDFDoc getDocJMFPhaseTime()
	{
		if (docJMFPhaseTime == null)
		{
			setIdlePhase(EnumDeviceStatus.Idle, null);
		}
		return (JDFDoc) docJMFPhaseTime.clone();
	}

	/**
	 * @return the docJMFResource
	 */
	public synchronized JDFDoc getDocJMFResource()
	{
		if (docJMFResource == null)
		{
			return null;
		}
		return (JDFDoc) docJMFResource.clone();
	}

	/**
	 * @return the docJMFNotification
	 */
	public synchronized JDFDoc getDocJMFNotification(final boolean bClean)
	{
		JDFDoc ret = null;
		if (docJMFNotification != null)
		{
			if (bClean)
			{
				ret = docJMFNotification;
				docJMFNotification = null;
			}
			else
			{
				ret = (JDFDoc) docJMFNotification.clone();
			}
		}
		return ret;
	}

	// /////////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////

	/**
	 * container class to set amounts and waste in phaseTime
	 */
	private class LinkAmount
	{
		private class AmountBag
		{
			/**
			 * refID of the resourceLink to set
			 */
			protected double totalAmount;
			protected double phaseAmount;
			protected double totalWaste;
			protected double phaseWaste;
			protected long lastCall = 0;
			protected double speed = 0.0;
			protected double amountLast = 0;

			@Override
			public String toString()
			{
				return "[AmountBag totalAmount=" + totalAmount + " phaseAmount=" + phaseAmount + " totalWaste=" + totalWaste + " phaseWaste=" + phaseWaste + " ]";
			}

			/**
			 * 
			 * @param _refID refID of the resource that is being counted
			 */
			protected AmountBag()
			{
				reset();
			}

			/**
			 *
			 */
			protected void reset()
			{
				totalAmount = 0;
				phaseAmount = 0;
				totalWaste = 0;
				phaseWaste = 0;
			}

			/**
			 * copy ctor
			 * 
			 * @param bag
			 */
			protected AmountBag(final AmountBag bag)
			{
				totalAmount = bag.totalAmount;
				phaseAmount = bag.phaseAmount;
				totalWaste = bag.totalWaste;
				phaseWaste = bag.phaseWaste;
			}

			/**
			 * @param amount
			 * @param waste
			 * @param bNewPhase
			 * @param sumTotal if true, also sum up the total amounts, else only phase
			 */
			protected void addPhase(final double amount, final double waste, final boolean bNewPhase, final boolean sumTotal)
			{
				if (sumTotal)
				{
					totalAmount += amount;
					totalWaste += waste;
				}
				if (bNewPhase)
				{
					phaseAmount = amount;
					phaseWaste = waste;
				}
				else
				{
					phaseAmount += amount;
					phaseWaste += waste;
				}
				final long t = System.currentTimeMillis();
				if ((totalAmount + totalWaste) == 0) // else total takes over
				{
					if (lastCall == 0)
					{
						lastCall = t;
						amountLast = 0.0;

					}
					// wait at least 30 seconds to calculate speed -else bad fluctuations...
					else if (t - lastCall < 30000 && !bNewPhase)
					{
						amountLast += amount + waste;
					}
					else
					// calculate current speed
					{
						amountLast += amount + waste;
						double deltaT = t - lastCall;
						deltaT /= 3600000.0; // milliseconds / hour
						speed = deltaT == 0.0 ? 0.0 : amountLast / deltaT;
						amountLast = 0.0;
						lastCall = t;
					}
				}
			}
		} // end AmountBag

		// ///////////////////////////////////////////////////////////////////

		protected double startAmount = 0;
		protected double startWaste = 0;
		protected JDFResourceLink rl;
		protected String refID;
		protected final AmountBag lastBag;
		protected VJDFAttributeMap vResPartMap;
		private boolean bInteger = false;

		protected LinkAmount()
		{
			lastBag = new AmountBag();
		}

		protected LinkAmount(final JDFResourceLink _rl)
		{
			final JDFNode dump = new JDFDoc("JDF").getJDFRoot();
			dump.appendResourceLinkPool().copyElement(_rl, null);
			final JDFResource target = _rl.getTarget();
			bInteger = isInteger(target);
			dump.appendResourcePool().copyElement(target, null);
			rl = (JDFResourceLink) dump.getResourceLinkPool().getElement(_rl.getNodeName(), null, 0);

			lastBag = new AmountBag();
			refID = rl.getrRef();
			if (m_vPartMap == null)
			{
				vResPartMap = rl.getPartMapVector();
			}
			else
			{
				vResPartMap = new VJDFAttributeMap(m_vPartMap);
			}

			if (vResPartMap != null)
			{
				if (m_ignoreParts != null)
				{
					vResPartMap.removeKeys(m_ignoreParts.getSet());
				}
				// final VString partIDKeys = target.getPartIDKeys();
				// Set keyset=partIDKeys==null ? null : partIDKeys.getSet();
				// vResPartMap.reduceMap(keyset);
				if (vResPartMap.size() == 0)
				{
					vResPartMap = null;
				}
			}
			if (isTrackWaste())
			{
				final VJDFAttributeMap vMap = new VJDFAttributeMap(vResPartMap);
				vMap.put(EnumPartIDKey.Condition, "Good");
				startAmount = rl.getAmountPoolSumDouble(AttributeName.AMOUNT, vMap);
				lastBag.totalAmount += rl.getAmountPoolSumDouble(AttributeName.ACTUALAMOUNT, vMap);

				vMap.put(EnumPartIDKey.Condition, "Waste");
				startWaste = rl.getAmountPoolSumDouble(AttributeName.MAXAMOUNT, vMap);
				if (startWaste <= 0.0)
				{
					startWaste = rl.getAmountPoolSumDouble(AttributeName.AMOUNT, vMap);
				}
				lastBag.totalAmount += rl.getAmountPoolSumDouble(AttributeName.ACTUALAMOUNT, vMap);
			}
			else
			{
				startAmount = rl.getAmountPoolSumDouble(AttributeName.AMOUNT, vResPartMap);
				lastBag.totalAmount += rl.getAmountPoolSumDouble(AttributeName.ACTUALAMOUNT, vResPartMap);
			}
		}

		/**
		 * should the resource be displayed as an integer?
		 * @param target
		 * @return true if the resource is an integer type
		 */
		private boolean isInteger(final JDFResource target)
		{
			return (target instanceof JDFUsageCounter) || (target instanceof JDFMedia) || (target instanceof JDFExposedMedia) || (target instanceof JDFComponent);
		}

		/**
		 * @return the updated reslink
		 */
		protected JDFResourceLink updateNodeLink()
		{
			final JDFResourceLink nodeLink = m_Node.getLink(0, null, new JDFAttributeMap(AttributeName.RREF, rl.getrRef()), null);
			final VJDFAttributeMap vMap = new VJDFAttributeMap(vResPartMap);
			if (vMap.size() == 0)
			{
				vMap.add(new JDFAttributeMap());
			}
			if (nodeLink != null)
			{

				if (isTrackWaste())
				{
					vMap.put(EnumPartIDKey.Condition, "Good");
					nodeLink.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(lastBag.totalAmount), null, vMap);
					vMap.put(EnumPartIDKey.Condition, "Waste");
					nodeLink.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(lastBag.totalWaste), null, vMap);
				}
				else
				{
					nodeLink.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(lastBag.totalAmount + lastBag.totalWaste), null, vMap);
				}
				// update output status
				if (lastBag.totalAmount > 0)
				{
					final JDFResource r = nodeLink.getLinkRoot();
					if (vResPartMap != null)
					{
						for (int i = 0; i < vResPartMap.size(); i++)
						{
							final JDFAttributeMap m = vResPartMap.elementAt(i);
							final JDFResource rp = r.getPartition(m, null);
							if (rp != null)
							{
								rp.setResStatus(EnumResStatus.Available, false);
							}
						}
					}
					else
					{
						r.setResStatus(EnumResStatus.Available, false);
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
						final VElement leaves = r.getLeaves(false);
						for (int j = 0; j < leaves.size(); j++)
						{
							final JDFResource rr = (JDFResource) leaves.get(j);
							final VJDFAttributeMap vMap = rr.getPartMapVector(false);
							if (m_vPartMap != null && m_vPartMap.overlapsMap(vMap))
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
						for (int j = 0; j < leaves.size(); j++)
						{
							final JDFResource rr = (JDFResource) leaves.get(j);
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
			if (lastBag.lastCall == 0)
			{
				lastBag.speed = 0;
				lastBag.lastCall = t;
				lastBag.amountLast = 0;
			}
			else
			{
				double dt = t - lastBag.lastCall;
				if (dt > 30000)
				{
					dt /= 3600000.;
					lastBag.speed = (lastBag.totalAmount + lastBag.totalWaste - lastBag.amountLast) / dt;
					lastBag.amountLast = lastBag.totalAmount + lastBag.totalWaste;
					lastBag.lastCall = t;
				}
			}
		}

		/**
		 * @return get a link to dump into a paseTime audit
		 */
		protected JDFResourceLink getPhaseTimeLink()
		{
			cleanAmounts();
			setPhaseAmounts();
			return rl;
		}

		/**
		 * @return JDFResourceLink the resource link for a resource audit
		 */
		protected JDFResourceLink getResourceAuditLink()
		{
			cleanAmounts();
			setTotalAmounts();
			return rl;
		}

		/**
		 * @return JDFResourceLink the resource link for a resourceInfo
		 */
		protected JDFResourceLink getResourceInfoLink()
		{
			cleanAmounts();
			return setTotalAmounts();
		}

		private JDFResourceLink setPhaseAmounts()
		{
			final VJDFAttributeMap vMap = new VJDFAttributeMap(vResPartMap);
			if (vMap.size() == 0)
			{
				vMap.add(new JDFAttributeMap());
			}
			if (isTrackWaste())
			{
				vMap.put(EnumPartIDKey.Condition, "Good");
				if (lastBag.totalAmount != 0 || startAmount > 0)
				{
					rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(lastBag.phaseAmount), null, vMap);
				}
				if (startAmount != 0)
				{
					rl.setAmountPoolAttribute(AttributeName.AMOUNT, formatAmount(startAmount), null, vMap);
				}
				vMap.put(EnumPartIDKey.Condition, "Waste");
				if (lastBag.totalWaste != 0 || startWaste > 0)
				{
					rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(lastBag.phaseWaste), null, vMap);
				}
				if (startWaste != 0)
				{
					rl.setAmountPoolAttribute(AttributeName.AMOUNT, formatAmount(startWaste), null, vMap);
				}
			}
			else
			{
				if (lastBag.totalAmount + lastBag.totalWaste != 0 || startAmount + startWaste > 0)
				{
					rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(lastBag.phaseAmount + lastBag.phaseWaste), null, vMap);
				}
				if (startAmount + startWaste != 0)
				{
					rl.setAmountPoolAttribute(AttributeName.AMOUNT, formatAmount(startAmount + startWaste), null, vMap);
				}
			}
			return rl;
		}

		private JDFResourceLink setTotalAmounts()
		{
			final VJDFAttributeMap vMap = new VJDFAttributeMap(vResPartMap);
			if (vMap.size() == 0)
			{
				vMap.add(new JDFAttributeMap());
			}
			if (isTrackWaste())
			{
				vMap.put(EnumPartIDKey.Condition, "Good");
				if (lastBag.totalAmount != 0)
				{
					rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(lastBag.totalAmount), null, vMap);
				}
				if (startAmount != 0)
				{
					rl.setAmountPoolAttribute(AttributeName.AMOUNT, formatAmount(startAmount), null, vMap);
				}
				vMap.put(EnumPartIDKey.Condition, "Waste");
				if (lastBag.totalWaste != 0)
				{
					rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(lastBag.totalWaste), null, vMap);
				}
				if (startWaste != 0)
				{
					rl.setAmountPoolAttribute(AttributeName.AMOUNT, formatAmount(startWaste), null, vMap);
				}
			}
			else
			{
				if (lastBag.totalAmount + lastBag.totalWaste != 0)
				{
					rl.setAmountPoolAttribute(AttributeName.ACTUALAMOUNT, formatAmount(lastBag.totalAmount + lastBag.totalWaste), null, vMap);
				}
				if (startAmount + startWaste != 0)
				{
					rl.setAmountPoolAttribute(AttributeName.AMOUNT, formatAmount(startAmount + startWaste), null, vMap);
				}
			}
			return rl;
		}

		/**
		 * change the value to integer, if required
		 * @return the formatted amount, either as integer or double
		 */
		protected double getAmount(final double amount)
		{
			return bInteger ? ((int) amount) : amount;
		}

		/**
		 * @return the formatted amount, either as integer or double
		 */
		protected String formatAmount(final double amount)
		{
			return bInteger ? StringUtil.formatInteger((int) amount) : StringUtil.formatDouble(amount);
		}

		/**
		 * 
		 */
		private void cleanAmounts()
		{

			rl.removeAttribute(AttributeName.AMOUNT);
			rl.removeAttribute(AttributeName.ACTUALAMOUNT);
			rl.removeChild(ElementName.AMOUNTPOOL, null, 0);
		}

		/**
		 * @param amount
		 * @param waste
		 * @param bNewPhase
		 * @param sumTotal if true, also sum up the total amounts, else only phase
		 */
		protected void addPhase(final double amount, final double waste, final boolean bNewPhase, final boolean sumTotal)
		{
			lastBag.addPhase(amount, waste, bNewPhase, sumTotal);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			final StringBuffer sb = new StringBuffer();
			sb.append("LinkAmount: refID=");
			sb.append(refID);
			sb.append("\n");
			sb.append(vResPartMap);
			sb.append("\nstartAmount: ");
			sb.append(startAmount);
			sb.append("\nstartWaste: ");
			sb.append(startWaste);
			sb.append(lastBag);

			return sb.toString();

		}

		protected boolean linkFitsKey(final String key)
		{
			if (key == null)
			{
				return true;
			}

			return rl.matchesString(key);
		}

		protected boolean linkFitsKey(final Set keys)
		{
			if (keys == null)
			{
				return false;
			}

			return keys.contains("*") || keys.contains(rl.getNamedProcessUsage()) || keys.contains(rl.getLinkedResourceName()) || keys.contains(refID)
					|| keys.contains(rl.getAttribute(AttributeName.USAGE));
		}

		/**
		 * @return the bCopyResInfo
		 */
		public boolean isCopyResInfo()
		{
			return linkFitsKey(setCopyResInfo);
		}

		/**
		 * @return the bTrackWaste
		 */
		public boolean isTrackWaste()
		{
			return linkFitsKey(setTrackWaste);
		}

	}

	/*
	 * @return the m_deviceID
	 */
	public String getDeviceID()
	{
		return m_deviceID;
	}

	/*
	 * @return the m_moduleID
	 */
	public VString getModuleeID()
	{
		return m_moduleID;
	}

	/**
	 * @param m_deviceid the m_deviceID to set
	 */
	public void setDeviceID(final String deviceid)
	{
		m_deviceID = deviceid;
	}

	/**
	 * @param m_deviceid the m_deviceID to set
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
	 * @param b tracking on or off
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
	 * @param rl the resourcelink to the resource to copy
	 * @param b tracking on or off
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
	 * 
	 * @param resID the resource ID to set/track reason for the audit
	 * @return JDFResourceAudit the generated audit
	 */
	public synchronized JDFResourceAudit setResourceAudit(final String resID, final EnumReason reason)
	{
		final LinkAmount la = getLinkAmount(resID);
		if (la == null)
		{
			return null;
		}
		final JDFAuditPool ap = m_Node.getCreateAuditPool();

		final JDFResourceAudit resourceAudit = ap.addResourceAudit(null);
		resourceAudit.setContentsModified(false);
		resourceAudit.setReason(reason);

		resourceAudit.copyElement(la.getResourceAuditLink(), null);
		resourceAudit.setPartMapVector(m_vPartMap);

		return resourceAudit;
	}

	/**
	 *
	 */
	public JDFProcessRun setProcessResult(@SuppressWarnings("unused") final EnumNodeStatus endStatus)
	{
		setPhase(EnumNodeStatus.Completed, null, EnumDeviceStatus.Idle, null);
		final JDFAuditPool ap = m_Node.getCreateAuditPool();
		final JDFProcessRun pr = (JDFProcessRun) ap.getAudit(-1, EnumAuditType.ProcessRun, null, m_vPartMap);
		return pr;
	}

	/**
	 * @param queueEntryID
	 */
	public void setQueueEntryID(final String _queueEntryID)
	{
		queueEntryID = _queueEntryID;
	}

	/**
	 * @param queueEntryID
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

	public EnumDeviceStatus getStatus()
	{
		return status;
	}

	public String getStatusDetails()
	{
		return statusDetails;
	}

	public JDFDate getStartDate()
	{
		return startDate;
	}

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
		return la == null ? 0 : la.getAmount(la.startAmount);
	}

	/**
	 * @param refID the resource ID or name
	 * @return the planned waste for the resource
	 */
	public double getPlannedWaste(final String refID)
	{
		final LinkAmount la = getLinkAmount(refID);
		return la == null ? 0 : la.getAmount(la.startWaste);
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
	 * @param percent the percentage to set
	 */
	public void setPercentComplete(final double percent)
	{
		percentComplete = percent;
	}

	/**
	 * update percentComplete by percent
	 * @param percent the percentage to increment
	 */
	public void updatePercentComplete(final double percent)
	{
		percentComplete += percent;
	}

	/**
	 * @param icsVersions the icsVersions to set
	 */
	public void setIcsVersions(final VString pICSVersions)
	{
		this.icsVersions = pICSVersions;
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
	 * @param employees
	 */
	public void replaceEmployees(final Vector<JDFEmployee> employees)
	{
		if (employees == null || employees.size() == 0)
		{
			if (vEmployees.size() > 0)
			{
				clearEmployees();
			}
		}
		else if (!new VElement(employees).isEqual(new VElement(vEmployees)))
		{
			vEmployees.clear();
			vEmployees.addAll(employees);
			resetPhase();
		}

	}

}