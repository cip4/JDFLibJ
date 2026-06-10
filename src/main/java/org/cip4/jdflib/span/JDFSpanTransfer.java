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

public class JDFSpanTransfer extends JDFEnumerationSpan
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSpanTransfer
	 *
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanTransfer(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanTransfer
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanTransfer(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanTransfer
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFSpanTransfer(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for EnumSpanTransfer
	 */

	public enum EnumSpanTransfer
	{
		BuyerToPrinterDeliver, BuyerToPrinterPickup;

		public static EnumSpanTransfer getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSpanTransfer.class, val, null);
		}

	}

	@Override
	public Class<? extends Enum<?>> getEnumClass()
	{
		return EnumSpanTransfer.class;
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
		return "JDFSpanTransfer[  --> " + super.toString() + " ]";
	}
}
