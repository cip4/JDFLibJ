/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2022 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

package org.cip4.jdflib.jmf;

import java.util.Vector;

import org.cip4.jdflib.auto.JDFAutoDeviceFilter;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoNotification.EnumClass;
import org.cip4.jdflib.auto.JDFAutoQueueFilter.EnumUpdateGranularity;
import org.cip4.jdflib.auto.JDFAutoResourceInfo.EnumScope;
import org.cip4.jdflib.auto.JDFAutoResourceQuParams.EnumResourceDetails;
import org.cip4.jdflib.auto.JDFAutoShutDownCmdParams.EnumShutDownType;
import org.cip4.jdflib.auto.JDFAutoStatusQuParams.EnumDeviceDetails;
import org.cip4.jdflib.auto.JDFAutoStatusQuParams.EnumJobDetails;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.NodeIdentifier;
import org.cip4.jdflib.resource.JDFMilestone;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.util.StringUtil;

/**
 * factory for creating JMF messages
 *
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         June 20, 2009 split off from JMFFactory
 */
public class JMFBuilder implements Cloneable
{
	private String acknowledgeURL;
	private String senderID;
	private String agentName;
	private String agentVersion;

	/**
	 * @return the acknowledgeURL
	 */
	public String getAcknowledgeURL()
	{
		return acknowledgeURL;
	}

	/**
	 * @param acknowledgeURL the acknowledgeURL to set
	 */
	public void setAcknowledgeURL(final String acknowledgeURL)
	{
		this.acknowledgeURL = acknowledgeURL;
	}

	/**
	 *
	 */
	public JMFBuilder()
	{
		super();
		acknowledgeURL = null;
		agentName = JDFAudit.getStaticAgentName();
		agentVersion = JDFAudit.getStaticAgentVersion();
		senderID = JDFJMF.getTheSenderID();
	}

	/**
	 * build a JMF SuspendQueueEntry command
	 *
	 * @param queueEntryId queue entry ID of the queue to suspend
	 * @return the message
	 */
	public JDFJMF buildSuspendQueueEntry(final String queueEntryId)
	{
		final JDFJMF jmf = buildQueueEntryCommand(queueEntryId, EnumType.SuspendQueueEntry);
		return jmf;
	}

	/**
	 * build a JMF HoldQueueEntry command
	 *
	 * @param queueEntryId queue entry ID of the queue to hold
	 * @return the message
	 */
	public JDFJMF buildHoldQueueEntry(final String queueEntryId)
	{
		final JDFJMF jmf = buildQueueEntryCommand(queueEntryId, EnumType.HoldQueueEntry);
		return lastSteps(jmf);
	}

	/**
	 * build a JMF ResumeQueueEntry command
	 *
	 * @param queueEntryId queue entry ID of the queue to resume
	 * @return the message
	 */
	public JDFJMF buildResumeQueueEntry(final String queueEntryId)
	{
		final JDFJMF jmf = buildQueueEntryCommand(queueEntryId, EnumType.ResumeQueueEntry);
		return lastSteps(jmf);
	}

	/**
	 * build a JMF AbortQueueEntry command
	 *
	 * @param queueEntryId queue entry ID of the queue to abort
	 * @return the message
	 */
	public JDFJMF buildAbortQueueEntry(final String queueEntryId)
	{
		final JDFJMF jmf = buildQueueEntryCommand(queueEntryId, EnumType.AbortQueueEntry);
		JDFAbortQueueEntryParams aqp = jmf.getCommand(0).getAbortQueueEntryParams();
		aqp.setEndStatus(EnumNodeStatus.Aborted);
		return lastSteps(jmf);
	}

	/**
	 * build a JMF AbortQueueEntry command
	 *
	 * @param queueEntryId queue entry ID of the queue to abort
	 * @return the message
	 */
	public JDFJMF buildReturnQueueEntry(final String queueEntryId)
	{
		final JDFJMF jmf = createJMF(EnumFamily.Command, EnumType.ReturnQueueEntry);
		final JDFCommand command = jmf.getCommand(0);
		command.appendReturnQueueEntryParams().setQueueEntryID(queueEntryId);
		return lastSteps(jmf);
	}

