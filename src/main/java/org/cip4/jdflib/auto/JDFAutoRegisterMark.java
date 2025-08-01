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
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoRegisterMark : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoRegisterMark extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CENTER, 0x2222222222l, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.MARKTYPE, 0x3333333333l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.MARKUSAGE, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumMarkUsage.getEnum(0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ROTATION, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.SEPARATIONSPEC, 0x3333333333l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoRegisterMark
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoRegisterMark(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoRegisterMark
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoRegisterMark(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoRegisterMark
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoRegisterMark(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for MarkUsage
	 */

	public enum EMarkUsage
	{
		Color, PaperPath, Tile;

		public static EMarkUsage getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EMarkUsage.class, val, null);
		}
	}

	/**
	 * Enumeration strings for MarkUsage
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumMarkUsage extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumMarkUsage(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumMarkUsage getEnum(String enumName)
		{
			return (EnumMarkUsage) getEnum(EnumMarkUsage.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumMarkUsage getEnum(int enumValue)
		{
			return (EnumMarkUsage) getEnum(EnumMarkUsage.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumMarkUsage.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumMarkUsage.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumMarkUsage.class);
		}

		/**  */
		public static final EnumMarkUsage Color = new EnumMarkUsage("Color");
		/**  */
		public static final EnumMarkUsage PaperPath = new EnumMarkUsage("PaperPath");
		/**  */
		public static final EnumMarkUsage Tile = new EnumMarkUsage("Tile");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Center ---------------------------------------------------------------------
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
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getCenter()
	{
		final String strAttrName = getAttribute(AttributeName.CENTER, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MarkType ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MarkType
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMarkType(VString value)
	{
		setAttribute(AttributeName.MARKTYPE, value, null);
	}

	/**
	 * (21) get VString attribute MarkType
	 *
	 * @return VString the value of the attribute
	 */
	public VString getMarkType()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.MARKTYPE, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MarkUsage ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MarkUsage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMarkUsage(EMarkUsage enumVar)
	{
		setAttribute(AttributeName.MARKUSAGE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute MarkUsage
	 *
	 * @return the value of the attribute
	 */
	public EMarkUsage getEMarkUsage()
	{
		return EMarkUsage.getEnum(getAttribute(AttributeName.MARKUSAGE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MarkUsage ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MarkUsage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setMarkUsage(EnumMarkUsage enumVar)
	{
		setAttribute(AttributeName.MARKUSAGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute MarkUsage
	 *
	 * @return the value of the attribute
	 */
	public EnumMarkUsage getMarkUsage()
	{
		return EnumMarkUsage.getEnum(getAttribute(AttributeName.MARKUSAGE, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Rotation ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Rotation
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRotation(double value)
	{
		setAttribute(AttributeName.ROTATION, value, null);
	}

	/**
	 * (17) get double attribute Rotation
	 *
	 * @return double the value of the attribute
	 */
	public double getRotation()
	{
		return getRealAttribute(AttributeName.ROTATION, null, 0.0);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element SeparationSpec
	 *
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec getSeparationSpec()
	{
		return (JDFSeparationSpec) getElement(ElementName.SEPARATIONSPEC, null, 0);
	}

	/**
	 * (25) getCreateSeparationSpec
	 * 
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec getCreateSeparationSpec()
	{
		return (JDFSeparationSpec) getCreateElement_JDFElement(ElementName.SEPARATIONSPEC, null, 0);
	}

	/**
	 * (26) getCreateSeparationSpec
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec getCreateSeparationSpec(int iSkip)
	{
		return (JDFSeparationSpec) getCreateElement_JDFElement(ElementName.SEPARATIONSPEC, null, iSkip);
	}

	/**
	 * (27) const get element SeparationSpec
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFSeparationSpec the element default is getSeparationSpec(0)
	 */
	public JDFSeparationSpec getSeparationSpec(int iSkip)
	{
		return (JDFSeparationSpec) getElement(ElementName.SEPARATIONSPEC, null, iSkip);
	}

	/**
	 * Get all SeparationSpec from the current element
	 * 
	 * @return Collection<JDFSeparationSpec>, null if none are available
	 */
	public Collection<JDFSeparationSpec> getAllSeparationSpec()
	{
		return getChildArrayByClass(JDFSeparationSpec.class, false, 0);
	}

	/**
	 * (30) append element SeparationSpec
	 *
	 * @return JDFSeparationSpec the element
	 */
	public JDFSeparationSpec appendSeparationSpec()
	{
		return (JDFSeparationSpec) appendElement(ElementName.SEPARATIONSPEC, null);
	}

}
