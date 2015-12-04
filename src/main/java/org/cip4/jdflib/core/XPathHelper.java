/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.core;

import java.util.Iterator;

import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.Node;

/**
 * 
 * implementation class for all the KElement xpath stuff
 * @author rainer prosi
 * @date Dec 13, 2012
 */
class XPathHelper
{

	private final KElement theElement;

	/**
	 * @param kElement
	 */
	XPathHelper(KElement kElement)
	{
		theElement = kElement;
	}

	/**
	 * copy attribute values or text from an xpath in src to this
	 * 
	 * @param dstXPath the destination xpath in this element
	 * @param src the source element, if null; use this
	 * @param srcXPath the source xpath, if null same as dstXPath
	 * @return the copied value; may be null if no value was found in srcXPath
	 */
	String copyXPathValue(String dstXPath, KElement src, String srcXPath)
	{
		if (src == null)
		{
			src = theElement;
		}
		if (srcXPath == null)
		{
			srcXPath = dstXPath;
		}

		String s = src.getXPathAttribute(srcXPath, null);
		setXPathValue(dstXPath, s);
		return s;
	}

	/**
	 * gets an element as defined by XPath to value and creates it if it does not exist <br>
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported
	 * @param path XPath abbreviated syntax representation of the attribute, <code>parentElement/thisElement</code> <code>parentElement/thisElement[2]</code> <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]</code>
	 * @return KElement the specified element
	 */
	KElement getCreateXPathElement(final String path)
	{
		return new XPathElementCreator().getCreateXPathElement(path);
	}

	class XPathElementCreator
	{
		/**
		 * gets an element as defined by XPath to value and creates it if it does not exist <br>
		 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported
		 * @param path XPath abbreviated syntax representation of the attribute, <code>parentElement/thisElement</code> <code>parentElement/thisElement[2]</code> <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]</code>
		 * @return KElement the specified element
		 */
		KElement getCreateXPathElement(final String path)
		{
			KElement e = getXPathElement(path);
			if (e != null)
			{
				return e;
			}

			if (path.startsWith(JDFCoreConstants.SLASH))
			{
				return evaluateSlash(path);
			}
			else if (path.startsWith(JDFCoreConstants.DOT))
			{
				return evaluateDot(path);
			}
			else
			{
				return evaluateNormal(path);
			}
		}

		private KElement evaluateNormal(final String path)
		{
			int slash = path.indexOf("/");
			final int posB0 = path.indexOf("[");
			final int brack = posB0 < slash ? path.indexOf("]") : -1;
			final int endPos = findSlashBrack(slash, brack);
			if (endPos > 0)
			{
				String next = path.substring(0, endPos);
				KElement e = getXPathElement(next);
				if (e != null)
				{
					next = path.substring(endPos + 1);
					return new XPathHelper(e).getCreateXPathElement(next);
				}
			}

			int iSkip = 0;
			String newPath = path;
			String attName = null;
			String attVal = null;
			if (posB0 != -1 && (posB0 < slash || slash == -1))
			{
				final int posB1 = path.indexOf("]");
				final String siSkip = path.substring(posB0 + 1, posB1);
				newPath = path.substring(0, posB0) + path.substring(posB1 + 1);
				slash = newPath.indexOf(JDFCoreConstants.SLASH);
				if (!StringUtil.isInteger(siSkip))
				{
					iSkip = -1; // flag for snafu
					final VString v = StringUtil.tokenize(siSkip, "=", false);
					if (v.size() == 2)
					{
						attName = v.get(0);
						attVal = v.get(1);
						if (attName.length() > 1 && attVal.startsWith("\"") && attVal.endsWith("\""))
						{
							attVal = attVal.substring(1, attVal.length() - 1);
							iSkip = calcSkipFromAtt(iSkip, newPath, slash, attName);
						}
					}

					if (iSkip < 0)
					{
						throw new IllegalArgumentException("GetCreateXPath: illegal path:" + path);
					}
				}
				else
				{
					iSkip = StringUtil.parseInt(siSkip, 1);
					iSkip--;
				}
			}
			fillMissing(iSkip, newPath, slash);
			if (slash != -1)
			{
				KElement e = theElement.getCreateElement_KElement(newPath.substring(0, slash), null, iSkip);
				if (attName != null && !e.hasAttribute(attName))
				{
					e.setXPathAttribute(attName, attVal);
				}
				return new XPathHelper(e).getCreateXPathElement(newPath.substring(slash + 1));
			}
			final KElement newElem = theElement.getCreateElement_KElement(newPath, null, iSkip);
			if (attName != null)
			{
				new XPathHelper(newElem).setXPathAttribute(attName, attVal);
			}
			return newElem;
		}

