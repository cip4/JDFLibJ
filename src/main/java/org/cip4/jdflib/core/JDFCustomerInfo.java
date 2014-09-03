/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2009 The International Cooperation for the Integration of 
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
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFCustomerInfo.java
 *
 * Last changes
 *
 * 2002-07-02 JG added GetHRefs()
 *
 */

package org.cip4.jdflib.core;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoCustomerInfo;
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.ifaces.IMatches;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFContact.EnumContactType;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.Node;

/**
 * Title: JDFCustomerInfo.java Description: Copyright: Copyright (c) 2002 Company: Heidelberger Druckmaschinen
 * 
 * @author Dietrich Mucha
 * @version 1.0
 */

public class JDFCustomerInfo extends JDFAutoCustomerInfo implements IMatches
{
	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BILLINGCODE, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CUSTOMERID, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.CUSTOMERJOBNAME, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.CUSTOMERORDERID, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.CUSTOMERPROJECTID, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.RREFS, 0x44444433, AttributeInfo.EnumAttributeType.IDREFS, null, null);
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		AttributeInfo ai;

		if (getParentNode().getLocalName().equals(ElementName.JDF))
		{
			ai = super.getTheAttributeInfo_JDFElement().updateReplace(atrInfoTable);
		}
		else
		{
			ai = super.getTheAttributeInfo().updateReplace(atrInfoTable);
		}

		return ai;
	}

	// //////////////////////////////////////////////////////////////////////////
	// /

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COMPANY, 0x44444446);
		elemInfoTable[1] = new ElemInfoTable(ElementName.CONTACT, 0x33333331);
		elemInfoTable[2] = new ElemInfoTable(ElementName.CUSTOMERMESSAGE, 0x33333311);
	}

	// //////////////////////////////////////////////////////////////////////////
	// /
	@Override
	protected ElementInfo getTheElementInfo()
	{
		ElementInfo ei;

		if (getParentNode().getLocalName().equals(ElementName.JDF))
		{
			ei = new ElementInfo(super.getTheElementInfo_JDFElement(), elemInfoTable);
		}
		else
		{
			ei = new ElementInfo(super.getTheElementInfo(), elemInfoTable);
		}
		return ei;
	}

	/**
	 * Constructor for JDFCustomerInfo
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFCustomerInfo(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFCustomerInfo
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFCustomerInfo(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFCustomerInfo
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFCustomerInfo(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoCustomerInfo#init()
	 */
	@Override
	public boolean init()
	{
		final Node n = getParentNode();
		if ((n != null) && (n instanceof JDFResourcePool))
		{
			super.init();
			setResStatus(EnumResStatus.Available, false);
			setPartUsage(JDFResource.EnumPartUsage.Implicit);
		}
		return true;
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoCustomerInfo#toString()
	 */
	@Override
	public String toString()
	{
		return "JDFCustomerInfo[  --> " + super.toString() + " ]";
	}

	/**
	 * Get the linked resources matching some conditions
	 * 
	 * @param mResAtt map of Resource attributes to search for
	 * @param bFollowRefs true if internal references shall be followed
	 * 
	 * @return VResource - vector with all elements matching the conditions
	 * 
	 * default: GetLinkedResources(new JDFAttributeMap(), false)
	 */
	public VElement getLinkedResources(final JDFAttributeMap mResAtt, final boolean bFollowRefs)
	{
		final VElement v = getChildElementVector(null, null, null, true, 0, false);
		final VElement vL = new VElement();

		for (int i = 0; i < v.size(); i++)
		{
			if ((v.elementAt(i)) instanceof JDFRefElement)
			{
				final JDFRefElement l = (JDFRefElement) v.elementAt(i);
				JDFResource r = l.getTarget();
				r = r == null ? null : r.getResourceRoot();

				if (r != null && r.includesAttributes(mResAtt, true))
				{
					vL.addElement(r);
					if (bFollowRefs)
					{
						vL.appendUnique(r.getvHRefRes(bFollowRefs, true));
					}
				}
			}
		}

		return vL;
	}

	/**
	 * get a Contact with at least one contacttype set
	 * 
	 * @param contactType the contatcttype string
	 * @param iSkip number of occurrences to skip - if 0 take the first
	 * @return a matching JDFContact, null if none are found
	 */
	public JDFContact getContactWithContactType(final String contactType, final int iSkip)
	{
		return (JDFContact) getChildWithMatchingAttribute(ElementName.CONTACT, AttributeName.CONTACTTYPES, null, contactType, iSkip, true, EnumAttributeType.NMTOKENS);
	}

	/**
	 * get a Contact with at least one contactType set; create on if non existent
	 * 
	 * @param contactType the contactType string
	 * @param iSkip number of occurrences to skip - if 0 take the first
	 * @return a matching JDFContact, null if none are found
	 */
	public JDFContact getCreateContactWithContactType(final String contactType, final int iSkip)
	{
		JDFContact c = getContactWithContactType(contactType, iSkip);
		return c == null ? appendContact(contactType) : c;
	}

	// //////////////////////////////////////////////////////////////////////////
	/**
	 * get a list of contacts with at least one matching contactType set
	 * 
	 * @param contactType the contactType to look for - if null return all contacts
	 * @return VElement the vector of matching JDFContacts, null if none are found
	 */
	public VElement getContactVectorWithContactType(final String contactType)
	{
		final VElement v = getChildElementVector(ElementName.CONTACT, null, null, true, 0, true);
		if (isWildCard(contactType))
		{
			return v.size() == 0 ? null : v;
		}
		final VElement v2 = new VElement();
		final int siz = v.size();
		for (int i = 0; i < siz; i++)
		{
			final JDFContact contact = (JDFContact) v.elementAt(i);
			final VString contactTypes = contact.getContactTypes();
			if (contactTypes.contains(contactType))
			{
				v2.add(contact);
			}
		}
		return v2.size() > 0 ? v2 : null;
	}

	/**
	 * add a contact with a given contacttype
	 * 
	 * @param typ
	 * @return
	 */
	public JDFContact appendContact(final EnumContactType typ)
	{
		final JDFContact c = appendContact();
		c.setContactTypes(typ);
		return c;
	}

	/**
	 * add a contact with a given contacttype
	 * 
	 * @param typ
	 * @return
	 */
	public JDFContact appendContact(final String typ)
	{
		final JDFContact c = appendContact();
		c.setContactTypes(typ);
		return c;
	}

	@Override
	public boolean matches(Object subset)
	{
		boolean matches = false;
		if (subset instanceof String)
		{
			String subString = StringUtil.normalize((String) subset, true);
			matches = subString == null ? false : subString.equalsIgnoreCase(getCustomerID());
		}
		else if (subset instanceof JDFCustomerInfo)
		{
			JDFCustomerInfo ci = (JDFCustomerInfo) subset;
			String customerID = StringUtil.normalize(getCustomerID(), true);
			String othercustomerID = StringUtil.normalize(ci.getCustomerID(), true);
			if (customerID != null && othercustomerID != null)
			{
				matches = customerID.equals(othercustomerID);
			}
			else
			{
				JDFContact contact = getContactWithContactType(EnumContactType.Customer.getName(), 0);

				matches = contact == null ? false : contact.matches(ci.getContactWithContactType(EnumContactType.Customer.getName(), 0));
			}
		}
		return matches;
	}
}
