/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFFeaturePool.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.devicecapability;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoFeaturePool;
import org.w3c.dom.DOMException;


public class JDFFeaturePool extends JDFAutoFeaturePool
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFFeaturePool
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFFeaturePool(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFFeaturePool
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFFeaturePool(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFFeaturePool
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFFeaturePool(
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
        return "JDFFeaturePool[  --> " + super.toString() + " ]" ;
    }
}



