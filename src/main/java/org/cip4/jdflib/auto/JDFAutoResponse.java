/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.process.JDFEmployee;

/**
 *****************************************************************************
 * class JDFAutoResponse : public JDFMessage
 *****************************************************************************
 * 
 */

public abstract class JDFAutoResponse extends JDFMessage
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ACKNOWLEDGED, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.LANGUAGES, 0x3333333333l, AttributeInfo.EnumAttributeType.languages, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.REFID, 0x3333333333l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.RETURNCODE, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, "0");
		atrInfoTable[4] = new AtrInfoTable(AttributeName.SUBSCRIBED, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.NOTIFICATION, 0x3333333333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.EMPLOYEE, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoResponse
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoResponse(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoResponse
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoResponse(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoResponse
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoResponse(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Acknowledged
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Acknowledged
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAcknowledged(boolean value)
	{
		setAttribute(AttributeName.ACKNOWLEDGED, value, null);
	}

	/**
	 * (18) get boolean attribute Acknowledged
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getAcknowledged()
	{
		return getBoolAttribute(AttributeName.ACKNOWLEDGED, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Languages ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Languages
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLanguages(VString value)
	{
		setAttribute(AttributeName.LANGUAGES, value, null);
	}

	/**
	 * (21) get VString attribute Languages
	 *
	 * @return VString the value of the attribute
	 */
	public VString getLanguages()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.LANGUAGES, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute refID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute refID
	 *
	 * @param value the value to set the attribute to
	 */
	@Override
	public void setrefID(String value)
	{
		setAttribute(AttributeName.REFID, value, null);
	}

	/**
	 * (23) get String attribute refID
	 *
	 * @return the value of the attribute
	 */
	@Override
	public String getrefID()
	{
		return getAttribute(AttributeName.REFID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ReturnCode ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ReturnCode
	 *
	 * @param value the value to set the attribute to
	 */
	public void setReturnCode(int value)
	{
		setAttribute(AttributeName.RETURNCODE, value, null);
	}

	/**
	 * (15) get int attribute ReturnCode
	 *
	 * @return int the value of the attribute
	 */
	@Override
	public int getReturnCode()
	{
		return getIntAttribute(AttributeName.RETURNCODE, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Subscribed ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Subscribed
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSubscribed(boolean value)
	{
		setAttribute(AttributeName.SUBSCRIBED, value, null);
	}

	/**
	 * (18) get boolean attribute Subscribed
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getSubscribed()
	{
		return getBoolAttribute(AttributeName.SUBSCRIBED, null, false);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateNotification
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFNotification the element
	 */
	public JDFNotification getCreateNotification(int iSkip)
	{
		return (JDFNotification) getCreateElement_JDFElement(ElementName.NOTIFICATION, null, iSkip);
	}

	/**
	 * (27) const get element Notification
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFNotification the element default is getNotification(0)
	 */
	public JDFNotification getNotification(int iSkip)
	{
		return (JDFNotification) getElement(ElementName.NOTIFICATION, null, iSkip);
	}

	/**
	 * Get all Notification from the current element
	 * 
	 * @return Collection<JDFNotification>, null if none are available
	 */
	public Collection<JDFNotification> getAllNotification()
	{
		return getChildArrayByClass(JDFNotification.class, false, 0);
	}

	/**
	 * (30) append element Notification
	 *
	 * @return JDFNotification the element
	 */
	public JDFNotification appendNotification()
	{
		return (JDFNotification) appendElement(ElementName.NOTIFICATION, null);
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

}
