/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSpanCoatings.java
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
public class JDFSpanCoatings extends JDFEnumerationSpan
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSpanCoatings
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFSpanCoatings(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanCoatings
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFSpanCoatings(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanCoatings
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFSpanCoatings(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for EnumSpanCoatings
	 */

	public static class EnumSpanCoatings extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSpanCoatings(String name)
		{
			super(name, m_startValue++);
		}

		public static EnumSpanCoatings getEnum(String enumName)
		{
			return (EnumSpanCoatings) getEnum(EnumSpanCoatings.class, enumName);
		}

		public static EnumSpanCoatings getEnum(int enumValue)
		{
			return (EnumSpanCoatings) getEnum(EnumSpanCoatings.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumSpanCoatings.class);
		}

		public static List getEnumList()
		{
			return getEnumList(EnumSpanCoatings.class);
		}

		public static Iterator iterator()
		{
			return iterator(EnumSpanCoatings.class);
		}

		public static final EnumSpanCoatings None = new EnumSpanCoatings("None");
		public static final EnumSpanCoatings Coated = new EnumSpanCoatings("Coated");
		public static final EnumSpanCoatings Glossy = new EnumSpanCoatings("Glossy");
		public static final EnumSpanCoatings HighGloss = new EnumSpanCoatings("HighGloss");
		public static final EnumSpanCoatings InkJet = new EnumSpanCoatings("InkJet");
		public static final EnumSpanCoatings Matte = new EnumSpanCoatings("Matte");
		public static final EnumSpanCoatings Satin = new EnumSpanCoatings("Satin");
		public static final EnumSpanCoatings Semigloss = new EnumSpanCoatings("Semigloss");

	}

	/**
	 * AllowedValues - vector of allowed values for this EnumerationSpan
	 * 
	 * @return Vector - vector representation of the allowed values
	 */
	@Override
	public ValuedEnum getEnumType()
	{
		return EnumSpanCoatings.getEnum(0);
	}

	// **************************************** Methods
	// *********************************************

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFSpanCoatings[  --> " + super.toString() + " ]";
	}
}
