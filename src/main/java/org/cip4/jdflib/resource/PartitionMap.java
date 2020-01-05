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
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
package org.cip4.jdflib.resource;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.util.ContainerUtil;

class PartitionMap
{
	/**
	 *
	 * @param r
	 */
	PartitionMap(final JDFResource r)
	{
		super();
		partIDKeys = r.getPartIDKeys();
		leafMap = new LinkedHashMap<>();
		addPartitionMap(new JDFAttributeMap(), r.getResourceRoot());
	}

	private final HashMap<JDFAttributeMap, JDFResource> leafMap;
	private final VString partIDKeys;

	/**
	 * @return
	 */
	void addPartitionMap(final JDFAttributeMap parentMap, final JDFResource parent)
	{
		leafMap.put(parentMap, parent);
		final String key = partIDKeys.get(parentMap.size());
		final List<? extends KElement> v = key == null ? null : parent.getDirectPartitionArray();
		if (v != null && !v.isEmpty())
		{
			for (final KElement e : v)
			{
				final JDFResource r = (JDFResource) e;
				final JDFAttributeMap newMap = parentMap.clone();
				final String val = r.getAttribute_KElement(key);
				newMap.put(key, val);
				addPartitionMap(newMap, r);
			}
		}
	}

	/**
	 *
	 * @param arg0
	 * @return
	 */
	JDFResource get(final JDFAttributeMap arg0)
	{
		return leafMap.get(arg0);
	}

	/**
	 *
	 * @return
	 */
	Set<JDFAttributeMap> keySet()
	{
		return leafMap.keySet();
	}

	/**
	 *
	 * @return
	 */
	VString getPartIDKeys()
	{
		return partIDKeys;
	}

	/**
	 *
	 * @param m
	 * @return true if at least one key in @PartIDKeys is missing and leaves a gap
	 */
	boolean hasMissingKeys(final JDFAttributeMap m)
	{
		int s = m.size();
		if (s == 0)
			return false;
		for (final String k : partIDKeys)
		{
			if (!m.containsKey(k))
				return true;
			if (--s == 0)
				return false;
		}
		return false;
	}

	/**
	 *
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	JDFResource put(final JDFAttributeMap arg0, final JDFResource arg1)
	{
		return leafMap.put(arg0, arg1);
	}

	/**
	 *
	 * @param vPartIDKeys
	 */
	void updatePartIDKeys(final VString vPartIDKeys)
	{
		partIDKeys.appendUnique(vPartIDKeys);
	}

	/**
	 *
	 * @return
	 */
	HashMap<JDFAttributeMap, JDFResource> getLeafMap()
	{
		return leafMap;
	}

	@Override
	public String toString()
	{
		return "PartitionMap [leafMap=" + leafMap + ", partIDKeys=" + partIDKeys + "]";
	}

	/**
	 *
	 * @return
	 */
	int size()
	{
		return leafMap.size();
	}

	/**
	 *
	 * @return
	 */
	int partSize()
	{
		return partIDKeys.size();
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.HashMap#remove(java.lang.Object)
	 */
	public JDFResource remove(final JDFAttributeMap key)
	{
		return leafMap.remove(key);
	}

	/**
	 *
	 * @return
	 */
	List<JDFAttributeMap> keyVector()
	{
		return ContainerUtil.getKeyList(leafMap);
	}
}
