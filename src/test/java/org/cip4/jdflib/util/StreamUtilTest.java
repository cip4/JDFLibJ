/**
 * The CIP4 Software License, Version 1.0
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
 */
package org.cip4.jdflib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.VString;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 * @date Apr 14, 2011
 */
class StreamUtilTest extends JDFTestCaseBase
{
	/**
	 *
	 *
	 * @throws IOException
	 */
	@Test
	void testGetLines() throws IOException
	{
		final ByteArrayIOStream bos = new ByteArrayIOStream();
		bos.write("foooo\nbar\nbar2".getBytes());
		final VString vs = StreamUtil.getLines(bos.getInputStream());
		assertEquals(3, vs.size());
		bos.close();
	}

	/**
	 *
	 *
	 * @throws IOException
	 */
	@Test
	void testReset() throws IOException
	{
		final InputStream s = FileUtil.getBufferedInputStream(new File(sm_dirTestData + "page.pdf"));
		s.mark(42);
		final byte[] b = new byte[4];
		s.read(b);
		assertEquals(new String(b), "%PDF");
		StreamUtil.reset(s);
		s.read(b);
		assertEquals(new String(b), "%PDF");
		StreamUtil.reset(null);
		s.read(b);
		assertNotSame(new String(b), "%PDF");
	}

	/**
	 *
	 *
	 * @throws IOException
	 */
	@Test
	void testCopy() throws IOException
	{
		final InputStream s = FileUtil.getBufferedInputStream(new File(sm_dirTestData + "page.pdf"));
		OutputStream os = new ByteArrayIOStream();
		StreamUtil.copy(null, os);
		StreamUtil.copy(s, null);
		StreamUtil.copy(s, os);
	}

	/**
	 *
	 *
	 * @throws IOException
	 */
	@Test
	void testReplaceBytes() throws IOException
	{
		final File f1 = new File(sm_dirTestData + "page.pdf");
		final InputStream s = FileUtil.getBufferedInputStream(f1);
		final File f2 = new File(sm_dirTestDataTemp + "page1.pdf");
		final OutputStream os = FileUtil.getBufferedOutputStream(f2);
		assertTrue(StreamUtil.replaceBytes(s, os, "QuarkXPress".getBytes(StandardCharsets.UTF_8), "CIP4Library".getBytes(StandardCharsets.UTF_8), -1));
		StreamUtil.close(s);

		StreamUtil.close(os);
		assertEquals(f1.length(), f2.length());
	}

	/**
	 *
	 *
	 * @throws IOException
	 */
	@Test
	void testReplaceBytesSingle() throws IOException
	{
		final File f1 = new File(sm_dirTestData + "page.pdf");
		final InputStream s = FileUtil.getBufferedInputStream(f1);
		final File f2 = new File(sm_dirTestDataTemp + "page1.pdf");
		final OutputStream os = FileUtil.getBufferedOutputStream(f2);
		asserTrue(StreamUtil.replaceBytes(s, os, "QuarkXPress".getBytes(StandardCharsets.UTF_8), "CIP4Library AND LOTS Longer".getBytes(StandardCharsets.UTF_8), 1));
		StreamUtil.close(s);
		StreamUtil.close(os);
		assertEquals(f1.length(), f2.length() - "CIP4Library AND LOTS Longer".length() + "QuarkXPress".length());

	}

	private void asserTrue(final boolean replaceBytes)
	{
		// TODO Auto-generated method stub

	}

	/**
	 *
	 *
	 * @throws IOException
	 */
	@Test
	void testClose() throws IOException
	{
		for (int i = 0; i < 42; i++)
		{
			final InputStream s = FileUtil.getBufferedInputStream(new File(sm_dirTestData + "page.pdf"));
			s.mark(42);
			final byte[] b = new byte[4];
			s.read(b);
			assertEquals(new String(b), "%PDF");
			StreamUtil.reset(s);
			s.read(b);
			assertEquals(new String(b), "%PDF");
			StreamUtil.reset(null);
			s.read(b);
			assertNotSame(new String(b), "%PDF");
			StreamUtil.close(s);
		}
	}

	/**
	 *
	 *
	 * @throws IOException
	 */
	@Test
	void testGetMD5() throws IOException
	{
		final InputStream s = FileUtil.getBufferedInputStream(new File(sm_dirTestData + "page.pdf"));
		final byte[] md5 = StreamUtil.getMD5(s);
		assertNotNull(md5);
		s.close();
		final InputStream s2 = FileUtil.getBufferedInputStream(new File(sm_dirTestData + "page.pdf"));
		final byte[] md52 = StreamUtil.getMD5(s2);
		for (int i = 0; i < md5.length; i++)
			assertEquals(md5[i], md52[i]);
		s2.close();
	}
}
