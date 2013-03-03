/**
 * ==========================================================================
 * class JDFGlueLine
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process.postpress;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoGlueLine;
import org.w3c.dom.DOMException;

public class JDFGlueLine extends JDFAutoGlueLine
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFGlueLine
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFGlueLine(CoreDocumentImpl myOwnerDocument, String qualifiedName)
			throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFGlueLine
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFGlueLine(CoreDocumentImpl myOwnerDocument, String myNamespaceURI,
			String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFGlueLine
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFGlueLine(CoreDocumentImpl myOwnerDocument, String myNamespaceURI,
			String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	@Override
	public String toString()
	{
		return "JDFGlueLine[  --> " + super.toString() + " ]";
	}
} // class JDFIDPLayout
// ==========================================================================
