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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.ThreadUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 * @date Dec 19, 2012
 */
public class OrderedTaskQueueTest extends JDFTestCaseBase
{

	class WaitRunner implements Runnable
	{
		/**
		 *
		 * @param i
		 */
		WaitRunner(final int i)
		{
			super();
			this.i = i;
			t = 100;
		}

		WaitRunner(final int i, final int t)
		{
			super();
			this.i = i;
			this.t = t;
		}

		private final int i;
		private final int t;

		/**
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			log.info("started: " + i);
			final boolean b = ThreadUtil.sleep(t);
			log.info("Sleep=" + b + " waited: " + i);
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
	*
	*/
	@Test
	@Disabled
	synchronized public void testInterruptTask()
	{
		final MultiTaskQueue q = MultiTaskQueue.getCreateQueue("multiiii2", 3);
		WaitRunner task = null;
		for (int i = 0; i < 3; i++)
		{
			task = new WaitRunner(i, 10000);
			q.queue(task);
		}
		for (int i = 0; i < 333; i++)
		{
			if (q.getCurrentRunning() < 3)
				ThreadUtil.sleep(3);
		}
		q.interruptTask(task);
		for (int i = 0; i < 333; i++)
		{
			if (q.getCurrentRunning() > 2)
				ThreadUtil.sleep(3);
		}
		assertEquals(q.getCurrentRunning(), 2);
		ThreadUtil.sleep(33);
		q.interruptCurrent(0);
		ThreadUtil.sleep(33);
		q.interruptCurrent(0);
		for (int i = 0; i < 342; i++)
		{
			if (q.getCurrentRunning() > 0 || q.size() > 0)
				ThreadUtil.sleep(3);
		}
		assertEquals(0, q.getCurrentRunning());
		assertEquals(0, q.size());
	}

	/**
	 *
	 *
	 */
	@Test
	synchronized public void test3Entry()
	{
		final OrderedTaskQueue q = OrderedTaskQueue.getCreateQueue("test");
		assertEquals(q.getAvQueue(), 0);
		assertTrue(q.queue(new WaitRunner(1, 2)));
		assertTrue(q.queue(new WaitRunner(2, 2)));
		assertTrue(q.queue(new WaitRunner(3, 2)));
		while (q.getAvRun() == 0)
			ThreadUtil.sleep(5);
		assertTrue(q.queue(new WaitRunner(4, 2)));
		while (q.getAvRun() == 0)
			ThreadUtil.sleep(5);
		assertTrue(q.getAvRun() > 0);
		assertTrue(q.getAvQueue() > 0);

	}

	/**
	 *
	 *
	 */
	@Test
	synchronized public void testStop()
	{
		final OrderedTaskQueue q = OrderedTaskQueue.getCreateQueue("tesffft2");
		assertTrue(q.queue(new WaitRunner(1)));
		q.shutDown();
		for (int i = 0; i < 100; i++)
		{
			if (!q.isLive())
				break;
			ThreadUtil.sleep(2);
		}
		assertFalse(q.queue(new WaitRunner(2)));
	}

	/**
	 *
	 *
	 */
	@Test
	synchronized public void testIsLive()
	{
		final OrderedTaskQueue q = OrderedTaskQueue.getCreateQueue("live");
		assertTrue(q.isLive());
		assertTrue(q.queue(new WaitRunner(1)));
		assertTrue(q.isLive());
		q.shutDown();
	}

	/**
	 *
	 *
	 */
	@Test
	synchronized public void testInterruptCurrent()
	{
		final OrderedTaskQueue q = OrderedTaskQueue.getCreateQueue("test3r33");
		assertTrue(q.queue(new WaitRunner(1, 10000)));
		assertTrue(q.queue(new WaitRunner(2, 10000)));
		ThreadUtil.sleep(420);
		log.info(q);
		assertFalse(q.interruptCurrent(2000));
		log.info(q);
		ThreadUtil.sleep(200);
		assertTrue(q.interruptCurrent(1));
		ThreadUtil.sleep(100);
		log.info(q);
		assertTrue(q.interruptCurrent(1));
		ThreadUtil.sleep(100);
		log.info(q);
		assertTrue(q.interruptCurrent(1));
	}

	/**
	 *
	 *
	 */
	@Test
	synchronized public void testStopOne()
	{
		final OrderedTaskQueue q = OrderedTaskQueue.getCreateQueue("teffst4");
		assertTrue(q.queue(new WaitRunner(1)));
		OrderedTaskQueue.shutDown("teffst4");
		for (int i = 0; i < 100; i++)
		{
			if (!q.isLive())
				break;
			ThreadUtil.sleep(12);
		}
		assertFalse(q.queue(new WaitRunner(2)));
	}

	/**
	 *
	 *
	 */
	@Test
	synchronized public void testStopAll()
	{
		final OrderedTaskQueue q = OrderedTaskQueue.getCreateQueue("test554445");
		assertTrue(q.queue(new WaitRunner(1, 200)));
		OrderedTaskQueue.shutDownAll();
		for (int i = 0; i < 2000; i++)
		{
			if (!q.isLive())
				break;
			ThreadUtil.sleep(12);
			OrderedTaskQueue.shutDownAll();
		}
		assertFalse(q.queue(new WaitRunner(2, 200)));
	}

	/**
	 *
	 *
	 */
	@Test
	synchronized public void testQueue()
	{
		final OrderedTaskQueue q = OrderedTaskQueue.getCreateQueue("test42");
		assertTrue(q.queue(new WaitRunner(1, 200)));
		assertEquals(0, q.idle.get());
	}

	/**
	 *
	 *
	 */
	@Test
	synchronized public void testString()
	{
		final OrderedTaskQueue q = OrderedTaskQueue.getCreateQueue("test42");
		assertNotNull(q.toString());
		for (int i = 0; i < 100; i++)
			q.queue(new WaitRunner(i, i));
		String shortString = q.shortString();
		assertNotNull(shortString);
	}

	@Override
	@AfterEach
	public void tearDown() throws Exception
	{
		super.tearDown();
		OrderedTaskQueue.shutDownAll();
	}
}
