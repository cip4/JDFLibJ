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
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFValue;
import org.cip4.jdflib.resource.devicecapability.JDFBasicPreflightTest;

/**
 *****************************************************************************
 * class JDFAutoStringEvaluation : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoStringEvaluation extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.LENGTHJDF, 0x3333333333l, AttributeInfo.EnumAttributeType.IntegerRange, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.REGEXP, 0x3333333333l, AttributeInfo.EnumAttributeType.Any, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BASICPREFLIGHTTEST, 0x3333333333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.VALUE, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoStringEvaluation
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoStringEvaluation(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoStringEvaluation
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoStringEvaluation(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoStringEvaluation
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoStringEvaluation(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
		setResourceClass(JDFResource.EnumResourceClass.Parameter);
		return bRet;
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute LengthJDF ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LengthJDF
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLengthJDF(JDFIntegerRange value)
	{
		setAttribute(AttributeName.LENGTHJDF, value, null);
	}

	/**
	 * (20) get JDFIntegerRange attribute LengthJDF
	 *
	 * @return JDFIntegerRange the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerRange
	 */
	public JDFIntegerRange getLengthJDF()
	{
		final String strAttrName = getAttribute(AttributeName.LENGTHJDF, null, null);
		final JDFIntegerRange nPlaceHolder = JDFIntegerRange.createIntegerRange(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RegExp ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RegExp
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRegExp(String value)
	{
		setAttribute(AttributeName.REGEXP, value, null);
	}

	/**
	 * (23) get String attribute RegExp
	 *
	 * @return the value of the attribute
	 */
	public String getRegExp()
	{
		return getAttribute(AttributeName.REGEXP, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element BasicPreflightTest
	 *
	 * @return JDFBasicPreflightTest the element
	 */
	public JDFBasicPreflightTest getBasicPreflightTest()
	{
		return (JDFBasicPreflightTest) getElement(ElementName.BASICPREFLIGHTTEST, null, 0);
	}

	/**
	 * (25) getCreateBasicPreflightTest
	 * 
	 * @return JDFBasicPreflightTest the element
	 */
	public JDFBasicPreflightTest getCreateBasicPreflightTest()
	{
		return (JDFBasicPreflightTest) getCreateElement_JDFElement(ElementName.BASICPREFLIGHTTEST, null, 0);
	}

	/**
	 * (26) getCreateBasicPreflightTest
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFBasicPreflightTest the element
	 */
	public JDFBasicPreflightTest getCreateBasicPreflightTest(int iSkip)
	{
		return (JDFBasicPreflightTest) getCreateElement_JDFElement(ElementName.BASICPREFLIGHTTEST, null, iSkip);
	}

	/**
	 * (27) const get element BasicPreflightTest
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFBasicPreflightTest the element default is getBasicPreflightTest(0)
	 */
	public JDFBasicPreflightTest getBasicPreflightTest(int iSkip)
	{
		return (JDFBasicPreflightTest) getElement(ElementName.BASICPREFLIGHTTEST, null, iSkip);
	}

	/**
	 * Get all BasicPreflightTest from the current element
	 * 
	 * @return Collection<JDFBasicPreflightTest>, null if none are available
	 */
	public Collection<JDFBasicPreflightTest> getAllBasicPreflightTest()
	{
		return getChildArrayByClass(JDFBasicPreflightTest.class, false, 0);
	}

	/**
	 * (30) append element BasicPreflightTest
	 *
	 * @return JDFBasicPreflightTest the element
	 */
	public JDFBasicPreflightTest appendBasicPreflightTest()
	{
		return (JDFBasicPreflightTest) appendElement(ElementName.BASICPREFLIGHTTEST, null);
	}

	/**
	 * (24) const get element Value
	 *
	 * @return JDFValue the element
	 */
	public JDFValue getValue()
	{
		return (JDFValue) getElement(ElementName.VALUE, null, 0);
	}

	/**
	 * (25) getCreateValue
	 * 
	 * @return JDFValue the element
	 */
	public JDFValue getCreateValue()
	{
		return (JDFValue) getCreateElement_JDFElement(ElementName.VALUE, null, 0);
	}

	/**
	 * (26) getCreateValue
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFValue the element
	 */
	public JDFValue getCreateValue(int iSkip)
	{
		return (JDFValue) getCreateElement_JDFElement(ElementName.VALUE, null, iSkip);
	}

	/**
	 * (27) const get element Value
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFValue the element default is getValue(0)
	 */
	public JDFValue getValue(int iSkip)
	{
		return (JDFValue) getElement(ElementName.VALUE, null, iSkip);
	}

	/**
	 * Get all Value from the current element
	 * 
	 * @return Collection<JDFValue>, null if none are available
	 */
	public Collection<JDFValue> getAllValue()
	{
		return getChildArrayByClass(JDFValue.class, false, 0);
	}

	/**
	 * (30) append element Value
	 *
	 * @return JDFValue the element
	 */
	public JDFValue appendValue()
	{
		return (JDFValue) appendElement(ElementName.VALUE, null);
	}

}
