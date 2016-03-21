/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of
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
import java.io.IOException;
import java.io.InputStream;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.ByteArrayIOStream.ByteArrayIOInputStream;
import org.junit.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * Jul 16, 2009
 */
public class ByteArrayIOStreamTest extends JDFTestCaseBase
{

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	@Test
	public void testSize()
	{
		final ByteArrayIOStream ios = new ByteArrayIOStream(20000);
		for (int i = 0; i < 12345; i++)
		{
			ios.write(i);
			assertEquals(ios.size(), 1 + i);
		}
		ios.close();
	}

	/**
	 * 
	 */
	@Test
	public void testTell()
	{
		final ByteArrayIOStream ios = new ByteArrayIOStream(20000);
		for (int i = 0; i < 12345; i++)
		{
			ios.write(i);
		}
		ByteArrayIOInputStream in = ios.getInputStream();
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
	public void testConstructStream()
	{
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		for (int i = 0; i < 1234567; i++)
		{
			ios.write(i);
		}
		final ByteArrayIOStream ios2 = new ByteArrayIOStream(ios.getInputStream());
		assertEquals(ios.toString(), ios2.toString());
		ios.close();
		ios2.close();
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testConstructFile() throws IOException
	{
		final File f = new File(sm_dirTestDataTemp + "bios.fil");
		FileUtil.forceDelete(f);
		f.createNewFile();
		final FileOutputStream fos = new FileOutputStream(f);
		for (int i = 0; i < 20000; i++)
		{
			fos.write(i % 256);
		}
		fos.close();
		final ByteArrayIOStream ios = new ByteArrayIOStream(f);
		final InputStream is = ios.getInputStream();
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
	public void testConstructBadFile() throws IOException
	{
		File f = new File(sm_dirTestDataTemp + "bios.fil");
		FileUtil.forceDelete(f);
		ByteArrayIOStream ios = new ByteArrayIOStream(f);
		InputStream is = ios.getInputStream();
		assertEquals(is.available(), 0);
		ios.close();
		// now null
		f = null;
		ios = new ByteArrayIOStream(f);
		is = ios.getInputStream();
		assertEquals(is.available(), 0);
		ios.close();
	}

	/**
	 * 
	 */
	@Test
	public void testConstructBuf()
	{
		final byte[] b = "abc".getBytes();
		final ByteArrayIOStream ios = new ByteArrayIOStream(b);

		assertEquals(ios.getInputStream().available(), 3);
		ios.close();
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testInRead() throws Exception
	{
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		for (int i = 0; i < 200000; i++)
		{
			ios.write(i);
		}
		final InputStream is = ios.getInputStream();
		int n = 0;
		int i;
		while ((i = is.read()) >= 0)
		{
			assertEquals("" + n, i, n % 256);
			n++;
		}
		assertEquals(n, 200000);
		ios.close();
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testInReadMulti() throws Exception
	{
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		for (int i = 0; i < 50000; i++)
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
			assertEquals("" + n, i, n % 256);
			assertEquals(jj, i);
			n++;
		}
		assertEquals(n, 50000);
		ios.close();
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testInReadMulti2() throws Exception
	{
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		for (int i = 0; i < 50000; i++)
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
			assertEquals("" + n, i, n % 256);
			assertEquals(jj, i);
			n++;
		}
		assertEquals(n, 50000);
		ios.close();
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetBufferedInputStream() throws Exception
	{
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		for (int i = 0; i < 50000; i++)
		{
			ios.write(i);
		}
		final ByteArrayIOInputStream is = ios.getInputStream();
		final InputStream is2 = ByteArrayIOStream.getBufferedInputStream(is);
		int n = 0;
		int i;
		while ((i = is.read()) >= 0)
		{
			final int jj = is2.read();
			assertEquals("" + n, i, n % 256);
			assertEquals(jj, i);
			n++;
		}
		assertEquals(n, 50000);
		ios.close();
	}

}
