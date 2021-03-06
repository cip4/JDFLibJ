/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFInterpretedPDLData.java
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoInterpretedPDLData;
import org.w3c.dom.DOMException;

public class JDFInterpretedPDLData extends JDFAutoInterpretedPDLData
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFInterpretedPDLData
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFInterpretedPDLData(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFInterpretedPDLData
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFInterpretedPDLData(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFInterpretedPDLData
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFInterpretedPDLData(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
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
		return "JDFInterpretedPDLData[  --> " + super.toString() + " ]";
	}
}
