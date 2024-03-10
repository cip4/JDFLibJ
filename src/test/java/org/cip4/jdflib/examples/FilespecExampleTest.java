/*
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
package org.cip4.jdflib.examples;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.MimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilespecExampleTest extends JDFTestCaseBase
{

	JDFNode n;
	JDFFileSpec fs;

	/**
	 * @return
	 */
	@Override
	@BeforeEach
	void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
		n = JDFNode.createRoot();
		n.setJobID("job");
		n.setType(EnumType.ColorCorrection);
		final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Input);
		fs = rl.appendLayoutElement().appendFileSpec();
		setSnippet(fs, true);
	}

	/**
	 *
	 */
	@Test
	void test1()
	{
		fs.setMimeType(MimeUtil.APPLICATION_PDF);
		fs.setURL("file://host/share/dir/a.pdf");
		writeTest(n, "AP_FileSpec/AP_FileSpec_1.jdf", false, null);
	}

	/**
	 *
	 */
	@Test
	void test2()
	{
		fs.setMimeType(MimeUtil.APPLICATION_PDF);
		fs.setURL("a.pdf");
		fs.setContainer("file://host/share/dir/a.gz", MimeUtil.APPLICATION_ZIP);

		writeTest(n, "AP_FileSpec/AP_FileSpec_2.jdf", false, null);
	}

	/**
	 *
	 */
	@Test
	void test3()
	{
		fs.setMimeType(MimeUtil.APPLICATION_PDF);
		fs.setURL("file://host/share/dir/a.pdf");
		fs.setCompression("Base64");
		writeTest(n, "AP_FileSpec/AP_FileSpec_3.jdf", false, null);
	}

	/**
	 *
	 */
	@Test
	void test7()
	{
		fs.setMimeType(MimeUtil.APPLICATION_PDF);
		fs.setURL("a.pdf");
		final JDFFileSpec z1 = fs.setContainer("myNestedZip.zip", MimeUtil.APPLICATION_ZIP);
		z1.setContainer("file://host/share/dir/c.zip", MimeUtil.APPLICATION_ZIP);

		writeTest(n, "AP_FileSpec/AP_FileSpec_7.jdf", false, null);
	}

	/**
	 *
	 */
	@Test
	void test9()
	{
		fs.setMimeType(MimeUtil.APPLICATION_PDF);
		fs.setURL("a.pdf");
		final JDFFileSpec c = fs.setContainer("file://host/share/dir/a.zip", MimeUtil.APPLICATION_ZIP);
		final JDFFileSpec c2 = (JDFFileSpec) c.makeRootResource("ID_Container", null, true);
		setSnippet(c2, true);
		c2.setResStatus(EnumResStatus.Available, true);
		final JDFRunList rl = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Output);
		final JDFFileSpec fs2 = rl.appendLayoutElement().appendFileSpec();
		fs2.appendContainer().refElement(c2);
		fs2.setMimeType(MimeUtil.APPLICATION_PDF);
		fs2.setURL("b.pdf");
		setSnippet(fs2, true);

		writeTest(n, "AP_FileSpec/AP_FileSpec_9.jdf", false, null);
	}

}
