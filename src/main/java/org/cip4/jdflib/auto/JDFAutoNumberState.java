/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
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
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.devicecapability.JDFLoc;
import org.cip4.jdflib.resource.devicecapability.JDFValueLoc;

/**
 *****************************************************************************
 * class JDFAutoNumberState : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoNumberState extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DEFAULTVALUE, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CURRENTVALUE, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ALLOWEDVALUELIST, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ALLOWEDVALUEMAX, 0x44444431, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ALLOWEDVALUEMIN, 0x44444431, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.ALLOWEDVALUEMOD, 0x33333311, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.PRESENTVALUELIST, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.PRESENTVALUEMAX, 0x44444431, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.PRESENTVALUEMIN, 0x44444431, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.PRESENTVALUEMOD, 0x33333311, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.UNITTYPE, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.LOC, 0x33333331);
		elemInfoTable[1] = new ElemInfoTable(ElementName.VALUELOC, 0x33333331);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoNumberState
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoNumberState(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoNumberState
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoNumberState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoNumberState
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoNumberState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoNumberState[  --> " + super.toString() + " ]";
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
	 * --------------------------------------------------------------------- Methods for Attribute DefaultValue ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DefaultValue
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setDefaultValue(double value)
	{
		setAttribute(AttributeName.DEFAULTVALUE, value, null);
	}

	/**
	 * (17) get double attribute DefaultValue
	 * 
	 * @return double the value of the attribute
	 */
	public double getDefaultValue()
	{
		return getRealAttribute(AttributeName.DEFAULTVALUE, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CurrentValue ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CurrentValue
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setCurrentValue(double value)
	{
		setAttribute(AttributeName.CURRENTVALUE, value, null);
	}

	/**
	 * (17) get double attribute CurrentValue
	 * 
	 * @return double the value of the attribute
	 */
	public double getCurrentValue()
	{
		return getRealAttribute(AttributeName.CURRENTVALUE, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AllowedValueList ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedValueList
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setAllowedValueList(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.ALLOWEDVALUELIST, value, null);
	}

	/**
	 * (20) get JDFNumberRangeList attribute AllowedValueList
	 * 
	 * @return JDFNumberRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberRangeList
	 */
	public JDFNumberRangeList getAllowedValueList()
	{
		final String strAttrName = getAttribute(AttributeName.ALLOWEDVALUELIST, null, null);
		final JDFNumberRangeList nPlaceHolder = JDFNumberRangeList.createNumberRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AllowedValueMax ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedValueMax
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setAllowedValueMax(double value)
	{
		setAttribute(AttributeName.ALLOWEDVALUEMAX, value, null);
	}

	/**
	 * (17) get double attribute AllowedValueMax
	 * 
	 * @return double the value of the attribute
	 */
	public double getAllowedValueMax()
	{
		return getRealAttribute(AttributeName.ALLOWEDVALUEMAX, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AllowedValueMin ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedValueMin
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setAllowedValueMin(double value)
	{
		setAttribute(AttributeName.ALLOWEDVALUEMIN, value, null);
	}

	/**
	 * (17) get double attribute AllowedValueMin
	 * 
	 * @return double the value of the attribute
	 */
	public double getAllowedValueMin()
	{
		return getRealAttribute(AttributeName.ALLOWEDVALUEMIN, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AllowedValueMod ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedValueMod
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setAllowedValueMod(JDFXYPair value)
	{
		setAttribute(AttributeName.ALLOWEDVALUEMOD, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute AllowedValueMod
	 * 
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getAllowedValueMod()
	{
		final String strAttrName = getAttribute(AttributeName.ALLOWEDVALUEMOD, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PresentValueList ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentValueList
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setPresentValueList(JDFNumberRangeList value)
	{
		setAttribute(AttributeName.PRESENTVALUELIST, value, null);
	}

	/**
	 * (20) get JDFNumberRangeList attribute PresentValueList
	 * 
	 * @return JDFNumberRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberRangeList
	 */
	public JDFNumberRangeList getPresentValueList()
	{
		final String strAttrName = getAttribute(AttributeName.PRESENTVALUELIST, null, null);
		final JDFNumberRangeList nPlaceHolder = JDFNumberRangeList.createNumberRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PresentValueMax ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentValueMax
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setPresentValueMax(double value)
	{
		setAttribute(AttributeName.PRESENTVALUEMAX, value, null);
	}

	/**
	 * (17) get double attribute PresentValueMax
	 * 
	 * @return double the value of the attribute
	 */
	public double getPresentValueMax()
	{
		return getRealAttribute(AttributeName.PRESENTVALUEMAX, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PresentValueMin ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentValueMin
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setPresentValueMin(double value)
	{
		setAttribute(AttributeName.PRESENTVALUEMIN, value, null);
	}

	/**
	 * (17) get double attribute PresentValueMin
	 * 
	 * @return double the value of the attribute
	 */
	public double getPresentValueMin()
	{
		return getRealAttribute(AttributeName.PRESENTVALUEMIN, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PresentValueMod ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentValueMod
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setPresentValueMod(JDFXYPair value)
	{
		setAttribute(AttributeName.PRESENTVALUEMOD, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute PresentValueMod
	 * 
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getPresentValueMod()
	{
		final String strAttrName = getAttribute(AttributeName.PRESENTVALUEMOD, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute UnitType ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute UnitType
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setUnitType(String value)
	{
		setAttribute(AttributeName.UNITTYPE, value, null);
	}

	/**
	 * (23) get String attribute UnitType
	 * 
	 * @return the value of the attribute
	 */
	public String getUnitType()
	{
		return getAttribute(AttributeName.UNITTYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateLoc
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFLoc the element
	 */
	public JDFLoc getCreateLoc(int iSkip)
	{
		return (JDFLoc) getCreateElement(ElementName.LOC, null, iSkip);
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
		return getChildrenByClass(JDFLoc.class, false, 0);
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
	 * (26) getCreateValueLoc
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFValueLoc the element
	 */
	public JDFValueLoc getCreateValueLoc(int iSkip)
	{
		return (JDFValueLoc) getCreateElement(ElementName.VALUELOC, null, iSkip);
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
		return getChildrenByClass(JDFValueLoc.class, false, 0);
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

}// end namespace JDF
