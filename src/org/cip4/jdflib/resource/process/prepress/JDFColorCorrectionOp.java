/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFColorCorrectionOp.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process.prepress;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoColorCorrectionOp;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.process.JDFFileSpec;


public class JDFColorCorrectionOp extends JDFAutoColorCorrectionOp
{
    private static final long serialVersionUID = 1L;

    /**
     * ructor for JDFColorCorrectionOp
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFColorCorrectionOp(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * ructor for JDFColorCorrectionOp
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFColorCorrectionOp(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * ructor for JDFColorCorrectionOp
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFColorCorrectionOp(
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
        return "JDFColorCorrectionOp[  --> " + super.toString() + " ]";
    }
    

    /**
    * Gets of 'this' an existing child FileSpec(AbstractProfile) element  
    *
    * @return JDFFileSpec the matching AbstractProfile element or null if nothing was found
    */
    public JDFFileSpec getAbstractProfile() 
    {
        VElement v = getChildElementVector(ElementName.FILESPEC, 
                                           null,
                                           new JDFAttributeMap(), 
                                           true, 
                                           0, 
                                           false);
        
        int siz = v.size();
        for(int i = 0; i < siz; i++)
        {
            JDFFileSpec res = (JDFFileSpec)v.elementAt(i);
            if (res.hasAttribute(AttributeName.RESOURCEUSAGE)) 
            {
                if ("AbstractProfile".equals(res.getResourceUsage()))
                {
                    return res;
                }   
            }
        }       
        
        return null;
    }

    
    /** 
    * Gets of 'this' child FileSpec(AbstractProfile) element, 
    * optionally creates it, if it doesn't exist.
    * 
    * @return JDFFileSpec the matching AbstractProfile element
    */
    public JDFFileSpec getCreateAbstractProfile() 
    {
        JDFFileSpec res = getAbstractProfile();
        if (res == null)
        {
            res = appendAbstractProfile();
        }
        
        return res;
    }


    /**
    * Appends new FileSpec(AbstractProfile) element to the end of 'this'  
    *
    * @return JDFFileSpec newly created child AbstractProfile element
    */
    public JDFFileSpec appendAbstractProfile()
    {
        JDFFileSpec res = appendFileSpec();
        res.setResourceUsage("AbstractProfile");

        return res;
    }

    

    /**
    * Gets of 'this' an existing child FileSpec(DeviceLinkProfile) element  
    *
    * @return JDFFileSpec the matching DeviceLinkProfile element or null if nothing was found
    */
    public JDFFileSpec getDeviceLinkProfile() 
    {
        VElement v = getChildElementVector(ElementName.FILESPEC, 
                                           null,
                                           new JDFAttributeMap(), 
                                           true, 
                                           0, 
                                           false);
        
        int siz = v.size();
        for(int i = 0; i < siz; i++)
        {
            JDFFileSpec res = (JDFFileSpec)v.elementAt(i);
            if (res.hasAttribute(AttributeName.RESOURCEUSAGE))
            {
                if ("DeviceLinkProfile".equals(res.getResourceUsage()))
                {
                    return res;
                }   
            }
        }

        return null;
    }

    
    /** 
    * Gets of 'this' child FileSpec(DeviceLinkProfile) element, 
    * optionally creates it, if it doesn't exist.
    * 
    * @return JDFFileSpec the matching DeviceLinkProfile element
    */
    public JDFFileSpec getCreateDeviceLinkProfile() 
    {
        JDFFileSpec res = getDeviceLinkProfile();
        if (res == null)
        {
            res = appendDeviceLinkProfile();
        }
        
        return res;
    }

    
    /**
     * Appends new FileSpec(DeviceLinkProfile) element to the end of 'this'  
     *
     * @return JDFFileSpec newly created child DeviceLinkProfile element
     */
    public JDFFileSpec appendDeviceLinkProfile() 
    {
        JDFFileSpec res = appendFileSpec();
        res.setResourceUsage("DeviceLinkProfile");

        return res;
    }

}
