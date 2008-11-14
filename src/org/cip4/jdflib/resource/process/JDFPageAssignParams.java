/**
 *
 * Copyright (c) 2008 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFPageAssignParams.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoAdvancedParams;
import org.w3c.dom.DOMException;

public class JDFPageAssignParams extends JDFAutoAdvancedParams
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFPageAssignParams
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFPageAssignParams(CoreDocumentImpl myOwnerDocument,
			String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFPageAssignParams
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFPageAssignParams(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFPageAssignParams
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFPageAssignParams(CoreDocumentImpl myOwnerDocument,
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
		return "JDFPageAssignParams[  --> " + super.toString() + " ]";
	}
}
