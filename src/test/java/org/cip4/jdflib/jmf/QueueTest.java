/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of 
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

import java.util.Map;

import junit.framework.TestCase;

import org.cip4.jdflib.auto.JDFAutoQueue.EnumQueueStatus;
import org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFQueue.CleanupCallback;
import org.cip4.jdflib.jmf.JDFQueue.ExecuteCallback;
import org.cip4.jdflib.node.NodeIdentifier;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.ThreadUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author MuchaD
 * 
 *         This implements the first fixture with unit tests for class JDFQueue.
 */
public class QueueTest extends TestCase
{
	JDFQueue q;
	protected static int iThread = 0;
	protected static int iThread2 = 0;

	protected class QueueTestThread implements Runnable
	{
		@Override
		public void run()
		{
			final int t = 1000 * iThread++;
			for (int i = 0; i < 100; i++)
			{
				synchronized (q)
				{
					final JDFQueueEntry qe = q.appendQueueEntry();
					qe.setQueueEntryID("q" + t + "_" + i);
					qe.setPriority((i * 7) % 100);
					qe.setQueueEntryStatus(((iThread2++) % 2 == 0) ? EnumQueueEntryStatus.Waiting : EnumQueueEntryStatus.Held);
				}
			}
			ThreadUtil.sleep(44);
			iThread--;
		}
	}

	protected class MyClean extends CleanupCallback
	{

		public int i = 0;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.cip4.jdflib.jmf.JDFQueue.CleanupCallback#cleanEntry(org.cip4. jdflib.jmf.JDFQueueEntry)
		 */
		@Override
		public void cleanEntry(final JDFQueueEntry qe)
		{
			i++;
		}

	}

	/**
	 * @see junit.framework.TestCase#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		return q == null ? "null" : q.toString();
	}

	/**
	 * 
	 */
	@Test
	public void testGetQueueEntry()
	{
		Assert.assertEquals("qe2", q.getQueueEntry(1).getQueueEntryID(), "qe2");
		Assert.assertEquals("qe2", q.getQueueEntry("qe1").getQueueEntryID(), "qe1");
		Assert.assertEquals("qe2", q.getQueueEntry("qe2").getQueueEntryID(), "qe2");
		Assert.assertEquals("qe2", q.getQueueEntry("qe3").getQueueEntryID(), "qe3");
		Assert.assertNull("qe6", q.getQueueEntry("qe6"));
		Assert.assertEquals("qe6", -1, q.getQueueEntryPos("qe6"));
		Assert.assertEquals("qe2", 1, q.getQueueEntryPos("qe2"));
	}

	/**
	 * 
	 */
	@Test
	public void testGetQueueEntryMap()
	{
		final Map<String, JDFQueueEntry> map = q.getQueueEntryIDMap();
		Assert.assertEquals(map.size(), q.numEntries(null));
		Assert.assertEquals(map.get("qe2"), q.getQueueEntry("qe2"));
	}

	/**
	 * 
	 */
	@Test
	public void testCreateQueueEntry()
	{
		q.setAutomated(true);
		q.setMaxRunningEntries(2);
		q.setMaxWaitingEntries(3);
		q.flushQueue(null);
		JDFQueueEntry qe = q.createQueueEntry(false);
		Assert.assertEquals(qe.getQueueEntryStatus(), EnumQueueEntryStatus.Waiting);
		qe = q.createQueueEntry(true);
		Assert.assertEquals(qe.getQueueEntryStatus(), EnumQueueEntryStatus.Held);
		q.setMaxWaitingEntries(1);
		qe = q.createQueueEntry(true);
		Assert.assertNull(qe);
	}

