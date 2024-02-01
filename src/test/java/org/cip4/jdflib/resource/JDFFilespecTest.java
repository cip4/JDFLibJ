/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
 * MediaColorTest.java
 *
 * @author Dietrich Mucha
 *
 * Copyright (C) 2004 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFFileSpec.EnumResourceUsage;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.MimeUtil;
import org.cip4.jdflib.util.MimeUtilTest;
import org.cip4.jdflib.util.PlatformUtil;
import org.cip4.jdflib.util.ThreadUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         26.11.2008
 */
public class JDFFilespecTest extends JDFTestCaseBase
{
	@TempDir
	Path tempDir;

	/**
	 *
	 */
	@Test
	public void testSetAbsoluteURL()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		final JDFFileSpec fs = (JDFFileSpec) n.addResource(ElementName.FILESPEC, null, EnumUsage.Input, null, null, null, null);
		final JDFFileSpec fs2 = (JDFFileSpec) n.addResource(ElementName.FILESPEC, null, EnumUsage.Input, null, null, null, null);
		if (PlatformUtil.isWindows())
		{
			fs.setAbsoluteFileURL(new File("C:\\ist blöd\\fnord is €"), false);
			fs2.setAbsoluteFileURL(new File("C:\\ist blöd\\fnord is €"), true);
			assertEquals(fs.getURL(), "file:///C:/ist%20blöd/fnord%20is%20€");
			assertEquals(fs2.getURL(), "file:///C:/ist%20bl%c3%b6d/fnord%20is%20%e2%82%ac");

		}
	}

	/**
	 *
	 */
	@Test
	public void testURLInput()
	{
		final JDFFileSpec fs = (JDFFileSpec) JDFElement.createRoot(ElementName.FILESPEC);
		assertNull(fs.getURLInputStream());
	}

	/**
	*
	*/
	@Test
	public void testSetFileSize()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		final JDFFileSpec fs = (JDFFileSpec) n.addResource(ElementName.FILESPEC, null, EnumUsage.Input, null, null, null, null);
		assertEquals(-1l, fs.getFileSizeLong());
		fs.setFileSize(42);
		assertEquals(42l, fs.getFileSizeLong());
	}

	/**
	*
	*/
	@Test
	public void testSetNPage()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFFileSpec fs = (JDFFileSpec) n.addResource(ElementName.FILESPEC, null, EnumUsage.Input, null, null, null, null);
		assertEquals(0, fs.getNPage());
		fs.setNPage(42);
		assertEquals(42, fs.getNPage());
	}

	/**
	*
	*/
	@Test
	public void testSetResourceUsage()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		final JDFFileSpec fs = (JDFFileSpec) n.addResource(ElementName.FILESPEC, EnumUsage.Input);
		fs.setResourceUsage(EnumResourceUsage.CIP3);
		assertEquals("CIP3", fs.getResourceUsage());
		assertEquals(EnumResourceUsage.CIP3, fs.getResourceUsageEnum());
	}

	@Test
	public void testGetResourceUsage()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		final JDFFileSpec fs = (JDFFileSpec) n.addResource(ElementName.FILESPEC, EnumUsage.Input);
		fs.setResourceUsage("aa");
		assertEquals("aa", fs.getResourceUsage());
		assertNull(fs.getResourceUsageEnum());
	}

	@Test
	public void testResourceUsageEnum()
	{
		for (final EnumResourceUsage e : EnumResourceUsage.values())
		{
			assertEquals(e, EnumResourceUsage.getEnum(e.name().toLowerCase()));
		}
	}

	/**
	*
	*/
	@Test
	public void testSetCheckSum()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		final JDFFileSpec fs = (JDFFileSpec) n.addResource(ElementName.FILESPEC, null, EnumUsage.Input, null, null, null, null);
		final byte[] b = new byte[] { 0, (byte) 255, (byte) 0x99, 64 };
		fs.setCheckSum(b);
		assertEquals(fs.getCheckSum(), "00FF9940");
		assertEquals(new String(fs.getCheckSumBytes()), new String(b));

	}

	/**
	 * @throws Exception x
	 */
	@Test
	public void testGetURLCidStream() throws Exception
	{
		new MimeUtilTest().testBuildMimePackageDocJMF(tempDir);
		final Path sourceFile = tempDir.resolve("testMimePackageDoc0.mjm");
		assertTrue(Files.isReadable(sourceFile));
		final Multipart mp;
		try (InputStream fileStream = Files.newInputStream(sourceFile))
		{
			mp = MimeUtil.getMultiPart(fileStream);
		}
		final BodyPart bp = MimeUtil.getPartByCID(mp, "jdf.JDF");
		final JDFDoc d = MimeUtil.getJDFDoc(bp);
		final JDFNode n = d.getJDFRoot();
		final JDFColorSpaceConversionParams cscp = (JDFColorSpaceConversionParams) n.getMatchingResource(ElementName.COLORSPACECONVERSIONPARAMS, null, null, 0);
		assertNotNull(cscp);
		final JDFFileSpec fs = cscp.getFinalTargetDevice();
		final InputStream is = fs.getURLInputStream();
		assertNotNull(is);
		final byte b[] = new byte[100];
		final int i = is.read(b);
		assertTrue(i > 0);
		final String s = new String(b);
		assertTrue(s.indexOf("I C C") >= 0);
	}

	/**
	 * @throws Exception x
	 */
	@Test
	public void testLocalGetURLStream() throws Exception
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		JDFRunList rli = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		n.getOwnerDocument_JDFElement().write2File(tempDir.resolve("localjdf.jdf").toFile(), 2, false);
		rli = rli.addRun("dummy.txt", 0, -1);
		final String contents = "Test contents";
		Files.write(tempDir.resolve("dummy.txt"), contents.getBytes());

		try (final InputStream is = rli.getFileSpec().getURLInputStream())
		{
			assertNotNull(is);
			try (ByteArrayIOStream bos = new ByteArrayIOStream(is))
			{
				assertEquals(contents.getBytes().length, bos.size());
			}
		}
	}

	/**
	 * @throws IOException
	 * @throws MessagingException
	 *
	 */
	@Test
	public void testMoveToDir() throws MessagingException, IOException
	{
		new MimeUtilTest().testBuildMimePackageDocJMF(tempDir);
		final Multipart mp;
		try (InputStream fileStream = Files.newInputStream(tempDir.resolve("testMimePackageDoc0.mjm")))
		{
			mp = MimeUtil.getMultiPart(fileStream);
		}
		final BodyPart bp = MimeUtil.getPartByCID(mp, "jdf.JDF");
		final JDFDoc d = MimeUtil.getJDFDoc(bp);
		final JDFNode n = d.getJDFRoot();
		final JDFColorSpaceConversionParams cscp = (JDFColorSpaceConversionParams) n.getMatchingResource(ElementName.COLORSPACECONVERSIONPARAMS, null, null, 0);
		assertNotNull(cscp);
		final JDFFileSpec fs = cscp.getFinalTargetDevice();
		final File newDir = tempDir.resolve("newDir").toFile();
		final File f = UrlUtil.moveToDir(fs, newDir, null, true);
		assertNotNull(f, "error moving file to dir");
		for (int i = 0; i < 10; i++)
		{
			ThreadUtil.sleep(1000);
			if (fs.getURL().contains(UrlUtil.fileToUrl(newDir, false)))
			{
				break;
			}
			log.info("Waiting " + i);
		}
		assertTrue(fs.getURL().contains(UrlUtil.fileToUrl(newDir, false)));
	}

	/**
	 * @throws IOException
	 * @throws MessagingException
	 *
	 */
	@Test
	public void testMoveToDirUserFileName()
	{
		final JDFFileSpec fileSpec = (JDFFileSpec) new JDFDoc(ElementName.FILESPEC).getRoot();
		fileSpec.setURL(sm_dirTestData + "url1.pdf");
		fileSpec.setUserFileName("newName1.pdf");
		final File copy = UrlUtil.moveToDir(fileSpec, tempDir.toAbsolutePath().toFile(), null, true);
		assertEquals(copy.getName(), "newName1.pdf");
		assertEquals(UrlUtil.urlToFile(fileSpec.getURL()), copy);
	}

	/**
	 *
	 */
	@Test
	public void testGetMimeTypeFromURL()
	{
		assertEquals("text/unknown", UrlUtil.getMimeTypeFromURL(null));
		assertEquals("text/unknown", UrlUtil.getMimeTypeFromURL("blubb"));
		assertEquals("application/pdf", UrlUtil.getMimeTypeFromURL("file://a/b/./testtif.foo.PDF"));
		assertEquals("image/tiff", UrlUtil.getMimeTypeFromURL("http://a/b/./testtif.foo.tiff"));
	}

	/**
	 *
	 */
	@Test
	public void testSetMimeURL()
	{
		final JDFDoc d = new JDFDoc("FileSpec");
		final JDFFileSpec fs = (JDFFileSpec) d.getRoot();
		fs.setMimeURL("file:/c/test.pdf");
		assertEquals(fs.getMimeType(), "application/pdf");
		assertEquals(fs.getURL(), "file:/c/test.pdf");
	}

	/**
	 *
	 */
	@Test
	public void testGetFileName()
	{
		final JDFFileSpec fs = (JDFFileSpec) JDFElement.createRoot(ElementName.FILESPEC);

		fs.setMimeURL("file:/c/test.pdf");
		assertEquals(fs.getMimeType(), "application/pdf");
		assertEquals(fs.getFileName(), "test.pdf");
		fs.setUserFileName("dummy.pdf");
		assertEquals(fs.getFileName(), "dummy.pdf");
	}
}
