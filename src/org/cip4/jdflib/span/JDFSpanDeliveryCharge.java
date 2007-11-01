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

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.w3c.dom.DOMException;


public class JDFSpanDeliveryCharge extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFSpanDeliveryCharge
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFSpanDeliveryCharge(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFSpanDeliveryCharge
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanDeliveryCharge(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFSpanDeliveryCharge
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanDeliveryCharge(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

     /**
     * Enumeration strings for EnumSpanDeliveryCharge
     */

     public static class EnumSpanDeliveryCharge extends ValuedEnum
     {
         private static final long serialVersionUID = 1L;
         private static int m_startValue = 0;

         private EnumSpanDeliveryCharge(String name)
         {
             super(name, m_startValue++);
         }

         public static EnumSpanDeliveryCharge getEnum(String enumName)
         {
             return (EnumSpanDeliveryCharge) getEnum(EnumSpanDeliveryCharge.class, enumName);
         }

         public static EnumSpanDeliveryCharge getEnum(int enumValue)
         {
             return (EnumSpanDeliveryCharge) getEnum(EnumSpanDeliveryCharge.class, enumValue);
         }

         public static Map getEnumMap()
         {
             return getEnumMap(EnumSpanDeliveryCharge.class);
         }

         public static List getEnumList()
         {
             return getEnumList(EnumSpanDeliveryCharge.class);
         }

         public static Iterator iterator()
         {
             return iterator(EnumSpanDeliveryCharge.class);
         }

         public static final EnumSpanDeliveryCharge Printer = new EnumSpanDeliveryCharge("Printer");
         public static final EnumSpanDeliveryCharge Buyer = new EnumSpanDeliveryCharge("Buyer");
    
     }      
    
     @Override
	public ValuedEnum getEnumType()
     {
         return EnumSpanDeliveryCharge.getEnum(0);
     }
     
    //**************************************** Methods *********************************************
    

    /**
     * toString
     *
     * @return String
     */
    @Override
	public String toString()
    {
        return "JDFSpanDeliveryCharge[  --> " + super.toString() + " ]" ;
    }
}



