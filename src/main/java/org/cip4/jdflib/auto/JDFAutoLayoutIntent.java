/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
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

/**
 *****************************************************************************
 * class JDFAutoLayoutIntent : public JDFIntentResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoLayoutIntent extends JDFIntentResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.FOLIOCOUNT, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumFolioCount.getEnum(0), "Booklet");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.NUMBERUP, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, "1 1");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.FINISHEDPAGEORIENTATION, 0x4444444443l, AttributeInfo.EnumAttributeType.enumeration,
				EnumFinishedPageOrientation.getEnum(0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ORIENTATION, 0x3333111111l, AttributeInfo.EnumAttributeType.enumeration, EnumOrientation.getEnum(0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ROTATEPOLICY, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumRotatePolicy.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.SPREADTYPE, 0x3333111111l, AttributeInfo.EnumAttributeType.enumeration, EnumSpreadType.getEnum(0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.SIDES, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumSides.getEnum(0), null);
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
	protected JDFAutoLayoutIntent(CoreDocumentImpl myOwnerDocument, String qualifiedName)
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
	protected JDFAutoLayoutIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
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
	protected JDFAutoLayoutIntent(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for FolioCount
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumFolioCount extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumFolioCount(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumFolioCount getEnum(String enumName)
		{
			return (EnumFolioCount) getEnum(EnumFolioCount.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumFolioCount getEnum(int enumValue)
		{
			return (EnumFolioCount) getEnum(EnumFolioCount.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumFolioCount.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumFolioCount.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumFolioCount.class);
		}

		/**  */
		public static final EnumFolioCount Booklet = new EnumFolioCount("Booklet");
		/**  */
		public static final EnumFolioCount Flat = new EnumFolioCount("Flat");
	}

	/**
	 * Enumeration strings for FinishedPageOrientation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumFinishedPageOrientation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumFinishedPageOrientation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumFinishedPageOrientation getEnum(String enumName)
		{
			return (EnumFinishedPageOrientation) getEnum(EnumFinishedPageOrientation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumFinishedPageOrientation getEnum(int enumValue)
		{
			return (EnumFinishedPageOrientation) getEnum(EnumFinishedPageOrientation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumFinishedPageOrientation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumFinishedPageOrientation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumFinishedPageOrientation.class);
		}

		/**  */
		public static final EnumFinishedPageOrientation Portrait = new EnumFinishedPageOrientation("Portrait");
		/**  */
		public static final EnumFinishedPageOrientation Landscape = new EnumFinishedPageOrientation("Landscape");
	}

	/**
	 * Enumeration strings for Orientation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumOrientation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumOrientation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumOrientation getEnum(String enumName)
		{
			return (EnumOrientation) getEnum(EnumOrientation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumOrientation getEnum(int enumValue)
		{
			return (EnumOrientation) getEnum(EnumOrientation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumOrientation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumOrientation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumOrientation.class);
		}

		/**  */
		public static final EnumOrientation Rotate0 = new EnumOrientation("Rotate0");
		/**  */
		public static final EnumOrientation Rotate90 = new EnumOrientation("Rotate90");
		/**  */
		public static final EnumOrientation Rotate180 = new EnumOrientation("Rotate180");
		/**  */
		public static final EnumOrientation Rotate270 = new EnumOrientation("Rotate270");
		/**  */
		public static final EnumOrientation Flip0 = new EnumOrientation("Flip0");
		/**  */
		public static final EnumOrientation Flip90 = new EnumOrientation("Flip90");
		/**  */
		public static final EnumOrientation Flip180 = new EnumOrientation("Flip180");
		/**  */
		public static final EnumOrientation Flip270 = new EnumOrientation("Flip270");
	}

	/**
	 * Enumeration strings for RotatePolicy
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumRotatePolicy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumRotatePolicy(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumRotatePolicy getEnum(String enumName)
		{
			return (EnumRotatePolicy) getEnum(EnumRotatePolicy.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumRotatePolicy getEnum(int enumValue)
		{
			return (EnumRotatePolicy) getEnum(EnumRotatePolicy.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumRotatePolicy.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumRotatePolicy.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumRotatePolicy.class);
		}

		/**  */
		public static final EnumRotatePolicy NoRotate = new EnumRotatePolicy("NoRotate");
		/**  */
		public static final EnumRotatePolicy RotateOrthogonal = new EnumRotatePolicy("RotateOrthogonal");
		/**  */
		public static final EnumRotatePolicy RotateClockwise = new EnumRotatePolicy("RotateClockwise");
		/**  */
		public static final EnumRotatePolicy RotateCounterClockwise = new EnumRotatePolicy("RotateCounterClockwise");
	}

	/**
	 * Enumeration strings for SpreadType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSpreadType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSpreadType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSpreadType getEnum(String enumName)
		{
			return (EnumSpreadType) getEnum(EnumSpreadType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSpreadType getEnum(int enumValue)
		{
			return (EnumSpreadType) getEnum(EnumSpreadType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSpreadType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSpreadType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSpreadType.class);
		}

		/**  */
		public static final EnumSpreadType SinglePage = new EnumSpreadType("SinglePage");
		/**  */
		public static final EnumSpreadType Spread = new EnumSpreadType("Spread");
	}

	/**
	 * Enumeration strings for Sides
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSides extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSides(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSides getEnum(String enumName)
		{
			return (EnumSides) getEnum(EnumSides.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSides getEnum(int enumValue)
		{
			return (EnumSides) getEnum(EnumSides.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSides.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSides.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSides.class);
		}

		/**  */
		public static final EnumSides OneSided = new EnumSides("OneSided");
		/**  */
		public static final EnumSides OneSidedBack = new EnumSides("OneSidedBack");
		/**  */
		public static final EnumSides TwoSidedHeadToHead = new EnumSides("TwoSidedHeadToHead");
		/**  */
		public static final EnumSides TwoSidedHeadToFoot = new EnumSides("TwoSidedHeadToFoot");
		/**  */
		public static final EnumSides Unprinted = new EnumSides("Unprinted");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FolioCount ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FolioCount
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFolioCount(EnumFolioCount enumVar)
	{
		setAttribute(AttributeName.FOLIOCOUNT, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute NumberUp ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NumberUp
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNumberUp(JDFXYPair value)
	{
		setAttribute(AttributeName.NUMBERUP, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute NumberUp
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getNumberUp()
	{
		final String strAttrName = getAttribute(AttributeName.NUMBERUP, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FinishedPageOrientation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FinishedPageOrientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFinishedPageOrientation(EnumFinishedPageOrientation enumVar)
	{
		setAttribute(AttributeName.FINISHEDPAGEORIENTATION, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute Orientation ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Orientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOrientation(EnumOrientation enumVar)
	{
		setAttribute(AttributeName.ORIENTATION, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute RotatePolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute RotatePolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setRotatePolicy(EnumRotatePolicy enumVar)
	{
		setAttribute(AttributeName.ROTATEPOLICY, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute SpreadType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SpreadType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSpreadType(EnumSpreadType enumVar)
	{
		setAttribute(AttributeName.SPREADTYPE, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute Sides ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Sides
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSides(EnumSides enumVar)
	{
		setAttribute(AttributeName.SIDES, enumVar == null ? null : enumVar.getName(), null);
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
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (26) getCreateBleed
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFNumberSpan the element
	 */
	public JDFNumberSpan getCreateBleed(int iSkip)
	{
		return (JDFNumberSpan) getCreateElement_JDFElement(ElementName.BLEED, null, iSkip);
	}

	/**
	 * (27) const get element Bleed
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFNumberSpan the element default is getBleed(0)
	 */
	public JDFNumberSpan getBleed(int iSkip)
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
	 * @return JDFXYPairSpan the element @ if the element already exists
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
	 * @return JDFShapeSpan the element @ if the element already exists
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
	 * @return JDFSpanFinishedGrainDirection the element @ if the element already exists
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
	 * @return JDFIntegerSpan the element @ if the element already exists
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
	 * @return JDFIntegerSpan the element @ if the element already exists
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
	 * @return JDFLayout the element @ if the element already exists
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
	public void refLayout(JDFLayout refTarget)
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
	 * @return JDFSpanSizePolicy the element @ if the element already exists
	 */
	public JDFSpanSizePolicy appendSizePolicy()
	{
		return (JDFSpanSizePolicy) appendElementN(ElementName.SIZEPOLICY, 1, null);
	}

}
