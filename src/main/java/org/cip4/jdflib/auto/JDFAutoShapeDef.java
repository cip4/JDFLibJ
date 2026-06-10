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
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFShapeElement;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRuleLength;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoShapeDef : public JDFResource
 */

public abstract class JDFAutoShapeDef extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.AREA, 0x3333331111l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CUTBOX, 0x3333331111l, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DIMENSIONS, 0x3333331111l, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.FLATDIMENSIONS, 0x3333311111l, AttributeInfo.EnumAttributeType.shape, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.FLUTEDIRECTION, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumFluteDirection.class, 0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.GRAINDIRECTION, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumGrainDirection.class, 0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MEDIASIDE, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumMediaSide.class, 0), null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.RESOURCEWEIGHT, 0x3333331111l, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[6];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COLORPOOL, 0x6666661111l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.CUTLINES, 0x6666661111l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.FILESPEC, 0x3333331111l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.MEDIA, 0x6666661111l);
		elemInfoTable[4] = new ElemInfoTable(ElementName.RULELENGTH, 0x3333331111l);
		elemInfoTable[5] = new ElemInfoTable(ElementName.SHAPE, 0x6666661111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoShapeDef
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoShapeDef(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoShapeDef
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoShapeDef(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoShapeDef
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoShapeDef(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
	 * Enumeration strings for numFluteDirection
	 */

	public enum EnumFluteDirection
	{
		Any, Both, ShortEdge, LongEdge, SameDirection, XDirection, YDirection;

		public static EnumFluteDirection getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumFluteDirection.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numGrainDirection
	 */

	public enum EnumGrainDirection
	{
		Any, Both, ShortEdge, LongEdge, SameDirection, XDirection, YDirection;

		public static EnumGrainDirection getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumGrainDirection.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numMediaSide
	 */

	public enum EnumMediaSide
	{
		Front, Back, Both;

		public static EnumMediaSide getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumMediaSide.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Area
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Area
	 *
	 * @param value the value to set the attribute to
	 */
	public void setArea(final double value)
	{
		setAttribute(AttributeName.AREA, value, null);
	}

	/**
	 * (17) get double attribute Area
	 *
	 * @return double the value of the attribute
	 */
	public double getArea()
	{
		return getRealAttribute(AttributeName.AREA, null, 0.0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CutBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CutBox
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCutBox(final JDFRectangle value)
	{
		setAttribute(AttributeName.CUTBOX, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute CutBox
	 *
	 * @return JDFRectangle the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getCutBox()
	{
		final String strAttrName = getAttribute(AttributeName.CUTBOX, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Dimensions
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Dimensions
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDimensions(final JDFShape value)
	{
		setAttribute(AttributeName.DIMENSIONS, value, null);
	}

	/**
	 * (20) get JDFShape attribute Dimensions
	 *
	 * @return JDFShape the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFShape
	 */
	public JDFShape getDimensions()
	{
		final String strAttrName = getAttribute(AttributeName.DIMENSIONS, null, null);
		final JDFShape nPlaceHolder = JDFShape.createShape(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FlatDimensions
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FlatDimensions
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFlatDimensions(final JDFShape value)
	{
		setAttribute(AttributeName.FLATDIMENSIONS, value, null);
	}

	/**
	 * (20) get JDFShape attribute FlatDimensions
	 *
	 * @return JDFShape the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFShape
	 */
	public JDFShape getFlatDimensions()
	{
		final String strAttrName = getAttribute(AttributeName.FLATDIMENSIONS, null, null);
		final JDFShape nPlaceHolder = JDFShape.createShape(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FluteDirection
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FluteDirection
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFluteDirection(final EnumFluteDirection enumVar)
	{
		setAttribute(AttributeName.FLUTEDIRECTION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute FluteDirection
	 *
	 * @return the value of the attribute
	 */
	public EnumFluteDirection getFluteDirection()
	{
		return EnumFluteDirection.getEnum(getAttribute(AttributeName.FLUTEDIRECTION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute GrainDirection
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute GrainDirection
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setGrainDirection(final EnumGrainDirection enumVar)
	{
		setAttribute(AttributeName.GRAINDIRECTION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute GrainDirection
	 *
	 * @return the value of the attribute
	 */
	public EnumGrainDirection getGrainDirection()
	{
		return EnumGrainDirection.getEnum(getAttribute(AttributeName.GRAINDIRECTION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MediaSide
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MediaSide
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMediaSide(final EnumMediaSide enumVar)
	{
		setAttribute(AttributeName.MEDIASIDE, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute MediaSide
	 *
	 * @return the value of the attribute
	 */
	public EnumMediaSide getMediaSide()
	{
		return EnumMediaSide.getEnum(getAttribute(AttributeName.MEDIASIDE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ResourceWeight
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ResourceWeight
	 *
	 * @param value the value to set the attribute to
	 */
	@Override
	public void setResourceWeight(final double value)
	{
		setAttribute(AttributeName.RESOURCEWEIGHT, value, null);
	}

	/**
	 * (17) get double attribute ResourceWeight
	 *
	 * @return double the value of the attribute
	 */
	@Override
	public double getResourceWeight()
	{
		return getRealAttribute(AttributeName.RESOURCEWEIGHT, null, 0.0);
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element ColorPool
	 *
	 * @return JDFColorPool the element
	 */
	public JDFColorPool getColorPool()
	{
		return (JDFColorPool) getElement(ElementName.COLORPOOL, null, 0);
	}

	/**
	 * (25) getCreateColorPool
	 * 
	 * @return JDFColorPool the element
	 */
	public JDFColorPool getCreateColorPool()
	{
		return (JDFColorPool) getCreateElement_JDFElement(ElementName.COLORPOOL, null, 0);
	}

	/**
	 * (29) append element ColorPool
	 *
	 * @return JDFColorPool the element
	 * @ if the element already exists
	 */
	public JDFColorPool appendColorPool()
	{
		return (JDFColorPool) appendElementN(ElementName.COLORPOOL, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refColorPool(final JDFColorPool refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element CutLines
	 *
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getCutLines()
	{
		return (JDFSeparationList) getElement(ElementName.CUTLINES, null, 0);
	}

	/**
	 * (25) getCreateCutLines
	 * 
	 * @return JDFSeparationList the element
	 */
	public JDFSeparationList getCreateCutLines()
	{
		return (JDFSeparationList) getCreateElement_JDFElement(ElementName.CUTLINES, null, 0);
	}

	/**
	 * (29) append element CutLines
	 *
	 * @return JDFSeparationList the element
	 * @ if the element already exists
	 */
	public JDFSeparationList appendCutLines()
	{
		return (JDFSeparationList) appendElementN(ElementName.CUTLINES, 1, null);
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
	 * (24) const get element RuleLength
	 *
	 * @return JDFRuleLength the element
	 */
	public JDFRuleLength getRuleLength()
	{
		return (JDFRuleLength) getElement(ElementName.RULELENGTH, null, 0);
	}

	/**
	 * (25) getCreateRuleLength
	 * 
	 * @return JDFRuleLength the element
	 */
	public JDFRuleLength getCreateRuleLength()
	{
		return (JDFRuleLength) getCreateElement_JDFElement(ElementName.RULELENGTH, null, 0);
	}

	/**
	 * (26) getCreateRuleLength
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFRuleLength the element
	 */
	public JDFRuleLength getCreateRuleLength(final int iSkip)
	{
		return (JDFRuleLength) getCreateElement_JDFElement(ElementName.RULELENGTH, null, iSkip);
	}

	/**
	 * (27) const get element RuleLength
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFRuleLength the element
	 *         default is getRuleLength(0)
	 */
	public JDFRuleLength getRuleLength(final int iSkip)
	{
		return (JDFRuleLength) getElement(ElementName.RULELENGTH, null, iSkip);
	}

	/**
	 * Get all RuleLength from the current element
	 * 
	 * @return Collection<JDFRuleLength>, null if none are available
	 */
	public Collection<JDFRuleLength> getAllRuleLength()
	{
		return getChildArrayByClass(JDFRuleLength.class, false, 0);
	}

	/**
	 * (30) append element RuleLength
	 *
	 * @return JDFRuleLength the element
	 */
	public JDFRuleLength appendRuleLength()
	{
		return (JDFRuleLength) appendElement(ElementName.RULELENGTH, null);
	}

	/**
	 * (24) const get element Shape
	 *
	 * @return JDFShapeElement the element
	 */
	public JDFShapeElement getShape()
	{
		return (JDFShapeElement) getElement(ElementName.SHAPE, null, 0);
	}

	/**
	 * (25) getCreateShape
	 * 
	 * @return JDFShapeElement the element
	 */
	public JDFShapeElement getCreateShape()
	{
		return (JDFShapeElement) getCreateElement_JDFElement(ElementName.SHAPE, null, 0);
	}

	/**
	 * (29) append element Shape
	 *
	 * @return JDFShapeElement the element
	 * @ if the element already exists
	 */
	public JDFShapeElement appendShape()
	{
		return (JDFShapeElement) appendElementN(ElementName.SHAPE, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refShape(final JDFShapeElement refTarget)
	{
		refElement(refTarget);
	}

}
