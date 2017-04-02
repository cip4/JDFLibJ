/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import java.io.IOException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoExposedMedia.EnumPlateType;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFCMYKColor;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.elementwalker.EnsureNSUri;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.JDFToXJDF.EnumProcessPartition;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFQueueEntryDef;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.node.NodeIdentifier;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.intent.JDFBindingIntent;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.intent.JDFLayoutIntent;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFCompany;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceSubstitute;
import org.cip4.jdflib.span.JDFSpanBindingType.EnumSpanBindingType;
import org.cip4.jdflib.util.JDFDate;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class XJDFTest extends JDFTestCaseBase
{
	private JDFNode n = null;
	private KElement xjdf = null;
	private JDFAttributeMap sheetMap = null;

	/**
	*
	*/
	@Override
	public void setUp() throws Exception
	{
		KElement.setLongID(false);
		super.setUp();
		n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.Combined);
		n.addTypes(EnumType.InkZoneCalculation);
		n.addTypes(EnumType.ConventionalPrinting);

		sheetMap = new JDFAttributeMap();
		sheetMap.put(EnumPartIDKey.SignatureName, "sig1");
		sheetMap.put(EnumPartIDKey.SheetName, "s1");

		final JDFResource r = n.addResource("ExposedMedia", EnumUsage.Input);
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SignatureName, "sig1");
		final JDFResource r3 = r2.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource m = n.addResource("Media", null);
		final JDFResource m2 = m.addPartition(EnumPartIDKey.SignatureName, "sig1");
		final JDFMedia m3 = (JDFMedia) m2.addPartition(EnumPartIDKey.SheetName, "s1");
		m3.setMediaType(EnumMediaType.Plate);
		r3.refElement(m3);
		final JDFColorantControl cc = (JDFColorantControl) n.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		cc.getCreateColorantParams().setSeparations(new VString("Spot1 Spot2", null));
		cc.getCreateColorantOrder().setSeparations(new VString("Cyan Magenta Yellow Black Spot1 Spot2", null));
		final JDFMedia mPaper = (JDFMedia) n.addResource("Media", EnumUsage.Input);
		mPaper.setMediaType(EnumMediaType.Paper);

		r3.setProductID("P1");
		final JDFExposedMedia xm0 = (JDFExposedMedia) r3;
		xm0.setPlateType(EnumPlateType.Dummy);
		final JDFCustomerInfo ci = n.appendCustomerInfo();
		ci.setCustomerJobName("foo");
		xjdf = new XJDF20().makeNewJDF(n, null);
	}

	/**
	 *
	 */
	@Test
	public void testToXJDF()
	{
		xjdf = new XJDF20().makeNewJDF(n, null);

		final JDFNode n2 = new JDFDoc("JDF").getJDFRoot();
		n2.setType(EnumType.ProcessGroup);
		n2.addTypes(EnumType.InkZoneCalculation);
		n2.addTypes(EnumType.ConventionalPrinting);
		xjdf.setAttribute("JobPartID", null);

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(n2.getOwnerDocument_JDFElement());
		final JDFDoc d2 = xCon.convert(xjdf);
		assertNotNull(d2);
		final JDFNode nConv = d2.getJDFRoot();
		assertNotNull(nConv);
		JDFExposedMedia xm = (JDFExposedMedia) nConv.getResource("ExposedMedia", EnumUsage.Input, 0);
		final JDFResourceLink rl = nConv.getLink(xm, null);
		assertNotNull(rl);
		xm = (JDFExposedMedia) rl.getTarget();
		assertEquals(xm.getProductID(), "P1");
		assertEquals(xm.getPlateType(), EnumPlateType.Dummy);
	}

	/**
	 *
	 */
	@Test
	public void testToXJDFTypes()
	{
		xjdf = new XJDF20().makeNewJDF(n, null);
		String s = xjdf.getAttribute(AttributeName.TYPES);
		assertFalse(s.startsWith(" "));
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFTypes()
	{
		xjdf = new XJDF20().makeNewJDF(n, null);
		xjdf.setXPathValue("ProductList/Product/@IsRoot", "true");
		xjdf.setXPathValue("@Types", "ConventionalPrinting");

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d2 = xCon.convert(xjdf);
		assertNotNull(d2.getJDFRoot().getElement("JDF"));
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFTypesNS()
	{
		xjdf = new XJDF20().makeNewJDF(n, null);
		xjdf.setXPathValue("ProductList/Product/@IsRoot", "true");
		xjdf.setXPathValue("@Types", "ConventionalPrinting");
		EnsureNSUri ns = new EnsureNSUri();
		ns.addNS("xjdf", JDFElement.getSchemaURL(2, 0));
		ns.addAlias("", "xjdf");
		ns.walkTree(xjdf, null);
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d2 = xCon.convert(xjdf);
		assertNotNull(d2.getJDFRoot().getElement("JDF"));
	}

	/**
	 *
	 */
	@Test
	public void testToXJDFMulti()
	{
		n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.ProcessGroup);

		JDFNode n2 = n.addJDFNode(EnumType.ProcessGroup);
		n2.addTypes(EnumType.InkZoneCalculation);
		n2.addTypes(EnumType.ConventionalPrinting);
		xjdf.setAttribute("JobPartID", null);

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(n2.getOwnerDocument_JDFElement());
		final JDFDoc d2 = xCon.convert(xjdf);
		assertNotNull(d2);
		JDFNode nConv = d2.getJDFRoot();
		nConv = (JDFNode) nConv.getvJDFNode(EnumType.ProcessGroup.getName(), null, true).get(0);
		assertNotNull(nConv);
		JDFExposedMedia xm = (JDFExposedMedia) nConv.getResource("ExposedMedia", EnumUsage.Input, 0);
		assertNotNull(xm);
		final JDFResourceLink rl = nConv.getLink(xm, null);
		assertNotNull(rl);
		xm = (JDFExposedMedia) rl.getTarget();
		assertEquals(xm.getProductID(), "P1");
		assertEquals(xm.getPlateType(), EnumPlateType.Dummy);
	}

	/**
	 *
	 */
	@Test
	public void testToXJDFProcessList()
	{
		n = JDFDoc.parseFile(sm_dirTestData + "job4.jdf").getJDFRoot();
		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setSingleNode(false);
		xjdf20.setProcessPart(EnumProcessPartition.processList);
		xjdf = xjdf20.makeNewJDF(n, null);
		KElement procList = xjdf.getXPathElement("ProcessList");
		assertNotNull(procList);
		xjdf.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "job4.xjdf", 2, false);
	}

	/**
	 */
	@Test
	public void testXJDFRunList()
	{
		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setMergeRunList(true);
		n = new JDFDoc("JDF").getJDFRoot();
		JDFRunList rl = (JDFRunList) n.addResource("RunList", EnumUsage.Input);
		JDFRunList rlr1 = rl.addPDF("test.pdf", 0, 2);
		rlr1.getLayoutElement().setElementType(JDFLayoutElement.EnumElementType.Page);

		rl.addPDF("test2.pdf", 3, 6);

		xjdf = xjdf20.makeNewJDF(n, null);
		KElement rlSet = xjdf.getXPathElement("ResourceSet[@Name=\"RunList\"]");
		assertNotNull(rlSet);
		KElement rl2 = rlSet.getXPathElement("Resource/RunList");
		assertNotNull(rl2);
		assertEquals("0 2", rl2.getAttribute(AttributeName.PAGES));
		assertNull(rl2.getElement("LayoutElement"));

		XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		JDFDoc d = xc.convert(xjdf);
		n = d.getJDFRoot();

	}

	/**
	 */
	@Test
	public void testXJDFRangeList()
	{
		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setMergeRunList(true);
		xjdf20.setConvertTilde(true);
		n = new JDFDoc("JDF").getJDFRoot();
		JDFRunList rl = (JDFRunList) n.addResource("RunList", EnumUsage.Input);
		JDFRunList rlr1 = rl.addPDF("test.pdf", 0, 2);
		JDFIntegerRangeList pages = rlr1.getPages();
		pages.append(33, 44);
		pages.append(55);
		pages.append(66, 77);
		rlr1.setPages(pages);
		rlr1.getLayoutElement().setElementType(JDFLayoutElement.EnumElementType.Page);

		xjdf = xjdf20.makeNewJDF(n, null);
		KElement rlSet = xjdf.getXPathElement("ResourceSet[@Name=\"RunList\"]");
		assertNotNull(rlSet);
		KElement rl2 = rlSet.getXPathElement("Resource/RunList");
		assertNotNull(rl2);
		assertEquals("0 2 33 44 55 55 66 77", rl2.getAttribute(AttributeName.PAGES));
		assertNull(rl2.getElement("LayoutElement"));

		XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		xc.setConvertTilde(true);
		JDFDoc d = xc.convert(xjdf);
		n = d.getJDFRoot();
		JDFRunList leaf = (JDFRunList) n.getResource(ElementName.RUNLIST, null, 0).getLeaves(false).get(0);
		assertEquals("0 ~ 2 33 ~ 44 55 66 ~ 77", leaf.getAttribute(AttributeName.PAGES));

	}

	/**
	 */
	@Test
	public void testXJDFNiCi()
	{
		XJDF20 xjdf20 = new XJDF20();
		n = new JDFDoc(ElementName.JDF).getJDFRoot();
		for (int i = 1; i < 4; i++)
		{
			JDFNodeInfo ni = (JDFNodeInfo) n.addResource("NodeInfo", null);
			ni.setNodeStatus(EnumNodeStatus.Cleanup);
			n.setXPathAttribute("AncestorPool/Ancestor[" + i + "]/NodeInforRef/@rRef", ni.getID());
			xjdf = xjdf20.makeNewJDF(n, null);
		}
		assertNotNull(xjdf.getXPathElement("ResourceSet/Resource/NodeInfo"));
	}

	/**
	 *
	 */
	@Test
	public void testMergeStripping()
	{
		n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Stripping);
		JDFRunList rl = (JDFRunList) n.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);
		assertNotNull(rl);
		JDFStrippingParams sp = (JDFStrippingParams) n.appendMatchingResource(ElementName.STRIPPINGPARAMS, EnumProcessUsage.AnyInput, null);
		JDFBinderySignature bs = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, null, null, null, null, null, null);
		JDFLayout lo = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Output);
		assertNotNull(lo);
		sp.refBinderySignature(bs);
		sp.appendPosition();

		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setMergeLayout(true);

		xjdf = xjdf20.makeNewJDF(n, null);
		assertEquals(xjdf.getXPathElementVector("//StrippingParams", -1).size(), 0);
		assertEquals(xjdf.getXPathElementVector("//Layout", -1).size(), 1);
		xjdf.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "mergeStripping.xjdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	public void testMergeStrippingPartition()
	{
		n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.Stripping);
		JDFRunList rl = (JDFRunList) n.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);
		assertNotNull(rl);
		JDFStrippingParams sp = (JDFStrippingParams) n.appendMatchingResource(ElementName.STRIPPINGPARAMS, EnumProcessUsage.AnyInput, null);
		JDFBinderySignature bs = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, null, null, null, null, null, null);
		bs.setFoldCatalog("F4-1");
		JDFLayout lo = (JDFLayout) n.addResource("Layout", EnumUsage.Output);
		lo = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "Sheet1");
		lo = (JDFLayout) lo.addPartition(EnumPartIDKey.Side, "Front");
		for (int i = 0; i < 4; i++)
		{
			JDFContentObject co = lo.appendContentObject();
			co.setOrd(i);
		}
		assertNotNull(lo);
		sp = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.SheetName, "Sheet1");
		sp.refBinderySignature(bs);
		sp.appendPosition();

		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setMergeLayout(true);

		xjdf = xjdf20.makeNewJDF(n, null);
		assertEquals(xjdf.getXPathElementVector("//StrippingParams", -1).size(), 0);
		assertEquals(xjdf.getXPathElementVector("//Layout", -1).size(), 2);
		xjdf.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "mergeStrippingParts.xjdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	public void testJMFMessageRoot()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Query, JDFMessage.EnumType.Status);
		XJDF20 xjdf20 = new XJDF20();
		xjdf = xjdf20.makeNewJMF(jmf);
		KElement firstChildElement = xjdf.getFirstChildElement();
		if (xjdf20.isTypeSafeMessage())
		{
			assertEquals(firstChildElement.getLocalName(), XJDFConstants.Header);
			assertEquals(firstChildElement.getNextSiblingElement().getLocalName(), "QueryStatus");
		}
		else
		{
			assertEquals(firstChildElement.getFirstChildElement().getLocalName(), "StatusQuery");
		}
	}

	/**
	 *
	 * @return
	 */
	XJDF20 getJMFConverter()
	{
		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setTypeSafeMessage(true);
		return xjdf20;
	}

	/**
	 *
	 */
	@Test
	public void testJMFToXJDF()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Resource);
		final JDFResourceInfo ri = jmf.getCreateSignal(0).appendResourceInfo();
		ri.setResourceName("ExposedMedia");
		final JDFExposedMedia xm = (JDFExposedMedia) ri.appendResource("ExposedMedia");
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put("SignatureName", "Sig1");
		partMap.put("SheetName", "S1");
		partMap.put("Side", "Front");
		partMap.put("Separation", "Black");
		ri.appendAmountPool().appendPartAmount(partMap).setActualAmount(1, null);
		final JDFExposedMedia xmPart = (JDFExposedMedia) xm.getCreatePartition(partMap, new VString("SignatureName SheetName Side Separation", null));
		xmPart.appendMedia();
		xjdf = getJMFConverter().makeNewJMF(jmf);
		final JDFPart pNew = (JDFPart) xjdf.getXPathElement("SignalResource/ResourceInfo/ResourceSet/Resource/Part");

		partMap.remove(AttributeName.SIGNATURENAME);
		assertEquals(pNew.getPartMap(), partMap);
	}

	/**
	 *
	 */
	@Test
	public void testJMFHoldQEToXJDF()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.HoldQueueEntry);
		final JDFQueueEntryDef qed = jmf.getCreateCommand(0).appendQueueEntryDef();
		qed.setQueueEntryID("QEID");
		XJDF20 jmfConverter = getJMFConverter();
		xjdf = jmfConverter.makeNewJMF(jmf);
		if (jmfConverter.isAbstractMessage())
			assertEquals(xjdf.getXPathAttribute("QueryModifyQueueEntry/ModifyQueueEntryParams/@Operation", null), "Hold");
		else
			assertEquals(xjdf.getXPathAttribute("CommandModifyQueueEntry/ModifyQueueEntryParams/@Operation", null), "Hold");

	}

	/**
	*
	 */
	@Test
	public void testToXJDFWithProduct()
	{
		final JDFNode nP = new JDFDoc("JDF").getJDFRoot();
		nP.setType(EnumType.Product);
		nP.setDescriptiveName("desc");
		JDFLayoutIntent loe = (JDFLayoutIntent) nP.addResource("LayoutIntent", EnumUsage.Input);
		loe.appendDimensions().setActual(new JDFXYPair(3, 4));
		JDFBindingIntent bi = (JDFBindingIntent) nP.addResource("BindingIntent", EnumUsage.Input);
		bi.appendBindingType().setActual(EnumSpanBindingType.ChannelBinding);
		n = (JDFNode) nP.copyElement(n, null);
		final JDFResource c = n.addResource("Component", EnumUsage.Output);
		nP.linkResource(c, EnumUsage.Output, null);

		xjdf = new XJDF20().makeNewJDF(n, null);
		assertNotNull(xjdf.getXPathElement("ProductList/Product/Intent[@Name=\"LayoutIntent\"]"));
		assertNotNull(xjdf.getXPathElement("ProductList/Product/Intent[@Name=\"BindingIntent\"]"));
		assertEquals(xjdf.getXPathAttribute("ProductList/Product/@DescriptiveName", null), "desc");
	}

	/**
	*
	 */
	@Test
	public void testToXJDFWithBigProduct()
	{
		final JDFNode n = JDFDoc.parseFile(sm_dirTestData + "job4.jdf").getJDFRoot();
		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setSingleNode(false);
		xjdf = xjdf20.makeNewJDF(n, null);
		xjdf.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "job4.xjdf", 2, false);
	}

	/**
	*
	 */
	@Test
	public void testToXJDFIntermediate()
	{
		final JDFNode nP = new JDFDoc("JDF").getJDFRoot();
		nP.setType(EnumType.Product);
		nP.setDescriptiveName("desc");
		nP.addResource("LayoutIntent", EnumUsage.Input);
		n = (JDFNode) nP.copyElement(n, null);
		final JDFResource c = n.addResource("Component", EnumUsage.Output);
		nP.linkResource(c, EnumUsage.Output, null);

		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setSingleNode(true);
		xjdf = xjdf20.makeNewJDF(nP, null);
		assertFalse(xjdf.toString().contains("ExposedMedia"));
		xjdf20.setSingleNode(false);
		xjdf = xjdf20.makeNewJDF(nP, null);
		assertTrue(xjdf.toString().contains("ExposedMedia"));
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFWithProduct()
	{
		testToXJDFWithProduct();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		final JDFNode root = d.getJDFRoot();
		assertEquals(root.getType(), "Product");
		assertEquals(root.getDescriptiveName(), "desc");
		assertNotNull(root.getResource(ElementName.LAYOUTINTENT, EnumUsage.Input, 0));
		assertNotNull(root.getResource(ElementName.BINDINGINTENT, EnumUsage.Input, 0));
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFWithSubProductProduct()
	{
		XJDFHelper h = new XJDFHelper("j1", "root", null);
		h.setTypes("Product");
		ProductHelper rootP = h.appendProduct();
		ProductHelper subP = h.appendProduct();
		rootP.setRoot();
		rootP.setChild(subP, 0);
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		KElement xRoot = h.getRoot();
		final JDFDoc d = xCon.convert(xRoot);
		final JDFNode root = d.getJDFRoot();
		assertEquals(root.getType(), "Product");
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFMedia()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		d.write2File(sm_dirTestDataTemp + "Media.xjdf.jdf", 2, false);
		final JDFNode root = d.getJDFRoot();
		JDFResourceLink rl = root.getLink(0, "ExposedMedia", null, null);
		JDFExposedMedia xm = (JDFExposedMedia) rl.getTarget();
		JDFMedia m = xm.getMedia();
		assertNotNull(m);
		assertNotSame(m, m.getResourceRoot());
		assertNotNull(((JDFMedia) m.getResourceRoot()).getMediaType());
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFCC()
	{

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(xjdf);
		d.write2File(sm_dirTestDataTemp + "CC.xjdf.jdf", 2, false);
		final JDFNode root = d.getJDFRoot();
		JDFResourceLink rl = root.getLink(0, "ColorantControl", null, null);
		JDFColorantControl cc = (JDFColorantControl) rl.getTarget();
		assertNull(cc.getAttribute(ElementName.COLORANTORDER, null, null));

	}

	/**
	 *
	 */
	@Test
	public void testResetProduct()
	{
		XJDFHelper h = new XJDFHelper("J1", "root", null);
		h.setTypes("Product");
		h.appendProduct().setRoot();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		JDFDoc d = xCon.convert(h.getRoot());
		for (int i = 1; i < 5; i++)
		{
			XJDFHelper h2 = new XJDFHelper("J1", "sub" + i, null);
			h2.setTypes("Product");
			h2.appendProduct().setRoot();
			xCon.resetProduct();
			d = xCon.convert(h2.getRoot());
			assertNotNull(d.getJDFRoot().getJobPart(new NodeIdentifier("J1", "sub" + i, null)));
		}
		assertNotNull(d);
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFWithProductMulti()
	{
		n.setJobPartID("p1");
		testToXJDFWithProduct();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		JDFDoc d = xCon.convert(xjdf);
		JDFNode root = d.getJDFRoot();
		assertEquals(root.getType(), "Product");
		n.setJobPartID("p2");
		testToXJDFWithProduct();
		d = xCon.convert(xjdf);
		root = d.getJDFRoot();
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFWithProductMultiProduct()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		XJDFHelper h = new XJDFHelper("jobID", "p0", null);
		ProductHelper ph0 = h.appendProduct();
		ph0.setRoot();
		assertNotNull(ph0);
		h.getRoot().setAttribute("Types", "Product");
		JDFDoc d = xCon.convert(h.getRoot());

		XJDFHelper h1 = new XJDFHelper("jobID", "p1", null);
		h1.getRoot().setAttribute("Types", "Product");
		h1.appendProduct();
		d = xCon.convert(h1.getRoot());
		XJDFHelper h2 = new XJDFHelper("jobID", "p2", null);
		h2.appendProduct();
		h2.getRoot().setAttribute("Types", "Product");
		d = xCon.convert(h2.getRoot());
		assertNotNull(d);

	}

	/**
	 *
	 */
	@Test
	public void testNamedFeatures()
	{

		n.setNamedFeatures(new VString("k1 v1 k2 v2", null));
		xjdf = new XJDF20().makeNewJDF(n, null);
		assertEquals(xjdf.numChildElements(ElementName.GENERALID, null), 2);
		for (int i = 1; i < 3; i++)
		{
			final JDFGeneralID gi = (JDFGeneralID) xjdf.getElement(ElementName.GENERALID, null, i - 1);
			assertEquals(gi.getIDUsage(), "k" + i);
			assertEquals(gi.getIDValue(), "v" + i);
		}
		assertNull(xjdf.getAttribute(AttributeName.NAMEDFEATURES, null, null));

	}

	/**
	*
	 */
	@Test
	public void testColorPool()
	{
		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, EnumUsage.Input);
		cp.appendColorWithName("Black", null).setCMYK(new JDFCMYKColor(0, 0, 0, 1));
		cp.appendColorWithName("Yellow", null).setCMYK(new JDFCMYKColor(0, 0, 1, 0));
		xjdf = new XJDF20().makeNewJDF(n, null);
		assertNotNull(xjdf.getXPathElement("ResourceSet[@Name=\"Color\"]/Resource/Part[@Separation=\"Black\"]"));
	}

	/**
	*
	 */
	@Test
	public void testNamespace()
	{
		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, EnumUsage.Input);
		cp.appendColorWithName("Black", null).setCMYK(new JDFCMYKColor(0, 0, 0, 1));
		cp.appendColorWithName("Yellow", null).setCMYK(new JDFCMYKColor(0, 0, 1, 0));
		xjdf = new XJDF20().makeNewJDF(n, null);
		assertEquals(XJDF20.getSchemaURL(), xjdf.getNamespaceURI());
		KElement xPathElement = xjdf.getXPathElement("ResourceSet[@Name=\"Color\"]/Resource/Part[@Separation=\"Black\"]");
		assertNotNull(xPathElement);
		assertEquals(XJDF20.getSchemaURL(), xPathElement.getNamespaceURI());
	}

	/**
	*
	 */
	@Test
	public void testNamespacePrefix()
	{
		xjdf = new XMLDoc("xjdf:XJDF", JDFElement.getSchemaURL(2, 0)).getRoot();
		assertEquals(XJDF20.getSchemaURL(), xjdf.getNamespaceURI());
		KElement xPathElement = xjdf.getCreateXPathElement("xjdf:ResourceSet[@Name=\"RunList\"]/xjdf:Resource/xjdf:RunList/xjdf:LayoutElement");
		assertNotNull(xPathElement);
		JDFDoc d = new XJDFToJDFConverter(null).convert(xjdf);
		JDFNode jdfRoot = d.getJDFRoot();
		assertNull(jdfRoot.getXPathElement("//RunList/RunList"));
		assertTrue(jdfRoot.isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 *
	 */
	@Test
	public void testColorPoolRef()
	{
		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, null);
		cp.appendColorWithName("Black", null).setCMYK(new JDFCMYKColor(0, 0, 0, 1));
		cp.appendColorWithName("Yellow", null).setCMYK(new JDFCMYKColor(0, 0, 1, 0));
		n.getResource("ExposedMedia", null, 0).refElement(cp);
		xjdf = new XJDF20().makeNewJDF(n, null);
		assertNotNull(xjdf.getXPathElement("ResourceSet[@Name=\"Color\"]/Resource/Part[@Separation=\"Black\"]"));
		assertEquals(xjdf.getXPathElement("ResourceSet[@Name=\"Color\"]").numChildElements("Resource", null), 2);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testRefElement() throws Exception
	{
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, null);
		n.getResource("ExposedMedia", null, 0).refElement(m);
		final JDFCustomerInfo ci = n.getCustomerInfo();
		// final JDFResource r =
		JDFCompany company = ci.appendCompany();
		company.setOrganizationName("Acme");
		company.makeRootResource(null, null, true);

		xjdf = new XJDF20().makeNewJDF(n, null);
		assertNotNull(xjdf.getXPathElement("ResourceSet[@Name=\"Media\"]"));
		assertEquals(xjdf.getXPathAttribute("ResourceSet[@Name=\"Media\"]/Resource/@ID", null), xjdf.getXPathAttribute("ResourceSet[@Name=\"ExposedMedia\"]/Resource/ExposedMedia/@MediaRef", null));

		assertNull(xjdf.getXPathElement("ResourceSet[@Name=\"Company\"]"));
		assertNotNull(xjdf.getXPathElement("ResourceSet[@Name=\"CustomerInfo\"]/Resource/CustomerInfo/Company"));

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testSeparationList() throws Exception
	{
		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, EnumUsage.Input);
		cp.appendColorWithName("Black", null).setCMYK(new JDFCMYKColor(0, 0, 0, 1));
		cp.appendColorWithName("Yellow", null).setCMYK(new JDFCMYKColor(0, 0, 1, 0));
		final JDFColorantControl cc = (JDFColorantControl) n.getCreateResource(ElementName.COLORANTCONTROL, EnumUsage.Input, 0);
		cc.getCreateColorantOrder().setSeparations(new VString("Cyan a b CC", null));
		xjdf = new XJDF20().makeNewJDF(n, null);
		assertEquals("Cyan a b CC", xjdf.getXPathAttribute("ResourceSet[@Name=\"ColorantControl\"]/Resource/ColorantControl/@ColorantOrder", null));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testResourceAudit() throws Exception
	{
		JDFMedia m = (JDFMedia) n.getResource("Media", EnumUsage.Input, 0);
		assertNotNull(m);
		JDFResourceAudit ra = n.getCreateAuditPool().addResourceAudit("foo");
		JDFResourceLink rl = ra.addNewOldLink(true, m, EnumUsage.Input);
		JDFAttributeMap map = new JDFAttributeMap(sheetMap);
		map.put(AttributeName.CONDITION, "Good");
		rl.setActualAmount(4200., map);
		map.put(AttributeName.CONDITION, "Waste");
		rl.setActualAmount(300., map);
		ra.setPartMap(sheetMap);
		xjdf = new XJDF20().makeNewJDF(n, null);
		xjdf.getElement(ElementName.AUDITPOOL);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testColorspaceSubstitute() throws Exception
	{
		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, EnumUsage.Input);
		cp.appendColorWithName("Black", null).setCMYK(new JDFCMYKColor(0, 0, 0, 1));
		cp.appendColorWithName("Yellow", null).setCMYK(new JDFCMYKColor(0, 0, 1, 0));
		final JDFColorantControl cc = (JDFColorantControl) n.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		final JDFColorSpaceSubstitute css = cc.appendColorSpaceSubstitute();
		css.appendSeparationSpec().setName("Spot1");
		css.appendPDLResourceAlias();
		xjdf = new XJDF20().makeNewJDF(n, null);
		assertNull(xjdf.getXPathElement("ResourceSet[@Name=\"ColorantControl\"]/Resource/ColorantControl/ColorSpaceSubstitute"));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testCombinedProcesIndex() throws Exception
	{
		n = new JDFDoc("JDF").getJDFRoot();
		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, EnumUsage.Input);
		n.getLink(cp, null).setCombinedProcessIndex(3);
		final JDFColorantControl cc = (JDFColorantControl) n.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		cc.setProcessColorModel("CMYK");
		n.getLink(cc, null).setCombinedProcessIndex(2);
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		m.setMediaColorNameDetails("Pink");
		n.getLink(m, null).setCombinedProcessIndex(1);
		xjdf = new XJDF20().makeNewJDF(n, null);
		assertNull(xjdf.getXPathAttribute("ResourceSet[@Name=\"Media\"]/@CombinedProcessIndex", null));
		assertNull(xjdf.getXPathAttribute("ResourceSet[@Name=\"NodeInfo\"]/@CombinedProcessIndex", null));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testColorNameBlank() throws Exception
	{
		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, EnumUsage.Input);
		cp.appendColorWithName("Black By Night", null).setCMYK(new JDFCMYKColor(0, 0, 0, 1));
		final JDFColorantControl cc = (JDFColorantControl) n.getCreateResource(ElementName.COLORANTCONTROL, EnumUsage.Input, 0);
		cc.getCreateColorantParams().setSeparations(new VString("Black By Night,Cyan", ","));
		xjdf = new XJDF20().makeNewJDF(n, null);
		assertEquals("Black_By_Night", xjdf.getXPathAttribute("ResourceSet[@Name=\"Color\"]/Resource/Part/@Separation", null));
		assertEquals("Black_By_Night Cyan", xjdf.getXPathAttribute("ResourceSet[@Name=\"ColorantControl\"]/Resource/ColorantControl/@ColorantParams", null));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testContainer() throws Exception
	{

		final JDFFileSpec fs1 = (JDFFileSpec) n.addResource("FileSpec", EnumUsage.Input);
		// final JDFFileSpec fsc =
		fs1.appendContainer().appendFileSpec();
		xjdf = new XJDF20().makeNewJDF(n, null);
		assertNull(xjdf.getXPathAttribute("ResourceSet[@Usage=\"Input\"]/Resource/FileSpec/@ContainerRef", null));
		assertNull(xjdf.getXPathAttribute("ResourceSet[@Usage=\"Input\"]/Resource/FileSpec/Container", null));

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testDependencies() throws Exception
	{

		final JDFLayoutElement le = (JDFLayoutElement) n.addResource(ElementName.LAYOUTELEMENT, EnumUsage.Input);
		final JDFLayoutElement fsc = le.appendDependencies().appendLayoutElement();
		fsc.setDescriptiveName("dep");
		le.getDependencies().appendLayoutElement();
		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setMergeRunList(false);
		xjdf = xjdf20.makeNewJDF(n, null);
		assertNotNull(xjdf.getXPathAttribute("ResourceSet[@Usage=\"Input\"]/Resource/LayoutElement/@Dependencies", null));

		xjdf20 = new XJDF20();
		xjdf20.setMergeRunList(true);
		xjdf = xjdf20.makeNewJDF(n, null);
		assertNotNull(xjdf.getXPathAttribute("ResourceSet[@Usage=\"Input\"]/Resource/RunList/@Dependencies", null));
	}

	/**
	 *
	 */
	@Test
	public void testToXJDFCustomerInfo()
	{
		xjdf = new XJDF20().makeNewJDF(n, null);
		assertNotNull(xjdf.getXPathElement("ResourceSet/Resource/CustomerInfo"));

	}

	/**
	 *
	 */
	@Test
	public void testToXJDFMedia()
	{
		xjdf = new XJDF20().makeNewJDF(n, null);
		assertNotNull(xjdf.getXPathElement("ResourceSet/Resource/Media"));
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testToXJDFLayout() throws Exception
	{
		n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.Stripping);
		JDFStrippingParams sp = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input);
		JDFMedia paper = (JDFMedia) n.addResource(ElementName.MEDIA, null);
		paper.setMediaType(EnumMediaType.Paper);
		JDFMedia plate = (JDFMedia) n.addResource(ElementName.MEDIA, null);
		plate.setMediaType(EnumMediaType.Plate);
		JDFStrippingParams sp1 = (JDFStrippingParams) sp.getCreatePartition(sheetMap, new VString("SignatureName SheetName", null));
		sp.refElement(paper);
		sp.refElement(plate);
		VString vbs = new VString("bs1 bs2", null);
		for (String bs : vbs)
		{
			JDFStrippingParams sp11 = (JDFStrippingParams) sp1.addPartition(EnumPartIDKey.BinderySignatureName, bs);
			JDFBinderySignature bs1 = sp11.appendBinderySignature();
			bs1.appendSignatureCell().setFrontPages(new JDFIntegerList("0 2 4"));
			bs1.appendSignatureCell().setFrontPages(new JDFIntegerList("6 8 10"));
			bs1.makeRootResource(null, null, true);
			sp11.appendPosition();
			sp11.appendPosition();
			for (int i = 0; i < 2; i++)
			{
				JDFStrippingParams spcell1 = (JDFStrippingParams) sp11.addPartition(EnumPartIDKey.CellIndex, "" + i);
				spcell1.appendStripCellParams().setSpine(42.);
			}
		}
		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setMergeLayout(true);
		xjdf = xjdf20.makeNewJDF(n, null);
		assertNull(xjdf.getChildByTagName("StrippingParams", null, 0, null, false, true));
		xjdf.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "mergelayout.xjdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDF()
	{
		testColorPool();
		final JDFDoc d2 = new XJDFToJDFConverter(null).convert(xjdf);
		assertNotNull(d2);
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFRunList()
	{
		XJDFHelper h = new XJDFHelper(null);
		xjdf = h.getRoot();
		xjdf.setAttribute("JobPartID", "Root");
		SetHelper sh = h.getCreateResourceSet("RunList", EnumUsage.Input);
		sh.getSet().setID("R");
		sh.getCreatePartition(new JDFAttributeMap("Run", "r1"), true).getResource().appendElement("FileSpec");
		XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		conv.convert(xjdf);
		h = new XJDFHelper(null);
		xjdf = h.getRoot();
		xjdf.setAttribute("JobPartID", "Root");
		sh = h.getCreateSet("Resource", "RunList", EnumUsage.Input);
		sh.getSet().setID("R");
		sh.getCreatePartition(new JDFAttributeMap("Run", "r2"), true).getResource().appendElement("FileSpec");
		JDFDoc d = conv.convert(xjdf);
		assertNotNull(d);

	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFNoNamespace()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		xjdf = new XMLDoc("XJDF", null).getRoot();
		xjdf.setXPathAttribute("ResourceSet/Resource/RunList/FileSpec/@URL", "http://foo/bar.pdf");
		xjdf.setXPathAttribute("ResourceSet/@Usage", "Input");
		final JDFDoc d = xCon.convert(xjdf);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		assertTrue(root.getResource("RunList", EnumUsage.Input, 0) instanceof JDFRunList);
	}

	/**
	 * @throws IOException
	 * @throws SAXException
	 *
	 */
	@Test
	public void testFromXJDFIntentNoNames() throws SAXException, IOException
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		xjdf = new XMLDoc("XJDF", null).getRoot();
		xjdf.setXPathAttribute("ProductList/Product/Intent/LayoutIntent/@Pages", "16");
		final JDFDoc d = xCon.convert(xjdf);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFResource li = root.getResource("LayoutIntent", EnumUsage.Input, 0);
		assertTrue(li instanceof JDFLayoutIntent);
		assertEquals(JDFIntentResource.guessActual(li, "Pages"), "16");
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFNiCiLink()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		xjdf = new XMLDoc("XJDF", null).getRoot();
		xjdf.setXPathAttribute("ResourceSet/Resource/NodeInfo/@End", new JDFDate(System.currentTimeMillis() + 1000 * 24 * 3600 * 3).getDateTimeISO());
		xjdf.setXPathAttribute("ResourceSet/Resource/NodeInfo/@AmountGood", "100");
		xjdf.setXPathAttribute("ResourceSet[2]/Resource/CustomerInfo/@CustomerID", "KundenIdentNummer");
		final JDFDoc d = xCon.convert(xjdf);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		assertTrue(root.getResource("CustomerInfo", EnumUsage.Input, 0) instanceof JDFCustomerInfo);
		JDFResource nodeInfo = root.getResource("NodeInfo", EnumUsage.Input, 0);
		assertTrue(nodeInfo instanceof JDFNodeInfo);
		JDFResourceLink rl = root.getLink(nodeInfo, EnumUsage.Input);
		assertNotNull(rl);
		assertTrue(rl.getAmount(null) == 100);
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFComment()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		xjdf = new XMLDoc("XJDF", null).getRoot();
		xjdf.appendElement("Comment").setText("foo");
		xjdf.getCreateXPathElement("ProductList/Product/Comment").setText("bar");
		final JDFDoc d = xCon.convert(xjdf);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		assertNotNull(root.getComment(1).getText());
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFProductComponentTransfer()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		xjdf = new XMLDoc("XJDF", null).getRoot();
		xjdf.setXPathAttribute("ProductList/Product/@ProductID", "ID_FOO");
		xjdf.setXPathAttribute("ProductList/Product/@AssemblyIDs", "Ass_ID");
		final JDFDoc d = xCon.convert(xjdf);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFComponent component = (JDFComponent) root.getResource(ElementName.COMPONENT, null, 0);
		assertEquals(component.getProductID(), "ID_FOO");
		assertEquals(component.getAssemblyIDs().get(0), "Ass_ID");
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFProductComment()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		xjdf = new XMLDoc(XJDFConstants.XJDF, null).getRoot();
		xjdf.getCreateXPathElement("ProductList/Product/Comment").setText("bar");
		final JDFDoc d = xCon.convert(xjdf);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		assertEquals(root.getComment(0).getText(), "bar");
	}

	/**
	 * @return
	 *
	 */
	public JDFNode testFromXJDFCompany()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		xjdf = new XMLDoc("XJDF", null).getRoot();
		KElement c = xjdf.appendElement("ResourceSet");
		c.setAttribute("Name", "Contact");
		c.setAttribute("Usage", "Input");
		c.appendElement("Resource").appendElement(ElementName.CONTACT).appendElement(ElementName.COMPANY).setAttribute("CompanyID", "company_id");
		final JDFDoc d = xCon.convert(xjdf);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFContact contact = (JDFContact) root.getResource("Contact", EnumUsage.Input, 0);
		assertEquals(contact.getCompany().getProductID(), "company_id");
		return root;
	}

	/**
	 *
	 *
	 */
	@Test
	public void testToXJDFCompany()
	{
		JDFNode n = testFromXJDFCompany();
		JDFContact contact = (JDFContact) n.getResource("Contact", EnumUsage.Input, 0);
		JDFCompany company = contact.getCompany();
		company.makeRootResource(null, null, true);
		xjdf = new XJDF20().makeNewJDF(n, null);
		assertNull(xjdf.getXPathElement("ResourceSet/Resource/Company"));
		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource/Contact/Company/@CompanyID", null), "company_id");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testToXJDFPartition()
	{
		JDFNode n = new JDFDoc("JDF").getJDFRoot();
		JDFExposedMedia xm = (JDFExposedMedia) n.addResource("ExposedMedia", EnumUsage.Output);
		JDFResource r = xm.addPartition(EnumPartIDKey.SheetName, "S1");
		JDFResource m = n.addResource("Media", EnumUsage.Input);
		xm.refElement(m);
		r = r.addPartition(EnumPartIDKey.Side, "Front");
		r.addPartitions(EnumPartIDKey.Separation, new VString("C M Y K", null));
		xjdf = new XJDF20().makeNewJDF(n, null);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testToXJDFSubPartition()
	{
		JDFNode n = new JDFDoc("JDF").getJDFRoot();
		JDFExposedMedia xm = (JDFExposedMedia) n.addResource("ExposedMedia", EnumUsage.Output);
		JDFResource r = xm.addPartition(EnumPartIDKey.SignatureName, "Sig1");
		r = r.addPartition(EnumPartIDKey.SheetName, "S1");
		JDFResourceLink rl = n.getLink(xm, null);
		JDFAttributeMap mPart = new JDFAttributeMap(EnumPartIDKey.SheetName, "S1");
		mPart.put(EnumPartIDKey.SignatureName, "Sig1");
		rl.setPartMap(mPart);
		JDFResource m = n.addResource("Media", EnumUsage.Input);
		xm.refElement(m);
		r = r.addPartition(EnumPartIDKey.Side, "Front");
		r.addPartitions(EnumPartIDKey.Separation, new VString("C M Y K", null));
		xjdf = new XJDF20().makeNewJDF(n, null);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testFromXJDFPartition()
	{
		testToXJDFPartition();
		XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		JDFDoc d = conv.convert(xjdf);
		assertNotNull(d);
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFColorIntentLegacy()
	{
		for (int i = 0; i < 3; i += 2)
		{
			final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
			xjdf = new XMLDoc("XJDF", null).getRoot();
			xjdf.setAttribute("Types", "Product");
			xjdf.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@NumColors", "4/1");
			xjdf.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@Coatings", "DullVarnish");
			xjdf.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@CoatingsBack", "GlossVarnish");
			if (i != 0)
			{
				xjdf.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@ColorsUsed", "Spot1 Spot");
				xjdf.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@ColorsUsedBack", "Spot2 Spot");
			}
			final JDFDoc d = xCon.convert(xjdf);
			assertNotNull(d);
			JDFNode root = d.getJDFRoot();
			JDFColorIntent ci = (JDFColorIntent) root.getResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
			JDFColorIntent cif = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Front"), null);
			JDFColorIntent cib = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Back"), null);
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
	public void testFromXJDFColorIntent44()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		xjdf = new XMLDoc("XJDF", null).getRoot();
		xjdf.setAttribute("Types", "Product");
		xjdf.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@NumColors", "4/4");
		final JDFDoc d = xCon.convert(xjdf);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFColorIntent ci = (JDFColorIntent) root.getResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		JDFColorIntent cif = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Front"), EnumPartUsage.Explicit);
		JDFColorIntent cib = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Back"), EnumPartUsage.Explicit);
		assertNull(cif);
		assertNull(cib);
		assertNull(ci.getElement("ColorsUsedBack"));
		assertEquals(ci.getNumColors(), 4);
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFColorIntentExplicitUsed()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		xjdf = new XMLDoc("XJDF", null).getRoot();
		xjdf.setAttribute("Types", "Product");
		xjdf.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@ColorsUsed", "Black Spot1");
		xjdf.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@ColorsUsedBack", "Black");
		final JDFDoc d = xCon.convert(xjdf);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFColorIntent ci = (JDFColorIntent) root.getResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		JDFColorIntent cif = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Front"), EnumPartUsage.Explicit);
		JDFColorIntent cib = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Back"), EnumPartUsage.Explicit);
		assertNotNull(cif);
		assertNotNull(cib);
		assertNull(cib.getElement("ColorsUsedBack"));
		assertEquals(cif.getColorsUsed().getSeparations().size(), 2);
		assertEquals(cib.getColorsUsed().getSeparations().size(), 1);
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFColorIntentFront()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		xjdf = new XMLDoc("XJDF", null).getRoot();
		xjdf.setAttribute("Types", "Product");
		xjdf.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@NumColors", "4/0");
		final JDFDoc d = xCon.convert(xjdf);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFColorIntent ci = (JDFColorIntent) root.getResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		JDFColorIntent cif = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Front"), null);
		JDFColorIntent cib = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Back"), EnumPartUsage.Explicit);
		assertNull(ci.getColorsUsed());
		assertNull(cib);
		assertEquals(cif.getNumColors(), 4);
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDFResourceID()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		xjdf = new XMLDoc("XJDF", null).getRoot();
		xjdf.setXPathAttribute("ResourceSet[1]/Resource/CustomerInfo/@ContactRefs", "cr");
		xjdf.setXPathAttribute("ResourceSet[2]/Resource[@ID=\"cr\"]/Contact/@ContacTypes", "Customer");
		final JDFDoc d = xCon.convert(xjdf);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFCustomerInfo ci = (JDFCustomerInfo) root.getResource("CustomerInfo", EnumUsage.Input, 0);
		assertNotNull(ci.getContact());
	}

	/**
	 *
	 */
	@Test
	public void testFromXJDF2Sheets()
	{
		testColorPool();
		KElement eNext = xjdf.getOwnerDocument_KElement().clone().getRoot();
		eNext.setAttribute("JobPartID", "eNext");
		xjdf.setXPathAttribute("ProductList/Product/@JobID", "jid");
		final XJDFToJDFConverter converter = new XJDFToJDFConverter(null);
		final KElement e2 = xjdf.getOwnerDocument_KElement().clone().getRoot();
		final VElement v = e2.getChildrenByTagName(null, null, new JDFAttributeMap("SheetName", "s1"), false, false, -1);
		for (int i = 0; i < v.size(); i++)
		{
			v.get(i).setAttribute("SheetName", "s2");
		}
		final JDFDoc d2a = converter.convert(xjdf);
		final JDFDoc d2 = converter.convert(e2);
		assertNotNull(d2);
		assertEquals(d2a.getRoot(), d2.getRoot());
		final JDFDoc d3 = converter.convert(eNext);
		d3.write2File(sm_dirTestDataTemp + "xjdf2.jdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	public void testRefFirst()
	{
		final JDFNode n0 = n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		n = n0.addJDFNode(EnumType.ImageSetting);

		final JDFResource r = n.addResource("ExposedMedia", null, EnumUsage.Output, null, n0, null, null);
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SignatureName, "sig1");
		final JDFResource r3 = r2.addPartition(EnumPartIDKey.SheetName, "s1");
		r3.setProductID("P1");
		final JDFExposedMedia xm0 = (JDFExposedMedia) r3;
		xm0.setPlateType(EnumPlateType.Dummy);
		final JDFMedia m = (JDFMedia) n.addResource("Media", EnumUsage.Input);
		m.setMediaType(EnumMediaType.Plate);
		r.refElement(m);
		final KElement out = new XJDF20().makeNewJDF(n, null);
		assertEquals("Input", out.getXPathAttribute("ResourceSet[@Name=\"Media\"]/@Usage", null), "Input");
	}

	/**
	 * @see junit.framework.TestCase#toString()
	 * @return duh string...
	*/
	@Override
	public String toString()
	{
		return super.toString() + xjdf;
	}

}
