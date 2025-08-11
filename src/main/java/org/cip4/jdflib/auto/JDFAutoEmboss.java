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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFTool;
import org.cip4.jdflib.resource.process.JDFIdentificationField;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoEmboss : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoEmboss extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DIRECTION, 0x2222222221l, AttributeInfo.EnumAttributeType.enumeration, EnumDirection.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.EDGEANGLE, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.EDGESHAPE, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumEdgeShape.getEnum(0), "Rounded");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.EMBOSSINGTYPE, 0x2222222221l, AttributeInfo.EnumAttributeType.enumeration, EnumEmbossingType.getEnum(0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.FACE, 0x3333111111l, AttributeInfo.EnumAttributeType.enumeration, EnumFace.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.HEIGHT, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.IMAGESIZE, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.LEVEL, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumLevel.getEnum(0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.POSITION, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPair, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.IDENTIFICATIONFIELD, 0x3333333331l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.MEDIA, 0x6666666661l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.TOOL, 0x6666666661l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoEmboss
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoEmboss(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoEmboss
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoEmboss(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoEmboss
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoEmboss(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for Direction
	 */

	public enum EDirection
	{
		Both, Flat, Raised, Depressed;

		public static EDirection getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EDirection.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Direction
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumDirection extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumDirection(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumDirection getEnum(String enumName)
		{
			return (EnumDirection) getEnum(EnumDirection.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumDirection getEnum(int enumValue)
		{
			return (EnumDirection) getEnum(EnumDirection.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDirection.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDirection.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDirection.class);
		}

		/**  */
		public static final EnumDirection Both = new EnumDirection("Both");
		/**  */
		public static final EnumDirection Flat = new EnumDirection("Flat");
		/**  */
		public static final EnumDirection Raised = new EnumDirection("Raised");
		/**  */
		public static final EnumDirection Depressed = new EnumDirection("Depressed");
	}

	/**
	 * Enumeration strings for EdgeShape
	 */

	public enum EEdgeShape
	{
		Beveled, Rounded;

		public static EEdgeShape getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EEdgeShape.class, val, EEdgeShape.Rounded);
		}
	}

	/**
	 * Enumeration strings for EdgeShape
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumEdgeShape extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumEdgeShape(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumEdgeShape getEnum(String enumName)
		{
			return (EnumEdgeShape) getEnum(EnumEdgeShape.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumEdgeShape getEnum(int enumValue)
		{
			return (EnumEdgeShape) getEnum(EnumEdgeShape.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumEdgeShape.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumEdgeShape.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumEdgeShape.class);
		}

		/**  */
		public static final EnumEdgeShape Beveled = new EnumEdgeShape("Beveled");
		/**  */
		public static final EnumEdgeShape Rounded = new EnumEdgeShape("Rounded");
	}

	/**
	 * Enumeration strings for EmbossingType
	 */

	public enum EEmbossingType
	{
		BlindEmbossing, Braille, EmbossedFinish, FoilEmbossing, FoilStamping, RegisteredEmbossing;

		public static EEmbossingType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EEmbossingType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for EmbossingType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumEmbossingType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumEmbossingType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumEmbossingType getEnum(String enumName)
		{
			return (EnumEmbossingType) getEnum(EnumEmbossingType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumEmbossingType getEnum(int enumValue)
		{
			return (EnumEmbossingType) getEnum(EnumEmbossingType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumEmbossingType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumEmbossingType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumEmbossingType.class);
		}

		/**  */
		public static final EnumEmbossingType BlindEmbossing = new EnumEmbossingType("BlindEmbossing");
		/**  */
		public static final EnumEmbossingType Braille = new EnumEmbossingType("Braille");
		/**  */
		public static final EnumEmbossingType EmbossedFinish = new EnumEmbossingType("EmbossedFinish");
		/**  */
		public static final EnumEmbossingType FoilEmbossing = new EnumEmbossingType("FoilEmbossing");
		/**  */
		public static final EnumEmbossingType FoilStamping = new EnumEmbossingType("FoilStamping");
		/**  */
		public static final EnumEmbossingType RegisteredEmbossing = new EnumEmbossingType("RegisteredEmbossing");
	}

	/**
	 * Enumeration strings for Face
	 */

	public enum EFace
	{
		Top, Bottom, Left, Right, Front, Back;

		public static EFace getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EFace.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Face
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumFace extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumFace(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumFace getEnum(String enumName)
		{
			return (EnumFace) getEnum(EnumFace.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumFace getEnum(int enumValue)
		{
			return (EnumFace) getEnum(EnumFace.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumFace.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumFace.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumFace.class);
		}

		/**  */
		public static final EnumFace Top = new EnumFace("Top");
		/**  */
		public static final EnumFace Bottom = new EnumFace("Bottom");
		/**  */
		public static final EnumFace Left = new EnumFace("Left");
		/**  */
		public static final EnumFace Right = new EnumFace("Right");
		/**  */
		public static final EnumFace Front = new EnumFace("Front");
		/**  */
		public static final EnumFace Back = new EnumFace("Back");
	}

	/**
	 * Enumeration strings for Level
	 */

	public enum ELevel
	{
		SingleLevel, MultiLevel, Sculpted;

		public static ELevel getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ELevel.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Level
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumLevel extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumLevel(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumLevel getEnum(String enumName)
		{
			return (EnumLevel) getEnum(EnumLevel.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumLevel getEnum(int enumValue)
		{
			return (EnumLevel) getEnum(EnumLevel.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumLevel.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumLevel.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumLevel.class);
		}

		/**  */
		public static final EnumLevel SingleLevel = new EnumLevel("SingleLevel");
		/**  */
		public static final EnumLevel MultiLevel = new EnumLevel("MultiLevel");
		/**  */
		public static final EnumLevel Sculpted = new EnumLevel("Sculpted");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Direction ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Direction
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDirection(EDirection enumVar)
	{
		setAttribute(AttributeName.DIRECTION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Direction
	 *
	 * @return the value of the attribute
	 */
	public EDirection getEDirection()
	{
		return EDirection.getEnum(getAttribute(AttributeName.DIRECTION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Direction ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Direction
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setDirection(EDirection) based on java.lang.enum instead
	 */
	@Deprecated
	public void setDirection(EnumDirection enumVar)
	{
		setAttribute(AttributeName.DIRECTION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Direction
	 *
	 * @return the value of the attribute
	 * @deprecated use EDirection getEDirection() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumDirection getDirection()
	{
		return EnumDirection.getEnum(getAttribute(AttributeName.DIRECTION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute EdgeAngle ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EdgeAngle
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEdgeAngle(double value)
	{
		setAttribute(AttributeName.EDGEANGLE, value, null);
	}

	/**
	 * (17) get double attribute EdgeAngle
	 *
	 * @return double the value of the attribute
	 */
	public double getEdgeAngle()
	{
		return getRealAttribute(AttributeName.EDGEANGLE, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute EdgeShape ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute EdgeShape
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setEdgeShape(EEdgeShape enumVar)
	{
		setAttribute(AttributeName.EDGESHAPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute EdgeShape
	 *
	 * @return the value of the attribute
	 */
	public EEdgeShape getEEdgeShape()
	{
		return EEdgeShape.getEnum(getAttribute(AttributeName.EDGESHAPE, null, "Rounded"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute EdgeShape ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute EdgeShape
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setEdgeShape(EEdgeShape) based on java.lang.enum instead
	 */
	@Deprecated
	public void setEdgeShape(EnumEdgeShape enumVar)
	{
		setAttribute(AttributeName.EDGESHAPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute EdgeShape
	 *
	 * @return the value of the attribute
	 * @deprecated use EEdgeShape getEEdgeShape() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumEdgeShape getEdgeShape()
	{
		return EnumEdgeShape.getEnum(getAttribute(AttributeName.EDGESHAPE, null, "Rounded"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute EmbossingType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute EmbossingType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setEmbossingType(EEmbossingType enumVar)
	{
		setAttribute(AttributeName.EMBOSSINGTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute EmbossingType
	 *
	 * @return the value of the attribute
	 */
	public EEmbossingType getEEmbossingType()
	{
		return EEmbossingType.getEnum(getAttribute(AttributeName.EMBOSSINGTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute EmbossingType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute EmbossingType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setEmbossingType(EEmbossingType) based on java.lang.enum instead
	 */
	@Deprecated
	public void setEmbossingType(EnumEmbossingType enumVar)
	{
		setAttribute(AttributeName.EMBOSSINGTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute EmbossingType
	 *
	 * @return the value of the attribute
	 * @deprecated use EEmbossingType getEEmbossingType() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumEmbossingType getEmbossingType()
	{
		return EnumEmbossingType.getEnum(getAttribute(AttributeName.EMBOSSINGTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Face ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Face
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFace(EFace enumVar)
	{
		setAttribute(AttributeName.FACE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Face
	 *
	 * @return the value of the attribute
	 */
	public EFace getEFace()
	{
		return EFace.getEnum(getAttribute(AttributeName.FACE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Face ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Face
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setFace(EFace) based on java.lang.enum instead
	 */
	@Deprecated
	public void setFace(EnumFace enumVar)
	{
		setAttribute(AttributeName.FACE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Face
	 *
	 * @return the value of the attribute
	 * @deprecated use EFace getEFace() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumFace getFace()
	{
		return EnumFace.getEnum(getAttribute(AttributeName.FACE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Height ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Height
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHeight(double value)
	{
		setAttribute(AttributeName.HEIGHT, value, null);
	}

	/**
	 * (17) get double attribute Height
	 *
	 * @return double the value of the attribute
	 */
	public double getHeight()
	{
		return getRealAttribute(AttributeName.HEIGHT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ImageSize ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ImageSize
	 *
	 * @param value the value to set the attribute to
	 */
	public void setImageSize(JDFXYPair value)
	{
		setAttribute(AttributeName.IMAGESIZE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute ImageSize
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getImageSize()
	{
		final String strAttrName = getAttribute(AttributeName.IMAGESIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Level ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Level
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setLevel(ELevel enumVar)
	{
		setAttribute(AttributeName.LEVEL, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Level
	 *
	 * @return the value of the attribute
	 */
	public ELevel getELevel()
	{
		return ELevel.getEnum(getAttribute(AttributeName.LEVEL, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Level ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Level
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setLevel(ELevel) based on java.lang.enum instead
	 */
	@Deprecated
	public void setLevel(EnumLevel enumVar)
	{
		setAttribute(AttributeName.LEVEL, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Level
	 *
	 * @return the value of the attribute
	 * @deprecated use ELevel getELevel() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumLevel getLevel()
	{
		return EnumLevel.getEnum(getAttribute(AttributeName.LEVEL, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Position ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Position
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPosition(JDFXYPair value)
	{
		setAttribute(AttributeName.POSITION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Position
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getPosition()
	{
		final String strAttrName = getAttribute(AttributeName.POSITION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element IdentificationField
	 *
	 * @return JDFIdentificationField the element
	 */
	public JDFIdentificationField getIdentificationField()
	{
		return (JDFIdentificationField) getElement(ElementName.IDENTIFICATIONFIELD, null, 0);
	}

	/**
	 * (25) getCreateIdentificationField
	 * 
	 * @return JDFIdentificationField the element
	 */
	public JDFIdentificationField getCreateIdentificationField()
	{
		return (JDFIdentificationField) getCreateElement_JDFElement(ElementName.IDENTIFICATIONFIELD, null, 0);
	}

	/**
	 * (26) getCreateIdentificationField
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFIdentificationField the element
	 */
	public JDFIdentificationField getCreateIdentificationField(int iSkip)
	{
		return (JDFIdentificationField) getCreateElement_JDFElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
	}

	/**
	 * (27) const get element IdentificationField
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFIdentificationField the element default is getIdentificationField(0)
	 */
	public JDFIdentificationField getIdentificationField(int iSkip)
	{
		return (JDFIdentificationField) getElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
	}

	/**
	 * Get all IdentificationField from the current element
	 * 
	 * @return Collection<JDFIdentificationField>, null if none are available
	 */
	public Collection<JDFIdentificationField> getAllIdentificationField()
	{
		return getChildArrayByClass(JDFIdentificationField.class, false, 0);
	}

	/**
	 * (30) append element IdentificationField
	 *
	 * @return JDFIdentificationField the element
	 */
	public JDFIdentificationField appendIdentificationField()
	{
		return (JDFIdentificationField) appendElement(ElementName.IDENTIFICATIONFIELD, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refIdentificationField(JDFIdentificationField refTarget)
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
		return (JDFMedia) getCreateElement_JDFElement(ElementName.MEDIA, null, 0);
	}

	/**
	 * (29) append element Media
	 *
	 * @return JDFMedia the element @ if the element already exists
	 */
	public JDFMedia appendMedia()
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
	 * (24) const get element Tool
	 *
	 * @return JDFTool the element
	 */
	public JDFTool getTool()
	{
		return (JDFTool) getElement(ElementName.TOOL, null, 0);
	}

	/**
	 * (25) getCreateTool
	 * 
	 * @return JDFTool the element
	 */
	public JDFTool getCreateTool()
	{
		return (JDFTool) getCreateElement_JDFElement(ElementName.TOOL, null, 0);
	}

	/**
	 * (29) append element Tool
	 *
	 * @return JDFTool the element @ if the element already exists
	 */
	public JDFTool appendTool()
	{
		return (JDFTool) appendElementN(ElementName.TOOL, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refTool(JDFTool refTarget)
	{
		refElement(refTarget);
	}

}
