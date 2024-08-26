/**
 *
 * Copyright (c) 2008-2024 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFShapeDef.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoShapeDef;

public class JDFShapeDef extends JDFAutoShapeDef
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFShapeDef
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFShapeDef(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFShapeDef
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFShapeDef(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFShapeDef
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFShapeDef(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

}
