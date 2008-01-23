/*
 *
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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.util.HashUtil;

/**
 * This is the Java class to the mAttribute class on the C++ side.
 */
public class JDFAttributeMap implements Map
{
    //**************************************** Attributes ******************************************
    private Hashtable m_hashTable = new Hashtable();

    //**************************************** Constructors ****************************************
    /**
     * constructor
     */
    public JDFAttributeMap()
    {
        //default super constructor
    }

    /**
     * utility constructor to construct a single value map
     * @param key   the key of the single value map
     * @param value the value of the single value map
     */
    public JDFAttributeMap(String key, String value)
    {
        put(key,value);
    }
    /**
     * utility constructor to construct a single value map
     * @param key   the key of the single value map
     * @param value the value of the single value map
     */
    public JDFAttributeMap(String key, ValuedEnum value)
    {
        put(key,value.getName());
    }

    /**
     * Method JDFAttributeMap clone the content of the input map
     * @param inputMap map to clone
     */
    public JDFAttributeMap (JDFAttributeMap inputMap)
    {
        if(inputMap!=null)
            m_hashTable = (Hashtable)inputMap.m_hashTable.clone();
    }

    /**
     * constructor: create a new map with one entry that is defined by partIDKey, value
     * @param partIDKey the enumerated partIDKey
     * @param value     the partition key value
     */
    public JDFAttributeMap(EnumPartIDKey partIDKey, String value)
    {
        this(partIDKey.getName(),value);
    }

    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer ();

        sb.append ("JDFAttributeMap: {");

        VString vsKeys = this.getKeys ();

        for (int k = 0; k < vsKeys.size (); k++)
        {
            String strKey   = vsKeys.stringAt(k);
            String strValue = this.get(strKey);

            sb.append (" (" + strKey + " = " + strValue + ")");
        }
        sb.append (" }");

