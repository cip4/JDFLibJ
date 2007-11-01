/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSpanSizePolicy.java
 *
 */
package org.cip4.jdflib.span;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.w3c.dom.DOMException;


public class JDFSpanSizePolicy extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    
    /**
     * Constructor for JDFSpanSizePolicy
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanSizePolicy(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanSizePolicy
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanSizePolicy(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanSizePolicy
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanSizePolicy(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    
    /**
     * Enumeration strings for EnumSpanSizePolicy
     */
    public static class EnumSpanSizePolicy extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanSizePolicy(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanSizePolicy getEnum(String enumName)
        {
            return (EnumSpanSizePolicy) getEnum(EnumSpanSizePolicy.class, enumName);
        }
        
        public static EnumSpanSizePolicy getEnum(int enumValue)
        {
            return (EnumSpanSizePolicy) getEnum(EnumSpanSizePolicy.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanSizePolicy.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanSizePolicy.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanSizePolicy.class);
        }
        
        public static final EnumSpanSizePolicy ClipToMaxPage = new EnumSpanSizePolicy("ClipToMaxPage");
        public static final EnumSpanSizePolicy FitToPage     = new EnumSpanSizePolicy("FitToPage");
        public static final EnumSpanSizePolicy ReduceToFit   = new EnumSpanSizePolicy("ReduceToFit");
        public static final EnumSpanSizePolicy Tile          = new EnumSpanSizePolicy("Tile");
        
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
        return EnumSpanSizePolicy.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    @Override
	public String toString()
    {
        return "JDFSpanSizePolicy[ --> " + super.toString() + " ]" ;
    }
    
    
}
