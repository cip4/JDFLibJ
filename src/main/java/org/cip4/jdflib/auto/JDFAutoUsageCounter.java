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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoUsageCounter : public JDFResource
 */

public abstract class JDFAutoUsageCounter extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.COUNTERID, 0x3333333111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.SCOPE, 0x2222222111l, AttributeInfo.EnumAttributeType.enumeration, EnumScope.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.COUNTERTYPES, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoUsageCounter
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoUsageCounter(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoUsageCounter
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoUsageCounter(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoUsageCounter
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoUsageCounter(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
		setResourceClass(JDFResource.EnumResourceClass.Consumable);
		return bRet;
	}

	/**
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Consumable;
	}

	/**
	 * Enumeration strings for Scope
	 */

	public enum EScope
	{
		Lifetime, PowerOn, Job;

		public static EScope getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EScope.class, val, null);
		}
	}

	/**
	 * Enumeration strings for Scope
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumScope extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumScope(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumScope getEnum(String enumName)
		{
			return (EnumScope) getEnum(EnumScope.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumScope getEnum(int enumValue)
		{
			return (EnumScope) getEnum(EnumScope.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumScope.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumScope.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumScope.class);
		}

		/**  */
		public static final EnumScope Lifetime = new EnumScope("Lifetime");
		/**  */
		public static final EnumScope PowerOn = new EnumScope("PowerOn");
		/**  */
		public static final EnumScope Job = new EnumScope("Job");
	}

	/*
	 * ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CounterID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CounterID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCounterID(String value)
	{
		setAttribute(AttributeName.COUNTERID, value, null);
	}

	/**
	 * (23) get String attribute CounterID
	 *
	 * @return the value of the attribute
	 */
	public String getCounterID()
	{
		return getAttribute(AttributeName.COUNTERID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Scope
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Scope
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setScope(EScope enumVar)
	{
		setAttribute(AttributeName.SCOPE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Scope
	 *
	 * @return the value of the attribute
	 */
	public EScope getEScope()
	{
		return EScope.getEnum(getAttribute(AttributeName.SCOPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Scope
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Scope
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetScope(EScope) based on java.lang.enum instead
	 */
	@Deprecated
	public void setScope(EnumScope enumVar)
	{
		setAttribute(AttributeName.SCOPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Scope
	 *
	 * @return the value of the attribute
	 * @deprecated use EScope GetEScope() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumScope getScope()
	{
		return EnumScope.getEnum(getAttribute(AttributeName.SCOPE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute CounterTypes
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CounterTypes
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCounterTypes(VString value)
	{
		setAttribute(AttributeName.COUNTERTYPES, value, null);
	}

	/**
	 * (21) get VString attribute CounterTypes
	 *
	 * @return VString the value of the attribute
	 */
	public VString getCounterTypes()
	{
		VString vStrAttrib = new VString();
		String s = getAttribute(AttributeName.COUNTERTYPES, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

}
