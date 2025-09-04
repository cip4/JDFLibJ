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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoWrappingParams : public JDFResource
 */

public abstract class JDFAutoWrappingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.WRAPPINGKIND, 0x3333333331l, AttributeInfo.EnumAttributeType.enumeration, EnumWrappingKind.getEnum(0),
				null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoWrappingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoWrappingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoWrappingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoWrappingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoWrappingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoWrappingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for WrappingKind
	 */

	public enum EWrappingKind
	{
		Band, LooseWrap, ShrinkWrap;

		public static EWrappingKind getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EWrappingKind.class, val, null);
		}
	}

	/**
	 * Enumeration strings for WrappingKind
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumWrappingKind extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumWrappingKind(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumWrappingKind getEnum(String enumName)
		{
			return (EnumWrappingKind) getEnum(EnumWrappingKind.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumWrappingKind getEnum(int enumValue)
		{
			return (EnumWrappingKind) getEnum(EnumWrappingKind.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumWrappingKind.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumWrappingKind.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumWrappingKind.class);
		}

		/**  */
		public static final EnumWrappingKind Band = new EnumWrappingKind("Band");
		/**  */
		public static final EnumWrappingKind LooseWrap = new EnumWrappingKind("LooseWrap");
		/**  */
		public static final EnumWrappingKind ShrinkWrap = new EnumWrappingKind("ShrinkWrap");
	}

	/*
	 * ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute WrappingKind
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute WrappingKind
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setWrappingKind(EWrappingKind enumVar)
	{
		setAttribute(AttributeName.WRAPPINGKIND, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute WrappingKind
	 *
	 * @return the value of the attribute
	 */
	public EWrappingKind getEWrappingKind()
	{
		return EWrappingKind.getEnum(getAttribute(AttributeName.WRAPPINGKIND, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute WrappingKind
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute WrappingKind
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetWrappingKind(EWrappingKind) based on java.lang.enum instead
	 */
	@Deprecated
	public void setWrappingKind(EnumWrappingKind enumVar)
	{
		setAttribute(AttributeName.WRAPPINGKIND, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute WrappingKind
	 *
	 * @return the value of the attribute
	 * @deprecated use EWrappingKind GetEWrappingKind() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumWrappingKind getWrappingKind()
	{
		return EnumWrappingKind.getEnum(getAttribute(AttributeName.WRAPPINGKIND, null, null));
	}

}
