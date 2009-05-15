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
import java.util.HashSet;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.VectorMap;

/**
 * @author prosirai finds unlinked resources - example usage of the walker classes
 */
public class UnLinkFinder extends BaseElementWalker
{
	protected LinkData ld;

	/**
	 * 
	 */
	public UnLinkFinder()
	{
		super(new BaseWalkerFactory());
		ld = this.new LinkData();
		new BaseWalker(getFactory()); // need a default walker
	}

	/**
	 * get a vector of all unlinked resources of n and its children
	 * 
	 * @param n the node to walk
	 * @return the vector of unlinked resources
	 */
	public VElement getUnlinkedResources(final JDFNode n)
	{
		ld.clear();
		walkTree(n, null);
		final Vector<KElement> toValueVector = ContainerUtil.toValueVector(ld.resMap, false);
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
		ld.clear();
		walkTree(n, null);
		final Vector<KElement> toValueVector = ld.refMap.getAllValues();
		return toValueVector == null ? null : new VElement(toValueVector);
	}

	/**
	 * get a vector of all unlinked resources of n and its children
	 * 
	 * @param n the node to walk
	 * @return the vector of unlinked resources
	 */
	public VElement getAllUnlinked(final JDFNode n)
	{
		ld.clear();
		walkTree(n, null);
		Vector<KElement> toValueVector = ContainerUtil.toValueVector(ld.resMap, false);
		final Vector<KElement> toValueVectorRef = ld.refMap.getAllValues();
		toValueVector = (Vector<KElement>) ContainerUtil.addAll(toValueVector, toValueVectorRef);
		return toValueVector == null ? null : new VElement(toValueVector);
	}

	/**
	 * erase all unlinked resources that are in n
	 * 
	 * @param n the node to clean
	 */
	public void eraseUnlinkedResources(final JDFNode n)
	{
		eraseUnlinked(n, false, true);
	}

	/**
	 * erase all unlinked resources that are in n
	 * 
	 * @param n the node to clean
	 */
	public void eraseUnlinkedRefs(final JDFNode n)
	{
		eraseUnlinked(n, true, false);
	}

	/**
	 * erase all unlinked resources that are in n
	 * 
	 * @param n the node to clean
	 */
	public void eraseUnlinked(final JDFNode n)
	{
		eraseUnlinked(n, true, true);
	}

	/**
	 * erase all unlinked resources that are in n
	 * 
	 * @param n the node to clean
	 */
	private void eraseUnlinked(final JDFNode n, final boolean ref, final boolean res)
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
		if (v != null)
		{
			final int siz = v.size();
			for (int i = 0; i < siz; i++)
			{
				v.get(i).deleteNode();
			}

			if (siz > 0)
			{
				eraseUnlinked(n, ref, res);
			}
		}
	}

	@Override
	protected BaseWalkerFactory getFactory()
	{
		return (BaseWalkerFactory) theFactory;
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
			refMap = new VectorMap<String, KElement>();
			doneSet = new HashSet<String>();
		}

		HashMap<String, KElement> resMap;
		VectorMap<String, KElement> refMap;
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
			final JDFResource r = (JDFResource) e;
			final String id = r.getID();
			if (ld.doneSet.contains(id))
			{
				return e;
			}
			if (ld.refMap.containsKey(id))
			{
				ld.doneSet.add(id);
				ld.refMap.remove(id);
				return e;
			}
			ld.resMap.put(id, r);
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
			return (toCheck instanceof JDFResource) && ((JDFResource) toCheck).isResourceRoot();
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
			if (ld.doneSet.contains(id))
			{
				return e;
			}

			if (ld.resMap.containsKey(id))
			{
				ld.doneSet.add(id);
				ld.resMap.remove(id);
				return e;
			}
			ld.refMap.putOne(id, e);
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
			return (toCheck instanceof JDFResourceLink) || (toCheck instanceof JDFRefElement);
		}

	}
}
