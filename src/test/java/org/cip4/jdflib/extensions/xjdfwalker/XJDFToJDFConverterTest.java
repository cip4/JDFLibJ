/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoApprovalDetails.EnumApprovalState;
import org.cip4.jdflib.auto.JDFAutoAssembly.EnumOrder;
import org.cip4.jdflib.auto.JDFAutoBinderySignature.EnumBinderySignatureType;
import org.cip4.jdflib.auto.JDFAutoLayoutIntent.EnumSides;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.auto.JDFAutoStrippingParams.EnumWorkStyle;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
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
import org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf.WalkSet;
import org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf.XJDFToJDFImpl;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.PartitionGetter;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFDropIntent;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.intent.JDFLayoutIntent;
import org.cip4.jdflib.resource.intent.JDFMediaIntent;
import org.cip4.jdflib.resource.process.JDFApprovalDetails;
import org.cip4.jdflib.resource.process.JDFApprovalSuccess;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantAlias;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFContact.EnumContactType;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFDeliveryParams;
import org.cip4.jdflib.resource.process.JDFDieLayout;
import org.cip4.jdflib.resource.process.JDFDieLayoutProductionParams;
import org.cip4.jdflib.resource.process.JDFIdentical;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPerson;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFUsageCounter;
import org.cip4.jdflib.resource.process.postpress.JDFHoleMakingParams;
import org.cip4.jdflib.span.JDFSpanBindingType.EnumSpanBindingType;
import org.cip4.jdflib.util.JDFDate;
import org.junit.jupiter.api.Test;

/**
 * @author rainer prosi
 * @date Dec 10, 2012
 */
class XJDFToJDFConverterTest extends JDFTestCaseBase
{
	/**
	 *
	 *
	 */
	@Test
	void testCompany()
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

	@Test
	void testReorder3()
	{
		final VJDFAttributeMap v = new VJDFAttributeMap();
		for (int i = 0; i < 3; i++)
		{
			final JDFAttributeMap map = new JDFAttributeMap("Run", "Run" + i);
			for (int j = 0; j < 2; j++)
			{
				map.put("Option", "" + j);
				for (int k = 0; k < 2; k++)
				{
					map.put("RunPage", "" + k);
					v.add(map.clone());
				}
			}
		}
		final XJDFHelper h = new XJDFHelper("j1", null, v);
		h.getNodeInfo().setDescriptiveName("foo");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFNodeInfo ni = root.getNodeInfo();
		assertEquals(new VString("Run Option RunPage"), ni.getPartIDKeys());
		assertEquals(22, ni.getLeaves(true).size());
	}

	@Test
	void testAddLowerSSS()
	{
		final WalkSet set = new WalkSet();
		set.setParent(new XJDFToJDFImpl(null));
		final VJDFAttributeMap v = new VJDFAttributeMap();
		for (int i = 0; i < 2; i++)
		{
			final JDFAttributeMap map = new JDFAttributeMap("SheetName", "S" + i);
			for (int j = 0; j < 2; j++)
			{
				map.put("Separation", "sep" + j);
				for (int k = 0; k < 2; k++)
				{
					map.put("Side", (EnumSide) EnumSide.getEnumList().get(k));
					v.add(map.clone());
				}
			}
		}
		final XJDFHelper h = new XJDFHelper("j1", null, v);
		h.getNodeInfo().setDescriptiveName("foo");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFNodeInfo ni = root.getNodeInfo();
		assertEquals(new VString("SignatureName SheetName Side Separation"), ni.getPartIDKeys());
		assertEquals(8, ni.getLeaves(false).size());
		assertEquals(17, ni.getLeaves(true).size());
	}