		private KElement evaluateDot(final String path)
		{
			if (path.startsWith(JDFCoreConstants.DOTSLASH))
			{
				return getCreateXPathElement(path.substring(2));
			}
			if (path.startsWith(JDFCoreConstants.DOTDOTSLASH))
			{
				return theElement.getParentNode_KElement().getCreateXPathElement(path.substring(3));
			}
			if (path.equals(JDFCoreConstants.DOT))
			{
				return theElement;
			}
			if (path.equals(".."))
			{
				return theElement.getParentNode_KElement();
			}
			throw new JDFException("GetCreateXPathElement:: invalid path: " + path);
		}

		private KElement evaluateSlash(final String path)
		{
			final KElement r = theElement.getDocRoot();
			final int nextPos = path.indexOf(JDFCoreConstants.SLASH, 2);
			if (!path.substring(1, nextPos).equals(r.getNodeName()))
			{
				throw new JDFException("GetCreateXPathElement:: invalid path: " + path);
			}

			if (nextPos == -1)
			{
				return theElement;
			}

			return new XPathHelper(r).getCreateXPathElement(path.substring(nextPos + 1));
		}

		private int findSlashBrack(final int slash, final int brack)
		{
			final int slashBrack;
			if (slash > 0 && brack > 0)
			{
				slashBrack = Math.max(slash, brack + 1);
			}
			else if (brack > 0)
			{
				slashBrack = brack + 1;
			}
			else
			{
				slashBrack = slash;
			}
			return slashBrack;
		}

		/**
		 * 
		 *  
		 * @param iSkip
		 * @param newPath
		 * @param pos
		 * @param attName
		 * @return
		 */
		private int calcSkipFromAtt(int iSkip, String newPath, int pos, String attName)
		{
			final String kidName = pos >= 0 ? newPath.substring(0, pos) : newPath;
			final VElement vNewChild = theElement.getChildElementVector(kidName, null);
			for (int j = 0; j < vNewChild.size(); j++)
			{
				final KElement tryKid = vNewChild.get(j);
				if (attName.equals(tryKid.getXPathAttribute(attName, null)))
				{
					iSkip = j + 1;
					break;
				}
			}
			if (iSkip == -1)
			{
				iSkip = vNewChild.size();
			}
			return iSkip;
		}

		/**
		 * 
		 *  
		 * @param iSkip
		 * @param newPath
		 * @param pos
		 */
		private void fillMissing(int iSkip, String newPath, int pos)
		{
			if (iSkip > 0)
			{
				String elem = pos < 0 ? newPath : newPath.substring(0, pos);
				final int n = theElement.numChildElements_KElement(elem, null);
				for (int i = n; i < iSkip; i++)
				{
					theElement.appendElement(elem, null);
				}
			}
		}
	}

	/**
	 * Gets the XPath full tree representation of 'this'
	 * @param relativeTo relative element to which to create an xpath
	 * @param methCountSiblings , if 1 count siblings, i.e. add '[n]' if 0, only specify the path of parents if 2 or 3, add [@ID="id"]
	 * @return String the XPath representation of 'this' e.g. <code>/root/parent/element</code><br>
	 * <code>null</code> if parent of this is null (e.g. called on rootnode)
	 */
	String buildRelativeXPath(final KElement relativeTo, final int methCountSiblings)
	{
		if (relativeTo == theElement)
			return ".";
		final KElement parent = theElement.getParentNode_KElement();

		String path = buildLocalPath(methCountSiblings, parent);
		if (parent != null)
		{
			path = new XPathHelper(parent).buildRelativeXPath(relativeTo, methCountSiblings) + path;
		}

		return path;
	}

