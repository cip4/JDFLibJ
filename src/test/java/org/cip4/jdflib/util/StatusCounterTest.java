/*
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

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceFilter.EnumDeviceDetails;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumWorkType;
import org.cip4.jdflib.auto.JDFAutoNotification.EnumClass;
import org.cip4.jdflib.auto.JDFAutoResourceInfo.EnumScope;
import org.cip4.jdflib.auto.JDFAutoStatusQuParams.EnumJobDetails;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.goldenticket.MISCPGoldenTicket;
import org.cip4.jdflib.goldenticket.MISFinGoldenTicket;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.jmf.JMFBuilderFactory;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFEvent;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.prepress.JDFInk;
import org.cip4.jdflib.util.StatusCounter.EAmountType;
import org.cip4.jdflib.util.StatusCounter.LinkAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 */
public class StatusCounterTest extends JDFTestCaseBase
{
	@Test
	void testCollisionWithFixVersion()
	{
		new SingleStatusCounterTest().testCollisionWithFixVersion();
	}

	@Test
	void testDeviceID()
	{
		new SingleStatusCounterTest().testDeviceID();
	}

	@Test
	void testEmployee()
	{
		new SingleStatusCounterTest().testEmployee();
	}

	@Test
	void testEvent()
	{
		new SingleStatusCounterTest().testEvent();
	}

	@Test
	void testIdle()
	{
		new SingleStatusCounterTest().testIdle();
	}

	@Test
	void testAddPhaseXM()
	{
		new SingleStatusCounterTest().testAddPhaseXM();
	}

	@Test
	void testAddPhase()
	{
		new SingleStatusCounterTest().testAddPhase();
	}

	@Test
	void testMemLeak()
	{
		new SingleStatusCounterTest().testMemLeak();
	}

	@Test
	void testMemLeak2()
	{
		new SingleStatusCounterTest().testMemLeak2();
	}

	@Test
	void testMemLeak3()
	{
		new SingleStatusCounterTest().testMemLeak3();
	}

	@Test
	void testPercentComplete()
	{
		new SingleStatusCounterTest().testPercentComplete();
	}

	@Test
	void testSetPhaseNull()
	{
		new SingleStatusCounterTest().testSetPhaseNull();
	}

	@Test
	void testShortString()
	{
		new SingleStatusCounterTest().testShortString();
	}

	@Test
	void testWasteAmount()
	{
		new SingleStatusCounterTest().testWasteAmount();
	}

	/**
	 *
	 */
	@Test
	void testSpeed()
	{
		final JDFNode n = creatXMDoc().getJDFRoot();
		final StatusCounter sc = new StatusCounter(n, null, null);
		final LinkAmount la = sc.getLinkAmount(0);
		assertEquals(0, la.getAmount(EAmountType.Speed), 0.1);
		la.updateSpeed(0);
		assertEquals(0, la.getAmount(EAmountType.Speed), 0.1);
		la.setAmount(EAmountType.TotalAmount, 90, null);
		la.setAmount(EAmountType.TotalWaste, 10, null);
		la.updateSpeed(20000);
		assertEquals(0, la.getAmount(EAmountType.Speed), 0.1);
		la.updateSpeed(100000);
		assertEquals(3600, la.getAmount(EAmountType.Speed), 0.1);
		la.setAmount(EAmountType.TotalAmount, 180, null);
		la.setAmount(EAmountType.TotalWaste, 20, null);
		la.updateSpeed(200000);
		assertEquals(3600, la.getAmount(EAmountType.Speed), 0.1);
		la.setAmount(EAmountType.TotalAmount, 270, null);
		la.setAmount(EAmountType.TotalWaste, 30, null);
		la.updateSpeed(300000);
		assertEquals(3600, la.getAmount(EAmountType.Speed), 0.1);
	}

	/**
	 *
	 */
	@Test
	void testSpeed2()
	{
		final JDFNode n = creatXMDoc().getJDFRoot();
		final StatusCounter sc = new StatusCounter(n, null, null);
		assertEquals(0, sc.getSpeed(), 0.1);
		sc.setSpeed(123);
		assertEquals(123, sc.getSpeed(), 0.1);
		sc.setSpeed(42);
		assertEquals(42, sc.getSpeed(), 0.1);
	}

