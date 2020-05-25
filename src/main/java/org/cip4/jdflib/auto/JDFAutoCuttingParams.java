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
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFCutBlock;
import org.cip4.jdflib.resource.process.postpress.JDFCut;
import org.cip4.jdflib.resource.process.postpress.JDFCutMark;

/**
 *****************************************************************************
 * class JDFAutoCuttingParams : public JDFResource
 *****************************************************************************
 *
 */

public abstract class JDFAutoCuttingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.NUPSEPARATION, 0x33331111, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.SHEETLAY, 0x33311111, AttributeInfo.EnumAttributeType.enumeration, EnumSheetLay.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.CUTBLOCK, 0x33333331);
		elemInfoTable[1] = new ElemInfoTable(ElementName.CUTMARK, 0x33333331);
		elemInfoTable[2] = new ElemInfoTable(ElementName.CUT, 0x33333331);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoCuttingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoCuttingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCuttingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoCuttingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoCuttingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoCuttingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for SheetLay
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSheetLay extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSheetLay(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumSheetLay getEnum(String enumName)
		{
			return (EnumSheetLay) getEnum(EnumSheetLay.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumSheetLay getEnum(int enumValue)
		{
			return (EnumSheetLay) getEnum(EnumSheetLay.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumSheetLay.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumSheetLay.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumSheetLay.class);
		}

		/**  */
		public static final EnumSheetLay Left = new EnumSheetLay("Left");
		/**  */
		public static final EnumSheetLay Right = new EnumSheetLay("Right");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute NUpSeparation
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute NUpSeparation
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNUpSeparation(JDFXYPair value)
	{
		setAttribute(AttributeName.NUPSEPARATION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute NUpSeparation
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getNUpSeparation()
	{
		final String strAttrName = getAttribute(AttributeName.NUPSEPARATION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute SheetLay
	--------------------------------------------------------------------- */
	/**
	 * (5) set attribute SheetLay
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSheetLay(EnumSheetLay enumVar)
	{
		setAttribute(AttributeName.SHEETLAY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute SheetLay
	 *
	 * @return the value of the attribute
	 */
	public EnumSheetLay getSheetLay()
	{
		return EnumSheetLay.getEnum(getAttribute(AttributeName.SHEETLAY, null, null));
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (26) getCreateCutBlock
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCutBlock the element
	 */
	public JDFCutBlock getCreateCutBlock(int iSkip)
	{
		return (JDFCutBlock) getCreateElement_JDFElement(ElementName.CUTBLOCK, null, iSkip);
	}

	/**
	 * (27) const get element CutBlock
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCutBlock the element default is getCutBlock(0)
	 */
	public JDFCutBlock getCutBlock(int iSkip)
	{
		return (JDFCutBlock) getElement(ElementName.CUTBLOCK, null, iSkip);
	}

	/**
	 * Get all CutBlock from the current element
	 *
	 * @return Collection<JDFCutBlock>, null if none are available
	 */
	public Collection<JDFCutBlock> getAllCutBlock()
	{
		return getChildArrayByClass(JDFCutBlock.class, false, 0);
	}

	/**
	 * (30) append element CutBlock
	 *
	 * @return JDFCutBlock the element
	 */
	public JDFCutBlock appendCutBlock()
	{
		return (JDFCutBlock) appendElement(ElementName.CUTBLOCK, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refCutBlock(JDFCutBlock refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (26) getCreateCutMark
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCutMark the element
	 */
	public JDFCutMark getCreateCutMark(int iSkip)
	{
		return (JDFCutMark) getCreateElement_JDFElement(ElementName.CUTMARK, null, iSkip);
	}

	/**
	 * (27) const get element CutMark
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCutMark the element default is getCutMark(0)
	 */
	public JDFCutMark getCutMark(int iSkip)
	{
		return (JDFCutMark) getElement(ElementName.CUTMARK, null, iSkip);
	}

	/**
	 * Get all CutMark from the current element
	 *
	 * @return Collection<JDFCutMark>, null if none are available
	 */
	public Collection<JDFCutMark> getAllCutMark()
	{
		return getChildArrayByClass(JDFCutMark.class, false, 0);
	}

	/**
	 * (30) append element CutMark
	 *
	 * @return JDFCutMark the element
	 */
	public JDFCutMark appendCutMark()
	{
		return (JDFCutMark) appendElement(ElementName.CUTMARK, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refCutMark(JDFCutMark refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (26) getCreateCut
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCut the element
	 */
	public JDFCut getCreateCut(int iSkip)
	{
		return (JDFCut) getCreateElement_JDFElement(ElementName.CUT, null, iSkip);
	}

	/**
	 * (27) const get element Cut
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFCut the element default is getCut(0)
	 */
	public JDFCut getCut(int iSkip)
	{
		return (JDFCut) getElement(ElementName.CUT, null, iSkip);
	}

	/**
	 * Get all Cut from the current element
	 *
	 * @return Collection<JDFCut>, null if none are available
	 */
	public Collection<JDFCut> getAllCut()
	{
		return getChildArrayByClass(JDFCut.class, false, 0);
	}

	/**
	 * (30) append element Cut
	 *
	 * @return JDFCut the element
	 */
	public JDFCut appendCut()
	{
		return (JDFCut) appendElement(ElementName.CUT, null);
	}

}
