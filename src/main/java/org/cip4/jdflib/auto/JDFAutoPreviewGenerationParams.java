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
import org.cip4.jdflib.resource.process.JDFImageSetterParams;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoPreviewGenerationParams : public JDFResource
 */

public abstract class JDFAutoPreviewGenerationParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ASPECTRATIO, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumAspectRatio.class, 0), "Ignore");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.PREVIEWFILETYPE, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumPreviewFileType.class, 0), "PNG");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.PREVIEWUSAGE, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumPreviewUsage.class, 0), "Separation");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.COMPENSATION, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumCompensation.class, 0), null);
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
	protected JDFAutoPreviewGenerationParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
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
	protected JDFAutoPreviewGenerationParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
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
	protected JDFAutoPreviewGenerationParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
	 * Enumeration strings for numAspectRatio
	 */

	public enum EnumAspectRatio
	{
		Ignore, CenterMax, CenterMin, Crop, Expand;

		public static EnumAspectRatio getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumAspectRatio.class, val, EnumAspectRatio.Ignore);
		}
	}

	/**
	 * Enumeration strings for numPreviewFileType
	 */

	public enum EnumPreviewFileType
	{
		PNG, CIP3Multiple, CIP3Single;

		public static EnumPreviewFileType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPreviewFileType.class, val, EnumPreviewFileType.PNG);
		}
	}

	/**
	 * Enumeration strings for numPreviewUsage
	 */

	public enum EnumPreviewUsage
	{
		Animation, Identification, Separation, SeparatedThumbNail, SeparationRaw, ThumbNail, Static3D, Viewable;

		public static EnumPreviewUsage getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPreviewUsage.class, val, EnumPreviewUsage.Separation);
		}
	}

	/**
	 * Enumeration strings for numCompensation
	 */

	public enum EnumCompensation
	{
		None, Film, Plate, Press;

		public static EnumCompensation getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumCompensation.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AspectRatio
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute AspectRatio
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setAspectRatio(final EnumAspectRatio enumVar)
	{
		setAttribute(AttributeName.ASPECTRATIO, JavaEnumUtil.getName(enumVar), null);
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PreviewFileType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PreviewFileType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPreviewFileType(final EnumPreviewFileType enumVar)
	{
		setAttribute(AttributeName.PREVIEWFILETYPE, JavaEnumUtil.getName(enumVar), null);
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PreviewUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PreviewUsage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPreviewUsage(final EnumPreviewUsage enumVar)
	{
		setAttribute(AttributeName.PREVIEWUSAGE, JavaEnumUtil.getName(enumVar), null);
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Compensation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Compensation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setCompensation(final EnumCompensation enumVar)
	{
		setAttribute(AttributeName.COMPENSATION, JavaEnumUtil.getName(enumVar), null);
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
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Resolution
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Resolution
	 *
	 * @param value the value to set the attribute to
	 */
	public void setResolution(final JDFXYPair value)
	{
		setAttribute(AttributeName.RESOLUTION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Resolution
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getResolution()
	{
		final String strAttrName = getAttribute(AttributeName.RESOLUTION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Size
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Size
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSize(final JDFXYPair value)
	{
		setAttribute(AttributeName.SIZE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Size
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getSize()
	{
		final String strAttrName = getAttribute(AttributeName.SIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
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
	 * @return JDFImageSetterParams the element
	 * @ if the element already exists
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
	public void refImageSetterParams(final JDFImageSetterParams refTarget)
	{
		refElement(refTarget);
	}

}
