/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFByteMap.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoByteMap;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;


public class JDFByteMap extends JDFAutoByteMap
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFByteMap
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFByteMap(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFByteMap
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFByteMap(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFByteMap
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFByteMap(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFByteMap[  --> " + super.toString() + " ]" ;
    }
    
    /**
    * Gets of 'this' an existing child FileSpec(RasterFileLocation) element  
    *
    * @return JDFFileSpec: the matching RasterFileLocation element or null if nothing was found
    */
    public JDFFileSpec getRasterFileLocation()
    {
        VElement v = getChildElementVector(ElementName.FILESPEC, null, null, true, 0, false);
        final int siz = v.size();
        
        for(int i = 0; i < siz; i++)
        {
            JDFFileSpec res = (JDFFileSpec)v.elementAt(i);
            if (res.hasAttribute(AttributeName.RESOURCEUSAGE)) 
            {
                if (res.getResourceUsage().equals("RasterFileLocation"))
                {
                    return res;
                }   
            }
        }
        return null;
    }


    /** 
    * Gets of 'this' child FileSpec(RasterFileLocation) element, 
    * optionally creates it, if it doesn't exist.
    * 
    * @return JDFFileSpec: the matching RasterFileLocation element
    */
    public JDFFileSpec getCreateRasterFileLocation() 
    {
        JDFFileSpec res = getRasterFileLocation();
        if (res == null)
        {
            res = appendRasterFileLocation();
        }
        
        return res;
    }
    
    /**
    * Appends new FileSpec(RasterFileLocation) element to the end of 'this'  
    *
    * @return JDFFileSpec: newly created child RasterFileLocation element
    */
    public JDFFileSpec appendRasterFileLocation() 
    {
        JDFFileSpec res = appendFileSpec();
        res.setResourceUsage("RasterFileLocation");
        return res;
    }
    /**
     * get the missing elements as a vector
     *
     * @param int nMax  maximum value of missing elements to return
     *
     * @return Vector   vector with nMax missing elements
     * 
     * default: getMissingElements(99999999)
     */
    public VString getMissingElements(int nMax)
    {
        final VString v = super.getMissingElements(nMax);
        if(v.size()>nMax)
            return v;
        if(getRasterFileLocation()==null)
            v.add("FileSpec[@ResourceUsage=\"RasterFileLocation\"]");
        return v;         
    }
    
    
}



