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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.postpress.JDFHoleMakingParams;

/**
 *****************************************************************************
 * class JDFAutoPlasticCombBindingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPlasticCombBindingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BRAND, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.COLOR, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.COLORDETAILS, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.DIAMETER, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.THICKNESS, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.TYPE, 0x44444433, AttributeInfo.EnumAttributeType.enumeration, EnumType.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.HOLEMAKINGPARAMS, 0x66666666);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPlasticCombBindingParams
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPlasticCombBindingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPlasticCombBindingParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPlasticCombBindingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPlasticCombBindingParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPlasticCombBindingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoPlasticCombBindingParams[  --> " + super.toString() + " ]";
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
	 * Enumeration strings for Type
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumType getEnum(String enumName)
		{
			return (EnumType) getEnum(EnumType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumType getEnum(int enumValue)
		{
			return (EnumType) getEnum(EnumType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumType.class);
		}

		/**  */
		public static final EnumType R2_generic = new EnumType("R2-generic");
		/**  */
		public static final EnumType S1_generic = new EnumType("S1-generic");
		/**  */
		public static final EnumType S_generic = new EnumType("S-generic");
		/**  */
		public static final EnumType R2m_DIN = new EnumType("R2m-DIN");
		/**  */
		public static final EnumType R2m_ISO = new EnumType("R2m-ISO");
		/**  */
		public static final EnumType R2m_MIB = new EnumType("R2m-MIB");
		/**  */
		public static final EnumType R2i_US_a = new EnumType("R2i-US-a");
		/**  */
		public static final EnumType R2i_US_b = new EnumType("R2i-US-b");
		/**  */
		public static final EnumType R3_generic = new EnumType("R3-generic");
		/**  */
		public static final EnumType R3i_US = new EnumType("R3i-US");
		/**  */
		public static final EnumType R4_generic = new EnumType("R4-generic");
		/**  */
		public static final EnumType R4m_DIN_A4 = new EnumType("R4m-DIN-A4");
		/**  */
		public static final EnumType R4m_DIN_A5 = new EnumType("R4m-DIN-A5");
		/**  */
		public static final EnumType R4m_swedish = new EnumType("R4m-swedish");
		/**  */
		public static final EnumType R4i_US = new EnumType("R4i-US");
		/**  */
		public static final EnumType R5_generic = new EnumType("R5-generic");
		/**  */
		public static final EnumType R5i_US_a = new EnumType("R5i-US-a");
		/**  */
		public static final EnumType R5i_US_b = new EnumType("R5i-US-b");
		/**  */
		public static final EnumType R5i_US_c = new EnumType("R5i-US-c");
		/**  */
		public static final EnumType R6_generic = new EnumType("R6-generic");
		/**  */
		public static final EnumType R6m_4h2s = new EnumType("R6m-4h2s");
		/**  */
		public static final EnumType R6m_DIN_A5 = new EnumType("R6m-DIN-A5");
		/**  */
		public static final EnumType R7_generic = new EnumType("R7-generic");
		/**  */
		public static final EnumType R7i_US_a = new EnumType("R7i-US-a");
		/**  */
		public static final EnumType R7i_US_b = new EnumType("R7i-US-b");
		/**  */
		public static final EnumType R7i_US_c = new EnumType("R7i-US-c");
		/**  */
		public static final EnumType R11m_7h4s = new EnumType("R11m-7h4s");
		/**  */
		public static final EnumType P16_9i_rect_0t = new EnumType("P16_9i-rect-0t");
		/**  */
		public static final EnumType P12m_rect_0t = new EnumType("P12m-rect-0t");
		/**  */
		public static final EnumType W2_1i_round_0t = new EnumType("W2_1i-round-0t");
		/**  */
		public static final EnumType W2_1i_square_0t = new EnumType("W2_1i-square-0t");
		/**  */
		public static final EnumType W3_1i_square_0t = new EnumType("W3_1i-square-0t");
		/**  */
		public static final EnumType C9_5m_round_0t = new EnumType("C9.5m-round-0t");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Brand ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Brand
	 * 
	 * @param value the value to set the attribute to
	 */
	@Override
	public void setBrand(String value)
	{
		setAttribute(AttributeName.BRAND, value, null);
	}

	/**
	 * (23) get String attribute Brand
	 * 
	 * @return the value of the attribute
	 */
	@Override
	public String getBrand()
	{
		return getAttribute(AttributeName.BRAND, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Color ---------------------------------------------------------------------
	 */
	/**
	 * (13) set attribute Color
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setColor(EnumNamedColor value)
	{
		setAttribute(AttributeName.COLOR, value == null ? null : value.getName(), null);
	}

	/**
	 * (19) get EnumNamedColor attribute Color
	 * 
	 * @return EnumNamedColor the value of the attribute
	 */
	public EnumNamedColor getColor()
	{
		String strAttrName = "";
		EnumNamedColor nPlaceHolder = null;
		strAttrName = getAttribute(AttributeName.COLOR, null, JDFCoreConstants.EMPTYSTRING);
		nPlaceHolder = EnumNamedColor.getEnum(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorDetails ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ColorDetails
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setColorDetails(String value)
	{
		setAttribute(AttributeName.COLORDETAILS, value, null);
	}

	/**
	 * (23) get String attribute ColorDetails
	 * 
	 * @return the value of the attribute
	 */
	public String getColorDetails()
	{
		return getAttribute(AttributeName.COLORDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Diameter ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Diameter
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setDiameter(double value)
	{
		setAttribute(AttributeName.DIAMETER, value, null);
	}

	/**
	 * (17) get double attribute Diameter
	 * 
	 * @return double the value of the attribute
	 */
	public double getDiameter()
	{
		return getRealAttribute(AttributeName.DIAMETER, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Thickness ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Thickness
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setThickness(double value)
	{
		setAttribute(AttributeName.THICKNESS, value, null);
	}

	/**
	 * (17) get double attribute Thickness
	 * 
	 * @return double the value of the attribute
	 */
	public double getThickness()
	{
		return getRealAttribute(AttributeName.THICKNESS, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Type ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Type
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setType(EnumType enumVar)
	{
		setAttribute(AttributeName.TYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Type
	 * 
	 * @return the value of the attribute
	 */
	public EnumType getType()
	{
		return EnumType.getEnum(getAttribute(AttributeName.TYPE, null, null));
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element HoleMakingParams
	 * 
	 * @return JDFHoleMakingParams the element
	 */
	public JDFHoleMakingParams getHoleMakingParams()
	{
		return (JDFHoleMakingParams) getElement(ElementName.HOLEMAKINGPARAMS, null, 0);
	}

	/**
	 * (25) getCreateHoleMakingParams
	 * 
	 * @return JDFHoleMakingParams the element
	 */
	public JDFHoleMakingParams getCreateHoleMakingParams()
	{
		return (JDFHoleMakingParams) getCreateElement(ElementName.HOLEMAKINGPARAMS, null, 0);
	}

	/**
	 * (29) append element HoleMakingParams
	 * 
	 * @return JDFHoleMakingParams the element
	 * @throws JDFException if the element already exists
	 */
	public JDFHoleMakingParams appendHoleMakingParams() throws JDFException
	{
		return (JDFHoleMakingParams) appendElementN(ElementName.HOLEMAKINGPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refHoleMakingParams(JDFHoleMakingParams refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
