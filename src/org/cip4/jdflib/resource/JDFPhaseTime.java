/**
 *========================================================================== class JDFPhaseTime extends JDFAutoPhaseTime
 * created 2001-09-06T10:02:57GMT+02:00 ==========================================================================
 *          @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 *              @Author: sabjon@topmail.de   using a code generator
 * Warning! very preliminary test version. Interface subject to change without prior notice! Revision history:   ...
 */

package org.cip4.jdflib.resource;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPhaseTime;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;


public class JDFPhaseTime extends JDFAutoPhaseTime
{
    private static final long serialVersionUID = 1L;


    /**
     * Constructor for JDFPhaseTime
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFPhaseTime(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFPhaseTime
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFPhaseTime(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFPhaseTime
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFPhaseTime(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    public String toString()
    {
        return "JDFPhaseTime[  --> " + super.toString() + " ]";
    }
    
    /**
     * get part map vector
     * @return VJDFAttributeMap: vector of mAttribute, one for each part
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
    /**
     * return a vector of unknown element nodenames
     * @param boolean bIgnorePrivate - used by JDFElement during the validation
     * !!! Do not change the signature of this method
     * @param int nMax - maximum size of the returned vector
     * @return Vector - vector of unknown element nodenames
     * 
     * default: GetInvalidElements(true, 999999)
     */
    public Vector getUnknownElements(boolean bIgnorePrivate, int nMax)
    {
        if(bIgnorePrivate)
            bIgnorePrivate=false; // dummy to fool compiler
        return getUnknownPoolElements(JDFElement.EnumPoolType.ResourceLinkPool, nMax);
    }
    
    
} // class JDFPhaseTime
// ==========================================================================
