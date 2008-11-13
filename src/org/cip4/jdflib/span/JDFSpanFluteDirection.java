/**
 *
 * Copyright (c) 2008 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSpanFluteDirection.java
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

/**
 *defines the data type dependent parts of a ranged Span resource
 */
public class JDFSpanFluteDirection extends JDFEnumerationSpan
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSpanFluteDirection
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFSpanFluteDirection(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanFluteDirection
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFSpanFluteDirection(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanFluteDirection
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFSpanFluteDirection(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for EnumSpanFluteDirection
	 */

	public static class EnumSpanFluteDirection extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSpanFluteDirection(String name)
		{
			super(name, m_startValue++);
		}

		public static EnumSpanFluteDirection getEnum(String enumName)
		{
			return (EnumSpanFluteDirection) getEnum(EnumSpanFluteDirection.class, enumName);
		}

		public static EnumSpanFluteDirection getEnum(int enumValue)
		{
			return (EnumSpanFluteDirection) getEnum(EnumSpanFluteDirection.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumSpanFluteDirection.class);
		}

		public static List getEnumList()
		{
			return getEnumList(EnumSpanFluteDirection.class);
		}

		public static Iterator iterator()
		{
			return iterator(EnumSpanFluteDirection.class);
		}

		public static final EnumSpanFluteDirection LongEdge = new EnumSpanFluteDirection("LongEdge");
		public static final EnumSpanFluteDirection ShortEdge = new EnumSpanFluteDirection("ShortEdge");
		public static final EnumSpanFluteDirection XDirection = new EnumSpanFluteDirection("XDirection");
		public static final EnumSpanFluteDirection YDirection = new EnumSpanFluteDirection("YDirection");
	}

	/**
	 * AllowedValues - vector of allowed values for this EnumerationSpan
	 * 
	 * @return Vector - vector representation of the allowed values
	 */
	@Override
	public ValuedEnum getEnumType()
	{
		return EnumSpanFluteDirection.getEnum(0);
	}

	// **************************************** Methods
	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFSpanFluteDirection[  --> " + super.toString() + " ]";
	}
}
