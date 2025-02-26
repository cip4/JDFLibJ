/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

package org.cip4.jdflib.util;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         08.12.2008
 */
class RollingBackupTest extends JDFTestCaseBase
{
	@TempDir
	File dir;

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * @throws Exception x
	 */
	@Test
	void testConstruct() throws Exception
	{
		final File backRoot = FileUtil.getFileInDirectory(dir, new File("Roll.txt"));
		final RollingBackupFile rbf = new RollingBackupFile(backRoot, 4);
		Assertions.assertTrue(rbf.createNewFile());

		final File rbf2 = FileUtil.getFileInDirectory(dir, new File("Roll.txt"));
		Assertions.assertTrue(rbf2.exists());
	}

	/**
	 * @throws Exception x
	 */
	@Test
	void testGetNewFile() throws Exception
	{
		final File backRoot = FileUtil.getFileInDirectory(dir, new File("Roll.txt"));
		final RollingBackupFile rbf = new RollingBackupFile(backRoot, 4);

		for (int i = 0; i < 10; i++)
		{
			rbf.getNewFile();
			Assertions.assertTrue(rbf.createNewFile());
			Assertions.assertEquals(dir.listFiles().length, Math.min(i + 1, 5));
		}
		final File backRoot2 = FileUtil.getFileInDirectory(dir, new File("Roll2.xml"));
		final RollingBackupFile rbf2 = new RollingBackupFile(backRoot2.getPath(), 4);
		for (int i = 0; i < 10; i++)
		{
			rbf2.getNewFile();
			Assertions.assertTrue(rbf2.createNewFile());
			Assertions.assertEquals(dir.listFiles().length, Math.min(5 + i + 1, 5 + 5));
		}
	}

	/**
	 * @throws Exception x
	 */
	@Test
	void testGetNewFileWithExtension() throws Exception
	{
		final File backRoot = FileUtil.getFileInDirectory(dir, new File("Roll.txt"));
		final RollingBackupFile rbf = new RollingBackupFile(backRoot, 4);

		for (int i = 0; i < 10; i++)
		{
			final File ff = rbf.getNewFile("txt" + (i % 3));
			Assertions.assertTrue(ff.createNewFile(), "" + i);
			Assertions.assertEquals(dir.listFiles().length, Math.min(i + 1, 5));
		}
	}

	/**
	 * @throws Exception x
	 */
	@Test
	void testGetNewFileWithDotExtension() throws Exception
	{
		final File backRoot = FileUtil.getFileInDirectory(dir, new File("Roll.txt"));
		final RollingBackupFile rbf = new RollingBackupFile(backRoot, 42);
		for (int i = 0; i < 30; i++)
		{
			final File ff = rbf.getNewFile();
			ff.createNewFile();
			final File ff1 = rbf.getNewFile(".foo.txt");
			ff1.createNewFile();
			final File ff2 = rbf.getNewFile(".bar.txt");
			ff2.createNewFile();
			Assertions.assertEquals(dir.listFiles().length, Math.min(3 * i + 3, 43));

		}
	}

	/**
	 * @throws Exception x
	 */
	@Test
	void testGetNewFileWithNewDotExtension() throws Exception
	{
		final File backRoot = FileUtil.getFileInDirectory(dir, new File("Roller"));
		for (int i = 0; i < 30; i++)
		{
			final RollingBackupFile rbf = new RollingBackupFile(backRoot, 42);
			final File ff = rbf.getNewFile(".txt");
			ff.createNewFile();
			final RollingBackupFile rbf1 = new RollingBackupFile(backRoot, 42);
			final File ff1 = rbf1.getNewFile(".foo.txt");
			ff1.createNewFile();
			final RollingBackupFile rbf2 = new RollingBackupFile(backRoot, 42);
			final File ff2 = rbf2.getNewFile(".bar.txt");
			ff2.createNewFile();
			Assertions.assertEquals(dir.listFiles().length, Math.min(3 * i + 3, 43));
		}
	}

	/**
	 * @throws Exception x
	 */
	@Test
	void testGetNewFileWithPreDotExtension() throws Exception
	{
		final File backRoot = FileUtil.getFileInDirectory(dir, new File("Roll.a.b.txt"));
		final RollingBackupFile rbf = new RollingBackupFile(backRoot, 42);
		for (int i = 0; i < 30; i++)
		{
			final File ff = rbf.getNewFile();
			ff.createNewFile();

			final File ff1 = rbf.getNewFile(".foo.txt");
			ff1.createNewFile();
			final File ff2 = rbf.getNewFile(".bar.txt");
			ff2.createNewFile();
		}
	}

	/**
	 * @throws Exception x
	 */
	@Test
	void testGetOldFile() throws Exception
	{
		final File backRoot = FileUtil.getFileInDirectory(dir, new File("Roll.txt"));
		final RollingBackupFile rbf = new RollingBackupFile(backRoot, 4);

		for (int i = 0; i < 10; i++)
		{
			rbf.getNewFile();
			Assertions.assertTrue(rbf.createNewFile());
			Assertions.assertEquals(dir.listFiles().length, Math.min(i + 1, 5));
		}
		Assertions.assertEquals(rbf.getOldFile(0), rbf);
		for (int i = 1; i < 4; i++)
		{
			Assertions.assertNotNull(rbf.getOldFile(i));
			Assertions.assertNotSame(rbf.getOldFile(i), rbf);
		}
		for (int i = 5; i < 14; i++)
		{
			Assertions.assertNull(rbf.getOldFile(i));
		}
	}

	/**
	 * @throws Exception x
	 */
	@Test
	void testClearAll() throws Exception
	{
		final File backRoot = FileUtil.getFileInDirectory(dir, new File("Roll.txt"));
		final RollingBackupFile rbf = new RollingBackupFile(backRoot, 4);

		for (int i = 0; i < 10; i++)
		{
			rbf.getNewFile();
			Assertions.assertTrue(rbf.createNewFile());
			Assertions.assertEquals(dir.listFiles().length, Math.min(i + 1, 5), "File " + i);
		}
		rbf.clearAll();
		Assertions.assertEquals(dir.listFiles().length, 0);
	}
}
