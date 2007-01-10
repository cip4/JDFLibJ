/**
 * ==========================================================================
 * class JDFSeparationSpec
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoSeparationSpec;
import org.w3c.dom.DOMException;


public class JDFSeparationSpec extends JDFAutoSeparationSpec
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFSeparationSpec
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFSeparationSpec(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFSeparationSpec
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSeparationSpec(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFSeparationSpec
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSeparationSpec(
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
        return "JDFSeparationSpec[  --> " + super.toString() + " ]";
    }
} // class JDFIDPLayout
// ==========================================================================
