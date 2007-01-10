/**
==========================================================================
class JDFQueueEntryPriParams extends JDFResource
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
**/





package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoQueueEntryPriParams;
import org.cip4.jdflib.core.AttributeName;



//----------------------------------
    public class JDFQueueEntryPriParams extends JDFAutoQueueEntryPriParams
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFQueueEntryPriParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFQueueEntryPriParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFQueueEntryPriParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFQueueEntryPriParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFQueueEntryPriParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFQueueEntryPriParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * toString()
     * @return String
     */
    public String toString()
    {
        return "JDFQueueEntryPriParams[  --> " + super.toString() + " ]";
    }
    
    /**
     * Method setQueueEntryID.
     * @param strID the ID to set
     */
    public void setQueueEntryID(String strID)
    {
        setAttribute(AttributeName.QUEUEENTRYID, strID);
    }
    
    /**
     * Method setPriority.
     * @param nPrio the priority to set
     */
    public void setPriority(int nPrio)
    {
        setAttribute(AttributeName.PRIORITY, nPrio, null );
    }    
}



