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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoHole : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoHole extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CENTER, 0x2222222222l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.EXTENT, 0x2222222222l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.REINFORCEMENT, 0x3333333333l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.SHAPE, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration, EnumShape.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoHole
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoHole(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoHole
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoHole(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoHole
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoHole(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for Shape
	 */

	public enum EShape
	{
		Eliptical, Round, Rectangular;

		public static EShape getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EShape.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Shape
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumShape extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumShape(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumShape getEnum(String enumName)
		{
			return (EnumShape) getEnum(EnumShape.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumShape getEnum(int enumValue)
		{
			return (EnumShape) getEnum(EnumShape.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumShape.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumShape.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumShape.class);
		}

		/**  */
		public static final EnumShape Eliptical = new EnumShape("Eliptical");
		/**  */
		public static final EnumShape Round = new EnumShape("Round");
		/**  */
		public static final EnumShape Rectangular = new EnumShape("Rectangular");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Center ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Center
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCenter(JDFXYPair value)
	{
		setAttribute(AttributeName.CENTER, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Center
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getCenter()
	{
		final String strAttrName = getAttribute(AttributeName.CENTER, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Extent ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Extent
	 *
	 * @param value the value to set the attribute to
	 */
	public void setExtent(JDFXYPair value)
	{
		setAttribute(AttributeName.EXTENT, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Extent
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getExtent()
	{
		final String strAttrName = getAttribute(AttributeName.EXTENT, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Reinforcement
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Reinforcement
	 *
	 * @param value the value to set the attribute to
	 */
	public void setReinforcement(String value)
	{
		setAttribute(AttributeName.REINFORCEMENT, value, null);
	}

	/**
	 * (23) get String attribute Reinforcement
	 *
	 * @return the value of the attribute
	 */
	public String getReinforcement()
	{
		return getAttribute(AttributeName.REINFORCEMENT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Shape ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Shape
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setShape(EShape enumVar)
	{
		setAttribute(AttributeName.SHAPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Shape
	 *
	 * @return the value of the attribute
	 */
	public EShape getEShape()
	{
		return EShape.getEnum(getAttribute(AttributeName.SHAPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Shape ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Shape
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setShape(EShape) based on java.lang.enum instead
	 */
	@Deprecated
	public void setShape(EnumShape enumVar)
	{
		setAttribute(AttributeName.SHAPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Shape
	 *
	 * @return the value of the attribute
	 * @deprecated use EShape getEShape() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumShape getShape()
	{
		return EnumShape.getEnum(getAttribute(AttributeName.SHAPE, null, null));
	}

}
