/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.util.thread;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.ThreadUtil;
import org.junit.jupiter.api.Test;

public class MultiTaskQueueTest extends JDFTestCaseBase
{
	int nRun;

	class WaitRunner implements Runnable
	{
		/**
		 *
		 * @param i
		 */
		WaitRunner(final int i)
		{
			this(i, 100);
		}

		WaitRunner(final int i, final int t)
		{
			super();
			this.i = i;
			this.t = t;
			addRun = true;
			log.info("created " + i);
		}

		private final int i;
		private final int t;
		boolean addRun;

		/**
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			log.info("run: " + i);
			final boolean b = ThreadUtil.sleep(t);
			log.info(b + " waited: " + i);
			nRun++;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return "WaitRunner [i=" + i + ", t=" + t + "]";
		}
	}

	/**
	 *
	 */
	@Test
	synchronized public void testSize()
	{
		final MultiTaskQueue q = MultiTaskQueue.getCreateQueue("multi0", 3);
		assertEquals(0, q.size());
		q.queue(new WaitRunner(0, 1000));
		ThreadUtil.sleep(2);
		assertEquals(1, q.size(), 1);
		ThreadUtil.sleep(42);
		assertEquals(1, q.size(), 1);
	}

	/**
	 *
	 */
	@Test
	synchronized public void testMaxParrallel()
	{
		final MultiTaskQueue q = MultiTaskQueue.getCreateQueue("multi0", 3);
		assertEquals(3, q.getMaxParallel());
	}

	/**
	 *
	 *
	 */
	@Test
	synchronized public void testString()
	{
		final MultiTaskQueue q = MultiTaskQueue.getCreateQueue("multi42", 3);
		assertNotNull(q.toString());
		for (int i = 0; i < 10; i++)
			q.queue(new WaitRunner(i, i));
		String shortString = q.shortString();
		assertNotNull(shortString);
	}

	/**
	 *
	 *
	 */
	@Test
	synchronized public void testMulti()
	{
		final OrderedTaskQueue q = MultiTaskQueue.getCreateQueue("multi1", 3);
		assertEquals(0, q.getAvQueue());
		assertEquals(0, q.getAvRun());
		for (int i = 0; i < 10; i++)
			q.queue(new WaitRunner(i, 100));
		assertEquals(q.getAvQueue(), 0);
		for (int i = 0; i < 142; i++)
		{
			ThreadUtil.sleep(10);
			if (q.size() <= 8)
			{
				break;
			}
		}
		assertEquals(q.size(), 5, 3);
		for (int i = 0; i < 420; i++)
		{
			ThreadUtil.sleep(10);
			if (q.size() == 0)
			{
				break;
			}
		}

		assertTrue(q.getAvQueue() > 0);
		assertTrue(q.getAvRun() > 0);
		assertEquals(q.size(), 0);
		assertTrue(q.queue(new WaitRunner(4)));
		for (int i = 0; i < 420; i++)
		{
			ThreadUtil.sleep(10);
			if (q.size() == 0)
			{
				break;
			}
		}
		assertEquals(q.size(), 0);
		assertTrue(q.getAvQueue() > 0);
	}

	/**
	 *
	 *
	 */
	@Test
	synchronized public void testManyMulti()
	{
		nRun = 0;
		final OrderedTaskQueue q = MultiTaskQueue.getCreateQueue("multi2b", 3);
		assertEquals(0, q.getAvQueue());
		assertEquals(0, q.getAvRun());
		for (int i = 0; i < 333; i++)
			q.queue(new WaitRunner(i, 5));

		for (int i = 0; i < 442; i++)
		{
			ThreadUtil.sleep(42);
			if (q.size() == 0)
			{
				break;
			}
		}
		ThreadUtil.sleep(42);
		assertEquals(nRun, 333, 2);
	}

	/**
	 *
	 *
	 */
	@Test
	synchronized public void testManyMultiIdle()
	{
		final OrderedTaskQueue q = MultiTaskQueue.getCreateQueue("multi2aa", 3);
		assertEquals(0, q.getAvQueue());
		assertEquals(0, q.getAvRun());
		for (int i = 0; i < 555; i++)
		{
			final WaitRunner task = new WaitRunner(i, 5);
			task.addRun = false;
			q.queue(task);
			assertEquals(0, q.idle.get(), 2);
		}

		for (int i = 0; i < 442; i++)
		{
			ThreadUtil.sleep(42);
			if (q.size() == 0)
			{
				break;
			}
		}
		assertEquals(0, q.idle.get(), 1);
	}

	/**
	 *
	 *
	 */
	@Test
	synchronized public void testInterruptMulti()
	{
		final OrderedTaskQueue q = MultiTaskQueue.getCreateQueue("multiZapp", 3);
		for (int i = 0; i < 10; i++)
			q.queue(new WaitRunner(i, 111));

		while (q.size() > 7)
			ThreadUtil.sleep(10);

		final long t0 = System.currentTimeMillis();
		while (q.size() > 0)
		{
			q.interruptCurrent(1);
			ThreadUtil.sleep(10);
		}
		assertEquals(System.currentTimeMillis() - t0, 1200, 1200);
	}

	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		MultiTaskQueue.setPercentQueue(100);

	}

	@Override
	public void tearDown() throws Exception
	{
		MultiTaskQueue.setPercentQueue(100);
		OrderedTaskQueue.shutDownAll();
		super.tearDown();
	}

}
