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
 * 04022005 VF initial version
 */
/*
 * Created on Aug 26, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.cip4.jdflib.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.Assert;
import org.junit.Test;
/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class FileUtilTest extends JDFTestCaseBase
{

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testisAbsoluteFile()
	{
		Assert.assertFalse(FileUtil.isAbsoluteFile(new File("foo")));
		Assert.assertFalse(FileUtil.isAbsoluteFile("foo"));

		if (PlatformUtil.isWindows())
		{
			Assert.assertTrue(FileUtil.isAbsoluteFile(new File("c:\\")));
			Assert.assertTrue(FileUtil.isAbsoluteFile("c:\\"));
			Assert.assertTrue(FileUtil.isAbsoluteFile(new File("c:\\a")));
			Assert.assertTrue(FileUtil.isAbsoluteFile("c:\\a"));
			Assert.assertTrue(FileUtil.isAbsoluteFile(new File("c:/a")));
		}
	}

	/**
	 * 
	 */
	@Test
	public void testisDirectory()
	{
		Assert.assertFalse(FileUtil.isDirectory(new File("foo")));
		Assert.assertFalse(FileUtil.isDirectory("foo"));
		Assert.assertFalse(FileUtil.isDirectory((String) null));
		Assert.assertFalse(FileUtil.isDirectory((File) null));
		Assert.assertTrue(FileUtil.isDirectory("/"));
		Assert.assertTrue(FileUtil.isDirectory(sm_dirTestData + "SampleFiles"));
		//		Assert.assertTrue(FileUtil.isDirectory(sm_dirTestData + "Samples2"));

	}

	/**
	 * 
	 */
	@Test
	public void testGetExtension()
	{
		Assert.assertNull(FileUtil.getExtension(new File("foo")));
		Assert.assertEquals("txt", FileUtil.getExtension(new File("foo.txt")));
	}

	/**
	 * 
	 */
	@Test
	public void testCleanDots()
	{
		Assert.assertEquals(FileUtil.cleanDots(new File(".")), new File("."));
		Assert.assertEquals(FileUtil.cleanDots(new File("..")), new File(".."));
		Assert.assertEquals(FileUtil.cleanDots(UrlUtil.urlToFile("a/..")), UrlUtil.urlToFile("."));
		Assert.assertEquals(FileUtil.cleanDots(UrlUtil.urlToFile(".././../c.pdf")), UrlUtil.urlToFile("../../c.pdf"));
		Assert.assertEquals(FileUtil.cleanDots(UrlUtil.urlToFile(".././a/../c.pdf")), UrlUtil.urlToFile("../c.pdf"));
	}

	/**
	 * 
	 */
	@Test
	public void testNewExtension()
	{
		Assert.assertNull(FileUtil.getExtension(new File("foo")));
		Assert.assertEquals(new File("foo.bar"), FileUtil.newExtension(new File("foo"), "bar"));
		Assert.assertEquals(new File("foo.bar"), FileUtil.newExtension(new File("foo"), ".bar"));
		Assert.assertEquals(new File("a/foo.bar"), FileUtil.newExtension(new File("a/foo"), ".bar"));
	}

	/**
	 * 
	 */
	@Test
	public void testCopyBytes()
	{
		final byte[] b = new byte[66666];
		for (int i = 0; i < 66666; i++)
		{
			b[i] = (byte) (i % 256);
		}
		final File f = new File(sm_dirTestDataTemp + "bufOut.dat");
		f.delete();
		FileUtil.copyBytes(b, f);
		Assert.assertEquals(f.length(), 66666);
		final byte b2[] = FileUtil.fileToByteArray(f);
		Assert.assertEquals(b.length, b2.length);
		for (int i = 0; i < 66666; i++)
		{
			Assert.assertEquals(b[i], b2[i]);
		}
		FileUtil.copyBytes(b, f);
		Assert.assertEquals(f.length(), 2 * 66666);
		final byte b3[] = FileUtil.fileToByteArray(f);
		for (int i = 0; i < 66666; i++)
		{
			Assert.assertEquals(b[i % 66666], b3[i]);
		}
	}

	/**
	 * @throws Exception x
	 */
	@Test
	public void testFileToByteArray() throws Exception
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
		Assert.assertTrue(f.exists());
		final byte b2[] = FileUtil.fileToByteArray(f);
		final byte b3[] = FileUtil.fileToByteArray(f);
		Assert.assertEquals(b.length, b2.length);
		for (int i = 0; i < 66666; i++)
		{
			Assert.assertEquals(b[i], b2[i]);
			Assert.assertEquals(b[i], b3[i]);
		}
		f.delete();
		final File f2 = new File(sm_dirTestDataTemp + "dummy_snafu.dat");
		Assert.assertNull(FileUtil.fileToByteArray(f2));
	}

	/**
	 * 
	 */
	@Test
	public void testisCleanURLFile()
	{
		if (PlatformUtil.isWindows())
		{
			Assert.assertEquals(new File("C:"), FileUtil.cleanURL(new File("C:/")));
			Assert.assertEquals(new File("C:"), FileUtil.cleanURL(new File("C:\\")));
			Assert.assertEquals(new File("C:\\a"), FileUtil.cleanURL(new File("C:\\a")));
			Assert.assertEquals(new File("C:\\a"), FileUtil.cleanURL(new File("C:/a")));
		}
	}

	/**
	 * 
	 */
	@Test
	public void testListTree()
	{
		final File root = new File(sm_dirTestData + File.separator + "dir1");
		Vector<File> list = FileUtil.listFilesInTree(root, "dir(.+)/");
		Assert.assertEquals(list.size(), 2);
		Assert.assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2a"))));
		Assert.assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2b"))));
		list = FileUtil.listFilesInTree(root, StringUtil.simpleRegExptoRegExp("dir*/*.txt"));
		Assert.assertEquals(list.size(), 4);
		Vector<File> list2 = FileUtil.listFilesInTree(root, StringUtil.simpleRegExptoRegExp("*.txt"));
		Assert.assertEquals(list2.size(), 6);
	}

	/**
	 * 
	 */
	@Test
	public void testListTreeFilter()
	{
		final File root = new File(sm_dirTestData + File.separator + "dir1");
		Vector<File> list = FileUtil.listFilesInTree(root, new FileUtil.DirectoryFileFilter());
		for (File f : list)
			Assert.assertTrue(f.isDirectory());
		Assert.assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2a"))));
		Assert.assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2b"))));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testListFilesWithExtension() throws Exception
	{
		final File f = new File(sm_dirTestDataTemp + "/foo");
		f.mkdir(); // make sure we have one
		Assert.assertTrue(FileUtil.deleteAll(f));
		Assert.assertTrue(f.mkdir());
		Assert.assertNull(FileUtil.listFilesWithExtension(null, null));

		for (char c = 'a'; c < 'g'; c++)
		{
			for (int i = 0; i < 3; i++)
			{
				final File f2 = new File(f.getAbsolutePath() + File.separator + i + "." + c);
				Assert.assertTrue(f2.createNewFile());
			}
		}
		Assert.assertEquals(FileUtil.listFilesWithExtension(f, "a").length, 3);
		Assert.assertEquals(FileUtil.listFilesWithExtension(f, "a,b,.c")[0].getName(), "0.a");
		Assert.assertEquals(FileUtil.listFilesWithExtension(f, null).length, 18);
		Assert.assertNull(FileUtil.listFilesWithExtension(f, "CC"));
		Assert.assertNull(FileUtil.listFilesWithExtension(f, ".CC,.dd"));
		new File(f.getAbsolutePath() + File.separator + "a").createNewFile();
		Assert.assertEquals(FileUtil.listFilesWithExtension(f, null).length, 19);
		Assert.assertEquals(FileUtil.listFilesWithExtension(f, ".").length, 1);
		new File(f.getAbsolutePath() + File.separator + "b.").createNewFile();
		Assert.assertEquals(FileUtil.listFilesWithExtension(f, ".").length, 2);

		if (PlatformUtil.isWindows())
		{
			Assert.assertEquals(FileUtil.listFilesWithExtension(f, "C")[0].getName(), "0.c");
			Assert.assertEquals(FileUtil.listFilesWithExtension(f, "a,b,.c")[8].getName(), "2.c");
		}

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testListFilesWithExpression() throws Exception
	{
		final File f = new File(sm_dirTestDataTemp + "/foo");
		f.mkdir(); // make sure we have one
		Assert.assertTrue(FileUtil.deleteAll(f));
		Assert.assertTrue(f.mkdir());
		Assert.assertNull(FileUtil.listFilesWithExtension(null, null));

		for (char c = 'a'; c < 'g'; c++)
		{
			for (int i = 0; i < 3; i++)
			{
				final File f2 = new File(f.getAbsolutePath() + File.separator + i + "." + c);
				Assert.assertTrue(f2.createNewFile());
			}
		}
		Assert.assertEquals(FileUtil.listFilesWithExtension(f, "a").length, 3);
		Assert.assertEquals(FileUtil.listFilesWithExtension(f, "a,b,.c")[0].getName(), "0.a");
		Assert.assertEquals(FileUtil.listFilesWithExtension(f, null).length, 18);
		Assert.assertNull(FileUtil.listFilesWithExtension(f, "CC"));
		Assert.assertNull(FileUtil.listFilesWithExtension(f, ".CC,.dd"));
		new File(f.getAbsolutePath() + File.separator + "a").createNewFile();
		Assert.assertEquals(FileUtil.listFilesWithExtension(f, null).length, 19);
		Assert.assertEquals(FileUtil.listFilesWithExtension(f, ".").length, 1);
		new File(f.getAbsolutePath() + File.separator + "b.").createNewFile();
		Assert.assertEquals(FileUtil.listFilesWithExtension(f, ".").length, 2);

		if (PlatformUtil.isWindows())
		{
			Assert.assertEquals(FileUtil.listFilesWithExtension(f, "C")[0].getName(), "0.c");
			Assert.assertEquals(FileUtil.listFilesWithExtension(f, "a,b,.c")[8].getName(), "2.c");
		}

	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * @throws Exception
	 */
	@Test
	public void testListDirectories() throws Exception
	{
		final File f = new File(sm_dirTestDataTemp + File.separator + "foo");
		f.mkdir(); // make sure we have one
		Assert.assertTrue(FileUtil.deleteAll(f));
		Assert.assertTrue(f.mkdir());
		Assert.assertNull(FileUtil.listDirectories(null));
		Assert.assertNull(FileUtil.listDirectories(f));
		final File f1 = new File(sm_dirTestDataTemp + File.separator + "foo" + File.separator + "bar1");
		Assert.assertTrue(f1.mkdir());
		final File f2 = new File(sm_dirTestDataTemp + File.separator + "foo" + File.separator + "bar2");
		Assert.assertTrue(f2.createNewFile());
		Assert.assertEquals(FileUtil.listDirectories(f).length, 1);
		Assert.assertEquals("skipping bar2 - not a directory", FileUtil.listDirectories(f)[0], f1);

	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testCreateFile()
	{
		final File f = new File(sm_dirTestDataTemp + "/aaa_aaa/b/c.txt");
		Assert.assertTrue(FileUtil.createNewFile(f));
		Assert.assertTrue(FileUtil.createNewFile(f));
		Assert.assertFalse(FileUtil.createNewFile(null));
		f.delete();
	}

	/**
	 * 
	 */
	@Test
	public void testEquals()
	{
		Assert.assertTrue(FileUtil.equals(null, null));
		Assert.assertFalse(FileUtil.equals(null, new File("a")));
		Assert.assertFalse(FileUtil.equals(new File("a"), null));
		Assert.assertTrue(FileUtil.equals(new File("a"), new File("a")));
		if (PlatformUtil.isWindows())
		{
			Assert.assertTrue(FileUtil.equals(new File("a"), new File("A")));
		}
		else
		{
			Assert.assertFalse(FileUtil.equals(new File("a"), new File("A")));
		}
	}

	/**
	 * 
	 */
	@Test
	public void testForceDelete() throws Exception
	{
		File f = new File(sm_dirTestDataTemp + "forcedelete.txt");
		Assert.assertTrue(FileUtil.forceDelete(null));
		Assert.assertTrue(FileUtil.forceDelete(f));
		f.createNewFile();
		Assert.assertTrue(FileUtil.forceDelete(f));
		f.createNewFile();
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(32);
		fos.flush();
		fos.close();
		Assert.assertTrue(FileUtil.forceDelete(f));
	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * @throws Exception
	 */
	@Test
	public void testMoveFile() throws Exception
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

		Assert.assertTrue(FileUtil.moveFile(f, f2));
		Assert.assertFalse(f.exists());
		Assert.assertTrue(f2.length() > 50000);
		final String newdir = sm_dirTestDataTemp + File.separator + "newDir";
		final File fd = new File(newdir);
		FileUtil.deleteAll(fd);
		Assert.assertFalse(fd.exists());
		fd.mkdirs();
		final File f3 = new File(newdir + File.separator + "streamMove3.dat");
		Assert.assertTrue(FileUtil.moveFile(f2, f3));
		Assert.assertFalse(f2.exists());
		Assert.assertTrue(f3.length() > 50000);

	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * @throws Exception
	 */
	@Test
	public void testCopyFileToDir() throws Exception
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
		Assert.assertFalse(fd.exists());
		fd.mkdirs();
		final File nf = FileUtil.copyFileToDir(f, fd);
		Assert.assertNotNull(nf);
		Assert.assertEquals(nf.getParentFile(), fd);
		Assert.assertEquals(nf.getName(), f.getName());
		Assert.assertTrue(f.exists());
		Assert.assertTrue(nf.exists());
		Assert.assertNull("do not copy self", FileUtil.copyFileToDir(nf, fd));
		Assert.assertEquals(nf, FileUtil.copyFileToDir(f, fd));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testEnsureFileInDir() throws Exception
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
		Assert.assertFalse(fd.exists());
		fd.mkdirs();
		final File nf = FileUtil.ensureFileInDir(f, fd);
		Assert.assertNotNull(nf);
		File parentDir = nf.getParentFile();
		Assert.assertEquals(parentDir, fd);
		Assert.assertEquals(nf.getName(), f.getName());
		Assert.assertTrue(f.exists());
		Assert.assertTrue(nf.exists());
		Assert.assertEquals(nf, FileUtil.ensureFileInDir(f, fd));
		Assert.assertEquals(nf, FileUtil.ensureFileInDir(nf, fd));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testMoveFileToDir() throws Exception
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
		Assert.assertFalse(fd.exists());
		fd.mkdirs();
		final File nf = FileUtil.moveFileToDir(f, fd);
		Assert.assertNotNull(nf);
		Assert.assertEquals(nf.getParentFile(), fd);
		Assert.assertEquals(nf.getName(), f.getName());
		Assert.assertFalse(f.exists());
		Assert.assertTrue(nf.exists());
	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * @throws Exception
	 */
	@Test
	public void testStreamToFile() throws Exception
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
		Assert.assertTrue(f.exists());
		Assert.assertEquals(f.length(), 55555, 2000);

		final FileInputStream fis = new FileInputStream(f);
		for (int i = 0; i < 55555; i++)
		{
			b[i] = (byte) fis.read();
			if (i % 287 == 0)
			{
				Assert.assertEquals((256 + b[i]) % 256, i % 256);
			}
		}

		final int j = fis.read();
		Assert.assertEquals("eof reached", j, -1);
		fis.close();
		FileUtil.streamToFile(is.getInputStream(), sm_dirTestDataTemp + "stream.dat");
		Assert.assertTrue(f.exists());
		Assert.assertEquals("deleted old stuff", f.length(), 55555, 2000);

		final FileInputStream fis2 = new FileInputStream(f);
		final File f2 = FileUtil.streamToFile(fis2, sm_dirTestDataTemp + "stream2.dat");
		Assert.assertTrue(f2.canRead());
		Assert.assertTrue(f.delete());
		Assert.assertTrue(f2.delete());
		Assert.assertFalse(f.delete());
		Assert.assertNull("null safe", FileUtil.streamToFile(null, sm_dirTestDataTemp + "stream2.dat"));
	}

	// ////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testGetFileInDirectory()
	{
		Assert.assertEquals(new File("a/b"), FileUtil.getFileInDirectory(new File("a"), new File("b")));
		Assert.assertEquals(new File("a/b"), FileUtil.getFileInDirectory(new File("a/"), new File("b")));
		Assert.assertEquals(new File("a/b"), FileUtil.getFileInDirectory(new File("a/"), new File("/b")));
		Assert.assertEquals(new File("a/c/b"), FileUtil.getFileInDirectory(new File("a"), new File("c/b")));
		Assert.assertEquals(new File("a/aa/c/b"), FileUtil.getFileInDirectory(new File("a/aa"), new File("c/b")));
		Assert.assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("c")), new File("a/b/c"));
		Assert.assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("c/d")), new File("a/b/c/d"));
		Assert.assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("/c")), new File("a/b/c"));
		Assert.assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("/c/d")), new File("a/b/c/d"));
		Assert.assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("../c/d")), new File("a/c/d"));
		Assert.assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("/c/d/")), new File("a/b/c/d"));
		Assert.assertEquals(FileUtil.getFileInDirectory(new File("a/b/"), new File("/c/d/")), new File("a/b/c/d"));

		if (PlatformUtil.isWindows())
		{
			Assert.assertEquals(new File("a/b"), FileUtil.getFileInDirectory(new File("a\\"), new File("b")));
			Assert.assertEquals(new File("a/b"), FileUtil.getFileInDirectory(new File("a\\"), new File("\\b")));
		}
	}

}
