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
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoScanParams : public JDFResource
 */

public abstract class JDFAutoScanParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BITDEPTH, 0x2222222222l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.MAGNIFICATION, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, "1 1");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.OUTPUTCOLORSPACE, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumOutputColorSpace.class, 0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.COMPRESSIONFILTER, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumCompressionFilter.class, 0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.DCTQUALITY, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.INPUTBOX, 0x3333333333l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MOUNTID, 0x3333333333l, AttributeInfo.EnumAttributeType.shortString, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.MOUNTING, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumMounting.class, 0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.OUTPUTRESOLUTION, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.OUTPUTSIZE, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.SPLITDOCUMENTS, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.FILESPEC, 0x3333333333l);
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
	protected JDFAutoScanParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
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
	protected JDFAutoScanParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
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
	protected JDFAutoScanParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
	 * Enumeration strings for numOutputColorSpace
	 */

	public enum EnumOutputColorSpace
	{
		LAB, RGB, CMYK, GrayScale;

		public static EnumOutputColorSpace getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumOutputColorSpace.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numCompressionFilter
	 */

	public enum EnumCompressionFilter
	{
		CCITTFaxEncode, DCTEncode, FlateEncode, WaveletEncode, JBIG2Encode;

		public static EnumCompressionFilter getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumCompressionFilter.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numMounting
	 */

	public enum EnumMounting
	{
		Unfixed, Fixed, Wet, Registered;

		public static EnumMounting getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumMounting.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BitDepth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BitDepth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBitDepth(final int value)
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

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Magnification
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Magnification
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMagnification(final JDFXYPair value)
	{
		setAttribute(AttributeName.MAGNIFICATION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Magnification
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getMagnification()
	{
		final String strAttrName = getAttribute(AttributeName.MAGNIFICATION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute OutputColorSpace
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute OutputColorSpace
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOutputColorSpace(final EnumOutputColorSpace enumVar)
	{
		setAttribute(AttributeName.OUTPUTCOLORSPACE, JavaEnumUtil.getName(enumVar), null);
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

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CompressionFilter
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute CompressionFilter
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setCompressionFilter(final EnumCompressionFilter enumVar)
	{
		setAttribute(AttributeName.COMPRESSIONFILTER, JavaEnumUtil.getName(enumVar), null);
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

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DCTQuality
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DCTQuality
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDCTQuality(final double value)
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

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute InputBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute InputBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setInputBox(final JDFRectangle value)
	{
		setAttribute(AttributeName.INPUTBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute InputBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getInputBox()
	{
		final String strAttrName = getAttribute(AttributeName.INPUTBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MountID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MountID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMountID(final String value)
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

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Mounting
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Mounting
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMounting(final EnumMounting enumVar)
	{
		setAttribute(AttributeName.MOUNTING, JavaEnumUtil.getName(enumVar), null);
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

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute OutputResolution
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OutputResolution
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOutputResolution(final JDFXYPair value)
	{
		setAttribute(AttributeName.OUTPUTRESOLUTION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute OutputResolution
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getOutputResolution()
	{
		final String strAttrName = getAttribute(AttributeName.OUTPUTRESOLUTION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute OutputSize
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OutputSize
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOutputSize(final JDFXYPair value)
	{
		setAttribute(AttributeName.OUTPUTSIZE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute OutputSize
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getOutputSize()
	{
		final String strAttrName = getAttribute(AttributeName.OUTPUTSIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SplitDocuments
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SplitDocuments
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSplitDocuments(final int value)
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

	/*
	 * ***********************************************************************
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
	 * (26) getCreateFileSpec
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFFileSpec the element
	 */
	public JDFFileSpec getCreateFileSpec(final int iSkip)
	{
		return (JDFFileSpec) getCreateElement_JDFElement(ElementName.FILESPEC, null, iSkip);
	}

	/**
	 * (27) const get element FileSpec
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFFileSpec the element
	 *         default is getFileSpec(0)
	 */
	public JDFFileSpec getFileSpec(final int iSkip)
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
	public void refFileSpec(final JDFFileSpec refTarget)
	{
		refElement(refTarget);
	}

}
