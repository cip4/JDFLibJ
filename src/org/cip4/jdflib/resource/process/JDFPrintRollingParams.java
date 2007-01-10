/**
 *
 * Copyright (c) 2006 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFPrintRollingParams.java
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPrintRollingParams;


public class JDFPrintRollingParams extends JDFAutoPrintRollingParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFPrintRollingParams
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFPrintRollingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFPrintRollingParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFPrintRollingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFPrintRollingParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFPrintRollingParams(
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
        return "JDFPrintRollingParams[  --> " + super.toString() + " ]" ;
    }
}



