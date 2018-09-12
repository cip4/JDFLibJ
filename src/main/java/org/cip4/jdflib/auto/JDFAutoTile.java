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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFMediaSource;

/**
 *****************************************************************************
 * class JDFAutoTile : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoTile extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CLIPBOX, 0x22222222, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CTM, 0x22222222, AttributeInfo.EnumAttributeType.matrix, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.TRIMBOX, 0x33311111, AttributeInfo.EnumAttributeType.rectangle, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.MARKOBJECT, 0x33331111);
		elemInfoTable[1] = new ElemInfoTable(ElementName.MEDIA, 0x66666611);
		elemInfoTable[2] = new ElemInfoTable(ElementName.MEDIASOURCE, 0x77777766);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoTile
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoTile(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoTile
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoTile(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoTile
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoTile(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoTile[  --> " + super.toString() + " ]";
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
	 * --------------------------------------------------------------------- Methods for Attribute ClipBox ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ClipBox
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setClipBox(JDFRectangle value)
	{
		setAttribute(AttributeName.CLIPBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute ClipBox
	 * 
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getClipBox()
	{
		final String strAttrName = getAttribute(AttributeName.CLIPBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CTM ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CTM
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setCTM(JDFMatrix value)
	{
		setAttribute(AttributeName.CTM, value, null);
	}

	/**
	 * (20) get JDFMatrix attribute CTM
	 * 
	 * @return JDFMatrix the value of the attribute, null if a the attribute value is not a valid to create a JDFMatrix
	 */
	public JDFMatrix getCTM()
	{
		final String strAttrName = getAttribute(AttributeName.CTM, null, null);
		final JDFMatrix nPlaceHolder = JDFMatrix.createMatrix(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrimBox ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TrimBox
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setTrimBox(JDFRectangle value)
	{
		setAttribute(AttributeName.TRIMBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute TrimBox
	 * 
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getTrimBox()
	{
		final String strAttrName = getAttribute(AttributeName.TRIMBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateMarkObject
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFMarkObject the element
	 */
	public JDFMarkObject getCreateMarkObject(int iSkip)
	{
		return (JDFMarkObject) getCreateElement_KElement(ElementName.MARKOBJECT, null, iSkip);
	}

	/**
	 * (27) const get element MarkObject
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFMarkObject the element default is getMarkObject(0)
	 */
	public JDFMarkObject getMarkObject(int iSkip)
	{
		return (JDFMarkObject) getElement(ElementName.MARKOBJECT, null, iSkip);
	}

	/**
	 * Get all MarkObject from the current element
	 * 
	 * @return Collection<JDFMarkObject>, null if none are available
	 */
	public Collection<JDFMarkObject> getAllMarkObject()
	{
		return getChildrenByClass(JDFMarkObject.class, false, 0);
	}

	/**
	 * (30) append element MarkObject
	 * 
	 * @return JDFMarkObject the element
	 */
	public JDFMarkObject appendMarkObject()
	{
		return (JDFMarkObject) appendElement(ElementName.MARKOBJECT, null);
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
		return (JDFMedia) getCreateElement_KElement(ElementName.MEDIA, null, 0);
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

	/**
	 * (24) const get element MediaSource
	 * 
	 * @return JDFMediaSource the element
	 */
	public JDFMediaSource getMediaSource()
	{
		return (JDFMediaSource) getElement(ElementName.MEDIASOURCE, null, 0);
	}

	/**
	 * (25) getCreateMediaSource
	 * 
	 * @return JDFMediaSource the element
	 */
	public JDFMediaSource getCreateMediaSource()
	{
		return (JDFMediaSource) getCreateElement_KElement(ElementName.MEDIASOURCE, null, 0);
	}

	/**
	 * (29) append element MediaSource
	 * 
	 * @return JDFMediaSource the element
	 * @throws JDFException if the element already exists
	 */
	public JDFMediaSource appendMediaSource() throws JDFException
	{
		return (JDFMediaSource) appendElementN(ElementName.MEDIASOURCE, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refMediaSource(JDFMediaSource refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
