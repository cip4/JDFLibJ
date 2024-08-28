/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.ListMap;

/**
 * @author Dr. Rainer Prosi
 *
 *         finds unlinked resources - example usage of the walker classes
 */
public class UnLinkFinder extends BaseElementWalker
{
	protected LinkData linkData;

	/**
	 *
	 */
	public UnLinkFinder()
	{
		super(new BaseWalkerFactory());
		linkData = new LinkData();
		ignoreForeign = true;
		new BaseWalker(getFactory()); // need a default walker
	}

	boolean ignoreForeign;

	/**
	 * @param ignoreForeign the ignoreForeign to set
	 */
	public void setIgnoreForeign(final boolean ignoreForeign)
	{
		this.ignoreForeign = ignoreForeign;
	}

	/**
	 * get a vector of all unlinked resources of n and its children
	 *
	 * @param n the node to walk
	 * @return the vector of unlinked resources
	 */
	public VElement getUnlinkedResources(final JDFNode n)
	{
		linkData.clear();
		walkTree(n, null);
		final Vector<KElement> toValueVector = ContainerUtil.toValueVector(linkData.resMap, false);
		return toValueVector == null ? null : new VElement(toValueVector);
	}

	/**
	 * get a vector of all unlinked resources of n and its children
	 *
	 * @param n the node to walk
	 * @return the vector of unlinked resources
	 */
	public VElement getUnlinkedRefs(final JDFNode n)
	{
		linkData.clear();
		walkTree(n, null);
		final List<KElement> toValueVector = linkData.refMap.getAllValues();
		if (toValueVector == null)
			return null;
		final VElement ret = new VElement();
		ret.addAll(toValueVector);
		return ret;
	}

	/**
	 * get a vector of all unlinked resources of n and its children
	 *
	 * @param n the node to walk
	 * @return the vector of unlinked resources
	 */
	public VElement getAllUnlinked(final JDFNode n)
	{
		linkData.clear();
		walkTree(n, null);
		List<KElement> toValueVector = ContainerUtil.toArrayList(linkData.resMap, false);
		final List<KElement> toValueVectorRef = linkData.refMap.getAllValues();
		toValueVector = (List<KElement>) ContainerUtil.addAll(toValueVector, toValueVectorRef);
		if (toValueVector == null)
			return null;
		final VElement ret = new VElement();
		ret.addAll(toValueVector);
		return ret;
	}

	/**
	 * erase all unlinked resources that are in n
	 *
	 * @param n the node to clean
	 */
	public int eraseUnlinkedResources(final JDFNode n)
	{
		return eraseUnlinked(n, false, true);
	}

	/**
	 * erase all unlinked resources that are in n
	 *
	 * @param n the node to clean
	 */
	public int eraseUnlinkedRefs(final JDFNode n)
	{
		return eraseUnlinked(n, true, false);
	}

	/**
	 * erase all unlinked resources that are in n
	 *
	 * @param n the node to clean
	 */
	public int eraseUnlinked(final JDFNode n)
	{
		return eraseUnlinked(n, true, true);
	}

	/**
	 * erase all unlinked resources that are in n
	 *
	 * @param n the node to clean
	 * @param ref
	 * @param res
	 */
	private int eraseUnlinked(final JDFNode n, final boolean ref, final boolean res)
	{
		VElement v = null;
		if (ref && res)
		{
			v = getAllUnlinked(n);
		}
		else if (ref)
		{
			v = getUnlinkedRefs(n);
		}
		else if (res)
		{
			v = getUnlinkedResources(n);
		}
		int siz = ContainerUtil.size(v);
		if (siz > 0 && v != null)
		{
			for (final KElement e : v)
			{
				e.deleteNode();
			}
			siz += eraseUnlinked(n, ref, res);
		}
		return siz;
	}

	/**
	 * collection of maps
	 *
	 * @author prosirai
	 *
	 */
	protected class LinkData
	{
		public LinkData()
		{
			super();
			resMap = new HashMap<String, KElement>();
			refMap = new ListMap<String, KElement>();
			doneSet = new HashSet<String>();
		}

		HashMap<String, KElement> resMap;
		ListMap<String, KElement> refMap;
		HashSet<String> doneSet;

		protected void clear()
		{
			resMap.clear();
			refMap.clear();
			doneSet.clear();
		}
	}

	/**
	 * the resource walker note the naming convention Walkxxx so that it is automagically instantiated by the super classes
	 *
	 * @author prosirai
	 *
	 */
	public class WalkRes extends BaseWalker
	{

		/**
		 * fills this into the factory
		 */
		public WalkRes()
		{
			super(getFactory());
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e
		 * @param trackElem
		 * @return the element to continue walking
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final JDFElement r = (JDFElement) e;
			final String id = r.getID();
			if (linkData.doneSet.contains(id))
			{
				return e;
			}
			if (linkData.refMap.containsKey(id))
			{
				linkData.doneSet.add(id);
				linkData.refMap.remove(id);
				return e;
			}
			linkData.resMap.put(id, r);
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
			if (ignoreForeign && !JDFElement.isInJDFNameSpaceStatic(toCheck))
				return false;
			return (toCheck.getParentNode() instanceof JDFResourcePool) && (toCheck instanceof JDFElement) && !(toCheck instanceof JDFComment)
					&& !(toCheck instanceof JDFGeneralID);
		}
	}

	/**
	 * the link and ref walker
	 *
	 * @author prosirai
	 *
	 */
	public class WalkRef extends BaseWalker
	{
		/**
		 * fills this into the factory
		 */
		public WalkRef()
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
			final String id = e.getAttribute(AttributeName.RREF, null, null);
			if (id == null)
			{
				return e;
			}
			if (linkData.doneSet.contains(id))
			{
				return e;
			}

			if (linkData.resMap.containsKey(id))
			{
				linkData.doneSet.add(id);
				linkData.resMap.remove(id);
				return e;
			}
			linkData.refMap.putOne(id, e);
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
			if (ignoreForeign && !JDFElement.isInJDFNameSpaceStatic(toCheck))
				return false;

			return JDFResourceLink.isResourceLink(toCheck) || (toCheck instanceof JDFRefElement);
		}

	}
}
