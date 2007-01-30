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


public class JDFSpanMediaUnit extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanMediaUnit
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanMediaUnit(
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
    public JDFSpanMediaUnit(
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
    public JDFSpanMediaUnit(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
 
    
    /**
     * Enumeration strings for EnumSpanMediaUnit
     */
    public static class EnumSpanMediaUnit extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanMediaUnit(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanMediaUnit getEnum(String enumName)
        {
            return (EnumSpanMediaUnit) getEnum(EnumSpanMediaUnit.class, enumName);
        }
        
        public static EnumSpanMediaUnit getEnum(int enumValue)
        {
            return (EnumSpanMediaUnit) getEnum(EnumSpanMediaUnit.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanMediaUnit.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanMediaUnit.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanMediaUnit.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanMediaUnit.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanMediaUnit Roll     = new EnumSpanMediaUnit("Roll");
        public static final EnumSpanMediaUnit Sheet    = new EnumSpanMediaUnit("Sheet");
        
    }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanMediaUnit.getEnum(0);
    }
    
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanMediaUnit[  --> " + super.toString() + " ]" ;
    }
}



