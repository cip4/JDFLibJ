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
 * 04022005 VF initial version
 */
package org.cip4.jdflib.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.enums.EnumUtils;
import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFCMYKColor;
import org.cip4.jdflib.datatypes.JDFDateTimeRangeList;
import org.cip4.jdflib.datatypes.JDFDurationRangeList;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFLabColor;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.datatypes.JDFRGBColor;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFRectangleRangeList;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.datatypes.JDFShapeRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.JDFXYPairRange;
import org.cip4.jdflib.datatypes.JDFXYPairRangeList;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;

/**
 * The maintainer of version-specific attribute information: - attribute type (for current version only) - validity per version
 */
public class AttributeInfo
{

	/**
	 *
	 */
	public static final Map<String, AttributeInfo> fixedMap = new HashMap<>();

	HashMap<String, AtrInfo> attribInfoTable = new HashMap<>();
	private EnumVersion version = null;

	/**
	 * Constructor
	 *
	 * @param attrInfo_own : table with element-specific attribute info
	 */
	protected AttributeInfo(final AtrInfoTable[] attrInfo_own)
	{
		// fill table with the attributes specific to this element type (if any)
		updateReplace(attrInfo_own);
		// now all schema-based knowledge should be in the attribute info table
	}

	/**
	 * Constructor
	 *
	 * @param attrInfo_super corresponding attrib info of super; if null: start from scratch, otherwise initialize from other AttributeInfo
	 * @param attrInfo_own table with element-specific attribute info
	 * @deprecated
	 */
	@Deprecated
	public AttributeInfo(final AttributeInfo attrInfo_super, final AtrInfoTable[] attrInfo_own)
	{
		// use AttributeInfo of super as a starting point
		if (attrInfo_super != null)
		{
			attribInfoTable = new HashMap<>(attrInfo_super.attribInfoTable);
			version = attrInfo_super.version;
		}

		// fill table with the attributes specific to this element type (if any)
		updateReplace(attrInfo_own);

		// now all schema-based knowledge should be in the attribute info table
	}

	/**
	 * Updater
	 *
	 * @param attrInfo_update table with element-specific attribute info
	 * @return
	 */
	public AttributeInfo updateAdd(final AtrInfoTable attrInfo_update)
	{
		if (attrInfo_update != null)
		{
			attribInfoTable.put(attrInfo_update.getAttributeName(), attrInfo_update.getAtrInfo());
		}
		return this;
	}

	/**
	 * Updater
	 *
	 * @param attrInfo_update table with element-specific attribute info
	 * @return
	 */
	public AttributeInfo updateAdd(final AtrInfoTable[] attrInfo_update)
	{
		if (attrInfo_update != null)
		{
			for (final AtrInfoTable element : attrInfo_update)
			{
				attribInfoTable.put(element.getAttributeName(), element.getAtrInfo());
			}
		}
		return this;
	}

	/**
	 * Updater
	 *
	 * @param attrInfo_update table with element-specific attribute info
	 * @return
	 */
	public AttributeInfo updateRemove(final AtrInfoTable attrInfo_update)
	{
		if (attrInfo_update != null && attribInfoTable.containsKey(attrInfo_update.getAttributeName()))
		{
			attribInfoTable.remove(attrInfo_update.getAttributeName());
		}

		return this;
	}

	/**
	 * Updater
	 *
	 * @param attrInfo_update table with element-specific attribute info to remove from attribInfoTable
	 * @return
	 */
	public AttributeInfo updateRemove(final AtrInfoTable[] attrInfo_update)
	{
		if (attrInfo_update != null)
		{
			for (final AtrInfoTable element : attrInfo_update)
			{
				if (attribInfoTable.containsKey(element.getAttributeName()))
				{
					attribInfoTable.remove(element.getAttributeName());
				}
			}
		}
		return this;
	}

	/**
	 * @param attrInfo_update
	 * @return
	 */
	public AttributeInfo updateReplace(final AtrInfoTable attrInfo_update)
	{
		if (attrInfo_update != null)
		{
			attribInfoTable.put(attrInfo_update.getAttributeName(), attrInfo_update.getAtrInfo());
		}
		return this;
	}

