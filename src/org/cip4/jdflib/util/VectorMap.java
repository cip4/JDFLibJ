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

import java.util.HashMap;
import java.util.Vector;


/**
 * Bidirectional HashMap utility class
 * @author prosirai
 *
 */
public class VectorMap extends HashMap
{

    /**
     * 
     */
    private static final long serialVersionUID = -2180413692846276265L;

    public VectorMap()
    {
        super();
    }
    
    /**
     * get the value for key
     */
    public Object getOne(Object key, int i)
    {
        Vector c=(Vector) get(key);
        if(c==null)
            return null;
        int n=c.size();
        if(i<0)
            i=n+i;
        if(i<0 || i>=n)
            return null;
        return c.get(i);
    }
    /**
     * get the value for key
     */
    public Object getOne(Object key, Object singleObject)
    {
        Vector c=(Vector) get(key);
        if(c==null)
            return null;
        int i=c.indexOf(singleObject);
        return i<0 ? null : c.get(i); 
    }
    /**
     * get the size of the vector for key
     */
    public int size(Object key)
    {
        Vector c=(Vector) get(key);
        if(c==null)
            return 0;
        return c.size();
    }
     
    /**
     * put the value for key, ensuring uniqueness
     */
    public void putOne(Object key, Object val)
    {
        Vector v=(Vector) get(key);
        if(v==null)
        {
            v=new Vector();
            put(key,v);
        }
        if(!v.contains(val))
            v.add(val);        
    }
    /**
     * remove the value for key,also remove key if the vector is empty
     */
    public void removeOne(Object key, Object val)
    {
        Vector v=(Vector) get(key);
        if(v!=null)
        {
            v.remove(val);
            if(v.size()==0)
                remove(key);
        } 
    }
    

//////////////////////////////////////////////////////////////////////////////////

}
