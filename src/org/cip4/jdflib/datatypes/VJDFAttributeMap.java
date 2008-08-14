/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of Processes in
 * Prepress, Press and Postpress (CIP4). All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 * 
 * 3. The end-user documentation included with the redistribution, if any, must include the
 * following acknowledgment: "This product includes software developed by the The International
 * Cooperation for the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)"
 * Alternately, this acknowledgment may appear in the software itself, if and wherever such
 * third-party acknowledgments normally appear.
 * 
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in
 * Prepress, Press and Postpress" must not be used to endorse or promote products derived from this
 * software without prior written permission. For written permission, please contact info@cip4.org.
 * 
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their
 * name, without prior written permission of the CIP4 organization
 * 
 * Usage of this software in commercial products is subject to restrictions. For details please
 * consult info@cip4.org.
 * 
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN
 * PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The
 * International Cooperation for the Integration of Processes in Prepress, Press and Postpress and
 * was originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in
 * Prepress, Press and Postpress , please see <http://www.cip4.org/>.
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

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
public class VJDFAttributeMap
{
	// **************************************** Attributes
	// ******************************************
	private Vector<JDFAttributeMap> m_vec = new Vector<JDFAttributeMap>();

	// **************************************** Constructors
	// ****************************************
	/**
	 * constructor
	 */
	public VJDFAttributeMap()
	{
		// default super constructor
	}

	/**
	 * copy constructor, the map elements are cloned
	 * 
	 * @param toAdd Vector of elements to clone
	 */
	public VJDFAttributeMap(Vector toAdd)
	{
		m_vec.clear();
		for (int i = 0; i < toAdd.size(); i++)
		{
			m_vec.add(new JDFAttributeMap((JDFAttributeMap) toAdd.elementAt(i)));
		}
	}

	/**
	 * constructor from array, the map elements are not cloned
	 * 
	 * @param toAdd the array
	 */
	public VJDFAttributeMap(JDFAttributeMap[] toAdd)
	{
		m_vec.clear();
		for (int i = 0; i < toAdd.length; i++)
		{
			m_vec.add(toAdd[i]);
		}
	}

	/**
	 * copy constructor - clones the vector including the contents!
	 * 
	 * @param v the VJDFAttributeMap to copy
	 */
	public VJDFAttributeMap(VJDFAttributeMap v)
	{
		if (v != null)
		{
			clear();
			final int size = v.size();
			for (int i = 0; i < size; i++)
			{
				add(new JDFAttributeMap(v.elementAt(i)));
			}
		}
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("VJDFAttributeMap: ");
		final int nPartMaps = this.size();

		for (int i = 0; i < nPartMaps; i++)
		{
			JDFAttributeMap amParts = this.elementAt(i);
			sb.append("[" + i + "]" + amParts.toString() + "\n");
		}

		return (sb.toString());
	}

	/**
	 * Returns the vector with JDFAttributeMap elements
	 * 
	 * @return Vector - the vector with JDFAttributeMap elements
	 */
	public Vector getVector()
	{
		return m_vec;
	}

	/**
	 * sets the Vector with JDFAttributeMap elements
	 * 
	 * @param vec the Vector with JDFAttributeMap elements
	 */
	public void setVector(Vector vec)
	{
		m_vec = vec;
	}

	/**
	 * Return the size of the JDFAttributeMap vector
	 * 
	 * @return int - the size
	 */
	public int size()
	{
		return m_vec.size();
	}

	/**
	 * Return true if the JDFAttributeMap vector is empty
	 * 
	 * @return boolean - true if empty otherwise false
	 */
	public boolean isEmpty()
	{
		return m_vec.isEmpty();
	}

	/**
	 * Returns the lemeent at the given position
	 * 
	 * @param i the given position
	 * @return JDFAttributeMap - the selected element
	 */
	public JDFAttributeMap elementAt(int i)
	{
		return m_vec.elementAt(i);
	}

	/**
	 * Method removeElementAt.
	 * 
	 * @param index the position of the element to remove
	 */
	public void removeElementAt(int index)
	{
		m_vec.removeElementAt(index);
	}

