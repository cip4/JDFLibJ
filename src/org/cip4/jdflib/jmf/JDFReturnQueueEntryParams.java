/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFReturnQueueEntryParams.java
 *
 * Last changes
 *
 * 2002-07-02 JG - init() Also call super::init()
 *
 */
package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoReturnQueueEntryParams;
import org.cip4.jdflib.core.JDFDoc;

/**
 *
 */
public class JDFReturnQueueEntryParams extends JDFAutoReturnQueueEntryParams
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFReturnQueueEntryParams
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFReturnQueueEntryParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFReturnQueueEntryParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFReturnQueueEntryParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFReturnQueueEntryParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFReturnQueueEntryParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * returns the jdf doc referenced by url
	 * 
	 * @return the document
	 */
	public JDFDoc getURLDoc()
	{
		return super.getURLDoc();
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString
	 * 
	 * @return String
	 */
	public String toString()
	{
		return "JDFReturnQueueEntryParams[  --> " + super.toString() + " ]";
	}
}
