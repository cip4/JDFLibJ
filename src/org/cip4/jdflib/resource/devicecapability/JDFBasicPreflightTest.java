/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFBasicPreflightTest.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.devicecapability;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest;
import org.w3c.dom.DOMException;


public class JDFBasicPreflightTest extends JDFAutoBasicPreflightTest
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFBasicPreflightTest
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFBasicPreflightTest(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFBasicPreflightTest
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFBasicPreflightTest(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFBasicPreflightTest
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFBasicPreflightTest(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
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
        return "JDFBasicPreflightTest[  --> " + super.toString() + " ]" ;
    }
}



