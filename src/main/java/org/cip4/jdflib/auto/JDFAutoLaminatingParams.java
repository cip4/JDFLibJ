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
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFResource;

/**
 *****************************************************************************
 * class JDFAutoLaminatingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoLaminatingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ADHESIVETYPE, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.GAPLIST, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.HARDENERTYPE, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.LAMINATINGBOX, 0x3333333331l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.LAMINATINGMETHOD, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumLaminatingMethod.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.NIPWIDTH, 0x3333333111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MODULEINDEX, 0x3333331111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.TEMPERATURE, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoLaminatingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoLaminatingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLaminatingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoLaminatingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLaminatingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoLaminatingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for LaminatingMethod
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumLaminatingMethod extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumLaminatingMethod(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumLaminatingMethod getEnum(String enumName)
		{
			return (EnumLaminatingMethod) getEnum(EnumLaminatingMethod.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumLaminatingMethod getEnum(int enumValue)
		{
			return (EnumLaminatingMethod) getEnum(EnumLaminatingMethod.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumLaminatingMethod.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumLaminatingMethod.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumLaminatingMethod.class);
		}

		/**  */
		public static final EnumLaminatingMethod CompoundFoil = new EnumLaminatingMethod("CompoundFoil");
		/**  */
		public static final EnumLaminatingMethod DispersionGlue = new EnumLaminatingMethod("DispersionGlue");
		/**  */
		public static final EnumLaminatingMethod Fusing = new EnumLaminatingMethod("Fusing");
		/**  */
		public static final EnumLaminatingMethod Unknown = new EnumLaminatingMethod("Unknown");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AdhesiveType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AdhesiveType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAdhesiveType(String value)
	{
		setAttribute(AttributeName.ADHESIVETYPE, value, null);
	}

	/**
	 * (23) get String attribute AdhesiveType
	 *
	 * @return the value of the attribute
	 */
	public String getAdhesiveType()
	{
		return getAttribute(AttributeName.ADHESIVETYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute GapList ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute GapList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setGapList(JDFNumberList value)
	{
		setAttribute(AttributeName.GAPLIST, value, null);
	}

	/**
	 * (20) get JDFNumberList attribute GapList
	 *
	 * @return JDFNumberList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberList
	 */
	public JDFNumberList getGapList()
	{
		final String strAttrName = getAttribute(AttributeName.GAPLIST, null, null);
		final JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute HardenerType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute HardenerType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHardenerType(String value)
	{
		setAttribute(AttributeName.HARDENERTYPE, value, null);
	}

	/**
	 * (23) get String attribute HardenerType
	 *
	 * @return the value of the attribute
	 */
	public String getHardenerType()
	{
		return getAttribute(AttributeName.HARDENERTYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute LaminatingBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LaminatingBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLaminatingBox(JDFRectangle value)
	{
		setAttribute(AttributeName.LAMINATINGBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute LaminatingBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getLaminatingBox()
	{
		final String strAttrName = getAttribute(AttributeName.LAMINATINGBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute LaminatingMethod
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute LaminatingMethod
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setLaminatingMethod(EnumLaminatingMethod enumVar)
	{
		setAttribute(AttributeName.LAMINATINGMETHOD, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute LaminatingMethod
	 *
	 * @return the value of the attribute
	 */
	public EnumLaminatingMethod getLaminatingMethod()
	{
		return EnumLaminatingMethod.getEnum(getAttribute(AttributeName.LAMINATINGMETHOD, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NipWidth ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NipWidth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNipWidth(double value)
	{
		setAttribute(AttributeName.NIPWIDTH, value, null);
	}

	/**
	 * (17) get double attribute NipWidth
	 *
	 * @return double the value of the attribute
	 */
	public double getNipWidth()
	{
		return getRealAttribute(AttributeName.NIPWIDTH, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ModuleIndex ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ModuleIndex
	 *
	 * @param value the value to set the attribute to
	 */
	public void setModuleIndex(int value)
	{
		setAttribute(AttributeName.MODULEINDEX, value, null);
	}

	/**
	 * (15) get int attribute ModuleIndex
	 *
	 * @return int the value of the attribute
	 */
	public int getModuleIndex()
	{
		return getIntAttribute(AttributeName.MODULEINDEX, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Temperature ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Temperature
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTemperature(double value)
	{
		setAttribute(AttributeName.TEMPERATURE, value, null);
	}

	/**
	 * (17) get double attribute Temperature
	 *
	 * @return double the value of the attribute
	 */
	public double getTemperature()
	{
		return getRealAttribute(AttributeName.TEMPERATURE, null, 0.0);
	}

}
