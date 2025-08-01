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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFRefAnchor;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoPositionObj : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPositionObj extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ANCHOR, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration, EnumAnchor.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CTM, 0x3333331111l, AttributeInfo.EnumAttributeType.matrix, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.PAGERANGE, 0x3333331111l, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.POSITIONPOLICY, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration, EnumPositionPolicy.getEnum(0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.RELATIVESIZE, 0x3333331111l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.ROTATIONPOLICY, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration, EnumRotationPolicy.getEnum(0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.SIZE, 0x3333331111l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.SIZEPOLICY, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration, EnumSizePolicy.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.REFANCHOR, 0x6666661111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPositionObj
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPositionObj(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPositionObj
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPositionObj(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPositionObj
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPositionObj(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for Anchor
	 */

	public enum EAnchor
	{
		TopLeft, TopCenter, TopRight, CenterLeft, Center, CenterRight, BottomLeft, BottomCenter, BottomRight;

		public static EAnchor getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EAnchor.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Anchor
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumAnchor extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumAnchor(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumAnchor getEnum(String enumName)
		{
			return (EnumAnchor) getEnum(EnumAnchor.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumAnchor getEnum(int enumValue)
		{
			return (EnumAnchor) getEnum(EnumAnchor.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumAnchor.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumAnchor.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumAnchor.class);
		}

		/**  */
		public static final EnumAnchor TopLeft = new EnumAnchor("TopLeft");
		/**  */
		public static final EnumAnchor TopCenter = new EnumAnchor("TopCenter");
		/**  */
		public static final EnumAnchor TopRight = new EnumAnchor("TopRight");
		/**  */
		public static final EnumAnchor CenterLeft = new EnumAnchor("CenterLeft");
		/**  */
		public static final EnumAnchor Center = new EnumAnchor("Center");
		/**  */
		public static final EnumAnchor CenterRight = new EnumAnchor("CenterRight");
		/**  */
		public static final EnumAnchor BottomLeft = new EnumAnchor("BottomLeft");
		/**  */
		public static final EnumAnchor BottomCenter = new EnumAnchor("BottomCenter");
		/**  */
		public static final EnumAnchor BottomRight = new EnumAnchor("BottomRight");
	}

	/**
	 * Enumeration strings for PositionPolicy
	 */

	public enum EPositionPolicy
	{
		Exact, Free;

		public static EPositionPolicy getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EPositionPolicy.class, val, null);
		}
	}

	/**
	 * Enumeration strings for PositionPolicy
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPositionPolicy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPositionPolicy(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPositionPolicy getEnum(String enumName)
		{
			return (EnumPositionPolicy) getEnum(EnumPositionPolicy.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPositionPolicy getEnum(int enumValue)
		{
			return (EnumPositionPolicy) getEnum(EnumPositionPolicy.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPositionPolicy.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPositionPolicy.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPositionPolicy.class);
		}

		/**  */
		public static final EnumPositionPolicy Exact = new EnumPositionPolicy("Exact");
		/**  */
		public static final EnumPositionPolicy Free = new EnumPositionPolicy("Free");
	}

	/**
	 * Enumeration strings for RotationPolicy
	 */

	public enum ERotationPolicy
	{
		Exact, Free;

		public static ERotationPolicy getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ERotationPolicy.class, val, null);
		}
	}

	/**
	 * Enumeration strings for RotationPolicy
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumRotationPolicy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumRotationPolicy(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumRotationPolicy getEnum(String enumName)
		{
			return (EnumRotationPolicy) getEnum(EnumRotationPolicy.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumRotationPolicy getEnum(int enumValue)
		{
			return (EnumRotationPolicy) getEnum(EnumRotationPolicy.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumRotationPolicy.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumRotationPolicy.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumRotationPolicy.class);
		}

		/**  */
		public static final EnumRotationPolicy Exact = new EnumRotationPolicy("Exact");
		/**  */
		public static final EnumRotationPolicy Free = new EnumRotationPolicy("Free");
	}

	/**
	 * Enumeration strings for SizePolicy
	 */

	public enum ESizePolicy
	{
		Exact, Free;

		public static ESizePolicy getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ESizePolicy.class, val, null);
		}
	}

	/**
	 * Enumeration strings for SizePolicy
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSizePolicy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSizePolicy(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSizePolicy getEnum(String enumName)
		{
			return (EnumSizePolicy) getEnum(EnumSizePolicy.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSizePolicy getEnum(int enumValue)
		{
			return (EnumSizePolicy) getEnum(EnumSizePolicy.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSizePolicy.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSizePolicy.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSizePolicy.class);
		}

		/**  */
		public static final EnumSizePolicy Exact = new EnumSizePolicy("Exact");
		/**  */
		public static final EnumSizePolicy Free = new EnumSizePolicy("Free");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Anchor ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Anchor
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setAnchor(EAnchor enumVar)
	{
		setAttribute(AttributeName.ANCHOR, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Anchor
	 *
	 * @return the value of the attribute
	 */
	public EAnchor getEAnchor()
	{
		return EAnchor.getEnum(getAttribute(AttributeName.ANCHOR, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Anchor ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Anchor
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setAnchor(EnumAnchor enumVar)
	{
		setAttribute(AttributeName.ANCHOR, enumVar == null ? null : enumVar.getName(), null);
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
	 * --------------------------------------------------------------------- Methods for Attribute PageRange ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PageRange
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPageRange(JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.PAGERANGE, value, null);
	}

	/**
	 * (20) get JDFIntegerRangeList attribute PageRange
	 *
	 * @return JDFIntegerRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerRangeList
	 */
	public JDFIntegerRangeList getPageRange()
	{
		final String strAttrName = getAttribute(AttributeName.PAGERANGE, null, null);
		final JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PositionPolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PositionPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPositionPolicy(EPositionPolicy enumVar)
	{
		setAttribute(AttributeName.POSITIONPOLICY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute PositionPolicy
	 *
	 * @return the value of the attribute
	 */
	public EPositionPolicy getEPositionPolicy()
	{
		return EPositionPolicy.getEnum(getAttribute(AttributeName.POSITIONPOLICY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PositionPolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PositionPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setPositionPolicy(EnumPositionPolicy enumVar)
	{
		setAttribute(AttributeName.POSITIONPOLICY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute PositionPolicy
	 *
	 * @return the value of the attribute
	 */
	public EnumPositionPolicy getPositionPolicy()
	{
		return EnumPositionPolicy.getEnum(getAttribute(AttributeName.POSITIONPOLICY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RelativeSize
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RelativeSize
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRelativeSize(JDFXYPair value)
	{
		setAttribute(AttributeName.RELATIVESIZE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute RelativeSize
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getRelativeSize()
	{
		final String strAttrName = getAttribute(AttributeName.RELATIVESIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RotationPolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute RotationPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setRotationPolicy(ERotationPolicy enumVar)
	{
		setAttribute(AttributeName.ROTATIONPOLICY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute RotationPolicy
	 *
	 * @return the value of the attribute
	 */
	public ERotationPolicy getERotationPolicy()
	{
		return ERotationPolicy.getEnum(getAttribute(AttributeName.ROTATIONPOLICY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RotationPolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute RotationPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setRotationPolicy(EnumRotationPolicy enumVar)
	{
		setAttribute(AttributeName.ROTATIONPOLICY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute RotationPolicy
	 *
	 * @return the value of the attribute
	 */
	public EnumRotationPolicy getRotationPolicy()
	{
		return EnumRotationPolicy.getEnum(getAttribute(AttributeName.ROTATIONPOLICY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Size ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Size
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSize(JDFXYPair value)
	{
		setAttribute(AttributeName.SIZE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Size
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getSize()
	{
		final String strAttrName = getAttribute(AttributeName.SIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SizePolicy ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SizePolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSizePolicy(ESizePolicy enumVar)
	{
		setAttribute(AttributeName.SIZEPOLICY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute SizePolicy
	 *
	 * @return the value of the attribute
	 */
	public ESizePolicy getESizePolicy()
	{
		return ESizePolicy.getEnum(getAttribute(AttributeName.SIZEPOLICY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SizePolicy ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SizePolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setSizePolicy(EnumSizePolicy enumVar)
	{
		setAttribute(AttributeName.SIZEPOLICY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute SizePolicy
	 *
	 * @return the value of the attribute
	 */
	public EnumSizePolicy getSizePolicy()
	{
		return EnumSizePolicy.getEnum(getAttribute(AttributeName.SIZEPOLICY, null, null));
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

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
	 * (29) append element RefAnchor
	 *
	 * @return JDFRefAnchor the element @ if the element already exists
	 */
	public JDFRefAnchor appendRefAnchor()
	{
		return (JDFRefAnchor) appendElementN(ElementName.REFANCHOR, 1, null);
	}

}
