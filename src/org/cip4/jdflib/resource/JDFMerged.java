/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFMerged.java
 *
 * Last changes
 *
 * 2002-07-02 JG added MergeID handling
 * 2002-07-02 JG removed MergeID handling - now done in JDFAutoMerged
 *
 */

package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoMerged;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.w3c.dom.DOMException;


public class JDFMerged extends JDFAutoMerged
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFMerged
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFMerged(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFMerged
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFMerged(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFMerged
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFMerged(
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
        return "JDFMerged[  --> " + super.toString() + " ]";
    }
    
    /**
     * get part map vector
     * @return VJDFAttributeMap: vector of attribute maps, one for each part
     */
    public VJDFAttributeMap getPartMapVector()
    {
        return super.getPartMapVector();
    }

    /**
     * set all parts to those define in vParts
     * @param VJDFAttributeMap vParts: vector of attribute maps for the parts
     */
    public void setPartMapVector(VJDFAttributeMap vParts)
    {
        super.setPartMapVector(vParts);
    }

    /**
     * set all parts to those define in vParts
     * @param JDFAttributeMap mPart: attribute map for the part to set
     */
    public void setPartMap(JDFAttributeMap mPart)
    {
        super.setPartMap(mPart);
    }

    /**
     * remove the part defined in mPart
     * @param JDFAttributeMap mPart: attribute map for the part to remove
     */
    public void removePartMap(JDFAttributeMap mPart)
    {
        super.removePartMap(mPart);
    }

    /**
     * check whether the part defined in mPart is included
     * @param JDFAttributeMap mPart: attribute map for the part to remove
     * @return boolean - returns true if the part exists
     */
    public boolean hasPartMap(JDFAttributeMap mPart)
    {
        return super.hasPartMap(mPart);
    }
    
} // class JDFMerged
// ==========================================================================
