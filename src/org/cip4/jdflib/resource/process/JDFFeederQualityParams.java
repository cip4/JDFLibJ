/**
 *
 * Copyright (c) 2006 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFFeederQualityParams.java
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoFeederQualityParams;


public class JDFFeederQualityParams extends JDFAutoFeederQualityParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFFeederQualityParams
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFFeederQualityParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFFeederQualityParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFFeederQualityParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFFeederQualityParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFFeederQualityParams(
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
        return "JDFFeederQualityParams[  --> " + super.toString() + " ]" ;
    }
}



