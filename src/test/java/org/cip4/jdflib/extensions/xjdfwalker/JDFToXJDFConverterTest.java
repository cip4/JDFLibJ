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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoComChannel.EnumChannelType;
import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoExposedMedia.EnumPlateType;
import org.cip4.jdflib.auto.JDFAutoGlue.EnumWorkingDirection;
import org.cip4.jdflib.auto.JDFAutoGlueApplication.EnumGluingTechnique;
import org.cip4.jdflib.auto.JDFAutoInsertingParams.EnumMethod;
import org.cip4.jdflib.auto.JDFAutoInterpretingParams.EnumPolarity;
import org.cip4.jdflib.auto.JDFAutoLayoutIntent.EnumSides;
import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumCostType;
import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumDeviceOperationMode;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumFluteDirection;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumGrainDirection;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoNotification.EnumClass;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumNamedColor;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFTransferFunction;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.JDFToXJDF;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JMFBuilder;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFCoilBindingParams;
import org.cip4.jdflib.resource.JDFHoleLine;
import org.cip4.jdflib.resource.JDFInsert;
import org.cip4.jdflib.resource.JDFInterpretingParams;
import org.cip4.jdflib.resource.JDFLabelingParams;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.intent.JDFArtDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFDropItemIntent;
import org.cip4.jdflib.resource.intent.JDFInsertingIntent;
import org.cip4.jdflib.resource.intent.JDFLayoutIntent;
import org.cip4.jdflib.resource.intent.JDFMediaIntent;
import org.cip4.jdflib.resource.intent.JDFPackingIntent;
import org.cip4.jdflib.resource.intent.JDFScreeningIntent;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComChannel;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFContact.EnumContactType;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMISDetails;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPageData;
import org.cip4.jdflib.resource.process.JDFPerson;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFTransferCurve;
import org.cip4.jdflib.resource.process.JDFTransferCurvePool;
import org.cip4.jdflib.resource.process.JDFTransferCurveSet;
import org.cip4.jdflib.resource.process.JDFUsageCounter;
import org.cip4.jdflib.resource.process.postpress.JDFChannelBindingParams;
import org.cip4.jdflib.resource.process.postpress.JDFGlue;
import org.cip4.jdflib.resource.process.postpress.JDFGlueApplication;
import org.cip4.jdflib.resource.process.postpress.JDFGlueLine;
import org.cip4.jdflib.resource.process.postpress.JDFHoleMakingParams;
import org.cip4.jdflib.span.JDFSpanScreeningType.EnumSpanScreeningType;
import org.cip4.jdflib.util.FileUtil;
import org.junit.Test;

/**
 * @author rainer prosi
 *
 */
public class JDFToXJDFConverterTest extends JDFTestCaseBase
{

