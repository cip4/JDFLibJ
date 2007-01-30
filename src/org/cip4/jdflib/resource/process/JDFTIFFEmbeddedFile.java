/**
 *
 * Copyright (c) 2006 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFTiffEmbeddedFile.java
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoTIFFEmbeddedFile;


public class JDFTIFFEmbeddedFile extends JDFAutoTIFFEmbeddedFile
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFTiffEmbeddedFile
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFTIFFEmbeddedFile(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFTiffEmbeddedFile
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFTIFFEmbeddedFile(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFTiffEmbeddedFile
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFTIFFEmbeddedFile(
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
        return "JDFTiffEmbeddedFile[  --> " + super.toString() + " ]" ;
    }
}



