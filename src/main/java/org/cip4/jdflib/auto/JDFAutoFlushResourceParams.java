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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.jmf.JDFQueueFilter;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoFlushResourceParams : public JDFElement
 *****************************************************************************
 * 
 */

public abstract class JDFAutoFlushResourceParams extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.FLUSHPOLICY, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration, EnumFlushPolicy.getEnum(0), "QueueEntry");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.QUEUEFILTER, 0x6666666611l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoFlushResourceParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoFlushResourceParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFlushResourceParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoFlushResourceParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoFlushResourceParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoFlushResourceParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for FlushPolicy
	 */

	public enum EFlushPolicy
	{
		Complete, QueueEntry, Intermediate;

		public static EFlushPolicy getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EFlushPolicy.class, val, EFlushPolicy.QueueEntry);
		}
	}

	/**
	 * Enumeration strings for FlushPolicy
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumFlushPolicy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumFlushPolicy(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumFlushPolicy getEnum(String enumName)
		{
			return (EnumFlushPolicy) getEnum(EnumFlushPolicy.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumFlushPolicy getEnum(int enumValue)
		{
			return (EnumFlushPolicy) getEnum(EnumFlushPolicy.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumFlushPolicy.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumFlushPolicy.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumFlushPolicy.class);
		}

		/**  */
		public static final EnumFlushPolicy Complete = new EnumFlushPolicy("Complete");
		/**  */
		public static final EnumFlushPolicy QueueEntry = new EnumFlushPolicy("QueueEntry");
		/**  */
		public static final EnumFlushPolicy Intermediate = new EnumFlushPolicy("Intermediate");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FlushPolicy ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FlushPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setFlushPolicy(EFlushPolicy enumVar)
	{
		setAttribute(AttributeName.FLUSHPOLICY, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute FlushPolicy
	 *
	 * @return the value of the attribute
	 */
	public EFlushPolicy getEFlushPolicy()
	{
		return EFlushPolicy.getEnum(getAttribute(AttributeName.FLUSHPOLICY, null, "QueueEntry"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FlushPolicy ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute FlushPolicy
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setFlushPolicy(EnumFlushPolicy enumVar)
	{
		setAttribute(AttributeName.FLUSHPOLICY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute FlushPolicy
	 *
	 * @return the value of the attribute
	 */
	public EnumFlushPolicy getFlushPolicy()
	{
		return EnumFlushPolicy.getEnum(getAttribute(AttributeName.FLUSHPOLICY, null, "QueueEntry"));
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element QueueFilter
	 *
	 * @return JDFQueueFilter the element
	 */
	public JDFQueueFilter getQueueFilter()
	{
		return (JDFQueueFilter) getElement(ElementName.QUEUEFILTER, null, 0);
	}

	/**
	 * (25) getCreateQueueFilter
	 * 
	 * @return JDFQueueFilter the element
	 */
	public JDFQueueFilter getCreateQueueFilter()
	{
		return (JDFQueueFilter) getCreateElement_JDFElement(ElementName.QUEUEFILTER, null, 0);
	}

	/**
	 * (29) append element QueueFilter
	 *
	 * @return JDFQueueFilter the element @ if the element already exists
	 */
	public JDFQueueFilter appendQueueFilter()
	{
		return (JDFQueueFilter) appendElementN(ElementName.QUEUEFILTER, 1, null);
	}

}
