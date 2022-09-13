/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2022 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class FileUtilTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	public void testisAbsoluteFile()
	{
		Assertions.assertFalse(FileUtil.isAbsoluteFile(new File("foo")));
		Assertions.assertFalse(FileUtil.isAbsoluteFile("foo"));

		if (PlatformUtil.isWindows())
		{
			Assertions.assertTrue(FileUtil.isAbsoluteFile(new File("c:\\")));
			Assertions.assertTrue(FileUtil.isAbsoluteFile("c:\\"));
			Assertions.assertTrue(FileUtil.isAbsoluteFile(new File("c:\\a")));
			Assertions.assertTrue(FileUtil.isAbsoluteFile("c:\\a"));
			Assertions.assertTrue(FileUtil.isAbsoluteFile(new File("c:/a")));
		}
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	public void testisLocked() throws IOException
	{
		final File file = new File(sm_dirTestDataTemp + "no such file");
		FileUtil.forceDelete(file);
		Assertions.assertFalse(FileUtil.isLocked(file));
		final File f = new File(sm_dirTestDataTemp + "test");
		FileUtil.forceDelete(f);
		final boolean b = f.createNewFile();
		ThreadUtil.sleep(12);
		Assertions.assertEquals(!b, FileUtil.isLocked(f));
	}

	/**
	 *
	 */
	@Test
	public void testisDirectory()
	{
		Assertions.assertFalse(FileUtil.isDirectory(new File("foo")));
		Assertions.assertFalse(FileUtil.isDirectory("foo"));
		Assertions.assertFalse(FileUtil.isDirectory((String) null));
		Assertions.assertFalse(FileUtil.isDirectory((File) null));
		Assertions.assertTrue(FileUtil.isDirectory("/"));
		Assertions.assertTrue(FileUtil.isDirectory(sm_dirTestData + "SampleFiles"));
		// assertTrue(FileUtil.isDirectory(sm_dirTestData + "Samples2"));

	}

	/**
	 *
	 */
	@Test
	public void testGetExtension()
	{
		Assertions.assertNull(FileUtil.getExtension(new File("foo")));
		Assertions.assertEquals("txt", FileUtil.getExtension(new File("foo.txt")));
	}

	/**
	 *
	 */
	@Test
	public void testGetAuxDir()
	{
		final File theHFDir = new File(sm_dirTestDataTemp + File.separator + "FooAux");
		FileUtil.deleteAll(theHFDir);
		theHFDir.mkdirs();
		final File aaa = FileUtil.getFileInDirectory(theHFDir, new File("aaa.txt"));
		FileUtil.createNewFile(aaa);

		Assertions.assertNull(FileUtil.getAuxDir(aaa));
		final File aaaDir = FileUtil.newExtension(aaa, null);
		aaaDir.mkdirs();
		Assertions.assertEquals(aaaDir, FileUtil.getAuxDir(aaa));
		final File aaaDir2 = new File(aaaDir.getAbsolutePath() + ".dir");
		aaaDir.renameTo(aaaDir2);
		Assertions.assertEquals(aaaDir2, FileUtil.getAuxDir(aaa));
		Assertions.assertNull(FileUtil.getAuxDir(new File(".xyz")));

	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	public void testGetBufferedOutputStream() throws IOException
	{
		final File file = new File(sm_dirTestDataTemp + "bufOut.txt");
		file.delete();
		final OutputStream os = FileUtil.getBufferedOutputStream(file);
		os.write("abc".getBytes());
		os.close();
		Assertions.assertTrue(file.exists());
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	public void testGetBufferedInputStream() throws IOException
	{
		final File file = new File(sm_dirTestDataTemp + "bufOut.txt");
		file.delete();
		final OutputStream os = FileUtil.getBufferedOutputStream(file);
		os.write("abc".getBytes());
		os.close();
		final BufferedInputStream bufferedInputStream = FileUtil.getBufferedInputStream(file);
		Assertions.assertNotNull(bufferedInputStream);
		StreamUtil.close(bufferedInputStream);
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	public void testGetBufferedInputStreamDir() throws IOException
	{
		final File file = new File(sm_dirTestDataTemp + "testDir");
		FileUtil.deleteAll(file);
		file.mkdir();
		final BufferedInputStream bufferedInputStream = FileUtil.getBufferedInputStream(file);
		Assertions.assertNull(bufferedInputStream);
	}

	/**
	 *
	 */
	@Test
	public void testCleanDots()
	{
		Assertions.assertEquals(FileUtil.cleanDots(new File(".")), new File("."));
		Assertions.assertEquals(FileUtil.cleanDots(new File("..")), new File(".."));
		Assertions.assertEquals(FileUtil.cleanDots(UrlUtil.urlToFile("a/..")), UrlUtil.urlToFile("."));
		Assertions.assertEquals(FileUtil.cleanDots(UrlUtil.urlToFile(".././../c.pdf")), UrlUtil.urlToFile("../../c.pdf"));
		Assertions.assertEquals(FileUtil.cleanDots(UrlUtil.urlToFile(".././a/../c.pdf")), UrlUtil.urlToFile("../c.pdf"));
		if (PlatformUtil.isWindows())
		{
			Assertions.assertEquals(FileUtil.cleanDots(UrlUtil.urlToFile("a\\..")), UrlUtil.urlToFile("."));
			Assertions.assertEquals(FileUtil.cleanDots(UrlUtil.urlToFile("..\\.\\..\\c.pdf")), UrlUtil.urlToFile("..\\..\\c.pdf"));
			Assertions.assertEquals(FileUtil.cleanDots(UrlUtil.urlToFile("..\\.\\a\\..\\c.pdf")), UrlUtil.urlToFile("..\\c.pdf"));

		}
	}

	/**
	 *
	 */
	@Test
	public void testNewExtension()
	{
		Assertions.assertNull(FileUtil.getExtension(new File("foo")));
		Assertions.assertEquals(new File("foo.bar"), FileUtil.newExtension(new File("foo"), "bar"));
		Assertions.assertEquals(new File("foo.bar"), FileUtil.newExtension(new File("foo"), ".bar"));
		Assertions.assertEquals(new File("a/foo.bar"), FileUtil.newExtension(new File("a/foo"), ".bar"));
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
		Assertions.assertEquals(f.length(), 66666);
		final byte b2[] = FileUtil.fileToByteArray(f);
		Assertions.assertEquals(b.length, b2.length);
		for (int i = 0; i < 66666; i++)
		{
			Assertions.assertEquals(b[i], b2[i]);
		}
		FileUtil.copyBytes(b, f);
		Assertions.assertEquals(f.length(), 2 * 66666);
		final byte b3[] = FileUtil.fileToByteArray(f);
		for (int i = 0; i < 66666; i++)
		{
			Assertions.assertEquals(b[i % 66666], b3[i]);
		}
	}

	/**
	 * @throws Exception x
	 */
	@Test
	public void testgetFastMD5() throws Exception
	{
		final byte[] b1 = FileUtil.getFastMD5(new File(sm_dirTestData + "dir1.zip"), 1000000);
		final byte[] b2 = FileUtil.getFastMD5(new File(sm_dirTestData + "dir1.zip"), 1000000);
		for (int i = 0; i < b1.length; i++)
		{
			Assertions.assertEquals(b1[i], b2[i]);
		}
		final byte[] b3 = FileUtil.getFastMD5(new File(sm_dirTestData + "FixVersion.jdf"), 1000000);
		Assertions.assertNotNull(b3);
	}

	/**
	 * @throws Exception x
	 */
	@Test
	public void testgetFastMD5NonExist() throws Exception
	{
		final byte[] b1 = FileUtil.getFastMD5(new File(sm_dirTestData + "nix"), 1000000);
		Assertions.assertNull(b1);
	}

	/**
	 * @throws Exception x
	 */
	@Test
	public void testgetFastMD5Null() throws Exception
	{
		final byte[] b1 = FileUtil.getFastMD5(null, 1000000);
		Assertions.assertNull(b1);
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
		Assertions.assertTrue(f.exists());
		final byte b2[] = FileUtil.fileToByteArray(f);
		final byte b3[] = FileUtil.fileToByteArray(f);
		Assertions.assertEquals(b.length, b2.length);
		for (int i = 0; i < 66666; i++)
		{
			Assertions.assertEquals(b[i], b2[i]);
			Assertions.assertEquals(b[i], b3[i]);
		}
		f.delete();
		final File f2 = new File(sm_dirTestDataTemp + "dummy_snafu.dat");
		Assertions.assertNull(FileUtil.fileToByteArray(f2));
	}

	/**
	 *
	 */
	@Test
	public void testisCleanURLFile()
	{
		if (PlatformUtil.isWindows())
		{
			Assertions.assertEquals(new File("C:"), FileUtil.cleanURL(new File("C:/")));
			Assertions.assertEquals(new File("C:"), FileUtil.cleanURL(new File("C:\\")));
			Assertions.assertEquals(new File("C:\\a"), FileUtil.cleanURL(new File("C:\\a")));
			Assertions.assertEquals(new File("C:\\a"), FileUtil.cleanURL(new File("C:/a")));
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
		Assertions.assertEquals(list.size(), 2);
		Assertions.assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2a"))));
		Assertions.assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2b"))));
		list = FileUtil.listFilesInTree(root, StringUtil.simpleRegExptoRegExp("dir*/*.txt"));
		Assertions.assertEquals(list.size(), 4);
		final Vector<File> list2 = FileUtil.listFilesInTree(root, StringUtil.simpleRegExptoRegExp("*.txt"));
		Assertions.assertEquals(list2.size(), 6);
	}

	/**
	 *
	 */
	@Test
	public void testListTreeFilter()
	{
		final File root = new File(sm_dirTestData + File.separator + "dir1");
		final Vector<File> list = FileUtil.listFilesInTree(root, new FileUtil.DirectoryFileFilter());
		for (final File f : list)
			Assertions.assertTrue(f.isDirectory());
		Assertions.assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2a"))));
		Assertions.assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2b"))));
	}

	/**
	 *
	 */
	@Test
	public void testListTreeFilterNull()
	{
		final File root = new File(sm_dirTestData + File.separator + "dir1");
		final Vector<File> list = FileUtil.listFilesInTree(root, (FileFilter) null);
		Assertions.assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2a"))));
		Assertions.assertTrue(list.contains(FileUtil.getFileInDirectory(root, new File("dir2b"))));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testListFilesWithExtension() throws Exception
	{
		final File f = new File(sm_dirTestDataTemp + "/foo");
		f.mkdir(); // make sure we have one
		Assertions.assertTrue(FileUtil.deleteAll(f));
		Assertions.assertTrue(f.mkdir());
		Assertions.assertNull(FileUtil.listFilesWithExtension(null, null));

		for (char c = 'a'; c < 'g'; c++)
		{
			for (int i = 0; i < 3; i++)
			{
				final File f2 = new File(f.getAbsolutePath() + File.separator + i + "." + c);
				System.out.println("Create new File: " + f2.getAbsolutePath());
				Assertions.assertTrue(f2.createNewFile());
				System.out.println("Is Created: " + f2.exists());
				Assertions.assertTrue(f2.exists());
			}
		}
		Assertions.assertEquals(FileUtil.listFilesWithExtension(f, "a").length, 3);
		// TODO @Raier (2013-03-10) - File order depends from target system
		// assertEquals(FileUtil.listFilesWithExtension(f, "a,b,.c")[0].getName(), "0.a");
		Assertions.assertEquals(FileUtil.listFilesWithExtension(f, null).length, 18);
		Assertions.assertNull(FileUtil.listFilesWithExtension(f, "CC"));
		Assertions.assertNull(FileUtil.listFilesWithExtension(f, ".CC,.dd"));
		new File(f.getAbsolutePath() + File.separator + "a").createNewFile();
		Assertions.assertEquals(FileUtil.listFilesWithExtension(f, null).length, 19);
		// TODO @Raier (2013-03-10) - Works not on Linux Systems
		// assertEquals(FileUtil.listFilesWithExtension(f, ".").length, 1);
		new File(f.getAbsolutePath() + File.separator + "b.").createNewFile();
		// TODO @Raier (2013-03-10) - Works not on Linux Systems
		// assertEquals(FileUtil.listFilesWithExtension(f, ".").length, 2);

		if (PlatformUtil.isWindows())
		{
			Assertions.assertEquals(FileUtil.listFilesWithExtension(f, "C")[0].getName(), "0.c");
			Assertions.assertEquals(FileUtil.listFilesWithExtension(f, "a,b,.c")[8].getName(), "2.c");
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
		Assertions.assertTrue(FileUtil.deleteAll(f));
		Assertions.assertTrue(f.mkdir());
		Assertions.assertNull(FileUtil.listFilesWithExpression(null, null));
		final File f1 = FileUtil.getFileInDirectory(f, new File("a1.b.c"));
		FileUtil.createNewFile(f1);
		final File f2 = FileUtil.getFileInDirectory(f, new File("b.c"));
		FileUtil.createNewFile(f2);
		final File[] listFilesWithExpression = FileUtil.listFilesWithExpression(f, "*.b.c");
		Assertions.assertEquals(listFilesWithExpression[0], f1);
		Assertions.assertEquals(listFilesWithExpression.length, 1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testListDirectories() throws Exception
	{
		final File f = new File(sm_dirTestDataTemp + File.separator + "foo");
		f.mkdir(); // make sure we have one
		Assertions.assertTrue(FileUtil.deleteAll(f));
		Assertions.assertTrue(f.mkdir());
		Assertions.assertNull(FileUtil.listDirectories(null));
		Assertions.assertNull(FileUtil.listDirectories(f));
		final File f1 = new File(sm_dirTestDataTemp + File.separator + "foo" + File.separator + "bar1");
		Assertions.assertTrue(f1.mkdir());
		final File f2 = new File(sm_dirTestDataTemp + File.separator + "foo" + File.separator + "bar2");
		Assertions.assertTrue(f2.createNewFile());
		Assertions.assertEquals(FileUtil.listDirectories(f).length, 1);
		Assertions.assertEquals(FileUtil.listDirectories(f)[0], f1, "skipping bar2 - not a directory");

	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 *
	 */
	@Test
	public void testCreateFile()
	{
		final File f = new File(sm_dirTestDataTemp + "/aaa_aaa/b/c.txt");
		Assertions.assertTrue(FileUtil.createNewFile(f));
		Assertions.assertTrue(FileUtil.createNewFile(f));
		Assertions.assertFalse(FileUtil.createNewFile(null));
		f.delete();
	}

	/**
	 *
	 */
	@Test
	public void testEquals()
	{
		Assertions.assertTrue(FileUtil.equals(null, null));
		Assertions.assertFalse(FileUtil.equals(null, new File("a")));
		Assertions.assertFalse(FileUtil.equals(new File("a"), null));
		Assertions.assertTrue(FileUtil.equals(new File("a"), new File("a")));
		if (PlatformUtil.isWindows())
		{
			Assertions.assertTrue(FileUtil.equals(new File("a"), new File("A")));
		}
		else
		{
			Assertions.assertFalse(FileUtil.equals(new File("a"), new File("A")));
		}
	}

	/**
	 * @throws Exception
	 *
	 */
	@Test
	public void testForceDelete() throws Exception
	{
		final File f = new File(sm_dirTestDataTemp + "forcedelete.txt");
		Assertions.assertTrue(FileUtil.forceDelete(null));
		Assertions.assertTrue(FileUtil.forceDelete(f));
		f.createNewFile();
		Assertions.assertTrue(FileUtil.forceDelete(f));
		f.createNewFile();
		final FileOutputStream fos = new FileOutputStream(f);
		fos.write(32);
		fos.flush();
		fos.close();
		Assertions.assertTrue(FileUtil.forceDelete(f));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testMoveFileNull() throws Exception
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

		Assertions.assertFalse(FileUtil.moveFile(null, f));
		Assertions.assertFalse(FileUtil.moveFile(f, null));
		Assertions.assertFalse(FileUtil.moveFile(null, null));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testMoveFileEquals() throws Exception
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

		Assertions.assertTrue(FileUtil.moveFile(f2, f));
		Assertions.assertFalse(FileUtil.moveFile(new File("file://a"), new File("file://a")));
	}

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

		Assertions.assertTrue(FileUtil.moveFile(f, f2));
		Assertions.assertFalse(f.exists());
		Assertions.assertTrue(f2.length() > 50000);
		final String newdir = sm_dirTestDataTemp + File.separator + "newDir";
		final File fd = new File(newdir);
		FileUtil.deleteAll(fd);
		Assertions.assertFalse(fd.exists());
		fd.mkdirs();
		final File f3 = new File(newdir + File.separator + "streamMove3.dat");
		Assertions.assertTrue(FileUtil.moveFile(f2, f3));
		Assertions.assertFalse(f2.exists());
		Assertions.assertTrue(f3.length() > 50000);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testMoveDir() throws Exception
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

		Assertions.assertTrue(FileUtil.moveFile(dir, dir2));
		Assertions.assertFalse(FileUtil.moveFile(dir, dir2));
		Assertions.assertFalse(dir.exists());
		Assertions.assertTrue(dir2.exists());
		Assertions.assertTrue(FileUtil.moveFile(dir2, dirdir));
		Assertions.assertFalse(dir2.exists());
		Assertions.assertTrue(dirdir.exists());
		Assertions.assertTrue(FileUtil.listFilesInTree(dir, "*.dat").toString().indexOf(sm.getName()) > 42);

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testCopyFileLarge() throws Exception
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
		Assertions.assertEquals(500000000l, fNew.length(), 10000);

		final File copy = new File(sm_dirTestDataTemp + "Big2.txt");
		Assertions.assertTrue(FileUtil.copyFile(fNew, copy));
		Assertions.assertEquals(500000000l, copy.length(), 10000);

		fNew.delete();
		copy.delete();

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testCopyFileNull() throws Exception
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
		Assertions.assertFalse(FileUtil.copyFile(fNew, null));

		fNew.delete();

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testCopyFileEvil() throws Exception
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
		Assertions.assertFalse(FileUtil.copyFile(fNew, copy));

		fNew.delete();

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testMoveFileLarge() throws Exception
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
		Assertions.assertEquals(500000000l, fNew.length(), 10000);
		final File fnewB = new File(sm_dirTestDataTemp + "Big3b.txt");
		FileUtil.copyFile(fNew, fnewB);

		final File copy = new File(sm_dirTestDataTemp + "Big4.txt");
		Assertions.assertTrue(FileUtil.moveFile(fNew, copy));
		Assertions.assertEquals(500000000l, copy.length(), 10000);

		Assertions.assertTrue(FileUtil.moveFile(fnewB, copy));
		Assertions.assertEquals(500000000l, copy.length(), 10000);

		fNew.delete();
		copy.delete();

	}

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
		Assertions.assertFalse(fd.exists());
		fd.mkdirs();
		final File nf = FileUtil.copyFileToDir(f, fd);
		Assertions.assertNotNull(nf);
		Assertions.assertEquals(nf.getParentFile(), fd);
		Assertions.assertEquals(nf.getName(), f.getName());
		Assertions.assertTrue(f.exists());
		Assertions.assertTrue(nf.exists());
		Assertions.assertNull(FileUtil.copyFileToDir(nf, fd), "do not copy self");
		Assertions.assertEquals(nf, FileUtil.copyFileToDir(f, fd));
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
		Assertions.assertFalse(fd.exists());
		fd.mkdirs();
		final File nf = FileUtil.ensureFileInDir(f, fd);
		Assertions.assertNotNull(nf);
		final File parentDir = nf.getParentFile();
		Assertions.assertEquals(parentDir, fd);
		Assertions.assertEquals(nf.getName(), f.getName());
		Assertions.assertTrue(f.exists());
		Assertions.assertTrue(nf.exists());
		Assertions.assertEquals(nf, FileUtil.ensureFileInDir(f, fd));
		Assertions.assertEquals(nf, FileUtil.ensureFileInDir(nf, fd));
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
		Assertions.assertFalse(fd.exists());
		fd.mkdirs();
		final File nf = FileUtil.moveFileToDir(f, fd);
		Assertions.assertNotNull(nf);
		Assertions.assertEquals(nf.getParentFile(), fd);
		Assertions.assertEquals(nf.getName(), f.getName());
		Assertions.assertFalse(f.exists());
		Assertions.assertTrue(nf.exists());

		Assertions.assertNull(FileUtil.moveFileToDir(null, fd));
		Assertions.assertNull(FileUtil.moveFileToDir(fd, null));
		Assertions.assertNull(FileUtil.moveFileToDir(null, null));
	}

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
		Assertions.assertTrue(f.exists());
		Assertions.assertEquals(f.length(), 55555, 2000);

		final FileInputStream fis = new FileInputStream(f);
		for (int i = 0; i < 55555; i++)
		{
			b[i] = (byte) fis.read();
			if (i % 287 == 0)
			{
				Assertions.assertEquals((256 + b[i]) % 256, i % 256);
			}
		}

		final int j = fis.read();
		Assertions.assertEquals(j, -1, "eof reached");
		fis.close();
		FileUtil.streamToFile(is.getInputStream(), sm_dirTestDataTemp + "stream.dat");
		Assertions.assertTrue(f.exists());
		Assertions.assertEquals(f.length(), 55555, 2000, "deleted old stuff");

		final FileInputStream fis2 = new FileInputStream(f);
		final File f2 = FileUtil.streamToFile(fis2, sm_dirTestDataTemp + "stream2.dat");
		Assertions.assertTrue(f2.canRead());
		Assertions.assertTrue(f.delete());
		Assertions.assertTrue(f2.delete());
		Assertions.assertFalse(f.delete());
		Assertions.assertNull(FileUtil.streamToFile(null, sm_dirTestDataTemp + "stream2.dat"), "null safe");
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testStringToFile() throws Exception
	{

		final File f = new File(sm_dirTestDataTemp + "string.dat");
		FileUtil.forceDelete(f);

		final String s0 = "abcdefg";
		FileUtil.stringToFile(s0, f);
		Assertions.assertTrue(f.exists());
		Assertions.assertEquals(f.length(), 7, 100);

		final String s = FileUtil.fileToString(f, null);
		Assertions.assertEquals(s0, s);
		final String s1 = FileUtil.fileToString(f, StandardCharsets.UTF_8);
		Assertions.assertEquals(s0, s1);
		Assertions.assertNull(FileUtil.stringToFile(null, new File(sm_dirTestDataTemp + "string2.dat")), "null safe");
		Assertions.assertNull(FileUtil.fileToString(null, null), "null safe");
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testStreamToMD5File() throws Exception
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
		Assertions.assertTrue(f.exists());
		Assertions.assertEquals(f, pair.a);
		Assertions.assertEquals(f.length(), 55555, 2000);
		Assertions.assertNotNull(pair.b);

		f.delete();
		final MyPair<File, byte[]> pair2 = FileUtil.streamToMD5File(is.getInputStream(), f);
		Assertions.assertTrue(f.exists());
		Assertions.assertEquals(f, pair2.a);
		Assertions.assertEquals(f.length(), 55555, 2000);
		Assertions.assertEquals(pair.a, pair2.a);
	}

	/**
	 *
	 */
	@Test
	public void testGetFileInDirectory()
	{
		Assertions.assertEquals(new File("a/b"), FileUtil.getFileInDirectory(new File("a"), new File("b")));
		Assertions.assertEquals(new File("a/b"), FileUtil.getFileInDirectory(new File("a/"), new File("b")));
		Assertions.assertEquals(new File("a/b"), FileUtil.getFileInDirectory(new File("a/"), new File("/b")));
		Assertions.assertEquals(new File("a/c/b"), FileUtil.getFileInDirectory(new File("a"), new File("c/b")));
		Assertions.assertEquals(new File("a/aa/c/b"), FileUtil.getFileInDirectory(new File("a/aa"), new File("c/b")));
		Assertions.assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("c")), new File("a/b/c"));
		Assertions.assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("c/d")), new File("a/b/c/d"));
		Assertions.assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("/c")), new File("a/b/c"));
		Assertions.assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("/c/d")), new File("a/b/c/d"));
		Assertions.assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("../c/d")), new File("a/c/d"));
		Assertions.assertEquals(FileUtil.getFileInDirectory(new File("a/b"), new File("/c/d/")), new File("a/b/c/d"));
		Assertions.assertEquals(FileUtil.getFileInDirectory(new File("a/b/"), new File("/c/d/")), new File("a/b/c/d"));

		if (PlatformUtil.isWindows())
		{
			Assertions.assertEquals(new File("a/b"), FileUtil.getFileInDirectory(new File("a\\"), new File("b")));
			Assertions.assertEquals(new File("a/b"), FileUtil.getFileInDirectory(new File("a\\"), new File("\\b")));
		}
	}

}
