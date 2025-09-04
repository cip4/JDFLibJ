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
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumHoleType;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFHoleLine;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRegisterMark;
import org.cip4.jdflib.resource.process.postpress.JDFHole;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoHoleMakingParams : public JDFResource
 */

public abstract class JDFAutoHoleMakingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CENTERREFERENCE, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration,
				EnumCenterReference.getEnum(0), "TrailingEdge");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.HOLETYPE, 0x2222222221l, AttributeInfo.EnumAttributeType.enumerations, EnumHoleType.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.CENTER, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.EXTENT, 0x3333333333l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.HOLECOUNT, 0x3333333311l, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.HOLEREFERENCEEDGE, 0x4444444431l, AttributeInfo.EnumAttributeType.enumeration,
				EnumHoleReferenceEdge.getEnum(0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.SHAPE, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumShape.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.HOLE, 0x3333333333l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.HOLELINE, 0x3333333331l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.REGISTERMARK, 0x6666666661l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoHoleMakingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoHoleMakingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoHoleMakingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoHoleMakingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoHoleMakingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoHoleMakingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		boolean bRet = super.init();
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
	 * Enumeration strings for CenterReference
	 */

	public enum ECenterReference
	{
		TrailingEdge, RegistrationMark;

		public static ECenterReference getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ECenterReference.class, val, ECenterReference.TrailingEdge);
		}
	}

	/**
	 * Enumeration strings for CenterReference
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumCenterReference extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumCenterReference(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumCenterReference getEnum(String enumName)
		{
			return (EnumCenterReference) getEnum(EnumCenterReference.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumCenterReference getEnum(int enumValue)
		{
			return (EnumCenterReference) getEnum(EnumCenterReference.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumCenterReference.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumCenterReference.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumCenterReference.class);
		}

		/**  */
		public static final EnumCenterReference TrailingEdge = new EnumCenterReference("TrailingEdge");
		/**  */
		public static final EnumCenterReference RegistrationMark = new EnumCenterReference("RegistrationMark");
	}

	/**
	 * Enumeration strings for HoleReferenceEdge
	 */

	public enum EHoleReferenceEdge
	{
		Left, Right, Top, Bottom, Pattern;

		public static EHoleReferenceEdge getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EHoleReferenceEdge.class, val, null);
		}
	}

	/**
	 * Enumeration strings for HoleReferenceEdge
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumHoleReferenceEdge extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumHoleReferenceEdge(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumHoleReferenceEdge getEnum(String enumName)
		{
			return (EnumHoleReferenceEdge) getEnum(EnumHoleReferenceEdge.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumHoleReferenceEdge getEnum(int enumValue)
		{
			return (EnumHoleReferenceEdge) getEnum(EnumHoleReferenceEdge.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumHoleReferenceEdge.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumHoleReferenceEdge.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumHoleReferenceEdge.class);
		}

		/**  */
		public static final EnumHoleReferenceEdge Left = new EnumHoleReferenceEdge("Left");
		/**  */
		public static final EnumHoleReferenceEdge Right = new EnumHoleReferenceEdge("Right");
		/**  */
		public static final EnumHoleReferenceEdge Top = new EnumHoleReferenceEdge("Top");
		/**  */
		public static final EnumHoleReferenceEdge Bottom = new EnumHoleReferenceEdge("Bottom");
		/**  */
		public static final EnumHoleReferenceEdge Pattern = new EnumHoleReferenceEdge("Pattern");
	}

	/**
	 * Enumeration strings for Shape
	 */

	public enum EShape
	{
		Eliptical, Round, Rectangular;

		public static EShape getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EShape.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Shape
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumShape extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumShape(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumShape getEnum(String enumName)
		{
			return (EnumShape) getEnum(EnumShape.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumShape getEnum(int enumValue)
		{
			return (EnumShape) getEnum(EnumShape.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumShape.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumShape.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumShape.class);
		}

		/**  */
		public static final EnumShape Eliptical = new EnumShape("Eliptical");
		/**  */
		public static final EnumShape Round = new EnumShape("Round");
		/**  */
		public static final EnumShape Rectangular = new EnumShape("Rectangular");
	}

	/*
	 * ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CenterReference
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute CenterReference
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setCenterReference(ECenterReference enumVar)
	{
		setAttribute(AttributeName.CENTERREFERENCE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute CenterReference
	 *
	 * @return the value of the attribute
	 */
	public ECenterReference getECenterReference()
	{
		return ECenterReference.getEnum(getAttribute(AttributeName.CENTERREFERENCE, null, "TrailingEdge"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CenterReference
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute CenterReference
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetCenterReference(ECenterReference) based on java.lang.enum instead
	 */
	@Deprecated
	public void setCenterReference(EnumCenterReference enumVar)
	{
		setAttribute(AttributeName.CENTERREFERENCE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute CenterReference
	 *
	 * @return the value of the attribute
	 * @deprecated use ECenterReference GetECenterReference() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumCenterReference getCenterReference()
	{
		return EnumCenterReference.getEnum(getAttribute(AttributeName.CENTERREFERENCE, null, "TrailingEdge"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute HoleType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute HoleType
	 *
	 * @param v List of the enumeration values
	 */
	public void setEHoleType(List<JDFMedia.EHoleType> v)
	{
		setEnumsAttribute(AttributeName.HOLETYPE, v, null);
	}

	/**
	 * (9.2) get HoleType attribute HoleType
	 *
	 * @return Vector of the enumerations
	 */
	public List<JDFMedia.EHoleType> getEnumsHoleType()
	{
		return getEnumerationsAttribute(AttributeName.HOLETYPE, null, JDFMedia.EHoleType.class);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute HoleType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute HoleType
	 *
	 * @param v List of the enumeration values
	 * @deprecated use SetEHoleType(List<Eedia.EnumHoleType>) based on java.lang.enum instead
	 */
	@Deprecated
	public void setHoleType(List<JDFMedia.EnumHoleType> v)
	{
		setEnumerationsAttribute(AttributeName.HOLETYPE, v, null);
	}

	/**
	 * (9.2) get HoleType attribute HoleType
	 *
	 * @return Vector of the enumerations
	 * @deprecated use List<Eedia.EnumHoleType > GetEHoleType() based on java.lang.enum instead
	 */
	@Deprecated
	public Vector<JDFMedia.EnumHoleType> getHoleType()
	{
		return getEnumerationsAttribute(AttributeName.HOLETYPE, null, JDFMedia.EnumHoleType.getEnum(0), false);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Center
	 * ---------------------------------------------------------------------
	 */
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
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getCenter()
	{
		String strAttrName = getAttribute(AttributeName.CENTER, null, null);
		JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Extent
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Extent
	 *
	 * @param value the value to set the attribute to
	 */
	public void setExtent(JDFXYPair value)
	{
		setAttribute(AttributeName.EXTENT, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Extent
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getExtent()
	{
		String strAttrName = getAttribute(AttributeName.EXTENT, null, null);
		JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute HoleCount
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute HoleCount
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHoleCount(JDFIntegerList value)
	{
		setAttribute(AttributeName.HOLECOUNT, value, null);
	}

	/**
	 * (20) get JDFIntegerList attribute HoleCount
	 *
	 * @return JDFIntegerList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFIntegerList
	 */
	public JDFIntegerList getHoleCount()
	{
		String strAttrName = getAttribute(AttributeName.HOLECOUNT, null, null);
		JDFIntegerList nPlaceHolder = JDFIntegerList.createIntegerList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute HoleReferenceEdge
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute HoleReferenceEdge
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setHoleReferenceEdge(EHoleReferenceEdge enumVar)
	{
		setAttribute(AttributeName.HOLEREFERENCEEDGE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute HoleReferenceEdge
	 *
	 * @return the value of the attribute
	 */
	public EHoleReferenceEdge getEHoleReferenceEdge()
	{
		return EHoleReferenceEdge.getEnum(getAttribute(AttributeName.HOLEREFERENCEEDGE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute HoleReferenceEdge
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute HoleReferenceEdge
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetHoleReferenceEdge(EHoleReferenceEdge) based on java.lang.enum instead
	 */
	@Deprecated
	public void setHoleReferenceEdge(EnumHoleReferenceEdge enumVar)
	{
		setAttribute(AttributeName.HOLEREFERENCEEDGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute HoleReferenceEdge
	 *
	 * @return the value of the attribute
	 * @deprecated use EHoleReferenceEdge GetEHoleReferenceEdge() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumHoleReferenceEdge getHoleReferenceEdge()
	{
		return EnumHoleReferenceEdge.getEnum(getAttribute(AttributeName.HOLEREFERENCEEDGE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Shape
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Shape
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setShape(EShape enumVar)
	{
		setAttribute(AttributeName.SHAPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Shape
	 *
	 * @return the value of the attribute
	 */
	public EShape getEShape()
	{
		return EShape.getEnum(getAttribute(AttributeName.SHAPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Shape
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Shape
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetShape(EShape) based on java.lang.enum instead
	 */
	@Deprecated
	public void setShape(EnumShape enumVar)
	{
		setAttribute(AttributeName.SHAPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Shape
	 *
	 * @return the value of the attribute
	 * @deprecated use EShape GetEShape() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumShape getShape()
	{
		return EnumShape.getEnum(getAttribute(AttributeName.SHAPE, null, null));
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element Hole
	 *
	 * @return JDFHole the element
	 */
	public JDFHole getHole()
	{
		return (JDFHole) getElement(ElementName.HOLE, null, 0);
	}

	/**
	 * (25) getCreateHole
	 * 
	 * @return JDFHole the element
	 */
	public JDFHole getCreateHole()
	{
		return (JDFHole) getCreateElement_JDFElement(ElementName.HOLE, null, 0);
	}

	/**
	 * (26) getCreateHole
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFHole the element
	 */
	public JDFHole getCreateHole(int iSkip)
	{
		return (JDFHole) getCreateElement_JDFElement(ElementName.HOLE, null, iSkip);
	}

	/**
	 * (27) const get element Hole
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFHole the element
	 *         default is getHole(0)
	 */
	public JDFHole getHole(int iSkip)
	{
		return (JDFHole) getElement(ElementName.HOLE, null, iSkip);
	}

	/**
	 * Get all Hole from the current element
	 * 
	 * @return Collection<JDFHole>, null if none are available
	 */
	public Collection<JDFHole> getAllHole()
	{
		return getChildArrayByClass(JDFHole.class, false, 0);
	}

	/**
	 * (30) append element Hole
	 *
	 * @return JDFHole the element
	 */
	public JDFHole appendHole()
	{
		return (JDFHole) appendElement(ElementName.HOLE, null);
	}

	/**
	 * (24) const get element HoleLine
	 *
	 * @return JDFHoleLine the element
	 */
	public JDFHoleLine getHoleLine()
	{
		return (JDFHoleLine) getElement(ElementName.HOLELINE, null, 0);
	}

	/**
	 * (25) getCreateHoleLine
	 * 
	 * @return JDFHoleLine the element
	 */
	public JDFHoleLine getCreateHoleLine()
	{
		return (JDFHoleLine) getCreateElement_JDFElement(ElementName.HOLELINE, null, 0);
	}

	/**
	 * (26) getCreateHoleLine
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFHoleLine the element
	 */
	public JDFHoleLine getCreateHoleLine(int iSkip)
	{
		return (JDFHoleLine) getCreateElement_JDFElement(ElementName.HOLELINE, null, iSkip);
	}

	/**
	 * (27) const get element HoleLine
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFHoleLine the element
	 *         default is getHoleLine(0)
	 */
	public JDFHoleLine getHoleLine(int iSkip)
	{
		return (JDFHoleLine) getElement(ElementName.HOLELINE, null, iSkip);
	}

	/**
	 * Get all HoleLine from the current element
	 * 
	 * @return Collection<JDFHoleLine>, null if none are available
	 */
	public Collection<JDFHoleLine> getAllHoleLine()
	{
		return getChildArrayByClass(JDFHoleLine.class, false, 0);
	}

	/**
	 * (30) append element HoleLine
	 *
	 * @return JDFHoleLine the element
	 */
	public JDFHoleLine appendHoleLine()
	{
		return (JDFHoleLine) appendElement(ElementName.HOLELINE, null);
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
	 * (29) append element RegisterMark
	 *
	 * @return JDFRegisterMark the element
	 * @ if the element already exists
	 */
	public JDFRegisterMark appendRegisterMark()
	{
		return (JDFRegisterMark) appendElementN(ElementName.REGISTERMARK, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refRegisterMark(JDFRegisterMark refTarget)
	{
		refElement(refTarget);
	}

}
