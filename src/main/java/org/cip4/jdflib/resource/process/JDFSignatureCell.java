/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSignatureCell.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoSignatureCell;
import org.w3c.dom.DOMException;

public class JDFSignatureCell extends JDFAutoSignatureCell
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSignatureCell
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSignatureCell(CoreDocumentImpl myOwnerDocument,
			String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSignatureCell
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSignatureCell(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSignatureCell
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFSignatureCell(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName, String myLocalName)
			throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
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
		return "JDFSignatureCell[  --> " + super.toString() + " ]";
	}
}
