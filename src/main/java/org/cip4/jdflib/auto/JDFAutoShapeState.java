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
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.datatypes.JDFShapeRangeList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.devicecapability.JDFLoc;
import org.cip4.jdflib.resource.devicecapability.JDFValueLoc;

/**
 *****************************************************************************
 * class JDFAutoShapeState : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoShapeState extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[14];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DEFAULTVALUE, 0x3333333331l, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CURRENTVALUE, 0x3333333331l, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ALLOWEDVALUELIST, 0x3333333331l, AttributeInfo.EnumAttributeType.ShapeRangeList, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ALLOWEDVALUEMAX, 0x4444444431l, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ALLOWEDVALUEMIN, 0x4444444431l, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.ALLOWEDX, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.ALLOWEDY, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.ALLOWEDZ, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.PRESENTVALUELIST, 0x3333333331l, AttributeInfo.EnumAttributeType.ShapeRangeList, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.PRESENTVALUEMAX, 0x4444444431l, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.PRESENTVALUEMIN, 0x4444444431l, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.PRESENTX, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.PRESENTY, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.PRESENTZ, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
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
		elemInfoTable[1] = new ElemInfoTable(ElementName.VALUELOC, 0x3333333331l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoShapeState
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoShapeState(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoShapeState
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoShapeState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoShapeState
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoShapeState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * --------------------------------------------------------------------- Methods for Attribute DefaultValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DefaultValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDefaultValue(JDFShape value)
	{
		setAttribute(AttributeName.DEFAULTVALUE, value, null);
	}

	/**
	 * (20) get JDFShape attribute DefaultValue
	 *
	 * @return JDFShape the value of the attribute, null if a the attribute value is not a valid to create a JDFShape
	 */
	public JDFShape getDefaultValue()
	{
		final String strAttrName = getAttribute(AttributeName.DEFAULTVALUE, null, null);
		final JDFShape nPlaceHolder = JDFShape.createShape(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CurrentValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CurrentValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCurrentValue(JDFShape value)
	{
		setAttribute(AttributeName.CURRENTVALUE, value, null);
	}

	/**
	 * (20) get JDFShape attribute CurrentValue
	 *
	 * @return JDFShape the value of the attribute, null if a the attribute value is not a valid to create a JDFShape
	 */
	public JDFShape getCurrentValue()
	{
		final String strAttrName = getAttribute(AttributeName.CURRENTVALUE, null, null);
		final JDFShape nPlaceHolder = JDFShape.createShape(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AllowedValueList
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedValueList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedValueList(JDFShapeRangeList value)
	{
		setAttribute(AttributeName.ALLOWEDVALUELIST, value, null);
	}

	/**
	 * (20) get JDFShapeRangeList attribute AllowedValueList
	 *
	 * @return JDFShapeRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFShapeRangeList
	 */
	public JDFShapeRangeList getAllowedValueList()
	{
		final String strAttrName = getAttribute(AttributeName.ALLOWEDVALUELIST, null, null);
		final JDFShapeRangeList nPlaceHolder = JDFShapeRangeList.createShapeRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AllowedValueMax
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedValueMax
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedValueMax(JDFShape value)
	{
		setAttribute(AttributeName.ALLOWEDVALUEMAX, value, null);
	}

	/**
	 * (20) get JDFShape attribute AllowedValueMax
	 *
	 * @return JDFShape the value of the attribute, null if a the attribute value is not a valid to create a JDFShape
	 */
	public JDFShape getAllowedValueMax()
	{
		final String strAttrName = getAttribute(AttributeName.ALLOWEDVALUEMAX, null, null);
		final JDFShape nPlaceHolder = JDFShape.createShape(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AllowedValueMin
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedValueMin
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedValueMin(JDFShape value)
	{
		setAttribute(AttributeName.ALLOWEDVALUEMIN, value, null);
	}

	/**
	 * (20) get JDFShape attribute AllowedValueMin
	 *
	 * @return JDFShape the value of the attribute, null if a the attribute value is not a valid to create a JDFShape
	 */
	public JDFShape getAllowedValueMin()
	{
		final String strAttrName = getAttribute(AttributeName.ALLOWEDVALUEMIN, null, null);
		final JDFShape nPlaceHolder = JDFShape.createShape(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AllowedX ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedX
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedX(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.ALLOWEDX, value, null);
	}

	/**
	 * (20) get JDFNumberRangeList attribute AllowedX
	 *
	 * @return JDFNumberRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberRangeList
	 */
	public JDFNumberRangeList getAllowedX()
	{
		final String strAttrName = getAttribute(AttributeName.ALLOWEDX, null, null);
		final JDFNumberRangeList nPlaceHolder = JDFNumberRangeList.createNumberRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AllowedY ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedY
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedY(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.ALLOWEDY, value, null);
	}

	/**
	 * (20) get JDFNumberRangeList attribute AllowedY
	 *
	 * @return JDFNumberRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberRangeList
	 */
	public JDFNumberRangeList getAllowedY()
	{
		final String strAttrName = getAttribute(AttributeName.ALLOWEDY, null, null);
		final JDFNumberRangeList nPlaceHolder = JDFNumberRangeList.createNumberRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AllowedZ ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedZ
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedZ(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.ALLOWEDZ, value, null);
	}

	/**
	 * (20) get JDFNumberRangeList attribute AllowedZ
	 *
	 * @return JDFNumberRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberRangeList
	 */
	public JDFNumberRangeList getAllowedZ()
	{
		final String strAttrName = getAttribute(AttributeName.ALLOWEDZ, null, null);
		final JDFNumberRangeList nPlaceHolder = JDFNumberRangeList.createNumberRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PresentValueList
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentValueList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentValueList(JDFShapeRangeList value)
	{
		setAttribute(AttributeName.PRESENTVALUELIST, value, null);
	}

	/**
	 * (20) get JDFShapeRangeList attribute PresentValueList
	 *
	 * @return JDFShapeRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFShapeRangeList
	 */
	public JDFShapeRangeList getPresentValueList()
	{
		final String strAttrName = getAttribute(AttributeName.PRESENTVALUELIST, null, null);
		final JDFShapeRangeList nPlaceHolder = JDFShapeRangeList.createShapeRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PresentValueMax
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentValueMax
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentValueMax(JDFShape value)
	{
		setAttribute(AttributeName.PRESENTVALUEMAX, value, null);
	}

	/**
	 * (20) get JDFShape attribute PresentValueMax
	 *
	 * @return JDFShape the value of the attribute, null if a the attribute value is not a valid to create a JDFShape
	 */
	public JDFShape getPresentValueMax()
	{
		final String strAttrName = getAttribute(AttributeName.PRESENTVALUEMAX, null, null);
		final JDFShape nPlaceHolder = JDFShape.createShape(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PresentValueMin
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentValueMin
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentValueMin(JDFShape value)
	{
		setAttribute(AttributeName.PRESENTVALUEMIN, value, null);
	}

	/**
	 * (20) get JDFShape attribute PresentValueMin
	 *
	 * @return JDFShape the value of the attribute, null if a the attribute value is not a valid to create a JDFShape
	 */
	public JDFShape getPresentValueMin()
	{
		final String strAttrName = getAttribute(AttributeName.PRESENTVALUEMIN, null, null);
		final JDFShape nPlaceHolder = JDFShape.createShape(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PresentX ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentX
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentX(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.PRESENTX, value, null);
	}

	/**
	 * (20) get JDFNumberRangeList attribute PresentX
	 *
	 * @return JDFNumberRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberRangeList
	 */
	public JDFNumberRangeList getPresentX()
	{
		final String strAttrName = getAttribute(AttributeName.PRESENTX, null, null);
		final JDFNumberRangeList nPlaceHolder = JDFNumberRangeList.createNumberRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PresentY ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentY
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentY(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.PRESENTY, value, null);
	}

	/**
	 * (20) get JDFNumberRangeList attribute PresentY
	 *
	 * @return JDFNumberRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberRangeList
	 */
	public JDFNumberRangeList getPresentY()
	{
		final String strAttrName = getAttribute(AttributeName.PRESENTY, null, null);
		final JDFNumberRangeList nPlaceHolder = JDFNumberRangeList.createNumberRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PresentZ ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentZ
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentZ(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.PRESENTZ, value, null);
	}

	/**
	 * (20) get JDFNumberRangeList attribute PresentZ
	 *
	 * @return JDFNumberRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberRangeList
	 */
	public JDFNumberRangeList getPresentZ()
	{
		final String strAttrName = getAttribute(AttributeName.PRESENTZ, null, null);
		final JDFNumberRangeList nPlaceHolder = JDFNumberRangeList.createNumberRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
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
	 * @return JDFLoc the element default is getLoc(0)
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
	 * (24) const get element ValueLoc
	 *
	 * @return JDFValueLoc the element
	 */
	public JDFValueLoc getValueLoc()
	{
		return (JDFValueLoc) getElement(ElementName.VALUELOC, null, 0);
	}

	/**
	 * (25) getCreateValueLoc
	 * 
	 * @return JDFValueLoc the element
	 */
	public JDFValueLoc getCreateValueLoc()
	{
		return (JDFValueLoc) getCreateElement_JDFElement(ElementName.VALUELOC, null, 0);
	}

	/**
	 * (26) getCreateValueLoc
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFValueLoc the element
	 */
	public JDFValueLoc getCreateValueLoc(int iSkip)
	{
		return (JDFValueLoc) getCreateElement_JDFElement(ElementName.VALUELOC, null, iSkip);
	}

	/**
	 * (27) const get element ValueLoc
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFValueLoc the element default is getValueLoc(0)
	 */
	public JDFValueLoc getValueLoc(int iSkip)
	{
		return (JDFValueLoc) getElement(ElementName.VALUELOC, null, iSkip);
	}

	/**
	 * Get all ValueLoc from the current element
	 * 
	 * @return Collection<JDFValueLoc>, null if none are available
	 */
	public Collection<JDFValueLoc> getAllValueLoc()
	{
		return getChildArrayByClass(JDFValueLoc.class, false, 0);
	}

	/**
	 * (30) append element ValueLoc
	 *
	 * @return JDFValueLoc the element
	 */
	public JDFValueLoc appendValueLoc()
	{
		return (JDFValueLoc) appendElement(ElementName.VALUELOC, null);
	}

}
