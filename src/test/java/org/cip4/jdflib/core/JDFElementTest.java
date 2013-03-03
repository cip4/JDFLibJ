/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.core;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoQueue.EnumQueueStatus;
import org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus;
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumOrientation;
import org.cip4.jdflib.core.JDFElement.EnumSettingsPolicy;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFElement.EnumXYRelation;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.jmf.JDFAcknowledge;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFPipeParams;
import org.cip4.jdflib.jmf.JDFQueue;
import org.cip4.jdflib.jmf.JDFQueueEntry;
import org.cip4.jdflib.jmf.JDFResourceCmdParams;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.node.JDFAncestor;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFComChannel;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.StringUtil;
import org.junit.Assert;
import org.junit.Test;
/**
 * @author MuchaD
 * 
 * This implements the first fixture with unit tests for class JDFElement.
 */
public class JDFElementTest extends JDFTestCaseBase
{
	static final String fileSeparator = System.getProperty("file.separator");

	// member variables for the fixture
	private JDFDoc m_jdfDoc;
	private JDFNode m_jdfRoot;
	private KElement m_kElement;
	private JDFElement m_jdfElement;

	/**
	 * 
	 */
	@Test
	public void testAppendElement()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final KElement r = d.getRoot();
		final KElement e = r.appendElement("e");
		Assert.assertEquals(e.getNamespaceURI(), JDFElement.getSchemaURL());
		final KElement foo = e.appendElement("pt:foo", "www.pt.com");
		Assert.assertEquals(foo.getNamespaceURI(), "www.pt.com");
		final KElement bar = foo.appendElement("bar");
		Assert.assertNotNull(bar.getNamespaceURI());
		final KElement foo2 = bar.appendElement("pt:foo", "www.pt.com");
		Assert.assertEquals(foo2.getNamespaceURI(), "www.pt.com");
	}

	/**
	 * 
	 */
	@Test
	public void testAppendGeneralID()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode r = d.getJDFRoot();
		JDFGeneralID gi1 = r.setGeneralID("foo", "bar");
		Assert.assertEquals(gi1.getNextSiblingElement(), r.getAuditPool());
		JDFGeneralID gi2 = r.appendGeneralID("foo2", "bar2");
		Assert.assertEquals(gi1.getNextSiblingElement(), gi2);
		Assert.assertEquals(gi2.getNextSiblingElement(), r.getAuditPool());
	}

	/**
	 * 
	 */
	@Test
	public void testCopyElement()
	{
		final JDFDoc d = new JDFDoc("d1");
		final JDFElement e = (JDFElement) d.getRoot();
		final JDFDoc d2 = new JDFDoc("d2");
		final JDFElement e2 = (JDFElement) d2.getRoot();
		final KElement e3 = e.copyElement(e2, null);
		final JDFParser p = new JDFParser();
		final JDFDoc dp = p.parseString("<Device xmlns=\"www.CIP4.org/JDFSchema_1_1\"/>");
		final KElement ep = dp.getRoot();
		final KElement e4 = e.copyElement(ep, null);
		Assert.assertEquals(e4.hasAttribute("xmlns"), ep.hasAttribute("xmlns"));
		Assert.assertEquals(e3.getNamespaceURI(), e.getNamespaceURI());
		Assert.assertFalse(d.toString().indexOf("xmlns=\"\"") >= 0);

	}

	/**
	 * 
	 *
	 */
	@Test
	public void testGetElement_KElement()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode root = d.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) root.addResource("ExposedMedia", null, EnumUsage.Input, null, null, null, null);
		final JDFMedia m = xm.appendMedia();
		m.makeRootResource(null, null, true);
		Assert.assertNull(xm.getElement_KElement("Media", null, 0));
		Assert.assertNotNull(xm.getElement_JDFElement("Media", null, 0));
	}

	/**
	 * 
	 *
	 */
	@Test
	public void testGetElement_JDFElement()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode root = d.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) root.addResource("ExposedMedia", null, EnumUsage.Input, null, null, null, null);
		final JDFMedia m = xm.appendMedia();
		final JDFResource r = m.makeRootResource(null, null, true);
		Assert.assertNull(xm.getElement_KElement("Media", null, 0));
		Assert.assertNotNull(xm.getElement_JDFElement("Media", null, 0));
		Assert.assertEquals(xm.getElement_JDFElement("Media", null, 0), r);
		Assert.assertEquals(xm.getElement_JDFElement("Media", null, -1), r);
	}

	/**
	 * 
	 *
	 */
	@Test
	public void testGetChildElementVector_KElement()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode root = d.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) root.addResource("ExposedMedia", null, EnumUsage.Input, null, null, null, null);
		final JDFMedia m = xm.appendMedia();
		m.makeRootResource(null, null, true);
		Assert.assertEquals(xm.getChildElementVector_KElement("Media", null, null, true, -1).size(), 0);
		Assert.assertEquals(xm.getChildElementVector_JDFElement("Media", null, null, true, -1, true).size(), 1);
	}

	private void _setUp()
	{
		// setup the fixture
		final String xmlFile = "bookintent.jdf";

		// test jdf functions
		// ==================
		final JDFParser p = new JDFParser();
		m_jdfDoc = p.parseFile(sm_dirTestData + xmlFile);

		Assert.assertTrue(sm_dirTestData + xmlFile + ": Parse Error", m_jdfDoc != null);

		m_jdfRoot = (JDFNode) m_jdfDoc.getRoot();
		m_kElement = m_jdfRoot.getChildByTagName("Dimensions", "", 0, null, false, true);
		m_jdfElement = (JDFElement) m_kElement;

	}

	/**
	 * 
	 */
	@Test
	public void testNameSpaceElement()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode root = doc.getJDFRoot();
		root.setType("foo:bar", false);
		String fooNS = "www.foo.com";
		root.addNameSpace("foo", fooNS);
		final JDFResource r = root.addResource("foo:res", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
		final JDFResourceLink rl = root.getLink(r, null);
		rl.setPartMap(new JDFAttributeMap("Side", "Front"));
		Assert.assertEquals(rl.toString().indexOf("xmlns=\"\""), -1);
		Assert.assertEquals(rl.getPart(0).toString().indexOf("xmlns=\"\""), -1);
		Assert.assertEquals(r.getNamespaceURI(), fooNS);
		Assert.assertEquals(rl.getNamespaceURI(), fooNS);
		Assert.assertEquals(rl.getElement("Part").getNamespaceURI(), root.getNamespaceURI());
	}

	/**
	 * 
	 */
	@Test
	public void testRemoveExtensions()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.appendElement("a:b", "a.com");
		n.getAuditPool().appendElement("a:b", "a.com");
		n.getAuditPool().setAttribute("a:b", "c", "a.com");
		Assert.assertTrue(n.toString().indexOf("a:") > 0);
		n.removeExtensions();
		Assert.assertEquals(n.toString().indexOf("a:"), -1);
	}

	/**
	 * 
	 */
	@Test
	public void testRemoveChild()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		final JDFExposedMedia xmpl = (JDFExposedMedia) n.appendMatchingResource("ExposedMedia", EnumProcessUsage.Plate, null);
		final JDFExposedMedia xmpr = (JDFExposedMedia) n.appendMatchingResource("ExposedMedia", EnumProcessUsage.Proof, null);
		JDFMedia m = xmpr.appendMedia();
		Assert.assertNotNull(xmpr.getMedia());
		m.setID("id1");
		final KElement t1 = n.getTarget("id1", "ID");
		m = (JDFMedia) m.makeRootResource(null, null, true);
		Assert.assertEquals(t1, m);
		Assert.assertNotNull(xmpr.getMedia());
		xmpl.refElement(m);
		Assert.assertNotNull(xmpl.getMedia());
		final JDFResourcePool rp = n.getResourcePool();
		Assert.assertNotNull(rp.getElement("Media"));
		xmpl.removeChild("Media", null, 0);
		Assert.assertNull(xmpl.getMedia());
		Assert.assertNotNull(rp.getElement("Media"));
		xmpr.removeChildren("Media", null, null);
		Assert.assertNull(xmpr.getMedia());
		Assert.assertNotNull(rp.getElement("Media"));
	}

	/**
	 * 
	 */
	@Test
	public void testGetGeneralID()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setGeneralID("Foo", "Bar1");
		n.appendGeneralID("Foo", "Bar2");
		Assert.assertEquals(n.getGeneralID("Foo", 0), "Bar1");
		Assert.assertEquals(n.getGeneralID("Foo", 1), "Bar2");
		Assert.assertEquals(n.getGeneralID("Foo", -2), "Bar1");
		Assert.assertEquals(n.getGeneralID("Foo", -1), "Bar2");
		Assert.assertNull(n.getGeneralID("Foo", 3));
		Assert.assertNull(n.getGeneralID("Foo", -3));
	}

	/**
	* 
	*/
	@Test
	public void testGetHRefs()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		final JDFExposedMedia xmpl = (JDFExposedMedia) n.appendMatchingResource("ExposedMedia", EnumProcessUsage.Plate, null);
		final JDFExposedMedia xmpr = (JDFExposedMedia) n.appendMatchingResource("ExposedMedia", EnumProcessUsage.Proof, null);
		JDFMedia m = xmpr.appendMedia();
		Assert.assertNotNull(xmpr.getMedia());
		m.setID("id1");
		final KElement t1 = n.getTarget("id1", "ID");
		m = (JDFMedia) m.makeRootResource(null, null, true);
		Assert.assertEquals(t1, m);
		Assert.assertTrue(n.getHRefs(null, true, false).contains("id1"));
		Assert.assertTrue(xmpr.getHRefs(null, true, false).contains("id1"));
		Assert.assertFalse(xmpl.getHRefs(null, true, false).contains("id1"));
		Assert.assertTrue(n.getHRefs(null, true, true).contains("id1"));
		Assert.assertTrue(xmpr.getHRefs(null, true, true).contains("id1"));
		Assert.assertFalse(xmpl.getHRefs(null, true, true).contains("id1"));
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testFixVersion()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.Bundling);
		final JDFProcessRun pr = n.getAuditPool().addProcessRun(EnumNodeStatus.Completed, null, null);
		pr.setAttribute("Duration", "PT90S", null);
		Assert.assertEquals(pr.getAttribute("Duration"), "PT90S");
		n.setAttribute("foo3", "a~.doc");
		n.fixVersion(null);
		Assert.assertEquals(pr.getAttribute("Duration"), "PT1M30S");
		Assert.assertEquals(n.getAttribute("foo3"), "a~.doc");

	}

	// //////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testDefaultVersion()
	{

		JDFDoc doc = new JDFDoc("JDF");
		JDFNode n = doc.getJDFRoot();
		Assert.assertEquals(n.getVersion(true), defaultVersion);
		JDFElement.setDefaultJDFVersion(EnumVersion.Version_1_2);
		n.setType("ProcessGroup", true);
		n = n.addJDFNode("Combined");
		Assert.assertEquals(n.getVersion(true), defaultVersion);

		doc = new JDFDoc("JDF");
		n = doc.getJDFRoot();
		Assert.assertEquals(n.getVersion(true), EnumVersion.Version_1_2);
		n.setType("ProcessGroup", true);
		n = n.addJDFNode("Combined");
		Assert.assertEquals(n.getVersion(true), EnumVersion.Version_1_2);

		doc = new JDFDoc("JMF");
		final JDFJMF jmf = doc.getJMFRoot();
		Assert.assertEquals(jmf.getVersion(true), EnumVersion.Version_1_2);

	}

	/**
	 * 
	 */
	@Test
	public void testEvaluateXY()
	{
		EnumXYRelation xyR = EnumXYRelation.eq;
		Assert.assertTrue("eq", xyR.evaluateXY(2, 2, 0, 0));
		Assert.assertTrue("eq", xyR.evaluateXY(1.9, 2, 0.1, 0.1));
		Assert.assertFalse("eq", xyR.evaluateXY(1.9, 2, 0.0, 0.15));
		Assert.assertTrue("eq", xyR.evaluateXY(1.9, 2, 0.1, 0.0));

		xyR = EnumXYRelation.ne;
		Assert.assertFalse("ne", xyR.evaluateXY(2, 2, 0, 0));
		Assert.assertFalse("ne", xyR.evaluateXY(1.9, 2, 0.1, 0.1));
		Assert.assertTrue("ne", xyR.evaluateXY(1.9, 2, 0.0, 0.15));
		Assert.assertFalse("ne", xyR.evaluateXY(1.9, 2, 0.1, 0.0));

		xyR = EnumXYRelation.gt;
		Assert.assertTrue("gt", xyR.evaluateXY(3, 2, 0, 0));
		Assert.assertTrue("gt", xyR.evaluateXY(1.9, 2, 0.2, 0.2));
		Assert.assertFalse("gt", xyR.evaluateXY(2.00, 2, 0.0, 0.0));
		Assert.assertTrue("gt", xyR.evaluateXY(1.95, 2, 0.1, 0.0));

		xyR = EnumXYRelation.lt;
		Assert.assertTrue("lt", xyR.evaluateXY(1.9, 2, 0.0, 0.0));

		xyR = EnumXYRelation.le;
		Assert.assertTrue("le", xyR.evaluateXY(1.9, 2, 0.0, 0.0));
		Assert.assertTrue("le", xyR.evaluateXY(2.0, 2, 0.0, 0.0));
		Assert.assertFalse("le", xyR.evaluateXY(3.0, 2, 0.0, 0.0));
	}

	// //////////////////////////////////////////////////////////////////////
	/**
	 * Method testGenerateDotID.
	 * 
	 */
	@Test
	public void testGenerateDotID()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode e = doc.getJDFRoot();
		final String dotID = e.generateDotID("foo", null);
		e.setAttribute("foo", dotID, null);
		Assert.assertNotNull(dotID);
		Assert.assertTrue(dotID.startsWith("n"));
		JDFNode e2 = (JDFNode) e.appendElement("JDF", null);
		String generateDotID = e2.generateDotID("foo", null);
		e2.setAttribute("foo", generateDotID, null);
		Assert.assertEquals(generateDotID, dotID + ".1");

		JDFNode e3 = (JDFNode) e2.appendElement("JDF", null);
		generateDotID = e3.generateDotID("foo", null);
		e3.setAttribute("foo", generateDotID, null);
		Assert.assertEquals(generateDotID, dotID + ".1.1");
		e3 = (JDFNode) e2.appendElement("JDF", null);
		generateDotID = e3.generateDotID("foo", null);
		e3.setAttribute("foo", generateDotID, null);
		Assert.assertEquals(generateDotID, dotID + ".1.2");

		e2.setAttribute("foo", "whatever", null);
		e2 = (JDFNode) e.appendElement("JDF", null);
		generateDotID = e2.generateDotID("foo", null);
		e2.setAttribute("foo", generateDotID, null);
		Assert.assertEquals(generateDotID, dotID + ".2");
		for (int i = 3; i < 22; i++)
		{
			e2 = (JDFNode) e.appendElement("JDF", null);
			generateDotID = e2.generateDotID("foo", null);
			e2.setAttribute("foo", generateDotID, null);
			Assert.assertEquals(generateDotID, dotID + "." + String.valueOf(i));
		}
	}

	/**
	 * Method testIncludesMatchingAttribute.
	 * 
	 */
	@Test
	public void testIncludesMatchingAttribute()
	{
		_setUp();

		Assert.assertTrue("isInside (600 800) = ", m_jdfElement.includesMatchingAttribute("Range", "600 800", AttributeInfo.EnumAttributeType.XYPairRangeList));
		Assert.assertFalse("isOutside(500 700) = ", m_jdfElement.includesMatchingAttribute("Range", "500 700", AttributeInfo.EnumAttributeType.XYPairRangeList));

		final JDFDoc d = new JDFDoc("JDF");
		final JDFElement e = d.getJDFRoot();
		e.setAttribute("abc", "a b c");
		Assert.assertTrue("b", e.includesMatchingAttribute("abc", "a", EnumAttributeType.NMTOKENS));
		Assert.assertTrue("b", e.includesMatchingAttribute("abc", "b", EnumAttributeType.NMTOKENS));
		Assert.assertTrue("b", e.includesMatchingAttribute("abc", "c", EnumAttributeType.NMTOKENS));
		Assert.assertTrue("b", e.includesMatchingAttribute("abc", "a c", EnumAttributeType.NMTOKENS));
		Assert.assertFalse("b", e.includesMatchingAttribute("abc", "d", EnumAttributeType.NMTOKENS));
		e.setAttribute("intlist", "-1 3 5");
		Assert.assertTrue(e.includesMatchingAttribute("intlist", "-1", EnumAttributeType.IntegerList));
		Assert.assertTrue(e.includesMatchingAttribute("intlist", "3", EnumAttributeType.IntegerList));
		Assert.assertTrue(e.includesMatchingAttribute("intlist", "5", EnumAttributeType.IntegerList));
		Assert.assertFalse(e.includesMatchingAttribute("intlist", "4", EnumAttributeType.IntegerList));
		Assert.assertFalse(e.includesMatchingAttribute("intlist", "8", EnumAttributeType.IntegerList));
	}

	/**
	 * Method testChildElementVector.
	 * 
	 */
	@Test
	public void testGetRefElement()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFMedia m = (JDFMedia) n.addResource("Media", null);
		final JDFMedia m1 = (JDFMedia) m.addPartition(EnumPartIDKey.Location, "T1");
		final JDFMedia m2 = (JDFMedia) m.addPartition(EnumPartIDKey.Location, "T2");
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource("ExposedMedia", null);
		Assert.assertNull(xm.getRefElement(m1));
		final JDFRefElement re = xm.refElement(m2);
		Assert.assertEquals(xm.getRefElement(m2), re);
		Assert.assertEquals(xm.getRefElement(m2), re);
		Assert.assertEquals(xm.getMedia(), m2);
		Assert.assertNull(xm.getRefElement(m1));
		Assert.assertNull(xm.getRefElement(m));

	}

	/**
	 * Method testChildElementVector.
	 * 
	 */
	@Test
	public void testGetCreateElement()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFMedia m = (JDFMedia) n.addResource("Media", null);
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource("ExposedMedia", null);
		xm.refMedia(m);
		Assert.assertEquals(xm.getCreateElement("Media"), m);
	}

	/**
	 * Method testChildElementVector.
	 * 
	 */
	@Test
	public void testGetCreateRefElement()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFMedia m = (JDFMedia) n.addResource("Media", null);
		final JDFMedia m1 = (JDFMedia) m.addPartition(EnumPartIDKey.Location, "T1");
		final JDFMedia m2 = (JDFMedia) m.addPartition(EnumPartIDKey.Location, "T2");
		final JDFExposedMedia xm = (JDFExposedMedia) n.addResource("ExposedMedia", null);
		Assert.assertNull(xm.getRefElement(m1));
		for (int i = 0; i < 10; i++)
		{
			final JDFRefElement re = xm.getCreateRefElement(m2);
			Assert.assertEquals(xm.getRefElement(m2), re);
			Assert.assertEquals(xm.getRefElement(m2), re);
			Assert.assertEquals(xm.getMedia(), m2);
			Assert.assertNull(xm.getRefElement(m1));
			Assert.assertNull(xm.getRefElement(m));
			Assert.assertEquals(xm.numChildElements("MediaRef", null), 1);
		}
		for (int i = 0; i < 10; i++)
		{
			final JDFRefElement re = xm.getCreateRefElement(m2);
			Assert.assertEquals(xm.getRefElement(m2), re);
			Assert.assertEquals(xm.getCreateRefElement(m2), re);
			xm.getCreateRefElement(m);
			xm.getCreateRefElement(m1);
			Assert.assertEquals(xm.numChildElements("MediaRef", null), 3);
		}

	}

	/**
	 * Method testChildElementVector.
	 * 
	 */
	@Test
	public void testGetChildElementVector()
	{
		_setUp();
		VElement velem = m_jdfRoot.getChildElementVector(null, null, null, true, 0, false);
		Assert.assertEquals(velem.size(), 5);
		final KElement elem = velem.elementAt(0);
		Assert.assertEquals(elem.getNodeName(), "AuditPool");
		velem = m_jdfRoot.getChildElementVector(null, null, null, true, 3, false);
		Assert.assertEquals(velem.size(), 3);
	}

	/**
	 * Method testChildElementVector.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetChildElementVector_or() throws Exception
	{
		final JDFDoc d = new JDFDoc("AmountPool");
		final JDFAmountPool ap = (JDFAmountPool) d.getRoot();
		final JDFAttributeMap partMap = new JDFAttributeMap("a", "a1");
		partMap.put("b", "b1");
		final JDFPartAmount pa1 = ap.appendPartAmount();
		pa1.setAttributes(partMap);
		partMap.put("a", "a2");
		partMap.put("b", "b2");
		final JDFPartAmount pa2 = ap.appendPartAmount();
		pa2.setAttributes(partMap);
		VElement v = ap.getChildElementVector(ElementName.PARTAMOUNT, null, partMap, false, 0, false);
		Assert.assertEquals(v.size(), 1);
		partMap.put("b", "b1");
		v = ap.getChildElementVector(ElementName.PARTAMOUNT, null, partMap, false, 0, false);
		Assert.assertEquals(v.size(), 2);

	}

	/**
	 * Method testChildElementVector.
	 * 
	 */
	@Test
	public void testGetParentJDFStatic()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFElement root = d.getJDFRoot();
		Assert.assertNull(JDFElement.getParentJDF(root));
		Assert.assertNull(JDFElement.getParentJDF(null));
		KElement k = root.appendElement("NodeInfo");
		Assert.assertEquals(root, JDFElement.getParentJDF(k));
		k = k.appendElement("foo:Bar", "www.foo.com");
		Assert.assertEquals(root, JDFElement.getParentJDF(k));
		k = root.appendElement("JDF");
		Assert.assertEquals(root, JDFElement.getParentJDF(k));
	}

	/**
	 * 
	 */
	@Test
	public void testGetSettingsPolicy()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		Assert.assertNull(n.getSettingsPolicy(false));
		final JDFAuditPool ap = n.getAuditPool();
		Assert.assertNull(ap.getSettingsPolicy(true));
		n.setSettingsPolicy(EnumSettingsPolicy.MustHonor);
		Assert.assertEquals(ap.getSettingsPolicy(true), EnumSettingsPolicy.MustHonor);
	}

	/**
	 * 
	 */
	@Test
	public void testGetSchemaVersion()
	{
		Assert.assertEquals(JDFElement.getSchemaURL(), "http://www.CIP4.org/JDFSchema_1_1");
		Assert.assertEquals(JDFElement.getSchemaURL(1, 3), "http://www.CIP4.org/JDFSchema_1_1");
		Assert.assertEquals(JDFElement.getSchemaURL(2, 0), "http://www.CIP4.org/JDFSchema_2_0");
	}

	/**
	 * 
	 */
	@Test
	public void testGetValueForNewAttribute()
	{
		JDFElement e = new JDFDoc("JMF").getJMFRoot();
		Assert.assertTrue(JDFElement.getValueForNewAttribute(e, "ID").startsWith("I"));
	}

	/**
	 * 
	 */
	@Test
	public void testOrientationMap()
	{
		Map<String, EnumOrientation> map = EnumOrientation.getEnumMap();
		Assert.assertEquals(map.get("Flip0"), EnumOrientation.Flip0);
	}

	/**
	 * 
	 */
	@Test
	public void testGetParentJDFNode()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType("ProcessGroup", true);
		final JDFNode n2 = n.addJDFNode("Scanning");
		Assert.assertEquals("n2.parent n", n2.getParentJDF(), n);
		Assert.assertNull("n parent", n.getParentJDF());
		JDFAuditPool ap = n.getCreateAuditPool();
		Assert.assertEquals("ap.parent n", ap.getParentJDF(), n);
		ap = n2.getCreateAuditPool();
		Assert.assertEquals("ap.parent n2", ap.getParentJDF(), n2);
		Assert.assertEquals("a.parent n2", ap.addCreated("me", n2).getParentJDF(), n2);

	}

	/**
	 * Method testGetElementByID.
	 * 
	 */
	@Test
	public void testGetElementByID()
	{
		_setUp();
		final KElement kelem = m_jdfRoot.getChildWithAttribute("*", "ID", "*", "n0006", 0, true);
		Assert.assertTrue("kelem==null", kelem != null);
		if (kelem == null)
		{
			return; // soothe findbugs ;)
		}
		final String strAtrib = kelem.getAttribute("ID", "", "");
		Assert.assertTrue("ID!=n0006", strAtrib.equals("n0006"));

		// second try
		final KElement kelem2 = m_jdfRoot.getTarget("n0006", "ID");
		Assert.assertTrue("kelem2==null", kelem2 != null);
		if (kelem2 == null)
		{
			return; // soothe findbugs ;)
		}
		final String strAtrib2 = kelem2.getAttribute("ID", "", "");
		Assert.assertTrue("ID!=n0006", strAtrib2.equals("n0006"));

		// third try
		final KElement kelem3 = m_jdfRoot.getTarget("198", "Preferred");
		Assert.assertTrue("kelem3==null", kelem3 != null);
		if (kelem3 == null)
		{
			return; // soothe findbugs ;)
		}
		final String strAtrib3 = kelem3.getAttribute("Preferred", "", "");
		Assert.assertTrue("Preferred!=198", strAtrib3.equals("198"));

		// fourth try: GetChildWithAttribute does only find direct children but
		// no deep children
		final KElement kelem4 = m_jdfRoot.getChildWithAttribute("*", "Preferred", "*", "198", 0, true);
		Assert.assertTrue("kelem4!=null", kelem4 == null);
	}

	/**
	 * 
	 */
	@Test
	public void testIsCommentStatic()
	{

		_setUp();
		Assert.assertFalse("Bug: This is a comment!", m_kElement instanceof JDFComment);
		m_jdfElement.appendComment();
		m_kElement = m_jdfElement.getChildByTagName("Comment", "", 0, null, false, true);
		Assert.assertTrue("Bug: This is no comment!", m_kElement instanceof JDFComment);
	}

	/**
	 * 
	 */
	@Test
	public void testIsResourceStatic()
	{
		_setUp();
		m_kElement = m_jdfRoot.getChildByTagName("ComponentLink", "", 0, null, false, true);
		Assert.assertFalse("Bug: " + m_kElement.getNodeName() + " is a Resource!", m_kElement instanceof JDFResource);
		m_kElement = m_jdfRoot.getChildByTagName("SizeIntent", "", 0, null, false, true);
		Assert.assertTrue("Bug: " + m_kElement.getNodeName() + " is no Resource!", m_kElement instanceof JDFResource);
		m_kElement = m_jdfRoot.getChildByTagName("Dimensions", "", 0, null, false, true);
		Assert.assertFalse("Bug: " + m_kElement.getNodeName() + " is a Resource!", m_kElement instanceof JDFResource);
	}

	/**
	 * 
	 */
	@Test
	public void testIsResourceLinkStatic()
	{
		_setUp();
		m_kElement = m_jdfRoot.getChildByTagName("Dimensions", "", 0, null, false, true);
		Assert.assertFalse("Bug: This is a ResourceLink!", m_kElement instanceof JDFResourceLink);
		m_kElement = m_jdfRoot.getChildByTagName("ComponentLink", "", 0, null, false, true);
		Assert.assertTrue("Bug: This is no ResourceLink!", m_kElement instanceof JDFResourceLink);
	}

	/**
	 * 
	 */
	@Test
	public void testInheritedVersionInfo()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		JDFNode node = doc.getJDFRoot();
		node.setVersion(JDFElement.EnumVersion.Version_1_3);
		node.setType(JDFConstants.PROCESSGROUP, true);
		node = node.addJDFNode("Scanning");
		final JDFNodeInfo ni = node.appendNodeInfo();
		Assert.assertTrue(ni.hasAttribute(AttributeName.CLASS));
		Assert.assertEquals(ni.getVersion(true), EnumVersion.Version_1_3);
	}

	/**
	 * 
	 */
	@Test
	public void testMatchesPathKElement()
	{
		final JDFDoc doc = new JDFDoc("Test"); // make sure we call jdf methods
		final KElement root = doc.getRoot();
		final KElement a = root.appendElement("a");
		root.appendElement("b");
		final KElement a2 = root.appendElement("a");
		final KElement a3 = root.appendElement("a");
		a.setAttribute("att", "42");
		Assert.assertTrue(a.matchesPath("//a", false));
		Assert.assertTrue(a.matchesPath("/Test/a", false));
		Assert.assertTrue(a.matchesPath("/Test/a[1]", false));
		Assert.assertTrue(a.matchesPath("/Test/a[@att=\"42\"]", false));
		Assert.assertTrue(a2.matchesPath("/Test/a[2]", false));
		Assert.assertTrue(a3.matchesPath("/Test/a[3]", false));
		Assert.assertFalse(a3.matchesPath("/Test/a[@att=\"*\"]", false));
		Assert.assertTrue(a.matchesPath("/Test/a[@att=\"*\"]", false));
	}

	/**
	 * 
	 */
	@Test
	public void testMatchesPath()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		node.setType("Product", true);
		node.setVersion(JDFElement.EnumVersion.Version_1_3);
		JDFNodeInfo ni = node.appendNodeInfo();
		ni = (JDFNodeInfo) ni.addPartition(EnumPartIDKey.Run, "R1");
		final JDFContact c = (JDFContact) node.addResource(ElementName.CONTACT, null, null, null, null, null, null);
		ni.refElement(c);
		final JDFComChannel cc = (JDFComChannel) node.addResource(ElementName.COMCHANNEL, null, null, null, null, null, null);
		c.refElement(cc);
		Assert.assertTrue("contact", ni.getContact() == c);
		Assert.assertTrue("hasrefelement", ni.hasChildElement(ElementName.CONTACT, null));
		final JDFRefElement re = (JDFRefElement) ni.getElement("ContactRef");
		Assert.assertTrue("refelementok", re.getTarget() == c);
		Assert.assertTrue("comchannel", c.getComChannel(0) == cc);
		Assert.assertTrue("hasrefelement", c.hasChildElement(ElementName.COMCHANNEL, null));
		final JDFNode n2 = node.addProduct();
		final JDFNodeInfo ni2 = n2.appendNodeInfo();
		ni2.refElement(c);
		Assert.assertTrue("follow refs in matchespath", c.matchesPath("ResourcePool/NodeInfo/Contact", true));
		Assert.assertTrue("follow refs in matchespath", cc.matchesPath("ResourcePool/NodeInfo/Contact/ComChannel", true));
		Assert.assertTrue("follow refs in matchespath", cc.matchesPath("JDF/ResourcePool/NodeInfo/Contact/ComChannel", true));
		Assert.assertTrue("follow refs in matchespath", cc.matchesPath("/JDF/ResourcePool/NodeInfo/Contact/ComChannel", true));
		Assert.assertTrue("follow refs in matchespath", cc.matchesPath("JDF/JDF/ResourcePool/NodeInfo/Contact/ComChannel", true));
		Assert.assertTrue("follow refs in matchespath", cc.matchesPath("/JDF/JDF/ResourcePool/NodeInfo/Contact/ComChannel", true));
		Assert.assertTrue("follow refs in matchespath", cc.matchesPath("//JDF/ResourcePool/NodeInfo/Contact/ComChannel", true));
		Assert.assertTrue("follow * in matchespath", cc.matchesPath("/JDF/*/ResourcePool/NodeInfo/Contact/ComChannel", true));
		Assert.assertFalse("follow refs in matchespath", cc.matchesPath("JDF/JDF/JDF/ResourcePool/NodeInfo/Contact/ComChannel", true));
		Assert.assertFalse("follow refs in matchespath", cc.matchesPath("JDF/JDF/JDF/ResourcePool/NodeInfo/Contact/ComChannel", true));
		Assert.assertFalse("follow refs in matchespath", c.matchesPath("ResourcePool/NodeInfo/Contact/ComChannel", true));

	}

	/**
	 * 
	 */
	@Test
	public void testRefElement()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode node = doc.getJDFRoot();
		node.setType("Product", true);
		node.setVersion(JDFElement.EnumVersion.Version_1_2);
		final JDFNodeInfo ni = node.appendNodeInfo();
		ni.appendElement("foo:bar", "www.foo.com"); // want a non jdf ns element
		// to see if any class casts
		// occur
		JDFContact c = (JDFContact) node.addResource(ElementName.CONTACT, null, null, null, null, null, null);
		final VString vCTypes = new VString();
		vCTypes.add("Customer");
		c.setContactTypes(vCTypes);

		ni.refElement(c);
		final JDFComChannel cc = (JDFComChannel) node.addResource(ElementName.COMCHANNEL, null, null, null, null, null, null);
		c.refElement(cc);

		Assert.assertEquals("contact", ni.getChildWithMatchingAttribute(ElementName.CONTACT, "ContactTypes", null, "Customer", 0, true, null), c);
		Assert.assertEquals("contact", ni.getParentJDF().getChildWithAttribute(ElementName.CONTACT, "ContactTypes", null, "Customer", 0, false), c);

		Assert.assertEquals("contact", ni.getContact(), c);
		Assert.assertTrue("hasrefelement", ni.hasChildElement(ElementName.CONTACT, null));
		JDFRefElement re = (JDFRefElement) ni.getElement("ContactRef");
		Assert.assertTrue("refelementok", re.getTarget() == c);
		Assert.assertTrue("comchannel", c.getComChannel(0) == cc);
		Assert.assertTrue("hasrefelement", c.hasChildElement(ElementName.COMCHANNEL, null));
		final JDFNode n2 = node.addProduct();
		final JDFNodeInfo ni2 = n2.appendNodeInfo();
		ni2.refElement(c);
		Assert.assertTrue("follow refs in matchespath", c.matchesPath("NodeInfo/Contact", true));
		Assert.assertTrue("follow refs in matchespath", cc.matchesPath("NodeInfo/Contact/ComChannel", true));
		Assert.assertFalse("follow refs in matchespath", c.matchesPath("NodeInfo/Contact/ComChannel", true));

		Assert.assertTrue("contact 2", ni2.getContact() == c);
		Assert.assertTrue("hasrefelement 2", ni2.hasChildElement(ElementName.CONTACT, null));
		re = (JDFRefElement) ni2.getElement("ContactRef");
		Assert.assertTrue("refelementok 2", re.getTarget() == c);

		ni2.inlineRefElements(null, null, true);
		Assert.assertNull("get ref post inline", ni2.getElement("ContactRef"));
		Assert.assertNotNull("refElement has been removed", node.getResourcePool().getElement("Contact"));
		Assert.assertTrue("haselement 3", ni2.hasChildElement(ElementName.CONTACT, null));
		c = ni2.getContact();
		re = (JDFRefElement) c.getElement("ComChannelRef");
		Assert.assertTrue("refelementok 2", re.getTarget() == cc);
		ni2.inlineRefElements(null, null, false);
		Assert.assertNull("get ref post inline 2", ni2.getElement("ComChannelRef"));
		Assert.assertTrue("haselement 4", c.hasChildElement(ElementName.COMCHANNEL, null));

		ni.inlineRefElements(null, null, true);
		Assert.assertNull("get ref post inline", ni.getElement("ContactRef"));
		Assert.assertNull("refElement has been removed", node.getResourcePool().getElement("Contact"));
		Assert.assertTrue("haselement 3", ni.hasChildElement(ElementName.CONTACT, null));

		c = ni.getContact();
		c.makeRootResource(null, null, true);
		re = (JDFRefElement) ni.getElement("ContactRef");
		re.deleteRef(true);
		Assert.assertNull(c.getElement("ContactRef"));
	}

	/**
	 * 
	 */
	@Test
	public void testIsValid()
	{
		final File testData = new File(sm_dirTestData + "SampleFiles");
		Assert.assertTrue("testData dir", testData.isDirectory());
		final File[] fList = testData.listFiles();
		final JDFParser p = new JDFParser();
		final JDFParser p2 = new JDFParser();
		p2.m_SchemaLocation = sm_dirTestSchema + "JDF.xsd";

		for (int i = 0; i < fList.length; i++)
		{
			final File file = fList[i];
			// skip directories in CVS environments
			if (file.isDirectory())
			{
				continue;
			}
			// skip non jdf
			if (!file.getPath().endsWith(".jdf") && !file.getPath().endsWith(".jmf") && !file.getPath().endsWith(".xml"))
			{
				continue;
			}

			System.out.println("Parsing: " + file.getPath());
			JDFDoc jdfDoc = p.parseFile(file.getPath());
			Assert.assertTrue("parse ok", jdfDoc != null);

			JDFElement e = null;
			if (jdfDoc != null)
			{
				e = (JDFElement) jdfDoc.getRoot();
				Assert.assertTrue("valid doc: " + file.getPath(), e.isValid(EnumValidationLevel.RecursiveComplete));
			}

			// now with schema validation
			jdfDoc = p2.parseFile(file.getPath());
			Assert.assertTrue("schema parse ok", jdfDoc != null);

			// TODO fix handling of prerelease default attributes
			if (jdfDoc != null)
			{
				e = (JDFElement) jdfDoc.getRoot();
				Assert.assertTrue("valid doc: " + file.getPath(), e.isValid(EnumValidationLevel.RecursiveComplete));
			}
		}
	}

	// //////////////////////////////////////////////////////////////////////////
	// ///////

	/**
	 * 
	 */
	@Test
	public void testIsInvalid()
	{
		final File testData = new File(sm_dirTestData + "BadSampleFiles");
		Assert.assertTrue("testData dir", testData.isDirectory());
		final File[] fList = testData.listFiles();
		final JDFParser p = new JDFParser();
		final JDFParser p2 = new JDFParser();
		p2.m_SchemaLocation = sm_dirTestSchema + "JDF.xsd";

		for (int i = 0; i < fList.length; i++)
		{
			final File file = fList[i];
			// skip directories in CVS environments
			if (file.isDirectory())
			{
				continue;
			}

			// skip schema files
			if (file.getPath().endsWith(".xsd"))
			{
				continue;
			}

			System.out.println("Parsing: " + file.getPath());
			JDFDoc jdfDoc = p.parseFile(file.getPath());
			Assert.assertTrue("parse ok", jdfDoc != null);
			JDFElement e = null;
			if (jdfDoc != null)
			{
				e = (JDFElement) jdfDoc.getRoot();
				Assert.assertFalse("valid doc: " + file.getPath(), e.isValid(EnumValidationLevel.RecursiveComplete));
			}

			// now with schema validation
			jdfDoc = p2.parseFile(file.getPath());
			Assert.assertTrue("schema parse ok", jdfDoc != null);
			// TODO fix handling of prerelease default attributes
			if (jdfDoc != null)
			{
				e = (JDFElement) jdfDoc.getRoot();
				Assert.assertFalse("valid doc: " + file.getPath(), e.isValid(EnumValidationLevel.RecursiveComplete));
			}
		}
	}

	/**
	 * @throws CloneNotSupportedException 
	 * 
	 */
	@Test
	public void testCloneNewDoc() throws CloneNotSupportedException
	{
		final XMLDoc doc = new JDFDoc("JDF");
		KElement e = doc.getRoot();
		KElement d = e.getCreateXPathElement("a/b/c/d[3]");
		KElement c_clone = d.getParentNode_KElement().cloneNewDoc();
		Assert.assertNotNull(c_clone);
		Assert.assertTrue(c_clone.isEqual(c_clone.cloneNewDoc()));
		Assert.assertNotSame(c_clone, c_clone.cloneNewDoc());
		Assert.assertNotSame(c_clone.getOwnerDocument(), c_clone.cloneNewDoc().getOwnerDocument());

		KElement rootClone = e.cloneNewDoc();
		Assert.assertTrue(rootClone.isEqual(e.cloneNewDoc()));
		Assert.assertNotSame(rootClone, e.cloneNewDoc());
		Assert.assertNotSame(rootClone.getOwnerDocument(), e.cloneNewDoc().getOwnerDocument());
		Assert.assertTrue(rootClone.isEqual(e));
		Assert.assertNotSame(rootClone, e);
		Assert.assertNotSame(rootClone.getOwnerDocument(), e.getOwnerDocument());
	}

	/**
	 * 
	 */
	@Test
	public void testCache()
	{
		final JDFDoc d1 = new JDFDoc("d1");
		final JDFDoc d2 = new JDFDoc("d2");
		Assert.assertNotNull(d1.getXMLDocUserData());
		Assert.assertNotNull(d2.getXMLDocUserData());
		Assert.assertTrue(d1.getXMLDocUserData().getIDCache());
		final KElement e1 = d1.getRoot();
		final KElement e2 = d2.getRoot();
		for (int i = 0; i < 4; i++)
		{
			e1.setXPathAttribute("e2/e3" + String.valueOf(i) + "/@ID", "i1" + String.valueOf(i));
			e2.setXPathAttribute("e2/e3" + String.valueOf(i) + "/@ID", "i2" + String.valueOf(i));
		}
		KElement e13 = e2.getTarget("i13", "ID");
		Assert.assertNull(e13);
		e13 = e1.getTarget("i13", "ID");
		Assert.assertNotNull(e13);
		Assert.assertEquals(d1, e1.getOwnerDocument_KElement());
		KElement e23 = e2.getTarget("i23", "ID");
		Assert.assertNotNull(e23);
		Assert.assertEquals(d2, e2.getOwnerDocument_KElement());
		e1.moveElement(e23, null);
		e23 = e2.getTarget("i23", "ID");
		Assert.assertNull(e23);
		e23 = e1.getTarget("i23", "ID");
		Assert.assertNotNull(e23);
		Assert.assertEquals(d1, e23.getOwnerDocument_KElement());
		e23.deleteNode();
		e23 = e1.getTarget("i23", "ID");
		Assert.assertNull(e23);

		e23 = e2.getTarget("i22", "ID");
		Assert.assertNotNull(e23);
		final KElement e24 = e23.renameElement("fnarf", null);
		Assert.assertEquals(e24, e23);
		Assert.assertEquals(e24.getNodeName(), "fnarf");
		Assert.assertEquals(e24.getLocalName(), "fnarf");
		Assert.assertEquals(e24, e2.getTarget("i22", "ID"));

	}

	/**
	 * Method testGetElementByID.
	 * 
	 */
	@Test
	public void testGetDeepElementByID()
	{
		final String xmlFile = "bookintent.jdf";

		final JDFParser p = new JDFParser();
		final JDFDoc jdfDoc = p.parseFile(sm_dirTestData + xmlFile);

		final JDFNode jdfRoot = (JDFNode) jdfDoc.getRoot();
		final XMLDocUserData ud = jdfRoot.getXMLDocUserData();

		// first try
		final KElement kelem1 = JDFElement.getDeepElementByID(jdfRoot, "ID", "n0006", null, ud);
		Assert.assertNotNull("kelem1==null", kelem1);
		Assert.assertEquals("id", kelem1.getAttribute("ID"), "n0006");

		// second try
		final KElement kelem2 = JDFElement.getDeepElementByID(jdfRoot, "Preferred", "198", null, null);
		Assert.assertTrue("kelem2==null", kelem2 != null);
		if (kelem2 == null)
		{
			return; // soothe findbugs ;)
		}
		final String strAtrib2 = kelem2.getAttribute("Preferred", "", "");
		Assert.assertTrue("Preferred!=198", strAtrib2.equals("198"));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testIncrementUniqueID()
	{
		KElement.setLongID(false);
		KElement.uniqueID(1);
		Assert.assertTrue(KElement.uniqueID(0).endsWith("" + 2));
		KElement.uniqueID(10000);
		Assert.assertTrue(KElement.uniqueID(0).endsWith("" + 10001));
		KElement.uniqueID(-5000);
		Assert.assertTrue("neg=increment", KElement.uniqueID(0).endsWith("" + 15003));
	}

	/**
	 * 
	 */
	@Test
	public void testUniqueID()
	{
		final HashSet<String> m = new HashSet<String>();
		KElement.uniqueID(99998);
		for (int i = 0; i < 200000; i++)
		{
			final String s = KElement.uniqueID(0);
			if (m.contains(s))
			{
				Assert.fail("oops");
			}
			m.add(s);
		}
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testInvalidNameSpace()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFElement e = doc.getJDFRoot();
		String s = JDFConstants.JDFNAMESPACE;
		s = StringUtil.replaceString(s, "_1_1", "_1_3");
		JDFElement e2 = (JDFElement) e.appendElement("ResourcePool", s);
		Assert.assertTrue(e2.getInvalidAttributes(EnumValidationLevel.Incomplete, true, 9999).contains("xmlns"));
		e2 = (JDFElement) e.appendElement("ResourceLinkPool", "www.cip4.org");
		Assert.assertTrue(e2.getInvalidAttributes(EnumValidationLevel.Incomplete, true, 9999).contains("xmlns"));

	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testAppendAnchor()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFElement e = doc.getJDFRoot();
		final HashSet<String> m = new HashSet<String>();
		final KElement e2 = e.appendElement("e2");
		for (int i = 0; i < 10000; i++)
		{
			final JDFElement appendElement = (JDFElement) e2.appendElement("FooBar");
			final String s = appendElement.appendAnchor(null);
			if (m.contains(s))
			{
				Assert.fail("oops");
			}
			Assert.assertEquals(s, appendElement.getID());
			Assert.assertTrue(s.indexOf("..") < 0);
			m.add(s);
		}
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testVersions()
	{
		Assert.assertEquals(JDFVersions.getTheOffset(EnumVersion.Version_1_0), 0);
		Assert.assertEquals(JDFVersions.getTheOffset(EnumVersion.Version_1_2), 8);
	}

	/**
	 * 
	 */
	@Test
	public void testMinorVersions()
	{
		Assert.assertEquals(EnumVersion.Version_1_0.getMinorVersion(), 0);
		Assert.assertEquals(EnumVersion.Version_1_6.getMinorVersion(), 6);
		Assert.assertEquals(EnumVersion.Version_2_0.getMinorVersion(), 0);
	}

	/**
	 * 
	 */
	@Test
	public void testMajorVersions()
	{
		Assert.assertEquals(EnumVersion.Version_1_0.getMajorVersion(), 1);
		Assert.assertEquals(EnumVersion.Version_1_9.getMajorVersion(), 1);
		Assert.assertEquals(EnumVersion.Version_2_0.getMajorVersion(), 2);
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testSetEnumerationsAttribute()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFElement root = d.getJDFRoot();
		root.setEnumerationsAttribute("dummy", null, null);
		Assert.assertNull(root.getEnumerationsAttribute("dummy", null, EnumNodeStatus.Aborted, false));
		final Vector<EnumNodeStatus> v = new Vector<EnumNodeStatus>();
		v.add(EnumNodeStatus.Cleanup);
		v.add(EnumNodeStatus.Completed);
		root.setEnumerationsAttribute("dummy", v, null);
		Assert.assertEquals("round trip enumerations", root.getEnumerationsAttribute("dummy", null, EnumNodeStatus.Aborted, false), v);
	}

	/**
	 * @throws DataFormatException 
	 * 
	 */
	@Test
	public void testSetAttributeNumList() throws DataFormatException
	{
		final JDFNumberList nl = new JDFNumberList("1.11 2.22 3.33");
		final JDFDoc d = new JDFDoc("JDF");
		final JDFElement root = d.getJDFRoot();
		root.setAttribute("test", nl, null, 1);
		Assert.assertTrue(root.toXML().indexOf("1.1 2.2 3.3") > 0);
	}

	/**
	 * 
	 */
	@Test
	public void testSetXSIType()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFElement root = d.getJDFRoot();
		root.setXSIType("foo");
		Assert.assertFalse(root.toXML().contains("NS1"));
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testStatusEquals()
	{
		// test if the auto classes implement the correct status

		// compare EnumNodeStatus
		JDFAuditPool myAuditPool = null;

		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);

		final JDFNode jdfRoot = (JDFNode) jdfDoc.getRoot();
		Assert.assertTrue("No Root found", jdfRoot != null);
		if (jdfRoot == null)
		{
			return; // soothe findbugs ;)
		}

		final JDFAncestor ancestor = jdfRoot.appendAncestorPool().appendAncestor();
		ancestor.setStatus(EnumNodeStatus.Completed);

		myAuditPool = jdfRoot.getCreateAuditPool();
		final JDFPhaseTime phaseTime = myAuditPool.addPhaseTime(JDFElement.EnumNodeStatus.Completed, null, null);
		final JDFSpawned spawned = myAuditPool.addSpawned(jdfRoot, null, null, null, null);
		spawned.setStatus(JDFElement.EnumNodeStatus.Completed);

		Assert.assertEquals(spawned.getStatus(), phaseTime.getStatus());
		Assert.assertEquals(spawned.getStatus(), ancestor.getStatus());

		final JDFDoc jmfDoc = new JDFDoc(ElementName.JMF);

		final JDFJMF jmfRoot = jmfDoc.getJMFRoot();
		Assert.assertTrue("No Root found", jmfRoot != null);
		if (jmfRoot == null)
		{
			return; // soothe findbugs ;)
		}

		final JDFAcknowledge acknowledge = jmfRoot.appendAcknowledge();
		acknowledge.setType("PipePush"); // Type is required and its existance
		// is validated for messages
		final JDFJobPhase jobPhase = acknowledge.appendJobPhase();
		jobPhase.setStatus(EnumNodeStatus.Completed);

		final JDFMessage message = jmfRoot.appendMessageElement(EnumFamily.Command, null);
		message.setType("PipePush"); // Type is required and its existance is
		// validated for messages
		final JDFPipeParams pipeParams = message.appendPipeParams();
		pipeParams.setStatus(EnumNodeStatus.Completed);

		Assert.assertEquals(jobPhase.getStatus(), pipeParams.getStatus());
		Assert.assertEquals(spawned.getStatus(), pipeParams.getStatus());

		// compare EnumResStatus
		final JDFDoc responseDoc = new JDFDoc(ElementName.RESPONSE);
		final JDFResponse responseRoot = (JDFResponse) responseDoc.getRoot();
		Assert.assertTrue("No Root found", responseRoot != null);
		if (responseRoot == null)
		{
			return; // soothe findbugs ;)
		}

		responseRoot.setType(ElementName.RESOURCE);
		final JDFResourceInfo resInfo = responseRoot.appendResourceInfo();
		resInfo.setResStatus(EnumResStatus.Available);

		final JDFDoc commandDoc = new JDFDoc(ElementName.COMMAND);
		final JDFCommand commandRoot = (JDFCommand) commandDoc.getRoot();
		Assert.assertTrue("No Root found", commandRoot != null);
		if (commandRoot == null)
		{
			return; // soothe findbugs ;)
		}

		commandRoot.setType(ElementName.RESOURCE);
		final JDFResourceCmdParams resCmdParams = commandRoot.appendResourceCmdParams();
		resCmdParams.setResStatus(EnumResStatus.Available);

		Assert.assertEquals(resInfo.getStatus(), resCmdParams.getStatus());

		// check EnumQueueStatus
		final JDFDoc queueDoc = new JDFDoc(ElementName.QUEUE);
		final JDFQueue queueRoot = (JDFQueue) queueDoc.getRoot();
		Assert.assertTrue("No Root found", queueRoot != null);
		if (queueRoot == null)
		{
			return; // soothe findbugs ;)
		}

		queueRoot.setQueueStatus(EnumQueueStatus.Running);

		// check EnumQueueEntryStatus
		final JDFQueueEntry queueEntry = queueRoot.appendQueueEntry();
		queueEntry.setQueueEntryStatus(EnumQueueEntryStatus.Running);
	}

}