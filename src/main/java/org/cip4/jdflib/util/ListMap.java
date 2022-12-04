/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2022 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 *
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * KString.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * HashMap of multiple elements utility class
 *
 * @author Rainer Prosi
 * @param <key> the type used for the key
 * @param <listObject> the type used for individual elements of each vector in the map
 *
 */
public class ListMap<key, listObject> extends HashMap<key, List<listObject>>
{
	/**
	 *
	 */
	private static final long serialVersionUID = -23;
	boolean bUnique;

	/**
	 * fill this with the values in map by switching key and value
	 * 
	 * @param map
	 */
	public void fillInvertedMap(final Map<listObject, key> map)
	{
		final boolean keepUnique = bUnique; // performance - we cannot be not unique so need not check
		setUnique(false);
		if (map != null)
		{
			for (final listObject k : map.keySet())
			{
				final key k2 = map.get(k);
				if (k2 != null)
				{
					putOne(k2, k);
				}
			}
		}
		setUnique(keepUnique);
	}

	/**
	 * null constructor
	 */
	public ListMap()
	{
		super();
		setUnique(true);
	}

	/**
	 * get the value for key
	 * 
	 * @param key the search key
	 * @param i the index in the vecor matching key; if <0 count from the back of the vector
	 * @return the matching vectorObject; null if the key does not exist or i is out of range
	 */
	public listObject getOne(final Object key, int i)
	{
		final List<listObject> c = get(key);
		if (c == null)
		{
			return null;
		}

		final int n = c.size();
		if (i < 0)
		{
			i += n;
		}

		if (i < 0 || i >= n)
		{
			return null;
		}

		return c.get(i);
	}

	/**
	 * get the index of singleObject in the vector of key
	 * 
	 * @param key the key of the vector
	 * @param singleObject the object to search
	 * @return -2: no such key; -1: no value in key; else the index in the vexctor of key
	 */
	public int getIndex(final key key, final listObject singleObject)
	{
		final List<listObject> keyVector = get(key);
		if (keyVector == null)
		{
			return -2;
		}
		return keyVector.indexOf(singleObject);
	}

	/**
	 * get the size of the vector for key
	 * 
	 * @param key the key of the vector
	 * @return the size of the vector for key, 0 if no key exists
	 */
	public int size(final key key)
	{
		final List<listObject> c = get(key);
		if (c == null)
		{
			return 0;
		}
		return c.size();
	}

	/**
	 * put the value for key, ensuring uniqueness
	 * 
	 * @param key the key of the vector
	 * @param val the vector element
	 */
	public void putOne(final key key, final listObject val)
	{
		List<listObject> v = get(key);
		if (v == null)
		{
			v = new ArrayList<listObject>();
			put(key, v);
		}
		if (!bUnique || !v.contains(val))
		{
			v.add(val);
		}
	}

	/**
	 *
	 * append a map, ensuring uniqueness
	 * 
	 * @param key the key of the vector
	 * @param vVal the vector of elements
	 */
	public synchronized void appendUnique(final key key, final List<listObject> vVal)
	{
		if (vVal == null)
			return;
		final boolean keepUnique = bUnique;
		bUnique = true;
		for (final listObject val : vVal)
		{
			putOne(key, val);
		}
		bUnique = keepUnique;
	}

	/**
	 *
	 * append a VectorMap, ensuring uniqueness
	 * 
	 * @param map the map to add
	 *
	 */
	public void appendUnique(final ListMap<key, listObject> map)
	{
		if (map == null)
			return;
		final Iterator<key> it = map.keySet().iterator();
		while (it.hasNext())
		{
			final key next = it.next();
			appendUnique(next, map.get(next));
		}
	}

	/**
	 * get all values as one big vector, multiple entries are retained (see {@link ContainerUtil}.unify())
	 * 
	 * @return a vector of all values, null if empty
	 */
	public List<listObject> getAllValues()
	{
		List<listObject> v = new ArrayList<listObject>();
		final Collection<List<listObject>> c = values();
		final Iterator<List<listObject>> it = c.iterator();
		while (it.hasNext())
		{
			v = (List<listObject>) ContainerUtil.addAll(v, it.next());
		}
		return v.size() == 0 ? null : v;
	}

	/**
	 * get an inverted map that uses all entries a s keys and vice versa<br/>
	 * note that the behavior is undefined in case of multiple identical values
	 * 
	 * @return an inverted map
	 */
	public Map<listObject, key> getInvertedMap()
	{
		final HashMap<listObject, key> inverted = new HashMap<listObject, key>();
		final Set<key> keys = keySet();
		for (final key k : keys)
		{
			final List<listObject> v = get(k);
			if (v != null)
			{
				for (final listObject vo : v)
					inverted.put(vo, k);
			}
		}
		return inverted;
	}

	/**
	 * remove the value for key,also remove key if the vector is empty
	 *
	 * @param key the key of the vector
	 * @param val the vector element
	 */
	public void removeOne(final key key, final listObject val)
	{
		final List<listObject> v = get(key);
		if (v != null)
		{
			v.remove(val);
			if (v.size() == 0)
			{
				remove(key);
			}
		}
	}

	/**
	 * replace the value for key, add if oldObj==null or is not there
	 *
	 * @param key the key of the vector
	 * @param newObj the new object to set
	 * @param oldObj the old object to replace
	 */
	public void setOne(final key key, final listObject newObj, final listObject oldObj)
	{

		final List<listObject> v = get(key);
		if (v != null)
		{
			final int i = v.indexOf(oldObj);
			if (i < 0)
			{
				putOne(key, newObj);
			}
			else
			{
				v.set(i, newObj);
			}
		}
		else
		{
			putOne(key, newObj);
		}
	}

	/**
	 * insert the value for keyat position pos
	 *
	 * @param key the key of the vector
	 * @param newObj the new object to set
	 * @param pos the index in the vector, may be <0 to count from the end
	 * @throws IllegalArgumentException if pos is negative and abs(pos)>size()
	 */
	public void setOne(final key key, final listObject newObj, int pos)
	{
		List<listObject> v = get(key);
		if (v == null)
		{
			v = new ArrayList<listObject>();
			put(key, v);
		}
		if (pos < 0)
			pos += v.size();

		if (pos < 0)
		{
			throw new IllegalArgumentException("index <0 after adding size: " + pos);
		}
		ContainerUtil.ensureSize(pos + 1, v);
		v.set(pos, newObj);
	}

	/**
	 * Getter for bUnique attribute.
	 * 
	 * @return the bUnique
	 */
	public boolean isUnique()
	{
		return bUnique;
	}

	/**
	 * Setter for bUnique attribute.
	 * 
	 * @param bUnique the bUnique to set
	 */
	public void setUnique(final boolean bUnique)
	{
		this.bUnique = bUnique;
	}
}
