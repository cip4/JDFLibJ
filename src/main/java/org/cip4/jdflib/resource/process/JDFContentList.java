/**
 *
 * Copyright (c) 2001-2013 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFContentList.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoContentList;
import org.w3c.dom.DOMException;

/**
 * 
 *  
 * @author rainer prosi
 * @date Apr 10, 2013
 */
public class JDFContentList extends JDFAutoContentList
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFContentList
	 * @param myOwnerDocument 
	 * @param qualifiedName 
	 * @throws DOMException 
	 * 
	 */
	public JDFContentList(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFContentList
	 * @param myOwnerDocument 
	 * @param myNamespaceURI 
	 * @param qualifiedName 
	 * @throws DOMException 
	 * 
	 */
	public JDFContentList(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFContentList
	 * @param myOwnerDocument 
	 * @param myNamespaceURI 
	 * @param qualifiedName 
	 * @param myLocalName 
	 * @throws DOMException 
	 * 
	 */
	public JDFContentList(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
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
		return "JDFContentList[  --> " + super.toString() + " ]";
	}
}
