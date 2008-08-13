/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of 
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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFContact.java
 *
 * Last changes
 *
 * 2002-07-02 JG added support for extended ContactTypes
 *
 */
package org.cip4.jdflib.resource.process;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoContact;
import org.cip4.jdflib.auto.JDFAutoComChannel.EnumChannelType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.VString;
import org.w3c.dom.DOMException;

public class JDFContact extends JDFAutoContact
{
	private static final long serialVersionUID = 1L;

	public static final class EnumContactType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumContactType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName
		 *            the name of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumContactType getEnum(String enumName)
		{
			return (EnumContactType) getEnum(EnumContactType.class, enumName);
		}

		/**
		 * @param enumValue
		 *            the value of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumContactType getEnum(int enumValue)
		{
			return (EnumContactType) getEnum(EnumContactType.class, enumValue);
		}

		/**
		 * @return a map of all EnumContactType enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumContactType.class);
		}

		/**
		 * @return a list of all EnumContactType enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumContactType.class);
		}

		/**
		 * @return an iterator over the enum objects
		 */
		public static Iterator iterator()
		{
			return iterator(EnumContactType.class);
		}

		public static final EnumContactType Accounting = new EnumContactType(
				"Accounting");
		public static final EnumContactType Administrator = new EnumContactType(
				"Administrator");
		public static final EnumContactType Approver = new EnumContactType(
				"Approver");
		public static final EnumContactType ArtReturn = new EnumContactType(
				"ArtReturn");
		public static final EnumContactType Billing = new EnumContactType(
				"Billing");
		public static final EnumContactType Customer = new EnumContactType(
				"Customer");
		public static final EnumContactType Delivery = new EnumContactType(
				"Delivery");
		public static final EnumContactType DeliveryCharge = new EnumContactType(
				"DeliveryCharge");
		public static final EnumContactType Owner = new EnumContactType("Owner");
		public static final EnumContactType Pickup = new EnumContactType(
				"Pickup");
		public static final EnumContactType Sender = new EnumContactType(
				"Sender");
		public static final EnumContactType Supplier = new EnumContactType(
				"Supplier");
		public static final EnumContactType SurplusReturn = new EnumContactType(
				"SurplusReturn");
	}

	/**
	 * Constructor for JDFContact
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFContact(CoreDocumentImpl myOwnerDocument, String qualifiedName)
			throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFContact
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFContact(CoreDocumentImpl myOwnerDocument, String myNamespaceURI,
			String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFContact
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFContact(CoreDocumentImpl myOwnerDocument, String myNamespaceURI,
			String qualifiedName, String myLocalName) throws DOMException
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
	public String toString()
	{
		return "JDFContact[  --> " + super.toString() + " ]";
	}

	/**
	 * Set attribute ContactTypes
	 * 
	 * @deprecated use getContactTypes
	 *@param vKString
	 *            value: the value to set the attribute to
	 */
	public void setExtendedContactTypes(VString value)
	{
		setContactTypes(value);
	}

	/**
	 * Get string attribute ContactTypes
	 * 
	 * @deprecated use setContactTypes
	 * @return vKString the vaue of the attribute
	 */
	public VString getExtendedContactTypes()
	{
		return getContactTypes();
	}

	/**
	 * @param typ
	 *            the single contacttype to set this contact to
	 */
	public void setContactTypes(EnumContactType typ)
	{
		if (typ == null)
			removeAttribute(AttributeName.CONTACTTYPES);
		else
			setAttribute(AttributeName.CONTACTTYPES, typ.getName(), null);

	}

	/**
	 * @param typ
	 *            the single contacttype to set this contact to
	 */
	public void addContactTypes(EnumContactType typ)
	{
		if (typ != null)
			appendAttribute(AttributeName.CONTACTTYPES, typ.getName(), null,
					" ", true);

	}

	/**
	 * append a comChannel with a given channelType
	 * 
	 * @param channelType
	 * @return
	 */
	public JDFComChannel appendComChannel(EnumChannelType channelType)
	{
		JDFComChannel comCh = appendComChannel();
		comCh.setChannelType(channelType);
		return comCh;
	}

	/**
	 * @param firstName
	 * @param familyName
	 */
	public JDFPerson setPerson(String firstName, String familyName)
	{
		JDFPerson p = null;
		if (firstName != null || familyName != null)
		{
			p = getCreatePerson();
			p.setFirstName(firstName);
			p.setFamilyName(familyName);
		}
		return p;
	}

}
