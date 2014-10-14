/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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

import java.io.File;
import java.io.IOException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoExposedMedia.EnumPlateType;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
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
import org.cip4.jdflib.elementwalker.EnsureNSUri;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFQueueEntryDef;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.node.NodeIdentifier;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFStrippingParams;
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
	private KElement e = null;
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
		e = new XJDF20().makeNewJDF(n, null);
	}

	/**
	 * 
	 */
	@Test
	public void testToXJDF()
	{
		e = new XJDF20().makeNewJDF(n, null);

		final JDFNode n2 = new JDFDoc("JDF").getJDFRoot();
		n2.setType(EnumType.ProcessGroup);
		n2.addTypes(EnumType.InkZoneCalculation);
		n2.addTypes(EnumType.ConventionalPrinting);
		e.setAttribute("JobPartID", null);

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(n2.getOwnerDocument_JDFElement());
		final JDFDoc d2 = xCon.convert(e);
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
		e = new XJDF20().makeNewJDF(n, null);
		String s = e.getAttribute(AttributeName.TYPES);
		assertFalse(s.startsWith(" "));
	}

	/**
	 * 
	 */
	@Test
	public void testFromXJDFTypes()
	{
		e = new XJDF20().makeNewJDF(n, null);
		e.setXPathValue("ProductList/Product/@IsRoot", "true");
		e.setXPathValue("@Types", "ConventionalPrinting");

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d2 = xCon.convert(e);
		assertNotNull(d2.getJDFRoot().getElement("JDF"));
	}

	/**
	 * 
	 */
	@Test
	public void testFromXJDFTypesNS()
	{
		e = new XJDF20().makeNewJDF(n, null);
		e.setXPathValue("ProductList/Product/@IsRoot", "true");
		e.setXPathValue("@Types", "ConventionalPrinting");
		EnsureNSUri ns = new EnsureNSUri();
		ns.addNS("xjdf", JDFElement.getSchemaURL(2, 0));
		ns.addAlias("", "xjdf");
		ns.walkTree(e, null);
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d2 = xCon.convert(e);
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
		e.setAttribute("JobPartID", null);

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(n2.getOwnerDocument_JDFElement());
		final JDFDoc d2 = xCon.convert(e);
		assertNotNull(d2);
		JDFNode nConv = d2.getJDFRoot();
		nConv = (JDFNode) nConv.getvJDFNode(EnumType.ProcessGroup.getName(), null, true).get(0);
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
	public void testToXJDFProcessList()
	{
		n = JDFDoc.parseFile(sm_dirTestData + "job4.jdf").getJDFRoot();
		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setSingleNode(false);
		e = xjdf20.makeNewJDF(n, null);
		KElement procList = e.getXPathElement("ProcessList");
		assertNotNull(procList);
		e.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "job4.xjdf", 2, false);
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

		e = xjdf20.makeNewJDF(n, null);
		KElement rlSet = e.getXPathElement("ParameterSet[@Name=\"RunList\"]");
		assertNotNull(rlSet);
		KElement rl2 = rlSet.getXPathElement("Parameter/RunList");
		assertNotNull(rl2);
		assertEquals("0 ~ 2", rl2.getAttribute(AttributeName.PAGES));
		assertNull(rl2.getElement("LayoutElement"));

		XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		JDFDoc d = xc.convert(e);
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

		e = xjdf20.makeNewJDF(n, null);
		KElement rlSet = e.getXPathElement("ParameterSet[@Name=\"RunList\"]");
		assertNotNull(rlSet);
		KElement rl2 = rlSet.getXPathElement("Parameter/RunList");
		assertNotNull(rl2);
		assertEquals("0 2 33 44 55 55 66 77", rl2.getAttribute(AttributeName.PAGES));
		assertNull(rl2.getElement("LayoutElement"));

		XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		xc.setConvertTilde(true);
		JDFDoc d = xc.convert(e);
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
		n = new JDFDoc("JDF").getJDFRoot();
		for (int i = 1; i < 4; i++)
		{
			JDFNodeInfo ni = (JDFNodeInfo) n.addResource("NodeInfo", null);
			n.setXPathAttribute("AncestorPool/Ancestor[" + i + "]/NodeInforRef/@rRef", ni.getID());
			e = xjdf20.makeNewJDF(n, null);
		}
		assertNotNull(e.getXPathElement("ParameterSet/Parameter/NodeInfo"));
	}

	/**
	 * 
	 */
	@Test
	public void testMergeStripping()
	{
		n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.Stripping);
		JDFRunList rl = (JDFRunList) n.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);
		assertNotNull(rl);
		JDFStrippingParams sp = (JDFStrippingParams) n.appendMatchingResource(ElementName.STRIPPINGPARAMS, EnumProcessUsage.AnyInput, null);
		JDFBinderySignature bs = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, null, null, null, null, null, null);
		JDFLayout lo = (JDFLayout) n.addResource("Layout", EnumUsage.Output);
		assertNotNull(lo);
		sp.refBinderySignature(bs);
		sp.appendPosition();

		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setMergeLayout(true);

		e = xjdf20.makeNewJDF(n, null);
		assertEquals(e.getXPathElementVector("//StrippingParams", -1).size(), 0);
		assertEquals(e.getXPathElementVector("//Layout", -1).size(), 1);
		e.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "mergeStripping.xjdf", 2, false);
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

		e = xjdf20.makeNewJDF(n, null);
		assertEquals(e.getXPathElementVector("//StrippingParams", -1).size(), 0);
		assertEquals(e.getXPathElementVector("//Layout", -1).size(), 2);
		e.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "mergeStrippingParts.xjdf", 2, false);
	}

	/**
	 * 
	 */
	@Test
	public void testJMFMessageRoot()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Query, JDFMessage.EnumType.Status);
		XJDF20 xjdf20 = new XJDF20();
		e = xjdf20.makeNewJMF(jmf);
		if (xjdf20.isTypeSafeMessage())
			assertEquals(e.getFirstChildElement().getLocalName(), "QueryStatus");
		else
			assertEquals(e.getFirstChildElement().getFirstChildElement().getLocalName(), "StatusQuery");
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
		e = getJMFConverter().makeNewJMF(jmf);
		final JDFPart pNew = (JDFPart) e.getXPathElement("SignalResource/ResourceInfo/ResourceSet/Resource/Part");
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
		e = jmfConverter.makeNewJMF(jmf);
		if (jmfConverter.isAbstractMessage())
			assertEquals(e.getXPathAttribute("QueryModifyQueueEntry/ModifyQueueEntryParams/@Operation", null), "Hold");
		else
			assertEquals(e.getXPathAttribute("CommandModifyQueueEntry/ModifyQueueEntryParams/@Operation", null), "Hold");

	}

	/**
	 * 	@deprecated
	 */
	@Deprecated
	public void xjdfSchemaTest()
	{
		final XJDFSchemaWalker sw = new XJDFSchemaWalker();
		final File in = new File(sm_dirTestSchema + "JDFResource.xsd");
		final File out = new File(sm_dirTestDataTemp + "schema/xjdf/JDFResource.xsd");
		sw.newFile(in, out);
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
		nP.addResource("LayoutIntent", EnumUsage.Input);
		nP.addResource("BindingIntent", EnumUsage.Input);
		n = (JDFNode) nP.copyElement(n, null);
		final JDFResource c = n.addResource("Component", EnumUsage.Output);
		nP.linkResource(c, EnumUsage.Output, null);

		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathElement("ProductList/Product/Intent[@Name=\"LayoutIntent\"]"));
		assertNotNull(e.getXPathElement("ProductList/Product/Intent[@Name=\"BindingIntent\"]"));
		assertEquals(e.getXPathAttribute("ProductList/Product/@DescriptiveName", null), "desc");
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
		e = xjdf20.makeNewJDF(n, null);
		e.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "job4.xjdf", 2, false);
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
		e = xjdf20.makeNewJDF(nP, null);
		assertFalse(e.toString().contains("ExposedMedia"));
		xjdf20.setSingleNode(false);
		e = xjdf20.makeNewJDF(nP, null);
		assertTrue(e.toString().contains("ExposedMedia"));
	}

	/**
	 *  
	 */
	@Test
	public void testFromXJDFWithProduct()
	{
		testToXJDFWithProduct();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(e);
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
		final JDFDoc d = xCon.convert(e);
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
		final JDFDoc d = xCon.convert(e);
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
		JDFDoc d = xCon.convert(e);
		JDFNode root = d.getJDFRoot();
		assertEquals(root.getType(), "Product");
		n.setJobPartID("p2");
		testToXJDFWithProduct();
		d = xCon.convert(e);
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
		e = new XJDF20().makeNewJDF(n, null);
		assertEquals(e.numChildElements(ElementName.GENERALID, null), 2);
		for (int i = 1; i < 3; i++)
		{
			final JDFGeneralID gi = (JDFGeneralID) e.getElement(ElementName.GENERALID, null, i - 1);
			assertEquals(gi.getIDUsage(), "k" + i);
			assertEquals(gi.getIDValue(), "v" + i);
		}
		assertNull(e.getAttribute(AttributeName.NAMEDFEATURES, null, null));

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
		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathElement("ParameterSet[@Name=\"Color\"]/Parameter/Part[@Separation=\"Black\"]"));
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
		e = new XJDF20().makeNewJDF(n, null);
		assertEquals(XJDF20.getSchemaURL(), e.getNamespaceURI());
		KElement xPathElement = e.getXPathElement("ParameterSet[@Name=\"Color\"]/Parameter/Part[@Separation=\"Black\"]");
		assertNotNull(xPathElement);
		assertEquals(XJDF20.getSchemaURL(), xPathElement.getNamespaceURI());
	}

	/**
	*
	 */
	@Test
	public void testNamespacePrefix()
	{
		e = new XMLDoc("xjdf:XJDF", JDFElement.getSchemaURL(2, 0)).getRoot();
		assertEquals(XJDF20.getSchemaURL(), e.getNamespaceURI());
		KElement xPathElement = e.getCreateXPathElement("xjdf:ParameterSet[@Name=\"RunList\"]/xjdf:Parameter/xjdf:RunList/xjdf:LayoutElement");
		assertNotNull(xPathElement);
		JDFDoc d = new XJDFToJDFConverter(null).convert(e);
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
		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathElement("ParameterSet[@Name=\"Color\"]/Parameter/Part[@Separation=\"Black\"]"));
		assertEquals(e.getXPathElement("ParameterSet[@Name=\"Color\"]").numChildElements("Parameter", null), 2);
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
		ci.appendCompany().makeRootResource(null, null, true);

		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathElement("ResourceSet[@Name=\"Media\"]"));
		assertEquals(e.getXPathAttribute("ResourceSet[@Name=\"Media\"]/Resource/@ID", null), e.getXPathAttribute("ResourceSet[@Name=\"ExposedMedia\"]/Resource/ExposedMedia/@MediaRef", null));

		assertNull(e.getXPathElement("ParameterSet[@Name=\"Company\"]"));
		assertNotNull(e.getXPathElement("ParameterSet[@Name=\"CustomerInfo\"]/Parameter/CustomerInfo/Company"));

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
		e = new XJDF20().makeNewJDF(n, null);
		assertEquals("Cyan a b CC", e.getXPathAttribute("ParameterSet[@Name=\"ColorantControl\"]/Parameter/ColorantControl/@ColorantOrder", null));
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
		e = new XJDF20().makeNewJDF(n, null);
		JDFAuditPool ap = (JDFAuditPool) e.getElement(ElementName.AUDITPOOL);
		assertNotNull(ap);
		JDFResourceAudit raNew = (JDFResourceAudit) ap.getAudit(0, EnumAuditType.ResourceAudit, null, null);
		assertEquals(raNew.getXPathAttribute("ResourceAmount/AmountPool/PartAmount[1]/@ActualAmount", null), "4200");
		assertEquals(raNew.getXPathAttribute("ResourceAmount/AmountPool/PartAmount[2]/@ActualAmount", null), "300");
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
		e = new XJDF20().makeNewJDF(n, null);
		assertEquals("Spot1", e.getXPathAttribute("ParameterSet[@Name=\"ColorantControl\"]/Parameter/ColorantControl/ColorSpaceSubstitute/@SeparationSpec", null));
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
		n.getLink(cc, null).setCombinedProcessIndex(2);
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		n.getLink(m, null).setCombinedProcessIndex(1);
		e = new XJDF20().makeNewJDF(n, null);
		assertEquals(e.getXPathElement("ParameterSet[@Name=\"Color\"]"), e.getXPathElement("ParameterSet[@Name=\"ColorantControl\"]").getNextSiblingElement());
		assertEquals(e.getXPathElement("ResourceSet[@Name=\"Media\"]"), e.getXPathElement("ParameterSet[@Name=\"ColorantControl\"]").getPreviousSiblingElement());
		assertNull(e.getXPathAttribute("ResourceSet[@Name=\"Media\"]/@CombinedProcessIndex", null));
		assertNull(e.getXPathAttribute("ParameterSet[@Name=\"NodeInfo\"]/@CombinedProcessIndex", null));
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
		e = new XJDF20().makeNewJDF(n, null);
		assertEquals("Black_By_Night", e.getXPathAttribute("ParameterSet[@Name=\"Color\"]/Parameter/Part/@Separation", null));
		assertEquals("Black_By_Night Cyan", e.getXPathAttribute("ParameterSet[@Name=\"ColorantControl\"]/Parameter/ColorantControl/@ColorantParams", null));
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
		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathAttribute("ParameterSet[@Usage=\"Input\"]/Parameter/FileSpec/@ContainerRef", null));

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
		e = xjdf20.makeNewJDF(n, null);
		assertNotNull(e.getXPathAttribute("ParameterSet[@Usage=\"Input\"]/Parameter/LayoutElement/@Dependencies", null));

		xjdf20 = new XJDF20();
		xjdf20.setMergeRunList(true);
		e = xjdf20.makeNewJDF(n, null);
		assertNotNull(e.getXPathAttribute("ParameterSet[@Usage=\"Input\"]/Parameter/RunList/@Dependencies", null));
	}

	/**
	 *  
	 */
	@Test
	public void testToXJDFCustomerInfo()
	{
		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathElement("ParameterSet/Parameter/CustomerInfo"));

	}

	/**
	 *  
	 */
	@Test
	public void testToXJDFMedia()
	{
		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathElement("ResourceSet/Resource/Media"));
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
		e = xjdf20.makeNewJDF(n, null);
		assertNull(e.getChildByTagName("StrippingParams", null, 0, null, false, true));
		e.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "mergelayout.xjdf", 2, false);
	}

	/**
	 *  
	 */
	@Test
	public void testFromXJDF()
	{
		testColorPool();
		final JDFDoc d2 = new XJDFToJDFConverter(null).convert(e);
		assertNotNull(d2);
	}

	/**
	 *  
	 */
	@Test
	public void testFromXJDFRunList()
	{
		XJDFHelper h = new XJDFHelper(null);
		e = h.getRoot();
		e.setAttribute("JobPartID", "Root");
		SetHelper sh = h.getCreateSet("Parameter", "RunList", EnumUsage.Input);
		sh.getSet().setID("R");
		sh.getCreatePartition(new JDFAttributeMap("Run", "r1"), true).getResource().appendElement("FileSpec");
		XJDFToJDFConverter conv = new XJDFToJDFConverter(null);
		conv.convert(e);
		h = new XJDFHelper(null);
		e = h.getRoot();
		e.setAttribute("JobPartID", "Root");
		sh = h.getCreateSet("Parameter", "RunList", EnumUsage.Input);
		sh.getSet().setID("R");
		sh.getCreatePartition(new JDFAttributeMap("Run", "r2"), true).getResource().appendElement("FileSpec");
		JDFDoc d = conv.convert(e);
		assertNotNull(d);

	}

	/**
	 *  
	 */
	@Test
	public void testFromXJDFNoNamespace()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		e = new XMLDoc("XJDF", null).getRoot();
		e.setXPathAttribute("ParameterSet/Parameter/RunList/FileSpec/@URL", "http://foo/bar.pdf");
		e.setXPathAttribute("ParameterSet/@Usage", "Input");
		final JDFDoc d = xCon.convert(e);
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
		e = new XMLDoc("XJDF", null).getRoot();
		e.setXPathAttribute("ProductList/Product/Intent/LayoutIntent/@Pages", "16");
		final JDFDoc d = xCon.convert(e);
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
		e = new XMLDoc("XJDF", null).getRoot();
		e.setXPathAttribute("ParameterSet/Parameter/NodeInfo/@End", new JDFDate(System.currentTimeMillis() + 1000 * 24 * 3600 * 3).getDateTimeISO());
		e.setXPathAttribute("ParameterSet/Parameter/NodeInfo/@AmountGood", "100");
		e.setXPathAttribute("ParameterSet[2]/Parameter/CustomerInfo/@CustomerID", "KundenIdentNummer");
		final JDFDoc d = xCon.convert(e);
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
		e = new XMLDoc("XJDF", null).getRoot();
		e.appendElement("Comment").setText("foo");
		e.getCreateXPathElement("ProductList/Product/Comment").setText("bar");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		assertEquals(root.getComment(1).getText(), "bar");
	}

	/**
	 *  
	 */
	@Test
	public void testFromXJDFProductComponentTransfer()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		e = new XMLDoc("XJDF", null).getRoot();
		e.setXPathAttribute("ProductList/Product/@ProductID", "ID_FOO");
		e.setXPathAttribute("ProductList/Product/@AssemblyIDs", "Ass_ID");
		final JDFDoc d = xCon.convert(e);
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
		e = new XMLDoc("XJDF", null).getRoot();
		e.getCreateXPathElement("ProductList/Product/Comment").setText("bar");
		final JDFDoc d = xCon.convert(e);
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
		e = new XMLDoc("XJDF", null).getRoot();
		KElement c = e.appendElement("ParameterSet");
		c.setAttribute("Name", "Contact");
		c.setAttribute("Usage", "Input");
		c.appendElement("Parameter").appendElement(ElementName.CONTACT).appendElement(ElementName.COMPANY).setAttribute("CompanyID", "company_id");
		final JDFDoc d = xCon.convert(e);
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
		e = new XJDF20().makeNewJDF(n, null);
		assertNull(e.getXPathElement("ParameterSet/Parameter/Company"));
		assertEquals(e.getXPathAttribute("ParameterSet/Parameter/Contact/Company/@CompanyID", null), "company_id");
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
		e = new XJDF20().makeNewJDF(n, null);
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
		e = new XJDF20().makeNewJDF(n, null);
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
		JDFDoc d = conv.convert(e);
		assertNotNull(d);
	}

	/**
	 *  
	 */
	@Test
	public void testFromXJDFColorIntent()
	{
		for (int i = 0; i < 3; i += 2)
		{
			final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
			e = new XMLDoc("XJDF", null).getRoot();
			e.setAttribute("Types", "Product");
			e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@NumColors", "4/1");
			e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@Coatings", "DullVarnish");
			e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@CoatingsBack", "GlossVarnish");
			if (i != 0)
			{
				e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@ColorsUsed", "Spot1 Black Spot");
				e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@ColorsUsedBack", "Spot2 Spot");
			}
			final JDFDoc d = xCon.convert(e);
			assertNotNull(d);
			JDFNode root = d.getJDFRoot();
			JDFColorIntent ci = (JDFColorIntent) root.getResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
			JDFColorIntent cif = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Front"), null);
			JDFColorIntent cib = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Back"), null);
			assertNull(ci.getColorsUsed());
			assertEquals(cib.getColorsUsed().getSeparations().size(), 1 + i);
			assertEquals(cif.getColorsUsed().getSeparations().size(), 4 + i);
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
		e = new XMLDoc("XJDF", null).getRoot();
		e.setAttribute("Types", "Product");
		e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@NumColors", "4/4");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFColorIntent ci = (JDFColorIntent) root.getResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		JDFColorIntent cif = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Front"), null);
		JDFColorIntent cib = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Back"), null);
		assertNull(cif);
		assertNull(cib);
		assertNull(ci.getElement("ColorsUsedBack"));
		assertEquals(ci.getColorsUsed().getSeparations().size(), 4);
	}

	/**
	 *  
	 */
	@Test
	public void testFromXJDFColorIntentFront()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		e = new XMLDoc("XJDF", null).getRoot();
		e.setAttribute("Types", "Product");
		e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@NumColors", "4/0");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFColorIntent ci = (JDFColorIntent) root.getResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		JDFColorIntent cif = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Front"), null);
		JDFColorIntent cib = (JDFColorIntent) ci.getPartition(new JDFAttributeMap("Side", "Back"), null);
		assertNull(ci.getColorsUsed());
		assertNull(cib);
		assertEquals(cif.getColorsUsed().getSeparations().size(), 4);
	}

	/**
	 *  
	 */
	@Test
	public void testFromXJDFParameterID()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		e = new XMLDoc("XJDF", null).getRoot();
		e.setXPathAttribute("ParameterSet[1]/Parameter/CustomerInfo/@ContactRefs", "cr");
		e.setXPathAttribute("ParameterSet[2]/Parameter[@ID=\"cr\"]/Contact/@ContacTypes", "Customer");
		final JDFDoc d = xCon.convert(e);
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
		KElement eNext = e.getOwnerDocument_KElement().clone().getRoot();
		eNext.setAttribute("JobPartID", "eNext");
		e.setXPathAttribute("ProductList/Product/@JobID", "jid");
		final XJDFToJDFConverter converter = new XJDFToJDFConverter(null);
		final KElement e2 = e.getOwnerDocument_KElement().clone().getRoot();
		final VElement v = e2.getChildrenByTagName(null, null, new JDFAttributeMap("SheetName", "s1"), false, false, -1);
		for (int i = 0; i < v.size(); i++)
		{
			v.get(i).setAttribute("SheetName", "s2");
		}
		final JDFDoc d2a = converter.convert(e);
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
		final JDFResource m = n.addResource("Media", EnumUsage.Input);
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
		return super.toString() + e;
	}

}
