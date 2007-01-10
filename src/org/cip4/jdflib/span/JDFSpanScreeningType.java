/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSpanScreeningType.java
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

/**
 * time range class
 */
public class JDFSpanScreeningType extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    
    
    /**
     * Constructor for JDFSpanScreeningType
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanScreeningType(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanScreeningType
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanScreeningType(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanScreeningType
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanScreeningType(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    
    /**
     * Enumeration strings for EnumSpanScreeningType
     */
    public static class EnumSpanScreeningType extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanScreeningType(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanScreeningType getEnum(String enumName)
        {
            return (EnumSpanScreeningType) getEnum(EnumSpanScreeningType.class, enumName);
        }
        
        public static EnumSpanScreeningType getEnum(int enumValue)
        {
            return (EnumSpanScreeningType) getEnum(EnumSpanScreeningType.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanScreeningType.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanScreeningType.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanScreeningType.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanScreeningType.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanScreeningType AM       = new EnumSpanScreeningType("AM");
        public static final EnumSpanScreeningType FM       = new EnumSpanScreeningType("FM");
        
    }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanScreeningType.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanScreeningType[ --> " + super.toString() + " ]";
    }
    
 }
