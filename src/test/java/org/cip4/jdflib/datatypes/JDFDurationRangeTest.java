/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.util.JDFDuration;
import org.junit.jupiter.api.Test;

class JDFDurationRangeTest
{

	/*
	 * Constructors JDFDurationRange(JDFDuration) and JDFDurationRange(JDFDuration, JDFDuration)
	 */
	@Test
	public final void testJDFDurationRangeJDFDuration() throws Exception
	{
		JDFDurationRange r = new JDFDurationRange(new JDFDuration("PT5M"), new JDFDuration("PT15M"));
		JDFDurationRange r2 = new JDFDurationRange(new JDFDuration("PT5M"));

		assertTrue(r.toString().equals("PT5M ~ PT15M"), "Bad Constructor ");
		assertTrue(r2.toString().equals("PT5M"), "Bad Constructor ");
	}

	/*
	 * Class under test for void JDFDurationRange(JDFDurationRange)
	 */
	@Test
	public final void testJDFDurationRangeJDFDurationRange() throws Exception
	{
		JDFDurationRange r = new JDFDurationRange(new JDFDuration("PT5M"), new JDFDuration("PT25M"));
		JDFDurationRange r2 = new JDFDurationRange(new JDFDuration("PT15M"));
		JDFDurationRange r3 = new JDFDurationRange(r2);
		r3.setRight(new JDFDuration("PT25M"));
		assertTrue(r.toString().equals("PT5M ~ PT25M"), "Bad Constructor" + r.toString());
		assertTrue(r2.toString().equals("PT15M"), "Bad Constructor" + r2.toString());
		assertTrue(r3.toString().equals("PT15M ~ PT25M"), "Bad CopyConstructor" + r3.toString());
	}

	@Test
	public final void testJDFDurationRangeString() throws Exception
	{
		JDFDurationRange r = new JDFDurationRange(" PT5M ~ PT15M ");

		assertTrue(r.toString().equals("PT5M ~ PT15M"), "Bad Constructor" + r.toString());
	}

	@Test
	public final void testInRange() throws Exception
	{
		JDFDurationRangeList rangelist = new JDFDurationRangeList("PT5M ~ PT15M  PT1H5M ~ PT1H15M");

		assertTrue(rangelist.inRange(new JDFDuration("PT15M")), "InRange: ");
		assertTrue(rangelist.inRange(new JDFDuration("PT1H15M")), "InRange: ");
		assertFalse(rangelist.inRange(new JDFDuration("PT1H25M")), "NOT InRange: ");
		assertFalse(rangelist.inRange(new JDFDuration("PT55S")), "NOT InRange: ");

	}

	@Test
	public final void testString() throws Exception
	{
		JDFDurationRange range = new JDFDurationRange("PT5M ~ PT15M");
		assertEquals("PT5M PT15M", range.getXJDFString(0));
		assertEquals("PT5M ~ PT15M", range.getString(0));
		assertEquals("PT5M ~ PT15M", range.toString());
		JDFDurationRange range2 = new JDFDurationRange("PT5M");
		assertEquals("PT5M", range2.toString());
		assertEquals("PT5M PT5M", range2.getXJDFString(0));

	}

	@Test
	public final void testJDFDuration() throws Exception
	{

		JDFDuration d = new JDFDuration("PT5M");
		JDFDuration d1 = new JDFDuration("PT50M");

		try
		{
			new JDFDuration("P0T5M");
			fail("invalid duration String");

		}
		catch (DataFormatException dfe)
		{
			//
		}

		try
		{
			new JDFDuration("PT5MS");
			fail("invalid duration String");

		}
		catch (DataFormatException dfe)
		{
			//
		}
		JDFDuration p1 = new JDFDuration("P1Y2M3DT50M");
		JDFDuration p11 = new JDFDuration("P01Y02M03DT50M");
		assertEquals(p1, p11);
		JDFDuration p2 = new JDFDuration("P01Y02M03D");
		JDFDuration p3 = new JDFDuration("P1Y2M3DT10H30M");
		assertTrue(d.getDuration() == 300, "Bad Constructor d");
		assertTrue(d1.getDuration() == 3000, "Bad Constructor d1");
		assertTrue(p1.getDurationISO().equals("P1Y2M3DT50M"), "Bad Constructor p1");
		assertTrue(p2.getDurationISO().equals("P1Y2M3D"), "Bad Constructor p2");
		assertTrue(p3.getDurationISO().equals("P1Y2M3DT10H30M"), "Bad Constructor p2");

	}
}
