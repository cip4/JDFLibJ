/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
package org.cip4.jdflib.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author MuchaD
 *
 *         This implements the first fixture with unit tests for class JDFNodeInfo.
 */
class JDFNodeInfoTest extends JDFTestCaseBase
{
	private JDFDate currentDate;

	/**
	 * @throws Exception
	 */
	@Test
	void testDuration() throws Exception
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		final JDFNodeInfo ni = n.getCreateNodeInfo();
		final JDFDuration duration = new JDFDuration("PT1H20M30S");
		ni.setTotalDuration(duration);
		assertEquals(ni.getTotalDuration(), duration);
		try
		{
			ni.setCleanupDuration(new JDFDuration("PS1L20M30S"));
			fail("bad duration");
		}
		catch (final Exception x)
		{
			// nop
		}
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	void testPartUsage()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		final JDFNodeInfo ni = n.getCreateNodeInfo();
		final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.Separation, "Cyan");
		n.setPartStatus(map, EnumNodeStatus.Ready, null);
		final JDFNodeInfo niPart = (JDFNodeInfo) ni.getPartition(map, null);
		assertNotNull(niPart);
		assertNull(niPart.getAttribute_KElement(AttributeName.PARTUSAGE, null, null));
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testWorkstepID()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		JDFNodeInfo.setDefaultWorkStepID(true);
		final JDFNodeInfo ni = n.getCreateNodeInfo();
		assertTrue(ni.hasAttribute("WorkStepID"));
		final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.Separation, "Cyan");
		n.setPartStatus(map, EnumNodeStatus.Ready, null);
		final JDFNodeInfo niPart = (JDFNodeInfo) ni.getPartition(map, null);
		assertNotNull(niPart);
		assertTrue(niPart.hasAttribute("WorkStepID"));
		d.write2File(sm_dirTestDataTemp + "workstepidtest.jdf", 2, false);
		// VString v =
		ni.getInvalidAttributes(EnumValidationLevel.Incomplete, true, -1);
		assertTrue(ni.isValid(EnumValidationLevel.Incomplete));

	}

	/**
	 *
	 */
	@Test
	void testWorkstepIDDotPerformance()
	{
		for (int j = 0; j < 3; j++)
		{
			final JDFDoc d = new JDFDoc(ElementName.JDF);
			final JDFNode n = d.getJDFRoot();
			n.setType("ConventionalPrinting", true);

			JDFNodeInfo.setDefaultWorkStepID(j == 1);
			final JDFNodeInfo ni = n.getCreateNodeInfo();
			if (j == 1)
				assertTrue(ni.hasAttribute("WorkStepID"));
			else
				assertFalse(ni.hasAttribute("WorkStepID"));
			final long l = System.currentTimeMillis();
			final HashSet<String> s = new HashSet<String>();
			for (int i = 0; i < 1000; i++)
			{
				final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.SheetName, "Sheet" + i);
				n.setPartStatus(map, EnumNodeStatus.Ready, null);
				final JDFNodeInfo niPart = (JDFNodeInfo) ni.getPartition(map, null);
				assertNotNull(niPart);
				if (j == 1)
				{
					assertTrue(niPart.hasAttribute("WorkStepID"));
					final String workStepID = niPart.getWorkStepID();
					assertTrue(workStepID.startsWith(ni.getWorkStepID()));
					assertFalse(s.contains(workStepID));
					s.add(workStepID);
				}
				else
					assertFalse(niPart.hasAttribute("WorkStepID"));
			}
			System.out.println(j + " t: " + (System.currentTimeMillis() - l));
			// assertTrue(ni.isValid(EnumValidationLevel.Incomplete));
		}
	}

	/**
	 *
	 */
	@Test
	void testWorkstepIDDotLength()
	{
		for (int j = 0; j < 3; j++)
		{
			final JDFDoc d = new JDFDoc(ElementName.JDF);
			final JDFNode n = d.getJDFRoot();
			n.setType("ConventionalPrinting", true);
			JDFNodeInfo.setDefaultWorkStepID(j == 1);
			final JDFNodeInfo ni = n.getCreateNodeInfo();
			ni.setPartIDKeys(new VString("SignatureName SheetName PartVersion", null));
			if (j == 1)
				assertTrue(ni.hasAttribute("WorkStepID"));
			else
				assertFalse(ni.hasAttribute("WorkStepID"));
			final long l = System.currentTimeMillis();
			final HashSet<String> s = new HashSet<String>();
			for (int i = 0; i < 10; i++)
			{
				final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.SignatureName, "Sig" + i);
				for (int ii = 0; ii < 10; ii++)
				{
					map.put(EnumPartIDKey.SheetName, "Sheet" + ii);
					for (int iii = 0; iii < 10; iii++)
					{
						map.put(EnumPartIDKey.PartVersion, "PV" + iii);

						n.setPartStatus(map, EnumNodeStatus.Ready, null);
						final JDFNodeInfo niPart = (JDFNodeInfo) ni.getPartition(map, null);
						assertNotNull(niPart);
						if (j == 1)
						{
							assertTrue(niPart.hasAttribute("WorkStepID"));
							final String workStepID = niPart.getWorkStepID();
							assertTrue(workStepID.startsWith(ni.getWorkStepID()));
							assertFalse(s.contains(workStepID));
							s.add(workStepID);
						}
						else
							assertFalse(niPart.hasAttribute("WorkStepID"));
					}
				}
			}
			System.out.println(j + " t: " + (System.currentTimeMillis() - l));
			// assertTrue(ni.isValid(EnumValidationLevel.Incomplete));
		}
	}

	/**
	 *
	 */
	@Test
	void testCPI()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType("Combined", true);
		n.setTypes(new VString("ConventionalPrinting Folding", " "));

		final JDFNodeInfo ni = n.getCreateNodeInfo();
		final JDFResourceLink rl = n.getLink(ni, null);
		assertFalse(rl.hasAttribute(AttributeName.COMBINEDPROCESSINDEX));
	}

	/**
	 *
	 */
	@Test
	void testGetStart()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFNodeInfo ni = n.getCreateNodeInfo();
		final JDFDate setDate = currentDate.clone();
		ni.setFirstStart(setDate);
		assertEquals(ni.getStart(), setDate);
		setDate.addOffset(0, 0, 1, 0);
		ni.setStart(setDate);
		assertEquals(ni.getStart(), setDate);
		setDate.addOffset(0, 0, 1, 0);
		ni.setLastStart(setDate);
		setDate.addOffset(0, 0, -1, 0);
		assertEquals(ni.getStart(), setDate);
	}

	/**
	 *
	 */
	@Test
	void testGetInheritedStart()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFNodeInfo ni = n.getCreateNodeInfo();
		final JDFDate setDate = currentDate.clone();
		ni.setFirstStart(setDate);
		assertEquals(ni.getStart(), setDate);
		setDate.addOffset(0, 0, 1, 0);
		ni.setStart(setDate);
		assertEquals(ni.getStart(), setDate);
		setDate.addOffset(0, 0, 1, 0);
		ni.setLastStart(setDate);
		setDate.addOffset(0, 0, -1, 0);
		assertEquals(ni.getStart(), setDate);
	}

	/**
	*
	*/
	@Test
	void testGetEnd()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFNodeInfo ni = n.getCreateNodeInfo();
		final JDFDate setDate = currentDate.clone();
		ni.setFirstEnd(setDate);
		assertEquals(ni.getEnd(), setDate);
		setDate.addOffset(0, 0, 1, 0);
		ni.setEnd(setDate);
		assertEquals(ni.getEnd(), setDate);
		setDate.addOffset(0, 0, 1, 0);
		ni.setLastEnd(setDate);
		setDate.addOffset(0, 0, -1, 0);
		assertEquals(ni.getEnd(), setDate);
	}

	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		currentDate = new JDFDate("2015-09-26T11:43:10+03:00");
	}

}