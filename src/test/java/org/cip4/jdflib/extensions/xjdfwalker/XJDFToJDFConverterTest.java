/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.xjdfwalker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoLayoutIntent.EnumSides;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.datatypes.JDFRGBColor;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.AuditPoolHelper;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.MessageHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.JDFToXJDF;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFInsert;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.PartitionGetter;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFInsertingIntent;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.intent.JDFLayoutIntent;
import org.cip4.jdflib.resource.intent.JDFMediaIntent;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFContact.EnumContactType;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFDeliveryParams;
import org.cip4.jdflib.resource.process.JDFDieLayoutProductionParams;
import org.cip4.jdflib.resource.process.JDFIdentical;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFUsageCounter;
import org.cip4.jdflib.resource.process.postpress.JDFHoleMakingParams;
import org.junit.Test;

/**
 * @author rainer prosi
 * @date Dec 10, 2012
 */
public class XJDFToJDFConverterTest extends JDFTestCaseBase
{
	/**
	 *
	 *
	 */
	@Test
	public void testCompany()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final KElement e = new XMLDoc(XJDFConstants.XJDF, null).getRoot();
		final KElement c = e.appendElement(SetHelper.RESOURCE_SET);
		c.setAttribute("Name", "Contact");
		c.setAttribute("Usage", "Input");
		c.appendElement(XJDFConstants.Resource).appendElement(ElementName.CONTACT).appendElement(ElementName.COMPANY).setAttribute("CompanyID", "company_id");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFContact contact = (JDFContact) root.getResource("Contact", EnumUsage.Input, 0);
		assertEquals(contact.getCompany().getProductID(), "company_id");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testExtendedAddress()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final KElement e = new XMLDoc(XJDFConstants.XJDF, null).getRoot();
		final KElement c = e.appendElement(SetHelper.RESOURCE_SET);
		c.setAttribute("Name", "Contact");
		c.setAttribute("Usage", "Input");
		c.appendElement(XJDFConstants.Resource).appendElement(ElementName.CONTACT).appendElement(ElementName.ADDRESS).setAttribute(ElementName.EXTENDEDADDRESS, "suite");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFContact contact = (JDFContact) root.getResource("Contact", EnumUsage.Input, 0);
		assertEquals(contact.getAddress().getExtendedAddressText(), "suite");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testColorantControl()
	{
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.setTypes(EnumType.ImageSetting.getName());
		final JDFColorantControl cc = (JDFColorantControl) h.getCreateSet(XJDFConstants.Resource, ElementName.COLORANTCONTROL, EnumUsage.Input).getCreatePartition(0, true).getResource();
		cc.setAttribute(ElementName.COLORANTPARAMS, "Cyan Magenta Yellow Black");
		cc.setAttribute(ElementName.COLORANTORDER, "Cyan Black");
		cc.setProcessColorModel("DeviceCMYK");

		final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		final JDFDoc docjdf = conv.convert(h);
		final JDFNode n = docjdf.getJDFRoot();

		final JDFColorantControl ccNew = (JDFColorantControl) n.getResource(ElementName.COLORANTCONTROL, EnumUsage.Input, null, 0);
		assertNull(ccNew.getColorantParams());
		assertEquals(ccNew.getColorantOrder().getSeparations(), new VString("Cyan Black", null));
		assertNull(ccNew.getDeviceColorantOrder());
	}

	/**
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testColorNoSep() throws Exception
	{
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.setTypes(EnumType.ImageSetting.getName());
		final JDFColor c = (JDFColor) h.getCreateSet(XJDFConstants.Resource, ElementName.COLOR, EnumUsage.Input).getCreatePartition(0, true).getResource();
		c.setsRGB(new JDFRGBColor("3 5 7"));

		final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		final JDFDoc docjdf = conv.convert(h);
		final JDFNode n = docjdf.getJDFRoot();

		final JDFColorPool ccNew = (JDFColorPool) n.getResource(ElementName.COLORPOOL, EnumUsage.Input, null, 0);
		assertNull(ccNew);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testMultiProcess()
	{
		final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		JDFDoc docjdf = null;
		for (int i = 0; i < 2; i++)
		{
			final XJDFHelper h = new XJDFHelper("j", "p", null);
			h.setTypes(EnumType.ConventionalPrinting.getName());
			final SetHelper cIn = h.getCreateSet(ElementName.COMPONENT, EnumUsage.Output);
			cIn.appendPartition(AttributeName.SHEETNAME, "S" + i, true);

			docjdf = conv.convert(h);

		}
		final JDFNode n = docjdf.getJDFRoot();

		assertNotNull(n);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testMultiComponent()
	{
		final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.setTypes(EnumType.ConventionalPrinting.getName());
		final SetHelper cIn = h.getCreateSet(ElementName.COMPONENT, EnumUsage.Output);
		cIn.appendPartition(AttributeName.SHEETNAME, "S", true);

		final ProductHelper p = h.getCreateRootProduct(0);
		p.setAmount(400);
		p.setAttribute(AttributeName.DESCRIPTIVENAME, "desc");
		p.appendIntent(ElementName.LAYOUTINTENT).getResource().setAttribute(AttributeName.NPAGE, "4");
		final JDFDoc docjdf = conv.convert(h);

		final JDFNode n = docjdf.getJDFRoot();
		assertNotNull(n.getResource(ElementName.COMPONENT, EnumUsage.Output, null, 0));
		assertNull(n.getResource(ElementName.COMPONENT, EnumUsage.Output, null, 1));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testInputStatus()
	{
		for (int i = 0; i < 3; i++)
		{
			final XJDFHelper h = new XJDFHelper("j", "p", null);
			h.setTypes(EnumType.ImageSetting.getName());
			final ResourceHelper partition = h.getCreateSet(XJDFConstants.Resource, ElementName.COLORANTCONTROL, EnumUsage.Input).getCreatePartition(0, true);
			if (i == 1)
				partition.setStatus(EnumResStatus.Available);
			else if (i == 2)
				partition.setStatus(EnumResStatus.Unavailable);
			final JDFColorantControl cc = (JDFColorantControl) partition.getResource();
			cc.setProcessColorModel("DeviceCMYK");

			final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
			final JDFDoc docjdf = conv.convert(h);
			final JDFNode n = docjdf.getJDFRoot();

			final JDFColorantControl ccNew = (JDFColorantControl) n.getResource(ElementName.COLORANTCONTROL, EnumUsage.Input, null, 0);
			if (i < 2)
				assertEquals(EnumResStatus.Available, ccNew.getResStatus(false));
			else
				assertEquals(EnumResStatus.Unavailable, ccNew.getResStatus(false));
		}
	}

	/**
	 *
	 *
	 */
	@Test
	public void testNodeInfoStatus()
	{
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.setTypes(EnumType.ImageSetting.getName());
		final ResourceHelper partition = h.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input).getCreatePartition(0, true);
		partition.setStatus(EnumResStatus.Available);
		final JDFNodeInfo ni = (JDFNodeInfo) partition.getResource();
		ni.setAttribute(AttributeName.STATUS, "InProgress");

