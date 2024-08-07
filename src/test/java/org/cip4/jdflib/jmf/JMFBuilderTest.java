/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2022 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoStatusQuParams.EnumDeviceDetails;
import org.cip4.jdflib.auto.JDFAutoStatusQuParams.EnumJobDetails;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.JDFToXJDF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * lets generate some jmf messages
 *
 * @author rainer prosi
 * @date Sept 7, 2010
 */
class JMFBuilderTest extends JDFTestCaseBase
{
	private JMFBuilder b;

	/**
	 *
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		b = new JMFBuilder();
	}

	/**
	 *
	 * test milestone
	 */
	@Test
	void testBuildMilestone()
	{
		final JDFJMF jmf = b.buildMilestone("PrepressCompleted", "jobID");
		roundTrip(jmf, EnumValidationLevel.Complete, sm_dirTestDataTemp + "milestone");
	}

	/**
	 *
	 * test milestone
	 */
	@Test
	void testBuildSubmitQueueEntry()
	{
		final JDFJMF jmf = b.buildSubmitQueueEntry("retURL", "xxx");
		final JDFQueueSubmissionParams queueSubmissionParams = jmf.getCommand(0).getQueueSubmissionParams(0);
		assertEquals(queueSubmissionParams.getURL(), "xxx");
		assertEquals(queueSubmissionParams.getReturnJMF(), "retURL");
		roundTrip(jmf, EnumValidationLevel.Complete, sm_dirTestDataTemp + "submitQueueEntry");
	}

	/**
	 *
	 * @param jmf
	 * @param level
	 * @param dir
	 */
	private void roundTrip(final JDFJMF jmf, final EnumValidationLevel level, final String dir)
	{
		assertTrue(jmf.isValid(level));
		jmf.write2File(dir + ".jmf");
		final JDFToXJDF xc = new XJDF20();
		xc.setTypeSafeMessage(true);
		final KElement e = xc.convert(jmf);
		assertNotNull(e);
		e.write2File(dir + ".xjmf");
		final XJDFToJDFConverter xc2 = new XJDFToJDFConverter(null);
		final JDFDoc doc = xc2.convert(e);
		doc.write2File(dir + ".xjmf.jmf", 2, false);
		assertTrue(doc.getJMFRoot().isValid(level));

	}

	/**
	 *
	 * test resource signal
	 */
	@Test
	void testBuildResourceSignal()
	{
		final JDFJMF jmf = b.buildResourceSignal(true, null);
		assertEquals(jmf.getSignal(0).getType(), "Resource");
		roundTrip(jmf, EnumValidationLevel.Complete, sm_dirTestDataTemp + "resourceSignal");
	}

	/**
	 *
	 * test resource signal
	 */
	@Test
	void testBuildResourceSignalWastePaper()
	{
		final JDFNode n = JDFNode.createRoot();
		n.setJobID("j1");
		n.setJobPartID("jp1");
		n.addTypes(EnumType.ConventionalPrinting);
		final JDFResourceLink rl = n.getLink(n.addResource(ElementName.MEDIA, EnumUsage.Input), null);
		final JDFAttributeMap map = new JDFAttributeMap();
		map.put(AttributeName.SHEETNAME, "S1");
		map.put(AttributeName.SIGNATURENAME, "Si1");
		map.put(AttributeName.CONDITION, "Good");
		rl.setActualAmount(1000, map);
		map.put(AttributeName.CONDITION, "Waste");
		rl.setActualAmount(33, map);
		final JDFJMF jmf = b.buildResourceSignal(true, rl);
		assertEquals(jmf.getSignal(0).getType(), "Resource");
		roundTrip(jmf, EnumValidationLevel.Complete, sm_dirTestDataTemp + "resourceSignalWaste");
	}

	/**
	 *
	 * test ink resource signal
	 */
	@Test
	void testBuildResourceSignalInkLot()
	{
		final JDFJMF jmf = b.buildResourceSignal(false, null);

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
		assertEquals(signal.getType(), "Resource");
		jmf.write2File(sm_dirTestDataTemp + "resourceInk.jmf");
	}

