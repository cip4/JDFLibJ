/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFBand.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource; 

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoCuttingParams;
import org.w3c.dom.DOMException;


public class JDFCuttingParams extends JDFAutoCuttingParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFCuttingParams
     * @param myOwnerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFCuttingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFCuttingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFCuttingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFCuttingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     * @throws DOMException
     */
    public JDFCuttingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFCuttingParams[  --> " + super.toString() + " ]" ;
    }
    
    /**
     * get the vector of implicitly defined partition keys that MUST NOT be used in the resource
     */
    public Vector getImplicitPartitions()
    {
        Vector v = super.getImplicitPartitions();
        if(v==null)
            v=new Vector();
        v.add(EnumPartIDKey.BlockName);
        return v;
    }
    
}



