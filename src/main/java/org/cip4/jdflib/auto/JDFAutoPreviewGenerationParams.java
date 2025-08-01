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
import org.cip4.jdflib.resource.process.JDFImageSetterParams;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoPreviewGenerationParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoPreviewGenerationParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ASPECTRATIO, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumAspectRatio.getEnum(0), "Ignore");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.PREVIEWFILETYPE, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumPreviewFileType.getEnum(0), "PNG");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.PREVIEWUSAGE, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumPreviewUsage.getEnum(0), "Separation");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.COMPENSATION, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumCompensation.getEnum(0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.RESOLUTION, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.SIZE, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.IMAGESETTERPARAMS, 0x6666666661l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPreviewGenerationParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPreviewGenerationParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPreviewGenerationParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPreviewGenerationParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPreviewGenerationParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPreviewGenerationParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for AspectRatio
	 */

	public enum EAspectRatio
	{
		Ignore, CenterMax, CenterMin, Crop, Expand;

		public static EAspectRatio getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EAspectRatio.class, val, EAspectRatio.Ignore);
		}
	}

	/**
	 * Enumeration strings for AspectRatio
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumAspectRatio extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumAspectRatio(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumAspectRatio getEnum(String enumName)
		{
			return (EnumAspectRatio) getEnum(EnumAspectRatio.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumAspectRatio getEnum(int enumValue)
		{
			return (EnumAspectRatio) getEnum(EnumAspectRatio.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumAspectRatio.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumAspectRatio.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumAspectRatio.class);
		}

		/**  */
		public static final EnumAspectRatio Ignore = new EnumAspectRatio("Ignore");
		/**  */
		public static final EnumAspectRatio CenterMax = new EnumAspectRatio("CenterMax");
		/**  */
		public static final EnumAspectRatio CenterMin = new EnumAspectRatio("CenterMin");
		/**  */
		public static final EnumAspectRatio Crop = new EnumAspectRatio("Crop");
		/**  */
		public static final EnumAspectRatio Expand = new EnumAspectRatio("Expand");
	}

	/**
	 * Enumeration strings for PreviewFileType
	 */

	public enum EPreviewFileType
	{
		PNG, CIP3Multiple, CIP3Single;

		public static EPreviewFileType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EPreviewFileType.class, val, EPreviewFileType.PNG);
		}
	}

	/**
	 * Enumeration strings for PreviewFileType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPreviewFileType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPreviewFileType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPreviewFileType getEnum(String enumName)
		{
			return (EnumPreviewFileType) getEnum(EnumPreviewFileType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPreviewFileType getEnum(int enumValue)
		{
			return (EnumPreviewFileType) getEnum(EnumPreviewFileType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPreviewFileType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPreviewFileType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPreviewFileType.class);
		}

		/**  */
		public static final EnumPreviewFileType PNG = new EnumPreviewFileType("PNG");
		/**  */
		public static final EnumPreviewFileType CIP3Multiple = new EnumPreviewFileType("CIP3Multiple");
		/**  */
		public static final EnumPreviewFileType CIP3Single = new EnumPreviewFileType("CIP3Single");
	}

	/**
	 * Enumeration strings for PreviewUsage
	 */

	public enum EPreviewUsage
	{
		Animation, Identification, Separation, SeparatedThumbNail, SeparationRaw, ThumbNail, Static3D, Viewable;

		public static EPreviewUsage getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EPreviewUsage.class, val, EPreviewUsage.Separation);
		}
	}

	/**
	 * Enumeration strings for PreviewUsage
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPreviewUsage extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPreviewUsage(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPreviewUsage getEnum(String enumName)
		{
			return (EnumPreviewUsage) getEnum(EnumPreviewUsage.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPreviewUsage getEnum(int enumValue)
		{
			return (EnumPreviewUsage) getEnum(EnumPreviewUsage.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPreviewUsage.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPreviewUsage.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPreviewUsage.class);
		}

		/**  */
		public static final EnumPreviewUsage Animation = new EnumPreviewUsage("Animation");
		/**  */
		public static final EnumPreviewUsage Identification = new EnumPreviewUsage("Identification");
		/**  */
		public static final EnumPreviewUsage Separation = new EnumPreviewUsage("Separation");
		/**  */
		public static final EnumPreviewUsage SeparatedThumbNail = new EnumPreviewUsage("SeparatedThumbNail");
		/**  */
		public static final EnumPreviewUsage SeparationRaw = new EnumPreviewUsage("SeparationRaw");
		/**  */
		public static final EnumPreviewUsage ThumbNail = new EnumPreviewUsage("ThumbNail");
		/**  */
		public static final EnumPreviewUsage Static3D = new EnumPreviewUsage("Static3D");
		/**  */
		public static final EnumPreviewUsage Viewable = new EnumPreviewUsage("Viewable");
	}

	/**
	 * Enumeration strings for Compensation
	 */

	public enum ECompensation
	{
		None, Film, Plate, Press;

		public static ECompensation getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ECompensation.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Compensation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumCompensation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumCompensation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumCompensation getEnum(String enumName)
		{
			return (EnumCompensation) getEnum(EnumCompensation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumCompensation getEnum(int enumValue)
		{
			return (EnumCompensation) getEnum(EnumCompensation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumCompensation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumCompensation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumCompensation.class);
		}

		/**  */
		public static final EnumCompensation None = new EnumCompensation("None");
		/**  */
		public static final EnumCompensation Film = new EnumCompensation("Film");
		/**  */
		public static final EnumCompensation Plate = new EnumCompensation("Plate");
		/**  */
		public static final EnumCompensation Press = new EnumCompensation("Press");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AspectRatio ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute AspectRatio
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setAspectRatio(EAspectRatio enumVar)
	{
		setAttribute(AttributeName.ASPECTRATIO, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute AspectRatio
	 *
	 * @return the value of the attribute
	 */
	public EAspectRatio getEAspectRatio()
	{
		return EAspectRatio.getEnum(getAttribute(AttributeName.ASPECTRATIO, null, "Ignore"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute AspectRatio ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute AspectRatio
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setAspectRatio(EnumAspectRatio enumVar)
	{
		setAttribute(AttributeName.ASPECTRATIO, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute AspectRatio
	 *
	 * @return the value of the attribute
	 */
	public EnumAspectRatio getAspectRatio()
	{
		return EnumAspectRatio.getEnum(getAttribute(AttributeName.ASPECTRATIO, null, "Ignore"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PreviewFileType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PreviewFileType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPreviewFileType(EPreviewFileType enumVar)
	{
		setAttribute(AttributeName.PREVIEWFILETYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute PreviewFileType
	 *
	 * @return the value of the attribute
	 */
	public EPreviewFileType getEPreviewFileType()
	{
		return EPreviewFileType.getEnum(getAttribute(AttributeName.PREVIEWFILETYPE, null, "PNG"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PreviewFileType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PreviewFileType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setPreviewFileType(EnumPreviewFileType enumVar)
	{
		setAttribute(AttributeName.PREVIEWFILETYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute PreviewFileType
	 *
	 * @return the value of the attribute
	 */
	public EnumPreviewFileType getPreviewFileType()
	{
		return EnumPreviewFileType.getEnum(getAttribute(AttributeName.PREVIEWFILETYPE, null, "PNG"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PreviewUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PreviewUsage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPreviewUsage(EPreviewUsage enumVar)
	{
		setAttribute(AttributeName.PREVIEWUSAGE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute PreviewUsage
	 *
	 * @return the value of the attribute
	 */
	public EPreviewUsage getEPreviewUsage()
	{
		return EPreviewUsage.getEnum(getAttribute(AttributeName.PREVIEWUSAGE, null, "Separation"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PreviewUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PreviewUsage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setPreviewUsage(EnumPreviewUsage enumVar)
	{
		setAttribute(AttributeName.PREVIEWUSAGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute PreviewUsage
	 *
	 * @return the value of the attribute
	 */
	public EnumPreviewUsage getPreviewUsage()
	{
		return EnumPreviewUsage.getEnum(getAttribute(AttributeName.PREVIEWUSAGE, null, "Separation"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Compensation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Compensation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setCompensation(ECompensation enumVar)
	{
		setAttribute(AttributeName.COMPENSATION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Compensation
	 *
	 * @return the value of the attribute
	 */
	public ECompensation getECompensation()
	{
		return ECompensation.getEnum(getAttribute(AttributeName.COMPENSATION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Compensation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Compensation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setCompensation(EnumCompensation enumVar)
	{
		setAttribute(AttributeName.COMPENSATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Compensation
	 *
	 * @return the value of the attribute
	 */
	public EnumCompensation getCompensation()
	{
		return EnumCompensation.getEnum(getAttribute(AttributeName.COMPENSATION, null, null));
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
	 * --------------------------------------------------------------------- Methods for Attribute Size ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Size
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSize(JDFXYPair value)
	{
		setAttribute(AttributeName.SIZE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Size
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getSize()
	{
		final String strAttrName = getAttribute(AttributeName.SIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element ImageSetterParams
	 *
	 * @return JDFImageSetterParams the element
	 */
	public JDFImageSetterParams getImageSetterParams()
	{
		return (JDFImageSetterParams) getElement(ElementName.IMAGESETTERPARAMS, null, 0);
	}

	/**
	 * (25) getCreateImageSetterParams
	 * 
	 * @return JDFImageSetterParams the element
	 */
	public JDFImageSetterParams getCreateImageSetterParams()
	{
		return (JDFImageSetterParams) getCreateElement_JDFElement(ElementName.IMAGESETTERPARAMS, null, 0);
	}

	/**
	 * (29) append element ImageSetterParams
	 *
	 * @return JDFImageSetterParams the element @ if the element already exists
	 */
	public JDFImageSetterParams appendImageSetterParams()
	{
		return (JDFImageSetterParams) appendElementN(ElementName.IMAGESETTERPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refImageSetterParams(JDFImageSetterParams refTarget)
	{
		refElement(refTarget);
	}

}
