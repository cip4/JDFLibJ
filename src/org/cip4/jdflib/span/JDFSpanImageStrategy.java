/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSpanImageStrategy.java
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

/**
 * time range class
 */
public class JDFSpanImageStrategy extends JDFEnumerationSpan
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSpanImageStrategy
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanImageStrategy(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanImageStrategy
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanImageStrategy(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanImageStrategy
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFSpanImageStrategy(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for EnumSpanImageStrategy
	 */
	public static class EnumSpanImageStrategy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSpanImageStrategy(String name)
		{
			super(name, m_startValue++);
		}

		public static EnumSpanImageStrategy getEnum(String enumName)
		{
			return (EnumSpanImageStrategy) getEnum(EnumSpanImageStrategy.class, enumName);
		}

		public static EnumSpanImageStrategy getEnum(int enumValue)
		{
			return (EnumSpanImageStrategy) getEnum(EnumSpanImageStrategy.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumSpanImageStrategy.class);
		}

		public static List getEnumList()
		{
			return getEnumList(EnumSpanImageStrategy.class);
		}

		public static Iterator iterator()
		{
			return iterator(EnumSpanImageStrategy.class);
		}

		public static final EnumSpanImageStrategy NoImages = new EnumSpanImageStrategy("NoImages");
		public static final EnumSpanImageStrategy LowResolution = new EnumSpanImageStrategy("LowResolution");
		public static final EnumSpanImageStrategy HighResolution = new EnumSpanImageStrategy("HighResolution");

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
		return EnumSpanImageStrategy.getEnum(0);
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFSpanImageStrategy[ --> " + super.toString() + " ]";
	}

	@Override
	public boolean init()
	{
		boolean b = super.init();
		setDataType(EnumDataType.EnumerationSpan);
		return b;
	}
}
