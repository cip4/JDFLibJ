/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFFileSpec;

/**
 *****************************************************************************
 * class JDFAutoScanParams : public JDFResource
 *****************************************************************************
 *
 */

public abstract class JDFAutoScanParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BITDEPTH, 0x22222222, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.MAGNIFICATION, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, "1 1");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.OUTPUTCOLORSPACE, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumOutputColorSpace.getEnum(0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.COMPRESSIONFILTER, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumCompressionFilter.getEnum(0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.DCTQUALITY, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.INPUTBOX, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MOUNTID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.MOUNTING, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumMounting.getEnum(0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.OUTPUTRESOLUTION, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.OUTPUTSIZE, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.SPLITDOCUMENTS, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.FILESPEC, 0x66666666);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoScanParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoScanParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoScanParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoScanParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoScanParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoScanParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for OutputColorSpace
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumOutputColorSpace extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumOutputColorSpace(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumOutputColorSpace getEnum(String enumName)
		{
			return (EnumOutputColorSpace) getEnum(EnumOutputColorSpace.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumOutputColorSpace getEnum(int enumValue)
		{
			return (EnumOutputColorSpace) getEnum(EnumOutputColorSpace.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumOutputColorSpace.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumOutputColorSpace.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumOutputColorSpace.class);
		}

		/**  */
		public static final EnumOutputColorSpace LAB = new EnumOutputColorSpace("LAB");
		/**  */
		public static final EnumOutputColorSpace RGB = new EnumOutputColorSpace("RGB");
		/**  */
		public static final EnumOutputColorSpace CMYK = new EnumOutputColorSpace("CMYK");
		/**  */
		public static final EnumOutputColorSpace GrayScale = new EnumOutputColorSpace("GrayScale");
	}

	/**
	 * Enumeration strings for CompressionFilter
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumCompressionFilter extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumCompressionFilter(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumCompressionFilter getEnum(String enumName)
		{
			return (EnumCompressionFilter) getEnum(EnumCompressionFilter.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumCompressionFilter getEnum(int enumValue)
		{
			return (EnumCompressionFilter) getEnum(EnumCompressionFilter.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumCompressionFilter.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumCompressionFilter.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumCompressionFilter.class);
		}

		/**  */
		public static final EnumCompressionFilter CCITTFaxEncode = new EnumCompressionFilter("CCITTFaxEncode");
		/**  */
		public static final EnumCompressionFilter DCTEncode = new EnumCompressionFilter("DCTEncode");
		/**  */
		public static final EnumCompressionFilter FlateEncode = new EnumCompressionFilter("FlateEncode");
		/**  */
		public static final EnumCompressionFilter WaveletEncode = new EnumCompressionFilter("WaveletEncode");
		/**  */
		public static final EnumCompressionFilter JBIG2Encode = new EnumCompressionFilter("JBIG2Encode");
	}

	/**
	 * Enumeration strings for Mounting
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumMounting extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumMounting(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumMounting getEnum(String enumName)
		{
			return (EnumMounting) getEnum(EnumMounting.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumMounting getEnum(int enumValue)
		{
			return (EnumMounting) getEnum(EnumMounting.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumMounting.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumMounting.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumMounting.class);
		}

		/**  */
		public static final EnumMounting Unfixed = new EnumMounting("Unfixed");
		/**  */
		public static final EnumMounting Fixed = new EnumMounting("Fixed");
		/**  */
		public static final EnumMounting Wet = new EnumMounting("Wet");
		/**  */
		public static final EnumMounting Registered = new EnumMounting("Registered");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute BitDepth
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute BitDepth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBitDepth(int value)
	{
		setAttribute(AttributeName.BITDEPTH, value, null);
	}

	/**
	 * (15) get int attribute BitDepth
	 *
	 * @return int the value of the attribute
	 */
	public int getBitDepth()
	{
		return getIntAttribute(AttributeName.BITDEPTH, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Magnification
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Magnification
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMagnification(JDFXYPair value)
	{
		setAttribute(AttributeName.MAGNIFICATION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Magnification
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getMagnification()
	{
		final String strAttrName = getAttribute(AttributeName.MAGNIFICATION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute OutputColorSpace
	--------------------------------------------------------------------- */
	/**
	 * (5) set attribute OutputColorSpace
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOutputColorSpace(EnumOutputColorSpace enumVar)
	{
		setAttribute(AttributeName.OUTPUTCOLORSPACE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute OutputColorSpace
	 *
	 * @return the value of the attribute
	 */
	public EnumOutputColorSpace getOutputColorSpace()
	{
		return EnumOutputColorSpace.getEnum(getAttribute(AttributeName.OUTPUTCOLORSPACE, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute CompressionFilter
	--------------------------------------------------------------------- */
	/**
	 * (5) set attribute CompressionFilter
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setCompressionFilter(EnumCompressionFilter enumVar)
	{
		setAttribute(AttributeName.COMPRESSIONFILTER, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute CompressionFilter
	 *
	 * @return the value of the attribute
	 */
	public EnumCompressionFilter getCompressionFilter()
	{
		return EnumCompressionFilter.getEnum(getAttribute(AttributeName.COMPRESSIONFILTER, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute DCTQuality
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute DCTQuality
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDCTQuality(double value)
	{
		setAttribute(AttributeName.DCTQUALITY, value, null);
	}

	/**
	 * (17) get double attribute DCTQuality
	 *
	 * @return double the value of the attribute
	 */
	public double getDCTQuality()
	{
		return getRealAttribute(AttributeName.DCTQUALITY, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute InputBox
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute InputBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setInputBox(JDFRectangle value)
	{
		setAttribute(AttributeName.INPUTBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute InputBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getInputBox()
	{
		final String strAttrName = getAttribute(AttributeName.INPUTBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute MountID
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute MountID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMountID(String value)
	{
		setAttribute(AttributeName.MOUNTID, value, null);
	}

	/**
	 * (23) get String attribute MountID
	 *
	 * @return the value of the attribute
	 */
	public String getMountID()
	{
		return getAttribute(AttributeName.MOUNTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Mounting
	--------------------------------------------------------------------- */
	/**
	 * (5) set attribute Mounting
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMounting(EnumMounting enumVar)
	{
		setAttribute(AttributeName.MOUNTING, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Mounting
	 *
	 * @return the value of the attribute
	 */
	public EnumMounting getMounting()
	{
		return EnumMounting.getEnum(getAttribute(AttributeName.MOUNTING, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute OutputResolution
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute OutputResolution
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOutputResolution(JDFXYPair value)
	{
		setAttribute(AttributeName.OUTPUTRESOLUTION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute OutputResolution
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getOutputResolution()
	{
		final String strAttrName = getAttribute(AttributeName.OUTPUTRESOLUTION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute OutputSize
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute OutputSize
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOutputSize(JDFXYPair value)
	{
		setAttribute(AttributeName.OUTPUTSIZE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute OutputSize
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getOutputSize()
	{
		final String strAttrName = getAttribute(AttributeName.OUTPUTSIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute SplitDocuments
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute SplitDocuments
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSplitDocuments(int value)
	{
		setAttribute(AttributeName.SPLITDOCUMENTS, value, null);
	}

	/**
	 * (15) get int attribute SplitDocuments
	 *
	 * @return int the value of the attribute
	 */
	public int getSplitDocuments()
	{
		return getIntAttribute(AttributeName.SPLITDOCUMENTS, null, 0);
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

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

}
