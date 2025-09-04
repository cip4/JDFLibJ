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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFNumberList;

/**
 ***************************************************************************** class JDFAutoNumberingParam : public JDFElement
 */

public abstract class JDFAutoNumberingParam extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.STARTVALUE, 0x4444433333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.XPOSITION, 0x4444433333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.YPOSITION, 0x4444433333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ORIENTATION, 0x4444433333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.STEP, 0x4444433333l, AttributeInfo.EnumAttributeType.integer, null, "1");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoNumberingParam
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoNumberingParam(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoNumberingParam
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoNumberingParam(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoNumberingParam
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoNumberingParam(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Methods for Attribute StartValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StartValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStartValue(String value)
	{
		setAttribute(AttributeName.STARTVALUE, value, null);
	}

	/**
	 * (23) get String attribute StartValue
	 *
	 * @return the value of the attribute
	 */
	public String getStartValue()
	{
		return getAttribute(AttributeName.STARTVALUE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute XPosition
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute XPosition
	 *
	 * @param value the value to set the attribute to
	 */
	public void setXPosition(double value)
	{
		setAttribute(AttributeName.XPOSITION, value, null);
	}

	/**
	 * (17) get double attribute XPosition
	 *
	 * @return double the value of the attribute
	 */
	public double getXPosition()
	{
		return getRealAttribute(AttributeName.XPOSITION, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute YPosition
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute YPosition
	 *
	 * @param value the value to set the attribute to
	 */
	public void setYPosition(JDFNumberList value)
	{
		setAttribute(AttributeName.YPOSITION, value, null);
	}

	/**
	 * (20) get JDFNumberList attribute YPosition
	 *
	 * @return JDFNumberList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFNumberList
	 */
	public JDFNumberList getYPosition()
	{
		String strAttrName = getAttribute(AttributeName.YPOSITION, null, null);
		JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Orientation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Orientation
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOrientation(double value)
	{
		setAttribute(AttributeName.ORIENTATION, value, null);
	}

	/**
	 * (17) get double attribute Orientation
	 *
	 * @return double the value of the attribute
	 */
	public double getOrientation()
	{
		return getRealAttribute(AttributeName.ORIENTATION, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Step
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Step
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStep(int value)
	{
		setAttribute(AttributeName.STEP, value, null);
	}

	/**
	 * (15) get int attribute Step
	 *
	 * @return int the value of the attribute
	 */
	public int getStep()
	{
		return getIntAttribute(AttributeName.STEP, null, 1);
	}

}
