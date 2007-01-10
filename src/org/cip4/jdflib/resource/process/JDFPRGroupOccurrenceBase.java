/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFPRGroupOccurrenceBase.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPRGroupOccurrenceBase;
import org.w3c.dom.DOMException;


public class JDFPRGroupOccurrenceBase extends JDFAutoPRGroupOccurrenceBase
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFPRGroupOccurrenceBase
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFPRGroupOccurrenceBase(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFPRGroupOccurrenceBase
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFPRGroupOccurrenceBase(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFPRGroupOccurrenceBase
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFPRGroupOccurrenceBase(
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
        return "JDFPRGroupOccurrenceBase[  --> " + super.toString() + " ]";
    }
}
