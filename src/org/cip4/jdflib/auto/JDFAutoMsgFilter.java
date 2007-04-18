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
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.util.JDFDate;
    /*
    *****************************************************************************
    class JDFAutoMsgFilter : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoMsgFilter extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[12];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.AFTER, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.BEFORE, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.COUNT, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.DEVICEID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.FAMILY, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumFamily.getEnum(0), null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.JOBID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.JOBPARTID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.MESSAGEREFID, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.MESSAGEID, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.MESSAGETYPE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.QUEUEENTRYID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.RECEIVERURL, 0x33333333, AttributeInfo.EnumAttributeType.URL, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.PART, 0x33333311);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoMsgFilter
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoMsgFilter(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMsgFilter
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoMsgFilter(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMsgFilter
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoMsgFilter(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoMsgFilter[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for Family
        */

        public static class EnumFamily extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumFamily(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumFamily getEnum(String enumName)
            {
                return (EnumFamily) getEnum(EnumFamily.class, enumName);
            }

            public static EnumFamily getEnum(int enumValue)
            {
                return (EnumFamily) getEnum(EnumFamily.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumFamily.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumFamily.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumFamily.class);
            }

            public static final EnumFamily Acknowledge = new EnumFamily("Acknowledge");
            public static final EnumFamily Response = new EnumFamily("Response");
            public static final EnumFamily Signal = new EnumFamily("Signal");
            public static final EnumFamily All = new EnumFamily("All");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute After
        --------------------------------------------------------------------- */
        /**
          * (10) set attribute After
          * @deprecated 2005-12-02 use setAfter(null)
          */
        public void setAfter()
        {
            setAttribute(AttributeName.AFTER, new JDFDate().getDateTimeISO(), null);
        }

        /**
          * (11) set attribute After
          * @param value: the value to set the attribute to or null
          */
        public void setAfter(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.AFTER, value.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute After
          * @return JDFDate the value of the attribute
          */
        public JDFDate getAfter()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.AFTER, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute Before
        --------------------------------------------------------------------- */
        /**
          * (10) set attribute Before
          * @deprecated 2005-12-02 use setBefore(null)
          */
        public void setBefore()
        {
            setAttribute(AttributeName.BEFORE, new JDFDate().getDateTimeISO(), null);
        }

        /**
          * (11) set attribute Before
          * @param value: the value to set the attribute to or null
          */
        public void setBefore(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.BEFORE, value.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute Before
          * @return JDFDate the value of the attribute
          */
        public JDFDate getBefore()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.BEFORE, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute Count
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Count
          * @param value: the value to set the attribute to
          */
        public void setCount(int value)
        {
            setAttribute(AttributeName.COUNT, value, null);
        }

        /**
          * (15) get int attribute Count
          * @return int the value of the attribute
          */
        public int getCount()
        {
            return getIntAttribute(AttributeName.COUNT, null, 0);
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
        Methods for Attribute Family
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Family
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setFamily(EnumFamily enumVar)
        {
            setAttribute(AttributeName.FAMILY, enumVar.getName(), null);
        }

        /**
          * (9) get attribute Family
          * @return the value of the attribute
          */
        public EnumFamily getFamily()
        {
            return EnumFamily.getEnum(getAttribute(AttributeName.FAMILY, null, null));
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
        Methods for Attribute MessageRefID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MessageRefID
          * @param value: the value to set the attribute to
          */
        public void setMessageRefID(String value)
        {
            setAttribute(AttributeName.MESSAGEREFID, value, null);
        }

        /**
          * (23) get String attribute MessageRefID
          * @return the value of the attribute
          */
        public String getMessageRefID()
        {
            return getAttribute(AttributeName.MESSAGEREFID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MessageID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MessageID
          * @param value: the value to set the attribute to
          */
        public void setMessageID(String value)
        {
            setAttribute(AttributeName.MESSAGEID, value, null);
        }

        /**
          * (23) get String attribute MessageID
          * @return the value of the attribute
          */
        public String getMessageID()
        {
            return getAttribute(AttributeName.MESSAGEID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MessageType
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MessageType
          * @param value: the value to set the attribute to
          */
        public void setMessageType(String value)
        {
            setAttribute(AttributeName.MESSAGETYPE, value, null);
        }

        /**
          * (23) get String attribute MessageType
          * @return the value of the attribute
          */
        public String getMessageType()
        {
            return getAttribute(AttributeName.MESSAGETYPE, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute ReceiverURL
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ReceiverURL
          * @param value: the value to set the attribute to
          */
        public void setReceiverURL(String value)
        {
            setAttribute(AttributeName.RECEIVERURL, value, null);
        }

        /**
          * (23) get String attribute ReceiverURL
          * @return the value of the attribute
          */
        public String getReceiverURL()
        {
            return getAttribute(AttributeName.RECEIVERURL, null, JDFConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

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
     * (30) append element Part
     */
    public JDFPart appendPart() throws JDFException
    {
        return (JDFPart) appendElement(ElementName.PART, null);
    }

}// end namespace JDF
