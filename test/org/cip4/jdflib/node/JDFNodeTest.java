/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of
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
package org.cip4.jdflib.node;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode.EnumActivation;
import org.cip4.jdflib.node.JDFNode.EnumCleanUpMerge;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.node.JDFNode.NodeIdentifier;
import org.cip4.jdflib.pool.JDFAncestorPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFResource.EnumAmountMerge;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.devicecapability.JDFModule;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFMerge;
import org.cip4.jdflib.util.JDFSpawn;
import org.cip4.jdflib.util.StatusCounter;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class JDFNodeTest extends JDFTestCaseBase
{

	/**
	 * performance test
	 */
	public void testBigSortChildren()
	{
		final String strJDFName = "000023_Test_PR3.0.jdf";
		final JDFParser p = new JDFParser();
		final JDFDoc d = p.parseFile(sm_dirTestData + strJDFName);
		final JDFNode n = d.getJDFRoot();
		n.sortChildren();
	}

	// /////////////////////////////////////////////////////
	/**
	 * 
	 */
	public void testGetPredecessors()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFNode n2 = n.addJDFNode("ImageSetting");
		final JDFResource r = n2.addResource("ExposedMedia", null, EnumUsage.Output, null, n, null, null);
		final JDFNode n3 = n.addJDFNode("ConventionalPrinting");
		n3.linkResource(r, EnumUsage.Input, null);
		assertTrue(n3.getPredecessors(true, true).contains(n2));
		assertTrue(n3.getPredecessors(true, false).contains(n2));
		assertTrue(n2.getPredecessors(false, true).contains(n3));
		assertTrue(n2.getPredecessors(false, false).contains(n3));
		n3.linkResource(r, EnumUsage.Output, null);
		// used to dead loop here after in=out...
		n3.getPredecessors(false, true);
		n3.getPredecessors(true, true);
	}

	/**
	 * 
	 */
	public void testGetRefPartition()
	{
		final JDFDoc jdfDoc = JDFDoc.parseFile(sm_dirTestData + "GetReferencedPartition.jdf");
		final JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("1008.C", null);
		final JDFResourceLinkPool linkPool = nodeProc.getResourceLinkPool();
		final JDFResourceLink link = linkPool.getPoolChild(0, "ExposedMediaLink", null, null);
		final VJDFAttributeMap vam = nodeProc.getExecutablePartitions(link, JDFResource.EnumResStatus.Draft, true);
		assertTrue(vam.size() == 5);

		// VJDFAttributeMap:
		// [0]JDFAttributeMap: { (Side = Front) (SheetName = 002_Text-1)
		// (SignatureName = SIG2) (Separation = Cyan) }
		// [1]JDFAttributeMap: { (Side = Front) (SheetName = 002_Text-1)
		// (SignatureName = SIG2) (Separation = Magenta) }
		// [2]JDFAttributeMap: { (Side = Front) (SheetName = 002_Text-1)
		// (SignatureName = SIG2) (Separation = Yellow) }
		// [3]JDFAttributeMap: { (Side = Front) (SheetName = 002_Text-1)
		// (SignatureName = SIG2) (Separation = Black) }
		// [4]JDFAttributeMap: { (Side = Front) (SheetName = 002_Text-1)
		// (SignatureName = SIG2) }

	}

	// /////////////////////////////////////////////////////

	public void testSortChildren()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFResourcePool rp = n.appendResourcePool();
		final KElement m2 = rp.appendElement("Media");
		m2.setAttribute("ID", "m2");
		final KElement xm = rp.appendElement("ExposedMedia");
		xm.setAttribute("ID", "xm2");
		final KElement m1 = rp.appendElement("Media");
		m1.setAttribute("ID", "m1");
		final JDFNode n2 = n.addJDFNode("foo");
		n2.setID("n2");
		final JDFNode n1 = n.addJDFNode("foo");
		n1.setID("n1");
		final JDFResourcePool rp2 = n2.appendResourcePool();
		final KElement m22 = rp2.appendElement("Media");
		m22.setAttribute("ID", "m2");
		final KElement xm2 = rp2.appendElement("ExposedMedia");
		xm2.setAttribute("ID", "xm2");
		final KElement m21 = rp2.appendElement("Media");
		m21.setAttribute("ID", "m1");

		n.sortChildren();
		assertEquals("reordered sub elements", n.getChildWithAttribute("JDF", "ID", null, "n1", 0, true).getNextSiblingElement(null, null), n.getChildWithAttribute("JDF", "ID", null, "n2", 0, true));

		assertEquals(rp2.getFirstChildElement(), xm2);
		assertEquals(xm2.getNextSiblingElement(), m21);
		assertEquals(m21.getNextSiblingElement(), m22);

	}

	public void testSetType()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		assertTrue("good Type", n.setType(JDFNode.EnumType.ConventionalPrinting.getName(), true));
		assertEquals("xsiType", n.getXSIType(), JDFNode.EnumType.ConventionalPrinting.getName());
		try
		{
			n.setType("badTypeName", true);
			fail("bad type");
		}
		catch (final JDFException e)
		{
			assertNotNull("bad type", e);
		}
		n.setType("foo:bar", false);
		assertEquals(n.getType(), "foo:bar");
		assertNull(n.getXSIType());
		final VString types = new VString("Interpreting Rendering", " ");
		n.setCombined(types);
		assertEquals(n.getTypes(), types);
		n.setType(EnumType.ContactCopying);
		assertNull(n.getAttribute("Types", null, null));
	}

	public void testSetEnum()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		assertTrue("good Type", n.setType(JDFNode.EnumType.ConventionalPrinting.getName(), true));
		assertEquals("xsiType", n.getXSIType(), JDFNode.EnumType.ConventionalPrinting.getName());
		try
		{
			n.setType("badTypeName", true);
			fail("bad type");
		}
		catch (final JDFException e)
		{
			assertNotNull("bad type", e);
		}
		n.setType("foo:bar", false);
		assertEquals(n.getType(), "foo:bar");
		assertNull(n.getXSIType());
	}

	public void testLinkResourceNS()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFResource rl = n.addResource("NS:RunList", EnumResourceClass.Parameter, null, null, null, "www.ns.com", null);
		assertEquals("res ns", "www.ns.com", rl.getNamespaceURI());
		final JDFResourceLink rll = n.linkResource(rl, EnumUsage.Input, null);
		assertEquals("res ns", "www.ns.com", rll.getNamespaceURI());
		assertFalse(rll.hasAttribute(AttributeName.COMBINEDPROCESSINDEX));
	}

	public void testEnsureLink()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFResource rl = n.addResource("RunList", null);
		final JDFResourceLink rl2 = n.ensureLink(rl, EnumUsage.Input, null);
		assertNotNull(rl2);
		assertEquals(rl2, n.ensureLink(rl, EnumUsage.Input, null));

	}

	/**
	 * test whether combinedprocessIndex is automagically and correctly assigned
	 * 
	 */
	public void testInsertTypeInTypes()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Combined);
		n.insertTypeInTypes(EnumType.Combine, 999);
		assertEquals(n.getAttribute("Types"), "Combine");
		n.insertTypeInTypes(EnumType.Imposition, 999);
		assertEquals(n.getAttribute("Types"), "Combine Imposition");
		n.insertTypeInTypes(EnumType.Stripping, 1);
		assertEquals(n.getAttribute("Types"), "Combine Stripping Imposition");
		n.insertTypeInTypes(EnumType.DigitalDelivery, 0);
		assertEquals(n.getAttribute("Types"), "DigitalDelivery Combine Stripping Imposition");
		n.insertTypeInTypes(EnumType.Imposition, 99999);
		assertEquals(n.getAttribute("Types"), "DigitalDelivery Combine Stripping Imposition Imposition");

	}

	/**
	 * test whether combinedprocessIndex is automagically and correctly assigned
	 * 
	 */
	public void testLinkNamesCombined()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setCombined(new VString("Interpreting Trapping Rendering", " "));
		final VString e = n.linkNames();
		assertTrue(e.contains(ElementName.CUSTOMERINFO));
		assertTrue(e.contains(ElementName.RENDERINGPARAMS));
	}

	/**
	 * test whether combinedprocessIndex is automagically and correctly assigned
	 * 
	 */
	public void testLinkNamesProduct()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Product);
		final VString e = n.linkNames();
		assertTrue(e.contains(ElementName.CUSTOMERINFO));
		assertTrue(e.contains(ElementName.MEDIAINTENT));
		assertTrue(e.contains(ElementName.COMPONENT));
	}

	/**
	 * test whether combinedprocessIndex is automagically and correctly assigned
	 * 
	 */
	public void testLinkResourceSimple()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Folding);
		final JDFResource foPa = n.addResource(ElementName.FOLDINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rlfoPa = n.getLink(foPa, null);
		assertFalse(rlfoPa.hasAttribute(AttributeName.COMBINEDPROCESSINDEX));
	}

	/**
	 * test whether combinedprocessIndex is automagically and correctly assigned
	 * 
	 */
	public void testLinkOutput()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFNode n1 = n.addJDFNode(EnumType.Interpreting);
		final JDFRunList rul1 = (JDFRunList) n1.addResource("RunList", EnumUsage.Output);
		final JDFNode n2 = n.addJDFNode(EnumType.Rendering);
		n2.linkOutputs(n1);
		assertEquals(n2.getResource("RunList", EnumUsage.Input, 0), rul1);
		n2.linkOutputs(n1);
		assertEquals(n2.getResource("RunList", EnumUsage.Input, 0), rul1);
		assertNull(n2.getResource("RunList", null, 1));

	}

	/**
	 * test whether combinedprocessIndex is automagically and correctly assigned
	 * 
	 */
	public void testLinkResourceCombined() throws Exception
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Combined);
		VString v = new VString("Folding Cutting Foo:Bar Folding", " ");
		n.setTypes(v);
		final JDFResource foPa = n.addResource(ElementName.FOLDINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rlfoPa = n.getLink(foPa, null);
		assertEquals("folding is 0 and 2", rlfoPa.getCombinedProcessIndex().toString(), "0 3");

		final JDFResource cuPa = n.addResource(ElementName.CUTTINGPARAMS, null, null, null, null, null, null);
		final JDFResourceLink rlCuPa = n.linkResource(cuPa, EnumUsage.Input, null);
		assertEquals("cutting is 1", rlCuPa.getCombinedProcessIndex().toString(), "1");

		final JDFResource pePa = n.addResource(ElementName.PERFORATINGPARAMS, null, null, null, null, null, null);
		final JDFResourceLink rlPePa = n.linkResource(pePa, EnumUsage.Input, null);
		assertNull("no perforating", rlPePa.getCombinedProcessIndex());

		n.insertTypeInTypes(EnumType.AdhesiveBinding, 999);
		assertEquals("folding is 0 and 2", rlfoPa.getCombinedProcessIndex().toString(), "0 3");
		assertEquals("cutting is 1", rlCuPa.getCombinedProcessIndex().toString(), "1");
		v = n.getTypes();
		assertEquals("appended one type", v.elementAt(4), EnumType.AdhesiveBinding.getName());
		assertEquals("appended one type", v.size(), 5);

		n.insertTypeInTypes(EnumType.Bundling, -2);
		assertEquals("folding is 0 and 3", rlfoPa.getCombinedProcessIndex().toString(), "0 4");
		assertEquals("cutting is 1", rlCuPa.getCombinedProcessIndex().toString(), "1");
		v = n.getTypes();
		assertEquals("appended one type", v.elementAt(3), EnumType.Bundling.getName());
		assertEquals("appended one type", v.size(), 6);

		n.insertTypeInTypes(EnumType.BoxPacking, 0);
		assertEquals("folding is 1 and 5", rlfoPa.getCombinedProcessIndex().toString(), "1 5");
		assertEquals("cutting is 2", rlCuPa.getCombinedProcessIndex().toString(), "2");
		v = n.getTypes();
		assertEquals("appended one type", v.elementAt(0), EnumType.BoxPacking.getName());
		assertEquals("appended one type", v.elementAt(1), EnumType.Folding.getName());
		assertEquals("appended one type", v.size(), 7);

		final JDFResource comp = n.addResource(ElementName.COMPONENT, null, null, null, null, null, null);
		final JDFResourceLink rlcomp = n.linkResource(comp, EnumUsage.Output, null);
		assertEquals("cpi output", rlcomp.getCombinedProcessIndex(), new JDFIntegerList("2 6"));

		final JDFResource compIn = n.addResource(ElementName.COMPONENT, null, null, null, null, null, null);
		final JDFResourceLink rlcompIn = n.linkResource(compIn, EnumUsage.Input, null);
		assertEquals("cpi output", rlcompIn.getCombinedProcessIndex(), new JDFIntegerList("0 4"));

		final JDFResource devIn = n.addResource(ElementName.DEVICE, null, null, null, null, null, null);
		final JDFResourceLink rlDevIn = n.linkResource(devIn, EnumUsage.Input, null);
		assertNull("dev input", rlDevIn.getCombinedProcessIndex());

		final JDFResource niIn = n.addResource(ElementName.NODEINFO, null, null, null, null, null, null);
		final JDFResourceLink rlNiIn = n.linkResource(niIn, EnumUsage.Input, null);
		assertNull("ni input", rlNiIn.getCombinedProcessIndex());

	}

	// /////////////////////////////////////////////////////////////////////////

	public void testAddTypes()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode mainNode = doc.getJDFRoot();
		mainNode.setType("ProcessGroup", true);
		mainNode.addTypes(EnumType.Shrinking);
		mainNode.addTypes(EnumType.Verification);
		assertTrue(mainNode.getEnumTypes().contains(EnumType.Shrinking));
		assertTrue(mainNode.getEnumTypes().contains(EnumType.Verification));
		assertEquals(mainNode.getEnumTypes().size(), 2);
		mainNode.addTypes(EnumType.Verification);
		assertEquals(mainNode.getEnumTypes().size(), 3);
	}

	// /////////////////////////////////////////////////////////////////////////

	public void testAddProduct()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode mainNode = doc.getJDFRoot();
		mainNode.setType(EnumType.Product);
		final JDFNode p2 = mainNode.addProduct();
		assertEquals(p2.getXSIType(), "Product");
	}

	// /////////////////////////////////////////////////////////////////////////

	public void testAddResource()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode mainNode = doc.getJDFRoot();
		mainNode.setType("Product", true);
		final JDFComponent myComponent = (JDFComponent) mainNode.addResource(ElementName.COMPONENT, JDFResource.EnumResourceClass.Quantity, EnumUsage.Output, null, mainNode, null, null);
		assertEquals(myComponent.getResourceClass(), JDFResource.EnumResourceClass.Quantity);
		final JDFComponent myComponent2 = (JDFComponent) mainNode.addResource(ElementName.COMPONENT, EnumUsage.Input);
		assertEquals(myComponent2.getResourceClass(), JDFResource.EnumResourceClass.Quantity);
		myComponent.setDescriptiveName("descriptive_name");
		assertNotNull("", mainNode.getMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyOutput, null, 0));
		final JDFResource myRes = mainNode.addResource("whatever:foo", JDFResource.EnumResourceClass.Quantity, EnumUsage.Output, null, mainNode, "www.whatever.com", null);
		assertEquals(myRes.getResourceClass(), JDFResource.EnumResourceClass.Quantity);
		myRes.setDescriptiveName("descriptive_name");
	}

	// /////////////////////////////////////////////////////////////

	public void testAddInternalPipe()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode mainNode = doc.getJDFRoot();
		mainNode.setType("Combined", true);
		mainNode.setTypes(new VString("Cutting Folding Folding Inserting", " "));
		final JDFComponent myComponent = (JDFComponent) mainNode.addInternalPipe(ElementName.COMPONENT, 3, 0);
		assertNotNull(myComponent);
		assertEquals(myComponent.getPipeProtocol(), "Internal");
		final VElement vE = myComponent.getLinks(myComponent.getLinkString(), null);
		assertEquals(vE.size(), 2);
		assertEquals(((JDFResourceLink) vE.elementAt(0)).getPipeProtocol(), "Internal");
		assertEquals(((JDFResourceLink) vE.elementAt(1)).getPipeProtocol(), "Internal");
	}

	// ///////////////////////////////////////////////////////

	public void testCloneResourceToModify()
	{
		final JDFDoc d = JDFTestCaseBase.creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		final JDFResourceLink rl = n.getMatchingLink(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, 0);
		final JDFAttributeMap m = new JDFAttributeMap();
		m.put("SignatureName", "Sig1");
		rl.setPartMap(m);
		rl.setAmount(42, m);
		final JDFResourceAudit ra = n.cloneResourceToModify(rl);
		assertTrue("link", ra.getNewLink().hasChildElement("Part", null));
		assertTrue("link", ra.getOldLink().hasChildElement("Part", null));
		assertTrue("link", ra.getNewLink().hasChildElement("AmountPool", null));
		assertTrue("link", ra.getOldLink().hasChildElement("AmountPool", null));
	}

	public void testEraseEmptyAttributes()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFResource r = n.addResource("fooRes", EnumResourceClass.Consumable, EnumUsage.Input, EnumProcessUsage.AnyInput, null, "", null);
		r.appendAttribute("bar", "", "", "", false);
		n.eraseEmptyAttributes(true);
		assertFalse(n.hasAttribute("bar"));
	}

	public void testEraseEmptyNodes()
	{
		// note: when using JDFParser.parse(string), empty nodes are removed by
		// default
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.addResource("_foo", EnumResourceClass.Consumable, EnumUsage.Input, EnumProcessUsage.AnyInput, null, "", null);
		n.removeResource("_foo", 0);
		n.eraseEmptyNodes(true);
		assertNotNull(n.getResourcePool());
		assertNotNull(n.getResourceLinkPool());
	}

	public void testFixVersion()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode node = doc.getJDFRoot();
		node.setAttribute("Type", "Scanning");
		assertFalse(node.hasAttribute("type", AttributeName.XSIURI, false));
		node.fixVersion(null);
		assertTrue(node.hasAttribute("type", AttributeName.XSIURI, false));
		assertTrue(doc.write2String(0).indexOf(AttributeName.XSIURI) > 0);
	}

	// //////////////////////////////////////////////////////////////////////////
	// /////

	public void testEraseUnlinkedResources()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFResource r = n.addResource("Component", null, null, null, null, null, null);
		final JDFResourcePool rp = n.getResourcePool();
		assertTrue(r instanceof JDFComponent);
		assertFalse(n.hasChildElement("ResourceLinkPool", null));
		final JDFResourceLinkPool rlp = n.getCreateResourceLinkPool();
		assertEquals(rp.getUnlinkedResources().elementAt(0), r);

		final JDFResourceLink rl = rlp.linkResource(r, EnumUsage.Input, EnumProcessUsage.BookBlock);
		assertNotNull(rl);
		assertNull(rp.getUnlinkedResources());
		final JDFResource rx = n.addResource("ExposedMedia", null, null, null, null, null, null);
		assertEquals(rp.getUnlinkedResources().elementAt(0), rx);

		n.setVersion(EnumVersion.Version_1_2);
		final JDFCustomerInfo ci = n.appendCustomerInfo();
		JDFContact co = ci.appendContact();
		co = (JDFContact) co.makeRootResource(null, null, true);
		assertEquals(rp.getUnlinkedResources().elementAt(0), rx);
		assertEquals(rp.getUnlinkedResources().size(), 1);
		n.eraseUnlinkedResources();
		assertNull(rp.getUnlinkedResources());
		assertNull("didn't zapp unlinked xm", rp.getElement("ExposedMedia"));
		assertEquals(rp.getElement("Contact"), co);

		ci.deleteNode();
		assertEquals("referenced contact accidentally zapped", rp.getUnlinkedResources().elementAt(0), co);
		n.eraseUnlinkedResources();
		assertNull("didn't zapp unlinked co", rp.getElement("Contact"));

		final JDFResource rFoo = n.addResource("FOO:Bar", EnumResourceClass.Handling, null, null, null, "www.foo.com", null);
		assertEquals(rp.getUnlinkedResources().elementAt(0), rFoo);
		final JDFResourceLink rlFoo = n.linkResource(rFoo, EnumUsage.Output, null);
		assertNotNull(rlFoo);
		assertNull(rp.getUnlinkedResources());

	}

	// ////////////////////////////////////////////////////////

	public void testRemoveResource()
	{
		final JDFDoc gd = new JDFDoc("JDF");
		final JDFNode n = gd.getJDFRoot();
		// JDFResource r=
		n.addResource("Media", EnumUsage.Input);
		// JDFResource r2=
		n.addResource("Media", EnumUsage.Input);
		assertNotNull(n.getLink(1, null, null, null));
		n.removeResource("Media", 0);
		assertNull(n.getLink(1, null, null, null));
		assertNotNull(n.getLink(0, null, null, null));
		n.removeResource("Media", 0);
		assertNull(n.getLink(0, null, null, null));

	}

	// //////////////////////////

	/**
	 * 
	 */
	public void testResourceAudit()
	{
		final JDFDoc gd = new JDFDoc("JDF");
		final JDFNode n = gd.getJDFRoot();

		final JDFRunList rl = (JDFRunList) n.addResource("RunList", null, EnumUsage.Input, null, null, null, null);
		n.setType("Product", false);
		n.setStatus(EnumNodeStatus.Waiting);
		final JDFResourceLink rli = n.getLink(rl, null);
		final JDFResourceAudit ra = n.cloneResourceToModify(rli);
		// messy comment insertion to test getNewOldLink with Comments
		ra.insertBefore(ElementName.COMMENT, ra.getOldLink(), null);
		ra.appendComment();
		assertTrue("Comment after NewLink", ra.getComment(0) == ra.getNewLink().getNextSiblingElement());
		assertTrue("Comment before OldLink", ra.getComment(0).getNextSiblingElement() == ra.getOldLink());
		assertTrue("Comment after OldLink", ra.getComment(1) == ra.getOldLink().getNextSiblingElement());

		assertTrue("resaudit children=2", ra.numChildElements("RunListLink", null) == 2);
		assertTrue("respool children=2", n.getResourcePool().numChildElements("RunList", null) == 2);
		assertTrue("reslink children=1", n.getResourceLinkPool().numChildElements("RunListLink", null) == 1);
		assertTrue("reslink audit1=pool", ((JDFResourceLink) n.getResourceLinkPool().getElement("RunListLink", null, 0)).getrRef().equals(ra.getNewLink().getrRef()));
		assertTrue("id names", (ra.getNewLink().getrRef() + "_old_001").equals(ra.getOldLink().getrRef()));
		assertTrue("old lock", (ra.getOldLink().getTarget()).getLocked());
		final JDFResourceAudit ra2 = n.getAuditPool().addResourceAudit("foo");
		ra2.addNewOldLink(true, rl, EnumUsage.Input);
		ra2.appendComment();
		ra2.addNewOldLink(false, rl, EnumUsage.Input).setDescriptiveName("foo");
		assertTrue("addnewlink", ra2.getOldLink().getDescriptiveName().equals("foo"));
		gd.write2File(sm_dirTestDataTemp + "testaudit.jdf", 2, false);
		assertTrue("audit valid with Link", ra2.isValid(EnumValidationLevel.Complete));
		ra2.copyElement(rl, null);
		assertFalse("audit invalid with resource", ra2.isValid(EnumValidationLevel.Complete));
	}

	/**
	 * test setPartStatus with null maps
	 * 
	 */
	public void testSetPartStatusNull()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setVersion(JDFElement.EnumVersion.Version_1_3);
		root.setPartStatus(((JDFAttributeMap) null), EnumNodeStatus.Waiting, null);
		JDFNodeInfo ni = root.getNodeInfo();
		assertNull(ni);
		assertNull(root.getStatusPool());
		assertEquals(root.getStatus(), EnumNodeStatus.Waiting);

		ni = root.getCreateNodeInfo();
		root.setPartStatus(((JDFAttributeMap) null), EnumNodeStatus.Completed, null);
		assertEquals(root.getStatus(), EnumNodeStatus.Completed);
		assertEquals("redundantly blasted in new nodestatus", ni.getNodeStatus(), EnumNodeStatus.Completed);

		root.setVersion(JDFElement.EnumVersion.Version_1_2);
		root.setPartStatus(((JDFAttributeMap) null), EnumNodeStatus.Completed, null);
		ni = root.getNodeInfo();
		assertEquals(root.getStatus(), EnumNodeStatus.Completed);
		assertNull(root.getStatusPool());
	}

	/**
	 *
	 *
	 */
	public void testSetPartStatusNotNull()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setVersion(JDFElement.EnumVersion.Version_1_3);
		final JDFAttributeMap pm = new JDFAttributeMap(EnumPartIDKey.SheetName.getName(), "s1");
		root.setPartStatus(pm, EnumNodeStatus.Waiting, null);
		JDFNodeInfo ni = (JDFNodeInfo) root.getNodeInfo().getPartition(pm, null);
		assertNotNull(ni);
		assertNull(root.getStatusPool());
		assertEquals(root.getStatus(), EnumNodeStatus.Part);
		assertEquals(root.getNodeInfo().getNodeStatus(), EnumNodeStatus.Waiting);
		assertEquals(ni.getNodeStatus(), EnumNodeStatus.Waiting);

		root.setVersion(JDFElement.EnumVersion.Version_1_2);
		root.setPartStatus(pm, EnumNodeStatus.Completed, null);
		ni = root.getNodeInfo();
		assertEquals(root.getStatus(), EnumNodeStatus.Pool);
		assertNotNull(root.getStatusPool());
		assertNotNull(root.getStatusPool().getPartStatus(pm));
		assertEquals(root.getStatusPool().getPartStatus(pm).getStatus(), EnumNodeStatus.Completed);
	}

	/**
	 *
	 *
	 */
	public void testSetPhase() throws Exception
	{
		for (int i = 0; i < 1; i++)
		{
			final JDFDoc doc = new JDFDoc(ElementName.JDF);
			final JDFNode root = doc.getJDFRoot();
			root.setVersion(JDFElement.EnumVersion.Version_1_3);
			root.setJobID("jID");
			root.setJobPartID("jpID");
			final JDFNodeInfo nodeInfo = root.getCreateNodeInfo();
			nodeInfo.setAgentName("myAgent");
			final StatusCounter su = new StatusCounter(root, null, null);
			su.setPhase(EnumNodeStatus.Waiting, "Queued", EnumDeviceStatus.Idle, null);
			JDFDoc docJMF = su.getDocJMFPhaseTime();
			JDFJMF jmf = docJMF.getJMFRoot();
			assertNotNull(jmf);
			assertEquals(jmf.numChildElements(ElementName.RESPONSE, null), 1);
			final JDFAuditPool ap = root.getAuditPool();
			JDFPhaseTime pt = ap.getLastPhase(null, null);
			assertEquals(pt.getStatus(), EnumNodeStatus.Waiting);
			assertEquals(root.getPartStatus(null), EnumNodeStatus.Waiting);
			final JDFAttributeMap map = new JDFAttributeMap("SheetName", "S1");
			final VJDFAttributeMap vMap = new VJDFAttributeMap();
			vMap.add(map);
			docJMF.write2File(sm_dirTestDataTemp + File.separator + "queued.jmf", 2, true);
			Thread.sleep(1000);
			su.setActiveNode(root, vMap, null);
			su.setPhase(EnumNodeStatus.Setup, "Setup", EnumDeviceStatus.Setup, null);

			docJMF = su.getDocJMFPhaseTime();
			pt = ap.getLastPhase(vMap, null);
			assertEquals(pt.getStatus(), EnumNodeStatus.Setup);
			assertEquals(root.getPartStatus(map), EnumNodeStatus.Setup);
			assertEquals(pt.getPartMapVector(), vMap);
			jmf = docJMF.getJMFRoot();
			assertNotNull(jmf);
			assertEquals(jmf.numChildElements(ElementName.RESPONSE, null), 1);
			docJMF.write2File(sm_dirTestDataTemp + File.separator + "setup.jmf", 2, true);
			Thread.sleep(1000);
			su.setPhase(EnumNodeStatus.InProgress, "Run", EnumDeviceStatus.Running, null);

			docJMF = su.getDocJMFPhaseTime();
			pt = (JDFPhaseTime) ap.getAudit(-1, EnumAuditType.PhaseTime, null, null);
			assertEquals(pt.getStatus(), EnumNodeStatus.InProgress);
			assertEquals(root.getPartStatus(map), EnumNodeStatus.InProgress);
			assertEquals(pt.getPartMapVector(), vMap);
			jmf = docJMF.getJMFRoot();
			assertNotNull(jmf);
			assertEquals(jmf.numChildElements(ElementName.RESPONSE, null), 2);
			docJMF.write2File(sm_dirTestDataTemp + "inprogress.jmf", 2, true);
			Thread.sleep(1000);
			su.setPhase(EnumNodeStatus.InProgress, "Run", EnumDeviceStatus.Running, null);

			docJMF = su.getDocJMFPhaseTime();
			pt = (JDFPhaseTime) ap.getAudit(-1, EnumAuditType.PhaseTime, null, null);
			assertEquals(pt.getStatus(), EnumNodeStatus.InProgress);
			assertEquals(root.getPartStatus(map), EnumNodeStatus.InProgress);
			assertEquals(pt.getPartMapVector(), vMap);
			jmf = docJMF.getJMFRoot();
			assertNotNull(jmf);
			assertEquals(jmf.numChildElements(ElementName.RESPONSE, null), 1);
			docJMF.write2File(sm_dirTestDataTemp + "inprogress2.jmf", 2, true);

			root.getCreateAncestorPool().setPartMapVector(vMap);
			su.setPhase(EnumNodeStatus.InProgress, "Run different", EnumDeviceStatus.Running, null);
			docJMF = su.getDocJMFPhaseTime();
			pt = (JDFPhaseTime) ap.getAudit(-1, EnumAuditType.PhaseTime, null, null);
			assertEquals(pt.getStatus(), EnumNodeStatus.InProgress);
			assertEquals(root.getPartStatus(map), EnumNodeStatus.InProgress);
			assertEquals(pt.getPartMapVector(), vMap);
			jmf = docJMF.getJMFRoot();
			assertNotNull(jmf);
			assertEquals(jmf.numChildElements(ElementName.RESPONSE, null), 2);

			jmf.convertResponses(null);
			docJMF.write2File(sm_dirTestDataTemp + "inprogress3.jmf", 2, true);

			su.setPhase(EnumNodeStatus.Completed, "Finito", EnumDeviceStatus.Idle, null);
			docJMF = su.getDocJMFPhaseTime();
			pt = (JDFPhaseTime) ap.getAudit(-1, EnumAuditType.PhaseTime, null, null);
			assertEquals(pt.getStatus(), EnumNodeStatus.InProgress);
			assertEquals(root.getPartStatus(map), EnumNodeStatus.Completed);
			assertEquals(pt.getPartMapVector(), vMap);
			jmf = docJMF.getJMFRoot();
			assertNotNull(jmf);
			assertEquals(jmf.numChildElements(ElementName.RESPONSE, null), 2);

			jmf.convertResponses(null);
			docJMF.write2File(sm_dirTestDataTemp + "inprogressCompleted.jmf", 2, true);
		}
	}

	/**
	 *
	 *
	 */
	public void testGetCreateNodeInfo()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setVersion(JDFElement.EnumVersion.Version_1_3);
		final JDFNodeInfo nodeInfo = root.getCreateNodeInfo();
		final JDFNodeInfo nodeInfo2 = root.getCreateNodeInfo();
		assertTrue(nodeInfo == nodeInfo2);

		root.setType(JDFNode.EnumType.Product.getName(), false);

		final JDFNode nodeVer3 = root.addJDFNode("Version 1.3");
		final JDFNode nodeVer1 = root.addJDFNode("Version 1.1");
		nodeVer1.setType("ProcessGroup", false);
		nodeVer3.setType("ProcessGroup", false);

		nodeVer3.setVersion(JDFElement.EnumVersion.Version_1_3);
		nodeVer1.setVersion(JDFElement.EnumVersion.Version_1_1);

		root.setVersion(EnumVersion.Version_1_3);
		// append some NodeInfos

		// try to append the other NodeInfo (only 1 is valid)
		nodeVer1.appendNodeInfo();
		nodeVer3.appendNodeInfo();
		final JDFNode subNodeVer1 = nodeVer1.addJDFNode("Folding");
		final JDFNode subNodeVer3 = nodeVer3.addJDFNode("Folding");

		boolean cat = false;
		try
		{
			nodeVer1.appendNodeInfo();
		}
		catch (final JDFException e)
		{
			cat = true;
			// nodeinfo has a cardinality of 1 in V1.1
			// if we try to append another NodeInfo
			//
		}
		assertTrue(cat);
		cat = false;
		try
		{
			nodeVer3.appendNodeInfo();
		}
		catch (final JDFException e)
		{
			// nodeinfo has a cardinality of 1 in V1.1
			// if we try to append another NodeInfo
			cat = true;
		}
		assertTrue(cat);
		cat = false;
		try
		{
			nodeVer3.appendNodeInfo();
		}
		catch (final JDFException e)
		{
			// nodeinfo has a cardinality of 1 in V1.1
			// if we try to append another NodeInfo
			cat = true;
		}

		assertTrue(cat);
		assertNotNull(nodeVer1.getNodeInfo());
		assertNotNull(nodeVer3.getNodeInfo());

		assertNull(subNodeVer1.getNodeInfo());
		assertNull(subNodeVer3.getNodeInfo());

		assertNotNull(subNodeVer1.getInheritedNodeInfo(null));
		assertNotNull(subNodeVer3.getInheritedNodeInfo(null));

		// remove them all
		nodeVer3.removeNodeInfo();
		assertNull(nodeVer3.getNodeInfo());
		nodeVer1.removeNodeInfo();
		assertNull(nodeVer1.getNodeInfo());

		doc.write2File(sm_dirTestDataTemp + "getCreateNodeInfo.xml", 0, true);
	}

	/**
	 * Method testGetExecutablePartitionsPreflightImport
	 * 
	 * @throws Exception
	 */

	public void testGetExecutablePartitionsPreflightImport()
	{
		final String strJDFName = "PreflightImport1.jdf";

		final JDFParser parser = new JDFParser();

		final JDFDoc jdfDoc = parser.parseFile(sm_dirTestData + strJDFName);

		final JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("Qua0.P", JDFConstants.EMPTYSTRING);

		final JDFResourceLinkPool linkPool = nodeProc.getResourceLinkPool();

		final VElement vRunListLinks = linkPool.getPoolChildren("RunListLink", null, null);
		if (vRunListLinks != null)
		{
			final JDFResourceLink link = (JDFResourceLink) vRunListLinks.get(0);
			final VJDFAttributeMap vamExec = nodeProc.getExecutablePartitions(link, JDFResource.EnumResStatus.Draft, true);
			assertTrue("Size of vamExec must be 2", vamExec.size() == 2);
			final JDFAttributeMap am0 = vamExec.elementAt(0);
			assertTrue("Size of vamExec[0] must be 1", am0.size() == 1);
			assertTrue(am0.containsKey("Run"));
			assertTrue(am0.containsValue("Chf06181149500001"));

			final JDFAttributeMap am1 = vamExec.elementAt(1);

			assertTrue("Size of vamExec[1] must be 1", am1.size() == 1);
			assertTrue(am1.containsKey("Run"));
			assertTrue(am1.containsValue("Chf06181154470000"));
		}
	}

	/**
	 * Method testGetExecutablePartitionsNormalizer
	 * 
	 * @throws Exception
	 */

	public void testGetExecutablePartitionsNormalizer()
	{
		final String strJDFName = "Normalizer.jdf";
		final JDFParser parser = new JDFParser();
		final JDFDoc jdfDoc = parser.parseFile(sm_dirTestData + strJDFName);
		final JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("Qua0.N", JDFConstants.EMPTYSTRING);
		final JDFResourceLinkPool linkPool = nodeProc.getResourceLinkPool();
		final VElement vRunListLinks = linkPool.getPoolChildren("RunListLink", null, null);
		final JDFResourceLink link = (JDFResourceLink) vRunListLinks.get(0);
		final VJDFAttributeMap vamExec = nodeProc.getExecutablePartitions(link, JDFResource.EnumResStatus.Draft, true);
		assertEquals("Size of vamExec must be 2", vamExec.size(), 2);
		final JDFAttributeMap am0 = vamExec.elementAt(0);
		assertEquals("Size of vamExec[0] must be 1", am0.size(), 1);
		assertTrue(am0.containsKey("Run"));
		assertTrue(am0.containsValue("Run93379_000255"));
		final JDFAttributeMap am1 = vamExec.elementAt(1);
		assertEquals("Size of vamExec[1] must be 0", am1.size(), 0);
	}

	/**
	 * Method testGetExecutablePartitionsNormalizer
	 * 
	 * @throws Exception
	 */

	public void testIsExecutableZones()
	{
		final String strJDFName = "ZoneTest.jdf";
		final JDFParser parser = new JDFParser();
		final JDFDoc jdfDoc = parser.parseFile(sm_dirTestData + strJDFName);
		final JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("PPIPressRoot.P", null);
		final JDFAttributeMap map = new JDFAttributeMap();
		map.put(EnumPartIDKey.SignatureName, "Signature1");
		map.put(EnumPartIDKey.SheetName, "SM 102 52x28");
		final VJDFAttributeMap vTest = new VJDFAttributeMap();
		vTest.add(map);
		final boolean b = nodeProc.isExecutable(map, true);
		assertTrue(b);

		VElement links = nodeProc.getResourceLinks(null, new JDFAttributeMap("Usage", "Input"), null);
		for (int i = 0; i < links.size(); i++)
		{
			final JDFResourceLink link = (JDFResourceLink) links.elementAt(i);
			final VJDFAttributeMap vamExec = nodeProc.getExecutablePartitions(link, JDFResource.EnumResStatus.Draft, true);
			assertNotNull(vamExec);
			assertTrue(vamExec.size() > 0);
		}

		links = nodeProc.getResourceLinks(null, new JDFAttributeMap("Usage", "Output"), null);
		for (int i = 0; i < links.size(); i++)
		{
			final JDFResourceLink link = (JDFResourceLink) links.elementAt(i);
			final VJDFAttributeMap vamExec = nodeProc.getExecutablePartitions(link, JDFResource.EnumResStatus.Unavailable, true);
			assertNotNull(vamExec);
			assertTrue(vamExec.size() > 0);
			assertTrue(vamExec.contains(map));
		}
		final JDFSpawn spawn = new JDFSpawn(nodeProc);
		spawn.vSpawnParts = vTest;
		spawn.vRWResources_in = new VString();
		spawn.vRWResources_in.add("Output");
		final JDFNode spawnedNode = spawn.spawn();
		assertNotNull(spawnedNode);
		assertEquals(spawnedNode.getAncestorPool().getPartMapVector(), vTest);

	}

	// ///////////////////////////////////////////////////////////////////////

	public void testNullPointerException()
	{
		final List LcleanUpMerge = JDFNode.EnumCleanUpMerge.getEnumList();
		final List LamountMerge = JDFResource.EnumAmountMerge.getEnumList();

		for (int i = 0; i < LcleanUpMerge.size(); i++)
		{
			final EnumCleanUpMerge cleanUp = (EnumCleanUpMerge) LcleanUpMerge.get(i);

			for (int j = 0; j < LcleanUpMerge.size(); j++)
			{
				final EnumAmountMerge amountMerge = (EnumAmountMerge) LamountMerge.get(j);

				final String xmlFile1 = "km4444.jdf";
				final String xmlFile2 = "Link33458670_000214km4444Qua0.NSp33486069_000371_37_out.jdf";
				final String outFile = "km4444_merged.xml";

				final JDFParser p = new JDFParser();
				final JDFDoc m_jdfDoc = p.parseFile(sm_dirTestData + xmlFile1);
				m_jdfDoc.getCreateXMLDocUserData();
				final JDFParser p2 = new JDFParser();
				final JDFDoc m_jdfDoc2 = p2.parseFile(sm_dirTestData + xmlFile2);
				m_jdfDoc2.getCreateXMLDocUserData();

				final JDFNode root = (JDFNode) m_jdfDoc.getRoot();
				assertNotNull(root);
				final JDFNode root2 = (JDFNode) m_jdfDoc2.getRoot();
				assertNotNull(root2);
				if (root == null)
				{
					return; // soothe findbugs ;)
				}
				new JDFMerge(root).mergeJDF(root2, sm_dirTestData + xmlFile2, cleanUp, amountMerge);

				m_jdfDoc.write2File(sm_dirTestDataTemp + outFile + i + j, 2, true);
			}
		}
	}

	public void testNodeIdentifier()
	{
		final NodeIdentifier ni = new NodeIdentifier();
		final NodeIdentifier ni2 = new NodeIdentifier("", "", null);
		assertEquals(ni, ni2);
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setJobID("j1");
		n.setJobPartID("p1");
		ni.setTo(n);
		assertNotSame(ni, ni2);
		ni2.setTo(n);
		assertEquals(ni, ni2);
		n.appendAncestorPool().appendPart().setPartMap(new JDFAttributeMap("a", "b"));
		ni.setTo(n);
		assertNotSame(ni, ni2);
		ni2.setTo(n);
		assertEquals(ni, ni2);
	}

	public void testNodeIdentifierParts()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFResource c = n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		final VJDFAttributeMap v = new VJDFAttributeMap();

		for (int i = 0; i < 3; i++)
		{
			final JDFAttributeMap m = new JDFAttributeMap();
			m.put("SignatureName", "sig1");
			m.put("SheetName", "S" + i);
			v.add(m);
			c.getCreatePartition(m, new VString("SignatureName SheetName", null));
		}
		final NodeIdentifier ni = new NodeIdentifier(n);
		final NodeIdentifier ni2 = new NodeIdentifier(n.getJobID(true), n.getJobPartID(true), v);
		assertEquals(ni, ni2);
		n.setPartStatus(new JDFAttributeMap("SignatureName", "Sig1"), EnumNodeStatus.InProgress, null);
		ni.setTo(n);
		assertNotSame(ni, ni2);

	}

	public void testNodeIdentifierMatches()
	{
		final NodeIdentifier ni = new NodeIdentifier();
		final NodeIdentifier ni2 = new NodeIdentifier("", "", null);
		assertEquals(ni, ni2);
		assertTrue(ni.matches(ni2));
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setJobID("j1");
		n.setJobPartID("p1");
		ni.setTo(n);
		assertTrue(ni.matches(ni2));
		assertTrue("ok if jobID matches", ni.matches("j1"));
		assertFalse(ni.matches("p1"));
		final JDFAncestorPool aPool = n.appendAncestorPool();
		aPool.appendPart().setPartMap(new JDFAttributeMap("a", "b"));
		ni.setTo(n);
		assertTrue(ni.matches(ni2));
		ni2.setTo(n);
		assertTrue(ni.matches(ni2));
		aPool.appendPart().setPartMap(new JDFAttributeMap("a", "c"));
		ni.setTo(n);
		assertTrue(ni.matches(ni2));

	}

	// ////////////////////////////////////////////

	public void testInit()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		assertNotNull(node.getStatus());
		assertFalse(node.getID().equals(""));
		node.init();
		node.init();
		final JDFAuditPool ap = node.getAuditPool();
		assertNotNull(ap);
		assertEquals(ap.numChildElements(ElementName.CREATED, null), 1);
	}

	/**
	 * 
	 */
	public void testInitDefaultVersion()
	{

		JDFDoc doc = new JDFDoc(ElementName.JDF);
		JDFNode node = doc.getJDFRoot();
		assertEquals(node.getVersion(true), JDFElement.getDefaultJDFVersion());
		assertEquals(node.getMaxVersion(true), JDFElement.getDefaultJDFVersion());

		JDFElement.setDefaultJDFVersion(EnumVersion.Version_1_1);
		doc = new JDFDoc(ElementName.JDF);
		node = doc.getJDFRoot();
		assertEquals(node.getVersion(true), JDFElement.getDefaultJDFVersion());
		assertEquals(node.getMaxVersion(true), JDFElement.getDefaultJDFVersion());

	}

	// ////////////////////////////////////////////

	/**
	 * 
	 */
	public void testIsValid()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		node.setType(EnumType.ProcessGroup);
		assertTrue(node.isValid(EnumValidationLevel.Complete));
		node.removeAttribute(AttributeName.JOBPARTID, null);
		assertTrue("isvalid does not require jpid", node.isValid(EnumValidationLevel.Complete));
	}

	// ////////////////////////////////////////////

	public void testIsValidCombined()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		node.setType(EnumType.Combined);
		assertFalse("need typed for combined", node.isValid(EnumValidationLevel.Complete));
	}

	public void testIsGroupNode()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		node.setType(EnumType.Product);
		assertTrue(node.isGroupNode());
		node.setType(EnumType.ProcessGroup);
		assertTrue(node.isGroupNode());
		node.setTypes(new VString("foo", " "));
		assertFalse(node.isGroupNode());
		node.setType(EnumType.Combined);
		assertFalse(node.isGroupNode());
		node.setType(EnumType.ConventionalPrinting);
		assertFalse(node.isGroupNode());
	}

	public void testIsTypesNode()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		node.setType(EnumType.Product);
		assertFalse(node.isTypesNode());
		node.setType(EnumType.Combined);
		assertTrue(node.isTypesNode());
		node.setType(EnumType.ConventionalPrinting);
		assertFalse(node.isTypesNode());
		node.setType(EnumType.ProcessGroup);
		assertTrue(node.isTypesNode());
		node.addJDFNode("foo");
		assertTrue(node.isTypesNode());

	}

	// ////////////////////////////////////////////

	public void testIsExecutable()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		node.setType("ConventionalPrinting", true);
		node.setVersion(JDFElement.EnumVersion.Version_1_3);
		node.setStatus(EnumNodeStatus.Ready);

		assertFalse("no links, no execute", node.isExecutable(null, true));

		// simple non-partitioned case
		final JDFNodeInfo n = node.appendNodeInfo();
		assertTrue("ni resource", n.hasAttribute(AttributeName.CLASS));
		final JDFConventionalPrintingParams convPrintParams = (JDFConventionalPrintingParams) node.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, EnumProcessUsage.AnyInput, null);
		convPrintParams.setResStatus(EnumResStatus.Available, true);
		final JDFComponent outComp = (JDFComponent) node.appendMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyOutput, null);
		outComp.setResStatus(EnumResStatus.Unavailable, true);
		JDFExposedMedia xm = (JDFExposedMedia) node.appendMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, null);
		xm.setResStatus(EnumResStatus.Unavailable, true);
		final JDFMedia media = (JDFMedia) node.appendMatchingResource(ElementName.MEDIA, EnumProcessUsage.AnyInput, null);
		media.setResStatus(EnumResStatus.Available, true);
		boolean exec = node.isExecutable(null, false);
		assertFalse("not exec", exec);
		xm.setResStatus(EnumResStatus.Available, true);
		exec = node.isExecutable(null, false);
		assertTrue("exec", exec);
		node.setStatus(EnumNodeStatus.Completed);
		exec = node.isExecutable(null, false);
		assertFalse("exec", exec);
		node.setStatus(EnumNodeStatus.Waiting);
		xm.setResStatus(EnumResStatus.Unavailable, true);
		final JDFResourceLink rl = node.getLink(xm, null);
		exec = node.isExecutable(null, false);
		assertFalse("exec", exec);
		rl.setDraftOK(true);
		exec = node.isExecutable(null, false);
		assertFalse("exec", exec);

		xm.setResStatus(EnumResStatus.Draft, true);
		exec = node.isExecutable(null, false);
		assertTrue("exec", exec);
		xm.setResStatus(EnumResStatus.Available, true);

		// now a partition
		convPrintParams.setPartUsage(EnumPartUsage.Implicit);
		media.setPartUsage(EnumPartUsage.Implicit);
		xm = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.SignatureName, "sig1");
		xm.setResStatus(EnumResStatus.Unavailable, true);
		exec = node.isExecutable(null, true);
		assertFalse("part not exec", exec);
		xm.setResStatus(EnumResStatus.Available, true);
		exec = node.isExecutable(null, true);
		assertTrue("part exec", exec);
		final JDFAttributeMap partMap = new JDFAttributeMap("SignatureName", "sig1");
		node.setPartStatus(partMap, EnumNodeStatus.Waiting, null);

		outComp.addPartition(EnumPartIDKey.SignatureName, "sig1");
		exec = node.isExecutable(partMap, false);
		assertTrue("part exec", exec);

		node.setPartStatus(((JDFAttributeMap) null), EnumNodeStatus.Completed, null);

		// the root is set to completed --> must fail
		exec = node.isExecutable(null, false);
		assertFalse("part exec", exec);

		// now try a non existing partition - should fail
		partMap.put("SignatureName", "sig2");
		exec = node.isExecutable(partMap, false);

		assertFalse("part exec", exec);

	}

	// ///////////////////////////////////////////////////

	public void testGetInheritedNodeInfo()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		final JDFNodeInfo n = node.appendNodeInfo();
		try
		{
			node.appendNodeInfo();
			fail("one ni is enough");
		}
		catch (final JDFException ex)
		{
			// nop
		}
		node.setType("ProcessGroup", true);
		final JDFNode node2 = node.addProcessGroup(null);
		final JDFNode node3 = node2.addJDFNode(JDFNode.EnumType.CaseMaking);
		final JDFAncestor an = node.appendAncestorPool().appendAncestor();
		final JDFCustomerInfo ciAN = an.appendCustomerInfo();
		final JDFNodeInfo niAN = an.appendNodeInfo();
		final JDFJMF jmf = niAN.appendJMF();
		final VString vs = new VString();
		vs.add("ICS_Foo");
		jmf.setICSVersions(vs);

		assertNull(node2.getNodeInfo());
		assertNull(node2.getInheritedNodeInfo("MISDetails"));
		assertNull(node2.getInheritedNodeInfo("JMF/@DeviceID"));
		assertEquals(node2.getInheritedNodeInfo("JMF/@ICSVersions"), niAN);
		assertEquals(node.getInheritedNodeInfo(null), n);
		assertEquals(node2.getInheritedNodeInfo(null), n);
		assertEquals(node3.getInheritedNodeInfo(null), n);
		assertEquals(node3.getInheritedCustomerInfo(null), ciAN);
		final JDFNodeInfo ni3 = node3.appendNodeInfo();
		final JDFNodeInfo sigNI = (JDFNodeInfo) ni3.addPartition(EnumPartIDKey.SignatureName, "Sig1");
		sigNI.setStart(new JDFDate());
		final JDFNodeInfo niPart = (JDFNodeInfo) sigNI.addPartition(EnumPartIDKey.SheetName, "S1");
		node3.getLink(ni3, null).setPartMap(niPart.getPartMap());
		niPart.setEnd(new JDFDate());
		assertEquals(node3.getInheritedNodeInfo(null), niPart);
		assertEquals(node3.getInheritedNodeInfo("@End"), niPart);
		assertEquals(node3.getInheritedNodeInfo("@Start"), niPart);
		assertNull(node3.getInheritedNodeInfo("@FooBar"));
		assertEquals(node3.getInheritedNodeInfo("JMF/@ICSVersions"), niAN);

	}

	// ////////////////////////////////////////////////////////////////////

	public void testCreateNodeInfo()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		final JDFNodeInfo n = node.appendNodeInfo();
		try
		{
			node.appendNodeInfo();
			fail("one ni is enough");
		}
		catch (final JDFException ex)
		{
			// nop
		}
		// System.out.println(n.version());
		assertTrue("nodeinfo is resource", n.getResourceClass() == EnumResourceClass.Parameter);
		doc.write2File(sm_dirTestDataTemp + "createNodeInfoTest.xml", 0, true);
		final JDFCustomerInfo myCustInfo = node.getCreateCustomerInfo();
		final JDFContact myContact = myCustInfo.appendContact();
		assertTrue("contact is res", myContact.getResourceClass() == EnumResourceClass.Parameter);
		assertNotNull(node.getNodeInfo());
		node.removeNodeInfo();
		assertNull(node.getNodeInfo());

	}

	// /////////////////////////////////////////////////////////////

	public void testGetvJDFNode()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFNode n1 = n.addProcessGroup(null);
		final JDFNode n2 = n.addProcessGroup(null);
		final JDFNode n11 = n1.addProcessGroup(null);
		assertEquals(n.getvJDFNode(null, null, true).size(), 2);
		assertEquals(n1.getvJDFNode(null, null, true).size(), 1);
		assertEquals(n2.getvJDFNode(null, null, false).size(), 1);
		assertEquals(n1.getvJDFNode(null, null, false).size(), 2);
		assertEquals(n.getvJDFNode(null, null, true).elementAt(0), n1);
		assertEquals(n.getvJDFNode(null, null, false).elementAt(3), n);
		assertEquals(n1.getvJDFNode(null, null, true).elementAt(0), n11);
		assertEquals(n1.getvJDFNode(null, null, false).elementAt(1), n1);

	}

	// /////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	public void testGetPartStatusNull()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setPartStatus(((JDFAttributeMap) null), EnumNodeStatus.Completed, null);
		assertEquals(n.getPartStatus(null), EnumNodeStatus.Completed);
		final JDFNodeInfo nodeInfo = n.appendNodeInfo();
		nodeInfo.setPartUsage(EnumPartUsage.Implicit);
		nodeInfo.setNodeStatus(EnumNodeStatus.Cleanup);
		n.setStatus(EnumNodeStatus.Part);
		assertEquals(n.getPartStatus(null), EnumNodeStatus.Cleanup);
		assertEquals(n.getPartStatus(new JDFAttributeMap("Run", "r1")), EnumNodeStatus.Cleanup);
		n.setStatus(EnumNodeStatus.Setup);
		assertEquals(n.getPartStatus(null), EnumNodeStatus.Setup);
		assertEquals(n.getPartStatus(new JDFAttributeMap("Run", "r1")), EnumNodeStatus.Setup);
	}

	// //////////////////////////////////////////////////////////////////////////
	// ///////////

	/**
	 * 
	 */
	public void testGetPartStatusImplicit()
	{
		final JDFNode createJDF = new JDFDoc("JDF").getJDFRoot();
		createJDF.getCreateResourcePool();
		final JDFNodeInfo createNodeInfo = createJDF.getCreateNodeInfo();
		createNodeInfo.setNodeStatus(EnumNodeStatus.Waiting);
		createJDF.setStatus(EnumNodeStatus.Part);
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put("Run", "1");
		final VString vPartKeys = null;
		final JDFNodeInfo createPartition = (JDFNodeInfo) createNodeInfo.getCreatePartition(partMap, vPartKeys);
		createPartition.setNodeStatus(EnumNodeStatus.Completed);
		partMap.put("Run", "2");
		final EnumNodeStatus partStatus = createJDF.getPartStatus(partMap);
		assertEquals("The implicit leaf defaults to root", partStatus, EnumNodeStatus.Waiting);
	}

	// /////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	public void testGetPartStatusPerformance()
	{
		final JDFNode node = new JDFDoc("JDF").getJDFRoot();
		final JDFNodeInfo ni = node.appendNodeInfo();
		final JDFResourceLink rl = node.getLink(ni, null);
		final VJDFAttributeMap vParts = new VJDFAttributeMap();
		final long t = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++)
		{
			final JDFAttributeMap pm = new JDFAttributeMap("SheetName", "s" + i);
			vParts.add(pm);
			node.setPartStatus(pm, EnumNodeStatus.Completed, null);
		}
		rl.setPartMapVector(vParts);
		for (int i = 0; i < 1000; i++)
		{
			assertEquals(node.getPartStatus(vParts.elementAt(i)), EnumNodeStatus.Completed);
		}
		assertTrue("too slow may laptop takes roughly 2.5 seconds", System.currentTimeMillis() - t < 25000);
	}

	// /////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	public void testGetVectorPartStatus()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final VJDFAttributeMap v = new VJDFAttributeMap();
		for (int i = 0; i < 3; i++)
		{
			v.add(new JDFAttributeMap("SheetName", "s" + i));
		}
		n.setPartStatus(v, EnumNodeStatus.Cleanup, null);
		assertEquals(n.getVectorPartStatus(v), EnumNodeStatus.Cleanup);
		v.removeElementAt(2);
		assertEquals(n.getVectorPartStatus(v), EnumNodeStatus.Cleanup);
		n.setPartStatus(new JDFAttributeMap("SheetName", "s1"), EnumNodeStatus.Setup, null);
		assertNull(n.getVectorPartStatus(v));
	}

	// /////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	public void testGetVectorPartStatusDetails()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final VJDFAttributeMap v = new VJDFAttributeMap();
		for (int i = 0; i < 3; i++)
		{
			v.add(new JDFAttributeMap("SheetName", "s" + i));
		}
		n.setPartStatus(v, EnumNodeStatus.Cleanup, "dummy");
		assertEquals(n.getVectorPartStatusDetails(v), "dummy");
		v.removeElementAt(2);
		assertEquals(n.getVectorPartStatusDetails(v), "dummy");
		n.setPartStatus(new JDFAttributeMap("SheetName", "s1"), EnumNodeStatus.Cleanup, "foobar");
		assertNull(n.getVectorPartStatusDetails(v));
	}

	/**
	 * 
	 */
	public void testGetPartStatus()
	{
		final JDFDoc doc = JDFTestCaseBase.creatXMDoc();
		final JDFNode node = doc.getJDFRoot();
		final JDFNodeInfo ni = node.getNodeInfo();
		assertTrue("nodeinfo is resource", ni.getResourceClass() == EnumResourceClass.Parameter);
		node.setPartStatus(((JDFAttributeMap) null), EnumNodeStatus.Waiting, null);
		assertTrue("ni root waiting", node.getPartStatus(null) == EnumNodeStatus.Waiting);
		final JDFAttributeMap m = new JDFAttributeMap("SignatureName", "Sig1");
		node.setPartStatus(m, EnumNodeStatus.Completed, null);
		assertTrue("ni sig1 completed", node.getPartStatus(m) == EnumNodeStatus.Completed);
		assertNull("ni root mixed", node.getPartStatus(null));
		final JDFAttributeMap m3 = new JDFAttributeMap("SignatureName", "Sig2");
		assertTrue("ni sig2 waiting", node.getPartStatus(m3) == EnumNodeStatus.Waiting);

		m.put("SheetName", "S1");
		m.put("Side", "Front");
		assertEquals(node.getPartStatus(m), EnumNodeStatus.Completed);
		node.setPartStatus(m, EnumNodeStatus.Waiting, null);
		assertTrue("ni sig1 waiting", node.getPartStatus(m) == EnumNodeStatus.Waiting);

		final JDFAttributeMap m2 = new JDFAttributeMap("SignatureName", "Sig1");
		assertNull("ni sig1 mixed", node.getPartStatus(m2));

		final JDFAttributeMap m4 = new JDFAttributeMap("SignatureName", "Sig3");
		m4.put("SheetName", "S1");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m4);
		node.prepareNodeInfo(v);
		assertTrue("ni sig3 waiting", node.getPartStatus(m4) == EnumNodeStatus.Waiting);
		assertNotNull("explicit m4", ni.getPartition(m4, EnumPartUsage.Explicit));

		final JDFAttributeMap m5 = new JDFAttributeMap("Side", "Back");
		assertNull("ni side back  mixed", node.getPartStatus(m5));
	}

	// ////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public void testGenericResources()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		final JDFDevice dev = (JDFDevice) n.appendMatchingResource(ElementName.DEVICE, EnumProcessUsage.AnyInput, null);
		dev.setDeviceID("mydev");
		assertTrue("valid device", dev.isValid(EnumValidationLevel.Complete));
		final JDFModule m = dev.appendModule();
		m.setModuleID("Foo");
		final JDFEmployee emp = (JDFEmployee) n.appendMatchingResource(ElementName.EMPLOYEE, EnumProcessUsage.AnyInput, null);
		emp.setPersonalID("emp1");
		assertTrue("valid module", m.isValid(EnumValidationLevel.Complete));
		n.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, EnumProcessUsage.AnyInput, null);
		n.appendMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyOutput, null);
		n.appendMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.Plate, null);

		assertTrue("valid node", n.isValid(EnumValidationLevel.Incomplete));
	}

	// ////////////////////////////////////////////////////////

	public void testProductValidation()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType("Product", true);
		final JDFDevice dev = (JDFDevice) n.appendMatchingResource(ElementName.DEVICE, EnumProcessUsage.AnyInput, null);
		dev.setDeviceID("mydev");
		assertTrue("valid device", dev.isValid(EnumValidationLevel.Complete));
		final JDFModule m = dev.appendModule();
		m.setModuleID("Foo");
		final JDFEmployee emp = (JDFEmployee) n.appendMatchingResource(ElementName.EMPLOYEE, EnumProcessUsage.AnyInput, null);
		emp.setPersonalID("emp1");
		assertTrue("valid module", m.isValid(EnumValidationLevel.Complete));
	}

	// ////////////////////////////////////////////////////////

	public void testGrayBox()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType("ProcessGroup", true);
		VString v = new VString();
		v.add("Interpreting");
		v.add("Rendering");
		n.setTypes(v);
		v = n.getInsertLinkVector(9999);
		assertTrue("interpretingParams", v.contains("InterpretingParamsLink:AnyInput"));
	}

	public void testAppendMatchingResourceProduct()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setStatus(EnumNodeStatus.Ready);
		n.setType("Product", true);
		JDFComponent co = (JDFComponent) n.appendMatchingResource("Component", EnumProcessUsage.Cover, null);
		final Vector<EnumComponentType> vType = new Vector<EnumComponentType>();
		vType.add(EnumComponentType.FinalProduct);
		vType.add(EnumComponentType.Block);
		co.setComponentType(vType);
		co = (JDFComponent) n.appendMatchingResource("Component", EnumProcessUsage.Cover, null);
		co.setComponentType(vType);
		co = (JDFComponent) n.appendMatchingResource("Component", EnumProcessUsage.AnyOutput, null);
		co.setComponentType(vType);
		assertTrue(n.isValid(EnumValidationLevel.Complete));

	}

	// /////////////////////////////////////////////////////////////////////////

	public void testAppendMatchingResourcePrivate()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setCombined(new VString("ConventionalPrinting fnarf ConventionalPrinting", null));
		JDFResource r = n.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, null);
		JDFResourceLink rl = n.getLink(r, null);
		assertNotNull("rl 1", rl);
		r = n.appendMatchingResource("FnarfParams", null, null);
		rl = n.getLink(r, null);
		assertNotNull("rl fnarf", rl);
		rl = n.getMatchingLink("FnarfParams", null, 0);
		assertNotNull("rl fnarf", rl);
		assertEquals(n.numMatchingLinks("FnarfParams", true, null), 1);
	}

	// /////////////////////////////////////////////////////////////////////////

	public void testAppendMatchingResourceDefinition()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ResourceDefinition);
		final JDFResource r = n.appendMatchingResource("foo", null, null);
		assertNotNull(r);
		assertEquals(r.getNodeName(), "foo");
	}

	// ////////////////////////////////////////////////////////////////////////

	public void testGetResource()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ResourceDefinition);
		JDFResource r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, 0);
		assertNull(r);
		r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, -1);
		assertNull(r);
		final JDFResource rAdd = n.addResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input);
		assertNotNull(rAdd);
		r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, 0);
		assertEquals(r, rAdd);
		r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, -1);
		assertEquals(r, rAdd);
		final JDFResource rAdd2 = n.addResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input);
		assertNotNull(rAdd2);
		r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, -1);
		assertEquals(r, rAdd2);
		r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, null, -2);
		assertEquals(r, rAdd);
		final JDFResource out = n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		assertEquals("null is valid wildcard", out, n.getResource(null, EnumUsage.Output, null, 0));
	}

	// ////////////////////////////////////////////////////////////////////////

	public void testGetResourceProcessUsage()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ResourceDefinition);
		final JDFResource rAdd = n.addResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input);
		final JDFResource rAdd2 = n.addResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input);
		assertNotNull(rAdd2);
		final JDFResourceLink rl = n.getLink(rAdd, null);
		rl.setProcessUsage(EnumProcessUsage.Application);
		JDFResource r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, EnumProcessUsage.Application, -1);
		assertEquals(r, rAdd);
		r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, EnumProcessUsage.Application, 0);
		assertEquals(r, rAdd);
		r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, EnumProcessUsage.Application, 1);
		assertNull(r);
	}

	public void testGetCreateResource()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ResourceDefinition);
		final JDFResource r = n.getCreateResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, 0);
		assertNotNull(r);
		final JDFResource rAdd = n.getCreateResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, 0);
		assertNotNull(rAdd);
		assertEquals(r, rAdd);
		JDFResource rAdd2 = n.getCreateResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, 1);
		assertNotNull(rAdd2);
		assertNotSame(rAdd2, rAdd);
		rAdd2 = n.getCreateResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, -2);
		assertNotNull(rAdd2);
		assertEquals(rAdd2, rAdd);
	}

	// ////////////////////////////////////////////////////////////////////////

	public void testAppendMatchingResource()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setCombined(new VString("ConventionalPrinting ConventionalPrinting", null));
		JDFResource r = n.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, null);
		JDFResourceLink rl = n.getLink(r, null);
		assertNotNull("rl 1", rl);

		r = n.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, null);
		assertNotNull("r 2", r);
		rl = n.getLink(r, null);
		assertNotNull("rl 2", rl);
		assertEquals("rl usage", rl.getUsage(), EnumUsage.Input);

		try
		{
			r = n.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, null);
			fail("exception 3");
		}
		catch (final JDFException e)
		{
			// nop
		}

		r = n.appendMatchingResource(ElementName.COMPONENT, null, null);
		assertNotNull("comp 1", r);
		rl = n.getLink(r, null);
		assertNull("complink 1", rl);
		rl = n.linkMatchingResource(r, EnumProcessUsage.Input, null);
		assertNotNull("rl 2", rl);
		assertEquals("rl usage", rl.getUsage(), EnumUsage.Input);
		assertEquals(rl.getProcessUsage(), "Input");

		r = n.appendMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyOutput, null);
		assertNotNull("comp 2", r);
		rl = n.getLink(r, null);
		assertNotNull("complink 2", rl);
		assertEquals(rl.getProcessUsage(), "");

		n.setCombined(new VString("Collecting ConventionalPrinting", null));
		for (int cLoop = 0; cLoop < 5; cLoop++)
		{
			r = n.appendMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyInput, null);
			assertNotNull("comp loop", r);
			rl = n.getLink(r, null);
			assertNotNull("complink 2", rl);
			assertEquals(rl.getProcessUsage(), "");
		}
	}

	// /////////////////////////////////////////////////////////////

	public void testCheckSpawnedResources()
	{
		final String strJDFName = "000023_Test_PR3.0.jdf";
		final String strJDFPath = sm_dirTestData + strJDFName;
		final JDFParser parser = new JDFParser();
		final JDFDoc jdfDoc = parser.parseFile(strJDFPath);
		// jdfDoc.getCreateXMLDocUserData().setDirtyPolicy(EnumDirtyPolicy.None);
		final VJDFAttributeMap vamParts = new VJDFAttributeMap();
		final JDFAttributeMap amParts0 = new JDFAttributeMap();
		amParts0.put("Side", "Front");
		amParts0.put("SignatureName", "Sig002");
		amParts0.put("SheetName", "FB 002");
		vamParts.add(amParts0);

		final JDFAttributeMap amParts1 = new JDFAttributeMap();
		amParts1.put("Side", "Back");
		amParts1.put("SignatureName", "Sig002");
		amParts1.put("SheetName", "FB 002");
		vamParts.add(amParts1);

		final VString vsRWResourceIDs = new VString();
		vsRWResourceIDs.add("Link84847227_000309");
		vsRWResourceIDs.add("r85326439_027691");

		final JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("IPD0.I", JDFConstants.EMPTYSTRING);

		final Collection vSpawned = nodeProc.checkSpawnedResources(vsRWResourceIDs, vamParts);
		assertNull(vSpawned);
	}

	// ////////////////////////////////////////////////////////////

	/**
	 * @throws DataFormatException
	 */
	public void testGetNodeInfo() throws DataFormatException
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(JDFNode.EnumType.ProcessGroup);
		n.setTypes(new VString("a b c", null));
		final JDFNodeInfo n2 = n.appendNodeInfo();
		final JDFResourceLink rl = n.getLink(n2, null);
		rl.setCombinedProcessIndex(new JDFIntegerList("1 2"));
		final JDFAttributeMap map = new JDFAttributeMap("SheetName", "S1");
		n.setPartStatus(map, EnumNodeStatus.FailedTestRun, null);
		assertNotNull(n.getNodeInfo());
		assertNotSame(n2, n.getNodeInfo());
		assertEquals(n2, n.getNodeInfo(1));
		assertEquals(n2, n.getNodeInfo(2));
		assertNull(n.getNodeInfo(0));
		assertNull(n.getNodeInfo(3));

		assertEquals(n.getPartStatus(map), EnumNodeStatus.FailedTestRun);
		n.removeAttribute(AttributeName.TYPES);
		assertNotNull("invalid types attribute, but still retrieve ni with no cpi", n.getNodeInfo());
		assertNotSame("invalid types attribute, but...", n.getNodeInfo(), n2);
	}

	// ////////////////////////////////////////////////////////////
	public void testGetParentJDF()
	{
		{
			final JDFDoc d = new JDFDoc("JDF");
			final JDFNode n = d.getJDFRoot();
			assertNull(n.getParentJDF());
			final JDFNode n2 = (JDFNode) n.appendElement("JDF");
			assertEquals(n, n2.getParentJDF());
		}
		{
			final XMLDoc d = new XMLDoc("ns:foo", "ns");
			final KElement e = d.getRoot();
			final JDFNode n = (JDFNode) e.appendElement("JDF");
			assertNull(n.getParentJDF());
			final JDFNode n2 = (JDFNode) n.appendElement("JDF");
			assertEquals(n, n2.getParentJDF());
		}
	}

	// ////////////////////////////////////////////////////////////
	public void testGetEnumTypes()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType("fnarf", false);
		assertNull(root.getEnumTypes());

		root.setType("ProcessGroup", true);

		root.setTypes(new VString("InkZoneCalculation ConventionalPrinting", null));
		assertEquals(root.getEnumTypes().elementAt(0), EnumType.InkZoneCalculation);
		assertEquals(root.getEnumTypes().elementAt(1), EnumType.ConventionalPrinting);
		assertEquals(root.getEnumTypes().size(), 2);

		root.setTypes(new VString("InkZoneCalculation xyz:fnarf ConventionalPrinting", null));
		assertNull("contains extension", root.getEnumTypes());

	}

	// ////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	public void testGetAncestorElement()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		final JDFNodeInfo ni = root.appendAncestorPool().appendAncestor().appendNodeInfo();
		assertEquals(ni, root.getAncestorElement(ElementName.NODEINFO, null));
	}

	// ////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	public void testGetActivation()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.ProcessGroup);
		assertEquals(root.getActivation(true), EnumActivation.Active);
		assertNull(root.getActivation(false));
		final VString types = new VString();
		types.add("foo");
		types.add("bar");

		final JDFNode n2 = root.addCombined(types);
		assertEquals(n2.getActivation(true), EnumActivation.Active);
		assertNull(n2.getActivation(false));

		root.setActivation(EnumActivation.Inactive);
		assertEquals(root.getActivation(true), EnumActivation.Inactive);
		assertEquals(root.getActivation(false), EnumActivation.Inactive);
		assertEquals(n2.getActivation(true), EnumActivation.Inactive);
		assertNull(n2.getActivation(false));

		n2.setActivation(EnumActivation.Active);
		assertEquals(n2.getActivation(true), EnumActivation.Inactive);
		assertEquals(n2.getActivation(false), EnumActivation.Active);
	}

	// ////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public void testGetAllTypes()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType("fnarf", false);
		assertEquals(root.getAllTypes().stringAt(0), "fnarf");
		assertEquals(root.getAllTypes().size(), 1);

		root.setType("Product", false);
		assertEquals(root.getAllTypes().stringAt(0), "Product");
		assertEquals(root.getAllTypes().size(), 1);

		root.setType("ProcessGroup", false);
		assertNull(root.getAllTypes());

		final VString types = new VString();
		types.add("foo");
		types.add("bar");
		root.setTypes(types);

		assertEquals(root.getAllTypes(), types);
		root.appendElement("JDF").setAttribute("Type", "fooBar2");
		final VString types2 = new VString(types);
		types2.insertElementAt("fooBar2", 0);
		// assertEquals(root.getAllTypes(),types2);

		root.removeAttribute("Types");

		final JDFNode n2 = root.addCombined(types);
		n2.setTypes(types);

		assertEquals(types2, root.getAllTypes());
		assertEquals(types, n2.getAllTypes());

		root.addJDFNode("foobar");

		assertEquals(types, n2.getAllTypes());
		types2.add("foobar");
		assertEquals(types2, root.getAllTypes());

	}

	// ////////////////////////////////////////////////////////////

	public void testGetMatchingNodes()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setJobID("j1");
		n.setJobPartID("p1");
		n.setType(EnumType.Product);
		final JDFNode n2 = n.addProduct();
		n2.setJobPartID("p2");
		assertTrue(n.getvJDFNode(null, null, false).containsAll(n.getMatchingNodes(null)));
		assertTrue(n.getMatchingNodes(null).contains(n));
		assertTrue(n.getMatchingNodes(n.getIdentifier()).contains(n));
		assertTrue(n.getMatchingNodes(n2.getIdentifier()).contains(n2));
		assertFalse(n.getMatchingNodes(n2.getIdentifier()).contains(n));

	}

	// ////////////////////////////////////////////////////////////

	public void testGetMatchingResource()
	{
		final JDFParser pars = new JDFParser();
		final JDFDoc doc = pars.parseFile(sm_dirTestData + File.separator + "testMatchRes.jdf");
		final JDFNode root = doc.getJDFRoot();
		final Vector v = root.getvJDFNode("ProcessGroup", null, false);
		JDFNode ppnode = null;
		for (int i = 0; i < v.size(); i++)
		{
			final JDFNode p = (JDFNode) v.elementAt(i);
			if (p.getCategory().equals("ContentCreation"))
			{
				ppnode = p;
				break;
			}
		}
		assertNotNull(ppnode);

		if (ppnode != null)
		{
			assertTrue(ppnode.getTypes().contains(EnumType.LayoutElementProduction.getName()));
			JDFResource res = ppnode.getMatchingResource("RunList", JDFNode.EnumProcessUsage.AnyInput, null, 0);
			assertEquals(res.getNodeName(), ElementName.RUNLIST);
			res = ppnode.getMatchingResource("LayoutElementProductionParams", JDFNode.EnumProcessUsage.AnyInput, null, 0);
			assertEquals(res.getNodeName(), ElementName.LAYOUTELEMENTPRODUCTIONPARAMS);
		}
	}

	public void testGetJobPart()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.Product);
		root.setJobPartID("p0");
		final JDFNode p1 = root.addJDFNode(EnumType.Product);
		assertEquals(root.getJobPart("p0", null), root);
		assertEquals(root.getJobPart("p0.1", null), p1);
		final JDFNode p11 = p1.addJDFNode(EnumType.Product);
		assertEquals(root.getJobPart("p0.1.1", null), p11);
		assertEquals(p1.getJobPart("p0.1.1", null), p11);
	}

	public void testGetChildJDFNode()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.Product);
		root.setJobPartID("p0");
		root.setID("I1");
		final JDFNode p1 = root.addJDFNode(EnumType.Product);
		p1.setID("I2");
		assertEquals(root.getChildJDFNode("I1", false), root);
		assertEquals(root.getChildJDFNode("I2", false), p1);
		assertEquals(root.getChildJDFNode("I2", true), p1);
		final JDFNode p11 = p1.addJDFNode(EnumType.Product);
		p11.setID("I11");
		assertEquals(root.getChildJDFNode("I11", false), p11);
		assertNull(root.getChildJDFNode("I11", true));
		assertEquals(root.getChildJDFNode("I11", false), p11);
		assertEquals(p1.getChildJDFNode("I11", true), p11);
		assertEquals(p1.getChildJDFNode("I11", false), p11);
	}

	public void testGetCombinedProcessIndex()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.Combined);
		root.setTypes(new VString("Folding Cutting Folding Stitching", " "));
		assertEquals(root.getCombinedProcessIndex(EnumType.AdhesiveBinding, 0), -1);
		assertEquals(root.getCombinedProcessIndex(EnumType.Folding, 0), 0);
		assertEquals(root.getCombinedProcessIndex(EnumType.Folding, 1), 2);
		assertEquals(root.getCombinedProcessIndex(EnumType.Cutting, 1), 1);
		assertEquals(root.getCombinedProcessIndex(EnumType.Stitching, 1), 3);
	}

	public void testGetLink()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.ImageSetting);
		{
			final JDFResource r = root.addResource("foo:res", EnumResourceClass.Parameter, EnumUsage.Input, null, null, "www.foo.com", null);
			final JDFResourceLink rl = root.getLink(r, null);
			assertNotNull(rl);
		}
		{
			final JDFResource r = root.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);
			final JDFResourceLink rl = root.getLink(r, null);
			assertNotNull(rl);
		}
	}

	// ////////////////////////////////////////////////////////////
	public void testGetResourceLinks()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.ImageSetting);
		VElement v = root.getResourceLinks(null);
		assertNull(v);
		root.addResource("foo:res", EnumResourceClass.Parameter, EnumUsage.Input, null, null, "www.foo.com", null);
		v = root.getResourceLinks(null);
		assertEquals(v.size(), 1);
		assertEquals(root.getResourceLinkPool().getElement(null), v.elementAt(0));
	}

	// ////////////////////////////////////////////////////////////

	public void testUpdatePartStatus()
	{
		for (int loop = 0; loop < 2; loop++)
		{
			final JDFDoc doc = new JDFDoc("JDF");
			final JDFNode root = doc.getJDFRoot();
			root.setType(EnumType.ProcessGroup);
			final JDFNode[] nodes = new JDFNode[12];
			final JDFAttributeMap map1 = new JDFAttributeMap("Run", "r1");
			final JDFAttributeMap map2 = new JDFAttributeMap("Run", "r2");
			final JDFAttributeMap map3 = new JDFAttributeMap("Run", "r3");
			final JDFAttributeMap map21 = new JDFAttributeMap("Run", "r2");
			map21.put("RunSet", "s1");
			final JDFAttributeMap map22 = new JDFAttributeMap("Run", "r2");
			map22.put("RunSet", "s2");
			for (int i = 0; i < 4; i++)
			{
				final JDFNode interNode = nodes[3 * i] = root.addProcessGroup(null);
				for (int j = 0; j < 3; j++)
				{
					final JDFNode leafNode = nodes[3 * i + j] = interNode.addCombined(new VString("a b c", " "));
					leafNode.setPartStatus(map1, EnumNodeStatus.Completed, null);
					leafNode.setPartStatus(map2, EnumNodeStatus.Waiting, null);
					leafNode.setPartStatus(map3, EnumNodeStatus.Waiting, null);
					leafNode.setPartStatus(map21, EnumNodeStatus.InProgress, null);
					leafNode.setPartStatus(map22, EnumNodeStatus.Waiting, null);
				}
			}
			final VJDFAttributeMap vMap = loop == 0 ? null : new VJDFAttributeMap();
			if (loop > 0)
			{
				vMap.add(map1);
				vMap.add(map3);
			}
			root.updatePartStatus(vMap, true, true);
			assertEquals(root.getPartStatus(map1), EnumNodeStatus.Completed);
			assertNull(root.getPartStatus(null));
			if (loop == 0)
			{
				assertEquals(root.getPartStatus(map21), EnumNodeStatus.InProgress);
			}
			else
			{
				assertNotSame("only updated run=1", root.getPartStatus(map21), EnumNodeStatus.InProgress);
			}

		}
	}

	// ////////////////////////////////////////////////////////////

	public void testToGrayBox()
	{
		final JDFNode root = new JDFDoc("JDF").getJDFRoot();

		root.setType(EnumType.Combined);
		final VString vs = new VString("Cutting Folding Cutting", " ");
		root.setTypes(vs);
		root.toGrayBox(false);
		assertEquals(root.getTypes(), vs);
		assertEquals(root.getType(), "ProcessGroup");

		root.removeAttribute(AttributeName.TYPES);
		root.setType(EnumType.ConventionalPrinting);
		root.toGrayBox(false);
		assertEquals(root.getTypes(), new VString("ConventionalPrinting", null));
		assertEquals(root.getType(), "ProcessGroup");

		root.removeAttribute(AttributeName.TYPES);
		root.setType(EnumType.ConventionalPrinting);
		root.toGrayBox(true);
		assertNull(root.getTypes());
		assertEquals(root.getType(), "ProcessGroup");
		final JDFNode child = (JDFNode) root.getElement(ElementName.JDF, null, 0);
		assertNotNull(child);
		assertEquals(child.getEnumType(), EnumType.ConventionalPrinting);
	}

	// ////////////////////////////////////////////////////////////

	public void testStatusPartMapVector()
	{
		final JDFNode root = new JDFDoc("JDF").getJDFRoot();

		root.setType(EnumType.Combined);
		root.setTypes(new VString("Cutting Folding Cutting", " "));
		for (int i = 0; i < 1; i++)
		{
			final EnumVersion v = i == 0 ? EnumVersion.Version_1_1 : EnumVersion.Version_1_3;
			root.setVersion(v);
			final VJDFAttributeMap vMapIn = new VJDFAttributeMap();
			if (i == 1)
			{
				vMapIn.add(new JDFAttributeMap());
			}
			for (int j = 0; j < 3; j++)
			{
				final JDFAttributeMap map = new JDFAttributeMap("Run", "R" + j);
				root.setPartStatus(map, EnumNodeStatus.Completed, null);
				vMapIn.add(map);
			}
			final VJDFAttributeMap vMap = root.getStatusPartMapVector();
			assertEquals(vMapIn, vMap);
		}
	}

	// ////////////////////////////////////////////////////////////

	public void testGetLinksForType()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();

		root.setType(EnumType.Combined);
		root.setTypes(new VString("Cutting Folding Cutting", " "));

		final JDFResource r1 = root.addResource("CuttingParams", null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl1 = root.getLink(r1, null);
		rl1.setCombinedProcessIndex(new JDFIntegerList(0));

		final JDFResource r2 = root.addResource("FoldingParams", null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl2 = root.getLink(r2, null);
		rl2.setCombinedProcessIndex(new JDFIntegerList(1));

		final JDFResource r3 = root.addResource("CuttingParams", null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl3 = root.getLink(r3, null);
		rl3.setCombinedProcessIndex(new JDFIntegerList(2));

		final JDFResource r4 = root.addResource("Component", null, EnumUsage.Output, null, null, null, null);
		final JDFResourceLink rl4 = root.getLink(r4, null);

		VElement ve = root.getLinksForType(EnumType.Cutting, 0);
		assertEquals(ve.size(), 1);
		assertTrue(ve.contains(rl1));
		assertFalse(ve.contains(rl4));

		ve = root.getLinksForType(EnumType.Cutting, 1);
		assertEquals(ve.size(), 2);
		assertTrue(ve.contains(rl3));
		assertTrue(ve.contains(rl4));

		ve = root.getLinksForType(EnumType.Cutting, -1);
		assertEquals(ve.size(), 3);
		assertTrue(ve.contains(rl1));
		assertTrue(ve.contains(rl3));
		assertTrue(ve.contains(rl4));

		ve = root.getLinksForType(EnumType.Folding, 0);
		assertEquals(ve.size(), 1);
		assertTrue(ve.contains(rl2));
		assertFalse(ve.contains(rl4));

		ve = root.getLinksForCombinedProcessIndex(0);
		assertEquals(ve.size(), 1);
		assertTrue(ve.contains(rl1));
		assertFalse(ve.contains(rl4));

		ve = root.getLinksForCombinedProcessIndex(1);
		assertEquals(ve.size(), 1);
		assertTrue(ve.contains(rl2));
		assertFalse(ve.contains(rl4));

		// now check whether this works with no cpi
		rl4.removeAttribute(AttributeName.COMBINEDPROCESSINDEX);
		ve = root.getLinksForType(EnumType.Folding, 0);
		assertEquals(ve.size(), 2);
		assertTrue(ve.contains(rl2));
		assertTrue(ve.contains(rl4));

		ve = root.getLinksForCombinedProcessIndex(0);
		assertEquals(ve.size(), 2);
		assertTrue(ve.contains(rl1));
		assertTrue(ve.contains(rl4));

		ve = root.getLinksForCombinedProcessIndex(1);
		assertEquals(ve.size(), 2);
		assertTrue(ve.contains(rl2));
		assertTrue(ve.contains(rl4));

	}

	// ////////////////////////////////////////////////////////////

	public void testGetMatchingResourceStar()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType((EnumType.Combine));

		for (int i = 0; i < 5; i++)
		{
			final JDFResource res = root.appendMatchingResource("RunList", JDFNode.EnumProcessUsage.AnyInput, null);
			assertNotNull(res);
			assertEquals(res.getNodeName(), ElementName.RUNLIST);
			final JDFResource resa = root.getMatchingResource("RunList", JDFNode.EnumProcessUsage.AnyInput, null, i);
			assertEquals(res, resa);
			final JDFResourceLink rlb = root.getMatchingLink("RunList", JDFNode.EnumProcessUsage.AnyInput, i);
			assertEquals(rlb.getTarget(), res);

		}
	}

	// ////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////

	/**
	 * getMinID is no longer used in the library
	 */
	@SuppressWarnings("deprecation")
	public void testGetMinID()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.ConventionalPrinting);
		final int id = root.getMinID();
		assertTrue(id < 5 && id > 0);
		for (int i = 0; i < 1000; i++)
		{
			root.getAuditPool().addModified(null, null);
		}
		assertEquals(id + 1000, root.getMinID());
		root.setID("ida123456");
		root.setID("ida123456");
		assertEquals(123456, root.getMinID());
		root.setID("ida00000");
		assertEquals(id + 1000, root.getMinID());
	}

	// ////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public void testGetMissingLinks()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.ConventionalPrinting);
		VString vs = root.getMissingLinkVector(999);
		assertTrue(vs.contains(ElementName.CONVENTIONALPRINTINGPARAMS + "Link:AnyInput"));
		assertTrue(vs.contains(ElementName.COMPONENT + "Link:AnyOutput"));

		final VString vsc = new VString();
		vsc.add(EnumType.InkZoneCalculation);
		vsc.add(EnumType.ConventionalPrinting);
		root.setCombined(vsc);
		vs = root.getMissingLinkVector(999);
		assertTrue(vs.contains(ElementName.PREVIEW + "Link:AnyInput"));
		assertTrue(vs.contains(ElementName.CONVENTIONALPRINTINGPARAMS + "Link:AnyInput"));
		assertTrue(vs.contains(ElementName.COMPONENT + "Link:AnyOutput"));
	}

	// ////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public void testGetMissingLinksProduct()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.Product);
		VString vs = root.getMissingLinkVector(999);
		assertTrue(vs.contains(ElementName.COMPONENT + "Link:AnyOutput"));

		root.addJDFNode(EnumType.ProcessGroup);
		root.appendMatchingResource("Employee", EnumProcessUsage.AnyInput, null);
		vs = root.getMissingLinkVector(999);
		assertTrue("product with sub element still requires an output component", vs.contains(ElementName.COMPONENT + "Link:AnyOutput"));
	}

	// ////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public void testGetWorkStepID()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.ConventionalPrinting);
		root.setJobPartID("p1");
		assertEquals(root.getWorkStepID(null), "p1");
		final JDFAttributeMap attributeMap = new JDFAttributeMap("SheetName", "S1");
		assertEquals(root.getWorkStepID(attributeMap), "p1");
		root.setPartStatus(attributeMap, EnumNodeStatus.Cleanup, null);
		assertEquals(root.getWorkStepID(null), "p1");
		assertEquals(root.getWorkStepID(attributeMap), "p1");
		final JDFNodeInfo ni = root.getNodeInfo();
		final JDFNodeInfo nip = (JDFNodeInfo) ni.getPartition(attributeMap, null);
		nip.setWorkStepID("w2");
		assertEquals(root.getWorkStepID(null), "p1");
		assertEquals(root.getWorkStepID(attributeMap), "w2");
		ni.setWorkStepID("w1");
		assertEquals(root.getWorkStepID(null), "w1");
		assertEquals(root.getWorkStepID(attributeMap), "w2");
		nip.removeAttribute(AttributeName.WORKSTEPID);
		assertEquals(root.getWorkStepID(null), "w1");
		assertEquals(root.getWorkStepID(attributeMap), "w1");
	}

	// ////////////////////////////////////////////////////////////

	public void testGetUnknownLinks()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.ConventionalPrinting);
		VElement vs = root.getUnknownLinkVector(null, 999);
		assertNull(vs);

		root.addResource(ElementName.FOLDINGPARAMS, null, EnumUsage.Input, null, null, null, null);

		vs = root.getUnknownLinkVector(null, 999);
		assertTrue(vs.elementAt(0) instanceof JDFResourceLink);
		assertEquals(((JDFResourceLink) vs.elementAt(0)).getLocalName(), "FoldingParamsLink");

		root.addResource("foo:barRes", EnumResourceClass.Parameter, EnumUsage.Input, null, null, "www.foo.com", null);

		vs = root.getUnknownLinkVector(null, 999);
		assertEquals(vs.size(), 2);
		assertTrue(vs.elementAt(0) instanceof JDFResourceLink);
		assertEquals(((JDFResourceLink) vs.elementAt(0)).getLocalName(), "FoldingParamsLink");
		assertEquals(((JDFResourceLink) vs.elementAt(1)).getNodeName(), "foo:barResLink");

		final VString vsc = new VString();
		vsc.add(EnumType.InkZoneCalculation);
		vsc.add(EnumType.ConventionalPrinting);
		root.setCombined(vsc);
		vs = root.getUnknownLinkVector(null, 999);
		assertTrue(vs.elementAt(0) instanceof JDFResourceLink);
		assertEquals(((JDFResourceLink) vs.elementAt(0)).getLocalName(), "FoldingParamsLink");
	}

	// ////////////////////////////////////////////////////////////
	/**
	 * test for getting statusdetails
	 */
	public void testGetPartStatusDetails()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setPartStatus(((JDFAttributeMap) null), EnumNodeStatus.Completed, null);
		n.setStatusDetails("foobar");
		assertEquals(n.getPartStatusDetails(null), "foobar");
		final JDFNodeInfo nodeInfo = n.appendNodeInfo();
		nodeInfo.setPartUsage(EnumPartUsage.Implicit);
		nodeInfo.setNodeStatus(EnumNodeStatus.Cleanup);
		n.setStatus(EnumNodeStatus.Part);
		assertNull(n.getPartStatusDetails(null));
		nodeInfo.setNodeStatusDetails("niDetails");
		assertEquals(n.getPartStatusDetails(new JDFAttributeMap("Run", "r1")), "niDetails");
	}

	// ////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////

}