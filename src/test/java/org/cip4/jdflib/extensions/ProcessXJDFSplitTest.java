/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFContact.EnumContactType;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.JDFDate;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 *
 */
class ProcessXJDFSplitTest extends JDFTestCaseBase
{
	/**
	 * @throws Throwable
	 *
	 */
	@Test
	void testSplitTypesCutting() throws Throwable
	{
		final XJDFHelper h = new XJDFHelper("j1", "p1", null);
		h.setTypes("ConventionalPrinting Cutting");
		final SetHelper cSet = h.appendResourceSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		cSet.appendPartition(new JDFAttributeMap("SheetName", "S1"), true);
		final SetHelper cuSet = h.appendResourceSet(ElementName.CUTTINGPARAMS, EnumUsage.Input);
		cuSet.appendPartition(new JDFAttributeMap("SheetName", "S1"), true);
		final SetHelper compSet = h.appendResourceSet(ElementName.COMPONENT, null);
		compSet.setCombinedProcessIndex(new JDFIntegerList("0 1"));
		compSet.appendPartition(new JDFAttributeMap("SheetName", "S1"), true).setExternalID("i1");

		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		final Vector<XJDFHelper> c = (Vector<XJDFHelper>) splitter.splitXJDF(h);
		assertEquals(3, c.size());
		assertNotNull(c.get(1).getSet(ElementName.COMPONENT, EnumUsage.Output));
		assertNotNull(c.get(2).getSet(ElementName.COMPONENT, EnumUsage.Input));
		assertNotNull(c.get(2).getSet(ElementName.COMPONENT, EnumUsage.Input).getPartition(0));
	}

