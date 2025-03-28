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
 * 04022005 VF initial version
 */
/*
 * Created on Aug 26, 2004
 *
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.cip4.jdflib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
class FileUtilTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	void testisAbsoluteFile()
	{
		assertFalse(FileUtil.isAbsoluteFile(new File("foo")));
		assertFalse(FileUtil.isAbsoluteFile("foo"));

		if (PlatformUtil.isWindows())
		{
			assertTrue(FileUtil.isAbsoluteFile(new File("c:\\")));
			assertTrue(FileUtil.isAbsoluteFile("c:\\"));
			assertTrue(FileUtil.isAbsoluteFile(new File("c:\\a")));
			assertTrue(FileUtil.isAbsoluteFile("c:\\a"));
			assertTrue(FileUtil.isAbsoluteFile(new File("c:/a")));
		}
	}

	/**
	 *
	 */
	@Test
	void testAddSecure()
	{
		assertThrows(IllegalArgumentException.class, () -> FileUtil.addSecure(new File("."), null));
		assertThrows(IllegalArgumentException.class, () -> FileUtil.addSecure(null, new File("a")));
		assertThrows(IllegalArgumentException.class, () -> FileUtil.addSecure(new File("abc"), new File("..")));
		assertThrows(IllegalArgumentException.class, () -> FileUtil.addSecure(new File("abc/../a"), new File("a")));
		assertThrows(IllegalArgumentException.class, () -> FileUtil.addSecure(new File("abc"), new File("a/../b")));
		assertEquals("abc" + File.separatorChar + "a", FileUtil.addSecure(new File("abc"), new File("a")).getPath());
		assertEquals("abc" + File.separatorChar + "a" + File.separatorChar + "b", FileUtil.addSecure(new File("abc"), new File("a/b")).getPath());

	}

	/**
	 *
	 */
	@Test
	void testGetSecure()
	{
		assertEquals("abc", FileUtil.getSecureFileName(new File("abc")));
		assertEquals("invalid name", FileUtil.getSecureFileName(new File("../abc")));
		assertThrows(IllegalArgumentException.class, () -> FileUtil.getSecureName(new File("../abc")));
		assertThrows(IllegalArgumentException.class, () -> FileUtil.getSecurePath(new File("../abc"), true));
		assertThrows(IllegalArgumentException.class, () -> FileUtil.getSecurePath(new File("/abc"), false));
	}

	/**
	 *
	 */
	@Test
	void testDumpException()
	{
		final File npe = new File(sm_dirTestDataTemp + "npe.txt");
		FileUtil.dumpException(npe, new NullPointerException("foo"));
		final String fileToString = FileUtil.fileToString(npe, null);
		assertNotNull(fileToString);
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testisLocked() throws IOException
	{
		final File file = new File(sm_dirTestDataTemp + "no such file");
		FileUtil.forceDelete(file);
		assertFalse(FileUtil.isLocked(file));
		final File f = new File(sm_dirTestDataTemp + "test");
		FileUtil.forceDelete(f);
		final boolean b = f.createNewFile();
		ThreadUtil.sleep(12);
		assertEquals(!b, FileUtil.isLocked(f));
	}

	/**
	 *
	 */
	@Test
	void testisDirectory()
	{
		assertFalse(FileUtil.isDirectory(new File("foo")));
		assertFalse(FileUtil.isDirectory("foo"));
		assertFalse(FileUtil.isDirectory((String) null));
		assertFalse(FileUtil.isDirectory((File) null));
		assertTrue(FileUtil.isDirectory("/"));
		assertTrue(FileUtil.isDirectory(sm_dirTestData + "SampleFiles"));
		// assertTrue(FileUtil.isDirectory(sm_dirTestData + "Samples2"));

	}

	/**
	 *
	 */
	@Test
	void testGetExtension()
	{
		assertNull(FileUtil.getExtension(new File("foo")));
		assertEquals("txt", FileUtil.getExtension(new File("foo.txt")));
	}

	/**
	 *
	 */
	@Test
	void testGetAuxDir()
	{
		final File theHFDir = new File(sm_dirTestDataTemp + File.separator + "FooAux");
		FileUtil.deleteAll(theHFDir);
		theHFDir.mkdirs();
		final File aaa = FileUtil.getFileInDirectory(theHFDir, new File("aaa.txt"));
		FileUtil.createNewFile(aaa);

		assertNull(FileUtil.getAuxDir(aaa));
		final File aaaDir = FileUtil.newExtension(aaa, null);
		aaaDir.mkdirs();
		assertEquals(aaaDir, FileUtil.getAuxDir(aaa));
		final File aaaDir2 = new File(aaaDir.getAbsolutePath() + ".dir");
		aaaDir.renameTo(aaaDir2);
		assertEquals(aaaDir2, FileUtil.getAuxDir(aaa));
		assertNull(FileUtil.getAuxDir(new File(".xyz")));

	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testGetBufferedOutputStream() throws IOException
	{
		final File file = new File(sm_dirTestDataTemp + "bufOut.txt");
		file.delete();
		final OutputStream os = FileUtil.getBufferedOutputStream(file);
		os.write("abc".getBytes());
		os.close();
		assertTrue(file.exists());
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testGetBufferedInputStream() throws IOException
	{
		final File file = new File(sm_dirTestDataTemp + "bufOut.txt");
		file.delete();
		final OutputStream os = FileUtil.getBufferedOutputStream(file);
		os.write("abc".getBytes());
		os.close();
		final BufferedInputStream bufferedInputStream = FileUtil.getBufferedInputStream(file);
		assertNotNull(bufferedInputStream);
		StreamUtil.close(bufferedInputStream);
		assertNull(FileUtil.getBufferedInputStream(null));
		assertNull(FileUtil.getBufferedInputStream(new File("")));
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testGetBufferedInputStreamDir() throws IOException
	{
		final File file = new File(sm_dirTestDataTemp + "testDir");
		FileUtil.deleteAll(file);
		file.mkdir();
		final BufferedInputStream bufferedInputStream = FileUtil.getBufferedInputStream(file);
		assertNull(bufferedInputStream);
	}

	/**
	 *
	 */
	@Test
	void testCleanDots()
	{
		assertEquals(FileUtil.cleanDots(new File(".")), new File("."));
		assertEquals(FileUtil.cleanDots(new File("..")), new File(".."));
		assertEquals(FileUtil.cleanDots(UrlUtil.urlToFile("a/..")), UrlUtil.urlToFile("."));
		assertEquals(FileUtil.cleanDots(UrlUtil.urlToFile(".././../c.pdf")), UrlUtil.urlToFile("../../c.pdf"));
		assertEquals(FileUtil.cleanDots(UrlUtil.urlToFile(".././a/../c.pdf")), UrlUtil.urlToFile("../c.pdf"));
		if (PlatformUtil.isWindows())
		{
			assertEquals(FileUtil.cleanDots(UrlUtil.urlToFile("a\\..")), UrlUtil.urlToFile("."));
			assertEquals(FileUtil.cleanDots(UrlUtil.urlToFile("..\\.\\..\\c.pdf")), UrlUtil.urlToFile("..\\..\\c.pdf"));
			assertEquals(FileUtil.cleanDots(UrlUtil.urlToFile("..\\.\\a\\..\\c.pdf")), UrlUtil.urlToFile("..\\c.pdf"));

		}
	}

	/**
	 *
	 */
	@Test
	void testNewExtension()
	{
		assertNull(FileUtil.getExtension(new File("foo")));
		assertEquals(new File("foo.bar"), FileUtil.newExtension(new File("foo"), "bar"));
		assertEquals(new File("foo.bar"), FileUtil.newExtension(new File("foo"), ".bar"));
		assertEquals(new File("a/foo.bar"), FileUtil.newExtension(new File("a/foo"), ".bar"));
	}

	/**
	 *
	 */
	@Test
	void testCopyBytes()
	{
		final byte[] b = new byte[66666];
		for (int i = 0; i < 66666; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final File f = new File(sm_dirTestDataTemp + "bufOut.dat");
		f.delete();
		FileUtil.copyBytes(b, f);
		assertEquals(f.length(), 66666);
		final byte b2[] = FileUtil.fileToByteArray(f);
		assertEquals(b.length, b2.length);
		for (int i = 0; i < 66666; i++)
		{
			assertEquals(b[i], b2[i]);
		}
		FileUtil.copyBytes(b, f);
		assertEquals(f.length(), 2 * 66666);
		final byte b3[] = FileUtil.fileToByteArray(f);
		for (int i = 0; i < 66666; i++)
		{
			assertEquals(b[i % 66666], b3[i]);
		}
	}

	/**
	 * @throws Exception x
	 */
	@Test
	void testgetFastMD5() throws Exception
	{
		final byte[] b1 = FileUtil.getFastMD5(new File(sm_dirTestData + "dir1.zip"), 1000000);
		final byte[] b2 = FileUtil.getFastMD5(new File(sm_dirTestData + "dir1.zip"), 1000000);
		for (int i = 0; i < b1.length; i++)
		{
			assertEquals(b1[i], b2[i]);
		}
		final byte[] b3 = FileUtil.getFastMD5(new File(sm_dirTestData + "FixVersion.jdf"), 1000000);
		assertNotNull(b3);
	}

	/**
	 * @throws Exception x
	 */
	@Test
	void testgetFastMD5NonExist() throws Exception
	{
		final byte[] b1 = FileUtil.getFastMD5(new File(sm_dirTestData + "nix"), 1000000);
		assertNull(b1);
	}

	/**
	 * @throws Exception x
	 */
	@Test
	void testgetFastMD5Null() throws Exception
	{
		final byte[] b1 = FileUtil.getFastMD5(null, 1000000);
		assertNull(b1);
	}

	/**
	 * @throws Exception x
	 */
	@Test
	void testFileToByteArray() throws Exception
	{
		final byte[] b = new byte[66666];
		for (int i = 0; i < 66666; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final ByteArrayInputStream is = new ByteArrayInputStream(b);
		is.close();
		final File f = new File(sm_dirTestDataTemp + "stream.dat");
		if (f.exists())
		{
			f.delete();
		}

		FileUtil.streamToFile(is, sm_dirTestDataTemp + "stream.dat");
		assertTrue(f.exists());
		final byte b2[] = FileUtil.fileToByteArray(f);
		final byte b3[] = FileUtil.fileToByteArray(f);
		assertEquals(b.length, b2.length);
		for (int i = 0; i < 66666; i++)
		{
			assertEquals(b[i], b2[i]);
			assertEquals(b[i], b3[i]);
		}
		f.delete();
		final File f2 = new File(sm_dirTestDataTemp + "dummy_snafu.dat");
		assertNull(FileUtil.fileToByteArray(f2));
	}

	/**
	 *
	 */
	@Test
	void testisCleanURLFile()
	{
		if (PlatformUtil.isWindows())
		{
			assertEquals(new File("C:"), FileUtil.cleanURL(new File("C:/")));
			assertEquals(new File("C:"), FileUtil.cleanURL(new File("C:\\")));
			assertEquals(new File("C:\\a"), FileUtil.cleanURL(new File("C:\\a")));
			assertEquals(new File("C:\\a"), FileUtil.cleanURL(new File("C:/a")));
		}
	}

	/**
	 *
	 */
	@Test
	void testListTree()
	{
		final File root = new File(sm_dirTestData + File.separator + "dir1");
		Vector<File> list = FileUtil.listFilesInTree(root, "dir(.+)/");
		assertEquals(list.size(), 2);
		assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2a"))));
		assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2b"))));
		list = FileUtil.listFilesInTree(root, StringUtil.simpleRegExptoRegExp("dir*/*.txt"));
		assertEquals(list.size(), 4);
		final Vector<File> list2 = FileUtil.listFilesInTree(root, StringUtil.simpleRegExptoRegExp("*.txt"));
		assertEquals(list2.size(), 6);
	}

	/**
	 *
	 */
	@Test
	void testListTreeFilter()
	{
		final File root = new File(sm_dirTestData + File.separator + "dir1");
		final Vector<File> list = FileUtil.listFilesInTree(root, new FileUtil.DirectoryFileFilter());
		for (final File f : list)
			assertTrue(f.isDirectory());
		assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2a"))));
		assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2b"))));
	}

	/**
	 *
	 */
	@Test
	void testListTreeFilterNull()
	{
		final File root = new File(sm_dirTestData + File.separator + "dir1");
		final Vector<File> list = FileUtil.listFilesInTree(root, (FileFilter) null);
		assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2a"))));
		assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2b"))));
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testListFilesWithExtension() throws Exception
	{
		final File f = new File(sm_dirTestDataTemp + "/fooExt");
		f.mkdir(); // make sure we have one
		assertTrue(FileUtil.deleteAll(f));
		assertTrue(f.mkdir());
		assertNull(FileUtil.listFilesWithExtension(null, null));

		for (char c = 'a'; c < 'g'; c++)
		{
			for (int i = 0; i < 3; i++)
			{
				final File f2 = new File(f.getAbsolutePath() + File.separator + i + "." + c);
				assertTrue(f2.createNewFile(), f2.getAbsolutePath());
				assertTrue(f2.exists(), f2.getAbsolutePath());
			}
		}
		assertEquals(FileUtil.listFilesWithExtension(f, "a").length, 3);
		assertEquals(FileUtil.listFilesWithExtension(f, null).length, 18);
		assertNull(FileUtil.listFilesWithExtension(f, "CC"));
		assertNull(FileUtil.listFilesWithExtension(f, ".CC,.dd"));
		new File(f.getAbsolutePath() + File.separator + "a").createNewFile();
		assertEquals(FileUtil.listFilesWithExtension(f, null).length, 19);
		assertEquals(FileUtil.listFilesWithExtension(f, null, 10).length, 10);

	}

	/**
	 * @throws Exception
	 */
	@Test
	void testNumFiles() throws Exception
	{
		final File f = new File(sm_dirTestDataTemp + "/foo2");
		FileUtil.deleteAll(f);
		f.mkdir(); // make sure we have one

		assertEquals(0, FileUtil.numFiles(null, 42));
		assertEquals(0, FileUtil.numFiles(f, 42));
		for (char c = 'a'; c < 'g'; c++)
		{
			for (int i = 0; i < 3; i++)
			{
				final File f2 = new File(f.getAbsolutePath() + File.separator + i + "." + c);
				System.out.println("Create new File: " + f2.getAbsolutePath());
				assertTrue(f2.createNewFile());
				System.out.println("Is Created: " + f2.exists());
				assertTrue(f2.exists());
			}
		}
		assertEquals(18, FileUtil.numFiles(f, 999));
		assertEquals(9, FileUtil.numFiles(f, 9));
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testListFilesWithExpression() throws Exception
	{
		final File f = new File(sm_dirTestDataTemp + "/foo");
		f.mkdir(); // make sure we have one
		assertTrue(FileUtil.deleteAll(f));
		assertTrue(f.mkdir());
		assertNull(FileUtil.listFilesWithExpression(null, null));
		final File f1 = FileUtil.getFileInDirectory(f, new File("a1.b.c"));
		FileUtil.createNewFile(f1);
		final File f2 = FileUtil.getFileInDirectory(f, new File("b.c"));
		FileUtil.createNewFile(f2);
		final File[] listFilesWithExpression = FileUtil.listFilesWithExpression(f, "*.b.c");
		assertEquals(listFilesWithExpression[0], f1);
		assertEquals(listFilesWithExpression.length, 1);
		FileUtil.createNewFile(FileUtil.getFileInDirectory(f, new File("a2.b.c")));
		assertEquals(2, FileUtil.listFilesWithExpression(f, "*.b.c").length);
		assertEquals(1, FileUtil.listFilesWithExpression(f, "*.b.c", 1).length);
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testListDirectories() throws Exception
	{
		final File f = new File(sm_dirTestDataTemp + File.separator + "foo");
		f.mkdir(); // make sure we have one
		assertTrue(FileUtil.deleteAll(f));
		assertTrue(f.mkdir());
		assertNull(FileUtil.listDirectories(null));
		assertNull(FileUtil.listDirectories(f));
		final File f1 = new File(sm_dirTestDataTemp + File.separator + "foo" + File.separator + "bar1");
		assertTrue(f1.mkdir());
		final File f2 = new File(sm_dirTestDataTemp + File.separator + "foo" + File.separator + "bar2");
		assertTrue(f2.createNewFile());
		assertEquals(FileUtil.listDirectories(f).length, 1);
		assertEquals(FileUtil.listDirectories(f)[0], f1, "skipping bar2 - not a directory");

	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	void testCreateFile()
	{
		final File f = new File(sm_dirTestDataTemp + "/aaa_aaa/b/c.txt");
		assertTrue(FileUtil.createNewFile(f));
		assertTrue(FileUtil.createNewFile(f));
		assertFalse(FileUtil.createNewFile(null));
		f.delete();
	}

	/**
	 *
	 */
	@Test
	void testEquals()
	{
		assertTrue(FileUtil.equals(null, null));
		assertFalse(FileUtil.equals(null, new File("a")));
		assertFalse(FileUtil.equals(new File("a"), null));
		assertTrue(FileUtil.equals(new File("a"), new File("a")));
		if (PlatformUtil.isWindows())
		{
			assertTrue(FileUtil.equals(new File("a"), new File("A")));
		}
		else
		{
			assertFalse(FileUtil.equals(new File("a"), new File("A")));
		}
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	void testForceDelete() throws Exception
	{
		final File f = new File(sm_dirTestDataTemp + "forcedelete.txt");
		assertTrue(FileUtil.forceDelete(null));
		assertTrue(FileUtil.forceDelete(f));
		f.createNewFile();
		assertTrue(FileUtil.forceDelete(f));
		f.createNewFile();
		final FileOutputStream fos = new FileOutputStream(f);
		fos.write(32);
		fos.flush();
		fos.close();
		assertTrue(FileUtil.forceDelete(f));
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testMoveFileNull() throws Exception
	{
		final byte[] b = new byte[5555];
		for (int i = 0; i < 5555; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final ByteArrayInputStream is = new ByteArrayInputStream(b);
		is.close();
		final File f = new File(sm_dirTestDataTemp + "streamMove.dat");
		if (f.exists())
		{
			f.delete();
		}
		FileUtil.streamToFile(is, f.getPath());

		assertFalse(FileUtil.moveFile(null, f));
		assertFalse(FileUtil.moveFile(f, null));
		assertFalse(FileUtil.moveFile(null, null));
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testMoveFileEquals() throws Exception
	{
		final byte[] b = new byte[5555];
		for (int i = 0; i < 5555; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final ByteArrayInputStream is = new ByteArrayInputStream(b);
		is.close();
		final File f = new File(sm_dirTestDataTemp + "streamMove.dat");
		if (f.exists())
		{
			f.delete();
		}
		FileUtil.streamToFile(is, f.getPath());
		final File f2 = new File(sm_dirTestDataTemp + "streamMove.dat");

		assertTrue(FileUtil.moveFile(f2, f));
		assertFalse(FileUtil.moveFile(new File("file://a"), new File("file://a")));
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testMoveFile() throws Exception
	{
		final byte[] b = new byte[55555];
		for (int i = 0; i < 55555; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final ByteArrayInputStream is = new ByteArrayInputStream(b);
		is.close();
		final File f = new File(sm_dirTestDataTemp + "streamMove.dat");
		if (f.exists())
		{
			f.delete();
		}
		FileUtil.streamToFile(is, f.getPath());
		final File f2 = new File(sm_dirTestDataTemp + "streamMove2.dat");

		assertTrue(FileUtil.moveFile(f, f2));
		assertFalse(f.exists());
		assertTrue(f2.length() > 50000);
		final String newdir = sm_dirTestDataTemp + File.separator + "newDir";
		final File fd = new File(newdir);
		FileUtil.deleteAll(fd);
		assertFalse(fd.exists());
		fd.mkdirs();
		final File f3 = new File(newdir + File.separator + "streamMove3.dat");
		assertTrue(FileUtil.moveFile(f2, f3));
		assertFalse(f2.exists());
		assertTrue(f3.length() > 50000);
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testMoveDir() throws Exception
	{
		final byte[] b = new byte[55555];
		for (int i = 0; i < 55555; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final ByteArrayInputStream is = new ByteArrayInputStream(b);
		is.close();
		final File dir = new File(sm_dirTestDataTemp + "testDir");
		final File dirdir = FileUtil.getFileInDirectory(dir, new File("subdir"));
		final File dir2 = new File(sm_dirTestDataTemp + "testDir2");
		FileUtil.deleteAll(dir);
		FileUtil.deleteAll(dir2);
		dir.mkdirs();
		final File sm = new File("streamMove.dat");
		final File f = FileUtil.getFileInDirectory(dir, sm);
		FileUtil.streamToFile(is, f.getPath());
		final File f2 = FileUtil.getFileInDirectory(dir, new File("streamMove2.dat"));
		FileUtil.streamToFile(is, f2.getPath());

		assertTrue(FileUtil.moveFile(dir, dir2));
		assertFalse(FileUtil.moveFile(dir, dir2));
		assertFalse(dir.exists());
		assertTrue(dir2.exists());
		assertTrue(FileUtil.moveFile(dir2, dirdir));
		assertFalse(dir2.exists());
		assertTrue(dirdir.exists());
		assertTrue(FileUtil.listFilesInTree(dir, "*.dat").toString().indexOf(sm.getName()) > 42);

	}

	/**
	 * @throws Exception
	 */
	@Test
	void testCopyFileLarge() throws Exception
	{
		final File fNew = new File(sm_dirTestDataTemp + "Big.txt");
		final byte[] b = new byte[100000];
		for (int i = 0; i < 100000; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final OutputStream os = FileUtil.getBufferedOutputStream(fNew);
		for (int i = 0; i < 5000; i++)
			os.write(b);

		os.flush();
		os.close();
		assertEquals(500000000l, fNew.length(), 10000);

		final File copy = new File(sm_dirTestDataTemp + "Big2.txt");
		assertTrue(FileUtil.copyFile(fNew, copy));
		assertEquals(500000000l, copy.length(), 10000);

		fNew.delete();
		copy.delete();

	}

	/**
	 * @throws Exception
	 */
	@Test
	void testCopyFileNull() throws Exception
	{
		final File fNew = new File(sm_dirTestDataTemp + "test.txt");
		final byte[] b = new byte[1000];
		for (int i = 0; i < 1000; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final OutputStream os = FileUtil.getBufferedOutputStream(fNew);
		os.write(b);

		os.flush();
		os.close();
		final File copy = new File(sm_dirTestDataTemp + "test2.txt");
		assertFalse(FileUtil.copyFile(fNew, null));

		fNew.delete();

	}

	/**
	 * @throws Exception
	 */
	@Test
	void testCopyFileEvil() throws Exception
	{
		final File fNew = new File(sm_dirTestDataTemp + "test.txt");
		final byte[] b = new byte[1000];
		for (int i = 0; i < 1000; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final OutputStream os = FileUtil.getBufferedOutputStream(fNew);
		os.write(b);

		os.flush();
		os.close();
		final File copy = new File(sm_dirTestDataTemp + "<?>/\\.txt");
		if (PlatformUtil.isWindows())
		{
			assertFalse(FileUtil.copyFile(fNew, copy));
		}
		fNew.delete();

	}

	/**
	 * @throws Exception
	 */
	@Test
	void testMoveFileLarge() throws Exception
	{
		final File fNew = new File(sm_dirTestDataTemp + "Big3.txt");
		final byte[] b = new byte[100000];
		for (int i = 0; i < 100000; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final OutputStream os = FileUtil.getBufferedOutputStream(fNew);
		for (int i = 0; i < 5000; i++)
			os.write(b);

		os.flush();
		os.close();
		assertEquals(500000000l, fNew.length(), 10000);
		final File fnewB = new File(sm_dirTestDataTemp + "Big3b.txt");
		FileUtil.copyFile(fNew, fnewB);

		final File copy = new File(sm_dirTestDataTemp + "Big4.txt");
		assertTrue(FileUtil.moveFile(fNew, copy));
		assertEquals(500000000l, copy.length(), 10000);

		assertTrue(FileUtil.moveFile(fnewB, copy));
		assertEquals(500000000l, copy.length(), 10000);

		fNew.delete();
		copy.delete();

	}

	/**
	 * @throws Exception
	 */
	@Test
	void testCopyFileToDir() throws Exception
	{
		final byte[] b = new byte[55555];
		for (int i = 0; i < 55555; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final ByteArrayInputStream is = new ByteArrayInputStream(b);
		is.close();
		final File f = new File(sm_dirTestDataTemp + "streamCopy.dat");
		if (f.exists())
		{
			f.delete();
		}
		FileUtil.streamToFile(is, f.getPath());
		final String newdir = sm_dirTestDataTemp + File.separator + "newDirCopy";
		final File fd = new File(newdir);
		FileUtil.deleteAll(fd);
		assertFalse(fd.exists());
		fd.mkdirs();
		final File nf = FileUtil.copyFileToDir(f, fd);
		assertNotNull(nf);
		assertEquals(nf.getParentFile(), fd);
		assertEquals(nf.getName(), f.getName());
		assertTrue(f.exists());
		assertTrue(nf.exists());
		assertNull(FileUtil.copyFileToDir(nf, fd), "do not copy self");
		assertEquals(nf, FileUtil.copyFileToDir(f, fd));
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testEnsureFileInDir() throws Exception
	{
		final byte[] b = new byte[55555];
		for (int i = 0; i < 55555; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final ByteArrayInputStream is = new ByteArrayInputStream(b);
		is.close();
		final File f = new File(sm_dirTestDataTemp + "streamCopy.dat");
		if (f.exists())
		{
			f.delete();
		}
		FileUtil.streamToFile(is, f.getPath());
		final String newdir = sm_dirTestDataTemp + File.separator + "newDirCopy";
		final File fd = new File(newdir);
		FileUtil.deleteAll(fd);
		assertFalse(fd.exists());
		fd.mkdirs();
		final File nf = FileUtil.ensureFileInDir(f, fd);
		assertNotNull(nf);
		final File parentDir = nf.getParentFile();
		assertEquals(parentDir, fd);
		assertEquals(nf.getName(), f.getName());
		assertTrue(f.exists());
		assertTrue(nf.exists());
		assertEquals(nf, FileUtil.ensureFileInDir(f, fd));
		assertEquals(nf, FileUtil.ensureFileInDir(nf, fd));
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testMoveFileToDir() throws Exception
	{
		final byte[] b = new byte[55555];
		for (int i = 0; i < 55555; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final ByteArrayInputStream is = new ByteArrayInputStream(b);
		is.close();
		final File f = new File(sm_dirTestDataTemp + "streamMove.dat");
		if (f.exists())
		{
			f.delete();
		}
		FileUtil.streamToFile(is, f.getPath());
		final String newdir = sm_dirTestDataTemp + File.separator + "newDir2";
		final File fd = new File(newdir);
		FileUtil.deleteAll(fd);
		assertFalse(fd.exists());
		fd.mkdirs();
		final File nf = FileUtil.moveFileToDir(f, fd);
		assertNotNull(nf);
		assertEquals(nf.getParentFile(), fd);
		assertEquals(nf.getName(), f.getName());
		assertFalse(f.exists());
		assertTrue(nf.exists());

		assertNull(FileUtil.moveFileToDir(null, fd));
		assertNull(FileUtil.moveFileToDir(fd, null));
		assertNull(FileUtil.moveFileToDir(null, null));
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testStreamToFile() throws Exception
	{
		final byte[] b = new byte[55555];
		for (int i = 0; i < 55555; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final ByteArrayIOStream is = new ByteArrayIOStream(b);
		is.close();
		final File f = new File(sm_dirTestDataTemp + "stream.dat");
		if (f.exists())
		{
			f.delete();
		}

		FileUtil.streamToFile(is.getInputStream(), sm_dirTestDataTemp + "stream.dat");
		assertTrue(f.exists());
		assertEquals(f.length(), 55555, 2000);

		final FileInputStream fis = new FileInputStream(f);
		for (int i = 0; i < 55555; i++)
		{
			b[i] = (byte) fis.read();
			if (i % 287 == 0)
			{
				assertEquals((256 + b[i]) % 256, i % 256);
			}
		}

		final int j = fis.read();
		assertEquals(j, -1, "eof reached");
		fis.close();
		FileUtil.streamToFile(is.getInputStream(), sm_dirTestDataTemp + "stream.dat");
		assertTrue(f.exists());
		assertEquals(f.length(), 55555, 2000, "deleted old stuff");

		final FileInputStream fis2 = new FileInputStream(f);
		final File f2 = FileUtil.streamToFile(fis2, sm_dirTestDataTemp + "stream2.dat");
		assertTrue(f2.canRead());
		assertTrue(f.delete());
		assertTrue(f2.delete());
		assertFalse(f.delete());
		assertNull(FileUtil.streamToFile(null, sm_dirTestDataTemp + "stream2.dat"), "null safe");
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testStringToFile() throws Exception
	{

		final File f = new File(sm_dirTestDataTemp + "string.dat");
		FileUtil.forceDelete(f);

		final String s0 = "abcdefg";
		assertNotNull(FileUtil.stringToFile(s0, f));
		assertTrue(f.exists());
		assertEquals(f.length(), 7, 100);

		final String s = FileUtil.fileToString(f, null);
		assertEquals(s0, s);
		final String s1 = FileUtil.fileToString(f, StandardCharsets.UTF_8);
		assertEquals(s0, s1);
		assertNull(FileUtil.stringToFile(null, new File(sm_dirTestDataTemp + "string2.dat")), "null safe");
		assertNull(FileUtil.fileToString(null, null), "null safe");
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testStreamToMD5File() throws Exception
	{
		final byte[] b = new byte[55555];
		for (int i = 0; i < 55555; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final ByteArrayIOStream is = new ByteArrayIOStream(b);
		is.close();
		final File f = new File(sm_dirTestDataTemp + "stream.dat");
		if (f.exists())
		{
			f.delete();
		}

		final MyPair<File, byte[]> pair = FileUtil.streamToMD5File(is.getInputStream(), f);
		assertTrue(f.exists());
		assertEquals(f, pair.a);
		assertEquals(f.length(), 55555, 2000);
		assertNotNull(pair.b);

		f.delete();
		final MyPair<File, byte[]> pair2 = FileUtil.streamToMD5File(is.getInputStream(), f);
		assertTrue(f.exists());
		assertEquals(f, pair2.a);
		assertEquals(f.length(), 55555, 2000);
		assertEquals(pair.a, pair2.a);
	}

	/**
	 *
	 */
	@Test
	void testGetFileInDirectory()
	{
		assertEquals(new File("a/b"), FileUtil.getFileInDirectory(new File("a"), new File("b")));
		assertEquals(new File("a/b"), FileUtil.getFileInDirectory(new File("a/"), new File("b")));
		assertEquals(new File("a/b"), FileUtil.getFileInDirectory(new File("a/"), new File("/b")));
		assertEquals(new File("a/c/b"), FileUtil.getFileInDirectory(new File("a"), new File("c/b")));
		assertEquals(new File("a/aa/c/b"), FileUtil.getFileInDirectory(new File("a/aa"), new File("c/b")));
		assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("c")), new File("a/b/c"));
		assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("c/d")), new File("a/b/c/d"));
		assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("/c")), new File("a/b/c"));
		assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("/c/d")), new File("a/b/c/d"));
		assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("../c/d")), new File("a/c/d"));
		assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("/c/d/")), new File("a/b/c/d"));
		assertEquals(FileUtil.getFileInDirectory(new File("a/b/"), new File("/c/d/")), new File("a/b/c/d"));

		if (PlatformUtil.isWindows())
		{
			assertEquals(new File("a/b"), FileUtil.getFileInDirectory(new File("a\\"), new File("b")));
			assertEquals(new File("a/b"), FileUtil.getFileInDirectory(new File("a\\"), new File("\\b")));
		}
	}

}
