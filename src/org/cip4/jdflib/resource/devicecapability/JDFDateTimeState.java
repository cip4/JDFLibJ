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
 * JDFDateTimeState.java
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
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFDateTimeRange;
import org.cip4.jdflib.datatypes.JDFDateTimeRangeList;
import org.cip4.jdflib.datatypes.JDFDurationRangeList;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;


public class JDFDateTimeState extends JDFAbstractState
{
    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static 
    {
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.ALLOWEDVALUEDURATIONLIST, 0x33333311, AttributeInfo.EnumAttributeType.DurationRangeList, null, null);
        atrInfoTable[1]  = new AtrInfoTable(AttributeName.ALLOWEDVALUELIST,         0x33333311, AttributeInfo.EnumAttributeType.DateTimeRangeList, null, null);
        atrInfoTable[2]  = new AtrInfoTable(AttributeName.CURRENTVALUE,             0x33333311, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[3]  = new AtrInfoTable(AttributeName.DEFAULTVALUE,             0x33333311, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[4]  = new AtrInfoTable(AttributeName.PRESENTVALUEDURATIONLIST, 0x33333311, AttributeInfo.EnumAttributeType.DurationRangeList, null, null);
        atrInfoTable[5]  = new AtrInfoTable(AttributeName.PRESENTVALUELIST,         0x33333311, AttributeInfo.EnumAttributeType.DateTimeRangeList, null, null);
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
     * Constructor for JDFDateTimeState
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFDateTimeState(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFDateTimeState
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFDateTimeState(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFDateTimeState
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFDateTimeState(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
	
    /**
     * toString
     * @return String
     */
    public String toString()
    {
        return "JDFDateTimeState[  --> " + super.toString() + " ]";
    }
    
	/* ******************************************************
	// Attribute getter/ setter
	**************************************************************** */	
	
    /**
     * set attribute <code>CurrentValue</code>
     * @param value the value to set the attribute to
     */
    public void setCurrentValue(JDFDate value)
    {
        setAttribute(AttributeName.CURRENTVALUE, value.getDateTimeISO(), JDFConstants.EMPTYSTRING);
    }

  
    /**
     * get attribute <code>CurrentValue</code>
     * @return the value of the attribute
     */
    public JDFDate getCurrentValue()
    {
        JDFDate currentValue = null;
        String str = getAttribute(AttributeName.CURRENTVALUE, null, JDFConstants.EMPTYSTRING);
        if (!JDFConstants.EMPTYSTRING.equals(str))
        {
            try
            {
                currentValue = new JDFDate(str);
            }
            catch(DataFormatException dfe)
            {
                throw new JDFException("not a valid date string. Malformed JDF");
            }
        }
        return currentValue;
    }


    /**
     * set attribute <code>DefaultValue</code>
     * @param value the value to set the attribute to
     */
    public void setDefaultValue(JDFDate value)
    {
        setAttribute(AttributeName.DEFAULTVALUE, value.getDateTimeISO(), JDFConstants.EMPTYSTRING);
    }

    /**
     * get attribute <code>DefaultValue</code>
     * @return the value of the attribute
     */
    public JDFDate getDefaultValue()
    {
        JDFDate defaultValue = null;
        String str = getAttribute(AttributeName.DEFAULTVALUE, null, JDFConstants.EMPTYSTRING);
        if (!JDFConstants.EMPTYSTRING.equals(str))
        {
            try
            {
                defaultValue = new JDFDate(str);
            }
            catch(DataFormatException dfe)
            {
                throw new JDFException("not a valid date string. Malformed JDF");
            }
        }
        return defaultValue;
    }

    /**
     * set attribute <code>AllowedValueList</code>
     * @param value the value to set the attribute to
     */
    public void setAllowedValueList(JDFDateTimeRangeList value)
    {
		setAttribute(AttributeName.ALLOWEDVALUELIST, value.toString());
	}

    /**
     * get attribute <code>AllowedValueList</code>
     * @return the value of the attribute
     */
    public JDFDateTimeRangeList getAllowedValueList()  
    {
        try
        {
        	final String attribute = getAttribute(AttributeName.ALLOWEDVALUELIST,null,null);
            if(attribute!=null)
                return new JDFDateTimeRangeList(attribute);
        }
        catch(DataFormatException dfe)
        {
        	//nop
        }
        return null;
	}
	

    /**
     * set attribute <code>PresentValueList</code>
     * @param value the value to set the attribute to
     */
    public void setPresentValueList(JDFDateTimeRangeList value)
    {
		setAttribute(AttributeName.PRESENTVALUELIST, value.toString());
	}

	/**
	 * get attribute <code>PresentValueList</code>
	 * @return the value of the attribute
	 */
	public JDFDateTimeRangeList getPresentValueList()  
    {
		if (hasAttribute(AttributeName.PRESENTVALUELIST)) 
        {
            JDFDateTimeRangeList r = null;
            try
            {
            	r = new JDFDateTimeRangeList(getAttribute(AttributeName.PRESENTVALUELIST));
            }
            catch(DataFormatException dfe)
            {
            	return null;
            }
            return r;
		}
		return getAllowedValueList();
	}
	
	/**
	 * set attribute <code>AllowedValueDurationList</code>
	 * @param value the value of the attribute
	 */
	public void setAllowedValueDurationList(JDFDurationRangeList value)
    {
		setAttribute(AttributeName.ALLOWEDVALUEDURATIONLIST, value.toString());
	}
    
	/**
	 * get attribute <code>AllowedValueDurationList</code>
	 * @return the value of the attribute
	 */
	public JDFDurationRangeList getAllowedValueDurationList()  
	{
	    JDFDurationRangeList r = null;
	    
	    final String allowedValueDurList = getAttribute(AttributeName.ALLOWEDVALUEDURATIONLIST,null,null);
	    if(allowedValueDurList!=null)
	    {
	        try
	        {
	            r = new JDFDurationRangeList(allowedValueDurList);
	        }
	        catch(DataFormatException dfe)
	        {
	            return null;   
	        }
	    }
	    return r;
	}

    
	/**
	 * set attribute <code>PresentValueDurationList</code>
	 * @param value the value to set the attribute to
	 */
	public void setPresentValueDurationList(JDFDurationRangeList value)
    {
		setAttribute(AttributeName.PRESENTVALUEDURATIONLIST, value.toString());
	}
	
    
	/**
	 * get attribute <code>PresentValueDurationList</code>
	 * @return the value of the attribute
	 */
	public JDFDurationRangeList getPresentValueDurationList()  
    {
		if (hasAttribute(AttributeName.PRESENTVALUEDURATIONLIST)) 
        {
			JDFDurationRangeList r = null;
            try
            {
            	r = new JDFDurationRangeList(getAttribute(AttributeName.PRESENTVALUEDURATIONLIST));
            }
            catch(DataFormatException dfe)
            {
            	return null;
            }
            return r;
		}
		return getAllowedValueDurationList();
	}


	/* ******************************************************
	// Element getter / setter
	**************************************************************** */


	/**
    * fitsValue - tests, if the defined value matches Allowed test lists 
    * or Present test lists, specified for this State
    *
    * @param value     value to test
    * @param testlists the test lists the value has to match.
    * In this State the test lists are ValueList and ValueDurationList.<br>
    * Choose one of two values: FitsValue_Allowed or FitsValue_Present. (Defaults to Allowed)
    * 
    * @return boolean - true, if the value matches test list or if AllowedValueList is not specified
    */
    public boolean fitsValue(String value, EnumFitsValue testlists)
    {
        if (fitsListType(value))
        {
            JDFDateTimeRangeList rangelist = null;
            try 
            {
                rangelist = new JDFDateTimeRangeList(value);
            }
            catch (DataFormatException dfe)
            {
                return false;
            }
            
            return (fitsValueList(rangelist,testlists) && 
                    fitsValueDurationList(rangelist,testlists));
        }
        return false; // the value doesn't fit ListType attribute of this State
    }
    
    /**
     * fitsValueList - tests, if the defined 'rangelist' matches 
     * the AllowedValueList or in the PresentValueList, specified for this State
     *
     * @param rangelist range list to test
     * @param valuelist switches between AllowedValueList and PresentValueList
     * 
     * @return boolean - true, if <code>rangelist</code> matches the valuelist or if AllowedValueList is not specified
     */
    private final boolean fitsValueList(JDFDateTimeRangeList rangelist, EnumFitsValue valuelist)
    {
        JDFDateTimeRangeList list;
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

        return (list.isPartOfRange(rangelist));
    }
    
    /**
     * fitsValueDurationList - tests, if the duration of the defined 
     * <code>rangelist</code> value matchest the ValueDurationList, specified for this State
     *
     * @param rangelist range list to test
     * @param valuelist switches between AllowedValueList and PresentValueList
     * @return boolean - true, if the duration of the defined rangelist 
     * is in <code>valueList</code> or if ValueDurationList is not specified
     */
    private final boolean fitsValueDurationList(JDFDateTimeRangeList rangelist, EnumFitsValue valuelist)
    {
        JDFDurationRangeList list;

        if (valuelist.equals(EnumFitsValue.Allowed))
        {
            list = getAllowedValueDurationList();
        } 
        else {
            list = getPresentValueDurationList();
        }
        if(list == null)
            return true;

        int siz=rangelist.size();
        for (int i=0; i<siz; i++)
        {
            JDFDateTimeRange range = (JDFDateTimeRange) rangelist.at(i);

            int duration = (int)((range.getRight().getTimeInMillis()-range.getLeft().getTimeInMillis())/1000);
            JDFDuration d = new JDFDuration();
            d.setDuration(duration);
            if (!list.inRange(d))
                return false;
        }
        return true;
    }

    /**
     * fitsCompleteList - tests for the case, when ListType=CompleteList,
     * if <code>value</code> matches AllowedValueList or PresentValueList,
     * specified for this State
     *
     * @param value value to test
     * @param list  testlists are either AllowedValueList or PresentValueList
     * 
     * @return boolean - true, if <code>value</code> matches testlist
     */
    private final boolean fitsCompleteList(JDFDateTimeRangeList value, JDFDateTimeRangeList list)
    {
        int v_size=value.size();
        int l_size=list.size();
        
        if (v_size!=l_size) 
            return false; 
        
        if (!value.isUnique()) 
            return false;
     
        JDFDateTimeRangeList valueList = new JDFDateTimeRangeList(value);

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
    * @param value value to test
    * @param list  testlists are either AllowedValueList or PresentValueList
    * 
    * @return boolean - true, if <code>value</code> matches testlist
    */
    private final boolean fitsCompleteOrderedList(JDFDateTimeRangeList value, JDFDateTimeRangeList list)
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
    * if the defined <code>value</code> matches AllowedValueList or PresentValueList,
    * specified for this State
    *
    * @param value to test
    * @param testlists are either AllowedValueList or PresentValueList
    * 
    * @return boolean - true, if <code>value</code> matches testlist
    */
    private final boolean fitsContainedList(JDFDateTimeRangeList value, JDFDateTimeRangeList list)
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