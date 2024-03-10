/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of
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
package org.cip4.jdflib.pool;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoNotification.EnumClass;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFQueueEntry;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.resource.JDFCreated;
import org.cip4.jdflib.resource.JDFMerged;
import org.cip4.jdflib.resource.JDFModified;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author MuchaD
 *
 * This implements the first fixture with unit tests for class JDFElement.
 */
public class JDFAuditPoolTest extends JDFTestCaseBase
{
	private JDFDoc jdfDoc;
	private JDFNode jdfRoot;
	private JDFAuditPool myAuditPool;

	/**
	 * Method testIncludesAttribute.
	 * */
	@Test
	void testGetResourceVector()
	{
		final JDFResourceAudit ra1 = (JDFResourceAudit) myAuditPool.addAudit(EnumAuditType.ResourceAudit, null);
		final JDFResourceLink rl1 = (JDFResourceLink) ra1.appendElement("MediaLink");
		rl1.setrRef("r1");
		Assertions.assertNull(myAuditPool.getResourceAudits("r2", null));
		Assertions.assertEquals(myAuditPool.getResourceAudits("r1", null).get(0), ra1);
		final JDFResourceAudit ra2 = (JDFResourceAudit) myAuditPool.addAudit(EnumAuditType.ResourceAudit, null);
		final JDFResourceLink rl2 = (JDFResourceLink) ra2.appendElement("MediaLink");
		rl2.setrRef("r2");
		Assertions.assertEquals(myAuditPool.getResourceAudits("r1", null).get(0), ra1);
		Assertions.assertEquals(myAuditPool.getResourceAudits("r2", null).get(0), ra2);
		Assertions.assertEquals(myAuditPool.getResourceAudits("r2", null).size(), 1);
	}

	/**
	 *
	 */
	@Test
	void testAddCreated()
	{
		jdfRoot.setVersion(EnumVersion.Version_1_2);
		// Test AddCreated with one parameter
		myAuditPool.addCreated("A_Test_Author", null);
		final JDFAudit myAudit = myAuditPool.getAudit(1, JDFAudit.EnumAuditType.Created, new JDFAttributeMap(), null);
		final String myText = myAudit.getAuthor();
		Assertions.assertEquals(myText, "A_Test_Author", "Error: Author should be \"A_Test_Author\"");
		// Test AddCreate with two Parameter

		// Get Create a ResourcePool
		final JDFResourcePool myResourcePool = jdfRoot.getCreateResourcePool();
		// Append a ResoureElement
		myResourcePool.appendElement("BindingIntent", "");

		// Get that element back
		final JDFResource e = (JDFResource) myResourcePool.getElement("BindingIntent", "", 0);
		myAuditPool.addCreated("A Test Author for JUnitTest 2", e);

		final String strResourceID = e.buildXPath("/JDF", 1);
		final JDFCreated kResourceAudit = (JDFCreated) myAuditPool.getChildWithAttribute(null, "XPath", null, strResourceID, 0, true);

		Assertions.assertNotNull(kResourceAudit, "Error: Audit not found ");
	}

	/**
	 *
	 */
	@Test
	void testAddModified()
	{
		JDFAudit.setStaticAuthor("foo");
		JDFNode node = JDFNode.createRoot();
		JDFModified mod = node.getCreateAuditPool().addModified("me", null);
		Assertions.assertEquals(1, mod.numChildElements(ElementName.EMPLOYEE, null));
		JDFAudit.setStaticAuthor(null);
	}

