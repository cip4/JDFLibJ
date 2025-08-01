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
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResourceParam;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoResourceDefinitionParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoResourceDefinitionParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DEFAULTPRIORITY, 0x3333333333l, AttributeInfo.EnumAttributeType.enumeration, EnumDefaultPriority.getEnum(0), "DefaultJDF");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DEFAULTID, 0x4444444443l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DEFAULTJDF, 0x3333333333l, AttributeInfo.EnumAttributeType.URL, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.RESOURCEPARAM, 0x3333333331l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoResourceDefinitionParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoResourceDefinitionParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoResourceDefinitionParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoResourceDefinitionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoResourceDefinitionParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoResourceDefinitionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for DefaultPriority
	 */

	public enum EDefaultPriority
	{
		Application, DefaultJDF;

		public static EDefaultPriority getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EDefaultPriority.class, val, EDefaultPriority.DefaultJDF);
		}
	}

	/**
	 * Enumeration strings for DefaultPriority
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumDefaultPriority extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumDefaultPriority(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumDefaultPriority getEnum(String enumName)
		{
			return (EnumDefaultPriority) getEnum(EnumDefaultPriority.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumDefaultPriority getEnum(int enumValue)
		{
			return (EnumDefaultPriority) getEnum(EnumDefaultPriority.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDefaultPriority.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDefaultPriority.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDefaultPriority.class);
		}

		/**  */
		public static final EnumDefaultPriority Application = new EnumDefaultPriority("Application");
		/**  */
		public static final EnumDefaultPriority DefaultJDF = new EnumDefaultPriority("DefaultJDF");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DefaultPriority
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DefaultPriority
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDefaultPriority(EDefaultPriority enumVar)
	{
		setAttribute(AttributeName.DEFAULTPRIORITY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute DefaultPriority
	 *
	 * @return the value of the attribute
	 */
	public EDefaultPriority getEDefaultPriority()
	{
		return EDefaultPriority.getEnum(getAttribute(AttributeName.DEFAULTPRIORITY, null, "DefaultJDF"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DefaultPriority
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DefaultPriority
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setDefaultPriority(EnumDefaultPriority enumVar)
	{
		setAttribute(AttributeName.DEFAULTPRIORITY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute DefaultPriority
	 *
	 * @return the value of the attribute
	 */
	public EnumDefaultPriority getDefaultPriority()
	{
		return EnumDefaultPriority.getEnum(getAttribute(AttributeName.DEFAULTPRIORITY, null, "DefaultJDF"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DefaultID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DefaultID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDefaultID(String value)
	{
		setAttribute(AttributeName.DEFAULTID, value, null);
	}

	/**
	 * (23) get String attribute DefaultID
	 *
	 * @return the value of the attribute
	 */
	public String getDefaultID()
	{
		return getAttribute(AttributeName.DEFAULTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DefaultJDF ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute DefaultJDF
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDefaultJDF(String value)
	{
		setAttribute(AttributeName.DEFAULTJDF, value, null);
	}

	/**
	 * (23) get String attribute DefaultJDF
	 *
	 * @return the value of the attribute
	 */
	public String getDefaultJDF()
	{
		return getAttribute(AttributeName.DEFAULTJDF, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element ResourceParam
	 *
	 * @return JDFResourceParam the element
	 */
	public JDFResourceParam getResourceParam()
	{
		return (JDFResourceParam) getElement(ElementName.RESOURCEPARAM, null, 0);
	}

	/**
	 * (25) getCreateResourceParam
	 * 
	 * @return JDFResourceParam the element
	 */
	public JDFResourceParam getCreateResourceParam()
	{
		return (JDFResourceParam) getCreateElement_JDFElement(ElementName.RESOURCEPARAM, null, 0);
	}

	/**
	 * (26) getCreateResourceParam
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFResourceParam the element
	 */
	public JDFResourceParam getCreateResourceParam(int iSkip)
	{
		return (JDFResourceParam) getCreateElement_JDFElement(ElementName.RESOURCEPARAM, null, iSkip);
	}

	/**
	 * (27) const get element ResourceParam
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFResourceParam the element default is getResourceParam(0)
	 */
	public JDFResourceParam getResourceParam(int iSkip)
	{
		return (JDFResourceParam) getElement(ElementName.RESOURCEPARAM, null, iSkip);
	}

	/**
	 * Get all ResourceParam from the current element
	 * 
	 * @return Collection<JDFResourceParam>, null if none are available
	 */
	public Collection<JDFResourceParam> getAllResourceParam()
	{
		return getChildArrayByClass(JDFResourceParam.class, false, 0);
	}

	/**
	 * (30) append element ResourceParam
	 *
	 * @return JDFResourceParam the element
	 */
	public JDFResourceParam appendResourceParam()
	{
		return (JDFResourceParam) appendElement(ElementName.RESOURCEPARAM, null);
	}

}
