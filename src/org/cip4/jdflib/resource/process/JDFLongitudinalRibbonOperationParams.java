/**
 *==========================================================================
 * class JDFLongitudinalRibbonOperationParams extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoLongitudinalRibbonOperationParams;
import org.w3c.dom.DOMException;


public class JDFLongitudinalRibbonOperationParams extends JDFAutoLongitudinalRibbonOperationParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFLongitudinalRibbonOperationParams
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFLongitudinalRibbonOperationParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFLongitudinalRibbonOperationParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFLongitudinalRibbonOperationParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFLongitudinalRibbonOperationParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFLongitudinalRibbonOperationParams(
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
        return "JDFLongitudinalRibbonOperationParams[  --> " + super.toString() + " ]";
    }
} // class JDFIDPLayout
// ==========================================================================
