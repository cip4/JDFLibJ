/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2021 The International Cooperation for the Integration of
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
 * JDFAddress.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoAddress;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.ifaces.IMatches;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.DOMException;

public class JDFAddress extends JDFAutoAddress implements IMatches
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFAddress
	 *
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFAddress(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAddress
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFAddress(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAddress
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFAddress(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
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
		return "JDFAddress[  --> " + super.toString() + " ]";
	}

	/**
	 *
	 * @return the extended address text
	 */
	public String getExtendedAddressText()
	{
		if (hasChildElement(ElementName.EXTENDEDADDRESS, null))
		{
			return getExtendedAddress().getText();
		}
		return JDFConstants.EMPTYSTRING;
	}

	public void setExtendedAddressText(final String extendedAddress)
	{
		getCreateExtendedAddress().setText(extendedAddress);
	}

	public JDFElement appendAddressLine(final String line)
	{
		final JDFElement e = appendAddressLine();
		e.setText(line);
		return e;
	}

	public String getAddressLineText(final int line)
	{
		final JDFElement e = super.getAddressLine(line);
		return e == null ? null : e.getText();
	}

	public StringArray getAddressLines()
	{
		final Collection<JDFElement> lines = getAllAddressLine();
		final StringArray ret = new StringArray();
		for (final JDFElement line : lines)
		{
			ret.addNonEmpty(line.getText());
		}
		return ret;
	}

	/**
	 * Get all AddressLine from the current element
	 *
	 * @return Collection<JDFElement>, null if none are available
	 */
	@Override
	public Collection<JDFElement> getAllAddressLine()
	{
		final Collection<KElement> c = getChildArray(ElementName.ADDRESSLINE, null);
		final Collection<JDFElement> cc = new ArrayList<>();
		for (final KElement l : c)
			cc.add((JDFElement) l);
		return cc;
	}

	/**
	 * match another address by calculating various levenshtein distances
	 * id either address is missang an entry, that entry will be assumed to match
	 *
	 * @see org.cip4.jdflib.ifaces.IMatches#matches(java.lang.Object)
	 */
	@Override
	public boolean matches(final Object subset)
	{
		if (!(subset instanceof JDFAddress))
			return false;
		final JDFAddress other = (JDFAddress) subset;
		if (StringUtil.getDistance(getCity(), other.getCity(), true, true, true) > 2)
			return false;
		if (StringUtil.getDistance(getCountry(), other.getCountry(), true, true, true) > 2)
			return false;
		if (StringUtil.getDistance(getCountryCode(), other.getCountryCode(), true, true, true) > 0)
			return false;
		if (StringUtil.getDistance(getPostalCode(), other.getPostalCode(), true, true, true) > 0)
			return false;
		if (StringUtil.getDistance(getRegion(), other.getRegion(), true, true, true) > 2)
			return false;
		if (StringUtil.getDistance(getStreet(), other.getStreet(), true, true, true) > 2)
			return false;

		return true;
	}
}
