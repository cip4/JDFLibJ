/*
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
 * VJDFAttributeMap.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.HashUtil;

/**
 *
 * Description: This class represents a vector of JDFAttributeMaps
 *
 * @author Torsten Kaehlert
 *
 * @version 1.0 2002-01-24
 *
 */
public class VJDFAttributeMap extends Vector<JDFAttributeMap>
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * constructor
	 */
	public VJDFAttributeMap()
	{
		super();
	}

	/**
	 * copy constructor, the map elements are cloned
	 *
	 * @param toAdd Vector of elements to clone
	 */
	public VJDFAttributeMap(final Vector<JDFAttributeMap> toAdd)
	{
		super();
		if (toAdd != null)
		{
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
	public VJDFAttributeMap(final JDFAttributeMap[] toAdd)
	{
		clear();
		if (toAdd != null)
		{
			for (final JDFAttributeMap map : toAdd)
			{
				add(map);
			}
		}
	}

	/**
	 * @param moreMap the single attribute map to add
	 */
	public VJDFAttributeMap(final JDFAttributeMap moreMap)
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
	public synchronized String toString()
	{
		return "VJDFAttributeMap: " + showKeys("\n", JDFConstants.SPACE);
	}

	/**
	 *
	 * @param v
	 * @return
	 */
	public static VJDFAttributeMap getNonEmpty(final VJDFAttributeMap v)
	{
		return isEmpty(v) ? null : v;
	}

	/**
	 *
	 * @param partMap
	 * @return
	 */
	public static VJDFAttributeMap getVector(final JDFAttributeMap partMap)
	{
		return JDFAttributeMap.isEmpty(partMap) ? null : new VJDFAttributeMap(partMap);
	}

	/**
	 * are we null or empty or contain only an empty JDFAttributeMap
	 *
	 * @param v
	 * @return
	 */
	public static boolean isEmpty(final VJDFAttributeMap v)
	{
		return v == null || v.isEmpty() || v.size() == 1 && JDFAttributeMap.isEmpty(v.get(0));
	}

	/**
	 * @param sepMap the separator between maps
	 * @param sepEntry the separator between map entries
	 * @return the string representation
	 */
	public String showKeys(final String sepMap, final String sepEntry)
	{
		final StringBuilder sb = new StringBuilder();
		final int nPartMaps = this.size();

		for (int i = 0; i < nPartMaps; i++)
		{
			final JDFAttributeMap amParts = this.elementAt(i);
			sb.append("[").append(i).append("]").append(amParts.showKeys(sepEntry));
			if (i + 1 < nPartMaps)
			{
				sb.append(sepMap);
			}
		}
		return sb.toString();
	}

	/**
	 * Returns the vector with JDFAttributeMap elements
	 *
	 * @return Vector - the vector with JDFAttributeMap elements
	 * @deprecated - use this
	 */
	@Deprecated
	public Vector<JDFAttributeMap> getVector()
	{
		return this;
	}

	/**
	 * @param strKey the attribute to get values from
	 * @param bUnique if true, ensure unique vector, else the vector corresponds to the vector of values
	 * @return the Vector of all values
	 */
	public VString getPartValues(final String strKey, final boolean bUnique)
	{
		final VString vsPartValues = new VString();

		for (final JDFAttributeMap map : this)
		{
			final String strValue = map.get(strKey);
			if (strValue != null)
			{
				vsPartValues.add(strValue);
			}
		}
		if (bUnique)
		{
			vsPartValues.unify();
		}
		return vsPartValues;
	}

	/**
	 * replace all maps in this with n maps that have the values strKey, vsValues
	 *
	 * @param strKey the new key to add
	 * @param vsValues String of values
	 */
	public void extendMap(final String strKey, final VString vsValues)
	{
		if (vsValues == null || vsValues.isEmpty())
		{
			return;
		}

		final VJDFAttributeMap vec = new VJDFAttributeMap();

		if (isEmpty())
		{
			add(new JDFAttributeMap());
		}
		for (final JDFAttributeMap map : this)
		{
			for (final String val : vsValues)
			{
				final JDFAttributeMap mapNew = new JDFAttributeMap(map);
				mapNew.put(strKey, val);
				vec.add(mapNew);
			}
		}

		setVector(vec);
	}

	/**
	 * andMap - builds a new vector of maps with identical pairs of both maps does not modify this
	 *
	 * @param map the given map
	 * @return the anded map, null if mismatches occurred
	 */
	public VJDFAttributeMap getAndMaps(final JDFAttributeMap map)
	{
		if (map == null)
		{
			return new VJDFAttributeMap(this);
		}
		VJDFAttributeMap newMap = new VJDFAttributeMap();
		for (int i = 0; i < size(); i++)
		{
			JDFAttributeMap map0 = get(i);
			if (map0 != null)
			{
				map0 = map0.getAndMap(map);
			}
			if (map0 != null)
			{
				newMap.add(map0);
			}
		}
		if (newMap.size() > 0)
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
	public VJDFAttributeMap getOrMaps(final JDFAttributeMap map)
	{
		if (JDFAttributeMap.isEmpty(map))
		{
			return new VJDFAttributeMap(this);
		}
		VJDFAttributeMap newMap = new VJDFAttributeMap();
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
	public VJDFAttributeMap getOrMaps(final VJDFAttributeMap vMap)
	{
		if (VJDFAttributeMap.isEmpty(vMap))
		{
			return new VJDFAttributeMap(this);
		}
		VJDFAttributeMap newMap = new VJDFAttributeMap();
		for (final JDFAttributeMap map : vMap)
		{
			final VJDFAttributeMap maps0 = getOrMaps(map);
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
	public boolean containsKey(final Object key)
	{
		boolean isFound = false;

		final int size = size();
		for (int i = 0; i < size && !isFound; i++)
		{
			final JDFAttributeMap map = elementAt(i);
			isFound = map.containsKey(key);
		}

		return (isFound);
	}

	/**
	 * sets the Vector with JDFAttributeMap elements
	 *
	 * @param vec the Vector with JDFAttributeMap elements
	 */
	public void setVector(final Vector<JDFAttributeMap> vec)
	{
		clear();
		if (vec != null)
		{
			addAll(vec);
		}
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
	 * Returns the element at the given position
	 *
	 * @param i the given position (may be<0 to count backwards)
	 * @return JDFAttributeMap - the selected element
	 */
	@Override
	public synchronized JDFAttributeMap elementAt(int i)
	{
		if (i < 0)
		{
			i += size();
		}
		return super.elementAt(i);
	}

	/**
	 * Returns the element at the given position (may be<0 to count backwards)
	 *
	 * @param i the given position
	 * @return JDFAttributeMap - the selected element
	 */
	@Override
	public synchronized JDFAttributeMap get(int i)
	{
		if (i < 0)
		{
			i += size();
		}
		return super.get(i);
	}

	/**
	 * remove the keys specified in set and then erase any duplicates and emptys
	 *
	 * @param set
	 */
	public void removeKeys(final Collection<String> set)
	{
		for (int i = size() - 1; i >= 0; i--)
		{
			elementAt(i).removeKeys(set);
			if (elementAt(i).isEmpty())
			{
				removeElementAt(i);
			}
		}
		unify();
	}

	/**
	 * remove the key specified and then erase any duplicates and emptys
	 *
	 * @param key
	 */
	public void removeKey(final String key)
	{
		for (int i = size() - 1; i >= 0; i--)
		{
			elementAt(i).remove(key);
			if (elementAt(i).isEmpty())
			{
				removeElementAt(i);
			}
		}
		unify();
	}

	/**
	 * Tests whether this has a entry with the same key and value entries not more nor less keys
	 *
	 * @param attmap the given JDFAttributeMap element
	 * @deprecated use contains
	 * @return boolean - true if and only if the specified AttributeMap has the some number of keys and values and the same keys and values as a entry in this vector
	 */
	@Deprecated
	public boolean hasEntryWithEqualKeyValuePairs(final JDFAttributeMap attmap)
	{

		boolean bEquals = false;

		for (int i = 0; i < size(); i++)
		{
			// if its the same object...ne further action needed
			if (attmap == elementAt(i))
			{
				return true;
			}

			// reset for every entry
			bEquals = false;
			final JDFAttributeMap map = elementAt(i);

			// only check if both have the same size

			if (map.size() == attmap.size())
			{
				// now that we found a entry with same entry counter set
				// this to true. A single wrong entry will set it to false and
				// break. If bEquals is still true after all checks, we found
				// the map
				bEquals = true;
				final Set<String> mapSet = map.keySet();
				final Iterator<String> it = mapSet.iterator();
				while (it.hasNext())
				{
					final String key = it.next();
					if (!attmap.containsKey(key))
					{
						bEquals = false;
						break;
					}
					final String value1 = map.get(key);
					final String value2 = attmap.get(key);
					if (!value1.equals(value2))
					{
						bEquals = false;
						break;
					}
				}
				// if bEquals is still true we found a matching map
				if (bEquals)
				{
					return bEquals;
				}
			}
		}

		return bEquals;
	}

	/**
	 *
	 * @param vKeys
	 * @deprecated use reduceMap
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Deprecated
	public void reduceKey(final Vector vKeys)
	{
		final VJDFAttributeMap v = new VJDFAttributeMap();

		for (int i = 0; i < size(); i++)
		{
			final JDFAttributeMap map = elementAt(i);
			map.reduceMap(vKeys);

			if (!map.isEmpty())
			{
				v.add(map);
			}
		}
		v.unify();
		setVector(v);
	}

	/**
	 * reduce each JDFAttributeMap in <code>this</code> by keySet (only entries in keyset are retained)
	 *
	 * @param keySet
	 */
	public void reduceMap(final Collection<String> keySet)
	{
		final VJDFAttributeMap v = new VJDFAttributeMap();

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
		setVector(v);
	}

	/**
	 * return list of all keys
	 *
	 * @return the vector of all keys
	 */
	public VString getKeys()
	{
		final VString v = new VString();
		for (final JDFAttributeMap map : this)
		{
			v.addAll(map.getKeys());
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
		final VString keys = map0.getKeys();
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
		for (final JDFAttributeMap m : this)
		{
			if (m.equals(map))
			{
				return;
			}
		}

		addElement(map);
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
	 * @param map maps to append
	 */
	public void appendUnique(final VJDFAttributeMap map)
	{
		addAll(map);
		unify();
	}

	/**
	 *
	 * deprecated legacy support
	 *
	 * @param v
	 * @deprecated use addAll
	 */
	@Deprecated
	public void addall(final VJDFAttributeMap v)
	{
		addAll(v);
	}

	/**
	 * Method overlapMap. removes all non-overlapping maps from this
	 *
	 * @param map the map to check against
	 */
	public void overlapMap(final JDFAttributeMap map)
	{
		for (int i = this.size() - 1; i >= 0; i--)
		{
			if (!elementAt(i).overlapMap(map))
			{
				removeElementAt(i);
			}
		}
	}

	/**
	 * Method overlapMap. get a copy of this with all overlapping maps the maps are NOT cloned
	 *
	 * @param map the map to check against
	 * @return a new VJDFAttributemap with the overlapping entries of this - never null
	 */
	public VJDFAttributeMap getOverlapMaps(final JDFAttributeMap map)
	{
		final VJDFAttributeMap newV = new VJDFAttributeMap();
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
	public VJDFAttributeMap getMatchingMaps(final String key, final String regExp, final boolean ignoreCase)
	{
		final VJDFAttributeMap newV = new VJDFAttributeMap();
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
	public void overlapMap(final VJDFAttributeMap vMap)
	{
		if (vMap == null)
		{
			return;
		}
		final Set<JDFAttributeMap> set = ContainerUtil.toHashSet(vMap);
		for (int i = size() - 1; i >= 0; i--)
		{
			final JDFAttributeMap attributeMap = get(i);
			if (!set.contains(attributeMap) && !attributeMap.overlapMap(vMap))
			{
				removeElementAt(i);
			}
		}
	}

	/**
	 * Method overlapMap.
	 *
	 * @param map the map to check against
	 * @return
	 */
	public boolean overlapsMap(final JDFAttributeMap map)
	{
		for (int i = size() - 1; i >= 0; i--)
		{
			if (elementAt(i).overlapMap(map))
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
		for (int i = this.size() - 1; i >= 0; i--)
		{
			if (this.elementAt(i).subMap(map))
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
	public boolean subMap(final VJDFAttributeMap vMap)
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
	public boolean overlapsMap(final VJDFAttributeMap vMap)
	{
		final int size = vMap == null ? 0 : vMap.size();
		if (size == 0)
		{
			return true;
		}

		if (vMap != null)
		{
			for (int i = 0; i < size; i++)
			{
				if (overlapsMap(vMap.elementAt(i)))
				{
					return true;
				}
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
	public synchronized boolean equals(final Object other)
	{
		if (this == other)
		{
			return true;
		}
		if (other == null)
		{
			return false;
		}
		if (!(other instanceof VJDFAttributeMap))
		{
			return false;
		}

		final int size = size();
		if (size != ((VJDFAttributeMap) other).size())
		{
			return false;
		}

		final VJDFAttributeMap vOther = new VJDFAttributeMap();
		vOther.addAll((VJDFAttributeMap) other);
		for (final JDFAttributeMap map : this)
		{
			final int index = vOther.indexOf(map);
			if (index < 0)
			{
				return false;
			}
			vOther.removeElementAt(index);
		}
		return true;
	}

	/**
	 * hashCode complements equals() to fulfill the equals/hashCode contract
	 *
	 * @return int
	 */
	@Override
	public synchronized int hashCode()
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
		final int size = size();
		if (size == 0)
		{
			add(new JDFAttributeMap(key, value));
		}
		else
		{
			for (int i = 0; i < size; i++)
			{
				elementAt(i).put(key, value);
			}
		}
	}

	/**
	 * @see java.lang.Object#clone() also clones the underlying maps
	 * @return
	 */
	@Override
	public synchronized VJDFAttributeMap clone()
	{
		return new VJDFAttributeMap(this);
	}

	/**
	 * remove all matching maps from this i.e. if map is subMap of this
	 *
	 * @param map
	 */
	public void removeMaps(final JDFAttributeMap map)
	{
		if (map == null || map.size() == 0)
		{
			clear();
		}
		for (int i = size() - 1; i >= 0; i--)
		{
			final JDFAttributeMap map0 = get(i);
			if (map0 != null && map0.subMap(map))
			{
				removeElementAt(i);
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
			for (final String key : commonMap.keySet())
			{
				put(key, commonMap.get(key));
			}
		}

	}

}
