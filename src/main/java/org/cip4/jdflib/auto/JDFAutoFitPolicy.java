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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoFitPolicy : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoFitPolicy extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CLIPOFFSET, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.GUTTERPOLICY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumGutterPolicy.getEnum(0), "Fixed");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.MINGUTTER, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ROTATEPOLICY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumRotatePolicy.getEnum(0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.SIZEPOLICY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumSizePolicy.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoFitPolicy
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoFitPolicy(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFitPolicy
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoFitPolicy(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFitPolicy
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoFitPolicy(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for GutterPolicy
	 */

	public enum EGutterPolicy
	{
		Distribute, Fixed;

		public static EGutterPolicy getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EGutterPolicy.class, val, EGutterPolicy.Fixed);
		}
	}

	/**
	 * Enumeration strings for GutterPolicy
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumGutterPolicy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumGutterPolicy(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumGutterPolicy getEnum(String enumName)
		{
			return (EnumGutterPolicy) getEnum(EnumGutterPolicy.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumGutterPolicy getEnum(int enumValue)
		{
			return (EnumGutterPolicy) getEnum(EnumGutterPolicy.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumGutterPolicy.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumGutterPolicy.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumGutterPolicy.class);
		}

		/**  */
		public static final EnumGutterPolicy Distribute = new EnumGutterPolicy("Distribute");
		/**  */
		public static final EnumGutterPolicy Fixed = new EnumGutterPolicy("Fixed");
	}

	/**
	 * Enumeration strings for RotatePolicy
	 */

	public enum ERotatePolicy
	{
		NoRotate, RotateOrthogonal, RotateClockwise, RotateCounterClockwise;

		public static ERotatePolicy getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ERotatePolicy.class, val, null);
		}
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
	 * Enumeration strings for SizePolicy
	 */

	public enum ESizePolicy
	{
		ClipToMaxPage, Abort, FitToPage, ReduceToFit, Tile;

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
		public static final EnumSizePolicy ClipToMaxPage = new EnumSizePolicy("ClipToMaxPage");
		/**  */
		public static final EnumSizePolicy Abort = new EnumSizePolicy("Abort");
		/**  */
		public static final EnumSizePolicy FitToPage = new EnumSizePolicy("FitToPage");
		/**  */
		public static final EnumSizePolicy ReduceToFit = new EnumSizePolicy("ReduceToFit");
		/**  */
		public static final EnumSizePolicy Tile = new EnumSizePolicy("Tile");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ClipOffset ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ClipOffset
	 *
	 * @param value the value to set the attribute to
	 */
	public void setClipOffset(JDFXYPair value)
	{
		setAttribute(AttributeName.CLIPOFFSET, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute ClipOffset
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getClipOffset()
	{
		final String strAttrName = getAttribute(AttributeName.CLIPOFFSET, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute GutterPolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute GutterPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setGutterPolicy(EGutterPolicy enumVar)
	{
		setAttribute(AttributeName.GUTTERPOLICY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute GutterPolicy
	 *
	 * @return the value of the attribute
	 */
	public EGutterPolicy getEGutterPolicy()
	{
		return EGutterPolicy.getEnum(getAttribute(AttributeName.GUTTERPOLICY, null, "Fixed"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute GutterPolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute GutterPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setGutterPolicy(EGutterPolicy) based on java.lang.enum instead
	 */
	@Deprecated
	public void setGutterPolicy(EnumGutterPolicy enumVar)
	{
		setAttribute(AttributeName.GUTTERPOLICY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute GutterPolicy
	 *
	 * @return the value of the attribute
	 * @deprecated use EGutterPolicy getEGutterPolicy() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumGutterPolicy getGutterPolicy()
	{
		return EnumGutterPolicy.getEnum(getAttribute(AttributeName.GUTTERPOLICY, null, "Fixed"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MinGutter ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MinGutter
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMinGutter(JDFXYPair value)
	{
		setAttribute(AttributeName.MINGUTTER, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute MinGutter
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getMinGutter()
	{
		final String strAttrName = getAttribute(AttributeName.MINGUTTER, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
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
	public void setRotatePolicy(ERotatePolicy enumVar)
	{
		setAttribute(AttributeName.ROTATEPOLICY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute RotatePolicy
	 *
	 * @return the value of the attribute
	 */
	public ERotatePolicy getERotatePolicy()
	{
		return ERotatePolicy.getEnum(getAttribute(AttributeName.ROTATEPOLICY, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RotatePolicy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute RotatePolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setRotatePolicy(ERotatePolicy) based on java.lang.enum instead
	 */
	@Deprecated
	public void setRotatePolicy(EnumRotatePolicy enumVar)
	{
		setAttribute(AttributeName.ROTATEPOLICY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute RotatePolicy
	 *
	 * @return the value of the attribute
	 * @deprecated use ERotatePolicy getERotatePolicy() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumRotatePolicy getRotatePolicy()
	{
		return EnumRotatePolicy.getEnum(getAttribute(AttributeName.ROTATEPOLICY, null, null));
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
	 * @deprecated use setSizePolicy(ESizePolicy) based on java.lang.enum instead
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
	 * @deprecated use ESizePolicy getESizePolicy() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumSizePolicy getSizePolicy()
	{
		return EnumSizePolicy.getEnum(getAttribute(AttributeName.SIZEPOLICY, null, null));
	}

}
