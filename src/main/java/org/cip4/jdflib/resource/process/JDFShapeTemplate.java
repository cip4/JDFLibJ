/**
 *
 * Copyright (c) 2009 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFShapeTemplate.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoShapeTemplate;

public class JDFShapeTemplate extends JDFAutoShapeTemplate
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFShapeTemplate
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFShapeTemplate(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFShapeTemplate
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFShapeTemplate(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFShapeTemplate
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFShapeTemplate(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	// **************************************** Methods
	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFShapeTemplate[  --> " + super.toString() + " ]";
	}
}
