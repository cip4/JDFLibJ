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


public class JDFSpanSurplusHandling extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanSurplusHandling
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanSurplusHandling(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanSurplusHandling
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanSurplusHandling(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanSurplusHandling
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanSurplusHandling(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    
    /**
     * Enumeration strings for EnumSpanSurplusHandling
     */
    
    public static class EnumSpanSurplusHandling extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_stSurplusValue = 0;
        
        private EnumSpanSurplusHandling(String name)
        {
            super(name, m_stSurplusValue++);
        }
        
        public static EnumSpanSurplusHandling getEnum(String enumName)
        {
            return (EnumSpanSurplusHandling) getEnum(EnumSpanSurplusHandling.class, enumName);
        }
        
        public static EnumSpanSurplusHandling getEnum(int enumValue)
        {
            return (EnumSpanSurplusHandling) getEnum(EnumSpanSurplusHandling.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanSurplusHandling.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanSurplusHandling.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanSurplusHandling.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanSurplusHandling.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanSurplusHandling ReturnWithProduct = new EnumSpanSurplusHandling("ReturnWithProduct");
        public static final EnumSpanSurplusHandling Return            = new EnumSpanSurplusHandling("Return");
        public static final EnumSpanSurplusHandling Pickup            = new EnumSpanSurplusHandling("Pickup");
        public static final EnumSpanSurplusHandling Destroy           = new EnumSpanSurplusHandling("Destroy");
        public static final EnumSpanSurplusHandling PrinterOwns       = new EnumSpanSurplusHandling("PrinterOwns");
        public static final EnumSpanSurplusHandling Store             = new EnumSpanSurplusHandling("Store");
        
    }      
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanSurplusHandling.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanSurplusHandling[  --> " + super.toString() + " ]" ;
    }
}