	/**
	 *
	 *
	 */
	@Test
	void testContactPartitioned()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", null);
		final SetHelper cs = h.appendSet(ElementName.CONTACT, EnumUsage.Input);
		final ResourceHelper cr1 = cs.appendResource(XJDFConstants.ContactType, "Customer", true);
		cr1.appendPartMap(new JDFAttributeMap(XJDFConstants.ContactType, "Billing"));
		cr1.getResource().appendElement(ElementName.COMPANY).setAttribute("CompanyID", "company_id1");
		final ResourceHelper cr2 = cs.appendResource(XJDFConstants.ContactType, "Delivery", true);
		cr2.getResource().appendElement(ElementName.COMPANY).setAttribute("CompanyID", "company_id2");
		final JDFDoc d = xCon.convert(h);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFContact contact = (JDFContact) root.getResource("Contact", EnumUsage.Input, 0).getLeaf(0);
		assertEquals(contact.getCompany().getProductID(), "company_id1");
		final JDFContact contact2 = (JDFContact) root.getResource("Contact", EnumUsage.Input, 0).getLeaf(1);
		assertEquals(contact2.getCompany().getProductID(), "company_id2");
	}

	/**
	*
	*/
	@Test
	void testDevice()
	{
		final XJDFHelper h = new XJDFHelper(EnumVersion.Version_2_2, "j1");
		h.setTypes(EnumType.RasterReading.getName());
		final JDFDevice dev = (JDFDevice) h.getCreateResource(ElementName.DEVICE, EnumUsage.Input, null);
		dev.setRestApiBaseURL("http://rest");
		dev.setDeviceID("d1");
		final JDFNode jdf = (JDFNode) writeRoundTripX(h, "RestAPI", EnumValidationLevel.Incomplete);
		assertEquals(EnumPartUsage.Implicit, jdf.getResource(ElementName.DEVICE, EnumUsage.Input, 0).getPartUsage());
	}

	/**
	 *
	 *
	 */
	@Test
	void testCSR()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final KElement e = new JDFDoc(XJDFConstants.XJDF, null).getRoot();
		final XJDFHelper xh = new XJDFHelper(e);
		xh.setTypes("Product");
		xh.getCreateSet(ElementName.NODEINFO, EnumUsage.Input).getCreatePartition(0, true).getResource().setAttribute(AttributeName.STATUS, "Waiting");

		final KElement c = e.appendElement(SetHelper.RESOURCE_SET);
		c.setAttribute("Name", "Contact");
		c.setAttribute("Usage", "Input");
		final KElement res = c.appendElement(XJDFConstants.Resource);
		final JDFContact contactX = (JDFContact) res.appendElement(ElementName.CONTACT);
		contactX.setContactTypeDetails("CSR");
		final JDFPerson pers = contactX.appendPerson();
		pers.setFirstName("f1");
		final ResourceHelper rh = new ResourceHelper(res);
		rh.setPartMap(new JDFAttributeMap(XJDFConstants.ContactType, "Employee"));
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		assertNull(root.getResource("Contact", EnumUsage.Input, 0));
		assertNull(root.getResource("Employee", EnumUsage.Input, 0));
		assertNotNull(root.getResource("NodeInfo", EnumUsage.Input, 0).getElement("Employee"));
	}

	/**
	 *
	 *
	 */
	@Test
	void testShapeTemplate()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", null);
		final SetHelper sh = h.getCreateSet(ElementName.SHAPEDEFPRODUCTIONPARAMS, EnumUsage.Input);
		final KElement sdp = sh.appendPartition(null, true).getResource();
		final KElement st = sdp.appendElement(ElementName.SHAPETEMPLATE);
		KElement sd = st.appendElement(XJDFConstants.ShapeDimension);
		sd.setAttribute(AttributeName.USAGE, "L");
		sd.setAttribute(AttributeName.VALUE, "1.234");
		sd = st.appendElement(XJDFConstants.ShapeDimension);
		sd.setAttribute(AttributeName.USAGE, "W");
		sd.setAttribute(AttributeName.VALUE, "12.345");
		final JDFNode n = xCon.convert(h).getJDFRoot();
		final JDFElement resource = (JDFElement) n.getResource(ElementName.SHAPEDEFPRODUCTIONPARAMS, EnumUsage.Input, 0).getElement(ElementName.SHAPETEMPLATE);
		assertEquals("1.234", resource.getGeneralID("L", 0));
		assertEquals("12.345", resource.getGeneralID("W", 0));
	}

	/**
	 *
	 *
	 */
	@Test
	void testExtendedAddress()
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
	void testColorantControl()
	{
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.setTypes(EnumType.ImageSetting.getName());
		final JDFColorantControl cc = (JDFColorantControl) h.getCreateSet(XJDFConstants.Resource, ElementName.COLORANTCONTROL, EnumUsage.Input).getCreatePartition(0, true)
				.getResource();
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
	 *
	 *
	 */
	@Test
	void testColorantAlias()
	{
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.setTypes(EnumType.ImageSetting.getName());
		final JDFColorantControl cc = (JDFColorantControl) h.getCreateSet(XJDFConstants.Resource, ElementName.COLORANTCONTROL, EnumUsage.Input).getCreatePartition(0, true)
				.getResource();
		final JDFColorantAlias ca = cc.appendColorantAlias();
		ca.setReplacementColorantName("Cyan");
		ca.setAttribute(AttributeName.COLORANTNAME, "nasty cyan");

		final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		final JDFDoc docjdf = conv.convert(h);
		final JDFNode n = docjdf.getJDFRoot();

		final JDFColorantControl ccNew = (JDFColorantControl) n.getResource(ElementName.COLORANTCONTROL, EnumUsage.Input, null, 0);
		final JDFColorantAlias caNew = ccNew.getColorantAlias(0);
		assertEquals("Cyan", caNew.getReplacementColorantName());
		assertEquals("nasty cyan", caNew.getSeparation(0));
		assertNull(caNew.getNonEmpty(AttributeName.COLORANTNAME));
	}

	/**
	 * @throws Exception
	 *
	 *
	 */
	@Test
	void testColorNoSep() throws Exception
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
	 */
	@Test
	void testMultiProduct()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		h.getCreateRootProduct(0).setDescriptiveName("d1");
		h.appendProduct().setDescriptiveName("d2");
		h.appendProduct().setDescriptiveName("d3");
		final JDFDoc d = xCon.convert(h);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		assertEquals(2, root.getvJDFNode(JDFConstants.PRODUCT, null, true).size());
		assertEquals("d1", root.getDescriptiveName());
	}

	/**
	 *
	 *
	 */
	@Test
	void testMultiProcess()
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
	void testMultiComponent()
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
	void testInputStatus()
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
	void testResourceIdentical()
	{
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.setTypes(EnumType.ImageSetting.getName());
		ResourceHelper r = h.getCreateSet(ElementName.NODEINFO, EnumUsage.Input).getCreatePartition(0, true);
		r.appendPartMap(new JDFAttributeMap(AttributeName.SHEETNAME, "s1"));
		r.appendPartMap(new JDFAttributeMap(AttributeName.SHEETNAME, "s2"));
		r.appendPartMap(new JDFAttributeMap(AttributeName.SHEETNAME, "s3"));
		r = h.getCreateSet(ElementName.NODEINFO, EnumUsage.Input).getCreatePartition(1, true);
		r.appendPartMap(new JDFAttributeMap(AttributeName.SHEETNAME, "s4"));
		r.appendPartMap(new JDFAttributeMap(AttributeName.SHEETNAME, "s5"));
		r.appendPartMap(new JDFAttributeMap(AttributeName.SHEETNAME, "s6"));
		final JDFDoc template = new JDFDoc("JDF");
		template.getJDFRoot().setType(EnumType.Product);
		final XJDFToJDFConverter conv = new XJDFToJDFConverter(template);
		final JDFDoc docjdf0 = conv.convert(h);
		final JDFNodeInfo nij0 = docjdf0.getJDFRoot().getNodeInfo();
		assertEquals(4, nij0.getChildArrayByClass(JDFIdentical.class, true, 0).size());
		final JDFDoc docjdf = conv.convert(h);
		final JDFNodeInfo nij = docjdf.getJDFRoot().getNodeInfo();
		assertEquals(4, nij.getChildArrayByClass(JDFIdentical.class, true, 0).size());

	}

	/**
	 *
	 *
	 */
	@Test
	void testNodeInfoStatus()
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
	void testNodeInfoStatusSparse()
	{
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.setTypes(EnumType.ImageSetting.getName());
		final ResourceHelper partition = h.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input).getCreatePartition(0, true);
		partition.setStatus(EnumResStatus.Available);
		final ResourceHelper partition2 = h.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input).getCreatePartition(1, true);
		partition2.setStatus(EnumResStatus.Unavailable);
		partition2.setPartMap(new JDFAttributeMap("SheetName", "S1"));
		final JDFNodeInfo ni = (JDFNodeInfo) partition.getResource();
		ni.setAttribute(AttributeName.STATUS, "Waiting");
		final JDFNodeInfo ni2 = (JDFNodeInfo) partition2.getResource();
		ni2.setAttribute(AttributeName.STATUS, "InProgress");

		final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		final JDFDoc docjdf = conv.convert(h);
		final JDFNodeInfo nij = docjdf.getJDFRoot().getNodeInfo();
		assertEquals(EnumResStatus.Available, nij.getResStatus(false));
		assertEquals(EnumNodeStatus.InProgress, nij.getNodeStatus());
		final JDFNodeInfo nir = (JDFNodeInfo) nij.getResourceRoot();
		assertEquals(EnumNodeStatus.Waiting, nir.getNodeStatus());
	}

	/**
	 *
	 *
	 */
	@Test
	void testNodeInfoWSID()
	{
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.setTypes(EnumType.ImageSetting.getName());
		final ResourceHelper partition = h.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input).getCreatePartition(0, true);
		partition.setExternalID("ws1");
		final JDFNodeInfo ni = (JDFNodeInfo) partition.getResource();

		final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		final JDFDoc docjdf = conv.convert(h);
		final JDFNodeInfo nij = docjdf.getJDFRoot().getNodeInfo();
		assertEquals("ws1", nij.getWorkStepID());
	}

	/**
	 *
	 *
	 */
	@Test
	void testNodeInfoGangSource()
	{
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.setTypes(EnumType.ImageSetting.getName());
		final ResourceHelper partition = h.getCreateSet(ElementName.NODEINFO, EnumUsage.Input).getCreatePartition(0, true);
		partition.setStatus(EnumResStatus.Available);
		final JDFNodeInfo ni = (JDFNodeInfo) partition.getResource();
		final KElement gs1 = ni.appendElement(ElementName.GANGSOURCE);
		gs1.setAttribute(AttributeName.COPIES, 200, null);
		gs1.setAttribute(XJDFConstants.BinderySignatureID, "BS1");
		gs1.setAttribute(AttributeName.JOBID, "SourceJob1");
		final KElement gs2 = ni.appendElement(ElementName.GANGSOURCE);
		gs2.setAttribute(AttributeName.COPIES, 180, null);
		gs2.setAttribute(XJDFConstants.BinderySignatureID, "BS2");
		gs2.setAttribute(AttributeName.JOBID, "SourceJob2");

		final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		final JDFDoc docjdf = conv.convert(h);
		final JDFNodeInfo nij = docjdf.getJDFRoot().getNodeInfo();
		assertEquals("SourceJob1", nij.getElement(ElementName.GANGSOURCE).getAttribute(AttributeName.JOBID));
	}

	/**
	 *
	 *
	 */
	@Test
	void testNodeInfoMulti()
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
	void testNodeInfoCSR()
	{
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.getCreateRootProduct(0);
		h.setTypes(EnumType.Product.getName());

		final SetHelper setcontact = h.getCreateSet(XJDFConstants.Resource, ElementName.CONTACT, EnumUsage.Input);
		final ResourceHelper rContact = setcontact.getCreatePartition(new JDFAttributeMap(XJDFConstants.ContactType, "Employee"), true);
		rContact.setExternalID("P1");
		final JDFContact c = (JDFContact) rContact.getCreateResource();
		c.setAttribute(AttributeName.CONTACTTYPEDETAILS, "CSR");
		final JDFPerson p = c.appendPerson();
		p.setFirstName("First");
		p.setFamilyName("Last");

		final SetHelper set = h.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input);
		final ResourceHelper partition = set.getCreatePartition(0, true);
		partition.setStatus(EnumResStatus.Available);
		final JDFNodeInfo ni = (JDFNodeInfo) partition.getResource();
		ni.setAttribute(AttributeName.STATUS, "InProgress");
		ni.setAttribute(AttributeName.PERSONALID, "P1");

		final XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		conv.setCreateProduct(true);
		final JDFDoc docjdf = conv.convert(h);
		final JDFNodeInfo nij = docjdf.getJDFRoot().getNodeInfo();
		assertNotNull(nij);
		assertNotNull(nij.getEmployee());
		assertEquals("P1", nij.getEmployee().getPersonalID());
		assertEquals("First", nij.getEmployee().getPerson().getFirstName());
	}

	/**
	 *
	 *
	 */
	@Test
	void testNodeInfoMultiNoCPI()
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
	void testNodeInfoStatusEmpty()
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
	void testActualColorName()
	{
		final XJDFHelper h = new XJDFHelper("j", "p", null);
		h.setTypes(EnumType.ImageSetting.getName());
		final JDFColorantControl cc = (JDFColorantControl) h.getCreateSet(XJDFConstants.Resource, ElementName.COLORANTCONTROL, EnumUsage.Input).getCreatePartition(0, true)
				.getResource();
		cc.setAttribute(ElementName.COLORANTPARAMS, "Sep_1");
		cc.setAttribute(ElementName.COLORANTORDER, "Sep_1");
		h.getCreateSet(XJDFConstants.Resource, ElementName.COLOR, EnumUsage.Input).getCreatePartition(AttributeName.SEPARATION, "Sep_1", true).getResource()
				.setAttribute(AttributeName.ACTUALCOLORNAME, "Sep 1");

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
	void testConvertingConfig()
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
	void testParameterSet()
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
	void testPrintedPages()
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
	void testExternalImpositionTemplate()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final KElement e = new XMLDoc(XJDFConstants.XJDF, null).getRoot();
		final KElement c = e.appendElement(SetHelper.RESOURCE_SET);
		c.setAttribute("Name", "Layout");
		c.setAttribute("Usage", "Input");
		c.appendElement(XJDFConstants.Resource).appendElement(ElementName.LAYOUT).appendElement(ElementName.EXTERNALIMPOSITIONTEMPLATE).appendElement(ElementName.FILESPEC)
				.setAttribute("URL", "file://foo.xml");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFStrippingParams sp = (JDFStrippingParams) root.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
		assertEquals(sp.getExternalImpositionTemplate().getFileSpec(0).getURL(), "file://foo.xml");
		assertNull(root.getResource(ElementName.LAYOUT, EnumUsage.Input, 0), "Layout is zapped");
	}

	/**
	 *
	 *
	 */
	@Test
	void testProductRunList()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		final SetHelper rls = h.getCreateSet(ElementName.RUNLIST, EnumUsage.Input);
		for (final String p : new String[] { "p1", "p2" })
		{
			final ProductHelper pr = h.appendProduct();
			pr.setExternalID(p);
			pr.setRoot();
			final JDFAttributeMap partMap = new JDFAttributeMap("Run", p);
			partMap.put(AttributeName.PRODUCT, p);

			final ResourceHelper rl = rls.appendPartition(partMap, false);
			rl.appendElement(ElementName.FILESPEC).setAttribute(AttributeName.URL, "file:" + p);
		}
		final JDFDoc d = xCon.convert(h);
		assertNotNull(d);
		final JDFNode jdfRoot = d.getJDFRoot();
		assertEquals(jdfRoot.getResourcePool().numChildElements(ElementName.RUNLIST, null), 1);
		final VElement products = jdfRoot.getvJDFNode("Product", null, true);
		assertEquals(2, products.size());
		for (final KElement p : products)
		{
			assertNotNull(((JDFNode) p).getResource(ElementName.RUNLIST, EnumUsage.Input, 0));
		}
		assertNull(jdfRoot.getResource(ElementName.RUNLIST, EnumUsage.Input, 0));
	}

	/**
	 *
	 *
	 */
	@Test
	void testProductRunListProduct()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		final SetHelper rls = h.getCreateSet(ElementName.RUNLIST, EnumUsage.Input);
		for (final String p : new String[] { "p1", "p2" })
		{
			final ProductHelper pr = h.appendProduct();
			pr.setExternalID(p);
			pr.setRoot();
			final JDFAttributeMap partMap = new JDFAttributeMap("Run", p);
			partMap.put(AttributeName.PRODUCTPART, p);

			final ResourceHelper rl = rls.appendPartition(partMap, false);
			rl.appendElement(ElementName.FILESPEC).setAttribute(AttributeName.URL, "file:" + p);
		}
		final JDFDoc d = xCon.convert(h);
		final JDFNode jdfRoot = d.getJDFRoot();
		final VElement products = jdfRoot.getvJDFNode("Product", null, true);
		assertEquals(2, products.size());
		assertEquals(d.getJDFRoot().getResourcePool().numChildElements(ElementName.RUNLIST, null), 1);
		for (final KElement p : products)
		{
			assertNotNull(((JDFNode) p).getResource(ElementName.RUNLIST, EnumUsage.Input, 0));
		}
		assertNull(jdfRoot.getResource(ElementName.RUNLIST, EnumUsage.Input, 0));
	}

	/**
	*
	*
	*/
	@Test
	void testProductComponent()
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
	*
	*
	*/
	@Test
	void testProductComponentExternalID()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		h.setTypes(JDFConstants.PRODUCT);
		h.appendProduct().setExternalID("x1");
		h.cleanUp();
		final JDFDoc d = xCon.convert(h);
		assertNotNull(d);
		final JDFNode n = d.getJDFRoot();
		final JDFResource comp = n.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
		assertNotNull(comp);
		assertEquals("x1", comp.getProductID());
	}

	/**
	*
	*
	*/
	@Test
	void testProductComponentAmount()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		h.setTypes(JDFConstants.PRODUCT);
		final ProductHelper p1 = h.appendProduct();
		p1.setAmount(10);
		final ProductHelper p11 = h.appendProduct();
		final ProductHelper p12 = h.appendProduct();
		p11.setAmount(20);
		p12.setAmount(20);
		p1.setChild(p11);
		p1.setChild(p12);

		h.cleanUp();
		final JDFDoc d = xCon.convert(h);
		assertNotNull(d);
		final JDFNode n = d.getJDFRoot();
		assertEquals(20, n.getLink(ElementName.COMPONENT, EnumUsage.Input, null).getAmount(), 0.0);
		assertEquals(10, n.getLink(ElementName.COMPONENT, EnumUsage.Output, null).getAmount(), 0.0);
	}

	/**
	 * check that signame gets automagically inserted below sheetname
	 */
	@Test
	void testSignatureName()
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
	void testAmountPool()
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
	void testAmountWaste()
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
	void testApprovalDetails()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		final SetHelper sh = xjdf.getCreateSet(XJDFConstants.Resource, ElementName.APPROVALDETAILS, EnumUsage.Input);
		final ResourceHelper ph = sh.getCreatePartition(null, true);
		ph.setComment("Approval ok");
		final JDFApprovalDetails ad = (JDFApprovalDetails) ph.getCreateResource();
		ad.setApprovalState(EnumApprovalState.Approved);

		final JDFDoc d = xCon.convert(xjdf);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFApprovalSuccess s = (JDFApprovalSuccess) root.getResource(ElementName.APPROVALSUCCESS, EnumUsage.Input, 0);
		final JDFApprovalDetails det = s.getApprovalDetails(0);
		assertNotNull(det);
		assertEquals("Approval ok", det.getComment(0).getText());
	}

	/**
	*
	*
	*/
	@Test
	void testApprovalDetailsSheet()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = new XJDFHelper("j1", null, null);
		final SetHelper sh = xjdf.getCreateSet(XJDFConstants.Resource, ElementName.APPROVALDETAILS, EnumUsage.Input);
		for (int i = 1; i < 3; i++)
		{
			final ResourceHelper ph = sh.getCreatePartition(new JDFAttributeMap(AttributeName.SHEETNAME, "S" + i), true);
			ph.setComment("Approval ok " + i);
			final JDFApprovalDetails ad = (JDFApprovalDetails) ph.getCreateResource();
			ad.setApprovalState(EnumApprovalState.Approved);
		}

		final JDFDoc d = xCon.convert(xjdf);
		assertNotNull(d);
		final JDFNode root = d.getJDFRoot();
		final JDFApprovalSuccess s0 = (JDFApprovalSuccess) root.getResource(ElementName.APPROVALSUCCESS, EnumUsage.Input, 0);
		final List<JDFResource> ls = s0.getLeafArray(false);
		for (int i = 1; i < 3; i++)
		{
			final JDFApprovalDetails det = ((JDFApprovalSuccess) ls.get(i - 1)).getApprovalDetails(0);
			assertNotNull(det);
			assertEquals("Approval ok " + i, det.getComment(0).getText());
		}
	}

	/**
	*
	*
	*/
	@Test
	void testOverage()
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
	void testIntentExternalID()
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
	void testResourceExternalID()
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
	void testDeliveryIntent()
	{
		final KElement xjdf = new JDFToXJDFConverterTest()._testDeliveryIntent();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFDeliveryParams dp = (JDFDeliveryParams) d.getJDFRoot().getResource(ElementName.DELIVERYPARAMS, EnumUsage.Input, 0);
		assertNull(dp);
		final JDFDeliveryIntent di = (JDFDeliveryIntent) d.getJDFRoot().getResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0);
		assertNotNull(di);
		assertNotNull(di.getDropIntent(0).getDropItemIntent(1).getComponent(), "The ProductRef was not translated");
	}

	/**
	*
	*
	*/
	@Test
	void testDeliveryIntentFromProduct()
	{
		final XJDFHelper xjdf = new XJDFHelper("j1", null);
		xjdf.appendProduct().setAmount(42);
		final JDFContact c = (JDFContact) xjdf.appendSet(ElementName.CONTACT, EnumUsage.Input).appendPartition(XJDFConstants.ContactType, "Delivery", true).getResource();
		c.appendPerson().setFamilyName("f");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFDeliveryParams dp = (JDFDeliveryParams) d.getJDFRoot().getResource(ElementName.DELIVERYPARAMS, EnumUsage.Input, 0);
		assertNull(dp);
		final JDFDeliveryIntent di = (JDFDeliveryIntent) d.getJDFRoot().getResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0);
		assertNotNull(di);
		assertEquals("f", di.getDropIntent(0).getContact(0).getPerson().getFamilyName(), "The Contact was not translated");
	}

	/**
	*
	*
	*/
	@Test
	void testDescNameProduct()
	{
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		h.setAttribute(AttributeName.DESCRIPTIVENAME, "x1");
		h.getCreateRootProduct(0).setAttribute(AttributeName.DESCRIPTIVENAME, "p2");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		assertEquals("x1", d.getJDFRoot().getDescriptiveName());
	}

	/**
	*
	*
	*/
	@Test
	void testDescNameProduct2()
	{
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		h.getCreateRootProduct(0).setAttribute(AttributeName.DESCRIPTIVENAME, "p2");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		assertEquals("p2", d.getJDFRoot().getDescriptiveName());
	}

	/**
	*
	*
	*/
	@Test
	void testDescName2RootProducts()
	{
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		h.setDescriptiveName("h1");
		h.getCreateRootProduct(0).setAttribute(AttributeName.DESCRIPTIVENAME, "p1");
		h.getCreateRootProduct(1).setAttribute(AttributeName.DESCRIPTIVENAME, "p2");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		assertEquals("p1", d.getJDFRoot().getJDF(0).getDescriptiveName());
		assertEquals("p2", d.getJDFRoot().getJDF(1).getDescriptiveName());
	}

	/**
	*
	*
	*/
	@Test
	void testDropID()
	{
		final KElement xjdf = new JDFToXJDFConverterTest()._testDeliveryIntent();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFDeliveryParams dp = (JDFDeliveryParams) d.getJDFRoot().getResource(ElementName.DELIVERYPARAMS, EnumUsage.Input, 0);
		assertNull(dp);
		final JDFDeliveryIntent di = (JDFDeliveryIntent) d.getJDFRoot().getResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0);
		assertNotNull(di);
		assertEquals("DROP_0", di.getDropIntent(0).getDropID());
		assertEquals(2, di.getDropIntent(0).getAllDropItemIntent().size());
	}

	/**
	*
	*
	*/
	@Test
	void testDropIDContact()
	{
		final KElement xjdf = new JDFToXJDFConverterTest()._testDeliveryIntent();
		final XJDFHelper h = new XJDFHelper(xjdf);
		final SetHelper csh = h.getCreateSet(ElementName.CONTACT, EnumUsage.Input);
		for (int i = 0; i < 2; i++)
		{
			final JDFAttributeMap partmap = new JDFAttributeMap(XJDFConstants.ContactType, EnumContactType.Delivery.getName());
			partmap.put(AttributeName.DROPID, "DROP_" + i);
			final ResourceHelper ch = csh.appendPartition(partmap, true);
			final JDFContact co = (JDFContact) ch.getResource();
			co.appendAddress().setStreet("S" + i);
		}

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFDeliveryParams dp = (JDFDeliveryParams) d.getJDFRoot().getResource(ElementName.DELIVERYPARAMS, EnumUsage.Input, 0);
		assertNull(dp);
		final JDFDeliveryIntent di = (JDFDeliveryIntent) d.getJDFRoot().getResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0);
		assertNotNull(di);
		assertEquals("DROP_0", di.getDropIntent(0).getDropID());
		assertNotNull(di.getDropIntent(0).getContact(0));
	}

	/**
	*
	*
	*/
	@Test
	void testDropIDContactPartition()
	{
		final KElement xjdf = new JDFToXJDFConverterTest()._testDeliveryIntent();
		final XJDFHelper h = new XJDFHelper(xjdf);
		final SetHelper csh = h.getCreateSet(ElementName.CONTACT, EnumUsage.Input);
		for (int i = 0; i < 2; i++)
		{
			final JDFAttributeMap partmap = new JDFAttributeMap(XJDFConstants.ContactType, EnumContactType.Delivery.getName());
			partmap.put(AttributeName.DROPID, "DROP_" + i);
			final ResourceHelper ch = csh.appendPartition(partmap, true);
			final JDFContact co = (JDFContact) ch.getResource();
			co.appendAddress().setStreet("S" + i);
		}

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFDeliveryParams dp = (JDFDeliveryParams) d.getJDFRoot().getResource(ElementName.DELIVERYPARAMS, EnumUsage.Input, 0);
		assertNull(dp);
		final JDFDeliveryIntent di = (JDFDeliveryIntent) d.getJDFRoot().getResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0);
		assertNotNull(di);
		final JDFContact contact = di.getDropIntent(0).getContact(0);
		assertFalse(contact.getPartIDKeys().contains(AttributeName.DROPID));
		assertEquals("S0", contact.getAddress().getStreet());
	}

	/**
	*
	*
	*/
	@Test
	void testPlacedObject()
	{
		final KElement xjdf = new XJDFHelper("j1", "p1", null).getRoot();
		xjdf.setXPathAttribute("ResourceSet[@Name=\"Layout\"]/Resource/Part/@SheetName", "s1");
		xjdf.setXPathAttribute("ResourceSet[@Name=\"Layout\"]/Resource/Layout/PlacedObject/@Ord", "1");
		xjdf.setXPathAttribute("ResourceSet[@Name=\"Layout\"]/@Usage", "Input");
		xjdf.setXPathAttribute("ResourceSet[@Name=\"Layout\"]/Resource/Layout/PlacedObject/@Type", "ContentObject");

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFLayout partition = (JDFLayout) d.getJDFRoot().getResource(ElementName.LAYOUT, EnumUsage.Input, 0).getPartition(new JDFAttributeMap(EnumPartIDKey.SheetName, "s1"),
				null);
		final JDFContentObject contentObject = partition.getContentObject(0);
		assertEquals(contentObject.getOrd(), 1);
		assertNull(contentObject.getNonEmpty(AttributeName.TYPE));
	}

	/**
	*
	*
	*/
	@Test
	void testHolePattern()
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
	void testPageList()
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
	void testRunListListName()
	{
		final XJDFHelper xjdf = new XJDFHelper("j1", null);
		xjdf.setTypes("ImageSetting");
		final SetHelper rs = xjdf.appendResourceSet(ElementName.RUNLIST, EnumUsage.Input);
		final ResourceHelper r1 = rs.appendPartition(AttributeName.RUN, "r1", true);
		final JDFRunList rl1 = (JDFRunList) r1.getResource();
		rl1.setFileSpecURL("file:r1.pdf");
		r1.setGeneralID("PageListName", "PL1");
		final ResourceHelper r2 = rs.appendPartition(AttributeName.RUN, "r2", true);
		final JDFRunList rl2 = (JDFRunList) r1.getResource();
		rl2.setFileSpecURL("file:r2.pdf");
		r2.setGeneralID("PageListName", "PL2");

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFNode root = d.getJDFRoot();
		d.write2File(sm_dirTestDataTemp + "PageList.name.jdf", 2, false);
		assertEquals("PL1", root.getResource(ElementName.RUNLIST).getPartition(new JDFAttributeMap("Run", "r1"), null).getGeneralID("PageListName"));
		assertTrue(root.isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 *
	 * @return
	 */
	@Test
	void testPreview()
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
	void testNPage()
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
	void testNPageFileSpec()
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
	void testPageListEmpty()
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
	void testPartIDKeys()
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
	void testAmountPoolPart()
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
	void testAmountPoolPart2()
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
	void testAmountPoolNoPart()
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
	void testPartAmountMap()
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
	void testAmountPoolAudit()
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
	void testAmountPoolAuditNoPart()
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
	 */
	@Test
	void testAssemblingIntentBindInt()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Stripping);
		final JDFAssembly as = (JDFAssembly) n.addResource(ElementName.ASSEMBLY, EnumUsage.Input);
		as.setOrder(EnumOrder.Collecting);
		as.setAssemblyIDs(VString.getVString("AS1 AS2 AS3", null));

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		final SetHelper sh = new XJDFHelper(xjdf).getSet(ElementName.ASSEMBLY, EnumUsage.Input);
		final JDFAssembly asx = (JDFAssembly) sh.getPartition(0).getResource();
		assertEquals("AS1 AS2 AS3", asx.getNonEmpty(XJDFConstants.BinderySignatureIDs));

		sh.cleanUp();
		assertEquals("AS1 AS2 AS3", asx.getNonEmpty(XJDFConstants.BinderySignatureIDs));
	}

	/**
	*
	*/
	@Test
	void testAssembly()
	{
		final XJDFHelper xh = new XJDFHelper("j1", "p1", null);
		final JDFAssembly assembly = (JDFAssembly) xh.getCreateSet(ElementName.ASSEMBLY, EnumUsage.Input).getCreatePartition(0, true).getResource();
		assembly.appendAssemblySection().setAttribute(XJDFConstants.BinderySignatureID, "b1");
		assembly.appendAssemblySection().setAttribute(XJDFConstants.BinderySignatureID, "b2");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xh);
		final JDFAssembly ja = (JDFAssembly) d.getJDFRoot().getResource(ElementName.ASSEMBLY);
		assertNotNull(ja);
		assertEquals(EnumOrder.List, ja.getOrder());
	}

	/**
	*
	*
	*/
	@Test
	void testBinderySignature()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper xjdf = XJDFHelper.parseFile(sm_dirTestData + "xjdf/tnr.strip.red.xjdf");
		final JDFDoc jdf = xCon.convert(xjdf);
		jdf.write2File(sm_dirTestDataTemp + "tnr.strip.red.jdf", 2, false);
		final JDFNode root = jdf.getJDFRoot();
		final JDFResource bs = root.getResource(ElementName.BINDERYSIGNATURE, EnumUsage.Input, 0);
		assertNull(bs);
		final JDFStrippingParams sp = (JDFStrippingParams) root.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0).getLeaf(0);
		assertNotNull(sp.getBinderySignature());
	}

	/**
	 *
	 *
	 */
	@Test
	void testCustomerInfoContacts()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		h.getCreateSet(ElementName.CUSTOMERINFO, EnumUsage.Input).getCreatePartition(0, true).getResource().setAttribute(AttributeName.CUSTOMERORDERID, "cc");
		final SetHelper sh = h.getCreateSet(ElementName.CONTACT, EnumUsage.Input);
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
	void testDieLayoutBindery()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.setTypes("Stripping");
		final KElement e = h.getRoot();
		final ResourceHelper dloRes = h.getCreateSet(ElementName.DIELAYOUT, null).getCreatePartition(0, true);
		final JDFDieLayout dlo = (JDFDieLayout) dloRes.getResource();
		dlo.appendStation().setStationName("station");

		final JDFBinderySignature bs = (JDFBinderySignature) h.getCreateSet(ElementName.BINDERYSIGNATURE, EnumUsage.Input).getCreatePartition(0, true).getResource();
		dloRes.ensureReference(bs, null);

		final ResourceHelper loRes = h.getCreateSet(ElementName.LAYOUT, EnumUsage.Output).getCreatePartition(AttributeName.SHEETNAME, "S1", true);
		final JDFLayout lo = (JDFLayout) loRes.getResource();
		lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.Simplex, null);

		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);

	}

	/**
	 * @throws Throwable
	 *
	 */
	@Test
	void testCustomerInfoNumber() throws Throwable
	{
		final XJDFHelper h = new XJDFHelper("j1", "p1");
		h.getCreateSet(ElementName.CUSTOMERINFO, EnumUsage.Input).getCreatePartition(null, true).getResource().setAttribute(AttributeName.CUSTOMERID, "12345.000");

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		final JDFNode n = d.getJDFRoot();
		assertEquals("12345.000", n.getCustomerInfo().getCustomerID());
	}

	/**
	 *
	 *
	 */
	@Test
	void testCustomerInfoMultiContacts()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		h.getCreateSet(XJDFConstants.Resource, ElementName.CUSTOMERINFO, EnumUsage.Input).getCreatePartition(0, true).getResource().setAttribute(AttributeName.CUSTOMERORDERID,
				"cc");
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
	void testMissingSetID()
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
	void testMultiSet()
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
	void testSetDescName()
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
	void testSetSparse()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final XJDFHelper h = new XJDFHelper("j1", "root", null);
		final KElement e = h.getRoot();
		final SetHelper set = h.appendResourceSet(ElementName.DIELAYOUT, EnumUsage.Input);
		final JDFAttributeMap m1 = new JDFAttributeMap(AttributeName.SHEETNAME, "S1");
		set.appendPartition(m1, true).setAmount(5, null, true);
		final JDFAttributeMap m2 = new JDFAttributeMap(AttributeName.SHEETNAME, "S2");
		m2.put(AttributeName.SIDE, JDFConstants.SIDE_FRONT);
		final VJDFAttributeMap v = new VJDFAttributeMap(m2.clone());
		m2.put(AttributeName.SIDE, JDFConstants.SIDE_BACK);
		v.add(m2);
		set.appendResource(v, true);
		final JDFDoc d = xCon.convert(e);
		final JDFResource dlo = d.getJDFRoot().getResource(ElementName.DIELAYOUT, EnumUsage.Input, 0);
		assertEquals(3, dlo.getLeaves(false).size());
	}

	/**
	 *
	 *
	 */
	@Test
	void testMultiResourcePart()
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
	void testResourceLinkPart()
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
	void testResourceGeneralID()
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
	void testResourceLinkRun()
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
	void testResourceLinkRunIndex()
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
	void testResourceLinkRunIndex2()
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
	void testResourceLinkPart2()
	{
		KElement.setLongID(false);
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
	void testWorkstepID()
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
	void testGetVersion()
	{
		final XJDFHelper h = new XJDFHelper("j1", "p1", null);
		h.setVersion(EnumVersion.Version_2_1);
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		assertEquals(EnumVersion.Version_2_1.getJDFVersion(), xCon.getVersion());

	}

	/**
	 *
	 */
	@Test
	void testCounterID()
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
	 */
	@Test
	void testCanConvert()
	{
		assertTrue(XJDFToJDFConverter.canConvert(JDFElement.createRoot(XJDFConstants.XJDF)));
		assertTrue(XJDFToJDFConverter.canConvert(JDFElement.createRoot(XJDFConstants.XJMF)));
		assertFalse(XJDFToJDFConverter.canConvert(JDFElement.createRoot(ElementName.JDF)));

	}

	/**
	 *
	 *
	 */
	@Test
	void testXJMFKnownMessages()
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
		assertTrue(jmfRoot.isValid(EnumValidationLevel.Incomplete));
	}

	/**
	*
	*
	*/
	@Test
	void testBackwardProduct()
	{
		final XJDFHelper h = new XJDFHelper("j", "root", null);
		final ProductHelper cover = h.appendProduct();
		final ProductHelper body = h.appendProduct();
		final ProductHelper book = h.appendProduct();
		book.setRoot();
		book.setChild(cover);
		book.setChild(body);
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		assertEquals(d.getJDFRoot().getJobPartID(true), "root");
	}

	/**
	*
	*
	*/
	@Test
	void testMediaIntent()
	{
		final XJDFHelper h = new XJDFHelper("j", "root", null);
		final ProductHelper book = h.appendProduct();
		book.setRoot();
		final KElement mi = book.getCreateIntent(ElementName.MEDIAINTENT).getResource();
		mi.setAttribute(XJDFConstants.Coating, "Gloss");
		mi.setAttribute(XJDFConstants.BackCoating, "gloss", null);
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		final JDFMediaIntent mij = (JDFMediaIntent) d.getJDFRoot().getResource(ElementName.MEDIAINTENT, EnumUsage.Input, 0);
		assertEquals("Glossy", JDFIntentResource.guessActual(mij, ElementName.FRONTCOATINGS));
		assertEquals("Glossy", JDFIntentResource.guessActual(mij, ElementName.BACKCOATINGS));

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(d.getJDFRoot());

		final XJDFHelper rt = XJDFHelper.getHelper(xjdf);
		assertEquals("Gloss", rt.getProduct(0).getIntentAttribute(ElementName.MEDIAINTENT, XJDFConstants.Coating));
		assertEquals("Gloss", rt.getProduct(0).getIntentAttribute(ElementName.MEDIAINTENT, XJDFConstants.BackCoating));

	}

	/**
	*
	*
	*/
	@Test
	void testFoldinfIntentDetails()
	{
		final XJDFHelper h = new XJDFHelper("j", "root", null);
		final ProductHelper book = h.appendProduct();
		book.setRoot();
		book.getCreateIntent(ElementName.FOLDINGINTENT).getResource().setAttribute(AttributeName.FOLDINGDETAILS, "fd");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		assertEquals("fd", d.getJDFRoot().getResource(ElementName.FOLDINGINTENT, EnumUsage.Input, 0).getAttribute(AttributeName.FOLDINGDETAILS));
		assertNull(d.getJDFRoot().getResource(ElementName.FOLDINGINTENT, EnumUsage.Input, 0).getElement(AttributeName.FOLDINGDETAILS));
	}

	/**
	*
	*
	*/
	@Test
	void testCoverBodyProduct()
	{
		final XJDFHelper h = new XJDFHelper("j", "root", null);
		final ProductHelper cover = h.appendProduct();
		cover.setDescriptiveName("Cover");
		cover.setID("Cover");
		cover.setRoot(false);
		final ProductHelper body = h.appendProduct();
		body.setID("Body");
		body.setDescriptiveName("Body");
		body.setRoot(false);
		final ProductHelper book = h.appendProduct();
		book.setRoot();
		book.setDescriptiveName("Book");
		book.setID("Book");
		final ProductHelper card = h.appendProduct();
		card.setRoot();
		card.setDescriptiveName("Card");
		card.setID("Card");
		book.appendIntent(ElementName.BINDINGINTENT).setAttribute(ElementName.BINDINGTYPE, EnumSpanBindingType.CornerStitch.getName());
		book.setChild(cover);
		book.setChild(body);
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		final JDFNode parent = d.getJDFRoot();
		assertEquals(2, parent.getvJDFNode(null, null, true).size());
		assertEquals(5, parent.getvJDFNode(null, null, false).size());
	}

	/**
	*
	*
	*/
	@Test
	void testMultiCoverBodyProduct()
	{
		final XJDFHelper h = new XJDFHelper("j", "root", null);
		h.getCreateSet(ElementName.NODEINFO, EnumUsage.Input).getCreateResource(0, true).getResource().setAttribute("Start", new JDFDate().getDateTimeISO());
		for (int i = 0; i < 2; i++)
		{
			final ProductHelper cover = h.appendProduct();
			cover.setDescriptiveName("Cover" + i);
			cover.setID("Cover" + i);
			cover.setRoot(false);
			final ProductHelper body = h.appendProduct();
			body.setID("Body" + i);
			body.setDescriptiveName("Body" + i);
			body.setRoot(false);
			final ProductHelper book = h.appendProduct();
			book.setRoot();
			book.setDescriptiveName("Book" + i);
			book.setID("Book" + i);
			final ProductHelper card = h.appendProduct();
			card.setRoot();
			card.setDescriptiveName("Card" + i);
			card.setID("Card" + i);
			book.appendIntent(ElementName.BINDINGINTENT).setAttribute(ElementName.BINDINGTYPE, EnumSpanBindingType.CornerStitch.getName());
			book.setChild(cover);
			book.setChild(body);
		}

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		final JDFNode parent = d.getJDFRoot();
		assertEquals(4, parent.getvJDFNode(null, null, true).size());
		assertEquals(9, parent.getvJDFNode(null, null, false).size());
		assertNotNull(parent.getNodeInfo().getStart());
	}

	/**
	*
	*
	*/
	@Test
	void testCoverBodyProductDelivery()
	{
		final XJDFHelper h = new XJDFHelper("j", "root", null);
		final ProductHelper cover = h.appendProduct();
		cover.setDescriptiveName("Cover");
		cover.setID("Cover");
		cover.setRoot(false);
		cover.setAmount(1);
		final ProductHelper body = h.appendProduct();
		body.setID("Body");
		body.setDescriptiveName("Body");
		body.setRoot(false);
		body.setAmount(2);
		final ProductHelper book = h.appendProduct();
		book.setRoot();
		book.setDescriptiveName("Book");
		book.setID("Book");
		book.setAmount(200);
		final ProductHelper card = h.appendProduct();
		card.setRoot();
		card.setDescriptiveName("Card");
		card.setID("Card");
		card.setAmount(500);
		book.appendIntent(ElementName.BINDINGINTENT).setAttribute(ElementName.BINDINGTYPE, EnumSpanBindingType.CornerStitch.getName());
		book.setChild(cover);
		book.setChild(body);
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		final JDFNode parent = d.getJDFRoot();
		final JDFDeliveryIntent di = (JDFDeliveryIntent) parent.getResource(ElementName.DELIVERYINTENT, EnumUsage.Input, 0);
		final JDFDropIntent dri = di.getDropIntent(0);
	}

	/**
	 *
	 */
	@Test
	void testNasty()
	{
		final XJDFHelper h = XJDFHelper.parseFile(sm_dirTestData + "nasty.xjdf");
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(h);
		d.write2File(sm_dirTestDataTemp + "nasty.jdf", 2, false);
		assertNotNull(d);
	}

	/**
	*
	*
	*/
	@Test
	void testNiciProduct()
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
	void testMultiBackwardProduct()
	{
		final XJDFHelper h = new XJDFHelper("j", "root", null);
		for (int i = 0; i < 2; i++)
		{
			final ProductHelper cover = h.appendProduct();
			final ProductHelper body = h.appendProduct();
			final ProductHelper book = h.appendProduct();
			book.setRoot();
			book.setChild(cover);
			book.setChild(body);
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
	void testFromXJDFColorIntentSurfaceColor()
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

	/**
	 *
	 */
	@Test
	void testStrippingMedia()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "3F-16", null);
		xjdfHelper.setTypes("Stripping");
		final SetHelper shBS = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final ResourceHelper rhBS = shBS.appendPartition(null, true);
		final JDFBinderySignature bs = (JDFBinderySignature) rhBS.getResource();
		bs.setFoldCatalog("F16-6");
		bs.setBinderySignatureType(EnumBinderySignatureType.Fold);

		final SetHelper shPap = xjdfHelper.getCreateSet(ElementName.MEDIA, null);
		final ResourceHelper rhPap = shPap.getCreatePartition(AttributeName.SHEETNAME, "sheet1", true);
		rhPap.setID("idPaper");
		final JDFMedia pap = (JDFMedia) rhPap.getResource();
		pap.setMediaType(EnumMediaType.Paper);

		final SetHelper shPlate = xjdfHelper.appendResourceSet(ElementName.MEDIA, null);
		final ResourceHelper rhPlate = shPlate.getCreatePartition(AttributeName.SHEETNAME, "sheet1", true);
		rhPlate.setID("idPlate");
		final JDFMedia plate = (JDFMedia) rhPlate.getResource();
		plate.setMediaType(EnumMediaType.Plate);

		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		rhBS.appendPartMap(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "bs1"));
		final ResourceHelper rh = shLO.appendPartition(AttributeName.SHEETNAME, "sheet1", true);
		final JDFLayout lo = (JDFLayout) rh.getResource();
		lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.WorkAndBack.getName());
		final KElement pos = lo.appendElement(ElementName.POSITION);
		pos.setAttribute(XJDFConstants.BinderySignatureID, "bs1");

		lo.setAttribute(XJDFConstants.PaperRef, "idPaper");
		lo.setAttribute(XJDFConstants.PlateRef, "idPlate");

		final SetHelper shAss = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.ASSEMBLY, EnumUsage.Input);
		final ResourceHelper rhAss = shAss.appendPartition(null, true);
		rhAss.getResource().setAttribute(XJDFConstants.BinderySignatureIDs, "bs1");
		((JDFAssembly) rhAss.getResource()).setOrder(EnumOrder.Collecting);

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdfHelper);
		final JDFNode n = d.getJDFRoot();
		final JDFLayout loj = (JDFLayout) n.getResource(ElementName.LAYOUT, EnumUsage.Input, 0).getLeaf(0);
		assertEquals(EnumResStatus.Available, loj.getResStatus(false));
		assertNotNull(loj.getMedia(1));
		assertEquals("Sig_sheet1", loj.getXPathAttribute("MediaRef/Part/@SignatureName", null));
		final JDFStrippingParams sp = (JDFStrippingParams) n.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0).getLeaf(0);
		assertNotNull(sp.getMedia(1));
		assertEquals(EnumResStatus.Available, sp.getResStatus(false));
	}

	/**
	 *
	 */
	@Test
	void testStrippingAmount()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "3F-16", null);
		xjdfHelper.setTypes("Stripping");
		final SetHelper shBS = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final ResourceHelper rhBS = shBS.appendPartition(null, true);
		final JDFBinderySignature bs = (JDFBinderySignature) rhBS.getResource();
		bs.setFoldCatalog("F16-6");
		bs.setBinderySignatureType(EnumBinderySignatureType.Fold);

		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		rhBS.appendPartMap(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "bs1"));
		final ResourceHelper rh = shLO.appendPartition(AttributeName.SHEETNAME, "sheet1", true);
		final JDFLayout lo = (JDFLayout) rh.getResource();
		lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.WorkAndBack.getName());
		final KElement pos = lo.appendElement(ElementName.POSITION);
		pos.setAttribute(XJDFConstants.BinderySignatureID, "bs1");

		rh.setAmount(100, null, true);
		rh.setAmount(5, null, false);

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdfHelper);
		final JDFNode n = d.getJDFRoot();
		final JDFLayout loj = (JDFLayout) n.getResource(ElementName.LAYOUT, EnumUsage.Input, 0);
		assertEquals(EnumResStatus.Available, loj.getResStatus(false));
		final JDFStrippingParams sp = (JDFStrippingParams) n.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
		assertEquals(EnumResStatus.Available, sp.getResStatus(false));
		final JDFResourceLink rlLO = n.getLink(loj, EnumUsage.Input);
		final JDFResourceLink rlSP = n.getLink(sp, EnumUsage.Input);
		assertEquals(105, rlLO.getAmount(null), 0.1);
		assertNull(rlSP.getAmountPool());
	}

	/**
	 *
	 */
	@Test
	void testStrippingMultiBSID()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "3F-16", null);
		xjdfHelper.setTypes("Stripping");

		final SetHelper shBS = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final ResourceHelper rhBS = shBS.appendPartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS1.1"), true);
		rhBS.appendPartMap(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS1.2"));
		rhBS.appendPartMap(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS1.3"));
		final JDFBinderySignature bs = (JDFBinderySignature) rhBS.getResource();
		bs.setFoldCatalog("F16-6");
		bs.setBinderySignatureType(EnumBinderySignatureType.Fold);

		final SetHelper shBS2 = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final ResourceHelper rhBS2 = shBS2.appendPartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS2.1"), true);
		rhBS2.appendPartMap(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS2.2"));
		rhBS2.appendPartMap(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS2.3"));
		final JDFBinderySignature bs2 = (JDFBinderySignature) rhBS2.getResource();
		bs2.setFoldCatalog("F16-5");
		bs2.setBinderySignatureType(EnumBinderySignatureType.Fold);

		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh = shLO.appendPartition(AttributeName.SHEETNAME, "sheet1", true);
		final JDFLayout lo = (JDFLayout) rh.getResource();
		lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.WorkAndBack.getName());
		for (int i = 1; i < 4; i++)
		{
			final KElement pos = lo.appendElement(ElementName.POSITION);
			pos.setAttribute(XJDFConstants.BinderySignatureID, "BS1." + i);
			final KElement pos2 = lo.appendElement(ElementName.POSITION);
			pos2.setAttribute(XJDFConstants.BinderySignatureID, "BS2." + i);
		}

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdfHelper);
		final JDFNode n = d.getJDFRoot();
		final JDFLayout loj = (JDFLayout) n.getResource(ElementName.LAYOUT, EnumUsage.Input, 0);
		assertEquals(EnumResStatus.Available, loj.getResStatus(false));
		final JDFStrippingParams sp = (JDFStrippingParams) n.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
		assertEquals(EnumResStatus.Available, sp.getResStatus(false));

		final JDFBinderySignature bsj = (JDFBinderySignature) ((JDFStrippingParams) sp.getLeaf(0)).getBinderySignature().getResourceRoot();
		assertEquals(2, bsj.getLeafArray(false).size());
		for (final KElement spr : sp.getLeaves(false))
		{
			assertNotNull(((JDFStrippingParams) spr).getBinderySignature());
		}
	}

	/**
	*
	*/
	@Test
	void testStrippingSignatureCell()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "3F-16", null);
		xjdfHelper.setTypes("Stripping");

		final SetHelper shBS = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final ResourceHelper rhBS = shBS.appendPartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS1.1"), true);
		final JDFBinderySignature bs = (JDFBinderySignature) rhBS.getResource();
		bs.setFoldCatalog("F16-6");
		bs.setBinderySignatureType(EnumBinderySignatureType.Fold);
		bs.appendSignatureCell().setAttribute(AttributeName.TRIMSIZE, "20 30");
		bs.appendSignatureCell().setAttribute(AttributeName.TRIMSIZE, "120 130");

		final SetHelper shBS2 = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final ResourceHelper rhBS2 = shBS2.appendPartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS2.1"), true);
		final JDFBinderySignature bs2 = (JDFBinderySignature) rhBS2.getResource();
		bs2.setFoldCatalog("F16-5");
		bs2.setBinderySignatureType(EnumBinderySignatureType.Fold);
		bs2.appendSignatureCell().setAttribute(AttributeName.TRIMSIZE, "40 50");
		bs2.appendSignatureCell().setAttribute(AttributeName.TRIMSIZE, "140 150");

		final SetHelper shLO = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh = shLO.appendPartition(AttributeName.SHEETNAME, "sheet1", true);
		final JDFLayout lo = (JDFLayout) rh.getResource();
		lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.WorkAndBack.getName());
		for (int i = 1; i < 2; i++)
		{
			final KElement pos = lo.appendElement(ElementName.POSITION);
			pos.setAttribute(XJDFConstants.BinderySignatureID, "BS1." + i);
			final KElement pos2 = lo.appendElement(ElementName.POSITION);
			pos2.setAttribute(XJDFConstants.BinderySignatureID, "BS2." + i);
		}

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdfHelper);
		final JDFNode n = d.getJDFRoot();
		final JDFLayout loj = (JDFLayout) n.getResource(ElementName.LAYOUT, EnumUsage.Input, 0);
		assertEquals(EnumResStatus.Available, loj.getResStatus(false));
		final JDFStrippingParams sp = (JDFStrippingParams) n.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
		assertEquals(EnumResStatus.Available, sp.getResStatus(false));

		final JDFBinderySignature bsj = (JDFBinderySignature) ((JDFStrippingParams) sp.getLeaf(0)).getBinderySignature().getResourceRoot();
		assertEquals(2, bsj.getLeafArray(false).size());
		for (final KElement bsl : bsj.getLeaves(false))
		{
			assertNull(bsl.getElement(ElementName.SIGNATURECELL));
		}
		for (final KElement spl : sp.getLeaves(false))
		{
			assertNotNull(spl.getElement(ElementName.STRIPCELLPARAMS));
			assertNotNull(spl.getElement(ElementName.STRIPCELLPARAMS, null, 1).getAttribute(AttributeName.TRIMSIZE));
		}

	}

	/**
	 *
	 */
	@Test
	void testStrippingDescNameOnly()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "3F-16", null);
		xjdfHelper.setTypes("Stripping");

		final SetHelper shLO = xjdfHelper.getCreateSet(ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh = shLO.appendPartition(AttributeName.SHEETNAME, "sheet1", true);
		rh.setDescriptiveName("Description of the sheet");

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdfHelper);
		final JDFNode n = d.getJDFRoot();
		final JDFLayout loj = (JDFLayout) n.getResource(ElementName.LAYOUT, EnumUsage.Input, 0);
		assertEquals(EnumResStatus.Available, loj.getResStatus(false));
		final JDFStrippingParams sp = (JDFStrippingParams) n.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
		assertEquals(EnumResStatus.Available, sp.getResStatus(false));
		assertEquals("Description of the sheet", loj.getLeaf(0).getDescriptiveName());
		assertEquals("Description of the sheet", sp.getLeaf(0).getDescriptiveName());
	}

	/**
	 *
	 */
	@Test
	void testStrippingDescNameOnly2()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "3F-16", null);
		xjdfHelper.setTypes("Stripping");

		final SetHelper shLO = xjdfHelper.getCreateSet(ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh = shLO.appendPartition(AttributeName.SHEETNAME, "sheet1", false);
		rh.setDescriptiveName("Description of the sheet");

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdfHelper);
		final JDFNode n = d.getJDFRoot();
		final JDFLayout loj = (JDFLayout) n.getResource(ElementName.LAYOUT, EnumUsage.Input, 0);
		assertEquals(EnumResStatus.Available, loj.getResStatus(false));
		final JDFStrippingParams sp = (JDFStrippingParams) n.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
		assertEquals(EnumResStatus.Available, sp.getResStatus(false));
		assertEquals("Description of the sheet", loj.getLeaf(0).getDescriptiveName());
		assertEquals("Description of the sheet", sp.getLeaf(0).getDescriptiveName());
	}

	/**
	 *
	 */
	@Test
	void testStrippingDescName()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper(ElementName.LAYOUT, "3F-16", null);
		xjdfHelper.setTypes("Stripping");

		final SetHelper shLO = xjdfHelper.getCreateSet(ElementName.LAYOUT, EnumUsage.Input);
		final ResourceHelper rh = shLO.appendPartition(AttributeName.SHEETNAME, "sheet1", true);
		rh.setDescriptiveName("Description of the sheet");
		final JDFLayout lo = (JDFLayout) rh.getResource();
		lo.setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.WorkAndBack.getName());
		final KElement pos = lo.appendElement(ElementName.POSITION);
		pos.setAttribute(XJDFConstants.BinderySignatureID, "bs1");

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdfHelper);
		final JDFNode n = d.getJDFRoot();
		final JDFLayout loj = (JDFLayout) n.getResource(ElementName.LAYOUT, EnumUsage.Input, 0);
		assertEquals(EnumResStatus.Available, loj.getResStatus(false));
		final JDFStrippingParams sp = (JDFStrippingParams) n.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
		assertEquals(EnumResStatus.Available, sp.getResStatus(false));
		assertEquals("Description of the sheet", loj.getLeaf(0).getAttribute_KElement(AttributeName.DESCRIPTIVENAME));
		assertEquals("Description of the sheet", sp.getLeaf(0).getParentNode_KElement().getAttribute_KElement(AttributeName.DESCRIPTIVENAME));
	}
}
