/**
 * ==========================================================================
 * class JDFColorCorrectionParams extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process.prepress;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoColorCorrectionParams;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.resource.process.JDFFileSpec;


public class JDFColorCorrectionParams extends JDFAutoColorCorrectionParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFColorCorrectionParams
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFColorCorrectionParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFColorCorrectionParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFColorCorrectionParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFColorCorrectionParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFColorCorrectionParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    public String toString()
    {
        return "JDFColorCorrectionParams[  --> " + super.toString() + " ]";
    }
    
    
    /**
    * Gets of 'this' an existing child FileSpec(FinalTargetDevice) element  
    *
    * @return JDFFileSpec the matching FinalTargetDevice element or 
    *         null if nothing was found
    */
    public JDFFileSpec getFinalTargetDevice() 
    {
        VElement v = getChildElementVector(ElementName.FILESPEC, 
                                            null,
                                            null, 
                                            true, 
                                            0, 
                                            false);
        int siz = v.size();
        for(int i = 0; i < siz; i++)
        {
            JDFFileSpec res = (JDFFileSpec)v.elementAt(i);
            if (res.hasAttribute(AttributeName.RESOURCEUSAGE))
            {
                if ("FinalTargetDevice".equals(res.getResourceUsage()))
                {
                    return res;
                }   
            }
        }
        
        return null;
    }


    /** 
    * Gets of 'this' child FileSpec(FinalTargetDevice) element, 
    * optionally creates it, if it doesn't exist.
    * 
    * @return JDFFileSpec: the matching FinalTargetDevice element
    */
    public JDFFileSpec getCreateFinalTargetDevice() 
    {
        JDFFileSpec res = getFinalTargetDevice();
        if (res == null)
        {
            res = appendFinalTargetDevice();
        }
        
        return res;
    }

    
    /**
    * Appends new FileSpec(FinalTargetDevice) element to the end of 'this'  
    *
    * @return JDFFileSpec: newly created child FinalTargetDevice element
    */
    public JDFFileSpec appendFinalTargetDevice() 
    {
        JDFFileSpec res = appendFileSpec();
        res.setResourceUsage("FinalTargetDevice");

        return res;
    }

    
    /**
    * Gets of 'this' an existing child FileSpec(WorkingColorSpace) element  
    *
    * @return JDFFileSpec: the matching WorkingColorSpace element
    */
    public JDFFileSpec getWorkingColorSpace() 
    {
        VElement v = getChildElementVector(ElementName.FILESPEC, 
                                          null,
                                          null, 
                                          true, 
                                          0, 
                                          false);
        int siz = v.size();
        for(int i = 0; i < siz; i++)
        {
            JDFFileSpec res = (JDFFileSpec)v.elementAt(i);
            if (res.hasAttribute(AttributeName.RESOURCEUSAGE)) 
            {
                if ("WorkingColorSpace".equals(res.getResourceUsage()))
                {
                    return res;
                }   
            }
        }

        return null;
    }

    
    /** 
    * Gets of 'this' child FileSpec(WorkingColorSpace) element, 
    * optionally creates it, if it doesn't exist.
    * 
    * @return JDFFileSpec: the matching WorkingColorSpace element
    */
    public JDFFileSpec getCreateWorkingColorSpace() 
    {
        JDFFileSpec res = getWorkingColorSpace();
        if (res == null)
        {
            res = appendWorkingColorSpace();
        }
        
        return res;
    }

   
    /**
    * Appends new FileSpec(WorkingColorSpace) element to the end of 'this'  
    *
    * @return JDFFileSpec: newly created child WorkingColorSpace element
    */ 
    public JDFFileSpec appendWorkingColorSpace() 
    {
        JDFFileSpec res = appendFileSpec();
        res.setResourceUsage("WorkingColorSpace");

        return res;
    }

} 
