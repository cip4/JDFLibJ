/*
 *
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
/**
 * JDFDateTimeRangeTest.java
 *
 * @author Elena Skobchenko
 *
 *         Copyright (c) 2001-2004 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.junit.Test;

import junit.framework.TestCase;

/**
 *
 *
 * @author rainer prosi
 * @date Apr 12, 2013
 */
public class JDFDateTimeRangeTest extends TestCase
{
	/**
	 * Class under test for void JDFDateTimeRange(JDFDateTimeRange)
	 */
	@Test
	public final void testJDFDateTimeRange()
	{
		try
		{
			final JDFDateTimeRange r = new JDFDateTimeRange(new JDFDate("2004-12-01T09:30:00Z"), new JDFDate("2004-12-01T09:35:00Z"));

			final JDFDateTimeRange test_r = new JDFDateTimeRange("2004-12-01T09:30:00Z ~ 2004-12-01T09:35:00Z");
			assertTrue("Bad Constructor: " + r.toString(), r.equals(test_r));

			final int duration = (int) ((test_r.getRight().getTimeInMillis() - test_r.getLeft().getTimeInMillis()) / 1000);
			final JDFDuration d = new JDFDuration();
			d.setDuration(duration);
			// System.out.println(duration);

			final JDFDateTimeRange r2 = new JDFDateTimeRange(new JDFDate("2004-12-01T09:30:00Z"));
			final JDFDateTimeRange r3 = new JDFDateTimeRange(r2);
			r3.setRight(r.getRight());

			assertTrue("Bad Constructor: " + r3.toString(), r3.equals(r));

		}
		catch (final DataFormatException dfe)
		{
			System.out.println(dfe.toString());
		}
	}

	/**
	 * @throws DataFormatException
	 *
	 *
	 */
	@Test
	public final void testInRange() throws DataFormatException
	{
		final JDFDateTimeRangeList rangelist = new JDFDateTimeRangeList("2004-11-30T09:30:00Z ~ 2004-12-05T09:00:00Z");

		assertTrue("Bad setString: ", rangelist.inRange(new JDFDate("2004-12-05T08:30:00Z")));
		assertTrue("Bad setString: ", rangelist.inRange(new JDFDate("2004-12-05T09:00:00Z")));
		assertFalse("Bad setString: ", rangelist.inRange(new JDFDate("2004-12-05T09:35:00Z")));
	}

	/**
	 * @throws DataFormatException
	 *
	 *
	 */
	@Test
	public final void testHash() throws DataFormatException
	{
		final JDFDateTimeRange range = new JDFDateTimeRange("2004-11-30T09:30:00Z ~ 2004-12-05T09:00:00Z");

		assertEquals(range.hashCode(), new JDFDateTimeRange(range).hashCode());
	}

	/**
	 * @throws DataFormatException
	 *
	 *
	 */
	@Test
	public final void testEq() throws DataFormatException
	{
		final JDFDateTimeRange range = new JDFDateTimeRange("2004-11-30T09:30:00Z ~ 2004-12-05T09:00:00Z");

		assertEquals(range, new JDFDateTimeRange(range));
	}

	/**
	 * @throws DataFormatException
	 *
	 *
	 */
	@Test
	public final void testCreateRange() throws DataFormatException
	{
		assertNotNull(JDFDateTimeRangeList.createDateTimeRangeList("2004-11-30T09:30:00Z ~ 2004-12-05T09:00:00Z"));
		assertNotNull(JDFDateTimeRangeList.createDateTimeRangeList("2013-11-30T09:30:00Z"));
		assertNull(JDFDateTimeRangeList.createDateTimeRangeList(""));
		assertNull(JDFDateTimeRangeList.createDateTimeRangeList(null));
		assertNull(JDFDateTimeRangeList.createDateTimeRangeList("foo"));
	}

}
