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


public class JDFSpanOpacity extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanOpacity
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanOpacity(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanOpacity
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanOpacity(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanOpacity
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanOpacity(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    /**
     * Enumeration strings for EnumSpanOpacity
     */
    public static class EnumSpanOpacity extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanOpacity(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanOpacity getEnum(String enumName)
        {
            return (EnumSpanOpacity) getEnum(EnumSpanOpacity.class, enumName);
        }
        
        public static EnumSpanOpacity getEnum(int enumValue)
        {
            return (EnumSpanOpacity) getEnum(EnumSpanOpacity.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanOpacity.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanOpacity.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanOpacity.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanOpacity.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanOpacity Opaque      = new EnumSpanOpacity("Opaque");
        public static final EnumSpanOpacity Translucent = new EnumSpanOpacity("Translucent");
        public static final EnumSpanOpacity Transparent = new EnumSpanOpacity("Transparent");
        
    }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanOpacity.getEnum(0);
    }
    
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanOpacity[  --> " + super.toString() + " ]" ;
    }
}



