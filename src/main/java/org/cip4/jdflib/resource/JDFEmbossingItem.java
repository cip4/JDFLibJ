/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFEmbossingItem.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoEmbossingItem;
import org.w3c.dom.DOMException;

public class JDFEmbossingItem extends JDFAutoEmbossingItem
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFEmbossingItem
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFEmbossingItem(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFEmbossingItem
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFEmbossingItem(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFEmbossingItem
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFEmbossingItem(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFEmbossingItem[  --> " + super.toString() + " ]";
	}
}
