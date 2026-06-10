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
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.span.JDFIntegerSpan;
import org.cip4.jdflib.span.JDFNumberSpan;
import org.cip4.jdflib.span.JDFShapeSpan;
import org.cip4.jdflib.span.JDFSpanFinishedGrainDirection;
import org.cip4.jdflib.span.JDFSpanSizePolicy;
import org.cip4.jdflib.span.JDFXYPairSpan;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoLayoutIntent : public JDFIntentResource
 */

public abstract class JDFAutoLayoutIntent extends JDFIntentResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.FOLIOCOUNT, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumFolioCount.class, 0), "Booklet");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.NUMBERUP, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, "1 1");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.FINISHEDPAGEORIENTATION, 0x4444444443l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumFinishedPageOrientation.class, 0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ORIENTATION, 0x3333111111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumOrientation.class, 0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ROTATEPOLICY, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumRotatePolicy.class, 0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.SPREADTYPE, 0x3333111111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumSpreadType.class, 0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.SIDES, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumSides.class, 0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[8];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BLEED, 0x3333311111l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.DIMENSIONS, 0x6666666661l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.FINISHEDDIMENSIONS, 0x6666666661l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.FINISHEDGRAINDIRECTION, 0x7777766611l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.PAGES, 0x6666666661l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.PAGEVARIANCE, 0x6666666661l);
		elemInfoTable[6] = new ElemInfoTable(ElementName.LAYOUT, 0x6666666661l);
		elemInfoTable[7] = new ElemInfoTable(ElementName.SIZEPOLICY, 0x6666666611l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoLayoutIntent
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoLayoutIntent(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLayoutIntent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoLayoutIntent(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLayoutIntent
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoLayoutIntent(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numFolioCount
	 */

	public enum EnumFolioCount
	{
		Booklet, Flat;

		public static EnumFolioCount getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumFolioCount.class, val, EnumFolioCount.Booklet);
		}
	}

	/**
	 * Enumeration strings for numFinishedPageOrientation
	 */

	public enum EnumFinishedPageOrientation
	{
		Portrait, Landscape;

		public static EnumFinishedPageOrientation getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumFinishedPageOrientation.class, val, null);
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
	 * Enumeration strings for numRotatePolicy
	 */

	public enum EnumRotatePolicy
	{
		NoRotate, RotateOrthogonal, RotateClockwise, RotateCounterClockwise;

		public static EnumRotatePolicy getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumRotatePolicy.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numSpreadType
	 */

	public enum EnumSpreadType
	{
		SinglePage, Spread;

		public static EnumSpreadType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSpreadType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numSides
	 */

	public enum EnumSides
	{
		OneSided, OneSidedBack, TwoSidedHeadToHead, TwoSidedHeadToFoot, Unprinted;

		public static EnumSides getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSides.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FolioCount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FolioCount
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFolioCount(final EnumFolioCount enumVar)
	{
		setAttribute(AttributeName.FOLIOCOUNT, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute FolioCount
	 *
	 * @return the value of the attribute
	 */
	public EnumFolioCount getFolioCount()
	{
		return EnumFolioCount.getEnum(getAttribute(AttributeName.FOLIOCOUNT, null, "Booklet"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NumberUp
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NumberUp
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNumberUp(final JDFXYPair value)
	{
		setAttribute(AttributeName.NUMBERUP, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute NumberUp
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getNumberUp()
	{
		final String strAttrName = getAttribute(AttributeName.NUMBERUP, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FinishedPageOrientation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FinishedPageOrientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFinishedPageOrientation(final EnumFinishedPageOrientation enumVar)
	{
		setAttribute(AttributeName.FINISHEDPAGEORIENTATION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute FinishedPageOrientation
	 *
	 * @return the value of the attribute
	 */
	public EnumFinishedPageOrientation getFinishedPageOrientation()
	{
		return EnumFinishedPageOrientation.getEnum(getAttribute(AttributeName.FINISHEDPAGEORIENTATION, null, null));
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
	 * Methods for Attribute RotatePolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute RotatePolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setRotatePolicy(final EnumRotatePolicy enumVar)
	{
		setAttribute(AttributeName.ROTATEPOLICY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute RotatePolicy
	 *
	 * @return the value of the attribute
	 */
	public EnumRotatePolicy getRotatePolicy()
	{
		return EnumRotatePolicy.getEnum(getAttribute(AttributeName.ROTATEPOLICY, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SpreadType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SpreadType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSpreadType(final EnumSpreadType enumVar)
	{
		setAttribute(AttributeName.SPREADTYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute SpreadType
	 *
	 * @return the value of the attribute
	 */
	public EnumSpreadType getSpreadType()
	{
		return EnumSpreadType.getEnum(getAttribute(AttributeName.SPREADTYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Sides
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Sides
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSides(final EnumSides enumVar)
	{
		setAttribute(AttributeName.SIDES, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Sides
	 *
	 * @return the value of the attribute
	 */
	public EnumSides getSides()
	{
		return EnumSides.getEnum(getAttribute(AttributeName.SIDES, null, null));
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element Bleed
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getBleed()
	{
		return (JDFNumberSpan) getElement(ElementName.BLEED, null, 0);
	}

	/**
	 * (25) getCreateBleed
	 * 
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCreateBleed()
	{
		return (JDFNumberSpan) getCreateElement_JDFElement(ElementName.BLEED, null, 0);
	}

	/**
	 * (26) getCreateBleed
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCreateBleed(final int iSkip)
	{
		return (JDFNumberSpan) getCreateElement_JDFElement(ElementName.BLEED, null, iSkip);
	}

	/**
	 * (27) const get element Bleed
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFNumberSpan the element
	 *         default is getBleed(0)
	 */
	public JDFNumberSpan getBleed(final int iSkip)
	{
		return (JDFNumberSpan) getElement(ElementName.BLEED, null, iSkip);
	}

	/**
	 * Get all Bleed from the current element
	 * 
	 * @return Collection<JDFNumberSpan>, null if none are available
	 */
	public Collection<JDFNumberSpan> getAllBleed()
	{
		return getChildArrayByClass(JDFNumberSpan.class, false, 0);
	}

	/**
	 * (30) append element Bleed
	 *
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan appendBleed()
	{
		return (JDFNumberSpan) appendElement(ElementName.BLEED, null);
	}

	/**
	 * (24) const get element Dimensions
	 *
	 * @return JDFXYPairSpan the element
	 */
	public JDFXYPairSpan getDimensions()
	{
		return (JDFXYPairSpan) getElement(ElementName.DIMENSIONS, null, 0);
	}

	/**
	 * (25) getCreateDimensions
	 * 
	 * @return JDFXYPairSpan the element
	 */
	public JDFXYPairSpan getCreateDimensions()
	{
		return (JDFXYPairSpan) getCreateElement_JDFElement(ElementName.DIMENSIONS, null, 0);
	}

	/**
	 * (29) append element Dimensions
	 *
	 * @return JDFXYPairSpan the element
	 * @ if the element already exists
	 */
	public JDFXYPairSpan appendDimensions()
	{
		return (JDFXYPairSpan) appendElementN(ElementName.DIMENSIONS, 1, null);
	}

	/**
	 * (24) const get element FinishedDimensions
	 *
	 * @return JDFShapeSpan the element
	 */
	public JDFShapeSpan getFinishedDimensions()
	{
		return (JDFShapeSpan) getElement(ElementName.FINISHEDDIMENSIONS, null, 0);
	}

	/**
	 * (25) getCreateFinishedDimensions
	 * 
	 * @return JDFShapeSpan the element
	 */
	public JDFShapeSpan getCreateFinishedDimensions()
	{
		return (JDFShapeSpan) getCreateElement_JDFElement(ElementName.FINISHEDDIMENSIONS, null, 0);
	}

	/**
	 * (29) append element FinishedDimensions
	 *
	 * @return JDFShapeSpan the element
	 * @ if the element already exists
	 */
	public JDFShapeSpan appendFinishedDimensions()
	{
		return (JDFShapeSpan) appendElementN(ElementName.FINISHEDDIMENSIONS, 1, null);
	}

	/**
	 * (24) const get element FinishedGrainDirection
	 *
	 * @return JDFSpanFinishedGrainDirection the element
	 */
	public JDFSpanFinishedGrainDirection getFinishedGrainDirection()
	{
		return (JDFSpanFinishedGrainDirection) getElement(ElementName.FINISHEDGRAINDIRECTION, null, 0);
	}

	/**
	 * (25) getCreateFinishedGrainDirection
	 * 
	 * @return JDFSpanFinishedGrainDirection the element
	 */
	public JDFSpanFinishedGrainDirection getCreateFinishedGrainDirection()
	{
		return (JDFSpanFinishedGrainDirection) getCreateElement_JDFElement(ElementName.FINISHEDGRAINDIRECTION, null, 0);
	}

	/**
	 * (29) append element FinishedGrainDirection
	 *
	 * @return JDFSpanFinishedGrainDirection the element
	 * @ if the element already exists
	 */
	public JDFSpanFinishedGrainDirection appendFinishedGrainDirection()
	{
		return (JDFSpanFinishedGrainDirection) appendElementN(ElementName.FINISHEDGRAINDIRECTION, 1, null);
	}

	/**
	 * (24) const get element Pages
	 *
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getPages()
	{
		return (JDFIntegerSpan) getElement(ElementName.PAGES, null, 0);
	}

	/**
	 * (25) getCreatePages
	 * 
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getCreatePages()
	{
		return (JDFIntegerSpan) getCreateElement_JDFElement(ElementName.PAGES, null, 0);
	}

	/**
	 * (29) append element Pages
	 *
	 * @return JDFIntegerSpan the element
	 * @ if the element already exists
	 */
	public JDFIntegerSpan appendPages()
	{
		return (JDFIntegerSpan) appendElementN(ElementName.PAGES, 1, null);
	}

	/**
	 * (24) const get element PageVariance
	 *
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getPageVariance()
	{
		return (JDFIntegerSpan) getElement(ElementName.PAGEVARIANCE, null, 0);
	}

	/**
	 * (25) getCreatePageVariance
	 * 
	 * @return JDFIntegerSpan the element
	 */
	public JDFIntegerSpan getCreatePageVariance()
	{
		return (JDFIntegerSpan) getCreateElement_JDFElement(ElementName.PAGEVARIANCE, null, 0);
	}

	/**
	 * (29) append element PageVariance
	 *
	 * @return JDFIntegerSpan the element
	 * @ if the element already exists
	 */
	public JDFIntegerSpan appendPageVariance()
	{
		return (JDFIntegerSpan) appendElementN(ElementName.PAGEVARIANCE, 1, null);
	}

	/**
	 * (24) const get element Layout
	 *
	 * @return JDFLayout the element
	 */
	public JDFLayout getLayout()
	{
		return (JDFLayout) getElement(ElementName.LAYOUT, null, 0);
	}

	/**
	 * (25) getCreateLayout
	 * 
	 * @return JDFLayout the element
	 */
	public JDFLayout getCreateLayout()
	{
		return (JDFLayout) getCreateElement_JDFElement(ElementName.LAYOUT, null, 0);
	}

	/**
	 * (29) append element Layout
	 *
	 * @return JDFLayout the element
	 * @ if the element already exists
	 */
	public JDFLayout appendLayout()
	{
		return (JDFLayout) appendElementN(ElementName.LAYOUT, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refLayout(final JDFLayout refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element SizePolicy
	 *
	 * @return JDFSpanSizePolicy the element
	 */
	public JDFSpanSizePolicy getSizePolicy()
	{
		return (JDFSpanSizePolicy) getElement(ElementName.SIZEPOLICY, null, 0);
	}

	/**
	 * (25) getCreateSizePolicy
	 * 
	 * @return JDFSpanSizePolicy the element
	 */
	public JDFSpanSizePolicy getCreateSizePolicy()
	{
		return (JDFSpanSizePolicy) getCreateElement_JDFElement(ElementName.SIZEPOLICY, null, 0);
	}

	/**
	 * (29) append element SizePolicy
	 *
	 * @return JDFSpanSizePolicy the element
	 * @ if the element already exists
	 */
	public JDFSpanSizePolicy appendSizePolicy()
	{
		return (JDFSpanSizePolicy) appendElementN(ElementName.SIZEPOLICY, 1, null);
	}

}