	/**
	 * @param attrInfo_update
	 * @return
	 */
	public AttributeInfo updateReplace(final AtrInfoTable[] attrInfo_update)
	{
		if (attrInfo_update != null)
		{
			for (final AtrInfoTable atrInfoTable : attrInfo_update)
			{
				attribInfoTable.put(atrInfoTable.getAttributeName(), atrInfoTable.getAtrInfo());
			}
		}
		return this;
	}

	/**
	 * Returns a list of attributes matching the requested validity for the specified JDF version.
	 *
	 * @param attrValidity requested validity
	 * @return VString: list of strings containing the names of the matching attributes
	 */
	public VString conformingAttribs(final EnumAttributeValidity attrValidity)
	{
		final VString matchingAttribs = new VString();
		final long l2 = JDFVersions.getTheMask(version);
		final long v2 = JDFVersions.getTheOffset(version);

		final Iterator<String> iter = attribInfoTable.keySet().iterator();
		final boolean bOK = attrValidity == null;
		while (iter.hasNext())
		{
			final String theKey = iter.next();
			final AtrInfo ai = attribInfoTable.get(theKey);
			if (bOK)
			{
				matchingAttribs.add(theKey);
			}
			else
			{
				// grab values from tables
				final long l1 = ai.getAtrValidityStatus();
				final long l3 = l1 & l2;

				// calculate correct mask from attrValidity and version
				final long v1 = attrValidity.getValue();
				final long v3 = v1 << v2;

				// tables and version coincide
				if (l3 == v3)
				{
					matchingAttribs.add(theKey);
				}
			}
		}

		return matchingAttribs;
	}

	/**
	 * Returns a map of attributes with defaults for the specified JDF version.
	 *
	 * @return JDFAttributeMap: map of strings containing the names and defaults of the matching attributes, null if no defaults exist
	 */
	public JDFAttributeMap getDefaultAttributeMap()
	{
		final JDFAttributeMap matchingAttribs = new JDFAttributeMap();

		final Iterator<String> iter = attribInfoTable.keySet().iterator();
		while (iter.hasNext())
		{
			final String theKey = iter.next();
			final AtrInfo ai = attribInfoTable.get(theKey);
			final long l2 = JDFVersions.getTheMask(version);
			final long v2 = JDFVersions.getTheOffset(version);
			final EnumAttributeValidity versionVal = EnumAttributeValidity.getEnum((int) ((ai.getAtrValidityStatus() & l2) >> v2));
			if (versionVal.equals(EnumAttributeValidity.Optional) || versionVal.equals(EnumAttributeValidity.Required))
			{
				final String def = ai.getAtrDefault();
				if (def != null)
				{
					matchingAttribs.put(theKey, def);
				}
			}
		}
		return matchingAttribs.isEmpty() ? null : matchingAttribs;
	}

