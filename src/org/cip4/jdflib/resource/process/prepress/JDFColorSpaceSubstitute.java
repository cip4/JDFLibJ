/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFColorSpaceSubstitute.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process.prepress;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoColorSpaceSubstitute;
import org.w3c.dom.DOMException;


public class JDFColorSpaceSubstitute extends JDFAutoColorSpaceSubstitute
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFColorSpaceSubstitute
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFColorSpaceSubstitute(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFColorSpaceSubstitute
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFColorSpaceSubstitute(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFColorSpaceSubstitute
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFColorSpaceSubstitute(
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
        return "JDFColorSpaceSubstitute[  --> " + super.toString() + " ]";
    }
}
