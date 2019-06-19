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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.MyPair;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker that writes XPaths of all elements to a file
 */
public class XMLCompareWalker extends BaseElementWalker
{
	final Map<String, MyPair<String, String>> theMap;
	private boolean first;
	private int precision;

	/**
	 * @return the precision
	 */
	public int getPrecision()
	{
		return precision;
	}

	/**
	 * @param precision the precision to set
	 */
	public void setPrecision(final int precision)
	{
		this.precision = precision;
	}

	/**
	 * @param e
	 * @return the number of xpaths
	 */
	public Map<String, MyPair<String, String>> compare()
	{
		theMap.clear();
		first = true;
		walkTree(e1, e2);
		first = false;
		walkTree(e2, e1);
		return theMap;
	}

	private int method;
	private final KElement e2;
	private final KElement e1;
	private final Set<String> ignore;

	/**
	 * @param w
	 */
	public XMLCompareWalker(final KElement e1, final KElement e2)
	{
		super(new BaseWalkerFactory());
		theMap = new HashMap<>();
		method = 1;
		this.e1 = e1;
		this.e2 = e2;
		precision = -1;
		ignore = new HashSet<>();
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
			final String myPath = e.buildRelativeXPath(e.getParentNode_KElement(), method);
			final KElement e2 = trackElem == null ? null : trackElem.getXPathElement(myPath);
			final VString keys = e.getAttributeVector();
			for (final String key : keys)
			{
				final MyPair<String, String> diff = myCompare(key, e.getAttribute(key), e2 == null ? null : e2.getNonEmpty(key));
				if (diff != null)
				{
					final String ret = e.buildRelativeXPath((first ? e1.getParentNode_KElement() : e2.getParentNode_KElement()), method) + "/@" + key;
					theMap.put(ret, diff);
				}
			}
			return e2;
		}

		MyPair<String, String> myCompare(final String key, final String attribute, final String attribute2)
		{
			if (wantKey(key) && !matches(attribute, attribute2))
			{
				return first ? new MyPair<>(attribute, attribute2) : new MyPair<>(attribute2, attribute);
			}
			return null;
		}

		boolean wantKey(final String key)
		{
			return !ignore.contains(key);
		}

		boolean matches(final String attribute, final String attribute2)
		{
			if (precision >= 0 && attribute != null && attribute2 != null)
			{
				if (StringUtil.isInteger(attribute) && StringUtil.isInteger(attribute2))
				{
					return StringUtil.parseInt(attribute, -1) == StringUtil.parseInt(attribute2, -2);
				}
				if (StringUtil.isNumber(attribute) && StringUtil.isNumber(attribute2))
				{
					final double parseDouble = StringUtil.parseDouble(attribute, -1);
					final double parseDouble2 = StringUtil.parseDouble(attribute2, -2);
					if (parseDouble == parseDouble2)
						return true;
					if (precision > 0)
					{
						if (parseDouble * parseDouble2 < 0)
							return false;
						final double mx = Math.max(Math.abs(parseDouble), Math.abs(parseDouble2));
						final double mn = Math.min(Math.abs(parseDouble), Math.abs(parseDouble2));
						return mn / mx > 1.0 - (Math.pow(0.1, precision));
					}
				}

			}
			return StringUtil.equals(attribute, attribute2);
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
	 * @param method the method to set
	 */
	public void addIgnore(final Collection<String> keys)
	{
		if (keys != null)
		{
			for (final String key : keys)
				addIgnore(key);
		}
	}

	public void addIgnore(final String key)
	{
		if (!StringUtil.isEmpty(key))
			ignore.add(key);

	}

}
