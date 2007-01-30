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


public class JDFSpanSurface extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanSurface
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanSurface(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanSurface
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanSurface(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanSurface
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanSurface(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    /**
     * Enumeration strings for EnumSpanSurface
     */
    public static class EnumSpanSurface extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanSurface(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanSurface getEnum(String enumName)
        {
            return (EnumSpanSurface) getEnum(EnumSpanSurface.class, enumName);
        }
        
        public static EnumSpanSurface getEnum(int enumValue)
        {
            return (EnumSpanSurface) getEnum(EnumSpanSurface.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanSurface.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanSurface.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanSurface.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanSurface.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanSurface Unknown = new EnumSpanSurface("Unknown");
        public static final EnumSpanSurface Front   = new EnumSpanSurface("Front");
        public static final EnumSpanSurface Back    = new EnumSpanSurface("Back");
        public static final EnumSpanSurface Both    = new EnumSpanSurface("Both");
        
    }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanSurface.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanSurface[  --> " + super.toString() + " ]" ;
    }
}



