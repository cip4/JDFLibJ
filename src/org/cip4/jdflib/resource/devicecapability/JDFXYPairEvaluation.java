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
 * JDFEvaluation.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.JDFXYPairRange;
import org.cip4.jdflib.datatypes.JDFXYPairRangeList;


public class JDFXYPairEvaluation extends JDFEvaluation
{
    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
    static 
    {
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.TOLERANCE, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, "0 0");
        atrInfoTable[1]  = new AtrInfoTable(AttributeName.VALUELIST, 0x33333333, AttributeInfo.EnumAttributeType.XYPairRangeList, null, null);
        atrInfoTable[2]  = new AtrInfoTable(AttributeName.XYRELATION,  0x33333333, AttributeInfo.EnumAttributeType.XYRelation, null, null);
    }

    protected AttributeInfo getTheAttributeInfo() 
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }

    /**
     * constructor for JDFXYPairEvaluation
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFXYPairEvaluation(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * constructor for JDFXYPairEvaluation
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFXYPairEvaluation(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * constructor for JDFXYPairEvaluation
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFXYPairEvaluation(
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
        return "JDFXYPairEvaluation[ --> " + super.toString() + " ]";
    }
    
    /* ******************************************************
    // Attribute getter/ setter
    **************************************************************** */

    public void setValueList(JDFXYPairRangeList value)
    {
        setAttribute(AttributeName.VALUELIST, value.toString(), null);
    }
    
    /** 
     * set the value list to exaxtly one value
     * @param value the xypair to set valuelist to
     */
    public void setValueList(JDFXYPair value)
    {
        setAttribute(AttributeName.VALUELIST, value.toString(), null);
    }

    /**
     * get attribute <code>ValueList</code>
     * @return JDFXYPairRangeList - the value of the attribute
     */
    public JDFXYPairRangeList getValueList()
    {
        try
        {
            final String valuelist = getAttribute(AttributeName.VALUELIST,null,null);
            if(valuelist==null)
                return null;
            JDFXYPairRangeList xyprl = new JDFXYPairRangeList(valuelist);
            return xyprl;   
        }
        catch(DataFormatException dfe)
        {
            throw new JDFException("JDFXYPairEvaluation.getValueList: Attribute VALUELIST is not applicable to create JDFXYPairRangeList");
        }
    }

    public void setXYRelation(EnumXYRelation value)
    {
        setAttribute(AttributeName.XYRELATION, value.getName(), null);
    }

    public JDFElement.EnumXYRelation getXYRelation()
    {
        return EnumXYRelation.getEnum(getAttribute(AttributeName.XYRELATION, null, null));
    }

    public void setTolerance(JDFXYPair value)
    {
        setAttribute( AttributeName.TOLERANCE,value.toString(), null);
    }

    public JDFXYPair getTolerance()
    {
        return super.getTolerance();
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
        {
            return false;
        }
        
        JDFXYPairRangeList rrl = null;
        try 
        {
            rrl = new JDFXYPairRangeList(value);
        }
        catch (DataFormatException dfe)
        {
            return false;
        }

        int siz=rrl.size();
        for (int i=0; i<siz; i++) // For every range, that rangelist consists of,
        {                         // we test both of range deliminators - right and left, if they fit XYRelation
                                  // In this case test of deliminators is sufficient for evaluation of the whole range
            JDFXYPairRange range=(JDFXYPairRange) rrl.at(i);
                
            JDFXYPair left = range.getLeft();
            JDFXYPair right = range.getRight();

            if (left.equals(right))
            {
                JDFXYPair xypair = left;
                if ((fitsValueList(new JDFXYPairRange(xypair)) &&
                        fitsXYRelation(xypair)) == false)
                    return false;
            }
            else {
                if ((fitsValueList(range) && 
                        fitsXYRelation(left) && 
                            fitsXYRelation(right))== false)
                    return false;
            }
        }
        return true; // all elements of rangelist fit
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

        JDFXYPairRangeList rangelist;
        try 
        {
            rangelist = new JDFXYPairRangeList(value);                       
        }
        catch (DataFormatException e)
        {
            return false;
        }
        catch (JDFException e)
        {
            return false;
        }
        
        if (listType==null || listType.equals(EnumListType.SingleValue)) 
        {// default ListType = SingleValue
            try
            {
                new JDFXYPair(value);
            }
            catch (JDFException e)
            {
                return false;
            }
            catch (DataFormatException ie)
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
        else if (listType.equals(EnumListType.List))  
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
            throw new JDFException ("JDFXYPairEvaluation.fitsListType illegal ListType attribute"); 
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
    private final boolean fitsValueList(JDFXYPairRange range)
    {
        if (!hasAttribute(AttributeName.VALUELIST))
            return true;

        JDFXYPairRangeList rangelist = getValueList();

        if (hasAttribute(AttributeName.TOLERANCE)) 
            return (fitsTolerance(rangelist).isPartOfRange(range));
        return rangelist.isPartOfRange(range);
    }
    
    /**
     * fitsTolerance - checks whether this Evaluation has a specified Tolerance 
     * that it is not equal to "0 0", and expands the original rangelist
     * to the rangelist that fits Tolerance.
     *
     * @param origRangeList original rangelist
     * @return NumberRangeList - expanded rangelist, returns original range if Tolerance=="0 0" 
     */
    private JDFXYPairRangeList fitsTolerance(JDFXYPairRangeList origRangeList)
    {
        final JDFXYPair tolerance = getTolerance();
        double nt = tolerance.getX(); // negative tolerance
        double pt = tolerance.getY(); // positive tolerance
        
        if ((nt==0)&&(pt==0)) 
            return origRangeList;
         
        // expand our original range into the range +/- Tolerance

        JDFXYPairRangeList rangeList = new JDFXYPairRangeList(origRangeList);
        
        JDFXYPairRangeList tolRangeList = new JDFXYPairRangeList();
        
        int size = rangeList.size();
        for (int i=0; i<size; i++) 
        {
            JDFXYPairRange range = (JDFXYPairRange) rangeList.at(i);
        
            JDFXYPair left=range.getLeft();
            double leftX=left.getX();
            double leftY=left.getY();
            left.setX( leftX - nt );
            left.setY( leftY - nt );
            
            JDFXYPair right=range.getRight();
            double rightX=right.getX();
            double rightY=right.getY();
            right.setX( rightX + pt );
            right.setY( rightY + pt );
            
            range.setLeft(left);
            range.setRight(right);
            
            tolRangeList.append(range);
        }
        
        return tolRangeList;
    }
    
    /**
    * fitsXYRelation - checks whether <code>xypair</code>matches the XYRelation
    * specified for this State
    *
    * @param xypair XYPair value to test
    * @return boolean - true, if <code>xypair</code> matches XYRelation or 
    * if XYRelation is not specified
    */
    private final boolean fitsXYRelation(JDFXYPair xypair)
    { 
        if (!hasAttribute(AttributeName.XYRELATION)) 
            return true;
        
        double x = xypair.getX();
        double y = xypair.getY();
        
        EnumXYRelation relation = getXYRelation();

        if (!hasAttribute(AttributeName.TOLERANCE))
            return relation.evaluateXY(x,y,EPSILON,EPSILON);
        
        double nt = getTolerance().getX(); // negative tolerance
        double pt = getTolerance().getY(); // positive tolerance
        
        return relation.evaluateXY(x,y,nt,pt);
    }
    
    
}