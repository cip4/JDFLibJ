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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoStitchingParams : public JDFResource
 */

public abstract class JDFAutoStitchingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[13];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.STITCHORIGIN, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration, EnumStitchOrigin.getEnum(0),
				"UntrimmedJogSide");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ANGLE, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.NUMBEROFSTITCHES, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.OFFSET, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.REFERENCEEDGE, 0x4444444431l, AttributeInfo.EnumAttributeType.enumeration,
				EnumReferenceEdge.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.STAPLESHAPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumStapleShape.getEnum(0),
				null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.STITCHFROMFRONT, 0x4444444433l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.STITCHPOSITIONS, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.STITCHTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumStitchType.getEnum(0),
				null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.STITCHWIDTH, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.TIGHTBACKING, 0x3333311111l, AttributeInfo.EnumAttributeType.enumeration, EnumTightBacking.getEnum(0),
				null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.WIREGAUGE, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.WIREBRAND, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoStitchingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoStitchingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoStitchingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoStitchingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoStitchingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoStitchingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		boolean bRet = super.init();
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

	/**
	 * Enumeration strings for StitchOrigin
	 */

	public enum EStitchOrigin
	{
		TrimBoxCenter, TrimBoxJogSide, UntrimmedJogSide;

		public static EStitchOrigin getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EStitchOrigin.class, val, EStitchOrigin.UntrimmedJogSide);
		}
	}

	/**
	 * Enumeration strings for StitchOrigin
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumStitchOrigin extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumStitchOrigin(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumStitchOrigin getEnum(String enumName)
		{
			return (EnumStitchOrigin) getEnum(EnumStitchOrigin.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumStitchOrigin getEnum(int enumValue)
		{
			return (EnumStitchOrigin) getEnum(EnumStitchOrigin.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumStitchOrigin.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumStitchOrigin.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumStitchOrigin.class);
		}

		/**  */
		public static final EnumStitchOrigin TrimBoxCenter = new EnumStitchOrigin("TrimBoxCenter");
		/**  */
		public static final EnumStitchOrigin TrimBoxJogSide = new EnumStitchOrigin("TrimBoxJogSide");
		/**  */
		public static final EnumStitchOrigin UntrimmedJogSide = new EnumStitchOrigin("UntrimmedJogSide");
	}

	/**
	 * Enumeration strings for ReferenceEdge
	 */

	public enum EReferenceEdge
	{
		Top, Left, Right, Bottom;

		public static EReferenceEdge getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EReferenceEdge.class, val, null);
		}
	}

	/**
	 * Enumeration strings for ReferenceEdge
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumReferenceEdge extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumReferenceEdge(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumReferenceEdge getEnum(String enumName)
		{
			return (EnumReferenceEdge) getEnum(EnumReferenceEdge.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumReferenceEdge getEnum(int enumValue)
		{
			return (EnumReferenceEdge) getEnum(EnumReferenceEdge.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumReferenceEdge.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumReferenceEdge.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumReferenceEdge.class);
		}

		/**  */
		public static final EnumReferenceEdge Top = new EnumReferenceEdge("Top");
		/**  */
		public static final EnumReferenceEdge Left = new EnumReferenceEdge("Left");
		/**  */
		public static final EnumReferenceEdge Right = new EnumReferenceEdge("Right");
		/**  */
		public static final EnumReferenceEdge Bottom = new EnumReferenceEdge("Bottom");
	}

	/**
	 * Enumeration strings for StapleShape
	 */

	public enum EStapleShape
	{
		Crown, Overlap, Butted, ClinchOut, Eyelet;

		public static EStapleShape getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EStapleShape.class, val, null);
		}
	}

	/**
	 * Enumeration strings for StapleShape
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumStapleShape extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumStapleShape(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumStapleShape getEnum(String enumName)
		{
			return (EnumStapleShape) getEnum(EnumStapleShape.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumStapleShape getEnum(int enumValue)
		{
			return (EnumStapleShape) getEnum(EnumStapleShape.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumStapleShape.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumStapleShape.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumStapleShape.class);
		}

		/**  */
		public static final EnumStapleShape Crown = new EnumStapleShape("Crown");
		/**  */
		public static final EnumStapleShape Overlap = new EnumStapleShape("Overlap");
		/**  */
		public static final EnumStapleShape Butted = new EnumStapleShape("Butted");
		/**  */
		public static final EnumStapleShape ClinchOut = new EnumStapleShape("ClinchOut");
		/**  */
		public static final EnumStapleShape Eyelet = new EnumStapleShape("Eyelet");
	}

	/**
	 * Enumeration strings for StitchType
	 */

	public enum EStitchType
	{
		Saddle, Side, Corner;

		public static EStitchType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EStitchType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for StitchType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumStitchType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumStitchType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumStitchType getEnum(String enumName)
		{
			return (EnumStitchType) getEnum(EnumStitchType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumStitchType getEnum(int enumValue)
		{
			return (EnumStitchType) getEnum(EnumStitchType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumStitchType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumStitchType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumStitchType.class);
		}

		/**  */
		public static final EnumStitchType Saddle = new EnumStitchType("Saddle");
		/**  */
		public static final EnumStitchType Side = new EnumStitchType("Side");
		/**  */
		public static final EnumStitchType Corner = new EnumStitchType("Corner");
	}

	/**
	 * Enumeration strings for TightBacking
	 */

	public enum ETightBacking
	{
		Flat, Pressure;

		public static ETightBacking getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ETightBacking.class, val, null);
		}
	}

	/**
	 * Enumeration strings for TightBacking
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumTightBacking extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumTightBacking(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumTightBacking getEnum(String enumName)
		{
			return (EnumTightBacking) getEnum(EnumTightBacking.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumTightBacking getEnum(int enumValue)
		{
			return (EnumTightBacking) getEnum(EnumTightBacking.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumTightBacking.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumTightBacking.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumTightBacking.class);
		}

		/**  */
		public static final EnumTightBacking Flat = new EnumTightBacking("Flat");
		/**  */
		public static final EnumTightBacking Pressure = new EnumTightBacking("Pressure");
	}

	/*
	 * ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StitchOrigin
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute StitchOrigin
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setStitchOrigin(EStitchOrigin enumVar)
	{
		setAttribute(AttributeName.STITCHORIGIN, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute StitchOrigin
	 *
	 * @return the value of the attribute
	 */
	public EStitchOrigin getEStitchOrigin()
	{
		return EStitchOrigin.getEnum(getAttribute(AttributeName.STITCHORIGIN, null, "UntrimmedJogSide"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StitchOrigin
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute StitchOrigin
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetStitchOrigin(EStitchOrigin) based on java.lang.enum instead
	 */
	@Deprecated
	public void setStitchOrigin(EnumStitchOrigin enumVar)
	{
		setAttribute(AttributeName.STITCHORIGIN, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute StitchOrigin
	 *
	 * @return the value of the attribute
	 * @deprecated use EStitchOrigin GetEStitchOrigin() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumStitchOrigin getStitchOrigin()
	{
		return EnumStitchOrigin.getEnum(getAttribute(AttributeName.STITCHORIGIN, null, "UntrimmedJogSide"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Angle
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Angle
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAngle(double value)
	{
		setAttribute(AttributeName.ANGLE, value, null);
	}

	/**
	 * (17) get double attribute Angle
	 *
	 * @return double the value of the attribute
	 */
	public double getAngle()
	{
		return getRealAttribute(AttributeName.ANGLE, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NumberOfStitches
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NumberOfStitches
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNumberOfStitches(int value)
	{
		setAttribute(AttributeName.NUMBEROFSTITCHES, value, null);
	}

	/**
	 * (15) get int attribute NumberOfStitches
	 *
	 * @return int the value of the attribute
	 */
	public int getNumberOfStitches()
	{
		return getIntAttribute(AttributeName.NUMBEROFSTITCHES, null, 0);
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
	public void setOffset(double value)
	{
		setAttribute(AttributeName.OFFSET, value, null);
	}

	/**
	 * (17) get double attribute Offset
	 *
	 * @return double the value of the attribute
	 */
	public double getOffset()
	{
		return getRealAttribute(AttributeName.OFFSET, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ReferenceEdge
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ReferenceEdge
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setReferenceEdge(EReferenceEdge enumVar)
	{
		setAttribute(AttributeName.REFERENCEEDGE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ReferenceEdge
	 *
	 * @return the value of the attribute
	 */
	public EReferenceEdge getEReferenceEdge()
	{
		return EReferenceEdge.getEnum(getAttribute(AttributeName.REFERENCEEDGE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ReferenceEdge
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ReferenceEdge
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetReferenceEdge(EReferenceEdge) based on java.lang.enum instead
	 */
	@Deprecated
	public void setReferenceEdge(EnumReferenceEdge enumVar)
	{
		setAttribute(AttributeName.REFERENCEEDGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ReferenceEdge
	 *
	 * @return the value of the attribute
	 * @deprecated use EReferenceEdge GetEReferenceEdge() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumReferenceEdge getReferenceEdge()
	{
		return EnumReferenceEdge.getEnum(getAttribute(AttributeName.REFERENCEEDGE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StapleShape
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute StapleShape
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setStapleShape(EStapleShape enumVar)
	{
		setAttribute(AttributeName.STAPLESHAPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute StapleShape
	 *
	 * @return the value of the attribute
	 */
	public EStapleShape getEStapleShape()
	{
		return EStapleShape.getEnum(getAttribute(AttributeName.STAPLESHAPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StapleShape
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute StapleShape
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetStapleShape(EStapleShape) based on java.lang.enum instead
	 */
	@Deprecated
	public void setStapleShape(EnumStapleShape enumVar)
	{
		setAttribute(AttributeName.STAPLESHAPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute StapleShape
	 *
	 * @return the value of the attribute
	 * @deprecated use EStapleShape GetEStapleShape() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumStapleShape getStapleShape()
	{
		return EnumStapleShape.getEnum(getAttribute(AttributeName.STAPLESHAPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StitchFromFront
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StitchFromFront
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStitchFromFront(boolean value)
	{
		setAttribute(AttributeName.STITCHFROMFRONT, value, null);
	}

	/**
	 * (18) get boolean attribute StitchFromFront
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getStitchFromFront()
	{
		return getBoolAttribute(AttributeName.STITCHFROMFRONT, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StitchPositions
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StitchPositions
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStitchPositions(JDFNumberList value)
	{
		setAttribute(AttributeName.STITCHPOSITIONS, value, null);
	}

	/**
	 * (20) get JDFNumberList attribute StitchPositions
	 *
	 * @return JDFNumberList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFNumberList
	 */
	public JDFNumberList getStitchPositions()
	{
		String strAttrName = getAttribute(AttributeName.STITCHPOSITIONS, null, null);
		JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StitchType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute StitchType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setStitchType(EStitchType enumVar)
	{
		setAttribute(AttributeName.STITCHTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute StitchType
	 *
	 * @return the value of the attribute
	 */
	public EStitchType getEStitchType()
	{
		return EStitchType.getEnum(getAttribute(AttributeName.STITCHTYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StitchType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute StitchType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetStitchType(EStitchType) based on java.lang.enum instead
	 */
	@Deprecated
	public void setStitchType(EnumStitchType enumVar)
	{
		setAttribute(AttributeName.STITCHTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute StitchType
	 *
	 * @return the value of the attribute
	 * @deprecated use EStitchType GetEStitchType() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumStitchType getStitchType()
	{
		return EnumStitchType.getEnum(getAttribute(AttributeName.STITCHTYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StitchWidth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StitchWidth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStitchWidth(double value)
	{
		setAttribute(AttributeName.STITCHWIDTH, value, null);
	}

	/**
	 * (17) get double attribute StitchWidth
	 *
	 * @return double the value of the attribute
	 */
	public double getStitchWidth()
	{
		return getRealAttribute(AttributeName.STITCHWIDTH, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute TightBacking
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute TightBacking
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setTightBacking(ETightBacking enumVar)
	{
		setAttribute(AttributeName.TIGHTBACKING, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute TightBacking
	 *
	 * @return the value of the attribute
	 */
	public ETightBacking getETightBacking()
	{
		return ETightBacking.getEnum(getAttribute(AttributeName.TIGHTBACKING, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute TightBacking
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute TightBacking
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetTightBacking(ETightBacking) based on java.lang.enum instead
	 */
	@Deprecated
	public void setTightBacking(EnumTightBacking enumVar)
	{
		setAttribute(AttributeName.TIGHTBACKING, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute TightBacking
	 *
	 * @return the value of the attribute
	 * @deprecated use ETightBacking GetETightBacking() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumTightBacking getTightBacking()
	{
		return EnumTightBacking.getEnum(getAttribute(AttributeName.TIGHTBACKING, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute WireGauge
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WireGauge
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWireGauge(double value)
	{
		setAttribute(AttributeName.WIREGAUGE, value, null);
	}

	/**
	 * (17) get double attribute WireGauge
	 *
	 * @return double the value of the attribute
	 */
	public double getWireGauge()
	{
		return getRealAttribute(AttributeName.WIREGAUGE, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute WireBrand
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WireBrand
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWireBrand(String value)
	{
		setAttribute(AttributeName.WIREBRAND, value, null);
	}

	/**
	 * (23) get String attribute WireBrand
	 *
	 * @return the value of the attribute
	 */
	public String getWireBrand()
	{
		return getAttribute(AttributeName.WIREBRAND, null, JDFCoreConstants.EMPTYSTRING);
	}

}
