/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

package org.cip4.jdflib.util.mime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Arrays;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFReturnQueueEntryParams;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.PlatformUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.cip4.jdflib.util.mime.MimeWriter.FixSemiColonStream;
import org.cip4.jdflib.util.mime.MimeWriter.eMimeSubType;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class MimeWriterTest extends JDFTestCaseBase
{

	/**
	 * @throws Exception
	 */
	@Test
	public void testFixSemicolon1() throws Exception
	{
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		final FixSemiColonStream fs = new FixSemiColonStream(ios);
		fs.write("AAAAAAAAAAAAAAAAAAAA   \n\ngdf".getBytes());
		fs.write("content-type: multipart/related; foo=bar\nBBBBB".getBytes());
		fs.close();
		final ByteArrayInputStream is = ios.getInputStream();
		final byte b[] = new byte[100];
		is.read(b);
		final String s = new String(b);
		assertTrue(s.indexOf("related;") < 0);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testMessageID() throws Exception
	{
		final JDFDoc docJMF = new JDFDoc("JMF");
		final JDFJMF jmf = docJMF.getJMFRoot();
		jmf.setSenderID("DeviceID");
		final JDFCommand com = (JDFCommand) jmf.appendMessageElement(JDFMessage.EnumFamily.Command, JDFMessage.EnumType.ReturnQueueEntry);
		final JDFReturnQueueEntryParams returnQEParams = com.appendReturnQueueEntryParams();

		final String queueEntryID = "qe1";
		returnQEParams.setQueueEntryID(queueEntryID);
		final JDFDoc docJDF = new JDFDoc(ElementName.JDF);
		returnQEParams.setURL("cid:dummy"); // will be overwritten by buildMimePackage
		final MimeWriter mw = new MimeWriter();
		mw.buildMimePackage(docJMF, docJDF, false);
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		mw.writeToStream(ios);
		final byte[] b = ios.getBuf();
		final String string = new String(b);
		assertTrue(string.startsWith("MIME-Version"));
		assertEquals(-1, string.indexOf("Message-ID"));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testFixSemicolon2() throws Exception
	{
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		final FixSemiColonStream fs = new FixSemiColonStream(ios);
		fs.write("AAAAAAAAAAAAAAAAAAAA   \n\ngdf".getBytes());
		fs.write("content-type: multipart/related;\nbbbb".getBytes());
		fs.close();
		final ByteArrayInputStream is = ios.getInputStream();
		final byte b[] = new byte[100];
		is.read(b);
		final String s = new String(b);
		assertTrue(s.indexOf("related;") < 0);
		assertTrue(s.indexOf("\nbbb") > 0);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testWriteStream() throws Exception
	{
		final JDFDoc docJMF = new JDFDoc("JMF");
		final JDFJMF jmf = docJMF.getJMFRoot();
		jmf.setSenderID("DeviceID");
		final JDFCommand com = (JDFCommand) jmf.appendMessageElement(JDFMessage.EnumFamily.Command, JDFMessage.EnumType.ReturnQueueEntry);
		final JDFReturnQueueEntryParams returnQEParams = com.appendReturnQueueEntryParams();

		final String queueEntryID = "qe1";
		returnQEParams.setQueueEntryID(queueEntryID);
		final JDFDoc docJDF = new JDFDoc(ElementName.JDF);
		returnQEParams.setURL("cid:dummy"); // will be overwritten by buildMimePackage
		final MimeWriter mw = new MimeWriter();
		mw.buildMimePackage(docJMF, docJDF, false);
		final File f = new File(sm_dirTestDataTemp + "mimestream.mjm");
		assertNotNull(FileUtil.writeFile(mw, f));
		assertTrue(f.exists());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testWriteForm() throws Exception
	{
		final MimeWriter mw = new MimeWriter(eMimeSubType.formdata);
		final BodyPartHelper bph = new BodyPartHelper();
		assertTrue(bph.setContent(new ByteArrayInputStream("foo".getBytes()), UrlUtil.TEXT_PLAIN));
		mw.addBodyPart(bph);
		final File f = new File(sm_dirTestDataTemp + "mimetext.mim");
		assertNotNull(FileUtil.writeFile(mw, f));
		assertTrue(f.exists());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testWriteFormJSON() throws Exception
	{
		final MimeWriter mw = new MimeWriter(eMimeSubType.formdata);
		final BodyPartHelper bph = new BodyPartHelper();
		final byte[] bytes = "{ \"a\":\"€\"}".getBytes();
		assertTrue(bph.setContent(new ByteArrayInputStream(bytes), UrlUtil.VND_XJDF_J));
		mw.addBodyPart(bph);
		final File f = new File(sm_dirTestDataTemp + "mimejson.mim");
		assertNotNull(FileUtil.writeFile(mw, f));
		assertTrue(f.exists());
		final MimeReader mr = new MimeReader(sm_dirTestDataTemp + "mimejson.mim");
		final BodyPartHelper bph2 = mr.getBodyPartHelper(0);
		assertArrayEquals(bytes, Arrays.copyOf(new ByteArrayIOStream(bph2.getInputStream()).getBuf(), bytes.length));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testWriteFormXJDFJSON() throws Exception
	{
		final MimeWriter mw = new MimeWriter(eMimeSubType.formdata);
		final BodyPartHelper bphM = new BodyPartHelper();
		final byte[] bytesM = "{ \"XJMF\":{\"JobID\":\"j1\"}}".getBytes();
		assertTrue(bphM.setContent(new ByteArrayInputStream(bytesM), UrlUtil.VND_XJMF_J));
		bphM.setFileName("submit.xjmf");
		mw.addBodyPart(bphM);
		final byte[] bytes = "{ \"XJDF\":{\"JobID\":\"j1\"}}".getBytes();
		final BodyPartHelper bph = new BodyPartHelper();
		assertTrue(bph.setContent(new ByteArrayInputStream(bytes), UrlUtil.VND_XJDF_J));
		bph.setFileName("submit.xjdf");
		mw.addBodyPart(bph);
		final File f = new File(sm_dirTestDataTemp + "mimexjmf.mim");
		assertNotNull(FileUtil.writeFile(mw, f));
		assertTrue(f.exists());
		final MimeReader mr = new MimeReader(sm_dirTestDataTemp + "mimexjmf.mim");
		final BodyPartHelper bphM2 = mr.getBodyPartHelper(0);
		assertEquals("submit.xjmf", bphM2.getFileName());
		assertArrayEquals(bytesM, Arrays.copyOf(new ByteArrayIOStream(bphM2.getInputStream()).getBuf(), bytes.length));
		final BodyPartHelper bph2 = mr.getBodyPartHelper(1);
		assertArrayEquals(bytes, Arrays.copyOf(new ByteArrayIOStream(bph2.getInputStream()).getBuf(), bytes.length));
		assertEquals("submit.xjdf", bph2.getFileName());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testWriteURL() throws Exception
	{
		final JDFDoc docJMF = new JDFDoc("JMF");
		final JDFJMF jmf = docJMF.getJMFRoot();
		jmf.setSenderID("DeviceID");
		final JDFCommand com = (JDFCommand) jmf.appendMessageElement(JDFMessage.EnumFamily.Command, JDFMessage.EnumType.ReturnQueueEntry);
		final JDFReturnQueueEntryParams returnQEParams = com.appendReturnQueueEntryParams();

		final String queueEntryID = "qe1";
		returnQEParams.setQueueEntryID(queueEntryID);
		final JDFDoc docJDF = new JDFDoc(ElementName.JDF);
		returnQEParams.setURL("cid:dummy"); // will be overwritten by buildMimePackage
		final MimeWriter mw = new MimeWriter();
		mw.buildMimePackage(docJMF, docJDF, false);
		final File f = new File(sm_dirTestDataTemp + "mimeurl.mjm");
		mw.writeToFile(f.getAbsolutePath());
		assertTrue(f.exists());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testExtendRef() throws Exception
	{
		final JDFDoc docJMF = new JDFDoc("JMF");
		final JDFJMF jmf = docJMF.getJMFRoot();
		jmf.setSenderID("DeviceID");
		final JDFCommand com = (JDFCommand) jmf.appendMessageElement(JDFMessage.EnumFamily.Command, JDFMessage.EnumType.ReturnQueueEntry);
		final JDFReturnQueueEntryParams returnQEParams = com.appendReturnQueueEntryParams();

		final String queueEntryID = "qe1";
		returnQEParams.setQueueEntryID(queueEntryID);
		final JDFDoc docJDF = new JDFDoc(ElementName.JDF);
		JDFPreview pv = (JDFPreview) docJDF.getJDFRoot().addResource(ElementName.PREVIEW, EnumUsage.Input);
		pv.setURL(sm_dirTestData + "url1.pdf");
		JDFPreview pv1 = (JDFPreview) docJDF.getJDFRoot().addResource(ElementName.PREVIEW, EnumUsage.Input);
		pv1.setURL(sm_dirTestData + (PlatformUtil.isWindows() ? "URL1.pdf" : "url1.pdf"));
		returnQEParams.setURL("cid:dummy"); // will be overwritten by buildMimePackage
		final MimeWriter mw = new MimeWriter();
		mw.buildMimePackage(docJMF, docJDF, true);
		assertEquals(3, mw.getCount());

		final File f = new File(sm_dirTestDataTemp + "mimeurlpv.mjm");
		mw.writeToFile(f.getAbsolutePath());
		MimeReader mr = new MimeReader(f);
		assertTrue(f.exists());
		assertNotNull(mr.getPartHelperByLocalName("url1.pdf"));
		JDFNode n = mr.getBodyPartHelper(1).getJDFDoc().getJDFRoot();
		JDFPreview pv2 = (JDFPreview) n.getResource(ElementName.PREVIEW);
		assertNotNull(pv2.getURL());
		assertNotNull(pv2.getURLInputStream());
		assertEquals(3, mr.getCount());
		JDFPreview pv3 = (JDFPreview) n.getResource(ElementName.PREVIEW, null, 1);
		assertEquals(pv2.getURL(), pv3.getURL());
		assertNotNull(pv3.getURLInputStream());

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testWriteQueue() throws Exception
	{
		final JDFDoc docJMF = new JDFDoc("JMF");
		final JDFJMF jmf = docJMF.getJMFRoot();
		jmf.setSenderID("DeviceID");
		final JDFCommand com = (JDFCommand) jmf.appendMessageElement(JDFMessage.EnumFamily.Command, JDFMessage.EnumType.ReturnQueueEntry);
		final JDFReturnQueueEntryParams returnQEParams = com.appendReturnQueueEntryParams();

		final String queueEntryID = "qe1";
		returnQEParams.setQueueEntryID(queueEntryID);
		final JDFDoc docJDF = new JDFDoc(ElementName.JDF);
		JDFPreview pv = (JDFPreview) docJDF.getJDFRoot().addResource(ElementName.PREVIEW, EnumUsage.Input);
		pv.setURL(sm_dirTestData + "url1.pdf");
		JDFPreview pv1 = (JDFPreview) docJDF.getJDFRoot().addResource(ElementName.PREVIEW, EnumUsage.Input);
		pv1.setURL(sm_dirTestData + (PlatformUtil.isWindows() ? "URL1.pdf" : "url1.pdf"));
		returnQEParams.setURL("cid:dummy"); // will be overwritten by buildMimePackage
		final MimeWriter mw = new MimeWriter();
		File f = new File(sm_dirTestDataTemp + "mimeurlpv2.mjm");
		mw.writeToQueue(docJMF, docJDF, UrlUtil.fileToUrl(f, false), true);
		assertEquals(3, mw.getCount());

		MimeReader mr = new MimeReader(f);
		assertTrue(f.exists());
		assertNotNull(mr.getPartHelperByLocalName("url1.pdf"));
		JDFNode n = mr.getBodyPartHelper(1).getJDFDoc().getJDFRoot();
		JDFPreview pv2 = (JDFPreview) n.getResource(ElementName.PREVIEW);
		assertNotNull(pv2.getURL());
		assertNotNull(pv2.getURLInputStream());
		assertEquals(3, mr.getCount());
		JDFPreview pv3 = (JDFPreview) n.getResource(ElementName.PREVIEW, null, 1);
		assertEquals(pv2.getURL(), pv3.getURL());
		assertNotNull(pv3.getURLInputStream());

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testWriteQueueNoExtend() throws Exception
	{
		final JDFDoc docJMF = new JDFDoc("JMF");
		final JDFJMF jmf = docJMF.getJMFRoot();
		jmf.setSenderID("DeviceID");
		final JDFCommand com = (JDFCommand) jmf.appendMessageElement(JDFMessage.EnumFamily.Command, JDFMessage.EnumType.ReturnQueueEntry);
		final JDFReturnQueueEntryParams returnQEParams = com.appendReturnQueueEntryParams();

		final String queueEntryID = "qe1";
		returnQEParams.setQueueEntryID(queueEntryID);
		final JDFDoc docJDF = new JDFDoc(ElementName.JDF);
		JDFPreview pv = (JDFPreview) docJDF.getJDFRoot().addResource(ElementName.PREVIEW, EnumUsage.Input);
		pv.setURL(sm_dirTestData + "url1.pdf");
		JDFPreview pv1 = (JDFPreview) docJDF.getJDFRoot().addResource(ElementName.PREVIEW, EnumUsage.Input);
		pv1.setURL(sm_dirTestData + (PlatformUtil.isWindows() ? "URL1.pdf" : "url1.pdf"));
		returnQEParams.setURL("cid:dummy"); // will be overwritten by buildMimePackage
		final MimeWriter mw = new MimeWriter();
		File f = new File(sm_dirTestDataTemp + "mimeurlpv3.mjm");
		mw.writeToQueue(docJMF, docJDF, UrlUtil.fileToUrl(f, false), false);
		assertEquals(2, mw.getCount());

		MimeReader mr = new MimeReader(f);
		assertTrue(f.exists());
		JDFNode n = mr.getBodyPartHelper(1).getJDFDoc().getJDFRoot();
		JDFPreview pv2 = (JDFPreview) n.getResource(ElementName.PREVIEW);
		assertNotNull(pv2.getURL());
		assertNotNull(pv2.getURLInputStream());
		assertEquals(2, mr.getCount());
		JDFPreview pv3 = (JDFPreview) n.getResource(ElementName.PREVIEW, null, 1);
		if (PlatformUtil.isWindows())
			assertNotEquals(pv2.getURL(), pv3.getURL());
		assertNotNull(pv3.getURLInputStream());

	}
}
