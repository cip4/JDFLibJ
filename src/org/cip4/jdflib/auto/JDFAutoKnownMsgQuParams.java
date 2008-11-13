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

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFElement;

public abstract class JDFAutoKnownMsgQuParams extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CHANNELMODE, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumChannelMode.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.EXACT, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.LISTCOMMANDS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.LISTQUERIES, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[4] = new AtrInfoTable(AttributeName.LISTREGISTRATIONS, 0x33333111, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[5] = new AtrInfoTable(AttributeName.LISTSIGNALS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "true");
        atrInfoTable[6] = new AtrInfoTable(AttributeName.PERSISTENT, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoKnownMsgQuParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoKnownMsgQuParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoKnownMsgQuParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoKnownMsgQuParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoKnownMsgQuParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoKnownMsgQuParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoKnownMsgQuParams[  --> " + super.toString() + " ]";
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
            return EnumChannelMode.getEnum(getAttribute(AttributeName.CHANNELMODE, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Exact
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Exact
          * @param value: the value to set the attribute to
          */
        public void setExact(boolean value)
        {
            setAttribute(AttributeName.EXACT, value, null);
        }

        /**
          * (18) get boolean attribute Exact
          * @return boolean the value of the attribute
          */
        public boolean getExact()
        {
            return getBoolAttribute(AttributeName.EXACT, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ListCommands
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ListCommands
          * @param value: the value to set the attribute to
          */
        public void setListCommands(boolean value)
        {
            setAttribute(AttributeName.LISTCOMMANDS, value, null);
        }

        /**
          * (18) get boolean attribute ListCommands
          * @return boolean the value of the attribute
          */
        public boolean getListCommands()
        {
            return getBoolAttribute(AttributeName.LISTCOMMANDS, null, true);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ListQueries
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ListQueries
          * @param value: the value to set the attribute to
          */
        public void setListQueries(boolean value)
        {
            setAttribute(AttributeName.LISTQUERIES, value, null);
        }

        /**
          * (18) get boolean attribute ListQueries
          * @return boolean the value of the attribute
          */
        public boolean getListQueries()
        {
            return getBoolAttribute(AttributeName.LISTQUERIES, null, true);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ListRegistrations
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ListRegistrations
          * @param value: the value to set the attribute to
          */
        public void setListRegistrations(boolean value)
        {
            setAttribute(AttributeName.LISTREGISTRATIONS, value, null);
        }

        /**
          * (18) get boolean attribute ListRegistrations
          * @return boolean the value of the attribute
          */
        public boolean getListRegistrations()
        {
            return getBoolAttribute(AttributeName.LISTREGISTRATIONS, null, true);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ListSignals
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ListSignals
          * @param value: the value to set the attribute to
          */
        public void setListSignals(boolean value)
        {
            setAttribute(AttributeName.LISTSIGNALS, value, null);
        }

        /**
          * (18) get boolean attribute ListSignals
          * @return boolean the value of the attribute
          */
        public boolean getListSignals()
        {
            return getBoolAttribute(AttributeName.LISTSIGNALS, null, true);
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

}// end namespace JDF
