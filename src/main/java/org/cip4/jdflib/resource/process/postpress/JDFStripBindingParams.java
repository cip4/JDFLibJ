/**
 *
 * Copyright (c) 2002 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFStripBindingParams.java
 *
 * Last changes
 *
 */

package org.cip4.jdflib.resource.process.postpress;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoStripBindingParams;
import org.w3c.dom.DOMException;

/**
 * @author gonnerma
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates.
 */
public class JDFStripBindingParams extends JDFAutoStripBindingParams
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFStripBindingParams
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFStripBindingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFStripBindingParams
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFStripBindingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFStripBindingParams
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFStripBindingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Method toString.
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFStripBindingParams[  --> " + super.toString() + " ]";
	}

}