		final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		final JDFDoc docjdf = conv.convert(h);
		final JDFNodeInfo nij = docjdf.getJDFRoot().getNodeInfo();
		assertEquals(EnumResStatus.Available, nij.getResStatus(false));
		assertEquals(EnumNodeStatus.InProgress, nij.getNodeStatus());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testNodeInfoMulti()
	{
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.getCreateRootProduct(0);
		h.setTypes(EnumType.ImageSetting.getName());
		h.addType(EnumType.ConventionalPrinting);
		final SetHelper set = h.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input);
		final ResourceHelper partition = set.getCreatePartition(0, true);
		set.setCombinedProcessIndex(new JDFIntegerList(0));
		partition.setStatus(EnumResStatus.Available);
		final JDFNodeInfo ni = (JDFNodeInfo) partition.getResource();
		ni.setAttribute(AttributeName.STATUS, "InProgress");

		final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		conv.setCreateProduct(true);
		final JDFDoc docjdf = conv.convert(h);
		final JDFNodeInfo nij1 = docjdf.getJDFRoot().getJDF(0).getNodeInfo();
		assertEquals(EnumResStatus.Available, nij1.getResStatus(false));
		assertEquals(EnumNodeStatus.InProgress, nij1.getNodeStatus());
		final JDFNodeInfo nij = docjdf.getJDFRoot().getNodeInfo();
		assertNotNull(nij);
		assertNotSame(nij1, nij);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testNodeInfoMultiNoCPI()
	{
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.getCreateRootProduct(0);
		h.setTypes(EnumType.ImageSetting.getName());
		h.addType(EnumType.ConventionalPrinting);
		final SetHelper set = h.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input);
		final ResourceHelper partition = set.getCreatePartition(0, true);
		partition.setStatus(EnumResStatus.Available);
		final JDFNodeInfo ni = (JDFNodeInfo) partition.getResource();
		ni.setAttribute(AttributeName.STATUS, "InProgress");

		final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		conv.setCreateProduct(true);
		final JDFDoc docjdf = conv.convert(h);
		final JDFNodeInfo nij1 = docjdf.getJDFRoot().getJDF(0).getNodeInfo();
		assertEquals(EnumResStatus.Available, nij1.getResStatus(false));
		assertEquals(EnumNodeStatus.InProgress, nij1.getNodeStatus());
		final JDFNodeInfo nij = docjdf.getJDFRoot().getNodeInfo();
		assertNotNull(nij);
		assertEquals(nij1, nij);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testNodeInfoStatusEmpty()
	{
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.setTypes(EnumType.ImageSetting.getName());
		final ResourceHelper partition = h.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input).getCreatePartition(0, true);
		partition.setStatus(EnumResStatus.Available);
		final JDFNodeInfo ni = (JDFNodeInfo) partition.getResource();
		ni.setAttribute(AttributeName.STATUS, "InProgress");

