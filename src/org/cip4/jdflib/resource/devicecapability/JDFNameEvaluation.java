/*
*
* The CIP4 Software License, Version 1.0
*
*
* Copyright (c) 2001-2006 The International Cooperation for the Integration of 
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
 * JDFNameEvaluation.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.StringUtil;

public class JDFNameEvaluation extends JDFEvaluation
{
    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
    static 
    {
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.REGEXP,    0x33333333, AttributeInfo.EnumAttributeType.RegExp, null, null);
        atrInfoTable[1]  = new AtrInfoTable(AttributeName.VALUELIST, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
    }

    protected AttributeInfo getTheAttributeInfo() 
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }

    /**
     * constructor for JDFNameEvaluation
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFNameEvaluation(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * constructor for JDFNameEvaluation
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFNameEvaluation(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * constructor for JDFNameEvaluation
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFNameEvaluation(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    //**************************************** Methods *********************************************
    /**
     * toString
     * @return String
     */
    public String toString()
    {
        return "JDFNameEvaluation[ --> " + super.toString() + " ]";
    }
        
    /* ******************************************************
    // Attribute getter/ setter
    **************************************************************** */

    /**
     * get attribute <code>ValueList</code>
     * @return VString - the attribute
     */
    public VString getValueList()
    {
        Vector v = StringUtil.tokenize(AttributeName.VALUELIST, JDFConstants.BLANK, false);
        VString vs = new VString(v);
        return vs;
    }

    /**
     * set attribute <code>ValueList</code>
     * @param vs the value to set the attribute to
     */
    public void setValueList(VString vs)
    {
        setAttribute(AttributeName.VALUELIST, StringUtil.setvString(vs," ",null,null), null);
    }

    /**
     * set attribute <code>RegExp</code>
     * @param value the value to set the attribute to
     */
    public void setRegExp(String value)
    {
        setAttribute(AttributeName.REGEXP, value);
    }

    /**
     * get attribute <code>RegExp</code>
     * @return String - the value of the attribute
     */
    public String getRegExp()
    {
        return getAttribute(AttributeName.REGEXP, null, JDFConstants.EMPTYSTRING);
    }

     
    /* ******************************************************
    // FitsValue Methods
    **************************************************************** */
    
    /**
     * fitsValue - checks whether <code>value</code> matches the testlists 
     * specified for this Evaluation
     *
     * @param value value to test
     * @return boolean - true, if <code>value</code> matches the testlists or 
     * if testlists are not specified
     */
    public boolean fitsValue(String value)
    {
        if (fitsListType(value))
            return (fitsValueList(value) && fitsRegExp(value));
        return false;
    }
    
    /**
     * fitsValueList - checks whether <code>value</code> matches 
     * the AllowedValueList or the PresentValueList specified for this Evaluation
     *
     * @param value nmtokens to test
     * @return boolean - true, if <code>value</code> matches <code>valuelist</code> or 
     * if AllowedValueList is not specified
     */
    private final boolean fitsValueList(String value)
    {
        if (!hasAttribute(AttributeName.VALUELIST))
            return true; // ValueList is not specified
        
        VString vs = new VString(value, null);

        VString list = getValueList();

        EnumListType listType = getListType();
        
        if (listType.equals(EnumListType.CompleteList))
        {
            return fitsCompleteList(vs,list);
        }
        else if (listType.equals(EnumListType.CompleteOrderedList))
        { 
            return fitsCompleteOrderedList(vs,list);
        }
        else if (listType.equals(EnumListType.ContainedList))
        {
            return fitsContainedList(vs,list);
        }

        int v_size = vs.size();
        int l_size = list.size();
        
        for (int i=0; i<v_size; i++) // test every token, that 'value' consists of
        {
            boolean bFound = false;
            for (int j=0; j<l_size; j++)
            {
                String str_i = (String)vs.elementAt(i);
                String str_j = (String)list.elementAt(j);
                if (str_i.compareTo(str_j)==0) {
                    bFound=true;  
                    break;
                }
            }
            if (!bFound)
                return false; // no such value in the 'list'
        }
        return true;        
    }
    
    /**
     * fitsRegExp - checks whether <code>str</code> matches the RegExp
     * specified for this Evaluation
     *
     * @param str string to test
     * @return boolean - true, if <code>str</code> matches the RegExp or if no RegExp is specified
     */
    private final boolean fitsRegExp(String str) 
    {
        if (!hasAttribute(AttributeName.REGEXP))
            return true; // if RegExp is not specified return true 
        return StringUtil.matches(str,getRegExp());
    }
    
    /**
     * fitsListType - checks whether <code>value</code> matches the value of the 
     * ListType attribute specified for this Evaluation
     *
     * @param value value to test
     * @return boolean - true, if <code>value</code> matches the specified value of ListType
     */
    private final boolean fitsListType(String value)
    {
        if (!StringUtil.isNMTOKENS(value,false)) 
            return false;
        
        EnumListType listType = getListType();
        if(listType==null)
            return true;

        if (listType.equals(EnumListType.SingleValue) || listType.equals(EnumListType.getEnum(0))) 
        {// default ListType = SingleValue
            return StringUtil.isNMTOKEN(value);
        }    
        else if (listType.equals(EnumListType.List) ||
                 listType.equals(EnumListType.Span) ||
                 listType.equals(EnumListType.CompleteList)  || // complete or not - tested in fitsValueList
                 listType.equals(EnumListType.CompleteOrderedList) || // tested in fitsValueList
                 listType.equals(EnumListType.ContainedList))  // tested in fitsValueList)
        {
            return true; 
        }
        else if (listType.equals(EnumListType.UniqueList))
        {
            VString v = new VString(value, null);
            return (isUnique(v));
        }
        else 
        {
            throw new JDFException ("JDFNameEvaluation.fitsListType illegal ListType attribute"); 
        }
    }
    
        
    /**
     * fitsCompleteList - tests whether <code>value</code> matches the given ValueList
     * (ListType=CompleteList)
     *
     * @param value value to test
     * @param list  ValueList
     * @return boolean - true, if <code>value</code> matches the ValueList
     */
    private final boolean fitsCompleteList(VString value, VString list)
    {
        int v_size=value.size();
        int l_size=list.size();
        
        if (v_size!=l_size) 
            return false; 
        
        if (!isUnique(value)) 
            return false;
     
        VString valueList = new VString(value);

        boolean bFound;
        for (int i=l_size-1; i>=0; i--)
        {
            bFound = false;
            for (int j=valueList.size()-1; j>=0; j--)       
            {
                if (list.elementAt(i).equals(valueList.elementAt(j)))
                {
                    valueList.remove(j);
                    bFound = true;
                    break;
                }
            }
            if (!bFound) 
            {
                return false;
            }
        }
        return true;
    }

    
    /**
     * fitsCompleteOrderedList - tests whether <code>value</code> matches the given ValueList
     * (ListType=CompleteOrderedList)
     *
     * @param value value to test
     * @param list  ValueList
     * @return boolean - true, if <code>value</code> matches the ValueList
     */
     private final boolean fitsCompleteOrderedList(VString value, VString list)
     {
         int v_size = value.size();
         int l_size = list.size();
         
         if ( v_size != l_size ) 
              return false; 

         if (!isUnique(value)) 
             return false;

         for (int i=0; i<l_size; i++)
         {
             if (!list.elementAt(i).equals(value.elementAt(i))) 
             {
                 return false;
             }
         }
         return true;        
     }
     
     /**
      * fitsContainedList - tests whether <code>value</code> matches the given ValueList
      * (ListType=ContainedList)
      *
      * @param value value to test
      * @param list  ValueList
      * @return boolean - true, if <code>value</code> matches the ValueList
      */
     private final boolean fitsContainedList(VString value, VString list)
     {
         int v_size = value.size();
         int l_size = list.size();
         
         for (int i=0; i<v_size; i++)
         {
             for (int j=0; j<l_size; j++)
             {
                 if (value.elementAt(i).equals(list.elementAt(j))) 
                 {
                     return true;
                 }
             }
         }
         return false;
     }
    
    /**
     * isUnique - tests whether <code>value</code> string has unique tokens only
     *
     * @param value value to test
     * @return boolean - true, if <code>value</code> has unique tokens only
     */ 
    private final boolean isUnique(VString v)
    {
        int size = v.size();
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                if ( j!=i ) 
                {
                    String si =(String)v.elementAt(i);
                    String sj =(String)v.elementAt(j);
                    if (si.compareTo(sj)==0) 
                        return false;
                }
            }
        }
        return true;
    }
    
    

}