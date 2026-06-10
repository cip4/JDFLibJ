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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.util.JavaEnumUtil;
import org.w3c.dom.DOMException;

public class JDFSpanSurplusHandling extends JDFEnumerationSpan
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSpanSurplusHandling
	 *
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanSurplusHandling(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanSurplusHandling
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanSurplusHandling(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanSurplusHandling
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFSpanSurplusHandling(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for EnumSpanSurplusHandling
	 */

	public enum EnumSpanSurplusHandling
	{
		ReturnWithProduct, Return, Pickup, Destroy, PrinterOwns, Store;

		public static EnumSpanSurplusHandling getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSpanSurplusHandling.class, val, null);
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
		return EnumSpanSurplusHandling.class;
	}

	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFSpanSurplusHandling[  --> " + super.toString() + " ]";
	}
}
