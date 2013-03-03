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
import org.cip4.jdflib.node.NodeIdentifier;
import org.cip4.jdflib.util.JDFDate;
import org.junit.Assert;
import org.junit.Test;
/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * < July 4, 2009
 */
public class JDFQueueFilterTest extends JDFTestCaseBase
{
	JDFQueue theQueue;
	JDFJMF theJMF;
	JDFQueueFilter filter;

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	public void setUp()
	{
		JDFDoc d = new JDFDoc(ElementName.QUEUE);
		theQueue = (JDFQueue) d.getRoot();
		d = new JDFDoc(ElementName.JMF);
		theJMF = d.getJMFRoot();
		filter = theJMF.appendCommand(EnumType.AbortQueueEntry).appendQueueFilter();
	}

	// //////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testMatchDiff_RemoveNonMatching() throws Exception
	{
		JDFDoc d = new JDFDoc(ElementName.QUEUE);
		JDFQueue newQueue = (JDFQueue) d.getRoot();
		for (int i = 5; i < 10; i++)
		{
			final JDFQueueEntry qe = newQueue.appendQueueEntry();
			qe.setQueueEntryID("q" + i);
			qe.setQueueEntryStatus(EnumQueueEntryStatus.Running);
			qe.appendJobPhase().setStatus(JDFElement.EnumNodeStatus.InProgress);
		}
		System.out.println("NewQueue:\n" + newQueue.toXML());

		d = new JDFDoc(ElementName.QUEUE);
		JDFQueue oldQueue = (JDFQueue) d.getRoot();
		for (int i = 0; i < 10; i++)
		{
			final JDFQueueEntry qe = oldQueue.appendQueueEntry();
			qe.setQueueEntryID("q" + i);
			qe.setQueueEntryStatus(EnumQueueEntryStatus.Running);
			qe.appendJobPhase().setStatus(JDFElement.EnumNodeStatus.InProgress);
		}
		System.out.println("OldQueue:\n" + oldQueue.toXML());

		d = new JDFDoc(ElementName.JMF);
		JDFResponse resp = ((JDFJMF) d.getRoot()).appendResponse();
		resp.setType(EnumType.AbortQueueEntry);

		d = new JDFDoc(ElementName.JMF);
		JDFJMF theJMF = d.getJMFRoot();
		JDFQueueFilter filter = theJMF.appendCommand(EnumType.AbortQueueEntry).appendQueueFilter();
		filter.setUpdateGranularity(EnumUpdateGranularity.ChangesOnly);
		Vector<EnumQueueEntryStatus> v = new Vector<EnumQueueEntryStatus>();
		v.add(EnumQueueEntryStatus.Aborted);
		filter.setStatusList(v);
		filter.setQueueEntryDetails(EnumQueueEntryDetails.JobPhase);
		System.out.println("Filter:\n" + filter.toXML());

		filter.copy(newQueue, oldQueue, resp);
		System.out.println("Result:\n" + resp.toXML());
		Assert.assertEquals(newQueue.getQueueEntryVector().size(), 5);
		Assert.assertNotNull(resp.getQueue(0));
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
		Assert.assertNull("identical queue should cancel", matchedQueue);
		theQueue.setQueueStatus(EnumQueueStatus.Held);
		copyMatch = (JDFQueue) new JDFDoc("JDF").getRoot().copyElement(theQueue, null);
		matchedQueue = filter.apply(copyMatch, copy);
		Assert.assertEquals(EnumQueueStatus.Held, matchedQueue.getQueueStatus());
		Assert.assertNull(matchedQueue.getQueueEntry(0));
		final JDFQueueEntry addedQE = theQueue.appendQueueEntry();
		addedQE.setQueueEntryID("qe_test");
		copyMatch = (JDFQueue) new JDFDoc("JDF").getRoot().copyElement(theQueue, null);
		matchedQueue = filter.apply(copyMatch, copy);
		Assert.assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryID(), "qe_test");
		Assert.assertNull(matchedQueue.getQueueEntry(1));
		addedQE.deleteNode();
		theQueue.getQueueEntry("q1").setQueueEntryStatus(EnumQueueEntryStatus.Aborted);
		copyMatch = (JDFQueue) new JDFDoc("JDF").getRoot().copyElement(theQueue, null);
		matchedQueue = filter.apply(copyMatch, copy);
		Assert.assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryID(), "q1");
		Assert.assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryStatus(), EnumQueueEntryStatus.Aborted);
		Assert.assertNull(matchedQueue.getQueueEntry(1));

		theQueue.getQueueEntry("q1").deleteNode();
		copyMatch = (JDFQueue) new JDFDoc("JDF").getRoot().copyElement(theQueue, null);
		matchedQueue = filter.apply(copyMatch, copy);
		Assert.assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryID(), "q1");
		Assert.assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryStatus(), EnumQueueEntryStatus.Removed);
		Assert.assertNull(matchedQueue.getQueueEntry(1));
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
		Assert.assertNull("identical queue should cancel", matchedQueue.getQueueEntry(0));
		JDFQueueEntry qe = copy.appendQueueEntry();
		qe.setQueueEntryID("q11");
		matchedQueue = filter.copy(theQueue, copy, null);
		Assert.assertEquals("got removed entry", matchedQueue.getQueueEntry(0).getQueueEntryStatus(), EnumQueueEntryStatus.Removed);
		qe = theQueue.appendQueueEntry();
		qe.setQueueEntryID("q10");
		qe = theQueue.appendQueueEntry();
		qe.setQueueEntryID("q11");
		matchedQueue = filter.copy(theQueue, copy, null);
		Assert.assertEquals("got new entry", matchedQueue.getQueueEntry(0).getQueueEntryID(), "q10");
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetStatusList() throws Exception
	{
		Vector<EnumQueueEntryStatus> vs = new Vector<EnumQueueEntryStatus>();
		vs.add(EnumQueueEntryStatus.Completed);
		vs.add(EnumQueueEntryStatus.Aborted);
		filter.setStatusList(vs);

		Vector<EnumQueueEntryStatus> statusList = filter.getStatusList();
		Assert.assertTrue(statusList.contains(EnumQueueEntryStatus.Completed));
		Assert.assertTrue(statusList.contains(EnumQueueEntryStatus.Aborted));
		Assert.assertFalse(statusList.contains(EnumQueueEntryStatus.Running));
	}

	/**
	 *  
	 */
	@Test
	public void testGetIdentifier()
	{
		NodeIdentifier ni = new NodeIdentifier("j1", "p1", null);
		filter.setIdentifier(ni);
		Assert.assertEquals(filter.getIdentifier(), ni);
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
		Assert.assertTrue(filter.matches(qe1));
		Assert.assertFalse(filter.matches(qe2));
		filter.appendGeneralID("ProductThing", "a2");
		Assert.assertTrue(filter.matches(qe1));
		Assert.assertTrue(filter.matches(qe2));
		filter.setGeneralID("ProductThing", "a0");
		Assert.assertFalse(filter.matches(qe1));
		Assert.assertFalse(filter.matches(qe2));
		filter.setGeneralID("ProductThing", "a(.)?");
		Assert.assertTrue(filter.matches(qe1));
		Assert.assertTrue(filter.matches(qe2));

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testMatches() throws Exception
	{
		final JDFQueueEntry qe = theQueue.appendQueueEntry();
		Assert.assertTrue("both empty ", filter.matches(qe));
		qe.setDeviceID("d1");
		qe.setQueueEntryID("qe1");

		filter.appendDevice("qe1");
		Assert.assertFalse("no device ", filter.matches(qe));
		filter.appendDevice("d1");
		Assert.assertTrue(" device ", filter.matches(qe));

		filter.appendQueueEntryDef("qe2");
		Assert.assertFalse("no qentryID ", filter.matches(qe));
		filter.appendQueueEntryDef("qe1");
		Assert.assertTrue("qentryID ", filter.matches(qe));
		filter.setQueueEntryDetails(EnumQueueEntryDetails.None);
		Assert.assertFalse("details=none never matches ", filter.matches(qe));

		filter.setQueueEntryDetails(EnumQueueEntryDetails.Brief); // undo none for additional tests

		qe.setJobID("jID");
		Assert.assertTrue("jobID ", filter.matches(qe));
		filter.setJobID("jID");
		Assert.assertTrue("jobID ", filter.matches(qe));
		filter.setJobID("j(.)*");
		Assert.assertTrue("jobID ", filter.matches(qe));
		filter.setJobID("jID2");
		Assert.assertFalse("jobID ", filter.matches(qe));
		filter.setJobID("jID");

		qe.setJobPartID("part");
		Assert.assertTrue("jobPartID ", filter.matches(qe));
		filter.setJobPartID("part");
		Assert.assertTrue("jobID ", filter.matches(qe));
		filter.setJobPartID("par(.)*");
		Assert.assertTrue("jobID ", filter.matches(qe));
		filter.setJobPartID("part2");
		Assert.assertFalse("jobID ", filter.matches(qe));
		filter.setJobPartID("part");

		JDFDate d = new JDFDate();
		qe.setSubmissionTime(d);
		JDFDate d2 = d.clone();
		d2.addOffset(200, 0, 0, 0);
		filter.setOlderThan(d2);
		Assert.assertTrue("older ", filter.matches(qe));
		filter.setOlderThan(null);
		filter.setNewerThan(d2);
		Assert.assertFalse("newer ", filter.matches(qe));
		d2.addOffset(0, -10, 0, 0);
		filter.setNewerThan(d2);
		Assert.assertTrue("newer ", filter.matches(qe));
		filter.setOlderThan(d2);
		filter.setNewerThan(null);
		Assert.assertFalse("older ", filter.matches(qe));
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
		Assert.assertEquals(10, theQueue.numEntries(null));
		filter.setQueueEntryDetails(EnumQueueEntryDetails.None);
		filter.apply(theQueue, null);
		Assert.assertEquals(0, theQueue.numEntries(null));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetQueueEntrySet() throws Exception
	{
		filter.appendQueueEntryDef("qe1");
		filter.appendQueueEntryDef("qe2");
		Assert.assertEquals(filter.getQueueEntryDefSet().size(), 2);
		Assert.assertTrue(filter.getQueueEntryDefSet().contains("qe1"));
		Assert.assertTrue(filter.getQueueEntryDefSet().contains("qe2"));
		Assert.assertFalse(filter.getQueueEntryDefSet().contains("qe3"));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetDeviceSet() throws Exception
	{
		filter.appendDevice("qe1");
		filter.appendDevice("qe2");
		Assert.assertEquals(filter.getDeviceIDSet().size(), 2);
		Assert.assertTrue(filter.getDeviceIDSet().contains("qe1"));
		Assert.assertTrue(filter.getDeviceIDSet().contains("qe2"));
		Assert.assertFalse(filter.getDeviceIDSet().contains("qe3"));
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
		Assert.assertEquals("Sort time <4 seconds", 2000, (l2 - l1), 2000);
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
		JDFQueueEntry queueEntryLast = qLast.getQueueEntry(333);
		JDFQueueEntry queueEntryNew = theQueue.getQueueEntry(333);
		queueEntryLast.setPriority(100);
		filter.setUpdateGranularity(EnumUpdateGranularity.ChangesOnly);

		final long l1 = System.currentTimeMillis();
		JDFQueue qCopy = filter.copy(theQueue, qLast, null);
		final long l2 = System.currentTimeMillis();
		Assert.assertEquals("copy time <1 second", 1000, (l2 - l1), 1000);
		Assert.assertEquals(qCopy.numEntries(null), 1);
		Assert.assertTrue(qCopy.getQueueEntry(0).isEqual(queueEntryNew));

		filter.setMaxEntries(0);
		JDFQueue copy = filter.copy(theQueue, null, null);
		Assert.assertNull(copy.getQueueEntry(0));
		filter.setMaxEntries(100);
		copy = filter.copy(theQueue, null, null);
		Assert.assertEquals(copy.numChildElements(ElementName.QUEUEENTRY, null), 100);
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
		JDFQueueEntry queueEntryLast = qLast.getQueueEntry(333);
		JDFQueueEntry queueEntryNew = theQueue.getQueueEntry(333);

		queueEntryLast.getJobPhase(0).setStatusDetails("other");
		filter.setQueueEntryDetails(EnumQueueEntryDetails.JobPhase);
		JDFQueue qCopy = filter.copy(theQueue, qLast, null);
		Assert.assertEquals("we modified statusdetails, and are not ignoring job phase", qCopy.numEntries(null), 1);
		Assert.assertEquals(qCopy.getQueueEntry(0).getQueueEntryID(), queueEntryNew.getQueueEntryID());

		filter.setQueueEntryDetails(EnumQueueEntryDetails.Brief);
		qCopy = filter.copy(theQueue, qLast, null);
		Assert.assertEquals("we modified statusdetails, but are ignoring job phase", qCopy.numEntries(null), 0);

		queueEntryLast.setPriority(100);
		qCopy = filter.copy(theQueue, qLast, null);
		Assert.assertEquals("we changed priority...", qCopy.numEntries(null), 1);
		Assert.assertEquals(qCopy.getQueueEntry(0).getQueueEntryID(), queueEntryNew.getQueueEntryID());

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
		Assert.assertNotNull(qe2.getJobPhase(0).getNode());
		filter.setQueueEntryDetails(EnumQueueEntryDetails.JobPhase);
		q2 = filter.copy(theQueue, null, null);
		qe2 = q2.getQueueEntry(0);
		Assert.assertNotNull(qe2.getJobPhase(0));
		Assert.assertNull(qe2.getJobPhase(0).getNode());
		filter.setQueueEntryDetails(EnumQueueEntryDetails.Brief);
		q2 = filter.copy(theQueue, null, null);
		qe2 = q2.getQueueEntry(0);
		Assert.assertNull(qe2.getJobPhase(0));

	}
}