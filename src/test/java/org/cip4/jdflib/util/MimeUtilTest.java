/*
 *
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

/*
 * @author muchadie
 */
package org.cip4.jdflib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;

import org.apache.commons.io.IOUtils;
import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.core.XMLParser;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams;
import org.cip4.jdflib.util.MimeUtil.MIMEDetails;
import org.cip4.jdflib.util.mime.BodyPartHelper;
import org.cip4.jdflib.util.mime.MimeReader;
import org.cip4.jdflib.util.mime.MimeWriter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         June 11, 2009
 */
public class MimeUtilTest extends JDFTestCaseBase
{

	@TempDir
	Path testDir;

	@BeforeAll
	static void ensurecopy() throws MessagingException, IOException
	{
		final Path tempPath = new File(sm_dirTestDataTemp).toPath();
		testBuildMimePackageDocJMF(tempPath);
	}

	/**
	 * @throws IOException
	 * @throws MessagingException
	 *
	 */
	synchronized void testBuildMimePackageDocJMF() throws MessagingException, IOException
	{
		// nop
	}

	@Test
	public synchronized void testBuildManyMimePackageDocJMF() throws MessagingException, IOException
	{
		final JDFDoc docJMF = new JDFDoc("JMF");
		docJMF.setOriginalFileName("JMF.jmf");
		final JDFJMF jmf = docJMF.getJMFRoot();
		final JDFCommand com = (JDFCommand) jmf.appendMessageElement(JDFMessage.EnumFamily.Command, JDFMessage.EnumType.SubmitQueueEntry);
		com.appendQueueSubmissionParams().setURL("TheJDF");

		final JDFDoc doc = new JDFDoc("JDF");
		doc.setOriginalFileName("JDF.jdf");
		final JDFNode n = fillCS(doc);

		final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, null, EnumUsage.Input, null, null, null, null);
		rl.addPDF(StringUtil.uncToUrl(sm_dirTestData + File.separator + "page.pdf", false), 0, -1);
		for (int i = 0; i < 2; i++)
		{
			rl.addPDF(StringUtil.uncToUrl(sm_dirTestData + File.separator + "url?.pdf", false), 0, -1);
		}
		for (int ii = 0; ii < 42; ii++)
		{
			final Multipart m = MimeUtil.buildMimePackage(docJMF, doc, false);
			final ByteArrayIOStream bos = new ByteArrayIOStream();
			m.writeTo(bos);
			final String s = new String(bos.getBuf());
			assertNotNull(m);
			assertNotNull(s);
		}
	}

	public static synchronized void testBuildMimePackageDocJMF(final Path tempDir) throws MessagingException, IOException
	{
		for (int ii = 0; ii < 3; ii++)
		{
			final JDFDoc docJMF = new JDFDoc("JMF");
			docJMF.setOriginalFileName("JMF.jmf");
			final JDFJMF jmf = docJMF.getJMFRoot();
			final JDFCommand com = (JDFCommand) jmf.appendMessageElement(JDFMessage.EnumFamily.Command, JDFMessage.EnumType.SubmitQueueEntry);
			com.appendQueueSubmissionParams().setURL("TheJDF");

			final JDFDoc doc = new JDFDoc("JDF");
			doc.setOriginalFileName("JDF.jdf");
			final JDFNode n = fillCS(doc);

			final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, null, EnumUsage.Input, null, null, null, null);
			rl.addPDF(StringUtil.uncToUrl(sm_dirTestData + File.separator + "page.pdf", false), 0, -1);
			for (int i = 0; i < 100; i++)
			{
				rl.addPDF(StringUtil.uncToUrl(sm_dirTestData + File.separator + "url?.pdf", false), 0, -1);
			}
			final Multipart m = MimeUtil.buildMimePackage(docJMF, doc, true);
			MIMEDetails md = new MIMEDetails();
			if (ii == 0)
			{
				md = null;
			}
			else if (ii == 1)
			{
				md.transferEncoding = UrlUtil.BASE64;
			}
			else if (ii == 2)
			{
				md.transferEncoding = UrlUtil.BINARY;
			}
			final File out = MimeUtil.writeToFile(m, tempDir.resolve("testMimePackageDoc" + ii + ".mjm").toString(), md);
			assertTrue(out.canRead());
			final MimeReader mr;
			try (InputStream inputStream = Files.newInputStream(out.toPath()))
			{
				mr = new MimeReader(inputStream);
			}
			final Multipart mp = mr.getMultiPart();
			assertEquals(mr.getBodyParts().length, 5);
			final MimeWriter mw = new MimeWriter(mp);
			mw.writeToDir(new File(UrlUtil.newExtension(out.getPath(), null)));
			ThreadUtil.sleep(123);
			assertTrue(out.renameTo(out), "File '" + out + "' is still locked.");
		}
	}

	/**
	 * @param doc
	 * @return
	 */
	public static JDFNode fillCS(final JDFDoc doc)
	{
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.ColorSpaceConversion);
		final JDFColorSpaceConversionParams cscp = (JDFColorSpaceConversionParams) n.addResource(ElementName.COLORSPACECONVERSIONPARAMS, null, EnumUsage.Input, null, null, null,
				null);
		final JDFFileSpec fs0 = cscp.appendFinalTargetDevice();
		final String unc = sm_dirTestData + File.separator + "test.icc";
		fs0.setURL(StringUtil.uncToUrl(unc, true));
		final JDFFileSpec fs1 = cscp.appendColorSpaceConversionOp().appendSourceProfile();
		final String unc2 = sm_dirTestDataTemp + File.separator + "4% von test äöüß€.icc";
		final String uncToUrl = StringUtil.uncToUrl(unc2, true);
		if (!new File(unc2).exists())
			FileUtil.copyFile(new File(unc), new File(unc2));
		fs1.setURL(uncToUrl);
		return n;
	}

	/**
	 *
	 */
	@Test
	void testGetJMFSubmission()
	{
		final JDFDoc d1 = new JDFDoc("JMF");
		d1.setOriginalFileName("JMF.jmf");
		final JDFJMF jmf = d1.getJMFRoot();
		jmf.setDeviceID("gr?n?");
		final JDFCommand com = (JDFCommand) jmf.appendMessageElement(JDFMessage.EnumFamily.Command, JDFMessage.EnumType.SubmitQueueEntry);

		com.appendQueueSubmissionParams().setURL("TheJDF");

		final JDFDoc doc = new JDFDoc("JDF");
		doc.setOriginalFileName("JDF.jdf");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.ColorSpaceConversion);
		final JDFColorSpaceConversionParams cscp = (JDFColorSpaceConversionParams) n.addResource(ElementName.COLORSPACECONVERSIONPARAMS, null, EnumUsage.Input, null, null, null,
				null);
		final JDFFileSpec fs0 = cscp.appendFinalTargetDevice();
		fs0.setURL(StringUtil.uncToUrl(sm_dirTestData + File.separator + "test.icc", true));
		final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, null, EnumUsage.Input, null, null, null, null);
		rl.addPDF(StringUtil.uncToUrl(sm_dirTestData + File.separator + "url1.pdf", false), 0, -1);
		for (int i = 0; i < 100; i++)
		{
			rl.addPDF("gr?n?" + i + ".pdf", 0, -1);
		}
		final Multipart m = MimeUtil.buildMimePackage(d1, doc, true);

		JDFDoc[] d2 = MimeUtil.getJMFSubmission(m);
		assertNotNull(d2);
		assertEquals("cid:TheJDF.jdf", d2[0].getJMFRoot().getCommand(0).getQueueSubmissionParams(0).getURL());
		assertEquals(d2[1].getJDFRoot().getEnumType(), EnumType.ColorSpaceConversion);

		// now serialize to file and reread - should still work
		MimeUtil.writeToFile(m, sm_dirTestDataTemp + "test2.mjm", null);
		final Multipart m2 = MimeUtil.getMultiPart(sm_dirTestDataTemp + "test2.mjm");
		assertNotNull(m2);
		d2 = MimeUtil.getJMFSubmission(m);
		assertNotNull(d2);
		assertEquals("cid:TheJDF.jdf", d2[0].getJMFRoot().getCommand(0).getQueueSubmissionParams(0).getURL());
		assertEquals(d2[1].getJDFRoot().getEnumType(), EnumType.ColorSpaceConversion);

	}

	/**
	 * @throws Exception
	 */
	@Test
	void testGetPartByCID() throws Exception
	{
		testBuildMimePackageDocJMF();
		final Multipart mp = MimeUtil.getMultiPart(sm_dirTestDataTemp + File.separator + "testMimePackageDoc.mjm");
		final BodyPart bp = MimeUtil.getPartByCID(mp, "TheJDF.jdf");
		assertNotNull(bp);
		assertNull(MimeUtil.getPartByCID(mp, "gipps.nicht"));
		assertEquals(bp.getFileName(), "JDF.jdf");
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testIsMimeMultiPart() throws Exception
	{
		assertTrue(MimeUtil.isMimeMultiPart(MimeUtil.MULTIPART_RELATED));
		assertTrue(MimeUtil.isMimeMultiPart(MimeUtil.MULTIPART_RELATED + "; foo"));
		assertTrue(MimeUtil.isMimeMultiPart(" " + MimeUtil.MULTIPART_RELATED + "; foo"));
		assertTrue(MimeUtil.isMimeMultiPart(" " + MimeUtil.MULTIPART_RELATED.toUpperCase() + "; foo"));
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testIsPPF() throws Exception
	{
		assertTrue(MimeUtil.isPPFMimeType(MimeUtil.VND_PPF));
		assertFalse(MimeUtil.isPPFMimeType(MimeUtil.MULTIPART_RELATED + "; foo"));
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testGetContentID() throws Exception
	{
		testBuildMimePackageDocJMF();
		final Multipart mp = MimeUtil.getMultiPart(sm_dirTestDataTemp + File.separator + "testMimePackageDoc.mjm");
		final BodyPart bp = MimeUtil.getPartByCID(mp, "TheJDF.jdf");
		assertNotNull(bp);
		assertEquals(bp.getFileName(), "JDF.jdf");
		assertEquals("TheJDF.jdf", MimeUtil.getContentID(bp));
		MimeUtil.setContentID(bp, "TheJDF");
		assertEquals("TheJDF", MimeUtil.getContentID(bp));
		MimeUtil.setContentID(bp, "<TheJDF>");
		assertEquals("TheJDF", MimeUtil.getContentID(bp));
		MimeUtil.setContentID(bp, "<cid:TheJDF>");
		assertEquals("TheJDF", MimeUtil.getContentID(bp));
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testGetCreatePartByCID() throws Exception
	{
		final Message message = new MimeMessage((Session) null);
		final Multipart multipart = new MimeMultipart("related"); // JDF:
		// multipart/related
		message.setContent(multipart);
		final BodyPart bp = MimeUtil.getCreatePartByCID(multipart, "cid1");
		bp.setContent("boo", "Text/plain");
		final BodyPart bp2 = MimeUtil.getCreatePartByCID(multipart, "cid2");
		bp2.setContent("bar", "Text/plain");
		final BodyPart bp3 = MimeUtil.getCreatePartByCID(multipart, "cid1");
		assertEquals(bp, bp3);
		assertEquals(multipart.getCount(), 2);
		assertEquals(bp3.getContent(), "boo");
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testGetJDFDoc() throws Exception
	{
		testBuildMimePackageDocJMF();
		final Multipart mp = MimeUtil.getMultiPart(sm_dirTestDataTemp + File.separator + "testMimePackageDoc.mjm");
		final BodyPart bp = MimeUtil.getPartByCID(mp, "TheJDF.jdf");
		assertNotNull(bp);
		assertEquals(bp.getFileName(), "JDF.jdf");
		final JDFDoc d = MimeUtil.getJDFDoc(bp);
		assertNotNull(d);
		final JDFNode n = d.getJDFRoot();
		assertNotNull(n);
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testGetJDFDocFromStream() throws Exception
	{
		testBuildMimePackageDocJMF();
		for (int i = 0; i < 3; i++)
		{
			InputStream fis = FileUtil.getBufferedInputStream(new File(sm_dirTestDataTemp + File.separator + "testMimePackageDoc" + i + ".mjm"));
			fis.mark(9999999);
			final MimeReader mr = new MimeReader();
			mr.setMarkSize(9999999);
			assertNotNull(mr.getJDFDoc(fis, 0));
			fis.reset();
			assertNotNull(mr.getJDFDoc(fis, 1));
			fis.reset();
			assertNull(mr.getJDFDoc(fis, 2));
			fis.close();
			final JDFDoc d = new JDFDoc("JDF");
			d.write2File(new File(sm_dirTestDataTemp + File.separator + "testMimePackageDoc.jdf"));
			fis = new BufferedInputStream(new FileInputStream(new File(sm_dirTestDataTemp + File.separator + "testMimePackageDoc.jdf")));
			assertNotNull(MimeUtil.getJDFDoc(fis, 0));
			fis.close();
			fis = new BufferedInputStream(new FileInputStream(new File(sm_dirTestDataTemp + File.separator + "testMimePackageDoc.jdf")));
			assertNull(MimeUtil.getJDFDoc(fis, 1));
			fis.close();
		}
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testGetJDFDocFromRawStream() throws Exception
	{
		final MimeReader mr = new MimeReader();
		final String xml = "<xml />";
		assertNotNull(mr.getXMLDoc(new ByteArrayIOStream(xml.getBytes()).getInputStream(), 0));
		assertNotNull(mr.getXMLDoc(new ByteArrayIOStream(new FileInputStream(new File(sm_dirTestData + File.separator + "matsch.jdf"))).getInputStream(), 0));
	}

	/**
	 * test for mjd creation
	 *
	 * @throws Exception
	 */
	@Test
	public void testBuildMimePackageDoc() throws Exception
	{
		for (int i = 0; i < 2; i++)
		{
			final JDFDoc doc = new JDFDoc(ElementName.JDF);
			if (i == 1)
			{
				doc.setOriginalFileName("JDF.jdf");
			}
			final JDFNode n = fillCS(doc);

			final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, null, EnumUsage.Input, null, null, null, null);
			rl.addPDF(StringUtil.uncToUrl(sm_dirTestData + File.separator + "url1.pdf", false), 0, -1);
			rl.addPDF(StringUtil.uncToUrl(sm_dirTestData + File.separator + "url1.pdf", false), 0, -1);
			rl.addPDF(StringUtil.uncToUrl(sm_dirTestData + File.separator + "url2.pdf", false), 0, -1);
			final Multipart m = MimeUtil.buildMimePackage(null, doc, true);

			final String mimeFile = sm_dirTestDataTemp + File.separator + "testMimePackageDoc" + (i == 0 ? "0" : "") + ".mjm";
			MimeUtil.writeToFile(m, mimeFile, null);

			final Multipart mp = MimeUtil.getMultiPart(mimeFile);
			assertEquals(mp.getCount(), 5, "JDF, 2* rl, 2 icc");
		}
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testUpdateMultipart() throws Exception
	{
		final MimeWriter mw = new MimeWriter();
		final ByteArrayIOStream bos = new ByteArrayIOStream("abcdefg".getBytes());
		mw.updateMultipart(bos.getInputStream(), "cid", UrlUtil.TEXT_PLAIN);
		final String fileName = sm_dirTestDataTemp + "testUpdateMultipart.mim";
		mw.writeToFile(fileName);
		final BodyPart[] bps = MimeUtil.extractMultipartMime(new FileInputStream(new File(fileName)));
		final InputStream is = bps[0].getInputStream();
		final byte[] b = new byte[10];
		final int n = is.read(b);
		assertEquals(n, 7);
		bos.close();
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testUpdateXMLMultipart() throws Exception
	{
		final Message message = new MimeMessage((Session) null);
		final Multipart multipart = new MimeMultipart("related"); // JDF:
		// multipart/related
		message.setContent(multipart);
		final JDFDoc jDoc = new JDFDoc("JMF");

		MimeUtil.updateXMLMultipart(multipart, jDoc, null);
		final String mimeFile = sm_dirTestDataTemp + File.separator + "testUpdateXML";
		MimeUtil.writeToFile(multipart, mimeFile + "0.mjm", null);
		Multipart multiparsed = MimeUtil.getMultiPart(mimeFile + "0.mjm");
		BodyPart bp = multiparsed.getBodyPart(0);
		assertTrue(bp.getHeader(UrlUtil.CONTENT_ID)[0].length() > 5, "cid >cid_");

		final JDFDoc jDoc1 = new JDFDoc("JDF");
		jDoc1.setOriginalFileName("jdf1.jdf");

		MimeUtil.updateXMLMultipart(multipart, jDoc1, null);

		MimeUtil.writeToFile(multipart, mimeFile + "1.mjm", null);
		multiparsed = MimeUtil.getMultiPart(mimeFile + "1.mjm");
		bp = multiparsed.getBodyPart(0);
		assertTrue(bp.getHeader(UrlUtil.CONTENT_ID)[0].length() > 5, "cid >cid_");

		final JDFDoc jDoc2 = new JDFDoc("JDF");
		jDoc2.setOriginalFileName("jdf1.jdf");
		jDoc2.getJDFRoot().setDescriptiveName("updated jdf");
		MimeUtil.updateXMLMultipart(multipart, jDoc2, "jdf1.jdf");
		MimeUtil.writeToFile(multipart, mimeFile + "2.mjm", null);

		final Multipart multipart3 = MimeUtil.getMultiPart(mimeFile + "2.mjm");
		jDoc2.getJDFRoot().setDescriptiveName("3rd jdf");
		MimeUtil.updateXMLMultipart(multipart3, jDoc2, "jdf2.jdf");
		MimeUtil.writeToFile(multipart3, mimeFile + "3.mjm", null);

	}

	/**
	 * @throws Exception
	 */
	@Test
	void testReplaceContents() throws Exception
	{
		final JDFDoc doc = new JDFDoc("JDF");
		doc.setOriginalFileName("JDF.jdf");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.ColorSpaceConversion);
		final JDFColorSpaceConversionParams cscp = (JDFColorSpaceConversionParams) n.addResource(ElementName.COLORSPACECONVERSIONPARAMS, null, EnumUsage.Input, null, null, null,
				null);
		final JDFFileSpec fs0 = cscp.appendFinalTargetDevice();
		fs0.setURL(StringUtil.uncToUrl(sm_dirTestData + File.separator + "test.icc", true));
		final Multipart mp = MimeUtil.buildMimePackage(null, doc, true);
		assertEquals(mp.getCount(), 2, "JDF,  1 icc");

		final BodyPart bp = mp.getBodyPart(1);
		assertNotNull(bp);
		final String newContents = "hello World";
		bp.setContent(newContents, "text/plain");

		final BodyPart bp2 = mp.getBodyPart(1);
		final Object o = bp2.getContent();
		assertEquals(o.toString(), "hello World");

		final String mimeFile = sm_dirTestDataTemp + File.separator + "testReplaceContents";
		MimeUtil.writeToFile(mp, mimeFile + ".mjm", null);

		final Multipart mp2 = MimeUtil.getMultiPart(mimeFile + ".mjm");
		final BodyPart bp21 = mp2.getBodyPart(1);
		final Object o2 = bp21.getContent();
		assertEquals(o2.toString(), "hello World");

		final BodyPart bp22 = mp2.getBodyPart(1);
		assertNotNull(bp22);
		final String newContents2 = "bye World";
		bp22.setContent(newContents2, "text/plain");
		final Object o22 = bp22.getContent();
		assertEquals(o22.toString(), "bye World");
		final BodyPart bp23 = mp2.getBodyPart(1);
		assertEquals(bp22, bp23);

		MimeUtil.writeToFile(mp2, mimeFile + "_1.mjm", null);
		final Multipart mp3 = MimeUtil.getMultiPart(mimeFile + "_1.mjm");
		final BodyPart bp31 = mp3.getBodyPart(1);
		final Object o3 = bp31.getContent();
		assertEquals(o3.toString(), "bye World");

	}

	/**
	 * Tests that MimeUtil can resolve FileSpec URLs with relative URLs.
	 *
	 * @author Claes Buckwalter
	 */
	@Test
	void testResolveRelativeUrls()
	{
		// Build MIME package
		final String path = sm_dirTestData + File.separator + "MISPrepress-ICS-Complex.jdf";
		final JDFDoc jdfDoc = new JDFParser().parseFile(path);
		assertNotNull(jdfDoc, "Could not parse JDF: " + path);
		final Multipart multipart = MimeUtil.buildMimePackage(null, jdfDoc, true);
		assertNotNull(multipart, "Could not build multipart");
		// Verify contents
		final BodyPart[] bodyParts = MimeUtil.getBodyParts(multipart);
		assertEquals(3, bodyParts.length);
		final JDFDoc jdfDoc2 = MimeUtil.getJDFDoc(bodyParts[0]);
		assertNotNull(jdfDoc2);
		final JDFNode jdf = jdfDoc2.getJDFRoot();
		assertNotNull(jdf);
		final VElement fileSpecs = jdf.getChildrenByTagName(ElementName.FILESPEC, null, new JDFAttributeMap(AttributeName.URL, "*"), false, false, 0);
		assertEquals(3, fileSpecs.size());
		for (final KElement kElement : fileSpecs)
		{
			final JDFFileSpec fileSpec = (JDFFileSpec) kElement;
			final String cid = fileSpec.getURL();
			assertTrue(cid.startsWith("cid:"));
			assertNotNull(MimeUtil.getPartByCID(multipart, cid));
		}

	}

	// ///////////////////////////////////////////////////

	/**
	 * @throws Exception
	 */
	@Test
	void testBuildMimePackage() throws Exception
	{
		final JDFDoc d1 = new JDFDoc("JMF");
		d1.setOriginalFileName("JMF.jmf");
		final JDFDoc d2 = new JDFDoc("JDF");
		d2.setOriginalFileName("JDF.jdf");
		final Vector<JDFDoc> vXMLDocs = new Vector<>();
		vXMLDocs.add(d1);
		vXMLDocs.add(d2);

		final Multipart m = MimeUtil.buildMimePackage(vXMLDocs);
		final File file = MimeUtil.writeToFile(m, sm_dirTestDataTemp + File.separator + "test.mjm", null);

		final FileInputStream fis = new FileInputStream(file);
		final BodyPart[] aBp = MimeUtil.extractMultipartMime(fis);
		assertEquals(aBp.length, 2);

		assertEquals(aBp[0].getFileName(), "JMF.jmf");
		assertEquals(aBp[1].getFileName(), "JDF.jdf");
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testMeanCharsXML() throws Exception
	{
		final XMLParser p = new XMLParser();
		final XMLDoc d = p.parseString("<foo a=\"SchuÌˆtz_Teil5_bel\"/>");
		d.setOriginalFileName("foo.xml");
		final Vector<XMLDoc> vXMLDocs = new Vector<>();
		vXMLDocs.add(d);

		final Multipart m = MimeUtil.buildMimePackage(vXMLDocs);
		final File file = MimeUtil.writeToFile(m, sm_dirTestDataTemp + File.separator + "nasty.mjm", new MIMEDetails());

		final FileInputStream fis = new FileInputStream(file);
		final BodyPart[] aBp = MimeUtil.extractMultipartMime(fis);
		assertEquals(aBp.length, 1);

		assertEquals(aBp[0].getFileName(), "foo.xml");
		final BodyPartHelper bh = new BodyPartHelper(aBp[0]);
		final XMLDoc xmlDoc = bh.getXMLDoc();
		assertNotNull(xmlDoc);
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testWriteToDir() throws Exception
	{
		testBuildMimePackageDocJMF(testDir);

		final Multipart mp;
		try (InputStream mimePackageStream = Files.newInputStream(testDir.resolve("testMimePackageDoc0.mjm")))
		{
			mp = MimeUtil.getMultiPart(mimePackageStream);
		}
		assertNotNull(mp);
		final Path baseDir = testDir.resolve("TestWriteMime2");
		final File directory = baseDir.toFile();
		if (Files.exists(baseDir))
		{
			Files.delete(baseDir);
		}
		MimeUtil.writeToDir(mp, directory);
		assertTrue(Files.exists(baseDir.resolve("test.icc")));
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testWriteToDirDown() throws Exception
	{
		final Multipart mp = MimeUtil.getMultiPart(sm_dirTestData + "evil.mjm");

		final String baseDir = sm_dirTestDataTemp + "TestWriteMimeDown";
		final File directory = new File(baseDir);
		if (directory.exists())
		{
			directory.delete();
		}
		MimeUtil.writeToDir(mp, directory);
		assertTrue(new File(directory, "OtherJDF.jdf").exists());
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testWriteToFile() throws Exception
	{
		testBuildMimePackageDocJMF();

		final Multipart mp = MimeUtil.getMultiPart(sm_dirTestDataTemp + File.separator + "testMimePackageDoc.mjm");
		MimeUtil.writeToFile(mp, sm_dirTestDataTemp + File.separator + "testMimePackageDoc2.mjm", null);
		final File f1 = new File(sm_dirTestDataTemp + File.separator + "testMimePackageDoc2.mjm");
		final File f2 = new File(sm_dirTestDataTemp + File.separator + "testMimePackageDoc.mjm");
		assertTrue(f1.exists());
		assertEquals(f1.length(), f2.length(), 100);
		final Multipart mp2 = MimeUtil.getMultiPart(sm_dirTestDataTemp + File.separator + "testMimePackageDoc2.mjm");
		assertNotNull(mp2);
		assertEquals(mp.getCount(), mp2.getCount());

	}

	// /////////////////////////////////////////////////////////////

	/**
	 * @throws Exception
	 */
	@Test
	void testWriteToFileMimeDetails() throws Exception
	{
		testBuildMimePackageDocJMF();

		final MIMEDetails md = new MIMEDetails();
		md.modifyBoundarySemicolon = true;
		final Multipart mp = MimeUtil.getMultiPart(sm_dirTestDataTemp + File.separator + "testMimePackageDoc1.mjm");
		MimeUtil.writeToFile(mp, sm_dirTestDataTemp + File.separator + "testMimePackageDoc4.mjm", md);
		final File f2 = new File(sm_dirTestDataTemp + File.separator + "testMimePackageDoc4.mjm");
		final File f1 = new File(sm_dirTestDataTemp + File.separator + "testMimePackageDoc1.mjm");
		assertTrue(f2.exists());
		assertEquals(f1.length(), f2.length(), 100);
		final Multipart mp2 = MimeUtil.getMultiPart(sm_dirTestDataTemp + File.separator + "testMimePackageDoc4.mjm");
		assertNotNull(mp2);
		assertEquals(mp.getCount(), mp2.getCount());
		final StringWriter sw = new StringWriter();
		IOUtils.copy(new FileInputStream(f2), sw);
		assertEquals(sw.getBuffer().toString().indexOf("related;"), -1);

	}

	/**
	 * @throws Exception
	 */
	@Test
	void testWriteToFileMimeMessage() throws Exception
	{
		testBuildMimePackageDocJMF();

		final File f1 = new File(sm_dirTestDataTemp + File.separator + "testMimePackageDoc1.mjm");

		final StringWriter sw = new StringWriter();
		IOUtils.copy(new FileInputStream(f1), sw);
		assertEquals(sw.getBuffer().toString().indexOf("Message-ID"), -1);

	}

	/**
	 * @throws Exception
	 */
	@Test
	void testWriteToURLFile() throws Exception
	{
		testBuildMimePackageDocJMF();

		final Multipart mp = MimeUtil.getMultiPart(sm_dirTestDataTemp + File.separator + "testMimePackageDoc.mjm");
		final File f1 = new File(sm_dirTestDataTemp + File.separator + "testMimePackageDoc3.mjm");
		MimeUtil.writeToURL(mp, UrlUtil.fileToUrl(f1, false));
		final File f2 = new File(sm_dirTestDataTemp + File.separator + "testMimePackageDoc.mjm");
		assertTrue(f1.exists());
		assertEquals(f1.length(), f2.length(), 100);
	}

	// /////////////////////////////////////////////////////

	/**
	 * @throws Exception
	 */
	@Test
	void testPerformance() throws Exception
	{
		testWritePerformance();

		final long write = System.currentTimeMillis();
		final Multipart mp = MimeUtil.getMultiPart(sm_dirTestDataTemp + File.separator + "performance.mjm");
		final long getMP = System.currentTimeMillis();
		System.out.println("get multipart time: " + (getMP - write));
		final BodyPartHelper bp = MimeUtil.getPartByFileName(mp, "bigger.pdf");
		final long getCID = System.currentTimeMillis();
		System.out.println("get big time: " + (getCID - getMP));
		assertNotNull(bp);
		assertEquals(bp.getFileName(), "bigger.pdf");
		final File outFile = FileUtil.streamToFile(bp.getInputStream(), sm_dirTestDataTemp + File.separator + "performance.pdf");
		assertNotNull(outFile);
		/*
		 * InputStream is=bp.getInputStream(); byte[] b=new byte[1000]; int l=0; while (true) { int i=is.read(b); if(i<=0) break; l+=i; }
		 */
		final long extract = System.currentTimeMillis();
		System.out.println("extracted  bytes in time: " + (extract - getCID));

	}

	/**
	 * @throws Exception
	 */
	@Test
	void testPerformanceGet() throws Exception
	{
		testWritePerformance();
		final long write = System.currentTimeMillis();
		final Multipart mp = MimeUtil.getMultiPart(sm_dirTestDataTemp + File.separator + "performance.mjm");
		final long getMP = System.currentTimeMillis();
		System.out.println("get multipart time: " + (getMP - write));
		final BodyPartHelper bp = MimeUtil.getPartByFileName(mp, "bigger.pdf");
		final long getCID = System.currentTimeMillis();
		System.out.println("get big time: " + (getCID - getMP));
		assertNotNull(bp);
		assertEquals(bp.getFileName(), "bigger.pdf");
	}

	// /////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	void testURLPerformance()
	{
		try
		{
			final long write = System.currentTimeMillis();
			final String mjm = sm_dirTestDataTemp + File.separator + "performance.mjm";
			if (!new File(mjm).exists())
			{
				testWritePerformance();
			}
			final Multipart mp = MimeUtil.getMultiPart(mjm);
			final long getMP = System.currentTimeMillis();
			System.out.println("get multipart time: " + (getMP - write));
			final BodyPartHelper bp = MimeUtil.getPartByFileName(mp, "bigger.pdf");
			final long getCID = System.currentTimeMillis();
			System.out.println("get big time: " + (getCID - getMP));
			assertNotNull(bp);
			assertEquals(bp.getFileName(), "bigger.pdf");
			final HttpURLConnection uc = MimeUtil.writeToURL(mp, "http://localhost:8080/JDFUtility/dump");
			final InputStream is = uc.getInputStream();
			IOUtils.copy(is, System.out);
			is.close();
			System.out.println();
			// System.out.println("extracted "+l+" bytes in time: "+(extract-
			// getCID));
			final long extract = System.currentTimeMillis();
			System.out.println("sent  bytes in time: " + (extract - getCID));
		}
		catch (final Exception x)
		{
			// nop
		}
	}

	// /////////////////////////////////////////////////////

	/**
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws MalformedURLException
	 */
	@Test
	void testWritePerformance() throws IOException, FileNotFoundException, MalformedURLException
	{
		final long start = System.currentTimeMillis();
		final String big = sm_dirTestData + File.separator + "big.pdf";
		final String bigger = sm_dirTestDataTemp + File.separator + "bigger.pdf";
		final JDFDoc docJMF = new JDFDoc("JMF");
		docJMF.setOriginalFileName("JMF.jmf");
		final JDFJMF jmf = docJMF.getJMFRoot();
		final JDFCommand com = (JDFCommand) jmf.appendMessageElement(JDFMessage.EnumFamily.Command, JDFMessage.EnumType.SubmitQueueEntry);
		com.appendQueueSubmissionParams().setURL("TheJDF");
		final File fBigger = new File(bigger);
		fBigger.createNewFile();
		final FileInputStream fis = new FileInputStream(big);
		final FileOutputStream fos = new FileOutputStream(bigger);
		final byte[] b = new byte[10000];
		while (true)
		{
			final int i = fis.read(b);
			if (i <= 0)
			{
				break;
			}
			for (int j = 0; j < 3; j++)
			{
				fos.write(b, 0, i);
			}
		}
		fis.close();
		fos.flush();
		fos.close();

		final JDFDoc doc = new JDFDoc("JDF");
		doc.setOriginalFileName("JDF.jdf");
		final JDFNode n = doc.getJDFRoot();
		n.setType(EnumType.Interpreting);
		final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		rl.addPDF(StringUtil.uncToUrl(bigger, false), 0, -1);
		final long setup = System.currentTimeMillis();
		System.out.println("Setup time: " + (setup - start));
		final Multipart m = MimeUtil.buildMimePackage(null, doc, true);
		final long build = System.currentTimeMillis();
		System.out.println("Build time: " + (build - setup));
		assertNotNull(MimeUtil.writeToFile(m, sm_dirTestDataTemp + "performance.mjm", null));
		final long write = System.currentTimeMillis();
		System.out.println("Write time: " + (write - build));

	}
}
