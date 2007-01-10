/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFBand.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.w3c.dom.DOMException;


public class JDFStripBindingParamsUpdate extends JDFResource
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFStripBindingParamsUpdate
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFStripBindingParamsUpdate(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFStripBindingParamsUpdate
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFStripBindingParamsUpdate(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFStripBindingParamsUpdate
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFStripBindingParamsUpdate(
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
        return "JDFStripBindingParamsUpdate[  --> " + super.toString() + " ]" ;
    }
}



