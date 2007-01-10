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
 * JDFRectangleRangeList.java
 *
 */

package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.StringUtil;



public class JDFRectangleRangeList extends JDFRangeList
{
 
     /**
      * empty constructor 
      */
     public JDFRectangleRangeList()
     {
         // default super constructor
     }
     
     /**
      * copy constructor<br>
      * constructs a JDFRectangleRangeList with the given JDFRectangleRangeList
      * 
      * @param rl
      */
     public JDFRectangleRangeList(JDFRectangleRangeList rl)
     {
         rangeList = new Vector (rl.rangeList);
     }
     
     /**
      * constructs a JDFRectangleRangeList from a given string
      * 
      * @param s the given string
      * 
      * @throws DataFormatException - if the String has not a valid format
      */
     public JDFRectangleRangeList(String s) throws DataFormatException
     {
         if(s != null && !s.equals(JDFConstants.EMPTYSTRING))
         {
             setString(s);
         }
     }



     //**************************************** Methods *********************************************

     /**
     * inRange - check whether rectangle 'x' is in the rectangle range defined by 'this'
     * 
     * @param x rectangle value to test
     * @return boolean - true if 'x' is in the range defined by 'this'
     */
     public boolean inRange(JDFRectangle x)
     {
         int sz = rangeList.size();
         for (int i = 0; i < sz; i++)
         {
             if (((JDFRectangleRange)rangeList.elementAt(i)).inRange(x))
             {    
                 return true; 
             } 
         }
         return false;
     }
    

     /**
      * setString - deserialize a string Reads the string, which represents
      * JDFRectangleRangeList, and converts it into real JDFRectangleRangeList
      * 
      * @param s string to read
      * 
      * @throws DataFormatException - if the String has not a valid format
      */
     public void setString(String s) throws DataFormatException
     {
         if (s.indexOf(JDFConstants.TILDE)==0 || s.lastIndexOf(JDFConstants.TILDE)==(s.length()-1))
             throw new DataFormatException("JDFRectangleRangeList.setString: Illegal string " + s);
         String zappedWS = StringUtil.zappTokenWS(s, JDFConstants.TILDE); // converts "0 0 1 1 ~ 0 0 4 4" to "0 0 1 1~0 0 4 4"
         VString vs = new VString(zappedWS, JDFConstants.BLANK);
         rangeList.clear();
         for(int i=0, size=vs.size(); i<size; i++)
         {
             if (size-i < MAX_RECTANGLE_DIMENSION) // the last Rectangle is incomplete
                 throw new DataFormatException("JDFRectangleRangeList.setString: Illegal string " + s);
             
             StringBuffer str = new StringBuffer(100);
             str.append((String) vs.elementAt(i)).append(JDFConstants.BLANK)
                .append((String) vs.elementAt(++i)).append(JDFConstants.BLANK)
                .append((String) vs.elementAt(++i)).append(JDFConstants.BLANK);
             // the 4-th token 'tildeToken' can be with or without "~"
             String tildeToken = (String) vs.elementAt(++i);
             str.append(tildeToken);
             if ( tildeToken.indexOf(JDFConstants.TILDE)!=-1) // str - JDFRectangleRange
             {
                 if (size-i < MAX_RECTANGLE_DIMENSION) // the last RectangleRange is incomplete
                     throw new DataFormatException("JDFRectangleRangeList.setString: Illegal string " + s);
                 
                 str.append(JDFConstants.BLANK).append((String) vs.elementAt(++i))
                    .append(JDFConstants.BLANK).append((String) vs.elementAt(++i))
                    .append(JDFConstants.BLANK).append((String) vs.elementAt(++i));
             }
             try 
             {
                 JDFRectangleRange r = new JDFRectangleRange(str.toString());
                 rangeList.addElement(r);
             }
             catch (DataFormatException dfe)
             {
                 throw new DataFormatException("JDFRectangleRangeList.setString: Illegal string " + s);
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
              new JDFRectangleRangeList(s);
          }
          catch (DataFormatException e)
          {
              return false;
          }
          return true;
      }
             

      
     /**
      * append - adds an element defined by a JDFRectangleRange
      * 
      * @param x the range to append to the list
      */
     public void append(JDFRectangleRange x)
     {
         rangeList.addElement(x);
     }
      
     /**
      * append - adds an individual JDFRectangle element
      * 
      * @param x the left and right value of the range to append to the list
      */
     public void append(JDFRectangle x)
     {
         append(new JDFRectangleRange(x));
     }
       
     /**
      * append - adds an element defined by two JDFRectangles xMin~xMax
      * 
      * @param xMin the left value of the range to append to the list
      * @param xMax the right value of the range to append to the list
      */
     public void append(JDFRectangle xMin, JDFRectangle xMax)
     {
         append(new JDFRectangleRange(xMin, xMax));
     }

      


       /**
       * isOrdered - tests if 'this' is an OrderedRangeList
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
              JDFRectangleRange r = (JDFRectangleRange) rangeList.elementAt(i);
              v.addElement(r.getLeft());
              if (!r.getLeft().equals(r.getRight()))
              {
                  v.addElement(r.getRight());
              }
          }

          int n = v.size() - 1;
          if (n == 0)
              return true; // single value

          JDFRectangle first = (JDFRectangle) (v.elementAt(0));
          JDFRectangle last = (JDFRectangle) (v.elementAt(n));

          for (int j = 0; j < n; j++)
          {
              JDFRectangle value = (JDFRectangle) (v.elementAt(j));
              JDFRectangle nextvalue = (JDFRectangle) (v.elementAt(j + 1));

              if (((first.equals(last)   && value.equals(nextvalue)) || 
                   (first.isLess(last)    && value.isLessOrEqual(nextvalue)) || 
                   (first.isGreater(last) && value.isGreaterOrEqual(nextvalue))) == false) 
                   return false;
          }
          return true;
      }
      
      /**
      * isUniqueOrdered - tests if 'this' is an UniqueOrdered RangeList
      * 
      * @return boolean - true if 'this' is an UniqueOrdered RangeList
      */
     public boolean isUniqueOrdered() {
          
          int siz=rangeList.size();
          if (siz==0) {
              return false; // attempt to operate on a null element
          }
          
          VString v = new VString(); // vector of ranges
          for  (int i=0; i<siz; i++)
          {
              JDFRectangleRange r = (JDFRectangleRange) rangeList.elementAt(i);
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
          JDFRectangle first = (JDFRectangle)v.elementAt(0);
          JDFRectangle last = (JDFRectangle) v.elementAt(n);
      
          if (first.equals(last)) {
              return false;
          }
          for (int j=0; j<n; j++)
          {
              JDFRectangle value = (JDFRectangle) v.elementAt(j);
              JDFRectangle nextvalue = (JDFRectangle) v.elementAt(j+1);
              
              if (((first.isLess(last)    && value.isLess(nextvalue)) || 
                   (first.isGreater(last) && value.isGreater(nextvalue))) == false )
                    return false;
          }
          return true; 
      }
     
     
}
