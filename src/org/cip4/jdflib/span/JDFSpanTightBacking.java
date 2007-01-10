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


public class JDFSpanTightBacking extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanTightBacking
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanTightBacking(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanTightBacking
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanTightBacking(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanTightBacking
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanTightBacking(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    /**
     * Enumeration strings for EnumSpanTightBacking
     */
    public static class EnumSpanTightBacking extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanTightBacking(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanTightBacking getEnum(String enumName)
        {
            return (EnumSpanTightBacking) getEnum(EnumSpanTightBacking.class, enumName);
        }
        
        public static EnumSpanTightBacking getEnum(int enumValue)
        {
            return (EnumSpanTightBacking) getEnum(EnumSpanTightBacking.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanTightBacking.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanTightBacking.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanTightBacking.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanTightBacking.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanTightBacking Flat        = new EnumSpanTightBacking("Flat");
        public static final EnumSpanTightBacking Round       = new EnumSpanTightBacking("Round");
        public static final EnumSpanTightBacking FlatBacked  = new EnumSpanTightBacking("FlatBacked");
        public static final EnumSpanTightBacking RoundBacked = new EnumSpanTightBacking("RoundBacked");
        
    }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanTightBacking.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanTightBacking[  --> " + super.toString() + " ]" ;
    }
}



