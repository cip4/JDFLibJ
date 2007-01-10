/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSpanFrequencySelection.java
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
public class JDFSpanFrequencySelection extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    
    
    /**
     * Constructor for JDFSpanFrequencySelection
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanFrequencySelection(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanFrequencySelection
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanFrequencySelection(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanFrequencySelection
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanFrequencySelection(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    /**
     * Enumeration strings for EnumSpanFrequencySelection
     */
    public static class EnumSpanFrequencySelection extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanFrequencySelection(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanFrequencySelection getEnum(String enumName)
        {
            return (EnumSpanFrequencySelection) getEnum(EnumSpanFrequencySelection.class, enumName);
        }
        
        public static EnumSpanFrequencySelection getEnum(int enumValue)
        {
            return (EnumSpanFrequencySelection) getEnum(EnumSpanFrequencySelection.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanFrequencySelection.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanFrequencySelection.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanFrequencySelection.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanFrequencySelection.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanFrequencySelection LowestFrequency  = new EnumSpanFrequencySelection("LowestFrequency");
        public static final EnumSpanFrequencySelection MiddleFrequency  = new EnumSpanFrequencySelection("MiddleFrequency");
        public static final EnumSpanFrequencySelection HighestFrequency = new EnumSpanFrequencySelection("HighestFrequency");
        
    }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanFrequencySelection.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanFrequencySelection[ --> " + super.toString() + " ]";
    }
    
    
    
    public boolean init()
    {
        boolean b = super.init();
        setDataType(EnumDataType.EnumerationSpan);
        return b;
    }
}
