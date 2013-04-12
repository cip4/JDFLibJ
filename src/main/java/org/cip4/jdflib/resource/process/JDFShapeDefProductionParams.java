/**
 *
 * Copyright (c) 2009 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFShapeDefProductionParams.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoShapeDefProductionParams;

public class JDFShapeDefProductionParams extends JDFAutoShapeDefProductionParams
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFShapeDefProductionParams
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFShapeDefProductionParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFShapeDefProductionParams
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFShapeDefProductionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFShapeDefProductionParams
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFShapeDefProductionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
		return "JDFShapeDefProductionParams[  --> " + super.toString() + " ]";
	}
}
