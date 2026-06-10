/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2022 The International Cooperation for the Integration of
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

import java.util.Collection;
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoComChannel.EnumChannelType;
import org.cip4.jdflib.auto.JDFAutoContact;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.ifaces.IMatches;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.JavaEnumUtil;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.DOMException;

/**
 * @author rainer prosi
 * @date way before Jan 31, 2012
 */
public class JDFContact extends JDFAutoContact implements IMatches
{
	private static final long serialVersionUID = 1L;

	/**
	 * @author rainerprosi
	 * @date Jan 31, 2012
	 */
	public enum EnumContactType
	{
		/** */
		Accounting,
		/** */
		Administrator,
		/** */
		Agency,
		/** */
		Approver,
		/** */
		ArtReturn,
		/** */
		Author,
		/** */
		Billing,
		/** */
		Customer,
		/** */
		Delivery,
		/** */
		DeliveryCharge,
		/** */
		Designer,
		/** */
		Editor,
		/** */
		Employee,
		/** */
		Illustrator,
		/** */
		Owner,
		/** */
		Photographer,
		/** */
		Pickup,
		/** */
		Sender,
		/** */
		Supplier,
		/** */
		SurplusReturn,
		/** */
		TelephoneSanitizer;

		/**
		 * @param enumName the name of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumContactType getEnum(final String enumName)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumContactType.class, enumName, null);
		}
	}

	/**
	 * Constructor for JDFContact
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFContact(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFContact
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFContact(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFContact
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 */
	public JDFContact(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
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
		return "JDFContact[  --> " + super.toString() + " ]";
	}

	/**
	 * Set attribute ContactTypes
	 *
	 * @deprecated use getContactTypes
	 * @param value the value to set the attribute to
	 */
	@Deprecated
	public void setExtendedContactTypes(final VString value)
	{
		setContactTypes(value);
	}

	/**
	 * Get string attribute ContactTypes
	 *
	 * @deprecated use setContactTypes
	 * @return vKString the vaue of the attribute
	 */
	@Deprecated
	public VString getExtendedContactTypes()
	{
		return getContactTypes();
	}

	/**
	 * @param typ the single contacttype to set this contact to
	 */
	public void setContactTypes(final EnumContactType typ)
	{
		if (typ == null)
		{
			removeAttribute(AttributeName.CONTACTTYPES);
		}
		else
		{
			setAttribute(AttributeName.CONTACTTYPES, typ.name(), null);
		}
	}

	/**
	 * @param typ the single contacttype to set this contact to
	 */
	public void setContactTypes(final String typ)
	{
		setAttribute(AttributeName.CONTACTTYPES, typ, null);
	}

	/**
	 * @param typ the single contacttype to set this contact to
	 */
	public void addContactTypes(final EnumContactType typ)
	{
		if (typ != null)
		{
			appendAttribute(AttributeName.CONTACTTYPES, typ.name(), null, JDFCoreConstants.BLANK, true);
		}
	}

	public JDFComChannel getComChannel(final EnumChannelType ct)
	{
		final JDFComChannel cc = JDFComChannel.getChannelByType(this, ct);
		if (cc == null)
		{
			return JDFComChannel.getChannelByType(getPerson(), ct);
		}
		return cc;
	}

	public JDFComChannel appendComChannel(final EnumChannelType ct, final String locator)
	{
		return JDFComChannel.appendChannel(this, ct, locator);
	}

	/**
	 * append a comChannel with a given channelType
	 *
	 * @param channelType
	 * @return
	 */
	public JDFComChannel appendComChannel(final EnumChannelType channelType)
	{
		final JDFComChannel comCh = appendComChannel();
		comCh.setChannelType(channelType);
		return comCh;
	}

	/**
	 * @param firstName
	 * @param familyName
	 * @return
	 */
	public JDFPerson setPerson(final String firstName, final String familyName)
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

	/**
	 * merge two contacts while avoiding duplicates
	 *
	 * @param other
	 */
	public void merge(final JDFContact other)
	{
		if (other == null || equals(other))
		{
			return;
		}

		if (getPerson() == null)
		{
			copyElement(other.getPerson(), null);
		}
		if (getAddress() == null)
		{
			copyElement(other.getAddress(), null);
		}
		if (getCompany() == null)
		{
			copyElement(other.getCompany(), null);
		}
		mergeComChannels(other);
		mergeContactTypes(other);

	}

	private void mergeComChannels(final JDFContact other)
	{
		Collection<JDFComChannel> cs = getAllComChannel();
		if (cs == null)
		{
			cs = new Vector<>();
		}
		final Collection<JDFComChannel> cso = other.getAllComChannel();
		if (cso != null)
		{
			for (final JDFComChannel occ : cso)
			{
				if (ContainerUtil.getMatch(cs, occ, 0) == null)
				{
					copyElement(occ, null);
					cs.add(occ);
				}
			}
		}
	}

	private void mergeContactTypes(final JDFContact other)
	{
		final VString contactTypes = getContactTypes();
		final int s0 = contactTypes.size();
		final Collection<String> vs = ContainerUtil.addAll(contactTypes, other.getContactTypes());
		ContainerUtil.unify(vs);
		if (vs != null && vs.size() > s0)
		{
			final VString nct = new VString();
			nct.addAll(vs);
			setContactTypes(nct);
		}
	}

	/**
	 * checks a match if subset is a String, then we check userID (ignoring case) if subset is a JDFContact, we do heuristic matching of the person, company and address
	 *
	 * @see org.cip4.jdflib.ifaces.IMatches#matches(java.lang.Object)
	 */
	@Override
	public boolean matches(final Object subset)
	{
		boolean matches = false;
		if (subset instanceof String)
		{
			final String subString = StringUtil.normalize((String) subset, true);
			matches = subString == null ? false : subString.equalsIgnoreCase(getUserID());
		}
		else if (subset instanceof JDFContact)
		{
			final JDFContact other = (JDFContact) subset;
			final String userID = StringUtil.normalize(getUserID(), true);
			final String otherUserID = StringUtil.normalize(other.getUserID(), true);
			if (userID != null && otherUserID != null)
			{
				matches = userID.equals(otherUserID);
			}
			else
			{
				matches = hasChildElement(ElementName.ADDRESS, null) || hasChildElement(ElementName.COMPANY, null) || hasChildElement(ElementName.PERSON, null);
				matches = matches && ContainerUtil.matchesExisting(getAddress(), other.getAddress());
				matches = matches && ContainerUtil.matchesExisting(getCompany(), other.getCompany());
				matches = matches && ContainerUtil.matchesExisting(getPerson(), other.getPerson());

			}
		}
		return matches;
	}

	/**
	 * also search in person
	 *
	 * @see org.cip4.jdflib.auto.JDFAutoContact#getAddress()
	 */
	@Override
	public JDFAddress getAddress()
	{
		JDFAddress a = super.getAddress();
		if (a == null)
		{
			final JDFPerson p = getPerson();
			if (p != null)
			{
				a = p.getAddress();
			}
		}
		return a;
	}

	/**
	 * also search in person
	 *
	 * @see org.cip4.jdflib.auto.JDFAutoContact#getCreateAddress()
	 */
	@Override
	public JDFAddress getCreateAddress()
	{
		final JDFAddress address = getAddress();
		return address == null ? appendAddress() : address;
	}

}
