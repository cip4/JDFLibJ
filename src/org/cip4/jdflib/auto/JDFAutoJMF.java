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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.jmf.JDFAcknowledge;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFQuery;
import org.cip4.jdflib.jmf.JDFRegistration;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.pool.JDFPool;
import org.cip4.jdflib.util.JDFDate;
    /*
    *****************************************************************************
    class JDFAutoJMF : public JDFPool

    *****************************************************************************
    */

public abstract class JDFAutoJMF extends JDFPool
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.DEVICEID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.ICSVERSIONS, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.MAXVERSION, 0x33333111, AttributeInfo.EnumAttributeType.JDFJMFVersion, EnumVersion.getEnum(0), null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.RESPONSEURL, 0x33333311, AttributeInfo.EnumAttributeType.URL, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.SENDERID, 0x22222222, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.TIMESTAMP, 0x22222222, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.VERSION, 0x22222222, AttributeInfo.EnumAttributeType.JDFJMFVersion, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[6];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COMMAND, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.ACKNOWLEDGE, 0x33333333);
        elemInfoTable[2] = new ElemInfoTable(ElementName.RESPONSE, 0x33333333);
        elemInfoTable[3] = new ElemInfoTable(ElementName.SIGNAL, 0x33333333);
        elemInfoTable[4] = new ElemInfoTable(ElementName.QUERY, 0x33333333);
        elemInfoTable[5] = new ElemInfoTable(ElementName.REGISTRATION, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoJMF
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoJMF(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoJMF
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoJMF(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoJMF
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoJMF(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoJMF[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
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
        Methods for Attribute MaxVersion
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute MaxVersion
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setMaxVersion(EnumVersion enumVar)
        {
            setAttribute(AttributeName.MAXVERSION, enumVar.getName(), null);
        }



        /**
          * (9) get attribute MaxVersion
          * @return the value of the attribute
          */
        public EnumVersion getMaxVersion()
        {
            return EnumVersion.getEnum(getAttribute(AttributeName.MAXVERSION, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ResponseURL
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ResponseURL
          * @param value: the value to set the attribute to
          */
        public void setResponseURL(String value)
        {
            setAttribute(AttributeName.RESPONSEURL, value, null);
        }



        /**
          * (23) get String attribute ResponseURL
          * @return the value of the attribute
          */
        public String getResponseURL()
        {
            return getAttribute(AttributeName.RESPONSEURL, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute TimeStamp
        --------------------------------------------------------------------- */
        /**
          * (10) set attribute TimeStamp
          * @deprecated 2005-12-02 use setTimeStamp(null)
          */
        public void setTimeStamp()
        {
            setAttribute(AttributeName.TIMESTAMP, new JDFDate().getDateTimeISO(), null);
        }



        /**
          * (11) set attribute TimeStamp
          * @param value: the value to set the attribute to or null
          */
        public void setTimeStamp(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.TIMESTAMP, value.getDateTimeISO(), null);
        }



        /**
          * (12) get JDFDate attribute TimeStamp
          * @return JDFDate the value of the attribute
          */
        public JDFDate getTimeStamp()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.TIMESTAMP, null, JDFConstants.EMPTYSTRING);
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

    /** (26) getCreateCommand
     * 
     * @param iSkip number of elements to skip
     * @return JDFCommand the element
     */
    public JDFCommand getCreateCommand(int iSkip)
    {
        return (JDFCommand)getCreateElement_KElement(ElementName.COMMAND, null, iSkip);
    }



    /**
     * (27) const get element Command
     * @param iSkip number of elements to skip
     * @return JDFCommand the element
     * default is getCommand(0)     */
    public JDFCommand getCommand(int iSkip)
    {
        return (JDFCommand) getElement(ElementName.COMMAND, null, iSkip);
    }



    public JDFCommand appendCommand()
    {
        return (JDFCommand) appendElement(ElementName.COMMAND, null);
    }

    /** (26) getCreateAcknowledge
     * 
     * @param iSkip number of elements to skip
     * @return JDFAcknowledge the element
     */
    public JDFAcknowledge getCreateAcknowledge(int iSkip)
    {
        return (JDFAcknowledge)getCreateElement_KElement(ElementName.ACKNOWLEDGE, null, iSkip);
    }



    /**
     * (27) const get element Acknowledge
     * @param iSkip number of elements to skip
     * @return JDFAcknowledge the element
     * default is getAcknowledge(0)     */
    public JDFAcknowledge getAcknowledge(int iSkip)
    {
        return (JDFAcknowledge) getElement(ElementName.ACKNOWLEDGE, null, iSkip);
    }



    public JDFAcknowledge appendAcknowledge()
    {
        return (JDFAcknowledge) appendElement(ElementName.ACKNOWLEDGE, null);
    }

    /** (26) getCreateResponse
     * 
     * @param iSkip number of elements to skip
     * @return JDFResponse the element
     */
    public JDFResponse getCreateResponse(int iSkip)
    {
        return (JDFResponse)getCreateElement_KElement(ElementName.RESPONSE, null, iSkip);
    }



    /**
     * (27) const get element Response
     * @param iSkip number of elements to skip
     * @return JDFResponse the element
     * default is getResponse(0)     */
    public JDFResponse getResponse(int iSkip)
    {
        return (JDFResponse) getElement(ElementName.RESPONSE, null, iSkip);
    }



    public JDFResponse appendResponse()
    {
        return (JDFResponse) appendElement(ElementName.RESPONSE, null);
    }

    /** (26) getCreateSignal
     * 
     * @param iSkip number of elements to skip
     * @return JDFSignal the element
     */
    public JDFSignal getCreateSignal(int iSkip)
    {
        return (JDFSignal)getCreateElement_KElement(ElementName.SIGNAL, null, iSkip);
    }



    /**
     * (27) const get element Signal
     * @param iSkip number of elements to skip
     * @return JDFSignal the element
     * default is getSignal(0)     */
    public JDFSignal getSignal(int iSkip)
    {
        return (JDFSignal) getElement(ElementName.SIGNAL, null, iSkip);
    }



    public JDFSignal appendSignal()
    {
        return (JDFSignal) appendElement(ElementName.SIGNAL, null);
    }

    /** (26) getCreateQuery
     * 
     * @param iSkip number of elements to skip
     * @return JDFQuery the element
     */
    public JDFQuery getCreateQuery(int iSkip)
    {
        return (JDFQuery)getCreateElement_KElement(ElementName.QUERY, null, iSkip);
    }



    /**
     * (27) const get element Query
     * @param iSkip number of elements to skip
     * @return JDFQuery the element
     * default is getQuery(0)     */
    public JDFQuery getQuery(int iSkip)
    {
        return (JDFQuery) getElement(ElementName.QUERY, null, iSkip);
    }



    public JDFQuery appendQuery()
    {
        return (JDFQuery) appendElement(ElementName.QUERY, null);
    }

    /** (26) getCreateRegistration
     * 
     * @param iSkip number of elements to skip
     * @return JDFRegistration the element
     */
    public JDFRegistration getCreateRegistration(int iSkip)
    {
        return (JDFRegistration)getCreateElement_KElement(ElementName.REGISTRATION, null, iSkip);
    }



    /**
     * (27) const get element Registration
     * @param iSkip number of elements to skip
     * @return JDFRegistration the element
     * default is getRegistration(0)     */
    public JDFRegistration getRegistration(int iSkip)
    {
        return (JDFRegistration) getElement(ElementName.REGISTRATION, null, iSkip);
    }



    public JDFRegistration appendRegistration()
    {
        return (JDFRegistration) appendElement(ElementName.REGISTRATION, null);
    }

}// end namespace JDF
