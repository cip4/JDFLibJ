/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFCommand.java
 *
 * Last changes
 *
 * 2002-07-02 JG init() Also call super::init()
 *
 *
 */
package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoCommand;

/**
 *
 */
public class JDFCommand extends JDFAutoCommand
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFCommand
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFCommand(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFCommand
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFCommand(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFCommand
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFCommand(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    //**************************************** Methods *********************************************
    /**
     * toString - StringRepresentation of JDFNode
     *
     * @return String
     */
    public String toString()
    {
        return "JDFCommand[  --> " + super.toString() + " ]";
    }


}
