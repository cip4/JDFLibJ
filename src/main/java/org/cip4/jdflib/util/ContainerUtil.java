/*
 *
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

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.cip4.jdflib.ifaces.IMatches;

/**
 * class with utilities for containers, e.g. Vectors, sets etc. <br/>
 * also simple object utilities
 * 
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * before June 18, 2009
 */
public class ContainerUtil
{
	/**
	 * create a HashSet from an enumeration
	 * @param <a> the data type of the sets
	 * @param enumeration the enumeration
	 * @return a Set created from list
	 */
	public static <a> Set<a> toHashSet(final Enumeration<a> enumeration)
	{
		if (enumeration == null)
		{
			return null;
		}

		final Set<a> s = new HashSet<a>();
		while (enumeration.hasMoreElements())
		{
			s.add(enumeration.nextElement());
		}

		return s;
	}

	/**
	 * create a HashSet from a List (Vector...)
	 * @param <a> the data type of the sets
	 * @param list the list
	 * @return a Set created from list
	 */
	public static <a> Set<a> toHashSet(final List<a> list)
	{
		if (list == null)
		{
			return null;
		}
		int size = list.size();
		final HashSet<a> s = new HashSet<a>(size + 10);

		for (int i = 0; i < size; i++)
		{
			s.add(list.get(i));
		}
		return s;
	}

	/**
	 * create a HashSet from an Array
	 * @param <a> datatype
	 * @param l the array
	 * @return a Set created from list
	 */
	public static <a> Set<a> toHashSet(final a[] l)
	{
		if (l == null)
		{
			return null;
		}
		final Set<a> s = new HashSet<a>(l.length + 10);
		for (int i = 0; i < l.length; i++)
		{
			s.add(l[i]);
		}
		return s;
	}

