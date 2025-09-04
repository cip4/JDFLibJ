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
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFValue;
import org.cip4.jdflib.resource.devicecapability.JDFLoc;

/**
 ***************************************************************************** class JDFAutoMatrixState : public JDFResource
 */

public abstract class JDFAutoMatrixState extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DEFAULTVALUE, 0x3333333331l, AttributeInfo.EnumAttributeType.matrix, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CURRENTVALUE, 0x3333333331l, AttributeInfo.EnumAttributeType.matrix, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ALLOWEDROTATEMOD, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ALLOWEDSHIFT, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ALLOWEDTRANSFORMS, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.PRESENTROTATEMOD, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.PRESENTSHIFT, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.PRESENTTRANSFORMS, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.LOC, 0x3333333331l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.VALUE, 0x3333333331l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoMatrixState
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoMatrixState(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoMatrixState
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoMatrixState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoMatrixState
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoMatrixState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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

	/*
	 * ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DefaultValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DefaultValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDefaultValue(JDFMatrix value)
	{
		setAttribute(AttributeName.DEFAULTVALUE, value, null);
	}

	/**
	 * (20) get JDFMatrix attribute DefaultValue
	 *
	 * @return JDFMatrix the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFMatrix
	 */
	public JDFMatrix getDefaultValue()
	{
		String strAttrName = getAttribute(AttributeName.DEFAULTVALUE, null, null);
		JDFMatrix nPlaceHolder = JDFMatrix.createMatrix(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CurrentValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CurrentValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCurrentValue(JDFMatrix value)
	{
		setAttribute(AttributeName.CURRENTVALUE, value, null);
	}

	/**
	 * (20) get JDFMatrix attribute CurrentValue
	 *
	 * @return JDFMatrix the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFMatrix
	 */
	public JDFMatrix getCurrentValue()
	{
		String strAttrName = getAttribute(AttributeName.CURRENTVALUE, null, null);
		JDFMatrix nPlaceHolder = JDFMatrix.createMatrix(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AllowedRotateMod
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedRotateMod
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedRotateMod(double value)
	{
		setAttribute(AttributeName.ALLOWEDROTATEMOD, value, null);
	}

	/**
	 * (17) get double attribute AllowedRotateMod
	 *
	 * @return double the value of the attribute
	 */
	public double getAllowedRotateMod()
	{
		return getRealAttribute(AttributeName.ALLOWEDROTATEMOD, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AllowedShift
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedShift
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedShift(String value)
	{
		setAttribute(AttributeName.ALLOWEDSHIFT, value, null);
	}

	/**
	 * (23) get String attribute AllowedShift
	 *
	 * @return the value of the attribute
	 */
	public String getAllowedShift()
	{
		return getAttribute(AttributeName.ALLOWEDSHIFT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AllowedTransforms
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedTransforms
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedTransforms(String value)
	{
		setAttribute(AttributeName.ALLOWEDTRANSFORMS, value, null);
	}

	/**
	 * (23) get String attribute AllowedTransforms
	 *
	 * @return the value of the attribute
	 */
	public String getAllowedTransforms()
	{
		return getAttribute(AttributeName.ALLOWEDTRANSFORMS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PresentRotateMod
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentRotateMod
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentRotateMod(double value)
	{
		setAttribute(AttributeName.PRESENTROTATEMOD, value, null);
	}

	/**
	 * (17) get double attribute PresentRotateMod
	 *
	 * @return double the value of the attribute
	 */
	public double getPresentRotateMod()
	{
		return getRealAttribute(AttributeName.PRESENTROTATEMOD, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PresentShift
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentShift
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentShift(String value)
	{
		setAttribute(AttributeName.PRESENTSHIFT, value, null);
	}

	/**
	 * (23) get String attribute PresentShift
	 *
	 * @return the value of the attribute
	 */
	public String getPresentShift()
	{
		return getAttribute(AttributeName.PRESENTSHIFT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PresentTransforms
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentTransforms
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentTransforms(String value)
	{
		setAttribute(AttributeName.PRESENTTRANSFORMS, value, null);
	}

	/**
	 * (23) get String attribute PresentTransforms
	 *
	 * @return the value of the attribute
	 */
	public String getPresentTransforms()
	{
		return getAttribute(AttributeName.PRESENTTRANSFORMS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element Loc
	 *
	 * @return JDFLoc the element
	 */
	public JDFLoc getLoc()
	{
		return (JDFLoc) getElement(ElementName.LOC, null, 0);
	}

	/**
	 * (25) getCreateLoc
	 * 
	 * @return JDFLoc the element
	 */
	public JDFLoc getCreateLoc()
	{
		return (JDFLoc) getCreateElement_JDFElement(ElementName.LOC, null, 0);
	}

	/**
	 * (26) getCreateLoc
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFLoc the element
	 */
	public JDFLoc getCreateLoc(int iSkip)
	{
		return (JDFLoc) getCreateElement_JDFElement(ElementName.LOC, null, iSkip);
	}

	/**
	 * (27) const get element Loc
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFLoc the element
	 *         default is getLoc(0)
	 */
	public JDFLoc getLoc(int iSkip)
	{
		return (JDFLoc) getElement(ElementName.LOC, null, iSkip);
	}

	/**
	 * Get all Loc from the current element
	 * 
	 * @return Collection<JDFLoc>, null if none are available
	 */
	public Collection<JDFLoc> getAllLoc()
	{
		return getChildArrayByClass(JDFLoc.class, false, 0);
	}

	/**
	 * (30) append element Loc
	 *
	 * @return JDFLoc the element
	 */
	public JDFLoc appendLoc()
	{
		return (JDFLoc) appendElement(ElementName.LOC, null);
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
	 * @return JDFValue the element
	 *         default is getValue(0)
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
