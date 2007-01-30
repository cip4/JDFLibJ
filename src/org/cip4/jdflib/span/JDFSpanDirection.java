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


public class JDFSpanDirection extends JDFEnumerationSpan
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFSpanDirection
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFSpanDirection(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFSpanDirection
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpanDirection(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFSpanDirection
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpanDirection(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * Enumeration strings for EnumSpanDirection
     */
     public static class EnumSpanDirection extends ValuedEnum
     {
         private static final long serialVersionUID = 1L;
         private static int m_startValue = 0;

         private EnumSpanDirection(String name)
         {
             super(name, m_startValue++);
         }

         public static EnumSpanDirection getEnum(String enumName)
         {
             return (EnumSpanDirection) getEnum(EnumSpanDirection.class, enumName);
         }

         public static EnumSpanDirection getEnum(int enumValue)
         {
             return (EnumSpanDirection) getEnum(EnumSpanDirection.class, enumValue);
         }

         public static Map getEnumMap()
         {
             return getEnumMap(EnumSpanDirection.class);
         }

         public static List getEnumList()
         {
             return getEnumList(EnumSpanDirection.class);
         }

         public static Iterator iterator()
         {
             return iterator(EnumSpanDirection.class);
         }

         public static Vector getNamesVector()
         {
             Vector namesVector = new Vector();
             Iterator it = iterator(EnumSpanDirection.class);
             while (it.hasNext())
             {
                 namesVector.addElement(((ValuedEnum) it.next()).getName());
             }

             return namesVector;
         }

         public static final EnumSpanDirection Both      = new EnumSpanDirection("Both");
         public static final EnumSpanDirection Depressed = new EnumSpanDirection("Depressed");
         public static final EnumSpanDirection Raised    = new EnumSpanDirection("Raised");
     
     }      
    
    
    //**************************************** Methods *********************************************
    
    /**
     * AllowedValues - vector of allowed values for this EnumerationSpan
     *
     * @return Vector - vector representation of the allowed values
     */
     public ValuedEnum getEnumType()
     {
         return EnumSpanDirection.getEnum(0);
     }

    /**
     * toStringÙ
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpanDirection[  --> " + super.toString() + " ]" ;
    }
}



