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

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.JDFDate;

public abstract class JDFAutoMessage extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.AGENTNAME, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.AGENTVERSION, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.ICSVERSIONS, 0x33331111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.ID, 0x22222222, AttributeInfo.EnumAttributeType.ID, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.SENDERID, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.TIME, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.TYPE, 0x22222222, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.VERSION, 0x33331111, AttributeInfo.EnumAttributeType.JDFJMFVersion, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoMessage
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoMessage(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMessage
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoMessage(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMessage
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoMessage(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoMessage[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute AgentName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AgentName
          * @param value: the value to set the attribute to
          */
        public void setAgentName(String value)
        {
            setAttribute(AttributeName.AGENTNAME, value, null);
        }

        /**
          * (23) get String attribute AgentName
          * @return the value of the attribute
          */
        public String getAgentName()
        {
            return getAttribute(AttributeName.AGENTNAME, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AgentVersion
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AgentVersion
          * @param value: the value to set the attribute to
          */
        public void setAgentVersion(String value)
        {
            setAttribute(AttributeName.AGENTVERSION, value, null);
        }

        /**
          * (23) get String attribute AgentVersion
          * @return the value of the attribute
          */
        public String getAgentVersion()
        {
            return getAttribute(AttributeName.AGENTVERSION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ICSVersions
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ICSVersions
          * @param value: the value to set the attribute to
          */
        public void setICSVersions(VString value)
        {
            setAttribute(AttributeName.ICSVERSIONS, value, null);
        }

        /**
          * (21) get VString attribute ICSVersions
          * @return VString the value of the attribute
          */
        public VString getICSVersions()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.ICSVERSIONS, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ID
          * @param value: the value to set the attribute to
          */
        public void setID(String value)
        {
            setAttribute(AttributeName.ID, value, null);
        }

        /**
          * (23) get String attribute ID
          * @return the value of the attribute
          */
        public String getID()
        {
            return getAttribute(AttributeName.ID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SenderID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SenderID
          * @param value: the value to set the attribute to
          */
        public void setSenderID(String value)
        {
            setAttribute(AttributeName.SENDERID, value, null);
        }

        /**
          * (23) get String attribute SenderID
          * @return the value of the attribute
          */
        public String getSenderID()
        {
            return getAttribute(AttributeName.SENDERID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Time
        --------------------------------------------------------------------- */
        /**
          * (11) set attribute Time
          * @param value: the value to set the attribute to or null
          */
        public void setTime(JDFDate value)
        {
            JDFDate date = value;
            if (date == null) date = new JDFDate();
            setAttribute(AttributeName.TIME, date.getDateTimeISO(), null);
        }

        /**
          * (12) get JDFDate attribute Time
          * @return JDFDate the value of the attribute
          */
        public JDFDate getTime()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.TIME, null, JDFConstants.EMPTYSTRING);
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

}// end namespace JDF
