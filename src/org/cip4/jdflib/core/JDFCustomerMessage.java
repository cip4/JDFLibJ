/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFCustomerMessage.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.core;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoCustomerMessage;

    
public class JDFCustomerMessage extends JDFAutoCustomerMessage
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFCustomerMessage
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFCustomerMessage(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFCustomerMessage
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFCustomerMessage(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFCustomerMessage
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFCustomerMessage(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * toString
     *
     * @return StringJDFAutoCustomerMessage
     */
    public String toString()
    {
        return "JDFCustomerMessage[  --> " + super.toString() + " ]" ;
    }
}