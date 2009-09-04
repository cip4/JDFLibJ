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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFBusinessInfo;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFMISDetails;
import org.cip4.jdflib.resource.process.JDFNotificationFilter;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;

public abstract class JDFAutoNodeInfo extends JDFResource
{

    private static final long serialVersionUID = 1L;


    /**
     * Constructor for JDFAutoNodeInfo
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoNodeInfo(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoNodeInfo
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoNodeInfo(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoNodeInfo
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoNodeInfo(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    @Override
	public String toString()
    {
        return " JDFAutoNodeInfo[  --> " + super.toString() + " ]";
    }


    @Override
	public boolean  init()
    {
        boolean bRet = super.init();
        setResourceClass(JDFResource.EnumResourceClass.Parameter);
        return bRet;
    }


    @Override
	public EnumResourceClass getValidClass()
    {
        return JDFResource.EnumResourceClass.Parameter;
    }


        /**
        * Enumeration strings for DueLevel
        */

        public static class EnumDueLevel extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumDueLevel(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumDueLevel getEnum(String enumName)
            {
                return (EnumDueLevel) getEnum(EnumDueLevel.class, enumName);
            }

            public static EnumDueLevel getEnum(int enumValue)
            {
                return (EnumDueLevel) getEnum(EnumDueLevel.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumDueLevel.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumDueLevel.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumDueLevel.class);
            }

            public static final EnumDueLevel Unknown = new EnumDueLevel("Unknown");
            public static final EnumDueLevel Trivial = new EnumDueLevel("Trivial");
            public static final EnumDueLevel Penalty = new EnumDueLevel("Penalty");
            public static final EnumDueLevel JobCancelled = new EnumDueLevel("JobCancelled");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute JobPriority
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute JobPriority
          * @param value: the value to set the attribute to
          */
        public void setJobPriority(int value)
        {
            setAttribute(AttributeName.JOBPRIORITY, value, null);
        }

        /**
          * (15) get int attribute JobPriority
          * @return int the value of the attribute
          */
        public int getJobPriority()
        {
            return getIntAttribute(AttributeName.JOBPRIORITY, null, 50);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CleanupDuration
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CleanupDuration
          * @param value: the value to set the attribute to
          */
        public void setCleanupDuration(JDFDuration value)
        {
            setAttribute(AttributeName.CLEANUPDURATION, value, null);
        }

        /**
          * (20) get JDFDuration attribute CleanupDuration
          * @return JDFDuration the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFDuration
          */
        public JDFDuration getCleanupDuration()
        {
            String strAttrName = "";
            JDFDuration nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CLEANUPDURATION, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute DueLevel
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute DueLevel
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setDueLevel(EnumDueLevel enumVar)
        {
            setAttribute(AttributeName.DUELEVEL, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute DueLevel
          * @return the value of the attribute
          */
        public EnumDueLevel getDueLevel()
        {
            return EnumDueLevel.getEnum(getAttribute(AttributeName.DUELEVEL, null, null));
        }

        
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
        Methods for Attribute FirstEnd
        --------------------------------------------------------------------- */
        /**
          * (11) set attribute FirstEnd
          * @param value: the value to set the attribute to or null
          */
        public void setFirstEnd(JDFDate value)
        {
            JDFDate date = value;
            if (date == null) date = new JDFDate();
            setAttribute(AttributeName.FIRSTEND, date.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute FirstEnd
          * @return JDFDate the value of the attribute
          */
        public JDFDate getFirstEnd()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.FIRSTEND, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute FirstStart
        --------------------------------------------------------------------- */
        /**
          * (11) set attribute FirstStart
          * @param value: the value to set the attribute to or null
          */
        public void setFirstStart(JDFDate value)
        {
            JDFDate date = value;
            if (date == null) date = new JDFDate();
            setAttribute(AttributeName.FIRSTSTART, date.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute FirstStart
          * @return JDFDate the value of the attribute
          */
        public JDFDate getFirstStart()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.FIRSTSTART, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute IPPVersion
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IPPVersion
          * @param value: the value to set the attribute to
          */
        public void setIPPVersion(JDFXYPair value)
        {
            setAttribute(AttributeName.IPPVERSION, value, null);
        }

        /**
          * (20) get JDFXYPair attribute IPPVersion
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getIPPVersion()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.IPPVERSION, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFXYPair(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute LastEnd
        --------------------------------------------------------------------- */
        /**
          * (11) set attribute LastEnd
          * @param value: the value to set the attribute to or null
          */
        public void setLastEnd(JDFDate value)
        {
            JDFDate date = value;
            if (date == null) date = new JDFDate();
            setAttribute(AttributeName.LASTEND, date.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute LastEnd
          * @return JDFDate the value of the attribute
          */
        public JDFDate getLastEnd()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.LASTEND, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute LastStart
        --------------------------------------------------------------------- */
        /**
          * (11) set attribute LastStart
          * @param value: the value to set the attribute to or null
          */
        public void setLastStart(JDFDate value)
        {
            JDFDate date = value;
            if (date == null) date = new JDFDate();
            setAttribute(AttributeName.LASTSTART, date.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute LastStart
          * @return JDFDate the value of the attribute
          */
        public JDFDate getLastStart()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.LASTSTART, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute NaturalLang
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NaturalLang
          * @param value: the value to set the attribute to
          */
        public void setNaturalLang(String value)
        {
            setAttribute(AttributeName.NATURALLANG, value, null);
        }

        /**
          * (23) get String attribute NaturalLang
          * @return the value of the attribute
          */
        public String getNaturalLang()
        {
            return getAttribute(AttributeName.NATURALLANG, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NodeStatus
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute NodeStatus
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setNodeStatus(EnumNodeStatus enumVar)
        {
            setAttribute(AttributeName.NODESTATUS, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute NodeStatus
          * @return the value of the attribute
          */
        public EnumNodeStatus getNodeStatus()
        {
            return EnumNodeStatus.getEnum(getAttribute(AttributeName.NODESTATUS, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NodeStatusDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NodeStatusDetails
          * @param value: the value to set the attribute to
          */
        public void setNodeStatusDetails(String value)
        {
            setAttribute(AttributeName.NODESTATUSDETAILS, value, null);
        }

        /**
          * (23) get String attribute NodeStatusDetails
          * @return the value of the attribute
          */
        public String getNodeStatusDetails()
        {
            return getAttribute(AttributeName.NODESTATUSDETAILS, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MergeTarget
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MergeTarget
          * @param value: the value to set the attribute to
          */
        public void setMergeTarget(boolean value)
        {
            setAttribute(AttributeName.MERGETARGET, value, null);
        }

        /**
          * (18) get boolean attribute MergeTarget
          * @return boolean the value of the attribute
          */
        public boolean getMergeTarget()
        {
            return getBoolAttribute(AttributeName.MERGETARGET, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Route
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Route
          * @param value: the value to set the attribute to
          */
        public void setRoute(String value)
        {
            setAttribute(AttributeName.ROUTE, value, null);
        }

        /**
          * (23) get String attribute Route
          * @return the value of the attribute
          */
        public String getRoute()
        {
            return getAttribute(AttributeName.ROUTE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SetupDuration
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SetupDuration
          * @param value: the value to set the attribute to
          */
        public void setSetupDuration(JDFDuration value)
        {
            setAttribute(AttributeName.SETUPDURATION, value, null);
        }

        /**
          * (20) get JDFDuration attribute SetupDuration
          * @return JDFDuration the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFDuration
          */
        public JDFDuration getSetupDuration()
        {
            String strAttrName = "";
            JDFDuration nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SETUPDURATION, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute TargetRoute
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TargetRoute
          * @param value: the value to set the attribute to
          */
        public void setTargetRoute(String value)
        {
            setAttribute(AttributeName.TARGETROUTE, value, null);
        }

        /**
          * (23) get String attribute TargetRoute
          * @return the value of the attribute
          */
        public String getTargetRoute()
        {
            return getAttribute(AttributeName.TARGETROUTE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TotalDuration
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TotalDuration
          * @param value: the value to set the attribute to
          */
        public void setTotalDuration(JDFDuration value)
        {
            setAttribute(AttributeName.TOTALDURATION, value, null);
        }

        /**
          * (20) get JDFDuration attribute TotalDuration
          * @return JDFDuration the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFDuration
          */
        public JDFDuration getTotalDuration()
        {
            String strAttrName = "";
            JDFDuration nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.TOTALDURATION, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute WorkStepID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute WorkStepID
          * @param value: the value to set the attribute to
          */
        public void setWorkStepID(String value)
        {
            setAttribute(AttributeName.WORKSTEPID, value, null);
        }

        /**
          * (23) get String attribute WorkStepID
          * @return the value of the attribute
          */
        public String getWorkStepID()
        {
            return getAttribute(AttributeName.WORKSTEPID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute rRefs
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute rRefs
          * @param value: the value to set the attribute to
          */
        public void setrRefs(VString value)
        {
            setAttribute(AttributeName.RREFS, value, null);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element BusinessInfo
     * @return JDFBusinessInfo the element
     */
    public JDFBusinessInfo getBusinessInfo()
    {
        return (JDFBusinessInfo) getElement(ElementName.BUSINESSINFO, null, 0);
    }

    /** (25) getCreateBusinessInfo
     * 
     * @return JDFBusinessInfo the element
     */
    public JDFBusinessInfo getCreateBusinessInfo()
    {
        return (JDFBusinessInfo) getCreateElement_KElement(ElementName.BUSINESSINFO, null, 0);
    }

    /**
     * (29) append element BusinessInfo
     */
    public JDFBusinessInfo appendBusinessInfo() throws JDFException
    {
        return (JDFBusinessInfo) appendElementN(ElementName.BUSINESSINFO, 1, null);
    }

    /**
     * (24) const get element Employee
     * @return JDFEmployee the element
     */
    public JDFEmployee getEmployee()
    {
        return (JDFEmployee) getElement(ElementName.EMPLOYEE, null, 0);
    }

    /** (25) getCreateEmployee
     * 
     * @return JDFEmployee the element
     */
    public JDFEmployee getCreateEmployee()
    {
        return (JDFEmployee) getCreateElement_KElement(ElementName.EMPLOYEE, null, 0);
    }

    /**
     * (29) append element Employee
     */
    public JDFEmployee appendEmployee() throws JDFException
    {
        return (JDFEmployee) appendElementN(ElementName.EMPLOYEE, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refEmployee(JDFEmployee refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateJMF
     * 
     * @param iSkip number of elements to skip
     * @return JDFJMF the element
     */
    public JDFJMF getCreateJMF(int iSkip)
    {
        return (JDFJMF)getCreateElement_KElement(ElementName.JMF, null, iSkip);
    }

    /**
     * (27) const get element JMF
     * @param iSkip number of elements to skip
     * @return JDFJMF the element
     * default is getJMF(0)     */
    public JDFJMF getJMF(int iSkip)
    {
        return (JDFJMF) getElement(ElementName.JMF, null, iSkip);
    }

    /**
     * Get all JMF from the current element
     * 
     * @return Collection<JDFJMF>, null if none are available
     */
    public Collection<JDFJMF> getAllJMF()
    {
        final VElement vc = getChildElementVector(ElementName.JMF, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFJMF> v = new Vector<JDFJMF>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFJMF) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element JMF
     */
    public JDFJMF appendJMF() throws JDFException
    {
        return (JDFJMF) appendElement(ElementName.JMF, null);
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

    /** (26) getCreateNotificationFilter
     * 
     * @param iSkip number of elements to skip
     * @return JDFNotificationFilter the element
     */
    public JDFNotificationFilter getCreateNotificationFilter(int iSkip)
    {
        return (JDFNotificationFilter)getCreateElement_KElement(ElementName.NOTIFICATIONFILTER, null, iSkip);
    }

    /**
     * (27) const get element NotificationFilter
     * @param iSkip number of elements to skip
     * @return JDFNotificationFilter the element
     * default is getNotificationFilter(0)     */
    public JDFNotificationFilter getNotificationFilter(int iSkip)
    {
        return (JDFNotificationFilter) getElement(ElementName.NOTIFICATIONFILTER, null, iSkip);
    }

    /**
     * Get all NotificationFilter from the current element
     * 
     * @return Collection<JDFNotificationFilter>, null if none are available
     */
    public Collection<JDFNotificationFilter> getAllNotificationFilter()
    {
        final VElement vc = getChildElementVector(ElementName.NOTIFICATIONFILTER, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFNotificationFilter> v = new Vector<JDFNotificationFilter>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFNotificationFilter) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element NotificationFilter
     */
    public JDFNotificationFilter appendNotificationFilter() throws JDFException
    {
        return (JDFNotificationFilter) appendElement(ElementName.NOTIFICATIONFILTER, null);
    }

}// end namespace JDF
