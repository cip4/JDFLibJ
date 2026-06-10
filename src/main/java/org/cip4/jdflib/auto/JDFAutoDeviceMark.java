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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.process.JDFBarcodeReproParams;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoDeviceMark : public JDFElement
 */

public abstract class JDFAutoDeviceMark extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ANCHOR, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumAnchor.class, 0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.FONT, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.FONTSIZE, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.HORIZONTALFITPOLICY, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumHorizontalFitPolicy.class, 0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.MARKJUSTIFICATION, 0x4444443331l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumMarkJustification.class, 0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.MARKOFFSET, 0x4444443331l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MARKORIENTATION, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumMarkOrientation.class, 0), null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.MARKPOSITION, 0x4444443331l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumMarkPosition.class, 0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.VERTICALFITPOLICY, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumVerticalFitPolicy.class, 0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BARCODEREPROPARAMS, 0x3333333331l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoDeviceMark
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDeviceMark(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDeviceMark
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDeviceMark(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDeviceMark
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDeviceMark(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numAnchor
	 */

	public enum EnumAnchor
	{
		TopLeft, TopCenter, TopRight, CenterLeft, Center, CenterRight, BottomLeft, BottomCenter, BottomRight;

		public static EnumAnchor getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumAnchor.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numHorizontalFitPolicy
	 */

	public enum EnumHorizontalFitPolicy
	{
		NoRepeat, StretchToFit, UndistortedScaleToFit, RepeatToFill, RepeatUnclipped;

		public static EnumHorizontalFitPolicy getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumHorizontalFitPolicy.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numMarkJustification
	 */

	public enum EnumMarkJustification
	{
		Left, Right, Center;

		public static EnumMarkJustification getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumMarkJustification.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numMarkOrientation
	 */

	public enum EnumMarkOrientation
	{
		Vertical, Horizontal;

		public static EnumMarkOrientation getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumMarkOrientation.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numMarkPosition
	 */

	public enum EnumMarkPosition
	{
		Top, Bottom, Left, Right;

		public static EnumMarkPosition getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumMarkPosition.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numVerticalFitPolicy
	 */

	public enum EnumVerticalFitPolicy
	{
		NoRepeat, StretchToFit, UndistortedScaleToFit, RepeatToFill, RepeatUnclipped;

		public static EnumVerticalFitPolicy getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumVerticalFitPolicy.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Anchor
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Anchor
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setAnchor(final EnumAnchor enumVar)
	{
		setAttribute(AttributeName.ANCHOR, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Anchor
	 *
	 * @return the value of the attribute
	 */
	public EnumAnchor getAnchor()
	{
		return EnumAnchor.getEnum(getAttribute(AttributeName.ANCHOR, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Font
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Font
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFont(final String value)
	{
		setAttribute(AttributeName.FONT, value, null);
	}

	/**
	 * (23) get String attribute Font
	 *
	 * @return the value of the attribute
	 */
	public String getFont()
	{
		return getAttribute(AttributeName.FONT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FontSize
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FontSize
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFontSize(final double value)
	{
		setAttribute(AttributeName.FONTSIZE, value, null);
	}

	/**
	 * (17) get double attribute FontSize
	 *
	 * @return double the value of the attribute
	 */
	public double getFontSize()
	{
		return getRealAttribute(AttributeName.FONTSIZE, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute HorizontalFitPolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute HorizontalFitPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setHorizontalFitPolicy(final EnumHorizontalFitPolicy enumVar)
	{
		setAttribute(AttributeName.HORIZONTALFITPOLICY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute HorizontalFitPolicy
	 *
	 * @return the value of the attribute
	 */
	public EnumHorizontalFitPolicy getHorizontalFitPolicy()
	{
		return EnumHorizontalFitPolicy.getEnum(getAttribute(AttributeName.HORIZONTALFITPOLICY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MarkJustification
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MarkJustification
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMarkJustification(final EnumMarkJustification enumVar)
	{
		setAttribute(AttributeName.MARKJUSTIFICATION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute MarkJustification
	 *
	 * @return the value of the attribute
	 */
	public EnumMarkJustification getMarkJustification()
	{
		return EnumMarkJustification.getEnum(getAttribute(AttributeName.MARKJUSTIFICATION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MarkOffset
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MarkOffset
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMarkOffset(final JDFXYPair value)
	{
		setAttribute(AttributeName.MARKOFFSET, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute MarkOffset
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getMarkOffset()
	{
		final String strAttrName = getAttribute(AttributeName.MARKOFFSET, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MarkOrientation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MarkOrientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMarkOrientation(final EnumMarkOrientation enumVar)
	{
		setAttribute(AttributeName.MARKORIENTATION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute MarkOrientation
	 *
	 * @return the value of the attribute
	 */
	public EnumMarkOrientation getMarkOrientation()
	{
		return EnumMarkOrientation.getEnum(getAttribute(AttributeName.MARKORIENTATION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MarkPosition
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MarkPosition
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMarkPosition(final EnumMarkPosition enumVar)
	{
		setAttribute(AttributeName.MARKPOSITION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute MarkPosition
	 *
	 * @return the value of the attribute
	 */
	public EnumMarkPosition getMarkPosition()
	{
		return EnumMarkPosition.getEnum(getAttribute(AttributeName.MARKPOSITION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute VerticalFitPolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute VerticalFitPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setVerticalFitPolicy(final EnumVerticalFitPolicy enumVar)
	{
		setAttribute(AttributeName.VERTICALFITPOLICY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute VerticalFitPolicy
	 *
	 * @return the value of the attribute
	 */
	public EnumVerticalFitPolicy getVerticalFitPolicy()
	{
		return EnumVerticalFitPolicy.getEnum(getAttribute(AttributeName.VERTICALFITPOLICY, null, null));
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element BarcodeReproParams
	 *
	 * @return JDFBarcodeReproParams the element
	 */
	public JDFBarcodeReproParams getBarcodeReproParams()
	{
		return (JDFBarcodeReproParams) getElement(ElementName.BARCODEREPROPARAMS, null, 0);
	}

	/**
	 * (25) getCreateBarcodeReproParams
	 * 
	 * @return JDFBarcodeReproParams the element
	 */
	public JDFBarcodeReproParams getCreateBarcodeReproParams()
	{
		return (JDFBarcodeReproParams) getCreateElement_JDFElement(ElementName.BARCODEREPROPARAMS, null, 0);
	}

	/**
	 * (26) getCreateBarcodeReproParams
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFBarcodeReproParams the element
	 */
	public JDFBarcodeReproParams getCreateBarcodeReproParams(final int iSkip)
	{
		return (JDFBarcodeReproParams) getCreateElement_JDFElement(ElementName.BARCODEREPROPARAMS, null, iSkip);
	}

	/**
	 * (27) const get element BarcodeReproParams
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFBarcodeReproParams the element
	 *         default is getBarcodeReproParams(0)
	 */
	public JDFBarcodeReproParams getBarcodeReproParams(final int iSkip)
	{
		return (JDFBarcodeReproParams) getElement(ElementName.BARCODEREPROPARAMS, null, iSkip);
	}

	/**
	 * Get all BarcodeReproParams from the current element
	 * 
	 * @return Collection<JDFBarcodeReproParams>, null if none are available
	 */
	public Collection<JDFBarcodeReproParams> getAllBarcodeReproParams()
	{
		return getChildArrayByClass(JDFBarcodeReproParams.class, false, 0);
	}

	/**
	 * (30) append element BarcodeReproParams
	 *
	 * @return JDFBarcodeReproParams the element
	 */
	public JDFBarcodeReproParams appendBarcodeReproParams()
	{
		return (JDFBarcodeReproParams) appendElement(ElementName.BARCODEREPROPARAMS, null);
	}

}
