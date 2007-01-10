/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFLayoutElementProductionParams.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoLayoutElementProductionParams;


public class JDFLayoutElementProductionParams extends JDFAutoLayoutElementProductionParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFLayoutElementProductionParams
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFLayoutElementProductionParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFLayoutElementProductionParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFLayoutElementProductionParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFLayoutElementProductionParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFLayoutElementProductionParams(
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
        return "JDFLayoutElementProductionParams[  --> " + super.toString() + " ]";
    }
}
