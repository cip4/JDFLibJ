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
package org.cip4.jdflib.util.hotfolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.atomic.AtomicInteger;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.ThreadUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code and Comments
 */
class HotFolderTest extends JDFTestCaseBase
{
	private File theHF;
	HotFolder hf;

	protected static class MyListener implements HotFolderListener
	{
		protected boolean bZapp;
		int n;
		int sleep;

		/**
		 * @return the sleep
		 */
		int getSleep()
		{
			return sleep;
		}

		/**
		 * @param sleep the sleep to set
		 */
		void setSleep(final int sleep)
		{
			this.sleep = sleep;
		}

		protected MyListener(final boolean _bZapp)
		{
			bZapp = _bZapp;
			n = 0;
			sleep = 0;
		}

		public MyListener(final boolean b, final int slp)
		{
			this(b);
			sleep = slp;
		}

		/**
		 * @see HotFolderListener#hotFile(File)
		 */
		@Override
		public boolean hotFile(final File hotFile)
		{
			boolean zapp = false;
			if (bZapp)
				zapp = hotFile.delete();
			n++;
			ThreadUtil.sleep(sleep);
			return zapp;
		}

		/**
		 * @return the n
		 */
		int getN()
		{
			return n;
		}

	}

	static AtomicInteger n = new AtomicInteger(1000);

