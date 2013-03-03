/**
 *
 * Copyright (c) 2009 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFObjectModel.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoObjectModel;

public class JDFObjectModel extends JDFAutoObjectModel
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFObjectModel
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFObjectModel(CoreDocumentImpl myOwnerDocument,
			String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFObjectModel
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFObjectModel(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFObjectModel
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFObjectModel(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName, String myLocalName)
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
		return "JDFObjectModel[  --> " + super.toString() + " ]";
	}
}
