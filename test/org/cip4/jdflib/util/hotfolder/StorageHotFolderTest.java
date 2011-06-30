/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.util.hotfolder;

import java.io.File;
import java.io.IOException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.ThreadUtil;

/**
 * 
 * @author rainerprosi
 * @date Feb 14, 2011
 */
public class StorageHotFolderTest extends JDFTestCaseBase
{

	public class CountListener implements HotFolderListener
	{
		/**
		 * 
		 */
		public CountListener()
		{
			super();
			iCount = 0;
		}

		private int iCount;

		/**
		 * dummy that alternates ok and false 
		 * @see org.cip4.jdflib.util.hotfolder.HotFolderListener#hotFile(java.io.File)
		 */
		public boolean hotFile(File hotFile)
		{
			return iCount++ % 2 == 0;
		}

	}

	File theHFDir;
	File tmpHFDir;
	StorageHotFolder hf;

	/**
	 * 
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		theHFDir = new File(sm_dirTestDataTemp + File.separator + "HFTest");
		FileUtil.deleteAll(theHFDir);
		theHFDir.mkdirs();

		tmpHFDir = new File(sm_dirTestDataTemp + File.separator + "HFTemp");
		FileUtil.deleteAll(tmpHFDir);

		HotFolder.setDefaultStabilizeTime(200);
	}

	/** 
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception
	{
		hf.stop();
		super.tearDown();
	}

	/**
	 * 
	 * simple creation
	 * @throws IOException 
	 */
	public void testSimple() throws IOException
	{
		hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		final File file = new File(theHFDir + File.separator + "f1.txt");
		file.createNewFile();
		assertTrue(file.exists());
		ThreadUtil.sleep(2000);
		assertFalse(file.exists());
		assertEquals(tmpHFDir.listFiles().length, 0, 0);
	}

	/**
	 * 
	 * simple creation
	 * @throws IOException 
	 */
	public void testAddListener() throws IOException
	{
		hf = new StorageHotFolder(theHFDir, tmpHFDir, ".xml", new CountListener());
		final File file = new File(theHFDir + File.separator + "f1.txt");
		file.createNewFile();
		assertTrue(file.exists());
		ThreadUtil.sleep(2000);
		assertTrue(file.exists());
		hf.addListener(new CountListener(), ".txt");
		ThreadUtil.sleep(2000);
		assertFalse(file.exists());
	}

	/**
	 * 
	 * ok or error folder testing
	 * @throws Exception 
	 */
	public void testOKError() throws Exception
	{
		hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		File error = new File("error");
		hf.setErrorStorage(error);
		File ok = new File("ok");
		hf.setOKStorage(ok);
		hf.setMaxStore(42);
		ThreadUtil.sleep(1000);

		for (int i = 0; i < 4; i++)
		{
			final File file = new File(theHFDir + File.separator + "f" + i + ".txt");
			file.createNewFile();
		}
		ok = FileUtil.getFileInDirectory(theHFDir, ok);
		error = FileUtil.getFileInDirectory(theHFDir, error);
		ThreadUtil.sleep(2000);
		assertEquals(ok.listFiles().length, 2, 1);
		assertEquals(tmpHFDir.listFiles().length, 0, 1);
		assertEquals(error.listFiles().length, 2, 1);
		for (int i = 0; i < 4; i++)
		{
			final File file = new File(theHFDir + File.separator + "f" + i + ".txt");
			file.createNewFile();
		}
		ThreadUtil.sleep(1000);
		assertEquals(ok.listFiles().length, 4, 1);
		assertEquals(tmpHFDir.listFiles().length, 0, 1);
		for (int i = 0; i < 100; i++)
		{
			final File file = new File(theHFDir + File.separator + "f" + i + ".txt");
			file.createNewFile();
		}
		ThreadUtil.sleep(1000);
		assertEquals(ok.listFiles().length, 42, 13);
		assertEquals(tmpHFDir.listFiles().length, 0, 0);
		assertEquals(error.listFiles().length, 42, 13);

	}
}
