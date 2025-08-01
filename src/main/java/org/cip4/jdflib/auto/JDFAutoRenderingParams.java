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
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFAutomatedOverPrintParams;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFObjectResolution;
import org.cip4.jdflib.resource.process.JDFTIFFFormatParams;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoRenderingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoRenderingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BANDHEIGHT, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.BANDORDERING, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumBandOrdering.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.BANDWIDTH, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.COLORANTDEPTH, 0x3333333333l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.INTERLEAVED, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.MIMETYPE, 0x3333311111l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.AUTOMATEDOVERPRINTPARAMS, 0x6666666666l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.OBJECTRESOLUTION, 0x3333333333l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.MEDIA, 0x7777777761l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.TIFFFORMATPARAMS, 0x6666611111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoRenderingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoRenderingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoRenderingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoRenderingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoRenderingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoRenderingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for BandOrdering
	 */

	public enum EBandOrdering
	{
		BandMajor, ColorMajor;

		public static EBandOrdering getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EBandOrdering.class, val, null);
		}
	}

	/**
	 * Enumeration strings for BandOrdering
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumBandOrdering extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumBandOrdering(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumBandOrdering getEnum(String enumName)
		{
			return (EnumBandOrdering) getEnum(EnumBandOrdering.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumBandOrdering getEnum(int enumValue)
		{
			return (EnumBandOrdering) getEnum(EnumBandOrdering.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBandOrdering.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBandOrdering.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBandOrdering.class);
		}

		/**  */
		public static final EnumBandOrdering BandMajor = new EnumBandOrdering("BandMajor");
		/**  */
		public static final EnumBandOrdering ColorMajor = new EnumBandOrdering("ColorMajor");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BandHeight ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BandHeight
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBandHeight(int value)
	{
		setAttribute(AttributeName.BANDHEIGHT, value, null);
	}

	/**
	 * (15) get int attribute BandHeight
	 *
	 * @return int the value of the attribute
	 */
	public int getBandHeight()
	{
		return getIntAttribute(AttributeName.BANDHEIGHT, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BandOrdering
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BandOrdering
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBandOrdering(EBandOrdering enumVar)
	{
		setAttribute(AttributeName.BANDORDERING, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute BandOrdering
	 *
	 * @return the value of the attribute
	 */
	public EBandOrdering getEBandOrdering()
	{
		return EBandOrdering.getEnum(getAttribute(AttributeName.BANDORDERING, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BandOrdering
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BandOrdering
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setBandOrdering(EnumBandOrdering enumVar)
	{
		setAttribute(AttributeName.BANDORDERING, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute BandOrdering
	 *
	 * @return the value of the attribute
	 */
	public EnumBandOrdering getBandOrdering()
	{
		return EnumBandOrdering.getEnum(getAttribute(AttributeName.BANDORDERING, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BandWidth ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BandWidth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBandWidth(int value)
	{
		setAttribute(AttributeName.BANDWIDTH, value, null);
	}

	/**
	 * (15) get int attribute BandWidth
	 *
	 * @return int the value of the attribute
	 */
	public int getBandWidth()
	{
		return getIntAttribute(AttributeName.BANDWIDTH, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorantDepth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ColorantDepth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setColorantDepth(int value)
	{
		setAttribute(AttributeName.COLORANTDEPTH, value, null);
	}

	/**
	 * (15) get int attribute ColorantDepth
	 *
	 * @return int the value of the attribute
	 */
	public int getColorantDepth()
	{
		return getIntAttribute(AttributeName.COLORANTDEPTH, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Interleaved ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Interleaved
	 *
	 * @param value the value to set the attribute to
	 */
	public void setInterleaved(boolean value)
	{
		setAttribute(AttributeName.INTERLEAVED, value, null);
	}

	/**
	 * (18) get boolean attribute Interleaved
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getInterleaved()
	{
		return getBoolAttribute(AttributeName.INTERLEAVED, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MimeType ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MimeType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMimeType(String value)
	{
		setAttribute(AttributeName.MIMETYPE, value, null);
	}

	/**
	 * (23) get String attribute MimeType
	 *
	 * @return the value of the attribute
	 */
	public String getMimeType()
	{
		return getAttribute(AttributeName.MIMETYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element AutomatedOverPrintParams
	 *
	 * @return JDFAutomatedOverPrintParams the element
	 */
	public JDFAutomatedOverPrintParams getAutomatedOverPrintParams()
	{
		return (JDFAutomatedOverPrintParams) getElement(ElementName.AUTOMATEDOVERPRINTPARAMS, null, 0);
	}

	/**
	 * (25) getCreateAutomatedOverPrintParams
	 * 
	 * @return JDFAutomatedOverPrintParams the element
	 */
	public JDFAutomatedOverPrintParams getCreateAutomatedOverPrintParams()
	{
		return (JDFAutomatedOverPrintParams) getCreateElement_JDFElement(ElementName.AUTOMATEDOVERPRINTPARAMS, null, 0);
	}

	/**
	 * (29) append element AutomatedOverPrintParams
	 *
	 * @return JDFAutomatedOverPrintParams the element @ if the element already exists
	 */
	public JDFAutomatedOverPrintParams appendAutomatedOverPrintParams()
	{
		return (JDFAutomatedOverPrintParams) appendElementN(ElementName.AUTOMATEDOVERPRINTPARAMS, 1, null);
	}

	/**
	 * (24) const get element ObjectResolution
	 *
	 * @return JDFObjectResolution the element
	 */
	public JDFObjectResolution getObjectResolution()
	{
		return (JDFObjectResolution) getElement(ElementName.OBJECTRESOLUTION, null, 0);
	}

	/**
	 * (25) getCreateObjectResolution
	 * 
	 * @return JDFObjectResolution the element
	 */
	public JDFObjectResolution getCreateObjectResolution()
	{
		return (JDFObjectResolution) getCreateElement_JDFElement(ElementName.OBJECTRESOLUTION, null, 0);
	}

	/**
	 * (26) getCreateObjectResolution
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFObjectResolution the element
	 */
	public JDFObjectResolution getCreateObjectResolution(int iSkip)
	{
		return (JDFObjectResolution) getCreateElement_JDFElement(ElementName.OBJECTRESOLUTION, null, iSkip);
	}

	/**
	 * (27) const get element ObjectResolution
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFObjectResolution the element default is getObjectResolution(0)
	 */
	public JDFObjectResolution getObjectResolution(int iSkip)
	{
		return (JDFObjectResolution) getElement(ElementName.OBJECTRESOLUTION, null, iSkip);
	}

	/**
	 * Get all ObjectResolution from the current element
	 * 
	 * @return Collection<JDFObjectResolution>, null if none are available
	 */
	public Collection<JDFObjectResolution> getAllObjectResolution()
	{
		return getChildArrayByClass(JDFObjectResolution.class, false, 0);
	}

	/**
	 * (30) append element ObjectResolution
	 *
	 * @return JDFObjectResolution the element
	 */
	public JDFObjectResolution appendObjectResolution()
	{
		return (JDFObjectResolution) appendElement(ElementName.OBJECTRESOLUTION, null);
	}

	/**
	 * (24) const get element Media
	 *
	 * @return JDFMedia the element
	 */
	public JDFMedia getMedia()
	{
		return (JDFMedia) getElement(ElementName.MEDIA, null, 0);
	}

	/**
	 * (25) getCreateMedia
	 * 
	 * @return JDFMedia the element
	 */
	public JDFMedia getCreateMedia()
	{
		return (JDFMedia) getCreateElement_JDFElement(ElementName.MEDIA, null, 0);
	}

	/**
	 * (29) append element Media
	 *
	 * @return JDFMedia the element @ if the element already exists
	 */
	public JDFMedia appendMedia()
	{
		return (JDFMedia) appendElementN(ElementName.MEDIA, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refMedia(JDFMedia refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element TIFFFormatParams
	 *
	 * @return JDFTIFFFormatParams the element
	 */
	public JDFTIFFFormatParams getTIFFFormatParams()
	{
		return (JDFTIFFFormatParams) getElement(ElementName.TIFFFORMATPARAMS, null, 0);
	}

	/**
	 * (25) getCreateTIFFFormatParams
	 * 
	 * @return JDFTIFFFormatParams the element
	 */
	public JDFTIFFFormatParams getCreateTIFFFormatParams()
	{
		return (JDFTIFFFormatParams) getCreateElement_JDFElement(ElementName.TIFFFORMATPARAMS, null, 0);
	}

	/**
	 * (29) append element TIFFFormatParams
	 *
	 * @return JDFTIFFFormatParams the element @ if the element already exists
	 */
	public JDFTIFFFormatParams appendTIFFFormatParams()
	{
		return (JDFTIFFFormatParams) appendElementN(ElementName.TIFFFORMATPARAMS, 1, null);
	}

}
