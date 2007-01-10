/**
==========================================================================
class JDFAdhesiveBindingParams
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
**/
package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoAdhesiveBindingParams;
import org.w3c.dom.DOMException;


public class JDFAdhesiveBindingParams extends JDFAutoAdhesiveBindingParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFAdhesiveBindingParams
     * @param myOwnerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFAdhesiveBindingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAdhesiveBindingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFAdhesiveBindingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAdhesiveBindingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     * @throws DOMException
     */
    public JDFAdhesiveBindingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * toString()
     * @return String
     */
    public String toString()
    {
        return "JDFAdhesiveBindingParams[  --> " + super.toString() + " ]";
    }
}



