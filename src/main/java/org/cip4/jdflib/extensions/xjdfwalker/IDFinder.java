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
package org.cip4.jdflib.extensions.xjdfwalker;

import java.util.HashMap;
import java.util.Map;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.extensions.SetHelper;

/**
 * @author Rainer Prosi finds all ids
 */
public class IDFinder extends BaseElementWalker
{
	protected final Map<String, IDPart> theMap;

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	public class IDPart
	{
		/**
		 * 
		 * @param idParent
		 * @param parts
		 */
		public IDPart(String idParent, VElement parts)
		{
			id = idParent;
			vMap = null;
			if (parts != null && parts.size() > 0)
			{
				vMap = new VJDFAttributeMap();
				for (int i = 0; i < parts.size(); i++)
				{
					vMap.add(parts.get(i).getAttributeMap());
				}
			}
		}

		/**
		 * 
		 */
		protected String id;
		/**
		 * 
		 */
		protected VJDFAttributeMap vMap;

		/**
		 * @see java.lang.Object#toString()
		 * @return
		 */
		@Override
		public String toString()
		{
			return "IDPart: ID=" + id + " Map= " + vMap;
		}

		/**
		 * @return
		 */
		public String getID()
		{
			return id;
		}

		/**
		 * @return
		 */
		public VJDFAttributeMap getPartMap()
		{
			return vMap;
		}
	}

	/**
	 * 
	 */
	public IDFinder()
	{
		super(new BaseWalkerFactory());
		theMap = new HashMap<String, IDPart>();
		new BaseWalker(getFactory()); // need a default walker
	}

	/**
	 * get a vector of all links and references of n and its children
	 * 
	 * @param n the element to walk
	 * @return the vector of unlinked resourcerefs and resourceLinks
	 */
	public Map<String, IDPart> getMap(final KElement n)
	{
		theMap.clear();
		walkTree(n, null);
		return theMap;
	}

	/**
	 * the link and ref walker
	 * 
	 * @author prosirai
	 * 
	 */
	public class WalkResource extends BaseWalker
	{
		/**
		 * fills this into the factory
		 */
		public WalkResource()
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
			final String idParent = e.getParentNode_KElement().getAttribute(AttributeName.ID, null, null);
			String id = e.getAttribute(AttributeName.ID, null, null);
			if (id == null)
			{
				id = e.generateDotID("ID", null);
				e.setID(id);
			}
			VElement vPart = e.getChildElementVector("Part", null, null, true, 0, false);
			theMap.put(id, new IDPart(idParent, vPart));
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
			final boolean b = super.matches(toCheck);
			if (!b)
			{
				return false;
			}
			String localName = toCheck.getLocalName();
			return (localName.equals("Resource") || localName.equals("Parameter"));
		}

	}

	/**
	 * the link and ref walker
	 * 
	 * @author prosirai
	 * 
	 */
	public class WalkSet extends BaseWalker
	{
		/**
		 * fills this into the factory
		 */
		public WalkSet()
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
			String id = e.getAttribute(AttributeName.ID, null, null);
			if (id == null)
			{
				id = "IDF" + KElement.uniqueID(0);
				e.setID(id);
			}
			theMap.put(id, new IDPart(id, null));
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
			final boolean b = super.matches(toCheck);
			if (!b)
			{
				return false;
			}
			return SetHelper.isSet(toCheck);
		}
	}
}
