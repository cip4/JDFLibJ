/*
*
* The CIP4 Software License, Version 1.0
*
*
* Copyright (c) 2001-2004 The International Cooperation for the Integration of 
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
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 * 
 * @author Elena Skobchenko
 *
 * JDFDurationRangeList.java
 *
 */
package org.cip4.jdflib.datatypes;



import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.StringUtil;


public class JDFDurationRangeList extends JDFRangeList
{
    
    //**************************************** Constructors ****************************************
    
    
    /**
     * empty constructor
     */
    public JDFDurationRangeList()
    {
        super();
    }
    
    /**
     * constructs a JDFDurationRangeList from a given string
     *
     * @param s the given string
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFDurationRangeList(String s) throws DataFormatException
    {
        if(s != null && !s.equals(JDFConstants.EMPTYSTRING))
        {
            setString(s);
        }
    }

    /**
     * constructs a JDFDurationRangeList from the given JDFDurationRangeList 
     *
     * @param rl the given JDFDurationRangeList
     */
    public JDFDurationRangeList(JDFDurationRangeList rl)
    {
        rangeList = new Vector(rl.rangeList);
    }
    
    
    //**************************************** Methods *********************************************
        
    /**
    *  inRange - returns true if the given JDFDuration value is in one of the ranges
    *  of the range list
    *
    * @param x the given JDFDuration (duration) value to compare
    *
    * @return boolean - true if in range otherwise false
    */
    public boolean inRange(JDFDuration x)
    {
       int sz = rangeList.size();
       for (int i = 0; i < sz; i++)
       {
           JDFDurationRange r = (JDFDurationRange)rangeList.elementAt(i);
    
           if (r.inRange(x))
           {
               return true;
           }
       }
       return false;
    }
    
	
	/**
     * setString - parse the given string and set the duration range list
     *
     * @param s the given string
     *
     * @throws DataFormatException - if the String has not a valid format
     */
	public void setString( String s) throws DataFormatException
    {
        if (s.indexOf(JDFConstants.TILDE)==0 || s.lastIndexOf(JDFConstants.TILDE)==(s.length()-1))
            throw new DataFormatException("JDFDurationRangeList::SetString: Illegal string " + s);
        String zappedWS = StringUtil.zappTokenWS(s, "~");
        Vector v = StringUtil.tokenize(zappedWS, " \t", false);
        VString vs = new VString(v);
        rangeList.clear();
        for(int i = 0; i <  vs.size(); i++)
        {
            String str = (String) vs.elementAt(i);
            try 
            {
                JDFDurationRange dr = new JDFDurationRange(str); 
                rangeList.addElement(dr);
            }
            catch (DataFormatException dfe)
            {
                throw new DataFormatException("JDFDurationRangeList::SetString: Illegal string " + s);
            }
        }
    }
	
    /**
    * isValid - validate the given String
    *
    * @param s the given string
    *
    * @return boolean - false if the String has not a valid format 
    */
    public boolean isValid(String s) 
    {
        try 
        {
            new JDFDurationRangeList(s);
        }
        catch (DataFormatException e)
        {
            return false;
        }
        return true;
    }
            
    
	/**
	* add a duration range r =rMin~rMax
	* @param r the Duration range to append to the list
	*/
	public void append(JDFDurationRange r)
    {
		rangeList.addElement(r);
	}
	
	/**
	* add a duration range defined by two dates xMin~xMax
	* @param xMin the left value of the Duration range to append to the list
	* @param xMax the right value of the Duration range to append to the list
	*/
	public void append(JDFDuration xMin, JDFDuration xMax)
    {
		append(new JDFDurationRange(xMin,xMax));
	}
	
	/**
	* add an individual JDFDuration element
	* @param x the left and right value of the Duration range to append to the list
	*/
	public void append(JDFDuration x)
    {
		append(new JDFDurationRange(x,x));
	}
	
    /**
     * isOrdered - tests if 'this' is OrderedRangeList
     * 
     * @return boolean - true if 'this' is a OrdneredRangeList
     */
     public boolean isOrdered()
     {
        int siz = rangeList.size();
        if (siz == 0)
            return false; // attempt to operate on a null element

        VString v = new VString(); // vector of ranges
        for (int i = 0; i < siz; i++)
        {
            JDFDurationRange r = (JDFDurationRange) rangeList.elementAt(i);
            v.addElement(r.getLeft());
            if (!r.getLeft().equals(r.getRight()))
            {
                v.addElement(r.getRight());
            }
        }

        int n = v.size() - 1;
        if (n == 0)
            return true; // single value

        JDFDuration first = (JDFDuration) (v.elementAt(0));
        JDFDuration last = (JDFDuration) (v.elementAt(n));

        for (int j = 0; j < n; j++)
        {
            JDFDuration value = (JDFDuration) (v.elementAt(j));
            JDFDuration nextvalue = (JDFDuration) (v.elementAt(j + 1));

            if (((first.equals(last)    &&   value.equals(nextvalue)) || 
                 (first.isShorter(last) &&  (value.isShorter(nextvalue)||value.equals(nextvalue))) || 
                 (first.isLonger(last)   &&  (value.isLonger(nextvalue)||value.equals(nextvalue)))) == false) 
                 return false;
        }
        return true;
    }
    
    /**
    * isUniqueOrdered - tests if 'this' is UniqueOrdered RangeList
    * 
    * @return boolean - true if 'this' is UniqueOrdered RangeList
    */
    public boolean isUniqueOrdered() {
        
        int siz=rangeList.size();
        if (siz==0) {
            return false; // attempt to operate on a null element
        }
        
        VString v = new VString(); // vector of ranges
        for  (int i=0; i<siz; i++)
        {
            JDFDurationRange r = (JDFDurationRange) rangeList.elementAt(i);
            v.addElement(r.getLeft());
            if (!r.getLeft().equals(r.getRight())) 
            {
                v.addElement(r.getRight());
            }
        }
        
        int n=v.size()-1;
        if (n==0) {
            return true; // single value
        }
        JDFDuration first = (JDFDuration)v.elementAt(0);
        JDFDuration last = (JDFDuration) v.elementAt(n);
    
        if (first.equals(last)) {
            return false;
        }
        for (int j=0; j<n; j++)
        {
            JDFDuration value = (JDFDuration) v.elementAt(j);
            JDFDuration nextvalue = (JDFDuration) v.elementAt(j+1);
            
            if (((first.isShorter(last) && value.isShorter(nextvalue)) || 
                 (first.isLonger(last)   && value.isLonger(nextvalue))) == false )
                  return false;
        }
        return true; 
    }

}

