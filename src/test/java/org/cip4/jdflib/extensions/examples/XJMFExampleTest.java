/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.extensions.examples;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoInterpretingParams.EnumPrintQuality;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoMessageService.EnumChannelMode;
import org.cip4.jdflib.auto.JDFAutoNotification.EnumClass;
import org.cip4.jdflib.auto.JDFAutoResourceCmdParams.EnumUpdateMethod;
import org.cip4.jdflib.auto.JDFAutoResourceInfo.EnumScope;
import org.cip4.jdflib.auto.JDFAutoResourceQuParams.EnumResourceDetails;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.extensions.MessageHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJMFHelper;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFMessageService;
import org.cip4.jdflib.jmf.JDFQueueEntry;
import org.cip4.jdflib.jmf.JDFResourceCmdParams;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFResourceQuParams;
import org.cip4.jdflib.jmf.JDFSubscription;
import org.cip4.jdflib.jmf.JMFBuilderFactory;
import org.cip4.jdflib.node.JDFNode.EnumActivation;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.JDFDate;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJMFExampleTest extends JDFTestCaseBase
{
	static final boolean defaultDateMilli = JDFDate.isWantISOMilliseconds();

	/**
	 *
	 */
	@Test
	public void testCommandResumeQE()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper command = xjmfHelper.appendMessage(EnumFamily.Command, XJDFConstants.ModifyQueueEntry);
		command.getHeader().setAttribute(AttributeName.ID, "C1");
		command.setXPathValue(XJDFConstants.ModifyQueueEntryParams + "/@" + AttributeName.OPERATION, "Resume");
		command.setXPathValue(XJDFConstants.ModifyQueueEntryParams + "/" + ElementName.QUEUEFILTER + "/@" + AttributeName.JOBID, "j1");
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/CommandModifyQE.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testCommandAbortQE()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper command = xjmfHelper.appendMessage(EnumFamily.Command, XJDFConstants.ModifyQueueEntry);
		command.getHeader().setAttribute(AttributeName.ID, "C1");
		command.setXPathValue(XJDFConstants.ModifyQueueEntryParams + "/@" + AttributeName.OPERATION, "Abort");
		command.setXPathValue(XJDFConstants.ModifyQueueEntryParams + "/" + ElementName.QUEUEFILTER + "/@" + AttributeName.JOBID, "j1");
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeRoundTripX(xjmfHelper.getRoot(), "jmf/CommandAbortQE.xjmf", EnumValidationLevel.Complete);
	}

	/**
	 *
	 */
	@Test
	public void testCommandForceGang()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper command = xjmfHelper.appendMessage(EnumFamily.Command, EnumType.ForceGang);
		command.getHeader().setAttribute(AttributeName.ID, "C1");
		command.setXPathValue(ElementName.GANGCMDFILTER + "/@Policy", "Optimized");
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/CommandForceGang.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testCommandRemoveQE()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper command = xjmfHelper.appendMessage(EnumFamily.Command, XJDFConstants.ModifyQueueEntry);
		command.getHeader().setAttribute(AttributeName.ID, "C1");
		command.setXPathValue(XJDFConstants.ModifyQueueEntryParams + "/@" + AttributeName.OPERATION, "Remove");
		command.setXPathValue(XJDFConstants.ModifyQueueEntryParams + "/" + ElementName.QUEUEFILTER + "/@" + AttributeName.JOBID, "j1");
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeRoundTripX(xjmfHelper.getRoot(), "jmf/CommandRemoveQE.xjmf", EnumValidationLevel.Complete);
	}

	/**
	 *
	 */
	@Test
	public void testCommandSubmitQE()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper command = xjmfHelper.appendMessage(EnumFamily.Command, EnumType.SubmitQueueEntry.getName());
		command.setXPathValue(ElementName.QUEUESUBMISSIONPARAMS + "/@" + AttributeName.URL, "http://jobserver.xjdf.org?job1");
		command.getHeader().setAttribute(AttributeName.ID, "C1");
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/commandSubmitQE.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testCommandResubmitQE()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper command = xjmfHelper.appendMessage(EnumFamily.Command, EnumType.ResubmitQueueEntry.getName());
		command.setXPathValue(ElementName.RESUBMISSIONPARAMS + "/@" + AttributeName.URL, "http://jobserver.xjdf.org?job1");
		command.setXPathValue(ElementName.RESUBMISSIONPARAMS + "/@" + AttributeName.QUEUEENTRYID, "qe1");
		command.setXPathValue(ElementName.RESUBMISSIONPARAMS + "/@" + AttributeName.UPDATEMETHOD, EnumUpdateMethod.Incremental.getName());
		command.getHeader().setAttribute(AttributeName.ID, "C1");
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "structure/commandResubmitQE.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testCommandResubmitQERemove()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper command = xjmfHelper.appendMessage(EnumFamily.Command, EnumType.ResubmitQueueEntry.getName());
		command.setXPathValue(ElementName.RESUBMISSIONPARAMS + "/@" + AttributeName.URL, "http://jobserver.xjdf.org?job1");
		command.setXPathValue(ElementName.RESUBMISSIONPARAMS + "/@" + AttributeName.QUEUEENTRYID, "qe1");
		command.setXPathValue(ElementName.RESUBMISSIONPARAMS + "/@" + AttributeName.UPDATEMETHOD, EnumUpdateMethod.Remove.getName());
		command.getHeader().setAttribute(AttributeName.ID, "C1");
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "building/commandResubmitQE.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testResponseResumeQE()
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID("DeviceID");
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper response = xjmfHelper.appendMessage(EnumFamily.Response, XJDFConstants.ModifyQueueEntry);
		response.getHeader().setAttribute(AttributeName.REFID, "C1");
		response.getHeader().setAttribute(AttributeName.ID, "R1");
		response.setAttribute(AttributeName.RETURNCODE, "0");
		final JDFQueueEntry qe = (JDFQueueEntry) response.getRoot().appendElement(ElementName.QUEUEENTRY);
		qe.setJobID("j1");
		qe.setQueueEntryID("QE1");
		qe.setStatus(EnumNodeStatus.Waiting);
		qe.setActivation(EnumActivation.Active);
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/ResponseModifyQE.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testResponseResumeQEBad()
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID("DeviceID");
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper response = xjmfHelper.appendMessage(EnumFamily.Response, XJDFConstants.ModifyQueueEntry);
		response.getHeader().setAttribute(AttributeName.REFID, "C1");
		response.getHeader().setAttribute(AttributeName.ID, "R1");
		response.setAttribute(AttributeName.RETURNCODE, "105");
		final JDFNotification not = (JDFNotification) response.appendElement(ElementName.NOTIFICATION);
		not.setAttribute(AttributeName.CLASS, "Error");
		not.appendComment().setText("Not resuming unknown QueueEntry: YourQueueEntryID");
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "building/ResponseError.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testResponseNotification()
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID("DeviceID");
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper response = xjmfHelper.appendMessage(EnumFamily.Response, XJDFConstants.ModifyQueueEntry);
		response.getHeader().setAttribute(AttributeName.REFID, "C1");
		response.getHeader().setAttribute(AttributeName.ID, "R1");
		response.setAttribute(AttributeName.RETURNCODE, "5");
		final JDFNotification n = (JDFNotification) response.appendElement(ElementName.NOTIFICATION);
		n.setClass(EnumClass.Error);
		n.appendComment().setText("StartJob unsuccessful - Device does not handle resume");
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "building/ResponseNotification.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testQueryPaper()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper q = xjmfHelper.appendMessage(EnumFamily.Query, EnumType.Resource);
		q.getHeader().setID("Q1");
		final JDFResourceQuParams rqp = (JDFResourceQuParams) q.appendElement(ElementName.RESOURCEQUPARAMS);
		rqp.setResourceName(ElementName.MEDIA);
		rqp.setAttribute(AttributeName.SCOPE, "Allowed");
		rqp.setResourceDetails(EnumResourceDetails.Full);
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/paperResourceQuery.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testCommandPaper()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper q = xjmfHelper.appendMessage(EnumFamily.Command, EnumType.Resource);
		q.getHeader().setID("C1");
		final JDFResourceCmdParams rcp = (JDFResourceCmdParams) q.appendElement(ElementName.RESOURCECMDPARAMS);
		rcp.setUpdateMethod(EnumUpdateMethod.Incremental);
		final SetHelper sh = new SetHelper(rcp.appendElement(XJDFConstants.ResourceSet));
		sh.setName(ElementName.MEDIA);
		for (int i = 1; i < 3; i++)
		{
			final ResourceHelper rh = sh.appendPartition(null, true);
			rh.setExternalID("ID_" + i);
			rh.setAttribute(AttributeName.DESCRIPTIVENAME, "Paper # " + i);
			((JDFMedia) rh.getResource()).setDimensionCM(new JDFXYPair(21, 29));
			((JDFMedia) rh.getResource()).setWeight(60 + 20 * i);
			((JDFMedia) rh.getResource()).setMediaType(EnumMediaType.Paper);

		}

		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		sh.getRoot().appendXMLComment(" One Resource element for each paper to upload follows here ", null);
		writeTest(xjmfHelper, "jmf/paperResourceCommand.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testSignalPaper()
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID("DeviceID");
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper q = xjmfHelper.appendMessage(EnumFamily.Signal, EnumType.Resource);
		q.getHeader().setID("S1");
		q.getHeader().setAttribute(AttributeName.REFID, "Sub1");
		final JDFResourceInfo ri = (JDFResourceInfo) q.appendElement(ElementName.RESOURCEINFO);
		ri.setAttribute(AttributeName.SCOPE, "Job");
		ri.setAttribute(AttributeName.JOBID, "Job1");
		ri.setAttribute(AttributeName.JOBPARTID, "Printing");
		final SetHelper sh = new SetHelper(ri.appendElement(XJDFConstants.ResourceSet));
		sh.setUsage(EnumUsage.Input);
		sh.setName(ElementName.MEDIA);
		final ResourceHelper rh = sh.appendPartition(new JDFAttributeMap(AttributeName.SHEETNAME, "S1"), false);
		rh.setExternalID("MIS-ID");
		rh.setAmount(4500, new JDFAttributeMap(AttributeName.LOTID, "Lot1"), true);
		rh.setAmount(66, new JDFAttributeMap(AttributeName.LOTID, "Lot1"), false);
		rh.setAmount(2200, new JDFAttributeMap(AttributeName.LOTID, "Lot2"), true);
		rh.setAmount(22, new JDFAttributeMap(AttributeName.LOTID, "Lot2"), false);
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/paperResourceSignal.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testResponsePrintCondition()
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID("DeviceID");
		final XJMFHelper xjmfHelper = new XJMFHelper().newXJMF(EnumVersion.Version_2_1);
		;
		final MessageHelper q = xjmfHelper.appendMessage(EnumFamily.Response, EnumType.Resource);
		q.getHeader().setID("S1");
		q.getHeader().setAttribute(AttributeName.REFID, "Sub1");
		final JDFResourceInfo ri = (JDFResourceInfo) q.appendElement(ElementName.RESOURCEINFO);
		ri.setAttribute(AttributeName.SCOPE, "Allowed");
		final SetHelper sh = new SetHelper(ri.appendElement(XJDFConstants.ResourceSet));
		sh.setUsage(EnumUsage.Input);
		sh.setName(ElementName.PRINTCONDITION);
		final String[] colors = new String[] { "Cyan Magenta Yellow Black", "Cyan Magenta Yellow Black", "Cyan Magenta Yellow Black Orange Green Violet",
				"Cyan Magenta Yellow Black Orange Green Violet Varnish" };
		final String[] names = new String[] { "draft", "standard", "7color", "7colorVarnish" };
		int n = -1;
		for (final String name : names)
		{
			n++;
			for (final String paper : new String[] { "Coated", "Uncoated" })
			{
				final String pcPart = name + paper;
				final ResourceHelper rh = sh.appendPartition(ElementName.PRINTCONDITION, pcPart, true);
				rh.setDescriptiveName(name + " print condition for " + paper);
				final KElement pc = rh.getResource();
				pc.setAttribute(AttributeName.NAME, name);
				pc.setAttribute(ElementName.COLORANTORDER, colors[n]);
				final EnumPrintQuality pq = n < 1 ? EnumPrintQuality.Draft : n > 2 ? EnumPrintQuality.High : EnumPrintQuality.Normal;
				pc.setAttribute(AttributeName.PRINTQUALITY, pq.getName());
			}
		}
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/PrintConditionResponse.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testSignalStatus()
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID("DeviceID");
		XJMFHelper xjmfHelper = new XJMFHelper();
		xjmfHelper.getHeader().setAttribute(AttributeName.TIME, new JDFDate().setTime(16, 59, 0).getDateTimeISO());
		MessageHelper s = xjmfHelper.appendMessage(EnumFamily.Signal, EnumType.Status);
		s.getHeader().setID("S1");
		s.getHeader().setAttribute(AttributeName.REFID, "Sub1");
		s.getHeader().setAttribute(AttributeName.TIME, new JDFDate().setTime(16, 59, 0).getDateTimeISO());
		JDFDeviceInfo di = (JDFDeviceInfo) s.getRoot().appendElement(ElementName.DEVICEINFO);
		di.setAttribute(AttributeName.STATUS, "Production");
		JDFJobPhase p = di.appendJobPhase();
		p.setJobID("j1");
		p.setJobPartID("p1");
		p.setStatus(EnumNodeStatus.Setup);
		p.setStartTime(new JDFDate().setTime(16, 0, 0));
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/statusSignalSetup.xjmf");

		xjmfHelper = new XJMFHelper();
		xjmfHelper.getHeader().setAttribute(AttributeName.TIME, new JDFDate().setTime(17, 00, 0).getDateTimeISO());
		s = xjmfHelper.appendMessage(EnumFamily.Signal, EnumType.Status);
		s.getHeader().setID("S2");
		s.getHeader().setAttribute(AttributeName.REFID, "Sub1");
		s.getHeader().setAttribute(AttributeName.TIME, new JDFDate().setTime(17, 0, 0).getDateTimeISO());
		di = (JDFDeviceInfo) s.getRoot().appendElement(ElementName.DEVICEINFO);
		di.setAttribute(AttributeName.STATUS, "Production");
		p = di.appendJobPhase();
		p.setJobID("j1");
		p.setJobPartID("p1");
		p.setStatus(EnumNodeStatus.InProgress);
		p.setStartTime(new JDFDate().setTime(17, 0, 0));
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/statusSignal.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testResponseStatus()
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID("DeviceID");
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper s = xjmfHelper.appendMessage(EnumFamily.Response, EnumType.Status);
		s.getHeader().setID("S1");
		s.getHeader().setAttribute(AttributeName.REFID, "Q1");
		s.getHeader().setAttribute(AttributeName.TIME, new JDFDate().setTime(17, 0, 0).getDateTimeISO());
		final JDFDeviceInfo di = (JDFDeviceInfo) s.getRoot().appendElement(ElementName.DEVICEINFO);
		di.setAttribute(AttributeName.STATUS, "Production");
		final JDFJobPhase p = di.appendJobPhase();
		p.setJobID("j1");
		p.setJobPartID("p1");
		p.setQueueEntryID("Q1");
		p.setStartTime(new JDFDate().setTime(16, 0, 0));
		p.setStatus(EnumNodeStatus.InProgress);
		p.setAttribute(AttributeName.MODULEID + "s", "P0 P1 P2 P3 P5 P6 P7 Perf");
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/statusSignal.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testSubscribeStatus()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper s = xjmfHelper.appendMessage(EnumFamily.Query, EnumType.Status);
		s.getHeader().setID("Status1");
		s.getHeader().setAttribute(AttributeName.TIME, new JDFDate().setTime(17, 0, 0).getDateTimeISO());
		s.appendElement(ElementName.STATUSQUPARAMS);
		final JDFSubscription sub = s.subscribe("http://MIS:1234/xjmfurl");
		sub.setRepeatTime(30);
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "building/subscribeStatus.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testRespondSubscribeStatus()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper s = xjmfHelper.appendMessage(EnumFamily.Response, EnumType.Status);
		s.getHeader().setAttribute(AttributeName.REFID, "Status1");
		s.getHeader().setAttribute(AttributeName.TIME, new JDFDate().setTime(17, 0, 0).getDateTimeISO());
		s.setAttribute(AttributeName.RETURNCODE, "0");
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "building/subscribeStatusResponse.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testSignalStatusBuilding()
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID("DeviceID");
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper s = xjmfHelper.appendMessage(EnumFamily.Signal, EnumType.Status);
		s.getHeader().setID("S1");
		s.getHeader().setAttribute(AttributeName.REFID, "Status1");
		s.getHeader().setAttribute(AttributeName.TIME, new JDFDate().setTime(17, 0, 0).getDateTimeISO());
		final JDFDeviceInfo di = (JDFDeviceInfo) s.getRoot().appendElement(ElementName.DEVICEINFO);
		di.setAttribute(AttributeName.STATUS, "Production");
		final JDFJobPhase p = di.appendJobPhase();
		p.setJobID("j1");
		p.setJobPartID("p1");
		p.setStatus(EnumNodeStatus.InProgress);
		p.setStartTime(new JDFDate().setTime(17, 0, 0));
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "building/subscribeStatusSignal.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testResponsePaper()
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID("DeviceID");
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper q = xjmfHelper.appendMessage(EnumFamily.Response, EnumType.Resource);
		q.getHeader().setAttribute(AttributeName.REFID, "Q1");
		q.getHeader().setID("R1");
		final JDFResourceInfo ri = (JDFResourceInfo) q.appendElement(ElementName.RESOURCEINFO);
		ri.setScope(EnumScope.Allowed);
		final SetHelper sh = new SetHelper(ri.appendElement(XJDFConstants.ResourceSet));
		sh.setName(ElementName.MEDIA);
		for (int i = 1; i < 3; i++)
		{
			final ResourceHelper rh = sh.appendPartition(null, true);
			rh.setExternalID("ID_" + i);
			rh.setAttribute(AttributeName.DESCRIPTIVENAME, "Paper # " + i);
			((JDFMedia) rh.getResource()).setDimensionCM(new JDFXYPair(21, 29));
			((JDFMedia) rh.getResource()).setWeight(60 + 20 * i);
			((JDFMedia) rh.getResource()).setMediaType(EnumMediaType.Paper);
		}
		xjmfHelper.cleanUp();
		sh.getRoot().appendXMLComment(" One Resource element for each paper follows here ", null);
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/paperResourceResponse.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testResponsePaperPrintCondition()
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID("DeviceID");
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper q = xjmfHelper.appendMessage(EnumFamily.Response, EnumType.Resource);
		q.getHeader().setAttribute(AttributeName.REFID, "Q1");
		q.getHeader().setID("R1");
		final JDFResourceInfo ri = (JDFResourceInfo) q.appendElement(ElementName.RESOURCEINFO);
		ri.setScope(EnumScope.Allowed);
		final SetHelper sh = new SetHelper(ri.appendElement(XJDFConstants.ResourceSet));
		sh.setName(ElementName.MEDIA);

		ResourceHelper rh = sh.appendPartition(new JDFAttributeMap(AttributeName.PRINTCONDITION, "7-color-gloss"), true);
		rh.appendPartMap(new JDFAttributeMap(AttributeName.PRINTCONDITION, "4-color-gloss"));
		rh.setExternalID("ID1");
		rh.setAttribute(AttributeName.DESCRIPTIVENAME, "Good Gloss Paper");
		((JDFMedia) rh.getResource()).setDimensionCM(new JDFXYPair(21, 29));
		((JDFMedia) rh.getResource()).setWeight(100);
		((JDFMedia) rh.getResource()).setAttribute(XJDFConstants.Coating, "Gloss");
		((JDFMedia) rh.getResource()).setMediaType(EnumMediaType.Paper);

		rh = sh.appendPartition(new JDFAttributeMap(ElementName.PRINTCONDITION, "4-color-gloss"), true);
		rh.setExternalID("ID2");
		rh.setAttribute(AttributeName.DESCRIPTIVENAME, "Cheap Gloss Paper not for 7 color");
		((JDFMedia) rh.getResource()).setDimensionCM(new JDFXYPair(21, 29));
		((JDFMedia) rh.getResource()).setWeight(100);
		((JDFMedia) rh.getResource()).setAttribute(XJDFConstants.Coating, "Gloss");
		((JDFMedia) rh.getResource()).setMediaType(EnumMediaType.Paper);

		rh = sh.appendPartition(new JDFAttributeMap(AttributeName.PRINTCONDITION, "7-color-matte"), true);
		rh.appendPartMap(new JDFAttributeMap(ElementName.PRINTCONDITION, "4-color-matte"));
		rh.setExternalID("ID3");
		rh.setAttribute(AttributeName.DESCRIPTIVENAME, "Good Matte Paper");
		((JDFMedia) rh.getResource()).setDimensionCM(new JDFXYPair(21, 29));
		((JDFMedia) rh.getResource()).setWeight(100);
		((JDFMedia) rh.getResource()).setAttribute(XJDFConstants.Coating, "Matte");
		((JDFMedia) rh.getResource()).setMediaType(EnumMediaType.Paper);

		rh = sh.appendPartition(new JDFAttributeMap(ElementName.PRINTCONDITION, "4-color-matte"), true);
		rh.setExternalID("ID2");
		rh.setAttribute(AttributeName.DESCRIPTIVENAME, "Cheap Matte Paper not for 7 color");
		((JDFMedia) rh.getResource()).setDimensionCM(new JDFXYPair(21, 29));
		((JDFMedia) rh.getResource()).setWeight(100);
		((JDFMedia) rh.getResource()).setAttribute(XJDFConstants.Coating, "Matte");
		((JDFMedia) rh.getResource()).setMediaType(EnumMediaType.Paper);

		xjmfHelper.cleanUp();
		sh.getRoot().appendXMLComment(" One Resource element for each paper follows here ", null);
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/paperResourceResponse_PC.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testResponseKnownDevices()
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID("VeggieController");
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper response = xjmfHelper.appendMessage(EnumFamily.Response, EnumType.KnownDevices);
		response.getHeader().setAttribute(AttributeName.REFID, "Q1");
		response.getHeader().setAttribute(AttributeName.ID, "R1");
		response.setAttribute(AttributeName.RETURNCODE, "0");
		JDFDevice dev = (JDFDevice) response.getRoot().appendElement(ElementName.DEVICE);
		dev.setDeviceID("dev1");
		dev.setDeviceType("ACME Linda potato press V16-12");
		dev.setAttribute(XJDFConstants.XJMFURL, "http://acmepotato1:1234/xjmfurl");

		dev = (JDFDevice) response.getRoot().appendElement(ElementName.DEVICE);
		dev.setDeviceID("dev2");
		dev.setDeviceType("ACME Baldrick turnip press V42-66");
		dev.setAttribute(XJDFConstants.XJMFURL, "http://acmeturnip1:1234/xjmfurl");
		xjmfHelper.cleanUp();
		response.getRoot().appendXMLComment(" One Device element for each known device follows here ", null);

		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/responseKnowDevices.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testResponseKnownMessages()
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID("DeviceID");
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper response = xjmfHelper.appendMessage(EnumFamily.Response, EnumType.KnownMessages);
		response.getHeader().setAttribute(AttributeName.REFID, "Q1");
		response.getHeader().setAttribute(AttributeName.ID, "R1");
		response.setAttribute(AttributeName.RETURNCODE, "0");
		JDFMessageService ms = (JDFMessageService) response.appendElement(ElementName.MESSAGESERVICE);
		ms.setType("QueryKnownMessages");
		ms.setAttribute(XJDFConstants.ResponseModes, ElementName.RESPONSE);
		ms = (JDFMessageService) response.appendElement(ElementName.MESSAGESERVICE);
		ms.setType("QueryStatus");
		ms.setAttribute(XJDFConstants.ResponseModes, EnumChannelMode.FireAndForget.getName());
		ms.appendAttribute(XJDFConstants.ResponseModes, EnumChannelMode.Reliable.getName(), null, null, false);
		ms = (JDFMessageService) response.appendElement(ElementName.MESSAGESERVICE);
		ms.setType("CommandSubmitQueueEntry");
		ms = (JDFMessageService) response.appendElement(ElementName.MESSAGESERVICE);
		ms.setType("ResponseReturnQueueEntry");

		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/ResponseKnownMessages.xjmf");
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setAgentName(null);
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setAgentVersion(null);
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID(null);
		KElement.setLongID(false);
		JDFDate.setWantISOMilliseconds(true);

	}

	/**
	 *
	 */
	@Test
	public void testExtendQuery()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final KElement e = xjmfHelper.getRoot().appendElement("foo:QueryBar", "www.foo.org");
		e.appendElement("foo:BarParams").setAttribute("BarDetails", "value");
		setSnippet(xjmfHelper, true);
		new MessageHelper(e).getHeader().setID("queryID");
		writeTest(xjmfHelper, "jmf/extendQuery.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testExtendResponse()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final KElement e = xjmfHelper.getRoot().appendElement("foo:ResponseBar", "www.foo.org");
		e.appendElement("foo:BarResonseParams").setAttribute("BarDetails", "value");
		new MessageHelper(e).getHeader().setAttribute(AttributeName.REFID, "queryID");
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/extendResponse.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testExtendQueryMixed()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper q = xjmfHelper.appendMessage(EnumFamily.Query, EnumType.KnownDevices);
		q.getHeader().setID("Q1");
		final KElement e = xjmfHelper.getRoot().appendElement("foo:QueryBar", "www.foo.org");
		e.copyElement(q.getHeader(), null).setID("F1");
		e.appendElement("foo:BarParams").setAttribute("BarDetails", "value");
		final MessageHelper q2 = xjmfHelper.appendMessage(EnumFamily.Query, EnumType.KnownMessages);
		q2.getHeader().setID("Q2");
		setSnippet(xjmfHelper, true);
		writeTest(xjmfHelper, "jmf/extendQueryMixed.xjmf");
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	public void tearDown() throws Exception
	{
		JDFDate.setWantISOMilliseconds(false);
		super.tearDown();
	}

}
