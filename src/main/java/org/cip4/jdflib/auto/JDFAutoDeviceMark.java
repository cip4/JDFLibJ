/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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

/**
*****************************************************************************
class JDFAutoDeviceMark : public JDFElement

*****************************************************************************
*/

public abstract class JDFAutoDeviceMark extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ANCHOR, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumAnchor.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.FONT, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.FONTSIZE, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.HORIZONTALFITPOLICY, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumHorizontalFitPolicy.getEnum(0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.MARKJUSTIFICATION, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumMarkJustification.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.MARKOFFSET, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MARKORIENTATION, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumMarkOrientation.getEnum(0), null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.MARKPOSITION, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumMarkPosition.getEnum(0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.VERTICALFITPOLICY, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumVerticalFitPolicy.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoDeviceMark
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDeviceMark(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDeviceMark
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDeviceMark(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDeviceMark
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDeviceMark(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return  the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoDeviceMark[  --> " + super.toString() + " ]";
	}

	/**
	* Enumeration strings for Anchor
	*/

	public static class EnumAnchor extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumAnchor(String name)
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

		public static final EnumAnchor TopLeft = new EnumAnchor("TopLeft");
		public static final EnumAnchor TopCenter = new EnumAnchor("TopCenter");
		public static final EnumAnchor TopRight = new EnumAnchor("TopRight");
		public static final EnumAnchor CenterLeft = new EnumAnchor("CenterLeft");
		public static final EnumAnchor Center = new EnumAnchor("Center");
		public static final EnumAnchor CenterRight = new EnumAnchor("CenterRight");
		public static final EnumAnchor BottomLeft = new EnumAnchor("BottomLeft");
		public static final EnumAnchor BottomCenter = new EnumAnchor("BottomCenter");
		public static final EnumAnchor BottomRight = new EnumAnchor("BottomRight");
	}

	/**
	* Enumeration strings for HorizontalFitPolicy
	*/

	public static class EnumHorizontalFitPolicy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumHorizontalFitPolicy(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumHorizontalFitPolicy getEnum(String enumName)
		{
			return (EnumHorizontalFitPolicy) getEnum(EnumHorizontalFitPolicy.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumHorizontalFitPolicy getEnum(int enumValue)
		{
			return (EnumHorizontalFitPolicy) getEnum(EnumHorizontalFitPolicy.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumHorizontalFitPolicy.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumHorizontalFitPolicy.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumHorizontalFitPolicy.class);
		}

		public static final EnumHorizontalFitPolicy NoRepeat = new EnumHorizontalFitPolicy("NoRepeat");
		public static final EnumHorizontalFitPolicy StretchToFit = new EnumHorizontalFitPolicy("StretchToFit");
		public static final EnumHorizontalFitPolicy UndistortedScaleToFit = new EnumHorizontalFitPolicy("UndistortedScaleToFit");
		public static final EnumHorizontalFitPolicy RepeatToFill = new EnumHorizontalFitPolicy("RepeatToFill");
		public static final EnumHorizontalFitPolicy RepeatUnclipped = new EnumHorizontalFitPolicy("RepeatUnclipped");
	}

	/**
	* Enumeration strings for MarkJustification
	*/

	public static class EnumMarkJustification extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumMarkJustification(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumMarkJustification getEnum(String enumName)
		{
			return (EnumMarkJustification) getEnum(EnumMarkJustification.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumMarkJustification getEnum(int enumValue)
		{
			return (EnumMarkJustification) getEnum(EnumMarkJustification.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumMarkJustification.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumMarkJustification.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumMarkJustification.class);
		}

		public static final EnumMarkJustification Left = new EnumMarkJustification("Left");
		public static final EnumMarkJustification Right = new EnumMarkJustification("Right");
		public static final EnumMarkJustification Center = new EnumMarkJustification("Center");
	}

	/**
	* Enumeration strings for MarkOrientation
	*/

	public static class EnumMarkOrientation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumMarkOrientation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumMarkOrientation getEnum(String enumName)
		{
			return (EnumMarkOrientation) getEnum(EnumMarkOrientation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumMarkOrientation getEnum(int enumValue)
		{
			return (EnumMarkOrientation) getEnum(EnumMarkOrientation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumMarkOrientation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumMarkOrientation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumMarkOrientation.class);
		}

		public static final EnumMarkOrientation Vertical = new EnumMarkOrientation("Vertical");
		public static final EnumMarkOrientation Horizontal = new EnumMarkOrientation("Horizontal");
	}

	/**
	* Enumeration strings for MarkPosition
	*/

	public static class EnumMarkPosition extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumMarkPosition(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumMarkPosition getEnum(String enumName)
		{
			return (EnumMarkPosition) getEnum(EnumMarkPosition.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumMarkPosition getEnum(int enumValue)
		{
			return (EnumMarkPosition) getEnum(EnumMarkPosition.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumMarkPosition.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumMarkPosition.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumMarkPosition.class);
		}

		public static final EnumMarkPosition Top = new EnumMarkPosition("Top");
		public static final EnumMarkPosition Bottom = new EnumMarkPosition("Bottom");
		public static final EnumMarkPosition Left = new EnumMarkPosition("Left");
		public static final EnumMarkPosition Right = new EnumMarkPosition("Right");
	}

	/**
	* Enumeration strings for VerticalFitPolicy
	*/

	public static class EnumVerticalFitPolicy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumVerticalFitPolicy(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumVerticalFitPolicy getEnum(String enumName)
		{
			return (EnumVerticalFitPolicy) getEnum(EnumVerticalFitPolicy.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumVerticalFitPolicy getEnum(int enumValue)
		{
			return (EnumVerticalFitPolicy) getEnum(EnumVerticalFitPolicy.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumVerticalFitPolicy.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumVerticalFitPolicy.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumVerticalFitPolicy.class);
		}

		public static final EnumVerticalFitPolicy NoRepeat = new EnumVerticalFitPolicy("NoRepeat");
		public static final EnumVerticalFitPolicy StretchToFit = new EnumVerticalFitPolicy("StretchToFit");
		public static final EnumVerticalFitPolicy UndistortedScaleToFit = new EnumVerticalFitPolicy("UndistortedScaleToFit");
		public static final EnumVerticalFitPolicy RepeatToFill = new EnumVerticalFitPolicy("RepeatToFill");
		public static final EnumVerticalFitPolicy RepeatUnclipped = new EnumVerticalFitPolicy("RepeatUnclipped");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute Anchor
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute Anchor
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setAnchor(EnumAnchor enumVar)
	{
		setAttribute(AttributeName.ANCHOR, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute Anchor
	  * @return the value of the attribute
	  */
	public EnumAnchor getAnchor()
	{
		return EnumAnchor.getEnum(getAttribute(AttributeName.ANCHOR, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Font
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Font
	  * @param value the value to set the attribute to
	  */
	public void setFont(String value)
	{
		setAttribute(AttributeName.FONT, value, null);
	}

	/**
	  * (23) get String attribute Font
	  * @return the value of the attribute
	  */
	public String getFont()
	{
		return getAttribute(AttributeName.FONT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute FontSize
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute FontSize
	  * @param value the value to set the attribute to
	  */
	public void setFontSize(double value)
	{
		setAttribute(AttributeName.FONTSIZE, value, null);
	}

	/**
	  * (17) get double attribute FontSize
	  * @return double the value of the attribute
	  */
	public double getFontSize()
	{
		return getRealAttribute(AttributeName.FONTSIZE, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute HorizontalFitPolicy
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute HorizontalFitPolicy
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setHorizontalFitPolicy(EnumHorizontalFitPolicy enumVar)
	{
		setAttribute(AttributeName.HORIZONTALFITPOLICY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute HorizontalFitPolicy
	  * @return the value of the attribute
	  */
	public EnumHorizontalFitPolicy getHorizontalFitPolicy()
	{
		return EnumHorizontalFitPolicy.getEnum(getAttribute(AttributeName.HORIZONTALFITPOLICY, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute MarkJustification
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute MarkJustification
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setMarkJustification(EnumMarkJustification enumVar)
	{
		setAttribute(AttributeName.MARKJUSTIFICATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute MarkJustification
	  * @return the value of the attribute
	  */
	public EnumMarkJustification getMarkJustification()
	{
		return EnumMarkJustification.getEnum(getAttribute(AttributeName.MARKJUSTIFICATION, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute MarkOffset
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute MarkOffset
	  * @param value the value to set the attribute to
	  */
	public void setMarkOffset(JDFXYPair value)
	{
		setAttribute(AttributeName.MARKOFFSET, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute MarkOffset
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getMarkOffset()
	{
		final String strAttrName = getAttribute(AttributeName.MARKOFFSET, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute MarkOrientation
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute MarkOrientation
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setMarkOrientation(EnumMarkOrientation enumVar)
	{
		setAttribute(AttributeName.MARKORIENTATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute MarkOrientation
	  * @return the value of the attribute
	  */
	public EnumMarkOrientation getMarkOrientation()
	{
		return EnumMarkOrientation.getEnum(getAttribute(AttributeName.MARKORIENTATION, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute MarkPosition
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute MarkPosition
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setMarkPosition(EnumMarkPosition enumVar)
	{
		setAttribute(AttributeName.MARKPOSITION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute MarkPosition
	  * @return the value of the attribute
	  */
	public EnumMarkPosition getMarkPosition()
	{
		return EnumMarkPosition.getEnum(getAttribute(AttributeName.MARKPOSITION, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute VerticalFitPolicy
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute VerticalFitPolicy
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setVerticalFitPolicy(EnumVerticalFitPolicy enumVar)
	{
		setAttribute(AttributeName.VERTICALFITPOLICY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute VerticalFitPolicy
	  * @return the value of the attribute
	  */
	public EnumVerticalFitPolicy getVerticalFitPolicy()
	{
		return EnumVerticalFitPolicy.getEnum(getAttribute(AttributeName.VERTICALFITPOLICY, null, null));
	}

}// end namespace JDF
