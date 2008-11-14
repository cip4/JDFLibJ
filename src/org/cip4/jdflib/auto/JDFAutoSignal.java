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
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.jmf.*;                       
import org.cip4.jdflib.resource.*;                  
import org.cip4.jdflib.resource.process.*;

public abstract class JDFAutoSignal extends JDFMessage
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CHANNELMODE, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumChannelMode.getEnum(0), "FireAndForget");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.LASTREPEAT, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.REFID, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.EMPLOYEE, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.NOTIFICATION, 0x66666666);
        elemInfoTable[2] = new ElemInfoTable(ElementName.TRIGGER, 0x66666666);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoSignal
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoSignal(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoSignal
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoSignal(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoSignal
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoSignal(
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
        return " JDFAutoSignal[  --> " + super.toString() + " ]";
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
        Methods for Attribute LastRepeat
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LastRepeat
          * @param value: the value to set the attribute to
          */
        public void setLastRepeat(boolean value)
        {
            setAttribute(AttributeName.LASTREPEAT, value, null);
        }

        /**
          * (18) get boolean attribute LastRepeat
          * @return boolean the value of the attribute
          */
        public boolean getLastRepeat()
        {
            return getBoolAttribute(AttributeName.LASTREPEAT, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute refID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute refID
          * @param value: the value to set the attribute to
          */
        @Override
		public void setrefID(String value)
        {
            setAttribute(AttributeName.REFID, value, null);
        }

        /**
          * (23) get String attribute refID
          * @return the value of the attribute
          */
        @Override
		public String getrefID()
        {
            return getAttribute(AttributeName.REFID, null, JDFConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateEmployee
     * 
     * @param iSkip number of elements to skip
     * @return JDFEmployee the element
     */
    @Override
	public JDFEmployee getCreateEmployee(int iSkip)
    {
        return (JDFEmployee)getCreateElement_KElement(ElementName.EMPLOYEE, null, iSkip);
    }

    /**
     * (27) const get element Employee
     * @param iSkip number of elements to skip
     * @return JDFEmployee the element
     * default is getEmployee(0)     */
    @Override
	public JDFEmployee getEmployee(int iSkip)
    {
        return (JDFEmployee) getElement(ElementName.EMPLOYEE, null, iSkip);
    }

    /**
     * Get all Employee from the current element
     * 
     * @return Collection<JDFEmployee>
     */
    @Override
	public Collection<JDFEmployee> getAllEmployee()
    {
        Vector<JDFEmployee> v = new Vector<JDFEmployee>();

        JDFEmployee kElem = (JDFEmployee) getFirstChildElement(ElementName.EMPLOYEE, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFEmployee) kElem.getNextSiblingElement(ElementName.EMPLOYEE, null);
        }

        return v;
    }

    /**
     * (30) append element Employee
     */
    @Override
	public JDFEmployee appendEmployee() throws JDFException
    {
        return (JDFEmployee) appendElement(ElementName.EMPLOYEE, null);
    }

    /**
     * (24) const get element Notification
     * @return JDFNotification the element
     */
    public JDFNotification getNotification()
    {
        return (JDFNotification) getElement(ElementName.NOTIFICATION, null, 0);
    }

    /** (25) getCreateNotification
     * 
     * @return JDFNotification the element
     */
    public JDFNotification getCreateNotification()
    {
        return (JDFNotification) getCreateElement_KElement(ElementName.NOTIFICATION, null, 0);
    }

    /**
     * (29) append element Notification
     */
    public JDFNotification appendNotification() throws JDFException
    {
        return (JDFNotification) appendElementN(ElementName.NOTIFICATION, 1, null);
    }

    /**
     * (24) const get element Trigger
     * @return JDFTrigger the element
     */
    public JDFTrigger getTrigger()
    {
        return (JDFTrigger) getElement(ElementName.TRIGGER, null, 0);
    }

    /** (25) getCreateTrigger
     * 
     * @return JDFTrigger the element
     */
    public JDFTrigger getCreateTrigger()
    {
        return (JDFTrigger) getCreateElement_KElement(ElementName.TRIGGER, null, 0);
    }

    /**
     * (29) append element Trigger
     */
    public JDFTrigger appendTrigger() throws JDFException
    {
        return (JDFTrigger) appendElementN(ElementName.TRIGGER, 1, null);
    }

}// end namespace JDF
