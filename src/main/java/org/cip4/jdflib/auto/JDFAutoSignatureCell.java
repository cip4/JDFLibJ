/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
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
import org.cip4.jdflib.datatypes.JDFIntegerList;

/**
 *****************************************************************************
 * class JDFAutoSignatureCell : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoSignatureCell extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[12];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BACKFACEPAGES, 0x44443311, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.BACKPAGES, 0x33333311, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.BACKSPREAD, 0x33311111, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.BOTTLEANGLE, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.BOTTLEAXIS, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumBottleAxis.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.FACECELLS, 0x33331111, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.FRONTFACEPAGES, 0x44443311, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.FRONTPAGES, 0x33333311, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.FRONTSPREAD, 0x33311111, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.ORIENTATION, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumOrientation.getEnum(0), "Up");
		atrInfoTable[10] = new AtrInfoTable(AttributeName.SECTIONINDEX, 0x33333311, AttributeInfo.EnumAttributeType.integer, null, "0");
		atrInfoTable[11] = new AtrInfoTable(AttributeName.STATIONNAME, 0x33333111, AttributeInfo.EnumAttributeType.string, null, "0");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoSignatureCell
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoSignatureCell(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSignatureCell
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoSignatureCell(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSignatureCell
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoSignatureCell(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoSignatureCell[  --> " + super.toString() + " ]";
	}

	/**
	 * Enumeration strings for BottleAxis
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumBottleAxis extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumBottleAxis(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumBottleAxis getEnum(String enumName)
		{
			return (EnumBottleAxis) getEnum(EnumBottleAxis.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumBottleAxis getEnum(int enumValue)
		{
			return (EnumBottleAxis) getEnum(EnumBottleAxis.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBottleAxis.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBottleAxis.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBottleAxis.class);
		}

		/**  */
		public static final EnumBottleAxis SpineHead = new EnumBottleAxis("SpineHead");
		/**  */
		public static final EnumBottleAxis SpineFoot = new EnumBottleAxis("SpineFoot");
		/**  */
		public static final EnumBottleAxis FaceHead = new EnumBottleAxis("FaceHead");
		/**  */
		public static final EnumBottleAxis FaceFoot = new EnumBottleAxis("FaceFoot");
	}

	/**
	 * Enumeration strings for Orientation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumOrientation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumOrientation(String name)
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
		public static final EnumOrientation Up = new EnumOrientation("Up");
		/**  */
		public static final EnumOrientation Down = new EnumOrientation("Down");
		/**  */
		public static final EnumOrientation Left = new EnumOrientation("Left");
		/**  */
		public static final EnumOrientation Right = new EnumOrientation("Right");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BackFacePages ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BackFacePages
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setBackFacePages(JDFIntegerList value)
	{
		setAttribute(AttributeName.BACKFACEPAGES, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute BackFacePages
	 * 
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getBackFacePages()
	{
		final String strAttrName = getAttribute(AttributeName.BACKFACEPAGES, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BackPages ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BackPages
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setBackPages(JDFIntegerList value)
	{
		setAttribute(AttributeName.BACKPAGES, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute BackPages
	 * 
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getBackPages()
	{
		final String strAttrName = getAttribute(AttributeName.BACKPAGES, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BackSpread ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BackSpread
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setBackSpread(JDFIntegerList value)
	{
		setAttribute(AttributeName.BACKSPREAD, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute BackSpread
	 * 
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getBackSpread()
	{
		final String strAttrName = getAttribute(AttributeName.BACKSPREAD, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BottleAngle ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BottleAngle
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setBottleAngle(double value)
	{
		setAttribute(AttributeName.BOTTLEANGLE, value, null);
	}

	/**
	 * (17) get double attribute BottleAngle
	 * 
	 * @return double the value of the attribute
	 */
	public double getBottleAngle()
	{
		return getRealAttribute(AttributeName.BOTTLEANGLE, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BottleAxis ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BottleAxis
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBottleAxis(EnumBottleAxis enumVar)
	{
		setAttribute(AttributeName.BOTTLEAXIS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute BottleAxis
	 * 
	 * @return the value of the attribute
	 */
	public EnumBottleAxis getBottleAxis()
	{
		return EnumBottleAxis.getEnum(getAttribute(AttributeName.BOTTLEAXIS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FaceCells ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FaceCells
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setFaceCells(JDFIntegerList value)
	{
		setAttribute(AttributeName.FACECELLS, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute FaceCells
	 * 
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getFaceCells()
	{
		final String strAttrName = getAttribute(AttributeName.FACECELLS, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FrontFacePages ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FrontFacePages
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setFrontFacePages(JDFIntegerList value)
	{
		setAttribute(AttributeName.FRONTFACEPAGES, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute FrontFacePages
	 * 
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getFrontFacePages()
	{
		final String strAttrName = getAttribute(AttributeName.FRONTFACEPAGES, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FrontPages ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FrontPages
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setFrontPages(JDFIntegerList value)
	{
		setAttribute(AttributeName.FRONTPAGES, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute FrontPages
	 * 
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getFrontPages()
	{
		final String strAttrName = getAttribute(AttributeName.FRONTPAGES, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FrontSpread ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FrontSpread
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setFrontSpread(JDFIntegerList value)
	{
		setAttribute(AttributeName.FRONTSPREAD, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute FrontSpread
	 * 
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getFrontSpread()
	{
		final String strAttrName = getAttribute(AttributeName.FRONTSPREAD, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Orientation ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Orientation
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
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
		return EnumOrientation.getEnum(getAttribute(AttributeName.ORIENTATION, null, "Up"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SectionIndex ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SectionIndex
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setSectionIndex(int value)
	{
		setAttribute(AttributeName.SECTIONINDEX, value, null);
	}

	/**
	 * (15) get int attribute SectionIndex
	 * 
	 * @return int the value of the attribute
	 */
	public int getSectionIndex()
	{
		return getIntAttribute(AttributeName.SECTIONINDEX, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StationName ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StationName
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setStationName(String value)
	{
		setAttribute(AttributeName.STATIONNAME, value, null);
	}

	/**
	 * (23) get String attribute StationName
	 * 
	 * @return the value of the attribute
	 */
	public String getStationName()
	{
		return getAttribute(AttributeName.STATIONNAME, null, "0");
	}

}// end namespace JDF
