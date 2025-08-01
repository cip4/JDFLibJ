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
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.postpress.JDFFold;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoFoldingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoFoldingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.SHEETLAY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumSheetLay.getEnum(0), "Left");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DESCRIPTIONTYPE, 0x4444444433l, AttributeInfo.EnumAttributeType.enumeration, EnumDescriptionType.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.FOLDCATALOG, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.FOLDINGDETAILS, 0x3333111111l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.FOLDSHEETIN, 0x4444444443l, AttributeInfo.EnumAttributeType.XYPair, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.FOLD, 0x3333333331l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoFoldingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoFoldingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFoldingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoFoldingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFoldingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoFoldingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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

	public enum ESheetLay
	{
		Left, Right;

		public static ESheetLay getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ESheetLay.class, val, ESheetLay.Left);
		}
	}

	/**
	 * Enumeration strings for SheetLay
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumSheetLay extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumSheetLay(String name)
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

	/**
	 * Enumeration strings for DescriptionType
	 */

	public enum EDescriptionType
	{
		FoldProc, FoldCatalog;

		public static EDescriptionType getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EDescriptionType.class, val, null);
		}
	}

	/**
	 * Enumeration strings for DescriptionType
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumDescriptionType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumDescriptionType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumDescriptionType getEnum(String enumName)
		{
			return (EnumDescriptionType) getEnum(EnumDescriptionType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumDescriptionType getEnum(int enumValue)
		{
			return (EnumDescriptionType) getEnum(EnumDescriptionType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDescriptionType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDescriptionType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDescriptionType.class);
		}

		/**  */
		public static final EnumDescriptionType FoldProc = new EnumDescriptionType("FoldProc");
		/**  */
		public static final EnumDescriptionType FoldCatalog = new EnumDescriptionType("FoldCatalog");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SheetLay ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SheetLay
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setSheetLay(ESheetLay enumVar)
	{
		setAttribute(AttributeName.SHEETLAY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute SheetLay
	 *
	 * @return the value of the attribute
	 */
	public ESheetLay getESheetLay()
	{
		return ESheetLay.getEnum(getAttribute(AttributeName.SHEETLAY, null, "Left"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SheetLay ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute SheetLay
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
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
		return EnumSheetLay.getEnum(getAttribute(AttributeName.SHEETLAY, null, "Left"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DescriptionType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DescriptionType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDescriptionType(EDescriptionType enumVar)
	{
		setAttribute(AttributeName.DESCRIPTIONTYPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute DescriptionType
	 *
	 * @return the value of the attribute
	 */
	public EDescriptionType getEDescriptionType()
	{
		return EDescriptionType.getEnum(getAttribute(AttributeName.DESCRIPTIONTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DescriptionType
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DescriptionType
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setDescriptionType(EnumDescriptionType enumVar)
	{
		setAttribute(AttributeName.DESCRIPTIONTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute DescriptionType
	 *
	 * @return the value of the attribute
	 */
	public EnumDescriptionType getDescriptionType()
	{
		return EnumDescriptionType.getEnum(getAttribute(AttributeName.DESCRIPTIONTYPE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FoldCatalog ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FoldCatalog
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFoldCatalog(String value)
	{
		setAttribute(AttributeName.FOLDCATALOG, value, null);
	}

	/**
	 * (23) get String attribute FoldCatalog
	 *
	 * @return the value of the attribute
	 */
	public String getFoldCatalog()
	{
		return getAttribute(AttributeName.FOLDCATALOG, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FoldingDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FoldingDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFoldingDetails(String value)
	{
		setAttribute(AttributeName.FOLDINGDETAILS, value, null);
	}

	/**
	 * (23) get String attribute FoldingDetails
	 *
	 * @return the value of the attribute
	 */
	public String getFoldingDetails()
	{
		return getAttribute(AttributeName.FOLDINGDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FoldSheetIn ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FoldSheetIn
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFoldSheetIn(JDFXYPair value)
	{
		setAttribute(AttributeName.FOLDSHEETIN, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute FoldSheetIn
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getFoldSheetIn()
	{
		final String strAttrName = getAttribute(AttributeName.FOLDSHEETIN, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Fold
	 *
	 * @return JDFFold the element
	 */
	public JDFFold getFold()
	{
		return (JDFFold) getElement(ElementName.FOLD, null, 0);
	}

	/**
	 * (25) getCreateFold
	 * 
	 * @return JDFFold the element
	 */
	public JDFFold getCreateFold()
	{
		return (JDFFold) getCreateElement_JDFElement(ElementName.FOLD, null, 0);
	}

	/**
	 * (26) getCreateFold
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFFold the element
	 */
	public JDFFold getCreateFold(int iSkip)
	{
		return (JDFFold) getCreateElement_JDFElement(ElementName.FOLD, null, iSkip);
	}

	/**
	 * (27) const get element Fold
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFFold the element default is getFold(0)
	 */
	public JDFFold getFold(int iSkip)
	{
		return (JDFFold) getElement(ElementName.FOLD, null, iSkip);
	}

	/**
	 * Get all Fold from the current element
	 * 
	 * @return Collection<JDFFold>, null if none are available
	 */
	public Collection<JDFFold> getAllFold()
	{
		return getChildArrayByClass(JDFFold.class, false, 0);
	}

	/**
	 * (30) append element Fold
	 *
	 * @return JDFFold the element
	 */
	public JDFFold appendFold()
	{
		return (JDFFold) appendElement(ElementName.FOLD, null);
	}

}
