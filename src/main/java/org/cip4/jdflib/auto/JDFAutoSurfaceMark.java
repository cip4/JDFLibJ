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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.resource.JDFScavengerArea;
import org.cip4.jdflib.resource.process.JDFColorControlStrip;
import org.cip4.jdflib.resource.process.JDFRegisterMark;
import org.cip4.jdflib.resource.process.postpress.JDFCutMark;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoSurfaceMark : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoSurfaceMark extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.SURFACE, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration, EnumFace.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COLORCONTROLSTRIP, 0x3333333333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.CUTMARK, 0x3333333333l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.REGISTERMARK, 0x3333333333l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.SCAVENGERAREA, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoSurfaceMark
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoSurfaceMark(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSurfaceMark
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoSurfaceMark(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSurfaceMark
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoSurfaceMark(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for Face
	 */

	public enum EFace
	{
		Top, Bottom, Left, Right, Front, Back;

		public static EFace getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EFace.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Face
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumFace extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumFace(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumFace getEnum(final String enumName)
		{
			return (EnumFace) getEnum(EnumFace.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumFace getEnum(final int enumValue)
		{
			return (EnumFace) getEnum(EnumFace.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumFace.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumFace.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumFace.class);
		}

		/**  */
		public static final EnumFace Top = new EnumFace("Top");
		/**  */
		public static final EnumFace Bottom = new EnumFace("Bottom");
		/**  */
		public static final EnumFace Left = new EnumFace("Left");
		/**  */
		public static final EnumFace Right = new EnumFace("Right");
		/**  */
		public static final EnumFace Front = new EnumFace("Front");
		/**  */
		public static final EnumFace Back = new EnumFace("Back");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Surface ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Surface
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSurface(final EFace enumVar)
	{
		setAttribute(AttributeName.SURFACE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Surface
	 * 
	 * @return the value of the attribute
	 */
	public EFace getESurface()
	{
		return EFace.getEnum(getAttribute(AttributeName.SURFACE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Surface ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Surface
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setSurface(final EnumFace enumVar)
	{
		setAttribute(AttributeName.SURFACE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Surface
	 * 
	 * @return the value of the attribute
	 */
	public EnumFace getSurface()
	{
		return EnumFace.getEnum(getAttribute(AttributeName.SURFACE, null, null));
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element ColorControlStrip
	 * 
	 * @return JDFColorControlStrip the element
	 */
	public JDFColorControlStrip getColorControlStrip()
	{
		return (JDFColorControlStrip) getElement(ElementName.COLORCONTROLSTRIP, null, 0);
	}

	/**
	 * (25) getCreateColorControlStrip
	 * 
	 * @return JDFColorControlStrip the element
	 */
	public JDFColorControlStrip getCreateColorControlStrip()
	{
		return (JDFColorControlStrip) getCreateElement_JDFElement(ElementName.COLORCONTROLSTRIP, null, 0);
	}

	/**
	 * (26) getCreateColorControlStrip
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFColorControlStrip the element
	 */
	public JDFColorControlStrip getCreateColorControlStrip(final int iSkip)
	{
		return (JDFColorControlStrip) getCreateElement_JDFElement(ElementName.COLORCONTROLSTRIP, null, iSkip);
	}

	/**
	 * (27) const get element ColorControlStrip
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFColorControlStrip the element default is getColorControlStrip(0)
	 */
	public JDFColorControlStrip getColorControlStrip(final int iSkip)
	{
		return (JDFColorControlStrip) getElement(ElementName.COLORCONTROLSTRIP, null, iSkip);
	}

	/**
	 * Get all ColorControlStrip from the current element
	 * 
	 * @return Collection<JDFColorControlStrip>, null if none are available
	 */
	public Collection<JDFColorControlStrip> getAllColorControlStrip()
	{
		return getChildArrayByClass(JDFColorControlStrip.class, false, 0);
	}

	/**
	 * (30) append element ColorControlStrip
	 * 
	 * @return JDFColorControlStrip the element
	 */
	public JDFColorControlStrip appendColorControlStrip()
	{
		return (JDFColorControlStrip) appendElement(ElementName.COLORCONTROLSTRIP, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refColorControlStrip(final JDFColorControlStrip refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element CutMark
	 * 
	 * @return JDFCutMark the element
	 */
	public JDFCutMark getCutMark()
	{
		return (JDFCutMark) getElement(ElementName.CUTMARK, null, 0);
	}

	/**
	 * (25) getCreateCutMark
	 * 
	 * @return JDFCutMark the element
	 */
	public JDFCutMark getCreateCutMark()
	{
		return (JDFCutMark) getCreateElement_JDFElement(ElementName.CUTMARK, null, 0);
	}

	/**
	 * (26) getCreateCutMark
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFCutMark the element
	 */
	public JDFCutMark getCreateCutMark(final int iSkip)
	{
		return (JDFCutMark) getCreateElement_JDFElement(ElementName.CUTMARK, null, iSkip);
	}

	/**
	 * (27) const get element CutMark
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFCutMark the element default is getCutMark(0)
	 */
	public JDFCutMark getCutMark(final int iSkip)
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
	public void refCutMark(final JDFCutMark refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element RegisterMark
	 * 
	 * @return JDFRegisterMark the element
	 */
	public JDFRegisterMark getRegisterMark()
	{
		return (JDFRegisterMark) getElement(ElementName.REGISTERMARK, null, 0);
	}

	/**
	 * (25) getCreateRegisterMark
	 * 
	 * @return JDFRegisterMark the element
	 */
	public JDFRegisterMark getCreateRegisterMark()
	{
		return (JDFRegisterMark) getCreateElement_JDFElement(ElementName.REGISTERMARK, null, 0);
	}

	/**
	 * (26) getCreateRegisterMark
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFRegisterMark the element
	 */
	public JDFRegisterMark getCreateRegisterMark(final int iSkip)
	{
		return (JDFRegisterMark) getCreateElement_JDFElement(ElementName.REGISTERMARK, null, iSkip);
	}

	/**
	 * (27) const get element RegisterMark
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFRegisterMark the element default is getRegisterMark(0)
	 */
	public JDFRegisterMark getRegisterMark(final int iSkip)
	{
		return (JDFRegisterMark) getElement(ElementName.REGISTERMARK, null, iSkip);
	}

	/**
	 * Get all RegisterMark from the current element
	 * 
	 * @return Collection<JDFRegisterMark>, null if none are available
	 */
	public Collection<JDFRegisterMark> getAllRegisterMark()
	{
		return getChildArrayByClass(JDFRegisterMark.class, false, 0);
	}

	/**
	 * (30) append element RegisterMark
	 * 
	 * @return JDFRegisterMark the element
	 */
	public JDFRegisterMark appendRegisterMark()
	{
		return (JDFRegisterMark) appendElement(ElementName.REGISTERMARK, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refRegisterMark(final JDFRegisterMark refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (24) const get element ScavengerArea
	 * 
	 * @return JDFScavengerArea the element
	 */
	public JDFScavengerArea getScavengerArea()
	{
		return (JDFScavengerArea) getElement(ElementName.SCAVENGERAREA, null, 0);
	}

	/**
	 * (25) getCreateScavengerArea
	 * 
	 * @return JDFScavengerArea the element
	 */
	public JDFScavengerArea getCreateScavengerArea()
	{
		return (JDFScavengerArea) getCreateElement_JDFElement(ElementName.SCAVENGERAREA, null, 0);
	}

	/**
	 * (26) getCreateScavengerArea
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFScavengerArea the element
	 */
	public JDFScavengerArea getCreateScavengerArea(final int iSkip)
	{
		return (JDFScavengerArea) getCreateElement_JDFElement(ElementName.SCAVENGERAREA, null, iSkip);
	}

	/**
	 * (27) const get element ScavengerArea
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFScavengerArea the element default is getScavengerArea(0)
	 */
	public JDFScavengerArea getScavengerArea(final int iSkip)
	{
		return (JDFScavengerArea) getElement(ElementName.SCAVENGERAREA, null, iSkip);
	}

	/**
	 * Get all ScavengerArea from the current element
	 * 
	 * @return Collection<JDFScavengerArea>, null if none are available
	 */
	public Collection<JDFScavengerArea> getAllScavengerArea()
	{
		return getChildArrayByClass(JDFScavengerArea.class, false, 0);
	}

	/**
	 * (30) append element ScavengerArea
	 * 
	 * @return JDFScavengerArea the element
	 */
	public JDFScavengerArea appendScavengerArea()
	{
		return (JDFScavengerArea) appendElement(ElementName.SCAVENGERAREA, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refScavengerArea(final JDFScavengerArea refTarget)
	{
		refElement(refTarget);
	}

}
