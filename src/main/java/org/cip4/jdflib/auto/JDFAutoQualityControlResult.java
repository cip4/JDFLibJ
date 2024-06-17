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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFInspection;
import org.cip4.jdflib.resource.JDFRegistrationQuality;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFBindingQualityParams;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFQualityMeasurement;
import org.cip4.jdflib.util.JDFDate;

/**
 *****************************************************************************
 * class JDFAutoQualityControlResult : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoQualityControlResult extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BOX, 0x3331111111l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.END, 0x3333111111l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.FAILED, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.MEASUREMENTS, 0x3331111111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.MEASUREMENTUSAGE, 0x3333333311l, AttributeInfo.EnumAttributeType.enumerations, EnumMeasurementUsage.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.POSITION, 0x3331111111l, AttributeInfo.EnumAttributeType.enumeration, EnumPosition.getEnum(0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.PASSED, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.SAMPLE, 0x3331111111l, AttributeInfo.EnumAttributeType.IntegerRange, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.SEVERITY, 0x3331111111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.SOURCEDEVICEID, 0x3331111111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.START, 0x3333111111l, AttributeInfo.EnumAttributeType.dateTime, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[5];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BINDINGQUALITYPARAMS, 0x6666666611l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.FILESPEC, 0x6666666611l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.INSPECTION, 0x6666666611l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.QUALITYMEASUREMENT, 0x3333333311l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.REGISTRATIONQUALITY, 0x6666666611l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoQualityControlResult
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoQualityControlResult(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoQualityControlResult
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoQualityControlResult(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoQualityControlResult
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoQualityControlResult(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for MeasurementUsage
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumMeasurementUsage extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumMeasurementUsage(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumMeasurementUsage getEnum(String enumName)
		{
			return (EnumMeasurementUsage) getEnum(EnumMeasurementUsage.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumMeasurementUsage getEnum(int enumValue)
		{
			return (EnumMeasurementUsage) getEnum(EnumMeasurementUsage.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumMeasurementUsage.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumMeasurementUsage.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumMeasurementUsage.class);
		}

		/**  */
		public static final EnumMeasurementUsage Master = new EnumMeasurementUsage("Master");
		/**  */
		public static final EnumMeasurementUsage Standard = new EnumMeasurementUsage("Standard");
	}

	/**
	 * Enumeration strings for Position
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPosition extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPosition(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPosition getEnum(String enumName)
		{
			return (EnumPosition) getEnum(EnumPosition.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPosition getEnum(int enumValue)
		{
			return (EnumPosition) getEnum(EnumPosition.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPosition.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPosition.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPosition.class);
		}

		/**  */
		public static final EnumPosition Top = new EnumPosition("Top");
		/**  */
		public static final EnumPosition Bottom = new EnumPosition("Bottom");
		/**  */
		public static final EnumPosition Left = new EnumPosition("Left");
		/**  */
		public static final EnumPosition Right = new EnumPosition("Right");
		/**  */
		public static final EnumPosition Front = new EnumPosition("Front");
		/**  */
		public static final EnumPosition Back = new EnumPosition("Back");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Box ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Box
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBox(JDFRectangle value)
	{
		setAttribute(AttributeName.BOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute Box
	 *
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getBox()
	{
		final String strAttrName = getAttribute(AttributeName.BOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute End ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute End
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setEnd(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.END, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute End
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getEnd()
	{
		final String str = getAttribute(AttributeName.END, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Failed ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Failed
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFailed(int value)
	{
		setAttribute(AttributeName.FAILED, value, null);
	}

	/**
	 * (15) get int attribute Failed
	 *
	 * @return int the value of the attribute
	 */
	public int getFailed()
	{
		return getIntAttribute(AttributeName.FAILED, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Measurements
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Measurements
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMeasurements(int value)
	{
		setAttribute(AttributeName.MEASUREMENTS, value, null);
	}

	/**
	 * (15) get int attribute Measurements
	 *
	 * @return int the value of the attribute
	 */
	public int getMeasurements()
	{
		return getIntAttribute(AttributeName.MEASUREMENTS, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MeasurementUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute MeasurementUsage
	 *
	 * @param v vector of the enumeration values
	 */
	public void setMeasurementUsage(Vector<? extends ValuedEnum> v)
	{
		setEnumerationsAttribute(AttributeName.MEASUREMENTUSAGE, v, null);
	}

	/**
	 * (9.2) get MeasurementUsage attribute MeasurementUsage
	 *
	 * @return Vector of the enumerations
	 */
	public Vector<? extends ValuedEnum> getMeasurementUsage()
	{
		return getEnumerationsAttribute(AttributeName.MEASUREMENTUSAGE, null, EnumMeasurementUsage.getEnum(0), false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Position ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Position
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPosition(EnumPosition enumVar)
	{
		setAttribute(AttributeName.POSITION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Position
	 *
	 * @return the value of the attribute
	 */
	public EnumPosition getPosition()
	{
		return EnumPosition.getEnum(getAttribute(AttributeName.POSITION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Passed ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Passed
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPassed(int value)
	{
		setAttribute(AttributeName.PASSED, value, null);
	}

	/**
	 * (15) get int attribute Passed
	 *
	 * @return int the value of the attribute
	 */
	public int getPassed()
	{
		return getIntAttribute(AttributeName.PASSED, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Sample ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Sample
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSample(JDFIntegerRange value)
	{
		setAttribute(AttributeName.SAMPLE, value, null);
	}

	/**
	 * (20) get JDFIntegerRange attribute Sample
	 *
	 * @return JDFIntegerRange the value of the attribute, null if a the attribute value is not a valid to create a JDFIntegerRange
	 */
	public JDFIntegerRange getSample()
	{
		final String strAttrName = getAttribute(AttributeName.SAMPLE, null, null);
		final JDFIntegerRange nPlaceHolder = JDFIntegerRange.createIntegerRange(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Severity ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Severity
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSeverity(int value)
	{
		setAttribute(AttributeName.SEVERITY, value, null);
	}

	/**
	 * (15) get int attribute Severity
	 *
	 * @return int the value of the attribute
	 */
	public int getSeverity()
	{
		return getIntAttribute(AttributeName.SEVERITY, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SourceDeviceID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SourceDeviceID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSourceDeviceID(String value)
	{
		setAttribute(AttributeName.SOURCEDEVICEID, value, null);
	}

	/**
	 * (23) get String attribute SourceDeviceID
	 *
	 * @return the value of the attribute
	 */
	public String getSourceDeviceID()
	{
		return getAttribute(AttributeName.SOURCEDEVICEID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Start ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute Start
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setStart(JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.START, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute Start
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getStart()
	{
		final String str = getAttribute(AttributeName.START, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element BindingQualityParams
	 *
	 * @return JDFBindingQualityParams the element
	 */
	public JDFBindingQualityParams getBindingQualityParams()
	{
		return (JDFBindingQualityParams) getElement(ElementName.BINDINGQUALITYPARAMS, null, 0);
	}

	/**
	 * (25) getCreateBindingQualityParams
	 * 
	 * @return JDFBindingQualityParams the element
	 */
	public JDFBindingQualityParams getCreateBindingQualityParams()
	{
		return (JDFBindingQualityParams) getCreateElement_JDFElement(ElementName.BINDINGQUALITYPARAMS, null, 0);
	}

	/**
	 * (29) append element BindingQualityParams
	 *
	 * @return JDFBindingQualityParams the element @ if the element already exists
	 */
	public JDFBindingQualityParams appendBindingQualityParams()
	{
		return (JDFBindingQualityParams) appendElementN(ElementName.BINDINGQUALITYPARAMS, 1, null);
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
	 * (29) append element FileSpec
	 *
	 * @return JDFFileSpec the element @ if the element already exists
	 */
	public JDFFileSpec appendFileSpec()
	{
		return (JDFFileSpec) appendElementN(ElementName.FILESPEC, 1, null);
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
	 * (24) const get element Inspection
	 *
	 * @return JDFInspection the element
	 */
	public JDFInspection getInspection()
	{
		return (JDFInspection) getElement(ElementName.INSPECTION, null, 0);
	}

	/**
	 * (25) getCreateInspection
	 * 
	 * @return JDFInspection the element
	 */
	public JDFInspection getCreateInspection()
	{
		return (JDFInspection) getCreateElement_JDFElement(ElementName.INSPECTION, null, 0);
	}

	/**
	 * (29) append element Inspection
	 *
	 * @return JDFInspection the element @ if the element already exists
	 */
	public JDFInspection appendInspection()
	{
		return (JDFInspection) appendElementN(ElementName.INSPECTION, 1, null);
	}

	/**
	 * (26) getCreateQualityMeasurement
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFQualityMeasurement the element
	 */
	public JDFQualityMeasurement getCreateQualityMeasurement(int iSkip)
	{
		return (JDFQualityMeasurement) getCreateElement_JDFElement(ElementName.QUALITYMEASUREMENT, null, iSkip);
	}

	/**
	 * (27) const get element QualityMeasurement
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQualityMeasurement the element default is getQualityMeasurement(0)
	 */
	public JDFQualityMeasurement getQualityMeasurement(int iSkip)
	{
		return (JDFQualityMeasurement) getElement(ElementName.QUALITYMEASUREMENT, null, iSkip);
	}

	/**
	 * Get all QualityMeasurement from the current element
	 * 
	 * @return Collection<JDFQualityMeasurement>, null if none are available
	 */
	public Collection<JDFQualityMeasurement> getAllQualityMeasurement()
	{
		return getChildArrayByClass(JDFQualityMeasurement.class, false, 0);
	}

	/**
	 * (30) append element QualityMeasurement
	 *
	 * @return JDFQualityMeasurement the element
	 */
	public JDFQualityMeasurement appendQualityMeasurement()
	{
		return (JDFQualityMeasurement) appendElement(ElementName.QUALITYMEASUREMENT, null);
	}

	/**
	 * (24) const get element RegistrationQuality
	 *
	 * @return JDFRegistrationQuality the element
	 */
	public JDFRegistrationQuality getRegistrationQuality()
	{
		return (JDFRegistrationQuality) getElement(ElementName.REGISTRATIONQUALITY, null, 0);
	}

	/**
	 * (25) getCreateRegistrationQuality
	 * 
	 * @return JDFRegistrationQuality the element
	 */
	public JDFRegistrationQuality getCreateRegistrationQuality()
	{
		return (JDFRegistrationQuality) getCreateElement_JDFElement(ElementName.REGISTRATIONQUALITY, null, 0);
	}

	/**
	 * (29) append element RegistrationQuality
	 *
	 * @return JDFRegistrationQuality the element @ if the element already exists
	 */
	public JDFRegistrationQuality appendRegistrationQuality()
	{
		return (JDFRegistrationQuality) appendElementN(ElementName.REGISTRATIONQUALITY, 1, null);
	}

}
