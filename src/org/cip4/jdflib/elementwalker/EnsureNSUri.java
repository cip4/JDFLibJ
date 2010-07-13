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

import java.util.HashMap;
import java.util.Iterator;

import org.apache.xerces.dom.AttrNSImpl;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.w3c.dom.Attr;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG<br/>
 * ensures correct ns uri
 * 
 */
public class EnsureNSUri extends BaseElementWalker
{

	protected HashMap<String, String> nsMap;

	/**
	 * add a prefix / uri pair
	 * @param prefix
	 * @param uri
	 */
	public void addNS(String prefix, String uri)
	{
		nsMap.put(prefix, uri);
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
			root.addNameSpace(next, nsMap.get(next));
		}
		walkTree(root, null);
	}

	/**
	 * 
	 */
	public EnsureNSUri()
	{
		super(new BaseWalkerFactory());
		nsMap = new HashMap<String, String>();
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
			String s = e1.getPrefix();
			if (nsMap.get(s) != null)
				e1.setNamespaceURI(nsMap.get(s));
			VString atts = e1.getAttributeVector_KElement();
			for (String att : atts)
			{
				String prefix = KElement.xmlnsPrefix(att);
				if (prefix == null)
					prefix = ":";
				String uri = nsMap.get(prefix);
				if (uri != null)
				{
					Attr attr = e1.getDOMAttr(att, null, false);
					if (!uri.equals(attr.getNamespaceURI()))
					{
						if (attr instanceof AttrNSImpl)
						{
							String val = e1.getAttribute(att);
							e1.removeAttribute(att);
							e1.setAttributeNS(uri, att, val);
						}
					}
				}
				else if (JDFConstants.XMLNS.equals(prefix) || JDFConstants.XMLNS.equals(att))
				{
					String locName = KElement.xmlnsLocalName(att);
					if (locName == null)
						locName = ":";
					uri = nsMap.get(locName);
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
			return e1;
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
