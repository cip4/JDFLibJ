/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
 * 04022005 VF initial version
 */

package org.cip4.jdflib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.thread.MyMutex;
import org.cip4.jdflib.util.thread.WaitTimeout;
import org.junit.jupiter.api.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         08.12.2008
 */
class ThreadUtilTest extends JDFTestCaseBase
{
	private class TestWait extends WaitTimeout<Integer>
	{

		private final int sleep;

		/**
		 * @param millis
		 * @param sleep
		 */
		TestWait(final int millis, final int sleep)
		{
			super(millis);
			this.sleep = sleep;
			start();
		}

		/**
		 * @see org.cip4.jdflib.util.thread.WaitTimeout#handle()
		 */
		@Override
		protected Integer handle()
		{
			if (sleep > 0)
				ThreadUtil.sleep(sleep);
			return Integer.valueOf(42);
		}

	}

	/**
	 *
	 */
	@Test
	synchronized void testWaitTimeout()
	{
		assertEquals(new TestWait(1400, 42).getWaitedObject().intValue(), 42);
		assertNull(new TestWait(2, 5000).getWaitedObject());
	}

	/**
	 *
	 */
	@Test
	synchronized void testPeekWaitTimeout()
	{
		final TestWait testWait = new TestWait(1400, 42);
		int n = 0;
		while (testWait.peekWaitedObject() == null)
		{
			ThreadUtil.sleep(2);
			if (n++ > 1234)
				break;
		}

		assertEquals(testWait.getWaitedObject().intValue(), 42);
		assertEquals(testWait.peekWaitedObject().intValue(), 42);
	}

	/**
	 *
	 */
	@Test
	synchronized void testWaitTimeoutDelay()
	{
		final TestWait testWait = new TestWait(2000, 1000);
		ThreadUtil.sleep(1200);
		final long t0 = System.currentTimeMillis();
		assertNotNull(testWait.getWaitedObject());
		assertTrue(System.currentTimeMillis() - t0 < 1000);
	}

	/**
	 *
	 */
	@Test
	synchronized void testWaitTimeoutFail()
	{
		assertNull(new TestWait(2, 14200).getWaitedObject());
	}

	/**
	 *
	 */
	@Test
	synchronized void testWaitTimeoutMany()
	{
		for (int i = 1; i < 42; i++)
		{
			final TestWait testWait = new TestWait(142, 0);
			for (int j = 0; j < 142; j++)
			{
				if (testWait.getWaitedObject() != null)
					break;
				ThreadUtil.sleep(j);
			}
			ThreadUtil.sleep(1);
			final Integer waitedObject = testWait.getWaitedObject();
			assertNotNull(waitedObject, "Waited Loop " + i);
			assertEquals(waitedObject.intValue(), 42, "Loop " + i);
		}
	}

	/**
	 *
	 */
	@Test
	synchronized void testMyMutex()
	{
		int nLast = -1;
		for (int i = 0; i < 10000; i++)
		{
			final int n = StringUtil.parseInt(StringUtil.token(new MyMutex().toString(), 1, " "), 0);
			assertTrue(n > nLast);
			nLast = n;
		}
	}

}
