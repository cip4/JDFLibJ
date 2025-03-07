/*
 * The CIP4 Software License, Version 1.0
 *
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
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumWorkType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.goldenticket.MISCPGoldenTicket;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class StatusCounterTest extends JDFTestCaseBase
{
	protected JDFDoc d;
	private JDFNode n;
	private StatusCounter sc;
	private String deviceID;
	private String resID;
	private JDFExposedMedia xpMedia;
	private JDFEmployee employee;

	/**
	 * @return the doc
	 *
	 *
	 */
	public static JDFDoc getJMF()
	{
		final StatusCounterTest statusCounterTest = new StatusCounterTest();
		try
		{
			statusCounterTest.setUp();
		}
		catch (final Exception e)
		{
			// NOP
		}
		return statusCounterTest.testAddPhase();
	}

	@Test
	void testCollisionWithFixVersion()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode docNode = doc.getJDFRoot();
		final JDFComponent comp = (JDFComponent) docNode.appendMatchingResource(ElementName.COMPONENT, JDFNode.EnumProcessUsage.AnyOutput, null);
		comp.appendElement("foo:bar", "www.foobar.com");
		new StatusCounter(docNode, null, null);

		final JDFNode n2 = new JDFDoc(ElementName.JDF).getJDFRoot();
		n2.setJobID("OuterJob");
		final KElement e = n2.appendElement("foo:bar", "http://www.foo.com/schema");
		final JDFNode n0 = (JDFNode) e.appendElement(ElementName.JDF);
		n0.setJobID("NestedJob");
		n0.appendStatusPool();
		assertNotNull(n0.getStatusPool(), "Status Pool could not be added");
		n2.fixVersion(JDFElement.EnumVersion.Version_1_3);
		assertNotNull(n0.getStatusPool(), "Status Pool did not survive: " + n2.toDisplayXML(4));
	}

	/**
	 * @throws Exception
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		d = creatXMDoc();
		n = d.getJDFRoot();
		xpMedia = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", null, null, 0);
		final JDFResourceLink rlxp = n.getLink(xpMedia, null);
		rlxp.setAmount(100, null);
		sc = new StatusCounter(n, null, null);
		deviceID = "Status-counter-TestDevice";
		sc.setDeviceID(deviceID);
		resID = xpMedia.getID();
		sc.setFirstRefID(resID);
		sc.addPhase(resID, 200, 0, true);
		employee = (JDFEmployee) new JDFDoc("Employee").getRoot();
		employee.setPersonalID("P1");
		super.setUp();
	}

	/**
	 *
	 */
	@Test
	void testDeviceID()
	{
		final boolean bChanged = sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
		assertTrue(bChanged);
		final JDFDoc docJMF = sc.getDocJMFPhaseTime();
		final JDFResponse sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
		final JDFDeviceInfo deviceInfo = sig.getDeviceInfo(0);
		assertEquals(deviceInfo.getDeviceID(), deviceID);
	}

	/**
	 *
	 */
	@Test
	void testPercentComplete()
	{
		final boolean bChanged = sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
		assertTrue(bChanged);
		final JDFDoc docJMF = sc.getDocJMFPhaseTime();
		final JDFResponse sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
		final JDFDeviceInfo deviceInfo = sig.getDeviceInfo(0);
		assertEquals(100, deviceInfo.getJobPhase().getPercentCompleted(), 0.1);
		sc.setPercentComplete(42);
		final boolean bChanged2 = sc.setPhase(null, null, null, null);
		assertFalse(bChanged2);
		final JDFDoc docJMF2 = sc.getDocJMFPhaseTime();
		assertEquals(42, docJMF2.getJMFRoot().getResponse().getDeviceInfo(0).getJobPhase().getPercentCompleted(), 0.1);
	}

	/**
	 *
	 */
	@Test
	void testSetPhaseNull()
	{
		final boolean bChanged = sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
		assertTrue(bChanged);
		final JDFDoc docJMF = sc.getDocJMFPhaseTime();
		final JDFResponse sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
		final JDFDeviceInfo deviceInfo = sig.getDeviceInfo(0);
		assertEquals(deviceInfo.getDeviceID(), deviceID);
		final boolean bChanged2 = sc.setPhase(null, null, null, null);
		assertFalse(bChanged2);
		final JDFDoc docJMF2 = sc.getDocJMFPhaseTime();
		assertNotSame(docJMF, docJMF2);

	}

	/**
	 *
	 */
	@Test
	void testShortString()
	{
		assertNotNull(sc.shortString());
	}

	/**
	 *
	 */
	@Test
	void testWasteAmount()
	{
		final VJDFAttributeMap singleMap = new VJDFAttributeMap();
		singleMap.add(xpMedia.getPartMapVector(false).elementAt(0));

		final MISCPGoldenTicket gt = new MISCPGoldenTicket(2, EnumVersion.Version_1_3, 2, 2, false, singleMap);
		gt.good = 1000;
		gt.waste = 100;
		gt.assign(null);
		n = gt.getNode();
		final JDFComponent c = (JDFComponent) n.getResource(ElementName.COMPONENT, null, 0);
		final JDFMedia m = (JDFMedia) n.getResource(ElementName.MEDIA, null, 0);
		final JDFResourceLink rl = n.getLink(c, null);
		final JDFResourceLink rlMedia = n.getLink(m, null);
		final VElement vRL = new VElement();
		vRL.add(rl);
		vRL.add(rlMedia);
		sc = new StatusCounter(null, null, null);
		sc.setTrackWaste(rl.getrRef(), true);
		sc.setTrackWaste(rlMedia.getrRef(), true);
		sc.setActiveNode(n, c.getPartMapVector(false), vRL);
		assertEquals(100, sc.getPlannedWaste(rlMedia.getrRef()), 0);
		assertEquals(1000, sc.getPlannedAmount(rl.getrRef()), 0);
	}

	/**
	 * @return
	 *
	 */
	public JDFDoc testAddPhase()
	{
		boolean bChanged = sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
		assertTrue(bChanged);
		JDFDoc docJMF = sc.getDocJMFPhaseTime();
		JDFResponse sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
		JDFJobPhase jp = sig.getDeviceInfo(0).getJobPhase(0);
		assertEquals(jp.getAmount(), 200, 0);
		sc.addPhase(resID, 0, 100, true);
		sc.setTrackWaste(resID, true);
		final JDFResourceLink rlXM = n.getLink(xpMedia, null);
		for (int loop = 1; loop < 4; loop++)
		{
			bChanged = sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
			assertFalse(bChanged);
			docJMF = sc.getDocJMFPhaseTime();
			sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
			jp = sig.getDeviceInfo(0).getJobPhase(0);
			assertEquals(jp.getAmount(), 200, 0, "multiple setPhase calls do not modify");
			assertEquals(rlXM.getActualAmount(new JDFAttributeMap("Condition", "Good")), 200, 0, "multiple setPhase calls do not modify: " + loop);
			assertEquals(jp.getPercentCompleted(), 100.0, 0, "% " + loop);
			sc.addPhase(resID, 0, 100, true);
			assertEquals(jp.getWaste(), loop * 100, 0, "" + loop);
			assertEquals(rlXM.getActualAmount(new JDFAttributeMap("Condition", "Waste")), 100 * loop, 0, "multiple setPhase calls do Stack: " + loop);
		}
		sc.setWorkType(EnumWorkType.Alteration);
		bChanged = sc.setPhase(EnumNodeStatus.InProgress, "ii", EnumDeviceStatus.Running, "r");
		assertTrue(bChanged);
		docJMF = sc.getDocJMFPhaseTime();
		sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
		jp = sig.getDeviceInfo(0).getJobPhase(0);
		assertEquals(jp.getAmount(), 200, 0);
		assertEquals(jp.getWaste(), 400, 0);
		assertTrue(jp.hasAttribute(AttributeName.PHASEAMOUNT));
		// get the second Signal (the new phase)
		sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 1);
		jp = sig.getDeviceInfo(0).getJobPhase(0);
		assertEquals(jp.getPhaseAmount(), 0.0, 0.0);
		assertEquals(jp.getMISDetails().getWorkType(), EnumWorkType.Alteration);

		sc.setFirstRefID("dummy");
		sc.addPhase(resID, 0, 100, true);
		sc.setTrackWaste(resID, true);
		sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
		docJMF = sc.getDocJMFPhaseTime();
		sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
		jp = sig.getDeviceInfo(0).getJobPhase(0);
		assertFalse(jp.hasAttribute(AttributeName.AMOUNT));
		assertEquals(jp.getMISDetails().getWorkType(), EnumWorkType.Alteration);
		return docJMF;
	}

	/**
	 *
	 */
	@Test
	void testEvent()
	{
		assertNull(sc.getDocJMFNotification(false));
		sc.setEvent("id", "value", "blah blah");
		d = sc.getDocJMFNotification(false);
		JDFDoc d2 = sc.getDocJMFNotification(false);
		assertTrue(d.getRoot().isEqual(d2.getRoot()));
		d = sc.getDocJMFNotification(true);
		d2 = sc.getDocJMFNotification(false);
		assertNull(d2);
		JDFJMF jmf = d.getJMFRoot();
		final JDFNotification noti = jmf.getSignal(0).getNotification();
		assertEquals(noti.getJobID(), n.getJobID(true));
		assertNotNull(noti.getEvent());
		d.write2File(sm_dirTestDataTemp + "jmfNotification.jmf", 2, false);
		assertTrue(jmf.isValid(EnumValidationLevel.Complete));
		sc.setEvent("id1", "value", "blah blah");
		sc.setEvent("id2", "value", "blah blah blah");
		d = sc.getDocJMFNotification(false);
		jmf = d.getJMFRoot();
		assertEquals(jmf.numChildElements(ElementName.SIGNAL, null), 2);
		sc.setEvent("id2", "value", "blah blah blah");
		d = sc.getDocJMFNotification(true);
		jmf = d.getJMFRoot();
		assertEquals(jmf.numChildElements(ElementName.SIGNAL, null), 3);
		d = sc.getDocJMFNotification(true);
		assertNull(d);
	}

	/**
	 * test for memory leaks in clone
	 */
	@Test
	void testMemLeak()
	{
		sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
		for (int i = 0; i < 100000; i++)
		{
			sc.getDocJMFPhaseTime();
			sc.getDocJMFNotification(true);
			sc.getDocJMFResource();
		}
		long dif = getCurrentMem() - mem;
		dif = Math.max(dif, 0);
		assertEquals(dif, 0, 12500000);
	}

	/**
	 * test for memory leaks in clone
	 */
	@Test
	void testMemLeak2()
	{
		sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
		final VElement v = new VElement();
		for (int i = 0; i < 222; i++)
		{
			if (i % 1000 == 0)
				log.info(i + " " + getCurrentMem() + " " + (getCurrentMem() / (i + 1)));
			v.add(sc.getDocJMFPhaseTime().getRoot());
		}
	}

	/**
	 * test for memory leaks in clone
	 */
	@Test
	void testMemLeak3()
	{
		sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		for (int i = 0; i < 222; i++)
		{
			if (i % 1000 == 0)
				log.info(i + " " + getCurrentMem() + " " + (getCurrentMem() / (i + 1)));
			v.add(sc.getDocJMFPhaseTime().getRoot().getXPathValueMap());
		}
	}

	/**
	 *
	 */
	@Test
	void testEmployee()
	{
		assertFalse(sc.removeEmployee(employee));
		assertEquals(sc.addEmployee(employee), 1);
		assertTrue(sc.removeEmployee(employee));
		assertEquals(sc.addEmployee(employee), 1);

		final JDFAuditPool ap = n.getAuditPool();
		JDFDoc docJMF = sc.getDocJMFPhaseTime();
		JDFResponse sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, -1);
		JDFDeviceInfo deviceInfo = sig.getDeviceInfo(0);
		assertTrue(deviceInfo.getEmployee(0).isEqual(employee));
		int nPT = ap.numChildElements("PhaseTime", null);
		sc.removeEmployee(employee);
		assertEquals(ap.numChildElements("PhaseTime", null), ++nPT, "modifying employess adds phase");
		docJMF = sc.getDocJMFPhaseTime();

		sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
		deviceInfo = sig.getDeviceInfo(0);
		assertNull(deviceInfo.getEmployee(0));
		final Vector<JDFEmployee> ve = new Vector<>();
		ve.add(employee);
		sc.replaceEmployees(ve);
		assertEquals(ap.numChildElements("PhaseTime", null), ++nPT, "modifying employess adds phase");
		docJMF = sc.getDocJMFPhaseTime();
		sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
		deviceInfo = sig.getDeviceInfo(0);
		assertNotNull(deviceInfo.getEmployee(0));

	}

	/**
	 *
	 */
	@Test
	void testIdle()
	{
		final JDFExposedMedia m = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", null, null, 0);
		boolean bChanged = sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
		assertTrue(bChanged);
		JDFDoc docJMF = sc.getDocJMFPhaseTime();
		JDFResponse sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
		JDFDeviceInfo deviceInfo = sig.getDeviceInfo(0);
		JDFJobPhase jp = deviceInfo.getJobPhase(0);
		assertEquals(jp.getAmount(), 200, 0);
		sc.addPhase(resID, 0, 100, true);
		sc.setTrackWaste(m.getID(), true);
		bChanged = sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
		assertFalse(bChanged);
		docJMF = sc.getDocJMFPhaseTime();
		sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
		bChanged = sc.setPhase(EnumNodeStatus.Completed, null, EnumDeviceStatus.Idle, null);
		assertTrue(bChanged);

		sc.setActiveNode(null, null, null);
		bChanged = sc.setPhase(null, null, EnumDeviceStatus.Idle, EnumDeviceStatus.Idle.getName());
		assertFalse(bChanged);
		bChanged = sc.setPhase(null, null, EnumDeviceStatus.Idle, "very idle");
		assertTrue(bChanged);

		docJMF = sc.getDocJMFPhaseTime();
		sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
		deviceInfo = sig.getDeviceInfo(0);
		jp = deviceInfo.getJobPhase(0);
		assertNull(jp);
	}

}
