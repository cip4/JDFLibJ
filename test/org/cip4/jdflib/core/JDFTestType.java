/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFComment.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.core;

import org.apache.xerces.dom.CoreDocumentImpl;

public class JDFTestType extends JDFElement
{
    private static final long serialVersionUID = 1L;
    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
    static 
    {
        atrInfoTable[0] =  new AtrInfoTable("fnarf", 0x33333333, AttributeInfo.EnumAttributeType.integer, null,null);
    }
     
    /**
     * Constructor for JDFTestType
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFTestType(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }
	
   /**
     * Constructor for JDFTestType
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFTestType(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }

    /**
     * Constructor for JDFTestType
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFTestType(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    //**************************************** Methods *********************************************
    /**
     * toString - StringRepresentation of JDFNode
     *
     * @return String
     */
    public String toString()
    {
        return "JDFTestType[  --> " + super.toString() + " ]";
    }

 
	/**
	* version fixing routine for JDF
	*
	* uses heuristics to modify this element and its children to be compatible with a given version
	* in general, it will be able to move from low to high versions but potentially fail 
	* when attempting to move from higher to lower versions
	*
	* @param version: version that the resulting element should correspond to
	* @return true if successful
	*/
   public boolean fixVersion(EnumVersion version)
    {
		if(version.getValue()>=EnumVersion.Version_1_3.getValue()){
			appendAnchor(null);
		}else{
			removeAttribute(AttributeName.ID);
		}
		return super.fixVersion(version);
	}
}
