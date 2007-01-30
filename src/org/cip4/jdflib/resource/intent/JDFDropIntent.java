/**
 * ========================================================================== 
 * class JDFDropIntent extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de   using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 * Revision history:   ...
 */

package org.cip4.jdflib.resource.intent;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDropIntent;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFSpanSurplusHandling;
import org.cip4.jdflib.span.JDFSpanTransfer;
import org.cip4.jdflib.span.JDFStringSpan;
import org.w3c.dom.DOMException;


public class JDFDropIntent extends JDFAutoDropIntent
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFDropIntent
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFDropIntent(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFDropIntent
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFDropIntent(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFDropIntent
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFDropIntent(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    public String toString()
    {
        return "JDFDropIntent[  --> " + super.toString() + " ]";
    }
    
    /**
    * Get parent node of 'this' - node DeliveryIntent
    * @return KElement: DeliveryIntent node
    */
    public JDFDeliveryIntent getParentDeliveryIntent()
    {
        return (JDFDeliveryIntent)getParentNode();
    }

    /**
    * Get of 'this' the value of attribute AdditionalAmount.
    * If not specified, get the default value of attribute AdditionalAmount,
    * that is specified in it's parent element (node DeliveryIntent)
    * @return WString: attribute value
    */
    public int getAdditionalAmount()
    {
        if (hasAttribute(AttributeName.ADDITIONALAMOUNT, null, false))
        {    
            return super.getAdditionalAmount();
        }
        return getParentDeliveryIntent().getAdditionalAmount();
    }
   
    /**
    * Get of 'this' the value of attribute BuyerAccount.
    * If not specified, get the default value of attribute BuyerAccount,
    * that is specified in it's parent element (node DeliveryIntent)
    * @return WString: attribute value
    */
    public String getBuyerAccount()
    {
        if (hasAttribute(AttributeName.BUYERACCOUNT))
        {    
            return super.getBuyerAccount();
        }
        return getParentDeliveryIntent().getBuyerAccount();
    }
    
    /**
    * Get of 'this' the value of element Method.
    * If not specified, get the default value of element Method,
    * that is specified in it's parent element (node DeliveryIntent)
    * @return JDFNameSpan: element value
    */
    public JDFNameSpan getMethod()
    {
        if (hasChildElement(ElementName.METHOD, null))
        {    
            return super.getMethod();
        }
        return getParentDeliveryIntent().getMethod();
    }
    
    /**
    * Get of 'this' the value of element ReturnMethod.
    * If not specified, get the default value of element ReturnMethod,
    * that is specified in it's parent element (node DeliveryIntent)
    * @return JDFNameSpan: element value
    */
    public JDFNameSpan getReturnMethod()
    {
        if (hasChildElement(ElementName.RETURNMETHOD, null))
        {    
            return super.getReturnMethod();
        }
        return getParentDeliveryIntent().getReturnMethod();
    }
    
    /**
    * Get of 'this' the value of element ServiceLevel.
    * If not specified, get the default value of element ServiceLevel,
    * that is specified in it's parent element (node DeliveryIntent)
    * @return JDFStringSpan: element value
    */
    public JDFStringSpan getServiceLevel()
    {
        if (hasChildElement(ElementName.SERVICELEVEL, null))
        {    
            return super.getServiceLevel();
        }
        return getParentDeliveryIntent().getServiceLevel();
    }
    
    /**
    * Get of 'this' the value of element SurplusHandling.
    * If not specified, get the default value of element SurplusHandling,
    * that is specified in it's parent element (node DeliveryIntent)
    * @return JDFSpanSurplusHandling: element value
    */
    public JDFSpanSurplusHandling getSurplusHandling()
    {
        if (hasChildElement(ElementName.SURPLUSHANDLING, null))
        {    
            return super.getSurplusHandling();
        }
        return getParentDeliveryIntent().getSurplusHandling();
    }
    
    /**
    * Get of 'this' the value of element Transfer.
    * If not specified, get the default value of element Transfer,
    * that is specified in it's parent element (node DeliveryIntent)
    * @return JDFSpanTransfer: element value
    */
    public JDFSpanTransfer getTransfer()
    {
        if (hasChildElement(ElementName.TRANSFER, null))
        {    
            return getTransfer();
        }
        return getParentDeliveryIntent().getTransfer();
    }
    
    /**
    * Get of 'this' the iSkip-th child element Contact. If not specified,
    * get the child element Contact of it's parent element (node DeliveryIntent)
    * @return JDFContact: the found element
    */
    public JDFContact getContact(int iSkip) 
    {
        if (hasChildElement(ElementName.CONTACT, null))
        {    
            return super.getContact(iSkip);
        }
        return getParentDeliveryIntent().getContact();
    }

} // class JDFIDPLayout
    // ==========================================================================
