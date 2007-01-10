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
 * JDFNumberEvaluation.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.util.StringUtil;

public class JDFNumberEvaluation extends JDFEvaluation
{
    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
    static 
    {
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.TOLERANCE, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, "0 0");
        atrInfoTable[1]  = new AtrInfoTable(AttributeName.VALUELIST, 0x33333333, AttributeInfo.EnumAttributeType.NumberRangeList, null, null);
        atrInfoTable[2]  = new AtrInfoTable(AttributeName.VALUEMOD,  0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
    }

    protected AttributeInfo getTheAttributeInfo() 
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }

    /**
     * constructor for JDFNumberEvaluation
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFNumberEvaluation(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * constructor for JDFNumberEvaluation
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFNumberEvaluation(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * constructor for JDFNumberEvaluation
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFNumberEvaluation(
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
        return "JDFNumberEvaluation[ --> " + super.toString() + " ]";
    }
    
    /* ******************************************************
    // Attribute getter/ setter
    **************************************************************** */

    public void setValueList(JDFNumberRangeList value)
    {
        setAttribute(AttributeName.VALUELIST, value.toString(), null);
    }
    
    public JDFNumberRangeList getValueList()
    {
        try
        {
            return new JDFNumberRangeList(
                                getAttribute(
                                    AttributeName.VALUELIST,
                                    JDFConstants.EMPTYSTRING,
                                    null));

        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFNumberEvaluation.setValueList: Attribute VALUELIST not applicable to create a JDFNumberRangeList");
        }
        
    }

    public void setTolerance(JDFXYPair value)
    {
        setAttribute(
            AttributeName.TOLERANCE,
            value.toString(),
            null);
    }

    public JDFXYPair getTolerance()
    {
        return super.getTolerance();
    }

    
    public void setValueMod(JDFXYPair value)
    {
        setAttribute(
            AttributeName.VALUEMOD,
            value.toString(),
            null);
    }

    public JDFXYPair getValueMod()
    {
        try
        {
            return new JDFXYPair(getAttribute(AttributeName.VALUEMOD));
        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFNumberEvaluation.setValueMod: Attribute AllowedValueMod is not applicable to create JDFXYPair");
        }
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
        if (!fitsListType(value))
            return false; 
        
        JDFNumberRangeList rangelist = null;
        try 
        {
            rangelist = new JDFNumberRangeList(value);
        }
        catch (DataFormatException dfe)
        {
            return false;
        }

        int siz=rangelist.size();
        for (int i=0; i<siz; i++) {
            if (!fitsValueMod((JDFNumberRange) rangelist.at(i)))
                return false;
        }
        return (fitsValueList(rangelist));
       
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
        EnumListType listType = getListType();

        JDFNumberRangeList rangelist;
        try 
        {
            rangelist = new JDFNumberRangeList(value);                       
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
            return (StringUtil.isNumber(value));
        }    
        else if (listType.equals(EnumListType.RangeList) ||
                 listType.equals(EnumListType.Span))
        {
            return true;
        }
        else if ( listType.equals(EnumListType.List) ||
             listType.equals(EnumListType.CompleteList)  || // complete or not - tested in fitsValueList
             listType.equals(EnumListType.CompleteOrderedList) || // tested in fitsValueList
             listType.equals(EnumListType.ContainedList))  // tested in fitsValueList
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
            throw new JDFException ("JDFNumberEvaluation.fitsListType illegal ListType attribute"); 
        }
    }
    
    /**
     * fitsValueList - checks whether <code>rangelist</code> matches 
     * the AllowedValueList or the PresentValueList specified for this Evaluation
     *
     * @param rangelist nmtokens to test
     * @return boolean - true, if <code>value</code> matches <code>valuelist</code> or 
     * if AllowedValueList is not specified
     */
    private final boolean fitsValueList(JDFNumberRangeList rangelist)
    {
        if (!hasAttribute(AttributeName.VALUELIST)) 
            return true;
        
        JDFNumberRangeList valuelist = getValueList();
        
        EnumListType listType = getListType();
        
        if (listType.equals(EnumListType.CompleteList))
        {
            return fitsCompleteList(rangelist,valuelist);
        }
        else if (listType.equals(EnumListType.CompleteOrderedList))
        { 
            return fitsCompleteOrderedList(rangelist,valuelist);
        }
        else if (listType.equals(EnumListType.ContainedList))
        {
            return fitsContainedList(rangelist,valuelist);
        }

        if (hasAttribute(AttributeName.TOLERANCE)) {
            valuelist = fitsTolerance(valuelist);
        }   
        return (!valuelist.isPartOfRange(rangelist));

    }

    
    /**
     * fitsValueMod - checks whether <code>range</code> matches the <code>ValueMod</code> 
     * specified for this Evaluation. If ValueMod is specified, 
     * only a single value can be tested, otherwise <code>false</code> is returned.
     *
     * @param range range to test
     * @return boolean - true, if <code>range</code> matches the ValueMod or 
     * if ValueMod is not specified
     */
    private final boolean fitsValueMod(JDFNumberRange range) 
    {
        if (!hasAttribute(AttributeName.VALUEMOD)) 
            return true;
        
        JDFXYPair mod = getValueMod();
  
        double left = range.getLeft();
        double right = range.getRight();
        if (left!=right) // if we have a range return false, check only single value
            return false;
       
        double elem=left; // single value 
        double divi = mod.getX(); // X - the Modulo
        double shift = mod.getY(); // Y - offset of the allowed/present value

        if (divi==0) 
            return false;
            
        double nt; // negative tolerance
        double pt; // positive tolerance
        
        if (hasAttribute(AttributeName.TOLERANCE)) 
        {
            nt = getTolerance().getX(); 
            pt = getTolerance().getY();
        } 
        else 
        {
            nt = pt = EPSILON;
        }

        double n = ((elem-divi*(int)(elem/divi))-shift); // n = ( elem % divi - shift )
        if ( java.lang.Math.abs(n)<pt || java.lang.Math.abs(n)>(divi-nt) ) {
            return true;
        }

        double m = (n-divi*(int)(n/divi)); // m = ( elem % divi - shift ) % divi
        if  ( java.lang.Math.abs(m)<pt || java.lang.Math.abs(m)>(divi-nt) ) {
            return true;
        }
        return false;
    }

    /**
     * fitsTolerance - checks whether this Evaluation has a specified Tolerance 
     * that it is not equal to "0 0", and expands original the rangelist
     * to the rangelist that fits Tolerance.
     *
     * @param origRangeList original rangelist
     * @return NumberRangeList - expanded rangelist, returns original range if Tolerance=="0 0" 
     */
    public final JDFNumberRangeList fitsTolerance(JDFNumberRangeList origRangeList) 
    {
        double nt = getTolerance().getX(); // negative tolerance
        double pt = getTolerance().getY(); // positive tolerance
        
        if ((nt==0)&&(pt==0)) 
            return origRangeList;
        
        // expand our original range into the range +/- Tolerance

        JDFNumberRangeList rangeList = new JDFNumberRangeList(origRangeList);

        JDFNumberRangeList tolRangeList = new JDFNumberRangeList();
        
        int size = rangeList.size();
        for (int i=0; i<size; i++) 
        {
            JDFNumberRange range = (JDFNumberRange) rangeList.at(i);
            JDFNumberRange r = new JDFNumberRange();
            r.setLeft(range.getLeft()-nt);
            r.setRight(range.getRight()+pt);
            tolRangeList.append(r);
        }
        return tolRangeList;
    }
    
    /**
    * fitsCompleteList - tests whether <code>value</code> matches the given ValueList
    * (ListType=fitsCompleteList)
    *
    * @param value value to test
    * @param list  ValueList
    * @return boolean - true, if <code>value</code> matches the ValueList
    */
    private final boolean fitsCompleteList(JDFNumberRangeList value, JDFNumberRangeList list)
    {
        int v_size=value.size();
        int l_size=list.size();
        
        if (v_size!=l_size) 
            return false; 
        
        if (!value.isUnique()) 
            return false;
     
        JDFNumberRangeList valueList = new JDFNumberRangeList(value);

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
     * fitsCompleteOrderedList - tests whether <code>value</code> matches the given ValueList
     * (ListType=CompleteOrderedList)
     *
     * @param value value to test
     * @param list  ValueList
     * @return boolean - true, if <code>value</code> matches the ValueList
     */
    private final boolean fitsCompleteOrderedList(JDFNumberRangeList value, JDFNumberRangeList list)
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
    private final boolean fitsContainedList(JDFNumberRangeList value, JDFNumberRangeList list)
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