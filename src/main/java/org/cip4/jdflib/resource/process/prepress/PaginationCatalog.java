/*
 * The CIP4 Software License, Version 1.0
 *
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
package org.cip4.jdflib.resource.process.prepress;

import java.util.Collection;
import java.util.HashMap;

import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.util.ContainerUtil;

public class PaginationCatalog
{
	class CatalogEntry
	{
		CatalogEntry(final int rows, final int[] frontPages)
		{
			super();
			this.frontPages = new int[frontPages.length / rows][rows];
			for (int i = 0; i < frontPages.length; i++)
			{
				this.frontPages[i / rows][i % rows] = frontPages[i] - 1;
			}
		}

		final int[][] frontPages;

		JDFIntegerList getFrontPages()
		{
			final JDFIntegerList il = new JDFIntegerList();
			for (final int[] row : frontPages)
			{
				for (final int p : row)
				{
					il.add(p);
				}
			}
			return il;
		}

		JDFIntegerList getBackPages()
		{
			final JDFIntegerList il = new JDFIntegerList();
			for (final int[] row : frontPages)
			{
				for (final int p : row)
				{
					if (p % 2 == 0)
						il.add(p + 1);
					else
						il.add(p - 1);
				}
			}
			return il;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return "CatalogEntry [" + getFrontPages() + "]";
		}
	}

	HashMap<String, CatalogEntry> theMap;

	public PaginationCatalog()
	{
		super();
		theMap = fill();
	}

	private void putMap(final String fc, final int rows, final int[] pages)
	{
		theMap.put(fc.toLowerCase(), new CatalogEntry(rows, pages));
	}

	private HashMap<String, CatalogEntry> fill()
	{
		theMap = new HashMap<>();
		putMap("F2-1", 1, new int[] { 1 });

		putMap("F4-1", 1, new int[] { 2, 3 });
		putMap("F4-2", 1, new int[] { 4, 1 });

		putMap("F6-1", 1, new int[] { 5, 4, 1 });
		putMap("F6-2", 1, new int[] { 1, 4, 5 });
		putMap("F6-4", 1, new int[] { 3, 2, 5 });
		putMap("F6-5", 1, new int[] { 2, 5, 4 });
		putMap("F6-8", 1, new int[] { 6, 3, 2 });

		putMap("F8-1", 1, new int[] { 6, 3, 2, 7 });
		putMap("F8-2", 1, new int[] { 2, 7, 6, 3 });
		putMap("F8-3", 1, new int[] { 2, 3, 6, 7 });
		putMap("F8-4", 1, new int[] { 3, 2, 7, 6 });
		putMap("F8-5", 1, new int[] { 4, 5, 2, 7 });
		putMap("F8-6", 1, new int[] { 2, 7, 4, 5 });
		putMap("F8-7", 2, new int[] { 3, 2, 6, 7 });

		putMap("F10-1", 1, new int[] { 9, 8, 5, 4, 1 });
		putMap("F10-2", 1, new int[] { 2, 9, 4, 7, 6 });
		putMap("F10-3", 1, new int[] { 2, 9, 8, 3, 6 });

		putMap("F12-1", 1, new int[] { 2, 11, 10, 3, 6, 7 });
		putMap("F12-2", 1, new int[] { 10, 3, 2, 11, 8, 5 });
		putMap("F12-3", 1, new int[] { 10, 7, 2, 3, 6, 11 });
		putMap("F12-4", 1, new int[] { 2, 11, 6, 7, 10, 3 });
		putMap("F12-5", 1, new int[] { 7, 2, 11, 10, 3, 6 });
		putMap("F12-6", 1, new int[] { 2, 3, 6, 7, 10, 11 });
		putMap("F12-7", 2, new int[] { 2, 3, 6, 11, 10, 7 });
		putMap("F12-8", 2, new int[] { 1, 4, 5, 12, 9, 8 });
		putMap("F12-9", 2, new int[] { 4, 5, 2, 9, 8, 11 });
		putMap("F12-10", 2, new int[] { 5, 2, 3, 8, 11, 10 });
		putMap("F12-11", 2, new int[] { 7, 6, 3, 10, 11, 2 });
		putMap("F12-12", 3, new int[] { 3, 2, 10, 11, 7, 6 });
		putMap("F12-13", 3, new int[] { 6, 7, 3, 2, 10, 11 });
		putMap("F12-14", 3, new int[] { 10, 11, 7, 6, 2, 3 });

		putMap("F14-1", 1, new int[] { 13, 12, 9, 8, 5, 4, 1 });

		putMap("F16-1", 1, new int[] { 10, 7, 2, 15, 14, 3, 6, 11 });
		putMap("F16-2", 1, new int[] { 2, 15, 10, 7, 6, 11, 14, 3 });
		putMap("F16-3", 1, new int[] { 6, 11, 14, 3, 2, 15, 10, 7 });
		putMap("F16-4", 1, new int[] { 14, 3, 6, 11, 10, 7, 2, 15 });
		putMap("F16-5", 1, new int[] { 16, 13, 12, 9, 8, 5, 4, 1 });
		putMap("F16-6", 2, new int[] { 6, 11, 10, 7, 3, 14, 15, 2 });
		putMap("F16-7", 2, new int[] { 14, 3, 2, 15, 11, 6, 7, 10 });
		putMap("F16-8", 2, new int[] { 10, 7, 6, 11, 16, 2, 3, 14 });
		putMap("F16-9", 2, new int[] { 7, 2, 3, 6, 10, 15, 14, 11 });
		putMap("F16-10", 2, new int[] { 3, 6, 7, 2, 14, 11, 10, 15 });
		putMap("F16-11", 2, new int[] { 7, 6, 3, 2, 10, 11, 14, 15 });
		putMap("F16-12", 2, new int[] { 5, 4, 7, 2, 12, 13, 10, 15 });
		putMap("F16-13", 4, new int[] { 3, 2, 14, 15, 11, 10, 6, 7 });

		putMap("F18-1", 1, new int[] { 17, 16, 13, 12, 9, 8, 5, 4, 1 });
		putMap("F18-2", 1, new int[] { 2, 11, 14, 17, 8, 5, 4, 9, 16 });
		putMap("F18-3", 1, new int[] { 2, 17, 8, 9, 16, 3, 6, 13, 12 });
		putMap("F18-4", 1, new int[] { 17, 8, 5, 4, 9, 16, 13, 12, 1 });
		putMap("F18-5", 3, new int[] { 17, 16, 13, 8, 9, 12, 5, 4, 1 });
		putMap("F18-6", 3, new int[] { 2, 3, 6, 17, 16, 13, 8, 9, 12 });
		putMap("F18-7", 3, new int[] { 15, 14, 17, 10, 11, 8, 3, 2, 5 });
		putMap("F18-8", 3, new int[] { 4, 5, 2, 15, 14, 17, 10, 11, 8 });
		putMap("F18-9", 3, new int[] { 13, 16, 17, 12, 9, 8, 1, 4, 5 });

		putMap("F20-1", 2, new int[] { 9, 2, 3, 8, 5, 12, 19, 18, 13, 16 });
		putMap("F20-2", 2, new int[] { 2, 3, 6, 7, 10, 19, 18, 15, 14, 11 });

		putMap("F24-1", 2, new int[] { 14, 11, 10, 15, 18, 7, 23, 2, 3, 22, 19, 6 });
		putMap("F24-2", 2, new int[] { 16, 9, 8, 17, 14, 11, 21, 4, 5, 20, 23, 2 });
		putMap("F24-3", 2, new int[] { 11, 2, 3, 10, 7, 6, 14, 23, 22, 15, 18, 19 });
		putMap("F24-4", 2, new int[] { 5, 8, 9, 4, 1, 12, 20, 17, 16, 21, 24, 13 });
		putMap("F24-5", 2, new int[] { 3, 10, 11, 2, 5, 8, 22, 15, 14, 23, 20, 17 });
		putMap("F24-6", 2, new int[] { 11, 10, 7, 6, 3, 2, 14, 15, 18, 19, 22, 23 });
		putMap("F24-7", 2, new int[] { 6, 19, 18, 7, 10, 15, 3, 22, 23, 2, 11, 14 });
		putMap("F24-8", 4, new int[] { 2, 3, 6, 23, 22, 19, 14, 15, 18, 11, 10, 7 });
		putMap("F24-9", 4, new int[] { 1, 4, 5, 24, 21, 20, 13, 16, 17, 12, 9, 8 });
		putMap("F24-10", 4, new int[] { 4, 5, 2, 21, 20, 23, 16, 17, 14, 9, 8, 11 });
		putMap("F24-11", 3, new int[] { 10, 15, 14, 11, 3, 22, 23, 2, 6, 19, 18, 7 });

		putMap("F28-1", 2, new int[] { 2, 3, 6, 7, 10, 11, 14, 27, 26, 23, 22, 19, 18, 15 });

		putMap("F32-1", 1, new int[] { 10, 23, 26, 7, 2, 31, 18, 15, 14, 19, 30, 3, 6, 27, 22, 11 });
		putMap("F32-2", 2, new int[] { 10, 23, 18, 15, 14, 19, 22, 11, 7, 26, 31, 2, 3, 30, 27, 6 });
		putMap("F32-3", 2, new int[] { 26, 7, 2, 31, 30, 3, 6, 27, 23, 10, 15, 18, 19, 14, 11, 22 });
		putMap("F32-4", 4, new int[] { 27, 22, 23, 26, 6, 11, 10, 7, 3, 14, 15, 2, 30, 19, 18, 31 });
		putMap("F32-5", 4, new int[] { 3, 14, 15, 2, 30, 19, 18, 31, 27, 22, 23, 26, 6, 11, 10, 7 });
		putMap("F32-6", 4, new int[] { 11, 6, 7, 10, 22, 27, 26, 23, 19, 30, 31, 18, 14, 3, 2, 15 });
		putMap("F32-7", 4, new int[] { 7, 6, 3, 2, 26, 27, 30, 31, 23, 22, 19, 18, 10, 11, 14, 15 });
		putMap("F32-8", 4, new int[] { 7, 2, 3, 6, 26, 31, 30, 27, 23, 18, 19, 22, 10, 15, 14, 11 });
		putMap("F32-9", 4, new int[] { 14, 19, 18, 15, 3, 30, 31, 2, 6, 27, 26, 7, 11, 22, 23, 10 });

		// TODO continue
		return theMap;

	}

	static final PaginationCatalog theCatalog = new PaginationCatalog();

	static public PaginationCatalog instance()
	{
		return theCatalog;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PaginationCatalog [" + (theMap != null ? "theMap=" + theMap : "") + "]";
	}

	/**
	 *
	 * @param fc
	 * @return
	 */
	public JDFIntegerList getFrontPages(final String fc)
	{
		final CatalogEntry catalogEntry = getEntry(fc);
		return catalogEntry == null ? null : catalogEntry.getFrontPages();
	}

	/**
	 *
	 * @param fc
	 * @return
	 */
	public JDFIntegerList getBackPages(final String fc)
	{
		final CatalogEntry catalogEntry = getEntry(fc);
		return catalogEntry == null ? null : catalogEntry.getBackPages();
	}

	/**
	 *
	 * @return
	 */
	public Collection<String> getKeys()
	{
		return ContainerUtil.getKeyArray(theMap);
	}

	public JDFXYPair getNUp(final String fc)
	{
		final CatalogEntry catalogEntry = getEntry(fc);
		return catalogEntry == null ? null : new JDFXYPair(catalogEntry.frontPages.length, catalogEntry.frontPages[0].length);
	}

	CatalogEntry getEntry(final String fc)
	{
		if (fc == null)
			return null;
		return theMap.get(fc.toLowerCase());
	}
}
