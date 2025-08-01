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
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoPosition : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPosition extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ABSOLUTEBOX, 0x3333333111l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.MARGINBOTTOM, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.MARGINTOP, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.MARGINLEFT, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.MARGINRIGHT, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.ORIENTATION, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumOrientation.getEnum(0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.RELATIVEBOX, 0x3333333311l, AttributeInfo.EnumAttributeType.rectangle, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoPosition
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPosition(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPosition
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPosition(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPosition
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPosition(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for Orientation
	 */

	public enum EOrientation
	{
		Rotate0, Rotate90, Rotate180, Rotate270, Flip0, Flip90, Flip180, Flip270;

		public static EOrientation getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EOrientation.class, val, null);
		}
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

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AbsoluteBox ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AbsoluteBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAbsoluteBox(JDFRectangle value)
	{
		setAttribute(AttributeName.ABSOLUTEBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute AbsoluteBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getAbsoluteBox()
	{
		final String strAttrName = getAttribute(AttributeName.ABSOLUTEBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MarginBottom
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MarginBottom
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMarginBottom(double value)
	{
		setAttribute(AttributeName.MARGINBOTTOM, value, null);
	}

	/**
	 * (17) get double attribute MarginBottom
	 *
	 * @return double the value of the attribute
	 */
	public double getMarginBottom()
	{
		return getRealAttribute(AttributeName.MARGINBOTTOM, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MarginTop ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MarginTop
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMarginTop(double value)
	{
		setAttribute(AttributeName.MARGINTOP, value, null);
	}

	/**
	 * (17) get double attribute MarginTop
	 *
	 * @return double the value of the attribute
	 */
	public double getMarginTop()
	{
		return getRealAttribute(AttributeName.MARGINTOP, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MarginLeft ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MarginLeft
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMarginLeft(double value)
	{
		setAttribute(AttributeName.MARGINLEFT, value, null);
	}

	/**
	 * (17) get double attribute MarginLeft
	 *
	 * @return double the value of the attribute
	 */
	public double getMarginLeft()
	{
		return getRealAttribute(AttributeName.MARGINLEFT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MarginRight ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MarginRight
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMarginRight(double value)
	{
		setAttribute(AttributeName.MARGINRIGHT, value, null);
	}

	/**
	 * (17) get double attribute MarginRight
	 *
	 * @return double the value of the attribute
	 */
	public double getMarginRight()
	{
		return getRealAttribute(AttributeName.MARGINRIGHT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Orientation ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Orientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOrientation(EOrientation enumVar)
	{
		setAttribute(AttributeName.ORIENTATION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Orientation
	 *
	 * @return the value of the attribute
	 */
	public EOrientation getEOrientation()
	{
		return EOrientation.getEnum(getAttribute(AttributeName.ORIENTATION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Orientation ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Orientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
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
	 * --------------------------------------------------------------------- Methods for Attribute RelativeBox ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RelativeBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRelativeBox(JDFRectangle value)
	{
		setAttribute(AttributeName.RELATIVEBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute RelativeBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getRelativeBox()
	{
		final String strAttrName = getAttribute(AttributeName.RELATIVEBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

}
