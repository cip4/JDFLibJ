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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VString;

/**
*****************************************************************************
class JDFAutoJDFService : public JDFElement

*****************************************************************************
*/

public abstract class JDFAutoJDFService extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.COMBINEDMETHOD, 0x44444431, AttributeInfo.EnumAttributeType.enumeration, EnumCombinedMethod.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.TYPE, 0x44444422, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.TYPEORDER, 0x44444431, AttributeInfo.EnumAttributeType.enumeration, EnumTypeOrder.getEnum(0), null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.TYPES, 0x44444433, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAutoJDFService
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoJDFService(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoJDFService
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoJDFService(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoJDFService
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoJDFService(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return  the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoJDFService[  --> " + super.toString() + " ]";
	}

	/**
	* Enumeration strings for CombinedMethod
	*/

	@SuppressWarnings("rawtypes")
	public static class EnumCombinedMethod extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumCombinedMethod(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumCombinedMethod getEnum(String enumName)
		{
			return (EnumCombinedMethod) getEnum(EnumCombinedMethod.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumCombinedMethod getEnum(int enumValue)
		{
			return (EnumCombinedMethod) getEnum(EnumCombinedMethod.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumCombinedMethod.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumCombinedMethod.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumCombinedMethod.class);
		}

		/**  */
		public static final EnumCombinedMethod Combined = new EnumCombinedMethod("Combined");
		/**  */
		public static final EnumCombinedMethod CombinedProcessGroup = new EnumCombinedMethod("CombinedProcessGroup");
		/**  */
		public static final EnumCombinedMethod GrayBox = new EnumCombinedMethod("GrayBox");
		/**  */
		public static final EnumCombinedMethod ProcessGroup = new EnumCombinedMethod("ProcessGroup");
		/**  */
		public static final EnumCombinedMethod None = new EnumCombinedMethod("None");
	}

	/**
	* Enumeration strings for TypeOrder
	*/

	@SuppressWarnings("rawtypes")
	public static class EnumTypeOrder extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumTypeOrder(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumTypeOrder getEnum(String enumName)
		{
			return (EnumTypeOrder) getEnum(EnumTypeOrder.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumTypeOrder getEnum(int enumValue)
		{
			return (EnumTypeOrder) getEnum(EnumTypeOrder.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumTypeOrder.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumTypeOrder.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumTypeOrder.class);
		}

		/**  */
		public static final EnumTypeOrder Fixed = new EnumTypeOrder("Fixed");
		/**  */
		public static final EnumTypeOrder Unordered = new EnumTypeOrder("Unordered");
		/**  */
		public static final EnumTypeOrder Unrestricted = new EnumTypeOrder("Unrestricted");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute CombinedMethod
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute CombinedMethod
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setCombinedMethod(EnumCombinedMethod enumVar)
	{
		setAttribute(AttributeName.COMBINEDMETHOD, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute CombinedMethod
	  * @return the value of the attribute
	  */
	public EnumCombinedMethod getCombinedMethod()
	{
		return EnumCombinedMethod.getEnum(getAttribute(AttributeName.COMBINEDMETHOD, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Type
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Type
	  * @param value the value to set the attribute to
	  */
	public void setType(String value)
	{
		setAttribute(AttributeName.TYPE, value, null);
	}

	/**
	  * (23) get String attribute Type
	  * @return the value of the attribute
	  */
	public String getType()
	{
		return getAttribute(AttributeName.TYPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute TypeOrder
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute TypeOrder
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setTypeOrder(EnumTypeOrder enumVar)
	{
		setAttribute(AttributeName.TYPEORDER, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute TypeOrder
	  * @return the value of the attribute
	  */
	public EnumTypeOrder getTypeOrder()
	{
		return EnumTypeOrder.getEnum(getAttribute(AttributeName.TYPEORDER, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Types
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Types
	  * @param value the value to set the attribute to
	  */
	public void setTypes(VString value)
	{
		setAttribute(AttributeName.TYPES, value, null);
	}

	/**
	  * (21) get VString attribute Types
	  * @return VString the value of the attribute
	  */
	public VString getTypes()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.TYPES, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

}// end namespace JDF
