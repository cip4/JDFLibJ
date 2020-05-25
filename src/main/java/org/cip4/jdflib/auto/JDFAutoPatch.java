/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFLabColor;
import org.cip4.jdflib.datatypes.JDFRGBColor;
import org.cip4.jdflib.datatypes.JDFTransferFunction;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFSeparationTint;

/**
 *****************************************************************************
 * class JDFAutoPatch : public JDFElement
 *****************************************************************************
 *
 */

public abstract class JDFAutoPatch extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CENTER, 0x31111111, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DENSITY, 0x31111111, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.LAB, 0x31111111, AttributeInfo.EnumAttributeType.LabColor, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.NEUTRALDENSITY, 0x31111111, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.PATCHUSAGE, 0x21111111, AttributeInfo.EnumAttributeType.enumeration, EnumPatchUsage.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.RGB, 0x31111111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.SIZE, 0x31111111, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.SPECTRUM, 0x31111111, AttributeInfo.EnumAttributeType.TransferFunction, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.SEPARATIONTINT, 0x31111111);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoPatch
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoPatch(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPatch
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoPatch(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoPatch
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoPatch(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for PatchUsage
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPatchUsage extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumPatchUsage(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPatchUsage getEnum(String enumName)
		{
			return (EnumPatchUsage) getEnum(EnumPatchUsage.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPatchUsage getEnum(int enumValue)
		{
			return (EnumPatchUsage) getEnum(EnumPatchUsage.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPatchUsage.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPatchUsage.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPatchUsage.class);
		}

		/**  */
		public static final EnumPatchUsage Color = new EnumPatchUsage("Color");
		/**  */
		public static final EnumPatchUsage Image = new EnumPatchUsage("Image");
		/**  */
		public static final EnumPatchUsage Technical = new EnumPatchUsage("Technical");
		/**  */
		public static final EnumPatchUsage Ignore = new EnumPatchUsage("Ignore");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute Center
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Center
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCenter(JDFXYPair value)
	{
		setAttribute(AttributeName.CENTER, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Center
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getCenter()
	{
		final String strAttrName = getAttribute(AttributeName.CENTER, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Density
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Density
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDensity(double value)
	{
		setAttribute(AttributeName.DENSITY, value, null);
	}

	/**
	 * (17) get double attribute Density
	 *
	 * @return double the value of the attribute
	 */
	public double getDensity()
	{
		return getRealAttribute(AttributeName.DENSITY, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Lab
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Lab
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLab(JDFLabColor value)
	{
		setAttribute(AttributeName.LAB, value, null);
	}

	/**
	 * (20) get JDFLabColor attribute Lab
	 *
	 * @return JDFLabColor the value of the attribute, null if a the attribute value is not a valid to create a JDFLabColor
	 */
	public JDFLabColor getLab()
	{
		final String strAttrName = getAttribute(AttributeName.LAB, null, null);
		final JDFLabColor nPlaceHolder = JDFLabColor.createLabColor(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute NeutralDensity
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute NeutralDensity
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNeutralDensity(double value)
	{
		setAttribute(AttributeName.NEUTRALDENSITY, value, null);
	}

	/**
	 * (17) get double attribute NeutralDensity
	 *
	 * @return double the value of the attribute
	 */
	public double getNeutralDensity()
	{
		return getRealAttribute(AttributeName.NEUTRALDENSITY, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute PatchUsage
	--------------------------------------------------------------------- */
	/**
	 * (5) set attribute PatchUsage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPatchUsage(EnumPatchUsage enumVar)
	{
		setAttribute(AttributeName.PATCHUSAGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute PatchUsage
	 *
	 * @return the value of the attribute
	 */
	public EnumPatchUsage getPatchUsage()
	{
		return EnumPatchUsage.getEnum(getAttribute(AttributeName.PATCHUSAGE, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute RGB
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute RGB
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRGB(JDFRGBColor value)
	{
		setAttribute(AttributeName.RGB, value, null);
	}

	/**
	 * (20) get JDFRGBColor attribute RGB
	 *
	 * @return JDFRGBColor the value of the attribute, null if a the attribute value is not a valid to create a JDFRGBColor
	 */
	public JDFRGBColor getRGB()
	{
		final String strAttrName = getAttribute(AttributeName.RGB, null, null);
		final JDFRGBColor nPlaceHolder = JDFRGBColor.createRGBColor(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Size
	--------------------------------------------------------------------- */
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

	/* ---------------------------------------------------------------------
	Methods for Attribute Spectrum
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Spectrum
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSpectrum(JDFTransferFunction value)
	{
		setAttribute(AttributeName.SPECTRUM, value, null);
	}

	/**
	 * (20) get JDFTransferFunction attribute Spectrum
	 *
	 * @return JDFTransferFunction the value of the attribute, null if a the attribute value is not a valid to create a JDFTransferFunction
	 */
	public JDFTransferFunction getSpectrum()
	{
		final String strAttrName = getAttribute(AttributeName.SPECTRUM, null, null);
		final JDFTransferFunction nPlaceHolder = JDFTransferFunction.createTransferFunction(strAttrName);
		return nPlaceHolder;
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (26) getCreateSeparationTint
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFSeparationTint the element
	 */
	public JDFSeparationTint getCreateSeparationTint(int iSkip)
	{
		return (JDFSeparationTint) getCreateElement_JDFElement(ElementName.SEPARATIONTINT, null, iSkip);
	}

	/**
	 * (27) const get element SeparationTint
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFSeparationTint the element default is getSeparationTint(0)
	 */
	public JDFSeparationTint getSeparationTint(int iSkip)
	{
		return (JDFSeparationTint) getElement(ElementName.SEPARATIONTINT, null, iSkip);
	}

	/**
	 * Get all SeparationTint from the current element
	 *
	 * @return Collection<JDFSeparationTint>, null if none are available
	 */
	public Collection<JDFSeparationTint> getAllSeparationTint()
	{
		return getChildArrayByClass(JDFSeparationTint.class, false, 0);
	}

	/**
	 * (30) append element SeparationTint
	 *
	 * @return JDFSeparationTint the element
	 */
	public JDFSeparationTint appendSeparationTint()
	{
		return (JDFSeparationTint) appendElement(ElementName.SEPARATIONTINT, null);
	}

}
