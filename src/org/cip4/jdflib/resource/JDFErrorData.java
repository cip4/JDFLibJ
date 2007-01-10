/**
 *
 * Copyright (c) 2005 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFErrorData.java
 *
 */
package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoError;


public class JDFErrorData extends JDFAutoError  //TODO JDFAutoErrorData ?
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFError
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFErrorData(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFError
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFErrorData(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFError
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFErrorData(
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
     * @return String
     */
    public String toString()
    {
        return "JDFErrorData[  --> " + super.toString() + " ]";
    }
}
