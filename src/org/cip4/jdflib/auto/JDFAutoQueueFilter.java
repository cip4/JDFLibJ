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
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.jmf.JDFQueueEntryDef;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.util.JDFDate;
    /*
    *****************************************************************************
    class JDFAutoQueueFilter : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoQueueFilter extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.GANGNAMES, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.MAXENTRIES, 0x33333311, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.OLDERTHAN, 0x33333311, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.NEWERTHAN, 0x33333311, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.QUEUEENTRYDETAILS, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumQueueEntryDetails.getEnum(0), "Brief");
        atrInfoTable[5] = new AtrInfoTable(AttributeName.STATUSLIST, 0x33333311, AttributeInfo.EnumAttributeType.enumerations, EnumStatusList.getEnum(0), null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.QUEUEENTRYDEF, 0x33333311);
        elemInfoTable[1] = new ElemInfoTable(ElementName.DEVICE, 0x33333311);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoQueueFilter
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoQueueFilter(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoQueueFilter
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoQueueFilter(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoQueueFilter
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoQueueFilter(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoQueueFilter[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for QueueEntryDetails
        */

        public static class EnumQueueEntryDetails extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumQueueEntryDetails(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumQueueEntryDetails getEnum(String enumName)
            {
                return (EnumQueueEntryDetails) getEnum(EnumQueueEntryDetails.class, enumName);
            }

            public static EnumQueueEntryDetails getEnum(int enumValue)
            {
                return (EnumQueueEntryDetails) getEnum(EnumQueueEntryDetails.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumQueueEntryDetails.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumQueueEntryDetails.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumQueueEntryDetails.class);
            }

            public static final EnumQueueEntryDetails None = new EnumQueueEntryDetails("None");
            public static final EnumQueueEntryDetails Brief = new EnumQueueEntryDetails("Brief");
            public static final EnumQueueEntryDetails JobPhase = new EnumQueueEntryDetails("JobPhase");
            public static final EnumQueueEntryDetails JDF = new EnumQueueEntryDetails("JDF");
            public static final EnumQueueEntryDetails Full = new EnumQueueEntryDetails("Full");
        }      



        /**
        * Enumeration strings for StatusList
        */

        public static class EnumStatusList extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumStatusList(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumStatusList getEnum(String enumName)
            {
                return (EnumStatusList) getEnum(EnumStatusList.class, enumName);
            }

            public static EnumStatusList getEnum(int enumValue)
            {
                return (EnumStatusList) getEnum(EnumStatusList.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumStatusList.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumStatusList.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumStatusList.class);
            }

            public static final EnumStatusList Running = new EnumStatusList("Running");
            public static final EnumStatusList Waiting = new EnumStatusList("Waiting");
            public static final EnumStatusList Held = new EnumStatusList("Held");
            public static final EnumStatusList Removed = new EnumStatusList("Removed");
            public static final EnumStatusList Suspended = new EnumStatusList("Suspended");
            public static final EnumStatusList PendingReturn = new EnumStatusList("PendingReturn");
            public static final EnumStatusList Completed = new EnumStatusList("Completed");
            public static final EnumStatusList Aborted = new EnumStatusList("Aborted");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute GangNames
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute GangNames
          * @param value: the value to set the attribute to
          */
        public void setGangNames(VString value)
        {
            setAttribute(AttributeName.GANGNAMES, value, null);
        }

        /**
          * (21) get VString attribute GangNames
          * @return VString the value of the attribute
          */
        public VString getGangNames()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.GANGNAMES, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MaxEntries
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MaxEntries
          * @param value: the value to set the attribute to
          */
        public void setMaxEntries(int value)
        {
            setAttribute(AttributeName.MAXENTRIES, value, null);
        }

        /**
          * (15) get int attribute MaxEntries
          * @return int the value of the attribute
          */
        public int getMaxEntries()
        {
            return getIntAttribute(AttributeName.MAXENTRIES, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute OlderThan
        --------------------------------------------------------------------- */
        /**
          * (11) set attribute OlderThan
          * @param value: the value to set the attribute to or null
          */
        public void setOlderThan(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.OLDERTHAN, value.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute OlderThan
          * @return JDFDate the value of the attribute
          */
        public JDFDate getOlderThan()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.OLDERTHAN, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute NewerThan
        --------------------------------------------------------------------- */
        /**
          * (11) set attribute NewerThan
          * @param value: the value to set the attribute to or null
          */
        public void setNewerThan(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.NEWERTHAN, value.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute NewerThan
          * @return JDFDate the value of the attribute
          */
        public JDFDate getNewerThan()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.NEWERTHAN, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute QueueEntryDetails
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute QueueEntryDetails
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setQueueEntryDetails(EnumQueueEntryDetails enumVar)
        {
            setAttribute(AttributeName.QUEUEENTRYDETAILS, enumVar.getName(), null);
        }

        /**
          * (9) get attribute QueueEntryDetails
          * @return the value of the attribute
          */
        public EnumQueueEntryDetails getQueueEntryDetails()
        {
            return EnumQueueEntryDetails.getEnum(getAttribute(AttributeName.QUEUEENTRYDETAILS, null, "Brief"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StatusList
        --------------------------------------------------------------------- */
        /**
          * (5.2) set attribute StatusList
          * @param v vector of the enumeration values
          */
        public void setStatusList(Vector v)
        {
            setEnumerationsAttribute(AttributeName.STATUSLIST, v, null);
        }

        /**
          * (9.2) get StatusList attribute StatusList
          * @return Vector of the enumerations
          */
        public Vector getStatusList()
        {
            return getEnumerationsAttribute(AttributeName.STATUSLIST, null, EnumStatusList.getEnum(0), false);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateQueueEntryDef
     * 
     * @param iSkip number of elements to skip
     * @return JDFQueueEntryDef the element
     */
    public JDFQueueEntryDef getCreateQueueEntryDef(int iSkip)
    {
        return (JDFQueueEntryDef)getCreateElement_KElement(ElementName.QUEUEENTRYDEF, null, iSkip);
    }

    /**
     * (27) const get element QueueEntryDef
     * @param iSkip number of elements to skip
     * @return JDFQueueEntryDef the element
     * default is getQueueEntryDef(0)     */
    public JDFQueueEntryDef getQueueEntryDef(int iSkip)
    {
        return (JDFQueueEntryDef) getElement(ElementName.QUEUEENTRYDEF, null, iSkip);
    }

    /**
     * (30) append element QueueEntryDef
     */
    public JDFQueueEntryDef appendQueueEntryDef() throws JDFException
    {
        return (JDFQueueEntryDef) appendElement(ElementName.QUEUEENTRYDEF, null);
    }

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
     * (30) append element Device
     */
    public JDFDevice appendDevice() throws JDFException
    {
        return (JDFDevice) appendElement(ElementName.DEVICE, null);
    }

}// end namespace JDF
