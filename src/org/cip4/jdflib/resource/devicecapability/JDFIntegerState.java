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
 * JDFIntegerState.java
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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.util.StringUtil;

public class JDFIntegerState extends JDFAbstractState
{
    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
    static 
    {
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.ALLOWEDVALUELIST, 0x33333331, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[1]  = new AtrInfoTable(AttributeName.ALLOWEDVALUEMAX,  0x44444431, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[2]  = new AtrInfoTable(AttributeName.ALLOWEDVALUEMIN,  0x44444431, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[3]  = new AtrInfoTable(AttributeName.ALLOWEDVALUEMOD,  0x33333311, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[4]  = new AtrInfoTable(AttributeName.CURRENTVALUE,     0x33333331, AttributeInfo.EnumAttributeType.IntegerList, null, null);
        atrInfoTable[5]  = new AtrInfoTable(AttributeName.DEFAULTVALUE,     0x33333331, AttributeInfo.EnumAttributeType.IntegerList, null, null);
        atrInfoTable[6]  = new AtrInfoTable(AttributeName.PRESENTVALUELIST, 0x33333331, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[7]  = new AtrInfoTable(AttributeName.PRESENTVALUEMAX,  0x44444431, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[8]  = new AtrInfoTable(AttributeName.PRESENTVALUEMIN,  0x44444431, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[9]  = new AtrInfoTable(AttributeName.PRESENTVALUEMOD,  0x33333311, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.UNITTYPE,         0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
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
     * constructor for JDFIntegerState
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFIntegerState(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * constructor for JDFIntegerState
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFIntegerState(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * constructor for JDFIntegerState
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFIntegerState(
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
        return "JDFIntegerState[ --> " + super.toString() + " ]";
    }

    /* ******************************************************
	// Attribute getter/ setter
     **************************************************************** */	

    public void setDefaultValue(int value)
    {
        setAttribute(AttributeName.DEFAULTVALUE, value, null);
    }
    public void setDefaultValue(JDFIntegerList value)
    {
        setAttribute(AttributeName.DEFAULTVALUE, value, null);
    }
    public void setCurrentValue(JDFIntegerList value)
    {
        setAttribute(AttributeName.CURRENTVALUE, value, null);
    }

    public  JDFIntegerList getDefaultValue()  
    {
        return getIntegerList(AttributeName.DEFAULTVALUE);
    }

    public void setCurrentValue(int value)
    {
        setAttribute(AttributeName.CURRENTVALUE, value, null);
    }

    public  JDFIntegerList getCurrentValue()  
    {
        return getIntegerList(AttributeName.CURRENTVALUE);
    }

    public void setAllowedValueList( JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.ALLOWEDVALUELIST, value.toString(), null);
    }

    /**
     * get the allowedValueList
     * @return the allowedValuelist, <code>null</code> if no list exists
     */
    public JDFIntegerRangeList getAllowedValueList()  
    {
        return getIntegerRangeList(AttributeName.ALLOWEDVALUELIST);
    }

    public void setPresentValueList( JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.PRESENTVALUELIST, value.toString(), null);
    }

    public JDFIntegerRangeList getPresentValueList()  
    {
        JDFIntegerRangeList il=getIntegerRangeList(AttributeName.PRESENTVALUELIST);
        return (il==null) ? getAllowedValueList() : il;            
    }

    /**
     * @param listName
     * @return
     */
    private JDFIntegerRangeList getIntegerRangeList(final String listName)
    {
        try
        {
            final String attribute = getAttribute(listName, null, null);
            if(attribute==null)
                return null;
            return new JDFIntegerRangeList(attribute);
        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFIntegerState.getIntegerRangeList, Unable to create JDFIntegerRangeList from Attribute value: "+listName);
        }
    }
    /**
     * @param listName
     * @return
     */
    private JDFIntegerList getIntegerList(final String listName)
    {
        try
        {
            final String attribute = getAttribute(listName, null, null);
            if(attribute==null)
                return null;
            return new JDFIntegerList(attribute);
        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFIntegerState.getIntegerList, Unable to create JDFIntegerRangeList from Attribute value: "+listName);
        }
    }

    public void setAllowedValueMax(int value)
    {
        setAttribute(AttributeName.ALLOWEDVALUEMAX, value, null);
    }

    public int getAllowedValueMax()  
    {
        return getIntAttribute(AttributeName.ALLOWEDVALUEMAX, null, 0);
    }

    public void setPresentValueMax(int value)
    {
        setAttribute(AttributeName.PRESENTVALUEMAX, value, null);
    }


    public int getPresentValueMax()  
    {
        if (hasAttribute(AttributeName.PRESENTVALUEMAX))
        {
            return getIntAttribute(AttributeName.PRESENTVALUEMAX, null, 0);
        }
        return getAllowedValueMax();
    }

    public void setAllowedValueMin(int value)
    {
        setAttribute(AttributeName.ALLOWEDVALUEMIN, value, null);
    }


    public int getAllowedValueMin()  
    {
        return getIntAttribute(AttributeName.ALLOWEDVALUEMIN, null, 0);
    }


    public void setPresentValueMin(int value)
    {
        setAttribute(AttributeName.PRESENTVALUEMIN, value, null);
    }

    public int getPresentValueMin()  
    {
        if (hasAttribute(AttributeName.PRESENTVALUEMIN))
        {
            return getIntAttribute(AttributeName.PRESENTVALUEMIN, null, 0);
        }
        return getAllowedValueMin();
    }


    public void setAllowedValueMod(JDFXYPair value)
    {
        setAttribute(AttributeName.ALLOWEDVALUEMOD, value.toString());
    }


    public JDFXYPair getAllowedValueMod()  
    {
        try
        {
            return new JDFXYPair(getAttribute(AttributeName.ALLOWEDVALUEMOD));
        }
        catch (DataFormatException e)
        {
            throw new JDFException("JDFIntegerState.getAllowedValueMod: The XYPair value is invalid!");      
        }
    }

    public void setPresentValueMod(JDFXYPair value)
    {
        setAttribute(AttributeName.PRESENTVALUEMOD, value.toString());
    }


    public JDFXYPair getPresentValueMod()  
    {
        try
        {
            if (hasAttribute(AttributeName.PRESENTVALUEMOD)) 
            {
                return new JDFXYPair(getAttribute(AttributeName.PRESENTVALUEMOD));
            }
            return getAllowedValueMod();
        }
        catch (DataFormatException e)
        {
            throw new JDFException("The XYPair value is invalid!");      
        }

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

    /* (non-Javadoc)
     * @see org.cip4.jdflib.resource.devicecapability.JDFAbstractState#addValue(java.lang.String, org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue)
     */
    public void addValue(String value, EnumFitsValue testlists)
    {
        if(fitsValue(value, testlists))
            return;

        if(!StringUtil.isInteger(value))
            return;
        int i=StringUtil.parseInt(value, 0);

        if(testlists==null || EnumFitsValue.Allowed.equals(testlists))
        {
            JDFIntegerRangeList list=getAllowedValueList();
            if(list==null)
                list=new JDFIntegerRangeList();
            list.append (i);
            list.normalize(true);
            setAllowedValueList(list);

        }
        if(testlists==null || EnumFitsValue.Present.equals(testlists))
        {
            JDFIntegerRangeList list=getPresentValueList();
            if(list==null || !hasAttribute(AttributeName.PRESENTVALUELIST))
                list=new JDFIntegerRangeList();
            list.append (i);
            list.normalize(true);
            setPresentValueList(list);
        }
    }
    /**
     * fitsValue - checks whether <code>value</code> matches the given test lists
     *
     * @param value     value to test
     * @param testlists test lists the value has to match.
     *            In this State the test lists are ValueList AND ValueMod.<br> 
     *            Choose one of two values: FitsValue_Allowed or FitsValue_Present. Defaults to Allowed.
     * 
     * @return boolean - true, if <code>value</code> matches the testlists or 
     *            if AllowedValueList and AllowedValueMod are not specified
     */
    public boolean fitsValue(String value, EnumFitsValue testlists)
    {
        boolean testResult = true;

        if (!fitsListType(value))
            testResult = false;
        else 
        {
            JDFIntegerRangeList rangelist = null;
            try 
            {
                rangelist = new JDFIntegerRangeList(value);
            }
            catch (DataFormatException dfe)
            {
                testResult = false;
            }
            if (testResult)
                testResult = (fitsValueList(rangelist, testlists) && fitsValueMod(rangelist, testlists));
        }
        return testResult;
    }


    /**
     * fitsValueList - checks whether <code>rangelist</code> matches 
     *           the AllowedValueList/PresentValueList, specified for this State
     *
     * @param rangelist range list to test
     * @param valuelist switches between AllowedValueList and PresentValueList
     * 
     * @return boolean - true, if <code>rangelist</code> matches the valuelist or if AllowedValueList is not specified
     */
    private final boolean fitsValueList(JDFIntegerRangeList rangelist, EnumFitsValue valuelist)
    {
        JDFIntegerRangeList list = null;
        if (valuelist.equals(EnumFitsValue.Allowed))
        {
            list = getAllowedValueList();
        } 
        else {
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
            JDFIntegerRange range = (JDFIntegerRange) rangelist.at(i);

            // if range looks like"0~-1" but no xdef, then we assume that xdef=lastIntegerInList 
            int lastInList = ((JDFIntegerRange) list.at(list.size()-1)).getRight();
            int leftInRange = range.getLeft();
            int rightInRange = range.getRight();
            if (lastInList>0  && 
                    (( rightInRange < 0 && java.lang.Math.abs(rightInRange)< lastInList ) || 
                            ( leftInRange  < 0 && java.lang.Math.abs(leftInRange) < lastInList )) ) 
            {
                range.setDef(lastInList);
            }
            if (!list.isPartOfRange(range))
                return false;
        }
        return true;
    }

    /**
     * fitsValueMod - checks whether <code>rangelist</code> matches 
     * AllowedValueMod/PresentValueMod, specified for this State
     *
     * @param rangelist range list to test
     * @param valuemod  switches between AllowedValueMod and PresentValueMod.
     * 
     * @return boolean - true, if <code>rangelist</code> matches the <code>valuemod</code> or if AllowedValueMod is not specified
     */
    private final boolean fitsValueMod(JDFIntegerRangeList rangelist, EnumFitsValue valuemod) 
    {
        if (valuemod.equals(EnumFitsValue.Allowed)) 
        {
            if(!hasAttribute(AttributeName.ALLOWEDVALUEMOD))
                return true;
        } 
        else 
        {
            if(!hasAttribute(AttributeName.ALLOWEDVALUEMOD)&&!hasAttribute(AttributeName.PRESENTVALUEMOD))
                return true;
        }

        JDFXYPair mod;
        if (valuemod.equals(EnumFitsValue.Allowed)) 
        {
            mod = getAllowedValueMod();
        } 
        else 
        {
            mod = getPresentValueMod();
        }

        int divi = (int)(mod.getX()+0.5); // X - the Modulo
        int shift = (int)(mod.getY()+0.5); // Y - offset of the allowed/present value

        if (divi==0)   // ValueMod can't be "0 x"  
            return false;


        JDFIntegerList v=rangelist.getIntegerList();
        int[] vi = v.getIntArray();
        int siz=vi.length;
        for (int i=0; i<siz; i++)
        {
            if ( (((vi[i] % divi)-shift) % divi) != 0 )
                return false;
        }
        return true;
    }

    /**
     * fitsCompleteList - tests whether <code>value</code> matches the given testlist
     * (ListType=CompleteList)
     *
     * @param value value to test
     * @param list  testlist, either AllowedValueList or PresentValueList.
     * 
     * @return boolean - true, if <code>value</code> matches the testlist
     */
    private final boolean fitsCompleteList(JDFIntegerRangeList value, JDFIntegerRangeList list)
    {
        int v_size=value.size();
        int l_size=list.size();

        if (v_size!=l_size) 
            return false; 

        if (!value.isUnique()) 
            return false;

        JDFIntegerRangeList valueList = new JDFIntegerRangeList(value);

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
    private final boolean fitsCompleteOrderedList(JDFIntegerRangeList value, JDFIntegerRangeList list)
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
     * fitsContainedList - tests whether <code>value</code> matches the given testlist
     * (ListType=ContainedList)
     *
     * @param value value to test
     * @param list  testlist, either AllowedValueList or PresentValueList.
     * 
     * @return boolean - true, if <code>value</code> matches the testlist
     */
    private final boolean fitsContainedList(JDFIntegerRangeList value, JDFIntegerRangeList list)
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

    public VString getInvalidAttributes(EnumValidationLevel level, boolean bIgnorePrivate, int nMax)
    {
        return getInvalidAttributesImpl(level, bIgnorePrivate, nMax);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    protected Object clone() throws CloneNotSupportedException
    {
        // TODO Auto-generated method stub
        return super.clone();
    }


}