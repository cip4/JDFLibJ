/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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

package org.cip4.jdflib.util;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 08.12.2008
 */
public class RollingBackupTest extends JDFTestCaseBase
{
	private File dir;

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * @throws Exception x
	 */
	public void testConstruct() throws Exception
	{
		final File backRoot = FileUtil.getFileInDirectory(dir, new File("Roll.txt"));
		final RollingBackupFile rbf = new RollingBackupFile(backRoot, 4);
		assertTrue(rbf.createNewFile());

		final File rbf2 = FileUtil.getFileInDirectory(dir, new File("Roll.txt"));
		assertTrue(rbf2.exists());
	}

	/**
	 * @throws Exception x
	 */
	public void testGetNewFile() throws Exception
	{
		final File backRoot = FileUtil.getFileInDirectory(dir, new File("Roll.txt"));
		final RollingBackupFile rbf = new RollingBackupFile(backRoot, 4);

		for (int i = 0; i < 10; i++)
		{
			rbf.getNewFile();
			assertTrue(rbf.createNewFile());
			assertEquals(dir.listFiles().length, Math.min(i + 1, 5));
		}
		final File backRoot2 = FileUtil.getFileInDirectory(dir, new File("Roll.xml"));
		final RollingBackupFile rbf2 = new RollingBackupFile(backRoot2.getPath(), 4);
		for (int i = 0; i < 10; i++)
		{
			rbf2.getNewFile();
			assertTrue(rbf2.createNewFile());
			assertEquals(dir.listFiles().length, Math.min(5 + i + 1, 5 + 5));
		}
	}

	/**
	 * @throws Exception x
	 */
	public void testGetNewFileWithExtension() throws Exception
	{
		final File backRoot = FileUtil.getFileInDirectory(dir, new File("Roll.txt"));
		final RollingBackupFile rbf = new RollingBackupFile(backRoot, 4);

		for (int i = 0; i < 10; i++)
		{
			File ff = rbf.getNewFile("txt" + (i % 3));
			assertTrue(ff.createNewFile());
			assertEquals(dir.listFiles().length, Math.min(i + 1, 5));
		}
	}

	/**
	 * @throws Exception x
	 */
	public void testGetOldFile() throws Exception
	{
		final File backRoot = FileUtil.getFileInDirectory(dir, new File("Roll.txt"));
		final RollingBackupFile rbf = new RollingBackupFile(backRoot, 4);

		for (int i = 0; i < 10; i++)
		{
			rbf.getNewFile();
			assertTrue(rbf.createNewFile());
			assertEquals(dir.listFiles().length, Math.min(i + 1, 5));
		}
		assertEquals(rbf.getOldFile(0), rbf);
		for (int i = 1; i < 4; i++)
		{
			assertNotNull(rbf.getOldFile(i));
			assertNotSame(rbf.getOldFile(i), rbf);
		}
		for (int i = 5; i < 14; i++)
		{
			assertNull(rbf.getOldFile(i));
		}
	}

	/**
	 * @throws Exception x
	 */
	public void testClearAll() throws Exception
	{
		final File backRoot = FileUtil.getFileInDirectory(dir, new File("Roll.txt"));
		final RollingBackupFile rbf = new RollingBackupFile(backRoot, 4);

		for (int i = 0; i < 10; i++)
		{
			rbf.getNewFile();
			assertTrue(rbf.createNewFile());
			assertEquals("File " + i, dir.listFiles().length, Math.min(i + 1, 5));
		}
		rbf.clearAll();
		assertEquals(dir.listFiles().length, 0);
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		dir = FileUtil.getFileInDirectory(new File(sm_dirTestDataTemp), new File("Rolling"));
		FileUtil.deleteAll(dir);
		dir.mkdirs();
	}

}
