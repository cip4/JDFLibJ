/*
 * The CIP4 Software License, Version 1.0
 *
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
/**
 * JDFResourceTest.java
 *
 * @author Dietrich Mucha
 *
 *         Copyright (C) 2002 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.List;
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
import org.cip4.jdflib.core.JDFElement.eUnit;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.LinkRefFinder;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
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
import org.cip4.jdflib.util.CPUTimer;
import org.cip4.jdflib.util.ListMap;
import org.cip4.jdflib.util.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         20.01.2009
 */
class JDFResourceTest extends JDFTestCaseBase
{

	private boolean bAgent;
	private boolean bSubElem;

	/**
	 *
	 */
	@Test
	void testGetCreator()
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
	void testGetUnit()
	{
		final JDFComponent c = (JDFComponent) JDFElement.createRoot(ElementName.COMPONENT);
		assertNull(c.getUnitEnum());
		c.setUnit(eUnit.m2);
		assertEquals(eUnit.m2, c.getUnitEnum());
		c.setUnit((eUnit) null);
		assertNull(c.getUnitEnum());
	}

	/**
	 *
	 */
	@Test
	void testAddPartIDKey()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		rl.addPartIDKey(EnumPartIDKey.Run);
		rl.addPartIDKey(EnumPartIDKey.Run);
		assertNull(rl.getPartIDKeyList().get(1));
	}

	/**
	 *
	 */
	@Test
	void testAddPartsCompatible()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFResource rl = n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		assertTrue(rl.isPartsCompatible(null));
		assertTrue(rl.isPartsCompatible(null));
		assertTrue(rl.isPartsCompatible(new VString()));
		assertTrue(rl.isPartsCompatible(new VString("Run")));
		rl.addPartIDKey(EnumPartIDKey.Run);
		assertTrue(rl.isPartsCompatible(new VString("Run")));
		assertFalse(rl.isPartsCompatible(new VString("Separation")));
		assertTrue(rl.isPartsCompatible(new VString("Run Separatiom")));
		rl.addPartIDKey(EnumPartIDKey.Separation);
		assertTrue(rl.isPartsCompatible(new VString("Run Separation")));
		assertFalse(rl.isPartsCompatible(new VString("Separation Run")));
	}

	/**
	 *
	 */
	@Test
	void testGetCreatorPartition()
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
	void testUnPartition()
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
	void testUnPartitionNot()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		((JDFExposedMedia) xm.getLeaves(false).get(0).getParentNode_KElement()).setBrand("foo"); // one is different...
		xm.unpartition(false);
		assertEquals(xm.getPartIDKeys().size(), 2);
		xm.unpartition(true);
		assertEquals(xm.getPartIDKeys().size(), 0);
	}

	/**
	 *
	 */
	@Test
	void testGetAttributeVector()
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
		assertTrue(partVector.contains("ID"), "contains inherited attributes");
		assertTrue(partVector.contains("SignatureName"), "contains inherited attributes");
		assertTrue(partVector.contains("SheetName"), "contains inherited attributes");
		assertTrue(partVector.contains("Side"), "contains inherited attributes");
		assertTrue(partVector.contains("AgentName"), "contains inherited attributes");
		assertTrue(partVector.contains("foo:bar"), "contains inherited attributes");
	}

	/**
	 *
	 */
	@Test
	void testGetNonEmpty()
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
	void testGetNonEmpty_KElement()
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
	void testHasNonEmpty()
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
	void testHasNonEmpty_KElement()
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
	void testGetAttributeMap()
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
	void testGetIgnoreCase()
	{
		final JDFNode root = new JDFDoc(ElementName.JDF).getJDFRoot();
		root.setType(JDFNode.EnumType.ConventionalPrinting.getName(), true);
		final JDFExposedMedia xm = (JDFExposedMedia) root.appendMatchingResource(ElementName.EXPOSEDMEDIA, JDFNode.EnumProcessUsage.AnyInput, null);
		xm.setProofType(JDFExposedMedia.EnumProofType.Page);

		final JDFExposedMedia xm2 = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.SheetName, "S1");
		assertEquals("Page", xm.getIgnoreCase("PROOFTYPE"));
		assertEquals("Page", xm2.getIgnoreCase("PROOFTYPE"));
		assertNull(xm2.getIgnoreCase_KElement("PROOFTYPE"));
	}

	/**
	 * test the the generalized partition matching
	 */
	@Test
	void testGetAttributeMap_KElement()
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
		assertNull(am.get("foo:bar"), "No inheritence");
	}

	/**
	 * tests the hard coded getters and setters
	 */
	@Test
	void testGetSet()
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
	void testOverlapPartMap()
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
	 * test the the generalized partition matching
	 */
	@Test
	void testPartIDKeys()
	{
		int n = 0;
		for (final EnumPartIDKey k : EnumPartIDKey.getEnumList())
		{
			assertEquals(k, EnumPartIDKey.getEnum(k.getName()));
			if (k.isXJDF())
				n++;
		}
		assertEquals(5, n);
	}

	/**
	 * test initialization and setAutoAgent
	 */
	@Test
	void testInit()
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
	void testInitAgentName()
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
	void testMakeRootResource()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFResourcePool p = n.appendResourcePool();
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		final JDFMedia m = xm.appendMedia();
		final JDFMedia m2 = (JDFMedia) m.makeRootResource();
		assertEquals(m, m2);
		assertEquals(m2.getParentNode_KElement(), p);
	}

	/**
	 * test the resource root stuff
	 */
	@Test
	void testGetResourcePoolNS()
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
	void testGetResourceRootNI13()
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
	void testGetResourceRoot()
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
	void testGetResourceRootJMF()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, JDFMessage.EnumType.Resource);
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
	void testGetResourceRoot_Old()
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
		assertEquals(resource, resRoot, "getResourceRoot now also returns the root of incorrectly placed resources");

		resRoot = elem.getResourceRoot();
		assertTrue(resRoot == elem);

		final JDFNode rootNode = (JDFNode) root;
		final JDFExposedMedia xm = (JDFExposedMedia) rootNode.addResource("ExposedMedia", null, EnumUsage.Input, null, null, null, null);
		final JDFMedia m = xm.appendMedia();
		assertEquals(xm.getResourceRoot(), xm, "xm");
		assertEquals(m.getResourceRoot(), xm, "m");

		final JDFColorantControl cc = (JDFColorantControl) rootNode.addResource("ColorantControl", null, EnumUsage.Input, null, null, null, null);
		final JDFSeparationSpec ss = cc.appendColorantParams().appendSeparationSpec();
		assertFalse(ss.hasAttribute(AttributeName.CLASS));

	}

	/**
	 *
	 */
	@Test
	void testIsPhysical()
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
	void testIsParameter()
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
	void testIsEqual()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, null);
		final JDFExposedMedia xm0 = (JDFExposedMedia) xm.getCreatePartition(new JDFAttributeMap("Side", "Front"), null);
		final JDFExposedMedia xm1 = (JDFExposedMedia) xm.getCreatePartition(new JDFAttributeMap("Side", "Back"), null);

		final JDFExposedMedia xmm = (JDFExposedMedia) xm.cloneNewDoc();
		assertTrue(xmm.isEqual(xm));
		xm0.setDescriptiveName("s1");

		assertFalse(xmm.isEqual(xm));
	}

	/**
	*
	*/
	@Test
	void testInvalidAttributes()
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
	void testIsResourceElement()
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
	void testAppendRaw()
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
	void testAppendRawAtt()
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
	void testgetPartMap()
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
	void testgetPartUsage()
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
	void testgetPartValues()
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
	void testgetPartMapVector()
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
	void testgetPartMapVectorSequence()
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
	void testGetChildrenByTagName()
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
		assertEquals(ve.size(), 1, "we skipped the ref pointing to Nirvana");
		for (final KElement e : ve)
		{
			assertNotNull(e);
		}

	}

	/**
	 *
	 */
	@Test
	void testGetChildElement()
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
	void testGetChildElementVector()
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
	void testGetColorPool()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();

		final JDFResourcePool resPool = root.appendResourcePool();
		final JDFColorantControl colControl = (JDFColorantControl) resPool.appendElement(ElementName.COLORANTCONTROL, null);
		colControl.setProcessColorModel("DeviceCMY");
		colControl.appendColorantParams().appendSeparationSpec().setName("Spot");

		final JDFColorSpaceConversionParams cpp = (JDFColorSpaceConversionParams) root.addResource(ElementName.COLORSPACECONVERSIONPARAMS, null, EnumUsage.Input, null, null, null,
				null);
		final JDFColorSpaceConversionOp cso = cpp.appendColorSpaceConversionOp();
		final Vector<EnumSourceObjects> sourceObjects = new Vector<>();
		sourceObjects.add(EnumSourceObjects.ImagePhotographic);
		sourceObjects.add(EnumSourceObjects.LineArt);
		cso.setSourceObjects(sourceObjects);

		// getseparations
		final VString vSeps = colControl.getSeparations();
		assertTrue(vSeps.size() == 4, "seps 4");
		assertTrue(vSeps.contains("Cyan"), "seps Cyan");
		assertTrue(vSeps.contains("Spot"), "seps Spot");
		assertTrue(!vSeps.contains("Black"), "seps no black");

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
	void testPartitionedAttribute()
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
		assertTrue(xm2.getMedia().getDimension().getX() == 200., "Media inline Dimension");
		assertTrue(xm.getResolution().getX() == 300., "ExposedMedia direct Resolution");
		assertTrue(xm2.getResolution().getX() == 300., "ExposedMedia inherited Resolution");
		m = (JDFMedia) m.makeRootResource(null, null, true);
		assertTrue(xm2.getMedia().getDimension().getX() == 200., "Media referenced Dimension");
		final JDFRefElement re = (JDFRefElement) xm2.getElement("MediaRef");
		assertTrue(re != null, "refElement found");

		if (re != null)
		{
			final JDFMedia m2 = (JDFMedia) re.getTarget();
			assertEquals(m, m2, "ref target");
		}
		assertTrue(xm2.hasChildElement("Media", null), "has Media");
		assertTrue(xm2.getPolarity(), "polarity true");
		xm2.setPolarity(false);
		assertFalse(xm2.getPolarity(), "polarity false");

		assertEquals(xm2.getProofType(), JDFExposedMedia.EnumProofType.Page, "prooftype");
	}

	/**
	 * tests the correct inheritence of refelements, elements and attributes
	 */
	@Test
	void testpartitionedElement()
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
	void testUpdateValuesFromLeaves()
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
	void testUpdateAmounts()
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
			assertEquals(media.getAmount(), 100 - 21, 0.1, "amount=100, - the 21 actual");
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
			assertEquals(c1.getAmountProduced(), 42., 0.1, "Anything but Condition=Good is ignored");
		}
	}

	/**
	 * tests updateAmounts()
	 */
	@Test
	void testUpdateAmountsVirtual()
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
	void testContainsData()
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
	void testCreatePartitions()
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
		assertEquals(parts2.size(), 2, "add partVersion at end anyhow");

	}

	/**
	 *
	 */
	// ///////////////////////////////////////////////////////////
	@Test
	void testGetCreatePartition()
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
		assertTrue(part1.equals(part2), "part1 and part2 must be equal");

		map.put("Side", "Front");
		final JDFResource part11 = part1.getPartition(map, EnumPartUsage.Explicit);
		final JDFResource part12 = part1.getCreatePartition(map, null);

		assertTrue(part11.equals(part12), "part11 and part12 must be equal");

		doc.write2File(sm_dirTestDataTemp + "testGetCreatePartition.jdf", 0, true);
	}

	// //////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testHasAttribute()
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
	void testIdenticalStatus()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType("Product", true);

		final JDFComponent c = (JDFComponent) n.appendMatchingResource("Component", JDFNode.EnumProcessUsage.AnyOutput, null);
		final JDFResourceLink l = n.getMatchingLink("Component", JDFNode.EnumProcessUsage.AnyOutput, 0);
		assertTrue(l != null, "link exists");
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
	void testIdentical()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType("Product", true);

		final JDFComponent c = (JDFComponent) n.appendMatchingResource("Component", JDFNode.EnumProcessUsage.AnyOutput, null);
		final JDFResourceLink l = n.getMatchingLink("Component", JDFNode.EnumProcessUsage.AnyOutput, 0);
		assertTrue(l != null, "link exists");
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
		assertEquals(c2.getSheetName(), "S2", "part ok");
		final JDFComponent c3 = (JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName, "S3");
		c3.setAmount(42);
		final JDFAttributeMap map = c3.getPartMap();
		c3.setIdentical(c1);
		assertEquals(map, c3.getPartMap());
		assertNotNull(c3.getIdenticalMap());
		assertEquals(c3.getIdenticalMap(), c1.getPartMap());

		final VElement v = c.getLeaves(false);
		assertEquals(v.size(), 5, "physical leaves are counted");
		final VJDFAttributeMap vCMap = c.getPartMapVector(false);
		assertEquals(vCMap.size(), 5, "physical leaves are counted");
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
		 * final JDFComponent c3y = (JDFComponent) c.getPartition(mPart3, null); assertEquals("identical", c1y, c3y); c1y = (JDFComponent) c.getPartition(mPart1, null);
		 * assertEquals("identical 2", c1y, c3y);
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
		 * try { c3y.setIdentical(c3y); fail("myself set self"); } catch (final JDFException x) { // } try { c3y.setIdentical(c3); fail("myself set identical"); } catch (final
		 * JDFException x) { // } try { c3y.setIdentical(c1); fail("myself set parent"); } catch (final JDFException x) { // }
		 */
		doc.write2File(sm_dirTestDataTemp + "identical.jdf", 2, false);
	}

	/**
	 *
	 */
	@Test
	void testIdenticalValid()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setType("Product", true);

		final JDFComponent c = (JDFComponent) n.appendMatchingResource("Component", JDFNode.EnumProcessUsage.AnyOutput, null);
		final JDFResourceLink l = n.getMatchingLink("Component", JDFNode.EnumProcessUsage.AnyOutput, 0);
		assertTrue(l != null, "link exists");
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
		assertEquals(c2.getSheetName(), "S2", "part ok");
		final JDFComponent c3 = (JDFComponent) c.addPartition(JDFResource.EnumPartIDKey.SheetName, "S3");
		c3.setIdentical(c1);
		assertTrue(c3.isValid(EnumValidationLevel.Incomplete));
	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testGetParentPartition()
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
	void testGetPartition()
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
	void testGetPartitionSelf()
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
	 *
	 */
	@Test
	void testGetPartitionGap()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource media = n.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);

		final JDFMedia mp1 = (JDFMedia) media.addPartition(EnumPartIDKey.SignatureName, "sig1");
		final JDFMedia m1 = (JDFMedia) mp1.addPartition(EnumPartIDKey.SheetName, "sh1");

		final JDFMedia mp2 = (JDFMedia) media.addPartition(EnumPartIDKey.SignatureName, "sig2");
		final JDFMedia m2 = (JDFMedia) mp2.addPartition(EnumPartIDKey.SheetName, "sh2");

		final JDFAttributeMap map = new JDFAttributeMap();
		map.put(EnumPartIDKey.SheetName, "sh1");
		assertEquals(m1, media.getPartition(map, null));
		assertEquals(m1, media.getPartition(map, EnumPartUsage.Explicit));
		assertEquals(m1, media.getPartition(map, EnumPartUsage.Implicit));
		assertEquals(m1, media.getPartition(map, EnumPartUsage.Sparse));
	}

	/**
	*
	*/
	@Test
	void testGetPartitionGapNot()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource media = n.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);

		final JDFMedia mp1 = (JDFMedia) media.addPartition(EnumPartIDKey.SignatureName, "sig1");
		final JDFMedia m1 = (JDFMedia) mp1.addPartition(EnumPartIDKey.SheetName, "sh1");

		final JDFMedia mp2 = (JDFMedia) media.addPartition(EnumPartIDKey.SignatureName, "sig2");
		final JDFMedia m2 = (JDFMedia) mp2.addPartition(EnumPartIDKey.SheetName, "sh2");

		final JDFAttributeMap map = new JDFAttributeMap();
		map.put(EnumPartIDKey.SheetName, "sh3");
		assertEquals(null, media.getPartition(map, null));
		assertEquals(null, media.getPartition(map, EnumPartUsage.Explicit));
		assertEquals(media, media.getPartition(map, EnumPartUsage.Implicit));
		assertEquals(null, media.getPartition(map, EnumPartUsage.Sparse));
	}

	/**
	 * test whether getpartition works for when inconsistently called
	 */
	@Test
	void testGetPartition_PartVersion()
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
		m2.put("PartVersion", "B");
		JDFResource r2 = fp.getPartition(m2, EnumPartUsage.Implicit);
		assertEquals(r.getParentNode_KElement(), r2);
		r2 = fp.getPartition(m2, EnumPartUsage.Sparse);
		assertNull(r2);
		r2 = fp.getPartition(m2, EnumPartUsage.Explicit);
		assertNull(r2);
	}

	/**
	 * test whether getpartition works for when inconsistently called
	 */
	@Test
	void testGetPartition_PartVersionWild()
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
		m2.put("PartVersion", "All");
		JDFResource r2 = fp.getPartition(m2, EnumPartUsage.Implicit);
		assertEquals(r, r2);
		r2 = fp.getPartition(m2, EnumPartUsage.Sparse);
		assertEquals(r, r2);
		r2 = fp.getPartition(m2, EnumPartUsage.Explicit);
		assertEquals(r, r2);
	}

	/**
	 * test whether getpartition works for when map has too many keys
	 */
	@Test
	void testGetPartitionExplicitOver()
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
	void testGetPartitionMap()
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
	void testGetPartitionFromMap()
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
	void testGetPartitionFromMapTime()
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
					assertEquals(fp, fp2, " " + i + " " + j);
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
	void testGetPartsPartition()
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
	void testStrictPartsPartition()
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
	void testGetPartitionVectorIdentical()
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
		assertEquals(v.size(), 8 - 4, "explicit identicals are excluded");
	}

	/**
	 *
	 */
	@Test
	void testGetPartitionVectorIdenticalNoFollowIdentical()
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
		assertEquals(v.size(), 1, "explicit identicals are not excluded");
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
	void testGetPartitionVector()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		xm.setPartUsage(EnumPartUsage.Implicit);
		final JDFAttributeMap m = new JDFAttributeMap("SignatureName", "Sig1");
		m.put("RunIndex", "3");
		VElement v = xm.getPartitionVector(m, null);
		assertEquals(v.size(), 1, "implicit 1");
		final VElement vLeaf = xm.getPartitionLeafVector(m, null);
		assertEquals(vLeaf.size(), 4, "implicit leaf 4");

		final JDFResource r = xm.getPartition(m, null);
		assertEquals(r, xm.getPartition(new JDFAttributeMap("SignatureName", "Sig1"), null), "same as only sig1");
		int i = 0;
		for (i = 0; i < v.size(); i++)
		{
			final JDFExposedMedia xmp = (JDFExposedMedia) v.elementAt(i);
			assertTrue(m.overlapMap(xmp.getPartMap()), "overlap of maps");
		}
		v = xm.getPartitionVector(m, EnumPartUsage.Sparse);
		for (i = 0; i < v.size(); i++)
		{
			final JDFExposedMedia xmp = (JDFExposedMedia) v.elementAt(i);
			assertTrue(m.overlapMap(xmp.getPartMap()), "overlap of maps");
		}
		assertEquals(v.size(), 1, "sparse 1");

		v = xm.getPartitionVector(m, EnumPartUsage.Explicit);
		assertTrue(v.size() == 0, "no explicit");

		m.clear();
		m.put("SignatureName", "Sig1");
		m.put("SheetName", "S3");
		v = xm.getPartitionVector(m, EnumPartUsage.Explicit);
		assertTrue(v.size() == 0, "no explicit");
		v = xm.getPartitionVector(m, EnumPartUsage.Sparse);
		assertTrue(v.size() == 0, "no sparse");
		v = xm.getPartitionVector(m, EnumPartUsage.Implicit);
		assertEquals(v.size(), 1, "1 impl");
		for (i = 0; i < v.size(); i++)
		{
			final JDFExposedMedia xmp = (JDFExposedMedia) v.elementAt(i);
			assertTrue(m.overlapMap(xmp.getPartMap()), "overlap of maps");
		}

		m.clear();
		m.put("SignatureName", "Sig1");
		m.put("Side", "Front");

		v = xm.getPartitionVector(m, EnumPartUsage.Explicit);
		assertTrue(v.size() == 2, "explicit key missing");
		v = xm.getPartitionVector(m, EnumPartUsage.Sparse);
		assertTrue(v.size() == 2, "no sparse");
		v = xm.getPartitionVector(m, EnumPartUsage.Implicit);
		assertEquals(v.size(), 2, "1 impl");
		for (i = 0; i < v.size(); i++)
		{
			final JDFExposedMedia xmp = (JDFExposedMedia) v.elementAt(i);
			assertTrue(m.overlapMap(xmp.getPartMap()), "overlap of maps");
		}
	}

	/**
	 * check that the vector gets missing levels of prtition correctly
	 */
	@Test
	void testGetPartionVectorExplicitSkip()
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
	void testGetPartitionVectorSkip()
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
	void testDeleteUnlinked()
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
	void testGetLinks()
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
	void testGetLinksAndRefsPerformance()
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
			assertEquals(vRef.size(), 2, "A link and a ref");

		}
		final long t2 = System.currentTimeMillis();
		log.info("time 21: " + (t2 - t1));

		final LinkRefFinder lrf = new LinkRefFinder(true, true);
		final ListMap<String, KElement> vm = lrf.getMap(n);
		final long t3 = System.currentTimeMillis();
		log.info("time 32: " + (t3 - t2));
		for (int i = 0; i < 500; i++)
		{
			final JDFMedia m2 = (JDFMedia) vM.get(i);
			final List<KElement> vRef = vm.get(m2.getID());
			assertNotNull(vRef);
			assertEquals(vRef.size(), 2, "A link and a ref");
		}
		final long t4 = System.currentTimeMillis();
		log.info("time 43: " + (t4 - t3));
	}

	/**
	 *
	 */
	@Test
	void testGetLinksAndRefs()
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
		assertEquals(v.size(), 2, "partitioned resource has no links");
		final JDFAttributeMap myMap = new JDFAttributeMap();
		myMap.put("SignatureName", "Sig2");
		rl.setPartMap(myMap);

		xm.getElement_KElement("MediaRef", null, 0).deleteNode();

		v = mPart.getLinksAndRefs(true, true);
		assertNull(v, "partitioned resource has no links");

		myMap.put("SignatureName", "Sig1");
		rl.setPartMap(myMap);
		v = mPart.getLinksAndRefs(true, true);
		assertEquals(v.size(), 1, "partitioned resource has one link");

	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testGetLocalPartitionKey()
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
	void testBuildXPath()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		final JDFExposedMedia xmLeaf = (JDFExposedMedia) xm.getLeaves(false).elementAt(1);
		assertEquals(xmLeaf.buildXPath(null, 2),
				"/JDF/ResourcePool[1]/ExposedMedia[1]/ExposedMedia[@SignatureName=\"Sig1\"]/ExposedMedia[@SheetName=\"S1\"]/ExposedMedia[@Side=\"Back\"]");
	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testBinderySignatureName()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		final JDFBinderySignature bs = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		final JDFBinderySignature bsp = (JDFBinderySignature) bs.addPartition(EnumPartIDKey.BinderySignatureName, "bad name");
		assertFalse(bsp.isValid(EnumValidationLevel.Incomplete), "Bad BS partidkey value - should be nmtoken");
	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testInvalidPartIDKeysLeaves()
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
	void testExplicitPartUsage()
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
	void testInvalidPartUsage()
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

	/**
	*
	*/
	@Test
	void testGetLeaves()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);

		VElement vL = xm.getLeaves(false);
		assertEquals(vL.size(), 8, "size false");
		for (int i = 0; i < vL.size(); i++)
		{
			final JDFExposedMedia xm2 = (JDFExposedMedia) vL.elementAt(i);
			assertEquals(xm2.getPartMap().size(), 3, "map ok");
		}

		vL = xm.getLeaves(true);
		assertEquals(vL.size(), 15, "size false");
	}

	/**
	*
	*/
	@Test
	void testGetLeavesPerformance()
	{

		final JDFNode n = JDFNode.createRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		for (int i = 0; i < 2222; i++)
		{
			xm.addPartition(EnumPartIDKey.SignatureName, "Sig" + i).addPartition(EnumPartIDKey.SheetName, "Sheet" + i).addPartition(EnumPartIDKey.Side, "Front")
					.addPartition(EnumPartIDKey.Separation, "Black").appendElement(ElementName.MEDIA);
		}

		final CPUTimer ct = new CPUTimer(false);
		for (int i = 0; i < 2222; i++)
		{
			ct.start();
			final VElement vL = xm.getLeaves(false);
			ct.stop();
			if (i % 22 == 0)
				log.info(ct.toString());
			assertEquals(vL.size(), 2222, "size false");

		}
	}

	/**
	*
	*/
	@Test
	void testGetMinStatusFromLeaves()
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
	void testSubElement()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		JDFMedia m = xm.getMedia();
		// assertEquals("Media in XM class", m.getResourceClass(),
		// EnumResourceClass.Consumable);
		m.setBrand("fooBrand");
		assertTrue(xm.isValid(EnumValidationLevel.Complete), "xm valid");
		assertTrue(m.isValid(EnumValidationLevel.Complete), "m valid");
		m.deleteNode();
		m = xm.appendMedia();
		m.setBrand("barBrand");
		assertTrue(xm.isValid(EnumValidationLevel.Complete), "xm valid");
		assertTrue(m.isValid(EnumValidationLevel.Complete), "m valid");

	}

	/**
	*
	*/
	@Test
	void testSetAttributes()
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
		assertEquals(r2p.getAgentName(), "a1", "child copied");
		assertEquals(r2p.getAgentVersion(), "v1", "root copied");
		assertEquals(r2p.getSheetName(), "s2", "parent partIDKey not copied");
		assertEquals(r2p.getSide(), EnumSide.Front, "leaf partIDKey copied");
	}

	/**
	*
	*/
	@Test
	void testSetAttributesIntermediate()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		final JDFResource r1 = n.addResource(ElementName.SPINETAPINGPARAMS, EnumUsage.Input);
		final JDFResource r2 = n.addResource(ElementName.SPINETAPINGPARAMS, EnumUsage.Input);
		final JDFResource r1p = r1.addPartition(EnumPartIDKey.SignatureName, "sig1").addPartition(EnumPartIDKey.SheetName, "s1").addPartition(EnumPartIDKey.Side, EnumSide.Front);
		r1p.setAgentName("a1");
		r1.setAgentVersion("v1");
		final JDFResource r2p = r2.addPartition(EnumPartIDKey.SignatureName, "sig1").addPartition(EnumPartIDKey.SheetName, "s1").addPartition(EnumPartIDKey.Side, EnumSide.Front);
		r2p.setAttributes(r1p, new VString("a"));
		assertEquals(r2p.getAgentName(), "a1", "child copied");
		assertEquals(r2p.getAgentVersion(), "v1", "root copied");
		assertNull(r2p.getNonEmpty_KElement(AttributeName.SIGNATURENAME));
		assertNull(r2p.getNonEmpty_KElement(AttributeName.SHEETNAME));
	}

	/**
	*
	*/
	@Test
	void testSetAttributePartition()
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
	void testRunPage()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		JDFResource r1 = n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		assertEquals(r1.getRunPage(), 0, "leaf partIDKey copied");
		assertFalse(r1.hasAttribute(AttributeName.RUNPAGE));
		r1 = r1.addPartition(EnumPartIDKey.RunPage, "2");
		assertEquals(r1.getRunPage(), 2);
	}

	/**
	*
	*/
	@Test
	void testSetLocked()
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
	void testImplicitPartitions()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		assertNull(xm.getImplicitPartitions(), "xm no impicit part");
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
		assertFalse(ruli.hasAttribute(AttributeName.PARTIDKEYS), "pidk");
		assertFalse(cb.hasAttribute(AttributeName.PARTIDKEYS), "pidk");
		ruli.addPartition(EnumPartIDKey.SheetName, "S1");
		assertEquals(ruli.getAttribute(AttributeName.PARTIDKEYS), EnumPartIDKey.SheetName.getName(), "pidk");
	}

	// //////////////////////////////////////////////////////////////////

	/**
	 * test reduction of partitions
	 */
	@Test
	void testReducePartitions()
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
		assertNotNull(xm.getPartition(mPart2, null), "Sig2 is still contained in vmap");
		vm.removeElementAt(1);
		xm.reducePartitions(vm);
		assertNotNull(xm.getPartition(mPart, null));
		assertNull(xm.getPartition(mPart2, null), "Sig2 was removed from vmap");
	}

	/**
	 *
	 */
	@Test
	void testReorderPartitions()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFResource r = n.getCreateResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input, 0);
		r.appendElement(ElementName.MEDIA);
		final JDFResource ab = r.addPartition(EnumPartIDKey.SheetName, "sh1").addPartition(EnumPartIDKey.PartVersion, "a b");
		final JDFResource cd = r.addPartition(EnumPartIDKey.SheetName, "sh2").addPartition(EnumPartIDKey.PartVersion, "c d");
		final JDFAttributeMap abs1 = ab.addPartition(EnumPartIDKey.Separation, "s1").getPartMap();
		final JDFAttributeMap cds1 = cd.addPartition(EnumPartIDKey.Separation, "s1").getPartMap();
		final JDFAttributeMap cds2 = cd.addPartition(EnumPartIDKey.Separation, "s2").getPartMap();

		final PartitionGetter pg = new PartitionGetter(r);
		pg.reorderPartitions(new StringArray("SheetName Separation PartVersion"));
		assertNotNull(pg.getPartition(null, null).getElement(ElementName.MEDIA));
		assertNotNull(pg.getPartition(abs1, EnumPartUsage.Explicit));
		assertNotNull(r.getPartition(abs1, EnumPartUsage.Explicit));
		assertNotNull(pg.getPartition(cds1, EnumPartUsage.Explicit));
		assertNotNull(r.getPartition(cds2, EnumPartUsage.Explicit));
		assertEquals(r, n.getCreateResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input, 0));
	}

	/**
	 *
	 */
	@Test
	void testRemoveImplicitPartions()
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
	void testRemoveInheritedAttributes()
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

	/**
	 * test expand and collapse method for subelements
	 */
	@Test
	void testCollapseElement()
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
		assertNotSame(rl.getLayoutElement(), rl1.getLayoutElement(), "no collapse elements");
		rl.collapse(false, true);
		assertNotSame(rl.getLayoutElement(), rl1.getLayoutElement(), "root element is different");
		ss1.setName("n1");
		rl.collapse(false, false);
		assertNotSame(rl.getLayoutElement(), rl1.getLayoutElement(), "no collapse elements");
		rl.collapse(false, true);
		assertEquals(rl.getLayoutElement(), rl1.getLayoutElement(), "root element is same");
		rl.expand(false);
		assertNotSame(rl.getLayoutElement(), rl1.getLayoutElement(), "expanded elements");
		rl.collapse(true, true);
		assertEquals(rl.getLayoutElement(), rl1.getLayoutElement(), "root element is same");
		rl.expand(true);
		assertNull(rl.getLayoutElement(), "expanded elements");
		assertNotSame(rl.getLayoutElement(), rl1.getLayoutElement(), "expanded elements");
		rl.collapse(true, true);
		assertNull(rl.getLayoutElement(), "no root element for collapse");
		assertNotSame(rl.getLayoutElement(), rl1.getLayoutElement(), "no root element for collapse");
	}

	/**
	 * test expand and collapse method for subelements
	 */
	@Test
	void testCollapseBetween()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFResource xm = n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		xm.setDescriptiveName("d1");
		final JDFResource sig = xm.addPartition(EnumPartIDKey.SignatureName, "sig1");
		sig.setDescriptiveName("sh");
		for (int i = 0; i < 3; i++)
		{
			sig.addPartition(EnumPartIDKey.SheetName, "s" + i).setDescriptiveName("sh");
		}
		xm.collapse(false, false);
		xm.unpartition(false);
		assertEquals(sig, sig.getLeaf(0));
	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	void testCollapseSubElement()
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
	void testSubelementClass()
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
	void testClonePartions()
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
		assertEquals(size, r0.getLeaves(false).size(), " after second application ");
		for (int i = 0; i < size; i++)
		{
			assertEquals(((JDFResource) r1.getLeaves(false).get(i)).getPartMap(), ((JDFResource) r0.getLeaves(false).get(i)).getPartMap());
		}
		final JDFResource r2 = (JDFResource) pool.appendElement("Layout");
		r2.clonePartitions(r0, new VString("SignatureName", null));
		size = r2.getLeaves(false).size();
		assertEquals(size, 3, " after third application - only signatureName");
		for (int i = 0; i < size; i++)
		{
			assertEquals(((JDFResource) r2.getLeaves(false).get(i)).getPartMap().size(), 1);
		}

		final JDFResource r3 = (JDFResource) pool.appendElement("Layout");
		r3.clonePartitions(s1, null);
		size = r3.getLeaves(false).size();
		assertEquals(size, 1, " partial clone: after 4th application - only signatureName");
		for (int i = 0; i < size; i++)
		{
			assertEquals(((JDFResource) r3.getLeaves(false).get(i)).getPartMap().size(), 1);
		}

		final JDFResource r4 = (JDFResource) pool.appendElement("Layout");
		r4.clonePartitions(sh1, null);
		size = r4.getLeaves(false).size();
		assertEquals(size, 1, " partial clone: after 5th application - only signatureName, sheetname 1");
		for (int i = 0; i < size; i++)
		{
			assertEquals(((JDFResource) r4.getLeaves(false).get(i)).getPartMap().size(), 2);
		}
		r4.clonePartitions(s1, null);
		size = r4.getLeaves(false).size();
		assertEquals(size, 2, " multiple partial clone: after 5th application - only signatureName, sheetname 1");

	}

	/**
	 * test clonePartitions method
	 */
	@Test
	void testClonePartionsExist()
	{
		final KElement pool = new JDFDoc("ResourcePool").getRoot();
		final JDFResource r0 = (JDFResource) pool.appendElement("Preview");
		final JDFResource sh1 = r0.addPartition(EnumPartIDKey.SignatureName, "s1").addPartition(EnumPartIDKey.SheetName, "sh1");
		final JDFResource sep1 = sh1.addPartition(EnumPartIDKey.Side, "Front").addPartition(EnumPartIDKey.Separation, "Black");
		final JDFResource r1 = (JDFResource) pool.appendElement("Ink");
		r1.addPartition(EnumPartIDKey.Separation, "Black");
		r1.clonePartitions(r0, null);
		final int size = r1.getLeaves(false).size();
		assertEquals(size, r0.getLeaves(false).size());
		for (int i = 0; i < size; i++)
		{
			assertEquals(((JDFResource) r1.getLeaves(false).get(i)).getPartMap(), ((JDFResource) r0.getLeaves(false).get(i)).getPartMap());
		}

	}

	/**
	 * test clonePartitions method
	 */
	@Test
	void testClonePartionsExist2()
	{
		final KElement pool = new JDFDoc("ResourcePool").getRoot();
		final JDFResource r0 = (JDFResource) pool.appendElement("Preview");
		final JDFResource sh1 = r0.addPartition(EnumPartIDKey.SignatureName, "s1").addPartition(EnumPartIDKey.SheetName, "sh1");
		final JDFResource sep1 = sh1.addPartition(EnumPartIDKey.Side, "Front").addPartition(EnumPartIDKey.Separation, "Black");
		final JDFResource sep2 = sh1.addPartition(EnumPartIDKey.Side, "Back").addPartition(EnumPartIDKey.Separation, "Black");
		final JDFResource r1 = (JDFResource) pool.appendElement("Ink");
		final JDFResource black = r1.addPartition(EnumPartIDKey.Separation, "Black");
		black.setDescriptiveName("black color");
		black.addPartition(EnumPartIDKey.Side, "Back");
		black.addPartition(EnumPartIDKey.Side, "Front");
		r1.clonePartitions(r0, null);
		final int size = r1.getLeaves(false).size();
		assertEquals(size, r0.getLeaves(false).size());
		for (int i = 0; i < size; i++)
		{
			final JDFResource l1 = (JDFResource) r1.getLeaves(false).get(i);
			final JDFResource l0 = (JDFResource) r0.getLeaves(false).get(i);
			assertEquals(l1.getPartMap(), l0.getPartMap());
			assertEquals("black color", l1.getDescriptiveName());
		}

	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	void testCollapseUniqueTree()
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
	void testDeleteNodePartition()
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
	void testDeleteNodePartition2()
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
	void testCollapseUniqueLeaves()
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
	void testCollapseEmptyAttribute()
	{
		final JDFNode n = JDFNode.createRoot();

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFResource sig = dpp.addPartition(EnumPartIDKey.SignatureName, "s1");
		final JDFResource sh = sig.addPartition(EnumPartIDKey.SheetName, "sh1");
		final JDFResource f = sh.addPartition(EnumPartIDKey.Side, "Front");
		final JDFResource b = sh.addPartition(EnumPartIDKey.Side, "Back");
		f.setDescriptiveName("");
		b.setDescriptiveName("");
		dpp.collapse(false, false);
		assertFalse(f.hasAttribute_KElement(AttributeName.DESCRIPTIVENAME, null, false));
		assertTrue(dpp.hasAttribute(AttributeName.DESCRIPTIVENAME));
	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	void testCollapseFilter()
	{
		final JDFNode n = JDFNode.createRoot();

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFResource sig = dpp.addPartition(EnumPartIDKey.SignatureName, "s1");
		final JDFResource sh = sig.addPartition(EnumPartIDKey.SheetName, "sh1");
		final JDFResource f = sh.addPartition(EnumPartIDKey.Side, "Front");
		final JDFResource b = sh.addPartition(EnumPartIDKey.Side, "Back");
		f.setDescriptiveName("a");
		b.setDescriptiveName("a");
		f.setProductID("a");
		b.setProductID("a");
		dpp.collapse(false, false, new StringArray(AttributeName.DESCRIPTIVENAME));
		assertFalse(f.hasAttribute_KElement(AttributeName.DESCRIPTIVENAME, null, false));
		assertTrue(dpp.hasAttribute(AttributeName.DESCRIPTIVENAME));
		assertFalse(dpp.hasAttribute_KElement(AttributeName.PRODUCTID, null, false));
		assertTrue(f.hasAttribute(AttributeName.PRODUCTID));
	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	void testCollapseFilterElemRef()
	{
		final JDFNode n = JDFNode.createRoot();

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFResource sig = dpp.addPartition(EnumPartIDKey.SignatureName, "s1");
		final JDFResource sh = sig.addPartition(EnumPartIDKey.SheetName, "sh1");
		final JDFResource f = sh.addPartition(EnumPartIDKey.Side, "Front");
		final JDFResource b = sh.addPartition(EnumPartIDKey.Side, "Back");
		JDFResource m = (JDFResource) f.appendElement(ElementName.MEDIA);
		m = m.makeRootResource();
		f.appendElement(ElementName.COMMENT);
		b.refElement(m);
		b.appendElement(ElementName.COMMENT);
		dpp.collapse(false, true, new StringArray(ElementName.MEDIA));
		assertNull(f.getElement_KElement(ElementName.MEDIA, null, 0));
		assertNotNull(f.getElement_KElement(ElementName.COMMENT, null, 0));
		assertNull(dpp.getElement_KElement(ElementName.COMMENT, null, 0));
		assertNull(f.getElement_KElement(ElementName.MEDIA, null, 0));
	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	void testCollapseFilterElem()
	{
		final JDFNode n = JDFNode.createRoot();

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFResource sig = dpp.addPartition(EnumPartIDKey.SignatureName, "s1");
		final JDFResource sh = sig.addPartition(EnumPartIDKey.SheetName, "sh1");
		final JDFResource f = sh.addPartition(EnumPartIDKey.Side, "Front");
		final JDFResource b = sh.addPartition(EnumPartIDKey.Side, "Back");
		f.appendElement(ElementName.MEDIA);
		f.appendElement(ElementName.COMMENT);
		b.appendElement(ElementName.MEDIA);
		b.appendElement(ElementName.COMMENT);
		dpp.collapse(false, true, new StringArray(ElementName.MEDIA));
		assertNull(f.getElement_KElement(ElementName.MEDIA, null, 0));
		assertNotNull(f.getElement_KElement(ElementName.COMMENT, null, 0));
		assertNull(dpp.getElement_KElement(ElementName.COMMENT, null, 0));
		assertNull(f.getElement_KElement(ElementName.MEDIA, null, 0));
	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	void testCollapseFilterElemDif()
	{
		final JDFNode n = JDFNode.createRoot();

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFResource sig = dpp.addPartition(EnumPartIDKey.SignatureName, "s1");
		final JDFResource sh = sig.addPartition(EnumPartIDKey.SheetName, "sh1");
		final JDFResource f = sh.addPartition(EnumPartIDKey.Side, "Front");
		final JDFResource b = sh.addPartition(EnumPartIDKey.Side, "Back");
		f.appendElement(ElementName.MEDIA).setAttribute("A", "B");
		f.appendElement(ElementName.COMMENT);
		b.appendElement(ElementName.MEDIA);
		b.appendElement(ElementName.COMMENT);
		dpp.collapse(false, true, new StringArray(ElementName.MEDIA));
		assertNotNull(f.getElement_KElement(ElementName.MEDIA, null, 0));
		assertNotNull(f.getElement_KElement(ElementName.COMMENT, null, 0));
		assertNull(dpp.getElement_KElement(ElementName.COMMENT, null, 0));
		assertNull(dpp.getElement_KElement(ElementName.MEDIA, null, 0));
	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	void testUnpartitionPartIDKeys()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFResource r = n.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFResource r1 = r.addPartition(EnumPartIDKey.BlockName, "B1");
		final JDFResource r2 = r1.addPartition(EnumPartIDKey.Condition, "Good");
		final JDFAttributeMap m = new JDFAttributeMap();
		m.put(EnumPartIDKey.BlockName, "B1");
		m.put(EnumPartIDKey.Condition, "Good");

		r2.setDescriptiveName("test");
		assertEquals(r2, r.getPartition(m, EnumPartUsage.Implicit));
		r.unpartition(false);
		assertEquals(r, r.getPartition(m, EnumPartUsage.Implicit));

	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	void testCollapse()
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
	void testFixVersion()
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
	void testExpand()
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
		assertEquals(xmPart.getBrand(), "PartBrand", "expanded sub");
		assertEquals(xmPart.getGeneralID("testID", 0), "partValue", "expanded sub");
		assertEquals(xmPart2.getBrand(), "rootBrand", "expanded sub2");
		assertEquals(xmPart2.getGeneralID("testID", 0), "rootValue", "expanded sub2");
		assertTrue(xmPart2.hasAttribute_KElement("Brand", null, false), "hasBrand");
		assertTrue(xmPart2.getElement_KElement("GeneralID", null, 0) != null, "hasID");
		assertFalse(xmPart.hasAttribute_KElement(AttributeName.SHEETNAME, null, false), "has part Key");
		assertFalse(xmPart2.hasAttribute_KElement(AttributeName.SHEETNAME, null, false), "has part Key");

		xm.collapse(false, true);
		assertEquals(xmPart.getBrand(), "PartBrand", "expanded sub after collapse");
		assertEquals(xmPart.getGeneralID("testID", 0), "partValue", "expanded sub after collapse");
		assertEquals(xmPart2.getBrand(), "rootBrand", "expanded sub2 after collapse");
		assertEquals(xmPart2.getGeneralID("testID", 0), "rootValue", "expanded sub2 after collapse");
		assertFalse(xmPart2.hasAttribute_KElement("Brand", null, false), "hasBrand");
		assertTrue(xmPart2.getElement_KElement("GeneralID", null, 0) == null, "hasID");
		assertFalse(xmPart.hasAttribute_KElement(AttributeName.SHEETNAME, null, false), "has part Key");
		assertFalse(xmPart2.hasAttribute_KElement(AttributeName.SHEETNAME, null, false), "has part Key");

		JDFExposedMedia xmPart3 = (JDFExposedMedia) xmPart2.getParentNode_KElement().getParentNode_KElement();
		mPart.put("SignatureName", "Sig2");
		final JDFExposedMedia xmPart4 = (JDFExposedMedia) xm.getPartition(mPart, null);

		xmPart3.expand(true);
		assertTrue(xmPart2.hasAttribute_KElement("Brand", null, false), "hasBrand");
		assertFalse(xmPart4.hasAttribute_KElement("Brand", null, false), "hasBrand");
		assertTrue(xmPart2.getElement_KElement("GeneralID", null, 0) != null, "hasID");
		assertFalse(xmPart4.getElement_KElement("GeneralID", null, 0) != null, "hasID");
		assertFalse(xmPart.hasAttribute_KElement(AttributeName.SHEETNAME, null, false), "has part Key");
		assertFalse(xmPart2.hasAttribute_KElement(AttributeName.SHEETNAME, null, false), "has part Key");

		xmPart3.collapse(false, true);
		assertFalse(xmPart2.hasAttribute_KElement("Brand", null, false), "hasBrand");
		assertFalse(xmPart3.hasAttribute_KElement("Brand", null, false), "hasBrand");
		assertFalse(xmPart2.getElement_KElement("GeneralID", null, 0) != null, "hasID");

		xmPart3 = (JDFExposedMedia) xmPart4.getParentNode_KElement().getParentNode_KElement();
		xmPart3.expand(true);
		assertTrue(xmPart4.hasAttribute_KElement("Brand", null, false), "hasBrand");
		assertFalse(xmPart2.hasAttribute_KElement("Brand", null, false), "hasBrand");
		assertTrue(xmPart4.getElement_KElement("GeneralID", null, 0) != null, "hasID");
		assertFalse(xmPart2.getElement_KElement("GeneralID", null, 0) != null, "hasID");
		xmPart3.collapse(false, true);
		assertFalse(xmPart4.hasAttribute_KElement("Brand", null, false), "hasBrand");
		assertFalse(xmPart3.hasAttribute_KElement("Brand", null, false), "hasBrand");
		assertFalse(xmPart4.getElement_KElement("GeneralID", null, 0) != null, "hasID");
		assertTrue(xmPart.getElement("GeneralID", null, 0) != null, "hasID");

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		dpp.expand(true);
		dpp.expand(false);
		assertTrue(dpp.hasAttribute("ID"));

	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	void testExpandLeaf()
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
	void testGeneralID()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		final JDFExposedMedia xm2 = (JDFExposedMedia) xm.getPartition(new JDFAttributeMap("SignatureName", "Sig1"), EnumPartUsage.Explicit);
		xm.setGeneralID("foo", "bar");
		assertEquals(xm.getGeneralID("foo", 0), "bar", "");
		assertEquals(xm2.getGeneralID("foo", 0), "bar", "");
		assertEquals(xm.numChildElements(ElementName.GENERALID, null), 1, "");
		xm.setGeneralID("foo", "bar2");
		assertEquals(xm.getGeneralID("foo", 0), "bar2", "");
		assertEquals(xm.numChildElements(ElementName.GENERALID, null), 1, "");
		assertEquals(xm2.numChildElements(ElementName.GENERALID, null), 1, "");
		xm2.setGeneralID("foo", "bar4");
		xm.setGeneralID("foo2", "bar3");
		assertEquals(xm.getGeneralID("foo", 0), "bar2", "");
		assertEquals(xm2.getGeneralID("foo", 0), "bar4", "");
		assertEquals(xm.getGeneralID("foo2", 0), "bar3", "");
		assertEquals(xm.numChildElements(ElementName.GENERALID, null), 2, "");
		xm.removeGeneralID("foo");
		assertNull(xm.getGeneralID("foo", 0), "");
		assertEquals(xm.getGeneralID("foo2", 0), "bar3", "");
		assertEquals(xm.numChildElements(ElementName.GENERALID, null), 1, "");
		xm.setGeneralID("foo3", "bar33");
		final JDFGeneralID gi = xm.getGeneralID(0);
		assertEquals(gi.getIDUsage(), "foo2", "");
		xm.removeGeneralID(null);
		assertEquals(xm.numChildElements(ElementName.GENERALID, null), 0, "");
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	void testGeneralIDEmptyNamespace()
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
	void testInstantiations()
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
	void testGetElement()
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
	void testGetXPathAttribute()
	{
		final JDFDoc doc = creatXMDoc();
		final JDFNode n = doc.getJDFRoot();
		assertEquals(n.getXPathAttribute("ResourcePool/ExposedMedia/ExposedMedia/@SignatureName", null), "Sig1");
		assertNull(n.getXPathAttribute("ResourcePool/ExposedMedia/ExposedMedia/ExposedMedia/@SignatureName", null));
	}

	/**
	 *
	 */
	@Test
	void testRemoveChildren()
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

	/**
	 *
	 */
	@Test
	void testRemoveAttributeFromLeaves()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFResource xm = n.addResource("ExposedMedia", EnumUsage.Input);
		xm.setAgentName("a1");
		xm.addPartition(EnumPartIDKey.SheetIndex, "1").setAgentName("a2");
		xm.removeAttributeFromLeaves(AttributeName.AGENTNAME, null);
		assertEquals("", xm.getAgentName());
		assertEquals("", xm.getLeaf(0).getAgentName());
	}

	/**
	 * tests getxpathattribute for partitions
	 */
	@Test
	void testGetXPathElement()
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
	void testGetResStatus()
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
	void testGetCreatePartition2()
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
	void testGetCreatePartitionCreate()
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
	void testGetCreatePartition3()
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
	void testGetCreatePartitionSelf()
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
	void testGetCreate_PartVersionStrict()
	{
		final JDFResource r = (JDFResource) new JDFDoc(ElementName.EXPOSEDMEDIA).getRoot();
		final JDFAttributeMap partMap1 = new JDFAttributeMap(EnumPartIDKey.PartVersion, "EN");
		final JDFAttributeMap partMap2 = new JDFAttributeMap(EnumPartIDKey.PartVersion, "EN FR");
		final JDFResource r1 = r.getCreatePartition(partMap1, null);
		final JDFResource r2 = r.getCreatePartition(partMap2, null);
		assertEquals(1, r.getLeafArray(false).size());
		assertEquals(r1, r2);
	}

	/**
	 *
	 */
	@Test
	void testAddpartitionEnum()
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
	void testAddpartition()
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

	/**
	 *
	 */
	@Test
	void testAddpartitionStar()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		final JDFResource media = n.addResource("Media", null, EnumUsage.Input, null, null, null, null);
		final JDFResource r2 = media.addPartition(EnumPartIDKey.PartVersion, "*");
		assertEquals("*", r2.getPartVersion());
	}

	/**
	 *
	 */
	@Test
	void testAddpartitionStar2()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		final JDFResource media = n.addResource("Media", null, EnumUsage.Input, null, null, null, null);
		final JDFResource r1 = media.addPartition(EnumPartIDKey.PartVersion, "a");
		final JDFResource r2 = media.addPartition(EnumPartIDKey.PartVersion, "*");
		assertEquals("*", r2.getPartVersion());
	}

	/**
	 *
	 */
	@Test
	void testAddpartitionStar1()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		final JDFResource media = n.addResource("Media", null, EnumUsage.Input, null, null, null, null);
		final JDFResource r1 = media.addPartition(EnumPartIDKey.SheetName, "a");
		final JDFResource r2 = media.addPartition(EnumPartIDKey.SheetName, "*");
		assertEquals("*", r2.getSheetName());
	}

	/**
	 *
	 */
	@Test
	void testMultiplePartIDKeys()
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
		assertTrue(r.getInvalidAttributes(EnumValidationLevel.Incomplete, true, 999).contains(EnumPartIDKey.Condition.getName()), "Duplicate partition found");

	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	/**
	 *
	 */
	@Test
	void testConsistentPartIDKeys()
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
	void testPreview14()
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
	 * @see JDFTestCaseBase#tearDown()
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
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		bAgent = JDFResource.getAutoAgent();
		bSubElem = JDFResource.getAutoSubElementClass();
	}
}
