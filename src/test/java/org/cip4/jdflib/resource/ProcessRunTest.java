/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2009 The International Cooperation for the Integration of
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
 * MediaColorTest.java
 * @author Dietrich Mucha
 *
 * Copyright (C) 2004 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.resource;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.util.JDFDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * July 20, 2009
 */
public class ProcessRunTest extends JDFTestCaseBase
{
	private JDFAuditPool ap;

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetDuration() throws Exception
	{
		final JDFProcessRun pt = ap.addProcessRun(EnumNodeStatus.Completed, null, null);
		pt.setStart(new JDFDate());
		final JDFDate end = new JDFDate();
		end.setTimeInMillis(end.getTimeInMillis() + 100 * 1000);
		pt.setEnd(end);
		Assertions.assertEquals(pt.getDuration().getDuration(), 100., 1., "");
		pt.setDurationSeconds(50);
		Assertions.assertEquals(pt.getDuration().getDuration(), 50., 1., "");

	}

	/**
	 * 
	 */
	@Test
	public void testAddPhaseTime()
	{
		final JDFProcessRun pr = ap.addProcessRun(EnumNodeStatus.Completed, null, null);
		JDFPhaseTime pt0 = null;
		for (int i = 0; i < 10; i++)
		{
			final JDFPhaseTime pt = ap.addPhaseTime(EnumNodeStatus.InProgress, null, null);
			if (i == 0)
			{
				pt0 = pt;
			}

			final JDFDate start = new JDFDate();
			start.setTimeInMillis(start.getTimeInMillis() + i * 1000 * 1000);
			pt.setStart(start);
			final JDFDate end = new JDFDate();
			end.setTimeInMillis(end.getTimeInMillis() + 100 * 1000 + i * 1000 * 1000);
			pt.setEnd(end);
			Assertions.assertEquals(pt.getDuration().getDuration(), 100, 1., "");

			pr.addPhase(pt);
			Assertions.assertEquals(pr.getDuration().getDuration(), (i + 1) * 100, 1., "");
			if (pt0 != null)
			{
				Assertions.assertEquals(pr.getStart(), pt0.getStart(), "");
			}
			Assertions.assertEquals(pr.getEnd(), pt.getEnd(), "");
		}
	}

	/**
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		ap = n.getCreateAuditPool();
	}

	/**
	 * 
	 */
	@Test
	public void testMatches()
	{
		final JDFProcessRun pr0 = ap.addProcessRun(EnumNodeStatus.Completed, null, null);
		final JDFPhaseTime pt1 = ap.addPhaseTime(EnumNodeStatus.InProgress, null, null);
		final JDFProcessRun pr1 = ap.addProcessRun(EnumNodeStatus.Completed, null, null);
		Assertions.assertTrue(pr1.matches(pt1));
		Assertions.assertFalse(pr0.matches(pt1));
		Assertions.assertFalse(pr1.matches(pr0));
	}

	/**
	 * @see junit.framework.TestCase#toString()
	 */
	@Override
	public String toString()
	{
		return "ap=" + ap;
	}
}
