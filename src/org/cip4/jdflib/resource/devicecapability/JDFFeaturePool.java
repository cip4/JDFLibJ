/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFFeaturePool.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.devicecapability;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoFeaturePool;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.w3c.dom.DOMException;


public class JDFFeaturePool extends JDFAutoFeaturePool
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFFeaturePool
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFFeaturePool(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFFeaturePool
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFFeaturePool(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFFeaturePool
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFFeaturePool(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFFeaturePool[  --> " + super.toString() + " ]" ;
    }

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[13];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.BOOLEANSTATE, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.DATETIMESTATE, 0x33333333);
        elemInfoTable[2] = new ElemInfoTable(ElementName.DURATIONSTATE, 0x33333333);
        elemInfoTable[3] = new ElemInfoTable(ElementName.ENUMERATIONSTATE, 0x33333333);
        elemInfoTable[4] = new ElemInfoTable(ElementName.INTEGERSTATE, 0x33333333);
        elemInfoTable[5] = new ElemInfoTable(ElementName.MATRIXSTATE, 0x33333333);
        elemInfoTable[6] = new ElemInfoTable(ElementName.NAMESTATE, 0x33333333);
        elemInfoTable[7] = new ElemInfoTable(ElementName.NUMBERSTATE, 0x33333333);
        elemInfoTable[8] = new ElemInfoTable(ElementName.PDFPATHSTATE, 0x33333333);
        elemInfoTable[9] = new ElemInfoTable(ElementName.RECTANGLESTATE, 0x33333333);
        elemInfoTable[10] = new ElemInfoTable(ElementName.SHAPESTATE, 0x33333333);
        elemInfoTable[11] = new ElemInfoTable(ElementName.STRINGSTATE, 0x33333333);
        elemInfoTable[12] = new ElemInfoTable(ElementName.XYPAIRSTATE, 0x33333333);
    }

    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }    
}



