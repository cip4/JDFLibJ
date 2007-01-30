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


public class JDFSpanBindingSide extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanBindingSide
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanBindingSide(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanBindingSide
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanBindingSide(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanBindingSide
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanBindingSide(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    
    /**
     * Enumeration strings for EnumSpanBindingSide
     */
    public static class EnumSpanBindingSide extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanBindingSide(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanBindingSide getEnum(String enumName)
        {
            return (EnumSpanBindingSide) getEnum(EnumSpanBindingSide.class, enumName);
        }
        
        public static EnumSpanBindingSide getEnum(int enumValue)
        {
            return (EnumSpanBindingSide) getEnum(EnumSpanBindingSide.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanBindingSide.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanBindingSide.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanBindingSide.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanBindingSide.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanBindingSide Top     = new EnumSpanBindingSide("Top");
        public static final EnumSpanBindingSide Bottom  = new EnumSpanBindingSide("Bottom");
        public static final EnumSpanBindingSide Right   = new EnumSpanBindingSide("Right");
        public static final EnumSpanBindingSide Left    = new EnumSpanBindingSide("Left");
        
    }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     *
     * @return  The enum that this span is linked to
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanBindingSide.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanBindingSide[  --> " + super.toString() + " ]" ;
    }
}



