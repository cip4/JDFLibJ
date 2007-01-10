/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFObservationTarget.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoObservationTarget;
import org.w3c.dom.DOMException;


public class JDFObservationTarget extends JDFAutoObservationTarget
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFObservationTarget
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFObservationTarget(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFObservationTarget
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFObservationTarget(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFObservationTarget
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFObservationTarget(
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
        return "JDFObservationTarget[  --> " + super.toString() + " ]" ;
    }
}



