/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFArtDelivery.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.intent;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoArtDelivery;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFSpanArtHandling;
import org.cip4.jdflib.span.JDFSpanDeliveryCharge;
import org.cip4.jdflib.span.JDFSpanTransfer;
import org.cip4.jdflib.span.JDFStringSpan;
import org.w3c.dom.DOMException;


public class JDFArtDelivery extends JDFAutoArtDelivery
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFArtDelivery
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFArtDelivery(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFArtDelivery
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFArtDelivery(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFArtDelivery
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFArtDelivery(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
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
        return "JDFArtDelivery[  --> " + super.toString() + " ]" ;
    }

    /**
    * Get parent node of 'this' - node ArtDeliveryIntent
    * @return JDFArtDeliveryIntent: ArtDeliveryIntent node
    */
    public JDFArtDeliveryIntent getParentArtDeliveryIntent()
    {
        return (JDFArtDeliveryIntent)getParentNode();
    }
    
    /**
    * Get of 'this' the value of element ArtHandling.
    * If not specified, get the default value of element ArtHandling,
    * that is specified in it's parent element (node ArtDeliveryIntent)
    * @return JDFSpanArtHandling: element value
    */  
    public JDFSpanArtHandling getArtHandling() 
    {
        if (hasChildElement(ElementName.ARTHANDLING, null))
        {
            return super.getArtHandling();
        }
        return getParentArtDeliveryIntent().getArtHandling();
    }
    
    /**
    * Get of 'this' the value of element DeliveryCharge.
    * If not specified, get the default value of element DeliveryCharge,
    * that is specified in it's parent element (node ArtDeliveryIntent)
    * @return JDFSpanDeliveryCharge: element value 
    */  
    public JDFSpanDeliveryCharge getDeliveryCharge()
    {
        if (hasChildElement(ElementName.DELIVERYCHARGE, null))
        {
            return super.getDeliveryCharge();
        }
        return getParentArtDeliveryIntent().getDeliveryCharge();
    }
    
    /**
    * Get of 'this' the value of element Method.
    * If not specified, get the default value of element Method,
    * that is specified in it's parent element (node ArtDeliveryIntent)
    * @return JDFNameSpan: element value
    */
    public JDFNameSpan getMethod()
    {
        if (hasChildElement(ElementName.METHOD, null))
        {
            return super.getMethod();
        }
        return getParentArtDeliveryIntent().getMethod();
    }
    
    /**
    * Get of 'this' the value of attribute PreflightStatus.
    * If not specified, get the default value of attribute PreflightStatus,
    * that is specified in it's parent element (node ArtDeliveryIntent)
    * @return EnumPreflightStatus: attribute value
    */
    //TODO this does not work!
//    public JDFArtDelivery.EnumPreflightStatus getPreflightStatus()
//    {
//        if (hasAttribute(AttributeName.PREFLIGHTSTATUS, null, false))
//        {
//            return super.getPreflightStatus();
//        }
//        return getParentArtDeliveryIntent().getPreflightStatus();
//    }
    
    /**
    * Get of 'this' the value of element ReturnMethod.
    * If not specified, get the default value of element ReturnMethod,
    * that is specified in it's parent element (node ArtDeliveryIntent)
    * @return JDFNameSpan: element value
    */
    public JDFNameSpan getReturnMethod()  
    {
        if (hasChildElement(ElementName.RETURNMETHOD, null))
        {
            return super.getReturnMethod();
        }
        return getParentArtDeliveryIntent().getReturnMethod();
    }
    
    /**
    * Get of 'this' the value of element ServiceLevel.
    * If not specified, get the default value of element ServiceLevel,
    * that is specified in it's parent element (node ArtDeliveryIntent)
    * @return JDFStringSpan: element value
    */  
    public JDFStringSpan getServiceLevel()
    {
        if (hasChildElement(ElementName.SERVICELEVEL, null))
        {
            return super.getServiceLevel();
        }
        return getParentArtDeliveryIntent().getServiceLevel();
    }
    
    /**
    * Get of 'this' the value of element Transfer.
    * If not specified, get the default value of element Transfer,
    * that is specified in it's parent element (node ArtDeliveryIntent)
    * @return JDFSpanTransfer: element value
    */  
    public JDFSpanTransfer getTransfer() 
    {
        if (hasChildElement(ElementName.TRANSFER, null))
        {
            return super.getTransfer();
        }
        return getParentArtDeliveryIntent().getTransfer();
    }
    
    /**
    * Get of 'this' the iSkip-th child element Contact. If not specified,
    * get the child element Contact of it's parent element (node ArtDeliveryIntent)
    * @return JDFContact: the found element
    */  
    public JDFContact getContact(int iSkip)
    {
        if (hasChildElement(ElementName.CONTACT, JDFConstants.EMPTYSTRING))
        {
            return super.getContact(iSkip);
        }
        return getParentArtDeliveryIntent().getContact();
    }
}

