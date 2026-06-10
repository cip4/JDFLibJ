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
import java.util.List;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.process.JDFDeviceNSpace;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFScreenSelector;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoColorSpaceConversionOp : public JDFElement
 */

public abstract class JDFAutoColorSpaceConversionOp extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[12];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BLACKPOINTCOMPENSATION, 0x3311111111l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.BLACKPOINTCOMPENSATIONDETAILS, 0x3311111111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.IGNOREEMBEDDEDICC, 0x4444443333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.OBJECTTAGS, 0x3333331111l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.OPERATION, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumOperation.class, 0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.PRESERVEBLACK, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[6] = new AtrInfoTable(AttributeName.RENDERINGINTENT, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumRenderingIntent.class, 0), "ColorSpaceDependent");
		atrInfoTable[7] = new AtrInfoTable(AttributeName.RGBGRAY2BLACK, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[8] = new AtrInfoTable(AttributeName.RGBGRAY2BLACKTHRESHOLD, 0x3333333311l, AttributeInfo.EnumAttributeType.double_, null, "1");
		atrInfoTable[9] = new AtrInfoTable(AttributeName.SOURCECS, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumSourceCS.class, 0), null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.SOURCEOBJECTS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumerations,
				JavaEnumUtil.getEnum(EnumSourceObjects.class, 0), "All");
		atrInfoTable[11] = new AtrInfoTable(AttributeName.SOURCERENDERINGINTENT, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumSourceRenderingIntent.class, 0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.DEVICENSPACE, 0x6666666611l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.FILESPEC, 0x3333333333l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.SCREENSELECTOR, 0x3333333333l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.SEPARATIONSPEC, 0x3333333311l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoColorSpaceConversionOp
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoColorSpaceConversionOp(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoColorSpaceConversionOp
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoColorSpaceConversionOp(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoColorSpaceConversionOp
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoColorSpaceConversionOp(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numOperation
	 */

	public enum EnumOperation
	{
		Convert, Tag, Untag, Retag, ConvertIgnore;

		public static EnumOperation getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumOperation.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numRenderingIntent
	 */

	public enum EnumRenderingIntent
	{
		ColorSpaceDependent, Perceptual, Saturation, RelativeColorimetric, AbsoluteColorimetric;

		public static EnumRenderingIntent getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumRenderingIntent.class, val, EnumRenderingIntent.ColorSpaceDependent);
		}
	}

	/**
	 * Enumeration strings for numSourceCS
	 */

	public enum EnumSourceCS
	{
		CalGray, CalRGB, Calibrated, CIEBased, CMYK, DeviceN, DevIndep, RGB, Gray, ICCBased, ICCCMYK, ICCGray, ICCLAB, ICCRGB, Lab, Separation, YUV, All;

		public static EnumSourceCS getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSourceCS.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numSourceObjects
	 */

	public enum EnumSourceObjects
	{
		All, ImagePhotographic, ImageScreenShot, LineArt, SmoothShades, Text;

		public static EnumSourceObjects getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSourceObjects.class, val, EnumSourceObjects.All);
		}
	}

	/**
	 * Enumeration strings for numSourceRenderingIntent
	 */

	public enum EnumSourceRenderingIntent
	{
		ColorSpaceDependent, Perceptual, Saturation, RelativeColorimetric, AbsoluteColorimetric;

		public static EnumSourceRenderingIntent getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSourceRenderingIntent.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BlackPointCompensation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BlackPointCompensation
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBlackPointCompensation(final boolean value)
	{
		setAttribute(AttributeName.BLACKPOINTCOMPENSATION, value, null);
	}

	/**
	 * (18) get boolean attribute BlackPointCompensation
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getBlackPointCompensation()
	{
		return getBoolAttribute(AttributeName.BLACKPOINTCOMPENSATION, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BlackPointCompensationDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BlackPointCompensationDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBlackPointCompensationDetails(final String value)
	{
		setAttribute(AttributeName.BLACKPOINTCOMPENSATIONDETAILS, value, null);
	}

	/**
	 * (23) get String attribute BlackPointCompensationDetails
	 *
	 * @return the value of the attribute
	 */
	public String getBlackPointCompensationDetails()
	{
		return getAttribute(AttributeName.BLACKPOINTCOMPENSATIONDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreEmbeddedICC
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreEmbeddedICC
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreEmbeddedICC(final boolean value)
	{
		setAttribute(AttributeName.IGNOREEMBEDDEDICC, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreEmbeddedICC
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreEmbeddedICC()
	{
		return getBoolAttribute(AttributeName.IGNOREEMBEDDEDICC, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ObjectTags
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ObjectTags
	 *
	 * @param value the value to set the attribute to
	 */
	public void setObjectTags(final VString value)
	{
		setAttribute(AttributeName.OBJECTTAGS, value, null);
	}

	/**
	 * (21) get VString attribute ObjectTags
	 *
	 * @return VString the value of the attribute
	 */
	public VString getObjectTags()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.OBJECTTAGS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Operation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Operation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOperation(final EnumOperation enumVar)
	{
		setAttribute(AttributeName.OPERATION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Operation
	 *
	 * @return the value of the attribute
	 */
	public EnumOperation getOperation()
	{
		return EnumOperation.getEnum(getAttribute(AttributeName.OPERATION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PreserveBlack
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PreserveBlack
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPreserveBlack(final boolean value)
	{
		setAttribute(AttributeName.PRESERVEBLACK, value, null);
	}

	/**
	 * (18) get boolean attribute PreserveBlack
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getPreserveBlack()
	{
		return getBoolAttribute(AttributeName.PRESERVEBLACK, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute RenderingIntent
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute RenderingIntent
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setRenderingIntent(final EnumRenderingIntent enumVar)
	{
		setAttribute(AttributeName.RENDERINGINTENT, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute RenderingIntent
	 *
	 * @return the value of the attribute
	 */
	public EnumRenderingIntent getRenderingIntent()
	{
		return EnumRenderingIntent.getEnum(getAttribute(AttributeName.RENDERINGINTENT, null, "ColorSpaceDependent"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute RGBGray2Black
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RGBGray2Black
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRGBGray2Black(final boolean value)
	{
		setAttribute(AttributeName.RGBGRAY2BLACK, value, null);
	}

	/**
	 * (18) get boolean attribute RGBGray2Black
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getRGBGray2Black()
	{
		return getBoolAttribute(AttributeName.RGBGRAY2BLACK, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute RGBGray2BlackThreshold
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RGBGray2BlackThreshold
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRGBGray2BlackThreshold(final double value)
	{
		setAttribute(AttributeName.RGBGRAY2BLACKTHRESHOLD, value, null);
	}

	/**
	 * (17) get double attribute RGBGray2BlackThreshold
	 *
	 * @return double the value of the attribute
	 */
	public double getRGBGray2BlackThreshold()
	{
		return getRealAttribute(AttributeName.RGBGRAY2BLACKTHRESHOLD, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SourceCS
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SourceCS
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSourceCS(final EnumSourceCS enumVar)
	{
		setAttribute(AttributeName.SOURCECS, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute SourceCS
	 *
	 * @return the value of the attribute
	 */
	public EnumSourceCS getSourceCS()
	{
		return EnumSourceCS.getEnum(getAttribute(AttributeName.SOURCECS, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SourceObjects
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute SourceObjects
	 *
	 * @param v List of the enumeration values
	 */
	public void setSourceObjects(final List<EnumSourceObjects> v)
	{
		setEnumsAttribute(AttributeName.SOURCEOBJECTS, v, null);
	}

	/**
	 * (9.2) get SourceObjects attribute SourceObjects
	 *
	 * @return Vector of the enumerations
	 */
	public List<EnumSourceObjects> getSourceObjects()
	{
		return getEnumerationsAttribute(AttributeName.SOURCEOBJECTS, null, EnumSourceObjects.class);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SourceRenderingIntent
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SourceRenderingIntent
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSourceRenderingIntent(final EnumSourceRenderingIntent enumVar)
	{
		setAttribute(AttributeName.SOURCERENDERINGINTENT, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute SourceRenderingIntent
	 *
	 * @return the value of the attribute
	 */
	public EnumSourceRenderingIntent getSourceRenderingIntent()
	{
		return EnumSourceRenderingIntent.getEnum(getAttribute(AttributeName.SOURCERENDERINGINTENT, null, null));
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element DeviceNSpace
	 *
	 * @return JDFDeviceNSpace the element
	 */
	public JDFDeviceNSpace getDeviceNSpace()
	{
		return (JDFDeviceNSpace) getElement(ElementName.DEVICENSPACE, null, 0);
	}

	/**
	 * (25) getCreateDeviceNSpace
	 * 
	 * @return JDFDeviceNSpace the element
	 */
	public JDFDeviceNSpace getCreateDeviceNSpace()
	{
		return (JDFDeviceNSpace) getCreateElement_JDFElement(ElementName.DEVICENSPACE, null, 0);
	}

	/**
	 * (29) append element DeviceNSpace
	 *
	 * @return JDFDeviceNSpace the element
	 * @ if the element already exists
	 */
	public JDFDeviceNSpace appendDeviceNSpace()
	{
		return (JDFDeviceNSpace) appendElementN(ElementName.DEVICENSPACE, 1, null);
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

	/**
	 * (24) const get element ScreenSelector
	 *
	 * @return JDFScreenSelector the element
	 */
	public JDFScreenSelector getScreenSelector()
	{
		return (JDFScreenSelector) getElement(ElementName.SCREENSELECTOR, null, 0);
	}

	/**
	 * (25) getCreateScreenSelector
	 * 
	 * @return JDFScreenSelector the element
	 */
	public JDFScreenSelector getCreateScreenSelector()
	{
		return (JDFScreenSelector) getCreateElement_JDFElement(ElementName.SCREENSELECTOR, null, 0);
	}

	/**
	 * (26) getCreateScreenSelector
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFScreenSelector the element
	 */
	public JDFScreenSelector getCreateScreenSelector(final int iSkip)
	{
		return (JDFScreenSelector) getCreateElement_JDFElement(ElementName.SCREENSELECTOR, null, iSkip);
	}

	/**
	 * (27) const get element ScreenSelector
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFScreenSelector the element
	 *         default is getScreenSelector(0)
	 */
	public JDFScreenSelector getScreenSelector(final int iSkip)
	{
		return (JDFScreenSelector) getElement(ElementName.SCREENSELECTOR, null, iSkip);
	}

	/**
	 * Get all ScreenSelector from the current element
	 * 
	 * @return Collection<JDFScreenSelector>, null if none are available
	 */
	public Collection<JDFScreenSelector> getAllScreenSelector()
	{
		return getChildArrayByClass(JDFScreenSelector.class, false, 0);
	}

	/**
	 * (30) append element ScreenSelector
	 *
	 * @return JDFScreenSelector the element
	 */
	public JDFScreenSelector appendScreenSelector()
	{
		return (JDFScreenSelector) appendElement(ElementName.SCREENSELECTOR, null);
	}

	/**
	 * (24) const get element SeparationSpec
	 *
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec getSeparationSpec()
	{
		return (JDFSeparationSpec) getElement(ElementName.SEPARATIONSPEC, null, 0);
	}

	/**
	 * (25) getCreateSeparationSpec
	 * 
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec getCreateSeparationSpec()
	{
		return (JDFSeparationSpec) getCreateElement_JDFElement(ElementName.SEPARATIONSPEC, null, 0);
	}

	/**
	 * (26) getCreateSeparationSpec
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec getCreateSeparationSpec(final int iSkip)
	{
		return (JDFSeparationSpec) getCreateElement_JDFElement(ElementName.SEPARATIONSPEC, null, iSkip);
	}

	/**
	 * (27) const get element SeparationSpec
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFSeparationSpec the element
	 *         default is getSeparationSpec(0)
	 */
	public JDFSeparationSpec getSeparationSpec(final int iSkip)
	{
		return (JDFSeparationSpec) getElement(ElementName.SEPARATIONSPEC, null, iSkip);
	}

	/**
	 * Get all SeparationSpec from the current element
	 * 
	 * @return Collection<JDFSeparationSpec>, null if none are available
	 */
	public Collection<JDFSeparationSpec> getAllSeparationSpec()
	{
		return getChildArrayByClass(JDFSeparationSpec.class, false, 0);
	}

	/**
	 * (30) append element SeparationSpec
	 *
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec appendSeparationSpec()
	{
		return (JDFSeparationSpec) appendElement(ElementName.SEPARATIONSPEC, null);
	}

}
