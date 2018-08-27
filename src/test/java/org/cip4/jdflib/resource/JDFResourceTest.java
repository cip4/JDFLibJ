/*
 * The CIP4 Software License, Version 1.0
 *
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
/**
 * JDFResourceTest.java
 *
 * @author Dietrich Mucha
 *
 *         Copyright (C) 2002 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoColorSpaceConversionOp.EnumSourceObjects;
import org.cip4.jdflib.auto.JDFAutoPart.EnumPreviewType;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.LinkRefFinder;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFCutBlock;
import org.cip4.jdflib.resource.process.JDFDigitalPrintingParams;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;
import org.cip4.jdflib.resource.process.JDFStripCellParams;
import org.cip4.jdflib.resource.process.postpress.JDFFoldingParams;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionOp;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.VectorMap;
import org.junit.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         20.01.2009
 */
public class JDFResourceTest extends JDFTestCaseBase
{

	private boolean bAgent;
	private boolean bSubElem;

	/**
	 *
	 */
	@Test
	public void testGetCreator()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		assertTrue(xm.getCreator(false).contains(n));
	}

	/**
	 *
	 */
	@Test
	public void testGetCreatorPartition()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFNode nc1 = n.addJDFNode(EnumType.ConventionalPrinting);
		final JDFNode nf1 = n.addJDFNode(EnumType.Folding);
		final JDFNode nc2 = n.addJDFNode(EnumType.ConventionalPrinting);
		final JDFNode nf2 = n.addJDFNode(EnumType.Folding);
		final JDFResource r = n.addResource(ElementName.COMPONENT, null);
		final JDFResource r1 = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SheetName, "s2");
		nc1.linkResource(r1, EnumUsage.Output, null);
		nf1.linkResource(r1, EnumUsage.Input, null);
		nc2.linkResource(r2, EnumUsage.Output, null);
		nf2.linkResource(r2, EnumUsage.Input, null);
		assertEquals(r1.getCreator(true).size(), 1);
		assertEquals(r1.getCreator(true).get(0), nc1);
		assertEquals(r2.getCreator(true).size(), 1);
		assertEquals(r2.getCreator(true).get(0), nc2);
		assertEquals(r1.getCreator(false).get(0), nf1);
		assertEquals(r2.getCreator(false).get(0), nf2);
	}

	/**
	 *
	 */
	@Test
	public void testUnPartition()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		xm.unpartition(false);
		assertEquals(xm.getPartIDKeys().size(), 0);
	}

	/**
	 *
	 */
	@Test
	public void testUnPartitionNot()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		((JDFExposedMedia) xm.getLeaves(false).get(0)).setBrand("foo"); // one is different...
		xm.unpartition(false);
		assertEquals(xm.getPartIDKeys().size(), 3);
		xm.unpartition(true);
		assertEquals(xm.getPartIDKeys().size(), 0);
	}

	/**
	 *
	 */
	@Test
	public void testGetAttributeVector()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "S1");
		mPart.put("Side", "Front");
		final JDFExposedMedia xmPart = (JDFExposedMedia) xm.getPartition(mPart, null);
		xmPart.setAgentName("agent");
		xmPart.setAttribute("foo:bar", "foobar", "www.foo.com");
		final VString partVector = xmPart.getAttributeVector();
		assertTrue("contains inherited attributes", partVector.contains("ID"));
		assertTrue("contains inherited attributes", partVector.contains("SignatureName"));
		assertTrue("contains inherited attributes", partVector.contains("SheetName"));
		assertTrue("contains inherited attributes", partVector.contains("Side"));
		assertTrue("contains inherited attributes", partVector.contains("AgentName"));
		assertTrue("contains inherited attributes", partVector.contains("foo:bar"));
	}

	/**
	 *
	 */
	@Test
	public void testGetNonEmpty()
	{
		final JDFDoc xd = new JDFDoc(ElementName.JDF, null);
		final JDFNode root = xd.getJDFRoot();
		final JDFResource r = root.addResource(ElementName.CUTTINGPARAMS, EnumUsage.Input);
		final JDFResource leaf = r.addPartition(EnumPartIDKey.DeliveryUnit1, "B1");
		r.setDescriptiveName("d1");
		assertEquals("d1", r.getNonEmpty(AttributeName.DESCRIPTIVENAME));
		assertEquals("d1", leaf.getNonEmpty(AttributeName.DESCRIPTIVENAME));
		assertNull(root.getNonEmpty(AttributeName.ABSOLUTEBOX));
		assertNull(leaf.getNonEmpty(AttributeName.ABSOLUTEBOX));
	}

	/**
	 *
	 */
	@Test
	public void testGetNonEmpty_KElement()
	{
		final JDFDoc xd = new JDFDoc(ElementName.JDF, null);
		final JDFNode root = xd.getJDFRoot();
		final JDFResource r = root.addResource(ElementName.CUTTINGPARAMS, EnumUsage.Input);
		final JDFResource leaf = r.addPartition(EnumPartIDKey.DeliveryUnit1, "B1");
		leaf.setAmount(42);
		r.setDescriptiveName("d1");
		assertEquals("d1", r.getNonEmpty_KElement(AttributeName.DESCRIPTIVENAME));
		assertEquals("42", leaf.getNonEmpty_KElement(AttributeName.AMOUNT));
		assertNull(leaf.getNonEmpty_KElement(AttributeName.DESCRIPTIVENAME));
		assertNull(root.getNonEmpty_KElement(AttributeName.ABSOLUTEBOX));
		assertNull(leaf.getNonEmpty_KElement(AttributeName.ABSOLUTEBOX));
	}

	/**
	 *
	 */
	@Test
	public void testHasNonEmpty()
	{
		final JDFDoc xd = new JDFDoc(ElementName.JDF, null);
		final JDFNode root = xd.getJDFRoot();
		final JDFResource r = root.addResource(ElementName.CUTTINGPARAMS, EnumUsage.Input);
		final JDFResource leaf = r.addPartition(EnumPartIDKey.DeliveryUnit1, "B1");
		r.setDescriptiveName("d1");
		assertTrue(r.hasNonEmpty(AttributeName.DESCRIPTIVENAME));
		assertTrue(leaf.hasNonEmpty(AttributeName.DESCRIPTIVENAME));
		assertFalse(root.hasNonEmpty(AttributeName.ABSOLUTEBOX));
		assertFalse(leaf.hasNonEmpty(AttributeName.ABSOLUTEBOX));
	}

	/**
	*
	*/
	@Test
	public void testHasNonEmpty_KElement()
	{
		final JDFDoc xd = new JDFDoc(ElementName.JDF, null);
		final JDFNode root = xd.getJDFRoot();
		final JDFResource r = root.addResource(ElementName.CUTTINGPARAMS, EnumUsage.Input);
		final JDFResource leaf = r.addPartition(EnumPartIDKey.DeliveryUnit1, "B1");
		leaf.setAmount(42);
		r.setDescriptiveName("d1");
		assertTrue(r.hasNonEmpty_KElement(AttributeName.DESCRIPTIVENAME));
		assertTrue(leaf.hasNonEmpty_KElement(AttributeName.AMOUNT));
		assertFalse(leaf.hasNonEmpty_KElement(AttributeName.DESCRIPTIVENAME));
		assertFalse(root.hasNonEmpty_KElement(AttributeName.ABSOLUTEBOX));
		assertFalse(leaf.hasNonEmpty_KElement(AttributeName.ABSOLUTEBOX));
	}

	/**
	 * test the the generalized partition matching
	 */
	@Test
	public void testGetAttributeMap()
	{
		final JDFNode root = new JDFDoc(ElementName.JDF).getJDFRoot();
		root.setType(JDFNode.EnumType.ConventionalPrinting.getName(), true);
		final JDFExposedMedia xm = (JDFExposedMedia) root.appendMatchingResource(ElementName.EXPOSEDMEDIA, JDFNode.EnumProcessUsage.AnyInput, null);
		xm.setResolution(new JDFXYPair(300, 300));
		xm.setPolarity(true);
		xm.setProofType(JDFExposedMedia.EnumProofType.Page);

		final JDFMedia m = xm.appendMedia();
		m.setDimension(new JDFXYPair(200, 300));
		final JDFExposedMedia xm2 = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.SheetName, "S1");
		final JDFAttributeMap xm2Map = xm2.getAttributeMap();
		xm2Map.remove(EnumPartIDKey.SheetName);
		assertEquals(xm.getAttributeMap(), xm2Map);
		xm.setAttribute("foo:bar", "foobar", "www.foo.com");
		JDFAttributeMap am = xm.getAttributeMap();
		assertEquals(am.get("foo:bar"), "foobar");
		am = xm2.getAttributeMap();
		assertEquals(am.get("foo:bar"), "foobar");
	}

	/**
	 * test the the generalized partition matching
	 */
	@Test
	public void testGetAttributeMap_KElement()
	{
		final JDFNode root = new JDFDoc(ElementName.JDF).getJDFRoot();
		root.setType(JDFNode.EnumType.ConventionalPrinting.getName(), true);
		final JDFExposedMedia xm = (JDFExposedMedia) root.appendMatchingResource(ElementName.EXPOSEDMEDIA, JDFNode.EnumProcessUsage.AnyInput, null);
		xm.setResolution(new JDFXYPair(300, 300));
		xm.setPolarity(true);
		xm.setProofType(JDFExposedMedia.EnumProofType.Page);

		final JDFMedia m = xm.appendMedia();
		m.setDimension(new JDFXYPair(200, 300));
		final JDFExposedMedia xm2 = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.SheetName, "S1");
		final JDFAttributeMap xm2Map = xm2.getAttributeMap();
		xm2Map.remove(EnumPartIDKey.SheetName);
		assertEquals(xm.getAttributeMap_KElement(), xm2Map);
		xm.setAttribute("foo:bar", "foobar", "www.foo.com");
		JDFAttributeMap am = xm.getAttributeMap_KElement();
		assertEquals(am.get("foo:bar"), "foobar");
		am = xm2.getAttributeMap_KElement();
		assertNull("No inheritence", am.get("foo:bar"));
	}

	/**
	 * tests the hard coded getters and setters
	 */
	@Test
	public void testGetSet()
	{
		final JDFNode root = new JDFDoc(ElementName.JDF).getJDFRoot();
		root.setType(JDFNode.EnumType.ConventionalPrinting.getName(), true);
		final JDFExposedMedia xm = (JDFExposedMedia) root.appendMatchingResource(ElementName.EXPOSEDMEDIA, JDFNode.EnumProcessUsage.AnyInput, null);
		xm.setManufacturer("acme");
		assertEquals(xm.getManufacturer(), "acme");
		assertTrue(xm.hasAttribute(AttributeName.MANUFACTURER));
	}

	/**
	 * test the the generalized partition matching
	 */
	@Test
	public void testOverlapPartMap()
	{
		final JDFAttributeMap m1 = new JDFAttributeMap("PartVersion", "DE");
		m1.put("Run", "r2");
		final JDFAttributeMap m2 = new JDFAttributeMap("PartVersion", "DE EN");
		assertTrue(JDFPart.overlapPartMap(m1, m2, false));
		assertFalse(JDFPart.overlapPartMap(m1, m2, true));
		m2.put("Run", "r2");
		assertTrue(JDFPart.overlapPartMap(m1, m2, false));
		assertFalse(JDFPart.overlapPartMap(m1, m2, true));
	}

	/**
	 * test initialization and setAutoAgent
	 */
	@Test
	public void testInit()
	{
		for (int i = 0; i < 2; i++)
		{
			final boolean bb = i == 0;
			JDFResource.setAutoAgent(bb);
			JDFAudit.setStaticAgentName(JDFAudit.software());
			JDFAudit.setStaticAgentVersion(JDFElement.getDefaultJDFVersion().getName());
			final JDFDoc doc = creatXMDoc();
			final JDFNode n = doc.getJDFRoot();
			final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
			assertTrue(xm.hasAttribute(AttributeName.AGENTNAME) == bb);
			assertTrue(xm.hasAttribute(AttributeName.AGENTVERSION) == bb);
		}
	}

	/**
	 *
	 */
	@Test
	public void testInitAgentName()
	{
		JDFAudit.setStaticAgentName("foo");
		JDFResource.setAutoAgent(true);
		final JDFResource r = new JDFDoc(ElementName.JDF).getJDFRoot().addResource(ElementName.COMPONENT, null);
		assertEquals(r.getAgentName(), "foo");
	}

	/**
	 *
	 *
	 */
	@Test
	public void testMakeRootResource()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFResourcePool p = n.appendResourcePool();
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		final JDFMedia m = xm.appendMedia();
		final JDFMedia m2 = (JDFMedia) m.makeRootResource(null, null, true);
		assertEquals(m, m2);
		assertEquals(m2.getParentNode_KElement(), p);
	}

	/**
	 * test the resource root stuff
	 */
	@Test
	public void testGetResourcePoolNS()
	{
		// set up a test document
		final JDFDoc jdfDoc = new JDFDoc("JDF");
		final JDFNode root = jdfDoc.getJDFRoot();
		root.appendElement("foo:elem", "www.foo.com");
		final JDFResourcePool rp = root.appendResourcePool();
		rp.appendResource("foo:res", EnumResourceClass.Parameter, "www.foo.com");
		rp.appendElement("foo:res", "www.foo.com");
		rp.appendElement("foo:elem", "www.foo.com");
	}

	/**
	 *
	 */
	@Test
	public void testGetResourceRootNI13()
	{
		final JDFNode rootNode = new JDFDoc("JDF").getJDFRoot();
		final JDFNodeInfo ni = rootNode.getCreateNodeInfo();
		final JDFContact con = ni.appendContact();
		assertEquals(con.getResourceRoot(), ni);
	}

	/**
	 * test the resource root stuff
	 */
	@Test
	public void testGetResourceRoot()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setVersion(EnumVersion.Version_1_1);
		final JDFLayout l = (JDFLayout) n.addResource(ElementName.LAYOUT, EnumUsage.Input);
		final KElement e = l.appendSignature().appendSheet().appendContentObject();
		assertEquals(JDFResource.getResourceRoot(e), l);
		assertEquals(JDFResource.getResourceRoot(l), l);
		assertNull(JDFResource.getResourceRoot(n));
		assertNull(JDFResource.getResourceRoot(n.getAuditPool()));
	}

	/**
	 * test the resource root stuff
	 */
	@Test
	public void testGetResourceRootJMF()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, org.cip4.jdflib.jmf.JDFMessage.EnumType.Resource);
		final JDFResourceInfo ri = jmf.getCreateSignal(0).appendResourceInfo();
		final JDFExposedMedia xm = (JDFExposedMedia) ri.appendResource("ExposedMedia");
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put("SignatureName", "Sig1");
		partMap.put("SheetName", "S1");
		partMap.put("Side", "Front");
		partMap.put("Separation", "Black");
		final JDFExposedMedia xmPart = (JDFExposedMedia) xm.getCreatePartition(partMap, new VString("SignatureName SheetName Side Separation", null));
		final JDFMedia m = xmPart.appendMedia();
		assertEquals(JDFResource.getResourceRoot(m), xm);
		assertEquals(JDFResource.getResourceRoot(xmPart), xm);
	}

	/**
	 * test the resource root stuff
	 */
	@Test
	public void testGetResourceRoot_Old()
	{

		// set up a test document
		XMLDoc jdfDoc = new JDFDoc(ElementName.COLORPOOL);
		JDFElement root = (JDFElement) jdfDoc.getRoot();
		JDFResource resource;
		JDFResource resRoot;

		// !(parentName != null && !parentName.equals(JDFConstants.EMPTYSTRING)
		// resRoot = ((JDFResource) root).getResourceRoot();
		// assertTrue(resRoot == null);

		resource = (JDFResource) root.appendElement(ElementName.COLOR);

		// set up a test document
		jdfDoc = new JDFDoc(ElementName.RESOURCEPOOL);
		root = (JDFElement) jdfDoc.getRoot();

		resource = (JDFResource) root.appendElement(ElementName.COLOR);

		resRoot = resource.getResourceRoot();
		assertTrue(resRoot == resource);

		// set up a test document
		final JDFDoc jdfDoc2 = new JDFDoc(ElementName.JDF);
		root = jdfDoc2.getJDFRoot();

		resource = (JDFResource) root.appendElement(ElementName.COLOR);
		final JDFResource elem = (JDFResource) root.appendElement(ElementName.NODEINFO);

		resRoot = resource.getResourceRoot();
		assertEquals("getResourceRoot now also returns the root of incorrectly placed resources", resource, resRoot);

		resRoot = elem.getResourceRoot();
		assertTrue(resRoot == elem);

		final JDFNode rootNode = (JDFNode) root;
		final JDFExposedMedia xm = (JDFExposedMedia) rootNode.addResource("ExposedMedia", null, EnumUsage.Input, null, null, null, null);
		final JDFMedia m = xm.appendMedia();
		assertEquals("xm", xm.getResourceRoot(), xm);
		assertEquals("m", m.getResourceRoot(), xm);

		final JDFColorantControl cc = (JDFColorantControl) rootNode.addResource("ColorantControl", null, EnumUsage.Input, null, null, null, null);
		final JDFSeparationSpec ss = cc.appendColorantParams().appendSeparationSpec();
		assertFalse(ss.hasAttribute(AttributeName.CLASS));

	}

	/**
	 *
	 */
	@Test
	public void testIsPhysical()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		assertFalse(n.addResource(ElementName.STRAPPINGPARAMS, null).isPhysical());
		assertFalse(n.addResource(ElementName.MEDIAINTENT, null).isPhysical());
		assertTrue(n.addResource(ElementName.MEDIA, null).isPhysical());
		assertTrue(n.addResource(ElementName.COMPONENT, null).isPhysical());
		assertFalse(n.addResource(ElementName.PLACEHOLDERRESOURCE, null).isPhysical());
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, null);
		final JDFMedia media = xm.appendMedia();
		media.removeAttribute(AttributeName.CLASS);
		media.setBrand("b1");
		assertTrue(media.isPhysical());
		assertFalse(media.isParameter());
	}

	/**
	*
	*/
	@Test
	public void testIsParameter()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		assertTrue(n.addResource(ElementName.STRAPPINGPARAMS, null).isParameter());
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, null);
		final JDFMedia media = xm.appendMedia();
		media.removeAttribute(AttributeName.CLASS);
		media.setBrand("b1");
		assertFalse(media.isParameter());
	}

	/**
	*
	*/
	@Test
	public void testInvalidAttributes()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, null);
		final JDFMedia media = xm.appendMedia();
		media.removeAttribute(AttributeName.CLASS);
		media.setBrand("b1");
		assertFalse(media.getInvalidAttributes(EnumValidationLevel.Incomplete, true, 0).contains("Brand"));
	}

	/**
	 *
	 */
	@Test
	public void testIsResourceElement()
	{
		// get the JDF document root element
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode root = (JDFNode) jdfDoc.getRoot();

		final JDFNode trappingNode = new JDFNode((CoreDocumentImpl) root.getOwnerDocument(), root.getNamespaceURI(), root.getNodeName());

		trappingNode.setType(JDFNode.EnumType.Trapping.getName(), false);

		// Add an intent resource
		final JDFRunList runList = (JDFRunList) trappingNode.appendMatchingResource(ElementName.RUNLIST, JDFNode.EnumProcessUsage.AnyInput, null);

		final JDFLayoutElement layoutElem = runList.appendLayoutElement();

		assertFalse(runList.isResourceElement());
		assertTrue(layoutElem.isResourceElement());
	}

	/**
	 *
	 */
	@Test
	public void testAppendRaw()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFResource r2 = r.addPartition(EnumPartIDKey.DeliveryUnit0, "d1");
		final JDFResource r3 = (JDFResource) r2.appendElement(ElementName.EMBOSSINGPARAMS);
		r3.setAttribute(AttributeName.DELIVERYUNIT1, "dd1");
		r.appendAttribute(AttributeName.PARTIDKEYS, AttributeName.DELIVERYUNIT1, null, null, true);
		assertEquals(r, r.getPartition(new JDFAttributeMap(), EnumPartUsage.Explicit));
		final JDFAttributeMap m = new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1");
		assertEquals(r2, r.getPartition(m, EnumPartUsage.Explicit));
		m.put(EnumPartIDKey.DeliveryUnit1, "dd1");
		assertEquals(r3, r.getPartition(m, EnumPartUsage.Explicit));
	}

	/**
	 *
	 */
	@Test
	public void testAppendRawAtt()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EMBOSSINGPARAMS).getRoot();
		final JDFResource r2 = r.addPartition(EnumPartIDKey.DeliveryUnit0, "d1");
		final JDFResource r3 = (JDFResource) r2.appendElement(ElementName.EMBOSSINGPARAMS);
		r3.setAttribute(AttributeName.DELIVERYUNIT1, "dd1");
		r.appendAttribute(AttributeName.PARTIDKEYS, AttributeName.DELIVERYUNIT1, null, null, true);
		assertEquals(r, r.getPartition(new JDFAttributeMap(), EnumPartUsage.Explicit));
		final JDFAttributeMap m = new JDFAttributeMap(EnumPartIDKey.DeliveryUnit0, "d1");
		assertEquals(r2, r.getPartition(m, EnumPartUsage.Explicit));
		m.put(EnumPartIDKey.DeliveryUnit1, "dd1");
		assertEquals(r3, r.getPartition(m, EnumPartUsage.Explicit));
		r3.setDeliveryUnit(1, "dd2");
		assertNull(r.getPartition(m, EnumPartUsage.Explicit));
		m.put(EnumPartIDKey.DeliveryUnit1, "dd2");
		assertEquals(r3, r.getPartition(m, EnumPartUsage.Explicit));
	}

	/**
	 *
	 */
	@Test
	public void testgetPartMap()
	{
		final String strFileName = sm_dirTestData + "partitioned_private_resources.jdf";
		final JDFParser p = new JDFParser();

		final JDFDoc myJDFDoc = p.parseFile(strFileName);
		final JDFNode myRoot = myJDFDoc.getJDFRoot();
		final JDFResourcePool myResPool = myRoot.getResourcePool();
		JDFResource myPreview = (JDFResource) myResPool.getElement("Preview", "", 0);
		final JDFAttributeMap m = new JDFAttributeMap();

		// m.put("SheetName", "Sheet 1");
		m.put("Side", "Front");
		m.put("Separation", "Black");
		m.put("PreviewType", "Separation");

		myPreview = myPreview.getDeepPart(m, EnumPartUsage.Explicit);
		final VElement vRes = myPreview.getLeaves(false);

		for (int i = 0; i < vRes.size(); i++)
		{
			final JDFAttributeMap myMap = ((JDFResource) vRes.elementAt(i)).getPartMap();
			if ("Black".equals(myMap.get("Separation")))
			{
				assertTrue(myMap.get("Side").equals("Front"));
				assertTrue(myMap.get("PreviewType").equals("Separation"));
				assertTrue(myMap.get("SheetName").startsWith("Sheet ")); // "Sheet 1"
				// or
				// "Sheet 2"
				assertTrue(myMap.get("Separation").equals("Black"));
			}
		}
	}

	// /////////////////////////////////////////////////////////////

	/**
	 * tests the various implicit and explicit defaults of getPartUsage
	 */
	@Test
	public void testgetPartUsage()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFRunList r = (JDFRunList) n.addResource("RunList", EnumUsage.Input);
		assertEquals(r.getPartUsage(), EnumPartUsage.Explicit);
		JDFResource.setUnpartitiondImplicit(true);
		assertEquals(r.getPartUsage(), EnumPartUsage.Implicit);
		final JDFRunList r2 = r.addRun("foo.pdf", 0, 99);
		assertEquals(r.getPartUsage(), EnumPartUsage.Explicit);
		assertEquals(r2.getPartUsage(), EnumPartUsage.Explicit);
		r.setPartUsage(EnumPartUsage.Sparse);
		assertEquals(r.getPartUsage(), EnumPartUsage.Sparse);
		assertEquals(r2.getPartUsage(), EnumPartUsage.Sparse);
	}

	/**
	 *
	 */
	@Test
	public void testgetPartValues()
	{
		final String strFileName = sm_dirTestData + "partitioned_private_resources.jdf";
		final JDFParser p = new JDFParser();

		final JDFDoc myJDFDoc = p.parseFile(strFileName);
		final JDFNode myRoot = myJDFDoc.getJDFRoot();
		final JDFResourcePool myResPool = myRoot.getResourcePool();
		JDFResource myPreview = (JDFResource) myResPool.getElement("Preview", "", 0);
		final JDFAttributeMap m = new JDFAttributeMap();

		// m.put("Side", "Front");
		m.put("PreviewType", "Separation");
		// m.put("Separation" , "Black");

		myPreview = myPreview.getDeepPart(m, EnumPartUsage.Explicit);
		final VString vPartValues = myPreview.getPartValues(JDFResource.EnumPartIDKey.Separation);

		assertTrue((vPartValues.elementAt(0)).equals("Cyan"));
		assertTrue((vPartValues.elementAt(1)).equals("Magenta"));
		assertTrue((vPartValues.elementAt(2)).equals("Yellow"));
		assertTrue((vPartValues.elementAt(3)).equals("Black"));
	}

	/**
	 *
	 */
	@Test
	public void testgetPartMapVector()
	{
		final String strFileName = sm_dirTestData + "partitioned_private_resources.jdf";
		final JDFDoc myJDFDoc = JDFDoc.parseFile(strFileName);
		final JDFNode myRoot = myJDFDoc.getJDFRoot();
		final JDFResourcePool myResPool = myRoot.getResourcePool();
		final JDFResource myPreview = (JDFResource) myResPool.getElement("Preview", "", 0);

		final VJDFAttributeMap vJDFAttrMap = myPreview.getPartMapVector(false);

		// there must be 12 maps in the map vector
		assertEquals(vJDFAttrMap.size(), 12);

		for (final JDFAttributeMap myMap : vJDFAttrMap)
		{
			assertTrue(myMap.containsKey("Side"));
			assertTrue(myMap.containsKey("PreviewType"));
			assertTrue(myMap.containsKey("SheetName"));
		}
	}

	/**
	 *
	 */
	@Test
	public void testgetPartMapVectorSequence()
	{
		final JDFResource r = new JDFDoc("JDF").getJDFRoot().addResource("NodeInfo", EnumUsage.Input);
		final JDFResource sheet = r.addPartition(EnumPartIDKey.SignatureName, "s1").addPartition(EnumPartIDKey.SheetName, "1");
		final JDFResource f = sheet.addPartition(EnumPartIDKey.Side, "Front").addPartition(EnumPartIDKey.PartVersion, "v1");
		final JDFResource b = sheet.addPartition(EnumPartIDKey.Side, "Back").addPartition(EnumPartIDKey.PartVersion, "v1");
		final JDFResource sheet2 = r.addPartition(EnumPartIDKey.SignatureName, "s2").addPartition(EnumPartIDKey.SheetName, "2");
		final JDFResource f2 = sheet2.addPartition(EnumPartIDKey.Side, "Front").addPartition(EnumPartIDKey.PartVersion, "v1");
		final JDFResource b2 = sheet2.addPartition(EnumPartIDKey.Side, "Back").addPartition(EnumPartIDKey.PartVersion, "v1");
		final JDFAttributeMap m = new JDFAttributeMap();
		m.put(EnumPartIDKey.SheetName, "1");
		m.put(EnumPartIDKey.PartVersion, "v1");
		final VJDFAttributeMap v = r.getPartMapVector(false);
		assertEquals(v.get(0), f.getPartMap());
		assertEquals(v.get(1), b.getPartMap());
		assertEquals(v.get(2), f2.getPartMap());
		assertEquals(v.get(3), b2.getPartMap());
	}

	/**
	 *
	 */
	@Test
	public void testGetChildrenByTagName()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();

		final JDFResourcePool resPool = root.appendResourcePool();
		final JDFMedia med = (JDFMedia) root.addResource("Media", EnumUsage.Input);
		final JDFExposedMedia expMedia = (JDFExposedMedia) root.addResource("ExposedMedia", EnumUsage.Input);
		final JDFRefElement ref = expMedia.refElement(med);
		VElement ve = resPool.getChildrenByTagName("Media", null, null, false, true, 0);
		assertEquals(ve.size(), 2);
		for (final KElement e : ve)
		{
			final JDFMedia m = (JDFMedia) e;
			assertEquals(m, med);
		}

		ref.setPartMap(new JDFAttributeMap("Side", "Front"));
		ve = resPool.getChildrenByTagName("Media", null, null, false, true, 0);
		assertEquals("we skipped the ref pointing to Nirvana", ve.size(), 1);
		for (final KElement e : ve)
		{
			assertNotNull(e);
		}

	}

	/**
	 *
	 */
	@Test
	public void testGetChildElement()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();

		final JDFResourcePool resPool = root.appendResourcePool();
		final JDFColorantControl cc = (JDFColorantControl) resPool.appendElement(ElementName.COLORANTCONTROL, null);
		for (int i = 0; i < 100; i++)
		{
			cc.addPartition(EnumPartIDKey.SheetName, "Sheet" + i);
		}
		final VString vs = new VString("Cyan Magenta Yello Black", null);
		cc.appendColorantOrder().setSeparations(vs);
		for (int i = 0; i < 100; i++)
		{
			final JDFColorantControl cc2 = (JDFColorantControl) cc.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetName, "Sheet" + i), null);
			assertEquals(vs, cc2.getColorantOrder().getSeparations());
		}
	}

	/**
	 *
	 */
	@Test
	public void testGetChildElementVector()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();

		final JDFResourcePool resPool = root.appendResourcePool();
		final JDFColorantControl colControl = (JDFColorantControl) resPool.appendElement(ElementName.COLORANTCONTROL, null);
		colControl.setProcessColorModel("DeviceCMY");
		final JDFColorantControl ccPart = (JDFColorantControl) colControl.addPartition(EnumPartIDKey.Condition, "Good");
		final KElement a1 = colControl.appendElement("a");
		final KElement a2 = colControl.appendElement("a");
		VElement vChildren = colControl.getChildElementVector("a", null, null, true, 0, true);
		assertEquals(vChildren.size(), 2);
		assertTrue(vChildren.contains(a1));
		assertTrue(vChildren.contains(a2));

		final KElement b1 = ccPart.appendElement("b");
		final KElement b2 = ccPart.appendElement("b");
		// now a leaf
		vChildren = ccPart.getChildElementVector("a", null, null, true, 0, true);
		assertEquals(vChildren.size(), 2);
		assertTrue(vChildren.contains(a1));
		assertTrue(vChildren.contains(a2));

		vChildren = ccPart.getChildElementVector(null, null, null, true, 0, true);
		assertEquals(vChildren.size(), 4);
		assertTrue(vChildren.contains(a1));
		assertTrue(vChildren.contains(a2));
		assertTrue(vChildren.contains(b1));
		assertTrue(vChildren.contains(b2));

		final KElement a3 = ccPart.appendElement("a");
		// now a leaf
		vChildren = ccPart.getChildElementVector("a", null, null, true, 0, true);
		assertEquals(vChildren.size(), 1);
		assertTrue(vChildren.contains(a3));
		assertFalse(vChildren.contains(a2));

		vChildren = ccPart.getChildElementVector(null, null, null, true, 0, true);
		assertEquals(vChildren.size(), 3);
		assertTrue(vChildren.contains(a3));
		assertFalse(vChildren.contains(a2));
		assertTrue(vChildren.contains(b1));
		assertTrue(vChildren.contains(b2));
	}

	// //////////////////////////////////////////////////////////////////////////
	// /////

	/**
	 *
	 */
	@Test
	public void testGetColorPool()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();

		final JDFResourcePool resPool = root.appendResourcePool();
		final JDFColorantControl colControl = (JDFColorantControl) resPool.appendElement(ElementName.COLORANTCONTROL, null);
		colControl.setProcessColorModel("DeviceCMY");
		colControl.appendColorantParams().appendSeparationSpec().setName("Spot");

		final JDFColorSpaceConversionParams cpp = (JDFColorSpaceConversionParams) root.addResource(ElementName.COLORSPACECONVERSIONPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFColorSpaceConversionOp cso = cpp.appendColorSpaceConversionOp();
		final Vector<EnumSourceObjects> sourceObjects = new Vector<>();
		sourceObjects.add(EnumSourceObjects.ImagePhotographic);
		sourceObjects.add(EnumSourceObjects.LineArt);
		cso.setSourceObjects(sourceObjects);

		// getseparations
		final VString vSeps = colControl.getSeparations();
		assertTrue("seps 4", vSeps.size() == 4);
		assertTrue("seps Cyan", vSeps.contains("Cyan"));
		assertTrue("seps Spot", vSeps.contains("Spot"));
		assertTrue("seps no black", !vSeps.contains("Black"));

		final JDFColorPool colorPool = (JDFColorPool) resPool.appendElement(ElementName.COLORPOOL, null);

		colControl.refElement(colorPool);
		// now colControl contains the ref element colPoolRef

		final JDFColorPool colPool2 = colControl.getColorPool();

		// assert that we get the true color pool and not the ref element ...
		assertTrue(colorPool.equals(colPool2));
	}

	/**
	 * tests the correct inheritence of refelements, elements and attributes
	 */
	@Test
	public void testPartitionedAttribute()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(JDFNode.EnumType.ConventionalPrinting.getName(), true);
		final JDFExposedMedia xm = (JDFExposedMedia) root.appendMatchingResource(ElementName.EXPOSEDMEDIA, JDFNode.EnumProcessUsage.AnyInput, null);
		xm.setResolution(new JDFXYPair(300, 300));
		xm.setPolarity(true);
		xm.setProofType(JDFExposedMedia.EnumProofType.Page);
		JDFMedia m = xm.appendMedia();
		m.setDimension(new JDFXYPair(200, 300));
		final JDFExposedMedia xm2 = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.SheetName, "S1");
		assertTrue("Media inline Dimension", xm2.getMedia().getDimension().getX() == 200.);
		assertTrue("ExposedMedia direct Resolution", xm.getResolution().getX() == 300.);
		assertTrue("ExposedMedia inherited Resolution", xm2.getResolution().getX() == 300.);
		m = (JDFMedia) m.makeRootResource(null, null, true);
		assertTrue("Media referenced Dimension", xm2.getMedia().getDimension().getX() == 200.);
		final JDFRefElement re = (JDFRefElement) xm2.getElement("MediaRef");
		assertTrue("refElement found", re != null);

		if (re != null)
		{
			final JDFMedia m2 = (JDFMedia) re.getTarget();
			assertEquals("ref target", m, m2);
		}
		assertTrue("has Media", xm2.hasChildElement("Media", null));
		assertTrue("polarity true", xm2.getPolarity());
		xm2.setPolarity(false);
		assertFalse("polarity false", xm2.getPolarity());

		assertEquals("prooftype", xm2.getProofType(), JDFExposedMedia.EnumProofType.Page);
	}

	/**
	 * tests the correct inheritence of refelements, elements and attributes
	 */
	@Test
	public void testpartitionedElement()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(JDFNode.EnumType.ConventionalPrinting.getName(), true);
		final JDFColorantControl cc = (JDFColorantControl) root.appendMatchingResource(ElementName.COLORANTCONTROL, JDFNode.EnumProcessUsage.AnyInput, null);
		cc.setPartUsage(EnumPartUsage.Implicit);
		final VString seps = StringUtil.tokenize("Cyan Magenta Yellow Black", " ", false);
		cc.appendColorantOrder().setSeparations(seps);
		final JDFColorantControl cc2 = (JDFColorantControl) cc.addPartition(EnumPartIDKey.SheetName, "S1");
		seps.add("smurf blue");
		cc2.appendColorantOrder().setSeparations(seps);
		JDFColorantControl select = (JDFColorantControl) cc.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetName.getName(), "s0"), null);
		assertEquals(cc, select);
		select = (JDFColorantControl) cc.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetName.getName(), "S1"), null);
		assertEquals(cc2, select);
		seps.add("dragon red");
		cc.appendDeviceColorantOrder().setSeparations(seps);
		assertTrue(select.getColorantOrder().getSeparations().contains("smurf blue"));
		assertTrue(select.getDeviceColorantOrder().getSeparations().contains("dragon red"));
	}

	/**
	 *
	 */
	@Test
	public void testUpdateValuesFromLeaves()
	{
		final JDFResource r = (JDFResource) new JDFDoc("Media").getRoot();
		final JDFResource r1 = r.addPartition(EnumPartIDKey.BlockName, "B1");
		final JDFResource r11 = r1.addPartition(EnumPartIDKey.CellIndex, "C1");
		final JDFResource r2 = r.addPartition(EnumPartIDKey.BlockName, "B2");
		final JDFResource r21 = r2.addPartition(EnumPartIDKey.CellIndex, "C1");
		final JDFResource r22 = r2.addPartition(EnumPartIDKey.CellIndex, "C2");
		r11.setAttribute("a", "a1");
		r21.setAttribute("a", "a1");
		r22.setAttribute("a", "a2");
		assertNull(r.updateAttributeFromLeaves("a", null, false));
		r22.setAttribute("a", "a1");
		r.updateAttributeFromLeaves("a", null, false);
		assertEquals(r.getAttribute("a"), "a1");

	}

	/**
	 * tests updateAmounts()
	 */
	@Test
	public void testUpdateAmounts()
	{
		for (int i = 0; i < 2; i++)
		{
			final JDFDoc doc = new JDFDoc(ElementName.JDF);
			final JDFNode root = doc.getJDFRoot();
			if (i == 0)
			{
				root.setType(JDFNode.EnumType.ConventionalPrinting.getName(), true);
			}
			else
			{
				root.setType("fooBar", false); // check whether this works with
				// non-enum types
			}

			final JDFMedia media = (JDFMedia) root.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);
			media.setAmount(100);
			final JDFComponent comp = (JDFComponent) root.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
			final JDFResourceLink rlMedia = root.getLink(media, null);
			final JDFResourceLink rlComp = root.getLink(comp, null);
			final JDFComponent c1 = (JDFComponent) comp.addPartition(EnumPartIDKey.SheetName, "S1");

			final JDFAttributeMap m1 = new JDFAttributeMap(EnumPartIDKey.SheetName, "S1");
			rlComp.setActualAmount(42, m1);
			comp.updateAmounts(false);
			assertEquals(c1.getAmount(), 42., 0.1);
			assertEquals(c1.getAmountProduced(), 42., 0.1);
			comp.updateAmounts(false);
			assertEquals(c1.getAmount(), 42., 0.1);
			assertEquals(c1.getAmountProduced(), 42., 0.1);
			rlMedia.setActualAmount(21, m1);
			media.updateAmounts(true);
			assertEquals("amount=100, - the 21 actual", media.getAmount(), 100 - 21, 0.1);
			assertEquals(media.getAmountRequired(), 100., 0.1);

			rlComp.removeChild(ElementName.AMOUNTPOOL, null, 0);
			m1.put(EnumPartIDKey.Condition, "Good");
			comp.removeAttribute(AttributeName.AMOUNTPRODUCED);
			rlComp.setActualAmount(42, m1);

			m1.put(EnumPartIDKey.Condition, "Waste");
			rlComp.setActualAmount(10, m1);

			m1.put(EnumPartIDKey.Condition, "OtherWaste");
			rlComp.setActualAmount(20, m1);

			comp.updateAmounts(false);
			assertEquals("Anything but Condition=Good is ignored", c1.getAmountProduced(), 42., 0.1);
		}
	}

	/**
	 * tests updateAmounts()
	 */
	@Test
	public void testUpdateAmountsVirtual()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(JDFNode.EnumType.ConventionalPrinting.getName(), true);

		final JDFMedia media = (JDFMedia) root.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);
		media.setAmount(100);
		final JDFComponent comp = (JDFComponent) root.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
		final JDFResourceLink rlComp = root.getLink(comp, null);
		final JDFComponent c1 = (JDFComponent) comp.addPartition(EnumPartIDKey.SheetName, "S1");

		final JDFAttributeMap m1 = new JDFAttributeMap(EnumPartIDKey.SheetName, "S1");
		m1.put("Side", "Front");
		rlComp.setActualAmount(42, m1);
		m1.put("Side", "Back");
		rlComp.setActualAmount(40, m1);
		comp.updateAmounts(false);
		assertEquals(c1.getAmount(), 40., 0.1);
		assertEquals(c1.getAmountProduced(), 40., 0.1);

	}

	/**
	 *
	 */
	@Test
	public void testContainsData()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFResource m = n.addResource("Media", EnumUsage.Input);
		assertFalse(m.containsData());
		m.addPartition(EnumPartIDKey.Side, "Front");
		assertTrue(m.containsData());
		m.unpartition(false);
		assertFalse(m.containsData());
		m.setBrand("abc");
		assertTrue(m.containsData());
	}

	/**
	 *
	 */
	@Test
	public void testCreatePartitions()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFElement root = doc.getJDFRoot();
		final JDFResourcePool resPool = (JDFResourcePool) root.appendElement(ElementName.RESOURCEPOOL, null);
		JDFResource resPreview = resPool.appendResource("Preview", null, null);
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put(EnumPartIDKey.SignatureName, "Sig1");
		partMap.put(EnumPartIDKey.SheetName, "Sheet1");
		partMap.put(EnumPartIDKey.PartVersion, "De");
		final JDFAttributeMap partMap2 = new JDFAttributeMap(partMap);
		partMap2.put(EnumPartIDKey.PartVersion, "En");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(partMap);
		v.add(partMap2);
		VString vs = new VString("SignatureName SheetName PartVersion", " ");
		final VString vs2 = new VString("PartVersion SignatureName SheetName", " ");

		VElement parts = resPreview.createPartitions(v, vs);
		assertEquals(parts.size(), 2);

		VElement parts2 = resPreview.createPartitions(v, vs2);
		assertEquals(parts, parts2);

		// next test
		resPreview.deleteNode();
		resPreview = resPool.appendResource("Preview", null, null);

		vs = new VString("SignatureName SheetName", " ");
		final VJDFAttributeMap vShort = new VJDFAttributeMap();

		final JDFAttributeMap partMapShort = new JDFAttributeMap();
		partMapShort.put(EnumPartIDKey.SignatureName, "Sig1");
		partMapShort.put(EnumPartIDKey.SheetName, "Sheet1");
		vShort.add(partMapShort);

		parts = resPreview.createPartitions(vShort, vs);
		assertEquals(parts.size(), 1);

		parts2 = resPreview.createPartitions(v, vs2);
		assertEquals("add partVersion at end anyhow", parts2.size(), 2);

	}

	/**
	 *
	 */
	// ///////////////////////////////////////////////////////////
	@Test
	public void testGetCreatePartition()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFElement root = doc.getJDFRoot();
		final JDFResourcePool resPool = (JDFResourcePool) root.appendElement(ElementName.RESOURCEPOOL, null);
		final JDFResource resPreview = resPool.appendResource("Preview", JDFResource.EnumResourceClass.Parameter, JDFConstants.EMPTYSTRING);
		final JDFAttributeMap partMap = new JDFAttributeMap();

		// create 2 PreviewType Partition
		partMap.put("PreviewType", "ThumbNail");
		final JDFResource resPreviewType = resPreview.getCreatePartition(partMap, null);
		partMap.clear();
		partMap.put("PreviewType", "Viewable");
		resPreview.getCreatePartition(partMap, null);

		// create a partition with same name under the existing one (MUST FAIL)
		try
		{
			partMap.clear();
			partMap.put("PreviewType", "SeparatedThumbNail");
			resPreviewType.getCreatePartition(partMap, null);
			fail("create a partition with same name");
		}
		catch (final JDFException jdfe)
		{
			// do nothing
		}

		// try to create the partition directly
		// because we create a "SheetName" partition under the
		// "PreviewType="Thumbnail"
		// we dont need to add PreviewType to the partmap
		partMap.clear();
		partMap.put("SheetName", "Cover");
		resPreviewType.getCreatePartition(partMap, null);

		// create subpartition under Preview Viewable
		partMap.clear();
		partMap.put("PreviewType", "Viewable");
		partMap.put("SheetName", "Cover");
		resPreview.getCreatePartition(partMap, null);

		// create two new partitions in one (MUST FAIL)
		try
		{
			partMap.clear();
			partMap.put("PreviewType", "Viewable");
			partMap.put("SheetName", "Cover");
			partMap.put("Side", "Front");
			partMap.put("WebName", "0001");
			resPreview.getCreatePartition(partMap, null);
			// failed
			assertTrue(false);
		}
		catch (final JDFException jdfe)
		{
			// do nothing
		}

		// same as above but this we use the second parameter to create a
		// structure of partitions
		VString struct = new VString();
		struct.add("PreviewType");
		struct.add("SheetName");
		struct.add("Side");
		struct.add("WebName");

		partMap.clear();
		partMap.put("PreviewType", "Viewable");
		partMap.put("SheetName", "Cover");
		partMap.put("Side", "Front");
		partMap.put("WebName", "0001");
		final JDFResource resWebName = resPreview.getCreatePartition(partMap, struct);

		// create a partition while inside a partition
		partMap.clear();
		partMap.put("PreviewType", "Viewable");
		partMap.put("SheetName", "Cover");
		partMap.put("Side", "Front");
		partMap.put("WebName", "0001");
		partMap.put("DocRunIndex", "0001");

		resWebName.getCreatePartition(partMap, null);

		// N�chster Test
		partMap.clear();
		partMap.put("PreviewType", "Viewable");
		partMap.put("SheetName", "Cover");
		partMap.put("Side", "Front");
		partMap.put("WebName", "0001");
		partMap.put("DocRunIndex", "0001");

		struct = new VString();
		struct.add("PreviewType");
		struct.add("SheetName");
		struct.add("Side");
		struct.add("WebName");
		struct.add("DocRunIndex");

		resWebName.getCreatePartition(partMap, struct);

		// N�chster Test
		partMap.clear();
		partMap.put("PreviewType", "Viewable");
		partMap.put("SheetName", "Cover");
		partMap.put("Side", "Front");
		partMap.put("WebName", "0001");
		partMap.put("DocRunIndex", "0001");
		partMap.put("CellIndex", "0002");

		struct = new VString();
		struct.add("PreviewType");
		struct.add("SheetName");
		struct.add("Side");
		struct.add("WebName");
		struct.add("DocRunIndex");
		struct.add("CellIndex");

		resPreview.getCreatePartition(partMap, struct);

		// create a partition while inside a partition
		partMap.clear();
		partMap.put("PreviewType", "Viewable");
		partMap.put("SheetName", "Cover");
		partMap.put("Side", "Front");
		partMap.put("WebName", "0001");
		partMap.put("DocRunIndex", "0001");
		partMap.put("CellIndex", "0002");

		final JDFResource resCellIndex = resPreview.getCreatePartition(partMap, null);

		partMap.clear();
		partMap.put("PreviewType", "Viewable");
		partMap.put("SheetName", "Cover");
		partMap.put("Side", "Front");
		partMap.put("WebName", "0001");
		partMap.put("DocRunIndex", "0001");
		partMap.put("CellIndex", "0002");
		partMap.put("PartVersion", "003");

		resCellIndex.getCreatePartition(partMap, null);

		// n�chster Test
		final JDFResource r = (JDFResource) resPool.getChildByTagName("Preview", null, 0, null, true, true);
		final JDFAttributeMap map = new JDFAttributeMap();
		map.put("PreviewType", "Viewable");
		map.put("SheetName", "Cover");

		final JDFResource part1 = r.getPartition(map, EnumPartUsage.Explicit);
		final JDFResource part2 = r.getCreatePartition(map, null);
		assertTrue("part1 and part2 must be equal", part1.equals(part2));

		map.put("Side", "Front");
		final JDFResource part11 = part1.getPartition(map, EnumPartUsage.Explicit);
		final JDFResource part12 = part1.getCreatePartition(map, null);

		assertTrue("part11 and part12 must be equal", part11.equals(part12));

		doc.write2File(sm_dirTestDataTemp + "testGetCreatePartition.jdf", 0, true);
	}

	// //////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testHasAttribute()
	{
		// TODO - behavior of hasattribute!!!
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFStrippingParams sp = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, null);
		sp.setDescriptiveName("fooBar");
		final JDFStrippingParams spSig = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.SignatureName, "Sig1");
		assertTrue(sp.hasAttribute(AttributeName.DESCRIPTIVENAME));
		assertTrue(spSig.hasAttribute(AttributeName.DESCRIPTIVENAME, null, false));

	}

	// //////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testIdenticalStatus()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType("Product", true);

		final JDFComponent c = (JDFComponent) n.appendMatchingResource("Component", JDFNode.EnumProcessUsage.AnyOutput, null);
		final JDFResourceLink l = n.getMatchingLink("Component", JDFNode.EnumProcessUsage.AnyOutput, 0);
		assertTrue("link exists", l != null);
		final JDFAttributeMap mPart1 = new JDFAttributeMap("SheetName", "S1");
		mPart1.put("Separation", "Yellow");
		final JDFAttributeMap mPart3 = new JDFAttributeMap("SheetName", "S3");
		mPart3.put("Separation", "Yellow");

		final JDFComponent c1 = (JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName, "S1");
		final JDFComponent c1y = (JDFComponent) c1.addPartition(JDFResource.EnumPartIDKey.Separation, "Yellow");
		c1y.setResStatus(EnumResStatus.Available, true);

		final JDFComponent c3 = (JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName, "S3");
		c3.setResStatus(EnumResStatus.Unavailable, true);
		final JDFComponent c3y = (JDFComponent) c3.addPartition(JDFResource.EnumPartIDKey.Separation, "Yellow");
		c3y.setIdentical(c1y);
		assertEquals(EnumResStatus.Available, c3y.getIdenticalTarget().getResStatus(true));
	}

	/**
	 *
	 */
	@Test
	public void testIdentical()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType("Product", true);

		final JDFComponent c = (JDFComponent) n.appendMatchingResource("Component", JDFNode.EnumProcessUsage.AnyOutput, null);
		final JDFResourceLink l = n.getMatchingLink("Component", JDFNode.EnumProcessUsage.AnyOutput, 0);
		assertTrue("link exists", l != null);
		final JDFAttributeMap mPart1 = new JDFAttributeMap("SheetName", "S1");
		mPart1.put("Separation", "Yellow");
		final JDFAttributeMap mPart3 = new JDFAttributeMap("SheetName", "S3");
		mPart3.put("Separation", "Yellow");

		final JDFMedia med = (JDFMedia) n.addResource("Media", null, EnumUsage.Input, null, null, null, null);
		final JDFExposedMedia xmed = (JDFExposedMedia) n.addResource("ExposedMedia", null, EnumUsage.Input, null, null, null, null);
		xmed.refElement(med);

		final JDFComponent c1 = (JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName, "S1");
		c1.addPartition(JDFResource.EnumPartIDKey.Separation, "Cyan");
		final JDFComponent c1y = (JDFComponent) c1.addPartition(JDFResource.EnumPartIDKey.Separation, "Yellow");
		c1.addPartition(JDFResource.EnumPartIDKey.Separation, "Magenta");

		final JDFComponent c2 = (JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName, "S2");
		assertEquals("part ok", c2.getSheetName(), "S2");
		final JDFComponent c3 = (JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName, "S3");
		c3.setAmount(42);
		final JDFAttributeMap map = c3.getPartMap();
		c3.setIdentical(c1);
		assertEquals(map, c3.getPartMap());
		assertNotNull(c3.getIdenticalMap());
		assertEquals(c3.getIdenticalMap(), c1.getPartMap());

		final VElement v = c.getLeaves(false);
		assertEquals("physical leaves are counted", v.size(), 5);
		final VJDFAttributeMap vCMap = c.getPartMapVector(false);
		assertEquals("physical leaves are counted", vCMap.size(), 5);
		c3.removeChild(ElementName.IDENTICAL, null, 0);
		assertNull(c3.getIdenticalMap());
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(c1.getPartMap());
		vMap.add(c3.getPartMap());
		c.setIdentical(vMap);
		assertEquals(map, c3.getPartMap());
		assertNotNull(c3.getIdenticalMap());
		assertEquals(c3.getIdenticalMap(), c1.getPartMap());

		/*
		 * final JDFComponent c3y = (JDFComponent) c.getPartition(mPart3, null); assertEquals("identical", c1y, c3y); c1y = (JDFComponent) c.getPartition(mPart1, null); assertEquals("identical 2",
		 * c1y, c3y);
		 */
		try
		{
			c.setIdentical(c2);
			fail("root set identical");
		}
		catch (final JDFException x)
		{
			//
		}
		/*
		 *
		 * try { c3y.setIdentical(c3y); fail("myself set self"); } catch (final JDFException x) { // } try { c3y.setIdentical(c3); fail("myself set identical"); } catch (final JDFException x) { // }
		 * try { c3y.setIdentical(c1); fail("myself set parent"); } catch (final JDFException x) { // }
		 */
		doc.write2File(sm_dirTestDataTemp + "identical.jdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	public void testIdenticalValid()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType("Product", true);

		final JDFComponent c = (JDFComponent) n.appendMatchingResource("Component", JDFNode.EnumProcessUsage.AnyOutput, null);
		final JDFResourceLink l = n.getMatchingLink("Component", JDFNode.EnumProcessUsage.AnyOutput, 0);
		assertTrue("link exists", l != null);
		final JDFAttributeMap mPart1 = new JDFAttributeMap("SheetName", "S1");
		mPart1.put("Separation", "Yellow");
		final JDFAttributeMap mPart3 = new JDFAttributeMap("SheetName", "S3");
		mPart3.put("Separation", "Yellow");

		final JDFMedia med = (JDFMedia) n.addResource("Media", null, EnumUsage.Input, null, null, null, null);
		final JDFExposedMedia xmed = (JDFExposedMedia) n.addResource("ExposedMedia", null, EnumUsage.Input, null, null, null, null);
		xmed.refElement(med);

		final JDFComponent c1 = (JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName, "S1");
		c1.addPartition(JDFResource.EnumPartIDKey.Separation, "Cyan");
		c1.addPartition(JDFResource.EnumPartIDKey.Separation, "Yellow");
		c1.addPartition(JDFResource.EnumPartIDKey.Separation, "Magenta");

		final JDFComponent c2 = (JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName, "S2");
		assertEquals("part ok", c2.getSheetName(), "S2");
		final JDFComponent c3 = (JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName, "S3");
		c3.setIdentical(c1);
		assertTrue(c3.isValid(EnumValidationLevel.Incomplete));
	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetParentPartition()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFResource r = n.addResource(ElementName.EXPOSEDMEDIA, null);
		final JDFExposedMedia r2 = (JDFExposedMedia) r.addPartition(EnumPartIDKey.BinderySignatureName, "bs1");
		final JDFMedia m = r2.appendMedia();
		assertEquals(r2.getParentPartition(), r);
		assertNull(m.getParentPartition());
		assertNull(r.getParentPartition());
	}

	/**
	 * test whether getpartition works for when inconsistently called
	 */
	@Test
	public void testGetPartition()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.Folding);
		final JDFFoldingParams fp = (JDFFoldingParams) n.addResource(ElementName.FOLDINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFAttributeMap m = new JDFAttributeMap("SignatureName", "Sig1");
		m.put("SheetName", "Sheet1");
		final JDFResource rSheet = fp.getCreatePartition(m, new VString("SignatureName SheetName", " "));
		m.put("BlockName", "Block1");
		JDFResource r = fp.getCreatePartition(m, new VString("SignatureName SheetName BlockName", " "));
		final JDFAttributeMap m2 = new JDFAttributeMap("SignatureName", "Sig1");
		m2.put("SheetName", "Sheet1");
		m2.put("Side", "Front");
		r = fp.getPartition(m2, EnumPartUsage.Implicit);
		assertEquals(r, rSheet);
		r = fp.getPartition(m2, EnumPartUsage.Explicit);
		assertNull(r);
	}

	/**
	 *
	 */
	@Test
	public void testGetPartitionSelf()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource media = n.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);

		final JDFMedia mp1 = (JDFMedia) media.addPartition(EnumPartIDKey.SignatureName, "sig1");
		final JDFMedia m2 = (JDFMedia) mp1.addPartition(EnumPartIDKey.SheetName, "sh1");

		final JDFAttributeMap map = new JDFAttributeMap();
		map.put(EnumPartIDKey.SignatureName, "sig1");
		map.put(EnumPartIDKey.SheetName, "sh1");
		assertEquals(m2, m2.getPartition(map, null));
		assertEquals(m2, mp1.getPartition(map, null));
	}

	/**
	 * test whether getpartition works for when inconsistently called
	 */
	@Test
	public void testGetPartition_PartVersion()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.Folding);
		final JDFFoldingParams fp = (JDFFoldingParams) n.addResource(ElementName.FOLDINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFAttributeMap m = new JDFAttributeMap("SignatureName", "Sig1");
		m.put("SheetName", "Sheet1");
		m.put("PartVersion", "A");
		final JDFResource r = fp.getCreatePartition(m, new VString("SignatureName SheetName PartVersion", null));
		final JDFAttributeMap m2 = new JDFAttributeMap("SignatureName", "Sig1");
		m2.put("SheetName", "Sheet1");
		m2.put("PartVersion", "*");
		JDFResource r2 = fp.getPartition(m2, EnumPartUsage.Implicit);
		assertEquals(r.getParentNode_KElement(), r2);
		r2 = fp.getPartition(m2, EnumPartUsage.Sparse);
		assertNull(r2);
		r2 = fp.getPartition(m2, EnumPartUsage.Explicit);
		assertNull(r2);
	}

	/**
	 * test whether getpartition works for when map has too many keys
	 */
	@Test
	public void testGetPartitionExplicitOver()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.Folding);
		final JDFFoldingParams fp = (JDFFoldingParams) n.addResource(ElementName.FOLDINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFAttributeMap m = new JDFAttributeMap("SignatureName", "Sig1");
		m.put("SheetName", "Sheet1");
		final JDFResource rSheet = fp.getCreatePartition(m, new VString("SignatureName SheetName", " "));
		m.put("BlockName", "Block1");
		JDFResource r = fp.getPartition(m, EnumPartUsage.Explicit);
		assertNull(r);
		r = rSheet.getPartition(m, EnumPartUsage.Explicit);
		assertNull(r);
		r = fp.getCreatePartition(m, null);
		assertNotNull(r);
	}

	/**
	 * test getPartitionMap()
	 */
	@Test
	public void testGetPartitionMap()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.Folding);
		final JDFFoldingParams fp = (JDFFoldingParams) n.addResource(ElementName.FOLDINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFAttributeMap m = new JDFAttributeMap("SignatureName", "Sig1");
		m.put("SheetName", "Sheet1");
		m.put("BlockName", "Block1");
		final JDFResource r1 = fp.getCreatePartition(m, new VString("SignatureName SheetName BlockName", " "));
		m.put("BlockName", "Block2");
		final JDFResource r2 = fp.getCreatePartition(m, new VString("SignatureName SheetName BlockName", " "));

		HashMap<JDFAttributeMap, JDFResource> partitionMap = fp.getPartitionMap();
		assertEquals(partitionMap.size(), 2 + 1 + 1 + 1);
		assertTrue(partitionMap.containsValue(r1));
		assertTrue(partitionMap.containsValue(r2));
		assertTrue(partitionMap.containsValue(fp));

		r2.setIdentical(r1);
		partitionMap = fp.getPartitionMap();
		assertEquals(partitionMap.size(), 2 + 1 + 1 + 1);
		assertTrue(partitionMap.containsValue(r1));
		assertTrue(partitionMap.containsValue(r2));
		assertTrue(partitionMap.containsValue(fp));

		m.put("BlockName", "Block3");
	}

	/**
	 * test getPartitionFromMap()
	 */
	@Test
	public void testGetPartitionFromMap()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.Folding);
		final JDFFoldingParams fp = (JDFFoldingParams) n.addResource(ElementName.FOLDINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFAttributeMap m = new JDFAttributeMap("SignatureName", "Sig1");
		final JDFResource rSig = fp.getCreatePartition(m, new VString("SignatureName SheetName BlockName", null));
		m.put("SheetName", "Sheet1");
		final JDFResource rSh = fp.getCreatePartition(m, new VString("SignatureName SheetName BlockName", null));
		m.put("BlockName", "Block1");
		final JDFResource rb1 = fp.getCreatePartition(m, new VString("SignatureName SheetName BlockName", null));
		m.put("BlockName", "Block2");
		final JDFResource rb2 = fp.getCreatePartition(m, new VString("SignatureName SheetName BlockName", null));
		fp.setPartUsage(EnumPartUsage.Implicit);
		final HashMap<JDFAttributeMap, JDFResource> partitionMap = fp.getPartitionMap();

		assertEquals(fp, fp.getPartition(null, null));
		assertEquals(fp, fp.getPartition(new JDFAttributeMap(), null));
		assertEquals(fp, fp.getPartition(new JDFAttributeMap("Run", "R1"), null));
		assertEquals(fp, fp.getPartition(new JDFAttributeMap("SignatureName", "Sig2"), null));
		JDFAttributeMap mClone = m.clone();
		mClone.put(AttributeName.SHEETNAME, "Sheet2");
		assertEquals(rSig, fp.getPartition(mClone, null));
		mClone = m.clone();
		mClone.put(AttributeName.BLOCKNAME, "B3");
		assertEquals(rSh, fp.getPartition(mClone, null));
		mClone = m.clone();
		mClone.put(AttributeName.RUNPAGE, "3");
		assertEquals(rb2, fp.getPartition(mClone, null));

	}

	/**
	 * test getPartitionFromMap()
	 */
	@Test
	public void testGetPartitionFromMapTime()
	{
		final long t00 = System.currentTimeMillis();
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.Folding);
		final JDFFoldingParams fp = (JDFFoldingParams) n.addResource(ElementName.FOLDINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		for (int i = 1; i < 100; i++)
		{
			final JDFResource rs = fp.addPartition(EnumPartIDKey.SignatureName, "si" + i);
			for (int j = 1; j < 100; j++)
			{
				final JDFResource rss = rs.addPartition(EnumPartIDKey.SheetName, "sh" + j);
			}
		}
		final long tm00 = System.currentTimeMillis() - t00;
		log.info("add time: " + tm00);
		fp.setPartUsage(EnumPartUsage.Implicit);
		final long t0 = System.currentTimeMillis();
		final HashMap<JDFAttributeMap, JDFResource> partitionMap = fp.getPartitionMap();
		final JDFAttributeMap map = new JDFAttributeMap();
		final long tm = System.currentTimeMillis() - t0;
		log.info("map time: " + tm);
		for (int i = 1; i < 200; i++)
		{
			map.put(EnumPartIDKey.SignatureName, "si" + i);
			for (int j = 1; j < 200; j++)
			{
				map.put(EnumPartIDKey.SheetName, "sh" + j);
				final JDFResource fp2 = fp.getPartition(map, null);
				assertNotNull(fp2);
				if (i > 100)
				{
					assertEquals(" " + i + " " + j, fp, fp2);
				}
			}
		}
		final long tt0 = System.currentTimeMillis() - t0;
		log.info("T0 fast: " + tt0);
		log.info("T0 fast no map: " + (tt0 - tm));
		final long t1 = System.currentTimeMillis();
		for (int i = 1; i < 200; i++)
		{
			map.put(EnumPartIDKey.SignatureName, "si" + i);
			for (int j = 1; j < 200; j++)
			{
				map.put(EnumPartIDKey.SheetName, "sh" + j);
				final JDFResource fp2 = fp.getPartition(map, null);
				assertNotNull(fp2);
				if (i > 100)
				{
					assertEquals(fp, fp2);
				}

			}
		}
		final long tt1 = System.currentTimeMillis() - t1;
		log.info("T0 slow: " + tt1);
	}

	/**
	 * test whether getpartition works for lists and ranges
	 */
	@Test
	public void testGetPartsPartition()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFExposedMedia xm = (JDFExposedMedia) n.appendMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, null);
		final JDFExposedMedia xmp = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.PartVersion, "DE FR");
		final JDFMedia m = (JDFMedia) n.appendMatchingResource(ElementName.MEDIA, EnumProcessUsage.AnyInput, null);
		final JDFMedia mp = (JDFMedia) m.addPartition(EnumPartIDKey.SheetIndex, "1 ~ 3");

		// tests for partition list DE from DE FR
		assertEquals(xm.getPartition(new JDFAttributeMap(EnumPartIDKey.PartVersion.getName(), "DE"), null), xmp);
		assertNull(xm.getPartition(new JDFAttributeMap(EnumPartIDKey.PartVersion.getName(), "GR"), null));

		// get 2 from 1~3
		assertEquals(m.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetIndex.getName(), "2"), null), mp);
		assertNull(m.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetIndex.getName(), "42"), null));
	}

	/**
	 * test whether getpartition works for lists and ranges
	 */
	@Test
	public void testStrictPartsPartition()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFResource xmRoot = n.appendMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, null);
		final JDFExposedMedia xm = (JDFExposedMedia) xmRoot.addPartition(EnumPartIDKey.SheetName, "S1");
		final JDFExposedMedia xmp = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.PartVersion, "DE FR");

		// tests for partition list
		// DE from DE FR
		final JDFAttributeMap m1 = new JDFAttributeMap(EnumPartIDKey.PartVersion.getName(), "DE");
		m1.put(EnumPartIDKey.SheetName, "S1");
		assertEquals(xmRoot.getPartition(m1, null), xmp);
		assertEquals(xmRoot.getCreatePartition(m1, null), xmp);
		assertNull(xmRoot.getPartition(new JDFAttributeMap(EnumPartIDKey.PartVersion.getName(), "GR"), null));

		final PartitionGetter pg = new PartitionGetter(xmRoot);
		pg.setStrictPartVersion(true);

		final JDFAttributeMap m = new JDFAttributeMap(EnumPartIDKey.PartVersion.getName(), "DE FR");
		m.put(EnumPartIDKey.SheetName, "S1");
		assertEquals(pg.getPartition(m, null), xmp);
		assertNull(pg.getPartition(m1, null));
		assertEquals(pg.getCreatePartition(m1, null).getPartVersion(), "DE");
	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetPartitionVectorIdentical()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFResource r = n.addResource("ExposedMedia", EnumUsage.Input);
		final JDFResource s1 = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource s2 = r.addPartition(EnumPartIDKey.SheetName, "s2");
		final VString seps = new VString("CYAN MAGENTA YELLOW BLACK", null);
		final VJDFAttributeMap vm = new VJDFAttributeMap();
		final JDFAttributeMap m1 = new JDFAttributeMap(EnumPartIDKey.SheetName, "s1");
		final JDFAttributeMap m2 = new JDFAttributeMap(EnumPartIDKey.SheetName, "s2");
		for (final String sep : seps)
		{
			final JDFResource s11 = s1.addPartition(EnumPartIDKey.Separation, sep);
			final JDFResource s21 = s2.addPartition(EnumPartIDKey.Separation, sep);

			JDFAttributeMap m = new JDFAttributeMap(m1);
			m.put(EnumPartIDKey.Separation, sep);
			vm.add(m);
			m = new JDFAttributeMap(m2);
			m.put(EnumPartIDKey.Separation, sep);
			vm.add(m);
			s21.setIdentical(s11);
		}

		final VElement v = r.getPartitionVector(r.getPartMapVector(false), null);
		assertEquals("explicit identicals are excluded", v.size(), 8 - 4);
	}

	/**
	 *
	 */
	@Test
	public void testGetPartitionVectorIdenticalNoFollowIdentical()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFResource r = n.addResource("ExposedMedia", EnumUsage.Input);
		final JDFResource s1 = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource s2 = r.addPartition(EnumPartIDKey.SheetName, "s2");
		final VString seps = new VString("CYAN MAGENTA YELLOW BLACK", null);
		final VJDFAttributeMap vm = new VJDFAttributeMap();
		final JDFAttributeMap m1 = new JDFAttributeMap(EnumPartIDKey.SheetName, "s1");
		final JDFAttributeMap m2 = new JDFAttributeMap(EnumPartIDKey.SheetName, "s2");
		for (final String sep : seps)
		{
			final JDFResource s11 = s1.addPartition(EnumPartIDKey.Separation, sep);
			final JDFResource s21 = s2.addPartition(EnumPartIDKey.Separation, sep);

			JDFAttributeMap m = new JDFAttributeMap(m1);
			m.put(EnumPartIDKey.Separation, sep);
			vm.add(m);
			m = new JDFAttributeMap(m2);
			m.put(EnumPartIDKey.Separation, sep);
			vm.add(m);
			s21.setIdentical(s11);
		}

		final PartitionGetter pg = new PartitionGetter(r);
		pg.setFollowIdentical(false);
		VElement v = pg.getPartitionVector((VJDFAttributeMap) null, null);
		assertEquals("explicit identicals are not excluded", v.size(), 1);
		v = pg.getPartitionVector(vm, null);
		assertEquals(8, v.size());
		for (final JDFAttributeMap map : vm)
		{
			final JDFResource rr = pg.getPartition(map, null);
			assertEquals(rr.getPartMap(), map);
		}

	}

	/**
	 *
	 */
	@Test
	public void testGetPartitionVector()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		xm.setPartUsage(EnumPartUsage.Implicit);
		final JDFAttributeMap m = new JDFAttributeMap("SignatureName", "Sig1");
		m.put("RunIndex", "3");
		VElement v = xm.getPartitionVector(m, null);
		assertEquals("implicit 1", v.size(), 1);
		final VElement vLeaf = xm.getPartitionLeafVector(m, null);
		assertEquals("implicit leaf 4", vLeaf.size(), 4);

		final JDFResource r = xm.getPartition(m, null);
		assertEquals("same as only sig1", r, xm.getPartition(new JDFAttributeMap("SignatureName", "Sig1"), null));
		int i = 0;
		for (i = 0; i < v.size(); i++)
		{
			final JDFExposedMedia xmp = (JDFExposedMedia) v.elementAt(i);
			assertTrue("overlap of maps", m.overlapMap(xmp.getPartMap()));
		}
		v = xm.getPartitionVector(m, EnumPartUsage.Sparse);
		for (i = 0; i < v.size(); i++)
		{
			final JDFExposedMedia xmp = (JDFExposedMedia) v.elementAt(i);
			assertTrue("overlap of maps", m.overlapMap(xmp.getPartMap()));
		}
		assertEquals("sparse 1", v.size(), 1);

		v = xm.getPartitionVector(m, EnumPartUsage.Explicit);
		assertTrue("no explicit", v.size() == 0);

		m.clear();
		m.put("SignatureName", "Sig1");
		m.put("SheetName", "S3");
		v = xm.getPartitionVector(m, EnumPartUsage.Explicit);
		assertTrue("no explicit", v.size() == 0);
		v = xm.getPartitionVector(m, EnumPartUsage.Sparse);
		assertTrue("no sparse", v.size() == 0);
		v = xm.getPartitionVector(m, EnumPartUsage.Implicit);
		assertEquals("1 impl", v.size(), 1);
		for (i = 0; i < v.size(); i++)
		{
			final JDFExposedMedia xmp = (JDFExposedMedia) v.elementAt(i);
			assertTrue("overlap of maps", m.overlapMap(xmp.getPartMap()));
		}

		m.clear();
		m.put("SignatureName", "Sig1");
		m.put("Side", "Front");

		v = xm.getPartitionVector(m, EnumPartUsage.Explicit);
		assertTrue("explicit key missing", v.size() == 2);
		v = xm.getPartitionVector(m, EnumPartUsage.Sparse);
		assertTrue("no sparse", v.size() == 2);
		v = xm.getPartitionVector(m, EnumPartUsage.Implicit);
		assertEquals("1 impl", v.size(), 2);
		for (i = 0; i < v.size(); i++)
		{
			final JDFExposedMedia xmp = (JDFExposedMedia) v.elementAt(i);
			assertTrue("overlap of maps", m.overlapMap(xmp.getPartMap()));
		}
	}

	/**
	 * check that the vector gets missing levels of prtition correctly
	 */
	@Test
	public void testGetPartionVectorExplicitSkip()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFResource xm = n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		final JDFResource xm2 = xm.addPartition(EnumPartIDKey.SignatureName, "Sig1").addPartition(EnumPartIDKey.SheetName, "s1").addPartition(EnumPartIDKey.Side, "Front");
		final VElement vSeps = xm2.addPartitions(EnumPartIDKey.Separation, new VString("C M Y K", null));
		for (final KElement sep : vSeps)
		{
			((JDFResource) sep).addPartitions(EnumPartIDKey.PartVersion, new VString("DE EN FR ES", null));
		}
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "s1");
		mPart.put("Side", "Front");
		mPart.put("PartVersion", "DE");
		final VElement partitionVector = xm.getPartitionVector(mPart, EnumPartUsage.Explicit);
		assertEquals(4, partitionVector.size());
	}

	/**
	 *
	 */
	@Test
	public void testGetPartitionVectorSkip()
	{
		final JDFResource r = new JDFDoc("JDF").getJDFRoot().addResource("NodeInfo", EnumUsage.Input);
		final JDFResource sheet = r.addPartition(EnumPartIDKey.SignatureName, "s1").addPartition(EnumPartIDKey.SheetName, "1");
		final JDFResource f = sheet.addPartition(EnumPartIDKey.Side, "Front").addPartition(EnumPartIDKey.PartVersion, "v1");
		final JDFResource b = sheet.addPartition(EnumPartIDKey.Side, "Back").addPartition(EnumPartIDKey.PartVersion, "v1");
		final JDFResource sheet2 = r.addPartition(EnumPartIDKey.SignatureName, "s2").addPartition(EnumPartIDKey.SheetName, "2");
		final JDFResource f2 = sheet2.addPartition(EnumPartIDKey.Side, "Front").addPartition(EnumPartIDKey.PartVersion, "v1");
		final JDFResource b2 = sheet2.addPartition(EnumPartIDKey.Side, "Back").addPartition(EnumPartIDKey.PartVersion, "v1");
		assertNotNull(b2);
		assertNotNull(f2);
		final JDFAttributeMap m = new JDFAttributeMap();
		m.put(EnumPartIDKey.SheetName, "1");
		m.put(EnumPartIDKey.PartVersion, "v1");
		final VElement v = r.getPartitionVector(m, EnumPartUsage.Explicit);
		assertTrue(v.contains(f));
		assertTrue(v.contains(b));
	}

	/**
	 *
	 */
	@Test
	public void testDeleteUnlinked()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA, JDFNode.EnumProcessUsage.AnyInput, null, 0);
		JDFMedia m = xm.getMedia();
		m = (JDFMedia) m.makeRootResource(null, null, true);
		final JDFResourceLink rl = n.linkResource(m, EnumUsage.Input, null);
		assertFalse(m.deleteUnLinked());
		xm.getElement_KElement("MediaRef", null, 0).deleteNode();
		assertFalse(m.deleteUnLinked());
		xm.refElement(m);
		rl.deleteNode();
		assertFalse(m.deleteUnLinked());
		n.removeResource(ElementName.EXPOSEDMEDIA, 0);
		assertNull(m.getParentNode());

	}

	/**
	 *
	 */
	@Test
	public void testGetLinks()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		JDFMedia m = xm.getMedia();
		m = (JDFMedia) m.makeRootResource(null, null, true);
		VElement v = m.getLinks(null, null);
		assertEquals(v.size(), 1);
		final JDFResourceLink rl = n.linkResource(m, EnumUsage.Input, null);
		v = m.getLinks(null, null);
		assertEquals(v.size(), 2);
		assertTrue(v.contains(rl));

	}

	/**
	 *
	 */
	@Test
	public void testGetLinksAndRefsPerformance()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.Product);
		final long t0 = System.currentTimeMillis();
		final VElement vM = new VElement();
		for (int i = 0; i < 500; i++)
		{
			final JDFNode n2 = n.addJDFNode(EnumType.ImageSetting);
			final JDFNode n3 = n.addJDFNode(EnumType.ConventionalPrinting);
			final JDFMedia m = (JDFMedia) n2.addResource("Media", null, EnumUsage.Input, null, n, null, null);
			final JDFExposedMedia xm = (JDFExposedMedia) n2.addResource("ExposedMedia", null, EnumUsage.Output, null, n, null, null);
			xm.refElement(m);
			n3.linkResource(xm, EnumUsage.Input, null);
			n.getAuditPool().addModified(null, n2);
			n.getAuditPool().addModified(null, n3);
			vM.add(m);
		}
		final long t1 = System.currentTimeMillis();
		log.info("time: " + (t1 - t0));
		final JDFMedia mNew = (JDFMedia) n.addResource("Media", null);
		final JDFExposedMedia xmNew = (JDFExposedMedia) n.addResource("ExposedMedia", null);
		assertNotNull(xmNew);
		for (int i = 0; i < 500; i++)
		{
			final JDFMedia p = (JDFMedia) mNew.addPartition(EnumPartIDKey.SheetName, "S" + i);
			assertNotNull(p);
			final JDFMedia m2 = (JDFMedia) vM.get(i);
			final VElement vRef = m2.getLinksAndRefs(true, true);
			assertEquals("A link and a ref", vRef.size(), 2);

		}
		final long t2 = System.currentTimeMillis();
		log.info("time: " + (t2 - t1));

		final LinkRefFinder lrf = new LinkRefFinder(true, true);
		final VectorMap<String, KElement> vm = lrf.getMap(n);
		final long t3 = System.currentTimeMillis();
		log.info("time: " + (t3 - t2));
		for (int i = 0; i < 500; i++)
		{
			final JDFMedia m2 = (JDFMedia) vM.get(i);
			final VElement vRef = new VElement(vm.get(m2.getID()));
			assertNotNull(vRef);
			assertEquals("A link and a ref", vRef.size(), 2);
		}
		final long t4 = System.currentTimeMillis();
		log.info("time: " + (t4 - t3));
	}

	/**
	 *
	 */
	@Test
	public void testGetLinksAndRefs()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		JDFMedia m = xm.getMedia();
		m = (JDFMedia) m.makeRootResource(null, null, true);
		VElement v = m.getLinksAndRefs(false, true);
		assertEquals(v.size(), 1);
		final JDFResourceLink rl = n.linkResource(m, EnumUsage.Input, null);
		v = m.getLinksAndRefs(false, true);
		assertEquals(v.size(), 1);
		v = m.getLinksAndRefs(true, false);
		assertEquals(v.size(), 1);
		assertTrue(v.contains(rl));

		v = m.getLinksAndRefs(true, true);
		assertEquals(v.size(), 2);
		assertTrue(v.contains(rl));

		final JDFMedia mPart = (JDFMedia) m.addPartition(EnumPartIDKey.SignatureName, "Sig1");
		v = mPart.getLinksAndRefs(true, true);
		assertEquals("partitioned resource has no links", v.size(), 2);
		final JDFAttributeMap myMap = new JDFAttributeMap();
		myMap.put("SignatureName", "Sig2");
		rl.setPartMap(myMap);

		xm.getElement_KElement("MediaRef", null, 0).deleteNode();

		v = mPart.getLinksAndRefs(true, true);
		assertNull("partitioned resource has no links", v);

		myMap.put("SignatureName", "Sig1");
		rl.setPartMap(myMap);
		v = mPart.getLinksAndRefs(true, true);
		assertEquals("partitioned resource has one link", v.size(), 1);

	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetLocalPartitionKey()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		JDFExposedMedia xmLeaf = (JDFExposedMedia) xm.getLeaves(false).elementAt(1);
		assertEquals(xmLeaf.getLocalPartitionKey(), "Side");
		xmLeaf = (JDFExposedMedia) xmLeaf.getParentNode_KElement();
		assertEquals(xmLeaf.getLocalPartitionKey(), "SheetName");
		xmLeaf = (JDFExposedMedia) xmLeaf.getParentNode_KElement();
		assertEquals(xmLeaf.getLocalPartitionKey(), "SignatureName");
		xmLeaf = (JDFExposedMedia) xmLeaf.getParentNode_KElement();
		assertNull(xmLeaf.getLocalPartitionKey());
	}

	// //////////////////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testBuildXPath()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		final JDFExposedMedia xmLeaf = (JDFExposedMedia) xm.getLeaves(false).elementAt(1);
		assertEquals(xmLeaf.buildXPath(null, 2), "/JDF/ResourcePool[1]/ExposedMedia[1]/ExposedMedia[@SignatureName=\"Sig1\"]/ExposedMedia[@SheetName=\"S1\"]/ExposedMedia[@Side=\"Back\"]");
	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testBinderySignatureName()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		final JDFBinderySignature bs = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final JDFBinderySignature bsp = (JDFBinderySignature) bs.addPartition(EnumPartIDKey.BinderySignatureName, "bad name");
		assertFalse("Bad BS partidkey value - should be nmtoken", bsp.isValid(EnumValidationLevel.Incomplete));
	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testInvalidPartIDKeysLeaves()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		xm.setPartIDKeys(new VString("fnarf", " "));
		assertTrue(xm.getInvalidAttributes(EnumValidationLevel.Incomplete, true, -1).contains(AttributeName.PARTIDKEYS));
	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testExplicitPartUsage()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFResource pv = n.addResource("Preview", EnumUsage.Input);
		pv.setPartUsage(EnumPartUsage.Explicit);
		final JDFResource pv1 = pv.addPartition(EnumPartIDKey.Separation, "Cyan");
		pv.setResStatus(EnumResStatus.Unavailable, false);
		assertFalse(pv.isValid(EnumValidationLevel.Complete));
		assertFalse(pv1.isValid(EnumValidationLevel.Complete));
		pv1.setResStatus(EnumResStatus.Incomplete, false);
		assertTrue(pv.isValid(EnumValidationLevel.Complete));
		assertTrue(pv1.isValid(EnumValidationLevel.Complete));
		final JDFResource pv2 = pv.addPartition(EnumPartIDKey.Separation, "Blue");
		assertFalse(pv.isValid(EnumValidationLevel.Complete));
		assertFalse(pv2.isValid(EnumValidationLevel.Complete));
		assertTrue(pv1.isValid(EnumValidationLevel.Complete));

	}

	// //////////////////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testInvalidPartUsage()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		xm.setPartUsage(EnumPartUsage.Sparse);
		assertFalse(xm.getInvalidAttributes(EnumValidationLevel.Incomplete, true, 999).contains("PartUsage"));
		n.setVersion(EnumVersion.Version_1_1);
		assertTrue(xm.getInvalidAttributes(EnumValidationLevel.Incomplete, true, 999).contains("PartUsage"));
		n.setVersion(EnumVersion.Version_1_2);
		assertTrue(xm.getInvalidAttributes(EnumValidationLevel.Incomplete, true, 999).contains("PartUsage"));
		assertFalse(xm.getInvalidAttributes(EnumValidationLevel.NoWarnIncomplete, true, 999).contains("PartUsage"));
	}

	// //////////////////////////////////////////////////////////////////////////
	/**
	*
	*/
	@Test
	public void testGetLeaves()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);

		VElement vL = xm.getLeaves(false);
		assertEquals("size false", vL.size(), 8);
		for (int i = 0; i < vL.size(); i++)
		{
			final JDFExposedMedia xm2 = (JDFExposedMedia) vL.elementAt(i);
			assertEquals("map ok", xm2.getPartMap().size(), 3);
		}

		vL = xm.getLeaves(true);
		assertEquals("size false", vL.size(), 15);
	}

	/**
	*
	*/
	@Test
	public void testGetMinStatusFromLeaves()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource(ElementName.EXPOSEDMEDIA, JDFNode.EnumProcessUsage.AnyInput, null, 0);

		xm.setResStatus(EnumResStatus.Incomplete, true);
		assertEquals(xm.getStatusFromLeaves(false), EnumResStatus.Incomplete);
		assertEquals(xm.getStatusFromLeaves(true), EnumResStatus.Incomplete);

		xm.setResStatus(EnumResStatus.Available, true);
		assertEquals(xm.getStatusFromLeaves(false), EnumResStatus.Available);
		assertEquals(xm.getStatusFromLeaves(true), EnumResStatus.Available);

		xm.setResStatus(EnumResStatus.Incomplete, true);

		final VElement vL = xm.getLeaves(false);
		for (int i = 0; i < vL.size(); i++)
		{
			final JDFResource r = (JDFResource) vL.elementAt(i);
			r.setResStatus(EnumResStatus.InUse, false);
		}
		assertEquals(xm.getStatusFromLeaves(false), EnumResStatus.InUse);
		assertEquals(xm.getStatusFromLeaves(true), EnumResStatus.Incomplete);

	}

	/**
	 * @return the doc
	 *
	 */
	public static JDFDoc creatRLDoc()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setJobPartID("P1");
		n.setJobID("J1");
		n.setType("Interpreting", true);

		final JDFRunList rl = (JDFRunList) n.appendMatchingResource("RunList", JDFNode.EnumProcessUsage.AnyInput, null);
		for (int i = 1; i < 3; i++)
		{
			final JDFRunList rlset = (JDFRunList) rl.addPartition(EnumPartIDKey.RunSet, "Set" + String.valueOf(i));
			final VString filNames = StringUtil.tokenize("FCyan.pdf FMagenta.pdf FYellow.pdf FBlack.pdf", " ", false);
			final VString sepNames = StringUtil.tokenize("Cyan Magenta Yellow Black", " ", false);
			final JDFRunList rlRun = rlset.addSepRun(filNames, sepNames, 0, 16, false);
			rlRun.setRun("Run" + String.valueOf(i));
			rlRun.setSorted(true);
			rlRun.appendElement("foo:bar", "www.foobar.com");
		}

		return doc;
	}

	/**
	*
	*/
	@Test
	public void testSubElement()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		JDFMedia m = xm.getMedia();
		// assertEquals("Media in XM class", m.getResourceClass(),
		// EnumResourceClass.Consumable);
		m.setBrand("fooBrand");
		assertTrue("xm valid", xm.isValid(EnumValidationLevel.Complete));
		assertTrue("m valid", m.isValid(EnumValidationLevel.Complete));
		m.deleteNode();
		m = xm.appendMedia();
		m.setBrand("barBrand");
		assertTrue("xm valid", xm.isValid(EnumValidationLevel.Complete));
		assertTrue("m valid", m.isValid(EnumValidationLevel.Complete));

	}

	/**
	*
	*/
	@Test
	public void testSetAttributes()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		final JDFResource r1 = n.addResource(ElementName.SPINETAPINGPARAMS, EnumUsage.Input);
		final JDFResource r2 = n.addResource(ElementName.SPINETAPINGPARAMS, EnumUsage.Input);
		final JDFResource r1p = r1.addPartition(EnumPartIDKey.SheetName, "s1").addPartition(EnumPartIDKey.Side, EnumSide.Front);
		r1p.setAgentName("a1");
		r1.setAgentVersion("v1");
		final JDFResource r2p = r2.addPartition(EnumPartIDKey.SheetName, "s2").addPartition(EnumPartIDKey.Side, EnumSide.Back);
		r2p.setAttributes(r1p);
		assertEquals("child copied", r2p.getAgentName(), "a1");
		assertEquals("root copied", r2p.getAgentVersion(), "v1");
		assertEquals("parent partIDKey not copied", r2p.getSheetName(), "s2");
		assertEquals("leaf partIDKey copied", r2p.getSide(), EnumSide.Front);
	}

	/**
	*
	*/
	@Test
	public void testSetAttributePartition()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		final JDFResource r1 = n.addResource(ElementName.SPINETAPINGPARAMS, EnumUsage.Input);
		final JDFAttributeMap m1 = new JDFAttributeMap(EnumPartIDKey.SheetName, "s1");
		final JDFResource r1p = r1.getCreatePartition(m1, null);
		final JDFAttributeMap m2 = new JDFAttributeMap(EnumPartIDKey.Side, EnumSide.Front.getName());
		m2.putAll(m1);
		final JDFResource r2p = r1.getCreatePartition(m2, null);
		r1p.setAttribute(AttributeName.SHEETNAME, "s2");
		assertNull(r1.getPartition(m1, EnumPartUsage.Explicit));
		assertNull(r1.getPartition(m2, EnumPartUsage.Explicit));

		m1.put(AttributeName.SHEETNAME, "s2");
		m2.put(AttributeName.SHEETNAME, "s2");

		assertEquals(r1p, r1.getPartition(m1, EnumPartUsage.Explicit));
		assertEquals(r2p, r1.getPartition(m2, EnumPartUsage.Explicit));

	}

	/**
	*
	*/
	@Test
	public void testRunPage()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		JDFResource r1 = n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		assertEquals("leaf partIDKey copied", r1.getRunPage(), 0);
		assertFalse(r1.hasAttribute(AttributeName.RUNPAGE));
		r1 = r1.addPartition(EnumPartIDKey.RunPage, "2");
		assertEquals(r1.getRunPage(), 2);
	}

	/**
	*
	*/
	@Test
	public void testSetLocked()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		xm.setLocked(false);
		assertFalse(xm.getLocked());
		assertFalse(xm.hasAttribute(AttributeName.LOCKED));
		final VElement vL = xm.getLeaves(false);
		for (int i = 0; i < 1; i++)
		{
			xm.setLocked(false);
			final JDFExposedMedia xm3 = (JDFExposedMedia) vL.elementAt(i);
			final JDFExposedMedia xm2 = (JDFExposedMedia) xm3.getParentNode();
			assertFalse(xm2.getLocked());
			assertFalse(xm3.getLocked());
			assertFalse(xm2.hasAttribute(AttributeName.LOCKED));
			assertFalse(xm3.hasAttribute(AttributeName.LOCKED));
			xm.setLocked(true);
			assertTrue(xm.getLocked());
			assertTrue(xm2.getLocked());
			assertTrue(xm3.getLocked());
			assertFalse(xm2.hasAttribute(AttributeName.LOCKED));
			assertFalse(xm3.hasAttribute(AttributeName.LOCKED));
			xm2.setLocked(false);
			assertFalse(xm2.getLocked());
			assertFalse(xm3.getLocked());
			assertTrue(xm2.hasAttribute(AttributeName.LOCKED));
			assertFalse(xm3.hasAttribute(AttributeName.LOCKED));
			xm3.setLocked(true);
			assertFalse(xm2.getLocked());
			assertTrue(xm3.getLocked());
			assertTrue(xm2.hasAttribute(AttributeName.LOCKED));
			assertTrue(xm3.hasAttribute(AttributeName.LOCKED));

		}
	}

	// ////////////////////////////////////////////////////////////////////////
	/**
	*
	*/
	@Test
	public void testImplicitPartitions()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		assertNull("xm no impicit part", xm.getImplicitPartitions());
		final JDFRunList ruli = (JDFRunList) n.addResource(ElementName.RUNLIST, null, EnumUsage.Input, null, null, null, null);
		final JDFCutBlock cb = (JDFCutBlock) n.addResource(ElementName.CUTBLOCK, null, EnumUsage.Input, null, null, null, null);
		try
		{
			ruli.addPartition(EnumPartIDKey.RunIndex, "1");
			fail("no go here");
		}
		catch (final JDFException e)
		{
			// nop
		}
		try
		{
			cb.addPartition(EnumPartIDKey.BlockName, "1");
			fail("no go here");
		}
		catch (final JDFException e)
		{
			// nop
		}
		assertFalse("pidk", ruli.hasAttribute(AttributeName.PARTIDKEYS));
		assertFalse("pidk", cb.hasAttribute(AttributeName.PARTIDKEYS));
		ruli.addPartition(EnumPartIDKey.SheetName, "S1");
		assertEquals("pidk", ruli.getAttribute(AttributeName.PARTIDKEYS), EnumPartIDKey.SheetName.getName());
	}

	// //////////////////////////////////////////////////////////////////

	/**
	 * test reduction of partitions
	 */
	@Test
	public void testReducePartitions()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);

		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "S1");
		mPart.put("Side", "Front");
		final JDFAttributeMap mPart2 = new JDFAttributeMap("SignatureName", "Sig2");
		mPart2.put("SheetName", "S1");
		mPart2.put("Side", "Front");
		final VJDFAttributeMap vm = new VJDFAttributeMap();
		vm.add(mPart);
		vm.add(mPart2);

		assertNotNull(xm.getPartition(mPart, null));
		assertNotNull(xm.getPartition(mPart2, null));
		xm.reducePartitions(vm);
		assertNotNull(xm.getPartition(mPart, null));
		assertNotNull("Sig2 is still contained in vmap", xm.getPartition(mPart2, null));
		vm.removeElementAt(1);
		xm.reducePartitions(vm);
		assertNotNull(xm.getPartition(mPart, null));
		assertNull("Sig2 was removed from vmap", xm.getPartition(mPart2, null));
	}

	/**
	 *
	 */
	@Test
	public void testRemoveImplicitPartions()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.Interpreting);
		final JDFRunList rul = (JDFRunList) n.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);

		// tests for partition list
		assertEquals(rul.getPartition(new JDFAttributeMap(EnumPartIDKey.RunIndex.getName(), "2~5"), null), rul);
		assertNull(rul.getPartition(new JDFAttributeMap(EnumPartIDKey.PartVersion.getName(), "GR"), null));
	}

	/**
	 *
	 */
	@Test
	public void testRemoveInheritedAttributes()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.Interpreting);
		final JDFRunList rul = (JDFRunList) n.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);
		final JDFResource part = rul.addPartition(EnumPartIDKey.Run, "r1");
		part.setProductID("P2");
		rul.setProductID("P1");
		assertNotNull(part.getAttribute("ProductID", null, null));
		assertNotNull(rul.getAttribute("ProductID", null, null));
		part.removeInheritedAttributes("ProductID", null);
		assertNull(part.getAttribute("ProductID", null, null));
		assertNull(rul.getAttribute("ProductID", null, null));

	}

	// //////////////////////////////////////////////////////////////////
	/**
	 * test expand and collapse method for subelements
	 */
	@Test
	public void testCollapseElement()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		final JDFLayoutElement le = rl.appendLayoutElement();
		final JDFSeparationSpec ss1 = le.appendSeparationSpec();
		ss1.setName("n1");
		final JDFRunList rl1 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r1");
		rl1.copyElement(le, null);
		final JDFRunList rl2 = (JDFRunList) rl.addPartition(EnumPartIDKey.Run, "r2");
		rl2.copyElement(le, null);
		ss1.setName("n2");
		rl.collapse(false, false);
		assertNotSame("no collapse elements", rl.getLayoutElement(), rl1.getLayoutElement());
		rl.collapse(false, true);
		assertNotSame("root element is different", rl.getLayoutElement(), rl1.getLayoutElement());
		ss1.setName("n1");
		rl.collapse(false, false);
		assertNotSame("no collapse elements", rl.getLayoutElement(), rl1.getLayoutElement());
		rl.collapse(false, true);
		assertEquals("root element is same", rl.getLayoutElement(), rl1.getLayoutElement());
		rl.expand(false);
		assertNotSame("expanded elements", rl.getLayoutElement(), rl1.getLayoutElement());
		rl.collapse(true, true);
		assertEquals("root element is same", rl.getLayoutElement(), rl1.getLayoutElement());
		rl.expand(true);
		assertNull("expanded elements", rl.getLayoutElement());
		assertNotSame("expanded elements", rl.getLayoutElement(), rl1.getLayoutElement());
		rl.collapse(true, true);
		assertNull("no root element for collapse", rl.getLayoutElement());
		assertNotSame("no root element for collapse", rl.getLayoutElement(), rl1.getLayoutElement());
	}

	// //////////////////////////////////////////////////////////////////
	/**
	 * test expand and collapse methods
	 */
	@Test
	public void testCollapseSubElement()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		final JDFRunList rl = (JDFRunList) n.addResource("RunList", EnumUsage.Input);
		final JDFLayoutElement le = rl.appendLayoutElement();
		final JDFSeparationSpec ss1 = le.appendSeparationSpec();
		ss1.setName("n1");
		final JDFSeparationSpec ss2 = le.appendSeparationSpec();
		ss2.setName("n2");
		rl.addPartition(EnumPartIDKey.Run, "r1");
		rl.addPartition(EnumPartIDKey.Run, "r2");
		rl.collapse(true, true);
		assertEquals(le.getSeparationSpec(0), ss1);
		assertEquals(le.getSeparationSpec(1), ss2);

		rl.collapse(false, true);
		assertEquals(le.getSeparationSpec(0), ss1);
		assertEquals(le.getSeparationSpec(1), ss2);
		le.collapse(true, true);
		assertEquals(le.getSeparationSpec(0), ss1);
		assertEquals(le.getSeparationSpec(1), ss2);
		le.collapse(false, true);
		assertEquals(le.getSeparationSpec(0), ss1);
		assertEquals(le.getSeparationSpec(1), ss2);
	}

	/**
	 *
	 */
	public void testSubelementClass()
	{
		final boolean b = JDFResource.getAutoSubElementClass();
		JDFResource.setAutoSubElementClass(true);
		final KElement k = new JDFDoc("JDF").getJDFRoot().appendResourcePool().appendElement(ElementName.RUNLIST).appendElement(ElementName.LAYOUTELEMENT);
		assertEquals(((JDFResource) k).getResourceClass(), EnumResourceClass.Parameter);
		JDFResource.setAutoSubElementClass(false);
		final KElement k2 = new JDFDoc("JDF").getJDFRoot().appendResourcePool().appendElement(ElementName.RUNLIST).appendElement(ElementName.LAYOUTELEMENT);
		assertNull(((JDFResource) k2).getResourceClass());
		JDFResource.setAutoSubElementClass(b);
	}

	/**
	 * test clonePartitions method
	 */
	@Test
	public void testClonePartions()
	{
		final KElement pool = new JDFDoc("ResourcePool").getRoot();
		final JDFResource r0 = (JDFResource) pool.appendElement("Preview");
		final JDFResource s1 = r0.addPartition(EnumPartIDKey.SignatureName, "s1");
		final JDFResource sh1 = r0.addPartition(EnumPartIDKey.SignatureName, "s2").addPartition(EnumPartIDKey.SheetName, "sh1");
		final JDFResource r1 = (JDFResource) pool.appendElement("Layout");
		r1.clonePartitions(r0, null);
		int size = r1.getLeaves(false).size();
		assertEquals(size, r0.getLeaves(false).size());
		for (int i = 0; i < size; i++)
		{
			assertEquals(((JDFResource) r1.getLeaves(false).get(i)).getPartMap(), ((JDFResource) r0.getLeaves(false).get(i)).getPartMap());
		}
		r0.addPartition(EnumPartIDKey.SignatureName, "s3").addPartition(EnumPartIDKey.SheetName, "sh1");
		r1.clonePartitions(r0, null);
		size = r1.getLeaves(false).size();
		assertEquals(" after second application ", size, r0.getLeaves(false).size());
		for (int i = 0; i < size; i++)
		{
			assertEquals(((JDFResource) r1.getLeaves(false).get(i)).getPartMap(), ((JDFResource) r0.getLeaves(false).get(i)).getPartMap());
		}
		final JDFResource r2 = (JDFResource) pool.appendElement("Layout");
		r2.clonePartitions(r0, new VString("SignatureName", null));
		size = r2.getLeaves(false).size();
		assertEquals(" after third application - only signatureName", size, 3);
		for (int i = 0; i < size; i++)
		{
			assertEquals(((JDFResource) r2.getLeaves(false).get(i)).getPartMap().size(), 1);
		}

		final JDFResource r3 = (JDFResource) pool.appendElement("Layout");
		r3.clonePartitions(s1, null);
		size = r3.getLeaves(false).size();
		assertEquals(" partial clone: after 4th application - only signatureName", size, 1);
		for (int i = 0; i < size; i++)
		{
			assertEquals(((JDFResource) r3.getLeaves(false).get(i)).getPartMap().size(), 1);
		}

		final JDFResource r4 = (JDFResource) pool.appendElement("Layout");
		r4.clonePartitions(sh1, null);
		size = r4.getLeaves(false).size();
		assertEquals(" partial clone: after 5th application - only signatureName, sheetname 1", size, 1);
		for (int i = 0; i < size; i++)
		{
			assertEquals(((JDFResource) r4.getLeaves(false).get(i)).getPartMap().size(), 2);
		}
		r4.clonePartitions(s1, null);
		size = r4.getLeaves(false).size();
		assertEquals(" multiple partial clone: after 5th application - only signatureName, sheetname 1", size, 2);

	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	public void testCollapseUniqueTree()
	{
		final JDFNode n = JDFNode.createRoot();

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFResource sig = dpp.addPartition(EnumPartIDKey.SignatureName, "s1");
		final JDFResource sh = sig.addPartition(EnumPartIDKey.SheetName, "sh1");
		final JDFResource f = sh.addPartition(EnumPartIDKey.Side, "Front");
		final JDFResource b = sh.addPartition(EnumPartIDKey.Side, "Back");
		f.setDescriptiveName("s");
		b.setDescriptiveName("s");
		sh.setDescriptiveName("s");
		dpp.collapse(false, false);
		assertEquals("s", dpp.getDescriptiveName());
		assertFalse(f.hasAttribute_KElement(AttributeName.DESCRIPTIVENAME, null, false));
		assertFalse(b.hasAttribute_KElement(AttributeName.DESCRIPTIVENAME, null, false));
		assertFalse(sh.hasAttribute_KElement(AttributeName.DESCRIPTIVENAME, null, false));
	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	public void testDeleteNodePartition()
	{
		final JDFNode n = JDFNode.createRoot();

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFResource sig = dpp.addPartition(EnumPartIDKey.SignatureName, "s1");
		final JDFResource sh = sig.addPartition(EnumPartIDKey.SheetName, "sh1");
		final JDFResource f = sh.addPartition(EnumPartIDKey.Side, "Front");
		final JDFResource b = sh.addPartition(EnumPartIDKey.Side, "Back");
		assertEquals(b, sh.getPartition(new JDFAttributeMap(EnumPartIDKey.Side, "Back"), EnumPartUsage.Explicit));
		b.deleteNode();
		assertNull(sh.getPartition(new JDFAttributeMap(EnumPartIDKey.Side, "Back"), EnumPartUsage.Explicit));
		assertEquals(f, sh.getPartition(new JDFAttributeMap(EnumPartIDKey.Side, "Front"), EnumPartUsage.Explicit));
	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	public void testDeleteNodePartition2()
	{
		final JDFNode n = JDFNode.createRoot();

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFResource sig = dpp.addPartition(EnumPartIDKey.SignatureName, "s1");
		final JDFResource sh = sig.addPartition(EnumPartIDKey.SheetName, "sh1");
		final JDFResource f = sh.addPartition(EnumPartIDKey.Side, "Front");
		final JDFResource b = sh.addPartition(EnumPartIDKey.Side, "Back");
		final JDFAttributeMap m = new JDFAttributeMap(EnumPartIDKey.Side, "Back");
		m.put(EnumPartIDKey.SheetName, "sh1");
		assertEquals(b, sig.getPartition(m, EnumPartUsage.Explicit));
		sh.deleteNode();
		assertNull(sig.getPartition(m, EnumPartUsage.Explicit));
	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	public void testCollapseUniqueLeaves()
	{
		final JDFNode n = JDFNode.createRoot();

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFResource sig = dpp.addPartition(EnumPartIDKey.SignatureName, "s1");
		final JDFResource sh = sig.addPartition(EnumPartIDKey.SheetName, "sh1");
		final JDFResource f = sh.addPartition(EnumPartIDKey.Side, "Front");
		final JDFResource b = sh.addPartition(EnumPartIDKey.Side, "Back");
		f.setDescriptiveName("s");
		b.setDescriptiveName("s");
		sh.setDescriptiveName("sh");
		dpp.collapse(false, false);
		assertEquals("sh", sh.getDescriptiveName());
	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	public void testCollapse()
	{
		final JDFDoc doc = creatRLDoc();
		final JDFNode n = doc.getJDFRoot();

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		dpp.collapse(true, true);
		dpp.collapse(false, true);

		final JDFRunList rl = (JDFRunList) n.getMatchingResource("RunList", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		final JDFAttributeMap map = new JDFAttributeMap();
		map.put("RunSet", "Set2");
		final JDFRunList rlSet = (JDFRunList) rl.getPartition(map, null);
		assertNotNull(rlSet);
		map.put("Run", "Run2");
		final JDFRunList rlRun = (JDFRunList) rl.getPartition(map, null);
		assertNotNull(rlRun);
		map.put("Separation", "Cyan");
		final JDFRunList rlSep = (JDFRunList) rl.getPartition(map, null);
		assertNotNull(rlSep);
		assertTrue(rlRun.getIsPage());

		assertFalse(rlSep.getIsPage());
		rlRun.collapse(true, true);
		assertTrue(rlRun.getIsPage());
		assertFalse(rlSep.getIsPage());
		assertTrue(rlSet.getIsPage());
		assertTrue(rl.getIsPage());
		rlRun.collapse(false, true);
		assertTrue(rlRun.getIsPage());
		assertFalse(rlSep.getIsPage());
		assertTrue(rlSet.getIsPage());
		assertTrue(rl.getIsPage());
		rlRun.setRunTag("foo");
		rlRun.expand(true);
		rlRun.collapse(false, true);
		assertTrue(rlRun.hasAttribute(AttributeName.RUNTAG));
		assertFalse(rlSep.hasAttribute(AttributeName.RUNTAG));
		assertFalse(rlSet.hasAttribute(AttributeName.RUNTAG));
		rlRun.expand(true);
		rlRun.collapse(true, true);
		assertFalse(rlRun.hasAttribute(AttributeName.RUNTAG));
		assertTrue(rlSep.hasAttribute(AttributeName.RUNTAG));
		assertFalse(rlSet.hasAttribute(AttributeName.RUNTAG));
	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	public void testFixVersion()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		final JDFExposedMedia xm2 = (JDFExposedMedia) xm.getPartition(new JDFAttributeMap(EnumPartIDKey.SignatureName, "Sig1"), null);
		assertTrue(xm.isValid(EnumValidationLevel.Complete));
		xm2.setAttribute("Class", EnumResourceClass.Handling.getName());
		assertFalse(xm.isValid(EnumValidationLevel.Complete));
		xm.fixVersion(null);
		assertNull(xm2.getAttribute_KElement("Class", null, null));
		assertTrue(xm.isValid(EnumValidationLevel.Complete));
	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	public void testExpand()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		xm.setBrand("rootBrand");
		xm.setGeneralID("testID", "rootValue");
		xm.expand(false);
		xm.collapse(true, true);
		xm.expand(true);
		xm.collapse(false, true);

		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "S1");
		mPart.put("Side", "Front");
		final JDFExposedMedia xmPart = (JDFExposedMedia) xm.getPartition(mPart, null);
		mPart.put("SheetName", "S2");
		final JDFExposedMedia xmPart2 = (JDFExposedMedia) xm.getPartition(mPart, null);

		xmPart.setBrand("PartBrand");
		xmPart.setGeneralID("testID", "partValue");

		xm.expand(false);
		assertEquals("expanded sub", xmPart.getBrand(), "PartBrand");
		assertEquals("expanded sub", xmPart.getGeneralID("testID", 0), "partValue");
		assertEquals("expanded sub2", xmPart2.getBrand(), "rootBrand");
		assertEquals("expanded sub2", xmPart2.getGeneralID("testID", 0), "rootValue");
		assertTrue("hasBrand", xmPart2.hasAttribute_KElement("Brand", null, false));
		assertTrue("hasID", xmPart2.getElement_KElement("GeneralID", null, 0) != null);
		assertFalse("has part Key", xmPart.hasAttribute_KElement(AttributeName.SHEETNAME, null, false));
		assertFalse("has part Key", xmPart2.hasAttribute_KElement(AttributeName.SHEETNAME, null, false));

		xm.collapse(false, true);
		assertEquals("expanded sub after collapse", xmPart.getBrand(), "PartBrand");
		assertEquals("expanded sub after collapse", xmPart.getGeneralID("testID", 0), "partValue");
		assertEquals("expanded sub2 after collapse", xmPart2.getBrand(), "rootBrand");
		assertEquals("expanded sub2 after collapse", xmPart2.getGeneralID("testID", 0), "rootValue");
		assertFalse("hasBrand", xmPart2.hasAttribute_KElement("Brand", null, false));
		assertTrue("hasID", xmPart2.getElement_KElement("GeneralID", null, 0) == null);
		assertFalse("has part Key", xmPart.hasAttribute_KElement(AttributeName.SHEETNAME, null, false));
		assertFalse("has part Key", xmPart2.hasAttribute_KElement(AttributeName.SHEETNAME, null, false));

		JDFExposedMedia xmPart3 = (JDFExposedMedia) xmPart2.getParentNode_KElement().getParentNode_KElement();
		mPart.put("SignatureName", "Sig2");
		final JDFExposedMedia xmPart4 = (JDFExposedMedia) xm.getPartition(mPart, null);

		xmPart3.expand(true);
		assertTrue("hasBrand", xmPart2.hasAttribute_KElement("Brand", null, false));
		assertFalse("hasBrand", xmPart4.hasAttribute_KElement("Brand", null, false));
		assertTrue("hasID", xmPart2.getElement_KElement("GeneralID", null, 0) != null);
		assertFalse("hasID", xmPart4.getElement_KElement("GeneralID", null, 0) != null);
		assertFalse("has part Key", xmPart.hasAttribute_KElement(AttributeName.SHEETNAME, null, false));
		assertFalse("has part Key", xmPart2.hasAttribute_KElement(AttributeName.SHEETNAME, null, false));

		xmPart3.collapse(false, true);
		assertFalse("hasBrand", xmPart2.hasAttribute_KElement("Brand", null, false));
		assertFalse("hasBrand", xmPart3.hasAttribute_KElement("Brand", null, false));
		assertFalse("hasID", xmPart2.getElement_KElement("GeneralID", null, 0) != null);

		xmPart3 = (JDFExposedMedia) xmPart4.getParentNode_KElement().getParentNode_KElement();
		xmPart3.expand(true);
		assertTrue("hasBrand", xmPart4.hasAttribute_KElement("Brand", null, false));
		assertFalse("hasBrand", xmPart2.hasAttribute_KElement("Brand", null, false));
		assertTrue("hasID", xmPart4.getElement_KElement("GeneralID", null, 0) != null);
		assertFalse("hasID", xmPart2.getElement_KElement("GeneralID", null, 0) != null);
		xmPart3.collapse(false, true);
		assertFalse("hasBrand", xmPart4.hasAttribute_KElement("Brand", null, false));
		assertFalse("hasBrand", xmPart3.hasAttribute_KElement("Brand", null, false));
		assertFalse("hasID", xmPart4.getElement_KElement("GeneralID", null, 0) != null);
		assertTrue("hasID", xmPart.getElement("GeneralID", null, 0) != null);

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		dpp.expand(true);
		dpp.expand(false);
		assertTrue(dpp.hasAttribute("ID"));

	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	public void testExpandLeaf()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		xm.setBrand("rootBrand");
		xm.setGeneralID("testID", "rootValue");

		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "S1");
		mPart.put("Side", "Front");
		final JDFExposedMedia xmPart = (JDFExposedMedia) xm.getPartition(mPart, null);

		assertNotSame(xmPart.getBrand(), xmPart.getAttribute_KElement(AttributeName.BRAND));
		assertNull(xmPart.getElement_KElement(ElementName.GENERALID, null, 0));
		xmPart.expandLeaf();

		assertEquals(xmPart.getBrand(), xmPart.getAttribute_KElement(AttributeName.BRAND));
		assertNotNull(xmPart.getElement_KElement(ElementName.GENERALID, null, 0));

	}

	/**
	 *
	 */
	@Test
	public void testGeneralID()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		final JDFExposedMedia xm2 = (JDFExposedMedia) xm.getPartition(new JDFAttributeMap("SignatureName", "Sig1"), EnumPartUsage.Explicit);
		xm.setGeneralID("foo", "bar");
		assertEquals("", xm.getGeneralID("foo", 0), "bar");
		assertEquals("", xm2.getGeneralID("foo", 0), "bar");
		assertEquals("", xm.numChildElements(ElementName.GENERALID, null), 1);
		xm.setGeneralID("foo", "bar2");
		assertEquals("", xm.getGeneralID("foo", 0), "bar2");
		assertEquals("", xm.numChildElements(ElementName.GENERALID, null), 1);
		assertEquals("", xm2.numChildElements(ElementName.GENERALID, null), 1);
		xm2.setGeneralID("foo", "bar4");
		xm.setGeneralID("foo2", "bar3");
		assertEquals("", xm.getGeneralID("foo", 0), "bar2");
		assertEquals("", xm2.getGeneralID("foo", 0), "bar4");
		assertEquals("", xm.getGeneralID("foo2", 0), "bar3");
		assertEquals("", xm.numChildElements(ElementName.GENERALID, null), 2);
		xm.removeGeneralID("foo");
		assertNull("", xm.getGeneralID("foo", 0));
		assertEquals("", xm.getGeneralID("foo2", 0), "bar3");
		assertEquals("", xm.numChildElements(ElementName.GENERALID, null), 1);
		xm.setGeneralID("foo3", "bar33");
		final JDFGeneralID gi = xm.getGeneralID(0);
		assertEquals("", gi.getIDUsage(), "foo2");
		xm.removeGeneralID(null);
		assertEquals("", xm.numChildElements(ElementName.GENERALID, null), 0);
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	public void testGeneralIDEmptyNamespace()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);

		final JDFGeneralID generalID = (JDFGeneralID) xm.appendElement(ElementName.GENERALID);
		assertEquals(JDFConstants.EMPTYSTRING, generalID.getIDUsage());
		assertEquals(JDFConstants.EMPTYSTRING, generalID.getIDValue());
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	public void testInstantiations()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		final JDFResourcePool resPool = root.getCreateResourcePool();
		KElement kElem = resPool.appendElement(ElementName.STRIPPINGPARAMS);
		assertTrue(kElem instanceof JDFStrippingParams);

		kElem = resPool.appendElement(ElementName.STRIPCELLPARAMS);
		assertTrue(kElem instanceof JDFStripCellParams);

		kElem = resPool.appendElement(ElementName.BINDERYSIGNATURE);
		assertTrue(kElem instanceof JDFBinderySignature);

	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	public void testGetElement()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		JDFMedia med = xm.getMedia();

		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "S1");
		mPart.put("Side", "Front");
		final JDFExposedMedia xmPart = (JDFExposedMedia) xm.getPartition(mPart, null);
		assertEquals(xm.getMedia(), med);
		assertEquals(xmPart.getMedia(), med);
		final JDFExposedMedia xmPartSig = (JDFExposedMedia) xm.getPartition(new JDFAttributeMap("SignatureName", "Sig1"), null);
		final JDFMedia med2 = xmPartSig.appendMedia();
		assertEquals(xm.getMedia(), med);
		assertEquals(xmPart.getMedia(), med2);
		assertEquals(xmPartSig.getMedia(), med2);

		med = (JDFMedia) med.makeRootResource(null, null, true);
		assertEquals(xm.getMedia(), med);
		assertEquals(xmPart.getMedia(), med2);
		assertEquals(xmPartSig.getMedia(), med2);

	}

	/**
	 * tests getxpathattribute for partitions
	 */
	@Test
	public void testGetXPathAttribute()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		assertEquals(n.getXPathAttribute("ResourcePool/ExposedMedia/ExposedMedia/@SignatureName", null), "Sig1");
		assertNull(n.getXPathAttribute("ResourcePool/ExposedMedia/ExposedMedia/ExposedMedia/@SignatureName", null));
	}

	/**
	 * tests getxpathattribute for partitions
	 */
	@Test
	public void testRemoveChildren()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource("ExposedMedia", EnumUsage.Input);
		xm.appendMedia();
		final JDFMedia m2 = (JDFMedia) n.addResource("Media", EnumUsage.Input);
		xm.refElement(m2);
		final JDFExposedMedia xmp = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.SheetName, "s1");
		xmp.appendMedia();
		xmp.refElement(m2);
		xmp.removeChildren("Media", null, null);
		assertNull(xmp.getElement_JDFElement("Media", null, 0));
		assertNotNull(xm.getMedia());
	}

	// //////////////////////////////////////////////////////////////////////////
	// /
	/**
	 * tests getxpathattribute for partitions
	 */
	@Test
	public void testGetXPathElement()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		assertNotNull(n.getXPathElement("//ResourcePool/ExposedMedia/ExposedMedia[@SignatureName=\"Sig1\"]"));
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	public void testGetResStatus()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "S1");
		mPart.put("Side", "Front");
		final JDFExposedMedia xmPart = (JDFExposedMedia) xm.getPartition(mPart, null);
		xm.setResStatus(EnumResStatus.Unavailable, false);
		final JDFMedia med = xm.getMedia();
		med.setResStatus(EnumResStatus.Unavailable, false);
		med.makeRootResource(null, null, true);

		assertEquals(xm.getResStatus(false), EnumResStatus.Unavailable);
		assertEquals(xmPart.getResStatus(false), EnumResStatus.Unavailable);
		assertEquals(xm.getResStatus(true), EnumResStatus.Unavailable);
		assertEquals(xmPart.getResStatus(true), EnumResStatus.Unavailable);

		xmPart.setResStatus(EnumResStatus.Available, false);
		assertEquals(xm.getResStatus(false), EnumResStatus.Unavailable);
		assertEquals(xmPart.getResStatus(false), EnumResStatus.Available);
		assertEquals(xm.getResStatus(true), EnumResStatus.Unavailable);
		assertEquals(xmPart.getResStatus(true), EnumResStatus.Unavailable);

		med.setResStatus(EnumResStatus.Available, false);
		assertEquals(xm.getResStatus(false), EnumResStatus.Unavailable);
		assertEquals(xmPart.getResStatus(false), EnumResStatus.Available);
		assertEquals(xm.getResStatus(true), EnumResStatus.Unavailable);
		assertEquals(xmPart.getResStatus(true), EnumResStatus.Available);

		xmPart.removeAttribute(AttributeName.STATUS);
		assertEquals(xm.getResStatus(false), EnumResStatus.Unavailable);
		assertEquals(xmPart.getResStatus(false), EnumResStatus.Unavailable);
		assertEquals(xm.getResStatus(true), EnumResStatus.Unavailable);
		assertEquals(xmPart.getResStatus(true), EnumResStatus.Unavailable);

		xm.setResStatus(EnumResStatus.Available, false);
		assertEquals(xm.getResStatus(false), EnumResStatus.Available);
		assertEquals(xmPart.getResStatus(false), EnumResStatus.Available);
		assertEquals(xm.getResStatus(true), EnumResStatus.Available);
		assertEquals(xmPart.getResStatus(true), EnumResStatus.Available);

		med.setResStatus(EnumResStatus.Unavailable, false);
		assertEquals(xm.getResStatus(false), EnumResStatus.Available);
		assertEquals(xmPart.getResStatus(false), EnumResStatus.Available);
		assertEquals(xm.getResStatus(true), EnumResStatus.Unavailable);
		assertEquals(xmPart.getResStatus(true), EnumResStatus.Unavailable);

	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	public void testGetCreatePartition2()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		final JDFResource media = n.addResource("Media", null, EnumUsage.Input, null, null, null, null);

		media.addPartition(EnumPartIDKey.SignatureName, "sig1");
		media.addPartition(EnumPartIDKey.SignatureName, "sig2");
		try
		{
			media.getCreatePartition(EnumPartIDKey.SheetName, "sh11", new VString("SignatureName SheetName", " "));
			fail("no parallel");
		}
		catch (final JDFException x)
		{
			// nop
		}
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	public void testGetCreatePartitionCreate()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		final JDFResource media = n.addResource("Media", null, EnumUsage.Input, null, null, null, null);

		final VString v = new VString("SignatureName SheetName Side", null);
		final JDFAttributeMap m = new JDFAttributeMap(EnumPartIDKey.SignatureName, "sig1");
		m.put(EnumPartIDKey.SheetName, "sh1");
		m.put(EnumPartIDKey.Side, "Front");

		final JDFResource part = media.getCreatePartition(m, v);
		assertNotNull(part);
		assertEquals(m, part.getPartMap());
	}

	/**
	 *
	 */
	@Test
	public void testGetCreatePartition3()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		final JDFResource media = n.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);

		final JDFMedia mp1 = (JDFMedia) media.addPartition(EnumPartIDKey.SignatureName, "sig1");
		mp1.addPartition(EnumPartIDKey.SheetName, "sh1");
		final JDFMedia mp2 = (JDFMedia) media.addPartition(EnumPartIDKey.SignatureName, "sig2");
		mp2.addPartition(EnumPartIDKey.SheetName, "sh1");
		assertEquals(media.getPartitionVector(new JDFAttributeMap(AttributeName.SHEETNAME, "sh1"), null).size(), 2);

		try
		{
			media.getCreatePartition(EnumPartIDKey.SheetName, "sh11", new VString("SignatureName SheetName", " "));
			fail("no parallel");
		}
		catch (final JDFException x)
		{
			// nop
		}
	}

	/**
	 *
	 */
	@Test
	public void testGetCreatePartitionSelf()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource media = n.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);

		final JDFMedia mp1 = (JDFMedia) media.addPartition(EnumPartIDKey.SignatureName, "sig1");
		final JDFMedia m2 = (JDFMedia) mp1.addPartition(EnumPartIDKey.SheetName, "sh1");

		final JDFAttributeMap map = new JDFAttributeMap();
		map.put(EnumPartIDKey.SignatureName, "sig1");
		map.put(EnumPartIDKey.SheetName, "sh1");
		assertEquals(m2, m2.getCreatePartition(map, new VString("SignatureName SheetName", null)));
		assertEquals(m2, m2.getCreatePartition(map, null));
		assertEquals(m2, mp1.getCreatePartition(map, new VString("SignatureName SheetName", null)));
		assertEquals(m2, mp1.getCreatePartition(map, null));
	}

	/**
	 *
	 */
	@Test
	public void testAddpartitionEnum()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		JDFResource media = n.addResource("Media", null, EnumUsage.Input, null, null, null, null);
		media = media.addPartition(EnumPartIDKey.Side, EnumSide.Front);
		assertEquals(media.getSide(), EnumSide.Front);
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	public void testAddpartition()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		final JDFResource media = n.addResource("Media", null, EnumUsage.Input, null, null, null, null);

		final JDFResource sig = media.addPartition(EnumPartIDKey.SignatureName, "sig1");
		media.addPartition(EnumPartIDKey.SignatureName, "sig2");
		try
		{
			media.addPartition(EnumPartIDKey.SignatureName, "sig1");
			fail("no identical key");
		}
		catch (final JDFException x)
		{
			// nop
		}

		try
		{
			media.addPartition(EnumPartIDKey.SheetName, "sh11");
			fail("no parallel");
		}
		catch (final JDFException x)
		{
			// nop
		}

		try
		{
			sig.addPartition(EnumPartIDKey.SignatureName, "sig2");
			fail("no existing");
		}
		catch (final JDFException x)
		{
			// nop
		}

		final JDFResource sheet = sig.addPartition(EnumPartIDKey.SheetName, "sh1");
		try
		{
			sig.addPartition(EnumPartIDKey.Side, "Front");
			fail("no existing other parallel");
		}
		catch (final JDFException x)
		{
			// nop
		}
		try
		{
			sheet.addPartition(EnumPartIDKey.SignatureName, "Sig3");
			fail("no existing lower");
		}
		catch (final JDFException x)
		{
			// nop
		}
		sheet.addPartition(EnumPartIDKey.Side, "Front");
		sheet.addPartition(EnumPartIDKey.Side, "Back");
		try
		{
			sheet.addPartition(EnumPartIDKey.Side, "Front");
			fail("no existing dulicate");
		}
		catch (final JDFException x)
		{
			// nop
		}
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	public void testMultiplePartIDKeys()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "S1");
		mPart.put("Side", "Front");
		final JDFExposedMedia xmPart = (JDFExposedMedia) xm.getPartition(mPart, null);
		assertEquals(xmPart.getInvalidAttributes(EnumValidationLevel.Incomplete, true, 999).size(), 0);
		final JDFResource r = xmPart.addPartition(EnumPartIDKey.Condition, "Good");
		assertFalse(r.getInvalidAttributes(EnumValidationLevel.Incomplete, true, 999).contains(EnumPartIDKey.Condition.getName()));
		xmPart.addPartition(EnumPartIDKey.Condition, "Bad").setAttribute(EnumPartIDKey.Condition.getName(), "Good");
		assertTrue("Duplicate partition found", r.getInvalidAttributes(EnumValidationLevel.Incomplete, true, 999).contains(EnumPartIDKey.Condition.getName()));

	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	public void testConsistentPartIDKeys()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "S1");
		mPart.put("Side", "Front");
		final JDFExposedMedia xmPart = (JDFExposedMedia) xm.getPartition(mPart, null);
		assertTrue(xmPart.consistentPartIDKeys(EnumPartIDKey.BinderySignatureName));
		assertTrue(xmPart.consistentPartIDKeys(EnumPartIDKey.Side));
		xmPart.removeAttribute("Side");
		assertFalse(xmPart.consistentPartIDKeys(EnumPartIDKey.Side));
		assertTrue(xmPart.getInvalidAttributes(EnumValidationLevel.Complete, false, 999).contains("Side"));
		xm.setAttribute("Side", "Front");
		assertFalse(xmPart.consistentPartIDKeys(EnumPartIDKey.Side));
		xmPart.setAttribute("Side", "Front");
		assertFalse(xmPart.consistentPartIDKeys(EnumPartIDKey.Side));
		xm.removeAttribute("Side");
		assertTrue(xmPart.consistentPartIDKeys(EnumPartIDKey.Side));
		assertTrue(xmPart.consistentPartIDKeys(EnumPartIDKey.SheetName));
		xmPart.getParentNode_KElement().removeAttribute("SheetName");
		assertFalse(xmPart.consistentPartIDKeys(EnumPartIDKey.SheetName));
		xmPart.getParentNode_KElement().setAttribute("SignatureName", "foo");
		assertFalse(xmPart.consistentPartIDKeys(EnumPartIDKey.SheetName));
		assertTrue(xmPart.getInvalidAttributes(EnumValidationLevel.Complete, false, 999).contains("SignatureName"));
	}

	// //////////////////////////////////////////////////////////////////////////
	// /
	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 * jdf 1.4 preview anywhere example
	 */
	@Test
	public void testPreview14()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setJobPartID("P1");
		n.setJobID("J1");
		n.setType("ConventionalPrinting", true);

		final JDFComponent comp = (JDFComponent) n.appendMatchingResource("Component", JDFNode.EnumProcessUsage.AnyOutput, null);
		final JDFPreview pvc = (JDFPreview) comp.appendElement(ElementName.PREVIEW);
		pvc.setURL("http://somehost/pvComponent.png");
		pvc.setPreviewType(EnumPreviewType.ThumbNail);
		final JDFExposedMedia xm = (JDFExposedMedia) n.appendMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.Plate, null);
		xm.appendMedia();
		final JDFPreview pv = (JDFPreview) xm.appendElement(ElementName.PREVIEW);
		pv.setURL("http://somehost/pvExposedMedia.png");
		pv.setPreviewType(EnumPreviewType.ThumbNail);
		doc.write2File(sm_dirTestDataTemp + "pv14.jdf", 2, false);
	}

	/**
	 *
	 *
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	public void tearDown() throws Exception
	{
		super.tearDown();
		JDFResource.setAutoAgent(bAgent);
		JDFResource.setAutoSubElementClass(bSubElem);
		JDFResource.setUnpartitiondImplicit(false);
	}

	/**
	 *
	 *
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		bAgent = JDFResource.getAutoAgent();
		bSubElem = JDFResource.getAutoSubElementClass();
	}
}
