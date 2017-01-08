/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions.examples;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoMessageService.EnumChannelMode;
import org.cip4.jdflib.auto.JDFAutoResourceQuParams.EnumResourceDetails;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.extensions.MessageHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJMFHelper;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFMessageService;
import org.cip4.jdflib.jmf.JDFQueueEntry;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFResourceQuParams;
import org.cip4.jdflib.node.JDFNode.EnumActivation;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJMFExampleTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	public void testCommandResumeQE()
	{
		XJMFHelper xjmfHelper = new XJMFHelper();
		MessageHelper signal = xjmfHelper.appendMessage(EnumFamily.Command, XJDFConstants.ModifyQueueEntry);
		signal.setXPathValue(XJDFConstants.ModifyQueueEntryParams + "/@" + AttributeName.OPERATION, "Resume");
		signal.setXPathValue(XJDFConstants.ModifyQueueEntryParams + "/" + ElementName.QUEUEFILTER + "/@" + AttributeName.JOBID, "j1");
		signal.setXPathValue(XJDFConstants.Header + "/@" + AttributeName.ID, "C1");
		setSnippet(xjmfHelper);
		writeTest(xjmfHelper, "CommandModifyQE.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testResponseResumeQE()
	{
		XJMFHelper xjmfHelper = new XJMFHelper();
		MessageHelper response = xjmfHelper.appendMessage(EnumFamily.Response, XJDFConstants.ModifyQueueEntry);
		response.setXPathValue(XJDFConstants.Header + "/@" + AttributeName.REFID, "C1");
		response.setXPathValue(XJDFConstants.Header + "/@" + AttributeName.ID, "R1");
		response.setAttribute(AttributeName.RETURNCODE, "0");
		JDFQueueEntry qe = (JDFQueueEntry) response.getRoot().appendElement(ElementName.QUEUEENTRY);
		qe.setJobID("j1");
		qe.setQueueEntryID("QE1");
		qe.setStatus(EnumNodeStatus.Waiting);
		qe.setActivation(EnumActivation.Active);

		xjmfHelper.writeToFile(sm_dirTestDataTemp + "xjdf/ResponseModifyQE.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testQueryPaper()
	{
		XJMFHelper xjmfHelper = new XJMFHelper();
		MessageHelper q = xjmfHelper.appendMessage(EnumFamily.Query, EnumType.Resource);
		q.setXPathValue(XJDFConstants.Header + "/@" + AttributeName.ID, "Q1");
		JDFResourceQuParams rqp = (JDFResourceQuParams) q.appendElement(ElementName.RESOURCEQUPARAMS);
		rqp.setResourceName(ElementName.MEDIA);
		rqp.setAttribute(AttributeName.SCOPE, "Allowed");
		rqp.setResourceDetails(EnumResourceDetails.Full);
		xjmfHelper.writeToFile(sm_dirTestDataTemp + "xjdf/QueryPaper.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testResponsePaper()
	{
		XJMFHelper xjmfHelper = new XJMFHelper();
		MessageHelper q = xjmfHelper.appendMessage(EnumFamily.Response, EnumType.Resource);
		q.setXPathValue(XJDFConstants.Header + "/@" + AttributeName.REFID, "Q1");
		q.setXPathValue(XJDFConstants.Header + "/@" + AttributeName.ID, "R1");
		JDFResourceInfo ri = (JDFResourceInfo) q.appendElement(ElementName.RESOURCEINFO);
		ri.setResourceName(ElementName.MEDIA);
		SetHelper sh = new SetHelper(ri.appendElement(XJDFConstants.ResourceSet));
		sh.setName(ElementName.MEDIA);
		for (int i = 1; i < 3; i++)
		{
			ResourceHelper rh = sh.appendPartition(null, true);
			rh.setExternalID("ID_" + i);
			rh.setAttribute(AttributeName.DESCRIPTIVENAME, "Paper # " + i);
			((JDFMedia) rh.getResource()).setDimensionCM(new JDFXYPair(21, 29));
			((JDFMedia) rh.getResource()).setWeight(60 + 20 * i);

		}
		xjmfHelper.writeToFile(sm_dirTestDataTemp + "xjdf/ResponsePaper.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testResponseKnownDevices()
	{
		XJMFHelper xjmfHelper = new XJMFHelper();
		MessageHelper response = xjmfHelper.appendMessage(EnumFamily.Response, EnumType.KnownDevices);
		response.setXPathValue(XJDFConstants.Header + "/@" + AttributeName.REFID, "C1");
		response.setXPathValue(XJDFConstants.Header + "/@" + AttributeName.ID, "R1");
		response.setXPathValue(XJDFConstants.Header + "/@" + AttributeName.DEVICEID, "VeggieController");
		response.setAttribute(AttributeName.RETURNCODE, "0");
		JDFDevice dev = (JDFDevice) response.getRoot().appendElement(ElementName.DEVICE);
		dev.setDeviceID("dev1");
		dev.setDeviceType("ACME Linda potato press V16-12");
		dev.setAttribute(XJDFConstants.XJMFURL, "http://acmepotato1:1234/xjmfurl");

		dev = (JDFDevice) response.getRoot().appendElement(ElementName.DEVICE);
		dev.setDeviceID("dev2");
		dev.setDeviceType("ACME Baldrick turnip press V42-66");
		dev.setAttribute(XJDFConstants.XJMFURL, "http://acmeturnip1:1234/xjmfurl");

		xjmfHelper.writeToFile(sm_dirTestDataTemp + "xjdf/ResponseKnownDevices.xjmf");
	}

	/**
	 *
	 */
	@Test
	public void testResponseKnownMessages()
	{
		XJMFHelper xjmfHelper = new XJMFHelper();
		MessageHelper response = xjmfHelper.appendMessage(EnumFamily.Response, EnumType.KnownMessages);
		JDFFileSpec fs = (JDFFileSpec) response.appendElement(ElementName.FILESPEC);
		fs.setResourceUsage("Schema");
		fs.setURL("http://acmeturnip1:1234/xsdurl");
		response.setXPathValue(XJDFConstants.Header + "/@" + AttributeName.REFID, "C1");
		response.setXPathValue(XJDFConstants.Header + "/@" + AttributeName.ID, "R1");
		response.setXPathValue(XJDFConstants.Header + "/@" + AttributeName.DEVICEID, "Controller");
		response.setAttribute(AttributeName.RETURNCODE, "0");
		JDFMessageService ms = (JDFMessageService) response.appendElement(ElementName.MESSAGESERVICE);
		ms.setType("QueryKnownMessages");
		ms.setAttribute(AttributeName.CHANNELMODE, ElementName.RESPONSE);
		ms = (JDFMessageService) response.appendElement(ElementName.MESSAGESERVICE);
		ms.setType("QueryStatus");
		ms.setAttribute(AttributeName.CHANNELMODE, EnumChannelMode.FireAndForget.getName());
		ms = (JDFMessageService) response.appendElement(ElementName.MESSAGESERVICE);
		ms.setType("CommandSubmitQueueEntry");
		ms.setAttribute(AttributeName.CHANNELMODE, ElementName.RESPONSE);
		ms = (JDFMessageService) response.appendElement(ElementName.MESSAGESERVICE);
		ms.setType("ResponseReturnQueueEntry");

		xjmfHelper.writeToFile(sm_dirTestDataTemp + "xjdf/ResponseKnownMessages.xjmf");
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}
}
