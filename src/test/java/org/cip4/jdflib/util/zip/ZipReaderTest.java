/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.util.zip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Vector;
import java.util.zip.ZipEntry;

import org.apache.commons.io.IOUtils;
import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.XJDFZipWriter;
import org.cip4.jdflib.util.ByteArrayIOFileStream;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.MemorySpy;
import org.cip4.jdflib.util.MemorySpy.MemScope;
import org.cip4.jdflib.util.ThreadUtil;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 * @date Feb 1, 2012
 */
public class ZipReaderTest extends JDFTestCaseBase
{
	/**
	 *
	 *
	 */
	@Test
	void testunpack()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		File dir = new File(sm_dirTestDataTemp + "schema.zip.dir");
		for (int i = 0; i < 3; i++)
		{
			r.buffer();
			FileUtil.deleteAll(dir);
			r.unPack(dir);
			assertTrue(dir.isDirectory());
			final File dir2 = dir = FileUtil.getFileInDirectory(dir, new File("schema"));
			assertEquals(dir2.listFiles().length, 3);
		}
	}

	/**
	 *
	 * @throws IOException
	 */
	@Test
	void testEvilPath() throws IOException
	{
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		final XJDFZipWriter w = new XJDFZipWriter();
		w.addXJDF(h);
		w.addAux("./foo.pdf", FileUtil.getBufferedInputStream(new File(sm_dirTestData + "page.pdf")));
		final File file = new File(sm_dirTestDataTemp + "testaux.zip");
		file.delete();
		FileUtil.writeFile(w, file);
		final ZipReader zr = new ZipReader(file);
		final ZipEntry entry = zr.getEntry("foo.pdf");
		assertNotNull(entry);
		assertNotNull(zr.getInputStream());
		assertTrue(zr.unPack(new File(sm_dirTestDataTemp + "auxevil"), entry));
	}

	/**
	 *
	 * @throws IOException
	 */
	@Test
	void testEvilPath2() throws IOException
	{
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		final XJDFZipWriter w = new XJDFZipWriter();
		w.addXJDF(h);
		w.addAux("./foo.pdf", FileUtil.getBufferedInputStream(new File(sm_dirTestData + "page.pdf")));
		final File file = new File(sm_dirTestDataTemp + "testaux2.zip");
		file.delete();
		FileUtil.writeFile(w, file);
		final ZipReader zr = new ZipReader(file);
		assertEquals(3, zr.unPack(new File(sm_dirTestDataTemp + "aux2")));

	}

	/**
	 *
	 * @throws IOException
	 */
	@Test
	void testAuxPath() throws IOException
	{
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		final XJDFZipWriter w = new XJDFZipWriter();
		w.addXJDF(h);
		w.addAux("foo.pdf", FileUtil.getBufferedInputStream(new File(sm_dirTestData + "page.pdf")));
		final File file = new File(sm_dirTestDataTemp + "testaux3.zip");
		file.delete();
		FileUtil.writeFile(w, file);
		final ZipReader zr = new ZipReader(file);
		assertEquals(3, zr.unPack(new File(sm_dirTestDataTemp + "aux3")));
	}

	/**
	 *
	 *
	 */
	@Test
	void testunpackSingle()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		final File dir = new File(sm_dirTestDataTemp + "schema.zip.dir");
		FileUtil.deleteAll(dir);
		assertFalse(r.unPack(dir, null));
		assertTrue(r.unPack(dir, r.getNextEntry()));
		assertTrue(dir.isDirectory());
		assertEquals(1, dir.listFiles().length);

	}

	/**
	 *
	 *
	 */
	@Test
	void testGetEntryStream()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		ZipEntry e = r.getEntry("schema/Conditions.jdf");
		assertNotNull(r.getInputStream());
		e = r.getEntry("schema/BarcodeDetails.jdf");
		assertNotNull(r.getInputStream());
		e = r.getEntry("schema/Conditions.jdf");
		assertNotNull(r.getInputStream());
		assertNotNull(e);
		r.close();
	}

	/**
	*
	*
	*/
	@Test
	void testNonAscii()
	{
		final File zipFile = new File(sm_dirTestDataTemp + "äöü.zip");
		FileUtil.copyFile(new File(sm_dirTestData + "schema.zip"), zipFile);
		final ZipReader r = new ZipReader(zipFile);
		ZipEntry e = r.getEntry("schema/Conditions.jdf");
		assertNotNull(r.getInputStream());
		e = r.getEntry("schema/BarcodeDetails.jdf");
		assertNotNull(r.getInputStream());
		e = r.getEntry("schema/Conditions.jdf");
		assertNotNull(r.getInputStream());
		assertNotNull(e);
		r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetXMLDoc()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		final ZipEntry e = r.getEntry("schema/Conditions.jdf");
		final XMLDoc xmlDoc = r.getXMLDoc();
		assertNotNull(xmlDoc);
		assertEquals(r, xmlDoc.getZipReader());
		assertNotNull(e);
		r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetJDFDoc()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		final ZipEntry e = r.getMatchingEntry("*Conditions.jdf", 0);
		final XMLDoc xmlDoc = r.getJDFDoc();
		assertNotNull(xmlDoc);
		assertEquals(r, xmlDoc.getZipReader());
		assertNotNull(e);
		r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetEntry()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		ZipEntry e = r.getEntry("schema/Conditions.jdf");
		assertNotNull(e);
		e = r.getEntry("schema/BarcodeDetails.jdf");
		assertNotNull(e);
		e = r.getEntry("schema/Conditions.jdf");
		assertNotNull(e);
		r.close();

	}

	/**
	 * @throws IOException
	 *
	 *
	 */
	@Test
	void testGetStream() throws IOException
	{
		final ByteArrayIOStream crap = new ByteArrayIOStream();
		for (int i = 0; i < 1; i++)
		{
			crap.write('P');
			crap.write('K');
			crap.write(3);
			crap.write(4);
			for (int j = 0; j < 3333; j++)
			{
				crap.write(j % 256);
			}
		}

		final InputStream is = FileUtil.getBufferedInputStream(new File(sm_dirTestData + "schema.zip"));
		IOUtils.copy(is, crap);
		for (int j = 0; j < 333; j++)
		{
			crap.write(j % 256);
		}

		final ZipReader r = ZipReader.getZipReader(crap.getInputStream());
		ZipEntry e = r.getEntry("schema/Conditions.jdf");
		assertNotNull(e);
		e = r.getEntry("schema/BarcodeDetails.jdf");
		assertNotNull(e);
		e = r.getEntry("schema/Conditions.jdf");
		assertNotNull(e);
		r.close();
	}

	/**
	 * @throws IOException
	 *
	 *
	 */
	@Test
	void testGetStreamFile() throws IOException
	{
		final ByteArrayIOStream crap = new ByteArrayIOFileStream(999);
		for (int i = 0; i < 1; i++)
		{
			crap.write('P');
			crap.write('K');
			crap.write(3);
			crap.write(4);
			for (int j = 0; j < 3333; j++)
			{
				crap.write(j % 256);
			}
		}

		final InputStream is = FileUtil.getBufferedInputStream(new File(sm_dirTestData + "schema.zip"));
		IOUtils.copy(is, crap);
		for (int j = 0; j < 333; j++)
		{
			crap.write(j % 256);
		}

		final ZipReader r = ZipReader.getZipReader(crap.getInputStream());
		ZipEntry e = r.getEntry("schema/Conditions.jdf");
		assertNotNull(e);
		e = r.getEntry("schema/BarcodeDetails.jdf");
		assertNotNull(e);
		e = r.getEntry("schema/Conditions.jdf");
		assertNotNull(e);
		r.close();
	}

	/**
	 * @throws IOException write 3 zipfiles with some chunks of crap added in and see if we can unpack them
	 *
	 */
	@Test
	void testGetStreams() throws IOException
	{
		final ByteArrayIOStream crap = new ByteArrayIOStream();
		for (int k = 0; k < 3; k++)
		{
			for (int i = 0; i < 1; i++)
			{
				crap.write('P');
				crap.write('K');
				crap.write(3);
				crap.write(4);
				for (int j = 0; j < 3333; j++)
				{
					crap.write(j % 256);
				}
			}
			final InputStream is = FileUtil.getBufferedInputStream(new File(sm_dirTestData + "schema.zip"));
			IOUtils.copy(is, crap);
			for (int j = 0; j < 3333; j++)
			{
				crap.write(j % 256);
			}
		}
		final Vector<ZipReader> v = ZipReader.getZipReaders(crap.getInputStream(), -1);
		assertEquals(v.size(), 3);
		for (int k = 0; k < 3; k++)
		{
			final ZipReader r = v.get(k);
			ZipEntry e = r.getEntry("schema/Conditions.jdf");
			assertNotNull(e);
			e = r.getEntry("schema/BarcodeDetails.jdf");
			assertNotNull(e);
			e = r.getEntry("schema/Conditions.jdf");
			assertNotNull(e);
		}
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetEntryEscaped()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "testZip.zip");
		assertNotNull(r.getEntry("content/%20.pdf"));
		assertNotNull(r.getEntry("content/a%20b.pdf"));
		assertNotNull(r.getEntry("content/a b.pdf"));
		assertNotNull(r.getEntry("content/ .pdf"));
		r.setCaseSensitive(false);
		assertNotNull(r.getEntry("content/A%20b.pdf"));
		assertNotNull(r.getEntry("content/A b.pdf"));
		assertNotNull(r.getEntry("content/ .Pdf"));
		assertNotNull(r.getEntry("content/%20.Pdf"));
		r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetEntryAutoFileRoot()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		ZipEntry e = r.getEntry("Conditions.jdf");
		assertNotNull(e);
		e = r.getEntry("BarcodeDetails.jdf");
		assertNotNull(e);
		e = r.getEntry("Conditions.jdf");
		assertNotNull(e);
		r.close();
	}

	/**
	 * check that entries with backslash are correctly handled
	 *
	 */
	@Test
	void testunpackBackslash()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "backslash.zip");
		final File dir = new File(sm_dirTestDataTemp + "backslash.zip.dir");
		FileUtil.deleteAll(dir);
		r.unPack(dir);
		assertTrue(dir.isDirectory());
		final File dir2 = FileUtil.getFileInDirectory(dir, new File("backslash/a"));
		assertTrue(dir2.isDirectory());
		assertEquals(dir2.listFiles().length, 2);
	}

	/**
	 * check that entries with backslash are correctly handled
	 *
	 */
	@Test
	void testgetEntryBackslash()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "backslash.zip");
		assertNotNull(r.getEntry("b.jmf"));
		assertNotNull(r.getEntry("c.jmf"));
		assertNotNull(r.getEntry("a/c.jmf"));
		assertNotNull(r.getEntry("backslash/a/c.jmf"));
		assertNotNull(r.getEntry("./backslash/a/c.jmf"));
		assertNotNull(r.getEntry("backslash"));
		r.close();
	}

	/**
	 * check that entries with backslash are correctly handled
	 *
	 */
	@Test
	void testgetMatchingEntryBackslash()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "backslash.zip");
		assertNotNull(r.getMatchingEntry("?.jmf", 0));
		assertNotNull(r.getMatchingEntry("?.jmf", 1));
		assertNull(r.getMatchingEntry("?.jmf", 2));
		r.close();
	}

	/**
	 * check that entries with backslash are correctly handled
	 *
	 */
	@Test
	void testgetNextMatchingEntryBackslash()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "backslash.zip");
		r.buffer();
		assertNotNull(r.getNextMatchingEntry("?.jmf"));
		assertNotNull(r.getNextMatchingEntry("?.jmf"));
		assertNull(r.getNextMatchingEntry("?.jmf"));
		r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testSetRoot()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		r.setRootEntry("schema/");
		ZipEntry e = r.getEntry("Conditions.jdf");
		assertNotNull(e);
		e = r.getEntry("BarcodeDetails.jdf");
		assertNotNull(e);
		e = r.getEntry("Conditions.jdf");
		assertNotNull(e);
		e = r.getMatchingEntry("Condition?.jdf", 0);
		assertNotNull(e);
		r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetBigEntryMemLeak()
	{
		final MemorySpy ms = new MemorySpy();
		System.gc();
		ThreadUtil.sleep(10);
		final long heap0 = ms.getHeapUsed(MemScope.current);
		log.info("testGetBigEntryMemLeak #1 " + ms.getSummary());
		ZipReader r = new ZipReader(sm_dirTestData + "dir1.zip");
		ZipEntry e = r.getEntry("dir1/bigzip.pdf");
		assertNotNull(e);
		log.info("testGetBigEntryMemLeak #2 " + ms.getSummary());
		r.close();
		e = null;
		r = null;
		for (int i = 0; i < 10; i++)
		{
			System.gc();
			ThreadUtil.sleep(10);
		}
		log.info(ms.getSummary());
		final long heapUsed = ms.getHeapUsed(MemScope.current) - heap0;
		log.info("testGetBigEntryMemLeak #3 " + heapUsed + " " + ms.getSummary());
		assertTrue(heapUsed < 42345678, "heapused: " + heapUsed);
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetBigEntry()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "dir1.zip");
		final ZipEntry e = r.getEntry("dir1/bigzip.pdf");
		assertNotNull(e);
		r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetBigEntryStatic()
	{
		final ZipReader r = ZipReader.getZipReader(new File(sm_dirTestData + "dir1.zip"));
		final ZipEntry e = r.getEntry("dir1/bigzip.pdf");
		assertNotNull(e);
		r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetManyBigEntryStatic()
	{
		final ArrayList<ZipReader> l = new ArrayList<>();
		for (int i = 0; i < 4; i++)
		{
			final ZipReader r = ZipReader.getZipReader(new File(sm_dirTestData + "dir1.zip"));
			final ZipEntry e = r.getEntry("dir1/bigzip.pdf");
			assertNotNull(e);
			l.add(r);
			final MemorySpy ms = new MemorySpy();
			log.info(i + " " + ms.getSummary());
		}
		for (final ZipReader r : l)
			r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetManyReadersBigEntryStatic()
	{
		final ArrayList<ZipReader> l = new ArrayList<>();
		for (int i = 0; i < 1; i++)
		{
			final ZipReader r = ZipReader.getZipReaders(FileUtil.getBufferedInputStream(new File(sm_dirTestData + "dir1.zip")), 1).get(0);
			final ZipEntry e = r.getEntry("dir1/bigzip.pdf");
			assertNotNull(e);
			l.add(r);
			final MemorySpy ms = new MemorySpy();
			log.info(i + " " + ms.getSummary());
		}
		for (final ZipReader r : l)
			r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetZipReaderFileNoSearch()
	{
		ZipReader zr = ZipReader.getZipReader(new File(sm_dirTestData + "CIP4 JDFEditor 2.4-SNAPSHOT.exe"), false);
		if (zr == null)
		{
			zr = ZipReader.getZipReader(new File(sm_dirTestData + "CIP4_JDFEditor_2.4-SNAPSHOT.exe"), false);
		}
		assertNull(zr);
	}

	/**
	 * @throws IOException
	 *
	 *
	 */
	@Test
	void testGetBigEntryStream() throws IOException
	{
		final ByteArrayIOStream bos = new ByteArrayIOFileStream(new File(sm_dirTestData + "dir1.zip"), 444444, true);
		final ZipReader r = new ZipReader(bos.getInputStream());
		final ZipEntry e = r.getEntry("dir1/bigzip.pdf");
		assertNotNull(e);
		final InputStream inputStream = r.getInputStream();
		assertNotNull(inputStream);
		int n = 0;
		final byte[] b = new byte[1000];
		while (true)
		{
			final int i = inputStream.read(b);
			n += i;
			if (i < 0)
				break;
		}
		assertTrue(n > 35000000);
		bos.close();
		r.close();
	}

	/**
	 * @throws IOException
	 *
	 *
	 */
	@Test
	void testGetBigEntryStreamGone() throws IOException
	{
		ByteArrayIOStream bos = new ByteArrayIOFileStream(FileUtil.getBufferedInputStream(new File(sm_dirTestData + "dir1.zip")), 4444);
		final ZipReader r = new ZipReader(bos.getInputStream());
		bos = null;
		for (int i = 0; i < 99; i++)
		{
			final byte[] bbb = new byte[1000000];
			System.gc();
		}

		final ZipEntry e = r.getEntry("dir1/bigzip.pdf");
		assertNotNull(e);
		final InputStream inputStream = r.getInputStream();
		assertNotNull(inputStream);
		int n = 0;
		final byte[] b = new byte[10000];
		int nn = 0;
		while (true)
		{
			final int i = inputStream.read(b);
			n += i;
			if (i < 0)
				break;
			if (nn++ % 100 == 0)
			{
				final byte[] bbb = new byte[10000];
				System.gc();
				log.info(nn + " " + i + " " + n);
			}
		}
		assertTrue(n > 35000000);
		r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetBigEntries()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "dir1.zip");
		final Vector<ZipEntry> v = r.getEntries();
		assertNotNull(v);
		r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetMatchingEntry()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		final ZipEntry e = r.getMatchingEntry("*.jdf", 0);
		assertTrue(e.getName().endsWith(".jdf"));
		final ZipEntry e2 = r.getMatchingEntry("*.jdf", 1);
		assertNotNull(e2);
		assertTrue(e2.getName().endsWith(".jdf"));
		final ZipEntry e3 = r.getMatchingEntry("*.jdf", 2);
		assertNull(e3);
		r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetMatchingEntryEscaped()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "testZip.zip");
		assertNotNull(r.getMatchingEntry("*boo.pdf", 0));
		assertNotNull(r.getMatchingEntry("*a%20b.pdf", 0));
		assertNotNull(r.getMatchingEntry("* b.pdf", 0));
		assertNotNull(r.getMatchingEntry("content/???.pdf", 0));
		assertNotNull(r.getMatchingEntry("content/%20*.pdf", 0));
		r.setCaseSensitive(false);
		assertNotNull(r.getMatchingEntry("content/A%20b.pdf", 0));
		assertNotNull(r.getMatchingEntry("content/A b.pdf", 0));
		assertNotNull(r.getMatchingEntry("content/???.Pdf", 0));
		assertNotNull(r.getMatchingEntry("content/%20.Pdf", 0));
		r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetMatchingEntries()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "testZip.zip");
		final Vector<ZipEntry> v = r.getMatchingEntries("*", true);
		r.buffer();
		assertEquals(v.size(), r.getEntries().size());
		for (int i = 0; i < v.size() - 1; i++)
		{
			assertTrue(v.get(i).getName().compareTo(v.get(i + 1).getName()) < 0);
		}
	}

	/**
	 *
	 *
	 */
	@Test
	void testSetEntry()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "testZip.zip");
		final Vector<ZipEntry> v = r.getEntries();
		for (final ZipEntry ze : v)
		{
			assertTrue(r.setEntry(ze));
		}
		assertFalse(r.setEntry(null));
		assertFalse(r.setEntry(new ZipEntry("blub")));
	}

	/**
	 *
	 *
	 */
	@Test
	void testEnsureEntry()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "testZip.zip");
		final ZipEntry e1 = r.ensureEntry();
		assertNotNull(e1);
		final ZipEntry e2 = r.ensureEntry();
		assertEquals(e1, e2);
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetMatchingEntryEscapedFile()
	{
		final ZipReader r = new ZipReader(new File(sm_dirTestData + "testZip.zip"), 33);
		assertNotNull(r.getMatchingEntry("*boo.pdf", 0));
		assertNotNull(r.getMatchingEntry("*a%20b.pdf", 0));
		assertNotNull(r.getMatchingEntry("* b.pdf", 0));
		assertNotNull(r.getMatchingEntry("content/???.pdf", 0));
		assertNotNull(r.getMatchingEntry("content/%20*.pdf", 0));
		r.setCaseSensitive(false);
		assertNotNull(r.getMatchingEntry("content/A%20b.pdf", 0));
		assertNotNull(r.getMatchingEntry("content/A b.pdf", 0));
		assertNotNull(r.getMatchingEntry("content/???.Pdf", 0));
		assertNotNull(r.getMatchingEntry("content/%20.Pdf", 0));
		r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetIgnoreCase()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		ZipEntry e = r.getMatchingEntry("*.jdf", 0);
		assertTrue(e.getName().endsWith(".jdf"));
		final ZipEntry e3 = r.getMatchingEntry("*.JDF", 0);
		assertNull(e3);
		e = r.getEntry("schema/barcodedetails.jdf");
		assertNull(e);
		r.setCaseSensitive(false);
		final ZipEntry e2 = r.getMatchingEntry("*.JDF", 0);
		assertTrue(e2.getName().endsWith(".jdf"));
		e = r.getEntry("schema/barcodedetails.jdf");
		assertNotNull(e);
		r.close();
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetEntries()
	{
		final ZipReader r = new ZipReader(sm_dirTestData + "schema.zip");
		final Vector<ZipEntry> entries = r.getEntries();
		assertEquals(entries.size(), 15);
		r.close();
	}
}
