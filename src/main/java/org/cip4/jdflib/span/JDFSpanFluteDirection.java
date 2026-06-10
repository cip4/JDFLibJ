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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 * defines the data type dependent parts of a ranged Span resource
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
	public JDFSpanFluteDirection(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
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
	public JDFSpanFluteDirection(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
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
	public JDFSpanFluteDirection(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for EnumSpanFluteDirection
	 */

	public enum EnumSpanFluteDirection
	{
		LongEdge, ShortEdge, XDirection, YDirection;

		public static EnumSpanFluteDirection getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSpanFluteDirection.class, val, null);
		}
	}

	/**
	 * AllowedValues - vector of allowed values for this EnumerationSpan
	 *
	 * @return Vector - vector representation of the allowed values
	 */
	@Override
	public Class<? extends Enum<?>> getEnumClass()
	{
		return EnumSpanFluteDirection.class;
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