	/**
	 * @param queueEntryId
	 * @param typ
	 * @return the jmf
	 */
	JDFJMF buildQueueEntryCommand(final String queueEntryId, final EnumType typ)
	{
		if (queueEntryId == null)
		{
			return null;
		}
		final JDFJMF jmf = createJMF(EnumFamily.Command, typ);
		final JDFCommand command = jmf.getCommand(0);
		String params = typ.getName() + "Params";
		KElement paramsEle = command.appendElement(params);
		JDFQueueFilter qf = (JDFQueueFilter) paramsEle.appendElement(ElementName.QUEUEFILTER);
		JDFQueueEntryDef qed = qf.appendQueueEntryDef(queueEntryId);
		return lastSteps(jmf);
	}

	/**
	 * create a JMF that has all builder specific details filled in
	 *
	 * @param family
	 * @param typ
	 * @return
	 */
	public JDFJMF createJMF(final EnumFamily family, final EnumType typ)
	{
		final JDFJMF jmf = JDFJMF.createJMF(family, typ);
		final JDFMessage m = jmf.getMessageElement(null, null, 0);
		if (EnumFamily.Command.equals(family))
		{
			final JDFCommand c = (JDFCommand) m;
			if (acknowledgeURL != null)
			{
				c.setAcknowledgeURL(acknowledgeURL);
			}
		}
		else if (EnumFamily.Query.equals(family))
		{
			final JDFQuery q = (JDFQuery) m;
			if (acknowledgeURL != null)
			{
				q.setAcknowledgeURL(acknowledgeURL);
			}
		}
		jmf.setSenderID(senderID);
		jmf.setAgentName(agentName);
		jmf.setAgentVersion(agentVersion);
		m.setSenderID(senderID);
		m.setAgentName(agentName);
		m.setAgentVersion(agentVersion);
		return jmf;
	}

	/**
	 * @param jmf
	 * @return
	 */
	JDFJMF lastSteps(final JDFJMF jmf)
	{
		return jmf;
	}

	/**
	 * build a JMF RemoveQueueEntry command
	 *
	 * @param queueEntryId queue entry ID of the queue to remove
	 * @return the message
	 */
	public JDFJMF buildRemoveQueueEntry(final String queueEntryId)
	{
		final JDFJMF jmf = buildQueueEntryCommand(queueEntryId, EnumType.RemoveQueueEntry);
		return lastSteps(jmf);
	}

	/**
	 * build a JMF Status query
	 *
	 * @param deviceDetails the device details
	 * @param jobDetails the status details
	 * @return the message
	 */
	public JDFJMF buildStatus(final EnumDeviceDetails deviceDetails, final EnumJobDetails jobDetails)
	{
		final JDFJMF jmf = createJMF(EnumFamily.Query, EnumType.Status);
		final JDFStatusQuParams statusQuParams = jmf.getCreateQuery(0).getCreateStatusQuParams(0);
		statusQuParams.setDeviceDetails(deviceDetails);
		statusQuParams.setJobDetails(jobDetails);
		return lastSteps(jmf);
	}

	/**
	 * build a JMF Status query
	 *
	 * @param bExact if true
	 * @return the message
	 */
	public JDFJMF buildResourceQuery(final boolean bExact)
	{
		final JDFJMF jmf = createJMF(EnumFamily.Query, EnumType.Resource);
		final JDFResourceQuParams resParams = jmf.getCreateQuery(0).getCreateResourceQuParams(0);
		resParams.setExact(bExact);
		return lastSteps(jmf);
	}

	/**
	 * build a JMF Status query
	 *
	 * @param bExact if true
	 * @param resLink
	 * @return the message
	 */
	public JDFJMF buildResourceSignal(final boolean bExact, final JDFResourceLink resLink)
	{
		final JDFJMF jmf = buildResourceQuery(bExact);
		final JDFJMF jmfSignal = createJMF(EnumFamily.Signal, EnumType.Resource);
		final JDFSignal signal = jmfSignal.getSignal(0);
		if (resLink != null)
		{
			JDFResourceInfo.createResourceInfo(signal, resLink, bExact);
		}
		signal.setQuery(jmf.getQuery(0));
		signal.copyElement(jmf.getQuery(0).getResourceQuParams(), null);
		return lastSteps(jmfSignal);
	}

