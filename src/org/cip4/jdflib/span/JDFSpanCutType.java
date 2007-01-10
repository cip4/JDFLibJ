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


public class JDFSpanCutType extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFSpanCutType
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFSpanCutType(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFSpanCutType
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanCutType(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFSpanCutType
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanCutType(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * Enumeration strings for EnumSpanCutType
     */
     public static class EnumSpanCutType extends ValuedEnum
     {
         private static final long serialVersionUID = 1L;
         private static int m_startValue = 0;

         private EnumSpanCutType(String name)
         {
             super(name, m_startValue++);
         }

         public static EnumSpanCutType getEnum(String enumName)
         {
             return (EnumSpanCutType) getEnum(EnumSpanCutType.class, enumName);
         }

         public static EnumSpanCutType getEnum(int enumValue)
         {
             return (EnumSpanCutType) getEnum(EnumSpanCutType.class, enumValue);
         }

         public static Map getEnumMap()
         {
             return getEnumMap(EnumSpanCutType.class);
         }

         public static List getEnumList()
         {
             return getEnumList(EnumSpanCutType.class);
         }

         public static Iterator iterator()
         {
             return iterator(EnumSpanCutType.class);
         }

         public static Vector getNamesVector()
         {
             Vector namesVector = new Vector();
             Iterator it = iterator(EnumSpanCutType.class);
             while (it.hasNext())
             {
                 namesVector.addElement(((ValuedEnum) it.next()).getName());
             }

             return namesVector;
         }

         public static final EnumSpanCutType Cut       = new EnumSpanCutType("Cut");
         public static final EnumSpanCutType Perforate = new EnumSpanCutType("Perforate");
     
     }      
    
    
    //**************************************** Methods *********************************************
    
   /**
    * AllowedValues - vector of allowed values for this EnumerationSpan
    *
    * @return Vector - vector representation of the allowed values
    */
    public ValuedEnum getEnumType()
    {
        return EnumSpanCutType.getEnum(0);
    }

    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanCutType[  --> " + super.toString() + " ]" ;
    }
}



