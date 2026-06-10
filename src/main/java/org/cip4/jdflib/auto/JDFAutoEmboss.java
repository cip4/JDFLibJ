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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFTool;
import org.cip4.jdflib.resource.process.JDFIdentificationField;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoEmboss : public JDFElement
 */

public abstract class JDFAutoEmboss extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DIRECTION, 0x2222222221l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumDirection.class, 0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.EDGEANGLE, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.EDGESHAPE, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumEdgeShape.class, 0), "Rounded");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.EMBOSSINGTYPE, 0x2222222221l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumEmbossingType.class, 0), null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.FACE, 0x3333111111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumFace.class, 0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.HEIGHT, 0x3333333331l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.IMAGESIZE, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.LEVEL, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumLevel.class, 0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.POSITION, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPair, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.IDENTIFICATIONFIELD, 0x3333333331l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.MEDIA, 0x6666666661l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.TOOL, 0x6666666661l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoEmboss
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoEmboss(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoEmboss
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoEmboss(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoEmboss
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoEmboss(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numDirection
	 */

	public enum EnumDirection
	{
		Both, Flat, Raised, Depressed;

		public static EnumDirection getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumDirection.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numEdgeShape
	 */

	public enum EnumEdgeShape
	{
		Beveled, Rounded;

		public static EnumEdgeShape getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumEdgeShape.class, val, EnumEdgeShape.Rounded);
		}
	}

	/**
	 * Enumeration strings for numEmbossingType
	 */

	public enum EnumEmbossingType
	{
		BlindEmbossing, Braille, EmbossedFinish, FoilEmbossing, FoilStamping, RegisteredEmbossing;

		public static EnumEmbossingType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumEmbossingType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numFace
	 */

	public enum EnumFace
	{
		Top, Bottom, Left, Right, Front, Back;

		public static EnumFace getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumFace.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numLevel
	 */

	public enum EnumLevel
	{
		SingleLevel, MultiLevel, Sculpted;

		public static EnumLevel getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumLevel.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Direction
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Direction
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDirection(final EnumDirection enumVar)
	{
		setAttribute(AttributeName.DIRECTION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Direction
	 *
	 * @return the value of the attribute
	 */
	public EnumDirection getDirection()
	{
		return EnumDirection.getEnum(getAttribute(AttributeName.DIRECTION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute EdgeAngle
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute EdgeAngle
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEdgeAngle(final double value)
	{
		setAttribute(AttributeName.EDGEANGLE, value, null);
	}

	/**
	 * (17) get double attribute EdgeAngle
	 *
	 * @return double the value of the attribute
	 */
	public double getEdgeAngle()
	{
		return getRealAttribute(AttributeName.EDGEANGLE, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute EdgeShape
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute EdgeShape
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setEdgeShape(final EnumEdgeShape enumVar)
	{
		setAttribute(AttributeName.EDGESHAPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute EdgeShape
	 *
	 * @return the value of the attribute
	 */
	public EnumEdgeShape getEdgeShape()
	{
		return EnumEdgeShape.getEnum(getAttribute(AttributeName.EDGESHAPE, null, "Rounded"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute EmbossingType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute EmbossingType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setEmbossingType(final EnumEmbossingType enumVar)
	{
		setAttribute(AttributeName.EMBOSSINGTYPE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute EmbossingType
	 *
	 * @return the value of the attribute
	 */
	public EnumEmbossingType getEmbossingType()
	{
		return EnumEmbossingType.getEnum(getAttribute(AttributeName.EMBOSSINGTYPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Face
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Face
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFace(final EnumFace enumVar)
	{
		setAttribute(AttributeName.FACE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Face
	 *
	 * @return the value of the attribute
	 */
	public EnumFace getFace()
	{
		return EnumFace.getEnum(getAttribute(AttributeName.FACE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Height
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Height
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHeight(final double value)
	{
		setAttribute(AttributeName.HEIGHT, value, null);
	}

	/**
	 * (17) get double attribute Height
	 *
	 * @return double the value of the attribute
	 */
	public double getHeight()
	{
		return getRealAttribute(AttributeName.HEIGHT, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ImageSize
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ImageSize
	 *
	 * @param value the value to set the attribute to
	 */
	public void setImageSize(final JDFXYPair value)
	{
		setAttribute(AttributeName.IMAGESIZE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute ImageSize
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getImageSize()
	{
		final String strAttrName = getAttribute(AttributeName.IMAGESIZE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Level
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Level
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setLevel(final EnumLevel enumVar)
	{
		setAttribute(AttributeName.LEVEL, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Level
	 *
	 * @return the value of the attribute
	 */
	public EnumLevel getLevel()
	{
		return EnumLevel.getEnum(getAttribute(AttributeName.LEVEL, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Position
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Position
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPosition(final JDFXYPair value)
	{
		setAttribute(AttributeName.POSITION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Position
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getPosition()
	{
		final String strAttrName = getAttribute(AttributeName.POSITION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element IdentificationField
	 *
	 * @return JDFIdentificationField the element
	 */
	public JDFIdentificationField getIdentificationField()
	{
		return (JDFIdentificationField) getElement(ElementName.IDENTIFICATIONFIELD, null, 0);
	}

	/**
	 * (25) getCreateIdentificationField
	 * 
	 * @return JDFIdentificationField the element
	 */
	public JDFIdentificationField getCreateIdentificationField()
	{
		return (JDFIdentificationField) getCreateElement_JDFElement(ElementName.IDENTIFICATIONFIELD, null, 0);
	}

	/**
	 * (26) getCreateIdentificationField
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFIdentificationField the element
	 */
	public JDFIdentificationField getCreateIdentificationField(final int iSkip)
	{
		return (JDFIdentificationField) getCreateElement_JDFElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
	}

	/**
	 * (27) const get element IdentificationField
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFIdentificationField the element
	 *         default is getIdentificationField(0)
	 */
	public JDFIdentificationField getIdentificationField(final int iSkip)
	{
		return (JDFIdentificationField) getElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
	}

	/**
	 * Get all IdentificationField from the current element
	 * 
	 * @return Collection<JDFIdentificationField>, null if none are available
	 */
	public Collection<JDFIdentificationField> getAllIdentificationField()
	{
		return getChildArrayByClass(JDFIdentificationField.class, false, 0);
	}

	/**
	 * (30) append element IdentificationField
	 *
	 * @return JDFIdentificationField the element
	 */
	public JDFIdentificationField appendIdentificationField()
	{
		return (JDFIdentificationField) appendElement(ElementName.IDENTIFICATIONFIELD, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refIdentificationField(final JDFIdentificationField refTarget)
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
	 * @return JDFMedia the element
	 * @ if the element already exists
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
	public void refMedia(final JDFMedia refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element Tool
	 *
	 * @return JDFTool the element
	 */
	public JDFTool getTool()
	{
		return (JDFTool) getElement(ElementName.TOOL, null, 0);
	}

	/**
	 * (25) getCreateTool
	 * 
	 * @return JDFTool the element
	 */
	public JDFTool getCreateTool()
	{
		return (JDFTool) getCreateElement_JDFElement(ElementName.TOOL, null, 0);
	}

	/**
	 * (29) append element Tool
	 *
	 * @return JDFTool the element
	 * @ if the element already exists
	 */
	public JDFTool appendTool()
	{
		return (JDFTool) appendElementN(ElementName.TOOL, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refTool(final JDFTool refTarget)
	{
		refElement(refTarget);
	}

}
