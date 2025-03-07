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
package org.cip4.jdflib.util.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.ThreadUtil;
import org.cip4.jdflib.util.file.FileJanitor.AgeFilter;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 * @date Dec 8, 2011
 */
class FileJanitorTest extends JDFTestCaseBase
{
	/**
	 * @throws Exception
	 *
	 *
	 */
	@Test
	void testOld() throws Exception
	{
		final File f = new File(sm_dirTestDataTemp + "testJanitor");
		FileUtil.deleteAll(f);
		f.mkdir();
		FileUtil.getFileInDirectory(f, new File("dir1")).mkdir();
		FileUtil.getFileInDirectory(f, new File("dir2")).mkdir();
		FileUtil.getFileInDirectory(f, new File("dir2/foo1")).createNewFile();
		ThreadUtil.sleep(3000);
		FileUtil.getFileInDirectory(f, new File("dir1/foo1")).createNewFile();
		FileUtil.getFileInDirectory(f, new File("dir3")).mkdir();
		FileJanitor fileJanitor = new FileJanitor(f, 2);
		fileJanitor.setLogSingle(true);
		Vector<File> cleanupList = fileJanitor.cleanup();
		assertEquals(cleanupList.size(), 2, 1);
		ThreadUtil.sleep(2000);
		fileJanitor = new FileJanitor(f, 1);
		fileJanitor.setLogSingle(true);
		cleanupList = fileJanitor.cleanup();
		assertEquals(cleanupList.size(), 3, 1);
	}

	/**
	 * @throws Exception
	 *
	 *
	 */
	@Test
	void testOldKeep() throws Exception
	{
		final File f = new File(sm_dirTestDataTemp + "testJanitorKeep");
		FileUtil.deleteAll(f);
		f.mkdir();
		FileUtil.getFileInDirectory(f, new File("dir1")).mkdir();
		FileUtil.getFileInDirectory(f, new File("dir2")).mkdir();
		for (int i = 0; i < 42; i++)
		{
			final File f2 = new File("dir2/foo" + i);
			final File f1 = new File("dir1/bar" + i);
			FileUtil.getFileInDirectory(f, f2).createNewFile();
			FileUtil.getFileInDirectory(f, f1).createNewFile();
		}
		FileUtil.getFileInDirectory(f, new File("dir3")).mkdir();
		ThreadUtil.sleep(3000);
		FileJanitor fileJanitor = new FileJanitor(f, 2);
		fileJanitor.setLogSingle(true);
		fileJanitor.setMinKeep(10);
		Vector<File> cleanupList = fileJanitor.cleanup();
		assertEquals(cleanupList.size(), 76, 2);
		fileJanitor = new FileJanitor(f, 1);
		fileJanitor.setLogSingle(true);
		fileJanitor.setMinKeep(5);
		cleanupList = fileJanitor.cleanup();
		assertEquals(cleanupList.size(), 4, 2);
	}

	/**
	 * @throws Exception
	 *
	 *
	 */
	@Test
	void testOldKeepFlat() throws Exception
	{
		final File f = new File(sm_dirTestDataTemp + "testJanitorFlat");
		FileUtil.deleteAll(f);
		f.mkdir();
		for (int i = 0; i < 42; i++)
		{
			final File f2 = new File("foo" + i);
			final File f1 = new File("bar" + i);
			FileUtil.getFileInDirectory(f, f2).createNewFile();
			FileUtil.getFileInDirectory(f, f1).createNewFile();
		}
		ThreadUtil.sleep(3000);
		FileJanitor fileJanitor = new FileJanitor(f, 2);
		fileJanitor.setRecurseDirs(false);
		fileJanitor.setLogSingle(true);
		fileJanitor.setMinKeep(10);
		Vector<File> cleanupList = fileJanitor.cleanup();
		assertEquals(cleanupList.size(), 74, 2);
		fileJanitor = new FileJanitor(f, 1);
		fileJanitor.setLogSingle(true);
		fileJanitor.setMinKeep(5);
		fileJanitor.setRecurseDirs(false);
		cleanupList = fileJanitor.cleanup();
		assertEquals(cleanupList.size(), 4, 2);
	}

	/**
	 * @throws Exception
	 *
	 *
	 */
	@Test
	void testDelEmpty() throws Exception
	{
		final File f = new File(sm_dirTestDataTemp + "testJanitor");
		FileUtil.deleteAll(f);
		f.mkdir();
		FileUtil.getFileInDirectory(f, new File("dir1")).mkdir();
		FileUtil.getFileInDirectory(f, new File("dir2")).mkdir();
		FileUtil.getFileInDirectory(f, new File("dir2/foo1")).createNewFile();
		ThreadUtil.sleep(3000);
		FileUtil.getFileInDirectory(f, new File("dir1/foo1")).createNewFile();
		FileUtil.getFileInDirectory(f, new File("dir3")).mkdir();
		FileJanitor fileJanitor = new FileJanitor(f, 2);
		fileJanitor.setLogSingle(false);
		fileJanitor.setDeleteEmptyDir(true);
		Vector<File> cleanupList = fileJanitor.cleanup();
		assertEquals(cleanupList.size(), 2, 1);
		ThreadUtil.sleep(2000);
		fileJanitor = new FileJanitor(f, 1);
		fileJanitor.setLogSingle(true);
		cleanupList = fileJanitor.cleanup();
		assertEquals(cleanupList.size(), 2, 1);
	}

	/**
	 * @throws Exception
	 *
	 *
	 */
	@Test
	void testSetDelEmpty() throws Exception
	{

		final FileJanitor fileJanitor = new FileJanitor(null, 2);
		fileJanitor.setDeleteEmptyDir(true);

		assertTrue(fileJanitor.delEmpty);
		fileJanitor.setDeleteEmptyDir(false);
		assertFalse(fileJanitor.delEmpty);
	}

	/**
	 * @throws Exception
	 *
	 *
	 */
	@Test
	void testAgeTostring() throws Exception
	{

		final AgeFilter af = new AgeFilter(10000000l);

		assertNotNull(af.toString());
	}

	/**
	 * @throws Exception
	 *
	 *
	 */
	@Test
	void testTostring() throws Exception
	{

		final FileJanitor fileJanitor = new FileJanitor(null, 2);

		assertNotNull(fileJanitor.toString());
	}

	/**
	 * @throws Exception
	 *
	 *
	 */
	@Test
	void testSetLogSingl() throws Exception
	{

		final FileJanitor fileJanitor = new FileJanitor(null, 2);
		fileJanitor.setLogSingle(true);

		assertTrue(fileJanitor.logSingle);
		fileJanitor.setLogSingle(false);
		assertFalse(fileJanitor.logSingle);
	}
}
