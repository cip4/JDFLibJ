/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of
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

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.process.JDFDisposition;
import org.cip4.jdflib.resource.process.JDFMISDetails;
    /**
    *****************************************************************************
    class JDFAutoResourcePullParams : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoResourcePullParams extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.AMOUNT, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.HOLD, 0x33333311, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.NEXTQUEUEENTRYID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.PREVQUEUEENTRYID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.JOBID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.PRIORITY, 0x33333311, AttributeInfo.EnumAttributeType.integer, null, "1");
        atrInfoTable[6] = new AtrInfoTable(AttributeName.QUEUEENTRYID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.REPEATPOLICY, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumRepeatPolicy.getEnum(0), null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.RESOURCEID, 0x22222211, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.RETURNURL, 0x33333311, AttributeInfo.EnumAttributeType.URL, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.WATCHURL, 0x33333311, AttributeInfo.EnumAttributeType.URL, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.PART, 0x33333311);
        elemInfoTable[1] = new ElemInfoTable(ElementName.DISPOSITION, 0x66666611);
        elemInfoTable[2] = new ElemInfoTable(ElementName.MISDETAILS, 0x66666611);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoResourcePullParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoResourcePullParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoResourcePullParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoResourcePullParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoResourcePullParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoResourcePullParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    /**
     * @return  the string representation
     */
    @Override
    public String toString()
    {
        return " JDFAutoResourcePullParams[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for RepeatPolicy
        */

        public static class EnumRepeatPolicy extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumRepeatPolicy(String name)
            {
                super(name, m_startValue++);
            }

    /**
     * @param enumName the string to convert
     * @return the enum
     */
            public static EnumRepeatPolicy getEnum(String enumName)
            {
                return (EnumRepeatPolicy) getEnum(EnumRepeatPolicy.class, enumName);
            }

    /**
     * @param enumValue the integer to convert
     * @return the enum
     */
            public static EnumRepeatPolicy getEnum(int enumValue)
            {
                return (EnumRepeatPolicy) getEnum(EnumRepeatPolicy.class, enumValue);
            }

    /**
     * @return the map of enums
     */
            public static Map getEnumMap()
            {
                return getEnumMap(EnumRepeatPolicy.class);
            }

    /**
     * @return the list of enums
     */
            public static List getEnumList()
            {
                return getEnumList(EnumRepeatPolicy.class);
            }

    /**
     * @return the iterator
     */
            public static Iterator iterator()
            {
                return iterator(EnumRepeatPolicy.class);
            }

            public static final EnumRepeatPolicy Complete = new EnumRepeatPolicy("Complete");
            public static final EnumRepeatPolicy CompleteOnly = new EnumRepeatPolicy("CompleteOnly");
            public static final EnumRepeatPolicy Fast = new EnumRepeatPolicy("Fast");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Amount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Amount
          * @param value the value to set the attribute to
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
        Methods for Attribute Hold
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Hold
          * @param value the value to set the attribute to
          */
        public void setHold(boolean value)
        {
            setAttribute(AttributeName.HOLD, value, null);
        }

        /**
          * (18) get boolean attribute Hold
          * @return boolean the value of the attribute
          */
        public boolean getHold()
        {
            return getBoolAttribute(AttributeName.HOLD, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NextQueueEntryID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NextQueueEntryID
          * @param value the value to set the attribute to
          */
        public void setNextQueueEntryID(String value)
        {
            setAttribute(AttributeName.NEXTQUEUEENTRYID, value, null);
        }

        /**
          * (23) get String attribute NextQueueEntryID
          * @return the value of the attribute
          */
        public String getNextQueueEntryID()
        {
            return getAttribute(AttributeName.NEXTQUEUEENTRYID, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PrevQueueEntryID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PrevQueueEntryID
          * @param value the value to set the attribute to
          */
        public void setPrevQueueEntryID(String value)
        {
            setAttribute(AttributeName.PREVQUEUEENTRYID, value, null);
        }

        /**
          * (23) get String attribute PrevQueueEntryID
          * @return the value of the attribute
          */
        public String getPrevQueueEntryID()
        {
            return getAttribute(AttributeName.PREVQUEUEENTRYID, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute JobID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute JobID
          * @param value the value to set the attribute to
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
            return getAttribute(AttributeName.JOBID, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Priority
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Priority
          * @param value the value to set the attribute to
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
          * @param value the value to set the attribute to
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
            return getAttribute(AttributeName.QUEUEENTRYID, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RepeatPolicy
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute RepeatPolicy
          * @param enumVar the enumVar to set the attribute to
          */
        public void setRepeatPolicy(EnumRepeatPolicy enumVar)
        {
            setAttribute(AttributeName.REPEATPOLICY, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute RepeatPolicy
          * @return the value of the attribute
          */
        public EnumRepeatPolicy getRepeatPolicy()
        {
            return EnumRepeatPolicy.getEnum(getAttribute(AttributeName.REPEATPOLICY, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ResourceID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ResourceID
          * @param value the value to set the attribute to
          */
        public void setResourceID(String value)
        {
            setAttribute(AttributeName.RESOURCEID, value, null);
        }

        /**
          * (23) get String attribute ResourceID
          * @return the value of the attribute
          */
        public String getResourceID()
        {
            return getAttribute(AttributeName.RESOURCEID, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ReturnURL
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ReturnURL
          * @param value the value to set the attribute to
          */
        public void setReturnURL(String value)
        {
            setAttribute(AttributeName.RETURNURL, value, null);
        }

        /**
          * (23) get String attribute ReturnURL
          * @return the value of the attribute
          */
        public String getReturnURL()
        {
            return getAttribute(AttributeName.RETURNURL, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute WatchURL
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute WatchURL
          * @param value the value to set the attribute to
          */
        public void setWatchURL(String value)
        {
            setAttribute(AttributeName.WATCHURL, value, null);
        }

        /**
          * (23) get String attribute WatchURL
          * @return the value of the attribute
          */
        public String getWatchURL()
        {
            return getAttribute(AttributeName.WATCHURL, null, JDFCoreConstants.EMPTYSTRING);
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
     * @return JDFPart the element
     */
    public JDFPart appendPart()
    {
        return (JDFPart) appendElement(ElementName.PART, null);
    }

    /**
     * (24) const get element Disposition
     * @return JDFDisposition the element
     */
    public JDFDisposition getDisposition()
    {
        return (JDFDisposition) getElement(ElementName.DISPOSITION, null, 0);
    }

    /** (25) getCreateDisposition
     * 
     * @return JDFDisposition the element
     */
    public JDFDisposition getCreateDisposition()
    {
        return (JDFDisposition) getCreateElement_KElement(ElementName.DISPOSITION, null, 0);
    }

    /**
     * (29) append element Disposition
     * @return JDFDisposition the element
     * @throws JDFException if the element already exists
     */
    public JDFDisposition appendDisposition() throws JDFException
    {
        return (JDFDisposition) appendElementN(ElementName.DISPOSITION, 1, null);
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
     * @return JDFMISDetails the element
     * @throws JDFException if the element already exists
     */
    public JDFMISDetails appendMISDetails() throws JDFException
    {
        return (JDFMISDetails) appendElementN(ElementName.MISDETAILS, 1, null);
    }

}// end namespace JDF
