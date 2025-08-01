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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.resource.process.JDFStack;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoLogicalStackParams : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoLogicalStackParams extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.MAXSTACKDEPTH, 0x3333331111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.RESTRICTIONS, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration, EnumRestrictions.getEnum(0), "None");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.STACK, 0x2222221111l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoLogicalStackParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoLogicalStackParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLogicalStackParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoLogicalStackParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoLogicalStackParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoLogicalStackParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for Restrictions
	 */

	public enum ERestrictions
	{
		None, WithinImposedSheetSet, WithinLogicalStack;

		public static ERestrictions getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ERestrictions.class, val, ERestrictions.None);
		}
	}

	/**
	 * Enumeration strings for Restrictions
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumRestrictions extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumRestrictions(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumRestrictions getEnum(String enumName)
		{
			return (EnumRestrictions) getEnum(EnumRestrictions.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumRestrictions getEnum(int enumValue)
		{
			return (EnumRestrictions) getEnum(EnumRestrictions.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumRestrictions.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumRestrictions.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumRestrictions.class);
		}

		/**  */
		public static final EnumRestrictions None = new EnumRestrictions("None");
		/**  */
		public static final EnumRestrictions WithinImposedSheetSet = new EnumRestrictions("WithinImposedSheetSet");
		/**  */
		public static final EnumRestrictions WithinLogicalStack = new EnumRestrictions("WithinLogicalStack");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MaxStackDepth
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxStackDepth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxStackDepth(int value)
	{
		setAttribute(AttributeName.MAXSTACKDEPTH, value, null);
	}

	/**
	 * (15) get int attribute MaxStackDepth
	 *
	 * @return int the value of the attribute
	 */
	public int getMaxStackDepth()
	{
		return getIntAttribute(AttributeName.MAXSTACKDEPTH, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Restrictions
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Restrictions
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setRestrictions(ERestrictions enumVar)
	{
		setAttribute(AttributeName.RESTRICTIONS, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute Restrictions
	 *
	 * @return the value of the attribute
	 */
	public ERestrictions getERestrictions()
	{
		return ERestrictions.getEnum(getAttribute(AttributeName.RESTRICTIONS, null, "None"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Restrictions
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Restrictions
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setRestrictions(EnumRestrictions enumVar)
	{
		setAttribute(AttributeName.RESTRICTIONS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Restrictions
	 *
	 * @return the value of the attribute
	 */
	public EnumRestrictions getRestrictions()
	{
		return EnumRestrictions.getEnum(getAttribute(AttributeName.RESTRICTIONS, null, "None"));
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element Stack
	 *
	 * @return JDFStack the element
	 */
	public JDFStack getStack()
	{
		return (JDFStack) getElement(ElementName.STACK, null, 0);
	}

	/**
	 * (25) getCreateStack
	 * 
	 * @return JDFStack the element
	 */
	public JDFStack getCreateStack()
	{
		return (JDFStack) getCreateElement_JDFElement(ElementName.STACK, null, 0);
	}

	/**
	 * (26) getCreateStack
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFStack the element
	 */
	public JDFStack getCreateStack(int iSkip)
	{
		return (JDFStack) getCreateElement_JDFElement(ElementName.STACK, null, iSkip);
	}

	/**
	 * (27) const get element Stack
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFStack the element default is getStack(0)
	 */
	public JDFStack getStack(int iSkip)
	{
		return (JDFStack) getElement(ElementName.STACK, null, iSkip);
	}

	/**
	 * Get all Stack from the current element
	 * 
	 * @return Collection<JDFStack>, null if none are available
	 */
	public Collection<JDFStack> getAllStack()
	{
		return getChildArrayByClass(JDFStack.class, false, 0);
	}

	/**
	 * (30) append element Stack
	 *
	 * @return JDFStack the element
	 */
	public JDFStack appendStack()
	{
		return (JDFStack) appendElement(ElementName.STACK, null);
	}

}
