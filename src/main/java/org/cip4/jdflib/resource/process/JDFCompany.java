/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFCompany.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoCompany;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.ifaces.IMatches;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.DOMException;

/**
 *
 *
 * @author rainer prosi
 * @date June 9, 2011
 */
public class JDFCompany extends JDFAutoCompany implements IMatches
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFCompany
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFCompany(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFCompany
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 *
	 * @throws DOMException
	 */
	public JDFCompany(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFCompany
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 *
	 */
	public JDFCompany(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFCompany[  --> " + super.toString() + " ]";
	}

	/**
	 * checks whether the organization names match
	 *
	 * @see org.cip4.jdflib.ifaces.IMatches#matches(java.lang.Object)
	 */
	@Override
	public boolean matches(final Object subset)
	{
		boolean matches = false;
		if (subset instanceof String)
		{
			matches = StringUtil.getDistance(getOrganizationName(), (String) subset, true, true, true) <= 2;
		}
		else if (subset instanceof JDFCompany)
		{
			final JDFCompany other = (JDFCompany) subset;
			matches = matches(other.getOrganizationName());
		}
		return matches;
	}

	/**
	 * @see org.cip4.jdflib.core.JDFElement#getDescriptiveName() defaults to OrganizationName if not specified
	 */
	@Override
	public String getDescriptiveName()
	{
		final String descName = getNonEmpty(AttributeName.DESCRIPTIVENAME);
		return descName != null ? descName : getOrganizationName();
	}

	/**
	 *
	 * @return
	 */
	public VString getOrganizationalUnits()
	{
		final Collection<JDFElement> vou = getAllOrganizationalUnit();
		if (vou != null && !vou.isEmpty())
		{
			final VString v = new VString();
			for (final JDFElement e : vou)
			{
				v.addNonEmpty(e.getText());
			}
			return v;
		}
		else
		{
			return null;
		}
	}

	/**
	 *
	 * @param unit the unit name to set
	 */
	public void setOrganizationalUnit(final String unit)
	{
		removeChildren(ElementName.ORGANIZATIONALUNIT, null, null);
		appendOrganizationalUnit(unit);
	}

	/**
	 *
	 * @param unit the unit name to set
	 */
	public void appendOrganizationalUnit(final String unit)
	{
		if (!StringUtil.isEmpty(unit))
		{
			appendOrganizationalUnit().setText(unit);
		}
	}

	/**
	 * Get all OrganizationalUnit from the current element
	 *
	 * @return Collection<JDFElement>, empty if none are available
	 */
	@Override
	public Collection<JDFElement> getAllOrganizationalUnit()
	{
		final Collection<KElement> c = getChildArray(ElementName.ORGANIZATIONALUNIT, null);
		final Collection<JDFElement> cc = new ArrayList<>();
		for (final KElement l : c)
			cc.add((JDFElement) l);
		return cc;
	}

}
