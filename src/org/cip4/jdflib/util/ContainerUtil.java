/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of 
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

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

/**
 * class with utilities for containers, e.g. Vectors, sets etc.
 * also simple object utils
 * @author prosirai
 *
 */
public class ContainerUtil
{
    /**
     * create a HashSet from a List (Vector...)
     * @param l
     * @return
     */
    public static<a> Set toHashSet(List<a> l)
    {
        if(l==null)
            return null;
        Set<a> s=new HashSet<a>();
        for(int i=0;i<l.size();i++)
            s.add(l.get(i));
        return s;
    }
    /**
     * create a HashSet from an Array
     * @param l
     * @return
     */
    public static<a> Set toHashSet(a[] l)
    {
        if(l==null)
            return null;
        Set<a> s=new HashSet<a>();
        for(int i=0;i<l.length;i++)
            s.add(l[i]);
        return s;
    }
    /**
     * create a Vector from an Array
     * @param l
     * @return
     */
    public static<a> Vector toVector(a[] l)
    {
        if(l==null)
            return null;
        Vector<a> v=new Vector<a>();
        v.ensureCapacity(l.length);
        for(int i=0;i<l.length;i++)
            v.add(l[i]);
        return v;
    }
 
    /**
     * create a Vector of entry values from a map
     * 
     * @param m the map to dump to an array
     * @param sortByKey, if true, sort the entries by key
     * 
     * @return the vector
     */
    public static<a,b> Vector<b> toValueVector(Map<a,b> m, boolean sortByKey)
    {
        if(m==null)
            return null;
        final Set<Entry<a,b>> entrySet = m.entrySet();
        if(entrySet.size()==0)
            return null;
        Vector<b> v=new Vector<b>();
        v.ensureCapacity(entrySet.size());
        Iterator<Entry<a,b>> it = entrySet.iterator();
        if(sortByKey)
        {
            Vector<a> keys=new Vector<a>();
            keys.ensureCapacity(entrySet.size());
            
            while(it.hasNext())
            {
                a key = it.next().getKey();
                if(key!=null)
                    keys.add(key);
            }
            
            Collections.sort((Vector)keys);
            for(int i=0;i<keys.size();i++)
            {
                v.add(m.get(keys.get(i)));
            }
        }
        else
        {
            while(it.hasNext())
                v.add(it.next().getValue());
        }
        return v;
    }

    /**
     * return true if a equals b or both are null
     * 
     * @param a Object to compare
     * @param b Object to compare
     * @return boolean true if a equals b or both are null
     */
    public static boolean equals(Object a, Object b)
    {
        if(a==null)
            return b==null;
        if(b==null)
            return false;
        return a.equals(b);
    }
}
