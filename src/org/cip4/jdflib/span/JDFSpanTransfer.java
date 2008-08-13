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
	public JDFSpanTransfer(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
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
	public JDFSpanTransfer(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
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
	public JDFSpanTransfer(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for EnumSpanTransfer
	 */

	public static class EnumSpanTransfer extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSpanTransfer(String name)
		{
			super(name, m_startValue++);
		}

		public static EnumSpanTransfer getEnum(String enumName)
		{
			return (EnumSpanTransfer) getEnum(EnumSpanTransfer.class, enumName);
		}

		public static EnumSpanTransfer getEnum(int enumValue)
		{
			return (EnumSpanTransfer) getEnum(EnumSpanTransfer.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumSpanTransfer.class);
		}

		public static List getEnumList()
		{
			return getEnumList(EnumSpanTransfer.class);
		}

		public static Iterator iterator()
		{
			return iterator(EnumSpanTransfer.class);
		}

		public static final EnumSpanTransfer BuyerToPrinterDeliver = new EnumSpanTransfer("BuyerToPrinterDeliver");
		public static final EnumSpanTransfer BuyerToPrinterPickup = new EnumSpanTransfer("BuyerToPrinterPickup");

	}

	@Override
	public ValuedEnum getEnumType()
	{
		return EnumSpanTransfer.getEnum(0);
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
