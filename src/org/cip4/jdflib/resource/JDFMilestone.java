/**
 *
 * Copyright (c) 2007 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFMilestone.java
 *
 */
package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoMilestone;

public class JDFMilestone extends JDFAutoMilestone
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFMilestone
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFMilestone(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFMilestone
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFMilestone(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFMilestone
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFMilestone(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	public String toString()
	{
		return "JDFMilestone[  --> " + super.toString() + " ]";
	}
}
