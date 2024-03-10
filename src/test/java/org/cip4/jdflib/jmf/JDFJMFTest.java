/*
 *
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
package org.cip4.jdflib.jmf;

import java.util.Iterator;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoStatusQuParams.EnumDeviceDetails;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.util.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class JDFJMFTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	void testGetMessageVector()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		final JDFCommand command = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.Status);
		Assertions.assertEquals(jmf.getMessageVector(null, EnumType.Status).elementAt(0), command);
		Assertions.assertEquals(jmf.getMessageVector(null, EnumType.Status).size(), 1);
		final JDFSignal signal = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.Status);
		Assertions.assertEquals(jmf.getMessageVector(null, EnumType.Status).elementAt(0), command);
		Assertions.assertEquals(jmf.getMessageVector(null, EnumType.Status).elementAt(1), signal);
		Assertions.assertEquals(jmf.getMessageVector(null, EnumType.Status).size(), 2);
	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testInit()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		jmf.setSenderID("sid");
		final JDFCommand c = jmf.appendCommand();
		Assertions.assertTrue(c.getID().indexOf("." + (("sid".hashCode() & 0xffff) % 10000) + ".") != -1);
		Assertions.assertTrue(jmf.toString().indexOf("xsi:type=") > 0);
		Assertions.assertEquals(jmf.getMaxVersion(), jmf.getVersion(false));
		Assertions.assertEquals(jmf.getMaxVersion(), c.getMaxVersion(true));
	}

	/**
	 *
	 */
	@Test
	void testInitMaxVersion()
	{
		JDFElement.setDefaultJDFVersion(EnumVersion.Version_1_2);
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		Assertions.assertEquals(jmf.getMaxVersion(), jmf.getVersion(false));
		final JDFCommand c = jmf.appendCommand();
		Assertions.assertEquals(jmf.getMaxVersion(), c.getMaxVersion(true));
	}

	/**
	 *
	 */
	@Test
	void testTheSenderID()
	{
		JDFJMF.setTheSenderID("sid");
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		final JDFCommand c = jmf.appendCommand();
		Assertions.assertTrue(c.getID().indexOf("." + (("sid".hashCode() & 0xffff) % 10000) + ".") != -1);
		JDFJMF.setTheSenderID(null);
	}

	/**
	 *
	 */
	@Test
	void testToXML()
	{
		final JDFDoc d = new JDFDoc("JMF");
		final JDFJMF jmf = d.getJMFRoot();
		jmf.appendAcknowledge();
		final JDFParser p = new JDFParser();
		final String xml = jmf.toXML();
		final JDFDoc newDoc = p.parseString(xml);
		System.out.print(xml);
		Assertions.assertNotNull(newDoc);
	}

	/**
	 *
	 */
	@Test
	void testgetSubmissionParams()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		Assertions.assertNull(jmf.getSubmissionURL());
		final JDFCommand c = jmf.appendCommand(EnumType.ResubmitQueueEntry);
		Assertions.assertNull(jmf.getSubmissionURL());
		final JDFResubmissionParams rsp = c.appendResubmissionParams();
		Assertions.assertNull(jmf.getSubmissionURL());
		rsp.setURL("url");
		Assertions.assertEquals("url", jmf.getSubmissionURL());
	}

	// //////////////////////////////////////////////////////////////////////////
	// /
	/**
	 *
	 */
	@Test
	void testCreateResponse()
	{
		final JDFJMF queries = JDFJMF.createJMF(EnumFamily.Query, EnumType.Status);
		queries.appendCommand(EnumType.Resource);
		queries.appendCommand(EnumType.Resource);
		queries.appendRegistration(EnumType.Resource);

		final JDFJMF responses = queries.createResponse();
		final VElement messageVector = queries.getMessageVector(null, null);
		final VElement responseVector = responses.getMessageVector(null, null);
		Assertions.assertEquals(responseVector.size(), 4);
		for (int i = 0; i < responseVector.size(); i++)
		{
			final JDFResponse r = (JDFResponse) responseVector.elementAt(i);
			final JDFMessage m = (JDFMessage) messageVector.elementAt(i);
			Assertions.assertEquals(r.getrefID(), m.getID());
			Assertions.assertEquals(r.getType(), m.getType());
		}
	}

	/**
	 * @throws CloneNotSupportedException
	 *
	 */
	@Test
	void testCloneNewDocInit() throws CloneNotSupportedException
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		jmf.appendCommand(EnumType.AbortQueueEntry);
		JDFJMF jmf2 = (JDFJMF) jmf.cloneNewDoc();
		for (int i = 0; i < 10; i++)
			jmf2 = (JDFJMF) jmf2.cloneNewDoc();
		Assertions.assertTrue(StringUtil.numSubstrings(jmf2.toXML(), "<--") < 2);

	}

	/**
	 *
	 */
	@Test
	void testCollectICSVersions()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		jmf.collectICSVersions();
		Assertions.assertFalse(jmf.hasAttribute(AttributeName.ICSVERSIONS));
		final VString s12 = new VString("s1 s2", null);
		jmf.appendSignal().setICSVersions(s12);
		Assertions.assertTrue(s12.containsAll(jmf.collectICSVersions()));
		Assertions.assertTrue(jmf.collectICSVersions().containsAll(s12));

		jmf.appendSignal().setICSVersions(s12);
		Assertions.assertTrue(s12.containsAll(jmf.collectICSVersions()));
		Assertions.assertTrue(jmf.collectICSVersions().containsAll(s12));

		jmf.setICSVersions(new VString("j1 j1 j2", null));
		s12.add("j1");
		s12.add("j2");

		Assertions.assertTrue(s12.containsAll(jmf.collectICSVersions()));
		Assertions.assertTrue(jmf.collectICSVersions().containsAll(s12));

		Assertions.assertEquals(jmf.getICSVersions(), jmf.collectICSVersions(), "multiple calls should not stack");
	}

	/**
	 *
	 */
	@Test
	void testConvertResponseToSignal()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		final JDFDoc doc2 = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf2 = doc2.getJMFRoot();
		JDFSignal s = jmf.appendSignal();
		final JDFResponse r = jmf2.appendResponse();
		final JDFQuery q = jmf.appendQuery();
		q.setType("KnownMessages");
		q.appendKnownMsgQuParams();
		r.setQuery(q);
		Assertions.assertEquals(q.getID(), r.getrefID(), "refID");

		final JDFMessageService ms = r.appendMessageService();
		ms.setType("KnownMessages");
		s.convertResponse(r, q);
		Assertions.assertEquals(r.getType(), s.getType(), "type");
		Assertions.assertTrue(ms.isEqual(s.getMessageService(0)), "ms equal");
		Assertions.assertTrue(s.getXSIType().startsWith("Signal"));
		Assertions.assertEquals(s.getKnownMsgQuParams(0).getNextSiblingElement(), s.getMessageService(0));

		s = jmf.appendSignal();
		s.convertResponse(r, null);
		Assertions.assertEquals(r.getType(), s.getType(), "type");
		Assertions.assertTrue(ms.isEqual(s.getMessageService(0)), "ms equal");
		Assertions.assertTrue(s.getXSIType().startsWith("Signal"));
	}

	/**
	 *
	 */
	@Test
	void testConvertResponseToSignalNameSpace()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmfQuery = doc.getJMFRoot();
		final JDFDoc doc2 = new JDFDoc(ElementName.JMF);
		final JDFJMF jmfResp = doc2.getJMFRoot();
		final JDFDoc docSig = new JDFDoc(ElementName.JMF);
		final JDFJMF jmfSig = docSig.getJMFRoot();

		JDFSignal s = jmfSig.appendSignal();
		final JDFResponse r = jmfResp.appendResponse();
		final JDFQuery q = jmfQuery.appendQuery();
		q.setType("KnownMessages");
		q.appendKnownMsgQuParams();
		r.setQuery(q);
		Assertions.assertEquals(q.getID(), r.getrefID(), "refID");

		final JDFMessageService ms = r.appendMessageService();
		jmfResp.setAttribute("xmlns:foo", "www.foo.com");
		ms.setType("KnownMessages");
		ms.setAttribute("foo:key", "val");
		ms.appendElement("foo:bar");
		s.convertResponse(r, q);
		Assertions.assertEquals(r.getType(), s.getType(), "type");
		Assertions.assertTrue(ms.isEqual(s.getMessageService(0)), "ms equal");
		Assertions.assertTrue(s.getXSIType().startsWith("Signal"));
		Assertions.assertEquals(s.getKnownMsgQuParams(0).getNextSiblingElement(), s.getMessageService(0));

		s = jmfQuery.appendSignal();
		s.convertResponse(r, null);
		Assertions.assertEquals(r.getType(), s.getType(), "type");
		Assertions.assertTrue(ms.isEqual(s.getMessageService(0)), "ms equal");
		Assertions.assertTrue(s.getXSIType().startsWith("Signal"));
	}

	/**
	 *
	 */
	@Test
	void testConvertResponseToAcknowledge()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		final JDFDoc doc2 = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf2 = doc2.getJMFRoot();
		JDFAcknowledge ack = jmf.appendAcknowledge();
		final JDFResponse r = jmf2.appendResponse();
		final JDFQuery q = jmf.appendQuery();
		q.setType("KnownMessages");
		r.setQuery(q);
		final JDFMessageService ms = r.appendMessageService();
		ms.setType("KnownMessages");
		ack.convertResponse(r, q);
		Assertions.assertEquals(q.getID(), r.getrefID(), "refID");
		Assertions.assertEquals(r.getType(), ack.getType(), "type");
		Assertions.assertTrue(ms.isEqual(ack.getMessageService(0)), "ms equal");
		ack = jmf.appendAcknowledge();
		ack.convertResponse(r, null);
		Assertions.assertEquals(r.getType(), ack.getType(), "type");
		Assertions.assertTrue(ms.isEqual(ack.getMessageService(0)), "ms equal");
		Assertions.assertTrue(ack.getXSIType().startsWith("Acknowledge"));
	}

	/**
	 *
	 */
	@Test
	void testSplitAcknowledge()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		final JDFDoc doc2 = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf2 = doc2.getJMFRoot();
		final JDFResponse r = jmf2.appendResponse();
		final JDFQuery q = jmf.appendQuery();
		q.setType("KnownMessages");
		r.setQuery(q);
		final JDFMessageService ms = r.appendMessageService();
		ms.setType("KnownMessages");
		final JDFAcknowledge ack = r.splitAcknowledge();
		Assertions.assertEquals(q.getID(), r.getrefID(), "refID");
		Assertions.assertEquals(r.getType(), ack.getType(), "type");
		Assertions.assertTrue(ms.isEqual(ack.getMessageService(0)), "ms equal");
		Assertions.assertNull(r.getMessageService(0));
	}

	/**
	 *
	 */
	@Test
	void testConvertResponses()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		final JDFDoc doc2 = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf2 = doc2.getJMFRoot();
		final JDFResponse r = jmf2.appendResponse();
		final JDFQuery q = jmf.appendQuery();
		q.setType("KnownMessages");
		r.setQuery(q);
		Assertions.assertEquals(q.getID(), r.getrefID(), "refID");

		jmf2.convertResponses(q);
		Assertions.assertNull(jmf2.getResponse(0));
		Assertions.assertEquals(jmf2.getSignal(0).getrefID(), q.getID());
	}

	/**
	 *
	 */
	@Test
	void testCreateJMF()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Response, EnumType.AbortQueueEntry);
		Assertions.assertEquals(jmf.getResponse(0).getEnumType(), EnumType.AbortQueueEntry);
		Assertions.assertEquals(jmf.getResponse(0).getLocalName(), "Response");
	}

	/**
	 *
	 *
	 */
	@Test
	void testInitAgent()
	{
		JDFAudit.setStaticAgentName("foo");
		JDFAudit.setStaticAgentVersion("v1");
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, EnumType.Status);
		Assertions.assertEquals(JDFAudit.getStaticAgentName(), jmf.getAgentName());
		Assertions.assertEquals(JDFAudit.getStaticAgentVersion(), jmf.getAgentVersion());
	}

	/**
	 *
	 */
	@Test
	void testSenderIDBlank()
	{
		JDFJMF.setTheSenderID("a b");
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Response, EnumType.AbortQueueEntry);
		final JDFResponse response = jmf.getResponse(0);
		Assertions.assertTrue(response.getID().indexOf("." + "ab".hashCode() + ".") > 0, "the sender id was added but stripped");
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testDeviceInfo()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		final JDFSignal s = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, null);
		s.setType("Status");
		final JDFStatusQuParams sqp = s.appendStatusQuParams();
		sqp.setDeviceDetails(EnumDeviceDetails.Brief);
		final JDFDeviceInfo di = s.appendDeviceInfo();
		di.setDeviceStatus(EnumDeviceStatus.Unknown);
		JDFJobPhase jp = di.appendJobPhase();
		Assertions.assertEquals(jp, di.getJobPhase(0), "");
		jp = (JDFJobPhase) di.appendElement("jdf:JobPhase", JDFElement.getSchemaURL());
		Assertions.assertEquals(jp, di.getJobPhase(1), "");
		Assertions.assertNull(di.getJobPhase(2), "");
		jp.appendNode();
		Assertions.assertTrue(jp.isValid(EnumValidationLevel.Incomplete));
		jp.setAttribute("Status", "fnarf");
		Assertions.assertFalse(jp.isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 *
	 */
	@Test
	void testError()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		final JDFResponse r = (JDFResponse) jmf.appendMessageElement(EnumFamily.Response, null);
		r.setType("Status");
		r.setrefID("r1");
		final JDFNotification n = r.setErrorText("blub", null);
		Assertions.assertEquals(n.getComment(0).getText(), "blub", "get comment text");
		Assertions.assertEquals(n.getType(), "Error", "type");
		Assertions.assertTrue(r.isValid(EnumValidationLevel.Complete));
		jmf.setSenderID("S1");
		Assertions.assertTrue(jmf.isValid(EnumValidationLevel.Complete));
	}

	/**
	 *
	 */
	@Test
	void testGetMessageElement()
	{
		final JDFDoc d = new JDFDoc("JMF");
		final JDFJMF jmf = d.getJMFRoot();
		final JDFCommand c = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, EnumType.Events);
		Assertions.assertEquals(c, jmf.getMessageElement(EnumFamily.Command, EnumType.Events, 0));
		jmf.appendComment();

		final JDFSignal s = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.Events);
		Assertions.assertEquals(s, jmf.getMessageElement(EnumFamily.Signal, EnumType.Events, 0));
		Assertions.assertEquals(s, jmf.getMessageElement(null, EnumType.Events, 1));
		Assertions.assertEquals(s, jmf.getMessageElement(null, null, 1));

		final JDFSignal s2 = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, EnumType.Status);
		Assertions.assertEquals(s2, jmf.getMessageElement(EnumFamily.Signal, EnumType.Status, 0));
		Assertions.assertEquals(s2, jmf.getMessageElement(EnumFamily.Signal, null, 1));
		Assertions.assertEquals(s2, jmf.getMessageElement(null, null, 2));
		Assertions.assertEquals(s2, jmf.getMessageElement(null, null, -1));
		Assertions.assertEquals(s, jmf.getMessageElement(null, null, -2));
		Assertions.assertEquals(c, jmf.getMessageElement(null, null, -3));
		Assertions.assertNull(jmf.getMessageElement(null, null, -4));
	}

	/**
	 *
	 */
	@Test
	void testGetResponseByRefID()
	{
		final JDFDoc d = new JDFDoc("JMF");
		final JDFJMF jmf = d.getJMFRoot();
		jmf.appendMessageElement(EnumFamily.Command, EnumType.Events);
		jmf.appendComment();

		final JDFResponse r = (JDFResponse) jmf.appendMessageElement(EnumFamily.Response, EnumType.Events);
		r.setrefID("i42");
		Assertions.assertEquals(r, jmf.getResponse("i42"));
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	void testGetAcknowledgeByRefID()
	{
		final JDFDoc d = new JDFDoc("JMF");
		final JDFJMF jmf = d.getJMFRoot();
		jmf.appendMessageElement(EnumFamily.Command, EnumType.Events);
		jmf.appendComment();

		final JDFAcknowledge a = (JDFAcknowledge) jmf.appendMessageElement(EnumFamily.Acknowledge, EnumType.Events);
		a.setrefID("i42");
		Assertions.assertEquals(a, jmf.getAcknowledge("i42"));
		Assertions.assertEquals(a, jmf.getAcknowledge(null));
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testJobPhase()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		final JDFSignal s = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, null);
		s.setType("Status");
		final JDFStatusQuParams sqp = s.appendStatusQuParams();
		sqp.setDeviceDetails(EnumDeviceDetails.Brief);
		final JDFDeviceInfo di = s.appendDeviceInfo();
		JDFJobPhase jp = di.appendJobPhase();
		Assertions.assertEquals(jp, di.getJobPhase(0), "");
		jp = (JDFJobPhase) di.appendElement("jdf:JobPhase", JDFElement.getSchemaURL());
		Assertions.assertEquals(jp, di.getJobPhase(1), "");
		Assertions.assertNull(di.getJobPhase(2), "");
		jp.appendNode();
		Assertions.assertTrue(jp.isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 *
	 */
	@Test
	void testMessage()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		jmf.setSenderID("Pippi Langstrumpf");

		final Iterator<JDFMessage.EnumFamily> it = JDFMessage.EnumFamily.iterator();
		while (it.hasNext())
		{
			final JDFMessage.EnumFamily f = it.next();
			if (f == null)
			{
				continue;
			}
			final JDFMessage m = jmf.appendMessageElement(f, null);
			m.setType("KnownMessages");

			if (f.equals(JDFMessage.EnumFamily.Acknowledge))
			{
				final JDFAcknowledge a = (JDFAcknowledge) m;
				a.setrefID("refID");
			}

			if (f.equals(JDFMessage.EnumFamily.Registration))
			{
				final JDFRegistration r = (JDFRegistration) m;
				r.appendSubscription();
			}

			Assertions.assertTrue(jmf.getMessageVector(f, null).size() == 1, " added messages");
			Assertions.assertTrue(jmf.getMessageElement(f, null, 0).hasAttribute(AttributeName.XSITYPE), "xsi type");
			Assertions.assertEquals(jmf.getMessageElement(f, null, 0).getAttribute(AttributeName.XSITYPE), f.getName() + "KnownMessages", "xsi type");

		}

		Assertions.assertTrue(jmf.getMessageVector(null, null).size() == 6, " added messages");
		Assertions.assertTrue(jmf.isValid(EnumValidationLevel.Complete), "valid");
	}

	/**
	 *
	 */
	@Test
	void testPrivateMessage()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		final JDFSignal s = (JDFSignal) jmf.appendMessageElement(EnumFamily.Signal, null);
		s.setType("foo:test");
		s.appendDevice();
		Assertions.assertNull(s.getXSIType());
		Assertions.assertTrue(s.getDevice(0) != null, "get device");
	}

	/**
	 *
	 */
	@Test
	void testReturnQueueEntry()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		final JDFCommand c = (JDFCommand) jmf.appendMessageElement(EnumFamily.Command, null);
		c.setType("ReturnQueueEntry");
		final JDFReturnQueueEntryParams rqe = c.appendReturnQueueEntryParams();
		rqe.setURL("http://foo.jdf");
		rqe.setQueueEntryID("dummyID");
		Assertions.assertTrue(rqe.isValid(EnumValidationLevel.Complete), "JDFReturnQueueEntryParams");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	public void tearDown() throws Exception
	{
		JDFJMF.setTheSenderID(null);
		super.tearDown();
	}

}