        return (sb.toString());
    }

    /**
     * clear - clears the hashtable
     */
    public void clear()
    {
        m_hashTable.clear();
    }

    /**
     * size - returns the number of keys in this hashtable
     *
     * @return int - the number of keys in this hashtable
     */
    public int size()
    {
        return m_hashTable.size();
    }

    /**
     * get - returns the value to which the specified key is mapped in this hashtable.
     *
     * @param key the key of the value to get
     *
     * @return String - the String to which the key is mapped in this hashtable; null if the key
     *                  is not mapped to any value in this hashtable.
     */
    public String get(String key)
    {
        return (String)m_hashTable.get(key);
    }

    /**
     * put - maps the specified key to the specified value in this hashtable. Neither the key
     * nor the value can be ""
     * 
     * Note: This method is the equivalent to AddPair in C++
     *
     * @param key   unique key of the pair to add. Must not be "" or null.
     * @param value value of the pair to add. Must not be "" or null.
     *
     * @return boolean - false if one Inputparamter is invalid (empty String and null are not alowed)<br>
     *                   true  if the new Key was inserted
     *                   <p>
     *                   NOTE: It is NOT possible to enter to identical keys. If you enter a key to a
     *                         Attribute Map which already exists, the value will be replaced. 
     */
    public boolean put(String key, String value)
    {  
        //check input parameter (valid or invalid)
        if ( key == null || key.equals(JDFConstants.EMPTYSTRING))
        {
            return false;
        }
        if(value==null)
            value="";
        //put key value to hashmap. The map returns null if the key was new
        //or an object (the old value) if the value was replaced 
        m_hashTable.put(key, value);
        return true;
    }

    /**
     * entrySet - Returns a Set view of the entries contained in this Hashtable. Each element in
     * this collection is a Map.Entry. The Set is backed by the Hashtable, so changes to the
     * Hashtable are reflected in the Set, and vice-versa. The Set supports element removal (which
     * removes the corresponding entry from the Hashtable), but not element addition.
     *
     * @return Set - the set view of the entries contained in this hashtable
     */
    public Set entrySet()
    {
        return m_hashTable.entrySet();
    }

    /**
     * subMap - returns true if map contains subMap, all keys of submap must be in this hashtable
     * and they must have the same value<br>
     * 
     * if subMap is null, the function returns true
     * if subMap contains any wildcards, then the existance of the key in this defines a match 
     *
     * @param subMap the map to compare
     *
     * @return boolean - true if this map contains subMap
     */
    public boolean subMap(JDFAttributeMap subMap)
    {
        if (subMap == null) // the null map is a subset of everything
        {
            return true;
        }

        Set mapSet    = this.keySet();
        Set subMapSet = subMap.keySet();

        if (!mapSet.containsAll(subMapSet))
        {
            return false;
        }

        Iterator it=subMapSet.iterator();
        while(it.hasNext())
        {
            String key=(String) it.next();
            String subVal=subMap.get(key);
            if(!KElement.isWildCard(subVal))
            {
                String val=this.get(key);
                if(!val.equals(subVal))
                    return false;
            }

        }
        return true;
    }

    /**
     * Method subMap check if any of the maps in vMap are a subMap oft this (see subMap for details)
     * if vMap is null, the function returns true
     * 
     * @param vMap the vector submaps to check against
     * @return true if this has at least one entry that vMap contains at least a submap of
     */
    public boolean subMap (VJDFAttributeMap vMap)
    {
        if(vMap==null)
            return true;
        for (int i=0; i<vMap.size(); i++)
        {
            if(subMap(vMap.elementAt(i)))
                return true;
        }
        return false;
    }

    /**
     * Method overlapMap.
     * @param vMap the vector submaps to check against
     * @return true if this has at least one entry that vMap contains at least a submap or supermap of
     */
    public boolean overlapMap (VJDFAttributeMap vMap)
    {
        if(vMap==null)
            return true;
        final int size = vMap.size();
        for (int i=0; i<size; i++)
        {
            if(overlapMap(vMap.elementAt(i)))
                return true;
        }
        return false;
    }

    /**
     * overlapMap - identical keys must have the same values in both maps
     * i.e submap is either a superset or a subset of this
     *
     * @param subMap the map to compare with <code>this</this>
     *
     * @return boolean - true if identical keys have the same values in both maps
     */
    public boolean overlapMap(JDFAttributeMap subMap)
    {
        if(subMap==null)
            return true;

        final Enumeration subMapEnum = subMap.keys();

        while (subMapEnum.hasMoreElements())
        {
            final String subMapKey    = (String)subMapEnum.nextElement();
            final String subMapVal    = subMap.get(subMapKey);
            if(KElement.isWildCard(subMapVal))
                continue;
            
            final String hashTableVal = this.get(subMapKey);
            if (hashTableVal != null && !hashTableVal.equals(subMapVal))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * orMap - put all key/value pairs which are not in this map to this map. Clear this, if both
     * maps have the same keys with different values.
     *
     * @param subMap the map to compare with <code>this</this>
     */
    public JDFAttributeMap orMap(JDFAttributeMap subMap)
    {
        Enumeration subMapEnum = subMap.keys();

        while (subMapEnum.hasMoreElements())
        {
            String subMapKey    = (String)subMapEnum.nextElement();
            String subMapVal    = subMap.get(subMapKey);
            String hashTableVal = this.get(subMapKey);

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
    public void andMap(JDFAttributeMap subMap)
    {
        Hashtable ht = new Hashtable();

        Enumeration subMapEnum = subMap.keys();

        while (subMapEnum.hasMoreElements())
        {
            String subMapKey    = (String)subMapEnum.nextElement();
            String subMapVal    = subMap.get(subMapKey);
            String hashTableVal = this.get(subMapKey);

            if (hashTableVal != null)
            {
                if (hashTableVal.equals(subMapVal))
                {
                    ht.put(subMapKey, subMapVal);
                }
            }
        }

        m_hashTable = ht;
    }

    /**
     * reduceKey - reduces the map, only valid map entries with the given key vector will be copied
     * to the new hashtable; if null, clear this map
     * 
     *
     * @param keySet the collection of given keys
     */
    public void reduceMap(Collection keySet)
    {
        if(keySet==null)
        {
            clear();
            return;
        }
        Iterator it=keySet.iterator();
        Hashtable ht=new Hashtable();
        while(it.hasNext())
        {
            Object key=it.next();
            if (m_hashTable.containsKey(key))
            {
                ht.put(key, m_hashTable.get(key));
            }
        }
        m_hashTable = ht;
    }

    /**
     * equals - Compares two maps, returns true if content equal, otherwise false.<br>
     *          If input is not of type JDFAttributeMap, the result of the superclasses'
     *          equals method is returned.
     *
     * @param obj JDFAttributeMap to compare with <code>this</code>
     *
     * @return boolean - true if the maps are equal, otherwise false
     */
    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (other == null || !(other instanceof JDFAttributeMap))
        {
            return false;
        }
        return this.m_hashTable.equals(((JDFAttributeMap) other).m_hashTable);
    }

    /**
     * hashCode complements equals() to fulfill the equals/hashCode contract
     * @return int
     */
    @Override
    public int hashCode()
    {
        return HashUtil.hashCode(0, this.m_hashTable);
    }

    /**
     * containsKey - looks for the given key in the hashtable, returns true if the hashtable
     * contains the key otherwise false
     *
     * @param key the key to look for
     *
     * @return boolean - true if the hashtable contains the given key otherwise false
     */
    public boolean containsKey(String key)
    {
        return m_hashTable.containsKey(key);
    }

    /**
     * isEmpty - returns true if the hashtable is empty
     *
     * @return boolean - true if the hashtable is empty
     */
    public boolean isEmpty()
    {
        return m_hashTable.isEmpty();
    }

    /**
     * getKeyIterator - returns an iterator over the elements in this set. The elements are
     * returned in no particular order (unless this set is an instance of some class that
     * provides a guarantee).
     *
     * @return Iterator - an iterator over the elements in this set
     */
    public Iterator getKeyIterator()
    {
        return m_hashTable.keySet().iterator();
    }

    /**
     * remove - removes the key (and its corresponding value) from this hashtable.<br>
     * This method does nothing if the key is not in the hashtable
     *
     * @return Object - the value to which the key had been mapped in this hashtable, or null if
     *                  the key did not have a mapping
     */
    public Object remove(Object key)
    {
        if(key instanceof ValuedEnum)
            key=((ValuedEnum)key).getName();
        return m_hashTable.remove(key);
    }

    /**
     * keys - returns an enumeration of all keys in this hashtable
     *
     * @return Enumeration - the enumeration of all keys
     */
    public Enumeration keys()
    {
        return m_hashTable.keys();
    }

    /**
     * keySet - returns a set of all keys in this hashtable
     *
     * @return Set - the set of all keys
     */
    public Set keySet()
    {
        return m_hashTable.keySet();
    }

    /* (non-Javadoc)
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object key)
    {
        return m_hashTable.containsKey(key);
    }

    /* (non-Javadoc)
     * @see java.util.Map#containsValue(java.lang.Object)
     */
    public boolean containsValue(Object value)
    {
        return m_hashTable.containsValue(value);
    }

    /* (non-Javadoc)
     * @see java.util.Map#values()
     */
    public Collection values()
    {
        return m_hashTable.values();
    }

    /* (non-Javadoc)
     * @see java.util.Map#putAll(java.util.Map)
     */
    public void putAll(Map t)
    {
        m_hashTable.putAll(t);
    }

    /* (non-Javadoc)
     * @see java.util.Map#get(java.lang.Object)
     */
    public Object get(Object key)
    {
        if(key instanceof ValuedEnum)
            key=((ValuedEnum)key).getName();
        return m_hashTable.get(key);
    }

    /* (non-Javadoc)
     * @see java.util.Map#put(K, V)
     */
    public Object put(Object key, Object value)
    {
        if(key instanceof ValuedEnum)
            key=((ValuedEnum)key).getName();
        if(value instanceof ValuedEnum)
            value=((ValuedEnum)value).getName();
        return m_hashTable.put(key, value);
    }

    /**
     * get the keys as a Vector,
     * @return
     */
    public VString getKeys()
    {
        Iterator<String> it = getKeyIterator();
        VString thisKeys = new VString();
        while(it.hasNext())
        {
            thisKeys.add(it.next());    
        }
        return thisKeys;
    }

    ///////////////////////////////////////////////////////////////////////

    /**
     * remove all keys defined by set from this
     * @param set the set of keys ot remove
     */
    public void removeKeys(Collection set)
    {
        if(set==null)
            return;
        Iterator it=set.iterator();
        while(it.hasNext())
        {
            Object key=it.next();
            remove(key);
        }        
    }

    //////////////////////////////////////////////////////////////////////////////

}
