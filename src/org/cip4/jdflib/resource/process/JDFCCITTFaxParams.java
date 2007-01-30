/**
 *
 * Copyright (c) 2006 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFCCITTFaxParams.java
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoCCITTFaxParams;


public class JDFCCITTFaxParams extends JDFAutoCCITTFaxParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFCCITTFaxParams
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFCCITTFaxParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFCCITTFaxParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFCCITTFaxParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFCCITTFaxParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFCCITTFaxParams(
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
        return "JDFCCITTFaxParams[  --> " + super.toString() + " ]" ;
    }
}



