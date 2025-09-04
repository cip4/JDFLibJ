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
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.resource.process.JDFComponent;

/**
 ***************************************************************************** class JDFAutoBundleItem : public JDFElement
 */

public abstract class JDFAutoBundleItem extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.AMOUNT, 0x2222222221l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ITEMNAME, 0x3333333311l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ORIENTATION, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumOrientation.getEnum(0),
				null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.TRANSFORMATION, 0x3333333331l, AttributeInfo.EnumAttributeType.matrix, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COMPONENT, 0x5555555551l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoBundleItem
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoBundleItem(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBundleItem
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoBundleItem(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBundleItem
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoBundleItem(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Methods for Attribute Amount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Amount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAmount(int value)
	{
		setAttribute(AttributeName.AMOUNT, value, null);
	}

	/**
	 * (15) get int attribute Amount
	 *
	 * @return int the value of the attribute
	 */
	public int getAmount()
	{
		return getIntAttribute(AttributeName.AMOUNT, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ItemName
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ItemName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setItemName(String value)
	{
		setAttribute(AttributeName.ITEMNAME, value, null);
	}

	/**
	 * (23) get String attribute ItemName
	 *
	 * @return the value of the attribute
	 */
	public String getItemName()
	{
		return getAttribute(AttributeName.ITEMNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Orientation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Orientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOrientation(EOrientation enumVar)
	{
		setAttribute(AttributeName.ORIENTATION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Orientation
	 *
	 * @return the value of the attribute
	 */
	public EOrientation getEOrientation()
	{
		return EOrientation.getEnum(getAttribute(AttributeName.ORIENTATION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Orientation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Orientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetOrientation(EOrientation) based on java.lang.enum instead
	 */
	@Deprecated
	public void setOrientation(EnumOrientation enumVar)
	{
		setAttribute(AttributeName.ORIENTATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Orientation
	 *
	 * @return the value of the attribute
	 * @deprecated use EOrientation GetEOrientation() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumOrientation getOrientation()
	{
		return EnumOrientation.getEnum(getAttribute(AttributeName.ORIENTATION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Transformation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Transformation
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTransformation(JDFMatrix value)
	{
		setAttribute(AttributeName.TRANSFORMATION, value, null);
	}

	/**
	 * (20) get JDFMatrix attribute Transformation
	 *
	 * @return JDFMatrix the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFMatrix
	 */
	public JDFMatrix getTransformation()
	{
		String strAttrName = getAttribute(AttributeName.TRANSFORMATION, null, null);
		JDFMatrix nPlaceHolder = JDFMatrix.createMatrix(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element Component
	 *
	 * @return JDFComponent the element
	 */
	public JDFComponent getComponent()
	{
		return (JDFComponent) getElement(ElementName.COMPONENT, null, 0);
	}

	/**
	 * (25) getCreateComponent
	 * 
	 * @return JDFComponent the element
	 */
	public JDFComponent getCreateComponent()
	{
		return (JDFComponent) getCreateElement_JDFElement(ElementName.COMPONENT, null, 0);
	}

	/**
	 * (29) append element Component
	 *
	 * @return JDFComponent the element
	 * @ if the element already exists
	 */
	public JDFComponent appendComponent()
	{
		return (JDFComponent) appendElementN(ElementName.COMPONENT, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refComponent(JDFComponent refTarget)
	{
		refElement(refTarget);
	}

}
