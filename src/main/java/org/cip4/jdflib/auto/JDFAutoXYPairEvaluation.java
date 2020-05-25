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
import org.cip4.jdflib.datatypes.JDFXYPairRangeList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.devicecapability.JDFBasicPreflightTest;

/**
 *****************************************************************************
 * class JDFAutoXYPairEvaluation : public JDFResource
 *****************************************************************************
 *
 */

public abstract class JDFAutoXYPairEvaluation extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.TOLERANCE, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, "0 0");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.VALUELIST, 0x33333333, AttributeInfo.EnumAttributeType.XYPairRangeList, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.XYRELATION, 0x33333333, AttributeInfo.EnumAttributeType.XYRelation, EnumXYRelation.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BASICPREFLIGHTTEST, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoXYPairEvaluation
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoXYPairEvaluation(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoXYPairEvaluation
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoXYPairEvaluation(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoXYPairEvaluation
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoXYPairEvaluation(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for XYRelation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumXYRelation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumXYRelation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumXYRelation getEnum(String enumName)
		{
			return (EnumXYRelation) getEnum(EnumXYRelation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumXYRelation getEnum(int enumValue)
		{
			return (EnumXYRelation) getEnum(EnumXYRelation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumXYRelation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumXYRelation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumXYRelation.class);
		}

		/**  */
		public static final EnumXYRelation gt = new EnumXYRelation("gt");
		/**  */
		public static final EnumXYRelation ge = new EnumXYRelation("ge");
		/**  */
		public static final EnumXYRelation eq = new EnumXYRelation("eq");
		/**  */
		public static final EnumXYRelation le = new EnumXYRelation("le");
		/**  */
		public static final EnumXYRelation lt = new EnumXYRelation("lt");
		/**  */
		public static final EnumXYRelation ne = new EnumXYRelation("ne");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute Tolerance
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute Tolerance
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTolerance(JDFXYPair value)
	{
		setAttribute(AttributeName.TOLERANCE, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Tolerance
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getTolerance()
	{
		final String strAttrName = getAttribute(AttributeName.TOLERANCE, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ValueList
	--------------------------------------------------------------------- */
	/**
	 * (36) set attribute ValueList
	 *
	 * @param value the value to set the attribute to
	 */
	public void setValueList(JDFXYPairRangeList value)
	{
		setAttribute(AttributeName.VALUELIST, value.toString(), null);
	}

	/**
	 * (20) get JDFXYPairRangeList attribute ValueList
	 *
	 * @return JDFXYPairRangeList the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPairRangeList
	 */
	public JDFXYPairRangeList getValueList()
	{
		final String strAttrName = getAttribute(AttributeName.VALUELIST, null, null);
		final JDFXYPairRangeList nPlaceHolder = JDFXYPairRangeList.createXYPairRangeList(strAttrName);
		return nPlaceHolder;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute XYRelation
	--------------------------------------------------------------------- */
	/**
	 * (5) set attribute XYRelation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setXYRelation(EnumXYRelation enumVar)
	{
		setAttribute(AttributeName.XYRELATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute XYRelation
	 *
	 * @return the value of the attribute
	 */
	public EnumXYRelation getXYRelation()
	{
		return EnumXYRelation.getEnum(getAttribute(AttributeName.XYRELATION, null, null));
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (26) getCreateBasicPreflightTest
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFBasicPreflightTest the element
	 */
	public JDFBasicPreflightTest getCreateBasicPreflightTest(int iSkip)
	{
		return (JDFBasicPreflightTest) getCreateElement_JDFElement(ElementName.BASICPREFLIGHTTEST, null, iSkip);
	}

	/**
	 * (27) const get element BasicPreflightTest
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFBasicPreflightTest the element default is getBasicPreflightTest(0)
	 */
	public JDFBasicPreflightTest getBasicPreflightTest(int iSkip)
	{
		return (JDFBasicPreflightTest) getElement(ElementName.BASICPREFLIGHTTEST, null, iSkip);
	}

	/**
	 * Get all BasicPreflightTest from the current element
	 *
	 * @return Collection<JDFBasicPreflightTest>, null if none are available
	 */
	public Collection<JDFBasicPreflightTest> getAllBasicPreflightTest()
	{
		return getChildArrayByClass(JDFBasicPreflightTest.class, false, 0);
	}

	/**
	 * (30) append element BasicPreflightTest
	 *
	 * @return JDFBasicPreflightTest the element
	 */
	public JDFBasicPreflightTest appendBasicPreflightTest()
	{
		return (JDFBasicPreflightTest) appendElement(ElementName.BASICPREFLIGHTTEST, null);
	}

}
