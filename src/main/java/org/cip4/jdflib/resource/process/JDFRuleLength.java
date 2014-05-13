/**
 *
 * Copyright (c) 2008 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFRuleLength.java
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoRuleLength;
import org.w3c.dom.DOMException;

/**
 * 
 * TODO Please insert comment!
 * @author rainerprosi
 * @date May 13, 2014
 */
public class JDFRuleLength extends JDFAutoRuleLength
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFRuleLength
	 * @param myOwnerDocument 
	 * @param qualifiedName 
	 * @throws DOMException 
	 * 
	
	 */
	public JDFRuleLength(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFRuleLength
	 * @param myOwnerDocument 
	 * @param myNamespaceURI 
	 * 
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFRuleLength(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFRuleLength
	 * @param myOwnerDocument 
	 * @param myNamespaceURI 
	 * @param qualifiedName 
	 * @param myLocalName 
	 * @throws DOMException 
	 * 
	 */
	public JDFRuleLength(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
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
		return "JDFRuleLength[  --> " + super.toString() + " ]";
	}
}
