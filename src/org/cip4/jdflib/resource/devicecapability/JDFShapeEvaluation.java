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
 * JDFShapeEvaluation.java
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
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.datatypes.JDFShapeRange;
import org.cip4.jdflib.datatypes.JDFShapeRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.w3c.dom.DOMException;

public class JDFShapeEvaluation extends JDFEvaluation
{
    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
    static  
    {
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.TOLERANCE, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, "0 0");
        atrInfoTable[1]  = new AtrInfoTable(AttributeName.VALUELIST, 0x33333333, AttributeInfo.EnumAttributeType.ShapeRangeList, null, null);
        atrInfoTable[2]  = new AtrInfoTable(AttributeName.X,         0x33333333, AttributeInfo.EnumAttributeType.NumberRangeList, null, null);
        atrInfoTable[3]  = new AtrInfoTable(AttributeName.Y,         0x33333333, AttributeInfo.EnumAttributeType.NumberRangeList, null, null);
        atrInfoTable[4]  = new AtrInfoTable(AttributeName.Z,         0x33333333, AttributeInfo.EnumAttributeType.NumberRangeList, null, null);
    }

    protected AttributeInfo getTheAttributeInfo() 
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }

    /**
     * constructor for JDFShapeEvaluation
     * @param myOwnerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFShapeEvaluation(CoreDocumentImpl myOwnerDocument, String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * constructor for JDFShapeEvaluation
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFShapeEvaluation(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * constructor for JDFShapeEvaluation
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     * @throws DOMException
     */
    public JDFShapeEvaluation(
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
        return "JDFShapeEvaluation[  --> " + super.toString() + " ]";
    }
    
    /* ******************************************************
    // Attribute getter/ setter
    **************************************************************** */

    
    public void setValueList(JDFShapeRangeList  value)
    {
        setAttribute(AttributeName.VALUELIST, value.toString());
    }


    public JDFShapeRangeList getValueList()
    {
        try
        {
            JDFShapeRangeList srl = new JDFShapeRangeList(getAttribute(AttributeName.VALUELIST));
            return srl;    
        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFShapeEvaluation.getValueList: Attribute VALUELIST is not capable to create JDFShapeRangeList");
        }
    }


    public void setX(JDFNumberRangeList  value)
    {
        setAttribute(AttributeName.X, value.toString(), null);
    }


    public JDFNumberRangeList getX()
    {
        try
        {
            JDFNumberRangeList nrl = new JDFNumberRangeList(getAttribute(AttributeName.X));
            return nrl;
        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFShapeEvaluation.getX: Attribute X is not capable to create JDFNumberRangeList");
        }
    }


    public void setY(JDFNumberRangeList  value)
    {
        setAttribute(AttributeName.Y, value.toString(), null);
    }


    public JDFNumberRangeList getY()
    {
        try
        {
            JDFNumberRangeList nrl = new JDFNumberRangeList(getAttribute(AttributeName.Y));
            return nrl;
        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFShapeEvaluation.getY: Attribute Y is not capable to create JDFNumberRangeList");
        }
    }


    public void setZ(JDFNumberRangeList  value)
    {
        setAttribute(AttributeName.Z, value.toString(), null);
    }


    public JDFNumberRangeList getZ()
    {
        try
        {
            JDFNumberRangeList nrl = new JDFNumberRangeList(getAttribute(AttributeName.Z));
            return nrl;
        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFShapeEvaluation.getZ: Attribute Z is not capable to create JDFNumberRangeList");
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
        
        JDFShapeRangeList rrl = null;
        try 
        {
            rrl = new JDFShapeRangeList(value);
        }
        catch (DataFormatException dfe)
        {
            return false;
        }
    
        int siz=rrl.size();
        for (int i=0; i<siz; i++)
        {
            JDFShapeRange range=(JDFShapeRange) rrl.at(i);

            if ((fitsValueList(range) && fitsXYZ(range))== false)
                return false;
        }
        return true;
      
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

        JDFShapeRangeList rangelist;
        try 
        {
            rangelist = new JDFShapeRangeList(value);                       
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
            try
            {
                new JDFShape(value);
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
            throw new JDFException ("JDFShapeEvaluation.fitsListType illegal ListType attribute"); 
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
    private final boolean fitsValueList(JDFShapeRange range)
    {
        if (!hasAttribute(AttributeName.VALUELIST))
            return true;
        
        JDFShapeRangeList rangelist = getValueList();
        
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
    private JDFShapeRangeList fitsTolerance(JDFShapeRangeList origRangeList)
    {
        double nt = getTolerance().getX(); // negative tolerance
        double pt = getTolerance().getY(); // positive tolerance
        
        if ((nt==0)&&(pt==0)) 
            return origRangeList;
         
        // expand our original range into the range +/- Tolerance

        JDFShapeRangeList rangeList = new JDFShapeRangeList(origRangeList);
        
        JDFShapeRangeList tolRangeList = new JDFShapeRangeList();
        
        int size = rangeList.size();
        for (int i=0; i<size; i++) 
        {
            JDFShapeRange range = (JDFShapeRange) rangeList.at(i);
        
            JDFShape left=range.getLeft();
            double leftX=left.getHeight();
            double leftY=left.getWidth();
            double leftZ=left.getLength();
            left.setHeight( leftX - nt );
            left.setWidth( leftY - nt );
            left.setLength( leftZ - nt );
            
            JDFShape right=range.getRight();
            double rightX=right.getHeight();
            double rightY=right.getWidth();
            double rightZ=right.getLength();
            right.setHeight( rightX + pt );
            right.setWidth( rightY + pt );
            right.setLength( rightZ + pt );
            
            range.setLeft(left);
            range.setRight(right);
            
            tolRangeList.append(range);
        }
        
        return tolRangeList;
    }
    
    /**
     * fitsXYZ - checks wheterh <code>range</code> matches the test lists X, Y, Z,
     * specified for this Evaluation
     *
     * @param range range to test
     * @return boolean - true, if <code>range</code> matches test lists X, Y, Z or 
     * if X, Y, Z are not specified
     */
    private boolean fitsXYZ (JDFShapeRange range) 
    {
        JDFNumberRangeList x, y, z;

        JDFShape left = range.getLeft();
        JDFShape right = range.getRight();

        double leftX = left.getHeight();
        double rightX = right.getHeight();
        JDFNumberRange rangeX = new JDFNumberRange(leftX, rightX);

        double leftY = left.getWidth();
        double rightY = right.getWidth();
        JDFNumberRange rangeY = new JDFNumberRange(leftY, rightY);
        
        double leftZ = left.getLength();
        double rightZ = right.getLength();
        JDFNumberRange rangeZ = new JDFNumberRange(leftZ, rightZ);
      
        x=getX();
        y=getY();
        z=getZ();
       
        if (hasAttribute(AttributeName.TOLERANCE)) 
        {
            x = fitsXYZTolerance(x);
            y = fitsXYZTolerance(y);
            z = fitsXYZTolerance(z);
        }
        
        boolean bFit=true;
        if (x.size()!=0) 
        {
            bFit=x.isPartOfRange(rangeX);
        }
        if (!bFit)
            return false;

        if (y.size()!=0) 
        {
            bFit=y.isPartOfRange(rangeY);
        }
        if (!bFit)
            return false;

        if (z.size()!=0) 
        {
            bFit=z.isPartOfRange(rangeZ);
        }
        return bFit;

   }
     
    
   /**
    * fitsXYZTolerance - checks whether this Evaluation has a specified Tolerance 
    * that it is not equal "0 0", and expands the original rangelist to the 
    * rangelist that fits the Tolerance.
    *
    * @param rangeList original rangelist
    * @return JDFNumberRangeList - expanded rangelist, 
    *         returns original range if Tolerance=="0 0" 
    */
    public JDFNumberRangeList fitsXYZTolerance(JDFNumberRangeList origRangeList)
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
            JDFNumberRange range=(JDFNumberRange) rangeList.at(i);
            JDFNumberRange r = new JDFNumberRange();
            r.setLeft(range.getLeft()-nt);
            r.setRight(range.getRight()+pt);
            
            tolRangeList.append(r);
        }
        return tolRangeList;
    }
    
    
}       