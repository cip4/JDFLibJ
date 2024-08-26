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

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFColorMeasurement;
import org.cip4.jdflib.resource.JDFRegistrationQuality;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFBindingQualityParams;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.util.JDFDuration;

/**
 *****************************************************************************
 * class JDFAutoQualityControlParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoQualityControlParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BOX, 0x3331111111l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.POSITION, 0x3331111111l, AttributeInfo.EnumAttributeType.enumeration, EnumPosition.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.SAMPLEINTERVAL, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.SEVERITY, 0x3331111111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.SOURCEDEVICEID, 0x3331111111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.TIMEINTERVAL, 0x3333333311l, AttributeInfo.EnumAttributeType.duration, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BINDINGQUALITYPARAMS, 0x6666666611l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.COLORMEASUREMENT, 0x3333333311l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.FILESPEC, 0x3333333311l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.REGISTRATIONQUALITY, 0x6666666611l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoQualityControlParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoQualityControlParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoQualityControlParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoQualityControlParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoQualityControlParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoQualityControlParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * --------------------------------------------------------------------- Methods for Attribute SampleInterval
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SampleInterval
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSampleInterval(int value)
	{
		setAttribute(AttributeName.SAMPLEINTERVAL, value, null);
	}

	/**
	 * (15) get int attribute SampleInterval
	 *
	 * @return int the value of the attribute
	 */
	public int getSampleInterval()
	{
		return getIntAttribute(AttributeName.SAMPLEINTERVAL, null, 0);
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
	 * --------------------------------------------------------------------- Methods for Attribute TimeInterval
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TimeInterval
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTimeInterval(JDFDuration value)
	{
		setAttribute(AttributeName.TIMEINTERVAL, value, null);
	}

	/**
	 * (20) get JDFDuration attribute TimeInterval
	 *
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getTimeInterval()
	{
		final String strAttrName = getAttribute(AttributeName.TIMEINTERVAL, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
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
	 * (24) const get element ColorMeasurement
	 *
	 * @return JDFColorMeasurement the element
	 */
	public JDFColorMeasurement getColorMeasurement()
	{
		return (JDFColorMeasurement) getElement(ElementName.COLORMEASUREMENT, null, 0);
	}

	/**
	 * (25) getCreateColorMeasurement
	 * 
	 * @return JDFColorMeasurement the element
	 */
	public JDFColorMeasurement getCreateColorMeasurement()
	{
		return (JDFColorMeasurement) getCreateElement_JDFElement(ElementName.COLORMEASUREMENT, null, 0);
	}

	/**
	 * (26) getCreateColorMeasurement
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFColorMeasurement the element
	 */
	public JDFColorMeasurement getCreateColorMeasurement(int iSkip)
	{
		return (JDFColorMeasurement) getCreateElement_JDFElement(ElementName.COLORMEASUREMENT, null, iSkip);
	}

	/**
	 * (27) const get element ColorMeasurement
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFColorMeasurement the element default is getColorMeasurement(0)
	 */
	public JDFColorMeasurement getColorMeasurement(int iSkip)
	{
		return (JDFColorMeasurement) getElement(ElementName.COLORMEASUREMENT, null, iSkip);
	}

	/**
	 * Get all ColorMeasurement from the current element
	 * 
	 * @return Collection<JDFColorMeasurement>, null if none are available
	 */
	public Collection<JDFColorMeasurement> getAllColorMeasurement()
	{
		return getChildArrayByClass(JDFColorMeasurement.class, false, 0);
	}

	/**
	 * (30) append element ColorMeasurement
	 *
	 * @return JDFColorMeasurement the element
	 */
	public JDFColorMeasurement appendColorMeasurement()
	{
		return (JDFColorMeasurement) appendElement(ElementName.COLORMEASUREMENT, null);
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
