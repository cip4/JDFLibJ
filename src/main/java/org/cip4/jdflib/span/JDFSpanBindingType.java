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

public class JDFSpanBindingType extends JDFEnumerationSpan
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSpanBindingType
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanBindingType(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanBindingType
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanBindingType(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanBindingType
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFSpanBindingType(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for EnumSpanBindingType
	 */
	public static class EnumSpanBindingType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumSpanBindingType(String name)
		{
			super(name, m_startValue++);
		}

		public static EnumSpanBindingType getEnum(String enumName)
		{
			return (EnumSpanBindingType) getEnum(EnumSpanBindingType.class, enumName);
		}

		public static EnumSpanBindingType getEnum(int enumValue)
		{
			return (EnumSpanBindingType) getEnum(EnumSpanBindingType.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumSpanBindingType.class);
		}

		public static List getEnumList()
		{
			return getEnumList(EnumSpanBindingType.class);
		}

		public static Iterator iterator()
		{
			return iterator(EnumSpanBindingType.class);
		}

		public static final EnumSpanBindingType Adhesive = new EnumSpanBindingType("Adhesive");
		public static final EnumSpanBindingType ChannelBinding = new EnumSpanBindingType("ChannelBinding");
		public static final EnumSpanBindingType CoilBinding = new EnumSpanBindingType("CoilBinding");
		public static final EnumSpanBindingType CornerStitch = new EnumSpanBindingType("CornerStitch");
		public static final EnumSpanBindingType EdgeGluing = new EnumSpanBindingType("EdgeGluing");
		public static final EnumSpanBindingType HardCover = new EnumSpanBindingType("HardCover");
		public static final EnumSpanBindingType LooseBinding = new EnumSpanBindingType("LooseBinding");
		public static final EnumSpanBindingType PlasticComb = new EnumSpanBindingType("PlasticComb");
		public static final EnumSpanBindingType Ring = new EnumSpanBindingType("Ring");
		public static final EnumSpanBindingType SaddleStitch = new EnumSpanBindingType("SaddleStitch");
		public static final EnumSpanBindingType Sewn = new EnumSpanBindingType("Sewn");
		public static final EnumSpanBindingType SideSewn = new EnumSpanBindingType("SideSewn");
		public static final EnumSpanBindingType SideStitch = new EnumSpanBindingType("SideStitch");
		public static final EnumSpanBindingType SoftCover = new EnumSpanBindingType("SoftCover");
		public static final EnumSpanBindingType StripBind = new EnumSpanBindingType("StripBind");
		public static final EnumSpanBindingType Tape = new EnumSpanBindingType("Tape");
		public static final EnumSpanBindingType ThreadSealing = new EnumSpanBindingType("ThreadSealing");
		public static final EnumSpanBindingType WireComb = new EnumSpanBindingType("WireComb");
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
		return EnumSpanBindingType.getEnum(0);
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFSpanBindingType[  --> " + super.toString() + " ]";
	}
}
