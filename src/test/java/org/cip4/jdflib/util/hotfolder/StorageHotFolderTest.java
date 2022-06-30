/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

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
			iCount = new AtomicInteger(0);
			delay = 1;
		}

		private int delay;
		private final AtomicInteger iCount;

		/**
		 * dummy that alternates ok and false
		 *
		 * @see HotFolderListener#hotFile(File)
		 */
		@Override
		public boolean hotFile(final File hotFile)
		{
			if (delay > 0)
				ThreadUtil.sleep(delay);
			return iCount.getAndIncrement() % 2 == 0;
		}

		/**
		 * @return the delay
		 */
		protected int getDelay()
		{
			return delay;
		}

		/**
		 * @param delay the delay to set
		 */
		protected void setDelay(final int delay)
		{
			this.delay = delay;
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
		 * @see HotFolderListener#hotFile(File)
		 */
		@Override
		public boolean hotFile(final File hotFile)
		{
			final File dumpDir = new File(sm_dirTestDataTemp + "URLOut" + ai.get());
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

	@TempDir
	File theHFDir;
	@TempDir
	File tmpHFDir;
	static AtomicInteger ai = new AtomicInteger(0);

	/**
	 *
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public synchronized void setUp() throws Exception
	{
		OrderedTaskQueue.shutDownAll();
		super.setUp();

		log.info("Setting up: " + theHFDir);
		HotFolder.setDefaultStabilizeTime(100);
	}

	/**
	 *
	 * simple creation
	 *
	 * @throws IOException
	 */
	public synchronized void testSimple() throws IOException
	{
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		final File file = new File(theHFDir + File.separator + "f1.txt");
		file.createNewFile();
		Assertions.assertTrue(file.exists());
		for (int i = 0; i < 4200; i++)
		{
			ThreadUtil.sleep(20);
			if (!file.exists() && tmpHFDir.listFiles().length == 0)
				break;
		}

		Assertions.assertFalse(file.exists());
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 0);
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
		Assertions.assertTrue(file.exists());
		for (int i = 0; i < 4800; i++)
		{
			ThreadUtil.sleep(10);
			if (!file.exists() && !content.exists() && tmpHFDir.listFiles().length == 0)
				break;
		}
		Assertions.assertFalse(file.exists(), file.getAbsolutePath());
		Assertions.assertFalse(content.exists(), content.getAbsolutePath());
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 1);
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
		Assertions.assertTrue(file.exists());
		for (int i = 0; i < 4200; i++)
		{
			ThreadUtil.sleep(10);
			final File file2 = new File(hfPath + "/OK/dummy space");
			if (!file.exists() && !content.exists() && !content.exists() && file2.isDirectory() && tmpHFDir.listFiles().length == 0)
				break;
		}
		Assertions.assertFalse(file.exists(), file.getAbsolutePath());
		Assertions.assertFalse(content.exists(), content.getAbsolutePath());
		final File file2 = new File(hfPath + "/OK/dummy space");
		Assertions.assertTrue(file2.isDirectory());
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 0);
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
		for (int i = 0; i < 1234; i++)
		{
			ThreadUtil.sleep(4);
			if (!file.exists() && tmpHFDir.listFiles().length == 0)
				break;
		}
		Assertions.assertFalse(file.exists());
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 0);
		hf.stop();
	}

	/**
	 *
	 * check problems with special characters
	 *
	 * @throws IOException
	 */
	@Test
	public synchronized void testEvilFile() throws IOException
	{
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		final File file = new File(theHFDir + File.separator + "a.~#~2.xml");
		file.createNewFile();
		for (int i = 0; i < 1234; i++)
		{
			ThreadUtil.sleep(4);
			if (!file.exists() && tmpHFDir.listFiles().length == 0)
			{
				break;
			}
		}
		Assertions.assertFalse(file.exists());
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 0);
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
		Assertions.assertTrue(file.exists());
		ThreadUtil.sleep(1000);
		Assertions.assertTrue(file.exists());
		hf.addListener(new CountListener(), ".txt");
		for (int i = 0; i < 100; i++)
		{
			ThreadUtil.sleep(200);
			if (!file.exists())
				break;
		}
		Assertions.assertFalse(file.exists());
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
		for (int i = 0; i < 42; i++)
		{
			ThreadUtil.sleep(111);
			if (ok.listFiles().length == 2 && error.listFiles().length == 2)
				break;
		}
		Assertions.assertEquals(ok.listFiles().length, 2, 1);
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 1);
		Assertions.assertEquals(error.listFiles().length, 2, 1);
		for (int i = 0; i < 4; i++)
		{
			final File file = new File(theHFDir + File.separator + "f" + i + ".txt");
			file.createNewFile();
		}
		for (int i = 0; i < 42; i++)
		{
			ThreadUtil.sleep(111);
			if (ok.listFiles().length == 4 && error.listFiles().length == 4)
				break;
		}
		Assertions.assertEquals(ok.listFiles().length, 4, 1);
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 1);
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
		Assertions.assertEquals(ok.listFiles().length, 42, 13);
		Assertions.assertEquals(0, tmpHFDir.listFiles().length, 1);
		Assertions.assertEquals(error.listFiles().length, 42, 13);

		hf.stop();
	}

	@Test
	public synchronized void testSetRetry() throws Exception
	{
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		hf.setRetry(-99);
		Assertions.assertEquals(1, hf.retry);
		hf.setRetry(99);
		Assertions.assertEquals(99, hf.retry);
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
		ThreadUtil.sleep(300);

		for (int i = 0; i < 4; i++)
		{
			final File file = new File(theHFDir + File.separator + "f" + i + ".txt");
			file.createNewFile();
		}
		ok = FileUtil.getFileInDirectory(theHFDir, ok);
		error = FileUtil.getFileInDirectory(theHFDir, error);
		for (int i = 0; i < 4200; i++)
		{
			if (ok.listFiles().length < 2)
				ThreadUtil.sleep(10);
		}
		Assertions.assertEquals(ok.listFiles().length, 2, 1);
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 1);
		Assertions.assertEquals(error.listFiles().length, 2, 1);
		for (int i = 0; i < 4; i++)
		{
			final File file = new File(theHFDir + File.separator + "f" + i + ".txt");
			file.createNewFile();
		}
		for (int i = 0; i < 4200; i++)
		{
			if (ok.listFiles().length < 4)
				ThreadUtil.sleep(10);
		}
		Assertions.assertEquals(ok.listFiles().length, 4, 1);
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 1);
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
		Assertions.assertEquals(ok.listFiles().length, 42, 13);
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 2);
		Assertions.assertEquals(error.listFiles().length, 42, 13);

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
		ThreadUtil.sleep(500);

		for (int i = 0; i < 4; i++)
		{
			final File file = new File(theHFDir + File.separator + "f" + i + ".txt");
			file.createNewFile();
		}
		ok = FileUtil.getFileInDirectory(theHFDir, ok);
		error = FileUtil.getFileInDirectory(theHFDir, error);
		for (int i = 0; i < 1000; i++)
		{
			ThreadUtil.sleep(50);
			if (ok.listFiles().length >= 2 && error.listFiles().length >= 2 && tmpHFDir.listFiles().length < 2)
				break;
		}
		Assertions.assertEquals(ok.listFiles().length, 2, 1);
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 1);
		Assertions.assertEquals(error.listFiles().length, 2, 1);
		for (int i = 0; i < 4; i++)
		{
			final File file = new File(theHFDir + File.separator + "f" + i + ".txt");
			file.createNewFile();
		}
		for (int i = 0; i < 1000; i++)
		{
			ThreadUtil.sleep(50);
			if (ok.listFiles().length >= 4 && tmpHFDir.listFiles().length < 1)
				break;
		}
		Assertions.assertEquals(ok.listFiles().length, 4, 1);
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 3);
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
		Assertions.assertEquals(ok.listFiles().length, 42, 13);
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 5);
		Assertions.assertEquals(error.listFiles().length, 42, 13);

		hf.stop();
	}

	/**
	 *
	 * ok or error folder testing
	 *
	 * @throws Exception
	 */
	@Test
	public synchronized void testOKErrorMultiDelay() throws Exception
	{

		final CountListener cl = new CountListener();
		cl.setDelay(2000);
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, cl);
		hf.setStabilizeTime(100);
		File error = new File("error");
		hf.setErrorStorage(error);
		File ok = new File("ok");
		hf.setOKStorage(ok);
		hf.setMaxStore(42);
		hf.restart();
		hf.setMaxConcurrent(20);
		ThreadUtil.sleep(500);

		for (int i = 0; i < 10; i++)
		{
			final File file = new File(theHFDir + File.separator + "fok_err" + i + ".txt");
			file.createNewFile();
		}
		ThreadUtil.sleep(2000);
		final long t0 = System.currentTimeMillis();
		ok = FileUtil.getFileInDirectory(theHFDir, ok);
		error = FileUtil.getFileInDirectory(theHFDir, error);
		for (int i = 0; i < 1000; i++)
		{
			ThreadUtil.sleep(50);
			if (ok.listFiles().length >= 4 && error.listFiles().length >= 4 && tmpHFDir.listFiles().length < 2)
				break;
		}
		Assertions.assertEquals(ok.listFiles().length, 5, 1);
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 1);
		Assertions.assertEquals(error.listFiles().length, 5, 1);
		// not 2000 * 10...
		Assertions.assertTrue(System.currentTimeMillis() - t0 < 5000);
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

		Assertions.assertEquals(ok.listFiles().length, 20, 4);
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 4);
		Assertions.assertEquals(error.listFiles().length, 20, 4);

		hf.stop();
	}

	/**
	 *
	 * ok or error folder testing
	 *
	 * @throws Exception
	 */
	@Test
	public synchronized void testMaxAux() throws Exception
	{
		final StorageHotFolder hf = new StorageHotFolder(theHFDir, tmpHFDir, null, new CountListener());
		hf.setStabilizeTime(100);
		File error = new File("error");
		hf.setErrorStorage(error);
		File ok = new File("ok");
		hf.setOKStorage(ok);
		hf.setMaxStore(40);
		hf.setMaxAux(10);
		hf.restart();
		ThreadUtil.sleep(300);

		for (int i = 0; i < 100; i++)
			createPair(i);

		ok = FileUtil.getFileInDirectory(theHFDir, ok);
		error = FileUtil.getFileInDirectory(theHFDir, error);
		for (int i = 0; i < 1000; i++)
		{
			ThreadUtil.sleep(20);
			if (tmpHFDir.listFiles().length == 0 && theHFDir.listFiles().length <= 2)
				break;
		}

		Assertions.assertTrue(FileUtil.listDirectories(ok).length < 15);
		Assertions.assertTrue(FileUtil.listDirectories(error).length < 15);

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

		Assertions.assertEquals(ok.listFiles().length, 2, 1);
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 1);

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
		ThreadUtil.sleep(300);
		for (int i = 0; i < 4; i++)
		{

			final File file = new File(theHFDir + File.separator + "f ä ö ü €" + i + ".txt");
			file.createNewFile();
		}
		ok = FileUtil.getFileInDirectory(theHFDir, ok);
		error = FileUtil.getFileInDirectory(theHFDir, error);
		for (int i = 0; i < 400; i++)
		{
			ThreadUtil.sleep(20);
			if (ok.listFiles().length == 2 && ok.listFiles().length == 2)
				break;
		}
		Assertions.assertEquals(ok.listFiles().length, 2, 1);
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 1);
		Assertions.assertEquals(error.listFiles().length, 2, 1);
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
		for (int i = 0; i < 123; i++)
		{
			ThreadUtil.sleep(20);
			if (theHFDir.listFiles().length == 2 && tmpHFDir.listFiles().length == 0)
				break;
		}
		Assertions.assertEquals(4, ok.listFiles().length, 1);
		Assertions.assertEquals(tmpHFDir.listFiles().length, 0, 1);
		Assertions.assertEquals(error.listFiles().length, 4, 1);
		hf.stop();
	}

	/**
	 * @see JDFTestCaseBase#tearDown()
	 */
	@Override
	@AfterEach
	public void tearDown() throws Exception
	{
		OrderedTaskQueue.shutDownAll();
		super.tearDown();
	}
}
