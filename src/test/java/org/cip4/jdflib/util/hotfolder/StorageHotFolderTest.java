/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.util.hotfolder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.elementwalker.URLExtractor;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.ThreadUtil;
import org.cip4.jdflib.util.thread.OrderedTaskQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 * @date Feb 14, 2011
 */
public class StorageHotFolderTest extends JDFTestCaseBase
{

	/**
	 *
	 *
	 * @author rainer prosi
	 * @date Mar 11, 2013
	 */
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
		 *
		 * @see org.cip4.jdflib.util.hotfolder.HotFolderListener#hotFile(java.io.File)
		 */
		@Override
		public boolean hotFile(final File hotFile)
		{
			return iCount++ % 2 == 0;
		}

	}

	/**
	 *
	 *
	 * @author rainer prosi
	 * @date Mar 11, 2013
	 */
	public class ExtractListener implements HotFolderListener
	{
		/**
		 *
		 */
		public ExtractListener()
		{
			super();
		}

		/**
		 * dummy that alternates ok and false
		 *
		 * @see org.cip4.jdflib.util.hotfolder.HotFolderListener#hotFile(java.io.File)
		 */
		@Override
		public boolean hotFile(final File hotFile)
		{
			final File dumpDir = new File(sm_dirTestDataTemp + "URLOut" + n);
			dumpDir.delete();
			final URLExtractor ex = new URLExtractor(dumpDir, hotFile.getParent(), null);
			ex.setWantLog(true);
			ex.setDeleteFile(true);
			final JDFDoc d = JDFDoc.parseFile(hotFile);
			log.info("processing " + this);
			if (d != null)
			{
				ex.walkTree(d.getJDFRoot(), null);
			}
			return true;
		}
	}

	File theHFDir;
	File tmpHFDir;
	static AtomicInteger ai = new AtomicInteger(0);
	int n = 0;

	/**
	 *
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	@Before
	public synchronized void setUp() throws Exception
	{
		OrderedTaskQueue.shutDownAll();
		super.setUp();
		n = ai.incrementAndGet();
		theHFDir = new File(sm_dirTestDataTemp + File.separator + "StHFTest" + n);
		FileUtil.deleteAll(theHFDir);
		theHFDir.mkdirs();

		tmpHFDir = new File(sm_dirTestDataTemp + File.separator + "StHFTemp" + n);
		FileUtil.deleteAll(tmpHFDir);

		log.info("Setting up: " + theHFDir);
		HotFolder.setDefaultStabilizeTime(100);
	}

	/**
	 *
	 * simple creation
	 *
	 * @throws IOException
	 */
	@Test
	public synchronized void testSimple() throws IOException
	{
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		final File file = new File(theHFDir + File.separator + "f1.txt");
		file.createNewFile();
		assertTrue(file.exists());
		ThreadUtil.sleep(2000);
		assertFalse(file.exists());
		assertEquals(tmpHFDir.listFiles().length, 0, 0);
		hf.stop();
	}

	/**
	 *
	 * simple creation
	 *
	 * @throws IOException
	 */
	@Test
	public synchronized void testSubdir() throws IOException
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFRunList rl = (JDFRunList) d.getJDFRoot().addResource(ElementName.RUNLIST, EnumUsage.Input);
		rl.addPDF("./dummy/boo.pdf", 0, -1);
		final String hfPath = theHFDir.getAbsolutePath();
		final File content = new File(hfPath + "/dummy/boo.pdf");
		FileUtil.createNewFile(content);
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new ExtractListener());
		ThreadUtil.sleep(333);
		d.write2File(hfPath + "/dummy.jdf", 2, false);
		final File file = new File(hfPath + "/dummy.jdf");
		assertTrue(file.exists());
		for (int i = 0; i < 800; i++)
		{
			ThreadUtil.sleep(100);
			if (!file.exists() && !content.exists())
				break;
		}
		ThreadUtil.sleep(420);
		assertFalse(file.getAbsolutePath(), file.exists());
		assertFalse(content.getAbsolutePath(), content.exists());
		assertEquals(tmpHFDir.listFiles().length, 0, 0);
		hf.stop();
	}

	/**
	 *
	 * simple creation
	 *
	 * @throws IOException
	 */
	@Test
	public synchronized void testSubdirSpace() throws IOException
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFRunList rl = (JDFRunList) d.getJDFRoot().addResource(ElementName.RUNLIST, EnumUsage.Input);
		rl.addPDF("./dummy%20space/boo.pdf", 0, -1);
		final String hfPath = theHFDir.getAbsolutePath();
		final File content = new File(hfPath + "/dummy space/boo.pdf");
		FileUtil.createNewFile(content);
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new ExtractListener());
		hf.setOKStorage(new File("OK"));
		ThreadUtil.sleep(333);
		d.write2File(hfPath + "/dummy space.jdf", 2, false);
		final File file = new File(hfPath + "/dummy space.jdf");
		assertTrue(file.exists());
		for (int i = 0; i < 10; i++)
		{
			ThreadUtil.sleep(1000);
			if (!file.exists() && !content.exists())
				break;
		}
		assertFalse(file.getAbsolutePath(), file.exists());
		assertFalse(content.getAbsolutePath(), content.exists());
		final File file2 = new File(hfPath + "/OK/dummy space");
		assertTrue(file2.isDirectory());
		assertEquals(tmpHFDir.listFiles().length, 0, 0);
		hf.stop();
	}

	/**
	 *
	 * check problems with special characters
	 *
	 * @throws IOException
	 */
	@Test
	public synchronized void testNonAscii() throws IOException
	{
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		final File file = new File(theHFDir + File.separator + "42 äöü €.txt");
		file.createNewFile();
		assertTrue(file.exists());
		ThreadUtil.sleep(2000);
		assertFalse(file.exists());
		assertEquals(tmpHFDir.listFiles().length, 0, 0);
		hf.stop();
	}

	/**
	 *
	 * simple creation
	 *
	 * @throws IOException
	 */
	@Test
	public synchronized void testAddListener() throws IOException
	{
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, ".xml", new CountListener());
		final File file = new File(theHFDir + File.separator + "f1.txt");
		file.createNewFile();
		assertTrue(file.exists());
		ThreadUtil.sleep(1000);
		assertTrue(file.exists());
		hf.addListener(new CountListener(), ".txt");
		for (int i = 0; i < 100; i++)
		{
			ThreadUtil.sleep(200);
			if (!file.exists())
				break;
		}
		assertFalse(file.exists());
		hf.stop();
	}

	/**
	 *
	 * ok or error folder testing
	 *
	 * @throws Exception
	 */
	@Test
	public synchronized void testOKError() throws Exception
	{
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		hf.setStabilizeTime(100);
		File error = new File("error");
		hf.setErrorStorage(error);
		File ok = new File("ok");
		hf.setOKStorage(ok);
		hf.setMaxStore(42);
		hf.restart();
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
		for (int i = 0; i < 1000; i++)
		{
			ThreadUtil.sleep(200);
			if (theHFDir.listFiles().length <= 2)
			{
				log.info("stop " + i);
				break;
			}
			log.warn("run over " + theHFDir.listFiles().length);
		}
		assertEquals(ok.listFiles().length, 42, 13);
		assertEquals(tmpHFDir.listFiles().length, 0, 1);
		assertEquals(error.listFiles().length, 42, 13);

		hf.stop();
	}

	@Test
	public synchronized void testSetRetry() throws Exception
	{
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		hf.setRetry(-99);
		assertEquals(1, hf.retry);
		hf.setRetry(99);
		assertEquals(99, hf.retry);
	}

	/**
	 *
	 * ok or error folder testing also check whether we run into a dead loop with retry>1
	 *
	 * @throws Exception
	 */
	@Test
	public synchronized void testOKErrorRetry() throws Exception
	{
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		hf.setRetry(99);
		hf.setStabilizeTime(100);
		File error = new File("error");
		hf.setErrorStorage(error);
		File ok = new File("ok");
		hf.setOKStorage(ok);
		hf.setMaxStore(42);
		hf.restart();
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
		for (int i = 0; i < 1000; i++)
		{
			ThreadUtil.sleep(200);
			if (theHFDir.listFiles().length <= 2)
			{
				log.info("stop " + i);
				break;
			}
			log.warn("run over " + theHFDir.listFiles().length);
		}
		assertEquals(ok.listFiles().length, 42, 13);
		assertEquals(tmpHFDir.listFiles().length, 0, 2);
		assertEquals(error.listFiles().length, 42, 13);

		hf.stop();
	}

	/**
	 *
	 * ok or error folder testing
	 *
	 * @throws Exception
	 */
	@Test
	public synchronized void testOKErrorMulti() throws Exception
	{

		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		hf.setMaxConcurrent(5);
		hf.setStabilizeTime(100);
		File error = new File("error");
		hf.setErrorStorage(error);
		File ok = new File("ok");
		hf.setOKStorage(ok);
		hf.setMaxStore(42);
		hf.restart();
		ThreadUtil.sleep(1000);

		for (int i = 0; i < 4; i++)
		{
			final File file = new File(theHFDir + File.separator + "f" + i + ".txt");
			file.createNewFile();
		}
		ok = FileUtil.getFileInDirectory(theHFDir, ok);
		error = FileUtil.getFileInDirectory(theHFDir, error);
		for (int i = 0; i < 100; i++)
		{
			ThreadUtil.sleep(200);
			if (ok.listFiles().length == 2 && error.listFiles().length == 2)
				break;
		}
		assertEquals(ok.listFiles().length, 2, 1);
		assertEquals(tmpHFDir.listFiles().length, 0, 1);
		assertEquals(error.listFiles().length, 2, 1);
		for (int i = 0; i < 4; i++)
		{
			final File file = new File(theHFDir + File.separator + "f" + i + ".txt");
			file.createNewFile();
		}
		ThreadUtil.sleep(2345);
		assertEquals(ok.listFiles().length, 4, 1);
		assertEquals(tmpHFDir.listFiles().length, 0, 1);
		for (int i = 0; i < 100; i++)
		{
			final File file = new File(theHFDir + File.separator + "f" + i + ".txt");
			file.createNewFile();
		}
		for (int i = 0; i < 1000; i++)
		{
			ThreadUtil.sleep(200);
			if (theHFDir.listFiles().length <= 2)
			{
				log.info("stop " + i);
				break;
			}
			log.warn("run over " + theHFDir.listFiles().length);
		}
		assertEquals(ok.listFiles().length, 42, 13);
		assertEquals(tmpHFDir.listFiles().length, 0, 5);
		assertEquals(error.listFiles().length, 42, 13);

		hf.stop();
	}

	/**
	 *
	 * ok or error folder testing
	 *
	 * @throws Exception
	 */
	@Test
	public synchronized void testOKErrorMultiAuxSame() throws Exception
	{
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		hf.setMaxConcurrent(5);
		hf.setStabilizeTime(100);
		File error = new File("error");
		hf.setErrorStorage(error);
		File ok = new File("ok");
		hf.setOKStorage(ok);
		hf.setMaxStore(42);
		hf.restart();
		ThreadUtil.sleep(1000);

		for (int i = 0; i < 20; i++)
		{
			createPair(i % 4);
		}

		for (int i = 0; i < 1000; i++)
		{
			ThreadUtil.sleep(200);
			if (theHFDir.listFiles().length == 2)
			{
				log.info("stop " + i);
				break;
			}
			log.warn("run over " + theHFDir.listFiles().length);
		}
		ok = FileUtil.getFileInDirectory(theHFDir, ok);
		error = FileUtil.getFileInDirectory(theHFDir, error);

		assertEquals(ok.listFiles().length, 20, 4);
		assertEquals(tmpHFDir.listFiles().length, 0, 4);
		assertEquals(error.listFiles().length, 20, 4);

		hf.stop();
	}

	/**
	 *
	 * ok or error folder testing
	 *
	 * @throws Exception
	 */
	@Test
	public synchronized void testAux() throws Exception
	{
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		hf.setMaxConcurrent(5);
		hf.setStabilizeTime(100);
		File error = new File("error");
		hf.setErrorStorage(error);
		File ok = new File("ok");
		hf.setOKStorage(ok);
		hf.setMaxStore(42);
		hf.restart();
		ThreadUtil.sleep(1000);

		createPair(0);

		ok = FileUtil.getFileInDirectory(theHFDir, ok);
		error = FileUtil.getFileInDirectory(theHFDir, error);
		for (int i = 0; i < 100; i++)
		{
			ThreadUtil.sleep(200);
			if (ok.listFiles().length == 2)
				break;
		}

		assertEquals(ok.listFiles().length, 2, 1);
		assertEquals(tmpHFDir.listFiles().length, 0, 1);

		hf.stop();
	}

	void createPair(final int i) throws IOException
	{
		final String fileName = "f" + i;
		final File dir = new File(theHFDir + File.separator + fileName + ".dir");
		while (dir.exists())
		{
			ThreadUtil.sleep(1);
		}
		dir.mkdir();
		final File file = new File(theHFDir + File.separator + fileName + ".txt");
		file.createNewFile();
	}

	/**
	 *
	 * ok or error folder testing
	 *
	 * @throws Exception
	 */
	@Test
	public synchronized void testOKErrorNonAscii() throws Exception
	{
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		File error = new File("error");
		hf.setErrorStorage(error);
		File ok = new File("ok");
		hf.setOKStorage(ok);
		hf.setMaxStore(42);
		ThreadUtil.sleep(1000);
		for (int i = 0; i < 4; i++)
		{

			final File file = new File(theHFDir + File.separator + "f ä ö ü €" + i + ".txt");
			file.createNewFile();
		}
		ok = FileUtil.getFileInDirectory(theHFDir, ok);
		error = FileUtil.getFileInDirectory(theHFDir, error);
		ThreadUtil.sleep(2000);
		assertEquals(ok.listFiles().length, 2, 1);
		assertEquals(tmpHFDir.listFiles().length, 0, 1);
		assertEquals(error.listFiles().length, 2, 1);
		hf.stop();
	}

	/**
	 *
	 * ok or error folder testing
	 *
	 * @throws Exception
	 */
	@Test
	public synchronized void testOKErrorNonAsciiAux() throws Exception
	{
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		File error = new File("error");
		hf.setErrorStorage(error);
		File ok = new File("ok");
		hf.setOKStorage(ok);
		hf.setMaxStore(42);
		ThreadUtil.sleep(1000);
		for (int i = 0; i < 4; i++)
		{
			final File file = new File(theHFDir + File.separator + "()&¢$[]f ä ö ü +&€" + i + ".txt");
			FileUtil.newExtension(file, "content").mkdirs();
			file.createNewFile();
		}
		ok = FileUtil.getFileInDirectory(theHFDir, ok);
		error = FileUtil.getFileInDirectory(theHFDir, error);
		ThreadUtil.sleep(2000);
		assertEquals(ok.listFiles().length, 4, 1);
		assertEquals(tmpHFDir.listFiles().length, 0, 1);
		assertEquals(error.listFiles().length, 4, 1);
		hf.stop();
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	@After
	public void tearDown() throws Exception
	{
		OrderedTaskQueue.shutDownAll();
		super.tearDown();
	}
}
