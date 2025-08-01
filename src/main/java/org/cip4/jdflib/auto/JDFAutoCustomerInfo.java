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
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFCustomerMessage;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFCompany;
import org.cip4.jdflib.resource.process.JDFContact;

/**
 *****************************************************************************
 * class JDFAutoCustomerInfo : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoCustomerInfo extends JDFResource
{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFAutoCustomerInfo
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoCustomerInfo(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCustomerInfo
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoCustomerInfo(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCustomerInfo
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoCustomerInfo(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		final boolean bRet = super.init();
		setResourceClass(JDFResource.EnumResourceClass.Parameter);
		return bRet;
	}

	/**
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Parameter;
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BillingCode ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BillingCode
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBillingCode(String value)
	{
		setAttribute(AttributeName.BILLINGCODE, value, null);
	}

	/**
	 * (23) get String attribute BillingCode
	 *
	 * @return the value of the attribute
	 */
	public String getBillingCode()
	{
		return getAttribute(AttributeName.BILLINGCODE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CustomerID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CustomerID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCustomerID(String value)
	{
		setAttribute(AttributeName.CUSTOMERID, value, null);
	}

	/**
	 * (23) get String attribute CustomerID
	 *
	 * @return the value of the attribute
	 */
	public String getCustomerID()
	{
		return getAttribute(AttributeName.CUSTOMERID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CustomerJobName
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CustomerJobName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCustomerJobName(String value)
	{
		setAttribute(AttributeName.CUSTOMERJOBNAME, value, null);
	}

	/**
	 * (23) get String attribute CustomerJobName
	 *
	 * @return the value of the attribute
	 */
	public String getCustomerJobName()
	{
		return getAttribute(AttributeName.CUSTOMERJOBNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CustomerOrderID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CustomerOrderID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCustomerOrderID(String value)
	{
		setAttribute(AttributeName.CUSTOMERORDERID, value, null);
	}

	/**
	 * (23) get String attribute CustomerOrderID
	 *
	 * @return the value of the attribute
	 */
	public String getCustomerOrderID()
	{
		return getAttribute(AttributeName.CUSTOMERORDERID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CustomerProjectID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CustomerProjectID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCustomerProjectID(String value)
	{
		setAttribute(AttributeName.CUSTOMERPROJECTID, value, null);
	}

	/**
	 * (23) get String attribute CustomerProjectID
	 *
	 * @return the value of the attribute
	 */
	public String getCustomerProjectID()
	{
		return getAttribute(AttributeName.CUSTOMERPROJECTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Company
	 *
	 * @return JDFCompany the element
	 */
	public JDFCompany getCompany()
	{
		return (JDFCompany) getElement(ElementName.COMPANY, null, 0);
	}

	/**
	 * (25) getCreateCompany
	 * 
	 * @return JDFCompany the element
	 */
	public JDFCompany getCreateCompany()
	{
		return (JDFCompany) getCreateElement_JDFElement(ElementName.COMPANY, null, 0);
	}

	/**
	 * (29) append element Company
	 *
	 * @return JDFCompany the element @ if the element already exists
	 */
	public JDFCompany appendCompany()
	{
		return (JDFCompany) appendElementN(ElementName.COMPANY, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refCompany(JDFCompany refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Contact
	 *
	 * @return JDFContact the element
	 */
	@Override
	public JDFContact getContact()
	{
		return (JDFContact) getElement(ElementName.CONTACT, null, 0);
	}

	/**
	 * (25) getCreateContact
	 * 
	 * @return JDFContact the element
	 */
	@Override
	public JDFContact getCreateContact()
	{
		return (JDFContact) getCreateElement_JDFElement(ElementName.CONTACT, null, 0);
	}

	/**
	 * (26) getCreateContact
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFContact the element
	 */
	public JDFContact getCreateContact(int iSkip)
	{
		return (JDFContact) getCreateElement_JDFElement(ElementName.CONTACT, null, iSkip);
	}

	/**
	 * (27) const get element Contact
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFContact the element default is getContact(0)
	 */
	public JDFContact getContact(int iSkip)
	{
		return (JDFContact) getElement(ElementName.CONTACT, null, iSkip);
	}

	/**
	 * Get all Contact from the current element
	 * 
	 * @return Collection<JDFContact>, null if none are available
	 */
	public Collection<JDFContact> getAllContact()
	{
		return getChildArrayByClass(JDFContact.class, false, 0);
	}

	/**
	 * (30) append element Contact
	 *
	 * @return JDFContact the element
	 */
	@Override
	public JDFContact appendContact()
	{
		return (JDFContact) appendElement(ElementName.CONTACT, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refContact(JDFContact refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element CustomerMessage
	 *
	 * @return JDFCustomerMessage the element
	 */
	public JDFCustomerMessage getCustomerMessage()
	{
		return (JDFCustomerMessage) getElement(ElementName.CUSTOMERMESSAGE, null, 0);
	}

	/**
	 * (25) getCreateCustomerMessage
	 * 
	 * @return JDFCustomerMessage the element
	 */
	public JDFCustomerMessage getCreateCustomerMessage()
	{
		return (JDFCustomerMessage) getCreateElement_JDFElement(ElementName.CUSTOMERMESSAGE, null, 0);
	}

	/**
	 * (26) getCreateCustomerMessage
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFCustomerMessage the element
	 */
	public JDFCustomerMessage getCreateCustomerMessage(int iSkip)
	{
		return (JDFCustomerMessage) getCreateElement_JDFElement(ElementName.CUSTOMERMESSAGE, null, iSkip);
	}

	/**
	 * (27) const get element CustomerMessage
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCustomerMessage the element default is getCustomerMessage(0)
	 */
	public JDFCustomerMessage getCustomerMessage(int iSkip)
	{
		return (JDFCustomerMessage) getElement(ElementName.CUSTOMERMESSAGE, null, iSkip);
	}

	/**
	 * Get all CustomerMessage from the current element
	 * 
	 * @return Collection<JDFCustomerMessage>, null if none are available
	 */
	public Collection<JDFCustomerMessage> getAllCustomerMessage()
	{
		return getChildArrayByClass(JDFCustomerMessage.class, false, 0);
	}

	/**
	 * (30) append element CustomerMessage
	 *
	 * @return JDFCustomerMessage the element
	 */
	public JDFCustomerMessage appendCustomerMessage()
	{
		return (JDFCustomerMessage) appendElement(ElementName.CUSTOMERMESSAGE, null);
	}

}
