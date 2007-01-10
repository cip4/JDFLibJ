/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFTransferCurve.java
 *
 * Last changes
 *
 * 2002-07-02 JG - added ConsistentPartIDKeys()
 *
 */

package org.cip4.jdflib.resource.process;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoTransferCurve;
import org.w3c.dom.DOMException;


public class JDFTransferCurve extends JDFAutoTransferCurve
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFTransferCurve
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFTransferCurve(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFTransferCurve
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFTransferCurve(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFTransferCurve
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFTransferCurve(
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
        return "JDFTransferCurve[  --> " + super.toString() + " ]";
    }
    
    
    /**
     * get the vector of implicitly define partition keys that MUST NOT be used in the resource
     */
    public Vector getImplicitPartitions()
    {
        Vector v = super.getImplicitPartitions();
        if(v==null)
            v=new Vector();
        v.add(EnumPartIDKey.Separation);
        return v;
    }

    

}
// ==========================================================================
