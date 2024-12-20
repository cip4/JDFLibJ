/**
 *
 * Copyright (c) 2010-2023 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFVarnishingParams.java
 *
 */
package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoVarnishingParams;
import org.w3c.dom.DOMException;

public class JDFVarnishingParams extends JDFAutoVarnishingParams
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFVarnishingParams
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFVarnishingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFVarnishingParams
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFVarnishingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFVarnishingParams
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFVarnishingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
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
		return "JDFVarnishingParams[  --> " + super.toString() + " ]";
	}

	/**
	 * 
	 * @return true if we are a varnish with offset plates rather than flexo / varnishing plates
	 */
	public boolean isPressModule()
	{
		String mt = getModuleType();
		return "PrintModule".equalsIgnoreCase(mt);
	}
}
