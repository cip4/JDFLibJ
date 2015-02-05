/*
 *
 * The CIP4 Software License, Version 1.0
 *
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

/*
 * @author muchadie
 */
package org.cip4.jdflib.util;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.CPUTimer.CPUTimerFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * June 11, 2009
 */
public class CPUTimerTest extends JDFTestCaseBase
{

	CPUTimer t;
	CPUTimerFactory fac;

	/**
	 * 
	 */
	@Test
	public void testCPUTime()
	{
		t.start();
		for (int i = 0; i < 100000000; i++)
		{
			Math.sin(i);
		}
		assertTrue(t.getTotalCPUTime() > 0);
	}

	/**
	 * 
	 */
	@Test
	public void testToXML()
	{
		t.start();
		for (int i = 0; i < 100000; i++)
		{
			t.toXML();
		}
		t.stop();
		assertTrue(t.getTotalCPUTime() > 0);
	}

	/**
	 * @throws InterruptedException 
	 * 
	 */
	@Test
	public void testGetSummary() throws InterruptedException
	{
		t.start();
		assertNotNull(t.getSingleSummary());
		t.stop();
		log.info(t.getSingleSummary());
		log.info("TotalCPUTime: " + t.getTotalCPUTime());
		log.info("t.getTotalCPUTime() > 0 " + (t.getTotalCPUTime() > 0));
	}

	/**
	 * 
	 */
	@Test
	public void testStartStop()
	{
		for (int i = 0; i < 1000; i++)
		{
			t.start();
			for (int ii = 0; ii < 100000; ii++)
			{
				Math.sin(ii);
			}
			t.stop();
		}
		assertTrue(t.getTotalCPUTime() > 0);
		assertEquals(t.getTotalCPUTime() / 1000, t.getAverageCPUTime());
		assertEquals(t.getTotalRealTime() / 1000, t.getAverageRealTime());
	}

	/**
	 * 
	 */
	@Test
	public void testGetCurrentTimer()
	{
		CPUTimer ct0 = fac.getCurrentTimer(null);
		assertNull(ct0);
		ct0 = fac.getCreateCurrentTimer(null);
		assertNotNull(ct0);
		CPUTimer ct1 = fac.getCreateCurrentTimer("1");
		assertNotNull(ct1);
		assertNotSame(ct0, ct1);
	}

	/**
	 * 
	 */
	@Test
	public void testGetFactory()
	{
		assertNotNull(CPUTimer.getFactory());
		assertEquals(fac, CPUTimer.getFactory());
	}

	/**
	 * 
	 */
	@Test
	public void testAdd()
	{
		long l = 0;
		long lCPU = 0;
		for (int ii = 0; ii < 5; ii++)
		{
			CPUTimer t1 = new CPUTimer(true);
			for (int i = 0; i < 3000; i++)
			{
				t.toXML();
			}
			t.add(t1);
			l += t1.getTotalRealTime();
			lCPU += t1.getTotalCPUTime();
			t1.stop();

		}
		assertTrue(t.getTotalCPUTime() > 0);
		assertEquals(t.getNumStarts(), 5);
		assertEquals(t.getTotalCPUTime(), lCPU, lCPU / 100);
		assertEquals(t.getTotalRealTime(), l, l / 100);

	}

	/**
	 * 
	 */
	@Test
	public void testAverage()
	{
		assertEquals(0, t.getAverageRealTime());
		assertEquals(0, t.getAverageCPUTime());
		assertEquals(t.getTotalCPUTime(), t.getAverageCPUTime());
		assertEquals(t.getTotalRealTime(), t.getAverageRealTime());

		t.start();
		for (int ii = 0; ii < 50000000; ii++)
		{
			Math.sin(ii);
		}
		assertTrue(t.getTotalCPUTime() > 0);
		assertEquals(t.getTotalCPUTime(), t.getAverageCPUTime(), t.getAverageCPUTime() / 100);
		assertEquals(t.getTotalRealTime(), t.getAverageRealTime(), t.getAverageRealTime() / 100);
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 * @throws Exception
	*/
	@Override
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		t = new CPUTimer(false);
		fac = CPUTimer.getFactory();
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#toString()
	 * @return
	*/
	@Override
	public String toString()
	{
		return super.toString() + " " + t;
	}

}
