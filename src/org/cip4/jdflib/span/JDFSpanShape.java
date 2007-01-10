/**
 * JDFSpanShape.java
 *
 * Copyright (c) 2001-2004 Heidelberger Druckmaschinen AG, All Rights Reserved.
 */
package org.cip4.jdflib.span;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.w3c.dom.DOMException;


public class JDFSpanShape extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanShape
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanShape(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanShape
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanShape(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanShape
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanShape(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
     
    /**
     * Enumeration strings for EnumSpanShape
     */
    public static class EnumSpanShape extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanShape(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanShape getEnum(String enumName)
        {
            return (EnumSpanShape) getEnum(EnumSpanShape.class, enumName);
        }
        
        public static EnumSpanShape getEnum(int enumValue)
        {
            return (EnumSpanShape) getEnum(EnumSpanShape.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanShape.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanShape.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanShape.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanShape.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanShape RoundedBack = new EnumSpanShape("RoundedBack");
        public static final EnumSpanShape SquareBack  = new EnumSpanShape("SquareBack");
        
    }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanShape.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanShape[  --> " + super.toString() + " ]" ;
    }
}



