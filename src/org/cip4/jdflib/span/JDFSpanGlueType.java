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


public class JDFSpanGlueType extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanGlueType
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanGlueType(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanGlueType
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanGlueType(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanGlueType
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanGlueType(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    /**
     * Enumeration strings for EnumSpanGlueType
     */
    public static class EnumSpanGlueType extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanGlueType(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanGlueType getEnum(String enumName)
        {
            return (EnumSpanGlueType) getEnum(EnumSpanGlueType.class, enumName);
        }
        
        public static EnumSpanGlueType getEnum(int enumValue)
        {
            return (EnumSpanGlueType) getEnum(EnumSpanGlueType.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanGlueType.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanGlueType.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanGlueType.class);
        }
        
        public static final EnumSpanGlueType Removable = new EnumSpanGlueType("Removable");
        public static final EnumSpanGlueType Permanent = new EnumSpanGlueType("Permanent");
        
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
        return EnumSpanGlueType.getEnum(0);
    }
    
    
    /**
     * toString
     *
     * @return String
     */
    @Override
	public String toString()
    {
        return "JDFSpanGlueType[  --> " + super.toString() + " ]" ;
    }
}



