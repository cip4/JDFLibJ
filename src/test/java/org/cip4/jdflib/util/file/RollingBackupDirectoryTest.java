/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2021 The International Cooperation for the Integration of
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
package org.cip4.jdflib.util.file;

import java.io.File;
import java.io.IOException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 * @date July 10, 2012
 */
class RollingBackupDirectoryTest extends JDFTestCaseBase
{
	File theDir;

	/**
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		theDir = new File(sm_dirTestDataTemp + "RollingBackupDir");
		FileUtil.deleteAll(theDir);
	}

	/**
	 * @throws IOException
	 *
	 *
	 */
	@Test
	void testCreateSimple() throws IOException
	{
		RollingBackupDirectory dir = new RollingBackupDirectory(theDir, 42, "test.txt");
		for (int i = 0; i < 155; i++)
		{
			File newFile = dir.getNewFile();
			Assertions.assertTrue(newFile.exists());
		}
	}

	/**
	 * @throws IOException
	 *
	 *
	 */
	@Test
	void testCreateSimpleNoExt() throws IOException
	{
		RollingBackupDirectory dir = new RollingBackupDirectory(theDir, 42, "test");
		for (int i = 0; i < 55; i++)
		{
			File newFile = dir.getNewFile();
			Assertions.assertTrue(newFile.exists());
		}
	}

	/**
	 * @throws IOException
	 *
	 *
	 */
	@Test
	void testCreateNewExt() throws IOException
	{
		RollingBackupDirectory dir = new RollingBackupDirectory(theDir, 42, "test");
		for (int i = 0; i < 155; i++)
		{
			String ext = ".txt";
			if (i % 3 == 1)
				ext = "xml";
			else if (i % 3 == 2)
				ext = ".foo";
			File newFile = dir.getNewFileWithExt(ext);
			Assertions.assertTrue(newFile.exists());
		}
	}

	/**
	 * @throws IOException
	 *
	 *
	 */
	@Test
	void testCreateNew() throws IOException
	{
		RollingBackupDirectory dir = new RollingBackupDirectory(theDir, 42, "test");
		for (int i = 0; i < 2; i++)
		{
			File newFile = dir.getNewFileWithExt("txt");
			Assertions.assertTrue(newFile.exists());
		}
		RollingBackupDirectory dir2 = new RollingBackupDirectory(theDir, 42, "test");
		File newFile = dir2.getNewFileWithExt("txt");
		Assertions.assertEquals(newFile.getName(), "test.3.txt");
	}

	/**
	 * @throws IOException
	 *
	 *
	 */
	@Test
	void testCreateNewDir() throws IOException
	{
		RollingBackupDirectory dir = new RollingBackupDirectory(theDir, 42, "testDir");
		dir.setDirectory(true);
		for (int i = 0; i < 200; i++)
		{
			File newFile = dir.getNewFile();
			Assertions.assertTrue(newFile.isDirectory());
		}
		dir.persist();
		Assertions.assertEquals(42, FileUtil.listDirectories(dir).length, 5);
	}

	/**
	 * @throws IOException
	 *
	 *
	 */
	@Test
	void testCreateNewNumeric() throws IOException
	{
		RollingBackupDirectory dir = new RollingBackupDirectory(theDir, 42, "42");
		for (int i = 0; i < 2; i++)
		{
			File newFile = dir.getNewFileWithExt("txt");
			Assertions.assertTrue(newFile.exists());
		}
		RollingBackupDirectory dir2 = new RollingBackupDirectory(theDir, 42, "42");
		File newFile = dir2.getNewFileWithExt("txt");
		Assertions.assertEquals(newFile.getName(), "42.3.txt");
	}

	/**
	 * @throws IOException
	 *
	 *
	 */
	@Test
	void testCreateNewExtWitxExt() throws IOException
	{
		RollingBackupDirectory dir = new RollingBackupDirectory(theDir, 42, "test.txt");
		for (int i = 0; i < 155; i++)
		{
			String ext = ".txt";
			if (i % 3 == 1)
				ext = "xml";
			else if (i % 3 == 2)
				ext = ".foo";
			File newFile = dir.getNewFileWithExt(ext);
			Assertions.assertTrue(newFile.exists());
		}
	}
}
