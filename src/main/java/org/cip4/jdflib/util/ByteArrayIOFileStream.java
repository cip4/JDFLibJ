/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of 
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import org.apache.commons.io.IOUtils;

/**
 * Shared input / outputStream class write once, read many...
 * 
 * @author rainer prosi
 * 
 */
public class ByteArrayIOFileStream extends ByteArrayIOStream
{

	private final long maxLength;
	private File file;
	private RandomAccessFile os;
	private boolean isTmpFile;

	/**
	 * 
	 * get a completely buffered and resizable input stream
	 * @param is
	 * @return
	 */
	public static ByteArrayIOInputStream getBufferedInputStream(InputStream is, long maxLen)
	{
		if (is == null)
			return null;
		if (is instanceof ByteArrayIOInputStream)
			return ((ByteArrayIOInputStream) is).getNewStream();
		ByteArrayIOStream byteArrayIOStream = new ByteArrayIOFileStream(is, maxLen);
		ByteArrayIOInputStream inputStream = byteArrayIOStream.getInputStream();
		return inputStream;
	}

	/**
	 * 
	 * bytearrayinputstream that allows duplication without copying
	 * @author rainer prosi
	 *  
	 */
	public static class ByteArrayIOFileInputStream extends ByteArrayIOInputStream
	{
		private final ByteArrayIOFileStream ios;
		private long pos;
		private long mark;

		/**
		 * @param buf
		 * @param count
		 *  
		 */
		ByteArrayIOFileInputStream(ByteArrayIOFileStream ios)
		{
			super(new byte[1], 0);
			this.ios = ios;
			mark = 0;
			try
			{
				pos = ios.os.getFilePointer();
			}
			catch (IOException e)
			{
				pos = 0;
			}
		}

		/**
		 * 
		 * get a new input stream that starts at current pos
		 * @return
		 */
		@Override
		public ByteArrayIOInputStream getNewStream()
		{
			ByteArrayIOFileInputStream byteArrayIOFileInputStream = new ByteArrayIOFileInputStream(ios);
			byteArrayIOFileInputStream.pos = pos;

			return byteArrayIOFileInputStream;
		}

		/**
		 * @see java.io.ByteArrayInputStream#toString()
		 */
		@Override
		public synchronized String toString()
		{
			return "ByteArrayIOFileInputStream: " + ios;
		}

		/**
		 * 
		 * @return
		 */
		@Override
		public long getCount()
		{
			try
			{
				return ios.os.length();
			}
			catch (IOException e)
			{
				return 0;
			}
		}

		@Override
		public void seek(long pos)
		{
			if (pos < 0)
				pos += getCount();

			try
			{
				if (ios.os.getFilePointer() != pos)
				{
					ios.os.seek(pos);
					this.pos = pos;
				}
			}
			catch (IOException e)
			{
			}
		}

		/**
		 * 
		 * @see org.cip4.jdflib.util.ByteArrayIOStream.ByteArrayIOInputStream#tell()
		 */
		@Override
		public long tell()
		{
			return pos;
		}

		@Override
		public byte[] getBuf()
		{
			return null;
		}

		@Override
		public synchronized int read()
		{
			try
			{
				ios.os.seek(pos);
				int read = ios.os.read();
				if (read >= 0)
					pos++;
				return read;
			}
			catch (IOException e)
			{
				return -1;
			}
		}

		@Override
		public synchronized int read(byte[] b, int off, int len)
		{
			try
			{
				seek(pos);
				int read = ios.os.read(b, off, len);
				if (read > 0)
					pos += read;
				return read;
			}
			catch (IOException e)
			{
				return 0;
			}
		}

		@Override
		public synchronized long skip(long n)
		{
			try
			{
				pos += n;
				ios.os.seek(pos);
				return pos;
			}
			catch (IOException e)
			{
				return 0;
			}
		}

		@Override
		public synchronized int available()
		{
			return (int) (getCount() - pos);
		}

		@Override
		public boolean markSupported()
		{
			return true;
		}

		@Override
		public void mark(int readAheadLimit)
		{
			mark = pos;
		}

		/**
		 * 
		 */
		@Override
		public synchronized void reset()
		{
			seek(mark);
		}

		/**
		 * 
		 * @throws IOException
		 */
		@Override
		public void close() throws IOException
		{
			// nop
		}

		/**
		 * close the underlying file
		 */
		public void closeAll()
		{
			ios.close();
		}
	}

	/**
	 * creates an empty input output stream class
	 * @param maxLength the maximum length in memory
	 */
	public ByteArrayIOFileStream(long maxLength)
	{
		super();
		this.maxLength = maxLength;
		file = null;
		isTmpFile = false;
		os = null;
	}

