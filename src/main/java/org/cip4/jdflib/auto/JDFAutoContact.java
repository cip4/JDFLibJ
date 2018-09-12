/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFAddress;
import org.cip4.jdflib.resource.process.JDFComChannel;
import org.cip4.jdflib.resource.process.JDFCompany;
import org.cip4.jdflib.resource.process.JDFPerson;

/**
 *****************************************************************************
 * class JDFAutoContact : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoContact extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CONTACTTYPES, 0x22222222, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CONTACTTYPEDETAILS, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.USERID, 0x33311111, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.ADDRESS, 0x66666666);
		elemInfoTable[1] = new ElemInfoTable(ElementName.COMCHANNEL, 0x33333333);
		elemInfoTable[2] = new ElemInfoTable(ElementName.COMPANY, 0x66666661);
		elemInfoTable[3] = new ElemInfoTable(ElementName.PERSON, 0x66666666);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoContact
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoContact(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoContact
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoContact(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoContact
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoContact(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoContact[  --> " + super.toString() + " ]";
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
	 * --------------------------------------------------------------------- Methods for Attribute ContactTypes ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ContactTypes
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setContactTypes(VString value)
	{
		setAttribute(AttributeName.CONTACTTYPES, value, null);
	}

	/**
	 * (21) get VString attribute ContactTypes
	 * 
	 * @return VString the value of the attribute
	 */
	public VString getContactTypes()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.CONTACTTYPES, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ContactTypeDetails ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ContactTypeDetails
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setContactTypeDetails(String value)
	{
		setAttribute(AttributeName.CONTACTTYPEDETAILS, value, null);
	}

	/**
	 * (23) get String attribute ContactTypeDetails
	 * 
	 * @return the value of the attribute
	 */
	public String getContactTypeDetails()
	{
		return getAttribute(AttributeName.CONTACTTYPEDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute UserID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute UserID
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setUserID(String value)
	{
		setAttribute(AttributeName.USERID, value, null);
	}

	/**
	 * (23) get String attribute UserID
	 * 
	 * @return the value of the attribute
	 */
	public String getUserID()
	{
		return getAttribute(AttributeName.USERID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Address
	 * 
	 * @return JDFAddress the element
	 */
	public JDFAddress getAddress()
	{
		return (JDFAddress) getElement(ElementName.ADDRESS, null, 0);
	}

	/**
	 * (25) getCreateAddress
	 * 
	 * @return JDFAddress the element
	 */
	public JDFAddress getCreateAddress()
	{
		return (JDFAddress) getCreateElement_KElement(ElementName.ADDRESS, null, 0);
	}

	/**
	 * (29) append element Address
	 * 
	 * @return JDFAddress the element
	 * @throws JDFException if the element already exists
	 */
	public JDFAddress appendAddress() throws JDFException
	{
		return (JDFAddress) appendElementN(ElementName.ADDRESS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refAddress(JDFAddress refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (26) getCreateComChannel
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFComChannel the element
	 */
	public JDFComChannel getCreateComChannel(int iSkip)
	{
		return (JDFComChannel) getCreateElement_KElement(ElementName.COMCHANNEL, null, iSkip);
	}

	/**
	 * (27) const get element ComChannel
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFComChannel the element default is getComChannel(0)
	 */
	public JDFComChannel getComChannel(int iSkip)
	{
		return (JDFComChannel) getElement(ElementName.COMCHANNEL, null, iSkip);
	}

	/**
	 * Get all ComChannel from the current element
	 * 
	 * @return Collection<JDFComChannel>, null if none are available
	 */
	public Collection<JDFComChannel> getAllComChannel()
	{
		return getChildrenByClass(JDFComChannel.class, false, 0);
	}

	/**
	 * (30) append element ComChannel
	 * 
	 * @return JDFComChannel the element
	 */
	public JDFComChannel appendComChannel()
	{
		return (JDFComChannel) appendElement(ElementName.COMCHANNEL, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refComChannel(JDFComChannel refTarget)
	{
		refElement(refTarget);
	}

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
		return (JDFCompany) getCreateElement_KElement(ElementName.COMPANY, null, 0);
	}

	/**
	 * (29) append element Company
	 * 
	 * @return JDFCompany the element
	 * @throws JDFException if the element already exists
	 */
	public JDFCompany appendCompany() throws JDFException
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
	 * (24) const get element Person
	 * 
	 * @return JDFPerson the element
	 */
	public JDFPerson getPerson()
	{
		return (JDFPerson) getElement(ElementName.PERSON, null, 0);
	}

	/**
	 * (25) getCreatePerson
	 * 
	 * @return JDFPerson the element
	 */
	public JDFPerson getCreatePerson()
	{
		return (JDFPerson) getCreateElement_KElement(ElementName.PERSON, null, 0);
	}

	/**
	 * (29) append element Person
	 * 
	 * @return JDFPerson the element
	 * @throws JDFException if the element already exists
	 */
	public JDFPerson appendPerson() throws JDFException
	{
		return (JDFPerson) appendElementN(ElementName.PERSON, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refPerson(JDFPerson refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
