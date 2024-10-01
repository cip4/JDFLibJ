/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.xjdfwalker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoMessageService.EnumChannelMode;
import org.cip4.jdflib.auto.JDFAutoMessageService.EnumJMFRole;
import org.cip4.jdflib.auto.JDFAutoStatusQuParams.EnumDeviceDetails;
import org.cip4.jdflib.auto.JDFAutoStatusQuParams.EnumJobDetails;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.MessageHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJMFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.JDFToXJDF;
import org.cip4.jdflib.jmf.JDFAbortQueueEntryParams;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFHoldQueueEntryParams;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFKnownMsgQuParams;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFMessageService;
import org.cip4.jdflib.jmf.JDFPipeParams;
import org.cip4.jdflib.jmf.JDFQuery;
import org.cip4.jdflib.jmf.JDFRemoveQueueEntryParams;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFResourceQuParams;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.jmf.JDFResumeQueueEntryParams;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.jmf.JDFSubscriptionInfo;
import org.cip4.jdflib.jmf.JMFBuilder;
import org.cip4.jdflib.jmf.JMFBuilderFactory;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFEvent;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.devicecapability.JDFIntegerState;
import org.cip4.jdflib.resource.process.JDFDieLayoutProductionParams;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFPerson;
import org.cip4.jdflib.resource.process.JDFRepeatDesc;
import org.cip4.jdflib.util.JDFDate;
import org.junit.jupiter.api.Test;

/**
 * @author rainer prosi
 *
 */
class JMFToXJMFConverterTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	void testForeignJMF()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Query, JDFMessage.EnumType.PipeClose);
		final JDFQuery query = jmf.getQuery(0);
		query.setType("foo:bar");
		query.appendSubscription().setURL("http://foo/bar");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertNull(xjmf);
	}

	/**
	 *
	 */
	@Test
	void testPipeJMF()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.PipeClose);
		final JDFPipeParams pp = jmf.getCommand(0).getCreatePipeParams(0);
		pp.setJobID("j1");
		pp.setJobPartID("p2");
		pp.setPipeID("pid");
		final JDFToXJDF conv = new JDFToXJDF();
		KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals(xjmf.getXPathAttribute("CommandPipeControl/PipeParams/@Operation", null), "Close");
		final JDFJMF jmfResp = JDFJMF.createJMF(EnumFamily.Response, JDFMessage.EnumType.PipeClose);
		xjmf = conv.makeNewJMF(jmfResp);
		assertEquals(xjmf.getElement("ResponsePipeControl").getLocalName(), "ResponsePipeControl");
		writeRoundTrip(jmf, "pipecontrol.jmf");
	}

	/**
	 *
	 */
	@Test
	void testAbortQE()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.AbortQueueEntry);
		final JDFCommand command = jmf.getCommand(0);
		command.setID("C1");
		final JDFAbortQueueEntryParams pp = (JDFAbortQueueEntryParams) command.appendElement(ElementName.ABORTQUEUEENTRYPARAMS);
		pp.appendQueueFilter().appendQueueEntryDef("q1e");
		pp.setEndStatus(EnumNodeStatus.Aborted);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final KElement mqp = xjmf.getElement("CommandModifyQueueEntry").getElement(XJDFConstants.ModifyQueueEntryParams);
		assertEquals(mqp.getAttribute(AttributeName.OPERATION), "Abort");

		writeRoundTrip(jmf, "abortqe.jmf");
	}

	/**
	 *
	 */
	@Test
	void testAbortQEResp()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Response, JDFMessage.EnumType.AbortQueueEntry);
		final JDFResponse resp = jmf.getResponse(0);
		resp.setID("R1");
		resp.setrefID("C1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);

		writeRoundTrip(jmf, "abortqeresp.jmf");
	}

	/**
	 *
	 */
	@Test
	void testCompleteQE()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.AbortQueueEntry);
		final JDFAbortQueueEntryParams pp = (JDFAbortQueueEntryParams) jmf.getCommand(0).appendElement(ElementName.ABORTQUEUEENTRYPARAMS);
		pp.appendQueueFilter().appendQueueEntryDef("q1e");
		pp.setEndStatus(EnumNodeStatus.Completed);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final KElement mqp = xjmf.getElement("CommandModifyQueueEntry").getElement(XJDFConstants.ModifyQueueEntryParams);
		assertEquals(mqp.getAttribute(AttributeName.OPERATION), "Complete");

		writeRoundTrip(jmf, "completeqe.jmf");
	}

	/**
	 *
	 */
	@Test
	void testSetGangQE()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Response, null);
		final JDFResponse r = jmf.getResponse(0);
		r.setType("SetGangQueueEntry");
		r.setReturnCode(42);
		final JDFToXJDF conv = new JDFToXJDF();
		final XJMFHelper xjmf = conv.convertToXJMF(jmf);
		final MessageHelper messageHelper = xjmf.getMessageHelper(0);
		assertNotNull(messageHelper);
		assertEquals(42, messageHelper.getReturnCode());
	}

	/**
	 *
	 */
	@Test
	void testHoldQE()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.HoldQueueEntry);
		final JDFHoldQueueEntryParams pp = (JDFHoldQueueEntryParams) jmf.getCommand(0).appendElement(ElementName.HOLDQUEUEENTRYPARAMS);
		pp.appendQueueFilter().appendQueueEntryDef("q1e");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final KElement mqp = xjmf.getElement("CommandModifyQueueEntry").getElement(XJDFConstants.ModifyQueueEntryParams);
		assertEquals(mqp.getAttribute(AttributeName.OPERATION), "Hold");

		writeRoundTrip(jmf, "holdqe.jmf");
	}

	/**
	 *
	 */
	@Test
	void testHoldQueue()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.HoldQueue);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertNull(xjmf);
	}

	/**
	 *
	 */
	@Test
	void testOpenQueue()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.OpenQueue);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertNull(xjmf);
	}

	/**
	 *
	 */
	@Test
	void testCloseQueue()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.CloseQueue);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertNull(xjmf);
	}

	/**
	 *
	 */
	@Test
	void testDeviceInfo1()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		jmf.getSignal(0).appendDeviceInfo().setDeviceID("d1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final XJMFHelper xh = new XJMFHelper(xjmf);
		assertEquals("d1", xh.getMessageHelper(0).getHeader().getAttribute(AttributeName.DEVICEID));
	}

	/**
	 *
	 */
	@Test
	void testDeviceInfo2()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		jmf.getSignal(0).appendDeviceInfo().appendDevice().setDeviceID("d1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final XJMFHelper xh = new XJMFHelper(xjmf);
		assertEquals("d1", xh.getMessageHelper(0).getHeader().getAttribute(AttributeName.DEVICEID));
	}

	/**
	 *
	 */
	@Test
	void testDeviceInfoDescName()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Response, JDFMessage.EnumType.KnownDevices);
		final JDFDevice dev = jmf.getResponse(0).appendDeviceList().appendDeviceInfo().appendDevice();
		dev.setDeviceID("d1");
		dev.setDescriptiveName("d");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final XJMFHelper xh = new XJMFHelper(xjmf);
		assertEquals("d", xh.getMessageHelper(0).getXPathValue("Device/@DescriptiveName"));
	}

	/**
	 *
	 */
	@Test
	void testDeviceInfoEndTime()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		final JDFDeviceInfo di = jmf.getSignal(0).appendDeviceInfo();
		di.appendDevice().setDeviceID("d1");
		final JDFDate now = new JDFDate();
		di.appendJobPhase().setEndTime(now);
		di.setEndTime(now);
		di.appendActivity().setEndTime(now);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final XJMFHelper xh = new XJMFHelper(xjmf);
		assertEquals("d1", xh.getMessageHelper(0).getHeader().getAttribute(AttributeName.DEVICEID));
		final KElement xdi = xh.getMessageHelper(0).getRoot().getElement(ElementName.DEVICEINFO);
		assertEquals(now.getDateTimeISO(), xdi.getAttribute(AttributeName.ENDTIME));
	}

	/**
	 *
	 */
	@Test
	void testJobPhaseSpeed()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		final JDFJobPhase jp = jmf.getSignal(0).appendDeviceInfo().appendJobPhase();
		jp.setSpeed(42);
		jp.setJobID("j1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertNull(xjmf.getXPathAttribute("SignalStatus/DeviceInfo/JobPhase/@Speed", null));
	}

	/**
	 *
	 */
	@Test
	void testComplexStatus()
	{
		final JDFJMF jmf = JDFJMF.parseFile(sm_dirTestData + "jmf/JMF1.jmf");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		xjmf.write2File(sm_dirTestDataTemp + "JMF1.xjmf");
		assertEquals("0", xjmf.getXPathAttribute("SignalStatus/DeviceInfo/ModuleInfo/@ModuleID", null));
	}

	/**
	 *
	 */
	@Test
	void testJobPhaseWaste()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		final JDFJobPhase jp = jmf.getSignal(0).appendDeviceInfo().appendJobPhase();
		jp.setWaste(42);
		jp.setJobID("j1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals("42", xjmf.getXPathAttribute("SignalStatus/DeviceInfo/JobPhase/@Waste", null));
	}

	/**
	 *
	 */
	@Test
	void testJobPhaseAmount()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		final JDFDeviceInfo di = jmf.getSignal(0).appendDeviceInfo();
		di.setDeviceStatus(EnumDeviceStatus.Running);
		final JDFJobPhase jp = di.appendJobPhase();
		jp.setAmount(42);
		jp.setJobID("j1");
		jp.setStatus(EnumNodeStatus.InProgress);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals("42", xjmf.getXPathAttribute("SignalStatus/DeviceInfo/JobPhase/@Amount", null));
		writeRoundTrip(jmf, "jophase.jmf");
	}

	/**
	 *
	 */
	@Test
	void testJobPhaseTotal()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		final JDFJobPhase jp = jmf.getSignal(0).appendDeviceInfo().appendJobPhase();
		jp.setTotalAmount(42);
		jp.setJobID("j1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertNull(xjmf.getXPathAttribute("SignalStatus/DeviceInfo/JobPhase/@TotalAmount", null));
	}

	/**
	 *
	 */
	@Test
	void testJobPhaseWorkstepID()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		final JDFJobPhase jp = jmf.getSignal(0).appendDeviceInfo().appendJobPhase();
		jp.setTotalAmount(42);
		jp.setJobID("j1");
		jp.setWorkStepID("wsid");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals("wsid", xjmf.getXPathAttribute("SignalStatus/DeviceInfo/JobPhase/@WorkStepID", null));
	}

	/**
	 *
	 */
	@Test
	void testResumeQE()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.ResumeQueueEntry);
		final JDFResumeQueueEntryParams pp = (JDFResumeQueueEntryParams) jmf.getCommand(0).appendElement(ElementName.RESUMEQUEUEENTRYPARAMS);
		pp.appendQueueFilter().appendQueueEntryDef("q1e");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final KElement mqp = xjmf.getElement("CommandModifyQueueEntry").getElement(XJDFConstants.ModifyQueueEntryParams);
		assertEquals(mqp.getAttribute(AttributeName.OPERATION), "Resume");

		writeRoundTrip(jmf, "resumeqe.jmf");
	}

	/**
	 *
	 */
	@Test
	void testRemovedQE()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.RemoveQueueEntry);
		final JDFRemoveQueueEntryParams pp = (JDFRemoveQueueEntryParams) jmf.getCommand(0).appendElement(ElementName.REMOVEQUEUEENTRYPARAMS);
		pp.appendQueueFilter().appendQueueEntryDef("q1e");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final KElement mqp = xjmf.getElement("CommandModifyQueueEntry").getElement(XJDFConstants.ModifyQueueEntryParams);
		assertEquals(mqp.getAttribute(AttributeName.OPERATION), "Remove");

		writeRoundTrip(jmf, "removeqe.jmf");
	}

	/**
	 *
	 */
	@Test
	void testMessageServiceMode()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Response, JDFMessage.EnumType.KnownMessages);
		final JDFMessageService ms = jmf.getResponse(0).appendMessageService();
		ms.setChannelMode(EnumChannelMode.FireAndForget);
		ms.setType(EnumType.KnownMessages);
		ms.setJMFRole(EnumJMFRole.Receiver);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals(xjmf.getXPathAttribute("ResponseKnownMessages/MessageService/@ResponseModes", null), "FireAndForget");
		writeRoundTrip(jmf, "MessageService");
	}

	/**
	 *
	 */
	@Test
	void testMessageServiceType()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Response, JDFMessage.EnumType.KnownMessages);
		final JDFMessageService ms = jmf.getResponse(0).appendMessageService();
		ms.setChannelMode(EnumChannelMode.FireAndForget);
		ms.setType(EnumType.KnownMessages);
		ms.setQuery(true);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals("QueryKnownMessages", xjmf.getXPathAttribute("ResponseKnownMessages/MessageService/@Type", null));
		writeRoundTrip(jmf, "MessageService2");
	}

	/**
	 *
	 */
	@Test
	void testMessageServiceState()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Response, JDFMessage.EnumType.KnownMessages);
		final JDFMessageService ms = jmf.getResponse(0).appendMessageService();
		ms.setChannelMode(EnumChannelMode.FireAndForget);
		ms.setType(EnumType.KnownMessages);
		final JDFIntegerState is = (JDFIntegerState) ms.appendElement(ElementName.INTEGERSTATE);
		is.setName("n");
		assertTrue(is.isValid(EnumValidationLevel.Complete));
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertNull(xjmf.getXPathAttribute("ResponseKnownMessages/MessageService/IntegerState", null));
		writeRoundTrip(jmf, "MessageServiceState");
	}

	/**
	 *
	 */
	@Test
	void testKnownMessages()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Query, JDFMessage.EnumType.KnownMessages);
		final JDFKnownMsgQuParams ms = jmf.getQuery(0).appendKnownMsgQuParams();
		ms.setExact(false);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertNotNull(xjmf.getXPathElement("QueryKnownMessages"));
		assertNull(xjmf.getXPathElement("QueryKnownMessages/KnownMessageQuParams"));

		writeRoundTrip(jmf, "KnownMessages");
	}

	/**
	 *
	 */
	@Test
	void testSubscriptionJMF()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildStatusSubscription("url", 42, 21, "qe33");
		jmf.getQuery(0).getSubscription().appendObservationTarget().setAttributes(new VString("a", null));
		jmf.getQuery(0).getStatusQuParams().setQueueInfo(true);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertNull(xjmf.getChildByTagName(ElementName.OBSERVATIONTARGET, null, 0, null, false, false));
		final KElement subscription = xjmf.getChildByTagName(ElementName.SUBSCRIPTION, null, 0, null, false, false);
		assertNotNull(subscription);
		assertFalse(subscription.hasAttribute(AttributeName.REPEATSTEP));
	}

	/**
	 *
	 */
	@Test
	void testSubscriptionFilter()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Query, JDFMessage.EnumType.KnownSubscriptions);
		jmf.getQuery(0).getCreateSubscriptionFilter().setJobID("J1");
		jmf.getQuery(0).getCreateSubscriptionFilter().setFamilies(new VString("Signal", null));
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertNull(xjmf.getChildByTagName(ElementName.SUBSCRIPTIONFILTER, null, 0, null, false, false));
	}

	/**
	 *
	 */
	@Test
	void testSubscriptionInfo()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Response, JDFMessage.EnumType.KnownSubscriptions);
		final JDFSubscriptionInfo si = jmf.getResponse(0).getCreateSubscriptionInfo(0);
		si.setMessageType(EnumType.Status);
		si.setChannelID("c1");
		si.appendSubscription().setURL("u1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals("SignalStatus", xjmf.getChildByTagName(ElementName.SUBSCRIPTIONINFO, null, 0, null, false, false).getAttribute(AttributeName.MESSAGETYPE));
		writeRoundTrip(jmf, "SubFilter");
	}

	/**
	 *
	 */
	@Test
	void testSubscriptionFilterKeep()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Query, JDFMessage.EnumType.KnownSubscriptions);
		jmf.getQuery(0).getCreateSubscriptionFilter().setJobID("J1");
		jmf.getQuery(0).getCreateSubscriptionFilter().setURL("aaa");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertNotNull(xjmf.getChildByTagName(ElementName.SUBSCRIPTIONFILTER, null, 0, null, false, false));
	}

	/**
	 *
	 */
	@Test
	void testKnownDevicesResponse()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).createJMF(EnumFamily.Response, EnumType.KnownDevices);
		final JDFDeviceInfo deviceInfo = jmf.getResponse(0).appendDeviceList().appendDeviceInfo();
		deviceInfo.setDeviceID("d1");
		deviceInfo.setDeviceStatus(EnumDeviceStatus.Idle);
		final KElement x = convertToXJDF(jmf);
		assertEquals("d1", x.getXPathAttribute("ResponseKnownDevices/Device/@DeviceID", null));
		writeRoundTrip(jmf, "KnownDevResp.jmf");
	}

	/**
	 *
	 */
	@Test
	void testKnownDevicesDetailedResponse()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).createJMF(EnumFamily.Response, EnumType.KnownDevices);
		final JDFDeviceInfo deviceInfo = jmf.getResponse(0).appendDeviceList().appendDeviceInfo();
		deviceInfo.setDeviceID("d1");
		deviceInfo.setDeviceStatus(EnumDeviceStatus.Unknown);
		deviceInfo.getCreateDevice().setJMFURL("http://myjmf/devid");
		final KElement x = convertToXJDF(jmf);
		assertEquals("http://myjmf/devid", x.getXPathAttribute("ResponseKnownDevices/Device/@XJMFURL", null));
		writeRoundTrip(jmf, "KnownDevResp2.jmf");
	}

	/**
	 *
	 */
	@Test
	void testAbortQEResponse()
	{
		final JDFJMF jmf0 = JMFBuilderFactory.getJMFBuilder(null).createJMF(EnumFamily.Command, EnumType.AbortQueueEntry);
		convertToXJDF(jmf0);

		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).createJMF(EnumFamily.Response, EnumType.AbortQueueEntry);
		jmf.getResponse(0).setrefID(jmf0.getCommand(0).getID());
		// assertEquals("q1", x.getXPathAttribute("ResponseModifyQueueEntry/QueueEntry/@QueueEntryID", null));
		writeRoundTrip(jmf, "AbortResp.jmf");
	}

	/**
	 *
	 */
	@Test
	void testStatusJMFQueueSubscription()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildStatusSubscription("url", 42, 21, "qe33");
		jmf.getQuery(0).getSubscription().appendObservationTarget().setAttributes(new VString("a", null));
		jmf.getQuery(0).getStatusQuParams().setQueueInfo(true);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final KElement statusquparams = xjmf.getChildByTagName(ElementName.STATUSQUPARAMS, null, 0, null, false, false);
		assertFalse(statusquparams.hasAttribute(AttributeName.QUEUEINFO));
		// writeTest(jmf, "../StatusJMF.jmf", true);
	}

	void testStatusJMF()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildStatusSubscription("url", 42, 21, "qe33");
		jmf.getQuery(0).getSubscription().appendObservationTarget().setAttributes(new VString("a", null));
		jmf.getQuery(0).getStatusQuParams().setQueueInfo(true);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final KElement statusquparams = xjmf.getChildByTagName(ElementName.STATUSQUPARAMS, null, 0, null, false, false);
		assertFalse(statusquparams.hasAttribute(AttributeName.QUEUEINFO));
		// writeTest(jmf, "../StatusJMF.jmf", true);
	}

	/**
	*
	*/
	@Test
	void testEventID()
	{
		final JDFJMF jmf = new JMFBuilder().buildStatusSignal(EnumDeviceDetails.Full, EnumJobDetails.Brief);
		final JDFDeviceInfo di2 = jmf.getSignal(0).getCreateDeviceInfo(0);
		final JDFEvent ev = di2.appendEvent("e3");
		assertEquals("e3", ev.getEventID());
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final XJMFHelper h = new XJMFHelper(xjmf);
		final JDFDeviceInfo di = (JDFDeviceInfo) h.getMessageHelper(0).getRoot().getElement(ElementName.DEVICEINFO);
		final JDFEvent e = di.getEvent(0);
		assertEquals("e3", e.getEventID());
	}

	/**
	 *
	 */
	@Test
	void testBadType()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		jmf.getSignal(0).setType("BadType");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertNull(xjmf);
	}

	/**
	 *
	 */
	@Test
	void testBadTypeQES()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.QueueEntryStatus);
		jmf.getSignal(0).setType("BadType");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertNull(xjmf);
	}

	/**
	 *
	 */
	@Test
	void testMoveToSender()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceQuery(true);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final KElement sender = xjmf.getElement("QueryResource").getElement(XJDFConstants.Header);
		assertNotNull(sender);
		assertEquals(jmf.getQuery(0).getID(), sender.getID());
	}

	/**
	 *
	 */
	@Test
	void testMoveToSenderEmployee()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceQuery(true);
		final JDFQuery q = jmf.getQuery(0);
		q.appendEmployee().setPersonalID("P123");
		final JDFPerson person = q.getEmployee(0).appendPerson();
		person.setFirstName("Franz");
		person.setFamilyName("meier");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final KElement sender = xjmf.getXPathElement("QueryResource/Header");
		assertNotNull(sender);
		assertEquals(q.getEmployee(0).getPersonalID(), sender.getAttribute(AttributeName.PERSONALID));
		assertEquals(q.getEmployee(0).getDescriptiveName(), sender.getAttribute(AttributeName.AUTHOR));
	}

	/**
	 *
	 */
	@Test
	void testMoveToSenderTime()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceQuery(true);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final KElement sender = xjmf.getElement(XJDFConstants.Header);
		assertNotNull(sender);
		assertEquals(jmf.getTimeStamp().getDateTimeISO(), sender.getAttribute(AttributeName.TIME));
	}

	/**
	 *
	 */
	@Test
	void testMoveToSenderSenderID()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceQuery(true);
		jmf.setSenderID("s1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		final KElement sender = xjmf.getElement(XJDFConstants.Header);
		assertNotNull(sender);
		assertEquals(jmf.getSenderID(), sender.getAttribute(AttributeName.DEVICEID));
	}

	/**
	 *
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	public void tearDown() throws Exception
	{
		XJMFTypeMap.shutDown();
		super.tearDown();
	}

	/**
	 *
	 * test ink resource signal
	 */
	@Test
	void testBuildResourceQueryPaperLot()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceQuery(true);

		final JDFQuery q = jmf.getQuery(0);
		final JDFResourceQuParams rqp = q.getCreateResourceQuParams(0);
		rqp.setJobID("job1");
		rqp.setJobPartID("ConvPrint.1");
		rqp.setLotID("L1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals("L1", xjmf.getXPathAttribute("QueryResource/ResourceQuParams/Part/@LotID", null));
		assertNull(xjmf.getXPathAttribute("QueryResource/ResourceQuParams/@LotID", null));
		xjmf.write2File(sm_dirTestDataTemp + "resourceQPaper.xjmf");
	}

	/**
	 *
	 * test ink resource signal
	 */
	@Test
	void testBuildResourceSignalInkLot()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceSignal(false, null);

		final JDFSignal signal = jmf.getSignal(0);
		final JDFResourceQuParams rqp = signal.getCreateResourceQuParams(0);
		rqp.setJobID("job1");
		rqp.setJobPartID("ConvPrint.1");
		final JDFResourceInfo ri = signal.getCreateResourceInfo(0);
		ri.setResourceName(ElementName.INK);
		ri.setUnit("g");
		final JDFAmountPool ap = ri.getCreateAmountPool();
		final JDFAttributeMap map = new JDFAttributeMap();
		map.put(AttributeName.SIGNATURENAME, "sig1");
		map.put(AttributeName.SHEETNAME, "s1");
		map.put(AttributeName.SIDE, "Front");
		for (int i = 1; i < 3; i++)
		{
			for (final String sep : new VString("Cyan Magenta Yellow Black Grün", null))
			{
				map.put(AttributeName.SEPARATION, sep);
				map.put(AttributeName.LOTID, "Los_" + i + "_" + sep);
				ap.appendPartAmount(map).setActualAmount(125 + (i * 42 * sep.hashCode()) % 123);
			}
		}
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertNotNull(xjmf.getXPathAttribute("SignalResource/ResourceInfo/ResourceSet/Resource/AmountPool/PartAmount/Part/@LotID", null));
		assertEquals(xjmf.getXPathAttribute("SignalResource/ResourceInfo/ResourceSet/Resource/AmountPool/PartAmount/Part/@LotID", null),
				jmf.getXPathAttribute("Signal/ResourceInfo/AmountPool/PartAmount/Part/@LotID", null));
		assertEquals(10, xjmf.getXPathElementVector("SignalResource/ResourceInfo/ResourceSet/Resource/AmountPool/PartAmount", 0).size());

		xjmf.write2File(sm_dirTestDataTemp + "resourceInk.xjmf");
	}

	/**
	 *
	 * test ink resource signal
	 */
	@Test
	void testResourceSignalSpeed()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceSignal(false, null);

		final JDFSignal signal = jmf.getSignal(0);
		final JDFResourceQuParams rqp = signal.getCreateResourceQuParams(0);
		rqp.setJobID("job1");
		final JDFResourceInfo ri = signal.getCreateResourceInfo(0);
		ri.setResourceName(ElementName.MISCCONSUMABLE);
		ri.setUnit("kwh");
		ri.setTotalAmount(100);
		ri.setSpeed(42);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals("42", xjmf.getXPathAttribute("SignalResource/ResourceInfo/@Speed", null));
		assertEquals("100", xjmf.getXPathAttribute("SignalResource/ResourceInfo/@TotalAmount", null));

		xjmf.write2File(sm_dirTestDataTemp + "resourceSpeed.xjmf");
	}

	/**
	 *
	 * test ink resource signal
	 */
	@Test
	void testResourceSignalScope()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceSignal(false, null);

		final JDFSignal signal = jmf.getSignal(0);
		final JDFResourceQuParams rqp = signal.getCreateResourceQuParams(0);
		rqp.setJobID("job1");
		rqp.setAttribute(AttributeName.SCOPE, "Estimate");
		final JDFResourceInfo ri = signal.getCreateResourceInfo(0);
		ri.setResourceName(ElementName.MISCCONSUMABLE);
		ri.setActualAmount(100);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals("Estimate", xjmf.getXPathAttribute("SignalResource/ResourceInfo/@Scope", null));
		assertEquals("100", xjmf.getXPathAttribute("SignalResource/ResourceInfo/ResourceSet/Resource/AmountPool/PartAmount/@Amount", null));

		xjmf.write2File(sm_dirTestDataTemp + "resourceSpeed.xjmf");
	}

	/**
	 *
	 * test ink resource signal
	 */
	@Test
	void testResourceSignalGeneratedScopeEstimate()
	{
		for (int i = 0; i < 2; i++)
		{
			final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceSignal(false, null);

			final JDFSignal signal = jmf.getSignal(0);
			final JDFResourceQuParams rqp = signal.getCreateResourceQuParams(0);
			rqp.setJobID("job1");
			final JDFResourceInfo ri = signal.getCreateResourceInfo(0);
			ri.setResourceName(ElementName.MISCCONSUMABLE);
			if (i == 1)
				ri.appendElement(ElementName.MISCCONSUMABLE);
			ri.setAmount(100);
			final JDFToXJDF conv = new JDFToXJDF();
			final KElement xjmf = conv.makeNewJMF(jmf);
			assertEquals("Estimate", xjmf.getXPathAttribute("SignalResource/ResourceInfo/@Scope", null));
			assertEquals("100", xjmf.getXPathAttribute("SignalResource/ResourceInfo/ResourceSet/Resource/AmountPool/PartAmount/@Amount", null));

			xjmf.write2File(sm_dirTestDataTemp + "resourceSpeed.xjmf");
		}
	}

	/**
	 *
	 * test ink resource signal
	 */
	@Test
	void testResourceSignalGeneratedScopeJob()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceSignal(false, null);

		final JDFSignal signal = jmf.getSignal(0);
		final JDFResourceQuParams rqp = signal.getCreateResourceQuParams(0);
		rqp.setJobID("job1");
		final JDFResourceInfo ri = signal.getCreateResourceInfo(0);
		ri.setResourceName(ElementName.MISCCONSUMABLE);
		ri.setActualAmount(100);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals("Job", xjmf.getXPathAttribute("SignalResource/ResourceInfo/@Scope", null));
		assertEquals("100", xjmf.getXPathAttribute("SignalResource/ResourceInfo/ResourceSet/Resource/AmountPool/PartAmount/@Amount", null));

		xjmf.write2File(sm_dirTestDataTemp + "resourceSpeed.xjmf");
	}

	/**
	 *
	 * test ink resource signal
	 */
	@Test
	void testResourceSignalInk()
	{
		final JDFJMF jmf = JDFJMF.parseFile(sm_dirTestData + "ink.jmf");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		xjmf.write2File(sm_dirTestDataTemp + "ink.xjmf");
		assertEquals("Estimate", xjmf.getXPathAttribute("SignalResource/ResourceInfo[1]/@Scope", null));
		assertEquals("Job", xjmf.getXPathAttribute("SignalResource/ResourceInfo[2]/@Scope", null));
		assertEquals("118830", xjmf.getXPathAttribute("SignalResource/ResourceInfo[1]/@JobID", null));
		assertEquals("118830", xjmf.getXPathAttribute("SignalResource/ResourceInfo[2]/@JobID", null));
		final SetHelper sh = SetHelper.getHelper(xjmf.getXPathElement("SignalResource/ResourceInfo/ResourceSet"));
		assertEquals(4, sh.getPartitions().size());
		for (final ResourceHelper rh : sh.getPartitions())
		{
			assertTrue(rh.getAmount(null, true) > 0);
			assertTrue(rh.getAmount(null, false) > 0);
		}

	}

	/**
	 *
	 * test ink resource signal
	 */
	@Test
	void testResourceSignalKWH()
	{
		final JDFJMF jmf = JDFJMF.parseFile(sm_dirTestData + "kwh.jmf");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		xjmf.write2File(sm_dirTestDataTemp + "kwh.xjmf");
		assertEquals("Job", xjmf.getXPathAttribute("SignalResource/ResourceInfo/@Scope", null));
		final SetHelper sh = SetHelper.getHelper(xjmf.getXPathElement("SignalResource/ResourceInfo/ResourceSet"));
		final List<JDFResourceInfo> childArrayByClass = xjmf.getChildArrayByClass(JDFResourceInfo.class, true, 0);
		assertEquals(3, childArrayByClass.size());
		for (final ResourceHelper rh : sh.getPartitions())
		{
			assertTrue(rh.getAmount(null, true) > 0);
			assertFalse(rh.getAmount(null, false) > 0);
		}
		for (final JDFResourceInfo ri : childArrayByClass)
		{
			assertTrue(ri.hasNonEmpty(AttributeName.MODULEID));
		}
	}

	/**
	 *
	 * test ink resource signal
	 */
	@Test
	void testResourceSignalActualAmount()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceSignal(false, null);

		final JDFSignal signal = jmf.getSignal(0);
		final JDFResourceQuParams rqp = signal.getCreateResourceQuParams(0);
		rqp.setJobID("job1");
		final JDFResourceInfo ri = signal.getCreateResourceInfo(0);
		ri.setResourceName(ElementName.MISCCONSUMABLE);
		ri.setAmount(50);
		ri.setActualAmount(100);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals("50", xjmf.getXPathAttribute("SignalResource/ResourceInfo[1]/ResourceSet/Resource/AmountPool/PartAmount/@Amount", null));
		assertEquals("100", xjmf.getXPathAttribute("SignalResource/ResourceInfo[2]/ResourceSet/Resource/AmountPool/PartAmount/@Amount", null));
		assertEquals("Estimate", xjmf.getXPathAttribute("SignalResource/ResourceInfo[1]/@Scope", null));
		assertEquals("Job", xjmf.getXPathAttribute("SignalResource/ResourceInfo[2]/@Scope", null));

		xjmf.write2File(sm_dirTestDataTemp + "resourceSpeed.xjmf");
	}

	/**
	 *
	 * test ink resource signal
	 */
	@Test
	void testResourceSignalRepeatDesc()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceSignal(false, null);

		final JDFSignal signal = jmf.getSignal(0);
		final JDFResourceQuParams rqp = signal.getCreateResourceQuParams(0);
		rqp.setJobID("job1");
		final JDFResourceInfo ri = signal.getCreateResourceInfo(0);
		ri.setResourceName(ElementName.DIELAYOUTPRODUCTIONPARAMS);
		final JDFDieLayoutProductionParams dlp = (JDFDieLayoutProductionParams) ri.getCreateResource(null);
		final JDFRepeatDesc rd = dlp.appendRepeatDesc();
		rd.setGutterX(0);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		xjmf.write2File(sm_dirTestDataTemp + "repeatdesc.xjmf");
	}

	/**
	 *
	 * test ink resource signal
	 */
	@Test
	void testResourceSignalPart()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceSignal(false, null);

		final JDFSignal signal = jmf.getSignal(0);
		final JDFResourceQuParams rqp = signal.getCreateResourceQuParams(0);
		rqp.setJobID("job1");
		final JDFResourceInfo ri = signal.getCreateResourceInfo(0);
		ri.setProductID("P1");
		final JDFAttributeMap mPart = new JDFAttributeMap();
		mPart.put(AttributeName.SIGNATURENAME, "Sig1");
		mPart.put(AttributeName.SHEETNAME, "S1");
		mPart.put(AttributeName.SIDE, "Front");
		ri.setPartMap(mPart);
		ri.setActualAmount(100);
		final JDFResource mc = ri.appendResource(ElementName.MISCCONSUMABLE);
		mc.setAttribute(AttributeName.CONSUMABLETYPE, "Foo");
		mc.setDescriptiveName("name");
		mc.setProductID("P1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		xjmf.write2File(sm_dirTestDataTemp + "resourcePart.xjmf");
		assertEquals("100", xjmf.getXPathAttribute("SignalResource/ResourceInfo/ResourceSet/Resource/AmountPool/PartAmount/@Amount", null));
		final SetHelper sh = new SetHelper(xjmf.getXPathElement("SignalResource/ResourceInfo/ResourceSet"));
		assertEquals(1, sh.getPartitions().size());
		assertEquals("P1", sh.getPartition(0).getExternalID());
		assertNull(xjmf.getXPathElement("SignalResource/ResourceInfo/Part"));
	}

	/**
	 *
	 * test ink resource signal
	 */
	@Test
	void testBuildResourceSignalPlate()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceSignal(false, null);

		final JDFSignal signal = jmf.getSignal(0);
		final JDFResourceQuParams rqp = signal.getCreateResourceQuParams(0);
		rqp.setJobID("job1");
		rqp.setJobPartID("ConvPrint.1");
		final JDFResourceInfo ri = signal.getCreateResourceInfo(0);
		ri.setResourceName(ElementName.EXPOSEDMEDIA);
		final JDFAmountPool ap = ri.getCreateAmountPool();
		final JDFAttributeMap map = new JDFAttributeMap();
		final JDFExposedMedia xm = (JDFExposedMedia) ri.appendResource(null);
		map.put(AttributeName.SIGNATURENAME, "sig1");
		xm.getCreatePartition(map, null);
		map.put(AttributeName.SHEETNAME, "s1");
		xm.getCreatePartition(map, null);
		map.put(AttributeName.SIDE, "Front");
		xm.getCreatePartition(map, null);
		map.put(AttributeName.SEPARATION, "Black");
		ap.appendPartAmount(map).setActualAmount(1);
		xm.getCreatePartition(map, null).setDescriptiveName("keep dis");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		xjmf.write2File(sm_dirTestDataTemp + "resourcePlate.xjmf");
		assertEquals(1, xjmf.numChildrenByClass(JDFAmountPool.class, true));
		final KElement set = xjmf.getElement(XJDFConstants.SignalResource).getElement(ElementName.RESOURCEINFO).getElement(XJDFConstants.ResourceSet);
		final SetHelper sh = SetHelper.getHelper(set);
		assertEquals(1, sh.getPartitions().size());
		final ResourceHelper ph = sh.getPartition(0);
		map.remove(AttributeName.SIGNATURENAME);
		assertEquals(map, ph.getPartMap());
		final JDFAmountPool aPool = ph.getAmountPool();
		assertEquals(1, aPool.numChildElements(ElementName.PARTAMOUNT, null));
		assertTrue(JDFAttributeMap.isEmpty(aPool.getPartAmount(0).getPartMap()));
	}

	/**
	 *
	 * test ink resource signal
	 */
	@Test
	void testBuildResourceSignalPlateMedia()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceSignal(false, null);

		final JDFSignal signal = jmf.getSignal(0);
		final JDFResourceQuParams rqp = signal.getCreateResourceQuParams(0);
		rqp.setJobID("job1");
		rqp.setJobPartID("ConvPrint.1");
		final JDFResourceInfo ri = signal.getCreateResourceInfo(0);
		ri.setResourceName(ElementName.EXPOSEDMEDIA);
		final JDFAttributeMap map = new JDFAttributeMap();
		final JDFExposedMedia xm = (JDFExposedMedia) ri.appendResource(null);
		map.put(AttributeName.SIGNATURENAME, "sig1");
		xm.getCreatePartition(map, null);
		map.put(AttributeName.SHEETNAME, "s1");
		xm.getCreatePartition(map, null);
		final JDFExposedMedia xmp = (JDFExposedMedia) xm.getCreatePartition(map, null);
		xmp.setDescriptiveName("keep dis");
		xmp.appendMedia().setMediaType(EnumMediaType.Plate);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		xjmf.write2File(sm_dirTestDataTemp + "resourcePlate2.xjmf");
		final KElement xri = xjmf.getElement(XJDFConstants.SignalResource).getElement(ElementName.RESOURCEINFO);
		assertNull(xri.getElement(ElementName.MEDIA));
		final KElement xri1 = xjmf.getElement(XJDFConstants.SignalResource).getElement(ElementName.RESOURCEINFO, null, 1);
		assertEquals(xri.getXPathAttribute("ResourceSet/Resource/ExposedMedia/@MediaRef", "a"), xri1.getXPathAttribute("ResourceSet/Resource/@ID", "b"));
	}

	/**
	 *
	 */
	@Test
	void testSubmitQueueEntryHold()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildSubmitQueueEntry(null);
		jmf.getCommand(0).getQueueSubmissionParams(0).setHold(true);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);

		assertEquals("Held", xjmf.getXPathAttribute("CommandSubmitQueueEntry/QueueSubmissionParams/@Activation", null));
		assertNull(xjmf.getXPathAttribute("CommandSubmitQueueEntry/QueueSubmissionParams/@Hold", null));
	}

	/**
	 *
	 */
	@Test
	void testSubmitQueueEntryQueueFilter()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildSubmitQueueEntry(null);
		jmf.getCommand(0).getQueueSubmissionParams(0).setHold(true);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);

		assertEquals("Held", xjmf.getXPathAttribute("CommandSubmitQueueEntry/QueueSubmissionParams/@Activation", null));
		assertNull(xjmf.getXPathAttribute("CommandSubmitQueueEntry/QueueSubmissionParams/@Hold", null));
		assertNull(xjmf.getXPathElement("CommandSubmitQueueEntry/QueueFilter"));
	}

}
