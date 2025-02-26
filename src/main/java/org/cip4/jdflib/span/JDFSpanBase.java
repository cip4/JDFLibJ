/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSpanBase.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.span;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.StringUtil;

/**
 * defines the data type independent parts of a ranged Span resource
 */
public abstract class JDFSpanBase extends JDFElement
{
	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.DATATYPE, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumDataType.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.PRIORITY, 0x44444433, AttributeInfo.EnumAttributeType.enumeration, EnumPriority.getEnum(0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFSpanBase
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 *
	 */
	public JDFSpanBase(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanBase
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 *
	 * @param qualifiedName
	 */
	public JDFSpanBase(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanBase
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 *
	 */
	public JDFSpanBase(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	@SuppressWarnings("unchecked")
	public static class EnumPriority extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumPriority(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName
		 * @return
		 */
		public static EnumPriority getEnum(final String enumName)
		{
			return (EnumPriority) getEnum(EnumPriority.class, enumName);
		}

		/**
		 * @param enumValue
		 * @return
		 */
		public static EnumPriority getEnum(final int enumValue)
		{
			return (EnumPriority) getEnum(EnumPriority.class, enumValue);
		}

		/**
		 * @return
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPriority.class);
		}

		/**
		 * @return
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPriority.class);
		}

		/**
		 * @return
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPriority.class);
		}

		/**
		 *
		 */
		public static final EnumPriority None = new EnumPriority(JDFConstants.PRIORITY_NONE);
		/**
		 *
		 */
		public static final EnumPriority Suggested = new EnumPriority(JDFConstants.PRIORITY_SUGGESTED);
		/**
		 *
		 */
		public static final EnumPriority Required = new EnumPriority(JDFConstants.PRIORITY_REQUIRED);
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	@SuppressWarnings("unchecked")
	public static class EnumDataType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumDataType(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName
		 * @return
		 */
		public static EnumDataType getEnum(final String enumName)
		{
			return (EnumDataType) getEnum(EnumDataType.class, enumName);
		}

		/**
		 * @param enumValue
		 * @return
		 */
		public static EnumDataType getEnum(final int enumValue)
		{
			return (EnumDataType) getEnum(EnumDataType.class, enumValue);
		}

		/**
		 * @return
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDataType.class);
		}

		/**
		 * @return
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDataType.class);
		}

		/**
		 * @return
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDataType.class);
		}

		/**
		 *
		 */
		public static final EnumDataType DurationSpan = new EnumDataType(JDFConstants.DATATYPE_DURATION);
		/**
		 *
		 */
		public static final EnumDataType IntegerSpan = new EnumDataType(JDFConstants.DATATYPE_INTEGER);
		/**
		 *
		 */
		public static final EnumDataType NumberSpan = new EnumDataType(JDFConstants.DATATYPE_NUMBER);
		/**
		 *
		 */
		public static final EnumDataType OptionSpan = new EnumDataType(JDFConstants.DATATYPE_OPTION);
		/**
		 *
		 */
		public static final EnumDataType NameSpan = new EnumDataType(JDFConstants.DATATYPE_NAME);
		/**
		 *
		 */
		public static final EnumDataType EnumerationSpan = new EnumDataType(JDFConstants.DATATYPE_ENUMERATION);
		/**
		 *
		 */
		public static final EnumDataType ShapeSpan = new EnumDataType(JDFConstants.DATATYPE_SHAPE);
		/**
		 *
		 */
		public static final EnumDataType StringSpan = new EnumDataType(JDFConstants.DATATYPE_STRING);
		/**
		 *
		 */
		public static final EnumDataType TimeSpan = new EnumDataType(JDFConstants.DATATYPE_TIME);
		/**
		 *
		 */
		public static final EnumDataType XYPairSpan = new EnumDataType(JDFConstants.DATATYPE_XYPAIR);
	}

	/**
	 * Gets the value of this Span: te value of Actual if it exists, otherwise value = Preferred + Range combination
	 *
	 * @deprecated 060302 was never correctly implemented and is unnecessary...
	 *
	 * @return String - value as a String
	 */
	@Deprecated
	public final String getValue()
	{
		return null;
	}

	/**
	 * Sets the value of attribute DataType
	 *
	 * @param value
	 *
	 */
	public void setDataType(final EnumDataType value)
	{
		setAttribute(AttributeName.DATATYPE, value, null);
	}

	/**
	 * Gets the value of attribute DataType
	 *
	 * @return EnumDataType - the attribute DataType value
	 */
	public EnumDataType getDataType()
	{
		return EnumDataType.getEnum(getAttribute(AttributeName.DATATYPE, null, null));
	}

	/**
	 * GetPriority
	 *
	 * @return EnumPriority
	 */
	public EnumPriority getPriority()
	{
		return EnumPriority.getEnum(getAttribute(AttributeName.PRIORITY, null, null));
	}

	/**
	 * SetPriority
	 *
	 * @param p
	 */
	public void setPriority(final EnumPriority p)
	{
		setAttribute(AttributeName.PRIORITY, p, null);
	}

	/**
	 * GetName
	 *
	 * @deprecated 060301 use getNodeName or getLocalName
	 * @return String
	 */
	@Deprecated
	public String getName()
	{
		return getNodeName();
	}

	/**
	 * PreferredToActual
	 *
	 * @return boolean
	 */
	public boolean preferredToActual()
	{
		final boolean preferredExists = !hasAttribute(AttributeName.ACTUAL) && hasAttribute(AttributeName.PREFERRED);

		if (preferredExists)
		{
			setAttribute(AttributeName.ACTUAL, guessActual(), null);
		}
		return preferredExists;
	}

	/**
	 * guess the value of Actual from actualrange, preferred etc,
	 *
	 * @return
	 */
	public String guessActual()
	{
		if (hasAttribute(AttributeName.ACTUAL))
			return getAttribute(AttributeName.ACTUAL);
		if (hasAttribute(AttributeName.PREFERRED))
			return getAttribute(AttributeName.PREFERRED);
		if (hasAttribute(AttributeName.RANGE))
		{
			final String range = getAttribute(AttributeName.RANGE);
			final VString v = StringUtil.tokenize(range, "~ ", false);
			final int n = v.size() / 2;
			if (n > 0)
			{
				return v.get(n);
			}
		}
		return null;
	}

	/**
	 * toString - StringRepresentation of JDFNode
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + " -->" + super.toString() + " ]";
	}
}
