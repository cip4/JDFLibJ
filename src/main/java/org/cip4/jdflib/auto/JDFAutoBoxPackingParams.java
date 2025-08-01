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
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoBoxPackingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoBoxPackingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[13];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BOXTYPE, 0x3333111111l, AttributeInfo.EnumAttributeType.enumeration, EnumBoxType.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.BOXTYPEDETAILS, 0x3333111111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.COMPONENTSPERROW, 0x3333333111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.COLUMNS, 0x3333331111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.COMPONENTORIENTATION, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration, EnumComponentOrientation.getEnum(0),
				null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.COPIES, 0x3333331111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.FILLMATERIAL, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.LAYERS, 0x3333333111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.MAXWEIGHT, 0x3333331111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.PATTERN, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.ROWS, 0x3333333111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.TIES, 0x3333333111l, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.UNDERLAYS, 0x3333333111l, AttributeInfo.EnumAttributeType.IntegerList, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoBoxPackingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoBoxPackingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBoxPackingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoBoxPackingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBoxPackingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoBoxPackingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		final boolean bRet = super.init();
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
	 * Enumeration strings for BoxType
	 */

	public enum EBoxType
	{
		Box, Carton, Envelope;

		public static EBoxType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EBoxType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for BoxType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumBoxType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumBoxType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumBoxType getEnum(String enumName)
		{
			return (EnumBoxType) getEnum(EnumBoxType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumBoxType getEnum(int enumValue)
		{
			return (EnumBoxType) getEnum(EnumBoxType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBoxType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBoxType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBoxType.class);
		}

		/**  */
		public static final EnumBoxType Box = new EnumBoxType("Box");
		/**  */
		public static final EnumBoxType Carton = new EnumBoxType("Carton");
		/**  */
		public static final EnumBoxType Envelope = new EnumBoxType("Envelope");
	}

	/**
	 * Enumeration strings for ComponentOrientation
	 */

	public enum EComponentOrientation
	{
		XY, XZ, YZ;

		public static EComponentOrientation getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EComponentOrientation.class, val, null);
		}
	}

	/**
	 * Enumeration strings for ComponentOrientation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumComponentOrientation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumComponentOrientation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumComponentOrientation getEnum(String enumName)
		{
			return (EnumComponentOrientation) getEnum(EnumComponentOrientation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumComponentOrientation getEnum(int enumValue)
		{
			return (EnumComponentOrientation) getEnum(EnumComponentOrientation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumComponentOrientation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumComponentOrientation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumComponentOrientation.class);
		}

		/**  */
		public static final EnumComponentOrientation XY = new EnumComponentOrientation("XY");
		/**  */
		public static final EnumComponentOrientation XZ = new EnumComponentOrientation("XZ");
		/**  */
		public static final EnumComponentOrientation YZ = new EnumComponentOrientation("YZ");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BoxType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BoxType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBoxType(EBoxType enumVar)
	{
		setAttribute(AttributeName.BOXTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute BoxType
	 *
	 * @return the value of the attribute
	 */
	public EBoxType getEBoxType()
	{
		return EBoxType.getEnum(getAttribute(AttributeName.BOXTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BoxType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BoxType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setBoxType(EnumBoxType enumVar)
	{
		setAttribute(AttributeName.BOXTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute BoxType
	 *
	 * @return the value of the attribute
	 */
	public EnumBoxType getBoxType()
	{
		return EnumBoxType.getEnum(getAttribute(AttributeName.BOXTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BoxTypeDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BoxTypeDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBoxTypeDetails(String value)
	{
		setAttribute(AttributeName.BOXTYPEDETAILS, value, null);
	}

	/**
	 * (23) get String attribute BoxTypeDetails
	 *
	 * @return the value of the attribute
	 */
	public String getBoxTypeDetails()
	{
		return getAttribute(AttributeName.BOXTYPEDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ComponentsPerRow
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ComponentsPerRow
	 *
	 * @param value the value to set the attribute to
	 */
	public void setComponentsPerRow(int value)
	{
		setAttribute(AttributeName.COMPONENTSPERROW, value, null);
	}

	/**
	 * (15) get int attribute ComponentsPerRow
	 *
	 * @return int the value of the attribute
	 */
	public int getComponentsPerRow()
	{
		return getIntAttribute(AttributeName.COMPONENTSPERROW, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Columns ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Columns
	 *
	 * @param value the value to set the attribute to
	 */
	public void setColumns(int value)
	{
		setAttribute(AttributeName.COLUMNS, value, null);
	}

	/**
	 * (15) get int attribute Columns
	 *
	 * @return int the value of the attribute
	 */
	public int getColumns()
	{
		return getIntAttribute(AttributeName.COLUMNS, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ComponentOrientation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ComponentOrientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setComponentOrientation(EComponentOrientation enumVar)
	{
		setAttribute(AttributeName.COMPONENTORIENTATION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ComponentOrientation
	 *
	 * @return the value of the attribute
	 */
	public EComponentOrientation getEComponentOrientation()
	{
		return EComponentOrientation.getEnum(getAttribute(AttributeName.COMPONENTORIENTATION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ComponentOrientation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ComponentOrientation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setComponentOrientation(EnumComponentOrientation enumVar)
	{
		setAttribute(AttributeName.COMPONENTORIENTATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ComponentOrientation
	 *
	 * @return the value of the attribute
	 */
	public EnumComponentOrientation getComponentOrientation()
	{
		return EnumComponentOrientation.getEnum(getAttribute(AttributeName.COMPONENTORIENTATION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Copies ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Copies
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCopies(int value)
	{
		setAttribute(AttributeName.COPIES, value, null);
	}

	/**
	 * (15) get int attribute Copies
	 *
	 * @return int the value of the attribute
	 */
	public int getCopies()
	{
		return getIntAttribute(AttributeName.COPIES, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FillMaterial
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FillMaterial
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFillMaterial(String value)
	{
		setAttribute(AttributeName.FILLMATERIAL, value, null);
	}

	/**
	 * (23) get String attribute FillMaterial
	 *
	 * @return the value of the attribute
	 */
	public String getFillMaterial()
	{
		return getAttribute(AttributeName.FILLMATERIAL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Layers ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Layers
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLayers(int value)
	{
		setAttribute(AttributeName.LAYERS, value, null);
	}

	/**
	 * (15) get int attribute Layers
	 *
	 * @return int the value of the attribute
	 */
	public int getLayers()
	{
		return getIntAttribute(AttributeName.LAYERS, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxWeight ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxWeight
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxWeight(double value)
	{
		setAttribute(AttributeName.MAXWEIGHT, value, null);
	}

	/**
	 * (17) get double attribute MaxWeight
	 *
	 * @return double the value of the attribute
	 */
	public double getMaxWeight()
	{
		return getRealAttribute(AttributeName.MAXWEIGHT, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Pattern ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Pattern
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPattern(String value)
	{
		setAttribute(AttributeName.PATTERN, value, null);
	}

	/**
	 * (23) get String attribute Pattern
	 *
	 * @return the value of the attribute
	 */
	public String getPattern()
	{
		return getAttribute(AttributeName.PATTERN, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Rows ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Rows
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRows(int value)
	{
		setAttribute(AttributeName.ROWS, value, null);
	}

	/**
	 * (15) get int attribute Rows
	 *
	 * @return int the value of the attribute
	 */
	public int getRows()
	{
		return getIntAttribute(AttributeName.ROWS, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Ties ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Ties
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTies(JDFIntegerList value)
	{
		setAttribute(AttributeName.TIES, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute Ties
	 *
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getTies()
	{
		final String strAttrName = getAttribute(AttributeName.TIES, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute UnderLays ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute UnderLays
	 *
	 * @param value the value to set the attribute to
	 */
	public void setUnderLays(JDFIntegerList value)
	{
		setAttribute(AttributeName.UNDERLAYS, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute UnderLays
	 *
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getUnderLays()
	{
		final String strAttrName = getAttribute(AttributeName.UNDERLAYS, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

}