	/**
	 * build a JMF Status query
	 *
	 * @param deviceDetails the device details
	 * @param jobDetails the status details
	 * @return the message
	 */
	public JDFJMF buildStatusSignal(final EnumDeviceDetails deviceDetails, final EnumJobDetails jobDetails)
	{
		final JDFJMF jmf = buildStatus(deviceDetails, jobDetails);
		final JDFJMF jmfSignal = createJMF(EnumFamily.Signal, EnumType.Status);
		final JDFSignal signal = jmfSignal.getSignal(0);
		signal.setQuery(jmf.getQuery(0));
		signal.copyElement(jmf.getQuery(0).getStatusQuParams(), null);
		final JDFDeviceInfo di = signal.appendDeviceInfo();
		di.setDeviceStatus(EnumDeviceStatus.Unknown);
		di.appendJobPhase();
		return lastSteps(jmfSignal);
	}

	/**
	 * build a JMF Status subscription
	 *
	 * @param subscriberURL
	 * @param repeatTime
	 * @param repeatStep
	 * @param queueEntryID
	 * @return the message
	 */
	public JDFJMF buildStatusSubscription(final String subscriberURL, final double repeatTime, final int repeatStep, final String queueEntryID)
	{
		final JDFJMF jmf = buildSubscription(EnumType.Status, subscriberURL, repeatTime, repeatStep);
		final JDFQuery query = jmf.getQuery(0);
		final JDFStatusQuParams statusQuParams = query.getCreateStatusQuParams(0);
		statusQuParams.setJobDetails(EnumJobDetails.Brief);
		statusQuParams.setDeviceDetails(EnumDeviceDetails.Brief);

		if (queueEntryID != null)
		{
			statusQuParams.setQueueEntryID(queueEntryID);
		}
		return lastSteps(jmf);
	}

	/**
	 * build a JMF Resource subscription
	 *
	 * @param subscriberURL
	 * @param repeatTime
	 * @param repeatStep
	 * @param queueEntryID
	 * @return the message
	 */
	public JDFJMF buildResourceSubscription(final String subscriberURL, final double repeatTime, final int repeatStep, final String queueEntryID)
	{
		final JDFJMF jmf = buildSubscription(EnumType.Resource, subscriberURL, repeatTime, repeatStep);
		final JDFQuery query = jmf.getQuery(0);
		final JDFResourceQuParams resourceQuParams = query.getCreateResourceQuParams(0);
		final Vector<EnumResourceClass> c = new Vector<>();
		c.add(EnumResourceClass.Consumable);
		c.add(EnumResourceClass.Handling);
		c.add(EnumResourceClass.Implementation);
		resourceQuParams.setClasses(c);
		if (queueEntryID != null)
		{
			resourceQuParams.setQueueEntryID(queueEntryID);
		}
		return lastSteps(jmf);
	}

	/**
	 * build a JMF Knownmessages query
	 *
	 * @return the message
	 */
	public JDFJMF buildKnownMessagesQuery()
	{
		return createQuery(JDFMessage.EnumType.KnownMessages).getJMFRoot();
	}

	/**
	 * build a JMF Resource query
	 *
	 * @param resType type of resource to query, e.g. Media
	 * @return the message
	 */
	public JDFJMF buildResourceCatalogQuery(final String resType)
	{
		final JDFQuery query = createQuery(JDFMessage.EnumType.Resource);
		final JDFResourceQuParams quParams = query.getCreateResourceQuParams(0);
		quParams.setResourceName(resType);
		quParams.setExact(true);
		quParams.setResourceDetails(EnumResourceDetails.Full);
		quParams.setAttribute(AttributeName.SCOPE, EnumScope.Allowed.getName());
		return query.getJMFRoot();
	}

