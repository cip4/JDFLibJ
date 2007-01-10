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
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.w3c.dom.DOMException;


public class JDFSpanMediaType extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanMediaUnit
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanMediaType(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanMediaUnit
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanMediaType(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanMediaUnit
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanMediaType(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
        
    /**
     * Enumeration strings for EnumSpanMediaType
     */
    public static class EnumSpanMediaType extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanMediaType(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanMediaType getEnum(String enumName)
        {
            return (EnumSpanMediaType) getEnum(EnumSpanMediaType.class, enumName);
        }
        
        public static EnumSpanMediaType getEnum(int enumValue)
        {
            return (EnumSpanMediaType) getEnum(EnumSpanMediaType.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanMediaType.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanMediaType.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanMediaType.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanMediaType.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanMediaType Disc         = new EnumSpanMediaType("Disc");
        public static final EnumSpanMediaType Other        = new EnumSpanMediaType("Other");
        public static final EnumSpanMediaType Paper        = new EnumSpanMediaType("Paper");
        public static final EnumSpanMediaType Transparency = new EnumSpanMediaType("Transparency");
        
    }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanMediaType.getEnum(0);
    }
    
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanMediaType[  --> " + super.toString() + " ]" ;
    }
}



