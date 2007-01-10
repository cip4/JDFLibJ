/**
 * ========================================================================== 
 * class JDFScanParams extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process.prepress;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoScanParams;
import org.w3c.dom.DOMException;


public class JDFScanParams extends JDFAutoScanParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFScanParams
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFScanParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFScanParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFScanParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFScanParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFScanParams(
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
        return "JDFScanParams[  --> " + super.toString() + " ]";
    }
} // class JDFIDPLayout
// ==========================================================================
