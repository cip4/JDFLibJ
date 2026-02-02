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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoApprovalDetails.EnumApprovalState;
import org.cip4.jdflib.auto.JDFAutoAssembly.EnumOrder;
import org.cip4.jdflib.auto.JDFAutoComChannel.EnumChannelType;
import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoCut.EnumWorkingDirection;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoDigitalPrintingParams.EnumPageDelivery;
import org.cip4.jdflib.auto.JDFAutoExposedMedia.EnumPlateType;
import org.cip4.jdflib.auto.JDFAutoGlueApplication.EnumGluingTechnique;
import org.cip4.jdflib.auto.JDFAutoInsertingParams.EnumMethod;
import org.cip4.jdflib.auto.JDFAutoInterpretingParams.EPrintQuality;
import org.cip4.jdflib.auto.JDFAutoInterpretingParams.EnumPrintQuality;
import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumCostType;
import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumDeviceOperationMode;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumFluteDirection;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumGrainDirection;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumPolarity;
import org.cip4.jdflib.auto.JDFAutoNotification.EnumClass;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.ESides;
import org.cip4.jdflib.core.JDFElement.EnumNamedColor;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumSides;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.datatypes.JDFTransferFunction;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.BaseXJDFHelper;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFEnums.eDeviceStatus;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.JDFToXJDF;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JMFBuilder;
import org.cip4.jdflib.node.JDFAncestor;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFCoilBindingParams;
import org.cip4.jdflib.resource.JDFCuttingParams;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFHoleLine;
import org.cip4.jdflib.resource.JDFInsert;
import org.cip4.jdflib.resource.JDFInterpretingParams;
import org.cip4.jdflib.resource.JDFLabelingParams;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFModuleStatus;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.intent.JDFArtDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFBindingIntent;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFDropIntent;
import org.cip4.jdflib.resource.intent.JDFDropItemIntent;
import org.cip4.jdflib.resource.intent.JDFInsertingIntent;
import org.cip4.jdflib.resource.intent.JDFLayoutIntent;
import org.cip4.jdflib.resource.intent.JDFMediaIntent;
import org.cip4.jdflib.resource.intent.JDFPackingIntent;
import org.cip4.jdflib.resource.intent.JDFScreeningIntent;
import org.cip4.jdflib.resource.process.JDFApprovalDetails;
import org.cip4.jdflib.resource.process.JDFApprovalSuccess;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFAssemblySection;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComChannel;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFContact.EnumContactType;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFCutBlock;
import org.cip4.jdflib.resource.process.JDFDeliveryParams;
import org.cip4.jdflib.resource.process.JDFDieLayout;
import org.cip4.jdflib.resource.process.JDFDigitalPrintingParams;
import org.cip4.jdflib.resource.process.JDFDropItem;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMISDetails;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPageData;
import org.cip4.jdflib.resource.process.JDFPerson;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFSheetOptimizingParams;
import org.cip4.jdflib.resource.process.JDFTransferCurve;
import org.cip4.jdflib.resource.process.JDFTransferCurvePool;
import org.cip4.jdflib.resource.process.JDFTransferCurveSet;
import org.cip4.jdflib.resource.process.JDFUsageCounter;
import org.cip4.jdflib.resource.process.postpress.JDFChannelBindingParams;
import org.cip4.jdflib.resource.process.postpress.JDFFoldingParams;
import org.cip4.jdflib.resource.process.postpress.JDFGlue;
import org.cip4.jdflib.resource.process.postpress.JDFGlueApplication;
import org.cip4.jdflib.resource.process.postpress.JDFGlueLine;
import org.cip4.jdflib.resource.process.postpress.JDFHoleMakingParams;
import org.cip4.jdflib.resource.process.press.JDFPrintCondition;
import org.cip4.jdflib.resource.process.press.JDFPrintCondition.ePrintQuality;
import org.cip4.jdflib.span.JDFSpanScreeningType.EnumSpanScreeningType;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.MyPair;
import org.junit.jupiter.api.Test;

/**
 * @author rainer prosi
 */
public class JDFToXJDFConverterTest extends JDFTestCaseBase
{

