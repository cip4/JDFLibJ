/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
/**
 *
 */
package org.cip4.jdflib.util.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.cip4.jdflib.util.ThreadUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class FileSorter
{
	private final File[] list;

	/**
	 *
	 * @param dir
	 */
	public FileSorter(final File dir)
	{
		this(dir == null ? null : dir.listFiles());
	}

	/**
	 *
	 * @param list
	 */
	public FileSorter(final File[] list)
	{
		this.list = list;
	}

	/**
	 * sort this by modification date
	 *
	 * @param bNewFirst , if true newest entries are first, else oldest entries
	 * @return sort
	 */
	public File[] sortLastModified(final boolean bNewFirst)
	{
		for (int i = 1; i < 7; i++)
		{
			try
			{
				if (list != null)
					Arrays.sort(list, new LastModifiedComparator(bNewFirst));
				return list;
			}
			catch (final Exception x)
			{
				ThreadUtil.sleep(123 * i);
			}
		}
		return list;
	}

	/**
	 * sort this by modification date
	 *
	 * @param bNewFirst , if true newest entries are first, else oldest entries
	 * @return sort
	 */
	public File[] sortLastModified(final boolean bNewFirst, final long minAge)
	{
		if (list != null)
		{
			final long t0 = System.currentTimeMillis() - minAge;
			final ArrayList<File> al = new ArrayList<>();
			for (final File f : list)
			{
				if (f.lastModified() < t0)
				{
					al.add(f);
				}
			}
			Collections.sort(al, new LastModifiedComparator(bNewFirst));
			final File[] ret = new File[al.size()];
			int n = 0;
			for (final File f : al)
			{
				ret[n++] = f;
			}
			return ret;
		}
		return null;
	}

	/**
	 * get the number of entries in this FileSorter
	 *
	 * @return
	 */
	public int size()
	{
		return list == null ? 0 : list.length;
	}

	protected class LastModifiedComparator implements Comparator<File>
	{
		private final int iNewFirst;

		/**
		 * @param newFirst
		 */
		public LastModifiedComparator(final boolean newFirst)
		{
			this.iNewFirst = newFirst ? 1 : -1;
		}

		@Override
		public int compare(final File o1, final File o2)
		{
			final int n;
			if (o1.lastModified() > o2.lastModified())
			{
				n = -1;
			}
			else if (o1.lastModified() < o2.lastModified())
			{
				n = +1;
			}
			else
			{
				n = 0;
			}
			return n * iNewFirst;
		}
	}
}
