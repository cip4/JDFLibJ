/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class ProcessXJDFSplitTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	public void testSplit()
	{
		XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.setTypes("ImageSetting PreviewGeneration ConventionalPrinting Cutting Folding");
		SetHelper s = h.appendResourceSet("Media", EnumUsage.Input);
		ResourceHelper p = s.appendPartition(null, true);
		((JDFMedia) p.getResource()).setMediaType(EnumMediaType.Plate);

		s = h.appendResourceSet("ExposedMedia", null);
		p = s.appendPartition(null, true);

		s = h.appendResourceSet("PreviewGenerationParams", EnumUsage.Input);
		p = s.appendPartition(null, true);

		s = h.appendResourceSet("Preview", null);
		p = s.appendPartition(null, true);

		s = h.appendResourceSet("ConventionalPrintingParams", EnumUsage.Input);
		p = s.appendPartition(null, true);

		s = h.appendResourceSet("CuttingParams", EnumUsage.Input);
		p = s.appendPartition(null, true);

		s = h.appendResourceSet("FoldingParams", EnumUsage.Input);
		p = s.appendPartition(null, true);

		s = h.appendResourceSet("Component", EnumUsage.Output);
		p = s.appendPartition(null, true);

		XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		splitter.addGroup(new VString("ImageSetting PreviewGeneration", null));
		c.setSplitter(splitter);

		JDFDoc d = c.convert(h.getRoot());
		d.write2File(sm_dirTestDataTemp + "splitxjdf.jdf", 2, false);
		assertTrue(d.getJDFRoot().isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 *
	 */
	@Test
	public void testSplitDevice()
	{
		XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.setTypes("ImageSetting ConventionalPrinting");

		SetHelper s = h.appendResourceSet("Device", EnumUsage.Input);
		s.setCombinedProcessIndex(new JDFIntegerList(0));
		s.setDescriptiveName("Dev PlateSetter");
		KElement dev = s.appendPartition(null, true).getResource();
		dev.setAttribute(AttributeName.DEVICEID, "PS1");

		s = h.appendResourceSet("Device", EnumUsage.Input);
		s.setCombinedProcessIndex(new JDFIntegerList(1));
		dev = s.appendPartition(null, true).getResource();
		dev.setAttribute(AttributeName.DEVICEID, "P1");
		s.setDescriptiveName("Dev Print");
		s.appendPartition(null, true);

		XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		splitter.addGroup(new VString("ImageSetting PreviewGeneration", null));
		c.setSplitter(splitter);

		JDFDoc d = c.convert(h.getRoot());
		JDFNode root = d.getJDFRoot();
		JDFNode imSet = (JDFNode) root.getvJDFNode("ImageSetting", null, true).get(0);
		assertEquals("PS1", imSet.getResource(ElementName.DEVICE, null, 0).getAttribute(AttributeName.DEVICEID));
		JDFNode cp = (JDFNode) root.getvJDFNode("ConventionalPrinting", null, true).get(0);
		assertEquals("P1", cp.getResource(ElementName.DEVICE, null, 0).getAttribute(AttributeName.DEVICEID));

		d.write2File(sm_dirTestDataTemp + "splitDevxjdf.jdf", 2, false);
		assertTrue(d.getJDFRoot().isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 *
	 */
	@Test
	public void testSplitNullTypes()
	{
		XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.setTypes((String) null);
		SetHelper s = h.appendResourceSet("Media", EnumUsage.Input);
		ResourceHelper p = s.appendPartition(null, true);
		((JDFMedia) p.getResource()).setMediaType(EnumMediaType.Plate);

		XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		splitter.addGroup(new VString("ImageSetting PreviewGeneration", null));
		c.setSplitter(splitter);

		JDFDoc d = c.convert(h.getRoot());
		d.write2File(sm_dirTestDataTemp + "splitxjdfNull.jdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	public void testSplitEndCustomer()
	{
		XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.appendProduct();
		h.setTypes("Product ConventionalPrinting");
		SetHelper s = h.appendResourceSet(ElementName.CUSTOMERINFO, EnumUsage.Input);
		s.setProcessUsage("EndCustomer");
		s.getCreatePartition(0, true).getResource().setAttribute(AttributeName.CUSTOMERID, "c1");
		SetHelper s2 = h.appendResourceSet(ElementName.CUSTOMERINFO, EnumUsage.Input);
		s2.getCreatePartition(0, true).getResource().setAttribute(AttributeName.CUSTOMERID, "c2");
		XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		c.setSplitter(splitter);

		JDFDoc d = c.convert(h);
		JDFNode n = d.getJDFRoot();

		assertNotNull(n.getCustomerInfo());
		assertNotNull(n.getResource(ElementName.CUSTOMERINFO, EnumUsage.Input, "EndCustomer", null, 0));
		d.write2File(sm_dirTestDataTemp + "ci2.jdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	public void testSplitFromFile()
	{
		XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		splitter.addGroup(new VString("ImageSetting PreviewGeneration", null));
		c.setSplitter(splitter);

		KElement root = XMLDoc.parseFile(sm_dirTestData + "29694232.ptk").getRoot();
		KElement xjdf = root.getChildByTagName(XJDF20.rootName, null, 0, null, false, true);
		JDFDoc d = c.convert(xjdf);
		d.write2File(sm_dirTestDataTemp + "splitxjdfFile.jdf", 2, false);
		//assertTrue(d.getJDFRoot().isValid(EnumValidationLevel.Incomplete));
	}

}
