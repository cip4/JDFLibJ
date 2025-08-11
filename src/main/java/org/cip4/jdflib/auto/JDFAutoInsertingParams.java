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
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.postpress.JDFGlueLine;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoInsertingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoInsertingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.INSERTLOCATION, 0x2222222222l, AttributeInfo.EnumAttributeType.enumeration, EnumInsertLocation.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.METHOD, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumMethod.getEnum(0), "BlowIn");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.SHEETOFFSET, 0x4444444443l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.FINISHEDPAGE, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.GLUELINE, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoInsertingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoInsertingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoInsertingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoInsertingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoInsertingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoInsertingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for InsertLocation
	 */

	public enum EInsertLocation
	{
		Front, Back, OverfoldLeft, OverfoldRight, Overfold, FinishedPage;

		public static EInsertLocation getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EInsertLocation.class, val, null);
		}
	}

	/**
	 * Enumeration strings for InsertLocation
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumInsertLocation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumInsertLocation(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumInsertLocation getEnum(String enumName)
		{
			return (EnumInsertLocation) getEnum(EnumInsertLocation.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumInsertLocation getEnum(int enumValue)
		{
			return (EnumInsertLocation) getEnum(EnumInsertLocation.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumInsertLocation.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumInsertLocation.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumInsertLocation.class);
		}

		/**  */
		public static final EnumInsertLocation Front = new EnumInsertLocation("Front");
		/**  */
		public static final EnumInsertLocation Back = new EnumInsertLocation("Back");
		/**  */
		public static final EnumInsertLocation OverfoldLeft = new EnumInsertLocation("OverfoldLeft");
		/**  */
		public static final EnumInsertLocation OverfoldRight = new EnumInsertLocation("OverfoldRight");
		/**  */
		public static final EnumInsertLocation Overfold = new EnumInsertLocation("Overfold");
		/**  */
		public static final EnumInsertLocation FinishedPage = new EnumInsertLocation("FinishedPage");
	}

	/**
	 * Enumeration strings for Method
	 */

	public enum EMethod
	{
		BlowIn, BindIn;

		public static EMethod getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EMethod.class, val, EMethod.BlowIn);
		}
	}

	/**
	 * Enumeration strings for Method
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumMethod extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumMethod(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumMethod getEnum(String enumName)
		{
			return (EnumMethod) getEnum(EnumMethod.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumMethod getEnum(int enumValue)
		{
			return (EnumMethod) getEnum(EnumMethod.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumMethod.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumMethod.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumMethod.class);
		}

		/**  */
		public static final EnumMethod BlowIn = new EnumMethod("BlowIn");
		/**  */
		public static final EnumMethod BindIn = new EnumMethod("BindIn");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute InsertLocation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute InsertLocation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setInsertLocation(EInsertLocation enumVar)
	{
		setAttribute(AttributeName.INSERTLOCATION, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute InsertLocation
	 *
	 * @return the value of the attribute
	 */
	public EInsertLocation getEInsertLocation()
	{
		return EInsertLocation.getEnum(getAttribute(AttributeName.INSERTLOCATION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute InsertLocation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute InsertLocation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setInsertLocation(EInsertLocation) based on java.lang.enum instead
	 */
	@Deprecated
	public void setInsertLocation(EnumInsertLocation enumVar)
	{
		setAttribute(AttributeName.INSERTLOCATION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute InsertLocation
	 *
	 * @return the value of the attribute
	 * @deprecated use EInsertLocation getEInsertLocation() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumInsertLocation getInsertLocation()
	{
		return EnumInsertLocation.getEnum(getAttribute(AttributeName.INSERTLOCATION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Method ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Method
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMethod(EMethod enumVar)
	{
		setAttribute(AttributeName.METHOD, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Method
	 *
	 * @return the value of the attribute
	 */
	public EMethod getEMethod()
	{
		return EMethod.getEnum(getAttribute(AttributeName.METHOD, null, "BlowIn"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Method ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Method
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use setMethod(EMethod) based on java.lang.enum instead
	 */
	@Deprecated
	public void setMethod(EnumMethod enumVar)
	{
		setAttribute(AttributeName.METHOD, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Method
	 *
	 * @return the value of the attribute
	 * @deprecated use EMethod getEMethod() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumMethod getMethod()
	{
		return EnumMethod.getEnum(getAttribute(AttributeName.METHOD, null, "BlowIn"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SheetOffset ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SheetOffset
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSheetOffset(JDFXYPair value)
	{
		setAttribute(AttributeName.SHEETOFFSET, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute SheetOffset
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getSheetOffset()
	{
		final String strAttrName = getAttribute(AttributeName.SHEETOFFSET, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FinishedPage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FinishedPage
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFinishedPage(int value)
	{
		setAttribute(AttributeName.FINISHEDPAGE, value, null);
	}

	/**
	 * (15) get int attribute FinishedPage
	 *
	 * @return int the value of the attribute
	 */
	public int getFinishedPage()
	{
		return getIntAttribute(AttributeName.FINISHEDPAGE, null, 0);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element GlueLine
	 *
	 * @return JDFGlueLine the element
	 */
	public JDFGlueLine getGlueLine()
	{
		return (JDFGlueLine) getElement(ElementName.GLUELINE, null, 0);
	}

	/**
	 * (25) getCreateGlueLine
	 * 
	 * @return JDFGlueLine the element
	 */
	public JDFGlueLine getCreateGlueLine()
	{
		return (JDFGlueLine) getCreateElement_JDFElement(ElementName.GLUELINE, null, 0);
	}

	/**
	 * (26) getCreateGlueLine
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFGlueLine the element
	 */
	public JDFGlueLine getCreateGlueLine(int iSkip)
	{
		return (JDFGlueLine) getCreateElement_JDFElement(ElementName.GLUELINE, null, iSkip);
	}

	/**
	 * (27) const get element GlueLine
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFGlueLine the element default is getGlueLine(0)
	 */
	public JDFGlueLine getGlueLine(int iSkip)
	{
		return (JDFGlueLine) getElement(ElementName.GLUELINE, null, iSkip);
	}

	/**
	 * Get all GlueLine from the current element
	 * 
	 * @return Collection<JDFGlueLine>, null if none are available
	 */
	public Collection<JDFGlueLine> getAllGlueLine()
	{
		return getChildArrayByClass(JDFGlueLine.class, false, 0);
	}

	/**
	 * (30) append element GlueLine
	 *
	 * @return JDFGlueLine the element
	 */
	public JDFGlueLine appendGlueLine()
	{
		return (JDFGlueLine) appendElement(ElementName.GLUELINE, null);
	}

}
