/*
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
/**
 * 
 */
package org.cip4.jdflib.elementwalker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.ContainerUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker that writes XPaths of all elements to a file
 */
public class SizeWalker extends BaseElementWalker
{
	/**
	 * the link and ref walker
	 * 
	 * @author prosirai
	 * 
	 */
	public class WalkElement extends BaseWalker
	{
		/**
		 * 
		 */
		public WalkElement()
		{
			super(getFactory());
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e
		 * @param trackElem
		 * @return
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final String nam = e.getNodeName();
			final Sizes siz = getSize(nam);
			final int locSize = e.toDisplayXML(0).length();

			siz.total += locSize;
			siz.local += locSize;
			final KElement parent = e.getParentNode_KElement();
			if (parent != null)
			{
				final Sizes ps = getSize(parent.getNodeName());
				ps.local -= locSize;
				if (siz == ps) // no double counting
					ps.total -= locSize;
			}

			siz.n++;
			final VString atts = e.getAttributeVector_KElement();
			if (atts != null)
			{
				for (final String att : atts)
				{
					final Sizes as = getSize("@" + att);
					as.n++;
					final String val = e.getAttribute(att);
					final int l = att.length() + val.length() + 4; // " "=
					as.total += l;
					as.local += val.length();
				}
			}

			return e;
		}

	}

	private class Sizes implements Comparable<Sizes>
	{
		/**
		 * @param name
		 */
		public Sizes(final String name)
		{
			super();
			total = 0;
			local = 0;
			n = 0;
			nam = name;
		}

		int total;
		int local;
		int n;
		String nam;

		/**
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(final Sizes o)
		{
			return total - o.total;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return nam + " N: " + n + " Total Size:" + total + " Local size:" + local + "\n";
		}
	}

	HashMap<String, Sizes> map = new HashMap<String, Sizes>();

	Sizes getSize(final String nam)
	{
		Sizes s = map.get(nam);
		if (s == null)
		{
			s = new Sizes(nam);
			map.put(nam, s);
		}
		return s;
	}

	/**
	 * @param e
	 * @return the number of xpaths
	 */
	public int walkAll(final KElement e)
	{
		final int n = walkTree(e, null);
		final Vector<Sizes> v = ContainerUtil.toValueVector(map);
		Collections.sort(v);
		Collections.reverse(v);
		writer.print(v);
		writer.flush();
		writer.close();
		return n;
	}

	private File outTxt = null;
	private final PrintWriter writer;

	/**
	 * @param xpathOutput
	 * @throws FileNotFoundException
	 */
	public SizeWalker(final File xpathOutput) throws FileNotFoundException
	{
		super(new BaseWalkerFactory());
		outTxt = xpathOutput;
		writer = new PrintWriter(outTxt);
	}

}
