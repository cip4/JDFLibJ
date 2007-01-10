/**
==========================================================================
class JDFDeviceInfo extends JDFResource
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
**/





package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFPhaseTime;



//----------------------------------
    public class JDFDeviceInfo extends JDFAutoDeviceInfo
{
    private static final long serialVersionUID = 1L;
    /**
     * Constructor for JDFDeviceInfo
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFDeviceInfo(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFDeviceInfo
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFDeviceInfo(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFDeviceInfo
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFDeviceInfo(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    public String toString()
    {
        return "JDFDeviceInfo[  --> " + super.toString() + " ]";
    }
    
     /**
     * Method getJobCount.
     * @return int
     * @deprecated use numChildElements(ElementName.JOBPHASE,null)
     */
    public int getJobCount()
    {
        return getChildrenByTagName(ElementName.JOBPHASE,null,null, false, true,0).size();
    }   
    
    /**
     * create a JobPhase message from a phaseTime Audit
     * @param pt the phasetime audit
     * @return JDFJobPhase the jobphase element that has been filled by the phaseTime
     */
    public JDFJobPhase createJobPhaseFromPhaseTime(final JDFPhaseTime pt)
    {
        JDFJobPhase jp=appendJobPhase();
        JDFNode node=pt.getParentJDF();
        
        jp.setJobID(node.getJobID(true));
        jp.setJobPartID(node.getJobPartID(true));
        jp.setPartMapVector(pt.getPartMapVector());
        jp.setStatus(pt.getStatus());
        final String statusDetails = pt.getStatusDetails();
        jp.setStatusDetails(statusDetails);
        jp.setPhaseStartTime(pt.getStart());
        jp.eraseEmptyAttributes(true);
        //TODO set more
        return jp;
    }
}