		final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		final JDFDoc docjdf = conv.convert(h);
		final JDFNodeInfo nij = docjdf.getJDFRoot().getNodeInfo();
		assertEquals(EnumResStatus.Available, nij.getResStatus(false));
		assertEquals(EnumNodeStatus.InProgress, nij.getNodeStatus());

	}

	/**
	 *
	 *
	 */
	@Test
	public void testActualColorName()
	{
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.setTypes(EnumType.ImageSetting.getName());
		final JDFColorantControl cc = (JDFColorantControl) h.getCreateSet(XJDFConstants.Resource, ElementName.COLORANTCONTROL, EnumUsage.Input).getCreatePartition(0, true).getResource();
		cc.setAttribute(ElementName.COLORANTPARAMS, "Sep_1");
		cc.setAttribute(ElementName.COLORANTORDER, "Sep_1");
		h.getCreateSet(XJDFConstants.Resource, ElementName.COLOR, EnumUsage.Input).getCreatePartition(AttributeName.SEPARATION, "Sep_1", true).getResource().setAttribute(AttributeName.ACTUALCOLORNAME,
				"Sep 1");

		final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		final JDFDoc docjdf = conv.convert(h);
		final JDFNode n = docjdf.getJDFRoot();

		final JDFColorantControl ccNew = (JDFColorantControl) n.getResource(ElementName.COLORANTCONTROL, EnumUsage.Input, null, 0);
		assertEquals(ccNew.getColorantOrder().getSeparations().get(0), "Sep 1");
		assertEquals(ccNew.getColorantParams().getSeparations().get(0), "Sep 1");

		final JDFColorPool cpNew = (JDFColorPool) n.getResource(ElementName.COLORPOOL, EnumUsage.Input, null, 0);
		assertNotNull(cpNew.getColorWithName("Sep 1"));
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testConvertingConfig()
	{
		final JDFNode n = JDFNode.parseFile(sm_dirTestData + "dielayoutproduction.jdf");

		final JDFToXJDF xjdf20 = new JDFToXJDF();
		xjdf20.setSingleNode(true);
		final KElement xjdf = xjdf20.makeNewJDF(n, null);
		final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		final JDFDoc docjdf = conv.convert(xjdf);
		assertNotNull(xjdf);
		final JDFDieLayoutProductionParams dlp = (JDFDieLayoutProductionParams) docjdf.getJDFRoot().getResource(ElementName.DIELAYOUTPRODUCTIONPARAMS, null, 0);
		assertEquals(JDFNumberRange.createNumberRange("2267.72 ~ 2267.72"), dlp.getConvertingConfig(0).getSheetHeight());
	}

	/**
	 *
	 *
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testParameterSet()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final KElement e = new XMLDoc("XJDF", null).getRoot();
		final KElement c = e.appendElement(SetHelper.PARAMETER_SET);
		c.setAttribute("Name", "Contact");
		c.setAttribute("Usage", "Input");
		c.appendElement(XJDFHelper.PARAMETER).appendElement(ElementName.CONTACT).appendElement(ElementName.COMPANY).setAttribute("CompanyID", "company_id");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFContact contact = (JDFContact) root.getResource("Contact", EnumUsage.Input, 0);
		assertEquals(contact.getCompany().getProductID(), "company_id");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testPrintedPages()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final KElement e = new XMLDoc("XJDF", null).getRoot();
		final KElement c = e.getCreateXPathElement("ProductList/Product/Intent[@Name=\"LayoutIntent\"]/LayoutIntent");
		c.setAttribute("Sides", EnumSides.OneSided.getName(), null);
		c.setAttribute("PrintedPages", "21");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFLayoutIntent loi = (JDFLayoutIntent) root.getResource(ElementName.LAYOUTINTENT, EnumUsage.Input, 0);
		assertEquals(loi.getPages().getActual(), 42);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testExternalImpositionTemplate()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final KElement e = new XMLDoc(XJDFConstants.XJDF, null).getRoot();
		final KElement c = e.appendElement(SetHelper.RESOURCE_SET);
		c.setAttribute("Name", "Layout");
		c.setAttribute("Usage", "Input");
		c.appendElement(XJDFConstants.Resource).appendElement(ElementName.LAYOUT).appendElement(ElementName.EXTERNALIMPOSITIONTEMPLATE).appendElement(ElementName.FILESPEC).setAttribute("URL",
				"file://foo.xml");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFStrippingParams sp = (JDFStrippingParams) root.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
		assertEquals(sp.getExternalImpositionTemplate().getFileSpec(0).getURL(), "file://foo.xml");
		assertNull("Layout is zapped", root.getResource(ElementName.LAYOUT, EnumUsage.Input, 0));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testProductRunList()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		final SetHelper rls = h.getCreateSet(ElementName.RUNLIST, EnumUsage.Input);
		for (final String p : new String[] { "p1", "p2" })
		{
			h.appendProduct().setID(p);
			final JDFAttributeMap partMap = new JDFAttributeMap("Run", p);
			partMap.put(AttributeName.PRODUCTPART, p);

			final ResourceHelper rl = rls.appendPartition(partMap, false);
			rl.appendElement(ElementName.FILESPEC).setAttribute(AttributeName.URL, "file:" + p);
		}
		final JDFDoc d = xCon.convert(h);
		assertNotNull(d);
		assertEquals(d.getJDFRoot().getResourcePool().numChildElements(ElementName.RUNLIST, null), 1);
	}

	/**
	*
	*
	*/
	@Test
	public void testProductComponent()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
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
	 * check that signame gets automagically inserted below sheetname
	 */
	@Test
	public void testSignatureName()
	{
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		final SetHelper lh = h.appendResourceSet(ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper ph = lh.appendPartition(new JDFAttributeMap(AttributeName.SHEETNAME, "S1"), true);
		ph.setAttribute(AttributeName.DESCRIPTIVENAME, "d1", null);
		final XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		final JDFDoc docJDF = c.convert(h);
		final JDFNode jdf = docJDF.getJDFRoot();
		final JDFLayout lo = (JDFLayout) jdf.getResource(ElementName.LAYOUT, EnumUsage.Input, 0);
		assertNotNull(lo);
		assertEquals(lo.getPartIDKeys().get(0), AttributeName.SIGNATURENAME);
	}

	/**
	*
	*
	*/
	@Test
	public void testAmountPool()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		final SetHelper sh = xjdf.getCreateSet(XJDFConstants.Resource, "Media", EnumUsage.Input);
		final ResourceHelper ph = sh.getCreatePartition(null, true);
		ph.setAmount(33, null, true);
		final KElement e = xjdf.getRoot();
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFMedia m = (JDFMedia) root.getResource("Media", EnumUsage.Input, 0);
		assertNotNull(m);
		final JDFResourceLink rl = root.getLink(m, null);
		assertNull(rl.getAmountPool());
		assertNull(m.getElement("AmountPool"));
	}

	/**
	*
	*
	*/
	@Test
	public void testAmountWaste()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		final SetHelper sh = xjdf.getCreateSet(XJDFConstants.Resource, "Media", EnumUsage.Input);
		final ResourceHelper ph = sh.getCreatePartition(null, true);
		ph.setAmount(333, null, true);
		ph.setAmount(33, null, false);
		final KElement e = xjdf.getRoot();
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFMedia m = (JDFMedia) root.getResource("Media", EnumUsage.Input, 0);
		assertNotNull(m);
		final JDFResourceLink rl = root.getLink(m, null);
		assertEquals(333, (int) rl.getAmount(new JDFAttributeMap(AttributeName.CONDITION, "Good")));
		assertEquals(33, (int) rl.getAmount(new JDFAttributeMap(AttributeName.CONDITION, "Waste")));
		assertNull(m.getElement("AmountPool"));
	}

	/**
	*
	*
	*/
	@Test
	public void testOverage()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		final ProductHelper ph = xjdf.appendProduct();
		ph.setAmount(400);
		ph.setXPathValue("@MaxAmount", "600");
		ph.setXPathValue("@MinAmount", "300");
		final JDFDoc d = xCon.convert(xjdf.getRoot());
		final JDFDeliveryIntent di = (JDFDeliveryIntent) d.getJDFRoot().getResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0);
		assertNotNull(di);
		assertEquals(JDFIntentResource.guessActual(di, "Overage"), "50");
		assertEquals(JDFIntentResource.guessActual(di, "Underage"), "25");
	}

	/**
	*
	*
	*/
	@Test
	public void testIntentExternalID()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		final ProductHelper ph = xjdf.appendProduct();
		final IntentHelper ih = ph.appendIntent(ElementName.MEDIAINTENT);
		ih.setAttribute(XJDFConstants.ExternalID, "id");
		final JDFDoc d = xCon.convert(xjdf.getRoot());
		final JDFMediaIntent mi = (JDFMediaIntent) d.getJDFRoot().getResource(ElementName.MEDIAINTENT, EnumUsage.Input, 0);

		assertEquals("id", mi.getProductID());
	}

	/**
	*
	*
	*/
	@Test
	public void testResourceExternalID()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		final SetHelper sh = xjdf.appendResourceSet(ElementName.MEDIA, EnumUsage.Input);
		final ResourceHelper part = sh.appendPartition(null, true);
		part.getResource().setAttribute(AttributeName.DIMENSION, "10 10");
		part.setExternalID("id");
		final JDFDoc d = xCon.convert(xjdf.getRoot());
		final JDFMedia m = (JDFMedia) d.getJDFRoot().getResource(ElementName.MEDIA, EnumUsage.Input, 0);

		assertEquals("id", m.getProductID());
	}

	/**
	*
	*
	*/
	@Test
	public void testDeliveryIntent()
	{
		final KElement xjdf = new JDFToXJDFConverterTest()._testDeliveryIntent();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFDeliveryParams dp = (JDFDeliveryParams) d.getJDFRoot().getResource(ElementName.DELIVERYPARAMS, EnumUsage.Input, 0);
		assertNull(dp);
		final JDFDeliveryIntent di = (JDFDeliveryIntent) d.getJDFRoot().getResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0);
		assertNotNull(di);
		assertNotNull("The ProductRef was not translated", di.getDropIntent(1).getDropItemIntent(0).getComponent());
	}

	/**
	*
	*
	*/
	@Test
	public void testDropID()
	{
		final KElement xjdf = new JDFToXJDFConverterTest()._testDeliveryIntent();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFDeliveryParams dp = (JDFDeliveryParams) d.getJDFRoot().getResource(ElementName.DELIVERYPARAMS, EnumUsage.Input, 0);
		assertNull(dp);
		final JDFDeliveryIntent di = (JDFDeliveryIntent) d.getJDFRoot().getResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0);
		assertNotNull(di);
		assertEquals("DROP_0", di.getDropIntent(0).getDropID());
		assertEquals("DROP_1", di.getDropIntent(1).getDropID());
	}

	/**
	*
	*
	*/
	@Test
	public void testPlacedObject()
	{
		final KElement xjdf = new XJDFHelper("j1", "p1", null).getRoot();
		xjdf.setXPathAttribute("ResourceSet[@Name=\"Layout\"]/Resource/Part/@SheetName", "s1");
		xjdf.setXPathAttribute("ResourceSet[@Name=\"Layout\"]/Resource/Layout/PlacedObject/@Ord", "1");
		xjdf.setXPathAttribute("ResourceSet[@Name=\"Layout\"]/@Usage", "Input");
		xjdf.setXPathAttribute("ResourceSet[@Name=\"Layout\"]/Resource/Layout/PlacedObject/@Type", "ContentObject");

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFLayout partition = (JDFLayout) d.getJDFRoot().getResource(ElementName.LAYOUT, EnumUsage.Input, 0).getPartition(new JDFAttributeMap(EnumPartIDKey.SheetName, "s1"), null);
		final JDFContentObject contentObject = partition.getContentObject(0);
		assertEquals(contentObject.getOrd(), 1);
		assertNull(contentObject.getNonEmpty(AttributeName.TYPE));
	}

	/**
	*
	*
	*/
	@Test
	public void testHolePattern()
	{
		final KElement xjdf = new JDFToXJDFConverterTest()._testHoleLine();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFHoleMakingParams hp = (JDFHoleMakingParams) d.getJDFRoot().getResource(ElementName.HOLEMAKINGPARAMS, EnumUsage.Input, 0);
		assertNotNull(hp);
		assertNotNull(hp.getHoleLine(0));
		assertNotNull(hp.getHoleLine(0).getHole());
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testPageList()
	{
		final KElement xjdf = new JDFToXJDFConverterTest()._testPageList(false);
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFNode root = d.getJDFRoot();
		d.write2File(sm_dirTestDataTemp + "PageList.xjdf.jdf", 2, false);
		assertTrue(root.isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testPreview()
	{
		final KElement xjdf = new JDFToXJDFConverterTest()._testPreview();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFNode root = d.getJDFRoot();
		d.write2File(sm_dirTestDataTemp + "Preview.xjdf.jdf", 2, false);
		assertTrue(root.isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testNPage()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("j1", "p1", null);
		final KElement xjdf = xjdfHelper.getRoot();
		xjdfHelper.appendResourceSet(ElementName.RUNLIST, EnumUsage.Input);
		xjdf.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/@NPage", "4");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFNode root = d.getJDFRoot();
		assertEquals(4, ((JDFRunList) root.getResource(ElementName.RUNLIST, EnumUsage.Input, 0)).getNPage());
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testNPageFileSpec()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("j1", "p1", null);
		final KElement xjdf = xjdfHelper.getRoot();
		xjdfHelper.appendResourceSet(ElementName.RUNLIST, EnumUsage.Input);
		xjdf.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/@NPage", "4");
		xjdf.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/@URL", "file.pdf");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFNode root = d.getJDFRoot();
		assertEquals(4, ((JDFRunList) root.getResource(ElementName.RUNLIST, EnumUsage.Input, 0)).getNPage());
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testPageListEmpty()
	{
		final KElement xjdf = new JDFToXJDFConverterTest()._testPageListEmpty();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFNode root = d.getJDFRoot();
		d.write2File(sm_dirTestDataTemp + "PageListEmpty.xjdf.jdf", 2, false);
		assertTrue(root.isValid(EnumValidationLevel.Incomplete));
	}

	/**
	*
	*
	*/
	@Test
	public void testPartIDKeys()
	{
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final SetHelper rls = h.appendResourceSet(ElementName.RUNLIST, EnumUsage.Input);
		for (int i = 0; i < 3; i++)
		{
			final ResourceHelper ph = rls.appendPartition(new JDFAttributeMap("Run", "R" + i), true);
			final JDFRunList rl = (JDFRunList) ph.getCreateResource();
			rl.setFileURL("url");
		}
		final XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		final JDFDoc jdfd = xc.convert(h);
		final JDFResource rl = jdfd.getJDFRoot().getResource(ElementName.RUNLIST, EnumUsage.Input, 0);
		assertEquals(rl.getPartIDKeys(), new VString("Run", null));
	}

	/**
	*
	*
	*/
	@Test
	public void testAmountPoolPart()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		final SetHelper sh = xjdf.getCreateSet(XJDFConstants.Resource, ElementName.MEDIA, EnumUsage.Input);
		final ResourceHelper ph = sh.getCreatePartition(0, true);
		ph.setAmount(33, new JDFAttributeMap(AttributeName.SHEETNAME, "S1"), true);
		final KElement e = xjdf.getRoot();
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFMedia m = (JDFMedia) root.getResource(ElementName.MEDIA, EnumUsage.Input, 0);
		assertNotNull(m);
		final JDFResourceLink rl = root.getLink(m, null);
		assertNotNull(rl.getAmountPool());
		assertNull(m.getElement(ElementName.AMOUNTPOOL));
	}

	/**
	*
	*
	*/
	@Test
	public void testAmountPoolPart2()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		final SetHelper sh = xjdf.getCreateSet(XJDFConstants.Resource, ElementName.MEDIA, EnumUsage.Input);
		final JDFAttributeMap map1 = new JDFAttributeMap(AttributeName.SHEETNAME, "S1");
		final ResourceHelper ph1 = sh.getCreatePartition(map1, true);
		ph1.setAmount(33, null, true);
		final JDFAttributeMap map2 = new JDFAttributeMap(AttributeName.SHEETNAME, "S2");
		final ResourceHelper ph2 = sh.getCreatePartition(map2, true);
		ph2.setAmount(44, null, true);
		final KElement e = xjdf.getRoot();
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFMedia m = (JDFMedia) root.getResource(ElementName.MEDIA, EnumUsage.Input, 0);
		assertNotNull(m);
		final JDFResourceLink rl = root.getLink(m, null);
		assertNotNull(rl.getAmountPool());
		assertEquals(33, rl.getAmount(map1), 0.1);
		assertEquals(44, rl.getAmount(map2), 0.1);
		assertNull(m.getElement(ElementName.AMOUNTPOOL));
	}

	/**
	*
	*
	*/
	@Test
	public void testAmountPoolNoPart()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		final SetHelper sh = xjdf.getCreateSet(XJDFConstants.Resource, ElementName.MEDIA, EnumUsage.Input);
		final ResourceHelper ph = sh.getCreatePartition(new JDFAttributeMap(AttributeName.SHEETNAME, "S1"), true);
		ph.setAmount(33, new JDFAttributeMap(AttributeName.SHEETNAME, "S1"), true);
		final KElement e = xjdf.getRoot();
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFMedia m = (JDFMedia) root.getResource(ElementName.MEDIA, EnumUsage.Input, 0);
		assertNotNull(m);
		final JDFResourceLink rl = root.getLink(m, null);
		assertNull(rl.getAmountPool());
		assertEquals(33, rl.getAmount(), 0.0);
		assertNull(m.getElement(ElementName.AMOUNTPOOL));
	}

	/**
	*
	*
	*/
	@Test
	public void testPartAmountMap()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		final SetHelper sh = xjdf.getCreateSet(XJDFConstants.Resource, ElementName.MEDIA, EnumUsage.Input);
		final ResourceHelper ph = sh.getCreatePartition(new JDFAttributeMap(AttributeName.SHEETNAME, "S1"), true);
		ph.setAmount(33, new JDFAttributeMap(AttributeName.SIDE, "Front"), true);
		final KElement e = xjdf.getRoot();
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFMedia m = (JDFMedia) root.getResource(ElementName.MEDIA, EnumUsage.Input, 0);
		assertNotNull(m);
		final JDFResourceLink rl = root.getLink(m, null);
		final JDFAmountPool amountPool = rl.getAmountPool();
		assertNotNull(amountPool);
		assertEquals("S1", amountPool.getPartAmount(0).getPartMap().get(AttributeName.SHEETNAME));
	}

	/**
	*
	*
	*/
	@Test
	public void testAmountPoolAudit()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		final SetHelper sh = xjdf.getCreateSet(XJDFConstants.Resource, ElementName.MEDIA, EnumUsage.Input);
		final ResourceHelper ph = sh.getCreatePartition(0, true);
		ph.setAmount(33, new JDFAttributeMap(AttributeName.SHEETNAME, "S1"), true);

		final AuditPoolHelper ah = xjdf.getCreateAuditPool();
		final MessageHelper ra = ah.appendMessage(XJDFConstants.AuditResource);
		ra.getRoot().appendElement(ElementName.RESOURCEINFO).copyElement(sh.getRoot(), null);
		xjdf.cleanUp();
		final KElement e = xjdf.getRoot();
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFAuditPool ap = root.getAuditPool();
		final JDFResourceAudit ra2 = (JDFResourceAudit) ap.getAudit(0, EnumAuditType.ResourceAudit, null, null);
		assertNotNull(ra2);
		final JDFResourceLink rl = ra2.getNewLink();
		assertNotNull(rl);
		final JDFAmountPool amountPool = rl.getAmountPool();
		assertNotNull(amountPool);
		final JDFPartAmount partAmount = amountPool.getPartAmount(new JDFAttributeMap(AttributeName.SHEETNAME, "S1"));
		assertEquals(33, partAmount.getAmount(), 0.3);
	}

	/**
	*
	*
	*/
	@Test
	public void testAmountPoolAuditNoPart()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		final SetHelper sh = xjdf.getCreateSet(XJDFConstants.Resource, ElementName.MEDIA, EnumUsage.Input);
		final ResourceHelper ph = sh.getCreatePartition(new JDFAttributeMap(AttributeName.SHEETNAME, "S1"), true);
		ph.setAmount(33, new JDFAttributeMap(AttributeName.SHEETNAME, "S1"), true);

		final AuditPoolHelper ah = xjdf.getCreateAuditPool();
		final MessageHelper ra = ah.appendMessage(XJDFConstants.AuditResource);
		ra.getRoot().appendElement(ElementName.RESOURCEINFO).copyElement(sh.getRoot(), null);
		xjdf.cleanUp();
		final KElement e = xjdf.getRoot();
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFAuditPool ap = root.getAuditPool();
		final JDFResourceAudit ra2 = (JDFResourceAudit) ap.getAudit(0, EnumAuditType.ResourceAudit, null, null);
		assertNotNull(ra2);
		final JDFResourceLink rl = ra2.getNewLink();
		assertNotNull(rl);
		final JDFAmountPool amountPool = rl.getAmountPool();
		assertNull(amountPool);
		assertEquals(33, rl.getAmount(), 0.3);
	}

	/**
	*
	*
	*/
	@Test
	public void testAssemblingIntentBindIn()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		final ProductHelper ph = xjdf.appendProduct();
		final IntentHelper h = ph.appendIntent(XJDFConstants.AssemblingIntent);
		h.getCreateResource().appendElement(XJDFConstants.BindIn);
		final JDFDoc jdf = xCon.convert(xjdf);
		final JDFNode root = jdf.getJDFRoot();
		final JDFInsertingIntent insertingIntent = (JDFInsertingIntent) root.getResource(ElementName.INSERTINGINTENT, EnumUsage.Input, 0);
		assertNotNull(insertingIntent);
		assertNotNull(insertingIntent.getInsertList());
		final JDFInsert insert = insertingIntent.getInsertList().getInsert(0);
		assertNotNull(insert);
		assertEquals(JDFIntentResource.guessActual(insert, "Method"), "BindIn");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testCustomerInfoContacts()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		h.getCreateSet(XJDFConstants.Resource, ElementName.CUSTOMERINFO, EnumUsage.Input).getCreatePartition(0, true).getResource().setAttribute(AttributeName.CUSTOMERORDERID, "cc");
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
	 *
	 */
	@Test
	public void testCustomerInfoMultiContacts()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		h.getCreateSet(XJDFConstants.Resource, ElementName.CUSTOMERINFO, EnumUsage.Input).getCreatePartition(0, true).getResource().setAttribute(AttributeName.CUSTOMERORDERID, "cc");
		final SetHelper sh = h.getCreateSet(XJDFConstants.Resource, ElementName.CONTACT, EnumUsage.Input);
		final ResourceHelper ph = sh.getCreatePartition(0, true);
		final VJDFAttributeMap vPart = new VJDFAttributeMap();
		vPart.add(new JDFAttributeMap(XJDFConstants.ContactType, EnumContactType.Customer.getName()));
		vPart.add(new JDFAttributeMap(XJDFConstants.ContactType, EnumContactType.Billing.getName()));
		vPart.add(new JDFAttributeMap(XJDFConstants.ContactType, EnumContactType.Delivery.getName()));
		ph.setPartMapVector(vPart);
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFCustomerInfo ci = d.getJDFRoot().getCustomerInfo();
		assertNotNull(ci);
		final JDFContact contact = (JDFContact) d.getJDFRoot().getResource(ElementName.CONTACT, null, 0);
		assertTrue(contact.getContactTypes().contains(EnumContactType.Customer.getName()));
		assertTrue(contact.getContactTypes().contains(EnumContactType.Billing.getName()));
		assertTrue(contact.getContactTypes().contains(EnumContactType.Delivery.getName()));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testMissingSetID()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		e.setXPathAttribute("ResourceSet[@Name=\"CustomerInfo\"]/Resource/CustomerInfo/@CustomerID", "cid");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d.getJDFRoot().getCustomerInfo());
		assertEquals("cid", d.getJDFRoot().getCustomerInfo().getCustomerID());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testMultiSet()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		h.appendResourceSet(ElementName.DIELAYOUT, EnumUsage.Input).appendPartition(null, true).setAmount(5, null, true);
		h.appendResourceSet(ElementName.DIELAYOUT, EnumUsage.Input).appendPartition(null, true).setAmount(6, null, true);
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d.getJDFRoot().getResource(ElementName.DIELAYOUT, null, 1));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testSetDescName()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		final SetHelper set = h.appendResourceSet(ElementName.DIELAYOUT, EnumUsage.Input);
		set.setDescriptiveName("desc");
		set.appendPartition(null, true).setAmount(5, null, true);
		final JDFDoc d = xCon.convert(e);
		assertEquals("desc", d.getJDFRoot().getResource(ElementName.DIELAYOUT, null, 0).getDescriptiveName());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testMultiResourcePart()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.SHEETNAME, "s1");

		final ResourceHelper res = h.appendResourceSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input).appendPartition(map, true);
		res.getResource().setAttribute(AttributeName.WORKSTYLE, "Simplex");
		final JDFAttributeMap s2 = new JDFAttributeMap(AttributeName.SHEETNAME, "s2");
		res.appendPartMap(s2);
		final JDFDoc d = xCon.convert(e);
		final JDFConventionalPrintingParams cp = (JDFConventionalPrintingParams) d.getJDFRoot().getResourceRoot(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input, 0);
		assertEquals(2, cp.getLeaves(false).size());
		final PartitionGetter pg = new PartitionGetter(cp);
		pg.setFollowIdentical(false);
		s2.put(AttributeName.SIGNATURENAME, "Sig_s2");
		final JDFResource partition = pg.getPartition(s2, null);
		final JDFIdentical id = partition.getIdentical();
		assertTrue(id.getPartMap().overlapMap(map));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testResourceLinkPart()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.SHEETNAME, "s1");

		final ResourceHelper res = h.appendResourceSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input).appendPartition(map, true);
		res.getResource().setAttribute(AttributeName.WORKSTYLE, "Simplex");
		final JDFDoc d = xCon.convert(e);
		final JDFNode jdfRoot = d.getJDFRoot();
		final JDFConventionalPrintingParams cp = (JDFConventionalPrintingParams) jdfRoot.getResource(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input, 0);
		assertEquals("s1", cp.getSheetName());
		assertEquals(1, jdfRoot.getLink(cp, null).getPartMapVector().size());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testResourceGeneralID()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.SHEETNAME, "s1");

		final ResourceHelper res = h.appendResourceSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input).appendPartition(map, true);
		res.setGeneralID("key", "val");
		final JDFDoc d = xCon.convert(e);
		final JDFNode jdfRoot = d.getJDFRoot();
		final JDFConventionalPrintingParams cp = (JDFConventionalPrintingParams) jdfRoot.getResource(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input, 0);
		assertEquals("s1", cp.getSheetName());
		assertEquals("val", cp.getGeneralID("key", 0));
		assertNull(cp.getResourceRoot().getGeneralID("key", 0));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testResourceLinkRun()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.RUN, "r1");

		final ResourceHelper res = h.appendResourceSet(ElementName.RUNLIST, EnumUsage.Input).appendPartition(map, true);
		res.getResource().setAttribute(AttributeName.NPAGE, "2");
		final JDFDoc d = xCon.convert(e);
		final JDFNode jdfRoot = d.getJDFRoot();
		final JDFRunList rl = (JDFRunList) jdfRoot.getResource(ElementName.RUNLIST, EnumUsage.Input, 0);
		assertEquals("r1", rl.getLeaf(0).getRun());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testResourceLinkRunIndex()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.RUN, "r1");
		map.put(AttributeName.RUNINDEX, "3 5");
		final ResourceHelper res = h.appendResourceSet(ElementName.RUNLIST, EnumUsage.Input).appendPartition(map, true);
		res.getResource().setAttribute(AttributeName.NPAGE, "2");
		final JDFDoc d = xCon.convert(e);
		final JDFNode jdfRoot = d.getJDFRoot();
		final JDFRunList rl = (JDFRunList) jdfRoot.getResource(ElementName.RUNLIST, EnumUsage.Input, 0);
		assertEquals("r1", rl.getLeaf(0).getRun());
		assertFalse(rl.getLeaf(0).hasNonEmpty(AttributeName.RUNINDEX));
		final JDFResourceLink resL = jdfRoot.getLink(rl, null);
		assertEquals("3 ~ 5", resL.getPartMapVector().get(0).get("RunIndex"));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testResourceLinkRunIndex2()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.RUN, "r1");
		map.put(AttributeName.RUNINDEX, "3 5");
		final JDFAttributeMap m2 = new JDFAttributeMap(map);
		m2.put(AttributeName.RUNINDEX, "7 -1");

		final ResourceHelper res = h.appendResourceSet(ElementName.RUNLIST, EnumUsage.Input).appendPartition(map, true);
		res.appendPartMap(m2);
		res.getResource().setAttribute(AttributeName.NPAGE, "2");
		final JDFDoc d = xCon.convert(e);
		final JDFNode jdfRoot = d.getJDFRoot();
		final JDFRunList rl = (JDFRunList) jdfRoot.getResource(ElementName.RUNLIST, EnumUsage.Input, 0);
		assertEquals("r1", rl.getLeaf(0).getRun());
		assertFalse(rl.getLeaf(0).hasNonEmpty(AttributeName.RUNINDEX));
		final JDFResourceLink resL = jdfRoot.getLink(rl, null);
		assertEquals("3 ~ 5 7 ~ -1", resL.getPartMapVector().get(0).get("RunIndex"));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testResourceLinkPart2()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.SHEETNAME, "s1");

		final ResourceHelper res = h.appendResourceSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input).appendPartition(map, true);
		res.getResource().setAttribute(AttributeName.WORKSTYLE, "Simplex");
		xCon.convert(e);
		map.put(AttributeName.SHEETNAME, "s2");
		res.setPartMap(map);
		final JDFDoc d = xCon.convert(e);
		final JDFNode jdfRoot = d.getJDFRoot();
		final JDFConventionalPrintingParams cp = (JDFConventionalPrintingParams) jdfRoot.getResource(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input, 0);
		assertEquals(2, cp.getLeaves(false).size());
		assertEquals(2, jdfRoot.getLink(cp, null).getPartMapVector().size());
	}

	/**
	 *
	 */
	@Test
	public void testWorkstepID()
	{
		final XJDFHelper h = new XJDFHelper("j1", "p1", null);
		final SetHelper sh = h.getCreateSet(ElementName.NODEINFO, null);
		sh.getCreatePartition(0, true).setExternalID("w1");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		assertEquals("w1", d.getJDFRoot().getNodeInfo().getWorkStepID());

	}

	/**
	 *
	 */
	@Test
	public void testCounterID()
	{
		final XJDFHelper h = new XJDFHelper("j1", "p1", null);
		final SetHelper sh = h.getCreateSet(ElementName.USAGECOUNTER, null);
		sh.getCreatePartition(0, true).setExternalID("c1");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		assertEquals("c1", ((JDFUsageCounter) d.getJDFRoot().getResource(ElementName.USAGECOUNTER, EnumUsage.Input, 0)).getCounterID());

	}

	/**
	 *
	 *
	 */
	@Test
	public void testXJMFKnownMessages()
	{
		final KElement root = new JDFDoc(XJDFConstants.XJMF).getRoot();
		root.setAttribute(AttributeName.DEVICEID, "devID");
		final KElement q = root.appendElement("QueryKnownMessages");
		q.setAttribute(AttributeName.DEVICEID, "devID2");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(root);
		final JDFJMF jmfRoot = d.getJMFRoot();
		assertNotNull(jmfRoot);
		assertEquals(jmfRoot.getSenderID(), "devID");
		assertEquals(jmfRoot.getQuery(0).getSenderID(), "devID2");
		assertTrue(jmfRoot.isValid(EnumValidationLevel.Complete));
	}

	/**
	*
	*
	*/
	@Test
	public void testBackwardProduct()
	{
		final XJDFHelper h = new XJDFHelper("j", "root", null);
		final ProductHelper cover = h.appendProduct();
		final ProductHelper body = h.appendProduct();
		final ProductHelper book = h.appendProduct();
		book.setRoot();
		book.setChild(cover, 1);
		book.setChild(body, 1);
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		assertEquals(d.getJDFRoot().getJobPartID(true), "root");
	}

	/**
	*
	*
	*/
	@Test
	public void testNiciProduct()
	{
		final XJDFHelper h = new XJDFHelper("j", "root", null);
		final ProductHelper p1 = h.appendProduct();
		p1.setRoot();
		p1.setID("ID1");
		final ProductHelper p2 = h.appendProduct();
		p2.setRoot();
		p2.setID("ID2");
		final SetHelper sh = h.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input);
		sh.setProcessUsage("Product");
		sh.appendPartition(new JDFAttributeMap(AttributeName.PRODUCTPART, "ID1"), true).getResource().setAttribute(AttributeName.JOBPRIORITY, "42");
		sh.appendPartition(new JDFAttributeMap(AttributeName.PRODUCTPART, "ID2"), true).getResource().setAttribute(AttributeName.JOBPRIORITY, "24");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		final JDFNode jobPart1 = d.getJDFRoot().getJobPart("root.1", null);
		assertEquals(jobPart1.getNodeInfo().getJobPriority(), 42);
		final JDFNode jobPart2 = d.getJDFRoot().getJobPart("root.2", null);
		assertEquals(jobPart2.getNodeInfo().getJobPriority(), 24);
	}

	/**
	*
	*
	*/
	@Test
	public void testMultiBackwardProduct()
	{
		final XJDFHelper h = new XJDFHelper("j", "root", null);
		for (int i = 0; i < 2; i++)
		{
			final ProductHelper cover = h.appendProduct();
			final ProductHelper body = h.appendProduct();
			final ProductHelper book = h.appendProduct();
			book.setRoot();
			book.setChild(cover, 1);
			book.setChild(body, 1);
		}
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		assertEquals(d.getJDFRoot().getJobPartID(true), "root");
		d.write2File(sm_dirTestDataTemp + "backproduct.jdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFColorIntentSurfaceColor()
	{
		for (int i = 0; i < 3; i += 2)
		{
			final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
			final KElement e = new XMLDoc("XJDF", null).getRoot();
			e.setAttribute("Types", "Product");
			e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@NumColors", "4/1");
			e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/SurfaceColor[@Surface=\"Front\"]/@Coatings", "DullVarnish");
			e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/SurfaceColor[@Surface=\"Back\"]/@Coatings", "GlossVarnish");
			if (i != 0)
			{
				e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/SurfaceColor[@Surface=\"Front\"]/@ColorsUsed", "Spot1 Spot");
				e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/SurfaceColor[@Surface=\"Back\"]/@ColorsUsed", "Spot2 Spot");
			}
			final JDFDoc d = xCon.convert(e);
			assertNotNull(d);
			final JDFNode root = d.getJDFRoot();
			final JDFColorIntent ci = (JDFColorIntent) root.getResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
			final JDFColorIntent cif = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Front"), null);
			final JDFColorIntent cib = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Back"), null);
			assertNull(ci.getColorsUsed());
			if (i > 0)
			{
				assertEquals(cib.getColorsUsed().getSeparations().size(), i);
				assertEquals(cif.getColorsUsed().getSeparations().size(), i);
			}
			else
			{
				assertNull(cif.getColorsUsed());
				assertNull(cib.getColorsUsed());
			}
			assertEquals(cif.getCoatings().getActual(), "DullVarnish");
		}
	}

}
