/**
==========================================================================
class JDFMsgFilter extends JDFResource
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
**/





package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoMsgFilter;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;



//----------------------------------
    public class JDFMsgFilter extends JDFAutoMsgFilter
{
    private static final long serialVersionUID = 1L;

     /**
      * Constructor for JDFMsgFilter
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFMsgFilter(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFMsgFilter
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFMsgFilter(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFMsgFilter
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFMsgFilter(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    public String toString()
    {
        return "JDFMsgFilter[  --> " + super.toString() + " ]";
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
    * @param vParts vector of attribute maps for the parts
    */
   public void setPartMapVector(VJDFAttributeMap vParts)
   {
       super.setPartMapVector(vParts);
   }

   /**
    * set all parts to those define in vParts
    * @param mPart attribute map for the part to set
    */
   public void setPartMap(JDFAttributeMap mPart)
   {
       super.setPartMap(mPart);
   }

   /**
    * remove the part defined in mPart
    * @param mPart attribute map for the part to remove
    */
   public void removePartMap(JDFAttributeMap mPart)
   {
       super.removePartMap(mPart);
   }

   /**
    * check whether the part defined in mPart is included
    * @param mPart attribute map to look for
    * @return boolean - returns true if the part exists
    */
   public boolean hasPartMap(JDFAttributeMap mPart)
   {
       return super.hasPartMap(mPart);
   }
}