	@BeforeEach
	@Override
	public synchronized void setUp() throws Exception
	{
		sequential.lock();

		n.incrementAndGet();
		super.setUp();
		theHF = new File(sm_dirTestDataTemp + File.separator + "HFTest" + n.get());
		theHF.mkdirs();
		HotFolder.setDefaultStabilizeTime(420);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testStartNull() throws Exception
	{
		hf = new HotFolder(theHF, null, new MyListener(false));
		final File file = new File(theHF + File.separator + "f1.txt");
		file.createNewFile();
		assertTrue(file.exists());
		ThreadUtil.sleep(1);
		assertTrue(file.exists());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testShutdown() throws Exception
	{
		hf = new HotFolder(theHF, null, new MyListener(false));
		assertNotNull(HotFolderRunner.getTherunner());
		HotFolderRunner.shutDown();
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testRestartMany() throws Exception
	{
		hf = new HotFolder(theHF, null, new MyListener(true));
		final int n0 = Thread.activeCount();
		for (int i = 0; i < 10; i++)
		{
			Thread.sleep(1);
			assertTrue(Thread.activeCount() - n0 < 7, "Loop " + i);
			hf.restart();
		}
		for (int i = 0; i < 3; i++)
		{
			hf.stop();
			Thread.sleep(1);
			assertTrue(Thread.activeCount() - n0 < 6, "Loop " + i);
		}
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testMany() throws Exception
	{
		final int n0 = Thread.activeCount();
		final File manyDir = new File(sm_dirTestDataTemp, "manyhf");
		final HotFolder[] hotfolders = new HotFolder[1000];
		for (int i = 0; i < 1000; i++)
		{
			final File singleHF = new File(manyDir, "single" + i);
			hotfolders[i] = new HotFolder(singleHF, null, new MyListener(true));
		}
		assertTrue(Thread.activeCount() - n0 < 17, "Loop ");
		for (final HotFolder hotfolder : hotfolders)
		{
			for (int j = 0; j < 10; j++)
			{
				final File towrite = new File(hotfolder.getDir(), j + ".txt");
				assertTrue(towrite.createNewFile());
			}
		}
		for (int l = 0; l < 100; l++)
		{
			int n = 0;
			ThreadUtil.sleep(100);
			for (final HotFolder hotfolder : hotfolders)
			{
				for (int j = 0; j < 10; j++)
				{
					final File towrite = new File(hotfolder.getDir(), j + ".txt");
					if (towrite.exists())
					{
						n++;
					}
				}
			}
			if (n == 0)
			{
				return;
			}
		}
		fail("not gone");
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testManyLimited() throws Exception
	{
		final int n0 = Thread.activeCount();
		final File manyDir = new File(sm_dirTestDataTemp, "manyhfmax");
		FileUtil.forceDelete(manyDir);
		final HotFolder[] hotfolders = new HotFolder[10];
		for (int i = 0; i < 10; i++)
		{
			final File singleHF = new File(manyDir, "single" + i);
			hotfolders[i] = new HotFolder(singleHF, null, new MyListener(true));
			hotfolders[i].setMaxCheck(10);
		}
		assertTrue(Thread.activeCount() - n0 < 17, "Loop ");
		for (final HotFolder hotfolder : hotfolders)
		{
			for (int j = 0; j < 100; j++)
			{
				final File towrite = new File(hotfolder.getDir(), j + ".txt");
				assertTrue(towrite.createNewFile());
			}
		}
		for (int l = 0; l < 100; l++)
		{
			int n = 0;
			ThreadUtil.sleep(100);
			for (final HotFolder hotfolder : hotfolders)
			{
				for (int j = 0; j < 100; j++)
				{
					final File towrite = new File(hotfolder.getDir(), j + ".txt");
					if (towrite.exists())
					{
						n++;
					}
				}
			}
			if (n == 0)
			{
				return;
			}
		}
		fail("not gone");
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testRestartManyConcurrent() throws Exception
	{
		for (int j = 0; j < 10; j++)
		{
			Thread.sleep(10);
			hf = new HotFolder(theHF, null, new MyListener(true));
			hf.setMaxConcurrent(3);
			hf.restart();
			int n0 = Thread.activeCount();
			for (int i = 0; i < 10; i++)
			{
				Thread.sleep(20);
				assertTrue(n0 - Thread.activeCount() < 19, "Loop " + i);
				hf.restart();
			}
			if (n0 < Thread.activeCount())
				n0 = Thread.activeCount();
			for (int i = 0; i < 3; i++)
			{
				Thread.sleep(20);
				hf.stop();
				assertTrue(n0 - Thread.activeCount() < 19, "Loop " + i);
			}
		}
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testStopStart() throws Exception
	{
		hf = new HotFolder(theHF, null, new MyListener(true));
		final File file = new File(theHF + File.separator + "f1.txt");
		file.createNewFile();
		assertTrue(file.exists());

		for (int i = 0; i < 145 && file.exists(); i++)
		{
			ThreadUtil.sleep(100);
		}
		assertFalse(file.exists());
		hf.stop();
		hf.stop();
		file.createNewFile();
		assertTrue(file.exists());
		for (int i = 0; i < 150 && !file.exists(); i++)
		{
			ThreadUtil.sleep(100);
		}
		assertTrue(file.exists());
		hf.restart();
		hf.restart();
		hf.restart();
		for (int i = 0; i < 150 && file.exists(); i++)
		{
			ThreadUtil.sleep(100);
		}
		assertFalse(file.exists());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testStopStartMulti() throws Exception
	{
		hf = new HotFolder(theHF, null, new MyListener(true));
		hf.setMaxConcurrent(5);
		final File file = new File(theHF + File.separator + "f1.txt");
		file.createNewFile();
		assertTrue(file.exists());

		for (int i = 0; (i < 145) && file.exists(); i++)
		{
			ThreadUtil.sleep(100);
		}
		assertFalse(file.exists());
		hf.stop();
		hf.stop();
		file.createNewFile();
		assertTrue(file.exists());
		for (int i = 0; i < 1500 && !file.exists(); i++)
		{
			ThreadUtil.sleep(10);
		}
		assertTrue(file.exists());
		hf.restart();
		ThreadUtil.sleep(10);
		hf.restart();
		for (int i = 0; i < 1500 && file.exists(); i++)
		{
			ThreadUtil.sleep(10);
		}
		assertFalse(file.exists());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testExtension() throws Exception
	{
		for (int conc = 1; conc < 6; conc += 4)
		{
			hf = new HotFolder(theHF, ".txt,txt2", new MyListener(true));
			hf.setStabilizeTime(111);
			hf.setMaxConcurrent(conc);
			ThreadUtil.sleep(300); // time to start up
			final File file = new File(theHF + File.separator + "f1.txt");
			final File file1 = new File(theHF + File.separator + "f1.xml");
			final File file2 = new File(theHF + File.separator + "f1.foo");
			final File file3 = new File(theHF + File.separator + "f1.txt2");
			final File file4 = new File(theHF + File.separator + "f1");
			file.createNewFile();
			assertTrue(file.exists());
			file1.createNewFile();
			assertTrue(file1.exists());
			file2.createNewFile();
			assertTrue(file2.exists());
			file3.createNewFile();
			assertTrue(file3.exists());
			file4.createNewFile();
			assertTrue(file4.exists());

			for (int i = 0; i < 420; i++)
			{
				ThreadUtil.sleep(100);
				if (!file.exists() && !file3.exists())
					break;
			}
			assertFalse(file.exists());
			assertFalse(file3.exists());
			assertTrue(file1.exists());
			assertTrue(file4.exists());

			hf.addListener(new MyListener(true), ".xml");
			for (int i = 0; i < 420; i++)
			{
				ThreadUtil.sleep(20);
				if (!file1.exists() && file2.exists() && file4.exists())
					break;
			}
			assertFalse(file1.exists());
			assertTrue(file2.exists());
			assertTrue(file4.exists());

			hf.addListener(new MyListener(true), null);
			for (int i = 0; i < 420; i++)
			{
				ThreadUtil.sleep(20);
				if (!file2.exists() && !file4.exists())
					break;
			}
			assertFalse(file2.exists());
			assertFalse(file4.exists());
			hf.stop();
		}
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testStabilize() throws Exception
	{
		hf = new HotFolder(theHF, null, new MyListener(true));
		hf.setStabilizeTime(3333);
		ThreadUtil.sleep(300); // time to start up
		final File file = new File(theHF + File.separator + "f123.txt");
		file.createNewFile();
		assertTrue(file.exists());
		final long t0 = System.currentTimeMillis();
		for (int i = 0; i < 420; i++)
		{
			ThreadUtil.sleep(100);
			if (!file.exists())
				break;
		}
		assertFalse(file.exists());

		assertTrue(System.currentTimeMillis() - t0 > 3000);
		hf.stop();
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testDir() throws Exception
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
		assertTrue(file1.exists(), "in subdir");
		assertTrue(file2.exists());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testGetListener() throws Exception
	{
		final MyListener myl = new MyListener(true);
		hf = new HotFolder(theHF, ".txt", myl);
		hf.addListener(myl, "xml");
		assertEquals(hf.getListener(0).getListener(), myl);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testToString() throws Exception
	{
		final HotFolder hh = new HotFolder(new File(sm_dirTestDataTemp), null, null);
		assertNotNull(hh.toString());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testGetExtension() throws Exception
	{
		final MyListener myl = new MyListener(true);
		hf = new HotFolder(theHF, null, null);
		assertEquals(null, hf.getAllExtensions());
		hf.addListener(myl, "xml");
		assertEquals("xml", hf.getAllExtensions());
		hf.addListener(myl, "txt");
		assertEquals("xml,txt", hf.getAllExtensions());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testLog() throws Exception
	{
		hf = new HotFolder(theHF, ".txt", new MyListener(true));
		final File backup = new File(sm_dirTestDataTemp + "backup/hfbackup.keep");
		FileUtil.deleteAll(backup.getParentFile());

		hf.restart();
		for (int i = 0; i < 10; i++)
		{
			final File file = new File(theHF + File.separator + "f" + i + ".txt");
			file.createNewFile();
		}
		final File file1 = new File(theHF + File.separator + "f1.txt");

		for (int i = 0; i < 15 && file1.exists(); i++)
		{
			ThreadUtil.sleep(1000);
		}

		assertFalse(file1.exists());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testLogMulti() throws Exception
	{
		hf = new HotFolder(theHF, ".txt", new MyListener(true, 10));
		hf.setMaxConcurrent(2);
		final File backup = new File(sm_dirTestDataTemp + "backup/hfbackup.keep");
		FileUtil.deleteAll(backup.getParentFile());

		for (int i = 0; i <= 123; i++)
		{
			final File file = new File(theHF + File.separator + "f" + i + ".txt");
			file.createNewFile();
		}
		final File file1 = new File(theHF + File.separator + "f123.txt");

		for (int i = 0; i < 1500 && file1.exists(); i++)
		{
			ThreadUtil.sleep(10);
		}

		assertFalse(file1.exists());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testStartNullDelete() throws Exception
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
	@Test
	public synchronized void testBig() throws Exception
	{
		hf = new HotFolder(theHF, null, new MyListener(true));
		hf.setStabilizeTime(1444);
		final File file = new File(theHF + File.separator + "f1Bigabc.txt");
		file.createNewFile();
		assertTrue(file.exists());
		ThreadUtil.sleep(123);

		final FileOutputStream fos = new FileOutputStream(file, true);
		for (int i = 0; i < 20; i++)
		{
			ThreadUtil.sleep(1);
			for (int j = 0; j < 20; j++)
			{// incrementally fill file
				fos.write(i);
			}
			fos.flush();

		}
		fos.close();
		final long t0 = System.currentTimeMillis();
		assertTrue(file.exists());

		for (int i = 0; i < 600 && file.exists(); i++)
		{
			ThreadUtil.sleep(100);
		}
		assertEquals(2222, System.currentTimeMillis() - t0, 2222);
		assertFalse(file.exists());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public synchronized void testBigConcurrent() throws Exception
	{
		hf = new HotFolder(theHF, null, new MyListener(true));
		hf.setMaxConcurrent(8);
		final File file = new File(theHF + File.separator + "f1Big.txt");
		final File file2 = new File(theHF + File.separator + "f2Big.txt");
		file.createNewFile();
		assertTrue(file.exists());

		final FileOutputStream fos = new FileOutputStream(file);
		final FileOutputStream fos2 = new FileOutputStream(file2);
		for (int i = 0; i < 20; i++) // incrementally fill file
		{
			ThreadUtil.sleep(1);
			for (int j = 0; j < 200; j++)
			{
				// incrementally fill file
				fos.write(j);
				fos2.write(j);
			}
			fos.flush();
			fos2.flush();
		}
		assertTrue(file.exists());
		assertTrue(file2.exists());
		fos.close();
		fos2.close();

		for (int i = 1; i < 666 && file.exists(); i++)
		{
			ThreadUtil.sleep(100);
		}
		assertFalse(file.exists());
		for (int i = 0; i < 666 && file2.exists(); i++)
		{
			ThreadUtil.sleep(100);
		}
		assertFalse(file2.exists());
	}

	/***
	 * @see JDFTestCaseBase#tearDown()
	 */
	@Override
	@AfterEach
	public synchronized void tearDown() throws Exception
	{
		if (hf != null)
			hf.stop();
		super.tearDown();
		sequential.unlock();

	}
}
