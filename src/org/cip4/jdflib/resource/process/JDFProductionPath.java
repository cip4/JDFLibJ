/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFProductionPath.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoProductionPath;


public class JDFProductionPath extends JDFAutoProductionPath
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFProductionPath
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFProductionPath(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFProductionPath
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFProductionPath(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFProductionPath
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFProductionPath(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
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
        return "JDFProductionPath[  --> " + super.toString() + " ]";
    }
}
