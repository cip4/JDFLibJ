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
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoPDFToPSConversionParams : public JDFResource
 */

public abstract class JDFAutoPDFToPSConversionParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[37];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BINARYOK, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CENTERCROPBOX, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.GENERATEPAGESTREAMS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.IGNOREANNOTFORMS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[4] = new AtrInfoTable(AttributeName.IGNOREBG, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[5] = new AtrInfoTable(AttributeName.IGNORECOLORSEPS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[6] = new AtrInfoTable(AttributeName.IGNOREDSC, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[7] = new AtrInfoTable(AttributeName.IGNOREEXTERNSTREAMREF, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[8] = new AtrInfoTable(AttributeName.IGNOREHALFTONES, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[9] = new AtrInfoTable(AttributeName.IGNOREOVERPRINT, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[10] = new AtrInfoTable(AttributeName.IGNOREPAGEROTATION, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[11] = new AtrInfoTable(AttributeName.IGNORERAWDATA, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[12] = new AtrInfoTable(AttributeName.IGNORESEPARABLEIMAGESONLY, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[13] = new AtrInfoTable(AttributeName.IGNORESHOWPAGE, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[14] = new AtrInfoTable(AttributeName.IGNORETRANSFERS, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[15] = new AtrInfoTable(AttributeName.IGNORETTFONTSFIRST, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[16] = new AtrInfoTable(AttributeName.IGNOREUCR, 0x3333333331l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[17] = new AtrInfoTable(AttributeName.INCLUDEBASEFONTS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumIncludeBaseFonts.class, 0), "IncludeNever");
		atrInfoTable[18] = new AtrInfoTable(AttributeName.INCLUDECIDFONTS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumIncludeCIDFonts.class, 0), "IncludeOncePerDoc");
		atrInfoTable[19] = new AtrInfoTable(AttributeName.INCLUDEEMBEDDEDFONTS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumIncludeEmbeddedFonts.class, 0), "IncludeOncePerDoc");
		atrInfoTable[20] = new AtrInfoTable(AttributeName.INCLUDEOTHERRESOURCES, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumIncludeOtherResources.class, 0), "IncludeOncePerDoc");
		atrInfoTable[21] = new AtrInfoTable(AttributeName.INCLUDEPROCSETS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumIncludeProcSets.class, 0), "IncludeOncePerDoc");
		atrInfoTable[22] = new AtrInfoTable(AttributeName.INCLUDETRUETYPEFONTS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumIncludeTrueTypeFonts.class, 0), "IncludeOncePerDoc");
		atrInfoTable[23] = new AtrInfoTable(AttributeName.INCLUDETYPE1FONTS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumIncludeType1Fonts.class, 0), "IncludeOncePerDoc");
		atrInfoTable[24] = new AtrInfoTable(AttributeName.INCLUDETYPE3FONTS, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumIncludeType3Fonts.class, 0), null);
		atrInfoTable[25] = new AtrInfoTable(AttributeName.OUTPUTTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumOutputType.class, 0), "PostScript");
		atrInfoTable[26] = new AtrInfoTable(AttributeName.PSLEVEL, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, "2");
		atrInfoTable[27] = new AtrInfoTable(AttributeName.SCALE, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, "100");
		atrInfoTable[28] = new AtrInfoTable(AttributeName.SETPAGESIZE, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[29] = new AtrInfoTable(AttributeName.SETUPPROCSETS, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[30] = new AtrInfoTable(AttributeName.SHRINKTOFIT, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[31] = new AtrInfoTable(AttributeName.SUPPRESSCENTER, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[32] = new AtrInfoTable(AttributeName.SUPPRESSROTATE, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[33] = new AtrInfoTable(AttributeName.TTAST42, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[34] = new AtrInfoTable(AttributeName.USEFONTALIASNAMES, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[35] = new AtrInfoTable(AttributeName.IGNOREDEVICEEXTGSTATE, 0x4444444443l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[36] = new AtrInfoTable(AttributeName.BOUNDINGBOX, 0x3333333333l, AttributeInfo.EnumAttributeType.rectangle, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoPDFToPSConversionParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPDFToPSConversionParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPDFToPSConversionParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPDFToPSConversionParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPDFToPSConversionParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPDFToPSConversionParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
	 * Enumeration strings for numIncludeBaseFonts
	 */

	public enum EnumIncludeBaseFonts
	{
		IncludeNever, IncludeOncePerDoc, IncludeOncePerPage;

		public static EnumIncludeBaseFonts getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumIncludeBaseFonts.class, val, EnumIncludeBaseFonts.IncludeNever);
		}
	}

	/**
	 * Enumeration strings for numIncludeCIDFonts
	 */

	public enum EnumIncludeCIDFonts
	{
		IncludeNever, IncludeOncePerDoc, IncludeOncePerPage;

		public static EnumIncludeCIDFonts getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumIncludeCIDFonts.class, val, EnumIncludeCIDFonts.IncludeOncePerDoc);
		}
	}

	/**
	 * Enumeration strings for numIncludeEmbeddedFonts
	 */

	public enum EnumIncludeEmbeddedFonts
	{
		IncludeNever, IncludeOncePerDoc, IncludeOncePerPage;

		public static EnumIncludeEmbeddedFonts getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumIncludeEmbeddedFonts.class, val, EnumIncludeEmbeddedFonts.IncludeOncePerDoc);
		}
	}

	/**
	 * Enumeration strings for numIncludeOtherResources
	 */

	public enum EnumIncludeOtherResources
	{
		IncludeNever, IncludeOncePerDoc, IncludeOncePerPage;

		public static EnumIncludeOtherResources getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumIncludeOtherResources.class, val, EnumIncludeOtherResources.IncludeOncePerDoc);
		}
	}

	/**
	 * Enumeration strings for numIncludeProcSets
	 */

	public enum EnumIncludeProcSets
	{
		IncludeNever, IncludeOncePerDoc, IncludeOncePerPage;

		public static EnumIncludeProcSets getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumIncludeProcSets.class, val, EnumIncludeProcSets.IncludeOncePerDoc);
		}
	}

	/**
	 * Enumeration strings for numIncludeTrueTypeFonts
	 */

	public enum EnumIncludeTrueTypeFonts
	{
		IncludeNever, IncludeOncePerDoc, IncludeOncePerPage;

		public static EnumIncludeTrueTypeFonts getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumIncludeTrueTypeFonts.class, val, EnumIncludeTrueTypeFonts.IncludeOncePerDoc);
		}
	}

	/**
	 * Enumeration strings for numIncludeType1Fonts
	 */

	public enum EnumIncludeType1Fonts
	{
		IncludeNever, IncludeOncePerDoc, IncludeOncePerPage;

		public static EnumIncludeType1Fonts getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumIncludeType1Fonts.class, val, EnumIncludeType1Fonts.IncludeOncePerDoc);
		}
	}

	/**
	 * Enumeration strings for numIncludeType3Fonts
	 */

	public enum EnumIncludeType3Fonts
	{
		IncludeNever, IncludeOncePerDoc, IncludeOncePerPage;

		public static EnumIncludeType3Fonts getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumIncludeType3Fonts.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numOutputType
	 */

	public enum EnumOutputType
	{
		PostScript, EPS;

		public static EnumOutputType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumOutputType.class, val, EnumOutputType.PostScript);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BinaryOK
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BinaryOK
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBinaryOK(final boolean value)
	{
		setAttribute(AttributeName.BINARYOK, value, null);
	}

	/**
	 * (18) get boolean attribute BinaryOK
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getBinaryOK()
	{
		return getBoolAttribute(AttributeName.BINARYOK, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CenterCropBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CenterCropBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCenterCropBox(final boolean value)
	{
		setAttribute(AttributeName.CENTERCROPBOX, value, null);
	}

	/**
	 * (18) get boolean attribute CenterCropBox
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getCenterCropBox()
	{
		return getBoolAttribute(AttributeName.CENTERCROPBOX, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute GeneratePageStreams
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute GeneratePageStreams
	 *
	 * @param value the value to set the attribute to
	 */
	public void setGeneratePageStreams(final boolean value)
	{
		setAttribute(AttributeName.GENERATEPAGESTREAMS, value, null);
	}

	/**
	 * (18) get boolean attribute GeneratePageStreams
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getGeneratePageStreams()
	{
		return getBoolAttribute(AttributeName.GENERATEPAGESTREAMS, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreAnnotForms
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreAnnotForms
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreAnnotForms(final boolean value)
	{
		setAttribute(AttributeName.IGNOREANNOTFORMS, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreAnnotForms
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreAnnotForms()
	{
		return getBoolAttribute(AttributeName.IGNOREANNOTFORMS, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreBG
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreBG
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreBG(final boolean value)
	{
		setAttribute(AttributeName.IGNOREBG, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreBG
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreBG()
	{
		return getBoolAttribute(AttributeName.IGNOREBG, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreColorSeps
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreColorSeps
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreColorSeps(final boolean value)
	{
		setAttribute(AttributeName.IGNORECOLORSEPS, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreColorSeps
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreColorSeps()
	{
		return getBoolAttribute(AttributeName.IGNORECOLORSEPS, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreDSC
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreDSC
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreDSC(final boolean value)
	{
		setAttribute(AttributeName.IGNOREDSC, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreDSC
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreDSC()
	{
		return getBoolAttribute(AttributeName.IGNOREDSC, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreExternStreamRef
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreExternStreamRef
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreExternStreamRef(final boolean value)
	{
		setAttribute(AttributeName.IGNOREEXTERNSTREAMREF, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreExternStreamRef
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreExternStreamRef()
	{
		return getBoolAttribute(AttributeName.IGNOREEXTERNSTREAMREF, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreHalftones
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreHalftones
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreHalftones(final boolean value)
	{
		setAttribute(AttributeName.IGNOREHALFTONES, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreHalftones
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreHalftones()
	{
		return getBoolAttribute(AttributeName.IGNOREHALFTONES, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreOverprint
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreOverprint
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreOverprint(final boolean value)
	{
		setAttribute(AttributeName.IGNOREOVERPRINT, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreOverprint
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreOverprint()
	{
		return getBoolAttribute(AttributeName.IGNOREOVERPRINT, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnorePageRotation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnorePageRotation
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnorePageRotation(final boolean value)
	{
		setAttribute(AttributeName.IGNOREPAGEROTATION, value, null);
	}

	/**
	 * (18) get boolean attribute IgnorePageRotation
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnorePageRotation()
	{
		return getBoolAttribute(AttributeName.IGNOREPAGEROTATION, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreRawData
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreRawData
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreRawData(final boolean value)
	{
		setAttribute(AttributeName.IGNORERAWDATA, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreRawData
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreRawData()
	{
		return getBoolAttribute(AttributeName.IGNORERAWDATA, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreSeparableImagesOnly
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreSeparableImagesOnly
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreSeparableImagesOnly(final boolean value)
	{
		setAttribute(AttributeName.IGNORESEPARABLEIMAGESONLY, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreSeparableImagesOnly
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreSeparableImagesOnly()
	{
		return getBoolAttribute(AttributeName.IGNORESEPARABLEIMAGESONLY, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreShowPage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreShowPage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreShowPage(final boolean value)
	{
		setAttribute(AttributeName.IGNORESHOWPAGE, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreShowPage
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreShowPage()
	{
		return getBoolAttribute(AttributeName.IGNORESHOWPAGE, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreTransfers
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreTransfers
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreTransfers(final boolean value)
	{
		setAttribute(AttributeName.IGNORETRANSFERS, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreTransfers
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreTransfers()
	{
		return getBoolAttribute(AttributeName.IGNORETRANSFERS, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreTTFontsFirst
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreTTFontsFirst
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreTTFontsFirst(final boolean value)
	{
		setAttribute(AttributeName.IGNORETTFONTSFIRST, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreTTFontsFirst
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreTTFontsFirst()
	{
		return getBoolAttribute(AttributeName.IGNORETTFONTSFIRST, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreUCR
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreUCR
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreUCR(final boolean value)
	{
		setAttribute(AttributeName.IGNOREUCR, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreUCR
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreUCR()
	{
		return getBoolAttribute(AttributeName.IGNOREUCR, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IncludeBaseFonts
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute IncludeBaseFonts
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setIncludeBaseFonts(final EnumIncludeBaseFonts enumVar)
	{
		setAttribute(AttributeName.INCLUDEBASEFONTS, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute IncludeBaseFonts
	 *
	 * @return the value of the attribute
	 */
	public EnumIncludeBaseFonts getIncludeBaseFonts()
	{
		return EnumIncludeBaseFonts.getEnum(getAttribute(AttributeName.INCLUDEBASEFONTS, null, "IncludeNever"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IncludeCIDFonts
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute IncludeCIDFonts
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setIncludeCIDFonts(final EnumIncludeCIDFonts enumVar)
	{
		setAttribute(AttributeName.INCLUDECIDFONTS, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute IncludeCIDFonts
	 *
	 * @return the value of the attribute
	 */
	public EnumIncludeCIDFonts getIncludeCIDFonts()
	{
		return EnumIncludeCIDFonts.getEnum(getAttribute(AttributeName.INCLUDECIDFONTS, null, "IncludeOncePerDoc"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IncludeEmbeddedFonts
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute IncludeEmbeddedFonts
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setIncludeEmbeddedFonts(final EnumIncludeEmbeddedFonts enumVar)
	{
		setAttribute(AttributeName.INCLUDEEMBEDDEDFONTS, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute IncludeEmbeddedFonts
	 *
	 * @return the value of the attribute
	 */
	public EnumIncludeEmbeddedFonts getIncludeEmbeddedFonts()
	{
		return EnumIncludeEmbeddedFonts.getEnum(getAttribute(AttributeName.INCLUDEEMBEDDEDFONTS, null, "IncludeOncePerDoc"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IncludeOtherResources
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute IncludeOtherResources
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setIncludeOtherResources(final EnumIncludeOtherResources enumVar)
	{
		setAttribute(AttributeName.INCLUDEOTHERRESOURCES, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute IncludeOtherResources
	 *
	 * @return the value of the attribute
	 */
	public EnumIncludeOtherResources getIncludeOtherResources()
	{
		return EnumIncludeOtherResources.getEnum(getAttribute(AttributeName.INCLUDEOTHERRESOURCES, null, "IncludeOncePerDoc"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IncludeProcSets
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute IncludeProcSets
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setIncludeProcSets(final EnumIncludeProcSets enumVar)
	{
		setAttribute(AttributeName.INCLUDEPROCSETS, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute IncludeProcSets
	 *
	 * @return the value of the attribute
	 */
	public EnumIncludeProcSets getIncludeProcSets()
	{
		return EnumIncludeProcSets.getEnum(getAttribute(AttributeName.INCLUDEPROCSETS, null, "IncludeOncePerDoc"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IncludeTrueTypeFonts
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute IncludeTrueTypeFonts
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setIncludeTrueTypeFonts(final EnumIncludeTrueTypeFonts enumVar)
	{
		setAttribute(AttributeName.INCLUDETRUETYPEFONTS, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute IncludeTrueTypeFonts
	 *
	 * @return the value of the attribute
	 */
	public EnumIncludeTrueTypeFonts getIncludeTrueTypeFonts()
	{
		return EnumIncludeTrueTypeFonts.getEnum(getAttribute(AttributeName.INCLUDETRUETYPEFONTS, null, "IncludeOncePerDoc"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IncludeType1Fonts
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute IncludeType1Fonts
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setIncludeType1Fonts(final EnumIncludeType1Fonts enumVar)
	{
		setAttribute(AttributeName.INCLUDETYPE1FONTS, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute IncludeType1Fonts
	 *
	 * @return the value of the attribute
	 */
	public EnumIncludeType1Fonts getIncludeType1Fonts()
	{
		return EnumIncludeType1Fonts.getEnum(getAttribute(AttributeName.INCLUDETYPE1FONTS, null, "IncludeOncePerDoc"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IncludeType3Fonts
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute IncludeType3Fonts
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setIncludeType3Fonts(final EnumIncludeType3Fonts enumVar)
	{
		setAttribute(AttributeName.INCLUDETYPE3FONTS, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute IncludeType3Fonts
	 *
	 * @return the value of the attribute
	 */
	public EnumIncludeType3Fonts getIncludeType3Fonts()
	{
		return EnumIncludeType3Fonts.getEnum(getAttribute(AttributeName.INCLUDETYPE3FONTS, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute OutputType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute OutputType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setOutputType(final EnumOutputType enumVar)
	{
		setAttribute(AttributeName.OUTPUTTYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute OutputType
	 *
	 * @return the value of the attribute
	 */
	public EnumOutputType getOutputType()
	{
		return EnumOutputType.getEnum(getAttribute(AttributeName.OUTPUTTYPE, null, "PostScript"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PSLevel
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PSLevel
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPSLevel(final int value)
	{
		setAttribute(AttributeName.PSLEVEL, value, null);
	}

	/**
	 * (15) get int attribute PSLevel
	 *
	 * @return int the value of the attribute
	 */
	public int getPSLevel()
	{
		return getIntAttribute(AttributeName.PSLEVEL, null, 2);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Scale
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Scale
	 *
	 * @param value the value to set the attribute to
	 */
	public void setScale(final double value)
	{
		setAttribute(AttributeName.SCALE, value, null);
	}

	/**
	 * (17) get double attribute Scale
	 *
	 * @return double the value of the attribute
	 */
	public double getScale()
	{
		return getRealAttribute(AttributeName.SCALE, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SetPageSize
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SetPageSize
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSetPageSize(final boolean value)
	{
		setAttribute(AttributeName.SETPAGESIZE, value, null);
	}

	/**
	 * (18) get boolean attribute SetPageSize
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getSetPageSize()
	{
		return getBoolAttribute(AttributeName.SETPAGESIZE, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SetupProcsets
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SetupProcsets
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSetupProcsets(final boolean value)
	{
		setAttribute(AttributeName.SETUPPROCSETS, value, null);
	}

	/**
	 * (18) get boolean attribute SetupProcsets
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getSetupProcsets()
	{
		return getBoolAttribute(AttributeName.SETUPPROCSETS, null, true);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ShrinkToFit
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ShrinkToFit
	 *
	 * @param value the value to set the attribute to
	 */
	public void setShrinkToFit(final boolean value)
	{
		setAttribute(AttributeName.SHRINKTOFIT, value, null);
	}

	/**
	 * (18) get boolean attribute ShrinkToFit
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getShrinkToFit()
	{
		return getBoolAttribute(AttributeName.SHRINKTOFIT, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SuppressCenter
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SuppressCenter
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSuppressCenter(final boolean value)
	{
		setAttribute(AttributeName.SUPPRESSCENTER, value, null);
	}

	/**
	 * (18) get boolean attribute SuppressCenter
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getSuppressCenter()
	{
		return getBoolAttribute(AttributeName.SUPPRESSCENTER, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute SuppressRotate
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SuppressRotate
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSuppressRotate(final boolean value)
	{
		setAttribute(AttributeName.SUPPRESSROTATE, value, null);
	}

	/**
	 * (18) get boolean attribute SuppressRotate
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getSuppressRotate()
	{
		return getBoolAttribute(AttributeName.SUPPRESSROTATE, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute TTasT42
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TTasT42
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTTasT42(final boolean value)
	{
		setAttribute(AttributeName.TTAST42, value, null);
	}

	/**
	 * (18) get boolean attribute TTasT42
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getTTasT42()
	{
		return getBoolAttribute(AttributeName.TTAST42, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute UseFontAliasNames
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute UseFontAliasNames
	 *
	 * @param value the value to set the attribute to
	 */
	public void setUseFontAliasNames(final boolean value)
	{
		setAttribute(AttributeName.USEFONTALIASNAMES, value, null);
	}

	/**
	 * (18) get boolean attribute UseFontAliasNames
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getUseFontAliasNames()
	{
		return getBoolAttribute(AttributeName.USEFONTALIASNAMES, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute IgnoreDeviceExtGState
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IgnoreDeviceExtGState
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIgnoreDeviceExtGState(final boolean value)
	{
		setAttribute(AttributeName.IGNOREDEVICEEXTGSTATE, value, null);
	}

	/**
	 * (18) get boolean attribute IgnoreDeviceExtGState
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getIgnoreDeviceExtGState()
	{
		return getBoolAttribute(AttributeName.IGNOREDEVICEEXTGSTATE, null, false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute BoundingBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BoundingBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBoundingBox(final JDFRectangle value)
	{
		setAttribute(AttributeName.BOUNDINGBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute BoundingBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getBoundingBox()
	{
		final String strAttrName = getAttribute(AttributeName.BOUNDINGBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

}
