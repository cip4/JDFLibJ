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


public class JDFSpanEdgeShape extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanEdgeShape
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanEdgeShape(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanEdgeShape
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanEdgeShape(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanEdgeShape
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanEdgeShape(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * Enumeration strings for EnumSpanEdgeShape
     */
    public static class EnumSpanEdgeShape extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanEdgeShape(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanEdgeShape getEnum(String enumName)
        {
            return (EnumSpanEdgeShape) getEnum(EnumSpanEdgeShape.class, enumName);
        }
        
        public static EnumSpanEdgeShape getEnum(int enumValue)
        {
            return (EnumSpanEdgeShape) getEnum(EnumSpanEdgeShape.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanEdgeShape.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanEdgeShape.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanEdgeShape.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanEdgeShape.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanEdgeShape Rounded = new EnumSpanEdgeShape("Rounded");
        public static final EnumSpanEdgeShape Beveled = new EnumSpanEdgeShape("Beveled");
        
    }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanEdgeShape.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanEdgeShape[  --> " + super.toString() + " ]" ;
    }
}



