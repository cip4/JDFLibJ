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
 * JDFStringState.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;


import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.resource.JDFValue;
import org.cip4.jdflib.resource.JDFValue.EnumValueUsage;

public class JDFStringState extends JDFAbstractState
{
    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static 
    { 
        atrInfoTable[1]  = new AtrInfoTable(AttributeName.ALLOWEDLENGTH,     0x33333311, AttributeInfo.EnumAttributeType.IntegerRange, null, null);
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.ALLOWEDREGEXP,     0x33333311, AttributeInfo.EnumAttributeType.RegExp, null, null);
        atrInfoTable[2]  = new AtrInfoTable(AttributeName.CURRENTVALUE,      0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3]  = new AtrInfoTable(AttributeName.DEFAULTVALUE,      0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[5]  = new AtrInfoTable(AttributeName.PRESENTLENGTH,     0x33333311, AttributeInfo.EnumAttributeType.IntegerRange, null, null);
        atrInfoTable[4]  = new AtrInfoTable(AttributeName.PRESENTREGEXP,     0x33333311, AttributeInfo.EnumAttributeType.RegExp, null, null);
    }

    protected AttributeInfo getTheAttributeInfo() 
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }
    

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.VALUE, 0x33333331);
    }

    protected ElementInfo getTheElementInfo()
    {
        return new ElementInfo(super.getTheElementInfo(), elemInfoTable);
    }

    /**
     * constructor for JDFStringState
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFStringState(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * constructor for JDFStringState
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFStringState(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * constructor for JDFStringState
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFStringState(
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
        return "JDFStringState[ --> " + super.toString() + " ]";
    }
    
    /* ******************************************************
    // Attribute getter/ setter
    **************************************************************** */


    public void setCurrentValue(String value)
    {
        setAttribute(AttributeName.CURRENTVALUE, value);
    }


    public String getCurrentValue()
    {
        return getAttribute(AttributeName.CURRENTVALUE, null, JDFConstants.EMPTYSTRING);
    }


    public void setDefaultValue(String value)
    {
        setAttribute(AttributeName.DEFAULTVALUE, value);
    }


    public String getDefaultValue()
    {
        return getAttribute(AttributeName.DEFAULTVALUE, null, JDFConstants.EMPTYSTRING);
    }
     
        
    public void setAllowedRegExp(String value)
    {
        setAttribute(AttributeName.ALLOWEDREGEXP, value);
    }


    public String getAllowedRegExp()
    {
        return getAttribute(AttributeName.ALLOWEDREGEXP);
    }


    public void setPresentRegExp(String value)
    {
        setAttribute(AttributeName.PRESENTREGEXP, value);
    }


    public String getPresentRegExp()
    {
        if (hasAttribute(AttributeName.PRESENTREGEXP))
        {
            return getAttribute(AttributeName.PRESENTREGEXP);
        }
        return getAllowedRegExp();
    }



    /* ******************************************************
    // Element getter / setter
    **************************************************************** */


    
    public JDFValue getValue(int iSkip)
    {
        JDFValue e = (JDFValue)getElement(ElementName.VALUE, null, iSkip);
        return e;
    }

    public JDFValue appendValue()
    {
        return (JDFValue)appendElement(ElementName.VALUE, null);
    }
    
    /* ******************************************************
    // Subelement attribute and element Getter / Setter
    **************************************************************** */

    /**
    * Gets the jSkip'th element <code>Loc</code> of the iSkip'th element <code>Value</code>
    *
    * @param iSkip number of <code>Value</code> elements to skip 
    *              (iSkip=0 -> first <code>Value</code> element)
    * @param jSkip number of <code>Loc</code> subelements of iSkip'th <code>Value</code> 
    *              element to skip
    *              (jSkip=0 -> first <code>Loc</code> element)
    * @return JDFLoc: the matching Loc element
    */
    public JDFLoc getValueLocLoc(int iSkip, int jSkip)
    {
        JDFValue val = (JDFValue) getElement(ElementName.VALUE,null,iSkip);
        JDFLoc loc = (JDFLoc) val.getElement(ElementName.LOC,null,jSkip);
        return loc;
    }
    
    
    /**
    * Appends element <code>Loc</code> to the end of the iSkip'th subelement <code>Value</code>
    *
    * @param iSkip number of <code>Value</code> elements to skip 
    *              (iSkip=0 -> first <code>Value</code> element)
    * @return JDFLoc: newly created <code>Loc</code> element
    */
    public JDFLoc appendValueLocLoc(int iSkip)
    {
        JDFValue val = getValue(iSkip);
        if(val==null)
            return null;
        return val.appendLoc();
     }
    
    /** 
     * Sets the <code>AllowedValue</code> attribute of the iSkip'th subelement <code>Value</code>
     *
     * @param value value to set the attribute to
     */
    public void appendValueAllowedValue(String value)
    {
        JDFValue e = (JDFValue) appendElement(ElementName.VALUE,null);
        e.setAllowedValue(value);
    }
    
    /**
     * Gets the <code>AllowedValue</code> attribute of the iSkip'th subelement <code>Value</code>
     *
     * @param iSkip the number of <code>Value</code> elements to skip
     * @return String: the attribute value
     */
    public final String getValueAllowedValue(int iSkip) 
    {
        JDFValue e = (JDFValue) getElement(ElementName.VALUE,null,iSkip);
        if(e==null)
            return null;
        return e.getAllowedValue();
    }
    
    
    /**
    * Sets the ValueUsage attribute of the i-th subelement Value
    *
    * @param int iSkip: the number of Value elements to skip
    * @param EnumFitsValue value: value to set the attribute to
    */
    public void setValueValueUsage(int iSkip, EnumFitsValue value)
    {
        JDFValue e = (JDFValue) getElement(ElementName.VALUE,null,iSkip);
        e.setValueUsage(EnumValueUsage.getEnum(value.getName()) );
    }
    
    /**
    * Gets the value of attribute <code>ValueUsage</code> of the iSkip'th subelement <code>Value</code>
    *
    * @param iSkip the number of <code>Value</code> elements to skip
    * @return EnumFitsValue: the attribute value
    */
    public final EnumFitsValue getValueValueUsage(int iSkip) 
    {
        JDFValue e = (JDFValue) getElement(ElementName.VALUE,null,iSkip);
        return EnumFitsValue.getEnum(e.getValueUsage().getName());
    }

    
    /* ******************************************************
    // FitsValue Methods
    **************************************************************** */
    
    /**
     * fitsValue - checks whether <code>value</code> matches the Allowed/Present test lists
     * specified for this State
     *
     * @param value     value to test
     * @param testlists the test lists the value has to match.
     * In this State the test lists are ValueList AND ValueMod.<br> 
     * Choose one of two values: FitsValue_Allowed or FitsValue_Present. (Defaults to Allowed)
     * 
     * @return boolean - true, if <code>value</code> matches testlists or 
     *         if AllowedValueList and AllowedValueMod are not specified
     */
    public final boolean fitsValue(String value, EnumFitsValue testlists)
    {
        return ( fitsLength (value,testlists)     &&
                 fitsRegExp (value,testlists)     &&
                 fitsValueElem (value,testlists));
    }
    
    /**
     * fitsValueElem - checks whether <code>str</code> matches the 
     * subelement <code>Value</code> specified for this State
     *
     * @param str         string to test
     * @param valueusage  switches between Allowed and Present configuration 
     *                    in subelement <code>Value</code>
     * 
     * @return boolean - true, if <code>str</code> matches subelement Value or no 
     *                   corresponding value elements exist
     */
    private final boolean fitsValueElem(String str, EnumFitsValue valuelist) 
    {
        VElement v = getChildElementVector(ElementName.VALUE, null, null, true, 0,false);
        int siz=v.size();
        boolean hasValue=false;
        for (int i=0; i<siz; i++)
        {
            JDFValue elm = (JDFValue) v.elementAt(i);
            if (elm.hasAttribute(AttributeName.VALUEUSAGE))
            {
                EnumFitsValue valueUsage = getValueValueUsage(i); 
                if ( valuelist.equals(valueUsage)) 
                {
                    String value = getValueAllowedValue(i);
                    hasValue=true;
                    if (value.compareTo(str)==0)
                        return true; // we have found it
                }   
            }
            else {
                hasValue=true;
                String value = getValueAllowedValue(i);
                if (value.compareTo(str)==0)
                    return true; // we have found it
            }
        }
        return !hasValue;
    }
   
    public void setAllowedLength(JDFIntegerRange value)
    {
        super.setPresentLength(value);
    }


    public JDFIntegerRange getAllowedLength()
    {
        return super.getAllowedLength();
    }


    public void setPresentLength(JDFIntegerRange value)
    {
        super.setAllowedLength(value);
    }


    public JDFIntegerRange getPresentLength()
    {        
        return super.getPresentLength();
    }

}