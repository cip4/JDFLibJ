/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFAmount.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBindItem;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.resource.intent.JDFBindingIntent;
import org.cip4.jdflib.span.JDFSpanBindingType;
import org.w3c.dom.DOMException;

public class JDFBindItem extends JDFAutoBindItem
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFBindItem
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFBindItem(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFBindItem
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFBindItem(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFBindItem
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 */
	public JDFBindItem(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
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
		return "JDFBindItem[  --> " + super.toString() + " ]";
	}

	/**
	 * get parent node of <code>this</code> - node BindingIntent
	 * 
	 * @return JDFBindingIntent: BindingIntent node
	 */
	public JDFBindingIntent getParentBindingIntent()
	{
		return (JDFBindingIntent) getParentNode();
	}

	/**
	 * Get the value of the BindingType element of <code>this</code>.<br>
	 * If not specified, defaults to the value of the BindingType which is specified in its parent element (node
	 * BindingIntent).
	 * 
	 * @return JDFSpanBindingType: BindingType value
	 */
	@Override
	public JDFSpanBindingType getBindingType()
	{
		if (hasChildElement(ElementName.BINDINGTYPE, null))
		{
			return super.getBindingType();
		}
		return getParentBindingIntent().getBindingType();
	}
}
