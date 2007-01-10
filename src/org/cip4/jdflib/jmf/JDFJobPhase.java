/**
==========================================================================
class JDFJobPhase extends JDFResource
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
**/





package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoJobPhase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;



//----------------------------------
    public class JDFJobPhase extends JDFAutoJobPhase
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFJobPhase
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFJobPhase(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFJobPhase
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFJobPhase(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFJobPhase
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFJobPhase(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    public String toString()
    {
        return "JDFJobPhase[  --> " + super.toString() + " ]";
    }
    
    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.JDF, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return new ElementInfo(super.getTheElementInfo(), elemInfoTable);
    }
    
    /**
     * Returns detailed status information.
     * @return String
     */
    public String getStatusDetails()
    {
        return getAttribute(AttributeName.STATUSDETAILS);
    }

    /**
     * Method getQueueEntryID.
     * @return String
     */
    public String getQueueEntryID()
    {
        return getAttribute(AttributeName.QUEUEENTRYID);
    }

    /**
     * Method getJobID.
     * @return String
     */
    public String getJobID()
    {
        return getAttribute(AttributeName.JOBID);
    }
    
    /**
     * get part map vector
     * @return VJDFAttributeMap: vector of attribute maps, one for each part
     */
    public VJDFAttributeMap getPartMapVector()
    {
        return super.getPartMapVector();
    }

    /**
     * set all parts to those define in vParts
     * @param VJDFAttributeMap vParts: vector of attribute maps for the parts
     */
    public void setPartMapVector(VJDFAttributeMap vParts)
    {
        super.setPartMapVector(vParts);
    }

    /**
     * set all parts to those define in vParts
     * @param JDFAttributeMap mPart: attribute map for the part to set
     */
    public void setPartMap(JDFAttributeMap mPart)
    {
        super.setPartMap(mPart);
    }

    /**
     * remove the part defined in mPart
     * @param JDFAttributeMap mPart: attribute map for the part to remove
     */
    public void removePartMap(JDFAttributeMap mPart)
    {
        super.removePartMap(mPart);
    }

    /**
     * check whether the part defined in mPart is included
     * @param JDFAttributeMap mPart: attribute map for the part to remove
     * @return boolean - returns true if the part exists
     */
    public boolean hasPartMap(JDFAttributeMap mPart)
    {
        return super.hasPartMap(mPart);
    }
    
    public JDFNode getCreateNode()
   {
       return (JDFNode)getCreateElement_KElement(ElementName.JDF, null, 0);
   }

   public JDFNode appendNode()
   {
       return  (JDFNode)appendElementN(ElementName.JDF, 1, null);
    }
   
   public JDFNode getNode()
   {
       return (JDFNode) getElement(ElementName.JDF, null, 0);
   }



   
}


