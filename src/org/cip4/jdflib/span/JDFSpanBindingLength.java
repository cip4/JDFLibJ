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


public class JDFSpanBindingLength extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanBindingLength
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanBindingLength(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanBindingLength
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanBindingLength(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanBindingLength
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanBindingLength(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    
    /**
     * Enumeration strings for EnumSpanBindingLength
     */
    public static class EnumSpanBindingLength extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanBindingLength(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanBindingLength getEnum(String enumName)
        {
            return (EnumSpanBindingLength) getEnum(EnumSpanBindingLength.class, enumName);
        }
        
        public static EnumSpanBindingLength getEnum(int enumValue)
        {
            return (EnumSpanBindingLength) getEnum(EnumSpanBindingLength.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanBindingLength.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanBindingLength.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanBindingLength.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanBindingLength.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
         public static final EnumSpanBindingLength Long    = new EnumSpanBindingLength("Long");
        public static final EnumSpanBindingLength Short   = new EnumSpanBindingLength("Short");
        
    }      
    
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanBindingLength.getEnum(0);
    }
    
    //**************************************** Methods *********************************************
    
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanBindingLength[  --> " + super.toString() + " ]" ;
    }
}



