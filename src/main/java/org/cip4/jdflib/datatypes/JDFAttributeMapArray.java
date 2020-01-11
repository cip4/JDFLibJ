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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * VJDFAttributeMap.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.HashUtil;

/**
 *
 * Description: This class represents a vector of JDFAttributeMaps
 *
 *
 */
public class JDFAttributeMapArray extends ArrayList<JDFAttributeMap>
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1234321L;

	/**
	 * constructor
	 */
	public JDFAttributeMapArray()
	{
		super();
	}

	/**
	 * copy constructor, the map elements are cloned
	 *
	 * @param toAdd Vector of elements to clone
	 */
	public JDFAttributeMapArray(final Collection<JDFAttributeMap> toAdd)
	{
		super();
		if (toAdd != null)
		{
			ensureCapacity(toAdd.size());
			for (final JDFAttributeMap map : toAdd)
			{
				add(map.clone());
			}
		}
	}

	/**
	 * constructor from array, the map elements are not cloned
	 *
	 * @param toAdd the array
	 */
	public JDFAttributeMapArray(final JDFAttributeMap[] toAdd)
	{
		super();
		ContainerUtil.addAll(this, toAdd);
	}

	/**
	 * @param moreMap the single attribute map to add
	 */
	public JDFAttributeMapArray(final JDFAttributeMap moreMap)
	{
		this();
		if (moreMap != null)
		{
			add(moreMap);
		}
	}

	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFAttributeMapArray: " + showKeys("\n", JDFConstants.SPACE);
	}

	/**
	 *
	 * @param v
	 * @return
	 */
	public static JDFAttributeMapArray getNonEmpty(final JDFAttributeMapArray v)
	{
		return isEmpty(v) ? null : v;
	}

	/**
	 *
	 * @param partMap
	 * @return
	 */
	public static JDFAttributeMapArray getArray(final JDFAttributeMap partMap)
	{
		return JDFAttributeMap.isEmpty(partMap) ? null : new JDFAttributeMapArray(partMap);
	}

	/**
	 * are we null or empty or contain only an empty JDFAttributeMap
	 *
	 * @param v
	 * @return
	 */
	public static boolean isEmpty(final Collection<JDFAttributeMap> v)
	{
		return v == null || v.isEmpty() || v.size() == 1 && JDFAttributeMap.isEmpty(v.iterator().next());
	}

	/**
	 * @param sepMap the separator between maps
	 * @param sepEntry the separator between map entries
	 * @return the string representation
	 */
	public String showKeys(final String sepMap, final String sepEntry)
	{
		final StringBuilder builder = new StringBuilder();
		final int nPartMaps = size();

		for (int n = 0; n < nPartMaps; n++)
		{
			final JDFAttributeMap amParts = this.get(n);
			builder.append("[").append(n).append("]").append(amParts.showKeys(sepEntry));
			if (n + 1 < nPartMaps)
			{
				builder.append(sepMap);
			}
		}
		return builder.toString();
	}

	/**
	 * @param strKey the attribute to get values from
	 * @param bUnique if true, ensure unique vector, else the vector corresponds to the vector of values
	 * @return the Vector of all values
	 */
	public List<String> getPartValues(final String strKey, final boolean bUnique)
	{
		final StringArray array = new StringArray();

		for (final JDFAttributeMap myMap : this)
		{
			final String strValue = myMap.get(strKey);
			if (strValue != null)
			{
				array.add(strValue);
			}
		}
		if (bUnique)
		{
			array.unify();
		}
		return array;
	}

	/**
	 * andMap - builds a new vector of maps with identical pairs of both maps does not modify this
	 *
	 * @param map the given map
	 * @return the anded map, null if mismatches occurred
	 */
	public JDFAttributeMapArray getAndMaps(final JDFAttributeMap map)
	{
		if (JDFAttributeMap.isEmpty(map))
		{
			return new JDFAttributeMapArray(this);
		}
		JDFAttributeMapArray newMap = new JDFAttributeMapArray();
		for (JDFAttributeMap map0 : this)
		{
			if (map0 != null)
			{
				map0 = map0.getAndMap(map);
				if (map0 != null)
				{
					newMap.add(map0);
				}
			}
		}
		if (!newMap.isEmpty())
		{
			newMap.unify();
		}
		else
		{
			newMap = null;
		}
		return newMap;
	}

	/**
	 * andMap - builds a new vector of maps with identical pairs of both maps does not modify this
	 *
	 * @param map the given map
	 * @return the anded map, null if mismatches occurred
	 */
	public JDFAttributeMapArray getOrMaps(final JDFAttributeMap map)
	{
		if (JDFAttributeMap.isEmpty(map))
		{
			return new JDFAttributeMapArray(this);
		}
		JDFAttributeMapArray newMap = new JDFAttributeMapArray();
		for (JDFAttributeMap map0 : this)
		{
			map0 = map0.getOrMap(map);
			if (map0 != null)
			{
				newMap.add(map0);
			}
		}
		if (newMap.isEmpty())
		{
			newMap = null;
		}
		else
		{
			newMap.unify();
		}
		return newMap;
	}

	/**
	 * andMap - builds a new vector of maps with identical pairs of both maps does not modify this
	 *
	 * @param map the given map
	 * @return the anded map, null if mismatches occurred
	 */
	public JDFAttributeMapArray getOrMaps(final JDFAttributeMapArray vMap)
	{
		if (JDFAttributeMapArray.isEmpty(vMap))
		{
			return new JDFAttributeMapArray(this);
		}
		JDFAttributeMapArray newMap = new JDFAttributeMapArray();
		for (final JDFAttributeMap map : vMap)
		{
			final JDFAttributeMapArray maps0 = getOrMaps(map);
			if (maps0 != null)
			{
				newMap.addAll(maps0);
			}
		}
		if (newMap.isEmpty())
		{
			newMap = null;
		}
		else
		{
			newMap.unify();
		}
		return newMap;
	}

	/**
	 * @see java.util.Map#containsKey(java.lang.Object)
	 * @param key the key to check for
	 * @return true if any of the maps contains key
	 */
	public boolean containsKey(final String key)
	{
		for (final JDFAttributeMap map : this)
		{
			if (map.containsKey(key))
				return true;
		}
		return false;
	}

	/**
	 * Return the maximum size size of a JDFAttributeMap in this
	 *
	 * @return int - the size
	 */
	public int maxSize()
	{
		int maxSize = 0;
		for (final JDFAttributeMap map : this)
		{
			if (map != null && map.size() > maxSize)
			{
				maxSize = map.size();
			}
		}
		return maxSize;
	}

	/**
	 * Return the minimum size size of a JDFAttributeMap in this
	 *
	 * @return int - the size
	 */
	public int minSize()
	{
		int minSize = 9999999;
		if (size() > 0)
		{
			for (final JDFAttributeMap map : this)
			{
				if (map != null && map.size() < minSize)
				{
					minSize = map.size();
					if (minSize == 0)
					{
						break;
					}
				}
			}
		}
		else
		{
			minSize = 0;
		}
		return minSize;
	}

	/**
	 * Returns the element at the given position (may be<0 to count backwards)
	 *
	 * @param i the given position
	 * @return JDFAttributeMap - the selected element
	 */
	@Override
	public JDFAttributeMap get(final int i)
	{
		final int ii = ContainerUtil.index(this, i);
		return ii >= 0 ? super.get(ii) : null;
	}

	/**
	 * remove the keys specified in set and then erase any duplicates and emptys
	 *
	 * @param set
	 */
	public JDFAttributeMapArray removeKeys(final Collection<String> set)
	{
		for (int i = size() - 1; i >= 0; i--)
		{
			get(i).removeKeys(set);
			if (get(i).isEmpty())
			{
				remove(i);
			}
		}
		unify();
		return this;
	}

	/**
	 * remove the key specified and then erase any duplicates and emptys
	 *
	 * @param key
	 */
	public JDFAttributeMapArray removeKey(final String key)
	{
		for (int i = size() - 1; i >= 0; i--)
		{
			get(i).remove(key);
			if (get(i).isEmpty())
			{
				remove(i);
			}
		}
		unify();
		return this;
	}

	/**
	 * reduce each JDFAttributeMap in <code>this</code> by keySet (only entries in keyset are retained)
	 *
	 * @param keySet
	 */
	public void reduceMap(final Collection<String> keySet)
	{
		final JDFAttributeMapArray v = new JDFAttributeMapArray();

		for (final JDFAttributeMap map : this)
		{
			final boolean bNullMap = map.isEmpty();
			map.reduceMap(keySet);

			if (bNullMap || !map.isEmpty())
			{
				v.add(map);
			}
		}
		v.unify();
		clear();
		addAll(v);
	}

	/**
	 * return list of all keys
	 *
	 * @return the vector of all keys
	 */
	public StringArray getKeys()
	{
		final StringArray v = new StringArray();
		for (final JDFAttributeMap map : this)
		{
			v.addAll(map.getKeyList());
		}
		v.unify();
		return v;
	}

	/**
	 * return the map that is common to all elements of this. All keys exist and have the same value
	 *
	 * @return the vector of all keys
	 */
	public JDFAttributeMap getCommonMap()
	{
		final JDFAttributeMap newMap = new JDFAttributeMap();

		if (isEmpty())
		{
			return null;
		}
		else if (size() == 1)
		{
			return get(0).clone();
		}

		final JDFAttributeMap map0 = get(0);
		final StringArray keys = map0.getKeyList();
		for (final String key : keys)
		{
			String val0 = map0.get(key);
			if (val0 != null)
			{
				for (final JDFAttributeMap map : this)
				{
					final String val = map.get(key);
					if (!val0.equals(val))
					{
						val0 = null;
						break;
					}
				}
				if (val0 != null)
				{
					newMap.put(key, val0);
				}
			}
		}

		return newMap;
	}

	/**
	 *
	 * @param map map to append
	 */
	public void appendUnique(final JDFAttributeMap map)
	{
		ContainerUtil.appendUnique(this, map);
	}

	/**
	 * unify - make VElement unique, retaining initial order
	 */
	public void unify()
	{
		ContainerUtil.unify(this);
	}

	/**
	 * Method appendUnique.
	 *
	 * @param maps maps to append
	 */
	public void appendUnique(final JDFAttributeMapArray maps)
	{
		ContainerUtil.appendUnique(this, maps);
	}

	/**
	 * Method overlapMap. removes all non-overlapping maps from this
	 *
	 * @param map the map to check against
	 * @return
	 */
	public JDFAttributeMapArray overlapMap(final JDFAttributeMap map)
	{
		for (int i = this.size() - 1; i >= 0; i--)
		{
			if (!get(i).overlapMap(map))
			{
				remove(i);
			}
		}
		return this;
	}

	/**
	 * Method overlapMap. get a copy of this with all overlapping maps the maps are NOT cloned
	 *
	 * @param map the map to check against
	 * @return a new VJDFAttributemap with the overlapping entries of this - never null
	 */
	public JDFAttributeMapArray getOverlapMaps(final JDFAttributeMap map)
	{
		final JDFAttributeMapArray newV = new JDFAttributeMapArray();
		for (final JDFAttributeMap m : this)
		{
			if (m.overlapMap(map))
			{
				newV.add(m);
			}
		}
		return newV;
	}

	/**
	 * Method overlapMap. get a copy of this with all matching maps the maps are NOT cloned
	 *
	 * @param key the key to match
	 * @param regExp the simplified regexp
	 * @param ignoreCase duh...
	 * @return a new VJDFAttributemap with the matching entries of this - never null (may be safely daisy-chained)
	 */
	public JDFAttributeMapArray getMatchingMaps(final String key, final String regExp, final boolean ignoreCase)
	{
		final JDFAttributeMapArray newV = new JDFAttributeMapArray();
		for (final JDFAttributeMap m : this)
		{
			if (m.matches(key, regExp, ignoreCase))
			{
				newV.add(m);
			}
		}
		return newV;
	}

	/**
	 * Method overlapMap. only entries that contain at least one matching map entry are retained
	 *
	 * @param vMap the map to check against
	 */
	public JDFAttributeMapArray overlapMap(final JDFAttributeMapArray vMap)
	{
		if (vMap != null)
		{
			final Set<JDFAttributeMap> set = ContainerUtil.toHashSet(vMap);
			for (int i = size() - 1; i >= 0; i--)
			{
				final JDFAttributeMap attributeMap = get(i);
				if (!set.contains(attributeMap) && !attributeMap.overlapsMap(vMap))
				{
					remove(i);
				}
			}
		}
		return this;
	}

	/**
	 * Method overlapMap.
	 *
	 * @param map the map to check against
	 * @return
	 */
	public boolean overlapsMap(final JDFAttributeMap map)
	{
		for (final JDFAttributeMap m : this)
		{
			if (m.overlapMap(map))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Method subMap.
	 *
	 * @param map the submap to check against
	 * @return true if this has at least one entry that subMap is a submap of
	 */
	public boolean subMap(final JDFAttributeMap map)
	{
		for (final JDFAttributeMap m : this)
		{
			if (m.subMap(map))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Method subMap.
	 *
	 * @param vMap the vector submaps to check against
	 * @return true if this has at least one entry that vMap contains at least a submap of
	 */
	public boolean subMap(final JDFAttributeMapArray vMap)
	{
		if (isEmpty(vMap))
		{
			return true;
		}
		for (final JDFAttributeMap map : vMap)
		{
			if (subMap(map))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Method overlapsMap. returns true if at least one element exists that has no non-matching key value pairs
	 *
	 * @param vMap the vector to check against
	 * @return true if this has at least one entry that vMap contains at least a submap of
	 */
	public boolean overlapsMap(final JDFAttributeMapArray vMap)
	{
		if (JDFAttributeMapArray.isEmpty(vMap))
		{
			return true;
		}

		for (final JDFAttributeMap m : vMap)
		{
			if (overlapsMap(m))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * equals - Compares two map vectors, returns true if content equal regardless of element order, otherwise false.<br>
	 * If input is not of type VJDFAttributeMap, result of superclasses equals method is returned.
	 *
	 * @param other in this case VJDFAttributeMap to compare
	 *
	 * @return boolean - true if the maps are equal, otherwise false
	 */
	@Override
	public boolean equals(final Object other)
	{
		if (this == other)
		{
			return true;
		}
		if (other == null)
		{
			return false;
		}
		if (!(other instanceof JDFAttributeMapArray))
		{
			return false;
		}

		final int size = size();
		if (size != ((JDFAttributeMapArray) other).size())
		{
			return false;
		}

		final Set<JDFAttributeMap> sOther = new HashSet<>((JDFAttributeMapArray) other);
		for (final JDFAttributeMap map : this)
		{
			if (!sOther.remove(map))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * hashCode complements equals() to fulfill the equals/hashCode contract
	 *
	 * @return int
	 */
	@Override
	public int hashCode()
	{
		return HashUtil.hashCode(0, this);
	}

	/**
	 * put the key value pair into all entries
	 *
	 * @param key the key to set - may be either String or Enum
	 * @param value the value to set - may be either String or Enum
	 * @throws IllegalArgumentException if key or value have the wrong type
	 */
	public void put(final Object key, final Object value)
	{
		String s1 = null;
		if (key instanceof String)
		{
			s1 = (String) key;
		}
		else if (key instanceof ValuedEnum)
		{
			s1 = ((ValuedEnum) key).getName();
		}

		String s2 = null;
		if (value instanceof String)
		{
			s2 = (String) value;
		}
		else if (value instanceof ValuedEnum)
		{
			s2 = ((ValuedEnum) value).getName();
		}

		if (s1 != null && s2 != null)
		{
			put(s1, s2);
		}
		else
		{
			throw new IllegalArgumentException("wrong key and value types in put: " + key + " " + value);
		}
	}

	/**
	 * put the key value pair into all entries; if no entries are there, create exactly one entry with the given key value pair
	 *
	 * @param key the key to set
	 * @param value the value to set
	 */
	public void put(final String key, final String value)
	{
		if (isEmpty())
		{
			add(new JDFAttributeMap(key, value));
		}
		else
		{
			for (final JDFAttributeMap m : this)
			{
				m.put(key, value);
			}
		}
	}

	/**
	 * @see java.lang.Object#clone() also clones the underlying maps
	 * @return
	 */
	@Override
	public JDFAttributeMapArray clone()
	{
		return new JDFAttributeMapArray(this);
	}

	/**
	 * remove all matching maps from this i.e. if map is subMap of this
	 *
	 * @param map
	 */
	public void removeMaps(final JDFAttributeMap map)
	{
		if (JDFAttributeMap.isEmpty(map))
		{
			clear();
		}
		else
		{
			for (int i = size() - 1; i >= 0; i--)
			{
				final JDFAttributeMap map0 = get(i);
				if (map0 != null && map0.subMap(map))
				{
					remove(i);
				}
			}
		}
	}

	/**
	 * put the value of all keys into every existing map
	 *
	 * @param commonMap
	 */
	public void put(final JDFAttributeMap commonMap)
	{
		if (commonMap != null)
		{
			for (final Entry<String, String> entry : commonMap.entrySet())
			{
				put(entry.getKey(), entry.getValue());
			}
		}
	}

}
