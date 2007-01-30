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
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.util.JDFDate;
    /*
    *****************************************************************************
    class JDFAutoQueueEntry : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoQueueEntry extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[10];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.DEVICEID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.ENDTIME, 0x33333311, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.GANGNAME, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.JOBID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.JOBPARTID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.PRIORITY, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, "1");
        atrInfoTable[6] = new AtrInfoTable(AttributeName.QUEUEENTRYID, 0x22222222, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.STATUS, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumQueueEntryStatus.getEnum(0), null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.STARTTIME, 0x33333311, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.SUBMISSIONTIME, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.JOBPHASE, 0x66666611);
        elemInfoTable[1] = new ElemInfoTable(ElementName.PART, 0x33333311);
        elemInfoTable[2] = new ElemInfoTable(ElementName.PREVIEW, 0x33333311);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoQueueEntry
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoQueueEntry(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoQueueEntry
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoQueueEntry(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoQueueEntry
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoQueueEntry(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoQueueEntry[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for QueueEntryStatus
        */

        public static class EnumQueueEntryStatus extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumQueueEntryStatus(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumQueueEntryStatus getEnum(String enumName)
            {
                return (EnumQueueEntryStatus) getEnum(EnumQueueEntryStatus.class, enumName);
            }

            public static EnumQueueEntryStatus getEnum(int enumValue)
            {
                return (EnumQueueEntryStatus) getEnum(EnumQueueEntryStatus.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumQueueEntryStatus.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumQueueEntryStatus.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumQueueEntryStatus.class);
            }

            public static final EnumQueueEntryStatus Running = new EnumQueueEntryStatus("Running");
            public static final EnumQueueEntryStatus Waiting = new EnumQueueEntryStatus("Waiting");
            public static final EnumQueueEntryStatus Held = new EnumQueueEntryStatus("Held");
            public static final EnumQueueEntryStatus Removed = new EnumQueueEntryStatus("Removed");
            public static final EnumQueueEntryStatus Suspended = new EnumQueueEntryStatus("Suspended");
            public static final EnumQueueEntryStatus PendingReturn = new EnumQueueEntryStatus("PendingReturn");
            public static final EnumQueueEntryStatus Completed = new EnumQueueEntryStatus("Completed");
            public static final EnumQueueEntryStatus Aborted = new EnumQueueEntryStatus("Aborted");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
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
        Methods for Attribute EndTime
        --------------------------------------------------------------------- */
        /**
          * (10) set attribute EndTime
          * @deprecated 2005-12-02 use setEndTime(null)
          */
        public void setEndTime()
        {
            setAttribute(AttributeName.ENDTIME, new JDFDate().getDateTimeISO(), null);
        }



        /**
          * (11) set attribute EndTime
          * @param value: the value to set the attribute to or null
          */
        public void setEndTime(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.ENDTIME, value.getDateTimeISO(), null);
        }



        /**
          * (12) get JDFDate attribute EndTime
          * @return JDFDate the value of the attribute
          */
        public JDFDate getEndTime()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.ENDTIME, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute GangName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute GangName
          * @param value: the value to set the attribute to
          */
        public void setGangName(String value)
        {
            setAttribute(AttributeName.GANGNAME, value, null);
        }



        /**
          * (23) get String attribute GangName
          * @return the value of the attribute
          */
        public String getGangName()
        {
            return getAttribute(AttributeName.GANGNAME, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute Priority
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Priority
          * @param value: the value to set the attribute to
          */
        public void setPriority(int value)
        {
            setAttribute(AttributeName.PRIORITY, value, null);
        }



        /**
          * (15) get int attribute Priority
          * @return int the value of the attribute
          */
        public int getPriority()
        {
            return getIntAttribute(AttributeName.PRIORITY, null, 1);
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
        Methods for Attribute Status
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Status
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setQueueEntryStatus(EnumQueueEntryStatus enumVar)
        {
            setAttribute(AttributeName.STATUS, enumVar.getName(), null);
        }



        /**
          * (9) get attribute Status
          * @return the value of the attribute
          */
        public EnumQueueEntryStatus getQueueEntryStatus()
        {
            return EnumQueueEntryStatus.getEnum(getAttribute(AttributeName.STATUS, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute StartTime
        --------------------------------------------------------------------- */
        /**
          * (10) set attribute StartTime
          * @deprecated 2005-12-02 use setStartTime(null)
          */
        public void setStartTime()
        {
            setAttribute(AttributeName.STARTTIME, new JDFDate().getDateTimeISO(), null);
        }



        /**
          * (11) set attribute StartTime
          * @param value: the value to set the attribute to or null
          */
        public void setStartTime(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.STARTTIME, value.getDateTimeISO(), null);
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
                    throw new JDFException("not a valid date string. Malformed JDF");
                }
            }
            return nMyDate;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute SubmissionTime
        --------------------------------------------------------------------- */
        /**
          * (10) set attribute SubmissionTime
          * @deprecated 2005-12-02 use setSubmissionTime(null)
          */
        public void setSubmissionTime()
        {
            setAttribute(AttributeName.SUBMISSIONTIME, new JDFDate().getDateTimeISO(), null);
        }



        /**
          * (11) set attribute SubmissionTime
          * @param value: the value to set the attribute to or null
          */
        public void setSubmissionTime(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.SUBMISSIONTIME, value.getDateTimeISO(), null);
        }



        /**
          * (12) get JDFDate attribute SubmissionTime
          * @return JDFDate the value of the attribute
          */
        public JDFDate getSubmissionTime()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.SUBMISSIONTIME, null, JDFConstants.EMPTYSTRING);
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



/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element JobPhase
     * @return JDFJobPhase the element
     */
    public JDFJobPhase getJobPhase()
    {
        return (JDFJobPhase) getElement(ElementName.JOBPHASE, null, 0);
    }



    /** (25) getCreateJobPhase
     * 
     * @return JDFJobPhase the element
     */
    public JDFJobPhase getCreateJobPhase()
    {
        return (JDFJobPhase) getCreateElement_KElement(ElementName.JOBPHASE, null, 0);
    }





    /**
     * (29) append elementJobPhase
     */
    public JDFJobPhase appendJobPhase() throws JDFException
    {
        return (JDFJobPhase) appendElementN(ElementName.JOBPHASE, 1, null);
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



    public JDFPart appendPart()
    {
        return (JDFPart) appendElement(ElementName.PART, null);
    }

    /** (26) getCreatePreview
     * 
     * @param iSkip number of elements to skip
     * @return JDFPreview the element
     */
    public JDFPreview getCreatePreview(int iSkip)
    {
        return (JDFPreview)getCreateElement_KElement(ElementName.PREVIEW, null, iSkip);
    }



    /**
     * (27) const get element Preview
     * @param iSkip number of elements to skip
     * @return JDFPreview the element
     * default is getPreview(0)     */
    public JDFPreview getPreview(int iSkip)
    {
        return (JDFPreview) getElement(ElementName.PREVIEW, null, iSkip);
    }



    public JDFPreview appendPreview()
    {
        return (JDFPreview) appendElement(ElementName.PREVIEW, null);
    }

}// end namespace JDF
