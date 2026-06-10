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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFAdvancedParams;
import org.cip4.jdflib.resource.process.JDFPDFXParams;
import org.cip4.jdflib.resource.process.JDFThinPDFParams;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoPSToPDFConversionParams : public JDFResource
 */

public abstract class JDFAutoPSToPDFConversionParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[15];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ASCII85ENCODEPAGES, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.BINDING, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumBinding.class, 0), "Left");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DETECTBLEND, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.DOTHUMBNAILS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[4] = new AtrInfoTable(AttributeName.OPTIMIZE, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[5] = new AtrInfoTable(AttributeName.AUTOROTATEPAGES, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumAutoRotatePages.class, 0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.COMPRESSPAGES, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.DEFAULTRENDERINGINTENT, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumDefaultRenderingIntent.class, 0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.ENDPAGE, 0x4444444333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.IMAGEMEMORY, 0x4444444433l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.INITIALPAGESIZE, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.INITIALRESOLUTION, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.OVERPRINTMODE, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.PDFVERSION, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.STARTPAGE, 0x4444444333l, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.ADVANCEDPARAMS, 0x6666666666l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.PDFXPARAMS, 0x6666666611l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.THINPDFPARAMS, 0x6666666666l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPSToPDFConversionParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPSToPDFConversionParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPSToPDFConversionParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPSToPDFConversionParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPSToPDFConversionParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPSToPDFConversionParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
	 * Enumeration strings for numBinding
	 */

	public enum EnumBinding
	{
		Left, Right;

		public static EnumBinding getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumBinding.class, val, EnumBinding.Left);
		}
	}

	/**
	 * Enumeration strings for numAutoRotatePages
	 */

	public enum EnumAutoRotatePages
	{
		None, All, PageByPage;

		public static EnumAutoRotatePages getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumAutoRotatePages.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numDefaultRenderingIntent
	 */

	public enum EnumDefaultRenderingIntent
	{
		Default, Perceptual, Saturation, RelativeColorimetric, AbsoluteColorimetric;

		public static EnumDefaultRenderingIntent getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumDefaultRenderingIntent.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ASCII85EncodePages
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ASCII85EncodePages
	 *
	 * @param value the value to set the attribute to
	 */
	public void setASCII85EncodePages(final boolean value)
	{
		setAttribute(AttributeName.ASCII85ENCODEPAGES, value, null);
	}

	/**
	 * (18) get boolean attribute ASCII85EncodePages
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getASCII85EncodePages()
	{
		return getBoolAttribute(AttributeName.ASCII85ENCODEPAGES, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Binding
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Binding
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBinding(final EnumBinding enumVar)
	{
		setAttribute(AttributeName.BINDING, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Binding
	 *
	 * @return the value of the attribute
	 */
	public EnumBinding getBinding()
	{
		return EnumBinding.getEnum(getAttribute(AttributeName.BINDING, null, "Left"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DetectBlend
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DetectBlend
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDetectBlend(final boolean value)
	{
		setAttribute(AttributeName.DETECTBLEND, value, null);
	}

	/**
	 * (18) get boolean attribute DetectBlend
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getDetectBlend()
	{
		return getBoolAttribute(AttributeName.DETECTBLEND, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DoThumbnails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DoThumbnails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDoThumbnails(final boolean value)
	{
		setAttribute(AttributeName.DOTHUMBNAILS, value, null);
	}

	/**
	 * (18) get boolean attribute DoThumbnails
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getDoThumbnails()
	{
		return getBoolAttribute(AttributeName.DOTHUMBNAILS, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Optimize
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Optimize
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOptimize(final boolean value)
	{
		setAttribute(AttributeName.OPTIMIZE, value, null);
	}

	/**
	 * (18) get boolean attribute Optimize
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getOptimize()
	{
		return getBoolAttribute(AttributeName.OPTIMIZE, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AutoRotatePages
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute AutoRotatePages
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setAutoRotatePages(final EnumAutoRotatePages enumVar)
	{
		setAttribute(AttributeName.AUTOROTATEPAGES, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute AutoRotatePages
	 *
	 * @return the value of the attribute
	 */
	public EnumAutoRotatePages getAutoRotatePages()
	{
		return EnumAutoRotatePages.getEnum(getAttribute(AttributeName.AUTOROTATEPAGES, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CompressPages
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CompressPages
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCompressPages(final boolean value)
	{
		setAttribute(AttributeName.COMPRESSPAGES, value, null);
	}

	/**
	 * (18) get boolean attribute CompressPages
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getCompressPages()
	{
		return getBoolAttribute(AttributeName.COMPRESSPAGES, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DefaultRenderingIntent
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DefaultRenderingIntent
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDefaultRenderingIntent(final EnumDefaultRenderingIntent enumVar)
	{
		setAttribute(AttributeName.DEFAULTRENDERINGINTENT, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute DefaultRenderingIntent
	 *
	 * @return the value of the attribute
	 */
	public EnumDefaultRenderingIntent getDefaultRenderingIntent()
	{
		return EnumDefaultRenderingIntent.getEnum(getAttribute(AttributeName.DEFAULTRENDERINGINTENT, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute EndPage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EndPage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEndPage(final int value)
	{
		setAttribute(AttributeName.ENDPAGE, value, null);
	}

	/**
	 * (15) get int attribute EndPage
	 *
	 * @return int the value of the attribute
	 */
	public int getEndPage()
	{
		return getIntAttribute(AttributeName.ENDPAGE, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ImageMemory
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ImageMemory
	 *
	 * @param value the value to set the attribute to
	 */
	public void setImageMemory(final int value)
	{
		setAttribute(AttributeName.IMAGEMEMORY, value, null);
	}

	/**
	 * (15) get int attribute ImageMemory
	 *
	 * @return int the value of the attribute
	 */
	public int getImageMemory()
	{
		return getIntAttribute(AttributeName.IMAGEMEMORY, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute InitialPageSize
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute InitialPageSize
	 *
	 * @param value the value to set the attribute to
	 */
	public void setInitialPageSize(final JDFXYPair value)
	{
		setAttribute(AttributeName.INITIALPAGESIZE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute InitialPageSize
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getInitialPageSize()
	{
		final String strAttrName = getAttribute(AttributeName.INITIALPAGESIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute InitialResolution
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute InitialResolution
	 *
	 * @param value the value to set the attribute to
	 */
	public void setInitialResolution(final JDFXYPair value)
	{
		setAttribute(AttributeName.INITIALRESOLUTION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute InitialResolution
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getInitialResolution()
	{
		final String strAttrName = getAttribute(AttributeName.INITIALRESOLUTION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute OverPrintMode
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OverPrintMode
	 *
	 * @param value the value to set the attribute to
	 */
	public void setOverPrintMode(final int value)
	{
		setAttribute(AttributeName.OVERPRINTMODE, value, null);
	}

	/**
	 * (15) get int attribute OverPrintMode
	 *
	 * @return int the value of the attribute
	 */
	public int getOverPrintMode()
	{
		return getIntAttribute(AttributeName.OVERPRINTMODE, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PDFVersion
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PDFVersion
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPDFVersion(final double value)
	{
		setAttribute(AttributeName.PDFVERSION, value, null);
	}

	/**
	 * (17) get double attribute PDFVersion
	 *
	 * @return double the value of the attribute
	 */
	public double getPDFVersion()
	{
		return getRealAttribute(AttributeName.PDFVERSION, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StartPage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute StartPage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStartPage(final int value)
	{
		setAttribute(AttributeName.STARTPAGE, value, null);
	}

	/**
	 * (15) get int attribute StartPage
	 *
	 * @return int the value of the attribute
	 */
	public int getStartPage()
	{
		return getIntAttribute(AttributeName.STARTPAGE, null, 0);
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element AdvancedParams
	 *
	 * @return JDFAdvancedParams the element
	 */
	public JDFAdvancedParams getAdvancedParams()
	{
		return (JDFAdvancedParams) getElement(ElementName.ADVANCEDPARAMS, null, 0);
	}

	/**
	 * (25) getCreateAdvancedParams
	 * 
	 * @return JDFAdvancedParams the element
	 */
	public JDFAdvancedParams getCreateAdvancedParams()
	{
		return (JDFAdvancedParams) getCreateElement_JDFElement(ElementName.ADVANCEDPARAMS, null, 0);
	}

	/**
	 * (29) append element AdvancedParams
	 *
	 * @return JDFAdvancedParams the element
	 * @ if the element already exists
	 */
	public JDFAdvancedParams appendAdvancedParams()
	{
		return (JDFAdvancedParams) appendElementN(ElementName.ADVANCEDPARAMS, 1, null);
	}

	/**
	 * (24) const get element PDFXParams
	 *
	 * @return JDFPDFXParams the element
	 */
	public JDFPDFXParams getPDFXParams()
	{
		return (JDFPDFXParams) getElement(ElementName.PDFXPARAMS, null, 0);
	}

	/**
	 * (25) getCreatePDFXParams
	 * 
	 * @return JDFPDFXParams the element
	 */
	public JDFPDFXParams getCreatePDFXParams()
	{
		return (JDFPDFXParams) getCreateElement_JDFElement(ElementName.PDFXPARAMS, null, 0);
	}

	/**
	 * (29) append element PDFXParams
	 *
	 * @return JDFPDFXParams the element
	 * @ if the element already exists
	 */
	public JDFPDFXParams appendPDFXParams()
	{
		return (JDFPDFXParams) appendElementN(ElementName.PDFXPARAMS, 1, null);
	}

	/**
	 * (24) const get element ThinPDFParams
	 *
	 * @return JDFThinPDFParams the element
	 */
	public JDFThinPDFParams getThinPDFParams()
	{
		return (JDFThinPDFParams) getElement(ElementName.THINPDFPARAMS, null, 0);
	}

	/**
	 * (25) getCreateThinPDFParams
	 * 
	 * @return JDFThinPDFParams the element
	 */
	public JDFThinPDFParams getCreateThinPDFParams()
	{
		return (JDFThinPDFParams) getCreateElement_JDFElement(ElementName.THINPDFPARAMS, null, 0);
	}

	/**
	 * (29) append element ThinPDFParams
	 *
	 * @return JDFThinPDFParams the element
	 * @ if the element already exists
	 */
	public JDFThinPDFParams appendThinPDFParams()
	{
		return (JDFThinPDFParams) appendElementN(ElementName.THINPDFPARAMS, 1, null);
	}

}
