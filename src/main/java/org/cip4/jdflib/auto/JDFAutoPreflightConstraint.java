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
import org.cip4.jdflib.core.JDFElement;

/**
 ***************************************************************************** class JDFAutoPreflightConstraint : public JDFElement
 */

public abstract class JDFAutoPreflightConstraint extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ATTEMPTFIXUPERRORS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ATTEMPTFIXUPWARNINGS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.CONSTRAINT, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.STATUS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumNodeStatus.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.CONSTRAINTVALUE, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPreflightConstraint
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPreflightConstraint(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPreflightConstraint
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPreflightConstraint(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPreflightConstraint
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPreflightConstraint(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AttemptFixupErrors
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AttemptFixupErrors
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAttemptFixupErrors(boolean value)
	{
		setAttribute(AttributeName.ATTEMPTFIXUPERRORS, value, null);
	}

	/**
	 * (18) get boolean attribute AttemptFixupErrors
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getAttemptFixupErrors()
	{
		return getBoolAttribute(AttributeName.ATTEMPTFIXUPERRORS, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AttemptFixupWarnings
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AttemptFixupWarnings
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAttemptFixupWarnings(boolean value)
	{
		setAttribute(AttributeName.ATTEMPTFIXUPWARNINGS, value, null);
	}

	/**
	 * (18) get boolean attribute AttemptFixupWarnings
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getAttemptFixupWarnings()
	{
		return getBoolAttribute(AttributeName.ATTEMPTFIXUPWARNINGS, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Constraint
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Constraint
	 *
	 * @param value the value to set the attribute to
	 */
	public void setConstraint(String value)
	{
		setAttribute(AttributeName.CONSTRAINT, value, null);
	}

	/**
	 * (23) get String attribute Constraint
	 *
	 * @return the value of the attribute
	 */
	public String getConstraint()
	{
		return getAttribute(AttributeName.CONSTRAINT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element ConstraintValue
	 *
	 * @return JDFElement the element
	 */
	public JDFElement getConstraintValue()
	{
		return (JDFElement) getElement(ElementName.CONSTRAINTVALUE, null, 0);
	}

	/**
	 * (25) getCreateConstraintValue
	 * 
	 * @return JDFElement the element
	 */
	public JDFElement getCreateConstraintValue()
	{
		return (JDFElement) getCreateElement_JDFElement(ElementName.CONSTRAINTVALUE, null, 0);
	}

	/**
	 * (26) getCreateConstraintValue
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFElement the element
	 */
	public JDFElement getCreateConstraintValue(int iSkip)
	{
		return (JDFElement) getCreateElement_JDFElement(ElementName.CONSTRAINTVALUE, null, iSkip);
	}

	/**
	 * (27) const get element ConstraintValue
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFElement the element
	 *         default is getConstraintValue(0)
	 */
	public JDFElement getConstraintValue(int iSkip)
	{
		return (JDFElement) getElement(ElementName.CONSTRAINTVALUE, null, iSkip);
	}

	/**
	 * Get all ConstraintValue from the current element
	 * 
	 * @return Collection<JDFElement>, null if none are available
	 */
	public Collection<JDFElement> getAllConstraintValue()
	{
		return getChildArrayByClass(JDFElement.class, false, 0);
	}

	/**
	 * (30) append element ConstraintValue
	 *
	 * @return JDFElement the element
	 */
	public JDFElement appendConstraintValue()
	{
		return (JDFElement) appendElement(ElementName.CONSTRAINTVALUE, null);
	}

}
