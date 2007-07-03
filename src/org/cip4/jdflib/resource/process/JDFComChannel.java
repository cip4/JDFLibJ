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
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.util.StringUtil;


public class JDFComChannel extends JDFAutoComChannel
{
    private static final long serialVersionUID = 1L;
    public static final String MAILTO="mailto:";
    public static final String TEL="tel:";

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

        /**
         * @deprectated - use null
         */
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
    
    /** 
     * sets locator to the string specified in eMail, checking valid email syntax
     * "mailto:" is prepended, if it is not yet there
     *  
     * @param eMail the email address
     * @throws IllegalArgumentException if eMail is not a valid emai as defined by JDFConstants.REGEXP_EMAIL
     */
    public void setEMailLocator(String eMail)
    {
        if(eMail!=null)
            eMail=eMail.trim();
        
        if(eMail==null|| !StringUtil.matchesIgnoreCase(eMail, JDFConstants.REGEXP_EMAIL))
        {
            throw new IllegalArgumentException("illegal email address:"+eMail);
        }
        setChannelType(EnumChannelType.Email);
        if(!eMail.toLowerCase().startsWith(MAILTO))
            eMail=MAILTO+eMail;
        setLocator(eMail);        
    }
    
    /**
     * get the email address of this, if this is an email address, else null
     * any "mailto" is stripped
     * @return
     */
    public String getEMailAddress()
    {
        if(!EnumChannelType.Email.equals(getChannelType()))
            return null;
        String locator=getLocator();
        if(!StringUtil.matchesIgnoreCase(locator, JDFConstants.REGEXP_EMAIL))
            return null;
        return StringUtil.stripPrefix(locator,MAILTO,true);        
    }
    /**
     * get the phone number of this, if this is a valid phone address, else null
     * any "tel:" or "fax:" is stripped
     * @param stripNonNumerical if true, remove any valid brackets, . / etc.
     *  so that a purely numerical code (except for an optional "+" for international) is returned
     * @return the phone number
     */
    public String getPhoneNumber(boolean stripNonNumerical)
    {
        final EnumChannelType channelType = getChannelType();
        if(!EnumChannelType.Fax.equals(channelType)&&!EnumChannelType.Phone.equals(channelType))
            return null;
        String locator=getLocator();
        if(!StringUtil.matchesIgnoreCase(locator, JDFConstants.REGEXP_PHONE))
            return null;
        locator= StringUtil.stripPrefix(locator,TEL,true);
        if(stripNonNumerical)
            locator=StringUtil.replaceCharSet(locator, "()., /", null, 0);
        return locator;
    }
    /**
     * set the phone number of this, if this is a valid phone address, 
     * any "tel:" or "fax:" is stripped
     * @param phone the phone number string
     * @param replaceForBlank the replacement char for non-leading blanks , typically "." or null are a good idea
     * @param channelType the channelType - must be either Fax or Phone
     * 
     */
    public void setPhoneNumber(String phone, String replaceForBlank, EnumChannelType channelType)
    {
        if(!EnumChannelType.Fax.equals(channelType)&&!EnumChannelType.Phone.equals(channelType))
            throw new IllegalArgumentException("illegal channelType: "+channelType);
        if(phone!=null)
            phone=phone.trim();

        phone=StringUtil.replaceCharSet(phone, " ", replaceForBlank, 0);
        if(phone==null|| !StringUtil.matches(phone, JDFConstants.REGEXP_PHONE))
        {
            throw new IllegalArgumentException("illegal phone number:"+phone);
        }
        setChannelType(channelType);
        if(!phone.toLowerCase().startsWith(TEL))
            phone=TEL+phone;
        setLocator(phone);      
    }

}
