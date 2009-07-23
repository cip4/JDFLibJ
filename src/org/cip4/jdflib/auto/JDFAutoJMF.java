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
import java.util.Vector;                            
import java.util.zip.DataFormatException;           

import org.apache.xerces.dom.CoreDocumentImpl;      
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.pool.*;                      
import org.cip4.jdflib.jmf.*;                       
import org.cip4.jdflib.resource.process.*;          
import org.cip4.jdflib.util.*;           
    /*
    *****************************************************************************
    class JDFAutoJMF : public JDFPool

    *****************************************************************************
    */

public abstract class JDFAutoJMF extends JDFPool
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.AGENTNAME, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.AGENTVERSION, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.DEVICEID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.ICSVERSIONS, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.MAXVERSION, 0x33333111, AttributeInfo.EnumAttributeType.JDFJMFVersion, EnumVersion.getEnum(0), null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.RESPONSEURL, 0x33333311, AttributeInfo.EnumAttributeType.URL, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.SENDERID, 0x22222222, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.TIMESTAMP, 0x22222222, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.VERSION, 0x22222222, AttributeInfo.EnumAttributeType.JDFJMFVersion, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[7];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.EMPLOYEE, 0x33331111);
        elemInfoTable[1] = new ElemInfoTable(ElementName.COMMAND, 0x33333333);
        elemInfoTable[2] = new ElemInfoTable(ElementName.ACKNOWLEDGE, 0x33333333);
        elemInfoTable[3] = new ElemInfoTable(ElementName.RESPONSE, 0x33333333);
        elemInfoTable[4] = new ElemInfoTable(ElementName.SIGNAL, 0x33333333);
        elemInfoTable[5] = new ElemInfoTable(ElementName.QUERY, 0x33333333);
        elemInfoTable[6] = new ElemInfoTable(ElementName.REGISTRATION, 0x33333333);
    }
    
    @Override
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


    @Override
	public String toString()
    {
        return " JDFAutoJMF[  --> " + super.toString() + " ]";
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
            setAttribute(AttributeName.MAXVERSION, enumVar==null ? null : enumVar.getName(), null);
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
          * (11) set attribute TimeStamp
          * @param value: the value to set the attribute to or null
          */
        public void setTimeStamp(JDFDate value)
        {
            JDFDate date = value;
            if (date == null) date = new JDFDate();
            setAttribute(AttributeName.TIMESTAMP, date.getDateTimeISO(), null);
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
                    // throw new JDFException("not a valid date string. Malformed JDF - return null");
                }
            }
            return nMyDate;
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
    public JDFEmployee getCreateEmployee(int iSkip)
    {
        return (JDFEmployee)getCreateElement_KElement(ElementName.EMPLOYEE, null, iSkip);
    }

    /**
     * (27) const get element Employee
     * @param iSkip number of elements to skip
     * @return JDFEmployee the element
     * default is getEmployee(0)     */
    public JDFEmployee getEmployee(int iSkip)
    {
        return (JDFEmployee) getElement(ElementName.EMPLOYEE, null, iSkip);
    }

    /**
     * Get all Employee from the current element
     * 
     * @return Collection<JDFEmployee>, null if none are available
     */
    public Collection<JDFEmployee> getAllEmployee()
    {
        final VElement vc = getChildElementVector(ElementName.EMPLOYEE, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFEmployee> v = new Vector<JDFEmployee>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFEmployee) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Employee
     */
    public JDFEmployee appendEmployee() throws JDFException
    {
        return (JDFEmployee) appendElement(ElementName.EMPLOYEE, null);
    }

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

    /**
     * Get all Command from the current element
     * 
     * @return Collection<JDFCommand>, null if none are available
     */
    public Collection<JDFCommand> getAllCommand()
    {
        final VElement vc = getChildElementVector(ElementName.COMMAND, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFCommand> v = new Vector<JDFCommand>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFCommand) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Command
     */
    public JDFCommand appendCommand() throws JDFException
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

    /**
     * Get all Acknowledge from the current element
     * 
     * @return Collection<JDFAcknowledge>, null if none are available
     */
    public Collection<JDFAcknowledge> getAllAcknowledge()
    {
        final VElement vc = getChildElementVector(ElementName.ACKNOWLEDGE, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFAcknowledge> v = new Vector<JDFAcknowledge>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFAcknowledge) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Acknowledge
     */
    public JDFAcknowledge appendAcknowledge() throws JDFException
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

    /**
     * Get all Response from the current element
     * 
     * @return Collection<JDFResponse>, null if none are available
     */
    public Collection<JDFResponse> getAllResponse()
    {
        final VElement vc = getChildElementVector(ElementName.RESPONSE, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFResponse> v = new Vector<JDFResponse>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFResponse) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Response
     */
    public JDFResponse appendResponse() throws JDFException
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

    /**
     * Get all Signal from the current element
     * 
     * @return Collection<JDFSignal>, null if none are available
     */
    public Collection<JDFSignal> getAllSignal()
    {
        final VElement vc = getChildElementVector(ElementName.SIGNAL, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFSignal> v = new Vector<JDFSignal>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFSignal) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Signal
     */
    public JDFSignal appendSignal() throws JDFException
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

    /**
     * Get all Query from the current element
     * 
     * @return Collection<JDFQuery>, null if none are available
     */
    public Collection<JDFQuery> getAllQuery()
    {
        final VElement vc = getChildElementVector(ElementName.QUERY, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFQuery> v = new Vector<JDFQuery>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFQuery) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Query
     */
    public JDFQuery appendQuery() throws JDFException
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

    /**
     * Get all Registration from the current element
     * 
     * @return Collection<JDFRegistration>, null if none are available
     */
    public Collection<JDFRegistration> getAllRegistration()
    {
        final VElement vc = getChildElementVector(ElementName.REGISTRATION, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFRegistration> v = new Vector<JDFRegistration>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFRegistration) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Registration
     */
    public JDFRegistration appendRegistration() throws JDFException
    {
        return (JDFRegistration) appendElement(ElementName.REGISTRATION, null);
    }

}// end namespace JDF
