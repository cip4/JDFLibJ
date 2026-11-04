/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2026 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.util.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.jupiter.api.Test;

class SecureURIResolverTest extends JDFTestCaseBase
{
	private File prepareRoot() throws IOException
	{
		final File rootDir = new File(sm_dirTestDataTemp + "secureResolver_" + System.currentTimeMillis() + "_" + System.nanoTime());
		SecureURIResolver.clear();
		SecureURIResolver.addRoot(rootDir);
		assertTrue(rootDir.mkdirs() || rootDir.isDirectory());
		return rootDir;
	}

	@Test
	void testConstructorAndToString() throws IOException
	{
		prepareRoot();
		final SecureURIResolver resolver = new SecureURIResolver();
		assertNotNull(resolver);
		assertEquals("SecureURIResolver []", resolver.toString());
	}

	@Test
	void testResolveSingleRelativeHrefAndResolveSuccess() throws IOException, TransformerException
	{
		final File rootDir = prepareRoot();
		final File inRoot = new File(rootDir, "relative-ok.xml");
		assertTrue(FileUtil.createFile(inRoot));
		final SecureURIResolver resolver = new SecureURIResolver();

		final Source single = resolver.resolveSingle("relative-ok.xml", rootDir);
		assertNotNull(single);

		final Source resolved = resolver.resolve("relative-ok.xml", UrlUtil.fileToUrl(rootDir, false));
		assertNotNull(resolved);
	}

	@Test
	void testResolveSingleAbsoluteHrefSuccess() throws IOException
	{
		final File rootDir = prepareRoot();
		final File inRoot = new File(rootDir, "absolute-ok.xml");
		assertTrue(FileUtil.createFile(inRoot));
		final SecureURIResolver resolver = new SecureURIResolver();

		final Source single = resolver.resolveSingle(UrlUtil.fileToUrl(inRoot, false), rootDir);
		assertNotNull(single);
	}

	@Test
	void testResolveSingleMalformedAbsoluteHrefReturnsNull() throws IOException
	{
		final File rootDir = prepareRoot();
		final SecureURIResolver resolver = new SecureURIResolver();

		final Source single = resolver.resolveSingle("http://example.com/not-a-file.xml", rootDir);
		assertNull(single);
	}

	@Test
	void testResolveSingleTraversalOutsideRootReturnsNull() throws IOException
	{
		final File rootDir = prepareRoot();
		final SecureURIResolver resolver = new SecureURIResolver();

		final Source single = resolver.resolveSingle("../outside.xml", rootDir);
		assertNull(single);
	}

	@Test
	void testResolveSingleMissingFileReturnsNull() throws IOException
	{
		final File rootDir = prepareRoot();
		final SecureURIResolver resolver = new SecureURIResolver();

		final Source single = resolver.resolveSingle("missing.xml", rootDir);
		assertNull(single);
	}

	@Test
	void testResolveSingleDirectoryReturnsNull() throws IOException
	{
		final File rootDir = prepareRoot();
		final File folder = new File(rootDir, "dirOnly");
		assertTrue(folder.mkdirs());
		final SecureURIResolver resolver = new SecureURIResolver();

		final Source single = resolver.resolveSingle("dirOnly", rootDir);
		assertNull(single);
	}

	@Test
	void testResolveRejectsIllegalInsecureHref() throws IOException
	{
		final File rootDir = prepareRoot();
		final SecureURIResolver resolver = new SecureURIResolver();

		assertThrows(TransformerException.class, () -> resolver.resolve("../test.xml", UrlUtil.fileToUrl(rootDir, false)));
	}

	@Test
	void testResolveThrowsWhenNoMatchingRoot() throws IOException
	{
		final File rootDir = prepareRoot();
		final SecureURIResolver resolver = new SecureURIResolver();

		assertThrows(TransformerException.class, () -> resolver.resolve("not-present.xml", UrlUtil.fileToUrl(rootDir, false)));
	}

	@Test
	void testResolveThrowsWhenRootsAreEmpty() throws IOException
	{
		final File rootDir = prepareRoot();
		SecureURIResolver.clear();
		final SecureURIResolver resolver = new SecureURIResolver();

		assertThrows(TransformerException.class, () -> resolver.resolve("still-missing.xml", UrlUtil.fileToUrl(rootDir, false)));
	}

	@Test
	void testResolve() throws TransformerException, IOException
	{
		final File rootDir = new File(sm_dirTestDataTemp + "res" + System.currentTimeMillis());
		SecureURIResolver.addRoot(rootDir);
		final SecureURIResolver res = new SecureURIResolver();
		FileUtil.createFile(new File(rootDir, "test.xml"));
		final Source s = res.resolve("test.xml", UrlUtil.fileToUrl(rootDir, false));
		assertNotNull(s);
	}

	@Test
	void testResolveAbs() throws TransformerException, IOException
	{
		final File rootDir = new File(sm_dirTestDataTemp + "res" + System.currentTimeMillis());
		SecureURIResolver.addRoot(rootDir);
		final SecureURIResolver res = new SecureURIResolver();
		final File abs = new File(rootDir, "test.xml");
		FileUtil.createFile(abs);
		final Source s = res.resolve(UrlUtil.fileToUrl(abs, false), UrlUtil.fileToUrl(rootDir, false));
		assertNotNull(s);
	}

	@Test
	void testResolveBad() throws TransformerException, IOException
	{
		final File rootDir = new File(sm_dirTestDataTemp + "res" + System.currentTimeMillis());
		SecureURIResolver.addRoot(rootDir);
		final SecureURIResolver res = new SecureURIResolver();
		FileUtil.createFile(new File(rootDir, "test.xml"));
		assertThrows(TransformerException.class, () -> res.resolve("../test.xml", UrlUtil.fileToUrl(rootDir, false)));

	}

}
