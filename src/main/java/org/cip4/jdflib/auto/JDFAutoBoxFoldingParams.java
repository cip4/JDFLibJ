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
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFBoxApplication;
import org.cip4.jdflib.resource.process.JDFBoxFoldAction;
import org.cip4.jdflib.resource.process.postpress.JDFGlueLine;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoBoxFoldingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoBoxFoldingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BLANKDIMENSIONSX, 0x3333333111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.BLANKDIMENSIONSY, 0x3333333111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.BOXFOLDINGTYPE, 0x3333333111l, AttributeInfo.EnumAttributeType.enumeration, EnumBoxFoldingType.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BOXFOLDACTION, 0x3333333111l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.GLUELINE, 0x3333333111l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.BOXAPPLICATION, 0x3333333111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoBoxFoldingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoBoxFoldingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBoxFoldingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoBoxFoldingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoBoxFoldingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoBoxFoldingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for BoxFoldingType
	 */

	public enum EBoxFoldingType
	{
		Type00, Type01, Type02, Type03, Type04, Type10, Type11, Type12, Type13, Type15, Type20;

		public static EBoxFoldingType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EBoxFoldingType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for BoxFoldingType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumBoxFoldingType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumBoxFoldingType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumBoxFoldingType getEnum(String enumName)
		{
			return (EnumBoxFoldingType) getEnum(EnumBoxFoldingType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumBoxFoldingType getEnum(int enumValue)
		{
			return (EnumBoxFoldingType) getEnum(EnumBoxFoldingType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBoxFoldingType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBoxFoldingType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBoxFoldingType.class);
		}

		/**  */
		public static final EnumBoxFoldingType Type00 = new EnumBoxFoldingType("Type00");
		/**  */
		public static final EnumBoxFoldingType Type01 = new EnumBoxFoldingType("Type01");
		/**  */
		public static final EnumBoxFoldingType Type02 = new EnumBoxFoldingType("Type02");
		/**  */
		public static final EnumBoxFoldingType Type03 = new EnumBoxFoldingType("Type03");
		/**  */
		public static final EnumBoxFoldingType Type04 = new EnumBoxFoldingType("Type04");
		/**  */
		public static final EnumBoxFoldingType Type10 = new EnumBoxFoldingType("Type10");
		/**  */
		public static final EnumBoxFoldingType Type11 = new EnumBoxFoldingType("Type11");
		/**  */
		public static final EnumBoxFoldingType Type12 = new EnumBoxFoldingType("Type12");
		/**  */
		public static final EnumBoxFoldingType Type13 = new EnumBoxFoldingType("Type13");
		/**  */
		public static final EnumBoxFoldingType Type15 = new EnumBoxFoldingType("Type15");
		/**  */
		public static final EnumBoxFoldingType Type20 = new EnumBoxFoldingType("Type20");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BlankDimensionsX
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BlankDimensionsX
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBlankDimensionsX(JDFNumberList value)
	{
		setAttribute(AttributeName.BLANKDIMENSIONSX, value, null);
	}

	/**
	 * (20) get JDFNumberList attribute BlankDimensionsX
	 *
	 * @return JDFNumberList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberList
	 */
	public JDFNumberList getBlankDimensionsX()
	{
		final String strAttrName = getAttribute(AttributeName.BLANKDIMENSIONSX, null, null);
		final JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BlankDimensionsY
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BlankDimensionsY
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBlankDimensionsY(JDFNumberList value)
	{
		setAttribute(AttributeName.BLANKDIMENSIONSY, value, null);
	}

	/**
	 * (20) get JDFNumberList attribute BlankDimensionsY
	 *
	 * @return JDFNumberList the value of the attribute, null if a the attribute value is not a valid to create a JDFNumberList
	 */
	public JDFNumberList getBlankDimensionsY()
	{
		final String strAttrName = getAttribute(AttributeName.BLANKDIMENSIONSY, null, null);
		final JDFNumberList nPlaceHolder = JDFNumberList.createNumberList(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BoxFoldingType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BoxFoldingType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setBoxFoldingType(EBoxFoldingType enumVar)
	{
		setAttribute(AttributeName.BOXFOLDINGTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute BoxFoldingType
	 *
	 * @return the value of the attribute
	 */
	public EBoxFoldingType getEBoxFoldingType()
	{
		return EBoxFoldingType.getEnum(getAttribute(AttributeName.BOXFOLDINGTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BoxFoldingType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute BoxFoldingType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setBoxFoldingType(EnumBoxFoldingType enumVar)
	{
		setAttribute(AttributeName.BOXFOLDINGTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute BoxFoldingType
	 *
	 * @return the value of the attribute
	 */
	public EnumBoxFoldingType getBoxFoldingType()
	{
		return EnumBoxFoldingType.getEnum(getAttribute(AttributeName.BOXFOLDINGTYPE, null, null));
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element BoxFoldAction
	 *
	 * @return JDFBoxFoldAction the element
	 */
	public JDFBoxFoldAction getBoxFoldAction()
	{
		return (JDFBoxFoldAction) getElement(ElementName.BOXFOLDACTION, null, 0);
	}

	/**
	 * (25) getCreateBoxFoldAction
	 * 
	 * @return JDFBoxFoldAction the element
	 */
	public JDFBoxFoldAction getCreateBoxFoldAction()
	{
		return (JDFBoxFoldAction) getCreateElement_JDFElement(ElementName.BOXFOLDACTION, null, 0);
	}

	/**
	 * (26) getCreateBoxFoldAction
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFBoxFoldAction the element
	 */
	public JDFBoxFoldAction getCreateBoxFoldAction(int iSkip)
	{
		return (JDFBoxFoldAction) getCreateElement_JDFElement(ElementName.BOXFOLDACTION, null, iSkip);
	}

	/**
	 * (27) const get element BoxFoldAction
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFBoxFoldAction the element default is getBoxFoldAction(0)
	 */
	public JDFBoxFoldAction getBoxFoldAction(int iSkip)
	{
		return (JDFBoxFoldAction) getElement(ElementName.BOXFOLDACTION, null, iSkip);
	}

	/**
	 * Get all BoxFoldAction from the current element
	 * 
	 * @return Collection<JDFBoxFoldAction>, null if none are available
	 */
	public Collection<JDFBoxFoldAction> getAllBoxFoldAction()
	{
		return getChildArrayByClass(JDFBoxFoldAction.class, false, 0);
	}

	/**
	 * (30) append element BoxFoldAction
	 *
	 * @return JDFBoxFoldAction the element
	 */
	public JDFBoxFoldAction appendBoxFoldAction()
	{
		return (JDFBoxFoldAction) appendElement(ElementName.BOXFOLDACTION, null);
	}

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

	/**
	 * (24) const get element BoxApplication
	 *
	 * @return JDFBoxApplication the element
	 */
	public JDFBoxApplication getBoxApplication()
	{
		return (JDFBoxApplication) getElement(ElementName.BOXAPPLICATION, null, 0);
	}

	/**
	 * (25) getCreateBoxApplication
	 * 
	 * @return JDFBoxApplication the element
	 */
	public JDFBoxApplication getCreateBoxApplication()
	{
		return (JDFBoxApplication) getCreateElement_JDFElement(ElementName.BOXAPPLICATION, null, 0);
	}

	/**
	 * (26) getCreateBoxApplication
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFBoxApplication the element
	 */
	public JDFBoxApplication getCreateBoxApplication(int iSkip)
	{
		return (JDFBoxApplication) getCreateElement_JDFElement(ElementName.BOXAPPLICATION, null, iSkip);
	}

	/**
	 * (27) const get element BoxApplication
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFBoxApplication the element default is getBoxApplication(0)
	 */
	public JDFBoxApplication getBoxApplication(int iSkip)
	{
		return (JDFBoxApplication) getElement(ElementName.BOXAPPLICATION, null, iSkip);
	}

	/**
	 * Get all BoxApplication from the current element
	 * 
	 * @return Collection<JDFBoxApplication>, null if none are available
	 */
	public Collection<JDFBoxApplication> getAllBoxApplication()
	{
		return getChildArrayByClass(JDFBoxApplication.class, false, 0);
	}

	/**
	 * (30) append element BoxApplication
	 *
	 * @return JDFBoxApplication the element
	 */
	public JDFBoxApplication appendBoxApplication()
	{
		return (JDFBoxApplication) appendElement(ElementName.BOXAPPLICATION, null);
	}

}
