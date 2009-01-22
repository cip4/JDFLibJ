/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFIdentical.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoIdentical;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.JDFPart;
import org.w3c.dom.DOMException;

public class JDFIdentical extends JDFAutoIdentical
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFIdentical
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFIdentical(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFIdentical
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFIdentical(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFIdentical
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFIdentical(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
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
		return "JDFIdentical[  --> " + super.toString() + " ]";
	}

	/**
	 * set all parts to those define in vParts
	 * 
	 * @param JDFAttributeMap mPart: attribute map for the part to set
	 */
	@Override
	public void setPartMap(final JDFAttributeMap mPart)
	{
		getCreatePart().setPartMap(mPart);
	}

	/**
	 * get part map
	 * 
	 * @return JDFAttributeMap: the map of parts
	 */
	@Override
	public JDFAttributeMap getPartMap()
	{
		final JDFPart p = getPart();
		return p == null ? null : p.getPartMap();
	}

}
