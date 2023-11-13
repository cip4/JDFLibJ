/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
/**
 * ========================================================================== class JDFDropItemIntent extends JDFResource ==========================================================================
 *
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de using a code generator Warning! very preliminary test version. Interface subject to change without prior notice! Revision history: ...
 */

package org.cip4.jdflib.resource.intent;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDropItemIntent;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.process.JDFDropItem;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.DOMException;

public class JDFDropItemIntent extends JDFAutoDropItemIntent
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFDropItemIntent
	 *
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFDropItemIntent(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFDropItemIntent
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFDropItemIntent(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFDropItemIntent
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFDropItemIntent(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	@Override
	public String toString()
	{
		return "JDFDropItemIntent[  --> " + super.toString() + " ]";
	}

	/**
	 * return a vector of unknown element nodenames
	 *
	 * @default getUnknownElements(bIgnorePrivate, 99999999)
	 */
	@Override
	public VString getUnknownElements(final boolean bIgnorePrivate, final int nMax)
	{
		return getUnknownPoolElements(JDFElement.EnumPoolType.ProductionIntent, nMax);
	}

	/**
	 * Get ancestor node of 'this' - node DeliveryIntent
	 *
	 * @return JDFDeliveryIntent: DeliveryIntent node
	 */
	public JDFDeliveryIntent getParentDeliveryIntent()
	{
		return (JDFDeliveryIntent) getDeepParent(ElementName.DELIVERYINTENT, 0);
	}

	/**
	 * Get parent node of 'this' - node DropIntent
	 *
	 * @return DropIntent: DropIntent node
	 */
	public JDFDropIntent getParentDropIntent()
	{
		return (JDFDropIntent) getDeepParent(ElementName.DROPINTENT, 0);
	}

	/**
	 * Get of 'this' the value of attribute AdditionalAmount. If not specified, get the default value of attribute AdditionalAmount, that is specified in it's parent element (node
	 * DeliveryIntent)
	 *
	 * @return WString: attribute value
	 * @deprecated
	 */
	@Deprecated
	public int getAdditionalAmount_Integer()
	{
		return getAdditionalAmount();
	}

	/**
	 *
	 * @see org.cip4.jdflib.auto.JDFAutoDropItemIntent#getAdditionalAmount()
	 */
	@Override
	public int getAdditionalAmount()
	{
		if (hasAttribute(AttributeName.ADDITIONALAMOUNT))
		{
			return super.getAdditionalAmount();
		}
		return getParentDeliveryIntent().getAdditionalAmount();
	}

	/**
	 *
	 * @param dropItem
	 */
	public void setFromDropItem(final JDFDropItem dropItem)
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

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoDropItemIntent#getDropID()
	 */
	@Override
	public String getDropID()
	{

		String dropID = super.getDropID();
		if (StringUtil.isEmpty(dropID))
		{
			final JDFDropIntent parentDropIntent = getParentDropIntent();
			dropID = parentDropIntent == null ? null : parentDropIntent.getDropID();
		}
		return dropID;
	}
}
