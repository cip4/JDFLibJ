/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSpanFinishedGrainDirection.java
 *
 */
package org.cip4.jdflib.span;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.util.JavaEnumUtil;
import org.w3c.dom.DOMException;

public class JDFSpanFinishedGrainDirection extends JDFEnumerationSpan
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSpanFinishedGrainDirection
	 *
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanFinishedGrainDirection(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanFinishedGrainDirection
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSpanFinishedGrainDirection(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSpanFinishedGrainDirection
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFSpanFinishedGrainDirection(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for EnumSpanFinishedGrainDirection
	 */
	public enum EnumSpanFinishedGrainDirection
	{
		ParallelToBind, PerpendicularToBind;

		public static EnumSpanFinishedGrainDirection getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumSpanFinishedGrainDirection.class, val, null);
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
		return EnumSpanFinishedGrainDirection.class;
	}

	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFSpanFinishedGrainDirection[ --> " + super.toString() + " ]";
	}

	@Override
	public boolean init()
	{
		final boolean b = super.init();
		setDataType(EnumDataType.EnumerationSpan);
		return b;
	}

}
