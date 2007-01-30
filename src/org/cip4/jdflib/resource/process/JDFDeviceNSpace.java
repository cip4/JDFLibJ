/**
 * ========================================================================== 
 * class JDFDeviceNSpace extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDeviceNSpace;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.w3c.dom.DOMException;


public class JDFDeviceNSpace extends JDFAutoDeviceNSpace
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFDeviceNSpace
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFDeviceNSpace(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFDeviceNSpace
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFDeviceNSpace(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFDeviceNSpace
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFDeviceNSpace(
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
        return "JDFDeviceNSpace[  --> " + super.toString() + " ]";
    }
   
    /**
    * Get a list of all separation names in the SeparationSpec elements
    * @return the vector of separation names
    */
    public VString getSeparations() 
    {
        VString vName=new VString();
        VElement v=getChildElementVector(ElementName.SEPARATIONSPEC,null,null,false,0,false);
        int nSep=v.size();
        for(int i=0;i<nSep;i++)
        {
            JDFSeparationSpec sep=(JDFSeparationSpec) v.elementAt(i);
            String sepName=sep.getName();
            vName.appendUnique(sepName);
        }
        return vName;
    }

} // class JDFDeviceNSpace
// ==========================================================================
