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
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
    /*
    *****************************************************************************
    class JDFAutoProcessRun : public JDFAudit

    *****************************************************************************
    */

public abstract class JDFAutoProcessRun extends JDFAudit
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.DURATION, 0x33333333, AttributeInfo.EnumAttributeType.duration, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.END, 0x22222222, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.ENDSTATUS, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumEndStatus.getEnum(0), null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.START, 0x22222222, AttributeInfo.EnumAttributeType.dateTime, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.PART, 0x33333331);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoProcessRun
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoProcessRun(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoProcessRun
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoProcessRun(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoProcessRun
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoProcessRun(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoProcessRun[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for EndStatus
        */

        public static class EnumEndStatus extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumEndStatus(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumEndStatus getEnum(String enumName)
            {
                return (EnumEndStatus) getEnum(EnumEndStatus.class, enumName);
            }

            public static EnumEndStatus getEnum(int enumValue)
            {
                return (EnumEndStatus) getEnum(EnumEndStatus.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumEndStatus.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumEndStatus.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumEndStatus.class);
            }

            public static final EnumEndStatus Aborted = new EnumEndStatus("Aborted");
            public static final EnumEndStatus Completed = new EnumEndStatus("Completed");
            public static final EnumEndStatus FailedTestRun = new EnumEndStatus("FailedTestRun");
            public static final EnumEndStatus Ready = new EnumEndStatus("Ready");
            public static final EnumEndStatus Stopped = new EnumEndStatus("Stopped");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Duration
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Duration
          * @param value: the value to set the attribute to
          */
        public void setDuration(JDFDuration value)
        {
            setAttribute(AttributeName.DURATION, value, null);
        }

        /**
          * (20) get JDFDuration attribute Duration
          * @return JDFDuration the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFDuration
          */
        public JDFDuration getDuration()
        {
            String strAttrName = "";
            JDFDuration nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.DURATION, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute End
        --------------------------------------------------------------------- */
        /**
          * (10) set attribute End
          * @deprecated 2005-12-02 use setEnd(null)
          */
        public void setEnd()
        {
            setAttribute(AttributeName.END, new JDFDate().getDateTimeISO(), null);
        }

        /**
          * (11) set attribute End
          * @param value: the value to set the attribute to or null
          */
        public void setEnd(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.END, value.getDateTimeISO(), null);
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
                    throw new JDFException("not a valid date string. Malformed JDF");
                }
            }
            return nMyDate;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute EndStatus
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute EndStatus
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setEndStatus(EnumNodeStatus enumVar)
        {
            setAttribute(AttributeName.ENDSTATUS, enumVar.getName(), null);
        }

        /**
          * (9) get attribute EndStatus
          * @return the value of the attribute
          */
        public EnumNodeStatus getEndStatus()
        {
            return EnumNodeStatus.getEnum(getAttribute(AttributeName.ENDSTATUS, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Start
        --------------------------------------------------------------------- */
        /**
          * (10) set attribute Start
          * @deprecated 2005-12-02 use setStart(null)
          */
        public void setStart()
        {
            setAttribute(AttributeName.START, new JDFDate().getDateTimeISO(), null);
        }

        /**
          * (11) set attribute Start
          * @param value: the value to set the attribute to or null
          */
        public void setStart(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.START, value.getDateTimeISO(), null);
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
                    throw new JDFException("not a valid date string. Malformed JDF");
                }
            }
            return nMyDate;
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
