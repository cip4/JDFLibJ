/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSpanFinishedGrainDirection.java
 *
 */
package org.cip4.jdflib.span;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.w3c.dom.DOMException;


public class JDFSpanFinishedGrainDirection extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    
    /**
     * Constructor for JDFSpanFinishedGrainDirection
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanFinishedGrainDirection(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanFinishedGrainDirection
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanFinishedGrainDirection(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanFinishedGrainDirection
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanFinishedGrainDirection(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    
    /**
     * Enumeration strings for EnumSpanFinishedGrainDirection
     */
    public static class EnumSpanFinishedGrainDirection extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanFinishedGrainDirection(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanFinishedGrainDirection getEnum(String enumName)
        {
            return (EnumSpanFinishedGrainDirection) getEnum(EnumSpanFinishedGrainDirection.class, enumName);
        }
        
        public static EnumSpanFinishedGrainDirection getEnum(int enumValue)
        {
            return (EnumSpanFinishedGrainDirection) getEnum(EnumSpanFinishedGrainDirection.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanFinishedGrainDirection.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanFinishedGrainDirection.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanFinishedGrainDirection.class);
        }
        
        public static final EnumSpanFinishedGrainDirection ParallelToBind      = new EnumSpanFinishedGrainDirection("ParallelToBind");
        public static final EnumSpanFinishedGrainDirection PerpendicularToBind = new EnumSpanFinishedGrainDirection("PerpendicularToBind");
        
    }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    @Override
	public ValuedEnum getEnumType()
    {
        return EnumSpanFinishedGrainDirection.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    @Override
	public String toString()
    {
        return "JDFSpanFinishedGrainDirection[ --> " + super.toString() + " ]" ;
    }
    
    
    @Override
	public boolean init()
    {
        boolean b = super.init();
        setDataType(EnumDataType.EnumerationSpan);
        return b;
    }
    
    
}
