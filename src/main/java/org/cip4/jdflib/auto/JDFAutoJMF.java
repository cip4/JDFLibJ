/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.jmf.JDFAcknowledge;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFQuery;
import org.cip4.jdflib.jmf.JDFRegistration;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.pool.JDFPool;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.util.JDFDate;

/**
 *****************************************************************************
 * class JDFAutoJMF : public JDFPool
 *****************************************************************************
 * 
 */

public abstract class JDFAutoJMF extends JDFPool
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.AGENTNAME, 0x3333331111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.AGENTVERSION, 0x3333331111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DEVICEID, 0x3333333333l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ICSVERSIONS, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.MAXVERSION, 0x3333333111l, AttributeInfo.EnumAttributeType.JDFJMFVersion, EnumVersion.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.RESPONSEURL, 0x4444433311l, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.SENDERID, 0x2222222222l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.TIMESTAMP, 0x2222222222l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.VERSION, 0x2222222222l, AttributeInfo.EnumAttributeType.JDFJMFVersion, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[7];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.EMPLOYEE, 0x3333331111l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.COMMAND, 0x3333333333l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.ACKNOWLEDGE, 0x3333333333l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.RESPONSE, 0x3333333333l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.SIGNAL, 0x3333333333l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.QUERY, 0x3333333333l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.REGISTRATION, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoJMF
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoJMF(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoJMF
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoJMF(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoJMF
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoJMF(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AgentName ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AgentName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAgentName(String value)
	{
		setAttribute(AttributeName.AGENTNAME, value, null);
	}

	/**
	 * (23) get String attribute AgentName
	 *
	 * @return the value of the attribute
	 */
	public String getAgentName()
	{
		return getAttribute(AttributeName.AGENTNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AgentVersion
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AgentVersion
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAgentVersion(String value)
	{
		setAttribute(AttributeName.AGENTVERSION, value, null);
	}

	/**
	 * (23) get String attribute AgentVersion
	 *
	 * @return the value of the attribute
	 */
	public String getAgentVersion()
	{
		return getAttribute(AttributeName.AGENTVERSION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DeviceID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DeviceID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDeviceID(String value)
	{
		setAttribute(AttributeName.DEVICEID, value, null);
	}

	/**
	 * (23) get String attribute DeviceID
	 *
	 * @return the value of the attribute
	 */
	public String getDeviceID()
	{
		return getAttribute(AttributeName.DEVICEID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ICSVersions ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ICSVersions
	 *
	 * @param value the value to set the attribute to
	 */
	public void setICSVersions(VString value)
	{
		setAttribute(AttributeName.ICSVERSIONS, value, null);
	}

	/**
	 * (21) get VString attribute ICSVersions
	 *
	 * @return VString the value of the attribute
	 */
	public VString getICSVersions()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.ICSVERSIONS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxVersion ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MaxVersion
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMaxVersion(EVersion enumVar)
	{
		setAttribute(AttributeName.MAXVERSION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute MaxVersion
	 *
	 * @return the value of the attribute
	 */
	public EVersion getEMaxVersion()
	{
		return EVersion.getEnum(getAttribute(AttributeName.MAXVERSION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxVersion ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MaxVersion
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setMaxVersion(EnumVersion enumVar)
	{
		setAttribute(AttributeName.MAXVERSION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute MaxVersion
	 *
	 * @return the value of the attribute
	 */
	public EnumVersion getMaxVersion()
	{
		return EnumVersion.getEnum(getAttribute(AttributeName.MAXVERSION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ResponseURL ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ResponseURL
	 *
	 * @param value the value to set the attribute to
	 */
	public void setResponseURL(String value)
	{
		setAttribute(AttributeName.RESPONSEURL, value, null);
	}

	/**
	 * (23) get String attribute ResponseURL
	 *
	 * @return the value of the attribute
	 */
	public String getResponseURL()
	{
		return getAttribute(AttributeName.RESPONSEURL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SenderID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SenderID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSenderID(String value)
	{
		setAttribute(AttributeName.SENDERID, value, null);
	}

	/**
	 * (23) get String attribute SenderID
	 *
	 * @return the value of the attribute
	 */
	public String getSenderID()
	{
		return getAttribute(AttributeName.SENDERID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TimeStamp ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute TimeStamp
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setTimeStamp(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.TIMESTAMP, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute TimeStamp
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getTimeStamp()
	{
		final String str = getAttribute(AttributeName.TIMESTAMP, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Employee
	 *
	 * @return JDFEmployee the element
	 */
	public JDFEmployee getEmployee()
	{
		return (JDFEmployee) getElement(ElementName.EMPLOYEE, null, 0);
	}

	/**
	 * (25) getCreateEmployee
	 * 
	 * @return JDFEmployee the element
	 */
	public JDFEmployee getCreateEmployee()
	{
		return (JDFEmployee) getCreateElement_JDFElement(ElementName.EMPLOYEE, null, 0);
	}

	/**
	 * (26) getCreateEmployee
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFEmployee the element
	 */
	public JDFEmployee getCreateEmployee(int iSkip)
	{
		return (JDFEmployee) getCreateElement_JDFElement(ElementName.EMPLOYEE, null, iSkip);
	}

	/**
	 * (27) const get element Employee
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFEmployee the element default is getEmployee(0)
	 */
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
		return getChildArrayByClass(JDFEmployee.class, false, 0);
	}

	/**
	 * (30) append element Employee
	 *
	 * @return JDFEmployee the element
	 */
	public JDFEmployee appendEmployee()
	{
		return (JDFEmployee) appendElement(ElementName.EMPLOYEE, null);
	}

	/**
	 * (24) const get element Command
	 *
	 * @return JDFCommand the element
	 */
	public JDFCommand getCommand()
	{
		return (JDFCommand) getElement(ElementName.COMMAND, null, 0);
	}

	/**
	 * (25) getCreateCommand
	 * 
	 * @return JDFCommand the element
	 */
	public JDFCommand getCreateCommand()
	{
		return (JDFCommand) getCreateElement_JDFElement(ElementName.COMMAND, null, 0);
	}

	/**
	 * (26) getCreateCommand
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFCommand the element
	 */
	public JDFCommand getCreateCommand(int iSkip)
	{
		return (JDFCommand) getCreateElement_JDFElement(ElementName.COMMAND, null, iSkip);
	}

	/**
	 * (27) const get element Command
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCommand the element default is getCommand(0)
	 */
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
		return getChildArrayByClass(JDFCommand.class, false, 0);
	}

	/**
	 * (30) append element Command
	 *
	 * @return JDFCommand the element
	 */
	public JDFCommand appendCommand()
	{
		return (JDFCommand) appendElement(ElementName.COMMAND, null);
	}

	/**
	 * (24) const get element Acknowledge
	 *
	 * @return JDFAcknowledge the element
	 */
	public JDFAcknowledge getAcknowledge()
	{
		return (JDFAcknowledge) getElement(ElementName.ACKNOWLEDGE, null, 0);
	}

	/**
	 * (25) getCreateAcknowledge
	 * 
	 * @return JDFAcknowledge the element
	 */
	public JDFAcknowledge getCreateAcknowledge()
	{
		return (JDFAcknowledge) getCreateElement_JDFElement(ElementName.ACKNOWLEDGE, null, 0);
	}

	/**
	 * (26) getCreateAcknowledge
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFAcknowledge the element
	 */
	public JDFAcknowledge getCreateAcknowledge(int iSkip)
	{
		return (JDFAcknowledge) getCreateElement_JDFElement(ElementName.ACKNOWLEDGE, null, iSkip);
	}

	/**
	 * (27) const get element Acknowledge
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFAcknowledge the element default is getAcknowledge(0)
	 */
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
		return getChildArrayByClass(JDFAcknowledge.class, false, 0);
	}

	/**
	 * (30) append element Acknowledge
	 *
	 * @return JDFAcknowledge the element
	 */
	public JDFAcknowledge appendAcknowledge()
	{
		return (JDFAcknowledge) appendElement(ElementName.ACKNOWLEDGE, null);
	}

	/**
	 * (24) const get element Response
	 *
	 * @return JDFResponse the element
	 */
	public JDFResponse getResponse()
	{
		return (JDFResponse) getElement(ElementName.RESPONSE, null, 0);
	}

	/**
	 * (25) getCreateResponse
	 * 
	 * @return JDFResponse the element
	 */
	public JDFResponse getCreateResponse()
	{
		return (JDFResponse) getCreateElement_JDFElement(ElementName.RESPONSE, null, 0);
	}

	/**
	 * (26) getCreateResponse
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFResponse the element
	 */
	public JDFResponse getCreateResponse(int iSkip)
	{
		return (JDFResponse) getCreateElement_JDFElement(ElementName.RESPONSE, null, iSkip);
	}

	/**
	 * (27) const get element Response
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFResponse the element default is getResponse(0)
	 */
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
		return getChildArrayByClass(JDFResponse.class, false, 0);
	}

	/**
	 * (30) append element Response
	 *
	 * @return JDFResponse the element
	 */
	public JDFResponse appendResponse()
	{
		return (JDFResponse) appendElement(ElementName.RESPONSE, null);
	}

	/**
	 * (24) const get element Signal
	 *
	 * @return JDFSignal the element
	 */
	public JDFSignal getSignal()
	{
		return (JDFSignal) getElement(ElementName.SIGNAL, null, 0);
	}

	/**
	 * (25) getCreateSignal
	 * 
	 * @return JDFSignal the element
	 */
	public JDFSignal getCreateSignal()
	{
		return (JDFSignal) getCreateElement_JDFElement(ElementName.SIGNAL, null, 0);
	}

	/**
	 * (26) getCreateSignal
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFSignal the element
	 */
	public JDFSignal getCreateSignal(int iSkip)
	{
		return (JDFSignal) getCreateElement_JDFElement(ElementName.SIGNAL, null, iSkip);
	}

	/**
	 * (27) const get element Signal
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFSignal the element default is getSignal(0)
	 */
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
		return getChildArrayByClass(JDFSignal.class, false, 0);
	}

	/**
	 * (30) append element Signal
	 *
	 * @return JDFSignal the element
	 */
	public JDFSignal appendSignal()
	{
		return (JDFSignal) appendElement(ElementName.SIGNAL, null);
	}

	/**
	 * (24) const get element Query
	 *
	 * @return JDFQuery the element
	 */
	public JDFQuery getQuery()
	{
		return (JDFQuery) getElement(ElementName.QUERY, null, 0);
	}

	/**
	 * (25) getCreateQuery
	 * 
	 * @return JDFQuery the element
	 */
	public JDFQuery getCreateQuery()
	{
		return (JDFQuery) getCreateElement_JDFElement(ElementName.QUERY, null, 0);
	}

	/**
	 * (26) getCreateQuery
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFQuery the element
	 */
	public JDFQuery getCreateQuery(int iSkip)
	{
		return (JDFQuery) getCreateElement_JDFElement(ElementName.QUERY, null, iSkip);
	}

	/**
	 * (27) const get element Query
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQuery the element default is getQuery(0)
	 */
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
		return getChildArrayByClass(JDFQuery.class, false, 0);
	}

	/**
	 * (30) append element Query
	 *
	 * @return JDFQuery the element
	 */
	public JDFQuery appendQuery()
	{
		return (JDFQuery) appendElement(ElementName.QUERY, null);
	}

	/**
	 * (24) const get element Registration
	 *
	 * @return JDFRegistration the element
	 */
	public JDFRegistration getRegistration()
	{
		return (JDFRegistration) getElement(ElementName.REGISTRATION, null, 0);
	}

	/**
	 * (25) getCreateRegistration
	 * 
	 * @return JDFRegistration the element
	 */
	public JDFRegistration getCreateRegistration()
	{
		return (JDFRegistration) getCreateElement_JDFElement(ElementName.REGISTRATION, null, 0);
	}

	/**
	 * (26) getCreateRegistration
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFRegistration the element
	 */
	public JDFRegistration getCreateRegistration(int iSkip)
	{
		return (JDFRegistration) getCreateElement_JDFElement(ElementName.REGISTRATION, null, iSkip);
	}

	/**
	 * (27) const get element Registration
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFRegistration the element default is getRegistration(0)
	 */
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
		return getChildArrayByClass(JDFRegistration.class, false, 0);
	}

	/**
	 * (30) append element Registration
	 *
	 * @return JDFRegistration the element
	 */
	public JDFRegistration appendRegistration()
	{
		return (JDFRegistration) appendElement(ElementName.REGISTRATION, null);
	}

}
