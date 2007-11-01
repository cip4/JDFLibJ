/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFCutBlock.java
 *
 */
package org.cip4.jdflib.resource.process;

import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoCutBlock;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.w3c.dom.DOMException;


public class JDFCutBlock extends JDFAutoCutBlock
{
    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.BLOCKNAME, 0x22222222, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }
    /**
     * Constructor for JDFCutBlock
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFCutBlock(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFCutBlock
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFCutBlock(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFCutBlock
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFCutBlock(
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
    @Override
	public String toString()
    {
        return "JDFCutBlock[  --> " + super.toString() + " ]";
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    /**
     * get the vector of implicitly define partition keys that MUST NOT be used in the resource
     */
    @Override
	public Vector getImplicitPartitions()
    {
        Vector<ValuedEnum> v = super.getImplicitPartitions();
        if(v==null)
            v=new Vector<ValuedEnum>();
        v.add(EnumPartIDKey.BlockName);
        return v;
    }

    
}
