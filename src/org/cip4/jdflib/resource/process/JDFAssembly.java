/**
 *
 * JDFAssembly.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoAssembly;


public class JDFAssembly extends JDFAutoAssembly
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFAssembly
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFAssembly(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFAssembly
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFAssembly(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAssembly
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFAssembly(
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
        return "JDFAssembly[  --> " + super.toString() + " ]" ;
    }
}



