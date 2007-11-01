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


public class JDFSpanWireCombMaterial extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanWireCombMaterial
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanWireCombMaterial(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanWireCombMaterial
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanWireCombMaterial(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanWireCombMaterial
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanWireCombMaterial(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    
    /**
     * Enumeration strings for EnumSpanWireCombMaterial
     */
    public static class EnumSpanWireCombMaterial extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanWireCombMaterial(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanWireCombMaterial getEnum(String enumName)
        {
            return (EnumSpanWireCombMaterial) getEnum(EnumSpanWireCombMaterial.class, enumName);
        }
        
        public static EnumSpanWireCombMaterial getEnum(int enumValue)
        {
            return (EnumSpanWireCombMaterial) getEnum(EnumSpanWireCombMaterial.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanWireCombMaterial.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanWireCombMaterial.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanWireCombMaterial.class);
        }
        
        public static final EnumSpanWireCombMaterial SteelSilver      = new EnumSpanWireCombMaterial("SteelSilver");
        public static final EnumSpanWireCombMaterial ColorCoatedSteel = new EnumSpanWireCombMaterial("ColorCoatedSteel");
        
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
        return EnumSpanWireCombMaterial.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    @Override
	public String toString()
    {
        return "JDFSpanWireCombMaterial[  --> " + super.toString() + " ]" ;
    }
}



