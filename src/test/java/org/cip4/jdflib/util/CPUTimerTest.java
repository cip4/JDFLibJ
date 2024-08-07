/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

/*
 * @author muchadie
 */
package org.cip4.jdflib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.CPUTimer.CPUTimerFactory;
import org.junit.jupiter.api.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         June 11, 2009
 */
class CPUTimerTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	void testCPUTime()
	{
		final CPUTimer t = new CPUTimer(false);
		t.start();
		for (int i = 0; i < 100000000; i++)
		{
			Math.sin(i);
			if (i % 100000 == 0 && t.getTotalCPUTime() > 0)
			{
				break;
			}
		}
		assertTrue(t.getTotalCPUTime() > 0, " t=" + t.getTotalCPUTime());
	}

	/**
	 *
	 */
	@Test
	void testToXML()
	{
		final CPUTimer t = new CPUTimer(false);
		t.start();
		for (int i = 0; i < 100000; i++)
		{
			t.toXML();
			if (i > 3 && (t.getTotalCPUTime() > 0))
				break;
		}
		t.stop();
		assertTrue(t.getTotalCPUTime() > 0);
	}

	/**
	 * @throws InterruptedException
	 *
	 */
	@Test
	void testGetSummary() throws InterruptedException
	{
		final CPUTimer t = new CPUTimer(false);
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
	void testStartStop()
	{
		int i = 0;
		final CPUTimer t = new CPUTimer(false);
		for (i = 1; i < 1000; i++)
		{
			t.start();
			for (int ii = 0; ii < 100000; ii++)
			{
				Math.sin(ii);
			}
			t.stop();
			if (i > 2 && t.getTotalCPUTime() > 0)
				break;
		}
		assertTrue(t.getTotalCPUTime() > 0);
		assertEquals(t.getTotalCPUTime() / i, t.getAverageCPUTime());
		assertEquals(t.getTotalRealTime() / i, t.getAverageRealTime());
	}

	/**
	 *
	 */
	@Test
	void testGetCurrentTimer()
	{
		final CPUTimerFactory fac = CPUTimer.getFactory();
		CPUTimer ct0 = fac.getCurrentTimer(null);
		assertNull(ct0);
		ct0 = fac.getCreateCurrentTimer(null);
		assertNotNull(ct0);
		final CPUTimer ct1 = fac.getCreateCurrentTimer("1");
		assertNotNull(ct1);
		assertNotSame(ct0, ct1);
	}

	/**
	 *
	 */
	@Test
	void testGetFactory()
	{
		final CPUTimerFactory fac = CPUTimer.getFactory();
		assertNotNull(CPUTimer.getFactory());
		assertEquals(fac, CPUTimer.getFactory());
	}

	/**
	 *
	 */
	@Test
	void testAdd()
	{
		long l = 0;
		long lCPU = 0;
		int ii;
		final CPUTimer t = new CPUTimer(false);
		for (ii = 1; ii < 142; ii++)
		{
			final CPUTimer t1 = new CPUTimer(true);
			for (int i = 0; i < 1000; i++)
			{
				t.toXML();
			}
			t.add(t1);
			l += t1.getTotalRealTime();
			lCPU += t1.getTotalCPUTime();
			t1.stop();
			if (ii > 2 && (t.getTotalCPUTime() > 0))
				break;
		}
		assertTrue(t.getTotalCPUTime() > 0);
		assertEquals(t.getNumStarts(), ii);
		assertEquals(t.getTotalCPUTime(), lCPU, 10 + lCPU / 7);
		assertEquals(t.getTotalRealTime(), l, 10 + l / 7);

	}

	/**
	 *
	 */
	@Test
	void testAverage()
	{
		final CPUTimer t = new CPUTimer(false);
		assertEquals(0, t.getAverageRealTime());
		assertEquals(0, t.getAverageCPUTime());
		assertEquals(t.getTotalCPUTime(), t.getAverageCPUTime());
		assertEquals(t.getTotalRealTime(), t.getAverageRealTime());

		t.start();
		int i = 0;
		for (i = 0; i < 1000; i++)
		{
			for (int ii = 0; ii < 100000; ii++)
			{
				Math.sin(ii);
			}
			if (i > 2 && t.getTotalCPUTime() > 0)
				break;
		}
		assertTrue(t.getTotalCPUTime() > 0);
		assertEquals(t.getTotalCPUTime(), t.getAverageCPUTime(), t.getAverageCPUTime() / i);
		assertEquals(t.getTotalRealTime(), t.getAverageRealTime(), t.getAverageRealTime() / i);
	}

}