	/**
	 * remove the keys specified in set and then erase any duplicates and emptys
	 * 
	 * @param set
	 */
	public void removeKeys(Collection set)
	{
		for (int i = size() - 1; i >= 0; i--)
		{
			elementAt(i).removeKeys(set);
			if (elementAt(i).isEmpty())
				removeElementAt(i);
		}
		unify();
	}

	/**
	 * Sets the element at the given position
	 * 
	 * @param obj the element to set
	 * @param i the given position
	 */
	public void setElementAt(JDFAttributeMap obj, int i)
	{
		m_vec.setElementAt(obj, i);
	}

	/**
	 * Appends the specified element to the end of this Vector
	 * 
	 * @param obj the given element
	 */
	public void add(JDFAttributeMap obj)
	{
		m_vec.add(obj);
	}

	/**
	 * Appends all the specified elements to the end of this Vector
	 * 
	 * @param obj the given element
	 */
	public void addall(VJDFAttributeMap obj)
	{
		if (obj != null)
			for (int i = 0; i < obj.size(); i++)
				m_vec.add(obj.elementAt(i));
	}

	/**
	 * Adds the specified component to the end of this vector, increasing its size by one
	 * 
	 * @param obj the given element
	 */
	public void addElement(JDFAttributeMap obj)
	{
		m_vec.addElement(obj);
	}

	/**
	 * Tests if the specified object is a component in this vector
	 * 
	 * @param obj the given JDFAttributeMap element
	 * 
	 * @return boolean - true if and only if the specified object is the same as a component in this vector, as
	 *         determined by the equals method; false otherwise
	 */
	public boolean contains(JDFAttributeMap obj)
	{
		return m_vec.contains(obj);
	}

	/**
	 * Tests wether this has a entry with the same key and value entries not more nor less keys
	 * 
	 * @param attmap the given JDFAttributeMap element
	 * @deprecated use contains
	 * @return boolean - true if and only if the specified AttributeMap has the some number of keys and values and the
	 *         same keys and values as a entry in this vector
	 */
	@Deprecated
	public boolean hasEntryWithEqualKeyValuePairs(JDFAttributeMap attmap)
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
			JDFAttributeMap map = elementAt(i);

			// only check if both have the same size