	/**
	 * build a JMF Milestone querysignal
	 *
	 * @param milestoneType the milestone type
	 * @param jobID the jobID
	 * @return the message
	 */
	public JDFJMF buildMilestone(final String milestoneType, final String jobID)
	{
		final JDFJMF jmf = createJMF(EnumFamily.Signal, JDFMessage.EnumType.Notification);
		final JDFSignal s = jmf.getSignal(0);
		final JDFNotification n = s.appendNotification();
		n.setClass(EnumClass.Event);
		n.setJobID(jobID);
		final JDFMilestone ms = n.appendMilestone();
		ms.setMilestoneType(milestoneType);
		return jmf;
	}

	/**
	 * build a JMF KnownSubscriptions query
	 *
	 * @param url the url of the subscription - typically the sender of the message
	 * @param slaveQEID the queuentryID at the slave
	 * @return the message
	 */
	public JDFJMF buildKnownSubscriptionsQuery(final String url, final String slaveQEID)
	{
		final JDFQuery q = createQuery(JDFMessage.EnumType.KnownSubscriptions);
		final JDFSubscriptionFilter sf = q.appendSubscriptionFilter();
		sf.setURL(url);
		sf.setQueueEntryID(slaveQEID);
		return q.getJMFRoot();
	}

	/**
	 * build a JMF Knownmessages query
	 *
	 * @return the message
	 */
	public JDFJMF buildSubmissionMethodsQuery()
	{
		return createQuery(JDFMessage.EnumType.SubmissionMethods).getJMFRoot();
	}

	/**
	 * build a ResubmitQueueEntry message
	 *
	 * @param returnUrl the url of the jdf to send back to
	 * @return the jmf
	 */
	public JDFJMF buildSubmitQueueEntry(final String returnUrl)
	{
		return buildSubmitQueueEntry(returnUrl, null);
	}

	/**
	 * build a ResubmitQueueEntry message
	 *
	 * @param returnUrl the url of the jdf to send back to
	 * @return the jmf
	 */
	public JDFJMF buildSubmitQueueEntry(final String returnUrl, final String submitURL)
	{
		final JDFCommand c = createCommand(EnumType.SubmitQueueEntry);
		createDefaultFilter(c);

		final JDFQueueSubmissionParams sp = c.appendQueueSubmissionParams();
		sp.setReturnJMF(returnUrl);
		sp.setURL(submitURL);
		return c.getJMFRoot();
	}

	/**
	 * @param c
	 */
	private void createDefaultFilter(final JDFCommand c)
	{
		final JDFQueueFilter f = c.appendQueueFilter();
		f.setMaxEntries(0);
	}

	/**
	 * build a JMF Knowndevices query
	 *
	 * @param details
	 * @return the message
	 */
	public JDFJMF buildKnownDevicesQuery(final org.cip4.jdflib.auto.JDFAutoDeviceFilter.EnumDeviceDetails details)
	{
		final JDFQuery q = createQuery(JDFMessage.EnumType.KnownDevices);
		final JDFDeviceFilter deviceFilter = q.appendDeviceFilter();
		deviceFilter.setDeviceDetails(details);
		return q.getJMFRoot();
	}

	/**
	 * build a JMFNewJDF query
	 *
	 * @param jobID
	 * @param jobPartID
	 * @return the message
	 */
	public JDFJMF buildNewJDFQuery(final String jobID, final String jobPartID)
	{
		final JDFQuery q = createQuery(JDFMessage.EnumType.NewJDF);
		final JDFNewJDFQuParams nqp = q.appendNewJDFQuParams();
		nqp.setIdentifier(new NodeIdentifier(jobID, jobPartID, null));
		return q.getJMFRoot();
	}

	/**
	 * build a JMFNewJDF query
	 *
	 * @return the message
	 */
	public JDFJMF buildNewJDFCommand()
	{
		final JDFCommand c = createCommand(JDFMessage.EnumType.NewJDF);
		final JDFNewJDFCmdParams ncp = c.appendNewJDFCmdParams();
		ncp.appendIDInfo();

		return c.getJMFRoot();
	}

