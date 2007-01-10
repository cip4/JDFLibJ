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


public class JDFSpanStripMaterial extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanStripMaterial
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanStripMaterial(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanStripMaterial
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanStripMaterial(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanStripMaterial
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanStripMaterial(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    
    /**
     * Enumeration strings for EnumSpanStripMaterial
     */
    public static class EnumSpanStripMaterial extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanStripMaterial(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanStripMaterial getEnum(String enumName)
        {
            return (EnumSpanStripMaterial) getEnum(EnumSpanStripMaterial.class, enumName);
        }
        
        public static EnumSpanStripMaterial getEnum(int enumValue)
        {
            return (EnumSpanStripMaterial) getEnum(EnumSpanStripMaterial.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanStripMaterial.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanStripMaterial.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanStripMaterial.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanStripMaterial.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanStripMaterial Calico           = new EnumSpanStripMaterial("Calico");
        public static final EnumSpanStripMaterial Cardboard        = new EnumSpanStripMaterial("Cardboard");
        public static final EnumSpanStripMaterial CrepePaper       = new EnumSpanStripMaterial("CrepePaper");
        public static final EnumSpanStripMaterial Gauze            = new EnumSpanStripMaterial("Gauze");
        public static final EnumSpanStripMaterial Paper            = new EnumSpanStripMaterial("Paper");
        public static final EnumSpanStripMaterial PaperlinedMules  = new EnumSpanStripMaterial("PaperlinedMules");
        public static final EnumSpanStripMaterial Tape             = new EnumSpanStripMaterial("Tape");
        
    }      
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanStripMaterial.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanStripMaterial[  --> " + super.toString() + " ]" ;
    }
}



