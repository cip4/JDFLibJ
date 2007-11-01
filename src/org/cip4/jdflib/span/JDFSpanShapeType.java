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


public class JDFSpanShapeType extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanShapeType
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanShapeType(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanShapeType
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanShapeType(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanShapeType
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanShapeType(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    /**
     * Enumeration strings for EnumSpanShapeType
     */
    public static class EnumSpanShapeType extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanShapeType(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanShapeType getEnum(String enumName)
        {
            return (EnumSpanShapeType) getEnum(EnumSpanShapeType.class, enumName);
        }
        
        public static EnumSpanShapeType getEnum(int enumValue)
        {
            return (EnumSpanShapeType) getEnum(EnumSpanShapeType.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanShapeType.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanShapeType.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanShapeType.class);
        }
        
        public static final EnumSpanShapeType Rectangular = new EnumSpanShapeType("Rectangular");
        public static final EnumSpanShapeType Round       = new EnumSpanShapeType("Round");
        public static final EnumSpanShapeType Path        = new EnumSpanShapeType("Path");
        
    }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    @Override
	public ValuedEnum getEnumType()
    {
        return EnumSpanShapeType.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    @Override
	public String toString()
    {
        return "JDFSpanShapeType[  --> " + super.toString() + " ]" ;
    }
}