	// ///////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testOpenClose()
	{
		q.setAutomated(true);
		q.setMaxRunningEntries(2);
		q.setMaxWaitingEntries(3);
		q.flushQueue(null);
		Assert.assertEquals(q.openQueue(), EnumQueueStatus.Waiting);
		Assert.assertTrue(q.canAccept());
		Assert.assertTrue(q.canExecute());
		Assert.assertEquals(q.closeQueue(), EnumQueueStatus.Closed);
		Assert.assertFalse(q.canAccept());
		Assert.assertTrue(q.canExecute());
		Assert.assertEquals(q.openQueue(), EnumQueueStatus.Waiting);
		Assert.assertEquals(q.holdQueue(), EnumQueueStatus.Held);
		Assert.assertTrue(q.canAccept());
		Assert.assertFalse(q.canExecute());
		Assert.assertEquals(q.resumeQueue(), EnumQueueStatus.Waiting);
		Assert.assertEquals(q.holdQueue(), EnumQueueStatus.Held);
		Assert.assertEquals(q.closeQueue(), EnumQueueStatus.Blocked);
		Assert.assertFalse(q.canAccept());
		Assert.assertFalse(q.canExecute());
		Assert.assertEquals(q.resumeQueue(), EnumQueueStatus.Closed);
		Assert.assertEquals(q.openQueue(), EnumQueueStatus.Waiting);
		JDFQueueEntry qe = q.createQueueEntry(false);
		qe = q.createQueueEntry(false);
		Assert.assertTrue(q.canAccept());
		Assert.assertEquals(q.getQueueStatus(), EnumQueueStatus.Waiting);
		qe = q.createQueueEntry(false);
		Assert.assertFalse("max 3 waiting - see above ", q.canAccept());
		Assert.assertEquals(q.getQueueStatus(), EnumQueueStatus.Closed);
		qe.setQueueEntryStatus(EnumQueueEntryStatus.Running);
		Assert.assertTrue("max 3 waiting - see above ", q.canAccept());
		Assert.assertTrue("max 3 waiting - see above ", q.canExecute());
		Assert.assertEquals(q.getQueueStatus(), EnumQueueStatus.Waiting);
		qe = q.createQueueEntry(false);
		qe.setQueueEntryStatus(EnumQueueEntryStatus.Running);
		Assert.assertEquals(q.getQueueStatus(), EnumQueueStatus.Running);
		qe = q.createQueueEntry(false);
		Assert.assertEquals(q.getQueueStatus(), EnumQueueStatus.Full);

	}

	/**
	 * 
	 */
	@Test
	public void testSetAutomated()
	{
		q.flush();
		q.setAutomated(true);
		Assert.assertEquals(q.isAutomated(), true);
		Assert.assertEquals(q.getQueueStatus(), EnumQueueStatus.Waiting);
	}

	/**
	 * 
	 */
	@Test
	public void testFlushAutomated()
	{
		q.setAutomated(true);
		q.setMaxWaitingEntries(1);
		q.setMaxRunningEntries(1);
		Assert.assertEquals(q.getQueueStatus(), EnumQueueStatus.Full);
		q.flushQueue(null);
		Assert.assertEquals(q.getQueueStatus(), EnumQueueStatus.Waiting);

	}

	/**
	 * 
	 */
	@Test
	public void testGetQueueEntryByIdentifier()
	{
		q.getQueueEntry(1).setJobID("j7");
		final NodeIdentifier ni = new NodeIdentifier("j7", null, null);
		Assert.assertEquals(q.getQueueEntry(ni, 0), q.getQueueEntry(1));
		Assert.assertEquals(q.getQueueEntry(ni, -1), q.getQueueEntry(1));
		Assert.assertNull(q.getQueueEntry(ni, -2));
		Assert.assertNull(q.getQueueEntry(ni, 1));
		q.getQueueEntry(3).setJobID("j7");

		Assert.assertEquals(q.getQueueEntry(ni, 0), q.getQueueEntry(1));
		Assert.assertEquals(q.getQueueEntry(ni, -1), q.getQueueEntry(3));
		Assert.assertEquals(q.getQueueEntry(ni, 1), q.getQueueEntry(3));
		Assert.assertEquals(q.getQueueEntry(ni, -2), q.getQueueEntry(1));
		Assert.assertNull(q.getQueueEntry(ni, -3));
		Assert.assertNull(q.getQueueEntry(ni, 4));
	}

	/**
	 * 
	 */
	@Test
	public void testGetQueueEntryVectorByIdentifier()
	{
		final NodeIdentifier ni = new NodeIdentifier("j2", null, null);
		Assert.assertEquals(q.getQueueEntryVector(ni).elementAt(0), q.getQueueEntry(1));
		Assert.assertEquals(q.getQueueEntryVector(null).elementAt(0), q.getQueueEntry(0));
		Assert.assertEquals(q.getQueueEntryVector(null).size(), q.numChildElements(ElementName.QUEUEENTRY, null));
	}