	/**
	 *
	 * test milestone
	 */
	@Test
	void testBuildStatusSignal()
	{
		final JDFJMF jmf = b.buildStatusSignal(EnumDeviceDetails.Full, EnumJobDetails.Full);
		assertEquals(jmf.getSignal(0).getType(), "Status");
		roundTrip(jmf, EnumValidationLevel.Incomplete, sm_dirTestDataTemp + "statusSignal");
	}

	/**
	 *
	 * test milestone
	 */
	@Test
	void testBuildNewJDFCommand()
	{
		final JDFJMF jmf = b.buildNewJDFCommand();
		assertTrue(jmf.isValid(EnumValidationLevel.Complete));
		final JDFToXJDF xc = new XJDF20();
		xc.setTypeSafeMessage(true);
		final KElement e = xc.convert(jmf);
		assertNull(e);

	}

	/**
	 *
	 * test status subscription
	 */
	@Test
	void testBuildStatusSubscription()
	{
		final JDFJMF jmf = b.buildStatusSubscription("signalurl", 30, -1, null);
		roundTrip(jmf, EnumValidationLevel.Complete, sm_dirTestDataTemp + "SubscriptionStatus");
	}

	/**
	 *
	 * test status subscription
	 */
	@Test
	void testBuildAbort()
	{
		final JDFJMF jmf = b.buildAbortQueueEntry("q1");
		roundTrip(jmf, EnumValidationLevel.Complete, sm_dirTestDataTemp + "Abort");
	}

	/**
	 *
	 * test status subscription
	 */
	@Test
	void testBuildHold()
	{
		final JDFJMF jmf = b.buildHoldQueueEntry("q1");
		roundTrip(jmf, EnumValidationLevel.Complete, sm_dirTestDataTemp + "Hold");
	}

	/**
	 *
	 * test resource subscription
	 */
	@Test
	void testBuildResourceSubscription()
	{
		final JDFJMF jmf = b.buildResourceSubscription("signalurl", 30, -1, null);
		roundTrip(jmf, EnumValidationLevel.Complete, sm_dirTestDataTemp + "SubscriptionResource");
	}

	/**
	 *
	 * test notification subscription
	 */
	@Test
	void testBuildNotificationSubscription()
	{
		final JDFJMF jmf = b.buildNotificationSubscription("signalurl");
		roundTrip(jmf, EnumValidationLevel.Complete, sm_dirTestDataTemp + "SubscriptionNotification");
	}

	/**
	 *
	 * test queue status subscription
	 */
	@Test
	void testBuildQueueStatusSubscription()
	{
		final JDFJMF jmf = b.buildQueueStatusSubscription("signalurl");
		roundTrip(jmf, EnumValidationLevel.Complete, sm_dirTestDataTemp + "SubscriptionQueueStatus");
	}

	/**
	 *
	 * test milestone
	 */
	@Test
	void testSenderID()
	{
		JDFJMF jmf = b.buildMilestone("PrepressCompleted", "jobID");
		assertEquals(jmf.getSenderID(), JDFJMF.getTheSenderID());
		b.setSenderID("fooBar");
		jmf = b.buildMilestone("PrepressCompleted", "jobID");
		assertEquals(jmf.getSenderID(), "fooBar");
		b.setSenderID(null);
		jmf = b.buildMilestone("PrepressCompleted", "jobID");
		assertEquals(jmf.getSenderID(), "");
	}

	/**
	 *
	 * test milestone
	 */
	@Test
	void testSenderIDMessage()
	{
		JDFJMF jmf = b.buildMilestone("PrepressCompleted", "jobID");
		assertEquals(jmf.getSenderID(), JDFJMF.getTheSenderID());
		b.setSenderID("fooBar");
		jmf = b.buildMilestone("PrepressCompleted", "jobID");
		assertEquals(jmf.getMessageElement(null, null, 0).getSenderID(), "fooBar");
		b.setSenderID(null);
		jmf = b.buildMilestone("PrepressCompleted", "jobID");
		assertEquals(jmf.getMessageElement(null, null, 0).getSenderID(), "");
	}

}
