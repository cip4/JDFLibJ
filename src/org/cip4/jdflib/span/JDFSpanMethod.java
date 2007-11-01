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


public class JDFSpanMethod extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanMethod
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanMethod(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanMethod
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanMethod(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanMethod
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanMethod(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    

    
    /**
     * Enumeration strings for EnumSpanMethod
     */
    public static class EnumSpanMethod extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanMethod(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanMethod getEnum(String enumName)
        {
            return (EnumSpanMethod) getEnum(EnumSpanMethod.class, enumName);
        }
        
        public static EnumSpanMethod getEnum(int enumValue)
        {
            return (EnumSpanMethod) getEnum(EnumSpanMethod.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanMethod.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanMethod.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanMethod.class);
        }
        
        public static final EnumSpanMethod BindIn  = new EnumSpanMethod("BindIn");
        public static final EnumSpanMethod BlowIn  = new EnumSpanMethod("BlowIn");
        
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
        return EnumSpanMethod.getEnum(0);
    }
    
    
    /**
     * toString
     *
     * @return String
     */
    @Override
	public String toString()
    {
        return "JDFSpanMethod[  --> " + super.toString() + " ]" ;
    }
}



