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
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFBarcodeDetails;
import org.cip4.jdflib.resource.process.JDFExtraValues;
import org.cip4.jdflib.resource.process.JDFMetadataMap;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoIdentificationField : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoIdentificationField extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[12];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BOUNDINGBOX, 0x3333333333l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ENCODING, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumEncoding.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ENCODINGDETAILS, 0x3333333333l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.FORMAT, 0x3333333333l, AttributeInfo.EnumAttributeType.Any, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ORIENTATION, 0x3333333333l, AttributeInfo.EnumAttributeType.matrix, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.PAGE, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.POSITION, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumPosition.getEnum(0), null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.PURPOSE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumPurpose.getEnum(0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.PURPOSEDETAILS, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.VALUE, 0x3333333331l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.VALUEFORMAT, 0x3333333111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.VALUETEMPLATE, 0x3333333111l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BARCODEDETAILS, 0x6666666111l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.EXTRAVALUES, 0x6666666111l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.METADATAMAP, 0x3333311111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoIdentificationField
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoIdentificationField(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoIdentificationField
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoIdentificationField(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoIdentificationField
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoIdentificationField(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for Encoding
	 */

	public enum EEncoding
	{
		ASCII, Barcode, BarCode1D, BarCode2D, Braille, RFID;

		public static EEncoding getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EEncoding.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Encoding
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumEncoding extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumEncoding(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumEncoding getEnum(String enumName)
		{
			return (EnumEncoding) getEnum(EnumEncoding.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumEncoding getEnum(int enumValue)
		{
			return (EnumEncoding) getEnum(EnumEncoding.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumEncoding.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumEncoding.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumEncoding.class);
		}

		/**  */
		public static final EnumEncoding ASCII = new EnumEncoding("ASCII");
		/**  */
		public static final EnumEncoding Barcode = new EnumEncoding("Barcode");
		/**  */
		public static final EnumEncoding BarCode1D = new EnumEncoding("BarCode1D");
		/**  */
		public static final EnumEncoding BarCode2D = new EnumEncoding("BarCode2D");
		/**  */
		public static final EnumEncoding Braille = new EnumEncoding("Braille");
		/**  */
		public static final EnumEncoding RFID = new EnumEncoding("RFID");
	}

	/**
	 * Enumeration strings for Position
	 */

	public enum EPosition
	{
		Header, Trailer, Page, Top, Bottom, Left, Right, Front, Back, Any;

		public static EPosition getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EPosition.class, val, null);
		}
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
		public static final EnumPosition Header = new EnumPosition("Header");
		/**  */
		public static final EnumPosition Trailer = new EnumPosition("Trailer");
		/**  */
		public static final EnumPosition Page = new EnumPosition("Page");
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
		/**  */
		public static final EnumPosition Any = new EnumPosition("Any");
	}

	/**
	 * Enumeration strings for Purpose
	 */

	public enum EPurpose
	{
		Verification, Separation, Label;

		public static EPurpose getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EPurpose.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Purpose
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPurpose extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPurpose(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPurpose getEnum(String enumName)
		{
			return (EnumPurpose) getEnum(EnumPurpose.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPurpose getEnum(int enumValue)
		{
			return (EnumPurpose) getEnum(EnumPurpose.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPurpose.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPurpose.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPurpose.class);
		}

		/**  */
		public static final EnumPurpose Verification = new EnumPurpose("Verification");
		/**  */
		public static final EnumPurpose Separation = new EnumPurpose("Separation");
		/**  */
		public static final EnumPurpose Label = new EnumPurpose("Label");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BoundingBox ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BoundingBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBoundingBox(JDFRectangle value)
	{
		setAttribute(AttributeName.BOUNDINGBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute BoundingBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getBoundingBox()
	{
		final String strAttrName = getAttribute(AttributeName.BOUNDINGBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Encoding ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Encoding
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setEncoding(EEncoding enumVar)
	{
		setAttribute(AttributeName.ENCODING, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Encoding
	 *
	 * @return the value of the attribute
	 */
	public EEncoding getEEncoding()
	{
		return EEncoding.getEnum(getAttribute(AttributeName.ENCODING, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Encoding ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Encoding
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setEncoding(EEncoding) based on java.lang.enum instead
	 */
	@Deprecated
	public void setEncoding(EnumEncoding enumVar)
	{
		setAttribute(AttributeName.ENCODING, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Encoding
	 *
	 * @return the value of the attribute
	 * @deprecated use EEncoding getEEncoding() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumEncoding getEncoding()
	{
		return EnumEncoding.getEnum(getAttribute(AttributeName.ENCODING, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute EncodingDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EncodingDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEncodingDetails(String value)
	{
		setAttribute(AttributeName.ENCODINGDETAILS, value, null);
	}

	/**
	 * (23) get String attribute EncodingDetails
	 *
	 * @return the value of the attribute
	 */
	public String getEncodingDetails()
	{
		return getAttribute(AttributeName.ENCODINGDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Format ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Format
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFormat(String value)
	{
		setAttribute(AttributeName.FORMAT, value, null);
	}

	/**
	 * (23) get String attribute Format
	 *
	 * @return the value of the attribute
	 */
	public String getFormat()
	{
		return getAttribute(AttributeName.FORMAT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Orientation ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Orientation
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOrientation(JDFMatrix value)
	{
		setAttribute(AttributeName.ORIENTATION, value, null);
	}

	/**
	 * (20) get JDFMatrix attribute Orientation
	 *
	 * @return JDFMatrix the value of the attribute, null if a the attribute value is not a valid to create a JDFMatrix
	 */
	public JDFMatrix getOrientation()
	{
		final String strAttrName = getAttribute(AttributeName.ORIENTATION, null, null);
		final JDFMatrix nPlaceHolder = JDFMatrix.createMatrix(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Page ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Page
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPage(int value)
	{
		setAttribute(AttributeName.PAGE, value, null);
	}

	/**
	 * (15) get int attribute Page
	 *
	 * @return int the value of the attribute
	 */
	public int getPage()
	{
		return getIntAttribute(AttributeName.PAGE, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Position ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Position
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPosition(EPosition enumVar)
	{
		setAttribute(AttributeName.POSITION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Position
	 *
	 * @return the value of the attribute
	 */
	public EPosition getEPosition()
	{
		return EPosition.getEnum(getAttribute(AttributeName.POSITION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Position ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Position
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setPosition(EPosition) based on java.lang.enum instead
	 */
	@Deprecated
	public void setPosition(EnumPosition enumVar)
	{
		setAttribute(AttributeName.POSITION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Position
	 *
	 * @return the value of the attribute
	 * @deprecated use EPosition getEPosition() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumPosition getPosition()
	{
		return EnumPosition.getEnum(getAttribute(AttributeName.POSITION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Purpose ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Purpose
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPurpose(EPurpose enumVar)
	{
		setAttribute(AttributeName.PURPOSE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Purpose
	 *
	 * @return the value of the attribute
	 */
	public EPurpose getEPurpose()
	{
		return EPurpose.getEnum(getAttribute(AttributeName.PURPOSE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Purpose ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Purpose
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setPurpose(EPurpose) based on java.lang.enum instead
	 */
	@Deprecated
	public void setPurpose(EnumPurpose enumVar)
	{
		setAttribute(AttributeName.PURPOSE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Purpose
	 *
	 * @return the value of the attribute
	 * @deprecated use EPurpose getEPurpose() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumPurpose getPurpose()
	{
		return EnumPurpose.getEnum(getAttribute(AttributeName.PURPOSE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PurposeDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PurposeDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPurposeDetails(String value)
	{
		setAttribute(AttributeName.PURPOSEDETAILS, value, null);
	}

	/**
	 * (23) get String attribute PurposeDetails
	 *
	 * @return the value of the attribute
	 */
	public String getPurposeDetails()
	{
		return getAttribute(AttributeName.PURPOSEDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Value ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Value
	 *
	 * @param value the value to set the attribute to
	 */
	public void setValue(String value)
	{
		setAttribute(AttributeName.VALUE, value, null);
	}

	/**
	 * (23) get String attribute Value
	 *
	 * @return the value of the attribute
	 */
	public String getValue()
	{
		return getAttribute(AttributeName.VALUE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ValueFormat ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ValueFormat
	 *
	 * @param value the value to set the attribute to
	 */
	public void setValueFormat(String value)
	{
		setAttribute(AttributeName.VALUEFORMAT, value, null);
	}

	/**
	 * (23) get String attribute ValueFormat
	 *
	 * @return the value of the attribute
	 */
	public String getValueFormat()
	{
		return getAttribute(AttributeName.VALUEFORMAT, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ValueTemplate
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ValueTemplate
	 *
	 * @param value the value to set the attribute to
	 */
	public void setValueTemplate(String value)
	{
		setAttribute(AttributeName.VALUETEMPLATE, value, null);
	}

	/**
	 * (23) get String attribute ValueTemplate
	 *
	 * @return the value of the attribute
	 */
	public String getValueTemplate()
	{
		return getAttribute(AttributeName.VALUETEMPLATE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element BarcodeDetails
	 *
	 * @return JDFBarcodeDetails the element
	 */
	public JDFBarcodeDetails getBarcodeDetails()
	{
		return (JDFBarcodeDetails) getElement(ElementName.BARCODEDETAILS, null, 0);
	}

	/**
	 * (25) getCreateBarcodeDetails
	 * 
	 * @return JDFBarcodeDetails the element
	 */
	public JDFBarcodeDetails getCreateBarcodeDetails()
	{
		return (JDFBarcodeDetails) getCreateElement_JDFElement(ElementName.BARCODEDETAILS, null, 0);
	}

	/**
	 * (29) append element BarcodeDetails
	 *
	 * @return JDFBarcodeDetails the element @ if the element already exists
	 */
	public JDFBarcodeDetails appendBarcodeDetails()
	{
		return (JDFBarcodeDetails) appendElementN(ElementName.BARCODEDETAILS, 1, null);
	}

	/**
	 * (24) const get element ExtraValues
	 *
	 * @return JDFExtraValues the element
	 */
	public JDFExtraValues getExtraValues()
	{
		return (JDFExtraValues) getElement(ElementName.EXTRAVALUES, null, 0);
	}

	/**
	 * (25) getCreateExtraValues
	 * 
	 * @return JDFExtraValues the element
	 */
	public JDFExtraValues getCreateExtraValues()
	{
		return (JDFExtraValues) getCreateElement_JDFElement(ElementName.EXTRAVALUES, null, 0);
	}

	/**
	 * (29) append element ExtraValues
	 *
	 * @return JDFExtraValues the element @ if the element already exists
	 */
	public JDFExtraValues appendExtraValues()
	{
		return (JDFExtraValues) appendElementN(ElementName.EXTRAVALUES, 1, null);
	}

	/**
	 * (24) const get element MetadataMap
	 *
	 * @return JDFMetadataMap the element
	 */
	public JDFMetadataMap getMetadataMap()
	{
		return (JDFMetadataMap) getElement(ElementName.METADATAMAP, null, 0);
	}

	/**
	 * (25) getCreateMetadataMap
	 * 
	 * @return JDFMetadataMap the element
	 */
	public JDFMetadataMap getCreateMetadataMap()
	{
		return (JDFMetadataMap) getCreateElement_JDFElement(ElementName.METADATAMAP, null, 0);
	}

	/**
	 * (26) getCreateMetadataMap
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFMetadataMap the element
	 */
	public JDFMetadataMap getCreateMetadataMap(int iSkip)
	{
		return (JDFMetadataMap) getCreateElement_JDFElement(ElementName.METADATAMAP, null, iSkip);
	}

	/**
	 * (27) const get element MetadataMap
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFMetadataMap the element default is getMetadataMap(0)
	 */
	public JDFMetadataMap getMetadataMap(int iSkip)
	{
		return (JDFMetadataMap) getElement(ElementName.METADATAMAP, null, iSkip);
	}

	/**
	 * Get all MetadataMap from the current element
	 * 
	 * @return Collection<JDFMetadataMap>, null if none are available
	 */
	public Collection<JDFMetadataMap> getAllMetadataMap()
	{
		return getChildArrayByClass(JDFMetadataMap.class, false, 0);
	}

	/**
	 * (30) append element MetadataMap
	 *
	 * @return JDFMetadataMap the element
	 */
	public JDFMetadataMap appendMetadataMap()
	{
		return (JDFMetadataMap) appendElement(ElementName.METADATAMAP, null);
	}

}
