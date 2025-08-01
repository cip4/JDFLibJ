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
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoSaddleStitchingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoSaddleStitchingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.NUMBEROFSTITCHES, 0x4444444442l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.STITCHPOSITIONS, 0x4444444443l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.STAPLESHAPE, 0x4444444443l, AttributeInfo.EnumAttributeType.enumeration, EnumStapleShape.getEnum(0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.STITCHWIDTH, 0x4444444443l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.WIREGAUGE, 0x4444444443l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.WIREBRAND, 0x4444444443l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoSaddleStitchingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoSaddleStitchingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSaddleStitchingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoSaddleStitchingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSaddleStitchingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoSaddleStitchingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for StapleShape
	 */

	public enum EStapleShape
	{
		Crown, Overlap, Butted, ClinchOut, Eyelet;

		public static EStapleShape getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EStapleShape.class, val, null);
		}
	}

	/**
	 * Enumeration strings for StapleShape
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumStapleShape extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumStapleShape(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumStapleShape getEnum(String enumName)
		{
			return (EnumStapleShape) getEnum(EnumStapleShape.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumStapleShape getEnum(int enumValue)
		{
			return (EnumStapleShape) getEnum(EnumStapleShape.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumStapleShape.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumStapleShape.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumStapleShape.class);
		}

		/**  */
		public static final EnumStapleShape Crown = new EnumStapleShape("Crown");
		/**  */
		public static final EnumStapleShape Overlap = new EnumStapleShape("Overlap");
		/**  */
		public static final EnumStapleShape Butted = new EnumStapleShape("Butted");
		/**  */
		public static final EnumStapleShape ClinchOut = new EnumStapleShape("ClinchOut");
		/**  */
		public static final EnumStapleShape Eyelet = new EnumStapleShape("Eyelet");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NumberOfStitches
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NumberOfStitches
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNumberOfStitches(int value)
	{
		setAttribute(AttributeName.NUMBEROFSTITCHES, value, null);
	}

	/**
	 * (15) get int attribute NumberOfStitches
	 *
	 * @return int the value of the attribute
	 */
	public int getNumberOfStitches()
	{
		return getIntAttribute(AttributeName.NUMBEROFSTITCHES, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StitchPositions
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StitchPositions
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStitchPositions(JDFNumberList value)
	{
		setAttribute(AttributeName.STITCHPOSITIONS, value, null);
	}

	/**
	 * (20) get JDFNumberList attribute StitchPositions
	 *
	 * @return JDFNumberList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberList
	 */
	public JDFNumberList getStitchPositions()
	{
		final String strAttrName = getAttribute(AttributeName.STITCHPOSITIONS, null, null);
		final JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StapleShape ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute StapleShape
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setStapleShape(EStapleShape enumVar)
	{
		setAttribute(AttributeName.STAPLESHAPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute StapleShape
	 *
	 * @return the value of the attribute
	 */
	public EStapleShape getEStapleShape()
	{
		return EStapleShape.getEnum(getAttribute(AttributeName.STAPLESHAPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StapleShape ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute StapleShape
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setStapleShape(EnumStapleShape enumVar)
	{
		setAttribute(AttributeName.STAPLESHAPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute StapleShape
	 *
	 * @return the value of the attribute
	 */
	public EnumStapleShape getStapleShape()
	{
		return EnumStapleShape.getEnum(getAttribute(AttributeName.STAPLESHAPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute StitchWidth ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StitchWidth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStitchWidth(double value)
	{
		setAttribute(AttributeName.STITCHWIDTH, value, null);
	}

	/**
	 * (17) get double attribute StitchWidth
	 *
	 * @return double the value of the attribute
	 */
	public double getStitchWidth()
	{
		return getRealAttribute(AttributeName.STITCHWIDTH, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute WireGauge ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WireGauge
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWireGauge(double value)
	{
		setAttribute(AttributeName.WIREGAUGE, value, null);
	}

	/**
	 * (17) get double attribute WireGauge
	 *
	 * @return double the value of the attribute
	 */
	public double getWireGauge()
	{
		return getRealAttribute(AttributeName.WIREGAUGE, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute WireBrand ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WireBrand
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWireBrand(String value)
	{
		setAttribute(AttributeName.WIREBRAND, value, null);
	}

	/**
	 * (23) get String attribute WireBrand
	 *
	 * @return the value of the attribute
	 */
	public String getWireBrand()
	{
		return getAttribute(AttributeName.WIREBRAND, null, JDFCoreConstants.EMPTYSTRING);
	}

}
