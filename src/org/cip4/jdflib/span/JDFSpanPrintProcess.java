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


public class JDFSpanPrintProcess extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFSpanPrintProcess
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanPrintProcess(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFSpanPrintProcess
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanPrintProcess(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSpanPrintProcess
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanPrintProcess(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    /**
     * Enumeration strings for EnumSpanPrintProcess
     */
    public static class EnumSpanPrintProcess extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSpanPrintProcess(String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumSpanPrintProcess getEnum(String enumName)
        {
            return (EnumSpanPrintProcess) getEnum(EnumSpanPrintProcess.class, enumName);
        }
        
        public static EnumSpanPrintProcess getEnum(int enumValue)
        {
            return (EnumSpanPrintProcess) getEnum(EnumSpanPrintProcess.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpanPrintProcess.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumSpanPrintProcess.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumSpanPrintProcess.class);
        }
        
        public static Vector getNamesVector()
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumSpanPrintProcess.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumSpanPrintProcess Electrophotography = new EnumSpanPrintProcess("Electrophotography");
        public static final EnumSpanPrintProcess Flexography        = new EnumSpanPrintProcess("Flexography");
        public static final EnumSpanPrintProcess Gravure            = new EnumSpanPrintProcess("Gravure");
        public static final EnumSpanPrintProcess Lithography        = new EnumSpanPrintProcess("Lithography");
        public static final EnumSpanPrintProcess Letterpress        = new EnumSpanPrintProcess("Letterpress");
        public static final EnumSpanPrintProcess Screen             = new EnumSpanPrintProcess("Screen");
        public static final EnumSpanPrintProcess Inkjet             = new EnumSpanPrintProcess("Inkjet");
        public static final EnumSpanPrintProcess Thermography       = new EnumSpanPrintProcess("Thermography");
        
    }      
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
    public ValuedEnum getEnumType()
    {
        return EnumSpanPrintProcess.getEnum(0);
    }
    
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanPrintProcess[  --> " + super.toString() + " ]" ;
    }
}



