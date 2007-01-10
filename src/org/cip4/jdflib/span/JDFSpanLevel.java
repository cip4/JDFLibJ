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


public class JDFSpanLevel extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanLevel
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanLevel(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanLevel
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanLevel(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanLevel
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanLevel(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    /**
     * Enumeration strings for EnumSpanLevel
     */
    public static class EnumSpanLevel extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanLevel(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanLevel getEnum(String enumName)
        {
            return (EnumSpanLevel) getEnum(EnumSpanLevel.class, enumName);
        }
        
        public static EnumSpanLevel getEnum(int enumValue)
        {
            return (EnumSpanLevel) getEnum(EnumSpanLevel.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanLevel.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanLevel.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanLevel.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanLevel.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanLevel SingleLevel = new EnumSpanLevel("SingleLevel");
        public static final EnumSpanLevel MultiLevel  = new EnumSpanLevel("MultiLevel");
        public static final EnumSpanLevel Sculpted    = new EnumSpanLevel("Sculpted");
        
    }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanLevel.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanLevel[  --> " + super.toString() + " ]" ;
    }
}



