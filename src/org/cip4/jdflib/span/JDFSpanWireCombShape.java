/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFAmount.java
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
import org.w3c.dom.DOMException;

public class JDFSpanWireCombShape extends JDFEnumerationSpan
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSpanWireCombShape
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanWireCombShape(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanWireCombShape
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanWireCombShape(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanWireCombShape
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFSpanWireCombShape(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for EnumSpanWireCombShape
	 */
	public static class EnumSpanWireCombShape extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSpanWireCombShape(String name)
		{
			super(name, m_startValue++);
		}

		public static EnumSpanWireCombShape getEnum(String enumName)
		{
			return (EnumSpanWireCombShape) getEnum(EnumSpanWireCombShape.class, enumName);
		}

		public static EnumSpanWireCombShape getEnum(int enumValue)
		{
			return (EnumSpanWireCombShape) getEnum(EnumSpanWireCombShape.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumSpanWireCombShape.class);
		}

		public static List getEnumList()
		{
			return getEnumList(EnumSpanWireCombShape.class);
		}

		public static Iterator iterator()
		{
			return iterator(EnumSpanWireCombShape.class);
		}

		public static final EnumSpanWireCombShape Single = new EnumSpanWireCombShape("Single");
		public static final EnumSpanWireCombShape Twin = new EnumSpanWireCombShape("Twin");
	}

	// **************************************** Methods
	// *********************************************

	/**
	 * AllowedValues - vector of allowed values for this EnumerationSpan
	 * 
	 * @return Vector - vector representation of the allowed values
	 */
	@Override
	public ValuedEnum getEnumType()
	{
		return EnumSpanWireCombShape.getEnum(0);
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFSpanWireCombShape[  --> " + super.toString() + " ]";
	}
}
