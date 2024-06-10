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
package org.cip4.jdflib.core;

import java.util.HashSet;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.junit.jupiter.api.Assertions;
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
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		JDFNodeInfo ni = n.getCreateNodeInfo();
		final JDFDuration duration = new JDFDuration("PT1H20M30S");
		ni.setTotalDuration(duration);
		Assertions.assertEquals(ni.getTotalDuration(), duration);
		try
		{
			ni.setCleanupDuration(new JDFDuration("PS1L20M30S"));
			Assertions.fail("bad duration");
		}
		catch (Exception x)
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
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		JDFNodeInfo ni = n.getCreateNodeInfo();
		final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.Separation, "Cyan");
		n.setPartStatus(map, EnumNodeStatus.Ready, null);
		JDFNodeInfo niPart = (JDFNodeInfo) ni.getPartition(map, null);
		Assertions.assertNotNull(niPart);
		Assertions.assertNull(niPart.getAttribute_KElement(AttributeName.PARTUSAGE, null, null));
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testWorkstepID()
	{
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		JDFNodeInfo.setDefaultWorkStepID(true);
		JDFNodeInfo ni = n.getCreateNodeInfo();
		Assertions.assertTrue(ni.hasAttribute("WorkStepID"));
		final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.Separation, "Cyan");
		n.setPartStatus(map, EnumNodeStatus.Ready, null);
		JDFNodeInfo niPart = (JDFNodeInfo) ni.getPartition(map, null);
		Assertions.assertNotNull(niPart);
		Assertions.assertTrue(niPart.hasAttribute("WorkStepID"));
		d.write2File(sm_dirTestDataTemp + "workstepidtest.jdf", 2, false);
		//		VString v =
		ni.getInvalidAttributes(EnumValidationLevel.Incomplete, true, -1);
		Assertions.assertTrue(ni.isValid(EnumValidationLevel.Incomplete));

	}

	/**
	 *
	 */
	@Test
	void testWorkstepIDDotPerformance()
	{
		for (int j = 0; j < 3; j++)
		{
			JDFDoc d = new JDFDoc(ElementName.JDF);
			JDFNode n = d.getJDFRoot();
			n.setType("ConventionalPrinting", true);

			JDFNodeInfo.setDefaultWorkStepID(j == 1);
			JDFNodeInfo ni = n.getCreateNodeInfo();
			if (j == 1)
				Assertions.assertTrue(ni.hasAttribute("WorkStepID"));
			else
				Assertions.assertFalse(ni.hasAttribute("WorkStepID"));
			long l = System.currentTimeMillis();
			HashSet<String> s = new HashSet<String>();
			for (int i = 0; i < 1000; i++)
			{
				final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.SheetName, "Sheet" + i);
				n.setPartStatus(map, EnumNodeStatus.Ready, null);
				JDFNodeInfo niPart = (JDFNodeInfo) ni.getPartition(map, null);
				Assertions.assertNotNull(niPart);
				if (j == 1)
				{
					Assertions.assertTrue(niPart.hasAttribute("WorkStepID"));
					String workStepID = niPart.getWorkStepID();
					Assertions.assertTrue(workStepID.startsWith(ni.getWorkStepID()));
					Assertions.assertFalse(s.contains(workStepID));
					s.add(workStepID);
				}
				else
					Assertions.assertFalse(niPart.hasAttribute("WorkStepID"));
			}
			System.out.println(j + " t: " + (System.currentTimeMillis() - l));
			//			assertTrue(ni.isValid(EnumValidationLevel.Incomplete));
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
			JDFDoc d = new JDFDoc(ElementName.JDF);
			JDFNode n = d.getJDFRoot();
			n.setType("ConventionalPrinting", true);
			JDFNodeInfo.setDefaultWorkStepID(j == 1);
			JDFNodeInfo ni = n.getCreateNodeInfo();
			ni.setPartIDKeys(new VString("SignatureName SheetName PartVersion", null));
			if (j == 1)
				Assertions.assertTrue(ni.hasAttribute("WorkStepID"));
			else
				Assertions.assertFalse(ni.hasAttribute("WorkStepID"));
			long l = System.currentTimeMillis();
			HashSet<String> s = new HashSet<String>();
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
						JDFNodeInfo niPart = (JDFNodeInfo) ni.getPartition(map, null);
						Assertions.assertNotNull(niPart);
						if (j == 1)
						{
							Assertions.assertTrue(niPart.hasAttribute("WorkStepID"));
							String workStepID = niPart.getWorkStepID();
							Assertions.assertTrue(workStepID.startsWith(ni.getWorkStepID()));
							Assertions.assertFalse(s.contains(workStepID));
							s.add(workStepID);
						}
						else
							Assertions.assertFalse(niPart.hasAttribute("WorkStepID"));
					}
				}
			}
			System.out.println(j + " t: " + (System.currentTimeMillis() - l));
			//			assertTrue(ni.isValid(EnumValidationLevel.Incomplete));
		}
	}

	/**
	 *
	 */
	@Test
	void testCPI()
	{
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFNode n = d.getJDFRoot();
		n.setType("Combined", true);
		n.setTypes(new VString("ConventionalPrinting Folding", " "));

		JDFNodeInfo ni = n.getCreateNodeInfo();
		JDFResourceLink rl = n.getLink(ni, null);
		Assertions.assertFalse(rl.hasAttribute(AttributeName.COMBINEDPROCESSINDEX));
	}

	/**
	 *
	 */
	@Test
	void testGetStart()
	{
		JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		JDFNodeInfo ni = n.getCreateNodeInfo();
		JDFDate setDate = currentDate.clone();
		ni.setFirstStart(setDate);
		Assertions.assertEquals(ni.getStart(), setDate);
		setDate.addOffset(0, 0, 1, 0);
		ni.setStart(setDate);
		Assertions.assertEquals(ni.getStart(), setDate);
		setDate.addOffset(0, 0, 1, 0);
		ni.setLastStart(setDate);
		setDate.addOffset(0, 0, -1, 0);
		Assertions.assertEquals(ni.getStart(), setDate);
	}

	/**
	*
	*/
	@Test
	void testGetEnd()
	{
		JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		JDFNodeInfo ni = n.getCreateNodeInfo();
		JDFDate setDate = currentDate.clone();
		ni.setFirstEnd(setDate);
		Assertions.assertEquals(ni.getEnd(), setDate);
		setDate.addOffset(0, 0, 1, 0);
		ni.setEnd(setDate);
		Assertions.assertEquals(ni.getEnd(), setDate);
		setDate.addOffset(0, 0, 1, 0);
		ni.setLastEnd(setDate);
		setDate.addOffset(0, 0, -1, 0);
		Assertions.assertEquals(ni.getEnd(), setDate);
	}

	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		currentDate = new JDFDate("2015-09-26T11:43:10+03:00");
	}

}