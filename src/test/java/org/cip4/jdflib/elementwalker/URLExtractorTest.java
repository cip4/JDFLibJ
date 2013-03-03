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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.elementwalker;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.MimeUtilTest;
import org.cip4.jdflib.util.UrlUtil.URLProtocol;
import org.cip4.jdflib.util.mime.MimeReader;
import org.cip4.jdflib.util.zip.ZipReader;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class URLExtractorTest extends JDFTestCaseBase {
	/**
	 * @return the created doc
	 * 
	 */
	public JDFDoc testWalk() {
		try {
			new MimeUtilTest().testBuildMimePackageDoc();
		} catch (Exception x) {
			Assert.fail("no build");
		}
		final String mimeFile = sm_dirTestDataTemp + File.separator + "testMimePackageDoc.mjm";

		MimeReader mr = new MimeReader(mimeFile);
		JDFDoc d = mr.getBodyPartHelper(0).getJDFDoc();
		Assert.assertNotNull(d);
		File dumpDir = new File(sm_dirTestDataTemp + File.separator + "URLExtract");
		URLExtractor ex = new URLExtractor(dumpDir, null, "http://foo");
		ex.walkTree(d.getJDFRoot(), null);
		String write2String = d.write2String(0);
		Assert.assertTrue(write2String.indexOf("http://foo/url2.pdf") > 0);
		Assert.assertTrue(FileUtil.getFileInDirectory(dumpDir, new File("url2.pdf")).canRead());
		return d;
	}

	/**
	 * extract from zip stream - also test CommentURL
	 */
	@Test
	public void testReadZip() {
		ZipReader zipReader = new ZipReader(sm_dirTestData + "testZip.zip");
		zipReader.getEntry("dummy.jdf");
		JDFDoc d = zipReader.getJDFDoc();
		Assert.assertNotNull(d);
		File dumpDir = new File(sm_dirTestDataTemp + File.separator + "ZipExtractor");
		URLExtractor ex = new URLExtractor(dumpDir, null, null);
		ex.walkTree(d.getJDFRoot(), null);
		Assert.assertTrue("", FileUtil.getFileInDirectory(dumpDir, new File("content/boo.pdf")).canRead());
		Assert.assertTrue("also extract commenturl", FileUtil.getFileInDirectory(dumpDir, new File("content/commentURL.pdf")).canRead());
	}

	/**
	 * 
	 */
	@Test
	public void testIgnoreSelf() {
		JDFDoc d = testWalk();
		Assert.assertNotNull(d);
		File dumpDir = new File(sm_dirTestDataTemp + File.separator + "URLExtractSelf");
		URLExtractor ex = new URLExtractor(dumpDir, null, "http://foo");
		ex.walkTree(d.getJDFRoot(), null);
		String write2String = d.write2String(0);
		Assert.assertTrue(write2String.indexOf("http://foo/url2.pdf") > 0);
		Assert.assertFalse("we did not dump to #2 since our base is also foo", FileUtil.getFileInDirectory(dumpDir, new File("url2.pdf")).canRead());
	}

	/**
	 * 
	 */
	@Test
	public void testRelativePath() {
		JDFDoc d = new JDFDoc("JDF");
		JDFRunList rl = (JDFRunList) d.getJDFRoot().addResource(ElementName.RUNLIST, EnumUsage.Input);
		rl.addPDF("./content/boo.pdf", 0, -1);
		d.write2File(sm_dirTestDataTemp + "URLIn/dummy.jdf", 2, false);

		FileUtil.createNewFile(new File(sm_dirTestDataTemp + "URLIn/content/boo.pdf"));

		File dumpDir = new File(sm_dirTestDataTemp + File.separator + "URLOut");
		URLExtractor ex = new URLExtractor(dumpDir, sm_dirTestDataTemp + "URLIn", "http://foo");
		ex.walkTree(d.getJDFRoot(), null);
		String write2String = d.write2String(0);
		Assert.assertTrue(write2String.indexOf("http://foo/content/boo.pdf") > 0);
		Assert.assertTrue(new File(sm_dirTestDataTemp + "URLOut/content/boo.pdf").exists());
	}

	/**
	 * 
	 */
	@Test
	public void testAddProtocol() {
		try {
			new MimeUtilTest().testBuildMimePackageDoc();
		} catch (Exception x) {
			Assert.fail("no build");
		}
		final String mimeFile = sm_dirTestDataTemp + File.separator + "testMimePackageDoc.mjm";

		MimeReader mr = new MimeReader(mimeFile);
		JDFDoc d = mr.getBodyPartHelper(0).getJDFDoc();
		Assert.assertNotNull(d);
		File dumpDir = new File(sm_dirTestDataTemp + File.separator + "URLExtract");
		FileUtil.deleteAll(dumpDir);

		URLExtractor ex = new URLExtractor(dumpDir, null, "http://foo");

		// only file protocols are modified
		ex.addProtocol(URLProtocol.file);
		ex.walkTree(d.getJDFRoot(), null);
		String write2String = d.write2String(0);
		Assert.assertTrue(write2String.indexOf("http://foo/url2.pdf") < 0);
		Assert.assertFalse(FileUtil.getFileInDirectory(dumpDir, new File("url2.pdf")).canRead());

		// only file and cid protocols are modified
		ex.addProtocol(URLProtocol.cid);
		ex.walkTree(d.getJDFRoot(), null);
		write2String = d.write2String(0);
		Assert.assertTrue(write2String.indexOf("http://foo/url2.pdf") > 0);
		Assert.assertTrue(FileUtil.getFileInDirectory(dumpDir, new File("url2.pdf")).canRead());
	}
}
