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
import org.cip4.jdflib.resource.process.JDFCostCenter;
import org.cip4.jdflib.resource.process.JDFPerson;

/**
 *****************************************************************************
 * class JDFAutoEmployee : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoEmployee extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.PERSONALID, 0x3333333333l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ROLES, 0x3333333311l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.SHIFT, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COSTCENTER, 0x6666666666l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.PERSON, 0x6666666666l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoEmployee
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoEmployee(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoEmployee
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoEmployee(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoEmployee
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoEmployee(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		final boolean bRet = super.init();
		setResourceClass(JDFResource.EnumResourceClass.Implementation);
		return bRet;
	}

	/**
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Implementation;
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PersonalID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PersonalID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPersonalID(String value)
	{
		setAttribute(AttributeName.PERSONALID, value, null);
	}

	/**
	 * (23) get String attribute PersonalID
	 *
	 * @return the value of the attribute
	 */
	public String getPersonalID()
	{
		return getAttribute(AttributeName.PERSONALID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Roles ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Roles
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRoles(VString value)
	{
		setAttribute(AttributeName.ROLES, value, null);
	}

	/**
	 * (21) get VString attribute Roles
	 *
	 * @return VString the value of the attribute
	 */
	public VString getRoles()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.ROLES, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Shift ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Shift
	 *
	 * @param value the value to set the attribute to
	 */
	public void setShift(String value)
	{
		setAttribute(AttributeName.SHIFT, value, null);
	}

	/**
	 * (23) get String attribute Shift
	 *
	 * @return the value of the attribute
	 */
	public String getShift()
	{
		return getAttribute(AttributeName.SHIFT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element CostCenter
	 *
	 * @return JDFCostCenter the element
	 */
	public JDFCostCenter getCostCenter()
	{
		return (JDFCostCenter) getElement(ElementName.COSTCENTER, null, 0);
	}

	/**
	 * (25) getCreateCostCenter
	 * 
	 * @return JDFCostCenter the element
	 */
	public JDFCostCenter getCreateCostCenter()
	{
		return (JDFCostCenter) getCreateElement_JDFElement(ElementName.COSTCENTER, null, 0);
	}

	/**
	 * (29) append element CostCenter
	 *
	 * @return JDFCostCenter the element @ if the element already exists
	 */
	public JDFCostCenter appendCostCenter()
	{
		return (JDFCostCenter) appendElementN(ElementName.COSTCENTER, 1, null);
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
		return (JDFPerson) getCreateElement_JDFElement(ElementName.PERSON, null, 0);
	}

	/**
	 * (29) append element Person
	 *
	 * @return JDFPerson the element @ if the element already exists
	 */
	public JDFPerson appendPerson()
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

}
