/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of
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
package org.cip4.jdflib.util.thread;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.ThreadUtil;
import org.junit.After;
import org.junit.Test;

/**
 * test for duh reggular janitor, duh
 *
 * @author rainer prosi
 * @date Nov 14, 2013
 */
public class RegularJanitorTest extends JDFTestCaseBase
{
	class Runner implements Runnable
	{
		int i = 0;

		/**
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			ThreadUtil.sleep(123);
			i++;
		}
	}

	class Runner2 implements Runnable
	{
		int i = 0;

		/**
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			ThreadUtil.sleep(123);
			i++;
		}
	}

	class Runner3 implements Runnable
	{

		/**
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
		}
	}

	class CSweeper implements Sweeper
	{
		/**
		 * @see org.cip4.jdflib.util.thread.Sweeper#sweep()
		 */
		@Override
		public boolean sweep()
		{
			return false;
		}

		/**
		 * @see org.cip4.jdflib.util.thread.Sweeper#needSweep()
		 */
		@Override
		public boolean needSweep()
		{
			return false;
		}
	}

	/**
	 *
	 */
	@Test
	public void testSweep()
	{
		RegularJanitor janitor = RegularJanitor.getJanitor();
		janitor.setInterval(1);
		Runner runner = new Runner();
		janitor.addSweeper(new TimeSweeper(1, runner), true);
		janitor.startSweep(1);
		ThreadUtil.sleep(5811);
		assertTrue(runner.i >= 3);
	}

	/**
	*
	*/
	@Test
	public void testSweepStart()
	{
		RegularJanitor janitor = RegularJanitor.getJanitor();
		janitor.setInterval(1);
		Runner runner = new Runner();
		janitor.addSweeper(new TimeSweeper(1, runner), true);
		janitor.startSweep(10);
		ThreadUtil.sleep(811);
		assertEquals(runner.i, 0);
	}

	/**
	*
	*/
	@Test
	public void testHasSweeper()
	{
		RegularJanitor janitor = RegularJanitor.getJanitor();
		janitor.addSweeper(new TimeSweeper(42, new Runner()), true);
		assertTrue(janitor.hasSweeper(new Runner()));
		assertTrue(janitor.hasSweeper(new TimeSweeper(42, new Runner())));
		janitor.addSweeper(new TimeSweeper(42, new Runner2()), true);
		assertTrue(janitor.hasSweeper(new Runner2()));
		assertTrue(janitor.hasSweeper(new TimeSweeper(42, new Runner2())));
		assertFalse(janitor.hasSweeper(new Runner3()));
		assertFalse(janitor.hasSweeper(new TimeSweeper(42, new Runner3())));
	}

	/**
	*
	*/
	@Test
	public void testAddSweeper()
	{
		RegularJanitor janitor = RegularJanitor.getJanitor();
		janitor.addSweeper(new TimeSweeper(42, new Runner()), true);
		assertEquals(janitor.numSweepers(), 1);
		janitor.addSweeper(new TimeSweeper(42, new Runner()), true);
		assertEquals(janitor.numSweepers(), 1);
		janitor.addSweeper(new TimeSweeper(42, new Runner2()), true);
		assertEquals(janitor.numSweepers(), 2);
		janitor.addSweeper(new TimeSweeper(42, new Runner2()), true);
		assertEquals(janitor.numSweepers(), 2);
		janitor.addSweeper(new CSweeper(), true);
		assertEquals(janitor.numSweepers(), 3);
		janitor.addSweeper(new CSweeper(), true);
		assertEquals(janitor.numSweepers(), 3);
		janitor.addSweeper(new CSweeper(), false);
		assertEquals(janitor.numSweepers(), 4);
		janitor.addSweeper(new CSweeper(), false);
		assertEquals(janitor.numSweepers(), 5);
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	@After
	protected void tearDown() throws Exception
	{
		super.tearDown();
		RegularJanitor.feierabend();
	}

}
