/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.jmf;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoMessageService.EnumJMFRole;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.resource.JDFDeviceList;
import org.cip4.jdflib.resource.devicecapability.JDFAction;
import org.cip4.jdflib.resource.devicecapability.JDFActionPool;
import org.cip4.jdflib.resource.devicecapability.JDFDevCap;
import org.cip4.jdflib.resource.devicecapability.JDFDevCaps;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap;
import org.cip4.jdflib.resource.devicecapability.JDFStringState;
import org.cip4.jdflib.resource.devicecapability.JDFTerm.EnumTerm;
import org.cip4.jdflib.resource.devicecapability.JDFTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/**
 * @author Rainer Prosi
 * 
 *         Test of the Resource JMF
 */
class JMFKnownMessagesTest extends JDFTestCaseBase
{
	/**
	 * 
	 */
	@Test
	void testJMFDevCaps()
	{
		JDFDoc doc = new JDFDoc(ElementName.JMF);
		JDFJMF jmfDC = doc.getJMFRoot();

		JDFResponse r = jmfDC.appendResponse(EnumType.KnownMessages);
		jmfDC.setSenderID("DeviceSenderID");

		JDFMessageService ms = r.appendMessageService();
		ms.setJMFRole(EnumJMFRole.Sender);
		ms.setQuery(true);
		ms.setType("KnownDevices");
		JDFDevCaps dcs = ms.appendDevCaps();
		dcs.setName("DeviceList");
		JDFDevCap dc = dcs.appendDevCapInPool();
		JDFDevCap dcDI = dc.appendDevCap();
		dcDI.setName(ElementName.DEVICEINFO);
		dcDI.setMinOccurs(1);
		dcDI.setMaxOccurs(1);
		JDFStringState state = dcDI.appendStringState(AttributeName.DEVICEID);
		state.setRequired(true);

		state = dcDI.appendStringState(AttributeName.DEVICESTATUS);
		state.setRequired(true);
		state.appendValueAllowedValue("Running");

		ms = r.appendMessageService();
		ms.setJMFRole(EnumJMFRole.Sender);
		ms.setQuery(true);
		ms.setType("KnownMessages");
		dcs = ms.appendDevCaps();
		dcs.setName("MessageService");
		dc = dcs.appendDevCapInPool();
		state = dc.appendStringState(AttributeName.TYPE);
		state.setRequired(true);
		state = dc.appendStringState("Foo");
		state.setRequired(false);

		JDFActionPool ap = ms.appendActionPool();
		JDFAction a = ap.appendActionTest(EnumTerm.IsPresentEvaluation, true);
		JDFTest t = a.getTest();
		// JDFTerm term=
		t.getTerm();
		// TODO
		JDFDoc docJMF = new JDFDoc("JMF");
		JDFJMF jmf = docJMF.getJMFRoot();
		for (int i = 0; i < 3; i++)
		{
			JDFResponse resp = jmf.appendResponse(EnumType.KnownDevices);
			JDFDeviceList dl = resp.appendDeviceList();
			JDFDeviceInfo di = dl.appendDeviceInfo();
			di.setDeviceID("d123");
			di.setDeviceStatus(EnumDeviceStatus.Running);
			XMLDoc report = JDFDeviceCap.getJMFInfo(jmf, r, EnumFitsValue.Allowed, EnumValidationLevel.Complete, true);
			Assertions.assertEquals(report.getRoot().getAttribute("IsValid"), "true");
		}
		{
			JDFResponse resp = jmf.appendResponse(EnumType.KnownMessages);
			JDFMessageService mi = resp.appendMessageService();
			mi.setType("FooBar");
			doc.write2File(sm_dirTestDataTemp + "JMFDevCap.xml", 2, false);
			docJMF.write2File(sm_dirTestDataTemp + "JMFDevCapTest.jmf", 2, false);

		}
		XMLDoc report = JDFDeviceCap.getJMFInfo(jmf, r, EnumFitsValue.Allowed, EnumValidationLevel.Complete, true);
		Assertions.assertEquals(report.getRoot().getAttribute("IsValid"), "true");

		doc.write2File(sm_dirTestDataTemp + "JMFDevCap.xml", 2, false);
		docJMF.write2File(sm_dirTestDataTemp + "JMFDevCapTest.jmf", 2, false);
		{
			jmf.appendResponse(EnumType.AbortQueueEntry);
		}
		report = JDFDeviceCap.getJMFInfo(jmf, r, EnumFitsValue.Allowed, EnumValidationLevel.Complete, true);
		Assertions.assertEquals(report.getRoot().getAttribute("IsValid"), "false");

	}
	// ///////////////////////////////////////////////////////////////////
}