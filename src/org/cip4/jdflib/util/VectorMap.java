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
 * KString.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.util;

import java.util.HashMap;
import java.util.Vector;

/**
 * HashMap of multiple elements utility class
 * 
 * @author Rainer Prosi
 * @param <key> the type used for the key
 * @param <vectorObject> the type used for individual elements of each vector in the map
 * 
 */
public class VectorMap<key, vectorObject> extends HashMap<key, Vector<vectorObject>>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2180413692846276265L;

	/**
	 * null constructor
	 */
	public VectorMap()
	{
		super();
	}

	/**
	 * get the value for key
	 * @param key the search key
	 * @param i the index in the vecor matching key; if <0 count from the back of the vector
	 * @return the matching vectorObject; null if the key does not exist or i is out of range
	 */
	public vectorObject getOne(Object key, int i)
	{
		int iLocal = i;
		
		Vector<vectorObject> c = get(key);
		if (c == null)
			return null;
		
		int n = c.size();
		if (iLocal < 0)
			iLocal = n + iLocal;
		
		if (iLocal < 0 || iLocal >= n)
			return null;
		
		return c.get(iLocal);
	}

	/**
	 * get the index of singleObject in the vector of key
	 * @param key the key of the vector
	 * @param singleObject the object to search
	 * @return -2: no such key; -1: no value in key; else the index in the vexctor of key
	 */
	public int getIndex(key key, vectorObject singleObject)
	{
		Vector<vectorObject> keyVector = get(key);
		if (keyVector == null)
			return -2;
		return keyVector.indexOf(singleObject);
	}

	/**
	 * get the size of the vector for key
	 * @param key the key of the vector
	 * @return the size of the vector for key, 0 if no key exists
	 */
	public int size(key key)
	{
		Vector<vectorObject> c = get(key);
		if (c == null)
			return 0;
		return c.size();
	}

	/**
	 * put the value for key, ensuring uniqueness
	 * @param key the key of the vector
	 * @param val the vector element
	 */
	public void putOne(key key, vectorObject val)
	{
		Vector<vectorObject> v = get(key);
		if (v == null)
		{
			v = new Vector<vectorObject>();
			put(key, v);
		}
		if (!v.contains(val))
			v.add(val);
	}

	/**
	 * remove the value for key,also remove key if the vector is empty
	 *
	 * @param key the key of the vector
	 * @param val the vector element
	 */
	public void removeOne(key key, vectorObject val)
	{
		Vector<vectorObject> v = get(key);
		if (v != null)
		{
			v.remove(val);
			if (v.size() == 0)
				remove(key);
		}
	}

	/**
	 * replace the value for key, add if oldObj==null or is not there
	 * 
	 * @param key the key of the vector
	 * @param newObj the new object to set
	 * @param oldObj the old object to replace
	 */
	public void setOne(key key, vectorObject newObj, vectorObject oldObj)
	{

		Vector<vectorObject> v = get(key);
		if (v != null)
		{
			int i = v.indexOf(oldObj);
			if (i < 0)
				putOne(key, newObj);
			else
				v.set(i, newObj);
		}
		else
		{
			putOne(key, newObj);
		}
	}

	// //////////////////////////////////////////////////////////////////////////
	// //////

}
