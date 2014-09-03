/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of 
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
 * ==========================================================================
 * class JDFPerson
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPerson;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.ifaces.IMatches;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.DOMException;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * before July 6, 2009
 */
public class JDFPerson extends JDFAutoPerson implements IMatches
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFPerson
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFPerson(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFPerson
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 * 
	 */
	public JDFPerson(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFPerson
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 * 
	 */
	public JDFPerson(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoPerson#toString()
	 */
	@Override
	public String toString()
	{
		return "JDFPerson[  --> " + super.toString() + " ]";
	}

	/**
	 * sets the familyname to value and also appends FamilyName to value in case descriptivename is either FirstName or empty/null
	 * 
	 * @param familyName the family name to set
	 */
	@Override
	public void setFamilyName(final String familyName)
	{
		final String oldName = getAttribute(AttributeName.FAMILYNAME, null, null);
		super.setFamilyName(familyName);
		if (familyName == null)
		{
			return;
		}
		String descName = getDescriptiveName();
		if (descName == null || descName.equals(""))
		{
			setDescriptiveName(familyName);
		}
		else if (descName.equals(getFirstName()))
		{
			setDescriptiveName(getFirstName() + " " + familyName);
		}
		else if (oldName != null && descName.endsWith(oldName))
		{
			descName = StringUtil.replaceString(descName, oldName, familyName);
			setDescriptiveName(descName);
		}
	}

	/**
	 * sets the firstame to value and also prepends firstName to value in case descriptivename is either FirstName or empty/null
	 * 
	 * @param firstName the given name to set
	 */
	@Override
	public void setFirstName(final String firstName)
	{
		final String oldName = getAttribute(AttributeName.FIRSTNAME, null, null);

		super.setFirstName(firstName);
		if (firstName == null)
		{
			return;
		}
		String descName = getDescriptiveName();
		if (descName == null || descName.equals(""))
		{
			setDescriptiveName(firstName);
		}
		else if (descName.equals(getFamilyName()))
		{
			setDescriptiveName(firstName + " " + getFamilyName());
		}
		else if (oldName != null && descName.startsWith(oldName))
		{
			descName = StringUtil.replaceString(descName, oldName, firstName);
			setDescriptiveName(descName);
		}
	}

	/**
	 * get first + last name if descname does not exist
	 * @see org.cip4.jdflib.core.JDFElement#getDescriptiveName()
	 */
	@Override
	public String getDescriptiveName()
	{
		String s = StringUtil.getNonEmpty(super.getDescriptiveName());

		if (s == null)
		{
			VString vs = new VString();
			s = StringUtil.getNonEmpty(getNamePrefix());
			if (s != null)
			{
				vs.add(s);
			}
			s = StringUtil.getNonEmpty(getFirstName());
			if (s != null)
			{
				vs.add(s);
			}
			s = StringUtil.getNonEmpty(getFamilyName());
			if (s != null)
			{
				vs.add(s);
			}
			s = StringUtil.getNonEmpty(getNameSuffix());
			if (s != null)
			{
				vs.add(s);
			}
			s = StringUtil.setvString(vs, " ", null, null);
		}
		return s;
	}

	/**
	 * checks firstname, familyname, aditionalNames and address
	 * 
	 * @see org.cip4.jdflib.ifaces.IMatches#matches(java.lang.Object)
	 */
	@Override
	public boolean matches(Object subset)
	{
		boolean matches = false;
		if (subset instanceof String)
		{
			matches = StringUtil.getDistance(getDescriptiveName(), (String) subset, true, true, true) <= 2;
		}
		else if (subset instanceof JDFPerson)
		{
			JDFPerson other = (JDFPerson) subset;
			if (StringUtil.getDistance(getFamilyName(), other.getFamilyName(), true, true, true) > 1)
				return false;
			if (StringUtil.getDistance(getFirstName(), other.getFirstName(), true, true, true) > 1)
				return false;
			if (StringUtil.getDistance(getAdditionalNames(), other.getAdditionalNames(), true, true, true) > 1)
				return false;
			matches = ContainerUtil.matchesExisting(getAddress(0), other.getAddress(0));
		}
		return matches;
	}
}
