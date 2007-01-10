/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFComChannel.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoComChannel;


public class JDFComChannel extends JDFAutoComChannel
{
    private static final long serialVersionUID = 1L;


    public static class EnumChannelTypeDetails extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int        m_startValue     = 0;

        private EnumChannelTypeDetails (String name)
        {
            super(name, m_startValue++);
        }

        public static EnumChannelTypeDetails getEnum(String enumName)
        {
        	return (EnumChannelTypeDetails) getEnum(EnumChannelTypeDetails.class, enumName);
        }

        public static EnumChannelTypeDetails getEnum(int enumValue)
        {
            return (EnumChannelTypeDetails) getEnum(EnumChannelTypeDetails.class, enumValue);
        }

        public static Map getEnumMap()
        {
            return getEnumMap(EnumChannelTypeDetails.class);
        }

        public static List getEnumList()
        {
            return getEnumList(EnumChannelTypeDetails.class);
        }

        public static Iterator iterator()
        {
            return iterator(EnumChannelTypeDetails.class);
        }

        public static final EnumChannelTypeDetails Unknown  = new EnumChannelTypeDetails("Unknown");
        public static final EnumChannelTypeDetails LandLine = new EnumChannelTypeDetails("LandLine");
        public static final EnumChannelTypeDetails Mobile   = new EnumChannelTypeDetails("Mobile");
        public static final EnumChannelTypeDetails Secure   = new EnumChannelTypeDetails("Secure");
        public static final EnumChannelTypeDetails ISDN     = new EnumChannelTypeDetails("ISDN");
        public static final EnumChannelTypeDetails Form     = new EnumChannelTypeDetails("Form");
        public static final EnumChannelTypeDetails Target   = new EnumChannelTypeDetails("Target");
    }


    /**
     * Constructor for JDFComChannel
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFComChannel(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFComChannel
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFComChannel(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFComChannel
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFComChannel(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFComChannel[  --> " + super.toString() + " ]";
    }
}
