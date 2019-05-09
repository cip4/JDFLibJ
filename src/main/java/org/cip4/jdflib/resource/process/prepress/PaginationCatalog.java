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

import java.util.HashMap;

import org.cip4.jdflib.datatypes.JDFIntegerList;

public class PaginationCatalog
{
	class CatalogEntry
	{
		public CatalogEntry(final int rows, final int[] frontPages)
		{
			super();
			this.frontPages = new int[frontPages.length / rows][rows];
			for (int i = 0; i < frontPages.length; i++)
			{
				this.frontPages[i / rows][i % rows] = frontPages[i];
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

	private HashMap<String, CatalogEntry> fill()
	{
		theMap = new HashMap<>();
		theMap.put("F2-1", new CatalogEntry(1, new int[] { 0 }));

		theMap.put("F4-1", new CatalogEntry(1, new int[] { 1, 2 }));
		theMap.put("F4-2", new CatalogEntry(1, new int[] { 3, 0 }));

		theMap.put("F6-1", new CatalogEntry(1, new int[] { 4, 3, 0 }));
		theMap.put("F6-2", new CatalogEntry(1, new int[] { 0, 3, 4 }));
		theMap.put("F6-4", new CatalogEntry(1, new int[] { 2, 1, 4 }));
		theMap.put("F6-5", new CatalogEntry(1, new int[] { 1, 4, 3 }));
		theMap.put("F6-8", new CatalogEntry(1, new int[] { 5, 2, 1 }));

		theMap.put("F8-1", new CatalogEntry(1, new int[] { 5, 2, 1, 6 }));
		theMap.put("F8-2", new CatalogEntry(1, new int[] { 1, 6, 5, 2 }));
		theMap.put("F8-3", new CatalogEntry(1, new int[] { 1, 6, 5, 2 }));
		theMap.put("F8-4", new CatalogEntry(1, new int[] { 1, 2, 5, 6 }));
		theMap.put("F8-5", new CatalogEntry(1, new int[] { 2, 1, 6, 5 }));
		theMap.put("F8-6", new CatalogEntry(1, new int[] { 1, 6, 3, 4 }));
		theMap.put("F8-7", new CatalogEntry(2, new int[] { 2, 1, 5, 6 }));

		theMap.put("F10-1", new CatalogEntry(1, new int[] { 8, 7, 4, 3, 0 }));
		theMap.put("F10-2", new CatalogEntry(1, new int[] { 1, 8, 3, 6, 5 }));
		theMap.put("F10-3", new CatalogEntry(1, new int[] { 1, 8, 7, 2, 5 }));

		theMap.put("F12-1", new CatalogEntry(1, new int[] { 1, 10, 9, 2, 5, 6 }));
		theMap.put("F12-2", new CatalogEntry(1, new int[] { 9, 2, 1, 10, 7, 4 }));
		theMap.put("F12-3", new CatalogEntry(1, new int[] { 9, 6, 1, 2, 5, 10 }));
		theMap.put("F12-4", new CatalogEntry(1, new int[] { 1, 10, 5, 6, 9, 2 }));
		theMap.put("F12-5", new CatalogEntry(1, new int[] { 6, 1, 10, 9, 2, 5 }));
		theMap.put("F12-6", new CatalogEntry(1, new int[] { 1, 2, 5, 6, 9, 10 }));
		theMap.put("F12-7", new CatalogEntry(2, new int[] { 1, 2, 5, 10, 9, 6 }));
		theMap.put("F12-8", new CatalogEntry(2, new int[] { 0, 3, 4, 11, 8, 7 }));
		theMap.put("F12-9", new CatalogEntry(2, new int[] { 3, 4, 1, 8, 7, 10 }));
		theMap.put("F12-10", new CatalogEntry(2, new int[] { 4, 1, 2, 7, 10, 9 }));
		theMap.put("F12-11", new CatalogEntry(2, new int[] { 6, 5, 2, 9, 10, 1 }));
		theMap.put("F12-12", new CatalogEntry(3, new int[] { 2, 1, 9, 10, 6, 5 }));
		theMap.put("F12-13", new CatalogEntry(3, new int[] { 5, 6, 2, 1, 9, 10 }));
		theMap.put("F12-14", new CatalogEntry(3, new int[] { 9, 10, 6, 5, 1, 2 }));

		theMap.put("F14-1", new CatalogEntry(1, new int[] { 12, 11, 8, 7, 4, 3, 0 }));

		theMap.put("F16-1", new CatalogEntry(1, new int[] { 9, 6, 1, 14, 13, 2, 5, 10 }));
		theMap.put("F16-2", new CatalogEntry(1, new int[] { 1, 14, 9, 6, 5, 10, 13, 2 }));
		theMap.put("F16-3", new CatalogEntry(1, new int[] { 5, 10, 13, 2, 1, 14, 9, 6 }));
		theMap.put("F16-4", new CatalogEntry(1, new int[] { 13, 2, 5, 10, 9, 6, 1, 14 }));
		theMap.put("F16-5", new CatalogEntry(1, new int[] { 15, 12, 11, 8, 7, 4, 3, 0 }));
		theMap.put("F16-6", new CatalogEntry(2, new int[] { 5, 10, 9, 6, 2, 13, 14, 1 }));
		theMap.put("F16-7", new CatalogEntry(2, new int[] { 13, 2, 1, 14, 10, 5, 6, 9 }));
		theMap.put("F16-8", new CatalogEntry(2, new int[] { 9, 6, 5, 10, 15, 1, 2, 13 }));
		theMap.put("F16-9", new CatalogEntry(2, new int[] { 6, 1, 2, 5, 9, 14, 13, 10 }));
		theMap.put("F16-10", new CatalogEntry(2, new int[] { 2, 5, 6, 1, 13, 10, 9, 14 }));
		theMap.put("F16-11", new CatalogEntry(2, new int[] { 6, 5, 2, 1, 9, 10, 13, 14 }));
		theMap.put("F16-12", new CatalogEntry(2, new int[] { 4, 3, 6, 1, 11, 12, 9, 14 }));
		theMap.put("F16-13", new CatalogEntry(4, new int[] { 2, 1, 13, 14, 10, 9, 5, 6 }));

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
}
