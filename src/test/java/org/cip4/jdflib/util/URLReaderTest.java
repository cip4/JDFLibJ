/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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
package org.cip4.jdflib.util;

import java.io.File;
import java.io.InputStream;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.util.zip.ZipReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 * @date Feb 1, 2012
 */
public class URLReaderTest extends JDFTestCaseBase
{
	/**
	 *
	 *
	 */
	@Test
	void testGetUrlInputStreamLocal()
	{
		final URLReader reader = new URLReader("job.jdf");
		reader.addLocalRoot(new File(sm_dirTestData));
		final InputStream is = reader.getURLInputStream();
		Assertions.assertNotNull(is);
		Assertions.assertTrue(ByteArrayIOStream.getBufferedInputStream(is).available() > 100);
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetFileLocal()
	{
		final URLReader reader = new URLReader("job.jdf");
		reader.addLocalRoot(new File(sm_dirTestData));
		Assertions.assertEquals(UrlUtil.urlToFile(sm_dirTestData + "job.jdf"), reader.getFile());
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetFileFile()
	{
		final String filePath = sm_dirTestData + "job.jdf";
		final File file = new File(filePath);
		final URLReader reader = new URLReader(UrlUtil.fileToUrl(file, false));
		Assertions.assertEquals(file, reader.getFile());
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetXMLDocLocal()
	{
		final URLReader reader = new URLReader("job.jdf");
		reader.addLocalRoot(new File(sm_dirTestData));
		final XMLDoc xmlDoc = reader.getXMLDoc();
		Assertions.assertNotNull(xmlDoc);
		Assertions.assertEquals(StringUtil.token(xmlDoc.getOriginalFileName(), -1, File.separator), "job.jdf");
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetXMLDocLocalDoc()
	{
		final JDFDoc d = JDFDoc.parseFile(sm_dirTestData + "job.jdf");
		final URLReader reader = new URLReader("job.jdf", d);
		final XMLDoc xmlDoc = reader.getXMLDoc();
		Assertions.assertNotNull(xmlDoc);
		Assertions.assertEquals(StringUtil.token(xmlDoc.getOriginalFileName(), -1, File.separator), "job.jdf");
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetXMLDocZip()
	{
		final URLReader reader = new URLReader("schema/BarcodeDetails.jdf");
		final ZipReader zip = new ZipReader(sm_dirTestData + "schema.zip");
		reader.setZipReader(zip);
		final XMLDoc xmlDoc = reader.getXMLDoc();
		Assertions.assertNotNull(xmlDoc);
		Assertions.assertEquals(StringUtil.token(xmlDoc.getOriginalFileName(), -1, "/\\"), "BarcodeDetails.jdf");
	}

	/**
	 *
	 */
	@Test
	void testGetURLInputStreamRedirect()
	{
		if (!isTestNetwork())
			return;
		final URLReader reader = new URLReader("http://google.ch");
		final InputStream is = reader.getURLInputStream();
		Assertions.assertNotNull(is);
		Assertions.assertTrue(ByteArrayIOStream.getBufferedInputStream(is).available() > 100);
	}

	/**
	 *
	 */
	@Test
	void testGetURLInputStreamSecure()
	{
		if (!isTestNetwork())
			return;
		final URLReader reader = new URLReader("https://google.ch");
		final InputStream is = reader.getURLInputStream();
		Assertions.assertNotNull(is);
		Assertions.assertTrue(ByteArrayIOStream.getBufferedInputStream(is).available() > 100);
	}

	/**
	 *
	 */
	@Test
	void testGetURLInputStreamFTP()
	{
		if (!isTestNetwork() || UrlUtilTest.FTP_SITE == null)
			return;
		final URLReader reader = new URLReader(UrlUtilTest.FTP_SITE);
		final InputStream is = reader.getURLInputStream();
		Assertions.assertNotNull(is);
		Assertions.assertTrue(ByteArrayIOStream.getBufferedInputStream(is).available() > 100);
	}

}