			if (map.size() == attmap.size())
			{
				// now that we found a entry with same entry counter set
				// this to true. A single wrong entry will set it to false and
				// break. If bEquals is still true after all checks, we found
				// the map
				bEquals = true;
				Set mapSet = map.keySet();
				Iterator<String> it = mapSet.iterator();
				while (it.hasNext())
				{
					String key = it.next();
					if (!attmap.containsKey(key))
					{
						bEquals = false;
						break;
					}
					String value1 = map.get(key);
					String value2 = attmap.get(key);
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
	 * Removes all of the elements from this Vector
	 */
	public void clear()
	{
		m_vec.clear();
	}

	/**
	 * 
	 * @param vKeys
	 * @deprecated use redceMap
	 */
	@Deprecated
	public void reduceKey(Vector vKeys)
	{
		VJDFAttributeMap v = new VJDFAttributeMap();

		for (int i = 0; i < m_vec.size(); i++)
		{
			JDFAttributeMap map = m_vec.elementAt(i);
			map.reduceMap(vKeys);

			if (!map.isEmpty())
			{
				v.appendUnique(map);
			}
		}

		m_vec = v.getVector();
	}

	/**
	 * reduce each JDFAttributeMap in <code>this</code> by keySet
	 * 
	 * @param keySet
	 */
	public void reduceMap(Set keySet)
	{
		VJDFAttributeMap v = new VJDFAttributeMap();

		for (int i = 0; i < m_vec.size(); i++)
		{
			JDFAttributeMap map = m_vec.elementAt(i);
			boolean bNullMap = map.isEmpty();
			map.reduceMap(keySet);

			if (bNullMap || !map.isEmpty())
			{
				v.appendUnique(map);
			}
		}
		m_vec = v.getVector();
	}

	/**
	 * 
	 * @param map map to append
	 */
	public void appendUnique(JDFAttributeMap map)
	{
		for (int i = 0; i < m_vec.size(); i++)
		{
			if ((m_vec.elementAt(i)).equals(map))
			{
				return;
			}
		}

		m_vec.addElement(map);
	}

	/**
	 * unify - make VElement unique, retaining initial order
	 */
	public void unify()
	{
		HashSet set = new HashSet();
		int size = size();
		for (int i = 0; i < size; i++)
		{
			final JDFAttributeMap e = this.elementAt(i);
			if (set.contains(e))
			{
				this.removeElementAt(i);
				i--;
				size--;
			}
			else
			{
				set.add(e);
			}
		}
	}

	/**
	 * Method appendUnique.
	 * 
	 * @param map maps to append
	 */
	public void appendUnique(VJDFAttributeMap map)
	{
		addall(map);
		unify();
	}

	/**
	 * Method overlapMap.
	 * 
	 * @param map the map to check against
	 */
	public void overlapMap(JDFAttributeMap map)
	{
		for (int i = this.size() - 1; i >= 0; i--)
		{
			if (!this.elementAt(i).overlapMap(map))
			{
				this.removeElementAt(i);
			}
		}
	}

	/**
	 * Method overlapMap.
	 * 
	 * @param map the map to check against
	 */
	public boolean overlapsMap(JDFAttributeMap map)
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
	public boolean subMap(JDFAttributeMap map)
	{
		for (int i = this.size() - 1; i >= 0; i--)
		{
			if (this.elementAt(i).subMap(map))
				return true;
		}
		return false;
	}

	/**
	 * Method subMap.
	 * 
	 * @param vMap the vector submaps to check against
	 * @return true if this has at least one entry that vMap contains at least a submap of
	 */
	public boolean subMap(VJDFAttributeMap vMap)
	{
		if (vMap == null)
			return true;
		for (int i = 0; i < vMap.size(); i++)
		{
			if (subMap(vMap.elementAt(i)))
				return true;
		}
		return false;
	}

	/**
	 * Method overlapsMap.
	 * returns true if at least one eleemnt exists that has no non-matching key value pairs
	 * 
	 * @param vMap the vector to check against
	 * @return true if this has at least one entry that vMap contains at least a submap of
	 */
	public boolean overlapsMap(VJDFAttributeMap vMap)
	{
		final int size = vMap == null ? 0 : vMap.size();
		if (size == 0)
			return true;
		for (int i = 0; i < size; i++)
		{
			if (overlapsMap(vMap.elementAt(i)))
				return true;
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
	public boolean equals(Object other)
	{
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof VJDFAttributeMap))
			return false;

		final int size = size();
		if (size != ((VJDFAttributeMap) other).size())
			return false;

		VJDFAttributeMap vOther = new VJDFAttributeMap(((VJDFAttributeMap) other).getVector());
		for (int i = 0; i < size; i++)
		{
			JDFAttributeMap map = elementAt(i);
			int index = vOther.indexOf(map);
			if (index < 0)
				return false;
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
	public int hashCode()
	{
		return HashUtil.hashCode(0, this.m_vec);
	}

	/**
	 * returns the index of a given JDFAttributeMap, -1 if not present
	 * 
	 * @param map the given JDFAttributeMap
	 */
	public int indexOf(JDFAttributeMap map)
	{
		int index = -1;
		final int size = this.size();
		for (int i = 0; i < size; i++)
		{
			if (this.elementAt(i).equals(map))
			{
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * put the key value pair into all entries
	 * 
	 * @param key the key to set - may be either String or Enum
	 * @param value the value to set - may be either String or Enum
	 */
	public void put(Object key, Object value)
	{
		for (int i = 0; i < size(); i++)
			elementAt(i).put(key, value);
	}

	/**
	 * put the key value pair into all entries; if no entries are there, create exactly one entry with the given key value pair
	 * 
	 * @param key the key to set
	 * @param value the value to set
	 */
	public void put(String key, String value)
	{
		int size = size();
		if (size == 0)
			add(new JDFAttributeMap(key, value));
		else
		{
			for (int i = 0; i < size; i++)
				elementAt(i).put(key, value);
		}
	}
}