	/**
	 *
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	void setUp() throws Exception
	{
		// TODO Auto-generated method stub
		super.setUp();

		jdfDoc = new JDFDoc(ElementName.JDF);
		jdfRoot = (JDFNode) jdfDoc.getRoot();
		jdfRoot.setJobID("jobID");

		myAuditPool = jdfRoot.getCreateAuditPool();

	}

	/**
	 *
	 */
	@Test
	void testAddMerged()
	{
		// Test AddCreated with one parameter
		final JDFDoc doc2 = new JDFDoc("JDF");
		final JDFNode node2 = doc2.getJDFRoot();
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(new JDFAttributeMap("SheetName", "s1"));

		final JDFMerged m1 = myAuditPool.addMerged(node2, null, null, null);
		final JDFMerged m2 = myAuditPool.addMerged(node2, null, null, vMap);
		Assertions.assertNotNull(m2);
		Assertions.assertEquals(m2.getPartMapVector(), vMap);
		Assertions.assertNull(myAuditPool.getElement(ElementName.PART));
		Assertions.assertNotNull(m1);
	}

	/**
	 *
	 */
	@Test
	void testAddSpawned()
	{
		// Test AddCreated with one parameter
		final JDFDoc doc2 = new JDFDoc("JDF");
		final JDFNode node2 = doc2.getJDFRoot();
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(new JDFAttributeMap("SheetName", "s1"));

		final JDFSpawned m1 = myAuditPool.addSpawned(node2, null, null, null, null);
		Assertions.assertNotNull(m1);
		final JDFSpawned m2 = myAuditPool.addSpawned(node2, null, null, null, vMap);
		Assertions.assertNotNull(m2);
		Assertions.assertEquals(m2.getPartMapVector(), vMap);
		Assertions.assertNull(myAuditPool.getElement(ElementName.PART));
	}

	/**
	 *
	 */
	@Test
	void testAddNotification()
	{

		final JDFNotification n1 = myAuditPool.addNotification(EnumClass.Event, null, null);
		final String id1 = StringUtil.getNonEmpty(n1.getID());
		Assertions.assertNotNull(id1);
		final JDFNotification n2 = myAuditPool.addNotification(EnumClass.Event, null, null);
		final String id2 = StringUtil.getNonEmpty(n2.getID());
		Assertions.assertNotSame(id1, id2);
	}

	/**
	 *
	 */
	@Test
	void testMergePools()
	{

		final JDFNotification n1 = myAuditPool.addNotification(EnumClass.Event, null, null);
		final String id1 = StringUtil.getNonEmpty(n1.getID());
		Assertions.assertNotNull(id1);
		final JDFNotification n2 = myAuditPool.addNotification(EnumClass.Event, null, null);
		final String id2 = StringUtil.getNonEmpty(n2.getID());
		Assertions.assertNotSame(id1, id2);
		final JDFNode node2 = new JDFDoc("JDF").getJDFRoot();
		JDFAuditPool ap2 = node2.getCreateAuditPool();
		ap2.deleteNode();
		ap2 = (JDFAuditPool) node2.copyElement(myAuditPool, null);
		final int notSize = myAuditPool.numChildElements(ElementName.NOTIFICATION, null);
		myAuditPool.appendUnique(ap2);
		Assertions.assertEquals(notSize, myAuditPool.numChildElements(ElementName.NOTIFICATION, null));

	}

	/**
	 *
	 */
	@Test
	void testSetPhaseNull()
	{
		JDFPhaseTime p1 = myAuditPool.setPhase(EnumNodeStatus.Setup, "foobar", null, null);
		Assertions.assertNotNull(p1);
		JDFPhaseTime p2 = myAuditPool.setPhase(null, null, null, null);
		Assertions.assertEquals(p1, p2);
	}

