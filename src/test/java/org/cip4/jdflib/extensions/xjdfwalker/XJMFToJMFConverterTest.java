/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceCondition;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoMessageService.EnumChannelMode;
import org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus;
import org.cip4.jdflib.auto.JDFAutoResourceCmdParams.EnumUpdateMethod;
import org.cip4.jdflib.auto.JDFAutoResourceQuParams.EnumScope;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.MessageHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJMFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.JDFToXJDF;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFDeviceInfo.eXjdfDeviceCondition;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFMessageService;
import org.cip4.jdflib.jmf.JDFQueueEntry;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFResourceQuParams;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.StringUtil;
import org.junit.jupiter.api.Test;

/**
 * @author rainer prosi
 *
 */
class XJMFToJMFConverterTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	void testDeviceInfoOffline()
	{
		final XJMFHelper h = new XJMFHelper();
		final MessageHelper mh = h.appendMessage(EnumFamily.Signal, EnumType.Status);
		final JDFDeviceInfo di = (JDFDeviceInfo) mh.appendElement(ElementName.DEVICEINFO);
		di.setXJDFDeviceCondition(eXjdfDeviceCondition.Offline);
		final XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		final JDFDoc d = xc.convert(h.getRoot());
		final JDFDeviceInfo dij = d.getJMFRoot().getSignal(0).getDeviceInfo(0);
		assertEquals(EnumDeviceCondition.OffLine.getName(), dij.getAttribute(AttributeName.DEVICECONDITION));
	}

	/**
	 *
	 */
	@Test
	void testResponseSubmitQueueEntry()
	{
		final XJMFHelper h = XJMFHelper.parseFile(sm_dirTestData + "xjmf/SQE_response.xjmf");
		final XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		final JDFDoc d = xc.convert(h.getRoot());
		final JDFJMF jmf = d.getJMFRoot();
		assertNotNull(jmf);
	}

	/**
	 *
	 */
	@Test
	void testResubmitQueueEntry()
	{
		final XJMFHelper h = new XJMFHelper();
		final MessageHelper mh = h.appendMessage(EnumFamily.Command, EnumType.ResubmitQueueEntry);
		mh.appendElement(ElementName.RESUBMISSIONPARAMS).setAttribute(AttributeName.UPDATEMETHOD, EnumUpdateMethod.Incremental.getName());
		final XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		final JDFDoc d = xc.convert(h.getRoot());
		assertNotNull(d);
		// assertTrue(d.getJMFRoot().isValid(EnumValidationLevel.Incomplete));
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
		final XJDFToJDFConverter xjdfConv = new XJDFToJDFConverter(null);
		final JDFJMF jmf1 = xjdfConv.convert(xjmf).getJMFRoot();
		assertEquals("KnownMessages", jmf1.getXPathAttribute("Response/MessageService/@Type", null));

	}

	/**
	 *
	 */
	@Test
	void testMessageServiceModifyType()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Response, JDFMessage.EnumType.KnownMessages);
		final JDFMessageService ms = jmf.getResponse(0).appendMessageService();
		ms.setChannelMode(EnumChannelMode.FireAndForget);
		ms.setType(XJDFConstants.ModifyQueueEntry);
		ms.setQuery(true);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals("QueryModifyQueueEntry", xjmf.getXPathAttribute("ResponseKnownMessages/MessageService/@Type", null));
		final XJDFToJDFConverter xjdfConv = new XJDFToJDFConverter(null);
		final JDFJMF jmf1 = xjdfConv.convert(xjmf).getJMFRoot();
		assertEquals("AbortQueueEntry", jmf1.getXPathAttribute("Response/MessageService/@Type", null));

	}

	/**
	 *
	 */
	@Test
	void testScope()
	{
		final XJMFHelper h = new XJMFHelper();
		final MessageHelper mh = h.appendMessage(EnumFamily.Query, EnumType.Resource);
		mh.appendElement(ElementName.RESOURCEQUPARAMS).setAttribute(AttributeName.SCOPE, EnumScope.Allowed.getName());
		final XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		final JDFDoc d = xc.convert(h.getRoot());
		final JDFResourceQuParams rqp2 = d.getJMFRoot().getQuery(0).getResourceQuParams();
		assertEquals(EnumScope.Allowed, rqp2.getScope());
	}

	/**
	 *
	 */
	@Test
	void testResponseModify()
	{
		final XJMFHelper h = new XJMFHelper();
		final MessageHelper mh = h.appendMessage(EnumFamily.Response, "ModifyQueueEntry");
		final JDFQueueEntry qe = (JDFQueueEntry) mh.appendElement(ElementName.QUEUEENTRY);
		qe.setQueueEntryStatus(EnumQueueEntryStatus.Aborted);
		qe.setQueueEntryID("q1");

		final XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		final JDFDoc d = xc.convert(h.getRoot());
		final JDFJMF jmf = d.getJMFRoot();
		final JDFResponse r = jmf.getResponse();
		assertEquals(0, r.getReturnCode());
		assertNull(r.getElement(ElementName.QUEUEENTRY));
	}

	/**
	 *
	 */
	@Test
	void testResponseModifyType()
	{
		final XJMFHelper h0 = new XJMFHelper();
		final MessageHelper mh0 = h0.appendMessage(EnumFamily.Command, "ModifyQueueEntry");
		mh0.setXPathValue(XJDFConstants.ModifyQueueEntryParams + "/@" + AttributeName.OPERATION, "Abort");
		mh0.setXPathValue(XJDFConstants.ModifyQueueEntryParams + "/" + ElementName.QUEUEFILTER + "/@" + AttributeName.JOBID, "j1");
		mh0.setHeader(AttributeName.ID, "C2");
		final XJDFToJDFConverter xc0 = new XJDFToJDFConverter(null);
		final JDFDoc d0 = xc0.convert(h0.getRoot());

		final XJMFHelper h = new XJMFHelper();
		final MessageHelper mh = h.appendMessage(EnumFamily.Response, "ModifyQueueEntry");
		mh.setQuery(mh0);
		final JDFQueueEntry qe = (JDFQueueEntry) mh.appendElement(ElementName.QUEUEENTRY);
		qe.setQueueEntryStatus(EnumQueueEntryStatus.Aborted);
		qe.setQueueEntryID("q1");

		final XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		final JDFDoc d = xc.convert(h.getRoot());
		final JDFJMF jmf = d.getJMFRoot();
		final JDFResponse r = jmf.getResponse();
		assertEquals(0, r.getReturnCode());
		assertNull(r.getElement(ElementName.QUEUEENTRY));
		assertEquals("AbortQueueEntry", r.getType());
	}

	/**
	 *
	 */
	@Test
	void testResponseModifyReject()
	{
		final XJMFHelper h = new XJMFHelper();
		final MessageHelper mh = h.appendMessage(EnumFamily.Response, "ModifyQueueEntry");
		mh.setReturnCode(113);
		final JDFQueueEntry qe = (JDFQueueEntry) mh.appendElement(ElementName.QUEUEENTRY);
		qe.setQueueEntryStatus(EnumQueueEntryStatus.Aborted);
		qe.setQueueEntryID("q1");
		final XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		final JDFDoc d = xc.convert(h.getRoot());
		final JDFJMF jmf = d.getJMFRoot();
		final JDFResponse r = jmf.getResponse();
		assertEquals(113, r.getReturnCode());
		assertNull(r.getElement(ElementName.QUEUEENTRY));
	}

	/**
	 *
	 */
	@Test
	void testResourceInfo()
	{
		final XJMFHelper h = new XJMFHelper();
		final MessageHelper mh = h.appendMessage(EnumFamily.Response, EnumType.Resource);
		final JDFResourceInfo ri = (JDFResourceInfo) mh.appendElement(ElementName.RESOURCEINFO);
		ri.setScope(org.cip4.jdflib.auto.JDFAutoResourceInfo.EnumScope.Present);

		final SetHelper sh = new SetHelper(ri.appendElement(XJDFConstants.ResourceSet));
		sh.setName(ElementName.MEDIA);
		sh.setUsage(EnumUsage.Input);
		for (int i = 0; i < 2; i++)
		{
			final ResourceHelper p = sh.appendPartition(null, true);
			p.setDescriptiveName("paper " + i);
			final JDFMedia m = (JDFMedia) p.getResource();
			m.setMediaType(EnumMediaType.Paper);
			m.setWeight(80 + 20 * i);
		}
		final XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		final JDFDoc d = xc.convert(h.getRoot());
		final JDFJMF jmf = d.getJMFRoot();
		assertNotNull(jmf.getResponse(0).getResourceInfo(0));
		assertNotNull(jmf.getResponse(0).getResourceInfo(1));
		assertNull(jmf.getResponse(0).getResourceInfo(2));
	}

	/**
	 *
	 */
	@Test
	void testSenderID()
	{
		final XJMFHelper h = new XJMFHelper();
		final MessageHelper mh = h.appendMessage(EnumFamily.Signal, EnumType.Status);
		mh.appendElement(ElementName.DEVICEINFO).setAttribute(AttributeName.DEVICEID, "d1");
		final XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		final JDFDoc d = xc.convert(h.getRoot());
		assertNull(StringUtil.getNonEmpty(d.getJMFRoot().getDeviceID()));
	}

	/**
	 *
	 */
	@Test
	void testSenderID2()
	{
		final XJMFHelper h = new XJMFHelper();
		h.getHeader().setAttribute(AttributeName.DEVICEID, "xjmfdev");
		final MessageHelper mh = h.appendMessage(EnumFamily.Query, EnumType.Status);
		mh.getHeader().setAttribute(AttributeName.DEVICEID, "qdev");
		mh.appendElement(ElementName.SUBSCRIPTION).setAttribute(AttributeName.URL, "foo");
		final XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		final JDFDoc d = xc.convert(h.getRoot());
		assertNull(StringUtil.getNonEmpty(d.getJMFRoot().getDeviceID()));
		assertEquals("xjmfdev", d.getJMFRoot().getSenderID());
	}

	/**
	 *
	 */
	@Test
	void testSenderID3()
	{
		final XJMFHelper h = new XJMFHelper();
		h.getHeader().setAttribute(AttributeName.DEVICEID, "xjmfdev");
		final MessageHelper mh = h.appendMessage(EnumFamily.Query, EnumType.Status);
		mh.getHeader().setAttribute(AttributeName.DEVICEID, "qdev");
		mh.appendElement(ElementName.SUBSCRIPTION).setAttribute(AttributeName.URL, "foo");
		final XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		final JDFDoc d = xc.convert(h.getRoot());
		assertEquals("qdev", d.getJMFRoot().getQuery(0).getSenderID());
	}

}
