/**
 * JDFAddress.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoAddress;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.w3c.dom.DOMException;


public class JDFAddress extends JDFAutoAddress
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFAddress
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFAddress(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFAddress
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFAddress(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAddress
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFAddress(
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
        return "JDFAddress[  --> " + super.toString() + " ]" ;
    }
    
    
    public String getExtendedAddressText()
    {
        if (hasAttribute(ElementName.EXTENDEDADDRESS))
        {
            return getExtendedAddress().getText();
        }
        return JDFConstants.EMPTYSTRING;
    }

    
    public void setExtendedAddressText(String  extendedAddress)
    {
        getCreateExtendedAddress().setText(extendedAddress);
    }
}



