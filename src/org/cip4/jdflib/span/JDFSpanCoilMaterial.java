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


public class JDFSpanCoilMaterial extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanCoilMaterial
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanCoilMaterial(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanCoilMaterial
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanCoilMaterial(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanCoilMaterial
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanCoilMaterial(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
     /**
     * Enumeration strings for EnumSpanCoilMaterial
     */
    public static class EnumSpanCoilMaterial extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanCoilMaterial(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanCoilMaterial getEnum(String enumName)
        {
            return (EnumSpanCoilMaterial) getEnum(EnumSpanCoilMaterial.class, enumName);
        }
        
        public static EnumSpanCoilMaterial getEnum(int enumValue)
        {
            return (EnumSpanCoilMaterial) getEnum(EnumSpanCoilMaterial.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanCoilMaterial.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanCoilMaterial.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanCoilMaterial.class);
        }
        
        public static final EnumSpanCoilMaterial Plastic          = new EnumSpanCoilMaterial("Plastic");
        public static final EnumSpanCoilMaterial Steel            = new EnumSpanCoilMaterial("Steel");
        public static final EnumSpanCoilMaterial ColorCoatedSteel = new EnumSpanCoilMaterial("ColorCoatedSteel");
        
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
        return EnumSpanCoilMaterial.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    @Override
	public String toString()
    {
        return "JDFSpanCoilMaterial[  --> " + super.toString() + " ]" ;
    }
}



