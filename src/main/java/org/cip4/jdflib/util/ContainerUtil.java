/*
 *
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
 *         before June 18, 2009
 */
public class ContainerUtil
{
	/**
	 * create a HashSet from an enumeration
	 *
	 * @param <A> the data type of the sets
	 * @param enumeration the enumeration
	 * @return a Set created from list
	 */
	public static <A> Set<A> toHashSet(final Enumeration<A> enumeration)
	{
		if (enumeration == null)
		{
			return null;
		}

		final Set<A> s = new HashSet<>();
		while (enumeration.hasMoreElements())
		{
			s.add(enumeration.nextElement());
		}

		return s;
	}

	/**
	 * return null if c==null or c.isEmpty=true used to zapp empty collections
	 *
	 * @param c the Collection to test
	 * @return the converted Collecion
	 */
	public static Collection<?> getNonEmpty(final Collection<?> c)
	{
		return c == null || c.isEmpty() ? null : c;
	}

	/**
	 * return null if c==null or c.isEmpty=true or c contains an empty collection used to zapp empty collections
	 *
	 * @param c the Collection to test
	 * @return the converted Collecion
	 */
	public static Collection<?> getNonEmptyCollection(final Collection<? extends Collection<?>> c)
	{
		return c == null || c.isEmpty() || (c.size() == 1 && getNonEmpty(c.iterator().next()) == null) ? null : c;
	}

	/**
	 * create a HashSet from a List (Vector...)
	 *
	 * @param <A> the data type of the sets
	 * @param list the list
	 * @return a Set created from list
	 */
	public static <A> Set<A> toHashSet(final List<A> list)
	{
		if (list == null)
		{
			return null;
		}
		final int size = list.size();
		final HashSet<A> s = new HashSet<>(size + 10);

		for (int i = 0; i < size; i++)
		{
			s.add(list.get(i));
		}
		return s;
	}

	/**
	 * create a HashSet from an Array
	 *
	 * @param <A> datatype
	 * @param l the array
	 * @return a Set created from list
	 */
	public static <A> Set<A> toHashSet(final A[] l)
	{
		if (l == null)
		{
			return null;
		}
		final Set<A> s = new HashSet<>(l.length + 10);
		for (final A element : l)
		{
			s.add(element);
		}
		return s;
	}

	/**
	 * create a Vector from an Array, skipping null elements
	 *
	 * @param <A> the type
	 * @param array the array to convert
	 * @return a Vector<a>
	 */
	public static <A> Vector<A> toVector(final A[] array)
	{
		if (array == null)
		{
			return null;
		}
		final Vector<A> v = new Vector<>();
		v.ensureCapacity(array.length);
		for (final A element : array)
		{
			if (element != null)
				v.add(element);
		}
		return v;
	}

	/**
	 * create an ArrayList from an Array, skipping null elements
	 *
	 * @param <A> the type
	 * @param array the array to convert
	 * @return a Vector<a>
	 */
	public static <A> List<A> toArrayList(final A[] array)
	{
		if (array == null)
		{
			return null;
		}
		final ArrayList<A> v = new ArrayList<>();
		v.ensureCapacity(array.length);
		for (final A element : array)
		{
			if (element != null)
				v.add(element);
		}
		return v;
	}

