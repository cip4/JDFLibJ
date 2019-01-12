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
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
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
import java.util.HashSet;
import java.util.Set;

import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.w3c.dom.Element;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker that writes XPaths of all elements to a file
 */
public class XPathWalker extends BaseElementWalker
{
	/**
	 * the attribute names to set as [@att] rather than [n] if method=3
	 */
	final VString attNames = new VString("Name,ChannelType,ContactTypes,IDUsage", ",");

	/**
	 * the method to create xpaths
	 */
	public int method = 1;

	static class XPathBuilder
	{
		XPathBuilder(final KElement _e, final int countSiblings, final VString attName)
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
		String buildXPath(final String relativeTo)
		{
			String path = elem.getNodeName();
			final KElement parent = elem.getParentNode_KElement();

			boolean bAtt = false;
			if (methCountSiblings > 0 && attributeNames != null)
			{
				final int size = attributeNames.size();
				for (int i = 0; i < size; i++)
				{
					if (methCountSiblings == 3 && elem.hasAttribute_KElement(attributeNames.get(i), null, false))
					{
						path += "[@" + attributeNames.get(i) + "=\"" + elem.getAttribute(attributeNames.get(i)) + "\"]";
						bAtt = true;
						break;
					}
				}
			}
			if (!bAtt)
			{
				KElement e = (parent != null) ? parent.getElement(path, null, 0) : null;
				int i = 1;
				while (e != null)
				{
					if (e.equals(elem))
					{
						if (methCountSiblings > 0)
						{
							path += "[" + Integer.toString(i) + "]";
						}
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
			if (parent != null)
			{
				path = new XPathBuilder(parent, methCountSiblings, attributeNames).buildXPath(relativeTo) + path;
			}

			if (relativeTo != null)
			{
				if (path.startsWith(relativeTo))
				{
					path = "." + path.substring(relativeTo.length());
					if (path.startsWith(".["))
					{
						final int iB = path.indexOf("]");
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
	public int walkAll(final KElement e)
	{
		final int n = walkTree(e, null);
		writer.flush();
		writer.close();
		return n;
	}

	protected final PrintWriter writer;
	boolean bAttribute = false;
	boolean bElement = true;
	boolean bAttributeValue = false;
	boolean bDatatype = false;
	String separator = " = ";

	protected Set<String> pathsFound;

	/**
	 * if true, include attributes
	 *
	 * @param attribute
	 */
	public void setAttribute(final boolean attribute)
	{
		bAttribute = attribute;
	}

	/**
	 * if true, include attribute values
	 *
	 * @param attribute
	 */
	public void setAttributeValue(final boolean attribute)
	{
		bAttributeValue = attribute;
	}

	/**
	 * @param xpathOutput
	 * @throws FileNotFoundException
	 */
	public XPathWalker(final File xpathOutput) throws FileNotFoundException
	{
		this(new PrintWriter(xpathOutput));
	}

	/**
	 * @param w
	 */
	public XPathWalker(final PrintWriter w)
	{
		super(new BaseWalkerFactory());
		writer = w;
		pathsFound = null;
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
			final XPathBuilder xb = new XPathBuilder(e, method, attNames);
			final String s = xb.buildXPath(null);
			if (bElement && (pathsFound == null || !pathsFound.contains(s)))
			{
				writer.println(s);
				if (pathsFound != null)
				{
					pathsFound.add(s);
				}
			}
			if (bAttribute)
			{
				final VString vkeys = e.getAttributeVector_KElement();
				Collections.sort(vkeys);
				for (int i = 0; i < vkeys.size(); i++)
				{
					final String key = vkeys.get(i);
					final String path = s + "/@" + key;
					if ((pathsFound == null || !pathsFound.contains(path)))
					{
						writer.print(path);
						final String attribute = e.getAttribute_KElement(key);
						if (bAttributeValue)
							writer.print(separator + attribute);
						if (bDatatype && (e instanceof JDFElement))
						{
							writeDatatype((JDFElement) e, key);
						}
						writer.println();
						if (pathsFound != null)
							pathsFound.add(path);

					}
				}
			}
			return e;
		}

		/**
		 * @param e
		 * @param key
		 */
		private void writeDatatype(final JDFElement e, final String key)
		{
			EnumAttributeType type = e.getAtrType(key);
			writer.print(separator);
			if (type != null)
				writer.print(type.getName());
			else if (e.knownElements().contains(key))
			{
				final Element createdElement = e.getOwnerDocument_KElement().createElement(key);
				if (createdElement instanceof JDFElement)
				{
					type = ((JDFElement) createdElement).getAtrType(AttributeName.ACTUAL);
					if (type != null)
						writer.print(type.getName());
				}
			}
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

	/**
	 * @param method the method to set
	 */
	public void setMethod(final int method)
	{
		this.method = method;
	}

	/**
	 * @param unique the method to set
	 */
	public void setUnique(final boolean unique)
	{
		if (unique)
		{
			pathsFound = new HashSet<>();
		}
		else
		{
			pathsFound = null;
		}
	}

	/**
	 * @param datatype the bDatatype to set
	 */
	public void setDatatype(final boolean datatype)
	{
		bDatatype = datatype;
	}

	/**
	 * @param separator the separator to set
	 */
	public void setSeparator(final String separator)
	{
		this.separator = separator;
	}

	/**
	 * @param element the bElement to set
	 */
	public void setBElement(final boolean element)
	{
		bElement = element;
	}
}
