/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFAdvancedParams;
import org.cip4.jdflib.resource.process.JDFPDFXParams;
import org.cip4.jdflib.resource.process.JDFThinPDFParams;

/**
*****************************************************************************
class JDFAutoPSToPDFConversionParams : public JDFResource

*****************************************************************************
*/

public abstract class JDFAutoPSToPDFConversionParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[15];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ASCII85ENCODEPAGES, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.BINDING, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumBinding.getEnum(0), "Left");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DETECTBLEND, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.DOTHUMBNAILS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[4] = new AtrInfoTable(AttributeName.OPTIMIZE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[5] = new AtrInfoTable(AttributeName.AUTOROTATEPAGES, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumAutoRotatePages.getEnum(0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.COMPRESSPAGES, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.DEFAULTRENDERINGINTENT, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumDefaultRenderingIntent.getEnum(0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.ENDPAGE, 0x44444333, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.IMAGEMEMORY, 0x44444433, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.INITIALPAGESIZE, 0x33333331, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.INITIALRESOLUTION, 0x33333331, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.OVERPRINTMODE, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.PDFVERSION, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.STARTPAGE, 0x44444333, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.ADVANCEDPARAMS, 0x66666666);
		elemInfoTable[1] = new ElemInfoTable(ElementName.PDFXPARAMS, 0x66666611);
		elemInfoTable[2] = new ElemInfoTable(ElementName.THINPDFPARAMS, 0x66666666);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPSToPDFConversionParams
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPSToPDFConversionParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPSToPDFConversionParams
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPSToPDFConversionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPSToPDFConversionParams
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPSToPDFConversionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return  the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoPSToPDFConversionParams[  --> " + super.toString() + " ]";
	}

	/**
	 * @return  true if ok
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
	* Enumeration strings for Binding
	*/

	@SuppressWarnings("rawtypes")
	public static class EnumBinding extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumBinding(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumBinding getEnum(String enumName)
		{
			return (EnumBinding) getEnum(EnumBinding.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumBinding getEnum(int enumValue)
		{
			return (EnumBinding) getEnum(EnumBinding.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBinding.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBinding.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBinding.class);
		}

		/**  */
		public static final EnumBinding Left = new EnumBinding("Left");
		/**  */
		public static final EnumBinding Right = new EnumBinding("Right");
	}

	/**
	* Enumeration strings for AutoRotatePages
	*/

	@SuppressWarnings("rawtypes")
	public static class EnumAutoRotatePages extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumAutoRotatePages(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumAutoRotatePages getEnum(String enumName)
		{
			return (EnumAutoRotatePages) getEnum(EnumAutoRotatePages.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumAutoRotatePages getEnum(int enumValue)
		{
			return (EnumAutoRotatePages) getEnum(EnumAutoRotatePages.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumAutoRotatePages.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumAutoRotatePages.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumAutoRotatePages.class);
		}

		/**  */
		public static final EnumAutoRotatePages None = new EnumAutoRotatePages("None");
		/**  */
		public static final EnumAutoRotatePages All = new EnumAutoRotatePages("All");
		/**  */
		public static final EnumAutoRotatePages PageByPage = new EnumAutoRotatePages("PageByPage");
	}

	/**
	* Enumeration strings for DefaultRenderingIntent
	*/

	@SuppressWarnings("rawtypes")
	public static class EnumDefaultRenderingIntent extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumDefaultRenderingIntent(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumDefaultRenderingIntent getEnum(String enumName)
		{
			return (EnumDefaultRenderingIntent) getEnum(EnumDefaultRenderingIntent.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumDefaultRenderingIntent getEnum(int enumValue)
		{
			return (EnumDefaultRenderingIntent) getEnum(EnumDefaultRenderingIntent.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDefaultRenderingIntent.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDefaultRenderingIntent.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDefaultRenderingIntent.class);
		}

		/**  */
		public static final EnumDefaultRenderingIntent Default = new EnumDefaultRenderingIntent("Default");
		/**  */
		public static final EnumDefaultRenderingIntent Perceptual = new EnumDefaultRenderingIntent("Perceptual");
		/**  */
		public static final EnumDefaultRenderingIntent Saturation = new EnumDefaultRenderingIntent("Saturation");
		/**  */
		public static final EnumDefaultRenderingIntent RelativeColorimetric = new EnumDefaultRenderingIntent("RelativeColorimetric");
		/**  */
		public static final EnumDefaultRenderingIntent AbsoluteColorimetric = new EnumDefaultRenderingIntent("AbsoluteColorimetric");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute ASCII85EncodePages
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ASCII85EncodePages
	  * @param value the value to set the attribute to
	  */
	public void setASCII85EncodePages(boolean value)
	{
		setAttribute(AttributeName.ASCII85ENCODEPAGES, value, null);
	}

	/**
	  * (18) get boolean attribute ASCII85EncodePages
	  * @return boolean the value of the attribute
	  */
	public boolean getASCII85EncodePages()
	{
		return getBoolAttribute(AttributeName.ASCII85ENCODEPAGES, null, false);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Binding
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute Binding
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setBinding(EnumBinding enumVar)
	{
		setAttribute(AttributeName.BINDING, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute Binding
	  * @return the value of the attribute
	  */
	public EnumBinding getBinding()
	{
		return EnumBinding.getEnum(getAttribute(AttributeName.BINDING, null, "Left"));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute DetectBlend
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute DetectBlend
	  * @param value the value to set the attribute to
	  */
	public void setDetectBlend(boolean value)
	{
		setAttribute(AttributeName.DETECTBLEND, value, null);
	}

	/**
	  * (18) get boolean attribute DetectBlend
	  * @return boolean the value of the attribute
	  */
	public boolean getDetectBlend()
	{
		return getBoolAttribute(AttributeName.DETECTBLEND, null, true);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute DoThumbnails
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute DoThumbnails
	  * @param value the value to set the attribute to
	  */
	public void setDoThumbnails(boolean value)
	{
		setAttribute(AttributeName.DOTHUMBNAILS, value, null);
	}

	/**
	  * (18) get boolean attribute DoThumbnails
	  * @return boolean the value of the attribute
	  */
	public boolean getDoThumbnails()
	{
		return getBoolAttribute(AttributeName.DOTHUMBNAILS, null, true);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Optimize
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Optimize
	  * @param value the value to set the attribute to
	  */
	public void setOptimize(boolean value)
	{
		setAttribute(AttributeName.OPTIMIZE, value, null);
	}

	/**
	  * (18) get boolean attribute Optimize
	  * @return boolean the value of the attribute
	  */
	public boolean getOptimize()
	{
		return getBoolAttribute(AttributeName.OPTIMIZE, null, true);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute AutoRotatePages
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute AutoRotatePages
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setAutoRotatePages(EnumAutoRotatePages enumVar)
	{
		setAttribute(AttributeName.AUTOROTATEPAGES, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute AutoRotatePages
	  * @return the value of the attribute
	  */
	public EnumAutoRotatePages getAutoRotatePages()
	{
		return EnumAutoRotatePages.getEnum(getAttribute(AttributeName.AUTOROTATEPAGES, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute CompressPages
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute CompressPages
	  * @param value the value to set the attribute to
	  */
	public void setCompressPages(boolean value)
	{
		setAttribute(AttributeName.COMPRESSPAGES, value, null);
	}

	/**
	  * (18) get boolean attribute CompressPages
	  * @return boolean the value of the attribute
	  */
	public boolean getCompressPages()
	{
		return getBoolAttribute(AttributeName.COMPRESSPAGES, null, false);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute DefaultRenderingIntent
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute DefaultRenderingIntent
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setDefaultRenderingIntent(EnumDefaultRenderingIntent enumVar)
	{
		setAttribute(AttributeName.DEFAULTRENDERINGINTENT, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute DefaultRenderingIntent
	  * @return the value of the attribute
	  */
	public EnumDefaultRenderingIntent getDefaultRenderingIntent()
	{
		return EnumDefaultRenderingIntent.getEnum(getAttribute(AttributeName.DEFAULTRENDERINGINTENT, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute EndPage
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute EndPage
	  * @param value the value to set the attribute to
	  */
	public void setEndPage(int value)
	{
		setAttribute(AttributeName.ENDPAGE, value, null);
	}

	/**
	  * (15) get int attribute EndPage
	  * @return int the value of the attribute
	  */
	public int getEndPage()
	{
		return getIntAttribute(AttributeName.ENDPAGE, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ImageMemory
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ImageMemory
	  * @param value the value to set the attribute to
	  */
	public void setImageMemory(int value)
	{
		setAttribute(AttributeName.IMAGEMEMORY, value, null);
	}

	/**
	  * (15) get int attribute ImageMemory
	  * @return int the value of the attribute
	  */
	public int getImageMemory()
	{
		return getIntAttribute(AttributeName.IMAGEMEMORY, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute InitialPageSize
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute InitialPageSize
	  * @param value the value to set the attribute to
	  */
	public void setInitialPageSize(JDFXYPair value)
	{
		setAttribute(AttributeName.INITIALPAGESIZE, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute InitialPageSize
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getInitialPageSize()
	{
		final String strAttrName = getAttribute(AttributeName.INITIALPAGESIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute InitialResolution
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute InitialResolution
	  * @param value the value to set the attribute to
	  */
	public void setInitialResolution(JDFXYPair value)
	{
		setAttribute(AttributeName.INITIALRESOLUTION, value, null);
	}

	/**
	  * (20) get JDFXYPair attribute InitialResolution
	  * @return JDFXYPair the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFXYPair
	  */
	public JDFXYPair getInitialResolution()
	{
		final String strAttrName = getAttribute(AttributeName.INITIALRESOLUTION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute OverPrintMode
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute OverPrintMode
	  * @param value the value to set the attribute to
	  */
	public void setOverPrintMode(int value)
	{
		setAttribute(AttributeName.OVERPRINTMODE, value, null);
	}

	/**
	  * (15) get int attribute OverPrintMode
	  * @return int the value of the attribute
	  */
	public int getOverPrintMode()
	{
		return getIntAttribute(AttributeName.OVERPRINTMODE, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute PDFVersion
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute PDFVersion
	  * @param value the value to set the attribute to
	  */
	public void setPDFVersion(double value)
	{
		setAttribute(AttributeName.PDFVERSION, value, null);
	}

	/**
	  * (17) get double attribute PDFVersion
	  * @return double the value of the attribute
	  */
	public double getPDFVersion()
	{
		return getRealAttribute(AttributeName.PDFVERSION, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute StartPage
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute StartPage
	  * @param value the value to set the attribute to
	  */
	public void setStartPage(int value)
	{
		setAttribute(AttributeName.STARTPAGE, value, null);
	}

	/**
	  * (15) get int attribute StartPage
	  * @return int the value of the attribute
	  */
	public int getStartPage()
	{
		return getIntAttribute(AttributeName.STARTPAGE, null, 0);
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element AdvancedParams
	 * @return JDFAdvancedParams the element
	 */
	public JDFAdvancedParams getAdvancedParams()
	{
		return (JDFAdvancedParams) getElement(ElementName.ADVANCEDPARAMS, null, 0);
	}

	/** (25) getCreateAdvancedParams
	 * 
	 * @return JDFAdvancedParams the element
	 */
	public JDFAdvancedParams getCreateAdvancedParams()
	{
		return (JDFAdvancedParams) getCreateElement_KElement(ElementName.ADVANCEDPARAMS, null, 0);
	}

	/**
	 * (29) append element AdvancedParams
	 * @return JDFAdvancedParams the element
	 * @throws JDFException if the element already exists
	 */
	public JDFAdvancedParams appendAdvancedParams() throws JDFException
	{
		return (JDFAdvancedParams) appendElementN(ElementName.ADVANCEDPARAMS, 1, null);
	}

	/**
	 * (24) const get element PDFXParams
	 * @return JDFPDFXParams the element
	 */
	public JDFPDFXParams getPDFXParams()
	{
		return (JDFPDFXParams) getElement(ElementName.PDFXPARAMS, null, 0);
	}

	/** (25) getCreatePDFXParams
	 * 
	 * @return JDFPDFXParams the element
	 */
	public JDFPDFXParams getCreatePDFXParams()
	{
		return (JDFPDFXParams) getCreateElement_KElement(ElementName.PDFXPARAMS, null, 0);
	}

	/**
	 * (29) append element PDFXParams
	 * @return JDFPDFXParams the element
	 * @throws JDFException if the element already exists
	 */
	public JDFPDFXParams appendPDFXParams() throws JDFException
	{
		return (JDFPDFXParams) appendElementN(ElementName.PDFXPARAMS, 1, null);
	}

	/**
	 * (24) const get element ThinPDFParams
	 * @return JDFThinPDFParams the element
	 */
	public JDFThinPDFParams getThinPDFParams()
	{
		return (JDFThinPDFParams) getElement(ElementName.THINPDFPARAMS, null, 0);
	}

	/** (25) getCreateThinPDFParams
	 * 
	 * @return JDFThinPDFParams the element
	 */
	public JDFThinPDFParams getCreateThinPDFParams()
	{
		return (JDFThinPDFParams) getCreateElement_KElement(ElementName.THINPDFPARAMS, null, 0);
	}

	/**
	 * (29) append element ThinPDFParams
	 * @return JDFThinPDFParams the element
	 * @throws JDFException if the element already exists
	 */
	public JDFThinPDFParams appendThinPDFParams() throws JDFException
	{
		return (JDFThinPDFParams) appendElementN(ElementName.THINPDFPARAMS, 1, null);
	}

}// end namespace JDF
