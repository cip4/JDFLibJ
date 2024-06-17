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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.resource.JDFResource;

/**
 *****************************************************************************
 * class JDFAutoSideSewingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoSideSewingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.NUMBEROFNEEDLES, 0x4444444442l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.OFFSET, 0x4444444442l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.NEEDLEPOSITIONS, 0x4444444443l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.SEWINGPATTERN, 0x4444444443l, AttributeInfo.EnumAttributeType.enumeration, EnumSewingPattern.getEnum(0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.THREADMATERIAL, 0x4444444443l, AttributeInfo.EnumAttributeType.enumeration, EnumThreadMaterial.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.THREADTHICKNESS, 0x4444444443l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.THREADBRAND, 0x4444444443l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoSideSewingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoSideSewingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSideSewingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoSideSewingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSideSewingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoSideSewingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for SewingPattern
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSewingPattern extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSewingPattern(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSewingPattern getEnum(String enumName)
		{
			return (EnumSewingPattern) getEnum(EnumSewingPattern.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSewingPattern getEnum(int enumValue)
		{
			return (EnumSewingPattern) getEnum(EnumSewingPattern.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSewingPattern.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSewingPattern.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSewingPattern.class);
		}

		/**  */
		public static final EnumSewingPattern Normal = new EnumSewingPattern("Normal");
		/**  */
		public static final EnumSewingPattern Staggered = new EnumSewingPattern("Staggered");
		/**  */
		public static final EnumSewingPattern CombinedStaggered = new EnumSewingPattern("CombinedStaggered");
	}

	/**
	 * Enumeration strings for ThreadMaterial
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumThreadMaterial extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumThreadMaterial(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumThreadMaterial getEnum(String enumName)
		{
			return (EnumThreadMaterial) getEnum(EnumThreadMaterial.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumThreadMaterial getEnum(int enumValue)
		{
			return (EnumThreadMaterial) getEnum(EnumThreadMaterial.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumThreadMaterial.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumThreadMaterial.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumThreadMaterial.class);
		}

		/**  */
		public static final EnumThreadMaterial Cotton = new EnumThreadMaterial("Cotton");
		/**  */
		public static final EnumThreadMaterial Nylon = new EnumThreadMaterial("Nylon");
		/**  */
		public static final EnumThreadMaterial Polyester = new EnumThreadMaterial("Polyester");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NumberOfNeedles
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NumberOfNeedles
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNumberOfNeedles(int value)
	{
		setAttribute(AttributeName.NUMBEROFNEEDLES, value, null);
	}

	/**
	 * (15) get int attribute NumberOfNeedles
	 *
	 * @return int the value of the attribute
	 */
	public int getNumberOfNeedles()
	{
		return getIntAttribute(AttributeName.NUMBEROFNEEDLES, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Offset ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Offset
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOffset(double value)
	{
		setAttribute(AttributeName.OFFSET, value, null);
	}

	/**
	 * (17) get double attribute Offset
	 *
	 * @return double the value of the attribute
	 */
	public double getOffset()
	{
		return getRealAttribute(AttributeName.OFFSET, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NeedlePositions
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NeedlePositions
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNeedlePositions(JDFNumberList value)
	{
		setAttribute(AttributeName.NEEDLEPOSITIONS, value, null);
	}

	/**
	 * (20) get JDFNumberList attribute NeedlePositions
	 *
	 * @return JDFNumberList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberList
	 */
	public JDFNumberList getNeedlePositions()
	{
		final String strAttrName = getAttribute(AttributeName.NEEDLEPOSITIONS, null, null);
		final JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SewingPattern
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SewingPattern
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSewingPattern(EnumSewingPattern enumVar)
	{
		setAttribute(AttributeName.SEWINGPATTERN, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute SewingPattern
	 *
	 * @return the value of the attribute
	 */
	public EnumSewingPattern getSewingPattern()
	{
		return EnumSewingPattern.getEnum(getAttribute(AttributeName.SEWINGPATTERN, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ThreadMaterial
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ThreadMaterial
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setThreadMaterial(EnumThreadMaterial enumVar)
	{
		setAttribute(AttributeName.THREADMATERIAL, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ThreadMaterial
	 *
	 * @return the value of the attribute
	 */
	public EnumThreadMaterial getThreadMaterial()
	{
		return EnumThreadMaterial.getEnum(getAttribute(AttributeName.THREADMATERIAL, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ThreadThickness
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ThreadThickness
	 *
	 * @param value the value to set the attribute to
	 */
	public void setThreadThickness(double value)
	{
		setAttribute(AttributeName.THREADTHICKNESS, value, null);
	}

	/**
	 * (17) get double attribute ThreadThickness
	 *
	 * @return double the value of the attribute
	 */
	public double getThreadThickness()
	{
		return getRealAttribute(AttributeName.THREADTHICKNESS, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ThreadBrand ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ThreadBrand
	 *
	 * @param value the value to set the attribute to
	 */
	public void setThreadBrand(String value)
	{
		setAttribute(AttributeName.THREADBRAND, value, null);
	}

	/**
	 * (23) get String attribute ThreadBrand
	 *
	 * @return the value of the attribute
	 */
	public String getThreadBrand()
	{
		return getAttribute(AttributeName.THREADBRAND, null, JDFCoreConstants.EMPTYSTRING);
	}

}
