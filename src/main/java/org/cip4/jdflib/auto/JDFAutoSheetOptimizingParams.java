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
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFConvertingConfig;
import org.cip4.jdflib.resource.process.JDFGangElement;

/**
 *****************************************************************************
 * class JDFAutoSheetOptimizingParams : public JDFResource
 *****************************************************************************
 *
 */

public abstract class JDFAutoSheetOptimizingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.POLICY, 0x31111111, AttributeInfo.EnumAttributeType.enumeration, EnumPolicy.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.CONVERTINGCONFIG, 0x22211111);
		elemInfoTable[1] = new ElemInfoTable(ElementName.GANGELEMENT, 0x22211111);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoSheetOptimizingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoSheetOptimizingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSheetOptimizingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoSheetOptimizingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoSheetOptimizingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoSheetOptimizingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
	 * Enumeration strings for Policy
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPolicy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumPolicy(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPolicy getEnum(String enumName)
		{
			return (EnumPolicy) getEnum(EnumPolicy.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPolicy getEnum(int enumValue)
		{
			return (EnumPolicy) getEnum(EnumPolicy.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPolicy.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPolicy.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPolicy.class);
		}

		/**  */
		public static final EnumPolicy Gang = new EnumPolicy("Gang");
		/**  */
		public static final EnumPolicy GangAndForce = new EnumPolicy("GangAndForce");
		/**  */
		public static final EnumPolicy NoGang = new EnumPolicy("NoGang");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute Policy
	--------------------------------------------------------------------- */
	/**
	 * (5) set attribute Policy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPolicy(EnumPolicy enumVar)
	{
		setAttribute(AttributeName.POLICY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Policy
	 *
	 * @return the value of the attribute
	 */
	public EnumPolicy getPolicy()
	{
		return EnumPolicy.getEnum(getAttribute(AttributeName.POLICY, null, null));
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (26) getCreateConvertingConfig
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFConvertingConfig the element
	 */
	public JDFConvertingConfig getCreateConvertingConfig(int iSkip)
	{
		return (JDFConvertingConfig) getCreateElement_JDFElement(ElementName.CONVERTINGCONFIG, null, iSkip);
	}

	/**
	 * (27) const get element ConvertingConfig
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFConvertingConfig the element default is getConvertingConfig(0)
	 */
	public JDFConvertingConfig getConvertingConfig(int iSkip)
	{
		return (JDFConvertingConfig) getElement(ElementName.CONVERTINGCONFIG, null, iSkip);
	}

	/**
	 * Get all ConvertingConfig from the current element
	 *
	 * @return Collection<JDFConvertingConfig>, null if none are available
	 */
	public Collection<JDFConvertingConfig> getAllConvertingConfig()
	{
		return getChildArrayByClass(JDFConvertingConfig.class, false, 0);
	}

	/**
	 * (30) append element ConvertingConfig
	 *
	 * @return JDFConvertingConfig the element
	 */
	public JDFConvertingConfig appendConvertingConfig()
	{
		return (JDFConvertingConfig) appendElement(ElementName.CONVERTINGCONFIG, null);
	}

	/**
	 * (26) getCreateGangElement
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFGangElement the element
	 */
	public JDFGangElement getCreateGangElement(int iSkip)
	{
		return (JDFGangElement) getCreateElement_JDFElement(ElementName.GANGELEMENT, null, iSkip);
	}

	/**
	 * (27) const get element GangElement
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFGangElement the element default is getGangElement(0)
	 */
	public JDFGangElement getGangElement(int iSkip)
	{
		return (JDFGangElement) getElement(ElementName.GANGELEMENT, null, iSkip);
	}

	/**
	 * Get all GangElement from the current element
	 *
	 * @return Collection<JDFGangElement>, null if none are available
	 */
	public Collection<JDFGangElement> getAllGangElement()
	{
		return getChildArrayByClass(JDFGangElement.class, false, 0);
	}

	/**
	 * (30) append element GangElement
	 *
	 * @return JDFGangElement the element
	 */
	public JDFGangElement appendGangElement()
	{
		return (JDFGangElement) appendElement(ElementName.GANGELEMENT, null);
	}

}
