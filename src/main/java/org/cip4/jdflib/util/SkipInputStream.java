/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of 
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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * stream class that allows allows skipping until a certain tag is found
 * 
 * @author prosirai
 * 
 */
public class SkipInputStream extends BufferedInputStream
{
	long deltaPos;
	private final int searchSize;
	private final long maxPreread;
	private final boolean ignoreCase;
	private final String searchTag;
	private boolean found = false;
	int myMark = 0;

	/**
	 * @param searchTag
	 * @param stream2
	 * @param ignorecase
	 */
	public SkipInputStream(final String searchTag, final InputStream stream2, final boolean ignorecase)
	{
		this(searchTag, stream2, ignorecase, -1);
	}

	/**
	 * @param searchTag
	 * @param stream2
	 * @param ignorecase
	 * @param maxPreRead
	 */
	public SkipInputStream(final String searchTag, final InputStream stream2, final boolean ignorecase, final long maxPreRead)
	{
		super(stream2);
		this.searchTag = searchTag;
		this.ignoreCase = ignorecase;
		this.maxPreread = maxPreRead;

		searchSize = searchTag == null ? 0 : searchTag.length();
		if (searchSize == 0)
		{
			found = true;
			return;
		}

		mark(searchSize + 10);
		try
		{
			readToTag();
		}
		catch (final IOException x)
		{
			// nop
		}
	}

	/**
	 * @param maxPreRead 
	 * @throws IOException
	 * 
	 */
	private boolean readToTag() throws IOException
	{
		if (StringUtil.getNonEmpty(searchTag) == null)
		{
			return false;
		}
		final byte[] bytes = searchTag.getBytes();
		final byte[] lowerBytes = searchTag.toLowerCase().getBytes();
		int tagPos = 0;
		int nSkipped = 0;
		while (nSkipped++ != maxPreread)
		{
			if (tagPos == 0)
			{
				super.mark(searchSize + 10);
			}
			final int c = super.read();
			if (c == -1)
			{
				break;
			}
			if (c == bytes[tagPos] || ignoreCase && Character.toLowerCase(c) == lowerBytes[tagPos])
			{
				tagPos++;
				if (tagPos >= searchSize) // heureka
				{
					super.reset();
					deltaPos = 0;
					found = true;
					return found;
				}
			}
			else
			{
				tagPos = 0;
			}
		}
		return found;
	}

	/**
	 * same as read, returns -1 if not found
	 * 
	 * @see java.io.BufferedInputStream#read()
	 */
	@Override
	public synchronized int read() throws IOException
	{
		deltaPos++;
		return found ? super.read() : -1;
	}

	/**
	 * read until we find the next occurrence of searchtag
	 * @return true if we found a new search tag and are at a new position
	 */
	public boolean readToNextTag()
	{
		if (StringUtil.getNonEmpty(searchTag) == null)
		{
			return false;
		}
		try
		{
			for (long i = deltaPos; i < searchTag.length(); i++)
			{
				if (super.read() == -1)
				{
					return false;
				}
			}
			found = false;
			readToTag();
		}
		catch (IOException e)
		{
			return false;
		}
		return found;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SkipInputStream " + found + " " + searchSize + " " + super.toString();
	}

	@Override
	public synchronized void reset() throws IOException
	{
		markpos = myMark;
		super.reset();
	}

	@Override
	public synchronized void mark(int readlimit)
	{
		marklimit = readlimit;
		myMark = pos;
	}

}
