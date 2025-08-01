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
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoDCTParams : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoDCTParams extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.SOURCECSS, 0x4444333311l, AttributeInfo.EnumAttributeType.enumerations, EnumSourceCSs.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.HSAMPLES, 0x3333333311l, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.VSAMPLES, 0x3333333311l, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.QFACTOR, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, "1.0");
		atrInfoTable[4] = new AtrInfoTable(AttributeName.QUANTTABLE, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.HUFFTABLE, 0x3333333311l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.COLORTRANSFORM, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumColorTransform.getEnum(0), "Automatic");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoDCTParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDCTParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDCTParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDCTParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDCTParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDCTParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for SourceCSs
	 */

	public enum ESourceCSs
	{
		CalGray, CalRGB, Calibrated, CIEBased, CMYK, DeviceN, DevIndep, RGB, Gray, ICCBased, ICCCMYK, ICCGray, ICCLAB, ICCRGB, Lab, Separation, YUV, All;

		public static ESourceCSs getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ESourceCSs.class, val, null);
		}
	}

	/**
	 * Enumeration strings for SourceCSs
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSourceCSs extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSourceCSs(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSourceCSs getEnum(String enumName)
		{
			return (EnumSourceCSs) getEnum(EnumSourceCSs.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSourceCSs getEnum(int enumValue)
		{
			return (EnumSourceCSs) getEnum(EnumSourceCSs.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSourceCSs.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSourceCSs.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSourceCSs.class);
		}

		/**  */
		public static final EnumSourceCSs CalGray = new EnumSourceCSs("CalGray");
		/**  */
		public static final EnumSourceCSs CalRGB = new EnumSourceCSs("CalRGB");
		/**  */
		public static final EnumSourceCSs Calibrated = new EnumSourceCSs("Calibrated");
		/**  */
		public static final EnumSourceCSs CIEBased = new EnumSourceCSs("CIEBased");
		/**  */
		public static final EnumSourceCSs CMYK = new EnumSourceCSs("CMYK");
		/**  */
		public static final EnumSourceCSs DeviceN = new EnumSourceCSs("DeviceN");
		/**  */
		public static final EnumSourceCSs DevIndep = new EnumSourceCSs("DevIndep");
		/**  */
		public static final EnumSourceCSs RGB = new EnumSourceCSs("RGB");
		/**  */
		public static final EnumSourceCSs Gray = new EnumSourceCSs("Gray");
		/**  */
		public static final EnumSourceCSs ICCBased = new EnumSourceCSs("ICCBased");
		/**  */
		public static final EnumSourceCSs ICCCMYK = new EnumSourceCSs("ICCCMYK");
		/**  */
		public static final EnumSourceCSs ICCGray = new EnumSourceCSs("ICCGray");
		/**  */
		public static final EnumSourceCSs ICCLAB = new EnumSourceCSs("ICCLAB");
		/**  */
		public static final EnumSourceCSs ICCRGB = new EnumSourceCSs("ICCRGB");
		/**  */
		public static final EnumSourceCSs Lab = new EnumSourceCSs("Lab");
		/**  */
		public static final EnumSourceCSs Separation = new EnumSourceCSs("Separation");
		/**  */
		public static final EnumSourceCSs YUV = new EnumSourceCSs("YUV");
		/**  */
		public static final EnumSourceCSs All = new EnumSourceCSs("All");
	}

	/**
	 * Enumeration strings for ColorTransform
	 */

	public enum EColorTransform
	{
		YUV, None, Automatic;

		public static EColorTransform getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EColorTransform.class, val, EColorTransform.Automatic);
		}
	}

	/**
	 * Enumeration strings for ColorTransform
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumColorTransform extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumColorTransform(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumColorTransform getEnum(String enumName)
		{
			return (EnumColorTransform) getEnum(EnumColorTransform.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumColorTransform getEnum(int enumValue)
		{
			return (EnumColorTransform) getEnum(EnumColorTransform.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumColorTransform.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumColorTransform.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumColorTransform.class);
		}

		/**  */
		public static final EnumColorTransform YUV = new EnumColorTransform("YUV");
		/**  */
		public static final EnumColorTransform None = new EnumColorTransform("None");
		/**  */
		public static final EnumColorTransform Automatic = new EnumColorTransform("Automatic");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SourceCSs ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute SourceCSs
	 *
	 * @param v List of the enumeration values
	 */
	public void setESourceCSs(List<ESourceCSs> v)
	{
		setEnumsAttribute(AttributeName.SOURCECSS, v, null);
	}

	/**
	 * (9.2) get SourceCSs attribute SourceCSs
	 *
	 * @return Vector of the enumerations
	 */
	public List<ESourceCSs> getEnumsSourceCSs()
	{
		return getEnumerationsAttribute(AttributeName.SOURCECSS, null, ESourceCSs.class);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SourceCSs ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute SourceCSs
	 *
	 * @param v List of the enumeration values
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setSourceCSs(List<EnumSourceCSs> v)
	{
		setEnumerationsAttribute(AttributeName.SOURCECSS, v, null);
	}

	/**
	 * (9.2) get SourceCSs attribute SourceCSs
	 *
	 * @return Vector of the enumerations
	 */
	public Vector<EnumSourceCSs> getSourceCSs()
	{
		return getEnumerationsAttribute(AttributeName.SOURCECSS, null, EnumSourceCSs.getEnum(0), false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute HSamples ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute HSamples
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHSamples(JDFIntegerList value)
	{
		setAttribute(AttributeName.HSAMPLES, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute HSamples
	 *
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getHSamples()
	{
		final String strAttrName = getAttribute(AttributeName.HSAMPLES, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute VSamples ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute VSamples
	 *
	 * @param value the value to set the attribute to
	 */
	public void setVSamples(JDFIntegerList value)
	{
		setAttribute(AttributeName.VSAMPLES, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute VSamples
	 *
	 * @return JDFIntegerList the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getVSamples()
	{
		final String strAttrName = getAttribute(AttributeName.VSAMPLES, null, null);
		final JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute QFactor ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute QFactor
	 *
	 * @param value the value to set the attribute to
	 */
	public void setQFactor(double value)
	{
		setAttribute(AttributeName.QFACTOR, value, null);
	}

	/**
	 * (17) get double attribute QFactor
	 *
	 * @return double the value of the attribute
	 */
	public double getQFactor()
	{
		return getRealAttribute(AttributeName.QFACTOR, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute QuantTable ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute QuantTable
	 *
	 * @param value the value to set the attribute to
	 */
	public void setQuantTable(JDFNumberList value)
	{
		setAttribute(AttributeName.QUANTTABLE, value, null);
	}

	/**
	 * (20) get JDFNumberList attribute QuantTable
	 *
	 * @return JDFNumberList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberList
	 */
	public JDFNumberList getQuantTable()
	{
		final String strAttrName = getAttribute(AttributeName.QUANTTABLE, null, null);
		final JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute HuffTable ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute HuffTable
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHuffTable(JDFNumberList value)
	{
		setAttribute(AttributeName.HUFFTABLE, value, null);
	}

	/**
	 * (20) get JDFNumberList attribute HuffTable
	 *
	 * @return JDFNumberList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberList
	 */
	public JDFNumberList getHuffTable()
	{
		final String strAttrName = getAttribute(AttributeName.HUFFTABLE, null, null);
		final JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorTransform
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ColorTransform
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setColorTransform(EColorTransform enumVar)
	{
		setAttribute(AttributeName.COLORTRANSFORM, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ColorTransform
	 *
	 * @return the value of the attribute
	 */
	public EColorTransform getEColorTransform()
	{
		return EColorTransform.getEnum(getAttribute(AttributeName.COLORTRANSFORM, null, "Automatic"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorTransform
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ColorTransform
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setColorTransform(EnumColorTransform enumVar)
	{
		setAttribute(AttributeName.COLORTRANSFORM, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ColorTransform
	 *
	 * @return the value of the attribute
	 */
	public EnumColorTransform getColorTransform()
	{
		return EnumColorTransform.getEnum(getAttribute(AttributeName.COLORTRANSFORM, null, "Automatic"));
	}

}
