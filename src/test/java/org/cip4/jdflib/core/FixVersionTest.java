/*
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
package org.cip4.jdflib.core;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoApprovalDetails.EnumApprovalState;
import org.cip4.jdflib.auto.JDFAutoComChannel.EnumChannelType;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumISOPaperSubstrate;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.elementwalker.FixVersion;
import org.cip4.jdflib.goldenticket.IDPGoldenTicket;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.jmf.JMFBuilder;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFCreated;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFProofItem;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.JDFTool;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.process.JDFApprovalDetails;
import org.cip4.jdflib.resource.process.JDFApprovalSuccess;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFAssemblySection;
import org.cip4.jdflib.resource.process.JDFBendingParams;
import org.cip4.jdflib.resource.process.JDFComChannel;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContentMetaData;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionOp;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams;
import org.cip4.jdflib.util.CPUTimer;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class FixVersionTest extends JDFTestCaseBase
{
	private JDFNode n;

	/**
	 *
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		JDFDoc mDoc = new JDFDoc(ElementName.JDF);
		n = mDoc.getJDFRoot();
	}

	/**
	 *
	 */
	@Test
	public void testApprovalSuccess()
	{
		n.setType("Approval", true);
		JDFApprovalSuccess as = (JDFApprovalSuccess) n.appendMatchingResource(ElementName.APPROVALSUCCESS, EnumProcessUsage.AnyOutput, null);
		n.setVersion(EnumVersion.Version_1_2);
		as.appendContact();
		as.appendFileSpec();
		boolean bRet = n.fixVersion(EnumVersion.Version_1_3);
		Assertions.assertTrue(bRet, "fix ok");
		Assertions.assertNotNull(as.getApprovalDetails(0), "approvaldetails");
		bRet = n.fixVersion(EnumVersion.Version_1_2);
		Assertions.assertTrue(bRet, "fix ok");
		Assertions.assertNull(as.getApprovalDetails(0), "approvaldetails");
		bRet = n.fixVersion(EnumVersion.Version_1_3);
		Assertions.assertTrue(bRet, "fix ok");
		as = (JDFApprovalSuccess) n.getMatchingResource(ElementName.APPROVALSUCCESS, EnumProcessUsage.AnyOutput, null, 0);
		final JDFApprovalDetails ad = as.getApprovalDetails(0);
		ad.setApprovalState(EnumApprovalState.Rejected);
		bRet = n.fixVersion(EnumVersion.Version_1_2);
		Assertions.assertFalse(bRet, "fix not ok");
	}

	// //////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	public void testRRefs()
	{
		final JDFResourcePool rp = n.appendResourcePool();
		rp.setAttribute(AttributeName.RREFS, "a b", null);
		n.fixVersion(null);
		Assertions.assertFalse(rp.hasAttribute(AttributeName.RREFS));
	}

	/**
	 *
	 */
	@Test
	public void testNamespace()
	{
		final KElement ns = n.appendElement("foo:abc", "www.foobar.com");
		n.fixVersion(null);
		KElement ns2 = n.getElement("foo:abc");
		Assertions.assertEquals(ns, ns2);
		Assertions.assertEquals(ns2.getNamespaceURI(), "www.foobar.com");
		n.fixVersion(EnumVersion.Version_1_3);
		ns2 = n.getElement("foo:abc");
		Assertions.assertEquals(ns, ns2);
		Assertions.assertEquals(ns2.getNamespaceURI(), "www.foobar.com");
	}

	/**
	 *
	 */
	@Test
	public void testNamespaceRes()
	{
		final KElement ns = n.addResource("foo:abc", EnumResourceClass.Parameter, null, null, null, "www.foobar.com", null);
		n.fixVersion(null);
		KElement ns2 = n.getResourcePool().getElement("foo:abc");
		Assertions.assertEquals(ns, ns2);
		Assertions.assertEquals(ns2.getNamespaceURI(), "www.foobar.com");
		n.fixVersion(EnumVersion.Version_1_3);
		ns2 = n.getResourcePool().getElement("foo:abc");
		Assertions.assertEquals(ns, ns2);
		Assertions.assertEquals(ns2.getNamespaceURI(), "www.foobar.com");
	}

	/**
	 *
	 */
	@Test
	public void testNamespaceParse()
	{
		JDFNode n = JDFDoc.parseFile(sm_dirTestData + "fixns.jdf").getJDFRoot();
		n.fixVersion(null);
		KElement ns2 = n.getResourcePool().getElement("foo:myresource");
		Assertions.assertEquals(ns2.getNamespaceURI(), "http://www.foo.com/schema");
		n.fixVersion(EnumVersion.Version_1_3);
		ns2 = n.getResourcePool().getElement("foo:myresource");
		Assertions.assertEquals(ns2.getNamespaceURI(), "http://www.foo.com/schema");
	}

	/**
	 *
	 */
	@Test
	public void testNamespaceRetain()
	{
		JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.addNameSpace("foo", "http://www.foo.com/schema");
		n.fixVersion(EnumVersion.Version_1_3);
		Assertions.assertEquals(n.getAttribute("xmlns:foo"), "http://www.foo.com/schema");
	}

	/**
	 *
	 */
	@Test
	public void testNamespaceRetain2()
	{
		JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final KElement e = n.appendElement("foo:" + this.getClass().getSimpleName(), "http://www.foo.com/schema");
		final JDFNode n0 = (JDFNode) e.appendElement(ElementName.JDF);
		n0.appendStatusPool();
		n.fixVersion(EnumVersion.Version_1_3);
		Assertions.assertNotNull(n0.getStatusPool());
	}

	/**
	 *
	 */
	@Test
	public void testNamespaceRetainZappDeprecated()
	{
		JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.addNameSpace("foo", "http://www.foo.com/schema");
		final FixVersion fv = new FixVersion(EnumVersion.Version_1_5);
		fv.setZappDeprecated(true);
		fv.convert(n);
		Assertions.assertEquals(n.getAttribute("xmlns:foo"), "http://www.foo.com/schema");
	}

	/**
	 *
	 */
	@Test
	public void testICSVersions()
	{
		final JDFDevice r = (JDFDevice) n.addResource("Device", EnumUsage.Input);
		final VString ics0 = new VString("Base_L2-1.2 MIS_L3-1.2 PerCP_L2_1.2", null);
		final VString ics1 = new VString("Base_L2-1.4 MIS_L3-1.4 PerCP_L2_1.4", null);
		r.setICSVersions(ics0);
		final FixVersion f0 = new FixVersion(EnumVersion.Version_1_4);
		f0.walkTree(n, null);
		Assertions.assertEquals(r.getICSVersions(), ics0);
		final FixVersion f1 = new FixVersion(EnumVersion.Version_1_4);
		f1.setFixICSVersions(true);
		f1.walkTree(n, null);
		Assertions.assertEquals(r.getICSVersions(), ics1);
	}

	/**
	 *
	 */
	@Test
	public void testJobID()
	{
		final FixVersion f1 = new FixVersion(EnumVersion.Version_1_4);
		n.removeAttribute(AttributeName.JOBID);
		f1.convert(n);
		Assertions.assertTrue(n.hasNonEmpty(AttributeName.JOBID));

	}

	/**
	 *
	 */
	@Test
	public void testJobPartID()
	{
		final FixVersion f1 = new FixVersion(EnumVersion.Version_1_7);
		n.setJobID("j1");
		n.removeAttribute(AttributeName.JOBPARTID);
		f1.convert(n);
		Assertions.assertEquals("P_j1", n.getJobPartID(true));

	}

	/**
	 *
	 */
	@Test
	public void testAudit()
	{
		final JDFAuditPool ap = n.getAuditPool();
		Assertions.assertNotNull(ap);
		final JDFCreated crea = (JDFCreated) ap.getAudit(0, EnumAuditType.Created, null, null);
		crea.setAgentName("Agent");
		crea.setAgentVersion("V1");
		final String agent = crea.getAgentName();
		crea.setAuthor(agent);
		Assertions.assertNotNull(agent);
		String author = crea.getAuthor();
		Assertions.assertNotNull(author);

		n.fixVersion(EnumVersion.Version_1_1);
		author = crea.getAuthor();
		Assertions.assertEquals(StringUtil.token(author, 1, "_|_"), agent);
		Assertions.assertTrue(author.startsWith(agent));
		String agent2 = crea.getAgentName();
		Assertions.assertEquals(agent2, "");

		n.fixVersion(EnumVersion.Version_1_3);
		author = crea.getAuthor();
		Assertions.assertEquals(author.indexOf("_|_"), -1);
		agent2 = crea.getAgentName();
		Assertions.assertEquals(agent, agent2);

		n.fixVersion(EnumVersion.Version_1_2);
		author = crea.getAuthor();
		Assertions.assertEquals(author.indexOf("_|_"), -1);
		agent2 = crea.getAgentName();
		Assertions.assertEquals(agent, agent2);
	}

	/**
	 *
	 */
	@Test
	public void testResourceStatus()
	{
		final JDFMedia m = (JDFMedia) n.addResource("Media", null, EnumUsage.Input, null, null, null, null);
		m.setResStatus(EnumResStatus.Available, true);
		Assertions.assertEquals(m.getResStatus(true), EnumResStatus.Available);
		Assertions.assertTrue(m.fixVersion(EnumVersion.Version_1_1));
		Assertions.assertEquals(m.getResStatus(true), EnumResStatus.Available);
		Assertions.assertTrue(m.fixVersion(EnumVersion.Version_1_3));
		Assertions.assertEquals(m.getResStatus(true), EnumResStatus.Available);
	}

	/**
	 *
	 */
	@Test
	public void testMultiConstruct()
	{
		final CPUTimer ct = new CPUTimer(false);
		for (int i = 0; i < 4; i++)
		{
			ct.start();
			final FixVersion v = new FixVersion(EnumVersion.Version_1_3);
			Assertions.assertNotNull(v);
			log.info(ct.toXML());
			ct.stop();
		}
	}

	/**
	 *
	 */
	@Test
	public void testConvert()
	{
		final JDFMedia m = (JDFMedia) n.addResource("Media", null, EnumUsage.Input, null, null, null, null);
		m.setResStatus(EnumResStatus.Available, true);
		Assertions.assertEquals(m.getResStatus(true), EnumResStatus.Available);
		Assertions.assertTrue(new FixVersion(EnumVersion.Version_1_1).convert(n));
		Assertions.assertEquals(n.getVersion(true), EnumVersion.Version_1_1);
	}

	/**
	 *
	 */
	@Test
	public void testCustomerMessage()
	{
		final JDFCustomerInfo ci = (JDFCustomerInfo) n.addResource(ElementName.CUSTOMERINFO, null, EnumUsage.Input, null, null, null, null);
		ci.appendCustomerMessage().setText("foo");
		final FixVersion fixVersion = new FixVersion(EnumVersion.Version_1_5);
		fixVersion.setZappDeprecated(true);
		fixVersion.convert(ci);
		Assertions.assertNull(ci.getCustomerMessage(0));
	}

	/**
	 *
	 */
	@Test
	public void testChannelType()
	{
		final JDFComChannel c = (JDFComChannel) new JDFDoc("ComChannel").getRoot();
		c.setChannelType(EnumChannelType.Mobile);
		Assertions.assertTrue(new FixVersion(EnumVersion.Version_1_1).convert(c));
		Assertions.assertEquals(c.getChannelTypeDetails(), "Mobile");
		Assertions.assertEquals(c.getAttribute(AttributeName.CHANNELTYPE), "Phone");
		Assertions.assertTrue(new FixVersion(EnumVersion.Version_1_5).convert(c));
		Assertions.assertEquals(c.getAttribute(AttributeName.CHANNELTYPE), "Mobile");
	}

	/**
	 *
	 */
	@Test
	public void testProofItem()
	{
		final JDFProofItem c = (JDFProofItem) new JDFDoc(ElementName.PROOFITEM).getRoot();
		c.setProofTarget("pt");
		Assertions.assertTrue(new FixVersion(EnumVersion.Version_1_7).convert(c));
		Assertions.assertEquals("pt", c.getFileSpec().getURL());
		Assertions.assertEquals("", c.getProofTarget());
		Assertions.assertTrue(new FixVersion(EnumVersion.Version_1_6).convert(c));
		Assertions.assertEquals("pt", c.getProofTarget());
		Assertions.assertNull(c.getFileSpec());
	}

	/**
	 *
	 */
	@Test
	public void testIgnore()
	{
		final JDFComChannel c = (JDFComChannel) new JDFDoc("ComChannel").getRoot();
		c.setAttribute("bad", "blah");
		c.setAttribute("notbad", "blah");
		final FixVersion fixVersion = new FixVersion(EnumVersion.Version_1_1);
		fixVersion.setBZappInvalid(true);
		fixVersion.addIgnore(ElementName.COMCHANNEL, "notbad");
		Assertions.assertTrue(fixVersion.convert(c));
		Assertions.assertNull(c.getNonEmpty("bad"));
		Assertions.assertEquals("blah", c.getAttribute("notbad"));
		Assertions.assertTrue(new FixVersion(EnumVersion.Version_1_5).convert(c));
	}

	/**
	 *
	 */
	@Test
	public void testFriendlyName()
	{
		final JDFDevice d = (JDFDevice) new JDFDoc(ElementName.DEVICE).getRoot();
		d.setFriendlyName("f1");
		d.setDescriptiveName("d1");
		final FixVersion fixVersion = new FixVersion(EnumVersion.Version_1_5);
		Assertions.assertTrue(fixVersion.convert(d));
		Assertions.assertEquals("f1", d.getFriendlyName());
		Assertions.assertEquals("d1", d.getDescriptiveName());
		fixVersion.setZappDeprecated(true);
		Assertions.assertTrue(fixVersion.convert(d));
		Assertions.assertNull(d.getNonEmpty(AttributeName.FRIENDLYNAME));
		Assertions.assertEquals("d1", d.getDescriptiveName());
	}

	/**
	 *
	 */
	@Test
	public void testContentMetaData()
	{
		final JDFContentMetaData c = (JDFContentMetaData) new JDFDoc(ElementName.CONTENTMETADATA).getRoot();
		c.setISBN13("12345");
		Assertions.assertTrue(new FixVersion(EnumVersion.Version_1_6).convert(c));
		Assertions.assertEquals(c.getAttribute(AttributeName.ISBN), "12345");
		Assertions.assertTrue(new FixVersion(EnumVersion.Version_1_1).convert(c));
		Assertions.assertEquals(c.getAttribute(AttributeName.ISBN10), "12345");
		Assertions.assertFalse(c.hasAttribute(AttributeName.ISBN));
	}

	/**
	 *
	 */
	@Test
	public void testNamedFeature()
	{
		n.setNamedFeatures(new VString("a b", null));
		Assertions.assertTrue(new FixVersion(EnumVersion.Version_1_5).convert(n));
		Assertions.assertEquals(n.getGeneralID("a", 0), "b");
		Assertions.assertNull(n.getAttribute(AttributeName.NAMEDFEATURES, null, null));
		Assertions.assertTrue(new FixVersion(EnumVersion.Version_1_4).convert(n));
		Assertions.assertEquals(n.getAttribute(AttributeName.NAMEDFEATURES, null, null), "a b");
		Assertions.assertNull(n.getGeneralID(null, 0));
	}

	/**
	 *
	 */
	@Test
	public void testNumColors()
	{
		final JDFColorIntent ci = (JDFColorIntent) n.getCreateResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		ci.setNumColors(4);
		boolean converted = new FixVersion(EnumVersion.Version_1_4).convert(ci);
		Assertions.assertTrue(converted);
		Assertions.assertNull(ci.getAttribute(AttributeName.NUMCOLORS, null, null));
		Assertions.assertEquals(ci.getColorsUsed().getSeparations().size(), 4);
		final FixVersion fixVersion = new FixVersion(EnumVersion.Version_1_5);
		fixVersion.setFixNewDuplicate(true);
		converted = fixVersion.convert(ci);
		Assertions.assertTrue(converted);
		Assertions.assertEquals(ci.getNumColors(), 4);
		Assertions.assertEquals(ci.getColorsUsed().getSeparations().size(), 0);
	}

	/**
	 *
	 */
	@Test
	public void testNumColorsK()
	{
		final JDFColorIntent ci = (JDFColorIntent) n.getCreateResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		ci.appendColorsUsed().setSeparations(new VString("K", null));
		final boolean converted = new FixVersion(EnumVersion.Version_1_4).convert(ci);
		Assertions.assertTrue(converted);
		Assertions.assertNull(ci.getAttribute(AttributeName.NUMCOLORS, null, null));
		Assertions.assertEquals(ci.getColorsUsed().getSeparations().size(), 1);
		Assertions.assertEquals("K", ci.getColorsUsed().getSeparations().get(0));
	}

	/**
	 *
	 */
	@Test
	public void testSourceObjects()
	{
		final JDFColorSpaceConversionOp co = ((JDFColorSpaceConversionParams) n.getCreateResource(ElementName.COLORSPACECONVERSIONPARAMS, EnumUsage.Input, 0)).appendColorSpaceConversionOp();

		co.addSourceObject(JDFColorSpaceConversionOp.EnumSourceObjects.All);
		final boolean converted = new FixVersion(EnumVersion.Version_1_6).convert(co);
		Assertions.assertTrue(converted);
		Assertions.assertNull(co.getSourceObjects());
	}

	/**
	 *
	 */
	@Test
	public void testSourceObjects2()
	{
		final JDFColorSpaceConversionOp co = ((JDFColorSpaceConversionParams) n.getCreateResource(ElementName.COLORSPACECONVERSIONPARAMS, EnumUsage.Input, 0)).appendColorSpaceConversionOp();

		co.addSourceObject(JDFColorSpaceConversionOp.EnumSourceObjects.ImagePhotographic);
		final boolean converted = new FixVersion(EnumVersion.Version_1_6).convert(co);
		Assertions.assertTrue(converted);
		Assertions.assertEquals(1, co.getSourceObjects().size());
	}

	/**
	 *
	 */
	@Test
	public void testSourceObjectsNull()
	{
		final JDFColorSpaceConversionOp co = ((JDFColorSpaceConversionParams) n.getCreateResource(ElementName.COLORSPACECONVERSIONPARAMS, EnumUsage.Input, 0)).appendColorSpaceConversionOp();

		co.addSourceObject(JDFColorSpaceConversionOp.EnumSourceObjects.ImagePhotographic);
		co.addSourceObject(JDFColorSpaceConversionOp.EnumSourceObjects.All);
		final boolean converted = new FixVersion((EnumVersion) null).convert(co);
		Assertions.assertTrue(converted);
		Assertions.assertEquals(2, co.getSourceObjects().size());
	}

	/**
	 *
	 */
	@Test
	public void testNumColors15()
	{
		final JDFColorIntent ci = (JDFColorIntent) n.getCreateResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		ci.appendColorsUsed().setSeparations(new VString("Black", null));
		final FixVersion fixVersion = new FixVersion(EnumVersion.Version_1_5);
		fixVersion.setFixNewDuplicate(false);
		final boolean converted = fixVersion.convert(ci);
		Assertions.assertTrue(converted);
		Assertions.assertNull(ci.getNonEmpty(AttributeName.NUMCOLORS));
		Assertions.assertEquals(ci.getColorsUsed().getSeparations().size(), 1);
		Assertions.assertEquals("Black", ci.getColorsUsed().getSeparations().get(0));

		fixVersion.setFixNewDuplicate(true);
		fixVersion.convert(ci);
		Assertions.assertEquals("1", ci.getNonEmpty(AttributeName.NUMCOLORS));
	}

	/**
	 *
	 */
	@Test
	public void testNodeInfo()
	{
		n.setVersion(EnumVersion.Version_1_1);
		n.appendNodeInfo().appendJMF();
		final FixVersion fixVersion = new FixVersion(EnumVersion.Version_1_5);
		fixVersion.setZappDeprecated(true);
		final boolean converted = fixVersion.convert(n);
		Assertions.assertTrue(converted);
		Assertions.assertNotNull(n.getResource(ElementName.NODEINFO, EnumUsage.Input, 0));
		Assertions.assertNull(n.getResource(ElementName.NODEINFO, EnumUsage.Input, 0).getElement(ElementName.JMF));
	}

	/**
	 *
	 */
	@Test
	public void testNodeInfoTime()
	{
		final JDFNodeInfo ni = n.appendNodeInfo();
		ni.setAttribute(AttributeName.START, new JDFDate().getDateISO());
		ni.setAttribute(AttributeName.LASTEND, new JDFDate().getDateISO());
		final FixVersion fixVersion = new FixVersion((EnumVersion) null);
		fixVersion.setBZappInvalid(true);
		final boolean converted = fixVersion.convert(n);
		Assertions.assertTrue(converted);
		Assertions.assertTrue(ni.getAttribute(AttributeName.START).indexOf("T06:00") > 0);
		Assertions.assertTrue(ni.getAttribute(AttributeName.LASTEND).indexOf("T18:00") > 0);
	}

	/**
	 *
	 */
	@Test
	public void testNodeInfoTimeSet()
	{
		final JDFNodeInfo ni = n.appendNodeInfo();
		ni.setAttribute(AttributeName.START, new JDFDate().getDateISO());
		ni.setAttribute(AttributeName.LASTEND, new JDFDate().getDateISO());
		final FixVersion fixVersion = new FixVersion((EnumVersion) null);
		fixVersion.setFirsthour(3);
		fixVersion.setLasthour(22);
		fixVersion.setBZappInvalid(true);
		final boolean converted = fixVersion.convert(n);
		Assertions.assertTrue(converted);
		Assertions.assertTrue(ni.getAttribute(AttributeName.START).indexOf("T03:00") > 0);
		Assertions.assertTrue(ni.getAttribute(AttributeName.LASTEND).indexOf("T22:00") > 0);
	}

	/**
	 *
	 */
	@Test
	public void testDeliveryIntentTimeSet()
	{
		final JDFDeliveryIntent ni = (JDFDeliveryIntent) n.addResource(ElementName.DELIVERYINTENT, EnumUsage.Input);
		ni.appendEarliest().setAttribute(AttributeName.ACTUAL, new JDFDate().getDateISO());
		ni.appendRequired().setAttribute(AttributeName.ACTUAL, new JDFDate().getDateISO());
		final FixVersion fixVersion = new FixVersion((EnumVersion) null);
		fixVersion.setFirsthour(3);
		fixVersion.setLasthour(22);
		fixVersion.setBZappInvalid(true);
		final boolean converted = fixVersion.convert(n);
		Assertions.assertTrue(converted);
		Assertions.assertTrue(ni.getEarliest().getAttribute(AttributeName.ACTUAL).indexOf("T03:00") > 0);
		Assertions.assertTrue(ni.getRequired().getAttribute(AttributeName.ACTUAL).indexOf("T22:00") > 0);
	}

	/**
	 *
	 */
	@Test
	public void testInheritedAttributes()
	{
		final JDFRunList ru = (JDFRunList) n.getCreateResource(ElementName.RUNLIST, EnumUsage.Input, 0);
		final JDFResource rup = ru.addPartition(EnumPartIDKey.Run, "r");
		ru.setNPage(7);
		final FixVersion fixVersion = new FixVersion(EnumVersion.Version_1_5);
		fixVersion.setZappDeprecated(true);
		final boolean converted = fixVersion.convert(n);
		Assertions.assertTrue(converted);
		Assertions.assertEquals(7, ru.getNPage());
		Assertions.assertFalse(rup.hasAttribute_KElement(AttributeName.NPAGE, null, false));
	}

	/**
	 *
	 */
	@Test
	public void testNumColorsDouble()
	{
		final JDFColorIntent ci = (JDFColorIntent) n.getCreateResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		ci.setNumColors(4);
		ci.getCreateColorsUsed().setCMYK();
		boolean converted = new FixVersion(EnumVersion.Version_1_4).convert(ci);
		Assertions.assertTrue(converted);
		Assertions.assertNull(ci.getAttribute(AttributeName.NUMCOLORS, null, null));
		Assertions.assertEquals(ci.getColorsUsed().getSeparations().size(), 4);
		final FixVersion fixVersion = new FixVersion(EnumVersion.Version_1_5);
		fixVersion.setFixNewDuplicate(true);
		converted = fixVersion.convert(ci);
		Assertions.assertTrue(converted);
		Assertions.assertEquals(ci.getNumColors(), 4);
		Assertions.assertEquals(ci.getColorsUsed().getSeparations().size(), 0);
	}

	/**
	 *
	 */
	@Test
	public void testTool()
	{
		final JDFTool t = (JDFTool) n.addResource("Tool", null, EnumUsage.Input, null, null, null, null);
		t.setResStatus(EnumResStatus.Available, true);
		t.setProductID("toolID");
		Assertions.assertTrue(t.fixVersion(EnumVersion.Version_1_1));
		Assertions.assertEquals(t.getToolID(), "toolID");
		Assertions.assertEquals(t.getProductID(), "toolID");
		Assertions.assertTrue(t.fixVersion(EnumVersion.Version_1_3));
		Assertions.assertEquals(t.getToolID(), "");
		Assertions.assertEquals(t.getProductID(), "toolID");
	}

	/**
	 *
	 */
	@Test
	public void testLayoutPrep()
	{
		final IDPGoldenTicket idpGoldenTicket = new IDPGoldenTicket(1);

		idpGoldenTicket.assign(null);
		final JDFNode node = idpGoldenTicket.getNode();
		Assertions.assertTrue(node.isValid(EnumValidationLevel.Complete));
		final FixVersion fix = new FixVersion(EnumVersion.Version_1_5);
		fix.setLayoutPrepToStripping(true);
		fix.walkTree(node, null);
		node.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "FixLayoutPrep.jdf", 2, false);
		Assertions.assertTrue(node.isValid(EnumValidationLevel.Complete));
	}

	/**
	 *
	 */
	@Test
	public void testJMF()
	{
		final JDFJMF jmf = new JMFBuilder().buildNewJDFCommand();
		jmf.setAgentName("AName");
		jmf.setAgentVersion("AVersion");
		final FixVersion fix = new FixVersion(EnumVersion.Version_1_3);
		fix.setBZappInvalid(true);
		fix.walkTree(jmf, null);
		Assertions.assertNull(StringUtil.getNonEmpty(jmf.getAgentName()));
		final FixVersion fix2 = new FixVersion(EnumVersion.Version_1_4);
		fix2.walkTree(jmf, null);
	}

	/**
	 *
	 */
	@Test
	public void testJMFQueueFilter()
	{
		final JDFJMF jmf = new JMFBuilder().buildAbortQueueEntry("42");
		final JDFCommand command = jmf.getCommand(0);
		command.appendQueueFilter();
		FixVersion fix = new FixVersion(EnumVersion.Version_1_4);
		fix.walkTree(jmf, null);
		Assertions.assertNotNull(command.getQueueFilter(0));
		fix = new FixVersion(EnumVersion.Version_1_5);
		fix.walkTree(jmf, null);
		Assertions.assertNull(command.getQueueFilter(0));
	}

	/**
	 *
	 */
	@Test
	public void testJMFQueueAbortQueueEntry()
	{
		final JDFJMF jmf = new JMFBuilder().buildAbortQueueEntry("42");
		final JDFCommand command = jmf.getCommand(0);
		FixVersion fix = new FixVersion(EnumVersion.Version_1_4);
		fix.walkTree(jmf, null);
		Assertions.assertNotNull(command.getQueueEntryDef(0));
		fix = new FixVersion(EnumVersion.Version_1_5);
		fix.walkTree(jmf, null);
		Assertions.assertNull(command.getQueueEntryDef(0));
		fix = new FixVersion(EnumVersion.Version_1_4);
		fix.walkTree(jmf, null);
		Assertions.assertNotNull(command.getQueueEntryDef(0));
	}

	/**
	 *
	 */
	@Test
	public void testJMFQueueAbortQueueEntryNull()
	{
		final JDFJMF jmf = new JMFBuilder().buildAbortQueueEntry("42");
		final JDFCommand command = jmf.getCommand(0);
		Assertions.assertNotNull(command.getQueueEntryDef(0));
		FixVersion fix = new FixVersion(EnumVersion.Version_1_5);
		fix.walkTree(jmf, null);
		Assertions.assertNull(command.getQueueEntryDef(0));
		Assertions.assertEquals("42", jmf.getXPathAttribute("Command/AbortQueueEntryParams/QueueFilter/QueueEntryDef/@QueueEntryID", null));
		fix = new FixVersion(EnumVersion.Version_1_4);
		fix.walkTree(jmf, null);
		Assertions.assertNotNull(command.getQueueEntryDef(0));
	}

	/**
	 *
	 */
	@Test
	public void testJMFQueueHoldQueueEntry()
	{
		final JDFJMF jmf = new JMFBuilder().buildHoldQueueEntry("42");
		final JDFCommand command = jmf.getCommand(0);
		FixVersion fix = new FixVersion(EnumVersion.Version_1_4);
		fix.walkTree(jmf, null);
		Assertions.assertNotNull(command.getQueueEntryDef(0));
		fix = new FixVersion(EnumVersion.Version_1_5);
		fix.walkTree(jmf, null);
		Assertions.assertNull(command.getQueueEntryDef(0));
		fix = new FixVersion(EnumVersion.Version_1_4);
		fix.walkTree(jmf, null);
		Assertions.assertNotNull(command.getQueueEntryDef(0));
	}

	/**
	 *
	 */
	@Test
	public void testJMFQueueRemoveQueueEntry()
	{
		final JDFJMF jmf = new JMFBuilder().buildRemoveQueueEntry("42");
		final JDFCommand command = jmf.getCommand(0);
		FixVersion fix = new FixVersion(EnumVersion.Version_1_4);
		fix.walkTree(jmf, null);
		Assertions.assertNotNull(command.getQueueEntryDef(0));
		fix = new FixVersion(EnumVersion.Version_1_5);
		fix.walkTree(jmf, null);
		Assertions.assertNull(command.getQueueEntryDef(0));
		fix = new FixVersion(EnumVersion.Version_1_4);
		fix.walkTree(jmf, null);
		Assertions.assertNotNull(command.getQueueEntryDef(0));
	}

	/**
	 *
	 */
	@Test
	public void testJMFQueueSuspendQueueEntry()
	{
		final JDFJMF jmf = new JMFBuilder().buildSuspendQueueEntry("42");
		final JDFCommand command = jmf.getCommand(0);
		FixVersion fix = new FixVersion(EnumVersion.Version_1_4);
		fix.walkTree(jmf, null);
		Assertions.assertNotNull(command.getQueueEntryDef(0));
		fix = new FixVersion(EnumVersion.Version_1_5);
		fix.walkTree(jmf, null);
		Assertions.assertNull(command.getQueueEntryDef(0));
		fix = new FixVersion(EnumVersion.Version_1_4);
		fix.walkTree(jmf, null);
		Assertions.assertNotNull(command.getQueueEntryDef(0));
	}

	/**
	 *
	 */
	@Test
	public void testJMFQueueSusbmitQueueEntry()
	{
		final JDFJMF jmf = new JMFBuilder().buildSubmitQueueEntry("http://www.example.com");
		final JDFCommand command = jmf.getCommand(0);
		FixVersion fix = new FixVersion(EnumVersion.Version_1_4);
		fix.walkTree(jmf, null);
		fix = new FixVersion(EnumVersion.Version_1_5);
		fix.walkTree(jmf, null);
		fix = new FixVersion(EnumVersion.Version_1_4);
		fix.walkTree(jmf, null);
		Assertions.assertNotNull(command);
	}

	/**
	 *
	 */
	@Test
	public void testJMFQueueResumeQueueEntry()
	{
		final JDFJMF jmf = new JMFBuilder().buildResumeQueueEntry("42");
		final JDFCommand command = jmf.getCommand(0);
		FixVersion fix = new FixVersion(EnumVersion.Version_1_4);
		fix.walkTree(jmf, null);
		Assertions.assertNotNull(command.getQueueEntryDef(0));
		fix = new FixVersion(EnumVersion.Version_1_5);
		fix.walkTree(jmf, null);
		Assertions.assertEquals(1, command.numChildElements("ResumeQueueEntryParams", null));
		Assertions.assertNull(command.getQueueEntryDef(0));
		fix = new FixVersion(EnumVersion.Version_1_5);
		fix.walkTree(jmf, null);
		Assertions.assertNull(command.getQueueEntryDef(0));
		Assertions.assertEquals(1, command.numChildElements("ResumeQueueEntryParams", null));
		fix = new FixVersion(EnumVersion.Version_1_4);
		fix.walkTree(jmf, null);
		Assertions.assertNotNull(command.getQueueEntryDef(0));
	}

	/**
	 *
	 */
	@Test
	public void testJMFQueue()
	{
		final JDFJMF jmf = new JDFDoc("JMF").getJMFRoot();
		final JDFResponse r = jmf.appendResponse();
		r.setType(EnumType.RemoveQueueEntry);
		r.appendQueue();
		FixVersion fix = new FixVersion(EnumVersion.Version_1_4);
		fix.walkTree(jmf, null);
		Assertions.assertNotNull(r.getQueue(0));
		fix = new FixVersion(EnumVersion.Version_1_5);
		fix.walkTree(jmf, null);
		Assertions.assertNull(r.getQueue(0));
	}

	// //////////////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testAssembly()
	{
		final JDFAssembly a = (JDFAssembly) n.addResource(ElementName.ASSEMBLY, EnumUsage.Input);
		a.setResStatus(EnumResStatus.Available, true);
		final VString ai = new VString("a1", null);
		a.setAssemblyIDs(ai);
		final JDFAssemblySection as = a.appendAssemblySection();
		final VString asi = new VString("a1.1", null);
		as.setAssemblyIDs(asi);
		Assertions.assertTrue(a.fixVersion(EnumVersion.Version_1_1));
		Assertions.assertEquals(a.getAssemblyID(), "a1");
		Assertions.assertNull(a.getAttribute("AssemblyIDs", null, null));
		Assertions.assertEquals(as.getAssemblyID(), "a1.1");
		Assertions.assertNull(as.getAttribute("AssemblyIDs", null, null));
		Assertions.assertTrue(a.fixVersion(EnumVersion.Version_1_4));
		Assertions.assertEquals(a.getAssemblyIDs(), ai);
		Assertions.assertEquals(a.getAssemblyID(), "");
		Assertions.assertEquals(as.getAssemblyIDs(), asi);
		Assertions.assertEquals(as.getAssemblyID(), "");
	}

	/**
	 * tests updating multiple versions at once
	 */
	@Test
	public void testMultiskip()
	{
		n.setVersion(EnumVersion.Version_1_4);
		final JDFAssembly a = (JDFAssembly) n.addResource(ElementName.ASSEMBLY, EnumUsage.Input);
		a.setResStatus(EnumResStatus.Available, true);
		final VString ai = new VString("a1", null);
		a.setAssemblyIDs(ai);
		final JDFAssemblySection as = a.appendAssemblySection();
		final VString asi = new VString("a1.1", null);
		as.setAssemblyIDs(asi);
		Assertions.assertTrue(a.fixVersion(EnumVersion.Version_1_1));
		Assertions.assertEquals(a.getAssemblyID(), "a1");
		Assertions.assertNull(a.getAttribute("AssemblyIDs", null, null));
		Assertions.assertEquals(as.getAssemblyID(), "a1.1");
		Assertions.assertNull(as.getAttribute("AssemblyIDs", null, null));
		Assertions.assertTrue(a.fixVersion(EnumVersion.Version_1_4));
		Assertions.assertEquals(a.getAssemblyIDs(), ai);
		Assertions.assertEquals(a.getAssemblyID(), "");
		Assertions.assertEquals(as.getAssemblyIDs(), asi);
		Assertions.assertEquals(as.getAssemblyID(), "");
	}

	/**
	 *
	 */
	@Test
	public void testMediaGrade()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(JDFNode.EnumType.ConventionalPrinting);
		final JDFMedia med = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		med.setMediaType(EnumMediaType.Paper);
		med.setWeight(42);
		med.setGrade(1);

		Assertions.assertTrue(med.fixVersion(EnumVersion.Version_1_6));
		Assertions.assertEquals(EnumISOPaperSubstrate.PS1, med.getISOPaperSubstrate());
	}

	/**
	 * tests updating multiple versions at once
	 */
	@Test
	public void testAmount()
	{
		n.setVersion(EnumVersion.Version_1_4);
		final JDFComponent c = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(c, null);
		rl.setAttribute(AttributeName.AMOUNT, "333.0");
		n.fixVersion(null);
		Assertions.assertEquals(rl.getAttribute(AttributeName.AMOUNT), "333");
	}

	/**
	 * tests updating multiple versions at once
	 */
	@Test
	public void testBoolean()
	{
		n.setVersion(EnumVersion.Version_1_4);
		final JDFBendingParams c = (JDFBendingParams) n.addResource(ElementName.BENDINGPARAMS, EnumUsage.Input);
		c.setAttribute(AttributeName.BEND, "1");
		n.fixVersion(null);
		Assertions.assertEquals("true", c.getAttribute(AttributeName.BEND));
	}

	/**
	 * tests updating multiple versions at once
	 */
	@Test
	public void testBoolean0()
	{
		n.setVersion(EnumVersion.Version_1_4);
		final JDFBendingParams c = (JDFBendingParams) n.addResource(ElementName.BENDINGPARAMS, EnumUsage.Input);
		c.setAttribute(AttributeName.BEND, "0");
		n.fixVersion(null);
		Assertions.assertEquals("false", c.getAttribute(AttributeName.BEND));
	}

	/**
	 *
	 * @see junit.framework.TestCase#toString()
	 */
	@Override
	public String toString()
	{
		return super.toString() + "\n" + n;
	}

}
