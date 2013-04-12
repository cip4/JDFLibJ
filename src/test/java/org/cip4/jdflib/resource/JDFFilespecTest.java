/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of
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
/*
 * MediaColorTest.java
 * @author Dietrich Mucha
 * 
 * Copyright (C) 2004 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.MimeUtil;
import org.cip4.jdflib.util.MimeUtilTest;
import org.cip4.jdflib.util.ThreadUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.Assert;
import org.junit.Test;
////////////////////////////////////////////////////////////////
/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 26.11.2008
 */
public class JDFFilespecTest extends JDFTestCaseBase
{
	/**
	 * 
	 */
	@Test
	public void testSetAbsoluteURL()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		final JDFFileSpec fs = (JDFFileSpec) n.addResource("FileSpec", null, EnumUsage.Input, null, null, null, null);
		final JDFFileSpec fs2 = (JDFFileSpec) n.addResource("FileSpec", null, EnumUsage.Input, null, null, null, null);
		if (FileUtil.isWindows())
		{
			fs.setAbsoluteFileURL(new File("C:\\ist blöd\\fnord is €"), false);
			fs2.setAbsoluteFileURL(new File("C:\\ist blöd\\fnord is €"), true);
			Assert.assertEquals(fs.getURL(), "file:///C:/ist%20blöd/fnord%20is%20€");
			Assert.assertEquals(fs2.getURL(), "file:///C:/ist%20bl%c3%b6d/fnord%20is%20%e2%82%ac");

		}
	}

	/**
	 * @throws Exception x
	 */
	@Test
	public void testGetURLCidStream() throws Exception
	{
		new MimeUtilTest().testBuildMimePackageDocJMF();
		final String fileName = sm_dirTestDataTemp + "testMimePackageDoc0.mjm";
		Assert.assertTrue(new File(fileName).canRead());
		final Multipart mp = MimeUtil.getMultiPart(fileName);
		final BodyPart bp = MimeUtil.getPartByCID(mp, "jdf.JDF");
		final JDFDoc d = MimeUtil.getJDFDoc(bp);
		final JDFNode n = d.getJDFRoot();
		final JDFColorSpaceConversionParams cscp = (JDFColorSpaceConversionParams) n.getMatchingResource(ElementName.COLORSPACECONVERSIONPARAMS, null, null, 0);
		Assert.assertNotNull(cscp);
		final JDFFileSpec fs = cscp.getFinalTargetDevice();
		final InputStream is = fs.getURLInputStream();
		Assert.assertNotNull(is);
		final byte b[] = new byte[100];
		final int i = is.read(b);
		Assert.assertTrue(i > 0);
		final String s = new String(b);
		Assert.assertTrue(s.indexOf("I C C") >= 0);
	}

	/**
	 * @throws Exception x
	 */
	@Test
	public void testLocalGetURLStream() throws Exception
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		JDFRunList rli = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		n.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "localjdf.jdf", 2, false);
		rli = rli.addRun("dummy.txt", 0, -1);
		String contents = "Test contents";
		ByteArrayIOStream bos = new ByteArrayIOStream(contents.getBytes());
		FileUtil.streamToFile(bos.getInputStream(), sm_dirTestDataTemp + "dummy.txt");
		InputStream is = rli.getFileSpec().getURLInputStream();
		Assert.assertNotNull(is);
		ByteArrayIOStream bos2 = new ByteArrayIOStream(is);
		Assert.assertEquals(contents.getBytes().length, bos2.size());
	}

	/**
	 * @throws IOException
	 * @throws MessagingException
	 * 
	 */
	@Test
	public void testMoveToDir() throws MessagingException, IOException
	{
		new MimeUtilTest().testBuildMimePackageDocJMF();
		final Multipart mp = MimeUtil.getMultiPart(sm_dirTestDataTemp + "testMimePackageDoc0.mjm");
		final BodyPart bp = MimeUtil.getPartByCID(mp, "jdf.JDF");
		final JDFDoc d = MimeUtil.getJDFDoc(bp);
		final JDFNode n = d.getJDFRoot();
		final JDFColorSpaceConversionParams cscp = (JDFColorSpaceConversionParams) n.getMatchingResource(ElementName.COLORSPACECONVERSIONPARAMS, null, null, 0);
		Assert.assertNotNull(cscp);
		final JDFFileSpec fs = cscp.getFinalTargetDevice();
		final File newDir = new File(sm_dirTestDataTemp + "newDir");
		File f = UrlUtil.moveToDir(fs, newDir, null, true);
		Assert.assertNotNull("error moving file to dir", f);
		for (int i = 0; i < 10; i++)
		{
			ThreadUtil.sleep(1000);
			if (fs.getURL().contains(UrlUtil.fileToUrl(newDir, false)))
			{
				break;
			}
			System.out.println("Waiting " + i);
		}
		Assert.assertTrue(fs.getURL().contains(UrlUtil.fileToUrl(newDir, false)));

	}

	// //////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testGetMimeTypeFromURL()
	{
		Assert.assertEquals("text/unknown", UrlUtil.getMimeTypeFromURL(null));
		Assert.assertEquals("text/unknown", UrlUtil.getMimeTypeFromURL("blubb"));
		Assert.assertEquals("application/pdf", UrlUtil.getMimeTypeFromURL("file://a/b/./testtif.foo.PDF"));
		Assert.assertEquals("image/tiff", UrlUtil.getMimeTypeFromURL("http://a/b/./testtif.foo.tiff"));
	}

	// //////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testSetMimeURL()
	{
		final JDFDoc d = new JDFDoc("FileSpec");
		final JDFFileSpec fs = (JDFFileSpec) d.getRoot();
		fs.setMimeURL("file:/c/test.pdf");
		Assert.assertEquals(fs.getMimeType(), "application/pdf");
		Assert.assertEquals(fs.getURL(), "file:/c/test.pdf");

	}
	// //////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////
}
