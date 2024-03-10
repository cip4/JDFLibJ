/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
/**
 * JDFDurationRangeTest.java
 *
 * @author Elena Skobchenko
 *
 *         Copyright (c) 2001-2004 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFDurationTest {

	/**
	 *
	 * @throws Exception
	 */
	@Test
	public final void testNegativeDuration() throws Exception
	{
		JDFDuration d = new JDFDuration(" -PT5M ");
		Assertions.assertEquals(d.getDurationISO(), "-PT5M");
		try
		{
			new JDFDuration("--PT5M90.95S");
			Assertions.fail("bad duration string");
		}
		catch (final Exception e)
		{
			// nop
		}
		d = new JDFDuration("-P3M");
		Assertions.assertEquals(d.getDurationISO(), "-P3M");
		Assertions.assertEquals(d.getDuration(), -3 * 30 * 24 * 60 * 60);
		d = new JDFDuration("-P3MT4M");
		Assertions.assertEquals(d.getDurationISO(), "-P3MT4M");
		Assertions.assertEquals(d.getDuration(), -3 * 30 * 24 * 60 * 60 - 4 * 60);
		d = new JDFDuration("-P13M");
		Assertions.assertEquals(d.getDurationISO(), "-P1Y1M");

		d = new JDFDuration("-P365D");
		Assertions.assertEquals(d.getDurationISO(), "-P1Y");
		d = new JDFDuration("-P395D");
		Assertions.assertEquals(d.getDurationISO(), "-P1Y1M");
		d = new JDFDuration("-PT3600S");
		Assertions.assertEquals(d.getDurationISO(), "-PT1H");
		Assertions.assertEquals(new JDFDuration("-PT0.95S").getDurationISO(), "-PT0.95S");
		Assertions.assertEquals(new JDFDuration("-PT5M30.45S").getDurationISO(), "-PT5M30.45S");
		Assertions.assertEquals(new JDFDuration("-PT5M90.95S").getDurationISO(), "-PT6M30.95S");
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	public final void testJDFDurationString() throws Exception
	{
		JDFDuration d = new JDFDuration(" PT5M ");
		Assertions.assertEquals(d.getDurationISO(), "PT5M");
		try
		{
			new JDFDuration("PT5M90.95aS");
			Assertions.fail("bad duration string");
		}
		catch (final Exception e)
		{
			// nop
		}
		try
		{
			new JDFDuration("PTM90.95aS");
			Assertions.fail("bad duration string");
		}
		catch (final Exception e)
		{
			// nop
		}
		d = new JDFDuration("P3M");
		Assertions.assertEquals(d.getDurationISO(), "P3M");
		Assertions.assertEquals(d.getDuration(), 3 * 30 * 24 * 60 * 60);
		d = new JDFDuration("P3MT4M");
		Assertions.assertEquals(d.getDurationISO(), "P3MT4M");
		Assertions.assertEquals(d.getDuration(), 3 * 30 * 24 * 60 * 60 + 4 * 60);
		d = new JDFDuration("P13M");
		Assertions.assertEquals(d.getDurationISO(), "P1Y1M");

		d = new JDFDuration("P365D");
		Assertions.assertEquals(d.getDurationISO(), "P1Y");
		d = new JDFDuration("P395D");
		Assertions.assertEquals(d.getDurationISO(), "P1Y1M");
		d = new JDFDuration("PT3600S");
		Assertions.assertEquals(d.getDurationISO(), "PT1H");
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public final void testFractions() throws Exception
	{
		Assertions.assertEquals(new JDFDuration(90.5).getDurationISO(), "PT1M30.5S");
		Assertions.assertEquals(new JDFDuration(-90.5).getDurationISO(), "-PT1M30.5S");
		Assertions.assertEquals(new JDFDuration("PT0.95S").getDurationISO(), "PT0.95S");
		Assertions.assertEquals(new JDFDuration("PT5M30.45S").getDurationISO(), "PT5M30.45S");
		Assertions.assertEquals(new JDFDuration("PT5M90.95S").getDurationISO(), "PT6M30.95S");
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public final void testEqualsFractions() throws Exception
	{
		Assertions.assertEquals(new JDFDuration(90.5), new JDFDuration("PT1M30.5S"));
		Assertions.assertEquals(new JDFDuration(-90.5), new JDFDuration("-PT1M30.5S"));
		Assertions.assertEquals(new JDFDuration(0.95), new JDFDuration("PT0.95S"));
		Assertions.assertEquals(new JDFDuration(99.00001), new JDFDuration(99));
		Assertions.assertFalse(new JDFDuration(99.001).equals(new JDFDuration(99)));
		Assertions.assertEquals(new JDFDuration(-99.00001), new JDFDuration(-99));
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public final void testEqualsFractionsHash() throws Exception
	{
		Assertions.assertEquals(new JDFDuration(90.5).hashCode(), new JDFDuration("PT1M30.5S").hashCode());
		Assertions.assertEquals(new JDFDuration(-90.5).hashCode(), new JDFDuration("-PT1M30.5S").hashCode());
		Assertions.assertEquals(new JDFDuration(0.95).hashCode(), new JDFDuration("PT0.95S").hashCode());
		Assertions.assertEquals(new JDFDuration(99.00001).hashCode(), new JDFDuration(99).hashCode());
		Assertions.assertFalse(new JDFDuration(99.001).hashCode() == new JDFDuration(99).hashCode());
		Assertions.assertEquals(new JDFDuration(-99.00001).hashCode(), new JDFDuration(-99).hashCode());
	}

	// //////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public final void testCompareTo()
	{
		Assertions.assertEquals(new JDFDuration(90.5).compareTo(new JDFDuration(90.5)), 0);
		Assertions.assertEquals(new JDFDuration(-90.5).compareTo(new JDFDuration(-90.5)), 0);
		Assertions.assertEquals(new JDFDuration(-90.5).compareTo(new JDFDuration(0)), -1);
		Assertions.assertEquals(new JDFDuration(-90.5).compareTo(new JDFDuration(-20)), -1);
		Assertions.assertEquals(new JDFDuration(90.5).compareTo(new JDFDuration(0)), 1);
		Assertions.assertEquals(new JDFDuration(90.5).compareTo(new JDFDuration(90)), 1);
		Assertions.assertEquals(new JDFDuration(90.5).compareTo(new JDFDuration(900)), -1);
	}

	/**
	 *
	 */
	@Test
	public final void testConstructFromDate()
	{
		final JDFDate start = new JDFDate();
		final JDFDate end = new JDFDate(start);
		Assertions.assertEquals(new JDFDuration(start, end), new JDFDuration(0));
		end.addOffset(20, 0, 0, 0);
		Assertions.assertEquals(new JDFDuration(start, end), new JDFDuration(20));
		start.addOffset(120, 0, 0, 0);
		Assertions.assertEquals(new JDFDuration(start, end), new JDFDuration(-100));
	}

	/**
	 *
	 */
	@Test
	public final void testAddSeconds()
	{
		final JDFDuration duration = new JDFDuration();
		Assertions.assertEquals(duration.addSeconds(5.234), 5.234, 0.0001);
		Assertions.assertEquals(duration.getDurationISO(), "PT5.234S");
	}

	/**
	 *
	 */
	@Test
	public final void testSetDuration()
	{
		final JDFDuration duration = new JDFDuration();
		duration.setDuration(65);
		Assertions.assertEquals(duration.getDurationISO(), "PT1M5S");
		duration.setDuration(60 * 60 * 24 * 63);
		Assertions.assertEquals(duration.getDurationISO(), "P2M3D");
		duration.addSeconds(65.5);
		Assertions.assertEquals(duration.getDurationISO(), "P2M3DT1M5.5S");
		duration.addSeconds(60 * 60 * 3);
		Assertions.assertEquals(duration.getDurationISO(), "P2M3DT3H1M5.5S");
		duration.addSeconds(60 * 60 * 3);
		Assertions.assertEquals(duration.getDurationISO(), "P2M3DT6H1M5.5S");
	}

	/**
	*
	*/
	@Test
	public final void testGetDurationMillis()
	{
		Assertions.assertEquals(JDFDuration.createDuration("P1000D").getDurationMillis(), 1000l * 1000l * 60l * 60l * 24l);
	}

	/**
	*
	*/
	@Test
	public final void testGetDuration()
	{
		Assertions.assertEquals(JDFDuration.createDuration("P1000D").getDuration() * 1000l, 1000l * 1000l * 60l * 60l * 24l);
	}

	/**
	*
	*/
	@Test
	public final void testGetDurationINF()
	{
		Assertions.assertEquals(JDFDuration.createDuration("INF").getDuration(), Long.MAX_VALUE);
	}

	/**
	 *
	 */
	@Test
	public final void testSetDurationInf()
	{
		final JDFDuration duration = new JDFDuration();
		duration.setDuration(Double.MAX_VALUE);
		Assertions.assertEquals(duration.getDurationISO(), JDFConstants.POSINF);
		duration.setDuration(Long.MAX_VALUE);
		Assertions.assertEquals(duration.getDurationISO(), JDFConstants.POSINF);
		duration.setDuration(Integer.MAX_VALUE);
		Assertions.assertEquals(duration.getDurationISO(), JDFConstants.POSINF);
	}

	@Test
	void testCreateDurationNull() throws Exception
	{
		DataFormatException ex = null;
		try
		{
			new JDFDuration((String) null);
		}
		catch (final DataFormatException e)
		{
			ex = e;
		}
		Assertions.assertNotNull(ex);
		try
		{
			new JDFDuration(" ");
		}
		catch (final DataFormatException e)
		{
			ex = e;
		}
	}

	/**
	 *
	 */
	@Test
	public final void testCreateDuration()
	{
		Assertions.assertEquals(JDFDuration.createDuration("1").getDuration(), 60 * 60 * 24);
		Assertions.assertEquals(JDFDuration.createDuration("1d").getDuration(), 60 * 60 * 24);
		Assertions.assertEquals(JDFDuration.createDuration("1D").getDuration(), 60 * 60 * 24);
		Assertions.assertEquals(JDFDuration.createDuration("1h").getDuration(), 60 * 60);
		Assertions.assertEquals(JDFDuration.createDuration("1D 1h 3m").getDuration(), 60 * 60 * 25 + 180);
		Assertions.assertEquals(JDFDuration.createDuration("\t1D \n1h 3m").getDuration(), 60 * 60 * 25 + 180);
		Assertions.assertEquals(JDFDuration.createDuration("25h").getDuration(), 60 * 60 * 25);
		Assertions.assertEquals(JDFDuration.createDuration("p25h").getDuration(), 60 * 60 * 25);
		Assertions.assertEquals(JDFDuration.createDuration("pt25h").getDuration(), 60 * 60 * 25);
		Assertions.assertEquals(JDFDuration.createDuration("pt30m").getDuration(), 30 * 60);
		Assertions.assertEquals(JDFDuration.createDuration("pt3h").getDuration(), 3 * 60 * 60);
		Assertions.assertEquals(JDFDuration.createDuration("240h").getDuration(), 60 * 60 * 240);
		Assertions.assertNull(JDFDuration.createDuration("foo"));
	}
}
