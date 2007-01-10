/**
 *
 * Copyright (c) 2006 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFFeedingParams.java
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoFeedingParams;


public class JDFFeedingParams extends JDFAutoFeedingParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFFeedingParams
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFFeedingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFFeedingParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFFeedingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFFeedingParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFFeedingParams(
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
        return "JDFFeedingParams[  --> " + super.toString() + " ]" ;
    }
}



