/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFArtDelivery.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.intent;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoArtDelivery;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFDropItem;
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFSpanArtHandling;
import org.cip4.jdflib.span.JDFSpanDeliveryCharge;
import org.cip4.jdflib.span.JDFSpanTransfer;
import org.cip4.jdflib.span.JDFStringSpan;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

/**
 * 
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFArtDelivery extends JDFAutoArtDelivery
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFArtDelivery
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 * 
	 */
	public JDFArtDelivery(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFArtDelivery
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * 
	 * @throws DOMException
	 */
	public JDFArtDelivery(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFArtDelivery
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 * 
	 */
	public JDFArtDelivery(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFArtDelivery[  --> " + super.toString() + " ]";
	}

	/**
	 * Get parent node of 'this' - node ArtDeliveryIntent
	 * 
	 * @return JDFArtDeliveryIntent: ArtDeliveryIntent node
	 */
	public JDFArtDeliveryIntent getParentArtDeliveryIntent()
	{
		Node parentNode = getParentNode();
		return (parentNode instanceof JDFArtDeliveryIntent) ? (JDFArtDeliveryIntent) parentNode : null;
	}

	/**
	 * Get of 'this' the value of element ArtHandling. If not specified, get the default value of element ArtHandling, that is specified in it's parent element (node
	 * ArtDeliveryIntent)
	 * 
	 * @return JDFSpanArtHandling: element value
	 */
	@Override
	public JDFSpanArtHandling getArtHandling()
	{
		if (hasChildElement(ElementName.ARTHANDLING, null))
		{
			return super.getArtHandling();
		}
		JDFArtDeliveryIntent parentArtDeliveryIntent = getParentArtDeliveryIntent();
		return parentArtDeliveryIntent == null ? null : parentArtDeliveryIntent.getArtHandling();
	}

	/**
	 * Get of 'this' the value of element DeliveryCharge. If not specified, get the default value of element DeliveryCharge, that is specified in it's parent element (node
	 * ArtDeliveryIntent)
	 * 
	 * @return JDFSpanDeliveryCharge: element value
	 */
	@Override
	public JDFSpanDeliveryCharge getDeliveryCharge()
	{
		if (hasChildElement(ElementName.DELIVERYCHARGE, null))
		{
			return super.getDeliveryCharge();
		}
		JDFArtDeliveryIntent parentArtDeliveryIntent = getParentArtDeliveryIntent();
		return parentArtDeliveryIntent == null ? null : parentArtDeliveryIntent.getDeliveryCharge();
	}

	/**
	 * Get of 'this' the value of element Method. If not specified, get the default value of element Method, that is specified in it's parent element (node ArtDeliveryIntent)
	 * 
	 * @return JDFNameSpan: element value
	 */
	@Override
	public JDFNameSpan getMethod()
	{
		if (hasChildElement(ElementName.METHOD, null))
		{
			return super.getMethod();
		}
		JDFArtDeliveryIntent parentArtDeliveryIntent = getParentArtDeliveryIntent();
		return parentArtDeliveryIntent == null ? null : parentArtDeliveryIntent.getMethod();
	}

	/**
	 * Get of 'this' the value of attribute PreflightStatus. If not specified, get the default value of attribute PreflightStatus, that is specified in it's parent element (node
	 * ArtDeliveryIntent)
	 * 
	 * @return EnumPreflightStatus: attribute value
	 */
	// TODO this does not work!
	// public JDFArtDelivery.EnumPreflightStatus getPreflightStatus()
	// {
	// if (hasAttribute(AttributeName.PREFLIGHTSTATUS, null, false))
	// {
	// return super.getPreflightStatus();
	// }
	// return getParentArtDeliveryIntent().getPreflightStatus();
	// }
	/**
	 * Get of 'this' the value of element ReturnMethod. If not specified, get the default value of element ReturnMethod, that is specified in it's parent element (node
	 * ArtDeliveryIntent)
	 * 
	 * @return JDFNameSpan: element value
	 */
	@Override
	public JDFNameSpan getReturnMethod()
	{
		if (hasChildElement(ElementName.RETURNMETHOD, null))
		{
			return super.getReturnMethod();
		}
		JDFArtDeliveryIntent parentArtDeliveryIntent = getParentArtDeliveryIntent();
		return parentArtDeliveryIntent == null ? null : parentArtDeliveryIntent.getReturnMethod();
	}

	/**
	 * Get of 'this' the value of element ServiceLevel. If not specified, get the default value of element ServiceLevel, that is specified in it's parent element (node
	 * ArtDeliveryIntent)
	 * 
	 * @return JDFStringSpan: element value
	 */
	@Override
	public JDFStringSpan getServiceLevel()
	{
		if (hasChildElement(ElementName.SERVICELEVEL, null))
		{
			return super.getServiceLevel();
		}
		JDFArtDeliveryIntent parentArtDeliveryIntent = getParentArtDeliveryIntent();
		return parentArtDeliveryIntent == null ? null : parentArtDeliveryIntent.getServiceLevel();
	}

	/**
	 * Get of 'this' the value of element Transfer. If not specified, get the default value of element Transfer, that is specified in it's parent element (node ArtDeliveryIntent)
	 * 
	 * @return JDFSpanTransfer: element value
	 */
	@Override
	public JDFSpanTransfer getTransfer()
	{
		if (hasChildElement(ElementName.TRANSFER, null))
		{
			return super.getTransfer();
		}
		JDFArtDeliveryIntent parentArtDeliveryIntent = getParentArtDeliveryIntent();
		return parentArtDeliveryIntent == null ? null : parentArtDeliveryIntent.getTransfer();
	}

	/**
	 * Get of 'this' the iSkip-th child element Contact. If not specified, get the child element Contact of it's parent element (node ArtDeliveryIntent)
	 * 
	 * @return JDFContact: the found element
	 */
	@Override
	public JDFContact getContact(int iSkip)
	{
		if (hasChildElement(ElementName.CONTACT, JDFConstants.EMPTYSTRING))
		{
			return super.getContact(iSkip);
		}
		JDFArtDeliveryIntent parentArtDeliveryIntent = getParentArtDeliveryIntent();
		return parentArtDeliveryIntent == null ? null : parentArtDeliveryIntent.getContact();
	}

	/**
	 * 
	 * @param dropItem
	 */
	public void setFromDropItem(JDFDropItem dropItem)
	{
		if (dropItem == null)
			return;

		copyAttribute(AttributeName.AMOUNT, dropItem);
		KElement e = dropItem.getFirstChildElement();
		while (e != null)
		{
			copyElement(e, null);
			e = e.getNextSiblingElement();
		}
	}
}
