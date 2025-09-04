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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.JDFXYPairRangeList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.devicecapability.JDFLoc;
import org.cip4.jdflib.resource.devicecapability.JDFValueLoc;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoXYPairState : public JDFResource
 */

public abstract class JDFAutoXYPairState extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[11];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DEFAULTVALUE, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.CURRENTVALUE, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.ALLOWEDVALUELIST, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPairRangeList, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ALLOWEDVALUEMAX, 0x4444444431l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.ALLOWEDVALUEMIN, 0x4444444431l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.ALLOWEDXYRELATION, 0x3333333311l, AttributeInfo.EnumAttributeType.XYRelation,
				EnumAllowedXYRelation.getEnum(0), null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.PRESENTVALUELIST, 0x3333333331l, AttributeInfo.EnumAttributeType.XYPairRangeList, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.PRESENTVALUEMAX, 0x4444444431l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.PRESENTVALUEMIN, 0x4444444431l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.PRESENTXYRELATION, 0x3333333311l, AttributeInfo.EnumAttributeType.XYRelation,
				EnumPresentXYRelation.getEnum(0), null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.UNITTYPE, 0x3333333311l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.LOC, 0x3333333331l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.VALUELOC, 0x3333333331l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoXYPairState
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoXYPairState(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoXYPairState
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoXYPairState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoXYPairState
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoXYPairState(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for AllowedXYRelation
	 */

	public enum EAllowedXYRelation
	{
		gt, ge, eq, le, lt, ne;

		public static EAllowedXYRelation getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EAllowedXYRelation.class, val, null);
		}
	}

	/**
	 * Enumeration strings for AllowedXYRelation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumAllowedXYRelation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumAllowedXYRelation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumAllowedXYRelation getEnum(String enumName)
		{
			return (EnumAllowedXYRelation) getEnum(EnumAllowedXYRelation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumAllowedXYRelation getEnum(int enumValue)
		{
			return (EnumAllowedXYRelation) getEnum(EnumAllowedXYRelation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumAllowedXYRelation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumAllowedXYRelation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumAllowedXYRelation.class);
		}

		/**  */
		public static final EnumAllowedXYRelation gt = new EnumAllowedXYRelation("gt");
		/**  */
		public static final EnumAllowedXYRelation ge = new EnumAllowedXYRelation("ge");
		/**  */
		public static final EnumAllowedXYRelation eq = new EnumAllowedXYRelation("eq");
		/**  */
		public static final EnumAllowedXYRelation le = new EnumAllowedXYRelation("le");
		/**  */
		public static final EnumAllowedXYRelation lt = new EnumAllowedXYRelation("lt");
		/**  */
		public static final EnumAllowedXYRelation ne = new EnumAllowedXYRelation("ne");
	}

	/**
	 * Enumeration strings for PresentXYRelation
	 */

	public enum EPresentXYRelation
	{
		gt, ge, eq, le, lt, ne;

		public static EPresentXYRelation getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EPresentXYRelation.class, val, null);
		}
	}

	/**
	 * Enumeration strings for PresentXYRelation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPresentXYRelation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPresentXYRelation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPresentXYRelation getEnum(String enumName)
		{
			return (EnumPresentXYRelation) getEnum(EnumPresentXYRelation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPresentXYRelation getEnum(int enumValue)
		{
			return (EnumPresentXYRelation) getEnum(EnumPresentXYRelation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPresentXYRelation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPresentXYRelation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPresentXYRelation.class);
		}

		/**  */
		public static final EnumPresentXYRelation gt = new EnumPresentXYRelation("gt");
		/**  */
		public static final EnumPresentXYRelation ge = new EnumPresentXYRelation("ge");
		/**  */
		public static final EnumPresentXYRelation eq = new EnumPresentXYRelation("eq");
		/**  */
		public static final EnumPresentXYRelation le = new EnumPresentXYRelation("le");
		/**  */
		public static final EnumPresentXYRelation lt = new EnumPresentXYRelation("lt");
		/**  */
		public static final EnumPresentXYRelation ne = new EnumPresentXYRelation("ne");
	}

	/*
	 * ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute DefaultValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DefaultValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDefaultValue(JDFXYPair value)
	{
		setAttribute(AttributeName.DEFAULTVALUE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute DefaultValue
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getDefaultValue()
	{
		String strAttrName = getAttribute(AttributeName.DEFAULTVALUE, null, null);
		JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CurrentValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CurrentValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCurrentValue(JDFXYPair value)
	{
		setAttribute(AttributeName.CURRENTVALUE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute CurrentValue
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getCurrentValue()
	{
		String strAttrName = getAttribute(AttributeName.CURRENTVALUE, null, null);
		JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AllowedValueList
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedValueList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedValueList(JDFXYPairRangeList value)
	{
		setAttribute(AttributeName.ALLOWEDVALUELIST, value == null ? null : value.toString(), null);
	}

	/**
	 * (20) get JDFXYPairRangeList attribute AllowedValueList
	 *
	 * @return JDFXYPairRangeList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPairRangeList
	 */
	public JDFXYPairRangeList getAllowedValueList()
	{
		String strAttrName = getAttribute(AttributeName.ALLOWEDVALUELIST, null, null);
		JDFXYPairRangeList nPlaceHolder = JDFXYPairRangeList.createXYPairRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AllowedValueMax
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedValueMax
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedValueMax(JDFXYPair value)
	{
		setAttribute(AttributeName.ALLOWEDVALUEMAX, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute AllowedValueMax
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getAllowedValueMax()
	{
		String strAttrName = getAttribute(AttributeName.ALLOWEDVALUEMAX, null, null);
		JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AllowedValueMin
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedValueMin
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedValueMin(JDFXYPair value)
	{
		setAttribute(AttributeName.ALLOWEDVALUEMIN, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute AllowedValueMin
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getAllowedValueMin()
	{
		String strAttrName = getAttribute(AttributeName.ALLOWEDVALUEMIN, null, null);
		JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AllowedXYRelation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute AllowedXYRelation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setAllowedXYRelation(EAllowedXYRelation enumVar)
	{
		setAttribute(AttributeName.ALLOWEDXYRELATION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute AllowedXYRelation
	 *
	 * @return the value of the attribute
	 */
	public EAllowedXYRelation getEAllowedXYRelation()
	{
		return EAllowedXYRelation.getEnum(getAttribute(AttributeName.ALLOWEDXYRELATION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AllowedXYRelation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute AllowedXYRelation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetAllowedXYRelation(EAllowedXYRelation) based on java.lang.enum instead
	 */
	@Deprecated
	public void setAllowedXYRelation(EnumAllowedXYRelation enumVar)
	{
		setAttribute(AttributeName.ALLOWEDXYRELATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute AllowedXYRelation
	 *
	 * @return the value of the attribute
	 * @deprecated use EAllowedXYRelation GetEAllowedXYRelation() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumAllowedXYRelation getAllowedXYRelation()
	{
		return EnumAllowedXYRelation.getEnum(getAttribute(AttributeName.ALLOWEDXYRELATION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PresentValueList
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentValueList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentValueList(JDFXYPairRangeList value)
	{
		setAttribute(AttributeName.PRESENTVALUELIST, value == null ? null : value.toString(), null);
	}

	/**
	 * (20) get JDFXYPairRangeList attribute PresentValueList
	 *
	 * @return JDFXYPairRangeList the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPairRangeList
	 */
	public JDFXYPairRangeList getPresentValueList()
	{
		String strAttrName = getAttribute(AttributeName.PRESENTVALUELIST, null, null);
		JDFXYPairRangeList nPlaceHolder = JDFXYPairRangeList.createXYPairRangeList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PresentValueMax
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentValueMax
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentValueMax(JDFXYPair value)
	{
		setAttribute(AttributeName.PRESENTVALUEMAX, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute PresentValueMax
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getPresentValueMax()
	{
		String strAttrName = getAttribute(AttributeName.PRESENTVALUEMAX, null, null);
		JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PresentValueMin
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentValueMin
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentValueMin(JDFXYPair value)
	{
		setAttribute(AttributeName.PRESENTVALUEMIN, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute PresentValueMin
	 *
	 * @return JDFXYPair the value of the attribute, null if a the
	 *         attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getPresentValueMin()
	{
		String strAttrName = getAttribute(AttributeName.PRESENTVALUEMIN, null, null);
		JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PresentXYRelation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PresentXYRelation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPresentXYRelation(EPresentXYRelation enumVar)
	{
		setAttribute(AttributeName.PRESENTXYRELATION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute PresentXYRelation
	 *
	 * @return the value of the attribute
	 */
	public EPresentXYRelation getEPresentXYRelation()
	{
		return EPresentXYRelation.getEnum(getAttribute(AttributeName.PRESENTXYRELATION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PresentXYRelation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute PresentXYRelation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetPresentXYRelation(EPresentXYRelation) based on java.lang.enum instead
	 */
	@Deprecated
	public void setPresentXYRelation(EnumPresentXYRelation enumVar)
	{
		setAttribute(AttributeName.PRESENTXYRELATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute PresentXYRelation
	 *
	 * @return the value of the attribute
	 * @deprecated use EPresentXYRelation GetEPresentXYRelation() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumPresentXYRelation getPresentXYRelation()
	{
		return EnumPresentXYRelation.getEnum(getAttribute(AttributeName.PRESENTXYRELATION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute UnitType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute UnitType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setUnitType(String value)
	{
		setAttribute(AttributeName.UNITTYPE, value, null);
	}

	/**
	 * (23) get String attribute UnitType
	 *
	 * @return the value of the attribute
	 */
	public String getUnitType()
	{
		return getAttribute(AttributeName.UNITTYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element Loc
	 *
	 * @return JDFLoc the element
	 */
	public JDFLoc getLoc()
	{
		return (JDFLoc) getElement(ElementName.LOC, null, 0);
	}

	/**
	 * (25) getCreateLoc
	 * 
	 * @return JDFLoc the element
	 */
	public JDFLoc getCreateLoc()
	{
		return (JDFLoc) getCreateElement_JDFElement(ElementName.LOC, null, 0);
	}

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
	 * @return JDFLoc the element
	 *         default is getLoc(0)
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
	 * (24) const get element ValueLoc
	 *
	 * @return JDFValueLoc the element
	 */
	public JDFValueLoc getValueLoc()
	{
		return (JDFValueLoc) getElement(ElementName.VALUELOC, null, 0);
	}

	/**
	 * (25) getCreateValueLoc
	 * 
	 * @return JDFValueLoc the element
	 */
	public JDFValueLoc getCreateValueLoc()
	{
		return (JDFValueLoc) getCreateElement_JDFElement(ElementName.VALUELOC, null, 0);
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
	 * @return JDFValueLoc the element
	 *         default is getValueLoc(0)
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