	/**
	 *
	 */
	@Test
	void testSetPhase()
	{
		JDFPhaseTime p1 = myAuditPool.setPhase(EnumNodeStatus.Setup, null, null, null);
		Assertions.assertNotNull(p1);
		Assertions.assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 1);
		JDFPhaseTime p2 = myAuditPool.setPhase(EnumNodeStatus.Setup, "foobar", null, null);
		Assertions.assertNotNull(p2);
		Assertions.assertNotSame(p1, p2);
		Assertions.assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 2);
		p2 = myAuditPool.setPhase(EnumNodeStatus.Setup, "foobar", null, null);
		Assertions.assertNotNull(p2);
		Assertions.assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 2);
		p2 = myAuditPool.setPhase(EnumNodeStatus.Ready, "foobar", null, null);
		Assertions.assertNotNull(p2);
		Assertions.assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 3);
		p1 = myAuditPool.setPhase(EnumNodeStatus.InProgress, null, null, null);
		Assertions.assertNotNull(p1);
		Assertions.assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 4);
		p2 = myAuditPool.setPhase(EnumNodeStatus.InProgress, null, null, null);
		Assertions.assertNotNull(p2);
		Assertions.assertEquals(p1, p2);
		Assertions.assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 4);
		final VElement vEmpl = new VElement();
		final JDFEmployee emp = (JDFEmployee) new JDFDoc(ElementName.EMPLOYEE).getRoot();
		emp.setPersonalID("p1");
		vEmpl.add(emp);
		p2 = myAuditPool.setPhase(EnumNodeStatus.InProgress, null, null, vEmpl);
		Assertions.assertNotNull(p2);
		Assertions.assertNotSame(p1, p2);
		Assertions.assertTrue(p2.getEmployee(0).isEqual(emp));
		emp.setPersonalID("p2");
		p2 = myAuditPool.setPhase(EnumNodeStatus.InProgress, null, null, vEmpl);
		Assertions.assertNotNull(p2);
		Assertions.assertNotSame(p1, p2);
		Assertions.assertEquals(p2.getEmployee(0).getPersonalID(), "p2");

	}

	/**
	 *
	 */
	@Test
	void testGetLastPhase()
	{
		final JDFPhaseTime p1 = myAuditPool.setPhase(EnumNodeStatus.Setup, null, null, null);
		Assertions.assertNotNull(p1);
		Assertions.assertEquals(p1, myAuditPool.getLastPhase(null, null));
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(new JDFAttributeMap("SheetName", "s1"));
		final VJDFAttributeMap vMap2 = new VJDFAttributeMap();
		vMap2.add(new JDFAttributeMap("SheetName", "s1"));
		final JDFPhaseTime p2 = myAuditPool.setPhase(EnumNodeStatus.Setup, null, vMap, null);
		Assertions.assertEquals(p2, myAuditPool.getLastPhase(vMap, null));
		Assertions.assertEquals(p2, myAuditPool.getLastPhase(null, null));
		final JDFPhaseTime p3 = myAuditPool.setPhase(EnumNodeStatus.Setup, null, vMap2, null);
		myAuditPool.addModified(null, jdfRoot);
		Assertions.assertEquals(p2, myAuditPool.getLastPhase(vMap, null));
		Assertions.assertEquals(p3, myAuditPool.getLastPhase(null, null));
		Assertions.assertEquals(p3, myAuditPool.getLastPhase(vMap2, null));

		p1.setModules(new VString("m1", null), new VString("RIP", null));
		Assertions.assertNull(myAuditPool.getLastPhase(null, "m2"));
		Assertions.assertEquals(p1, myAuditPool.getLastPhase(null, "m1"));

	}

	/**
	 *
	 */
	@Test
	void testCreateSubmitProcessRun()
	{
		JDFProcessRun pr = myAuditPool.createSubmitProcessRun(null);
		Assertions.assertNotNull(pr.getSubmissionTime());
		Assertions.assertFalse(new JDFDate().isEarlier(pr.getSubmissionTime()), "has submissiontime before now");
		Assertions.assertTrue(pr.getAttribute(AttributeName.QUEUEENTRYID).startsWith("qe"));

		final JDFDoc d = new JDFDoc(ElementName.QUEUEENTRY);
		final JDFQueueEntry qe = (JDFQueueEntry) d.getRoot();

		final JDFDate dat = new JDFDate();
		dat.addOffset(0, 0, 6, 2);
		qe.setSubmissionTime(dat);
		qe.setQueueEntryID("q1");
		pr = myAuditPool.createSubmitProcessRun(qe);
		Assertions.assertEquals(pr.getSubmissionTime(), dat);
		Assertions.assertEquals(pr.getAttribute(AttributeName.QUEUEENTRYID), "q1");
	}

	/**
	 * @throws Exception x
	 */
	@Test
	void testSetPhaseJMF() throws Exception
	{

		final JDFDoc docJMF = new JDFDoc("JMF");
		final JDFJMF jmf = docJMF.getJMFRoot();
		final JDFSignal sig = jmf.appendSignal(EnumType.Status);
		final JDFDeviceInfo di = sig.appendDeviceInfo();
		di.appendEmployee().setPersonalID("p1");
		final JDFJobPhase phase = di.appendJobPhase();
		phase.setPhaseStartTime(new JDFDate());
		phase.setStatus(EnumNodeStatus.Setup);
		phase.setJobID(jdfRoot.getJobID(true));
		phase.setJobPartID(jdfRoot.getJobPartID(true));

		VElement el = myAuditPool.setPhase(jmf);
		Assertions.assertNotNull(el);
		Assertions.assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 1);
		Assertions.assertNotNull(((JDFPhaseTime) myAuditPool.getAudit(0, EnumAuditType.PhaseTime, null, null)).getEmployee(0));
		Assertions.assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true), el);

		el = myAuditPool.setPhase(jmf);
		Assertions.assertNotNull(el);
		Assertions.assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 1);
		Assertions.assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true), el);

		phase.setStatus(EnumNodeStatus.Aborted);
		el = myAuditPool.setPhase(jmf);
		Assertions.assertNotNull(el);
		Assertions.assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).size(), 2);
		Assertions.assertEquals(myAuditPool.getChildElementVector(ElementName.PHASETIME, null, null, true, 0, true).elementAt(1), el.elementAt(0));

	}

	/**
	 *
	 */
	@Test
	void testGetAudit()
	{
		final JDFAudit a1 = myAuditPool.addAudit(EnumAuditType.Deleted, null);
		final JDFAudit a2 = myAuditPool.addAudit(EnumAuditType.Created, null);
		final JDFAudit a3 = myAuditPool.addAudit(EnumAuditType.PhaseTime, null);
		final JDFAudit a4 = myAuditPool.addAudit(EnumAuditType.Deleted, null);
		Assertions.assertEquals(myAuditPool.getAudit(-1, null, null, null), a4);
		Assertions.assertEquals(myAuditPool.getAudit(1, EnumAuditType.Deleted, null, null), a4);
		Assertions.assertEquals(myAuditPool.getAudit(-2, null, null, null), a3);
		Assertions.assertEquals(myAuditPool.getAudit(-2, EnumAuditType.Deleted, null, null), a1);
		Assertions.assertEquals(myAuditPool.getAudit(0, EnumAuditType.Deleted, null, null), a1);
		Assertions.assertEquals(myAuditPool.getAudit(-1, EnumAuditType.Created, null, null), a2);
	}

	/**
	 *
	 */
	@Test
	void testGetAuditPartitioned()
	{
		final JDFPhaseTime a3 = (JDFPhaseTime) myAuditPool.addAudit(EnumAuditType.PhaseTime, null);
		VJDFAttributeMap vParts = new VJDFAttributeMap();
		JDFAttributeMap map = new JDFAttributeMap("Run", "R1");
		map.put("PageNumber", "3");
		vParts.add(map);
		a3.setPartMapVector(vParts);
		Assertions.assertEquals(myAuditPool.getAudit(0, EnumAuditType.PhaseTime, null, vParts), a3);
		map.clear();
		Assertions.assertEquals(myAuditPool.getAudit(0, EnumAuditType.PhaseTime, null, vParts), a3);
		map.put("PageNumber", "3");
		Assertions.assertEquals(myAuditPool.getAudit(0, EnumAuditType.PhaseTime, null, vParts), a3);
		map.put("PageNumber", "34");
		Assertions.assertNull(myAuditPool.getAudit(0, EnumAuditType.PhaseTime, null, vParts));
	}

	/**
	 * @see junit.framework.TestCase#toString()
	 */
	@Override
	public String toString()
	{
		return myAuditPool == null ? "null" : myAuditPool.toString();
	}
}