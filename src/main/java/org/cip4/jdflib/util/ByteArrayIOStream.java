/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of 
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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * KString.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.ByteArrayIOFileStream.ByteArrayIOFileInputStream;

/**
 * Shared input / outputStream class write once, read many...
 * 
 * @author rainer prosi
 * 
 */
public class ByteArrayIOStream extends ByteArrayOutputStream
{
	/**
	 * 
	 * get a completely buffered and resizable input stream
	 * 
	 * @param is
	 * @return
	 */
	public static ByteArrayIOInputStream getBufferedInputStream(InputStream is)
	{
		if (is == null)
			return null;
		if (is instanceof ByteArrayIOInputStream)
			return ((ByteArrayIOInputStream) is).getNewStream();
		ByteArrayIOStream byteArrayIOStream = new ByteArrayIOStream(is);
		ByteArrayIOInputStream inputStream = byteArrayIOStream.getInputStream();
		byteArrayIOStream.close();
		return inputStream;
	}

	private static final Log log = LogFactory.getLog(ByteArrayIOStream.class);
	int pos;

	/**
	 * 
	 * bytearrayinputstream that allows duplication without copying
	 * 
	 * @author rainer prosi
	 * @date Feb 29, 2012
	 */
	public static class ByteArrayIOInputStream extends ByteArrayInputStream
	{
		/**
		 * 
		 * @param pos
		 */
		public void seek(long pos)
		{
			if (pos < 0)
			{
				pos = count + pos;
			}
			if (pos < 0)
				pos = 0;
			if (pos > count)
				pos = count;
			this.pos = (int) pos;
		}

		/**
		 * 
		 * @return
		 */
		public long tell()
		{
			return pos;
		}

		/**
		 * 
		 * @param buf
		 * @param offset
		 * @param length
		 */
		public ByteArrayIOInputStream(byte[] buf, int offset, int length)
		{
			super(buf, offset, length);
		}

		/**
		 * @param buf
		 * @param count
		 * 
		 */
		protected ByteArrayIOInputStream(byte[] buf, int count)
		{
			super(buf, 0, count);
		}

		/**
		 * creates an input output stream class from any stream if is already is a buffered inputstream, no copy is made
		 * 
		 * @param is the inputstream to buffer
		 */
		protected ByteArrayIOInputStream(final InputStream is)
		{
			super(new byte[1]);
			ByteArrayIOStream bos = new ByteArrayIOStream(is);
			buf = bos.getBuf();
			count = bos.count;
			bos.close();
		}

		/**
		 * 
		 * get a new input stream that starts at pos
		 * 
		 * @return
		 */
		public ByteArrayIOInputStream getNewStream()
		{
			return new ByteArrayIOInputStream(buf, pos, count);
		}

		/**
		 * @see java.io.ByteArrayInputStream#toString()
		 */
		@Override
		public synchronized String toString()
		{
			return "ByteArrayIOInputStream:\n" + new String(buf, 0, count);
		}

		/**
		 * 
		 * @return
		 */
		public byte[] getBuf()
		{
			return buf;
		}

		/**
		 * 
		 * @return
		 */
		public long getCount()
		{
			return count;
		}

		public String asString(int maxLen)
		{
			if (maxLen <= 0 || maxLen > count)
				maxLen = count;
			return new String(buf, 0, maxLen);
		}
	}

	/**
	 * creates an empty input output stream class
	 */
	public ByteArrayIOStream()
	{
		super();
		pos = 0;
	}

	/**
	 * creates a sized input output stream class
	 * 
	 * @param i the size of the stream
	 */
	public ByteArrayIOStream(final int i)
	{
		super(i);
		pos = 0;
	}

	/**
	 * creates an input output stream class from any stream if is alraedy is a buffered inputstream, no copy is made
	 * 
	 * @param is the inputstream to buffer
	 */
	public ByteArrayIOStream(final InputStream is)
	{
		super(1000);
		setStream(is);
	}

	/**
	 * 
	 * @param is
	 */
	void setStream(final InputStream is)
	{
		if (is == null)
		{
			return;
		}
		if (is instanceof ByteArrayIOInputStream && !(is instanceof ByteArrayIOFileInputStream))
		{
			ByteArrayIOInputStream bis = (ByteArrayIOInputStream) is;
			buf = bis.getBuf();
			count = (int) bis.getCount();
			pos = (int) bis.tell();
		}
		else
		{
			try
			{
				final int available = is.available();
				if (available > 1000)
				{
					buf = new byte[available + 1000];
				}
				IOUtils.copy(is, this);
			}
			catch (final IOException e)
			{
				log.error("error copying streams to buffer", e);
			}
		}
	}

	/**
	 * create a ByteArrayIOStream from a file
	 * 
	 * @param f the file
	 * @throws IOException
	 */
	public ByteArrayIOStream(final File f) throws IOException
	{
		super(10);
		if (f != null && f.length() > 10)
		{
			buf = new byte[(int) f.length() + 100];
		}
		final InputStream fis = FileUtil.getBufferedInputStream(f);
		setStream(fis);
	}

	/**
	 * creates a sized input output stream class
	 * 
	 * @param b the buffer to use (is NOT copied)
	 */
	public ByteArrayIOStream(final byte[] b)
	{
		super();
		buf = b;
		count = b.length;
	}

	/**
	 * gets an input stream based on the current byte contents - note this operates on the internal data
	 * 
	 * @return an input stream
	 */
	public ByteArrayIOInputStream getInputStream()
	{
		final ByteArrayIOInputStream is = new ByteArrayIOInputStream(buf, pos, count);
		return is;
	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 * get the internal buffer - be careful, this is THE buffer
	 * 
	 * @return the internal buffer
	 * 
	 */
	public byte[] getBuf()
	{
		return buf;
	}

	/**
	 * @see java.io.ByteArrayOutputStream#toString()
	 */
	@Override
	public synchronized String toString()
	{
		return "ByteArrayIOStream: " + count;
	}

	/**
	 * @see java.io.ByteArrayOutputStream#close()
	 */
	@Override
	public void close()
	{
		try
		{
			super.close();
		}
		catch (IOException e)
		{
			// NOP - super.close() is a nop anyhow
		}
	}
}
