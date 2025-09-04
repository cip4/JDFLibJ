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
import org.cip4.jdflib.span.JDFNumberSpan;
import org.cip4.jdflib.span.JDFSpanDirection;
import org.cip4.jdflib.span.JDFSpanEdgeShape;
import org.cip4.jdflib.span.JDFSpanLevel;
import org.cip4.jdflib.span.JDFSpanNamedColor;
import org.cip4.jdflib.span.JDFStringSpan;
import org.cip4.jdflib.span.JDFXYPairSpan;

/**
 ***************************************************************************** class JDFAutoEmbossingItem : public JDFElement
 */

public abstract class JDFAutoEmbossingItem extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.TOOLNAME, 0x3333111111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[10];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.DIRECTION, 0x5555555551l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.EDGEANGLE, 0x6666666661l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.EDGESHAPE, 0x6666666661l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.EMBOSSINGTYPE, 0x6666666661l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.FOILCOLOR, 0x6666666661l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.FOILCOLORDETAILS, 0x6666666661l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.HEIGHT, 0x6666666661l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.IMAGESIZE, 0x6666666661l);
		elemInfoTable[8] = new ElemInfoTable(ElementName.LEVEL, 0x6666666661l);
		elemInfoTable[9] = new ElemInfoTable(ElementName.POSITION, 0x6666666661l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoEmbossingItem
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoEmbossingItem(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoEmbossingItem
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoEmbossingItem(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoEmbossingItem
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoEmbossingItem(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Methods for Attribute ToolName
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ToolName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setToolName(String value)
	{
		setAttribute(AttributeName.TOOLNAME, value, null);
	}

	/**
	 * (23) get String attribute ToolName
	 *
	 * @return the value of the attribute
	 */
	public String getToolName()
	{
		return getAttribute(AttributeName.TOOLNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element Direction
	 *
	 * @return JDFSpanDirection the element
	 */
	public JDFSpanDirection getDirection()
	{
		return (JDFSpanDirection) getElement(ElementName.DIRECTION, null, 0);
	}

	/**
	 * (25) getCreateDirection
	 * 
	 * @return JDFSpanDirection the element
	 */
	public JDFSpanDirection getCreateDirection()
	{
		return (JDFSpanDirection) getCreateElement_JDFElement(ElementName.DIRECTION, null, 0);
	}

	/**
	 * (29) append element Direction
	 *
	 * @return JDFSpanDirection the element
	 * @ if the element already exists
	 */
	public JDFSpanDirection appendDirection()
	{
		return (JDFSpanDirection) appendElementN(ElementName.DIRECTION, 1, null);
	}

	/**
	 * (24) const get element EdgeAngle
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getEdgeAngle()
	{
		return (JDFNumberSpan) getElement(ElementName.EDGEANGLE, null, 0);
	}

	/**
	 * (25) getCreateEdgeAngle
	 * 
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCreateEdgeAngle()
	{
		return (JDFNumberSpan) getCreateElement_JDFElement(ElementName.EDGEANGLE, null, 0);
	}

	/**
	 * (29) append element EdgeAngle
	 *
	 * @return JDFNumberSpan the element
	 * @ if the element already exists
	 */
	public JDFNumberSpan appendEdgeAngle()
	{
		return (JDFNumberSpan) appendElementN(ElementName.EDGEANGLE, 1, null);
	}

	/**
	 * (24) const get element EdgeShape
	 *
	 * @return JDFSpanEdgeShape the element
	 */
	public JDFSpanEdgeShape getEdgeShape()
	{
		return (JDFSpanEdgeShape) getElement(ElementName.EDGESHAPE, null, 0);
	}

	/**
	 * (25) getCreateEdgeShape
	 * 
	 * @return JDFSpanEdgeShape the element
	 */
	public JDFSpanEdgeShape getCreateEdgeShape()
	{
		return (JDFSpanEdgeShape) getCreateElement_JDFElement(ElementName.EDGESHAPE, null, 0);
	}

	/**
	 * (29) append element EdgeShape
	 *
	 * @return JDFSpanEdgeShape the element
	 * @ if the element already exists
	 */
	public JDFSpanEdgeShape appendEdgeShape()
	{
		return (JDFSpanEdgeShape) appendElementN(ElementName.EDGESHAPE, 1, null);
	}

	/**
	 * (24) const get element EmbossingType
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getEmbossingType()
	{
		return (JDFStringSpan) getElement(ElementName.EMBOSSINGTYPE, null, 0);
	}

	/**
	 * (25) getCreateEmbossingType
	 * 
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateEmbossingType()
	{
		return (JDFStringSpan) getCreateElement_JDFElement(ElementName.EMBOSSINGTYPE, null, 0);
	}

	/**
	 * (29) append element EmbossingType
	 *
	 * @return JDFStringSpan the element
	 * @ if the element already exists
	 */
	public JDFStringSpan appendEmbossingType()
	{
		return (JDFStringSpan) appendElementN(ElementName.EMBOSSINGTYPE, 1, null);
	}

	/**
	 * (24) const get element FoilColor
	 *
	 * @return JDFSpanNamedColor the element
	 */
	public JDFSpanNamedColor getFoilColor()
	{
		return (JDFSpanNamedColor) getElement(ElementName.FOILCOLOR, null, 0);
	}

	/**
	 * (25) getCreateFoilColor
	 * 
	 * @return JDFSpanNamedColor the element
	 */
	public JDFSpanNamedColor getCreateFoilColor()
	{
		return (JDFSpanNamedColor) getCreateElement_JDFElement(ElementName.FOILCOLOR, null, 0);
	}

	/**
	 * (29) append element FoilColor
	 *
	 * @return JDFSpanNamedColor the element
	 * @ if the element already exists
	 */
	public JDFSpanNamedColor appendFoilColor()
	{
		return (JDFSpanNamedColor) appendElementN(ElementName.FOILCOLOR, 1, null);
	}

	/**
	 * (24) const get element FoilColorDetails
	 *
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getFoilColorDetails()
	{
		return (JDFStringSpan) getElement(ElementName.FOILCOLORDETAILS, null, 0);
	}

	/**
	 * (25) getCreateFoilColorDetails
	 * 
	 * @return JDFStringSpan the element
	 */
	public JDFStringSpan getCreateFoilColorDetails()
	{
		return (JDFStringSpan) getCreateElement_JDFElement(ElementName.FOILCOLORDETAILS, null, 0);
	}

	/**
	 * (29) append element FoilColorDetails
	 *
	 * @return JDFStringSpan the element
	 * @ if the element already exists
	 */
	public JDFStringSpan appendFoilColorDetails()
	{
		return (JDFStringSpan) appendElementN(ElementName.FOILCOLORDETAILS, 1, null);
	}

	/**
	 * (24) const get element Height
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getHeight()
	{
		return (JDFNumberSpan) getElement(ElementName.HEIGHT, null, 0);
	}

	/**
	 * (25) getCreateHeight
	 * 
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCreateHeight()
	{
		return (JDFNumberSpan) getCreateElement_JDFElement(ElementName.HEIGHT, null, 0);
	}

	/**
	 * (29) append element Height
	 *
	 * @return JDFNumberSpan the element
	 * @ if the element already exists
	 */
	public JDFNumberSpan appendHeight()
	{
		return (JDFNumberSpan) appendElementN(ElementName.HEIGHT, 1, null);
	}

	/**
	 * (24) const get element ImageSize
	 *
	 * @return JDFXYPairSpan the element
	 */
	public JDFXYPairSpan getImageSize()
	{
		return (JDFXYPairSpan) getElement(ElementName.IMAGESIZE, null, 0);
	}

	/**
	 * (25) getCreateImageSize
	 * 
	 * @return JDFXYPairSpan the element
	 */
	public JDFXYPairSpan getCreateImageSize()
	{
		return (JDFXYPairSpan) getCreateElement_JDFElement(ElementName.IMAGESIZE, null, 0);
	}

	/**
	 * (29) append element ImageSize
	 *
	 * @return JDFXYPairSpan the element
	 * @ if the element already exists
	 */
	public JDFXYPairSpan appendImageSize()
	{
		return (JDFXYPairSpan) appendElementN(ElementName.IMAGESIZE, 1, null);
	}

	/**
	 * (24) const get element Level
	 *
	 * @return JDFSpanLevel the element
	 */
	public JDFSpanLevel getLevel()
	{
		return (JDFSpanLevel) getElement(ElementName.LEVEL, null, 0);
	}

	/**
	 * (25) getCreateLevel
	 * 
	 * @return JDFSpanLevel the element
	 */
	public JDFSpanLevel getCreateLevel()
	{
		return (JDFSpanLevel) getCreateElement_JDFElement(ElementName.LEVEL, null, 0);
	}

	/**
	 * (29) append element Level
	 *
	 * @return JDFSpanLevel the element
	 * @ if the element already exists
	 */
	public JDFSpanLevel appendLevel()
	{
		return (JDFSpanLevel) appendElementN(ElementName.LEVEL, 1, null);
	}

	/**
	 * (24) const get element Position
	 *
	 * @return JDFXYPairSpan the element
	 */
	public JDFXYPairSpan getPosition()
	{
		return (JDFXYPairSpan) getElement(ElementName.POSITION, null, 0);
	}

	/**
	 * (25) getCreatePosition
	 * 
	 * @return JDFXYPairSpan the element
	 */
	public JDFXYPairSpan getCreatePosition()
	{
		return (JDFXYPairSpan) getCreateElement_JDFElement(ElementName.POSITION, null, 0);
	}

	/**
	 * (29) append element Position
	 *
	 * @return JDFXYPairSpan the element
	 * @ if the element already exists
	 */
	public JDFXYPairSpan appendPosition()
	{
		return (JDFXYPairSpan) appendElementN(ElementName.POSITION, 1, null);
	}

}
