/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of
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

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * walker that writes XPaths of all elements to a file
 */
public class XPathWalker extends BaseElementWalker
{
	/**
	 *  the attribute names to set as [@att] rather than [n] if method=3
	 */
	public VString attNames = new VString("Name,ChannelType,ContactTypes,IDUsage", ",");

	/**
	 * the method to create xpaths
	 */
	public int method = 1;

	protected class XPathBuilder
	{
		public XPathBuilder(KElement _e, int countSiblings, VString attName)
		{
			super();
			this.elem = _e;
			methCountSiblings = countSiblings;
			attributeNames = attName;
		}

		KElement elem;
		int methCountSiblings;
		VString attributeNames;

		/**
		 * Gets the XPath full tree representation of 'this'
		 * 
		 * @param relativeTo relative path to which to create an xpath
		 * 
		 * @return String the XPath representation of 'this' e.g. <code>/root/parent/element</code><br>
		 *         <code>null</code> if parent of this is null (e.g. called on rootnode)
		 */
		public String buildXPath(String relativeTo)
		{
			String path = elem.getNodeName();
			KElement p = elem.getParentNode_KElement();

			boolean bAtt = false;
			if (methCountSiblings > 0 && attributeNames != null)
			{
				for (int i = 0; i < attributeNames.size(); i++)
				{
					if (methCountSiblings == 3 && elem.hasAttribute(attributeNames.get(i), null, false))
					{
						path += "[@" + attributeNames.get(i) + "=\"" + elem.getAttribute(attributeNames.get(i)) + "\"]";
						bAtt = true;
						break;
					}
				}
			}
			if (!bAtt)
			{
				KElement e = (p != null) ? p.getElement(path, null, 0) : null;
				int i = 1;
				while (e != null)
				{
					if (e.equals(elem))
					{
						path += "[" + Integer.toString(i) + "]";
						break;
					}
					do
					{
						e = e.getNextSiblingElement();
					}
					while (e != null && !e.fitsName(path, null));
					i++;
				}
			}

			path = "/" + path;
			if (p != null)
			{
				path = new XPathBuilder(p, methCountSiblings, attributeNames).buildXPath(relativeTo) + path;
			}

			if (relativeTo != null)
			{
				if (path.startsWith(relativeTo))
				{
					path = "." + path.substring(relativeTo.length());
					if (path.startsWith(".["))
					{
						int iB = path.indexOf("]");
						if (iB > 0)
						{
							path = "." + path.substring(iB + 1);
						}
					}
				}
			}
			return path;
		}
	}

	/**
	 * @param e
	 * @return the number of xpaths
	 */
	public int walkAll(KElement e)
	{
		int n = walkTree(e, null);
		writer.flush();
		writer.close();
		return n;
	}

	private File outTxt = null;
	protected final PrintWriter writer;
	boolean bAttribute = false;
	boolean bAttributeValue = false;

	/**
	 * if true, include attributes
	 * @param attribute
	 */
	public void setBAttribute(boolean attribute)
	{
		bAttribute = attribute;
	}

	/**
	 * if true, include attribute values
	 * @param attribute
	 */
	public void setBAttributeValue(boolean attribute)
	{
		bAttributeValue = attribute;
	}

	/**
	 * @param xpathOutput
	 * @throws FileNotFoundException 
	 */
	public XPathWalker(File xpathOutput) throws FileNotFoundException
	{
		super(new BaseWalkerFactory());
		outTxt = xpathOutput;
		writer = new PrintWriter(outTxt);
	}

	/**
	 * @param w
	 */
	public XPathWalker(PrintWriter w)
	{
		super(new BaseWalkerFactory());
		outTxt = null;
		writer = w;
	}

	@Override
	protected BaseWalkerFactory getFactory()
	{
		return (BaseWalkerFactory) theFactory;
	}

	/**
	 * the link and ref walker
	 * 
	 * @author prosirai
	 * 
	 */
	public class WalkAll extends BaseWalker
	{
		/**
		 * fills this into the factory
		 */
		public WalkAll()
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
			XPathBuilder xb = new XPathBuilder(e, method, attNames);
			String s = xb.buildXPath(null);
			writer.println(s);
			if (bAttribute)
			{
				VString vkeys = e.getAttributeVector_KElement();
				Collections.sort(vkeys);
				for (int i = 0; i < vkeys.size(); i++)
				{
					writer.print(s + "/@" + vkeys.get(i));
					if (bAttributeValue)
						writer.print(" = " + e.getAttribute_KElement(vkeys.get(i)));
					writer.println();
				}
			}
			return e;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return true;
		}
	}
}
