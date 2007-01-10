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
 * JDFPDFPathState.java
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

public class JDFPDFPathState extends JDFAbstractState
{
    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
    static 
    {
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.ALLOWEDLENGTH,     0x33333311, AttributeInfo.EnumAttributeType.IntegerRange, null, null);
        atrInfoTable[1]  = new AtrInfoTable(AttributeName.CURRENTVALUE,      0x33333311, AttributeInfo.EnumAttributeType.PDFPath, null, null);
        atrInfoTable[2]  = new AtrInfoTable(AttributeName.DEFAULTVALUE,      0x33333311, AttributeInfo.EnumAttributeType.PDFPath, null, null);
        atrInfoTable[3]  = new AtrInfoTable(AttributeName.PRESENTLENGTH,     0x33333311, AttributeInfo.EnumAttributeType.IntegerRange, null, null);
    }

    protected AttributeInfo getTheAttributeInfo() 
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }
    

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.VALUE, 0x33333311);
    }

    protected ElementInfo getTheElementInfo()
    {
        return new ElementInfo(super.getTheElementInfo(), elemInfoTable);
    }


    /**
     * constructor for JDFPDFPathState
     * @param ownerDocument
     * @param qualifiedName
     */
    public JDFPDFPathState(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * constructor for JDFPDFPathState
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFPDFPathState(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * constructor for JDFPDFPathState
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFPDFPathState(
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
        return "JDFPDFPathState[ --> " + super.toString() + " ]";
    }
    
    /* ******************************************************
    // Attribute getter / setter
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
        
        
    public JDFValue getValue(int iSkip)
    {
        JDFValue e = (JDFValue)getElement(ElementName.VALUE, JDFConstants.EMPTYSTRING, iSkip);
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
    * Gets the j-th element Loc of the i-th element Value
    *
    * @param int iSkip: number of Value elements to skip 
    * ( iSkip=0 - first Value element)
    * @param int jSkip: number of Loc subelements of i-th Value element to skip,
    * ( jSkip=0 - first Loc element)
    * @return JDFLoc: the matching Loc element
    */
    public final JDFLoc getValueLoc(int iSkip, int jSkip)
    {
        JDFValue val = getValue(iSkip);
        if(val==null)
            return null;
        return val.getLoc(jSkip);
    }
    
    /**
    * Appends element Loc to the end of the i-th subelement Value
    *
    * @param int iSkip: number of Value elements to skip 
    * ( iSkip=0 - first Value element)
    * @return JDFLoc: newly created Loc element
    */
    public JDFLoc appendValueLocLoc(int iSkip)
    {
        JDFValue val = getValue(iSkip);
        if(val==null)
            return null;
        return val.appendLoc();
     }
    
    /** 
     * Sets the AllowedValue attribute of the i-th subelement Value
     *
     * @param int iSkip: the number of Value elements to skip
     * @param String value: value to set the attribute to
     */
    public void setValueAllowedValue(int iSkip, String value)
    {
        JDFValue e = (JDFValue) getElement(ElementName.VALUE,null,iSkip);
        e.setAllowedValue(value);
    }
    
    /**
     * Gets the AllowedValue attribute of the i-th subelement Value
     *
     * @param int iSkip: the number of Value elements to skip
     * @return String: the attribute value
     */
    public final String getValueAllowedValue(int iSkip) 
    {
        JDFValue e = (JDFValue) getElement(ElementName.VALUE,null,iSkip);
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
    * Gets the value of attribute ValueUsage of the i-th subelement Value
    *
    * @param int iSkip: the number of Value elements to skip
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
     * fitsValue - tests, if the defined value matches Allowed test lists 
     * or Present test lists, specified for this State
     *
     * @param String value - value to test
     * @param EnumFitsValue tetlists - test lists, that the value has to match.
     * In this State the test lists are Length, ValueElem. 
     * Choose one of two values: FitsValue_Allowed or FitsValue_Present. (Defaults to Allowed)
     * 
     * @return boolean - true, if the value matches all test lists or if Allowed test lists are not specified
     */
    public final boolean fitsValue(String value, EnumFitsValue testlists)
    {
        return (fitsLength(value,testlists) && fitsValueElem(value,testlists));
    }
    
    /**
     * fitsValueElem - tests, if the defined PDFPath matches 
     * subelement Value, specified for this State
     *
     * @param String pdfPath - PDFPath to test
     * @param EnumFitsValue valueusage - Switches between 
     * Allowed and Present configuration in subelement Value.
     * 
     * @return boolean - true, if 'pdfPath' matches subelement Value 
     */
    private final boolean fitsValueElem(String pdfPath, EnumFitsValue valuelist) 
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
                    hasValue=true;
                    String value = getValueAllowedValue(i);
                    if (value.compareTo(pdfPath)==0)
                        return true; // we have found it
                }               
            }
            else 
            {
                hasValue=true;
                String value = getValueAllowedValue(i);
                if (value.compareTo(pdfPath)==0)
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