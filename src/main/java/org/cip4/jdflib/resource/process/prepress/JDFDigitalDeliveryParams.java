/**
 *
 * Copyright (c) 2006 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFDigitalDeliveryParams.java
 *
 */
package org.cip4.jdflib.resource.process.prepress;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDigitalDeliveryParams;

public class JDFDigitalDeliveryParams extends JDFAutoDigitalDeliveryParams
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFDigitalDeliveryParams
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFDigitalDeliveryParams(CoreDocumentImpl myOwnerDocument,
			String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFDigitalDeliveryParams
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFDigitalDeliveryParams(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFDigitalDeliveryParams
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFDigitalDeliveryParams(CoreDocumentImpl myOwnerDocument,
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
		return "JDFDigitalDeliveryParams[  --> " + super.toString() + " ]";
	}
}