	/**
	 * create a Vector from an Array, skipping null elements
	 * @param <a> the type
	 * @param array the array to convert
	 * @return a Vector<a>
	 */
	public static <a> Vector<a> toVector(final a[] array)
	{
		if (array == null)
		{
			return null;
		}
		final Vector<a> v = new Vector<a>();
		v.ensureCapacity(array.length);
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] != null)
				v.add(array[i]);
		}
		return v;
	}

	/**
	 * null safe addAll
	 * 
	 * @param c1 first collection
	 * @param c2 second collection
	 * @param <a> type
	 * @return c1 with c2 added, c2 if c1==null
	 * 
	 */
	public static <a> Collection<a> addAll(final Collection<a> c1, final Collection<a> c2)
	{
		if (c1 == null)
		{
			return c2;
		}
		if (c2 == null)
		{
			return c1;
		}
		c1.addAll(c2);
		return c1;
	}

	/**
	 * return a matching element from a collection of IMatches
	 * @param <a> the data type
	 * @param c the collection to search
	 * @param obj the search key for matches
	 * @param iSkip which one to grab, may be negative in which case we count -1=last, -2=second last...
	 * @return the matching <a>
	 */
	public static <a> IMatches getMatch(final Collection<? extends IMatches> c, final a obj, final int iSkip)
	{
		int iSkipLocal = iSkip;

		if (c == null)
		{
			return null;
		}

		if (iSkipLocal < 0)
		{
			final Vector<IMatches> v = getMatches(c, obj);
			if (v == null)
			{
				return null;
			}

			iSkipLocal = v.size() + iSkipLocal;
			if (iSkipLocal < 0)
			{
				return null;
			}

			return v.get(iSkipLocal);
		}

		final Iterator<? extends IMatches> it = c.iterator();
		while (it.hasNext())
		{
			final IMatches m = it.next();
			if (m.matches(obj) && iSkipLocal-- <= 0)
			{
				return m;
			}
		}

		return null;
	}

	/**
	 * return a matching element from a collection of IMatches
	 * @param <a> the data type
	 * @param c the collection to search
	 * @param obj the search key for matches
	 * @return Vector of matching a
	 */
	public static <a> Vector<IMatches> getMatches(final Collection<? extends IMatches> c, final a obj)
	{
		if (c == null)
		{
			return null;
		}
		final Iterator<? extends IMatches> it = c.iterator();
		final Vector<IMatches> v = new Vector<IMatches>();
		while (it.hasNext())
		{
			final IMatches m = it.next();
			if (m.matches(obj))
			{
				v.add(m);
			}
		}
		return v.size() == 0 ? null : v;
	}

	/**
	 * create a Vector of entry values from a map
	 * @param <a> type of the map key
	 * @param <b> type of the map entry
	 * @param m the map to dump to an array
	 * @param sortByKey , if true, sort the entries by key
	 * @return the vector
	 */
	public static <a extends Comparable<? super a>, b> Vector<b> toValueVector(final Map<a, b> m, final boolean sortByKey)
	{
		if (!sortByKey)
		{
			return toValueVector(m);
		}

		if (m == null)
		{
			return null;
		}

		synchronized (m)
		{
			final Set<Entry<a, b>> entrySet = m.entrySet();
			if (entrySet.size() == 0)
			{
				return null;
			}

			final Vector<b> v = new Vector<b>();
			v.ensureCapacity(entrySet.size());
			final Iterator<Entry<a, b>> it = entrySet.iterator();
			final Vector<a> keys = new Vector<a>();
			keys.ensureCapacity(entrySet.size());

			while (it.hasNext())
			{
				final a key = it.next().getKey();
				if (key != null)
				{
					keys.add(key);
				}
			}

			Collections.sort(keys);
			for (int i = 0; i < keys.size(); i++)
			{
				v.add(m.get(keys.get(i)));
			}
			return v;
		}
	}

	/**
	 * create a Vector copy of entry values from a map
	 * @param <a> data type of the map key
	 * @param <b> data type of the map value
	 * @param m the map to dump to an array
	 * @return the vector
	 */
	public static <a, b> Vector<b> toValueVector(final Map<a, b> m)
	{
		if (m == null)
		{
			return null;
		}

		synchronized (m)
		{
			Vector<b> v = new Vector<b>();
			final Collection<b> values = m.values();
			if (values.size() == 0)
			{
				return null;
			}
			v.addAll(values);
			return v;
		}
	}

	/**
	 * create an inverted map with keys and values swapped.<br/>
	 *  The new values are vectors since a map may have identical values for different keys
	 * @param <a> data type of the map key
	 * @param <b> data type of the map value
	 * @param m the map to invert
	 * @return the inverted map
	 */
	public static <a, b> VectorMap<b, a> getInvertedMap(final Map<a, b> m)
	{
		if (m == null)
		{
			return null;
		}

		synchronized (m)
		{
			VectorMap<b, a> inv = new VectorMap<b, a>();
			final Collection<a> keys = m.keySet();
			if (keys.size() == 0)
			{
				return null;
			}
			for (a key : keys)
			{
				b val = m.get(key);
				if (val != null)
				{
					inv.putOne(val, key);
				}
			}
			return inv;
		}
	}

	/**
	 * create a Vector of key values from a map
	 * @param <a> data type of the map key
	 * @param m the map to dump to an array
	 * @return the vector of keys - note that this Vector goes NOT reflect changes to the map
	 */
	public static <a> Vector<a> getKeyVector(final Map<a, ?> m)
	{
		if (m == null)
		{
			return null;
		}

		synchronized (m)
		{
			final Set<a> keySet = m.keySet();
			if (keySet.size() == 0)
			{
				return null;
			}
			final Vector<a> v = new Vector<a>();
			v.ensureCapacity(keySet.size());
			final Iterator<a> it = keySet.iterator();

			while (it.hasNext())
			{
				v.add(it.next());
			}
			return v;
		}
	}

	/**
	 * return true if a equals b or both are null
	 * @param a Object to compare
	 * @param b Object to compare
	 * @return boolean true if a equals b or both are null
	 */
	public static boolean equals(final Object a, final Object b)
	{
		if (a == null)
		{
			return b == null;
		}
		if (b == null)
		{
			return false;
		}
		return a.equals(b);
	}

	/**
	 * return true if a matches b or both are null
	 * @param a Object to compare
	 * @param b Object to compare
	 * @return boolean true if a equals b or both are null
	 */
	public static boolean matches(final IMatches a, final Object b)
	{
		if (a == null)
		{
			return b == null;
		}
		if (b == null)
		{
			return false;
		}
		return a.matches(b);
	}

	/**
	 * static implementation of compare for any comparable object that gracefully handles null<br/>
	 * null is always the smallest
	 * @param c0
	 * @param c1
	 * @return -1 if c0 &lt; c1, 0 if equal, 1 if c0 &gt; c1;
	 */
	@SuppressWarnings("unchecked")
	public static int compare(final Comparable c0, final Comparable c1)
	{
		if (c0 == null)
		{
			return c1 == null ? 0 : -1;
		}
		if (c1 == null)
		{
			return 1;
		}
		return c0.compareTo(c1);
	}

	/**
	 * ensure that a collection has at least size elements and fill any newly created entries with nulls
	 * @param <a> anything - needed for the cast
	 * @param size
	 * @param coll
	 */
	public static <a> void ensureSize(final int size, final Collection<a> coll)
	{
		final int s2 = coll.size();
		if (s2 < size)
		{
			for (int i = s2; i < size; i++)
			{
				coll.add((a) null);
			}
		}
	}

	/**
	 * unify a collection while retaining the initial order (if the input collection is ordered)
	 * @param <a> the data type of the collection
	 * @param c the collection to unify
	 * @return the unified collection - always the input collection
	 */
	public static <a> Collection<a> unify(final Collection<a> c)
	{
		if (c == null || c.size() < 2)
		{
			return c;
		}
		final LinkedHashSet<a> lhsIn = new LinkedHashSet<a>(c.size());

		for (final a el : c)
		{
			if (!lhsIn.contains(el))
			{
				lhsIn.add(el);
			}
		}

		if (lhsIn.size() < c.size())
		{
			c.clear();
			c.addAll(lhsIn);
		}
		return c;
	}
}
