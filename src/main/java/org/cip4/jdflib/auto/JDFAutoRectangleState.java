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
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFRectangleRangeList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.devicecapability.JDFLoc;
import org.cip4.jdflib.resource.devicecapability.JDFValueLoc;

/**
 *****************************************************************************
 * class JDFAutoRectangleState : public JDFResource
 *****************************************************************************
 *
 */

public abstract class JDFAutoRectangleState extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DEFAULTVALUE, 0x33333311, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CURRENTVALUE, 0x33333311, AttributeInfo.EnumAttributeType.rectangle, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ALLOWEDHWRELATION, 0x33333311, AttributeInfo.EnumAttributeType.XYRelation, EnumAllowedHWRelation.getEnum(0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ALLOWEDVALUELIST, 0x33333311, AttributeInfo.EnumAttributeType.RectangleRangeList, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.PRESENTHWRELATION, 0x33333311, AttributeInfo.EnumAttributeType.XYRelation, EnumPresentHWRelation.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.PRESENTVALUELIST, 0x33333311, AttributeInfo.EnumAttributeType.RectangleRangeList, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.LOC, 0x33333311);
		elemInfoTable[1] = new ElemInfoTable(ElementName.VALUELOC, 0x33333311);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoRectangleState
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoRectangleState(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoRectangleState
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoRectangleState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoRectangleState
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoRectangleState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for AllowedHWRelation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumAllowedHWRelation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumAllowedHWRelation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumAllowedHWRelation getEnum(String enumName)
		{
			return (EnumAllowedHWRelation) getEnum(EnumAllowedHWRelation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumAllowedHWRelation getEnum(int enumValue)
		{
			return (EnumAllowedHWRelation) getEnum(EnumAllowedHWRelation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumAllowedHWRelation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumAllowedHWRelation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumAllowedHWRelation.class);
		}

		/**  */
		public static final EnumAllowedHWRelation gt = new EnumAllowedHWRelation("gt");
		/**  */
		public static final EnumAllowedHWRelation ge = new EnumAllowedHWRelation("ge");
		/**  */
		public static final EnumAllowedHWRelation eq = new EnumAllowedHWRelation("eq");
		/**  */
		public static final EnumAllowedHWRelation le = new EnumAllowedHWRelation("le");
		/**  */
		public static final EnumAllowedHWRelation lt = new EnumAllowedHWRelation("lt");
		/**  */
		public static final EnumAllowedHWRelation ne = new EnumAllowedHWRelation("ne");
	}

	/**
	 * Enumeration strings for PresentHWRelation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPresentHWRelation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumPresentHWRelation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPresentHWRelation getEnum(String enumName)
		{
			return (EnumPresentHWRelation) getEnum(EnumPresentHWRelation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPresentHWRelation getEnum(int enumValue)
		{
			return (EnumPresentHWRelation) getEnum(EnumPresentHWRelation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPresentHWRelation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPresentHWRelation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPresentHWRelation.class);
		}

		/**  */
		public static final EnumPresentHWRelation gt = new EnumPresentHWRelation("gt");
		/**  */
		public static final EnumPresentHWRelation ge = new EnumPresentHWRelation("ge");
		/**  */
		public static final EnumPresentHWRelation eq = new EnumPresentHWRelation("eq");
		/**  */
		public static final EnumPresentHWRelation le = new EnumPresentHWRelation("le");
		/**  */
		public static final EnumPresentHWRelation lt = new EnumPresentHWRelation("lt");
		/**  */
		public static final EnumPresentHWRelation ne = new EnumPresentHWRelation("ne");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute DefaultValue
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute DefaultValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDefaultValue(JDFRectangle value)
	{
		setAttribute(AttributeName.DEFAULTVALUE, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute DefaultValue
	 *
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getDefaultValue()
	{
		final String strAttrName = getAttribute(AttributeName.DEFAULTVALUE, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute CurrentValue
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute CurrentValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCurrentValue(JDFRectangle value)
	{
		setAttribute(AttributeName.CURRENTVALUE, value, null);
	}

	/**
	 * (20) get JDFRectangle attribute CurrentValue
	 *
	 * @return JDFRectangle the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangle
	 */
	public JDFRectangle getCurrentValue()
	{
		final String strAttrName = getAttribute(AttributeName.CURRENTVALUE, null, null);
		final JDFRectangle nPlaceHolder = JDFRectangle.createRectangle(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute AllowedHWRelation
	--------------------------------------------------------------------- */
	/**
	 * (5) set attribute AllowedHWRelation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setAllowedHWRelation(EnumAllowedHWRelation enumVar)
	{
		setAttribute(AttributeName.ALLOWEDHWRELATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute AllowedHWRelation
	 *
	 * @return the value of the attribute
	 */
	public EnumAllowedHWRelation getAllowedHWRelation()
	{
		return EnumAllowedHWRelation.getEnum(getAttribute(AttributeName.ALLOWEDHWRELATION, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute AllowedValueList
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute AllowedValueList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedValueList(JDFRectangleRangeList value)
	{
		setAttribute(AttributeName.ALLOWEDVALUELIST, value, null);
	}

	/**
	 * (20) get JDFRectangleRangeList attribute AllowedValueList
	 *
	 * @return JDFRectangleRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangleRangeList
	 */
	public JDFRectangleRangeList getAllowedValueList()
	{
		final String strAttrName = getAttribute(AttributeName.ALLOWEDVALUELIST, null, null);
		final JDFRectangleRangeList nPlaceHolder = JDFRectangleRangeList.createRectangleRangeList(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute PresentHWRelation
	--------------------------------------------------------------------- */
	/**
	 * (5) set attribute PresentHWRelation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPresentHWRelation(EnumPresentHWRelation enumVar)
	{
		setAttribute(AttributeName.PRESENTHWRELATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute PresentHWRelation
	 *
	 * @return the value of the attribute
	 */
	public EnumPresentHWRelation getPresentHWRelation()
	{
		return EnumPresentHWRelation.getEnum(getAttribute(AttributeName.PRESENTHWRELATION, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute PresentValueList
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute PresentValueList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentValueList(JDFRectangleRangeList value)
	{
		setAttribute(AttributeName.PRESENTVALUELIST, value, null);
	}

	/**
	 * (20) get JDFRectangleRangeList attribute PresentValueList
	 *
	 * @return JDFRectangleRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFRectangleRangeList
	 */
	public JDFRectangleRangeList getPresentValueList()
	{
		final String strAttrName = getAttribute(AttributeName.PRESENTVALUELIST, null, null);
		final JDFRectangleRangeList nPlaceHolder = JDFRectangleRangeList.createRectangleRangeList(strAttrName);
		return nPlaceHolder;
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (26) getCreateLoc
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFLoc the element
	 */
	public JDFLoc getCreateLoc(int iSkip)
	{
		return (JDFLoc) getCreateElement_JDFElement(ElementName.LOC, null, iSkip);
	}

	/**
	 * (27) const get element Loc
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFLoc the element default is getLoc(0)
	 */
	public JDFLoc getLoc(int iSkip)
	{
		return (JDFLoc) getElement(ElementName.LOC, null, iSkip);
	}

	/**
	 * Get all Loc from the current element
	 *
	 * @return Collection<JDFLoc>, null if none are available
	 */
	public Collection<JDFLoc> getAllLoc()
	{
		return getChildArrayByClass(JDFLoc.class, false, 0);
	}

	/**
	 * (30) append element Loc
	 *
	 * @return JDFLoc the element
	 */
	public JDFLoc appendLoc()
	{
		return (JDFLoc) appendElement(ElementName.LOC, null);
	}

	/**
	 * (26) getCreateValueLoc
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFValueLoc the element
	 */
	public JDFValueLoc getCreateValueLoc(int iSkip)
	{
		return (JDFValueLoc) getCreateElement_JDFElement(ElementName.VALUELOC, null, iSkip);
	}

	/**
	 * (27) const get element ValueLoc
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFValueLoc the element default is getValueLoc(0)
	 */
	public JDFValueLoc getValueLoc(int iSkip)
	{
		return (JDFValueLoc) getElement(ElementName.VALUELOC, null, iSkip);
	}

	/**
	 * Get all ValueLoc from the current element
	 *
	 * @return Collection<JDFValueLoc>, null if none are available
	 */
	public Collection<JDFValueLoc> getAllValueLoc()
	{
		return getChildArrayByClass(JDFValueLoc.class, false, 0);
	}

	/**
	 * (30) append element ValueLoc
	 *
	 * @return JDFValueLoc the element
	 */
	public JDFValueLoc appendValueLoc()
	{
		return (JDFValueLoc) appendElement(ElementName.VALUELOC, null);
	}

}
