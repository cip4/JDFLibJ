/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSpanGlue.java
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

/**
 *defines the data type dependent parts of a ranged Span resource
 */
public class JDFSpanGlue extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanGlue
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanGlue(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanGlue
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanGlue(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanGlue
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanGlue(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    /**
     * Enumeration strings for EnumSpanGlue
     */
    
    public static class EnumSpanGlue extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanGlue(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanGlue getEnum(String enumName)
        {
            return (EnumSpanGlue) getEnum(EnumSpanGlue.class, enumName);
        }
        
        public static EnumSpanGlue getEnum(int enumValue)
        {
            return (EnumSpanGlue) getEnum(EnumSpanGlue.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanGlue.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanGlue.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanGlue.class);
        }
        
        public static final EnumSpanGlue ColdGlue = new EnumSpanGlue("ColdGlue");
        public static final EnumSpanGlue Hotmelt  = new EnumSpanGlue("Hotmelt");
        public static final EnumSpanGlue PUR      = new EnumSpanGlue("PUR");
        
    }      
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    @Override
	public ValuedEnum getEnumType()
    {
        return EnumSpanGlue.getEnum(0);
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
        return "JDFSpanGlue[  --> " + super.toString() + " ]" ;
    }
}
