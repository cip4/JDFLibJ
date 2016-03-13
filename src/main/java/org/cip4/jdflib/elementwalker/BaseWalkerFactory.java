/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of
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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;

/**
 * simple implementation of the IWalkerFactory
 * 
 * @author prosirai
 * 
 */
public class BaseWalkerFactory implements IWalkerFactory
{

	public BaseWalkerFactory()
	{
		super();
		maxDepth = 0;
		vBaseWalker = new Vector<BaseWalker>();
		nameMap = new HashMap<String, BaseWalker>();
	}

	protected int maxDepth;
	protected final Vector<BaseWalker> vBaseWalker;
	private final Map<String, BaseWalker> nameMap;

	/**
	 * 
	 * get the appropriate walker for a given element
	 * @see org.cip4.jdflib.elementwalker.IWalkerFactory#getWalker(org.cip4.jdflib .core.KElement)
	 */
	@Override
	public IWalker getWalker(final KElement toCheck)
	{
		if (toCheck != null)
		{
			String name = toCheck.getLocalName();
			BaseWalker walkerByName = nameMap.get(name);
			// sometimes we have additional restrictions that make a named walker a mismatch
			if (walkerByName != null && walkerByName.matches(toCheck))
			{
				return walkerByName;
			}
		}
		for (BaseWalker w : vBaseWalker)
		{
			if (w.matches(toCheck))
			{
				return w;
			}
		}
		return null;
	}

	/**
	 * add a walker to this and make sure that abstract walkers are sorted at the end of the list so that superclass walkers are always found first called by
	 * BaseWorker so no need for external calls
	 * 
	 * @param w the walker to add
	 */
	void addWalker(final BaseWalker w)
	{
		final int d = w.getDepth();
		maxDepth = d > maxDepth ? d : maxDepth;
		VString elementNames = w.getElementNames();
		if (elementNames != null)
		{
			for (String name : elementNames)
			{
				nameMap.put(name, w);
			}
		}
		else
		{
			vBaseWalker.add(w);
			Collections.sort(vBaseWalker);
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "BasewalkerFactory " + maxDepth + " name walkers " + nameMap.keySet() + " walkers: " + vBaseWalker;
	}

	/**
	 * Getter for vBaseWalker attribute.
	 * @return the vBaseWalker
	 */
	public Vector<BaseWalker> getBaseWalkers()
	{
		return vBaseWalker;
	}
}
