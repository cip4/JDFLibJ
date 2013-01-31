/*
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
package org.cip4.jdflib.elementwalker;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.xerces.dom.AttrNSImpl;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.BiHashMap;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.Attr;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG<br/>
 * ensures correct ns uri
 * 
 */
public class EnsureNSUri extends BaseElementWalker
{

	protected final BiHashMap<String, String> nsMap;
	protected final HashMap<String, String> aliasMap;

	/**
	 * add a prefix / uri pair
	 * @param prefix the namespace prefix - may be null for empty namespace
	 * @param uri the URI must not be null 
	 * @throws IllegalArgumentException if uri is null
	 */
	public void addNS(String prefix, String uri)
	{
		if (uri == null)
			throw new IllegalArgumentException("uri MUST NOT be null");
		if (prefix == null)
			prefix = "";
		nsMap.put(prefix, uri);
	}

	/**
	 * add a an alias
	 * @param badPrefix the ns prefix to rename (e.g. ns1)
	 * @param goodPrefix the destination prefix
	 */
	public void addAlias(String badPrefix, String goodPrefix)
	{
		if (goodPrefix == null)
			goodPrefix = "<";
		aliasMap.put(badPrefix, goodPrefix);
	}

	/**
	 * 
	 * @param root
	 */
	public void walk(KElement root)
	{
		Iterator<String> it = nsMap.keySet().iterator();
		while (it.hasNext())
		{
			String next = it.next();
			String strNameSpaceURI = nsMap.get(next);
			root.addNameSpace(next, strNameSpaceURI);
		}
		Iterator<String> itAlias = aliasMap.keySet().iterator();
		while (itAlias.hasNext())
		{
			String next = itAlias.next();
			String zappAtt = next == null ? "xmlns" : "xmlns:" + next;
			root.removeAttribute(zappAtt);
		}
		walkTree(root, null);
	}

	/**
	 * 
	 */
	public EnsureNSUri()
	{
		super(new BaseWalkerFactory());
		nsMap = new BiHashMap<String, String>();
		aliasMap = new HashMap<String, String>();
		new BaseWalker(getFactory()); // need a default walker
	}

	@Override
	protected BaseWalkerFactory getFactory()
	{
		return (BaseWalkerFactory) theFactory;
	}

	/**
	 * the resource walker note the naming convention Walkxxx so that it is automagically instantiated by the super classes
	 * 
	 * @author prosirai
	 * 
	 */
	public class WalkElement extends BaseWalker
	{

		/**
		 * fills this into the factory
		 */
		public WalkElement()
		{
			super(getFactory());
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e1 - the element to track
		 * @param trackElem - always null
		 * @return the element to continue walking
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			String prefix = e1.getPrefix();
			String uri = e1.getNamespaceURI();
			String destPrefix = getAlias(prefix, uri);

			if (nsMap.get(destPrefix) != null)
			{
				e1.setNamespaceURI(nsMap.get(destPrefix));
			}
			if ("".equals(destPrefix))
				destPrefix = null;
			if (destPrefix != null && !destPrefix.equals(prefix) || (prefix != null && destPrefix == null))
			{
				e1.setPrefix(destPrefix);
			}

			VString atts = e1.getAttributeVector_KElement();

			for (String att : atts)
			{
				processAttribute(e1, att);
			}
			return e1;
		}

		/**
		 * 
		 * @param e1
		 * @param att
		 */
		private void processAttribute(final KElement e1, String att)
		{
			String origPrefix = KElement.xmlnsPrefix(att);
			String prefix = getAlias(origPrefix, null);
			String uri = nsMap.get(prefix);
			if ("".equals(prefix))
				prefix = null;
			if (uri != null && !JDFConstants.XMLNS.equals(att))
			{
				processStandardAttribute(e1, att, origPrefix, prefix, uri);
			}
			else if (JDFConstants.XMLNS.equals(prefix) || JDFConstants.XMLNS.equals(att))
			{
				processXmlns(e1, att);
			}
		}

		private void processStandardAttribute(final KElement e1, String att, String origPrefix, String prefix, String uri)
		{
			Attr attr = e1.getDOMAttr(att, null, false);
			if (!uri.equals(attr.getNamespaceURI()) || !ContainerUtil.equals(prefix, origPrefix))
			{
				if (attr instanceof AttrNSImpl)
				{
					String val = e1.getAttribute(att);
					e1.removeAttribute(att);
					if (origPrefix != null && !origPrefix.equals(prefix))
						att = StringUtil.replaceToken(att, 0, ":", prefix);
					if (prefix == null)
						e1.setAttribute(att, val);
					else
						e1.setAttributeNS(uri, att, val);
				}
			}
		}

		private void processXmlns(final KElement e1, String att)
		{
			String locName = KElement.xmlnsLocalName(att);
			String alias = getAlias(locName, null);
			if (alias != null && !alias.equals(locName))
			{
				e1.removeAttribute(att);
			}
			else
			{
				if (locName == null)
					locName = ":";
				String uri = nsMap.get(locName);
				if (uri != null)
				{
					Attr attr = e1.getDOMAttr(att, null, false);
					if (!uri.equals(attr.getValue()))
					{
						attr.setNodeValue(uri);
					}
				}
			}
		}

		/**
		 * 
		 *   
		 * @param prefix
		 * @param localName
		 * @param uri 
		 * @return
		 */
		private String getAlias(String prefix, String uri)
		{
			if (prefix == null)
				return "";
			String s2 = aliasMap.get(prefix);
			if ("<".equals(s2))
				return "";
			if (s2 == null && uri != null)
			{
				String newPrefix = nsMap.getKey(uri);
				String newURI = nsMap.get(prefix);
				if (newURI == null && newPrefix != null && !ContainerUtil.equals(prefix, newPrefix))
				{
					addAlias(prefix, newPrefix);
					s2 = newPrefix;
				}
			}
			return s2 == null ? prefix : s2;
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
