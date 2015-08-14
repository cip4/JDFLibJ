/*
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
/**
 * JDFDurationRangeTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import junit.framework.TestCase;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.junit.Test;

/**
 * 
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFDurationTest extends TestCase
{

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testNegativeDuration() throws Exception
	{
		JDFDuration d = new JDFDuration(" -PT5M ");
		assertEquals(d.getDurationISO(), "-PT5M");
		try
		{
			new JDFDuration("--PT5M90.95S");
			fail("bad duration string");
		}
		catch (Exception e)
		{
			// nop
		}
		d = new JDFDuration("-P3M");
		assertEquals(d.getDurationISO(), "-P3M");
		assertEquals(d.getDuration(), -3 * 30 * 24 * 60 * 60);
		d = new JDFDuration("-P3MT4M");
		assertEquals(d.getDurationISO(), "-P3MT4M");
		assertEquals(d.getDuration(), -3 * 30 * 24 * 60 * 60 - 4 * 60);
		d = new JDFDuration("-P13M");
		assertEquals(d.getDurationISO(), "-P1Y1M");

		d = new JDFDuration("-P365D");
		assertEquals(d.getDurationISO(), "-P1Y");
		d = new JDFDuration("-P395D");
		assertEquals(d.getDurationISO(), "-P1Y1M");
		d = new JDFDuration("-PT3600S");
		assertEquals(d.getDurationISO(), "-PT1H");
		assertEquals(new JDFDuration("-PT0.95S").getDurationISO(), "-PT0.95S");
		assertEquals(new JDFDuration("-PT5M30.45S").getDurationISO(), "-PT5M30.45S");
		assertEquals(new JDFDuration("-PT5M90.95S").getDurationISO(), "-PT6M30.95S");
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testJDFDurationString() throws Exception
	{
		JDFDuration d = new JDFDuration(" PT5M ");
		assertEquals(d.getDurationISO(), "PT5M");
		try
		{
			new JDFDuration("PT5M90.95aS");
			fail("bad duration string");
		}
		catch (Exception e)
		{
			// nop
		}
		try
		{
			new JDFDuration("PTM90.95aS");
			fail("bad duration string");
		}
		catch (Exception e)
		{
			// nop
		}
		d = new JDFDuration("P3M");
		assertEquals(d.getDurationISO(), "P3M");
		assertEquals(d.getDuration(), 3 * 30 * 24 * 60 * 60);
		d = new JDFDuration("P3MT4M");
		assertEquals(d.getDurationISO(), "P3MT4M");
		assertEquals(d.getDuration(), 3 * 30 * 24 * 60 * 60 + 4 * 60);
		d = new JDFDuration("P13M");
		assertEquals(d.getDurationISO(), "P1Y1M");

		d = new JDFDuration("P365D");
		assertEquals(d.getDurationISO(), "P1Y");
		d = new JDFDuration("P395D");
		assertEquals(d.getDurationISO(), "P1Y1M");
		d = new JDFDuration("PT3600S");
		assertEquals(d.getDurationISO(), "PT1H");
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @throws Exception
	 * 
	 */
	@Test
	public final void testFractions() throws Exception
	{
		assertEquals(new JDFDuration(90.5).getDurationISO(), "PT1M30.5S");
		assertEquals(new JDFDuration(-90.5).getDurationISO(), "-PT1M30.5S");
		assertEquals(new JDFDuration("PT0.95S").getDurationISO(), "PT0.95S");
		assertEquals(new JDFDuration("PT5M30.45S").getDurationISO(), "PT5M30.45S");
		assertEquals(new JDFDuration("PT5M90.95S").getDurationISO(), "PT6M30.95S");
	}

	// //////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public final void testCompareTo()
	{
		assertEquals(new JDFDuration(90.5).compareTo(new JDFDuration(90.5)), 0);
		assertEquals(new JDFDuration(-90.5).compareTo(new JDFDuration(-90.5)), 0);
		assertEquals(new JDFDuration(-90.5).compareTo(new JDFDuration(0)), -1);
		assertEquals(new JDFDuration(-90.5).compareTo(new JDFDuration(-20)), -1);
		assertEquals(new JDFDuration(90.5).compareTo(new JDFDuration(0)), 1);
		assertEquals(new JDFDuration(90.5).compareTo(new JDFDuration(90)), 1);
		assertEquals(new JDFDuration(90.5).compareTo(new JDFDuration(900)), -1);
	}

	/**
	 * 
	 */
	@Test
	public final void testConstructFromDate()
	{
		JDFDate start = new JDFDate();
		JDFDate end = new JDFDate(start);
		assertEquals(new JDFDuration(start, end), new JDFDuration(0));
		end.addOffset(20, 0, 0, 0);
		assertEquals(new JDFDuration(start, end), new JDFDuration(20));
		start.addOffset(120, 0, 0, 0);
		assertEquals(new JDFDuration(start, end), new JDFDuration(-100));
	}

	/**
	 * 
	 */
	@Test
	public final void testAddSeconds()
	{
		final JDFDuration duration = new JDFDuration();
		assertEquals(duration.addSeconds(5.234), 5.234, 0.0001);
		assertEquals(duration.getDurationISO(), "PT5.234S");
	}

	/**
	 * 
	 */
	@Test
	public final void testSetDuration()
	{
		final JDFDuration duration = new JDFDuration();
		duration.setDuration(65);
		assertEquals(duration.getDurationISO(), "PT1M5S");
		duration.setDuration(60 * 60 * 24 * 63);
		assertEquals(duration.getDurationISO(), "P2M3D");
		duration.addSeconds(65.5);
		assertEquals(duration.getDurationISO(), "P2M3DT1M5.5S");
		duration.addSeconds(60 * 60 * 3);
		assertEquals(duration.getDurationISO(), "P2M3DT3H1M5.5S");
		duration.addSeconds(60 * 60 * 3);
		assertEquals(duration.getDurationISO(), "P2M3DT6H1M5.5S");
	}

	/**
	* 
	*/
	@Test
	public final void testGetDurationMillis()
	{
		assertEquals(JDFDuration.createDuration("P1000D").getDurationMillis(), 1000l * 1000l * 60l * 60l * 24l);
	}

	/**
	* 
	*/
	@Test
	public final void testGetDuration()
	{
		assertEquals(JDFDuration.createDuration("P1000D").getDuration() * 1000l, 1000l * 1000l * 60l * 60l * 24l);
	}

	/**
	* 
	*/
	@Test
	public final void testGetDurationINF()
	{
		assertEquals(JDFDuration.createDuration("INF").getDuration(), Long.MAX_VALUE);
	}

	/**
	 * 
	 */
	@Test
	public final void testSetDurationInf()
	{
		final JDFDuration duration = new JDFDuration();
		duration.setDuration(Double.MAX_VALUE);
		assertEquals(duration.getDurationISO(), JDFConstants.POSINF);
		duration.setDuration(Long.MAX_VALUE);
		assertEquals(duration.getDurationISO(), JDFConstants.POSINF);
		duration.setDuration(Integer.MAX_VALUE);
		assertEquals(duration.getDurationISO(), JDFConstants.POSINF);
	}

	/**
	 * 
	 */
	@Test
	public final void testCreateDuration()
	{
		assertEquals(JDFDuration.createDuration("1").getDuration(), 60 * 60 * 24);
		assertEquals(JDFDuration.createDuration("1d").getDuration(), 60 * 60 * 24);
		assertEquals(JDFDuration.createDuration("1D").getDuration(), 60 * 60 * 24);
		assertEquals(JDFDuration.createDuration("1h").getDuration(), 60 * 60);
		assertEquals(JDFDuration.createDuration("1D 1h 3m").getDuration(), 60 * 60 * 25 + 180);
		assertEquals(JDFDuration.createDuration("\t1D \n1h 3m").getDuration(), 60 * 60 * 25 + 180);
		assertEquals(JDFDuration.createDuration("25h").getDuration(), 60 * 60 * 25);
		assertEquals(JDFDuration.createDuration("p25h").getDuration(), 60 * 60 * 25);
		assertEquals(JDFDuration.createDuration("pt25h").getDuration(), 60 * 60 * 25);
		assertEquals(JDFDuration.createDuration("240h").getDuration(), 60 * 60 * 240);
		assertNull(JDFDuration.createDuration("foo"));
	}
}
