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
/*
 * Created on Aug 26, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.cip4.jdflib.util;

import java.io.File;
import java.io.FileOutputStream;

import org.cip4.jdflib.JDFTestCaseBase;

/**
 * @author Rainer
 * 
 *         To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code and Comments
 */
public class HotFolderTest extends JDFTestCaseBase
{
	private File theHF;
	HotFolder hf;

	protected class MyListener implements HotFolderListener
	{
		protected boolean bZapp;

		protected MyListener(boolean _bZapp)
		{
			bZapp = _bZapp;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.cip4.jdflib.util.HotFolderListener#hotFile(java.io.File)
		 */
		public void hotFile(File hotFile)
		{
			boolean zapp = false;
			if (bZapp)
				zapp = hotFile.delete();
			System.out.println(System.currentTimeMillis() + " " + hotFile.getPath() + "," + bZapp + "," + zapp);

		}

	}

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		theHF = new File(sm_dirTestDataTemp + File.separator + "HFTest");
		theHF.mkdirs();
		HotFolder.setDefaultStabilizeTime(500);
	}

	/**
	 * @throws Exception
	 */
	public void testStartNull() throws Exception
	{
		hf = new HotFolder(theHF, null, new MyListener(false));
		final File file = new File(theHF + File.separator + "f1.txt");
		file.createNewFile();
		assertTrue(file.exists());
		ThreadUtil.sleep(5000);
		assertTrue(file.exists());
	}

	/**
	 * @throws Exception
	 */
	public void testRestartMany() throws Exception
	{
		hf = new HotFolder(theHF, null, new MyListener(true));
		for (int i = 0; i < 10; i++)
		{
			assertEquals("Loop " + i, Thread.activeCount(), 3);
			hf.restart();
		}
		for (int i = 0; i < 3; i++)
		{
			Thread.sleep(1);
			hf.stop();
			assertEquals(Thread.activeCount(), 2);
		}
	}

	/**
	 * @throws Exception
	 */
	public void testStopStart() throws Exception
	{
		hf = new HotFolder(theHF, null, new MyListener(true));
		final File file = new File(theHF + File.separator + "f1.txt");
		file.createNewFile();
		assertTrue(file.exists());

		for (int i = 0; i < 45 && file.exists(); i++)
		{
			ThreadUtil.sleep(1000);
		}
		assertFalse(file.exists());
		hf.stop();
		hf.stop();
		file.createNewFile();
		assertTrue(file.exists());
		for (int i = 0; i < 15 && !file.exists(); i++)
		{
			ThreadUtil.sleep(1000);
		}
		assertTrue(file.exists());
		hf.restart();
		hf.restart();
		hf.restart();
		for (int i = 0; i < 15 && file.exists(); i++)
		{
			ThreadUtil.sleep(1000);
		}
		assertFalse(file.exists());
	}

	/**
	 * @throws Exception
	 */
	public void testExtension() throws Exception
	{
		hf = new HotFolder(theHF, ".txt,txt2", new MyListener(true));
		hf.setStabilizeTime(333);
		ThreadUtil.sleep(1000); // time to start up
		final File file = new File(theHF + File.separator + "f1.txt");
		final File file1 = new File(theHF + File.separator + "f1.xml");
		final File file2 = new File(theHF + File.separator + "f1.foo");
		final File file3 = new File(theHF + File.separator + "f1.txt2");
		file.createNewFile();
		assertTrue(file.exists());
		file1.createNewFile();
		assertTrue(file1.exists());
		file2.createNewFile();
		assertTrue(file2.exists());
		file3.createNewFile();
		assertTrue(file3.exists());

		ThreadUtil.sleep(2000);
		assertFalse(file.exists());
		assertFalse(file3.exists());
		assertTrue(file1.exists());

		hf.addListener(new MyListener(true), ".xml");
		ThreadUtil.sleep(3000);
		assertFalse(file1.exists());
		assertTrue(file2.exists());
	}

	/**
	 * @throws Exception
	 */
	public void testDir() throws Exception
	{
		hf = new HotFolder(theHF, ".txt", new MyListener(true));
		hf.addListener(new MyListener(true), "xml");
		final File file = new File(theHF + File.separator + "f1.txt");
		final File file1 = new File(theHF + File.separator + "f2.xml" + File.separator + "f1.xml");
		final File file2 = new File(theHF + File.separator + "f2.xml");
		file.createNewFile();
		file2.mkdir();
		file1.createNewFile();
		assertTrue(file.exists());

		for (int i = 0; i < 15 && file.exists(); i++)
		{
			ThreadUtil.sleep(1000);
		}

		assertFalse(file.exists());
		assertTrue("in subdir", file1.exists());
		assertTrue(file2.exists());
	}

	/**
	 * @throws Exception
	 */
	public void testStartNullDelete() throws Exception
	{
		hf = new HotFolder(theHF, null, new MyListener(true));
		final File file = new File(theHF + File.separator + "f1.txt");
		file.createNewFile();
		assertTrue(file.exists());

		for (int i = 0; i < 15 && file.exists(); i++)
		{
			ThreadUtil.sleep(1000);
		}

		assertFalse(file.exists());
	}

	/**
	 * @throws Exception
	 */
	public void testBig() throws Exception
	{
		hf = new HotFolder(theHF, null, new MyListener(true));
		final File file = new File(theHF + File.separator + "f1.txt");
		file.createNewFile();
		assertTrue(file.exists());

		FileOutputStream fos = new FileOutputStream(file);
		for (int i = 0; i < 20; i++) // incrementally fill file
		{
			fos.write(i);
			fos.flush();

			ThreadUtil.sleep(10);

		}
		assertTrue(file.exists());
		fos.close();

		for (int i = 0; i < 60 && file.exists(); i++)
		{
			ThreadUtil.sleep(1000);
		}

		assertFalse(file.exists());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception
	{
		hf.stop();
		super.tearDown();
	}

	// /////////////////////////////////////////////////////////////////////////

}
