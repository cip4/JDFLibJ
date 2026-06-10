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
import org.cip4.jdflib.resource.JDFJobField;
import org.cip4.jdflib.resource.JDFRefAnchor;
import org.cip4.jdflib.resource.process.JDFMarkColor;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoStripMark : public JDFElement
 */

public abstract class JDFAutoStripMark extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[17];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ABSOLUTEHEIGHT, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ABSOLUTEWIDTH, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ANCHOR, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumAnchor.class, 0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.FONT, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.FONTSIZE, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.HORIZONTALFITPOLICY, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumHorizontalFitPolicy.class, 0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.ID, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.MARKCONTEXT, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumMarkContext.class, 0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.MARKNAME, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.MARKSIDE, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumMarkSide.class, 0), null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.OFFSET, 0x3333333111l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.ORD, 0x3333333111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.ORIENTATION, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumOrientation.class, 0), null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.RELATIVEHEIGHT, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.RELATIVEWIDTH, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.STRIPMARKDETAILS, 0x3333333111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.VERTICALFITPOLICY, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumVerticalFitPolicy.class, 0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.MARKCOLOR, 0x3333333111l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.POSITION, 0x6666666111l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.JOBFIELD, 0x6666666111l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.REFANCHOR, 0x3333333111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoStripMark
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoStripMark(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoStripMark
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoStripMark(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoStripMark
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoStripMark(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
	 * Enumeration strings for numMarkContext
	 */

	public enum EnumMarkContext
	{
		BinderySignature, Cell, CellPair, Sheet, Tile;

		public static EnumMarkContext getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumMarkContext.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numMarkSide
	 */

	public enum EnumMarkSide
	{
		Front, Back, TwoSidedBackToBack, TwoSidedIndependent;

		public static EnumMarkSide getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumMarkSide.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numOrientation
	 */

	public enum EnumOrientation
	{
		Rotate0, Rotate90, Rotate180, Rotate270, Flip0, Flip90, Flip180, Flip270;

		public static EnumOrientation getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumOrientation.class, val, null);
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
	 * Methods for Attribute AbsoluteHeight
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AbsoluteHeight
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAbsoluteHeight(final double value)
	{
		setAttribute(AttributeName.ABSOLUTEHEIGHT, value, null);
	}

	/**
	 * (17) get double attribute AbsoluteHeight
	 *
	 * @return double the value of the attribute
	 */
	public double getAbsoluteHeight()
	{
		return getRealAttribute(AttributeName.ABSOLUTEHEIGHT, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AbsoluteWidth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AbsoluteWidth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAbsoluteWidth(final double value)
	{
		setAttribute(AttributeName.ABSOLUTEWIDTH, value, null);
	}

	/**
	 * (17) get double attribute AbsoluteWidth
	 *
	 * @return double the value of the attribute
	 */
	public double getAbsoluteWidth()
	{
		return getRealAttribute(AttributeName.ABSOLUTEWIDTH, null, 0.0);
	}

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
	 * Methods for Attribute ID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ID
	 *
	 * @param value the value to set the attribute to
	 */
	@Override
	public void setID(final String value)
	{
		setAttribute(AttributeName.ID, value, null);
	}

	/**
	 * (23) get String attribute ID
	 *
	 * @return the value of the attribute
	 */
	@Override
	public String getID()
	{
		return getAttribute(AttributeName.ID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MarkContext
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MarkContext
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMarkContext(final EnumMarkContext enumVar)
	{
		setAttribute(AttributeName.MARKCONTEXT, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute MarkContext
	 *
	 * @return the value of the attribute
	 */
	public EnumMarkContext getMarkContext()
	{
		return EnumMarkContext.getEnum(getAttribute(AttributeName.MARKCONTEXT, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MarkName
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MarkName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMarkName(final String value)
	{
		setAttribute(AttributeName.MARKNAME, value, null);
	}

	/**
	 * (23) get String attribute MarkName
	 *
	 * @return the value of the attribute
	 */
	public String getMarkName()
	{
		return getAttribute(AttributeName.MARKNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MarkSide
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MarkSide
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMarkSide(final EnumMarkSide enumVar)
	{
		setAttribute(AttributeName.MARKSIDE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute MarkSide
	 *
	 * @return the value of the attribute
	 */
	public EnumMarkSide getMarkSide()
	{
		return EnumMarkSide.getEnum(getAttribute(AttributeName.MARKSIDE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Offset
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Offset
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOffset(final JDFXYPair value)
	{
		setAttribute(AttributeName.OFFSET, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Offset
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getOffset()
	{
		final String strAttrName = getAttribute(AttributeName.OFFSET, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Ord
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Ord
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOrd(final int value)
	{
		setAttribute(AttributeName.ORD, value, null);
	}

	/**
	 * (15) get int attribute Ord
	 *
	 * @return int the value of the attribute
	 */
	public int getOrd()
	{
		return getIntAttribute(AttributeName.ORD, null, 0);
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
	public void setOrientation(final EnumOrientation enumVar)
	{
		setAttribute(AttributeName.ORIENTATION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Orientation
	 *
	 * @return the value of the attribute
	 */
	public EnumOrientation getOrientation()
	{
		return EnumOrientation.getEnum(getAttribute(AttributeName.ORIENTATION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute RelativeHeight
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RelativeHeight
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRelativeHeight(final double value)
	{
		setAttribute(AttributeName.RELATIVEHEIGHT, value, null);
	}

	/**
	 * (17) get double attribute RelativeHeight
	 *
	 * @return double the value of the attribute
	 */
	public double getRelativeHeight()
	{
		return getRealAttribute(AttributeName.RELATIVEHEIGHT, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute RelativeWidth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RelativeWidth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRelativeWidth(final double value)
	{
		setAttribute(AttributeName.RELATIVEWIDTH, value, null);
	}

	/**
	 * (17) get double attribute RelativeWidth
	 *
	 * @return double the value of the attribute
	 */
	public double getRelativeWidth()
	{
		return getRealAttribute(AttributeName.RELATIVEWIDTH, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StripMarkDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StripMarkDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStripMarkDetails(final String value)
	{
		setAttribute(AttributeName.STRIPMARKDETAILS, value, null);
	}

	/**
	 * (23) get String attribute StripMarkDetails
	 *
	 * @return the value of the attribute
	 */
	public String getStripMarkDetails()
	{
		return getAttribute(AttributeName.STRIPMARKDETAILS, null, JDFCoreConstants.EMPTYSTRING);
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
	 * (24) const get element MarkColor
	 *
	 * @return JDFMarkColor the element
	 */
	public JDFMarkColor getMarkColor()
	{
		return (JDFMarkColor) getElement(ElementName.MARKCOLOR, null, 0);
	}

	/**
	 * (25) getCreateMarkColor
	 * 
	 * @return JDFMarkColor the element
	 */
	public JDFMarkColor getCreateMarkColor()
	{
		return (JDFMarkColor) getCreateElement_JDFElement(ElementName.MARKCOLOR, null, 0);
	}

	/**
	 * (26) getCreateMarkColor
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFMarkColor the element
	 */
	public JDFMarkColor getCreateMarkColor(final int iSkip)
	{
		return (JDFMarkColor) getCreateElement_JDFElement(ElementName.MARKCOLOR, null, iSkip);
	}

	/**
	 * (27) const get element MarkColor
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFMarkColor the element
	 *         default is getMarkColor(0)
	 */
	public JDFMarkColor getMarkColor(final int iSkip)
	{
		return (JDFMarkColor) getElement(ElementName.MARKCOLOR, null, iSkip);
	}

	/**
	 * Get all MarkColor from the current element
	 * 
	 * @return Collection<JDFMarkColor>, null if none are available
	 */
	public Collection<JDFMarkColor> getAllMarkColor()
	{
		return getChildArrayByClass(JDFMarkColor.class, false, 0);
	}

	/**
	 * (30) append element MarkColor
	 *
	 * @return JDFMarkColor the element
	 */
	public JDFMarkColor appendMarkColor()
	{
		return (JDFMarkColor) appendElement(ElementName.MARKCOLOR, null);
	}

	/**
	 * (24) const get element Position
	 *
	 * @return JDFPosition the element
	 */
	public JDFPosition getPosition()
	{
		return (JDFPosition) getElement(ElementName.POSITION, null, 0);
	}

	/**
	 * (25) getCreatePosition
	 * 
	 * @return JDFPosition the element
	 */
	public JDFPosition getCreatePosition()
	{
		return (JDFPosition) getCreateElement_JDFElement(ElementName.POSITION, null, 0);
	}

	/**
	 * (29) append element Position
	 *
	 * @return JDFPosition the element
	 * @ if the element already exists
	 */
	public JDFPosition appendPosition()
	{
		return (JDFPosition) appendElementN(ElementName.POSITION, 1, null);
	}

	/**
	 * (24) const get element JobField
	 *
	 * @return JDFJobField the element
	 */
	public JDFJobField getJobField()
	{
		return (JDFJobField) getElement(ElementName.JOBFIELD, null, 0);
	}

	/**
	 * (25) getCreateJobField
	 * 
	 * @return JDFJobField the element
	 */
	public JDFJobField getCreateJobField()
	{
		return (JDFJobField) getCreateElement_JDFElement(ElementName.JOBFIELD, null, 0);
	}

	/**
	 * (29) append element JobField
	 *
	 * @return JDFJobField the element
	 * @ if the element already exists
	 */
	public JDFJobField appendJobField()
	{
		return (JDFJobField) appendElementN(ElementName.JOBFIELD, 1, null);
	}

	/**
	 * (24) const get element RefAnchor
	 *
	 * @return JDFRefAnchor the element
	 */
	public JDFRefAnchor getRefAnchor()
	{
		return (JDFRefAnchor) getElement(ElementName.REFANCHOR, null, 0);
	}

	/**
	 * (25) getCreateRefAnchor
	 * 
	 * @return JDFRefAnchor the element
	 */
	public JDFRefAnchor getCreateRefAnchor()
	{
		return (JDFRefAnchor) getCreateElement_JDFElement(ElementName.REFANCHOR, null, 0);
	}

	/**
	 * (26) getCreateRefAnchor
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFRefAnchor the element
	 */
	public JDFRefAnchor getCreateRefAnchor(final int iSkip)
	{
		return (JDFRefAnchor) getCreateElement_JDFElement(ElementName.REFANCHOR, null, iSkip);
	}

	/**
	 * (27) const get element RefAnchor
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFRefAnchor the element
	 *         default is getRefAnchor(0)
	 */
	public JDFRefAnchor getRefAnchor(final int iSkip)
	{
		return (JDFRefAnchor) getElement(ElementName.REFANCHOR, null, iSkip);
	}

	/**
	 * Get all RefAnchor from the current element
	 * 
	 * @return Collection<JDFRefAnchor>, null if none are available
	 */
	public Collection<JDFRefAnchor> getAllRefAnchor()
	{
		return getChildArrayByClass(JDFRefAnchor.class, false, 0);
	}

	/**
	 * (30) append element RefAnchor
	 *
	 * @return JDFRefAnchor the element
	 */
	public JDFRefAnchor appendRefAnchor()
	{
		return (JDFRefAnchor) appendElement(ElementName.REFANCHOR, null);
	}

}
