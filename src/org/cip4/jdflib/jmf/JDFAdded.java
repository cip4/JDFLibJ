/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFChangedAttribute.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoAdded;


public class JDFAdded extends JDFAutoAdded
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFChangedAttribute
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFAdded(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFChangedAttribute
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFAdded(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFChangedAttribute
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFAdded(
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
        return "JDFAdded[  --> " + super.toString() + " ]" ;
    }
}



