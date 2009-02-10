/**
 * Copyright (c) 2009 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFDieLayoutProductionParams.java
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDieLayoutProductionParams;

public class JDFDieLayoutProductionParams extends JDFAutoDieLayoutProductionParams
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFDieLayoutProductionParams
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFDieLayoutProductionParams(CoreDocumentImpl myOwnerDocument,
			String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFDieLayoutProductionParams
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFDieLayoutProductionParams(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFDieLayoutProductionParams
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFDieLayoutProductionParams(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName, String myLocalName)
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
		return "JDFDieLayoutProductionParams[  --> " + super.toString() + " ]";
	}
}
