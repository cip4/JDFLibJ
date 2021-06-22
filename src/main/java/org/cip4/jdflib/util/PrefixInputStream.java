/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2021 The International Cooperation for the Integration of
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
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * stream class that allows allows prefixing of a stream without requiring a copy
 *
 * @author Rainer Prosi
 *
 */
public class PrefixInputStream extends FilterInputStream
{

	private final InputStream streamPre;
	private boolean bDone;
	private boolean markDone;

	/**
	 * @param stream1
	 * @param stream2
	 */
	public PrefixInputStream(final InputStream stream1, final InputStream stream2)
	{
		super(stream2);
		bDone = markDone = false;
		streamPre = stream1;
	}

	/**
	 * @param prefix
	 * @param stream2
	 */
	public PrefixInputStream(final String prefix, final InputStream stream2)
	{
		super(stream2);
		bDone = false;
		streamPre = new ByteArrayInputStream(prefix.getBytes());
	}

	/**
	 *
	 * @see java.io.FilterInputStream#read()
	 */
	@Override
	public int read() throws IOException
	{
		if (!bDone)
		{
			final int read = streamPre.read();
			if (read != -1)
				return read;
			bDone = true;
		}

		return super.read();
	}

	/**
	 *
	 * @see java.io.FilterInputStream#read(byte[], int, int)
	 */
	@Override
	public int read(final byte[] b, final int off, final int len) throws IOException
	{
		if (!bDone)
		{
			final int read = streamPre.read(b, off, len);
			if (read != -1)
				return read;
			bDone = true;
		}
		return super.read(b, off, len);
	}

	/**
	*
	 * @see java.io.FilterInputStream#read(byte[])
	 */
	@Override
	public int read(final byte[] b) throws IOException
	{
		if (!bDone)
		{
			final int read = streamPre.read(b);
			if (read != -1)
				return read;
			bDone = true;
		}
		return super.read(b);
	}

	/**
	 * @see java.io.FilterInputStream#mark(int)
	 */
	@Override
	public synchronized void mark(final int readlimit)
	{
		markDone = bDone;
		if (!bDone)
			streamPre.mark(readlimit);
		super.mark(readlimit);

	}

	/**
	 * @see java.io.FilterInputStream#reset()
	 */
	@Override
	public synchronized void reset() throws IOException
	{
		bDone = markDone;
		if (!bDone)
			streamPre.reset();
		super.reset();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PrefixInputStream [bDone=" + bDone + ", markDone=" + markDone + "]";
	}

}
