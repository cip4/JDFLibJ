/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.util.hotfolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.ThreadUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * @author Rainer
 *
 *         To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code and Comments
 */
class ColdFolderTest extends JDFTestCaseBase
{

	static AtomicInteger n = new AtomicInteger(1000);

	@BeforeEach
	@Override
	public synchronized void setUp() throws Exception
	{
		n.incrementAndGet();
		super.setUp();
		HotFolder.setDefaultStabilizeTime(420);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testStartNull() throws Exception
	{
		final File cfd = new File(sm_dirTestDataTemp + "Cold");
		final HotFolderTest.MyListener listener = new HotFolderTest.MyListener(false);
		final ColdFolder cf = new ColdFolder(cfd, null, listener);
		final File file = new File(cfd, "f1.txt");
		file.createNewFile();
		assertTrue(file.exists());
		ThreadUtil.sleep(1420);
		assertTrue(file.exists());
		assertEquals(1, listener.getN());
		cf.stop();
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testDoMulti() throws Exception
	{
		final File cfd = new File(sm_dirTestDataTemp + "Cold3");
		FileUtil.deleteAll(cfd);
		final HotFolderListener listener = Mockito.mock(HotFolderListener.class);

		final ColdFolder cf = new ColdFolder(cfd, null, listener);
		for (int i = 0; i < 3; i++)
		{
			final File file = new File(cfd, "fmulti1.txt");
			assertTrue(file.createNewFile(), "File could not be created");
			assertTrue(file.exists());
			Mockito.verify(listener, Mockito.timeout(10_000)).hotFile(file);
			assertTrue(file.exists());
			FileUtil.forceDelete(file);
			ThreadUtil.sleep(42);
		}
		cf.stop();
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testGetFiles() throws Exception
	{
		final File cfd = new File(sm_dirTestDataTemp + "Cold2");
		final HotFolderTest.MyListener listener = new HotFolderTest.MyListener(false);
		final ColdFolder cf = new ColdFolder(cfd, null, listener);
		final File file = new File(cfd, "f1.txt");
		file.createNewFile();
		assertTrue(file.exists());
		assertEquals(1, cf.getHotFiles().length);
		cf.stop();
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testProcSingle() throws Exception
	{
		final File cfd = new File(sm_dirTestDataTemp + "Cold2");
		final HotFolderTest.MyListener listener = new HotFolderTest.MyListener(false);
		final ColdFolder cf = new ColdFolder(cfd, null, listener);
		final File file = new File(cfd, "f1.txt");
		file.createNewFile();
		assertTrue(file.exists());
		assertTrue(cf.processSingleFile(new FileTime(file, true)));
		cf.stop();
	}

}
