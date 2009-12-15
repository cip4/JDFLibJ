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
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.devicecapability.JDFActionPool;
import org.cip4.jdflib.resource.devicecapability.JDFDevCapPool;
import org.cip4.jdflib.resource.devicecapability.JDFDevCaps;
import org.cip4.jdflib.resource.devicecapability.JDFModulePool;
import org.cip4.jdflib.resource.devicecapability.JDFTestPool;

public abstract class JDFAutoMessageService extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ACKNOWLEDGE, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.CHANNELMODE, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumChannelMode.getEnum(0), null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.COMMAND, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.GENERICATTRIBUTES, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.JMFROLE, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumJMFRole.getEnum(0), null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.PERSISTENT, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[6] = new AtrInfoTable(AttributeName.QUERY, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[7] = new AtrInfoTable(AttributeName.REGISTRATION, 0x33333111, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[8] = new AtrInfoTable(AttributeName.SIGNAL, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[9] = new AtrInfoTable(AttributeName.TYPE, 0x22222222, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.URLSCHEMES, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[5];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.ACTIONPOOL, 0x66666111);
        elemInfoTable[1] = new ElemInfoTable(ElementName.DEVCAPPOOL, 0x66666111);
        elemInfoTable[2] = new ElemInfoTable(ElementName.DEVCAPS, 0x33333331);
        elemInfoTable[3] = new ElemInfoTable(ElementName.MODULEPOOL, 0x66666111);
        elemInfoTable[4] = new ElemInfoTable(ElementName.TESTPOOL, 0x66666111);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoMessageService
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoMessageService(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMessageService
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoMessageService(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMessageService
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoMessageService(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoMessageService[  --> " + super.toString() + " ]";
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
        * Enumeration strings for JMFRole
        */

        public static class EnumJMFRole extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumJMFRole(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumJMFRole getEnum(String enumName)
            {
                return (EnumJMFRole) getEnum(EnumJMFRole.class, enumName);
            }

            public static EnumJMFRole getEnum(int enumValue)
            {
                return (EnumJMFRole) getEnum(EnumJMFRole.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumJMFRole.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumJMFRole.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumJMFRole.class);
            }

            public static final EnumJMFRole Receiver = new EnumJMFRole("Receiver");
            public static final EnumJMFRole Sender = new EnumJMFRole("Sender");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Acknowledge
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Acknowledge
          * @param value: the value to set the attribute to
          */
        public void setAcknowledge(boolean value)
        {
            setAttribute(AttributeName.ACKNOWLEDGE, value, null);
        }

        /**
          * (18) get boolean attribute Acknowledge
          * @return boolean the value of the attribute
          */
        public boolean getAcknowledge()
        {
            return getBoolAttribute(AttributeName.ACKNOWLEDGE, null, false);
        }

        
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
            return EnumChannelMode.getEnum(getAttribute(AttributeName.CHANNELMODE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Command
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Command
          * @param value: the value to set the attribute to
          */
        public void setCommand(boolean value)
        {
            setAttribute(AttributeName.COMMAND, value, null);
        }

        /**
          * (18) get boolean attribute Command
          * @return boolean the value of the attribute
          */
        public boolean getCommand()
        {
            return getBoolAttribute(AttributeName.COMMAND, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute GenericAttributes
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute GenericAttributes
          * @param value: the value to set the attribute to
          */
        public void setGenericAttributes(VString value)
        {
            setAttribute(AttributeName.GENERICATTRIBUTES, value, null);
        }

        /**
          * (21) get VString attribute GenericAttributes
          * @return VString the value of the attribute
          */
        public VString getGenericAttributes()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.GENERICATTRIBUTES, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute JMFRole
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute JMFRole
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setJMFRole(EnumJMFRole enumVar)
        {
            setAttribute(AttributeName.JMFROLE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute JMFRole
          * @return the value of the attribute
          */
        public EnumJMFRole getJMFRole()
        {
            return EnumJMFRole.getEnum(getAttribute(AttributeName.JMFROLE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Persistent
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Persistent
          * @param value: the value to set the attribute to
          */
        public void setPersistent(boolean value)
        {
            setAttribute(AttributeName.PERSISTENT, value, null);
        }

        /**
          * (18) get boolean attribute Persistent
          * @return boolean the value of the attribute
          */
        public boolean getPersistent()
        {
            return getBoolAttribute(AttributeName.PERSISTENT, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Query
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Query
          * @param value: the value to set the attribute to
          */
        public void setQuery(boolean value)
        {
            setAttribute(AttributeName.QUERY, value, null);
        }

        /**
          * (18) get boolean attribute Query
          * @return boolean the value of the attribute
          */
        public boolean getQuery()
        {
            return getBoolAttribute(AttributeName.QUERY, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Registration
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Registration
          * @param value: the value to set the attribute to
          */
        public void setRegistration(boolean value)
        {
            setAttribute(AttributeName.REGISTRATION, value, null);
        }

        /**
          * (18) get boolean attribute Registration
          * @return boolean the value of the attribute
          */
        public boolean getRegistration()
        {
            return getBoolAttribute(AttributeName.REGISTRATION, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Signal
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Signal
          * @param value: the value to set the attribute to
          */
        public void setSignal(boolean value)
        {
            setAttribute(AttributeName.SIGNAL, value, null);
        }

        /**
          * (18) get boolean attribute Signal
          * @return boolean the value of the attribute
          */
        public boolean getSignal()
        {
            return getBoolAttribute(AttributeName.SIGNAL, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Type
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Type
          * @param value: the value to set the attribute to
          */
        public void setType(String value)
        {
            setAttribute(AttributeName.TYPE, value, null);
        }

        /**
          * (23) get String attribute Type
          * @return the value of the attribute
          */
        public String getType()
        {
            return getAttribute(AttributeName.TYPE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute URLSchemes
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute URLSchemes
          * @param value: the value to set the attribute to
          */
        public void setURLSchemes(VString value)
        {
            setAttribute(AttributeName.URLSCHEMES, value, null);
        }

        /**
          * (21) get VString attribute URLSchemes
          * @return VString the value of the attribute
          */
        public VString getURLSchemes()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.URLSCHEMES, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element ActionPool
     * @return JDFActionPool the element
     */
    public JDFActionPool getActionPool()
    {
        return (JDFActionPool) getElement(ElementName.ACTIONPOOL, null, 0);
    }

    /** (25) getCreateActionPool
     * 
     * @return JDFActionPool the element
     */
    public JDFActionPool getCreateActionPool()
    {
        return (JDFActionPool) getCreateElement_KElement(ElementName.ACTIONPOOL, null, 0);
    }

    /**
     * (29) append element ActionPool
     */
    public JDFActionPool appendActionPool() throws JDFException
    {
        return (JDFActionPool) appendElementN(ElementName.ACTIONPOOL, 1, null);
    }

    /**
     * (24) const get element DevCapPool
     * @return JDFDevCapPool the element
     */
    public JDFDevCapPool getDevCapPool()
    {
        return (JDFDevCapPool) getElement(ElementName.DEVCAPPOOL, null, 0);
    }

    /** (25) getCreateDevCapPool
     * 
     * @return JDFDevCapPool the element
     */
    public JDFDevCapPool getCreateDevCapPool()
    {
        return (JDFDevCapPool) getCreateElement_KElement(ElementName.DEVCAPPOOL, null, 0);
    }

    /**
     * (29) append element DevCapPool
     */
    public JDFDevCapPool appendDevCapPool() throws JDFException
    {
        return (JDFDevCapPool) appendElementN(ElementName.DEVCAPPOOL, 1, null);
    }

    /** (26) getCreateDevCaps
     * 
     * @param iSkip number of elements to skip
     * @return JDFDevCaps the element
     */
    public JDFDevCaps getCreateDevCaps(int iSkip)
    {
        return (JDFDevCaps)getCreateElement_KElement(ElementName.DEVCAPS, null, iSkip);
    }

    /**
     * (27) const get element DevCaps
     * @param iSkip number of elements to skip
     * @return JDFDevCaps the element
     * default is getDevCaps(0)     */
    public JDFDevCaps getDevCaps(int iSkip)
    {
        return (JDFDevCaps) getElement(ElementName.DEVCAPS, null, iSkip);
    }

    /**
     * Get all DevCaps from the current element
     * 
     * @return Collection<JDFDevCaps>, null if none are available
     */
    public Collection<JDFDevCaps> getAllDevCaps()
    {
        final VElement vc = getChildElementVector(ElementName.DEVCAPS, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFDevCaps> v = new Vector<JDFDevCaps>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFDevCaps) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element DevCaps
     */
    public JDFDevCaps appendDevCaps() throws JDFException
    {
        return (JDFDevCaps) appendElement(ElementName.DEVCAPS, null);
    }

    /**
     * (24) const get element ModulePool
     * @return JDFModulePool the element
     */
    public JDFModulePool getModulePool()
    {
        return (JDFModulePool) getElement(ElementName.MODULEPOOL, null, 0);
    }

    /** (25) getCreateModulePool
     * 
     * @return JDFModulePool the element
     */
    public JDFModulePool getCreateModulePool()
    {
        return (JDFModulePool) getCreateElement_KElement(ElementName.MODULEPOOL, null, 0);
    }

    /**
     * (29) append element ModulePool
     */
    public JDFModulePool appendModulePool() throws JDFException
    {
        return (JDFModulePool) appendElementN(ElementName.MODULEPOOL, 1, null);
    }

    /**
     * (24) const get element TestPool
     * @return JDFTestPool the element
     */
    public JDFTestPool getTestPool()
    {
        return (JDFTestPool) getElement(ElementName.TESTPOOL, null, 0);
    }

    /** (25) getCreateTestPool
     * 
     * @return JDFTestPool the element
     */
    public JDFTestPool getCreateTestPool()
    {
        return (JDFTestPool) getCreateElement_KElement(ElementName.TESTPOOL, null, 0);
    }

    /**
     * (29) append element TestPool
     */
    public JDFTestPool appendTestPool() throws JDFException
    {
        return (JDFTestPool) appendElementN(ElementName.TESTPOOL, 1, null);
    }

}// end namespace JDF
