/**
 * ==========================================================================
 * class JDFLocation
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de   using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 * Revision history:   ...
 */

package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoLocation;
import org.w3c.dom.DOMException;


public class JDFLocation extends JDFAutoLocation
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFLocation
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFLocation(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFLocation
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFLocation(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFLocation
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFLocation(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    public String toString()
    {
        return "JDFLocation[  --> " + super.toString() + " ]";
    }
} // class JDFIDPLayout
// ==========================================================================