	/**
	 * 
	 */
	@Test
	public void testGetTimes()
	{
		final JDFQueueEntry qe = q.getQueueEntry(0);
		qe.setQueueEntryID("qe1");
		JDFDate d = qe.getEndTime();
		Assert.assertNull("date", d);
		qe.setEndTime(null);
		d = qe.getEndTime();
		Assert.assertEquals("date", d.getTimeInMillis(), new JDFDate().getTimeInMillis(), 30000);
	}

	/**
	 * 
	 */
	@Test
	public void testFlushQueue()
	{
		final JDFQueueFilter qf = (JDFQueueFilter) new JDFDoc(ElementName.QUEUEFILTER).getRoot();
		qf.appendQueueEntryDef("qe5");
		final VElement v = q.flushQueue(qf);
		Assert.assertEquals(v.size(), 1);
		Assert.assertEquals(((JDFQueueEntry) v.get(0)).getQueueEntryID(), "qe5");
		Assert.assertEquals(q.numEntries(null), 4);

	}

	/**
	 * 
	 */
	@Test
	public void testSortPerformance()
	{
		q.setAutomated(false);
		for (int i = 0; i < 10000; i++)
		{
			final JDFQueueEntry qe = q.appendQueueEntry();
			qe.setPriority((i * 317) % 99);
			qe.setQueueEntryStatus(EnumQueueEntryStatus.getEnum(i % 7 + 1));
		}
		final long l1 = System.currentTimeMillis();
		q.sortChildren();
		final long l2 = System.currentTimeMillis();
		Assert.assertEquals("Sort time <4 seconds", 3000, (l2 - l1), 3000);
	}

	/**
	 * 
	 */
	@Test
	public void testSortCompleted()
	{
		q.removeChildren(null, null, null);
		q.setAutomated(true);
		q.setMaxCompletedEntries(10);
		for (int i = 0; i < 5; i++)
		{
			final JDFQueueEntry qe = q.appendQueueEntry();
			qe.setQueueEntryID("q" + i);
			qe.setPriority((i * 317) % 99);
			qe.setQueueEntryStatus(EnumQueueEntryStatus.Completed);
			JDFDate d = new JDFDate(System.currentTimeMillis() + i * 1000000);
			qe.setEndTime(d);
		}
		Assert.assertTrue(q.getQueueEntry(2).getEndTime().isEarlier(q.getQueueEntry(1).getEndTime()));
	}

