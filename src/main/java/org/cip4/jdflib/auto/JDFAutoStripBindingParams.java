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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.postpress.JDFHoleMakingParams;

/**
 *****************************************************************************
 * class JDFAutoStripBindingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoStripBindingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BRAND, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DISTANCE, 0x44444431, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.LENGTHJDF, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.STRIPCOLOR, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.STRIPCOLORDETAILS, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.HOLEMAKINGPARAMS, 0x66666661);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoStripBindingParams
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoStripBindingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoStripBindingParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoStripBindingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoStripBindingParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoStripBindingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoStripBindingParams[  --> " + super.toString() + " ]";
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
	 * --------------------------------------------------------------------- Methods for Attribute Brand ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Brand
	 * 
	 * @param value the value to set the attribute to
	 */
	@Override
	public void setBrand(String value)
	{
		setAttribute(AttributeName.BRAND, value, null);
	}

	/**
	 * (23) get String attribute Brand
	 * 
	 * @return the value of the attribute
	 */
	@Override
	public String getBrand()
	{
		return getAttribute(AttributeName.BRAND, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Distance ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Distance
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setDistance(double value)
	{
		setAttribute(AttributeName.DISTANCE, value, null);
	}

	/**
	 * (17) get double attribute Distance
	 * 
	 * @return double the value of the attribute
	 */
	public double getDistance()
	{
		return getRealAttribute(AttributeName.DISTANCE, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute LengthJDF ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LengthJDF
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setLengthJDF(double value)
	{
		setAttribute(AttributeName.LENGTHJDF, value, null);
	}

	/**
	 * (17) get double attribute LengthJDF
	 * 
	 * @return double the value of the attribute
	 */
	public double getLengthJDF()
	{
		return getRealAttribute(AttributeName.LENGTHJDF, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StripColor ---------------------------------------------------------------------
	 */
	/**
	 * (13) set attribute StripColor
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setStripColor(EnumNamedColor value)
	{
		setAttribute(AttributeName.STRIPCOLOR, value == null ? null : value.getName(), null);
	}

	/**
	 * (19) get EnumNamedColor attribute StripColor
	 * 
	 * @return EnumNamedColor the value of the attribute
	 */
	public EnumNamedColor getStripColor()
	{
		String strAttrName = "";
		EnumNamedColor nPlaceHolder = null;
		strAttrName = getAttribute(AttributeName.STRIPCOLOR, null, JDFCoreConstants.EMPTYSTRING);
		nPlaceHolder = EnumNamedColor.getEnum(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StripColorDetails ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StripColorDetails
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setStripColorDetails(String value)
	{
		setAttribute(AttributeName.STRIPCOLORDETAILS, value, null);
	}

	/**
	 * (23) get String attribute StripColorDetails
	 * 
	 * @return the value of the attribute
	 */
	public String getStripColorDetails()
	{
		return getAttribute(AttributeName.STRIPCOLORDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element HoleMakingParams
	 * 
	 * @return JDFHoleMakingParams the element
	 */
	public JDFHoleMakingParams getHoleMakingParams()
	{
		return (JDFHoleMakingParams) getElement(ElementName.HOLEMAKINGPARAMS, null, 0);
	}

	/**
	 * (25) getCreateHoleMakingParams
	 * 
	 * @return JDFHoleMakingParams the element
	 */
	public JDFHoleMakingParams getCreateHoleMakingParams()
	{
		return (JDFHoleMakingParams) getCreateElement_JDFElement(ElementName.HOLEMAKINGPARAMS, null, 0);
	}

	/**
	 * (29) append element HoleMakingParams
	 * 
	 * @return JDFHoleMakingParams the element
	 * @throws JDFException if the element already exists
	 */
	public JDFHoleMakingParams appendHoleMakingParams() throws JDFException
	{
		return (JDFHoleMakingParams) appendElementN(ElementName.HOLEMAKINGPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refHoleMakingParams(JDFHoleMakingParams refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
