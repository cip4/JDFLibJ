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
 * JDFXYPairState.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.JDFXYPairRange;
import org.cip4.jdflib.datatypes.JDFXYPairRangeList;

public class JDFXYPairState extends JDFAbstractState
{
    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
    static 
    { 
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.ALLOWEDVALUELIST,  0x33333331, AttributeInfo.EnumAttributeType.XYPairRangeList, null, null);
        atrInfoTable[1]  = new AtrInfoTable(AttributeName.ALLOWEDVALUEMAX,   0x44444431, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[2]  = new AtrInfoTable(AttributeName.ALLOWEDVALUEMIN,   0x44444431, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[3]  = new AtrInfoTable(AttributeName.ALLOWEDXYRELATION, 0x33333311, AttributeInfo.EnumAttributeType.XYRelation, null, null);
        atrInfoTable[4]  = new AtrInfoTable(AttributeName.CURRENTVALUE,      0x33333331, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[5]  = new AtrInfoTable(AttributeName.DEFAULTVALUE,      0x33333331, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[6]  = new AtrInfoTable(AttributeName.PRESENTVALUELIST,  0x33333331, AttributeInfo.EnumAttributeType.XYPairRangeList, null, null);
        atrInfoTable[7]  = new AtrInfoTable(AttributeName.PRESENTVALUEMAX,   0x44444431, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[8]  = new AtrInfoTable(AttributeName.PRESENTVALUEMIN,   0x44444431, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[9]  = new AtrInfoTable(AttributeName.PRESENTXYRELATION, 0x33333311, AttributeInfo.EnumAttributeType.XYRelation, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.UNITTYPE,          0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
    }

    protected AttributeInfo getTheAttributeInfo() 
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }
    

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.VALUELOC, 0x33333311);
    }

    protected ElementInfo getTheElementInfo()
    {
        return new ElementInfo(super.getTheElementInfo(), elemInfoTable);
    }


    /**
     * constructor for JDFXYPairState
     * @param ownerDocument
     * @param qualifiedName
     */
    public JDFXYPairState(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * constructor for JDFXYPairState
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFXYPairState(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * constructor for JDFXYPairState
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFXYPairState(
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
        return "JDFXYPairState[ --> " + super.toString() + " ]";
    }
    
    //////////////////////////////////////////////////////////////////////
    
    public void setCurrentValue(JDFXYPair value)
    {
        setAttribute(AttributeName.CURRENTVALUE, value.toString(), null);
    }


    public JDFXYPair getCurrentValue()
    {
        try
        {
            return new JDFXYPair(getAttribute(AttributeName.CURRENTVALUE));
        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFXYPairState.getCurrentValue: Attribute CURRENTVALUE is not capable to create JDFXYPair");
        }
    }


    public void setDefaultValue(JDFXYPair value)
    {
        setAttribute(AttributeName.DEFAULTVALUE, value.toString(), null);
    }


    public JDFXYPair getDefaultValue()
    {
        try
        {
            return new JDFXYPair(getAttribute(AttributeName.DEFAULTVALUE));
        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFXYPairState.getDefaultValue: Attribute DEFAULTVALUE is not capable to create JDFXYPair");
        }
    }
        
    /* ******************************************************
    // Attribute getter/ setter
    **************************************************************** */




    public void setAllowedValueList(JDFXYPairRangeList value)
    {
        setAttribute(AttributeName.ALLOWEDVALUELIST, value.toString(), null);
    }

    public JDFXYPairRangeList getAllowedValueList()
    {
        try
        {
            final String attribute = getAttribute(AttributeName.ALLOWEDVALUELIST,null,null);
            return attribute==null ? null : new JDFXYPairRangeList(attribute);
        }
        catch(DataFormatException dfe)
        {
            throw new JDFException("JDFXYPairState.getAllowedValueList: Attribute ALLOWEDVALUELIST is not applicable to create JDFXYPairRangeList");
        }
    }

    public void setPresentValueList(JDFXYPairRangeList value)
    {
        setAttribute(AttributeName.PRESENTVALUELIST, value.toString(), null);
    }

    public JDFXYPairRangeList getPresentValueList()
    {
        if (hasAttribute(AttributeName.PRESENTVALUELIST))
        {
            try
            {
                return new JDFXYPairRangeList(getAttribute(AttributeName.PRESENTVALUELIST));
            }
            catch(DataFormatException dfe)
            {
                throw new JDFException("JDFXYPairState.getPresentValueList: Attribute PRESENTVALUELIST is not applicable to create JDFXYPairRangeList");
            }
        }
        return getAllowedValueList();
    }

    public void setAllowedValueMax(JDFXYPair value)
    {
        setAttribute(
            AttributeName.ALLOWEDVALUEMAX,
            value.toString(),
            null);
    }

    public JDFXYPair getAllowedValueMax()
    {
        try
        {
            return new JDFXYPair(getAttribute(AttributeName.ALLOWEDVALUEMAX));
        }
        catch(DataFormatException dfe)
        {
            throw new JDFException("JDFXYPairState.getAllowedValueMax: Attribute ALLOWEDVALUEMAX is not applicable to create JDFXYPair");
        }
    }

    public void setPresentValueMax(JDFXYPair value)
    {
        setAttribute(AttributeName.PRESENTVALUEMAX, value.toString(), null);
    }

    public JDFXYPair getPresentValueMax()
    {
        if (hasAttribute(AttributeName.PRESENTVALUEMAX))
        {
            try
            {
                return new JDFXYPair(getAttribute(AttributeName.PRESENTVALUEMAX));
            }
            catch(DataFormatException dfe)
            {
                throw new JDFException("JDFXYPairState.setAllowedValueMin: Attribute PRESENTVALUEMAX is not applicable to create JDFXYPair");
            }
        }
        return getAllowedValueMax();
    }

    public void setAllowedValueMin(JDFXYPair value)
    {
        setAttribute(AttributeName.ALLOWEDVALUEMIN, value.toString(), null);
    }

    public JDFXYPair getAllowedValueMin()
    {
        try
        {
            return new JDFXYPair(getAttribute(AttributeName.ALLOWEDVALUEMIN));
        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFXYPairState.setAllowedValueMin: Attribute ALLOWEDVALUEMIN is not applicable to create JDFXYPair");
        }
    }

    public void setPresentValueMin(JDFXYPair value)
    {
        setAttribute(AttributeName.PRESENTVALUEMIN, value.toString(), null);
    }

    public JDFXYPair getPresentValueMin()
    {
        if (hasAttribute(AttributeName.PRESENTVALUEMIN))
        {
            
            try
            {
                return new JDFXYPair(getAttribute(AttributeName.PRESENTVALUEMIN));
            }
            catch (DataFormatException e)
            {
                throw new JDFException("JDFXYPairState.getPresentValueMin: Attribute PRESENTVALUEMIN is not applicable to create JDFXYPair");
            }
        }
        return getAllowedValueMin();
    }

    public void setAllowedXYRelation(EnumXYRelation value)
    {
        setAttribute(
            AttributeName.ALLOWEDXYRELATION,
            value.getName(),null);
    }

    public JDFElement.EnumXYRelation getAllowedXYRelation()
    {
        return EnumXYRelation.getEnum(getAttribute(AttributeName.ALLOWEDXYRELATION, null, null));
    }

    public void setPresentXYRelation(EnumXYRelation value)
    {
        setAttribute( AttributeName.PRESENTXYRELATION, value.getName(), null);
    }

    public JDFElement.EnumXYRelation getPresentXYRelation()
    {
//        return EnumXYRelation.getEnum(getAttribute(
//                AttributeName.ALLOWEDXYRELATION, null, EnumXYRelation.Unknown.getName()));
        JDFElement.EnumXYRelation avail = JDFElement.EnumXYRelation.getEnum(getAttribute( AttributeName.PRESENTXYRELATION, null, null));
        
        if (avail==null)
        {
            return getAllowedXYRelation();
        }
        
        return avail;
    }

    public String getUnitType()
    {
        return getAttribute(AttributeName.UNITTYPE);
    }

    public void setUnitType(String value)
    {
        setAttribute(AttributeName.UNITTYPE, value);
    }

    /* ******************************************************
    // Element getter / setter
    **************************************************************** */

    /**
    * fitsValue - tests, if the defined value matches Allowed or Present testlists,
    * specified for this State
    *
    * @param String value - value to test
    * @param EnumFitsValue valuelist - test lists, that the value has to match.
    * In this State the test lists are ValueList AND XYRelation. 
    * Choose one of two values:  FitsValue_Allowed and FitsValue_Present. (Defaults to Allowed)
    * 
    * @return boolean - true, if the value is in the valuelist or if AllowedValueList is not specified
    */
    public boolean fitsValue(String value, EnumFitsValue testlists)
    {
        if (!fitsListType(value))
            return false;
        
        JDFXYPairRangeList rangelist = null;
        try 
        {
            rangelist = new JDFXYPairRangeList(value);
        }
        catch (DataFormatException dfe)
        {
            return false;
        }

        int siz=rangelist.size();
        for (int i=0; i<siz; i++) // For every range, that rangelist consists of,
        {                         // we test both of range deliminators - right and left, if they fit XYRelation
                                  // In this case test of deliminators is sufficient for evaluation of the whole range
            JDFXYPairRange range = (JDFXYPairRange) rangelist.at(i);
                
            JDFXYPair left = range.getLeft();
            JDFXYPair right = range.getRight();
            
            boolean bFitsXY;
            if (left.equals(right)) 
            {
                bFitsXY = fitsXYRelation(left,testlists);
            } 
            else 
            {
                bFitsXY = fitsXYRelation(left,testlists) && fitsXYRelation(right,testlists);
            }
            if (!bFitsXY)
                return false;
        }
        
        return fitsValueList(rangelist,testlists); // if we are here bFitsXY is true, test ValueList     
    }
    
    
    /**
     * fitsValueList - tests, if the defined 'rangelist' matches 
     * the AllowedValueList or in the PresentValueList, specified for this State
     *
     * @param JDFXYPairRangeList rangelist - range list to test
     * @param EnumFitsValue valuelist - Switches between AllowedValueList and PresentValueList.
     * 
     * @return boolean - true, if 'rangelist' matches the valuelist or if AllowedValueList is not specified
     */
    private final boolean fitsValueList(JDFXYPairRangeList rangelist, EnumFitsValue valuelist)
    {
        JDFXYPairRangeList list;
        if (valuelist.equals(EnumFitsValue.Allowed))
        {
            list = getAllowedValueList();
        } 
        else 
        {
            list = getPresentValueList();
        }
        if (list == null)
            return true;
        
        EnumListType listType = getListType();
        if (listType.equals(EnumListType.CompleteList))
        {
            return fitsCompleteList(rangelist,list);
        }
        else if (listType.equals(EnumListType.CompleteOrderedList))
        { 
            return fitsCompleteOrderedList(rangelist,list);
        }
        else if (listType.equals(EnumListType.ContainedList))
        {
            return fitsContainedList(rangelist,list);
        }

        int siz=rangelist.size();
        for (int i=0; i<siz; i++)
        {
            JDFXYPairRange range = (JDFXYPairRange) rangelist.at(i);

            if (!list.isPartOfRange(range))
                return false;
        }
        return true;
    }
    
    /**
    * fitsXYRelation - tests, if the defined 'xypair' value matches AllowedXYRelation or PresentXYRelation,
    * specified for this State
    *
    * @param JDFXYPair xypair - xypair to test
    * @param EnumFitsValue xyrelation - Switches between AllowedXYRelation and PresentXYRelation.
    * @return boolean - true, if the 'xypair' matches hwrelation or if AllowedXYRelation is not specified
    */
    private final boolean fitsXYRelation(JDFXYPair xypair, EnumFitsValue xyrelation)
    { 
        EnumXYRelation relation;
        
        if (xyrelation.equals(EnumFitsValue.Allowed))
        {
            relation = getAllowedXYRelation();
        } 
        else 
        {
            relation = getPresentXYRelation();
        }
        
        if (relation == null)
            return true;

        double x = xypair.getX();
        double y = xypair.getY();
        
        return relation.evaluateXY(x,y,EPSILON,EPSILON);
    }
    
    
    /**
     * fitsCompleteList - tests for the case, when ListType=CompleteList,
     * if the defined 'value' matches AllowedValueList or PresentValueList,
     * specified for this State
     *
     * @param JDFXYPairRangeList value - value to test
     * @param JDFXYPairRangeList list - testlist are either AllowedValueList or PresentValueList.
     * 
     * @return boolean - true, if 'value' matches testlist
     */
    private final boolean fitsCompleteList(JDFXYPairRangeList value, JDFXYPairRangeList list)
    {
        int v_size=value.size();
        int l_size=list.size();
        
        if (v_size!=l_size) 
            return false; 
        
        if (!value.isUnique()) 
            return false;
     
        JDFXYPairRangeList valueList = new JDFXYPairRangeList(value);

        boolean bFound;
        for (int i=l_size-1; i>=0; i--)
        {
            bFound = false;
            for (int j=valueList.size()-1; j>=0; j--)       
            {
                if (list.at(i).equals(valueList.at(j)))
                {
                    valueList.erase(j);
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
    * if the defined 'value' matches AllowedValueList or PresentValueList,
    * specified for this State
    *
    * @param JDFXYPairRangeList value - value to test
    * @param JDFXYPairRangeList list - testlist are either AllowedValueList or PresentValueList.
    * 
    * @return boolean - true, if 'value' matches testlist
    */
    private final boolean fitsCompleteOrderedList(JDFXYPairRangeList value, JDFXYPairRangeList list)
    {
        int v_size = value.size();
        int l_size = list.size();
        
        if ( v_size != l_size ) 
             return false; 

        if (!value.isUnique()) 
            return false;

        for (int i=0; i<l_size; i++)
        {
            if (!list.at(i).equals(value.at(i))) 
            {
                return false;
            }
        }
        return true;        
    }

    /**
    * fitsContainedList - tests for the case, when ListType=ContainedList,
    * if the defined 'value' matches AllowedValueList or PresentValueList,
    * specified for this State
    *
    * @param JDFXYPairRangeList value - value to test
    * @param JDFXYPairRangeList list - testlist are either AllowedValueList or PresentValueList.
    * 
    * @return boolean - true, if 'value' matches testlist
    */
    private final boolean fitsContainedList(JDFXYPairRangeList value, JDFXYPairRangeList list)
    {
        int v_size = value.size();
        int l_size = list.size();
        
        for (int i=0; i<v_size; i++)
        {
            for (int j=0; j<l_size; j++)
            {
                if (value.at(i).equals(list.at(j))) 
                {
                    return true;
                }
            }
        }
        return false;
    }
}