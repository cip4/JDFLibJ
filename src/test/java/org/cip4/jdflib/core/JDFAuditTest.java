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
package org.cip4.jdflib.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFCreated;
import org.cip4.jdflib.resource.JDFModified;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JDFDate;
import org.junit.Test;

/**
 * @author MuchaD This implements the first fixture with unit tests for class JDFAudit.
 */
public class JDFAuditTest extends JDFTestCaseBase
{
	private boolean bAutoAgent;

	/**
	 *
	 */
	@Test
	public void testInit()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		final JDFAuditPool ap = n.getAuditPool();
		assertNotNull(ap);
		final JDFCreated crea = (JDFCreated) ap.getAudit(0, EnumAuditType.Created, null, null);
		assertTrue(crea.hasAttribute("ID"));
		assertTrue(crea.getID().startsWith("a"));
		final JDFProcessRun pr = ap.addProcessRun(EnumNodeStatus.Completed, "me", null);
		assertTrue(pr.hasAttribute("End"));
		assertTrue(pr.hasAttribute("ID"));
		final JDFSpawned sp = ap.addSpawned(n, null, null, null, null);
		assertTrue(sp.hasAttribute("ID"));
		n.setVersion(JDFElement.EnumVersion.Version_1_2);
		final JDFModified mod = ap.addModified("me", n);
		assertFalse(mod.hasAttribute("ID"));

	}

	/**
	 *
	 */
	@Test
	public void testFixVersion()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		final JDFAuditPool ap = n.getAuditPool();
		assertNotNull(ap);
		final JDFCreated crea = (JDFCreated) ap.getAudit(0, EnumAuditType.Created, null, null);
		assertTrue(crea.hasAttribute("ID"));
		n.fixVersion(JDFElement.EnumVersion.Version_1_2);
		assertFalse(crea.hasAttribute("ID"));
	}

	/**
	 *
	 */
	@Test
	public void testFixVersion14()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setVersion(EnumVersion.Version_1_3);
		n.setType("ConventionalPrinting", true);
		final JDFAuditPool ap = n.getAuditPool();
		assertNotNull(ap);
		final JDFModified mod = ap.addModified("foo", null);
		assertEquals(mod.getAuthor(), "foo");
		n.fixVersion(EnumVersion.Version_1_4);
		assertTrue(!mod.hasAttribute(AttributeName.AUTHOR));
		assertEquals(mod.getEmployee(0).getDescriptiveName(), "foo");

	}

	/**
	 *
	 */
	@Test
	public void testSetRef()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		final JDFAuditPool ap = n.getAuditPool();
		assertNotNull(ap);
		final JDFPhaseTime pt = ap.setPhase(EnumNodeStatus.Stopped, null, null, null);
		final JDFPhaseTime pt2 = ap.setPhase(EnumNodeStatus.Aborted, null, null, null);
		pt2.setRef(pt);
		assertEquals(pt.getID(), pt2.getrefID());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetTimeStamp()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		final JDFAuditPool ap = n.getAuditPool();
		final JDFAudit audit = ap.getAudit(0, EnumAuditType.Created, null, null);
		assertEquals(audit.getTimeStamp().getTimeInMillis(), System.currentTimeMillis(), 4242);
		audit.setAttribute(AttributeName.TIMESTAMP, "crap");
		assertNull(audit.getTimeStamp());
	}

	/**
	 *
	 */
	@Test
	public void testCreateUpdate()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		final JDFAuditPool ap = n.getAuditPool();
		assertNotNull(ap);
		final JDFPhaseTime pt = ap.setPhase(EnumNodeStatus.Stopped, null, null, null);
		ap.addAudit(EnumAuditType.Modified, null);
		ap.addAudit(EnumAuditType.PhaseTime, null);
		final JDFPhaseTime pt2 = (JDFPhaseTime) pt.createUpdateAudit();
		assertEquals(pt.getID(), pt2.getrefID());
		assertNotSame(pt.getID(), "");
		assertNotSame(pt2.getID(), "");
		assertNotSame(pt2.getID(), pt.getID());
	}

	/**
	 *
	 */
	@Test
	public void testGetUpdatedPreviousAudit()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		final JDFAuditPool ap = n.getAuditPool();
		assertNotNull(ap);
		final JDFPhaseTime pt = ap.setPhase(EnumNodeStatus.Stopped, null, null, null);
		ap.addAudit(EnumAuditType.Modified, null);
		ap.addAudit(EnumAuditType.PhaseTime, null);
		final JDFPhaseTime pt2 = (JDFPhaseTime) pt.createUpdateAudit();
		assertEquals(pt2.getUpdatedPreviousAudit(), pt);
		assertNull(pt.getUpdatedPreviousAudit());
	}

	/**
	 *
	 */
	@Test
	public void testCreated()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFAuditPool ap = n.getAuditPool();
		assertNotNull(ap);
		final JDFNode n2 = n.addJDFNode(EnumType.CaseMaking);
		final JDFCreated c1 = ap.addCreated("foo", n2);
		assertEquals(n2.buildXPath(ap.getParentJDF().buildXPath(null, 1), 1), c1.getXPath());
		final JDFResource r = n2.addResource("CaseMakingParams", null, EnumUsage.Input, null, null, null, null);
		final JDFCreated c2 = ap.addCreated("foo", r);
		assertEquals(r.buildXPath(ap.getParentJDF().buildXPath(null, 1), 1), c2.getXPath());

		d.write2File(sm_dirTestDataTemp + "createdTest.jdf", 0, false);

	}

	/**
	 *
	 */
	@Test
	public void testUpdateAudit()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFAuditPool ap = n.getAuditPool();
		final JDFCreated c = (JDFCreated) ap.getAudit(0, null, null, null);
		c.updateAgent("foo");
		assertTrue(c.getAgentName().endsWith(": foo"));

	}

	/**
	 *
	 */
	@Test
	public void testProcessRun()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		final JDFAuditPool ap = n.getAuditPool();
		assertNotNull(ap);
		final JDFProcessRun p1 = ap.addProcessRun(EnumNodeStatus.Completed, null, null);
		assertEquals(p1.getTimeStamp().getTimeInMillis(), new JDFDate().getTimeInMillis(), 2000);
	}

	/**
	 *
	 */
	@Test
	public void testSpawnID()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.setSpawnID("spawn");
		n.setType(EnumType.ProcessGroup);
		final JDFAuditPool ap = n.getAuditPool();
		assertNotNull(ap);
		final JDFProcessRun p1 = ap.addProcessRun(EnumNodeStatus.Completed, null, null);
		assertEquals(p1.getSpawnID(), n.getSpawnID(false));
		final JDFNode n2 = n.addJDFNode(EnumType.CaseMaking);
		final JDFProcessRun p2 = n.getCreateAuditPool().addProcessRun(EnumNodeStatus.Completed, null, null);
		assertEquals(p2.getSpawnID(), n2.getSpawnID(true));
		assertEquals(p2.getSpawnID(), n.getSpawnID(false));
	}

	/**
	 *
	 */
	@Test
	public void testSetStaticAgentVersion()
	{
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		JDFAuditPool ap = n.getAuditPool();
		assertNotNull(ap);
		JDFCreated crea = (JDFCreated) ap.getAudit(0, EnumAuditType.Created, null, null);
		// @Rainer (2013-03-10) - Not compatible to Linux
		// assertEquals(crea.getAgentName(), JDFAudit.getStaticAgentName());
		//
		// JDFResource.setAutoAgent(true);
		JDFResource r = n.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, null);
		// assertEquals(r.getAgentName(), JDFAudit.getStaticAgentName());
		// assertEquals(r.getAgentVersion(), JDFAudit.getStaticAgentVersion());
		JDFAudit.setStaticAgentName(null);
		JDFAudit.setStaticAgentVersion(null);
		JDFAudit.setStaticAuthor(null);
		d = new JDFDoc(ElementName.JDF);
		n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		ap = n.getAuditPool();
		assertNotNull(ap);
		crea = (JDFCreated) ap.getAudit(0, EnumAuditType.Created, null, null);
		assertEquals(crea.getAgentName(), "");
		assertEquals(crea.getAgentVersion(), "");
		assertEquals(crea.getAuthor(), "");
		r = n.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, null);
		assertFalse(r.hasAttribute(AttributeName.AGENTNAME));
		assertFalse(r.hasAttribute(AttributeName.AGENTVERSION));
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	public void tearDown() throws Exception
	{
		super.tearDown();
		JDFResource.setAutoAgent(bAutoAgent);

	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		bAutoAgent = JDFResource.getAutoAgent();
		KElement.setLongID(false);

	}

}