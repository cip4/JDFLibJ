/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFPerformance.java
 *
 */
package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPerformance;
import org.w3c.dom.DOMException;


public class JDFPerformance extends JDFAutoPerformance
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFPerformance
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFPerformance(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFPerformance
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFPerformance(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFPerformance
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFPerformance(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
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
        return "JDFPerformance[  --> " + super.toString() + " ]";
    }
}