	/**
	*
	*/
	@Test
	public void testJMFCartridgeChange()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceSignal(true, null);
		jmf.setSenderID("CDM");
		final JDFSignal resource = jmf.getSignal(0);
		resource.setSenderID("JoeThePrimeFire");
		final JDFResourceInfo ri = resource.getCreateResourceInfo(0);
		ri.setScope(EnumScope.Present);
		ri.setProductID("SAPID");
		final JDFPart p = ri.appendPart();
		p.setSeparation("Green");
		p.setAttribute(AttributeName.LOTID, "Batch42");
		final JDFInk ink = (JDFInk) ri.appendResource(ElementName.INK);
		ink.setProductID("SapID");
		ink.setGeneralID("ContainerID", "BibID");

		final JDFNotification not = resource.appendNotification();
		not.setClass(EnumClass.Event);
		final JDFEvent ev = not.appendEvent();
		ev.setEventID("4711");
		ev.setDescriptiveName("Cartridge change");
		ev.setEventValue("Cartridge Code - IL");

		jmf.write2File(sm_dirTestDataTemp + "cartridge.jmf");
		assertTrue(jmf.isValid(EnumValidationLevel.Complete));

		ink.setAttribute("ExpirationDate", new JDFDate().setTime(0, 0, 0).addOffset(0, 0, 0, 444).getDateTimeISO());

	}

	/**
	*
	*/
	@Test
	public void testJMFStatus()
	{
		FileUtil.forceDelete(new File(sm_dirTestDataTemp + "examplejmf"));
		KElement.setLongID(false);
		final JDFJMF jmfq = JMFBuilderFactory.getJMFBuilder(null).buildStatus(EnumDeviceDetails.Full, EnumJobDetails.MIS);

		final StatusCounter sc = new StatusCounter(null, null, null);

		sc.setDeviceID("MyDeviceID");
		sc.setPhase(null, null, EnumDeviceStatus.Idle, null);
		sc.setTotalCounter(10000);
		sc.setCurrentCounter(0);
		sc.setSplitJobPhase(true);

		List<JDFJMF> jmfs = getSignals(sc, jmfq);
		int i = 0;
		for (final JDFJMF jmf : jmfs)
		{
			jmf.write2File(sm_dirTestDataTemp + "examplejmf/" + i + ".idle.jmf");
			i++;
		}

		final MISFinGoldenTicket gt = new MISFinGoldenTicket(1, defaultVersion, 1, 1, null);
		gt.addSheet("Sheet1");
		gt.addSheet("Sheet2");
		gt.setCategory(MISFinGoldenTicket.MISFIN_STITCHFIN);

		gt.assign(null);
		gt.setGrayBox(false);
		final JDFNode n = gt.getNode();
		n.getLink(ElementName.COMPONENT, EnumUsage.Output, null).setAmount(1000);
		final JDFResource outComp = n.getResource(ElementName.COMPONENT, EnumUsage.Output);
		sc.setActiveNode(n, null, new VElement(outComp));
		final String id = outComp.getID();
		sc.setFirstRefID(id);
		sc.setTrackWaste("*", true);

		sc.setPhase(EnumNodeStatus.Setup, "setup", EnumDeviceStatus.Setup, "setup");
		sc.addPhase(null, 0, 0, true);
		jmfs = getSignals(sc, jmfq);
		for (final JDFJMF jmf2 : jmfs)
		{
			updateJMF(jmf2, 0, 0, 1, 0, true);
			jmf2.write2File(sm_dirTestDataTemp + "examplejmf/" + i + ".setupstart.jmf");
			i++;
		}
		assertEquals(10000, jmfs.get(0).getSignal().getDeviceInfo(0).getTotalProductionCounter(), 0.1);

		sc.setPhase(EnumNodeStatus.Setup, "setup", EnumDeviceStatus.Setup, "waste");
		sc.setSpeed(100);
		sc.addPhase(null, 0, 10, true);
		jmfs = getSignals(sc, jmfq);
		assertEquals(10010, jmfs.get(0).getSignal().getDeviceInfo(0).getTotalProductionCounter(), 0.1);
		assertEquals(100, jmfs.get(0).getSignal().getDeviceInfo(0).getSpeed(), 0.1);
		for (final JDFJMF jmf2 : jmfs)
		{
			updateJMF(jmf2, 0, 0, 2, 0, true);
			jmf2.write2File(sm_dirTestDataTemp + "examplejmf/" + i + ".setupstart.jmf");
			i++;
		}
		sc.setPhase(EnumNodeStatus.InProgress, "good", EnumDeviceStatus.Running, "good");
		sc.setSpeed(500);
		jmfs = getSignals(sc, jmfq);
		for (final JDFJMF jmf3 : jmfs)
		{
			updateJMF(jmf3, 5, 0, 2, 0, true);
			updateJMF(jmf3, 5, 0, 0, 0, false);
			jmf3.write2File(sm_dirTestDataTemp + "examplejmf/" + i + ".runstart.jmf");
			i++;
		}
		assertEquals(10010, jmfs.get(0).getSignal().getDeviceInfo(0).getTotalProductionCounter(), 0.1);
		assertEquals(100, jmfs.get(0).getSignal().getDeviceInfo(0).getSpeed(), 0.1);
		assertEquals(500, jmfs.get(1).getSignal().getDeviceInfo(0).getSpeed(), 0.1);
		for (int j = 0; j < 10; j++)
		{
			sc.addPhase(null, 100, 0, true);
			sc.setPhase(EnumNodeStatus.InProgress, "good", EnumDeviceStatus.Running, "good");
			jmfs = getSignals(sc, jmfq);
			assertEquals(10110 + j * 100, jmfs.get(0).getSignal().getDeviceInfo(0).getTotalProductionCounter(), 0.1);
			assertEquals(500, jmfs.get(0).getSignal().getDeviceInfo(0).getSpeed(), 0.1);
			for (final JDFJMF jmfr : jmfs)
			{
				updateJMF(jmfr, 5, 0, 1, 0, true);
				updateJMF(jmfr, 0, j, 0, 0, false);
				jmfr.write2File(sm_dirTestDataTemp + "examplejmf/" + i + ".runheartbeat.jmf");
				i++;
			}
		}
		sc.setPhase(EnumNodeStatus.Completed, "good", EnumDeviceStatus.Running, "good");
		jmfs = getSignals(sc, jmfq);
		assertEquals(11010, jmfs.get(0).getSignal().getDeviceInfo(0).getTotalProductionCounter(), 0.1);
		assertEquals(1010, jmfs.get(0).getSignal().getDeviceInfo(0).getProductionCounter(), 0.1);
		assertEquals(500, jmfs.get(0).getSignal().getDeviceInfo(0).getSpeed(), 0.1);
		for (final JDFJMF jmfc : jmfs)
		{
			updateJMF(jmfc, 5, 0, 1, 0, true);
			updateJMF(jmfc, 0, 10, 0, 0, false);
			jmfc.write2File(sm_dirTestDataTemp + "examplejmf/" + i + ".completed.jmf");
			i++;
		}
		sc.setSpeed(0);
		sc.setActiveNode(null, null, null);
		sc.setPhase(null, null, EnumDeviceStatus.Idle, null);
		jmfs = getSignals(sc, jmfq);
		for (final JDFJMF jmfc : jmfs)
		{
			updateJMF(jmfc, 5, 10, 1, 0, true);
			jmfc.write2File(sm_dirTestDataTemp + "examplejmf/" + i + ".idle.jmf");
		}
		assertEquals(0, jmfs.get(0).getSignal().getDeviceInfo(0).getSpeed(), 0.1);

	}

	private List<JDFJMF> getSignals(StatusCounter sc, final JDFJMF jmfq)
	{
		final List<JDFJMF> jmfs = sc.getJMFStatusList();
		for (final JDFJMF jmf : jmfs)
		{
			for (final JDFResponse r : jmf.getAllResponse())
			{
				r.setQuery(jmfq.getQuery());
			}
			jmf.convertResponses(jmfq.getQuery());
		}
		return jmfs;
	}

	void updateJMF(JDFJMF jmf, int seconds, int minutes, int hours, int days, boolean withstart)
	{
		jmf.setTimeStamp(jmf.getTimeStamp().addOffset(seconds, minutes, hours, days));
		jmf.setID(KElement.uniqueID(0));
		for (final KElement e : jmf.getMessageVector(null, null))
		{
			updateTime(e, AttributeName.TIME, seconds, minutes, hours, days);
			final JDFMessage m = (JDFMessage) e;
			m.setID(KElement.uniqueID(0));
			if (EnumType.Status.equals(m.getEnumType()))
			{
				for (int idi = 0; true; idi++)
				{
					final JDFDeviceInfo di = m.getDeviceInfo(idi);
					if (di == null)
					{
						break;
					}
					if (withstart)
					{
						updateTime(di, AttributeName.IDLESTARTTIME, seconds, minutes, hours, days);
					}
					updateTime(di, AttributeName.ENDTIME, seconds, minutes, hours, days);
					for (final JDFJobPhase jp : di.getAllJobPhase())
					{
						if (withstart)
						{
							updateTime(jp, AttributeName.STARTTIME, seconds, minutes, hours, days);
						}
					}
				}
			}
		}

	}

	private void updateTime(KElement e, String key, int seconds, int minutes, int hours, int days)
	{
		final JDFDate time = JDFDate.createDate(e.getAttribute(key));
		if (time != null)
		{
			final JDFDate offset = time.addOffset(seconds, minutes, hours, days);
			e.setAttribute(key, offset, null);
		}
	}

	/**
	 *
	 */
	@Test
	void testStitchGB2()
	{
		final MISFinGoldenTicket gt = new MISFinGoldenTicket(1, defaultVersion, 1, 1, null);
		gt.addSheet("Sheet1");
		gt.addSheet("Sheet2");
		gt.setCategory(MISFinGoldenTicket.MISFIN_STITCHFIN);

		gt.setGrayBox(false);
		gt.assign(null);
		final JDFNode n = gt.getNode();
		assertEquals(1000, n.getLink(ElementName.COMPONENT, EnumUsage.Output, null).getAmount(null));
		gt.makeReady();
		final StatusCounter sc = gt.getStatusCounter();
		sc.setTotalCounter(12345);
		sc.setDeviceID("MyDeviceID");
		sc.setPhase(null, null, EnumDeviceStatus.Idle, null);

		sc.addPhase(null, 100, 0, true);
		sc.setPhase(EnumNodeStatus.InProgress, "good", EnumDeviceStatus.Running, "good");
		final JDFJMF jmf = sc.getDocJMFPhaseTime().getJMFRoot();
		sc.addPhase(null, 100, 0, true);
		sc.setPhase(EnumNodeStatus.InProgress, "good", EnumDeviceStatus.Running, "good");
		final JDFJMF jmf2 = sc.getDocJMFPhaseTime().getJMFRoot();

	}

	private class SingleStatusCounterTest
	{
		private JDFNode n;
		private StatusCounter sc;
		private final String deviceID;
		private final JDFExposedMedia xpMedia;
		private final JDFComponent outComp;
		private final JDFEmployee employee;

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
		SingleStatusCounterTest()
		{
			n = creatXMDoc().getJDFRoot();
			xpMedia = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", null, null, 0);
			outComp = (JDFComponent) n.getMatchingResource(ElementName.COMPONENT, null, null, 0);
			final JDFResourceLink rlComp = n.getLink(outComp, null);
			rlComp.setAmount(100, null);
			sc = new StatusCounter(n, null, null);
			deviceID = "Status-counter-TestDevice";
			sc.setDeviceID(deviceID);
			sc.setFirstRefID(outComp.getID());
			sc.addPhase(outComp.getID(), 200, 0, true);
			employee = (JDFEmployee) new JDFDoc("Employee").getRoot();
			employee.setPersonalID("P1");
		}

		/**
		 *
		 */
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
		void testAddPhaseXM()
		{
			final boolean bChanged = sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
			assertTrue(bChanged);
			final JDFDoc docJMF = sc.getDocJMFPhaseTime();
			final JDFResponse sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
			final JDFDeviceInfo deviceInfo = sig.getDeviceInfo(0);
			assertEquals(deviceInfo.getDeviceID(), deviceID);
			final boolean bChanged2 = sc.setPhase(null, null, null, null);
			assertFalse(bChanged2);

			for (final JDFAttributeMap map : xpMedia.getPartMapVector(false))
			{
				final String s = sc.toString();
				sc.addPhase(ElementName.EXPOSEDMEDIA, 1, 0, false, map);
			}

			final JDFDoc d = sc.getDocJMFResource();

		}

		/**
		 *
		 */
		void testShortString()
		{
			assertNotNull(sc.shortString());
			final String string = sc.toString();
			assertNotNull(string);
		}

		/**
		 *
		 */
		void testWasteAmount()
		{
			final VJDFAttributeMap singleMap = new VJDFAttributeMap();
			singleMap.add(xpMedia.getPartMapVector(false).elementAt(0));

			final MISCPGoldenTicket gt = new MISCPGoldenTicket(2, EnumVersion.Version_1_7, 2, 2, false, singleMap);
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
		 */
		public JDFDoc testAddPhase()
		{
			boolean bChanged = sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
			assertTrue(bChanged);
			JDFDoc docJMF = sc.getDocJMFPhaseTime();
			JDFResponse sig = (JDFResponse) docJMF.getJMFRoot().getMessageElement(EnumFamily.Response, EnumType.Status, 0);
			JDFJobPhase jp = sig.getDeviceInfo(0).getJobPhase(0);
			assertEquals(jp.getAmount(), 200, 0);
			final String resID = outComp.getID();
			sc.addPhase(resID, 0, 100, true);
			sc.setTrackWaste(resID, true);
			final JDFResourceLink rlXM = n.getLink(outComp, null);
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

			sc.setSplitJobPhase(true);
			final List<JDFJMF> l = sc.getJMFStatusList();
			assertEquals(2, l.size());

			sig = (JDFResponse) l.get(0).getMessageElement(EnumFamily.Response, EnumType.Status, 0);
			jp = sig.getDeviceInfo(0).getJobPhase(0);
			assertEquals(jp.getAmount(), 200, 0);
			assertEquals(jp.getWaste(), 400, 0);
			assertTrue(jp.hasAttribute(AttributeName.PHASEAMOUNT));

			sig = (JDFResponse) l.get(1).getMessageElement(EnumFamily.Response, EnumType.Status, 0);
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
		void testEvent()
		{
			assertNull(sc.getDocJMFNotification(false));
			sc.setEvent("id", "value", "blah blah");
			JDFDoc d = sc.getDocJMFNotification(false);
			JDFDoc d2 = sc.getDocJMFNotification(false);
			assertTrue(d.getJMFRoot().getSignal().getNotification().isEqual(d2.getJMFRoot().getSignal().getNotification()));
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
		void testMemLeak2()
		{
			sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
			final VElement v = new VElement();
			for (int i = 0; i < 222; i++)
			{
				if (i % 1000 == 0)
				{
					log.info(i + " " + getCurrentMem() + " " + (getCurrentMem() / (i + 1)));
				}
				v.add(sc.getDocJMFPhaseTime().getRoot());
			}
		}

		/**
		 * test for memory leaks in clone
		 */
		void testMemLeak3()
		{
			sc.setPhase(EnumNodeStatus.InProgress, "i", EnumDeviceStatus.Running, "r");
			final VJDFAttributeMap v = new VJDFAttributeMap();
			for (int i = 0; i < 222; i++)
			{
				if (i % 1000 == 0)
				{
					log.info(i + " " + getCurrentMem() + " " + (getCurrentMem() / (i + 1)));
				}
				v.add(sc.getDocJMFPhaseTime().getRoot().getXPathValueMap());
			}
		}

		/**
		 *
		 */
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
			sc.addPhase(outComp.getID(), 0, 100, true);
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

	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}

	/**
	 * @return the doc
	 */
	public static JDFDoc getJMF()
	{
		return new StatusCounterTest().new SingleStatusCounterTest().testAddPhase();
	}

}
