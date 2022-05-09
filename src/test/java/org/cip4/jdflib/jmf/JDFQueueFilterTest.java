/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoQueue.EnumQueueStatus;
import org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus;
import org.cip4.jdflib.auto.JDFAutoQueueFilter.EnumQueueEntryDetails;
import org.cip4.jdflib.auto.JDFAutoQueueFilter.EnumUpdateGranularity;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode.EnumActivation;
import org.cip4.jdflib.node.NodeIdentifier;
import org.cip4.jdflib.util.CPUTimer;
import org.cip4.jdflib.util.JDFDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         < July 4, 2009
 */
public class JDFQueueFilterTest extends JDFTestCaseBase
{
	JDFQueue theQueue;
	JDFJMF theJMF;
	JDFQueueFilter filter;

	/**
	 * @throws Exception
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		JDFDoc d = new JDFDoc(ElementName.QUEUE);
		theQueue = (JDFQueue) d.getRoot();
		d = new JDFDoc(ElementName.JMF);
		theJMF = d.getJMFRoot();
		filter = theJMF.appendCommand(EnumType.QueueStatus).appendQueueFilter();
		super.setUp();
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testMatchDiff_RemoveNonMatching() throws Exception
	{
		JDFDoc d = new JDFDoc(ElementName.QUEUE);
		final JDFQueue newQueue = (JDFQueue) d.getRoot();
		for (int i = 5; i < 10; i++)
		{
			final JDFQueueEntry qe = newQueue.appendQueueEntry();
			qe.setQueueEntryID("q" + i);
			qe.setQueueEntryStatus(EnumQueueEntryStatus.Running);
			qe.appendJobPhase().setStatus(JDFElement.EnumNodeStatus.InProgress);
		}
		System.out.println("NewQueue:\n" + newQueue.toXML());

		d = new JDFDoc(ElementName.QUEUE);
		final JDFQueue oldQueue = (JDFQueue) d.getRoot();
		for (int i = 0; i < 10; i++)
		{
			final JDFQueueEntry qe = oldQueue.appendQueueEntry();
			qe.setQueueEntryID("q" + i);
			qe.setQueueEntryStatus(EnumQueueEntryStatus.Running);
			qe.appendJobPhase().setStatus(JDFElement.EnumNodeStatus.InProgress);
		}
		System.out.println("OldQueue:\n" + oldQueue.toXML());

		d = new JDFDoc(ElementName.JMF);
		final JDFResponse resp = ((JDFJMF) d.getRoot()).appendResponse();
		resp.setType(EnumType.AbortQueueEntry);

		d = new JDFDoc(ElementName.JMF);
		final JDFJMF theJMF = d.getJMFRoot();
		final JDFQueueFilter filter = theJMF.appendCommand(EnumType.AbortQueueEntry).appendQueueFilter();
		filter.setUpdateGranularity(EnumUpdateGranularity.ChangesOnly);
		final Vector<EnumQueueEntryStatus> v = new Vector<>();
		v.add(EnumQueueEntryStatus.Aborted);
		filter.setStatusList(v);
		filter.setQueueEntryDetails(EnumQueueEntryDetails.JobPhase);
		System.out.println("Filter:\n" + filter.toXML());

		filter.copy(newQueue, oldQueue, resp);
		System.out.println("Result:\n" + resp.toXML());
		Assertions.assertEquals(newQueue.getQueueEntryVector().size(), 5);
		Assertions.assertNotNull(resp.getQueue(0));
	}

	/**
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testMatchDiff() throws Exception
	{
		for (int i = 0; i < 100; i++)
		{
			final JDFQueueEntry qe = theQueue.appendQueueEntry();
			qe.setQueueEntryID("q" + i);
		}
		final JDFQueue copy = (JDFQueue) new JDFDoc("JDF").getRoot().copyElement(theQueue, null);
		filter.setMaxEntries(10);
		filter.setUpdateGranularity(EnumUpdateGranularity.ChangesOnly);
		JDFQueue copyMatch = (JDFQueue) new JDFDoc("JDF").getRoot().copyElement(theQueue, null);
		JDFQueue matchedQueue = filter.apply(copyMatch, copy);
		Assertions.assertNull(matchedQueue, "identical queue should cancel");
		theQueue.setQueueStatus(EnumQueueStatus.Held);
		copyMatch = (JDFQueue) new JDFDoc("JDF").getRoot().copyElement(theQueue, null);
		matchedQueue = filter.apply(copyMatch, copy);
		Assertions.assertEquals(EnumQueueStatus.Held, matchedQueue.getQueueStatus());
		Assertions.assertNull(matchedQueue.getQueueEntry(0));
		final JDFQueueEntry addedQE = theQueue.appendQueueEntry();
		addedQE.setQueueEntryID("qe_test");
		copyMatch = (JDFQueue) new JDFDoc("JDF").getRoot().copyElement(theQueue, null);
		matchedQueue = filter.apply(copyMatch, copy);
		Assertions.assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryID(), "qe_test");
		Assertions.assertNull(matchedQueue.getQueueEntry(1));
		addedQE.deleteNode();
		theQueue.getQueueEntry("q1").setQueueEntryStatus(EnumQueueEntryStatus.Aborted);
		copyMatch = (JDFQueue) new JDFDoc("JDF").getRoot().copyElement(theQueue, null);
		matchedQueue = filter.apply(copyMatch, copy);
		Assertions.assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryID(), "q1");
		Assertions.assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryStatus(), EnumQueueEntryStatus.Aborted);
		Assertions.assertNull(matchedQueue.getQueueEntry(1));

		theQueue.getQueueEntry("q1").deleteNode();
		copyMatch = (JDFQueue) new JDFDoc("JDF").getRoot().copyElement(theQueue, null);
		matchedQueue = filter.apply(copyMatch, copy);
		Assertions.assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryID(), "q1");
		Assertions.assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryStatus(), EnumQueueEntryStatus.Removed);
		Assertions.assertNull(matchedQueue.getQueueEntry(1));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testRemove() throws Exception
	{
		for (int i = 0; i < 10; i++)
		{
			final JDFQueueEntry qe = theQueue.appendQueueEntry();
			qe.setQueueEntryID("q" + i);
		}
		final JDFQueue copy = (JDFQueue) new JDFDoc("JDF").getRoot().copyElement(theQueue, null);
		filter.setMaxEntries(10);
		filter.setUpdateGranularity(EnumUpdateGranularity.ChangesOnly);

		JDFQueue matchedQueue = filter.copy(theQueue, copy, null);
		Assertions.assertNull(matchedQueue, "identical queue should cancel");
		JDFQueueEntry qe = copy.appendQueueEntry();
		qe.setQueueEntryID("q11");
		matchedQueue = filter.copy(theQueue, copy, null);
		Assertions.assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryStatus(), EnumQueueEntryStatus.Removed, "got removed entry");
		qe = theQueue.appendQueueEntry();
		qe.setQueueEntryID("q10");
		qe = theQueue.appendQueueEntry();
		qe.setQueueEntryID("q11");
		matchedQueue = filter.copy(theQueue, copy, null);
		Assertions.assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryID(), "q10", "got new entry");
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetStatusList() throws Exception
	{
		final Vector<EnumQueueEntryStatus> vs = new Vector<>();
		vs.add(EnumQueueEntryStatus.Completed);
		vs.add(EnumQueueEntryStatus.Aborted);
		filter.setStatusList(vs);

		final Vector<EnumQueueEntryStatus> statusList = filter.getStatusList();
		Assertions.assertTrue(statusList.contains(EnumQueueEntryStatus.Completed));
		Assertions.assertTrue(statusList.contains(EnumQueueEntryStatus.Aborted));
		Assertions.assertFalse(statusList.contains(EnumQueueEntryStatus.Running));
	}

	/**
	 *
	 */
	@Test
	public void testGetIdentifier()
	{
		final NodeIdentifier ni = new NodeIdentifier("j1", "p1", null);
		filter.setIdentifier(ni);
		Assertions.assertEquals(filter.getIdentifier(), ni);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	public void testMatchesGeneralID() throws Exception
	{
		final JDFQueueEntry qe1 = theQueue.appendQueueEntry();
		qe1.setGeneralID("ProductThing", "a1");
		final JDFQueueEntry qe2 = theQueue.appendQueueEntry();
		qe2.setGeneralID("ProductThing", "a2");
		filter.setGeneralID("ProductThing", "a1");
		Assertions.assertTrue(filter.matches(qe1));
		Assertions.assertFalse(filter.matches(qe2));
		filter.appendGeneralID("ProductThing", "a2");
		Assertions.assertTrue(filter.matches(qe1));
		Assertions.assertTrue(filter.matches(qe2));
		filter.setGeneralID("ProductThing", "a0");
		Assertions.assertFalse(filter.matches(qe1));
		Assertions.assertFalse(filter.matches(qe2));
		filter.setGeneralID("ProductThing", "a(.)?");
		Assertions.assertTrue(filter.matches(qe1));
		Assertions.assertTrue(filter.matches(qe2));

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testMatches() throws Exception
	{
		final JDFQueueEntry qe = theQueue.appendQueueEntry();
		Assertions.assertTrue(filter.matches(qe), "both empty ");
		qe.setDeviceID("d1");
		qe.setQueueEntryID("qe1");

		filter.appendDevice("qe1");
		Assertions.assertFalse(filter.matches(qe), "no device ");
		filter.appendDevice("d1");
		Assertions.assertTrue(filter.matches(qe), " device ");

		filter.appendQueueEntryDef("qe2");
		Assertions.assertFalse(filter.matches(qe), "no qentryID ");
		filter.appendQueueEntryDef("qe1");
		Assertions.assertTrue(filter.matches(qe), "qentryID ");
		filter.setQueueEntryDetails(EnumQueueEntryDetails.None);
		Assertions.assertFalse(filter.matches(qe), "details=none never matches ");

		filter.setQueueEntryDetails(EnumQueueEntryDetails.Brief); // undo none for additional tests

		qe.setJobID("jID");
		Assertions.assertTrue(filter.matches(qe), "jobID ");
		filter.setJobID("jID");
		Assertions.assertTrue(filter.matches(qe), "jobID ");
		filter.setJobID("j(.)*");
		Assertions.assertTrue(filter.matches(qe), "jobID ");
		filter.setJobID("jID2");
		Assertions.assertFalse(filter.matches(qe), "jobID ");
		filter.setJobID("jID");

		qe.setJobPartID("part");
		Assertions.assertTrue(filter.matches(qe), "jobPartID ");
		filter.setJobPartID("part");
		Assertions.assertTrue(filter.matches(qe), "jobID ");
		filter.setJobPartID("par(.)*");
		Assertions.assertTrue(filter.matches(qe), "jobID ");
		filter.setJobPartID("part2");
		Assertions.assertFalse(filter.matches(qe), "jobID ");
		filter.setJobPartID("part");

		final JDFDate d = new JDFDate();
		qe.setSubmissionTime(d);
		final JDFDate d2 = d.clone();
		d2.addOffset(200, 0, 0, 0);
		filter.setOlderThan(d2);
		Assertions.assertTrue(filter.matches(qe), "older ");
		filter.setOlderThan(null);
		filter.setNewerThan(d2);
		Assertions.assertFalse(filter.matches(qe), "newer ");
		d2.addOffset(0, -10, 0, 0);
		filter.setNewerThan(d2);
		Assertions.assertTrue(filter.matches(qe), "newer ");
		filter.setOlderThan(d2);
		filter.setNewerThan(null);
		Assertions.assertFalse(filter.matches(qe), "older ");
	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testMatch() throws Exception
	{
		for (int i = 0; i < 100; i++)
		{
			final JDFQueueEntry qe = theQueue.appendQueueEntry();
			qe.setQueueEntryID("q" + i);
		}

		filter.setMaxEntries(10);
		filter.apply(theQueue, null);
		Assertions.assertEquals(10, theQueue.numEntries(null));
		filter.setQueueEntryDetails(EnumQueueEntryDetails.None);
		filter.apply(theQueue, null);
		Assertions.assertEquals(0, theQueue.numEntries(null));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetQueueEntrySet() throws Exception
	{
		filter.appendQueueEntryDef("qe1");
		filter.appendQueueEntryDef("qe2");
		Assertions.assertEquals(filter.getQueueEntryDefSet().size(), 2);
		Assertions.assertTrue(filter.getQueueEntryDefSet().contains("qe1"));
		Assertions.assertTrue(filter.getQueueEntryDefSet().contains("qe2"));
		Assertions.assertFalse(filter.getQueueEntryDefSet().contains("qe3"));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetDeviceSet() throws Exception
	{
		filter.appendDevice("qe1");
		filter.appendDevice("qe2");
		Assertions.assertEquals(filter.getDeviceIDSet().size(), 2);
		Assertions.assertTrue(filter.getDeviceIDSet().contains("qe1"));
		Assertions.assertTrue(filter.getDeviceIDSet().contains("qe2"));
		Assertions.assertFalse(filter.getDeviceIDSet().contains("qe3"));
	}

	/**
	 *
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testPerformanceDelta()
	{
		theQueue.setAutomated(false);
		for (int i = 0; i < 20000; i++)
		{
			final JDFQueueEntry qe = theQueue.appendQueueEntry();
			qe.setPriority((i * 317) % 99);
			qe.setQueueEntryID("q" + i);
			qe.setQueueEntryStatus(EnumQueueEntryStatus.getEnum(i % 7 + 1));
		}
		final JDFQueue q2 = (JDFQueue) theQueue.getOwnerDocument_JDFElement().clone().getRoot();
		q2.getQueueEntry(333).setPriority(100);
		filter.setUpdateGranularity(EnumUpdateGranularity.ChangesOnly);

		final long l1 = System.currentTimeMillis();
		filter.apply(theQueue, q2);
		final long l2 = System.currentTimeMillis();
		Assertions.assertEquals(2000, (l2 - l1), 2000, "Sort time <4 seconds");
	}

	/**
	 * @throws Exception
	 * @see JDFTestCaseBase#setUp()
	 */
	@Test
	public void testNonStatus() throws Exception
	{
		filter = theJMF.appendCommand(EnumType.SubmitQueueEntry).appendQueueFilter();
		theQueue.setAutomated(false);
		for (int i = 0; i < 120; i++)
		{
			final JDFQueueEntry qe = theQueue.appendQueueEntry();
			qe.setPriority((i * 317) % 99);
			qe.setQueueEntryID("q" + i);
			qe.setQueueEntryStatus(EnumQueueEntryStatus.getEnum(i % 7 + 1));
		}
		final JDFQueue qLast = (JDFQueue) theQueue.getOwnerDocument_JDFElement().clone().getRoot();
		final JDFQueue qCopy = filter.copy(theQueue, qLast, null);
		Assertions.assertNull(qCopy.getQueueEntry(0));
		filter.setMaxEntries(0);
		JDFQueue copy = filter.copy(theQueue, null, null);
		Assertions.assertNull(copy.getQueueEntry(0));
		filter.setMaxEntries(100);
		copy = filter.copy(theQueue, null, null);
		Assertions.assertEquals(copy.numChildElements(ElementName.QUEUEENTRY, null), 100);
	}

	/**
	 * @throws Exception
	 * @see JDFTestCaseBase#setUp()
	 */
	@Test
	public void testQueueSize() throws Exception
	{
		filter = theJMF.appendCommand(EnumType.SubmitQueueEntry).appendQueueFilter();
		theQueue.setAutomated(false);
		for (int i = 0; i < 120; i++)
		{
			final JDFQueueEntry qe = theQueue.appendQueueEntry();
			qe.setPriority((i * 317) % 99);
			qe.setQueueEntryID("q" + i);
			qe.setQueueEntryStatus(EnumQueueEntryStatus.getEnum(i % 7 + 1));
		}
		final JDFQueue qCopy = filter.copy(theQueue, null, null);
		Assertions.assertNull(qCopy.getQueueEntry(0));
		Assertions.assertEquals(120, qCopy.getQueueSize());
	}

	/**
	 *
	 */
	@Test
	public void testCopyTo()
	{
		theQueue.setAutomated(false);
		for (int i = 0; i < 12000; i++)
		{
			final JDFQueueEntry qe = theQueue.appendQueueEntry();
			qe.setPriority((i * 317) % 99);
			qe.setQueueEntryID("q" + i);
			qe.setQueueEntryStatus(EnumQueueEntryStatus.getEnum(i % 7 + 1));
		}
		final JDFQueue qLast = (JDFQueue) theQueue.getOwnerDocument_JDFElement().clone().getRoot();
		final JDFQueueEntry queueEntryLast = qLast.getQueueEntry(333);
		final JDFQueueEntry queueEntryNew = theQueue.getQueueEntry(333);
		queueEntryLast.setPriority(100);
		filter.setUpdateGranularity(EnumUpdateGranularity.ChangesOnly);

		final long l1 = System.currentTimeMillis();
		final JDFQueue qCopy = filter.copy(theQueue, qLast, null);
		final long l2 = System.currentTimeMillis();
		Assertions.assertEquals(1000, (l2 - l1), 1000, "copy time <1 second");
		Assertions.assertEquals(qCopy.numEntries(null), 1);
		Assertions.assertTrue(qCopy.getQueueEntry(0).isEqual(queueEntryNew));

		filter.setMaxEntries(0);
		JDFQueue copy = filter.copy(theQueue, null, null);
		Assertions.assertNull(copy.getQueueEntry(0));
		filter.setMaxEntries(100);
		copy = filter.copy(theQueue, null, null);
		Assertions.assertEquals(copy.numChildElements(ElementName.QUEUEENTRY, null), 100);
	}

	/**
	 *
	 */
	@Test
	public void testCopyToActivation()
	{
		for (int test = 0; test < 6; test++)
		{
			theQueue.setAutomated(false);
			for (int i = 0; i < 1200; i++)
			{
				final JDFQueueEntry qe = theQueue.getCreateQueueEntry(i);
				qe.setPriority((i * 317) % 99);
				qe.setQueueEntryID("q" + i);
				qe.setQueueEntryStatus(EnumQueueEntryStatus.getEnum(i % 7 + 1));
				if (test == 1 || test == 2)
					qe.setActivation(EnumActivation.Active);
				if (test > 3 && i % 3 == 0)
					qe.setActivation(EnumActivation.Inactive);
			}
			if (test > 0)
				filter.setActivation(EnumActivation.Active);
			if (test == 3 || test == 5)
				filter.setActivation(EnumActivation.Inactive);

			final long l1 = System.currentTimeMillis();
			final JDFQueue qCopy = filter.copy(theQueue, null, null);
			final long l2 = System.currentTimeMillis();
			Assertions.assertEquals(2000, (l2 - l1), 2000, "copy time <1 second");
			if (test < 3)
				Assertions.assertEquals(qCopy.numEntries(null), theQueue.numEntries(null));
			if (test == 3)
				Assertions.assertEquals(qCopy.numEntries(null), 0);
			if (test == 4)
				Assertions.assertEquals(qCopy.numEntries(null), 800);
			if (test == 5)
				Assertions.assertEquals(qCopy.numEntries(null), 400);

		}
	}

	/**
	 *
	 */
	@Test
	public void testCopyToDelta()
	{
		theQueue.setAutomated(false);
		for (int i = 0; i < 1200; i++)
		{
			final JDFQueueEntry qe = theQueue.appendQueueEntry();
			qe.setPriority((i * 317) % 99);
			qe.setQueueEntryID("q" + i);
			qe.setQueueEntryStatus(EnumQueueEntryStatus.getEnum(i % 7 + 1));
			qe.appendJobPhase().setStatusDetails("aa" + i);
		}
		filter.setUpdateGranularity(EnumUpdateGranularity.ChangesOnly);

		final JDFQueue qLast = (JDFQueue) theQueue.getOwnerDocument_JDFElement().clone().getRoot();
		final JDFQueueEntry queueEntryLast = qLast.getQueueEntry(333);
		final JDFQueueEntry queueEntryNew = theQueue.getQueueEntry(333);

		queueEntryLast.getJobPhase(0).setStatusDetails("other");
		filter.setQueueEntryDetails(EnumQueueEntryDetails.JobPhase);
		JDFQueue qCopy = filter.copy(theQueue, qLast, null);
		Assertions.assertEquals(qCopy.numEntries(null), 1, "we modified statusdetails, and are not ignoring job phase");
		Assertions.assertEquals(qCopy.getQueueEntry(0).getQueueEntryID(), queueEntryNew.getQueueEntryID());

		filter.setQueueEntryDetails(EnumQueueEntryDetails.Brief);
		qCopy = filter.copy(theQueue, qLast, null);
		Assertions.assertNull(qCopy, "we modified statusdetails, but are ignoring job phase");

		queueEntryLast.setPriority(100);
		qCopy = filter.copy(theQueue, qLast, null);
		Assertions.assertEquals(qCopy.numEntries(null), 1, "we changed priority...");
		Assertions.assertEquals(qCopy.getQueueEntry(0).getQueueEntryID(), queueEntryNew.getQueueEntryID());

	}

	/**
	 *
	 */
	@Test
	public void testCopyToDeltaSubset()
	{
		theQueue.setAutomated(false);
		final HashSet<String> set = new HashSet<>();
		for (int i = 0; i < 1200; i++)
		{
			final JDFQueueEntry qe = theQueue.appendQueueEntry();
			qe.setPriority((i * 317) % 99);
			qe.setQueueEntryID("q" + i);
			qe.setQueueEntryStatus(EnumQueueEntryStatus.getEnum(i % 7 + 1));
			qe.appendJobPhase().setStatusDetails("aa" + i);
			if (i % 13 == 0)
				set.add("q" + i);
		}
		filter.setUpdateGranularity(EnumUpdateGranularity.ChangesOnly);
		filter.setQueueEntrieDefs(set);

		final JDFQueue qLast = (JDFQueue) theQueue.getOwnerDocument_JDFElement().clone().getRoot();
		for (int i = 0; i < 1200; i++)
		{
			final JDFQueueEntry qe = qLast.getQueueEntry(i);
			qe.setQueueEntryStatus(EnumQueueEntryStatus.getEnum((i + 1) % 7 + 1));
		}
		filter.setQueueEntryDetails(EnumQueueEntryDetails.JobPhase);
		JDFQueue qCopy = filter.copy(theQueue, qLast, null);
		Assertions.assertEquals(qCopy.getQueueEntryVector().size() - 1, 1200 / 13);
		final Set<String> ms = qCopy.getQueueEntryIDMap().keySet();
		Assertions.assertTrue(ms.equals(set));
		filter.setQueueEntrieDefs(new HashSet<String>());
		qCopy = filter.copy(theQueue, qLast, null);
		Assertions.assertNull(qCopy);
	}

	/**
	 *
	 */
	@Test
	public void testCopyToDeltaSubsetRemove()
	{
		theQueue.setAutomated(false);
		final HashSet<String> set = new HashSet<>();
		for (int i = 0; i < 1200; i++)
		{
			final JDFQueueEntry qe = theQueue.appendQueueEntry();
			qe.setPriority((i * 317) % 99);
			qe.setQueueEntryID("q" + i);
			qe.setQueueEntryStatus(EnumQueueEntryStatus.getEnum(i % 7 + 1));
			qe.appendJobPhase().setStatusDetails("aa" + i);
			if (i % 13 == 0)
				set.add("q" + i);
		}
		filter.setUpdateGranularity(EnumUpdateGranularity.ChangesOnly);
		filter.setQueueEntrieDefs(set);

		final JDFQueue qLast = (JDFQueue) theQueue.getOwnerDocument_JDFElement().clone().getRoot();
		for (int i = 0; i < 1000; i++)
		{
			theQueue.getQueueEntry(0).deleteNode();
		}
		filter.setQueueEntryDetails(EnumQueueEntryDetails.JobPhase);
		JDFQueue qCopy = filter.copy(theQueue, qLast, null);
		Assertions.assertEquals(qCopy.getQueueEntryVector().size() - 1, 1000 / 13);
		final Set<String> ms = qCopy.getQueueEntryIDMap().keySet();
		Assertions.assertTrue(set.containsAll(ms));
		filter.setQueueEntrieDefs(new HashSet<String>());
		qCopy = filter.copy(theQueue, qLast, null);
		Assertions.assertEquals(qCopy.getQueueEntryVector().size(), 0);
	}

	/**
	 *
	 */
	@Test
	public void testCopyToDeltaPerformance()
	{
		theQueue.setAutomated(true);
		filter.setUpdateGranularity(EnumUpdateGranularity.ChangesOnly);
		final CPUTimer ct = new CPUTimer(false);
		for (int i = 0; i < 9400; i++)
		{
			final JDFQueue qLast = i < 9000 ? null : (JDFQueue) theQueue.getOwnerDocument_JDFElement().clone().getRoot();
			final JDFQueueEntry qe = theQueue.appendQueueEntry();
			qe.setPriority((i * 317) % 99);
			qe.setQueueEntryID("q" + i);
			if (i == 9000)
				log.info("startup");
			if (i > 9000)
			{
				ct.start();
				final JDFQueue qCopy = filter.copy(theQueue, qLast, null);
				Assertions.assertEquals(qCopy.getQueueEntryVector().size(), 1, "test " + i);
				Assertions.assertEquals(qCopy.getQueueEntry(0).getQueueEntryID(), "q" + i, "test " + i);
				if ((i % 100) == 0)
					log.info(ct.toString());
				ct.stop();
			}
		}
		log.info(ct.toString());
	}

	/**
	 *
	 */
	@Test
	public void testCopyToDeltaPerformance2()
	{
		theQueue.setAutomated(true);
		filter.setUpdateGranularity(EnumUpdateGranularity.ChangesOnly);
		final CPUTimer ct = new CPUTimer(false);
		final JDFQueue qLast = (JDFQueue) theQueue.getOwnerDocument_JDFElement().clone().getRoot();
		final JDFQueueEntry qeLast = qLast.appendQueueEntry();
		for (int i = 0; i < 9600; i++)
		{
			final JDFQueueEntry qe = theQueue.appendQueueEntry();
			qe.setPriority((i * 317) % 99);
			qe.setQueueEntryID("q" + i);
			qeLast.setQueueEntryID("q" + i);
			filter.getCreateQueueEntryDef(0).setQueueEntryID("q" + i);
			if (i == 9000)
				log.info("startup");
			if (i > 9000)
			{
				ct.start();
				final JDFQueue qCopy = filter.copy(theQueue, qLast, null);
				Assertions.assertEquals(qCopy.getQueueEntryVector().size(), 1, "test " + i);
				Assertions.assertEquals(qCopy.getQueueEntry(0).getQueueEntryID(), "q" + i, "test " + i);
				if ((i % 100) == 0)
					log.info(ct.toString());
				ct.stop();
			}
		}
		log.info(ct.toString());
	}

	/**
	 *
	 */
	@Test
	public void testClean()
	{
		theQueue.setAutomated(false);
		final JDFQueueEntry qe = theQueue.appendQueueEntry();
		qe.appendJobPhase().appendNode();
		filter.setQueueEntryDetails(EnumQueueEntryDetails.JDF);
		JDFQueue q2 = filter.copy(theQueue, null, null);
		JDFQueueEntry qe2 = q2.getQueueEntry(0);
		Assertions.assertNotNull(qe2.getJobPhase(0).getNode());
		filter.setQueueEntryDetails(EnumQueueEntryDetails.JobPhase);
		q2 = filter.copy(theQueue, null, null);
		qe2 = q2.getQueueEntry(0);
		Assertions.assertNotNull(qe2.getJobPhase(0));
		Assertions.assertNull(qe2.getJobPhase(0).getNode());
		filter.setQueueEntryDetails(EnumQueueEntryDetails.Brief);
		q2 = filter.copy(theQueue, null, null);
		qe2 = q2.getQueueEntry(0);
		Assertions.assertNull(qe2.getJobPhase(0));

	}
}