/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumWorkType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.process.JDFMISDetails;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.ThreadUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi
 *
 *         Test of the Status JMF
 */
class JDFJobPhaseTest extends JDFTestCaseBase
{
	private JDFDeviceInfo di;
	private JDFJMF jmf;

	/**
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp()
	{
		KElement.setLongID(false);
		jmf = JDFJMF.createJMF(EnumFamily.Signal, EnumType.Status);
		di = jmf.getSignal(0).appendDeviceInfo();

	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testJobPhaseFromPhaseTime()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFAuditPool ap = d.getJDFRoot().getCreateAuditPool();
		final JDFPhaseTime pt = ap.setPhase(EnumNodeStatus.InProgress, "dummy", null, null);
		JDFJobPhase jp = di.createJobPhaseFromPhaseTime(pt);
		assertFalse(pt.hasChildElement(ElementName.MISDETAILS, null));
		final JDFMISDetails misDetails = pt.appendMISDetails();
		misDetails.setWorkTypeDetails("FooBar");
		misDetails.setWorkType(EnumWorkType.Alteration);
		jp = di.createJobPhaseFromPhaseTime(pt);
		assertEquals(pt.getMISDetails().getWorkType(), jp.getMISDetails().getWorkType());
		assertTrue(jp.hasAttribute(AttributeName.PHASESTARTTIME));
	}

	/**
	 *
	 */
	@Test
	void testApplyNode()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setJobID("j1");
		n.setJobPartID("p2");
		final JDFJobPhase jp = di.appendJobPhase();
		jp.applyNode(null);
		assertNull(jp.getJobID());
		jp.applyNode(n);
		assertEquals(jp.getJobID(), "j1");
		assertEquals(jp.getActivation().getName(), n.getActivation(true).getName());

	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	void testGetPhaseAmount()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFAuditPool ap = d.getJDFRoot().getCreateAuditPool();
		final JDFPhaseTime pt = ap.setPhase(EnumNodeStatus.InProgress, "dummy", null, null);
		final JDFJobPhase jp = di.createJobPhaseFromPhaseTime(pt);
		jp.setAmount(42);
		assertEquals(jp.getPhaseAmount(), 42.0, 0.0);

	}

	/**
	 *
	 */
	@Test
	void testGetPhaseWaste()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFAuditPool ap = d.getJDFRoot().getCreateAuditPool();
		final JDFPhaseTime pt = ap.setPhase(EnumNodeStatus.InProgress, "dummy", null, null);
		final JDFJobPhase jp = di.createJobPhaseFromPhaseTime(pt);
		jp.setWaste(42);
		assertEquals(jp.getPhaseWaste(), 42.0, 0.0);
	}

	/**
	 *
	 */
	@Test
	void testGetStatusQuParams()
	{
		final JDFStatusQuParams sqp = jmf.getSignal(0).appendStatusQuParams();
		JDFJobPhase jp = di.appendJobPhase();
		assertEquals(sqp, jp.getStatusQuParams());

		jmf = JDFJMF.createJMF(EnumFamily.Response, EnumType.PipePush);
		jp = jmf.getResponse(0).appendJobPhase();
		assertNull(jp.getStatusQuParams());

		jmf = JDFJMF.createJMF(EnumFamily.Response, EnumType.ShutDown);
		jp = jmf.getResponse(0).appendDeviceInfo().appendJobPhase();
		assertNull(jp.getStatusQuParams());

		jmf = JDFJMF.createJMF(EnumFamily.Response, EnumType.Status);
		jp = jmf.getResponse(0).appendDeviceInfo().appendJobPhase();
		assertNull(jp.getStatusQuParams());

	}

	/**
	 *
	 */
	@Test
	void testGetQueueEntryID()
	{
		final JDFStatusQuParams sqp = jmf.getSignal(0).appendStatusQuParams();
		sqp.setQueueEntryID("qeID1");
		JDFJobPhase jp = di.appendJobPhase();
		assertEquals("qeID1", jp.getQueueEntryID());

		jmf = JDFJMF.createJMF(EnumFamily.Response, EnumType.Status);
		jp = jmf.getResponse(0).appendDeviceInfo().appendJobPhase();
		assertNull(jp.getStatusQuParams());
	}

	/**
	 *
	 */
	@Test
	void testGetSpeed()
	{

		di.setSpeed(3);
		final JDFJobPhase jp = di.appendJobPhase();
		assertEquals(jp.getSpeed(), 3.0, 0.001);
		jp.setSpeed(42);
		assertEquals(jp.getSpeed(), 42.0, 0.001);
	}

	/**
	 *
	 */
	@Test
	void testSetPercentComplete()
	{

		di.setSpeed(3);
		final JDFJobPhase jp = di.appendJobPhase();
		jp.setPercentCompleted(1234);

		assertEquals(100, jp.getPercentCompleted());
		jp.setPercentCompleted(12);

		assertEquals(12, jp.getPercentCompleted(), 0);
		assertThrows(IllegalArgumentException.class, () -> jp.setPercentCompleted(-1));
	}

	/**
	 *
	 */
	@Test
	void testGetAmountDifference()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFAuditPool ap = d.getJDFRoot().getCreateAuditPool();
		final JDFPhaseTime pt = ap.setPhase(EnumNodeStatus.InProgress, "dummy", null, null);
		final JDFJobPhase jp = di.createJobPhaseFromPhaseTime(pt);
		jp.setAmount(42);
		assertEquals(jp.getAmountDifference(null), 42.0, 0.0);
		final JDFJobPhase jp2 = (JDFJobPhase) di.copyElement(jp, null);
		jp2.setAmount(62);
		assertEquals(jp2.getAmountDifference(jp), 20.0, 0.0);

	}

	/**
	 *
	 */
	@Test
	void testGetWasteDifference()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFAuditPool ap = d.getJDFRoot().getCreateAuditPool();
		final JDFPhaseTime pt = ap.setPhase(EnumNodeStatus.InProgress, "dummy", null, null);
		final JDFJobPhase jp = di.createJobPhaseFromPhaseTime(pt);
		jp.setPhaseWaste(42);
		assertEquals(jp.getWasteDifference(null), 42.0, 0.0);
		final JDFJobPhase jp2 = (JDFJobPhase) di.copyElement(jp, null);
		jp2.setPhaseWaste(62);
		assertEquals(jp2.getWasteDifference(jp), 20.0, 0.0);

	}

	/**
	 *
	 */
	@Test
	void testMergeLastPhase()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFAuditPool ap = d.getJDFRoot().getCreateAuditPool();
		final JDFPhaseTime pt = ap.setPhase(EnumNodeStatus.InProgress, "dummy", null, null);
		final JDFJobPhase jp = di.createJobPhaseFromPhaseTime(pt);
		jp.setPhaseAmount(200);
		jp.setAmount(200);
		jp.setPhaseWaste(100);
		final JDFDate d1 = jp.getPhaseStartTime();
		final JDFJobPhase jp2 = (JDFJobPhase) di.copyElement(jp, null);
		ThreadUtil.sleep(1000);
		jp2.setPhaseStartTime(new JDFDate());
		jp2.setPhaseAmount(300);
		jp2.setPhaseWaste(30);
		jp2.setAmount(500);
		assertTrue(jp2.mergeLastPhase(jp));
		assertEquals(jp2.getPhaseStartTime(), d1);
		assertEquals(jp2.getPhaseWaste(), 130., 0.);
		assertEquals(jp2.getPhaseAmount(), 500., 0.);
		assertEquals(jp2.getAmount(), 500., 0.);
	}
}