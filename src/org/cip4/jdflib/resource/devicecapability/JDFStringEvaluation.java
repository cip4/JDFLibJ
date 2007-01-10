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
 * JDFStringEvaluation.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.resource.JDFValue;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.DOMException;

public class JDFStringEvaluation extends JDFEvaluation
{
    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
    static 
    {
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.LENGTH,    0x33333333, AttributeInfo.EnumAttributeType.IntegerRange, null, null);
        atrInfoTable[1]  = new AtrInfoTable(AttributeName.REGEXP,    0x33333333, AttributeInfo.EnumAttributeType.RegExp, null, null);
    }

    protected AttributeInfo getTheAttributeInfo() 
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.VALUE, 0x33333333);
    }

    protected ElementInfo getTheElementInfo()
    {
        return new ElementInfo(super.getTheElementInfo(), elemInfoTable);
    }

    /**
     * constructor for JDFStringEvaluation
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFStringEvaluation(CoreDocumentImpl myOwnerDocument, String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * constructor for JDFStringEvaluation
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFStringEvaluation(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * constructor for JDFStringEvaluation
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFStringEvaluation(
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
        return "JDFStringEvaluation[ --> " + super.toString() + " ]";
    }
    
    /* ******************************************************
    // Attribute getter/ setter
    **************************************************************** */


    public void setLength(JDFIntegerRange value)
    {
        setAttribute(AttributeName.LENGTH, value.toString());
    }


    public JDFIntegerRange getLengthRange()
    {
        try
        {
            return new JDFIntegerRange(getAttribute(AttributeName.LENGTH));
        }
        catch(DataFormatException dfe)
        {
            throw new JDFException("JDFStringEvaluation.getLengthRange: Attribute LENGTH is not capable to create JDFIntegerRange");
        }
        
    }

    
    public void setRegExp(String value)
    {
        setAttribute(AttributeName.REGEXP, value);
    }


    public String getRegExp()
    {
        return getAttribute(AttributeName.REGEXP);
    }


    /* ******************************************************
    // Element getter / setter
    **************************************************************** */

    public JDFValue getValue(int iSkip)
    {
        return (JDFValue)getElement(ElementName.VALUE, JDFConstants.EMPTYSTRING, iSkip);
    }

    /**
     * append a value element
     * @return the newly created value element
     */
    public JDFValue appendValue()
    {
        return (JDFValue)appendElement(ElementName.VALUE, null);
    }   
    /**
     * append a value element and set Value/@Value to value
     * @param value the value string to set
     * 
     * @return the newly created value element
     */
    public JDFValue appendValueValue(String value)
    {
        JDFValue v= (JDFValue)appendElement(ElementName.VALUE, null);
        v.setValue(value);
        return v;
    }
    
    /* ******************************************************
    // Subelement attribute and element Getter / Setter
    **************************************************************** */

    /** 
     * Sets the Value attribute of the i-th subelement Value
     *
     * @param int iSkip: the number of Value elements to skip
     * @param String value: value to set the attribute to
     */
    public void setValueValue(int iSkip, String value)
    {
        JDFValue e = (JDFValue) getCreateElement(ElementName.VALUE,null,iSkip);
        e.setValue(value);
    }
    
    /**
     * Gets the Value attribute of the i-th subelement Value
     *
     * @param int iSkip: the number of Value elements to skip
     * @return String: the attribute value, null if no matching value element element exists
     */
    public final String getValueValue(int iSkip) 
    {
        JDFValue e = (JDFValue) getElement(ElementName.VALUE,null,iSkip);
        if(e==null)
            return null;
        return e.getValue();
    }
    
   
    /* ******************************************************
    // FitsValue Methods
    **************************************************************** */
    
    /**
     * fitsValue - tests, if the defined 'value' matches testlists, 
     * specified for this Evaluation
     *
     * @param String value -  value to test
     * @return boolean - true, if 'value' matches testlists or 
     * if testlists are not specified
     */
    public final boolean fitsValue(String value)
    {
        return fitsLength(value) && fitsRegExp(value) &&  fitsValueElem(value);
    }
    

    /**
     * fitsLength - tests, if the defined string 'str' matches Length,
     * specified for this Evaluation
     *
     * @param String str - string to test
     * @return boolean - true, if 'str' matches Length or if Length is not specified
     */
    private final boolean fitsLength(String str)
    {
        if (!hasAttribute(AttributeName.LENGTH)) 
        {
            int len=str.length();
            return getLengthRange().inRange(len);
        }
        return true;
    }


    /**
     * fitsRegExp - tests, if the defined string 'str' matches RegExp,
     * specified for this Evaluation
     *
     * @param String str - string to test
     * @return boolean - true, if 'str' matches RegExp or if RegExp is not specified
     */
    private final boolean fitsRegExp(String str) 
    {
        if (!hasAttribute(AttributeName.REGEXP))
            return true; 

        return StringUtil.matches(str,getRegExp()); 
    }


    /**
     * fitsValueElem - tests, if the defined string 'str' value matches 
     * subelement Value, specified for this Evaluation
     *
     * @param String str - string to test
     * @return boolean - true, if 'str' matches subelement Value 
     */
    private final boolean fitsValueElem(String str) 
    {
        VElement v = getChildElementVector(ElementName.VALUE, null, null, true, 0,false);
        int siz = v.size();
        if (siz==0) 
            return true; // Evaluation has no Value elements
      
        for (int i=0; i<siz; i++)
        {
            String value = getValueValue(i); // JDFValue elm =(JDFValue) v.elementAt(i);
            if (value.compareTo(str)==0)
                return true; // we have found it

        }
        return false;
    }

}