	/**
	 * Returns true if there is at least one attribute matching the requested validity for the specified JDF version.
	 *
	 * @param attrValidity requested validity
	 * @return boolean: true if at least one attribute matches the requested validity
	 */
	public boolean hasConformingAttrib(final EnumAttributeValidity attrValidity)
	{
		final Set<String> set = attribInfoTable.keySet();

		final long l2 = JDFVersions.getTheMask(version);
		final long v2 = JDFVersions.getTheOffset(version);
		for (final String s : set)
		{
			final AtrInfo ai = attribInfoTable.get(s);
			if ((ai.getAtrValidityStatus() & l2) == ((long) attrValidity.getValue() << v2))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns the list of required attributes for the specified JDF version.
	 *
	 * @return VString: list of strings containing the names of the required attributes
	 */
	public VString requiredAttribs()
	{
		return conformingAttribs(EnumAttributeValidity.Required);
	}

	/**
	 * Returns the list of optional attributes for the specified JDF version. Note: This includes attributes marked as optional as well as attributes marked as deprecated (since, for backward
	 * compatibility, these are also optional).
	 *
	 * @return VString: list of strings containing the names of the optional attributes
	 */
	public VString optionalAttribs()
	{
		final VString optionals = new VString(conformingAttribs(EnumAttributeValidity.Optional));
		optionals.appendUnique(conformingAttribs(EnumAttributeValidity.Deprecated));
		final Iterator<String> iter = attribInfoTable.keySet().iterator();
		// anything with a default is at least optional
		while (iter.hasNext())
		{
			final String theKey = iter.next();
			final String defaultVal = getAttributeDefault(theKey);
			if (defaultVal != null)
				optionals.appendUnique(theKey);
		}

		return optionals;
	}

	/**
	 * Returns the list of all attributes for the specified JDF version.
	 *
	 * @return VString: list of strings containing the names of the deprecated attributes
	 */
	public VString knownAttribs()
	{
		return conformingAttribs(null);
	}

	/**
	 * Returns the list of deprecated attributes for the specified JDF version.
	 *
	 * @return VString: list of strings containing the names of the deprecated attributes
	 */
	public VString deprecatedAttribs()
	{
		return conformingAttribs(EnumAttributeValidity.Deprecated);
	}

	/**
	 * Returns the list of prerelease attributes (those that are only valid in a later version) for the specified JDF version.
	 *
	 * @return VString: list of strings containing the names of the prerelease attributes
	 */
	public VString prereleaseAttribs()
	{
		return conformingAttribs(EnumAttributeValidity.None);
	}

	/**
	 * Returns the type of the given attribute for the latest JDF version. Attribute types of previous versions have to be provided by attribute-specific functions (if necessary).
	 *
	 * @param attributeName name of the attribute
	 * @return EnumAttributeType: the attribute's type
	 */
	public EnumAttributeType getAttributeType(final String attributeName)
	{
		final AtrInfo atrInfo = attribInfoTable.get(attributeName);
		if (atrInfo != null)
		{
			return atrInfo.getAtrType();
		}
		return null;
	}

	/**
	 * Returns the validity of the given attribute for the latest JDF version. Attribute types of previous versions have to be provided by attribute-specific functions (if necessary).
	 *
	 * @param attributeName name of the attribute
	 * @return EnumAttributeType: the attribute's type
	 */
	public EnumAttributeValidity getAttributeValidity(final String attributeName)
	{
		final AtrInfo atrInfo = attribInfoTable.get(attributeName);
		if (atrInfo != null)
		{
			long l = atrInfo.getAtrValidityStatus();
			final long l2 = JDFVersions.getTheMask(version);
			final long v2 = JDFVersions.getTheOffset(version);
			l = (l & l2) >> v2;
			return EnumAttributeValidity.getEnum((int) l);

		}
		return null;
	}

	/**
	 * Returns the ValuedEnum that goes with attributeName
	 *
	 * @param attributeName : name of the attribute
	 * @return EnumAttributeType: the attribute's type
	 */
	public ValuedEnum getAttributeEnum(final String attributeName)
	{
		final AtrInfo atrInfo = attribInfoTable.get(attributeName);
		if (atrInfo != null)
		{
			return atrInfo.getEnumEnum();
		}
		return null;
	}

	/**
	 * Returns the Default that goes with attributeName
	 *
	 * @param attributeName : name of the attribute
	 * @return EnumAttributeType: the attribute's type
	 */
	public String getAttributeDefault(final String attributeName)
	{
		final AtrInfo atrInfo = attribInfoTable.get(attributeName);
		if (atrInfo != null)
		{
			return atrInfo.getAtrDefault();
		}
		return null;
	}

	/*
	 * ----------------------------------------------------------------------- Enumeration of valid attribute types -----------------------------------------------------------------------
	 */

	/**
	 * Enumeration of valid attribute types
	 */
	public static final class EnumAttributeType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;
		private static Set<EnumAttributeType> setRange = null;

		/**
		 * @param name
		 */
		private EnumAttributeType(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the name of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumAttributeType getEnum(final String enumName)
		{
			final EnumAttributeType eat = (EnumAttributeType) getEnum(EnumAttributeType.class, enumName);
			return (eat == null) ? EnumAttributeType.Any : eat;
		}

		/**
		 * @param enumValue the value of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumAttributeType getEnum(final int enumValue)
		{
			return (EnumAttributeType) getEnum(EnumAttributeType.class, enumValue);
		}

		/**
		 * @return a map of all orientation enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumAttributeType.class);
		}

		/**
		 * @return a list of all orientation enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumAttributeType.class);
		}

		/**
		 * @return an iterator over the enum objects
		 */
		public static Iterator iterator()
		{
			return iterator(EnumAttributeType.class);
		}

		/**
		 * @param test
		 * @return true if test is a range data type
		 */
		public static boolean isRange(final EnumAttributeType test)
		{
			if (setRange == null)
			{
				final HashSet<EnumAttributeType> setRangeLocal = new HashSet<>();
				setRangeLocal.add(EnumAttributeType.DateTimeRange);
				setRangeLocal.add(EnumAttributeType.DateTimeRangeList);
				setRangeLocal.add(EnumAttributeType.DurationRange);
				setRangeLocal.add(EnumAttributeType.DurationRangeList);
				setRangeLocal.add(EnumAttributeType.IntegerRange);
				setRangeLocal.add(EnumAttributeType.IntegerRangeList);
				setRangeLocal.add(EnumAttributeType.NameRange);
				setRangeLocal.add(EnumAttributeType.NameRangeList);
				setRangeLocal.add(EnumAttributeType.NumberRange);
				setRangeLocal.add(EnumAttributeType.NumberRangeList);
				setRangeLocal.add(EnumAttributeType.RectangleRange);
				setRangeLocal.add(EnumAttributeType.RectangleRangeList);
				setRangeLocal.add(EnumAttributeType.ShapeRange);
				setRangeLocal.add(EnumAttributeType.ShapeRangeList);
				setRangeLocal.add(EnumAttributeType.XYPairRange);
				setRangeLocal.add(EnumAttributeType.XYPairRangeList);
				setRange = setRangeLocal;
			}

			return test == null ? false : setRange.contains(test);
		}

		/** */
		public static final EnumAttributeType Any = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_ANY);
		/** */
		public static final EnumAttributeType boolean_ = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_BOOLEAN);
		/** */
		public static final EnumAttributeType CMYKColor = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_CMYKCOLOR);
		/** */
		public static final EnumAttributeType dateTime = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_DATETIME);
		/** */
		public static final EnumAttributeType DateTimeRange = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_DATETIMERANGE);
		/** */
		public static final EnumAttributeType DateTimeRangeList = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_DATETIMERANGELIST);
		/** */
		public static final EnumAttributeType double_ = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_DOUBLE);
		/** */
		public static final EnumAttributeType duration = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_DURATION);
		/** */
		public static final EnumAttributeType DurationRange = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_DURATIONRANGE);
		/** */
		public static final EnumAttributeType DurationRangeList = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_DURATIONRANGELIST);
		/** */
		public static final EnumAttributeType enumeration = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_ENUMERATION); // also
		/** */
		public static final EnumAttributeType enumerations = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_ENUMERATIONS); // also
		/** */
		public static final EnumAttributeType hexBinary = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_HEXBINARY);
		/** */
		public static final EnumAttributeType ID = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_ID);
		/** */
		public static final EnumAttributeType IDREF = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_IDREF);
		/** */
		public static final EnumAttributeType IDREFS = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_IDREFS);
		/** */
		public static final EnumAttributeType integer = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_INTEGER);
		/** */
		public static final EnumAttributeType IntegerList = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_INTEGERLIST);
		/** */
		public static final EnumAttributeType IntegerRange = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_INTEGERRANGE);
		/** */
		public static final EnumAttributeType IntegerRangeList = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_INTEGERRANGELIST);
		/** */
		public static final EnumAttributeType JDFJMFVersion = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_JDFJMFVERSION);
		/** */
		public static final EnumAttributeType LabColor = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_LABCOLOR);
		/** */
		public static final EnumAttributeType language = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_LANGUAGE);
		/** */
		public static final EnumAttributeType languages = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_LANGUAGES);
		/** */
		public static final EnumAttributeType matrix = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_MATRIX);
		/** */
		public static final EnumAttributeType NameRange = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_NAMERANGE);
		/** */
		public static final EnumAttributeType NameRangeList = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_NAMERANGELIST);
		/** */
		public static final EnumAttributeType NMTOKEN = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_NMTOKEN);
		/** */
		public static final EnumAttributeType NMTOKENS = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_NMTOKENS);
		/** */
		public static final EnumAttributeType NumberList = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_NUMBERLIST); // equivalent
		/** */
		public static final EnumAttributeType NumberRange = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_NUMBERRANGE);
		/** */
		public static final EnumAttributeType NumberRangeList = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_NUMBERRANGELIST);
		/** */
		public static final EnumAttributeType PDFPath = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_PDFPATH);
		/** */
		public static final EnumAttributeType rectangle = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_RECTANGLE);
		/** */
		public static final EnumAttributeType RectangleRange = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_RECTANGLERANGE);
		/** */
		public static final EnumAttributeType RectangleRangeList = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_RECTANGLERANGELIST);
		/** */
		public static final EnumAttributeType RegExp = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_REGEXP);
		/** */
		public static final EnumAttributeType RGBColor = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_RGBCOLOR);
		/** */
		public static final EnumAttributeType shape = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_SHAPE);
		/** */
		public static final EnumAttributeType ShapeRange = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_SHAPERANGE);
		/** */
		public static final EnumAttributeType ShapeRangeList = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_SHAPERANGELIST);
		/** */
		public static final EnumAttributeType shortString = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_SHORTSTRING);
		/** */
		public static final EnumAttributeType string = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_STRING);
		/** */
		public static final EnumAttributeType TransferFunction = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_TRANSFERFUNCTION);
		/** */
		public static final EnumAttributeType unbounded = new EnumAttributeType(JDFConstants.UNBOUNDED); // needed
		/** */
		public static final EnumAttributeType URI = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_URI);
		/** */
		public static final EnumAttributeType URL = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_URL);
		/** */
		public static final EnumAttributeType XYPair = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_XYPAIR);
		/** */
		public static final EnumAttributeType XYPairRange = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_XYPAIRRANGE);
		/** */
		public static final EnumAttributeType XYPairRangeList = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_XYPAIRRANGELIST);
		/** */
		public static final EnumAttributeType XPath = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_XPATH);
		/** */
		public static final EnumAttributeType XYRelation = new EnumAttributeType(JDFConstants.ATTRIBUTETYPE_XYRELATION);
	}

	/*
	 * ----------------------------------------------------------------------- Enumeration of attribute validity values -----------------------------------------------------------------------
	 */

	/**
	 * Enumeration of attribute validity values
	 */
	public static final class EnumAttributeValidity extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		/**
		 * @param name
		 */
		private EnumAttributeValidity(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the name of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumAttributeValidity getEnum(final String enumName)
		{
			return (EnumAttributeValidity) getEnum(EnumAttributeValidity.class, enumName);
		}

		/**
		 * @param enumValue the value of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumAttributeValidity getEnum(final int enumValue)
		{
			return (EnumAttributeValidity) getEnum(EnumAttributeValidity.class, enumValue);
		}

		/**
		 * @return a map of all orientation enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumAttributeValidity.class);
		}

		/**
		 * @return a list of all orientation enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumAttributeValidity.class);
		}

		/**
		 * @return an iterator over the enum objects
		 */
		public static Iterator iterator()
		{
			return iterator(EnumAttributeValidity.class);
		}

		/** */
		public static final EnumAttributeValidity Unknown = new EnumAttributeValidity(JDFConstants.ATTRIBUTEVALIDITY_UNKNOWN);
		/** */
		public static final EnumAttributeValidity None = new EnumAttributeValidity(JDFConstants.ATTRIBUTEVALIDITY_NONE);
		/** */
		public static final EnumAttributeValidity Required = new EnumAttributeValidity(JDFConstants.ATTRIBUTEVALIDITY_REQUIRED);
		/** */
		public static final EnumAttributeValidity Optional = new EnumAttributeValidity(JDFConstants.ATTRIBUTEVALIDITY_OPTIONAL);
		/** */
		public static final EnumAttributeValidity Deprecated = new EnumAttributeValidity(JDFConstants.ATTRIBUTEVALIDITY_DEPRECATED);
		/** */
		public static final EnumAttributeValidity Any = new EnumAttributeValidity(JDFConstants.ATTRIBUTEVALIDITY_ANY);
	}

	/**
	 * @param v
	 */
	public void setVersion(final EnumVersion v)
	{
		version = v;
	}

	/**
	 * returns the data type of a given attribute
	 *
	 * @param attributeName the localname of the attribute to check
	 * @return the data type of attributeName
	 * @deprecated 2005-08-26
	 */
	@Deprecated
	public EnumAttributeType getAtrType(final String attributeName)
	{
		final AtrInfo ai = attribInfoTable.get(attributeName);
		if (ai == null)
		{
			return null;
		}
		return ai.getAtrType();
	}

	/**
	 * @param key
	 * @param attribute
	 * @param level
	 * @return
	 */
	public boolean validAttribute(final String key, final String attribute, final EnumValidationLevel level)
	{
		final EnumAttributeType typ = getAttributeType(key);
		if (typ == null) // unknown attributes are by definition valid, the check is done in the unknown method
			return true;

		// get the correct enumeration lists
		ValuedEnum enu = null;
		if ((typ == EnumAttributeType.enumeration) || (typ == EnumAttributeType.enumerations))
		{
			enu = getAttributeEnum(key);
		}
		else if (typ == EnumAttributeType.JDFJMFVersion)
		{
			enu = EnumVersion.getEnum(0);
		}

		final EnumAttributeValidity val = getAttributeValidity(key);
		if (val == EnumAttributeValidity.Unknown)
		{
			return (attribute == null);
		}
		else if (val == EnumAttributeValidity.Deprecated)
		{
			return (attribute == null) || EnumValidationLevel.isNoWarn(level);
		}
		else if (val == EnumAttributeValidity.None) // prerelease may be set
		// by schema validating
		// parser
		{
			return (attribute == null) || attribute.equals(getAttributeDefault(key)) || EnumValidationLevel.isNoWarn(level);
		}
		else if ((val == EnumAttributeValidity.Optional) || ((level != EnumValidationLevel.Complete) && (level != EnumValidationLevel.RecursiveComplete)))
		{
			return (attribute == null) || validStringForType(attribute, typ, enu);
		}
		else if (val == EnumAttributeValidity.Required)
		{
			return (attribute != null) && validStringForType(attribute, typ, enu);
		}

		return true;
	}

	/**
	 * @param val
	 * @param iType
	 * @param enu
	 * @return
	 *
	 */
	public static boolean validStringForType(final String val, final EnumAttributeType iType, final ValuedEnum enu)
	{
		if (val == null || val.isEmpty())
			return false;

		if (iType == null)
			return true;

		try
		{
			if (iType == AttributeInfo.EnumAttributeType.Any || iType == AttributeInfo.EnumAttributeType.string)
			{
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.shortString)
			{
				return val.length() < 64;
			}
			else if (iType == AttributeInfo.EnumAttributeType.ID || iType == AttributeInfo.EnumAttributeType.IDREF)
			{
				return StringUtil.isID(val);
			}
			else if (iType == AttributeInfo.EnumAttributeType.NMTOKEN)
			{
				return StringUtil.isNMTOKEN(val);
			}
			else if (iType == AttributeInfo.EnumAttributeType.NMTOKENS)
			{
				return StringUtil.isNMTOKENS(val, false);
			}
			else if (iType == AttributeInfo.EnumAttributeType.IDREFS)
			{
				return StringUtil.isNMTOKENS(val, true);
			}
			else if (iType == AttributeInfo.EnumAttributeType.boolean_)
			{
				return StringUtil.isBoolean(val);
			}
			else if (iType == AttributeInfo.EnumAttributeType.double_)
			{
				return StringUtil.isNumber(val);
			}
			else if (iType == AttributeInfo.EnumAttributeType.integer)
			{
				return StringUtil.isInteger(val);
			}
			// integer or unbounded
			else if (iType == AttributeInfo.EnumAttributeType.unbounded)
			{
				return JDFConstants.UNBOUNDED.equals(val) || StringUtil.isInteger(val);
			}
			else if ((iType == AttributeInfo.EnumAttributeType.URI) || (iType == AttributeInfo.EnumAttributeType.URL))
			{
				return UrlUtil.isIRL(val);
			}
			else if (iType == AttributeInfo.EnumAttributeType.RegExp)
			{
				return true;
			}
			else if ((iType == AttributeInfo.EnumAttributeType.enumeration) || (iType == AttributeInfo.EnumAttributeType.JDFJMFVersion))
			{
				return isEnum(val, enu);
			}
			else if (iType == AttributeInfo.EnumAttributeType.enumerations)
			{
				return isEnums(val, enu);
			}
			else if (iType == AttributeInfo.EnumAttributeType.IntegerRange)
			{
				new JDFIntegerRange(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.IntegerList)
			{
				new JDFIntegerList(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.IntegerRangeList)
			{
				new JDFIntegerRangeList(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.NumberRange)
			{
				new JDFNumberRange(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.NumberRangeList)
			{
				new JDFNumberRangeList(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.NumberList)
			{
				new JDFNumberList(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.matrix)
			{
				new JDFMatrix(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.rectangle)
			{
				new JDFRectangle(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.shape)
			{
				new JDFShape(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.XYPair)
			{
				new JDFXYPair(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.XYPairRange)
			{
				new JDFXYPairRange(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.XYPairRangeList)
			{
				new JDFXYPairRangeList(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.dateTime)
			{
				new JDFDate(val);
				return val.indexOf('T') == 10; // pure dates are not valid
			}
			else if (iType == AttributeInfo.EnumAttributeType.duration)
			{
				new JDFDuration(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.DurationRangeList)
			{
				new JDFDurationRangeList(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.DateTimeRangeList)
			{
				new JDFDateTimeRangeList(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.RectangleRangeList)
			{
				new JDFRectangleRangeList(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.ShapeRangeList)
			{
				new JDFShapeRangeList(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.CMYKColor)
			{
				new JDFCMYKColor(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.LabColor)
			{
				new JDFLabColor(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.RGBColor)
			{
				new JDFRGBColor(val);
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.language)
			{
				return validLanguageString(val);
			}
			else if (iType == AttributeInfo.EnumAttributeType.languages)
			{
				final VString v = StringUtil.tokenize(val, JDFConstants.BLANK, false);
				for (int i = 0; i < v.size(); i++)
				{
					if (!validLanguageString(v.elementAt(i)))
						return false;
				}
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.PDFPath)
			{
				// TODO better regexp
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.XPath)
			{
				// TODO better regexp
				return true;
			}
			else if (iType == AttributeInfo.EnumAttributeType.hexBinary)
			{
				return StringUtil.matches(val, JDFConstants.REGEXP_HEXBINARY);
			}
			else if (iType == AttributeInfo.EnumAttributeType.TransferFunction)
			{
				final JDFNumberList nl = new JDFNumberList(val);
				return nl.size() % 2 == 0;
			}
			else
			{
				return false;
			}
		}
		catch (final DataFormatException excep)
		{
			return false;
		}
	}

	static boolean isEnums(final String val, final ValuedEnum enu)
	{
		if (enu != null)
		{
			final VString vs = StringUtil.tokenize(val, JDFConstants.BLANK, false);
			for (int i = 0; i < vs.size(); i++)
			{
				final ValuedEnum ve = (ValuedEnum) EnumUtils.getEnum(enu.getClass(), vs.get(i));
				// there was an invalid token
				if (ve == null)
					return false;
			}
			// all were ok
			return true;
		}
		// limp along if something went wrong
		return StringUtil.isNMTOKENS(val, false);
	}

	static boolean isEnum(final String val, final ValuedEnum enu)
	{
		if (enu != null)
		{
			final ValuedEnum ve = (ValuedEnum) EnumUtils.getEnum(enu.getClass(), val);
			return ve != null;
		}
		// limp along if something went wrong
		return StringUtil.isNMTOKEN(val);
	}

	private static boolean validLanguageString(final String val)
	{
		// TODO better regexp
		final int l = val.length();
		final int posDash = val.indexOf('-');
		return l >= 2 && l <= 3 || l > 4 && (posDash >= 2 && posDash < 4);
		// 2=en , de , ...
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		String s = "AttributeInfoTable verion=" + version;
		s += attribInfoTable.toString();
		return s;
	}

	/**
	 * get the first jdf version where an attrinute of this type is valid
	 *
	 * @param attributeName the name of the queried attribute
	 * @return
	 */
	public EnumVersion getFirstVersion(final String attributeName)
	{
		if (attribInfoTable.containsKey(attributeName))
		{
			return (attribInfoTable.get(attributeName)).getFirstVersion();
		}
		return null;
	}

	/**
	 * get the last jdf version where an attrinute of this type is valid
	 *
	 * @param attributeName the name of the queried attribute
	 * @return
	 */
	public EnumVersion getLastVersion(final String attributeName)
	{
		if (attribInfoTable.containsKey(attributeName))
		{
			return (attribInfoTable.get(attributeName)).getLastVersion();
		}
		return null;
	}

}
