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
package org.cip4.jdflib.util.hotfolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.hotfolder.StorageHotFolderListener.DelayedRunner;
import org.junit.jupiter.api.Test;

class StorageHotFolderListenerTest extends JDFTestCaseBase
{

	protected class DummyListener implements HotFolderListener
	{

		/**
		 *
		 *
		 * @see org.cip4.jdflib.util.HotFolderListener#hotFile(java.io.File)
		 */
		@Override
		public boolean hotFile(final File hotFile)
		{
			return true;
		}

	}

	protected class BoomListener implements HotFolderListener
	{

		/**
		 *
		 *
		 * @see org.cip4.jdflib.util.HotFolderListener#hotFile(java.io.File)
		 */
		@Override
		public boolean hotFile(final File hotFile)
		{
			throw new IllegalArgumentException();
		}

	}

	/**
	 *
	 */
	@Test
	void testConstruct()
	{
		final File theHFDir = new File(sm_dirTestDataTemp + File.separator + "Foo");
		FileUtil.deleteAll(theHFDir);
		theHFDir.mkdirs();
		final StorageHotFolder parent = new StorageHotFolder(theHFDir, theHFDir, null, null);
		final StorageHotFolderListener hl = new StorageHotFolderListener(theHFDir, new DummyListener(), parent);
		assertNotNull(hl);
	}

	/**
	 *
	 */
	@Test
	void testBoom()
	{
		final File theHFDir = new File(sm_dirTestDataTemp + File.separator + "Foo");
		FileUtil.deleteAll(theHFDir);
		theHFDir.mkdirs();
		final StorageHotFolder parent = new StorageHotFolder(theHFDir, theHFDir, null, null);
		parent.setSynchronous(true);
		final StorageHotFolderListener hl = new StorageHotFolderListener(theHFDir, new BoomListener(), parent);

		assertFalse(hl.hotFile(new File("a")));
	}

	/**
	 *
	 */
	@Test
	void testgetStored()
	{
		final File theHFDir = new File(sm_dirTestDataTemp + File.separator + "Foo");
		FileUtil.deleteAll(theHFDir);
		theHFDir.mkdirs();
		final StorageHotFolderListener hl = new StorageHotFolderListener(theHFDir, new BoomListener(), new StorageHotFolder(theHFDir, theHFDir, null, null));
		assertNull(hl.getStoredFile(new File("a")));
		assertNull(hl.getStoredFile(null));
	}

	/**
	 *
	 */
	@Test
	void testToString()
	{
		final File theHFDir = new File(sm_dirTestDataTemp + File.separator + "Foo");
		FileUtil.deleteAll(theHFDir);
		theHFDir.mkdirs();
		final StorageHotFolderListener hl = new StorageHotFolderListener(theHFDir, new BoomListener(), new StorageHotFolder(theHFDir, theHFDir, null, null));
		assertNotNull(hl.toString());
		hl.setErrorStorage(null);
		assertNotNull(hl.toString());
		hl.setOKStorage(null);
		assertNotNull(hl.toString());
		final StorageHotFolder parent = new StorageHotFolder(theHFDir, theHFDir, null, null);
		final StorageHotFolderListener hlnull = new StorageHotFolderListener(null, new BoomListener(), parent);
		assertNotNull(hlnull.toString());
	}

	/**
	 *
	 */
	@Test
	void testDelayedRunner()
	{
		final File theHFDir = new File(sm_dirTestDataTemp + File.separator + "Foo");
		FileUtil.deleteAll(theHFDir);
		theHFDir.mkdirs();
		final StorageHotFolderListener hl = new StorageHotFolderListener(theHFDir, new BoomListener(), new StorageHotFolder(theHFDir, theHFDir, null, null));
		final File hotFile = new File("a");
		final DelayedRunner dr = hl.new DelayedRunner(hotFile);
		assertNotNull(dr.toString());
		assertFalse(dr.ok);
		assertEquals(hotFile, dr.hotFile);
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testBoom2() throws IOException
	{
		final File theHFDir = new File(sm_dirTestDataTemp + File.separator + "Foo");
		FileUtil.deleteAll(theHFDir);
		theHFDir.mkdirs();
		final File file = new File(theHFDir, "a");
		file.createNewFile();
		final StorageHotFolder parent = new StorageHotFolder(theHFDir, theHFDir, null, null);
		parent.setSynchronous(true);
		final StorageHotFolderListener hl = new StorageHotFolderListener(theHFDir, new BoomListener(), parent);
		assertFalse(hl.hotFile(file));
		assertTrue(new File(theHFDir, "error/a.error.txt").canRead());
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testHandleBad() throws IOException
	{
		final File theHFDir = new File(sm_dirTestDataTemp + File.separator + "FooBad");
		FileUtil.deleteAll(theHFDir);
		theHFDir.mkdirs();
		final File file = new File(theHFDir, "a");
		final File file2 = new File(theHFDir, "b");
		file.createNewFile();
		file2.createNewFile();
		final StorageHotFolderListener hl = new StorageHotFolderListener(theHFDir, new BoomListener(), new StorageHotFolder(theHFDir, theHFDir, null, null));
		hl.setOKStorage(new File("ok"));
		hl.setErrorStorage(new File("nok"));
		assertTrue(hl.handleBad(file, true));
		assertFalse(file.exists());
		assertTrue(hl.handleBad(file2, false));
		assertFalse(file2.exists());
	}

}
