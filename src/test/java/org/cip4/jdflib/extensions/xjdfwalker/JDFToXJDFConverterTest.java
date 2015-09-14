/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.extensions.xjdfwalker;

import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoInterpretingParams.EnumPolarity;
import org.cip4.jdflib.auto.JDFAutoLayoutIntent.EnumSides;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.PartitionHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.JDFToXJDF;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JMFBuilderFactory;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFHoleLine;
import org.cip4.jdflib.resource.JDFInterpretingParams;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFDropItemIntent;
import org.cip4.jdflib.resource.intent.JDFLayoutIntent;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFPageData;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.postpress.JDFHoleMakingParams;
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
	public void testRefMediaFromInline()
	{
		JDFToXJDF conv = new JDFToXJDF();
		JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.ImageSetting);
		JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output);
		xm.appendMedia().setMediaSetCount(42);
		KElement xjdf = conv.convert(n);
		assertNotNull(new XJDFHelper(xjdf).getSet("Media", 0));
	}

	/**
	*
	 */
	@Test
	public void testDeliveryIntent()
	{
		_testDeliveryIntent();
	}

	public KElement _testDeliveryIntent()
	{
		final JDFNode nP = new JDFDoc("JDF").getJDFRoot();
		nP.setType(EnumType.Product);
		nP.setDescriptiveName("desc");
		JDFDeliveryIntent di = (JDFDeliveryIntent) nP.addResource(ElementName.DELIVERYINTENT, EnumUsage.Input);
		final JDFComponent c = (JDFComponent) nP.addResource("Component", EnumUsage.Output);
		nP.getLink(c, null).setAmount(66);
		JDFDropItemIntent dropItemIntent = di.appendDropIntent().appendDropItemIntent();
		dropItemIntent.refComponent(c);
		dropItemIntent.setAmount(42);
		JDFDropItemIntent dropItemIntent2 = di.appendDropIntent().appendDropItemIntent();
		dropItemIntent2.refComponent(c);
		dropItemIntent2.setAmount(63);
		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setSingleNode(true);
		KElement xjdf = xjdf20.makeNewJDF(nP, null);
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
	public void testPageList()
	{
		_testPageList();
	}

	/**
	 * 
	 * @return
	 */
	public KElement _testPageList()
	{
		final JDFNode root = new JDFDoc("JDF").getJDFRoot();
		root.setType(EnumType.Trapping);
		root.setDescriptiveName("desc");

		JDFRunList rl = (JDFRunList) root.addResource(ElementName.RUNLIST, EnumUsage.Input);
		JDFRunList ruLiRun = rl.addRun("foo.pdf", 0, 42);
		JDFRunList ruLiRun2 = rl.addRun("foo2.pdf", 0, 666);
		JDFPageList pList = ruLiRun.appendPageList();
		pList = (JDFPageList) pList.makeRootResource(null, null, true);
		ruLiRun2.refElement(pList);

		pList.appendScreeningParams().appendScreenSelector().setAngle(42);

		for (int i = 0; i < 4; i++)
		{
			JDFPageData pd = pList.appendPageData();
			pd.setPageStatus("DigitalArtArrived");
			pd.setIsBlank(i % 2 == 0);
		}

		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setSingleNode(true);
		KElement xjdf = xjdf20.makeNewJDF(root, null);
		xjdf.write2File(sm_dirTestDataTemp + "pageListTest.xjdf");
		assertNotNull(xjdf);
		for (int i = 1; i < 5; i++)
		{
			assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource[" + i + "]/Content/@IsBlank", null), i % 2 == 1 ? "true" : "false");
			assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource[" + i + "]/Content/@ContentStatus", null), "DigitalArtArrived");
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
	public KElement _testPageListEmpty()
	{
		final JDFNode root = new JDFDoc("JDF").getJDFRoot();
		root.setType(EnumType.Trapping);
		root.setDescriptiveName("desc");

		JDFRunList rl = (JDFRunList) root.addResource(ElementName.RUNLIST, EnumUsage.Input);
		JDFPageList pList = (JDFPageList) root.addResource(ElementName.PAGELIST, null);

		JDFRunList ruLiRun = rl.addRun("P1.pdf", 0, 42);
		JDFRunList ruLiRun2 = rl.addRun("P2.pdf", 0, 42);
		JDFResource p1 = pList.addPartition(EnumPartIDKey.PartVersion, "P1");
		ruLiRun.refElement(p1);
		p1.setDescriptiveName("Pl for p1");
		JDFResource p2 = pList.addPartition(EnumPartIDKey.PartVersion, "P2");
		p2.setDescriptiveName("Pl for p2");
		ruLiRun2.refElement(p2);

		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setSingleNode(true);
		KElement xjdf = xjdf20.makeNewJDF(root, null);
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
		JDFToXJDF conv = new JDFToXJDF();
		JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		JDFResource c = n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		c.setDescriptiveName("desc");
		c.addPartition(EnumPartIDKey.SignatureName, "Sig1").addPartition(EnumPartIDKey.SheetName, "s1");
		KElement x = conv.convert(n);
		assertEquals(x.toXML().indexOf("SignatureName"), -1);
		conv.setRemoveSignatureName(false);
		KElement x2 = conv.convert(n);
		assertTrue(x2.toXML().indexOf("SignatureName") > 0);
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testKeepParameter()
	{
		JDFToXJDF conv = new JDFToXJDF();
		JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		JDFResource c = n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		c.setDescriptiveName("desc");
		n.setStatus(EnumNodeStatus.Cleanup);
		conv.setParameterSet(true);
		KElement xjdf = conv.convert(n);
		assertEquals(xjdf.getXPathAttribute("ParameterSet/Parameter/NodeInfo/@NodeStatus", null), "Cleanup");
		assertEquals(xjdf.getXPathAttribute("ResourceSet/Resource/@DescriptiveName", null), "desc");
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testColorIntent()
	{
		JDFToXJDF conv = new JDFToXJDF();
		JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.Product);
		n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		JDFColorIntent ci = (JDFColorIntent) n.getCreateResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		JDFColorIntent cif = (JDFColorIntent) ci.addPartition(EnumPartIDKey.Side, "Front");
		cif.appendColorsUsed().setCMYK();
		cif.appendCoatings().setActual("Varnish");
		JDFColorIntent cib = (JDFColorIntent) ci.addPartition(EnumPartIDKey.Side, "Back");
		cib.appendCoatings().setActual("Mess");
		cib.setNumColors(1);
		KElement xjdf = conv.convert(n);
		ProductHelper ph = new XJDFHelper(xjdf).getProductHelpers().get(0);
		assertNotNull(ph);
		IntentHelper ih = ph.getIntent("ColorIntent");
		KElement e = ih.getResource();
		assertEquals(e.getChildElementVector("SurfaceColor", null).size(), 2);
	}

	/**
	*  
	*  
	*/
	@Test
	public void testMultiBackwardProduct()
	{
		XJDFHelper h = new XJDFHelper("j", "root", null);
		for (int i = 0; i < 2; i++)
		{
			ProductHelper cover = h.appendProduct();
			ProductHelper body = h.appendProduct();
			ProductHelper book = h.appendProduct();
			book.setRoot();
			book.setChild(cover, 1);
			book.setChild(body, 1);
		}
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		JDFDoc d = xCon.convert(h);
		assertEquals(d.getJDFRoot().getJobPartID(true), "root");

		JDFToXJDF conv = new JDFToXJDF();
		KElement newRoot = conv.convert(d.getJDFRoot());
		XJDFHelper h2 = new XJDFHelper(newRoot);
		assertEquals(h2.numProductHelpers(true), 2);
		assertEquals(h2.numProductHelpers(false), 6);
	}

	/**
	 * 
	 */
	@Test
	public void testPipeJMF()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.PipeClose);
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals(xjmf.getXPathAttribute("CommandPipeControl/PipeParams/@Operation", null), "PipeClose");
		final JDFJMF jmfResp = JDFJMF.createJMF(EnumFamily.Response, JDFMessage.EnumType.PipeClose);
		xjmf = conv.makeNewJMF(jmfResp);
		assertEquals(xjmf.getElement(null).getLocalName(), "ResponsePipeControl");
	}

	/**
	 * 
	 */
	@Test
	public void testJMFEmployee()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.PipeClose);
		jmf.getCommand(0).appendEmployee().setPersonalID("P1");
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals(xjmf.getXPathAttribute("CommandPipeControl/@PersonalID", null), "P1");

		XJDFToJDFConverter invert = new XJDFToJDFConverter(null);
		JDFDoc d = invert.convert(xjmf);
		JDFJMF jmf2 = d.getJMFRoot();
		assertEquals(jmf2.getCommand(0).getEmployee(0).getPersonalID(), "P1");
	}

	/**
	 * 
	 */
	@Test
	public void testAuditEmployee()
	{
		JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.getCreateAuditPool().addAudit(EnumAuditType.PhaseTime, "me");
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjdf = conv.convert(n);
		assertEquals(xjdf.getXPathAttribute("AuditPool/PhaseTime/@Author", null), "me");

		XJDFToJDFConverter invert = new XJDFToJDFConverter(null);
		JDFDoc d = invert.convert(xjdf);
		JDFNode n2 = d.getJDFRoot();
		assertEquals(n2.getAuditPool().getAudit(0, EnumAuditType.PhaseTime, null, null).getEmployee(0).getDescriptiveName(), "me");
	}

	/**
	 * 
	 */
	@Test
	public void testSubscriptionJMF()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildStatusSubscription("url", 42, 21, "qe33");
		jmf.getQuery(0).getSubscription().appendObservationTarget().setAttributes(new VString("a", null));
		jmf.getQuery(0).getStatusQuParams().setQueueInfo(true);
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjmf = conv.makeNewJMF(jmf);
		assertNull(xjmf.getChildByTagName(ElementName.OBSERVATIONTARGET, null, 0, null, false, false));
		KElement subscription = xjmf.getChildByTagName(ElementName.SUBSCRIPTION, null, 0, null, false, false);
		assertNotNull(subscription);
		assertFalse(subscription.hasAttribute(AttributeName.REPEATSTEP));
	}

	/**
	 * 
	 */
	@Test
	public void testStatusJMF()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildStatusSubscription("url", 42, 21, "qe33");
		jmf.getQuery(0).getSubscription().appendObservationTarget().setAttributes(new VString("a", null));
		jmf.getQuery(0).getStatusQuParams().setQueueInfo(true);
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjmf = conv.makeNewJMF(jmf);
		KElement statusquparams = xjmf.getChildByTagName(ElementName.STATUSQUPARAMS, null, 0, null, false, false);
		assertFalse(statusquparams.hasAttribute(AttributeName.QUEUEINFO));
	}

	/**
	 * 
	 */
	@Test
	public void testMultiNode1()
	{
		JDFElement.setLongID(false);
		JDFNode product = new JDFDoc(ElementName.JDF).getJDFRoot();
		product.setType(EnumType.Product);
		JDFLayoutIntent loi = (JDFLayoutIntent) product.addResource(ElementName.LAYOUTINTENT, EnumUsage.Input);
		loi.setSides(EnumSides.OneSided);
		JDFNode plateset = product.addCombined(new VString("Impositioning Interpreting Rendering ImageSetting", " "));
		JDFInterpretingParams ip = (JDFInterpretingParams) plateset.addResource(ElementName.INTERPRETINGPARAMS, EnumUsage.Input);
		ip.setPolarity(EnumPolarity.Negative);
		JDFRunList ruli = (JDFRunList) plateset.addResource(ElementName.RUNLIST, EnumUsage.Input);
		ruli.setFileURL("file:///foo.pdf");
		plateset.addResource(ElementName.MEDIA, EnumUsage.Input);
		plateset.addResource(ElementName.LAYOUT, EnumUsage.Input);
		JDFExposedMedia xm = (JDFExposedMedia) plateset.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output);
		JDFNode cp = product.addCombined(new VString("InkZoneCalculation ConventionalPrinting", " "));
		cp.ensureLink(xm, EnumUsage.Input, null);
		cp.addResource(ElementName.MEDIA, EnumUsage.Input);

		JDFToXJDF conv = new JDFToXJDF();
		conv.setWantProduct(true);
		conv.saveZip(sm_dirTestDataTemp + "3files.xjdf.zip", product, true);
	}

	/**
	 * 
	 */
	@Test
	public void testHoleMaking()
	{
		JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.HoleMaking);
		JDFHoleMakingParams hp = (JDFHoleMakingParams) node.appendMatchingResource(ElementName.HOLEMAKINGPARAMS, EnumUsage.Input);
		hp.setCenter(new JDFXYPair(3, 4));
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjdf = conv.convert(node);
		PartitionHelper ph = new XJDFHelper(xjdf).getPartition(ElementName.HOLEMAKINGPARAMS, 0, 0);
		KElement holepattern = ph.getResource().getElement("HolePattern");
		assertNotNull(holepattern);
		assertEquals(holepattern.getAttribute(AttributeName.CENTER), "3 4");
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
		JDFNode node = new JDFDoc(ElementName.JDF).getJDFRoot();
		node.setType(EnumType.HoleMaking);
		JDFHoleMakingParams hp = (JDFHoleMakingParams) node.appendMatchingResource(ElementName.HOLEMAKINGPARAMS, EnumUsage.Input);
		JDFHoleLine holeline = hp.appendHoleLine();
		holeline.setPitch(42);
		holeline.appendHole().setCenter(new JDFXYPair(5, 6));
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjdf = conv.convert(node);
		PartitionHelper ph = new XJDFHelper(xjdf).getPartition(ElementName.HOLEMAKINGPARAMS, 0, 0);
		KElement holepattern = ph.getResource().getElement("HolePattern");
		assertNotNull(holepattern);
		assertEquals(holepattern.getAttribute(AttributeName.CENTER), "5 6");
		assertEquals(holepattern.getAttribute(AttributeName.PITCH), "42");
		return xjdf;
	}

	/**
	 * 
	 */
	@Test
	public void testgetXJDFs1()
	{
		JDFElement.setLongID(false);
		JDFNode product = new JDFDoc(ElementName.JDF).getJDFRoot();
		product.setType(EnumType.Product);
		product.addResource(ElementName.COMPONENT, EnumUsage.Output);
		JDFLayoutIntent loi = (JDFLayoutIntent) product.addResource(ElementName.LAYOUTINTENT, EnumUsage.Input);
		loi.setSides(EnumSides.OneSided);
		JDFNode product2 = product.addProduct();
		product2.addResource(ElementName.COMPONENT, EnumUsage.Output).setDescriptiveName("Cover");
		product.linkOutputs(product2);
		JDFNode product3 = product.addProduct();
		product3.addResource(ElementName.COMPONENT, EnumUsage.Output).setDescriptiveName("Body");
		product.linkOutputs(product3);

		JDFNode plateset = product.addCombined(new VString("Impositioning Interpreting Rendering ImageSetting", " "));
		JDFInterpretingParams ip = (JDFInterpretingParams) plateset.addResource(ElementName.INTERPRETINGPARAMS, EnumUsage.Input);
		ip.setPolarity(EnumPolarity.Negative);
		JDFRunList ruli = (JDFRunList) plateset.addResource(ElementName.RUNLIST, EnumUsage.Input);
		ruli.setFileURL("file:///foo.pdf");
		plateset.addResource(ElementName.MEDIA, EnumUsage.Input).setProductID("p1");
		plateset.addResource(ElementName.LAYOUT, EnumUsage.Input);
		JDFExposedMedia xm = (JDFExposedMedia) plateset.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output);
		JDFNode cp = product.addCombined(new VString("InkZoneCalculation ConventionalPrinting", " "));
		cp.ensureLink(xm, EnumUsage.Input, null);
		cp.addResource(ElementName.MEDIA, EnumUsage.Input).setProductID("p2");

		product.write2File(sm_dirTestDataTemp + "getXJDFS.jdf");
		JDFToXJDF conv = new JDFToXJDF();
		Vector<XJDFHelper> v = conv.getXJDFs(product);
		assertEquals(v.size(), 3);
		for (XJDFHelper h : v)
		{
			h.writeToFile(sm_dirTestDataTemp + "getXJDFS." + h.getJobPartID() + ".xjdf");
		}
	}

	/**
	 * 
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception
	{
		XJMFTypeMap.shutDown();
		super.tearDown();
	}

}
