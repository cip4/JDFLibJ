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
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFAddress;
import org.cip4.jdflib.resource.process.JDFComChannel;

/**
 ***************************************************************************** class JDFAutoPerson : public JDFResource
 */

public abstract class JDFAutoPerson extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ADDITIONALNAMES, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.FAMILYNAME, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.PHONETICLASTNAME, 0x3333311111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.FIRSTNAME, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.PHONETICFIRSTNAME, 0x3333311111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.JOBTITLE, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.LANGUAGES, 0x3333331111l, AttributeInfo.EnumAttributeType.languages, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.NAMEPREFIX, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.NAMESUFFIX, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.ADDRESS, 0x6666666611l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.COMCHANNEL, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPerson
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPerson(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPerson
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPerson(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPerson
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPerson(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		boolean bRet = super.init();
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
	 * ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AdditionalNames
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AdditionalNames
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAdditionalNames(String value)
	{
		setAttribute(AttributeName.ADDITIONALNAMES, value, null);
	}

	/**
	 * (23) get String attribute AdditionalNames
	 *
	 * @return the value of the attribute
	 */
	public String getAdditionalNames()
	{
		return getAttribute(AttributeName.ADDITIONALNAMES, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FamilyName
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FamilyName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFamilyName(String value)
	{
		setAttribute(AttributeName.FAMILYNAME, value, null);
	}

	/**
	 * (23) get String attribute FamilyName
	 *
	 * @return the value of the attribute
	 */
	public String getFamilyName()
	{
		return getAttribute(AttributeName.FAMILYNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PhoneticLastName
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PhoneticLastName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPhoneticLastName(String value)
	{
		setAttribute(AttributeName.PHONETICLASTNAME, value, null);
	}

	/**
	 * (23) get String attribute PhoneticLastName
	 *
	 * @return the value of the attribute
	 */
	public String getPhoneticLastName()
	{
		return getAttribute(AttributeName.PHONETICLASTNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FirstName
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FirstName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFirstName(String value)
	{
		setAttribute(AttributeName.FIRSTNAME, value, null);
	}

	/**
	 * (23) get String attribute FirstName
	 *
	 * @return the value of the attribute
	 */
	public String getFirstName()
	{
		return getAttribute(AttributeName.FIRSTNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PhoneticFirstName
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PhoneticFirstName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPhoneticFirstName(String value)
	{
		setAttribute(AttributeName.PHONETICFIRSTNAME, value, null);
	}

	/**
	 * (23) get String attribute PhoneticFirstName
	 *
	 * @return the value of the attribute
	 */
	public String getPhoneticFirstName()
	{
		return getAttribute(AttributeName.PHONETICFIRSTNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute JobTitle
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JobTitle
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJobTitle(String value)
	{
		setAttribute(AttributeName.JOBTITLE, value, null);
	}

	/**
	 * (23) get String attribute JobTitle
	 *
	 * @return the value of the attribute
	 */
	public String getJobTitle()
	{
		return getAttribute(AttributeName.JOBTITLE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Languages
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Languages
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLanguages(VString value)
	{
		setAttribute(AttributeName.LANGUAGES, value, null);
	}

	/**
	 * (21) get VString attribute Languages
	 *
	 * @return VString the value of the attribute
	 */
	public VString getLanguages()
	{
		VString vStrAttrib = new VString();
		String s = getAttribute(AttributeName.LANGUAGES, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NamePrefix
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NamePrefix
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNamePrefix(String value)
	{
		setAttribute(AttributeName.NAMEPREFIX, value, null);
	}

	/**
	 * (23) get String attribute NamePrefix
	 *
	 * @return the value of the attribute
	 */
	public String getNamePrefix()
	{
		return getAttribute(AttributeName.NAMEPREFIX, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NameSuffix
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NameSuffix
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNameSuffix(String value)
	{
		setAttribute(AttributeName.NAMESUFFIX, value, null);
	}

	/**
	 * (23) get String attribute NameSuffix
	 *
	 * @return the value of the attribute
	 */
	public String getNameSuffix()
	{
		return getAttribute(AttributeName.NAMESUFFIX, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
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
		return (JDFAddress) getCreateElement_JDFElement(ElementName.ADDRESS, null, 0);
	}

	/**
	 * (29) append element Address
	 *
	 * @return JDFAddress the element
	 * @ if the element already exists
	 */
	public JDFAddress appendAddress()
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
	 * (24) const get element ComChannel
	 *
	 * @return JDFComChannel the element
	 */
	public JDFComChannel getComChannel()
	{
		return (JDFComChannel) getElement(ElementName.COMCHANNEL, null, 0);
	}

	/**
	 * (25) getCreateComChannel
	 * 
	 * @return JDFComChannel the element
	 */
	public JDFComChannel getCreateComChannel()
	{
		return (JDFComChannel) getCreateElement_JDFElement(ElementName.COMCHANNEL, null, 0);
	}

	/**
	 * (26) getCreateComChannel
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFComChannel the element
	 */
	public JDFComChannel getCreateComChannel(int iSkip)
	{
		return (JDFComChannel) getCreateElement_JDFElement(ElementName.COMCHANNEL, null, iSkip);
	}

	/**
	 * (27) const get element ComChannel
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFComChannel the element
	 *         default is getComChannel(0)
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
		return getChildArrayByClass(JDFComChannel.class, false, 0);
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

}
