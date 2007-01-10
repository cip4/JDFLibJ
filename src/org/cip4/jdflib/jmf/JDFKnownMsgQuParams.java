/**
==========================================================================
class JDFKnownMsgQuParams extends JDFResource
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
**/





package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoKnownMsgQuParams;



//----------------------------------
    public class JDFKnownMsgQuParams extends JDFAutoKnownMsgQuParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFKnownMsgQuParams
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFKnownMsgQuParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFKnownMsgQuParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFKnownMsgQuParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFKnownMsgQuParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFKnownMsgQuParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    public String toString()
    {
        return "JDFKnownMsgQuParams[  --> " + super.toString() + " ]";
    }
}