	/**
	 *
	 *
	 */
	@Test
	public void testExtendedAddress()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		final JDFNode n = createBaseProductNode();
		final JDFContact c = n.appendCustomerInfo().appendContact();
		c.makeRootResource(null, null, true);
		c.setContactTypes(EnumContactType.Customer);
		c.appendAddress().setExtendedAddressText("foo");
		// n.ensureLink(c, EnumUsage.Input, null);
		final KElement e = conv.convert(n);
		final KElement resource = new XJDFHelper(e).getSet(ElementName.CONTACT, null).getPartition(0).getResource();
		assertEquals("foo", resource.getElement(ElementName.ADDRESS).getAttribute(ElementName.EXTENDEDADDRESS));
		writeRoundTrip(n, "extendedAddress");
	}

	protected JDFNode createBaseProductNode()
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Product);
		n.setDescriptiveName("description");

		final JDFComponent jdfComponent = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		jdfComponent.setProductType("foo");
		jdfComponent.setComponentType(EnumComponentType.PartialProduct, null);
		return n;
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRefMediaFromInline()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ImageSetting);
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output);
		n.addResource(ElementName.RUNLIST, EnumUsage.Input).setAttribute(AttributeName.NPAGE, "4");
		xm.appendMedia().setMediaSetCount(42);
		final KElement xjdf = conv.convert(n);
		assertNotNull(new XJDFHelper(xjdf).getSet(ElementName.MEDIA, 0));
		writeRoundTrip(n, "xm");

	}

	/**
	*
	 */
	@Test
	public void testDeliveryIntent()
	{
		_testDeliveryIntent();
	}

	/**
	 *
	 */
	@Test
	public void testDependent()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setJobPartID("p");
		n.setType(EnumType.ProcessGroup);
		final JDFNode nc1 = n.addJDFNode(EnumType.ConventionalPrinting);
		final JDFNode nf1 = n.addJDFNode(EnumType.Folding);
		final JDFNode nc2 = n.addJDFNode(EnumType.ConventionalPrinting);
		final JDFNode nf2 = n.addJDFNode(EnumType.Folding);
		final JDFResource r = n.addResource(ElementName.COMPONENT, null);
		final JDFResource r1 = r.addPartition(EnumPartIDKey.SheetName, "s1");
		r1.setDescriptiveName("d1");
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SheetName, "s2");
		r2.setDescriptiveName("d2");
		nc1.linkResource(r1, EnumUsage.Output, null);
		nf1.linkResource(r1, EnumUsage.Input, null);
		nc2.linkResource(r2, EnumUsage.Output, null);
		nf2.linkResource(r2, EnumUsage.Input, null);

		final JDFToXJDF conv = new JDFToXJDF();
		conv.setSingleNode(true);
		final KElement xjdff1 = conv.convert(nf1);
		final KElement xjdff2 = conv.convert(nf2);
		final KElement xjdfc1 = conv.convert(nc1);
		final KElement xjdfc2 = conv.convert(nc2);

		assertEquals(xjdff1.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Dependent/@JobPartID", null), "p.1");
		assertEquals(xjdfc1.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Dependent/@JobPartID", null), "p.2");
		assertEquals(xjdff2.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Dependent/@JobPartID", null), "p.3");
		assertEquals(xjdfc2.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Dependent/@JobPartID", null), "p.4");
	}

	/**
	 *
	 */
	@Test
	public void testDependentMulti()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setJobPartID("p");
		n.setType(EnumType.ProcessGroup);
		final JDFNode nc = n.addJDFNode(EnumType.ConventionalPrinting);
		final JDFNode nf1 = n.addJDFNode(EnumType.Folding);
		final JDFNode nf2 = n.addJDFNode(EnumType.Folding);
		final JDFResource r = n.addResource(ElementName.COMPONENT, null);
		final JDFResource r1 = r.addPartition(EnumPartIDKey.SheetName, "s1");
		r1.setDescriptiveName("d1");
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SheetName, "s2");
		r2.setDescriptiveName("d2");
		nc.linkResource(r, EnumUsage.Output, null);
		nf1.linkResource(r1, EnumUsage.Input, null);
		nf2.linkResource(r2, EnumUsage.Input, null);

		final JDFToXJDF conv = new JDFToXJDF();
		conv.setSingleNode(true);
		final KElement xjdff1 = conv.convert(nf1);
		final KElement xjdff2 = conv.convert(nf2);
		final KElement xjdfc1 = conv.convert(nc);

		assertEquals(xjdfc1.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Dependent/@JobPartID", null), "p.2");

		assertEquals(xjdff1.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Dependent/@JobPartID", null), "p.1");
		assertEquals(xjdff2.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Dependent/@JobPartID", null), "p.1");
	}

	/**
	 *
	 */
	@Test
	public void testDependentMultiPart()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setJobPartID("p");
		n.setType(EnumType.ProcessGroup);
		final JDFNode nc = n.addJDFNode(EnumType.ConventionalPrinting);
		final JDFNode nf = n.addJDFNode(EnumType.Folding);
		final JDFResource r = n.addResource(ElementName.COMPONENT, null);
		final JDFResource r1 = r.addPartition(EnumPartIDKey.SheetName, "s1");
		r1.setDescriptiveName("d1");
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SheetName, "s2");
		r2.setDescriptiveName("d2");
		nc.linkResource(r1, EnumUsage.Output, null).getPartMapVector().add(new JDFAttributeMap(EnumPartIDKey.SheetName, "s2"));
		nf.linkResource(r1, EnumUsage.Input, null).getPartMapVector().add(new JDFAttributeMap(EnumPartIDKey.SheetName, "s2"));

		final JDFToXJDF conv = new JDFToXJDF();
		conv.setSingleNode(true);
		final KElement xjdff1 = conv.convert(nf);
		final KElement xjdfc1 = conv.convert(nc);

		assertEquals(xjdfc1.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Dependent/@JobPartID", null), "p.2");
		assertEquals(xjdff1.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Dependent/@JobPartID", null), "p.1");
	}

	/**
	 *
	 * @return
	 */
	public KElement _testDeliveryIntent()
	{
		final JDFNode nP = new JDFDoc(ElementName.JDF).getJDFRoot();
		nP.setType(EnumType.Product);
		nP.setDescriptiveName("desc");
		final JDFDeliveryIntent di = (JDFDeliveryIntent) nP.addResource(ElementName.DELIVERYINTENT, EnumUsage.Input);
		final JDFComponent c = (JDFComponent) nP.addResource(ElementName.COMPONENT, EnumUsage.Output);
		nP.getLink(c, null).setAmount(66);
		final JDFDropItemIntent dropItemIntent = di.appendDropIntent().appendDropItemIntent();
		dropItemIntent.refComponent(c);
		dropItemIntent.setAmount(42);
		final JDFDropItemIntent dropItemIntent2 = di.appendDropIntent().appendDropItemIntent();
		dropItemIntent2.refComponent(c);
		dropItemIntent2.setAmount(63);
		final XJDF20 xjdf20 = new XJDF20();
		xjdf20.setSingleNode(true);
		final KElement xjdf = xjdf20.makeNewJDF(nP, null);
		xjdf.write2File(sm_dirTestDataTemp + "delTest2.xjdf");
		assertNotNull(xjdf);
		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource/DeliveryParams/DropItem/@Amount", null), "42");
		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource[2]/DeliveryParams/DropItem/@Amount", null), "63");
		return xjdf;
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testSpanIntent()
	{
		final JDFNode nP = createBaseProductNode();
		nP.setType(EnumType.Product);
		nP.setDescriptiveName("desc");
		final JDFMediaIntent mi = (JDFMediaIntent) nP.addResource(ElementName.MEDIAINTENT, EnumUsage.Input);
		mi.appendBrightness().setPreferred(42);
		mi.appendMediaQuality().setPreferred("foo");

		final JDFToXJDF xjdf20 = new JDFToXJDF();
		xjdf20.setSingleNode(true);
		xjdf20.setSpanAsAttribute(true);
		final KElement xjdf = xjdf20.makeNewJDF(nP, null);
		xjdf.write2File(sm_dirTestDataTemp + "bind.xjdf");
		assertNotNull(xjdf);
		assertEquals(xjdf.getXPathAttribute("ProductList/Product/Intent/MediaIntent/@MediaQuality", null), "foo");
		assertNull(xjdf.getXPathElement("ProductList/Product/Intent/MediaIntent/Brightness"));
		assertNull(xjdf.getXPathElement("ProductList/Product/Intent/MediaIntent/@Brightness"));
		writeRoundTrip(nP, "intent");
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
		xjdf.write2File(sm_dirTestDataTemp + "dielayoutproduction.xjdf");
		assertNotNull(xjdf);
		assertEquals("2267.72", xjdf.getXPathAttribute("ResourceSet/Resource/DieLayoutProductionParams/ConvertingConfig/@SheetHeightMax", null));
	}

	/**
	 *
	 * @return
	 * @throws DataFormatException
	 */
	@Test
	public void testTCPPartitioned() throws DataFormatException
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Stripping);
		final JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Input);
		final JDFLayout lo1 = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFLayout lo2 = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "s2");

		final JDFTransferCurvePool tcp = (JDFTransferCurvePool) n.addResource(ElementName.TRANSFERCURVEPOOL, EnumUsage.Input);
		final JDFTransferCurvePool tcp1 = (JDFTransferCurvePool) tcp.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFTransferCurveSet tcs1 = tcp1.appendTransferCurveSet();
		tcs1.setName("Paper");
		tcs1.setDescriptiveName("ddd");
		final JDFTransferCurve tc1 = tcs1.appendTransferCurve();
		tc1.setCurve(new JDFTransferFunction("0 0 1 1"));
		tc1.setSeparation("Cyan");

		final JDFTransferCurvePool tcp2 = (JDFTransferCurvePool) tcp.addPartition(EnumPartIDKey.SheetName, "s2");
		final JDFTransferCurveSet tcs2 = tcp2.appendTransferCurveSet();
		tcs2.setName("Paper");
		tcs2.setDescriptiveName("eee");
		final JDFTransferCurve tc2 = tcs2.appendTransferCurve();
		tc2.setCurve(new JDFTransferFunction("1 1 0 0"));
		tc2.setSeparation("Cyan");
		lo1.refElement(tcp1);
		lo2.refElement(tcp2);

		final JDFToXJDF xjdf20 = new JDFToXJDF();
		xjdf20.setSingleNode(true);
		final KElement xjdf = xjdf20.makeNewJDF(n, null);
		xjdf.write2File(sm_dirTestDataTemp + "tcp.xjdf");
		assertNotNull(xjdf);
		assertEquals("0 0 1 1", xjdf.getXPathAttribute("ResourceSet/Resource/TransferCurve/@Curve", null));
		assertEquals("1 1 0 0", xjdf.getXPathAttribute("ResourceSet/Resource[2]/TransferCurve/@Curve", null));
	}

	/**
	 *
	 * @return
	 * @throws DataFormatException
	 */
	@Test
	public void testTCPRef() throws DataFormatException
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Stripping);
		final JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Input);
		final JDFLayout lo1 = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFLayout lo2 = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "s2");

		final JDFTransferCurvePool tcp = (JDFTransferCurvePool) n.addResource(ElementName.TRANSFERCURVEPOOL, null);
		final JDFTransferCurvePool tcp1 = (JDFTransferCurvePool) tcp.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFTransferCurveSet tcs1 = tcp1.appendTransferCurveSet();
		tcs1.setName("Paper");
		tcs1.setDescriptiveName("ddd");
		final JDFTransferCurve tc1 = tcs1.appendTransferCurve();
		tc1.setCurve(new JDFTransferFunction("0 0 1 1"));
		tc1.setSeparation("Cyan");

		final JDFTransferCurvePool tcp2 = (JDFTransferCurvePool) tcp.addPartition(EnumPartIDKey.SheetName, "s2");
		final JDFTransferCurveSet tcs2 = tcp2.appendTransferCurveSet();
		tcs2.setName("Paper");
		tcs2.setDescriptiveName("eee");
		final JDFTransferCurve tc2 = tcs2.appendTransferCurve();
		tc2.setCurve(new JDFTransferFunction("1 1 0 0"));
		tc2.setSeparation("Cyan");
		lo1.refElement(tcp1);
		lo2.refElement(tcp2);

		final JDFToXJDF xjdf20 = new JDFToXJDF();
		xjdf20.setSingleNode(true);
		final KElement xjdf = xjdf20.makeNewJDF(n, null);
		xjdf.write2File(sm_dirTestDataTemp + "tcp.xjdf");
		assertNotNull(xjdf);
		assertEquals("0 0 1 1", xjdf.getXPathAttribute("ResourceSet/Resource/TransferCurve/@Curve", null));
		assertEquals("1 1 0 0", xjdf.getXPathAttribute("ResourceSet/Resource[2]/TransferCurve/@Curve", null));
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testMultiTypes()
	{
		final JDFNode nP = createBaseProductNode();
		nP.addJDFNode(EnumType.ImageSetting);
		nP.addJDFNode(EnumType.ConventionalPrinting);
		final JDFToXJDF xjdf20 = new JDFToXJDF();
		final KElement xjdf = xjdf20.convert(nP);
		assertEquals("Product ImageSetting ConventionalPrinting", xjdf.getAttribute("Types"));
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testMultiTypeProductPart()
	{
		final JDFNode nP = createBaseProductNode();
		nP.addJDFNode(EnumType.ImageSetting).setJobPartID("j1");
		nP.addJDFNode(EnumType.ConventionalPrinting).setJobPartID("j2");
		final JDFToXJDF xjdf20 = new JDFToXJDF();
		final KElement xjdf = xjdf20.convert(nP);
		assertEquals(3, new XJDFHelper(xjdf).getSets(ElementName.NODEINFO, null).size());
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testSingleTypes()
	{
		final JDFNode nP = createBaseProductNode();
		nP.setType(EnumType.ImageSetting);
		final JDFToXJDF xjdf20 = new JDFToXJDF();
		final KElement xjdf = xjdf20.convert(nP);
		assertEquals("ImageSetting", xjdf.getAttribute("Types"));
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testPackingIntent()
	{
		final JDFNode nP = createBaseProductNode();
		final JDFPackingIntent pi = (JDFPackingIntent) nP.addResource(ElementName.PACKINGINTENT, EnumUsage.Input);
		pi.appendBoxedQuantity().setActual(43);

		final JDFToXJDF xjdf20 = new JDFToXJDF();
		xjdf20.setSingleNode(true);
		xjdf20.setSpanAsAttribute(true);
		KElement xjdf = xjdf20.makeNewJDF(nP, null);
		xjdf.write2File(sm_dirTestDataTemp + "pack.xjdf");
		assertNotNull(xjdf);
		assertNull(xjdf.getXPathElement("ProductList/Product/Intent/PackingIntent"));
		xjdf20.setRetainAll(true);
		xjdf = xjdf20.makeNewJDF(nP, null);
		xjdf.write2File(sm_dirTestDataTemp + "packretain.xjdf");
		assertNotNull(xjdf);
		assertNotNull(xjdf.getXPathElement("ProductList/Product/Intent/PackingIntent"));
		writeRoundTrip(nP, "pack");
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testScreeningIntent()
	{
		final JDFNode nP = createBaseProductNode();
		final JDFScreeningIntent pi = (JDFScreeningIntent) nP.addResource(ElementName.SCREENINGINTENT, EnumUsage.Input);
		pi.appendScreeningType().setActual(EnumSpanScreeningType.AM);

		final JDFToXJDF xjdf20 = new JDFToXJDF();
		xjdf20.setSingleNode(true);
		xjdf20.setSpanAsAttribute(true);
		KElement xjdf = xjdf20.makeNewJDF(nP, null);
		xjdf.write2File(sm_dirTestDataTemp + "screen.xjdf");
		assertNotNull(xjdf);
		assertNull(xjdf.getXPathElement("ProductList/Product/Intent/ScreeningIntent"));
		xjdf20.setRetainAll(true);
		xjdf = xjdf20.makeNewJDF(nP, null);
		xjdf.write2File(sm_dirTestDataTemp + "screenretain.xjdf");
		assertNotNull(xjdf);
		assertNotNull(xjdf.getXPathElement("ProductList/Product/Intent/ScreeningIntent"));
		writeRoundTrip(nP, "screen");
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testRetainResPool()
	{
		final JDFNode nP = createBaseProductNode();
		final JDFScreeningIntent pi = (JDFScreeningIntent) nP.addResource(ElementName.SCREENINGINTENT, EnumUsage.Input);
		pi.appendScreeningType().setActual(EnumSpanScreeningType.AM);

		final JDFToXJDF xjdf20 = new JDFToXJDF();
		xjdf20.setSingleNode(true);
		xjdf20.setSpanAsAttribute(true);
		xjdf20.setRetainAll(true);
		final KElement xjdf = xjdf20.makeNewJDF(nP, null);
		xjdf.write2File(sm_dirTestDataTemp + "retain.xjdf");
		assertNotNull(xjdf);
		assertNull(xjdf.getChildByTagName(ElementName.RESOURCEPOOL, null, 0, null, false, false));
		writeRoundTrip(nP, "pack");
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testPageList()
	{
		_testPageList(false);
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testPageListRetainAll()
	{
		_testPageList(true);
	}

	/**
	 *
	 * @return
	 */
	public KElement _testPageList(final boolean retainAll)
	{
		final JDFNode root = new JDFDoc("JDF").getJDFRoot();
		root.setType(EnumType.Trapping);
		root.setDescriptiveName("desc");

		final JDFRunList rl = (JDFRunList) root.addResource(ElementName.RUNLIST, EnumUsage.Input);
		final JDFRunList ruLiRun = rl.addRun("foo.pdf", 0, 42);
		final JDFRunList ruLiRun2 = rl.addRun("foo2.pdf", 0, 666);
		JDFPageList pList = ruLiRun.appendPageList();
		pList = (JDFPageList) pList.makeRootResource(null, null, true);
		ruLiRun2.refElement(pList);

		pList.appendScreeningParams().appendScreenSelector().setAngle(42);

		for (int i = 0; i < 4; i++)
		{
			final JDFPageData pd = pList.appendPageData();
			pd.setPageStatus("DigitalArtArrived");
			pd.setIsBlank(i % 2 == 0);
		}

		final XJDF20 xjdf20 = new XJDF20();
		xjdf20.setSingleNode(true);
		xjdf20.setRetainAll(retainAll);
		final KElement xjdf = xjdf20.makeNewJDF(root, null);
		xjdf.write2File(sm_dirTestDataTemp + "pageListTest." + retainAll + ".xjdf");
		assertNotNull(xjdf);
		if (!retainAll)
		{
			for (int i = 1; i < 5; i++)
			{
				assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource[" + i + "]/Content/@IsBlank", null), i % 2 == 1 ? "true" : "false");
				assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource[" + i + "]/Content/@ContentStatus", null), "DigitalArtArrived");
			}
		}
		return xjdf;
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testPageListEmpty()
	{
		_testPageListEmpty();
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testPageListNS()
	{
		final JDFNode root = new JDFDoc(ElementName.JDF).getJDFRoot();
		root.setType(EnumType.Trapping);
		root.setDescriptiveName("desc");
		root.addNameSpace("Foo", "www.foo.com");
		final JDFResource pList = root.addResource("Foo:" + ElementName.PAGELIST, EnumUsage.Input);
		pList.setDescriptiveName("Desc");
		assertFalse(pList instanceof JDFPageList);
		final XJDF20 xjdf20 = new XJDF20();
		xjdf20.setSingleNode(true);
		final KElement xjdf = xjdf20.makeNewJDF(root, null);
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Foo:PageList\"]/Resource/@DescriptiveName", null), "Desc");

	}

	/**
	 *
	 * @return
	 */
	public KElement _testPageListEmpty()
	{
		final JDFNode root = new JDFDoc(ElementName.JDF).getJDFRoot();
		root.setType(EnumType.Trapping);
		root.setDescriptiveName("desc");

		final JDFRunList rl = (JDFRunList) root.addResource(ElementName.RUNLIST, EnumUsage.Input);
		final JDFPageList pList = (JDFPageList) root.addResource(ElementName.PAGELIST, null);

		final JDFRunList ruLiRun = rl.addRun("P1.pdf", 0, 42);
		final JDFRunList ruLiRun2 = rl.addRun("P2.pdf", 0, 42);
		final JDFResource p1 = pList.addPartition(EnumPartIDKey.PartVersion, "P1");
		ruLiRun.refElement(p1);
		p1.setDescriptiveName("Pl for p1");
		final JDFResource p2 = pList.addPartition(EnumPartIDKey.PartVersion, "P2");
		p2.setDescriptiveName("Pl for p2");
		ruLiRun2.refElement(p2);

		final XJDF20 xjdf20 = new XJDF20();
		xjdf20.setSingleNode(true);
		final KElement xjdf = xjdf20.makeNewJDF(root, null);
		xjdf.write2File(sm_dirTestDataTemp + "pageListEmptyTest.xjdf");
		assertNotNull(xjdf);
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Content\"]/Resource/@DescriptiveName", null), "Pl for p1");
		return xjdf;
	}

	/**
	 *
	 *
	 */
	@Test
	public void testSignatureName()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFConventionalPrintingParams cp = (JDFConventionalPrintingParams) n.addResource(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		cp.setWorkStyle(EnumWorkStyle.WorkAndBack);
		final JDFComponent c = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		c.setDescriptiveName("desc");
		c.setComponentType(EnumComponentType.PartialProduct, null);
		c.addPartition(EnumPartIDKey.SignatureName, "Sig1").addPartition(EnumPartIDKey.SheetName, "s1");
		final KElement x = conv.convert(n);
		assertEquals(x.toXML().indexOf(AttributeName.SIGNATURENAME), -1);
		conv.setRemoveSignatureName(false);
		final KElement x2 = conv.convert(n);
		assertTrue(x2.toXML().indexOf(AttributeName.SIGNATURENAME) > 0);
		writeRoundTrip(n, "print");

	}

	/**
	 *
	 *
	 */
	@Test
	public void testKeepParameter()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFResource c = n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		c.setDescriptiveName("desc");
		n.setStatus(EnumNodeStatus.Cleanup);
		conv.setParameterSet(true);
		final KElement xjdf = conv.convert(n);
		assertEquals(xjdf.getXPathAttribute("ParameterSet/Parameter/NodeInfo/@Status", null), "Cleanup");
		assertEquals(xjdf.getXPathAttribute("ResourceSet/@DescriptiveName", null), "desc");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testCleanupFalse()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFToXJDF conv = new JDFToXJDF();
		conv.setCleanup(false);
		final KElement xjdf = conv.convert(n);
		assertNotNull(xjdf.getXPathAttribute("ResourceSet/@ID", null));
		assertNotNull(xjdf.getXPathAttribute("ResourceSet/Resource/@ID", null));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testCleanupTrue()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertNull(xjdf.getXPathAttribute("ResourceSet/@ID", null));
		assertNull(xjdf.getXPathAttribute("ResourceSet/Resource/@ID", null));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testComponentProductID()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		conv.setWantProduct(true);
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Product);
		final JDFComponent c = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		c.setDescriptiveName("desc");
		c.setProductID("prodID");
		c.setComponentType(EnumComponentType.PartialProduct, EnumComponentType.Sheet);
		n.setStatus(EnumNodeStatus.Cleanup);
		final KElement xjdf = conv.convert(n);
		assertEquals(xjdf.getXPathAttribute("ProductList/Product/@ExternalID", null), "prodID");
		writeRoundTrip(n, "pid");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testColorantControl()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ImageSetting);
		final JDFColorantControl cc = (JDFColorantControl) n.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		cc.getCreateColorantOrder().setSeparations(new VString("Cyan Magenta Yellow Black", null));
		cc.getCreateDeviceColorantOrder().setSeparations(new VString("Magenta Cyan Yellow Black", null));
		cc.setProcessColorModel("DeviceCMYK");

		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output);
		xm.setPunchType("pt");
		xm.appendMedia().setMediaType(EnumMediaType.Plate);

		final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		rl.setPages(JDFIntegerRangeList.createIntegerRangeList("3~5"));

		final JDFResourceLink ccl = n.getLink(cc, null);
		ccl.appendPart().setSeparation("Cyan");
		ccl.appendPart().setSeparation("Black");

		final JDFToXJDF conv = new JDFToXJDF();
		conv.setRetainAll(false);

		final KElement xjdf = conv.convert(n);

		final JDFColorantControl ccNew = (JDFColorantControl) new XJDFHelper(xjdf).getPartition(ElementName.COLORANTCONTROL, 0, 0).getResource();
		assertEquals(ccNew.getAttribute(ElementName.COLORANTPARAMS), "Cyan Magenta Yellow Black");
		assertEquals(ccNew.getAttribute(ElementName.COLORANTORDER), "Cyan Black");
		assertNull(ccNew.getNonEmpty(ElementName.DEVICECOLORANTORDER));

		writeRoundTrip(n, "colorcont");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testColorantControlRefPool()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ImageSetting);
		final JDFColorantControl cc = (JDFColorantControl) n.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		cc.getCreateColorantOrder().setSeparations(new VString("Cyan Magenta Yellow Black", null));
		cc.getCreateDeviceColorantOrder().setSeparations(new VString("Magenta Cyan Yellow Black", null));
		cc.setProcessColorModel("DeviceCMYK");

		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output);
		xm.setPunchType("pt");
		xm.appendMedia().setMediaType(EnumMediaType.Plate);

		final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		rl.setPages(JDFIntegerRangeList.createIntegerRangeList("3~5"));

		final JDFColorPool cp = (JDFColorPool) cc.appendColorPool().makeRootResource(null, null, true);
		final JDFColor color = cp.appendColor();
		color.setName("spot");
		color.setGray(44);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);

		final JDFColorantControl ccNew = (JDFColorantControl) new XJDFHelper(xjdf).getPartition(ElementName.COLORANTCONTROL, 0, 0).getResource();
		assertNull(ccNew.getNonEmpty("ColorRef"));
		writeRoundTrip(n, "colorpool");

	}

	/**
	 *
	 *
	 */
	@Test
	public void testColorPool()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ImageSetting);
		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, EnumUsage.Input);
		cp.appendColorWithName("c1", null);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);

		final ResourceHelper partition = new XJDFHelper(xjdf).getPartition(ElementName.COLOR, 0, 0);
		final JDFColor cNew = (JDFColor) partition.getResource();
		assertEquals(cNew.getAttribute(AttributeName.ACTUALCOLORNAME), "c1");
		assertEquals(partition.getPartMap().get(AttributeName.SEPARATION), "c1");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testColorPoolSpace()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ImageSetting);
		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, EnumUsage.Input);
		cp.appendColorWithName("sep 1", null);
		cp.appendColorWithName("sep 2", null);

		final JDFColorantControl cc = (JDFColorantControl) n.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		cc.getCreateColorantOrder().setSeparations(new VString("Cyan,Magenta,Yellow,Black,sep 1,sep 2", ","));
		cc.setProcessColorModel("DeviceCMYK");

		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output);
		xm.setPunchType("pt");
		xm.appendMedia().setMediaType(EnumMediaType.Plate);

		final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		rl.setPages(JDFIntegerRangeList.createIntegerRangeList("3~5"));

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);

		for (int i = 0; i < 2; i++)
		{
			final ResourceHelper partition = new XJDFHelper(xjdf).getPartition(ElementName.COLOR, 0, i);
			final JDFColor cNew = (JDFColor) partition.getResource();
			assertEquals(cNew.getAttribute(AttributeName.ACTUALCOLORNAME), "sep " + (i + 1));
			assertEquals(partition.getPartMap().get(AttributeName.SEPARATION), "sep_" + (i + 1));
		}
		writeRoundTrip(n, "colorspaces");
	}

	/**
	*
	*
	*/
	@Test
	public void testColorantControlSpace()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ImageSetting);
		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, EnumUsage.Input);
		cp.appendColorWithName("sep 1", null);
		cp.appendColorWithName("sep 2", null);

		final JDFColorantControl cc = (JDFColorantControl) n.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		cc.appendColorantParams().setSeparations(new VString("sep 1,sep 2", ","));

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);

		final ResourceHelper partition = new XJDFHelper(xjdf).getPartition(ElementName.COLORANTCONTROL, 0, 0);
		final JDFColorantControl cNew = (JDFColorantControl) partition.getResource();
		assertEquals(cNew.getAttribute(ElementName.COLORANTPARAMS), "sep_1 sep_2");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testColorIntentStandard()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Product);
		final JDFComponent co = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		co.setComponentType(EnumComponentType.PartialProduct, null);
		final JDFColorIntent ci = (JDFColorIntent) n.getCreateResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		ci.appendColorsUsed().setCMYK();
		ci.appendColorStandard().setActual("CMYK");
		ci.appendColorICCStandard().setActual("ICC");
		final KElement xjdf = conv.convert(n);
		final ProductHelper ph = new XJDFHelper(xjdf).getProductHelpers().get(0);
		assertNotNull(ph);
		final IntentHelper ih = ph.getIntent("ColorIntent");
		final KElement e = ih.getResource();
		assertEquals(e.getChildElementVector("SurfaceColor", null).size(), 2);
		assertEquals("CMYK", e.getChildElementVector("SurfaceColor", null).get(0).getAttribute(XJDFConstants.PrintStandard));
		writeRoundTrip(n, "ColorIntent");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testColorIntent()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Product);
		final JDFComponent co = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		co.setComponentType(EnumComponentType.PartialProduct, null);
		final JDFColorIntent ci = (JDFColorIntent) n.getCreateResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		final JDFColorIntent cif = (JDFColorIntent) ci.addPartition(EnumPartIDKey.Side, "Front");
		cif.appendColorsUsed().setCMYK();
		cif.appendCoatings().setActual("Varnish");
		final JDFColorIntent cib = (JDFColorIntent) ci.addPartition(EnumPartIDKey.Side, "Back");
		cib.appendCoatings().setActual("Mess");
		cib.setNumColors(1);
		final KElement xjdf = conv.convert(n);
		final ProductHelper ph = new XJDFHelper(xjdf).getProductHelpers().get(0);
		assertNotNull(ph);
		final IntentHelper ih = ph.getIntent("ColorIntent");
		final KElement e = ih.getResource();
		assertEquals(e.getChildElementVector("SurfaceColor", null).size(), 2);
		writeRoundTrip(n, "ColorIntent");
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

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement newRoot = conv.convert(d.getJDFRoot());
		final XJDFHelper h2 = new XJDFHelper(newRoot);
		assertEquals(h2.numProductHelpers(true), 2);
		assertEquals(h2.numProductHelpers(false), 6);
	}

	/**
	 *
	 */
	@Test
	public void testDeviceInfoStatus()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		final JDFDeviceInfo di = jmf.getCreateSignal(0).appendDeviceInfo();
		di.appendDevice().setDeviceID("id");
		di.setDeviceStatus(EnumDeviceStatus.Running);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals(xjmf.getXPathAttribute("SignalStatus/DeviceInfo/@Status", null), "Production");
	}

	/**
	 *
	 */
	@Test
	public void testDeviceInfoEmployees()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		final JDFDeviceInfo di = jmf.getCreateSignal(0).appendDeviceInfo();
		di.appendDevice().setDeviceID("id");
		di.setDeviceStatus(EnumDeviceStatus.Running);
		di.appendEmployee().setPersonalID("e1");
		di.appendEmployee().setPersonalID("e2");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals(xjmf.getXPathAttribute("SignalStatus/DeviceInfo/Activity/@PersonalID", null), "e1");
		assertEquals(xjmf.getXPathAttribute("SignalStatus/DeviceInfo/Activity[2]@PersonalID", null), "e2");
	}

	/**
	 *
	 */
	@Test
	public void testDeviceInfoModuleStatus()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		final JDFDeviceInfo di = jmf.getCreateSignal(0).appendDeviceInfo();
		di.appendDevice().setDeviceID("id");
		di.setDeviceStatus(EnumDeviceStatus.Running);
		di.appendModuleStatus().setModuleIndex(new JDFIntegerRangeList(new int[] { 0 }));
		di.appendModuleStatus().setModuleIndex(new JDFIntegerRangeList(new int[] { 1 }));
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals(xjmf.getXPathAttribute("SignalStatus/DeviceInfo/@ModuleIDs", null), "0 1");
	}

	/**
	 *
	 */
	@Test
	public void testJMFEmployee()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.PipeClose);
		jmf.getCommand(0).appendEmployee().setPersonalID("P1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals(xjmf.getXPathAttribute("CommandPipeControl/Header/@PersonalID", null), "P1");

		final XJDFToJDFConverter invert = new XJDFToJDFConverter(null);
		final JDFDoc d = invert.convert(xjmf);
		final JDFJMF jmf2 = d.getJMFRoot();
		assertEquals(jmf2.getCommand(0).getEmployee(0).getPersonalID(), "P1");
	}

	/**
	 *
	 */
	@Test
	public void testNodeEmployee()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFEmployee emp = (JDFEmployee) n.addResource(ElementName.EMPLOYEE, EnumUsage.Input);
		final VString roles = new VString("ShiftLeader TelephoneSanitizer", null);
		emp.setRoles(roles);
		emp.setPersonalID("P1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/Part/@ContactType", null), ElementName.EMPLOYEE);
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/Contact/@ContactTypeDetails", null), "ShiftLeader TelephoneSanitizer");
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/@ExternalID", null), "P1");

		final XJDFToJDFConverter invert = new XJDFToJDFConverter(null);
		final JDFDoc d = invert.convert(xjdf);
		final JDFNode n2 = d.getJDFRoot();
		final JDFEmployee emp2 = (JDFEmployee) n2.getResource(ElementName.EMPLOYEE, EnumUsage.Input, 0);
		assertEquals(emp2.getPersonalID(), "P1");
		assertEquals(emp2.getRoles(), roles);
	}

	/**
	 *
	 */
	@Test
	public void testRef()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFResource r1 = n.addResource(ElementName.PAGELIST, EnumUsage.Input);
		final JDFResource r2 = n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		final JDFResource r3 = n.addResource(ElementName.COMPONENT, EnumUsage.Input);

		r1.refElement(r2);
		r1.refElement(r3);
		r2.refElement(r1);
		r2.refElement(r3);
		r3.refElement(r1);
		r3.refElement(r2);

		final JDFToXJDF conv = new JDFToXJDF();
		KElement xjdf = conv.makeNewJDF(n, null);
		assertNotNull(xjdf);

		conv.setRetainAll(true);
		xjdf = conv.makeNewJDF(n, null);
		assertNotNull(xjdf);
	}

	/**
	 *
	 */
	@Test
	public void testRefSelf()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource r1 = n.addResource(ElementName.COMPONENT, EnumUsage.Input);

		r1.refElement(r1);

		final JDFToXJDF conv = new JDFToXJDF();
		KElement xjdf = conv.makeNewJDF(n, null);
		assertNotNull(xjdf);

		conv.setRetainAll(true);
		xjdf = conv.makeNewJDF(n, null);
		assertNotNull(xjdf);
	}

	/**
	*
	*/
	@Test
	public void testRunListDir()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.RasterReading);
		final JDFRunList r1 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		r1.setFileURL("file:///foo.pdf");
		final JDFRunList r2 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Output);
		r2.setDirectory("file:///dir");
		writeRoundTrip(n, "RunListDir");
	}

	/**
	*
	*/
	@Test
	public void testRunListCopies()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.RasterReading);
		final JDFRunList r1 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		r1.setFileURL("file:///foo.pdf");
		r1.setDocCopies(new JDFIntegerRangeList(new JDFIntegerRange(42)));
		final JDFRunList r2 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Output);
		r2.setFileURL("file:///fooout.pdf");
		writeRoundTrip(n, "RunListCopies");
	}

	/**
	 *
	 */
	@Test
	public void testResLocked()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource r1 = n.addResource(ElementName.COMPONENT, EnumUsage.Input);
		r1.setDescriptiveName("c1");

		r1.setLocked(true);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertNull(xjdf.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Resource/@Locked", null));
	}

	/**
	 *
	 */
	@Test
	public void testResUnit()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource r1 = n.addResource(ElementName.MEDIA, EnumUsage.Input);
		r1.setDescriptiveName("c1");
		r1.setUnit("parsec");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertNull(xjdf.getXPathAttribute("ResourceSet[@Name=\"Media\"]/Resource/@Unit", null));
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Media\"]/@Unit", null), "parsec");
	}

	/**
	 *
	 */
	@Test
	public void testSourceRes()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource m1 = n.addResource(ElementName.MEDIA, EnumUsage.Input);
		m1.setDescriptiveName("mmm");
		final JDFResource r1 = n.addResource(ElementName.COMPONENT, EnumUsage.Input);
		r1.setDescriptiveName("c1");
		r1.appendSourceResource().refElement(m1);

		r1.setLocked(true);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertTrue(xjdf.toXML().indexOf(ElementName.SOURCERESOURCE) < 0);
	}

	/**
	 *
	 */
	@Test
	public void testWorkstepID()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFNodeInfo ni = n.getCreateNodeInfo();
		ni.setWorkStepID("w1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertEquals("w1", new XJDFHelper(xjdf).getSet(ElementName.NODEINFO, null).getPartition(0).getAttribute(XJDFConstants.ExternalID));
	}

	/**
	 *
	 */
	@Test
	public void testCounterID()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFUsageCounter uc = (JDFUsageCounter) n.getCreateResource(ElementName.USAGECOUNTER, EnumUsage.Input, null);
		uc.setCounterID("c1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertEquals("c1", new XJDFHelper(xjdf).getSet(ElementName.USAGECOUNTER, null).getPartition(0).getAttribute(XJDFConstants.ExternalID));
	}

	/**
	 *
	 */
	@Test
	public void testMISDetails()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFNodeInfo ni = n.getCreateNodeInfo();
		final JDFMISDetails det = ni.appendMISDetails();
		det.setDeviceOperationMode(EnumDeviceOperationMode.Maintenance);
		det.setCostType(EnumCostType.Chargeable);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		final JDFNodeInfo ni2 = (JDFNodeInfo) new XJDFHelper(xjdf).getSet(ElementName.NODEINFO, null).getPartition(0).getResource();
		assertNull(ni2.getMISDetails().getDeviceOperationMode());

	}

	/**
	 *
	 */
	@Test
	public void testPerson()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFEmployee emp = (JDFEmployee) n.addResource(ElementName.EMPLOYEE, EnumUsage.Input);
		final VString roles = new VString("ShiftLeader TelephoneSanitizer", null);
		emp.setRoles(roles);
		emp.setPersonalID("P1");
		final JDFPerson person = emp.appendPerson();
		person.setFirstName("Snoopy");
		person.appendAddress().setStreet("Sesame");
		final JDFComChannel cc1 = person.appendComChannel();
		cc1.setLocator("tel:123");
		cc1.setChannelType(EnumChannelType.Phone);
		final JDFComChannel cc2 = person.appendComChannel();
		cc2.setLocator("fax:123");
		cc1.setChannelType(EnumChannelType.Fax);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/Part/@ContactType", null), "Employee");
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/Contact/@ContactTypeDetails", null), "ShiftLeader TelephoneSanitizer");
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/@ExternalID", null), "P1");
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/Contact/Address/@Street", null), "Sesame");
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/Contact/ComChannel/@Locator", null), "tel:123");
	}

	/**
	 *
	 */
	public KElement _testPreview()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFPreview pv = (JDFPreview) n.addResource(ElementName.PREVIEW, EnumUsage.Input);
		pv.setURL("previewURL");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertNull(xjdf.getXPathAttribute("ResourceSet[@Name=\"Preview\"]/Resource/Preview/@URL", null));
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Preview\"]/Resource/Preview/FileSpec/@URL", null), "previewURL");
		return xjdf;
	}

	/**
	 *
	 */
	@Test
	public void testPreview()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFPreview pv = (JDFPreview) n.addResource(ElementName.PREVIEW, EnumUsage.Input);
		pv.setURL("previewURL");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertNull(xjdf.getXPathAttribute("ResourceSet[@Name=\"Preview\"]/Resource/Preview/@URL", null));
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Preview\"]/Resource/Preview/FileSpec/@URL", null), "previewURL");
	}

	/**
	 *
	 */
	@Test
	public void testProductRetainAll()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Product);
		n.addResource(ElementName.LAYOUTINTENT, EnumUsage.Input);

		final JDFToXJDF conv = new JDFToXJDF();
		conv.setRetainAll(true);
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertNull(xjdf.getXPathAttribute("ProductList/Product/IntentSet", null));
		assertEquals(xjdf.getXPathAttribute("ProductList/Product/Intent/@Name", null), ElementName.LAYOUTINTENT);
	}

	/**
	 *
	 */
	@Test
	public void testNotificationAudit()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.getCreateAuditPool().addNotification(EnumClass.Error, "me", null).appendComment().setText("foo");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertEquals(xjdf.getXPathAttribute("AuditPool/AuditNotification/Notification/Comment", null), "foo");

		final XJDFToJDFConverter invert = new XJDFToJDFConverter(null);
		final JDFDoc d = invert.convert(xjdf);
		final JDFNode n2 = d.getJDFRoot();
		final JDFAudit audit = n2.getAuditPool().getAudit(0, EnumAuditType.Notification, null, null);
		assertEquals(audit.getEmployee(0).getDescriptiveName(), "me");
		assertEquals(audit.getComment(0).getText(), "foo");
	}

	/**
	 *
	 */
	@Test
	public void testNotificationEvent()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.getCreateAuditPool().addNotification(EnumClass.Error, "me", null).appendEvent().setEventID("IDfoo");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertEquals(xjdf.getXPathAttribute("AuditPool/AuditNotification/Notification/Event/@EventID", null), "IDfoo");

		final XJDFToJDFConverter invert = new XJDFToJDFConverter(null);
		final JDFDoc d = invert.convert(xjdf);
		final JDFNode n2 = d.getJDFRoot();
		final JDFNotification audit = (JDFNotification) n2.getAuditPool().getAudit(0, EnumAuditType.Notification, null, null);
		assertEquals(audit.getEmployee(0).getDescriptiveName(), "me");
		assertEquals(audit.getEvent().getEventID(), "IDfoo");
	}

	/**
	 *
	 */
	@Test
	public void testAuditEmployee()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.getCreateAuditPool().addAudit(EnumAuditType.PhaseTime, "me");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertEquals(xjdf.getXPathAttribute("AuditPool/AuditStatus/Header/@Author", null), "me");

		final XJDFToJDFConverter invert = new XJDFToJDFConverter(null);
		final JDFDoc d = invert.convert(xjdf);
		final JDFNode n2 = d.getJDFRoot();
		assertEquals(n2.getAuditPool().getAudit(0, EnumAuditType.PhaseTime, null, null).getEmployee(0).getDescriptiveName(), "me");
	}

	/**
	 *
	 */
	@Test
	public void testArtDeliveryRunLists()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Product);
		final JDFArtDeliveryIntent adi = (JDFArtDeliveryIntent) n.addResource(ElementName.ARTDELIVERYINTENT, EnumUsage.Input);
		adi.appendArtDelivery().appendRunList().setFileURL("f1.pdf");
		adi.appendArtDelivery().appendRunList().setFileURL("f2.pdf");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		final XJDFHelper h = new XJDFHelper(xjdf);
		assertEquals(2, h.getSet(ElementName.RUNLIST, 0).getPartitions().size());
	}

	/**
	 *
	 */
	@Test
	public void testAuditCreated()
	{
		JDFAudit.setStaticAgentName("CIP4 Test Agent");
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		final String agent = xjdf.getXPathAttribute("AuditPool/AuditCreated/Header/@AgentName", null);
		assertEquals("CIP4 Test Agent", agent);

		final XJDFToJDFConverter invert = new XJDFToJDFConverter(null);
		final JDFDoc d = invert.convert(xjdf);
		final JDFNode n2 = d.getJDFRoot();
		assertEquals(n2.getAuditPool().getAudit(0, EnumAuditType.Created, null, null).getAgentName(), agent);
	}

	/**
	 *
	 */
	@Test
	public void testAuditProcessRun()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final VJDFAttributeMap maps = new VJDFAttributeMap();
		maps.add(new JDFAttributeMap("SheetName", "s1"));
		maps.add(new JDFAttributeMap("SheetName", "s2"));
		n.getCreateAuditPool().addProcessRun(EnumNodeStatus.Completed, "me", maps);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertEquals(xjdf.getXPathAttribute("AuditPool/AuditProcessRun/ProcessRun/Part[1]/@SheetName", null), "s1");
		assertEquals(xjdf.getXPathAttribute("AuditPool/AuditProcessRun/ProcessRun/Part[2]/@SheetName", null), "s2");

		final XJDFToJDFConverter invert = new XJDFToJDFConverter(null);
		final JDFDoc d = invert.convert(xjdf);
		final JDFNode n2 = d.getJDFRoot();
		assertEquals(n2.getAuditPool().getAudit(0, EnumAuditType.ProcessRun, null, null).getPartMapVector().size(), 2);
	}

	/**
	 *
	 */
	@Test
	public void testAmountPoolWaste()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource media = n.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(media, null);

		final JDFAttributeMap mPart = new JDFAttributeMap(AttributeName.CONDITION, "Good");
		mPart.put("SheetName", "S1");
		rl.setActualAmount(10, mPart);
		mPart.put(AttributeName.CONDITION, "FooWaste");
		rl.setActualAmount(15, mPart);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);

		assertEquals(xjdf.getXPathAttribute("//ResourceInfo/ResourceSet/Resource/AmountPool/PartAmount/@Waste", null), "15");
	}

	/**
	 *
	 */
	@Test
	public void testAncestorPool()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.appendAncestorPool().appendAncestor().setJobPartID("j2");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);

		assertNull(xjdf.getElement(ElementName.ANCESTORPOOL));
	}

	/**
	 *
	 */
	@Test
	public void testMediaComponentIn()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFMedia med = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFMedia m1 = (JDFMedia) med.addPartition(EnumPartIDKey.Location, "loc1");
		m1.setMediaType(EnumMediaType.Paper);
		m1.setWeight(42);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertTrue(xjdf.getXPathAttribute("ResourceSet/Resource/Component/@MediaRef", null).startsWith(med.getID()));
	}

	/**
	 *
	 */
	@Test
	public void testAmountsSimple()
	{
		final JDFNode n1 = JDFNode.createRoot();
		n1.setType(EnumType.ConventionalPrinting);
		final JDFResource c1 = n1.addResource(ElementName.COMPONENT, EnumUsage.Output);
		final JDFResourceLink rl1 = n1.getLink(c1, null);
		rl1.setAmount(100, new JDFAttributeMap(AttributeName.CONDITION, "Good"));
		rl1.setAmount(10, new JDFAttributeMap(AttributeName.CONDITION, "Waste"));
		final JDFToXJDF conv = new JDFToXJDF();
		conv.setRetainAll(true);

		final KElement xjdf = conv.convert(n1);
		final SetHelper sh = new XJDFHelper(xjdf).getSet(ElementName.COMPONENT, EnumUsage.Output);
		final ResourceHelper rh = sh.getPartition(0);
		final JDFAmountPool ap = rh.getAmountPool();
		assertEquals(2, ap.numChildElements(ElementName.PARTAMOUNT, null));

	}

	/**
	 *
	 */
	@Test
	public void testAmountPoolNoExplicitWaste()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource media = n.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(media, null);

		final JDFAttributeMap mPart = new JDFAttributeMap(AttributeName.CONDITION, "Good");
		mPart.put("SheetName", "S1");
		mPart.put(AttributeName.CONDITION, "Good");
		rl.setAmount(10, mPart);
		mPart.put(AttributeName.CONDITION, "Waste");
		rl.setAmount(15, mPart);

		final JDFToXJDF conv = new JDFToXJDF();
		conv.setExplicitWaste(false);
		conv.setRetainAll(true);
		final KElement xjdf = conv.convert(n);

		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource/AmountPool/PartAmount/Part[@Condition=\"Waste\"]/../@Amount", null), "15");
		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource/AmountPool/PartAmount/Part[@Condition=\"Good\"]/../@Amount", null), "10");
		conv.setRetainAll(true);
		final KElement xjdf2 = conv.convert(n);

		assertEquals(xjdf2.getXPathAttribute("ResourceSet/Resource/AmountPool/PartAmount/Part[@Condition=\"Waste\"]/../@Amount", null), "15");
		assertEquals(xjdf2.getXPathAttribute("ResourceSet/Resource/AmountPool/PartAmount/Part[@Condition=\"Good\"]/../@Amount", null), "10");

		rl.removeChild(ElementName.AMOUNTPOOL, null, 0);
		rl.setAmount(42);
		final KElement xjdf3 = conv.convert(n);
		// assertEquals(xjdf3.getXPathAttribute("ResourceSet/Resource/@Amount", null), "42");
		assertEquals(xjdf3.getXPathAttribute("ResourceSet/Resource/AmountPool/PartAmount/@Amount", null), "42");

	}

	/**
	 *
	 */
	@Test
	public void testMediaInline()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		xm.appendMedia().setMediaType(EnumMediaType.Plate);
		xm.setPlateType(EnumPlateType.Dummy);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertNotNull(xjdf.getXPathAttribute("ResourceSet[@Name=\"Media\"]/Resource/@ID", null));
		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource/ExposedMedia/@MediaRef", null), xjdf.getXPathAttribute("ResourceSet[@Name=\"Media\"]/Resource/@ID", null));
	}

	/**
	 *
	 */
	@Test
	public void testLocation()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFComponent c = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Input);
		c.setDescriptiveName("c1");
		c.setProductID("p1");
		c.appendLocationElement().setLocID("L1");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource/Part/@Location", null), "L1");
	}

	/**
	 *
	 */
	@Test
	public void testLayoutMark()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Imposition);
		final JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Input);
		final JDFLayout sheet = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFMarkObject markObject = sheet.appendMarkObject();
		markObject.setOrd(0);
		markObject.appendCIELABMeasuringField().setCenter(new JDFXYPair(2, 2));

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource/Layout/PlacedObject/@Ord", null), "0");
		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource/Layout/PlacedObject/MarkObject/CIELABMeasuringField/@Center", null), "2 2");
	}

	/**
	 *
	 */
	@Test
	public void testLayoutDescName()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Imposition);
		final JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Input);
		lo.setName("n1");
		lo.setDescriptiveName("d1");
		final JDFLayout sheet = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "s1");
		sheet.setDescriptiveName("s1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertEquals("n1", new XJDFHelper(xjdf).getSet(ElementName.LAYOUT, 0).getDescriptiveName());
		assertEquals("s1", new XJDFHelper(xjdf).getSet(ElementName.LAYOUT, 0).getPartition(0).getDescriptiveName());
	}

	/**
	 *
	 */
	@Test
	public void testExternalID()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Imposition);
		final JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Input);
		lo.setProductID("p1");
		final JDFLayout sheet = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "s1");
		sheet.setDescriptiveName("s1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertNull(new XJDFHelper(xjdf).getSet(ElementName.LAYOUT, 0).getAttribute(XJDFConstants.ExternalID));
		assertEquals("p1", new XJDFHelper(xjdf).getSet(ElementName.LAYOUT, 0).getPartition(0).getExternalID());
	}

	/**
	 *
	 */
	@Test
	public void testLayoutMedia()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Imposition);
		final JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Input);
		final JDFLayout sheet = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "s1");
		sheet.appendMedia().setMediaType(EnumMediaType.Plate);
		sheet.appendMedia().setMediaType(EnumMediaType.Paper);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertNotNull(xjdf.getXPathAttribute("ResourceSet/Resource/Layout/@PaperRef", null));
		assertNotNull(xjdf.getXPathAttribute("ResourceSet/Resource/Layout/@PlateRef", null));
	}

	/**
	 *
	 */
	@Test
	public void testLayoutContent()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Imposition);
		final JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Input);
		final JDFLayout sheet = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFContentObject markObject = sheet.appendContentObject();
		markObject.setOrd(0);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource/Layout/PlacedObject/@Ord", null), "0");
		assertNotNull(xjdf.getXPathElement("ResourceSet/Resource/Layout/PlacedObject/ContentObject"));
	}

	/**
	 *
	 */
	@Test
	public void testPlatePaperMedia()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFMedia med = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFMedia m1 = (JDFMedia) med.addPartition(EnumPartIDKey.Location, "loc1");
		m1.setMediaType(EnumMediaType.Paper);
		m1.setWeight(42);

		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		final JDFExposedMedia xms = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFMedia medPlate = (JDFMedia) n.addResource(ElementName.MEDIA, null);
		medPlate.setMediaType(EnumMediaType.Plate);
		xms.refMedia(medPlate);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertEquals(new XJDFHelper(xjdf).getSets(ElementName.MEDIA, null).size(), 2);
	}

	/**
	 *
	 */
	@Test
	public void testMediaComponentAmount()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFMedia med = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFMedia m1 = (JDFMedia) med.addPartition(EnumPartIDKey.Location, "loc1");
		m1.setMediaType(EnumMediaType.Paper);
		m1.setWeight(42);
		final JDFResourceLink mediaLink = n.ensureLink(med, EnumUsage.Input, null);
		final JDFAttributeMap amountMap = new JDFAttributeMap();
		amountMap.put(AttributeName.CONDITION, "Good");
		amountMap.put(AttributeName.LOCATION, "loc1");
		mediaLink.setAmount(1234, amountMap);
		amountMap.put(AttributeName.CONDITION, "Waste");
		mediaLink.setAmount(123, amountMap);

		JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Resource/AmountPool/PartAmount/@Amount", null), "1234");
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Resource/AmountPool/PartAmount/@Waste", null), "123");

		mediaLink.removeChild(ElementName.AMOUNTPOOL, null, 0);
		mediaLink.setAmount(5678, (JDFAttributeMap) null);
		conv = new JDFToXJDF();
		final KElement xjdfc = conv.convert(n);
		assertEquals(xjdfc.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Resource/AmountPool/PartAmount/@Amount", null), "5678");
		writeRoundTripX(xjdf, "CompAmount", EnumValidationLevel.Incomplete);
	}

	/**
	 *
	 */
	@Test
	public void testMediaGrade()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFMedia med = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		med.setMediaType(EnumMediaType.Paper);
		med.setWeight(42);
		med.setGrade(1);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdfc = conv.convert(n);
		assertEquals(xjdfc.getXPathAttribute("ResourceSet[@Name=\"Media\"]/Resource/Media/@ISOPaperSubstrate", null), "PS1");
		assertNull(xjdfc.getXPathAttribute("ResourceSet[@Name=\"Media\"]/Resource/Media/@Grade", null));
		writeRoundTripX(xjdfc, "MediaGrade", EnumValidationLevel.Incomplete);
	}

	/**
	 *
	 */
	@Test
	public void testMediaPlatePaper()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFMedia med = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		med.setMediaType(EnumMediaType.Paper);
		med.setWeight(42);
		med.setGrade(1);
		final JDFMedia med2 = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		med2.setMediaType(EnumMediaType.Plate);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertNotNull(new XJDFHelper(xjdf).getSet(ElementName.MEDIA, 1));
	}

	/**
	 *
	 */
	@Test
	public void testMediaGrain()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFMedia med = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		med.setMediaType(EnumMediaType.Paper);
		med.setWeight(42);
		med.setGrade(1);
		med.setDimensionCM(29, 42);
		med.setGrainDirection(EnumGrainDirection.LongEdge);
		med.setFluteDirection(EnumFluteDirection.ShortEdge);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdfc = conv.convert(n);
		final JDFMedia m = (JDFMedia) xjdfc.getXPathElement("ResourceSet[@Name=\"Media\"]/Resource/Media");
		assertEquals(EnumGrainDirection.YDirection, m.getGrainDirection());
		assertEquals(EnumFluteDirection.XDirection, m.getFluteDirection());
		writeRoundTripX(xjdfc, "MediaFlute", EnumValidationLevel.Incomplete);
	}

	/**
	 *
	 */
	@Test
	public void testMediaUMT()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFMedia med = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		med.setMediaType(EnumMediaType.Paper);
		med.setUserMediaType("mtd");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdfc = conv.convert(n);
		assertEquals(xjdfc.getXPathAttribute("ResourceSet[@Name=\"Media\"]/Resource/Media/@MediaTypeDetails", null), "mtd");
		assertNull(xjdfc.getXPathAttribute("ResourceSet[@Name=\"Media\"]/Resource/Media/@UserMediaType", null));
		writeRoundTripX(xjdfc, "MediaType", EnumValidationLevel.Incomplete);
	}

	/**
	 *
	 */
	@Test
	public void testPartAmountPartitions()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFMedia med = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFMedia m1 = (JDFMedia) med.addPartition(EnumPartIDKey.Location, "loc1");
		m1.setMediaType(EnumMediaType.Paper);
		m1.setWeight(42);
		final JDFResourceLink ml = n.getLink(med, null);
		final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.Location.getName(), "loc1");
		ml.setPartMap(map);

		map.put(EnumPartIDKey.Separation, "Cyan");
		map.put(EnumPartIDKey.Condition, "Good");
		ml.setAmount(42, map);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertNotNull(xjdf.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Resource/Part/@Location", null));
		assertNull(xjdf.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Resource/AmountPool/PartAmount/Part/@Location", null));
		assertNotNull(xjdf.getXPathAttribute("ResourceSet[@Name=\"Component\"]/Resource/AmountPool/PartAmount/Part/@Separation", null));
	}

	/**
	 *
	 */
	@Test
	public void testMultiNode1()
	{
		JDFElement.setLongID(false);
		final JDFNode product = new JDFDoc(ElementName.JDF).getJDFRoot();
		product.setType(EnumType.Product);
		final JDFLayoutIntent loi = (JDFLayoutIntent) product.addResource(ElementName.LAYOUTINTENT, EnumUsage.Input);
		loi.setSides(EnumSides.OneSided);
		final JDFNode plateset = product.addCombined(new VString("Imposition Interpreting Rendering ImageSetting", " "));
		final JDFInterpretingParams ip = (JDFInterpretingParams) plateset.addResource(ElementName.INTERPRETINGPARAMS, EnumUsage.Input);
		ip.setPrintQuality(org.cip4.jdflib.auto.JDFAutoInterpretingParams.EnumPrintQuality.Normal);
		final JDFRunList ruli = (JDFRunList) plateset.addResource(ElementName.RUNLIST, EnumUsage.Input);
		ruli.setFileURL("file:///foo.pdf");
		plateset.addResource(ElementName.MEDIA, EnumUsage.Input);
		plateset.addResource(ElementName.LAYOUT, EnumUsage.Input);
		final JDFExposedMedia xm = (JDFExposedMedia) plateset.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output);
		final JDFNode cp = product.addCombined(new VString("InkZoneCalculation ConventionalPrinting", " "));
		cp.ensureLink(xm, EnumUsage.Input, null);
		cp.addResource(ElementName.MEDIA, EnumUsage.Input);
		product.write2File(sm_dirTestDataTemp + "3files.jdf");

		final JDFToXJDF conv = new JDFToXJDF();
		conv.setWantProduct(true);
		conv.saveZip(sm_dirTestDataTemp + "3files.xjdf.zip", product, true);

		final JDFJMF jmf = new JMFBuilder().buildSubmitQueueEntry("http://foo.bar", "xjdfs");
		conv.writeStream(FileUtil.getBufferedOutputStream(new File(sm_dirTestDataTemp + "3files.xjmf.zip")), product, jmf);
		assertTrue(product.isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 *
	 */
	@Test
	public void testHoleMaking()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.HoleMaking);
		final JDFHoleMakingParams hp = (JDFHoleMakingParams) node.appendMatchingResource(ElementName.HOLEMAKINGPARAMS, EnumUsage.Input);
		hp.setCenter(new JDFXYPair(3, 4));
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(node);
		final ResourceHelper ph = new XJDFHelper(xjdf).getPartition(ElementName.HOLEMAKINGPARAMS, 0, 0);
		final KElement holepattern = ph.getResource().getElement("HolePattern");
		assertNotNull(holepattern);
		assertEquals(holepattern.getAttribute(AttributeName.CENTER), "3 4");
	}

	/**
	 *
	 */
	@Test
	public void testIdentical()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.Labeling);
		final JDFLabelingParams lp = (JDFLabelingParams) node.appendMatchingResource(ElementName.LABELINGPARAMS, EnumUsage.Input);
		final JDFLabelingParams lp1 = (JDFLabelingParams) lp.addPartition(EnumPartIDKey.SignatureName, "sig1").addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFLabelingParams lp2 = (JDFLabelingParams) lp.addPartition(EnumPartIDKey.SignatureName, "sig2").addPartition(EnumPartIDKey.SheetName, "s2");
		lp2.setIdentical(lp1);
		lp1.setApplication("Glue");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(node);
		final ResourceHelper ph = new XJDFHelper(xjdf).getPartition(ElementName.LABELINGPARAMS, 0, 0);
		final VJDFAttributeMap vMap = new VJDFAttributeMap(new JDFAttributeMap(AttributeName.SHEETNAME, "s1"));
		vMap.add(new JDFAttributeMap(AttributeName.SHEETNAME, "s2"));
		assertEquals(vMap, ph.getPartMapVector());
		assertNull(ph.getResource().getElement(ElementName.IDENTICAL));

	}

	/**
	 *
	 */
	@Test
	public void testInsertingIntent()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.Product);
		final JDFInsertingIntent insi = (JDFInsertingIntent) node.addResource(ElementName.INSERTINGINTENT, EnumUsage.Input);
		final JDFInsert ins = insi.appendInsertList().appendInsert();
		ins.appendMethod().setActual(EnumMethod.BindIn);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(node);

		assertNotNull(xjdf.getXPathElement("ProductList/Product/Intent[@Name=\"AssemblingIntent\"]/AssemblingIntent/BindIn"));
	}

	/**
	 * @return
	 *
	 */
	@Test
	public void testGlueLine()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setJobID("glue");
		node.setType(EnumType.Gluing);
		final JDFComponent c1 = (JDFComponent) node.addResource(ElementName.COMPONENT, EnumUsage.Input);
		final JDFComponent c2 = (JDFComponent) node.addResource(ElementName.COMPONENT, EnumUsage.Output);
		c1.setComponentType(EnumComponentType.PartialProduct, null);
		c1.setDimensions(new JDFXYPair(3, 5));
		c2.setComponentType(EnumComponentType.PartialProduct, null);
		c2.setDimensions(new JDFXYPair(3, 5));
		final JDFGlue glue = (JDFGlue) node.addResource(ElementName.GLUINGPARAMS, EnumUsage.Input).appendElement(ElementName.GLUE);
		glue.setWorkingDirection(EnumWorkingDirection.Bottom);
		final JDFGlueLine gl = (JDFGlueLine) glue.appendElement(ElementName.GLUELINE);
		gl.setGlueBrand("gb1");
		gl.setAreaGlue(true);
		final JDFGlue glue2 = (JDFGlue) node.getResource(ElementName.GLUINGPARAMS, EnumUsage.Input, 0).appendElement(ElementName.GLUE);
		final JDFGlueApplication ga = glue2.appendGlueApplication();
		ga.setGluingTechnique(EnumGluingTechnique.SpineGluing);
		ga.copyElement(gl, null);

		writeRoundTrip(node, "glue");

	}

	/**
	 * @return
	 *
	 */
	@Test
	public void testHoleLine()
	{
		_testHoleLine();
	}

	public KElement _testHoleLine()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.HoleMaking);
		final JDFHoleMakingParams hp = (JDFHoleMakingParams) node.appendMatchingResource(ElementName.HOLEMAKINGPARAMS, EnumUsage.Input);
		final JDFHoleLine holeline = hp.appendHoleLine();
		holeline.setPitch(42);
		holeline.appendHole().setCenter(new JDFXYPair(5, 6));
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(node);
		final ResourceHelper ph = new XJDFHelper(xjdf).getPartition(ElementName.HOLEMAKINGPARAMS, 0, 0);
		final KElement holepattern = ph.getResource().getElement("HolePattern");
		assertNotNull(holepattern);
		assertEquals(holepattern.getAttribute(AttributeName.CENTER), "5 6");
		assertEquals(holepattern.getAttribute(AttributeName.PITCH), "42");
		return xjdf;
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testCoilBindingTypes()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.CoilBinding);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(node);
		assertEquals(XJDFConstants.LooseBinding, xjdf.getAttribute(AttributeName.TYPES));
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testCoilBindingParams()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.CoilBinding);
		final JDFCoilBindingParams cbp = (JDFCoilBindingParams) node.getCreateResource(ElementName.COILBINDINGPARAMS, EnumUsage.Input, null);
		cbp.setColor(EnumNamedColor.Red);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(node);
		assertEquals(XJDFConstants.LooseBinding, xjdf.getAttribute(AttributeName.TYPES));
		assertNull(new XJDFHelper(xjdf).getSet(ElementName.COILBINDINGPARAMS, 0));
		assertNotNull(new XJDFHelper(xjdf).getSet(XJDFConstants.LooseBindingParams, 0));
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testCoilBindingMisc()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.CoilBinding);
		final JDFCoilBindingParams cbp = (JDFCoilBindingParams) node.getCreateResource(ElementName.COILBINDINGPARAMS, EnumUsage.Input, null);
		cbp.setColor(EnumNamedColor.Red);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(node);
		assertEquals(XJDFConstants.LooseBinding, xjdf.getAttribute(AttributeName.TYPES));
		assertNull(new XJDFHelper(xjdf).getSet(ElementName.COILBINDINGPARAMS, 0));
		assertNotNull(new XJDFHelper(xjdf).getSet(XJDFConstants.LooseBindingParams, 0));
		final SetHelper set = new XJDFHelper(xjdf).getSet(ElementName.MISCCONSUMABLE, EnumUsage.Input, "Spine");
		assertNotNull(set);
		assertEquals("Red", set.getPartition(0).getResource().getAttribute(AttributeName.COLOR));
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testChannellBindingMisc()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.ChannelBinding);
		final JDFChannelBindingParams cbp = (JDFChannelBindingParams) node.getCreateResource(ElementName.CHANNELBINDINGPARAMS, EnumUsage.Input, null);
		cbp.setClampColor(EnumNamedColor.Red);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(node);
		assertEquals(XJDFConstants.LooseBinding, xjdf.getAttribute(AttributeName.TYPES));
		assertNull(new XJDFHelper(xjdf).getSet(ElementName.CHANNELBINDINGPARAMS, 0));
		assertNotNull(new XJDFHelper(xjdf).getSet(XJDFConstants.LooseBindingParams, 0));
		final SetHelper set = new XJDFHelper(xjdf).getSet(ElementName.MISCCONSUMABLE, EnumUsage.Input, "Spine");
		assertNotNull(set);
		assertEquals("Red", set.getPartition(0).getResource().getAttribute(AttributeName.COLOR));
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testCoilBindingDetails()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.CoilBinding);
		final JDFCoilBindingParams cbp = (JDFCoilBindingParams) node.getCreateResource(ElementName.COILBINDINGPARAMS, EnumUsage.Input, null);
		cbp.setDiameter(42);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(node);
		assertEquals(XJDFConstants.LooseBinding, xjdf.getAttribute(AttributeName.TYPES));
		assertNull(new XJDFHelper(xjdf).getSet(ElementName.COILBINDINGPARAMS, 0));
		assertNotNull(new XJDFHelper(xjdf).getSet(XJDFConstants.LooseBindingParams, 0));
		assertEquals("42", new XJDFHelper(xjdf).getSet(XJDFConstants.LooseBindingParams, 0).getPartition(0).getXPathValue("LooseBindingParams/CoilBindingDetails/@Diameter"));
	}

	/**
	 *
	 * @return
	 */
	@Test
	public void testDeprecatedTypes()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.Buffer);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(node);
		assertEquals(EnumType.ManualLabor.getName(), xjdf.getAttribute(AttributeName.TYPES));
	}

	/**
	 *
	 */
	@Test
	public void testgetXJDFs1()
	{
		final JDFNode product = prepareProduct();

		product.write2File(sm_dirTestDataTemp + "getXJDFS.jdf");
		final JDFToXJDF conv = new JDFToXJDF();
		final Vector<XJDFHelper> v = conv.getXJDFs(product);
		assertEquals(v.size(), 3);
		for (final XJDFHelper h : v)
		{
			h.writeToFile(sm_dirTestDataTemp + "getXJDFS." + h.getJobPartID() + ".xjdf");
		}
	}

	static JDFNode prepareProduct()
	{
		JDFElement.setLongID(false);
		final JDFNode product = new JDFDoc(ElementName.JDF).getJDFRoot();
		product.setType(EnumType.Product);
		product.addResource(ElementName.COMPONENT, EnumUsage.Output);
		final JDFLayoutIntent loi = (JDFLayoutIntent) product.addResource(ElementName.LAYOUTINTENT, EnumUsage.Input);
		loi.setSides(EnumSides.OneSided);
		final JDFNode product2 = product.addProduct();
		product2.addResource(ElementName.COMPONENT, EnumUsage.Output).setDescriptiveName("Cover");
		product.linkOutputs(product2);
		final JDFNode product3 = product.addProduct();
		product3.addResource(ElementName.COMPONENT, EnumUsage.Output).setDescriptiveName("Body");
		product.linkOutputs(product3);

		final JDFNode plateset = product.addCombined(new VString("Impositioning Interpreting Rendering ImageSetting", " "));
		final JDFInterpretingParams ip = (JDFInterpretingParams) plateset.addResource(ElementName.INTERPRETINGPARAMS, EnumUsage.Input);
		ip.setPolarity(EnumPolarity.Negative);
		final JDFRunList ruli = (JDFRunList) plateset.addResource(ElementName.RUNLIST, EnumUsage.Input);
		ruli.setFileURL("file:///foo.pdf");
		final JDFMedia plate = (JDFMedia) plateset.addResource(ElementName.MEDIA, EnumUsage.Input);
		plate.setMediaType(EnumMediaType.Plate);
		plate.setProductID("p1");

		plateset.addResource(ElementName.LAYOUT, EnumUsage.Input);
		final JDFExposedMedia xm = (JDFExposedMedia) plateset.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output);
		final JDFNode cp = product.addCombined(new VString("InkZoneCalculation ConventionalPrinting", " "));
		cp.ensureLink(xm, EnumUsage.Input, null);
		final JDFMedia paper = (JDFMedia) cp.addResource(ElementName.MEDIA, EnumUsage.Input);
		paper.setProductID("p2");
		paper.setMediaType(EnumMediaType.Paper);
		return product;
	}

	/**
	 *
	 */
	@Test
	public void testgetCombinedXJDFTypes()
	{
		final JDFNode product = prepareProduct();

		final JDFToXJDF conv = new JDFToXJDF();
		final XJDFHelper h = conv.getCombined(product);
		assertEquals(new VString("Product Impositioning Interpreting Rendering ImageSetting InkZoneCalculation ConventionalPrinting", null), h.getTypes());
	}

	/**
	 *
	 */
	@Test
	public void testgetCombinedXJDFSets()
	{
		final JDFNode product = prepareProduct();

		final JDFToXJDF conv = new JDFToXJDF();
		final XJDFHelper h = conv.getCombined(product);
		assertEquals(1, h.getSets(ElementName.EXPOSEDMEDIA, null).size());
	}

	/**
	 *
	 */
	@Test
	public void testgetCombinedXJDF()
	{
		final JDFNode product = prepareProduct();

		final JDFToXJDF conv = new JDFToXJDF();
		final XJDFHelper h = conv.getCombined(product);
		h.writeToFile(sm_dirTestDataTemp + "combined.xjdf");
	}

	/**
	 *
	 */
	@Test
	public void testgetCombinedComplex()
	{
		final JDFNode product = JDFNode.parseFile(sm_dirTestData + "SampleFiles/MISPrepress-ICS-Complex.jdf");

		final JDFToXJDF conv = new JDFToXJDF();
		final XJDFHelper h = conv.getCombined(product);
		h.writeToFile(sm_dirTestDataTemp + "prepress.xjdf");
	}

	/**
	 *
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	public void tearDown() throws Exception
	{
		XJMFTypeMap.shutDown();
		super.tearDown();
	}

}
