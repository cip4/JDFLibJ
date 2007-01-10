/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFMedia
 *
 * Last changes
 *
 * 2002-07-02 JG added ValidMediaType() to correctly handle explicit Unknown enumeration
 * 2002-07-02 JG de-inlined copy constructor
 *
 */

package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoMedia;
import org.cip4.jdflib.datatypes.JDFXYPair;


public class JDFMedia extends JDFAutoMedia
{
    private static final long serialVersionUID = 1L;


    /**
     * Constructor for JDFMedia
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFMedia(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFMedia
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFMedia(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFMedia
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFMedia(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    public String toString()
    {
        return "JDFMedia[  --> " + super.toString() + " ]";
    }
    
       
    /**
    * Set attribute Dimension (in point)
    * @param JDFXYPair value: the value (in centimeter) to set the dimension to
    */
    public void setDimensionCM (JDFXYPair value)
    {
        JDFXYPair xyp = new JDFXYPair(value);  // don't change the original
        xyp.scale(72.0/2.54);
        setDimension(xyp);
    }
        
   /**
    * Get attribute Dimension in centimeter
    * @return JDFXYPair the dimension in centimeter 
    */
    public JDFXYPair getDimensionCM()
    {
        JDFXYPair xyp = getDimension();
        xyp.scale(2.54/72.0);
        return xyp;
    }

        
    /**
     * Set attribute Dimension (in point)
     * @param JDFXYPair value: the value (in inch) to set the dimension to
     */
     public void setDimensionInch (JDFXYPair value)
     {
         JDFXYPair xyp = new JDFXYPair(value);  // don't change the original
         xyp.scale(72.0);
         setDimension(xyp);
     }
         
    /**
     * Get attribute Dimension in inch
     * @return JDFXYPair the dimension in inch 
     */
     public JDFXYPair getDimensionInch()
     {
         JDFXYPair xyp = getDimension();
         xyp.scale(1.0/72.0);
         return xyp;
     }
}