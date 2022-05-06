/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2021 The International Cooperation for the Integration of
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

import java.util.List;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFPipeParams;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author MuchaD
 *
 *         This implements the first fixture with unit tests for class JDFElement.
 */
public class JDFResourceLinkTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	public void testAmount()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setVersion(JDFElement.EnumVersion.Version_1_3);
		n.setType("ConventionalPrinting", true);
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl = n.getLink(m, null);

		m.setAmount(42);
		Assertions.assertEquals(m.getAttribute(AttributeName.AMOUNT), "42", "m amount!=42");
		Assertions.assertTrue(rl.getAmount(null) == 42, "rl amount!=42");
		Assertions.assertTrue(rl.getMinAmount(null) == 42, "rl amount!=42");
		Assertions.assertTrue(rl.getMaxAmount(null) == 42, "rl amount!=42");

		rl.setAmount(44, null);
		Assertions.assertEquals(rl.getAttribute(AttributeName.AMOUNT), "44", "ml amount!=44");
		Assertions.assertTrue(rl.getAmount(null) == 44, "rl amount!=44");
		Assertions.assertTrue(rl.getMinAmount(null) == 44, "rl amount!=44");
		Assertions.assertTrue(rl.getMaxAmount(null) == 44, "rl amount!=44");
	}

	/**
	 *
	 */
	@Test
	public void testAmountPart()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setVersion(JDFElement.EnumVersion.Version_1_3);
		n.setType("ConventionalPrinting", true);
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl = n.getLink(m, null);

		rl.setAmount(42);
		rl.setPart("SheetName", "s1");
		Assertions.assertEquals(rl.getAmount(new JDFAttributeMap("SheetName", "s1")), 42.0, 0);
	}

	/**
	 *
	 */
	@Test
	public void testGetLinkedResourceName()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl = n.getLink(m, null);
		Assertions.assertEquals(rl.getLinkedResourceName(), ElementName.MEDIA);
		rl.setrRef("ddd");
		Assertions.assertEquals(rl.getLinkedResourceName(), ElementName.MEDIA);
		rl.renameElement("foo", null);
		Assertions.assertNull(rl.getLinkedResourceName());
	}

	/**
	 *
	 */
	@Test
	public void testAmountCondition()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setVersion(JDFElement.EnumVersion.Version_1_3);
		n.setType("ConventionalPrinting", true);
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl = n.getLink(m, null);
		final JDFAttributeMap map = new JDFAttributeMap("SheetName", "s1");
		final JDFAttributeMap mapC = new JDFAttributeMap(map);
		mapC.put("Condition", "Good");

		m.setAmount(42);
		Assertions.assertEquals(m.getAttribute(AttributeName.AMOUNT), "42", "m amount!=42");
		Assertions.assertEquals(rl.getAmount(null), 42., 0.1, "rl amount!=42");
		Assertions.assertEquals(rl.getMinAmount(null), 42., 0.1, "rl amount!=42");
		Assertions.assertEquals(rl.getMaxAmount(null), 42., 0.1, "rl amount!=42");

		rl.setAmount(44, map);
		Assertions.assertEquals(rl.getAmount(map), 44., 0.1, "rl amount!=42");
		Assertions.assertEquals(rl.getMinAmount(map), 44., 0.1, "rl amount!=42");
		Assertions.assertEquals(rl.getMaxAmount(map), 44., 0.1, "rl amount!=42");

		Assertions.assertEquals(rl.getAmount(mapC), -1., 0.1, "no good in map");
		Assertions.assertEquals(rl.getMinAmount(mapC), -1., 0.1, "no good in map");
		Assertions.assertEquals(rl.getMaxAmount(mapC), -1., 0.1, "no good in map");

		rl.removeChild(ElementName.AMOUNTPOOL, null, 0);
		rl.setAmount(44, mapC);
		Assertions.assertEquals(rl.getAmount(mapC), 44., 0.1, "rl amount!=42");
		Assertions.assertEquals(rl.getMinAmount(mapC), 44., 0.1, "rl amount!=42");
		Assertions.assertEquals(rl.getMaxAmount(mapC), 44., 0.1, "rl amount!=42");

		Assertions.assertEquals(rl.getAmount(map), 44., 0.1, "no good in rl");
		Assertions.assertEquals(rl.getMinAmount(map), 44., 0.1, "no good in rl");
		Assertions.assertEquals(rl.getMaxAmount(map), 44., 0.1, "no good in rl");

		final JDFAttributeMap mapW = new JDFAttributeMap(map);
		mapW.put("Condition", "Waste");
		rl.setAmount(4, mapW);
		Assertions.assertEquals(rl.getAmount(map), 48., 0.1, "sum g/w in rl");
		Assertions.assertEquals(rl.getMinAmount(map), 48., 0.1, "sum g/w in rl");
		Assertions.assertEquals(rl.getMaxAmount(map), 48., 0.1, "sum g/w in rl");
	}

	/**
	 *
	 */
	@Test
	public void testAppendAmountPool()
	{
		final JDFDoc d = new JDFDoc("MediaLink");
		final JDFResourceLink rl = (JDFResourceLink) d.getRoot();
		Assertions.assertNotNull(rl.appendAmountPool());
		try
		{
			rl.appendAmountPool();
			Assertions.fail("AmountPool #2");
		}
		catch (final JDFException x)
		{
			// nop
		}

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testDraftOK() throws Exception
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setVersion(JDFElement.EnumVersion.Version_1_3);
		n.setType("ProcessGroup", true);
		final JDFCustomerInfo ci = (JDFCustomerInfo) n.addResource(ElementName.CUSTOMERINFO, null, EnumUsage.Input, null, null, null, null);
		final JDFComponent co = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
		JDFResourceLink rl = n.getLink(ci, null);
		Assertions.assertEquals(rl.getMinStatus(), EnumResStatus.Available, "available");

		rl.setDraftOK(true);
		Assertions.assertEquals(rl.getMinStatus(), EnumResStatus.Draft, "draft");
		Assertions.assertTrue(!rl.hasAttribute(AttributeName.DRAFTOK), "has no draft");
		Assertions.assertTrue(rl.getMinLateStatus() == EnumResStatus.Draft, "late draft");
		Assertions.assertTrue(rl.isValid(EnumValidationLevel.Complete), "1.3 valid");
		rl.setDraftOK(false);
		Assertions.assertEquals(rl.getMinStatus(), EnumResStatus.Available, "draft");
		Assertions.assertTrue(!rl.hasAttribute(AttributeName.DRAFTOK), "has no draft");
		Assertions.assertTrue(rl.getMinLateStatus() == EnumResStatus.Available, "late draft");
		Assertions.assertTrue(rl.isValid(EnumValidationLevel.Complete), "1.3 valid");
		rl.removeAttribute(AttributeName.MINSTATUS);

		n.setVersion(JDFElement.EnumVersion.Version_1_2);
		rl.init();
		rl.setDraftOK(true);

		Assertions.assertTrue(rl.getMinStatus() == EnumResStatus.Draft, "draft");
		Assertions.assertTrue(rl.hasAttribute(AttributeName.DRAFTOK), "has draft");
		Assertions.assertTrue(!rl.hasAttribute(AttributeName.MINSTATUS), "has no MinStatus");
		Assertions.assertEquals(rl.getMinLateStatus(), EnumResStatus.Draft, "late draft");
		Assertions.assertTrue(rl.isValid(EnumValidationLevel.Complete), "1.2 valid");
		rl.setDraftOK(false);
		Assertions.assertTrue(rl.getMinStatus() == EnumResStatus.Available, "draft");
		Assertions.assertTrue(rl.hasAttribute(AttributeName.DRAFTOK), "has draft");
		Assertions.assertTrue(!rl.hasAttribute(AttributeName.MINSTATUS), "has no MinStatus");
		Assertions.assertEquals(rl.getMinLateStatus(), EnumResStatus.Available, "late draft");
		Assertions.assertTrue(rl.isValid(EnumValidationLevel.Complete), "1.2 valid");

		rl = n.getLink(co, null);
		Assertions.assertEquals(rl.getMinStatus(), EnumResStatus.Unavailable, "unavailable");
	}

	/**
	 *
	 */
	@Test
	public void testGetMinAmountPoolAttribute()
	{
		final JDFDoc d = new JDFDoc("TestLink");
		final JDFResourceLink rl = (JDFResourceLink) d.getRoot();
		rl.setActualAmount(12, new JDFAttributeMap("SignatureName", "1"));
		rl.setActualAmount(14, new JDFAttributeMap("SignatureName", "2"));
		Assertions.assertEquals(rl.getMinAmountPoolAttribute("ActualAmount", null, null, 42), 12., 0.);

		final JDFAttributeMap map = new JDFAttributeMap("SignatureName", "3");
		map.put("SheetName", "a");
		rl.setActualAmount(24, map);
		map.put("SheetName", "b");
		rl.setActualAmount(26, map);
		Assertions.assertEquals(rl.getMinAmountPoolAttribute("ActualAmount", null, new JDFAttributeMap("SignatureName", "3"), 42), 24., 0.);
	}

	/**
	 *
	 */
	@Test
	public void testExpandAmountPool()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setVersion(JDFElement.EnumVersion.Version_1_3);
		n.setType("ConventionalPrinting", true);
		final JDFComponent comp = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
		comp.addPartitions(EnumPartIDKey.SheetName, new VString("S1 S2 S3", null));
		final JDFResourceLink rl = n.getLink(comp, null);
		rl.setAmount(42, null);
		rl.setPipeProtocol("JDF");
		Assertions.assertEquals(rl.getAmount(null), 42., 0.1);
		Assertions.assertNull(rl.getAmountPool());
		rl.expandAmountPool();
		Assertions.assertNull(rl.getAttribute("Amount", null, null));
		Assertions.assertEquals(rl.getAmount(new JDFAttributeMap(EnumPartIDKey.SheetName, "S2")), 42., 0.1);
		Assertions.assertNotNull(rl.getAmountPool());
		Assertions.assertEquals(rl.getPipeProtocol(), "JDF");

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGenerateCPINull() throws Exception
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setVersion(JDFElement.EnumVersion.Version_1_3);
		final JDFResource r = n.addResource(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(r, null);
		rl.setrRef("dummy");
		n.setCombined(new VString("ImageSetting ConventionalPrinting", null));
		rl.generateCombinedProcessIndex();
		Assertions.assertNull(rl.getCombinedProcessIndex(), " No CPI for unlinked resLink");
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGenerateCPI() throws Exception
	{
		JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setVersion(JDFElement.EnumVersion.Version_1_3);
		JDFResource r = n.addResource(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		JDFResourceLink rl = n.getLink(r, null);
		Assertions.assertNull(rl.getCombinedProcessIndex(), " No CPI if no type");
		rl.generateCombinedProcessIndex();
		Assertions.assertNull(rl.getCombinedProcessIndex(), " No CPI if no type");

		n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setVersion(JDFElement.EnumVersion.Version_1_3);
		n.setType(EnumType.ProcessGroup);
		r = n.addResource(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		rl = n.getLink(r, null);
		Assertions.assertNull(rl.getCombinedProcessIndex(), " No CPI for gray box");
		rl.generateCombinedProcessIndex();
		Assertions.assertNull(rl.getCombinedProcessIndex(), " No CPI for gray box");

		n.setCombined(new VString("ImageSetting ConventionalPrinting", null));
		rl.generateCombinedProcessIndex();
		Assertions.assertEquals(rl.getCombinedProcessIndex(), new JDFIntegerList("1"));
		n.setCombined(new VString("ImageSetting ConventionalPrinting ConventionalPrinting", null));
		rl.generateCombinedProcessIndex();
		Assertions.assertEquals(rl.getCombinedProcessIndex(), new JDFIntegerList("1 2"));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGenerateCPIComponent() throws Exception
	{
		JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		JDFResource r = n.addResource(ElementName.COMPONENT, EnumUsage.Input);
		JDFResource r2 = n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		JDFResourceLink rl = n.getLink(r, null);
		JDFResourceLink rl2 = n.getLink(r2, null);
		Assertions.assertNull(rl.getCombinedProcessIndex(), " No CPI if no type");
		Assertions.assertNull(rl2.getCombinedProcessIndex(), " No CPI if no type");
		rl.generateCombinedProcessIndex();
		Assertions.assertNull(rl.getCombinedProcessIndex(), " No CPI if no type");
		Assertions.assertNull(rl2.getCombinedProcessIndex(), " No CPI if no type");

		n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setVersion(JDFElement.EnumVersion.Version_1_3);
		n.setType(EnumType.ProcessGroup);
		r = n.addResource(ElementName.COMPONENT, EnumUsage.Input);
		r2 = n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		rl = n.getLink(r, null);
		rl2 = n.getLink(r2, null);
		Assertions.assertNull(rl.getCombinedProcessIndex(), " No CPI for gray box");
		rl.generateCombinedProcessIndex();
		Assertions.assertNull(rl.getCombinedProcessIndex(), " No CPI for gray box");

		n.setCombined(new VString("ConventionalPrinting", null));
		rl.generateCombinedProcessIndex();
		Assertions.assertEquals(rl.getCombinedProcessIndex(), new JDFIntegerList("0"));
		rl2.generateCombinedProcessIndex();
		Assertions.assertEquals(rl2.getCombinedProcessIndex(), new JDFIntegerList("0"));

		n.setCombined(new VString("ImageSetting ConventionalPrinting", null));
		rl.generateCombinedProcessIndex();
		Assertions.assertEquals(rl.getCombinedProcessIndex(), new JDFIntegerList("1"));
		rl2.generateCombinedProcessIndex();
		Assertions.assertEquals(rl2.getCombinedProcessIndex(), new JDFIntegerList("1"));
		n.setCombined(new VString("ImageSetting ConventionalPrinting ConventionalPrinting", null));
		rl.generateCombinedProcessIndex();
		rl2.generateCombinedProcessIndex();
		Assertions.assertEquals(rl.getCombinedProcessIndex(), new JDFIntegerList("1"));
		Assertions.assertEquals(rl2.getCombinedProcessIndex(), new JDFIntegerList("2"));
	}

	/**
	 *
	 */
	@Test
	public void testGetAmountPoolDouble()
	{
		final JDFDoc d = new JDFDoc("TestLink");
		final JDFResourceLink rl = (JDFResourceLink) d.getRoot();
		rl.setActualAmount(12, new JDFAttributeMap("SignatureName", "1"));
		rl.setActualAmount(14, new JDFAttributeMap("SignatureName", "2"));
		Assertions.assertEquals(rl.getActualAmount(null), 26., 0.1);
		rl.setAmount(42., null);
		Assertions.assertEquals(rl.getAmount(new JDFAttributeMap("SignatureName", "2")), 42., 0.1, "root attribute is incorrectly retrieved");
		rl.removeChild(ElementName.AMOUNTPOOL, null, 0);
		rl.setActualAmount(33, null);
		Assertions.assertEquals(rl.getActualAmount(null), 33., 0.1);
	}

	/**
	 *
	 */
	@Test
	public void testGetAmountPoolSumDouble()
	{
		final JDFNode d = new JDFDoc("JDF").getJDFRoot();
		final JDFResource m = d.addResource("Media", EnumUsage.Input);
		m.addPartition(EnumPartIDKey.SignatureName, "1");
		final JDFResourceLink rl = d.getLink(m, EnumUsage.Input);
		final JDFAttributeMap map = new JDFAttributeMap("SignatureName", "1");
		map.put("Condition", "Good");

		rl.setActualAmount(12, map);
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(new JDFAttributeMap("SignatureName", "1"));
		JDFAttributeMap sig1 = new JDFAttributeMap("SignatureName", "2");
		vMap.add(sig1);

		rl.getAmountPool().getPartAmount(map).setPartMapVector(vMap);
		map.put("Condition", "Waste");
		rl.setActualAmount(14, map);
		Assertions.assertEquals(rl.getAmountPoolSumDouble(AttributeName.ACTUALAMOUNT, null), 26., 0.1);
		Assertions.assertEquals(rl.getAmountPoolSumDouble(AttributeName.ACTUALAMOUNT, vMap), 26., 0.1);
		sig1.put("SheetName", "S1");
		Assertions.assertEquals(rl.getAmountPoolSumDouble(AttributeName.ACTUALAMOUNT, vMap), 26., 0.1, " also when more granular");
		sig1 = new JDFAttributeMap(sig1);
		sig1.put("SheetName", "S2");
		vMap.add(sig1);
		Assertions.assertEquals(rl.getAmountPoolSumDouble(AttributeName.ACTUALAMOUNT, vMap), 26., 0.1, " also when more granular");

	}

	/**
	 *
	 */
	@Test
	public void testGetAmountPoolVector()
	{
		final JDFDoc d = new JDFDoc("TestLink");
		final JDFResourceLink rl = (JDFResourceLink) d.getRoot();
		final VJDFAttributeMap vM = new VJDFAttributeMap();
		vM.add(new JDFAttributeMap("SignatureName", "1"));
		vM.add(new JDFAttributeMap("SignatureName", "2"));
		rl.setAmountPoolAttribute("Amount", "42", null, vM);

		Assertions.assertEquals(rl.getAmountPoolAttribute("Amount", null, vM), "42");
		vM.add(new JDFAttributeMap("SignatureName", "3"));
		Assertions.assertNull(rl.getAmountPoolAttribute("Amount", null, vM));

	}

	/**
	 * tests whether the convoluted inheritence of partAmount and ResourceLink function correctly
	 *
	 * @throws Exception
	 */
	@Test
	public void testPartAmount() throws Exception
	{

		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setVersion(JDFElement.EnumVersion.Version_1_3);
		n.setType("ConventionalPrinting", true);
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, null, EnumUsage.Input, null, null, null, null);
		final JDFExposedMedia xm1 = (JDFExposedMedia) xm.addPartition(JDFResource.EnumPartIDKey.SheetName, "Sheet1");
		xm.addPartition(JDFResource.EnumPartIDKey.SheetName, "Sheet2");
		xm1.setAmount(1.);
		xm.setAmount(2.);
		final JDFAttributeMap map1 = new JDFAttributeMap(JDFResource.EnumPartIDKey.SheetName.getName(), "Sheet1");
		final JDFAttributeMap map2 = new JDFAttributeMap(JDFResource.EnumPartIDKey.SheetName.getName(), "Sheet2");

		final JDFResourceLink rl = n.getLink(xm, null);
		final JDFPartAmount pa = rl.getCreateAmountPool().getCreatePartAmount(new JDFAttributeMap("SheetName", "Sheet1"));
		pa.setDraftOK(true);
		Assertions.assertTrue(pa.getMinStatus() == EnumResStatus.Draft, "draft");
		Assertions.assertTrue(!pa.hasAttribute(AttributeName.DRAFTOK), "has no draft");
		Assertions.assertTrue(pa.getMinLateStatus() == EnumResStatus.Draft, "late draft");
		pa.removeAttribute(AttributeName.MINSTATUS);
		n.setVersion(JDFElement.EnumVersion.Version_1_2);
		pa.setDraftOK(true);
		Assertions.assertTrue(pa.getMinStatus() == EnumResStatus.Draft, "draft");
		Assertions.assertTrue(pa.hasAttribute(AttributeName.DRAFTOK), "has draft");
		Assertions.assertTrue(!pa.hasAttribute(AttributeName.MINSTATUS), "has no MinStatus");
		Assertions.assertTrue(pa.getMinLateStatus() == EnumResStatus.Draft, "late draft");

		rl.setMinAmount(42., map1);
		Assertions.assertEquals(rl.getMinAmount(map1), 42., 0., "map1min");
		Assertions.assertEquals(rl.getMaxAmount(map1), 1., 0., "map1max");
		Assertions.assertEquals(rl.getMinAmount(map2), 2., 0., "map2min"); // last default
		Assertions.assertEquals(rl.getMaxAmount(null), 2., 0., "map0min"); // last default
		pa.setAmount(55, null);
		Assertions.assertEquals(pa.getAmount(null), 55., 0., "pa amount");

	}

	/**
	 * tests whether the convoluted inheritence of partAmount and ResourceLink function correctly
	 *
	 */
	@Test
	public void testPartAmountVirtual()
	{

		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setVersion(JDFElement.EnumVersion.Version_1_3);
		n.setType("ConventionalPrinting", true);
		final JDFComponent comp = (JDFComponent) n.appendMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyOutput, null);

		final JDFResourceLink cpLink = n.getLink(comp, null);
		final JDFAttributeMap mapBad = new JDFAttributeMap("Condition", "Waste");
		cpLink.setActualAmount(42, mapBad);
		Assertions.assertEquals(cpLink.getActualAmount(mapBad), 42., 0);
		Assertions.assertTrue(n.isValid(EnumValidationLevel.Incomplete), "allow partamounts to non-existing partitions");

		cpLink.removeChild(ElementName.AMOUNTPOOL, null, 0);
		comp.addPartition(EnumPartIDKey.SheetName, "Sheet1");
		mapBad.put(EnumPartIDKey.SheetName.getName(), "Sheet1");
		cpLink.setActualAmount(42, mapBad);
		Assertions.assertEquals(cpLink.getActualAmount(mapBad), 42., 0);
		Assertions.assertTrue(n.isValid(EnumValidationLevel.Incomplete), "allow partamounts to non-existing partitions");
		mapBad.put(EnumPartIDKey.SheetName.getName(), "Sheet2");
		Assertions.assertEquals(cpLink.getActualAmount(mapBad), 0., 0);
		final JDFAttributeMap m2 = new JDFAttributeMap("Condition", "Waste");
		Assertions.assertEquals(cpLink.getActualAmount(m2), 42., 0);
		m2.put("SheetName", "Sheet1");
		Assertions.assertEquals(cpLink.getActualAmount(m2), 42., 0);
		m2.put("Side", "Front");
		Assertions.assertEquals(cpLink.getActualAmount(m2), 0., 0);

	}

	/**
	 *
	 */
	@Test
	public void testSetAmountPoolAttribute()
	{
		final JDFDoc d = new JDFDoc("ResourceLinkPool");
		final JDFResourceLinkPool rlp = (JDFResourceLinkPool) d.getRoot();
		final JDFResourceLink foo = (JDFResourceLink) rlp.appendElement("FooLink");
		VJDFAttributeMap vPart = new VJDFAttributeMap();
		vPart.add(new JDFAttributeMap());
		foo.setAmountPoolAttribute("blub", "123", null, vPart);
		Assertions.assertEquals(foo.getAttribute("blub"), "123");
		vPart = new VJDFAttributeMap();
		final JDFAttributeMap map = new JDFAttributeMap("SheetName", "b");
		vPart.add(map);
		foo.setAmountPoolAttribute("blub", "123", null, vPart);
		Assertions.assertNull(foo.getAttribute("blub", null, null));
		Assertions.assertEquals(foo.getAmountPoolAttribute("blub", null, map, 0), "123");

	}

	/**
	 * Method testGetLinkRoot.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetLinkRootJMF() throws Exception
	{
		final JDFDoc d = new JDFDoc("JMF");
		final JDFJMF jmf = d.getJMFRoot();
		jmf.setSenderID("Elvis");
		final JDFCommand c = jmf.appendCommand();
		c.setType("PipePull");
		final JDFPipeParams pp = c.appendPipeParams();
		pp.setAttribute(AttributeName.PIPEID, "foo", null);
		final JDFRunList ruli = (JDFRunList) pp.appendResource(ElementName.RUNLIST);
		final JDFResourceLink rl = pp.appendResourceLink("RunListLink", true);
		rl.setrRef(ruli.getID());
		Assertions.assertTrue(jmf.isValid(EnumValidationLevel.Complete), "valid param");
		Assertions.assertEquals(ruli, rl.getTarget());
	}

	/**
	 * Method testGetTarget
	 *
	 */
	@Test
	public void testGetLeafVector()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource("ExposedMedia", null);
		final JDFResourceLink rl = n.ensureLink(xm, EnumUsage.Input, null);
		Assertions.assertEquals(rl.getLeafVector().get(0), xm);
		Assertions.assertEquals(rl.getLeafVector().size(), 1);

		final JDFExposedMedia[] vxm = new JDFExposedMedia[5];
		for (int i = 0; i < 5; i++)
		{
			vxm[i] = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.SheetName, "S" + i);
		}
		Assertions.assertEquals(rl.getLeafVector().size(), 5);
		Assertions.assertEquals(rl.getLeafVector().get(3), vxm[3]);
		rl.appendPart().setPartMap(new JDFAttributeMap("SheetName", "S1"));
		Assertions.assertEquals(rl.getLeafVector().size(), 1);
		Assertions.assertEquals(rl.getLeafVector().get(0), vxm[1]);

		rl.appendPart().setPartMap(new JDFAttributeMap("SheetName", "S3"));
		Assertions.assertEquals(rl.getLeafVector().size(), 2);
		Assertions.assertEquals(rl.getLeafVector().get(1), vxm[3]);

		rl.removeChildren("Part", null, null);
		final JDFAttributeMap map = new JDFAttributeMap("SheetName", "S4");
		map.put("Separation", "green");
		rl.appendPart().setPartMap(map);

		Assertions.assertNull(rl.getLeafVector());

	}

	/**
	 * Method testGetTarget * @throws Exception
	 */
	@Test
	public void testGetTargetVector()
	{
		final JDFDoc d = JDFTestCaseBase.creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "S1");
		final JDFResourceLink rl = n.getMatchingLink("ExposedMedia", EnumProcessUsage.Plate, 0);
		rl.setPartMap(mPart);
		final VElement v = rl.getTargetVector(0);
		Assertions.assertEquals(v.size(), 1, "The target vector is the node with two leaves");
	}

	/**
	 * Method testGetTarget * @throws Exception
	 */
	@Test
	public void testGetTargetList()
	{
		final JDFDoc d = JDFTestCaseBase.creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "S1");
		final JDFResourceLink rl = n.getMatchingLink("ExposedMedia", EnumProcessUsage.Plate, 0);
		rl.setPartMap(mPart);
		final List<JDFResource> v = rl.getTargetList();
		Assertions.assertEquals(v.size(), 1, "The target vector is the node with two leaves");
	}

	/**
	 * Method testGetTarget * @throws Exception
	 */
	@Test
	public void testGetTargetVectorPartVersion()
	{
		final JDFDoc d = JDFTestCaseBase.creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		final JDFAttributeMap mPartLink = new JDFAttributeMap("PartVersion", "En");
		mPart.put("SheetName", "S1");
		mPart.put("Side", "Front");
		mPart.put("PartVersion", "En Fr");
		final JDFResourceLink rl = n.getMatchingLink("ExposedMedia", EnumProcessUsage.Plate, 0);
		final JDFResource r = rl.getTarget();
		rl.setPartMap(mPartLink);
		r.getCreatePartition(mPart, null);
		mPart.put("PartVersion", "En DK");
		r.getCreatePartition(mPart, null);
		final VElement v = rl.getTargetVector(-1);
		Assertions.assertEquals(v.size(), 2, "The target vector has two pv nodes");
	}

	/**
	 * Method testGetTarget * @throws Exception
	 */
	@Test
	public void testGetStatusFromLeaves()
	{
		final JDFDoc d = JDFTestCaseBase.creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		final JDFAttributeMap mPartLink = new JDFAttributeMap("PartVersion", "En");
		mPart.put("SheetName", "S1");
		mPart.put("Side", "Front");
		mPart.put("PartVersion", "En Fr");
		final JDFResourceLink rl = n.getMatchingLink("ExposedMedia", EnumProcessUsage.Plate, 0);

		Assertions.assertEquals(EnumResStatus.Unavailable, rl.getStatusFromLeaves());
		final JDFResource r = rl.getTarget();
		r.getResourceRoot().setResStatus(EnumResStatus.Available, true);
		Assertions.assertEquals(EnumResStatus.Available, rl.getStatusFromLeaves());
		rl.setPartMap(mPartLink);
		r.getCreatePartition(mPart, null);
		mPart.put("PartVersion", "En DK");
		r.getCreatePartition(mPart, null).setResStatus(EnumResStatus.Draft, true);
		Assertions.assertEquals(EnumResStatus.Draft, rl.getStatusFromLeaves());
		mPartLink.put(AttributeName.SHEETNAME, "nixda");
		r.getResourceRoot().setPartUsage(EnumPartUsage.Explicit);
		rl.setPartMap(mPartLink);
		Assertions.assertEquals(EnumResStatus.Incomplete, rl.getStatusFromLeaves());
	}

	/**
	 * Method testGetTarget * @throws Exception
	 */
	@Test
	public void testGetTargetPartVersion()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFResource comp = n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "S1");
		mPart.put("PartVersion", "En");
		final JDFAttributeMap mPartLink = mPart.clone();
		mPartLink.put("PartVersion", "En En");
		final JDFResourceLink rl = n.getLink(comp, null);
		final JDFResource leaf = comp.getCreatePartition(mPart, new VString("SignatureName SheetName PartVersion", null));
		rl.setPartMap(mPartLink);

		Assertions.assertEquals(rl.getTarget(), leaf);
		leaf.setPartVersion("En En");
		rl.setPartMap(mPart);
		Assertions.assertEquals(rl.getTarget(), leaf, "swapped");
	}

	/**
	 * Method testGetTarget * @throws Exception
	 */
	@Test
	public void testGetTargetPartVersionMulti()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFResource comp = n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "S1");
		mPart.put("PartVersion", "En");
		final JDFAttributeMap mPartLink = mPart.clone();
		mPartLink.put("PartVersion", "En En");
		final JDFResourceLink rl = n.getLink(comp, null);
		final VJDFAttributeMap vLink = new VJDFAttributeMap();
		vLink.add(mPartLink.clone());
		comp.getCreatePartition(mPart, new VString("SignatureName SheetName PartVersion", null));
		mPart.put("PartVersion", "Fr");
		mPartLink.put("PartVersion", "Fr Fr");
		comp.getCreatePartition(mPart, new VString("SignatureName SheetName PartVersion", null));
		vLink.add(mPartLink.clone());

		rl.setPartMapVector(vLink);
		mPart.remove("PartVersion");

		Assertions.assertEquals(rl.getTarget(), comp.getPartition(mPart, EnumPartUsage.Explicit));
	}

	/**
	 * Method testGetTarget * @throws Exception
	 */
	@Test
	public void testGetTargetVectorMultiPartVersion()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFAttributeMap mPartLink = new JDFAttributeMap("PartVersion", "v1");
		final JDFAttributeMap mPartLink2 = new JDFAttributeMap("PartVersion", "v2");
		final JDFResource r = n.addResource(ElementName.COMPONENT, EnumUsage.Input);
		r.addPartition(EnumPartIDKey.PartVersion, "v1");
		r.addPartition(EnumPartIDKey.PartVersion, "v2");
		final JDFResourceLink rl = n.getLink(r, null);
		final VJDFAttributeMap vm = new VJDFAttributeMap();
		vm.add(mPartLink);
		vm.add(mPartLink2);
		rl.setPartMapVector(vm);
		final VElement v = rl.getTargetVector(-1);
		Assertions.assertEquals(v.size(), 2, "The target vector has two pv nodes");
	}

	/**
	 *
	 */
	@Test
	public void testGetTargetVectorIdentical()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFResource r = n.addResource("ExposedMedia", EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(r, EnumUsage.Input);
		final JDFResource s1 = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource s2 = r.addPartition(EnumPartIDKey.SheetName, "s2");
		final VString seps = new VString("CYAN MAGENTA YELLOW BLACK", null);
		final VJDFAttributeMap vm = new VJDFAttributeMap();
		final JDFAttributeMap m1 = new JDFAttributeMap(EnumPartIDKey.SheetName, "s1");
		final JDFAttributeMap m2 = new JDFAttributeMap(EnumPartIDKey.SheetName, "s2");
		for (final String sep : seps)
		{
			final JDFResource s11 = s1.addPartition(EnumPartIDKey.Separation, sep);
			final JDFResource s21 = s2.addPartition(EnumPartIDKey.Separation, sep);

			JDFAttributeMap m = new JDFAttributeMap(m1);
			m.put(EnumPartIDKey.Separation, sep);
			vm.add(m);
			m = new JDFAttributeMap(m2);
			m.put(EnumPartIDKey.Separation, sep);
			vm.add(m);
			s21.setIdentical(s11);

		}

		VElement v = r.getPartitionVector((VJDFAttributeMap) null, null);
		Assertions.assertEquals(v.size(), 1, "explicit identicals are excluded");
		v = r.getPartitionVector(vm, null);
		Assertions.assertEquals(v.size(), 4);

		rl.setPartMapVector(vm);
		v = rl.getTargetVector(0);
		Assertions.assertEquals(v.size(), 4);
	}

	/**
	 *
	 */
	@Test
	public void testGetRawTargetVectorIdentical()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFResource r = n.addResource("ExposedMedia", EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(r, EnumUsage.Input);
		final JDFResource s1 = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource s2 = r.addPartition(EnumPartIDKey.SheetName, "s2");
		final VString seps = new VString("CYAN MAGENTA YELLOW BLACK", null);
		final VJDFAttributeMap vm = new VJDFAttributeMap();
		final JDFAttributeMap m1 = new JDFAttributeMap(EnumPartIDKey.SheetName, "s1");
		final JDFAttributeMap m2 = new JDFAttributeMap(EnumPartIDKey.SheetName, "s2");
		for (final String sep : seps)
		{
			final JDFResource s11 = s1.addPartition(EnumPartIDKey.Separation, sep);
			final JDFResource s21 = s2.addPartition(EnumPartIDKey.Separation, sep);

			JDFAttributeMap m = new JDFAttributeMap(m1);
			m.put(EnumPartIDKey.Separation, sep);
			vm.add(m);
			m = new JDFAttributeMap(m2);
			m.put(EnumPartIDKey.Separation, sep);
			vm.add(m);
			s21.setIdentical(s11);

		}

		VElement v = r.getPartitionVector((VJDFAttributeMap) null, null);
		Assertions.assertEquals(v.size(), 1, "explicit identicals are excluded");
		v = r.getPartitionVector(vm, null);
		Assertions.assertEquals(v.size(), 4);

		rl.setPartMapVector(vm);
		v = rl.getRawTargetVector(0);
		Assertions.assertEquals(v.size(), 8);
	}

	/**
	 * Method testGetTarget
	 *
	 */
	@Test
	public void testGetTargetVectorSparse()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getCreateResource("ExposedMedia", EnumUsage.Input, 0);
		final JDFExposedMedia xmb = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.Separation, "Black");
		final JDFExposedMedia xmbe = (JDFExposedMedia) xmb.addPartition(EnumPartIDKey.PartVersion, "EN");
		final JDFExposedMedia xmbd = (JDFExposedMedia) xmb.addPartition(EnumPartIDKey.PartVersion, "DE");
		final JDFExposedMedia xmc = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.Separation, "Cyan");
		xm.setPartUsage(EnumPartUsage.Sparse);
		final JDFResourceLink rl = n.getLink(xm, null);
		rl.setPartMap(new JDFAttributeMap("PartVersion", "DE"));
		VElement v = rl.getTargetVector(-1);
		Assertions.assertEquals(v.size(), 2, "DE/Black + Cyan");
		Assertions.assertEquals(v.get(0), xmbd);
		Assertions.assertEquals(v.get(1), xmc);
		rl.setPartMap(new JDFAttributeMap("PartVersion", "EN"));
		v = rl.getTargetVector(-1);
		Assertions.assertEquals(v.size(), 2);
		Assertions.assertEquals(v.get(0), xmbe);
		Assertions.assertEquals(v.get(1), xmc);
		rl.setPartMap(new JDFAttributeMap("PartVersion", "FR"));
		v = rl.getTargetVector(-1);
		Assertions.assertEquals(v.size(), 1);
		Assertions.assertEquals(v.get(0), xmc);
		xm.setPartUsage(EnumPartUsage.Implicit);
		v = rl.getTargetVector(-1);
		Assertions.assertEquals(v.size(), 2);
		// TODO should be xmb?
		Assertions.assertEquals(v.get(0), xmb);
		Assertions.assertEquals(v.get(1), xmc);
	}

	/**
	 * Method testGetTarget
	 *
	 */
	@Test
	public void testGetTargetVectorNullPart()
	{
		final JDFDoc d = JDFTestCaseBase.creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		final JDFResourceLink rl = n.getMatchingLink("ExposedMedia", EnumProcessUsage.Plate, 0);
		rl.appendPart();
		VElement v = rl.getTargetVector(0);
		Assertions.assertEquals(v.size(), 1, "The target vector is the node with two leaves");
		final JDFResource linkRoot = rl.getLinkRoot();
		Assertions.assertEquals(v.elementAt(0), linkRoot, "The target vector is the node with two leaves");
		linkRoot.setPartUsage(EnumPartUsage.Implicit);
		v = rl.getTargetVector(0);
		Assertions.assertEquals(v.size(), 1, "The target vector is the node with two leaves");
		Assertions.assertEquals(v.elementAt(0), linkRoot, "The target vector is the node with two leaves");

		linkRoot.setPartUsage(EnumPartUsage.Explicit);

		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "S1");
		rl.setPartMap(mPart);
		rl.appendPart();

		v = rl.getTargetVector(0);
		Assertions.assertEquals(v.size(), 2, "The target vector is the node with two leaves");
		Assertions.assertTrue(v.contains(linkRoot));
		Assertions.assertTrue(v.contains(linkRoot.getPartition(mPart, null)));
		linkRoot.setPartUsage(EnumPartUsage.Implicit);
		v = rl.getTargetVector(0);
		Assertions.assertTrue(v.contains(linkRoot));
		Assertions.assertTrue(v.contains(linkRoot.getPartition(mPart, null)));
	}

	/**
	 * Method testGetTarget
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetTarget() throws Exception
	{
		final JDFDoc d = JDFTestCaseBase.creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SignatureName", "S12234");
		mPart.put("SheetName", "S12");
		mPart.put("Side", "Front");

		final JDFAttributeMap mPart2 = new JDFAttributeMap("SignatureName", "Sig1");
		mPart2.put("SignatureName", "Sig1");
		mPart2.put("SheetName", "S1");
		mPart2.put("Side", "Front");
		final JDFExposedMedia xmPart = (JDFExposedMedia) xm.getPartition(mPart2, null);
		Assertions.assertNotNull(xmPart);

		final JDFResourceLink rl = n.getMatchingLink("ExposedMedia", EnumProcessUsage.Plate, 0);

		rl.setPartMap(mPart);
		Assertions.assertNull(rl.getTarget());
		Assertions.assertEquals(rl.getTargetVector(0).size(), 0);

		xm.setPartUsage(EnumPartUsage.Explicit);
		Assertions.assertNull(rl.getTarget());
		Assertions.assertEquals(rl.getTargetVector(0).size(), 0);

		xm.setPartUsage(EnumPartUsage.Implicit);
		Assertions.assertEquals(rl.getTarget(), xm);
		Assertions.assertEquals(rl.getTargetVector(0).size(), 1);

		xm.setPartUsage(EnumPartUsage.Sparse);
		Assertions.assertEquals(rl.getTarget(), null);
		Assertions.assertEquals(rl.getTargetVector(0).size(), 0);

		rl.setPartMap(mPart2);
		xm.removeAttribute("PartUsage");
		Assertions.assertEquals(xmPart, rl.getTarget());
		Assertions.assertEquals(rl.getTargetVector(0).size(), 1);

		xm.setPartUsage(EnumPartUsage.Explicit);
		Assertions.assertEquals(xmPart, rl.getTarget());
		Assertions.assertEquals(rl.getTargetVector(0).size(), 1);

		xm.setPartUsage(EnumPartUsage.Implicit);
		Assertions.assertEquals(rl.getTarget(), xmPart);
		Assertions.assertEquals(rl.getTargetVector(0).size(), 1);

		xm.setPartUsage(EnumPartUsage.Sparse);
		Assertions.assertEquals(rl.getTarget(), xmPart);
		Assertions.assertEquals(rl.getTargetVector(0).size(), 1);

		mPart2.put("PartVersion", "Fnarf");
		rl.setPartMap(mPart2);
		xm.removeAttribute("PartUsage");
		Assertions.assertEquals(null, rl.getTarget());
		Assertions.assertEquals(rl.getTargetVector(0).size(), 0);

		xm.setPartUsage(EnumPartUsage.Explicit);
		Assertions.assertEquals(null, rl.getTarget());
		Assertions.assertEquals(rl.getTargetVector(0).size(), 0);

		xm.setPartUsage(EnumPartUsage.Implicit);
		Assertions.assertEquals(rl.getTarget(), xmPart);
		Assertions.assertEquals(rl.getTargetVector(0).size(), 1);

		xm.setPartUsage(EnumPartUsage.Sparse);
		Assertions.assertEquals(rl.getTarget(), xmPart);
		Assertions.assertEquals(rl.getTargetVector(0).size(), 1);
	}

	/**
	 * Method testGetTarget
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetTargetIntermediate() throws Exception
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFResource xm = n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		xm.setPartUsage(EnumPartUsage.Implicit);
		xm.setPartUsage(EnumPartUsage.Implicit);
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		xm.getCreatePartition(mPart, null);
		mPart.put("SheetName", "S12");
		xm.getCreatePartition(mPart, null);
		final JDFExposedMedia xmSheet = (JDFExposedMedia) xm.getPartition(mPart, null);
		final JDFAttributeMap m2 = mPart.clone();
		mPart.put("Side", "Front");
		xm.getCreatePartition(mPart, null);

		final JDFResourceLink rl = n.getLink(xm, null);
		rl.setPartMap(m2);

		Assertions.assertEquals(xmSheet, rl.getTarget());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetCombinedProcessTypes() throws Exception
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType("Combined", true);
		n.setTypes(new VString("a b c d e f e f", " "));
		final JDFResource r = n.addResource(ElementName.ADHESIVEBINDINGPARAMS, EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(r, null);
		final VString nodeTypes = n.getTypes();
		nodeTypes.unify();
		Assertions.assertEquals(rl.getCombinedProcessTypes(), nodeTypes);
		rl.setCombinedProcessType("c");
		Assertions.assertEquals(rl.getCombinedProcessTypes(), new VString("c", " "));
		rl.removeAttribute(AttributeName.COMBINEDPROCESSTYPE);
		Assertions.assertEquals(rl.getCombinedProcessTypes(), nodeTypes);
		rl.setCombinedProcessIndex(new JDFIntegerList("0 2 4 6"));
		Assertions.assertEquals(rl.getCombinedProcessTypes(), new VString("a c e", " "));
		rl.setCombinedProcessIndex(new JDFIntegerList("0 2 4 6 8 99"));
		Assertions.assertEquals(rl.getCombinedProcessTypes(), new VString("a c e", " "));
	}

	/**
	 * Method testGetLinkRootJMF
	 *
	 */
	@Test
	public void testGetLinkRoot()
	{

		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType("ProcessGroup", true);
		final JDFNode n2 = n.addJDFNode("ConventionalPrinting");
		final JDFCustomerInfo ci = (JDFCustomerInfo) n.addResource(ElementName.CUSTOMERINFO, null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink ciLink = n.getLink(ci, null);
		Assertions.assertTrue(ci == ciLink.getLinkRoot(), "getLinkRoot in same node");
		Assertions.assertTrue(ci == ciLink.getTarget(), "getLinkTarget in same node");
		Assertions.assertTrue(ci == ciLink.getTarget(), "getTarget in same node");

		final JDFResourceLink ciLink2 = n2.linkResource(ci, EnumUsage.Input, null);
		Assertions.assertTrue(ci == ciLink2.getLinkRoot(), "getLinkRoot in child node");
		Assertions.assertTrue(ci == ciLink2.getTarget(), "getLinkTarget in child node");
		Assertions.assertTrue(ci == ciLink2.getTarget(), "getTarget in child node");

		final JDFNodeInfo ni = (JDFNodeInfo) n2.addResource(ElementName.NODEINFO, null, null, null, null, null, null);
		final JDFResourceLink niLink = n2.linkResource(ni, true ? EnumUsage.Input : EnumUsage.Output, null);
		Assertions.assertTrue(ni == niLink.getLinkRoot(), "getLinkRoot both in child node");
		Assertions.assertTrue(ni == niLink.getTarget(), "getLinkTarget both in child node");
		Assertions.assertTrue(ni == niLink.getTarget(), "getTarget both in child node");

		final JDFResourceLink niLink2 = (JDFResourceLink) n.getCreateResourceLinkPool().appendElement("NodeInfoLink", null);
		niLink2.setrRef(ni.getID());
		Assertions.assertTrue(niLink2.getLinkRoot() == null, "getLinkRoot illegal in child node");
		Assertions.assertTrue(niLink2.getTarget() == null, "getLinkTarget illegal in child node");
		Assertions.assertTrue(niLink2.getTarget() == null, "getTarget illegal in child node");

		final JDFDoc d22 = new JDFDoc(ElementName.JDF);
		final JDFNode n22 = d22.getJDFRoot();
		final JDFResourceLinkPool rlp = n22.getCreateResourceLinkPool();
		final JDFResourceLink rl = rlp.linkResource(ni, true ? EnumUsage.Input : EnumUsage.Output, null);
		Assertions.assertNull(rl);
		Assertions.assertNull(rlp.getElement("NodeInfoLink"), "NI not linked");

	}

	/**
	 *
	 */
	@Test
	public void testLinkRootDeadLoop()
	{
		final JDFDoc jdfDoc = new JDFDoc("JDF");
		final JDFNode node = jdfDoc.getJDFRoot();
		final JDFResource r = node.addResource(ElementName.ADHESIVEBINDINGPARAMS, EnumUsage.Input);
		node.getResourcePool().insertBefore(ElementName.ADHESIVEBINDINGPARAMS + "Ref", r, null).setAttribute("rRef", "badLink");
		final JDFResourceLink link = node.getLink(r, null);
		Assertions.assertNotNull(link.getLinkRoot()); // Endlos-Schleife !!!!
	}

	/**
	 *
	 */
	@Test
	public void testMatchesString()
	{
		final JDFDoc jdfDoc = new JDFDoc("JDF");
		final JDFNode node = jdfDoc.getJDFRoot();
		final JDFResource r = node.addResource(ElementName.ADHESIVEBINDINGPARAMS, EnumUsage.Input);
		final JDFResourceLink link = node.getLink(r, null);
		Assertions.assertTrue(link.matchesString(ElementName.ADHESIVEBINDINGPARAMS));
		Assertions.assertTrue(link.matchesString("Input"));
		Assertions.assertFalse(link.matchesString("Output"));
		Assertions.assertTrue(link.matchesString(ElementName.ADHESIVEBINDINGPARAMS + "Link"));
		Assertions.assertFalse(link.matchesString(ElementName.ADHESIVEBINDINGPARAMS + ":Plate"));
		link.setProcessUsage(EnumProcessUsage.Plate);
		Assertions.assertTrue(link.matchesString(ElementName.ADHESIVEBINDINGPARAMS));
		Assertions.assertTrue(link.matchesString("Input"));
		Assertions.assertFalse(link.matchesString("Output"));
		Assertions.assertTrue(link.matchesString(ElementName.ADHESIVEBINDINGPARAMS + "Link"));
		Assertions.assertTrue(link.matchesString(ElementName.ADHESIVEBINDINGPARAMS + ":Plate"));
	}

	/**
	 * Method testIncludesMatchingAttribute.
	 *
	 */
	@Test
	public void testSetPartMap()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setVersion(JDFElement.EnumVersion.Version_1_3);
		n.setType("ConventionalPrinting", true);
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, null, EnumUsage.Input, null, null, null, null);
		xm.addPartition(JDFResource.EnumPartIDKey.SheetName, "Sheet1");
		xm.addPartition(JDFResource.EnumPartIDKey.SheetName, "Sheet2");
		final JDFAttributeMap map1 = new JDFAttributeMap(JDFResource.EnumPartIDKey.SheetName.getName(), "Sheet1");
		final JDFAttributeMap map2 = new JDFAttributeMap(JDFResource.EnumPartIDKey.SheetName.getName(), "Sheet2");
		final JDFResourceLink rl = n.getLink(xm, null);

		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(map1);
		v.add(map2);
		rl.setPartMapVector(v);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testSetTarget() throws Exception
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setVersion(JDFElement.EnumVersion.Version_1_3);
		n.setType("ConventionalPrinting", true);
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, null, EnumUsage.Input, null, null, null, null);
		final JDFExposedMedia xm1 = (JDFExposedMedia) xm.addPartition(JDFResource.EnumPartIDKey.SignatureName, "Sig1");
		final VJDFAttributeMap vSig1 = new VJDFAttributeMap();
		vSig1.add(xm1.getPartMap());
		// want a lower leaf partition
		xm1.addPartition(JDFResource.EnumPartIDKey.SheetName, "Sheet1");
		final JDFMedia m = xm.appendMedia();
		final JDFResourceLink rl = n.linkResource(xm, EnumUsage.Input, null);
		try
		{
			rl.setTarget(m);
			Assertions.fail("no link to subelem");
		}
		catch (final JDFException x)
		{
			// nop
		}
		rl.setTarget(xm1);
		Assertions.assertEquals(rl.getPartMapVector(), vSig1);

	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * Method testIncludesMatchingAttribute.
	 *
	 */
	@Test
	public void testGetUsage()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		final JDFResourceLinkPool rlp = n.appendResourceLinkPool();
		final JDFResourceLink rl = (JDFResourceLink) rlp.appendElement("FooLink");
		Assertions.assertNull(rl.getUsage());
	}

	/**
	 * Method testHasResourcePartMap.
	 *
	 */
	@Test
	public void testHasResourcePartMap()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		final JDFResource r = n.addResource(ElementName.SCREENINGINTENT, null, EnumUsage.Input, null, null, null, null);

		final JDFResourceLink rl = n.getLink(r, null);
		// the root always exists
		Assertions.assertTrue(rl.hasResourcePartMap(null, false));
		Assertions.assertTrue(rl.hasResourcePartMap(null, true));

		final JDFAttributeMap attributeMap = new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig1");

		Assertions.assertTrue(rl.hasResourcePartMap(attributeMap, false));
		Assertions.assertFalse(rl.hasResourcePartMap(attributeMap, true));

		r.setPartUsage(EnumPartUsage.Implicit);
		Assertions.assertTrue(rl.hasResourcePartMap(attributeMap, false));
		Assertions.assertTrue(rl.hasResourcePartMap(attributeMap, true));

		r.setPartUsage(EnumPartUsage.Explicit);

		final JDFResource rSig = r.addPartition(EnumPartIDKey.SignatureName, "sig1");

		// the root always exists
		Assertions.assertTrue(rl.hasResourcePartMap(null, false));
		Assertions.assertTrue(rl.hasResourcePartMap(null, true));

		// link and resource match
		rl.setPart(EnumPartIDKey.SignatureName.getName(), "sig1");
		Assertions.assertTrue(rl.hasResourcePartMap(null, false));
		Assertions.assertTrue(rl.hasResourcePartMap(null, true));

		// resource is partitioned deeper than link
		rSig.addPartition(EnumPartIDKey.SheetName, "sh1");
		Assertions.assertTrue(rl.hasResourcePartMap(null, false));
		Assertions.assertTrue(rl.hasResourcePartMap(attributeMap, false));

		attributeMap.put(EnumPartIDKey.SheetName, "sh1");
		Assertions.assertTrue(rl.hasResourcePartMap(attributeMap, true));
		Assertions.assertFalse(rl.hasResourcePartMap(attributeMap, false));

		attributeMap.put(EnumPartIDKey.SheetName, "sh2");
		Assertions.assertFalse(rl.hasResourcePartMap(attributeMap, true));
		Assertions.assertFalse(rl.hasResourcePartMap(attributeMap, false));

		attributeMap.put(EnumPartIDKey.Side, "Front");
		Assertions.assertFalse(rl.hasResourcePartMap(attributeMap, true));
		Assertions.assertFalse(rl.hasResourcePartMap(attributeMap, false));

		Assertions.assertFalse(rl.hasResourcePartMap(new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig2"), false));
		Assertions.assertFalse(rl.hasResourcePartMap(new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig2"), true));

		r.setPartUsage(EnumPartUsage.Implicit);
		Assertions.assertTrue(rl.hasResourcePartMap(attributeMap, true));
		Assertions.assertTrue(rl.hasResourcePartMap(attributeMap, false));

		Assertions.assertFalse(rl.hasResourcePartMap(new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig2"), false));
		Assertions.assertFalse(rl.hasResourcePartMap(new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig2"), true));
	}

	/**
	 *
	 */
	@Test
	public void testInvertUsage()
	{
		Assertions.assertEquals(EnumUsage.Output, EnumUsage.Output.invert().invert());
		Assertions.assertEquals(EnumUsage.Input, EnumUsage.Output.invert());
	}

	/**
	 *
	 */
	@Test
	public void testIsResourceLink()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		Assertions.assertFalse(JDFResourceLink.isResourceLink(n));
		final JDFResource r = n.addResource(ElementName.ADHESIVEBINDINGPARAMS, EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(r, null);
		Assertions.assertFalse(JDFResourceLink.isResourceLink(r));
		Assertions.assertTrue(JDFResourceLink.isResourceLink(rl));
		final JDFPartAmount pa = rl.appendAmountPool().appendPartAmount();
		Assertions.assertFalse(JDFResourceLink.isResourceLink(pa));
	}

	/**
	 * Method testIsExecutable().
	 *
	 */
	@Test
	public void testIsExecutableNull()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		final JDFResource r = n.addResource(ElementName.SCREENINGINTENT, null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl = n.getLink(r, null);
		r.setResStatus(null, true);

		Assertions.assertFalse(rl.isExecutable(null, true));
	}

	/**
	 * Method testIsExecutable().
	 *
	 */
	@Test
	public void testIsExecutable()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		final JDFResource r = n.addResource(ElementName.SCREENINGINTENT, null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl = n.getLink(r, null);

		r.setResStatus(EnumResStatus.Available, true);
		Assertions.assertTrue(rl.isExecutable(null, true));
		r.setResStatus(EnumResStatus.Unavailable, true);
		Assertions.assertFalse(rl.isExecutable(null, true));
		r.setResStatus(EnumResStatus.Draft, true);
		Assertions.assertFalse(rl.isExecutable(null, true));
		rl.setDraftOK(true);
		Assertions.assertTrue(rl.isExecutable(null, true));

		rl.setUsage(EnumUsage.Output);
		r.setResStatus(EnumResStatus.Available, true);
		Assertions.assertTrue(rl.isExecutable(null, true));
		r.setResStatus(EnumResStatus.Unavailable, true);
		Assertions.assertFalse(rl.isExecutable(null, true));
		r.setResStatus(EnumResStatus.Draft, true);
		Assertions.assertTrue(rl.isExecutable(null, true));
		rl.setDraftOK(true);
		Assertions.assertTrue(rl.isExecutable(null, true));

		final JDFResource rSig = r.addPartition(EnumPartIDKey.SignatureName, "sig1");
		final JDFResource rSheet = rSig.addPartition(EnumPartIDKey.SheetName, "sh1");
		rSheet.setResStatus(EnumResStatus.Available, false);
		r.setResStatus(EnumResStatus.Unavailable, true);
		rSheet.setResStatus(EnumResStatus.Available, true);
		rl.setUsage(EnumUsage.Input);
		JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig1");
		Assertions.assertFalse(rl.isExecutable(map, false));
		Assertions.assertTrue(rl.isExecutable(map, true));
		final JDFResource rSheet2 = rSig.addPartition(EnumPartIDKey.SheetName, "sh2");
		rSheet2.setResStatus(EnumResStatus.Unavailable, false);
		Assertions.assertFalse(rl.isExecutable(map, false));
		Assertions.assertFalse(rl.isExecutable(map, true));
		map.put(EnumPartIDKey.SheetName, "sh1");
		Assertions.assertTrue(rl.isExecutable(map, false));
		Assertions.assertTrue(rl.isExecutable(map, true));
		rl.appendPart().setPartMap(map);
		map.put(EnumPartIDKey.SheetName, "sh2");
		rl.appendPart().setPartMap(map);
		map = new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig1");
		rSheet2.setResStatus(EnumResStatus.Available, false);

		Assertions.assertTrue(rl.isExecutable(map, false));
		Assertions.assertTrue(rl.isExecutable(map, true));

	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * Method test if the node name matching works.
	 */
	@Test
	public void testValidName()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		final JDFResourceLinkPool rlp = n.appendResourceLinkPool();
		JDFResourceLink rl = (JDFResourceLink) rlp.appendElement("FooLink");
		final JDFResource rBar = n.addResource("Bar", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
		rl.setrRef(rBar.getID());
		Assertions.assertFalse((rl.isValid(null)));
		rl = n.getLink(rBar, null);
		Assertions.assertNotNull(rl);
		Assertions.assertTrue((rl.isValid(null)));
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * test that the position checking algorithm works
	 *
	 */
	@Test
	public void testValidPosition()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFResource rBar = n.addResource("Bar", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl = n.getLink(rBar, null);
		Assertions.assertNotNull(rl);
		Assertions.assertTrue((rl.isValid(null)));
		final JDFNode n2 = n.addJDFNode("Whatever");
		n2.moveElement(n.getResourcePool(), null);
		Assertions.assertFalse((rl.isValid(null)));
		Assertions.assertFalse((rl.validResourcePosition()));
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * test that the position checking algorithm works
	 *
	 */
	@Test
	public void testUnPartition()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Strapping);
		final JDFResource rBar = n.addResource(ElementName.COMPONENT, EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(rBar, null);
		final JDFAttributeMap partMap = new JDFAttributeMap("SheetName", "s1");
		rl.setActualAmount(42, partMap);
		rl.setPartMap(partMap);
		rl.unpartition(false, true);
		Assertions.assertEquals(rl.getActualAmount(null), 42, 0.);
		Assertions.assertNotNull(rl.getPartMapVector());
		rl.unpartition(true, true);
		Assertions.assertNull(rl.getPartMapVector());
	}

	/**
	 * test that the position checking algorithm works
	 *
	 */
	@Test
	public void testValidAttributesAmountPool()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Strapping);
		final JDFResource rBar = n.addResource(ElementName.COMPONENT, EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(rBar, null);
		rl.setActualAmount(42, null);
		Assertions.assertFalse(rl.getInvalidAttributes(EnumValidationLevel.Incomplete, false, 99).contains(AttributeName.ACTUALAMOUNT));
		// rl.appendAmountPool();
		// assertTrue(rl.getInvalidAttributes(EnumValidationLevel.Incomplete,
		// false, 99).contains(AttributeName.ACTUALAMOUNT));
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * test that the position checking algorithm works
	 *
	 */
	@Test
	public void testValidCombinedProcessIndex()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Strapping);
		final JDFResource rBar = n.addResource("Bar", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl = n.getLink(rBar, null);
		Assertions.assertTrue(rl.validCombinedProcessIndex());
		rl.setCombinedProcessIndex(null);
		Assertions.assertTrue(rl.validCombinedProcessIndex());
		final JDFIntegerList il = new JDFIntegerList();
		rl.setCombinedProcessIndex(il);
		Assertions.assertTrue(rl.validCombinedProcessIndex());
		il.add(0);
		rl.setCombinedProcessIndex(il);
		Assertions.assertFalse(rl.validCombinedProcessIndex());
		n.setCombined(new VString("Approval ImageSetting", " "));
		Assertions.assertTrue(rl.validCombinedProcessIndex());
		il.add(1);
		rl.setCombinedProcessIndex(il);
		Assertions.assertTrue(rl.validCombinedProcessIndex());
		il.add(1);
		rl.setCombinedProcessIndex(il);
		Assertions.assertTrue(rl.validCombinedProcessIndex());
		Assertions.assertTrue(n.isValid(EnumValidationLevel.Incomplete));
		il.add(2);
		rl.setCombinedProcessIndex(il);
		Assertions.assertFalse(rl.validCombinedProcessIndex());
		Assertions.assertFalse(n.isValid(EnumValidationLevel.Incomplete));
		Assertions.assertTrue(rl.getInvalidAttributes(EnumValidationLevel.Incomplete, true, -1).contains(AttributeName.COMBINEDPROCESSINDEX));

	} // ///////////////////////////////////////////////////////////////////

	/**
	 * test that the position checking algorithm works
	 *
	 */
	@Test
	public void testSetCombinedProcessIndex()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Strapping);
		final JDFResource rBar = n.addResource("Bar", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl = n.getLink(rBar, null);
		rl.setCombinedProcessIndex(null);
		Assertions.assertFalse(rl.hasAttribute(AttributeName.COMBINEDPROCESSINDEX));
		final JDFIntegerList il = new JDFIntegerList();
		rl.setCombinedProcessIndex(il);
		Assertions.assertFalse(rl.hasAttribute(AttributeName.COMBINEDPROCESSINDEX));
		il.add(0);
		rl.setCombinedProcessIndex(il);
		Assertions.assertEquals(rl.getCombinedProcessIndex(), il);
	}

}