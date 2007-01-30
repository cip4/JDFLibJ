/**
 * ========================================================================== 
 * class JDFModulePhase extends JDFAutoModulePhase
 * created 2001-09-06T10:02:57GMT+02:00 
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de   using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 * Revision history:   ...
 */

package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoModulePhase;
import org.w3c.dom.DOMException;


public class JDFModulePhase extends JDFAutoModulePhase
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFModulePhase
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFModulePhase(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFModulePhase
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFModulePhase(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFModulePhase
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFModulePhase(
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
        return "JDFModulePhase[  --> " + super.toString() + " ]";
    }
} // class JDFModulePhase
// ==========================================================================
