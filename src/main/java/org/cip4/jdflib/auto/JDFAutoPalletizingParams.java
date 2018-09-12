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
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFBundle;
import org.cip4.jdflib.resource.JDFResource;

/**
 *****************************************************************************
 * class JDFAutoPalletizingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPalletizingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.LAYERAMOUNT, 0x33331111, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.MAXHEIGHT, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.MAXWEIGHT, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.OVERHANG, 0x33331111, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.OVERHANGOFFSET, 0x33331111, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.PATTERN, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BUNDLE, 0x33333331);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPalletizingParams
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPalletizingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPalletizingParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPalletizingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPalletizingParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPalletizingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoPalletizingParams[  --> " + super.toString() + " ]";
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

	/**
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Parameter;
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute LayerAmount ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LayerAmount
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setLayerAmount(JDFIntegerList value)
	{
		setAttribute(AttributeName.LAYERAMOUNT, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute LayerAmount
	 * 
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getLayerAmount()
	{
		final String strAttrName = getAttribute(AttributeName.LAYERAMOUNT, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxHeight ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxHeight
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setMaxHeight(double value)
	{
		setAttribute(AttributeName.MAXHEIGHT, value, null);
	}

	/**
	 * (17) get double attribute MaxHeight
	 * 
	 * @return double the value of the attribute
	 */
	public double getMaxHeight()
	{
		return getRealAttribute(AttributeName.MAXHEIGHT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxWeight ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxWeight
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setMaxWeight(double value)
	{
		setAttribute(AttributeName.MAXWEIGHT, value, null);
	}

	/**
	 * (17) get double attribute MaxWeight
	 * 
	 * @return double the value of the attribute
	 */
	public double getMaxWeight()
	{
		return getRealAttribute(AttributeName.MAXWEIGHT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Overhang ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Overhang
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setOverhang(JDFXYPair value)
	{
		setAttribute(AttributeName.OVERHANG, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Overhang
	 * 
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getOverhang()
	{
		final String strAttrName = getAttribute(AttributeName.OVERHANG, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OverhangOffset ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OverhangOffset
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setOverhangOffset(JDFXYPair value)
	{
		setAttribute(AttributeName.OVERHANGOFFSET, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute OverhangOffset
	 * 
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getOverhangOffset()
	{
		final String strAttrName = getAttribute(AttributeName.OVERHANGOFFSET, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Pattern ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Pattern
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setPattern(String value)
	{
		setAttribute(AttributeName.PATTERN, value, null);
	}

	/**
	 * (23) get String attribute Pattern
	 * 
	 * @return the value of the attribute
	 */
	public String getPattern()
	{
		return getAttribute(AttributeName.PATTERN, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateBundle
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFBundle the element
	 */
	public JDFBundle getCreateBundle(int iSkip)
	{
		return (JDFBundle) getCreateElement_KElement(ElementName.BUNDLE, null, iSkip);
	}

	/**
	 * (27) const get element Bundle
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFBundle the element default is getBundle(0)
	 */
	public JDFBundle getBundle(int iSkip)
	{
		return (JDFBundle) getElement(ElementName.BUNDLE, null, iSkip);
	}

	/**
	 * Get all Bundle from the current element
	 * 
	 * @return Collection<JDFBundle>, null if none are available
	 */
	public Collection<JDFBundle> getAllBundle()
	{
		return getChildrenByClass(JDFBundle.class, false, 0);
	}

	/**
	 * (30) append element Bundle
	 * 
	 * @return JDFBundle the element
	 */
	public JDFBundle appendBundle()
	{
		return (JDFBundle) appendElement(ElementName.BUNDLE, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refBundle(JDFBundle refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
