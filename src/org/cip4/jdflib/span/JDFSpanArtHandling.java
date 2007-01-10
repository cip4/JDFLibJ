/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFAmount.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.span;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.w3c.dom.DOMException;


public class JDFSpanArtHandling extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanArtHandling
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanArtHandling(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanArtHandling
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanArtHandling(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanArtHandling
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanArtHandling(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    
    /**
     * Enumeration strings for EnumSpanArtHandling
     */
    
    public static class EnumSpanArtHandling extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanArtHandling(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanArtHandling getEnum(String enumName)
        {
            return (EnumSpanArtHandling) getEnum(EnumSpanArtHandling.class, enumName);
        }
        
        public static EnumSpanArtHandling getEnum(int enumValue)
        {
            return (EnumSpanArtHandling) getEnum(EnumSpanArtHandling.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanArtHandling.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanArtHandling.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanArtHandling.class);
        }
        
        public static final EnumSpanArtHandling ReturnWithProof   = new EnumSpanArtHandling("ReturnWithProof");
        public static final EnumSpanArtHandling ReturnWithProduct = new EnumSpanArtHandling("ReturnWithProduct");
        public static final EnumSpanArtHandling Return            = new EnumSpanArtHandling("Return");
        public static final EnumSpanArtHandling Pickup            = new EnumSpanArtHandling("Pickup");
        public static final EnumSpanArtHandling Destroy           = new EnumSpanArtHandling("Destroy");
        public static final EnumSpanArtHandling PrinterOwns       = new EnumSpanArtHandling("PrinterOwns");
        public static final EnumSpanArtHandling Store             = new EnumSpanArtHandling("Store");
        
    }      
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanArtHandling.getEnum(0);
    }
    
    //**************************************** Methods *********************************************
    
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanArtHandling[  --> " + super.toString() + " ]" ;
    }
}



