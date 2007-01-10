/**
 * ========================================================================== 
 * class JDFFoldingIntent extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de   using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 * Revision history:   ...
 */

package org.cip4.jdflib.resource.intent;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoFoldingIntent;
import org.w3c.dom.DOMException;


public class JDFFoldingIntent extends JDFAutoFoldingIntent
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFFoldingIntent
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFFoldingIntent(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFFoldingIntent
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFFoldingIntent(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFFoldingIntent
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFFoldingIntent(
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
        return "JDFFoldingIntent[  --> " + super.toString() + " ]";
    }
} // class JDFIDPLayout
// ==========================================================================
