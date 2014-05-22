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

import java.util.Iterator;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * June 9, 2008
 * 
 * replaces all instances of an attribute in all matching elements
 */
public class AttributeReplacer extends BaseElementWalker
{

	/**
	 * fills this into the factory
	 */
	protected JDFAttributeMap theMap;
	String elemRegExp;

	/**
	 * @param attributeName the attribute to replace
	 * @param newValue the new value, if null the attribute is removed
	 * @param elementExpression the elements to match, if null all match
	 */
	public AttributeReplacer(final String attributeName, final String newValue, final String elementExpression)
	{
		super(new BaseWalkerFactory());
		theMap = new JDFAttributeMap(attributeName, newValue);
		elemRegExp = elementExpression;
	}

	/**
	 * @param setMap the map of attributes to set
	 * @param elementExpression the elements to match, if null all match
	 */
	public AttributeReplacer(final JDFAttributeMap setMap, final String elementExpression)
	{
		super(new BaseWalkerFactory());
		theMap = setMap;
		elemRegExp = elementExpression;
	}

	/**
	 * erase all unlinked resources that are in n
	 * 
	 * @param root the element to clean
	 */
	public void replace(final KElement root)
	{
		walkTree(root, null);
	}

	/**
	 * the link and ref walker
	 * 
	 * @author prosirai
	 * 
	 */
	public class WalkAttributeReplacer extends WalkDefault
	{

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e
		 * @param trackElem
		 * @return
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final Iterator<String> it = theMap.getKeyIterator();
			while (it.hasNext())
			{
				final String key = it.next();
				if (e.hasAttribute(key))
				{
					final String value = theMap.get(key);
					e.setAttribute(key, value, null);
				}
			}
			return e;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 */
		@Override
		public boolean matches(final KElement e)
		{
			return StringUtil.matchesSimple(e.getLocalName(), elemRegExp);
		}

	}

	/**
	 * the link and ref walker
	 * 
	 * @author prosirai
	 * 
	 */
	public class WalkDefault extends BaseWalker
	{
		/**
		 * 
		 */
		public WalkDefault()
		{
			super(getFactory());
		}
	}

	/**
	 * @param map the attribute map to set
	 */
	public void setMap(final JDFAttributeMap map)
	{
		theMap = map;
	}
}
