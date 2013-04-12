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

public class JDFSpanTemperature extends JDFEnumerationSpan
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSpanTemperature
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanTemperature(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanTemperature
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanTemperature(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanTemperature
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFSpanTemperature(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for EnumSpanTemperature
	 */
	public static class EnumSpanTemperature extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSpanTemperature(String name)
		{
			super(name, m_startValue++);
		}

		public static EnumSpanTemperature getEnum(String enumName)
		{
			return (EnumSpanTemperature) getEnum(EnumSpanTemperature.class, enumName);
		}

		public static EnumSpanTemperature getEnum(int enumValue)
		{
			return (EnumSpanTemperature) getEnum(EnumSpanTemperature.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumSpanTemperature.class);
		}

		public static List getEnumList()
		{
			return getEnumList(EnumSpanTemperature.class);
		}

		public static Iterator iterator()
		{
			return iterator(EnumSpanTemperature.class);
		}

		public static final EnumSpanTemperature Hot = new EnumSpanTemperature("Hot");
		public static final EnumSpanTemperature Cold = new EnumSpanTemperature("Cold");

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
		return EnumSpanTemperature.getEnum(0);
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFSpanTemperature[  --> " + super.toString() + " ]";
	}
}
