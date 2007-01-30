/**
==========================================================================
class JDFMessageService extends JDFResource
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
**/





package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoMessageService;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;



//----------------------------------
    public class JDFMessageService extends JDFAutoMessageService
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFMessageService
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFMessageService(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFMessageService
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFMessageService(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFMessageService
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFMessageService(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.auto.JDFAutoMessageService#toString()
     */
    public String toString()
    {
        return "JDFMessageService[  --> " + super.toString() + " ]";
    }
    
    /**
     * Method getType.
     * @return String
     */
    public String getType()
    {
        return getAttribute(AttributeName.TYPE);
    }

    /**
     * Method isCommand.
     * @return boolean
     */
    public boolean isCommand()
    {
        return getBoolAttribute(AttributeName.COMMAND, JDFConstants.EMPTYSTRING, false);
        //return getAttributeAsBoolean(AttributeName.COMMAND);
    }

    /**
     * Method isQuery.
     * @return boolean
     */
    public boolean isQuery()
    {
        return getBoolAttribute(AttributeName.QUERY, JDFConstants.EMPTYSTRING, false);
        //return getAttributeAsBoolean(AttributeName.QUERY);
    }    
}



