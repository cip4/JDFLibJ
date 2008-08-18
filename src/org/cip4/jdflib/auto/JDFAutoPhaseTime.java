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

import java.util.Collection;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFModulePhase;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFMISDetails;
import org.cip4.jdflib.util.JDFDate;

public abstract class JDFAutoPhaseTime extends JDFAudit
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.END, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.START, 0x22222222, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.STATUS, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumNodeStatus.getEnum(0), null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.STATUSDETAILS, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[5];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.DEVICE, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.EMPLOYEE, 0x33333333);
        elemInfoTable[2] = new ElemInfoTable(ElementName.MISDETAILS, 0x66666611);
        elemInfoTable[3] = new ElemInfoTable(ElementName.MODULEPHASE, 0x33333333);
        elemInfoTable[4] = new ElemInfoTable(ElementName.PART, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoPhaseTime
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPhaseTime(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPhaseTime
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPhaseTime(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPhaseTime
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPhaseTime(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoPhaseTime[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute End
        --------------------------------------------------------------------- */
        /**
          * (11) set attribute End
          * @param value: the value to set the attribute to or null
          */
        public void setEnd(JDFDate value)
        {
            JDFDate date = value;
            if (date == null) date = new JDFDate();
            setAttribute(AttributeName.END, date.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute End
          * @return JDFDate the value of the attribute
          */
        public JDFDate getEnd()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.END, null, JDFConstants.EMPTYSTRING);
            if (!JDFConstants.EMPTYSTRING.equals(str))
            {
                try
                {
                    nMyDate = new JDFDate(str);
                }
                catch(DataFormatException dfe)
                {
                    // throw new JDFException("not a valid date string. Malformed JDF - return null");
                }
            }
            return nMyDate;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Start
        --------------------------------------------------------------------- */
        /**
          * (11) set attribute Start
          * @param value: the value to set the attribute to or null
          */
        public void setStart(JDFDate value)
        {
            JDFDate date = value;
            if (date == null) date = new JDFDate();
            setAttribute(AttributeName.START, date.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute Start
          * @return JDFDate the value of the attribute
          */
        public JDFDate getStart()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.START, null, JDFConstants.EMPTYSTRING);
            if (!JDFConstants.EMPTYSTRING.equals(str))
            {
                try
                {
                    nMyDate = new JDFDate(str);
                }
                catch(DataFormatException dfe)
                {
                    // throw new JDFException("not a valid date string. Malformed JDF - return null");
                }
            }
            return nMyDate;
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

    /** (26) getCreateDevice
     * 
     * @param iSkip number of elements to skip
     * @return JDFDevice the element
     */
    public JDFDevice getCreateDevice(int iSkip)
    {
        return (JDFDevice)getCreateElement_KElement(ElementName.DEVICE, null, iSkip);
    }

    /**
     * (27) const get element Device
     * @param iSkip number of elements to skip
     * @return JDFDevice the element
     * default is getDevice(0)     */
    public JDFDevice getDevice(int iSkip)
    {
        return (JDFDevice) getElement(ElementName.DEVICE, null, iSkip);
    }

    /**
     * Get all Device from the current element
     * 
     * @return Collection<JDFDevice>
     */
    public Collection<JDFDevice> getAllDevice()
    {
        Vector<JDFDevice> v = new Vector<JDFDevice>();

        JDFDevice kElem = (JDFDevice) getFirstChildElement(ElementName.DEVICE, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFDevice) kElem.getNextSiblingElement(ElementName.DEVICE, null);
        }

        return v;
    }

    /**
     * (30) append element Device
     */
    public JDFDevice appendDevice() throws JDFException
    {
        return (JDFDevice) appendElement(ElementName.DEVICE, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refDevice(JDFDevice refTarget)
    {
        refElement(refTarget);
    }

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
     * Get all Employee from the current element
     * 
     * @return Collection<JDFEmployee>
     */
    public Collection<JDFEmployee> getAllEmployee()
    {
        Vector<JDFEmployee> v = new Vector<JDFEmployee>();

        JDFEmployee kElem = (JDFEmployee) getFirstChildElement(ElementName.EMPLOYEE, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFEmployee) kElem.getNextSiblingElement(ElementName.EMPLOYEE, null);
        }

        return v;
    }

    /**
     * (30) append element Employee
     */
    public JDFEmployee appendEmployee() throws JDFException
    {
        return (JDFEmployee) appendElement(ElementName.EMPLOYEE, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refEmployee(JDFEmployee refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element MISDetails
     * @return JDFMISDetails the element
     */
    public JDFMISDetails getMISDetails()
    {
        return (JDFMISDetails) getElement(ElementName.MISDETAILS, null, 0);
    }

    /** (25) getCreateMISDetails
     * 
     * @return JDFMISDetails the element
     */
    public JDFMISDetails getCreateMISDetails()
    {
        return (JDFMISDetails) getCreateElement_KElement(ElementName.MISDETAILS, null, 0);
    }

    /**
     * (29) append element MISDetails
     */
    public JDFMISDetails appendMISDetails() throws JDFException
    {
        return (JDFMISDetails) appendElementN(ElementName.MISDETAILS, 1, null);
    }

    /** (26) getCreateModulePhase
     * 
     * @param iSkip number of elements to skip
     * @return JDFModulePhase the element
     */
    public JDFModulePhase getCreateModulePhase(int iSkip)
    {
        return (JDFModulePhase)getCreateElement_KElement(ElementName.MODULEPHASE, null, iSkip);
    }

    /**
     * (27) const get element ModulePhase
     * @param iSkip number of elements to skip
     * @return JDFModulePhase the element
     * default is getModulePhase(0)     */
    public JDFModulePhase getModulePhase(int iSkip)
    {
        return (JDFModulePhase) getElement(ElementName.MODULEPHASE, null, iSkip);
    }

    /**
     * Get all ModulePhase from the current element
     * 
     * @return Collection<JDFModulePhase>
     */
    public Collection<JDFModulePhase> getAllModulePhase()
    {
        Vector<JDFModulePhase> v = new Vector<JDFModulePhase>();

        JDFModulePhase kElem = (JDFModulePhase) getFirstChildElement(ElementName.MODULEPHASE, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFModulePhase) kElem.getNextSiblingElement(ElementName.MODULEPHASE, null);
        }

        return v;
    }

    /**
     * (30) append element ModulePhase
     */
    public JDFModulePhase appendModulePhase() throws JDFException
    {
        return (JDFModulePhase) appendElement(ElementName.MODULEPHASE, null);
    }

    /** (26) getCreatePart
     * 
     * @param iSkip number of elements to skip
     * @return JDFPart the element
     */
    public JDFPart getCreatePart(int iSkip)
    {
        return (JDFPart)getCreateElement_KElement(ElementName.PART, null, iSkip);
    }

    /**
     * (27) const get element Part
     * @param iSkip number of elements to skip
     * @return JDFPart the element
     * default is getPart(0)     */
    public JDFPart getPart(int iSkip)
    {
        return (JDFPart) getElement(ElementName.PART, null, iSkip);
    }

    /**
     * Get all Part from the current element
     * 
     * @return Collection<JDFPart>
     */
    public Collection<JDFPart> getAllPart()
    {
        Vector<JDFPart> v = new Vector<JDFPart>();

        JDFPart kElem = (JDFPart) getFirstChildElement(ElementName.PART, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFPart) kElem.getNextSiblingElement(ElementName.PART, null);
        }

        return v;
    }

    /**
     * (30) append element Part
     */
    public JDFPart appendPart() throws JDFException
    {
        return (JDFPart) appendElement(ElementName.PART, null);
    }

}// end namespace JDF