	/**
	 * null safe addAll
	 *
	 * @param c1 first collection
	 * @param c2 second collection
	 * @param <A> type
	 * @return c1 with c2 added, c2 if c1==null
	 *
	 */
	public static <A> Collection<A> addAll(final Collection<A> c1, final Collection<A> c2)
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
	 * checks whether at least one of a given vector of strings is contained in <code>this</code>
	 *
	 * @param others the VSTring of values to test
	 * @return true if at least one String in other is in <code>this</code>
	 */
	public static <A> boolean containsAny(final Collection<A> c, final Collection<A> others)
	{
		if (isEmpty(others))
		{
			return false;
		}
		for (final A other : others)
		{
			if (c.contains(other))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * vector of strings that is contained in <code>this</code>
	 *
	 * @param others the VString of values to test
	 * @return true if at least one String in other is in <code>this</code>
	 */
	public static <A> List<A> getOverlapping(final Collection<A> c, final Collection<A> others)
	{
		if (others == null)
		{
			return null;
		}
		final List<A> ret = new ArrayList<>();
		for (final A s : c)
		{
			if (others.contains(s))
			{
				ret.add(s);
			}
		}
		return !ret.isEmpty() ? ret : null;
	}

	/**
	 * appends all strings of an array to <code>this</code>
	 *
	 * @param strings the array of strings to append to <code>this</code>
	 */
	public static <A> void addAll(final Collection<A> c, final A[] a)
	{
		if (a != null && c != null)
		{
			for (final A aa : a)
			{
				c.add(aa);
			}
		}
	}

	/**
	 * return a matching element from a collection of IMatches
	 *
	 * @param <a> the data type
	 * @param c the collection to search
	 * @param obj the search key for matches
	 * @param iSkip which one to grab, may be negative in which case we count -1=last, -2=second last...
	 * @return the matching {@link IMatches}
	 */
	public static <a> IMatches getMatch(final Collection<? extends IMatches> c, final a obj, int iSkip)
	{
		if (c == null)
		{
			return null;
		}

		if (iSkip < 0)
		{
			final List<IMatches> v = getMatchesList(c, obj);
			if (v == null)
			{
				return null;
			}

			iSkip = v.size() + iSkip;
			if (iSkip < 0)
			{
				return null;
			}

			return v.get(iSkip);
		}

		for (final IMatches m : c)
		{
			if (m.matches(obj) && iSkip-- <= 0)
			{
				return m;
			}
		}

		return null;
	}

	/**
	 * return a matching element from a collection of IMatches
	 *
	 * @param <A> the data type
	 * @param match the matcher
	 * @param c the {@link Collection}
	 * @param iSkip which one to grab, may be negative in which case we count -1=last, -2=second last...
	 * @return the matching <a>
	 */
	public static <A> A getMatch(final IMatches match, final Collection<A> c, int iSkip)
	{
		if (c == null)
		{
			return null;
		}

		if (iSkip < 0)
		{
			final List<A> v = getMatchesList(match, c);
			if (v == null)
			{
				return null;
			}

			iSkip = v.size() + iSkip;
			if (iSkip < 0)
			{
				return null;
			}

			return v.get(iSkip);
		}

		for (final A b : c)
		{
			if (match.matches(b) && iSkip-- <= 0)
			{
				return b;
			}
		}

		return null;
	}

	/**
	 * return a matching element from a collection of IMatches
	 *
	 * @param <A> the data type
	 * @param c the collection to search
	 * @param obj the search key for matches
	 * @return Vector of matching a
	 */
	public static <A> Vector<IMatches> getMatches(final Collection<? extends IMatches> c, final A obj)
	{
		if (c == null)
		{
			return null;
		}
		final Vector<IMatches> v = new Vector<>();
		for (final IMatches m : c)
		{
			if (m.matches(obj))
			{
				v.add(m);
			}
		}
		return v.isEmpty() ? null : v;
	}

	/**
	 * return a matching element from a collection of IMatches
	 *
	 * @param <A> the data type
	 * @param c the collection to search
	 * @param obj the search key for matches
	 * @return Vector of matching a
	 */
	public static <A> List<IMatches> getMatchesList(final Collection<? extends IMatches> c, final A obj)
	{
		if (c == null)
		{
			return null;
		}
		final List<IMatches> l = new ArrayList<>();
		for (final IMatches m : c)
		{
			if (m.matches(obj))
			{
				l.add(m);
			}
		}
		return l.isEmpty() ? null : l;
	}

	/**
	 * return a matching element from a collection
	 *
	 * @param <A> the data type
	 * @param c the collection to search
	 * @param obj the matches
	 * @return Vector of matching a
	 */
	public static <A> Vector<A> getMatches(final IMatches m, final Collection<A> c)
	{
		if (c == null)
		{
			return null;
		}
		final Vector<A> v = new Vector<>();
		for (final A b : c)
		{
			if (m.matches(b))
			{
				v.add(b);
			}
		}
		return v.isEmpty() ? null : v;
	}

	/**
	 * return a matching element from a collection
	 *
	 * @param <A> the data type
	 * @param c the collection to search
	 * @param obj the matches
	 * @return Vector of matching a
	 */
	public static <A> List<A> getMatchesList(final IMatches m, final Collection<A> c)
	{
		if (c == null)
		{
			return null;
		}
		final List<A> v = new ArrayList<>();
		for (final A b : c)
		{
			if (m.matches(b))
			{
				v.add(b);
			}
		}
		return v.isEmpty() ? null : v;
	}

	/**
	 * create a Vector of entry values from a map
	 *
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
			if (entrySet.isEmpty())
			{
				return null;
			}

			final Vector<b> v = new Vector<>();
			v.ensureCapacity(entrySet.size());
			final Iterator<Entry<a, b>> it = entrySet.iterator();
			final Vector<a> keys = new Vector<>();
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
	 * create a Vector of entry values from a map
	 *
	 * @param <A> type of the map key
	 * @param <B> type of the map entry
	 * @param m the map to dump to an array
	 * @param sortByKey , if true, sort the entries by key
	 * @return the vector
	 */
	public static <A extends Comparable<? super A>, B> List<B> toArrayList(final Map<A, B> m, final boolean sortByKey)
	{
		if (!sortByKey)
		{
			return toArrayList(m);
		}

		if (m == null)
		{
			return null;
		}

		synchronized (m)
		{
			final Set<Entry<A, B>> entrySet = m.entrySet();
			if (entrySet.isEmpty())
			{
				return null;
			}

			final ArrayList<B> v = new ArrayList<>();
			v.ensureCapacity(entrySet.size());
			final Iterator<Entry<A, B>> it = entrySet.iterator();
			final ArrayList<A> keys = new ArrayList<>();
			keys.ensureCapacity(entrySet.size());

			while (it.hasNext())
			{
				final A key = it.next().getKey();
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
	 *
	 * @param <A> data type of the map key
	 * @param <B> data type of the map value
	 * @param m the map to dump to an array
	 * @return the vector
	 */
	public static <A, B> Vector<B> toValueVector(final Map<A, B> m)
	{
		if (m == null)
		{
			return null;
		}

		synchronized (m)
		{
			final Vector<B> v = new Vector<>();
			final Collection<B> values = m.values();
			if (values.isEmpty())
			{
				return null;
			}
			v.addAll(values);
			return v;
		}
	}

	/**
	 * create a Vector copy of entry values from a map
	 *
	 * @param <A> data type of the map key
	 * @param <B> data type of the map value
	 * @param m the map to dump to an array
	 * @return the vector
	 */
	public static <A, B> List<B> toArrayList(final Map<A, B> m)
	{
		if (m == null)
		{
			return null;
		}

		synchronized (m)
		{
			final ArrayList<B> v = new ArrayList<>();
			final Collection<B> values = m.values();
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
	 * The new values are vectors since a map may have identical values for different keys
	 *
	 * @param <A> data type of the map key
	 * @param <B> data type of the map value
	 * @param m the map to invert
	 * @return the inverted map
	 * @deprecated
	 */
	@Deprecated
	public static <A, B> VectorMap<B, A> getInvertedMap(final Map<A, B> m)
	{
		if (m == null)
		{
			return null;
		}

		synchronized (m)
		{
			final VectorMap<B, A> inv = new VectorMap<>();
			final Collection<A> keys = m.keySet();
			if (keys.size() == 0)
			{
				return null;
			}
			for (final A key : keys)
			{
				final B val = m.get(key);
				if (val != null)
				{
					inv.putOne(val, key);
				}
			}
			return inv;
		}
	}

	/**
	 * AppendUnique - append a string but ignore multiple entries
	 *
	 * @param a the A to append, if null nothing is added
	 */
	public static <A> void appendUnique(final Collection<A> c, final A a)
	{
		if (a != null && !c.contains(a))
		{
			c.add(a);
		}
	}

	/**
	 *
	 * @param l
	 * @param i
	 * @return
	 */
	public static <A> int index(final List<A> l, int i)
	{
		if (l == null)
			return -1;
		final int size = l.size();
		if (i < 0)
		{
			i += size;
		}
		return i >= size ? -2 : i;
	}

	/**
	 *
	 * @param l
	 * @param i
	 * @return
	 */
	public static <A> A get(final List<A> l, int i)
	{
		i = index(l, i);
		return i >= 0 ? l.get(i) : null;
	}

	/**
	 * remove but also implementing the usual neg number syntax
	 *
	 * @param index if 0, or positive count from front, else if negative from back
	 * @see java.util.Vector#remove(int)
	 */
	public static <A> A remove(final List<A> l, int i)
	{
		i = index(l, i);
		return i >= 0 ? l.remove(i) : null;
	}

	/**
	 * create an inverted map with keys and values swapped.<br/>
	 * The new values are vectors since a map may have identical values for different keys
	 *
	 * @param <A> data type of the map key
	 * @param <B> data type of the map value
	 * @param m the map to invert
	 * @return the inverted map
	 */
	public static <A, B> ListMap<B, A> getInvertedListMap(final Map<A, B> m)
	{
		if (m == null)
		{
			return null;
		}

		synchronized (m)
		{
			final ListMap<B, A> inv = new ListMap<>();
			final Collection<A> keys = m.keySet();
			if (keys.size() == 0)
			{
				return null;
			}
			for (final A key : keys)
			{
				final B val = m.get(key);
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
	 *
	 * @param <A> data type of the map key
	 * @param m the map to dump to an array
	 * @return the vector of keys - note that this Vector goes NOT reflect changes to the map
	 * @deprecated
	 */
	@Deprecated
	public static <A> Vector<A> getKeyVector(final Map<A, ?> m)
	{
		if (m == null)
		{
			return null;
		}

		synchronized (m)
		{
			final Set<A> keySet = m.keySet();
			if (keySet.isEmpty())
			{
				return null;
			}
			final Vector<A> v = new Vector<>();
			v.ensureCapacity(keySet.size());
			v.addAll(keySet);
			return v;
		}
	}

	/**
	 * return true if a equals b or both are null
	 *
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
	 *
	 * @param a Object to compare
	 * @param b Object to compare
	 * @return boolean true if a matches b or both are null
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
	 * return true if a matches b or one of a or b is null
	 *
	 * @param a Object to compare
	 * @param b Object to compare
	 * @return boolean true if a matches b or both are null
	 */
	public static boolean matchesExisting(final IMatches a, final Object b)
	{
		if (a == null || b == null)
		{
			return true;
		}
		return a.matches(b);
	}

	/**
	 * static implementation of compare for any comparable object that gracefully handles null<br/>
	 * null is always the smallest
	 *
	 * @param c0
	 * @param c1
	 * @return -1 if c0 &lt; c1, 0 if equal, 1 if c0 &gt; c1;
	 */
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
	 *
	 * @param <A> anything - needed for the cast
	 * @param size
	 * @param coll
	 */
	public static <A> void ensureSize(final int size, final Collection<A> coll)
	{
		final int s2 = coll.size();
		if (s2 < size)
		{
			for (int i = s2; i < size; i++)
			{
				coll.add((A) null);
			}
		}
	}

	/**
	 * unify a collection while retaining the initial order (if the input collection is ordered)
	 *
	 * @param <A> the data type of the collection
	 * @param c the collection to unify
	 * @return the unified collection - always the input collection
	 */
	public static <A> Collection<A> unify(final Collection<A> c)
	{
		if (size(c) < 2)
		{
			return c;
		}
		final LinkedHashSet<A> lhsIn = new LinkedHashSet<>(c.size());

		for (final A el : c)
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

	/**
	 * unify a collection while retaining the initial order (if the input collection is ordered)
	 *
	 * @param <A> the data type of the collection
	 * @param c the collection to unify
	 * @return the unified collection - always the input collection
	 */
	public static <A extends IMatches> Collection<A> unifyMatches(final Collection<A> c)
	{
		if (c == null || c.size() < 2)
		{
			return c;
		}
		final ArrayList<A> al = new ArrayList<>(c.size());

		for (final A el : c)
		{
			final List<IMatches> mm = getMatchesList(al, el);
			if (mm == null)
			{
				al.add(el);
			}
		}
		if (al.size() < c.size())
		{
			final List<A> v2 = new ArrayList<>();
			for (int i = al.size() - 1; i >= 0; i--)
			{
				final List<IMatches> mm = getMatchesList(v2, al.get(i));
				if (mm == null)
				{
					v2.add(al.get(i));
				}
			}
			c.clear();
			c.addAll(v2);
		}
		return c;
	}

	/**
	 * @param c the collection to check
	 * @return 0 for null or size
	 */
	public static int size(final Collection<?> c)
	{
		return c == null ? 0 : c.size();
	}

	/**
	 *
	 * @param c
	 * @return
	 */
	public static boolean isEmpty(final Collection<?> c)
	{
		return c == null ? true : c.isEmpty();
	}

	public static <A> Collection<A> getKeyArray(final Map<A, ?> m)
	{
		if (m == null)
		{
			return null;
		}

		final Set<A> keySet = m.keySet();
		if (keySet.isEmpty())
		{
			return null;
		}
		final ArrayList<A> v = new ArrayList<>();
		v.ensureCapacity(keySet.size());
		v.addAll(keySet);
		return v;
	}

	public static <A> List<A> getKeyList(final Map<A, ?> m)
	{
		return (List<A>) getKeyArray(m);
	}
}
