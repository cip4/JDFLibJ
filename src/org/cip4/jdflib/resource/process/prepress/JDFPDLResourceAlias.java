/**
 * ==========================================================================
 * class JDFPDLResourceAlias
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process.prepress;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPDLResourceAlias;
import org.w3c.dom.DOMException;


public class JDFPDLResourceAlias extends JDFAutoPDLResourceAlias
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFPDLResourceAlias
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFPDLResourceAlias(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFPDLResourceAlias
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFPDLResourceAlias(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFPDLResourceAlias
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFPDLResourceAlias(
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
        return "JDFPDLResourceAlias[  --> " + super.toString() + " ]";
    }
} // class JDFIDPLayout
// ==========================================================================
