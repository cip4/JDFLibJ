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
 * JDFBooleanEvaluation.java
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


public class JDFBooleanEvaluation extends JDFEvaluation
{
    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
    static 
    {
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.VALUELIST, 0x33333333, AttributeInfo.EnumAttributeType.enumerations, EnumBoolean.getEnum(0),null);
    }

    protected AttributeInfo getTheAttributeInfo() 
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }

    /**
     * Constructor for JDFBooleanEvaluation
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFBooleanEvaluation(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFBooleanEvaluation
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFBooleanEvaluation(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFBooleanEvaluation
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFBooleanEvaluation(
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
        return "JDFBooleanEvaluation[ --> " + super.toString() + " ]";
    }
     
    
    
    /* ******************************************************
    // Attribute Getter / Setter
    **************************************************************** */
 /**
  * getValueList
  * @return Vector of Boolean objects
  */  
    public Vector getValueList()
    {
        String s = getAttribute(AttributeName.VALUELIST, null, null);
        Vector v = StringUtil.tokenize(s, JDFConstants.BLANK, false);
        Vector vRet = new Vector();
        for (int i = 0; i < v.size(); i++)
        {
            String s2 = (String) v.elementAt(i);
            if (s2.equalsIgnoreCase(JDFConstants.TRUE))
            {
                vRet.add(Boolean.valueOf(true));
            }
            else if (s2.equalsIgnoreCase(JDFConstants.FALSE))
            {
                vRet.add(Boolean.valueOf(false));
            }

        }

        return vRet;
    }
    
    /**
     * set ValueList
     * @param value vector of Boolean values
     */   
       public void setValueList(Vector value)
       {
           String s = JDFConstants.EMPTYSTRING;
           for (int i = 0; i < value.size(); i++)
           {
               Boolean b = (Boolean) value.elementAt(i);
               if (b.booleanValue())
               {
                   s += JDFConstants.TRUE;
               }
               else
               {
                   s += JDFConstants.FALSE;
               }
               if (i > 0)
                   s += JDFConstants.BLANK;
           }
           setAttribute(AttributeName.VALUELIST, s, null);

       }
       
       /**
        * convenience method for single valued boolean lists
        * @param value the single boolean to set ValueList to
        */   
       public void setValueList(boolean value)
       {
           setAttribute(AttributeName.VALUELIST, value, null);
       }
    
      
    /* ******************************************************
    // FitsValue Methods
    **************************************************************** */
    
    
    /**
     * fitsValue - tests, if the defined value matches ValueList, 
     * specified for this Evaluation
     *
     * @param valueStr value to test
     * @return boolean - true, if <code>value</code> matches testlists or 
     * if ValueList is not specified
     */
    public final boolean fitsValue(String valueStr)
    {
        if (fitsListType(valueStr))
        {
            VString value = new VString(valueStr, null);

            for (int i=0; i<value.size(); i++)
            {
                if (!fitsValueList((String)value.elementAt(i)))
                    return false;
            }
            return true; // if we are here a whole 'valueStr' fits
        }
        return false;
    }
    
    
    /**
     * fitsValueList - tests, if the defined 'value' matches ValueList,
     *
     * @param value token to test
     * @return boolean - true, if <code>value</code> matches valuelist or 
     * if ValueList is not specified
     */
    private final boolean fitsValueList(String value) 
    {
        if (!hasAttribute(AttributeName.VALUELIST))
             return true;
        if(!StringUtil.isBoolean(value))
            return false;
       
        Vector v = getValueList();
                      
        for (int i=0, size=v.size(); i < size; i++) 
        {
            boolean a = "true".equals(value);
            boolean b = ((Boolean) v.elementAt(i)).booleanValue();
            if (a==b)
                return true; // we have found it
        }
        return false;
    }
    
    /**
     * fitsListType - tests, if the defined 'value' matches value of 
     * ListType attribute, specified for this Evaluation
     *
     * @param value value to test
     * @return boolean - true, if 'value' matches specified value of ListType
     */
    private final boolean fitsListType(String value)
    {
        VString vBool = new VString(value, null);
        int size=vBool.size();
        for (int i=0; i<size; i++)
        {
            if (!StringUtil.isBoolean((String)vBool.elementAt(i)))
                return false;
        }
                
        EnumListType listType = getListType();

        if (listType.equals(EnumListType.SingleValue) || listType.equals(EnumListType.getEnum(0))) 
        {// default ListType = SingleValue
            return (size==1);
        }    
        else if ( listType.equals(EnumListType.List) ||
                  listType.equals(EnumListType.Span))
        {
            return true; 
        }
        else if (listType.equals(EnumListType.UniqueList))
        {
            for (int i=0; i<size; i++)
            {
                for (int j=0; j<size; j++)
                {
                    if ( j!=i ) 
                    {
                        String bi =(String)vBool.elementAt(i);
                        String bj =(String)vBool.elementAt(j);
                        if (bi.compareTo(bj)==0) 
                            return false;
                    }
                }
            }
            return true;
        }
        else 
        {
            throw new JDFException ("JDFBooleanEvaluation.fitsListType illegal ListType attribute"); 
        }

    }
    
    
    
    
       
}