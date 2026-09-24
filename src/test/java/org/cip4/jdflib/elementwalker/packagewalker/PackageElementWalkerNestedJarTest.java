/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2026 The International Cooperation for the Integration of
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
package org.cip4.jdflib.elementwalker.packagewalker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.elementwalker.PackageElementWalker;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.jupiter.api.Test;

class PackageElementWalkerNestedJarTest extends JDFTestCaseBase
{
	static class RecordingWalker extends PackageElementWalker
	{
		final List<String> constructed = new ArrayList<>();

		RecordingWalker()
		{
			super(new BaseWalkerFactory());
		}

		@Override
		protected BaseWalker constructWalker(final String name)
		{
			if (constructed != null)
			{
				constructed.add(name);
			}
			return super.constructWalker(name);
		}
	}

	@Test
	void testNestedJarConstructsWalker() throws Exception
	{
		new File(sm_dirTestDataTemp).mkdirs();
		final File outerJar = buildNestedOuterJar();
		final RecordingWalker w = new RecordingWalker();
		w.constructed.clear();

		final String outerPath = outerJar.getAbsolutePath().replace('\\', '/');
		String token0 = null;
		for (final String candidate : new String[] { "/" + outerPath, outerPath, "file:/" + outerPath })
		{
			final File resolved = UrlUtil.urlToFile(candidate);
			if (resolved != null && resolved.isFile())
			{
				token0 = candidate;
				break;
			}
		}
		assertNotNull(token0);

		final String url = "jar:nested:" + token0 + "!BOOT-INF/lib/nestedInner.jar!";
		invokeConstructWorkersNestedJar(w, url);

		assertTrue(w.constructed.contains("org.cip4.jdflib.elementwalker.packagewalker.WalkFoo"));
		assertNotNull(w.getFactory());
		assertEquals(WalkFoo.class, w.getFactory().getWalker(new XMLDoc("D", null).getRoot()).getClass());
	}

	@Test
	void testNestedJarMissingOuterDoesNotThrow() throws Exception
	{
		new File(sm_dirTestDataTemp).mkdirs();
		final RecordingWalker w = new RecordingWalker();
		w.constructed.clear();
		final String tempDir = sm_dirTestDataTemp.replace('\\', '/');
		final String normalizedTempDir = tempDir.endsWith("/") ? tempDir : tempDir + "/";
		final String url = "jar:nested:/" + normalizedTempDir + "doesNotExist.jar!BOOT-INF/lib/nestedInner.jar!";

		invokeConstructWorkersNestedJar(w, url);

		assertTrue(w.constructed.isEmpty());
	}

	@Test
	void testNestedJarMalformedUrlDoesNotThrow() throws Exception
	{
		final RecordingWalker w = new RecordingWalker();
		w.constructed.clear();

		invokeConstructWorkersNestedJar(w, "jar:nested:");

		assertFalse(w.constructed.contains("org.cip4.jdflib.elementwalker.packagewalker.WalkFoo"));
		assertTrue(w.constructed.isEmpty());
	}

	private File buildNestedOuterJar() throws IOException
	{
		final File tempDir = new File(sm_dirTestDataTemp);
		tempDir.mkdirs();

		final File innerJar = new File(tempDir, "nestedInner.jar");
		final Map<String, byte[]> innerEntries = new LinkedHashMap<>();
		innerEntries.put("org/cip4/jdflib/elementwalker/packagewalker/WalkFoo.class", "dummy".getBytes(StandardCharsets.US_ASCII));
		writeJar(innerJar, innerEntries);

		final byte[] innerBytes = Files.readAllBytes(innerJar.toPath());
		final File outerJar = new File(tempDir, "nestedOuter.jar");
		final Map<String, byte[]> outerEntries = new LinkedHashMap<>();
		outerEntries.put("BOOT-INF/lib/nestedInner.jar", innerBytes);
		writeJar(outerJar, outerEntries);
		return outerJar;
	}

	private void writeJar(final File jarFile, final Map<String, byte[]> entries) throws IOException
	{
		try (FileOutputStream fos = new FileOutputStream(jarFile); ZipOutputStream zos = new ZipOutputStream(fos))
		{
			for (final Map.Entry<String, byte[]> e : entries.entrySet())
			{
				zos.putNextEntry(new ZipEntry(e.getKey()));
				zos.write(e.getValue());
				zos.closeEntry();
			}
		}
	}

	private void invokeConstructWorkersNestedJar(final RecordingWalker walker, final String url) throws ReflectiveOperationException
	{
		final Method m = PackageElementWalker.class.getDeclaredMethod("constructWorkersNestedJar", String.class);
		m.setAccessible(true);
		m.invoke(walker, url);
	}

}
