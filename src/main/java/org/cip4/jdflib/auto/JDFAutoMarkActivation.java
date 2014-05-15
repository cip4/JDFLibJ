/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;

/**
*****************************************************************************
class JDFAutoMarkActivation : public JDFElement

*****************************************************************************
*/

public abstract class JDFAutoMarkActivation extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CONTEXT, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumContext.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.INDEX, 0x22222222, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoMarkActivation
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoMarkActivation(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoMarkActivation
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoMarkActivation(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoMarkActivation
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoMarkActivation(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return  the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoMarkActivation[  --> " + super.toString() + " ]";
	}

	/**
	* Enumeration strings for Context
	*/

	@SuppressWarnings("rawtypes")
	public static class EnumContext extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumContext(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumContext getEnum(String enumName)
		{
			return (EnumContext) getEnum(EnumContext.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumContext getEnum(int enumValue)
		{
			return (EnumContext) getEnum(EnumContext.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumContext.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumContext.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumContext.class);
		}

		/**  */
		public static final EnumContext CollectIndex = new EnumContext("CollectIndex");
		/**  */
		public static final EnumContext DocIndex = new EnumContext("DocIndex");
		/**  */
		public static final EnumContext SetDocIndex = new EnumContext("SetDocIndex");
		/**  */
		public static final EnumContext SetIndex = new EnumContext("SetIndex");
		/**  */
		public static final EnumContext SheetIndex = new EnumContext("SheetIndex");
		/**  */
		public static final EnumContext SubDocIndex0 = new EnumContext("SubDocIndex0");
		/**  */
		public static final EnumContext SubDocIndex1 = new EnumContext("SubDocIndex1");
		/**  */
		public static final EnumContext SubDocIndex2 = new EnumContext("SubDocIndex2");
		/**  */
		public static final EnumContext SubDocIndex3 = new EnumContext("SubDocIndex3");
		/**  */
		public static final EnumContext SubDocIndex4 = new EnumContext("SubDocIndex4");
		/**  */
		public static final EnumContext SubDocIndex5 = new EnumContext("SubDocIndex5");
		/**  */
		public static final EnumContext SubDocIndex6 = new EnumContext("SubDocIndex6");
		/**  */
		public static final EnumContext SubDocIndex7 = new EnumContext("SubDocIndex7");
		/**  */
		public static final EnumContext SubDocIndex8 = new EnumContext("SubDocIndex8");
		/**  */
		public static final EnumContext SubDocIndex9 = new EnumContext("SubDocIndex9");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute Context
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute Context
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setContext(EnumContext enumVar)
	{
		setAttribute(AttributeName.CONTEXT, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute Context
	  * @return the value of the attribute
	  */
	public EnumContext getContext()
	{
		return EnumContext.getEnum(getAttribute(AttributeName.CONTEXT, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Index
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Index
	  * @param value the value to set the attribute to
	  */
	public void setIndex(JDFIntegerRangeList value)
	{
		setAttribute(AttributeName.INDEX, value, null);
	}

	/**
	  * (20) get JDFIntegerRangeList attribute Index
	  * @return JDFIntegerRangeList the value of the attribute, null if a the
	  *         attribute value is not a valid to create a JDFIntegerRangeList
	  */
	public JDFIntegerRangeList getIndex()
	{
		final String strAttrName = getAttribute(AttributeName.INDEX, null, null);
		final JDFIntegerRangeList nPlaceHolder = JDFIntegerRangeList.createIntegerRangeList(strAttrName);
		return nPlaceHolder;
	}

}// end namespace JDF
