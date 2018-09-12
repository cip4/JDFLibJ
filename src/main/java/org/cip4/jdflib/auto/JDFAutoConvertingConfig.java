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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.process.JDFMedia;

/**
 *****************************************************************************
 * class JDFAutoConvertingConfig : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoConvertingConfig extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.MARGINBOTTOM, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.MARGINLEFT, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.MARGINRIGHT, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.MARGINTOP, 0x33331111, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.SHEETHEIGHT, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.SHEETWIDTH, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.DEVICE, 0x66661111);
		elemInfoTable[1] = new ElemInfoTable(ElementName.MEDIA, 0x66661111);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoConvertingConfig
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoConvertingConfig(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoConvertingConfig
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoConvertingConfig(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoConvertingConfig
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoConvertingConfig(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoConvertingConfig[  --> " + super.toString() + " ]";
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MarginBottom ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MarginBottom
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setMarginBottom(double value)
	{
		setAttribute(AttributeName.MARGINBOTTOM, value, null);
	}

	/**
	 * (17) get double attribute MarginBottom
	 * 
	 * @return double the value of the attribute
	 */
	public double getMarginBottom()
	{
		return getRealAttribute(AttributeName.MARGINBOTTOM, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MarginLeft ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MarginLeft
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setMarginLeft(double value)
	{
		setAttribute(AttributeName.MARGINLEFT, value, null);
	}

	/**
	 * (17) get double attribute MarginLeft
	 * 
	 * @return double the value of the attribute
	 */
	public double getMarginLeft()
	{
		return getRealAttribute(AttributeName.MARGINLEFT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MarginRight ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MarginRight
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setMarginRight(double value)
	{
		setAttribute(AttributeName.MARGINRIGHT, value, null);
	}

	/**
	 * (17) get double attribute MarginRight
	 * 
	 * @return double the value of the attribute
	 */
	public double getMarginRight()
	{
		return getRealAttribute(AttributeName.MARGINRIGHT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MarginTop ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MarginTop
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setMarginTop(double value)
	{
		setAttribute(AttributeName.MARGINTOP, value, null);
	}

	/**
	 * (17) get double attribute MarginTop
	 * 
	 * @return double the value of the attribute
	 */
	public double getMarginTop()
	{
		return getRealAttribute(AttributeName.MARGINTOP, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SheetHeight ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SheetHeight
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setSheetHeight(JDFNumberRange value)
	{
		setAttribute(AttributeName.SHEETHEIGHT, value, null);
	}

	/**
	 * (20) get JDFNumberRange attribute SheetHeight
	 * 
	 * @return JDFNumberRange the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberRange
	 */
	public JDFNumberRange getSheetHeight()
	{
		final String strAttrName = getAttribute(AttributeName.SHEETHEIGHT, null, null);
		final JDFNumberRange nPlaceHolder = JDFNumberRange.createNumberRange(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SheetWidth ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SheetWidth
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setSheetWidth(JDFNumberRange value)
	{
		setAttribute(AttributeName.SHEETWIDTH, value, null);
	}

	/**
	 * (20) get JDFNumberRange attribute SheetWidth
	 * 
	 * @return JDFNumberRange the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberRange
	 */
	public JDFNumberRange getSheetWidth()
	{
		final String strAttrName = getAttribute(AttributeName.SHEETWIDTH, null, null);
		final JDFNumberRange nPlaceHolder = JDFNumberRange.createNumberRange(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Device
	 * 
	 * @return JDFDevice the element
	 */
	public JDFDevice getDevice()
	{
		return (JDFDevice) getElement(ElementName.DEVICE, null, 0);
	}

	/**
	 * (25) getCreateDevice
	 * 
	 * @return JDFDevice the element
	 */
	public JDFDevice getCreateDevice()
	{
		return (JDFDevice) getCreateElement(ElementName.DEVICE, null, 0);
	}

	/**
	 * (29) append element Device
	 * 
	 * @return JDFDevice the element
	 * @throws JDFException if the element already exists
	 */
	public JDFDevice appendDevice() throws JDFException
	{
		return (JDFDevice) appendElementN(ElementName.DEVICE, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refDevice(JDFDevice refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Media
	 * 
	 * @return JDFMedia the element
	 */
	public JDFMedia getMedia()
	{
		return (JDFMedia) getElement(ElementName.MEDIA, null, 0);
	}

	/**
	 * (25) getCreateMedia
	 * 
	 * @return JDFMedia the element
	 */
	public JDFMedia getCreateMedia()
	{
		return (JDFMedia) getCreateElement(ElementName.MEDIA, null, 0);
	}

	/**
	 * (29) append element Media
	 * 
	 * @return JDFMedia the element
	 * @throws JDFException if the element already exists
	 */
	public JDFMedia appendMedia() throws JDFException
	{
		return (JDFMedia) appendElementN(ElementName.MEDIA, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refMedia(JDFMedia refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