	/**
	 * @throws Throwable
	 *
	 */
	@Test
	void testSplitTypesCuttingParams() throws Throwable
	{
		final XJDFHelper h = new XJDFHelper("j1", "p1", null);
		h.setTypes("ConventionalPrinting Cutting");
		final SetHelper cSet = h.appendResourceSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		cSet.appendPartition(new JDFAttributeMap("SheetName", "S1"), true);
		final SetHelper cuSet = h.appendResourceSet(ElementName.CUTTINGPARAMS, EnumUsage.Input);
		cuSet.appendPartition(new JDFAttributeMap("SheetName", "S1"), true);
		final SetHelper compSet = h.appendResourceSet(ElementName.COMPONENT, null);
		compSet.setCombinedProcessIndex(new JDFIntegerList("0 1"));
		compSet.appendPartition(new JDFAttributeMap("SheetName", "S1"), true).setExternalID("i1");

		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		final Vector<XJDFHelper> c = (Vector<XJDFHelper>) splitter.splitXJDF(h);
		assertEquals(3, c.size());
		assertNotNull(c.get(1).getSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input));
		assertNull(c.get(2).getSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input));
		assertNotNull(c.get(2).getSet(ElementName.CUTTINGPARAMS, EnumUsage.Input));
		assertNull(c.get(1).getSet(ElementName.CUTTINGPARAMS, EnumUsage.Input));
	}

	/**
	 * @throws Throwable
	 *
	 */
	@Test
	void testSplitTypesICS() throws Throwable
	{
		final XJDFHelper h = new XJDFHelper("j1", "p1", null);
		h.setTypes("ConventionalPrinting Varnishing");
		final SetHelper cSet = h.appendResourceSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		cSet.appendPartition(new JDFAttributeMap("SheetName", "S1"), true);
		final SetHelper cuSet = h.appendResourceSet(ElementName.VARNISHINGPARAMS, EnumUsage.Input);
		cuSet.appendPartition(new JDFAttributeMap("SheetName", "S1"), true);
		final SetHelper compSet = h.appendResourceSet(ElementName.COMPONENT, EnumUsage.Output);
		compSet.setCombinedProcessIndex(new JDFIntegerList("0 1"));
		compSet.appendPartition(new JDFAttributeMap("SheetName", "S1"), true).setExternalID("i1");

		final ProcessXJDFSplit splitter = ProcessXJDFSplit.getICSSplit();

		final Vector<XJDFHelper> c = (Vector<XJDFHelper>) splitter.splitXJDF(h);
		assertEquals(1, c.size());

	}

	/**
	 *
	 */
	@Test
	void testSplitRef()
	{
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.setTypes("Product Stripping");
		SetHelper s = h.appendResourceSet("Media", EnumUsage.Input);
		final ResourceHelper p = s.appendPartition(null, true);
		final JDFMedia media = (JDFMedia) p.getResource();
		media.setMediaType(EnumMediaType.Plate);

		s = h.appendResourceSet(ElementName.STRIPPINGPARAMS, null);
		final JDFResource sp = (JDFResource) s.appendPartition(null, true).getResource();
		sp.setAttribute("PaperRef", p.ensureID());

		final XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		c.setSplitter(splitter);

		final JDFDoc d = c.convert(h.getRoot());
		d.write2File(sm_dirTestDataTemp + "splitPaper.jdf", 2, false);

		final JDFNode jdfRoot = d.getJDFRoot();
		final JDFNode jdf0 = jdfRoot.getJDF(0);
		final JDFResource strpParams = jdf0.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
		assertNotNull(strpParams.getElement(ElementName.MEDIA));
	}

	/**
	 * @throws Throwable
	 *
	 */
	@Test
	void testSplitTypesCuttingProduct() throws Throwable
	{
		final XJDFHelper h = new XJDFHelper("j1", "p1", null);
		h.setTypes("ConventionalPrinting Cutting Product");

		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		assertEquals(new VString("Product"), splitter.splitTypes(h).get(0));
	}

	/**
	 *
	 */
	@Test
	void testSplit()
	{
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
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

		final XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		splitter.addGroup(new VString("ImageSetting PreviewGeneration", null));
		c.setSplitter(splitter);

		final JDFDoc d = c.convert(h.getRoot());
		d.write2File(sm_dirTestDataTemp + "splitxjdf.jdf", 2, false);
		assertTrue(d.getJDFRoot().isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 *
	 */
	@Test
	void testSplitDevice()
	{
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
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

		final XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		splitter.addGroup(new VString("ImageSetting PreviewGeneration", null));
		c.setSplitter(splitter);

		final JDFDoc d = c.convert(h.getRoot());
		final JDFNode root = d.getJDFRoot();
		final JDFNode imSet = (JDFNode) root.getvJDFNode("ImageSetting", null, true).get(0);
		final JDFResource jdfDev = imSet.getResource(ElementName.DEVICE, null, 0);
		assertEquals("PS1", jdfDev.getAttribute(AttributeName.DEVICEID));
		assertNull(imSet.getLink(jdfDev, null).getNonEmpty(AttributeName.COMBINEDPROCESSINDEX));
		final JDFNode cp = (JDFNode) root.getvJDFNode("ConventionalPrinting", null, true).get(0);
		final JDFResource cpDev = cp.getResource(ElementName.DEVICE, null, 0);
		assertEquals("P1", cpDev.getAttribute(AttributeName.DEVICEID));
		assertNull(cp.getLink(cpDev, null).getNonEmpty(AttributeName.COMBINEDPROCESSINDEX));

		d.write2File(sm_dirTestDataTemp + "splitDevxjdf.jdf", 2, false);
		assertTrue(d.getJDFRoot().isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 *
	 *
	 */
	@Test
	void testSplitProductNodeInfo()
	{
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();

		final XJDFHelper h = new XJDFHelper("j1", null, null);
		h.addType(EnumType.Product);
		h.addType(EnumType.ConventionalPrinting);
		h.appendProduct().setProductType("foo");
		final SetHelper niProduct = h.getCreateSet(ElementName.NODEINFO, EnumUsage.Input);
		niProduct.setCombinedProcessIndex(new JDFIntegerList(0));
		final JDFNodeInfo nip0 = (JDFNodeInfo) niProduct.getCreatePartition(0, true).getResource();
		nip0.setEnd(new JDFDate().addOffset(0, 0, 0, 10));

		final SetHelper niCP = h.appendResourceSet(ElementName.NODEINFO, EnumUsage.Input);
		niCP.setCombinedProcessIndex(new JDFIntegerList(1));
		final JDFNodeInfo nicp0 = (JDFNodeInfo) niCP.getCreatePartition(0, true).getResource();
		nicp0.setEnd(new JDFDate().addOffset(0, 0, 0, 5));

		final Vector<XJDFHelper> splitted = (Vector<XJDFHelper>) splitter.splitXJDF(h);
		assertEquals(2, splitted.size());
		assertEquals(1, splitted.get(0).getSets(ElementName.NODEINFO, EnumUsage.Input).size());
		assertEquals(1, splitted.get(1).getSets(ElementName.NODEINFO, EnumUsage.Input).size());
	}

	/**
	*
	*
	*/
	@Test
	void testProductComponent()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		xCon.setSplitter(splitter);

		final XJDFHelper h = new XJDFHelper("j1", null, null);
		h.setTypes(JDFConstants.PRODUCT);
		h.addType(EnumType.ConventionalPrinting);
		final SetHelper csh = h.getCreateSet(ElementName.COMPONENT, EnumUsage.Output);
		csh.getCreatePartition(new JDFAttributeMap(AttributeName.SHEETNAME, "s1"), true).setAmount(5, null, true);
		csh.getCreatePartition(new JDFAttributeMap(AttributeName.SHEETNAME, "s2"), true).setAmount(8, null, true);
		h.appendProduct().setAmount(10);
		h.cleanUp();
		final JDFDoc d = xCon.convert(h);
		assertNotNull(d);
		final JDFNode n = d.getJDFRoot();
		assertNull(n.getResource(ElementName.COMPONENT, EnumUsage.Output, 1));
	}

	/**
	 *
	 *
	 */
	@Test
	void testCheckProduct()
	{
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();

		final XJDFHelper h = new XJDFHelper("j1", null, null);
		h.addType(EnumType.Product);
		final SetHelper niProduct = h.getCreateSet(ElementName.NODEINFO, EnumUsage.Input);
		final SetHelper color = h.getCreateSet(ElementName.COLOR, EnumUsage.Input);
		assertNull(splitter.checkProduct(color, new VString("Product")));
		assertNotNull(splitter.checkProduct(color, new VString("Climbing")));
		assertNotNull(splitter.checkProduct(niProduct, new VString("Product")));

	}

	/**
	 *
	 *
	 */
	@Test
	void testProcessUsage()
	{
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();

		final XJDFHelper h = new XJDFHelper("j1", null, null);
		h.addType(EnumType.Imposition);
		h.addType(EnumType.ConventionalPrinting);
		final SetHelper rlDoc = h.getCreateSet(ElementName.RUNLIST, EnumUsage.Input, "Document");
		rlDoc.setExternalID("rl");
		h.removeSet(ElementName.NODEINFO);
		final SetHelper niCP = h.getCreateSet(ElementName.NODEINFO, EnumUsage.Input, JDFConstants.CONVENTIONALPRINTING);
		niCP.setExternalID("cp");
		final SetHelper niIm = h.getCreateSet(ElementName.NODEINFO, EnumUsage.Input, JDFConstants.IMPOSITION);
		niIm.setExternalID("im");
		final List<XJDFHelper> s = (List<XJDFHelper>) splitter.splitXJDF(h);
		s.remove(0);
		assertEquals("im", s.get(0).getSet(ElementName.NODEINFO, 0).getExternalID());
		assertNull(s.get(0).getSet(ElementName.NODEINFO, 1), "im");
		assertEquals("rl", s.get(0).getSet(ElementName.RUNLIST, 0).getExternalID());
		assertEquals("cp", s.get(1).getSet(ElementName.NODEINFO, 0).getExternalID());
		assertNull(s.get(1).getSet(ElementName.NODEINFO, 1), "cp");
	}

	/**
	 *
	 *
	 */
	@Test
	void testProductNodeInfo()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		xCon.setSplitter(splitter);

		final XJDFHelper h = new XJDFHelper("j1", null, null);
		h.addType(EnumType.Product);
		h.addType(EnumType.ConventionalPrinting);
		h.appendProduct().setProductType("foo");
		final SetHelper niProduct = h.getCreateSet(ElementName.NODEINFO, EnumUsage.Input);
		niProduct.setCombinedProcessIndex(new JDFIntegerList(0));
		final JDFNodeInfo nip0 = (JDFNodeInfo) niProduct.getCreatePartition(0, true).getResource();
		final JDFDate d10 = new JDFDate().addOffset(0, 0, 0, 10);
		nip0.setEnd(d10);

		final SetHelper niCP = h.appendResourceSet(ElementName.NODEINFO, EnumUsage.Input);
		niCP.setCombinedProcessIndex(new JDFIntegerList(1));
		final JDFNodeInfo nicp0 = (JDFNodeInfo) niCP.getCreatePartition(0, true).getResource();
		final JDFDate d5 = new JDFDate().addOffset(0, 0, 0, 5);
		nicp0.setEnd(d5);

		final JDFDoc d = xCon.convert(h);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		assertEquals(d10, root.getNodeInfo().getEnd());
		assertEquals(d5, root.getJDF(0).getNodeInfo().getEnd());
	}

	/**
	 *
	 *
	 */
	@Test
	void testCustomerInfoContacts()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		xCon.setSplitter(splitter);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		h.getCreateSet(XJDFConstants.Resource, ElementName.CUSTOMERINFO, EnumUsage.Input).getCreatePartition(0, true).getResource().setAttribute(AttributeName.CUSTOMERORDERID,
				"cc");
		final SetHelper sh = h.getCreateSet(XJDFConstants.Resource, ElementName.CONTACT, EnumUsage.Input);
		final ResourceHelper ph = sh.getCreatePartition(0, true);
		ph.setPartMap(new JDFAttributeMap(XJDFConstants.ContactType, EnumContactType.Customer.getName()));
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFCustomerInfo ci = d.getJDFRoot().getCustomerInfo();
		assertNotNull(ci);
		final JDFContact contact = (JDFContact) d.getJDFRoot().getResource(ElementName.CONTACT, null, 0);
		assertEquals(EnumContactType.Customer.getName(), contact.getContactTypes().get(0));
	}

	/**
	 *
	 */
	@Test
	void testSplitNullTypes()
	{
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.setTypes((String) null);
		final SetHelper s = h.appendResourceSet("Media", EnumUsage.Input);
		final ResourceHelper p = s.appendPartition(null, true);
		((JDFMedia) p.getResource()).setMediaType(EnumMediaType.Plate);

		final XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		splitter.addGroup(new VString("ImageSetting PreviewGeneration", null));
		c.setSplitter(splitter);

		final JDFDoc d = c.convert(h.getRoot());
		d.write2File(sm_dirTestDataTemp + "splitxjdfNull.jdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	void testSplitJPIDTypes()
	{
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.setTypes("Screening ImageSetting ConventionalPrinting");
		final SetHelper s = h.appendResourceSet("Media", EnumUsage.Input);
		final ResourceHelper p = s.appendPartition(null, true);
		((JDFMedia) p.getResource()).setMediaType(EnumMediaType.Plate);

		final XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		splitter.addGroup(new VString("Screening ImageSetting PreviewGeneration", null));
		c.setSplitter(splitter);

		final JDFDoc d = c.convert(h.getRoot());
		final VElement v = d.getJDFRoot().getvJDFNode(null, null, false);
		for (final KElement e : v)
		{
			assertEquals(-1, e.getNonEmpty(AttributeName.JOBPARTID).indexOf(" "));
		}
		d.write2File(sm_dirTestDataTemp + "splitxjdfTypes.jdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	void testSplitCategory()
	{
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.setTypes("Screening ImageSetting ConventionalPrinting");
		h.setAttribute(AttributeName.CATEGORY, "w2p");
		final SetHelper s = h.appendResourceSet("Media", EnumUsage.Input);
		final ResourceHelper p = s.appendPartition(null, true);
		((JDFMedia) p.getResource()).setMediaType(EnumMediaType.Plate);

		final XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		c.setSplitter(splitter);

		final JDFDoc d = c.convert(h.getRoot());
		final VElement v = d.getJDFRoot().getvJDFNode(null, null, false);
		for (final KElement e : v)
		{
			final JDFNode n = (JDFNode) e;
			assertEquals(n.isJDFRoot() ? "w2p" : "", n.getCategory());
		}
		d.write2File(sm_dirTestDataTemp + "splitxjdfCategory.jdf", 2, false);
	}

	/**
	 * @throws DataFormatException
	 *
	 */
	@Test
	void testSplitNodeInfo() throws DataFormatException
	{
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.setTypes("Screening ImageSetting ConventionalPrinting Cutting Folding Trimming");
		h.appendSet(ElementName.NODEINFO, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("0 1"));
		h.appendSet(ElementName.NODEINFO, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("2"));
		h.appendSet(ElementName.NODEINFO, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("3 4 5"));

		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();

		final VString types = h.getTypes();
		final VString t0 = splitter.extractTypes(h, types, 0);
		assertEquals(2, t0.size());
		final VString t1 = splitter.extractTypes(h, types, 2);
		assertEquals(1, t1.size());
		final VString t2 = splitter.extractTypes(h, types, 3);
		assertEquals(3, t2.size());
	}

	/**
	 * @throws DataFormatException
	 *
	 */
	@Test
	void testSplitDevice2() throws DataFormatException
	{
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.setTypes("Screening ImageSetting ConventionalPrinting Cutting Folding Trimming");
		h.appendSet(ElementName.DEVICE, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("0 1"));
		h.appendSet(ElementName.DEVICE, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("2"));
		h.appendSet(ElementName.DEVICE, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("3 5 4"));

		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();

		final VString types = h.getTypes();
		final VString t0 = splitter.extractTypes(h, types, 0);
		assertEquals(2, t0.size());
		final VString t1 = splitter.extractTypes(h, types, 2);
		assertEquals(1, t1.size());
		final VString t2 = splitter.extractTypes(h, types, 3);
		assertEquals(3, t2.size());
	}

	/**
	 * @throws DataFormatException
	 *
	 */
	@Test
	void testSplitDeviceNI() throws DataFormatException
	{
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.setTypes("Screening ImageSetting ConventionalPrinting Cutting Folding Trimming");
		h.appendSet(ElementName.NODEINFO, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("0 1"));
		h.appendSet(ElementName.NODEINFO, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("2"));
		h.appendSet(ElementName.NODEINFO, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("3 4 5"));

		final XJDFHelper h2 = new XJDFHelper("j1", "root", null);
		h2.setTypes("Screening ImageSetting ConventionalPrinting Cutting Folding Trimming");
		h2.appendSet(ElementName.DEVICE, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("0 1"));
		h2.appendSet(ElementName.DEVICE, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("2"));
		h2.appendSet(ElementName.DEVICE, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("3 4 5"));

		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();

		final VString types = h2.getTypes();
		final VString t0 = splitter.extractTypes(h2, types, 0);
		assertEquals(2, t0.size());
		final VString t1 = splitter.extractTypes(h2, types, 2);
		assertEquals(1, t1.size());
		final VString t2 = splitter.extractTypes(h2, types, 3);
		assertEquals(3, t2.size());
	}

	/**
	 * @throws DataFormatException
	 */
	@Test
	void testSplitDeviceIncomplete() throws DataFormatException
	{
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.setTypes("Screening ImageSetting ConventionalPrinting Cutting Folding Trimming");
		h.appendSet(ElementName.NODEINFO, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("0 1"));
		h.appendSet(ElementName.DEVICE, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("3 5 4"));

		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();

		final VString types = h.getTypes();
		final VString t0 = splitter.extractTypes(h, types, 0);
		assertEquals(2, t0.size());
		final VString t1 = splitter.extractTypes(h, types, 2);
		assertEquals(1, t1.size());
		final VString t2 = splitter.extractTypes(h, types, 3);
		assertEquals(3, t2.size());
	}

	/**
	 * @throws DataFormatException
	 *
	 */
	@Test
	void testSplitDeviceNIMix() throws DataFormatException
	{
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.setTypes("Screening ImageSetting ConventionalPrinting Cutting Folding Trimming");
		h.appendSet(ElementName.NODEINFO, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("2"));
		h.appendSet(ElementName.NODEINFO, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("3 4 5"));

		h.appendSet(ElementName.DEVICE, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("0 1"));
		h.appendSet(ElementName.DEVICE, EnumUsage.Input).setCombinedProcessIndex(new JDFIntegerList("2"));

		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();

		final VString types = h.getTypes();
		final VString t0 = splitter.extractTypes(h, types, 0);
		assertEquals(2, t0.size());
		final VString t1 = splitter.extractTypes(h, types, 2);
		assertEquals(1, t1.size());
		final VString t2 = splitter.extractTypes(h, types, 3);
		assertEquals(3, t2.size());
	}

	/**
	 *
	 */
	@Test
	void testSplitCategorySingle()
	{
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.setTypes("Screening");
		h.setAttribute(AttributeName.CATEGORY, "w2p");

		final XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		c.setSplitter(splitter);

		final JDFDoc d = c.convert(h.getRoot());
		final VElement v = d.getJDFRoot().getvJDFNode(null, null, false);
		assertEquals(1, v.size());
		final JDFNode n = (JDFNode) v.get(0);
		assertEquals("w2p", n.getCategory());

		d.write2File(sm_dirTestDataTemp + "splitxjdfCategory.jdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	void testSplitEndCustomer()
	{
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.appendProduct();
		h.setTypes("Product ConventionalPrinting");
		final SetHelper s = h.appendResourceSet(ElementName.CUSTOMERINFO, EnumUsage.Input);
		s.setProcessUsage("EndCustomer");
		s.getCreatePartition(0, true).getResource().setAttribute(AttributeName.CUSTOMERID, "c1");
		final SetHelper s2 = h.appendResourceSet(ElementName.CUSTOMERINFO, EnumUsage.Input);
		s2.getCreatePartition(0, true).getResource().setAttribute(AttributeName.CUSTOMERID, "c2");
		final XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		c.setSplitter(splitter);

		final JDFDoc d = c.convert(h);
		final JDFNode n = d.getJDFRoot();

		assertNotNull(n.getCustomerInfo());
		assertNotNull(n.getResource(ElementName.CUSTOMERINFO, EnumUsage.Input, "EndCustomer", null, 0));
		d.write2File(sm_dirTestDataTemp + "ci2.jdf", 2, false);
	}

	/**
	 * @throws Throwable
	 */
	@Test
	void testSplitTypesExchange() throws Throwable
	{
		final XJDFHelper h = new XJDFHelper("j1", "p1", null);
		h.setTypes("ImageSetting ConventionalPrinting");
		final SetHelper xmSet = h.appendResourceSet(ElementName.EXPOSEDMEDIA, null);
		xmSet.setCombinedProcessIndex(JDFIntegerList.createIntegerList("0 1"));

		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		final Vector<XJDFHelper> c = (Vector<XJDFHelper>) splitter.splitXJDF(h);
		assertEquals(3, c.size());
		assertNotNull(c.get(1).getSet(ElementName.EXPOSEDMEDIA, EnumUsage.Output));
		assertNotNull(c.get(2).getSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input));
	}

	/**
	 *
	 */
	@Test
	void testSplitColorPool()
	{
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.appendProduct();
		h.setTypes("Product");
		final SetHelper s = h.appendResourceSet(ElementName.COLOR, EnumUsage.Input);
		s.appendPartition(new JDFAttributeMap(AttributeName.SEPARATION, "Cyan"), true).getResource().setAttribute(AttributeName.ACTUALCOLORNAME, "c1");
		final XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		c.setSplitter(splitter);

		final JDFDoc d = c.convert(h);
		final JDFNode n = d.getJDFRoot();

		assertNotNull(n.getResource(ElementName.COLORPOOL, EnumUsage.Input, 0));
		d.write2File(sm_dirTestDataTemp + "cp.jdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	void testSplitFromFile()
	{
		final XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		final ProcessXJDFSplit splitter = new ProcessXJDFSplit();
		splitter.addGroup(new VString("ImageSetting PreviewGeneration", null));
		c.setSplitter(splitter);

		final KElement root = XMLDoc.parseFile(sm_dirTestData + "29694232.ptk").getRoot();
		final KElement xjdf = root.getChildByTagName(XJDF20.rootName, null, 0, null, false, true);
		final JDFDoc d = c.convert(xjdf);
		d.write2File(sm_dirTestDataTemp + "splitxjdfFile.jdf", 2, false);
		// assertTrue(d.getJDFRoot().isValid(EnumValidationLevel.Incomplete));
	}

}