	/**
	 * creates an input output stream class from any stream
	 * if is alraedy is a buffered inputstream, no copy is made
	 * 
	 * @param is the inputstream to buffer
	 * @param maxLength the maximum length in memory
	 */
	public ByteArrayIOFileStream(final InputStream is, long maxLength)
	{
		this(maxLength);
		setStream(is);

	}

	/**
	 * create a ByteArrayIOStream from a file
	 * @param f the file
	 * @param maxLength the maximum length in memory
	 * @param readOnly
	 * 
	 * @throws IOException
	 */
	public ByteArrayIOFileStream(final File f, long maxLength, boolean readOnly)
	{
		this(maxLength);
		if (f == null)
		{
			return;
		}
		file = f;
		if (f.length() > maxLength)
		{
			try
			{
				os = new RandomAccessFile(f, readOnly ? "r" : "rw");
			}
			catch (FileNotFoundException e)
			{
				log.error("cannot open file " + f, e);
			}
		}
		else
		{
			final InputStream fis = FileUtil.getBufferedInputStream(f);
			setStream(fis);
		}
	}

	/**
	 * gets an input stream based on the current byte contents - note this operates on the internal data
	 * 
	 * @return an input stream
	 */
	@Override
	public ByteArrayIOInputStream getInputStream()
	{
		if (os != null)
		{
			ByteArrayIOFileInputStream byteArrayIOFileInputStream = new ByteArrayIOFileInputStream(this);
			byteArrayIOFileInputStream.seek(0);
			return byteArrayIOFileInputStream;
		}
		return super.getInputStream();
	}

	/**
	 * @see java.io.ByteArrayOutputStream#toString()
	 */
	@Override
	public synchronized String toString()
	{
		return "ByteArrayIOFileStream: size= " + count + " maxLength=" + maxLength;
	}

	/**
	* Writes the specified byte to this byte array output stream.
	*
	* @param   b   the byte to be written.
	*/
	@Override
	public synchronized void write(int b)
	{
		ensureStream(count + 1);
		if (os != null)
		{
			try
			{
				os.write(b);
			}
			catch (IOException e)
			{
			}
		}
		else
		{
			super.write(b);
		}
	}

	/**
	 * 
	 * @param newSize
	 */
	private void ensureStream(long newSize)
	{
		if (newSize > maxLength && os == null)
		{
			if (file == null)
			{
				try
				{
					isTmpFile = true;
					file = File.createTempFile("ByteArray", null);
				}
				catch (IOException e)
				{
					log.error("Cannot create temp file ", e);
				}
			}
			try
			{
				os = new RandomAccessFile(file, "rw");
				os.write(buf, pos, count);
				buf = null;
				count = 0;
			}
			catch (Exception e)
			{
				log.error("cannot create file " + file);
			}
		}
	}

	/**
	 * Writes <code>len</code> bytes from the specified byte array
	 * starting at offset <code>off</code> to this byte array output stream.
	 *
	 * @param   b     the data.
	 * @param   off   the start offset in the data.
	 * @param   len   the number of bytes to write.
	 */
	@Override
	public synchronized void write(byte b[], int off, int len)
	{
		ensureStream(count + len);
		if (os != null)
		{
			try
			{
				os.write(b, off, len);
			}
			catch (IOException e)
			{
			}
		}
		else
		{
			super.write(b, off, len);
		}
	}

	/**
	 * @see java.io.ByteArrayOutputStream#close()
	 */
	@Override
	public void close()
	{
		try
		{
			if (os != null)
			{
				os.close();
				os = null;
			}
			if (isTmpFile && file != null)
			{
				file.delete();
				file = null;
				isTmpFile = false;
			}
			super.close();
		}
		catch (IOException e)
		{
			// NOP -  super.close() is a nop anyhow
		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	@Override
	protected void finalize() throws Throwable
	{
		close();
		super.finalize();
	}

	/**
	 * 
	 * @see org.cip4.jdflib.util.ByteArrayIOStream#setStream(java.io.InputStream)
	 */
	@Override
	void setStream(InputStream is)
	{
		if (is instanceof ByteArrayIOFileInputStream)
		{
			ByteArrayIOFileInputStream bais = (ByteArrayIOFileInputStream) is;
			file = bais.ios.file;
			isTmpFile = false;
			os = bais.ios.os;
		}
		else if (is != null)
		{
			int avail = 0;
			try
			{
				avail = is.available();
				if (avail > maxLength)
				{
					ensureStream(avail);
					IOUtils.copy(is, this);
				}
				else
				{
					super.setStream(is);
				}
			}
			catch (IOException e)
			{
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public synchronized int size()
	{
		if (os != null)
		{
			try
			{
				return (int) os.length();
			}
			catch (IOException e)
			{
				//
			}
		}
		return super.size();
	}
}