	/**
	 * 
	 */
	@Test
	public void testThreads()
	{
		q.setAutomated(true);
		q.sortChildren();
		q.removeChildren(ElementName.QUEUEENTRY, null, null);
		q.setMaxCompletedEntries(999999999);
		for (int i = 0; i < 10; i++)
		{
			final QueueTestThread queueTestThread = new QueueTestThread();
			new Thread(queueTestThread).start();
		}
		// now also zapp some...
		int k = 0;
		for (int j = 0; k < 100; j++)
		{
			final JDFQueueEntry qex = q.getNextExecutableQueueEntry();
			if (qex != null)
			{
				qex.setQueueEntryStatus(EnumQueueEntryStatus.Running);
				ThreadUtil.sleep(50);
				qex.setQueueEntryStatus(EnumQueueEntryStatus.Completed);
				k++;
			}
			else
			{
				ThreadUtil.sleep(10);
			}
		}
		while (iThread > 0)
		{
			ThreadUtil.sleep(1000); // wait for threads to be over
			System.out.println("threads: " + iThread);
		}
		Assert.assertEquals(q.getQueueSize(), 1000);
		VElement v = q.getQueueEntryVector();
		JDFQueueEntry qeLast = null;
		for (int i = 0; i < v.size(); i++)
		{
			final JDFQueueEntry qe = (JDFQueueEntry) v.elementAt(i);
			System.out.println(i + " " + qe.getPriority() + " " + qe.getQueueEntryID() + " " + qe.getQueueEntryStatus());
			boolean b = qe.compareTo(qeLast) >= 0;
			Assert.assertTrue(b);
			qeLast = qe;
		}

		v = q.getQueueEntryVector(new JDFAttributeMap(AttributeName.STATUS, "Completed"), null);
		for (int i = 0; i < 10; i++)
		{
			if (v.size() != 100)
			{
				ThreadUtil.sleep(1500);
				System.out.println("Threads done: " + v.size());
				v = q.getQueueEntryVector(new JDFAttributeMap(AttributeName.STATUS, "Completed"), null);
			}
		}

		Assert.assertEquals(v.size(), 100);
		v = q.getQueueEntryVector(new JDFAttributeMap(AttributeName.STATUS, "Running"), null);
		Assert.assertNull(v);
		v = q.getQueueEntryVector(new JDFAttributeMap(AttributeName.STATUS, "Held"), null);
		Assert.assertEquals(v.size(), 500);
		v = q.getQueueEntryVector(new JDFAttributeMap(AttributeName.STATUS, "Waiting"), null);
		Assert.assertEquals(v.size(), 400);
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 * 
	 */
	@Test
	public void testNumEntries()
	{
		Assert.assertEquals(5, q.numEntries(null));
		Assert.assertEquals(2, q.numEntries(EnumQueueEntryStatus.Waiting));
	}

	/**
	 * 
	 */
	@Test
	public void testHasFewerEntries()
	{
		Assert.assertEquals(true, q.hasFewerEntries(null, 4));
		Assert.assertEquals(true, q.hasFewerEntries(null, 5));
		Assert.assertEquals(false, q.hasFewerEntries(null, 6));
		Assert.assertEquals(true, q.hasFewerEntries(EnumQueueEntryStatus.Waiting, 2));
		Assert.assertEquals(false, q.hasFewerEntries(EnumQueueEntryStatus.Waiting, 3));
	}

	/**
	 * 
	 */
	@Test
	public void testGetQueueEntryVector()
	{
		Assert.assertEquals(5, q.getQueueEntryVector().size());
		Assert.assertEquals(2, q.getQueueEntryVector(new JDFAttributeMap("Status", EnumQueueEntryStatus.Waiting), null).size());
	}

	/**
	 * 
	 */
	@Test
	public void testCanExecute()
	{
		Assert.assertFalse(q.canExecute());
		q.setMaxRunningEntries(2);
		Assert.assertTrue(q.canExecute());
		q.setQueueStatus(EnumQueueStatus.Held);
		Assert.assertFalse(q.canExecute());
		q.setQueueStatus(EnumQueueStatus.Waiting);
		Assert.assertTrue("note that this is inconsistent", q.canExecute());
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	protected class TestCanExecute extends ExecuteCallback
	{

		public TestCanExecute(final String pdeviceID, final String pproxy)
		{
			super();
			this.deviceID = pdeviceID;
			this.proxy = pproxy;
		}

		String deviceID;
		String proxy;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.cip4.jdflib.jmf.JDFQueue.ExecuteCallback#canExecute(org.cip4.jdflib.jmf.JDFQueueEntry)
		 */
		@Override
		public boolean canExecute(final JDFQueueEntry qe)
		{
			if (proxy != null && qe.hasAttribute(proxy))
			{
				return false;
			}
			if (deviceID != null && !KElement.isWildCard(qe.getDeviceID()) && !deviceID.equals(qe.getDeviceID()))
			{
				return false;
			}
			return true;

		}

	}

	/**
	 * 
	 */
	@Test
	public void testExecuteCallBack()
	{
		q.setQueueStatus(EnumQueueStatus.Waiting);
		q.sortChildren();
		q.setExecuteCallback(new TestCanExecute("d1", null));
		Assert.assertEquals(q.getNextExecutableQueueEntry(), q.getQueueEntry("qe2"));
		q.getQueueEntry("qe4").setQueueEntryStatus(EnumQueueEntryStatus.Waiting);
		q.setExecuteCallback(new TestCanExecute("d2", null));
		Assert.assertEquals(q.getNextExecutableQueueEntry(), q.getQueueEntry("qe2"));
		q.getQueueEntry("qe2").setDeviceID("d1");
		Assert.assertEquals(q.getNextExecutableQueueEntry(), q.getQueueEntry("qe1"));
		q.setExecuteCallback(new TestCanExecute("d1", "foo:foo2"));
		Assert.assertEquals(q.getNextExecutableQueueEntry(), q.getQueueEntry("qe2"));
		q.getQueueEntry("qe2").setAttribute("foo:foo2", "bar", "www.foo");
		Assert.assertEquals(q.getNextExecutableQueueEntry(), q.getQueueEntry("qe1"));
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 * 
	 */
	@Test
	public void testGetNextExecutableQueueEntry()
	{
		Assert.assertNull(q.getNextExecutableQueueEntry());
		q.setMaxRunningEntries(2);
		q.sortChildren();
		Assert.assertEquals(q.getNextExecutableQueueEntry(), q.getQueueEntry("qe2"));
		q.setQueueStatus(EnumQueueStatus.Held);
		Assert.assertNull(q.getNextExecutableQueueEntry());
		q.setQueueStatus(EnumQueueStatus.Waiting);
		Assert.assertEquals(q.getNextExecutableQueueEntry(), q.getQueueEntry("qe2"));
		q.getQueueEntry("qe4").setQueueEntryStatus(EnumQueueEntryStatus.Waiting);
		q.getQueueEntry("qe2").setDeviceID("d1");
		Assert.assertEquals(q.getNextExecutableQueueEntry(), q.getQueueEntry("qe2"));

	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 * 
	 */
	@Test
	public void testCleanup()
	{
		final JDFQueueEntry qe = q.appendQueueEntry();
		final MyClean myClean = new MyClean();
		Assert.assertEquals(myClean.i, 0);
		q.setCleanupCallback(myClean);
		qe.setQueueEntryStatus(EnumQueueEntryStatus.Removed);
		q.setAutomated(true);
		q.setMaxCompletedEntries(1);
		Assert.assertFalse(q.getQueueEntryVector().contains(qe));
		Assert.assertEquals(myClean.i, 1);
		Assert.assertEquals("removed completed and aborted", q.numEntries(null), 5);
		q.setMaxCompletedEntries(0);
		q.cleanup();
		Assert.assertEquals(myClean.i, 2);

		Assert.assertEquals("removed completed and aborted", q.numEntries(null), 4);
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 * 
	 */
	@Test
	public void testCopyToResponse()
	{
		final JDFResponse r = JDFJMF.createJMF(JDFMessage.EnumFamily.Response, EnumType.AbortQueueEntry).getResponse(0);
		final JDFQueueFilter qf = (JDFQueueFilter) new JDFDoc(ElementName.QUEUEFILTER).getRoot();
		qf.setMaxEntries(3);
		JDFQueue q2 = q.copyToResponse(r, qf, null);
		Assert.assertEquals(q2, r.getQueue(0));
		Assert.assertEquals(q2.numEntries(null), 3);
		Assert.assertNotSame(q, q2);
		Assert.assertTrue(q.numEntries(null) > 3);
		q2 = q.copyToResponse(r, qf, null);
		Assert.assertEquals(q2, r.getQueue(0));
		Assert.assertNull(r.getElement("Queue", null, 1));
		Assert.assertEquals(q2.numEntries(null), 3);
		Assert.assertNotSame(q, q2);
		Assert.assertTrue(q.numEntries(null) > 3);
	}

	/**
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		// TODO Auto-generated method stub
		super.setUp();
		final JDFDoc doc = new JDFDoc(ElementName.QUEUE);
		q = (JDFQueue) doc.getRoot();
		q.setMaxRunningEntries(1);
		JDFQueueEntry qe = q.appendQueueEntry();
		qe.setQueueEntryID("qe1");
		qe.setJobID("j1");
		qe.setQueueEntryStatus(EnumQueueEntryStatus.Waiting);
		qe.setPriority(5);
		qe = q.appendQueueEntry();
		qe.setQueueEntryStatus(EnumQueueEntryStatus.Waiting);
		qe.setPriority(55);
		qe.setQueueEntryID("qe2");
		qe.setJobID("j2");
		qe = q.appendQueueEntry();
		qe.setQueueEntryStatus(EnumQueueEntryStatus.Held);
		qe.setPriority(99);
		qe.setQueueEntryID("qe3");
		qe.setJobID("j3");
		qe = q.appendQueueEntry();
		qe.setQueueEntryStatus(EnumQueueEntryStatus.Completed);
		qe.setQueueEntryID("qe4");
		qe.setJobID("j4");
		qe = q.appendQueueEntry();
		qe.setQueueEntryStatus(EnumQueueEntryStatus.Running);
		qe.setQueueEntryID("qe5");
		qe.setJobID("j5");
		iThread = 0;
	}

	/**
	 * 
	 */
	@Test
	public void testgetQueueSize()
	{
		Assert.assertEquals("no size set - count entries", q.getQueueSize(), 5);
		q.setQueueSize(10);
		Assert.assertEquals(q.getQueueSize(), 10);
	}
}