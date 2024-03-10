/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of
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
package org.cip4.jdflib.jmf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.net.URL;

import javax.mail.Multipart;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams;
import org.cip4.jdflib.util.CPUTimer;
import org.cip4.jdflib.util.MimeUtil;
import org.cip4.jdflib.util.MyPair;
import org.cip4.jdflib.util.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class JDFQueueSubmissionParamsTest extends JDFTestCaseBase
{
	JDFQueue theQueue;
	JDFJMF theJMF;
	JDFQueueSubmissionParams qsp;

	/**
	 * @throws Exception
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		JDFDoc d = new JDFDoc(ElementName.QUEUE);
		theQueue = (JDFQueue) d.getRoot();
		d = new JDFDoc(ElementName.JMF);
		theJMF = d.getJMFRoot();
		qsp = theJMF.appendCommand(EnumType.SubmitQueueEntry).appendQueueSubmissionParams();
		super.setUp();
	}

	/**
	 *
	 */
	@Test
	void testAddNull()
	{
		JDFResponse resp = qsp.addEntry(null, null, null);
		assertEquals(2, resp.getReturnCode());
	}

	/**
	 *
	 */
	@Test
	void testURLInput()
	{
		assertNull(qsp.getURLInputStream());
	}

	/**
	 *
	 */
	@Test
	void testAddEntry()
	{
		JDFResponse resp = qsp.addEntry(theQueue, null, null);
		assertEquals(0, resp.getReturnCode());
		assertNull(resp.getQueue(0));
	}

	/**
	 *
	 */
	@Test
	void testAddQueueEntry()
	{
		MyPair<JDFResponse, JDFQueueEntry> resp = qsp.addQueueEntry(theQueue, null, null);
		assertEquals(0, resp.a.getReturnCode());
		assertNull(resp.a.getQueue(0));
		assertEquals(theQueue.getQueueEntry(0), resp.b);
	}

	/**
	 *
	 */
	@Test
	void testAddEntryMany()
	{
		for (int i = 0; i < 20000; i++)
		{
			theQueue.appendQueueEntry().setQueueEntryStatus(EnumQueueEntryStatus.Waiting);
		}
		JDFQueueFilter f = (JDFQueueFilter) new JDFDoc(ElementName.QUEUEFILTER).getRoot();
		f.setMaxEntries(0);
		for (int b = 0; b < 2; b++)
		{
			theQueue.setAutomated(b == 1);
			CPUTimer t = new CPUTimer(false);
			for (int i = 0; i < 300; i++)
			{
				t.start();
				JDFResponse resp = qsp.addEntry(theQueue, null, f);
				assertEquals(0, resp.getReturnCode());
				JDFQueueEntry queueResp = resp.getQueueEntry(0);
				assertNotNull(queueResp);
				t.stop();
				if (i % 100 == 0)
				{
					log.info(b + " " + i + " " + t.getTotalRealTime() + " " + t.getAverageRealTime() + " " + t.getTotalCPUTime() + " " + t.getAverageCPUTime());
				}
			}
		}
	}

	/**
	 *
	 */
	@Test
	void testGetMimeURL()
	{
		JDFDoc d1 = new JDFDoc("JMF");
		d1.setOriginalFileName("JMF.jmf");
		JDFJMF jmf = d1.getJMFRoot();
		JDFCommand com = (JDFCommand) jmf.appendMessageElement(JDFMessage.EnumFamily.Command, JDFMessage.EnumType.SubmitQueueEntry);

		com.appendQueueSubmissionParams().setURL("cid:TheJDF");

		JDFDoc doc = new JDFDoc("JDF");
		doc.setOriginalFileName("JDF.jdf");
		JDFNode n = doc.getJDFRoot();
		n.setType(JDFNode.EnumType.ColorSpaceConversion);
		JDFColorSpaceConversionParams cscp = (JDFColorSpaceConversionParams) n.addResource(ElementName.COLORSPACECONVERSIONPARAMS, null, EnumUsage.Input, null, null, null, null);
		JDFFileSpec fs0 = cscp.appendFinalTargetDevice();
		fs0.setURL(StringUtil.uncToUrl(sm_dirTestData + File.separator + "test.icc", true));
		JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, null, EnumUsage.Input, null, null, null, null);
		rl.addPDF(StringUtil.uncToUrl(sm_dirTestData + File.separator + "url1.pdf", false), 0, -1);
		Multipart m = MimeUtil.buildMimePackage(d1, doc, true);

		JDFDoc[] d2 = MimeUtil.getJMFSubmission(m);
		assertNotNull(d2);
		final JDFQueueSubmissionParams queueSubmissionParams = d2[0].getJMFRoot().getCommand(0).getQueueSubmissionParams(0);
		assertEquals(queueSubmissionParams.getURL(), "cid:JDF.jdf");
		assertEquals(d2[1].getJDFRoot().getEnumType(), JDFNode.EnumType.ColorSpaceConversion);
		JDFDoc d3 = queueSubmissionParams.getURLDoc();
		assertNotNull(d3);
		assertEquals(d3.getJDFRoot().getEnumType(), JDFNode.EnumType.ColorSpaceConversion);
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testSetReturnURL() throws Exception
	{
		qsp.setReturnURL((URL) null);
		assertFalse(qsp.hasAttribute(AttributeName.RETURNURL));
		qsp.setReturnURL(new URL("http://localhost"));
		assertEquals(qsp.getReturnURL(), "http://localhost");
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testSetReturnJMFL() throws Exception
	{
		qsp.setReturnJMF((URL) null);
		assertFalse(qsp.hasAttribute(AttributeName.RETURNJMF));
		qsp.setReturnJMF(new URL("http://localhost"));
		assertEquals(qsp.getReturnJMF(), "http://localhost");
	}
}