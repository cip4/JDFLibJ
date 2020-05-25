/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.datatypes.JDFXYPair;

/**
 *****************************************************************************
 * class JDFAutoJPEG2000Params : public JDFElement
 *****************************************************************************
 *
 */

public abstract class JDFAutoJPEG2000Params extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CODEBLOCKSIZE, 0x33333111, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.LAYERSPERTILE, 0x33333111, AttributeInfo.EnumAttributeType.integer, null, "1");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.LAYERRATES, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.NUMRESOLUTIONS, 0x33333111, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.PROGRESSIONORDER, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumProgressionOrder.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.TILESIZE, 0x33333111, AttributeInfo.EnumAttributeType.XYPair, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoJPEG2000Params
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoJPEG2000Params(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoJPEG2000Params
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoJPEG2000Params(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoJPEG2000Params
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoJPEG2000Params(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for ProgressionOrder
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumProgressionOrder extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumProgressionOrder(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumProgressionOrder getEnum(String enumName)
		{
			return (EnumProgressionOrder) getEnum(EnumProgressionOrder.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumProgressionOrder getEnum(int enumValue)
		{
			return (EnumProgressionOrder) getEnum(EnumProgressionOrder.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumProgressionOrder.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumProgressionOrder.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumProgressionOrder.class);
		}

		/**  */
		public static final EnumProgressionOrder LRCP = new EnumProgressionOrder("LRCP");
		/**  */
		public static final EnumProgressionOrder RLCP = new EnumProgressionOrder("RLCP");
		/**  */
		public static final EnumProgressionOrder RPCL = new EnumProgressionOrder("RPCL");
		/**  */
		public static final EnumProgressionOrder PCRL = new EnumProgressionOrder("PCRL");
		/**  */
		public static final EnumProgressionOrder CPRL = new EnumProgressionOrder("CPRL");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute CodeBlockSize
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute CodeBlockSize
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCodeBlockSize(int value)
	{
		setAttribute(AttributeName.CODEBLOCKSIZE, value, null);
	}

	/**
	 * (15) get int attribute CodeBlockSize
	 *
	 * @return int the value of the attribute
	 */
	public int getCodeBlockSize()
	{
		return getIntAttribute(AttributeName.CODEBLOCKSIZE, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute LayersPerTile
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute LayersPerTile
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLayersPerTile(int value)
	{
		setAttribute(AttributeName.LAYERSPERTILE, value, null);
	}

	/**
	 * (15) get int attribute LayersPerTile
	 *
	 * @return int the value of the attribute
	 */
	public int getLayersPerTile()
	{
		return getIntAttribute(AttributeName.LAYERSPERTILE, null, 1);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute LayerRates
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute LayerRates
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLayerRates(JDFNumberList value)
	{
		setAttribute(AttributeName.LAYERRATES, value, null);
	}

	/**
	 * (20) get JDFNumberList attribute LayerRates
	 *
	 * @return JDFNumberList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberList
	 */
	public JDFNumberList getLayerRates()
	{
		final String strAttrName = getAttribute(AttributeName.LAYERRATES, null, null);
		final JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute NumResolutions
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute NumResolutions
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNumResolutions(int value)
	{
		setAttribute(AttributeName.NUMRESOLUTIONS, value, null);
	}

	/**
	 * (15) get int attribute NumResolutions
	 *
	 * @return int the value of the attribute
	 */
	public int getNumResolutions()
	{
		return getIntAttribute(AttributeName.NUMRESOLUTIONS, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ProgressionOrder
	--------------------------------------------------------------------- */
	/**
	 * (5) set attribute ProgressionOrder
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setProgressionOrder(EnumProgressionOrder enumVar)
	{
		setAttribute(AttributeName.PROGRESSIONORDER, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ProgressionOrder
	 *
	 * @return the value of the attribute
	 */
	public EnumProgressionOrder getProgressionOrder()
	{
		return EnumProgressionOrder.getEnum(getAttribute(AttributeName.PROGRESSIONORDER, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute TileSize
	--------------------------------------------------------------------- */
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

}
