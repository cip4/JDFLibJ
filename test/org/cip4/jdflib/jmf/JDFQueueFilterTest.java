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
import org.cip4.jdflib.jmf.JDFMessage.EnumType;

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
	// /////////////////

	/**
	 * @throws Exception
	 */
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
		assertNull("identical queue should cancel", matchedQueue);
		theQueue.setQueueStatus(EnumQueueStatus.Held);
		copyMatch = (JDFQueue) new JDFDoc("JDF").getRoot().copyElement(theQueue, null);
		matchedQueue = filter.apply(copyMatch, copy);
		assertEquals(EnumQueueStatus.Held, matchedQueue.getQueueStatus());
		assertNull(matchedQueue.getQueueEntry(0));
		final JDFQueueEntry addedQE = theQueue.appendQueueEntry();
		addedQE.setQueueEntryID("qe_test");
		copyMatch = (JDFQueue) new JDFDoc("JDF").getRoot().copyElement(theQueue, null);
		matchedQueue = filter.apply(copyMatch, copy);
		assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryID(), "qe_test");
		assertNull(matchedQueue.getQueueEntry(1));
		addedQE.deleteNode();
		theQueue.getQueueEntry("q1").setQueueEntryStatus(EnumQueueEntryStatus.Aborted);
		copyMatch = (JDFQueue) new JDFDoc("JDF").getRoot().copyElement(theQueue, null);
		matchedQueue = filter.apply(copyMatch, copy);
		assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryID(), "q1");
		assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryStatus(), EnumQueueEntryStatus.Aborted);
		assertNull(matchedQueue.getQueueEntry(1));

		theQueue.getQueueEntry("q1").deleteNode();
		copyMatch = (JDFQueue) new JDFDoc("JDF").getRoot().copyElement(theQueue, null);
		matchedQueue = filter.apply(copyMatch, copy);
		assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryID(), "q1");
		assertEquals(matchedQueue.getQueueEntry(0).getQueueEntryStatus(), EnumQueueEntryStatus.Removed);
		assertNull(matchedQueue.getQueueEntry(1));
	}

	/**
	 * @throws Exception
	 */
	public void testGetStatusList() throws Exception
	{
		Vector<EnumQueueEntryStatus> vs = new Vector<EnumQueueEntryStatus>();
		vs.add(EnumQueueEntryStatus.Completed);
		vs.add(EnumQueueEntryStatus.Aborted);
		filter.setStatusList(vs);

		Vector<EnumQueueEntryStatus> statusList = filter.getStatusList();
		assertTrue(statusList.contains(EnumQueueEntryStatus.Completed));
		assertTrue(statusList.contains(EnumQueueEntryStatus.Aborted));
		assertFalse(statusList.contains(EnumQueueEntryStatus.Running));
	}

	/**
	 * @throws Exception
	 */
	public void testMatches() throws Exception
	{
		final JDFQueueEntry qe = theQueue.appendQueueEntry();
		assertTrue("both empty ", filter.matches(qe));
		qe.setDeviceID("d1");
		qe.setQueueEntryID("qe1");

		filter.appendDevice("qe1");
		assertFalse("no device ", filter.matches(qe));
		filter.appendDevice("d1");
		assertTrue(" device ", filter.matches(qe));

		filter.appendQueueEntryDef("qe2");
		assertFalse("no qentryID ", filter.matches(qe));
		filter.appendQueueEntryDef("qe1");
		assertTrue("qentryID ", filter.matches(qe));
		filter.setQueueEntryDetails(EnumQueueEntryDetails.None);
		assertFalse("details=none never matches ", filter.matches(qe));

		filter.setQueueEntryDetails(EnumQueueEntryDetails.Brief); // undo none for additional tests

		qe.setJobID("jID");
		assertTrue("jobID ", filter.matches(qe));
		filter.setJobID("jID");
		assertTrue("jobID ", filter.matches(qe));
		filter.setJobID("jID2");
		assertFalse("jobID ", filter.matches(qe));
		filter.setJobID("jID");

		qe.setJobPartID("part");
		assertTrue("jobPartID ", filter.matches(qe));
		filter.setJobPartID("part");
		assertTrue("jobID ", filter.matches(qe));
		filter.setJobPartID("part2");
		assertFalse("jobID ", filter.matches(qe));
		filter.setJobPartID("part");

	}

	// //////////////////////////////////////////////////////////////////////////
	// /////////////////

	/**
	 * @throws Exception
	 */
	public void testMatch() throws Exception
	{
		for (int i = 0; i < 100; i++)
		{
			final JDFQueueEntry qe = theQueue.appendQueueEntry();
			qe.setQueueEntryID("q" + i);
		}

		filter.setMaxEntries(10);
		filter.apply(theQueue, null);
		assertEquals(10, theQueue.numEntries(null));
		filter.setQueueEntryDetails(EnumQueueEntryDetails.None);
		filter.apply(theQueue, null);
		assertEquals(0, theQueue.numEntries(null));
	}

	/**
	 * @throws Exception
	 */
	public void testGetQueueEntrySet() throws Exception
	{
		filter.appendQueueEntryDef("qe1");
		filter.appendQueueEntryDef("qe2");
		assertEquals(filter.getQueueEntryDefSet().size(), 2);
		assertTrue(filter.getQueueEntryDefSet().contains("qe1"));
		assertTrue(filter.getQueueEntryDefSet().contains("qe2"));
		assertFalse(filter.getQueueEntryDefSet().contains("qe3"));
	}

	/**
	 * @throws Exception
	 */
	public void testGetDeviceSet() throws Exception
	{
		filter.appendDevice("qe1");
		filter.appendDevice("qe2");
		assertEquals(filter.getDeviceIDSet().size(), 2);
		assertTrue(filter.getDeviceIDSet().contains("qe1"));
		assertTrue(filter.getDeviceIDSet().contains("qe2"));
		assertFalse(filter.getDeviceIDSet().contains("qe3"));
	}

	/**
	 * 
	 */
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
		assertEquals("Sort time <4 seconds", 2000, (l2 - l1), 2000);
	}

	/**
	 * 
	 */
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
		assertEquals("copy time <1 second", 1000, (l2 - l1), 1000);
		assertEquals(qCopy.numEntries(null), 1);
		assertTrue(qCopy.getQueueEntry(0).isEqual(queueEntryNew));

		filter.setMaxEntries(0);
		JDFQueue copy = filter.copy(theQueue, null, null);
		assertNull(copy.getQueueEntry(0));
		filter.setMaxEntries(100);
		copy = filter.copy(theQueue, null, null);
		assertEquals(copy.numChildElements(ElementName.QUEUEENTRY, null), 100);
	}
}