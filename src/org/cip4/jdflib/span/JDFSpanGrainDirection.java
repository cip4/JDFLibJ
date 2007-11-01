/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSpanGrainDirection.java
 *
 */
package org.cip4.jdflib.span;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.w3c.dom.DOMException;


public class JDFSpanGrainDirection extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    
    /**
     * Constructor for JDFSpanGrainDirection
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanGrainDirection(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanGrainDirection
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanGrainDirection(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanGrainDirection
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanGrainDirection(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    

    /**
     * Enumeration strings for EnumSpanGrainDirection
     */
    public static class EnumSpanGrainDirection extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanGrainDirection(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanGrainDirection getEnum(String enumName)
        {
            return (EnumSpanGrainDirection) getEnum(EnumSpanGrainDirection.class, enumName);
        }
        
        public static EnumSpanGrainDirection getEnum(int enumValue)
        {
            return (EnumSpanGrainDirection) getEnum(EnumSpanGrainDirection.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanGrainDirection.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanGrainDirection.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanGrainDirection.class);
        }
        
        public static final EnumSpanGrainDirection ShortEdge = new EnumSpanGrainDirection("ShortEdge");
        public static final EnumSpanGrainDirection LongEdge  = new EnumSpanGrainDirection("LongEdge");
        
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
        return EnumSpanGrainDirection.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    @Override
	public String toString()
    {
        return "JDFSpanGrainDirection[ --> " + super.toString() + " ]" ;
    }
    
    
    @Override
	public boolean init()
    {
        boolean b = super.init();
        setDataType(EnumDataType.EnumerationSpan);
        return b;
    }
    
    
}
