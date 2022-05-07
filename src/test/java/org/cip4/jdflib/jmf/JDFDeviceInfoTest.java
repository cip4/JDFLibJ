/*
 * The CIP4 Software License, Version 1.0
 *
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

package org.cip4.jdflib.jmf;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumDeviceOperationMode;
import org.cip4.jdflib.auto.JDFAutoStatusQuParams.EnumDeviceDetails;
import org.cip4.jdflib.auto.JDFAutoStatusQuParams.EnumJobDetails;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFEvent;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.ThreadUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi
 *
 *         Test of the Status JMF
 */
public class JDFDeviceInfoTest extends JDFTestCaseBase
{
	private JDFDeviceInfo di;

	/**
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp()
	{
		KElement.setLongID(false);
		final JDFDoc doc = new JDFDoc(ElementName.DEVICEINFO);
		di = (JDFDeviceInfo) doc.getRoot();

	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetDeviceID()
	{
		final JDFJMF jmf = (JDFJMF) new JDFDoc("JMF").getRoot();
		jmf.setSenderID("S1");
		di = jmf.appendSignal(EnumType.Status).appendDeviceInfo();
		Assertions.assertEquals(jmf.getSenderID(), di.getDeviceID());
		di.appendDevice().setDeviceID("dd");
		Assertions.assertEquals(di.getDeviceID(), "dd");
		di.setDeviceID("da");
		Assertions.assertEquals(di.getDeviceID(), "da");
	}

	/**
	 *
	 */
	@Test
	public void testGetDescriptiveName()
	{
		final JDFJMF jmf = (JDFJMF) new JDFDoc("JMF").getRoot();
		jmf.setSenderID("S1");
		di = jmf.appendSignal(EnumType.Status).appendDeviceInfo();
		di.appendDevice().setDescriptiveName("dd");
		Assertions.assertEquals(di.getDescriptiveName(), "dd");
		di.setDescriptiveName("da");
		Assertions.assertEquals(di.getDescriptiveName(), "da");
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testNullDeviceStatus()
	{
		di.setDeviceStatus(null);
		Assertions.assertNotNull(di, "got here!");
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testMergeLastPhase()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFAuditPool ap = d.getJDFRoot().getCreateAuditPool();
		final JDFPhaseTime pt = ap.setPhase(EnumNodeStatus.InProgress, "dummy", null, null);
		final JDFJobPhase jp = di.createJobPhaseFromPhaseTime(pt);
		jp.setPhaseAmount(200);
		jp.setAmount(200);
		jp.setPhaseWaste(100);
		final JDFDate d1 = jp.getPhaseStartTime();
		final JDFDoc doc = new JDFDoc(ElementName.DEVICEINFO);
		final JDFDeviceInfo di2 = (JDFDeviceInfo) doc.getRoot();

		final JDFJobPhase jp2 = (JDFJobPhase) di2.copyElement(jp, null);
		final JDFDate dat = new JDFDate();
		dat.addOffset(20, 0, 0, 0);
		jp2.setPhaseStartTime(dat);
		ThreadUtil.sleep(1000);
		jp2.setPhaseAmount(300);
		jp2.setPhaseWaste(30);
		jp2.setAmount(500);
		Assertions.assertTrue(di2.mergeLastPhase(di));
		Assertions.assertEquals(jp2.getPhaseStartTime(), d1);
		Assertions.assertEquals(jp2.getPhaseWaste(), 130., 0.);
		Assertions.assertEquals(jp2.getPhaseAmount(), 500., 0.);
		Assertions.assertEquals(jp2.getAmount(), 500., 0.);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testIsSamePhaseIdle()
	{

		final JDFDeviceInfo di1 = (JDFDeviceInfo) new JDFDoc(ElementName.DEVICEINFO).getRoot();
		final JDFDeviceInfo di2 = (JDFDeviceInfo) new JDFDoc(ElementName.DEVICEINFO).getRoot();

		Assertions.assertTrue(di1.isSamePhase(di2, false));
		final JDFDate date = new JDFDate();
		di1.setIdleStartTime(date);
		di2.setIdleStartTime(date);
		Assertions.assertTrue(di1.isSamePhase(di2, false));
		di1.setDeviceStatus(EnumDeviceStatus.Idle);
		di2.setDeviceStatus(EnumDeviceStatus.Idle);
		Assertions.assertTrue(di1.isSamePhase(di2, false));
		di1.setDeviceOperationMode(EnumDeviceOperationMode.Productive);
		di2.setDeviceOperationMode(EnumDeviceOperationMode.Productive);
		Assertions.assertTrue(di1.isSamePhase(di2, false));
		final JDFDevice dev = di1.appendDevice();
		dev.setDeviceID("d1");
		dev.setDeviceType("foo");
		di2.copyElement(dev, null);
		Assertions.assertTrue(di1.isSamePhase(di2, false));
	}

	/**
	 *
	 */
	@Test
	public void testIsSamePhase()
	{

		final JDFDeviceInfo di1 = (JDFDeviceInfo) new JDFDoc(ElementName.DEVICEINFO).getRoot();
		final JDFDeviceInfo di2 = (JDFDeviceInfo) new JDFDoc(ElementName.DEVICEINFO).getRoot();

		Assertions.assertTrue(di1.isSamePhase(di2, false));
		di1.appendEmployee().setPersonalID("p1");
		Assertions.assertFalse(di1.isSamePhase(di2, false));
		di2.appendEmployee().setPersonalID("p1");
		Assertions.assertTrue(di1.isSamePhase(di2, false));

		di1.appendJobPhase();
		Assertions.assertFalse(di1.isSamePhase(di2, false));
		di2.appendJobPhase();
		Assertions.assertTrue(di1.isSamePhase(di2, false));

		di1.appendEmployee().setPersonalID("p2");
		Assertions.assertFalse(di1.isSamePhase(di2, false));
		di2.appendEmployee().setPersonalID("p3");
		Assertions.assertFalse(di1.isSamePhase(di2, false));
	}

	/**
	 *
	 */
	@Test
	public void testEmployeeDeprecated15()
	{
		final JDFJMF jmf = new JMFBuilder().buildStatusSignal(EnumDeviceDetails.Full, EnumJobDetails.Brief);
		final JDFDeviceInfo di2 = jmf.getSignal(0).getCreateDeviceInfo(0);
		di2.appendEmployee().setPersonalID("p");
		Assertions.assertTrue(di2.getDeprecatedElements(0).isEmpty());

	}

	/**
	*
	*/
	@Test
	public void testEvent()
	{
		final JDFJMF jmf = new JMFBuilder().buildStatusSignal(EnumDeviceDetails.Full, EnumJobDetails.Brief);
		final JDFDeviceInfo di2 = jmf.getSignal(0).getCreateDeviceInfo(0);
		final JDFEvent ev = di2.appendEvent();
		di2.setDeviceStatus(EnumDeviceStatus.Cleanup);
		di2.removeChild(ElementName.JOBPHASE, null, 0);
		ev.setEventID("e1");
		Assertions.assertTrue(di2.isValid(EnumValidationLevel.Complete));
	}

	/**
	*
	*/
	@Test
	public void testEventID()
	{
		final JDFJMF jmf = new JMFBuilder().buildStatusSignal(EnumDeviceDetails.Full, EnumJobDetails.Brief);
		final JDFDeviceInfo di2 = jmf.getSignal(0).getCreateDeviceInfo(0);
		final JDFEvent ev = di2.appendEvent("e3");
		Assertions.assertEquals("e3", ev.getEventID());
	}

	/**
	 *
	 */
	@Test
	public void testGetIdleStartTime()
	{
		final JDFDeviceInfo di1 = (JDFDeviceInfo) new JDFDoc(ElementName.DEVICEINFO).getRoot();
		Assertions.assertNull(di1.getIdleStartTime());
		di1.setIdleStartTime(null);
		Assertions.assertNotNull(di1.getIdleStartTime());
	}
}