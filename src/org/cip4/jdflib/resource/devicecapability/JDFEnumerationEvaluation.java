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
 * JDFEnumerationEvaluation.java
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
import org.w3c.dom.DOMException;

public class JDFEnumerationEvaluation extends JDFEvaluation
{
    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
    static 
    {
        // TODO Vorbelegung mit null - unsauber
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.VALUELIST, 0x33333333, AttributeInfo.EnumAttributeType.enumerations, null, null);
    }

    protected AttributeInfo getTheAttributeInfo() 
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    /**
     * constructor for JDFEnumerationEvaluation
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFEnumerationEvaluation(CoreDocumentImpl myOwnerDocument, String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * constructor for JDFEnumerationEvaluation
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFEnumerationEvaluation(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * constructor for JDFEnumerationEvaluation
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFEnumerationEvaluation(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
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
        return "JDFEnumerationEvaluation[ --> " + super.toString() + " ]";
    }
    
    /* ******************************************************
	// Attribute getter / setter
	**************************************************************** */

    public VString getValueList()
    {
		VString vs = new VString();
        Vector v = StringUtil.tokenize(getAttribute(AttributeName.VALUELIST), JDFConstants.BLANK, false);
        vs.addAll(v);
        return vs;
	}
	
    public void setValueList( VString vs)
    {
		setAttribute(AttributeName.VALUELIST, vs.getString(" ",null,null), null);
	}

    
	/* ******************************************************
    // FitsValue Methods
    **************************************************************** */
    
    /**
     * fitsValue - tests, if the defined 'value' matches testlists, 
     * specified for this Evaluation
     *
     * @param String value - value to test
     * @return boolean - true, if 'value' matches testlists or 
     * if testlists are not specified
     */
    public boolean fitsValue(String value)
    {
        if (!fitsListType(value))
            return false;
        return (fitsValueList(value));
    }
    
    /**
     * fitsValueList - tests, if the defined 'value' matches 
     * the AllowedValueList or the PresentValueList, specified for this Evaluation
     *
     * @param String value - nmtokens to test
     * @param EnumFitsValue valuelist - Switches between AllowedValueList and PresentValueList.
     * @return boolean - true, if 'value' matches valuelist or if AllowedValueList is not specified
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
     * fitsListType - tests, if the defined 'value' matches value of 
     * ListType attribute, specified for this Evaluation
     *
     * @param String value - value to test
     * @return boolean - true, if 'value' matches specified value of ListType
     */
    private final boolean fitsListType(String value)
    {
        if (!StringUtil.isNMTOKENS(value,false)) 
            return false;
        
        EnumListType listType = getListType();

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
            throw new JDFException ("JDFEnumerationEvaluation.fitsListType illegal ListType attribute"); 
        }
    }
    
    
    /**
     * fitsContainedList - tests for the case, when ListType=CompleteList,
     * does the defined 'value' match ValueList, specified for this Evaluation
     *
     * @param VString value - value to test
     * @param VString list - specified ValueList
     * @return boolean - true, if 'value' matches testlist
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
     * fitsCompleteOrderedList - tests for the case, when ListType=CompleteOrderedList,
     * does the defined 'value' match ValueList, specified for this Evaluation
     *
     * @param VString value - value to test
     * @param VString list - specified ValueList
     * @return boolean - true, if 'value' matches testlist
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
      * fitsContainedList - tests for the case, when ListType=ContainedList,
      * does the defined 'value' match ValueList, specified for this Evaluation
      *
      * @param VString value - value to test
      * @param VString list -  specified ValueList
      * @return boolean - true, if 'value' matches testlist
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
     * isUnique - tests, if 'value' string has only unique tokens
     *
     * @param VString value - value to test
     * @return boolean - true, if 'value' has only unique tokens
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