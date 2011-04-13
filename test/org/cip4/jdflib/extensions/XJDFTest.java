/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFCMYKColor;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
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
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceSubstitute;
import org.cip4.jdflib.util.JDFDate;
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
	public void testToXJDF()
	{
		e = new XJDF20().makeNewJDF(n, null);

		final JDFNode n2 = new JDFDoc("JDF").getJDFRoot();
		n2.setType(EnumType.ConventionalPrinting);
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
	public void testToXJDFTypes()
	{
		e = new XJDF20().makeNewJDF(n, null);
		String s = e.getAttribute(AttributeName.TYPES);
		assertFalse(s.startsWith(" "));
	}

	/**
	 * 
	 */
	public void testToXJDFMulti()
	{
		n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.ProcessGroup);

		JDFNode n2 = n.addJDFNode(EnumType.ConventionalPrinting);
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
	 */
	public void testXJDFRunList()
	{
		XJDF20 xjdf20 = new XJDF20();
		xjdf20.bMergeRunList = true;
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
		assertNull(rl2.getElement("LayoutElement"));

		XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		JDFDoc d = xc.convert(e);
		n = d.getJDFRoot();

	}

	/**
	 */
	public void testXJDFNiCi()
	{
		XJDF20 xjdf20 = new XJDF20();
		n = new JDFDoc("JDF").getJDFRoot();
		JDFNodeInfo ni = (JDFNodeInfo) n.addResource("NodeInfo", null);
		n.setXPathAttribute("AncestorPool/Ancestor/NodeInforRef/@rRef", ni.getID());
		e = xjdf20.makeNewJDF(n, null);
		assertNotNull(e.getXPathElement("ParameterSet/Parameter/NodeInfo"));
	}

	/**
	 */
	public void testMergeStripping()
	{
		n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.Stripping);
		JDFRunList rl = (JDFRunList) n.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);
		JDFStrippingParams sp = (JDFStrippingParams) n.appendMatchingResource(ElementName.STRIPPINGPARAMS, EnumProcessUsage.AnyInput, null);
		JDFBinderySignature bs = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, null, null, null, null, null, null);
		JDFLayout lo = (JDFLayout) n.addResource("Layout", EnumUsage.Output);
		sp.refBinderySignature(bs);
		sp.appendPosition();

		XJDF20 xjdf20 = new XJDF20();
		xjdf20.bMergeLayout = true;

		e = xjdf20.makeNewJDF(n, null);
		assertEquals(e.getXPathElementVector("//StrippingParams", -1).size(), 0);
		assertEquals(e.getXPathElementVector("//Layout", -1).size(), 1);
		e.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "mergeStripping.xjdf", 2, false);

	}

	/**
	 */
	public void testJMFToXJDF()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, org.cip4.jdflib.jmf.JDFMessage.EnumType.Resource);
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
		e = new XJDF20().makeNewJMF(jmf);
		final JDFPart pNew = (JDFPart) e.getXPathElement("Signal/ResourceInfo/ResourceSet/Resource/Part");
		assertEquals(pNew.getPartMap(), partMap);

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
		xjdf20.bSingleNode = true;
		e = xjdf20.makeNewJDF(nP, null);
		assertFalse(e.toString().contains("ExposedMedia"));
		xjdf20.bSingleNode = false;
		e = xjdf20.makeNewJDF(nP, null);
		assertTrue(e.toString().contains("ExposedMedia"));
	}

	/**
	 *  
	 */
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
		assertEquals(raNew.getElement("ResourceAmount").getAttribute("ActualAmountGood"), "4200");
		assertEquals(raNew.getElement("ResourceAmount").getAttribute("ActualAmountWaste"), "300");
	}

	/**
	 * @throws Exception
	 */
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
	public void testDependencies() throws Exception
	{

		final JDFLayoutElement le = (JDFLayoutElement) n.addResource(ElementName.LAYOUTELEMENT, EnumUsage.Input);
		final JDFLayoutElement fsc = le.appendDependencies().appendLayoutElement();
		fsc.setDescriptiveName("dep");
		le.getDependencies().appendLayoutElement();
		XJDF20 xjdf20 = new XJDF20();
		xjdf20.bMergeRunList = false;
		e = xjdf20.makeNewJDF(n, null);
		assertNotNull(e.getXPathAttribute("ParameterSet[@Usage=\"Input\"]/Parameter/LayoutElement/@Dependencies", null));

		xjdf20 = new XJDF20();
		xjdf20.bMergeRunList = true;
		e = xjdf20.makeNewJDF(n, null);
		assertNotNull(e.getXPathAttribute("ParameterSet[@Usage=\"Input\"]/Parameter/RunList/@Dependencies", null));
	}

	/**
	 *  
	 */
	public void testToXJDFCustomerInfo()
	{
		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathElement("ParameterSet/Parameter/CustomerInfo"));

	}

	/**
	 *  
	 */
	public void testToXJDFLayout()
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
			bs1.makeRootResource(null, null, true);
			sp11.appendPosition();
			sp11.appendPosition();
			for (int i = 0; i < 2; i++)
			{
				JDFStrippingParams spcell1 = (JDFStrippingParams) sp11.addPartition(EnumPartIDKey.CellIndex, "" + i);
				spcell1.appendStripCellParams();
			}
		}
		XJDF20 xjdf20 = new XJDF20();
		xjdf20.bMergeLayout = true;
		e = xjdf20.makeNewJDF(n, null);
		assertNull(e.getChildByTagName("StrippingParams", null, 0, null, false, true));

	}

	/**
	 *  
	 */
	public void testFromXJDF()
	{
		testColorPool();
		final JDFDoc d2 = new XJDFToJDFConverter(null).convert(e);
		assertNotNull(d2);
	}

	/**
	 *  
	 */
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
	public void testFromXJDFComment()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		e = new XMLDoc("XJDF", null).getRoot();
		e.appendElement("Comment").setText("foo");
		e.getCreateXPathElement("ProductList/Product/Comment").setText("bar");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		assertEquals(root.getComment(0).getText(), "bar");
	}

	/**
	 *  
	 */
	public void testFromXJDFColorIntent()
	{
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		e = new XMLDoc("XJDF", null).getRoot();
		e.setAttribute("Types", "Product");
		e.setXPathAttribute("ProductList/Product/Intent[@Name=\"ColorIntent\"]/ColorIntent/@NumColors", "4/1");
		final JDFDoc d = xCon.convert(e);
		assertNotNull(d);
		JDFNode root = d.getJDFRoot();
		JDFColorIntent ci = (JDFColorIntent) root.getResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		assertEquals(ci.getColorsUsed().getSeparations().size(), 4);
	}

	/**
	 *  
	 */
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
