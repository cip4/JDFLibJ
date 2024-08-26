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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.process.JDFTIFFEmbeddedFile;
import org.cip4.jdflib.resource.process.JDFTIFFtag;

/**
 *****************************************************************************
 * class JDFAutoTIFFFormatParams : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoTIFFFormatParams extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BYTEORDER, 0x4444433311l, AttributeInfo.EnumAttributeType.enumeration, EnumByteOrder.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.INTERLEAVING, 0x4444433311l, AttributeInfo.EnumAttributeType.integer, null, "1");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.WHITEISZERO, 0x4444433311l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.SEGMENTATION, 0x4444433311l, AttributeInfo.EnumAttributeType.enumeration, EnumSegmentation.getEnum(0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ROWSPERSTRIP, 0x4444433311l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.TILESIZE, 0x4444433311l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.SEPARATIONNAMETAG, 0x4444433311l, AttributeInfo.EnumAttributeType.integer, null, "270");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.TIFFTAG, 0x4444433331l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.TIFFEMBEDDEDFILE, 0x4444433331l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoTIFFFormatParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoTIFFFormatParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoTIFFFormatParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoTIFFFormatParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoTIFFFormatParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoTIFFFormatParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for ByteOrder
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumByteOrder extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumByteOrder(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumByteOrder getEnum(String enumName)
		{
			return (EnumByteOrder) getEnum(EnumByteOrder.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumByteOrder getEnum(int enumValue)
		{
			return (EnumByteOrder) getEnum(EnumByteOrder.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumByteOrder.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumByteOrder.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumByteOrder.class);
		}

		/**  */
		public static final EnumByteOrder II = new EnumByteOrder("II");
		/**  */
		public static final EnumByteOrder MM = new EnumByteOrder("MM");
	}

	/**
	 * Enumeration strings for Segmentation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSegmentation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSegmentation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSegmentation getEnum(String enumName)
		{
			return (EnumSegmentation) getEnum(EnumSegmentation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSegmentation getEnum(int enumValue)
		{
			return (EnumSegmentation) getEnum(EnumSegmentation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSegmentation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSegmentation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSegmentation.class);
		}

		/**  */
		public static final EnumSegmentation SingleStrip = new EnumSegmentation("SingleStrip");
		/**  */
		public static final EnumSegmentation Stripped = new EnumSegmentation("Stripped");
		/**  */
		public static final EnumSegmentation Tiled = new EnumSegmentation("Tiled");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ByteOrder ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ByteOrder
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setByteOrder(EnumByteOrder enumVar)
	{
		setAttribute(AttributeName.BYTEORDER, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ByteOrder
	 *
	 * @return the value of the attribute
	 */
	public EnumByteOrder getByteOrder()
	{
		return EnumByteOrder.getEnum(getAttribute(AttributeName.BYTEORDER, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Interleaving
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Interleaving
	 *
	 * @param value the value to set the attribute to
	 */
	public void setInterleaving(int value)
	{
		setAttribute(AttributeName.INTERLEAVING, value, null);
	}

	/**
	 * (15) get int attribute Interleaving
	 *
	 * @return int the value of the attribute
	 */
	public int getInterleaving()
	{
		return getIntAttribute(AttributeName.INTERLEAVING, null, 1);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute WhiteIsZero ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WhiteIsZero
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWhiteIsZero(boolean value)
	{
		setAttribute(AttributeName.WHITEISZERO, value, null);
	}

	/**
	 * (18) get boolean attribute WhiteIsZero
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getWhiteIsZero()
	{
		return getBoolAttribute(AttributeName.WHITEISZERO, null, true);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Segmentation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Segmentation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSegmentation(EnumSegmentation enumVar)
	{
		setAttribute(AttributeName.SEGMENTATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Segmentation
	 *
	 * @return the value of the attribute
	 */
	public EnumSegmentation getSegmentation()
	{
		return EnumSegmentation.getEnum(getAttribute(AttributeName.SEGMENTATION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RowsPerStrip
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RowsPerStrip
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRowsPerStrip(int value)
	{
		setAttribute(AttributeName.ROWSPERSTRIP, value, null);
	}

	/**
	 * (15) get int attribute RowsPerStrip
	 *
	 * @return int the value of the attribute
	 */
	public int getRowsPerStrip()
	{
		return getIntAttribute(AttributeName.ROWSPERSTRIP, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TileSize ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TileSize
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTileSize(JDFXYPair value)
	{
		setAttribute(AttributeName.TILESIZE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute TileSize
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getTileSize()
	{
		final String strAttrName = getAttribute(AttributeName.TILESIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SeparationNameTag
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SeparationNameTag
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSeparationNameTag(int value)
	{
		setAttribute(AttributeName.SEPARATIONNAMETAG, value, null);
	}

	/**
	 * (15) get int attribute SeparationNameTag
	 *
	 * @return int the value of the attribute
	 */
	public int getSeparationNameTag()
	{
		return getIntAttribute(AttributeName.SEPARATIONNAMETAG, null, 270);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element TIFFtag
	 *
	 * @return JDFTIFFtag the element
	 */
	public JDFTIFFtag getTIFFtag()
	{
		return (JDFTIFFtag) getElement(ElementName.TIFFTAG, null, 0);
	}

	/**
	 * (25) getCreateTIFFtag
	 * 
	 * @return JDFTIFFtag the element
	 */
	public JDFTIFFtag getCreateTIFFtag()
	{
		return (JDFTIFFtag) getCreateElement_JDFElement(ElementName.TIFFTAG, null, 0);
	}

	/**
	 * (26) getCreateTIFFtag
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFTIFFtag the element
	 */
	public JDFTIFFtag getCreateTIFFtag(int iSkip)
	{
		return (JDFTIFFtag) getCreateElement_JDFElement(ElementName.TIFFTAG, null, iSkip);
	}

	/**
	 * (27) const get element TIFFtag
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFTIFFtag the element default is getTIFFtag(0)
	 */
	public JDFTIFFtag getTIFFtag(int iSkip)
	{
		return (JDFTIFFtag) getElement(ElementName.TIFFTAG, null, iSkip);
	}

	/**
	 * Get all TIFFtag from the current element
	 * 
	 * @return Collection<JDFTIFFtag>, null if none are available
	 */
	public Collection<JDFTIFFtag> getAllTIFFtag()
	{
		return getChildArrayByClass(JDFTIFFtag.class, false, 0);
	}

	/**
	 * (30) append element TIFFtag
	 *
	 * @return JDFTIFFtag the element
	 */
	public JDFTIFFtag appendTIFFtag()
	{
		return (JDFTIFFtag) appendElement(ElementName.TIFFTAG, null);
	}

	/**
	 * (24) const get element TIFFEmbeddedFile
	 *
	 * @return JDFTIFFEmbeddedFile the element
	 */
	public JDFTIFFEmbeddedFile getTIFFEmbeddedFile()
	{
		return (JDFTIFFEmbeddedFile) getElement(ElementName.TIFFEMBEDDEDFILE, null, 0);
	}

	/**
	 * (25) getCreateTIFFEmbeddedFile
	 * 
	 * @return JDFTIFFEmbeddedFile the element
	 */
	public JDFTIFFEmbeddedFile getCreateTIFFEmbeddedFile()
	{
		return (JDFTIFFEmbeddedFile) getCreateElement_JDFElement(ElementName.TIFFEMBEDDEDFILE, null, 0);
	}

	/**
	 * (26) getCreateTIFFEmbeddedFile
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFTIFFEmbeddedFile the element
	 */
	public JDFTIFFEmbeddedFile getCreateTIFFEmbeddedFile(int iSkip)
	{
		return (JDFTIFFEmbeddedFile) getCreateElement_JDFElement(ElementName.TIFFEMBEDDEDFILE, null, iSkip);
	}

	/**
	 * (27) const get element TIFFEmbeddedFile
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFTIFFEmbeddedFile the element default is getTIFFEmbeddedFile(0)
	 */
	public JDFTIFFEmbeddedFile getTIFFEmbeddedFile(int iSkip)
	{
		return (JDFTIFFEmbeddedFile) getElement(ElementName.TIFFEMBEDDEDFILE, null, iSkip);
	}

	/**
	 * Get all TIFFEmbeddedFile from the current element
	 * 
	 * @return Collection<JDFTIFFEmbeddedFile>, null if none are available
	 */
	public Collection<JDFTIFFEmbeddedFile> getAllTIFFEmbeddedFile()
	{
		return getChildArrayByClass(JDFTIFFEmbeddedFile.class, false, 0);
	}

	/**
	 * (30) append element TIFFEmbeddedFile
	 *
	 * @return JDFTIFFEmbeddedFile the element
	 */
	public JDFTIFFEmbeddedFile appendTIFFEmbeddedFile()
	{
		return (JDFTIFFEmbeddedFile) appendElement(ElementName.TIFFEMBEDDEDFILE, null);
	}

}
