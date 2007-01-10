/**
 * ========================================================================== 
 * class JDFProcessRun extends JDFAutoProcessRun
 * created 2001-09-06T10:02:57GMT+02:00 
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de   using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 * Revision history:   ...
 */

package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoProcessRun;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.util.JDFDuration;
import org.w3c.dom.DOMException;


public class JDFProcessRun extends JDFAutoProcessRun
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFProcessRun
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFProcessRun(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFProcessRun
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFProcessRun(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFProcessRun
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFProcessRun(
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
        return "JDFProcessRun[  --> " + super.toString() + " ]";
    }
    
    
    
    public void setDurationSeconds(/* unsigned */ int seconds) throws JDFException
    {
        if(seconds < 0)
        {
            throw new JDFException("parameter need to be >= 0");
        }
        JDFDuration d = new JDFDuration();
        d.setDuration(seconds);
        setAttribute("Duration", d.getDurationISO(), JDFConstants.EMPTYSTRING);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    

    
    
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
    
} // class JDFProcessRun
// ==========================================================================
