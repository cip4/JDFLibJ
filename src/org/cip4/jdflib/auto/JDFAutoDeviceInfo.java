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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFModuleStatus;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
    /*
    *****************************************************************************
    class JDFAutoDeviceInfo : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoDeviceInfo extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.COUNTERUNIT, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.DEVICECONDITION, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumDeviceCondition.getEnum(0), null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.DEVICEID, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.DEVICEOPERATIONMODE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumDeviceOperationMode.getEnum(0), null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.DEVICESTATUS, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumDeviceStatus.getEnum(0), null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.HOURCOUNTER, 0x33333333, AttributeInfo.EnumAttributeType.duration, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.POWERONTIME, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.PRODUCTIONCOUNTER, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.SPEED, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.STATUSDETAILS, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.TOTALPRODUCTIONCOUNTER, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.DEVICE, 0x66666666);
        elemInfoTable[1] = new ElemInfoTable(ElementName.EMPLOYEE, 0x33333333);
        elemInfoTable[2] = new ElemInfoTable(ElementName.JOBPHASE, 0x33333333);
        elemInfoTable[3] = new ElemInfoTable(ElementName.MODULESTATUS, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoDeviceInfo
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoDeviceInfo(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoDeviceInfo
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoDeviceInfo(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoDeviceInfo
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoDeviceInfo(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoDeviceInfo[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for DeviceCondition
        */

        public static class EnumDeviceCondition extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumDeviceCondition(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumDeviceCondition getEnum(String enumName)
            {
                return (EnumDeviceCondition) getEnum(EnumDeviceCondition.class, enumName);
            }

            public static EnumDeviceCondition getEnum(int enumValue)
            {
                return (EnumDeviceCondition) getEnum(EnumDeviceCondition.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumDeviceCondition.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumDeviceCondition.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumDeviceCondition.class);
            }

            public static final EnumDeviceCondition OK = new EnumDeviceCondition("OK");
            public static final EnumDeviceCondition NeedsAttention = new EnumDeviceCondition("NeedsAttention");
            public static final EnumDeviceCondition Failure = new EnumDeviceCondition("Failure");
            public static final EnumDeviceCondition OffLine = new EnumDeviceCondition("OffLine");
        }      



        /**
        * Enumeration strings for DeviceOperationMode
        */

        public static class EnumDeviceOperationMode extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumDeviceOperationMode(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumDeviceOperationMode getEnum(String enumName)
            {
                return (EnumDeviceOperationMode) getEnum(EnumDeviceOperationMode.class, enumName);
            }

            public static EnumDeviceOperationMode getEnum(int enumValue)
            {
                return (EnumDeviceOperationMode) getEnum(EnumDeviceOperationMode.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumDeviceOperationMode.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumDeviceOperationMode.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumDeviceOperationMode.class);
            }

            public static final EnumDeviceOperationMode Productive = new EnumDeviceOperationMode("Productive");
            public static final EnumDeviceOperationMode NonProductive = new EnumDeviceOperationMode("NonProductive");
            public static final EnumDeviceOperationMode Maintenance = new EnumDeviceOperationMode("Maintenance");
        }      



        /**
        * Enumeration strings for DeviceStatus
        */

        public static class EnumDeviceStatus extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumDeviceStatus(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumDeviceStatus getEnum(String enumName)
            {
                return (EnumDeviceStatus) getEnum(EnumDeviceStatus.class, enumName);
            }

            public static EnumDeviceStatus getEnum(int enumValue)
            {
                return (EnumDeviceStatus) getEnum(EnumDeviceStatus.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumDeviceStatus.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumDeviceStatus.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumDeviceStatus.class);
            }

            public static final EnumDeviceStatus Unknown = new EnumDeviceStatus("Unknown");
            public static final EnumDeviceStatus Idle = new EnumDeviceStatus("Idle");
            public static final EnumDeviceStatus Down = new EnumDeviceStatus("Down");
            public static final EnumDeviceStatus Setup = new EnumDeviceStatus("Setup");
            public static final EnumDeviceStatus Running = new EnumDeviceStatus("Running");
            public static final EnumDeviceStatus Cleanup = new EnumDeviceStatus("Cleanup");
            public static final EnumDeviceStatus Stopped = new EnumDeviceStatus("Stopped");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute CounterUnit
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CounterUnit
          * @param value: the value to set the attribute to
          */
        public void setCounterUnit(String value)
        {
            setAttribute(AttributeName.COUNTERUNIT, value, null);
        }



        /**
          * (23) get String attribute CounterUnit
          * @return the value of the attribute
          */
        public String getCounterUnit()
        {
            return getAttribute(AttributeName.COUNTERUNIT, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeviceCondition
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute DeviceCondition
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setDeviceCondition(EnumDeviceCondition enumVar)
        {
            setAttribute(AttributeName.DEVICECONDITION, enumVar.getName(), null);
        }



        /**
          * (9) get attribute DeviceCondition
          * @return the value of the attribute
          */
        public EnumDeviceCondition getDeviceCondition()
        {
            return EnumDeviceCondition.getEnum(getAttribute(AttributeName.DEVICECONDITION, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeviceID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DeviceID
          * @param value: the value to set the attribute to
          */
        public void setDeviceID(String value)
        {
            setAttribute(AttributeName.DEVICEID, value, null);
        }



        /**
          * (23) get String attribute DeviceID
          * @return the value of the attribute
          */
        public String getDeviceID()
        {
            return getAttribute(AttributeName.DEVICEID, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeviceOperationMode
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute DeviceOperationMode
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setDeviceOperationMode(EnumDeviceOperationMode enumVar)
        {
            setAttribute(AttributeName.DEVICEOPERATIONMODE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute DeviceOperationMode
          * @return the value of the attribute
          */
        public EnumDeviceOperationMode getDeviceOperationMode()
        {
            return EnumDeviceOperationMode.getEnum(getAttribute(AttributeName.DEVICEOPERATIONMODE, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute DeviceStatus
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute DeviceStatus
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setDeviceStatus(EnumDeviceStatus enumVar)
        {
            setAttribute(AttributeName.DEVICESTATUS, enumVar.getName(), null);
        }



        /**
          * (9) get attribute DeviceStatus
          * @return the value of the attribute
          */
        public EnumDeviceStatus getDeviceStatus()
        {
            return EnumDeviceStatus.getEnum(getAttribute(AttributeName.DEVICESTATUS, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute HourCounter
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HourCounter
          * @param value: the value to set the attribute to
          */
        public void setHourCounter(JDFDuration value)
        {
            setAttribute(AttributeName.HOURCOUNTER, value, null);
        }



        /**
          * (20) get JDFDuration attribute HourCounter
          * @return JDFDuration the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFDuration
          */
        public JDFDuration getHourCounter()
        {
            String strAttrName = "";
            JDFDuration nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.HOURCOUNTER, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute PowerOnTime
        --------------------------------------------------------------------- */
        /**
          * (10) set attribute PowerOnTime
          * @deprecated 2005-12-02 use setPowerOnTime(null)
          */
        public void setPowerOnTime()
        {
            setAttribute(AttributeName.POWERONTIME, new JDFDate().getDateTimeISO(), null);
        }



        /**
          * (11) set attribute PowerOnTime
          * @param value: the value to set the attribute to or null
          */
        public void setPowerOnTime(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.POWERONTIME, value.getDateTimeISO(), null);
        }



        /**
          * (12) get JDFDate attribute PowerOnTime
          * @return JDFDate the value of the attribute
          */
        public JDFDate getPowerOnTime()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.POWERONTIME, null, JDFConstants.EMPTYSTRING);
            if (!JDFConstants.EMPTYSTRING.equals(str))
            {
                try
                {
                    nMyDate = new JDFDate(str);
                }
                catch(DataFormatException dfe)
                {
                    throw new JDFException("not a valid date string. Malformed JDF");
                }
            }
            return nMyDate;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ProductionCounter
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ProductionCounter
          * @param value: the value to set the attribute to
          */
        public void setProductionCounter(double value)
        {
            setAttribute(AttributeName.PRODUCTIONCOUNTER, value, null);
        }



        /**
          * (17) get double attribute ProductionCounter
          * @return double the value of the attribute
          */
        public double getProductionCounter()
        {
            return getRealAttribute(AttributeName.PRODUCTIONCOUNTER, null, 0.0);
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
        Methods for Attribute TotalProductionCounter
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TotalProductionCounter
          * @param value: the value to set the attribute to
          */
        public void setTotalProductionCounter(double value)
        {
            setAttribute(AttributeName.TOTALPRODUCTIONCOUNTER, value, null);
        }



        /**
          * (17) get double attribute TotalProductionCounter
          * @return double the value of the attribute
          */
        public double getTotalProductionCounter()
        {
            return getRealAttribute(AttributeName.TOTALPRODUCTIONCOUNTER, null, 0.0);
        }



/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element Device
     * @return JDFDevice the element
     */
    public JDFDevice getDevice()
    {
        return (JDFDevice) getElement(ElementName.DEVICE, null, 0);
    }



    /** (25) getCreateDevice
     * 
     * @return JDFDevice the element
     */
    public JDFDevice getCreateDevice()
    {
        return (JDFDevice) getCreateElement_KElement(ElementName.DEVICE, null, 0);
    }





    /**
     * (29) append elementDevice
     */
    public JDFDevice appendDevice() throws JDFException
    {
        return (JDFDevice) appendElementN(ElementName.DEVICE, 1, null);
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



    public JDFEmployee appendEmployee()
    {
        return (JDFEmployee) appendElement(ElementName.EMPLOYEE, null);
    }

    /** (26) getCreateJobPhase
     * 
     * @param iSkip number of elements to skip
     * @return JDFJobPhase the element
     */
    public JDFJobPhase getCreateJobPhase(int iSkip)
    {
        return (JDFJobPhase)getCreateElement_KElement(ElementName.JOBPHASE, null, iSkip);
    }



    /**
     * (27) const get element JobPhase
     * @param iSkip number of elements to skip
     * @return JDFJobPhase the element
     * default is getJobPhase(0)     */
    public JDFJobPhase getJobPhase(int iSkip)
    {
        return (JDFJobPhase) getElement(ElementName.JOBPHASE, null, iSkip);
    }



    public JDFJobPhase appendJobPhase()
    {
        return (JDFJobPhase) appendElement(ElementName.JOBPHASE, null);
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



    public JDFModuleStatus appendModuleStatus()
    {
        return (JDFModuleStatus) appendElement(ElementName.MODULESTATUS, null);
    }

}// end namespace JDF
