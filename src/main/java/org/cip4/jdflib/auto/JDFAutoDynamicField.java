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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.resource.JDFDeviceMark;

/**
 *****************************************************************************
 * class JDFAutoDynamicField : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoDynamicField extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.FORMAT, 0x2222222222l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.INPUTFIELD, 0x4444444443l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ORD, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ORDEXPRESSION, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.REPLACEFIELD, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.TEMPLATE, 0x2222222222l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.DEVICEMARK, 0x6666666661l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoDynamicField
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDynamicField(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDynamicField
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDynamicField(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDynamicField
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDynamicField(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Format ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Format
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFormat(String value)
	{
		setAttribute(AttributeName.FORMAT, value, null);
	}

	/**
	 * (23) get String attribute Format
	 *
	 * @return the value of the attribute
	 */
	public String getFormat()
	{
		return getAttribute(AttributeName.FORMAT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute InputField ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute InputField
	 *
	 * @param value the value to set the attribute to
	 */
	public void setInputField(String value)
	{
		setAttribute(AttributeName.INPUTFIELD, value, null);
	}

	/**
	 * (23) get String attribute InputField
	 *
	 * @return the value of the attribute
	 */
	public String getInputField()
	{
		return getAttribute(AttributeName.INPUTFIELD, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Ord ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Ord
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOrd(int value)
	{
		setAttribute(AttributeName.ORD, value, null);
	}

	/**
	 * (15) get int attribute Ord
	 *
	 * @return int the value of the attribute
	 */
	public int getOrd()
	{
		return getIntAttribute(AttributeName.ORD, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OrdExpression
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OrdExpression
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOrdExpression(String value)
	{
		setAttribute(AttributeName.ORDEXPRESSION, value, null);
	}

	/**
	 * (23) get String attribute OrdExpression
	 *
	 * @return the value of the attribute
	 */
	public String getOrdExpression()
	{
		return getAttribute(AttributeName.ORDEXPRESSION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ReplaceField
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ReplaceField
	 *
	 * @param value the value to set the attribute to
	 */
	public void setReplaceField(String value)
	{
		setAttribute(AttributeName.REPLACEFIELD, value, null);
	}

	/**
	 * (23) get String attribute ReplaceField
	 *
	 * @return the value of the attribute
	 */
	public String getReplaceField()
	{
		return getAttribute(AttributeName.REPLACEFIELD, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Template ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Template
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTemplate(String value)
	{
		setAttribute(AttributeName.TEMPLATE, value, null);
	}

	/**
	 * (23) get String attribute Template
	 *
	 * @return the value of the attribute
	 */
	public String getTemplate()
	{
		return getAttribute(AttributeName.TEMPLATE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element DeviceMark
	 *
	 * @return JDFDeviceMark the element
	 */
	public JDFDeviceMark getDeviceMark()
	{
		return (JDFDeviceMark) getElement(ElementName.DEVICEMARK, null, 0);
	}

	/**
	 * (25) getCreateDeviceMark
	 * 
	 * @return JDFDeviceMark the element
	 */
	public JDFDeviceMark getCreateDeviceMark()
	{
		return (JDFDeviceMark) getCreateElement_JDFElement(ElementName.DEVICEMARK, null, 0);
	}

	/**
	 * (29) append element DeviceMark
	 *
	 * @return JDFDeviceMark the element @ if the element already exists
	 */
	public JDFDeviceMark appendDeviceMark()
	{
		return (JDFDeviceMark) appendElementN(ElementName.DEVICEMARK, 1, null);
	}

}
