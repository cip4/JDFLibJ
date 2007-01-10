/**
 * ==========================================================================
 * class JDFColorSpaceConversionParams extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process.prepress;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoColorSpaceConversionParams;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.w3c.dom.DOMException;


public class JDFColorSpaceConversionParams extends JDFAutoColorSpaceConversionParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFColorSpaceConversionParams
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFColorSpaceConversionParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFColorSpaceConversionParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFColorSpaceConversionParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFColorSpaceConversionParams
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFColorSpaceConversionParams(
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
        return "JDFColorSpaceConversionParams[  --> " + super.toString() + " ]";
    }
    /**
     * Appends new FileSpec(FinalTargetDevice) element to the end of 'this'  
     *
     * @return JDFFileSpec newly created child FileSpec(FinalTargetDevice) element
     */
     public JDFFileSpec appendFinalTargetDevice()
     {
         JDFFileSpec res = appendFileSpec();
         res.setResourceUsage("FinalTargetDevice");
         return res;
     }
     /**
      * Gets of 'this' an existing child FileSpec(SourceProfile) element  
      *
      * @return JDFFileSpec the matching SourceProfile element
      */
      public JDFFileSpec getFinalTargetDevice() 
      {
          return (JDFFileSpec) getChildWithAttribute(ElementName.FILESPEC, AttributeName.RESOURCEUSAGE, null, "FinalTargetDevice", 0, true);
      }
      /** 
       * Gets of 'this' child FileSpec(AbstractProfile) element, 
       * optionally creates it, if it doesn't exist.
       * 
       * @return JDFFileSpec the matching AbstractProfile element
       */
       public JDFFileSpec getCreateAbstractProfile() 
       {
           JDFFileSpec res = getFinalTargetDevice();
           if (res == null) 
           {
               res = appendFinalTargetDevice();
           }
           return res;
       }

} 
// ==========================================================================
