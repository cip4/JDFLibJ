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
 * JDFDurationEvaluation.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFDurationRangeList;
import org.cip4.jdflib.util.JDFDate;

public class JDFDurationEvaluation extends JDFEvaluation
{
	private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
    static 
    {
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.VALUELIST, 0x33333333, AttributeInfo.EnumAttributeType.DurationRangeList, null, null);
    }

    protected AttributeInfo getTheAttributeInfo() 
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    /**
     * constructor for JDFDurationEvaluation
     * @param ownerDocument
     * @param qualifiedName
     */
    public JDFDurationEvaluation(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * constructor for JDFDurationEvaluation
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFDurationEvaluation(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * constructor for JDFDurationEvaluation
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFDurationEvaluation(
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
        return "JDFDurationEvaluation[ --> " + super.toString() + " ]";
    }
    
	/* ******************************************************
	// Attribute getter/ Setter
	**************************************************************** */	

    public void setValueList( JDFDurationRangeList value)
    {
		setAttribute(AttributeName.VALUELIST, value.toString());
	}
    
 
    public JDFDurationRangeList getValueList()
    {
        try
        {
            return new JDFDurationRangeList(getAttribute(AttributeName.VALUELIST));
        }
        catch(DataFormatException dfe)
        {
        	return null;   
        }
	
    }
    
    /* ******************************************************
    // FitsValue Methods
    **************************************************************** */
    
    /**
     * fitsValue - tests, if the defined value matches ValueList,
     * specified for this Evaluation
     *
     * @param String value - value to test
     * @return boolean - true, if the value matches ValueList or if ValueList is not specified
     */
    public final boolean fitsValue(String value)
    {
        if (!fitsListType(value))
            return false;
        
        JDFDurationRangeList rangelist = null;
        try 
        {
            rangelist = new JDFDurationRangeList(value);
        }
        catch (DataFormatException dfe)
        {
            return false;
        }
        return (fitsValueList(rangelist));
    }

    /**
     * fitsListType - tests, if the defined 'value' matches value of ListType attribute,
     * specified for this Evaluation
     *
     * @param String value - value to test
     * @return boolean - true, if 'value' matches specified ListType
     */
    private final boolean fitsListType(String value)
    {
        EnumListType listType=getListType();

        JDFDurationRangeList rangelist;
        try 
        {
            rangelist = new JDFDurationRangeList(value);                       
        }
        catch (DataFormatException e)
        {
            return false;
        }
        catch (JDFException e)
        {
            return false;
        }
        
        if (listType.equals(EnumListType.SingleValue) || listType.equals(EnumListType.getEnum(0))) 
        {// default ListType = SingleValue
            
            if (value.indexOf("P")!=0) 
                return false;
            
            try
            {
                new JDFDate(value);
            }
            catch (JDFException e)
            {
                return false;
            }
            catch (DataFormatException e)
            {
                return false;
            }
            
            return true;
        }    
        else if (listType.equals(EnumListType.RangeList) ||
                 listType.equals(EnumListType.Span))
        {
            return true;
        }
        else if ( listType.equals(EnumListType.List))
        {
            return rangelist.isList(); 
        }
        else if (listType.equals(EnumListType.OrderedList))
        {
            return (rangelist.isList()&&rangelist.isOrdered());
        }
        else if (listType.equals(EnumListType.UniqueList))
        {
            return (rangelist.isList()&&rangelist.isUnique());
        }
        else if (listType.equals(EnumListType.UniqueOrderedList))
        {
            return (rangelist.isList()&&rangelist.isUniqueOrdered());
        }
        else if (listType.equals(EnumListType.OrderedRangeList))
        {
            return rangelist.isOrdered();
        }
        else if (listType.equals(EnumListType.UniqueRangeList))
        {
            return rangelist.isUnique();
        }
        else if (listType.equals(EnumListType.UniqueOrderedRangeList))
        {
            return (rangelist.isUniqueOrdered());
        }
        else
        {
            throw new JDFException ("JDFDurationEvaluation.fitsListType illegal ListType attribute"); 
        }
    }

    /**
     * fitsValueList - tests, if the defined 'rangelist' matches ValueList
     * specified for this Evaluation
     *
     * @param JDFDurationRangeList rangelist - range list to test
     * 
     * @return boolean - true, if 'rangelist' matches the valuelist or 
     * if ValueList is not specified
     */
    private final boolean fitsValueList(JDFDurationRangeList rangelist)
    {
        if (!hasAttribute(AttributeName.VALUELIST))
            return true;
        return getValueList().isPartOfRange(rangelist);
    }
    
   
}