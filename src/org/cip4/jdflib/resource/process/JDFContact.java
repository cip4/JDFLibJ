/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFContact.java
 *
 * Last changes
 *
 * 2002-07-02 JG added support for extended ContactTypes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoContact;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.DOMException;


public class JDFContact extends JDFAutoContact
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFContact
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFContact(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFContact
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFContact(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFContact
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFContact(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
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
        return "JDFContact[  --> " + super.toString() + " ]";
    }
    
   /**
    * Set attribute ContactTypes
    *@param vKString value: the value to set the attribute to
    */
    public void setExtendedContactTypes (VString value)
    {
        setAttribute (AttributeName.CONTACTTYPES, StringUtil.setvString(value,JDFConstants.BLANK,null,null), JDFConstants.EMPTYSTRING);
    }

   /**
    * Get string attribute ContactTypes
    * @return vKString the vaue of the attribute 
    */
    public VString getExtendedContactTypes()
    {
        VString vstrTypes = new VString();
        vstrTypes.setAllStrings (getAttribute (AttributeName.CONTACTTYPES, 
            JDFConstants.EMPTYSTRING, JDFConstants.EMPTYSTRING), JDFConstants.BLANK);
        
        return vstrTypes;
    }

    
    
}
