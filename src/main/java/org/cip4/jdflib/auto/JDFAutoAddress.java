/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFElement;

/**
 *****************************************************************************
 * class JDFAutoAddress : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoAddress extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ADDRESSUSAGE, 0x3333333333l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CITY, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.CIVICNUMBER, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.COUNTRY, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.COUNTRYCODE, 0x3333333333l, AttributeInfo.EnumAttributeType.Any, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.POSTBOX, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.POSTALCODE, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.REGION, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.STREET, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.ADDRESSLINE, 0x3333333333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.EXTENDEDADDRESS, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoAddress
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoAddress(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAddress
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoAddress(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAddress
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoAddress(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AddressUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AddressUsage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAddressUsage(String value)
	{
		setAttribute(AttributeName.ADDRESSUSAGE, value, null);
	}

	/**
	 * (23) get String attribute AddressUsage
	 *
	 * @return the value of the attribute
	 */
	public String getAddressUsage()
	{
		return getAttribute(AttributeName.ADDRESSUSAGE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute City ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute City
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCity(String value)
	{
		setAttribute(AttributeName.CITY, value, null);
	}

	/**
	 * (23) get String attribute City
	 *
	 * @return the value of the attribute
	 */
	public String getCity()
	{
		return getAttribute(AttributeName.CITY, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CivicNumber ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CivicNumber
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCivicNumber(String value)
	{
		setAttribute(AttributeName.CIVICNUMBER, value, null);
	}

	/**
	 * (23) get String attribute CivicNumber
	 *
	 * @return the value of the attribute
	 */
	public String getCivicNumber()
	{
		return getAttribute(AttributeName.CIVICNUMBER, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Country ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Country
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCountry(String value)
	{
		setAttribute(AttributeName.COUNTRY, value, null);
	}

	/**
	 * (23) get String attribute Country
	 *
	 * @return the value of the attribute
	 */
	public String getCountry()
	{
		return getAttribute(AttributeName.COUNTRY, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CountryCode ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CountryCode
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCountryCode(String value)
	{
		setAttribute(AttributeName.COUNTRYCODE, value, null);
	}

	/**
	 * (23) get String attribute CountryCode
	 *
	 * @return the value of the attribute
	 */
	public String getCountryCode()
	{
		return getAttribute(AttributeName.COUNTRYCODE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PostBox ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PostBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPostBox(String value)
	{
		setAttribute(AttributeName.POSTBOX, value, null);
	}

	/**
	 * (23) get String attribute PostBox
	 *
	 * @return the value of the attribute
	 */
	public String getPostBox()
	{
		return getAttribute(AttributeName.POSTBOX, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PostalCode ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PostalCode
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPostalCode(String value)
	{
		setAttribute(AttributeName.POSTALCODE, value, null);
	}

	/**
	 * (23) get String attribute PostalCode
	 *
	 * @return the value of the attribute
	 */
	public String getPostalCode()
	{
		return getAttribute(AttributeName.POSTALCODE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Region ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Region
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRegion(String value)
	{
		setAttribute(AttributeName.REGION, value, null);
	}

	/**
	 * (23) get String attribute Region
	 *
	 * @return the value of the attribute
	 */
	public String getRegion()
	{
		return getAttribute(AttributeName.REGION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Street ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Street
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStreet(String value)
	{
		setAttribute(AttributeName.STREET, value, null);
	}

	/**
	 * (23) get String attribute Street
	 *
	 * @return the value of the attribute
	 */
	public String getStreet()
	{
		return getAttribute(AttributeName.STREET, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element AddressLine
	 *
	 * @return JDFElement the element
	 */
	public JDFElement getAddressLine()
	{
		return (JDFElement) getElement(ElementName.ADDRESSLINE, null, 0);
	}

	/**
	 * (25) getCreateAddressLine
	 * 
	 * @return JDFElement the element
	 */
	public JDFElement getCreateAddressLine()
	{
		return (JDFElement) getCreateElement_JDFElement(ElementName.ADDRESSLINE, null, 0);
	}

	/**
	 * (26) getCreateAddressLine
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFElement the element
	 */
	public JDFElement getCreateAddressLine(int iSkip)
	{
		return (JDFElement) getCreateElement_JDFElement(ElementName.ADDRESSLINE, null, iSkip);
	}

	/**
	 * (27) const get element AddressLine
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFElement the element default is getAddressLine(0)
	 */
	public JDFElement getAddressLine(int iSkip)
	{
		return (JDFElement) getElement(ElementName.ADDRESSLINE, null, iSkip);
	}

	/**
	 * Get all AddressLine from the current element
	 * 
	 * @return Collection<JDFElement>, null if none are available
	 */
	public Collection<JDFElement> getAllAddressLine()
	{
		return getChildArrayByClass(JDFElement.class, false, 0);
	}

	/**
	 * (30) append element AddressLine
	 *
	 * @return JDFElement the element
	 */
	public JDFElement appendAddressLine()
	{
		return (JDFElement) appendElement(ElementName.ADDRESSLINE, null);
	}

	/**
	 * (24) const get element ExtendedAddress
	 *
	 * @return JDFElement the element
	 */
	public JDFElement getExtendedAddress()
	{
		return (JDFElement) getElement(ElementName.EXTENDEDADDRESS, null, 0);
	}

	/**
	 * (25) getCreateExtendedAddress
	 * 
	 * @return JDFElement the element
	 */
	public JDFElement getCreateExtendedAddress()
	{
		return (JDFElement) getCreateElement_JDFElement(ElementName.EXTENDEDADDRESS, null, 0);
	}

	/**
	 * (26) getCreateExtendedAddress
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFElement the element
	 */
	public JDFElement getCreateExtendedAddress(int iSkip)
	{
		return (JDFElement) getCreateElement_JDFElement(ElementName.EXTENDEDADDRESS, null, iSkip);
	}

	/**
	 * (27) const get element ExtendedAddress
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFElement the element default is getExtendedAddress(0)
	 */
	public JDFElement getExtendedAddress(int iSkip)
	{
		return (JDFElement) getElement(ElementName.EXTENDEDADDRESS, null, iSkip);
	}

	/**
	 * Get all ExtendedAddress from the current element
	 * 
	 * @return Collection<JDFElement>, null if none are available
	 */
	public Collection<JDFElement> getAllExtendedAddress()
	{
		return getChildArrayByClass(JDFElement.class, false, 0);
	}

	/**
	 * (30) append element ExtendedAddress
	 *
	 * @return JDFElement the element
	 */
	public JDFElement appendExtendedAddress()
	{
		return (JDFElement) appendElement(ElementName.EXTENDEDADDRESS, null);
	}

}
