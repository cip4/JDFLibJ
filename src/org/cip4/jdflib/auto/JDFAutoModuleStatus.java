/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2005 The International Cooperation for the Integration of
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

package org.cip4.jdflib.auto;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.resource.process.JDFEmployee;
    /*
    *****************************************************************************
    class JDFAutoModuleStatus : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoModuleStatus extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.COMBINEDPROCESSINDEX, 0x33333333, AttributeInfo.EnumAttributeType.IntegerList, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.DEVICESTATUS, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, JDFDeviceInfo.EnumDeviceStatus.getEnum(0), null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.MODULEID, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.MODULEINDEX, 0x22222222, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.MODULETYPE, 0x22222222, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.STATUSDETAILS, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.EMPLOYEE, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoModuleStatus
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoModuleStatus(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoModuleStatus
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoModuleStatus(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoModuleStatus
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoModuleStatus(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoModuleStatus[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute CombinedProcessIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CombinedProcessIndex
          * @param value: the value to set the attribute to
          */
        public void setCombinedProcessIndex(JDFIntegerList value)
        {
            setAttribute(AttributeName.COMBINEDPROCESSINDEX, value, null);
        }

        /**
          * (20) get JDFIntegerList attribute CombinedProcessIndex
          * @return JDFIntegerList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerList
          */
        public JDFIntegerList getCombinedProcessIndex()
        {
            String strAttrName = "";
            JDFIntegerList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.COMBINEDPROCESSINDEX, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFIntegerList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeviceStatus
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute DeviceStatus
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setDeviceStatus(JDFDeviceInfo.EnumDeviceStatus enumVar)
        {
            setAttribute(AttributeName.DEVICESTATUS, enumVar.getName(), null);
        }

        /**
          * (9) get attribute DeviceStatus
          * @return the value of the attribute
          */
        public JDFDeviceInfo.EnumDeviceStatus getDeviceStatus()
        {
            return JDFDeviceInfo.EnumDeviceStatus.getEnum(getAttribute(AttributeName.DEVICESTATUS, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ModuleID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ModuleID
          * @param value: the value to set the attribute to
          */
        public void setModuleID(String value)
        {
            setAttribute(AttributeName.MODULEID, value, null);
        }

        /**
          * (23) get String attribute ModuleID
          * @return the value of the attribute
          */
        public String getModuleID()
        {
            return getAttribute(AttributeName.MODULEID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ModuleIndex
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ModuleIndex
          * @param value: the value to set the attribute to
          */
        public void setModuleIndex(JDFIntegerRangeList value)
        {
            setAttribute(AttributeName.MODULEINDEX, value, null);
        }

        /**
          * (20) get JDFIntegerRangeList attribute ModuleIndex
          * @return JDFIntegerRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerRangeList
          */
        public JDFIntegerRangeList getModuleIndex()
        {
            String strAttrName = "";
            JDFIntegerRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.MODULEINDEX, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFIntegerRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ModuleType
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ModuleType
          * @param value: the value to set the attribute to
          */
        public void setModuleType(String value)
        {
            setAttribute(AttributeName.MODULETYPE, value, null);
        }

        /**
          * (23) get String attribute ModuleType
          * @return the value of the attribute
          */
        public String getModuleType()
        {
            return getAttribute(AttributeName.MODULETYPE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StatusDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StatusDetails
          * @param value: the value to set the attribute to
          */
        public void setStatusDetails(String value)
        {
            setAttribute(AttributeName.STATUSDETAILS, value, null);
        }

        /**
          * (23) get String attribute StatusDetails
          * @return the value of the attribute
          */
        public String getStatusDetails()
        {
            return getAttribute(AttributeName.STATUSDETAILS, null, JDFConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateEmployee
     * 
     * @param iSkip number of elements to skip
     * @return JDFEmployee the element
     */
    public JDFEmployee getCreateEmployee(int iSkip)
    {
        return (JDFEmployee)getCreateElement_KElement(ElementName.EMPLOYEE, null, iSkip);
    }

    /**
     * (27) const get element Employee
     * @param iSkip number of elements to skip
     * @return JDFEmployee the element
     * default is getEmployee(0)     */
    public JDFEmployee getEmployee(int iSkip)
    {
        return (JDFEmployee) getElement(ElementName.EMPLOYEE, null, iSkip);
    }

    /**
     * (30) append element Employee
     */
    public JDFEmployee appendEmployee() throws JDFException
    {
        return (JDFEmployee) appendElement(ElementName.EMPLOYEE, null);
    }

}// end namespace JDF
