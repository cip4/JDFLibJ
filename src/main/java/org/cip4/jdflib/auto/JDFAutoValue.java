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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.resource.devicecapability.JDFLoc;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoValue : public JDFElement
 */

public abstract class JDFAutoValue extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ALLOWEDVALUE, 0x2222222221l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.PRESENTVALUE, 0x4444444431l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.VALUEUSAGE, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumValueUsage.getEnum(0),
				null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.VALUE, 0x2222222222l, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.LOC, 0x3333333331l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoValue
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoValue(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoValue
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoValue(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoValue
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoValue(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for ValueUsage
	 */

	public enum EValueUsage
	{
		Allowed, Device, Present, Job, Estimate;

		public static EValueUsage getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EValueUsage.class, val, null);
		}
	}

	/**
	 * Enumeration strings for ValueUsage
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumValueUsage extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumValueUsage(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumValueUsage getEnum(String enumName)
		{
			return (EnumValueUsage) getEnum(EnumValueUsage.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumValueUsage getEnum(int enumValue)
		{
			return (EnumValueUsage) getEnum(EnumValueUsage.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumValueUsage.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumValueUsage.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumValueUsage.class);
		}

		/**  */
		public static final EnumValueUsage Allowed = new EnumValueUsage("Allowed");
		/**  */
		public static final EnumValueUsage Device = new EnumValueUsage("Device");
		/**  */
		public static final EnumValueUsage Present = new EnumValueUsage("Present");
		/**  */
		public static final EnumValueUsage Job = new EnumValueUsage("Job");
		/**  */
		public static final EnumValueUsage Estimate = new EnumValueUsage("Estimate");
	}

	/*
	 * ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute AllowedValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute AllowedValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setAllowedValue(String value)
	{
		setAttribute(AttributeName.ALLOWEDVALUE, value, null);
	}

	/**
	 * (23) get String attribute AllowedValue
	 *
	 * @return the value of the attribute
	 */
	public String getAllowedValue()
	{
		return getAttribute(AttributeName.ALLOWEDVALUE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PresentValue
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PresentValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setPresentValue(String value)
	{
		setAttribute(AttributeName.PRESENTVALUE, value, null);
	}

	/**
	 * (23) get String attribute PresentValue
	 *
	 * @return the value of the attribute
	 */
	public String getPresentValue()
	{
		return getAttribute(AttributeName.PRESENTVALUE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ValueUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ValueUsage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setValueUsage(EValueUsage enumVar)
	{
		setAttribute(AttributeName.VALUEUSAGE, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute ValueUsage
	 *
	 * @return the value of the attribute
	 */
	public EValueUsage getEValueUsage()
	{
		return EValueUsage.getEnum(getAttribute(AttributeName.VALUEUSAGE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute ValueUsage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ValueUsage
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use SetValueUsage(EValueUsage) based on java.lang.enum instead
	 */
	@Deprecated
	public void setValueUsage(EnumValueUsage enumVar)
	{
		setAttribute(AttributeName.VALUEUSAGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute ValueUsage
	 *
	 * @return the value of the attribute
	 * @deprecated use EValueUsage GetEValueUsage() based on java.lang.enum instead
	 */
	@Deprecated
	public EnumValueUsage getValueUsage()
	{
		return EnumValueUsage.getEnum(getAttribute(AttributeName.VALUEUSAGE, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Value
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Value
	 *
	 * @param value the value to set the attribute to
	 */
	public void setValue(String value)
	{
		setAttribute(AttributeName.VALUE, value, null);
	}

	/**
	 * (23) get String attribute Value
	 *
	 * @return the value of the attribute
	 */
	public String getValue()
	{
		return getAttribute(AttributeName.VALUE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element Loc
	 *
	 * @return JDFLoc the element
	 */
	public JDFLoc getLoc()
	{
		return (JDFLoc) getElement(ElementName.LOC, null, 0);
	}

	/**
	 * (25) getCreateLoc
	 * 
	 * @return JDFLoc the element
	 */
	public JDFLoc getCreateLoc()
	{
		return (JDFLoc) getCreateElement_JDFElement(ElementName.LOC, null, 0);
	}

	/**
	 * (26) getCreateLoc
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFLoc the element
	 */
	public JDFLoc getCreateLoc(int iSkip)
	{
		return (JDFLoc) getCreateElement_JDFElement(ElementName.LOC, null, iSkip);
	}

	/**
	 * (27) const get element Loc
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFLoc the element
	 *         default is getLoc(0)
	 */
	public JDFLoc getLoc(int iSkip)
	{
		return (JDFLoc) getElement(ElementName.LOC, null, iSkip);
	}

	/**
	 * Get all Loc from the current element
	 * 
	 * @return Collection<JDFLoc>, null if none are available
	 */
	public Collection<JDFLoc> getAllLoc()
	{
		return getChildArrayByClass(JDFLoc.class, false, 0);
	}

	/**
	 * (30) append element Loc
	 *
	 * @return JDFLoc the element
	 */
	public JDFLoc appendLoc()
	{
		return (JDFLoc) appendElement(ElementName.LOC, null);
	}

}
