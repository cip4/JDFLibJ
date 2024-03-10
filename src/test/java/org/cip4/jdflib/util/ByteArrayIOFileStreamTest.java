/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.ByteArrayIOStream.ByteArrayIOInputStream;
import org.junit.jupiter.api.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         Jul 16, 2009
 */
public class ByteArrayIOFileStreamTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	void testSize()
	{
		final ByteArrayIOStream ios = new ByteArrayIOFileStream(2000);
		for (int i = 0; i < 12345; i++)
		{
			ios.write(i);
			assertEquals(ios.size(), 1 + i);
		}
		ios.close();

	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testFileSize() throws IOException
	{
		final ByteArrayIOFileStream ios = new ByteArrayIOFileStream(2000);
		for (int i = 0; i < 12345; i++)
		{
			ios.write(i);
			assertEquals(ios.size(), 1 + i);
		}
		ios.flush();
		assertEquals(12345, ios.getFile().length());
		ios.close();
	}

	/**
	 *
	 */
	@Test
	void testTell()
	{
		final ByteArrayIOStream ios = new ByteArrayIOFileStream(2000);
		for (int i = 0; i < 12345; i++)
		{
			ios.write(i);
		}
		final ByteArrayIOInputStream in = ios.getInputStream();
		assertEquals(0, in.tell());
		in.seek(-1);
		assertTrue(in.read() >= 0);
		assertTrue(in.read() < 0);
		in.seek(42);
		assertEquals(42, in.tell());
		ios.close();
	}

	/**
	 *
	 */
	@Test
	void testConstructStream()
	{
		final ByteArrayIOStream ios = new ByteArrayIOFileStream(2000);
		for (int i = 0; i < 123456; i++)
		{
			ios.write(i);
		}
		final ByteArrayIOStream ios2 = new ByteArrayIOStream(ios.getInputStream());
		final ByteArrayInputStream is1 = ios.getInputStream();
		final ByteArrayInputStream is2 = ios2.getInputStream();
		for (int i = 0; i < 123457; i++)
		{
			assertEquals(is1.read(), is2.read());
		}
		ios.close();
		ios2.close();
	}

	/**
	 *
	 */
	@Test
	void testToStreing()
	{
		final ByteArrayIOStream ios = new ByteArrayIOFileStream(2000);
		for (int i = 0; i < 123456; i++)
		{
			ios.write(i);
		}
		final ByteArrayIOStream ios2 = new ByteArrayIOStream(ios.getInputStream());
		final ByteArrayInputStream is1 = ios.getInputStream();
		final ByteArrayInputStream is2 = ios2.getInputStream();
		for (int i = 0; i < 123457; i++)
		{
			assertEquals(is1.read(), is2.read());
		}
		ios.close();
		ios2.close();
		assertNotNull(ios.toString());
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testConstructFile() throws IOException
	{
		final File f = new File(sm_dirTestDataTemp + "bios222.fil");
		f.delete();
		f.createNewFile();
		final FileOutputStream fos = new FileOutputStream(f);
		for (int i = 0; i < 20000; i++)
		{
			fos.write(i % 256);
		}
		fos.close();
		ByteArrayIOStream ios = new ByteArrayIOFileStream(f, 333, true);
		InputStream is = ios.getInputStream();
		for (int i = 0; i < 20000; i++)
		{
			assertEquals(is.read(), i % 256);
		}
		ios.close();
		ios = new ByteArrayIOFileStream(f, 333333, true);
		is = ios.getInputStream();
		for (int i = 0; i < 20000; i++)
		{
			assertEquals(is.read(), i % 256);
		}
		ios.close();
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testDeleteFile() throws IOException
	{
		final File f = new File(sm_dirTestDataTemp + "bios222.fil");
		f.delete();
		f.createNewFile();
		final FileOutputStream fos = new FileOutputStream(f);
		for (int i = 0; i < 20000; i++)
		{
			fos.write(i % 256);
		}
		fos.close();
		ByteArrayIOStream ios = new ByteArrayIOFileStream(f, 333, true);
		InputStream is = ios.getInputStream();
		for (int i = 0; i < 20000; i++)
		{
			assertEquals(is.read(), i % 256);
		}
		ios.close();
		assertTrue(f.delete());
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testDeleteFileLarge() throws IOException
	{
		final File f = new File(sm_dirTestDataTemp + "bios222.fil");
		f.delete();
		f.createNewFile();
		final FileOutputStream fos = new FileOutputStream(f);
		for (int i = 0; i < 20000; i++)
		{
			fos.write(i % 256);
		}
		fos.close();
		ByteArrayIOStream ios = new ByteArrayIOFileStream(f, 33333, true);
		InputStream is = ios.getInputStream();
		for (int i = 0; i < 20000; i++)
		{
			assertEquals(is.read(), i % 256);
		}
		ios.close();
		assertTrue(f.delete());
	}

	/**
	 * @throws IOException
	 *
	 */
	@Test
	void testConstructBadFile() throws IOException
	{
		File f = new File(sm_dirTestDataTemp + "bios1.fil");
		f.delete();
		ThreadUtil.sleep(123);
		assertFalse(f.exists());
		ByteArrayIOStream ios = new ByteArrayIOFileStream(f, 200, true);
		InputStream is = ios.getInputStream();
		assertEquals(is.available(), 0);
		ios.close();
		// now null
		f = null;
		ios = new ByteArrayIOFileStream(f, 200, true);
		is = ios.getInputStream();
		assertEquals(is.available(), 0);
		ios.close();
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testInRead() throws Exception
	{
		final ByteArrayIOStream ios = new ByteArrayIOFileStream(2000);
		for (int i = 0; i < 200000; i++)
		{
			ios.write(i);
		}
		final InputStream is = ios.getInputStream();
		int n = 0;
		int i;
		while ((i = is.read()) >= 0)
		{
			assertEquals(i, n % 256, "" + n);
			n++;
		}
		assertEquals(n, 200000);
		ios.close();
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testInReadMulti() throws Exception
	{
		final ByteArrayIOStream ios = new ByteArrayIOFileStream(2222);
		final int len = 10000;
		for (int i = 0; i < len; i++)
		{
			ios.write(i);
		}
		final InputStream is = ios.getInputStream();
		final InputStream is2 = ios.getInputStream();
		int n = 0;
		int i;
		while ((i = is.read()) >= 0)
		{
			final int jj = is2.read();
			assertEquals(i, n % 256, "" + n);
			assertEquals(jj, i);
			n++;
		}
		assertEquals(n, len);
		ios.close();
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testCloseSkip()
	{
		final ByteArrayIOStream ios = new ByteArrayIOFileStream(2222);
		final int len = 10000;
		for (int i = 0; i < len; i++)
		{
			ios.write(i);
		}
		final InputStream is = ios.getInputStream();
		ios.close();
		try
		{
			is.skip(4);
			fail("");
		}
		catch (final Exception e)
		{
			//
		}

	}

	/**
	 * @throws Exception
	 */
	@Test
	void testCloseSkip2()
	{
		final ByteArrayIOStream ios = new ByteArrayIOFileStream(2222);
		final int len = 10000;
		for (int i = 0; i < len; i++)
		{
			ios.write(i);
		}
		final InputStream i = ios.getInputStream();
		final ByteArrayIOStream ios2 = new ByteArrayIOFileStream(i, 777);
		final InputStream is = ios2.getInputStream();
		ios.close();
		try
		{
			final long n = is.skip(4);
			assertEquals(0, n);
		}
		catch (final IOException e)
		{
			//
		}

	}

	/**
	 * @throws Exception
	 */
	@Test
	void testInReadMulti2() throws Exception
	{
		final ByteArrayIOStream ios = new ByteArrayIOFileStream(2222);
		final int len = 10000;
		for (int i = 0; i < len; i++)
		{
			ios.write(i);
		}
		final ByteArrayIOInputStream is = ios.getInputStream();
		final InputStream is2 = is.getNewStream();
		int n = 0;
		int i;
		while ((i = is.read()) >= 0)
		{
			final int jj = is2.read();
			assertEquals(i, n % 256, "" + n);
			assertEquals(jj, i);
			n++;
		}
		assertEquals(n, len);
		ios.close();
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testGetBufferedInputStream() throws Exception
	{
		final ByteArrayIOStream ios = new ByteArrayIOFileStream(444);
		final int len = 10000;
		for (int i = 0; i < len; i++)
		{
			ios.write(i);
		}
		final ByteArrayIOInputStream is = ios.getInputStream();
		final InputStream is2 = ByteArrayIOFileStream.getBufferedInputStream(is, 4444);
		int n = 0;
		int i;
		while ((i = is.read()) >= 0)
		{
			final int jj = is2.read();
			assertEquals(i, n % 256, "" + n);
			assertEquals(jj, i);
			n++;
		}
		assertEquals(n, len);
		ios.close();
	}

	/**
	 * @throws Exception
	 */
	@Test
	void testGetBufferedInputStreamKeep() throws Exception
	{
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		final int len = 10000;
		for (int i = 0; i < len; i++)
		{
			ios.write(i);
		}
		final File tmp = new File(sm_dirTestDataTemp + "tmp.tmp");
		FileUtil.streamToFile(ios.getInputStream(), tmp);
		final InputStream is2 = new ByteArrayIOFileStream(FileUtil.getBufferedInputStream(tmp), 4444).getInputStream();

		int n = 0;
		for (int i = 0; i < 10; i++)
		{
			final byte[] big = new byte[10000000];
		}
		System.gc();
		ThreadUtil.sleep(10);
		int i;
		while ((i = is2.read()) >= 0)
		{
			final byte[] big = new byte[10000];
			if (i % 20 == 0)
			{
				System.gc();
				ThreadUtil.sleep(1);
			}
			n++;
		}
		assertEquals(n, len);
	}

	/**
	 * @throws Throwable
	 */
	@Test
	void testGetBufferedInputStreamFinalize() throws Throwable
	{
		final File f = new File(sm_dirTestDataTemp + "bios224.fil");
		f.delete();
		f.createNewFile();
		final FileOutputStream fos = new FileOutputStream(f);
		for (int i = 0; i < 20000; i++)
		{
			fos.write(i % 256);
		}
		fos.close();
		ByteArrayIOFileStream ios = new ByteArrayIOFileStream(f, 333, true);
		ByteArrayIOFileStream ios2 = new ByteArrayIOFileStream(ios.getInputStream(), 333);
		ios.finalize();
		ByteArrayIOInputStream is = ios2.getInputStream();
		int n = 0;
		while (is.read() >= 0)
		{
			n++;
		}
		assertEquals(n, 20000);
		ios2.close();

	}

	/**
	 * @throws Throwable
	 */
	@Test
	void testreadFinalize() throws Throwable
	{
		final File f = new File(sm_dirTestDataTemp + "bios223.fil");
		f.delete();
		f.createNewFile();
		final FileOutputStream fos = new FileOutputStream(f);
		for (int i = 0; i < 20000; i++)
		{
			fos.write(i % 256);
		}
		fos.close();
		ByteArrayIOFileStream ios = new ByteArrayIOFileStream(f, 333, true);
		ByteArrayIOInputStream is = ios.getInputStream();
		ios.finalize();
		int n = 0;
		try
		{
			is.read();
			fail();
		}
		catch (Exception e)
		{
			// nop
		}

	}

}
