/**
 * ========================================================================== 
 * class JDFIDPFinishing extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoIDPFinishing;
import org.w3c.dom.DOMException;


public class JDFIDPFinishing extends JDFAutoIDPFinishing
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFIDPFinishing
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFIDPFinishing(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFIDPFinishing
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFIDPFinishing(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFIDPFinishing
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFIDPFinishing(
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
        return "JDFIDPFinishing[  --> " + super.toString() + " ]";
    }

   /**
    * Set attribute Finishings
    *@param vector value: vector of String the value to set the attribute to
    */
    /*
    public void SetFinishings(vector value)
    {
        vector v;
        for(int i=0;i<value.size();i++)
        {
            v.push_back(KString(value[i]));
        }
        super.SetFinishings(v);
    }
    */
   /**
    * Get string attribute Finishings
    * @return vKString the vaue of the attribute
    */
    /*
    public vint GetFinishings()
    {
        vKString v= super.GetFinishings();
        vint vi;
        for(int i=0;i<v.size();i++)
        {
            vi.push_back((int)v[i]);
        }
        return vi;
    }
   */
   /**
    * Typesafe attribute validation of Finishings
    * @param EnumValidationLevel level of attribute validation
    * @return bool true if valid
    */
    /*
    public boolean ValidFinishings(EnumValidationLevel level)
    {
        return ValidEnumerationsAttribute("Finishings" , 
            "3,4,5,6,7,8,9,10,11,12,13,14,50,51,52,53",RequiredLevel(level));
    }
    */

} // class JDFIDPFinishing
// ==========================================================================
