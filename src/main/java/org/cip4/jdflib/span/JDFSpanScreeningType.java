/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSpanScreeningType.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.span;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.util.JavaEnumUtil;
import org.w3c.dom.DOMException;

/**
 * time range class
 */
public class JDFSpanScreeningType extends JDFEnumerationSpan
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSpanScreeningType
	 *
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanScreeningType(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanScreeningType
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanScreeningType(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanScreeningType
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFSpanScreeningType(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for EnumSpanScreeningType
	 */
	public enum EnumSpanScreeningType
	{
		AM, FM;

		public static EnumSpanScreeningType getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSpanScreeningType.class, val, null);
		}

	}

	// **************************************** Methods
	// *********************************************

	/**
	 * AllowedValues - vector of allowed values for this EnumerationSpan
	 *
	 * @return Vector - vector representation of the allowed values
	 */
	@Override
	public Class<? extends Enum<?>> getEnumClass()
	{
		return EnumSpanScreeningType.class;
	}

	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFSpanScreeningType[ --> " + super.toString() + " ]";
	}

}
