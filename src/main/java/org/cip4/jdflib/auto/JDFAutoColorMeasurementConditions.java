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
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoColorMeasurementConditions : public JDFResource
 */

public abstract class JDFAutoColorMeasurementConditions extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[13];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DENSITYSTANDARD, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				EnumDensityStandard.getEnum(0), "ANSIT");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ILLUMINATION, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumIllumination.getEnum(0),
				"D50");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.OBSERVER, 0x3333333331l, AttributeInfo.EnumAttributeType.integer, null, "2");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.APERTURE, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ILLUMINATIONANGLE, 0x3333333331l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.INKSTATE, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumInkState.getEnum(0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.INSTRUMENTATION, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.MEASUREMENTANGLE, 0x3333333331l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.MEASUREMENTFILTER, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				EnumMeasurementFilter.getEnum(0), null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.MEASUREMENTMODE, 0x3333333331l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.SAMPLEBACKING, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				EnumSampleBacking.getEnum(0), null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.SPECTRALRESOLUTION, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.WHITEBASE, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumWhiteBase.getEnum(0),
				null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoColorMeasurementConditions
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoColorMeasurementConditions(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoColorMeasurementConditions
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoColorMeasurementConditions(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoColorMeasurementConditions
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoColorMeasurementConditions(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		boolean bRet = super.init();
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
	 * Enumeration strings for DensityStandard
	 */

	public enum EDensityStandard
	{
		ANSIA, ANSIE, ANSII, ANSIT, DIN16536, DIN16536NB;

		public static EDensityStandard getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EDensityStandard.class, val, EDensityStandard.ANSIT);
		}
	}

	/**
	 * Enumeration strings for DensityStandard
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumDensityStandard extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumDensityStandard(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumDensityStandard getEnum(String enumName)
		{
			return (EnumDensityStandard) getEnum(EnumDensityStandard.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumDensityStandard getEnum(int enumValue)
		{
			return (EnumDensityStandard) getEnum(EnumDensityStandard.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDensityStandard.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDensityStandard.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDensityStandard.class);
		}

		/**  */
		public static final EnumDensityStandard ANSIA = new EnumDensityStandard("ANSIA");
		/**  */
		public static final EnumDensityStandard ANSIE = new EnumDensityStandard("ANSIE");
		/**  */
		public static final EnumDensityStandard ANSII = new EnumDensityStandard("ANSII");
		/**  */
		public static final EnumDensityStandard ANSIT = new EnumDensityStandard("ANSIT");
		/**  */
		public static final EnumDensityStandard DIN16536 = new EnumDensityStandard("DIN16536");
		/**  */
		public static final EnumDensityStandard DIN16536NB = new EnumDensityStandard("DIN16536NB");
	}

	/**
	 * Enumeration strings for Illumination
	 */

	public enum EIllumination
	{
		D50, D65, Unknown;

		public static EIllumination getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EIllumination.class, val, EIllumination.D50);
		}
	}

	/**
	 * Enumeration strings for Illumination
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumIllumination extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumIllumination(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumIllumination getEnum(String enumName)
		{
			return (EnumIllumination) getEnum(EnumIllumination.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumIllumination getEnum(int enumValue)
		{
			return (EnumIllumination) getEnum(EnumIllumination.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumIllumination.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumIllumination.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumIllumination.class);
		}

		/**  */
		public static final EnumIllumination D50 = new EnumIllumination("D50");
		/**  */
		public static final EnumIllumination D65 = new EnumIllumination("D65");
		/**  */
		public static final EnumIllumination Unknown = new EnumIllumination("Unknown");
	}

	/**
	 * Enumeration strings for InkState
	 */

	public enum EInkState
	{
		Dry, Wet, NA;

		public static EInkState getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EInkState.class, val, null);
		}
	}

	/**
	 * Enumeration strings for InkState
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumInkState extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumInkState(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumInkState getEnum(String enumName)
		{
			return (EnumInkState) getEnum(EnumInkState.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumInkState getEnum(int enumValue)
		{
			return (EnumInkState) getEnum(EnumInkState.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumInkState.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumInkState.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumInkState.class);
		}

		/**  */
		public static final EnumInkState Dry = new EnumInkState("Dry");
		/**  */
		public static final EnumInkState Wet = new EnumInkState("Wet");
		/**  */
		public static final EnumInkState NA = new EnumInkState("NA");
	}

	/**
	 * Enumeration strings for MeasurementFilter
	 */

	public enum EMeasurementFilter
	{
		None, Pol, UV;

		public static EMeasurementFilter getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EMeasurementFilter.class, val, null);
		}
	}

	/**
	 * Enumeration strings for MeasurementFilter
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumMeasurementFilter extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumMeasurementFilter(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumMeasurementFilter getEnum(String enumName)
		{
			return (EnumMeasurementFilter) getEnum(EnumMeasurementFilter.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumMeasurementFilter getEnum(int enumValue)
		{
			return (EnumMeasurementFilter) getEnum(EnumMeasurementFilter.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumMeasurementFilter.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumMeasurementFilter.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumMeasurementFilter.class);
		}

		/**  */
		public static final EnumMeasurementFilter None = new EnumMeasurementFilter("None");
		/**  */
		public static final EnumMeasurementFilter Pol = new EnumMeasurementFilter("Pol");
		/**  */
		public static final EnumMeasurementFilter UV = new EnumMeasurementFilter("UV");
	}

	/**
	 * Enumeration strings for SampleBacking
	 */

	public enum ESampleBacking
	{
		Black, White, NA;

		public static ESampleBacking getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ESampleBacking.class, val, null);
		}
	}

	/**
	 * Enumeration strings for SampleBacking
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSampleBacking extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSampleBacking(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSampleBacking getEnum(String enumName)
		{
			return (EnumSampleBacking) getEnum(EnumSampleBacking.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSampleBacking getEnum(int enumValue)
		{
			return (EnumSampleBacking) getEnum(EnumSampleBacking.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSampleBacking.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSampleBacking.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSampleBacking.class);
		}

		/**  */
		public static final EnumSampleBacking Black = new EnumSampleBacking("Black");
		/**  */
		public static final EnumSampleBacking White = new EnumSampleBacking("White");
		/**  */
		public static final EnumSampleBacking NA = new EnumSampleBacking("NA");
	}

	/**
	 * Enumeration strings for WhiteBase
	 */

	public enum EWhiteBase
	{
		Absolute, Paper, Substrate;

		public static EWhiteBase getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EWhiteBase.class, val, null);
		}
	}

	/**
	 * Enumeration strings for WhiteBase
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumWhiteBase extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumWhiteBase(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumWhiteBase getEnum(String enumName)
		{
			return (EnumWhiteBase) getEnum(EnumWhiteBase.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumWhiteBase getEnum(int enumValue)
		{
			return (EnumWhiteBase) getEnum(EnumWhiteBase.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumWhiteBase.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumWhiteBase.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumWhiteBase.class);
		}

		/**  */
		public static final EnumWhiteBase Absolute = new EnumWhiteBase("Absolute");
		/**  */
		public static final EnumWhiteBase Paper = new EnumWhiteBase("Paper");
		/**  */
		public static final EnumWhiteBase Substrate = new EnumWhiteBase("Substrate");
	}

	/*
	 * ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DensityStandard
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DensityStandard
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDensityStandard(EDensityStandard enumVar)
	{
		setAttribute(AttributeName.DENSITYSTANDARD, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute DensityStandard
	 *
	 * @return the value of the attribute
	 */
	public EDensityStandard getEDensityStandard()
	{
		return EDensityStandard.getEnum(getAttribute(AttributeName.DENSITYSTANDARD, null, "ANSIT"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DensityStandard
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DensityStandard
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetDensityStandard(EDensityStandard) based on java.lang.enum instead
	 */
	@Deprecated
	public void setDensityStandard(EnumDensityStandard enumVar)
	{
		setAttribute(AttributeName.DENSITYSTANDARD, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute DensityStandard
	 *
	 * @return the value of the attribute
	 * @deprecated use EDensityStandard GetEDensityStandard() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumDensityStandard getDensityStandard()
	{
		return EnumDensityStandard.getEnum(getAttribute(AttributeName.DENSITYSTANDARD, null, "ANSIT"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Illumination
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Illumination
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setIllumination(EIllumination enumVar)
	{
		setAttribute(AttributeName.ILLUMINATION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Illumination
	 *
	 * @return the value of the attribute
	 */
	public EIllumination getEIllumination()
	{
		return EIllumination.getEnum(getAttribute(AttributeName.ILLUMINATION, null, "D50"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Illumination
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Illumination
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetIllumination(EIllumination) based on java.lang.enum instead
	 */
	@Deprecated
	public void setIllumination(EnumIllumination enumVar)
	{
		setAttribute(AttributeName.ILLUMINATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Illumination
	 *
	 * @return the value of the attribute
	 * @deprecated use EIllumination GetEIllumination() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumIllumination getIllumination()
	{
		return EnumIllumination.getEnum(getAttribute(AttributeName.ILLUMINATION, null, "D50"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Observer
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Observer
	 *
	 * @param value the value to set the attribute to
	 */
	public void setObserver(int value)
	{
		setAttribute(AttributeName.OBSERVER, value, null);
	}

	/**
	 * (15) get int attribute Observer
	 *
	 * @return int the value of the attribute
	 */
	public int getObserver()
	{
		return getIntAttribute(AttributeName.OBSERVER, null, 2);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Aperture
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Aperture
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAperture(double value)
	{
		setAttribute(AttributeName.APERTURE, value, null);
	}

	/**
	 * (17) get double attribute Aperture
	 *
	 * @return double the value of the attribute
	 */
	public double getAperture()
	{
		return getRealAttribute(AttributeName.APERTURE, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IlluminationAngle
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IlluminationAngle
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIlluminationAngle(int value)
	{
		setAttribute(AttributeName.ILLUMINATIONANGLE, value, null);
	}

	/**
	 * (15) get int attribute IlluminationAngle
	 *
	 * @return int the value of the attribute
	 */
	public int getIlluminationAngle()
	{
		return getIntAttribute(AttributeName.ILLUMINATIONANGLE, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute InkState
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute InkState
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setInkState(EInkState enumVar)
	{
		setAttribute(AttributeName.INKSTATE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute InkState
	 *
	 * @return the value of the attribute
	 */
	public EInkState getEInkState()
	{
		return EInkState.getEnum(getAttribute(AttributeName.INKSTATE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute InkState
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute InkState
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetInkState(EInkState) based on java.lang.enum instead
	 */
	@Deprecated
	public void setInkState(EnumInkState enumVar)
	{
		setAttribute(AttributeName.INKSTATE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute InkState
	 *
	 * @return the value of the attribute
	 * @deprecated use EInkState GetEInkState() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumInkState getInkState()
	{
		return EnumInkState.getEnum(getAttribute(AttributeName.INKSTATE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Instrumentation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Instrumentation
	 *
	 * @param value the value to set the attribute to
	 */
	public void setInstrumentation(String value)
	{
		setAttribute(AttributeName.INSTRUMENTATION, value, null);
	}

	/**
	 * (23) get String attribute Instrumentation
	 *
	 * @return the value of the attribute
	 */
	public String getInstrumentation()
	{
		return getAttribute(AttributeName.INSTRUMENTATION, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MeasurementAngle
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MeasurementAngle
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMeasurementAngle(int value)
	{
		setAttribute(AttributeName.MEASUREMENTANGLE, value, null);
	}

	/**
	 * (15) get int attribute MeasurementAngle
	 *
	 * @return int the value of the attribute
	 */
	public int getMeasurementAngle()
	{
		return getIntAttribute(AttributeName.MEASUREMENTANGLE, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MeasurementFilter
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MeasurementFilter
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMeasurementFilter(EMeasurementFilter enumVar)
	{
		setAttribute(AttributeName.MEASUREMENTFILTER, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute MeasurementFilter
	 *
	 * @return the value of the attribute
	 */
	public EMeasurementFilter getEMeasurementFilter()
	{
		return EMeasurementFilter.getEnum(getAttribute(AttributeName.MEASUREMENTFILTER, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MeasurementFilter
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MeasurementFilter
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetMeasurementFilter(EMeasurementFilter) based on java.lang.enum instead
	 */
	@Deprecated
	public void setMeasurementFilter(EnumMeasurementFilter enumVar)
	{
		setAttribute(AttributeName.MEASUREMENTFILTER, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute MeasurementFilter
	 *
	 * @return the value of the attribute
	 * @deprecated use EMeasurementFilter GetEMeasurementFilter() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumMeasurementFilter getMeasurementFilter()
	{
		return EnumMeasurementFilter.getEnum(getAttribute(AttributeName.MEASUREMENTFILTER, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MeasurementMode
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MeasurementMode
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMeasurementMode(String value)
	{
		setAttribute(AttributeName.MEASUREMENTMODE, value, null);
	}

	/**
	 * (23) get String attribute MeasurementMode
	 *
	 * @return the value of the attribute
	 */
	public String getMeasurementMode()
	{
		return getAttribute(AttributeName.MEASUREMENTMODE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SampleBacking
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SampleBacking
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSampleBacking(ESampleBacking enumVar)
	{
		setAttribute(AttributeName.SAMPLEBACKING, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute SampleBacking
	 *
	 * @return the value of the attribute
	 */
	public ESampleBacking getESampleBacking()
	{
		return ESampleBacking.getEnum(getAttribute(AttributeName.SAMPLEBACKING, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SampleBacking
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SampleBacking
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetSampleBacking(ESampleBacking) based on java.lang.enum instead
	 */
	@Deprecated
	public void setSampleBacking(EnumSampleBacking enumVar)
	{
		setAttribute(AttributeName.SAMPLEBACKING, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute SampleBacking
	 *
	 * @return the value of the attribute
	 * @deprecated use ESampleBacking GetESampleBacking() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumSampleBacking getSampleBacking()
	{
		return EnumSampleBacking.getEnum(getAttribute(AttributeName.SAMPLEBACKING, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SpectralResolution
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SpectralResolution
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSpectralResolution(double value)
	{
		setAttribute(AttributeName.SPECTRALRESOLUTION, value, null);
	}

	/**
	 * (17) get double attribute SpectralResolution
	 *
	 * @return double the value of the attribute
	 */
	public double getSpectralResolution()
	{
		return getRealAttribute(AttributeName.SPECTRALRESOLUTION, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute WhiteBase
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute WhiteBase
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setWhiteBase(EWhiteBase enumVar)
	{
		setAttribute(AttributeName.WHITEBASE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute WhiteBase
	 *
	 * @return the value of the attribute
	 */
	public EWhiteBase getEWhiteBase()
	{
		return EWhiteBase.getEnum(getAttribute(AttributeName.WHITEBASE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute WhiteBase
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute WhiteBase
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetWhiteBase(EWhiteBase) based on java.lang.enum instead
	 */
	@Deprecated
	public void setWhiteBase(EnumWhiteBase enumVar)
	{
		setAttribute(AttributeName.WHITEBASE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute WhiteBase
	 *
	 * @return the value of the attribute
	 * @deprecated use EWhiteBase GetEWhiteBase() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumWhiteBase getWhiteBase()
	{
		return EnumWhiteBase.getEnum(getAttribute(AttributeName.WHITEBASE, null, null));
	}

}
