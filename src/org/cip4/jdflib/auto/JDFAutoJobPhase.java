/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.enums.ValuedEnum;
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
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.resource.JDFModuleStatus;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.process.JDFCostCenter;
import org.cip4.jdflib.resource.process.JDFMISDetails;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
    /**
    *****************************************************************************
    class JDFAutoJobPhase : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoJobPhase extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[18];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTIVATION, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumActivation.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.AMOUNT, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.DEADLINE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumDeadLine.getEnum(0), null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.JOBID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.JOBPARTID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.PERCENTCOMPLETED, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.PHASEAMOUNT, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.PHASESTARTTIME, 0x33333311, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.PHASEWASTE, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.QUEUEENTRYID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.RESTTIME, 0x33333331, AttributeInfo.EnumAttributeType.duration, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.SPEED, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.STARTTIME, 0x33333331, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.STATUS, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumNodeStatus.getEnum(0), null);
        atrInfoTable[14] = new AtrInfoTable(AttributeName.STATUSDETAILS, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[15] = new AtrInfoTable(AttributeName.TOTALAMOUNT, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[16] = new AtrInfoTable(AttributeName.URL, 0x33331111, AttributeInfo.EnumAttributeType.URL, null, null);
        atrInfoTable[17] = new AtrInfoTable(AttributeName.WASTE, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COSTCENTER, 0x66666666);
        elemInfoTable[1] = new ElemInfoTable(ElementName.MISDETAILS, 0x66666611);
        elemInfoTable[2] = new ElemInfoTable(ElementName.MODULESTATUS, 0x33333111);
        elemInfoTable[3] = new ElemInfoTable(ElementName.PART, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoJobPhase
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoJobPhase(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoJobPhase
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoJobPhase(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoJobPhase
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoJobPhase(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoJobPhase[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for Activation
        */

        public static class EnumActivation extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumActivation(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumActivation getEnum(String enumName)
            {
                return (EnumActivation) getEnum(EnumActivation.class, enumName);
            }

            public static EnumActivation getEnum(int enumValue)
            {
                return (EnumActivation) getEnum(EnumActivation.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumActivation.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumActivation.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumActivation.class);
            }

            public static final EnumActivation Inactive = new EnumActivation("Inactive");
            public static final EnumActivation Informative = new EnumActivation("Informative");
            public static final EnumActivation Held = new EnumActivation("Held");
            public static final EnumActivation Active = new EnumActivation("Active");
            public static final EnumActivation TestRun = new EnumActivation("TestRun");
            public static final EnumActivation TestRunAndGo = new EnumActivation("TestRunAndGo");
        }      



        /**
        * Enumeration strings for DeadLine
        */

        public static class EnumDeadLine extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumDeadLine(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumDeadLine getEnum(String enumName)
            {
                return (EnumDeadLine) getEnum(EnumDeadLine.class, enumName);
            }

            public static EnumDeadLine getEnum(int enumValue)
            {
                return (EnumDeadLine) getEnum(EnumDeadLine.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumDeadLine.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumDeadLine.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumDeadLine.class);
            }

            public static final EnumDeadLine InTime = new EnumDeadLine("InTime");
            public static final EnumDeadLine Warning = new EnumDeadLine("Warning");
            public static final EnumDeadLine Late = new EnumDeadLine("Late");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Activation
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Activation
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setActivation(EnumActivation enumVar)
        {
            setAttribute(AttributeName.ACTIVATION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Activation
          * @return the value of the attribute
          */
        public EnumActivation getActivation()
        {
            return EnumActivation.getEnum(getAttribute(AttributeName.ACTIVATION, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Amount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Amount
          * @param value: the value to set the attribute to
          */
        public void setAmount(double value)
        {
            setAttribute(AttributeName.AMOUNT, value, null);
        }

        /**
          * (17) get double attribute Amount
          * @return double the value of the attribute
          */
        public double getAmount()
        {
            return getRealAttribute(AttributeName.AMOUNT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeadLine
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute DeadLine
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setDeadLine(EnumDeadLine enumVar)
        {
            setAttribute(AttributeName.DEADLINE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute DeadLine
          * @return the value of the attribute
          */
        public EnumDeadLine getDeadLine()
        {
            return EnumDeadLine.getEnum(getAttribute(AttributeName.DEADLINE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute JobID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute JobID
          * @param value: the value to set the attribute to
          */
        public void setJobID(String value)
        {
            setAttribute(AttributeName.JOBID, value, null);
        }

        /**
          * (23) get String attribute JobID
          * @return the value of the attribute
          */
        public String getJobID()
        {
            return getAttribute(AttributeName.JOBID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute JobPartID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute JobPartID
          * @param value: the value to set the attribute to
          */
        public void setJobPartID(String value)
        {
            setAttribute(AttributeName.JOBPARTID, value, null);
        }

        /**
          * (23) get String attribute JobPartID
          * @return the value of the attribute
          */
        public String getJobPartID()
        {
            return getAttribute(AttributeName.JOBPARTID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PercentCompleted
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PercentCompleted
          * @param value: the value to set the attribute to
          */
        public void setPercentCompleted(double value)
        {
            setAttribute(AttributeName.PERCENTCOMPLETED, value, null);
        }

        /**
          * (17) get double attribute PercentCompleted
          * @return double the value of the attribute
          */
        public double getPercentCompleted()
        {
            return getRealAttribute(AttributeName.PERCENTCOMPLETED, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PhaseAmount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PhaseAmount
          * @param value: the value to set the attribute to
          */
        public void setPhaseAmount(double value)
        {
            setAttribute(AttributeName.PHASEAMOUNT, value, null);
        }

        /**
          * (17) get double attribute PhaseAmount
          * @return double the value of the attribute
          */
        public double getPhaseAmount()
        {
            return getRealAttribute(AttributeName.PHASEAMOUNT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PhaseStartTime
        --------------------------------------------------------------------- */
        /**
          * (11) set attribute PhaseStartTime
          * @param value: the value to set the attribute to or null
          */
        public void setPhaseStartTime(JDFDate value)
        {
            JDFDate date = value;
            if (date == null) date = new JDFDate();
            setAttribute(AttributeName.PHASESTARTTIME, date.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute PhaseStartTime
          * @return JDFDate the value of the attribute
          */
        public JDFDate getPhaseStartTime()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.PHASESTARTTIME, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute PhaseWaste
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PhaseWaste
          * @param value: the value to set the attribute to
          */
        public void setPhaseWaste(double value)
        {
            setAttribute(AttributeName.PHASEWASTE, value, null);
        }

        /**
          * (17) get double attribute PhaseWaste
          * @return double the value of the attribute
          */
        public double getPhaseWaste()
        {
            return getRealAttribute(AttributeName.PHASEWASTE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute QueueEntryID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute QueueEntryID
          * @param value: the value to set the attribute to
          */
        public void setQueueEntryID(String value)
        {
            setAttribute(AttributeName.QUEUEENTRYID, value, null);
        }

        /**
          * (23) get String attribute QueueEntryID
          * @return the value of the attribute
          */
        public String getQueueEntryID()
        {
            return getAttribute(AttributeName.QUEUEENTRYID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RestTime
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RestTime
          * @param value: the value to set the attribute to
          */
        public void setRestTime(JDFDuration value)
        {
            setAttribute(AttributeName.RESTTIME, value, null);
        }

        /**
          * (20) get JDFDuration attribute RestTime
          * @return JDFDuration the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFDuration
          */
        public JDFDuration getRestTime()
        {
            String strAttrName = "";
            JDFDuration nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.RESTTIME, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFDuration(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Speed
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Speed
          * @param value: the value to set the attribute to
          */
        public void setSpeed(double value)
        {
            setAttribute(AttributeName.SPEED, value, null);
        }

        /**
          * (17) get double attribute Speed
          * @return double the value of the attribute
          */
        public double getSpeed()
        {
            return getRealAttribute(AttributeName.SPEED, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StartTime
        --------------------------------------------------------------------- */
        /**
          * (11) set attribute StartTime
          * @param value: the value to set the attribute to or null
          */
        public void setStartTime(JDFDate value)
        {
            JDFDate date = value;
            if (date == null) date = new JDFDate();
            setAttribute(AttributeName.STARTTIME, date.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute StartTime
          * @return JDFDate the value of the attribute
          */
        public JDFDate getStartTime()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.STARTTIME, null, JDFConstants.EMPTYSTRING);
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

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TotalAmount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TotalAmount
          * @param value: the value to set the attribute to
          */
        public void setTotalAmount(double value)
        {
            setAttribute(AttributeName.TOTALAMOUNT, value, null);
        }

        /**
          * (17) get double attribute TotalAmount
          * @return double the value of the attribute
          */
        public double getTotalAmount()
        {
            return getRealAttribute(AttributeName.TOTALAMOUNT, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute URL
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute URL
          * @param value: the value to set the attribute to
          */
        public void setURL(String value)
        {
            setAttribute(AttributeName.URL, value, null);
        }

        /**
          * (23) get String attribute URL
          * @return the value of the attribute
          */
        public String getURL()
        {
            return getAttribute(AttributeName.URL, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Waste
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Waste
          * @param value: the value to set the attribute to
          */
        public void setWaste(double value)
        {
            setAttribute(AttributeName.WASTE, value, null);
        }

        /**
          * (17) get double attribute Waste
          * @return double the value of the attribute
          */
        public double getWaste()
        {
            return getRealAttribute(AttributeName.WASTE, null, 0.0);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element CostCenter
     * @return JDFCostCenter the element
     */
    public JDFCostCenter getCostCenter()
    {
        return (JDFCostCenter) getElement(ElementName.COSTCENTER, null, 0);
    }

    /** (25) getCreateCostCenter
     * 
     * @return JDFCostCenter the element
     */
    public JDFCostCenter getCreateCostCenter()
    {
        return (JDFCostCenter) getCreateElement_KElement(ElementName.COSTCENTER, null, 0);
    }

    /**
     * (29) append element CostCenter
     */
    public JDFCostCenter appendCostCenter() throws JDFException
    {
        return (JDFCostCenter) appendElementN(ElementName.COSTCENTER, 1, null);
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

    /** (26) getCreateModuleStatus
     * 
     * @param iSkip number of elements to skip
     * @return JDFModuleStatus the element
     */
    public JDFModuleStatus getCreateModuleStatus(int iSkip)
    {
        return (JDFModuleStatus)getCreateElement_KElement(ElementName.MODULESTATUS, null, iSkip);
    }

    /**
     * (27) const get element ModuleStatus
     * @param iSkip number of elements to skip
     * @return JDFModuleStatus the element
     * default is getModuleStatus(0)     */
    public JDFModuleStatus getModuleStatus(int iSkip)
    {
        return (JDFModuleStatus) getElement(ElementName.MODULESTATUS, null, iSkip);
    }

    /**
     * Get all ModuleStatus from the current element
     * 
     * @return Collection<JDFModuleStatus>, null if none are available
     */
    public Collection<JDFModuleStatus> getAllModuleStatus()
    {
        final VElement vc = getChildElementVector(ElementName.MODULESTATUS, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFModuleStatus> v = new Vector<JDFModuleStatus>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFModuleStatus) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element ModuleStatus
     */
    public JDFModuleStatus appendModuleStatus() throws JDFException
    {
        return (JDFModuleStatus) appendElement(ElementName.MODULESTATUS, null);
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
     * @return Collection<JDFPart>, null if none are available
     */
    public Collection<JDFPart> getAllPart()
    {
        final VElement vc = getChildElementVector(ElementName.PART, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFPart> v = new Vector<JDFPart>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFPart) vc.get(i));
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
