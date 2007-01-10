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
     * @param myOwnerDocument
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
     * @param myOwnerDocument
     * @param myNamespaceURI
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
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
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