	/**
	 *
	 *
	 */
	@Test
	void testExtendedAddress()
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
	void testCustomerInfoContact()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		final JDFNode n = createBaseProductNode();
		final JDFCustomerInfo ci = n.appendCustomerInfo();
		ci.setDescriptiveName("c");
		ci.appendContact(EnumContactType.Customer).setDescriptiveName("cust");
		ci.appendContact(EnumContactType.Delivery).setDescriptiveName("del");
		final KElement x = conv.convert(n);
		final XJDFHelper h = XJDFHelper.getHelper(x);
		assertNotNull(h.getSet(ElementName.CONTACT, null));
	}

	/**
	 *
	 *
	 */
	@Test
	void testPrintQuality()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.Interpreting);
		final JDFInterpretingParams ip = (JDFInterpretingParams) node.appendMatchingResource(ElementName.INTERPRETINGPARAMS, EnumUsage.Input);
		ip.setPrintQuality(EnumPrintQuality.High);
		final KElement x = conv.convert(node);
		final XJDFHelper h = XJDFHelper.getHelper(x);
		final SetHelper set = h.getSet(ElementName.PRINTCONDITION, EnumUsage.Input);
		assertNotNull(set);
		final JDFPrintCondition pc = (JDFPrintCondition) set.getResource(0).getResource();
		assertEquals(ePrintQuality.High, pc.getPrintQuality());
	}

	/**
	 *
	 *
	 */
	@Test
	void testForeignRef()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		final JDFNode n = createBaseProductNode();
		final JDFCustomerInfo ci = n.appendCustomerInfo();
		final JDFNodeInfo ni = n.getCreateNodeInfo();
		final KElement foo = ni.appendElement("foo:foo", "www.foo.com");
		foo.appendElement("CustomerInfoRef").setAttribute(AttributeName.RREF, ci.getID());
		final KElement x = conv.convert(n);
		final XJDFHelper h = XJDFHelper.getHelper(x);
		assertNotNull(h.getSet(ElementName.NODEINFO, null));
	}

	/**
	 *
	 *
	 */
	// no test
	void testSpeed()
	{
		final JDFToXJDF conv = new JDFToXJDF();

		final JDFNode n = JDFNode.parseFile("C:\\data\\265276-PD\\digital.main.jdf");
		final KElement x = conv.convert(n);
		final XJDFHelper h = XJDFHelper.getHelper(x);
		h.writeToFile("C:\\data\\265276-PD\\digital.main.xjdf");
	}

	/**
	 * @throws IOException
	 * @throws JDF_AccessException
	 * @throws StorageAccessException
	 */
	@Test
	void testparseRef()
	{
		final long ct = System.currentTimeMillis();
		final String jobID = "Varnish" + ct;
		final XJDFHelper xjdfHelper = new XJDFHelper(getDefaultXJDFVersion(), jobID);
		xjdfHelper.setTypes(new VString("ConventionalPrinting"));

		final SetHelper pm = xjdfHelper.getCreateSet(ElementName.MEDIA, null);

		final String v1 = "varnish1";
		final ResourceHelper mediaPartV = pm.getCreatePartition(new JDFAttributeMap(AttributeName.SEPARATION, v1), true);
		final JDFMedia plateMediaV = (JDFMedia) mediaPartV.getResource();
		plateMediaV.setDimensionCM(new JDFXYPair(130, 80));
		plateMediaV.setMediaType(EnumMediaType.Blanket);

		final ResourceHelper mediaPart = pm.getCreatePartition(null, true);

		final JDFMedia plateMedia = (JDFMedia) mediaPart.getResource();
		plateMedia.setDimensionCM(new JDFXYPair(130, 80));
		plateMedia.setMediaType(EnumMediaType.Plate);

		final SetHelper xmH = xjdfHelper.getCreateSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input);

		final JDFAttributeMap surfaceMap = new JDFAttributeMap(AttributeName.SHEETNAME, "S1");
		surfaceMap.put(AttributeName.SIDE, "Front");

		final VJDFAttributeMap front1 = new VJDFAttributeMap(surfaceMap);
		final List<String> cmykSeparations = new VString("Black");
		front1.extendMap(AttributeName.SEPARATION, cmykSeparations);

		final List<String> allSeparations = new VString("Black");
		allSeparations.add(v1);
		final VJDFAttributeMap allColors = new VJDFAttributeMap(new JDFAttributeMap());
		allColors.extendMap(AttributeName.SEPARATION, allSeparations);

		final VJDFAttributeMap varnishes = new VJDFAttributeMap(surfaceMap);
		varnishes.extendMap(AttributeName.SEPARATION, new VString(v1, null));

		final Vector<ResourceHelper> vXM = xmH.getCreatePartitions(allColors, true);
		for (final ResourceHelper xm : vXM)
		{
			final JDFExposedMedia xmr = (JDFExposedMedia) xm.getResource();
			mediaPart.ensureReference(xmr, XJDFConstants.MediaRef);
		}
		final JDFExposedMedia xmv = (JDFExposedMedia) xmH.getPartition(varnishes.get(0)).getResource();
		mediaPartV.ensureReference(xmv, XJDFConstants.MediaRef);

		cleanSnippets(xjdfHelper);
		writeRoundTripX(xjdfHelper.getRoot(), "xmsparse", EnumValidationLevel.Incomplete);
	}

	/**
	 *
	 *
	 */
	@Test
	void testToString()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		assertNotNull(conv.toString());
	}

	/**
	 *
	 *
	 */
	@Test
	void testCustomerInfoContactPersonAddress()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		final JDFNode n = createBaseProductNode();
		final JDFCustomerInfo ci = n.appendCustomerInfo();
		ci.setDescriptiveName("c");
		final JDFContact c1 = ci.appendContact(EnumContactType.Customer);
		final JDFPerson p1 = c1.appendPerson();
		p1.setFamilyName("F1");
		p1.appendComChannel(EnumChannelType.Phone, "1234");
		p1.appendAddress().setCity("city");
		c1.setDescriptiveName("cust");
		ci.appendContact(EnumContactType.Delivery).setDescriptiveName("del");
		final KElement x = conv.convert(n);
		final XJDFHelper h = XJDFHelper.getHelper(x);
		final SetHelper set = h.getSet(ElementName.CONTACT, null);
		assertNotNull(set);
	}

	/**
	 *
	 *
	 */
	@Test
	void testCustomerInfo2Contact()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		final JDFNode n = createBaseProductNode();
		for (int i = 0; i < 2; i++)
		{
			final JDFNode p = n.addProduct();
			final JDFComponent comp = (JDFComponent) p.addResource(ElementName.COMPONENT, EnumUsage.Output);
			comp.setComponentType(EnumComponentType.FinalProduct, null);

			final JDFCustomerInfo ci = p.appendCustomerInfo();
			ci.setDescriptiveName("c" + i);
			ci.appendContact(EnumContactType.Customer).setDescriptiveName("cust" + i);
			ci.appendContact(EnumContactType.Delivery).setDescriptiveName("del" + i);
		}
		final KElement x = conv.convert(n);
		final XJDFHelper h = XJDFHelper.getHelper(x);
		final SetHelper set = h.getSet(ElementName.CONTACT, null);
		assertNotNull(set);
		final VJDFAttributeMap pv = set.getPartMapVector();
		pv.unify();
		assertEquals(4, pv.size());
	}

	/**
	 *
	 *
	 */
	@Test
	void testRefMediaFromInline()
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
	void testDeliveryIntent()
	{
		_testDeliveryIntent();
	}

	/**
	 *
	 */
	@Test
	void testDependent()
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
	void testDependentMulti()
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
	void testTypesMulti()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setJobPartID("p");
		n.setType(EnumType.ProcessGroup);
		final VString typs = new VString("Cutting Folding Folding Cutting");
		n.setTypes(typs);

		final JDFToXJDF conv = new JDFToXJDF();
		conv.setSingleNode(true);
		final KElement xjdf = conv.convert(n);

		assertEquals(typs, new XJDFHelper(xjdf).getTypes());

	}

	/**
	 *
	 */
	@Test
	void testCPI()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setJobPartID("p");
		n.setType(EnumType.ProcessGroup);
		n.addTypes(EnumType.ConventionalPrinting);
		n.addTypes(EnumType.Folding);
		final JDFResource r0 = n.addResource(ElementName.COMPONENT, EnumUsage.Input);
		r0.setDescriptiveName("input foobar");
		final JDFResourceLink rl0 = n.ensureLink(r0, null, null);
		rl0.setCombinedProcessIndex(0);
		rl0.setAmount(40);
		final JDFResource r1 = n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		r1.setDescriptiveName("exchange foobar");
		final JDFResourceLink rl2 = n.ensureLink(r1, EnumUsage.Input, null);
		rl2.setCombinedProcessIndex(1);
		rl2.setAmount(20);
		final JDFResourceLink rl1 = n.ensureLink(r1, EnumUsage.Output, null);
		rl1.setCombinedProcessIndex(0);
		rl1.setAmount(30);
		final JDFResource r2 = n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		r2.setDescriptiveName("output foobar");
		final JDFResourceLink rl3 = n.ensureLink(r2, null, null);
		rl3.setCombinedProcessIndex(1);
		rl3.setAmount(10);

		final JDFToXJDF conv = new JDFToXJDF();
		conv.setSingleNode(true);
		final KElement xjdf = conv.convert(n);
		final XJDFHelper h = XJDFHelper.getHelper(xjdf);
		assertNotNull(h.getSet(ElementName.COMPONENT, EnumUsage.Output, null, new JDFIntegerList(0)));
		assertNotNull(h.getSet(ElementName.COMPONENT, EnumUsage.Output, null, new JDFIntegerList(1)));
		assertNotNull(h.getSet(ElementName.COMPONENT, EnumUsage.Input, null, new JDFIntegerList(0)));
		assertNull(h.getSet(ElementName.COMPONENT, EnumUsage.Input, null, new JDFIntegerList(1)));

	}

	/**
	 *
	 */
	@Test
	void testDependentMultiPart()
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
		final JDFDropIntent dropIntent = di.appendDropIntent();
		final JDFDate date = new JDFDate();
		dropIntent.appendRequired().setActual(date);
		final JDFDropItemIntent dropItemIntent = dropIntent.appendDropItemIntent();
		dropItemIntent.refComponent(c);
		dropItemIntent.setAmount(42);
		final JDFDropItemIntent dropItemIntent2 = dropIntent.appendDropItemIntent();
		dropItemIntent2.refComponent(c);
		dropItemIntent2.setAmount(63);
		final XJDF20 xjdf20 = new XJDF20();
		xjdf20.setSingleNode(true);
		final KElement xjdf = xjdf20.makeNewJDF(nP, null);
		xjdf.write2File(sm_dirTestDataTemp + "delTest2.xjdf");
		assertNotNull(xjdf);
		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource/DeliveryParams/DropItem/@Amount", null), "42");
		assertEquals(date.getDateTimeISO(), xjdf.getXPathAttribute("ResourceSet/Resource/DeliveryParams/@Required", null));
		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource/DeliveryParams/DropItem[2]/@Amount", null), "63");
		final XJDFHelper h = new XJDFHelper(xjdf);
		final SetHelper dh = h.getSet(ElementName.DELIVERYPARAMS, 0);
		final ResourceHelper drh = dh.getResource(0);
		assertEquals("DROP_0", drh.getPartKey(AttributeName.DROPID));
		assertNull(drh.getResourceAttribute(AttributeName.DROPID));
		return xjdf;
	}

	/**
	 * @return
	 */
	@Test
	void testSpanIntent()
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
	 * @return
	 */
	@Test
	void testConvertingConfig()
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
	 * @return
	 * @throws DataFormatException
	 */
	@Test
	void testTCPPartitioned() throws DataFormatException
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
	 * @return
	 * @throws DataFormatException
	 */
	@Test
	void testStrippingMultiBS() throws DataFormatException
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Stripping);

		final JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Output);
		lo.addPartition(EnumPartIDKey.SheetName, "s1");
		lo.addPartition(EnumPartIDKey.SheetName, "s2");
		lo.addPartition(EnumPartIDKey.SheetName, "s3");

		final JDFBinderySignature bs = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final JDFBinderySignature bs1 = (JDFBinderySignature) bs.addPartition(EnumPartIDKey.BinderySignatureName, "bs1");
		bs1.appendSignatureCell().setFrontPages(JDFIntegerList.createIntegerList("1 2"));
		final JDFBinderySignature bs2 = (JDFBinderySignature) bs.addPartition(EnumPartIDKey.BinderySignatureName, "bs2");
		bs2.appendSignatureCell().setFrontPages(JDFIntegerList.createIntegerList("1 2 3 4"));

		final JDFStrippingParams sp = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input);
		final JDFStrippingParams sp1 = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.SheetName, "s1");
		sp1.refBinderySignature(bs1);
		final JDFStrippingParams sp2 = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.SheetName, "s2");
		sp2.refBinderySignature(bs2);
		final JDFStrippingParams sp3 = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.SheetName, "s3");
		sp3.refBinderySignature(bs2);

		final JDFToXJDF xjdf20 = new JDFToXJDF();
		xjdf20.setSingleNode(true);
		final KElement xjdf = xjdf20.makeNewJDF(n, null);
		xjdf.write2File(sm_dirTestDataTemp + "bs.xjdf");
		assertNotNull(xjdf);
		assertEquals(3, XJDFHelper.getHelper(xjdf).getSet(ElementName.BINDERYSIGNATURE, 0).getPartitionList().size());
	}

	/**
	 * @return
	 * @throws DataFormatException
	 */
	@Test
	void testStrippingMultiBSPos() throws DataFormatException
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Stripping);

		final JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Output);
		lo.addPartition(EnumPartIDKey.SheetName, "s1");
		lo.addPartition(EnumPartIDKey.SheetName, "s2");
		lo.addPartition(EnumPartIDKey.SheetName, "s3");

		final JDFBinderySignature bs = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final JDFBinderySignature bs1 = (JDFBinderySignature) bs.addPartition(EnumPartIDKey.BinderySignatureName, "bs1");
		bs1.appendSignatureCell().setFrontPages(JDFIntegerList.createIntegerList("1 2"));
		final JDFBinderySignature bs2 = (JDFBinderySignature) bs.addPartition(EnumPartIDKey.BinderySignatureName, "bs2");
		bs2.appendSignatureCell().setFrontPages(JDFIntegerList.createIntegerList("1 2 3 4"));

		final JDFStrippingParams sp = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input);
		final JDFStrippingParams sp1 = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.SheetName, "s1");
		sp1.refBinderySignature(bs1);
		sp1.appendPosition();
		sp1.appendPosition();
		final JDFStrippingParams sp2 = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.SheetName, "s2");
		sp2.refBinderySignature(bs2);
		sp2.appendPosition();
		sp2.appendPosition();
		final JDFStrippingParams sp3 = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.SheetName, "s3");
		sp3.refBinderySignature(bs2);
		sp3.appendPosition();
		sp3.appendPosition();

		final JDFToXJDF xjdf20 = new JDFToXJDF();
		xjdf20.setSingleNode(true);
		final KElement xjdf = xjdf20.makeNewJDF(n, null);
		xjdf.write2File(sm_dirTestDataTemp + "bs.xjdf");
		assertNotNull(xjdf);
		assertEquals(3, XJDFHelper.getHelper(xjdf).getSet(ElementName.BINDERYSIGNATURE, 0).getPartitionList().size());
		assertEquals(6, xjdf.getChildArrayByClass(JDFPosition.class, true, 0).size());
		assertEquals(2,
				xjdf.getChildrenByTagName(ElementName.POSITION, null, new JDFAttributeMap(XJDFConstants.BinderySignatureID, "bs2.1"), false, false, 0).size());
	}

	/**
	 * @return
	 * @throws DataFormatException
	 */
	@Test
	void testStrippingMultiBSPosBSName() throws DataFormatException
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Stripping);

		final JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Output);
		lo.addPartition(EnumPartIDKey.SheetName, "s1");
		lo.addPartition(EnumPartIDKey.SheetName, "s2");
		lo.addPartition(EnumPartIDKey.SheetName, "s3");

		final JDFBinderySignature bs1 = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		bs1.appendSignatureCell().setFrontPages(JDFIntegerList.createIntegerList("1 2"));

		final JDFBinderySignature bs2 = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		bs2.appendSignatureCell().setFrontPages(JDFIntegerList.createIntegerList("1 2 3 4"));

		final JDFStrippingParams sp = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input);
		final JDFStrippingParams sp1 = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.SheetName, "s1");
		sp1.refBinderySignature(bs1);
		sp1.setAssemblyID("ass1");
		sp1.appendPosition();
		sp1.appendPosition();
		final JDFStrippingParams sp2 = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.SheetName, "s2");
		sp2.refBinderySignature(bs2);
		sp2.setAssemblyID("ass2");
		sp2.appendPosition();
		sp2.appendPosition();
		final JDFStrippingParams sp3 = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.SheetName, "s3");
		sp3.refBinderySignature(bs2);
		sp3.setAssemblyID("ass2");
		sp3.appendPosition();
		sp3.appendPosition();

		final JDFToXJDF xjdf20 = new JDFToXJDF();
		xjdf20.setSingleNode(true);
		final KElement xjdf = xjdf20.makeNewJDF(n, null);
		xjdf.write2File(sm_dirTestDataTemp + "bs.xjdf");
		assertNotNull(xjdf);
		assertEquals(2, XJDFHelper.getHelper(xjdf).getSet(ElementName.BINDERYSIGNATURE, 0).getPartitionList().size());
		assertEquals(6, xjdf.getChildArrayByClass(JDFPosition.class, true, 0).size());
		assertEquals(4,
				xjdf.getChildrenByTagName(ElementName.POSITION, null, new JDFAttributeMap(XJDFConstants.BinderySignatureID, "ass2"), false, false, 0).size());
	}

	/**
	 * @return
	 * @throws DataFormatException
	 */
	@Test
	void testTCPRef() throws DataFormatException
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
	 * @return
	 * @throws DataFormatException
	 */
	@Test
	void testFold() throws DataFormatException
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Folding);
		final JDFFoldingParams fp = (JDFFoldingParams) n.addResource(ElementName.FOLDINGPARAMS, EnumUsage.Input);
		fp.setFoldCatalog(4, 1);
		fp.appendFold().setTravel(44);
		final JDFToXJDF xjdf20 = new JDFToXJDF();
		xjdf20.setSingleNode(true);
		final KElement xjdf = xjdf20.makeNewJDF(n, null);
		xjdf.write2File(sm_dirTestDataTemp + "fc.xjdf");
		assertNotNull(xjdf);
	}

	/**
	 * @return
	 * @throws DataFormatException
	 */
	@Test
	void testTCPSet() throws DataFormatException
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Stripping);
		final JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Input);
		final JDFLayout lo1 = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFLayout lo2 = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "s2");

		final JDFTransferCurvePool tcp = (JDFTransferCurvePool) n.addResource(ElementName.TRANSFERCURVEPOOL, null);
		final JDFTransferCurvePool tcp1 = (JDFTransferCurvePool) tcp.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFTransferCurveSet tcs1 = tcp.appendTransferCurveSet();
		tcs1.setName("Paper");
		tcs1.setDescriptiveName("ddd");
		final JDFTransferCurve tc1 = tcs1.appendTransferCurve();
		tc1.setCurve(new JDFTransferFunction("0 0 1 1"));
		tc1.setSeparation("Cyan");

		final JDFTransferCurvePool tcp2 = (JDFTransferCurvePool) tcp.addPartition(EnumPartIDKey.SheetName, "s2");
		final JDFTransferCurveSet tcs2 = tcp.appendTransferCurveSet();
		tcs2.setName("Plate");
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
		assertEquals("0 0 1 1", xjdf.getXPathAttribute("ResourceSet/Resource[3]/TransferCurve/@Curve", null));
		assertEquals("1 1 0 0", xjdf.getXPathAttribute("ResourceSet/Resource[4]/TransferCurve/@Curve", null));
	}

	/**
	 * @return
	 * @throws DataFormatException
	 */
	@Test
	void testTCPSig() throws DataFormatException
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Stripping);
		final JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Input);
		final JDFLayout lo1 = (JDFLayout) lo.addPartition(EnumPartIDKey.SignatureName, "s1").addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFLayout lo2 = (JDFLayout) lo.addPartition(EnumPartIDKey.SignatureName, "s2").addPartition(EnumPartIDKey.SheetName, "s2");

		final JDFTransferCurvePool tcp = (JDFTransferCurvePool) n.addResource(ElementName.TRANSFERCURVEPOOL, null);
		final JDFTransferCurvePool tcp1 = (JDFTransferCurvePool) tcp.addPartition(EnumPartIDKey.SignatureName, "s1").addPartition(EnumPartIDKey.SheetName,
				"s1");
		final JDFTransferCurveSet tcs1 = tcp.appendTransferCurveSet();
		tcs1.setName("Paper");
		tcs1.setDescriptiveName("ddd");
		final JDFTransferCurve tc1 = tcs1.appendTransferCurve();
		tc1.setCurve(new JDFTransferFunction("0 0 1 1"));
		tc1.setSeparation("Cyan");

		final JDFTransferCurvePool tcp2 = (JDFTransferCurvePool) tcp.addPartition(EnumPartIDKey.SignatureName, "s2").addPartition(EnumPartIDKey.SheetName,
				"s2");
		final JDFTransferCurveSet tcs2 = tcp.appendTransferCurveSet();
		tcs2.setName("Plate");
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
		assertEquals("Substrate", xjdf.getXPathAttribute("ResourceSet/Resource/Part/@TransferCurveName", null));
		assertEquals("1 1 0 0", xjdf.getXPathAttribute("ResourceSet/Resource[2]/TransferCurve/@Curve", null));
		assertEquals("Plate", xjdf.getXPathAttribute("ResourceSet/Resource[2]/Part/@TransferCurveName", null));
		assertEquals("0 0 1 1", xjdf.getXPathAttribute("ResourceSet/Resource[3]/TransferCurve/@Curve", null));
		assertEquals("Substrate", xjdf.getXPathAttribute("ResourceSet/Resource[3]/Part/@TransferCurveName", null));
		assertEquals("1 1 0 0", xjdf.getXPathAttribute("ResourceSet/Resource[4]/TransferCurve/@Curve", null));
	}

	/**
	 * @return
	 */
	@Test
	void testMultiTypes()
	{
		final JDFNode nP = createBaseProductNode();
		nP.addJDFNode(EnumType.ImageSetting);
		nP.addJDFNode(EnumType.ConventionalPrinting);
		final JDFToXJDF xjdf20 = new JDFToXJDF();
		final KElement xjdf = xjdf20.convert(nP);
		assertEquals("Product ImageSetting ConventionalPrinting", xjdf.getAttribute("Types"));
	}

	/**
	 * @return
	 */
	@Test
	void testMultiTypeProductPart()
	{
		final JDFNode nP = createBaseProductNode();
		nP.addJDFNode(EnumType.ImageSetting).setJobPartID("j1");
		nP.addJDFNode(EnumType.ConventionalPrinting).setJobPartID("j2");
		final JDFToXJDF xjdf20 = new JDFToXJDF();
		final KElement xjdf = xjdf20.convert(nP);
		assertEquals(3, new XJDFHelper(xjdf).getSets(ElementName.NODEINFO, null).size());
	}

	/**
	 * @return
	 */
	@Test
	void testSingleTypes()
	{
		final JDFNode nP = createBaseProductNode();
		nP.setType(EnumType.ImageSetting);
		final JDFToXJDF xjdf20 = new JDFToXJDF();
		final KElement xjdf = xjdf20.convert(nP);
		assertEquals("ImageSetting", xjdf.getAttribute("Types"));
	}

	/**
	 * @return
	 */
	@Test
	void testPackingIntent()
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
	 * @return
	 */
	@Test
	void testScreeningIntent()
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
	 * @return
	 */
	@Test
	void testRetainResPool()
	{
		final JDFNode nP = createBaseProductNode();
		final JDFScreeningIntent pi = (JDFScreeningIntent) nP.addResource(ElementName.SCREENINGINTENT, EnumUsage.Input);
		pi.appendScreeningType().setActual(EnumSpanScreeningType.AM);

		final JDFToXJDF xjdf20 = new JDFToXJDF();
		xjdf20.setSingleNode(true);
		xjdf20.setSpanAsAttribute(true);
		xjdf20.setRetainAll(true);
		xjdf20.setNewVersion(EnumVersion.Version_2_1);
		final KElement xjdf = xjdf20.makeNewJDF(nP, null);
		xjdf.write2File(sm_dirTestDataTemp + "retain.xjdf");
		assertNotNull(xjdf);
		assertNull(xjdf.getChildByTagName(ElementName.RESOURCEPOOL, null, 0, null, false, false));
		writeRoundTrip(nP, "pack");
	}

	/**
	 * @return
	 */
	@Test
	void testRetainPartID()
	{
		final JDFNode nP = createBaseProductNode();
		nP.setJobPartID("jp1");
		final JDFLayoutIntent loi = (JDFLayoutIntent) nP.addResource(ElementName.LAYOUTINTENT, EnumUsage.Input);
		loi.appendFinishedDimensions().setActual(new JDFShape(20, 30));
		final JDFToXJDF xjdf20 = new JDFToXJDF();
		xjdf20.setSingleNode(true);
		xjdf20.setRetainAll(true);
		xjdf20.setNewVersion(EnumVersion.Version_2_1);
		final KElement xjdf = xjdf20.makeNewJDF(nP, null);
		xjdf.write2File(sm_dirTestDataTemp + "retainJP.xjdf");
		assertNotNull(xjdf);
		assertEquals("jp1", xjdf.getXPathAttribute("ProductList/Product/@JobPartID", null));
	}

	/**
	 * @return
	 */
	@Test
	void testPageList()
	{
		_testPageList(false);
	}

	/**
	 * @return
	 */
	@Test
	void testPageListRetainAll()
	{
		_testPageList(true);
	}

	/**
	 * @return
	 */
	@Test
	void testPageListSimple()
	{
		final JDFNode root = new JDFDoc("JDF").getJDFRoot();
		root.setType(EnumType.Trapping);

		final JDFRunList rl = (JDFRunList) root.addResource(ElementName.RUNLIST, EnumUsage.Input);
		final JDFRunList ruLiRun = rl.addRun("foo.pdf", 0, 42);
		final JDFRunList ruLiRun2 = rl.addRun("foo2.pdf", 0, 666);
		JDFPageList pList = ruLiRun.appendPageList();
		pList = (JDFPageList) pList.makeRootResource(null, null, true);
		for (int i = 0; i < 2; i++)
		{
			final JDFPageData pd = pList.appendPageData();
			pd.setPageStatus("DigitalArtArrived");
		}

		ruLiRun2.refElement(pList);

		final XJDF20 xjdf20 = new XJDF20();
		xjdf20.setSingleNode(true);
		final KElement xjdf = xjdf20.makeNewJDF(root, null);
		xjdf.write2File(sm_dirTestDataTemp + "pageListTest.simple.xjdf");
		assertTrue(reparse(xjdf, 2, -1));
		assertNotNull(xjdf);
		for (int i = 1; i < 3; i++)
		{
			assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource[" + i + "]/Content/@ContentStatus", null), "DigitalArtArrived");
		}
	}

	/**
	 * @return
	 */
	KElement _testPageList(final boolean retainAll)
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
	 * @return
	 */
	@Test
	void testPageListEmpty()
	{
		_testPageListEmpty();
	}

	/**
	 * @return
	 */
	@Test
	void testPageListNS()
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
	void testSignatureName()
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
	void testKeepParameter()
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
		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource/@DescriptiveName", null), "desc");
	}

	/**
	 *
	 *
	 */
	@Test
	void testCleanupFalse()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFToXJDF conv = new JDFToXJDF();
		conv.setCleanup(false);
		final KElement xjdf = conv.convert(n);
		assertNotNull(xjdf.getXPathAttribute("ResourceSet/@ID", null));
	}

	/**
	 *
	 *
	 */
	@Test
	void testCleanupTrue()
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
	void testComponentProductID()
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
	 */
	@Test
	void testDisjointingDPP()
	{
		final JDFNode n = JDFNode.parseFile(sm_dirTestData + "xjdf/Disjointing.jdf");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		xjdf.write2File(sm_dirTestDataTemp + "Disjointing.xjdf");
		final XJDFHelper h = XJDFHelper.getHelper(xjdf);
		assertTrue(h.getTypes().contains("Stacking"));
		final SetHelper sp = h.getSet(ElementName.STACKINGPARAMS, 0);
		assertNotNull(sp.getPartition(0).getResource().getElement(ElementName.DISJOINTING));
		final SetHelper dpp = h.getSet(ElementName.DIGITALPRINTINGPARAMS, 0);
		assertNotNull(dpp);
	}

	/**
	 *
	 *
	 */
	@Test
	void testColorantControl()
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
	void testColorantControlRefPool()
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
	void testColorPool()
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
	void testColorPoolSpace()
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
	void testColorantControlSpace()
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
	void testColorantControlDCO()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ImageSetting);

		final JDFColorantControl cc = (JDFColorantControl) n.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		cc.appendDeviceColorantOrder().setSeparations(new VString("sep1,sep2", ","));

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);

		final ResourceHelper partition = new XJDFHelper(xjdf).getPartition(ElementName.COLORANTCONTROL, 0, 0);
		final JDFColorantControl cNew = (JDFColorantControl) partition.getResource();
		assertEquals("sep1 sep2", cNew.getAttribute(ElementName.COLORANTORDER));
		assertNull(cNew.getNonEmpty(ElementName.DEVICECOLORANTORDER));
	}

	/**
	 *
	 *
	 */
	@Test
	void testColorIntentStandard()
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
	void testColorIntent()
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
	void testColorIntentCert()
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
		cif.appendElement(ElementName.CERTIFICATION).setAttribute(AttributeName.ORGANIZATION, "fsc");
		final JDFColorIntent cib = (JDFColorIntent) ci.addPartition(EnumPartIDKey.Side, "Back");
		cib.appendCoatings().setActual("Mess");
		cib.setNumColors(1);
		final KElement xjdf = conv.convert(n);
		final ProductHelper ph = new XJDFHelper(xjdf).getProductHelpers().get(0);
		assertNotNull(ph);
		final IntentHelper ih = ph.getIntent("ColorIntent");
		final KElement e = ih.getResource();
		assertEquals(e.getChildElementVector(XJDFConstants.SurfaceColor, null).size(), 2);
		assertEquals("fsc", e.getElement(XJDFConstants.SurfaceColor).getElement(ElementName.CERTIFICATION, null, 0).getAttribute(AttributeName.ORGANIZATION));
		assertNull(e.getElement(XJDFConstants.SurfaceColor, null, 1).getElement(ElementName.CERTIFICATION), "fsc");
		// writeRoundTrip(n, "ColorIntent");
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
	void testDeviceInfoStatus()
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
	void testDeviceInfoEmployees()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		final JDFDeviceInfo di = jmf.getCreateSignal(0).appendDeviceInfo();
		di.appendDevice().setDeviceID("id");
		di.setDeviceStatus(EDeviceStatus.Running);
		di.appendEmployee().setPersonalID("e1");
		di.appendEmployee().setPersonalID("e2");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals(xjmf.getXPathAttribute("SignalStatus/DeviceInfo/Employee/@PersonalID", null), "e1");
		assertEquals(xjmf.getXPathAttribute("SignalStatus/DeviceInfo/Employee[2]@PersonalID", null), "e2");
	}

	/**
	 *
	 */
	@Test
	void testDeviceInfoModuleStatus()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		jmf.setVersion(EnumVersion.Version_1_9);
		final JDFDeviceInfo di = jmf.getCreateSignal(0).appendDeviceInfo();
		di.appendDevice().setDeviceID("id");
		di.setDeviceStatus(EDeviceStatus.Running);
		final JDFModuleStatus moduleStatus = di.appendModuleStatus();
		moduleStatus.setModuleIndex(new JDFIntegerRangeList(new int[] { 0 }));
		moduleStatus.setDeviceStatus(EDeviceStatus.Running);
		final JDFToXJDF conv = new JDFToXJDF();
		conv.setNewVersion(EnumVersion.Version_2_3);
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals("0", xjmf.getXPathAttribute("SignalStatus/DeviceInfo/ModuleInfo/@ModuleID", null));
		assertEquals(eDeviceStatus.Production.name(), xjmf.getXPathAttribute("SignalStatus/DeviceInfo/ModuleInfo/@ModuleStatus", null));
	}

	/**
	 *
	 */
	@Test
	void testDeviceInfoModulePart()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		jmf.setVersion(EnumVersion.Version_1_9);
		final JDFDeviceInfo di = jmf.getCreateSignal(0).appendDeviceInfo();
		di.appendDevice().setDeviceID("id");
		di.setDeviceStatus(EDeviceStatus.Running);
		final JDFModuleStatus moduleStatus = di.appendModuleStatus();
		moduleStatus.setModuleIndex(new JDFIntegerRangeList(new int[] { 0 }));
		moduleStatus.setDeviceStatus(EDeviceStatus.Running);
		moduleStatus.appendPart().setAttribute(AttributeName.SEPARATION, "Black");
		final JDFToXJDF conv = new JDFToXJDF();
		conv.setNewVersion(EnumVersion.Version_2_3);
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals("0", xjmf.getXPathAttribute("SignalStatus/DeviceInfo/ModuleInfo/@ModuleID", null));
		assertEquals(eDeviceStatus.Production.name(), xjmf.getXPathAttribute("SignalStatus/DeviceInfo/ModuleInfo/@ModuleStatus", null));
		assertEquals("Black", xjmf.getXPathAttribute("SignalStatus/DeviceInfo/ModuleInfo/Part/@Separation", null));
	}

	/**
	 *
	 */
	@Test
	void testDeviceInfoModuleType()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		jmf.setVersion(EnumVersion.Version_1_9);
		final JDFDeviceInfo di = jmf.getCreateSignal(0).appendDeviceInfo();
		di.appendDevice().setDeviceID("id");
		di.setDeviceStatus(EDeviceStatus.Running);
		final JDFModuleStatus moduleStatus = di.appendModuleStatus();
		moduleStatus.setModuleIndex(new JDFIntegerRangeList(new int[] { 0 }));
		moduleStatus.setDeviceStatus(EDeviceStatus.Running);
		moduleStatus.setModuleType("typ");
		final JDFToXJDF conv = new JDFToXJDF();
		conv.setNewVersion(EnumVersion.Version_2_3);
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals("typ", xjmf.getXPathAttribute("SignalStatus/DeviceInfo/ModuleInfo/@ModuleType", null));
	}

	/**
	 *
	 */
	@Test
	void testDeviceInfoModuleStatusSkip()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Status);
		final JDFDeviceInfo di = jmf.getCreateSignal(0).appendDeviceInfo();
		di.appendDevice().setDeviceID("id");
		di.setDeviceStatus(EDeviceStatus.Running);
		di.appendModuleStatus().setModuleIndex(new JDFIntegerRangeList(new int[] { 0 }));
		di.appendModuleStatus().setModuleIndex(new JDFIntegerRangeList(new int[] { 4, 6 }));
		final JDFToXJDF conv = new JDFToXJDF();
		conv.setNewVersion(EnumVersion.Version_2_3);
		final KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals("0", xjmf.getXPathAttribute("SignalStatus/DeviceInfo/ModuleInfo/@ModuleID", null));
	}

	/**
	 *
	 */
	@Test
	void testJMFEmployee()
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
	void testNodeEmployee()
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
	void testNodeInfoEmployee()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFNodeInfo ni = n.getCreateNodeInfo();
		ni.setNodeStatus(EnumNodeStatus.Waiting);
		final JDFEmployee emp = ni.appendEmployee();
		final VString roles = new VString("ShiftLeader TelephoneSanitizer", null);
		emp.setRoles(roles);
		emp.setPersonalID("P1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/Part/@ContactType", null), ElementName.EMPLOYEE);
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/Contact/@ContactTypeDetails", null), "ShiftLeader TelephoneSanitizer CSR");
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/@ExternalID", null), "P1");
		assertNull(xjdf.getXPathAttribute("ResourceSet[@Name=\"NodeInfo\"]/Resource/NodeInfo/@ContactRef", null));

		final XJDFToJDFConverter invert = new XJDFToJDFConverter(null);
		final JDFDoc d = invert.convert(xjdf);
		final JDFNode n2 = d.getJDFRoot();
		final JDFEmployee emp3 = (JDFEmployee) n2.getResource(ElementName.EMPLOYEE, EnumUsage.Input, 0);
		assertNull(emp3);
		final JDFEmployee emp2 = n.getNodeInfo().getEmployee();
		assertEquals(emp2.getPersonalID(), "P1");
		assertEquals(emp2.getRoles(), roles);
	}

	/**
	 *
	 */
	@Test
	void testNodeInfoStatus()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.getCreateNodeInfo();
		n.setPartStatus((VJDFAttributeMap) null, EnumNodeStatus.Completed, null);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertEquals("Completed", xjdf.getXPathAttribute("ResourceSet[@Name=\"NodeInfo\"]/Resource/NodeInfo/@Status", null));
	}

	/**
	 *
	 */
	@Test
	void testNodeInfoPartition()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFNodeInfo ni = n.getCreateNodeInfo();
		ni.setStart(new JDFDate());
		final JDFNodeInfo nis = (JDFNodeInfo) ni.addPartition(EnumPartIDKey.SignatureName, "Sig1").addPartition(EnumPartIDKey.SheetName, "S1");
		ni.setNodeStatus(EnumNodeStatus.Waiting);
		nis.setEnd(new JDFDate());

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		final SetHelper niSet = XJDFHelper.getHelper(xjdf).getNodeInfo();
		final List<ResourceHelper> partitionList = niSet.getPartitionList();
		assertEquals(2, partitionList.size());
		for (final ResourceHelper rh : partitionList)
		{
			assertNotNull(rh.getRoot().getElement(ElementName.NODEINFO, null, 0));
			assertNull(rh.getRoot().getElement(ElementName.NODEINFO, null, 1));
		}
	}

	/**
	 *
	 */
	@Test
	void testRef()
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
	void testRefSelf()
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
	void testRunListDir()
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
	void testRunListCopies()
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
	void testRunListRunPage()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.RasterReading);
		final JDFRunList r1 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		final JDFRunList r1r = (JDFRunList) r1.addPartition(EnumPartIDKey.Run, "r1");
		final JDFRunList r1rp = (JDFRunList) r1r.addPartition(EnumPartIDKey.RunPage, "1");
		r1rp.setFileURL("file:///foo.pdf");
		final JDFRunList r1rp2 = (JDFRunList) r1r.addPartition(EnumPartIDKey.RunPage, "2");
		r1rp2.setFileURL("file:///foo.pdf");

		final JDFRunList r2 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Output);
		r2.setFileURL("file:///fooout.pdf");

		writeRoundTrip(n, "RunListCopies");
		final JDFToXJDF c = new JDFToXJDF();
		final KElement e = c.convert(n);
		final ResourceHelper rl = new XJDFHelper(e).getSet(ElementName.RUNLIST, 0).getResource(0);
		assertEquals("1 1", rl.getPartMap().get(AttributeName.PAGENUMBER));
	}

	/**
	*
	*/
	@Test
	void testRunListPages()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.RasterReading);
		final JDFRunList r1 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		r1.setFileURL("file:///foo.pdf");
		r1.setPages(JDFIntegerRangeList.createIntegerRangeList("0~2 4~6 7"));
		final JDFRunList r2 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Output);
		r2.setFileURL("file:///fooout.pdf");
		writeRoundTrip(n, "RunListPages");
	}

	/**
	*
	*/
	@Test
	void testRunListPagesRepeat()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.RasterReading);
		final JDFRunList r1 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		final JDFRunList r11 = r1.addRun("file:///foo1.pdf", 0, 0);
		r11.setPages(JDFIntegerRangeList.createIntegerRangeList("0 0 0 0 0 0"));
		final JDFRunList r12 = r1.addRun("file:///foo2.pdf", 0, 0);
		r12.setPages(JDFIntegerRangeList.createIntegerRangeList("0 0 0 0"));
		final JDFRunList r2 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Output);
		r2.setFileURL("file:///fooout.pdf");
		writeRoundTrip(n, "RunListPagesRepeat");
	}

	/**
	*
	*/
	@Test
	void testRunListPagesNeg()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.RasterReading);
		final JDFRunList r1 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		r1.setFileURL("file:///foo.pdf");
		r1.setPages(JDFIntegerRangeList.createIntegerRangeList("0~2 4~-5"));
		final JDFRunList r2 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Output);
		r2.setFileURL("file:///fooout.pdf");
		writeRoundTrip(n, "RunListPagesNeg");

		final JDFToXJDF c = new JDFToXJDF();
		final KElement e = c.convert(n);
		final JDFRunList rl = (JDFRunList) new XJDFHelper(e).getSet(ElementName.RUNLIST, 0).getResource(0).getResource();
		assertEquals(3, rl.getNPage());
		assertEquals("0 2", rl.getAttribute(AttributeName.PAGES));
		final JDFRunList rl1 = (JDFRunList) new XJDFHelper(e).getSet(ElementName.RUNLIST, 0).getResource(1).getResource();
		assertEquals(2, rl1.getNPage());
		assertEquals("4 -5", rl1.getAttribute(AttributeName.PAGES));

	}

	/**
	*
	*/
	@Test
	void testRunListPagesLogicalPage()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.RasterReading);
		final JDFRunList r1 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		r1.setFileURL("file:///foo.pdf");
		r1.setPages(JDFIntegerRangeList.createIntegerRangeList("0~2 4~8 10"));
		r1.setLogicalPage(4);
		final XJDF20 xjdfConv = new XJDF20();
		final KElement xjdfRoot = xjdfConv.convert(n);

		final XJDFHelper h = new XJDFHelper(xjdfRoot);
		final SetHelper rls = h.getSet(ElementName.RUNLIST, null);
		assertEquals(3, rls.size());
		assertEquals("7", rls.getPartition(1).getResourceAttribute(AttributeName.LOGICALPAGE));
		assertEquals("12", rls.getPartition(2).getResourceAttribute(AttributeName.LOGICALPAGE));
	}

	/**
	*
	*/
	@Test
	void testRunListPagesLogicalPageNeg()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.RasterReading);
		final JDFRunList r1 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		r1.setFileURL("file:///foo.pdf");
		r1.setPages(JDFIntegerRangeList.createIntegerRangeList("0~2 4~-8 10"));
		r1.setLogicalPage(4);
		final XJDF20 xjdfConv = new XJDF20();
		final KElement xjdfRoot = xjdfConv.convert(n);

		final XJDFHelper h = new XJDFHelper(xjdfRoot);
		final SetHelper rls = h.getSet(ElementName.RUNLIST, null);
		assertEquals(3, rls.size());
		assertEquals("7", rls.getPartition(1).getResourceAttribute(AttributeName.LOGICALPAGE));
		assertEquals(null, rls.getPartition(2).getResourceAttribute(AttributeName.LOGICALPAGE));
	}

	/**
	*
	*/
	@Test
	void testMetaDataMulti()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.RasterReading);
		final JDFRunList r1 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		final JDFResource p0 = r1.addPartition(EnumPartIDKey.Metadata0, "m1");
		final JDFResource p1 = p0.addPartition(EnumPartIDKey.Metadata1, "m2");
		p1.setProductID("p1");
		r1.setFileURL("file:///foo.pdf");
		final JDFRunList r2 = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Output);
		r2.setFileURL("file:///fooout.pdf");

		writeRoundTrip(n, "metadata");
	}

	/**
	 *
	 */
	@Test
	void testResLocked()
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
	void testPartUsagePartsExplicit()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource dpp = n.addResource(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input);
		dpp.setDescriptiveName("d");
		final JDFResource dpp1 = dpp.addPartition(EnumPartIDKey.RunIndex, "1");
		dpp1.setDescriptiveName("d1");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		final XJDFHelper h = new XJDFHelper(xjdf);
		final SetHelper s = h.getSet(ElementName.DIGITALPRINTINGPARAMS, 0);
		assertEquals(1, s.getPartitionList().size());
	}

	/**
	 *
	 */
	@Test
	void testPartUsagePartsImplicit()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource dpp = n.addResource(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input);
		dpp.setDescriptiveName("d");
		dpp.setBrand("b");
		dpp.setPartUsage(EnumPartUsage.Implicit);
		final JDFResource dpp1 = dpp.addPartition(EnumPartIDKey.RunIndex, "1");
		dpp1.setDescriptiveName("d1");
		dpp.setBrand("b1");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		final XJDFHelper h = new XJDFHelper(xjdf);
		final SetHelper s = h.getSet(ElementName.DIGITALPRINTINGPARAMS, 0);
		assertEquals(2, s.getPartitionList().size());
		assertNull(s.getPartition(0).getAttribute(AttributeName.PARTUSAGE));
		assertNull(s.getPartition(1).getAttribute(AttributeName.PARTUSAGE));
	}

	/**
	 *
	 */
	@Test
	void testPartUsagePartsImplicitRoot()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource dpp = n.addResource(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input);
		dpp.setPartUsage(EnumPartUsage.Implicit);
		dpp.setDescriptiveName("root");
		final JDFResource dpp1 = dpp.addPartition(EnumPartIDKey.RunIndex, "1");
		dpp1.setDescriptiveName("d1");
		final JDFResource dpp2 = dpp.addPartition(EnumPartIDKey.RunIndex, "2");
		dpp2.setDescriptiveName("d2");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		final XJDFHelper h = new XJDFHelper(xjdf);
		final SetHelper s = h.getSet(ElementName.DIGITALPRINTINGPARAMS, 0);
		assertEquals(3, s.getPartitionList().size());
	}

	/**
	 *
	 */
	@Test
	void testPartUsagePartsImplicitRootRetain()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource dpp = n.addResource(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input);
		dpp.setPartUsage(EnumPartUsage.Implicit);
		final JDFResource dpp1 = dpp.addPartition(EnumPartIDKey.RunIndex, "1");
		dpp1.setDescriptiveName("d1");
		final JDFResource dpp2 = dpp.addPartition(EnumPartIDKey.RunIndex, "2");
		dpp2.setDescriptiveName("d2");

		final JDFToXJDF conv = new JDFToXJDF();
		conv.setRetainAll(true);
		final KElement xjdf = conv.makeNewJDF(n, null);
		final XJDFHelper h = new XJDFHelper(xjdf);
		final SetHelper s = h.getSet(ElementName.DIGITALPRINTINGPARAMS, 0);
		assertEquals(2, s.getPartitionList().size());
	}

	/**
	 *
	 */
	@Test
	void testMixedMediaRef()
	{
		final JDFNode n = JDFNode.parseFile(sm_dirTestData + "mixedMediaRef.jdf");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		final XJDFHelper h = new XJDFHelper(xjdf);
		final SetHelper s = h.getSet(ElementName.DIGITALPRINTINGPARAMS, 0);
		assertEquals(2, s.getPartitionList().size());
		assertEquals(1, s.getPartition(0).getPartMap().size());
		assertEquals(0, s.getPartition(1).getPartMap().size());
	}

	/**
	 *
	 */
	@Test
	void testMixedMediaRef2()
	{
		final JDFNode n = JDFNode.parseFile(sm_dirTestData + "SaddleStitchBooklet_MixedMedia2.jdf");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		final XJDFHelper h = new XJDFHelper(xjdf);
		final SetHelper dpp = h.getSet(ElementName.DIGITALPRINTINGPARAMS, 0);
		assertEquals(3, dpp.getPartitionList().size());
		assertEquals(1, dpp.getPartition(0).getPartMap().size());
		assertEquals(1, dpp.getPartition(1).getPartMap().size());
		assertEquals(0, dpp.getPartition(2).getPartMap().size());
		final SetHelper lo = h.getSet(ElementName.LAYOUT, 0);
		assertEquals(3, lo.getPartitionList().size());

		for (int i = 0; i < 3; i++)
		{
			assertNotNull(lo.getResource(i).getResourceAttribute(XJDFConstants.PaperRef));
		}
	}

	/**
	 *
	 */
	@Test
	void testPartUsagePartsImplicitRootWantImplicitNot()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource dpp = n.addResource(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input);
		dpp.setPartUsage(EnumPartUsage.Implicit);
		dpp.setDescriptiveName("boo");
		final JDFResource dpp1 = dpp.addPartition(EnumPartIDKey.RunIndex, "1");
		dpp1.setDescriptiveName("d1");
		final JDFResource dpp2 = dpp.addPartition(EnumPartIDKey.RunIndex, "2");
		dpp2.setDescriptiveName("d2");

		final JDFToXJDF conv = new JDFToXJDF();
		conv.setRetainAll(true);
		conv.setwantImplicit(false);
		final KElement xjdf = conv.makeNewJDF(n, null);
		final XJDFHelper h = new XJDFHelper(xjdf);
		final SetHelper s = h.getSet(ElementName.DIGITALPRINTINGPARAMS, 0);
		assertEquals(2, s.getPartitionList().size());
	}

	/**
	 *
	 */
	@Test
	void testResUnit()
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
	void testResDescName()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource r1 = n.addResource(ElementName.MEDIA, EnumUsage.Input);
		r1.setDescriptiveName("c1");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertNull(xjdf.getXPathAttribute("ResourceSet[@Name=\"Media\"]/@DescriptiveName", null));
		assertEquals("c1", xjdf.getXPathAttribute("ResourceSet[@Name=\"Media\"]/Resource/@DescriptiveName", null));
	}

	/**
	 *
	 */
	@Test
	void testSourceRes()
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
	void testWorkstepID()
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
	void testVersion()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFToXJDF conv = new JDFToXJDF();
		conv.setRetainAll(true);
		conv.setNewVersion(EnumVersion.Version_2_1);
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertEquals("2.1", xjdf.getAttribute(AttributeName.VERSION));
	}

	/**
	 *
	 */
	@Test
	void testCounterID()
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
	void testMISDetails()
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
	void testPerson()
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
	void testPreview()
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
	void testDieLayoutMedia()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFDieLayout dl = (JDFDieLayout) n.addResource(ElementName.DIELAYOUT, EnumUsage.Input);
		dl.setProductID("id_dl");
		final JDFMedia m = dl.appendMedia();
		m.setMediaType(EnumMediaType.Paper);
		m.setProductID("pid");
		m.setGrade(3);
		m.makeRootResource("mid", null, true);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertNull(xjdf.getXPathAttribute("ResourceSet[@Name=\"DieLayout\"]/Resource/DieLayout/@MediaRef", null));
		assertEquals("PS3", xjdf.getXPathAttribute("ResourceSet[@Name=\"DieLayout\"]/Resource/DieLayout/Media/@ISOPaperSubstrate", null));
	}

	/**
	 *
	 */
	// @Test
	void testDigitalMixed()
	{
		final JDFNode n = JDFNode.parseFile(sm_dirTestData + "xjdf/MixedSides.jdf");
		writeRoundTrip(n, "mixedsides");
	}

	/**
	 *
	 */
	@Test
	void testGangElementMedia()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFSheetOptimizingParams sop = (JDFSheetOptimizingParams) n.addResource(ElementName.SHEETOPTIMIZINGPARAMS, EnumUsage.Input);

		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, null);
		m.setMediaType(EnumMediaType.Paper);
		m.setProductID("pid");
		m.setGrade(3);
		for (int i = 0; i < 3; i++)
		{
			sop.appendGangElement().refMedia(m);
		}
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertNull(xjdf.getXPathAttribute("ResourceSet[@Name=\"SheetOptimizingParams\"]/Resource/SheetOptimizingParams/GangElement[1]/@MediaRef", null));
		assertEquals("PS3", xjdf.getXPathAttribute(
				"ResourceSet[@Name=\"SheetOptimizingParams\"]/Resource/SheetOptimizingParams/GangElement[1]/Media/@ISOPaperSubstrate", null));
	}

	/**
	 *
	 */
	@Test
	void testResStatus()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFPreview pv = (JDFPreview) n.addResource(ElementName.PREVIEW, EnumUsage.Input);
		pv.setURL("previewURL");
		pv.setResStatus(EnumResStatus.Available, false);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertEquals(EnumResStatus.Available, new XJDFHelper(xjdf).getSet(ElementName.PREVIEW, 0).getPartition(0).getStatus());
	}

	/**
	 *
	 */
	@Test
	void testResStatusMove()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFPreview pv = (JDFPreview) n.addResource(ElementName.PREVIEW, EnumUsage.Input);
		pv.setURL("previewURL");
		pv.setResStatus(EnumResStatus.Incomplete, false);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		assertEquals(EnumResStatus.Unavailable, new XJDFHelper(xjdf).getSet(ElementName.PREVIEW, 0).getPartition(0).getStatus());
	}

	/**
	 *
	 */
	@Test
	void testProductRetainAll()
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
	void testProductPartVersion()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Product);

		final JDFNode n1 = n.addJDFNode(EnumType.Product);
		final JDFComponent c1 = (JDFComponent) n1.addResource(ElementName.COMPONENT, null);
		c1.setComponentType(EnumComponentType.FinalProduct, null);
		n1.ensureLink(c1.addPartition(EnumPartIDKey.PartVersion, "v1"), EnumUsage.Output, null).setAmount(100);

		final JDFNode n2 = n.addJDFNode(EnumType.Product);
		final JDFComponent c2 = (JDFComponent) n2.addResource(ElementName.COMPONENT, null);
		c2.setComponentType(EnumComponentType.FinalProduct, null);
		n2.ensureLink(c2.addPartition(EnumPartIDKey.PartVersion, "v2"), EnumUsage.Output, null).setAmount(200);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		final XJDFHelper h = new XJDFHelper(xjdf);
		assertEquals("v1", h.getRootProduct(0).getAttribute(AttributeName.PARTVERSION));
		assertEquals("v2", h.getRootProduct(1).getAttribute(AttributeName.PARTVERSION));
	}

	/**
	 *
	 */
	@Test
	void testProductPartVersionDelivery()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Product);

		final JDFNode n1 = n.addJDFNode(EnumType.Product);
		final JDFComponent c1 = (JDFComponent) n1.addResource(ElementName.COMPONENT, null);
		c1.setComponentType(EnumComponentType.FinalProduct, null);
		final JDFComponent c11 = (JDFComponent) c1.addPartition(EnumPartIDKey.PartVersion, "v1");
		n1.ensureLink(c11, EnumUsage.Output, null).setAmount(100);

		final JDFNode n2 = n.addJDFNode(EnumType.Product);
		final JDFComponent c12 = (JDFComponent) c1.addPartition(EnumPartIDKey.PartVersion, "v2");
		n2.ensureLink(c12, EnumUsage.Output, null).setAmount(200);

		final JDFDeliveryIntent di = (JDFDeliveryIntent) n.getCreateResource(ElementName.DELIVERYINTENT, EnumUsage.Input);
		final JDFDropIntent dropIntent = di.appendDropIntent();
		dropIntent.getCreateEarliest().setActual(new JDFDate());
		final JDFDropItemIntent dii1 = dropIntent.appendDropItemIntent();
		dii1.refComponent(c11);
		dii1.setAmount(42);
		final JDFDropItemIntent dii2 = dropIntent.appendDropItemIntent();
		dii2.refComponent(c12);
		dii2.setAmount(66);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		final XJDFHelper h = new XJDFHelper(xjdf);
		assertEquals("v1", h.getRootProduct(0).getAttribute(AttributeName.PARTVERSION));
		assertEquals("v2", h.getRootProduct(1).getAttribute(AttributeName.PARTVERSION));
		final SetHelper sh = h.getSet(ElementName.DELIVERYPARAMS, 0);
		final JDFDeliveryParams dp = (JDFDeliveryParams) sh.getResource(0).getResource();
		final JDFDropItem d0 = dp.getDropItem(0);
		final JDFDropItem d1 = dp.getDropItem(1);
		assertNotEquals(d0.getItemRef(), d1.getItemRef());
	}

	/**
	 *
	 */
	@Test
	void testProductCoverBody()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Product);

		final JDFNode n1 = n.addJDFNode(EnumType.Product);
		final JDFComponent c1 = (JDFComponent) n1.addResource(ElementName.COMPONENT, EnumUsage.Output);
		c1.setComponentType(EnumComponentType.PartialProduct, null);
		c1.setProductType("Cover");

		final JDFNode n2 = n.addJDFNode(EnumType.Product);
		final JDFComponent c2 = (JDFComponent) n2.addResource(ElementName.COMPONENT, EnumUsage.Output);
		c2.setComponentType(EnumComponentType.FinalProduct, null);
		c2.setProductType("Body");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.makeNewJDF(n, null);
		final XJDFHelper h = new XJDFHelper(xjdf);
		assertEquals("Cover", h.getRootProduct(0).getAttribute(AttributeName.PRODUCTTYPE));
		assertEquals("Body", h.getRootProduct(1).getAttribute(AttributeName.PRODUCTTYPE));
	}

	/**
	 *
	 */
	@Test
	void testNotificationAudit()
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
	void testNotificationEvent()
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
	void testAuditEmployee()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.getCreateAuditPool().addAudit(EnumAuditType.PhaseTime, "me");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertEquals(xjdf.getXPathAttribute("AuditPool/AuditStatus/Header/@Author", null), "me");
		assertEquals(n.getAuditPool().getAudit(0, EnumAuditType.PhaseTime, null, null).getEmployee(0).getDescriptiveName(), "me");

		final XJDFToJDFConverter invert = new XJDFToJDFConverter(null);
		final JDFDoc d = invert.convert(xjdf);
		final JDFNode n2 = d.getJDFRoot();
		assertEquals(n2.getAuditPool().getAudit(0, EnumAuditType.PhaseTime, null, null).getEmployee(0).getDescriptiveName(), "me");
	}

	/**
	 *
	 */
	@Test
	void testApprovalDetails()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Approval);
		final JDFApprovalSuccess as = (JDFApprovalSuccess) n.addResource(ElementName.APPROVALSUCCESS, EnumUsage.Output);
		final JDFApprovalDetails ad = as.appendApprovalDetails();
		ad.setApprovalState(EnumApprovalState.Approved);
		ad.appendComment().setText("ok");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		final XJDFHelper h = new XJDFHelper(xjdf);
		final SetHelper set = h.getSet(ElementName.APPROVALDETAILS, 0);
		assertNotNull(set);
		assertNull(set.getPartition(1));
		final JDFApprovalDetails det = (JDFApprovalDetails) set.getPartition(0).getResource();
		assertEquals(EnumApprovalState.Approved, det.getApprovalState());
		assertEquals("ok", set.getPartition(0).getComment(0));
		assertNull(det.getElement_KElement(ElementName.APPROVALDETAILS, null, 0));

	}

	/**
	 *
	 */
	@Test
	void testArtDeliveryRunLists()
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
	void testAuditCreated()
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
	void testProjectID()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setStatus(EnumNodeStatus.Completed);
		n.setProjectID("p1");
		n.setJobID("j1");
		n.setJobPartID("jp1");
		n.setType(EnumType.Product);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertEquals("j1", xjdf.getAttribute(AttributeName.JOBID));
		assertEquals("p1", xjdf.getAttribute(AttributeName.PROJECTID));
		assertEquals("jp1", xjdf.getAttribute(AttributeName.JOBPARTID));
		assertEquals("", xjdf.getAttribute(AttributeName.ID));
		assertEquals("", xjdf.getAttribute(AttributeName.STATUS));
	}

	/**
	 *
	 */
	@Test
	void testStatus()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setStatus(EnumNodeStatus.Completed);
		n.setProjectID("p1");
		n.setJobID("j1");
		n.setType(EnumType.ConventionalPrinting);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertEquals("j1", xjdf.getAttribute(AttributeName.JOBID));
		assertEquals("p1", xjdf.getAttribute(AttributeName.PROJECTID));
		assertEquals("", xjdf.getAttribute(AttributeName.ID));
		assertEquals("", xjdf.getAttribute(AttributeName.STATUS));
	}

	/**
	 *
	 */
	@Test
	void testAuditProcessRun()
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
	void testDefVersion()
	{
		assertEquals(EnumVersion.Version_2_3, JDFToXJDF.getDefaultVersion());
	}

	/**
	 *
	 */
	@Test
	void testAmountPoolWaste()
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
	void testAncestorPool()
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
	void testAncestorPoolComments()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFAncestor a = n.appendAncestorPool().appendAncestor();
		a.setJobPartID("j2");
		a.appendComment().setText("foo");
		a.appendComment().setText("bar");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);

		assertNull(xjdf.getElement(ElementName.ANCESTORPOOL));
		assertNull(xjdf.getElement(ElementName.COMMENT));
	}

	/**
	 *
	 */
	@Test
	void testComments()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.appendComment().setText("foo");
		n.appendComment().setText("bar");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);

		assertEquals("foo", xjdf.getElement(ElementName.COMMENT, null, 0).getText());
		assertEquals("bar", xjdf.getElement(ElementName.COMMENT, null, 1).getText());
		assertNull(xjdf.getElement(ElementName.COMMENT, null, 2));
	}

	/**
	 *
	 */
	@Test
	void testMediaComponentIn()
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
	void testAmountsSimple()
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
	void testAmountPoolNoExplicitWaste()
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
	void testMediaInline()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		xm.appendMedia().setMediaType(EnumMediaType.Plate);
		xm.setPlateType(EnumPlateType.Dummy);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertNotNull(xjdf.getXPathAttribute("ResourceSet[@Name=\"Media\"]/Resource/@ID", null));
		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource/ExposedMedia/@MediaRef", null),
				xjdf.getXPathAttribute("ResourceSet[@Name=\"Media\"]/Resource/@ID", null));
	}

	/**
	 *
	 */
	@Test
	void testLocation()
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
	void testLayoutMark()
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
	void testLayoutPrepAutomated()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.LayoutPreparation);
		n.addResource(ElementName.LAYOUTPREPARATIONPARAMS, EnumUsage.Input);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource/Layout/@Automated", null), "true");
	}

	/**
	 *
	 */
	@Test
	void testLayoutPrepLayoutUsage()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.LayoutPreparation);
		n.addResource(ElementName.LAYOUTPREPARATIONPARAMS, EnumUsage.Input);

		final JDFToXJDF conv = new JDFToXJDF();
		final XJDFHelper xjdf = conv.convertToXJDF(n);
		assertEquals(EnumUsage.Input, xjdf.getSet(ElementName.LAYOUT, 0).getUsage());
	}

	/**
	 *
	 */
	@Test
	void testLayoutPrepMultiBS()
	{
		final JDFNode n = JDFNode.parseFile(sm_dirTestData + "xjdf/lpp.jdf");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		xjdf.write2File(sm_dirTestDataTemp + "lpp.xjdf");
		final XJDFHelper xh = new XJDFHelper(xjdf);
		final SetHelper bs = xh.getSet(ElementName.BINDERYSIGNATURE, 0);
		final SetHelper lo = xh.getSet(ElementName.LAYOUT, 0);
		final VJDFAttributeMap pbs = bs.getPartMapVector();
		assertEquals(3, pbs.size());
		final VString vv = pbs.getPartValues("BinderySignatureID", true);
		assertEquals(3, vv.size());
		final JDFAttributeMap vlo = lo.getRoot().getXPathValueMap();
		for (final Entry<String, String> e : vlo.entrySet())
		{
			if (e.getKey().endsWith("@BinderySignatureID"))
			{
				assertTrue(vv.contains(e.getValue()));
				vv.remove(e.getValue());
			}
		}
		assertTrue(vv.isEmpty());
	}

	/**
	 *
	 */
	@Test
	void testLayoutPrepMultiBS2()
	{
		final JDFNode n = JDFNode.parseFile(sm_dirTestData + "xjdf/DigitalPrintingMultiPDF_IDP-ICS-1.5.jdf");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		xjdf.write2File(sm_dirTestDataTemp + "lpp2.xjdf");
		final XJDFHelper xh = new XJDFHelper(xjdf);
		final SetHelper bs = xh.getSet(ElementName.BINDERYSIGNATURE, 0);
		final SetHelper lo = xh.getSet(ElementName.LAYOUT, 0);
		final VJDFAttributeMap pbs = bs.getPartMapVector();
		assertEquals(3, pbs.size());
		final VString vv = pbs.getPartValues("BinderySignatureID", true);
		assertEquals(3, vv.size());
		final JDFAttributeMap vlo = lo.getRoot().getXPathValueMap();
		for (final Entry<String, String> e : vlo.entrySet())
		{
			if (e.getKey().endsWith("@BinderySignatureID"))
			{
				assertTrue(vv.contains(e.getValue()));
				vv.remove(e.getValue());
			}
		}
		assertTrue(vv.isEmpty());
	}

	/**
	 *
	 */
	@Test
	void testDeviceDescName()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Imposition);
		final JDFDevice dev = (JDFDevice) n.addResource(ElementName.DEVICE, EnumUsage.Input);
		dev.setDescriptiveName("d1");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);

		assertEquals("d1", new XJDFHelper(xjdf).getSet(ElementName.DEVICE, 0).getResource(0).getDescriptiveName());
		assertEquals("d1", new XJDFHelper(xjdf).getSet(ElementName.DEVICE, 0).getResource(0).getResource().getAttribute(AttributeName.DESCRIPTIVENAME));
	}

	/**
	 *
	 */
	@Test
	void testLayoutDescName()
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
	 * @throws Exception
	 */
	@Test
	void testLayoutExternalImpo() throws Exception
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Stripping);
		final JDFStrippingParams sp = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input);
		sp.appendExternalImpositionTemplate().appendFileSpec("foo");

		final XJDF20 xjdf20 = new XJDF20();
		xjdf20.setMergeLayout(true);
		final KElement xjdf = xjdf20.makeNewJDF(n, null);
		assertEquals("foo", xjdf.getXPathAttribute("ResourceSet/Resource/Layout/FileSpec/@URL", null));
		assertEquals(ElementName.EXTERNALIMPOSITIONTEMPLATE, xjdf.getXPathAttribute("ResourceSet/Resource/Layout/FileSpec/@ResourceUsage", null));
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testLayoutExternalImpoBS() throws Exception
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Stripping);
		final JDFStrippingParams sp = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input);
		for (int i = 0; i < 2; i++)
		{
			((JDFStrippingParams) sp.addPartition(EnumPartIDKey.BinderySignatureName, "BS" + i)).appendExternalImpositionTemplate().appendFileSpec("foo");
		}
		final XJDF20 xjdf20 = new XJDF20();
		xjdf20.setMergeLayout(true);
		final KElement xjdf = xjdf20.makeNewJDF(n, null);
		assertEquals(1, xjdf.getXPathElementVector("ResourceSet/Resource/Layout/FileSpec", 0).size());
	}

	/**
	 *
	 */
	@Test
	void testExternalID()
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
	void testLayoutMedia()
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
	void testLayoutIntentPages()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Product);
		final JDFLayoutIntent li = (JDFLayoutIntent) n.addResource(ElementName.LAYOUTINTENT, EnumUsage.Input);
		li.appendPages().setActual(3);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		assertEquals("3", xjdf.getXPathAttribute("ProductList/Product/Intent/LayoutIntent/@Pages", null));

	}

	/**
	 *
	 */
	@Test
	void testLayoutContent()
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
	void testPlatePaperMedia()
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
	void testAssemblySection()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Stripping);
		final JDFAssembly med = (JDFAssembly) n.addResource(ElementName.ASSEMBLY, EnumUsage.Input);
		final JDFAssembly[] a = new JDFAssembly[2];
		a[0] = (JDFAssembly) med.addPartition(EnumPartIDKey.PartVersion, "loc1");
		a[1] = (JDFAssembly) med.addPartition(EnumPartIDKey.PartVersion, "loc2");
		for (final JDFAssembly as : a)
		{
			as.setOrder(EnumOrder.None);
			as.appendAssemblySection().setAssemblyID("a1");
			as.appendAssemblySection().setAssemblyID("a2");

		}

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		final SetHelper sh = new XJDFHelper(xjdf).getSet(ElementName.ASSEMBLY, EnumUsage.Input);
		assertEquals(4, sh.getRoot().getChildArrayByClass(JDFAssemblySection.class, true, 0).size());

		sh.cleanUp();
		assertEquals(4, sh.getRoot().getChildArrayByClass(JDFAssemblySection.class, true, 0).size());
	}

	/**
	 *
	 */
	@Test
	void testAssemblyCollect()
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
	void testAssemblySectionCollect()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Stripping);
		final JDFAssembly as = (JDFAssembly) n.addResource(ElementName.ASSEMBLY, EnumUsage.Input);
		as.setOrder(EnumOrder.Collecting);
		as.appendAssemblySection().setAssemblyID("a1");
		as.appendAssemblySection().setAssemblyID("a2");
		as.appendAssemblySection().setAssemblyID("a3");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		final SetHelper sh = new XJDFHelper(xjdf).getSet(ElementName.ASSEMBLY, EnumUsage.Input);
		final JDFAssembly asx = (JDFAssembly) sh.getPartition(0).getResource();
		assertEquals("a1", asx.getAssemblySection(0).getAttribute(XJDFConstants.BinderySignatureID));
		assertNull(asx.getAssemblySection(1));
		assertEquals("a2", asx.getAssemblySection(0).getAssemblySection(0).getAttribute(XJDFConstants.BinderySignatureID));
		assertNull(asx.getAssemblySection(0).getAssemblySection(1));
		assertEquals("a3", asx.getAssemblySection(0).getAssemblySection(0).getAssemblySection(0).getAttribute(XJDFConstants.BinderySignatureID));
		assertNull(asx.getAssemblySection(0).getAssemblySection(0).getAssemblySection(1));

		sh.cleanUp();
		assertEquals("a1", asx.getAssemblySection(0).getAttribute(XJDFConstants.BinderySignatureID));
		assertNull(asx.getAssemblySection(1));
		assertEquals("a2", asx.getAssemblySection(0).getAssemblySection(0).getAttribute(XJDFConstants.BinderySignatureID));
		assertNull(asx.getAssemblySection(0).getAssemblySection(1));
		assertEquals("a3", asx.getAssemblySection(0).getAssemblySection(0).getAssemblySection(0).getAttribute(XJDFConstants.BinderySignatureID));
		assertNull(asx.getAssemblySection(0).getAssemblySection(0).getAssemblySection(1));

	}

	/**
	 *
	 */
	@Test
	void testMediaComponentAmount()
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
	void testMediaGrade()
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
	void testMediaPlatePaper()
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
	void testMediaGrain()
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
	void testMediaSetCount()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFMedia med = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		med.setMediaType(EnumMediaType.Paper);
		med.setMediaSetCount(42);

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdfc = conv.convert(n);
		final JDFMedia m = (JDFMedia) xjdfc.getXPathElement("ResourceSet[@Name=\"Media\"]/Resource/Media");
		assertEquals(42, m.getMediaSetCount());
		writeRoundTripX(xjdfc, "MediaSetCount", EnumValidationLevel.Incomplete);
	}

	/**
	 *
	 */
	@Test
	void testMediaUMT()
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
	void testPartAmountPartitions()
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
	public void testMultiNode1()
	{
		JDFElement.setLongID(false);
		final JDFNode product = new JDFDoc(ElementName.JDF).getJDFRoot();
		product.setType(EnumType.Product);
		final JDFLayoutIntent loi = (JDFLayoutIntent) product.addResource(ElementName.LAYOUTINTENT, EnumUsage.Input);
		loi.setSides(ESides.OneSidedFront);
		final JDFNode plateset = product.addCombined(new VString("Imposition Interpreting Rendering ImageSetting", " "));
		final JDFInterpretingParams ip = (JDFInterpretingParams) plateset.addResource(ElementName.INTERPRETINGPARAMS, EnumUsage.Input);
		ip.setPrintQuality(EPrintQuality.Normal);
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
	void testHoleMaking()
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
	void testIdentical()
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
	void testImplicit()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.DigitalPrinting);
		final JDFDigitalPrintingParams dp = (JDFDigitalPrintingParams) node.getCreateResource(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input);
		dp.setPartUsage(EnumPartUsage.Implicit);
		dp.setPageDelivery(EnumPageDelivery.SameOrderFaceDown);
		final JDFMedia m0 = dp.appendMedia();
		m0.setMediaType(EnumMediaType.Paper);
		m0.setDescriptiveName("base media");
		final JDFDigitalPrintingParams dp1 = (JDFDigitalPrintingParams) dp.addPartition(EnumPartIDKey.RunIndex, "0");
		dp1.setPageDelivery(EnumPageDelivery.SameOrderFaceUp);
		final JDFMedia m1 = dp1.appendMedia();
		m1.setMediaType(EnumMediaType.Paper);
		m1.setDescriptiveName("cover media");
		final JDFToXJDF conv = new JDFToXJDF();
		final XJDFHelper xjdf = conv.convertToXJDF(node);
		final SetHelper setd = xjdf.getSet(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input);
		assertEquals(2, setd.size());
	}

	/**
	 *
	 */
	@Test
	void testExplicit()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.DigitalPrinting);
		final JDFDigitalPrintingParams dp = (JDFDigitalPrintingParams) node.getCreateResource(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input);
		dp.setPageDelivery(EnumPageDelivery.SameOrderFaceDown);
		final JDFMedia m0 = dp.appendMedia();
		m0.setMediaType(EnumMediaType.Paper);
		m0.setDescriptiveName("base media");
		final JDFDigitalPrintingParams dp1 = (JDFDigitalPrintingParams) dp.addPartition(EnumPartIDKey.RunIndex, "0");
		dp1.setPageDelivery(EnumPageDelivery.SameOrderFaceUp);
		final JDFMedia m1 = dp1.appendMedia();
		m1.setMediaType(EnumMediaType.Paper);
		m1.setDescriptiveName("cover media");
		final JDFToXJDF conv = new JDFToXJDF();
		final XJDFHelper xjdf = conv.convertToXJDF(node);
		final SetHelper setd = xjdf.getSet(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input);
		assertEquals(1, setd.size());
	}

	/**
	 *
	 */
	@Test
	void testImplicit2()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.DigitalPrinting);
		final JDFDigitalPrintingParams dp = (JDFDigitalPrintingParams) node.getCreateResource(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input);
		dp.setPartUsage(EnumPartUsage.Implicit);
		dp.setPageDelivery(EnumPageDelivery.SameOrderFaceDown);

		final JDFMedia m0 = (JDFMedia) node.getCreateResource(ElementName.MEDIA, EnumUsage.Input);
		dp.refMedia(m0);

		m0.setMediaType(EnumMediaType.Paper);
		m0.setDescriptiveName("base media");
		final JDFDigitalPrintingParams dp1 = (JDFDigitalPrintingParams) dp.addPartition(EnumPartIDKey.RunIndex, "0");
		dp1.setPageDelivery(EnumPageDelivery.SameOrderFaceUp);
		final JDFMedia m1 = (JDFMedia) node.getCreateResource(ElementName.MEDIA, EnumUsage.Input, 1);
		dp.refMedia(m1);
		m1.setMediaType(EnumMediaType.Paper);
		m1.setDescriptiveName("cover media");
		final JDFToXJDF conv = new JDFToXJDF();
		final XJDFHelper xjdf = conv.convertToXJDF(node);
		final SetHelper setd = xjdf.getSet(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input);
		assertEquals(2, setd.size());
		final SetHelper setm = xjdf.getSet(ElementName.MEDIA, null);
		assertEquals(2, setm.size());
		final SetHelper setc = xjdf.getSet(ElementName.COMPONENT, EnumUsage.Input);
		assertEquals(2, setc.size());
	}

	/**
	 *
	 */
	@Test
	void testInsertingIntent()
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
	 *
	 */
	@Test
	void testIntentDescName()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.Product);
		final JDFBindingIntent bi = (JDFBindingIntent) node.addResource(ElementName.BINDINGINTENT, EnumUsage.Input);
		bi.setDescriptiveName("desc");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(node);

		assertEquals("desc", xjdf.getXPathAttribute("ProductList/Product/Intent[@Name=\"BindingIntent\"]/@DescriptiveName", null));
	}

	/**
	 * @return
	 */
	@Test
	void testGlueLine()
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
	 */
	@Test
	void testGeneralID()
	{
		final JDFNode node = JDFNode.createRoot();
		node.setJobID("gid");
		node.setType(EnumType.Product);
		node.setGeneralID("g1", "v1");
		final JDFComponent c = (JDFComponent) node.addResource(ElementName.COMPONENT, EnumUsage.Output);
		c.setComponentType(EnumComponentType.FinalProduct, null);

		final MyPair<BaseXJDFHelper, JDFElement> p = writeRoundTrip(node, "gid");
		assertEquals("v1", p.a.getGeneralID("g1"));
		assertEquals("v1", p.b.getGeneralID("g1"));
	}

	/**
	 * @return
	 */
	@Test
	void testHoleLine()
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
	 * @return
	 */
	@Test
	void testCoilBindingTypes()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.CoilBinding);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(node);
		assertEquals(XJDFConstants.LooseBinding, xjdf.getAttribute(AttributeName.TYPES));
	}

	/**
	 * @return
	 */
	@Test
	void testCoilBindingParams()
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
	 * @return
	 */
	@Test
	void testCoilBindingParamsNOP()
	{
		final JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.CoilBinding);
		final JDFCoilBindingParams cbp = (JDFCoilBindingParams) node.getCreateResource(ElementName.COILBINDINGPARAMS, EnumUsage.Input, null);
		cbp.setColor(EnumNamedColor.Red);
		cbp.setNoOp(true);
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(node);
		assertEquals(XJDFConstants.LooseBinding, xjdf.getAttribute(AttributeName.TYPES));
		assertNull(new XJDFHelper(xjdf).getSet(ElementName.COILBINDINGPARAMS, 0));
		assertNull(new XJDFHelper(xjdf).getSet(XJDFConstants.LooseBindingParams, 0));
	}

	/**
	 * @return
	 */
	@Test
	void testCoilBindingMisc()
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
	 * @return
	 */
	@Test
	void testChannellBindingMisc()
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
	 */
	@Test
	void testCutBlocDescName()
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.Cutting);
		final JDFCuttingParams cp = (JDFCuttingParams) n.addResource(ElementName.CUTTINGPARAMS, EnumUsage.Input);
		final JDFCutBlock cbo = cp.appendCutBlock();
		final JDFXYPair size = new JDFXYPair(10, 20);
		cbo.setBlockSize(size);
		final JDFMatrix m = JDFMatrix.getUnitMatrix();
		final JDFXYPair shift = new JDFXYPair(400, 600);
		m.shift(shift);
		cbo.setBlockTrf(m);
		cbo.setDescriptiveName("desc name");
		cbo.setProductID("pid");
		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);

		final JDFCutBlock cb = (JDFCutBlock) xjdf.getXPathElement("ResourceSet/Resource/CuttingParams/CutBlock");
		assertEquals("pid", cb.getAttribute(XJDFConstants.ExternalID));
		assertEquals("desc name", cb.getDescriptiveName());
	}

	/**
	 * @return
	 */
	@Test
	void testCoilBindingDetails()
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
		assertEquals("42", new XJDFHelper(xjdf).getSet(XJDFConstants.LooseBindingParams, 0).getPartition(0)
				.getXPathValue("LooseBindingParams/CoilBindingDetails/@Diameter"));
	}

	/**
	 * @return
	 */
	@Test
	void testDeprecatedTypes()
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
	void testgetXJDFs1()
	{
		final JDFNode product = prepareProduct();

		product.write2File(sm_dirTestDataTemp + "getXJDFS.jdf");
		final JDFToXJDF conv = new JDFToXJDF();
		final Collection<XJDFHelper> v = conv.getXJDFs(product);
		assertEquals(v.size(), 3);
		for (final XJDFHelper h : v)
		{
			h.writeToFile(sm_dirTestDataTemp + "getXJDFS." + h.getJobPartID() + ".xjdf");
		}
	}

	/**
	 *
	 */
	@Test
	void testGetCombinedFinishing()
	{
		final JDFNode jdf = JDFNode.parseFile(sm_dirTestData + "Folding_sample.jdf");
		final JDFToXJDF conv = new JDFToXJDF();
		final XJDFHelper h = conv.getCombined(jdf);
		h.writeToFile(sm_dirTestDataTemp + "Folding_sample.xjdf");
		assertNotNull(h.getSet(ElementName.CUTTINGPARAMS, 0));
	}

	static JDFNode prepareProduct()
	{
		JDFElement.setLongID(false);
		final JDFNode product = new JDFDoc(ElementName.JDF).getJDFRoot();
		product.setType(EnumType.Product);
		product.addResource(ElementName.COMPONENT, EnumUsage.Output);
		final JDFLayoutIntent loi = (JDFLayoutIntent) product.addResource(ElementName.LAYOUTINTENT, EnumUsage.Input);
		loi.setSides(EnumSides.OneSidedFront);
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
	void testgetCombinedXJDFTypes()
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
	void testgetCombinedXJDFSets()
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
	void testgetCombinedXJDF()
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
	void testgetCombinedComplex()
	{
		final JDFNode product = JDFNode.parseFile(sm_dirTestData + "SampleFiles/MISPrepress-ICS-Complex.jdf");

		final JDFToXJDF conv = new JDFToXJDF();
		final XJDFHelper h = conv.getCombined(product);
		h.writeToFile(sm_dirTestDataTemp + "prepress.xjdf");
	}

	@Test
	void testRemoveAgentFromResource()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();

		final JDFResource runList = n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		runList.setAttribute(AttributeName.NPAGE, "4");
		runList.setAgentName("resource agent name");

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);

		assertNull(xjdf.getXPathAttribute("//ResourceSet[@Name=RunList]/Resource/@AgentName", null));
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	public void tearDown() throws Exception
	{
		XJMFTypeMap.shutDown();
		super.tearDown();
	}

}
