/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFBendingParams.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBendingParams;
import org.w3c.dom.DOMException;


public class JDFBendingParams extends JDFAutoBendingParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFBendingParams
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFBendingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFBendingParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFBendingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFBendingParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFBendingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFBendingParams[  --> " + super.toString() + " ]";
    }
}
