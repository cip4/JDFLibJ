/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode.CombinedProcessLinkHelper;
import org.cip4.jdflib.node.JDFNode.EnumActivation;
import org.cip4.jdflib.node.JDFNode.EnumCleanUpMerge;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAncestorPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumAmountMerge;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.devicecapability.JDFModule;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.CPUTimer;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFMerge;
import org.cip4.jdflib.util.JDFSpawn;
import org.cip4.jdflib.util.StatusCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class JDFNodeTest extends JDFTestCaseBase
{
	/**
	 *
	 *
	 */
	public void testgetLinkedResourceVector()
	{
		final JDFNode n = creatXMDoc().getJDFRoot();
		Assertions.assertNull(n.getLinkedResourceVector(EnumUsage.Output, ElementName.EXPOSEDMEDIA, null, true));
		Assertions.assertEquals(n.getLinkedResourceVector(EnumUsage.Input, ElementName.EXPOSEDMEDIA, null, true).size(), 8);
	}

	/**
	 * performance test
	 */
	@Test
	public void testBigSortChildren()
	{
		final String strJDFName = "000023_Test_PR3.0.jdf";
		final JDFParser p = new JDFParser();
		final JDFDoc d = p.parseFile(sm_dirTestData + strJDFName);
		final JDFNode n = d.getJDFRoot();
		n.sortChildren();
	}

	/**
	 * performance test
	 */
	@Test
	public void testGetPartIDKeys()
	{
		final JDFParser p = new JDFParser();
		final JDFDoc d = p.parseFile(sm_dirTestData + "evilparts.jdf");
		final JDFNode n = d.getJDFRoot();
		final CPUTimer ct = new CPUTimer(true);
		final VString v = n.getPartIDKeys(null);
		Assertions.assertNotNull(v);
		ct.stop();
		System.out.println(ct.toString());
	}

	// /////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testGetPredecessors()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFNode n2 = n.addJDFNode("ImageSetting");
		final JDFResource r = n2.addResource("ExposedMedia", null, EnumUsage.Output, null, n, null, null);
		final JDFNode n3 = n.addJDFNode("ConventionalPrinting");
		n3.linkResource(r, EnumUsage.Input, null);
		Assertions.assertTrue(n3.getPredecessors(true, true).contains(n2));
		Assertions.assertTrue(n3.getPredecessors(true, false).contains(n2));
		Assertions.assertTrue(n2.getPredecessors(false, true).contains(n3));
		Assertions.assertTrue(n2.getPredecessors(false, false).contains(n3));
		Assertions.assertFalse(n2.getPredecessors(false, false).contains(n2));
		Assertions.assertFalse(n2.getPredecessors(true, false).contains(n2));
		Assertions.assertFalse(n3.getPredecessors(false, false).contains(n3));
		Assertions.assertFalse(n3.getPredecessors(true, false).contains(n3));
		n3.linkResource(r, EnumUsage.Output, null);
		// used to dead loop here after in=out...
		n3.getPredecessors(false, true);
		n3.getPredecessors(true, true);
	}

	/**
	 *
	 */
	@Test
	public void testGetPredecessorsPartition()
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
		Assertions.assertEquals(nf1.getPredecessors(true, true).size(), 1);
		Assertions.assertEquals(nf1.getPredecessors(true, true).get(0), nc1);
		Assertions.assertEquals(nf2.getPredecessors(true, true).size(), 1);
		Assertions.assertEquals(nf2.getPredecessors(true, true).get(0), nc2);
		Assertions.assertEquals(nc1.getPredecessors(false, true).get(0), nf1);
		Assertions.assertEquals(nc2.getPredecessors(false, true).get(0), nf2);
	}

	/**
	 *
	 */
	@Test
	public void testGetRefPartition()
	{
		final JDFDoc jdfDoc = JDFDoc.parseFile(sm_dirTestData + "GetReferencedPartition.jdf");
		final JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("1008.C", null);
		final JDFResourceLinkPool linkPool = nodeProc.getResourceLinkPool();
		final JDFResourceLink link = linkPool.getPoolChild(0, "ExposedMediaLink", null, null);
		final VJDFAttributeMap vam = nodeProc.getExecutablePartitions(link, JDFResource.EnumResStatus.Draft, true);
		Assertions.assertEquals(vam.size(), 5);

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

	/**
	 *
	 */
	@Test
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
		Assertions.assertEquals(n.getChildWithAttribute("JDF", "ID", null, "n1", 0, true).getNextSiblingElement(null, null), n.getChildWithAttribute("JDF", "ID", null, "n2", 0, true), "reordered sub elements");

		Assertions.assertEquals(rp2.getFirstChildElement(), xm2);
		Assertions.assertEquals(xm2.getNextSiblingElement(), m21);
		Assertions.assertEquals(m21.getNextSiblingElement(), m22);

	}

	/**
	 *
	 */
	@Test
	public void testSetType()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		Assertions.assertTrue(n.setType(EnumType.ConventionalPrinting.getName(), true), "good Type");
		Assertions.assertEquals(n.getXSIType(), EnumType.ConventionalPrinting.getName(), "xsiType");
		try
		{
			n.setType("badTypeName", true);
			Assertions.fail("bad type");
		}
		catch (final JDFException e)
		{
			Assertions.assertNotNull(e, "bad type");
		}
		n.setType("foo:bar", false);
		Assertions.assertEquals(n.getType(), "foo:bar");
		Assertions.assertNull(n.getXSIType());
		final VString types = new VString("Interpreting Rendering", " ");
		n.setCombined(types);
		Assertions.assertEquals(n.getTypes(), types);
		n.setType(EnumType.ContactCopying);
		Assertions.assertNull(n.getAttribute("Types", null, null));
	}

	/**
	 *
	 */
	@Test
	public void testSetEnum()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		Assertions.assertTrue(n.setType(EnumType.ConventionalPrinting.getName(), true), "good Type");
		Assertions.assertEquals(n.getXSIType(), EnumType.ConventionalPrinting.getName(), "xsiType");
		try
		{
			n.setType("badTypeName", true);
			Assertions.fail("bad type");
		}
		catch (final JDFException e)
		{
			Assertions.assertNotNull(e, "bad type");
		}
		n.setType("foo:bar", false);
		Assertions.assertEquals(n.getType(), "foo:bar");
		Assertions.assertNull(n.getXSIType());
	}

	/**
	 *
	 */
	@Test
	public void testLinkResourceNS()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFResource rl = n.addResource("NS:RunList", EnumResourceClass.Parameter, null, null, null, "www.ns.com", null);
		Assertions.assertEquals("www.ns.com", rl.getNamespaceURI(), "res ns");
		final JDFResourceLink rll = n.linkResource(rl, EnumUsage.Input, null);
		Assertions.assertEquals("www.ns.com", rll.getNamespaceURI(), "res ns");
		Assertions.assertFalse(rll.hasAttribute(AttributeName.COMBINEDPROCESSINDEX));
	}

	/**
	 *
	 */
	@Test
	public void testEnsureLink()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFResource rl = n.addResource("RunList", null);
		final JDFResource r2 = n.addResource("RunList", null);
		final JDFResourceLink rl1 = n.ensureLink(rl, EnumUsage.Input, null);
		final JDFResourceLink rl2 = n.ensureLink(r2, EnumUsage.Input, null);
		Assertions.assertNotNull(rl1);
		Assertions.assertEquals(rl1, n.ensureLink(rl, EnumUsage.Input, null));
		Assertions.assertEquals(rl2, n.ensureLink(r2, EnumUsage.Input, null));
		Assertions.assertNotSame(rl1, rl2);

	}

	/**
	 *
	 */
	@Test
	public void testEnsureJobPartID()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		final String jpid = n.getNonEmpty(AttributeName.JOBPARTID);
		n.setType(EnumType.Product);
		Assertions.assertNotNull(jpid);
		Assertions.assertEquals(jpid, n.ensureJobPartID());
		final JDFNode n2 = n.addJDFNode("Foo");
		Assertions.assertTrue(n2.ensureJobPartID().startsWith(jpid));
	}

	/**
	 *
	 */
	@Test
	public void testEnsureLinkProcessUsage()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFResource rl = n.addResource("RunList", null);
		final JDFResourceLink rl1 = n.ensureLink(rl, EnumUsage.Input, null);
		final JDFResourceLink rl2 = n.ensureLink(rl, EnumUsage.Input, EnumProcessUsage.Marks);
		Assertions.assertEquals(rl1, rl2);
		final JDFResourceLink rl3 = n.ensureLink(rl, EnumUsage.Input, null);
		Assertions.assertEquals(rl1, rl3);
	}

	/**
	 *
	 */
	@Test
	public void testEnsureLinkRecursive()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Product);
		final JDFNode n2 = n.addJDFNode("ProcessGroup");
		final JDFResource r1 = n2.addResource("ExposedMedia", EnumUsage.Input);
		final JDFResource r2 = n2.addResource("Media", null);
		r1.addPartition(EnumPartIDKey.SheetName, "s1").refElement(r2);
		n.ensureLink(r1, EnumUsage.Input, null);
		Assertions.assertTrue(n.getResourcePool().getPoolChildren(null, null, null).contains(r2));

	}

	/**
	 * gracefully handle circular references
	 */
	@Test
	public void testEnsureLinkLoop()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Product);
		final JDFNode n2 = n.addJDFNode("ProcessGroup");
		final JDFResource r1 = n2.addResource("ExposedMedia", EnumUsage.Input);
		final JDFResource r2 = n2.addResource("Media", null);
		r1.refElement(r2);
		r2.refElement(r1);
		r2.refElement(r2);
		n.ensureLink(r1, EnumUsage.Input, null);
		Assertions.assertTrue(n.getResourcePool().getPoolChildren(null, null, null).contains(r2));

	}

	/**
	 * test whether combinedprocessIndex is automagically and correctly assigned
	 *
	 */
	@Test
	public void testInsertTypeInTypes()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Combined);
		n.insertTypeInTypes(EnumType.Combine, 999);
		Assertions.assertEquals(n.getAttribute("Types"), "Combine");
		n.insertTypeInTypes(EnumType.Imposition, 999);
		Assertions.assertEquals(n.getAttribute("Types"), "Combine Imposition");
		n.insertTypeInTypes(EnumType.Stripping, 1);
		Assertions.assertEquals(n.getAttribute("Types"), "Combine Stripping Imposition");
		n.insertTypeInTypes(EnumType.DigitalDelivery, 0);
		Assertions.assertEquals(n.getAttribute("Types"), "DigitalDelivery Combine Stripping Imposition");
		n.insertTypeInTypes(EnumType.Imposition, 99999);
		Assertions.assertEquals(n.getAttribute("Types"), "DigitalDelivery Combine Stripping Imposition Imposition");
		n.insertTypeInTypes(EnumType.DigitalPrinting, -1);
		Assertions.assertEquals(n.getAttribute("Types"), "DigitalDelivery Combine Stripping Imposition DigitalPrinting Imposition");

	}

	/**
	 * test whether combinedprocessIndex is automagically and correctly assigned
	 *
	 */
	@Test
	public void testLinkNamesCombined()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setCombined(new VString("Interpreting Trapping Rendering", " "));
		final VString e = n.linkNames();
		Assertions.assertTrue(e.contains(ElementName.CUSTOMERINFO));
		Assertions.assertTrue(e.contains(ElementName.RENDERINGPARAMS));
	}

	/**
	 * test whether combinedprocessIndex is automagically and correctly assigned
	 *
	 */
	@Test
	public void testLinkNamesProduct()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Product);
		final VString e = n.linkNames();
		Assertions.assertTrue(e.contains(ElementName.CUSTOMERINFO));
		Assertions.assertTrue(e.contains(ElementName.MEDIAINTENT));
		Assertions.assertTrue(e.contains(ElementName.COMPONENT));
	}

	/**
	 * test whether combinedprocessIndex is automagically and correctly assigned
	 *
	 */
	@Test
	public void testLinkResourceSimple()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Folding);
		final JDFResource foPa = n.addResource(ElementName.FOLDINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rlfoPa = n.getLink(foPa, null);
		Assertions.assertFalse(rlfoPa.hasAttribute(AttributeName.COMBINEDPROCESSINDEX));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testParseFile()
	{
		JDFNode n = JDFNode.parseFile(new File(sm_dirTestData + "job.jdf"));
		Assertions.assertNotNull(n);
		n = JDFNode.parseFile(sm_dirTestData + "job.jdf");
		Assertions.assertNotNull(n);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testParseFileNull()
	{
		JDFNode n = JDFNode.parseFile((File) null);
		Assertions.assertNull(n);
		n = JDFNode.parseFile((String) null);
		Assertions.assertNull(n);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testParseStreamNull()
	{
		final JDFNode n = JDFNode.parseStream(null);
		Assertions.assertNull(n);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testParseStream()
	{
		final JDFNode n = JDFNode.parseStream(FileUtil.getBufferedInputStream(new File(sm_dirTestData + "job.jdf")));
		Assertions.assertNotNull(n);
	}

	/**
	 * test whether combinedprocessIndex is automagically and correctly assigned
	 *
	 */
	@Test
	public void testLinkOutput()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFNode n1 = n.addJDFNode(EnumType.Interpreting);
		final JDFRunList rul1 = (JDFRunList) n1.addResource("RunList", EnumUsage.Output);
		final JDFNode n2 = n.addJDFNode(EnumType.Rendering);
		n2.linkOutputs(n1);
		Assertions.assertEquals(n2.getResource("RunList", EnumUsage.Input, 0), rul1);
		n2.linkOutputs(n1);
		Assertions.assertEquals(n2.getResource("RunList", EnumUsage.Input, 0), rul1);
		Assertions.assertNull(n2.getResource("RunList", null, 1));

	}

	/**
	 * test whether combinedprocessIndex is automagically and correctly assigned
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void testLinkResourceCombined() throws Exception
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Combined);
		VString v = new VString("Folding Cutting Foo:Bar Folding", " ");
		n.setTypes(v);
		final JDFResource foPa = n.addResource(ElementName.FOLDINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rlfoPa = n.getLink(foPa, null);
		Assertions.assertEquals(rlfoPa.getCombinedProcessIndex().toString(), "0 3", "folding is 0 and 2");

		final JDFResource cuPa = n.addResource(ElementName.CUTTINGPARAMS, null, null, null, null, null, null);
		final JDFResourceLink rlCuPa = n.linkResource(cuPa, EnumUsage.Input, null);
		Assertions.assertEquals(rlCuPa.getCombinedProcessIndex().toString(), "1", "cutting is 1");

		final JDFResource pePa = n.addResource(ElementName.PERFORATINGPARAMS, null, null, null, null, null, null);
		final JDFResourceLink rlPePa = n.linkResource(pePa, EnumUsage.Input, null);
		Assertions.assertNull(rlPePa.getCombinedProcessIndex(), "no perforating");

		n.insertTypeInTypes(EnumType.AdhesiveBinding, 999);
		Assertions.assertEquals(rlfoPa.getCombinedProcessIndex().toString(), "0 3", "folding is 0 and 2");
		Assertions.assertEquals(rlCuPa.getCombinedProcessIndex().toString(), "1", "cutting is 1");
		v = n.getTypes();
		Assertions.assertEquals(v.elementAt(4), EnumType.AdhesiveBinding.getName(), "appended one type");
		Assertions.assertEquals(v.size(), 5, "appended one type");

		n.insertTypeInTypes(EnumType.Bundling, -2);
		Assertions.assertEquals(rlfoPa.getCombinedProcessIndex().toString(), "0 4", "folding is 0 and 3");
		Assertions.assertEquals(rlCuPa.getCombinedProcessIndex().toString(), "1", "cutting is 1");
		v = n.getTypes();
		Assertions.assertEquals(v.elementAt(3), EnumType.Bundling.getName(), "appended one type");
		Assertions.assertEquals(v.size(), 6, "appended one type");

		n.insertTypeInTypes(EnumType.BoxPacking, 0);
		Assertions.assertEquals(rlfoPa.getCombinedProcessIndex().toString(), "1 5", "folding is 1 and 5");
		Assertions.assertEquals(rlCuPa.getCombinedProcessIndex().toString(), "2", "cutting is 2");
		v = n.getTypes();
		Assertions.assertEquals(v.elementAt(0), EnumType.BoxPacking.getName(), "appended one type");
		Assertions.assertEquals(v.elementAt(1), EnumType.Folding.getName(), "appended one type");
		Assertions.assertEquals(v.size(), 7, "appended one type");

		final JDFResource comp = n.addResource(ElementName.COMPONENT, null, null, null, null, null, null);
		final JDFResourceLink rlcomp = n.linkResource(comp, EnumUsage.Output, null);
		Assertions.assertEquals(rlcomp.getCombinedProcessIndex(), new JDFIntegerList("2 6"), "cpi output");

		final JDFResource compIn = n.addResource(ElementName.COMPONENT, null, null, null, null, null, null);
		final JDFResourceLink rlcompIn = n.linkResource(compIn, EnumUsage.Input, null);
		Assertions.assertEquals(rlcompIn.getCombinedProcessIndex(), new JDFIntegerList("0 4"), "cpi output");

		final JDFResource devIn = n.addResource(ElementName.DEVICE, null, null, null, null, null, null);
		final JDFResourceLink rlDevIn = n.linkResource(devIn, EnumUsage.Input, null);
		Assertions.assertNull(rlDevIn.getCombinedProcessIndex(), "dev input");

		final JDFResource niIn = n.addResource(ElementName.NODEINFO, null, null, null, null, null, null);
		final JDFResourceLink rlNiIn = n.linkResource(niIn, EnumUsage.Input, null);
		Assertions.assertNull(rlNiIn.getCombinedProcessIndex(), "ni input");

	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testAddTypes()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode mainNode = doc.getJDFRoot();
		mainNode.setType("ProcessGroup", true);
		mainNode.addTypes(EnumType.Shrinking);
		mainNode.addTypes(EnumType.Verification);
		Assertions.assertTrue(mainNode.getEnumTypes().contains(EnumType.Shrinking));
		Assertions.assertTrue(mainNode.getEnumTypes().contains(EnumType.Verification));
		Assertions.assertEquals(mainNode.getEnumTypes().size(), 2);
		mainNode.addTypes(EnumType.Verification);
		Assertions.assertEquals(mainNode.getEnumTypes().size(), 3);
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testAddProduct()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode mainNode = doc.getJDFRoot();
		mainNode.setType(EnumType.Product);
		final JDFNode p2 = mainNode.addProduct();
		Assertions.assertEquals(p2.getXSIType(), "Product");
	}

	/**
	 *
	 */
	@Test
	public void testAddProcessGroup()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode mainNode = doc.getJDFRoot();
		mainNode.setType(EnumType.ProcessGroup);
		final VString combiNodes = new VString("a b c", null);
		mainNode.setTypes(combiNodes);
		mainNode.addProcessGroup(new VString("d e f", null));
		Assertions.assertNull(mainNode.getTypes(), "must remove types in parent processgroup");
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testAddResource()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode mainNode = doc.getJDFRoot();
		mainNode.setType("Product", true);
		final JDFComponent myComponent = (JDFComponent) mainNode.addResource(ElementName.COMPONENT, JDFResource.EnumResourceClass.Quantity, EnumUsage.Output, null, mainNode, null, null);
		Assertions.assertEquals(myComponent.getResourceClass(), EnumResourceClass.Quantity);
		final JDFComponent myComponent2 = (JDFComponent) mainNode.addResource(ElementName.COMPONENT, EnumUsage.Input);
		Assertions.assertEquals(myComponent2.getResourceClass(), EnumResourceClass.Quantity);
		myComponent.setDescriptiveName("descriptive_name");
		Assertions.assertNotNull(mainNode.getMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyOutput, null, 0), "");
		final JDFResource myRes = mainNode.addResource("whatever:foo", JDFResource.EnumResourceClass.Quantity, EnumUsage.Output, null, mainNode, "www.whatever.com", null);
		Assertions.assertEquals(myRes.getResourceClass(), EnumResourceClass.Quantity);
		myRes.setDescriptiveName("descriptive_name");
	}

	// /////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testActivation()
	{
		Assertions.assertTrue(EnumActivation.isActive(null));
		Assertions.assertFalse(EnumActivation.isActive(EnumActivation.TestRun));
		Assertions.assertTrue(EnumActivation.isActive(EnumActivation.TestRunAndGo));
	}

	/**
	 *
	 */
	@Test
	public void testAddInternalPipe()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode mainNode = doc.getJDFRoot();
		mainNode.setType("Combined", true);
		mainNode.setTypes(new VString("Cutting Folding Folding Inserting", " "));
		final JDFComponent myComponent = (JDFComponent) mainNode.addInternalPipe(ElementName.COMPONENT, 3, 0);
		Assertions.assertNotNull(myComponent);
		Assertions.assertEquals(myComponent.getPipeProtocol(), "Internal");
		final VElement vE = myComponent.getLinks(myComponent.getLinkString(), null);
		Assertions.assertEquals(vE.size(), 2);
		Assertions.assertEquals(((JDFResourceLink) vE.elementAt(0)).getPipeProtocol(), "Internal");
		Assertions.assertEquals(((JDFResourceLink) vE.elementAt(1)).getPipeProtocol(), "Internal");
	}

	// ///////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testCombinedProcessIndex()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setCombined(new VString("LayoutPreparation Imposition Interpreting DigitalPrinting Folding", null));
		final JDFResource r = n.addResource("Media", EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(r, null);
		final JDFIntegerList cpi = rl.getCombinedProcessIndex();
		Assertions.assertTrue(cpi.contains(3));
		Assertions.assertFalse(cpi.contains(0));
	}

	/**
	 *
	 */
	@Test
	public void testCombinedProcessIndexStar()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setCombined(new VString("LayoutPreparation ConventionalPrinting foo ManualLabor", null));
		final JDFResource r = n.addResource("Media", EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(r, null);
		final JDFIntegerList cpi = rl.getCombinedProcessIndex();
		Assertions.assertTrue(cpi.contains(1));
		Assertions.assertTrue(cpi.contains(3));
		Assertions.assertFalse(cpi.contains(2));
		Assertions.assertFalse(cpi.contains(0));
	}

	/**
	*
	*/
	@Test
	public void testCombinedProcessIndexGeneric()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setCombined(new VString("ManualLabor", null));
		final JDFResource r = n.addResource("Component", EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(r, null);
		final JDFIntegerList cpi = rl.getCombinedProcessIndex();
		Assertions.assertTrue(cpi.contains(0));
	}

	/**
	 *
	 */
	@Test
	public void testEnumType()
	{
		Assertions.assertNull(EnumType.getEnum(-1));
		Assertions.assertNull(EnumType.getEnum("FOO"));
		Assertions.assertEquals(EnumType.ConventionalPrinting, EnumType.getEnum(JDFConstants.TYPE_CONVENTIONALPRINTING));
	}

	/**
	 *
	 */
	@Test
	public void testContainsType()
	{
		JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setCombined(new VString("LayoutPreparation ConventionalPrinting foo ManualLabor", null));
		Assertions.assertTrue(n.containsType("foo"));
		Assertions.assertTrue(n.containsType("LayoutPreparation"));
		Assertions.assertTrue(n.containsType("ManualLabor"));
		Assertions.assertFalse(n.containsType("Combined"));
		n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.AssetListCreation);
		Assertions.assertTrue(n.containsType(EnumType.AssetListCreation.getName()));
	}

	/**
	 *
	 */
	@Test
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
		Assertions.assertTrue(ra.getNewLink().hasChildElement("Part", null), "link");
		Assertions.assertTrue(ra.getOldLink().hasChildElement("Part", null), "link");
		Assertions.assertTrue(ra.getNewLink().hasChildElement("AmountPool", null), "link");
		Assertions.assertTrue(ra.getOldLink().hasChildElement("AmountPool", null), "link");
	}

	/**
	 *
	 */
	@Test
	public void testEraseEmptyAttributes()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFResource r = n.addResource("fooRes", EnumResourceClass.Consumable, EnumUsage.Input, EnumProcessUsage.AnyInput, null, "", null);
		r.appendAttribute("bar", "", "", "", false);
		n.eraseEmptyAttributes(true);
		Assertions.assertFalse(n.hasAttribute("bar"));
	}

	/**
	 *
	 */
	@Test
	public void testEraseEmptyNodes()
	{
		// note: when using JDFParser.parse(string), empty nodes are removed by
		// default
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.addResource("_foo", EnumResourceClass.Consumable, EnumUsage.Input, EnumProcessUsage.AnyInput, null, "", null);
		n.removeResource("_foo", 0);
		n.eraseEmptyNodes(true);
		Assertions.assertNotNull(n.getResourcePool());
		Assertions.assertNotNull(n.getResourceLinkPool());
	}

	/**
	 *
	 */
	@Test
	public void testFixVersion()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode node = doc.getJDFRoot();
		node.setAttribute("Type", "Scanning");
		Assertions.assertFalse(node.hasAttribute("type", AttributeName.XSIURI, false));
		node.fixVersion(null);
		Assertions.assertTrue(node.hasAttribute("type", AttributeName.XSIURI, false));
		Assertions.assertTrue(doc.write2String(0).indexOf(AttributeName.XSIURI) > 0);
	}

	// //////////////////////////////////////////////////////////////////////////
	// /////

	/**
	 *
	 */
	@Test
	public void testEraseUnlinkedResources()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFResource r = n.addResource("Component", null, null, null, null, null, null);
		final JDFResourcePool rp = n.getResourcePool();
		Assertions.assertTrue(r instanceof JDFComponent);
		Assertions.assertFalse(n.hasChildElement("ResourceLinkPool", null));
		final JDFResourceLinkPool rlp = n.getCreateResourceLinkPool();
		Assertions.assertEquals(rp.getUnlinkedResources().elementAt(0), r);

		final JDFResourceLink rl = rlp.linkResource(r, EnumUsage.Input, EnumProcessUsage.BookBlock);
		Assertions.assertNotNull(rl);
		Assertions.assertNull(rp.getUnlinkedResources());
		final JDFResource rx = n.addResource("ExposedMedia", null, null, null, null, null, null);
		Assertions.assertEquals(rp.getUnlinkedResources().elementAt(0), rx);

		n.setVersion(EnumVersion.Version_1_2);
		final JDFCustomerInfo ci = n.appendCustomerInfo();
		JDFContact co = ci.appendContact();
		co = (JDFContact) co.makeRootResource(null, null, true);
		Assertions.assertEquals(rp.getUnlinkedResources().elementAt(0), rx);
		Assertions.assertEquals(rp.getUnlinkedResources().size(), 1);
		n.eraseUnlinkedResources();
		Assertions.assertNull(rp.getUnlinkedResources());
		Assertions.assertNull(rp.getElement("ExposedMedia"), "didn't zapp unlinked xm");
		Assertions.assertEquals(rp.getElement("Contact"), co);

		ci.deleteNode();
		Assertions.assertEquals(rp.getUnlinkedResources().elementAt(0), co, "referenced contact accidentally zapped");
		n.eraseUnlinkedResources();
		Assertions.assertNull(rp.getElement("Contact"), "didn't zapp unlinked co");

		final JDFResource rFoo = n.addResource("FOO:Bar", EnumResourceClass.Handling, null, null, null, "www.foo.com", null);
		Assertions.assertEquals(rp.getUnlinkedResources().elementAt(0), rFoo);
		final JDFResourceLink rlFoo = n.linkResource(rFoo, EnumUsage.Output, null);
		Assertions.assertNotNull(rlFoo);
		Assertions.assertNull(rp.getUnlinkedResources());

	}

	// ////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testRemoveResource()
	{
		final JDFDoc gd = new JDFDoc("JDF");
		final JDFNode n = gd.getJDFRoot();
		n.addResource("Media", EnumUsage.Input);
		n.addResource("Media", EnumUsage.Input);
		Assertions.assertNotNull(n.getLink(1, null, null, null));
		n.removeResource("Media", 0);
		Assertions.assertNull(n.getLink(1, null, null, null));
		Assertions.assertNotNull(n.getLink(0, null, null, null));
		n.removeResource("Media", 0);
		Assertions.assertNull(n.getLink(0, null, null, null));
	}

	/**
	 *
	 */
	@Test
	public void testRemoveLink()
	{
		final JDFDoc gd = new JDFDoc("JDF");
		final JDFNode n = gd.getJDFRoot();
		n.addResource("Media", EnumUsage.Input);
		n.addResource("Media", EnumUsage.Input);
		JDFResourceLink rl0 = n.getLink(0, null, null, null);
		final JDFResourceLink rl1 = n.getLink(1, null, null, null);
		Assertions.assertNotNull(rl1);
		n.removeLink(rl0, true);
		Assertions.assertNull(n.getLink(1, null, null, null));
		rl0 = n.getLink(0, null, null, null);
		Assertions.assertNotNull(n.getLink(0, null, null, null));
		n.removeLink(rl0, true);
		Assertions.assertNull(n.getLink(0, null, null, null));
	}

	/**
	 * @throws DataFormatException
	 *
	 */
	@Test
	public void testRemoveFromTypes() throws DataFormatException
	{
		final JDFDoc gd = new JDFDoc("JDF");
		final JDFNode n = gd.getJDFRoot();
		n.setCombined(new VString("Interpreting Rendering Screening", null));
		final JDFResource r1 = n.addResource(ElementName.INTERPRETINGPARAMS, EnumUsage.Input);
		final JDFResourceLink rl1 = n.getLink(r1, null);
		Assertions.assertEquals(rl1.getCombinedProcessIndex(), new JDFIntegerList("0"));
		final JDFResource r2 = n.addResource(ElementName.RENDERINGPARAMS, EnumUsage.Input);
		final JDFResourceLink rl2 = n.getLink(r2, null);
		Assertions.assertEquals(rl2.getCombinedProcessIndex(), new JDFIntegerList("1"));
		final JDFResource r3 = n.addResource(ElementName.SCREENINGPARAMS, EnumUsage.Input);
		final JDFResourceLink rl3 = n.getLink(r3, null);
		Assertions.assertEquals(rl3.getCombinedProcessIndex(), new JDFIntegerList("2"));
		n.removeFromTypes("Rendering", 1, true);
		Assertions.assertNotNull(n.getLink(r2, null));
		Assertions.assertEquals(rl3.getCombinedProcessIndex(), new JDFIntegerList("2"));
		n.removeFromTypes("Rendering", 0, true);
		Assertions.assertNull(n.getLink(r2, null));
		Assertions.assertEquals(rl3.getCombinedProcessIndex(), new JDFIntegerList("1"));

	}

	// //////////////////////////

	/**
	 *
	 */
	@Test
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
		Assertions.assertTrue(ra.getComment(0) == ra.getNewLink().getNextSiblingElement(), "Comment after NewLink");
		Assertions.assertTrue(ra.getComment(0).getNextSiblingElement() == ra.getOldLink(), "Comment before OldLink");
		Assertions.assertTrue(ra.getComment(1) == ra.getOldLink().getNextSiblingElement(), "Comment after OldLink");

		Assertions.assertTrue(ra.numChildElements("RunListLink", null) == 2, "resaudit children=2");
		Assertions.assertTrue(n.getResourcePool().numChildElements("RunList", null) == 2, "respool children=2");
		Assertions.assertTrue(n.getResourceLinkPool().numChildElements("RunListLink", null) == 1, "reslink children=1");
		Assertions.assertTrue(((JDFResourceLink) n.getResourceLinkPool().getElement("RunListLink", null, 0)).getrRef().equals(ra.getNewLink().getrRef()), "reslink audit1=pool");
		Assertions.assertTrue((ra.getNewLink().getrRef() + "_old_001").equals(ra.getOldLink().getrRef()), "id names");
		Assertions.assertTrue((ra.getOldLink().getTarget()).getLocked(), "old lock");
		final JDFResourceAudit ra2 = n.getAuditPool().addResourceAudit("foo");
		ra2.addNewOldLink(true, rl, EnumUsage.Input);
		ra2.appendComment();
		ra2.addNewOldLink(false, rl, EnumUsage.Input).setDescriptiveName("foo");
		Assertions.assertTrue(ra2.getOldLink().getDescriptiveName().equals("foo"), "addnewlink");
		gd.write2File(sm_dirTestDataTemp + "testaudit.jdf", 2, false);
		Assertions.assertTrue(ra2.isValid(EnumValidationLevel.Complete), "audit valid with Link");
		ra2.copyElement(rl, null);
		Assertions.assertFalse(ra2.isValid(EnumValidationLevel.Complete), "audit invalid with resource");
	}

	/**
	 * test setPartStatus with null maps
	 *
	 */
	@Test
	public void testSetPartStatusNull()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setVersion(JDFElement.EnumVersion.Version_1_3);
		root.setPartStatus(((JDFAttributeMap) null), EnumNodeStatus.Waiting, null);
		JDFNodeInfo ni = root.getNodeInfo();
		Assertions.assertNull(ni);
		Assertions.assertNull(root.getStatusPool());
		Assertions.assertEquals(root.getStatus(), EnumNodeStatus.Waiting);

		ni = root.getCreateNodeInfo();
		root.setPartStatus(((JDFAttributeMap) null), EnumNodeStatus.Completed, null);
		Assertions.assertEquals(root.getStatus(), EnumNodeStatus.Completed);
		Assertions.assertEquals(ni.getNodeStatus(), EnumNodeStatus.Completed, "redundantly blasted in new nodestatus");

		root.setVersion(JDFElement.EnumVersion.Version_1_2);
		root.setPartStatus(((JDFAttributeMap) null), EnumNodeStatus.Completed, null);
		ni = root.getNodeInfo();
		Assertions.assertEquals(root.getStatus(), EnumNodeStatus.Completed);
		Assertions.assertNull(root.getStatusPool());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testSetPartStatusNotNull()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setVersion(JDFElement.EnumVersion.Version_1_3);
		final JDFAttributeMap pm = new JDFAttributeMap(EnumPartIDKey.SheetName.getName(), "s1");
		root.setPartStatus(pm, EnumNodeStatus.Waiting, null);
		JDFNodeInfo ni = (JDFNodeInfo) root.getNodeInfo().getPartition(pm, null);
		Assertions.assertNotNull(ni);
		Assertions.assertNull(root.getStatusPool());
		Assertions.assertEquals(root.getStatus(), EnumNodeStatus.Part);
		Assertions.assertEquals(root.getNodeInfo().getNodeStatus(), EnumNodeStatus.Waiting);
		Assertions.assertEquals(ni.getNodeStatus(), EnumNodeStatus.Waiting);

		root.setVersion(JDFElement.EnumVersion.Version_1_2);
		root.setPartStatus(pm, EnumNodeStatus.Completed, null);
		ni = root.getNodeInfo();
		Assertions.assertEquals(root.getStatus(), EnumNodeStatus.Pool);
		Assertions.assertNotNull(root.getStatusPool());
		Assertions.assertNotNull(root.getStatusPool().getPartStatus(pm));
		Assertions.assertEquals(root.getStatusPool().getPartStatus(pm).getStatus(), EnumNodeStatus.Completed);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testSetPartStatusMisMatchPart()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setVersion(JDFElement.EnumVersion.Version_1_3);
		final JDFAttributeMap pm = new JDFAttributeMap(EnumPartIDKey.SheetName.getName(), "s1");
		root.setPartStatus(pm, EnumNodeStatus.Waiting, null);
		final JDFNodeInfo ni = (JDFNodeInfo) root.getNodeInfo().getPartition(pm, null);
		Assertions.assertNotNull(ni);
		final JDFResourceLink rl = root.getLink(ni, null);
		rl.setPartMap(pm);
		final JDFAttributeMap pm2 = new JDFAttributeMap(EnumPartIDKey.SheetName.getName(), "s2");
		root.setPartStatus(pm2, EnumNodeStatus.Completed, null);
		Assertions.assertEquals(root.getPartStatus(pm2, 0), EnumNodeStatus.Completed);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testSetPartStatusMisMatchPartVersion()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setVersion(JDFElement.EnumVersion.Version_1_6);
		final JDFAttributeMap pm = new JDFAttributeMap(EnumPartIDKey.SheetName.getName(), "s1");
		pm.put(AttributeName.SIDE, "Front");
		pm.put(AttributeName.PARTVERSION, "PV1");
		final VJDFAttributeMap vp = new VJDFAttributeMap();
		vp.add(pm.clone());
		pm.put(AttributeName.SIDE, "Back");
		vp.add(pm);
		root.getCreateNodeInfo().setPartIDKeys(new VString("SheetName Side PartVersion"));
		root.setPartStatus(vp, EnumNodeStatus.Waiting, null);

		final VJDFAttributeMap vp2 = vp.clone();
		vp2.removeKey(AttributeName.SIDE);

		Assertions.assertTrue(root.setPartStatus(vp2, EnumNodeStatus.Completed, null));
		Assertions.assertEquals(EnumNodeStatus.Completed, root.getPartStatus(vp.get(0), 0));
	}

	/**
	 * @throws Exception
	 *
	 *
	 */
	@Test
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
			Assertions.assertNotNull(jmf);
			Assertions.assertEquals(jmf.numChildElements(ElementName.RESPONSE, null), 1);
			final JDFAuditPool ap = root.getAuditPool();
			JDFPhaseTime pt = ap.getLastPhase(null, null);
			Assertions.assertEquals(pt.getStatus(), EnumNodeStatus.Waiting);
			Assertions.assertEquals(root.getPartStatus(null, 0), EnumNodeStatus.Waiting);
			final JDFAttributeMap map = new JDFAttributeMap("SheetName", "S1");
			final VJDFAttributeMap vMap = new VJDFAttributeMap();
			vMap.add(map);
			docJMF.write2File(sm_dirTestDataTemp + File.separator + "queued.jmf", 2, true);
			Thread.sleep(1000);
			su.setActiveNode(root, vMap, null);
			su.setPhase(EnumNodeStatus.Setup, "Setup", EnumDeviceStatus.Setup, null);

			docJMF = su.getDocJMFPhaseTime();
			pt = ap.getLastPhase(vMap, null);
			Assertions.assertEquals(pt.getStatus(), EnumNodeStatus.Setup);
			Assertions.assertEquals(root.getPartStatus(map, 0), EnumNodeStatus.Setup);
			Assertions.assertEquals(pt.getPartMapVector(), vMap);
			jmf = docJMF.getJMFRoot();
			Assertions.assertNotNull(jmf);
			Assertions.assertEquals(jmf.numChildElements(ElementName.RESPONSE, null), 2, "both apply - strange part maps");
			docJMF.write2File(sm_dirTestDataTemp + File.separator + "setup.jmf", 2, true);
			Thread.sleep(1000);
			su.setPhase(EnumNodeStatus.InProgress, "Run", EnumDeviceStatus.Running, null);

			docJMF = su.getDocJMFPhaseTime();
			pt = (JDFPhaseTime) ap.getAudit(-1, EnumAuditType.PhaseTime, null, null);
			Assertions.assertEquals(pt.getStatus(), EnumNodeStatus.InProgress);
			Assertions.assertEquals(root.getPartStatus(map, 0), EnumNodeStatus.InProgress);
			Assertions.assertEquals(pt.getPartMapVector(), vMap);
			jmf = docJMF.getJMFRoot();
			Assertions.assertNotNull(jmf);
			Assertions.assertEquals(jmf.numChildElements(ElementName.RESPONSE, null), 2);
			docJMF.write2File(sm_dirTestDataTemp + "inprogress.jmf", 2, true);
			Thread.sleep(1000);
			su.setPhase(EnumNodeStatus.InProgress, "Run", EnumDeviceStatus.Running, null);

			docJMF = su.getDocJMFPhaseTime();
			pt = (JDFPhaseTime) ap.getAudit(-1, EnumAuditType.PhaseTime, null, null);
			Assertions.assertEquals(pt.getStatus(), EnumNodeStatus.InProgress);
			Assertions.assertEquals(root.getPartStatus(map, 0), EnumNodeStatus.InProgress);
			Assertions.assertEquals(pt.getPartMapVector(), vMap);
			jmf = docJMF.getJMFRoot();
			Assertions.assertNotNull(jmf);
			Assertions.assertEquals(jmf.numChildElements(ElementName.RESPONSE, null), 1);
			docJMF.write2File(sm_dirTestDataTemp + "inprogress2.jmf", 2, true);

			root.getCreateAncestorPool().setPartMapVector(vMap);
			su.setPhase(EnumNodeStatus.InProgress, "Run different", EnumDeviceStatus.Running, null);
			docJMF = su.getDocJMFPhaseTime();
			pt = (JDFPhaseTime) ap.getAudit(-1, EnumAuditType.PhaseTime, null, null);
			Assertions.assertEquals(pt.getStatus(), EnumNodeStatus.InProgress);
			Assertions.assertEquals(root.getPartStatus(map, 0), EnumNodeStatus.InProgress);
			Assertions.assertEquals(pt.getPartMapVector(), vMap);
			jmf = docJMF.getJMFRoot();
			Assertions.assertNotNull(jmf);
			Assertions.assertEquals(jmf.numChildElements(ElementName.RESPONSE, null), 2);

			jmf.convertResponses(null);
			docJMF.write2File(sm_dirTestDataTemp + "inprogress3.jmf", 2, true);

			su.setPhase(EnumNodeStatus.Completed, "Finito", EnumDeviceStatus.Idle, null);
			docJMF = su.getDocJMFPhaseTime();
			pt = (JDFPhaseTime) ap.getAudit(-1, EnumAuditType.PhaseTime, null, null);
			Assertions.assertEquals(pt.getStatus(), EnumNodeStatus.InProgress);
			Assertions.assertEquals(root.getPartStatus(map, 0), EnumNodeStatus.Completed);
			Assertions.assertEquals(pt.getPartMapVector(), vMap);
			jmf = docJMF.getJMFRoot();
			Assertions.assertNotNull(jmf);
			Assertions.assertEquals(jmf.numChildElements(ElementName.RESPONSE, null), 2);

			jmf.convertResponses(null);
			docJMF.write2File(sm_dirTestDataTemp + "inprogressCompleted.jmf", 2, true);
		}
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetCreateNodeInfo()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setVersion(JDFElement.EnumVersion.Version_1_3);
		final JDFNodeInfo nodeInfo = root.getCreateNodeInfo();
		final JDFNodeInfo nodeInfo2 = root.getCreateNodeInfo();
		Assertions.assertTrue(nodeInfo == nodeInfo2);

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
		Assertions.assertTrue(cat);

		Assertions.assertNotNull(nodeVer1.getNodeInfo());
		Assertions.assertNotNull(nodeVer3.getNodeInfo());

		Assertions.assertNull(subNodeVer1.getNodeInfo());
		Assertions.assertNull(subNodeVer3.getNodeInfo());

		Assertions.assertNotNull(subNodeVer1.getInheritedNodeInfo(null));
		Assertions.assertNotNull(subNodeVer3.getInheritedNodeInfo(null));

		// remove them all
		nodeVer3.removeNodeInfo();
		Assertions.assertNull(nodeVer3.getNodeInfo());
		nodeVer1.removeNodeInfo();
		Assertions.assertNull(nodeVer1.getNodeInfo());

		doc.write2File(sm_dirTestDataTemp + "getCreateNodeInfo.xml", 0, true);
	}

	/**
	 * Method testGetExecutablePartitionsPreflightImport
	 *
	 */

	@Test
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
			Assertions.assertTrue(vamExec.size() == 2, "Size of vamExec must be 2");
			final JDFAttributeMap am0 = vamExec.elementAt(0);
			Assertions.assertTrue(am0.size() == 1, "Size of vamExec[0] must be 1");
			Assertions.assertTrue(am0.containsKey("Run"));
			Assertions.assertTrue(am0.containsValue("Chf06181149500001"));

			final JDFAttributeMap am1 = vamExec.elementAt(1);

			Assertions.assertTrue(am1.size() == 1, "Size of vamExec[1] must be 1");
			Assertions.assertTrue(am1.containsKey("Run"));
			Assertions.assertTrue(am1.containsValue("Chf06181154470000"));
		}
	}

	/**
	 * Method testGetExecutablePartitionsNormalizer
	 *
	 */

	@Test
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
		Assertions.assertEquals(vamExec.size(), 2, "Size of vamExec must be 2");
		final JDFAttributeMap am0 = vamExec.elementAt(0);
		Assertions.assertEquals(am0.size(), 1, "Size of vamExec[0] must be 1");
		Assertions.assertTrue(am0.containsKey("Run"));
		Assertions.assertTrue(am0.containsValue("Run93379_000255"));
		final JDFAttributeMap am1 = vamExec.elementAt(1);
		Assertions.assertEquals(am1.size(), 0, "Size of vamExec[1] must be 0");
	}

	/**
	 * Method testIsExecutableZones
	 *
	 */

	@Test
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
		Assertions.assertTrue(b);

		VElement links = nodeProc.getResourceLinks(null, new JDFAttributeMap("Usage", "Input"), null);
		for (int i = 0; i < links.size(); i++)
		{
			final JDFResourceLink link = (JDFResourceLink) links.elementAt(i);
			final VJDFAttributeMap vamExec = nodeProc.getExecutablePartitions(link, JDFResource.EnumResStatus.Draft, true);
			Assertions.assertNotNull(vamExec);
			Assertions.assertTrue(vamExec.size() > 0);
		}

		links = nodeProc.getResourceLinks(null, new JDFAttributeMap("Usage", "Output"), null);
		for (int i = 0; i < links.size(); i++)
		{
			final JDFResourceLink link = (JDFResourceLink) links.elementAt(i);
			final VJDFAttributeMap vamExec = nodeProc.getExecutablePartitions(link, JDFResource.EnumResStatus.Unavailable, true);
			Assertions.assertNotNull(vamExec);
			Assertions.assertTrue(vamExec.size() > 0);
			Assertions.assertTrue(vamExec.contains(map));
		}
		final JDFSpawn spawn = new JDFSpawn(nodeProc);
		spawn.vSpawnParts = vTest;
		spawn.vRWResources_in = new VString();
		spawn.vRWResources_in.add("Output");
		final JDFNode spawnedNode = spawn.spawn();
		Assertions.assertNotNull(spawnedNode);
		Assertions.assertEquals(spawnedNode.getAncestorPool().getPartMapVector(), vTest);

	}

	// ///////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testNullPointerException()
	{
		final List<EnumCleanUpMerge> LcleanUpMerge = JDFNode.EnumCleanUpMerge.getEnumList();
		final List<EnumAmountMerge> LamountMerge = JDFResource.EnumAmountMerge.getEnumList();

		for (int i = 0; i < LcleanUpMerge.size(); i++)
		{
			final EnumCleanUpMerge cleanUp = LcleanUpMerge.get(i);

			for (int j = 0; j < LcleanUpMerge.size(); j++)
			{
				final EnumAmountMerge amountMerge = LamountMerge.get(j);

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
				Assertions.assertNotNull(root);
				final JDFNode root2 = (JDFNode) m_jdfDoc2.getRoot();
				Assertions.assertNotNull(root2);
				if (root == null)
				{
					return; // soothe findbugs ;)
				}
				new JDFMerge(root).mergeJDF(root2, sm_dirTestData + xmlFile2, cleanUp, amountMerge);

				m_jdfDoc.write2File(sm_dirTestDataTemp + outFile + i + j, 2, true);
			}
		}
	}

	/**
	 *
	 */
	@Test
	public void testNodeIdentifier()
	{
		final NodeIdentifier ni = new NodeIdentifier();
		final NodeIdentifier ni2 = new NodeIdentifier("", "", null);
		Assertions.assertEquals(ni, ni2);
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setJobID("j1");
		n.setJobPartID("p1");
		ni.setTo(n);
		Assertions.assertNotSame(ni, ni2);
		ni2.setTo(n);
		Assertions.assertEquals(ni, ni2);
		n.appendAncestorPool().appendPart().setPartMap(new JDFAttributeMap("a", "b"));
		ni.setTo(n);
		Assertions.assertNotSame(ni, ni2);
		ni2.setTo(n);
		Assertions.assertEquals(ni, ni2);
	}

	/**
	 *
	 */
	@Test
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
		Assertions.assertEquals(ni, ni2);
		n.setPartStatus(new JDFAttributeMap("SignatureName", "Sig1"), EnumNodeStatus.InProgress, null);
		ni.setTo(n);
		Assertions.assertNotSame(ni, ni2);

	}

	/**
	 *
	 */
	@Test
	public void testNodeIdentifierMatches()
	{
		final NodeIdentifier ni = new NodeIdentifier();
		final NodeIdentifier ni2 = new NodeIdentifier("", "", null);
		Assertions.assertEquals(ni, ni2);
		Assertions.assertTrue(ni.matches(ni2));
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setJobID("j1");
		n.setJobPartID("p1");
		ni.setTo(n);
		Assertions.assertTrue(ni.matches(ni2));
		Assertions.assertTrue(ni.matches("j1"), "ok if jobID matches");
		Assertions.assertFalse(ni.matches("p1"));
		final JDFAncestorPool aPool = n.appendAncestorPool();
		aPool.appendPart().setPartMap(new JDFAttributeMap("a", "b"));
		ni.setTo(n);
		Assertions.assertTrue(ni.matches(ni2));
		ni2.setTo(n);
		Assertions.assertTrue(ni.matches(ni2));
		aPool.appendPart().setPartMap(new JDFAttributeMap("a", "c"));
		ni.setTo(n);
		Assertions.assertTrue(ni.matches(ni2));

	}

	// ////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testInit()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		Assertions.assertNotNull(node.getStatus());
		Assertions.assertFalse(node.getID().equals(""));
		node.init();
		node.init();
		final JDFAuditPool ap = node.getAuditPool();
		Assertions.assertNotNull(ap);
		Assertions.assertEquals(ap.numChildElements(ElementName.CREATED, null), 1);
	}

	/**
	 *
	 */
	@Test
	public void testInitDefaultVersion()
	{

		JDFDoc doc = new JDFDoc(ElementName.JDF);
		JDFNode node = doc.getJDFRoot();
		Assertions.assertEquals(node.getVersion(true), JDFElement.getDefaultJDFVersion());
		Assertions.assertEquals(node.getMaxVersion(true), JDFElement.getDefaultJDFVersion());

		JDFElement.setDefaultJDFVersion(EnumVersion.Version_1_1);
		doc = new JDFDoc(ElementName.JDF);
		node = doc.getJDFRoot();
		Assertions.assertEquals(node.getVersion(true), JDFElement.getDefaultJDFVersion());
		Assertions.assertEquals(node.getMaxVersion(true), JDFElement.getDefaultJDFVersion());

	}

	// ////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testIsValid()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		node.setType(EnumType.ProcessGroup);
		Assertions.assertTrue(node.isValid(EnumValidationLevel.Complete));
		node.removeAttribute(AttributeName.JOBPARTID, null);
		Assertions.assertTrue(node.isValid(EnumValidationLevel.Complete), "isvalid does not require jpid");
	}

	// ////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testIsValidCombined()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		node.setType(EnumType.Combined);
		Assertions.assertFalse(node.isValid(EnumValidationLevel.Complete), "need typed for combined");
	}

	/**
	 *
	 */
	@Test
	public void testIsGroupNode()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		node.setType(EnumType.Product);
		Assertions.assertTrue(node.isGroupNode());
		node.setType(EnumType.ProcessGroup);
		Assertions.assertTrue(node.isGroupNode());
		node.setTypes(new VString("foo", " "));
		Assertions.assertFalse(node.isGroupNode());
		node.setType(EnumType.Combined);
		Assertions.assertFalse(node.isGroupNode());
		node.setType(EnumType.ConventionalPrinting);
		Assertions.assertFalse(node.isGroupNode());
	}

	/**
	 *
	 */
	@Test
	public void testIsTypesNode()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		node.setType(EnumType.Product);
		Assertions.assertFalse(node.isTypesNode());
		node.setType(EnumType.Combined);
		Assertions.assertTrue(node.isTypesNode());
		node.setType(EnumType.ConventionalPrinting);
		Assertions.assertFalse(node.isTypesNode());
		node.setType(EnumType.ProcessGroup);
		Assertions.assertTrue(node.isTypesNode());
		node.addJDFNode("foo");
		Assertions.assertTrue(node.isTypesNode());

	}

	// ////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testIsExecutable()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		node.setType("ConventionalPrinting", true);
		node.setVersion(JDFElement.EnumVersion.Version_1_3);
		node.setStatus(EnumNodeStatus.Ready);

		Assertions.assertFalse(node.isExecutable(null, true), "no links, no execute");

		// simple non-partitioned case
		final JDFNodeInfo n = node.appendNodeInfo();
		Assertions.assertTrue(n.hasAttribute(AttributeName.CLASS), "ni resource");
		final JDFConventionalPrintingParams convPrintParams = (JDFConventionalPrintingParams) node.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, EnumProcessUsage.AnyInput, null);
		convPrintParams.setResStatus(EnumResStatus.Available, true);
		final JDFComponent outComp = (JDFComponent) node.appendMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyOutput, null);
		outComp.setResStatus(EnumResStatus.Unavailable, true);
		JDFExposedMedia xm = (JDFExposedMedia) node.appendMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.AnyInput, null);
		xm.setResStatus(EnumResStatus.Unavailable, true);
		final JDFMedia media = (JDFMedia) node.appendMatchingResource(ElementName.MEDIA, EnumProcessUsage.AnyInput, null);
		media.setResStatus(EnumResStatus.Available, true);
		boolean exec = node.isExecutable(null, false);
		Assertions.assertFalse(exec, "not exec");
		xm.setResStatus(EnumResStatus.Available, true);
		exec = node.isExecutable(null, false);
		Assertions.assertTrue(exec, "exec");
		node.setStatus(EnumNodeStatus.Completed);
		exec = node.isExecutable(null, false);
		Assertions.assertFalse(exec, "exec");
		node.setStatus(EnumNodeStatus.Waiting);
		xm.setResStatus(EnumResStatus.Unavailable, true);
		final JDFResourceLink rl = node.getLink(xm, null);
		exec = node.isExecutable(null, false);
		Assertions.assertFalse(exec, "exec");
		rl.setDraftOK(true);
		exec = node.isExecutable(null, false);
		Assertions.assertFalse(exec, "exec");

		xm.setResStatus(EnumResStatus.Draft, true);
		exec = node.isExecutable(null, false);
		Assertions.assertTrue(exec, "exec");
		xm.setResStatus(EnumResStatus.Available, true);

		// now a partition
		convPrintParams.setPartUsage(EnumPartUsage.Implicit);
		media.setPartUsage(EnumPartUsage.Implicit);
		xm = (JDFExposedMedia) xm.addPartition(EnumPartIDKey.SignatureName, "sig1");
		xm.setResStatus(EnumResStatus.Unavailable, true);
		exec = node.isExecutable(null, true);
		Assertions.assertFalse(exec, "part not exec");
		xm.setResStatus(EnumResStatus.Available, true);
		exec = node.isExecutable(null, true);
		Assertions.assertTrue(exec, "part exec");
		final JDFAttributeMap partMap = new JDFAttributeMap("SignatureName", "sig1");
		node.setPartStatus(partMap, EnumNodeStatus.Waiting, null);

		outComp.addPartition(EnumPartIDKey.SignatureName, "sig1");
		exec = node.isExecutable(partMap, false);
		Assertions.assertTrue(exec, "part exec");

		node.setPartStatus(((JDFAttributeMap) null), EnumNodeStatus.Completed, null);

		// the root is set to completed --> must fail
		exec = node.isExecutable(null, false);
		Assertions.assertFalse(exec, "part exec");

		// now try a non existing partition - should fail
		partMap.put("SignatureName", "sig2");
		exec = node.isExecutable(partMap, false);

		Assertions.assertFalse(exec, "part exec");

	}

	// ///////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetInheritedNodeInfo()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		final JDFNodeInfo n = node.appendNodeInfo();

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

		Assertions.assertNull(node2.getNodeInfo());
		Assertions.assertNull(node2.getInheritedNodeInfo("MISDetails"));
		Assertions.assertNull(node2.getInheritedNodeInfo("JMF/@DeviceID"));
		Assertions.assertEquals(node2.getInheritedNodeInfo("JMF/@ICSVersions"), niAN);
		Assertions.assertEquals(node.getInheritedNodeInfo(null), n);
		Assertions.assertEquals(node2.getInheritedNodeInfo(null), n);
		Assertions.assertEquals(node3.getInheritedNodeInfo(null), n);
		Assertions.assertEquals(node3.getInheritedCustomerInfo(null), ciAN);
		final JDFNodeInfo ni3 = node3.appendNodeInfo();
		final JDFNodeInfo sigNI = (JDFNodeInfo) ni3.addPartition(EnumPartIDKey.SignatureName, "Sig1");
		sigNI.setStart(new JDFDate());
		final JDFNodeInfo niPart = (JDFNodeInfo) sigNI.addPartition(EnumPartIDKey.SheetName, "S1");
		node3.getLink(ni3, null).setPartMap(niPart.getPartMap());
		niPart.setEnd(new JDFDate());
		Assertions.assertEquals(node3.getInheritedNodeInfo(null), niPart);
		Assertions.assertEquals(node3.getInheritedNodeInfo("@End"), niPart);
		Assertions.assertEquals(node3.getInheritedNodeInfo("@Start"), niPart);
		Assertions.assertNull(node3.getInheritedNodeInfo("@FooBar"));
		Assertions.assertEquals(node3.getInheritedNodeInfo("JMF/@ICSVersions"), niAN);
		niPart.addPartition(EnumPartIDKey.DeliveryUnit0, "D0").setAttribute("FooBar", "f1");
		Assertions.assertEquals(node3.getInheritedNodeInfo("@FooBar"), niPart, "search in leaves");

	}

	/**
	 *
	 */
	@Test
	public void testCreateNode()
	{
		Assertions.assertEquals(JDFNode.class, JDFNode.createRoot().getClass());
	}

	/**
	 *
	 */
	@Test
	public void testCreateNodeInfo()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		final JDFNodeInfo n = node.appendNodeInfo();

		Assertions.assertTrue(n.getResourceClass() == EnumResourceClass.Parameter, "nodeinfo is resource");
		doc.write2File(sm_dirTestDataTemp + "createNodeInfoTest.xml", 0, true);
		final JDFCustomerInfo myCustInfo = node.getCreateCustomerInfo();
		myCustInfo.appendContact();
		// assertTrue("contact is res", myContact.getResourceClass() == EnumResourceClass.Parameter);
		Assertions.assertNotNull(node.getNodeInfo());
		node.removeNodeInfo();
		Assertions.assertNull(node.getNodeInfo());

	}

	// /////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetvJDFNode()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFNode n1 = n.addProcessGroup(null);
		final JDFNode n2 = n.addProcessGroup(null);
		final JDFNode n11 = n1.addProcessGroup(null);
		Assertions.assertEquals(n.getvJDFNode(null, null, true).size(), 2);
		Assertions.assertEquals(n1.getvJDFNode(null, null, true).size(), 1);
		Assertions.assertEquals(n2.getvJDFNode(null, null, false).size(), 1);
		Assertions.assertEquals(n1.getvJDFNode(null, null, false).size(), 2);
		Assertions.assertEquals(n.getvJDFNode(null, null, true).elementAt(0), n1);
		Assertions.assertEquals(n.getvJDFNode(null, null, false).elementAt(3), n);
		Assertions.assertEquals(n1.getvJDFNode(null, null, true).elementAt(0), n11);
		Assertions.assertEquals(n1.getvJDFNode(null, null, false).elementAt(1), n1);

	}

	// /////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testGetPartStatusNull()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setPartStatus(((JDFAttributeMap) null), EnumNodeStatus.Completed, null);
		Assertions.assertEquals(n.getPartStatus(null, 0), EnumNodeStatus.Completed);
		final JDFNodeInfo nodeInfo = n.appendNodeInfo();
		nodeInfo.setPartUsage(EnumPartUsage.Implicit);
		nodeInfo.setNodeStatus(EnumNodeStatus.Cleanup);
		n.setStatus(EnumNodeStatus.Part);
		Assertions.assertEquals(n.getPartStatus(null, 0), EnumNodeStatus.Cleanup);
		Assertions.assertEquals(n.getPartStatus(new JDFAttributeMap("Run", "r1"), 0), EnumNodeStatus.Cleanup);
		n.setStatus(EnumNodeStatus.Setup);
		Assertions.assertEquals(n.getPartStatus(null, 0), EnumNodeStatus.Setup);
		Assertions.assertEquals(n.getPartStatus(new JDFAttributeMap("Run", "r1"), 0), EnumNodeStatus.Setup);
	}

	// //////////////////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testGetPartStatusSingleLeaf()
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
		final JDFAttributeMap partMap2 = new JDFAttributeMap(partMap);
		partMap2.put("RunSet", "1");
		final JDFNodeInfo createPartition2 = (JDFNodeInfo) createPartition.getCreatePartition(partMap2, vPartKeys);
		createNodeInfo.setNodeStatus(EnumNodeStatus.Completed);
		createPartition.setNodeStatus(EnumNodeStatus.Waiting);
		createPartition2.setNodeStatus(EnumNodeStatus.Waiting);
		EnumNodeStatus partStatus = createJDF.getPartStatus(null, 0);
		Assertions.assertNull(partStatus, "If parent is different from single leaf: null ");
		partStatus = createJDF.getPartStatus(null, -1);
		Assertions.assertEquals(EnumNodeStatus.Waiting, partStatus, "If parent is different from single leaf: null ");
		partStatus = createJDF.getPartStatus(null, 1);
		Assertions.assertEquals(EnumNodeStatus.Completed, partStatus, "If parent is different from single leaf: null ");
		partStatus = createJDF.getPartStatus(partMap, 0);
		Assertions.assertEquals(EnumNodeStatus.Waiting, partStatus, "If parent is different from single leaf: null ");
	}

	/**
	 *
	 */
	@Test
	public void testGetPartStatusIdentical()
	{
		final JDFNode createJDF = new JDFDoc("JDF").getJDFRoot();
		createJDF.getCreateResourcePool();
		final JDFNodeInfo createNodeInfo = createJDF.getCreateNodeInfo();
		createNodeInfo.setNodeStatus(EnumNodeStatus.Waiting);
		createJDF.setStatus(EnumNodeStatus.Part);
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put("Run", "1");
		final JDFNodeInfo createPartition = (JDFNodeInfo) createNodeInfo.getCreatePartition(partMap, null);
		final JDFAttributeMap partMap2 = new JDFAttributeMap();
		partMap2.put("Run", "2");
		final JDFNodeInfo createPartition2 = (JDFNodeInfo) createNodeInfo.getCreatePartition(partMap2, null);
		createNodeInfo.setNodeStatus(EnumNodeStatus.Waiting);
		createPartition2.setIdentical(createPartition);
		EnumNodeStatus partStatus = createJDF.getPartStatus(null, 0);
		Assertions.assertEquals(EnumNodeStatus.Waiting, partStatus, "two leaves ");
		createPartition.setNodeStatus(EnumNodeStatus.Completed);
		partStatus = createJDF.getPartStatus(partMap, 0);
		Assertions.assertEquals(EnumNodeStatus.Completed, partStatus, "If parent is different from identical leaves: null ");
		partStatus = createJDF.getPartStatus(partMap2, 0);
		Assertions.assertEquals(EnumNodeStatus.Completed, partStatus, "If parent is different from identical leaves: null ");
	}

	/**
	 *
	 */
	@Test
	public void testGetPartStatusImplicit()
	{
		final JDFNode createJDF = new JDFDoc(ElementName.JDF).getJDFRoot();
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
		final EnumNodeStatus partStatus = createJDF.getPartStatus(partMap, 0);
		Assertions.assertEquals(partStatus, EnumNodeStatus.Waiting, "The implicit leaf defaults to root");
	}

	// /////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
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
			Assertions.assertEquals(node.getPartStatus(vParts.elementAt(i), 0), EnumNodeStatus.Completed);
		}
		Assertions.assertTrue(System.currentTimeMillis() - t < 25000, "too slow may laptop takes roughly 2.5 seconds");
	}

	// /////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testGetVectorPartStatus()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final VJDFAttributeMap v = new VJDFAttributeMap();
		for (int i = 0; i < 3; i++)
		{
			v.add(new JDFAttributeMap("SheetName", "s" + i));
		}
		n.setPartStatus(v, EnumNodeStatus.Cleanup, null);
		Assertions.assertEquals(n.getVectorPartStatus(v), EnumNodeStatus.Cleanup);
		v.removeElementAt(2);
		Assertions.assertEquals(n.getVectorPartStatus(v), EnumNodeStatus.Cleanup);
		n.setPartStatus(new JDFAttributeMap("SheetName", "s1"), EnumNodeStatus.Setup, null);
		Assertions.assertNull(n.getVectorPartStatus(v));
	}

	// /////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testGetVectorPartStatusDetails()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final VJDFAttributeMap v = new VJDFAttributeMap();
		for (int i = 0; i < 3; i++)
		{
			v.add(new JDFAttributeMap("SheetName", "s" + i));
		}
		n.setPartStatus(v, EnumNodeStatus.Cleanup, "dummy");
		Assertions.assertEquals(n.getVectorPartStatusDetails(v), "dummy");
		v.removeElementAt(2);
		Assertions.assertEquals(n.getVectorPartStatusDetails(v), "dummy");
		n.setPartStatus(new JDFAttributeMap("SheetName", "s1"), EnumNodeStatus.Cleanup, "foobar");
		Assertions.assertNull(n.getVectorPartStatusDetails(v));
	}

	/**
	 *
	 */
	@Test
	public void testGetPartStatusGlobal()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode node = doc.getJDFRoot();
		node.setStatus(EnumNodeStatus.Spawned);
		for (int i = -1; i < 2; i++)
		{
			Assertions.assertEquals(node.getPartStatus(null, i), EnumNodeStatus.Spawned);
			Assertions.assertEquals(node.getPartStatus(new JDFAttributeMap(), i), EnumNodeStatus.Spawned);
		}
		node.setPartStatus(new JDFAttributeMap(), EnumNodeStatus.InProgress, null);
		for (int i = -1; i < 2; i++)
		{
			Assertions.assertEquals(node.getPartStatus(null, i), EnumNodeStatus.InProgress);
			Assertions.assertEquals(node.getPartStatus(new JDFAttributeMap(), i), EnumNodeStatus.InProgress);
		}
	}

	/**
	 *
	 */
	@Test
	public void testGetPartStatus()
	{
		final JDFDoc doc = JDFTestCaseBase.creatXMDoc();
		final JDFNode node = doc.getJDFRoot();
		final JDFNodeInfo ni = node.getNodeInfo();
		Assertions.assertTrue(ni.getResourceClass() == EnumResourceClass.Parameter, "nodeinfo is resource");
		node.setPartStatus(((JDFAttributeMap) null), EnumNodeStatus.Waiting, null);
		Assertions.assertTrue(node.getPartStatus(null, 0) == EnumNodeStatus.Waiting, "ni root waiting");
		final JDFAttributeMap m = new JDFAttributeMap("SignatureName", "Sig1");
		node.setPartStatus(m, EnumNodeStatus.Completed, null);
		Assertions.assertTrue(node.getPartStatus(m, 0) == EnumNodeStatus.Completed, "ni sig1 completed");
		Assertions.assertNull(node.getPartStatus(null, 0), "ni root mixed");
		final JDFAttributeMap m3 = new JDFAttributeMap("SignatureName", "Sig2");
		Assertions.assertTrue(node.getPartStatus(m3, 0) == EnumNodeStatus.Waiting, "ni sig2 waiting");

		m.put("SheetName", "S1");
		m.put("Side", "Front");
		Assertions.assertEquals(node.getPartStatus(m, 0), EnumNodeStatus.Completed);
		node.setPartStatus(m, EnumNodeStatus.Waiting, null);
		Assertions.assertTrue(node.getPartStatus(m, 0) == EnumNodeStatus.Waiting, "ni sig1 waiting");

		final JDFAttributeMap m2 = new JDFAttributeMap("SignatureName", "Sig1");
		Assertions.assertNull(node.getPartStatus(m2, 0), "ni sig1 mixed");

		final JDFAttributeMap m4 = new JDFAttributeMap("SignatureName", "Sig3");
		m4.put("SheetName", "S1");
		final VJDFAttributeMap v = new VJDFAttributeMap();
		v.add(m4);
		node.prepareNodeInfo(v);
		Assertions.assertTrue(node.getPartStatus(m4, 0) == EnumNodeStatus.Waiting, "ni sig3 waiting");
		Assertions.assertNotNull(ni.getPartition(m4, EnumPartUsage.Explicit), "explicit m4");

		final JDFAttributeMap m5 = new JDFAttributeMap("Side", "Back");
		Assertions.assertNull(node.getPartStatus(m5, 0), "ni side back  mixed");
	}

	/**
	 *
	 */
	@Test
	public void testGetPartStatusPartVersion()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFNodeInfo ni = n.getCreateNodeInfo();
		ni.setStatus(EnumNodeStatus.Waiting);
		ni.setPartUsage(EnumPartUsage.Implicit);
		n.setPartStatus(new JDFAttributeMap(EnumPartIDKey.PartVersion, "p1"), EnumNodeStatus.Completed, null);
		n.setPartStatus(new JDFAttributeMap(EnumPartIDKey.PartVersion, "p2"), EnumNodeStatus.Completed, null);
		Assertions.assertEquals(EnumNodeStatus.Completed, n.getPartStatus(new JDFAttributeMap(EnumPartIDKey.PartVersion, "p1 p2"), -1));
		Assertions.assertEquals(EnumNodeStatus.Completed, n.getPartStatus(new JDFAttributeMap(EnumPartIDKey.PartVersion, "p1 p2"), 0));
		Assertions.assertEquals(EnumNodeStatus.Completed, n.getPartStatus(new JDFAttributeMap(EnumPartIDKey.PartVersion, "p1 p2"), 1));

	}

	/**
	 *
	 */
	@Test
	public void testGetPartStatusMinMax()
	{
		for (int i = -1; i < 2; i += 2)
		{
			final EnumNodeStatus minmax = i < 0 ? EnumNodeStatus.Waiting : EnumNodeStatus.Completed;
			final JDFDoc doc = JDFTestCaseBase.creatXMDoc();
			final JDFNode node = doc.getJDFRoot();
			final JDFNodeInfo ni = node.getNodeInfo();
			Assertions.assertEquals(ni.getResourceClass(), EnumResourceClass.Parameter, "nodeinfo is resource");
			node.setPartStatus(((JDFAttributeMap) null), EnumNodeStatus.Waiting, null);
			Assertions.assertEquals(node.getPartStatus(null, i), EnumNodeStatus.Waiting, "ni root waiting");
			final JDFAttributeMap m = new JDFAttributeMap("SignatureName", "Sig1");
			node.setPartStatus(m, EnumNodeStatus.Completed, null);
			Assertions.assertTrue(node.getPartStatus(m, i) == EnumNodeStatus.Completed, "ni sig1 completed");
			Assertions.assertEquals(minmax, node.getPartStatus(null, i), "ni root mixed");
			final JDFAttributeMap m3 = new JDFAttributeMap("SignatureName", "Sig2");
			Assertions.assertEquals(node.getPartStatus(m3, i), EnumNodeStatus.Waiting, "ni sig2 waiting");

			m.put("SheetName", "S1");
			m.put("Side", "Front");
			Assertions.assertEquals(node.getPartStatus(m, i), EnumNodeStatus.Completed);
			node.setPartStatus(m, EnumNodeStatus.Waiting, null);
			Assertions.assertEquals(node.getPartStatus(m, i), EnumNodeStatus.Waiting, "ni sig1 waiting");

			final JDFAttributeMap m2 = new JDFAttributeMap("SignatureName", "Sig1");
			Assertions.assertEquals(minmax, node.getPartStatus(m2, i), "ni sig1 mixed");

			final JDFAttributeMap m4 = new JDFAttributeMap("SignatureName", "Sig3");
			m4.put("SheetName", "S1");
			final VJDFAttributeMap v = new VJDFAttributeMap();
			v.add(m4);
			node.prepareNodeInfo(v);
			Assertions.assertEquals(node.getPartStatus(m4, i), EnumNodeStatus.Waiting, "ni sig3 waiting");
			Assertions.assertNotNull(ni.getPartition(m4, EnumPartUsage.Explicit), "explicit m4");

			final JDFAttributeMap m5 = new JDFAttributeMap("Side", "Back");
			Assertions.assertEquals(minmax, node.getPartStatus(m5, i), "ni side back  mixed");
		}
	}

	/**
	 *
	 */
	@Test
	public void testGenericResources()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		final JDFDevice dev = (JDFDevice) n.appendMatchingResource(ElementName.DEVICE, EnumProcessUsage.AnyInput, null);
		dev.setDeviceID("mydev");
		Assertions.assertTrue(dev.isValid(EnumValidationLevel.Complete), "valid device");
		final JDFModule m = dev.appendModule();
		m.setModuleID("Foo");
		final JDFEmployee emp = (JDFEmployee) n.appendMatchingResource(ElementName.EMPLOYEE, EnumProcessUsage.AnyInput, null);
		emp.setPersonalID("emp1");
		Assertions.assertTrue(m.isValid(EnumValidationLevel.Complete), "valid module");
		n.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, EnumProcessUsage.AnyInput, null);
		n.appendMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyOutput, null);
		n.appendMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.Plate, null);

		Assertions.assertTrue(n.isValid(EnumValidationLevel.Incomplete), "valid node");
	}

	// ////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testProductValidation()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType("Product", true);
		final JDFDevice dev = (JDFDevice) n.appendMatchingResource(ElementName.DEVICE, EnumProcessUsage.AnyInput, null);
		dev.setDeviceID("mydev");
		Assertions.assertTrue(dev.isValid(EnumValidationLevel.Complete), "valid device");
		final JDFModule m = dev.appendModule();
		m.setModuleID("Foo");
		final JDFEmployee emp = (JDFEmployee) n.appendMatchingResource(ElementName.EMPLOYEE, EnumProcessUsage.AnyInput, null);
		emp.setPersonalID("emp1");
		Assertions.assertTrue(m.isValid(EnumValidationLevel.Complete), "valid module");
	}

	// ////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
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
		Assertions.assertTrue(v.contains("InterpretingParamsLink:AnyInput"), "interpretingParams");
	}

	/**
	 *
	 */
	@Test
	public void testAppendMatchingResourceProduct()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setStatus(EnumNodeStatus.Ready);
		n.setType("Product", true);
		JDFComponent co = (JDFComponent) n.appendMatchingResource("Component", EnumProcessUsage.Cover, null);
		final Vector<EnumComponentType> vType = new Vector<>();
		vType.add(EnumComponentType.FinalProduct);
		vType.add(EnumComponentType.Block);
		co.setComponentType(vType);
		co = (JDFComponent) n.appendMatchingResource("Component", EnumProcessUsage.Cover, null);
		co.setComponentType(vType);
		co = (JDFComponent) n.appendMatchingResource("Component", EnumProcessUsage.AnyOutput, null);
		co.setComponentType(vType);
		Assertions.assertTrue(n.isValid(EnumValidationLevel.Complete));

	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testAppendMatchingResourcePrivate()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setCombined(new VString("ConventionalPrinting fnarf ConventionalPrinting", null));
		JDFResource r = n.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, null);
		JDFResourceLink rl = n.getLink(r, null);
		Assertions.assertNotNull(rl, "rl 1");
		r = n.appendMatchingResource("FnarfParams", null, null);
		Assertions.assertNotNull(r, "r fnarf");
		rl = n.getLink(r, null);
		Assertions.assertNull(rl, "rl fnarf");
		Assertions.assertEquals(n.numMatchingLinks("FnarfParams", true, null), 0);
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testAppendMatchingResourceDefinition()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ResourceDefinition);
		final JDFResource r = n.appendMatchingResource("foo", EnumProcessUsage.AnyInput, null);
		Assertions.assertNotNull(r);
		Assertions.assertEquals(r.getNodeName(), "foo");
	}

	/**
	 *
	 */
	@Test
	public void testGetResource()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ResourceDefinition);
		JDFResource r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, 0);
		Assertions.assertNull(r);
		r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, -1);
		Assertions.assertNull(r);
		final JDFResource rAdd = n.addResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input);
		Assertions.assertNotNull(rAdd);
		r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, 0);
		Assertions.assertEquals(r, rAdd);
		r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, -1);
		Assertions.assertEquals(r, rAdd);
		final JDFResource rAdd2 = n.addResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input);
		Assertions.assertNotNull(rAdd2);
		r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, -1);
		Assertions.assertEquals(r, rAdd2);
		r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, null, -2);
		Assertions.assertEquals(r, rAdd);
		final JDFResource out = n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		Assertions.assertEquals(out, n.getResource(null, EnumUsage.Output, null, 0), "null is valid wildcard");
		final JDFResource ns = n.addResource("foo:bar", EnumResourceClass.Parameter, EnumUsage.Output, null, null, "www.foo.com", null);
		Assertions.assertEquals(ns, n.getResource("foo:bar", EnumUsage.Output, null, 0), "qualified names");
		Assertions.assertNull(n.getResource("bar", EnumUsage.Output, null, 0), "unqualified names no longer supported");
		final JDFResource nsJDF = n.addResource("jdf:bar", EnumResourceClass.Parameter, EnumUsage.Output, null, null, JDFElement.getSchemaURL(), null);
		Assertions.assertEquals(nsJDF, n.getResource("jdf:bar", EnumUsage.Output, null, 0), "qualified names");
		Assertions.assertEquals(nsJDF, n.getResource("bar", EnumUsage.Output, null, 0), "unqualified names");
	}

	/**
	 *
	 */
	@Test
	public void testGetResourceLeaves()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ResourceDefinition);
		final List<JDFResource> r = n.getResourceLeaves(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input);
		Assertions.assertTrue(ContainerUtil.isEmpty(r));
	}

	/**
	 *
	 */
	@Test
	public void testGetLink2()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ResourceDefinition);
		JDFResourceLink rl = n.getLink(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, null);
		Assertions.assertNull(rl);
		final JDFResource rAdd = n.addResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input);
		Assertions.assertNotNull(rAdd);
		rl = n.getLink(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, null);
		Assertions.assertEquals(rl.getLinkRoot(), rAdd);
	}

	/**
	 *
	 */
	@Test
	public void testGetResourceNS()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ResourceDefinition);
		final JDFResource ns = n.addResource("foo:bar", EnumResourceClass.Parameter, EnumUsage.Output, null, null, "www.foo.com", null);
		Assertions.assertEquals(ns, n.getResource("foo:bar", EnumUsage.Output, null, "www.foo.com", 0), "qualified names");
		Assertions.assertEquals(ns, n.getResource("bar", EnumUsage.Output, null, "www.foo.com", 0), "unqualified names");
	}

	/**
	 *
	 */
	@Test
	public void testGetResourceRoot()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ResourceDefinition);
		JDFResource r = n.getResourceRoot(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, 0);
		Assertions.assertNull(r);
		r = n.getResourceRoot(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, -1);
		Assertions.assertNull(r);
		final JDFResource rAdd = n.addResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input);
		Assertions.assertNotNull(rAdd);
		r = n.getResourceRoot(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, 0);
		Assertions.assertEquals(r, rAdd);
		r = n.getResourceRoot(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, -1);
		Assertions.assertEquals(r, rAdd);
	}

	/**
	 *
	 */
	@Test
	public void testGetResourceEmptyRLP()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ResourceDefinition);
		JDFResource r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, 0);
		Assertions.assertNull(r);
		n.getCreateResourceLinkPool();
		r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, 0);
		Assertions.assertNull(r);
	}

	// ////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetResourceProcessUsage()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ResourceDefinition);
		final JDFResource rAdd = n.addResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input);
		final JDFResource rAdd2 = n.addResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input);
		Assertions.assertNotNull(rAdd2);
		final JDFResourceLink rl = n.getLink(rAdd, null);
		rl.setProcessUsage(EnumProcessUsage.Application);
		JDFResource r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, EnumProcessUsage.Application, -1);
		Assertions.assertEquals(r, rAdd);
		r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, EnumProcessUsage.Application, 0);
		Assertions.assertEquals(r, rAdd);
		r = n.getResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, EnumProcessUsage.Application, 1);
		Assertions.assertNull(r);
	}

	/**
	 *
	 */
	@Test
	public void testGetCreateResource()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ResourceDefinition);
		final JDFResource r = n.getCreateResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, 0);
		Assertions.assertNotNull(r);
		final JDFResource rAdd = n.getCreateResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, 0);
		Assertions.assertNotNull(rAdd);
		Assertions.assertEquals(r, rAdd);
		JDFResource rAdd2 = n.getCreateResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, 1);
		Assertions.assertNotNull(rAdd2);
		Assertions.assertNotSame(rAdd2, rAdd);
		rAdd2 = n.getCreateResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, -2);
		Assertions.assertNotNull(rAdd2);
		Assertions.assertEquals(rAdd2, rAdd);
	}

	/**
	 *
	 */
	@Test
	public void testGetCreateResourceProcUsage()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFResource r = n.getCreateResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, "Foo");
		Assertions.assertNotNull(r);
		final JDFResource r2 = n.getCreateResource(ElementName.SADDLESTITCHINGPARAMS, EnumUsage.Input, "Foo");
		Assertions.assertEquals(r, r2);
		Assertions.assertEquals(n.getLink(0, ElementName.SADDLESTITCHINGPARAMS, null, null).getProcessUsage(), "Foo");
	}

	// ////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testAppendMatchingResource()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setCombined(new VString("ConventionalPrinting ConventionalPrinting", null));
		JDFResource r = n.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, null);
		JDFResourceLink rl = n.getLink(r, null);
		Assertions.assertNotNull(rl, "rl 1");

		r = n.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, null);
		Assertions.assertNotNull(r, "r 2");
		rl = n.getLink(r, null);
		Assertions.assertNotNull(rl, "rl 2");
		Assertions.assertEquals(rl.getUsage(), EnumUsage.Input, "rl usage");

		r = n.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, null);
		Assertions.assertNull(r, "exception 3");

		r = n.appendMatchingResource(ElementName.COMPONENT, null, null);
		Assertions.assertNotNull(r, "comp 1");
		rl = n.getLink(r, null);
		Assertions.assertNull(rl, "complink 1");
		rl = n.linkMatchingResource(r, EnumProcessUsage.Input, null);
		Assertions.assertNotNull(rl, "rl 2");
		Assertions.assertEquals(rl.getUsage(), EnumUsage.Input, "rl usage");
		Assertions.assertEquals(rl.getProcessUsage(), "Input");

		r = n.appendMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyOutput, null);
		Assertions.assertNotNull(r, "comp 2");
		rl = n.getLink(r, null);
		Assertions.assertNotNull(rl, "complink 2");
		Assertions.assertEquals(rl.getProcessUsage(), "");

		n.setCombined(new VString("Collecting ConventionalPrinting", null));
		for (int cLoop = 0; cLoop < 5; cLoop++)
		{
			r = n.appendMatchingResource(ElementName.COMPONENT, EnumProcessUsage.AnyInput, null);
			Assertions.assertNotNull(r, "comp loop");
			rl = n.getLink(r, null);
			Assertions.assertNotNull(rl, "complink 2");
			Assertions.assertEquals(rl.getProcessUsage(), "");
		}
	}

	// /////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
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

		final Collection<JDFResource> vSpawned = nodeProc.checkSpawnedResources(vsRWResourceIDs, vamParts);
		Assertions.assertNull(vSpawned);
	}

	/**
	 * @throws DataFormatException
	 */
	@Test
	public void testGetNodeInfo() throws DataFormatException
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(JDFNode.EnumType.ProcessGroup);
		n.setTypes(new VString("a b c", null));
		final JDFNodeInfo ni2 = n.appendNodeInfo();
		final JDFResourceLink rl = n.getLink(ni2, null);
		Assertions.assertNotNull(rl);
		rl.setCombinedProcessIndex(new JDFIntegerList("1 2"));
		final JDFAttributeMap map = new JDFAttributeMap("SheetName", "S1");
		n.setPartStatus(map, EnumNodeStatus.FailedTestRun, null);
		Assertions.assertNotNull(n.getNodeInfo());
		Assertions.assertEquals(ni2, n.getNodeInfo());
		Assertions.assertEquals(ni2, n.getNodeInfo(1));
		Assertions.assertEquals(ni2, n.getNodeInfo(2));
		Assertions.assertNull(n.getNodeInfo(0));
		Assertions.assertNull(n.getNodeInfo(3));

		Assertions.assertEquals(n.getPartStatus(map, 0), EnumNodeStatus.FailedTestRun);
		n.removeAttribute(AttributeName.TYPES);
		Assertions.assertNotNull(n.getNodeInfo(), "invalid types attribute, but still retrieve ni with no cpi");
		Assertions.assertNotNull(n.getNodeInfo(), "invalid types attribute, but...");
	}

	/**
	 * @throws DataFormatException
	 */
	@Test
	public void testGetNodeInfoProc() throws DataFormatException
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(JDFNode.EnumType.ConventionalPrinting);
		final JDFNodeInfo ni2 = n.appendNodeInfo();
		Assertions.assertEquals(ni2, n.getNodeInfo());
		Assertions.assertEquals(ni2, n.getNodeInfo(0));
		Assertions.assertNull(n.getNodeInfo(1));
	}

	/**
	 * @throws DataFormatException
	 */
	@Test
	public void testGetNodeInfoCombinedProc() throws DataFormatException
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(JDFNode.EnumType.ProcessGroup);
		n.setTypes(new VString("a b", null));
		final JDFNodeInfo ni1 = n.appendNodeInfo();
		final JDFResourceLink rl1 = n.getLink(ni1, null);
		rl1.setCombinedProcessIndex(new JDFIntegerList("0"));
		final JDFNodeInfo ni2 = n.appendNodeInfo();
		final JDFResourceLink rl2 = n.getLink(ni2, null);
		rl2.setCombinedProcessIndex(new JDFIntegerList("1"));
		Assertions.assertNotNull(n.getNodeInfo());
		Assertions.assertEquals(ni2, n.getNodeInfo(1));
		Assertions.assertEquals(ni1, n.getNodeInfo(0));
		Assertions.assertNull(n.getNodeInfo(3));
	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetParentJDF()
	{
		{
			final JDFDoc d = new JDFDoc("JDF");
			final JDFNode n = d.getJDFRoot();
			Assertions.assertNull(n.getParentJDF());
			final JDFNode n2 = (JDFNode) n.appendElement("JDF");
			Assertions.assertEquals(n, n2.getParentJDF());
		}
	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetEnumTypes()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType("fnarf", false);
		Assertions.assertNull(root.getEnumTypes());

		root.setType("ProcessGroup", true);

		root.setTypes(new VString("InkZoneCalculation ConventionalPrinting", null));
		Assertions.assertEquals(root.getEnumTypes().elementAt(0), EnumType.InkZoneCalculation);
		Assertions.assertEquals(root.getEnumTypes().elementAt(1), EnumType.ConventionalPrinting);
		Assertions.assertEquals(root.getEnumTypes().size(), 2);

		root.setTypes(new VString("InkZoneCalculation xyz:fnarf ConventionalPrinting", null));
		Assertions.assertNull(root.getEnumTypes(), "contains extension");

	}

	/**
	 *
	 */
	@Test
	public void testGetAncestorElementRef()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		final JDFNodeInfo ni2 = (JDFNodeInfo) root.addResource(ElementName.NODEINFO, null);
		root.appendAncestorPool().appendAncestor().refElement(ni2);
		Assertions.assertEquals(ni2, root.getAncestorElement(ElementName.NODEINFO, null));
	}

	/**
	 *
	 */
	@Test
	public void testGetAncestorElement()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		final JDFNodeInfo ni = root.appendAncestorPool().appendAncestor().appendNodeInfo();
		Assertions.assertEquals(ni, root.getAncestorElement(ElementName.NODEINFO, null));
	}

	/**
	 *
	 */
	@Test
	public void testGetAncestorElementAttribute()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		final JDFNodeInfo ni2 = (JDFNodeInfo) root.addResource(ElementName.NODEINFO, null);
		root.appendAncestorPool().appendAncestor().refElement(ni2);
		ni2.setJobPriority(42);
		Assertions.assertEquals("42", root.getAncestorElementAttribute(ElementName.NODEINFO, AttributeName.JOBPRIORITY, null, null));
		final JDFNodeInfo ni3 = root.getCreateNodeInfo();
		ni3.setJobPriority(43);
		Assertions.assertEquals("43", root.getAncestorElementAttribute(ElementName.NODEINFO, AttributeName.JOBPRIORITY, null, null));
	}

	/**
	 *
	 */
	@Test
	public void testGetActivation()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.ProcessGroup);
		Assertions.assertEquals(root.getActivation(true), EnumActivation.Active);
		Assertions.assertNull(root.getActivation(false));
		final VString types = new VString();
		types.add("foo");
		types.add("bar");

		final JDFNode n2 = root.addCombined(types);
		Assertions.assertEquals(n2.getActivation(true), EnumActivation.Active);
		Assertions.assertNull(n2.getActivation(false));

		root.setActivation(EnumActivation.Inactive);
		Assertions.assertEquals(root.getActivation(true), EnumActivation.Inactive);
		Assertions.assertEquals(root.getActivation(false), EnumActivation.Inactive);
		Assertions.assertEquals(n2.getActivation(true), EnumActivation.Inactive);
		Assertions.assertNull(n2.getActivation(false));

		n2.setActivation(EnumActivation.Active);
		Assertions.assertEquals(n2.getActivation(true), EnumActivation.Inactive);
		Assertions.assertEquals(n2.getActivation(false), EnumActivation.Active);

		n2.setActivation(EnumActivation.Held);
		Assertions.assertEquals(n2.getActivation(true), EnumActivation.Inactive);
		Assertions.assertEquals(n2.getActivation(false), EnumActivation.Held);

		n2.setActivation(null);
		Assertions.assertEquals(n2.getActivation(true), EnumActivation.Inactive);
		Assertions.assertEquals(n2.getActivation(false), null);
	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetAllTypes()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType("fnarf", false);
		Assertions.assertEquals(root.getAllTypes().get(0), "fnarf");
		Assertions.assertEquals(root.getAllTypes().size(), 1);

		root.setType("Product", false);
		Assertions.assertEquals(root.getAllTypes().get(0), "Product");
		Assertions.assertEquals(root.getAllTypes().size(), 1);

		root.setType("ProcessGroup", false);
		Assertions.assertTrue(root.getAllTypes().isEmpty());

		final VString types = new VString();
		types.add("foo");
		types.add("bar");
		root.setTypes(types);

		Assertions.assertEquals(root.getAllTypes(), types);
		root.appendElement("JDF").setAttribute("Type", "fooBar2");
		final VString types2 = new VString(types);
		types2.insertElementAt("fooBar2", 0);
		Assertions.assertEquals(root.getAllTypes(), types2);

		root.removeAttribute("Types");

		final JDFNode n2 = root.addCombined(types);
		n2.setTypes(types);

		Assertions.assertEquals(types2, root.getAllTypes());
		Assertions.assertEquals(types, n2.getAllTypes());

		root.addJDFNode("foobar");

		Assertions.assertEquals(types, n2.getAllTypes());
		types2.add("foobar");
		Assertions.assertEquals(types2, root.getAllTypes());

	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetMatchingNodes()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setJobID("j1");
		n.setJobPartID("p1");
		n.setType(EnumType.Product);
		final JDFNode n2 = n.addProduct();
		n2.setJobPartID("p2");
		Assertions.assertTrue(n.getvJDFNode(null, null, false).containsAll(n.getMatchingNodes(null)));
		Assertions.assertTrue(n.getMatchingNodes(null).contains(n));
		Assertions.assertTrue(n.getMatchingNodes(n.getIdentifier()).contains(n));
		Assertions.assertTrue(n.getMatchingNodes(n2.getIdentifier()).contains(n2));
		Assertions.assertFalse(n.getMatchingNodes(n2.getIdentifier()).contains(n));

	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetMatchingResource()
	{
		final JDFParser pars = new JDFParser();
		final JDFDoc doc = pars.parseFile(sm_dirTestData + File.separator + "testMatchRes.jdf");
		final JDFNode root = doc.getJDFRoot();
		final VElement v = root.getvJDFNode("ProcessGroup", null, false);
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
		Assertions.assertNotNull(ppnode);

		if (ppnode != null)
		{
			Assertions.assertTrue(ppnode.getTypes().contains(EnumType.LayoutElementProduction.getName()));
			JDFResource res = ppnode.getMatchingResource("RunList", JDFNode.EnumProcessUsage.AnyInput, null, 0);
			Assertions.assertEquals(res.getNodeName(), ElementName.RUNLIST);
			res = ppnode.getMatchingResource("LayoutElementProductionParams", JDFNode.EnumProcessUsage.AnyInput, null, 0);
			Assertions.assertEquals(res.getNodeName(), ElementName.LAYOUTELEMENTPRODUCTIONPARAMS);
		}
	}

	/**
	 *
	 */
	@Test
	public void testGetJobPart()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.Product);
		root.setJobPartID("p0");
		root.setJobID("j1");
		final JDFNode p1 = root.addJDFNode(EnumType.Product);
		Assertions.assertEquals(root.getJobPart("p0", null), root);
		Assertions.assertEquals(root.getJobPart("p0", "j1"), root);
		Assertions.assertNull(p1.getJobPart("p0", "j2"));
		Assertions.assertEquals(root.getJobPart("p0.1", null), p1);
		Assertions.assertEquals(root.getJobPart("p0.1", "j1"), p1);
		final JDFNode p11 = p1.addJDFNode(EnumType.Product);
		Assertions.assertEquals(root.getJobPart("p0.1.1", null), p11);
		Assertions.assertEquals(root.getJobPart("p0.1.1", "j1"), p11);
		Assertions.assertEquals(p1.getJobPart("p0.1.1", null), p11);
		Assertions.assertEquals(p1.getJobPart("p0.1.1", "j1"), p11);
		Assertions.assertNull(p1.getJobPart("p0.1.1", "j2"));
	}

	/**
	 *
	 */
	@Test
	public void testGetJobPartIdentifier()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.Product);
		root.setJobPartID("p0");
		final JDFNode p1 = root.addJDFNode(EnumType.Product);
		Assertions.assertEquals(root.getJobPart(new NodeIdentifier(root.getJobID(true), "p0", null)), root);
		Assertions.assertEquals(root.getJobPart(new NodeIdentifier(root.getJobID(true), "p0.1", null)), p1);
		Assertions.assertEquals(root.getJobPart(new NodeIdentifier(null, "p0", null)), root);
		Assertions.assertEquals(root.getJobPart(new NodeIdentifier(null, "p0.1", null)), p1);
		Assertions.assertEquals(root.getJobPart(new NodeIdentifier("", "p0", null)), root);
		Assertions.assertEquals(root.getJobPart(new NodeIdentifier("", "p0.1", null)), p1);
	}

	/**
	 *
	 */
	@Test
	public void testGetChildJDFNode()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.Product);
		root.setJobPartID("p0");
		root.setID("I1");
		final JDFNode p1 = root.addJDFNode(EnumType.Product);
		p1.setID("I2");
		Assertions.assertEquals(root.getChildJDFNode("I1", false), root);
		Assertions.assertEquals(root.getChildJDFNode("I2", false), p1);
		Assertions.assertEquals(root.getChildJDFNode("I2", true), p1);
		final JDFNode p11 = p1.addJDFNode(EnumType.Product);
		p11.setID("I11");
		Assertions.assertEquals(root.getChildJDFNode("I11", false), p11);
		Assertions.assertNull(root.getChildJDFNode("I11", true));
		Assertions.assertEquals(root.getChildJDFNode("I11", false), p11);
		Assertions.assertEquals(p1.getChildJDFNode("I11", true), p11);
		Assertions.assertEquals(p1.getChildJDFNode("I11", false), p11);
	}

	/**
	 *
	 */
	@Test
	public void testGetCombinedProcessIndex()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.Combined);
		root.setTypes(new VString("Folding Cutting Folding Stitching", " "));
		Assertions.assertEquals(root.getCombinedProcessIndex(EnumType.AdhesiveBinding, 0), -1);
		Assertions.assertEquals(root.getCombinedProcessIndex(EnumType.Folding, 0), 0);
		Assertions.assertEquals(root.getCombinedProcessIndex(EnumType.Folding, 1), 2);
		Assertions.assertEquals(root.getCombinedProcessIndex(EnumType.Cutting, 1), 1);
		Assertions.assertEquals(root.getCombinedProcessIndex(EnumType.Stitching, 1), 3);
	}

	/**
	 *
	 */
	@Test
	public void testGetLink()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.ImageSetting);
		{
			final JDFResource r = root.addResource("foo:res", EnumResourceClass.Parameter, EnumUsage.Input, null, null, "www.foo.com", null);
			final JDFResourceLink rl = root.getLink(r, null);
			Assertions.assertNotNull(rl);
		}
		{
			final JDFResource r = root.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);
			final JDFResourceLink rl = root.getLink(r, null);
			Assertions.assertNotNull(rl);
		}
	}

	// ////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testGetResourceLinks()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.ImageSetting);
		VElement v = root.getResourceLinks(null);
		Assertions.assertNull(v);
		root.addResource("foo:res", EnumResourceClass.Parameter, EnumUsage.Input, null, null, "www.foo.com", null);
		v = root.getResourceLinks(null);
		Assertions.assertEquals(v.size(), 1);
		Assertions.assertEquals(root.getResourceLinkPool().getElement(null), v.elementAt(0));
	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
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
			root.updatePartStatus(vMap, true, true, 0);
			Assertions.assertEquals(root.getPartStatus(map1, 0), EnumNodeStatus.Completed);
			Assertions.assertNull(root.getPartStatus(null, 0));
			if (loop == 0)
			{
				Assertions.assertEquals(root.getPartStatus(map21, 0), EnumNodeStatus.InProgress);
			}
			else
			{
				Assertions.assertNotSame(root.getPartStatus(map21, 0), EnumNodeStatus.InProgress, "only updated run=1");
			}

		}
	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testToGrayBox()
	{
		final boolean[] tf = { true, false };
		for (final boolean b : tf)
		{
			JDFNode root = new JDFDoc("JDF").getJDFRoot();

			root.setType(EnumType.Combined);
			final VString vs = new VString("Cutting Folding Cutting", " ");
			root.setTypes(vs);
			root.toGrayBox(b);
			JDFNode child = (JDFNode) root.getElement(ElementName.JDF, null, 0);
			Assertions.assertEquals(b, child != null);
			if (!b)
			{
				Assertions.assertEquals(root.getTypes(), vs);
			}
			else if (child != null)
			{
				Assertions.assertEquals(child.getTypes(), vs);
			}

			Assertions.assertEquals(root.getType(), "ProcessGroup");

			root = new JDFDoc("JDF").getJDFRoot();

			root.setType(EnumType.ConventionalPrinting);
			root.toGrayBox(b);
			child = (JDFNode) root.getElement(ElementName.JDF, null, 0);
			if (!b)
			{
				Assertions.assertEquals(root.getTypes(), new VString("ConventionalPrinting", null));
				Assertions.assertEquals(root.getType(), "ProcessGroup");
			}
			else
			{
				Assertions.assertEquals(child.getType(), "ConventionalPrinting");

			}

		}
	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
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
			Assertions.assertEquals(vMapIn, vMap);
		}
	}

	/**
	 *
	 */
	@Test
	public void testGetGreateLinksForType()
	{
		final JDFNode root = new JDFDoc("JDF").getJDFRoot();

		root.setType(EnumType.Combined);
		root.setTypes(new VString("Cutting Folding Cutting Stitching Trimming", " "));

		final CombinedProcessLinkHelper h = root.new CombinedProcessLinkHelper("Component", EnumUsage.Input);

		final JDFResourceLink rl = h.getCreateLinkForType(EnumType.Folding);
		Assertions.assertEquals(rl.getCombinedProcessIndex(), new JDFIntegerList(1));
		h.setUsage(EnumUsage.Output);
		final JDFResourceLink rl2 = h.getCreateLinkForType(EnumType.Folding);
		Assertions.assertEquals(rl2.getCombinedProcessIndex(), new JDFIntegerList(1));
		Assertions.assertNotSame(rl, rl2);
		final JDFResourceLink rlf = h.getCreateLinkForType(EnumType.Cutting);
		Assertions.assertEquals(rlf.getCombinedProcessIndex(), new JDFIntegerList(0));
		Assertions.assertEquals(rl.getTarget(), rlf.getTarget());
		h.setBoth(true);
		h.setUsage(EnumUsage.Input);
		final JDFResourceLink rlt = h.getCreateLinkForType(EnumType.Trimming);
		Assertions.assertEquals(rlt.getCombinedProcessIndex(), new JDFIntegerList(4));
		h.setUsage(EnumUsage.Output);

		final JDFResourceLink rls = (JDFResourceLink) h.getLinksForType(EnumType.Stitching).get(0);
		Assertions.assertEquals(rls.getTarget(), rlt.getTarget());
	}

	/**
	 *
	 */
	@Test
	public void testGetLinksForType()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();

		root.setType(EnumType.Combined);
		root.setTypes(new VString("Cutting Folding Cutting", " "));

		final JDFResource r1 = root.addResource("CuttingParams", null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl1 = root.getLink(r1, null);
		rl1.setCombinedProcessIndex(new JDFIntegerList(0));

		final JDFResource c1 = root.addResource("Component", EnumUsage.Output);
		final JDFResourceLink rlc1o = root.getLink(c1, null);
		rlc1o.setCombinedProcessIndex(new JDFIntegerList(0));
		rlc1o.setPipeProtocol("Internal");
		final JDFResourceLink rlc1i = root.linkResource(c1, EnumUsage.Input, null);
		rlc1i.setPipeProtocol("Internal");
		rlc1i.setCombinedProcessIndex(new JDFIntegerList(1));

		final JDFResource r2 = root.addResource("FoldingParams", null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl2 = root.getLink(r2, null);
		rl2.setCombinedProcessIndex(new JDFIntegerList(1));

		final JDFResource r3 = root.addResource("CuttingParams", null, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl3 = root.getLink(r3, null);
		rl3.setCombinedProcessIndex(new JDFIntegerList(2));

		final JDFResource r4 = root.addResource("Component", null, EnumUsage.Output, null, null, null, null);
		final JDFResourceLink rl4 = root.getLink(r4, null);

		VElement ve = root.getLinksForType(EnumType.Cutting, 0);
		Assertions.assertEquals(ve.size(), 2);
		Assertions.assertTrue(ve.contains(rl1));
		Assertions.assertTrue(ve.contains(rlc1o));
		Assertions.assertFalse(ve.contains(rl4));

		ve = root.getLinksForType(EnumType.Cutting, 1);
		Assertions.assertEquals(ve.size(), 2);
		Assertions.assertTrue(ve.contains(rl3));
		Assertions.assertTrue(ve.contains(rl4));

		ve = root.getLinksForType(EnumType.Cutting, -1);
		Assertions.assertEquals(ve.size(), 4);
		Assertions.assertTrue(ve.contains(rl1));
		Assertions.assertTrue(ve.contains(rl3));
		Assertions.assertTrue(ve.contains(rl4));
		Assertions.assertTrue(ve.contains(rlc1o));

		ve = root.getLinksForType(EnumType.Folding, 0);
		Assertions.assertEquals(ve.size(), 2);
		Assertions.assertTrue(ve.contains(rl2));
		Assertions.assertFalse(ve.contains(rl4));
		Assertions.assertTrue(ve.contains(rlc1i));

		ve = root.getLinksForCombinedProcessIndex(0);
		Assertions.assertEquals(ve.size(), 2);
		Assertions.assertTrue(ve.contains(rl1));
		Assertions.assertFalse(ve.contains(rl4));

		ve = root.getLinksForCombinedProcessIndex(1);
		Assertions.assertEquals(ve.size(), 2);
		Assertions.assertTrue(ve.contains(rlc1i));
		Assertions.assertTrue(ve.contains(rl2));
		Assertions.assertFalse(ve.contains(rl4));

		// now check whether this works with no cpi
		rl4.removeAttribute(AttributeName.COMBINEDPROCESSINDEX);
		ve = root.getLinksForType(EnumType.Folding, 0);
		Assertions.assertEquals(ve.size(), 3);
		Assertions.assertTrue(ve.contains(rl2));
		Assertions.assertTrue(ve.contains(rl4));
		Assertions.assertTrue(ve.contains(rlc1i));

		ve = root.getLinksForCombinedProcessIndex(0);
		Assertions.assertEquals(ve.size(), 3);
		Assertions.assertTrue(ve.contains(rl1));
		Assertions.assertTrue(ve.contains(rl4));
		Assertions.assertTrue(ve.contains(rlc1o));

		ve = root.getLinksForCombinedProcessIndex(1);
		Assertions.assertEquals(ve.size(), 3);
		Assertions.assertTrue(ve.contains(rl2));
		Assertions.assertTrue(ve.contains(rl4));
		Assertions.assertTrue(ve.contains(rlc1i));

		final CombinedProcessLinkHelper h = root.new CombinedProcessLinkHelper();
		h.setUsage(EnumUsage.Input);
		h.setLinkName("Component");

		ve = h.getLinksForType(EnumType.Cutting);
		Assertions.assertNull(ve);
		ve = h.getLinksForType(EnumType.Folding);
		Assertions.assertEquals(ve.size(), 1);
	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetMatchingResourceStar()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType((EnumType.Combine));

		for (int i = 0; i < 5; i++)
		{
			final JDFResource res = root.appendMatchingResource("RunList", JDFNode.EnumProcessUsage.AnyInput, null);
			Assertions.assertNotNull(res);
			Assertions.assertEquals(res.getNodeName(), ElementName.RUNLIST);
			final JDFResource resa = root.getMatchingResource("RunList", JDFNode.EnumProcessUsage.AnyInput, null, i);
			Assertions.assertEquals(res, resa);
			final JDFResourceLink rlb = root.getMatchingLink("RunList", JDFNode.EnumProcessUsage.AnyInput, i);
			Assertions.assertEquals(rlb.getTarget(), res);

		}
	}

	// ////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////

	/**
	 * getMinID is no longer used in the library
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetMinID()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.ConventionalPrinting);
		final int id = root.getMinID();
		Assertions.assertTrue(id < 5 && id > 0);
		for (int i = 0; i < 1000; i++)
		{
			root.getAuditPool().addModified(null, null);
		}
		Assertions.assertEquals(id + 1000, root.getMinID());
		root.setID("ida123456");
		root.setID("ida123456");
		Assertions.assertEquals(123456, root.getMinID());
		root.setID("ida00000");
		Assertions.assertEquals(id + 1000, root.getMinID());
	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetMissingLinks()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.ConventionalPrinting);
		VString vs = root.getMissingLinkVector(999);
		Assertions.assertTrue(vs.contains(ElementName.CONVENTIONALPRINTINGPARAMS + "Link:AnyInput"), vs.toString());
		Assertions.assertTrue(vs.contains(ElementName.COMPONENT + "Link:AnyOutput"), vs.toString());

		final VString vsc = new VString();
		vsc.add(EnumType.InkZoneCalculation);
		vsc.add(EnumType.ConventionalPrinting);
		root.setCombined(vsc);
		vs = root.getMissingLinkVector(999);
		Assertions.assertTrue(vs.contains(ElementName.PREVIEW + "Link:AnyInput"), vs.toString());
		Assertions.assertTrue(vs.contains(ElementName.CONVENTIONALPRINTINGPARAMS + "Link:AnyInput"), vs.toString());
		Assertions.assertTrue(vs.contains(ElementName.COMPONENT + "Link:AnyOutput"), vs.toString());
	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetMissingLinksProduct()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.Product);
		VString vs = root.getMissingLinkVector(999);
		Assertions.assertTrue(vs.contains(ElementName.COMPONENT + "Link:AnyOutput"));

		root.addJDFNode(EnumType.ProcessGroup);
		root.appendMatchingResource("Employee", EnumProcessUsage.AnyInput, null);
		vs = root.getMissingLinkVector(999);
		Assertions.assertTrue(vs.contains(ElementName.COMPONENT + "Link:AnyOutput"), "product with sub element still requires an output component");
	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetWorkStepID()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.ConventionalPrinting);
		root.setJobPartID("p1");
		Assertions.assertEquals(root.getWorkStepID(null), "p1");
		final JDFAttributeMap attributeMap = new JDFAttributeMap("SheetName", "S1");
		Assertions.assertEquals(root.getWorkStepID(attributeMap), "p1");
		root.setPartStatus(attributeMap, EnumNodeStatus.Cleanup, null);
		Assertions.assertEquals(root.getWorkStepID(null), "p1");
		Assertions.assertEquals(root.getWorkStepID(attributeMap), "p1");
		final JDFNodeInfo ni = root.getNodeInfo();
		final JDFNodeInfo nip = (JDFNodeInfo) ni.getPartition(attributeMap, null);
		nip.setWorkStepID("w2");
		Assertions.assertEquals(root.getWorkStepID(null), "p1");
		Assertions.assertEquals(root.getWorkStepID(attributeMap), "w2");
		ni.setWorkStepID("w1");
		Assertions.assertEquals(root.getWorkStepID(null), "w1");
		Assertions.assertEquals(root.getWorkStepID(attributeMap), "w2");
		nip.removeAttribute(AttributeName.WORKSTEPID);
		Assertions.assertEquals(root.getWorkStepID(null), "w1");
		Assertions.assertEquals(root.getWorkStepID(attributeMap), "w1");
	}

	// ////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testGetUnknownLinks()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.ConventionalPrinting);
		VElement vs = root.getUnknownLinkVector(null, 999);
		Assertions.assertNull(vs);

		root.addResource(ElementName.FOLDINGPARAMS, null, EnumUsage.Input, null, null, null, null);

		vs = root.getUnknownLinkVector(null, 999);
		Assertions.assertTrue(vs.elementAt(0) instanceof JDFResourceLink);
		Assertions.assertEquals(((JDFResourceLink) vs.elementAt(0)).getLocalName(), "FoldingParamsLink");

		root.addResource("foo:barRes", EnumResourceClass.Parameter, EnumUsage.Input, null, null, "www.foo.com", null);

		vs = root.getUnknownLinkVector(null, 999);
		Assertions.assertEquals(vs.size(), 2);
		Assertions.assertTrue(vs.elementAt(0) instanceof JDFResourceLink);
		Assertions.assertEquals(((JDFResourceLink) vs.elementAt(0)).getLocalName(), "FoldingParamsLink");
		Assertions.assertEquals(((JDFResourceLink) vs.elementAt(1)).getNodeName(), "foo:barResLink");

		final VString vsc = new VString();
		vsc.add(EnumType.InkZoneCalculation);
		vsc.add(EnumType.ConventionalPrinting);
		root.setCombined(vsc);
		vs = root.getUnknownLinkVector(null, 999);
		Assertions.assertTrue(vs.elementAt(0) instanceof JDFResourceLink);
		Assertions.assertEquals(((JDFResourceLink) vs.elementAt(0)).getLocalName(), "FoldingParamsLink");
	}

	// ////////////////////////////////////////////////////////////
	/**
	 * test for getting statusdetails
	 */
	@Test
	public void testGetPartStatusDetails()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setPartStatus(((JDFAttributeMap) null), EnumNodeStatus.Completed, null);
		n.setStatusDetails("foobar");
		Assertions.assertEquals(n.getPartStatusDetails(null), "foobar");
		final JDFNodeInfo nodeInfo = n.appendNodeInfo();
		nodeInfo.setPartUsage(EnumPartUsage.Implicit);
		nodeInfo.setNodeStatus(EnumNodeStatus.Cleanup);
		n.setStatus(EnumNodeStatus.Part);
		Assertions.assertNull(n.getPartStatusDetails(null));
		nodeInfo.setNodeStatusDetails("niDetails");
		Assertions.assertEquals(n.getPartStatusDetails(new JDFAttributeMap("Run", "r1")), "niDetails");
	}

}