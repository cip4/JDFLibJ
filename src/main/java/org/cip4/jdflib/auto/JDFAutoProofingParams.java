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
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoProofingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoProofingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DISPLAYTRAPS, 0x4444444433l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.HALFTONE, 0x4444444433l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.IMAGEVIEWINGSTRATEGY, 0x4444444433l, AttributeInfo.EnumAttributeType.string, null, "NoImages");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.MANUALFEED, 0x4444444431l, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[4] = new AtrInfoTable(AttributeName.PROOFRENDERINGINTENT, 0x4444444431l, AttributeInfo.EnumAttributeType.enumeration, EnumProofRenderingIntent.getEnum(0),
				"Perceptual");
		atrInfoTable[5] = new AtrInfoTable(AttributeName.PROOFTYPE, 0x4444444433l, AttributeInfo.EnumAttributeType.enumeration, EnumProofType.getEnum(0), "None");
		atrInfoTable[6] = new AtrInfoTable(AttributeName.COLORTYPE, 0x4444444433l, AttributeInfo.EnumAttributeType.enumeration, EnumColorType.getEnum(0), null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.RESOLUTION, 0x4444444433l, AttributeInfo.EnumAttributeType.XYPair, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.FILESPEC, 0x7777777766l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.MEDIA, 0x7777777766l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoProofingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoProofingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoProofingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoProofingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoProofingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoProofingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for ProofRenderingIntent
	 */

	public enum EProofRenderingIntent
	{
		Saturation, Perceptual, RelativeColorimetric, AbsoluteColorimetric;

		public static EProofRenderingIntent getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EProofRenderingIntent.class, val, EProofRenderingIntent.Perceptual);
		}
	}

	/**
	 * Enumeration strings for ProofRenderingIntent
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumProofRenderingIntent extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumProofRenderingIntent(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumProofRenderingIntent getEnum(String enumName)
		{
			return (EnumProofRenderingIntent) getEnum(EnumProofRenderingIntent.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumProofRenderingIntent getEnum(int enumValue)
		{
			return (EnumProofRenderingIntent) getEnum(EnumProofRenderingIntent.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumProofRenderingIntent.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumProofRenderingIntent.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumProofRenderingIntent.class);
		}

		/**  */
		public static final EnumProofRenderingIntent Saturation = new EnumProofRenderingIntent("Saturation");
		/**  */
		public static final EnumProofRenderingIntent Perceptual = new EnumProofRenderingIntent("Perceptual");
		/**  */
		public static final EnumProofRenderingIntent RelativeColorimetric = new EnumProofRenderingIntent("RelativeColorimetric");
		/**  */
		public static final EnumProofRenderingIntent AbsoluteColorimetric = new EnumProofRenderingIntent("AbsoluteColorimetric");
	}

	/**
	 * Enumeration strings for ProofType
	 */

	public enum EProofType
	{
		None, Page, Imposition;

		public static EProofType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EProofType.class, val, EProofType.None);
		}
	}

	/**
	 * Enumeration strings for ProofType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumProofType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumProofType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumProofType getEnum(String enumName)
		{
			return (EnumProofType) getEnum(EnumProofType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumProofType getEnum(int enumValue)
		{
			return (EnumProofType) getEnum(EnumProofType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumProofType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumProofType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumProofType.class);
		}

		/**  */
		public static final EnumProofType None = new EnumProofType("None");
		/**  */
		public static final EnumProofType Page = new EnumProofType("Page");
		/**  */
		public static final EnumProofType Imposition = new EnumProofType("Imposition");
	}

	/**
	 * Enumeration strings for ColorType
	 */

	public enum EColorType
	{
		Monochrome, BasicColor, MatchedColor;

		public static EColorType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EColorType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for ColorType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumColorType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumColorType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumColorType getEnum(String enumName)
		{
			return (EnumColorType) getEnum(EnumColorType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumColorType getEnum(int enumValue)
		{
			return (EnumColorType) getEnum(EnumColorType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumColorType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumColorType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumColorType.class);
		}

		/**  */
		public static final EnumColorType Monochrome = new EnumColorType("Monochrome");
		/**  */
		public static final EnumColorType BasicColor = new EnumColorType("BasicColor");
		/**  */
		public static final EnumColorType MatchedColor = new EnumColorType("MatchedColor");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DisplayTraps
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DisplayTraps
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDisplayTraps(boolean value)
	{
		setAttribute(AttributeName.DISPLAYTRAPS, value, null);
	}

	/**
	 * (18) get boolean attribute DisplayTraps
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getDisplayTraps()
	{
		return getBoolAttribute(AttributeName.DISPLAYTRAPS, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute HalfTone ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute HalfTone
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHalfTone(boolean value)
	{
		setAttribute(AttributeName.HALFTONE, value, null);
	}

	/**
	 * (18) get boolean attribute HalfTone
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getHalfTone()
	{
		return getBoolAttribute(AttributeName.HALFTONE, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ImageViewingStrategy
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ImageViewingStrategy
	 *
	 * @param value the value to set the attribute to
	 */
	public void setImageViewingStrategy(String value)
	{
		setAttribute(AttributeName.IMAGEVIEWINGSTRATEGY, value, null);
	}

	/**
	 * (23) get String attribute ImageViewingStrategy
	 *
	 * @return the value of the attribute
	 */
	public String getImageViewingStrategy()
	{
		return getAttribute(AttributeName.IMAGEVIEWINGSTRATEGY, null, "NoImages");
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ManualFeed ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ManualFeed
	 *
	 * @param value the value to set the attribute to
	 */
	public void setManualFeed(boolean value)
	{
		setAttribute(AttributeName.MANUALFEED, value, null);
	}

	/**
	 * (18) get boolean attribute ManualFeed
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getManualFeed()
	{
		return getBoolAttribute(AttributeName.MANUALFEED, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProofRenderingIntent
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ProofRenderingIntent
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setProofRenderingIntent(EProofRenderingIntent enumVar)
	{
		setAttribute(AttributeName.PROOFRENDERINGINTENT, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ProofRenderingIntent
	 *
	 * @return the value of the attribute
	 */
	public EProofRenderingIntent getEProofRenderingIntent()
	{
		return EProofRenderingIntent.getEnum(getAttribute(AttributeName.PROOFRENDERINGINTENT, null, "Perceptual"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProofRenderingIntent
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ProofRenderingIntent
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setProofRenderingIntent(EProofRenderingIntent) based on java.lang.enum instead
	 */
	@Deprecated
	public void setProofRenderingIntent(EnumProofRenderingIntent enumVar)
	{
		setAttribute(AttributeName.PROOFRENDERINGINTENT, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ProofRenderingIntent
	 *
	 * @return the value of the attribute
	 * @deprecated use EProofRenderingIntent getEProofRenderingIntent() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumProofRenderingIntent getProofRenderingIntent()
	{
		return EnumProofRenderingIntent.getEnum(getAttribute(AttributeName.PROOFRENDERINGINTENT, null, "Perceptual"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProofType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ProofType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setProofType(EProofType enumVar)
	{
		setAttribute(AttributeName.PROOFTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ProofType
	 *
	 * @return the value of the attribute
	 */
	public EProofType getEProofType()
	{
		return EProofType.getEnum(getAttribute(AttributeName.PROOFTYPE, null, "None"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProofType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ProofType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setProofType(EProofType) based on java.lang.enum instead
	 */
	@Deprecated
	public void setProofType(EnumProofType enumVar)
	{
		setAttribute(AttributeName.PROOFTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ProofType
	 *
	 * @return the value of the attribute
	 * @deprecated use EProofType getEProofType() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumProofType getProofType()
	{
		return EnumProofType.getEnum(getAttribute(AttributeName.PROOFTYPE, null, "None"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ColorType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setColorType(EColorType enumVar)
	{
		setAttribute(AttributeName.COLORTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ColorType
	 *
	 * @return the value of the attribute
	 */
	public EColorType getEColorType()
	{
		return EColorType.getEnum(getAttribute(AttributeName.COLORTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ColorType ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ColorType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setColorType(EColorType) based on java.lang.enum instead
	 */
	@Deprecated
	public void setColorType(EnumColorType enumVar)
	{
		setAttribute(AttributeName.COLORTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ColorType
	 *
	 * @return the value of the attribute
	 * @deprecated use EColorType getEColorType() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumColorType getColorType()
	{
		return EnumColorType.getEnum(getAttribute(AttributeName.COLORTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Resolution ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Resolution
	 *
	 * @param value the value to set the attribute to
	 */
	public void setResolution(JDFXYPair value)
	{
		setAttribute(AttributeName.RESOLUTION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Resolution
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getResolution()
	{
		final String strAttrName = getAttribute(AttributeName.RESOLUTION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
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

}
