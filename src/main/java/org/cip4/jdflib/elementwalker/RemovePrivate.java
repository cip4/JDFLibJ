/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import java.util.HashSet;
import java.util.Set;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.process.JDFGeneralID;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG<br/>
 *         removes any private extensions, including prefixed keys in GeneralID elements
 *
 */
public class RemovePrivate extends BaseElementWalker
{
	Set<String> prefixes;
	boolean zappGeneralID;
	boolean zappElements;
	boolean zappAttributes;

	/**
	 *
	 */
	public RemovePrivate()
	{
		super(new BaseWalkerFactory());
		prefixes = null;
		zappAttributes = zappElements = zappGeneralID = true;
		new BaseWalker(getFactory()); // need a default walker
	}

	/**
	 * add a prefix if never called -all prefixes are zapped
	 *
	 * @param prefix
	 */
	public void addPrefix(final String prefix)
	{
		if (prefixes == null)
			prefixes = new HashSet<>();
		prefixes.add(prefix);
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
			if (!zappAttributes)
				return e1;

			final VString unknown;
			if (prefixes == null)
			{
				if (!(e1 instanceof JDFElement))
					return e1;
				final JDFElement j = (JDFElement) e1;
				unknown = j.getUnknownAttributes(false, -1);
			}
			else
			{
				unknown = e1.getAttributeVector_KElement();
			}
			if (unknown != null)
			{
				for (final String attName : unknown)
				{
					if (prefixes != null)
					{
						final String prefix = KElement.xmlnsPrefix(attName);
						if (prefix == null)
							continue;
						if (JDFConstants.XMLNS.equals(prefix))
						{
							if (!prefixes.contains(KElement.xmlnsLocalName(attName)))
								continue;
						}
						// not in list - move on
						else if (!prefixes.contains(prefix))
							continue;
					}
					e1.removeAttribute(attName);
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

	/**
	 * zapp me
	 *
	 * @author prosirai
	 *
	 */
	public class WalkPrivate extends WalkElement
	{

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e - the element to track
		 * @param trackElem - always null
		 * @return the element to continue walking
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			if (!JDFConstants.JDFNAMESPACE.equals(e.getNamespaceURI()))
			{
				e.deleteNode();
				return null;
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
			boolean b = zappElements && super.matches(toCheck);
			if (!b)
			{
				return false;
			}
			if (prefixes != null)
			{
				final String prefix = toCheck.getPrefix();
				// not in list - we don't match
				if (prefix == null || !prefixes.contains(prefix))
					b = false;
			}
			else
			{
				b = !JDFConstants.JDFNAMESPACE.equals(toCheck.getNamespaceURI());
			}
			return b;
		}
	}

	/**
	 * zapp me
	 *
	 * @author prosirai
	 *
	 */
	public class WalkGeneralID extends WalkElement
	{

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e - the element to track
		 * @param trackElem - always null
		 * @return the element to continue walking
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final String idUsage = ((JDFGeneralID) e).getIDUsage();
			final String prefix = KElement.xmlnsPrefix(idUsage);
			if (prefix != null && !"JDF".equalsIgnoreCase(prefix))
			{
				if (prefixes == null || prefixes.contains(prefix))
				{
					e.deleteNode();
					return null;
				}
			}
			return super.walk(e, trackElem);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return zappGeneralID && (toCheck instanceof JDFGeneralID);
		}
	}

	/**
	 *
	 * if set to true, generalID elements with proprietary namespace prefixes are zapped, else kept
	 *
	 * @param zappGeneralID if true, zapp 'em (the default) else keep 'em
	 */
	public void setZappGeneralID(final boolean zappGeneralID)
	{
		this.zappGeneralID = zappGeneralID;
	}

	/**
	 *
	 * if set to true, attributes with proprietary namespace prefixes are zapped, else kept
	 *
	 * @param zappAttributes if true, zapp 'em (the default) else keep 'em
	 */
	public void setZappAttributes(final boolean zappAttributes)
	{
		this.zappAttributes = zappAttributes;
	}

	/**
	 *
	 * if set to true, attributes with proprietary namespace prefixes are zapped, else kept
	 *
	 * @param zappElements if true, zapp 'em (the default) else keep 'em
	 */
	public void setZappElements(final boolean zappElements)
	{
		this.zappElements = zappElements;
	}
}