	/**
	 * build a JMF Notification subscription
	 *
	 * @param subscriberURL
	 * @return the message
	 */
	public JDFJMF buildNotificationSubscription(final String subscriberURL)
	{
		final JDFJMF jmf = buildSubscription(EnumType.Notification, subscriberURL, 0, 0);
		return jmf;
	}

	/**
	 * build a generic query for a given type
	 *
	 * @param typ
	 * @return the query
	 */
	private JDFQuery createQuery(final EnumType typ)
	{
		final JDFJMF jmf = createJMF(EnumFamily.Query, typ);
		return jmf.getQuery(0);
	}

	/**
	 * build a generic query for a given type
	 *
	 * @param typ
	 * @return the query
	 */
	private JDFCommand createCommand(final EnumType typ)
	{
		final JDFJMF jmf = createJMF(EnumFamily.Command, typ);
		return jmf.getCommand(0);
	}

	/**
	 * build a generic subscription for a given type
	 *
	 * @param typ
	 * @param subscriberURL
	 * @param repeatTime
	 * @param repeatStep
	 * @return the message
	 */
	private JDFJMF buildSubscription(final EnumType typ, final String subscriberURL, final double repeatTime, final int repeatStep)
	{
		final JDFJMF jmf = createJMF(EnumFamily.Query, typ);
		final JDFQuery q = jmf.getQuery(0);
		final JDFSubscription s = q.appendSubscription();
		s.setURL(subscriberURL);
		if (repeatTime > 0)
		{
			s.setRepeatTime(repeatTime);
		}
		if (repeatStep > 0)
		{
			s.setRepeatStep(repeatStep);
		}
		s.appendObservationTarget().setObservationPath("*");

		return jmf;
	}

	/**
	 * build a JMF QueueStatus query
	 *
	 * @return the message
	 */
	public JDFJMF buildQueueStatus()
	{
		final JDFJMF jmf = createJMF(EnumFamily.Query, EnumType.QueueStatus);
		return jmf;
	}

	/**
	 * build a JMF QueueStatus query
	 *
	 * @param subscriberURL
	 * @return the message
	 */
	public JDFJMF buildQueueStatusSubscription(final String subscriberURL)
	{
		final JDFJMF jmf = buildSubscription(EnumType.QueueStatus, subscriberURL, 0, 0);
		final JDFQueueFilter filter = jmf.getQuery(0).appendQueueFilter();
		filter.setUpdateGranularity(EnumUpdateGranularity.ChangesOnly);
		return jmf;
	}

	/**
	 * build a JMF RequestQueueEntry command <br/>
	 * default: JMFFactory.buildRequestQueueEntry(theQueueURL,null)
	 *
	 * @param queueURL the queue URL of the device sending the command ("where do you want your SubmitQE's delivered to?")
	 * @param nid the nodeidentifier of the requested qe, default=null
	 * @return the message
	 */
	public JDFJMF buildRequestQueueEntry(final String queueURL, final NodeIdentifier nid)
	{
		// maybe replace DeviceID with DeviceType, just to be able to decrease the
		// Proxy's knowledge about querying devices?
		final JDFJMF jmf = createJMF(EnumFamily.Command, EnumType.RequestQueueEntry);
		final JDFRequestQueueEntryParams qep = jmf.getCommand(0).appendRequestQueueEntryParams();
		qep.setQueueURL(queueURL);
		qep.setIdentifier(nid);
		return jmf;
	}

	/**
	 * create a set of default subscriptions
	 *
	 * @param url
	 * @param queueEntryID
	 * @param repeatTime
	 * @param repeatStep
	 * @return the array of subscriptions to be sent
	 */
	public JDFJMF[] createSubscriptions(final String url, final String queueEntryID, final double repeatTime, final int repeatStep)
	{
		final JDFJMF jmfs[] = new JDFJMF[4];
		jmfs[0] = buildStatusSubscription(url, repeatTime, repeatStep, queueEntryID);
		jmfs[1] = buildResourceSubscription(url, 0, 0, queueEntryID);
		jmfs[2] = buildNotificationSubscription(url);
		jmfs[3] = buildQueueStatusSubscription(url);
		return jmfs;
	}

