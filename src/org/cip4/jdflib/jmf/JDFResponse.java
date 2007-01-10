/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFResponse.java
 *
 * Last changes
 *
 * 2002-07-02   JG - init() Also call super::init()
 */
package org.cip4.jdflib.jmf;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoResponse;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.resource.JDFNotification;


public class JDFResponse extends JDFAutoResponse //JDFMessage
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFResponse
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFResponse(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFResponse
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFResponse(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFResponse
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFResponse(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * inner class EnumClass
     */
    public static final class EnumError extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;

        private EnumError(String status) 
        {
            super(status, m_startValue++);
        }

        public static EnumError getEnum(String status) 
        {
            return (EnumError) getEnum(EnumError.class, status);
        }

        public static EnumError getEnum(int value) 
        {
            return (EnumError) getEnum(EnumError.class, value);
        }

        public static Map getEnumMap() 
        {
            return getEnumMap(EnumError.class);
        }

        public static List getEnumList() 
        {
            return getEnumList(EnumError.class);
        }

        public static Iterator iterator() 
        {
            return iterator(EnumError.class);
        }

        /**
         * Retrieve all allowed value names of this Enum in a vector
         * @deprecated
         * @return the <code>String Vector of</code> names
         */
        public static Vector getNamesVector() 
        {
            Vector namesVector = new Vector();
            Iterator it = iterator(EnumError.class);
            while (it.hasNext()) 
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }

            return namesVector;
        }

        /**
         * constants EnumError
         */
        public static final EnumError ErrorUnknown      = new EnumError("ErrorUnknown");
        public static final EnumError ErrorUnknownQuery = new EnumError("ErrorUnknownQuery");

    }

    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFResponse[  --> " + super.toString() + " ]";
    }

 

    /**
     * SetErrorText
     *
     * @param String et
     */
    public JDFNotification setErrorText(String et)
    {
        JDFNotification n=appendNotification();
        n.setType("Error");
        JDFComment c=n.appendComment();
        c.appendText(et);
        return n;
    }
}
