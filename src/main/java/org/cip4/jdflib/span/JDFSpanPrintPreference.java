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

public class JDFSpanPrintPreference extends JDFEnumerationSpan
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSpanPrintPreference
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanPrintPreference(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanPrintPreference
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanPrintPreference(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanPrintPreference
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFSpanPrintPreference(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for EnumSpanPrintPreference
	 */
	public static class EnumSpanPrintPreference extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSpanPrintPreference(String name)
		{
			super(name, m_startValue++);
		}

		public static EnumSpanPrintPreference getEnum(String enumName)
		{
			return (EnumSpanPrintPreference) getEnum(EnumSpanPrintPreference.class, enumName);
		}

		public static EnumSpanPrintPreference getEnum(int enumValue)
		{
			return (EnumSpanPrintPreference) getEnum(EnumSpanPrintPreference.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumSpanPrintPreference.class);
		}

		public static List getEnumList()
		{
			return getEnumList(EnumSpanPrintPreference.class);
		}

		public static Iterator iterator()
		{
			return iterator(EnumSpanPrintPreference.class);
		}

		public static final EnumSpanPrintPreference Fastest = new EnumSpanPrintPreference("Fastest");
		public static final EnumSpanPrintPreference Balanced = new EnumSpanPrintPreference("Balanced");
		public static final EnumSpanPrintPreference CostEffective = new EnumSpanPrintPreference("CostEffective");
		public static final EnumSpanPrintPreference HighestQuality = new EnumSpanPrintPreference("HighestQuality");

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
		return EnumSpanPrintPreference.getEnum(0);
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFSpanPrintPreference[  --> " + super.toString() + " ]";
	}
}
