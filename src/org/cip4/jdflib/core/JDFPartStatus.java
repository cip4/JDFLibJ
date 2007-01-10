/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * String myLocalName)
 *
 * Last changes
 *
 */
package org.cip4.jdflib.core;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPartStatus;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.pool.JDFStatusPool;

/**
 *
 */
public class JDFPartStatus extends JDFAutoPartStatus
{
    private static final long serialVersionUID = 1L;

     /**
      * Constructor for JDFPartStatus
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFPartStatus(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFPartStatus
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFPartStatus(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFPartStatus
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFPartStatus(
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
        return "JDFAudit[ -->" + super.toString() + "]";
    }

    /**
     * @return JDFAttributeMap the attribute map of this
     */
    public JDFAttributeMap getPartMap()
    {
        return super.getPartMap();
    }
   
   /**
     * SetPartMap - set all parts to those define in vParts
     *
     * @param mPart attribute map for the part to set
     */
    public void setPartMap(JDFAttributeMap mPart)
    {
        super.setPartMap(mPart);
    }
  
     
    /**
     * Gets string value of attribute StatusDetails, 
     * if it is not specified, inherit it from the StatusPool
     *
     * @return String: the attribute value
     */
    public String getStatusDetails()
    {
        if(hasAttribute(AttributeName.STATUSDETAILS))
        {
            return super.getStatusDetails();
        }
        final JDFStatusPool pool = (JDFStatusPool)getParentNode();
        return pool.getStatusDetails();
    }

    
    /**
     * Gets enum value of attribute Status, 
     * if it is not specified, inherit it from the StatusPool
     *
     * @return EnumNodeStatus: the attribute value
     */
    public EnumNodeStatus getStatus()
    {
        if(hasAttribute(AttributeName.STATUS))
        {
            return super.getStatus();
        }
        final JDFStatusPool pool = (JDFStatusPool)getParentNode();
        return pool.getStatus();
    }
}
