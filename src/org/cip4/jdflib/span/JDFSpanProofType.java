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


public class JDFSpanProofType extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanProofType
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanProofType(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanProofType
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanProofType(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanProofType
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanProofType(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    
    /**
     * Enumeration strings for EnumSpanProofType
     */
    public static class EnumSpanProofType extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanProofType(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanProofType getEnum(String enumName)
        {
            return (EnumSpanProofType) getEnum(EnumSpanProofType.class, enumName);
        }
        
        public static EnumSpanProofType getEnum(int enumValue)
        {
            return (EnumSpanProofType) getEnum(EnumSpanProofType.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanProofType.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanProofType.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanProofType.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanProofType.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanProofType Page       = new EnumSpanProofType("Page");
        public static final EnumSpanProofType Imposition = new EnumSpanProofType("Imposition");
        public static final EnumSpanProofType None       = new EnumSpanProofType("None");
    }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanProofType.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanProofType[  --> " + super.toString() + " ]" ;
    }
}



