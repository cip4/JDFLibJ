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


public class JDFSpanGlueProcedure extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanGlueProcedure
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanGlueProcedure(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanGlueProcedure
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanGlueProcedure(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanGlueProcedure
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanGlueProcedure(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    /**
     * Enumeration strings for EnumSpanGlueProcedure
     */
    public static class EnumSpanGlueProcedure extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanGlueProcedure(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanGlueProcedure getEnum(String enumName)
        {
            return (EnumSpanGlueProcedure) getEnum(EnumSpanGlueProcedure.class, enumName);
        }
        
        public static EnumSpanGlueProcedure getEnum(int enumValue)
        {
            return (EnumSpanGlueProcedure) getEnum(EnumSpanGlueProcedure.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanGlueProcedure.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanGlueProcedure.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanGlueProcedure.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanGlueProcedure.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanGlueProcedure Spine      = new EnumSpanGlueProcedure("Spine");
        public static final EnumSpanGlueProcedure SideOnly   = new EnumSpanGlueProcedure("SideOnly");
        public static final EnumSpanGlueProcedure SingleSide = new EnumSpanGlueProcedure("SingleSide");
        public static final EnumSpanGlueProcedure SideSpine  = new EnumSpanGlueProcedure("SideSpine");
        
    }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanGlueProcedure.getEnum(0);
    }
    
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanGlueProcedure[  --> " + super.toString() + " ]" ;
    }
}