	/**
	 * Gets the XPath full tree representation of 'this'
	 * @param relativeTo relative path to which to create an xpath
	 * @param methCountSiblings , if 1 count siblings, i.e. add '[n]' if 0, only specify the path of parents if 2 or 3, add [@ID="id"]
	 * @return String the XPath representation of 'this' e.g. <code>/root/parent/element</code><br>
	 * <code>null</code> if parent of this is null (e.g. called on rootnode)
	 */
	String buildXPath(final String relativeTo, final int methCountSiblings)
	{
		final KElement parent = theElement.getParentNode_KElement();

		String path = buildLocalPath(methCountSiblings, parent);
		if (parent != null)
		{
			path = new XPathHelper(parent).buildXPath(relativeTo, methCountSiblings) + path;
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

	/**
	 * 
	 * @param methCountSiblings
	 * @param parent
	 * @return
	 */
	String buildLocalPath(final int methCountSiblings, final KElement parent)
	{
		StringBuffer buf = new StringBuffer(theElement.getNodeName());
		if (methCountSiblings > 0)
		{
			if (methCountSiblings == 3 && theElement.hasAttribute_KElement(JDFCoreConstants.ID, null, false))
			{
				buf.append("[@ID=\"").append(theElement.getAttribute(JDFCoreConstants.ID)).append("\"]");
			}
			else
			{
				String s = buf.toString();
				Node e = (parent != null) ? parent.getElement_KElement(s, null, 0) : null;
				int i = 1;
				while (e != null)
				{
					if (e == theElement)
					{
						buf.append("[").append(i).append("]");
						break;
					}
					do
					{
						e = e.getNextSibling();
					}
					while (e != null && !s.equals(e.getNodeName()));
					i++;
				}
			}
		}

		return "/" + buf.toString();
	}

	/**
	 * Sets an attribute as defined by XPath to value <br>
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported
	 * @param path XPath abbreviated syntax representation of the attribute, e.g.: <code>parentElement/thisElement@thisAtt</code>
	 * <code>parentElement/thisElement[2]/@thisAtt</code> <code>parentElement/thisElement[@foo=\"bar\"]/@thisAtt</code>
	 * @param value string to be set as attribute value
	 */
	void setXPathValue(final String path, final String value)
	{
		final int pos = path.lastIndexOf(JDFCoreConstants.AET);
		final int pos2 = path.lastIndexOf(JDFCoreConstants.SLASH);
		if (pos >= 0 && pos > pos2)
		{
			setXPathAttribute(path, value);
		}
		else
		{
			VElement vEle = getXPathElementVectorInternal(path, -1, true);
			if (vEle == null)
			{
				vEle = new VElement();
			}
			if (vEle.size() == 0)
			{
				final KElement kEle = getCreateXPathElement(path);
				vEle.add(kEle);
			}
			for (int i = 0; i < vEle.size(); i++)
			{
				vEle.get(i).setText(value);
			}
		}
	}

	/**
	 * 
	 * sets all xpaths to the values provided in map
	 * @param map map of XPath / values to set
	 */
	void setXPathValues(JDFAttributeMap map)
	{
		if (map == null)
			return;
		Iterator<String> it = map.getKeyIterator();

		// we need this double loop to ensure that all namespaces are correctly set
		while (it.hasNext())
		{
			String xpath = it.next();
			if (xpath.indexOf(AttributeName.XMLNS) >= 0)
			{
				setXPathValue(xpath, map.get(xpath));
			}
		}

		it = map.getKeyIterator();
		while (it.hasNext())
		{
			String xpath = it.next();
			if (xpath.indexOf(AttributeName.XMLNS) < 0)
			{
				setXPathValue(xpath, map.get(xpath));
			}
		}
	}

	/**
	 * Sets an attribute as defined by XPath to value <br>
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported
	 * @param path XPath abbreviated syntax representation of the attribute, e.g.: <code>parentElement/thisElement@thisAtt</code>
	 * <code>parentElement/thisElement[2]/@thisAtt</code> <code>parentElement/thisElement[@foo=\"bar\"]/@thisAtt</code>
	 * @param value string to be set as attribute value
	 * @throws JDFException if the defined path is a bad attribute path
	 */
	void setXPathAttribute(final String path, final String value)
	{
		final int pos = path.lastIndexOf(JDFCoreConstants.AET);
		if (pos == -1)
		{
			throw new JDFException("SetXPathAttribute - bad attribute path: " + path);
		}

		final String att = path.substring(pos + 1);
		final String strAttrPath = path.substring(0, pos);
		VElement vEle = getXPathElementVectorInternal(strAttrPath, -1, true);
		if (vEle == null)
		{
			vEle = new VElement();
		}
		if (vEle.size() == 0)
		{
			final KElement kEle = getCreateXPathElement(strAttrPath);
			vEle.add(kEle);
		}
		for (int i = 0; i < vEle.size(); i++)
		{
			vEle.get(i).setAttribute(att, value, null);
		}
	}

	/**
	 * returns true if the element or attribute described by this XPath exists, else false
	 * @param path the XPath to test for
	 * @return true if the element described by path exists
	 */
	boolean hasXPathNode(final String path)
	{
		final String path2 = StringUtil.replaceString(path, "[@", "");
		final int pos = path2.indexOf(JDFCoreConstants.AET);
		if (pos >= 0)
		{
			return getXPathAttribute(path, null) != null;
		}
		return getXPathElement(path) != null;
	}

	/**
	 * Gets an attribute value as defined by XPath namespace prefixes are resolved or the node text if an element is specified <br>
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported TODO fix bug for attribute searches where the att value contains xpath syntax
	 * @param path XPath abbreviated syntax representation of the attribute, <code>parentElement/thisElement/@thisAtt</code>
	 * <code>parentElement/thisElement[2]/@thisAtt</code> <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]/@thisAtt</code>
	 * @param def default value if it doesn't exist
	 * @return String the String value of the attribute or null if the xpath element does not exist
	 * @throws JDFException if the defined path is a bad attribute path
	 * @default getXPathAttribute(path, null);
	 */
	String getXPathAttribute(final String path, final String def)
	{
		int pos = path.lastIndexOf(JDFCoreConstants.AET);
		final String elemPath;
		if (pos == -1 || pos > 0 && path.charAt(pos - 1) == '[')
		{
			elemPath = path;
			pos = -1;
		}
		else
		{
			elemPath = path.substring(0, pos);
		}
		final KElement kEle = getXPathElement(elemPath);
		if (kEle == null)
			return def;
		if (pos >= 0)
		{
			return kEle.getAttribute_KElement(path.substring(pos + 1), null, def);
		}
		else
		{
			String s = kEle.getText();
			return s == null ? def : s;
		}
	}

	/**
	 * Gets an attribute value as defined by XPath namespace prefixes are resolved <br>
	 * Attributes are searched for in partitioned resources if appropriate
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported TODO fix bug for attribute searches where the att value contains xpath syntax
	 * @param path XPath abbreviated syntax representation of the attribute, <code>parentElement/thisElement/@thisAtt</code>
	 * <code>parentElement/thisElement[2]/@thisAtt</code> <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]/@thisAtt</code>
	 * @param def default value if it doesn't exist
	 * @return String the String value of the attribute or null if the xpath element does not exist
	 * @throws JDFException if the defined path is a bad attribute path
	 * @default getXPathAttribute(path, null);
	 */
	String getInheritedXPathAttribute(final String path, final String def)
	{
		final int pos = path.lastIndexOf(JDFCoreConstants.AET);
		if (pos == -1 || pos > 0 && path.charAt(pos - 1) == '[')
		{
			throw new JDFException("GetXPathAttribute - bad attribute path: " + path);
		}
		final KElement kEle = getXPathElement(path.substring(0, pos));
		return kEle == null ? def // xpath element does not exist
		: kEle.getAttribute(path.substring(pos + 1), null, def);
	}

	/**
	 * remove an attribute or element that is described by the xpath path quietly returns if the element does not exist
	 * @param path the XPath to the attribute that is to be removed
	 * @throws JDFException if the XPath is corrupt
	 */
	void removeXPathElement(final String path)
	{
		final int pos = path.lastIndexOf(JDFCoreConstants.AET);
		if (pos == -1)
		{
			final KElement kEle = getXPathElement(path);
			if (kEle != null)
			{
				kEle.deleteNode();
			}
		}
		else
		{
			removeXPathAttribute(path);
		}
	}

	/**
	 * Gets a map of attribute values as defined by XPath 
	 * namespace prefixes are resolved <br>
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported
	 * @param path XPath abbreviated syntax representation of the attribute, <code>parentElement/thisElement/@thisAtt</code>
	 * <code>parentElement/thisElement[2]/@thisAtt</code> <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]/@thisAtt</code>
	 * if null, assume .//@*, i.e. all of this
	 * @param bWantText if true, also add text
	 * 
	 * @return String the String value of the attribute or null if the xpath element does not exist
	 * @throws JDFException if the defined path is a bad attribute path
	 */
	JDFAttributeMap getXPathAttributeMap(String path, boolean bWantText)
	{
		final int pos = path == null ? -1 : path.lastIndexOf(JDFCoreConstants.AET);
		final String attName = path == null ? null : path.substring(pos + 1);
		String subele = path == null ? ".//*" : path.substring(0, pos);
		final VElement vEle = theElement.getXPathElementVector(subele, 0);
		if (vEle == null)
		{
			return null;
		}
		String base = path == null ? null : buildXPath((String) null, 1);
		final JDFAttributeMap map = new JDFAttributeMap();
		for (KElement e : vEle)
		{
			VString vKeys = e.getAttributeVector_KElement();
			String baseXPath = new XPathHelper(e).buildXPath(base, 1);
			String nodeName = e.getNodeName();
			String prefix = KElement.xmlnsPrefix(nodeName);
			if (prefix != null)
			{
				String uri = e.getNamespaceURI();
				map.put("/" + StringUtil.token(baseXPath, 0, "/") + "/@xmlns:" + prefix, uri);
			}
			if (vKeys != null)
			{
				for (String key : vKeys)
				{
					if (StringUtil.matches(key, attName))
					{
						map.put(baseXPath + "/@" + key, e.getAttributeRaw(key));
						prefix = KElement.xmlnsPrefix(key);
						if (prefix != null)
						{
							String uri = e.getNamespaceURIFromPrefix(prefix);
							map.put("/" + StringUtil.token(baseXPath, 0, "/") + "/@xmlns:" + prefix, uri);
						}
					}
				}
			}
			if (bWantText)
			{
				String text = e.getText();
				if (text != null || vKeys == null || vKeys.size() == 0)
				{
					if (text == null)
						text = "";
					map.put(baseXPath, text);
				}
			}
		}
		return map.size() > 0 ? map : null;
	}

	/**
	 * gets an element as defined by XPath to value <br>
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@ are supported
	 * @param path XPath abbreviated syntax representation of the attribute, e.g <code>parentElement/thisElement</code>
	 * <code>parentElement/thisElement[2]</code> <code>parentElement[@a=\"b\"]/thisElement[./foo/@foo=\"bar\"]</code>
	 * @return KElement the specified element
	 * @throws IllegalArgumentException if path is not supported
	 */
	KElement getXPathElement(final String path)
	{
		if (path == null || path.length() == 0 || ".".equals(path))
		{
			return theElement;
		}
		final VElement v = getXPathElementVectorInternal(path, 1, true);
		if (v == null || v.size() < 1)
		{
			return null;
		}
		return v.item(0);
	}

	/**
	 * remove an attribute or text that is described by the xpath path quietly returns if the attribute or text does not exist
	 * @param path the XPath to the attribute that is to be removed
	 * @throws JDFException if the XPath is corrupt
	 */
	void removeXPathAttribute(final String path)
	{
		final int pos = path.lastIndexOf(JDFCoreConstants.AET);
		if (pos == -1)
		{
			final KElement kEle = theElement.getXPathElement(path);
			if (kEle != null)
			{
				kEle.removeAllText();
			}
		}
		else
		{
			final KElement kEle = theElement.getXPathElement(path.substring(0, pos));
			if (kEle != null)
			{
				kEle.removeAttribute(path.substring(pos + 1), null);
			}
		}
	}

	/**
	 * gets an vector of elements element as defined by XPath to value <br>
	 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@,// are supported
	 * @param path XPath abbreviated syntax representation of the attribute, e.g <code>parentElement/thisElement</code>
	 * <code>parentElement/thisElement[2]</code> <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]</code>
	 * @param maxSize 
	 * @param bLocal 
	 * @return VElement the vector of matching elements
	 * @throws IllegalArgumentException if path is not supported
	 */
	VElement getXPathElementVectorInternal(String path, final int maxSize, final boolean bLocal)
	{
		return new ElementVectorGetter().getXPathElementVectorInternal(path, maxSize, bLocal);
	}

	private class ElementVectorGetter
	{
		/**
		 * gets an vector of elements element as defined by XPath to value <br>
		 * @tbd enhance the subsets of allowed XPaths, now only .,..,/,@,// are supported
		 * @param path XPath abbreviated syntax representation of the attribute, e.g <code>parentElement/thisElement</code>
		 * <code>parentElement/thisElement[2]</code> <code>parentElement[@a=\"b\"]/thisElement[@foo=\"bar\"]</code>
		 * @param maxSize 
		 * @param bLocal 
		 * @return VElement the vector of matching elements
		 * @throws IllegalArgumentException if path is not supported
		 */
		VElement getXPathElementVectorInternal(String path, final int maxSize, final boolean bLocal)
		{
			if (path == null)
			{
				return null;
			}

			VElement vRet = new VElement();
			if (JDFCoreConstants.EMPTYSTRING.equals(path))
			{
				return completeEmpty(bLocal, vRet);
			}

			if (path.startsWith(JDFCoreConstants.SLASH))
			{
				return evaluateSlash(path, maxSize);
			}
			else if (path.startsWith(JDFCoreConstants.DOT))
			{
				return evaluateDot(path, maxSize, vRet);
			}

			int posB0 = path.indexOf("[");
			int posBAt = posB0;
			if (posB0 >= 0)
			{
				char next = path.charAt(posB0 + 1);
				if (next < '0' || next > '9')
				{
					posB0 = -1;
				}
				else
				{
					posBAt = -1;
				}
			}
			int iSkip = -1;
			String newPath = path;
			int pos = newPath.indexOf(JDFCoreConstants.SLASH);
			JDFAttributeMap map = null;
			boolean bExplicitSkip = false;

			if (posB0 != -1 && (posB0 < pos || pos == -1)) // parse for [n]
			{
				final int posB1 = path.indexOf("]");

				// TODO fix escape attribute values

				final String n = path.substring(posB0 + 1, posB1);
				if (posB1 - posB0 == 2) // most of the time, so special handling is faster...
				{
					iSkip = 1 + n.charAt(0) - '1';
				}
				if (iSkip <= 0 || iSkip > 9)
				{
					iSkip = StringUtil.parseInt(n, 0);
				}

				if (iSkip <= 0)
				{
					throw new IllegalArgumentException("getXPathVector: bad index:" + iSkip);
				}

				iSkip--;
				bExplicitSkip = true;
				String childName = path.substring(0, posB0);
				newPath = childName + path.substring(posB1 + 1);
				pos = newPath.indexOf(JDFCoreConstants.SLASH);
			}
			else if (posBAt != -1 && (posBAt < pos || pos == -1)) // parse for [@a="b"]
			{
				final int posB1 = path.indexOf("]");
				map = getXPathAtMap(path, posBAt, posB1);
				newPath = path.substring(0, posBAt) + path.substring(posB1 + 1);
				pos = newPath.indexOf(JDFCoreConstants.SLASH);
			}

			if (pos != -1) // have another element
			{
				final String elmName = newPath.substring(0, pos);
				VElement ve = getLocalElements(bLocal, newPath, map, elmName, iSkip);
				if (ve == null || ve.size() == 0)
				{
					return null;
				}

				final int iFirst = 0;
				final int iLast = ve.size();
				for (int i = iFirst; i < iLast; i++) // loop in case multiple elements contain the same attribute
				{
					final KElement ee = ve.item(i);
					VElement eRet = new XPathHelper(ee).getXPathElementVectorInternal(newPath.substring(pos + 1), maxSize, true);
					if (eRet != null)
					{
						vRet.addAll(eRet);
					}
				}

				return vRet.size() > 0 ? vRet : null;
			}
			// last element
			if (bExplicitSkip)
			{
				return doExplicitSkip(vRet, iSkip, newPath);
			}

			if (bLocal)
			{
				vRet.addAll(getPathVector(newPath, map, false));
			}
			else
			{
				vRet = theElement.getElementsByTagName_KElement(newPath, null);
				if (theElement.getLocalName().equals(newPath) || KElement.isWildCard(newPath))
				{
					vRet.add(theElement);
				}
			}
			return vRet;
		}

		private VElement getLocalElements(final boolean bLocal, String newPath, JDFAttributeMap map, final String elmName, int iSkip)
		{
			VElement ve;
			if (bLocal)
			{
				if (iSkip < 0)
				{
					ve = getPathVector(elmName, map, false);
				}
				else
				{
					KElement e = theElement.getElement_KElement(elmName, null, iSkip);
					if (e == null)
					{
						ve = null;
					}
					else
					{
						ve = new VElement();
						ve.add(e);
					}
				}
			}
			else
			{
				ve = theElement.getElementsByTagName_KElement(elmName, null);
				if (theElement.getLocalName().equals(elmName) || KElement.isWildCard(newPath))
				{
					ve.add(theElement);
				}
			}
			return ve;
		}

		private VElement doExplicitSkip(VElement vRet, int iSkip, String newPath)
		{
			final KElement e = theElement.getElement_KElement(newPath, null, iSkip);
			if (e == null)
			{
				return null;
			}

			vRet.add(e);
			return vRet;
		}

		private VElement evaluateDot(String path, final int maxSize, VElement vRet)
		{
			if (path.startsWith(".//"))
			{
				VElement v = getXPathElementVectorInternal(path.substring(3), maxSize, false);
				if (v == null)
					v = new VElement();
				v.add(theElement);
				return v;
			}
			else if (path.startsWith(JDFCoreConstants.DOTSLASH))
			{
				return getXPathElementVectorInternal(path.substring(JDFCoreConstants.DOTSLASH.length()), maxSize, true);
			}

			else if (path.startsWith(JDFCoreConstants.DOTDOTSLASH))
			{
				final KElement parent = theElement.getParentNode_KElement();
				if (parent == null)
				{
					return null;
				}

				return new XPathHelper(parent).getXPathElementVectorInternal(path.substring(JDFCoreConstants.DOTDOTSLASH.length()), maxSize, true);
			}
			else if (path.equals(JDFCoreConstants.DOT))
			{
				vRet.add(theElement);
				return vRet;
			}
			else if (path.equals(".."))
			{
				final KElement parent = theElement.getParentNode_KElement();
				if (parent == null)
				{
					return null;
				}

				vRet.add(parent);
				return vRet;
			}
			throw new IllegalArgumentException("Invalid path name: " + path);
		}

		private VElement completeEmpty(final boolean bLocal, VElement vRet)
		{
			if (bLocal)
			{
				vRet.add(theElement);
			}
			else
			{
				vRet = theElement.getChildrenByTagName(null, null, null, false, true, 0);
			}
			return vRet;
		}

		private VElement evaluateSlash(String path, final int maxSize)
		{
			if (path.startsWith("//"))
			{
				return new XPathHelper(theElement.getDocRoot()).getXPathElementVectorInternal(path.substring(2), maxSize, false);
			}

			final KElement r = theElement.getDocRoot();
			final String rootNodeName = r.getLocalName();
			final int nextPos = path.indexOf("/", 2);
			final String rootPath = nextPos > 0 ? path.substring(1, nextPos) : path.substring(1);
			final String nextPath = nextPos > 0 ? path.substring(nextPos + 1) : "";
			if (rootPath.equals(rootNodeName) || KElement.isWildCard(rootPath))
			{
				return new XPathHelper(r).getXPathElementVectorInternal(nextPath, maxSize, true);
			}
			throw new IllegalArgumentException("Invalid root node name: " + path);
		}
	}

	VElement getPathVector(String newPath, JDFAttributeMap map, boolean create)
	{
		VElement v = theElement.getChildElementVector_KElement(newPath, null, null, true, 0);
		VElement vRet = new VElement();
		if (v != null && v.size() > 0)
		{
			if (map != null && map.size() > 0)
			{
				VString keys = map.getKeys();
				for (String key : keys)
				{
					for (KElement e : v)
					{
						if (e.getXPathAttribute(key, "").equals(map.get(key)))
						{
							vRet.add(e);
						}
					}
				}
			}
			else
			{
				vRet = v;
			}
		}
		else if (map != null && create)
		{
			VString keys = map.getKeys();
			KElement e = theElement.appendElement(newPath);
			for (String key : keys)
			{
				new XPathHelper(e).setXPathValue(key, map.get(key));
			}
			vRet.add(e);
		}
		return vRet;
	}

	JDFAttributeMap getXPathAtMap(final String path, final int posBAt, final int posB1)
	{
		final JDFAttributeMap map = new JDFAttributeMap();
		final String attEqVal = path.substring(posBAt + 1, posB1);
		// TODO multiple attributes, maybe tokenize by ","
		final String attName = StringUtil.token(attEqVal, 0, "=");
		final String attVal = attEqVal.substring(attName.length() + 2, attEqVal.length() - 1);
		map.put(attName, attVal);
		return map;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "XPathHelper [theElement=" + theElement + "]";
	}
}