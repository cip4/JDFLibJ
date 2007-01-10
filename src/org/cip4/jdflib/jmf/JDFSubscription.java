/**
==========================================================================
class JDFSubscription extends JDFResource
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
**/





package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoSubscription;



//----------------------------------
    public class JDFSubscription extends JDFAutoSubscription
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFSubscription
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFSubscription(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFSubscription
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFSubscription(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFSubscription
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFSubscription(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    public String toString()
    {
        return "JDFSubscription[  --> " + super.toString() + " ]";
    }
}



