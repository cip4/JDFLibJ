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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.datatypes.JDFCMYKColor;
import org.cip4.jdflib.datatypes.JDFLabColor;
import org.cip4.jdflib.datatypes.JDFRGBColor;
import org.cip4.jdflib.datatypes.JDFTransferFunction;
import org.cip4.jdflib.resource.JDFColorMeasurementConditions;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFDeviceNColor;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFPrintConditionColor;
import org.cip4.jdflib.resource.process.JDFTransferCurve;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoColor : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoColor extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[22];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.MAPPINGSELECTION, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumMappingSelection.getEnum(0),
				"UsePDLValues");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.NAME, 0x2222222222l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ACTUALCOLORNAME, 0x3333333111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.CMYK, 0x3333333333l, AttributeInfo.EnumAttributeType.CMYKColor, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.COLORBOOK, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.COLORBOOKENTRY, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.COLORBOOKPREFIX, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.COLORBOOKSUFFIX, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.COLORDETAILS, 0x3333331111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.COLORNAME, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.COLORTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumColorType.getEnum(0), null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.COLORTYPEDETAILS, 0x3333311111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.DENSITY, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.GRAY, 0x3333331111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.LAB, 0x3333333333l, AttributeInfo.EnumAttributeType.LabColor, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.MEDIATYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.NEUTRALDENSITY, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.PRINTINGTECHNOLOGY, 0x3333311111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.RAWNAME, 0x3333333311l, AttributeInfo.EnumAttributeType.hexBinary, null, null);
		atrInfoTable[19] = new AtrInfoTable(AttributeName.SPECTRUM, 0x3331111111l, AttributeInfo.EnumAttributeType.TransferFunction, null, null);
		atrInfoTable[20] = new AtrInfoTable(AttributeName.SRGB, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[21] = new AtrInfoTable(AttributeName.USEPDLALTERNATECS, 0x4444444433l, AttributeInfo.EnumAttributeType.boolean_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[5];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COLORMEASUREMENTCONDITIONS, 0x6666666666l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.FILESPEC, 0x3333333333l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.DEVICENCOLOR, 0x3333333333l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.PRINTCONDITIONCOLOR, 0x3333333311l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.TRANSFERCURVE, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoColor
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoColor(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoColor
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoColor(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoColor
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoColor(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for MappingSelection
	 */

	public enum EMappingSelection
	{
		UsePDLValues, UseLocalPrinterValues, UseProcessColorValues;

		public static EMappingSelection getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EMappingSelection.class, val, EMappingSelection.UsePDLValues);
		}
	}

	/**
	 * Enumeration strings for MappingSelection
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumMappingSelection extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumMappingSelection(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumMappingSelection getEnum(String enumName)
		{
			return (EnumMappingSelection) getEnum(EnumMappingSelection.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumMappingSelection getEnum(int enumValue)
		{
			return (EnumMappingSelection) getEnum(EnumMappingSelection.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumMappingSelection.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumMappingSelection.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumMappingSelection.class);
		}

		/**  */
		public static final EnumMappingSelection UsePDLValues = new EnumMappingSelection("UsePDLValues");
		/**  */
		public static final EnumMappingSelection UseLocalPrinterValues = new EnumMappingSelection("UseLocalPrinterValues");
		/**  */
		public static final EnumMappingSelection UseProcessColorValues = new EnumMappingSelection("UseProcessColorValues");
	}

	/**
	 * Enumeration strings for ColorType
	 */

	public enum EColorType
	{
		DieLine, Normal, Opaque, OpaqueIgnore, Primer, Transparent;

		public static EColorType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EColorType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for ColorType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumColorType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumColorType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumColorType getEnum(String enumName)
		{
			return (EnumColorType) getEnum(EnumColorType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumColorType getEnum(int enumValue)
		{
			return (EnumColorType) getEnum(EnumColorType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumColorType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumColorType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumColorType.class);
		}

		/**  */
		public static final EnumColorType DieLine = new EnumColorType("DieLine");
		/**  */
		public static final EnumColorType Normal = new EnumColorType("Normal");
		/**  */
		public static final EnumColorType Opaque = new EnumColorType("Opaque");
		/**  */
		public static final EnumColorType OpaqueIgnore = new EnumColorType("OpaqueIgnore");
		/**  */
		public static final EnumColorType Primer = new EnumColorType("Primer");
		/**  */
		public static final EnumColorType Transparent = new EnumColorType("Transparent");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MappingSelection
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MappingSelection
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMappingSelection(EMappingSelection enumVar)
	{
		setAttribute(AttributeName.MAPPINGSELECTION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute MappingSelection
	 *
	 * @return the value of the attribute
	 */
	public EMappingSelection getEMappingSelection()
	{
		return EMappingSelection.getEnum(getAttribute(AttributeName.MAPPINGSELECTION, null, "UsePDLValues"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MappingSelection
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MappingSelection
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setMappingSelection(EnumMappingSelection enumVar)
	{
		setAttribute(AttributeName.MAPPINGSELECTION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute MappingSelection
	 *
	 * @return the value of the attribute
	 */
	public EnumMappingSelection getMappingSelection()
	{
		return EnumMappingSelection.getEnum(getAttribute(AttributeName.MAPPINGSELECTION, null, "UsePDLValues"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Name ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Name
	 *
	 * @param value the value to set the attribute to
	 */
	public void setName(String value)
	{
		setAttribute(AttributeName.NAME, value, null);
	}

	/**
	 * (23) get String attribute Name
	 *
	 * @return the value of the attribute
	 */
	public String getName()
	{
		return getAttribute(AttributeName.NAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ActualColorName
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ActualColorName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setActualColorName(String value)
	{
		setAttribute(AttributeName.ACTUALCOLORNAME, value, null);
	}

	/**
	 * (23) get String attribute ActualColorName
	 *
	 * @return the value of the attribute
	 */
	public String getActualColorName()
	{
		return getAttribute(AttributeName.ACTUALCOLORNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CMYK ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CMYK
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCMYK(JDFCMYKColor value)
	{
		setAttribute(AttributeName.CMYK, value, null);
	}

	/**
	 * (20) get JDFCMYKColor attribute CMYK
	 *
	 * @return JDFCMYKColor the value of the attribute, null if a the attribute value is not a valid to create a JDFCMYKColor
	 */
	public JDFCMYKColor getCMYK()
	{
		final String strAttrName = getAttribute(AttributeName.CMYK, null, null);
		final JDFCMYKColor nPlaceHolder = JDFCMYKColor.createCMYKColor(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorBook ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ColorBook
	 *
	 * @param value the value to set the attribute to
	 */
	public void setColorBook(String value)
	{
		setAttribute(AttributeName.COLORBOOK, value, null);
	}

	/**
	 * (23) get String attribute ColorBook
	 *
	 * @return the value of the attribute
	 */
	public String getColorBook()
	{
		return getAttribute(AttributeName.COLORBOOK, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorBookEntry
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ColorBookEntry
	 *
	 * @param value the value to set the attribute to
	 */
	public void setColorBookEntry(String value)
	{
		setAttribute(AttributeName.COLORBOOKENTRY, value, null);
	}

	/**
	 * (23) get String attribute ColorBookEntry
	 *
	 * @return the value of the attribute
	 */
	public String getColorBookEntry()
	{
		return getAttribute(AttributeName.COLORBOOKENTRY, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorBookPrefix
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ColorBookPrefix
	 *
	 * @param value the value to set the attribute to
	 */
	public void setColorBookPrefix(String value)
	{
		setAttribute(AttributeName.COLORBOOKPREFIX, value, null);
	}

	/**
	 * (23) get String attribute ColorBookPrefix
	 *
	 * @return the value of the attribute
	 */
	public String getColorBookPrefix()
	{
		return getAttribute(AttributeName.COLORBOOKPREFIX, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorBookSuffix
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ColorBookSuffix
	 *
	 * @param value the value to set the attribute to
	 */
	public void setColorBookSuffix(String value)
	{
		setAttribute(AttributeName.COLORBOOKSUFFIX, value, null);
	}

	/**
	 * (23) get String attribute ColorBookSuffix
	 *
	 * @return the value of the attribute
	 */
	public String getColorBookSuffix()
	{
		return getAttribute(AttributeName.COLORBOOKSUFFIX, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorDetails
	 * ---------------------------------------------------------------------
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
	 * --------------------------------------------------------------------- Methods for Attribute ColorName ---------------------------------------------------------------------
	 */
	/**
	 * (13) set attribute ColorName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setColorName(EnumNamedColor value)
	{
		setAttribute(AttributeName.COLORNAME, value == null ? null : value.getName(), null);
	}

	/**
	 * (19) get EnumNamedColor attribute ColorName
	 *
	 * @return EnumNamedColor the value of the attribute
	 */
	public EnumNamedColor getColorName()
	{
		String strAttrName = "";
		EnumNamedColor nPlaceHolder = null;
		strAttrName = getAttribute(AttributeName.COLORNAME, null, JDFCoreConstants.EMPTYSTRING);
		nPlaceHolder = EnumNamedColor.getEnum(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ColorType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setColorType(EColorType enumVar)
	{
		setAttribute(AttributeName.COLORTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ColorType
	 *
	 * @return the value of the attribute
	 */
	public EColorType getEColorType()
	{
		return EColorType.getEnum(getAttribute(AttributeName.COLORTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ColorType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setColorType(EnumColorType enumVar)
	{
		setAttribute(AttributeName.COLORTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ColorType
	 *
	 * @return the value of the attribute
	 */
	public EnumColorType getColorType()
	{
		return EnumColorType.getEnum(getAttribute(AttributeName.COLORTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorTypeDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ColorTypeDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setColorTypeDetails(String value)
	{
		setAttribute(AttributeName.COLORTYPEDETAILS, value, null);
	}

	/**
	 * (23) get String attribute ColorTypeDetails
	 *
	 * @return the value of the attribute
	 */
	public String getColorTypeDetails()
	{
		return getAttribute(AttributeName.COLORTYPEDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Density ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Density
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDensity(double value)
	{
		setAttribute(AttributeName.DENSITY, value, null);
	}

	/**
	 * (17) get double attribute Density
	 *
	 * @return double the value of the attribute
	 */
	public double getDensity()
	{
		return getRealAttribute(AttributeName.DENSITY, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Gray ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Gray
	 *
	 * @param value the value to set the attribute to
	 */
	public void setGray(double value)
	{
		setAttribute(AttributeName.GRAY, value, null);
	}

	/**
	 * (17) get double attribute Gray
	 *
	 * @return double the value of the attribute
	 */
	public double getGray()
	{
		return getRealAttribute(AttributeName.GRAY, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Lab ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Lab
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLab(JDFLabColor value)
	{
		setAttribute(AttributeName.LAB, value, null);
	}

	/**
	 * (20) get JDFLabColor attribute Lab
	 *
	 * @return JDFLabColor the value of the attribute, null if a the attribute value is not a valid to create a JDFLabColor
	 */
	public JDFLabColor getLab()
	{
		final String strAttrName = getAttribute(AttributeName.LAB, null, null);
		final JDFLabColor nPlaceHolder = JDFLabColor.createLabColor(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MediaType ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MediaType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMediaType(String value)
	{
		setAttribute(AttributeName.MEDIATYPE, value, null);
	}

	/**
	 * (23) get String attribute MediaType
	 *
	 * @return the value of the attribute
	 */
	public String getMediaType()
	{
		return getAttribute(AttributeName.MEDIATYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NeutralDensity
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NeutralDensity
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNeutralDensity(double value)
	{
		setAttribute(AttributeName.NEUTRALDENSITY, value, null);
	}

	/**
	 * (17) get double attribute NeutralDensity
	 *
	 * @return double the value of the attribute
	 */
	public double getNeutralDensity()
	{
		return getRealAttribute(AttributeName.NEUTRALDENSITY, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PrintingTechnology
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PrintingTechnology
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPrintingTechnology(String value)
	{
		setAttribute(AttributeName.PRINTINGTECHNOLOGY, value, null);
	}

	/**
	 * (23) get String attribute PrintingTechnology
	 *
	 * @return the value of the attribute
	 */
	public String getPrintingTechnology()
	{
		return getAttribute(AttributeName.PRINTINGTECHNOLOGY, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RawName ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RawName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRawName(String value)
	{
		setAttribute(AttributeName.RAWNAME, value, null);
	}

	/**
	 * (23) get String attribute RawName
	 *
	 * @return the value of the attribute
	 */
	public String getRawName()
	{
		return getAttribute(AttributeName.RAWNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Spectrum ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Spectrum
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSpectrum(JDFTransferFunction value)
	{
		setAttribute(AttributeName.SPECTRUM, value, null);
	}

	/**
	 * (20) get JDFTransferFunction attribute Spectrum
	 *
	 * @return JDFTransferFunction the value of the attribute, null if a the attribute value is not a valid to create a JDFTransferFunction
	 */
	public JDFTransferFunction getSpectrum()
	{
		final String strAttrName = getAttribute(AttributeName.SPECTRUM, null, null);
		final JDFTransferFunction nPlaceHolder = JDFTransferFunction.createTransferFunction(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute sRGB ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute sRGB
	 *
	 * @param value the value to set the attribute to
	 */
	public void setsRGB(JDFRGBColor value)
	{
		setAttribute(AttributeName.SRGB, value, null);
	}

	/**
	 * (20) get JDFRGBColor attribute sRGB
	 *
	 * @return JDFRGBColor the value of the attribute, null if a the attribute value is not a valid to create a JDFRGBColor
	 */
	public JDFRGBColor getsRGB()
	{
		final String strAttrName = getAttribute(AttributeName.SRGB, null, null);
		final JDFRGBColor nPlaceHolder = JDFRGBColor.createRGBColor(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute UsePDLAlternateCS
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute UsePDLAlternateCS
	 *
	 * @param value the value to set the attribute to
	 */
	public void setUsePDLAlternateCS(boolean value)
	{
		setAttribute(AttributeName.USEPDLALTERNATECS, value, null);
	}

	/**
	 * (18) get boolean attribute UsePDLAlternateCS
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getUsePDLAlternateCS()
	{
		return getBoolAttribute(AttributeName.USEPDLALTERNATECS, null, false);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element ColorMeasurementConditions
	 *
	 * @return JDFColorMeasurementConditions the element
	 */
	public JDFColorMeasurementConditions getColorMeasurementConditions()
	{
		return (JDFColorMeasurementConditions) getElement(ElementName.COLORMEASUREMENTCONDITIONS, null, 0);
	}

	/**
	 * (25) getCreateColorMeasurementConditions
	 * 
	 * @return JDFColorMeasurementConditions the element
	 */
	public JDFColorMeasurementConditions getCreateColorMeasurementConditions()
	{
		return (JDFColorMeasurementConditions) getCreateElement_JDFElement(ElementName.COLORMEASUREMENTCONDITIONS, null, 0);
	}

	/**
	 * (29) append element ColorMeasurementConditions
	 *
	 * @return JDFColorMeasurementConditions the element @ if the element already exists
	 */
	public JDFColorMeasurementConditions appendColorMeasurementConditions()
	{
		return (JDFColorMeasurementConditions) appendElementN(ElementName.COLORMEASUREMENTCONDITIONS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refColorMeasurementConditions(JDFColorMeasurementConditions refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element FileSpec
	 *
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getFileSpec()
	{
		return (JDFFileSpec) getElement(ElementName.FILESPEC, null, 0);
	}

	/**
	 * (25) getCreateFileSpec
	 * 
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getCreateFileSpec()
	{
		return (JDFFileSpec) getCreateElement_JDFElement(ElementName.FILESPEC, null, 0);
	}

	/**
	 * (26) getCreateFileSpec
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getCreateFileSpec(int iSkip)
	{
		return (JDFFileSpec) getCreateElement_JDFElement(ElementName.FILESPEC, null, iSkip);
	}

	/**
	 * (27) const get element FileSpec
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFFileSpec the element default is getFileSpec(0)
	 */
	public JDFFileSpec getFileSpec(int iSkip)
	{
		return (JDFFileSpec) getElement(ElementName.FILESPEC, null, iSkip);
	}

	/**
	 * Get all FileSpec from the current element
	 * 
	 * @return Collection<JDFFileSpec>, null if none are available
	 */
	public Collection<JDFFileSpec> getAllFileSpec()
	{
		return getChildArrayByClass(JDFFileSpec.class, false, 0);
	}

	/**
	 * (30) append element FileSpec
	 *
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec appendFileSpec()
	{
		return (JDFFileSpec) appendElement(ElementName.FILESPEC, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refFileSpec(JDFFileSpec refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element DeviceNColor
	 *
	 * @return JDFDeviceNColor the element
	 */
	public JDFDeviceNColor getDeviceNColor()
	{
		return (JDFDeviceNColor) getElement(ElementName.DEVICENCOLOR, null, 0);
	}

	/**
	 * (25) getCreateDeviceNColor
	 * 
	 * @return JDFDeviceNColor the element
	 */
	public JDFDeviceNColor getCreateDeviceNColor()
	{
		return (JDFDeviceNColor) getCreateElement_JDFElement(ElementName.DEVICENCOLOR, null, 0);
	}

	/**
	 * (26) getCreateDeviceNColor
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDeviceNColor the element
	 */
	public JDFDeviceNColor getCreateDeviceNColor(int iSkip)
	{
		return (JDFDeviceNColor) getCreateElement_JDFElement(ElementName.DEVICENCOLOR, null, iSkip);
	}

	/**
	 * (27) const get element DeviceNColor
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDeviceNColor the element default is getDeviceNColor(0)
	 */
	public JDFDeviceNColor getDeviceNColor(int iSkip)
	{
		return (JDFDeviceNColor) getElement(ElementName.DEVICENCOLOR, null, iSkip);
	}

	/**
	 * Get all DeviceNColor from the current element
	 * 
	 * @return Collection<JDFDeviceNColor>, null if none are available
	 */
	public Collection<JDFDeviceNColor> getAllDeviceNColor()
	{
		return getChildArrayByClass(JDFDeviceNColor.class, false, 0);
	}

	/**
	 * (30) append element DeviceNColor
	 *
	 * @return JDFDeviceNColor the element
	 */
	public JDFDeviceNColor appendDeviceNColor()
	{
		return (JDFDeviceNColor) appendElement(ElementName.DEVICENCOLOR, null);
	}

	/**
	 * (24) const get element PrintConditionColor
	 *
	 * @return JDFPrintConditionColor the element
	 */
	public JDFPrintConditionColor getPrintConditionColor()
	{
		return (JDFPrintConditionColor) getElement(ElementName.PRINTCONDITIONCOLOR, null, 0);
	}

	/**
	 * (25) getCreatePrintConditionColor
	 * 
	 * @return JDFPrintConditionColor the element
	 */
	public JDFPrintConditionColor getCreatePrintConditionColor()
	{
		return (JDFPrintConditionColor) getCreateElement_JDFElement(ElementName.PRINTCONDITIONCOLOR, null, 0);
	}

	/**
	 * (26) getCreatePrintConditionColor
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPrintConditionColor the element
	 */
	public JDFPrintConditionColor getCreatePrintConditionColor(int iSkip)
	{
		return (JDFPrintConditionColor) getCreateElement_JDFElement(ElementName.PRINTCONDITIONCOLOR, null, iSkip);
	}

	/**
	 * (27) const get element PrintConditionColor
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPrintConditionColor the element default is getPrintConditionColor(0)
	 */
	public JDFPrintConditionColor getPrintConditionColor(int iSkip)
	{
		return (JDFPrintConditionColor) getElement(ElementName.PRINTCONDITIONCOLOR, null, iSkip);
	}

	/**
	 * Get all PrintConditionColor from the current element
	 * 
	 * @return Collection<JDFPrintConditionColor>, null if none are available
	 */
	public Collection<JDFPrintConditionColor> getAllPrintConditionColor()
	{
		return getChildArrayByClass(JDFPrintConditionColor.class, false, 0);
	}

	/**
	 * (30) append element PrintConditionColor
	 *
	 * @return JDFPrintConditionColor the element
	 */
	public JDFPrintConditionColor appendPrintConditionColor()
	{
		return (JDFPrintConditionColor) appendElement(ElementName.PRINTCONDITIONCOLOR, null);
	}

	/**
	 * (24) const get element TransferCurve
	 *
	 * @return JDFTransferCurve the element
	 */
	public JDFTransferCurve getTransferCurve()
	{
		return (JDFTransferCurve) getElement(ElementName.TRANSFERCURVE, null, 0);
	}

	/**
	 * (25) getCreateTransferCurve
	 * 
	 * @return JDFTransferCurve the element
	 */
	public JDFTransferCurve getCreateTransferCurve()
	{
		return (JDFTransferCurve) getCreateElement_JDFElement(ElementName.TRANSFERCURVE, null, 0);
	}

	/**
	 * (26) getCreateTransferCurve
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFTransferCurve the element
	 */
	public JDFTransferCurve getCreateTransferCurve(int iSkip)
	{
		return (JDFTransferCurve) getCreateElement_JDFElement(ElementName.TRANSFERCURVE, null, iSkip);
	}

	/**
	 * (27) const get element TransferCurve
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFTransferCurve the element default is getTransferCurve(0)
	 */
	public JDFTransferCurve getTransferCurve(int iSkip)
	{
		return (JDFTransferCurve) getElement(ElementName.TRANSFERCURVE, null, iSkip);
	}

	/**
	 * Get all TransferCurve from the current element
	 * 
	 * @return Collection<JDFTransferCurve>, null if none are available
	 */
	public Collection<JDFTransferCurve> getAllTransferCurve()
	{
		return getChildArrayByClass(JDFTransferCurve.class, false, 0);
	}

	/**
	 * (30) append element TransferCurve
	 *
	 * @return JDFTransferCurve the element
	 */
	public JDFTransferCurve appendTransferCurve()
	{
		return (JDFTransferCurve) appendElement(ElementName.TRANSFERCURVE, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refTransferCurve(JDFTransferCurve refTarget)
	{
		refElement(refTarget);
	}

}
