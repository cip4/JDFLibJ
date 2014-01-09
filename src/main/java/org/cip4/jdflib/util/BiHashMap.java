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

package org.cip4.jdflib.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Bidirectional HashMap utility class 
 * 
 * @author Rainer Prosi
 * @param <a> any datatype for the key
 * @param <b> any datatype for the value
 * 
 */
public class BiHashMap<a, b> implements Map<a, b>
{

	private final HashMap<a, b> mapKey;
	private final HashMap<b, a> mapVal;

	/**
	 * 
	 */
	public BiHashMap()
	{
		mapKey = new HashMap<a, b>();
		mapVal = new HashMap<b, a>();
	}

	/**
	 * get the value for key
	 * @param key the key
	 * @return the corresponding value
	 */
	public b getValue(final a key)
	{
		return key == null ? null : mapKey.get(key);
	}

	/**
	 * @return the corresponding key iterator
	 */
	public Iterator<a> getKeyIterator()
	{
		return mapKey.keySet().iterator();
	}

	/**
	 * get the value for key
	 * @param val the value
	 * @return the corresponding key
	 */
	public a getKey(final b val)
	{
		return val == null ? null : mapVal.get(val);
	}

	/**
	 * remove key and its associated value
	 * @param key the key
	 * @return 
	 */
	@Override
	public b remove(final Object key)
	{
		if (key != null)
		{
			final b val = mapKey.get(key);
			if (val != null)
				mapVal.remove(val);
			mapKey.remove(key);
			return val;
		}
		else
		{
			return null;
		}
	}

	/**
	 * remove value and its associated key
	 * @param value the value
	 * @return 
	 */
	public a removeVal(final Object value)
	{
		if (value != null)
		{
			final a key = mapVal.get(value);
			mapVal.remove(value);
			if (key != null)
			{
				mapKey.remove(key);
			}
			return key;
		}
		else
		{
			return null;
		}
	}

	/**
	 * put the value for key<br/>
	 * both key and value must be non-null
	 * @param key the key
	 * @param val the value
	 * @return 
	 */
	@Override
	public b put(final a key, final b val)
	{
		if (key == null || val == null)
		{
			return null;
		}
		final b o = mapKey.get(key);
		if (o != null)
		{
			mapVal.remove(o);
		}
		final a o2 = mapVal.get(val);
		if (o2 != null)
		{
			mapKey.remove(o2);
		}
		mapVal.put(val, key);
		mapKey.put(key, val);
		return val;
	}

	/**
	 * get the value for key
	 */
	@Override
	public void clear()
	{
		mapVal.clear();
		mapKey.clear();
	}

	/**
	 * get a reference to the internal key map
	 * 
	 * @return
	 */
	public Map<a, b> getKeyMap()
	{
		return mapKey;
	}

	/**
	 * get a reference to the internal value map
	 * 
	 * @return
	 */
	public Map<b, a> getValMap()
	{
		return mapVal;
	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		final String s = "[BiHashMap]: " + mapKey;
		return s;
	}

	/**
	 * @see java.util.Map#containsKey(java.lang.Object)
	 * @param arg0
	 * @return
	*/
	@Override
	public boolean containsKey(Object arg0)
	{
		return mapKey.containsKey(arg0);
	}

	/**
	 * @see java.util.Map#containsValue(java.lang.Object)
	 * @param arg0
	 * @return
	*/
	@Override
	public boolean containsValue(Object arg0)
	{
		return mapKey.containsValue(arg0);
	}

	/**
	 * @see java.util.Map#entrySet()
	 * @return
	*/
	@Override
	public Set<java.util.Map.Entry<a, b>> entrySet()
	{
		return mapKey.entrySet();
	}

	/**
	 * @see java.util.Map#get(java.lang.Object)
	 * @param arg0
	 * @return
	*/
	@Override
	public b get(Object arg0)
	{
		return mapKey.get(arg0);
	}

	/**
	 * @see java.util.Map#isEmpty()
	 * @return
	*/
	@Override
	public boolean isEmpty()
	{
		return mapKey.isEmpty();
	}

	/**
	 * @see java.util.Map#keySet()
	 * @return
	*/
	@Override
	public Set<a> keySet()
	{
		return mapKey.keySet();
	}

	/**
	 * @see java.util.Map#putAll(java.util.Map)
	 * @param arg0
	*/
	@Override
	public void putAll(Map<? extends a, ? extends b> arg0)
	{
		if (arg0 == null)
			return;
		Iterator<? extends a> it = arg0.keySet().iterator();
		while (it.hasNext())
		{
			a key = it.next();
			put(key, arg0.get(key));
		}
	}

	/**
	 * @see java.util.Map#size()
	 * @return
	*/
	@Override
	public int size()
	{
		return mapKey.size();
	}

	/**
	 * @see java.util.Map#values()
	 * @return
	*/
	@Override
	public Collection<b> values()
	{
		return mapKey.values();
	}

}
