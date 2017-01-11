/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
 * JDFAttribute.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.StringUtil;

/**
 * This is the Java class to the mAttribute class on the C++ side.
 */
public class JDFAttributeMap extends HashMap<String, String>
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	// **************************************** Constructors
	// ****************************************
	/**
	 * constructor
	 */
	public JDFAttributeMap()
	{
		super();
	}

	/**
	 * utility constructor to construct a single value map
	 *
	 * @param key the key of the single value map
	 * @param value the value of the single value map
	 */
	public JDFAttributeMap(final String key, final String value)
	{
		super();
		put(key, value);
	}

	/**
	 * utility constructor to construct a single value map
	 *
	 * @param key the key of the single value map
	 * @param value the value of the single value map
	 */
	public JDFAttributeMap(final String key, final ValuedEnum value)
	{
		super();
		put(key, value.getName());
	}

	/**
	 * Method JDFAttributeMap clone the content of the input map
	 *
	 * @param inputMap map to clone
	 */
	public JDFAttributeMap(final Map<String, String> inputMap)
	{
		super();
		if (inputMap != null && inputMap.size() > 0)
		{
			putAll(inputMap);
		}
	}

	/**
	 * constructor: create a new map with one entry that is defined by partIDKey, value
	 *
	 * @param partIDKey the enumerated partIDKey
	 * @param value the partition key value
	 */
	public JDFAttributeMap(final ValuedEnum partIDKey, final String value)
	{
		this(partIDKey.getName(), value);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * showKeys - similar to toString but without class identifier
	 * @param sep the separator key between key-entry pairs
	 *
	 * @return String
	 */
	public String showKeys(String sep)
	{
		if (sep == null)
		{
			sep = "";
		}
		final StringBuffer sb = new StringBuffer();
		final VString vsKeys = getKeys();
		vsKeys.sort();
		final int size = vsKeys.size();
		for (int k = 0; k < size; k++)
		{
			final String strKey = vsKeys.get(k);
			final String strValue = this.get(strKey);
			sb.append(k == 0 ? "" : sep).append("(").append(strKey).append(" = ").append(strValue).append(")");
		}
		return sb.toString();
	}

	/**
	 * keys - returns an enumeration of all keys in this {@link JDFAttributeMap}
	 *
	 * @return Enumeration - the enumeration of all keys
	 * @deprecated use keyset().iterator()
	 */
	@Deprecated
	public Enumeration<String> keys()
	{
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.putAll(this);
		return ht.keys();
	}

	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFAttributeMap: {" + showKeys(JDFCoreConstants.BLANK) + " }";
	}

	/**
	 * put - maps the specified key to the specified value in this hashtable. the key MUST NOT be ""
	 *
	 * Note: This method is the equivalent to AddPair in C++
	 *
	 * @param key unique key of the pair to add. Must not be "" or null.
	 * @param value value of the pair to add. MAY be "" or null.
	 *
	 * @return the previous value of oldkey, if any
	 * <p>
	 * NOTE: It is NOT possible to enter to identical keys. If you enter a key to a Attribute Map which already exists, the value will be replaced.
	 */
	@Override
	public String put(final String key, final String value)
	{
		// check input parameter (valid or invalid)
		if (key == null || key.equals(JDFCoreConstants.EMPTYSTRING))
		{
			return null;
		}
		// put key value to hashmap. The map returns null if the key was new or an object (the old value) if the value was replaced
		return super.put(key, value);
	}

	/**
	 * put - maps the specified key to the specified value in this hashtable. Neither the key nor the value can be ""
	 *
	 * Note: This method is the equivalent to AddPair in C++
	 *
	 * @param key unique key of the pair to add. Must not be "" or null.
	 * @param value value of the pair to add. Must not be "" or null.
	 *
	 * @return boolean - false if one Inputparamter is invalid (empty String and null are not alowed)<br>
	 * true if the new Key was inserted
	 * <p>
	 * NOTE: It is NOT possible to enter to identical keys. If you enter a key to a Attribute Map which already exists, the value will be replaced.
	 */
	public String putNotNull(final Object key, final Object value)
	{
		String k1 = null;
		if (key instanceof ValuedEnum)
		{
			k1 = ((ValuedEnum) key).getName();
		}
		else if (key instanceof String)
		{
			k1 = (String) key;
		}

		String v1 = null;
		if (value instanceof ValuedEnum)
		{
			v1 = ((ValuedEnum) value).getName();
		}
		else if (value instanceof String)
		{
			v1 = (String) value;
		}
		return StringUtil.getNonEmpty(v1) == null ? null : put(k1, v1);
	}

	/**
	 * subMap - returns true if map contains subMap, all keys of submap must be in this hashtable and they must have the same value<br>
	 *
	 * if subMap is null, the function returns true if subMap contains any wildcards, then the existance of the key in this defines a match
	 *
	 * @param subMap the map to compare
	 *
	 * @return boolean - true if this map contains subMap
	 */
	public boolean subMap(final JDFAttributeMap subMap)
	{
		if (subMap == null || subMap.size() == 0) // the null map is a subset of everything
		{
			return true;
		}
		else if (subMap.size() > size())
		{
			return false;
		}

		final Set<String> mapSet = this.keySet();
		final Set<String> subMapSet = subMap.keySet();

		if (!mapSet.containsAll(subMapSet))
		{
			return false;
		}

		final Iterator<String> it = subMapSet.iterator();
		while (it.hasNext())
		{
			final String key = it.next();
			final String subVal = subMap.get(key);
			if (!KElement.isWildCard(subVal))
			{
				final String val = this.get(key);
				if (!KElement.isWildCard(val) && !val.equals(subVal))
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method subMap check if any of the maps in vMap are a subMap oft this (see subMap for details) if vMap is null, the function returns true
	 *
	 * @param vMap the vector submaps to check against
	 * @return true if this has at least one entry that vMap contains at least a submap of
	 */
	public boolean subMap(final VJDFAttributeMap vMap)
	{
		if (vMap == null || vMap.size() == 0)
		{
			return true;
		}
		for (JDFAttributeMap map : vMap)
		{
			if (subMap(map))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Method overlapMap.
	 *
	 * @param vMap the vector submaps to check against
	 * @return true if this has at least one entry that vMap contains at least a submap or supermap of
	 */
	public boolean overlapMap(final VJDFAttributeMap vMap)
	{
		if (vMap == null)
		{
			return true;
		}
		final int size = vMap.size();
		for (int i = 0; i < size; i++)
		{
			if (overlapMap(vMap.elementAt(i)))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * checks whether this attributemap matches a regexp
	 *
	 * @param key the key to match
	 * @param regExp the simplified regexp
	 * @param ignoreCase duh...
	 * @return true if the value matches the regexp
	 */
	public boolean matches(String key, String regExp, boolean ignoreCase)
	{
		String myVal = get(key);
		return ignoreCase ? StringUtil.matchesIgnoreCase(myVal, regExp) : StringUtil.matchesSimple(myVal, regExp);
	}

	/**
	 * overlapMap - identical keys must have the same values in both maps i.e submap is either a superset or a subset of this
	 *
	 * @param subMap the map to compare with <code>this</this>
	 *
	 * @return boolean - true if identical keys have the same values in both maps
	 */
	public boolean overlapMap(final JDFAttributeMap subMap)
	{
		if (subMap == null || subMap.size() == 0)
		{
			return true;
		}

		final Iterator<String> it = subMap.keySet().iterator();
		while (it.hasNext())
		{
			final String subMapKey = it.next();
			final String subMapVal = subMap.get(subMapKey);
			if (KElement.isWildCard(subMapVal))
			{
				continue;
			}

			final String val = get(subMapKey);
			if (val != null && !subMapVal.equals(val))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * orMap - put all key/value pairs which are not in this map to this map. Clear this, if both maps have the same keys with different values.
	 *
	 * @param subMap the map to compare with <code>this</this>
	 * @return
	 */
	public JDFAttributeMap orMap(final JDFAttributeMap subMap)
	{
		if (subMap == null)
			return this;

		final Iterator<String> it = subMap.keySet().iterator();
		while (it.hasNext())
		{
			final String subMapKey = it.next();
			final String subMapVal = subMap.get(subMapKey);
			final String hashTableVal = this.get(subMapKey);

			if (hashTableVal != null)
			{
				if (!hashTableVal.equals(subMapVal))
				{
					this.clear();
					break;
				}
			}
			else
			{
				this.put(subMapKey, subMapVal);
			}
		}

		return this;
	}

	/**
	 * andMap - builds a new map with identical pairs of both maps
	 *
	 * @param subMap the given map
	 */
	public void andMap(final JDFAttributeMap subMap)
	{
		Iterator<String> it = getKeys().iterator();

		while (it.hasNext())
		{
			final String key = it.next();
			String subMapVal = subMap.get(key); // if this is null, we have a mismatch
			final String thisVal = subMapVal == null ? null : get(key);
			if (subMapVal != null && !subMapVal.equals(thisVal))
			{
				subMapVal = null;
			}
			if (subMapVal == null)
			{
				remove(key);
			}
		}
	}

	/**
	 * andMap - builds a new map with identical pairs of both maps does not modify this
	 *
	 * @param subMap the given map
	 * @return the ored map, null if mismatches occurred
	 */
	public JDFAttributeMap getOrMap(final JDFAttributeMap subMap)
	{
		if (subMap == null || subMap.size() == 0)
		{
			return new JDFAttributeMap(this);
		}

		final JDFAttributeMap newMap = new JDFAttributeMap(subMap);
		final Iterator<String> it = keySet().iterator();
		while (it.hasNext())
		{
			final String key = it.next();
			final String val = get(key);
			final String subVal = subMap.get(key);
			if (subVal == null || val.equals(subVal))
			{
				newMap.put(key, val);
			}
			else
			{
				return null;
			}
		}

		return newMap.size() == 0 ? null : newMap;
	}

	/**
	 * andMap - builds a new map with identical pairs of both maps does not modify this
	 *
	 * @param subMap the given map
	 * @return the anded map, null if mismatches occurred
	 */
	public JDFAttributeMap getAndMap(final JDFAttributeMap subMap)
	{
		if (subMap == null || subMap.size() == 0)
		{
			return null;
		}
		final JDFAttributeMap newMap = new JDFAttributeMap();
		final Iterator<String> it = keySet().iterator();
		while (it.hasNext())
		{
			final String key = it.next();
			final String val = get(key);
			final String subVal = subMap.get(key);
			if (val.equals(subVal))
			{
				newMap.put(key, val);
			}
			else if (subVal != null)
			{
				return null;
			}
		}
		return newMap.size() == 0 ? null : newMap;
	}

	/**
	 * reduceKey - reduces the map, only valid map entries with the given key vector will be copied to the new hashtable; if null, clear this map
	 *
	 *
	 * @param keySet the collection of given keys
	 * @return this after removal
	 */
	public JDFAttributeMap reduceMap(final Collection<String> keySet)
	{
		if (keySet == null)
		{
			clear();
			return this;
		}
		final Iterator<String> it = getKeys().iterator();
		while (it.hasNext())
		{
			final String key = it.next();
			if (!keySet.contains(key))
			{
				remove(key);
			}
		}
		return this;
	}

	/**
	 * remove - removes the key (and its corresponding value) from this hashtable.<br>
	 * This method does nothing if the key is not in the hashtable
	 *
	 * @return Object - the value to which the key had been mapped in this hashtable, or null if the key did not have a mapping
	 */
	@Override
	public String remove(Object key)
	{

		if (key instanceof ValuedEnum)
		{
			key = ((ValuedEnum) key).getName();
		}

		return super.remove(key);
	}

	/**
	 * rename a key to the new value. newKey is only replaced if oldkey exists and is non empty
	 *
	 * @param oldKey
	 * @param newKey
	 * @return the previous value of oldkey
	 */
	public String renameKey(String oldKey, String newKey)
	{
		String val = remove(oldKey);
		String ret = null;
		if (StringUtil.getNonEmpty(val) != null)
		{
			ret = put(newKey, val);
		}
		return ret;
	}

	/**
	 *
	 *
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public String get(Object key)
	{
		if (key instanceof ValuedEnum)
		{
			key = ((ValuedEnum) key).getName();
		}

		return super.get(key);
	}

	/**
	 *
	 * convenience int getter
	 * @param key
	 * @param def
	 * @return
	 *
	 */
	public int getInt(Object key, int def)
	{
		return StringUtil.parseInt(get(key), def);
	}

	/**
	 *
	 * convenience boolean getter
	 * @param key
	 * @param def
	 * @return
	 *
	 */
	public boolean getBool(Object key, boolean def)
	{
		return StringUtil.parseBoolean(get(key), def);
	}

	/**
	 *
	 * convenience double getter
	 * @param key
	 * @param def
	 * @return
	 *
	 */
	public double getDouble(Object key, double def)
	{
		return StringUtil.parseDouble(get(key), def);
	}

	/**
	 *
	 * @param key
	 * @param value
	 * @return
	 *
	 */
	public String put(ValuedEnum key, String value)
	{
		return super.put(key.getName(), value);
	}

	/**
	 *
	 * @param key
	 * @param value
	 * @return
	 *
	 */
	public String put(String key, int value)
	{
		return put(key, StringUtil.formatInteger(value));
	}

	/**
	 *
	 * @param key
	 * @param value
	 * @return
	 *
	 */
	public String put(ValuedEnum key, int value)
	{
		return put(key.getName(), StringUtil.formatInteger(value));
	}

	/**
	 *
	 * @param key
	 * @param value
	 * @return
	 *
	 */
	public String put(String key, double value)
	{
		return put(key, StringUtil.formatDouble(value));
	}

	/**
	 *
	 * @param key
	 * @param value
	 * @return
	 *
	 */
	public String put(ValuedEnum key, double value)
	{
		return super.put(key.getName(), StringUtil.formatDouble(value));
	}

	/**
	 *
	 * @param key
	 * @param value
	 * @return
	 *
	 */
	public String put(String key, boolean value)
	{
		return put(key, value ? "true" : "false");
	}

	/**
	 *
	 * @param key
	 * @param value
	 * @return
	 *
	 */
	public String put(ValuedEnum key, boolean value)
	{
		return super.put(key.getName(), value ? "true" : "false");
	}

	/**
	 *
	 * @param key
	 * @param value
	 * @return
	 *
	 */
	public String put(String key, ValuedEnum value)
	{
		return super.put(key, value.getName());
	}

	/**
	 *
	 * @param key
	 * @param value
	 * @return
	 *
	 */
	public String put(ValuedEnum key, ValuedEnum value)
	{
		return super.put(key.getName(), value.getName());
	}

	/**
	 * getKeyIterator - returns an iterator over the elements in this set. The elements are returned in no particular order (unless this set is an instance of
	 * some class that provides a guarantee).
	 *
	 * @return Iterator - an iterator over the elements in this set
	 */
	public Iterator<String> getKeyIterator()
	{
		return keySet().iterator();
	}

	/**
	 * get the keys as a Vector,
	 *
	 * @return
	 */
	public VString getKeys()
	{
		final VString thisKeys = new VString();
		thisKeys.addAll(keySet());
		return thisKeys;
	}

	// /////////////////////////////////////////////////////////////////////

	/**
	 * remove all keys defined by set from this
	 *
	 * @param toRemove the set of keys to remove
	 * @return this map
	 */
	public JDFAttributeMap removeKeys(final Collection<String> toRemove)
	{
		if (toRemove != null)
		{
			final Iterator<String> it = toRemove.iterator();
			while (it.hasNext())
			{
				final String key = it.next();
				remove(key);
			}
		}
		return this;
	}

	/**
	 * @see java.lang.Object#clone()
	 * @return
	*/
	@Override
	public JDFAttributeMap clone()
	{
		return new JDFAttributeMap(this);
	}

	// //////////////////////////////////////////////////////////////////////////
}
