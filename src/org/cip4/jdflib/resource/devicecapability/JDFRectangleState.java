/*
*
* The CIP4 Software License, Version 1.0
*
*
* Copyright (c) 2001-2007 The International Cooperation for the Integration of 
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
 * JDFRectangleState.java
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
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFRectangleRange;
import org.cip4.jdflib.datatypes.JDFRectangleRangeList;
import org.cip4.jdflib.resource.devicecapability.JDFTerm.EnumTerm;

public class JDFRectangleState extends JDFAbstractState
{
    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static  
    {
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.ALLOWEDHWRELATION, 0x33333311, AttributeInfo.EnumAttributeType.XYRelation, null, null);
        atrInfoTable[1]  = new AtrInfoTable(AttributeName.ALLOWEDVALUELIST,  0x33333311, AttributeInfo.EnumAttributeType.RectangleRangeList, null, null);
        atrInfoTable[2]  = new AtrInfoTable(AttributeName.CURRENTVALUE,      0x33333311, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[3]  = new AtrInfoTable(AttributeName.DEFAULTVALUE,      0x33333311, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[4]  = new AtrInfoTable(AttributeName.PRESENTHWRELATION, 0x33333311, AttributeInfo.EnumAttributeType.XYRelation, null, null);
        atrInfoTable[5]  = new AtrInfoTable(AttributeName.PRESENTVALUELIST,  0x33333311, AttributeInfo.EnumAttributeType.RectangleRangeList, null, null);
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
     * constructor for JDFRectangleState
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFRectangleState(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * constructor for JDFRectangleState
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFRectangleState(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * constructor for JDFRectangleState
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFRectangleState(
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
        return "JDFRectangleState[ --> " + super.toString() + " ]";
    }
    
    /* ******************************************************
    // Attribute getter / setter
    **************************************************************** */

    public void setCurrentValue(JDFRectangle value)
    {
        setAttribute(AttributeName.CURRENTVALUE, value.toString(), null);
    }


    public JDFRectangle getCurrentValue()
    {
        try
        {
            JDFRectangle r = new JDFRectangle(getAttribute(AttributeName.CURRENTVALUE));
            return r;    
        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFRectangleState.getCurrentValue: Attribute CURRENTVALUE is not capable to create JDFRectangle");
        }
    }


    public void setDefaultValue(JDFRectangle value)
    {
        setAttribute(AttributeName.DEFAULTVALUE, value.toString(), null);
    }


    public JDFRectangle getDefaultValue()
    {
        try
        {
            return new JDFRectangle(getAttribute(AttributeName.DEFAULTVALUE));
        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFRectangleState.getDefaultValue: Attribute DEFAULTVALUE is not capable to create JDFRectangle");
        }
    }
        
    public void setAllowedValueList(JDFRectangleRangeList value)
    {
        setAttribute(AttributeName.ALLOWEDVALUELIST, value.toString());
    }

    public JDFRectangleRangeList getAllowedValueList()
    {
        try
        {
            final String attribute = getAttribute(AttributeName.ALLOWEDVALUELIST,null,null);
            return attribute==null ? null : new JDFRectangleRangeList(attribute);
        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFRectangleState.getAllowedValueList: Attribute ALLOWEDVALUELIST is not capable to create JDFRectangleRangeList");
        }
    }

    public void setPresentValueList(JDFRectangleRangeList  value)
    {
        setAttribute(AttributeName.PRESENTVALUELIST, value.toString());
    }

    public JDFRectangleRangeList getPresentValueList()
    {
        if (hasAttribute(AttributeName.PRESENTVALUELIST))
        {
            try
            {
                return new JDFRectangleRangeList(getAttribute(AttributeName.PRESENTVALUELIST));
            }
            catch (DataFormatException e)
            {
                throw new JDFException("JDFRectangleState.getPresentValueList: Attribute PRESENTVALUELIST is not capable to create JDFRectangleRangeList");
            }        	
        }
        return getAllowedValueList();
    }

    public void setAllowedHWRelation(EnumXYRelation value)
    {
        setAttribute(AttributeName.ALLOWEDHWRELATION, value.getName(), null);
    }

    public JDFElement.EnumXYRelation getAllowedHWRelation()
    {
        return JDFElement.EnumXYRelation.getEnum(getAttribute(AttributeName.ALLOWEDHWRELATION, null, null));
    }

    public void setPresentHWRelation(EnumXYRelation value)
    {
        setAttribute(AttributeName.PRESENTHWRELATION, value.getName(), null);
    }

    public JDFElement.EnumXYRelation getPresentHWRelation()
    {
        JDFElement.EnumXYRelation rel =
            JDFElement.EnumXYRelation.getEnum(getAttribute(AttributeName.PRESENTHWRELATION, null, null));

        if (rel == null)
        {
            return getAllowedHWRelation();
        }
        return rel;
    }

    /* ******************************************************
    // Element getter / setter
    **************************************************************** */

    /* (non-Javadoc)
     * @see org.cip4.jdflib.resource.devicecapability.JDFAbstractState#addValue(java.lang.String, org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue)
     */
    public void addValue(String value, EnumFitsValue testlists)
    {
        if(fitsValue(value, testlists))
            return;

        JDFRectangle rect;
        try
        {
            rect = new JDFRectangle(value);
        }
        catch (DataFormatException x)
        {
            return; // nop for bad values
        }
        if(testlists==null || EnumFitsValue.Allowed.equals(testlists))
        {
            JDFRectangleRangeList list=getAllowedValueList();
            if(list==null)
                list=new JDFRectangleRangeList();
            list.append (rect);
            setAllowedValueList(list);
        }
        if(testlists==null || EnumFitsValue.Present.equals(testlists))
        {
            JDFRectangleRangeList list=getPresentValueList();
            if(list==null || !hasAttribute(AttributeName.PRESENTVALUELIST))
                list=new JDFRectangleRangeList();
            list.append (rect);
            setPresentValueList(list);
        }
    }
    /**
     * fitsValue - checks whether <code>value</code> matches the Allowed/Present test lists
     * specified for this State
     *
     * @param value     value to test
     * @param testlists the test lists the value has to match.
     * In this State the test lists are ValueList and HWRelation.<br> 
     * Choose one of two values: FitsValue_Allowed or FitsValue_Present. (Defaults to Allowed)
     * 
     * @return boolean - true, if <code>value</code> matches testlists or 
     *         if AllowedValueList and AllowedValueMod are not specified
     */
    public final boolean fitsValue(String value, EnumFitsValue testlists)
    {
        if (fitsListType(value))
        {
            JDFRectangleRangeList rrl = null;
            try 
            {
                rrl = new JDFRectangleRangeList(value);
            }
            catch (DataFormatException dfe)
            {
                return false;
            }

            int siz=rrl.size();
            for (int i=0; i<siz; i++) // For every range, that rangelist consists of,
            {                         // we test both of range deliminators - right and left, if they fit HWRelation
                                      // In this case test of deliminators is sufficient for evaluation of the whole range
                JDFRectangleRange range = (JDFRectangleRange) rrl.at(i);
                    
                JDFRectangle left = range.getLeft();
                JDFRectangle right = range.getRight();
                
                boolean bFitsHW;
                if (left.equals(right)) 
                {
                    bFitsHW = fitsHWRelation(left,testlists);
                } 
                else 
                {
                    bFitsHW = fitsHWRelation(left,testlists) && fitsHWRelation(right,testlists);
                }
                if (!bFitsHW)
                    return false;
            }
            return fitsValueList(rrl,testlists); // if we are here bFitsHW is true, test ValueList
        }
        return false; // the value doesn't fit ListType attribute of this State
    }
    
    /**
     * fitsValueList - checks whether <code>rangelist</code> matches 
     * the AllowedValueList/PresentValueList specified for this State
     *
     * @param rangelist range list to test
     * @param valuelist switches between AllowedValueList and PresentValueList.
     * 
     * @return boolean - true, if <code>rangelist</code> matches the valuelist or 
     *         if AllowedValueList is not specified
     */
    private final boolean fitsValueList(JDFRectangleRangeList rangelist, EnumFitsValue valuelist)
    {
        JDFRectangleRangeList list;
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
        
        EnumListType listType=getListType();
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
            JDFRectangleRange range = (JDFRectangleRange) rangelist.at(i);

            if (!list.isPartOfRange(range))
                return false;
        }
        return true;
    }

    /**
     * fitsHWRelation - checks whether <code>rect</code> matches the  
     * AllowedHWRelation/PresentHWRelation specified for this State 
     *
     * @param rect       rectangle value to test
     * @param hwrelation Switches between AllowedHWRelation and PresentHWRelation.
     * @return boolean - true, if <code>rect</code> matches hwrelation or if AllowedHWRelation is not specified
     */
     private final boolean fitsHWRelation(JDFRectangle rect, EnumFitsValue hwrelation)
     { 
         EnumXYRelation relation;
         
         if (hwrelation.equals(EnumFitsValue.Allowed))
         {
             relation = getAllowedHWRelation();
         } 
         else 
         {
             relation = getPresentHWRelation();
         }
         
         if (relation == null)
             return true;

         double width = rect.getUrx() - rect.getLlx();
         double height = rect.getUry() - rect.getLly();
        
         return relation.evaluateXY(width,height,EPSILON,EPSILON);
    }
    
    /**
     * fitsCompleteList - tests whether <code>value</code> matches the given testlist
     * (ListType=fitsCompleteList)
     *
     * @param value value to test
     * @param list  testlist, either AllowedValueList or PresentValueList.
     * 
     * @return boolean - true, if <code>value</code> matches the testlist
     */
    private final boolean fitsCompleteList(JDFRectangleRangeList value, JDFRectangleRangeList list)
    {
        int v_size=value.size();
        int l_size=list.size();
        
        if (v_size!=l_size) 
            return false; 
        
        if (!value.isUnique()) 
            return false;
     
        JDFRectangleRangeList valueList = new JDFRectangleRangeList(value);

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
     * fitsCompleteOrderedList - tests whether <code>value</code> matches the given testlist
     * (ListType=CompleteOrderedList)
     *
     * @param value value to test
     * @param list  testlist, either AllowedValueList or PresentValueList.
     * 
     * @return boolean - true, if <code>value</code> matches the testlist
     */
    private final boolean fitsCompleteOrderedList(JDFRectangleRangeList value, JDFRectangleRangeList list)
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
     * fitsContainedList - tests whether <code>value</code> matches the given ValueList
     * (ListType=ContainedList)
     *
     * @param value value to test
     * @param list  ValueList
     * @return boolean - true, if <code>value</code> matches the ValueList
     */
    private final boolean fitsContainedList(JDFRectangleRangeList value, JDFRectangleRangeList list)
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
    
    /* (non-Javadoc)
     * @see org.cip4.jdflib.ifaces.ICapabilityElement#getEvaluationType()
     */
    public EnumTerm getEvaluationType()
    {
        return EnumTerm.RectangleEvaluation;
    }

}