	/**
	 * build a ResubmitQueueEntry message
	 *
	 * @param qeID
	 * @param url the url of the jdf to resubmit
	 * @return the jmf
	 */
	public JDFJMF buildResubmitQueueEntry(final String qeID, final String url)
	{
		final JDFCommand c = createCommand(EnumType.ResubmitQueueEntry);
		createDefaultFilter(c);
		final JDFResubmissionParams rp = c.appendResubmissionParams();
		rp.setQueueEntryID(qeID);
		rp.setURL(url);
		return c.getJMFRoot();
	}

	/**
	 * build a Shutdown message
	 *
	 * @param typ hard or soft shutdown?
	 *
	 * @return the jmf
	 */
	public JDFJMF buildShutdownCommand(final EnumShutDownType typ)
	{
		final JDFCommand c = createCommand(EnumType.ShutDown);
		createDefaultFilter(c);
		final JDFShutDownCmdParams cp = c.appendShutDownCmdParams();
		cp.setShutDownType(typ);
		return c.getJMFRoot();
	}

	/**
	 * build a stopPersistentChannelParams message
	 *
	 * @param channelID
	 * @param qeID
	 * @param url the url of the subscription
	 * @return the jmf
	 */
	public JDFJMF buildStopPersistentChannel(final String channelID, final String qeID, final String url)
	{
		final JDFCommand c = createCommand(EnumType.StopPersistentChannel);
		final JDFStopPersChParams scp = c.appendStopPersChParams();
		scp.setChannelID(channelID);
		scp.setURL(url);
		scp.setQueueEntryID(qeID);
		return c.getJMFRoot();
	}

	/**
	 * create a new jmf message with some heuristic parameters
	 *
	 * @param family
	 * @param type
	 * @return
	 */
	public JDFJMF newJMF(final EnumFamily family, final String type)
	{
		final EnumType typ = EnumType.getEnum(type);
		final JDFJMF jmfRoot;
		if (EnumType.Status.getName().equals(type) && EnumFamily.Query.equals(family))
		{
			jmfRoot = buildStatus(EnumDeviceDetails.Brief, EnumJobDetails.Brief);
		}
		else if (EnumType.KnownDevices.getName().equals(type) && EnumFamily.Query.equals(family))
		{
			jmfRoot = buildKnownDevicesQuery(JDFAutoDeviceFilter.EnumDeviceDetails.Full);
		}
		else
		{
			jmfRoot = createJMF(family, typ);
		}

		return jmfRoot;
	}

	/**
	 *
	 * get the SenderId used for this builder
	 *
	 * @return
	 */
	public String getSenderID()
	{
		return senderID;
	}

	/**
	 *
	 * set the SenderId used for this builder
	 *
	 * @param senderID the new default senderID, if null use the static default from {@link JDFJMF}
	 */
	public void setSenderID(final String senderID)
	{
		this.senderID = senderID;
	}

	/**
	 *
	 * @see java.lang.Object#clone()
	 */
	@Override
	public JMFBuilder clone()
	{
		final JMFBuilder bNew = new JMFBuilder();
		bNew.setAcknowledgeURL(getAcknowledgeURL());
		bNew.setSenderID(getSenderID());
		bNew.setAgentName(getAgentName());
		bNew.setAgentVersion(getAgentVersion());
		return bNew;
	}

	/**
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "JMFBuilder [acknowledgeURL=" + acknowledgeURL + ", senderID=" + senderID + ", agentName=" + agentName + ", agentVersion=" + agentVersion + "]";
	}

	/**
	 *
	 * @return
	 */
	public String getAgentName()
	{
		return agentName;
	}

	/**
	 *
	 * @param agentName
	 */
	public void setAgentName(final String agentName)
	{
		this.agentName = StringUtil.getNonEmpty(agentName);
	}

	/**
	 *
	 * @return
	 */
	public String getAgentVersion()
	{
		return agentVersion;
	}

	/**
	 *
	 * @param agentVersion
	 */
	public void setAgentVersion(final String agentVersion)
	{
		this.agentVersion = StringUtil.getNonEmpty(agentVersion);
	}

}