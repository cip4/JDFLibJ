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

public class JDFSpanScoring extends JDFEnumerationSpan
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSpanScoring
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanScoring(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanScoring
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanScoring(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanScoring
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFSpanScoring(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for EnumSpanScoring
	 */
	public static class EnumSpanScoring extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSpanScoring(String name)
		{
			super(name, m_startValue++);
		}

		public static EnumSpanScoring getEnum(String enumName)
		{
			return (EnumSpanScoring) getEnum(EnumSpanScoring.class, enumName);
		}

		public static EnumSpanScoring getEnum(int enumValue)
		{
			return (EnumSpanScoring) getEnum(EnumSpanScoring.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumSpanScoring.class);
		}

		public static List getEnumList()
		{
			return getEnumList(EnumSpanScoring.class);
		}

		public static Iterator iterator()
		{
			return iterator(EnumSpanScoring.class);
		}

		public static final EnumSpanScoring TwiceScored = new EnumSpanScoring("TwiceScored");
		public static final EnumSpanScoring QuadScored = new EnumSpanScoring("QuadScored");
		public static final EnumSpanScoring None = new EnumSpanScoring("None");

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
		return EnumSpanScoring.getEnum(0);
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFSpanScoring[  --> " + super.toString() + " ]";
	}
}
