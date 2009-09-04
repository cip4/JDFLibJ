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
import org.cip4.jdflib.resource.JDFObservationTarget;
import org.cip4.jdflib.util.JDFDuration;

public abstract class JDFAutoSubscription extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CHANNELMODE, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumChannelMode.getEnum(0), "FireAndForget");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.FORMAT, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.MINDELAYTIME, 0x33333111, AttributeInfo.EnumAttributeType.duration, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.REPEATSTEP, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.REPEATTIME, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.RETRYPOLICY, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumRetryPolicy.getEnum(0), null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.TEMPLATE, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.URL, 0x33333333, AttributeInfo.EnumAttributeType.URL, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.OBSERVATIONTARGET, 0x33333333);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoSubscription
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoSubscription(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoSubscription
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoSubscription(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoSubscription
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoSubscription(
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
        return " JDFAutoSubscription[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for ChannelMode
        */

        public static class EnumChannelMode extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumChannelMode(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumChannelMode getEnum(String enumName)
            {
                return (EnumChannelMode) getEnum(EnumChannelMode.class, enumName);
            }

            public static EnumChannelMode getEnum(int enumValue)
            {
                return (EnumChannelMode) getEnum(EnumChannelMode.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumChannelMode.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumChannelMode.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumChannelMode.class);
            }

            public static final EnumChannelMode FireAndForget = new EnumChannelMode("FireAndForget");
            public static final EnumChannelMode Reliable = new EnumChannelMode("Reliable");
        }      



        /**
        * Enumeration strings for RetryPolicy
        */

        public static class EnumRetryPolicy extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumRetryPolicy(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumRetryPolicy getEnum(String enumName)
            {
                return (EnumRetryPolicy) getEnum(EnumRetryPolicy.class, enumName);
            }

            public static EnumRetryPolicy getEnum(int enumValue)
            {
                return (EnumRetryPolicy) getEnum(EnumRetryPolicy.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumRetryPolicy.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumRetryPolicy.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumRetryPolicy.class);
            }

            public static final EnumRetryPolicy DiscardAtNextSignal = new EnumRetryPolicy("DiscardAtNextSignal");
            public static final EnumRetryPolicy RetryForever = new EnumRetryPolicy("RetryForever");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute ChannelMode
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute ChannelMode
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setChannelMode(EnumChannelMode enumVar)
        {
            setAttribute(AttributeName.CHANNELMODE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute ChannelMode
          * @return the value of the attribute
          */
        public EnumChannelMode getChannelMode()
        {
            return EnumChannelMode.getEnum(getAttribute(AttributeName.CHANNELMODE, null, "FireAndForget"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Format
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Format
          * @param value: the value to set the attribute to
          */
        public void setFormat(String value)
        {
            setAttribute(AttributeName.FORMAT, value, null);
        }

        /**
          * (23) get String attribute Format
          * @return the value of the attribute
          */
        public String getFormat()
        {
            return getAttribute(AttributeName.FORMAT, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MinDelayTime
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MinDelayTime
          * @param value: the value to set the attribute to
          */
        public void setMinDelayTime(JDFDuration value)
        {
            setAttribute(AttributeName.MINDELAYTIME, value, null);
        }

        /**
          * (20) get JDFDuration attribute MinDelayTime
          * @return JDFDuration the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFDuration
          */
        public JDFDuration getMinDelayTime()
        {
            String strAttrName = "";
            JDFDuration nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.MINDELAYTIME, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute RepeatStep
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RepeatStep
          * @param value: the value to set the attribute to
          */
        public void setRepeatStep(int value)
        {
            setAttribute(AttributeName.REPEATSTEP, value, null);
        }

        /**
          * (15) get int attribute RepeatStep
          * @return int the value of the attribute
          */
        public int getRepeatStep()
        {
            return getIntAttribute(AttributeName.REPEATSTEP, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RepeatTime
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RepeatTime
          * @param value: the value to set the attribute to
          */
        public void setRepeatTime(double value)
        {
            setAttribute(AttributeName.REPEATTIME, value, null);
        }

        /**
          * (17) get double attribute RepeatTime
          * @return double the value of the attribute
          */
        public double getRepeatTime()
        {
            return getRealAttribute(AttributeName.REPEATTIME, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RetryPolicy
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute RetryPolicy
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setRetryPolicy(EnumRetryPolicy enumVar)
        {
            setAttribute(AttributeName.RETRYPOLICY, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute RetryPolicy
          * @return the value of the attribute
          */
        public EnumRetryPolicy getRetryPolicy()
        {
            return EnumRetryPolicy.getEnum(getAttribute(AttributeName.RETRYPOLICY, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Template
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Template
          * @param value: the value to set the attribute to
          */
        public void setTemplate(String value)
        {
            setAttribute(AttributeName.TEMPLATE, value, null);
        }

        /**
          * (23) get String attribute Template
          * @return the value of the attribute
          */
        public String getTemplate()
        {
            return getAttribute(AttributeName.TEMPLATE, null, JDFConstants.EMPTYSTRING);
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

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateObservationTarget
     * 
     * @param iSkip number of elements to skip
     * @return JDFObservationTarget the element
     */
    public JDFObservationTarget getCreateObservationTarget(int iSkip)
    {
        return (JDFObservationTarget)getCreateElement_KElement(ElementName.OBSERVATIONTARGET, null, iSkip);
    }

    /**
     * (27) const get element ObservationTarget
     * @param iSkip number of elements to skip
     * @return JDFObservationTarget the element
     * default is getObservationTarget(0)     */
    public JDFObservationTarget getObservationTarget(int iSkip)
    {
        return (JDFObservationTarget) getElement(ElementName.OBSERVATIONTARGET, null, iSkip);
    }

    /**
     * Get all ObservationTarget from the current element
     * 
     * @return Collection<JDFObservationTarget>, null if none are available
     */
    public Collection<JDFObservationTarget> getAllObservationTarget()
    {
        final VElement vc = getChildElementVector(ElementName.OBSERVATIONTARGET, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFObservationTarget> v = new Vector<JDFObservationTarget>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFObservationTarget) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element ObservationTarget
     */
    public JDFObservationTarget appendObservationTarget() throws JDFException
    {
        return (JDFObservationTarget) appendElement(ElementName.OBSERVATIONTARGET, null);
    }

}// end namespace JDF
