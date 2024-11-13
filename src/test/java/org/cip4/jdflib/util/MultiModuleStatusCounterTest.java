/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
/*
 * @author muchadie
 */
package org.cip4.jdflib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class MultiModuleStatusCounterTest extends JDFTestCaseBase
{

	@Test
	void testMultiModuleJob()
	{
		final MultiModuleStatusCounter msc = new MultiModuleStatusCounter();
		final StatusCounter scDev = new StatusCounter(null, null, null);
		scDev.setDeviceID("d1");
		msc.addModule(scDev);
		final JDFResponse idlePhase = msc.getStatusResponse().getJMFRoot().getResponse(0);
		assertEquals(idlePhase.numChildElements(ElementName.DEVICEINFO, null), 1);
		final JDFNode n = creatXMDoc().getJDFRoot();
		final StatusCounter scRIP = new StatusCounter(n, null, null);
		msc.addModule(scRIP);
		final JDFExposedMedia m = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", null, null, 0);
		final String resID = m.getID();
		scRIP.setFirstRefID(resID);
		scRIP.setPhase(EnumNodeStatus.InProgress, "RIP", EnumDeviceStatus.Running, null);

		scRIP.addPhase(resID, 200, 0, true);
		final JDFResponse p1 = msc.getStatusResponse().getJMFRoot().getResponse(0);
		assertEquals(1, p1.numChildElements(ElementName.DEVICEINFO, null));
		assertEquals(1, p1.getDeviceInfo(0).getAllJobPhase().size());

		final JDFNode n2 = creatXMDoc().getJDFRoot();
		final StatusCounter scRIP2 = new StatusCounter(n2, null, null);
		msc.addModule(scRIP2);
		scRIP2.setPhase(EnumNodeStatus.InProgress, "RIP", EnumDeviceStatus.Running, null);
		scRIP2.addPhase(resID, 100, 0, true);
		final JDFResponse p2 = msc.getStatusResponse().getJMFRoot().getResponse(0);
		assertEquals(1, p2.numChildElements(ElementName.DEVICEINFO, null));
		assertEquals(2, p2.getDeviceInfo(0).getAllJobPhase().size());

		msc.removeModule(scRIP);
		final JDFResponse p3 = msc.getStatusResponse().getJMFRoot().getResponse(0);
		assertEquals(1, p3.numChildElements(ElementName.DEVICEINFO, null));
		assertEquals(1, p3.getDeviceInfo(0).getAllJobPhase().size());
	}

	@Test
	void testMultiModuleJob2()
	{
		final MultiModuleStatusCounter msc = new MultiModuleStatusCounter();
		final StatusCounter scDev = new StatusCounter(null, null, null);
		scDev.setDeviceID("d1");
		msc.addModule(scDev);
		final JDFResponse idlePhase = msc.getStatusResponse().getJMFRoot().getResponse(0);
		assertEquals(idlePhase.numChildElements(ElementName.DEVICEINFO, null), 1);
		final JDFNode n = creatXMDoc().getJDFRoot();
		final StatusCounter scRIP = new StatusCounter(n, null, null);
		msc.addModule(scRIP);
		final JDFExposedMedia m = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", null, null, 0);
		final String resID = m.getID();
		scRIP.setFirstRefID(resID);
		scRIP.setPhase(EnumNodeStatus.InProgress, "RIP", EnumDeviceStatus.Running, null);

		scRIP.addPhase(resID, 200, 0, true);
		final JDFResponse p1 = msc.getStatusResponse().getJMFRoot().getResponse(0);
		assertEquals(1, p1.numChildElements(ElementName.DEVICEINFO, null));
		assertEquals(1, p1.getDeviceInfo(0).getAllJobPhase().size());
		scRIP.setPhase(EnumNodeStatus.Cleanup, "abc", EnumDeviceStatus.Cleanup, null);

		final JDFNode n2 = creatXMDoc().getJDFRoot();
		final StatusCounter scRIP2 = new StatusCounter(n2, null, null);
		msc.addModule(scRIP2);
		scRIP2.setPhase(EnumNodeStatus.InProgress, "RIP", EnumDeviceStatus.Running, null);
		scRIP2.addPhase(resID, 100, 0, true);
		final JDFResponse p2 = msc.getStatusResponse().getJMFRoot().getResponse(0);
		assertEquals(1, p2.numChildElements(ElementName.DEVICEINFO, null));
		assertEquals(2, p2.getDeviceInfo(0).getAllJobPhase().size());
		final JDFResponse p22 = msc.getStatusResponse().getJMFRoot().getResponse(1);
		assertEquals(1, p22.numChildElements(ElementName.DEVICEINFO, null));
		assertEquals(2, p22.getDeviceInfo(0).getAllJobPhase().size());

	}

	@Test
	void testGetDevCounter()
	{
		final StatusCounter scDev = new StatusCounter(null, null, null);
		final MultiModuleStatusCounter msc = new MultiModuleStatusCounter(scDev);
		assertEquals(scDev, msc.getDeviceCounter());
	}

	@Test
	void testToString()
	{
		final StatusCounter scDev = new StatusCounter(null, null, null);
		final MultiModuleStatusCounter msc = new MultiModuleStatusCounter(scDev);
		assertNotNull(msc.toString());
	}

	/**
	 *
	 */
	@Test
	void testMultiModule()
	{
		final JDFNode n = creatXMDoc().getJDFRoot();
		final StatusCounter scRIP = new StatusCounter(n, null, null);
		scRIP.addModule("ID_RIP", "RIP");
		final StatusCounter scSetter = new StatusCounter(n, null, null);
		scSetter.addModule("ID_Setter", "Platesetter");

		final MultiModuleStatusCounter msc = new MultiModuleStatusCounter();
		msc.addModule(scRIP);
		msc.addModule(scSetter);

		final JDFExposedMedia m = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", null, null, 0);
		final String resID = m.getID();
		scRIP.setFirstRefID(resID);
		scRIP.addPhase(resID, 200, 0, true);
		boolean bChanged = scRIP.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
		assertTrue(bChanged);
		final JDFDoc docJMF = scRIP.getDocJMFPhaseTime();
		final JDFResponse sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
		final JDFDeviceInfo deviceInfo = sig.getDeviceInfo(0);
		final JDFJobPhase jp = deviceInfo.getJobPhase(0);
		assertEquals(jp.getAmount(), 200, 0);
		scRIP.addPhase(resID, 0, 100, true);
		scRIP.setTrackWaste(m.getID(), true);
		bChanged = scRIP.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
		assertFalse(bChanged);
		JDFDoc dJMFAll = msc.getStatusResponse();
		assertEquals(dJMFAll.getRoot().getChildrenByTagName(ElementName.JOBPHASE, null, null, false, true, -1).size(), 1);
		scSetter.setPhase(EnumNodeStatus.InProgress, "seti", EnumDeviceStatus.Running, "run");
		scSetter.setFirstRefID(resID);
		scSetter.addPhase(resID, 400, 0, true);
		dJMFAll = msc.getStatusResponse();
		assertEquals(dJMFAll.getRoot().getChildrenByTagName(ElementName.JOBPHASE, null, null, false, true, -1).size(), 2, "1 RIP, 1 setter");

		scRIP.setActiveNode(null, null, null);
		bChanged = scRIP.setPhase(null, null, EnumDeviceStatus.Idle, null);
		dJMFAll = msc.getStatusResponse();
		assertEquals(dJMFAll.getRoot().getChildrenByTagName(ElementName.JOBPHASE, null, null, false, true, -1).size(), 1);

		scSetter.setActiveNode(null, null, null);
		bChanged = scSetter.setPhase(null, null, EnumDeviceStatus.Idle, null);
		dJMFAll = msc.getStatusResponse();
		assertEquals(dJMFAll.getRoot().getChildrenByTagName(ElementName.JOBPHASE, null, null, false, true, -1).size(), 0);
	}
}
